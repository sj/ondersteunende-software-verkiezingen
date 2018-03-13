/*
 * AnteilWertLeer
 * 
 * Copyright (c) 2002-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.auswertung.erg;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;

/**
 * Leerer AnteilWert zur Markierung nicht existenter Eintr�ge
 * 
 * @author cos@ivu.de - IVU Traffic Technologies AG
 */
public class AnteilWertLeer extends AnteilWert {
  private static final long serialVersionUID = -2278980895498167847L;

  private static final Category LOGGER = Log4J.configure(AnteilWertLeer.class);

  static {
    LOGGER.info(Log4J.dumpVersion(AnteilWertLeer.class, Log4J.extractVersion("$Revision: 1.3 $"))); //$NON-NLS-1$
  }

  private static final String NOT_MODIFIABLE = "This value cannot be changed"; //$NON-NLS-1$

  public AnteilWertLeer() {
    super(-1, -1, -1, -1);
  }

  @Override
  public void setBasis(int aktBasis, int vglBasis) {
    throw new RuntimeException(NOT_MODIFIABLE);
  }

  @Override
  public void setAktWert(int wert) {
    throw new RuntimeException(NOT_MODIFIABLE);
  }

  @Override
  public void plus(int akt, int vgl) {
    throw new RuntimeException(NOT_MODIFIABLE);
  }

  @Override
  public void plus(Wert w) {
    throw new RuntimeException(NOT_MODIFIABLE);
  }
}