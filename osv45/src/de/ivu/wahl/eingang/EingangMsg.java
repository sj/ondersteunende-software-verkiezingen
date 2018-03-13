/*
 * EingangMsg
 * 
 * Copyright (c) 2002-9 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.eingang;

import java.net.URL;
import java.util.Date;

import de.ivu.wahl.AnwContext;

/**
 * Interface for result import messages containing voting results from GUI or eml file respectively
 * 
 * @author cos@ivu.de klie@ivu.de - IVU Traffic Technologies AG
 */

public interface EingangMsg {

  /**
   * Don't accept input with warnings
   */
  final int MODE_CHECK_WARNINGS = 1;

  /**
   * Approve warnings (after affirmation by user)
   */
  final int MODE_APPROVE_WARNINGS = 2;

  /**
   * Ignore warnings (used for file input and second GUI input)
   */
  final int MODE_IGNORE_WARNINGS = 3;

  /**
   * @return Name of the message or <code>null</code>, if there is no name
   */
  String getMsgName();

  /**
   * @return Anwenderkontext or System-Context
   */
  AnwContext getErsteller();

  /**
   * @param ersteller Anwenderkontext or System-Context
   */
  void setErsteller(AnwContext ersteller);

  /**
   * data source (GUI, first or second input or file import resp.)
   * 
   * @return the corresponding value from <code>ErgebniseingangKonstanten</code>
   */
  int getSource();

  /**
   * @return time of data import
   */
  Date getEingangszeit();

  /**
   * @return URL of import file, <code>null</code> for GUI input
   */
  URL getURL();

  /**
   * @return Election name
   */
  String getWahlkurzname();

  /**
   * @return region category
   */
  int getGebietsartErfassungseinheit();

  /**
   * @return region number
   */
  int getNummerErfassungseinheit();

  /**
   * Determines what to do with warnings (check, ignore, approve)
   * 
   * @return corresponding value from <code>EingangMsg</code>
   */
  int getInputMode();

  /**
   * Determines what to do with warnings (check, ignore, approve)
   */
  void setInputMode(int inputMode);

  /**
   * @param position list position for the group or party
   * @return vorin result for al group or party
   */
  int getGruppenstimmen(int position);

  void setGruppenstimmen(int gruppenposition, int stimmen);

  /**
   * @return Fehler-String, welcher alle festgestellten Fehler und Warnungen enth�lt
   */
  String getFehler();

  /**
   * Error messages
   * 
   * @param fehler
   */
  void setFehler(String fehler);

  /**
   * Add an error message
   * 
   * @param msg
   */
  void addFehler(String msg);

  Boolean getUnterschiedeVorhanden();

  void setUnterschiedeVorhanden(Boolean unterschiedeVorhanden);

  /**
   * @return status of this message
   */
  int getStatus();

  /**
   * @param status of this message
   */
  void setStatus(int status);

  /**
   * @return eine Serialisierungskennung als virtuelle Nummer des zu produzierenden
   *         Ergebniseingangs; wird nicht ausgewertet, wenn sie -1 ist
   */
  int getSerialisierungsnummer();

  void setEingangszeit(Date eingangszeit);

  /**
   * @return wahlergebnisart.
   */
  int getWahlergebnisart();

  public int getStimmen(int gruppenposition, int listenposition);

  public String getKandidatenfehler(int gruppenposition, int listenposition);

  public void setKandidatenfehler(int gruppenposition, int listenposition, String fehler);

  public String getGruppefehler(int gruppenposition);

  public void setGruppefehler(int gruppenposition, String fehler);

  public void addGruppefehler(int gruppenposition, String fehler);

  public void resetGruppeUndKandidatenfehler(int gruppenposition);

  public String getErgebnisHash();
}