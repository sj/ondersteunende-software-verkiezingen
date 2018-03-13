package de.ivu.wahl.modell.ejb;

/**
 * ListenkandidaturBean
 * 
 * @author cos@ivu.de (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

import static de.ivu.ejb.fw.DBABase.retrieveIDs;
import static de.ivu.wahl.modell.impl.ListenkandidaturDBA.TABLENAME;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.FinderException;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.modell.impl.BasicGruppeDBA;
import de.ivu.wahl.modell.impl.GruppeGebietsspezifischDBA;
import de.ivu.wahl.modell.impl.ListeDBA;
import de.ivu.wahl.modell.impl.ListenkandidaturDBA;

public class ListenkandidaturBean extends BasicListenkandidaturBean {
  private static final long serialVersionUID = 598005195285664717L;

  private static final Category LOGGER = Log4J.configure(ListenkandidaturBean.class);
  static {
    LOGGER.info(Log4J.dumpVersion(ListenkandidaturBean.class, Log4J
        .extractVersion("$Revision: 1.23 $"))); //$NON-NLS-1$
  }

  /**
   * Get the region where the candidature was set
   * 
   * @return
   */
  public Collection<Gebiet> getGebietCol() {
    List<Gebiet> gebiete = new ArrayList<Gebiet>();
    for (GruppeGebietsspezifisch gg : getListe().getGruppeGebietsspezifischCol()) {
      gebiete.add(gg.getGebiet());
    }
    return gebiete;
  }

  /**
   * Get the group where the candidature was set
   * 
   * @return
   */
  public Gruppe getGruppe() {
    return getListe().getGruppe();
  }

  public String getAnzeigeName() {
    return getPersonendaten().getAnzeigeName() + " (" + getGruppe().getNameLang() + ", pos. " //$NON-NLS-1$//$NON-NLS-2$
        + getListenplatz() + ")"; //$NON-NLS-1$
  }

  /**
   * Find all list candidatures by group key and list position
   * 
   * @param gruppenschluessel
   * @param listenplatz
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByGruppenschluesselAndListenplatz(int gruppenschluessel,
      int listenplatz) throws FinderException {
    try {
      return retrieveIDs("select distinct " + ListenkandidaturDBA.ID_LISTENKANDIDATUR + " from " //$NON-NLS-1$ //$NON-NLS-2$
          + TABLENAME
          + " lk, " + GruppeGebietsspezifischDBA.TABLENAME + " gg, " //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGruppeDBA.TABLENAME
          + " g where gg." + GruppeGebietsspezifischDBA.ID_LISTE + "=lk." //$NON-NLS-1$ //$NON-NLS-2$
          + ListenkandidaturDBA.ID_LISTE + " and lk." + ListenkandidaturDBA.LISTENPLATZ //$NON-NLS-1$
          + "=? and gg." + GruppeGebietsspezifischDBA.ID_GRUPPE + "=g." + BasicGruppeDBA.ID_GRUPPE //$NON-NLS-1$ //$NON-NLS-2$
          + " and g." + BasicGruppeDBA.SCHLUESSEL + "=?", new Object[]{new Integer(listenplatz), //$NON-NLS-1$ //$NON-NLS-2$
          new Integer(gruppenschluessel)});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find all list candidatures by region and group key
   * 
   * @param id_Gebiet
   * @param gruppenschluessel
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByGebietAndGruppenschluessel(String id_Gebiet,
      int gruppenschluessel) throws FinderException {
    try {
      return retrieveIDs("select " + ListenkandidaturDBA.ID_LISTENKANDIDATUR + " from " + TABLENAME //$NON-NLS-1$ //$NON-NLS-2$
          + " lk, " + GruppeGebietsspezifischDBA.TABLENAME + " gg, " + BasicGruppeDBA.TABLENAME //$NON-NLS-1$ //$NON-NLS-2$
          + " g where gg." + GruppeGebietsspezifischDBA.ID_GEBIET + "=? and gg." //$NON-NLS-1$ //$NON-NLS-2$
          + GruppeGebietsspezifischDBA.ID_LISTE + "=lk." + ListenkandidaturDBA.ID_LISTE //$NON-NLS-1$
          + " and gg." + GruppeGebietsspezifischDBA.ID_GRUPPE + "=g." + BasicGruppeDBA.ID_GRUPPE //$NON-NLS-1$ //$NON-NLS-2$
          + " and g." + BasicGruppeDBA.SCHLUESSEL + "=?", new Object[]{id_Gebiet, //$NON-NLS-1$ //$NON-NLS-2$
          new Integer(gruppenschluessel)});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find list candidature by region and person
   * 
   * @param id_Gebiet
   * @param id_Personendaten
   * @return
   * @throws FinderException
   */
  public String ejbFindByGebietAndPerson(String id_Gebiet, String id_Personendaten)
      throws FinderException {
    try {
      return findSingle(retrieveIDs("select " + ListenkandidaturDBA.ID_LISTENKANDIDATUR + " from " //$NON-NLS-1$ //$NON-NLS-2$
          + TABLENAME + " lk, " + GruppeGebietsspezifischDBA.TABLENAME + " gg where gg." //$NON-NLS-1$ //$NON-NLS-2$
          + GruppeGebietsspezifischDBA.ID_GEBIET + "=? and gg." //$NON-NLS-1$
          + GruppeGebietsspezifischDBA.ID_LISTE + "=lk." + ListenkandidaturDBA.ID_LISTE //$NON-NLS-1$
          + " and lk." + ListenkandidaturDBA.ID_PERSONENDATEN + "=?", new Object[]{id_Gebiet, //$NON-NLS-1$ //$NON-NLS-2$
          id_Personendaten}));
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find list candidature by list and list position
   * 
   * @param id_Liste
   * @param listenplatz
   * @return
   * @throws FinderException
   */
  public String ejbFindByListeAndListenplatz(String id_Liste, int listenplatz)
      throws FinderException {
    try {
      return findSingle(retrieveIDs("select " + ListenkandidaturDBA.ID_LISTENKANDIDATUR + " from " //$NON-NLS-1$ //$NON-NLS-2$
          + TABLENAME + " lk where lk." + ListenkandidaturDBA.ID_LISTE + "=?" + " and lk." //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ListenkandidaturDBA.LISTENPLATZ + "=?", //$NON-NLS-1$
          new Object[]{id_Liste, Integer.valueOf(listenplatz)}));
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find list candidature by region, group key and list position
   * 
   * @param id_Gebiet
   * @param gruppenschluessel
   * @param listenplatz
   * @return
   * @throws FinderException
   */
  public String ejbFindByGebietGruppenschluesselAndListenplatz(String id_Gebiet,
      int gruppenschluessel,
      int listenplatz) throws FinderException {
    try {
      return findSingle(retrieveIDs("select " + ListenkandidaturDBA.ID_LISTENKANDIDATUR + " from " //$NON-NLS-1$ //$NON-NLS-2$
          + TABLENAME + " lk, " + GruppeGebietsspezifischDBA.TABLENAME + " gg, " //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGruppeDBA.TABLENAME + " g where gg." + GruppeGebietsspezifischDBA.ID_GEBIET //$NON-NLS-1$
          + "=? and gg." + GruppeGebietsspezifischDBA.ID_LISTE + "=lk." //$NON-NLS-1$//$NON-NLS-2$
          + ListenkandidaturDBA.ID_LISTE + " and lk." + ListenkandidaturDBA.LISTENPLATZ //$NON-NLS-1$
          + "=? and gg." + GruppeGebietsspezifischDBA.ID_GRUPPE + "=g." + BasicGruppeDBA.ID_GRUPPE //$NON-NLS-1$ //$NON-NLS-2$
          + " and g." + BasicGruppeDBA.SCHLUESSEL + "=?", new Object[]{id_Gebiet, //$NON-NLS-1$ //$NON-NLS-2$
          Integer.valueOf(listenplatz), Integer.valueOf(gruppenschluessel)}));
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find all list candidatures by list id order by candidate position
   * 
   * @param id_Liste
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByListeOrderByListenplatz(String id_Liste)
      throws FinderException {
    try {
      return retrieveIDs("select " + ListenkandidaturDBA.ID_LISTENKANDIDATUR + " from " //$NON-NLS-1$ //$NON-NLS-2$
          + TABLENAME
          + " lk where lk." + ListenkandidaturDBA.ID_LISTE + "=?" + " order by " + ListenkandidaturDBA.LISTENPLATZ, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          new Object[]{id_Liste});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find all list candidatures by party
   * 
   * @param id_Gruppe
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindDistinctPersonendatenByGruppe(String id_Gruppe)
      throws FinderException {
    try {
      return retrieveIDs("select max(" + ListenkandidaturDBA.ID_LISTENKANDIDATUR + ") from " //$NON-NLS-1$ //$NON-NLS-2$
          + TABLENAME + " lk, " + ListeDBA.TABLENAME + " l where l." + ListeDBA.ID_GRUPPE //$NON-NLS-1$ //$NON-NLS-2$
          + "=? and l." + ListeDBA.ID_LISTE + "=lk." + ListenkandidaturDBA.ID_LISTE //$NON-NLS-1$ //$NON-NLS-2$
          + " group by lk." + ListenkandidaturDBA.ID_PERSONENDATEN, //$NON-NLS-1$
          new Object[]{id_Gruppe});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

}