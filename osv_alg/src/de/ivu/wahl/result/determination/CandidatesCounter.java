/*
 * CandidatesCounter
 * 
 * Created on 19.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.determination;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.CandidateList;
import de.ivu.wahl.result.ElectionAndCandidates;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;

/**
 * The CandidatesCounter keeps track of the number of candidates of a list, that may receive a seat.
 * "List" stand for either of candidate list, P3-list or P42-list.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
class CandidatesCounter {
  private final Map<CandidateList, Long> _candidatesPerList;
  private final Map<P2List, Long> _candidatesPerP2List;
  private final Map<P3List, Long> _candidatesPerP3List;
  private final Map<P42List, Long> _candidatesPerP42List;

  /**
   * Constructor for a CandidatesCounter for the normal seat distribution
   * 
   * @param electionAndCandidates
   * @param p42Lists
   */
  public CandidatesCounter(ElectionAndCandidates electionAndCandidates,
      final Collection<P42List> p42Lists) {
    Map<CandidateList, Long> candidatesPerList = new HashMap<CandidateList, Long>();
    Map<P2List, Long> candidatesPerP2List = new HashMap<P2List, Long>();
    Map<P3List, Long> candidatesPerP3List = new HashMap<P3List, Long>();
    Map<P42List, Long> candidatesPerP42List = new HashMap<P42List, Long>();

    for (P42List p42List : p42Lists) {
      Set<Candidate> p42Candidates = new HashSet<Candidate>();
      for (P3List p3List : p42List.getP3Lists()) {
        Set<Candidate> p3Candidates = new HashSet<Candidate>();
        for (P2List p2List : p3List.getP2Lists()) {
          Set<Candidate> p2Candidates = new HashSet<Candidate>();
          for (CandidateList candidateList : p2List.getCandidateLists()) {
            Set<Candidate> candidates = new HashSet<Candidate>(candidateList.getCandidates());
            candidates.removeAll(electionAndCandidates.getDeceasedCandidates());

            p2Candidates.addAll(candidates);
            p3Candidates.addAll(candidates);
            p42Candidates.addAll(candidates);
            candidatesPerList.put(candidateList, Long.valueOf(candidates.size()));
          }
          candidatesPerP2List.put(p2List, Long.valueOf(p2Candidates.size()));
        }
        candidatesPerP3List.put(p3List, Long.valueOf(p3Candidates.size()));
      }
      candidatesPerP42List.put(p42List, Long.valueOf(p42Candidates.size()));
    }

    this._candidatesPerList = Util.createUnmodifiableCopy(candidatesPerList);
    this._candidatesPerP2List = Util.createUnmodifiableCopy(candidatesPerP2List);
    this._candidatesPerP3List = Util.createUnmodifiableCopy(candidatesPerP3List);
    this._candidatesPerP42List = Util.createUnmodifiableCopy(candidatesPerP42List);
  }

  /**
   * Constructor for a CandidatesCounter for the fictitious seat distribution. It contains no data
   * that concern P42-lists.
   * 
   * @param electionAndCandidates
   * @param p3Lists
   */
  public CandidatesCounter(Collection<P3List> p3Lists,
      final ElectionAndCandidates electionAndCandidates) {
    Map<CandidateList, Long> candidatesPerList = new HashMap<CandidateList, Long>();
    Map<P2List, Long> candidatesPerP2List = new HashMap<P2List, Long>();
    Map<P3List, Long> candidatesPerP3List = new HashMap<P3List, Long>();

    for (P3List p3List : p3Lists) {
      Set<Candidate> p3Candidates = new HashSet<Candidate>();
      for (P2List p2List : p3List.getP2Lists()) {
        Set<Candidate> p2Candidates = new HashSet<Candidate>();
        for (CandidateList candidateList : p2List.getCandidateLists()) {
          Set<Candidate> candidates = new HashSet<Candidate>(candidateList.getCandidates());
          candidates.removeAll(electionAndCandidates.getDeceasedCandidates());

          p2Candidates.addAll(candidates);
          p3Candidates.addAll(candidates);
          candidatesPerList.put(candidateList, Long.valueOf(candidates.size()));
        }
        candidatesPerP2List.put(p2List, Long.valueOf(p2Candidates.size()));
      }
      candidatesPerP3List.put(p3List, Long.valueOf(p3Candidates.size()));
    }

    this._candidatesPerList = Util.createUnmodifiableCopy(candidatesPerList);
    this._candidatesPerP2List = Util.createUnmodifiableCopy(candidatesPerP2List);
    this._candidatesPerP3List = Util.createUnmodifiableCopy(candidatesPerP3List);
    this._candidatesPerP42List = Util.createUnmodifiableCopy(new HashMap<P42List, Long>());
  }

  public long getNumberOfP42ListCandidates(P42List p42List) {
    Long result = _candidatesPerP42List.get(p42List);
    assert result != null : Messages
        .bind(MessageKeys.Builder_Assert_NoNumberOfCandidatesStoredForP42List_0, p42List);
    return result.longValue();
  }

  public long getNumberOfListedCandidates(CandidateList candidateList) {
    Long result = _candidatesPerList.get(candidateList);
    assert result != null : Messages
        .bind(MessageKeys.Builder_Assert_NoNumberOfCandidatesStoredFoCandidateList_0, candidateList);
    return result.longValue();
  }

  public long getNumberOfP3ListCandidates(P3List p3List) {
    Long result = _candidatesPerP3List.get(p3List);
    assert result != null : Messages
        .bind(MessageKeys.Builder_Assert_NoNumberOfCandidatesStoredForP3List_0, p3List);
    return result.longValue();
  }

  /**
   * @return an unmodifiable Map of P2 lists (key) and their respective number of candidates (value)
   */
  public Map<P2List, Long> getCandidatesPerP2List() {
    return _candidatesPerP2List;
  }

  /**
   * @return an unmodifiable Map of P3 lists (key) and their respective number of candidates (value)
   */
  public Map<P3List, Long> getCandidatesPerP3List() {
    return _candidatesPerP3List;
  }

  /**
   * @return an unmodifiable Map of P42 lists (key) and their respective number of candidates
   *         (value)
   */
  public Map<P42List, Long> getCandidatesPerP42List() {
    return _candidatesPerP42List;
  }

}
