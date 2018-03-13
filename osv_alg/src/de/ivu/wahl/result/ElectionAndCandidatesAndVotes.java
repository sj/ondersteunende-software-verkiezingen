/*
 * ElectionAndCandidates
 * 
 * Created on 15.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result;

import java.util.Map;

/**
 * Extends the {@link ElectionAndCandidates} interface by information about the numbers of votes the
 * candidates received.
 * <p>
 * Immutable.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface ElectionAndCandidatesAndVotes extends ElectionAndCandidates {

  /**
   * @return an unmodifiyable Map that contains for each {@link CandidateList} an array with the
   *         number of votes for the respective candidates in the order they appear on that list.
   */
  public Map<CandidateList, long[]> getVotes();

}
