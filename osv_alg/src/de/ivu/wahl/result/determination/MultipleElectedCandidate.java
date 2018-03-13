/*
 * MultipleElectedCandidate
 * 
 * Created on 21.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.determination;

import java.util.Collection;
import java.util.List;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.ElectionAndCandidatesAndVotes;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;
import de.ivu.wahl.result.result.CandidateResult;

/**
 * Helper class that implements the part of the algorithm that deals with candidates that are
 * elected on more than one P3-list.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class MultipleElectedCandidate implements Comparable<MultipleElectedCandidate> {
  private final Candidate candidate;
  private final int minElectoralDistrictNumber;
  private final int minListPosition;
  private final int minListNumber;
  private final List<CandidateResult> candidateResults;

  @SuppressWarnings("hiding")
  public MultipleElectedCandidate(Candidate candidate,
      int minElectoralDistrictNumber,
      int minListPosition,
      int minListNumber,
      List<CandidateResult> candidateResults) {
    this.candidate = candidate;
    this.minElectoralDistrictNumber = minElectoralDistrictNumber;
    this.minListPosition = minListPosition;
    this.minListNumber = minListNumber;
    this.candidateResults = candidateResults;
  }

  public Candidate getCandidate() {
    return candidate;
  }

  public int getMinElectoralDistrictNumber() {
    return minElectoralDistrictNumber;
  }

  public int getMinListPosition() {
    return minListPosition;
  }

  public int getMinListNumber() {
    return minListNumber;
  }

  public List<CandidateResult> getCandidateResults() {
    return candidateResults;
  }

  public int compareTo(MultipleElectedCandidate other) {
    if (this.minElectoralDistrictNumber != other.minElectoralDistrictNumber) {
      return this.minElectoralDistrictNumber - other.minElectoralDistrictNumber;
    }
    if (this.minListPosition != other.minListPosition) {
      return this.minListPosition - other.minListPosition;
    }
    return this.minListNumber - other.minListNumber;
  }

  /**
   * @return g_9,i = the P3-list for which the candidate received the most votes. If votes are
   *         equal, return the P3-list with the smaller electoral district number.
   */
  public CandidateResult getP3ListWithMostVotes(VotesCounter votesCounter,
      ElectionAndCandidatesAndVotes ecv) {
    long maxVotes = -1L;
    CandidateResult maxCandidateResult = null;
    for (CandidateResult candidateResult : candidateResults) {
      P2List p2List = candidateResult.getP2List();
      P3List p3List = getP3List(ecv, p2List);
      long votes = votesCounter.getVotesPerCandidateInP3List(p3List)
          .get(candidateResult.getCandidate());
      if (votes > maxVotes
          || votes == maxVotes
          && candidateResult.getP2List().getElectoralDistrictNumber() < maxCandidateResult
              .getP2List().getElectoralDistrictNumber()) {
        maxVotes = votes;
        maxCandidateResult = candidateResult;
      }
    }
    return maxCandidateResult;
  }

  private P3List getP3List(ElectionAndCandidatesAndVotes ecv, P2List p2List) {
    Collection<P3List> p3Lists = ecv.getP3Lists();
    for (P3List p3List : p3Lists) {
      if (p3List.getP2Lists().contains(p2List)) {
        return p3List;
      }
    }
    throw new AssertionError(Messages.bind(MessageKeys.Result_Assert_P2ListMustBePartOfP3List_0,
        p2List.getName()));
  }
}
