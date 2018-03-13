/*
 * DBInitService
 * 
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.mbean.dbinit;

/**
 * Database Initialisation
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public interface DBInitService {
  /**
   * MBean-Lifecycle-Methode zum Starten des Dienstes
   * 
   * @throws Exception bei einem Problem
   */
  public void start() throws Exception;
}