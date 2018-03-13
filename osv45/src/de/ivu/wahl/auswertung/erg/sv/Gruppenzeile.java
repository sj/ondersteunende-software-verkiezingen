/*
 * Gruppenzeile
 * 
 * Created on 20.02.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.auswertung.erg.sv;

import java.io.Serializable;

import de.ivu.wahl.modell.GruppeGebietsspezifischModel;
import de.ivu.wahl.modell.GruppeModel;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class Gruppenzeile implements Serializable, Comparable<Gruppenzeile> {
  private static final long serialVersionUID = -5655263391212026093L;

  private final GruppeModel _gruppe;
  private final GruppeGebietsspezifischModel _gruppeGebietsspezifisch;
  private final int _stimmenanzahl;
  private final float _prozetualerAnteil;
  private final int _sitzanzahl;

  /**
   * Constructor
   * 
   * @param gruppe
   * @param gruppeGebietsspezifisch
   * @param stimmenanzahl
   * @param prozetualerAnteil
   * @param sitzanzahl
   */
  public Gruppenzeile(GruppeModel gruppe,
      GruppeGebietsspezifischModel gruppeGebietsspezifisch,
      int stimmenanzahl,
      float prozetualerAnteil,
      int sitzanzahl) {
    _gruppeGebietsspezifisch = gruppeGebietsspezifisch;
    _gruppe = gruppe;
    _stimmenanzahl = stimmenanzahl;
    _prozetualerAnteil = prozetualerAnteil;
    _sitzanzahl = sitzanzahl;
  }

  public int compareTo(Gruppenzeile o) {

    return _gruppeGebietsspezifisch.getPosition() - o._gruppeGebietsspezifisch.getPosition();
  }

  /**
   * @return {@link GruppeModel} f�r die Gruppe, um dessen Ergebnis es sich handelt
   */
  public GruppeModel getGruppe() {
    return _gruppe;
  }

  public GruppeGebietsspezifischModel getGruppeGebietsspezifisch() {
    return _gruppeGebietsspezifisch;
  }

  /**
   * @return Parteiname
   */
  public String getGruppenName() {
    return _gruppe.getNameLang();
  }

  public int getGruppenschluessel() {
    return _gruppe.getSchluessel();
  }

  public int getStimmenanzahl() {
    return _stimmenanzahl;
  }

  public float getProzetualerAnteil() {
    return _prozetualerAnteil;
  }

  public int getSitzanzahl() {
    return _sitzanzahl;
  }
}
