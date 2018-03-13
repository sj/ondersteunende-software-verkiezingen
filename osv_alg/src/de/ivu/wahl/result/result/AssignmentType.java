/*
 * AssignmentType
 * 
 * Created on 12.02.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.result;

/**
 * Enumeration of different kinds of assignments of seats to lists. TRIVIAL is a purely technical
 * AssignmentType for the assignment within a one-element P42-list or one-element P3-list.
 * 
 * @see StepType
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public enum AssignmentType {
  FIRST_ASSIGNMENT(1, "FA"), //$NON-NLS-1$ 

  DHONDT_ASSIGNMENT(2, "LA"), //$NON-NLS-1$ 

  NIEMEYER_ASSIGNMENT(3, "LR"), //$NON-NLS-1$

  ABSOLUTE_MAJORITY(4, "AM"), //$NON-NLS-1$

  EXHAUSTED_LIST(5, "EX"), //$NON-NLS-1$

  TRIVIAL(99, "TR"); //$NON-NLS-1$

  private int _id;
  private String _key;

  private AssignmentType(final int id, final String key) {
    this._id = id;
    this._key = key;
  }

  public int getId() {
    return _id;
  }

  public String getKey() {
    return _key;
  }

  public static AssignmentType byKey(String key) {
    for (AssignmentType each : values()) {
      if (each.getKey().equals(key)) {
        return each;
      }
    }

    return null;
  }
}
