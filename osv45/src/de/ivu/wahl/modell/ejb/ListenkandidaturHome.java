package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.FinderException;

/**
 * LocalHome Interface f�r die Entit�t Listenkandidatur als BMP Entity Bean.
 * 
 * @author cos@ivu.de (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
public interface ListenkandidaturHome extends BasicListenkandidaturHome {

  /**
   * Find list candidature by region and person
   * 
   * @param id_Gebiet
   * @param id_Personendaten
   * @return
   * @throws FinderException
   */
  public Listenkandidatur findByGebietAndPerson(String id_Gebiet, String id_Personendaten)
      throws FinderException;

  /**
   * Find list candidature by list and list position
   * 
   * @param id_Liste
   * @param listenplatz
   * @return
   * @throws FinderException
   */
  public Listenkandidatur findByListeAndListenplatz(String id_Liste, int listenplatz)
      throws FinderException;

  /**
   * Find list candidature by region, group key and list position
   * 
   * @param id_Gebiet
   * @param gruppenschluessel
   * @param listenplatz
   * @return
   * @throws FinderException
   */
  public Listenkandidatur findByGebietGruppenschluesselAndListenplatz(String id_Gebiet,
      int gruppenschluessel,
      int listenplatz) throws FinderException;

  /**
   * Find all list candidatures by region and group key
   * 
   * @param id_Gebiet
   * @param gruppenschluessel
   * @return
   * @throws FinderException
   */
  public Collection<Listenkandidatur> findAllByGebietAndGruppenschluessel(String id_Gebiet,
      int gruppenschluessel) throws FinderException;

  /**
   * Find all list candidatures by group key and list position
   * 
   * @param gruppenschluessel
   * @param listenplatz
   * @return
   * @throws FinderException
   */
  public Collection<Listenkandidatur> findAllByGruppenschluesselAndListenplatz(int gruppenschluessel,
      int listenplatz) throws FinderException;

  /**
   * Find all list candidatures by list id order by candidate position
   * 
   * @param id_Liste
   * @return
   * @throws FinderException
   */
  public Collection<Listenkandidatur> findAllByListeOrderByListenplatz(String id_Liste)
      throws FinderException;

  /**
   * Find all list candidatures by party
   * 
   * @param id_Gruppe
   * @return
   * @throws FinderException
   */
  public Collection<Listenkandidatur> findDistinctPersonendatenByGruppe(String id_Gruppe)
      throws FinderException;
}