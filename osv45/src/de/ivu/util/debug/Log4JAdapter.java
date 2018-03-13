/*
 * Log4JAdapter
 * 
 * Created on 08.04.2003
 * Copyright (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.util.debug;

import java.net.URL;

import org.apache.log4j.Category;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public interface Log4JAdapter {
  /**
   * Creates a logger using the given name as category and configures the Log4J system.<BR>
   * This implementation doesn't configure Log4J if any configuration was allready done. This is a
   * workaround, to prevent multiple instance of the same appender. In future we will change this
   * behavior and supporting differential configuration.
   * 
   * @param category name of category
   * @param logConfig URL pointing to the log4j properties
   * @return a category / logger or null if any error occurs
   */
  public Category configure(String category, URL logConfig) throws Exception;

}
