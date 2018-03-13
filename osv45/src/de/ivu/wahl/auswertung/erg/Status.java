package de.ivu.wahl.auswertung.erg;

/**
 * Ergebnisobjekt zur Statusanzeige
 *
 * @author mur@ivu.de
 */
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.modell.WahlModel;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class Status extends Ergebnis {
  private static final long serialVersionUID = -4551794791659733170L;

  /**
   * Klartextbeschreibung der Wahl, nur zu Präsentationszwecken (z.B. Bundestagswahl 1998)
   */
  protected final String _wahl;

  /**
   * Status des WahlABWICKLUNGSSYSTEMS, nur zu Präsentationszwecken mit Benutzung von
   * Konstanten.APPSTATE_KLARTEXT[i]
   */
  protected final int _statusWAS;

  /**
   * Art der Wahl, kein default nur zu Präsentationszwecken mit Benutzung von
   * WahlModel..WAHLART_KLARTEXT[i]
   */
  protected final ElectionCategory _electionCategory;

  /**
   * Aktuelle Wahlergebnisart nur zu Präsentationszwecken mit Benutzung von
   * WahlModel.WAHLERGEBNISART_KLARTEXT[i]
   */
  protected final int _wahlergebnisart;

  /**
   * Ist die Aktuelle Wahl geschlossen?
   */
  protected final boolean _geschlossen;

  /**
   * Ist die aktuelle Wahl freigegeben?
   */
  protected final boolean _freigegeben;

  public Status(String ergBezeichnung,
      String wahl,
      int statusWAS,
      ElectionCategory electionCategory,
      int wahlergebnisart,
      boolean geschlossen,
      boolean freigegeben) {
    super(ergBezeichnung);
    _wahl = wahl;
    _statusWAS = statusWAS;
    _electionCategory = electionCategory;
    _wahlergebnisart = wahlergebnisart;
    _geschlossen = geschlossen;
    _freigegeben = freigegeben;
  }

  public String getWahlbezeichnung() {
    return _wahl;
  }

  public String getArt() {
    return WahlModel.WAHLART_KLARTEXT.get(_electionCategory);
  }

  public String getStatusWAS() {
    return Konstanten.APPSTATE_KLARTEXT[_statusWAS];
  }

  /**
   * @return <code>true</code> wenn die Wahl geschlossen ist
   */
  public boolean getGeschlossen() {
    return _geschlossen;
  }

  /**
   * @return <code>true</code> wenn die Wahl freigegeben ist
   */
  public boolean getFreigegeben() {
    return _freigegeben;
  }

  public boolean isStatusTest() {
    return Konstanten.APPSTATE_TEST == _statusWAS;
  }
}