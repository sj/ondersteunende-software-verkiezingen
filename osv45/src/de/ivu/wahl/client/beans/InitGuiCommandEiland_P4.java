/*
 * Created on 09.02.2011
 * Copyright (c) 2011 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import static de.ivu.wahl.modell.GebietModel.GEBIETSART_INSELGEMEINDE;

import java.util.Map;

/**
 * @author mur@ivu.de, IVU Traffic Technologies AG
 */
public class InitGuiCommandEiland_P4 extends InitGuiCommandGR_DR_P4
    implements
      ApplicationBeanKonstanten {

  @Override
  protected int getTopLevel() {
    return GEBIETSART_INSELGEMEINDE;
  }

  /**
   * @param jspMap Beziehung zwischen work-Kodierung und JSPs
   * @param gebietsartErfassungseinheitMax Gebietsart der hierarchisch <b>niedrigsten</b>
   *          Erfassungseinheit im Gebietsbaum
   */
  public InitGuiCommandEiland_P4(Map<Integer, String> jspMap,
      int gebietsartErfassungseinheitMax,
      int ebene) {
    super(jspMap, gebietsartErfassungseinheitMax, ebene);
  }

}
