/*
 * Created on 18.02.2011
 * Copyright (c) 2011 Kiesraad
 */
package de.ivu.wahl.wus.utils.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Simple helper class reading a plain text file and converting the content to a list of Strings
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class ReadFileUtil {
  private static final Logger LOGGER = Logger.getLogger(ReadFileUtil.class);

  public static List<String> readListFromFile(String fileName) {
    InputStream is = WidgetFactory.class.getResourceAsStream(fileName);
    return readListFromInputStream(fileName, is);
  }

  public static List<String> readListFromInputStream(String fileName, InputStream is) {
    List<String> list = new ArrayList<String>();
    BufferedReader br;
    try {
      br = new BufferedReader(new InputStreamReader(is, "UTF-8")); //$NON-NLS-1$
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException(e);
    }
    String line;
    try {
      while (null != (line = br.readLine())) {
        list.add(line);
      }
    } catch (IOException e) {
      LOGGER.error("Failed to read file: " + fileName, e); //$NON-NLS-1$
      throw new RuntimeException("Failed to read file:" + fileName, e); //$NON-NLS-1$
    } finally {
      try {
        br.close();
      } catch (IOException e) {
        // nothing to do
        LOGGER.warn("Failed closing file", e); //$NON-NLS-1$
      }
    }

    LOGGER.debug(fileName + " read. #lines: " + list.size()); //$NON-NLS-1$
    return list;
  }

  public static Map<String, String> readMapFromFile(String fileName) {
    Map<String, String> result = new HashMap<String, String>();
    List<String> list = readListFromFile(fileName);
    for (String string : list) {
      if (string != null) {
        String[] tokens = string.split("=");
        if (tokens.length == 1) {
          result.put(tokens[0], null);
        } else if (tokens.length > 1) {
          result.put(tokens[0], tokens[1]);
        }
      }
    }

    return result;
  }

}
