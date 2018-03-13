/*
 * Created on 18.02.2011
 * Copyright (c) 2011 Kiesraad
 */
package de.ivu.wahl.wus.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import de.ivu.wahl.wus.utils.ui.ReadFileUtil;

/**
 * Helper class to check if a city is in the non-European part of the Netherlands
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class NonEuropeanDutchCitiesUtil {
  private static final Map<String, String> CITIES = ReadFileUtil.readMapFromFile("nonEuropeanCities.txt");

  public static boolean isNonEuropeanDutchCity(String city) {
    return CITIES.containsKey(city);
  }

  /**
   * Unexpected situations also return true, so the method is not quite symmetric
   * 
   * @return <code>true</code> if the agent live in the same public body (i.e. on the same of the
   *         BES islands) where the election takes place
   */
  public static boolean areInTheSamePublicBody(String electionDomain, String agentLivingCity) {
    // in unexpected situations also return true
    if (StringUtils.isEmpty(electionDomain)) {
      return true;
    }
    String electionDomainBESIndex = getBESIndexOf(electionDomain);
    if (StringUtils.isEmpty(electionDomainBESIndex)) {
      return true;
    }

    String livingCityBESIndex = getBESIndexOf(agentLivingCity);
    return StringUtils.equals(electionDomainBESIndex, livingCityBESIndex);
  }

  /**
   * If city is the name of one of the BES islands or of a city on one of the BES islands, return a
   * string that identifies the island. For all other values of city, return <code>null</code>. For
   * example if city = "Bonaire" or city = "Kralendijk", return "1", for city = "Sint Eustatius" or
   * city = "St. Eustatius", return "3".
   * 
   * @return a String identifying the BES island where city lies, or <code>null</code>
   */
  public static String getBESIndexOf(String city) {
    return CITIES.get(city);
  }

}
