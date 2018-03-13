/*
 * Copyright (c) 2014 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import java.util.Map;

import de.ivu.wahl.modell.WahlModel;

/**
 * @author Joachim Nottebaum
 */
public class InitGuiCommandAB_P5 extends InitGuiCommand_P5 implements ApplicationBeanKonstanten {

  /**
   * @param jspMap Beziehung zwischen work-Kodierung und JSPs
   * @param gebietsartErfassungseinheitMax Gebietsart der hierarchisch <b>niedrigsten</b>
   *          Erfassungseinheit im Gebietsbaum
   */
  public InitGuiCommandAB_P5(Map<Integer, String> jspMap, int gebietsartErfassungseinheitMax) {
    super(jspMap, gebietsartErfassungseinheitMax);
  }

  @Override
  protected int getTopLevel() {
    return WahlModel.GEBIETE_ALGEMEEN_BESTUUR_NL[0];
  }

  @Override
  protected String modelForProtocol() {
    return MODEL_P22_2;
  }

  @Override
  protected String getGebietErgebnisBundleString() {
    return "Gebiet_Ergebnis_WATERSCHAP"; //$NON-NLS-1$
  }

  @Override
  protected void createCommandGebietErgebnisKandidat(InitGuiCommandHelper helper) {
    helper.setRights(null);
    helper
        .addCommand(Command.GEB_ERG_KAN_ZUS,
            "Gebiet_Ergebnis_Kandidat", "Gebiet_Ergebnis_Kandidat_titel", "gebietErgebnisKandidatZusammenfassung.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }
}
