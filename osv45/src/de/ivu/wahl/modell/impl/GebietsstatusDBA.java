package de.ivu.wahl.modell.impl;

/**
 * GebietsstatusDBA
 *
 * @author cos@ivu.de  (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;

public class GebietsstatusDBA extends BasicGebietsstatusDBA {
  private static final long serialVersionUID = -6663530551967135010L;
  private static final Category LOGGER = Log4J.configure(GebietsstatusDBA.class);
  static {
    LOGGER
        .info(Log4J.dumpVersion(GebietsstatusDBA.class, Log4J.extractVersion("$Revision: 1.5 $"))); //$NON-NLS-1$
  }
}
