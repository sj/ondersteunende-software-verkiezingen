package de.ivu.wahl.client.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Kleines Hilfobjekt zum erfassen, von durch den Anwender anstossbaren Aktionen Die Visualisierung
 * wird in der entsprechenden JSP umgesetzt. im ApplicationBean werden Listen von f�r den
 * Angemeldeten Anwender zul�ssigen Befehlen zusammengestellt und bereitgehalten.
 * 
 * @author klie@ivu.de Copyright (c) 2002-5 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public class GUICommand implements Serializable {
  private static final long serialVersionUID = -993265416544665946L;

  /**
   * GUIClass Um optisch verschieden Bl�cke im Befehlsframe schaffen zu k�nne, werden den
   * GUICommands classes f�r die HTML-Oberfl�che mitgegeben. Hiermit k�nnen �ber einen Stylesheet
   * (*.css) den Befehlen unterschiedliches Aussehen verliehen werden.
   */
  private static final String GUI_CLASS_PRE = "guiBefehl_";
  public static final String GUI_CLASS_1 = GUI_CLASS_PRE + 1;
  public static final String GUI_CLASS_2 = GUI_CLASS_PRE + 2;
  public static final String GUI_CLASS_3 = GUI_CLASS_PRE + 3;
  public static final String GUI_CLASS_4 = GUI_CLASS_PRE + 4;
  public static final String GUI_CLASS_5 = GUI_CLASS_PRE + 5;
  public static final String GUI_CLASS_6 = GUI_CLASS_PRE + 6;
  public static final String GUI_SELECTED = GUI_CLASS_PRE + "selected";

  // Arbeitsview, kodiert durch Konstanten im ApplicationBean
  private int _viewNr = -1;

  // ben�tigtes Recht (Konstanten in de.ivu.wahl.anwender.Rechte)
  // null beduetet, es ist kein recht erforderlich
  private String _recht = null;

  // wenn true dann abh�ngigkeit von land
  private boolean _gebietsabhaengig = false;

  // anzuzeigener Tooltip auf dem Befehlslink
  private String _tooltip = null;

  // legt fest, zu welcher Farbgruppe (durch class in HTML) dieser Befehl geh�rt
  private String _guiClass = null;

  public static final int STATE_DONT_CARE = -1;
  // Befehl nur bei einem bestimmten Anwendungszustand?
  // Ist dies der Fall, dann steht er hier drin
  private int _appstate = STATE_DONT_CARE;

  // Befehl ist nur bei einer bestimmten Eingabeart m�glich
  private int _wahlart = STATE_DONT_CARE;

  // Soll der Befehl nur Angezeigt werden, wenn die Freigabe durch den Bundeswahlleiter erfolgt ist
  private boolean _nurFreigegeben = false;

  // Soll der Befehl nur Angezeigt werden, wenn alle Ergebnisse eingegangen sind und damit das
  // Wurzelgebiet vollstaendig ist
  private boolean _nurVollstaendig = false;

  // Soll der Befehl nur Angezeigt werden, wenn noch nicht alle Ergebnisse eingegangen sind und
  // damit das Wurzelgebiet nicht vollstaendig ist
  private boolean _nurNichtVollstaendig = false;

  // Soll der Befehl nur Angezeigt werden, wenn die Freigabe durch den Bundeswahlleiter NOCH NICHT
  // erfolgt ist
  private boolean _nurNichtFreigegeben = false;

  // ist ein recht eingetragen, wird dieses ben�tigt, damir der Befehl auch ohne Freigabe sichtbar
  // wird
  private String _rechtOderFreigabe = null;

  // Wahl muss geschlossen und und dieses recht vorhanden, dann kann der befehl gesehen werden
  private String _rechtUndGeschlossen = null;

  // Wahl soll auf allen Leveln angezeigt werden. z.B. Command zum Abmelden vom System
  private boolean _alleLevel = false;

  // Wahl soll nur auf allen Gebieten angezeigt werden. z.B. Command zum Abmelden vom System
  private boolean _nurGebiete = false;

  // soll nur angezeigt werden, wenn Anwender das entprechende Recht auf dem Wurzelgebiet hat.
  // z.B. Admin- Grundeinstellungen �ndern
  private boolean _nurWennRechtAufWurzelgebiet = false;

  /**
   * Position, an der der Command angezeigt werden soll. Ist nur die Position in einer Liste, an der
   * der Command abgelegt wird. Setzt zu einem sp�teren Zeitpunkt jemand einen Command an die
   * gleiche Position, wird vorheriger um eine Position nach hinten verschoben
   */
  private int _position = -1;

  /**
   * Command wird nur angezeigt, wenn es sich bei dem Gebiet um eine Erfassungseinheit handelt
   */
  private boolean _nurErfassungseinheit = false;

  private boolean _nurWurzelgebiet = false;

  /**
   * Command wird nur angezeigt, wenn es sich bei dem Gebiet um ein Wahlgebiet handelt
   */
  private boolean _nurWahlgebiete = false;

  /**
   * Befehlsbezeichnung zum Anzeigen Map, um bei LEVELUNABH�NGIGKEIT f�r verschiedene Level
   * verschieden Name angeben zu k�nnen
   */
  private final Map<Object, String> _bezeichnungsmap;
  private static final String DEFAULTNAME = "defaultname"; //$NON-NLS-1$

  public GUICommand(String bezeichnung,
      int viewNr,
      String recht,
      boolean gebietsabhaengig,
      String tooltip,
      String guiClass) {
    _bezeichnungsmap = new HashMap<Object, String>();
    _bezeichnungsmap.put(DEFAULTNAME, bezeichnung);
    _viewNr = viewNr;
    _recht = recht;
    _gebietsabhaengig = gebietsabhaengig;
    _tooltip = tooltip;
    _guiClass = guiClass;
  }

  /**
   * @return Zustand der Application in der der Befehl bereitgestellt wird
   */
  public int getAppstate() {
    return _appstate;
  }

  /**
   * Setter f�r die Eigenschaft des Applikationszustandes, da dies eher selten gebraucht wird
   * 
   * @param state Zustand der Application in der der Befehl bereitgestellt wird
   */
  public void setAppstate(int state) {
    _appstate = state;
  }

  /**
   * @return true, wenn befehl vom Land abh�ngig ist
   */
  public boolean getGebietsabhaengig() {
    return _gebietsabhaengig;
  }

  /**
   * @return Nummer des Views f�r das Ergebnis des Befehls
   */
  public int getViewNr() {
    return _viewNr;
  }

  public String getBezeichnung() {
    return _bezeichnungsmap.get(DEFAULTNAME);
  }

  public String getBezeichnung(int levelkey) {
    Integer key = new Integer(levelkey);
    if (_bezeichnungsmap.containsKey(key)) {
      return _bezeichnungsmap.get(key);
    }
    return _bezeichnungsmap.get(DEFAULTNAME);
  }

  public void setBezeichnung(int levelkey, String levelname) {
    _bezeichnungsmap.put(new Integer(levelkey), levelname);
  }

  /**
   * @return Recht, das f�r die Ausf�hrung n�tig ist
   */
  public String getRecht() {
    return _recht;
  }

  /**
   * @return Inhalt des Tooltips f�r den Befehl
   */
  public String getTooltip() {
    return _tooltip;
  }

  public String getGUIClass() {
    return _guiClass;
  }

  /**
   * @return die Eingabeart, f�r die der befehl vorhanden sein soll oder State_dont_care
   */
  public int getWahlArt() {
    return _wahlart;
  }

  /**
   * Setze die Eingabeart, f�r den der Befehl sichtbar sein soll
   * 
   * @param art Eingabeart, f�r die der Befehl sichtbar sein soll
   */
  public void setWahlArt(int art) {
    _wahlart = art;
  }

  /**
   * @return <code>true</code>, wenn der Befehl nur bei freigegebener Wahl verf�gbar ist
   */
  public boolean getNurFreigegeben() {
    return _nurFreigegeben;
  }

  /**
   * @param nurFreigegeben <code>true</code>, wenn der Befehl nur bei freigegebener Wahl verf�gbar
   *          ist
   */
  public void setNurFreigegeben(boolean nurFreigegeben) {
    _nurFreigegeben = nurFreigegeben;
  }

  /**
   * @return <code>true</code>, wenn der Befehl nur bei nicht freigegebener Wahl verf�gbar ist
   */
  public boolean getNurNichtFreigegeben() {
    return _nurNichtFreigegeben;
  }

  /**
   * @param nurNichtFreigegeben <code>true</code>, wenn der Befehl nur bei nicht freigegebener Wahl
   *          verf�gbar ist
   */
  public void setNurNichtFreigegeben(boolean nurNichtFreigegeben) {
    _nurNichtFreigegeben = nurNichtFreigegeben;
  }

  /**
   * @return Recht, das n�tig ist, um diesen Befehl vor der Freigabe zu nutzen (impliziert nach
   *         Freigabe immer nutzbar)
   */
  public String getRechtOderFreigabe() {
    return _rechtOderFreigabe;
  }

  /**
   * @param recht Recht, das n�tig ist, um diesen Befehl vor der Freigabe zu nutzen (impliziert nach
   *          Freigabe immer nutzbar)
   */
  public void setRechtOderFreigabe(String recht) {
    _rechtOderFreigabe = recht;
  }

  /**
   * @return Recht, das n�tig ist, um diesen Befehl nach dem Schlie�en der Wahl zu nutzen
   *         (impliziert sonst gar nicht nutzbar!)
   */
  public String getRechtUndGeschlossen() {
    return _rechtUndGeschlossen;
  }

  /**
   * @param recht Recht, das n�tig ist, um diesen Befehl nach dem Schlie�en der Wahl zu nutzen
   *          (impliziert sonst gar nicht nutzbar!)
   */
  public void setRechtUndGeschlossen(String recht) {
    _rechtUndGeschlossen = recht;
  }

  /**
   * @param nurWennRechtAufWurzelgebiet <code>true</code>, wenn GUICommand angezeigt werden soll,
   *          wenn Anwender das entprechende Recht auf dem Wurzelgebiet hat.
   */
  public void setNurWennRechtAufWurzelgebiet(boolean nurWennRechtAufWurzelgebiet) {
    _nurWennRechtAufWurzelgebiet = nurWennRechtAufWurzelgebiet;
  }

  /**
   * @return <code>true</code>, wenn GUICommand angezeigt werden soll, wenn Anwender das
   *         entprechende Recht auf dem Wurzelgebiet hat.
   */
  public boolean getNurWennRechtAufWurzelgebiet() {
    return _nurWennRechtAufWurzelgebiet;
  }

  /**
   * Achtung: Methode mit gewollten Seiteneffekten
   * 
   * @param gebiete wenn <code>true</code>, dann: setAlleLevel(false);
   *          setNurErfassungseinheit(false); setNurWahlgebiete(false);
   */
  public void setNurGebiete(boolean gebiete) {
    _nurGebiete = gebiete;
    if (gebiete) {
      _alleLevel = false;
      _nurErfassungseinheit = false;
      _nurWahlgebiete = false;
    }
  }

  /**
   * @return <code>true</code>, wenn der Befehl nur auf Gebieten zur Verf�gung steht
   */
  public boolean getNurGebiete() {
    return _nurGebiete;
  }

  /**
   * @return <code>true</code>, wenn der Befehl auf allen Leveln zur Verf�gung steht
   */
  public boolean getAlleLevel() {
    return _alleLevel;
  }

  /**
   * Achtung: Methode mit gewollten Seiteneffekten
   * 
   * @param level wenn <code>true</code>, dann:<br>
   *          setNurGebiete(false);<br>
   *          setNurErfassungseinheit(false);<br>
   *          setNurWahlgebiete(false);<br>
   */
  public void setAlleLevel(boolean level) {
    _alleLevel = level;
    if (level) {
      _nurGebiete = false;
      _nurErfassungseinheit = false;
      _nurWahlgebiete = false;
    }
  }

  /**
   * @return Position des Commands in den verschiedenen Command-Listen: -1 wenn der Command einfach
   *         ans Ende geh�ngt werden soll
   */
  public int getPosition() {
    return _position;
  }

  /**
   * Setzt die Position, an der der Command angezeigt werden soll. Ist nur die Position in einer
   * Liste, an der der Command abgelegt wird. Setzt zu einem sp�teren Zeitpunkt jemand einen Command
   * an die gleiche Position, wird vorheriger um eine Position nach hinten verschoben.
   * 
   * @param position Position, an der der Command angezeigt werden soll
   */
  public void setPosition(int position) {
    _position = position;
  }

  /**
   * @return nurErfassungseinheit, Command wird nur angezeigt, wenn es sich bei dem Gebiet um eine
   *         Erfassungseinheit handelt
   */
  public boolean getNurErfassungseinheit() {
    return _nurErfassungseinheit;
  }

  /**
   * @return nurWuzelgebiet, Command wird nur angezeigt, wenn es sich bei dem Gebiet um das
   *         Wurzelgebiet handelt
   */
  public boolean getNurWurzelgebiet() {
    return _nurWurzelgebiet;
  }

  /**
   * @param nurWurzelgebiet Command wird nur angezeigt, wenn es sich bei dem Gebiet um das
   *          Wurzelgebiet handelt.<br>
   *          Achtung Methode mit gewollten Seiteneffekten: wenn auf <code>true</code>, dann:<br>
   *          setAlleLevel(<code>false</code>);<br>
   *          setNurGebiete(<code>true</code>);<br>
   *          setNurWahlgebiete(<code>false</code>);<br>
   *          setNurErfassungseinheit(<code>false</code>);<br>
   */
  public void setNurWurzelgebiet(boolean nurWurzelgebiet) {
    _nurWurzelgebiet = nurWurzelgebiet;
    if (nurWurzelgebiet) {
      _nurErfassungseinheit = false;
      _alleLevel = false;
      _nurGebiete = true;
      _nurWahlgebiete = false;
    }
  }

  /**
   * @param erfassungseinheit Command wird nur angezeigt, wenn es sich bei dem Gebiet um eine
   *          Erfassungseinheit handelt.<br>
   *          Achtung Methode mit gewollten Seiteneffekten: wenn auf <code>true</code>, dann:<br>
   *          setAlleLevel(<code>false</code>);<br>
   *          setNurGebiete(<code>true</code>);<br>
   *          setNurWahlgebiete(<code>false</code>);<br>
   */
  public void setNurErfassungseinheit(boolean erfassungseinheit) {
    _nurErfassungseinheit = erfassungseinheit;
    if (erfassungseinheit) {
      _alleLevel = false;
      _nurGebiete = true;
      _nurWahlgebiete = false;
    }
  }

  /**
   * @return nurWahlgebiete, Command wird nur angezeigt, wenn es sich bei dem Gebiet um ein
   *         Wahlgebiet handelt.
   */
  public boolean getNurWahlgebiete() {
    return _nurWahlgebiete;
  }

  /**
   * @param wahlgebiete Command wird nur angezeigt, wenn es sich bei dem Gebiet um ein Wahlgebiet
   *          handelt.<br>
   *          Achtung Methode mit gewollten Seiteneffecten: wenn auf <code>true</code>, dann:<br>
   *          setAlleLevel(<code>false</code>);<br>
   *          setNurGebiete(<code>true</code>);<br>
   *          setNurErfassungseinheit(<code>false</code>);<br>
   */
  public void setNurWahlgebiete(boolean wahlgebiete) {
    _nurWahlgebiete = wahlgebiete;
    if (wahlgebiete) {
      setAlleLevel(false);
      setNurGebiete(true);
      setNurErfassungseinheit(false);
    }
  }

  /**
   * @return <code>true</code>, wenn alle Ergebnisse eingegangen sind und damit das Wurzelgebiet
   *         vollstaendig ist
   */
  public boolean getNurVollstaendig() {
    return _nurVollstaendig;
  }

  /**
   * @return <code>true</code>, wenn noch nicht alle Ergebnisse eingegangen sind und damit das
   *         Wurzelgebiet nicht vollstaendig ist
   */
  public boolean getNurNichtVollstaendig() {
    return _nurNichtVollstaendig;
  }

  /**
   * @param nurVollstaendig <code>true</code>, wenn alle Ergebnisse eingegangen sind und damit das
   *          Wurzelgebiet vollstaendig ist
   */
  public void setNurVollstaendig(boolean nurVollstaendig) {
    _nurVollstaendig = nurVollstaendig;
  }

  /**
   * @param nurNichtVollstaendig <code>true</code>, wenn noch nicht alle Ergebnisse eingegangen sind
   *          und damit das Wurzelgebiet nicht vollstaendig ist
   */
  public void setNurNichtVollstaendig(boolean nurNichtVollstaendig) {
    _nurNichtVollstaendig = nurNichtVollstaendig;
  }
}