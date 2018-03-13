package de.ivu.wahl.modell.impl;

/**
 * SchwellwertModelImpl
 *
 * @author cos@ivu.de  (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;

public class SchwellwertModelImpl extends BasicSchwellwertModelImpl {
  private static final long serialVersionUID = -8386988931049743932L;
  private static final Category LOGGER = Log4J.configure(SchwellwertModelImpl.class);
  static {
    LOGGER.info(Log4J.dumpVersion(SchwellwertModelImpl.class, Log4J
        .extractVersion("$Revision: 1.3 $"))); //$NON-NLS-1$
  }

  public SchwellwertModelImpl() {
    super();
  }

  public SchwellwertModelImpl(String id_Gebiet) {
    super(id_Gebiet);
  }

  public int getArtSchwellwert(String schwellwertKey) {
    for (int i = 0; i < SWERT_ALLG_KEYS.length; i++) {
      String bezeichnung = SWERT_ALLG_KEYS[i][0];
      if (bezeichnung.equals(schwellwertKey)) {
        return Integer.parseInt(SWERT_ALLG_KEYS[i][2]);
      }
    }
    return -1;
  }

  public String getKlartextSchwellwert(String schwellwertKey) {
    for (int i = 0; i < SWERT_ALLG_KEYS.length; i++) {
      String bezeichnung = SWERT_ALLG_KEYS[i][0];
      if (bezeichnung.equals(schwellwertKey)) {
        return SWERT_ALLG_KEYS[i][1];
      }
    }
    return null;
  }
}
