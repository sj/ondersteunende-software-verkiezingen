package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.FinderException;

/**
 * SitzberechnungErgebnisHome
 * 
 * @author cos@ivu.de (c) 2003-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public interface SitzberechnungErgebnisHome extends BasicSitzberechnungErgebnisHome {

  /**
   * Find results by type result of entry and type
   * 
   * @param id_Ergebniseingang
   * @param typ
   * @return
   * @throws FinderException
   */
  Collection<SitzberechnungErgebnis> findAllByErgebniseingangAndTyp(String id_Ergebniseingang,
      int typ) throws FinderException;

  /**
   * Find results by type result of entry and distribution
   * 
   * @param id_Ergebniseingang
   * @param verteilung
   * @return
   * @throws FinderException
   */
  Collection<SitzberechnungErgebnis> findAllByErgebniseingangAndVerteilung(String id_Ergebniseingang,
      int verteilung) throws FinderException;

  /**
   * Find results by type result of entry, distribution and type
   * 
   * @param id_Ergebniseingang
   * @param typ
   * @param verteilung
   * @return
   * @throws FinderException
   */
  Collection<SitzberechnungErgebnis> findAllByErgebniseingangAndTypAndVerteilung(String id_Ergebniseingang,
      int typ,
      int verteilung) throws FinderException;

}
