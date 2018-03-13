/*
 * DrawingLotsIdentifier
 * 
 * Created on 26.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.drawlots;

import java.util.Set;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.P3ListCandidate;
import de.ivu.wahl.result.builder.CombinedList;
import de.ivu.wahl.result.determination.CandidateForSorting;
import de.ivu.wahl.result.determination.P2List;
import de.ivu.wahl.result.determination.P3List;
import de.ivu.wahl.result.determination.P42List;

/**
 * A DrawingLotsIdentifier is a view on an DrawingLotsAlternative by showing Objects that are
 * identifyable from external (from the persistency layer).
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class DrawingLotsIdentifier {
  private final CombinedList combinedList;
  private final P3List p3List;
  private final P2List p2List;
  private final Candidate candidate;

  public DrawingLotsIdentifier(P42List p42List) {
    this.combinedList = p42List.getCombinedList();
    Set<P3List> p3Lists = p42List.getP3Lists();
    if (p3Lists.size() == 1) {
      this.p3List = p3Lists.iterator().next();
    } else {
      this.p3List = null;
    }
    this.p2List = null;
    this.candidate = null;
  }

  @SuppressWarnings("hiding")
  public DrawingLotsIdentifier(P3List p3List) {
    this.combinedList = null;
    this.p3List = p3List;
    this.p2List = null;
    this.candidate = null;
  }

  @SuppressWarnings("hiding")
  public DrawingLotsIdentifier(P2List p2List) {
    this.combinedList = null;
    this.p3List = null;
    this.p2List = p2List;
    this.candidate = null;
  }

  @SuppressWarnings("hiding")
  public DrawingLotsIdentifier(CombinedList combinedList) {
    this.combinedList = combinedList;
    this.p3List = null;
    this.p2List = null;
    this.candidate = null;
  }

  public DrawingLotsIdentifier(CandidateForSorting candidateForSorting) {
    this.combinedList = null;
    this.p3List = candidateForSorting.getP3List();
    this.p2List = null;
    this.candidate = candidateForSorting.getCandidate();
  }

  public DrawingLotsIdentifier(P3ListCandidate p3ListCandidate) {
    this.combinedList = null;
    this.p3List = p3ListCandidate.getP3List();
    this.p2List = null;
    this.candidate = p3ListCandidate.getCandidate();
  }

  public Object getExternalP2ListKey() {
    return p2List == null ? null : p2List.getExternalKey();
  }

  public Object getExternalP3ListKey() {
    return p3List == null ? null : p3List.getExternalKey();
  }

  public Object getExternalCombinedListKey() {
    return combinedList == null ? null : combinedList.getExternalKey();
  }

  public Object getExternalCandidateKey() {
    return candidate == null ? null : candidate.getExternalKey();
  }

  @Override
  public String toString() {
    String comma = ", "; //$NON-NLS-1$
    return "DrawingLotsIdentifier(" + combinedList + comma + p3List + comma + p2List + comma //$NON-NLS-1$
        + candidate + ")"; //$NON-NLS-1$
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((candidate == null) ? 0 : candidate.hashCode());
    result = prime * result + ((combinedList == null) ? 0 : combinedList.hashCode());
    result = prime * result + ((p2List == null) ? 0 : p2List.hashCode());
    result = prime * result + ((p3List == null) ? 0 : p3List.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    DrawingLotsIdentifier other = (DrawingLotsIdentifier) obj;
    if (candidate == null) {
      if (other.candidate != null) {
        return false;
      }
    } else if (!candidate.equals(other.candidate)) {
      return false;
    }
    if (combinedList == null) {
      if (other.combinedList != null) {
        return false;
      }
    } else if (!combinedList.equals(other.combinedList)) {
      return false;
    }
    if (p2List == null) {
      if (other.p2List != null) {
        return false;
      }
    } else if (!p2List.equals(other.p2List)) {
      return false;
    }
    if (p3List == null) {
      if (other.p3List != null) {
        return false;
      }
    } else if (!p3List.equals(other.p3List)) {
      return false;
    }
    return true;
  }

  public String getId() {
    String emptyString = ""; //$NON-NLS-1$
    if (combinedList != null) {
      return emptyString + getExternalCombinedListKey();
    } else if (candidate != null) {
      return emptyString + candidate.getName();
    } else if (p3List != null) {
      return emptyString + p3List.getKey();
    } else if (p2List != null) {
      return emptyString + p2List.getKey();
    } else {
      return emptyString;
    }
  }
}
