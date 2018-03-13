/*
 * AnmeldeFehlversuchException
 * 
 * Created on 23.04.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.anwender;

/**
 * @author tdu@ivu.de, IVU Traffic Technologies AG
 */
public class AnmeldeFehlversuchException extends Exception {

  private static final long serialVersionUID = 3058525907596187085L;

  private int verbleibendeVersuche;

  /**
   * Constructor
   * 
   * @param verbleibendeAnmeldeversuche1
   */
  public AnmeldeFehlversuchException(final int verbleibendeAnmeldeversuche) {
    this.setVerbleibendeAnmeldeversuche(verbleibendeAnmeldeversuche);
  }

  public void setVerbleibendeAnmeldeversuche(final int verbleibendeAnmeldeversuche) {
    this.verbleibendeVersuche = verbleibendeAnmeldeversuche;
  }

  public int getVerbleibendeAnmeldeversuche() {
    return verbleibendeVersuche;
  }

}
