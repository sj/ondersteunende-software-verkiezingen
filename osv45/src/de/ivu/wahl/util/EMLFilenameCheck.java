/*
 * EMLFilenameCheck
 * 
 * Created on 20.02.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.util;

import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.wus.reportgen.RgConstants;

/**
 * Before file import, check if the file name matches the expected patterns
 */
public class EMLFilenameCheck {
  private static final String CURRENT_FOLDER = "."; //$NON-NLS-1$

  private static boolean isEMLFilename(String fileName, String type) {
    return fileName != null && -1 != fileName.indexOf(type) && checkEMLEnd(fileName);
  }

  public static boolean is110aFilename(String fileName) {
    return isEMLFilename(fileName, RgConstants.FILENAME_110A);
  }

  public static boolean is630Filename(String fileName) {
    return isEMLFilename(fileName, RgConstants.FILENAME_630);
  }

  public static boolean is210Filename(String fileName) {
    return isEMLFilename(fileName, RgConstants.FILENAME_210);
  }

  public static boolean is230aFilename(String fileName) {
    return isEMLFilename(fileName, RgConstants.FILENAME_230A);
  }

  public static boolean is230bFilename(String fileName) {
    return isEMLFilename(fileName, RgConstants.FILENAME_230B);
  }

  public static boolean is230cFilename(String fileName) {
    return isEMLFilename(fileName, RgConstants.FILENAME_230C);
  }

  public static boolean is110bFilename(String fileName) {
    return isEMLFilename(fileName, RgConstants.FILENAME_110B);
  }

  private static boolean is510abcFilename(String fileName) {
    return isEMLFilename(fileName, RgConstants.FILENAME_510_COMPLETE);
  }

  public static boolean is510aFilename(String fileName) {
    return is510abcFilename(fileName)
        && fileName.contains(RgConstants.FILENAME_FRAGMENT_STEMBUREAU);
  }

  /**
   * Note: For GR, BC and GC elections, the word "gemeente" is omitted in the file name
   */
  public static boolean is510bFilename(String fileName) {
    if (!is510abcFilename(fileName)) {
      return false;
    }
    if (fileName.contains("GR") || fileName.contains("BC") || fileName.contains("GC")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      return true;
    }
    if (WahlInfo.getWahlInfo().isEK()) {
      return fileName.contains(RgConstants.FILENAME_FRAGMENT_STEMBUREAU);
    } else {
      return fileName.contains(RgConstants.FILENAME_FRAGMENT_MUNICIPALITY)
          || fileName.contains(RgConstants.FILENAME_FRAGMENT_ISLAND_MUNICIPALITY_IN_FILENAME);
    }
  }

  public static boolean is510cFilename(String fileName) {
    if (!is510abcFilename(fileName)) {
      return false;
    }
    if (WahlInfo.getWahlInfo().isEK()) {
      return fileName.contains(RgConstants.FILENAME_FRAGMENT_PROVINCE);
    } else {
      return fileName.contains(RgConstants.FILENAME_FRAGMENT_DISTRICT);
    }
  }

  public static boolean is510dFilename(String fileName) {
    return isEMLFilename(fileName, RgConstants.FILENAME_510D_COMPLETE);
  }

  public static boolean is520Filename(String fileName) {
    return isEMLFilename(fileName, RgConstants.FILENAME_520);
  }

  public static String reduceFilenameFromSlashAsPrefix(String filename) {
    if (filename.startsWith("/")) { //$NON-NLS-1$
      return filename.substring(1);
    }
    return filename;

  }

  private static boolean checkEMLEnd(String fileName) {
    return fileName.endsWith(RgConstants.SUFFIX_EML_XML);
  }

  public static String getUnterverzeichnis(String fileName) {
    // Now with flat file structure, see OSV-324

    if (is510aFilename(fileName) || EMLFilenameCheck.is110bFilename(fileName)) { // 510a, 110b
      return CURRENT_FOLDER;
    } else if (is510bFilename(fileName)) { // 510b
      return CURRENT_FOLDER;
    } else if (is510cFilename(fileName)) { // 510c
      return CURRENT_FOLDER;
    } else if (!checkEMLEnd(fileName)) { // non EML
      return RgConstants.PATH_FRAGMENT_OTHERS;
    } else if (is510dFilename(fileName) || is520Filename(fileName) || is230aFilename(fileName)
        || is230bFilename(fileName) || is230cFilename(fileName) || is110aFilename(fileName)
        || is630Filename(fileName)) {
      // 510d, 520, 230a, 230b, 230c, 110a (election definition), 630 (referendum)
      return CURRENT_FOLDER;
    } else {
      // other
      return RgConstants.PATH_FRAGMENT_OTHERS;
    }
  }
}
