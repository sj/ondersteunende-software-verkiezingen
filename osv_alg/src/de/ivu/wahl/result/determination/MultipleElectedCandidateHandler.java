/*
 * MultipleElectedCandidateHandler
 * 
 * Created on 24.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.determination;

import static de.ivu.wahl.result.determination.ElectionResultDeterminator.APPLOG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.CandidateList;
import de.ivu.wahl.result.ElectionAndCandidatesAndVotes;
import de.ivu.wahl.result.MultimapUtil;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;
import de.ivu.wahl.result.result.AnomalyFactory;
import de.ivu.wahl.result.result.CandidateResult;
import de.ivu.wahl.result.result.CandidateResult.Elected;

/**
 * Helper class that implements the part of the algorithm that deals with candidates that are
 * elected on more than one P3-list.
 * <p>
 * The method handleCandidatesElectedOnMoreThanOnP3List() has side effects on the
 * {@link CandidateResult}s that are handed as parameters. The methods
 * setCandidateElectedOnOtherP3List() and setSuccessorForCandidateElectedOnOtherP3List() are called
 * on the respective CandidateResults.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class MultipleElectedCandidateHandler {
  private final ElectionAndCandidatesAndVotes ecv;
  private final VotesCounter votesCounter;
  private final AnomalyFactory anomalyFactory;

  /**
   * Constructor
   */
  @SuppressWarnings("hiding")
  public MultipleElectedCandidateHandler(ElectionAndCandidatesAndVotes ecv,
      VotesCounter votesCounter,
      AnomalyFactory anomalyFactory) {
    this.ecv = ecv;
    this.votesCounter = votesCounter;
    this.anomalyFactory = anomalyFactory;
  }

  /**
   * @return <code>true</code> if any changes have been made, <code>false</code> otherwise
   */
  public void handleCandidatesElectedOnMoreThanOnP3List(Map<P2List, List<CandidateResult>> allCandidateResults) {
    Map<Candidate, List<CandidateResult>> electedCandidates = getElectedCandidatesAndListOfCandidateResults(allCandidateResults);
    List<MultipleElectedCandidate> multipleElectedCandidates = getMultipleElectedCandidates(electedCandidates);
    if (multipleElectedCandidates.isEmpty()) {
      return;
    }
    Collections.sort(multipleElectedCandidates);
    anomalyFactory.candidateElectedForMultipleP3Lists(multipleElectedCandidates);

    // Each candidate can be a successor at most once
    Set<Candidate> successors = new HashSet<Candidate>();

    for (MultipleElectedCandidate multipleElectedCandidate : multipleElectedCandidates) {
      handleMultipleElectedCandidate(multipleElectedCandidate, allCandidateResults, successors);
    }

    writeResultToLog(allCandidateResults);
  }

  private Map<Candidate, List<CandidateResult>> getElectedCandidatesAndListOfCandidateResults(Map<P2List, List<CandidateResult>> result) {
    Map<Candidate, List<CandidateResult>> electedCandidates = new HashMap<Candidate, List<CandidateResult>>();
    for (List<CandidateResult> candidateResults : result.values()) {
      for (CandidateResult candidateResult : candidateResults) {
        if (candidateResult.isElectedHere()) {
          MultimapUtil
              .addToList(electedCandidates, candidateResult.getCandidate(), candidateResult);
        }
      }
    }
    return electedCandidates;
  }

  private List<MultipleElectedCandidate> getMultipleElectedCandidates(Map<Candidate, List<CandidateResult>> electedCandidates) {
    List<MultipleElectedCandidate> multipleElectedCandidates = new ArrayList<MultipleElectedCandidate>();
    for (List<CandidateResult> candidateResults : electedCandidates.values()) {
      if (candidateResults.size() > 1) {
        Candidate candidate = candidateResults.iterator().next().getCandidate();
        int minElectoralDistrictNumber = Integer.MAX_VALUE;
        int minListPosition = 0;
        int minListNumber = 0;
        for (CandidateList candidateList : ecv.getAllCandidateLists()) {
          int positionOnList = candidateList.getCandidates().indexOf(candidate) + 1;
          if (positionOnList != 0) {
            int electoralDistrictNumber = candidateList.getElectoralDistrict().getNumber();
            if (electoralDistrictNumber < minElectoralDistrictNumber) {
              minElectoralDistrictNumber = electoralDistrictNumber;
              minListPosition = positionOnList;
              minListNumber = candidateList.getListNumber();
            }
          }
        }
        MultipleElectedCandidate multipleElectedCandidate = new MultipleElectedCandidate(candidate,
            minElectoralDistrictNumber, minListPosition, minListNumber, candidateResults);
        multipleElectedCandidates.add(multipleElectedCandidate);
      }
    }
    return multipleElectedCandidates;
  }

  private void handleMultipleElectedCandidate(MultipleElectedCandidate multipleElectedCandidate,
      Map<P2List, List<CandidateResult>> allCandidateResults,
      Set<Candidate> successors) {
    // g_9,i - This is where the candiates will get his seat
    CandidateResult p3ListWithMostVotes = multipleElectedCandidate
        .getP3ListWithMostVotes(votesCounter, ecv);
    Candidate candidate = p3ListWithMostVotes.getCandidate();
    anomalyFactory.multipleElectedCandidateKeepsSeat(candidate, p3ListWithMostVotes.getP2List());
    List<CandidateResult> remainingCandidateResults = Util.copy(multipleElectedCandidate
        .getCandidateResults());
    remainingCandidateResults.remove(p3ListWithMostVotes);
    // remainingCandidateResults = S_10,i - This is where the candiates will NOT get his seat(s)
    for (CandidateResult remainingCandidateResult : remainingCandidateResults) {
      // Take away the seat from the candidate on this P3-list and P2-list
      remainingCandidateResult.setCandidateElectedOnOtherP3List();
      P2List p2List = remainingCandidateResult.getP2List();
      List<CandidateResult> newListOrder = allCandidateResults.get(p2List);
      CandidateResult successor = getSuccessor(newListOrder, successors);
      APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_Candidate_0_MultiplyElected_1,
          candidate.getName(),
          p2List.getName()));
      anomalyFactory.multipleElectedCandidateLoosesSeat(candidate, p2List);
      if (successor == null) {
        APPLOG
            .info(Messages
                .bind(MessageKeys.Result_Tracelog_NoSuccessorFoundForCandidate_0_OneSeatOf_1CannotBeAssigned,
                    remainingCandidateResult.getCandidate().getName(),
                    remainingCandidateResult.getP2List().getName()));
        anomalyFactory.multipleElectedCandidateNoSuccessorFound(candidate, p2List);
      } else {
        APPLOG.info(Messages
            .bind(MessageKeys.Result_Tracelog_Candidates_0_ReceivesSeatAsSuccessorOf_1_OnList_2,
                successor.getCandidate().getName(),
                remainingCandidateResult.getCandidate().getName(),
                p2List.getName()));
        successors.add(successor.getCandidate());
        anomalyFactory.multipleElectedCandidateSuccessorFound(successor.getCandidate(), p2List);
        successor.setSuccessorForCandidateElectedOnOtherP3List(p2List);
      }
    }
  }

  /**
   * @param successors Candidates in successors cannot be successor again
   */
  private CandidateResult getSuccessor(List<CandidateResult> newListOrder, Set<Candidate> successors) {
    for (CandidateResult candidateResult : newListOrder) {
      if (Elected.NOWHERE.equals(candidateResult.getElected())
          && !successors.contains(candidateResult.getCandidate())) {
        return candidateResult;
      }
    }
    return null;
  }

  private void writeResultToLog(Map<P2List, List<CandidateResult>> allCandidateResults) {
    APPLOG.info(""); //$NON-NLS-1$
    APPLOG.info(Messages.getString(MessageKeys.Result_Tracelog_CandidateElectedForMultiplePartys));
    APPLOG.info(Messages.getString(MessageKeys.Result_Tracelog_NewSeatAssignment));
    for (P3List p3List : Util.sortByNumber(Util.asList(ecv.getP3Lists()))) {
      APPLOG.info("*** " + Messages.bind(MessageKeys.Result_Tracelog_SeatAssignmentFor_0, //$NON-NLS-1$
          p3List.getName()));
      for (P2List p2List : p3List.getP2ListsSorted()) {
        APPLOG.info("** " //$NON-NLS-1$
            + Messages.bind(MessageKeys.Result_Tracelog_SeatAssignmentFor_0, p2List.getName()));
        List<CandidateResult> candidateResults = allCandidateResults.get(p2List);
        for (CandidateResult candidateResult : candidateResults) {
          APPLOG.info(candidateResult.displayForLog());
        }
      }
    }
    APPLOG.info(""); //$NON-NLS-1$
  }
}
