package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.FinderException;

/**
 * KonfliktHome
 * 
 * @author cos@ivu.de (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public interface KonfliktHome extends BasicKonfliktHome {

  /**
   * Find all by result and kind
   * 
   * @param id_Ergebniseingang
   * @param art
   * @return
   * @throws FinderException
   */
  Collection<Konflikt> findAllByErgebniseingangAndArt(String id_Ergebniseingang, int art)
      throws FinderException;

  /**
   * Find all by result, ordered by number
   * 
   * @param id_Ergebniseingang
   * @return
   * @throws FinderException
   */
  Collection<Konflikt> findAllByErgebniseingangOrderByNummer(String id_Ergebniseingang)
      throws FinderException;

}
