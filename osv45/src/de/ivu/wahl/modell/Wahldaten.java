/*
 * Created on 07.01.2009
 * @author ugo
 */
package de.ivu.wahl.modell;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.ivu.wahl.dataimport.ImportClient;
import de.ivu.wahl.dataimport.ImportElectionMetadata;
import de.ivu.wahl.modell.GruppeComposite.Liste;
import de.ivu.wahl.modell.GruppeComposite.Listenkandidat;
import de.ivu.wahl.modell.exception.ImportException;

/**
 * Holding metadata and voting results as needed by the algorithm module
 * 
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public class Wahldaten implements Serializable {
  private static final long serialVersionUID = -8839827636033764703L;

  private static Wahldaten __wahldaten;
  private WahlModel _wahl;
  private List<GebietModel> _gebiete;
  private Collection<GruppeComposite> _gruppen;

  // Ergebniseingang the voting results in this Wahldaten object belong to
  private String _id_Ergebniseingang;

  /**
   * Empty Wahldaten for database initialization
   * 
   * @return
   * @throws ImportException
   */
  public static Wahldaten get_wahldaten() throws ImportException {
    if (Wahldaten.__wahldaten == null) {
      __wahldaten = new Wahldaten();
    }
    return __wahldaten;
  }

  /**
   * Initialize Wahldaten from EML-messages, used for the algorithm module's test
   * 
   * @param electionDefinition election definition
   * @param eml230c candidate lists
   * @param eml510d voting results
   * @return Wahldaten object filled with metadata and voting results
   * @throws ImportException
   */
  public static Wahldaten getWahldaten(URL electionDefinition, URL eml230, URL eml510)
      throws ImportException {
    __wahldaten = new Wahldaten();
    __wahldaten.init(electionDefinition, eml230, eml510);
    return __wahldaten;
  }

  /**
   * Initialize Wahldaten with data from EML-Files
   * 
   * @param electionDefinition
   * @param eml230
   * @param eml510
   * @throws ImportException
   */
  private void init(URL electionDefinition, URL eml230, URL eml510) throws ImportException {
    ImportElectionMetadata importElectionMetadata = new ImportElectionMetadata(
        ImportElectionMetadata.MODE_STANDALONE, GebietModel.EBENE_CSB);
    importElectionMetadata.setDefinition(electionDefinition);
    importElectionMetadata.setEML230(eml230);
    importElectionMetadata.setEML510(eml510);
    importElectionMetadata.updateStatus();
    new ImportClient(importElectionMetadata).run(this);
  }

  /**
   * Gibt _id_Ergebniseingang zurück.
   * 
   * @return _id_Ergebniseingang.
   */
  public String getId_Ergebniseingang() {
    return _id_Ergebniseingang;
  }

  /**
   * Setzt den neuen Wert für _id_Ergebniseingang auf _id_Ergebniseingang.
   * 
   * @param _id_Ergebniseingang neuer Wert für _id_Ergebniseingang
   */
  public void setId_Ergebniseingang(String id_Ergebniseingang) {
    this._id_Ergebniseingang = id_Ergebniseingang;
  }

  /**
   * @return the _wahl
   */
  public WahlModel getWahl() {
    return this._wahl;
  }

  /**
   * @param _wahl the _wahl to set
   */
  public void setWahl(WahlModel wahl) {
    this._wahl = wahl;
  }

  /**
   * @return the _gebiete
   */
  public List<GebietModel> getGebiete() {
    return this._gebiete;
  }

  /**
   * @param _gebiete the _gebiete to set
   */
  public void setGebiete(List<GebietModel> wahleinheiten) {
    this._gebiete = wahleinheiten;
  }

  /**
   * @param _gebiete the _gebiete to set
   */
  public void addGebiet(GebietModel wahleinheit) {
    if (this._gebiete == null) {
      this._gebiete = new ArrayList<GebietModel>();
    }
    this._gebiete.add(wahleinheit);
  }

  /**
   * @return the gruppen
   */
  public Collection<GruppeComposite> getGruppen() {
    return this._gruppen;
  }

  /**
   * @param gruppen the gruppen to set
   */
  public void setGruppen(Collection<GruppeComposite> gruppen) {
    this._gruppen = gruppen;
  }

  /**
   * @param _gebiete the _gebiete to set
   */
  public void addGruppe(GruppeComposite gruppe) {
    if (this._gruppen == null) {
      this._gruppen = new ArrayList<GruppeComposite>();
    }
    this._gruppen.add(gruppe);
  }

  /**
   * Mark the given candidate as not nominateable
   */
  public void addDeadCandidate(int listenNumber, String districtName, int listPosition) {
    Collection<GruppeComposite> gruppen = getGruppen();
    for (GruppeComposite gruppeComposite : gruppen) {
      if (listenNumber == gruppeComposite.getGruppe().getSchluessel()) {
        for (Liste liste : gruppeComposite.getListen()) {
          if (districtName.equals(liste.getGebiet().getBezeichnung())) {
            Listenkandidat listenkandidat = liste.getKandidaten().get(listPosition - 1);
            listenkandidat.getPerson().setBenennbar(false);
            return;
          }
        }
      }
    }
    throw new IllegalArgumentException("Unable to find candidate mit listNumber = " + listenNumber //$NON-NLS-1$
        + ", districtName = " + districtName + ", listPosition = " + listPosition); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * @return true, if there is an region which is a briefstembureau.
   */
  public boolean isPostalVoteOfficeExists() {
    if (getGebiete() != null) {
      for (GebietModel gebiet : getGebiete()) {
        if (gebiet.isPostalvote()) {
          return true;
        }
      }
    }
    return false;
  }
}
