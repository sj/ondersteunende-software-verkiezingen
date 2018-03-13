/*
 * CandidateResult
 * 
 * Created on 21.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.result;

import java.util.Comparator;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.determination.P2List;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;

/**
 * A CandidateResult belongs to a candidate and a P2List. It contains all information about the
 * election result with respect to this candidate on this P2List.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class CandidateResult {
  public static Comparator<CandidateResult> BY_OLD_POSITION = new Comparator<CandidateResult>() {
    public int compare(CandidateResult x, CandidateResult y) {
      return x.getOldListPosition() - y.getOldListPosition();
    }
  };
  public static Comparator<CandidateResult> BY_NEW_POSITION = new Comparator<CandidateResult>() {
    public int compare(CandidateResult x, CandidateResult y) {
      int px = x.getNewListPosition();
      int py = y.getNewListPosition();
      if (px == 0) {
        if (py == 0) {
          return x.getOldListPosition() - y.getOldListPosition();
        } else {
          return 1;
        }
      } else {
        if (py == 0) {
          return -1;
        }
      }
      return px - py;
    }
  };

  public enum DrawnByLot {
    WINNER, LOOSER, NONE
  }

  private final Candidate candidate;
  private final P2List p2List;
  private final int oldListPosition;
  private final int newListPosition; // 0 for dead candidates

  private final long votes;
  private Elected elected;
  private final DrawnByLot drawnByLot;
  private final int drawnByLotIndex; // default = 0
  private final boolean abovePreferentialBarrier;
  private P2List electedInP2List; // null if not elected in this P3-list

  /**
   * Factory for a CandidateResult for dead candidates
   */
  public static CandidateResult deadCandidate(P2List p2List, Candidate candidate, long votes) {
    return new CandidateResult(candidate, p2List, 0, votes, CandidateResult.Elected.DEAD,
        CandidateResult.DrawnByLot.NONE, 0, false, null);
  }

  @SuppressWarnings("hiding")
  public CandidateResult(Candidate candidate,
      P2List p2List,
      int newListPosition,
      long votes,
      Elected elected,
      DrawnByLot drawnByLot,
      int drawnByLotIndex,
      boolean abovePreferentialBarrier,
      P2List electedInP2List) {
    this.candidate = candidate;
    this.p2List = p2List;
    this.oldListPosition = p2List.getCandidates().indexOf(candidate) + 1;
    this.newListPosition = newListPosition;
    this.votes = votes;
    this.elected = elected;
    this.drawnByLot = drawnByLot;
    this.drawnByLotIndex = drawnByLotIndex;
    this.abovePreferentialBarrier = abovePreferentialBarrier;
    this.electedInP2List = electedInP2List;
  }

  public Candidate getCandidate() {
    return candidate;
  }

  public P2List getP2List() {
    return p2List;
  }

  public int getOldListPosition() {
    return oldListPosition;
  }

  public int getNewListPosition() {
    return newListPosition;
  }

  public Elected getElected() {
    return elected;
  }

  public DrawnByLot getDrawnByLot() {
    return drawnByLot;
  }

  public int getDrawnByLotIndex() {
    return drawnByLotIndex;
  }

  public boolean isAbovePreferentialBarrier() {
    return abovePreferentialBarrier;
  }

  public P2List getElectedInP2List() {
    return electedInP2List;
  }

  public boolean isDeceased() {
    return Elected.DEAD.equals(elected);
  }

  public boolean isElectedHere() {
    return Elected.HERE.equals(elected) || Elected.SUCCESSOR.equals(elected);
  }

  /**
   * @return <code>true</code> if the candidate is elected on one of the P2-lists of the current
   *         P3-list. This may be another one than getP2List().
   */
  public boolean isElectedOnListGroup() {
    return Elected.ANOTHER_P2_LIST.equals(elected) || Elected.HERE.equals(elected)
        || Elected.SUCCESSOR.equals(elected);
  }

  public boolean tookPartInDrawingLots() {
    return !DrawnByLot.NONE.equals(drawnByLot);
  }

  public boolean wasDrawnByLot() {
    return DrawnByLot.WINNER.equals(drawnByLot);
  }

  /**
   * Inner Enum class for indicating if / how a candidate is or is not elected on this P2-list.
   * ANOTHER_P2_LIST means that the candidate was elected on another P2-list of the same P3-list. If
   * the candidate was elected on another P3-list, the value is NOWHERE.
   * 
   * @author jon@ivu.de, IVU Traffic Technologies AG
   */
  public enum Elected {
    HERE("(**)"), ANOTHER_P2_LIST("(*)"), NOWHERE(""), DEAD(""), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ 

    ANOTHER_P3_LIST(Messages.getString(MessageKeys.Result_Tracelog_ElectedInAnotherListGroup)),

    SUCCESSOR(Messages
        .getString(MessageKeys.Result_Tracelog_SuccessorOfCandidateElectedInOtherListGroup));

    public static Elected getElected(P2List p2List, P2List electedInP2List) {
      if (electedInP2List == null) {
        return Elected.NOWHERE;
      } else if (electedInP2List.equals(p2List)) {
        return Elected.HERE;
      } else {
        return Elected.ANOTHER_P2_LIST;
      }
    }

    private final String _display;

    private Elected(String display) {
      this._display = display;
    }

    public String getDisplay() {
      return _display;
    }
  }

  public String displayForLog() {
    if (isDeceased()) {
      return Messages.bind(MessageKeys.Result_Tracelog_Candidate_0_DeceasedAndNotRegarded,
          getCandidate().getName());
    }

    String result = Messages
        .bind(MessageKeys.Result_Tracelog_0_1_2_OldPosition_3_District_4_Votes_5,
            elected.getDisplay(),
            getNewListPosition(),
            candidate.getName(),
            getOldListPosition(),
            p2List.getElectoralDistrictNumbers(),
            votes);
    if (abovePreferentialBarrier) {
      result += ", "; //$NON-NLS-1$
      result += Messages.getString(MessageKeys.Result_Tracelog_AbovePreferencialBarrier);
    }
    result += ")"; //$NON-NLS-1$

    return result;
  }

  /**
   * The candidate originally received a seat here but also one or more other P3-lists. As a
   * consequence he looses his seat here.
   */
  public void setCandidateElectedOnOtherP3List() {
    elected = Elected.ANOTHER_P3_LIST;
  }

  /**
   * Another candidate on p2List originally received a seat here but also one or more other
   * P3-lists. As a consequence that other candidate looses his seat here and THIS candidate
   * receives it as a successor.
   */
  public void setSuccessorForCandidateElectedOnOtherP3List(P2List p2List) {
    elected = Elected.SUCCESSOR;
    electedInP2List = p2List;
  }

}
