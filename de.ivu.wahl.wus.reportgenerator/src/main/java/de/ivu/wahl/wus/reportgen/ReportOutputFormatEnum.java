/*
 * ReportOutputFormatEnum
 * 
 * Created on 17.11.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.util.Arrays;
import java.util.List;

/**
 * Possible values for the output file format
 */
public enum ReportOutputFormatEnum {
  PDF("pdf", "-to-FO.xslt"), //$NON-NLS-1$ //$NON-NLS-2$
  RTF("rtf", "-to-RTF.xslt"), //$NON-NLS-1$ //$NON-NLS-2$
  HTML("html", "-to-HTML.xslt"), //$NON-NLS-1$ //$NON-NLS-2$
  CSV("csv", null); //$NON-NLS-1$

  public static List<ReportOutputFormatEnum> getSupportedFormats() {
    return Arrays.asList(PDF, RTF);
  }

  private ReportOutputFormatEnum(String fileExtension, String stylesheetSuffix) {
    this.fileExtension = fileExtension;
    this.stylesheetSuffix = stylesheetSuffix;
  }

  private final String fileExtension;
  private final String stylesheetSuffix;

  public String getFileExtension() {
    return fileExtension;
  }

  public String getStylesheetSuffix() {
    return stylesheetSuffix;
  }

}
