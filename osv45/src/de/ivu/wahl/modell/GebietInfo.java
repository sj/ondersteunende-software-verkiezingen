package de.ivu.wahl.modell;

import java.sql.Timestamp;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.auswertung.erg.Ergebnis;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.Gebietsstatus;
import de.ivu.wahl.modell.ejb.Wahl;

/**
 * Zusammenfassung mit Gebietsinformationen f�r die Clientseite.
 * 
 * @author mur@ivu.de Copyright (c) 2004 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public class GebietInfo extends Ergebnis {
  private static final long serialVersionUID = -7552482748127097429L;

  private final static Category LOGGER = Log4J.configure(GebietInfo.class);
  static {
    LOGGER.info(Log4J.dumpVersion(GebietInfo.class, Log4J.extractVersion("$Revision: 1.3 $"))); //$NON-NLS-1$
  }

  private final GebietModel _gebietModel;

  private ErgebniseingangModel _letzterGueltigerEingang;

  private ErgebniseingangModel _letzterEingang;

  private final boolean _isVollstaendig;

  private final boolean _isWahleinheit;

  private final boolean _isWahlgebiet;

  private final boolean _isErfassungseinheit;

  private final boolean _isAuswertungseinheit;

  private final boolean _isAuswertungseinheitOderTiefer;

  private final int _korrekturnummer;

  private int _anzahlGesamt = 0;

  private int _anzahlErwartet = 0;

  private int _anzahlEingegangen = 0;

  private DefaultMutableTreeNode _node = null;

  private String _nodePath;

  private String _nachwahlText;

  private boolean _hatKinder = false;

  public GebietInfo(Gebiet gebiet) {

    super(GebietInfo.class.getSimpleName());
    _gebietModel = gebiet.getDetails();
    _hatKinder = gebiet.getGebietErwartetCol() != null && gebiet.getGebietErwartetCol().size() > 0;

    Wahl wahl = gebiet.getWahl();
    if (wahl.getID_Wahlgebiet() != null) {
      _isWahlgebiet = wahl.getID_Wahlgebiet().equals(_gebietModel.getID_Gebiet());
    } else {
      _isWahlgebiet = false;
    }
    int gebietsartAuswertungseinheit = wahl.getGebietsartAuswertungseinheit();
    int gebietsart = _gebietModel.getGebietsart();
    _isAuswertungseinheit = gebietsart == gebietsartAuswertungseinheit;
    _isAuswertungseinheitOderTiefer = gebietsart >= gebietsartAuswertungseinheit;

    _isErfassungseinheit = _gebietModel.isErfassungseinheit();
    _isWahleinheit = _gebietModel.isWahleinheit();

    if (_isErfassungseinheit) {
      _anzahlGesamt = 1;
    }

    Gebietsstatus currentGebietsstatus = gebiet.getCurrentGebietsstatus(wahl
        .getAktuelleWahlergebnisart());
    if (currentGebietsstatus != null) {
      _korrekturnummer = currentGebietsstatus.getKorrekturnummer();
      _isVollstaendig = currentGebietsstatus.isVollstaendig();
      if (_isVollstaendig && _isErfassungseinheit) {
        _anzahlEingegangen = 1;
      }
    } else {
      // noch gar kein Ergebniseingang
      _korrekturnummer = 0;
      _isVollstaendig = false;
    }

    _letzterGueltigerEingang = gebiet.getLetzterGueltigerEingang(wahl.getAktuelleWahlergebnisart());
    if (_letzterGueltigerEingang != null) {
      _letzterGueltigerEingang = _letzterGueltigerEingang.copy();
    }
    if (_isErfassungseinheit) {
      _letzterEingang = gebiet.getLastInput(wahl.getAktuelleWahlergebnisart());
      if (_letzterEingang != null) {
        _letzterEingang = _letzterEingang.copy();
      }
    } else {
      _letzterEingang = _letzterGueltigerEingang;
    }

  }

  public boolean hatLetztenGueltigenEingang() {
    return _letzterGueltigerEingang != null;
  }

  public boolean hatLetztenEingang() {
    return _letzterEingang != null;
  }

  public int getStatusLetzterEingang() {
    return _letzterEingang == null ? ErgebniseingangKonstanten.STATE_NO_INPUT : _letzterEingang
        .getStatus();
  }

  public Timestamp getZeitLetzterGueltigerEingang() {
    return _letzterGueltigerEingang == null ? null : _letzterGueltigerEingang.getZeitstempel();
  }

  public Timestamp getZeitLetzterEingang() {
    return _letzterEingang == null ? null : _letzterEingang.getZeitstempel();
  }

  /**
   * @return Anzahl der eingegangenden Untergebiete
   */
  public int getAnzahlEingegangen() {
    return _anzahlEingegangen;
  }

  /**
   * @return Anzahl der erwarteten Untergebiete
   */
  public int getAnzahlErwartet() {
    return _anzahlErwartet;
  }

  /**
   * @return Anzahl der Untergebiete
   */
  public int getAnzahlGesamt() {
    return _anzahlGesamt;
  }

  /**
   * @return Gebietsart
   */
  public int getGebietsart() {
    return _gebietModel.getGebietsart();
  }

  /**
   * @return Gebietsart
   */
  public String getGebietsartKlartext() {
    return Gebietsart.getGebietsartKlartext(_gebietModel);
  }

  /**
   * @return ID des Gebiets
   */
  public String getID_Gebiet() {
    return _gebietModel.getID_Gebiet();
  }

  /**
   * @return ID des Gebiets
   */
  public String getIdGebiet() {
    return _gebietModel.getID_Gebiet();
  }

  /**
   * @return ID des letzten gueltigen Eingangs
   */
  public String getID_LetzterGueltigerEingang() {
    return _letzterGueltigerEingang == null ? null : _letzterGueltigerEingang
        .getID_Ergebniseingang();
  }

  /**
   * @return ID des letzten gueltigen Eingangs
   */
  public String getID_LetzterEingang() {
    return _letzterEingang == null ? null : _letzterEingang.getID_Ergebniseingang();
  }

  /**
   * @return ID des uebergeordneten Gebiets
   */
  public String getID_UebergeordnetesGebiet() {
    return _gebietModel.getID_UebergeordnetesGebiet();
  }

  /**
   * @return id der Wahl
   */
  public String getID_Wahl() {
    return _gebietModel.getID_Wahl();
  }

  /**
   * @return Kuerzel
   */
  public String getKuerzel() {
    return _gebietModel.getKuerzel();
  }

  /**
   * @return Name
   */
  public String getName() {
    return _gebietModel.getName();
  }

  public String getNodeBezeichnung() {
    String retValue = getName();
    if (isErfassungseinheit() || isWahleinheit()) {
      retValue = "(" + getNumber4Display() + ") " + retValue; //$NON-NLS-1$ //$NON-NLS-2$
    }
    return retValue;
  }

  /**
   * @return Nummer
   */
  public int getNummer() {
    return _gebietModel.getNummer();
  }

  /**
   * @return Position
   */
  public int getPosition() {
    return _gebietModel.getPosition();
  }

  /**
   * @return vollstaendigkeit des Gebietes
   */
  public boolean isVollstaendig() {
    return _isVollstaendig;
  }

  /**
   * @return Korrekturnummer des Gebietergebnisses
   */
  public int getKorrekturnummer() {
    return _korrekturnummer;
  }

  /**
   * @return linearisierter Pfad zum Knoten
   */
  public String getNodePath() {
    return _nodePath;
  }

  /**
   * @param nodePath linearisierter Pfad zum Knoten
   */
  public void setNodePath(String nodePath) {
    LOGGER.debug("setNodePath " + getNummer() + " " + nodePath); //$NON-NLS-1$ //$NON-NLS-2$
    _nodePath = nodePath;
  }

  /**
   * @return Returns the isAuswertungseinheit.
   */
  public boolean isAuswertungseinheit() {
    return _isAuswertungseinheit;
  }

  /**
   * @return Returns the isAuswertungseinheitOderTiefer.
   */
  public boolean isAuswertungseinheitOderTiefer() {
    return _isAuswertungseinheitOderTiefer;
  }

  /**
   * @return <code>true</code> wenn es sich um eine Wahleinheit handelt
   */
  public boolean isWahleinheit() {
    return _isWahleinheit;
  }

  @Override
  public String toString() {
    StringBuilder stringBuffer = new StringBuilder("GebietInfo ["); //$NON-NLS-1$
    stringBuffer.append("Name=").append(getName()); //$NON-NLS-1$
    stringBuffer.append(" - Nummer=").append(getNummer()); //$NON-NLS-1$
    stringBuffer.append(" - StatusLetzerEingang=").append(getStatusLetzterEingang()); //$NON-NLS-1$
    stringBuffer.append(" - AnzahlEingegangen=").append(getAnzahlEingegangen()); //$NON-NLS-1$
    stringBuffer.append(" - AnzahlErwartet=").append(getAnzahlErwartet()); //$NON-NLS-1$
    stringBuffer.append(" - AnzahlGesamt=").append(getAnzahlGesamt()); //$NON-NLS-1$
    stringBuffer.append(" - Gebietsart=").append(getGebietsart()); //$NON-NLS-1$
    stringBuffer.append("] "); //$NON-NLS-1$

    stringBuffer.append(super.toString());

    return stringBuffer.toString();
  }

  /**
   * Setzt die Anzahl der eingegangenen Auswertungseinheiten (nicht f�r Sondergebiete, um
   * Doppelz�hlungen zu vermeiden!)
   * 
   * @param anzahlEingegangen Anzahl der eingegangenen Auswertungseinheiten
   */
  public void setAnzahlEingegangen(int anzahlEingegangen) {
    _anzahlEingegangen = anzahlEingegangen;
  }

  /**
   * Setzt die Anzahl der erwarteten Auswertungseinheiten
   * 
   * @param anzahlErwartet Anzahl der erwarteten Auswertungseinheiten
   */
  public void setAnzahlErwartet(int anzahlErwartet) {
    _anzahlErwartet = anzahlErwartet;
  }

  /**
   * Setzt die Gesamtanzahl der Auswertungseinheiten
   * 
   * @param anzahlGesamt Gesamtanzahl der Auswertungseinheiten
   */
  public void setAnzahlGesamt(int anzahlGesamt) {
    _anzahlGesamt = anzahlGesamt;
  }

  /**
   * @return <code>true</code> wenn es sich um eine Erfassungseineheit handelt
   */
  public boolean isErfassungseinheit() {
    return _isErfassungseinheit;
  }

  /**
   * @return <code>true</code> wenn es sich um ein Wahlgebiet handelt
   */
  public boolean isWahlgebiet() {
    return _isWahlgebiet;
  }

  /**
   * @return Knoten im Gebietsbaum, an dem dieses Gebiet h�ngt
   */
  public DefaultMutableTreeNode getNode() {
    return _node;
  }

  /**
   * @param node Knoten im Gebietsbaum, an dem dieses Gebiet h�ngt
   */
  public void setNode(DefaultMutableTreeNode node) {
    _node = node;
  }

  /**
   * @return Beschreibung der Nachwahleinstellung (menschenlesbar)
   */
  public String getNachwahlText() {
    return _nachwahlText;
  }

  public boolean isGuiEingabeErlaubt() {
    return _gebietModel.isGUIEingabeErlaubt();
  }

  /**
   * @return region number, for gemeente as 4-digit number
   */
  public String getNumber4Display() {
    return _gebietModel.getNumber4Display();
  }

  /**
   * @return region number, for gemeente as 4-digit number
   */
  public String getCompleteDisplay(String separator) {
    if (WahlInfo.getWahlInfo().isEK() && getGebietsart() == GebietModel.GEBIETSART_STIMMBEZIRK) {
      return Gebietsart.LAND.getKlartext() + " " + getName(); //$NON-NLS-1$
    } else {
      return getGebietsartKlartext() + " " + getNumber4Display() + separator + getName(); //$NON-NLS-1$
    }
  }

  /**
   * @return <code>true</code> wenn das Gebiet Untergebiete (Kinder) hat
   */
  public boolean hasChildren() {
    return _hatKinder;
  }
}