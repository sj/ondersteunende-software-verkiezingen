package de.ivu.wahl.modell.ejb;

/**
 * RepositoryBean
 */

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;

public class RepositoryBean extends BasicRepositoryBean {
  private static final long serialVersionUID = -1736780541886115766L;

  private static final Category LOGGER = Log4J.configure(RepositoryBean.class);
  static {
    LOGGER.info(Log4J.dumpVersion(RepositoryBean.class, Log4J.extractVersion("$Revision: 1.3 $"))); //$NON-NLS-1$
  }

  /**
   * Find repository by given name
   * 
   * @param name
   * @return
   * @throws ObjectNotFoundException
   * @throws FinderException
   * @throws EJBException
   */
  public String ejbFindByName(String name)
      throws ObjectNotFoundException, FinderException, EJBException {
    return findSingle(ejbFindAllByName(name));
  }
}
