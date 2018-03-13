/*
 * ExportDirService
 * 
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.mbean.exportdir;

/**
 * Management of the shadow export directory
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public interface ExportDirService {
  /**
   * MBean-Lifecycle-Methode zum Erzeugen des Dienstes
   * 
   * @throws Exception bei einem Problem
   */
  public void create() throws Exception;

  /**
   * MBean-Lifecycle-Methode zum Entfernen des Dienstes
   * 
   * @throws Exception bei einem Problem
   */
  public void destroy() throws Exception;

  /**
   * MBean-Lifecycle-Methode zum Starten des Dienstes
   * 
   * @throws Exception bei einem Problem
   */
  public void start() throws Exception;

  /**
   * MBean-Lifecycle-Methode zum Stoppen des Dienstes
   * 
   * @throws Exception bei einem Problem
   */
  public void stop() throws Exception;
}