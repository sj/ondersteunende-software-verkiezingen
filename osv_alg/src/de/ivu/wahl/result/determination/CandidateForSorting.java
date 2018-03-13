/*
 * CandidateForSorting
 * 
 * Created on 04.01.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.determination;

import java.util.Map;
import java.util.Set;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.NamedObject;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.drawlots.DrawingLotsAlternative;
import de.ivu.wahl.result.drawlots.DrawingLotsIdentifier;
import de.ivu.wahl.result.result.CandidateResult;
import de.ivu.wahl.result.result.CandidateResult.DrawnByLot;
import de.ivu.wahl.result.result.CandidateResult.Elected;

/**
 * Wrapper around a candidate used for sorting them to determine their new order.
 * <p>
 * ATTENTION: Not immutable: The result of drawing lots may be set.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class CandidateForSorting implements NamedObject, DrawingLotsAlternative {
  private final Candidate candidate;
  private final Map<P2List, Long> p2ListsAndVotes;
  private final long numberOfVotes;
  private final boolean isAbovePreferencialBarrier;
  private final boolean takesPartInDrawingLots;
  private final boolean hasPrefSeatWithoutLot;
  private final long numberOfCandidatesWithMoreVotes;
  private ListPositionAndDistrictNumber listPositionAndDistrictNumberCache = null;
  private final P3List p3List;

  // mutable attributes
  private int selectedByLotIndex;
  private boolean hasPrioritySeat = false;
  private P2List _electedForP2List = null;

  CandidateForSorting(Candidate candidate,
      Map<P2List, Long> p2ListsAndVotes,
      long numberOfVotes,
      boolean isAbovePreferencialBarrier,
      boolean takesPartInDrawingLots,
      boolean hasPrefSeatWithoutLot,
      long numberOfCandidatesWithMoreVotes,
      P3List p3List) {

    this.candidate = candidate;
    this.p2ListsAndVotes = Util.createUnmodifiableCopy(p2ListsAndVotes);
    this.numberOfVotes = numberOfVotes;
    this.isAbovePreferencialBarrier = isAbovePreferencialBarrier;
    this.takesPartInDrawingLots = takesPartInDrawingLots;
    this.hasPrefSeatWithoutLot = hasPrefSeatWithoutLot;
    this.selectedByLotIndex = 0;
    this.numberOfCandidatesWithMoreVotes = numberOfCandidatesWithMoreVotes;
    this.p3List = p3List;
  }

  public long getNumberOfVotes() {
    return numberOfVotes;
  }

  public boolean isAbovePreferencialBarrier() {
    return isAbovePreferencialBarrier;
  }

  public Candidate getCandidate() {
    return candidate;
  }

  public Set<P2List> getP2Lists() {
    return p2ListsAndVotes.keySet();
  }

  public Map<P2List, Long> getP2ListsAndVotes() {
    return p2ListsAndVotes;
  }

  public boolean takesPartInDrawingLots() {
    return takesPartInDrawingLots;
  }

  /**
   * If drawing of k elements out of n is required, the first drawn element receives
   * <code>selectedByLotIndex = k</code>, the next drawn element receives
   * <code>selectedByLotIndex = k - 1</code>. The last drawn element receives
   * <code>selectedByLotIndex = 1</code>. All others receive <code>selectedByLotIndex = 0</code>.
   */
  public int getSelectedByLotIndex() {
    return selectedByLotIndex;
  }

  /**
   * One of two setters of this class. Sets the <code>selectedByLotIndex</code> attribute to
   * <code>index</code> .
   */
  public void setSelectedByLotIndex(final int index) {
    selectedByLotIndex = index;
  }

  public long o1() {
    return hasPrioritySeat ? numberOfVotes : 0;
  }

  public int o2() {
    return selectedByLotIndex;
  }

  public long o3(P2List p2List) {
    return (isAbovePreferencialBarrier() && !hasPrioritySeat) ? getVotesOnP2List(p2List) : 0;
  }

  public String getName() {
    return candidate.getName();
  }

  @Override
  public String toString() {
    return "CandidateForSorting(" + getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  public DrawingLotsIdentifier getIdentifier() {
    return new DrawingLotsIdentifier(this);
  }

  public boolean hasPrefSeatWithoutLot() {
    return hasPrefSeatWithoutLot;
  }

  public long getNumberOfCandidatesWithMoreVotes() {
    return numberOfCandidatesWithMoreVotes;
  }

  public ListPositionAndDistrictNumber getListPositionAndDistrictNumber() {
    if (listPositionAndDistrictNumberCache == null) {
      listPositionAndDistrictNumberCache = calculateListPositionAndDistrictNumber();
    }
    return listPositionAndDistrictNumberCache;
  }

  @SuppressWarnings("synthetic-access")
  private ListPositionAndDistrictNumber calculateListPositionAndDistrictNumber() {
    int positionInList = -1;
    int districtNumber = -1;
    for (P2List p2List : p2ListsAndVotes.keySet()) {
      int newDistrictNumber = p2List.getElectoralDistrictNumber();
      if (districtNumber == -1 || districtNumber >= newDistrictNumber) {
        int newPositionInList = p2List.getCandidates().indexOf(getCandidate()) + 1;
        if (districtNumber == -1 || districtNumber > newDistrictNumber
            || positionInList > newPositionInList) {
          positionInList = newPositionInList;
          districtNumber = newDistrictNumber;
        }
      }
    }
    return new ListPositionAndDistrictNumber(positionInList, districtNumber);
  }

  /**
   * Helper class
   */
  public static class ListPositionAndDistrictNumber
      implements
        Comparable<ListPositionAndDistrictNumber> {
    private final int listPosition;
    private final int electoralDistrictNumber;

    @SuppressWarnings("hiding")
    private ListPositionAndDistrictNumber(int listPosition, int electoralDistrictNumber) {
      this.listPosition = listPosition;
      this.electoralDistrictNumber = electoralDistrictNumber;
    }

    public int getListPosition() {
      return listPosition;
    }

    public int getElectoralDistrictNumber() {
      return electoralDistrictNumber;
    }

    public int compareTo(ListPositionAndDistrictNumber other) {
      if (this.listPosition < other.listPosition) {
        return -1;
      } else if (this.listPosition > other.listPosition) {
        return 1;
      } else if (this.electoralDistrictNumber < other.electoralDistrictNumber) {
        return -1;
      } else if (this.electoralDistrictNumber > other.electoralDistrictNumber) {
        return 1;
      } else {
        return 0;
      }
    }
  }

  public boolean hasPrioritySeat() {
    return hasPrioritySeat;
  }

  public void setHasPrioritySeat() {
    this.hasPrioritySeat = true;
  }

  public P3List getP3List() {
    return p3List;
  }

  public P2List getElectedForP2List() {
    return _electedForP2List;
  }

  public void setElectedForP2List(P2List electedForP2List) {
    this._electedForP2List = electedForP2List;
  }

  private long getVotesOnP2List(P2List p2List) {
    Long votes = p2ListsAndVotes.get(p2List);
    return votes == null ? 0L : votes;
  }

  /**
   * Converts me to a CandidateResult for the given p2List
   */
  public CandidateResult asCandidateResult(P2List p2List, int newPositionInList) {
    DrawnByLot drawnByLot = takesPartInDrawingLots() ? getSelectedByLotIndex() > 0
        ? CandidateResult.DrawnByLot.WINNER
        : CandidateResult.DrawnByLot.LOOSER : CandidateResult.DrawnByLot.NONE;
    Elected elected = Elected.getElected(p2List, getElectedForP2List());

    return new CandidateResult(getCandidate(), p2List, newPositionInList, getNumberOfVotes(),
        elected, drawnByLot, getSelectedByLotIndex(), isAbovePreferencialBarrier(),
        getElectedForP2List());
  }
}
