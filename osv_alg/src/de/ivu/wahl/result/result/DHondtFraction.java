package de.ivu.wahl.result.result;

public class DHondtFraction {
  private final long numerator;
  private final long denominator;
  private final boolean receivesSeat;
  private final int fractionsIndex;
  private final int columnIndex;

  public DHondtFraction(long numerator,
      long denominator,
      boolean receivesSeat,
      int fractionsIndex,
      int columnIndex) {
    this.numerator = numerator;
    this.denominator = denominator;
    this.receivesSeat = receivesSeat;
    this.fractionsIndex = fractionsIndex;
    this.columnIndex = columnIndex;
  }

  public long getNumerator() {
    return numerator;
  }

  public long getDenominator() {
    return denominator;
  }

  public boolean receivesSeat() {
    return receivesSeat;
  }

  /**
   * @return Index of the set of fractions
   */
  public int getFractionsIndex() {
    return fractionsIndex;
  }

  /**
   * @return the index of the column for publication. Each column contains the assignment to only a
   *         single list
   */
  public int getColumnIndex() {
    return columnIndex;
  }

}