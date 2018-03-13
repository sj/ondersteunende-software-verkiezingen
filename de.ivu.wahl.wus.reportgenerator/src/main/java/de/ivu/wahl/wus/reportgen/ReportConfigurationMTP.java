/*
 * Created on 31.03.2009
 * Copyright (c) 2009-2011 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.io.File;
import java.util.Date;

import de.ivu.wahl.wus.reportgen.extension.IPDFOpener;

/**
 * Parameter object for calling {@link ReportGenerator#createReport()}. This class is immutable.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class ReportConfigurationMTP implements ReportConfiguration {
  private final File exportSelection;
  private final boolean openDocumentInViewer;
  private final IPDFOpener pdfOpener;
  private final ExportEml exportEml;
  private final String programVersion;

  /**
   * Constructor
   * 
   * @param openDocumentInViewer is ignored unless selectedFormat is PDF
   */
  public ReportConfigurationMTP(File exportSelection,
      ReportNameComponents reportNameComponents,
      boolean openDocumentInViewer,
      IPDFOpener pdfOpener,
      ExportEml exportEml,
      String programVersion) {

    this.exportSelection = exportSelection;
    this.openDocumentInViewer = openDocumentInViewer;
    this.pdfOpener = pdfOpener;
    this.exportEml = exportEml;
    this.programVersion = programVersion;
  }

  public ReportConfiguration copyWithHashCode(String newHashCode) {
    return this;
  }

  public IPDFOpener getPdfOpener() {
    return pdfOpener;
  }

  public ReportTemplateEnum getTemplate() {
    if (System.getProperty("os.name").toLowerCase().matches(".*mac.*")) {
      return ReportTemplateEnum.MTP1_ON_MAC;
    }
    return ReportTemplateEnum.MTP1;
  }

  public ReportOutputFormatEnum getSelectedFormat() {
    return ReportOutputFormatEnum.PDF;
  }

  public ReportOutputFormatEnum getFormat() {
    return ReportOutputFormatEnum.PDF;
  }

  public boolean isDraft() {
    return false;
  }

  public boolean openDocumentInViewer() {
    return openDocumentInViewer;
  }

  public String getShaHashCode() {
    return "";
  }

  public File getExportSelectionPath() {
    return exportSelection;
  }

  @Override
  public String toString() {
    String sep = ", "; //$NON-NLS-1$
    return getClass().getSimpleName()
        + "(" + getTemplate() + sep + getFormat() + sep + exportSelection + ")"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  public Date getTimestamp() {
    return new Date();
  }

  public ExportEml exportEml() {
    return exportEml;
  }

  public boolean generatesDocument() {
    return true;
  }

  public ReportNameComponents getReportNameComponents() {
    return new ReportNameComponentsMTP();
  }

  public String getStylesheetName() {
    return getTemplate().getStylesheetPrefix() + getFormat().getStylesheetSuffix();
  }

  public String getElectionIdentifier() {
    return "";
  }

  public boolean overwriteAllDocuments() {
    return true;
  }

  public boolean isCSB() {
    return false;
  }

  public MultiReportConfiguration getMultiReportConfiguration() {
    return null; // Not needed for MTP
  }

  public ReportLanguage getLanguage() {
    return ReportLanguage.NL;
  }

  public String getProgramName() {
    return "MTP";
  }

  public String getProgramVersion() {
    return programVersion;
  }
}
