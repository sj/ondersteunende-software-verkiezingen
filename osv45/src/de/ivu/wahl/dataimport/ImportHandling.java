/*
 * ImportHandling
 * 
 * Copyright (c) 2004-8 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import java.net.URL;
import java.util.Collection;

import javax.ejb.EJBException;

import de.ivu.ejb.bmp.Model;
import de.ivu.wahl.modell.exception.ImportException;

/**
 * Interface des SessionBeans f�r die Unterst�tzung des ImportClient
 * 
 * @author cos@ivu.de
 */

public interface ImportHandling {
  /**
   * Save model objects
   * 
   * @param detailsCol
   * @throws ImportException
   */
  void createEntities(Collection<Model> detailsCol) throws ImportException;

  /**
   * Initialize administration values
   * 
   * @param id_Wahl
   * @throws ImportException
   */
  void createAdministrationValues(String id_Wahl) throws ImportException;

  /**
   * Set names for blanco lists to first candidate's name
   * 
   * @throws ImportException
   */
  void updateGruppennamen() throws ImportException;

  /**
   * Creates polling stations
   * 
   * @param anzahl number of polling stations to be created
   * @throws EJBException
   * @throws ImportException
   */
  void createStimmbezirke(int anzahl) throws ImportException;

  /**
   * Create polling stations from EML 110 xml file
   * 
   * @param anzahl number of polling stations to be created
   * @throws EJBException
   */
  void createStimmbezirke(URL url110) throws ImportException;

}
