/*
 * Besonderheiten
 * 
 * Created on 25.03.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.auswertung.erg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains conflicts and loting results for a given result. Used by sitzverteilungErg.jsp.
 * 
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public class Besonderheiten {

  List<KonfliktAnzeige> _konflikte;
  final List<String> _besonderheiten = new ArrayList<String>();
  List<String> _deceasedCandidates;
  final String _id_Ergebniseingang;

  public Besonderheiten(String id_Ergebniseingang) {
    _id_Ergebniseingang = id_Ergebniseingang;
    _konflikte = new ArrayList<KonfliktAnzeige>();
    _deceasedCandidates = new ArrayList<String>();
  }

  /**
   * Gibt konflikte zur�ck.
   * 
   * @return konflikte.
   */
  public List<KonfliktAnzeige> getKonflikte() {
    return _konflikte;
  }

  public void addKonflikt(int art, List<String> alternativen) {
    addKonflikt(art, alternativen, -1);
  }

  public void addKonflikt(int art, List<String> alternativen, int losgewinner) {
    _konflikte.add(new KonfliktAnzeige(art, alternativen, losgewinner));
  }

  /**
   * Setzt den neuen Wert f�r konflikte auf konflikte.
   * 
   * @param konflikte neuer Wert f�r konflikte
   */
  public void setKonflikte(List<KonfliktAnzeige> konflikte) {
    _konflikte = konflikte;
  }

  /**
   * Gibt deceasedCandidates zur�ck.
   * 
   * @return deceasedCandidates.
   */
  public List<String> getDeceasedCandidates() {
    return _deceasedCandidates;
  }

  /**
   * Setzt den neuen Wert f�r deceasedCandidates auf deceasedCandidates.
   * 
   * @param deceasedCandidates neuer Wert f�r deceasedCandidates
   */
  public void addDeceasedCandidate(String deceasedCandidate) {
    _deceasedCandidates.add(deceasedCandidate);
  }

  public String getId_Ergebniseingang() {
    return _id_Ergebniseingang;
  }

  public static class KonfliktAnzeige {
    final int art;
    final List<String> alternativen;
    final int losgewinner;

    /**
     * Constructor
     */
    @SuppressWarnings("hiding")
    public KonfliktAnzeige(int art, List<String> alternativen, int losgewinner) {
      this.art = art;
      this.alternativen = alternativen;
      this.losgewinner = losgewinner;
    }

    /**
     * Gibt art zur�ck.
     * 
     * @return art.
     */
    public int getArt() {
      return art;
    }

    /**
     * Gibt alternativen zur�ck.
     * 
     * @return alternativen.
     */
    public List<String> getAlternativen() {
      return alternativen;
    }

    /**
     * Gibt losgewinner zur�ck.
     * 
     * @return losgewinner.
     */
    public int getLosgewinner() {
      return losgewinner;
    }

  }

  /**
   * @param besonderheitenTexte
   */
  public void addBesonderheiten(List<String> besonderheitenTexte) {
    _besonderheiten.addAll(besonderheitenTexte);
  }

  /**
   * @param besonderheitenTexte
   */
  public List<String> getBesonderheiten() {
    return Collections.unmodifiableList(_besonderheiten);
  }

}
