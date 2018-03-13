/*
 * WahlImportBean
 * 
 * Created on 26.04.2007
 * Copyright (c) 2007-2017 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import static java.io.File.separatorChar;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import de.ivu.wahl.Konstanten;
import de.ivu.wahl.SystemInfo;
import de.ivu.wahl.dataimport.AbstractImportEML;
import de.ivu.wahl.dataimport.AbstractImportEML.UploadStreamHandler;
import de.ivu.wahl.dataimport.IImportEML;
import de.ivu.wahl.dataimport.ImportClientDb;
import de.ivu.wahl.dataimport.ImportElectionMetadata;
import de.ivu.wahl.dataimport.ImportReferendumMetadata;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;

/**
 * Importieren einer neuen Wahl in die Datenbank aus hochgeladenen Metadaten.
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class WahlImportBean extends BasicUploadBean {
  private static final long serialVersionUID = 7453307320770129023L;

  protected IImportEML _importMetadata;

  public static final String FELD_WAHLDEFINITION = ApplicationBeanKonstanten.PREFIX
      + "electiondefinition"; //$NON-NLS-1$
  public static final String FELD_EML230 = ApplicationBeanKonstanten.PREFIX + "eml230"; //$NON-NLS-1$
  public static final String FELD_EML630 = ApplicationBeanKonstanten.PREFIX + "eml630"; //$NON-NLS-1$

  public static final String FELD_GEBIETSNUMMER = ApplicationBeanKonstanten.PREFIX + "districtNr"; //$NON-NLS-1$
  public static final String FELD_ACCEPT_ELECTIONDEFINITION = ApplicationBeanKonstanten.PREFIX
      + "electionDefinition"; //$NON-NLS-1$

  private static final String FELD_HASHCODE_230 = ApplicationBeanKonstanten.PREFIX + "hashCode230"; //$NON-NLS-1$
  public static final String FELD_HASHCODE_230_INPUT_0 = FELD_HASHCODE_230 + "Input0"; //$NON-NLS-1$
  public static final String FELD_HASHCODE_230_INPUT_1 = FELD_HASHCODE_230 + "Input1"; //$NON-NLS-1$

  public static final String FELD_HASHCODE_WAHLDEFINITION = ApplicationBeanKonstanten.PREFIX
      + "hashCodeWahldefinition"; //$NON-NLS-1$
  public static final String FELD_EBENE = ApplicationBeanKonstanten.PREFIX + "ebene"; //$NON-NLS-1$
  public static final String FELD_RESET = ApplicationBeanKonstanten.PREFIX + "reset"; //$NON-NLS-1$

  /**
   * @author cos@ivu.de, IVU Traffic Technologies AG
   */
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
  final static Logger LOGGER = Logger.getLogger(WahlImportBean.class);

  /**
   */
  public WahlImportBean() {
    final SystemInfo systemInfo = SystemInfo.getSystemInfo();

    _importMetadata = new ImportElectionMetadata(systemInfo.getWahlModus(),
        systemInfo.getWahlEbene());

    _commandMap.put("imp_import_Wahl", new Command( //$NON-NLS-1$
        Messages.getString("Logger_ImportierenNeuerWahlInDieDatenbankGestartet")) { //$NON-NLS-1$

          private static final long serialVersionUID = 3321147219019479989L;

          @Override
          void execute(HttpServletRequest request) {
            if (_importMetadata == null || !(_importMetadata instanceof ImportElectionMetadata)) {
              _importMetadata = new ImportElectionMetadata(systemInfo.getWahlModus(), systemInfo
                  .getWahlEbene());
            }

            // Requestparameter auslesen
            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new BRPrintWriter(writer);
            if ("POST".equals(request.getMethod())) { //$NON-NLS-1$
              if (request.getParameter(FELD_RESET) != null) {
                _importMetadata.reset();
              } else if (request.getParameter(FELD_ACCEPT_ELECTIONDEFINITION) != null) {
                _importMetadata.setDefinitionAccepted(true);
              } else if (request.getParameter(FELD_HASHCODE_230_INPUT_0) != null
                  || request.getParameter(FELD_HASHCODE_230_INPUT_1) != null) {
                _importMetadata.setHashWert230Input(0,
                    request.getParameter(FELD_HASHCODE_230_INPUT_0));
                _importMetadata.setHashWert230Input(1,
                    request.getParameter(FELD_HASHCODE_230_INPUT_1));
              } else if (request.getParameter(FELD_GEBIETSNUMMER) != null) {
                LOGGER.info(Messages.getString("Logger_Gebiet") + request.getParameter(FELD_GEBIETSNUMMER)); //$NON-NLS-1$
                _importMetadata.setGebietsNr(Integer.parseInt(request
                    .getParameter(FELD_GEBIETSNUMMER)));
              } else {
                String remoteHost = request.getRemoteHost();
                try {
                  UploadStreamHandler handler = new UploadStreamHandler();
                  for (FileItem item : getItems(request)) {
                    if (item.isFormField()) {
                      LOGGER.debug(Messages
                          .bind(MessageKeys.Logger_POSTEnthaeltUnerwarteteDatenSieWerdenIgnoriert_0_1,
                              item.getFieldName(),
                              item.getString()));
                    } else {
                      writeFileIntoArchiveFolder(item, _importMetadata);
                      String fileName = item.getName();
                      String feldName = item.getFieldName();
                      if (fileName != null && !fileName.isEmpty()) {
                        fileName = ("/" + fileName).replace('\\', separatorChar); //$NON-NLS-1$

                        info(printWriter,
                            Messages.bind(MessageKeys.Logger_DateinameFuer_0_1, feldName, fileName));
                        byte[] input = item.get();
                        URL url = handler.add(remoteHost, fileName, input);
                        if (FELD_WAHLDEFINITION.equals(feldName)) {
                          _importMetadata.setDefinition(url);
                        } else if (FELD_EML230.equals(feldName)) {
                          _importMetadata.setEML230(url);
                        }
                        printWriter.println();
                      }
                    }
                  }
                  printWriter.println();
                } catch (Exception e) {
                  error(printWriter, e.getMessage(), e);
                }
              }
              // Neuen Status berechnen
              _importMetadata.updateStatus();
            }

            request.setAttribute("output", writer.getBuffer().toString()); //$NON-NLS-1$
            if (_importMetadata.getStatus() == AbstractImportEML.STATUS_KOMPLETT) {
              try {
                new ImportClientDb(_importMetadata).run();
              } catch (Exception e) {
                // Import ist insgesamt fehlgeschlagen und muss wieder von neuem beginnen
                _importMetadata.reset();
                _importMetadata.setFehlermeldung(e.getMessage());
                error(printWriter, e.getMessage(), e);
              }
            }
          }
        });

    _commandMap.put("imp_import_Referendum", new Command( //$NON-NLS-1$
        Messages.getString("Logger_ImportierenNeuenReferendumInDieDatenbankGestartet")) { //$NON-NLS-1$

          private static final long serialVersionUID = 6051778254387936403L;

          @Override
          void execute(HttpServletRequest request) {
            if (_importMetadata == null || !(_importMetadata instanceof ImportReferendumMetadata)) {
              _importMetadata = new ImportReferendumMetadata(systemInfo.getWahlModus(), systemInfo
                  .getWahlEbene());
            }

            // Requestparameter auslesen
            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new BRPrintWriter(writer);
            if ("POST".equals(request.getMethod())) { //$NON-NLS-1$
              if (request.getParameter(FELD_RESET) != null) {
                _importMetadata.reset();
              } else if (request.getParameter(FELD_ACCEPT_ELECTIONDEFINITION) != null) {
                _importMetadata.setDefinitionAccepted(true);
              } else if (request.getParameter(FELD_GEBIETSNUMMER) != null) {
                LOGGER.info(Messages.getString("Logger_Gebiet") + request.getParameter(FELD_GEBIETSNUMMER)); //$NON-NLS-1$
                _importMetadata.setGebietsNr(Integer.parseInt(request
                    .getParameter(FELD_GEBIETSNUMMER)));
              } else {
                String remoteHost = request.getRemoteHost();
                try {
                  UploadStreamHandler handler = new UploadStreamHandler();
                  for (FileItem item : getItems(request)) {
                    if (item.isFormField()) {
                      LOGGER.debug(Messages
                          .bind(MessageKeys.Logger_POSTEnthaeltUnerwarteteDatenSieWerdenIgnoriert_0_1,
                              item.getFieldName(),
                              item.getString()));
                    } else {
                      writeFileIntoArchiveFolder(item, _importMetadata);
                      String fileName = item.getName();
                      String feldName = item.getFieldName();
                      if (fileName != null && !fileName.isEmpty()) {
                        fileName = ("/" + fileName).replace('\\', separatorChar); //$NON-NLS-1$

                        info(printWriter,
                            Messages.bind(MessageKeys.Logger_DateinameFuer_0_1, feldName, fileName));
                        byte[] input = item.get();
                        URL url = handler.add(remoteHost, fileName, input);
                        if (FELD_EML630.equals(feldName)) {
                          _importMetadata.setDefinition(url);
                        }
                        printWriter.println();
                      }
                    }
                  }
                  printWriter.println();
                } catch (Exception e) {
                  error(printWriter, e.getMessage(), e);
                }
              }
              // Neuen Status berechnen
              _importMetadata.updateStatus();
            }

            request.setAttribute("output", writer.getBuffer().toString()); //$NON-NLS-1$
            if (_importMetadata.getStatus() == AbstractImportEML.STATUS_KOMPLETT) {
              try {
                new ImportClientDb(_importMetadata).run();
              } catch (Exception e) {
                // Import ist insgesamt fehlgeschlagen und muss wieder von neuem beginnen
                _importMetadata.reset();
                _importMetadata.setFehlermeldung(e.getMessage());
                error(printWriter, e.getMessage(), e);
              }
            }
          }
        });
  }

  /**
   * Gibt importDefinition zur�ck.
   * 
   * @return importDefinition.
   */
  public IImportEML getImportMetadata() {
    return _importMetadata;
  }

}
