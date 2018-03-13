/*
 * ErgebnisImportBean
 * 
 * Created on 19.01.2009
 * Copyright (c) 2009-2017 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import static de.ivu.ejb.EJBUtil.lookupLocal;
import static de.ivu.wahl.client.beans.ApplicationBean.getAnwContext;
import static java.io.File.separatorChar;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJBException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import de.ivu.util.collections.TwoKeyMap;
import de.ivu.wahl.AnwContext;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.SystemInfo;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.admin.AdminHandling;
import de.ivu.wahl.admin.AdminHandlingBean;
import de.ivu.wahl.client.util.ClientHelper;
import de.ivu.wahl.dataimport.AbstractImportEML;
import de.ivu.wahl.dataimport.AbstractImportEML.UploadStreamHandler;
import de.ivu.wahl.dataimport.EingangMsgXML;
import de.ivu.wahl.dataimport.EingangMsgXMLFactory;
import de.ivu.wahl.dataimport.ErgebnisImportHandlerDb;
import de.ivu.wahl.dataimport.ErgebnisImportHandlerMsg;
import de.ivu.wahl.dataimport.ImportClientErgebnis;
import de.ivu.wahl.dataimport.ImportEML510;
import de.ivu.wahl.eingang.EingangHandling;
import de.ivu.wahl.eingang.EingangHandlingBean;
import de.ivu.wahl.eingang.EingangMsg;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ErgebniseingangKonstanten;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.exception.ImportException;

/**
 * Importieren der Ergebnisse in die Datenbank aus hochgeladenen Metadaten.
 * 
 * @author SMA@ivu.de, IVU Traffic Technologies AG
 */
public class ErgebnisImportBean extends BasicUploadBean {

  private static final long serialVersionUID = 5202716539734862404L;

  protected final TwoKeyMap<Integer, Integer, ImportEML510> _importEML510s = new TwoKeyMap<Integer, Integer, ImportEML510>();
  public static final String FELD_EML510 = ApplicationBeanKonstanten.PREFIX + "eml510"; //$NON-NLS-1$
  private static final String FELD_HASHCODE_510 = ApplicationBeanKonstanten.PREFIX + "hashCode510"; //$NON-NLS-1$
  public static final String FELD_HASHCODE_510_INPUT_0 = FELD_HASHCODE_510 + "Input0"; //$NON-NLS-1$
  public static final String FELD_HASHCODE_510_INPUT_1 = FELD_HASHCODE_510 + "Input1"; //$NON-NLS-1$
  public static final String FELD_RESET = ApplicationBeanKonstanten.PREFIX + "reset"; //$NON-NLS-1$

  static final class BRPrintWriter extends PrintWriter {

    /**
     * @param writer
     */
    BRPrintWriter(Writer writer) {
      super(writer, true);
    }

    @Override
    public void println() {
      super.write(Konstanten.BR);
      super.println();
    }
  }

  void info(PrintWriter printWriter, String message) {
    LOGGER.info(message);
    printWriter.println(message);
  }

  void error(PrintWriter printWriter, String message, Throwable t) {
    LOGGER.error(message, t);
    printWriter.print("<div style='color: #D00; background-color: #FEE';>"); //$NON-NLS-1$
    printWriter.println(message);
    t.printStackTrace(printWriter);
    printWriter.print("</div>"); //$NON-NLS-1$
  }

  /** Logger for instances of this class */
  final static Logger LOGGER = Logger.getLogger(ErgebnisImportBean.class);

  /**
   */
  public ErgebnisImportBean() {
    _commandMap.put("ergImp_import_Ergebnisse", new Command( //$NON-NLS-1$
        Messages.getString(MessageKeys.Msg_ImportierenNeuerErgebnisseInDieDatenbankGestartet)) {

      private static final long serialVersionUID = -360994175636562444L;

      @Override
      void execute(HttpServletRequest request) {
        SystemInfo sI = SystemInfo.getSystemInfo();
        // Requestparameter auslesen
        int gebietsart = Integer.parseInt(request.getParameter(ApplicationBeanKonstanten.LEVEL));
        int gebNr = ClientHelper.getGebietNr(request);
        ImportEML510 importEML510 = _importEML510s.get(gebietsart, gebNr);
        if (importEML510 == null) {
          importEML510 = new ImportEML510(sI.getWahlEbene(), sI.getWahlModus(), gebietsart, gebNr);
          _importEML510s.put(gebietsart, gebNr, importEML510);
        }

        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new BRPrintWriter(writer);
        if ("POST".equals(request.getMethod())) { //$NON-NLS-1$
          if (request.getParameter(FELD_RESET) != null) {
            importEML510.reset();
            return;
          } else if (request.getParameter(FELD_HASHCODE_510_INPUT_0) != null
              || request.getParameter(FELD_HASHCODE_510_INPUT_1) != null) {
            importEML510.setHashWert510Input(0, request.getParameter(FELD_HASHCODE_510_INPUT_0));
            importEML510.setHashWert510Input(1, request.getParameter(FELD_HASHCODE_510_INPUT_1));
          } else {
            String remoteHost = request.getRemoteHost();
            try {
              UploadStreamHandler handler = new UploadStreamHandler();
              for (FileItem item : getItems(request)) {
                if (item.isFormField()) {
                  LOGGER.info(Messages
                      .bind(MessageKeys.Logger_POSTEnthaeltUnerwarteteDatenSieWerdenIgnoriert_0_1,
                          item.getFieldName(),
                          item.getString()));
                } else {
                  if (importEML510.getStatus() == AbstractImportEML.STATUS_KOMPLETT) {
                    importEML510.reset();
                  }
                  // START
                  writeFileIntoArchiveFolder(item, importEML510);
                  String fileName = item.getName();
                  fileName = new File(fileName.replace('\\', separatorChar)).getName();
                  String feldName = item.getFieldName();
                  if (fileName != null && !fileName.isEmpty()) {
                    info(printWriter,
                        Messages.bind(MessageKeys.Msg_DateinameFuer_0_1, feldName, fileName));
                    byte[] input = item.get();
                    URL url510 = handler.add(remoteHost, "/" + fileName, input); //$NON-NLS-1$
                    importEML510.setEML510(url510);
                    printWriter.println();
                  }
                }
              }
              printWriter.println();
            } catch (Exception e) {
              importEML510.setStatus(AbstractImportEML.STATUS_ERROR);
              importEML510.setFehlermeldung(e.getMessage());
              error(printWriter, e.getMessage(), e);
            }
          }
          importEML510.updateStatus();
          if (importEML510.getStatus() == AbstractImportEML.STATUS_KOMPLETT
              && importEML510.getFehlermeldung() == null) {
            final HttpSession session = request.getSession(false);
            int defaultSessionTimeout = session.getMaxInactiveInterval();
            try {
              session
                  .setAttribute("defaultSessionTimeout", Integer.toString(defaultSessionTimeout)); //$NON-NLS-1$
              session.setMaxInactiveInterval(60 * 60); // set to 60 minutes

              readMsg(getAnwContext(request),
                  importEML510.getEML510(),
                  importEML510.getGebietsart(),
                  importEML510.getGebietsNr());
              String filename = importEML510.getEML510().getPath();
              WahlInfo wahlInfo = WahlInfo.getWahlInfo(getAnwContext(request));
              String gebietname = wahlInfo.getName4Gebiet(importEML510.getGebietsart(),
                  importEML510.getGebietsNr());
              importEML510.reset();
              importEML510.setStatus(AbstractImportEML.STATUS_KOMPLETT);
              importEML510.setLastFileName(filename);
              importEML510.setLastGebietName(gebietname);
              importEML510.setLastImport(new SimpleDateFormat().format(new Date()));
            } catch (Exception e) {
              String fehler = e.getMessage() == null ? Messages
                  .getString(MessageKeys.Msg_UnbekannterFehlerBeimEinlesenDerDaten) : e
                  .getMessage();
              importEML510.reset();
              importEML510.setFehlermeldung(fehler);
              error(printWriter, e.getMessage(), e);
            } finally {
              long interval = System.currentTimeMillis() - session.getLastAccessedTime();
              session.setMaxInactiveInterval((int) (interval / 1000 + defaultSessionTimeout));
            }
          } else if (importEML510.getStatus() != AbstractImportEML.STATUS_URL_KOMPLETT
              && importEML510.getFehlermeldung() != null) {
            // if there is a Error while Hash-Input, do not reset the Importdefinition and try to
            // input the Hashcode again
            String fehler = importEML510.getFehlermeldung();
            importEML510.reset();
            importEML510.setFehlermeldung(fehler);
          }
        }

        request.setAttribute("output", writer.getBuffer().toString()); //$NON-NLS-1$
      }
    });
  }

  AdminHandling _admHandling = null;

  /**
   * @return AdminHandling
   */
  AdminHandling getAdminHandling() {
    if (_admHandling == null) {
      _admHandling = lookupLocal(AdminHandlingBean.class.getSimpleName());
    }
    return _admHandling;
  }

  private transient EingangHandling _eingangHandling = null;

  /**
   * @return EingangHandling
   */
  EingangHandling getEingangHandling() {
    if (_eingangHandling == null) {
      try {
        _eingangHandling = lookupLocal(EingangHandlingBean.class.getSimpleName());
      } catch (Exception e) {
        LOGGER.error(e, e);
      }
    }
    return _eingangHandling;
  }

  /**
   * Gibt importEML510 zur�ck.
   * 
   * @param gebArt
   * @param gebNr
   * @return importEML510.
   */
  public ImportEML510 getImportEML510(int gebArt, int gebNr) {
    ImportEML510 result = _importEML510s.get(gebArt, gebNr);
    if (result == null) {
      SystemInfo sI = SystemInfo.getSystemInfo();
      result = new ImportEML510(sI.getWahlEbene(), sI.getWahlModus(), gebArt, gebNr);
      _importEML510s.put(gebArt, gebNr, result);
    }
    return result;
  }

  /**
   * Read new EML 510 result file
   * 
   * @param anwContext Anwenderkontext the user' rights
   * @param url URL
   * @param gebietsart region category of import
   * @param gebietsnummer number of region
   * @return EingangMsg with results
   * @throws ImportException error in xml content
   * @throws EJBException technical error
   */
  EingangMsg readMsg(AnwContext anwContext, URL url, int gebietsart, int gebietsnummer)
      throws ImportException, EJBException {
    LOGGER.info(Messages.bind(MessageKeys.Logger_Datei_0_istZumLesenEingegangen,
        url.toExternalForm()));

    WahlInfo wahlInfo = WahlInfo.getWahlInfo();
    wahlInfo.getWahl().lock();
    EingangMsgXML msg = null;
    Gebiet gebiet = getEingangHandling().getGebiet(anwContext, gebietsart, gebietsnummer);
    EingangMsgXMLFactory.checkElectionAndRegion(anwContext, url, gebiet);
    if (gebietsart == wahlInfo.getGebietsartWurzelgebiet()) {
      new ImportClientErgebnis(new ErgebnisImportHandlerDb(wahlInfo)).importVotingResults(url);
    } else {
      msg = EingangMsgXMLFactory.createEingangMsg(anwContext, url, gebiet);
      ErgebnisImportHandlerMsg importHandler = new ErgebnisImportHandlerMsg(wahlInfo, msg);
      new ImportClientErgebnis(importHandler).importVotingResults(url);
      msg = importHandler.getEingangMsg();
      @SuppressWarnings("unused")
      String id_Ergebniseingang = getEingangHandling().processInput(msg);
      // After successfully reading a result file manual input is forbidden for this region
      if (msg.getStatus() == ErgebniseingangKonstanten.STATE_OK) {
        // leave it here just in case the code for subregionresults gets fixed
        // if (gebiet.getGebietsart() != GebietModel.GEBIETSART_STIMMBEZIRK) {
        // saveSubregionResults(msg, id_Ergebniseingang, gebiet.getID_Gebiet());
        // }
        gebiet.setGUIEingabeErlaubt(false);
      }
    }
    wahlInfo.synchronize();
    return msg;
  }

  // /**
  // * All result files but EML510a include subregion results which have to be stored in database
  // *
  // * @param msg
  // */
  // private void saveSubregionResults(EingangMsgXML msg, String id_Ergebniseingang, String
  // id_Gebiet) {
  // if (msg.getRootElement() == null) {
  // return;
  // }
  // StimmergebnisseUntergebiete subResults;
  // try {
  // subResults = getStimmergebnisseUntergebieteHome().create(EJBUtil.getUniqueKey());
  // subResults.setID_Ergebniseingang(id_Ergebniseingang);
  // subResults.setID_Gebiet(id_Gebiet);
  // subResults.setErgebnisseXML(msg.getRootElement().toXML());
  // return;
  // } catch (CreateException e) {
  // throw new EJBException(Messages
  // .getString(MessageKeys.Error_FehlerBeimSpeichernDerUnterergebnisse), e);
  // }
  // }
  //
}
