package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

/**
 * ListeHome
 * 
 * @author cos@ivu.de (c) 2003-7 Statistisches Bundesamt und IVU Traffic Technologies AG

 */

public interface ListeHome extends BasicListeHome {

  /**
   * Find all lists nominated for this region
   * 
   * @param id_Gebiet
   * @return
   * @throws FinderException
   * @throws EJBException
   */
  Collection<Liste> findAllByGebiet(String id_Gebiet) throws FinderException, EJBException;

}
