/*
 * ListType
 * 
 * Created on 13.08.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

public enum ListType {
  LIST_GROUP("lijstengroep"), //$NON-NLS-1$

  STEL("stel gelijkluidende lijsten"), //$NON-NLS-1$

  INDEPENDENT("op zichzelf staande lijst"); //$NON-NLS-1$

  private String eml;

  @SuppressWarnings("hiding")
  private ListType(String eml) {
    this.eml = eml;
  }

  public String getEml() {
    return eml;
  }
}
