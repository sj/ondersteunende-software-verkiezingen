/*
 * SortCandidatesUtil
 * 
 * Created on 10.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.determination;

import java.util.Comparator;
import java.util.List;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.Util;

/**
 * Utility class that provides Comparators to sort CandidateForSorting
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public final class SortCandidatesUtil {
  private SortCandidatesUtil() {
    // hide constructor
  }

  /**
   * Comparator<CandidateForSorting> Sorts candidates with priority seat by number of votes and the
   * result of drawing lots
   */
  public static final Comparator<CandidateForSorting> COMPARATOR_FOR_PREF_SEATS = new Comparator<CandidateForSorting>() {

    public int compare(CandidateForSorting x, CandidateForSorting y) {
      long diff = y.getNumberOfVotes() - x.getNumberOfVotes();
      if (diff != 0) {
        return Long.signum(diff);
      }
      int diff2 = y.o2() - x.o2();
      return Integer.signum(diff2);
    }
  };

  public static final Comparator<CandidateForSorting> COMPARE_BY_POSITION_IN_LIST = new Comparator<CandidateForSorting>() {
    public int compare(CandidateForSorting x, CandidateForSorting y) {
      return x.getListPositionAndDistrictNumber().compareTo(y.getListPositionAndDistrictNumber());
    }
  };

  public static Comparator<CandidateForSorting> sortByPositionOnP2List(P2List p2List) {
    final List<Candidate> candidates = Util.createUnmodifiableCopy(p2List.getCandidates());
    return new Comparator<CandidateForSorting>() {
      public int compare(CandidateForSorting x, CandidateForSorting y) {
        int ix = candidates.indexOf(x.getCandidate());
        int iy = candidates.indexOf(y.getCandidate());
        return ix == -1 ? (iy == -1 ? 0 : 1) : (iy == -1 ? -1 : (ix - iy));
      }
    };
  }

  /**
   * Comparator to determine the new list order on a P2List.
   * 
   * @author jon@ivu.de, IVU Traffic Technologies AG
   */
  public final static class NewOrderOnP2ListComparator implements Comparator<CandidateForSorting> {
    private final P2List _p2List;
    private final Comparator<CandidateForSorting> sortByPositionOnP2List;

    public NewOrderOnP2ListComparator(P2List p2List) {
      this._p2List = p2List;
      this.sortByPositionOnP2List = SortCandidatesUtil.sortByPositionOnP2List(p2List);
    }

    public int compare(CandidateForSorting x, CandidateForSorting y) {
      int result = Long.signum(y.o1() - x.o1());
      if (result != 0) {
        return result;
      }
      result = Integer.signum(y.o2() - x.o2());
      if (result != 0) {
        return result;
      }
      result = Long.signum(y.o3(_p2List) - x.o3(_p2List));
      if (result != 0) {
        return result;
      }
      return sortByPositionOnP2List.compare(x, y);
    }
  }

}
