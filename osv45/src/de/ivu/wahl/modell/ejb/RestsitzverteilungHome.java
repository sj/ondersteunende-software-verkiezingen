package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.FinderException;

/**
 * RestsitzverteilungHome
 * 
 * @author jon@ivu.de (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public interface RestsitzverteilungHome extends BasicRestsitzverteilungHome {

  /**
   * Finder for the information of d'Hondt assignments to P42-lists
   */
  Collection<Restsitzverteilung> findAllForP42Distribution(String id_Ergebniseingang)
      throws FinderException;

  /**
   * Finder for the information of d'Hondt assignments to the P3-lists that belong to the P42-list
   * denoted by id_Listenkombination.
   */
  Collection<Restsitzverteilung> findAllByListenkombination(String id_Ergebniseingang,
      String id_Listenkombination) throws FinderException;

  /**
   * Finder for the information of d'Hondt assignments to the P2-lists that belong to the P3-list
   * denoted by id_Gruppe.
   */
  Collection<Restsitzverteilung> findAllByGruppe(String id_Ergebniseingang, String id_Gruppe)
      throws FinderException;
}
