/*
 * AnteilWert
 * 
 * Copyright (c) 2002-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.auswertung.erg;

import java.util.StringTokenizer;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;

/**
 * Erweitert Wert um eine Bezugsgr��e f�r aktuell und Vergleich
 * 
 * @author klie@ivu.de - IVU Traffic Technologies AG
 */

public class AnteilWert extends Wert implements Cloneable {
  private static final long serialVersionUID = -625789968737467316L;

  private static final Category LOGGER = Log4J.configure(AnteilWert.class);

  static {
    LOGGER.info(Log4J.dumpVersion(AnteilWert.class, Log4J.extractVersion("$Revision: 1.5 $"))); //$NON-NLS-1$
  }

  private int _aktBase;
  private int _vglBase;

  /**
   * Konstruktor
   * 
   * @param akt Aktueller Wert
   * @param aktBasis Bezugswert aktuell
   * @param vgl Vergleichswert
   * @param vglBasis Bezugswert vergleich
   */
  public AnteilWert(int akt, int aktBasis, int vgl, int vglBasis) {
    super(akt, vgl);
    _aktBase = aktBasis;
    _vglBase = vglBasis;
  }

  public double getAktProz() {
    if (getAktWert() < 0) {
      return 0.0;
    }
    return getAktWert() * 100.0 / _aktBase;
  }

  public double getVglProz() {
    if (getVglWert() < 0) {
      return 0.0;
    }
    return getVglWert() * 100.0 / _vglBase;
  }

  public double getProsPunkteDiff() {
    return getAktProz() - getVglProz();
  }

  public void setBasis(int aktBasis, int vglBasis) {
    _aktBase = aktBasis;
    _vglBase = vglBasis;
  }

  @Override
  public String toDBString() {
    return getAktWert() + " | " + _aktBase; //$NON-NLS-1$
  }

  public static Wert fromDBString(String valArt, String valVerglArt) {
    AnteilWert ret = null;
    try {
      int akt = 0;
      int aktBase = 0;
      int vgl = 0;
      int vglBase = 0;

      StringTokenizer tokenizer = null;
      if (valArt != null) {
        tokenizer = new StringTokenizer(valArt, "|"); //$NON-NLS-1$
        akt = Integer.parseInt(tokenizer.nextToken().trim());
        aktBase = Integer.parseInt(tokenizer.nextToken().trim());
      }
      if (valVerglArt != null) {
        tokenizer = new StringTokenizer(valVerglArt, "|"); //$NON-NLS-1$
        vgl = Integer.parseInt(tokenizer.nextToken().trim());
        vglBase = Integer.parseInt(tokenizer.nextToken().trim());
      }
      ret = new AnteilWert(akt, aktBase, vgl, vglBase);
    } catch (Exception ex) {
      LOGGER.error("Cannot reconstruct value!", ex); //$NON-NLS-1$
    }
    return ret;
  }

  public AnteilWert createClone() {
    try {
      return (AnteilWert) clone();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public void plus(int akt, int aktBasis, int vgl, int vglBasis) {
    super.plus(akt, vgl);
    if (aktBasis >= 0) {
      _aktBase += aktBasis;
    }
    if (vglBasis >= 0) {
      _vglBase += vglBasis;
    }
  }

  public void plus(AnteilWert aw) {
    plus(aw.getAktWert(), aw._aktBase, aw.getVglWert(), aw._vglBase);
  }
}
