/*
 * Util
 * 
 * Created on 18.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Utility class providing static service methods:
 * <ul>
 * <li>creating unmodifiable copies of various collections and maps</li>
 * <li>sorting</li>
 * <li>displaying</li>
 * </ul>
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public final class Util {
  public static <T> Set<T> createUnmodifiableCopy(final Set<T> source) {
    return Collections.unmodifiableSet(new HashSet<T>(source));
  }

  public static <K, V> Map<K, V> createUnmodifiableCopy(final Map<K, V> source) {
    return Collections.unmodifiableMap(new HashMap<K, V>(source));
  }

  public static <T> List<Set<T>> createUnmodifiableDeepCopy(final Collection<Set<T>> source) {
    List<Set<T>> result = new ArrayList<Set<T>>();
    for (Set<T> set : source) {
      result.add(createUnmodifiableCopy(set));
    }
    return Collections.unmodifiableList(result);
  }

  public static <T> List<T> createUnmodifiableCopy(final List<T> source) {
    return Collections.unmodifiableList(asList(source));
  }

  public static <K, V> Map<K, V> createUnmodifiableSubMap(Map<K, V> base, Collection<K> keys) {
    Map<K, V> result = new HashMap<K, V>();
    for (K key : keys) {
      result.put(key, base.get(key));
    }
    return Collections.unmodifiableMap(result);
  }

  public static <K1, K2, V> Map<K1, Map<K2, V>> createUnmodifiableNestedMap(Map<K1, Map<K2, V>> nestedMap) {
    Map<K1, Map<K2, V>> result = new HashMap<K1, Map<K2, V>>();
    for (K1 key : nestedMap.keySet()) {
      result.put(key, Util.createUnmodifiableCopy(nestedMap.get(key)));
    }

    return Collections.unmodifiableMap(result);
  }

  public static String displayNamedObjects(final Collection<? extends NamedObject> namedObjects) {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (NamedObject namedObject : namedObjects) {
      if (first) {
        first = false;
      } else {
        b.append(", "); //$NON-NLS-1$
      }
      b.append(namedObject.getName());
    }
    return b.toString();
  }

  public static String displayQuotient(final long numerator, final long denominator) {
    return displayQuotient(new Fraction(numerator, denominator));
  }

  public static String displayQuotient(Fraction fraction) {
    StringBuilder builder = new StringBuilder();
    builder.append(fraction.getNumerator());
    builder.append(" / "); //$NON-NLS-1$
    builder.append(fraction.getDenominator());

    if (fraction.getNumerator() > fraction.getDenominator()) {
      builder.append(" = "); //$NON-NLS-1$
      builder.append(fraction.getNumerator() / fraction.getDenominator());
      long rest = fraction.getNumerator() % fraction.getDenominator();
      if (rest != 0) {
        builder.append(" "); //$NON-NLS-1$
        builder.append(rest);
        builder.append("/"); //$NON-NLS-1$
        builder.append(fraction.getDenominator());
      }
    }
    return builder.toString();
  }

  public static <T extends NumberedObject> List<T> sortByNumber(final Collection<T> base) {
    List<T> result = asList(base);
    Collections.sort(result, NumberedObject.SORT_BY_NUMBER);
    return result;
  }

  public static <T extends Comparable<T>> List<T> asSortList(final Collection<T> base) {
    List<T> result = new ArrayList<T>(base);
    Collections.sort(result);
    return result;
  }

  public static <T> Set<T> copy(Set<T> toCopy) {
    return new HashSet<T>(toCopy);
  }

  public static <T> Set<T> asSet(Collection<T> toCopy) {
    return new HashSet<T>(toCopy);
  }

  public static <T> List<T> copy(List<T> toCopy) {
    return new ArrayList<T>(toCopy);
  }

  public static <T> List<T> asList(Collection<T> toCopy) {
    return new ArrayList<T>(toCopy);
  }
}
