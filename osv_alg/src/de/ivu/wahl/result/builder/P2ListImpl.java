/*
 * P2ListImpl
 * 
 * Created on 18.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.CandidateList;
import de.ivu.wahl.result.determination.P2List;
import de.ivu.wahl.result.drawlots.DrawingLotsIdentifier;

/**
 * Default implementation of the P2List interface. _candidateLists may be changed (by adding
 * candidate lists) during construction.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class P2ListImpl implements P2List {
  public static final String P2LIST_KEY_SEPARATOR = "_"; //$NON-NLS-1$
  private final Set<CandidateList> _candidateLists;
  private final Object _externalKey;

  public P2ListImpl(final Object externalKey) {
    this._candidateLists = new HashSet<CandidateList>();
    this._externalKey = externalKey;
  }

  public Set<CandidateList> getCandidateLists() {
    return Collections.unmodifiableSet(_candidateLists);
  }

  public List<Candidate> getCandidates() {
    return _candidateLists.isEmpty() ? new ArrayList<Candidate>() : _candidateLists.iterator()
        .next().getCandidates();
  }

  public Object getExternalKey() {
    return _externalKey;
  }

  void addCandidateList(final CandidateList candidateList) {
    _candidateLists.add(candidateList);
  }

  public String getName() {
    if (_candidateLists.isEmpty()) {
      return "Empty P2-list"; //$NON-NLS-1$
    }
    CandidateList anyList = _candidateLists.iterator().next();
    StringBuilder builder = new StringBuilder();
    builder.append(anyList.getListNumberOrName());
    builder.append(" ("); //$NON-NLS-1$
    boolean first = true;
    for (CandidateList candidateList : _candidateLists) {
      if (first) {
        first = false;
      } else {
        builder.append(", "); //$NON-NLS-1$
      }
      builder.append(candidateList.getElectoralDistrict().getName());
    }
    builder.append(")"); //$NON-NLS-1$

    return builder.toString();
  }

  public int compareTo(P2List other) {
    boolean xEmpty = this.getCandidateLists().isEmpty();
    boolean yEmpty = other.getCandidateLists().isEmpty();
    if (xEmpty && yEmpty) {
      return 0;
    } else if (!xEmpty && yEmpty) {
      return 1;
    } else if (xEmpty && !yEmpty) {
      return -1;
    }
    return this.getCandidateLists().iterator().next()
        .compareTo(other.getCandidateLists().iterator().next());
  }

  @Override
  public String toString() {
    return getName();
  }

  public String getKey() {
    int min = 999;
    for (CandidateList candidateList : _candidateLists) {
      min = Math.min(min, candidateList.getElectoralDistrict().getNumber());
    }

    String listNumber = String.valueOf(getCandidateLists().iterator().next().getListNumber());
    String districtNumber = String.valueOf(min);
    return listNumber + P2LIST_KEY_SEPARATOR + districtNumber;
  }

  public int getElectoralDistrictNumber() {
    int min = 999;
    for (CandidateList candidateList : _candidateLists) {
      min = Math.min(min, candidateList.getElectoralDistrict().getNumber());
    }
    return min;
  }

  public DrawingLotsIdentifier getIdentifier() {
    return new DrawingLotsIdentifier(this);
  }

  @Override
  public String getElectoralDistrictNumbers() {
    SortedSet<Integer> set = new TreeSet<Integer>();
    for (CandidateList candidateList : _candidateLists) {
      set.add(candidateList.getElectoralDistrict().getNumber());
    }
    Integer[] array = set.toArray(new Integer[0]);
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < array.length; i++) {
      Integer integer = array[i];
      b.append(integer);
      if (i < array.length - 2) {
        b.append(", ");
      } else if (i == array.length - 2) {
        b.append(" en ");
      }
    }
    return b.toString();
  }
}