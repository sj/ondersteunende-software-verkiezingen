/*
 * ElectionAndCandidates
 * 
 * Created on 15.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.ivu.wahl.result.builder.CombinedList;
import de.ivu.wahl.result.determination.P2List;
import de.ivu.wahl.result.determination.P3List;

/**
 * Extends the {@link Election} interface by information about lists and candidates.
 * <p>
 * Immutable.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface ElectionAndCandidates extends Election {
  /**
   * @return the {@link Candidate} with the given externalKey.
   */
  public Candidate getCandidateByExternalKey(Object externalKey);

  /**
   * @return the {@link CandidateList} with the given externalKey.
   */
  public CandidateList getListByExternalKey(Object externalKey);

  public Collection<CandidateList> getAllCandidateLists();

  /**
   * CAUTION: Use for testing only.
   */
  public CandidateList getListByElectoralDistrictAndParty(String electoralDistrictName,
      String partyName);

  public Collection<P3List> getP3Lists();

  /**
   * @return the combined lists as nominated and admitted according to electoral law articles I 10
   *         and I 11, i.e. before determination of valid combined lists according to according to
   *         electoral law article P 4
   */
  public List<CombinedList> getCombinedLists();

  public CombinedList getCombinedList(Object externalKey);

  public Map<Object, Candidate> getCandidatesMap();

  public Map<Object, CandidateList> getCandidateListsMap();

  public Map<Object, P2List> getP2ListsMap();

  /**
   * @return Map of all P3-lists by their external key
   */
  public Map<Object, P3List> getP3ListsMap();

  public Map<CandidateList, P3List> getCandidateListToP3List();

  public Set<Candidate> getDeceasedCandidates();

}
