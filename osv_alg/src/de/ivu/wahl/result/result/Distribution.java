/*
 * Distribution - characterization of list level where a distribution takes place
 * 
 * Created on 12.02.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.result;

/**
 * Enum for the different types of seat distributions.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public enum Distribution {
  P42(1), P3(2), P2(3), FICTITIOUS(4);

  int id;

  @SuppressWarnings("hiding")
  Distribution(final int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public static Distribution byId(int id) {
    for (Distribution distribution : Distribution.values()) {
      if (id == distribution.getId()) {
        return distribution;
      }
    }
    return null;
  }
}
