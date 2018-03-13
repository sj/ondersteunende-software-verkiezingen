/*
 * Election
 * 
 * Created on 16.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result;

import java.util.Collection;
import java.util.Map;

import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;

/**
 * Basic information about the election including the electoral districts.
 * <p>
 * Immutable.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface Election {

  /**
   * @return The number of seats to be assigned in the election
   */
  public long getNumberOfSeats();

  /**
   * getPreferencialBarrierNumerator() and getPreferencialBarrierDenominator() determine the
   * fraction of preferential barrier and electoral quota. This fraction is 1/1 for the EK
   * election,1/10 for the EP election, 1/2 for community councils with less than 19 seats and 1/4
   * for all other election types.
   */
  public long getPreferencialBarrierNumerator();

  /**
   * getPreferencialBarrierNumerator() and getPreferencialBarrierDenominator() determine the
   * fraction of preferential barrier and electoral quota. This fraction is 1/1 for the EK election,
   * 1/10 for the EP election, 1/2 for community councils with less than 19 seats and 1/4 for all
   * other election types.
   */
  public long getPreferencialBarrierDenominator();

  /**
   * @return Unmodifiable collection of the ElectoralDistricts
   */
  public Collection<ElectoralDistrict> getElectoralDistricts();

  /**
   * @return Unmodifieable Map of the ElectoralDistricts with their external keys.
   */
  public Map<Object, ElectoralDistrict> getElectoralDistrictsByExternalKeys();

  /**
   * @return the type of election
   */
  public ElectionSubcategory getElectionSubcategory();
}