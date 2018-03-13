/*
 * AuthorityLevel
 * 
 * Created on 06.10.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell;

/**
 * Enum type for the authorities CSB, HSB, PSB and SB.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public enum AuthorityLevel {
  // Zentraler Wahlausschuss
  EBENE_CSB(GebietModel.EBENE_CSB, "CSB"), //$NON-NLS-1$

  // Hauptwahlausschuss
  EBENE_HSB(GebietModel.EBENE_HSB, "HSB"), //$NON-NLS-1$

  // Gemeinde
  EBENE_PSB(GebietModel.EBENE_PSB, "PSB"), //$NON-NLS-1$

  EBENE_SB(GebietModel.EBENE_SB, "SB"); //$NON-NLS-1$

  private final int id;
  private final String shortName;

  @SuppressWarnings("hiding")
  AuthorityLevel(int id, String shortName) {
    this.id = id;
    this.shortName = shortName;
  }

  public int getId() {
    return id;
  }

  public String getShortName() {
    return shortName;
  }

  public static AuthorityLevel byId(int id) {
    for (AuthorityLevel authorityLevel : values()) {
      if (id == authorityLevel.getId()) {
        return authorityLevel;
      }
    }
    return null;
  }
}
