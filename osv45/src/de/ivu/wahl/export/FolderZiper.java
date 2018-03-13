/*
 * Created on 01.12.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import de.ivu.wahl.wus.reportgen.RgConstants;

/**
 * Original code by Yashwant Chavan, modified by jon@ivu.de and tdu@ivu.de
 * 
 * @see http://stackoverflow.com/questions/2271801/zip-and-unzip-the-folders-and-files-using-java
 */
public class FolderZiper {
  
  private static final int BUFFER_SIZE = 1024 * 1024;
  private static String SEPARATOR = File.separator;
  private static String EMPTY_STRING = ""; //$NON-NLS-1$
  private static Set<String> alreadyZippedFiles = null;

  public static void zipFolderFilterResults(String electionId, String destZipFile, String... srcFolders) throws Exception {
    alreadyZippedFiles = new HashSet<String>();
    ZipOutputStream zip = null;
    try {
      FileOutputStream fileWriter = new FileOutputStream(destZipFile);
      zip = new ZipOutputStream(fileWriter);

      for (String srcFolder : srcFolders) {
        boolean addName = false;
        boolean filterResults = true;
        addFolderToZip(EMPTY_STRING, srcFolder, zip, addName, filterResults, electionId);
      }
      
      zip.flush();
    } finally {
      if (zip != null) {
        zip.close();
      }
    }
    alreadyZippedFiles = null;
  }
  
  public static void zipFolder(String destZipFile, String srcFolder) throws Exception {
    ZipOutputStream zip = null;
    try {
      FileOutputStream fileWriter = new FileOutputStream(destZipFile);
      zip = new ZipOutputStream(fileWriter);

      boolean addName = false;
      boolean filterResults = false;
      addFolderToZip(EMPTY_STRING, srcFolder, zip, addName, filterResults, null);
      zip.flush();
    } finally {
      if (zip != null) {
        zip.close();
      }
    }
  }

  private static void addFolderToZip(String path,
      String srcFolder,
      ZipOutputStream zip,
      boolean addName,
      boolean filterResults,
      String electionId) throws Exception {
    File folder = new File(srcFolder);
    if (folder.isDirectory()) { // only add existing folders
      String newName = path;
      if (addName) {
        if (path.equals(EMPTY_STRING)) {
          newName += folder.getName();
        } else {
          newName += SEPARATOR + folder.getName();
        }
      }
      for (String fileName : folder.list()) {
        addFileOrFolderToZip(newName, srcFolder + SEPARATOR + fileName, zip, filterResults, electionId);
      }
    }
  }

  private static void addFileOrFolderToZip(String path,
      String srcFile,
      ZipOutputStream zip,
      boolean filterResults,
      String electionId)
      throws Exception {
    File folder = new File(srcFile);
    if (folder.isDirectory()) {
      if (folder.getName().equals("archive")) { //$NON-NLS-1$
        boolean addName = !filterResults;
        addFolderToZip(path, srcFile, zip, addName, filterResults, electionId);
      }
    } else {
      if (!filterResults || isResultFile(srcFile, electionId)) {
        // Add file to zip
        byte[] buf = new byte[BUFFER_SIZE];
        int len;
        FileInputStream in = new FileInputStream(srcFile);
        try {
          String entryName = path;
          if (entryName.length() > 0) {
            entryName += SEPARATOR;
          }
          entryName += folder.getName();
    
          // prevent files with the same name are written twice
          if (alreadyZippedFiles == null || !alreadyZippedFiles.contains(entryName)) {
            zip.putNextEntry(new ZipEntry(entryName));
            while ((len = in.read(buf)) > 0) {
              zip.write(buf, 0, len);
            }
            if (alreadyZippedFiles != null) {
              alreadyZippedFiles.add(entryName);
            }
          }
        } finally {
          in.close();
        }
      }
    }
  }

  private static boolean isResultFile(String srcFile, String electionId) {
    String filename = srcFile.substring(srcFile.lastIndexOf(SEPARATOR) + 1);
    if (filename.endsWith(".eml.xml") //$NON-NLS-1$
        && (filename.startsWith(RgConstants.FILENAME_110A)
          || filename.startsWith(RgConstants.FILENAME_630)
          || filename.startsWith(RgConstants.FILENAME_230B)
          || filename.startsWith(RgConstants.FILENAME_510_COMPLETE) && !filename.contains(RgConstants.FILENAME_FRAGMENT_STEMBUREAU)
          || filename.startsWith(RgConstants.FILENAME_510D_COMPLETE)
          || filename.startsWith(RgConstants.FILENAME_520))
        && isContainsElectionId(filename, electionId)) {
      return true;
    }
    return false;
  }

  private static boolean isContainsElectionId(String srcFile, String electionId) {
    if (electionId == null) {
      return true;
    }
    
    /*
     *  Split election domain from category and year part, because Totaaltelling filename has the word
     *  gemeente in between.   
     */
    String[] electionIdParts = electionId.split("_"); //$NON-NLS-1$
    for (String electionIdPart : electionIdParts) {
      if (!srcFile.contains(electionIdPart)) {
        return false;
      }
    }
    
    return true;
  }
}
