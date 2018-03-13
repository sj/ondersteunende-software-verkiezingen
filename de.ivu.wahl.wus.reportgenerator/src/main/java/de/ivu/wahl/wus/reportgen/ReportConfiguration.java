/*
 * ReportConfiguration
 * 
 * Created on 31.03.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.io.File;
import java.util.Date;

import de.ivu.wahl.wus.reportgen.extension.IPDFOpener;

public interface ReportConfiguration {
  public enum ExportEml {
    YES, // An EML file shall be created only if none exists so far
    NO, // No EML file shall be created
    OVERWRITE
    // A new EML file shall be created even if it already exists
  }

  /**
   * @return a copy of the receiver with the shaHashCode replaced by newHashCode
   */
  public ReportConfiguration copyWithHashCode(String newHashCode);

  // The following methods are about the GENERATION PROCESS of the documents

  /**
   * @return the file selectedFormat in which report is to be generated
   */
  public ReportTemplateEnum getTemplate();

  /**
   * @return the file selectedFormat in which the user has selected. Still some documents are always
   *         generated as PDF.
   */
  public ReportOutputFormatEnum getSelectedFormat();

  /**
   * @return the file format in which report is to be generated. For some documents the selected
   *         format is ignored. The are always generated as RTF.
   */
  public ReportOutputFormatEnum getFormat();

  // The following methods are about OPENING generated PDF documents

  /**
   * @return <code>true</code> if the report generator shall open the generated document in an
   *         external viewer after saving it. This option is only supported for PDF documents so
   *         far.
   */
  public boolean openDocumentInViewer();

  /**
   * @return The plugin that starts the PDF reader.
   */
  public IPDFOpener getPdfOpener();

  // The following methods define the CONTENT of the document

  public String getStylesheetName();

  public ReportLanguage getLanguage();

  /**
   * @return the sha-256 hash code that is to be printed in the footer of the generated document.
   *         May be <code>null</code>.
   */
  public String getShaHashCode();

  public Date getTimestamp();

  /**
   * Relevant for the file names and for the contents of the file.
   * 
   * @return <code>true</code> if a draft document is to be created. <code>false</code> if a final
   *         document is to be created.
   */
  public boolean isDraft();

  /**
   * The name of the program generating the file is written into the EML file in a comment.
   * Examples: "P1", "P4_CSB"
   */
  public String getProgramName();

  /**
   * The version of the program generating the file is written into the EML file in a comment.
   * Examples: "2.7.2", "2.9"
   */
  public String getProgramVersion();

  // The following methods define WHICH FILES are created (EML and / or others)

  /**
   * Specifies if the EML file shall
   * <ul>
   * <li>be created, if it does not exist (YES)</li>
   * <li>be created anyway (OVERWRITE)</li>
   * <li>not be created (NO)</li>
   * </ul>
   */
  public ExportEml exportEml();

  /**
   * @return <code>true</code>, if a document shall be generated (the normal case).
   *         <code>false</code> if no document but only the EML file shall be generated.
   */
  public boolean generatesDocument();

  /**
   * The result of this method controls the behaviour in the case that documents (one or more) that
   * shall be created already exist. If overwriteAllDocuments() returns true, the documents will be
   * overwritten (if possible). If overwriteAllDocuments() returns false, a
   * {@link GeneratedFileExistsException} is thrown.
   * 
   * @throws GeneratedFileExistsException
   */
  public boolean overwriteAllDocuments();

  // The following methods define the FILE NAMES

  /**
   * @return the path in the file system where generated reports should be saved
   */
  public File getExportSelectionPath();

  /**
   * @return <code>true</code> if the special file name for the CSB shall be used.
   */
  public boolean isCSB();

  public ReportNameComponents getReportNameComponents();

  public MultiReportConfiguration getMultiReportConfiguration();

}