/*
 * Created on 18.02.2011
 * Copyright (c) 2011 Kiesraad
 */
package de.ivu.wahl.wus.utils;

public class EuropeanNetherlandsUtil {
  /**
   * @param netherlandsCountryCode "NL"
   * @return <code>true</code> if the country code belongs to the Netherlands (this includes
   *         <code>null</code>) and the city is not a NonEuropeanDutchCity (i.e. on the BES islands)
   */
  public static boolean isInEuropeanNetherlands(String city,
      String countryCode,
      String netherlandsCountryCode) {
    return (countryCode == null || countryCode.equals(netherlandsCountryCode))
        && !NonEuropeanDutchCitiesUtil.isNonEuropeanDutchCity(city);
  }
}
