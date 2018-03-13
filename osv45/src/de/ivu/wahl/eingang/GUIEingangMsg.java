/*
 * Copyright (c) 2002-10 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.eingang;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import de.ivu.wahl.AnwContext;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.client.util.ClientHelper;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.BasicEingangMsg;
import de.ivu.wahl.modell.ErgebniseingangKonstanten;
import de.ivu.wahl.modell.ErgebniseingangModel;
import de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein;
import de.ivu.wahl.modell.ejb.Gebiet;

/**
 * Data object containing GUI input data and corresponding metadata
 * 
 * @author cos@ivu.de klie@ivu.de - IVU Traffic Technologies AG
 */

public class GUIEingangMsg extends BasicEingangMsg implements EingangMsg, Serializable {
  private static final long serialVersionUID = -6889854223628510687L;

  protected String _infoText = null;
  protected String _confirmationText = null;
  protected final Map<Integer, Gruppendaten> _gruppendaten;
  protected Gebiet _erfassungseinheit;
  protected ErgebniseingangModel _ergebniseingang = null;
  protected ErgebniseingangModel _letzterGueltigerErgebniseingangModel = null;
  protected Boolean _forDisplay = null;

  /**
   * Serialisierungskennung nur für GUI wirklich interessant
   */
  private int _serNr = -1;

  /**
   * @author cos@ivu.de, IVU Traffic Technologies AG
   */
  public static class Gruppendaten implements Serializable {
    private static final long serialVersionUID = -8996828539690488150L;

    private int _position;
    private String _name;
    private String _kurzname;
    private String _farbe;
    private String _helptext;
    private String _kategorie;
    private String _unterkategorie;
    private boolean _isRadioButtons;
    private boolean _isCollapsible;
    private String _buchstabe;
    private boolean _smallFontSize;
    private boolean _smallGapAfterwards;
    private boolean _visible;
    private boolean _visibleInOverview;
    private double _stimmenprozent = -1;

    Map<Integer, Kandidat> _kandidaten = new TreeMap<Integer, Kandidat>();

    public int getPosition() {
      return _position;
    }

    public void setPosition(int position) {
      _position = position;
    }

    public String getName() {
      return _name;
    }

    public void setName(String name) {
      _name = ClientHelper.forHTML(name);
    }

    public String getKurzname() {
      return _kurzname;
    }

    public void setKurzname(String kurzname) {
      _kurzname = kurzname;
    }

    public String getFarbe() {
      return _farbe;
    }

    public void setFarbe(String farbe) {
      _farbe = farbe;
    }

    public String getHelptext() {
      return _helptext;
    }

    public void setHelptext(String helptext) {
      _helptext = helptext;
    }

    public String getKategorie() {
      return _kategorie;
    }

    public void setKategorie(String kategorie) {
      _kategorie = kategorie;
    }

    public String getUnterkategorie() {
      return _unterkategorie;
    }

    public void setUnterkategorie(String unterkategorie) {
      _unterkategorie = unterkategorie;
    }

    public boolean isRadioButtons() {
      return _isRadioButtons;
    }

    public void setRadioButtons(boolean isRadioButtons) {
      _isRadioButtons = isRadioButtons;
    }

    public boolean isCollapsible() {
      return _isCollapsible;
    }

    public void setCollapsible(boolean isCollapsible) {
      _isCollapsible = isCollapsible;
    }

    public String getBuchstabe() {
      return _buchstabe;
    }

    public void setBuchstabe(String buchstabe) {
      _buchstabe = buchstabe;
    }

    public boolean isSmallFontSize() {
      return _smallFontSize;
    }

    public void setSmallFontSize(boolean smallFontSize) {
      _smallFontSize = smallFontSize;
    }

    public boolean isSmallGapAfterwards() {
      return _smallGapAfterwards;
    }

    public void setSmallGapAfterwards(boolean smallGapAfterwards) {
      _smallGapAfterwards = smallGapAfterwards;
    }

    public boolean isVisible() {
      return _visible;
    }

    public void setVisible(boolean visible) {
      _visible = visible;
    }

    public boolean isVisibleInOverview() {
      return _visibleInOverview;
    }

    public void setVisibleInOverview(boolean visibleInOverview) {
      _visibleInOverview = visibleInOverview;
    }

    public Map<Integer, Kandidat> getKandidaten() {
      return _kandidaten;
    }

    public void addKandidat(int listenplatz, String name, String referenName) {
      _kandidaten.put(listenplatz, new Kandidat(name, listenplatz, referenName));
    }

    public Kandidat getKandidat(int listenplatz) {
      return _kandidaten.get(listenplatz);
    }

    public double getStimmenprozent() {
      return _stimmenprozent;
    }

    public void setStimmenprozent(double stimmenprozent) {
      _stimmenprozent = stimmenprozent;
    }

    public int getKandidatenanzahl() {
      return _kandidaten.size();
    }

  }

  public static class Kandidat implements Serializable {
    /** long */
    private static final long serialVersionUID = 1652698930661892162L;
    final String _name;
    int _listenposition;
    final String _referendumName;

    /**
     * Constructor
     * 
     * @param name
     * @param listenposition
     * @param referendumName if the election type is a referendum, the candidate is used as an
     *          referendum answer. The referendumName represents the referendum answer written in
     *          the repository-table
     */
    Kandidat(String name, int listenposition, String referendumName) {
      _name = name;
      _listenposition = listenposition;
      _referendumName = referendumName;
    }

    public int getListenposition() {
      return _listenposition;
    }

    public String getName() {
      return _name;
    }

    public String getReferendumName() {
      return _referendumName;
    }

    public String getReferendumNameCutOff() {
      return ClientHelper.resizeString(_referendumName, 100);
    }
  }

  /**
   * Spezieller Comparator, der dafür sorgt, dass die Referendum-Optionen nicht zum Schluss kommen,
   * sondern an der Stelle der GUELTIGE eingefügt werden.
   */
  static final class ReferendumGruppenComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer x, Integer y) {
      int result = getBlock(x) - getBlock(y);
      if (result != 0) {
        return result;
      } else {
        return x - y;
      }
    }

    /**
     * Display the special groups upto (excluding) GUELTIGE, then the normal groups, then the other
     * special groups
     */
    private int getBlock(Integer x) {
      int positionGueltige = GruppeAllgemein.GUELTIGE.getPosition();
      if (x < positionGueltige) {
        return 0;
      } else if (x < 0) {
        return 2;
      } else {
        return 1;
      }
    }
  }

  public GUIEingangMsg(AnwContext ersteller) {
    super(ersteller);
    // da der Constructor nur auf der Server-Seite ausgef�hrt wird...
    WahlInfo wahlInfo = WahlInfo.getWahlInfo(ersteller);

    Comparator<Integer> referendumGruppenComparator = new ReferendumGruppenComparator();
    _gruppendaten = wahlInfo.isReferendum() ? new TreeMap<Integer, Gruppendaten>(
        referendumGruppenComparator) : new TreeMap<Integer, Gruppendaten>();

    _wahlkurzname = wahlInfo.getWahlNameKurz();
    _wahlergebnisart = wahlInfo.getAktuelleWahlergebnisart();

    setSource(ErgebniseingangKonstanten.SOURCE_GUI_1);
    setInputMode(MODE_CHECK_WARNINGS);
  }

  public void setErgebniseingangModel(ErgebniseingangModel model) {
    _ergebniseingang = model;
  }

  public ErgebniseingangModel getErgebniseingangModel() {
    return _ergebniseingang;
  }

  /**
   * Setzen des letzten g�ltigen Ergebniseingangs (der mit dem gerechnet wird)
   * 
   * @param model Modellobjekt des letzten g�ltigen Eingangs
   */
  public void setLetzterGueltigerErgebniseingangModel(ErgebniseingangModel model) {
    _letzterGueltigerErgebniseingangModel = model;
  }

  /**
   * @return Wertobjekt des letzten g�ltigen Ergebniseingangs auf diesem Gebiet
   */
  public ErgebniseingangModel getLetzterGueltigerErgebniseingangModel() {
    return _letzterGueltigerErgebniseingangModel;
  }

  public void setGebietsartErfassungsgebiet(int param) {
    _gebietsartErfassungseinheit = param;
  }

  public void setNummerErfassungsgebiet(int param) {
    _nummerErfassungseinheit = param;
  }

  @Override
  public void setInputMode(int param) {
    _inputMode = param;
  }

  @Override
  public String getMsgName() {
    return Messages.getString(MessageKeys.Msg_EingabeDerErfassungseinheit)
        + _nummerErfassungseinheit;
  }

  @Override
  public int getSerialisierungsnummer() {
    return _serNr;
  }

  public void setSerialisierungsnummer(int nr) {
    _serNr = nr;
  }

  public Map<Integer, Gruppendaten> getGruppendaten() {
    return _gruppendaten;
  }

  public Set<Integer> getGruppendatenKey() {
    return _gruppendaten.keySet();
  }

  public void addGruppendatenObj(Integer position, Gruppendaten gruppendaten) {
    _gruppendaten.put(position, gruppendaten);
  }

  public Gruppendaten getGruppendatenObj(Integer position) {
    return _gruppendaten.get(position);
  }

  public Gebiet getErfassungseinheit() {
    return _erfassungseinheit;
  }

  public void setErfassungseinheit(Gebiet erfassungseinheit) {
    _erfassungseinheit = erfassungseinheit;
  }

  public String getInfoText() {
    return _infoText;
  }

  public void setInfoText(String infoText) {
    _infoText = infoText;
  }

  public String getConfirmationText() {
    return _confirmationText;
  }

  public void setConfirmationText(String confirmationText) {
    _confirmationText = confirmationText;
  }

  /**
   * Gibt an, ob die GUIEingangMsg zur Anzeige o. zur Eingabe der Ergebnisse erstellt wurde.
   * (OSV-1584)
   * 
   * @return
   */
  public Boolean getForDisplay() {
    return _forDisplay;
  }

  public void setForDisplay(Boolean forDisplay) {
    _forDisplay = forDisplay;
  }
}