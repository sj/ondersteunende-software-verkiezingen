/*
 * P4_PSB_4_DBInitServiceImpl
 * 
 * Created on 21.11.2014
 * Copyright (c) 2014 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.mbean.dbinit;

import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;

/**
 * Database Initialisation for P4_PSB_4
 * 
 * @author Joachim Nottebaum, IVU Traffic Technologies AG
 */
@Service(objectName = "osv:service=P4_PSB_4_DBInitService")
@Management(DBInitService.class)
public class P4_PSB_4__DBInitServiceImpl extends AbstractDBInitServiceImpl {
  @Override
  public void start() throws Exception {
    super.start();
  }
}
