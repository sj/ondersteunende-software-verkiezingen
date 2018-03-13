/*
 * MultiReportConfiguration
 * 
 * Created on 13.10.2010
 * Copyright (c) 2010 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A MultiReportConfiguration defines how multiple documents of the same template shall be generated
 * with the same EML file
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class MultiReportConfiguration {
  private final Map<String, String> namesAndParameters;

  public MultiReportConfiguration(Map<String, String> namesAndParameters) {
    Map<String, String> map = new HashMap<String, String>();
    map.putAll(namesAndParameters);
    this.namesAndParameters = Collections.unmodifiableMap(map);
  }

  public Map<String, String> getNamesAndParameters1() {
    return namesAndParameters;
  }

}
