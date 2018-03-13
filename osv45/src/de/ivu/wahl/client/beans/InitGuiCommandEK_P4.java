/*
 * InitGuiCommandTweedeKamerBund
 * 
 * Created on Oct 29, 2008
 * Copyright (c) 2008 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import static de.ivu.wahl.modell.GebietModel.GEBIETSART_LAND;
import static de.ivu.wahl.modell.GebietModel.GEBIETSART_STIMMBEZIRK;

import java.util.Map;

import de.ivu.wahl.client.util.GUICommand;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.WahlModel;

/**
 * @author mur@ivu.de, IVU Traffic Technologies AG
 */
public class InitGuiCommandEK_P4 extends InitGuiCommand_P4 implements ApplicationBeanKonstanten {
  protected static final int[][] WAHL_ART_LEVEL_EK_CSB = {{GEBIETSART_LAND}};
  protected static final int[][] WAHL_ART_LEVEL_EK_HSB = {{GEBIETSART_STIMMBEZIRK}};

  /**
   * @param jspMap Beziehung zwischen work-Kodierung und JSPs
   * @param gebietsartErfassungseinheitMax Gebietsart der hierarchisch <b>niedrigsten</b>
   *          Erfassungseinheit im Gebietsbaum
   */
  public InitGuiCommandEK_P4(Map<Integer, String> jspMap,
      int gebietsartErfassungseinheitMax,
      int ebene) {
    super(jspMap, gebietsartErfassungseinheitMax, ebene);
  }

  @Override
  protected boolean isEk() {
    return true;
  }

  @Override
  protected int[] getGebiete() {
    return WahlModel.GEBIETE_EERSTE_KAMER;
  }

  @Override
  protected int[][] getWahlartLevels() {
    if (_ebene == GebietModel.EBENE_HSB) {
      return WAHL_ART_LEVEL_EK_HSB;
    } else if (_ebene == GebietModel.EBENE_CSB) {
      return WAHL_ART_LEVEL_EK_CSB;
    } else {
      return WAHL_ART_LEVEL_EK_CSB;
    }
  }

  @Override
  protected void initCommandGebietErgebnis(GUICommand cmd) {
    cmd.setBezeichnung(GebietModel.GEBIETSART_BUND, getBundleString("Gebiet_Ergebnis_BUND")); //$NON-NLS-1$
    cmd.setBezeichnung(LEVEL_WKR, getBundleString("Gebiet_Ergebnis_LAND")); //$NON-NLS-1$
    cmd.setBezeichnung(LEVEL_GEM, getBundleString("Gebiet_Ergebnis_LAND")); //$NON-NLS-1$
  }

  @Override
  protected void createCommandGebietErgebnisKandidat(InitGuiCommandHelper helper) {
    GUICommand cmd;

    helper.setRights(null);
    cmd = helper.addCommand(Command.GEB_ERG_KAN,
        "Gebiet_Ergebnis_Kandidat", "Gebiet_Ergebnis_Kandidat_titel", "gebietErgebnisKandidat.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    cmd.setNurGebiete(true);
    // if the Level is CSB the list of candidates are defined for a deeper Level
    if (_ebene == GebietModel.EBENE_CSB) {
      cmd.setNurErfassungseinheit(true);
    }

    if (_ebene == GebietModel.EBENE_CSB) {
      cmd = helper
          .addCommand(Command.GEB_ERG_KAN_ZUS,
              "Gebiet_Ergebnis_Kandidat", "Gebiet_Ergebnis_Kandidat_titel", "gebietErgebnisKandidatZusammenfassung.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      // if the Level is CSB the list of candidates are defined for a deeper Level
      cmd.setNurWurzelgebiet(true);
    }
  }

  @Override
  protected void initLevelGemeinde(Map<String, String> jspLevelWorkName,
      GUICommandList[] befehleInitial) {
    // For EK elections, P4_HSB shall not have the function to import EML 510b
    if (_ebene == GebietModel.EBENE_CSB) {
      super.initLevelGemeinde(jspLevelWorkName, befehleInitial);
    }
  }

}
