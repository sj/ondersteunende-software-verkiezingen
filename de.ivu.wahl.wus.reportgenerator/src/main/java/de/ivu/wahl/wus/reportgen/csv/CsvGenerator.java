/*
 * Created on 31.08.2010
 * Copyright (c) 2010 Kiesraad
 */
package de.ivu.wahl.wus.reportgen.csv;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class that helps to generate CSV files.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class CsvGenerator {
  private static final char SEMICOLON = ';';
  private static final char CR = '\n';
  private static final String QUOTES = "\""; //$NON-NLS-1$
  private static final String DOUBLE_QUOTES = "\"\""; //$NON-NLS-1$
  private static final String EMPTY_STRING = ""; //$NON-NLS-1$

  private final List<List<String>> table = new ArrayList<List<String>>();
  private List<String> line = null;

  public CsvGenerator() {
    createNewLine();
  }

  /**
   * Discards the current line, if it has not been added, yet and starts and adds a new line.
   */
  public void startLine() {
    createNewLine();
    addCurrentLine();
  }

  /**
   * Adds the current line, if it has not been added, yet and starts and adds a new line.
   */
  public void newLine() {
    addCurrentLine();
    createNewLine();
    addCurrentLine();
  }

  /**
   * Adds the current line, if it has not been added, yet and starts a new line.
   */
  public void finishLine() {
    addCurrentLine();
    createNewLine();
  }

  public void add(String string) {
    line.add(string);
  }

  public void addEmptyCell() {
    add(EMPTY_STRING);
  }

  public void add(Object object) {
    if (object == null) {
      addEmptyCell();
    } else {
      add(object.toString());
    }
  }

  private void createNewLine() {
    line = new ArrayList<String>();
  }

  private void addCurrentLine() {
    if (table.isEmpty() || table.get(table.size() - 1) != line) {
      table.add(line);
    }
  }

  public String getCsv() {
    StringBuilder builder = new StringBuilder();
    for (List<String> eachLine : table) {
      boolean firstCell = true;
      for (String cell : eachLine) {
        if (firstCell) {
          firstCell = false;
        } else {
          builder.append(SEMICOLON);
        }
        builder.append(QUOTES);
        if (cell != null) {
          builder.append(cell.replace(QUOTES, DOUBLE_QUOTES));
        }
        builder.append(QUOTES);
      }

      builder.append(CR);
    }

    return builder.toString();
  }

}
