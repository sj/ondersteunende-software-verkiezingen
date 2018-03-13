/*
 * ReportConfigurationImpl
 * 
 * Created on 18.11.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import de.ivu.wahl.wus.reportgen.extension.IPDFOpener;

/**
 * Parameter object for calling {@link ReportGenerator#createReport()}. This class is immutable.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class ReportConfigurationImpl implements ReportConfiguration {
  private final static List<ReportTemplateEnum> ALWAYS_RTF = Arrays.asList(ReportTemplateEnum.OSV2_1,
      ReportTemplateEnum.OSV3_2,
      ReportTemplateEnum.OSV3_7,
      ReportTemplateEnum.OSV5_1,
      ReportTemplateEnum.OSV5_2);
  private final static List<ReportTemplateEnum> ALWAYS_PDF = Arrays.asList(ReportTemplateEnum.OSV3_3,
      ReportTemplateEnum.OSV3_6,
      ReportTemplateEnum.OSV3_9,
      ReportTemplateEnum.OSV4_1);
  private final static List<ReportTemplateEnum> SUPPORTS_FRISIAN = Arrays.asList(ReportTemplateEnum.H1,
      ReportTemplateEnum.H3_1,
      ReportTemplateEnum.H3_2,
      ReportTemplateEnum.H4,
      ReportTemplateEnum.H9,
      ReportTemplateEnum.OSV1_1);

  private final ReportTemplateEnum template;
  private final ReportOutputFormatEnum selectedFormat;
  private final File exportSelection;
  private final Date timestamp;
  private final ReportNameComponents reportNameComponents;
  private final boolean draft;
  private final String shaHashCode;
  private final boolean openDocumentInViewer;
  private final IPDFOpener pdfOpener;
  private final ExportEml exportEml;
  private final boolean overwriteAllDocuments;
  private final boolean isCSB;
  private final MultiReportConfiguration multiReportConfiguration;
  private final ReportLanguage language;
  private final String programName;
  private final String programVersion;

  /**
   * @param template
   * @param selectedFormat
   * @param exportSelection
   * @param timestamp this identifies the print. The generated files are saved in a directory whose
   *          name is derived from this timestamp.
   * @param reportNameComponents
   * @param draft
   * @param shaHashCode is ignored if draft == true
   * @param openDocumentInViewer is ignored unless selectedFormat is PDF
   * @param pdfOpener
   * @param exportEml
   * @param multiReportConfiguration may be null. Defines multiple documents created from the same
   *          EML file.
   */
  ReportConfigurationImpl(ReportTemplateEnum template,
      ReportOutputFormatEnum selectedFormat,
      File exportSelection,
      Date timestamp,
      ReportNameComponents reportNameComponents,
      boolean draft,
      String shaHashCode,
      boolean openDocumentInViewer,
      IPDFOpener pdfOpener,
      ExportEml exportEml,
      boolean overwriteAllDocuments,
      boolean isCSB,
      MultiReportConfiguration multiReportConfiguration,
      ReportLanguage language,
      String programName,
      String programVersion) {
    assert template != null : "template must not be null";
    assert selectedFormat != null : "selectedFormat must not be null";

    this.template = template;
    this.selectedFormat = selectedFormat;
    this.exportSelection = exportSelection;
    this.timestamp = timestamp;
    this.reportNameComponents = reportNameComponents;
    this.draft = draft;
    this.shaHashCode = shaHashCode;
    this.openDocumentInViewer = openDocumentInViewer;
    this.pdfOpener = pdfOpener;
    this.exportEml = exportEml;
    this.overwriteAllDocuments = overwriteAllDocuments;
    this.isCSB = isCSB;
    this.multiReportConfiguration = multiReportConfiguration;
    this.language = language;
    this.programName = programName;
    this.programVersion = programVersion;
  }

  public ReportConfiguration copyWithHashCode(String newHashCode) {
    return new ReportConfigurationImpl(template, selectedFormat, exportSelection, timestamp,
        reportNameComponents, draft, newHashCode, openDocumentInViewer, pdfOpener, exportEml,
        overwriteAllDocuments, isCSB, multiReportConfiguration, language, programName,
        programVersion);
  }

  public IPDFOpener getPdfOpener() {
    return pdfOpener;
  }

  public ReportTemplateEnum getTemplate() {
    return template;
  }

  public ReportOutputFormatEnum getSelectedFormat() {
    return selectedFormat;
  }

  public ReportOutputFormatEnum getFormat() {
    if (ALWAYS_RTF.contains(template)) {
      return ReportOutputFormatEnum.RTF;
    }
    if (ALWAYS_PDF.contains(template)) {
      return ReportOutputFormatEnum.PDF;
    }
    return selectedFormat;
  }

  public boolean isDraft() {
    return draft;
  }

  public boolean openDocumentInViewer() {
    return openDocumentInViewer;
  }

  public String getShaHashCode() {
    return shaHashCode;
  }

  public File getExportSelectionPath() {
    return exportSelection;
  }

  @Override
  public String toString() {
    String sep = ", "; //$NON-NLS-1$
    return getClass().getSimpleName()
        + "(" + template + sep + selectedFormat + sep + exportSelection + ")"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public ExportEml exportEml() {
    return exportEml;
  }

  public boolean generatesDocument() {
    // First exception: If the empty EML510 is created, do not generate a document.
    if (ReportTemplateEnum.EML.equals(getTemplate())) {
      return false;
    }

    // Second exception: Hashcode file for incomplete election.
    if (ReportTemplateEnum.OSV4_1.equals(getTemplate())) {
      if (reportNameComponents instanceof ReportNameComponentsP4) {
        ReportNameComponentsP4 reportNameComponentsP4 = (ReportNameComponentsP4) reportNameComponents;
        if (!reportNameComponentsP4.isComplete()) {
          return false;
        }
      }
    }

    return true;
  }

  public ReportNameComponents getReportNameComponents() {
    return reportNameComponents;
  }

  public String getStylesheetName() {
    return getTemplate().getStylesheetPrefix() + getFormat().getStylesheetSuffix();
  }

  public boolean overwriteAllDocuments() {
    return overwriteAllDocuments;
  }

  public boolean isCSB() {
    return isCSB;
  }

  public MultiReportConfiguration getMultiReportConfiguration() {
    return multiReportConfiguration;
  }

  public ReportLanguage getLanguage() {
    if (SUPPORTS_FRISIAN.contains(getTemplate())) {
      return language;
    } else {
      return ReportLanguage.NL;
    }
  }

  public String getProgramName() {
    return programName;
  }

  public String getProgramVersion() {
    return programVersion;
  }

}
