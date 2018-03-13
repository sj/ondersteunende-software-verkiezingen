/*
 * ExportDirImpl
 * 
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.mbean.exportdir;

import static de.ivu.ejb.EJBUtil.lookupLocal;
import static de.ivu.wahl.Konstanten.PROP_EXPORT_FORMULAR_DIR;
import static java.lang.Thread.sleep;
import static org.apache.catalina.Lifecycle.DESTROY_EVENT;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.jboss.logging.Logger;

import de.ivu.ejb.jboss.JMXInvoker;
import de.ivu.ejb.jboss.JMXInvokerBean;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.SystemInfo;
import de.ivu.wahl.admin.PropertyHandling;
import de.ivu.wahl.admin.PropertyHandlingBean;

/**
 * Management of the shadow export directory
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class ExportDirImpl implements Runnable {

  private static final Object[] EMPTY_OBJECT_ARRAY = new Object[]{};

  private final Logger _logger;

  private final JMXInvoker _jmxInvoker = lookupLocal(JMXInvokerBean.class.getSimpleName());
  private final PropertyHandling _propertyHandling = lookupLocal(PropertyHandlingBean.class
      .getSimpleName());

  /** Base path for export */
  protected volatile File _exportPath;
  private volatile String _contextObjectName;
  private volatile boolean _stopped = false;
  private Thread _thread;

  /**
   * Constructor
   * 
   * @param logger
   */
  public ExportDirImpl(Logger logger) {
    _logger = logger;
  }

  /**
   * Creates a context for the export directory if one is set; it changes the context if the set
   * directory changes
   */
  public void initExportDirContext() {
    try {
      File exportPath = _propertyHandling.getFileProperty(PROP_EXPORT_FORMULAR_DIR);
      File styleSheetDir = _propertyHandling
          .getFileProperty(Konstanten.PROP_LISTING_STYLESHEET_FILE);
      if (exportPath != null) {
        if (!exportPath.equals(_exportPath)) {
          // internen Context auf den Path legen
          synchronized (this) {
            removeExportDirContext();
            if (exportPath.isDirectory()) {
              final SystemInfo systemInfo = SystemInfo.getSystemInfo();
              String exportDir = "/" + systemInfo.getModusklartext() + "_" //$NON-NLS-1$ //$NON-NLS-2$
                  + systemInfo.getEbenenklartext() + systemInfo.getInstallationSuffix()
                  + "-export-map"; //$NON-NLS-1$
              Context context = (Context) _jmxInvoker.invoke("jboss.web:type=server", //$NON-NLS-1$
                  "createContext", //$NON-NLS-1$
                  new Object[]{exportDir, exportPath.getAbsolutePath()});

              final String servletName = "d2"; //$NON-NLS-1$
              Wrapper wrapper = context.createWrapper();
              wrapper.setServletClass("org.apache.catalina.servlets.DefaultServlet"); //$NON-NLS-1$
              wrapper.setName(servletName);
              wrapper.addInitParameter("listings", "true"); //$NON-NLS-1$ //$NON-NLS-2$
              wrapper.addInitParameter("globalXsltFile", styleSheetDir.getAbsolutePath()); //$NON-NLS-1$
              context.addChild(wrapper);
              context.addServletMapping("/*", servletName); //$NON-NLS-1$

              _jmxInvoker.invoke("jboss.web:host=localhost,type=Host", //$NON-NLS-1$
                  "addChild", //$NON-NLS-1$
                  new Object[]{context},
                  new String[]{"org.apache.catalina.Container"}); //$NON-NLS-1$
              _contextObjectName = context.getObjectName();
            }
          }
          if (exportPath.isDirectory()) {
            _exportPath = exportPath;
            _logger.info("Export path: " + _exportPath); //$NON-NLS-1$
          } else {
            exportPath.mkdirs();
            if (!exportPath.isDirectory()) {
              _logger.info("Export path: '" + exportPath + "' does not point to a valid directory"); //$NON-NLS-1$ //$NON-NLS-2$
            }
          }
        }
      } else if (_exportPath != null) {
        _exportPath = null;
        _logger.info("Export path reset"); //$NON-NLS-1$
      }
    } catch (Exception e) {
      // then we use the old path if there is one
      _logger.error(e, e);
    }
  }

  /**
   * Removes the context for the export directory
   */
  public void removeExportDirContext() {
    if (_contextObjectName != null) {
      try {
        _jmxInvoker.invoke(_contextObjectName, DESTROY_EVENT, EMPTY_OBJECT_ARRAY);
      } catch (Exception e) {
        _logger.warn(e);
      }
      _contextObjectName = null;
    }
  }

  public void start() {
    _logger.info("Starting Export-Directory"); //$NON-NLS-1$
    _thread = new Thread(this);
    _thread.start();
  }

  public void stop() {
    _logger.info("Stopping Export-Directory"); //$NON-NLS-1$
    _stopped = true;
    try {
      _thread.join();
    } catch (InterruptedException e) {
      _logger
          .debug("Thread was interrupted while waiting for another thread to die, going on with stopping"); //$NON-NLS-1$
    }
    removeExportDirContext();
  }

  @Override
  public void run() {
    try {
      do {
        sleep(10000);
        initExportDirContext();
      } while (!_stopped);
    } catch (InterruptedException e) {
      _logger.debug("Thread was interrupted, exiting service silently"); //$NON-NLS-1$
    }
  }
}