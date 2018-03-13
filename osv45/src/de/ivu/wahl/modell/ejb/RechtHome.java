package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

/**
 * RechtHome
 */

public interface RechtHome extends BasicRechtHome {

  /**
   * Find all rights of the given user
   * 
   * @param id_Anwender
   * @return
   * @throws FinderException
   * @throws EJBException
   */
  Collection<Recht> findAllByAnwender(String id_Anwender) throws FinderException, EJBException;
}
