/*
 * SitzverteilungHome
 * 
 * Copyright (c) 2007 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.FinderException;

/**
 * SitzverteilungHome
 * 
 * @author cos@ivu.de (c) 2007 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public interface SitzverteilungHome extends BasicSitzverteilungHome {

  /**
   * Find seat distribution by result and list
   * 
   * @param id_Ergebniseingang
   * @param id_Liste
   * @return
   * @throws FinderException
   */
  Sitzverteilung findByErgebniseingangAndListe(String id_Ergebniseingang, String id_Liste)
      throws FinderException;

  /**
   * Find seat distribution by result and list combination with the sum of the list combination
   * 
   * @param id_Ergebniseingang
   * @param id_Listenkombination
   * @param withListenkombinationsumme
   * @return
   * @throws FinderException
   */
  Collection<Sitzverteilung> findAllByErgebniseingangAndListenkombinationWithLKSumme(String id_Ergebniseingang,
      String id_Listenkombination,
      boolean withListenkombinationsumme) throws FinderException;

}
