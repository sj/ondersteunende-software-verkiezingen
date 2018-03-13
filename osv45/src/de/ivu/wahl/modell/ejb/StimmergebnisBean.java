/*
 * StimmergebnisBean
 * 
 * Copyright (c) 2002-4 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb;

import static de.ivu.ejb.fw.DBABase.retrieveIDs;
import static de.ivu.wahl.modell.impl.StimmergebnisDBA.ID_ERGEBNISEINGANG;
import static de.ivu.wahl.modell.impl.StimmergebnisDBA.ID_ERGEBNISEINGANG_QUAL;
import static de.ivu.wahl.modell.impl.StimmergebnisDBA.ID_GEBIET;
import static de.ivu.wahl.modell.impl.StimmergebnisDBA.ID_GEBIET_QUAL;
import static de.ivu.wahl.modell.impl.StimmergebnisDBA.ID_LISTENKANDIDATUR;
import static de.ivu.wahl.modell.impl.StimmergebnisDBA.ID_LISTENKANDIDATUR_QUAL;
import static de.ivu.wahl.modell.impl.StimmergebnisDBA.ID_STIMMERGEBNIS;
import static de.ivu.wahl.modell.impl.StimmergebnisDBA.TABLENAME;

import java.sql.SQLException;
import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.StimmergebnisModel;
import de.ivu.wahl.modell.impl.BasicGebietDBA;
import de.ivu.wahl.modell.impl.GruppeGebietsspezifischDBA;
import de.ivu.wahl.modell.impl.ListenkandidaturDBA;
import de.ivu.wahl.modell.impl.StimmergebnisDBA;

/**
 * StimmergebnisBean
 * 
 * @author cos@ivu.de IVU Traffic Technologies AG
 */

public class StimmergebnisBean extends BasicStimmergebnisBean {
  private static final long serialVersionUID = -1612977904001714067L;

  private static final String IS_NULL = " is null "; //$NON-NLS-1$

  private static final Category LOGGER = Log4J.configure(StimmergebnisBean.class);
  static {
    LOGGER.info(Log4J.dumpVersion(StimmergebnisBean.class, Log4J
        .extractVersion("$Revision: 1.22 $"))); //$NON-NLS-1$
  }

  /**
   * Find all voting results by region and result for parties
   * 
   * @param id_Gebiet
   * @param id_Ergebniseingang
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByGebietAndErgebniseingangForGruppen(String id_Gebiet,
      String id_Ergebniseingang) throws FinderException {

    try {
      return retrieveIDs("select " + ID_STIMMERGEBNIS + " from " + TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ID_ERGEBNISEINGANG + "=? and " + ID_GEBIET + "=? and " + ID_LISTENKANDIDATUR + IS_NULL, new Object[]{id_Ergebniseingang, //$NON-NLS-1$ //$NON-NLS-2$
              id_Gebiet});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * All voting results by import action and region
   * 
   * @param id_Ergebniseingang Ergebniseingang als Referenz f�r einen bestimmten Zustand, zu dem die
   *          Stimmen geholt werden sollen
   * @param id_Gebiet das Gebiet zu dem die Stimmen geholt werden sollen
   * @return Prim�rschl�ssel der Stimmergebnisse f�r ein aktuelles oder historisches Resultat f�r
   *         ein Gebiet
   * @throws FinderException genereller Fehler bei der Durchf�hrung der Suche (bedeutet NICHT "nicht
   *           gefunden").
   */
  public Collection<String> ejbFindAllByGebietAndErgebniseingang(String id_Gebiet,
      String id_Ergebniseingang) throws FinderException {

    try {
      return retrieveIDs("select " + ID_STIMMERGEBNIS + " from " + TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ID_ERGEBNISEINGANG + "=? and " + ID_GEBIET + "=?", new Object[]{id_Ergebniseingang, //$NON-NLS-1$ //$NON-NLS-2$
          id_Gebiet});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Voting results for candidates of a given P2-list by import action and region (so it really
   * filters a candidate list, if the region is where candidate lists are submitted)
   */
  public Collection<String> ejbFindAllByErgebniseingangAndGebietAndListe(String id_Ergebniseingang,
      String id_Gebiet,
      String id_Liste) throws FinderException {

    try {
      return retrieveIDs("select " + ID_STIMMERGEBNIS + " from " + TABLENAME + "," //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ListenkandidaturDBA.TABLENAME + " where " + ID_ERGEBNISEINGANG_QUAL + "=? and " //$NON-NLS-1$ //$NON-NLS-2$
          + ID_GEBIET_QUAL + "=? and " + ID_LISTENKANDIDATUR_QUAL + "=" //$NON-NLS-1$ //$NON-NLS-2$
          + ListenkandidaturDBA.ID_LISTENKANDIDATUR_QUAL + " and " //$NON-NLS-1$
          + ListenkandidaturDBA.ID_LISTE_QUAL + "=?", new Object[]{id_Ergebniseingang, id_Gebiet, //$NON-NLS-1$
          id_Liste});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * All voting results for a group in a specific region
   */
  public Collection<String> ejbFindAllByErgebniseingangAndGruppeGebietsspezifisch(String id_Ergebniseingang,
      String id_GruppeGebietsspezifisch) throws FinderException {

    try {
      return retrieveIDs("select " + ID_STIMMERGEBNIS + " from " + TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + StimmergebnisDBA.ID_ERGEBNISEINGANG + "=? and " //$NON-NLS-1$
          + StimmergebnisDBA.ID_GRUPPEGEBIETSSPEZIFISCH + "=?", new Object[]{id_Ergebniseingang, //$NON-NLS-1$
          id_GruppeGebietsspezifisch});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * All voting results for a list in a specific region
   */
  public Collection<String> ejbFindAllByErgebniseingangAndListe(String id_Ergebniseingang,
      String id_Liste) throws FinderException {

    try {
      return retrieveIDs("select ID_Stimmergebnis from " //$NON-NLS-1$
          + TABLENAME
          + " s, " //$NON-NLS-1$
          + GruppeGebietsspezifischDBA.TABLENAME
          + " gg, " //$NON-NLS-1$
          + BasicGebietDBA.TABLENAME
          + " g where s.ID_Ergebniseingang=? and s.ID_GruppeGebietsspezifisch=gg.ID_GruppeGebietsspezifisch " //$NON-NLS-1$
          + " and gg." + GruppeGebietsspezifischDBA.ID_LISTE + "=?" + " and s." //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ID_LISTENKANDIDATUR + IS_NULL + " and s." //$NON-NLS-1$
          + ID_GEBIET + "=g." + BasicGebietDBA.ID_GEBIET + " order by g." //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGebietDBA.NUMMER,
          new Object[]{id_Ergebniseingang, id_Liste});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  public Collection<String> ejbFindAllByErgebniseingangAndGruppe(String id_Ergebniseingang,
      String id_Gruppe) throws FinderException {

    try {
      return retrieveIDs("select ID_Stimmergebnis from " //$NON-NLS-1$
          + TABLENAME
          + " s, " //$NON-NLS-1$
          + GruppeGebietsspezifischDBA.TABLENAME
          + " gg, " //$NON-NLS-1$
          + BasicGebietDBA.TABLENAME
          + " g where s.ID_Ergebniseingang=? and s.ID_GruppeGebietsspezifisch=gg.ID_GruppeGebietsspezifisch " //$NON-NLS-1$
          + " and gg." + GruppeGebietsspezifischDBA.ID_GRUPPE + "=?" + " and s." //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ID_LISTENKANDIDATUR + IS_NULL + " and s." //$NON-NLS-1$
          + ID_GEBIET + "=g." + BasicGebietDBA.ID_GEBIET //$NON-NLS-1$
          + " and g." //$NON-NLS-1$
          + BasicGebietDBA.ERFASSUNGSEINHEIT + "=1 " + " order by g." + BasicGebietDBA.NUMMER, //$NON-NLS-1$//$NON-NLS-2$
          new Object[]{id_Ergebniseingang, id_Gruppe});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  public Collection<String> ejbFindAllByErgebniseingangAndStimmartForGroups(String id_Ergebniseingang,
      int stimmart) throws FinderException {

    try {
      return retrieveIDs("select ID_Stimmergebnis from " //$NON-NLS-1$
          + TABLENAME
          + " s, " //$NON-NLS-1$
          + BasicGebietDBA.TABLENAME
          + " g where s.ID_Ergebniseingang = ? and s." + StimmergebnisDBA.STIMMART + " = ?" //$NON-NLS-1$ //$NON-NLS-2$
          + " and s." + ID_LISTENKANDIDATUR + IS_NULL //$NON-NLS-1$
          + " and s." + ID_GEBIET + "= g." + BasicGebietDBA.ID_GEBIET //$NON-NLS-1$ //$NON-NLS-2$
          + " and g." + BasicGebietDBA.ERFASSUNGSEINHEIT + " = 1 " //$NON-NLS-1$ //$NON-NLS-2$
          + " order by g." + BasicGebietDBA.NUMMER, //$NON-NLS-1$
          new Object[]{id_Ergebniseingang, stimmart});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  public Collection<String> ejbFindAllByErgebniseingangAndListenkandidatur(String id_Ergebniseingang,
      String id_Listenkandidatur) throws FinderException {

    try {
      return retrieveIDs("select ID_Stimmergebnis from " + TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$
          + ID_ERGEBNISEINGANG_QUAL + "=? and " + ID_LISTENKANDIDATUR_QUAL + "=?", new Object[]{ //$NON-NLS-1$//$NON-NLS-2$
          id_Ergebniseingang, id_Listenkandidatur});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  public Collection<String> ejbFindAllByGebietAndErgebniseingangAllgemein(String id_Ergebniseingang,
      String id_Gebiet) throws FinderException {
    try {
      return retrieveIDs("select " + StimmergebnisDBA.ID_STIMMERGEBNIS_QUAL + " from " + TABLENAME //$NON-NLS-1$ //$NON-NLS-2$
          + " where " + StimmergebnisDBA.ID_ERGEBNISEINGANG_QUAL + "=? and " //$NON-NLS-1$ //$NON-NLS-2$
          + StimmergebnisDBA.ID_GEBIET_QUAL + " = ? and " + StimmergebnisDBA.STIMMART_QUAL + "=" //$NON-NLS-1$ //$NON-NLS-2$
          + StimmergebnisModel.STIMMART_KEINE, new Object[]{id_Ergebniseingang, id_Gebiet});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * @param id_Ergebniseingang
   * @param id_Gebiet
   * @param id_Gruppe
   * @return
   * @throws FinderException
   */
  public String ejbFindByErgebniseingangAndGebietAndGruppeForGruppe(String id_Ergebniseingang,
      String id_Gebiet,
      String id_Gruppe) throws FinderException {
    return findSingle("select " + StimmergebnisDBA.ID_STIMMERGEBNIS_QUAL + " from " + TABLENAME //$NON-NLS-1$ //$NON-NLS-2$
        + ", " + GruppeGebietsspezifischDBA.TABLENAME + " where " //$NON-NLS-1$//$NON-NLS-2$
        + StimmergebnisDBA.ID_ERGEBNISEINGANG_QUAL + "=? and " + StimmergebnisDBA.ID_GEBIET_QUAL //$NON-NLS-1$
        + " =? and " + StimmergebnisDBA.ID_GRUPPEGEBIETSSPEZIFISCH_QUAL + " =" //$NON-NLS-1$ //$NON-NLS-2$
        + GruppeGebietsspezifischDBA.ID_GRUPPEGEBIETSSPEZIFISCH_QUAL + " and " //$NON-NLS-1$
        + GruppeGebietsspezifischDBA.ID_GRUPPE_QUAL + " =? and " //$NON-NLS-1$
        + StimmergebnisDBA.ID_LISTENKANDIDATUR_QUAL + IS_NULL, new Object[]{id_Ergebniseingang,
        id_Gebiet, id_Gruppe});
  }

  /**
   * F�hrt die �bergebene Query aus, pr�ft auf ein Einzelergebnis oder, im Fehlerfall, kovertiert
   * die Exception
   * 
   * @param query die auszuf�hrende Query
   * @param params Parameter der Query
   * @return Einzelergebnis der Query (ein Prim�rschl�ssel)
   * @throws ObjectNotFoundException Entity nicht gefunden
   * @throws FinderException genereller Fehler bei der Durchf�hrung der Suche
   */
  private String findSingle(String query, Object[] params)
      throws ObjectNotFoundException, FinderException {

    try {
      return findSingle(retrieveIDs(query, params));
    } catch (SQLException se) {
      throw new FinderException(se.getMessage());
    }
  }

  // experimentelle Optimierung f�r den L�schvorgang
  {
    _usesLazyLoading = true;
  }

  @Override
  public StimmergebnisModel getDetails() {
    condLoad();
    return super.getDetails();
  }

  @Override
  public Ergebniseingang getErgebniseingang() throws EJBException {
    condLoad();
    return super.getErgebniseingang();
  }

  @Override
  public Gebiet getGebiet() throws EJBException {
    condLoad();
    return super.getGebiet();
  }

  @Override
  public GruppeGebietsspezifisch getGruppeGebietsspezifisch() throws EJBException {
    condLoad();
    return super.getGruppeGebietsspezifisch();
  }

  @Override
  public String getID_Ergebniseingang() {
    condLoad();
    return super.getID_Ergebniseingang();
  }

  @Override
  public String getID_Gebiet() {
    condLoad();
    return super.getID_Gebiet();
  }

  @Override
  public String getID_GruppeGebietsspezifisch() {
    condLoad();
    return super.getID_GruppeGebietsspezifisch();
  }

  @Override
  public String getID_Listenkandidatur() {
    condLoad();
    return super.getID_Listenkandidatur();
  }

  @Override
  public Listenkandidatur getListenkandidatur() {
    condLoad();
    return super.getListenkandidatur();
  }

  @Override
  public String getID_Stimmergebnis() {
    condLoad();
    return super.getID_Stimmergebnis();
  }

  @Override
  public int getStimmart() {
    condLoad();
    return super.getStimmart();
  }

  @Override
  public int getStimmen() {
    condLoad();
    return super.getStimmen();
  }

  @Override
  public int getWahlergebnisart() {
    condLoad();
    return super.getWahlergebnisart();
  }

  @Override
  public void setErgebniseingang(Ergebniseingang ergebniseingang) {
    condLoad();
    super.setErgebniseingang(ergebniseingang);
  }

  @Override
  public void setGebiet(Gebiet gebiet) {
    condLoad();
    super.setGebiet(gebiet);
  }

  @Override
  public void setGruppeGebietsspezifisch(GruppeGebietsspezifisch gruppeGebietsspezifisch) {
    condLoad();
    super.setGruppeGebietsspezifisch(gruppeGebietsspezifisch);
  }

  @Override
  public void setID_Ergebniseingang(String id_Ergebniseingang) {
    condLoad();
    super.setID_Ergebniseingang(id_Ergebniseingang);
  }

  @Override
  public void setID_Gebiet(String id_Gebiet) {
    condLoad();
    super.setID_Gebiet(id_Gebiet);
  }

  @Override
  public void setID_GruppeGebietsspezifisch(String id_GruppeGebietsspezifisch) {
    condLoad();
    super.setID_GruppeGebietsspezifisch(id_GruppeGebietsspezifisch);
  }

  @Override
  public void setStimmart(int stimmart) {
    condLoad();
    super.setStimmart(stimmart);
  }

  @Override
  public void setStimmen(int stimmen) {
    condLoad();
    super.setStimmen(stimmen);
  }

  @Override
  public void setWahlergebnisart(int wahlergebnisart) {
    condLoad();
    super.setWahlergebnisart(wahlergebnisart);
  }

  @Override
  public String toString() {
    if (_pendingLoad) {
      return Messages.bind(MessageKeys.Msg_id_Stimmergebnis_0_BeanNochNichtGeladen, _ctx
          .getPrimaryKey());
    } else {
      return super.toString();
    }
  }

}