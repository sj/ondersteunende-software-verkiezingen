/*
 * AssignmentSupplementWithRemainderFraction
 * 
 * Created on 24.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.result;

import de.ivu.wahl.result.Fraction;

/**
 * Supplementary information containing the number of votes of the list and the remainder according
 * to Niemeyer of the list that receive the assignment.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class AssignmentSupplementWithRemainderFraction extends AssignmentSupplementVotes {
  private final Fraction remainder;

  @SuppressWarnings("hiding")
  public AssignmentSupplementWithRemainderFraction(long votes, Fraction remainder) {
    super(votes);
    this.remainder = remainder;
  }

  public Fraction getRemainder() {
    return remainder;
  }
}
