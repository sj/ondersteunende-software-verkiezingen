/*
 * Created on 03.02.2011
 * Copyright (c) 2011 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.io.File;
import java.util.Date;

import de.ivu.wahl.wus.reportgen.ReportConfiguration.ExportEml;
import de.ivu.wahl.wus.reportgen.extension.IPDFOpener;

public class ReportConfigurer {
  private ReportTemplateEnum template;
  private ReportOutputFormatEnum selectedFormat;
  private File exportSelection;
  private Date timestamp = new Date();
  private ReportNameComponents reportNameComponents;
  private boolean draft;
  private boolean openDocumentInViewer = false;
  private IPDFOpener pdfOpener = null;
  private ExportEml exportEml;
  private boolean overwriteAllDocuments;
  private boolean isCSB = false;
  private MultiReportConfiguration multiReportConfiguration = null;
  private ReportLanguage language = ReportLanguage.NL;
  private String programName = null;
  private String programVersion = null;

  // *********** Basic configuration *********************

  /**
   * Sets the template and output format
   */
  public void setDocument(ReportTemplateEnum template, ReportOutputFormatEnum selectedFormat) {
    this.template = template;
    this.selectedFormat = selectedFormat;
  }

  /**
   * Sets the pdfOpener and the flag "openDocumentInViewer"
   */
  public void setPdfOpener(IPDFOpener pdfOpener) {
    this.openDocumentInViewer = (pdfOpener != null);
    this.pdfOpener = pdfOpener;
  }

  /**
   * Sets the programs name and version
   */
  public void setProgramNameAndVersion(String programName, String programVersion) {
    this.programName = programName;
    this.programVersion = programVersion;
  }

  /**
   * Configure the filename and path of the generated file/s
   */
  public void configureFileName(File exportSelection,
      ReportNameComponents reportNameComponents,
      boolean draft) {
    this.exportSelection = exportSelection;
    this.reportNameComponents = reportNameComponents;
    this.draft = draft;
  }

  /**
   * Configure if EML shall be created and if existing documents shall be replaced.
   */
  public void configureWhichFiles(ExportEml exportEml, boolean overwriteAllDocuments) {
    this.exportEml = exportEml;
    this.overwriteAllDocuments = overwriteAllDocuments;
  }

  // *********** Special configuration *********************

  /**
   * Sets the language (default is NL). The only other option is FY (Frisian).
   */
  public void setLanguage(ReportLanguage language) {
    this.language = language;
  }

  /**
   * Sets the creation timestamp. Default is the time when the constructor of the ReportConfigurer
   * was called.
   */
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Setter for "isCSB" attribute. Default is false. Set it to true if the special filename for CSB
   * ("Totaaltelling" instead of "Telling") shall be used (only for P4);
   */
  public void setCSB(boolean isCSB) {
    this.isCSB = isCSB;
  }

  public void setMultiReportConfiguration(MultiReportConfiguration multiReportConfiguration) {
    this.multiReportConfiguration = multiReportConfiguration;
  }

  // *********** create ReportConfiguration *********************

  public ReportConfiguration getReportConfiguration() {
    String hashcode = null;
    return new ReportConfigurationImpl(template, selectedFormat, exportSelection, timestamp,
        reportNameComponents, draft, hashcode, openDocumentInViewer, pdfOpener, exportEml,
        overwriteAllDocuments, isCSB, multiReportConfiguration, language, programName,
        programVersion);
  }

}
