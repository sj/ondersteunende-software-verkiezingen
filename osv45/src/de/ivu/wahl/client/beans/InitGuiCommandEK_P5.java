/*
 * Created on 06.10.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import static de.ivu.wahl.modell.GebietModel.GEBIETSART_LAND;

import java.util.Map;

import de.ivu.wahl.modell.WahlModel;

/**
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class InitGuiCommandEK_P5 extends InitGuiCommand_P5 implements ApplicationBeanKonstanten {
  private static final int[][] WAHL_ART_LEVEL = {{GEBIETSART_LAND}};

  /**
   * @param jspMap Beziehung zwischen work-Kodierung und JSPs
   * @param gebietsartErfassungseinheitMax Gebietsart der hierarchisch <b>niedrigsten</b>
   *          Erfassungseinheit im Gebietsbaum
   */
  public InitGuiCommandEK_P5(Map<Integer, String> jspMap, int gebietsartErfassungseinheitMax) {
    super(jspMap, gebietsartErfassungseinheitMax);
  }

  @Override
  protected int getTopLevel() {
    return WahlModel.GEBIETE_EERSTE_KAMER[0];
  }

  @Override
  protected int[][] getWahlartLevels() {
    return WAHL_ART_LEVEL;
  }

  @Override
  protected boolean isEKElection() {
    return true;
  }

  @Override
  protected String modelForProtocol() {
    return MODEL_U16;
  }

  @Override
  protected String getGebietErgebnisBundleString() {
    return "Gebiet_Ergebnis_BUND"; //$NON-NLS-1$
  }

  @Override
  protected void createCommandGebietErgebnisKandidat(InitGuiCommandHelper helper) {
    helper.setRights(null);
    helper
        .addCommand(Command.GEB_ERG_KAN_ZUS,
            "Gebiet_Ergebnis_Kandidat", "Gebiet_Ergebnis_Kandidat_titel", "gebietErgebnisKandidatZusammenfassungEK.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

}
