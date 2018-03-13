package de.ivu.wahl.modell.ejb;

import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;

/**
 * SchwellwertHome
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */

public interface SchwellwertHome extends BasicSchwellwertHome {

  /**
   * Find threshold by election and name
   * 
   * @param id_Wahl
   * @param name
   * @return
   * @throws ObjectNotFoundException
   * @throws FinderException
   */
  Schwellwert findByWahlAndName(String id_Wahl, String name)
      throws ObjectNotFoundException, FinderException;

  /**
   * Find threshold by group, region and name
   * 
   * @param id_Gruppe
   * @param id_Gebiet
   * @param name
   * @return
   * @throws ObjectNotFoundException
   * @throws FinderException
   */
  Schwellwert findByGruppeAndGebietAndName(String id_Gruppe, String id_Gebiet, String name)
      throws ObjectNotFoundException, FinderException;
}
