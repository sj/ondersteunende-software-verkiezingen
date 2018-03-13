/*
 * P4_PSB_DBInitServiceImpl
 * 
 * Created on 03.04.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.mbean.dbinit;

import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;

/**
 * Database Initialisation for P4_PSB
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
@Service(objectName = "osv:service=P4_PSB_DBInitService")
@Management(DBInitService.class)
public class P4_PSB__DBInitServiceImpl extends AbstractDBInitServiceImpl {
  @Override
  public void start() throws Exception {
    super.start();
  }
}
