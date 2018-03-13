/*
 * Created on 13.10.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell;

import de.ivu.wahl.SystemInfo;

/**
 * Indirect access to SystemInfo.getSystemInfo(). This is only needed for test cases where the code
 * runs outside an EJB container. Initialising the SystemInfo raises an exception. This is cought.
 * Instead, the values for wahlEbene and ebenenKlartext are set like in P5.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
final class GruppeAllgemeinEbeneProvider {
  private static String EBENE_KLARTEXT = null;
  private static int WAHL_EBENE;

  static int getWahlEbene() {
    if (EBENE_KLARTEXT == null) {
      init();
    }
    return WAHL_EBENE;
  }

  static String getEbenenKlartext() {
    if (EBENE_KLARTEXT == null) {
      init();
    }
    return EBENE_KLARTEXT;
  }

  private static void init() {
    try {
      WAHL_EBENE = SystemInfo.getSystemInfo().getWahlEbene();
      EBENE_KLARTEXT = SystemInfo.getSystemInfo().getEbenenklartext();
    } catch (Throwable e) {
      WAHL_EBENE = GebietModel.EBENE_CSB;
      EBENE_KLARTEXT = "CSB"; //$NON-NLS-1$;
    }
  }

  private GruppeAllgemeinEbeneProvider() {
    // hide constructor
  }
}
