/*
 * ErgebniseingangModelKonstantenMap
 * 
 * Created on 30.11.2005
 * Copyright (c) 2005 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import de.ivu.wahl.modell.ErgebniseingangKonstanten;

public class ErgebniseingangModelKonstantenMap extends KonstantenWrapperMap {

  /** long */
  private static final long serialVersionUID = 345414126234732273L;

  public ErgebniseingangModelKonstantenMap() {
    super(ErgebniseingangKonstanten.class);
  }
}
