package de.ivu.wahl.modell.ejb;

import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;

/**
 * RepositoryHome
 */

public interface RepositoryHome extends BasicRepositoryHome {

  /**
   * Find repository by given name
   * 
   * @param name
   * @return
   * @throws ObjectNotFoundException
   * @throws FinderException
   */
  Repository findByName(String name) throws ObjectNotFoundException, FinderException;
}
