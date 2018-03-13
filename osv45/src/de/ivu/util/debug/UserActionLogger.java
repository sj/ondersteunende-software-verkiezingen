/*
 * UserActionLogger
 * 
 * Created on 05.04.2011
 * Copyright (c) 2011 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.util.debug;

import org.apache.log4j.Category;

import de.ivu.wahl.Konstanten;
import de.ivu.wahl.wus.loggerinterface.IUserActionLogger;

/**
 * @author tdu@ivu.de, IVU Traffic Technologies AG
 */
public class UserActionLogger implements IUserActionLogger {

  private static final Category APP_LOGGER = Log4J.configure(Konstanten.APPLOG);

  public UserActionLogger() {
    //
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.wus.loggerinterface.IUserActionLogger#logInfo(java.lang.Object)
   */
  @Override
  public void logInfo(Object message) {
    APP_LOGGER.info(message);
  }

}
