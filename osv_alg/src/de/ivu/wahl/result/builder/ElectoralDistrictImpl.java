/*
 * ElectoralDistrictImpl
 * 
 * Created on 16.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.builder;

import de.ivu.wahl.result.ElectoralDistrict;

final class ElectoralDistrictImpl implements ElectoralDistrict {
  private final String name;
  private final int number;
  private final long voteValue;

  @SuppressWarnings("hiding")
  public ElectoralDistrictImpl(String name, int number, long voteValue) {
    this.name = name;
    this.number = number;
    this.voteValue = voteValue;
    assert voteValue >= 1;
  }

  public String getName() {
    return name;
  }

  public int getNumber() {
    return number;
  }

  public int compareTo(ElectoralDistrict other) {
    return Integer.valueOf(this.getNumber()).compareTo(Integer.valueOf(other.getNumber()));
  }

  public long getVoteValue() {
    return voteValue;
  }
}
