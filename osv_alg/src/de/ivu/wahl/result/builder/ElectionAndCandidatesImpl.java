/*
 * ElectionAndCandidatesImpl
 * 
 * Created on 17.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.builder;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.CandidateList;
import de.ivu.wahl.result.Election;
import de.ivu.wahl.result.ElectionAndCandidates;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.determination.P2List;
import de.ivu.wahl.result.determination.P3List;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;

class ElectionAndCandidatesImpl extends ElectionImpl implements ElectionAndCandidates {
  private final Map<Object, Candidate> candidates;
  private final Map<Object, CandidateList> candidateLists;
  private final Map<Object, P2List> p2Lists;
  private final Map<Object, P3List> p3Lists;
  private final List<CombinedList> combinedLists;
  private final Map<CandidateList, P3List> candidateListToP3List;
  private final Set<Candidate> _deceasedCandidates;

  @SuppressWarnings("hiding")
  ElectionAndCandidatesImpl(Election election,
      Map<Object, Candidate> candidates,
      Map<Object, CandidateList> candidateLists,
      Map<Object, P2List> p2Lists,
      Map<Object, P3List> p3Lists,
      List<CombinedList> combinedLists,
      Set<Candidate> deceasedCandidates) {
    super(election.getNumberOfSeats(), election.getElectoralDistrictsByExternalKeys(), election
        .getPreferencialBarrierNumerator(), election.getPreferencialBarrierDenominator(), election
        .getElectionSubcategory());
    this.candidates = Util.createUnmodifiableCopy(candidates);
    this.candidateLists = Util.createUnmodifiableCopy(candidateLists);
    this.p2Lists = Util.createUnmodifiableCopy(p2Lists);
    this.p3Lists = Util.createUnmodifiableCopy(p3Lists);
    this.combinedLists = Util.createUnmodifiableCopy(combinedLists);
    this._deceasedCandidates = Util.createUnmodifiableCopy(deceasedCandidates);

    Map<CandidateList, P3List> candidateListToP3List = new HashMap<CandidateList, P3List>();
    for (P3List p3List : p3Lists.values()) {
      for (CandidateList candidateList : p3List.getCandidateLists()) {
        assert !candidateListToP3List.containsKey(candidateList) : Messages
            .bind(MessageKeys.Builder_Assert_TheCandidateList_0_BelongsToMoreThanOneP3list,
                candidateList.getListNumberOrName());
        candidateListToP3List.put(candidateList, p3List);
      }
    }
    this.candidateListToP3List = Util.createUnmodifiableCopy(candidateListToP3List);
    assert candidateListToP3List.size() == candidateLists.size() : Messages
        .getString(MessageKeys.Builder_Assert_NotAllCandidateListsBelongToAP3list);
  }

  public Candidate getCandidateByExternalKey(Object externalKey) {
    return candidates.get(externalKey);
  }

  public CandidateList getListByExternalKey(Object externalKey) {
    return candidateLists.get(externalKey);
  }

  public Collection<P3List> getP3Lists() {
    return Collections.unmodifiableCollection(p3Lists.values());
  }

  public List<CombinedList> getCombinedLists() {
    return combinedLists;
  }

  public CombinedList getCombinedList(Object externalKey) {
    for (CombinedList combinedList : combinedLists) {
      if (combinedList.getExternalKey().equals(externalKey)) {
        return combinedList;
      }
    }
    return null;
  }

  public Collection<CandidateList> getAllCandidateLists() {
    return candidateLists.values();
  }

  public Map<Object, Candidate> getCandidatesMap() {
    return candidates;
  }

  public Map<Object, CandidateList> getCandidateListsMap() {
    return candidateLists;
  }

  public Map<Object, P2List> getP2ListsMap() {
    return p2Lists;
  }

  public Map<Object, P3List> getP3ListsMap() {
    return p3Lists;
  }

  public Map<CandidateList, P3List> getCandidateListToP3List() {
    return candidateListToP3List;
  }

  public CandidateList getListByElectoralDistrictAndParty(String electoralDistrictName,
      String partyName) {
    for (CandidateList candidateList : candidateLists.values()) {
      if (candidateList.getElectoralDistrict().getName().equals(electoralDistrictName)
          && candidateList.getPoliticalGroupingName().equals(partyName)) {
        return candidateList;
      }
    }
    return null;
  }

  public Set<Candidate> getDeceasedCandidates() {
    return _deceasedCandidates;
  }

}
