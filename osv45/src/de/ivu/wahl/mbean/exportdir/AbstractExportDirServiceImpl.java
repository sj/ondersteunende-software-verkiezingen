/*
 * AbstractExportDirServiceImpl
 * 
 * Created on 27.03.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.mbean.exportdir;

import org.jboss.annotation.ejb.Depends;
import org.jboss.logging.Logger;

/**
 * Management of the shadow export directory
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
@Depends({"jboss.j2ee:module=osv3.jar,service=EJB3", "jboss.web:service=WebServer"})
public abstract class AbstractExportDirServiceImpl implements ExportDirService {

  private static final Logger LOGGER = Logger.getLogger(AbstractExportDirServiceImpl.class);
  private ExportDirImpl _exportDirImpl;

  public void start() throws Exception {
    _exportDirImpl.start();
  }

  public void stop() throws Exception {
    _exportDirImpl.stop();
  }

  public void create() throws Exception {
    _exportDirImpl = new ExportDirImpl(LOGGER);
  }

  public void destroy() throws Exception {
    _exportDirImpl = null;
  }
}