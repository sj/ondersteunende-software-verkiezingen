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
 * Determines the number of seats for the P3 lists belonging to a given P42 list.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class ElectionResultForP3ListsDeterminator
    extends
      AbstractElectionResultDeterminator<P3List, P42List> {

  @Override
  protected void assignmentStarted(P42List parent,
      long totalVotes,
      long totalSeats,
      ElectionResult electionResult) {
    electionResult.subDistributionStarted(parent, totalVotes, totalSeats, Distribution.P3);
  }

}
