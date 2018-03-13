/*
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import static de.ivu.wahl.modell.GebietModel.GEBIETSART_GEMEINDE;

import java.util.Map;

/**
 * Common superclass of InitGuiCommandGemeente_P5 and InitGuiCommandDeelraad_P5.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public abstract class InitGuiCommandGR_DR_P5 extends InitGuiCommand_P5
    implements
      ApplicationBeanKonstanten {

  private static final int[][] WAHL_ART_LEVEL = {{GEBIETSART_GEMEINDE}};

  private static final int[][] ADMIN_WAHL_ART_LEVEL = {{GEBIETSART_GEMEINDE}};

  @Override
  protected String getLowerGebietErgebnisBundleString() {
    return "Gebiet_Ergebnis_STIMMBEZ"; //$NON-NLS-1$
  }

  /**
   * @param jspMap Beziehung zwischen work-Kodierung und JSPs
   * @param gebietsartErfassungseinheitMax Gebietsart der hierarchisch <b>niedrigsten</b>
   *          Erfassungseinheit im Gebietsbaum
   */
  public InitGuiCommandGR_DR_P5(Map<Integer, String> jspMap, int gebietsartErfassungseinheitMax) {
    super(jspMap, gebietsartErfassungseinheitMax);
  }

  @Override
  protected int[][] getWahlartLevels() {
    return WAHL_ART_LEVEL;
  }

  @Override
  protected int[][] getAdminWahlartLevels() {
    return ADMIN_WAHL_ART_LEVEL;
  }

  @Override
  protected String modelForProtocol() {
    return MODEL_P22_2;
  }

  @Override
  protected String getGebietErgebnisBundleString() {
    return "Gebiet_Ergebnis_GEM"; //$NON-NLS-1$
  }

  @Override
  protected void createCommandGebietErgebnisKandidat(InitGuiCommandHelper helper) {
    helper.setRights(null);
    helper.addCommand(Command.GEB_ERG_KAN,
        "Gebiet_Ergebnis_Kandidat", "Gebiet_Ergebnis_Kandidat_titel", "gebietErgebnisKandidat.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

}
