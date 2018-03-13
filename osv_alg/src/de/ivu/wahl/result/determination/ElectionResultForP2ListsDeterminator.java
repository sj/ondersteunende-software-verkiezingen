/*
 * ElectionResultForP3ListsDeterminator
 * 
 * Created on 01.01.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.determination;

import de.ivu.wahl.result.result.Distribution;
import de.ivu.wahl.result.result.ElectionResult;

/**
 * Determines the number of seats for the P2 lists belonging to a given P3 list.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class ElectionResultForP2ListsDeterminator
    extends
      AbstractElectionResultDeterminator<P2List, P3List> {

  @Override
  protected void assignmentStarted(P3List parent,
      long totalVotes,
      long totalSeats,
      ElectionResult electionResult) {
    electionResult.subDistributionStarted(parent, totalVotes, totalSeats, Distribution.P2);
  }

  /**
   * @return <code>true</code> if p3List is a list group, i.e. has more than one P2List.
   *         <code>false</code> otherwise.
   */
  @Override
  protected boolean needsCosalgMode(P3List p3List) {
    return p3List.getP2Lists().size() > 1;
  }

}
