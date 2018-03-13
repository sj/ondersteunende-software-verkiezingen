package de.ivu.wahl.client.beans;

import static de.ivu.ejb.EJBUtil.lookupLocal;
import static de.ivu.wahl.client.beans.ApplicationBean.getAnwContext;
import static de.ivu.wahl.client.beans.ApplicationBeanKonstanten.PREFIX;
import static de.ivu.wahl.client.util.ClientHelper.getStep;
import static de.ivu.wahl.eingang.EingangMsg.MODE_APPROVE_WARNINGS;
import static de.ivu.wahl.eingang.EingangMsg.MODE_CHECK_WARNINGS;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;

import nu.xom.Document;

import de.ivu.ejb.EJBUtil;
import de.ivu.util.debug.UserActionLogger;
import de.ivu.util.session.Step;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.SystemInfo;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.admin.PropertyHandling;
import de.ivu.wahl.admin.PropertyHandlingBean;
import de.ivu.wahl.anwender.Rechte;
import de.ivu.wahl.auswertung.erg.ResultSummary;
import de.ivu.wahl.client.util.ClientHelper;
import de.ivu.wahl.eingang.EingangHandling;
import de.ivu.wahl.eingang.EingangHandlingBean;
import de.ivu.wahl.eingang.GUIEingangMsg;
import de.ivu.wahl.eingang.GUIEingangMsgEK;
import de.ivu.wahl.export.ExportHandling;
import de.ivu.wahl.export.ExportHandlingBean;
import de.ivu.wahl.export.WeightedVotesByGroup;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ErgebniseingangKonstanten;
import de.ivu.wahl.modell.GebietInfo;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein;
import de.ivu.wahl.modell.ejb.service.GebietHandling;
import de.ivu.wahl.modell.ejb.service.GebietHandlingBean;
import de.ivu.wahl.modell.ejb.service.VoteValues;
import de.ivu.wahl.modell.ejb.service.VotesHandling;
import de.ivu.wahl.modell.ejb.service.VotesHandlingBean;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.wus.reportgen.ReportConfiguration;
import de.ivu.wahl.wus.reportgen.ReportConfiguration.ExportEml;
import de.ivu.wahl.wus.reportgen.ReportConfigurer;
import de.ivu.wahl.wus.reportgen.ReportGenerator;
import de.ivu.wahl.wus.reportgen.ReportGeneratorImpl;
import de.ivu.wahl.wus.reportgen.ReportNameComponentsP4;
import de.ivu.wahl.wus.reportgen.ReportOutputFormatEnum;
import de.ivu.wahl.wus.reportgen.ReportTemplateEnum;

/**
 * Organisiert die Eingabe von Gebietsergebnissen
 * 
 * @author cos@ivu.de mur@ivu.de klie@ivu.de tdu@ivu.de 
 * (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public class EingabeBean implements Executer, Serializable {
  private static final long serialVersionUID = 1702441359985045233L;

  public static final String REQUEST_PARAMETER_REDIRECT = "redirect"; //$NON-NLS-1$
  public static final String REPLACE_FOR_REDIRECT_SEPARATOR = "##"; //$NON-NLS-1$

  protected static final Logger LOGGER = Logger.getLogger(EingabeBean.class);
  protected static final UserActionLogger APP_LOGGER = new UserActionLogger();

  private static final String GUI_EE_EINGANG_KEY = "GUI_ErfassungseinheitEingangsNachricht"; //$NON-NLS-1$

  public static void removeGUI_ErfassungseinheitEingangsNachrichtFromStep(HttpServletRequest request) {
    getStep(request).remove(GUI_EE_EINGANG_KEY);
  }

  /**
   * erfüllt gewünschte Commands vom HTML-Client
   * 
   * @param request Der Request mit allen Informationen
   * @param n Anzahl der Commands
   */
  public void executeCommand(HttpServletRequest request, int n) {
    String cmd = request.getParameter("cmd" + (n == 0 ? "" : "" + n)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    if (cmd != null) {
      if (cmd.equals("eingabe_eingabe")) { //$NON-NLS-1$
        setGuiMsg(request);
      } else if (cmd.equals("eingabe_erlauben")) { //$NON-NLS-1$
        allowManualInput(request);
      } else {
        final String message = Messages.bind(MessageKeys.Error_Command_0_Unknown, cmd);
        LOGGER.warn(message);
        throw new RuntimeException(message);
      }
    }
  }

  /**
   * Verarbeitet einen Submit von der Oberfläche. Die zu behandelnde GuiMsg kommt aus dem Step bei
   * erfolgreichem parsen und runterschicken wird die Message allerdings aus dem Step entfernt: eine
   * aktuelle kommt dann wieder aus der DB
   */
  private void setGuiMsg(HttpServletRequest request) {
    boolean fehler = false;
    final SystemInfo systemInfo = SystemInfo.getSystemInfo();
    final WahlInfo wahlInfo = WahlInfo.getWahlInfo();
    GUIEingangMsg geMsg = (GUIEingangMsg) ClientHelper.getStep(request).get(GUI_EE_EINGANG_KEY);

    // prevent Uecker-Randow problem (could not be reproduced in OSV)
    final int gebietsnummer = ClientHelper.getIntParameter(request
        .getParameter(ApplicationBeanKonstanten.GEBIETNR));
    final int gebietsnummerMsg = geMsg.getNummerErfassungseinheit();
    if (gebietsnummer != gebietsnummerMsg) {
      LOGGER.warn("Region number of message is different from current one: " //$NON-NLS-1$
          + gebietsnummerMsg + "!=" + gebietsnummer); //$NON-NLS-1$
      geMsg = getGUIMsg(request, geMsg.getGebietsartErfassungseinheit(), gebietsnummer, false);
    }

    geMsg.setInfoText(null);
    geMsg.setConfirmationText(null);

    int art;

    if (request.getParameter(PREFIX + "bestaetigen") != null) { //$NON-NLS-1$
      art = MODE_APPROVE_WARNINGS;
    } else {
      art = MODE_CHECK_WARNINGS;
    }
    geMsg.setInputMode(art);

    String paramVal = request.getParameter(PREFIX
        + ErgebniseingangKonstanten.UNTERSCHIEDE_FORM_ELEMENT_NAME);
    if (paramVal != null) {
      geMsg.setUnterschiedeVorhanden(ClientHelper.getBooleanParameter(paramVal));
    } else {
      geMsg.setUnterschiedeVorhanden(null);
    }

    // Fehler zurücksetzen ... wenn es welche gibt werden diese durch die
    // neue Eingabe bestimmt
    geMsg.setStatus(ErgebniseingangKonstanten.STATE_OK);
    geMsg.setFehler(null);
    Map<Integer, GUIEingangMsg.Gruppendaten> gd = geMsg.getGruppendaten();
    Iterator<Integer> gdKey = gd.keySet().iterator();
    while (gdKey.hasNext()) {
      GUIEingangMsg.Gruppendaten gErg = gd.get(gdKey.next());
      final int posGruppe = gErg.getPosition();
      // Fehler zurücksetzen ... wenn es welche gibt werden diese durch die
      // neue Eingabe bestimmt
      geMsg.resetGruppeUndKandidatenfehler(posGruppe);

      // On PSB level, do not let the user enter the number of elidgible voters
      if (!(systemInfo.getWahlEbene() == GebietModel.EBENE_PSB && posGruppe == GruppeAllgemein.WAHLBERECHTIGTE
          .getPosition())) {
        final String paramNameGruppe = PREFIX + "GRUPPE_" + posGruppe; //$NON-NLS-1$
        fehler = transferInputGruppe(request,
            paramNameGruppe,
            geMsg,
            posGruppe,
            fehler,
            wahlInfo.isReferendum());
        if (posGruppe >= 0) {
          Map<Integer, GUIEingangMsg.Kandidat> kandidaten = gErg.getKandidaten();
          Iterator<Integer> kKey = kandidaten.keySet().iterator();
          // Kandidatenzeilen
          while (kKey.hasNext()) {
            GUIEingangMsg.Kandidat kandidat = kandidaten.get(kKey.next());
            int posKandidat = kandidat.getListenposition();
            final String paramNameKandidat = PREFIX + "KANDIDAT_" + posGruppe + "_" + posKandidat; //$NON-NLS-1$ //$NON-NLS-2$
            fehler = transferInputKandidat(request,
                paramNameKandidat,
                geMsg,
                posGruppe,
                posKandidat,
                fehler,
                wahlInfo.isReferendum());
          }
        }
      }

    }

    if (!fehler) {
      String confirmationText = null;
      geMsg.setStatus(ErgebniseingangKonstanten.STATE_OK);
      // No error found, send data to server to be validated there
      removeGUI_ErfassungseinheitEingangsNachrichtFromStep(request);
      geMsg.setEingangszeit(new Date());
      try {
        getEingangHandling().processInputMsg(geMsg);
        if (ErgebniseingangKonstanten.STATE_OK == geMsg.getStatus()) {
          confirmationText = Messages.bind(MessageKeys.GuiMsg_ErfolgreichUebernommen);
          createEML510(geMsg);
        } else if (ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK == geMsg.getStatus()) {
          // necessary for redirect to result page
          confirmationText = ""; //$NON-NLS-1$
        }
      } catch (ImportException e) {
        LOGGER.error(e, e);
        geMsg.addFehler(e.getMessage());
        geMsg.setStatus(ErgebniseingangKonstanten.STATE_ERROR);
      } catch (Exception e) {
        LOGGER.error(e, e);
        geMsg.addFehler(Messages.bind(MessageKeys.Error_DieDateiKonnteNichtAngelegtWerden));
        geMsg.setStatus(ErgebniseingangKonstanten.STATE_ERROR);
      }
      geMsg.setConfirmationText(confirmationText);
      if (confirmationText != null) {
        // everything is fine -> do redirect to result page
        String replaceForRedirect = ApplicationBeanKonstanten.WORK
            + "=" + ApplicationBeanKonstanten.GEBE //$NON-NLS-1$
            + REPLACE_FOR_REDIRECT_SEPARATOR + ApplicationBeanKonstanten.WORK
            + "=" + ApplicationBeanKonstanten.GEB_ERG; //$NON-NLS-1$
        request.setAttribute(REQUEST_PARAMETER_REDIRECT, replaceForRedirect);
      }
    }
    getStep(request).put(GUI_EE_EINGANG_KEY, geMsg);
  }

  private void createEML510(GUIEingangMsg geMsg)
      throws ImportException, IOException, ParserConfigurationException {
    File filedef = getPropertyHandling().getFileProperty(Konstanten.PROP_UPLOADDIR);
    if (filedef == null) {
      return;
    }

    Document eml510 = getExportHandling().createEML510(geMsg.getErfassungseinheit(), false);
    // isRootRegion: same condition as in ExportHandlingBean.getIdForEml510()
    boolean isRootRegion = (geMsg.getErfassungseinheit().getID_UebergeordnetesGebiet() == null);
    SystemInfo systemInfo = SystemInfo.getSystemInfo();
    boolean isCSB = (systemInfo.getWahlEbene() == GebietModel.EBENE_CSB);

    ReportGenerator generator = new ReportGeneratorImpl(APP_LOGGER);
    if (isCSB || !WahlInfo.getWahlInfo().isEK()) {
      ReportConfigurer rc = new ReportConfigurer();
      rc.setDocument(ReportTemplateEnum.OSV4_1, ReportOutputFormatEnum.PDF);
      rc.setProgramNameAndVersion(EJBUtil.getProgramSpecificAffix(),
          ApplicationBean.getVersionString());
      rc.configureFileName(filedef,
          ReportNameComponentsP4.fromEML510(eml510, systemInfo.isInputmodusComplete()),
          false);
      rc.configureWhichFiles(ExportEml.OVERWRITE, true);
      rc.setCSB(isCSB && isRootRegion);

      generator.createReport(rc.getReportConfiguration(), eml510);
    }
    if (!systemInfo.isInputmodusComplete()) {
      File filedefExp = getPropertyHandling().getFileProperty(Konstanten.PROP_EXPORT_FORMULAR_DIR);
      Document eml510Root = getExportHandling().createEML510(WahlInfo.getWahlInfo().getWahl()
          .getWurzelgebiet(),
          false);

      ReportConfigurer rc = new ReportConfigurer();
      rc.setDocument(ReportTemplateEnum.OSV4_1, ReportOutputFormatEnum.PDF);
      rc.setProgramNameAndVersion(EJBUtil.getProgramSpecificAffix(),
          ApplicationBean.getVersionString());
      rc.configureFileName(filedefExp,
          ReportNameComponentsP4.fromEML510(eml510Root, systemInfo.isInputmodusComplete()),
          false);
      rc.configureWhichFiles(ExportEml.OVERWRITE, true);
      ReportConfiguration reportConfigurationOSV4_1Root = rc.getReportConfiguration();

      generator.createReport(reportConfigurationOSV4_1Root, eml510Root);
    }
    getExportHandling().updateBackupArchive();
  }

  /**
   * @param request
   * @param paramName
   * @param geMsg
   * @param gruppenposition
   * @param fehler
   * @param referendum
   * @return <code>true</code> wenn ein Fehler festgestellt wurde
   */
  private boolean transferInputGruppe(HttpServletRequest request,
      String paramName,
      GUIEingangMsg geMsg,
      int gruppenposition,
      boolean pFehler,
      boolean referendum) {

    boolean fehler = pFehler;
    String paramVal = request.getParameter(paramName);
    if (paramVal != null && paramVal.length() > 0) {
      try {
        int stimmen = ClientHelper.getIntParameter(paramVal);
        geMsg.setGruppenstimmen(gruppenposition, stimmen);
      } catch (NumberFormatException e) {
        geMsg.addFehler(Messages.getString(MessageKeys.Msg_WertMussEineZahlSein));
        geMsg.addGruppefehler(gruppenposition,
            Messages.getString(MessageKeys.Msg_WertMussEineZahlSein));
        geMsg.setStatus(ErgebniseingangKonstanten.STATE_ERROR);
        fehler = true;
      }
    } else if (!referendum) {
      geMsg.addFehler(Messages.getString(MessageKeys.Msg_WertMussGefuelltWerden));
      geMsg.addGruppefehler(gruppenposition,
          Messages.getString(MessageKeys.Msg_WertMussGefuelltWerden));
      geMsg.setStatus(ErgebniseingangKonstanten.STATE_ERROR);
      fehler = true;
    }
    return fehler;
  }

  private boolean transferInputKandidat(HttpServletRequest request,
      String paramName,
      GUIEingangMsg geMsg,
      int gruppenposition,
      int listenposition,
      boolean pFehler,
      boolean referendum) {

    boolean fehler = pFehler;
    String paramVal = request.getParameter(paramName);
    if (paramVal != null && paramVal.length() > 0) {
      try {
        int stimmen = ClientHelper.getIntParameter(paramVal);
        geMsg.setStimmen(gruppenposition, listenposition, stimmen);
        // under the acceptance that with a referendum always only one candidate is put on below a
        // party as a referendum answer, the party gets the voices of the candidate assigned,
        // because these are not grasped by gui
        if (referendum) {
          geMsg.setGruppenstimmen(gruppenposition, stimmen);
        }

      } catch (NumberFormatException e) {
        geMsg.addFehler(Messages.getString(MessageKeys.Msg_WertMussEineZahlSein));
        geMsg.setKandidatenfehler(gruppenposition,
            listenposition,
            Messages.getString(MessageKeys.Msg_WertMussEineZahlSein));
        geMsg.setStatus(ErgebniseingangKonstanten.STATE_ERROR);
        fehler = true;
      }
    } else {
      geMsg.addFehler(Messages.getString(MessageKeys.Msg_WertMussGefuelltWerden));
      geMsg.setKandidatenfehler(gruppenposition,
          listenposition,
          Messages.getString(MessageKeys.Msg_WertMussGefuelltWerden));
      geMsg.setStatus(ErgebniseingangKonstanten.STATE_ERROR);
      fehler = true;
    }
    return fehler;
  }

  public ResultSummary getResultSummary(HttpServletRequest request, GebietInfo gebietInfo) {
    int defaultSessionTimeout = ClientHelper.changeSessionTimeout(request, 60);
    int gebietsart = gebietInfo.getGebietsart();
    int gebietsnummer = gebietInfo.getNummer();
    ResultSummary resultSummary = getVotesHandling().getResultSummary(getAnwContext(request),
        gebietsart,
        gebietsnummer);
    ClientHelper.setSessionTimeoutBackToDefaultPlus(request, defaultSessionTimeout);
    return resultSummary;
  }

  /**
   * Besorgt eine vorbereitete Eingangsmessage. Die Message wird immer "unten" abgeholt und dort
   * zusammengebaut.
   * 
   * @param request HttpRequest
   * @param gebietInfo region metadata
   * @param forDisplay <code>true</code> ask for valid results only <code>false</code> initialize
   *          input GUI
   * @return data object with metadata and voting results
   */
  public GUIEingangMsg getGUIMsg(HttpServletRequest request,
      GebietInfo gebietInfo,
      boolean forDisplay) {

    int gebietsart = gebietInfo.getGebietsart();
    int gebietsnummer = gebietInfo.getNummer();
    return getGUIMsg(request, gebietsart, gebietsnummer, forDisplay);
  }

  /**
   * Besorgt eine vorbereitete Eingangsmessage. Die Message wird immer "unten" abgeholt und dort
   * zusammengebaut.
   * 
   * @param request HttpRequest
   * @param gebietsart region type
   * @param gebietsnummer region number
   * @param forDisplay <code>true</code> ask for valid results only <code>false</code> initialize
   *          input GUI
   * @return data object with metadata and voting results
   */
  public GUIEingangMsg getGUIMsg(HttpServletRequest request,
      int gebietsart,
      int gebietsnummer,
      boolean forDisplay) {
    try {
      Step step = getStep(request);
      GUIEingangMsg geMsg = (GUIEingangMsg) step.get(GUI_EE_EINGANG_KEY);

      /**
       * Wenn geMsg nicht im Step ist oder geMsg zum falschen Gebiet geh�rt oder bereits korrekt
       * verarbeitet wurde wird sie neu vom Server geholt.
       */
      if (geMsg == null || geMsg.getGebietsartErfassungseinheit() != gebietsart
          || geMsg.getNummerErfassungseinheit() != gebietsnummer
          || geMsg.getStatus() == ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK
          || geMsg.getStatus() == ErgebniseingangKonstanten.STATE_OK && geMsg.getInfoText() == null
          || forDisplay || !new Boolean(forDisplay).equals(geMsg.getForDisplay())) { // OSV-1584
                                                                                     // forDisplay
                                                                                     // changed?
        geMsg = getEingangHandling().getGUIMsg(getAnwContext(request),
            gebietsart,
            gebietsnummer,
            forDisplay);

        // Save data message in step object
        step.put(GUI_EE_EINGANG_KEY, geMsg);
      } else {
        // OSV-1652 check source. If other souce is needed, get new GUIEingangMsg
        int newSource = getEingangHandling().sourceForGUIMsg(getAnwContext(request),
            gebietsart,
            gebietsnummer,
            forDisplay);
        int oldSource = geMsg.getSource();
        if (oldSource != newSource) {
          LOGGER.info("EingabeBean.getGUIMsg(): Fetching new GUIEingangMsg, bypass the cache. This solves OSV-1652. If this log line occurs often, review OSV-1652.");
          geMsg = getEingangHandling().getGUIMsg(getAnwContext(request),
              gebietsart,
              gebietsnummer,
              forDisplay);
        }

      }
      return geMsg;
    } catch (Exception e) {
      LOGGER.error(e, e);
      return null;
    }
  }

  public GUIEingangMsgEK getGUIMsgEK(HttpServletRequest request,
      GebietInfo gebietInfo,
      boolean forDisplay) {
    VoteValues voteValues = getGebietHandling().getVoteValues();
    GUIEingangMsg msg = getGUIMsg(request, gebietInfo, forDisplay);
    Integer voteValue = voteValues.get(gebietInfo.getNummer());
    if (voteValue != null) {
      return new GUIEingangMsgEK(msg, voteValue);
    } else {
      WeightedVotesByGroup weightedVotesByGroup = getVotesHandling().getWeightedVotesByGroup();
      return new GUIEingangMsgEK(msg, weightedVotesByGroup);
    }
  }

  private transient GebietHandling _gebietHandling = null;

  private GebietHandling getGebietHandling() {
    if (_gebietHandling == null) {
      try {
        _gebietHandling = lookupLocal(GebietHandlingBean.class.getSimpleName());
      } catch (Exception e) {
        LOGGER.error(e, e);
      }
    }
    return _gebietHandling;
  }

  private transient VotesHandling _votesHandling = null;

  private VotesHandling getVotesHandling() {
    if (_votesHandling == null) {
      try {
        _votesHandling = lookupLocal(VotesHandlingBean.class.getSimpleName());
      } catch (Exception e) {
        LOGGER.error(e, e);
      }
    }
    return _votesHandling;
  }

  private transient EingangHandling _eingangHandling = null;

  /**
   * @return EingangHandling
   */
  private EingangHandling getEingangHandling() {
    if (_eingangHandling == null) {
      try {
        _eingangHandling = lookupLocal(EingangHandlingBean.class.getSimpleName());
      } catch (Exception e) {
        LOGGER.error(e, e);
      }
    }
    return _eingangHandling;
  }

  private transient ExportHandling _exportHandling = null;

  /**
   * @return ExportHandling
   */
  private ExportHandling getExportHandling() {
    if (_exportHandling == null) {
      try {
        _exportHandling = lookupLocal(ExportHandlingBean.class.getSimpleName());
      } catch (Exception e) {
        LOGGER.error(e, e);
      }
    }
    return _exportHandling;
  }

  private transient PropertyHandling _propertyHandling = null;

  /**
   * @return PropertyHandling
   */
  private PropertyHandling getPropertyHandling() {
    if (_propertyHandling == null) {
      try {
        _propertyHandling = lookupLocal(PropertyHandlingBean.class.getSimpleName());
      } catch (Exception e) {
        LOGGER.error(e, e);
      }
    }
    return _propertyHandling;
  }

  /**
   * Allows manual input on the given region
   * 
   * @param request
   */
  private void allowManualInput(HttpServletRequest request) {
    if (getAnwContext(request).checkRight(Rechte.R_EINGABE_ERLAUBEN)) {
      GUIEingangMsg geMsg = (GUIEingangMsg) ClientHelper.getStep(request).get(GUI_EE_EINGANG_KEY);
      getEingangHandling().setGUIEingabeErlaubt(getAnwContext(request),
          geMsg.getGebietsartErfassungseinheit(),
          geMsg.getNummerErfassungseinheit(),
          true);
    }
  }

}