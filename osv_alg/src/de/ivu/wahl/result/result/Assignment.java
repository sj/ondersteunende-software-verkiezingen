/*
 * Assignment
 * 
 * Created on 24.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.result;

import de.ivu.wahl.result.determination.GeneralList;
import de.ivu.wahl.result.determination.P2List;
import de.ivu.wahl.result.determination.P3List;
import de.ivu.wahl.result.determination.P42List;

public class Assignment {
  private final int index;
  private final GeneralList list;
  private final GeneralList assignmentParent;
  private final AssignmentSupplement supplement;

  private final long seats;
  private final AssignmentType assignmentType;
  private final boolean byLot;
  private final boolean cosalgMode;

  @SuppressWarnings("hiding")
  public Assignment(int index,
      GeneralList list,
      GeneralList assignmentParent,
      long seats,
      AssignmentType assignmentType,
      boolean byLot,
      boolean cosalgMode,
      AssignmentSupplement supplement) {
    this.index = index;
    this.list = list;
    this.assignmentParent = assignmentParent;
    this.seats = seats;
    this.assignmentType = assignmentType;
    this.byLot = byLot;
    this.cosalgMode = cosalgMode;
    this.supplement = supplement;
  }

  /**
   * Constructor for filling the gaps
   */
  @SuppressWarnings("hiding")
  public Assignment(int index, GeneralList list, GeneralList assignmentParent, boolean cosalgMode) {
    this(index, list, assignmentParent, 0, null, false, cosalgMode, AssignmentSupplement.NULL);
  }

  public long getSeats() {
    return seats;
  }

  public AssignmentType getAssignmentType() {
    return assignmentType;
  }

  public boolean isByLot() {
    return byLot;
  }

  public boolean isCosalgMode() {
    return cosalgMode;
  }

  public int getIndex() {
    return index;
  }

  public GeneralList getList() {
    return list;
  }

  public GeneralList getAssignmentParent() {
    return assignmentParent;
  }

  public AssignmentSupplement getSupplement() {
    return supplement;
  }

  public Distribution getDistribution() {
    if (list instanceof P2List) {
      return Distribution.P2;
    } else if (list instanceof P42List) {
      return Distribution.P42;
    } else if (list instanceof P3List) {
      return Distribution.P3;
    } else {
      return null;
    }
  }
}