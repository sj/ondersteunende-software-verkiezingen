package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.FinderException;

/**
 * StimmergebnisHome
 * 
 * @author cos@ivu.de (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public interface StimmergebnisHome extends BasicStimmergebnisHome {

  /**
   * All voting results for a group in a specific region
   * 
   * @param id_Ergebniseingang the import action
   * @param id_GruppeGebietsspezifisch the region specifoc identifier for a party
   * @return
   * @throws FinderException
   */
  Collection<Stimmergebnis> findAllByErgebniseingangAndGruppeGebietsspezifisch(String id_Ergebniseingang,
      String id_GruppeGebietsspezifisch) throws FinderException;

  /**
   * All voting results for a list
   * 
   * @param id_Ergebniseingang the import action
   * @param id_Liste the list
   * @return
   * @throws FinderException
   */
  Collection<Stimmergebnis> findAllByErgebniseingangAndListe(String id_Ergebniseingang,
      String id_Liste) throws FinderException;

  /**
   * All voting results for a group
   * 
   * @param id_Ergebniseingang the import action
   * @param id_Gruppe the list
   * @return
   * @throws FinderException
   */
  Collection<Stimmergebnis> findAllByErgebniseingangAndGruppe(String id_Ergebniseingang,
      String id_Gruppe) throws FinderException;

  /**
   * All voting results with given stimmart for groups
   */
  Collection<Stimmergebnis> findAllByErgebniseingangAndStimmartForGroups(String id_Ergebniseingang,
      int stimmart) throws FinderException;

  /**
   * All candidate voting results for a list in a region
   * 
   * @param id_Ergebniseingang the import action
   * @param id_Gebiet
   * @param id_Liste
   * @return
   * @throws FinderException
   */
  Collection<Stimmergebnis> findAllByErgebniseingangAndGebietAndListe(String id_Ergebniseingang,
      String id_Gebiet,
      String id_Liste) throws FinderException;

  /**
   * All voting results for a candidate
   * 
   * @param id_Ergebniseingang the import action
   * @param id_Listenkandidatur the candidate
   * @return
   * @throws FinderException
   */
  Collection<Stimmergebnis> findAllByErgebniseingangAndListenkandidatur(String id_Ergebniseingang,
      String id_Listenkandidatur) throws FinderException;

  /**
   * The voting result for a group in a specific region
   * <p>
   * Do NOT regard vote values. Used for EML510 and the HSB part of P22-2 (RG520) only.
   * 
   * @param id_Ergebniseingang the import action
   * @param id_GruppeGebietsspezifisch the region specifoc identifier for a party
   * @return
   * @throws FinderException
   */
  Stimmergebnis findByErgebniseingangAndGebietAndGruppeForGruppe(String id_Ergebniseingang,
      String id_Gebiet,
      String id_Gruppe) throws FinderException;

  /**
   * Find all voting results by region and result for parties
   * 
   * @param id_Gebiet
   * @param id_Ergebniseingang
   * @return
   * @throws FinderException
   */
  Collection<Stimmergebnis> findAllByGebietAndErgebniseingangForGruppen(String id_Gebiet,
      String id_Ergebniseingang) throws FinderException;

  /**
   * Find all voting results by region and result
   * <p>
   * Do NOT regard vote values. Used by EingangsHandlingBean.
   * 
   * @param id_Gebiet
   * @param id_Ergebniseingang
   * @return
   * @throws FinderException
   */
  Collection<Stimmergebnis> findAllByGebietAndErgebniseingang(String id_Gebiet,
      String id_Ergebniseingang) throws FinderException;

  /**
   * Find general results for a given region
   * <p>
   * CAUTION: Used by EML510 and EML520. MULTIPLY vote values if necessary.
   * 
   * @param id_Gebiet region identifier
   * @param id_Ergebniseingang the import action
   * @return
   * @throws FinderException
   */
  Collection<Stimmergebnis> findAllByGebietAndErgebniseingangAllgemein(String id_Ergebniseingang,
      String id_Gebiet) throws FinderException;

}
