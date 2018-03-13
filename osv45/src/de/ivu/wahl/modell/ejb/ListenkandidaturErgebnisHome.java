package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.FinderException;

/**
 * ListenkandidaturErgebnisHome
 * 
 * @author cos@ivu.de (c) 2003-7 Statistisches Bundesamt und IVU Traffic Technologies AG

 */

public interface ListenkandidaturErgebnisHome extends BasicListenkandidaturErgebnisHome {

  /**
   * Find list candidatur results by result and list candidature
   * 
   * @param id_Ergebniseingang
   * @param id_Listenkandidatur
   * @return
   * @throws FinderException
   */
  public ListenkandidaturErgebnis findByErgebniseingangAndListenkandidatur(String id_Ergebniseingang,
      String id_Listenkandidatur) throws FinderException;

  /**
   * Find all list candidature results filtered by selected and ordered by list position
   * 
   * @param id_Ergebniseingang
   * @return
   * @throws FinderException
   */
  public Collection<ListenkandidaturErgebnis> findAllByErgebniseingangAndGewaehlt(String id_Ergebniseingang)
      throws FinderException;

  /**
   * Find all list candidature results by result and list
   * 
   * @param id_Ergebniseingang
   * @param id_Liste
   * @return
   * @throws FinderException
   */
  public Collection<ListenkandidaturErgebnis> findAllByErgebniseingangAndListeAndGewaehlt(String id_Ergebniseingang,
      String id_Liste) throws FinderException;
}
