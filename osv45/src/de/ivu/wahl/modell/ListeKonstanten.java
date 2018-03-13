/*
 * ListeKonstanten
 * 
 * Created on 06.01.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell;

import de.ivu.wahl.util.BundleHelper;

/**
 * @author ugo@ivu.de, IVU Traffic Technologies AG

 */
public class ListeKonstanten {

  public static enum Listentyp {
    EINZELLISTE, LISTENKOMBINATION, LISTE_IN_KOMBINATION;

    public String name;

    Listentyp() {
      this.name = BundleHelper.getBundleString("ListeKonstanten." + name()); //$NON-NLS-1$
    }
  }
}
