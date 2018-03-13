/*
 * Created on 05.11.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.io.File;
import java.util.Date;

import de.ivu.wahl.wus.reportgen.extension.IPDFOpener;

public class ReportConfigurationCsvExport implements ReportConfiguration {
  private final ReportTemplateEnum template;
  private final File exportSelection;
  private final ReportNameComponents reportNameComponents;
  private final boolean isCSB;

  public ReportConfigurationCsvExport(File exportSelection,
      ReportNameComponents reportNameComponents,
      boolean isCSB,
      ReportTemplateEnum template) {
    this.exportSelection = exportSelection;
    this.reportNameComponents = reportNameComponents;
    this.isCSB = isCSB;
    this.template = template;
  }

  public ReportConfiguration copyWithHashCode(String newHashCode) {
    return this;
  }

  public ExportEml exportEml() {
    return ExportEml.NO;
  }

  public boolean generatesDocument() {
    return true;
  }

  public File getExportSelectionPath() {
    return exportSelection;
  }

  public ReportOutputFormatEnum getFormat() {
    return ReportOutputFormatEnum.CSV;
  }

  public IPDFOpener getPdfOpener() {
    return null;
  }

  public ReportNameComponents getReportNameComponents() {
    return reportNameComponents;
  }

  public ReportOutputFormatEnum getSelectedFormat() {
    return ReportOutputFormatEnum.CSV;
  }

  public String getShaHashCode() {
    return null;
  }

  public String getStylesheetName() {
    return null;
  }

  public ReportTemplateEnum getTemplate() {
    return template;
  }

  public Date getTimestamp() {
    return new Date();
  }

  public boolean isDraft() {
    return false;
  }

  public boolean isCSB() {
    return isCSB;
  }

  public boolean openDocumentInViewer() {
    return false;
  }

  public boolean overwriteAllDocuments() {
    return true;
  }

  public MultiReportConfiguration getMultiReportConfiguration() {
    return null; // Not needed for CSV files
  }

  public ReportLanguage getLanguage() {
    return ReportLanguage.NL;
  }

  public String getProgramName() {
    // Program name is not needed because no EML file is created
    return ""; //$NON-NLS-1$
  }

  public String getProgramVersion() {
    // Program version is not needed because no EML file is created
    return ""; //$NON-NLS-1$
  }

}
