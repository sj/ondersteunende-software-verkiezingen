/*
 * VotesBuilder
 * 
 * Created on 16.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.CandidateList;
import de.ivu.wahl.result.ElectionAndCandidates;
import de.ivu.wahl.result.ElectionAndCandidatesAndVotes;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;

public class VotesBuilder {
  private final ElectionAndCandidates electionAndCandidates;
  private final Map<CandidateList, long[]> votesCast = new HashMap<CandidateList, long[]>();
  private final Map<CandidateList, long[]> votes = new HashMap<CandidateList, long[]>();

  @SuppressWarnings("hiding")
  public VotesBuilder(ElectionAndCandidates electionAndCandidates) {
    this.electionAndCandidates = electionAndCandidates;
  }

  public void setVotesCast(CandidateList candidateList, long... noOfVotes) {
    assert noOfVotes != null : Messages.bind(MessageKeys.Builder_Assert_MustNotBeNull, "noOfVotes"); //$NON-NLS-1$
    assert candidateList != null : Messages.bind(MessageKeys.Builder_Assert_MustNotBeNull,
        "candidateList"); //$NON-NLS-1$
    List<Candidate> candidates = candidateList.getCandidates();
    assert candidates != null : Messages.bind(MessageKeys.Builder_Assert_MustNotBeNull,
        "candidateList.getCandidates()"); //$NON-NLS-1$
    assert candidates.size() == noOfVotes.length : Messages
        .bind(MessageKeys.Builder_Assert_ThereAre_0_CandidatesButVotesFor_1_Candidates, candidates
            .size(), noOfVotes.length);
    int length = noOfVotes.length;
    assert electionAndCandidates.getAllCandidateLists().contains(candidateList) : Messages
        .getString(MessageKeys.Builder_Assert_VotesCanOnlyBeSetForRegisteredCandidateLists);

    for (int i = 0; i < length; i++) {
      assert noOfVotes[i] >= 0 : Messages
          .getString(MessageKeys.Builder_Assert_NumberOfVotesMustNotBeNegative);
    }
    assert !votes.containsKey(candidateList) : Messages
        .bind(MessageKeys.Builder_Assert_CandidateList_0_AlreadyHasVotes, candidateList.getName());
    long[] myVotesCast = new long[noOfVotes.length];
    System.arraycopy(noOfVotes, 0, myVotesCast, 0, length);
    votesCast.put(candidateList, myVotesCast);

    long voteValue = candidateList.getElectoralDistrict().getVoteValue();
    long[] myVotes = new long[noOfVotes.length];
    for (int i = 0; i < myVotes.length; i++) {
      myVotes[i] = noOfVotes[i] * voteValue;
    }
    votes.put(candidateList, myVotes);
  }

  public ElectionAndCandidatesAndVotes getElectionAndCandidatesAndVotes() {
    return new ElectionAndCandidatesAndVotesImpl(electionAndCandidates, votes);
  }
}
