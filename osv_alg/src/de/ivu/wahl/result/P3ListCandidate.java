/*
 * P3ListCandidate
 * 
 * Created on 19.10.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result;

import de.ivu.wahl.result.determination.P3List;
import de.ivu.wahl.result.drawlots.DrawingLotsAlternative;
import de.ivu.wahl.result.drawlots.DrawingLotsIdentifier;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;

/**
 * Pair of a Candidate and a P3List. This pair is used for drawing lots.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class P3ListCandidate implements DrawingLotsAlternative {
  private final Candidate candidate;
  private final P3List p3List;

  public P3ListCandidate(P3List p3List, Candidate candidate) {
    this.p3List = p3List;
    if (p3List == null) {
      throw new IllegalArgumentException(Messages.bind(MessageKeys.Builder_Assert_MustNotBeNull,
          "P3List")); //$NON-NLS-1$
    }
    this.candidate = candidate;
    if (candidate == null) {
      throw new IllegalArgumentException(Messages.bind(MessageKeys.Builder_Assert_MustNotBeNull,
          "Candidate")); //$NON-NLS-1$
    }
  }

  public Candidate getCandidate() {
    return candidate;
  }

  public P3List getP3List() {
    return p3List;
  }

  public DrawingLotsIdentifier getIdentifier() {
    return new DrawingLotsIdentifier(this);
  }

  public String getName() {
    return Messages.applyPattern("{0}, lijst {1} ({2})",
        candidate.getName(),
        p3List.getNumber(),
        p3List.getName());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + candidate.hashCode();
    result = prime * result + p3List.hashCode();
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
    P3ListCandidate other = (P3ListCandidate) obj;
    if (!candidate.equals(other.candidate)) {
      return false;
    }
    if (!p3List.equals(other.p3List)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "P3ListCandidate(" + getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
  }

}
