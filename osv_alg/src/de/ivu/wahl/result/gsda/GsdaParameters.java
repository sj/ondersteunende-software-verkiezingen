/*
 * GsdaParameters
 * 
 * Created on 03.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.gsda;

import de.ivu.wahl.result.Fraction;

/**
 * Parameters for the general seat distribution algorithm.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class GsdaParameters {
  private static final Fraction _75PERCENT = new Fraction(3L, 4L);
  private static final Fraction _25PERCENT = new Fraction(1L, 4L);
  private static final Fraction _ZERO = new Fraction(0L, 1L);

  private final boolean b1_fictitious;
  private final boolean b2_minimumForResidualSeat;
  private final Fraction b3_minimumForLargestRemainder;
  private final boolean b4_includeLargestRemainder;
  private final boolean b5_oneSeatByLargestAverage;
  private final boolean b6_withAbsoluteMajorityRegulation;
  private final boolean b7_assignmentInListGroup;

  public static GsdaParameters forFictitiousDistribution() {
    return new GsdaParameters(true, false, _ZERO, false, false, true, false);
  }

  public static GsdaParameters forFictitiousDistributionEpTk() {
    return new GsdaParameters(true, true, _ZERO, false, false, true, false);
  }

  /**
   * For GR1, ER, AB1, GC
   */
  public static GsdaParameters forFictitiousDistributionGR1() {
    return new GsdaParameters(true, false, _75PERCENT, true, true, true, false);
  }

  public static GsdaParameters forFictitiousDistributionBC() {
    return new GsdaParameters(true, false, _25PERCENT, true, true, true, false);
  }

  public static GsdaParameters forP42DistributionEpTk() {
    return new GsdaParameters(false, true, _ZERO, false, false, true, false);
  }

  public static GsdaParameters forP42DistributionEK() {
    return new GsdaParameters(false, false, _ZERO, false, false, false, false);
  }

  /**
   * For PS1, PS2, AB2, GR2
   */
  public static GsdaParameters forP42DistributionPsAb2Gr2() {
    return new GsdaParameters(false, false, _ZERO, false, false, true, false);
  }

  /**
   * For GR1, ER, AB1, GC
   */
  public static GsdaParameters forP42DistributionGr1() {
    return new GsdaParameters(false, false, _75PERCENT, true, true, true, false);
  }

  public static GsdaParameters forP42DistributionBC() {
    return new GsdaParameters(false, false, _25PERCENT, true, true, true, false);
  }

  /**
   * For all elections but EK, TK, PS2
   */
  public static GsdaParameters forP2P3Distribution() {
    return new GsdaParameters(false, false, _ZERO, true, false, false, false);
  }

  /**
   * For EK, TK, PS2
   */
  public static GsdaParameters forP2DistributionTkPs2() {
    return new GsdaParameters(false, false, _ZERO, true, false, false, true);
  }

  private GsdaParameters(boolean b1_fictitious,
      boolean b2_minimumForResidualSeat,
      Fraction b3_minimumForLargestRemainder,
      boolean b4_includeLargestRemainder,
      boolean b5_oneSeatByLargestAverage,
      boolean b6_withAbsoluteMajorityRegulation,
      boolean b7_assignmentInListGroup) {
    this.b1_fictitious = b1_fictitious;
    this.b2_minimumForResidualSeat = b2_minimumForResidualSeat;
    this.b3_minimumForLargestRemainder = b3_minimumForLargestRemainder;
    this.b4_includeLargestRemainder = b4_includeLargestRemainder;
    this.b5_oneSeatByLargestAverage = b5_oneSeatByLargestAverage;
    this.b6_withAbsoluteMajorityRegulation = b6_withAbsoluteMajorityRegulation;
    this.b7_assignmentInListGroup = b7_assignmentInListGroup;
  }

  public boolean isB1_fictitious() {
    return b1_fictitious;
  }

  public boolean isB2_minimumForResidualSeat() {
    return b2_minimumForResidualSeat;
  }

  public Fraction getB3_minimumForLargestRemainder() {
    return b3_minimumForLargestRemainder;
  }

  public boolean isB4_includeLargestRemainder() {
    return b4_includeLargestRemainder;
  }

  public boolean isB5_oneSeatByLargestAverage() {
    return b5_oneSeatByLargestAverage;
  }

  public boolean isB6_withAbsoluteMajorityRegulation() {
    return b6_withAbsoluteMajorityRegulation;
  }

  public boolean isB7_assignmentInListGroup() {
    return b7_assignmentInListGroup;
  }
}
