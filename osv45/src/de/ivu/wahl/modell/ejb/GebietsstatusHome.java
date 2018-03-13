package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.FinderException;

/**
 * GebietsstatusHome
 */

public interface GebietsstatusHome extends BasicGebietsstatusHome {

  /**
   * Find all region states of the region by kind of election result and region
   * 
   * @param wahlergebnisart
   * @param id_Gebiet
   * @return
   * @throws FinderException
   */
  Collection<Gebietsstatus> findAllByWahlergebnisartAndGebiet(int wahlergebnisart, String id_Gebiet)
      throws FinderException;

  /**
   * Find a region's state, that was valid at the moment of the given result
   * 
   * @param id_Ergebniseingang
   * @param id_Gebiet
   * @return
   * @throws FinderException
   */
  Gebietsstatus findByErgebniseingangAndGebiet(String id_Ergebniseingang, String id_Gebiet)
      throws FinderException;
}
