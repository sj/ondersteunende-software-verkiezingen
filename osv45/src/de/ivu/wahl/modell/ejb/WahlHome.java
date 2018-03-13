package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

/**
 * WahlHome
 */

public interface WahlHome extends BasicWahlHome {

  /**
   * Get all elections
   * 
   * @return
   * @throws FinderException
   * @throws EJBException
   */
  Collection<Wahl> findAll() throws FinderException, EJBException;
}
