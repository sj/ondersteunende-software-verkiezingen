package de.ivu.wahl.auswertung.erg;

import java.io.Serializable;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;

/**
 * Ergebnis.java BasisErgebnis Objekt Von diesem leiten sich alle anderen ErgebnisObjekte ab
 * 
 * @author mur 14.10.2003 Copyright (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public class Ergebnis implements Serializable {
  private static final long serialVersionUID = -8276769080178131743L;

  private static final Category LOGGER = Log4J.configure(Ergebnis.class);

  static {
    LOGGER.info(Log4J.dumpVersion(Ergebnis.class, Log4J.extractVersion("$Revision: 1.4 $"))); //$NON-NLS-1$
  }

  /**
   * Ergebnisbezeichnung Klartext (kann eine l�ngere Zeile sein)
   */
  protected String _ergBezeichnung;

  /**
   * Zeitpunkt der Erstellung in Millisekunden
   */
  protected long _timestamp = System.currentTimeMillis();

  /**
   * @param ergBezeichnung
   */
  public Ergebnis(String ergBezeichnung) {
    _ergBezeichnung = ergBezeichnung;
  }

  protected Ergebnis(Ergebnis anderesErgebnis) {
    _ergBezeichnung = anderesErgebnis._ergBezeichnung;
  }

  /**
   * @return Ergebnisbezeichnung Klartext (kann eine l�ngere Zeile sein)
   * @param defaultValue wenn _ergBezeichnung == null
   */
  public String getErgBezeichnung(String defaultValue) {
    return _ergBezeichnung != null ? _ergBezeichnung : defaultValue;
  }

  /**
   * @return Ergebnisbezeichnung Klartext (kann eine l�ngere Zeile sein)
   */
  public String getErgBezeichnung() {
    return _ergBezeichnung;
  }

  /**
   * @return Zeitpunkt der Erstellung in Millisekunden
   */
  public long getTimestamp() {
    return _timestamp;
  }

  /**
   * @param string Ergebnisbezeichnung Klartext
   */
  public void setErgBezeichnung(String string) {
    _ergBezeichnung = string;
  }
}
