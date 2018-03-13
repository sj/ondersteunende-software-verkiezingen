/*
 * Created on Oct 29, 2008
 * Copyright (c) 2008 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import java.util.Map;

import de.ivu.wahl.modell.WahlModel;

/**
 * @author mur@ivu.de, IVU Traffic Technologies AG
 */
public class InitGuiCommandEP_P5 extends InitGuiCommand_P5 implements ApplicationBeanKonstanten {

  /**
   * @param jspMap Beziehung zwischen work-Kodierung und JSPs
   * @param gebietsartErfassungseinheitMax Gebietsart der hierarchisch <b>niedrigsten</b>
   *          Erfassungseinheit im Gebietsbaum
   */
  public InitGuiCommandEP_P5(Map<Integer, String> jspMap, int gebietsartErfassungseinheitMax) {
    super(jspMap, gebietsartErfassungseinheitMax);
  }

  @Override
  protected int getTopLevel() {
    return WahlModel.GEBIETE_EP_NL[0];
  }

  @Override
  protected String modelForProtocol() {
    return MODEL_P22_1;
  }

  @Override
  protected String getGebietErgebnisBundleString() {
    return "Gebiet_Ergebnis_BUND"; //$NON-NLS-1$
  }

  @Override
  protected void createCommandGebietErgebnisKandidat(InitGuiCommandHelper helper) {
    helper.setRights(null);
    helper.addCommand(Command.GEB_ERG_KAN,
        "Gebiet_Ergebnis_Kandidat", "Gebiet_Ergebnis_Kandidat_titel", "gebietErgebnisKandidat.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

}
