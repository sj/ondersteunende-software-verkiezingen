/*
 * ElectionBuilder
 * 
 * Created on 15.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ivu.wahl.result.Election;
import de.ivu.wahl.result.ElectoralDistrict;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;
import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;

/**
 * ElectionBuilder is used to build an Election object.
 */
public class ElectionBuilder {
  public static final String DISTRICT_PREFIX = "DISTRICT_"; //$NON-NLS-1$
  private static final String DISTRICT_SUFFIX = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //$NON-NLS-1$

  private final Map<Object, ElectoralDistrict> electoralDistricts = new HashMap<Object, ElectoralDistrict>();
  private final long numberOfSeats;
  private final long preferencialBarrierNumerator;
  private final long preferencialBarrierDenominator;
  private final ElectionSubcategory electionSubcategory;

  @SuppressWarnings("hiding")
  public ElectionBuilder(long numberOfSeats,
      long preferencialBarrierNumerator,
      long preferencialBarrierDenominator,
      ElectionSubcategory electionSubcategory) {
    assert numberOfSeats > 0 : Messages.bind(MessageKeys.Builder_Assert_0_MustBeGreaterZero,
        "numberOfSeats"); //$NON-NLS-1$
    assert preferencialBarrierNumerator > 0 : Messages
        .bind(MessageKeys.Builder_Assert_0_MustBeGreaterZero, "preferencialBarrierNumerator"); //$NON-NLS-1$
    assert preferencialBarrierDenominator > 0 : Messages
        .bind(MessageKeys.Builder_Assert_0_MustBeGreaterZero, "preferencialBarrierDenominator"); //$NON-NLS-1$

    this.numberOfSeats = numberOfSeats;
    this.preferencialBarrierNumerator = preferencialBarrierNumerator;
    this.preferencialBarrierDenominator = preferencialBarrierDenominator;
    this.electionSubcategory = electionSubcategory;
  }

  /**
   * Creates default ElectoralDistricts. Their names start with DISTRICT_ and end with the capital
   * letters of the alphabet.
   * 
   * @param numberOfElectoralDistricts must be &le; 26 and &gt; 0
   * @return an unmodifiable List of ElectoralDistricts
   */
  public List<ElectoralDistrict> createElectoralDistricts(int numberOfElectoralDistricts) {
    assert numberOfElectoralDistricts > 0 : Messages
        .bind(MessageKeys.Builder_Assert_0_MustBeGreaterZero, "numberOfElectoralDistricts"); //$NON-NLS-1$
    assert numberOfElectoralDistricts <= 26 : "numberOfElectoralDistricts > must be <= 26"; //$NON-NLS-1$

    List<ElectoralDistrict> result = new ArrayList<ElectoralDistrict>(numberOfElectoralDistricts);
    for (int i = 0; i < numberOfElectoralDistricts; i++) {
      String name = DISTRICT_PREFIX + DISTRICT_SUFFIX.charAt(i);
      ElectoralDistrict district = createElectoralDistrict(name, i + 1, name, 1L);
      result.add(district);
    }

    return Collections.unmodifiableList(result);
  }

  /**
   * Creates a new ElectoralDistrict and registers it under the given externalKey.
   * 
   * @param name The name of the new ElectoralDistrict
   * @param externalKey any external object that may identify the new ElectoralDistrict
   * @param voteValue
   * @return the new ElectoralDistrict
   */
  public ElectoralDistrict createElectoralDistrict(String name,
      int number,
      Object externalKey,
      long voteValue) {
    assert !electoralDistricts.containsKey(externalKey) : Messages
        .bind(MessageKeys.Builder_Assert_AnotherElectoralDistrictHasBeenCreatedForTheKey_0,
            externalKey);

    ElectoralDistrict district = new ElectoralDistrictImpl(name, number, voteValue);
    electoralDistricts.put(externalKey, district);
    return district;
  }

  /**
   * Calling this method terminates the build process and creates an {@link Election} object that
   * contains all the information collected by the builder. This {@link Election} object shall be
   * used to create a {@link CandidatesBuilder} and proceed with it.
   * 
   * @return the result of the build process
   */
  public Election getElection() {
    return new ElectionImpl(numberOfSeats, electoralDistricts, preferencialBarrierNumerator,
        preferencialBarrierDenominator, electionSubcategory);
  }
}
