/*
 * 
 * Copyright (c) 2002-9 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import static de.ivu.ejb.EJBUtil.findLocalHomeNoCache;
import static de.ivu.ejb.EJBUtil.lookupLocal;
import static de.ivu.wahl.Konstanten.BR;
import static de.ivu.wahl.Konstanten.PREFILL_DB;
import static de.ivu.wahl.Konstanten.PROP_BENACHRICHTIGUNG_GEWAEHLTE;
import static de.ivu.wahl.Konstanten.PROP_EXPORT_FORMULAR_DIR;
import static de.ivu.wahl.Konstanten.PROP_P22_1_APPENDIX;
import static de.ivu.wahl.Konstanten.PROP_P22_1_D1;
import static de.ivu.wahl.Konstanten.PROP_P22_2;
import static de.ivu.wahl.Konstanten.PROP_P22_2_APPENDIX;
import static de.ivu.wahl.Konstanten.PROP_U16_D1;
import static de.ivu.wahl.Konstanten.PROP_UPLOADDIR;
import static de.ivu.wahl.client.beans.ApplicationBean.getAnwContext;
import static de.ivu.wahl.client.beans.ApplicationBeanKonstanten.PREFIX;
import static de.ivu.wahl.client.beans.EingabeBean.removeGUI_ErfassungseinheitEingangsNachrichtFromStep;
import static de.ivu.wahl.modell.GebietModel.GEBIETSART_STIMMBEZIRK;
import static de.ivu.wahl.modell.GruppeKonstanten.GRUPPENART_PARTEI;
import static de.ivu.wahl.modell.SchwellwertModel.SWERT_ALLG_KEYS;
import static de.ivu.wahl.modell.WahlModel.STATE_METADATA_P4;
import static de.ivu.wahl.modell.WahlModel.STATE_NEW_RESULT;
import static java.io.File.separatorChar;
import static java.lang.Integer.parseInt;
import static java.util.Collections.emptyList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.log4j.Logger;

import nu.xom.Document;

import de.ivu.ejb.EJBUtil;
import de.ivu.util.debug.UserActionLogger;
import de.ivu.wahl.AnwContext;
import de.ivu.wahl.BasiseinstellungMultiMap;
import de.ivu.wahl.GebietsBaum;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.admin.DialogStateHolder;
import de.ivu.wahl.admin.SchwellwertHandling;
import de.ivu.wahl.admin.SchwellwertHandlingBean;
import de.ivu.wahl.admin.StateHandling;
import de.ivu.wahl.anwender.AnwenderException;
import de.ivu.wahl.anwender.AnwenderHandling;
import de.ivu.wahl.anwender.AnwenderHandlingBean;
import de.ivu.wahl.client.util.ClientHelper;
import de.ivu.wahl.dataimport.AbstractImportEML.UploadStreamHandler;
import de.ivu.wahl.eingang.EingangMsg;
import de.ivu.wahl.eingang.GUIEingangMsg;
import de.ivu.wahl.export.XMLTags;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ErgebniseingangKonstanten;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein;
import de.ivu.wahl.modell.RechtegruppeModel;
import de.ivu.wahl.modell.ejb.Anwender;
import de.ivu.wahl.modell.ejb.AnwenderHome;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.GebietHome;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.GruppeHome;
import de.ivu.wahl.modell.ejb.ListeHome;
import de.ivu.wahl.modell.ejb.Listenkandidatur;
import de.ivu.wahl.modell.ejb.Personendaten;
import de.ivu.wahl.modell.ejb.Wahl;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.util.EMLFilenameCheck;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;
import de.ivu.wahl.wus.reportgen.GeneratedFileExistsException;
import de.ivu.wahl.wus.reportgen.ReportConfiguration;
import de.ivu.wahl.wus.reportgen.ReportConfiguration.ExportEml;
import de.ivu.wahl.wus.reportgen.ReportConfigurer;
import de.ivu.wahl.wus.reportgen.ReportGenerator;
import de.ivu.wahl.wus.reportgen.ReportGeneratorImpl;
import de.ivu.wahl.wus.reportgen.ReportNameComponentsP5;
import de.ivu.wahl.wus.reportgen.ReportNameComponentsP5CSV;
import de.ivu.wahl.wus.reportgen.ReportOutputFormatEnum;
import de.ivu.wahl.wus.reportgen.ReportTemplateEnum;
import de.ivu.wahl.wus.reportgen.RgConstants;

/**
 * Administrative client functions, mostly using session bean functions
 * 
 * @author mur@ivu.de, klie@ivu.de
 */

public class AdministrationBean extends RepositoryPropertyHandler
    implements
      ExportP5Commands,
      Executer,
      Serializable {

  private static final long serialVersionUID = -3964303085550216507L;

  /** Logger for instances of this class */
  protected final static Logger LOGGER = Logger.getLogger(AdministrationBean.class);
  protected static final UserActionLogger APP_LOGGER = new UserActionLogger();

  private static final String ID_NO_REGION = "-99"; //$NON-NLS-1$

  private static final String EJB_GRUPPE = "Gruppe"; //$NON-NLS-1$
  private static final String EJB_LISTE = "Liste"; //$NON-NLS-1$
  private static final String EJB_ANWENDER = "Anwender"; //$NON-NLS-1$

  private static final String PREFIX_CMD = "cmd"; //$NON-NLS-1$

  private static final String CMD_ADM_STIMMBEZIRK_LOESCHEN = "adm_Stimmbezirk_loeschen"; //$NON-NLS-1$
  private static final String CMD_ADM_IMPORT_STIMMBEZIRKE = "adm_import_Stimmbezirke"; //$NON-NLS-1$
  public static final String CMD_ADM_STIMMBEZIRK_ANZAHL = "adm_Stimmbezirk_anzahl"; //$NON-NLS-1$

  private static final String CMD_ADM_CHANGE_PW = "adm_change_pw"; //$NON-NLS-1$
  private static final String CMD_ADM_DEL_ANWENDER = "adm_delAnwender"; //$NON-NLS-1$
  private static final String CMD_ADM_SAVE_ANWENDER = "adm_saveAnwender"; //$NON-NLS-1$
  private static final String CMD_ADM_WAHL_ZURUECK = "adm_wahlZurueck"; //$NON-NLS-1$
  private static final String CMD_ADM_PROP_EINGABE = "adm_propEingabe"; //$NON-NLS-1$
  private static final String CMD_ADM_FREIGABE = "adm_freigabe"; //$NON-NLS-1$
  public static final String CMD_ADM_RE_INDEX_DATABASE = "adm_re_index_database"; //$NON-NLS-1$
  private static final String CMD_ADM_SW_EINGABE_ALLG = "adm_swEingabeAllg"; //$NON-NLS-1$
  public static final String CMD_ADM_KANDIDATEN_WAEHLBAR = "adm_Kandidaten_waehlbar"; //$NON-NLS-1$
  public static final String CMD_ADM_VOTE_VALUES = "adm_vote_values"; //$NON-NLS-1$

  private static final String PARAM_ANW_NEW_PW1 = PREFIX + "anw_new_pw_1"; //$NON-NLS-1$
  private static final String PARAM_ANW_OLD_PW = PREFIX + "anw_old_pw"; //$NON-NLS-1$
  private static final String PARAM_RELEASED = PREFIX + "freigegeben"; //$NON-NLS-1$
  private static final String PARAM_ANW_FAULTS = PREFIX + "anw_fehlanmeldungen"; //$NON-NLS-1$
  private static final String PARAM_ANW_PASSWORD1 = PREFIX + "anw_passwort1"; //$NON-NLS-1$
  private static final String PARAM_ANW_REGION = PREFIX + "anw_gebiet"; //$NON-NLS-1$
  private static final String PARAM_ANW_NAME = PREFIX + "anw_name"; //$NON-NLS-1$
  private static final String PARAM_ANW_USERNAME = PREFIX + "anw_anwendername"; //$NON-NLS-1$
  private static final String PARAM_ID_USER = PREFIX + "id_anwender"; //$NON-NLS-1$
  private static final String PARAM_ON = "on"; //$NON-NLS-1$
  public static final String PARAM_ACCEPT = PREFIX + "uebernehmen"; //$NON-NLS-1$
  private static final String PARAM_ID = PREFIX + "id"; //$NON-NLS-1$
  public static final String PARAM_SUFFIX_NR = "_nr"; //$NON-NLS-1$
  private static final String PARAM_SUFFIX_WAHLBERECHTIGTE = "_wahlberechtigte"; //$NON-NLS-1$
  private static final String PARAM_SUFFIX_NAME = "_name"; //$NON-NLS-1$
  private static final String PARAM_SUFFIX_ZIPCODE = "_zipcode"; //$NON-NLS-1$
  private static final String PARAM_SUFFIX_POSTALVOTE = "_postalvote"; //$NON-NLS-1$
  private static final String PARAM_DELETE = "Del"; //$NON-NLS-1$
  private static final String PARAM_READY_WITH_EDIT = PREFIX + "readyWithEdit"; //$NON-NLS-1$
  private static final String PARAM_ANZAHL_NEUE_GEBIETE = PREFIX + "anzahlNeueGebiete"; //$NON-NLS-1$
  private static final String PARAM_ANW_ID_USER = "ANW_" + PARAM_ID_USER; //$NON-NLS-1$
  public static final String FELD_STIMMBEZIRKDEFINITION = PREFIX + "pollingstation"; //$NON-NLS-1$
  public static final String PARAM_BUSY = "busy"; //$NON-NLS-1$

  private static final String EMPTY_STRING = new String();

  private transient SchwellwertHandling _schwellwertHandling;

  private final Map<ExportP5Type, DialogStateHolder> _stateMap = new HashMap<ExportP5Type, DialogStateHolder>() {
    private static final long serialVersionUID = 1L;
    {
      for (ExportP5Type type : ExportP5Type.values()) {
        put(type, new DialogStateHolder());
      }
    }
  };

  /**
   * anwenderMsg wird von der Webseite benutzt um eine Statusmeldung f�r den Anwender anzuzeigen
   */
  public String _adminMsg = null;
  public String _confirmationMsg = null;
  public String _adminMsgStimmbezFile;
  public String _adminMsgStimmbez;
  public String _adminMsgStimmbezWarning = null;
  public String _adminMsgStimmbezConfirmation = null;

  /**
   * Ausführen eines Befehls
   * 
   * @param request HTTP-Request
   * @param n index des Befehls
   */
  @Override
  public void executeCommand(HttpServletRequest request, int n) {
    String cmd = request.getParameter(PREFIX_CMD + (n == 0 ? EMPTY_STRING : String.valueOf(n)));
    AnwContext anwContext = getAnwContext(request);

    for (ExportP5Type config : ExportP5Type.values()) {
      if (cmd.equals(config.getExportCommand())) {
        exportP5(request, config);
        return;
      }
    }

    if (CMD_ADM_KANDIDATEN_WAEHLBAR.equals(cmd)) {
      // Eingabe von verstorbenen Kandidaten
      handleKandidatenWaehlbar(request);
    } else if (CMD_ADM_VOTE_VALUES.equals(cmd)) {
      // Eingabe von Stimmgewichten
      handleVoteValues(request);
    } else if (CMD_ADM_SW_EINGABE_ALLG.equals(cmd)) {
      // Eingabe von allgemeinen Schwellwerten
      handleSWEingabeAllg(request);
    } else if (CMD_ADM_FREIGABE.equals(cmd)) {
      // Freigeben der aktuellen Wahl
      handleFreigabe(request);
    } else if (CMD_ADM_RE_INDEX_DATABASE.equals(cmd)) {
      reIndexStimmergebnis(request);
    } else if (CMD_ADM_PROP_EINGABE.equals(cmd)) {
      // Eingabe von Properties
      handlePropEingabeAllg(request);
    } else if (CMD_ADM_WAHL_ZURUECK.equals(cmd)) {
      // Alle Wahl f�r die aktuelle Wahlergebnisart zur�cksetzen
      wahlZuruecksetzen(request);
    } else if (CMD_ADM_SAVE_ANWENDER.equals(cmd)) {
      // Anwenderdaten speichern
      saveAnwender(request);
    } else if (CMD_ADM_DEL_ANWENDER.equals(cmd)) {
      // Anwenderdaten speichern
      deleteAnwender(request.getParameter(PARAM_ANW_ID_USER), anwContext);
    } else if (CMD_ADM_CHANGE_PW.equals(cmd)) {
      // Anwenderdaten speichern
      changePassword(request);

      // Eingabe von Properties f�r Exporte
    } else if (CMD_ADM_PROP_EINGABE_P22_1_1.equals(cmd)) {
      handleProp(ExportP5Type.P22_1, PROP_P22_1_D1, request);
    } else if (CMD_ADM_PROP_EINGABE_P22_2_1.equals(cmd)) {
      handleProp(ExportP5Type.P22_2, PROP_P22_2, request);
    } else if (CMD_ADM_PROP_EINGABE_U16_1.equals(cmd)) {
      handleProp(ExportP5Type.U16, PROP_U16_D1, request);
    } else if (CMD_ADM_PROP_EINGABE_P22_1_APPENDIX_1.equals(cmd)) {
      handleProp(ExportP5Type.P22_1_APPENDIX, PROP_P22_1_APPENDIX, request);
    } else if (CMD_ADM_PROP_EINGABE_P22_2_APPENDIX_2.equals(cmd)) {
      handleProp(ExportP5Type.P22_2_APPENDIX, PROP_P22_2_APPENDIX, request);
    } else if (CMD_ADM_PROP_EINGABE_GEW_BEN.equals(cmd)) {
      handleProp(ExportP5Type.KAN_BEN, PROP_BENACHRICHTIGUNG_GEWAEHLTE, request);

    } else if (CMD_ADM_STIMMBEZIRK_ANZAHL.equals(cmd)) {
      createStimmbezirke(request);
    } else if (CMD_ADM_IMPORT_STIMMBEZIRKE.equals(cmd)) {
      readStimmbezirke(request);
    } else if (CMD_ADM_STIMMBEZIRK_LOESCHEN.equals(cmd)) {
      deleteStimmbezirk(request);
    } else {
      throw new RuntimeException(Messages.bind(MessageKeys.Error_Command_0_Unknown, cmd));
    }
  }

  /**
   * Eingabe von Properties
   */
  private void handleProp(ExportP5Type type,
      BasiseinstellungMultiMap prop,
      HttpServletRequest request) {
    getState(type).setModus(handlePropEingabeAllg(request, prop, getState(type).getModus()));
  }

  private void readStimmbezirke(HttpServletRequest request) {
    try {
      boolean isBusy = request.getSession().getAttribute(PARAM_BUSY) != null;
      if (isBusy) {
        // already reading a file
        return;
      }
      request.getSession().setAttribute(PARAM_BUSY, true);
      FileUpload fileUpload = new ServletFileUpload();
      DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
      diskFileItemFactory.setSizeThreshold(BasicUploadBean.SIZE_MAX_BYTE);
      fileUpload.setFileItemFactory(diskFileItemFactory);
      fileUpload.setSizeMax(BasicUploadBean.SIZE_MAX_BYTE);
      @SuppressWarnings("unchecked")
      final List<FileItem> fileItems = fileUpload.parseRequest(new ServletRequestContext(request));
      String remoteHost = request.getRemoteHost();
      UploadStreamHandler handler = new UploadStreamHandler();

      for (FileItem item : fileItems) {
        if (item.isFormField()) {
          LOGGER.debug("POST contains unexpected data, which will be ignored: " //$NON-NLS-1$
              + item.getFieldName() + ' ' + item.getString());
        } else {
          String fileName = item.getName();
          String feldName = item.getFieldName();
          if (fileName != null && !fileName.isEmpty()) {
            fileName = ('/' + fileName).replace('\\', separatorChar);
            LOGGER.info("Filename for " + feldName + ": " + fileName); //$NON-NLS-1$ //$NON-NLS-2$
            byte[] input = item.get();
            URL url = handler.add(remoteHost, fileName, input);
            getImportHandling().createStimmbezirke(url);
          }
        }
      }
    } catch (Exception e) {
      _adminMsgStimmbezFile = e.getMessage();
      request.getSession().removeAttribute(PARAM_BUSY);
      LOGGER.info(e.getMessage(), e);
    }

    request.getSession().removeAttribute(PARAM_BUSY);
  }

  /**
   * Create polling station objects
   * 
   * @param request contains number of required stations (user input)
   */
  private void createStimmbezirke(HttpServletRequest request) {
    _adminMsgStimmbez = ""; //$NON-NLS-1$
    _adminMsgStimmbezWarning = ""; //$NON-NLS-1$
    _adminMsgStimmbezConfirmation = ""; //$NON-NLS-1$
    final File dpath = getPropertyHandling().getFileProperty(PROP_UPLOADDIR);

    setStimmbezirke(request);
    final String parameter = request.getParameter(PARAM_ANZAHL_NEUE_GEBIETE);
    boolean force = ClientHelper.getBooleanParameter(request.getParameter(PARAM_FORCE), false);
    // Create new polling station objects
    if (parameter != null && !parameter.isEmpty()) {
      try {
        int anzGebiete = Integer.parseInt(parameter);
        getImportHandling().createStimmbezirke(anzGebiete);
      } catch (ImportException e) {
        _adminMsgStimmbez += Messages.bind(MessageKeys.Error_CreateStimmgebiet) + BR;
        return;
      } catch (Exception e) {
        _adminMsgStimmbez += Messages.bind(MessageKeys.Error_AnzahlStimmgebiet) + BR;
        return;
      }
    }
    final String readyWithEditing = request.getParameter(PARAM_READY_WITH_EDIT);
    if (readyWithEditing != null && !readyWithEditing.isEmpty() && _adminMsgStimmbez.length() == 0) {
      if (getStimmbezirkeToAdmin().size() == 0) {
        // no pollingstations created
        _adminMsgStimmbez += Messages.bind(MessageKeys.Msg_KeineStimmbezirkeAdministriert) + BR;

      } else if (_adminMsgStimmbezWarning.length() == 0 || force) {
        // finish metadatenimport
        final WahlInfo wahlInfo = WahlInfo.getWahlInfo();
        try {
          final Document eml110 = getExportHandling().createEML110();
          // Stembureaus EP2004 Bollenstad.eml.xml
          boolean isGrOrDr = false;
          String fileName = RgConstants.FILENAME_110B + "_" + wahlInfo.getID_Wahl() //$NON-NLS-1$
              + (isGrOrDr ? "" : "_" + wahlInfo.getWahl().getWurzelgebiet().getName()) //$NON-NLS-1$ //$NON-NLS-2$
              + RgConstants.SUFFIX_EML_XML;
          String unterverzeichnis = EMLFilenameCheck.getUnterverzeichnis(EMLFilenameCheck
              .reduceFilenameFromSlashAsPrefix(fileName));
          LOGGER.info("filename : " + fileName); //$NON-NLS-1$
          fileName = new File(fileName.replace('\\', separatorChar)).getName();
          File parentFile = new File(dpath, unterverzeichnis);
          File realFile = new File(parentFile, fileName);
          parentFile.mkdirs();
          if (!parentFile.isDirectory()) {
            _adminMsgStimmbez += Messages
                .bind(MessageKeys.Error_Verzeichnis_0_KonnteNichtAngelegtWerden,
                    parentFile.getPath())
                + BR;
            return;
          }
          FileOutputStream fout = new FileOutputStream(realFile);
          fout.write(eml110.toXML().getBytes());
          fout.close();

          _adminMsgStimmbezConfirmation += Messages
              .bind(MessageKeys.Die_Datei_wurde_erfolgreich_nach_0_gespeichert,
                  parentFile.getPath())
              + BR;

          // finally set new state
          if (wahlInfo.getWahl() == null || wahlInfo.getWahl().getStatus() != STATE_NEW_RESULT) {
            getAdminHandling().setWahlStatus(wahlInfo.getID_Wahl(), STATE_METADATA_P4);
          }

          if (getPropertyHandling().getBooleanProperty(PREFILL_DB)) {
            prefillDatabase();
          }

          // clear "Gebietsbaum" cache
          AnwContext anwContext = getAnwContext(request);
          if (anwContext != null) {
            GebietsBaum gebietsBaum = getStateHandling().getGebietsBaum(anwContext);
            if (gebietsBaum != null) {
              gebietsBaum.invalidate();
            }
          }

        } catch (Exception e) {
          _adminMsgStimmbez += Messages.bind(MessageKeys.ERROR_DateinameWurdeNichtAngegeben)
              + e.getMessage() + BR;
          LOGGER.error(e.getMessage(), e);
          return;
        }
      }
    }
  }

  private void prefillDatabase() {
    final WahlInfo wahlInfo = WahlInfo.getWahlInfo();

    final String wurzelgebiet = wahlInfo.getID_Wurzelgebiet();
    try {
      for (Gebiet gebiet : ((GebietHome) findLocalHomeNoCache("Gebiet"))//$NON-NLS-1$
          .findAllByUebergeordnetesGebiet(wurzelgebiet)) {
        final AnwContext anwContext = new AnwContext(gebiet.getID_Wahl(), "prefill", "prefill", //$NON-NLS-1$ //$NON-NLS-2$
            wurzelgebiet, null);
        // Ersteingang
        GUIEingangMsg geMsg = getEingangHandling().getGUIMsg(anwContext, gebiet, true);
        geMsg.setInputMode(EingangMsg.MODE_IGNORE_WARNINGS);
        geMsg.setStatus(ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK);
        geMsg.setSource(ErgebniseingangKonstanten.SOURCE_GUI_1);
        getEingangHandling().processInputMsg(geMsg);
        // Zweiteingang
        geMsg.setInputMode(EingangMsg.MODE_IGNORE_WARNINGS);
        geMsg.setStatus(ErgebniseingangKonstanten.STATE_OK);
        geMsg.setSource(ErgebniseingangKonstanten.SOURCE_GUI_2);
        getEingangHandling().processInputMsg(geMsg);
      }
    } catch (EJBException e) {
      // Ignore exception
      e.printStackTrace();
    } catch (FinderException e) {
      // Ignore exception
      e.printStackTrace();
    } catch (ImportException e) {
      // Ignore exception
      e.printStackTrace();
    }
  }

  /**
   * @param request
   * @throws FinderException if ErgebniseingangHome could not be found
   */
  private void setStimmbezirke(HttpServletRequest request) {
    final AnwContext anwContext = getAnwContext(request);
    final WahlInfo wahlInfo = WahlInfo.getWahlInfo();
    Collection<GebietModel> stimmbezirke = getStimmbezirkeToAdmin();
    int anzWahlberechtigteGes = 0;
    Set<Integer> newNumbers = new HashSet<Integer>();
    List<GebietModel> regionOK = new ArrayList<GebietModel>();
    // First step is, to delete the selected areas
    for (GebietModel gebiet : stimmbezirke) {
      final String idGebiet = gebiet.getID_Gebiet();
      String deleteGebiet = request.getParameter(PREFIX + idGebiet + PARAM_DELETE);
      if (deleteGebiet != null) {
        getAdminHandling().deleteGebiet(idGebiet);
      }
    }
    // than, save the changes without the deleted areas
    stimmbezirke = getStimmbezirkeToAdmin();
    for (GebietModel gebiet : stimmbezirke) {
      final String idGebiet = gebiet.getID_Gebiet();

      String name = request.getParameter(PREFIX + idGebiet + PARAM_SUFFIX_NAME);
      gebiet.setName(name);
      String zipcode = request.getParameter(PREFIX + idGebiet + PARAM_SUFFIX_ZIPCODE);
      gebiet.setZipcode(zipcode);
      try {
        int nr = ClientHelper.getIntParameter(request.getParameter(PREFIX + idGebiet
            + PARAM_SUFFIX_NR));
        gebiet.setNummer(nr);
        if (newNumbers.contains(nr)) {
          _adminMsgStimmbez += Messages.bind(MessageKeys.Error_StimmgebietMitNr_0_BereitsVorhanden,
              nr) + BR;
          continue;
        }
        newNumbers.add(nr);
      } catch (NumberFormatException e1) {
        _adminMsgStimmbez += Messages.bind(MessageKeys.Error_0_Nr_WERT, name) + BR;
      }
      try {
        int wahlberechtigte = ClientHelper.getIntParameter(request.getParameter(PREFIX + idGebiet
            + PARAM_SUFFIX_WAHLBERECHTIGTE));
        if (wahlberechtigte != gebiet.getWahlberechtigte()) {
          // number of eligible voters changed
          GebietHome gebietHome = (GebietHome) findLocalHomeNoCache("Gebiet"); //$NON-NLS-1$
          try {
            Gebiet gebietEjb = gebietHome.findByWahlAndGebietsartAndNummer(wahlInfo.getID_Wahl(),
                gebiet.getGebietsart(),
                gebiet.getNummer());
            if (!gebietEjb.getStimmergebnisCol().isEmpty()) {
              // only on regions with input
              GUIEingangMsg eingangMsg = getEingangHandling().getGUIMsg(anwContext,
                  gebietEjb,
                  false,
                  true);
              eingangMsg.setGruppenstimmen(GruppeAllgemein.WAHLBERECHTIGTE.getPosition(),
                  wahlberechtigte);
              try {
                getEingangHandling().processInputMsg(eingangMsg);
              } catch (EJBException e) {
                e.printStackTrace();
              } catch (ImportException e) {
                e.printStackTrace();
              }
            }
          } catch (FinderException e) {
            e.printStackTrace();
          }
        }
        gebiet.setWahlberechtigte(wahlberechtigte);
        anzWahlberechtigteGes += wahlberechtigte;
        if (wahlberechtigte == 0) {
          _adminMsgStimmbezWarning += Messages
              .bind(MessageKeys.Warning_0_WAHLBERECHTIGTE_WERT_GLEICH_NULL, name)
              + BR;
        }
      } catch (NumberFormatException e1) {
        _adminMsgStimmbez += Messages.bind(MessageKeys.Error_0_WAHLBERECHTIGTE_WERT, name) + BR;
        continue;
      }
      String postalvote = request.getParameter(PREFIX + idGebiet + PARAM_SUFFIX_POSTALVOTE);
      if (postalvote != null) {
        gebiet.setPostalvote(true);
      } else {
        gebiet.setPostalvote(false);
      }
      regionOK.add(gebiet);
    }
    getAdminHandling().updatePollingStations(regionOK);
    // setting max votes for superior region
    // NAME suchen
    getAdminHandling().setGebietNameUndWahlberechtigte(wahlInfo.getID_Wurzelgebiet(),
        null,
        anzWahlberechtigteGes);
  }

  private void deleteStimmbezirk(HttpServletRequest request) {
    String idGebiet = request.getParameter(PARAM_ID);
    getAdminHandling().deleteGebiet(idGebiet);
  }

  private void exportP5(HttpServletRequest request, ExportP5Type config) {
    _adminMsgExport = EMPTY_STRING;
    _adminMsgExportConfirmation = null;
    _adminWarningOverride = EMPTY_STRING;

    String gotoModus = request.getParameter(PARAM_ACCEPT);
    DialogStateHolder dialogStateHolder = getState(config);
    if (ZURUECK.equals(gotoModus) || RESET.equals(gotoModus) || NEU.equals(gotoModus)) {
      if (config.getFinalState() == dialogStateHolder.getModus() || RESET.equals(gotoModus)
          || NEU.equals(gotoModus)) {
        dialogStateHolder.reset();
      } else {
        dialogStateHolder.back();
      }
      // Navigation, no export, yet
      return;
    }

    File filedef = getPropertyHandling().getFileProperty(PROP_EXPORT_FORMULAR_DIR);
    if (filedef == null) {
      // No destination directory for the export selected
      _adminMsgExport = Messages.bind(config.getErrorMsgKey());
      LOGGER.error(_adminMsgExport);
      return;
    }

    if (ReportOutputFormatEnum.CSV.equals(config.getReportOutputFormat())) {
      exportCSV(request, config, filedef);
    } else {
      exportP5(request, config, filedef);
    }
  }

  private void exportP5(HttpServletRequest request, ExportP5Type config, File filedef) {
    HttpSession session = request.getSession(false);
    int defaultSessionTimeout = ClientHelper.changeSessionTimeout(request, 60);
    boolean forceOverwrite = ClientHelper.getBooleanParameter(request.getParameter(PARAM_FORCE),
        false);
    Document eml520 = null;
    try {
      if (forceOverwrite) {
        eml520 = getStoredEMLResult(session);
      }
      removeStoredEMLResult(session);

      boolean forLetterOfAppointment = ExportP5Type.KAN_BEN.equals(config);
      boolean forProtocolAppendix = ExportP5Type.P22_1_APPENDIX.equals(config)
          || ExportP5Type.P22_2_APPENDIX.equals(config);
      WahlInfo wahlInfo = WahlInfo.getWahlInfo();
      if (eml520 == null) {
        String id_Ergebniseingang = wahlInfo.getWahl().getWurzelgebiet().getID_LetzterEingang();
        eml520 = getExportHandling().createEML520(id_Ergebniseingang,
            forLetterOfAppointment,
            forProtocolAppendix);
      }

      ExportEml exportEML = ExportEml.OVERWRITE;
      if (forLetterOfAppointment || forProtocolAppendix || !wahlInfo.isFreigegeben()) {
        exportEML = ExportEml.NO;
      }

      ReportOutputFormatEnum outputFormat = config.getReportOutputFormat();
      if (outputFormat == null) {
        outputFormat = getRtfPdf(request);
      }

      ReportConfigurer rc = new ReportConfigurer();
      rc.setDocument(config.getTemplate(), outputFormat);
      rc.setProgramNameAndVersion(EJBUtil.getProgramSpecificAffix(),
          ApplicationBean.getVersionString());
      rc.configureFileName(filedef,
          ReportNameComponentsP5.fromEML520(eml520),
          !wahlInfo.isFreigegeben());
      rc.configureWhichFiles(exportEML, forceOverwrite);
      ReportConfiguration reportConfiguration = rc.getReportConfiguration();

      ReportGenerator generator = new ReportGeneratorImpl(APP_LOGGER);
      generator.createReport(reportConfiguration, eml520);

      ReportTemplateEnum[] reportTemplateExts = config.getTemplates();
      for (ReportTemplateEnum reportTemplateExt : reportTemplateExts) {
        if (ReportTemplateEnum.OSV5_6.equals(reportTemplateExt)) {
          generateCsvExport(filedef, forceOverwrite, reportTemplateExt);
        } else {
          // set different template
          rc.setDocument(reportTemplateExt, outputFormat);
          // do not export the EML file a second time
          rc.configureWhichFiles(ExportEml.NO, forceOverwrite);
          ReportConfiguration confCopy = rc.getReportConfiguration();
          generator.createReport(confCopy, eml520);
        }
      }

      getExportHandling().updateBackupArchive();

      if (!rc.getReportConfiguration().isDraft()
          && (ExportP5Type.P22_1.equals(config) || ExportP5Type.P22_2.equals(config) || ExportP5Type.U16
              .equals(config))) {
        getExportHandling().updateElectionResultArchive(); // OSV-1385
      }

      DialogStateHolder dialogStateHolder = getState(config);
      dialogStateHolder.setModus(setNewMode(request, dialogStateHolder.getModus()));
      _adminMsgExportConfirmation = Messages
          .bind(MessageKeys.Msg_ReportdateienErfolgreichExportiert_0, filedef.getAbsolutePath());
    } catch (GeneratedFileExistsException e) {
      _adminWarningOverride = e.getMessage();
      if (!forceOverwrite) {
        storeEMLResult(session, eml520);
      }
    } catch (Exception e) {
      _adminMsgExport = Messages.bind(config.getErrorMsgKey()) + BR + e.getMessage();
      LOGGER.error(_adminMsgExport, e);
    } finally {
      ClientHelper.setSessionTimeoutBackToDefaultPlus(request, defaultSessionTimeout);
    }
  }

  private void exportCSV(HttpServletRequest request, ExportP5Type config, File filedef) {
    int defaultSessionTimeout = ClientHelper.changeSessionTimeout(request, 60);
    boolean forceOverwrite = ClientHelper.getBooleanParameter(request.getParameter(PARAM_FORCE),
        false);
    try {
      generateCsvExport(filedef, forceOverwrite, config.getTemplate());

      DialogStateHolder dialogStateHolder = getState(config);
      dialogStateHolder.setModus(setNewMode(request, dialogStateHolder.getModus()));
      _adminMsgExportConfirmation = Messages
          .bind(MessageKeys.Msg_ReportdateienErfolgreichExportiert_0, filedef.getAbsolutePath());
      getExportHandling().updateBackupArchive();
    } catch (GeneratedFileExistsException e) {
      _adminWarningOverride = e.getMessage();
    } catch (Exception e) {
      _adminMsgExport = Messages.bind(config.getErrorMsgKey()) + BR + e.getMessage();
      LOGGER.error(_adminMsgExport, e);
    } finally {
      ClientHelper.setSessionTimeoutBackToDefaultPlus(request, defaultSessionTimeout);
    }
  }

  private void generateCsvExport(File filedef, boolean forceOverwrite, ReportTemplateEnum template)
      throws IOException, ImportException {
    ReportConfiguration reportConfiguration = getReportConfigurationCSV(filedef,
        forceOverwrite,
        template);
    ReportGenerator generator = new ReportGeneratorImpl(APP_LOGGER);
    final String csv;
    if (ReportTemplateEnum.OSV5_5.equals(template)) {
      csv = getExportHandling().createCandidateAddressesCsvExport();
    } else if (ReportTemplateEnum.OSV5_6.equals(template)) {
      csv = getExportHandling().createCandidateDetailsCsvExport();
    } else {
      throw new IllegalArgumentException();
    }
    generator.createCsvExportFile(reportConfiguration, csv);
  }

  private ReportConfiguration getReportConfigurationCSV(File filedef,
      boolean forceOverwrite,
      ReportTemplateEnum reportTemplate) {
    ExportEml exportEML = ExportEml.NO;
    ReportNameComponentsP5CSV reportNameComponents = getReportNameComponentsP5CSV();
    ReportOutputFormatEnum outputFormat = ReportOutputFormatEnum.CSV;

    ReportConfigurer rc = new ReportConfigurer();
    rc.setDocument(reportTemplate, outputFormat);
    rc.configureFileName(filedef, reportNameComponents, !WahlInfo.getWahlInfo().isFreigegeben());
    rc.configureWhichFiles(exportEML, forceOverwrite);
    return rc.getReportConfiguration();
  }

  private ReportNameComponentsP5CSV getReportNameComponentsP5CSV() {
    WahlInfo wahlInfo = WahlInfo.getWahlInfo();
    String electionIdentifier = wahlInfo.getID_Wahl();
    ElectionCategory electionCategory = wahlInfo.getElectionCategory();
    String electionDomain = wahlInfo.getWahl().getElectionDomain();

    return new ReportNameComponentsP5CSV(electionIdentifier, electionCategory, electionDomain);
  }

  private void storeEMLResult(final HttpSession session, Document eml520) {
    session.setAttribute(ATTR_EXPORT_RESULT_520, new SerializableSoftDocumentReference(eml520));
  }

  private Document getStoredEMLResult(final HttpSession session) {
    final SerializableSoftDocumentReference eml520Reference = (SerializableSoftDocumentReference) session
        .getAttribute(ATTR_EXPORT_RESULT_520);
    return eml520Reference == null ? null : eml520Reference.get();
  }

  private void removeStoredEMLResult(final HttpSession session) {
    session.removeAttribute(ATTR_EXPORT_RESULT_520);
  }

  private void handleKandidatenWaehlbar(HttpServletRequest request) {
    Collection<Personendaten> personendatenListe = getAuswertungHandling()
        .getPersonenAlphabetisch(getAnwContext(request));
    for (Personendaten personendaten : personendatenListe) {
      String idPersonendaten = personendaten.getID_Personendaten();
      String parameter = request.getParameter(PREFIX + idPersonendaten);
      boolean waehlbar = true;
      if (parameter != null && PARAM_ON.equals(parameter)) {
        waehlbar = false;
      }
      if (personendaten.isBenennbar() != waehlbar) {
        getAdminHandling().setPersonendatenBenennbar(idPersonendaten, waehlbar);
      }
    }
  }

  private void handleVoteValues(HttpServletRequest request) {
    Collection<Gebiet> provinces = getGebietHandling().getRegionsForVoteValues();
    for (Gebiet region : provinces) {
      String idRegion = region.getID_Gebiet();
      String parameter = request.getParameter(PREFIX + idRegion);
      int voteValue = 1;
      if (parameter != null) {
        voteValue = Integer.parseInt(parameter);
      }
      if (region.getVoteValue() != voteValue) {
        getAdminHandling().setVoteValue(idRegion, voteValue);
      }
    }
    _confirmationMsg = Messages.bind(MessageKeys.Msg_VoteValuesSaved);
  }

  /**
   * @return {@link SchwellwertHandling}
   */
  public SchwellwertHandling getSchwellwertHandling() {
    if (_schwellwertHandling == null) {
      _schwellwertHandling = lookupLocal(SchwellwertHandlingBean.class.getSimpleName());
    }
    return _schwellwertHandling;
  }

  public boolean existWarnungenOderFehler(HttpServletRequest request) throws EJBException {
    return getAdminHandling().existWarnungenOderFehler(getAnwContext(request));
  }

  public int getSchwellwert(String bezeichnung, HttpServletRequest request) throws EJBException {
    return getSchwellwertHandling().getSchwellwert(getAnwContext(request), bezeichnung);
  }

  /**
   * Verarbeitet das abgesendete Formular der JSP für allgemeine Schwellwerte Dabei wird auf das in
   * Kostanten definieret Array von Bezeichnern zurückgegriffen
   * 
   * @param request der HttPRequest mit den submitteten Werten
   */
  private void handleSWEingabeAllg(HttpServletRequest request) {
    AnwContext anwContext = getAnwContext(request);
    String nachricht = Messages.getString(MessageKeys.MSg_Schwellwertfestlegung) + BR;

    for (String[] element : SWERT_ALLG_KEYS) {
      try {
        String schwellwertKey = element[0];
        String wert = request.getParameter(PREFIX + schwellwertKey);
        int val = parseInt(wert);
        getSchwellwertHandling().setSchwellwert(anwContext, schwellwertKey, val);
        nachricht += element[1] + ":" + val + BR; //$NON-NLS-1$
      } catch (Exception e) {
        LOGGER.error(e, e);
      }
    }
  }

  /**
   * Wahl komplett zr�cksetzen
   */
  private void wahlZuruecksetzen(HttpServletRequest request) {
    try {
      // sollte noch eine ErfassungseinheitEingangsNachricht im Step stehen, wird sie gel�scht.
      removeGUI_ErfassungseinheitEingangsNachrichtFromStep(request);
    } catch (Exception e) {
      LOGGER.error(e, e);
    }
  }

  /**
   * liefert das Anwenderbean mit der angegebenen ID
   * 
   * @param id_anwender ID des Anwenders
   * @return Anwender Bean
   */
  public Anwender getAnwenderBean(String id_anwender) {
    try {
      return getAnwenderHome().findByPrimaryKey(id_anwender);
    } catch (Exception e) {
      LOGGER.error(e, e);
      return null;
    }
  }

  /**
   * @return alle Anwender
   */
  public Collection<Anwender> getAllAnwenderBeans() {
    try {
      return getAnwenderHome().findAll();
    } catch (Exception e) {
      LOGGER.error(e, e);
      return null;
    }
  }

  /**
   * liefert alle Anwender, die vom �bergebenen Anwender bearbeitet werden d�rfen
   * 
   * @param anwContext Anwenderkontext
   * @return alle Anwender, die vom �bergebenen Anwender bearbeitet werden d�rfen
   */
  public Collection<Anwender> getAllAnwenderBeans(AnwContext anwContext) {
    try {
      AnwenderHome anwenderHome = getAnwenderHome();
      // pr�fen, ob der Anwender auf allen Gebieten (und damit allen Wahlen) zugelassen ist
      // --> ID_Gebiet des Anwenders == null
      boolean anwWithNull = anwenderHome.findByPrimaryKey(anwContext.getID_Anwender())
          .getID_Gebiet() == null;
      return anwenderHome.findAllVonGebietUndKindergebieten(anwContext.getID_Gebiet(), anwWithNull);
    } catch (Exception e) {
      LOGGER.error(e, e);
      return null;
    }
  }

  private AnwenderHome getAnwenderHome() throws EJBException {
    return findLocalHomeNoCache(EJB_ANWENDER);
  }

  /**
   * Speichert eine Anwender. existriert kein id_anwender im Request wir ein neuer Anwender erzeugt.
   * Andernfalls wird versucht den Anwender mit der angegebenen ID zu �ndern
   * 
   * @param request der http request
   */
  private void saveAnwender(HttpServletRequest request) {
    try {
      AnwContext anwContext = getAnwContext(request);
      // Auslesen des Requests
      // lesen der Anwender ID aus der Request
      String id_Anwender = request.getParameter(PARAM_ID_USER);
      String name_anwender = request.getParameter(PARAM_ANW_USERNAME);
      String name = request.getParameter(PARAM_ANW_NAME);
      String gebietID = request.getParameter(PARAM_ANW_REGION);
      String passwort = request.getParameter(PARAM_ANW_PASSWORD1);
      String fehlversuche = request.getParameter(PARAM_ANW_FAULTS);

      _adminMsg = null;
      _confirmationMsg = null;
      try {
        AnwenderHandling anwHandling = lookupLocal(AnwenderHandlingBean.class.getSimpleName());

        Collection<String> zukuenftigeAnwRechte = new ArrayList<String>();
        for (RechtegruppeModel rechtegruppeModel : anwHandling.getAllRechtegruppen()) {
          String id_Rechtegruppe = rechtegruppeModel.getID_Rechtegruppe();
          if ("true".equals(request.getParameter(PREFIX + id_Rechtegruppe))) { //$NON-NLS-1$
            zukuenftigeAnwRechte.add(id_Rechtegruppe);
          }
        }

        boolean modify = false;
        boolean create = false;
        String confirmationMsg = null;
        // Wenn im Request keine ID eines Anwenders existiert,
        // neuen Anwender anlegen
        if (id_Anwender == null || id_Anwender.length() == 0) {
          create = true;
          confirmationMsg = Messages.bind(MessageKeys.Msg_Anwender_0_1_ErfolgreichAngelegt,
              name_anwender,
              name);
          getAdminHandling().writeAppLog(anwContext, _confirmationMsg);
        } else {
          // eine AnwenderID --> modifiziere diesen Anwender
          modify = true;
          confirmationMsg = Messages
              .bind(MessageKeys.Msg_DatenDesAnwenders_0_1_WurdenErfolgreichGeaendert,
                  name_anwender,
                  name);//
          getAdminHandling().writeAppLog(anwContext, _confirmationMsg);
        }

        // setze Land
        if (ID_NO_REGION.equals(gebietID)) {
          gebietID = null;
        }
        if (passwort != null && passwort.trim().length() > 0) {
          passwort = passwort.trim();
        }
        // Anzahl fehlversuche
        int fehlversucheInt = 0;
        if (fehlversuche != null) {
          fehlversucheInt = new Integer(fehlversuche).intValue();
        }

        try {
          getAnwenderHandling().createOrModifyAnwender(anwContext,
              name_anwender,
              name,
              passwort,
              zukuenftigeAnwRechte,
              gebietID,
              fehlversucheInt,
              create,
              modify);
          _confirmationMsg = confirmationMsg;
        } catch (AnwenderException ae) {
          _adminMsg = ae.getMessage();
        }
      } catch (Exception e) {
        _adminMsg = Messages.getString(MessageKeys.Msg_FehlerAufgetreten_SieheLogDatei);
        LOGGER.error(e, e);
      }
    } catch (Exception e) {
      _adminMsg = Messages.getString(MessageKeys.Msg_FehlerAufgetreten_SieheLogDatei);
      LOGGER.error(e, e);
    }
  }

  /**
   * Freigeben und R�cknahme der Ferigabe aktuelle Wahl
   * 
   * @param request mit Eingaben des Anwenders
   */
  private void handleFreigabe(HttpServletRequest request) {
    String freigabeString = request.getParameter(PARAM_RELEASED);
    boolean freigabe = Boolean.parseBoolean(freigabeString);
    AnwContext anwContext = getAnwContext(request);
    try {
      final StateHandling stateHandling = getStateHandling();
      stateHandling.setFreigabe(anwContext, freigabe);
      ExportP4Bean exportP4Bean = (ExportP4Bean) request.getSession().getAttribute("expP4Bean"); //$NON-NLS-1$
      if (exportP4Bean != null) {
        exportP4Bean.initP4ExportState();
      }
    } catch (Exception e) {
      LOGGER.error(e, e);
    }
  }

  private void reIndexStimmergebnis(HttpServletRequest request) {
    _adminMsg = null;
    _confirmationMsg = null;
    boolean success = getAdminHandling().reIndexStimmergebnis();
    if (success) {
      _confirmationMsg = Messages.getString(MessageKeys.Msg_ReIndexDatabaseSuccessful);
    } else {
      _adminMsg = Messages.getString(MessageKeys.Msg_ReIndexDatabaseFailed);
    }
  }

  /**
   * l�scht einen Anwender der Applikation
   * 
   * @param id_anwender die ID des zu l�schenden Anwenders
   */
  private void deleteAnwender(String id_anwender, AnwContext anwContext) {
    _adminMsg = null;
    _confirmationMsg = null;
    if (id_anwender == null || id_anwender.length() == 0) {
      _adminMsg = Messages.getString(MessageKeys.Msg_BitteWaehlenSieAnwenderAus);
      return;
    }
    if (id_anwender.equals(anwContext.getID_Anwender())) {
      _adminMsg = Messages
          .getString(MessageKeys.Msg_DerAngemeldeteAnwenderKannNichtGeloeschtWerden);
      return;
    }
    try {
      getAnwenderHandling().delAnwender(id_anwender);
      _confirmationMsg = Messages.getString(MessageKeys.Msg_AnwednerErfolgreichGeloescht);
    } catch (EJBException e) {
      _adminMsg = Messages.getString(MessageKeys.Msg_FehlerBeimLoeschen) + e;
    } catch (FinderException e) {
      _adminMsg = Messages.getString(MessageKeys.Msg_FehlerBeimLoeschenAnwenderNichtGefunden);
    } catch (RemoveException e) {
      _adminMsg = Messages.getString(MessageKeys.Msg_FehlerBeimLoeschenAnwenderNichtLoeschbar);
    }
    return;
  }

  /**
   * Ändert das Passwort eines Benutzers.
   * 
   * @param request der Request zum ändern des Passwortes
   */
  private void changePassword(HttpServletRequest request) {
    _adminMsg = null;
    _confirmationMsg = null;
    boolean hasDefaultUserUnchangedPasswort = getAnwenderHandling()
        .hasDefaultUserUnchangedPasswort();

    String oldPW;
    if (hasDefaultUserUnchangedPasswort) {
      oldPW = Konstanten.DEFAULT_USER_PASSWORD;
    } else {
      oldPW = request.getParameter(PARAM_ANW_OLD_PW);
    }
    String newPW = request.getParameter(PARAM_ANW_NEW_PW1);
    if (oldPW == null || newPW == null || oldPW.length() == 0 || newPW.length() == 0) {
      // da ist dann wohl was schief gelaufen
      _adminMsg = Messages.getString(MessageKeys.Msg_FehlerInAnwendung);
      return;
    }

    AnwContext anwContext = getAnwContext(request);
    if (anwContext == null || anwContext.getAnmeldename() == null) {
      if (hasDefaultUserUnchangedPasswort) {
        // Der Default-Benutzer wurde automatisch angemeldet. Wenn das mehrmals passiert, schlägt
        // die Anmeldung fehl, ohne dass man das merkt. Deshalb soll diese Meldung angezeigt werden.
        _adminMsg = Messages.getString(MessageKeys.Msg_PasswortAendernNichtMoeglich2MinWarten);
        return;
      }
    }

    try {
      getAnwenderHandling().changePassword(anwContext, oldPW, newPW);
      _confirmationMsg = Messages.getString(MessageKeys.Msg_PasswortErfolgreichGeaendert);
      anwContext.setChangePasswordForced(false);
    } catch (AnwenderException e) {
      _adminMsg = e.getMessage();
      LOGGER.error(e, e);
    } catch (Exception e) {
      _adminMsg = Messages.getString(MessageKeys.Msg_FehlerAufgetreten_SieheLogDatei);
      LOGGER.error(e, e);
    }
  }

  /**
   * liefert eine Collection von Listenkandidatur-Objekten der LIste mit �bergebener ID
   * 
   * @param id_Liste ID der gesuchten Liste
   * @return Collection von Listenkandidatur-Objekten
   */
  public Collection<Listenkandidatur> getGebietsliste(String id_Liste) {
    try {
      ListeHome landeslisteHome = findLocalHomeNoCache(EJB_LISTE);
      return landeslisteHome.findByPrimaryKey(id_Liste).getListenkandidaturCol();
    } catch (Exception e) {
      LOGGER.error(e, e);
      return null;
    }
  }

  /**
   * Gibt die Liste der Partei(Beans) zur�ck
   * 
   * @param request HTTP-Request
   * @return Liste der Partei(Beans)
   */
  public Collection<Gruppe> getAllParteien(HttpServletRequest request) {
    try {
      GruppeHome parteiHome = findLocalHomeNoCache(EJB_GRUPPE);
      return parteiHome.findAllByWahlAndGruppenart(getAnwContext(request).getID_Wahl(),
          GRUPPENART_PARTEI);
    } catch (Exception e) {
      LOGGER.error(e, e);
      return emptyList();
    }
  }

  public Collection<GebietModel> getBundeslaender(AnwContext anwContext, int gebietsart)
      throws EJBException {

    return getAdminHandling().getGebiete(anwContext, gebietsart);
  }

  /**
   * Gibt die Liste der Wahleinheit(Bean) zur�ck
   * 
   * @param anwContext Anwenderkontext
   * @return Liste der Wahleinheit(Bean)
   */
  public Collection<GebietModel> getWahleinheiten(AnwContext anwContext) {
    return getAdminHandling().getWahleinheiten(anwContext);
  }

  // Handling of DialogStateHolders

  private DialogStateHolder getState(ExportP5Type report) {
    return _stateMap.get(report);
  }

  private void reset(ExportP5Type report) {
    getState(report).reset();
  }

  public DialogStateHolder getP5ExportStateP22_1() {
    return getState(ExportP5Type.P22_1);
  }

  public DialogStateHolder getP5ExportStateP22_2() {
    return getState(ExportP5Type.P22_2);
  }

  public DialogStateHolder getP5ExportStateU16() {
    return getState(ExportP5Type.U16);
  }

  public DialogStateHolder getP5ExportStateP22_1Appendix() {
    return getState(ExportP5Type.P22_1_APPENDIX);
  }

  public DialogStateHolder getP5ExportStateP22_2Appendix() {
    return getState(ExportP5Type.P22_2_APPENDIX);
  }

  public DialogStateHolder getP5ExportStateCandidateAddress() {
    return getState(ExportP5Type.CANDIDATE_ADDRESS);
  }

  public DialogStateHolder getP5ExportStateKanBen() {
    return getState(ExportP5Type.KAN_BEN);
  }

  public void resetExportStateP22_1() {
    reset(ExportP5Type.P22_1);
  }

  public void resetExportStateP22_2() {
    reset(ExportP5Type.P22_2);
  }

  public void resetExportStateU16() {
    reset(ExportP5Type.U16);
  }

  public void resetExportStateP22_2Appendix() {
    reset(ExportP5Type.P22_2_APPENDIX);
  }

  public void resetExportStateKanBen() {
    reset(ExportP5Type.KAN_BEN);
  }

  public Collection<GebietModel> getStimmbezirkeToAdmin() {
    return getAdminHandling().getGebiete(GEBIETSART_STIMMBEZIRK);
  }

  public List<GebietModel> getStimmbezirkeSortedByNummer() {
    List<GebietModel> stimmbezirke = new ArrayList<GebietModel>(getStimmbezirkeToAdmin());
    Collections.sort(stimmbezirke, new Comparator<GebietModel>() {
      @Override
      public int compare(GebietModel g1, GebietModel g2) {
        return Integer.signum(g1.getNummer() - g2.getNummer());
      }
    });
    return stimmbezirke;
  }

  private static class SerializableSoftDocumentReference implements Serializable {
    private static final long serialVersionUID = -8955549683272038865L;
    private final transient SoftReference<Document> _softReference;

    public SerializableSoftDocumentReference(Document referent) {
      _softReference = new SoftReference<Document>(referent);
    }

    /**
     * @return stored {@link Document} if available
     * @see java.lang.ref.SoftReference#get()
     */
    public Document get() {
      // _softReference can become <code>null</code> on deserialization because it is transient
      return _softReference == null ? null : _softReference.get();
    }
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.client.beans.RepositoryPropertyHandler#getProperty(java.lang.String)
   */
  @Override
  public String getProperty(String name) throws EJBException {
    String propertyValue = super.getProperty(name);
    return replaceWithDefaultValueIfNull(name, propertyValue);
  }

  /**
   * Replaces the propertyValue with the default value, if propertyValue is null.
   * 
   * @param propertyName property name
   * @param propertyValue property value
   * @return value to use
   */
  private String replaceWithDefaultValueIfNull(String propertyName, String propertyValue) {
    if (propertyValue != null) {
      return propertyValue;
    }
    if (XMLTags.RG_PLACE_LETTER.equals(propertyName)
        || XMLTags.RG_REJECTION_LOCATION.equals(propertyName)
        || XMLTags.RG_ACCEPTANCE_LOCATION.equals(propertyName)) {
      return getCityOfWurzelgebiet();
    }
    if (Konstanten.PROP_BACKGROUND_COLOR_RED.equals(propertyName)
        || Konstanten.PROP_BACKGROUND_COLOR_GREEN.equals(propertyName)
        || Konstanten.PROP_BACKGROUND_COLOR_BLUE.equals(propertyName)) {
      return String.valueOf(Konstanten.DEFAULT_BACKGROUND_COLOR_GREY);
    }
    return propertyValue;
  }

  /**
   * @return name of the city of the wurzelgebiet
   */
  private String getCityOfWurzelgebiet() {
    Wahl wahl = WahlInfo.getWahlInfo().getWahl();
    String wahlkategorie = wahl.getWahlkategorie();
    if ("TK".equals(wahlkategorie) || "EK".equals(wahlkategorie) || "EP".equals(wahlkategorie)) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      return Messages.getString(MessageKeys.LocationOfDeKiesraad);
    }
    return getProperty(Konstanten.KEY_CSB_REGION_NAME);
  }
}
