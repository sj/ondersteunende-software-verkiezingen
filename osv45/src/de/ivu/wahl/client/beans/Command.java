/*
 * Command
 * 
 * Created on 04.01.2016
 * Copyright (c) 2016 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

public enum Command {
  /** Namensliste Alphabetisch */
  NLA(4),

  /** Gebietseingabe im Dialog */
  GEBE(5),

  /** Gebietsergebnis */
  GEB_ERG(6),

  /** Gebietsergebnis */
  GEB_ERG_KAN(7),

  /** Gebietsergebnis Kandidaten Zusammenfassung */
  GEB_ERG_KAN_ZUS(8),

  /** Sitzverteilung im Bund */
  AUSW_SITZVERTEILUNG_GEBIET(19),
  /** Upload von Wahleinheitergebnissen */
  UPLOAD_FILE(23),
  /** Bekanntmachung Endergebnis */
  NLPA(30),
  /** Import der Ergebnisse anzeigen */
  IMPORT_ERGEBNISSE(38),
  /** Exportverzeichnis anzeigen */
  EXPORT_VERZEICHNIS(39),
  /** Exportverzeichnis anzeigen */
  EXPORT(41),

  /** Export for P22-1 or P22-2 Document */
  P22(46),
  /** Export for kennis verkozen kandidaten */
  GEW_BEN(47),
  /** Export for O3-Document */
  O3(48),
  /** Export for N11-Document */
  N11(49),
  /** Export for OSV4_1-Document */
  OSV4_1(50),
  /** Export for Referendum-Document */
  REF_EXP(51),
  /** OnlineHelp */
  HELP(52),
  /** Gebiet Status */
  STATUS_GEB(53),
  /** Status Uebersicht */
  STATUS(54),
  /** Referendum Uebersicht */
  REFERENDUM(55),
  /** Ergebnisse-CSV export **/
  VOTES_CSV(56),
  /** RTF export with objections by voters **/
  PROTOCOL_APPENDIX(57),
  /** CSV export with names and addresses of all candidates **/
  CAND_ADDRESS(58),
  /** Export for T11-Document (EK election, P4_HSB) */
  T11(59),
  /** Export for U16-Document (EK election, P5) */
  U16(60),
  /** Export for OSV4_4-Document (Appendix to O3) */
  OSV4_4(62),
  /** Export for PS1 elections of EML510c + OSV4_1 document (hashcode) */
  EML510c(63),
  /** Export for OSV4_5-Document (Control list for model T11) */
  OSV4_5(64),
  /** Export for Wrr83-Document (Result of referendum) */
  WRR83(65),
  /** Export for OSV4_6-Document (Appendix to Wrr83) */
  OSV4_6(66),

  ANWENDER_ANLEGEN(106),

  ANWENDER_VERAENDERN_1_AUSWAHLEN(107),

  ANWENDER_VERAENDERN_2_EDIT(108),

  ANWENDER_LOESCHEN(109),

  ANWENDER_VERAENDERN_PASSWORT(110),

  /**
   * Anzeigen aller Anwender der Applikation
   */
  ANWENDER_ANZEIGEN(111),

  /**
   * Liste der angemeldeten Anwender
   */
  ADM_ANW_LISTE(114),

  /**
   * Exportieren von CSV-Dateien
   */
  ADM_EXPORT(118),

  /**
   * Ver�ndern / editieren von Properties
   */
  ADM_PROPS(122),

  /**
   * Freigabesteuerung
   */
  ADM_FREIGABE(130),

  /**
   * Re-index database
   */
  ADM_RE_INDEX_DATABASE(131),

  /**
   * Output directory
   */
  ADM_OUTDIR(132),

  /** Neue Wahl importieren */
  ADM_NEUE_WAHL(138),

  /**
   * Kandidat w�hlbar?
   */
  ADM_KANDIDAT_WAEHLBAR(141),

  /**
   * new Areas
   */
  ADM_STIMMBEZIRKE(142),

  /**
   * export of empty eml files
   */
  ADM_EMPTY_EML_EXPORT(143),

  /**
   * edit Areas
   */
  ADM_STIMMBEZIRKE_EDIT(144),

  /**
   * Vote values
   */
  ADM_VOTE_VALUES(145),

  /**
   * export of empty eml files
   */
  ADM_N10_1_EXPORT(146),

  /**
   * ############ Sonstige ab 200
   */

  /**
   * Logout nachfrage
   */
  SONST_LOGOUT(201),

  /** Wahldetails **/
  SONST_ELECTIONDETAILS(205);

  private final int id;

  private Command(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public boolean hasId(int otherId) {
    return id == otherId;
  }
}