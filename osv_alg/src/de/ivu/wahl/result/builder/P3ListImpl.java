/*
 * P3ListImpl
 * 
 * Created on 17.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.builder;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.ivu.wahl.result.CandidateList;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.determination.P2List;
import de.ivu.wahl.result.determination.P3List;
import de.ivu.wahl.result.drawlots.DrawingLotsIdentifier;

/**
 * Immutable implementation of the P3List interface
 */
public class P3ListImpl implements P3List {
  private final Object externalKey;
  private final String name;
  private final List<CandidateList> candidateLists;

  public P3ListImpl(Object externalKey, String name, List<CandidateList> candidateLists) {
    this.externalKey = externalKey;
    this.name = name;
    this.candidateLists = Util.createUnmodifiableCopy(candidateLists);
  }

  public Collection<CandidateList> getCandidateLists() {
    return candidateLists;
  }

  public Set<P2List> getP2Lists() {
    Set<P2List> p2Lists = new HashSet<P2List>();
    for (CandidateList candidateList : candidateLists) {
      p2Lists.add(candidateList.getP2List());
    }
    return p2Lists;
  }

  public List<P2List> getP2ListsSorted() {
    List<P2List> result = Util.asList(getP2Lists());
    Collections.sort(result, P2List.SORT_BY_ELECTORAL_DISTRICT_NUMBER);
    return result;
  }

  /**
   * @return Name of the P3-list, typically the name of the political grouping.
   * @see de.ivu.wahl.result.NamedObject#getName()
   */
  public String getName() {
    if (name.length() == 0) {
      if (candidateLists == null || candidateLists.isEmpty()) {
        return "Empty blanko P3-List";
      } else {
        return candidateLists.iterator().next().getListNumberOrName();
      }
    } else {
      return name;
    }
  }

  @Override
  public String getListNumberWithName() {
    return getCandidateLists().iterator().next().getListNumberWithName();
  }

  public int getNumber() {
    return getCandidateLists().iterator().next().getListNumber();
  }

  public int compareTo(P3List other) {
    return Integer.valueOf(this.getNumber()).compareTo(Integer.valueOf(other.getNumber()));
  }

  public Object getExternalKey() {
    return this.externalKey;
  }

  public DrawingLotsIdentifier getIdentifier() {
    return new DrawingLotsIdentifier(this);
  }

  @Override
  public String toString() {
    return getName();
  }

  public String getKey() {
    return String.valueOf(getNumber());
  }

}
