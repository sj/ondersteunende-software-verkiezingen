package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

/**
 * RechtegruppeHome
 */

public interface RechtegruppeHome extends BasicRechtegruppeHome {

  /**
   * Find all groups containing user rights
   * 
   * @return
   * @throws FinderException
   * @throws EJBException
   */
  Collection<Rechtegruppe> findAll() throws FinderException, EJBException;
}
