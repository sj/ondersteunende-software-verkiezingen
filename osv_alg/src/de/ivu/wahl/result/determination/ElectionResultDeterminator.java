/*
 * ElectionResultDeterminator
 * 
 * Created on 15.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.determination;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import de.ivu.wahl.result.ElectionAndCandidatesAndVotes;
import de.ivu.wahl.result.Fraction;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.drawlots.DrawingLotsCallback;
import de.ivu.wahl.result.drawlots.DrawingLotsException;
import de.ivu.wahl.result.gsda.GeneralSeatDistributor;
import de.ivu.wahl.result.gsda.GsdaParameters;
import de.ivu.wahl.result.gsda.GsdaResult;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;
import de.ivu.wahl.result.result.AnomalyFactory;
import de.ivu.wahl.result.result.CandidateResult;
import de.ivu.wahl.result.result.Distribution;
import de.ivu.wahl.result.result.ElectionResult;
import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;

public class ElectionResultDeterminator {
  public static final Logger APPLOG = Logger.getLogger("applog"); //$NON-NLS-1$
  private static final String THREE_ASTERISKS = "*** "; //$NON-NLS-1$

  public static ElectionResult determineElectionResult(DrawingLotsCallback lotingCallback,
      ElectionAndCandidatesAndVotes ecv, ElectionResult electionResult) {
    try {
      ElectionResultDeterminator calculator = new ElectionResultDeterminator(lotingCallback, ecv);
      return calculator.calculate(electionResult);
    } catch (DrawingLotsException exc) {
      electionResult.setConflict(exc.getDecisionType(), exc.getAlternatives());
      return electionResult;
    }
  }

  private final DrawingLotsCallback drawingLotsCallback;
  private final ElectionAndCandidatesAndVotes ecv;
  private VotesCounter votesCounter;
  private CandidatesCounter candidatesCounter;

  private ElectionResultDeterminator(DrawingLotsCallback drawingLotsCallback,
      ElectionAndCandidatesAndVotes ecv) {
    this.drawingLotsCallback = drawingLotsCallback;
    this.ecv = ecv;
  }

  private ElectionResult calculate(ElectionResult electionResult) {
    AnomalyFactory anomalyFactory = new AnomalyFactory(electionResult, ecv);

    // Count the votes
    APPLOG
        .info(THREE_ASTERISKS + Messages.getString(MessageKeys.Result_Tracelog_CalculationStarts));
    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_ElectionType_0,
        ecv.getElectionSubcategory()));
    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_TotalNumberOfSeats_0,
        ecv.getNumberOfSeats()));
    votesCounter = new VotesCounter(ecv);
    votesCounter.logVotes();

    // Determine electoral quota
    Fraction electoralQuota = new Fraction(votesCounter.getTotalNumberOfVotes(),
        ecv.getNumberOfSeats());
    APPLOG.info(THREE_ASTERISKS
        + Messages.bind(MessageKeys.Result_Tracelog_TheElectoralQuotaIs_0,
            Util.displayQuotient(electoralQuota)));

    // Validate combined lists
    CombinedListValidator combinedListValidator = new CombinedListValidator(ecv, votesCounter);
    Map<P42List, Long> p42ListsAndVotes = combinedListValidator
        .determineValidityOfCombinedLists(electionResult, anomalyFactory);

    // Count candidates
    Set<P42List> p42Lists = Util.createUnmodifiableCopy(p42ListsAndVotes.keySet());
    candidatesCounter = new CandidatesCounter(ecv, p42Lists);

    // Assign seats to P42-lists
    Map<P42List, Long> p42ListsAndSeats = assignSeatsToP42Lists(electionResult,
        anomalyFactory,
        p42ListsAndVotes);

    // Assign seats to P3-lists
    Map<P3List, Long> p3ListsAndSeats = assignSeatsWithinP42Lists(electionResult,
        anomalyFactory,
        p42ListsAndSeats);

    // Assign seats to P2-lists
    Map<P3List, GsdaResult<P2List>> p2ListsAndSeats = assignSeatsWithinP3Lists(electionResult,
        anomalyFactory,
        p3ListsAndSeats);

    // Assign seats to candidates
    APPLOG.info(THREE_ASTERISKS
        + Messages.getString(MessageKeys.Result_Tracelog_AssigningSeatsToCandidates));
    Map<P2List, List<CandidateResult>> allCandidateResults = new HashMap<P2List, List<CandidateResult>>();
    for (P3List p3List : Util.sortByNumber(p3ListsAndSeats.keySet())) {
      GsdaResult<P2List> p2ListsAndSeatsInP3List = p2ListsAndSeats.get(p3List);
      long seats = p3ListsAndSeats.get(p3List).longValue();
      Map<P2List, List<CandidateResult>> newOrder = ElectionResultForCandidatesDeterminator
          .calculate(drawingLotsCallback,
              votesCounter,
              ecv,
              p3List,
              seats,
              p2ListsAndSeatsInP3List,
              anomalyFactory);
      allCandidateResults.putAll(newOrder);
    }

    // Handle candidates that are elected on more than one P3-list
    new MultipleElectedCandidateHandler(ecv, votesCounter, anomalyFactory)
        .handleCandidatesElectedOnMoreThanOnP3List(allCandidateResults);

    writeCandidateResultsToElectionResult(electionResult, allCandidateResults);

    return electionResult;
  }

  private void writeCandidateResultsToElectionResult(ElectionResult electionResult,
      Map<P2List, List<CandidateResult>> allCandidateResults) {
    for (P3List p3List : Util.sortByNumber(Util.asList(ecv.getP3Lists()))) {
      for (P2List p2List : p3List.getP2ListsSorted()) {
        List<CandidateResult> candidateResults = allCandidateResults.get(p2List);
        for (CandidateResult candidateResult : candidateResults) {
          electionResult.candidateResult(candidateResult);
        }
      }
    }
  }

  private Map<P42List, Long> assignSeatsToP42Lists(ElectionResult electionResult,
      AnomalyFactory anomalyFactory, Map<P42List, Long> votesPerP42List) {
    GsdaParameters parameters = getGsdaParameters(ecv.getElectionSubcategory());
    ParentOfP42List parent = null;
    Map<P42List, Long> numbersOfCandidatesPerP42List = candidatesCounter.getCandidatesPerP42List();
    GeneralSeatDistributor<P42List, ParentOfP42List> generalSeatDistributor = new GeneralSeatDistributor<P42List, ParentOfP42List>(
        drawingLotsCallback, parent, ecv.getNumberOfSeats(), votesPerP42List,
        numbersOfCandidatesPerP42List, parameters, electionResult, anomalyFactory, Distribution.P42);
    GsdaResult<P42List> p42ListsAndSeats = generalSeatDistributor.calculate();
    return p42ListsAndSeats.getListsAndSeats();
  }

  private GsdaParameters getGsdaParameters(ElectionSubcategory electionSubcategory) {
    if (ElectionSubcategory.EK.equals(electionSubcategory)) {
      return GsdaParameters.forP42DistributionEK();
    } else if (ElectionSubcategory.EP.equals(electionSubcategory)
        || ElectionSubcategory.TK.equals(electionSubcategory)) {
      return GsdaParameters.forP42DistributionEpTk();
    } else if (ElectionSubcategory.GR1.equals(electionSubcategory)
        || ElectionSubcategory.AB1.equals(electionSubcategory)
        || ElectionSubcategory.ER1.equals(electionSubcategory)
        || ElectionSubcategory.GC.equals(electionSubcategory)) {
      return GsdaParameters.forP42DistributionGr1();
    } else if (ElectionSubcategory.BC.equals(electionSubcategory)) {
      return GsdaParameters.forP42DistributionBC();
    } else {
      return GsdaParameters.forP42DistributionPsAb2Gr2();
    }
  }

  private Map<P3List, Long> assignSeatsWithinP42Lists(ElectionResult electionResult,
      AnomalyFactory anomalyFactory, Map<P42List, Long> p42ListsAndSeats) {
    Map<P3List, Long> result = new HashMap<P3List, Long>();
    // To make the order of drawing lots events deterministic, sort the P42-lists
    List<P42List> p42Lists = Util.asSortList(p42ListsAndSeats.keySet());
    for (P42List p42List : p42Lists) {
      long p42Seats = p42ListsAndSeats.get(p42List);
      GsdaResult<P3List> p3ListsAndSeats = assignSeatsWithinP42List(p42List,
          p42Seats,
          electionResult,
          anomalyFactory);
      result.putAll(p3ListsAndSeats.getListsAndSeats());
    }
    return Collections.unmodifiableMap(result);
  }

  private GsdaResult<P3List> assignSeatsWithinP42List(P42List p42List, long p42Seats,
      ElectionResult electionResult, AnomalyFactory anomalyFactory) {
    Set<P3List> p3Lists = p42List.getP3Lists();
    Map<P3List, Long> p3ListsAndVotes = Util.createUnmodifiableSubMap(votesCounter
        .getVotesPerP3List(), p3Lists);
    Map<P3List, Long> p3ListsAndCandidates = Util.createUnmodifiableSubMap(candidatesCounter
        .getCandidatesPerP3List(), p3Lists);
    GsdaResult<P3List> p3ListsAndSeatsWithinP42List = new ElectionResultForP3ListsDeterminator()
        .calculate(drawingLotsCallback,
            p42List,
            p42Seats,
            p3ListsAndVotes,
            p3ListsAndCandidates,
            electionResult,
            anomalyFactory,
            ecv.getElectionSubcategory(),
            Distribution.P3);
    return p3ListsAndSeatsWithinP42List;
  }

  private Map<P3List, GsdaResult<P2List>> assignSeatsWithinP3Lists(ElectionResult electionResult,
      AnomalyFactory anomalyFactory, Map<P3List, Long> p3ListsAndSeats) {
    Map<P3List, GsdaResult<P2List>> result = new HashMap<P3List, GsdaResult<P2List>>();
    List<P3List> p3Lists = Util.asSortList(p3ListsAndSeats.keySet());
    // To make the order of drawing lots events deterministic, sort the P3-lists
    for (P3List p3List : p3Lists) {
      long p3Seats = p3ListsAndSeats.get(p3List);
      GsdaResult<P2List> p2ListsAndSeats = assignSeatsWithinP3List(p3List,
          p3Seats,
          electionResult,
          anomalyFactory);
      result.put(p3List, p2ListsAndSeats);
    }
    return Collections.unmodifiableMap(result);
  }

  private GsdaResult<P2List> assignSeatsWithinP3List(P3List p3List, long p3Seats,
      ElectionResult electionResult, AnomalyFactory anomalyFactory) {
    Set<P2List> p2Lists = p3List.getP2Lists();
    Map<P2List, Long> p2ListsAndVotes = Util.createUnmodifiableSubMap(votesCounter
        .getVotesPerP2List(), p2Lists);
    Map<P2List, Long> p2ListsAndCandidates = Util.createUnmodifiableSubMap(candidatesCounter
        .getCandidatesPerP2List(), p2Lists);
    GsdaResult<P2List> result = new ElectionResultForP2ListsDeterminator()
        .calculate(drawingLotsCallback,
            p3List,
            p3Seats,
            p2ListsAndVotes,
            p2ListsAndCandidates,
            electionResult,
            anomalyFactory,
            ecv.getElectionSubcategory(),
            Distribution.P2);

    for (P2List p2List : p2Lists) {
      Long p2Seats = result.get(p2List);
      electionResult.totalSeatsAssigned(p2List, p2Seats);
    }
    return result;
  }

}
