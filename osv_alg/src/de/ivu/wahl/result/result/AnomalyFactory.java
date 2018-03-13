/*
 * AnomalyFactory
 * 
 * Created on 12.10.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.ElectionAndCandidatesAndVotes;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.determination.CandidateForSorting;
import de.ivu.wahl.result.determination.GeneralList;
import de.ivu.wahl.result.determination.MultipleElectedCandidate;
import de.ivu.wahl.result.determination.P2List;
import de.ivu.wahl.result.determination.P3List;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;

/**
 * Creates pieces of information about exceptional events and passes them to the ElectionResult.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class AnomalyFactory {
  private final ElectionResult _electionResult;
  private final Map<P2List, P3List> p2ListAndP3List;
  private final boolean isEK;
  private int _count = 0;

  public AnomalyFactory(ElectionResult electionResult, ElectionAndCandidatesAndVotes ecv) {
    this._electionResult = electionResult;
    this.isEK = ecv.getElectionSubcategory().isEK();
    Map<P2List, P3List> tempP2ListAndP3List = new HashMap<P2List, P3List>();
    for (P3List p3List : ecv.getP3Lists()) {
      for (P2List p2List : p3List.getP2Lists()) {
        tempP2ListAndP3List.put(p2List, p3List);
      }
    }
    this.p2ListAndP3List = Util.createUnmodifiableCopy(tempP2ListAndP3List);
  }

  private void addMaybeToP22(String message) {
    add(message, false);
  }

  private void addToP22(String message) {
    add(message, true);
  }

  private void add(String message) {
    add(message, false);
  }

  private void add(String message, boolean shallBePublishedInModelP22) {
    _count++;
    Anomaly anomaly = new Anomaly(message, _count, shallBePublishedInModelP22);
    _electionResult.addAnomalitiy(anomaly);
  }

  private P3List getP3List(P2List p2List) {
    return p2ListAndP3List.get(p2List);
  }

  /**
   * Introducing information that one or more candidates were elected on multiple P3-lists
   */
  public void candidateElectedForMultipleP3Lists(
      List<MultipleElectedCandidate> multipleElectedCandidates) {
    for (MultipleElectedCandidate multipleElectedCandidate : multipleElectedCandidates) {
      addMaybeToP22(Messages.bind(MessageKeys.Result_User_MultipleElectedCandidate_0,
          multipleElectedCandidate.getCandidate().getName()));
    }
  }

  /**
   * Information on which P2-list the candidate keeps his / her seat
   */
  public void multipleElectedCandidateKeepsSeat(Candidate candidate, P2List p2List) {
    P3List p3List = getP3List(p2List);
    addMaybeToP22(Messages
        .bind(MessageKeys.Result_User_MultipleElectedCandidate_0_KeepsSeatFor_1_In_2,
            candidate.getName(),
            p3List.getNumber(),
            p2List.getElectoralDistrictNumbers()));
  }

  /**
   * Information on which P2-list the candidate keeps his / her seat
   */
  public void multipleElectedCandidateLoosesSeat(Candidate candidate, P2List p2List) {
    P3List p3List = getP3List(p2List);
    addMaybeToP22(Messages
        .bind(MessageKeys.Result_User_MultipleElectedCandidate_0_LoosesSeatFor_1_In_2,
            candidate.getName(),
            p3List.getNumber(),
            p2List.getElectoralDistrictNumbers()));
  }

  /**
   * Information of who is the successor for the multiple elected candidate on the p2List
   */
  public void multipleElectedCandidateSuccessorFound(Candidate candidate, P2List p2List) {
    P3List p3List = getP3List(p2List);
    addMaybeToP22(Messages
        .bind(MessageKeys.Result_User_Successor_0_ForMultipleElectedCandidateFor_1_In_2,
            candidate.getName(),
            p3List.getNumber(),
            p2List.getElectoralDistrictNumbers()));
  }

  /**
   * Information that no successor has been fould for the multiple elected candidate on the p2List
   */
  public void multipleElectedCandidateNoSuccessorFound(Candidate candidate, P2List p2List) {
    P3List p3List = getP3List(p2List);
    addMaybeToP22(Messages
        .bind(MessageKeys.Result_User_NoSuccessorFoundForMultipleElectedCandidate_0_For_1_In_2,
            candidate.getName(),
            p3List.getNumber(),
            p2List.getElectoralDistrictNumbers()));
  }

  /**
   * Information about the looser of a conflict about seat(s) of one p2List
   */
  public void candidateDoesNotReceiveSeatInPreferredDistrict(CandidateForSorting candidate,
      P2List p2List) {
    P3List p3List = getP3List(p2List);
    addToP22(Messages
        .bind(MessageKeys.Result_User_Candidate_0_For_1_in_2_DoesNotReceiveSeatInPreferredDistrict,
            candidate.getName(),
            p3List.getNumber(),
            p2List.getElectoralDistrictNumbers()));
  }

  /**
   * Information that no seat is available on all lists where he/she is nominated. So he/she
   * receives the seat on the list where he/she received the most votes. The seat must be withheld
   * from another P2List.
   */
  public void candidateReceiveSeatInDistrictWithoutFreeSeat(CandidateForSorting candidate,
      P2List p2List) {
    P3List p3List = getP3List(p2List);
    addToP22(Messages.bind(MessageKeys.Result_User_Candidate_0_ReceivesSeatByP16Sub2For_1In_2,
        candidate.getName(),
        p3List.getNumber(),
        p2List.getElectoralDistrictNumbers()));
  }

  public void rollBackSequenceExhaustedSeatWithheldFrom(P2List p2List) {
    P3List p3List = getP3List(p2List);
    addToP22(Messages.bind(MessageKeys.Result_User_RollBackSequenceExhaustedFor_0_SeatWithheldIn_1,
        p3List.getNumber(),
        p2List.getElectoralDistrictNumbers()));
  }

  public void seatWithheldFromRollBackSequence(P2List p2List) {
    P3List p3List = getP3List(p2List);
    addToP22(Messages.bind(MessageKeys.Result_User_SeatWithheldFor_0_In_1_ByP16Sub2,
        p3List.getNumber(),
        p2List.getElectoralDistrictNumbers()));
  }

  public void lateListExhaustion(P2List p2List, P2List receiver) {
    P3List p3List = getP3List(p2List);
    addToP22(Messages.bind(MessageKeys.Result_User_LateListExhaustionFor_0_In_1_SeatPassesTo_2,
        p3List.getNumber(),
        p2List.getElectoralDistrictNumbers(),
        receiver.getElectoralDistrictNumbers()));
  }

  public void listExhaustionToOtherList(GeneralList list, long numberOfLostSeats) {
    if (isEK) {
      if (numberOfLostSeats == 1) {
        add(Messages.bind(MessageKeys.Result_User_ListExhaustionEKFor_0_SeatLostToOtherList,
            list.getName()));
      } else {
        add(Messages.bind(MessageKeys.Result_User_ListExhaustionEKFor_0_1_SeatsLostToOtherList,
            list.getName(),
            numberOfLostSeats));
      }
    } else {
      if (numberOfLostSeats == 1) {
        add(Messages.bind(MessageKeys.Result_User_ListExhaustionFor_0_SeatLostToOtherList,
            list.getName()));
      } else {
        add(Messages.bind(MessageKeys.Result_User_ListExhaustionFor_0_1_SeatsLostToOtherList,
            list.getName(),
            numberOfLostSeats));
      }
    }
  }

  /**
   * @param looser may be null if isFictitiousSeatDistribution == true
   * @param isFictitiousSeatDistribution
   */
  public void absoluteMajority(GeneralList winner, GeneralList looser,
      boolean isFictitiousSeatDistribution) {
    if (isFictitiousSeatDistribution) {
      if (looser == null) {
        add(Messages
            .bind(MessageKeys.Result_User_List_0_HasAbsoluteMajorityOfVotesInFictitiousDistributionAndReceivesSeatWithoutLooser,
                winner.getName()));
      } else {
        add(Messages
            .bind(MessageKeys.Result_User_List_0_HasAbsoluteMajorityOfVotesInFictitiousDistributionAndReceivesSeatFromList_1,
                winner.getName(),
                looser.getName()));
      }
    } else {
      add(Messages
          .bind(MessageKeys.Result_User_List_0_HasAbsoluteMajorityOfVotesAndReceivesSeatFromList_1,
              winner.getName(),
              looser.getName()));
    }
  }
}
