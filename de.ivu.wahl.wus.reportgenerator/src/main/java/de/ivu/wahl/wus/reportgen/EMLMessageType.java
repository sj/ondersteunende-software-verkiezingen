/*
 * Created on 19.02.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

public enum EMLMessageType {
  EML210("210", EMLSchemaFile.EMLSchema210), //$NON-NLS-1$

  EML230cFirstMeeting("230c", EMLSchemaFile.EMLSchema230), //$NON-NLS-1$

  EML230b("230b", EMLSchemaFile.EMLSchema230), //$NON-NLS-1$

  EML230c("230c", EMLSchemaFile.EMLSchema230), //$NON-NLS-1$

  EML510a("510a", EMLSchemaFile.EMLSchema510), //$NON-NLS-1$

  EML510b("510b", EMLSchemaFile.EMLSchema510), //$NON-NLS-1$

  EML510c("510c", EMLSchemaFile.EMLSchema510), //$NON-NLS-1$

  EML510d("510d", EMLSchemaFile.EMLSchema510), //$NON-NLS-1$

  EML520("520", EMLSchemaFile.EMLSchema520), //$NON-NLS-1$

  EML630("630", EMLSchemaFile.EMLSchema630); //$NON-NLS-1$

  private final String id;
  private final EMLSchemaFile emlSchemaFile;

  private EMLMessageType(String id, EMLSchemaFile emlSchemaFile) {
    this.id = id;
    this.emlSchemaFile = emlSchemaFile;
  }

  public static EMLSchemaFile getEMLSchemaFileById(String id) {
    EMLMessageType messageType = byId(id);
    return messageType == null ? null : messageType.emlSchemaFile;
  }

  public static EMLMessageType byId(String id) {
    EMLMessageType[] values = EMLMessageType.values();
    for (EMLMessageType messageType : values) {
      if (messageType.id.equals(id)) {
        return messageType;
      }
    }
    return null;
  }

}
