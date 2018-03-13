/*
 * AnomalyType
 * 
 * Created on 12.10.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.result;

public enum AnomalyType {
  IN_MAIN_DISTRIBUTION(1), IN_P3_DISTRIBUTION(2), IN_P2_DISTRIBUTION(3), IN_NOMINATION_OF_ELECTED_CANDIDATES(
      4);

  private int _id;

  private AnomalyType(final int id) {
    this._id = id;
  }

  public int getId() {
    return _id;
  }

}
