/*
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import java.util.Map;

import de.ivu.wahl.client.util.GUICommand;
import de.ivu.wahl.modell.GebietModel;

/**
 * Common superclass of InitGuiCommandGemeente_P4 and InitGuiCommandDeelraad_P4.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public abstract class InitGuiCommandGR_DR_P4 extends InitGuiCommand_P4
    implements
      ApplicationBeanKonstanten {

  @Override
  protected abstract int getTopLevel();

  @Override
  protected boolean isGrOrBcOrGc() {
    return true;
  }

  @Override
  protected int[] getGebiete() {
    return new int[]{0, 0, GebietModel.GEBIETSART_GEMEINDE, GebietModel.GEBIETSART_STIMMBEZIRK};
  }

  /**
   * @param jspMap Beziehung zwischen work-Kodierung und JSPs
   * @param ebene
   * @param gebietsartErfassungseinheitMax Gebietsart der hierarchisch <b>niedrigsten</b>
   *          Erfassungseinheit im Gebietsbaum
   */
  public InitGuiCommandGR_DR_P4(Map<Integer, String> jspMap,
      int gebietsartErfassungseinheitMax,
      int ebene) {
    super(jspMap, gebietsartErfassungseinheitMax, ebene);
  }

  @Override
  protected void initCommandGebietErgebnis(GUICommand cmd) {
    cmd.setBezeichnung(LEVEL_GEM, getBundleString("Gebiet_Ergebnis_GEM")); //$NON-NLS-1$
    cmd.setBezeichnung(LEVEL_STIMMBEZ, getBundleString("Gebiet_Ergebnis_STIMMBEZ")); //$NON-NLS-1$
  }

  @Override
  protected void createCommandGebietErgebnisKandidat(InitGuiCommandHelper helper) {
    GUICommand cmd = helper.addCommand(Command.GEB_ERG_KAN,
        "Gebiet_Ergebnis_Kandidat", "Gebiet_Ergebnis_Kandidat_titel", "gebietErgebnisKandidat.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    cmd.setNurGebiete(true);
    // if the Level is CSB, the list of candidates are defined for a deeper Level
    if (_ebene == GebietModel.EBENE_CSB) {
      cmd.setNurErfassungseinheit(true);
    }
  }

}
