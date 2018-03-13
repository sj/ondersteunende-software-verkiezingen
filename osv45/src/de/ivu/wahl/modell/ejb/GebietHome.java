/*
 * GebietHome
 * 
 * Copyright (c) 2002-5 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

/**
 * Home interface of the entity "Gebiet" (region), that is part of the meta data of the election
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public interface GebietHome extends BasicGebietHome {

  /**
   * Find all regions by superior region, sort by position
   * 
   * @param id_UebergeordnetesGebiet
   * @return
   * @throws FinderException
   */
  Collection<Gebiet> findAllByUebergeordnetesGebietSortByPosition(String id_UebergeordnetesGebiet)
      throws FinderException;

  /**
   * Find all regions by superior region, sort by number
   * 
   * @param id_UebergeordnetesGebiet
   * @return
   * @throws FinderException
   */
  Collection<Gebiet> findAllByUebergeordnetesGebietSortByNummer(String id_UebergeordnetesGebiet)
      throws FinderException;

  /**
   * Find all regions by election and kind of election
   * 
   * @param id_Wahl
   * @param gebietsart
   * @return
   * @throws FinderException
   */
  Collection<Gebiet> findAllByWahlAndGebietsart(String id_Wahl, int gebietsart)
      throws FinderException;

  /**
   * Find all regions by kind of region and sort by position
   * 
   * @param id_Wahl
   * @param gebietsart
   * @return
   * @throws FinderException
   */
  Collection<Gebiet> findAllByWahlAndGebietsartSortByPosition(String id_Wahl, int gebietsart)
      throws FinderException;

  /**
   * Find all regions by kind of region and sort by position
   * 
   * @param id_Wahl
   * @param gebietsart
   * @return
   * @throws FinderException
   */
  Collection<Gebiet> findAllByGebietsartSortByPosition(int gebietsart) throws FinderException;

  /**
   * Find all regions by election and kind of election result, that has changed since the entry of
   * the results
   * 
   * @param id_Wahl
   * @param wahlergebnisart
   * @param id_Ergebniseingang
   * @return
   * @throws FinderException
   */
  Collection<Gebiet> findAllByWahlAndWahlergebnisartGeaendertSeitErgebniseingang(String id_Wahl,
      int wahlergebnisart,
      String id_Ergebniseingang) throws FinderException;

  /**
   * Find all child regions of the given superior region
   * 
   * @param id_UebergeordnetesGebiet
   * @return
   * @throws FinderException
   */
  Collection<Gebiet> findAllErwartetByUebergeordnetesGebiet(String id_UebergeordnetesGebiet)
      throws FinderException;

  /**
   * Find all regions expected in the hierarchy and kind of region
   * 
   * @param id_Gebiet
   * @param gebietsart
   * @return
   * @throws FinderException
   */
  Collection<Gebiet> findAllErwartetInHierarchieByGebietAndGebietsart(String id_Gebiet,
      int gebietsart) throws FinderException;

  /**
   * Find all regions in hierarchy beginning with the given region, that comply with the given kind
   * of region
   * 
   * @param id_Gebiet
   * @param gebietsart
   * @return
   * @throws FinderException
   */
  Collection<Gebiet> findAllInHierarchieByGebietAndGebietsart(String id_Gebiet, int gebietsart)
      throws FinderException;

  /**
   * Find all detect units of an election
   * 
   * @param id_Wahl
   * @return
   * @throws FinderException
   */
  Collection<Gebiet> findAllIsErfassungseinheitByWahl(String id_Wahl) throws FinderException;

  /**
   * Find all detect units of an election and order by kind of region and number of region
   * 
   * @param id_Wahl
   * @return
   * @throws FinderException
   */
  Collection<Gebiet> findAllIsErfassungseinheitByWahlOrderByGebietsartAndNummer(String id_Wahl)
      throws FinderException;

  /**
   * Find all regions belonging to the superior region while being an election unit
   * 
   * @param id_UebergeordnetesGebiet
   * @return
   * @throws FinderException
   */
  Collection<Gebiet> findAllIsWahleinheitAusstehendByUebergeordnetesGebiet(String id_UebergeordnetesGebiet)
      throws FinderException;

  /**
   * Find all regions, which are election units too, order by number of region
   * 
   * @param id_Wahl
   * @return
   * @throws FinderException
   */
  Collection<Gebiet> findAllIsWahleinheitByWahlOrderByNummer(String id_Wahl) throws FinderException;

  /**
   * Find region by election, kind of region and number of region
   * 
   * @param id_Wahl
   * @param gebietsart
   * @param nummer
   * @return
   * @throws FinderException
   */
  Gebiet findByWahlAndGebietsartAndNummer(String id_Wahl, int gebietsart, int nummer)
      throws FinderException;

  /**
   * Fill table Gebiet_Gebiet
   * 
   * @throws EJBException when errors with database occur
   */
  void initGebietGebiet() throws EJBException;
}
