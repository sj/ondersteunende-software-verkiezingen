/*
 * ApplicationBeanKonstanten
 * 
 * Created on 07.11.2003
 * Copyright (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;

/**
 * @author mur@ivu.de, IVU Traffic Technologies AG
 */
public interface ApplicationBeanKonstanten {

  // Zentrale steuerung der Nachkommastellen bei Prozentangaben
  String EXC_RECHTEZUGRIFF = Messages.getString(MessageKeys.Msg_KonnteRechteDesAnwendersNichtLesen);

  int MAXIMUM_DIGET_PROZ = 1;
  int MINIMUM_DIGET_PROZ = 1;
  // Kennung f�r die �bermittlung von Anmeldefehlern
  String LOGIN_ERROR = "login_error"; //$NON-NLS-1$
  /**
   * Prefix der in jsp-Seiten vor alle Submitparameter gestellt werden mu�
   */
  String PREFIX = "pre_"; //$NON-NLS-1$
  /**
   * Navigationsparameter zum aufklappen und zuklappen der Navigationsleiste fangen alle mit
   * nachfolgendem Schl�ssel an, um sie gemeinsam aus dem Request zu entfernen
   */
  /**
   * Parameter als anchor in HTML-Seiten f�r die Navigation
   */
  String NAVI_ANKER = "anker"; //$NON-NLS-1$
  String NAVI_ANKERIS = NAVI_ANKER + "="; //$NON-NLS-1$

  /**
   * Parameter wenn aus der unteren Navigation ein WK direkt angesprungen werdne soll
   */
  String NAVI_UNTEN = "naviu"; //$NON-NLS-1$
  String NAVI_UNTENIS = NAVI_UNTEN + "="; //$NON-NLS-1$

  /** Gebietseingabe im Dialog */
  int GEBE = Command.GEBE.getId();

  /** Gebietsergebnis */
  int GEB_ERG = Command.GEB_ERG.getId();

  String ADM_NAVI_ANKER = "adm_anker"; //$NON-NLS-1$
  String ADM_NAVI_ANKERIS = ADM_NAVI_ANKER + "="; //$NON-NLS-1$

  /**
   * view: URL-Parameter, welcher die momentane Sicht auf das Wahl-System beschreibt Parameter mu�
   * immer in der URL vorhanden sein
   */
  String VIEW = "view"; //$NON-NLS-1$
  String VIEWIS = VIEW + "="; //$NON-NLS-1$

  /** Sicht auf das WahlAbwicklungsSysytem */
  int VIEW_BASIS = 0;

  /** .... .... initial viewstate */
  int VIEWSTATE_INITIAL = VIEW_BASIS;

  /**
   * level: URL-Parameter, welcher die momentane Ebene beschreibt (Bund / Land / Wahleinheit)
   * Parameter mu� immer in der URL vorhanden sein
   */
  String LEVEL = "level"; //$NON-NLS-1$

  /** level= */
  String LEVELIS = LEVEL + "="; //$NON-NLS-1$

  /**
   * level: URL-Parameter, welcher die momentane Ebene beschreibt (Bund / Land / Wahleinheit)
   * Parameter mu� immer in der URL vorhanden sein
   */
  String LEVELAUFRUFENDER = "aufrufender_level"; //$NON-NLS-1$

  /** level= */
  String LEVELAUFRUFENDERIS = LEVELAUFRUFENDER + "="; //$NON-NLS-1$

  /** Sicht auf ein Nachrichtenfenster */
  int LEVEL_NACHRICHT = GebietModel.ANZAHL_GEBIETSARTEN;

  /** Sicht auf die Adminstration */
  int LEVEL_ADMIN = GebietModel.ANZAHL_GEBIETSARTEN + 1;

  /** fuer LEVEL_unabhaengige Commands */
  int LEVEL_UNABHAENGIG = GebietModel.ANZAHL_GEBIETSARTEN + 2;

  /** initial LEVEL */
  int LEVEL_INITIAL = GebietModel.GEBIETSART_BUND;

  /**
   * work: was im Arbeitsbereich angezeigt wird
   */
  String WORK = "work"; //$NON-NLS-1$
  /** work= */
  String WORKIS = WORK + "="; //$NON-NLS-1$

  /**
   * work: was im Arbeitsbereich angezeigt wird
   */
  String WORKAUFRUFENDER = "aufrufender_work"; //$NON-NLS-1$
  /** work= */
  String WORKAUFRUFENDERIS = WORKAUFRUFENDER + "="; //$NON-NLS-1$

  /** initial WORK */
  Command INITIAL_COMMAND = Command.GEB_ERG;

  int WORK_INITIAL = INITIAL_COMMAND.getId();

  /**
   * gebietnr: URL-Parameter, mit der Gebietnummer Parameter mu� in URL vorhanden sein
   */
  String GEBIETNR = "gebietnr"; //$NON-NLS-1$
  /** wkrnr= */
  String GEBIETNRIS = GEBIETNR + "="; //$NON-NLS-1$
  /** initial WKRNR */
  int GEBIETNR_INITIAL = -1;

  /**
   * Schluessel mit dem der angemeldete Anwnder in der Session als AnwContext gespeichert wird.
   */
  String CUR_ANW_KEY = "CUR_ANW_KEY"; //$NON-NLS-1$
}
