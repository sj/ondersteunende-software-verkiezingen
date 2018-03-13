/*
 * Wert
 * 
 * Copyright (c) 2002-9 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.auswertung.erg;

import java.io.Serializable;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;

/**
 * Objekt, welches einen Integer und einen Vergleichswert h�lt und auch vergleichende Funktionen
 * anbietet
 * 
 * @author klie@ivu.de - IVU Traffic Technologies AG
 */

public class Wert implements Serializable, Cloneable {
  private static final long serialVersionUID = -413842674668302934L;

  private static final Category LOGGER = Log4J.configure(Wert.class);

  static {
    LOGGER.info(Log4J.dumpVersion(Wert.class, Log4J.extractVersion("$Revision: 1.5 $"))); //$NON-NLS-1$
  }

  protected int _wertAkt;
  protected int _wertVgl;

  /**
   * Konstruktor
   * 
   * @param akt Aktueller Wert
   * @param vgl Vergleichswert
   */
  public Wert(int akt, int vgl) {
    _wertAkt = akt;
    _wertVgl = vgl;
  }

  /**
   * @return aktuellen wert
   */
  public int getAktWert() {
    return _wertAkt;
  }

  /**
   * setzen des aktuellen Werts
   * 
   * @param wert der Wert
   */
  public void setAktWert(int wert) {
    _wertAkt = wert;
  }

  /**
   * @return Vergleichswert
   */
  public int getVglWert() {
    return _wertVgl;
  }

  /**
   * Setzen des vergleichserts
   * 
   * @param vglWert Vergleichswert
   */
  public void setVglWert(int vglWert) {
    _wertVgl = vglWert;
  }

  /**
   * @return akt - vgl
   */
  public int getDiff() {
    return (_wertAkt < 0 ? 0 : _wertAkt) - (_wertVgl < 0 ? 0 : _wertVgl);
  }

  /**
   * @return prozentualer Unterschied zwischen vergleichswert und aktuellem wert
   */
  public double getProzDiff() {
    return getDiff() * 100.0 / _wertVgl;
  }

  public void plus(int akt, int vgl) {
    // Summiere nur, wenn der Wert >= 0!! Negative Werte sind Marker f�r keine Kandidaten/Listen!!!
    if (akt >= 0) {
      _wertAkt = _wertAkt + akt;
    }
    if (vgl >= 0) {
      _wertVgl = _wertVgl + vgl;
    }
  }

  public void plus(Wert w) {
    plus(w.getAktWert(), w.getVglWert());
  }

  @Override
  public String toString() {
    return "[Value: act: " + _wertAkt + ", cmp: " + _wertVgl + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  public String toDBString() {
    return Integer.toString(_wertAkt);
  }

  /**
   * null -> 0
   * 
   * @param valArt String mit dem Wert aus der akuellen Wahlergebnisart
   * @param valVerglArt String mit dem Wert aus der Vergleichswahlergebnisart
   * @return Wert, gebildet aus den �bergebenen Strings
   */
  public static Wert fromDBString(String valArt, String valVerglArt) {
    Wert ret = null;
    try {
      int akt;
      int vgl;
      if (valArt != null) {
        akt = Integer.parseInt(valArt);
      } else {
        akt = 0;
      }
      if (valVerglArt != null) {
        vgl = Integer.parseInt(valVerglArt);
      } else {
        vgl = 0;
      }
      ret = new Wert(akt, vgl);
    } catch (Exception ex) {
      LOGGER.error("Value cannot be reconstructed!", ex); //$NON-NLS-1$
    }
    return ret;
  }

  public Wert createCloneWert() {
    try {
      return (Wert) clone();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
