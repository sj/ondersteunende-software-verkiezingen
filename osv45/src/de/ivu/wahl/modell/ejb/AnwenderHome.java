package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;

/**
 * AnwenderHome
 */

public interface AnwenderHome extends BasicAnwenderHome {

  /**
   * find by user name and password hash
   * 
   * @param anwendername
   * @param passwordHash
   * @return
   * @throws ObjectNotFoundException
   * @throws FinderException
   */
  Anwender findByAnwendernameAndPasswordHash(String anwendername, String passwordHash)
      throws ObjectNotFoundException, FinderException;

  /**
   * find by user name
   * 
   * @param anwendername
   * @return
   * @throws ObjectNotFoundException
   * @throws FinderException
   */
  Anwender findByAnwendername(String anwendername) throws ObjectNotFoundException, FinderException;

  /**
   * find all users by region or child region
   * 
   * @param id_Gebiet
   * @param anwWithNull
   * @return
   * @throws FinderException
   */
  Collection<Anwender> findAllVonGebietUndKindergebieten(String id_Gebiet, boolean anwWithNull)
      throws FinderException;
}
