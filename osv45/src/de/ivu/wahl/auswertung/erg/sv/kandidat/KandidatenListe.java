package de.ivu.wahl.auswertung.erg.sv.kandidat;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import de.ivu.wahl.auswertung.erg.Ergebnis;
import de.ivu.wahl.modell.GebietModel;

/**
 * Objekt mit Informationen für Kandidaten, welche sowohl Direktkandidaten als auch Listenkandidaten
 * sein können.
 * 
 * @author apa@ivu.de
 * @autor tst
 */
public class KandidatenListe extends Ergebnis {
  private static final long serialVersionUID = 3779748198073105633L;

  private final List<KandidatInfo> _kandidaten;
  private List<GebietModel> _gebiete;
  private List<String> _gruppen;

  /**
   * @param ergBezeichnung Ergebnisbezeichnung Klartext
   * @param kandidaten Liste der Kandidaten
   */
  public KandidatenListe(String ergBezeichnung, List<KandidatInfo> kandidaten) {
    super(ergBezeichnung);
    _kandidaten = kandidaten;
  }

  /**
   * @return Liste der Kandidaten
   */
  public List<KandidatInfo> getKandidaten() {
    return _kandidaten;
  }

  /**
   * @return Anzahl der Gebiete im ergebnis
   */
  public int getAnzahlGebiete() {
    return getGebiete().size();
  }

  /**
   * @return die Liste von GebietModel der Gebiet der Kandidaten
   */
  public List<GebietModel> getGebiete() {
    if (_gebiete == null) {
      Set<GebietModel> gebieteSet = new LinkedHashSet<GebietModel>();
      for (KandidatInfo kandidat : getKandidaten()) {
        gebieteSet.add(kandidat.getGebietModel());
      }
      _gebiete = new ArrayList<GebietModel>(gebieteSet);
    }
    return _gebiete;
  }

  /**
   * @return Anzahl der Gruppen in der Kandidatenliste
   */
  public int getAnzahlGruppen() {
    return getGruppen().size();
  }

  /**
   * @return die Liste der Kurznamen der Gruppen innerhalb der Kandidatenliste
   */
  public List<String> getGruppen() {
    if (_gruppen == null) {
      Set<String> gruppenSet = new LinkedHashSet<String>();
      for (KandidatInfo kandidat : getKandidaten()) {
        gruppenSet.add(kandidat.getGruppennameKurz());
      }
      _gruppen = new ArrayList<String>(gruppenSet);
    }
    return _gruppen;
  }

}