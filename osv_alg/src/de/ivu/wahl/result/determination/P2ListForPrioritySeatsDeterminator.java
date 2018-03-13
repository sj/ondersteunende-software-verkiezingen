/*
 * P2ListForPrioritySeatsDeterminator
 * 
 * Created on 07.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.determination;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;
import de.ivu.wahl.result.result.AnomalyFactory;

class P2ListForPrioritySeatsDeterminator {
  private final SeatDistributionInP3List _dist;
  private final Iterator<P2List> _rbs;
  private final AnomalyFactory _anomalyFactory;

  private P2ListForPrioritySeatsDeterminator(SeatDistributionInP3List dist,
      Iterator<P2List> rbs,
      AnomalyFactory anomalyFactory) {
    this._dist = dist;
    this._rbs = rbs;
    this._anomalyFactory = anomalyFactory;
  }

  /**
   * Determines the P2Lists on which the candidates with priority seat are elected. This method
   * loops over the sets C_i.
   */
  static void determineP2ListsForCandidatesWithPrioritySeat(SeatDistributionInP3List dist,
      Iterator<P2List> rbs,
      List<Set<CandidateForSorting>> candidatesWithPrioritySeatSorted,
      AnomalyFactory anomalyFactory) {
    new P2ListForPrioritySeatsDeterminator(dist, rbs, anomalyFactory)
        .calculate(candidatesWithPrioritySeatSorted);
  }

  /**
   * Determines the P2Lists on which the candidates with priority seat are elected. This method
   * loops over the sets C_i.
   */
  private void calculate(List<Set<CandidateForSorting>> candidatesWithPrioritySeatSorted) {
    int h = candidatesWithPrioritySeatSorted.size();
    for (int i = 1; i <= h; i++) {
      Set<CandidateForSorting> c_i = candidatesWithPrioritySeatSorted.get(i - 1);
      determineP2ListsForElectedCandidates(c_i);
    }
  }

  /**
   * Determines the P2Lists on which the candidates with priority seat that are in the sets C_i are
   * elected.
   */
  private void determineP2ListsForElectedCandidates(Collection<CandidateForSorting> candidatesWithPrioritySeat) {
    // create a copy - will be modified
    Set<CandidateForSorting> c_i = new HashSet<CandidateForSorting>(candidatesWithPrioritySeat);

    Map<CandidateForSorting, P2List> preferredP2Lists = getPreferredP2Lists(c_i); // sigma_c,j
    while (containsNotNullValue(preferredP2Lists)) {
      Map<P2List, Set<CandidateForSorting>> preferringCandidates = reverse(preferredP2Lists); // Y_s,j
      for (P2List p2List : preferringCandidates.keySet()) {
        Set<CandidateForSorting> set = preferringCandidates.get(p2List);
        if (set != null && !set.isEmpty()) {
          List<CandidateForSorting> list = Util.asList(set);
          Collections.sort(list, SortCandidatesUtil.sortByPositionOnP2List(p2List));
          int minOfRemainingSeatsAndNoOfCandidates = (int) Math.min(list.size(), _dist
              .getRemainingSeats(p2List));
          for (int j = 0; j < minOfRemainingSeatsAndNoOfCandidates; j++) {
            CandidateForSorting candidate = list.get(j);
            c_i.remove(candidate);
            candidate.setHasPrioritySeat();
            boolean enoughSeats = _dist.assignSeat(p2List, candidate);
            assert enoughSeats : Messages
                .bind(MessageKeys.Result_Assert_P2ListShouldHaveEnoughSeats_0, p2List.getName());
          }
          for (int j = minOfRemainingSeatsAndNoOfCandidates; j < list.size(); j++) {
            CandidateForSorting candidate = list.get(j);
            _anomalyFactory.candidateDoesNotReceiveSeatInPreferredDistrict(candidate, p2List);
          }
        }
      }
      preferredP2Lists = getPreferredP2Lists(c_i); // sigma_c,j
    }

    // Remaining candidates go to the P2List with most votes according to kieswet P 16 sub 2
    for (CandidateForSorting candidate : c_i) {
      P2List p2List = getP2ListWithMostVotesFor(candidate);
      candidate.setHasPrioritySeat();
      boolean enoughSeats = _dist.assignSeat(p2List, candidate);
      _anomalyFactory.candidateReceiveSeatInDistrictWithoutFreeSeat(candidate, p2List);
      assert !enoughSeats : Messages
          .bind(MessageKeys.Result_Assert_P2ListShouldNotHaveEnoughSeats_0, p2List.getName());
    }

    // The number of seats that have to be passed from another P2List
    int missingSeats = c_i.size();
    passSeatsFromOtherP2Lists(missingSeats);
  }

  private boolean containsNotNullValue(Map<CandidateForSorting, P2List> preferredP2Lists) {
    for (P2List p2List : preferredP2Lists.values()) {
      if (p2List != null) {
        return true;
      }
    }
    return false;
  }

  private Map<P2List, Set<CandidateForSorting>> reverse(Map<CandidateForSorting, P2List> preferredP2Lists) {
    Map<P2List, Set<CandidateForSorting>> result = new HashMap<P2List, Set<CandidateForSorting>>();
    for (CandidateForSorting candidate : preferredP2Lists.keySet()) {
      P2List p2List = preferredP2Lists.get(candidate);
      if (p2List != null) {
        Set<CandidateForSorting> set = result.get(p2List);
        if (set == null) {
          set = new HashSet<CandidateForSorting>();
          result.put(p2List, set);
        }
        set.add(candidate);
      }
    }
    return result;
  }

  private void passSeatsFromOtherP2Lists(int missingSeats) {
    for (int j = 0; j < missingSeats; j++) {
      P2List p2List = _dist.getP2ListWithRemainingSeat(_rbs);
      if (p2List == null) {
        // Roll back sequence is exhausted
        p2List = _dist.getFirstP2ListWithRemainingSeat();
        _anomalyFactory.rollBackSequenceExhaustedSeatWithheldFrom(p2List);
      } else {
        _anomalyFactory.seatWithheldFromRollBackSequence(p2List);
      }
      assert p2List != null : Messages.bind(MessageKeys.Builder_Assert_MustNotBeNull, "p2List"); //$NON-NLS-1$
      _dist.removeRemainingSeat(p2List);
    }
  }

  /**
   * @param c_i
   * @return for each candidate in c_i return of the P2-lists with remaining seats the one where the
   *         candidate received the most votes.
   */
  private Map<CandidateForSorting, P2List> getPreferredP2Lists(Set<CandidateForSorting> c_i) {
    Map<CandidateForSorting, P2List> result = new HashMap<CandidateForSorting, P2List>();
    for (CandidateForSorting candidate : c_i) {
      Map<P2List, Long> p2ListsAndVotes = candidate.getP2ListsAndVotes();
      long maxVotes = -1;
      P2List preferredP2List = null;
      for (P2List p2List : p2ListsAndVotes.keySet()) {
        if (_dist.hasRemainingSeats(p2List)) {
          long votesOnList = p2ListsAndVotes.get(p2List);
          if (preferredP2List == null
              || maxVotes < votesOnList
              || (maxVotes == votesOnList && preferredP2List.getElectoralDistrictNumber() > p2List
                  .getElectoralDistrictNumber())) {
            preferredP2List = p2List;
            maxVotes = votesOnList;
          }
        }
      }
      result.put(candidate, preferredP2List);
    }
    return result;
  }

  /**
   * @return the P2List on with the candidate is nominated and received the highest number of votes.
   *         In case the number of votes are equal, return the P2List with the lower electoral
   *         district number.
   */
  private P2List getP2ListWithMostVotesFor(CandidateForSorting candidate) {
    Map<P2List, Long> p2ListsAndVotes = candidate.getP2ListsAndVotes();
    long maxVotes = -1;
    P2List preferredP2List = null;
    for (P2List p2List : p2ListsAndVotes.keySet()) {
      long votesOnList = p2ListsAndVotes.get(p2List);
      if (preferredP2List == null
          || maxVotes < votesOnList
          || (maxVotes == votesOnList && preferredP2List.getElectoralDistrictNumber() > p2List
              .getElectoralDistrictNumber())) {
        preferredP2List = p2List;
        maxVotes = votesOnList;
      }
    }
    return preferredP2List;
  }

}
