/*
 * CandidateImpl
 * 
 * Created on 16.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.builder;

import de.ivu.wahl.result.Candidate;

/**
 * Immutable implementation of the Candidate interface.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
final class CandidateImpl implements Candidate {
  private final String candidateName;
  private final Object externalKey;

  @SuppressWarnings("hiding")
  public CandidateImpl(String candidateName, Object externalKey) {
    this.candidateName = candidateName;
    this.externalKey = externalKey;
  }

  public String getName() {
    return candidateName;
  }

  public int compareTo(Candidate other) {
    return this.getName().compareTo(other.getName());
  }

  @Override
  public String toString() {
    return "Candidate(\"" + candidateName + "\")"; //$NON-NLS-1$ //$NON-NLS-2$ 
  }

  public Object getExternalKey() {
    return externalKey;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((externalKey == null) ? 0 : externalKey.hashCode());
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
    CandidateImpl other = (CandidateImpl) obj;
    if (externalKey == null) {
      if (other.externalKey != null) {
        return false;
      }
    } else if (!externalKey.equals(other.externalKey)) {
      return false;
    }
    return true;
  }

}
