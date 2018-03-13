/*
 * InitGuiCommandGemeente_P4
 * 
 * Created on Oct 29, 2008
 * Copyright (c) 2008 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import static de.ivu.wahl.modell.GebietModel.GEBIETSART_GEMEINDE;

import java.util.Map;

/**
 * @author mur@ivu.de, IVU Traffic Technologies AG
 */
public class InitGuiCommandGemeente_P4 extends InitGuiCommandGR_DR_P4
    implements
      ApplicationBeanKonstanten {

  @Override
  protected int getTopLevel() {
    return GEBIETSART_GEMEINDE;
  }

  /**
   * @param jspMap Beziehung zwischen work-Kodierung und JSPs
   * @param gebietsartErfassungseinheitMax Gebietsart der hierarchisch <b>niedrigsten</b>
   *          Erfassungseinheit im Gebietsbaum
   */
  public InitGuiCommandGemeente_P4(Map<Integer, String> jspMap,
      int gebietsartErfassungseinheitMax,
      int ebene) {
    super(jspMap, gebietsartErfassungseinheitMax, ebene);
  }

}
