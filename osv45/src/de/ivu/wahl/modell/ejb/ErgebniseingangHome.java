package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;

/**
 * ErgebniseingangHome
 * 
 * @author cos@ivu.de (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public interface ErgebniseingangHome extends BasicErgebniseingangHome {

  /**
   * Find by election and kind of election result
   * 
   * @param id_Wahl
   * @param wahlergebnisart
   * @return
   * @throws FinderException
   */
  Collection<Ergebniseingang> findAllByWahlAndWahlergebnisart(String id_Wahl, int wahlergebnisart)
      throws FinderException;

  /**
   * Find by region and kind of election
   * 
   * @param id_Gebiet
   * @param wahlergebnisart
   * @return
   * @throws FinderException
   */
  Ergebniseingang findByGebietAndWahlergebnisart(String id_Gebiet, int wahlergebnisart)
      throws FinderException;

  /**
   * Find all by kind of election and region, sort by order of results
   * 
   * @param wahlergebnisart
   * @param id_Gebiet
   * @return
   * @throws FinderException
   */
  public Collection<Ergebniseingang> findAllByWahlergebnisartAndGebietSortByEingangsreihenfolge(int wahlergebnisart,
      String id_Gebiet) throws FinderException;

  /**
   * Find all result for input-region
   * 
   * @param id_erfassungseinheit ID_Gebiet of input-region
   * @return Collection of Ergebnbiseingang for input-region
   * @throws FinderException
   */
  Collection<Ergebniseingang> findAllByIDErfassungseinheit(String id_erfassungseinheit)
      throws FinderException;

  /**
   * Find inputs with same hash code
   * 
   * @param hash
   * @return
   * @throws FinderException
   */
  Collection<Ergebniseingang> findAllByErgebnisHashAndLastValidInput(String hash)
      throws FinderException;

  /**
   * Find last result of election with given kind of election result
   * 
   * @param id_Wahl
   * @param wahlergebnisart
   * @return
   * @throws FinderException
   */
  Ergebniseingang findLetzterByWahlAndWahlergebnisart(String id_Wahl, int wahlergebnisart)
      throws ObjectNotFoundException, FinderException;

  /**
   * Find last input by region and input mode
   * 
   * @param id_Gebiet region object identifier
   * @param wahlergebnisart input mode
   * @return
   * @throws ObjectNotFoundException
   * @throws FinderException
   */
  Ergebniseingang findLastInputByGebietAndWahlergebnisart(String id_Gebiet, int wahlergebnisart)
      throws ObjectNotFoundException, FinderException;

  /**
   * Find last valid first input by region
   * 
   * @param id_Gebiet region object identifier
   * @return
   * @throws ObjectNotFoundException
   * @throws FinderException
   */
  Ergebniseingang findLastValidFirstInputByGebietAndWahlergebnisart(String id_Gebiet,
      int wahlergebnisart) throws FinderException;

  /**
   * Get back the number of results for kind of election
   * 
   * @param id_Wahl
   * @param wahlergebnisart
   * @return
   * @throws EJBException
   */
  int countByWahlAndWahlergebnisart(String id_Wahl, int wahlergebnisart) throws EJBException;

}