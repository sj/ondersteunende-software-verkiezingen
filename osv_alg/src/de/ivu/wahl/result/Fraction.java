/*
 * Fraction
 * 
 * Created on 04.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result;

import java.math.BigInteger;

import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;

/**
 * Fraction of two long values.
 * <p>
 * Caution, do not reuse. Designed for osv_alg only.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class Fraction extends Number implements Comparable<Fraction> {
  private static final long serialVersionUID = 1L;
  private static final long MAX_INT = Integer.MAX_VALUE;
  private static final long MIN_INT = Integer.MIN_VALUE;
  private static final BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
  private static final BigInteger MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);

  private final long _numerator;
  private final long _denominator;

  public Fraction(long numerator, long denominator) {
    if (denominator == 0) {
      throw new ArithmeticException("Division by zero"); //$NON-NLS-1$
    }
    _numerator = numerator;
    _denominator = denominator;
  }

  public long getNumerator() {
    return _numerator;
  }

  public long getDenominator() {
    return _denominator;
  }

  // Comparing

  public int compareTo(Fraction other) {
    checkOverflow(this._numerator, other._denominator);
    checkOverflow(other._numerator, this._denominator);
    long diff = this._numerator * other._denominator - other._numerator * this._denominator;
    return Long.signum(diff) * Long.signum(this._denominator) * Long.signum(other._denominator);
  }

  public int compareTo(long aLong) {
    checkOverflow(aLong, this._denominator);
    long diff = this._numerator - aLong * this._denominator;
    return Long.signum(diff) * Long.signum(this._denominator);
  }

  /**
   * @throw AssertionError if multiplying x and y would result in a value &gt; Long.MAX_LONG or &lt;
   *        Long.MIN_LONG.
   */
  private void checkOverflow(long x, long y) {
    if (MIN_INT <= x && x <= MAX_INT && MIN_INT <= y && y <= MAX_INT) {
      return;
    }
    BigInteger product = BigInteger.valueOf(x).multiply(BigInteger.valueOf(y));
    assert MIN_LONG.compareTo(product) <= 0 && product.compareTo(MAX_LONG) <= 0 : Messages
        .bind(MessageKeys.Result_Assert_OverflowAtMultiplication_0_1, x, y);
  }

  // Conversion

  @Override
  public double doubleValue() {
    return (double) _numerator / (double) _denominator;
  }

  @Override
  public float floatValue() {
    return (float) _numerator / (float) _denominator;
  }

  @Override
  public int intValue() {
    return (int) longValue();
  }

  @Override
  public long longValue() {
    return _numerator / _denominator;
  }

  // Arithmetics

  public Fraction times(Fraction other) {
    checkOverflow(this._numerator, other._numerator);
    checkOverflow(this._denominator, other._denominator);
    return new Fraction(this._numerator * other._numerator, this._denominator * other._denominator);
  }

  public Fraction times(long aLong) {
    checkOverflow(this._numerator, aLong);
    return new Fraction(this._numerator * aLong, this._denominator);
  }

  public Fraction dividedBy(Fraction other) {
    checkOverflow(this._numerator, other._denominator);
    checkOverflow(this._denominator, other._numerator);
    return new Fraction(this._numerator * other._denominator, this._denominator * other._numerator);
  }

  public Fraction dividedBy(long aLong) {
    checkOverflow(this._denominator, aLong);
    return new Fraction(this._numerator, this._denominator * aLong);
  }

}
