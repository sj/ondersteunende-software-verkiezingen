/*
 * Created on 18.11.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell;

import java.util.List;

import de.ivu.wahl.modell.GesamtstimmenImpl.Kandidatenergebnis;

/**
 * Read-only interface of class {@link GesamtstimmenImpl}
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface Gesamtstimmen {

  /**
   * Returns all candidate results for one list identifier, sorted by list position (if possible)
   * 
   * @param gruppenschluessel
   * @return
   */
  public List<Kandidatenergebnis> getKandidatenergebnisse(int gruppenschluessel);

  public int getStimmen(int gruppenschluessel, int listenplatz, String shortCode);

  public int getGruppenstimmen(int gruppenschluessel);

}