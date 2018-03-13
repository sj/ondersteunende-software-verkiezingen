/*
 * CandidateListImpl
 * 
 * Created on 16.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.builder;

import java.util.List;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.CandidateList;
import de.ivu.wahl.result.ElectoralDistrict;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.determination.P2List;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;

/**
 * Immutable implementation of the CandidateList interface.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
final class CandidateListImpl implements CandidateList {
  private final ElectoralDistrict electoralDistrict;
  private final String politicalGroupingName;
  private final List<Candidate> candidates;
  private final int listNumber;
  private final P2List p2List;

  public CandidateListImpl(ElectoralDistrict electoralDistrict,
      String politicalGroupingName,
      List<Candidate> candidates,
      int listNumber,
      P2List p2List) {
    this.electoralDistrict = electoralDistrict;
    this.politicalGroupingName = politicalGroupingName;
    this.candidates = Util.createUnmodifiableCopy(candidates);
    this.listNumber = listNumber;
    this.p2List = p2List;
  }

  public ElectoralDistrict getElectoralDistrict() {
    return electoralDistrict;
  }

  public String getPoliticalGroupingName() {
    return politicalGroupingName;
  }

  public List<Candidate> getCandidates() {
    return candidates;
  }

  public int getListNumber() {
    return listNumber;
  }

  @Override
  public String getListNumberOrName() {
    if (politicalGroupingName.length() == 0) {
      return Messages.bind(MessageKeys.Result_Tracelog_List_0, getListNumber());
    } else {
      return politicalGroupingName;
    }
  }

  @Override
  public String getListNumberWithName() {
    if (politicalGroupingName.length() == 0) {
      return Messages.bind(MessageKeys.Result_Tracelog_List_0, getListNumber());
    } else {
      return Messages.bind(MessageKeys.Result_Tracelog_List_0_1,
          getListNumber(),
          politicalGroupingName);
    }
  }

  public P2List getP2List() {
    return p2List;
  }

  public String getName() {
    return Messages.bind(MessageKeys.Result_Tracelog_0_in_1,
        getListNumberOrName(),
        getElectoralDistrict().getName());
  }

  public int compareTo(final CandidateList other) {
    int result = this.getListNumberOrName().compareTo(other.getListNumberOrName());
    if (result != 0) {
      return result;
    }
    return this.getElectoralDistrict().compareTo(other.getElectoralDistrict());
  }

  @Override
  public String toString() {
    return "CandidateListImpl(" + getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}
