/*
 * BasicEingangMsg
 * 
 * Copyright (c) 2002-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell;

import java.io.Serializable;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import de.ivu.wahl.AnwContext;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.eingang.EingangMsg;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein;

/**
 * Basic data object containing voting results, used to hold input data from GUI or file and results
 * for display
 * 
 * @author ugo@ivu.de cos@ivu.de klie@ivu.de - IVU Traffic Technologies AG
 */
public abstract class BasicEingangMsg implements EingangMsg, Serializable {

  private static final long serialVersionUID = -4193896414065496576L;

  protected static final char LF = '\n';

  private String _msgName = null;

  private AnwContext _ersteller = null;

  private int _source;

  private Date _eingangszeit;

  protected int _nummerErfassungseinheit = -1;

  protected int _gebietsartErfassungseinheit = -1;

  protected int _inputMode;

  protected String _gruppenfehler;

  protected Boolean _unterschiedeVorhanden = null;

  protected int _status = ErgebniseingangKonstanten.STATE_OK;

  protected String _wahlkurzname;

  protected int _wahlergebnisart;

  protected final URL _url;

  protected Map<Integer, Gruppenergebnis> _gruppenergebnisse = new TreeMap<Integer, Gruppenergebnis>();

  /**
   * Constructor
   * 
   * @param ersteller
   */
  public BasicEingangMsg(AnwContext ersteller) {
    this(ersteller, null);
  }

  public BasicEingangMsg(AnwContext ersteller, URL url) {
    WahlInfo wahlInfo = WahlInfo.getWahlInfo(ersteller);
    _wahlergebnisart = wahlInfo.getAktuelleWahlergebnisart();
    _ersteller = ersteller;
    _url = url;
  }

  public int getGebietsartErfassungseinheit() {
    return _gebietsartErfassungseinheit;
  }

  public int getNummerErfassungseinheit() {
    return _nummerErfassungseinheit;
  }

  public int getInputMode() {
    return _inputMode;
  }

  /**
   * Setzt den neuen Wert f�r eingabeart auf eingabeart.
   * 
   * @param eingabeart neuer Wert f�r eingabeart
   */
  public void setInputMode(int eingabeart) {
    _inputMode = eingabeart;
  }

  public String getFehler() {
    return _gruppenfehler;
  }

  /**
   * @param position
   * @param fehler
   */
  public void setFehler(String fehler) {
    _gruppenfehler = fehler;
  }

  public void addFehler(String msg) {
    if (_gruppenfehler == null) {
      _gruppenfehler = ErgebniseingangKonstanten.THREE_ASTERISKS + msg;
    } else {
      _gruppenfehler += Konstanten.BR + ErgebniseingangKonstanten.THREE_ASTERISKS + msg;
    }
  }

  public Boolean getUnterschiedeVorhanden() {
    return _unterschiedeVorhanden;
  }

  public void setUnterschiedeVorhanden(Boolean unterschiedeVorhanden) {
    _unterschiedeVorhanden = unterschiedeVorhanden;
  }

  public int getStatus() {
    return _status;
  }

  public void setStatus(int status) {
    _status = status;
  }

  /**
   * {@inheritDoc} dummy Implementierung
   */
  public int getSerialisierungsnummer() {
    return -1;
  }

  /**
   * @return Kurzname der Wahl
   */
  @Override
  public String getWahlkurzname() {
    return _wahlkurzname;
  }

  @Override
  public void setEingangszeit(Date eingangszeit) {
    _eingangszeit = eingangszeit;
  }

  /**
   * @return msgName.
   */
  public String getMsgName() {
    return _msgName;
  }

  /**
   * Setzt den neuen Wert f�r msgName auf msgName.
   * 
   * @param msgName neuer Wert f�r msgName
   */
  public void setMsgName(String msgName) {
    _msgName = msgName;
  }

  /**
   * @return ersteller.
   */
  public AnwContext getErsteller() {
    return _ersteller;
  }

  /**
   * Setzt den neuen Wert f�r ersteller auf ersteller.
   * 
   * @param ersteller neuer Wert f�r ersteller
   */
  public void setErsteller(AnwContext ersteller) {
    _ersteller = ersteller;
  }

  /**
   * @return herkunft.
   */
  public int getSource() {
    return _source;
  }

  /**
   * Setzt den neuen Wert f�r herkunft auf herkunft.
   * 
   * @param herkunft neuer Wert f�r herkunft
   */
  public void setSource(int herkunft) {
    _source = herkunft;
  }

  /**
   * @return eingangszeit.
   */
  public Date getEingangszeit() {
    return _eingangszeit;
  }

  /**
   * Setzt den neuen Wert für nummerErfassungseinheit auf nummerErfassungseinheit.
   * 
   * @param nummerErfassungseinheit neuer Wert für nummerErfassungseinheit
   */
  public void setNummerErfassungseinheit(int nummerErfassungseinheit) {
    _nummerErfassungseinheit = nummerErfassungseinheit;
  }

  public int getWahlergebnisart() {
    return _wahlergebnisart;
  }

  public int getStimmen(int gruppenposition, int listenposition) {
    return _gruppenergebnisse.get(gruppenposition).getStimmen(listenposition);
  }

  public void setStimmen(int gruppenposition, int listenposition, int stimmen) {
    _gruppenergebnisse.get(gruppenposition).setStimmen(listenposition, stimmen);
  }

  public String getKandidatenfehler(int gruppenposition, int listenposition) {
    return _gruppenergebnisse.get(gruppenposition).getKandidatenfehler(listenposition);
  }

  public void setKandidatenfehler(int gruppenposition, int listenposition, String fehler) {
    _gruppenergebnisse.get(gruppenposition).setKandidatenfehler(listenposition, fehler);
  }

  public int getGruppenstimmen(int gruppenposition) {
    return _gruppenergebnisse.get(gruppenposition).getGesamtstimmen();
  }

  public void setGruppenstimmen(int gruppenposition, int stimmen) {
    if (_gruppenergebnisse.get(gruppenposition) == null) {
      throw new RuntimeException(Messages.bind(MessageKeys.Error_UngueltigerGruppenschluessel_0,
          String.valueOf(gruppenposition)));
    }
    _gruppenergebnisse.get(gruppenposition).setGesamtstimmen(stimmen);
  }

  public String getGruppefehler(int gruppenposition) {
    return _gruppenergebnisse.get(gruppenposition).getGruppefehler();
  }

  public void setGruppefehler(int gruppenposition, String fehler) {
    _gruppenergebnisse.get(gruppenposition).setGruppefehler(fehler);
  }

  public void addGruppefehler(int gruppenposition, String fehler) {
    String gruppefehlerBisher = _gruppenergebnisse.get(gruppenposition).getGruppefehler();
    if (gruppefehlerBisher == null) {
      _gruppenergebnisse.get(gruppenposition).setGruppefehler(fehler);
    } else {
      _gruppenergebnisse.get(gruppenposition).setGruppefehler(gruppefehlerBisher + Konstanten.BR
          + fehler);
    }
  }

  public void resetGruppeUndKandidatenfehler(int gruppenposition) {
    _gruppenergebnisse.get(gruppenposition).resetGruppeUndKandidatenfehler();
  }

  public void addGruppenergebnis(int gruppenposition) {
    _gruppenergebnisse.put(gruppenposition, new Gruppenergebnis(gruppenposition));
  }

  public Map<Integer, Gruppenergebnis> getGruppenergebnisse() {
    return _gruppenergebnisse;
  }

  public Gruppenergebnis getGruppenergebnis(int gruppenposition) {
    return _gruppenergebnisse.get(gruppenposition);
  }

  /**
   * @return url.
   */
  public URL getURL() {
    return _url;
  }

  /**
   * Result for one party/group
   * 
   * @author ugo@ivu.de, IVU Traffic Technologies AG
   */
  public class Gruppenergebnis {

    int _position;
    String _gruppefehler;

    protected Map<Integer, Integer> _daten = new TreeMap<Integer, Integer>();
    protected Map<Integer, String> _kandidatenfehler = new TreeMap<Integer, String>();

    private int _gesamtStimmen;
    private double _prozentWert;

    public double getProzentWert() {
      return _prozentWert;
    }

    public void setProzentWert(double prozentWert) {
      _prozentWert = prozentWert;
    }

    public Gruppenergebnis(int position) {
      _position = position;
    }

    public int getAnzahlZeilen() {
      return _daten.size();
    }

    public int getGesamtStimmen() {
      return _gesamtStimmen;
    }

    public void setGesamtStimmen(int gesamtStimmen) {
      _gesamtStimmen = gesamtStimmen;
    }

    public Map<Integer, Integer> getDaten() {
      return _daten;
    }

    public Map<Integer, String> getKandidatenfehlermap() {
      return _kandidatenfehler;
    }

    /** {@inheritDoc} */
    public int getStimmen(int listenplatz) {
      Integer stimmen = _daten.get(listenplatz);
      if (stimmen == null) {
        return StimmergebnisModel.STIMMEN_EINTRAG_NICHT_VORHANDEN;
      } else {
        return stimmen;
      }
    }

    public void setStimmen(int listenplatz, int stimmen) {
      _daten.put(listenplatz, stimmen);
    }

    public String getKandidatenfehler(int listenplatz) {
      return _kandidatenfehler.get(listenplatz);
    }

    public void setKandidatenfehler(int listenplatz, String fehler) {
      _kandidatenfehler.put(listenplatz, fehler);
    }

    /** {@inheritDoc} */
    public int getGesamtstimmen() {
      return _gesamtStimmen;
    }

    public void setGesamtstimmen(int stimmen) {
      _gesamtStimmen = stimmen;
    }

    public int getPosition() {
      return _position;
    }

    public void setGruppefehler(String fehler) {
      _gruppefehler = fehler;
    }

    public String getGruppefehler() {
      return _gruppefehler;
    }

    public void resetGruppeUndKandidatenfehler() {
      _gruppefehler = null;
      _kandidatenfehler = new TreeMap<Integer, String>();
    }

    public boolean isFehlerVorhanden() {
      return _gruppefehler != null || _kandidatenfehler.size() > 0;
    }

    public boolean isEingabeGroesserNullVorhanden() {
      if (_gesamtStimmen > 0) {
        return true;
      }
      for (Entry<Integer, Integer> entry : _daten.entrySet()) {
        if (entry.getValue() > 0) {
          return true;
        }
      }
      return false;
    }

    public String getErgebnisAsString() {
      StringBuilder resultAsString = new StringBuilder();
      if (_position < 0) {
        resultAsString.append(_position).append("."); //$NON-NLS-1$
        resultAsString.append(getGesamtstimmen());
      }
      for (Integer pos : _daten.keySet()) {
        resultAsString.append(pos.toString()).append("."); //$NON-NLS-1$
        resultAsString.append(getStimmen(pos));
      }
      return resultAsString.toString();
    }
  }

  @Override
  public String getErgebnisHash() {
    StringBuilder resultAsString = new StringBuilder();
    for (Entry<Integer, Gruppenergebnis> entry : getGruppenergebnisse().entrySet()) {
      if (!entry.getKey().equals(GruppeAllgemein.WAHLBERECHTIGTE.getPosition())) {
        // Create hash code from all groups excluding the number of voters
        Gruppenergebnis gruppenergebnis = entry.getValue();
        resultAsString.append(gruppenergebnis.getErgebnisAsString());
      }
    }
    return createDigestFromBytes(resultAsString.toString().getBytes());
  }

  private String createDigestFromBytes(byte[] content) {
    try {
      final String MD = "SHA-256"; //$NON-NLS-1$
      MessageDigest md = MessageDigest.getInstance(MD);
      StringBuilder sb = new StringBuilder();

      byte[] digest = md.digest(content);
      for (byte digit : digest) {
        String hexString = Integer.toHexString(digit & 0xff).toUpperCase();
        if (hexString.length() < 2) {
          hexString = "0" + hexString; //$NON-NLS-1$
        }
        sb.append(hexString);
      }
      return sb.toString().trim();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Digest algorithm not found", e); //$NON-NLS-1$
    }
  }
}