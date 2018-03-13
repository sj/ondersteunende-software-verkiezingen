package de.ivu.wahl.modell;

import de.ivu.wahl.util.BundleHelper;
import de.ivu.wahl.wus.electioncategory.WaterBoardNamingType;

/**
 * NOTE: The id's must reflect the region hierachie: A region higher in the hierachie must have
 * smaller id. The id's must be consecutive starting from 0, because of strange uses of
 * GebietModel.ANZAHL_GEBIETSARTEN.
 * 
 * @author Joachim Nottebaum
 */
public enum Gebietsart {
  /** Nederlands */
  BUND(0, "GebietModel.Klartext_Bund", "GebietModel.Klartext_Export_Bund"), //$NON-NLS-1$ //$NON-NLS-2$

  /** Province */
  LAND(1, "GebietModel.Klartext_Bundesland", "GebietModel.Klartext_Export_Land"), //$NON-NLS-1$ //$NON-NLS-2$

  /** Water council */
  ALGEMEEN_BESTUUR(2, "GebietModel.Klartext_Wasserkreis", "GebietModel.Klartext_Export_Wasserkreis"), //$NON-NLS-1$ //$NON-NLS-2$

  WAHLKREIS(3, "GebietModel.Klartext_Wahlkreis", "GebietModel.Klartext_Export_Wahlkreis"), //$NON-NLS-1$ //$NON-NLS-2$

  GEMEINDE(4, "GebietModel.Klartext_Gemeinde", "GebietModel.Klartext_Export_Gemeinde"), //$NON-NLS-1$ //$NON-NLS-2$

  INSELGEMEINDE(5,
      "GebietModel.Klartext_Inselgemeinde", "GebietModel.Klartext_Export_Inselgemeinde"), //$NON-NLS-1$ //$NON-NLS-2$

  ORTSTEIL(6, "GebietModel.Klartext_Ortsteil", "GebietModel.Klartext_Export_Ortsteil"), //$NON-NLS-1$ //$NON-NLS-2$

  STIMMBEZIRK(7, "GebietModel.Klartext_Stimmbezirk", "GebietModel.Klartext_Export_Stimmbezirk"); //$NON-NLS-1$ //$NON-NLS-2$

  private final int id;
  private final String klartext;
  private final String klartextExport;

  @SuppressWarnings("hiding")
  private Gebietsart(int id, String klartextKey, String klartextExportKey) {
    this.id = id;
    this.klartext = BundleHelper.getBundleString(klartextKey);
    this.klartextExport = BundleHelper.getBundleString(klartextExportKey);
  }

  public int getId() {
    return id;
  }

  public String getKlartext() {
    return klartext;
  }

  /**
   * @return Konstante für die Klartextbezeichnungen der Gebietsarten für Export/Import
   */
  public String getKlartextExport() {
    return klartextExport;
  }

  public static Gebietsart byId(int id) {
    for (Gebietsart gebietsart : Gebietsart.values()) {
      if (id == gebietsart.getId()) {
        return gebietsart;
      }
    }
    return null;
  }

  public static String getKlartext(int id) {
    return byId(id).getKlartext();
  }

  public static String getGebietsartKlartext(GebietModel gebiet) {
    if (gebiet.getGebietsart() == Gebietsart.ALGEMEEN_BESTUUR.getId()) {
      return WaterBoardNamingType.getPrefixForWaterBoardElection(gebiet.getNummer());
    } else {
      return getKlartext(gebiet.getGebietsart());
    }
  }

}
