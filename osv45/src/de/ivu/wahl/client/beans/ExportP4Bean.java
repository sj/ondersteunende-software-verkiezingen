/*
 * ExportP4Bean
 * 
 * Copyright (c) 2002-9 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import static de.ivu.wahl.client.beans.ApplicationBeanKonstanten.PREFIX;
import static de.ivu.wahl.client.beans.ExportP4Type.EML510C;
import static de.ivu.wahl.client.beans.ExportP4Type.EMPTY_EML;
import static de.ivu.wahl.client.beans.ExportP4Type.N10_1;
import static de.ivu.wahl.client.beans.ExportP4Type.N11;
import static de.ivu.wahl.client.beans.ExportP4Type.O3;
import static de.ivu.wahl.client.beans.ExportP4Type.OSV4_1;
import static de.ivu.wahl.client.beans.ExportP4Type.OSV4_4;
import static de.ivu.wahl.client.beans.ExportP4Type.OSV4_5;
import static de.ivu.wahl.client.beans.ExportP4Type.OSV4_6;
import static de.ivu.wahl.client.beans.ExportP4Type.REF;
import static de.ivu.wahl.client.beans.ExportP4Type.T11;
import static de.ivu.wahl.client.beans.ExportP4Type.VOTES_CSV;
import static de.ivu.wahl.client.beans.ExportP4Type.WRR83;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;

import nu.xom.Document;

import de.ivu.ejb.EJBUtil;
import de.ivu.util.debug.UserActionLogger;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.SystemInfo;
import de.ivu.wahl.SystemProperty;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.admin.DialogStateHolder;
import de.ivu.wahl.admin.ExportWithParametersState;
import de.ivu.wahl.auswertung.erg.ResultSummary;
import de.ivu.wahl.client.util.ClientHelper;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;
import de.ivu.wahl.wus.reportgen.GeneratedFileExistsException;
import de.ivu.wahl.wus.reportgen.ReportConfiguration;
import de.ivu.wahl.wus.reportgen.ReportConfiguration.ExportEml;
import de.ivu.wahl.wus.reportgen.ReportConfigurationCsvExport;
import de.ivu.wahl.wus.reportgen.ReportConfigurer;
import de.ivu.wahl.wus.reportgen.ReportGenerator;
import de.ivu.wahl.wus.reportgen.ReportGeneratorImpl;
import de.ivu.wahl.wus.reportgen.ReportNameComponents;
import de.ivu.wahl.wus.reportgen.ReportNameComponentsP4;
import de.ivu.wahl.wus.reportgen.ReportOutputFormatEnum;
import de.ivu.wahl.wus.reportgen.ReportTemplateEnum;

/**
 * Funktionen f�r Administrative Zwecke auf der Client-Seite. Dies sind fast ausschlie�lich
 * Durchgriffe auf Sessionbeans.
 * 
 * @author mur@ivu.de, klie@ivu.de
 */

public class ExportP4Bean extends RepositoryPropertyHandler implements Executer, Serializable {
  private static final long serialVersionUID = -1363115913111148993L;
  private static final String EMPTY_STRING = new String();

  /** Logger for instances of this class */
  protected static final Logger LOGGER = Logger.getLogger(ExportP4Bean.class);
  protected static final UserActionLogger APP_LOGGER = new UserActionLogger();

  private final Map<ExportP4Type, DialogStateHolder> _stateMap = new HashMap<ExportP4Type, DialogStateHolder>() {
    private static final long serialVersionUID = 1L;
    {
      for (ExportP4Type type : ExportP4Type.values()) {
        if (type.isWithParameters()) {
          put(type, new DialogStateHolder());
        } else {
          put(type, new ExportWithParametersState());
        }
      }
    }
  };

  /**
   * anwenderMsg wird von der Webseite benutzt um eine Statusmeldung f�r den Anwender anzuzeigen
   */
  public String _adminMsg;

  /**
   * Execute a command. All valid commands are enumerated in ExportP4Commands.
   * 
   * @param request HTTP-Request
   * @param n index des Befehls
   */
  @Override
  public void executeCommand(HttpServletRequest request, int n) {
    String cmd = request.getParameter("cmd" + (n == 0 ? EMPTY_STRING : String.valueOf(n))); //$NON-NLS-1$

    for (ExportP4Type config : ExportP4Type.values()) {
      if (cmd.equals(config.getExportCommand())) {
        exportP4(request, config);
        return;
      }
      if (cmd.equals(config.getPropertiesCommand())) {
        // Eingabe von Properties
        DialogStateHolder state = getState(config);
        state._modus = handlePropEingabeAllg(request, config.getPropertiesMap(), state._modus);
        return;
      }
    }
    throw new RuntimeException(Messages.bind(MessageKeys.Error_Command_0_Unknown, cmd));
  }

  /**
   * @param request
   */
  private void exportP4(HttpServletRequest request, ExportP4Type config) {
    _adminMsgExport = EMPTY_STRING;
    _adminMsgExportConfirmation = null;
    _adminWarningOverride = EMPTY_STRING;

    String gotoModus = request.getParameter(PREFIX + "uebernehmen"); //$NON-NLS-1$

    if (RESET.equals(gotoModus) || NEU.equals(gotoModus)) {
      getState(config).reset();
    } else if (ZURUECK.equals(gotoModus)) {
      getState(config).back();
    } else {
      doExportP4(request, config);
    }
  }

  private void doExportP4(HttpServletRequest request, ExportP4Type config) {
    String errorMsg = EMPTY_STRING;

    File filedef = getPropertyHandling().getFileProperty(Konstanten.PROP_EXPORT_FORMULAR_DIR);
    if (filedef == null) {
      // No export folder selected
      _adminMsgExport = Messages.bind(errorMsg);
      LOGGER.error(_adminMsgExport);
      return;
    }

    int defaultSessionTimeout = ClientHelper.changeSessionTimeout(request, 60);
    try {
      // Prepare an error message just in case an Exception occurs
      errorMsg = config.getErrorMsgKey();

      errorMsg = createExportP4File(request, config, filedef);
    } catch (Exception e) {
      _adminMsgExport = (errorMsg.isEmpty() ? EMPTY_STRING : Messages.bind(errorMsg)
          + Konstanten.BR)
          + e.getMessage();
      LOGGER.error(_adminMsgExport, e);
    } finally {
      ClientHelper.setSessionTimeoutBackToDefaultPlus(request, defaultSessionTimeout);
    }
  }

  private String createExportP4File(HttpServletRequest request, ExportP4Type config, File filedef)
      throws ImportException, IOException, ParserConfigurationException, Exception {
    // Prepare an error message just in case an Exception occurs
    String result = config.getErrorMsgKey();

    HttpSession session = request.getSession(false);
    boolean forceOverwrite = ClientHelper.getBooleanParameter(request.getParameter(PARAM_FORCE),
        false);

    long start = new Date().getTime();
    Document eml510 = createEML510(config, forceOverwrite, session);
    LOGGER.info("Created eml510 in ms: " + (new Date().getTime() - start)); //$NON-NLS-1$

    ReportGenerator generator = new ReportGeneratorImpl(APP_LOGGER);
    ReportNameComponents nameComponents = ReportNameComponentsP4.fromEML510(eml510, SystemInfo
        .getSystemInfo().isInputmodusComplete());

    boolean draft = !WahlInfo.getWahlInfo().isFreigegeben()
        && !Arrays.asList(N10_1, EMPTY_EML, T11).contains(config);
    final ReportOutputFormatEnum outputFormat = determineOutputFormat(request, config);
    boolean isCSB = (SystemInfo.getSystemInfo().getWahlEbene() == GebietModel.EBENE_CSB);
    ExportEml exportEml = draft || Arrays.asList(N10_1, OSV4_4, T11, OSV4_6).contains(config)
        ? ExportEml.NO
        : ExportEml.OVERWRITE;

    try {
      ReportConfiguration rc;
      ReportTemplateEnum template = config.getTemplate();
      switch (config) {
        case N11 :
          // For model N11, replace the template for openbaar lichaams (BES islands)
          if (nameComponents.isOpenbaarLichaam()) {
            template = ReportTemplateEnum.N11_OL;
          }
          //$FALL-THROUGH$
        case N10_1 :
        case EML510C :
        case O3 :
        case REF :
        case OSV4_1 :
        case OSV4_4 :
        case OSV4_5 :
        case EMPTY_EML :
        case T11 :
        case WRR83 :
        case OSV4_6 :
          ReportConfigurer configurer = new ReportConfigurer();
          configurer.setDocument(template, outputFormat);
          configurer.setProgramNameAndVersion(EJBUtil.getProgramSpecificAffix(),
              ApplicationBean.getVersionString());
          configurer.configureFileName(filedef, nameComponents, draft);
          configurer.configureWhichFiles(exportEml, forceOverwrite);
          configurer.setCSB(isCSB);

          rc = configurer.getReportConfiguration();

          if (N11.equals(config)) {
            boolean exportForCentralCounting = isExportForCentralCounting(request);
            if (exportForCentralCounting) {
              // Generate model II
              result = MessageKeys.Error_Model_II_KonnteNichtExportiertWerden;
              configurer.setDocument(ReportTemplateEnum.II, outputFormat);
              rc = configurer.getReportConfiguration();
              generator.createReport(rc, eml510);

              // Generate EML 510a (Totaaltelling)
              createAdditionalOSV4_1InMunicipality(forceOverwrite,
                  filedef,
                  generator,
                  draft,
                  outputFormat);

              // Generate Appendix 1 of Model II, but not another EML file
              result = MessageKeys.Error_Model_II_Appendix1_KonnteNichtExportiertWerden;
              configurer.setDocument(ReportTemplateEnum.OSV4_7, outputFormat);
              configurer.configureWhichFiles(ExportEml.NO, forceOverwrite);
              rc = configurer.getReportConfiguration();
              generator.createReport(rc, eml510);

            } else {
              // Generate model N11
              generator.createReport(rc, eml510);

              // Generate EML 510a (Totaaltelling)
              result = MessageKeys.Error_OSV4_1_KonnteNichtExportiertWerden;
              createAdditionalOSV4_1InMunicipality(forceOverwrite,
                  filedef,
                  generator,
                  draft,
                  outputFormat);
            }
          } else if (N10_1.equals(config)) {
            boolean exportForCentralCounting = isExportForCentralCounting(request);
            if (exportForCentralCounting) {
              // Generate empty Model I
              result = MessageKeys.Error_Model_I_KonnteNichtExportiertWerden;
              configurer.setDocument(ReportTemplateEnum.I, outputFormat);
              rc = configurer.getReportConfiguration();
              generator.createReport(rc, eml510);

              // Generate Appendix 2 of Model II
              result = MessageKeys.Error_Model_II_Appendix2_KonnteNichtExportiertWerden;
              configurer.setDocument(ReportTemplateEnum.OSV4_8, outputFormat);
              rc = configurer.getReportConfiguration();
              generator.createReport(rc, eml510);
            } else {
              // Generate model N10-1
              generator.createReport(rc, eml510);
            }
          } else {

            // The normal case (not model N11, not model N10-1)
            generator.createReport(rc, eml510);
          }
          break;

        case VOTES_CSV :
          ResultSummary resultSummary = getVotesHandling().getResultSummary();
          String csv = getExportHandling().createVotesCsvExport(resultSummary, eml510);
          rc = new ReportConfigurationCsvExport(filedef, nameComponents, isCSB, template);
          generator.createCsvExportFile(rc, csv);
          break;

        default :
          LOGGER.error("Unknown P4ExportType: " + config); //$NON-NLS-1$
          break;
      }

      getState(config)._modus++;
      _adminMsgExportConfirmation = Messages
          .bind(MessageKeys.Msg_ReportdateienErfolgreichExportiert_0, filedef.getAbsolutePath());
      getExportHandling().updateBackupArchive();
    } catch (GeneratedFileExistsException e) {
      // One of the files that shall be created, already exist. Before creating the exports, ask
      // the user if the existing exports shall be overwitten. Store the EML message for this
      // case.
      _adminWarningOverride = e.getMessage();
      if (!forceOverwrite) {
        storeEMLResult(session, eml510);
      }
    }
    return result;
  }

  public boolean isDocumentSelected(SystemProperty systemProperty) {
    return getPropertyHandling().getBooleanProperty(systemProperty.getKey());
  }

  private ReportOutputFormatEnum determineOutputFormat(HttpServletRequest request,
      ExportP4Type config) {
    final ReportOutputFormatEnum outputFormat;
    if (EMPTY_EML.equals(config)) {
      outputFormat = ReportOutputFormatEnum.RTF;
    } else if (N10_1.equals(config)) {
      outputFormat = ReportOutputFormatEnum.RTF;
    } else if (VOTES_CSV.equals(config)) {
      outputFormat = ReportOutputFormatEnum.CSV;
    } else {
      outputFormat = getRtfPdf(request);
    }
    return outputFormat;
  }

  /**
   * Only in GR and DR elections, together with N11 create an additional OSV 4-1 export in the PSB
   * (because CSB = PSB).
   */
  private void createAdditionalOSV4_1InMunicipality(boolean forceOverwrite,
      File filedef,
      ReportGenerator generator,
      boolean draft,
      ReportOutputFormatEnum outputFormat) throws GeneratedFileExistsException, Exception {

    WahlInfo wahlInfo = WahlInfo.getWahlInfo();
    int ebene = SystemInfo.getSystemInfo().getWahlEbene();
    ElectionCategory electionCategory = wahlInfo.getElectionCategory();

    if (electionCategory.isMunicipalityElection() && ebene == GebietModel.EBENE_PSB
        && wahlInfo.isFreigegeben()) {
      boolean isCSB = true;
      Gebiet wurzelgebiet = wahlInfo.getWahl().getWurzelgebiet();
      Document eml510d = getExportHandling().createEML510(wurzelgebiet, true, true, false);

      ReportConfigurer configurer = new ReportConfigurer();
      configurer.setDocument(ReportTemplateEnum.OSV4_1, outputFormat);
      configurer.setProgramNameAndVersion(EJBUtil.getProgramSpecificAffix(),
          ApplicationBean.getVersionString());
      configurer.configureFileName(filedef, ReportNameComponentsP4.fromEML510(eml510d, SystemInfo
          .getSystemInfo().isInputmodusComplete()), draft);
      configurer.configureWhichFiles(ExportEml.OVERWRITE, forceOverwrite);
      configurer.setCSB(isCSB);

      ReportConfiguration reportConfigurationOSV41 = configurer.getReportConfiguration();
      generator.createReport(reportConfigurationOSV41, eml510d);
    }
  }

  private Document createEML510(ExportP4Type exportType, boolean forceOverwrite, HttpSession session)
      throws ImportException {
    // Try to use the stored EML510 document
    Document result = null;
    if (forceOverwrite) {
      result = getStoredEMLResult(session);
    }
    removeStoredEMLResult(session);
    if (result != null) {
      return result;
    }

    // Create a new EML510 document
    Gebiet wurzelgebiet = WahlInfo.getWahlInfo().getWahl().getWurzelgebiet();
    if (EMPTY_EML.equals(exportType)) {
      return getExportHandling().createEmptyEML510(wurzelgebiet);
    } else if (T11.equals(exportType) || N10_1.equals(exportType)) {
      // For T11 and the empty N10-1 use an empty EML with RG elements
      return getExportHandling().createEML510(wurzelgebiet, true, false, true);
    } else {
      boolean createRGNodes = !exportType.equals(OSV4_1);
      return getExportHandling().createEML510(wurzelgebiet, createRGNodes);
    }
  }

  // Handling of DialogStateHolders

  private DialogStateHolder getState(ExportP4Type report) {
    return _stateMap.get(report);
  }

  private void reset(ExportP4Type report) {
    getState(report).reset();
  }

  public DialogStateHolder getP4ExportStateO3() {
    return getState(O3);
  }

  public DialogStateHolder getP4ExportStateN10_1() {
    return getState(N10_1);
  }

  public DialogStateHolder getP4ExportStateN11() {
    return getState(N11);
  }

  public DialogStateHolder getP4ExportStateT11() {
    return getState(T11);
  }

  public DialogStateHolder getP4ExportStateReferendum() {
    return getState(REF);
  }

  public DialogStateHolder getP4ExportStateEmptyEml() {
    return getState(EMPTY_EML);
  }

  public DialogStateHolder getP4ExportStateEML510c() {
    return getState(EML510C);
  }

  public DialogStateHolder getP4ExportStateOSV4_1() {
    return getState(OSV4_1);
  }

  public DialogStateHolder getP4ExportStateOSV4_4() {
    return getState(OSV4_4);
  }

  public DialogStateHolder getP4ExportStateOSV4_5() {
    return getState(OSV4_5);
  }

  public DialogStateHolder getP4ExportStateCSV() {
    return getState(VOTES_CSV);
  }

  public DialogStateHolder getP4ExportStateWrr83() {
    return getState(WRR83);
  }

  public DialogStateHolder getP4ExportStateOSV4_6() {
    return getState(OSV4_6);
  }

  public void resetExportStateN10_1() {
    reset(N10_1);
  }

  public void resetExportStateN11() {
    reset(N11);
  }

  public void resetExportStateOSV4_1() {
    reset(OSV4_1);
  }

  public void resetExportStateOSV4_4() {
    reset(OSV4_4);
  }

  public void resetExportStateOSV4_5() {
    reset(OSV4_5);
  }

  public void resetExportStateO3() {
    reset(O3);
  }

  public void resetExportStateT11() {
    reset(T11);
  }

  public void resetExportStateCSV() {
    reset(VOTES_CSV);
  }

  public void resetP4ExportStateWrr83() {
    reset(WRR83);
  }

  public void resetP4ExportStateOSV4_6() {
    reset(OSV4_6);
  }

  public void resetExportStateEML510c() {
    reset(EML510C);
  }

  public void resetExportStateReferendum() {
    reset(REF);
  }

  public void resetExportStateEmptyEml() {
    reset(EMPTY_EML);
  }

  public void initP4ExportState() {
    for (ExportP4Type type : ExportP4Type.values()) {
      reset(type);
    }
  }

  // Management of stored EML510 document

  private void storeEMLResult(final HttpSession session, Document eml510) {
    session.setAttribute(ATTR_EXPORT_RESULT_510, new SerializableSoftDocumentReference(eml510));
  }

  private Document getStoredEMLResult(final HttpSession session) {
    final SerializableSoftDocumentReference eml510Reference = (SerializableSoftDocumentReference) session
        .getAttribute(ATTR_EXPORT_RESULT_510);
    return eml510Reference == null ? null : eml510Reference.get();
  }

  private void removeStoredEMLResult(final HttpSession session) {
    session.removeAttribute(ATTR_EXPORT_RESULT_510);
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

}
