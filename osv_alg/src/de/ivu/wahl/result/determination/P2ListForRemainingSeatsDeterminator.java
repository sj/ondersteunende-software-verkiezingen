/*
 * P2ListForRemainingSeatsDeterminator
 * 
 * Created on 07.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.determination;

import static de.ivu.wahl.result.determination.ElectionResultDeterminator.APPLOG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;
import de.ivu.wahl.result.result.AnomalyFactory;

/**
 * Assigns seats to candidates that received less votes than the preferencial barrier. These seats
 * are assigned according to the list order. See kieswet article P 17 and P 18.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class P2ListForRemainingSeatsDeterminator {
  private final SeatDistributionInP3List dist;
  private final Iterator<P2List> rfs;
  private final Map<P2List, List<CandidateForSorting>> unelectedCandidates; // mutable lists
  private final AnomalyFactory anomalyFactory;

  public P2ListForRemainingSeatsDeterminator(SeatDistributionInP3List dist,
      Iterator<P2List> rfs,
      Map<Candidate, CandidateForSorting> candidatesForSorting,
      AnomalyFactory anomalyFactory) {
    this.dist = dist;
    this.rfs = rfs;
    this.unelectedCandidates = dist.getUnelectedCandidates(candidatesForSorting); // E_i,3(s)
    this.anomalyFactory = anomalyFactory;
  }

  public void calculate() {
    while (dist.hasRemainingSeats()) {
      handleLateListExhaustion();
      Map<P2List, List<CandidateForSorting>> nextElectedCandidates = getNextElectedCandidates(); // E_i,4(s)
      Map<CandidateForSorting, List<P2List>> nextElectedCandidatesReversed = reverse(nextElectedCandidates); // S_i(c)
      Set<CandidateForSorting> allNextElectedCandidates = nextElectedCandidatesReversed.keySet(); // E_i,5

      for (CandidateForSorting candidate : allNextElectedCandidates) {
        List<P2List> p2Lists = nextElectedCandidatesReversed.get(candidate);
        P2List p2List = listWithMostVotesAndLowestDistrictNumber(candidate, p2Lists); // sigma_i(c)
        boolean enoughSeats = dist.assignSeat(p2List, candidate);
        assert enoughSeats : Messages.bind(MessageKeys.Result_Assert_P2ListShouldHaveEnoughSeats_0,
            p2List.getName());
      }
      for (List<CandidateForSorting> unelectedCand : unelectedCandidates.values()) {
        unelectedCand.removeAll(allNextElectedCandidates);
      }
    }
  }

  /**
   * @return the P2-list for which the given candidate received the highest number of votes. For
   *         equal numbers of votes return the P2-list with the lower electoral district number
   */
  private P2List listWithMostVotesAndLowestDistrictNumber(CandidateForSorting candidate,
      List<P2List> p2Lists) {
    long votes = -1;
    P2List result = null;
    List<P2List> listsWithMaxVotes = new ArrayList<P2List>();
    Map<P2List, Long> listsAndVotes = candidate.getP2ListsAndVotes();
    for (P2List p2List : p2Lists) {
      long newVotes = listsAndVotes.get(p2List).longValue();
      if (newVotes > votes) {
        votes = newVotes;
        result = p2List;
        listsWithMaxVotes.clear();
        listsWithMaxVotes.add(p2List);
      } else if (newVotes == votes) {
        listsWithMaxVotes.add(p2List);
        if (result.getElectoralDistrictNumber() > p2List.getElectoralDistrictNumber()) {
          result = p2List;
        }
      }
    }

    if (p2Lists.size() > 1) {
      // The candidate is elected at the same time in more than one district. The district is
      // selected by the number of votes which the candidate received in that district.
      APPLOG.info(Messages
          .bind(MessageKeys.Result_Tracelog_DistrictWhere_0_isElectedIsDeterminedByNumberOfVotes,
              candidate.getName()));
      if (listsWithMaxVotes.size() > 1) {
        // The candidate received the same highest the number of votes in more than one district.
        // The district is selected by the district number.
        APPLOG
            .info(Messages
                .bind(MessageKeys.Result_Tracelog_DistrictWhere_0_isElectedIsDeterminedByDistrictNumber,
                    candidate.getName()));
      }
      APPLOG.info(Messages
          .bind(MessageKeys.Result_Tracelog_District_0_WasDeterminedFromTheFollowingList,
              result.getName()));
      for (P2List p2List : p2Lists) {
        long newVotes = listsAndVotes.get(p2List).longValue();
        int electoralDistrictNumber = p2List.getElectoralDistrictNumber();
        APPLOG.info(Messages
            .bind(MessageKeys.Result_Tracelog_TheListContains_0_With_1_VotesAndDistrictNumber_2,
                p2List.getName(),
                newVotes,
                electoralDistrictNumber));
      }
    }

    return result;
  }

  private Map<CandidateForSorting, List<P2List>> reverse(
      Map<P2List, List<CandidateForSorting>> nextElectedCandidates) {
    Map<CandidateForSorting, List<P2List>> result = new HashMap<CandidateForSorting, List<P2List>>();
    for (P2List p2List : nextElectedCandidates.keySet()) {
      List<CandidateForSorting> candidates = nextElectedCandidates.get(p2List);
      for (CandidateForSorting candidate : candidates) {
        List<P2List> list = result.get(candidate);
        if (list == null) {
          list = new ArrayList<P2List>();
          result.put(candidate, list);
        }
        list.add(p2List);
      }
    }
    return result;
  }

  /**
   * @return from each P2-list as many unelected candidates from the top of the list as there are
   *         remaining seats.
   */
  private Map<P2List, List<CandidateForSorting>> getNextElectedCandidates() {
    Map<P2List, List<CandidateForSorting>> result = new TreeMap<P2List, List<CandidateForSorting>>();
    for (P2List p2List : unelectedCandidates.keySet()) {
      List<CandidateForSorting> unelected = unelectedCandidates.get(p2List);
      int size = (int) dist.getRemainingSeats(p2List);
      if (size > unelected.size()) {
        size = unelected.size();
      }
      List<CandidateForSorting> subList = Util.copy(unelected.subList(0, size));
      result.put(p2List, subList);
    }
    return result;
  }

  /**
   * Handles the case that for a P2-list there are still unassigned seats but no more unelected
   * candidates. In this case the remaining seats are passed to another P2-list from the
   * "roll forward sequence".
   */
  private void handleLateListExhaustion() {
    P2List p2List = determineP2ListWithUnassignedSeatsButNoUnelectedCandidates();
    while (p2List != null) {
      dist.removeRemainingSeat(p2List);
      P2List receiver = getP2ListFromRfs();
      anomalyFactory.lateListExhaustion(p2List, receiver);
      if (receiver != null) {
        dist.addRemainingSeat(receiver);
        APPLOG.info(Messages
            .bind(MessageKeys.Result_Tracelog_SeatOf_CannotBeAssignedToCandidateAndPassesTo_1,
                p2List.getName(),
                receiver.getName()));
      } else {
        // This can never happen because the P3-list
        String text = Messages
            .bind(MessageKeys.Result_Assert_SeatAssignedTo_0_CannotBeAssignedToCandidateOfSameP3List,
                p2List.getName());
        APPLOG.info(text);
        throw new RuntimeException(text);
      }
      p2List = determineP2ListWithUnassignedSeatsButNoUnelectedCandidates();
    }
  }

  /**
   * @return the first (in the order of the electoral district number) P2-list with more unassigned
   *         seats than unelected candidates. <code>null</code> if no such P2-list exists.
   */
  private P2List determineP2ListWithUnassignedSeatsButNoUnelectedCandidates() {
    List<P2List> p2ListsSorted = Util.asList(unelectedCandidates.keySet());
    Collections.sort(p2ListsSorted, P2List.SORT_BY_ELECTORAL_DISTRICT_NUMBER);
    for (P2List p2List : p2ListsSorted) {
      if (dist.getRemainingSeats(p2List) > 0 && unelectedCandidates.get(p2List).size() == 0) {
        return p2List;
      }
    }
    return null;
  }

  private P2List getP2ListFromRfs() {
    while (rfs.hasNext()) {
      P2List next = rfs.next();
      if (dist.getRemainingSeats(next) < unelectedCandidates.get(next).size()) {
        return next;
      }
    }
    return null;
  }
}
