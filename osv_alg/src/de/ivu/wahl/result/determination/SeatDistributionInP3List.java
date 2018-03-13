/*
 * SeatDistributionInP3List
 * 
 * Created on 06.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.determination;

import static de.ivu.wahl.result.determination.ElectionResultDeterminator.APPLOG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;

/**
 * Helper class for determining the P2List on which elected candidates are assigned their seat.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class SeatDistributionInP3List {
  private final P3List p3List;
  private final Map<P2List, Long> remainingSeats;
  private final Map<P2List, Set<CandidateForSorting>> elected; // E_i

  /**
   * Constructor
   */
  public SeatDistributionInP3List(P3List p3List, Map<P2List, Long> p2ListsAndSeats) {
    this.p3List = p3List;
    this.remainingSeats = new HashMap<P2List, Long>(p2ListsAndSeats); // phi_i
    this.elected = new HashMap<P2List, Set<CandidateForSorting>>(); // E_i
    for (P2List p2List : p3List.getP2Lists()) {
      elected.put(p2List, new HashSet<CandidateForSorting>());
    }
  }

  /**
   * @return the number of seats of the p2List that are not assigned to a candidate yet.
   */
  public long getRemainingSeats(P2List p2List) {
    return remainingSeats.get(p2List);
  }

  /**
   * @return <code>true</code>, if the given p2List has a remaining seat, <code>false</code>
   *         otherwise
   */
  public boolean hasRemainingSeats(P2List p2List) {
    return getRemainingSeats(p2List) > 0;
  }

  /**
   * @return <code>true</code>, if any P2-list has a remaining seat, <code>false</code> otherwise
   */
  public boolean hasRemainingSeats() {
    for (Long remSeats : remainingSeats.values()) {
      if (remSeats.longValue() > 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return <code>true</code> if the corresponding p2List had enough remaining seats,
   *         <code>false</code> otherwise
   */
  public boolean assignSeat(P2List p2List, CandidateForSorting candidate) {
    String info = Messages.bind(MessageKeys.Result_Tracelog_AssignSeatTo_0_OnCandidateList_1,
        candidate.getCandidate().getName(),
        p2List.getName());
    APPLOG.info(info);
    elected.get(p2List).add(candidate);
    candidate.setElectedForP2List(p2List);
    long remSeats = getRemainingSeats(p2List);
    if (remSeats > 0) {
      remainingSeats.put(p2List, remSeats - 1);
      return true;
    } else {
      return false;
    }
  }

  public void removeRemainingSeat(P2List p2List) {
    assert hasRemainingSeats(p2List) : Messages.bind(MessageKeys.Builder_Assert_MustBePositive,
        "getRemainingSeats(p2List)"); //$NON-NLS-1$
    remainingSeats.put(p2List, getRemainingSeats(p2List) - 1);
  }

  public void addRemainingSeat(P2List p2List) {
    remainingSeats.put(p2List, getRemainingSeats(p2List) + 1);
  }

  /**
   * This method iterates further over the rbs Iterator.
   * 
   * @return the first element from the rbs that still has a remaining seat.
   */
  public P2List getP2ListWithRemainingSeat(Iterator<P2List> rbs) {
    while (rbs.hasNext()) {
      P2List next = rbs.next();
      if (hasRemainingSeats(next)) {
        return next;
      }
    }
    return null;
  }

  /**
   * @return the P2List with smallest electoral district number that still has a remaining seat.
   */
  public P2List getFirstP2ListWithRemainingSeat() {
    for (P2List p2List : p3List.getP2ListsSorted()) {
      if (hasRemainingSeats(p2List)) {
        return p2List;
      }
    }
    return null;
  }

  /**
   * For each P2List the candidates that can still be assigned a seat. I.e. those candidates that
   * are not yet elected and that are not dead. The order is the same as the order on the list.
   * 
   * @param candidatesForSorting contains for each allocatable (i.e. not dead) candidate the
   *          corresponding CandidateForSorting
   * @return an unmodifiable Map of modifiable (!) Lists of CandidateForSorting
   */
  public Map<P2List, List<CandidateForSorting>> getUnelectedCandidates(Map<Candidate, CandidateForSorting> candidatesForSorting) {
    Map<P2List, List<CandidateForSorting>> result = new TreeMap<P2List, List<CandidateForSorting>>();
    Set<CandidateForSorting> allElectedCandidates = getAllElectedCandidates();
    for (P2List p2List : p3List.getP2Lists()) {
      List<CandidateForSorting> cand = new ArrayList<CandidateForSorting>();
      for (Candidate candidate : p2List.getCandidates()) {
        CandidateForSorting candidateForSorting = candidatesForSorting.get(candidate);
        // For dead candidates, candidateForSorting == null
        if (candidateForSorting != null && !allElectedCandidates.contains(candidateForSorting)) {
          cand.add(candidateForSorting);
        }
      }
      result.put(p2List, cand);
    }
    return Collections.unmodifiableMap(result);
  }

  public Set<CandidateForSorting> getAllElectedCandidates() {
    Set<CandidateForSorting> result = new HashSet<CandidateForSorting>();
    for (Set<CandidateForSorting> electedCandidates : elected.values()) {
      result.addAll(electedCandidates);
    }
    return result;
  }

}
