/*
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb.service;

import java.util.List;
import java.util.Map;

import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.Liste;

/**
 * SessionBean interface for business services related to the entities {@link Gruppe}
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface GruppeHandling {
  /**
   * @see de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein
   * @return Map of all special groups by key
   */
  public Map<Integer, Gruppe> getSpecialGroups();

  /**
   * @return All P3-lists sorted by list number
   */
  public List<Gruppe> getGroupsSorted();

  /**
   * Returns all the P2-lists (Liste) sorted by the <code>schluessel</code> attribute of the
   * corresponding P3-list (Gruppe).
   */
  List<Liste> getAllP2ListsSortedByGruppenschluessel();

}