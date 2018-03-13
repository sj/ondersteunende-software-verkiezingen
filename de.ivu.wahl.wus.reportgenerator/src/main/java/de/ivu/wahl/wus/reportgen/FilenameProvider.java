/*
 * FilenameProvider
 * 
 * Created on 28.01.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ibm.icu.text.Normalizer;

import de.ivu.wahl.wus.reportgen.i18n.MessageKeys;
import de.ivu.wahl.wus.reportgen.i18n.Messages;

public class FilenameProvider {
  private static final String UNDERSCORE = "_";

  private final ReportConfiguration config;

  public FilenameProvider(ReportConfiguration config) {
    this.config = config;
  }

  /**
   * @return the directory in the file system where generated reports should be saved
   */
  public File getExportSelectionPath() {
    return config.getExportSelectionPath();
  }

  /**
   * e.g. %OSV%/PS2004/
   * 
   * @return the directory where all files for the election will be stored
   */
  public File getExportRoot() {
    ReportNameComponents reportNameComponents = config.getReportNameComponents();
    if (reportNameComponents.useElectionIdentifier()) {
      return new File(config.getExportSelectionPath(), reportNameComponents.getElectionIdentifier());
    } else {
      return config.getExportSelectionPath();
    }
  }

  /**
   * e.g. %OSV%/PS2004/P1_2009-02-04_18-00-00_CDA-Boer/ or %OSV%/P3_2009-02-04_18-00-00/
   * 
   * @return The directory where the files for the program and timestamp will be stored
   */
  public File getOutputDirectory() {
    // Now with flat file structure, see OSV-324
    return getExportRoot();
  }

  /**
   * e.g. %OSV%/P3_2009-02-04_18-00-00/PDF/
   * 
   * @return The subdirectory of the OutputDirectory for a certain file extension
   */
  public File getOutputSubdirectory() {
    // Now with flat file structure, see OSV-324
    return getOutputDirectory();
  }

  /**
   * e.g. %OSV%/P3_2009-02-04_18-00-00/EML/
   * 
   * @return the subdirectory of the OutputDirectory for the EML files
   */
  public File getEmlDirectory() {
    // Now with flat file structure, see OSV-324
    return getOutputDirectory();
  }

  /**
   * @return the text document (.rtf, .pdf or .csv) that shall be created
   */
  public File getOutputFile() {
    return getOutputFile(null);
  }

  /**
   * @return the text document (.rtf, .pdf or .csv) that shall be created
   */
  public File getOutputFile(String additionalComponentOrNull) {
    File outputDirectory = getOutputSubdirectory();
    String templateLongName = config.isCSB()
        ? config.getTemplate().getDescriptionCSB()
        : config.getTemplate().getDescription();
    String filename = "";
    if (config.isDraft()) {
      filename += Messages.getString(MessageKeys.Draft) + UNDERSCORE;
    }
    filename += config.getTemplate().getFilePrefix() + UNDERSCORE + templateLongName
        + getConstantFileComponents(additionalComponentOrNull) + "." //$NON-NLS-1$ //$NON-NLS-2$
        + config.getFormat().getFileExtension();
    return new File(outputDirectory, filename);
  }

  public String getConstantFileComponents(String additionalComponentOrNull) {
    return getConstantFileComponents(config.getReportNameComponents(), additionalComponentOrNull);
  }

  public static String getConstantFileComponents(ReportNameComponents reportNameComponents) {
    return getConstantFileComponents(reportNameComponents, null);
  }

  public static String getConstantFileComponents(ReportNameComponents reportNameComponents,
      String additionalComponentOrNull) {
    StringBuilder builder = new StringBuilder();
    List<String> nameComponents = new ArrayList<String>(reportNameComponents.getNameComponents());
    if (additionalComponentOrNull != null) {
      nameComponents.add(additionalComponentOrNull);
    }
    for (String component : nameComponents) {
      component = Normalizer.normalize(component, Normalizer.NFKD);
      if (component.length() > 0) {
        builder.append(UNDERSCORE); //$NON-NLS-1$
        for (Character ch : component.toCharArray()) {
          if (filter(ch)) {
            builder.append(ch);
          }
        }
      }
    }
    return builder.toString();
  }

  public File getOutputEmlFile(String emlFileSuffix) {
    File emlDir = getEmlDirectory();
    String suffix = ""; //$NON-NLS-1$
    if (ReportTemplateEnum.EML.equals(config.getTemplate())) {
      suffix = UNDERSCORE + RgConstants.EMPTY_EML_SUFFIX; //$NON-NLS-1$
    }
    String filename = "";
    if (config.isDraft()) {
      filename += Messages.getString(MessageKeys.Draft) + UNDERSCORE;
    }
    filename += config.getReportNameComponents().getEmlFileFirstName()
        + getConstantFileComponents((String) null) + suffix + emlFileSuffix;
    return new File(emlDir, filename);
  }

  private static boolean filter(Character ch) {
    return Character.isDigit(ch) || 'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z' || ch == '_';
  }
}
