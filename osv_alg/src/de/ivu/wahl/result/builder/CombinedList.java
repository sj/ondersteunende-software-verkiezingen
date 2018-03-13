/*
 * CombinedList
 * 
 * Created on 10.02.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.builder;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.determination.P3List;
import de.ivu.wahl.result.drawlots.DrawingLotsAlternative;
import de.ivu.wahl.result.drawlots.DrawingLotsIdentifier;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;

/**
 * A combined list as submitted (i.e. independent of the election result).
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class CombinedList implements DrawingLotsAlternative {
  private final Object externalKey;
  private final Set<P3List> p3Lists;

  @SuppressWarnings("hiding")
  public CombinedList(Object externalKey, Collection<P3List> p3Lists) {
    this.externalKey = externalKey;
    if (p3Lists != null) {
      this.p3Lists = Collections.unmodifiableSet(Util.asSet(p3Lists));
    } else {
      this.p3Lists = null;
    }
  }

  public Object getExternalKey() {
    return externalKey;
  }

  public Set<P3List> getP3Lists() {
    return p3Lists;
  }

  public String getName() {
    if (p3Lists.size() == 1) {
      // For a trivial P42-list, the name is the same as the name of the contained P3-list
      return p3Lists.iterator().next().getName();
    }
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (P3List p3List : p3Lists) {
      if (first) {
        first = false;
      } else {
        b.append(", "); //$NON-NLS-1$
      }
      b.append(p3List.getName());
    }
    return Messages.bind(MessageKeys.Result_Tracelog_CombinedList_0_1, externalKey, b.toString());
  }

  public DrawingLotsIdentifier getIdentifier() {
    return new DrawingLotsIdentifier(this);
  }

}
