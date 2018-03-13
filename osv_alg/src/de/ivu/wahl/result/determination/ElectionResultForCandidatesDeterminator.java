/*
 * ElectionResultForCandidatesDeterminator
 * 
 * Created on 04.01.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.determination;

import static de.ivu.wahl.result.determination.ElectionResultDeterminator.APPLOG;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.CandidateList;
import de.ivu.wahl.result.ElectionAndCandidatesAndVotes;
import de.ivu.wahl.result.Fraction;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.drawlots.DecisionType;
import de.ivu.wahl.result.drawlots.DrawingLotsCallback;
import de.ivu.wahl.result.gsda.GsdaResult;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;
import de.ivu.wahl.result.result.AnomalyFactory;
import de.ivu.wahl.result.result.CandidateResult;
import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;

/**
 * Determines the elected candidates and the new order on the list for one P3List.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
class ElectionResultForCandidatesDeterminator {

  private final DrawingLotsCallback _drawingLotsCallback;
  /**
   * For each of the candidates in the P3List the map <code>votesPerCandidate</code> contains the
   * number of votes of the candidate in the P3List
   */
  private final Map<Candidate, Long> votesPerCandidate;
  private final long totalNumberOfVotes;
  private final ElectionAndCandidatesAndVotes ecv;
  private final P3List p3List;
  private final GsdaResult<P2List> p2ListsAndSeats;
  private final long seats;
  private final Fraction preferentialBarrier;
  private final AnomalyFactory anomalyFactory;

  /**
   * @param seats The number of seats to be assigned to the candidates
   * @param anomalyFactory
   * @return an unmodifiable List of the candidates in the new order
   */
  public static Map<P2List, List<CandidateResult>> calculate(DrawingLotsCallback drawingLotsCallback,
      VotesCounter votesCounter,
      ElectionAndCandidatesAndVotes ecv,
      P3List p3List,
      long seats,
      GsdaResult<P2List> p2ListsAndSeats,
      AnomalyFactory anomalyFactory) {
    ElectionResultForCandidatesDeterminator calculator = new ElectionResultForCandidatesDeterminator(
        drawingLotsCallback, votesCounter, ecv, p3List, seats, p2ListsAndSeats, anomalyFactory);
    return calculator.calculate();
  }

  private ElectionResultForCandidatesDeterminator(DrawingLotsCallback lotingCallback,
      VotesCounter votesCounter,
      ElectionAndCandidatesAndVotes ecv,
      P3List p3List,
      long seats,
      GsdaResult<P2List> p2ListsAndSeats,
      AnomalyFactory anomalyFactory) {
    this._drawingLotsCallback = lotingCallback;
    this.votesPerCandidate = Util.createUnmodifiableCopy(votesCounter
        .getVotesPerCandidateInP3List(p3List));
    this.totalNumberOfVotes = votesCounter.getTotalNumberOfVotes();
    this.ecv = ecv;
    this.p3List = p3List;
    this.seats = seats;
    this.p2ListsAndSeats = p2ListsAndSeats;
    this.preferentialBarrier = new Fraction(totalNumberOfVotes
        * ecv.getPreferencialBarrierNumerator(), ecv.getNumberOfSeats()
        * ecv.getPreferencialBarrierDenominator());
    this.anomalyFactory = anomalyFactory;
  }

  /**
   * @return For each P2-list the new order of the candidates
   */
  private Map<P2List, List<CandidateResult>> calculate() {
    // Determine preferencial barrier
    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_ElectedCandidatesFor_0_1, p3List
        .getNumber(), p3List.getName()));
    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_PreferencialBarrier, Util
        .displayQuotient(preferentialBarrier)));

    Map<Candidate, CandidateForSorting> candidatesForSorting = getCandidatesForSorting(ecv
        .getElectionSubcategory());

    // Priority seats
    List<CandidateForSorting> candidatesWithPrioritySeat = getCandidatesWithPrioritySeat(candidatesForSorting
        .values());
    List<Set<CandidateForSorting>> candidatesWithPrioritySeatSorted = OrderUtil
        .sortAndGroup(candidatesWithPrioritySeat, SortCandidatesUtil.COMPARATOR_FOR_PREF_SEATS);
    SeatDistributionInP3List dist = new SeatDistributionInP3List(p3List, p2ListsAndSeats
        .getListsAndSeats());
    P2ListForPrioritySeatsDeterminator.determineP2ListsForCandidatesWithPrioritySeat(dist,
        p2ListsAndSeats.getRbs(),
        candidatesWithPrioritySeatSorted,
        anomalyFactory);

    // Remaining seats
    new P2ListForRemainingSeatsDeterminator(dist, p2ListsAndSeats.getRfs(), candidatesForSorting,
        anomalyFactory).calculate();

    // Determine new order, convert CandidateForSorting to Candidate
    Map<P2List, List<CandidateResult>> result = new HashMap<P2List, List<CandidateResult>>();
    for (P2List p2List : p3List.getP2ListsSorted()) {
      List<CandidateResult> newOrder = newOrderForP2List(p2List, candidatesForSorting);
      result.put(p2List, Collections.unmodifiableList(newOrder));
    }

    return Collections.unmodifiableMap(result);
  }

  private Map<Candidate, Map<P2List, Long>> getAllocatableCandidatesWithListsAndVotes() {
    Map<Candidate, Map<P2List, Long>> result = new HashMap<Candidate, Map<P2List, Long>>();
    for (CandidateList candidateList : p3List.getCandidateLists()) {
      for (Candidate candidate : candidateList.getCandidates()) {
        if (!ecv.getDeceasedCandidates().contains(candidate)) {
          Map<P2List, Long> innerMap = result.get(candidate);
          if (innerMap == null) {
            innerMap = new HashMap<P2List, Long>();
            result.put(candidate, innerMap);
          }
          P2List p2List = candidateList.getP2List();
          int index = candidateList.getCandidates().indexOf(candidate);
          long[] votesArray = ecv.getVotes().get(candidateList);
          long newVotes = votesArray[index];
          Long oldVotes = innerMap.get(p2List);
          if (oldVotes == null) {
            innerMap.put(p2List, newVotes);
          } else {
            innerMap.put(p2List, oldVotes + newVotes);
          }
        }
      }
    }
    return Util.createUnmodifiableNestedMap(result);
  }

  /**
   * Creates for each allocateable candidate a CandidateForSorting. CandidateForSorting contains a
   * lot of information that is relevant for the allocation of elected candidates.
   */
  private Map<Candidate, CandidateForSorting> getCandidatesForSorting(ElectionSubcategory electionSubcategory) {
    Map<Candidate, Map<P2List, Long>> candidatesAndP2Lists = getAllocatableCandidatesWithListsAndVotes();
    Set<Candidate> candidates = Util.createUnmodifiableCopy(candidatesAndP2Lists.keySet());

    // Collect all information needed for the new order and for drawing lots (if necessary)
    Map<Candidate, CandidateForSorting> result = new HashMap<Candidate, CandidateForSorting>();
    for (Candidate candidate : candidates) {
      long votes = getVotes(candidate);
      long numberOfCandidatesWithMoreVotes = getNumberOfCandidatesWithMoreVotes(candidates, votes);
      long numberOfCandidatesWithMoreOrEqualVotes = getNumberOfCandidatesWithMoreOrEqualVotes(candidates,
          votes);
      // For EK election, a candidate needs to reach the preferential barrier,
      // for other elections, a candidate needs to exceed the preferential barrier.
      final boolean isAbovePreferencialBarrier;
      if (ElectionSubcategory.EK.equals(electionSubcategory)) {
        isAbovePreferencialBarrier = preferentialBarrier.compareTo(votes) <= 0;
      } else {
        isAbovePreferencialBarrier = preferentialBarrier.compareTo(votes) < 0;
      }
      boolean takesPartInDrawingLots = isAbovePreferencialBarrier
          && numberOfCandidatesWithMoreVotes < seats
          && seats < numberOfCandidatesWithMoreOrEqualVotes;
      boolean hasPrefSeatWithoutLot = isAbovePreferencialBarrier
          && (numberOfCandidatesWithMoreOrEqualVotes <= seats);
      Map<P2List, Long> listsAndVotes = candidatesAndP2Lists.get(candidate);

      CandidateForSorting candidateForSorting = new CandidateForSorting(candidate, listsAndVotes,
          votes, isAbovePreferencialBarrier, takesPartInDrawingLots, hasPrefSeatWithoutLot,
          numberOfCandidatesWithMoreVotes, p3List);
      result.put(candidate, candidateForSorting);
    }

    return result;
  }

  /**
   * @return the subset of candidatesForSorting that receive a priority seat (i.e. receive a seat
   *         and have more votes in the P3-list than the preferential barrier).
   */
  private List<CandidateForSorting> getCandidatesWithPrioritySeat(Collection<CandidateForSorting> candidatesForSorting) {
    List<CandidateForSorting> result = new ArrayList<CandidateForSorting>();

    List<CandidateForSorting> candidatesForDrawingLots = new ArrayList<CandidateForSorting>();
    long seatsForLoting = 0;
    for (CandidateForSorting candidate : candidatesForSorting) {
      if (candidate.hasPrefSeatWithoutLot()) {
        result.add(candidate);
      }
      if (candidate.takesPartInDrawingLots()) {
        candidatesForDrawingLots.add(candidate);
        seatsForLoting = seats - candidate.getNumberOfCandidatesWithMoreVotes();
      }
    }

    // Perform drawing lots if necessary
    if (!candidatesForDrawingLots.isEmpty()) {
      // In the following method the candidatesForDrawingLots are changed!!!
      List<CandidateForSorting> candidatesDrawnByLot = selectCandidatesByLot(candidatesForDrawingLots,
          seatsForLoting);
      result.addAll(candidatesDrawnByLot);
    }
    return result;
  }

  private List<CandidateForSorting> selectCandidatesByLot(List<CandidateForSorting> candidatesForLoting,
      long seatsForLoting) {
    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_LotingRequiredToSelectCandidates,
        seatsForLoting,
        candidatesForLoting.size()));

    // Sort candidates by position in List
    List<CandidateForSorting> sortedCandidates = Util.copy(candidatesForLoting);
    Collections.sort(sortedCandidates, SortCandidatesUtil.COMPARE_BY_POSITION_IN_LIST);

    for (CandidateForSorting candidateForSorting : sortedCandidates) {
      APPLOG.info(candidateForSorting.getCandidate().getName());
    }

    List<CandidateForSorting> result = new ArrayList<CandidateForSorting>();
    for (int i = (int) seatsForLoting; i > 0; i--) {
      int index = _drawingLotsCallback.selectAlternative(sortedCandidates, DecisionType.CANDIDATES);
      CandidateForSorting selectedCandidate = sortedCandidates.get(index);
      APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_CandidateDrawnByLot, selectedCandidate
          .getName()));
      selectedCandidate.setSelectedByLotIndex(i);
      sortedCandidates.remove(selectedCandidate);
      result.add(selectedCandidate);
    }
    return result;
  }

  private List<CandidateResult> newOrderForP2List(P2List p2List,
      Map<Candidate, CandidateForSorting> candidatesForSorting) {
    List<CandidateForSorting> toSort = new ArrayList<CandidateForSorting>();
    List<Candidate> deceasedCandidates = new ArrayList<Candidate>();
    for (Candidate candidate : p2List.getCandidates()) {
      CandidateForSorting value = candidatesForSorting.get(candidate);
      if (value == null) {
        deceasedCandidates.add(candidate);
      } else {
        toSort.add(value);
      }
    }

    // Sort candidates
    Collections.sort(toSort, new SortCandidatesUtil.NewOrderOnP2ListComparator(p2List));

    // Write result to APPLOG
    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_CandidateOrders, p2List.getName()));
    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_CandidateOrders2));
    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_CandidateOrders3));
    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_InformationInParenthesis));
    List<CandidateResult> result = new ArrayList<CandidateResult>();
    int newPositionInList = 1;
    for (CandidateForSorting candidateForSorting : toSort) {
      CandidateResult candidateResult = candidateForSorting.asCandidateResult(p2List,
          newPositionInList);
      result.add(candidateResult);
      newPositionInList++;
    }
    for (Candidate candidate : deceasedCandidates) {
      CandidateResult candidateResult = CandidateResult.deadCandidate(p2List,
          candidate,
          votesPerCandidate.get(candidate));
      result.add(candidateResult);
    }

    // Log without modifications from MultipleElectedCandidateHandler
    for (CandidateResult candidateResult : result) {
      APPLOG.info(candidateResult.displayForLog());
    }

    return result;
  }

  private long getNumberOfCandidatesWithMoreOrEqualVotes(Collection<Candidate> candidates,
      final long votes) {
    long numberOfCandidatesWithMoreOrEqualVotes = 0;
    for (Candidate otherCandidate : candidates) {
      long otherVotes = getVotes(otherCandidate);
      if (otherVotes >= votes) {
        numberOfCandidatesWithMoreOrEqualVotes++;
      }
    }
    return numberOfCandidatesWithMoreOrEqualVotes;
  }

  private long getNumberOfCandidatesWithMoreVotes(Collection<Candidate> candidates, long votes) {
    long numberOfCandidatesWithMoreVotes = 0;
    for (Candidate otherCandidate : candidates) {
      long otherVotes = getVotes(otherCandidate);
      if (otherVotes > votes) {
        numberOfCandidatesWithMoreVotes++;
      }
    }
    return numberOfCandidatesWithMoreVotes;
  }

  /**
   * PRE: c is nominated in the current p3List
   * 
   * @return the number of votes that the candidate received in the current p3List
   */
  private long getVotes(Candidate candidate) {
    return votesPerCandidate.get(candidate).longValue();
  }

}
