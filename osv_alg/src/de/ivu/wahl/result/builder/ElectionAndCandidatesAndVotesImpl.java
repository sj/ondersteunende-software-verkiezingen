/*
 * ElectionAndCandidatesAndVotesImpl
 * 
 * Created on 18.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.builder;

import java.util.Map;

import de.ivu.wahl.result.CandidateList;
import de.ivu.wahl.result.ElectionAndCandidates;
import de.ivu.wahl.result.ElectionAndCandidatesAndVotes;
import de.ivu.wahl.result.Util;

/**
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class ElectionAndCandidatesAndVotesImpl extends ElectionAndCandidatesImpl
    implements
      ElectionAndCandidatesAndVotes {
  private final Map<CandidateList, long[]> votes;

  @SuppressWarnings("hiding")
  public ElectionAndCandidatesAndVotesImpl(ElectionAndCandidates eac,
      Map<CandidateList, long[]> votes) {
    super(eac, eac.getCandidatesMap(), eac.getCandidateListsMap(), eac.getP2ListsMap(), eac
        .getP3ListsMap(), eac.getCombinedLists(), eac.getDeceasedCandidates());
    this.votes = Util.createUnmodifiableCopy(votes);
  }

  public Map<CandidateList, long[]> getVotes() {
    return votes;
  }

}
