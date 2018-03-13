package de.ivu.wahl.modell.exception;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Exception f�r Fehler w�hrend des Imports von Daten. Es wird jeweils der Dateiname zur
 * Identifikation und evtl. ein Offset mit der Feldposition gehalten. Liegt eine andere Exception zu
 * Grunde, so kann auch diese gespeichert werden.
 * 
 * @author klie@ivu.de cos@ivu.de Copyright (c) 2004 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public class ImportException extends Exception {
  private static final long serialVersionUID = 7741979276964228880L;

  public final static int TYPE_IO = 0;

  public final static int TYPE_CONTENT = 1;

  public final static int TYPE_WAHL_GESCHLOSSEN = 2;

  private URL _url = null;

  private int _offset = -1;

  private int _type = -1;

  // Falls ein Adressat ermittelt werden konnte, wird der hier eingetragen
  // -1 ist unbekannt
  private int _gebietsnummer = -1;

  private int _gebietsart = -1;

  /**
   * @param t {@link Throwable}, welche die Unterbrechung der Verarbeitung verursacht hat
   */
  public ImportException(Throwable t) {
    super(t);
  }

  /**
   * @param msg menschenlesbare Fehlermeldung
   * @param t {@link Throwable}, welche die Unterbrechung der Verarbeitung verursacht hat
   */
  public ImportException(String msg, Throwable t) {
    super(msg, t);
  }

  /**
   * @param msg menschenlesbare Fehlermeldung
   */
  public ImportException(String msg) {
    super(msg);
  }

  /**
   * @param path Dateipfad zum Datensatz
   * @param msg menschenlesbare Fehlermeldung
   * @deprecated
   */
  @Deprecated
  public ImportException(String path, String msg) {
    this(makeURL(path), msg, -1, null, -1, -1);
  }

  /**
   * @param file Datei des Datensatzes
   * @param msg menschenlesbare Fehlermeldung
   * @param offset bei Blockformaten: Offset in der Quelle, an dem der Fehler entdeckt wurde
   * @param t {@link Throwable}, welche die Unterbrechung der Verarbeitung verursacht hat
   * @param zielGebietsnummer Nummer des Gebiets, zu dem die importierten Daten geh�ren
   * @param zielGebietsart Gebietsart des Gebiets, zu dem die importierten Daten geh�ren
   */
  public ImportException(File file,
      String msg,
      int offset,
      Throwable t,
      int zielGebietsnummer,
      int zielGebietsart) {
    this(makeURL(file.getAbsolutePath()), msg, offset, t, zielGebietsnummer, zielGebietsart);
  }

  /**
   * @param url {@link URL} zum Datensatz
   * @param msg menschenlesbare Fehlermeldung
   * @param offset bei Blockformaten: Offset in der Quelle, an dem der Fehler entdeckt wurde
   * @param t {@link Throwable}, welche die Unterbrechung der Verarbeitung verursacht hat
   * @param zielGebietsnummer Nummer des Gebiets, zu dem die importierten Daten geh�ren
   * @param zielGebietsart Gebietsart des Gebiets, zu dem die importierten Daten geh�ren
   */
  public ImportException(URL url,
      String msg,
      int offset,
      Throwable t,
      int zielGebietsnummer,
      int zielGebietsart) {
    super(msg, t);
    _url = url;
    _offset = offset;
    _gebietsnummer = zielGebietsnummer;
    _gebietsart = zielGebietsart;
  }

  /**
   * @param file Datei des Datensatzes
   * @param msg menschenlesbare Fehlermeldung
   * @param offset bei Blockformaten: Offset in der Quelle, an dem der Fehler entdeckt wurde
   * @param t {@link Throwable}, welche die Unterbrechung der Verarbeitung verursacht hat
   */
  public ImportException(File file, String msg, int offset, Throwable t) {
    this(file, msg, offset, t, -1, -1);
  }

  /**
   * @param url {@link URL} zum Datensatz
   * @param msg menschenlesbare Fehlermeldung
   * @param offset bei Blockformaten: Offset in der Quelle, an dem der Fehler entdeckt wurde
   * @param t {@link Throwable}, welche die Unterbrechung der Verarbeitung verursacht hat
   */
  public ImportException(URL url, String msg, int offset, Throwable t) {
    this(url, msg, offset, t, -1, -1);
  }

  /**
   * @param file Datei des Datensatzes
   * @param msg menschenlesbare Fehlermeldung
   * @param offset bei Blockformaten: Offset in der Quelle, an dem der Fehler entdeckt wurde
   */
  public ImportException(File file, String msg, int offset) {
    this(file, msg, offset, null);
  }

  /**
   * @param file Datei des Datensatzes
   * @param msg menschenlesbare Fehlermeldung
   */
  public ImportException(File file, String msg) {
    this(file, msg, -1, null);
  }

  /**
   * @param url {@link URL} zum Datensatz
   * @param msg menschenlesbare Fehlermeldung
   */
  public ImportException(URL url, String msg) {
    this(url, msg, -1, null);
  }

  /**
   * @param type Typ des Fehlers
   * @param msg menschenlesbare Fehlermeldung
   */
  public ImportException(int type, String msg) {
    this(msg);
    _type = type;
  }

  /**
   * @param type Typ des Fehlers
   * @param file Datei des Datesatzes
   * @param msg menschenlesbare Fehlermeldung
   */
  public ImportException(int type, File file, String msg) {
    this(file, msg, -1, null);
    _type = type;
  }

  /**
   * @param type Typ des Fehlers
   * @param url {@link URL} zum Datensatz
   * @param msg menschenlesbare Fehlermeldung
   */
  public ImportException(int type, URL url, String msg) {
    this(url, msg, -1, null);
    _type = type;
  }

  /**
   * @return URL der Quelle als String
   */
  public String getURLString() {
    return _url.toExternalForm();
  }

  /**
   * @return bei Blockformaten: Offset in der Quelle, an dem der Fehler entdeckt wurde
   */
  public int getOfset() {
    return _offset;
  }

  /**
   * @return Typ des Fehlers
   */
  public int getType() {
    return _type;
  }

  /**
   * @param gebietsnummer Nummer des Gebiets, zu dem die importierten Daten geh�ren
   */
  public void setZielGebietsnummer(int gebietsnummer) {
    _gebietsnummer = gebietsnummer;
  }

  /**
   * @return Nummer des Gebiets, zu dem die importierten Daten geh�ren
   */
  public int getZielGebietsnummer() {
    return _gebietsnummer;
  }

  /**
   * @param gebietsart Gebietsart des Gebiets, zu dem die importierten Daten geh�ren
   */
  public void setZielGebietsart(int gebietsart) {
    _gebietsart = gebietsart;
  }

  /**
   * @return Gebietsart des Gebiets, zu dem die importierten Daten geh�ren
   */
  public int getZielGebietsart() {
    return _gebietsart;
  }

  /**
   * Wandelt einen Dateipfad in eine {@link URL} um
   * 
   * @param path Dateipfad
   * @return {@link URL}, die dem Dateipfad entspricht
   */
  private static URL makeURL(String path) {
    try {
      return new URL("file", null, path); //$NON-NLS-1$
    } catch (MalformedURLException e) {
      // cannot normally happen
      return null;
    }
  }
}