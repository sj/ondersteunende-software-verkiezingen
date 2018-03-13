/*
 * Created on 17.12.2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.ivu.wahl.auswertung.erg.sv.kandidat;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.GruppeModel;
import de.ivu.wahl.modell.ListeModel;
import de.ivu.wahl.modell.ListenkandidaturErgebnisModel;
import de.ivu.wahl.modell.PersonendatenKonstanten;
import de.ivu.wahl.modell.PersonendatenKonstanten.Geschlecht;
import de.ivu.wahl.modell.PersonendatenModel;

/**
 * Information about a candidate. Immutable.
 * 
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public class KandidatInfo implements Serializable {
  public final static int KEIN_SATZ = -1;

  private static final long serialVersionUID = 3752443305297710657L;

  private final PersonendatenModel _personendatenModel;

  private final ListenkandidaturErgebnisModel _listenkandidaturModel;
  private final GebietModel _gebietModel;
  private final GruppeModel _gruppeModel;

  private final int _stimmenAnzahl;

  private final boolean _isGeschlechtSichtbar;
  private final String _publicationLanguage;
  private final int _satz;
  private final int _urspruenglicherListenplatz;

  /**
   * @param personendatenModel Wertobjekt der Personendaten des Kandidaten
   * @param listenkandidaturModel
   * @param gebietModel Wertobjekt des Gebiets auf dem der Kandidat aufgestellt ist
   * @param gruppeModel
   * @param stimmen
   * @param urspruenglicherListenplatz
   * @param geschlecht Geschlecht des Kandidaten
   */
  public KandidatInfo(PersonendatenModel personendatenModel,
      ListenkandidaturErgebnisModel listenkandidaturModel,
      GebietModel gebietModel,
      GruppeModel gruppeModel,
      int stimmen,
      ListeModel listeModel,
      int urspruenglicherListenplatz) {

    _personendatenModel = personendatenModel;
    _listenkandidaturModel = listenkandidaturModel;
    _gebietModel = gebietModel;
    _gruppeModel = gruppeModel;
    _stimmenAnzahl = stimmen;
    _isGeschlechtSichtbar = listeModel.isGeschlechtSichtbar();
    _publicationLanguage = listeModel.getPublicationLanguage();
    _satz = listeModel.getSatz() > 0 ? listeModel.getSatz() : KEIN_SATZ;
    _urspruenglicherListenplatz = urspruenglicherListenplatz;
  }

  public String getID_Gebiet() {
    return _gebietModel.getID_Gebiet();
  }

  public String getID_Gruppe() {
    return _gruppeModel.getID_Gruppe();
  }

  public String getGruppennameLang() {
    return _gruppeModel.getNameLang();
  }

  public String getGruppennameKurz() {
    return _gruppeModel.getNameKurz();
  }

  /**
   * @return suffix to the candidate name describing his/her gender, like " (m)" or " (v)" or " (f)"
   *         or an empty String.
   */
  public String displayGeschlecht() {
    Geschlecht geschlecht = PersonendatenKonstanten.Geschlecht.forDisplay(getGeschlecht(),
        _publicationLanguage);
    if (geschlecht != null && _isGeschlechtSichtbar) {
      return " (" + geschlecht.name + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    } else {
      return StringUtils.EMPTY;
    }
  }

  private int getGeschlecht() {
    return _personendatenModel != null
        ? _personendatenModel.getGeschlecht()
        : Geschlecht.KEINE_ANGABE.id;
  }

  public String getID_Listenkandidatur() {
    return _listenkandidaturModel == null ? null : _listenkandidaturModel.getID_Listenkandidatur();
  }

  public String getID_Personendaten() {
    return _personendatenModel == null ? null : _personendatenModel.getID_Personendaten();
  }

  public String getNachname() {
    return _personendatenModel == null ? "" : _personendatenModel.getNachname(); //$NON-NLS-1$
  }

  public String getPraefix() {
    return _personendatenModel != null && _personendatenModel.getPraefix() != null
        ? _personendatenModel.getPraefix()
        : ""; //$NON-NLS-1$
  }

  /**
   * @return Name des Kandidaten, formatiert f�r die GUI
   */
  public String getNameAnzeige() {
    if (_personendatenModel == null) {
      return ""; //$NON-NLS-1$
    }
    String name = _personendatenModel.getTitel();
    name += name == null || name.length() == 0 ? "" : " "; //$NON-NLS-1$ //$NON-NLS-2$
    name += _personendatenModel.getVorname() + " "; //$NON-NLS-1$
    name += _personendatenModel.getNachname();
    return name;
  }

  public String getInitialen() {
    return _personendatenModel.getInitialen();
  }

  public String getKontaktPlz() {
    return _personendatenModel == null ? StringUtils.EMPTY : _personendatenModel.getKontakt_PLZ();
  }

  public String getKontaktStrasse() {
    return _personendatenModel == null ? StringUtils.EMPTY : _personendatenModel
        .getKontakt_Strasse();
  }

  public String getKontaktWohnort() {
    return _personendatenModel != null && _personendatenModel.getKontakt_Wohnort() != null
        ? _personendatenModel.getKontakt_Wohnort()
        : "-"; //$NON-NLS-1$
  }

  public String getKontaktLand() {
    return _personendatenModel != null && _personendatenModel.getKontakt_Land() != null ? "(" //$NON-NLS-1$
        + _personendatenModel.getKontakt_Land() + ")" : StringUtils.EMPTY; //$NON-NLS-1$ 
  }

  public String getTitel() {
    return _personendatenModel == null ? StringUtils.EMPTY : _personendatenModel.getTitel();
  }

  public String getVorname() {
    return _personendatenModel == null ? StringUtils.EMPTY : _personendatenModel.getVorname();
  }

  public String getWohnort() {
    return _personendatenModel != null && _personendatenModel.getWohnort() != null
        ? _personendatenModel.getWohnort()
        : "-"; //$NON-NLS-1$
  }

  public String getLand() {
    return _personendatenModel != null && _personendatenModel.getLand() != null ? "(" //$NON-NLS-1$
        + _personendatenModel.getLand() + ")" : StringUtils.EMPTY; //$NON-NLS-1$ 
  }

  public int getGebietsnummer() {
    return _gebietModel.getNummer();
  }

  public int getGebietsart() {
    return _gebietModel.getGebietsart();
  }

  public int getGebietsPosition() {
    return _gebietModel.getPosition();
  }

  public GebietModel getGebietModel() {
    return _gebietModel;
  }

  public PersonendatenModel getPersonendatenModel() {
    return _personendatenModel;
  }

  public int getGruppenSchluessel() {
    return _gruppeModel.getSchluessel();
  }

  public int getListenplatz() {
    return _listenkandidaturModel.getListenplatz();
  }

  public int getUrspruenglicherListenplatz() {
    return _urspruenglicherListenplatz;
  }

  /**
   * @return number of votes for the candidate in the whole P3-list (i.e. Gruppe)
   */
  public int getStimmenAnzahl() {
    return _stimmenAnzahl;
  }

  /**
   * Gibt bevorzugtGewaehlt zur�ck.
   * 
   * @return bevorzugtGewaehlt.
   */
  public boolean isBevorzugtGewaehlt() {
    return _listenkandidaturModel.isBevorzugtGewaehlt();
  }

  /**
   * Was elected by loting.
   * 
   * @return bevorzugtGewaehlt.
   */
  public boolean isLosgewinner() {
    return _listenkandidaturModel.isLosgewinner();
  }

  /**
   * @return the id of the set of identical lists on which the candidate was elected. If he / she
   *         was not elected on a set of identical lists, return KEIN_SATZ.
   */
  public int getSatz() {
    return _satz;
  }

  public String getSatzOrRegionNumber4Display() {
    if (_satz == KEIN_SATZ) {
      return "Kieskring " + _gebietModel.getNummer(); //$NON-NLS-1$
    } else {
      return "Stel " + _satz; //$NON-NLS-1$
    }
  }

  /**
   * @return Utility for
   */
  public int getSortKeyForSatzOrRegion() {
    if (_satz == KEIN_SATZ) {
      return _gebietModel.getNummer();
    } else {
      return _satz - 100;
    }
  }
}
