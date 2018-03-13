/*
 * VotesCounter
 * 
 * Created on 18.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.determination;

import static de.ivu.wahl.result.determination.ElectionResultDeterminator.APPLOG;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.CandidateList;
import de.ivu.wahl.result.ElectionAndCandidatesAndVotes;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;

/**
 * A VotesCounter calculates in its constructor the total number of votes and the number of votes
 * for each
 * <ul>
 * <li>candidate</li>
 * <li>candidate list</li>
 * <li>P3-list</li>
 * </ul>
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
class VotesCounter {
  private final Map<CandidateList, Long> votesPerList;
  private final Map<P2List, Long> votesPerP2List;
  private final Map<P3List, Long> votesPerP3List;
  private final Map<Candidate, Long> votesPerCandidate;
  private final Map<P2List, Map<Candidate, Long>> votesPerCandidateInP2List;
  private final Map<P3List, Map<Candidate, Long>> votesPerCandidateInP3List;
  private final long totalNumberOfVotes;

  VotesCounter(ElectionAndCandidatesAndVotes ecv) {
    Map<CandidateList, Long> tempVotesPerList = new HashMap<CandidateList, Long>();
    Map<P2List, Long> tempVotesPerP2List = new HashMap<P2List, Long>();
    Map<P3List, Long> tempVotesPerP3List = new HashMap<P3List, Long>();
    Map<Candidate, Long> tempVotesPerCandidate = new HashMap<Candidate, Long>();
    Map<P2List, Map<Candidate, Long>> tempVotesPerCandidateInP2List = new HashMap<P2List, Map<Candidate, Long>>();
    Map<P3List, Map<Candidate, Long>> tempVotesPerCandidateInP3List = new HashMap<P3List, Map<Candidate, Long>>();

    Map<CandidateList, P3List> candidateListToP3List = new HashMap<CandidateList, P3List>();
    Collection<P3List> p3Lists = ecv.getP3Lists();
    for (P3List p3List : p3Lists) {
      for (CandidateList candidateList : p3List.getCandidateLists()) {
        assert !candidateListToP3List.containsKey(candidateList) : Messages
            .bind(MessageKeys.Builder_Assert_CandidateList_0_BelongsToMoreThanOneP3List,
                candidateList.getListNumberOrName());
        candidateListToP3List.put(candidateList, p3List);
      }
    }

    long tempTotalNumberOfVotes = 0;
    Map<CandidateList, long[]> allVotes = ecv.getVotes();
    List<CandidateList> candidateLists = Util.asSortList(allVotes.keySet());
    for (CandidateList candidateList : candidateLists) {
      P2List p2List = candidateList.getP2List();
      P3List p3List = ecv.getCandidateListToP3List().get(candidateList);

      Map<Candidate, Long> innerVotesPerCandidateInP2List = tempVotesPerCandidateInP2List
          .get(p2List);
      if (innerVotesPerCandidateInP2List == null) {
        innerVotesPerCandidateInP2List = new HashMap<Candidate, Long>();
        tempVotesPerCandidateInP2List.put(p2List, innerVotesPerCandidateInP2List);
      }

      Map<Candidate, Long> innerVotesPerCandidateInP3List = tempVotesPerCandidateInP3List
          .get(p3List);
      if (innerVotesPerCandidateInP3List == null) {
        innerVotesPerCandidateInP3List = new HashMap<Candidate, Long>();
        tempVotesPerCandidateInP3List.put(p3List, innerVotesPerCandidateInP3List);
      }

      assert p3List != null : Messages
          .bind(MessageKeys.Builder_Assert_CandidateList_0_DoesNotBelongToAP3List,
              candidateList.getName());
      List<Candidate> candidates = candidateList.getCandidates();
      long[] votes = allVotes.get(candidateList);
      for (int i = 0; i < votes.length; i++) {
        long newVotes = votes[i];
        Candidate candidate = candidates.get(i);
        APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_VotesForListCandidate,
            candidateList.getName(),
            candidate.getName(),
            newVotes));

        addVotes(tempVotesPerList, candidateList, newVotes);
        addVotes(tempVotesPerP2List, p2List, newVotes);
        addVotes(tempVotesPerP3List, p3List, newVotes);
        addVotes(tempVotesPerCandidate, candidate, newVotes);
        addVotes(innerVotesPerCandidateInP2List, candidate, newVotes);
        addVotes(innerVotesPerCandidateInP3List, candidate, newVotes);
        tempTotalNumberOfVotes += newVotes;
      }
    }

    this.votesPerList = Collections.unmodifiableMap(tempVotesPerList);
    this.votesPerP2List = Collections.unmodifiableMap(tempVotesPerP2List);
    this.votesPerP3List = Collections.unmodifiableMap(tempVotesPerP3List);
    this.votesPerCandidate = Collections.unmodifiableMap(tempVotesPerCandidate);
    this.votesPerCandidateInP2List = Util
        .createUnmodifiableNestedMap(tempVotesPerCandidateInP2List);
    this.votesPerCandidateInP3List = Util
        .createUnmodifiableNestedMap(tempVotesPerCandidateInP3List);
    this.totalNumberOfVotes = tempTotalNumberOfVotes;
  }

  private static <T> void addVotes(Map<T, Long> map, T key, long newVotes) {
    Long oldVotes = map.get(key);
    if (oldVotes != null) {
      map.put(key, Long.valueOf(oldVotes + newVotes));
    } else {
      map.put(key, Long.valueOf(newVotes));
    }
  }

  public Map<CandidateList, Long> getVotesPerList() {
    return votesPerList;
  }

  public Map<P2List, Long> getVotesPerP2List() {
    return votesPerP2List;
  }

  public Map<P3List, Long> getVotesPerP3List() {
    return votesPerP3List;
  }

  public Map<Candidate, Long> getVotesPerCandidate() {
    return votesPerCandidate;
  }

  public Map<Candidate, Long> getVotesPerCandidateInP2List(P2List p2List) {
    return votesPerCandidateInP2List.get(p2List);
  }

  public Map<Candidate, Long> getVotesPerCandidateInP3List(P3List p3List) {
    return votesPerCandidateInP3List.get(p3List);
  }

  public long getTotalNumberOfVotes() {
    return totalNumberOfVotes;
  }

  public void logVotes() {
    APPLOG.info("*** " + Messages.bind(MessageKeys.Result_Tracelog_TotalNumberOfVotes) //$NON-NLS-1$
        + totalNumberOfVotes);
    // A P3-list must have the same number in all electoral districts. This is assured by program 3.
    for (P3List p3List : Util.sortByNumber(votesPerP3List.keySet())) {
      APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_VotesForP3List,
          votesPerP3List.get(p3List),
          p3List.getNumber(),
          p3List.getName()));
    }
    for (CandidateList candidateList : new TreeSet<CandidateList>(votesPerList.keySet())) {
      APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_VotesForCandidateList,
          votesPerList.get(candidateList),
          candidateList.getName()));
    }
    for (Candidate candidate : new TreeSet<Candidate>(votesPerCandidate.keySet())) {
      APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_VotesForCandidate,
          votesPerCandidate.get(candidate),
          candidate.getName()));
    }

  }
}
