/*
 * EingangHandlingBean
 * 
 * Copyright (c) 2002-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.eingang;

import static de.ivu.wahl.WahlInfo.getWahlInfo;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.ADMITTED_VOTERS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.ELECTION_NOTICES;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.GUELTIGE;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.GUELTIG_ODER_LEER;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.LEER;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.LESS_VALID_VOTES_THAN_ADMITTED_VOTERS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.MORE_VALID_VOTES_THAN_ADMITTED_VOTERS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.POLLING_CARDS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.PROXY_VOTERS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.UNGUELTIGE;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.WAEHLER;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.WAHLBERECHTIGTE;
import static de.ivu.wahl.modell.StimmergebnisModel.STIMMART_KEINE;
import static de.ivu.wahl.modell.StimmergebnisModel.STIMMART_LISTENSTIMME;
import static de.ivu.wahl.modell.exception.ImportException.TYPE_CONTENT;
import static java.lang.System.currentTimeMillis;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.wahl.AnwContext;
import de.ivu.wahl.GebietsBaum;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.SystemInfo;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.WahlStatelessSessionBeanBase;
import de.ivu.wahl.admin.StateHandling;
import de.ivu.wahl.eingang.GUIEingangMsg.Gruppendaten;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ErgebniseingangKonstanten;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.Gebietsart;
import de.ivu.wahl.modell.GebietsstatusModel;
import de.ivu.wahl.modell.GruppeKonstanten;
import de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein;
import de.ivu.wahl.modell.SchwellwertModel;
import de.ivu.wahl.modell.StimmergebnisModel;
import de.ivu.wahl.modell.WahlModel;
import de.ivu.wahl.modell.ejb.Ergebniseingang;
import de.ivu.wahl.modell.ejb.ErgebniseingangHome;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.GebietHome;
import de.ivu.wahl.modell.ejb.Gebietsstatus;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifisch;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifischHome;
import de.ivu.wahl.modell.ejb.Liste;
import de.ivu.wahl.modell.ejb.Listenkandidatur;
import de.ivu.wahl.modell.ejb.Personendaten;
import de.ivu.wahl.modell.ejb.Schwellwert;
import de.ivu.wahl.modell.ejb.SchwellwertHome;
import de.ivu.wahl.modell.ejb.Stimmergebnis;
import de.ivu.wahl.modell.ejb.StimmergebnisseUntergebieteHome;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.modell.impl.StimmergebnisModelImpl;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * SessionBean mit den wesentlichen Verarbeitungsfunktionen bei der Eingabe der Erfassungseinheiten
 * und administrativer Nachrichten �ber Dialog oder per Datei.
 * 
 * @author klie@ivu.de cos@ivu.de - IVU Traffic Technologies AG
 */
@Stateless
@Local(EingangHandling.class)
public class EingangHandlingBean extends WahlStatelessSessionBeanBase implements EingangHandling {

  private static final long serialVersionUID = 4109708050363991732L;
  private static final Category LOGGER = Log4J.configure(EingangHandlingBean.class);
  public static final String LOCKING = "[LOCKING] "; //$NON-NLS-1$

  /** Map<ID_Gebiet,ID_Anwender> holding the user ID of current input lock for a region */
  private static final Map<String, String> INPUT_LOCKS = new HashMap<String, String>();

  @EJB
  protected StateHandling _stateHandling;

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.eingang.EingangHandling#getGUIMsg(de.ivu.wahl.AnwContext, int, int, boolean)
   */
  @Override
  public GUIEingangMsg getGUIMsg(AnwContext anwContext,
      int gebietsart,
      int gebietsnummer,
      boolean forDisplay) throws EJBException {

    Gebiet gebiet = getGebiet(anwContext, gebietsart, gebietsnummer);
    return getGUIMsg(anwContext, gebiet, forDisplay);
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.eingang.EingangHandling#getGUIMsg(de.ivu.wahl.AnwContext, Gebiet, boolean)
   */
  @Override
  public GUIEingangMsg getGUIMsg(AnwContext anwContext, Gebiet gebiet, boolean forDisplay)
      throws EJBException {
    WahlInfo wahlInfo = getWahlInfo(anwContext);
    wahlInfo.getWahl().readLock();
    GUIEingangMsg msg = new GUIEingangMsg(anwContext);
    GUIEingangMsg retMsg = fillEingangMsg(msg,
        gebiet,
        forDisplay,
        false,
        wahlInfo.getAktuelleWahlergebnisart());
    return retMsg;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.eingang.EingangHandling#getGUIMsg(de.ivu.wahl.AnwContext,
   * de.ivu.wahl.modell.ejb.Gebiet, boolean, boolean)
   */
  @Override
  public GUIEingangMsg getGUIMsg(AnwContext anwContext,
      Gebiet gebiet,
      boolean forDisplay,
      boolean lastInputWithLastStatus) {
    WahlInfo wahlInfo = getWahlInfo(anwContext);
    wahlInfo.getWahl().readLock();
    GUIEingangMsg msg = new GUIEingangMsg(anwContext);
    GUIEingangMsg retMsg = fillEingangMsg(msg,
        gebiet,
        forDisplay,
        lastInputWithLastStatus,
        wahlInfo.getAktuelleWahlergebnisart());
    return retMsg;
  }

  /**
   * Read input from GUI
   * 
   * @param msg input data
   * @throws ImportException Error during import
   * @throws EJBException general error
   */
  @Override
  public void processInputMsg(EingangMsg msg) throws ImportException, EJBException {
    try {
      WahlInfo.getWahlInfo().getWahl().lock();
      processInput(msg);
    } catch (Exception e1) {
      LOGGER.error(e1, e1);
      throw new ImportException(msg.getURL(), msg.getMsgName(), 0, e1);
    }
  }

  @Override
  public String removeLock(AnwContext anwContext, String id_Gebiet) {
    final String userID;
    synchronized (INPUT_LOCKS) {
      String message = Messages.bind(MessageKeys.Logger_RemoveLockForRegionWithId_0, id_Gebiet);
      logLocking(anwContext, message);

      userID = INPUT_LOCKS.remove(id_Gebiet);
      _stateHandling.setLastChangeNow(anwContext);
    }
    return userID;
  }

  @Override
  public void removeLockForUser(AnwContext anwContext, String id_Gebiet) {
    synchronized (INPUT_LOCKS) {
      if (INPUT_LOCKS.containsKey(id_Gebiet)) {
        final String keyForViewlock = INPUT_LOCKS.get(id_Gebiet);
        if (keyForViewlock.equals(anwContext.getKeyForViewlock())) {
          // Lock is removed
          String message = Messages.bind(MessageKeys.Logger_RemoveLockForRegionWithId_0_ForUser_1,
              id_Gebiet,
              anwContext.getAnmeldename());
          logLocking(anwContext, message);

          INPUT_LOCKS.remove(id_Gebiet);
          _stateHandling.setLastChangeNow(anwContext);
          return;
        } else {
          // Lock remains
          String message = Messages
              .bind(MessageKeys.Logger_UnableToRemoveLockForRegionWithId_0_ForUser_1_StillLockedBy_2,
                  id_Gebiet,
                  anwContext.getAnmeldename(),
                  keyForViewlock);
          logLocking(anwContext, message);
        }
      }
    }
  }

  @Override
  public void removeLockForUser(AnwContext anwContext) {
    synchronized (INPUT_LOCKS) {
      final String keyForViewlock = anwContext.getKeyForViewlock();
      if (INPUT_LOCKS.containsValue(keyForViewlock)) {
        // Remove all Map Entries with value == keyForViewlock (see OSV-825)
        final Iterator<Entry<String, String>> iter = INPUT_LOCKS.entrySet().iterator();
        while (iter.hasNext()) {
          Entry<String, String> entry = iter.next();
          if (entry.getValue().equals(keyForViewlock)) {
            iter.remove();
          }
        }
        _stateHandling.setLastChangeNow(anwContext);
      }
    }
  }

  @Override
  public String lock(AnwContext anwContext, String id_Gebiet) {
    synchronized (INPUT_LOCKS) {
      if (!INPUT_LOCKS.containsKey(id_Gebiet)) {
        String message = Messages.bind(MessageKeys.Logger_LockRegionWithId_0_ForUser_1,
            id_Gebiet,
            anwContext.getAnmeldename());
        logLocking(anwContext, message);

        INPUT_LOCKS.put(id_Gebiet, anwContext.getKeyForViewlock());
        _stateHandling.setLastChangeNow(anwContext);
        return anwContext.getKeyForViewlock();
      } else {
        String keyForViewlock = INPUT_LOCKS.get(id_Gebiet);
        String message = Messages
            .bind(MessageKeys.Logger_UnableToLockRegionWithId_0_ForUser_1_StillLockedBy_2,
                id_Gebiet,
                anwContext.getAnmeldename(),
                keyForViewlock);
        logLocking(anwContext, message);
      }
    }
    return null;
  }

  @Override
  public boolean lockAllRegions(AnwContext anwContext) throws FinderException {
    synchronized (INPUT_LOCKS) {
      if (INPUT_LOCKS.isEmpty()) {
        final String keyForViewlock = anwContext.getKeyForViewlock();
        for (Gebiet gebiet : GebietHome.HomeFinder.findHome(this).findAll()) {
          String id_Gebiet = gebiet.getID_Gebiet();
          String message = Messages.bind(MessageKeys.Logger_LockRegionWithId_0_ForUser_1,
              id_Gebiet,
              anwContext.getAnmeldename());
          logLocking(anwContext, message);

          INPUT_LOCKS.put(id_Gebiet, keyForViewlock);
        }
        _stateHandling.setLastChangeNow(anwContext);
        return true;
      }
    }
    return false;
  }

  private void logLocking(AnwContext anwContext, String message) {
    writeAppLog(anwContext, LOCKING + message);
    LOGGER.info(LOCKING + message);
  }

  @Override
  public Map<String, String> getINPUT_MAP() {
    return INPUT_LOCKS;
  }

  /**
   * Convenience zum Holen eines Gebiets
   * 
   * @param anwContext Anwenderkontext f�r die Bestimmung der Wahl und der Rechte
   * @param gebietsart Gebietsart
   * @param gebietsnummer Gebietsnummer
   * @return Gebiet anhand der Gebietsart und der Gebietsnummer
   * @throws EJBException genereller Fehler
   */
  @Override
  public Gebiet getGebiet(AnwContext anwContext, int gebietsart, int gebietsnummer)
      throws EJBException {

    try {
      return GebietHome.HomeFinder.findHome(this)
          .findByWahlAndGebietsartAndNummer(anwContext.getID_Wahl(), gebietsart, gebietsnummer);
    } catch (FinderException fe) {
      throw new EJBException(fe);
    }
  }

  /**
   * Behandlung eines Ergebniseingangs Zunächst wird die Reihenfolge und die Korrekturnummer
   * geprüft. Dann erfolgt die Aufnahme der Erfassungseinheit. Nach Aufnahme der Erfassungseinheit
   * wird hier eine entsprechende Nachricht erzeugt, weitere Vorgänge angestoßen (Export!) sowie
   * geprüft, ob die übergeordneten Gebiete dadurch vollständig geworden sind.
   * 
   * @param msg Eingangsnachricht
   * @throws ImportException
   * @throws ImportException Fehler bei der Eingangsverarbeitung
   * @throws EJBException genereller Fehler
   * @throws FinderException Daten für die Sitzberechung oder Hochrechung konnten nicht ermittelt
   *           werden
   * @throws RemoveException alte Ergebnisse der Sitzberechung oder Hochrechung konnten nicht
   *           entfernt werden
   */
  @Override
  public String processInput(EingangMsg msg) throws ImportException {

    final AnwContext anwContext = msg.getErsteller();
    final WahlInfo wahlInfo = getWahlInfo(anwContext);

    // Jetzt haben wir den Lock und die Verarbeitung kann beginnen:
    msg.setEingangszeit(new Date());

    int gebietsart = msg.getGebietsartErfassungseinheit();
    int gebietsnummer = msg.getNummerErfassungseinheit();
    Gebiet erfassungsgebiet = getGebiet(anwContext, gebietsart, gebietsnummer);
    if (!erfassungsgebiet.isErfassungseinheit()) {
      throw new ImportException(TYPE_CONTENT, msg.getURL(),
          Messages.bind(MessageKeys.Error_0_IstKeineErfassungseinheit, erfassungsgebiet.getName()));
    }

    String id_Ergebniseingang = processEingangMsg(msg, erfassungsgebiet);
    wahlInfo.synchronize();
    return id_Ergebniseingang;
  }

  @Override
  public int sourceForGUIMsg(AnwContext anwContext,
      int gebietsart,
      int gebietsnummer,
      boolean forDisplay) throws EJBException {
    Gebiet gebiet = getGebiet(anwContext, gebietsart, gebietsnummer);
    WahlInfo wahlInfo = getWahlInfo(anwContext);
    return sourceForFillEingangMsg(gebiet, forDisplay, false, wahlInfo.getAktuelleWahlergebnisart());
  }

  /**
   * This method returns the source attribute or what #fillEingangMsg() would result in.
   * 
   * @return ErgebniseingangKonstanten.SOURCE_GUI_1 or ErgebniseingangKonstanten.SOURCE_GUI_2
   */
  int sourceForFillEingangMsg(final Gebiet gebiet,
      final boolean forDisplay,
      final boolean lastInputWithLastStatus,
      final int wahlergebnisart) throws EJBException {
    Ergebniseingang letzterGueltigerEingang = gebiet.getLetzterGueltigerEingang(wahlergebnisart);
    Ergebniseingang letzterEingang = gebiet.getLastInput(wahlergebnisart);
    // find last valid input
    if (forDisplay && !lastInputWithLastStatus) {
      if (letzterGueltigerEingang != null) {
        // return last correct input, if it exists
        // source first input to open first input next time
        return ErgebniseingangKonstanten.SOURCE_GUI_1;
      }
    } else {
      if (letzterEingang != null) {
        int status = letzterEingang.getStatus();
        // return the new GuiMsg for second input, if no one exists
        if (ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK == status && !lastInputWithLastStatus) {
          return ErgebniseingangKonstanten.SOURCE_GUI_2;
        }

        final int source;
        // if previuos result is valid initialize first input
        if (ErgebniseingangKonstanten.STATE_OK == status && !lastInputWithLastStatus) {
          source = ErgebniseingangKonstanten.SOURCE_GUI_1;
        } else {
          source = letzterEingang.getHerkunft();
        }
        // return the last input with status of last result
        return source;
      }
    }

    // no result exists, return new GuiMsg for first input
    return ErgebniseingangKonstanten.SOURCE_GUI_1;
  }

  /**
   * Filling a GUIEingangMsg object with metadata and data of a previous result. Used to initialize
   * input GUI or to show the result
   * 
   * @param msg empty data object
   * @param gebiet input region
   * @param forDisplay <code>true</code> ask for valid results only <code>false</code> initialize
   *          input GUI
   * @param wahlergebnisart election result type
   * @return data object with metadata and voting results
   * @throws EJBException any problem
   */
  GUIEingangMsg fillEingangMsg(GUIEingangMsg msg,
      Gebiet gebiet,
      boolean forDisplay,
      boolean lastInputWithLastStatus,
      int wahlergebnisart) throws EJBException {
    msg.setErfassungseinheit(gebiet);
    msg.setGebietsartErfassungsgebiet(gebiet.getGebietsart());
    msg.setNummerErfassungsgebiet(gebiet.getNummer());
    msg.setForDisplay(forDisplay);
    // id
    msg.setSerialisierungsnummer(gebiet.getErgebniseingangCol().size() + 1);
    Ergebniseingang letzterGueltigerEingang = gebiet.getLetzterGueltigerEingang(wahlergebnisart);
    if (letzterGueltigerEingang != null) {
      msg.setLetzterGueltigerErgebniseingangModel(letzterGueltigerEingang.getDetails());
    }
    Ergebniseingang letzterEingang = gebiet.getLastInput(msg.getWahlergebnisart());
    if (letzterEingang != null) {
      msg.setErgebniseingangModel(letzterEingang.getDetails());
    }
    // find last valid input
    if (forDisplay && !lastInputWithLastStatus) {
      if (letzterGueltigerEingang != null) {
        // return last correct input, if it exists
        // source first input to open first input next time
        fillMsgMetaData(msg, gebiet, ErgebniseingangKonstanten.SOURCE_GUI_1);
        msg.setStatus(ErgebniseingangKonstanten.STATE_OK);
        msg.setUnterschiedeVorhanden(ErgebniseingangKonstanten.UnterschiedeVorhandenTyp
            .getBooleanByValue(letzterGueltigerEingang.getUnterschiedeVorhanden()));
        GUIEingangMsg msgForDisplay = fillEingangMsg(msg,
            gebiet.getID_Gebiet(),
            letzterGueltigerEingang.getID_Ergebniseingang());
        checkForWarnings(msgForDisplay, gebiet, true);
        return msgForDisplay;
      }
    } else {
      if (letzterEingang != null) {
        int status = letzterEingang.getStatus();
        // return the new GuiMsg for second input, if no one exists
        if (ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK == status && !lastInputWithLastStatus) {
          msg.setStatus(status);
          fillMsgMetaData(msg, gebiet, ErgebniseingangKonstanten.SOURCE_GUI_2);
          if (letzterEingang.getHerkunft() == ErgebniseingangKonstanten.SOURCE_GUI_2) { // do not
                                                                                        // show
                                                                                        // value of
                                                                                        // first
                                                                                        // input on
                                                                                        // screen
                                                                                        // for
                                                                                        // second
                                                                                        // input
            msg.setUnterschiedeVorhanden(ErgebniseingangKonstanten.UnterschiedeVorhandenTyp
                .getBooleanByValue(letzterEingang.getUnterschiedeVorhanden()));
          }
          return msg;
        }
        int source;
        // if previuos result is valid initialize first input
        if (ErgebniseingangKonstanten.STATE_OK == status && !lastInputWithLastStatus) {
          source = ErgebniseingangKonstanten.SOURCE_GUI_1;
        } else {
          source = letzterEingang.getHerkunft();
        }
        // return the last input with status of last result
        msg.setStatus(status);
        msg.setFehler(letzterEingang.getFehlermeldung());
        fillMsgMetaData(msg, gebiet, source);
        msg.setUnterschiedeVorhanden(ErgebniseingangKonstanten.UnterschiedeVorhandenTyp
            .getBooleanByValue(letzterEingang.getUnterschiedeVorhanden()));
        return fillEingangMsg(msg, gebiet.getID_Gebiet(), letzterEingang.getID_Ergebniseingang());
      }
    }
    // no result exists, return new GuiMsg for first input
    fillMsgMetaData(msg, gebiet, ErgebniseingangKonstanten.SOURCE_GUI_1);
    return msg;
  }

  /**
   * Helper class to calculate the percentage value on screen
   */
  private static class PercentageValueCalculator {
    private final boolean isReferendum = WahlInfo.getWahlInfo().isReferendum();
    private final Map<GruppeAllgemein, Double> map = new HashMap<GruppeAllgemein, Double>();
    private final GruppeAllgemein[] relevantSpecialGroups = {GUELTIGE, GUELTIG_ODER_LEER, WAEHLER,
        WAHLBERECHTIGTE};

    PercentageValueCalculator() {
      for (GruppeAllgemein group : relevantSpecialGroups) {
        map.put(group, 0d);
      }
    }

    /**
     * If votingResult contains the value of a relevant special group, memorize it.
     */
    void extractFrom(Stimmergebnis votingResult) {
      int positionOfGroup = votingResult.getGruppeGebietsspezifisch().getPosition();
      for (GruppeAllgemein specialGroup : relevantSpecialGroups) {
        if (positionOfGroup == specialGroup.getPosition()) {
          map.put(specialGroup, Double.valueOf(votingResult.getStimmen()));
        }
      }
    }

    public void setStimmenprozent(int positionOfGroup, int votes, Gruppendaten gruppendatenObj) {
      GruppeAllgemein vergleichsGruppe = getRelevantSpecialGroup(positionOfGroup);
      if (vergleichsGruppe != null) {
        double vergleichsStimmen = map.get(vergleichsGruppe);
        if (vergleichsStimmen > 0) {
          gruppendatenObj.setStimmenprozent(votes * 100.0 / vergleichsStimmen);
        }
      }
    }

    /**
     * @return the special group in comparision to which the percentage value shall be calculated
     */
    private GruppeAllgemein getRelevantSpecialGroup(int positionOfGroup) {
      if (positionOfGroup == WAEHLER.getPosition()) {
        return WAHLBERECHTIGTE;
      } else if (positionOfGroup == UNGUELTIGE.getPosition()) {
        return WAEHLER;
      } else if (positionOfGroup == GUELTIG_ODER_LEER.getPosition()) {
        return WAEHLER;
      } else if (positionOfGroup == GUELTIGE.getPosition() || positionOfGroup == LEER.getPosition()) {
        if (isReferendum) {
          return GUELTIG_ODER_LEER;
        } else {
          return WAEHLER;
        }
      } else if (positionOfGroup > 0) {
        // "normal" groups
        if (isReferendum) {
          return GUELTIG_ODER_LEER;
        } else {
          return GUELTIGE;
        }
      } else {
        // The remaining special groups
        return null;
      }
    }
  }

  /**
   * Filling a data object with voting results from a specified import action
   * 
   * @param msg data object for previous voting result
   * @param id_Gebiet region identifier
   * @param ID_Ergebniseingang identifier of specific import action
   * @return data object with voting results
   * @throws EJBException any error
   */
  private GUIEingangMsg fillEingangMsg(GUIEingangMsg msg,
      String id_Gebiet,
      String ID_Ergebniseingang) throws EJBException {
    try {
      Collection<Stimmergebnis> votingResultCol = getStimmergebnisHome()
          .findAllByGebietAndErgebniseingang(id_Gebiet, ID_Ergebniseingang);

      PercentageValueCalculator stimmenprozentSetzer = new PercentageValueCalculator();
      // First iteration: Extract values of special groups that are relevant for the calculation of
      // percentage values
      for (Stimmergebnis stimmergebnis : votingResultCol) {
        stimmenprozentSetzer.extractFrom(stimmergebnis);
      }
      // Fill msg, also calculation of percentage values
      for (Stimmergebnis votingResult : votingResultCol) {
        GruppeGebietsspezifisch gebietGruppe = votingResult.getGruppeGebietsspezifisch();
        Listenkandidatur lk = votingResult.getListenkandidatur();
        int positionGruppe = gebietGruppe.getPosition();
        Gruppendaten gruppendatenObj = msg.getGruppendatenObj(positionGruppe);
        int votes = votingResult.getStimmen();
        if (lk == null) {
          msg.setGruppenstimmen(positionGruppe, votes);
          stimmenprozentSetzer.setStimmenprozent(positionGruppe, votes, gruppendatenObj);
        } else {
          int positionKandidat = lk.getListenplatz();
          if (gruppendatenObj.getKandidat(positionKandidat) != null) {
            msg.setStimmen(positionGruppe, positionKandidat, votes);
          }
        }
      }
    } catch (FinderException e) {
      throw new EJBException(e);
    }
    if (msg.getStatus() == ErgebniseingangKonstanten.STATE_ERROR) {
      // should set errors with position
      checkForErrors(msg, msg.getErfassungseinheit());
    } else if (msg.getStatus() == ErgebniseingangKonstanten.STATE_WARNING) {
      checkForWarnings(msg, getGebietById(id_Gebiet));
      if (msg.getSource() == ErgebniseingangKonstanten.SOURCE_GUI_2) {
        // should set warnings with position
        Ergebniseingang firstInput = msg.getErfassungseinheit()
            .getLastValidFirstInput(msg.getWahlergebnisart());
        if (firstInput == null) {
          throw new RuntimeException("Hier sollte der Ersteingang bekannt sein!!"); //$NON-NLS-1$
        }
        compareWithFirstResult(msg, msg.getErfassungseinheit(), firstInput.getID_Ergebniseingang());
      }
    }
    return msg;
  }

  private Gebiet getGebietById(String gebietId) {
    try {
      return GebietHome.HomeFinder.findHome(this).findByPrimaryKey(gebietId);
    } catch (ObjectNotFoundException e) {
      throw new RuntimeException(e.getLocalizedMessage(), e);
    } catch (FinderException e) {
      throw new RuntimeException(e.getLocalizedMessage(), e);
    }
  }

  /**
   * Filling the message object with groups and there candidates and their corresponding list
   * positions
   * 
   * @param msg empty GUIEingangMsg object
   * @param region region the result is for
   * @param source input source
   * @throws EJBException general problem
   */
  private void fillMsgMetaData(GUIEingangMsg msg, Gebiet region, int source) throws EJBException {
    boolean isReferendum = getWahlInfo().isReferendum();

    // über Gruppen des Gebietes iterieren
    Collection<GruppeGebietsspezifisch> gruppeGebietsspezifischCol = region
        .getGruppeGebietsspezifischCol();
    for (GruppeGebietsspezifisch gruppeGebietsspezifisch : gruppeGebietsspezifischCol) {
      int gruppenposition = gruppeGebietsspezifisch.getPosition();
      // init
      msg.addGruppenergebnis(gruppenposition);
      msg.setGruppenstimmen(gruppenposition, 0);
      Gruppe gruppe = gruppeGebietsspezifisch.getGruppe();
      Gruppendaten gd = new Gruppendaten();
      gd.setPosition(gruppenposition);
      gd.setName(gruppe.getNameLang());
      gd.setKurzname(gruppe.getNameKurz());
      gd.setFarbe(gruppe.getFarbe());
      gd.setHelptext(GruppeAllgemein.getHilfstext(gruppenposition));
      gd.setKategorie(GruppeAllgemein.getKategorie(gruppenposition));
      gd.setUnterkategorie(GruppeAllgemein.getUnterkategorie(gruppenposition));
      gd.setRadioButtons(GruppeAllgemein.isRadioButtons(gruppenposition));
      gd.setCollapsible(GruppeAllgemein.isCollapsible(gruppenposition));
      gd.setBuchstabe(GruppeAllgemein.getBuchstabe(gruppenposition));
      gd.setSmallFontSize(GruppeAllgemein.isSmallFontSize(gruppenposition));
      gd.setSmallGapAfterwards(GruppeAllgemein.isSmallGapAfterwards(gruppenposition));
      gd.setVisible(GruppeAllgemein.isVisible(gruppenposition, region));
      gd.setVisibleInOverview(GruppeAllgemein.isVisibleInOverview(gruppenposition, region));
      if (WahlModel.INPUT_MODE_COMPLETE == msg.getWahlergebnisart()) {
        Liste liste = gruppeGebietsspezifisch.getListe();
        if (liste == null && region.getID_UebergeordnetesGebiet() == null) {
          Collection<Liste> listCol = gruppe.getListeCol();
          if (listCol.size() == 1) {
            liste = listCol.iterator().next();
          }
        }
        if (liste != null) {
          for (Listenkandidatur listenkandidatur : liste.getListenkandidaturCol()) {
            Personendaten personendaten = listenkandidatur.getPersonendaten();
            final int kandidatenposition = listenkandidatur.getListenplatz();
            gd.addKandidat(kandidatenposition,
                (personendaten.getPraefix() != null ? personendaten.getPraefix() + " " : "") + personendaten.getNachname() //$NON-NLS-1$ //$NON-NLS-2$
                    + (personendaten.getInitialen() != null ? (", " + personendaten.getInitialen()) //$NON-NLS-1$
                        : ""), isReferendum ? getAdminHandling().getProperty(Konstanten.KEY_REFERENDUM_ANSW + gruppenposition) : null); //$NON-NLS-1$
            msg.setStimmen(gruppenposition, kandidatenposition, 0);
          }
        }
      }
      msg.addGruppendatenObj(gruppenposition, gd);
    }
    // Set default value for number of persons entitled to vote, but not for second input
    if (region.getWahlberechtigte() > 0) {
      msg.setGruppenstimmen(WAHLBERECHTIGTE.getPosition(), region.getWahlberechtigte());
    }
    msg.setSource(source);
  }

  /**
   * Processing input data for this region, saving voting results to database, checking for
   * consistency and if approved update results for superior region
   * 
   * @param msg input data
   * @return input data with status
   * @throws EJBException general problem
   */
  String processEingangMsg(EingangMsg msg, Gebiet gebiet) throws EJBException {
    // monitoring performance
    long time = currentTimeMillis();
    String bezeichnung = gebiet.getBezeichnung();
    try {
      // get last valid result for reference
      Ergebniseingang vorigerErgebniseingang = gebiet.getLetzterGueltigerEingang(msg
          .getWahlergebnisart());

      // get last input
      Ergebniseingang lastInput = gebiet.getLastInput(msg.getWahlergebnisart());

      // OSV-1462
      calculateAndSetGueltigeIfInvisible(msg, gebiet);

      /*
       * 1. write down the results 2. update status in Ergebniseingang object 3. if everything is ok
       * handle update superior result
       */
      Map<Stimmzuordnung, Stimmergebnis> stimmergebnisse = new HashMap<Stimmzuordnung, Stimmergebnis>();
      Ergebniseingang ergebniseingang = writeErgebniseingang(msg, gebiet, stimmergebnisse);

      // first always check for errors
      checkForErrors(msg, gebiet);
      final int status = msg.getStatus();

      // if this input is different from last input, warnings have to be checked again
      if (lastInput != null && !lastInput.getErgebnisHash().equals(msg.getErgebnisHash())) {
        msg.setInputMode(EingangMsg.MODE_CHECK_WARNINGS);
      }

      // if no error check for warnings
      if (status != ErgebniseingangKonstanten.STATE_ERROR
          && msg.getInputMode() == EingangMsg.MODE_CHECK_WARNINGS) {
        checkForWarnings(msg, gebiet);
        if (msg.getSource() == ErgebniseingangKonstanten.SOURCE_GUI_2) {
          Ergebniseingang firstInput = gebiet.getLastValidFirstInput(msg.getWahlergebnisart());
          if (firstInput == null) {
            throw new RuntimeException("Hier sollte der Ersteingang bekannt sein!!"); //$NON-NLS-1$
          }
          compareWithFirstResult((GUIEingangMsg) msg, gebiet, firstInput.getID_Ergebniseingang());
        }
      }
      // add new result to supRegion
      completeEingang(gebiet, ergebniseingang, vorigerErgebniseingang, msg, stimmergebnisse);

      if (LOGGER.isInfoEnabled()) {
        long elapsed = currentTimeMillis() - time;
        String sec = (elapsed / 1000) + "," + ((elapsed % 1000 + 50) / 100); //$NON-NLS-1$
        LOGGER.info("processing data input " + bezeichnung + ": " + sec + " seconds"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
      gebiet.getWahl().setLetzteAenderung(new Timestamp(currentTimeMillis()));
      return ergebniseingang.getID_Ergebniseingang();
    } catch (CreateException ce) {
      throw new EJBException(bezeichnung, ce);
    } catch (FinderException fe) {
      throw new EJBException(bezeichnung, fe);
    }
  }

  /**
   * Calculate and set sum of votes for system groups GUELTIGE, GUELTIG_ODER_LEER, WAEHLER if it is
   * invisible (necessary for OSV-1462, OSV-1857)
   */
  private void calculateAndSetGueltigeIfInvisible(EingangMsg msg, Gebiet gebiet) {
    if (!GruppeKonstanten.GruppeAllgemein.isVisible(GUELTIGE, gebiet)) {
      int votes = calculateSumOfVotes(msg, gebiet, false, false);
      msg.setGruppenstimmen(GUELTIGE.getPosition(), votes);
    }
    if (!GruppeKonstanten.GruppeAllgemein.isVisible(GUELTIG_ODER_LEER, gebiet)) {
      int votes = calculateSumOfVotes(msg, gebiet, true, false);
      msg.setGruppenstimmen(GUELTIG_ODER_LEER.getPosition(), votes);
    }
    if (!GruppeKonstanten.GruppeAllgemein.isVisible(WAEHLER, gebiet)) {
      int votes = calculateSumOfVotes(msg, gebiet, true, true);
      msg.setGruppenstimmen(WAEHLER.getPosition(), votes);
    }
  }

  /**
   * Calculates the sum of all votes cast to political groups
   * 
   * @param includeBlankVotes include blanc votes in the sum
   * @param includeInvalidVotes include invalid votes in the sum
   */
  private int calculateSumOfVotes(EingangMsg msg,
      Gebiet gebiet,
      boolean includeBlankVotes,
      boolean includeInvalidVotes) {
    int sumListVotes = 0;
    for (GruppeGebietsspezifisch gruppeGebietsspezifisch : gebiet.getGruppeGebietsspezifischCol()) {
      int position = gruppeGebietsspezifisch.getPosition();
      if (position > 0 || includeBlankVotes && LEER.hasPosition(position) || includeInvalidVotes
          && UNGUELTIGE.hasPosition(position)) {
        int listVotes = msg.getGruppenstimmen(position);
        if (listVotes >= 0) {
          sumListVotes += listVotes;
        }
      }
    }
    return sumListVotes;
  }

  private void checkSummeError(EingangMsg msg,
      String summeSubjekte,
      String subjekte,
      int summenwert,
      int sollwert,
      int gruppenposition) {
    if (summenwert != sollwert) {
      String errorMsg = Messages.bind(MessageKeys.Error_Summe_0_1_mussDerAnzahlDer_2_3_entsprechen,
          summeSubjekte,
          summenwert,
          subjekte,
          sollwert);
      LOGGER.error(errorMsg);
      msg.setStatus(ErgebniseingangKonstanten.STATE_ERROR);
      msg.addFehler(errorMsg);
      msg.addGruppefehler(gruppenposition, errorMsg);
    }
  }

  private void checkSummeWarning(EingangMsg msg,
      String summeSubjekte,
      String subjekte,
      int summenwert,
      int sollwert,
      int gruppenposition) {
    if (summenwert != sollwert) {
      String errorMsg = Messages.bind(MessageKeys.Error_Summe_0_1_mussDerAnzahlDer_2_3_entsprechen,
          summeSubjekte,
          summenwert,
          subjekte,
          sollwert);
      LOGGER.warn(errorMsg);
      msg.setStatus(ErgebniseingangKonstanten.STATE_WARNING);
      msg.addFehler(errorMsg);
      msg.addGruppefehler(gruppenposition, errorMsg);
    }
  }

  /**
   * Checking for warnings
   * <p>
   * TODO: Konfiguration derGrenzwerte kl�ren!!!
   * 
   * @param msg input message
   * @param gebiet
   * @return input message with warnings and corresponding status
   * @throws FinderException
   * @throws EJBException
   */
  private EingangMsg checkForWarnings(EingangMsg msg, Gebiet gebiet) throws EJBException {
    return checkForWarnings(msg, gebiet, false);
  }

  private EingangMsg checkForWarnings(EingangMsg msg, Gebiet gebiet, boolean onlyCheckSums)
      throws EJBException {
    int wahlberechtigte = msg.getGruppenstimmen(WAHLBERECHTIGTE.getPosition());
    int waehler = msg.getGruppenstimmen(WAEHLER.getPosition());
    int ungueltige = msg.getGruppenstimmen(UNGUELTIGE.getPosition());
    int gueltige = msg.getGruppenstimmen(GUELTIGE.getPosition());
    int leere = msg.getGruppenstimmen(LEER.getPosition());

    int admittedVoters = msg.getGruppenstimmen(ADMITTED_VOTERS.getPosition());
    int electionNotices = msg.getGruppenstimmen(ELECTION_NOTICES.getPosition());
    int proxyVoters = msg.getGruppenstimmen(PROXY_VOTERS.getPosition());
    int pollingCards = msg.getGruppenstimmen(POLLING_CARDS.getPosition());

    int moreValidVotes = msg.getGruppenstimmen(MORE_VALID_VOTES_THAN_ADMITTED_VOTERS.getPosition());
    int lessValidVotes = msg.getGruppenstimmen(LESS_VALID_VOTES_THAN_ADMITTED_VOTERS.getPosition());

    double upperLimit;
    double quote;
    if (!onlyCheckSums && GruppeKonstanten.GruppeAllgemein.isVisible(WAEHLER, gebiet)) {
      if (wahlberechtigte > 0) {

        /*
         * Quota Attention: can be greater than 100%
         */
        quote = waehler * 100.0 / wahlberechtigte;
        Schwellwert wahlbeteiligungOber = getSchwellwert(SchwellwertModel.SWERT_WAHLBETEILIGUNG_HOCH);
        upperLimit = wahlbeteiligungOber.getWert().doubleValue();
      }
      if (waehler > 0) {
        /*
         * Quota of invalid votes > limit (in percent of total votes)
         */
        quote = ungueltige * 100.0 / waehler;
        upperLimit = getSchwellwertWert(SchwellwertModel.SWERT_UNGUELTIGE);
        if (quote > upperLimit) {
          msg.setStatus(ErgebniseingangKonstanten.STATE_WARNING);
          msg.addFehler(Messages
              .bind(MessageKeys.Error_DerAnteilDerUngueltigenStimmenIstZuHochSchwellwert_0,
                  upperLimit));
        }
        /*
         * Quota of blank votes > limit (in percent of total votes)
         */
        quote = leere * 100.0 / waehler;
        upperLimit = getSchwellwertWert(SchwellwertModel.SWERT_LEER);
        if (quote > upperLimit) {
          msg.setStatus(ErgebniseingangKonstanten.STATE_WARNING);
          msg.addFehler(Messages
              .bind(MessageKeys.Error_DerAnteilDerLeerenStimmenIstZuHochSchwellwert_0, upperLimit));
        }
      } else {
        /*
         * Number of votes > 0
         */
        if (GruppeKonstanten.GruppeAllgemein.isVisible(WAEHLER, gebiet)) {
          msg.setStatus(ErgebniseingangKonstanten.STATE_WARNING);
          msg.addFehler(Messages.getString(MessageKeys.Error_DieAnzahlDerWaehlerMussPositivSein));
        }
      }
      /*
       * votes cast > voters
       */
      if (waehler > wahlberechtigte) {
        msg.setStatus(ErgebniseingangKonstanten.STATE_WARNING);
        msg.addFehler(Messages
            .bind(MessageKeys.Warning_AnzahlDerWaehlerIstGroesserAlsAnzahlWahlberechtigte));
      }
    }
    /*
     * invalid + valid + empty votes != total votes cast
     */
    if (GruppeKonstanten.GruppeAllgemein.isVisible(UNGUELTIGE, gebiet)
        && GruppeKonstanten.GruppeAllgemein.isVisible(GUELTIGE, gebiet)
        && GruppeKonstanten.GruppeAllgemein.isVisible(LEER, gebiet)
        && GruppeKonstanten.GruppeAllgemein.isVisible(WAEHLER, gebiet)) {
      checkSummeWarning(msg,
          Messages.getString(MessageKeys.Msg_ausGueltigenUngueltigenUndLeerenStimmen),
          WAEHLER.kurzname,
          ungueltige + gueltige + leere,
          waehler,
          WAEHLER.getPosition());
    }
    /*
     * For referendum: sum of votes for answers + empty + invalid votes != total votes cast
     */
    if (WahlInfo.getWahlInfo().isReferendum()
        && GruppeKonstanten.GruppeAllgemein.isVisible(UNGUELTIGE, gebiet)
        && GruppeKonstanten.GruppeAllgemein.isVisible(LEER, gebiet)
        && GruppeKonstanten.GruppeAllgemein.isVisible(WAEHLER, gebiet)) {
      int validVotesOnAnswers = calculateSumOfVotes(msg, gebiet, false, false);
      checkSummeWarning(msg,
          Messages.getString(MessageKeys.Msg_ausDenReferendumOptionenUngueltigenUndLeerenStimmen),
          WAEHLER.kurzname,
          validVotesOnAnswers + ungueltige + leere,
          waehler,
          WAEHLER.getPosition());
    }
    /*
     * election notices + proxy voters + polling cards != admitted voters
     */
    if (GruppeKonstanten.GruppeAllgemein.isVisible(ELECTION_NOTICES, gebiet)
        && GruppeKonstanten.GruppeAllgemein.isVisible(PROXY_VOTERS, gebiet)
        && GruppeKonstanten.GruppeAllgemein.isVisible(POLLING_CARDS, gebiet)
        && GruppeKonstanten.GruppeAllgemein.isVisible(ADMITTED_VOTERS, gebiet)
        && !isRegionWithPostalVotesInPSB(gebiet)) {
      checkSummeWarning(msg,
          Messages.getString(MessageKeys.Msg_ausElectionNoticesUndProxyVotersUndPollingCards),
          ADMITTED_VOTERS.kurzname,
          electionNotices + proxyVoters + pollingCards,
          admittedVoters,
          ADMITTED_VOTERS.getPosition());
    }
    /*
     * Same check as before for EK, but without polling cards: election notices + proxy voters !=
     * admitted voters
     */
    if (GruppeKonstanten.GruppeAllgemein.isVisible(ELECTION_NOTICES, gebiet)
        && GruppeKonstanten.GruppeAllgemein.isVisible(PROXY_VOTERS, gebiet)
        && !GruppeKonstanten.GruppeAllgemein.isVisible(POLLING_CARDS, gebiet)
        && GruppeKonstanten.GruppeAllgemein.isVisible(ADMITTED_VOTERS, gebiet)
        && !isRegionWithPostalVotesInPSB(gebiet)) {
      checkSummeWarning(msg,
          Messages.getString(MessageKeys.Msg_ausElectionNoticesUndProxyVoters),
          ADMITTED_VOTERS.kurzname,
          electionNotices + proxyVoters,
          admittedVoters,
          ADMITTED_VOTERS.getPosition());
    }
    if (!onlyCheckSums) {
      if (GruppeKonstanten.GruppeAllgemein.isVisible(WAEHLER, gebiet)
          && GruppeKonstanten.GruppeAllgemein.isVisible(ADMITTED_VOTERS, gebiet)
          && GruppeKonstanten.GruppeAllgemein.isVisible(MORE_VALID_VOTES_THAN_ADMITTED_VOTERS,
              gebiet)
          && GruppeKonstanten.GruppeAllgemein.isVisible(LESS_VALID_VOTES_THAN_ADMITTED_VOTERS,
              gebiet)) {
        SystemInfo systemInfo = SystemInfo.getSystemInfo();
        String moreValidVotesErrorMsg = null;
        if (systemInfo.getWahlEbene() == GebietModel.EBENE_PSB) {
          if (moreValidVotes != 0 && waehler <= admittedVoters
              || moreValidVotes != waehler - admittedVoters && waehler > admittedVoters) {
            moreValidVotesErrorMsg = Messages
                .bind(MessageKeys.Error_unsupposedMoreValidVotesThanAdmittedVoters_PSB_0_1,
                    admittedVoters,
                    waehler);
          }
        } else {
          if (!(waehler == admittedVoters && moreValidVotes == 0)
              && waehler - admittedVoters != moreValidVotes - lessValidVotes) {
            moreValidVotesErrorMsg = Messages
                .getString(MessageKeys.Error_unsupposedMoreValidVotesThanAdmittedVoters);
          }
        }
        if (moreValidVotesErrorMsg != null) {
          LOGGER.warn(moreValidVotesErrorMsg);
          msg.setStatus(ErgebniseingangKonstanten.STATE_WARNING);
          msg.addFehler(moreValidVotesErrorMsg);
          msg.addGruppefehler(MORE_VALID_VOTES_THAN_ADMITTED_VOTERS.getPosition(),
              moreValidVotesErrorMsg);
        }
        String lessValidVotesErrorMsg = null;
        if (systemInfo.getWahlEbene() == GebietModel.EBENE_PSB) {
          if (lessValidVotes != 0 && waehler >= admittedVoters
              || lessValidVotes != admittedVoters - waehler && waehler < admittedVoters) {
            lessValidVotesErrorMsg = Messages
                .bind(MessageKeys.Error_unsupposedLessValidVotesThanAdmittedVoters_PSB_0_1,
                    admittedVoters,
                    waehler);
          }
        } else {
          if (!(admittedVoters == waehler && lessValidVotes == 0)
              && admittedVoters - waehler != lessValidVotes - moreValidVotes) {
            lessValidVotesErrorMsg = Messages
                .getString(MessageKeys.Error_unsupposedLessValidVotesThanAdmittedVoters);
          }
        }
        if (lessValidVotesErrorMsg != null) {
          LOGGER.warn(lessValidVotesErrorMsg);
          msg.setStatus(ErgebniseingangKonstanten.STATE_WARNING);
          msg.addFehler(lessValidVotesErrorMsg);
          msg.addGruppefehler(LESS_VALID_VOTES_THAN_ADMITTED_VOTERS.getPosition(),
              lessValidVotesErrorMsg);
        }
      }
      if (WahlInfo.getWahlInfo().isEK()) {
        /*
         * For EK election: admitted voters = votes cast
         */
        if (GruppeKonstanten.GruppeAllgemein.isVisible(WAEHLER, gebiet)
            && GruppeKonstanten.GruppeAllgemein.isVisible(ADMITTED_VOTERS, gebiet)) {
          if (waehler != admittedVoters) {
            String notEqualMsg = Messages
                .bind(MessageKeys.Error_validVotesNotEqualToAdmittedVoters, waehler, admittedVoters);
            msg.setStatus(ErgebniseingangKonstanten.STATE_WARNING);
            msg.addFehler(notEqualMsg);
            msg.addGruppefehler(ADMITTED_VOTERS.getPosition(), notEqualMsg);
          }
        }
      }
      // Check if there was an identical input before
      try {
        Collection<Ergebniseingang> eeCol = getErgebniseingangHome()
            .findAllByErgebnisHashAndLastValidInput(msg.getErgebnisHash());
        for (Ergebniseingang ee : eeCol) {
          if (!ee.getErfassungseinheit().equals(gebiet)) {
            Gebiet otherRegion = ee.getErfassungseinheit();
            String otherRegionStr = Gebietsart.getGebietsartKlartext(otherRegion)
                + " " + otherRegion.getName(); //$NON-NLS-1$
            msg.setStatus(ErgebniseingangKonstanten.STATE_WARNING);
            msg.addFehler(Messages.bind(MessageKeys.Warning_ResultIsIdenticalWithResultForRegion_0,
                otherRegionStr));
          }
        }
      } catch (FinderException e) {
        LOGGER.error("Error finding Result Hashs"); //$NON-NLS-1$
        throw new EJBException(e);
      }
    }
    return msg;
  }

  private boolean isRegionWithPostalVotesInPSB(Gebiet gebiet) {
    if (SystemInfo.getSystemInfo().getWahlEbene() != GebietModel.EBENE_PSB) {
      return false;
    } else if (!ElectionCategory.fromWahlart(gebiet.getWahl().getWahlart()).hasPostalVotes()) {
      return false;
    } else if (GruppeKonstanten.GruppeAllgemein.isGebietOrHasUntergebietSGravenhage(gebiet)) {
      return true;
    }
    return false;
  }

  /**
   * Comparing the second input data with first input for this region
   * 
   * @param msg input message wih data from second input
   * @return second input, if differences are found with corresponding warnings
   */
  private EingangMsg compareWithFirstResult(GUIEingangMsg msg,
      Gebiet gebiet,
      String id_ErgebniseingangFirstResult) {
    GUIEingangMsg firstInput = new GUIEingangMsg(msg.getErsteller());
    fillMsgMetaData(firstInput, gebiet, ErgebniseingangKonstanten.SOURCE_GUI_1);
    fillEingangMsg(firstInput, gebiet.getID_Gebiet(), id_ErgebniseingangFirstResult);

    for (GruppeGebietsspezifisch gruppeGebietsspezifisch : gebiet.getGruppeGebietsspezifischCol()) {
      int position = gruppeGebietsspezifisch.getPosition();
      // Compare general or group results
      int firstVotes = firstInput.getGruppenstimmen(position);
      int secondVotes = msg.getGruppenstimmen(position);
      // Only check visible input fields (see OSV-1549)
      if (firstVotes != secondVotes && GruppeKonstanten.GruppeAllgemein.isVisible(position, gebiet)) {
        addNotEqualError(msg, firstVotes, position);
      }
      if (position < 0 || msg.getWahlergebnisart() == WahlModel.INPUT_MODE_GROUPS) {
        continue;
      }
      // Compare candidate votes
      if (gruppeGebietsspezifisch.getListe() != null) {
        Collection<Listenkandidatur> lkCol = gruppeGebietsspezifisch.getListe()
            .getListenkandidaturCol();
        for (Listenkandidatur lk : lkCol) {
          int listposition = lk.getListenplatz();
          int firstVotesK = firstInput.getStimmen(position, listposition);
          int secondVotesK = msg.getStimmen(position, listposition);
          if (firstVotesK != secondVotesK) {
            addNotEqualError(msg, firstVotesK, position, listposition);
          }
        }
      }
    }
    return msg;
  }

  /**
   * Checking data consistency, total votes, etc.
   * 
   * @param msg Eingangsnachricht
   * @return input message with errors and corresponding status
   */
  private EingangMsg checkForErrors(EingangMsg msg, Gebiet gebiet) {
    // sums
    int sumListVotes = 0;
    for (GruppeGebietsspezifisch gruppeGebietsspezifisch : gebiet.getGruppeGebietsspezifischCol()) {
      int position = gruppeGebietsspezifisch.getPosition();
      if (position <= 0) {
        continue;
      }
      int listVotes = msg.getGruppenstimmen(position);
      if (listVotes >= 0) {
        sumListVotes += listVotes;
      } else {
        addNichtAusgefuelltFehler(msg,
            Messages.bind(MessageKeys.Error_StimmenAufPosition_0, position),
            position);
      }
      if (msg.getWahlergebnisart() == WahlModel.INPUT_MODE_COMPLETE) {
        int sumCandidateVotes = 0;
        // Count candidate votes
        if (gruppeGebietsspezifisch.getListe() != null) {
          Collection<Listenkandidatur> lkCol = gruppeGebietsspezifisch.getListe()
              .getListenkandidaturCol();
          for (Listenkandidatur lk : lkCol) {
            int listenplatz = lk.getListenplatz();
            int candidateVotes = msg.getStimmen(position, listenplatz);
            if (candidateVotes >= 0) {
              sumCandidateVotes += candidateVotes;
            } else {
              addNichtAusgefuelltFehler(msg,
                  Messages.bind(MessageKeys.Error_StimmenAufPosition_0_Listenplatz_1,
                      position,
                      listenplatz),
                  position,
                  listenplatz);
            }
          }
          checkSummeError(msg,
              Messages.bind(MessageKeys.Msg_KandidatenStimmenFuer_0, gruppeGebietsspezifisch
                  .getGruppe().getSchluessel()),
              Messages.getString(MessageKeys.Msg_ListenStimmen),
              sumCandidateVotes,
              listVotes,
              position);
        }
      }
    }

    checkSumAgainstEligibleAndMetVoters(msg, sumListVotes, gebiet);

    return msg;
  }

  private void checkSumAgainstEligibleAndMetVoters(EingangMsg msg, int sumListVotes, Gebiet gebiet) {
    int wahlberechtigte = msg.getGruppenstimmen(WAHLBERECHTIGTE.getPosition());
    int waehler = msg.getGruppenstimmen(WAEHLER.getPosition());
    int ungueltige = msg.getGruppenstimmen(UNGUELTIGE.getPosition());
    int leere = msg.getGruppenstimmen(LEER.getPosition());
    int gueltige = msg.getGruppenstimmen(GUELTIGE.getPosition());

    /*
     * Persons entitled to vote > 0
     */
    if (wahlberechtigte < 0) {
      addNichtAusgefuelltFehler(msg, WAHLBERECHTIGTE.kurzname, WAHLBERECHTIGTE.getPosition());
    }
    // can be 0
    // if (wahlberechtigte == 0) {
    // msg.addFehler(Messages
    // .getString(MessageKeys.Error_DieAnzahlDerWahlberechtigtenMussPositivSein));
    // msg.setStatus(ErgebniseingangKonstanten.STATE_ERROR);
    // }
    if (waehler < 0) {
      addNichtAusgefuelltFehler(msg, WAEHLER.kurzname, WAEHLER.getPosition());
    }

    if (ungueltige < 0) {
      addNichtAusgefuelltFehler(msg, UNGUELTIGE.kurzname, UNGUELTIGE.getPosition());
    }
    if (leere < 0) {
      addNichtAusgefuelltFehler(msg, LEER.kurzname, LEER.getPosition());
    }
    if (gueltige < 0) {
      addNichtAusgefuelltFehler(msg, GUELTIGE.kurzname, GUELTIGE.getPosition());
    }
    if (GruppeKonstanten.GruppeAllgemein.isVisible(GUELTIGE, gebiet)) {
      checkSummeError(msg,
          GUELTIGE.kurzname,
          Messages.getString(MessageKeys.Msg_ListenStimmen),
          gueltige,
          sumListVotes,
          GUELTIGE.getPosition());
    }
  }

  /**
   * @param msg
   * @param subjekte
   */
  private void addNichtAusgefuelltFehler(EingangMsg msg, String subjekte, int gruppenposition) {
    msg.setStatus(ErgebniseingangKonstanten.STATE_ERROR);
    String msgString = Messages
        .bind(MessageKeys.Error_DasFeldFuerDieAnzahlDer_0_MussEineNichtNegativeGanzeZahlEnthalten,
            subjekte);
    msg.addGruppefehler(gruppenposition, Messages
        .bind(MessageKeys.Error_DasFeldFuerDieAnzahlDer_0_MussEineNichtNegativeGanzeZahlEnthalten,
            subjekte));
    msg.addFehler(msgString);
  }

  private void addNotEqualError(GUIEingangMsg msg,
      int firstInput,
      int gruppenposition,
      int listenposition) {
    msg.setStatus(ErgebniseingangKonstanten.STATE_WARNING);
    int secondInput = msg.getStimmen(gruppenposition, listenposition);
    String name = msg.getGruppendatenObj(gruppenposition).getKandidat(listenposition).getName();
    String msgString = Messages
        .bind(MessageKeys.Error_Ersteingabe_0_Fuer_1_StimmtNichtMitZweiteingabe_2_ueberein,
            String.valueOf(firstInput),
            name,
            String.valueOf(secondInput));
    msg.setKandidatenfehler(gruppenposition, listenposition, msgString);
    msg.addFehler(msgString);
  }

  /**
   * Comparing input at specified position with first result
   * 
   * @param msg
   * @param firstInput
   * @param gruppenposition
   */
  private void addNotEqualError(GUIEingangMsg msg, int firstInput, int gruppenposition) {
    msg.setStatus(ErgebniseingangKonstanten.STATE_WARNING);
    int secondInput = msg.getGruppenstimmen(gruppenposition);
    String name = msg.getGruppendatenObj(gruppenposition).getKurzname();
    String msgString = Messages
        .bind(MessageKeys.Error_Ersteingabe_0_Fuer_1_StimmtNichtMitZweiteingabe_2_ueberein,
            String.valueOf(firstInput),
            name,
            String.valueOf(secondInput));
    msg.addGruppefehler(gruppenposition, msgString);
    msg.addFehler(msgString);
  }

  /**
   * @param msg
   * @param subjekte
   */
  private void addNichtAusgefuelltFehler(EingangMsg msg,
      String subjekte,
      int gruppenposition,
      int listenposition) {
    msg.setStatus(ErgebniseingangKonstanten.STATE_ERROR);
    String msgString = Messages
        .bind(MessageKeys.Error_DasFeldFuerDieAnzahlDer_0_MussEineNichtNegativeGanzeZahlEnthalten,
            subjekte);
    msg.setKandidatenfehler(gruppenposition, listenposition, msgString);
    msg.addFehler(msgString);
  }

  /**
   * Nach Überprüfung wird das Ergebniseingangs-Objekt angepasst und eventuell werden die Werte aus
   * der Nachricht als Rechengrundlage übernommen (bei Fehlerfreiheit!). Die Daten werden aus der
   * Nachricht ausgelesen.
   * 
   * @param ergebniseingang aktueller Ergebniseingang der Erfassungseinheit
   * @param vorigerErgebniseingang vorheriger Ergebniseingang der Erfassungseinheit
   * @param msg Eingangsnachricht
   * @param stimmergebnisErfassungseinheitMap Map (Stimmzuordnung, StimmergebnisModel) von allen
   *          Werten des Ergebnisses
   */
  private void completeEingang(Gebiet gebiet,
      Ergebniseingang ergebniseingang,
      Ergebniseingang vorigerErgebniseingang,
      EingangMsg msg,
      Map<Stimmzuordnung, Stimmergebnis> stimmergebnisErfassungseinheitMap)
      throws CreateException, FinderException, EJBException {

    final int status = msg.getStatus();
    boolean isFinalResult = isFinalResult(msg);
    ergebniseingang.setStatus(status);
    String id_Ergebniseingang = (String) ergebniseingang.getPrimaryKey();
    // Set the result hash
    ergebniseingang.setErgebnisHash(msg.getErgebnisHash());
    ergebniseingang.setUnterschiedeVorhanden(ErgebniseingangKonstanten.UnterschiedeVorhandenTyp
        .getValueByBoolean(msg.getUnterschiedeVorhanden()));
    if (status == ErgebniseingangKonstanten.STATE_WARNING
        || status == ErgebniseingangKonstanten.STATE_ERROR) {
      LOGGER.debug(Messages.getString(MessageKeys.Logger_FehlerhafterEingang));
      ergebniseingang.setFehlermeldung(msg.getFehler());
    } else if (isFinalResult) {
      if (LOGGER.isDebugEnabled()) {
        LOGGER
            .debug(Messages.bind(MessageKeys.Logger_VerarbeiteEingangImGebiet_0, gebiet.getName()));
      }
      boolean neuVollstaendig = gebiet.getLetzterGueltigerEingang() == null;
      int wahlergebnisart = msg.getWahlergebnisart();
      String id_Gebiet = gebiet.getID_Gebiet();
      int alteKorrekturnummer = -1;
      if (gebiet.getLetzterGueltigerEingang() != null) {
        String id_LGEE = gebiet.getLetzterGueltigerEingang().getID_Ergebniseingang();
        try {
          Gebietsstatus alterStatus = getGebietsstatusHome()
              .findByErgebniseingangAndGebiet(id_LGEE, id_Gebiet);
          alteKorrekturnummer = alterStatus.getKorrekturnummer();
        } catch (FinderException e) {
          // do nothing
        }
      }
      Gebietsstatus gebietsstatus = getGebietsstatusHome().create();
      LOGGER.debug(Messages
          .getString(MessageKeys.Logger_ErzeugeGebietsstatusFuerDieErfassungseinheit));
      gebietsstatus.setID_Ergebniseingang(id_Ergebniseingang);
      gebietsstatus.setID_Gebiet(id_Gebiet);
      gebietsstatus.setWahlergebnisart(wahlergebnisart);
      gebietsstatus.setVollstaendig(true);
      gebietsstatus.setKorrekturnummer(alteKorrekturnummer + 1);
      gebiet.setLetzterGueltigerEingang(wahlergebnisart, ergebniseingang);

      // Adding voting results to root region
      addVotesToRootRegion(gebiet,
          ergebniseingang,
          vorigerErgebniseingang,
          stimmergebnisErfassungseinheitMap,
          id_Ergebniseingang,
          neuVollstaendig,
          wahlergebnisart,
          id_Gebiet);

    } else {
      // First input ok, second required
      ergebniseingang.setStatus(ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK);
      msg.setStatus(ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK);
      // msg.set(ErgebniseingangKonstanten.SOURCE_GUI_2);
    }
  }

  private void addVotesToRootRegion(Gebiet gebiet,
      Ergebniseingang ergebniseingang,
      Ergebniseingang vorigerErgebniseingang,
      Map<Stimmzuordnung, Stimmergebnis> stimmergebnisErfassungseinheitMap,
      String id_Ergebniseingang,
      boolean neuVollstaendig,
      int wahlergebnisart,
      String id_Gebiet) throws FinderException, CreateException {

    Map<Stimmzuordnung, Integer> stimmergebnisDeltaMap = calculateStimmergebnisDeltas(vorigerErgebniseingang,
        stimmergebnisErfassungseinheitMap,
        neuVollstaendig,
        id_Gebiet);

    // determine new result for superior region
    LOGGER
        .debug(Messages
            .getString(MessageKeys.Logger_InkrementelleErgaenzungVonErgebnissenDesUebergeordnetenGebietes));
    Gebiet ueGebiet = gebiet.getUebergeordnetesGebiet();
    int anzahlErgebnisseKumuliert;
    Gebietsstatus gebietsstatusAlt = ueGebiet.getCurrentGebietsstatus();
    if (gebietsstatusAlt != null) {
      anzahlErgebnisseKumuliert = gebietsstatusAlt.getAnzahlErgebnisseKumuliert();
    } else {
      anzahlErgebnisseKumuliert = 0;
    }
    if (neuVollstaendig) {
      anzahlErgebnisseKumuliert += 1;
    }
    Gebietsstatus ueGebietsstatus = getGebietsstatusHome().create();
    GebietsstatusModel gebietsstatusModel = ueGebietsstatus.getDetails();
    LOGGER.debug(Messages.bind(MessageKeys.Logger_Ergaenze_0, ueGebiet.getName()));
    String id_UeGebiet = ueGebiet.getID_Gebiet();
    String id_LGEE;
    if (ueGebiet.getLetzterGueltigerEingang() != null) {
      id_LGEE = ueGebiet.getLetzterGueltigerEingang().getID_Ergebniseingang();
    } else {
      id_LGEE = null;
    }
    Map<Stimmzuordnung, StimmergebnisModel> supRegionResults = new HashMap<Stimmzuordnung, StimmergebnisModel>();
    // Fallunterscheidung
    if (id_LGEE != null) {
      // There have been results before, just add changes
      Collection<Stimmergebnis> stimmergebnisseGebietAltCol = getStimmergebnisHome()
          .findAllByGebietAndErgebniseingang(id_UeGebiet, id_LGEE);
      for (Stimmergebnis stimmergebnisAlt : stimmergebnisseGebietAltCol) {
        // copy all result (getDetails() creates a copy)
        StimmergebnisModel stimmergebnisModel = stimmergebnisAlt.getDetails();
        stimmergebnisModel.setID_Ergebniseingang(id_Ergebniseingang);
        supRegionResults.put(new Stimmzuordnung(stimmergebnisAlt), stimmergebnisModel);
      }
    }
    // Fill GebietsStatus root region
    gebietsstatusModel.setAnzahlErgebnisseKumuliert(anzahlErgebnisseKumuliert);
    gebietsstatusModel.setWahlergebnisart(wahlergebnisart);
    gebietsstatusModel.setID_Ergebniseingang(id_Ergebniseingang);
    gebietsstatusModel.setID_Gebiet(id_UeGebiet);

    // Add increment to superior result
    updateSuperiourResult(id_Ergebniseingang,
        id_UeGebiet,
        wahlergebnisart,
        supRegionResults,
        stimmergebnisDeltaMap);
    speichern(supRegionResults);

    // Vollst�ndigkeit testen
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug(Messages
          .bind(MessageKeys.Logger_PruefeAufVollstaendigkeitKumuliert_0_erwartet_1,
              anzahlErgebnisseKumuliert,
              ueGebiet.getGebietErwartetCol().size()));
    }
    boolean ueVollstaendig = anzahlErgebnisseKumuliert == ueGebiet.getGebietErwartetCol().size();
    if (neuVollstaendig && ueVollstaendig) {
      LOGGER.info(Messages.bind(MessageKeys.Logger_0_istVollstaendigGeworden_1_Teilgebiete,
          gebiet.getName(),
          anzahlErgebnisseKumuliert));
    }
    gebietsstatusModel.setVollstaendig(ueVollstaendig);

    ueGebietsstatus.setDetails(gebietsstatusModel);

    final Ergebniseingang ueLetzterGueltigerErgebniseingang = ueGebiet
        .getLetzterGueltigerEingang(wahlergebnisart);
    if (ueLetzterGueltigerErgebniseingang != null) {
      ueLetzterGueltigerErgebniseingang.removeGebiet(ueGebiet);
    }
    ueGebiet.setLetzterEingang(ergebniseingang);
    ergebniseingang.addGebiet(ueGebiet);
  }

  private Map<Stimmzuordnung, Integer> calculateStimmergebnisDeltas(Ergebniseingang vorigerErgebniseingang,
      Map<Stimmzuordnung, Stimmergebnis> stimmergebnisErfassungseinheitMap,
      boolean neuVollstaendig,
      String id_Gebiet) throws FinderException {
    LOGGER
        .debug(Messages
            .getString(MessageKeys.Logger_DifferenzenDerStimmergebnisseZuEinemEventullenVorherigenEingangOderZu0Ermitteln));
    Map<Stimmzuordnung, Integer> stimmergebnisDeltaMap = new HashMap<Stimmzuordnung, Integer>();
    if (neuVollstaendig) {
      LOGGER
          .debug(Messages
              .getString(MessageKeys.Logger_DasNeueErgebnisIstGleichzeitigDieDifferenzDaKeinVorherigesExistiert));
      stimmergebnisDeltaMap = inkrement(stimmergebnisErfassungseinheitMap);
    } else {
      LOGGER.debug(Messages.getString(MessageKeys.Logger_DifferenzZumAltenErgebnisErmitteln));
      String id_VorigerErgebniseingang = vorigerErgebniseingang.getID_Ergebniseingang();
      Collection<Stimmergebnis> stimmergebnisErfassungseinheitAltCol = getStimmergebnisHome()
          .findAllByGebietAndErgebniseingang(id_Gebiet, id_VorigerErgebniseingang);
      stimmergebnisDeltaMap = inkrementDifferenz(stimmergebnisErfassungseinheitMap,
          stimmergebnisErfassungseinheitAltCol);
    }
    return stimmergebnisDeltaMap;
  }

  private boolean isFinalResult(EingangMsg msg) {
    boolean isFinalInput = msg.getSource() == ErgebniseingangKonstanten.SOURCE_FILE_IMPORT;
    isFinalInput |= SystemInfo.getSystemInfo().getInputMode().isSingleInput()
        || msg.getSource() == ErgebniseingangKonstanten.SOURCE_GUI_2;
    return msg.getStatus() == ErgebniseingangKonstanten.STATE_OK && isFinalInput;
  }

  /**
   * Speichern der StimmergebnisModel-Objekte in Stimmergebnis-EJB
   * 
   * @param stimmergebnisHome Home IF von Stimmergebnis
   * @param stimmergebnisseAktGebiet Map (Stimmzuordnung, StimmergebnisModel) von allen aktuellen
   *          Werten des Ergebnisses dieses Gebietes
   * @throws CreateException wenn die Erzeugung eines Stimmergebnis-EJB nicht m�glich ist
   *           (haupts�chlich durch ein Datenbankproblem)
   * @throws EJBException bei einem allgemeinen Problem
   */
  private void speichern(Map<Stimmzuordnung, StimmergebnisModel> stimmergebnisseAktGebiet)
      throws CreateException, EJBException {

    for (StimmergebnisModel stimmergebnisModel : stimmergebnisseAktGebiet.values()) {
      getStimmergebnisHome().create().setDetails(stimmergebnisModel);
    }
  }

  /**
   * Update voting results for superior region
   * 
   * @param id_Ergebniseingang identifier for this votoing result
   * @param id_UeGebiet identifier of superior region
   * @param aktuelleWahlergebnisart current election mode
   * @param supRegionResults Map (Stimmzuordnung, StimmergebnisModel) with present voting results
   *          for the superior region
   * @param stimmergebnisDeltaMap current voting result as map (Stimmzuordnung, Integer) containing
   *          resulting differences for the superior region
   */
  private void updateSuperiourResult(String id_Ergebniseingang,
      String id_UeGebiet,
      int aktuelleWahlergebnisart,
      Map<Stimmzuordnung, StimmergebnisModel> supRegionResults,
      Map<Stimmzuordnung, Integer> stimmergebnisDeltaMap) {

    for (Entry<Stimmzuordnung, Integer> deltaEntry : stimmergebnisDeltaMap.entrySet()) {
      Stimmzuordnung stimmzuordnung = deltaEntry.getKey();
      Stimmzuordnung agStimmzuordnung = stimmzuordnung.getUebergeordneteStimmzuordnung();
      int delta = deltaEntry.getValue();
      StimmergebnisModel supRegionResult = supRegionResults.get(agStimmzuordnung);
      if (supRegionResult == null) {
        supRegionResult = new StimmergebnisModelImpl();
        supRegionResult.setID_Gebiet(id_UeGebiet);
        supRegionResult.setID_GruppeGebietsspezifisch(agStimmzuordnung.getGruppeGebietsspezifisch()
            .getID_GruppeGebietsspezifisch());
        int stimmart = agStimmzuordnung.getGruppeGebietsspezifisch().getPosition() < 0
            ? STIMMART_KEINE
            : STIMMART_LISTENSTIMME;
        supRegionResult.setStimmart(stimmart);
        supRegionResult.setWahlergebnisart(aktuelleWahlergebnisart);
        supRegionResult.setID_Ergebniseingang(id_Ergebniseingang);
        supRegionResult.setID_Listenkandidatur(agStimmzuordnung._id_Listenkandidatur);
        // consider default for WAHLBERECHTIGTE
        if (agStimmzuordnung.getGruppeGebietsspezifisch().getPosition() == WAHLBERECHTIGTE
            .getPosition()) {
          int val = agStimmzuordnung.getGruppeGebietsspezifisch().getGebiet().getWahlberechtigte();
          if (val >= 0) {
            supRegionResult.setStimmen(val);
          }
        }
        supRegionResults.put(agStimmzuordnung, supRegionResult);
      }

      // PSB: number of eligible voters are edited via special administration page and should always
      // equal their value of the region
      if (SystemInfo.getSystemInfo().getWahlEbene() == GebietModel.EBENE_PSB
          && agStimmzuordnung.getGruppeGebietsspezifisch().getPosition() == WAHLBERECHTIGTE
              .getPosition()) {
        int val = agStimmzuordnung.getGruppeGebietsspezifisch().getGebiet().getWahlberechtigte();
        if (val >= 0) {
          supRegionResult.setStimmen(val);
        }

      } else {
        supRegionResult.setStimmen(supRegionResult.getStimmen() + delta);

      }
    }
  }

  /**
   * Calculating differences between this and the last voting result for the input region, used to
   * update the superior region's voting result
   * 
   * @param stimmergebnisErfassungseinheitMap current voting results of the input region
   * @param stimmergebnisErfassungseinheitAltCol last voting results (Stimmergebnis-objects) of the
   *          input region
   * @return Map (Stimmzuordnung, Integer) with differences
   * @throws EJBException any problem
   */
  private Map<Stimmzuordnung, Integer> inkrementDifferenz(Map<Stimmzuordnung, Stimmergebnis> stimmergebnisErfassungseinheitMap,
      Collection<Stimmergebnis> stimmergebnisErfassungseinheitAltCol) throws EJBException {

    Map<Stimmzuordnung, Integer> stimmergebnisDeltaMap = new HashMap<Stimmzuordnung, Integer>();
    for (Stimmergebnis stimmergebnisAlt : stimmergebnisErfassungseinheitAltCol) {
      Stimmzuordnung stimmzuordnung = new Stimmzuordnung(stimmergebnisAlt);
      int aktErgebnis = stimmergebnisErfassungseinheitMap.get(stimmzuordnung).getStimmen();
      // deltaMap filled with differences between current and previous results
      stimmergebnisDeltaMap.put(stimmzuordnung, aktErgebnis - stimmergebnisAlt.getStimmen());
    }
    return stimmergebnisDeltaMap;
  }

  /**
   * Calculating differences between the currently handled input and previous results. Used to
   * update superior region resuults
   * 
   * @param stimmergebnisErfassungseinheitMap current voting results
   * @return Map (Stimmzuordnung, Integer) with differences
   * @throws EJBException any problem
   */
  private Map<Stimmzuordnung, Integer> inkrement(Map<Stimmzuordnung, Stimmergebnis> stimmergebnisErfassungseinheitMap)
      throws EJBException {

    Map<Stimmzuordnung, Integer> stimmergebnisDeltaMap = new HashMap<Stimmzuordnung, Integer>();
    for (Entry<Stimmzuordnung, Stimmergebnis> stimmergebnisEintrag : stimmergebnisErfassungseinheitMap
        .entrySet()) {
      Stimmzuordnung stimmzuordnung = stimmergebnisEintrag.getKey();
      int aktErgebnis = stimmergebnisEintrag.getValue().getStimmen();
      // Consider default values for eligible voters
      if (stimmergebnisEintrag.getKey()._gruppeGebietsspezifisch.getPosition() == WAHLBERECHTIGTE
          .getPosition()) {
        int defWahlberechtigte = stimmergebnisEintrag.getKey()._gruppeGebietsspezifisch.getGebiet()
            .getWahlberechtigte();
        if (defWahlberechtigte > 0) {
          aktErgebnis = stimmergebnisEintrag.getValue().getStimmen() - defWahlberechtigte;
        }
      }
      // deltaMap has positiv values for voting results
      stimmergebnisDeltaMap.put(stimmzuordnung, aktErgebnis);
    }
    return stimmergebnisDeltaMap;
  }

  /**
   * Ermittelt das HomeIF f�r GruppeGebietsspezifisch
   * 
   * @return HomeIF f�r GruppeGebietsspezifisch
   * @throws EJBException bei einem Problem
   */
  private GruppeGebietsspezifischHome getGruppeGebietsspezifischHome() throws EJBException {
    return (GruppeGebietsspezifischHome) findLocalHome("GruppeGebietsspezifisch"); //$NON-NLS-1$
  }

  /**
   * Wrinting voting results to database so far no checks for validity or consistency have taken
   * place, that's why the state of the result is "worst case" :ERROR
   * 
   * @param msg Voting results
   * @return the new import reference
   */
  private Ergebniseingang writeErgebniseingang(EingangMsg msg,
      Gebiet gebiet,
      Map<Stimmzuordnung, Stimmergebnis> stimmergebnisse) throws EJBException {

    // Create identifier object for import action
    Ergebniseingang ergebniseingang = createErgebniseingang(msg, gebiet);
    String id_Ergebniseingang = (String) ergebniseingang.getPrimaryKey();
    gebiet.setID_LetzterEingang(id_Ergebniseingang);
    // Iterate over parties
    Stimmergebnis stimmergebnisWaehler = null;
    int anzahlWaehler = 0;
    for (GruppeGebietsspezifisch gruppeGebietsspezifisch : gebiet.getGruppeGebietsspezifischCol()) {
      // General results and total votes for lists
      Stimmergebnis stimmergebnis = createStimmergebnis(msg,
          gruppeGebietsspezifisch,
          id_Ergebniseingang,
          null);
      // hold for reference
      if (stimmergebnis != null) {
        stimmergebnisse.put(new Stimmzuordnung(gruppeGebietsspezifisch, null), stimmergebnis);
        if (gruppeGebietsspezifisch.getPosition() == WAEHLER.getPosition()) {
          stimmergebnisWaehler = stimmergebnis;
        }
        if (gruppeGebietsspezifisch.getPosition() == GUELTIGE.getPosition()
            || gruppeGebietsspezifisch.getPosition() == UNGUELTIGE.getPosition()
            || gruppeGebietsspezifisch.getPosition() == LEER.getPosition()) {
          anzahlWaehler += stimmergebnis.getStimmen();
        }
      }
      // Read candidate results if there are any
      if (gruppeGebietsspezifisch.getListe() != null) {
        for (Listenkandidatur listenkandidatur : gruppeGebietsspezifisch.getListe()
            .getListenkandidaturCol()) {
          LOGGER.debug(Messages.bind(MessageKeys.Logger_Kandidat_0, listenkandidatur
              .getPersonendaten().getAnzeigeName()));
          stimmergebnis = createStimmergebnis(msg,
              gruppeGebietsspezifisch,
              id_Ergebniseingang,
              listenkandidatur);
          // hold for reference
          if (stimmergebnis != null) {
            stimmergebnisse.put(new Stimmzuordnung(gruppeGebietsspezifisch, listenkandidatur),
                stimmergebnis);
          }
        }
      }
    }

    if ((SystemInfo.getSystemInfo().getWahlEbene() == GebietModel.EBENE_HSB || SystemInfo
        .getSystemInfo().getWahlEbene() == GebietModel.EBENE_CSB)
        && stimmergebnisWaehler != null
        && stimmergebnisWaehler.getStimmen() == 0) {
      stimmergebnisWaehler.setStimmen(anzahlWaehler);
    }

    if (msg instanceof GUIEingangMsg) {
      ((GUIEingangMsg) msg).setErgebniseingangModel(ergebniseingang.getDetails());
      if (ergebniseingang.getStatus() == ErgebniseingangKonstanten.STATE_OK) {
        ((GUIEingangMsg) msg).setLetzterGueltigerErgebniseingangModel(ergebniseingang.getDetails());
      }
    }
    return ergebniseingang;
  }

  private Stimmergebnis createStimmergebnis(EingangMsg msg,
      GruppeGebietsspezifisch gg,
      String id_Ergebniseingang,
      Listenkandidatur listenkandidatur) throws EJBException {
    try {
      Stimmergebnis stimmergebnis = null;
      int stimmen;
      if (listenkandidatur == null) {
        stimmen = msg.getGruppenstimmen(gg.getPosition());
      } else {
        stimmen = msg.getStimmen(gg.getPosition(), listenkandidatur.getListenplatz());
      }
      if (stimmen >= 0) {
        int stimmart = gg.getPosition() > 0 ? STIMMART_LISTENSTIMME : STIMMART_KEINE;
        stimmergebnis = getStimmergebnisHome().create();
        StimmergebnisModel model = stimmergebnis.getDetails();
        model.setID_Ergebniseingang(id_Ergebniseingang);
        model.setID_Gebiet(gg.getID_Gebiet());
        model.setStimmart(stimmart);
        model.setWahlergebnisart(msg.getWahlergebnisart());
        model.setStimmen(stimmen);
        stimmergebnis.setDetails(model);
        stimmergebnis.setGruppeGebietsspezifisch(gg);
        stimmergebnis.setListenkandidatur(listenkandidatur);
      }
      return stimmergebnis;
    } catch (CreateException e) {
      throw new EJBException(Messages.bind(MessageKeys.Logger_FehlerBeimAnlegen), e);
    }
  }

  /**
   * Create Ergebniseingang object
   * 
   * @param msg the message object containing data
   * @param gebiet the region the result is for
   * @return the newly created Ergebniseingang object
   */
  private Ergebniseingang createErgebniseingang(EingangMsg msg, Gebiet gebiet) {
    try {

      Ergebniseingang ee = getErgebniseingangHome().create();
      ee.setID_Wahl(gebiet.getID_Wahl());
      ee.setHerkunft(msg.getSource());
      ee.setAnwenderName(msg.getErsteller().getAnmeldename());
      ee.setWahlergebnisart(msg.getWahlergebnisart());
      ee.setUnterschiedeVorhanden(ErgebniseingangKonstanten.UnterschiedeVorhandenTyp.UNTERSCHIEDE_UNBEKANNT
          .getValue());
      ee.setStatus(ErgebniseingangKonstanten.STATE_ERROR);
      // wenn eine Zeit in der Message steht, diese verwenden!
      Date ez = msg.getEingangszeit();
      ee.setZeitstempel(new Timestamp(ez == null ? currentTimeMillis() : ez.getTime()));
      ee.setID_Erfassungseinheit(gebiet.getID_Gebiet());
      return ee;
    } catch (CreateException e) {
      throw new EJBException(Messages.bind(MessageKeys.Logger_FehlerBeimAnlegen), e);
    }
  }

  /**
   * Ermittelt das HomeIF f�r Ergebniseingang
   * 
   * @return HomeIF f�r Ergebniseingang
   * @throws EJBException bei einem Problem
   */
  private ErgebniseingangHome getErgebniseingangHome() throws EJBException {
    return (ErgebniseingangHome) findLocalHome("Ergebniseingang"); //$NON-NLS-1$
  }

  /**
   * Ermittelt den Wert eines benannten allgemeinen Schwellwerts
   * 
   * @param id_Wahl ID der Wahl
   * @param name Name des allgemeinen Schwellwerts
   * @return der numerische Wert des durch <code>name</code> bezeichneten Schwellwerts
   * @throws EJBException bei einem Problem
   */
  private int getSchwellwertWert(String id_Wahl, String name) throws EJBException {
    return getSchwellwert(id_Wahl, name).getWert().intValue();
  }

  /**
   * Ermittelt einen benannten allgemeinen Schwellwert
   * 
   * @param id_Wahl ID der Wahl
   * @param name Name des allgemeinen Schwellwerts
   * @return der durch <code>name</code> bezeichnete Schwellwert
   * @throws EJBException bei einem Problem
   */
  private Schwellwert getSchwellwert(String id_Wahl, String name) throws EJBException {
    try {
      return getSchwellwertHome().findByWahlAndName(id_Wahl, name);
    } catch (Exception e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_Schwellwert_0_NichtGefunden, name), e);
    }
  }

  /**
   * Ermittelt das HomeIF für Schwellwert
   * 
   * @return HomeIF für Schwellwert
   * @throws EJBException bei einem Problem
   */
  private SchwellwertHome getSchwellwertHome() throws EJBException {
    return ((SchwellwertHome) findLocalHome("Schwellwert")); //$NON-NLS-1$
  }

  /**
   * Komplexer Schlüssel für die Speicherung der Stimmergebnisse in Maps im Laufe der Berechnung. Es
   * ist <code>Comparable</code> und implementiert korrekt <code>equals()</code> und
   * <code>hashCode()</code>.
   * 
   * @author cos@ivu.de, IVU Traffic Technologies AG
   */
  protected static class Stimmzuordnung implements Comparable<Stimmzuordnung> {

    /** GruppeGebietsspezifisch, der die Stimmen geh�ren; Zusatzinformation */
    private final GruppeGebietsspezifisch _gruppeGebietsspezifisch;

    /**
     * Prim�rschl�ssel der GruppeGebietsspezifisch, der die Stimmen geh�ren; Bestandteil des
     * komplexen Schl�ssels
     */
    private final String _id_GruppeGebietsspezifisch;

    /** Listenkandidatur, der die Stimmen geh�ren; Zusatzinformation */
    private final Listenkandidatur _listenkandidatur;

    /**
     * Primärschlüssel der Listenkandidatur, der die Stimmen gehören; Bestandteil des komplexen
     * Schlüssels
     */
    final String _id_Listenkandidatur;

    /**
     * Erzeugt die Stimmzuordnung aus der impliziten Objektbeziehung und Stimmart eines
     * Stimmergebnisses.
     * 
     * @param stimmergebnis Stimmergebnis, dessen Objektbeziehung und Stimmart zugrunde gelegt
     *          werden
     * @throws EJBException bei einem Problem
     */
    Stimmzuordnung(Stimmergebnis stimmergebnis) throws EJBException {
      this(stimmergebnis.getGruppeGebietsspezifisch(), stimmergebnis.getListenkandidatur());
    }

    /**
     * Erzeugt die Stimmzuordung aus expliziten Angaben
     * 
     * @param gruppeGebietsspezifisch GruppeGebietsspezifisch, der die Stimmen geh�ren
     * @param stimmart Stimmart, der die Stimmen geh�ren
     */
    Stimmzuordnung(final GruppeGebietsspezifisch gruppeGebietsspezifisch,
        final Listenkandidatur listenkandidatur) {
      _gruppeGebietsspezifisch = gruppeGebietsspezifisch;
      _id_GruppeGebietsspezifisch = gruppeGebietsspezifisch.getID_GruppeGebietsspezifisch();
      _listenkandidatur = listenkandidatur;
      _id_Listenkandidatur = listenkandidatur != null
          ? listenkandidatur.getID_Listenkandidatur()
          : null;
    }

    /**
     * Gibt die GruppeGebietsspezifisch, der die Stimmen geh�ren zur�ck
     * 
     * @return GruppeGebietsspezifisch, der die Stimmen geh�ren
     */
    GruppeGebietsspezifisch getGruppeGebietsspezifisch() {
      return _gruppeGebietsspezifisch;
    }

    /**
     * Erzeugt anhand der Objektbeziehungen die Stimmzuordung f�r ein Stimmergebnis derselben Gruppe
     * und gleicher Stimmart auf dem �bergeordneten Gebiet
     * 
     * @return Stimmzuordung f�r ein Stimmergebnis derselben Gruppe und gleicher Stimmart auf dem
     *         �bergeordneten Gebiet
     */
    Stimmzuordnung getUebergeordneteStimmzuordnung() {
      return new Stimmzuordnung(_gruppeGebietsspezifisch.getUebergeordneteGG(), _listenkandidatur);
    }

    @Override
    public boolean equals(Object obj) {
      Stimmzuordnung other = (Stimmzuordnung) obj;
      return super.equals(obj)
          || (_id_GruppeGebietsspezifisch.equals(other._id_GruppeGebietsspezifisch) && _id_Listenkandidatur == other._id_Listenkandidatur);
    }

    @Override
    public int hashCode() {
      return _gruppeGebietsspezifisch.getID_GruppeGebietsspezifisch().hashCode()
          + (_listenkandidatur != null ? _listenkandidatur.getID_Listenkandidatur().hashCode() : 0);
    }

    @Override
    public int compareTo(Stimmzuordnung o) {
      int primaryOrder = _id_GruppeGebietsspezifisch.compareTo(o._id_GruppeGebietsspezifisch);
      return primaryOrder != 0 ? primaryOrder : _id_Listenkandidatur
          .compareTo(o._id_Listenkandidatur);
    }
  }

  /**
   * Ermittelt den Wert eines benannten allgemeinen Schwellwerts
   * 
   * @param name Name des allgemeinen Schwellwerts
   * @return der numerische Wert des durch <code>name</code> bezeichneten Schwellwerts
   * @throws EJBException bei einem Problem
   */
  private int getSchwellwertWert(String name) throws EJBException {
    return getSchwellwert(name).getWert().intValue();
  }

  /**
   * Ermittelt einen benannten allgemeinen Schwellwert
   * 
   * @param name Name des allgemeinen Schwellwerts
   * @return der durch <code>name</code> bezeichnete Schwellwert
   * @throws EJBException bei einem Problem
   */
  private Schwellwert getSchwellwert(String name) throws EJBException {
    try {
      Collection<Schwellwert> swCol = getSchwellwertHome().findAllByName(name);
      if (swCol.size() != 1) {
        throw new RuntimeException(Messages.bind(MessageKeys.Error_Schwellwert_0_NichtGefunden,
            name));
      }
      return swCol.iterator().next();
    } catch (Exception e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_Schwellwert_0_NichtGefunden, name), e);
    }
  }

  StimmergebnisseUntergebieteHome _stimmergebnisseUntergebieteHome = null;

  private StimmergebnisseUntergebieteHome getStimmergebnisseUntergebieteHome() {
    if (_stimmergebnisseUntergebieteHome == null) {
      _stimmergebnisseUntergebieteHome = (StimmergebnisseUntergebieteHome) findLocalHome("StimmergebnisseUntergebiete"); //$NON-NLS-1$
    }
    return _stimmergebnisseUntergebieteHome;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.eingang.EingangHandling#setGUIEingabeErlaubt(de.ivu.wahl.AnwContext, int, int,
   * boolean)
   */
  @Override
  public void setGUIEingabeErlaubt(AnwContext anwContext,
      int gebietsartErfassungseinheit,
      int nummerErfassungseinheit,
      boolean guiEingabeErlaubt) {
    Gebiet gebiet = getGebiet(anwContext, gebietsartErfassungseinheit, nummerErfassungseinheit);
    if (gebiet != null && guiEingabeErlaubt != gebiet.isGUIEingabeErlaubt()) {
      gebiet.setGUIEingabeErlaubt(guiEingabeErlaubt);
      GebietsBaum.getGebietsBaum(anwContext).invalidate();
      writeAppLog(anwContext,
          Messages.bind(MessageKeys.Logger_ManualInputOnRegion_0_Unblocked, gebiet.getName()));
    }
  }

}