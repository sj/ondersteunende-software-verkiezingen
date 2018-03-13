/*
 * P4_CSB_ExportDirServiceImpl
 * 
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.mbean.exportdir;

import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;

/**
 * Management of the shadow export directory
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
@Service(objectName = "osv:service=P4_CSB_Export")
@Management(ExportDirService.class)
public class P4_CSB__ExportDirServiceImpl extends AbstractExportDirServiceImpl {
  // only metadata differs
}