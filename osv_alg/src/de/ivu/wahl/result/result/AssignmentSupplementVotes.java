/*
 * AssignmentSupplementVotes
 * 
 * Created on 24.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.result;

/**
 * Supplementary information containing the number of votes of the list that receive the assignment.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class AssignmentSupplementVotes implements AssignmentSupplement {
  private final int votes;

  @SuppressWarnings("hiding")
  public AssignmentSupplementVotes(long votes) {
    this.votes = (int) votes;
  }

  public int getVotes() {
    return votes;
  }

}
