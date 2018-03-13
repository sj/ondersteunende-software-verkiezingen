/*
 * AdminHandlingBean
 * 
 * Copyright (c) 2002-8 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.admin;

import static de.ivu.wahl.Konstanten.PROP_INPUTDISABLE;
import static de.ivu.wahl.Konstanten.PROP_REPENABLE;
import static de.ivu.wahl.Konstanten.PROP_REPNR;
import static de.ivu.wahl.Konstanten.PROP_REPNRIN;
import static de.ivu.wahl.Konstanten.RECHTE_LAST_CHANGE;
import static de.ivu.wahl.Konstanten.SYSTEM_ANWENDER;
import static de.ivu.wahl.Konstanten.SYSTEM_ANWENDERID;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.Local;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.apache.log4j.Category;

import de.ivu.ejb.EJBUtil;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.AnwContext;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.WahlStatelessSessionBeanBase;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ErgebniseingangKonstanten;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.RechtegruppeModel;
import de.ivu.wahl.modell.ejb.Ergebniseingang;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifisch;
import de.ivu.wahl.modell.ejb.Personendaten;
import de.ivu.wahl.modell.ejb.PersonendatenHome;
import de.ivu.wahl.modell.ejb.RechtegruppeHome;
import de.ivu.wahl.modell.ejb.Wahl;
import de.ivu.wahl.modell.ejb.WahlHome;

/**
 * SessionBean f�r Administrationsunterst�tzung
 * <P>
 * Enth�lt auch Methoden f�r das Handling von Schwellwerten, die aus der Applikation verwendet
 * werden. Zus�tzlich finden sich weitere n�tzlichen Methoden, die kein "nat�rliches" Pl�tzchen
 * gefunden haben
 * 
 * @author cos@ivu.de klie@ivu.de hil@ivu.de
 */
@Stateless
@Local(AdminHandling.class)
public class AdminHandlingBean extends WahlStatelessSessionBeanBase implements AdminHandling {
  private static final long serialVersionUID = -5234628662533525779L;
  private static final Category LOGGER = Log4J.configure(AdminHandlingBean.class);
  public static final int HOCHRECHNUNG = 1;
  public static final int VORPERIODE = 2;
  public static final int ZUFALL = 3;

  @EJB
  private PropertyHandling _propertyHandling;

  @EJB
  private SchwellwertHandling _schwellwertHandling;

  static {
    LOGGER.info(Log4J.dumpVersion(AdminHandlingBean.class,
        Log4J.extractVersion("$Revision: 1.33 $"))); //$NON-NLS-1$
  }

  public boolean isZuruecksetzenErlaubtAnwender(AnwContext c, int gebietsart, int nummer)
      throws EJBException {

    Gebiet erfassungseinheit = getErfassungseinheit(c, gebietsart, nummer);
    return !erfassungseinheit.getUebergeordnetesGebiet().isVollstaendig()
        && erfassungseinheit.isVollstaendig();
  }

  public void setPersonendatenBenennbar(String idPersonendaten, boolean benennbar)
      throws EJBException {
    try {
      Personendaten personendaten = PersonendatenHome.HomeFinder.findHome(this)
          .findByPrimaryKey(idPersonendaten);
      personendaten.setBenennbar(benennbar);
    } catch (FinderException e) {
      throw new EJBException(Messages.getString(MessageKeys.Error_FehlerBeimPersonendatenSetzen), e);
    }
  }

  public void setVoteValue(String idRegion, int voteValue) throws EJBException {
    try {
      Gebiet region = getGebietHome().findByPrimaryKey(idRegion);
      region.setVoteValue(voteValue);
    } catch (FinderException e) {
      throw new EJBException(Messages.getString(MessageKeys.Error_ErrorSettingVoteValue), e);
    }
  }

  public void setGebietNameUndWahlberechtigte(String idGebiet, String name, int eligible_voter)
      throws EJBException {
    try {
      Gebiet gebiet = getGebietHome().findByPrimaryKey(idGebiet);
      if (name != null) {
        gebiet.setName(name);
      }
      gebiet.setWahlberechtigte(eligible_voter);
    } catch (FinderException e) {
      throw new EJBException(Messages.getString(MessageKeys.Error_FehlerBeimGebietdatenSetzen), e);
    }
  }

  public void setWahlStatus(String idWahl, int status) throws EJBException {
    try {
      Wahl wahl = WahlHome.HomeFinder.findHome(this).findByPrimaryKey(idWahl);
      wahl.setStatus(status);
    } catch (FinderException e) {
      throw new EJBException(Messages.getString(MessageKeys.Error_FehlerBeimWahldatenSetzen), e);
    }
  }

  /*
   * Used in initial administration of polling stations (non-Javadoc)
   * @see de.ivu.wahl.admin.AdminHandling#deleteGebiet(java.lang.String)
   */
  public void deleteGebiet(String idGebiet) throws EJBException {
    try {
      Gebiet gebiet = getGebietHome().findByPrimaryKey(idGebiet);
      for (Gebiet supRegion : gebiet.getElterngebietCol()) {
        gebiet.removeElterngebiet(supRegion);
      }
      // remove references first
      for (Gebiet refRegion : gebiet.getGebietCol()) {
        refRegion.setUebergeordnetesGebiet(null);
      }
      for (Iterator<GruppeGebietsspezifisch> ggIt = gebiet.getGruppeGebietsspezifischCol()
          .iterator(); ggIt.hasNext();) {
        ggIt.next().remove();
      }
      gebiet.remove();
    } catch (RemoveException e) {
      throw new EJBException(Messages.getString(MessageKeys.Error_FehlerBeimLoeschenDesGebietes), e);
    } catch (FinderException e) {
      throw new EJBException(Messages.getString(MessageKeys.Error_FehlerBeimLoeschenDesGebietes), e);
    }
  }

  public boolean existsErgebniseingang(AnwContext c, int gebietsart, int nummer)
      throws EJBException {
    Gebiet erf = getErfassungseinheit(c, gebietsart, nummer);
    if (erf == null) {
      return false;
    }

    Ergebniseingang ergebniseingang = erf.getLetzterEingang();
    LOGGER.info("--> Region detect unit  --> " + erf.getName()); //$NON-NLS-1$
    if (ergebniseingang != null) {
      LOGGER.info("--> result --> " + ergebniseingang.getWahl()); //$NON-NLS-1$
      LOGGER.info("--> result state --> " + ergebniseingang.getStatus()); //$NON-NLS-1$
    }
    return ergebniseingang != null;
  }

  public boolean existWarnungenOderFehler(AnwContext c) throws EJBException {
    try {
      WahlInfo.getWahlInfo(c).getWahl().readLock();

      for (Gebiet ea : getGebietHome().findAllIsErfassungseinheitByWahl(c.getID_Wahl())) {
        // todo pr�fen, ob das der letzte Eingang f�r die eingestellte
        // Wahlergebnisart sein soll
        Ergebniseingang ee = ea.getLetzterEingang();

        if (ee != null) {
          int status = ee.getStatus();
          if (status != ErgebniseingangKonstanten.STATE_OK) {
            return true;
          }
        }
      }
      return false;
    } catch (Exception e) {
      throw new EJBException(Messages.getString(MessageKeys.Error_GingNcht), e);
    }
  }

  public Collection<GebietModel> getGebiete(AnwContext c, int gebietsart) {
    try {
      return toModelList(getGebietHome().findAllByWahlAndGebietsart(c.getID_Wahl(), gebietsart));
    } catch (Exception ex) {
      LOGGER.info(Messages.getString(MessageKeys.Error_getGebieteKonnteNichtAusgefuehrtWerden));
      return null;
    }
  }

  public Collection<GebietModel> getGebiete(int gebietsart) {
    try {
      return toModelList(getGebietHome().findAllByGebietsartSortByPosition(gebietsart));
    } catch (Exception ex) {
      LOGGER.info(Messages.getString(MessageKeys.Error_getGebieteKonnteNichtAusgefuehrtWerden));
      return null;
    }
  }

  public GebietModel getGebietModel(AnwContext context, int gebietart, int gebietnr)
      throws EJBException {
    try {
      return getGebietHome().findByWahlAndGebietsartAndNummer(context.getID_Wahl(),
          gebietart,
          gebietnr).getDetails();
    } catch (Exception e) {
      LOGGER.error(e);
      throw new EJBException(e);
    }
  }

  public GebietModel getGebietModelParent(AnwContext context, int gebietart, int gebietnr)
      throws EJBException {
    return WahlInfo.getWahlInfo(context).getModelUebergeordnet4Gebiet(gebietart, gebietnr);
  }

  public String getProperty(String name) throws EJBException {
    return _propertyHandling.getProperty(name);
  }

  public Collection<RechtegruppeModel> getRechtegruppe() throws EJBException {
    try {
      return toModelList(RechtegruppeHome.HomeFinder.findHome(this).findAll());
    } catch (Exception ex) {
      throw new EJBException(
          Messages.getString(MessageKeys.Error_getRechtegruppeKonnteNichtAusgefuehrtWerden), ex);
    }
  }

  /**
   * @param c Anwenderkontext
   * @param gebietsart Gebietsart des gesuchten Gebiets
   * @param nummer Gebietsnummer des gesuchten Gebiets
   * @return Erfassungseinheit zu den �bergebenen Gebietskenndaten, oder <code>null</code> wenn es
   *         sich um keine Erfassungseinheit handelt
   * @throws EJBException genereller Fehler
   */
  private Gebiet getErfassungseinheit(AnwContext c, int gebietsart, int nummer) throws EJBException {
    try {
      Gebiet g = getGebietHome().findByWahlAndGebietsartAndNummer(c.getID_Wahl(),
          gebietsart,
          nummer);
      if (!g.isErfassungseinheit()) {
        return null;
      }
      return g;
    } catch (Exception ex) {
      throw new EJBException(
          Messages.getString(MessageKeys.Error_ErfassungseinheitKonnteNichtGefundenWerden), ex);
    }
  }

  public void initAdministrationValues(String id_Wahl) throws EJBException {
    try {
      if (WahlHome.HomeFinder.findHome(this).findAll().size() == 1) {
        // Wahl unabh�ngige Properties, nur bei der ersten importierten Wahl
        _propertyHandling.setBooleanProperty(PROP_REPENABLE, true);
        _propertyHandling.setBooleanProperty(PROP_INPUTDISABLE, false);
        _propertyHandling.setLongProperty(RECHTE_LAST_CHANGE, 0);

        _propertyHandling.setIntProperty(PROP_REPNR, 0);
        _propertyHandling.setIntProperty(PROP_REPNRIN, 0);
      }
    } catch (FinderException e) {
      throw new EJBException(e);
    }

    // Wahl abh�ngige Properties
    AnwContext c = new AnwContext(id_Wahl, SYSTEM_ANWENDERID, SYSTEM_ANWENDER, null, null);

    _schwellwertHandling.initSchwellwerte(c);

    getGebietHome().initGebietGebiet();
  }

  public void setProperty(AnwContext c, String name, boolean val) throws EJBException {
    writeAppLog(c, Messages.bind(MessageKeys.Error_SetzenDerProperty_0_auf_1, name, val));
    _propertyHandling.setBooleanProperty(name, val);
  }

  public void setProperty(AnwContext c, String name, String val) throws EJBException {
    writeAppLog(c, Messages.bind(MessageKeys.Error_SetzenDerProperty_0_auf_1, name, val));
    _propertyHandling.setProperty(name, val);
  }

  @Override
  public void writeAppLog(AnwContext c, String message) {
    // keine neue Funktion, nur Sichtbarkeits�nderung
    super.writeAppLog(c, message);
  }

  public Collection<GebietModel> getWahleinheiten(AnwContext c) throws EJBException {
    try {
      return toModelList(getGebietHome().findAllIsWahleinheitByWahlOrderByNummer(c.getID_Wahl()));
    } catch (EJBException e) {
      throw e;
    } catch (FinderException e) {
      throw new EJBException(e);
    }
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.admin.AdminHandling#updatePollingStations(List)
   */
  public void updatePollingStations(Collection<GebietModel> pollingStations) {
    try {
      // Sort by number
      List<GebietModel> pollingStationsSorted = new ArrayList<GebietModel>(pollingStations);
      Collections.sort(pollingStationsSorted, new Comparator<GebietModel>() {
        public int compare(GebietModel g1, GebietModel g2) {
          return Integer.signum(g1.getNummer() - g2.getNummer());
        }
      });
      int position = 1;
      for (GebietModel pollingStation : pollingStationsSorted) {
        Gebiet region;
        region = getGebietHome().findByPrimaryKey(pollingStation.getID_Gebiet());
        region.setDetails(pollingStation);
        region.setPosition(position++);
      }
    } catch (EJBException e) {
      throw e;
    } catch (FinderException e) {
      throw new EJBException(e);
    }
  }

  private static final String[] SQL_STATEMENTS = {
      "DROP INDEX stimmergebnis_index_1", //$NON-NLS-1$
      "DROP INDEX stimmergebnis_index_2", //$NON-NLS-1$
      "DROP INDEX stimmergebnis_index_3", //$NON-NLS-1$
      "DROP INDEX stimmergebnis_index_4", //$NON-NLS-1$
      "CREATE INDEX stimmergebnis_index_1 ON Stimmergebnis (ID_Ergebniseingang, ID_Gebiet, ID_GruppeGebietsspezifisch, ID_Listenkandidatur)", //$NON-NLS-1$
      "CREATE INDEX stimmergebnis_index_2 ON Stimmergebnis (ID_Ergebniseingang, ID_Gebiet, Stimmart, ID_Listenkandidatur)", //$NON-NLS-1$
      "CREATE INDEX stimmergebnis_index_3 ON Stimmergebnis (ID_Ergebniseingang, ID_Gebiet, Stimmart)", //$NON-NLS-1$
      "CREATE INDEX stimmergebnis_index_4 ON Stimmergebnis (ID_Ergebniseingang, ID_Gebiet)" //$NON-NLS-1$
  };

  @Override
  public boolean reIndexStimmergebnis() {
    boolean success = false;
    try {
      DataSource ds = (DataSource) EJBUtil.getInitialContext()
          .lookup("java:/jdbc/wahlDB-" + EJBUtil.getInstallationSpecificAffix()); //$NON-NLS-1$
      Connection connection = ds.getConnection();
      try {
        Statement statement = connection.createStatement();
        try {
          for (String line : SQL_STATEMENTS) {
            LOGGER.info(line);
            statement.execute(line);
          }
          success = true;
          LOGGER.info("Database schema successfully re-indexed"); //$NON-NLS-1$
        } finally {
          statement.close();
        }
      } catch (Exception e) {
        LOGGER.error(e, e);
      } finally {
        connection.close();
      }
    } catch (Exception e) {
      LOGGER.fatal(e);
    }

    return success;
  }

}
