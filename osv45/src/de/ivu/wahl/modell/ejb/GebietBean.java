/*
 * GebietBean
 * 
 * Copyright (c) 2002-2006 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb;

import static de.ivu.ejb.fw.DBABase.retrieveIDs;
import static de.ivu.wahl.WahlInfo.getWahlInfo;
import static de.ivu.wahl.modell.impl.BasicGebietDBA.ERFASSUNGSEINHEIT;
import static de.ivu.wahl.modell.impl.BasicGebietDBA.GEBIETSART;
import static de.ivu.wahl.modell.impl.BasicGebietDBA.GUIEINGABEERLAUBT;
import static de.ivu.wahl.modell.impl.BasicGebietDBA.ID_GEBIET;
import static de.ivu.wahl.modell.impl.BasicGebietDBA.ID_UEBERGEORDNETESGEBIET;
import static de.ivu.wahl.modell.impl.BasicGebietDBA.ID_WAHL;
import static de.ivu.wahl.modell.impl.BasicGebietDBA.NUMMER;
import static de.ivu.wahl.modell.impl.BasicGebietDBA.POSITION;
import static de.ivu.wahl.modell.impl.BasicGebietDBA.TABLENAME;
import static de.ivu.wahl.modell.impl.BasicGebietDBA.WAHLEINHEIT;
import static de.ivu.wahl.modell.impl.BasicGebiet_GebietDBA.ID_ELTERNGEBIET;
import static de.ivu.wahl.modell.impl.BasicGebiet_GebietDBA.ID_UNTERGEBIET;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.impl.BasicGebiet_GebietDBA;
import de.ivu.wahl.modell.impl.BasicGebietsstatusDBA;
import de.ivu.wahl.modell.impl.ErgebniseingangDBA;
import de.ivu.wahl.modell.impl.Gebiet_GebietDBA;

/**
 * Implementation of the entity region, that is part of the meta data of the election. Also contains
 * region specific logic to process the entry of the result on the particular instance of the region
 * 
 * @author SMA@ivu.de, IVU Traffic Technologies AG
 */
public class GebietBean extends BasicGebietBean {
  private static final long serialVersionUID = 2939166112529421837L;

  /** Logger */
  private static final Category LOGGER = Log4J.configure(GebietBean.class);

  /** Format f�r die Ausgabe der Kann-Fehler */
  private static final NumberFormat NF_GER = NumberFormat.getNumberInstance(Locale.GERMANY);

  static {
    LOGGER.info(Log4J.dumpVersion(GebietBean.class, Log4J.extractVersion("$Revision: 1.40 $"))); //$NON-NLS-1$
    // Anzahl der Nachkommastellen f�r Ausgabe der Kannfehler
    NF_GER.setMaximumFractionDigits(4);
    NF_GER.setMinimumFractionDigits(4);
  }

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
  public Collection<String> ejbFindAllByWahlAndWahlergebnisartGeaendertSeitErgebniseingang(String id_Wahl,
      int wahlergebnisart,
      String id_Ergebniseingang) throws FinderException {

    try {
      Set<String> gebiete = new HashSet<String>();
      if (id_Ergebniseingang != null) {
        Object[] parameter = new Object[]{wahlergebnisart, id_Ergebniseingang, id_Wahl};
        gebiete.addAll(retrieveIDs("select distinct g." + ID_GEBIET + " from " //$NON-NLS-1$ //$NON-NLS-2$
            + ErgebniseingangDBA.TABLENAME + " ee," + TABLENAME + " g" //$NON-NLS-1$ //$NON-NLS-2$
            + " where ee.wahlergebnisart=? and ee.ID_Ergebniseingang>? " + " and g." + ID_GEBIET //$NON-NLS-1$ //$NON-NLS-2$
            + "=ee.ID_Erfassungseinheit " + " and ee.ID_Wahl = ? ", parameter)); //$NON-NLS-1$ //$NON-NLS-2$
        gebiete.addAll(retrieveIDs("select distinct g." + ID_GEBIET + " from " + TABLENAME + " g, " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            + BasicGebietsstatusDBA.TABLENAME + " gs " + " where wahlergebnisart=? " //$NON-NLS-1$ //$NON-NLS-2$
            + " and ID_Ergebniseingang>? " + " and gs.ID_Gebiet = g.ID_Gebiet " //$NON-NLS-1$ //$NON-NLS-2$
            + " and g.ID_Wahl = ?", parameter)); //$NON-NLS-1$
      } else {
        Object[] parameter = new Object[]{wahlergebnisart, id_Wahl};
        gebiete.addAll(retrieveIDs("select distinct g." + ID_GEBIET + " from " //$NON-NLS-1$ //$NON-NLS-2$
            + ErgebniseingangDBA.TABLENAME + " ee," + TABLENAME + " g" //$NON-NLS-1$//$NON-NLS-2$
            + " where ee.wahlergebnisart=? and g.ID_Gebiet=ee.ID_Erfassungseinheit " //$NON-NLS-1$
            + " and ee.ID_Wahl = ? ", parameter)); //$NON-NLS-1$
        gebiete.addAll(retrieveIDs("select distinct g." + ID_GEBIET + " from " + TABLENAME + " g, " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            + BasicGebietsstatusDBA.TABLENAME + " gs " + " where wahlergebnisart=?" + " and gs." //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            + BasicGebietsstatusDBA.ID_GEBIET + " = g." + ID_GEBIET + " and g.ID_Wahl = ?", //$NON-NLS-1$ //$NON-NLS-2$
            parameter));
      }
      return gebiete;
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find region by election, kind of region and number of region
   * 
   * @param id_Wahl
   * @param gebietsart
   * @param nummer
   * @return
   * @throws FinderException
   */
  public String ejbFindByWahlAndGebietsartAndNummer(String id_Wahl, int gebietsart, int nummer)
      throws FinderException {

    try {
      return findSingle(retrieveIDs("select " + ID_GEBIET + " from " + TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ID_WAHL + " =? and " + GEBIETSART + "=? and " + NUMMER + "=?", new Object[]{id_Wahl, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          gebietsart, nummer}));
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find all regions of an election by the kind of region
   * 
   * @param id_Wahl
   * @param gebietsart
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByWahlAndGebietsart(String id_Wahl, int gebietsart)
      throws FinderException {

    try {
      return retrieveIDs("select " + ID_GEBIET + " from " + TABLENAME + " where " + ID_WAHL //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + "=? and " + GEBIETSART + "=?", new Object[]{id_Wahl, gebietsart}); //$NON-NLS-1$ //$NON-NLS-2$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find all regions by election and kind of election
   * 
   * @param id_Wahl
   * @param gebietsart
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByWahlAndGebietsartSortByPosition(String id_Wahl,
      int gebietsart) throws FinderException {

    try {
      return retrieveIDs("select " + ID_GEBIET + " from " + TABLENAME //$NON-NLS-1$ //$NON-NLS-2$
          + " where ID_Wahl=? and Gebietsart=? order by Position", //$NON-NLS-1$
          new Object[]{id_Wahl, gebietsart});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find all regions by election and kind of election
   * 
   * @param gebietsart
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByGebietsartSortByPosition(int gebietsart)
      throws FinderException {

    try {
      return retrieveIDs("select " + ID_GEBIET + " from " + TABLENAME //$NON-NLS-1$ //$NON-NLS-2$
          + " where Gebietsart=? order by Position", //$NON-NLS-1$
          new Object[]{gebietsart});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find all regions by superior region, sort by number
   * 
   * @param id_UebergeordnetesGebiet
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByUebergeordnetesGebietSortByNummer(String id_UebergeordnetesGebiet)
      throws FinderException {

    try {
      return retrieveIDs("select " + ID_GEBIET + " from " + TABLENAME //$NON-NLS-1$ //$NON-NLS-2$
          + " where ID_UebergeordnetesGebiet=? " + " order by " + NUMMER, //$NON-NLS-1$ //$NON-NLS-2$
          new Object[]{id_UebergeordnetesGebiet});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find all regions by superior region, sort by number kind of region and position
   * 
   * @param id_UebergeordnetesGebiet
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByUebergeordnetesGebietSortByPosition(String id_UebergeordnetesGebiet)
      throws FinderException {

    try {
      return retrieveIDs("select " + ID_GEBIET + " from " + TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ID_UEBERGEORDNETESGEBIET + "=? " + " order by " + POSITION, //$NON-NLS-1$ //$NON-NLS-2$ 
          new Object[]{id_UebergeordnetesGebiet});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find all child regions of the given superior region
   * 
   * @param id_UebergeordnetesGebiet
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllErwartetByUebergeordnetesGebiet(String id_UebergeordnetesGebiet)
      throws FinderException {

    try {
      String query = "select " + ID_GEBIET + " from " + TABLENAME + " g where " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ID_UEBERGEORDNETESGEBIET + "=? and (exists (select * from " + TABLENAME + " join " //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGebiet_GebietDBA.TABLENAME + " on " + ID_GEBIET + "=" + ID_UNTERGEBIET //$NON-NLS-1$ //$NON-NLS-2$
          + "  where " + ID_ELTERNGEBIET + "=g." + ID_GEBIET + " and " + WAHLEINHEIT + " !=0 and (" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
          + GUIEINGABEERLAUBT + "!=0)) " + " or not exists (select * from " + TABLENAME + " join " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + BasicGebiet_GebietDBA.TABLENAME + " on " + ID_GEBIET + "=" + ID_UNTERGEBIET //$NON-NLS-1$ //$NON-NLS-2$
          + "  where " + ID_ELTERNGEBIET + "=g." + ID_GEBIET + " and " + WAHLEINHEIT + " !=0 ) " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
          + ")"; //$NON-NLS-1$
      return retrieveIDs(query, new Object[]{id_UebergeordnetesGebiet});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find all regions in hierarchy by region and kind of region starting with the given region
   * 
   * @param id_Gebiet
   * @param gebietsart
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllErwartetInHierarchieByGebietAndGebietsart(String id_Gebiet,
      int gebietsart) throws FinderException {

    try {
      return retrieveIDs("select " + ID_GEBIET + " from " + TABLENAME + " g join " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + BasicGebiet_GebietDBA.TABLENAME + " on " + ID_GEBIET + "=" + ID_UNTERGEBIET + " where " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ID_ELTERNGEBIET + "=? and " + GEBIETSART + "=? and (" + "exists (select * from " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + TABLENAME + " join " + BasicGebiet_GebietDBA.TABLENAME + " on " + ID_GEBIET + "=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ID_UNTERGEBIET + " where " + ID_ELTERNGEBIET + "=g." + ID_GEBIET + " and " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + WAHLEINHEIT + " !=0 and (" + GUIEINGABEERLAUBT + "!=0))" + " or " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + "exists (select * from " + TABLENAME + " join " + BasicGebiet_GebietDBA.TABLENAME //$NON-NLS-1$ //$NON-NLS-2$
          + " on " + ID_GEBIET + "=" + ID_ELTERNGEBIET + " where " + ID_UNTERGEBIET + "=g." //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
          + ID_GEBIET + " and " + WAHLEINHEIT + " !=0 and (" + GUIEINGABEERLAUBT + "!=0))" + ")", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
          new Object[]{id_Gebiet, gebietsart});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find all regions in hierarchy beginning with the given region, that comply with the given kind
   * of region
   * 
   * @param id_Gebiet
   * @param gebietsart
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllInHierarchieByGebietAndGebietsart(String id_Gebiet,
      int gebietsart) throws FinderException {

    try {
      return retrieveIDs("select " + ID_GEBIET + " from " + TABLENAME + " join " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + BasicGebiet_GebietDBA.TABLENAME + " on " + ID_GEBIET + "=" + ID_UNTERGEBIET //$NON-NLS-1$ //$NON-NLS-2$
          + "  where " + ID_ELTERNGEBIET + "=? and " + GEBIETSART + "=?", new Object[]{id_Gebiet, //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
          gebietsart});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find all regions belonging to the superior region while being an election unit
   * 
   * @param id_UebergeordnetesGebiet
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllIsWahleinheitAusstehendByUebergeordnetesGebiet(String id_UebergeordnetesGebiet)
      throws FinderException {
    try {

      return retrieveIDs("select " //$NON-NLS-1$
          + ID_GEBIET
          + " from " //$NON-NLS-1$
          + TABLENAME
          + " join Gebiet_Gebiet on " //$NON-NLS-1$
          + ID_GEBIET
          + " = ID_Untergebiet " //$NON-NLS-1$
          + " where ID_Elterngebiet = ? " //$NON-NLS-1$
          + " and Wahleinheit != 0 " //$NON-NLS-1$
          + " and (Nachwahlgrund = 0 or EingabeErlaubt != 0 ) " //$NON-NLS-1$
          + " and  ID_Gebiet not in (select ID_Gebiet " //$NON-NLS-1$
          + " from Gebiet_Ergebniseingang left outer join Ergebniseingang on (Ergebniseingang.ID_Ergebniseingang=Gebiet_Ergebniseingang.ID_Ergebniseingang) " //$NON-NLS-1$
          + " where Status = 0 ) " + " order by Nummer", //$NON-NLS-1$ //$NON-NLS-2$

          new Object[]{id_UebergeordnetesGebiet});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Fill table Gebiet_Gebiet
   * 
   * @throws EJBException when errors with database occur
   */
  public void ejbHomeInitGebietGebiet() throws EJBException {
    try {
      Gebiet_GebietDBA.init();
    } catch (SQLException se) {
      throw new EJBException(se.getMessage(), se);
    }
  }

  /**
   * Get information about completeness of the region for actual kind of election result
   * 
   * @return
   * @throws EJBException
   */
  public boolean isVollstaendig() throws EJBException {
    Gebietsstatus currentGebietsstatus = getCurrentGebietsstatus();
    return currentGebietsstatus != null && currentGebietsstatus.isVollstaendig();
  }

  /**
   * Get information, whether region is election region
   * 
   * @return
   * @throws EJBException
   */
  public boolean isWahlgebiet() throws EJBException {
    return this.getID_Gebiet().equals(getWahl().getID_Wahlgebiet());
  }

  /**
   * Give back information for actual state of region (for actual kind of election result)
   * 
   * @return
   * @throws EJBException
   */
  public Gebietsstatus getCurrentGebietsstatus() throws EJBException {
    Ergebniseingang letzterGueltiger = getLetzterGueltigerEingang();
    GebietsstatusHome gebietsstatusHome = GebietsstatusHome.HomeFinder.findHome(this);
    if (letzterGueltiger != null) {
      return findGebietsstatusByLetzterGueltigerEE(letzterGueltiger, gebietsstatusHome);
    } else {
      try {
        return findGebietsstatusByWahlergebnisart(getWahl().getAktuelleWahlergebnisart(),
            gebietsstatusHome);
      } catch (FinderException e) {
        throw new EJBException(e);
      }
    }
  }

  /**
   * Get information about actual state of region (for given kind of election result)
   * 
   * @param wahlergebnisart
   * @return
   * @throws EJBException
   */
  public Gebietsstatus getCurrentGebietsstatus(int wahlergebnisart) throws EJBException {
    Ergebniseingang letzterGueltiger = getLetzterGueltigerEingang(wahlergebnisart);
    GebietsstatusHome gebietsstatusHome = GebietsstatusHome.HomeFinder.findHome(this);
    if (letzterGueltiger != null) {
      return findGebietsstatusByLetzterGueltigerEE(letzterGueltiger, gebietsstatusHome);
    } else {
      try {
        return findGebietsstatusByWahlergebnisart(wahlergebnisart, gebietsstatusHome);
      } catch (FinderException e) {
        throw new EJBException(e);
      }
    }
  }

  private Gebietsstatus findGebietsstatusByLetzterGueltigerEE(Ergebniseingang letzterGueltiger,
      GebietsstatusHome gebietsstatusHome) {
    try {
      return gebietsstatusHome.findByErgebniseingangAndGebiet((String) letzterGueltiger
          .getPrimaryKey(), _details.getID_Gebiet());
    } catch (ObjectNotFoundException onfe) {
      return null;
    } catch (FinderException e) {
      throw new EJBException(e);
    }
  }

  private Gebietsstatus findGebietsstatusByWahlergebnisart(int wahlergebnisart,
      GebietsstatusHome gebietsstatusHome) throws FinderException {
    Collection<Gebietsstatus> gsCol = gebietsstatusHome
        .findAllByWahlergebnisartAndGebiet(wahlergebnisart, _details.getID_Gebiet());
    if (gsCol.isEmpty()) {
      return null;
    } else {
      return gsCol.iterator().next();
    }
  }

  /**
   * Get information about the state of the region for the given result
   * 
   * @param ergebniseingang
   * @return
   * @throws EJBException
   */
  public Gebietsstatus getGebietsstatusForErgebniseingang(Ergebniseingang ergebniseingang)
      throws EJBException {

    if (ergebniseingang != null) {
      try {
        return GebietsstatusHome.HomeFinder.findHome(this)
            .findByErgebniseingangAndGebiet((String) ergebniseingang.getPrimaryKey(),
                _details.getID_Gebiet());
      } catch (ObjectNotFoundException onfe) {
        return null;
      } catch (FinderException e) {
        throw new EJBException(e);
      }
    }
    return null;
  }

  /**
   * Return all direct children of this region
   * 
   * @throws EJBException
   */
  public Collection<Gebiet> getGebietErwartetCol() throws EJBException {
    try {
      return ((GebietHome) _ctx.getEJBLocalHome()).findAllErwartetByUebergeordnetesGebiet(_details
          .getID_Gebiet());
    } catch (FinderException fe) {
      throw new EJBException(fe);
    }
  }

  /**
   * Get last valid entry for this region for actual kind of election result
   * 
   * @return
   * @throws EJBException
   */
  public Ergebniseingang getLetzterGueltigerEingang() throws EJBException {
    return getLetzterGueltigerEingang(getWahl().getAktuelleWahlergebnisart());
  }

  /**
   * Get last valid entry for this region for a certain kind of election
   * 
   * @param wahlergebnisart
   * @return
   * @throws EJBException
   */
  public Ergebniseingang getLetzterGueltigerEingang(int wahlergebnisart) throws EJBException {
    try {
      return ((ErgebniseingangHome) findLocalHome("Ergebniseingang")) //$NON-NLS-1$
          .findByGebietAndWahlergebnisart(getID_Gebiet(), wahlergebnisart);
    } catch (ObjectNotFoundException onfe) {
      return null;
    } catch (FinderException fe) {
      throw new EJBException(fe);
    }
  }

  /**
   * Get last valid entry for this region for first input
   * 
   * @return
   * @throws EJBException
   */
  public Ergebniseingang getLastValidFirstInput(int wahlergebnisart) throws EJBException {
    try {
      return ((ErgebniseingangHome) findLocalHome("Ergebniseingang")) //$NON-NLS-1$
          .findLastValidFirstInputByGebietAndWahlergebnisart(getID_Gebiet(), wahlergebnisart);
    } catch (ObjectNotFoundException onfe) {
      return null;
    } catch (FinderException fe) {
      throw new EJBException(fe);
    }
  }

  /**
   * The latest voting result for this region and this input mode
   * 
   * @param wahlergebnisart
   * @return
   * @throws EJBException
   */
  public Ergebniseingang getLastInput(int wahlergebnisart) throws EJBException {
    try {
      return ((ErgebniseingangHome) findLocalHome("Ergebniseingang")).findLastInputByGebietAndWahlergebnisart(getID_Gebiet(), wahlergebnisart); //$NON-NLS-1$
    } catch (ObjectNotFoundException onfe) {
      return null;
    } catch (FinderException fe) {
      throw new EJBException(fe);
    }
  }

  /**
   * Set last valid entry for this region for the actual kind of election result
   * 
   * @param ergebniseingang
   * @throws EJBException
   */
  public void setLetzterGueltigerEingang(Ergebniseingang ergebniseingang) throws EJBException {
    Ergebniseingang vorigerEingang = getLetzterGueltigerEingang();
    if (vorigerEingang != null) {
      removeErgebniseingang(vorigerEingang);
    }
    if (ergebniseingang != null) {
      addErgebniseingang(ergebniseingang);
    }
  }

  /**
   * Set last valid entry for this region for the explicit given kind of election result
   * 
   * @param wahlergebnisart
   * @param ergebniseingang
   * @throws EJBException
   */
  public void setLetzterGueltigerEingang(int wahlergebnisart, Ergebniseingang ergebniseingang)
      throws EJBException {

    if (ergebniseingang != null && ergebniseingang.getWahlergebnisart() != wahlergebnisart) {
      throw new IllegalArgumentException(
          Messages
              .getString(MessageKeys.Error_DieAngegebeneWahlergebnisartMussMitDerWahlergebnisartDesEingangsUebereinstimmen));
    }
    Ergebniseingang vorigerEingang = getLetzterGueltigerEingang(wahlergebnisart);
    if (vorigerEingang != null) {
      removeErgebniseingang(vorigerEingang);
    }
    if (ergebniseingang != null) {
      addErgebniseingang(ergebniseingang);
    }
  }

  /**
   * Get the number of the expected regions in a certain kind of region, that lie behind this region
   * (including the region itself)
   * 
   * @param gebietsart
   * @return
   * @throws EJBException
   */
  public int getAnzahlErwartetByGebietsart(int gebietsart) throws EJBException {
    try {
      return ((GebietHome) _ctx.getEJBLocalHome())
          .findAllErwartetInHierarchieByGebietAndGebietsart(_details.getID_Gebiet(), gebietsart)
          .size();
    } catch (FinderException fe) {
      throw new EJBException(fe);
    }
  }

  /**
   * Get all regions in the hierarchy by it's kind of region
   * 
   * @param gebietsart
   * @return
   * @throws EJBException
   */
  public Collection<Gebiet> getAllGebieteInHierarchieByGebietsart(int gebietsart)
      throws EJBException {
    try {
      return ((GebietHome) _ctx.getEJBLocalHome())
          .findAllInHierarchieByGebietAndGebietsart(_details.getID_Gebiet(), gebietsart);
    } catch (FinderException fe) {
      throw new EJBException(fe);
    }
  }

  /*
   * (non-Javadoc) Get the plaintext of the kind of region from the intern table
   * @see de.ivu.wahl.modell.GebietModel#getGebietsartKlartext()
   */
  public String getGebietsartKlartext() {
    return _details.getGebietsartKlartext();
  }

  /*
   * (non-Javadoc) Get a technical but human readable identification for the region
   * @see de.ivu.wahl.modell.GebietModel#getBezeichnung()
   */
  public String getBezeichnung() {
    return _details.getBezeichnung();
  }

  /**
   * Find all detect units of an election
   * 
   * @param id_Wahl
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllIsErfassungseinheitByWahl(String id_Wahl)
      throws FinderException {
    try {
      return retrieveIDs("select " + ID_GEBIET + " from " + TABLENAME + " where " + ID_WAHL //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + "=? and " + ERFASSUNGSEINHEIT + "!=0", new Object[]{id_Wahl}); //$NON-NLS-1$ //$NON-NLS-2$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find all detect units of an election ordered by kind of region and number
   * 
   * @param id_Wahl
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllIsErfassungseinheitByWahlOrderByGebietsartAndNummer(String id_Wahl)
      throws FinderException {
    try {
      return retrieveIDs("select " + ID_GEBIET + " from " + TABLENAME + " where " + ID_WAHL //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + "=? and " + ERFASSUNGSEINHEIT + "!=0 " + " order by " + GEBIETSART + ", " + NUMMER, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
          new Object[]{id_Wahl});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Get analysis unit of the region
   * 
   * @return
   */
  public Gebiet getAuswertungseinheit() {
    int gebietsartAwE = getWahlInfo(_details.getID_Wahl()).getGebietsartAuswertungseinheit();
    if (gebietsartAwE == _details.getGebietsart()) {
      return (Gebiet) _ctx.getEJBLocalObject();
    }
    Gebiet gebiet = getUebergeordnetesGebiet();
    while (gebiet != null) {
      if (gebiet.getGebietsart() == gebietsartAwE) {
        return gebiet;
      }
      gebiet = gebiet.getUebergeordnetesGebiet();
    }
    return null;
  }

  /**
   * Find all regions, which are election units too, order by number of region
   * 
   * @param id_Wahl
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllIsWahleinheitByWahlOrderByNummer(String id_Wahl)
      throws FinderException {

    try {
      return retrieveIDs("select " + ID_GEBIET + " from " + TABLENAME + " where " + WAHLEINHEIT //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + "!=0 " + " and " + ID_WAHL + "=? " + " order by " + NUMMER, new Object[]{id_Wahl}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * @return roman numbers if needed
   */
  public String getGebietsnummerAnzeige() {
    String nrString = String.valueOf(getNummer());
    if (isRoemisch()) {
      if (getNummer() == 1) {
        nrString = "I"; //$NON-NLS-1$
      }
      if (getNummer() == 2) {
        nrString = "II"; //$NON-NLS-1$
      }
    }
    return nrString;
  }

  /**
   * @return true if lists are nominated for this region or a superior region
   */
  public boolean hasLists() {
    return getWahlInfo().getGebietsartMitListen() >= getGebietsart();
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.modell.GebietModel#getNumber4Display()
   */
  @Override
  public String getNumber4Display() {
    return _details.getNumber4Display();
  }
}