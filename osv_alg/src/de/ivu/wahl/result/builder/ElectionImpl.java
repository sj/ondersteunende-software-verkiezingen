/*
 * ElectionImpl
 * 
 * Created on 16.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.builder;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.ivu.wahl.result.Election;
import de.ivu.wahl.result.ElectoralDistrict;
import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;

class ElectionImpl extends Object implements Election {
  private final long numberOfSeats;
  private final Map<Object, ElectoralDistrict> electoralDistricts;
  private final long preferencialBarrierNumerator;
  private final long preferencialBarrierDenominator;
  private final ElectionSubcategory electionSubcategory;

  @SuppressWarnings("hiding")
  ElectionImpl(long numberOfSeats,
      Map<Object, ElectoralDistrict> electoralDistricts,
      long preferencialBarrierNumerator,
      long preferencialBarrierDenominator,
      ElectionSubcategory electionSubcategory) {
    this.numberOfSeats = numberOfSeats;
    this.electoralDistricts = Collections.unmodifiableMap(new HashMap<Object, ElectoralDistrict>(
        electoralDistricts));
    this.preferencialBarrierNumerator = preferencialBarrierNumerator;
    this.preferencialBarrierDenominator = preferencialBarrierDenominator;
    this.electionSubcategory = electionSubcategory;
  }

  public long getNumberOfSeats() {
    return numberOfSeats;
  }

  public long getPreferencialBarrierNumerator() {
    return preferencialBarrierNumerator;
  }

  public long getPreferencialBarrierDenominator() {
    return preferencialBarrierDenominator;
  }

  public Collection<ElectoralDistrict> getElectoralDistricts() {
    return electoralDistricts.values();
  }

  public Map<Object, ElectoralDistrict> getElectoralDistrictsByExternalKeys() {
    return electoralDistricts;
  }

  public ElectionSubcategory getElectionSubcategory() {
    return electionSubcategory;
  }

}
