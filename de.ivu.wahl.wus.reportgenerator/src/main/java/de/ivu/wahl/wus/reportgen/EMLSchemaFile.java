/*
 * Created on 19.02.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

/**
 * All possible values for EML Schema files
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public enum EMLSchemaFile {
  EMLSchema210("210-nomination-v5-0.xsd"), //$NON-NLS-1$

  EMLSchema230("230-candidatelist-v5-0.xsd"), //$NON-NLS-1$

  EMLSchema510("510-count-v5-0.xsd"), //$NON-NLS-1$

  EMLSchema520("520-result-v5-0.xsd"), //$NON-NLS-1$

  EMLSchema630("630-optionslist-v5-0.xsd"); //$NON-NLS-1$

  private final String filename;

  private EMLSchemaFile(String filename) {
    this.filename = filename;
  }

  public String getFilename() {
    return filename;
  }

}
