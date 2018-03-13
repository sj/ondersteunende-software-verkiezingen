/*
 * Created on 24.11.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb.service;

import javax.ejb.FinderException;

import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.Liste;

/**
 * Provides Services with information about votes and elected candidates
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface SeatsHandling {

  int getTotalSeatsForP2List(String id_Ergebniseingang, Liste liste, boolean inConflict)
      throws FinderException;

  int getTotalSeatsForP3List(String id_Ergebniseingang, Gruppe gruppe) throws FinderException;
}
