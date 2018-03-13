/*
 * P42List
 * 
 * Created on 18.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.determination;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.ivu.wahl.result.NumberedObject;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.builder.CombinedList;
import de.ivu.wahl.result.drawlots.DrawingLotsAlternative;
import de.ivu.wahl.result.drawlots.DrawingLotsIdentifier;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;

public class P42List
    implements
      NumberedObject,
      Comparable<P42List>,
      GeneralList,
      DrawingLotsAlternative {
  private final Set<P3List> p3Lists;
  private final CombinedList combinedList;

  /**
   * Constructor
   * 
   * @param p3Lists must not be null and or empty
   */
  public P42List(final Collection<P3List> p3Lists, final CombinedList combinedList) {
    assert p3Lists != null : Messages.bind(MessageKeys.Builder_Assert_MustNotBeNull, "p3Lists"); //$NON-NLS-1$
    assert !p3Lists.isEmpty() : Messages.bind(MessageKeys.Builder_Assert_MustNotBeEmpty, "p3Lists"); //$NON-NLS-1$
    assert p3Lists.size() == 1 || combinedList.getExternalKey() != null : Messages
        .bind(MessageKeys.Builder_Assert_MustNotBeEmpty, "p42List"); //$NON-NLS-1$
    this.p3Lists = Util.createUnmodifiableCopy(new HashSet<P3List>(p3Lists));
    this.combinedList = combinedList;
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
      b.append(p3List.getListNumberWithName());
    }
    return Messages.bind(MessageKeys.Result_Tracelog_CombinedList_0_1,
        getExternalKey(),
        b.toString());
  }

  /**
   * Sort by the smallest of the contained P3 lists.
   * 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(final P42List other) {
    return getSmallestP3List().compareTo(other.getSmallestP3List());
  }

  private P3List getSmallestP3List() {
    List<P3List> myLists = Util.asSortList(getP3Lists());
    return myLists.get(0);
  }

  public int getNumber() {
    return Util.sortByNumber(getP3Lists()).get(0).getNumber();
  }

  public Object getExternalKey() {
    return combinedList == null ? null : combinedList.getExternalKey();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(getClass().getSimpleName());
    builder.append("("); //$NON-NLS-1$
    if (p3Lists.size() == 1) {
      builder.append(p3Lists.iterator().next().getNumber());
    } else {
      builder.append(getExternalKey());
    }
    builder.append(")"); //$NON-NLS-1$

    return builder.toString();
  }

  public String getKey() {
    if (p3Lists.size() == 1) {
      return String.valueOf(p3Lists.iterator().next().getNumber());
    } else {
      return getExternalKey().toString();
    }
  }

  public CombinedList getCombinedList() {
    return combinedList;
  }

  public DrawingLotsIdentifier getIdentifier() {
    return new DrawingLotsIdentifier(this);
  }
}