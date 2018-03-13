package de.ivu.wahl.anwender;

import de.ivu.wahl.util.BundleHelper;

/**
 * Konstanten f�r die Abbildung von Funktionen auf Rechte Beinhaltet auch die Zuordnung f�r die
 * Initialisierung der Rechtegruppen
 * 
 * @author klie@ivu.de Copyright (c) 2002 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public interface Rechte {

  // Rechte
  // Die namen der Rechte werden als Prim�rschl�ssel verwendet!

  /**
   * output-verzeichnis
   */
  String R_OUTDIR = "outdir"; //$NON-NLS-1$

  String R_HOCHRECHNUNG = "Hochrechnung"; //$NON-NLS-1$

  /**
   * allgemeine Schwellwerte ver�ndern
   */
  String R_ADM_SCHWELLWERTE_ALLG = "adm_sw_allg"; //$NON-NLS-1$

  /**
   * Parteien Schwellwerte ver�ndern
   */
  String R_ADM_SCHWELLWERTE_PARTEIEN = "adm_sw_parteien"; //$NON-NLS-1$

  /**
   * Parteienschwellwerte f�r alle Kindergebiete �berschreiben
   */
  String R_ADM_SCHWELLWERTE_PARTEIEN_UEBERSCHREIBEN = "adm_sw_parteien_ueberschreiben"; //$NON-NLS-1$

  /**
   * Einstellungen ver�ndern
   */
  String R_ADM_PROPS = "adm_props"; //$NON-NLS-1$

  /**
   * Liste der angemeldeten Anwender anzeigen
   */
  String R_ADM_ANGEMELDETE = "adm_angemeldeteAnw"; //$NON-NLS-1$

  /**
   * Anwender anlegen
   */
  String R_ADM_ANW_ANLEGEN = "adm_anw_anlegen"; //$NON-NLS-1$
  /**
   * Anwender ver�ndern
   */
  String R_ADM_ANW_AENDERN = "adm_anw_aendern"; //$NON-NLS-1$
  /**
   * alle Anwender anzeigen
   */
  String R_ADM_ANW_ANZEIGEN = "adm_anw_anzeigen"; //$NON-NLS-1$

  String R_ADM_KANDIDAT_WAEHLBAR = "adm_kandidat_waehlbar"; //$NON-NLS-1$

  String R_ADM_VOTE_VALUES = "adm_vote_values"; //$NON-NLS-1$

  String R_ADM_EMPTY_EML_EXPORT = "adm_empty_eml_export"; //$NON-NLS-1$

  String R_ADM_N10_1_EXPORT = "adm_n10_1_export"; //$NON-NLS-1$

  /**
   * Stimmbezirke bearbeiten
   */
  String R_ADM_STIMMBEZIRKE_EDIT = "adm_stimmbezirke_edit"; //$NON-NLS-1$

  // /////// Wahleinheiten
  /**
   * Wahleinheit eingeben (ist Landesabh�ngig!)
   */
  String R_EINGABE = "wk_eingeben"; //$NON-NLS-1$

  /**
   * Wahleinheit eingeben (ist Landesabh�ngig!)
   */
  String R_EINGABE_ERLAUBEN = "wk_eingeben_erlauben"; //$NON-NLS-1$

  /**
   * Upload von Wahleinheitergebnissen
   */
  String R_UPLOAD = "wk_ergebnis_upload"; //$NON-NLS-1$

  /**
   * Exportieren von Ergebnissen
   */
  String R_EXPORT = "exportieren"; //$NON-NLS-1$

  /**
   * Import 510-Msg
   */
  String R_IMPORT = "importieren"; //$NON-NLS-1$

  /**
   * Import 510-Msg only for Administrator
   */
  String R_IMPORT_ADM = "importieren_Adm"; //$NON-NLS-1$

  /**
   * Exportieren von Ergebnissen
   */
  String R_SITZVERTEILUNG_BERECHNEN = "sitzverteilung_berechnen"; //$NON-NLS-1$

  /**
   * recht, die Freigabe zu zu erteilen
   */
  String R_FREIGABE = "r_freigabe"; //$NON-NLS-1$

  /**
   * recht, die Freigabe zu zu erteilen
   */
  String R_RE_INDEX_DATABASE = "r_re_index_database"; //$NON-NLS-1$

  /**
   * recht, die Freigabe zur�ckzunehmen
   */
  String R_FREIGABE_RUECK = "r_freigabe_rueck"; //$NON-NLS-1$

  /** Wahl in die Datenbank importieren und aus der Datenbank entfernen */
  String R_ADM_WAHLCREATEREMOVE = "r_adm_wahlcreateremove"; //$NON-NLS-1$

  String RG_ADMIN = "ADMIN"; //$NON-NLS-1$
  String RG_SITZVERTEILUNG = "SITZVERTEILUNG"; //$NON-NLS-1$
  String RG_ANWENDER = "ANWENDER"; //$NON-NLS-1$

  String R_GEB_STATUS = "GEB_STATUS"; //$NON-NLS-1$

  /**
   * Definition der Gruppen f�r die Initialisierung der Datenbank
   */
  String[][] RGRUPPEN_P4 = {{BundleHelper.getBundleString("RG_ADMIN"), //$NON-NLS-1$
      BundleHelper.getBundleString("RG_ADMIN_BESCHREIBUNG"), RG_ADMIN}, //$NON-NLS-1$
      {BundleHelper.getBundleString("RG_ANWENDER"), //$NON-NLS-1$
          BundleHelper.getBundleString("RG_ANWENDER_BESCHREIBUNG"), RG_ANWENDER}}; //$NON-NLS-1$
  /**
   * Definition der Gruppen f�r die Initialisierung der Datenbank
   */
  String[][] RGRUPPEN_P5 = {{BundleHelper.getBundleString("RG_ADMIN"), //$NON-NLS-1$
      BundleHelper.getBundleString("RG_ADMIN_BESCHREIBUNG"), RG_ADMIN}, //$NON-NLS-1$
      {BundleHelper.getBundleString("RG_SITZVERTEILUNG"), //$NON-NLS-1$
          BundleHelper.getBundleString("RG_SITZVERTEILUNG_BESCHREIBUNG"), RG_SITZVERTEILUNG}}; //$NON-NLS-1$

  /**
   * Dieses Array dient sowohl der Auflistung aller Rechte (Anlegen in der DB) als auch der
   * Zusammenstellung der Rechtegruppen
   */
  String[][] RECHTE = {
      // Admin sollte so ziemlich alles d�rfen
      {R_ADM_SCHWELLWERTE_ALLG, RG_ADMIN}, {R_ADM_SCHWELLWERTE_PARTEIEN, RG_ADMIN},
      {R_ADM_SCHWELLWERTE_PARTEIEN_UEBERSCHREIBEN, RG_ADMIN}, {R_EINGABE, RG_ADMIN},
      {R_ADM_ANGEMELDETE, RG_ADMIN}, {R_ADM_ANW_AENDERN, RG_ADMIN}, {R_ADM_ANW_ANLEGEN, RG_ADMIN},
      {R_ADM_ANW_ANZEIGEN, RG_ADMIN}, {R_IMPORT, RG_ADMIN}, {R_IMPORT_ADM, RG_ADMIN},
      {R_ADM_STIMMBEZIRKE_EDIT, RG_ADMIN}, {R_ADM_PROPS, RG_ADMIN}, {R_UPLOAD, RG_ADMIN},
      {R_OUTDIR, RG_ADMIN}, {R_HOCHRECHNUNG, RG_ADMIN}, {R_ADM_WAHLCREATEREMOVE, RG_ADMIN},
      {R_ADM_VOTE_VALUES, RG_ADMIN}, {R_ADM_KANDIDAT_WAEHLBAR, RG_ADMIN},
      {R_ADM_EMPTY_EML_EXPORT, RG_ADMIN}, {R_ADM_N10_1_EXPORT, RG_ADMIN},
      {R_EXPORT, RG_ADMIN},
      {R_FREIGABE, RG_ADMIN},
      {R_FREIGABE_RUECK, RG_ADMIN},
      {R_SITZVERTEILUNG_BERECHNEN, RG_ADMIN},
      {R_GEB_STATUS, RG_ADMIN},
       {R_RE_INDEX_DATABASE, RG_ADMIN},
      {R_EINGABE_ERLAUBEN, RG_ADMIN},

      // normalerAnwender
      {R_EINGABE, RG_ANWENDER}, // {R_IMPORT, RG_ANWENDER},

      // Sitzverteilung
      {R_IMPORT, RG_SITZVERTEILUNG}, {R_UPLOAD, RG_SITZVERTEILUNG},
      {R_SITZVERTEILUNG_BERECHNEN, RG_SITZVERTEILUNG}, {R_EXPORT, RG_SITZVERTEILUNG},
      {R_FREIGABE, RG_SITZVERTEILUNG}};
}