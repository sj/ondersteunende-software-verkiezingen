package de.ivu.wahl.modell;

import de.ivu.wahl.util.BundleHelper;

/**
 * SchwellwertModel
 * 
 * @author cos@ivu.de (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public interface SchwellwertModel extends BasicSchwellwertModel {
  // //////////////////////////////
  // Schwellwerte
  // //////////////////////////////

  int SWERT_ART_OHNE = -1;
  int SWERT_ART_ABSOLUT = 0;
  int SWERT_ART_PROZENT = 1;
  int SWERT_ART_PROZENTPUNKTE = 2;
  // allgemein
  String SWERT_WAHLBERECHTIGTE_NIEDRIG = "wber_niedrig"; //$NON-NLS-1$
  String SWERT_WAHLBERECHTIGTE_HOCH = "wber_hoch"; //$NON-NLS-1$
  String SWERT_WAHLBETEILIGUNG_NIEDRIG = "wb_niedrig"; //$NON-NLS-1$
  String SWERT_WAHLBETEILIGUNG_HOCH = "wb_hoch"; //$NON-NLS-1$
  String SWERT_UNGUELTIGE = "uglt"; //$NON-NLS-1$
  String SWERT_LEER = "leer"; //$NON-NLS-1$

  String[][] SWERT_ALLG_KEYS = {
      {
          SWERT_WAHLBERECHTIGTE_NIEDRIG,
          BundleHelper
              .getBundleString("SchwellwertModel.Schwellwert_Wahlberechtigte_Untergrenze_abs"), //$NON-NLS-1$
          String.valueOf(SWERT_ART_ABSOLUT)},
      {
          SWERT_WAHLBERECHTIGTE_HOCH,
          BundleHelper
              .getBundleString("SchwellwertModel.Schwellwert_Wahlberechtigte_Obergrenze_abs"), //$NON-NLS-1$
          String.valueOf(SWERT_ART_ABSOLUT)},
      {
          SWERT_WAHLBETEILIGUNG_NIEDRIG,
          BundleHelper
              .getBundleString("SchwellwertModel.Schwellwert_Wahlberechtigte_Untergrenze_Proz"), //$NON-NLS-1$
          String.valueOf(SWERT_ART_PROZENT)},
      {
          SWERT_WAHLBETEILIGUNG_HOCH,
          BundleHelper
              .getBundleString("SchwellwertModel.Schwellwert_Wahlberechtigte_Obergrenze_Proz"), //$NON-NLS-1$
          String.valueOf(SWERT_ART_PROZENT)},
      {
          SWERT_UNGUELTIGE,
          BundleHelper
              .getBundleString("SchwellwertModel.Schwellwert_Listenstimmen_Obergrenze_Proz"), //$NON-NLS-1$
          String.valueOf(SWERT_ART_PROZENT)},
      {
          SWERT_LEER,
          BundleHelper
              .getBundleString("SchwellwertModel.Schwellwert_Listenstimmen_Obergrenze_Proz"), //$NON-NLS-1$
          String.valueOf(SWERT_ART_PROZENT)}};

  /**
   * Liefert die Schwellwertart f�r den Schwellwert
   * 
   * @param schwellwertKey Schl�ssel des Schwellwerts
   * @return Schwellwertart
   */
  int getArtSchwellwert(String schwellwertKey);

  /**
   * Liefert die Beschreibung f�r den Schwellwert
   * 
   * @param schwellwertKey Schl�ssel des Schwellwerts
   * @return Beschreibung des Schwellwerts
   */
  String getKlartextSchwellwert(String schwellwertKey);
}
