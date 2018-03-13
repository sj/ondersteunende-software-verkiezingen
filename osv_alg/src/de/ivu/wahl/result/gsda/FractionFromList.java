/*
 * FractionFromList
 * 
 * Created on 28.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.gsda;

import de.ivu.wahl.result.NamedObject;
import de.ivu.wahl.result.Util;

/**
 * FractionFromList is used to compare and order fractions that occur in the assignments of seats
 * according to d'Hondt or Hare-Niemeyer. The fraction is associated to the corresponding list. The
 * compareTo() method reverses the "natural" ordering to get the greatest value first.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class FractionFromList<T extends NamedObject> implements Comparable<FractionFromList<T>> {
  private final T list;
  private final long numerator;
  private final long denominator;

  public FractionFromList(T list, long numerator, long denominator) {
    this.list = list;
    this.numerator = numerator;
    this.denominator = denominator;
  }

  /**
   * Reverse the "natural" ordering to get the greatest values first in a sorted list.
   */
  public int compareTo(FractionFromList<T> other) {
    return Long.signum(other.numerator * this.denominator - this.numerator * other.denominator);
  }

  public T getList() {
    return list;
  }

  @Override
  public String toString() {
    return list.getName() + ": " + Util.displayQuotient(numerator, denominator); //$NON-NLS-1$
  }

  public long getNumerator() {
    return numerator;
  }

  public long getDenominator() {
    return denominator;
  }
}
