package de.ivu.wahl.modell;

import java.util.Map;

import de.ivu.util.collections.TwoKeyMap;
import de.ivu.wahl.dataimport.AbstractImportEML;
import de.ivu.wahl.util.ConstantMap;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * WahlModel. Defines constants for the election.
 * 
 * @author cos@ivu.de (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public interface WahlModel extends BasicWahlModel {

  public static final int STATE_INITIAL = 0;
  public static final int STATE_STEMDISTRICT_IMPORT = 1;
  /**
   * after import of meta-data-files in P4
   */
  public static final int STATE_METADATA_P4 = 2;
  /**
   * after import of meta-data-files in P5
   */
  public static final int STATE_METADATA_P5 = 3;
  /**
   * new result, but no seat-calculation
   */
  public static final int STATE_NEW_RESULT = 4;
  public static final int STATE_CALCULATING = 5;
  public static final int STATE_CALCULATION_CONFLICT = 6;
  public static final int STATE_CALCULATION_SUCCESSFUL = 7;

  /**
   * Konstante für die bei der Wahlart TWEEDE_KAMER in den Niederlanden beinhalteten Gebiete
   */
  int[] GEBIETE_TWEEDE_KAMER = {GebietModel.GEBIETSART_BUND, GebietModel.GEBIETSART_WAHLKREIS,
      GebietModel.GEBIETSART_GEMEINDE, GebietModel.GEBIETSART_STIMMBEZIRK};

  /**
   * Constant for the involved districts for the election EUROPEAN_PARLAMENT in the Netherlands
   */
  int[] GEBIETE_EP_NL = {GebietModel.GEBIETSART_BUND, GebietModel.GEBIETSART_WAHLKREIS,
      GebietModel.GEBIETSART_GEMEINDE, GebietModel.GEBIETSART_STIMMBEZIRK};

  /**
   * Constant for the involved districts for the election PROVINCE in the Netherlands
   */
  int[] GEBIETE_PROVINCE_NL = {GebietModel.GEBIETSART_LAND, GebietModel.GEBIETSART_WAHLKREIS,
      GebietModel.GEBIETSART_GEMEINDE, GebietModel.GEBIETSART_STIMMBEZIRK};

  /**
   * Constant for the involved districts for the election ALGEMEEN_BESTUUR (Waterschap) in the
   * Netherlands
   */
  int[] GEBIETE_ALGEMEEN_BESTUUR_NL = {GebietModel.GEBIETSART_ALGEMEEN_BESTUUR,
      GebietModel.GEBIETSART_WAHLKREIS, GebietModel.GEBIETSART_GEMEINDE,
      GebietModel.GEBIETSART_STIMMBEZIRK};

  /**
   * Constant for the involved districts for the election GEMEENTERAAD in the Netherlands
   */
  int[] GEBIETE_GEMEENTE_NL = {GebietModel.GEBIETSART_GEMEINDE, GebietModel.GEBIETSART_STIMMBEZIRK};

  /**
   * Constant for the involved districts for the election EILANDSRAAD in the Netherlands
   */
  int[] GEBIETE_EILAND_NL = {GebietModel.GEBIETSART_INSELGEMEINDE,
      GebietModel.GEBIETSART_STIMMBEZIRK};

  /**
   * Constant for the involved districts for the election DEELRAAD in the Netherlands
   */
  int[] GEBIETE_DEELRAAD_NL = {GebietModel.GEBIETSART_ORTSTEIL, GebietModel.GEBIETSART_STIMMBEZIRK};

  /**
   * Constant for the involved districts for the national referendum in the Netherlands
   */
  int[] GEBIETE_REFERENDUM_NR = {GebietModel.GEBIETSART_BUND, GebietModel.GEBIETSART_WAHLKREIS,
      GebietModel.GEBIETSART_GEMEINDE, GebietModel.GEBIETSART_STIMMBEZIRK};

  /**
   * Constant for the involved districts for the provincial referendum in the Netherlands
   */
  int[] GEBIETE_REFERENDUM_PR = {GebietModel.GEBIETSART_LAND, GebietModel.GEBIETSART_WAHLKREIS,
      GebietModel.GEBIETSART_GEMEINDE, GebietModel.GEBIETSART_STIMMBEZIRK};

  /**
   * Constant for the involved districts for the local referendum in the Netherlands
   */
  int[] GEBIETE_REFERENDUM_LR = {GebietModel.GEBIETSART_GEMEINDE,
      GebietModel.GEBIETSART_STIMMBEZIRK};

  /**
   * Constant for the involved districts for the Eilands referendum in the Netherlands
   */
  int[] GEBIETE_REFERENDUM_IR = {GebietModel.GEBIETSART_INSELGEMEINDE,
      GebietModel.GEBIETSART_STIMMBEZIRK};

  /**
   * Constant for the involved districts for the election type EERSTE KAMER in the Netherlands
   */
  int[] GEBIETE_EERSTE_KAMER = {GebietModel.GEBIETSART_BUND, GebietModel.GEBIETSART_LAND,
      GebietModel.GEBIETSART_STIMMBEZIRK};

  /**
   * Konstante für die Abbildung der Wahlart auf die jeweils beinhalteten Gebiete
   */
  Map<ElectionCategory, int[]> WAHLGEBIETSARTEN = new ConstantMap<ElectionCategory, int[]>() {
    {
      add(ElectionCategory.TK, GEBIETE_TWEEDE_KAMER);
      add(ElectionCategory.EK, GEBIETE_EERSTE_KAMER);
      add(ElectionCategory.EP, GEBIETE_EP_NL);
      add(ElectionCategory.GR, GEBIETE_GEMEENTE_NL);
      add(ElectionCategory.ER, GEBIETE_EILAND_NL);
      add(ElectionCategory.BC, GEBIETE_DEELRAAD_NL);
      add(ElectionCategory.GC, GEBIETE_DEELRAAD_NL);
      add(ElectionCategory.PS, GEBIETE_PROVINCE_NL);
      add(ElectionCategory.AB, GEBIETE_ALGEMEEN_BESTUUR_NL);
      add(ElectionCategory.NR, GEBIETE_REFERENDUM_NR);
      add(ElectionCategory.PR, GEBIETE_REFERENDUM_PR);
      add(ElectionCategory.LR, GEBIETE_REFERENDUM_LR);
      add(ElectionCategory.IR, GEBIETE_REFERENDUM_IR);
    }
  };

  TwoKeyMap<ElectionCategory, Integer, Integer> GEBIETSART_FOR_WAHLART_UND_EBENE = new TwoKeyMap<ElectionCategory, Integer, Integer>() {
    {
      put(ElectionCategory.EP, GebietModel.EBENE_CSB, GebietModel.GEBIETSART_BUND);
      put(ElectionCategory.EP, GebietModel.EBENE_HSB, GebietModel.GEBIETSART_WAHLKREIS);
      put(ElectionCategory.EP, GebietModel.EBENE_PSB, GebietModel.GEBIETSART_GEMEINDE);
      put(ElectionCategory.EP, GebietModel.EBENE_SB, GebietModel.GEBIETSART_STIMMBEZIRK);

      put(ElectionCategory.EK, GebietModel.EBENE_CSB, GebietModel.GEBIETSART_BUND);
      put(ElectionCategory.EK, GebietModel.EBENE_HSB, GebietModel.GEBIETSART_LAND);
      // PSB level is probably never needed
      put(ElectionCategory.EK, GebietModel.EBENE_PSB, GebietModel.GEBIETSART_STIMMBEZIRK);
      put(ElectionCategory.EK, GebietModel.EBENE_SB, GebietModel.GEBIETSART_STIMMBEZIRK);

      put(ElectionCategory.TK, GebietModel.EBENE_CSB, GebietModel.GEBIETSART_BUND);
      put(ElectionCategory.TK, GebietModel.EBENE_HSB, GebietModel.GEBIETSART_WAHLKREIS);
      put(ElectionCategory.TK, GebietModel.EBENE_PSB, GebietModel.GEBIETSART_GEMEINDE);
      put(ElectionCategory.TK, GebietModel.EBENE_SB, GebietModel.GEBIETSART_STIMMBEZIRK);

      put(ElectionCategory.GR, GebietModel.EBENE_CSB, GebietModel.GEBIETSART_GEMEINDE);
      put(ElectionCategory.GR, GebietModel.EBENE_HSB, GebietModel.GEBIETSART_GEMEINDE);
      put(ElectionCategory.GR, GebietModel.EBENE_PSB, GebietModel.GEBIETSART_GEMEINDE);
      put(ElectionCategory.GR, GebietModel.EBENE_SB, GebietModel.GEBIETSART_STIMMBEZIRK);

      put(ElectionCategory.ER, GebietModel.EBENE_CSB, GebietModel.GEBIETSART_INSELGEMEINDE);
      put(ElectionCategory.ER, GebietModel.EBENE_HSB, GebietModel.GEBIETSART_INSELGEMEINDE);
      put(ElectionCategory.ER, GebietModel.EBENE_PSB, GebietModel.GEBIETSART_INSELGEMEINDE);
      put(ElectionCategory.ER, GebietModel.EBENE_SB, GebietModel.GEBIETSART_STIMMBEZIRK);

      put(ElectionCategory.BC, GebietModel.EBENE_CSB, GebietModel.GEBIETSART_ORTSTEIL);
      put(ElectionCategory.BC, GebietModel.EBENE_HSB, GebietModel.GEBIETSART_ORTSTEIL);
      put(ElectionCategory.BC, GebietModel.EBENE_PSB, GebietModel.GEBIETSART_ORTSTEIL);
      put(ElectionCategory.BC, GebietModel.EBENE_SB, GebietModel.GEBIETSART_STIMMBEZIRK);

      put(ElectionCategory.GC, GebietModel.EBENE_CSB, GebietModel.GEBIETSART_ORTSTEIL);
      put(ElectionCategory.GC, GebietModel.EBENE_HSB, GebietModel.GEBIETSART_ORTSTEIL);
      put(ElectionCategory.GC, GebietModel.EBENE_PSB, GebietModel.GEBIETSART_ORTSTEIL);
      put(ElectionCategory.GC, GebietModel.EBENE_SB, GebietModel.GEBIETSART_STIMMBEZIRK);

      put(ElectionCategory.PS, GebietModel.EBENE_CSB, GebietModel.GEBIETSART_LAND);
      put(ElectionCategory.PS, GebietModel.EBENE_HSB, GebietModel.GEBIETSART_WAHLKREIS);
      put(ElectionCategory.PS, GebietModel.EBENE_PSB, GebietModel.GEBIETSART_GEMEINDE);
      put(ElectionCategory.PS, GebietModel.EBENE_SB, GebietModel.GEBIETSART_STIMMBEZIRK);

      put(ElectionCategory.AB, GebietModel.EBENE_CSB, GebietModel.GEBIETSART_ALGEMEEN_BESTUUR);
      put(ElectionCategory.AB, GebietModel.EBENE_HSB, GebietModel.GEBIETSART_WAHLKREIS);
      put(ElectionCategory.AB, GebietModel.EBENE_PSB, GebietModel.GEBIETSART_GEMEINDE);
      put(ElectionCategory.AB, GebietModel.EBENE_SB, GebietModel.GEBIETSART_STIMMBEZIRK);

      put(ElectionCategory.NR, GebietModel.EBENE_CSB, GebietModel.GEBIETSART_BUND);
      put(ElectionCategory.NR, GebietModel.EBENE_HSB, GebietModel.GEBIETSART_WAHLKREIS);
      put(ElectionCategory.NR, GebietModel.EBENE_PSB, GebietModel.GEBIETSART_GEMEINDE);
      put(ElectionCategory.NR, GebietModel.EBENE_SB, GebietModel.GEBIETSART_STIMMBEZIRK);

      put(ElectionCategory.PR, GebietModel.EBENE_HSB, GebietModel.GEBIETSART_LAND);
      put(ElectionCategory.PR, GebietModel.EBENE_PSB, GebietModel.GEBIETSART_GEMEINDE);
      put(ElectionCategory.PR, GebietModel.EBENE_SB, GebietModel.GEBIETSART_STIMMBEZIRK);

      put(ElectionCategory.LR, GebietModel.EBENE_PSB, GebietModel.GEBIETSART_GEMEINDE);
      put(ElectionCategory.LR, GebietModel.EBENE_SB, GebietModel.GEBIETSART_STIMMBEZIRK);

      put(ElectionCategory.IR, GebietModel.EBENE_PSB, GebietModel.GEBIETSART_INSELGEMEINDE);
      put(ElectionCategory.IR, GebietModel.EBENE_SB, GebietModel.GEBIETSART_STIMMBEZIRK);
    }

  };

  /**
   * Konstante für die Abbildung der Wahlart auf ihre Bezeichner
   */
  Map<ElectionCategory, String> WAHLART_KLARTEXT = new ConstantMap<ElectionCategory, String>() {
    {
      add(ElectionCategory.TK, "Tweede Kamer"); //$NON-NLS-1$
      add(ElectionCategory.EK, "Eerste Kamer"); //$NON-NLS-1$ 
      add(ElectionCategory.GR, "Gemeenteraad"); //$NON-NLS-1$
      add(ElectionCategory.ER, "Eilandsraad"); //$NON-NLS-1$
      add(ElectionCategory.BC, "Bestuurscommissie"); //$NON-NLS-1$
      add(ElectionCategory.GC, "Gebiedscommissie"); //$NON-NLS-1$
      add(ElectionCategory.PS, "Provinciale staten"); //$NON-NLS-1$
      add(ElectionCategory.AB, "Algemeen bestuur"); //$NON-NLS-1$
      add(ElectionCategory.EP, "Verkiezing Europees Parlement"); //$NON-NLS-1$
      add(ElectionCategory.NR, "National Referendum"); //$NON-NLS-1$
      add(ElectionCategory.PR, "Provincial Referendum"); //$NON-NLS-1$
      add(ElectionCategory.LR, "Lokaal Referendum"); //$NON-NLS-1$
      add(ElectionCategory.IR, "Lokaal Referendum"); //$NON-NLS-1$
    }
  };

  /**
   * Konstante für die Abbildung des Wahlmodus auf ihre Kurzbezeichner
   */
  Map<Integer, String> WAHLMODUS_KLARTEXT = new ConstantMap<Integer, String>() {
    {
      add(AbstractImportEML.MODE_DB_P4, "P4"); //$NON-NLS-1$
      add(AbstractImportEML.MODE_DB_P5, "P5"); //$NON-NLS-1$
    }
  };

  /* # Tabelle Wahl # */
  /**
   * Input with candidate results
   */
  int INPUT_MODE_COMPLETE = 1;

  /**
   * Input of group results only
   */
  int INPUT_MODE_GROUPS = 2;

}