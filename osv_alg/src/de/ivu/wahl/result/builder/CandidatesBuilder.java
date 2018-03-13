/*
 * CandidatesBuilder
 * 
 * Created on 16.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.builder;

import java.util.ArrayList;
import java.util.Arrays;
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
import de.ivu.wahl.result.ElectoralDistrict;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.determination.P2List;
import de.ivu.wahl.result.determination.P3List;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;

/**
 * With this builder the lists, groups of lists, sets of identical lists including their respective
 * _candidates can be created.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class CandidatesBuilder {
  private final Election _election;
  private final Map<Object, Candidate> _candidates = new HashMap<Object, Candidate>();
  private final Map<Object, CandidateList> _candidateLists = new HashMap<Object, CandidateList>();
  private final Map<Object, P2ListImpl> _p2Lists = new HashMap<Object, P2ListImpl>();
  private final Map<Object, P3List> _p3Lists = new HashMap<Object, P3List>();
  private final List<CombinedList> _combinedLists = new ArrayList<CombinedList>();

  private List<Candidate> _pendingCandidateList; // collects the candidates for the P2-list

  private List<CandidateList> _pendingP3List; // collects the ???

  // contains the electoral district, where the pending P2-list belongs
  private Collection<ElectoralDistrict> _pendingP3ElectoralDistricts;

  // Political grouping name of the pending P3-list
  private String _pendingPoliticalGroupingName;

  // List number of the pending P3-list
  private Integer _pendingListNumber;

  private Object _pendingExternalP2ListKey;
  private Object _pendingExternalP3ListKey;
  private Set<Candidate> _deceasedCandidates = Collections.emptySet();

  public CandidatesBuilder(final Election election) {
    this._election = election;
  }

  /**
   * This method is only used to create test data !!!
   */
  public P3List createDummyP3ListInAllElectoralDistricts(final int numberOfCandidates,
      String namePrefix, String politicalGroupingName, int listNumber) {
    Collection<ElectoralDistrict> electoralDistricts = _election.getElectoralDistricts();
    startIdenticalP3List(electoralDistricts,
        politicalGroupingName,
        listNumber,
        politicalGroupingName);
    for (int i = 1; i <= numberOfCandidates; i++) {
      String candidateName = namePrefix + i;
      addCandidateToPendingList(candidateName);
    }
    P3List result = finishIdenticalP3List();

    return result;
  }

  // ****************** Creation of Candidates ********************** //

  /**
   * CAUTION: Use this method only if you are sure that the candidateName is unique.
   */
  public Candidate addCandidateToPendingList(final String candidateName) {
    return addCandidateToPendingList(candidateName, candidateName);
  }

  public Candidate addCandidateToPendingList(final String candidateName, final Object externalKey) {
    Candidate result = getOrCreateCandidate(candidateName, externalKey);
    _pendingCandidateList.add(result);
    return result;
  }

  /**
   * @return If for the <code>externalKey</code> a Candidate has already been created, return that
   *         Candidate. Otherwise return <code>null</code>.
   */
  public Candidate getCandidate(final Object externalKey) {
    return _candidates.get(externalKey);
  }

  /**
   * @param candidateName
   * @param externalKey
   * @return the created Candidate
   * @throws AssertionError if <code>getCandidate(externalKey) != null</code>
   */
  private Candidate createCandidate(final String candidateName, final Object externalKey) {
    Candidate candidate = new CandidateImpl(candidateName, externalKey);
    assert !_candidates.containsKey(externalKey) : Messages
        .getString(MessageKeys.Builder_Assert_AnotherCandidateRegisteredForKey_0) + externalKey;
    _candidates.put(externalKey, candidate);
    return candidate;
  }

  /**
   * @return If for the <code>externalKey</code> a Candidate has already been created, return that
   *         Candidate. Otherwise create a new Candidate, register it under the given externalKey
   *         and return the new Candidate.
   */
  private Candidate getOrCreateCandidate(final String candidateName, final Object externalKey) {
    Candidate result = _candidates.get(externalKey);
    if (result == null) {
      result = createCandidate(candidateName, externalKey);
    }
    return result;
  }

  // ****************** Creation of P3-Lists ******************** //

  /**
   * Starts a new P3-List that is a single Set of identical Lists. After creating the _candidates,
   * finishIdenticalP3List() has to be called.
   * <p>
   * Use for testing only. politicalGroupingName is also the external key of the P3List.
   * 
   * @param externalP2ListKey
   */
  public void startIdenticalP3List(Collection<ElectoralDistrict> electoralDistricts,
      String politicalGroupingName, int listNumber, Object externalP2ListKey) {
    assertNoPendingCandidateList();
    assert _pendingPoliticalGroupingName == null : Messages
        .bind(MessageKeys.Builder_Assert_MustBeNull, "_pendingPoliticalGroupingName"); //$NON-NLS-1$
    assert !_p3Lists.containsKey(politicalGroupingName) : Messages
        .bind(MessageKeys.Builder_Assert_P3ListWithName_0_AlreadyRegistered, politicalGroupingName);

    _pendingP3ElectoralDistricts = Util.asList(electoralDistricts);
    _pendingCandidateList = new ArrayList<Candidate>();
    _pendingP3List = new ArrayList<CandidateList>();
    _pendingPoliticalGroupingName = politicalGroupingName;
    _pendingListNumber = Integer.valueOf(listNumber);
    _pendingExternalP2ListKey = externalP2ListKey;
    _pendingExternalP3ListKey = politicalGroupingName;
  }

  public P3List finishIdenticalP3List() {
    assert _pendingExternalP2ListKey != null : Messages
        .bind(MessageKeys.Builder_Assert_MustNotBeNull, "pendingExternalP2ListKey"); //$NON-NLS-1$
    assert _pendingListNumber != null : Messages.bind(MessageKeys.Builder_Assert_MustNotBeNull,
        "_pendingListNumber"); //$NON-NLS-1$
    assert _pendingPoliticalGroupingName != null : Messages
        .bind(MessageKeys.Builder_Assert_MustNotBeNull, "_pendingPoliticalGroupingName"); //$NON-NLS-1$
    assert _pendingExternalP3ListKey != null : Messages
        .bind(MessageKeys.Builder_Assert_MustNotBeNull, "_pendingExternalP3ListKey"); //$NON-NLS-1$

    for (ElectoralDistrict electoralDistrict : _pendingP3ElectoralDistricts) {
      createCandidateList(electoralDistrict,
          _pendingPoliticalGroupingName,
          _pendingCandidateList,
          _pendingListNumber.intValue(),
          _pendingExternalP2ListKey);
    }
    P3List result = new P3ListImpl(_pendingExternalP3ListKey, _pendingPoliticalGroupingName,
        _pendingP3List);
    P3List previous = _p3Lists.put(_pendingExternalP3ListKey, result);
    assert previous == null : Messages.bind(MessageKeys.Builder_Assert_P3ListAlreadyRegistered,
        _pendingExternalP3ListKey);

    resetPendingCandidateList();
    _pendingPoliticalGroupingName = null;
    _pendingP3List = null;

    return result;
  }

  /**
   * Starts a new P3-List that is a single Set of identical Lists. After creating the _candidates,
   * finishIdenticalP3List() has to be called.
   * 
   * @param politicalGroupingName may be empty
   */
  public void startP3List(Object externalKey, String politicalGroupingName) {
    assert _pendingPoliticalGroupingName == null : Messages
        .bind(MessageKeys.Builder_Assert_MustBeNull, "_pendingPoliticalGroupingName"); //$NON-NLS-1$
    assert !_p3Lists.containsKey(externalKey) : Messages
        .bind(MessageKeys.Builder_Assert_P3ListWithName_0_AlreadyRegistered, externalKey);
    assertNoPendingCandidateList();

    _pendingP3List = new ArrayList<CandidateList>();
    _pendingPoliticalGroupingName = politicalGroupingName;
    _pendingExternalP3ListKey = externalKey;
  }

  public P3List finishP3List() {
    assert _pendingListNumber == null : Messages.bind(MessageKeys.Builder_Assert_MustBeNull,
        "_pendingListNumber"); //$NON-NLS-1$
    assert _pendingCandidateList == null : Messages.bind(MessageKeys.Builder_Assert_MustBeNull,
        "_pendingCandidateList"); //$NON-NLS-1$
    assert _pendingP3ElectoralDistricts == null : Messages
        .bind(MessageKeys.Builder_Assert_MustBeNull, "_pendingP3ElectoralDistricts"); //$NON-NLS-1$
    assert _pendingPoliticalGroupingName != null : Messages
        .bind(MessageKeys.Builder_Assert_MustNotBeNull, "_pendingPoliticalGroupingName"); //$NON-NLS-1$
    assert _pendingExternalP3ListKey != null : Messages
        .bind(MessageKeys.Builder_Assert_MustNotBeNull, "_pendingExternalP3ListKey"); //$NON-NLS-1$

    P3List result = new P3ListImpl(_pendingExternalP3ListKey, _pendingPoliticalGroupingName,
        _pendingP3List);
    P3List previous = _p3Lists.put(_pendingExternalP3ListKey, result);
    assert previous == null : Messages.bind(MessageKeys.Builder_Assert_P3ListAlreadyRegistered,
        _pendingPoliticalGroupingName);

    _pendingPoliticalGroupingName = null;
    _pendingP3List = null;

    return result;
  }

  // ****************** Creation of P2-Lists ******************** //

  /**
   * Use this method after calling <code>startP3List()</code>. Then add _candidates by calling
   * <code>addCandidateToPendingList()</code>. Finally call <code>finishCandidateList()</code>.
   * 
   * @param externalP2ListKey This identifies the P2-list (e.g. sets of identical lists).
   */
  public void startCandidateList(Object electoralDistrictKey, int listNumber,
      final Object externalP2ListKey) {
    startCandidateList(_election.getElectoralDistrictsByExternalKeys().get(electoralDistrictKey),
        listNumber,
        externalP2ListKey);
  }

  /**
   * Use this method after calling <code>startP3List()</code>. Then add _candidates by calling
   * <code>addCandidateToPendingList()</code>. Finally call <code>finishCandidateList()</code>.
   * 
   * @param externalP2ListKey This identifies the P2-list (e.g. sets of identical lists).
   */
  public void startCandidateList(ElectoralDistrict electoralDistrict, int listNumber,
      Object externalP2ListKey) {
    assertNoPendingCandidateList();

    _pendingP3ElectoralDistricts = new ArrayList<ElectoralDistrict>();
    _pendingP3ElectoralDistricts.add(electoralDistrict);
    _pendingCandidateList = new ArrayList<Candidate>();
    _pendingListNumber = Integer.valueOf(listNumber);
    _pendingExternalP2ListKey = externalP2ListKey;
  }

  public CandidateList finishCandidateList() {
    assertPendingCandidateList();
    assert _pendingP3ElectoralDistricts.size() == 1 : Messages
        .getString(MessageKeys.Builder_Assert_PendingP3ElectoralDistrictsMustHaveSize1);

    ElectoralDistrict electoralDistrict = _pendingP3ElectoralDistricts.iterator().next();
    CandidateList result = createCandidateList(electoralDistrict,
        _pendingPoliticalGroupingName,
        _pendingCandidateList,
        _pendingListNumber.intValue(),
        _pendingExternalP2ListKey);

    resetPendingCandidateList();

    return result;
  }

  public CandidateList createCandidateList(ElectoralDistrict electoralDistrict,
      String politicalGroupingName, List<Candidate> candidates, int listNumber,
      Object externalP2ListKey) {
    assert _pendingP3List != null : Messages
        .getString(MessageKeys.Builder_Assert_CannotCreateCandidateListWithoutP3List);
    assert electoralDistrict != null : Messages.bind(MessageKeys.Builder_Assert_MustNotBeNull,
        "electoralDistrict"); //$NON-NLS-1$
    assert politicalGroupingName != null : Messages.bind(MessageKeys.Builder_Assert_MustNotBeNull,
        "politicalGroupingName"); //$NON-NLS-1$
    assert candidates != null : Messages.bind(MessageKeys.Builder_Assert_MustNotBeNull,
        "_candidates"); //$NON-NLS-1$
    assert !candidates.isEmpty() : Messages.bind(MessageKeys.Builder_Assert_MustNotBeEmpty,
        "_candidates"); //$NON-NLS-1$
    assert listNumber > 0 : Messages.getString(MessageKeys.Builder_Assert_ListNumberMustBePositive);

    P2ListImpl p2List = _p2Lists.get(externalP2ListKey);
    if (p2List == null) {
      p2List = new P2ListImpl(externalP2ListKey);
      _p2Lists.put(externalP2ListKey, p2List);
    }
    CandidateList candidateList = new CandidateListImpl(electoralDistrict, politicalGroupingName,
        candidates, listNumber, p2List);
    p2List.addCandidateList(candidateList);
    _pendingP3List.add(candidateList);
    _candidateLists.put(candidateList, candidateList);

    return candidateList;
  }

  // ********* Combine lists ***************

  @Deprecated
  // Should no longer be used, see OSV-1938
  public void combineLists(final Object externalKey, final P3List... p3lists) {
    combineLists(externalKey, Arrays.asList(p3lists));
  }

  /**
   * Method for testing only. Works only if the externalKeys are Strings.
   */
  @Deprecated
  // Should no longer be used, see OSV-1938
  public void combineLists(final Object externalKey, final List<String> p3listNames) {
    List<P3List> result = new ArrayList<P3List>();
    for (String p3listName : p3listNames) {
      result.add(_p3Lists.get(p3listName));
    }
    combineLists(externalKey, result);
  }

  @Deprecated
  // Should no longer be used, see OSV-1938
  public void combineLists(final Object externalKey, final Collection<P3List> p3lists) {
    CombinedList combinedList = new CombinedList(externalKey, p3lists);
    assert combinedList.getP3Lists().size() > 1 : Messages
        .getString(MessageKeys.Builder_Assert_CombinedListsMustHaveMoreThanOneElement);
    for (P3List p3List : combinedList.getP3Lists()) {
      assert _p3Lists.containsValue(p3List) : Messages
          .getString(MessageKeys.Builder_Assert_P3ListNotRegistered);
      for (CombinedList cl : _combinedLists) {
        Set<P3List> lists = cl.getP3Lists();
        assert !lists.contains(p3List) : Messages
            .getString(MessageKeys.Builder_Assert_P3ListInMultipleCombinedLists);
      }
    }
    _combinedLists.add(combinedList);

  }

  // ********* RESULT ***************

  public ElectionAndCandidates getElectionAndCandidates() {
    Map<Object, P2List> copyOfP2Lists = new HashMap<Object, P2List>();
    for (Object key : _p2Lists.keySet()) {
      copyOfP2Lists.put(key, _p2Lists.get(key));
    }
    return new ElectionAndCandidatesImpl(_election, _candidates, _candidateLists, copyOfP2Lists,
        _p3Lists, _combinedLists, _deceasedCandidates);
  }

  // ********* assertions ***************

  /**
   * Makes sure that no attributes of a pending candidate list are set.
   */
  private void assertNoPendingCandidateList() {
    assert _pendingP3ElectoralDistricts == null : Messages
        .bind(MessageKeys.Builder_Assert_MustBeNull, "_pendingP3ElectoralDistricts"); //$NON-NLS-1$
    assert _pendingCandidateList == null : Messages.bind(MessageKeys.Builder_Assert_MustBeNull,
        "_pendingCandidateList"); //$NON-NLS-1$
    assert _pendingListNumber == null : Messages.bind(MessageKeys.Builder_Assert_MustBeNull,
        "_pendingListNumber"); //$NON-NLS-1$
    assert _pendingExternalP2ListKey == null : Messages.bind(MessageKeys.Builder_Assert_MustBeNull,
        "pendingExternalP2ListKey"); //$NON-NLS-1$
  }

  /**
   * Makes sure that the attributes of a pending candidate list are set.
   */
  private void assertPendingCandidateList() {
    assert _pendingP3ElectoralDistricts != null : Messages
        .bind(MessageKeys.Builder_Assert_MustNotBeEmpty, "_pendingP3ElectoralDistricts"); //$NON-NLS-1$
    assert _pendingCandidateList != null : Messages.bind(MessageKeys.Builder_Assert_MustNotBeEmpty,
        "_pendingCandidateList"); //$NON-NLS-1$
    assert _pendingListNumber != null : Messages.bind(MessageKeys.Builder_Assert_MustNotBeEmpty,
        "_pendingListNumber"); //$NON-NLS-1$
    assert _pendingExternalP2ListKey != null : Messages
        .bind(MessageKeys.Builder_Assert_MustNotBeEmpty, "pendingExternalP2ListKey"); //$NON-NLS-1$
  }

  private void resetPendingCandidateList() {
    _pendingP3ElectoralDistricts = null;
    _pendingCandidateList = null;
    _pendingListNumber = null;
    _pendingExternalP2ListKey = null;
  }

  public void setDeceasedCandidates(final Set<Candidate> deceasedCandidates) {
    _deceasedCandidates = Util.createUnmodifiableCopy(deceasedCandidates);
  }

}
