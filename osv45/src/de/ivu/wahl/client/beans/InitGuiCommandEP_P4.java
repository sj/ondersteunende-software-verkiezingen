/*
 * Created on Oct 29, 2008
 * Copyright (c) 2008 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import java.util.Map;

import de.ivu.wahl.client.util.GUICommand;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.WahlModel;

/**
 * @author mur@ivu.de, IVU Traffic Technologies AG
 */
public class InitGuiCommandEP_P4 extends InitGuiCommand_P4 implements ApplicationBeanKonstanten {

  /**
   * @param jspMap Beziehung zwischen work-Kodierung und JSPs
   * @param gebietsartErfassungseinheitMax Gebietsart der hierarchisch <b>niedrigsten</b>
   *          Erfassungseinheit im Gebietsbaum
   */
  public InitGuiCommandEP_P4(Map<Integer, String> jspMap,
      int gebietsartErfassungseinheitMax,
      int ebene) {
    super(jspMap, gebietsartErfassungseinheitMax, ebene);
  }

  @Override
  protected int[] getGebiete() {
    return WahlModel.GEBIETE_EP_NL;
  }

  @Override
  protected void initCommandGebietErgebnis(GUICommand cmd) {
    cmd.setBezeichnung(GebietModel.GEBIETSART_BUND, getBundleString("Gebiet_Ergebnis_BUND")); //$NON-NLS-1$
    cmd.setBezeichnung(LEVEL_WKR, getBundleString("Gebiet_Ergebnis_WK")); //$NON-NLS-1$
    cmd.setBezeichnung(LEVEL_GEM, getBundleString("Gebiet_Ergebnis_GEM")); //$NON-NLS-1$
    cmd.setBezeichnung(LEVEL_STIMMBEZ, getBundleString("Gebiet_Ergebnis_STIMMBEZ")); //$NON-NLS-1$
  }

  @Override
  protected void createCommandGebietErgebnisKandidat(InitGuiCommandHelper helper) {
    helper.setRights(null);
    GUICommand cmd = helper.addCommand(Command.GEB_ERG_KAN,
        "Gebiet_Ergebnis_Kandidat", "Gebiet_Ergebnis_Kandidat_titel", "gebietErgebnisKandidat.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    cmd.setNurGebiete(true);
  }

}
