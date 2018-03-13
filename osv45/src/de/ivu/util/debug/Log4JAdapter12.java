/*
 * Log4JAdapter11
 * 
 * Created on 08.04.2003
 * Copyright (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.util.debug;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Category;
import org.apache.log4j.PropertyConfigurator;

import de.ivu.util.config.Extract;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class Log4JAdapter12 implements Log4JAdapter {

  @SuppressWarnings("deprecation")
  public Category configure(String category, URL logConfig) throws Exception {
    // this method is used by all other configure() methods
    // check if any appender is configured, because there is no default appender in log4j
    // this is a workaround, to prevent multiple instance of the same appender

    // inspect logger library
    if (Package.getPackage("org.apache.log4j.helpers") != null) { //$NON-NLS-1$
      return Category.getInstance(category);
    }

    // already initialized, pre
    if (logConfig == null) {
      // there are no properties, so we use the default strategie
      BasicConfigurator.configure();
    } else {
      boolean isXML = logConfig.toExternalForm().toLowerCase().endsWith(".xml"); //$NON-NLS-1$
      boolean isLocal = logConfig.getProtocol().equalsIgnoreCase("file"); //$NON-NLS-1$
      long watch = 0L;

      if (!isXML) {
        Properties props = new Properties();
        try {
          InputStream in = logConfig.openStream();
          props.load(in);
          in.close();
        } catch (IOException ex) {
          System.err.println("Cannot load configuration from properties: " + ex); //$NON-NLS-1$
        }
        watch = 1000L * Extract.asInt(props.getProperty(Log4J.PROPERTY_WATCH_INTERVAL), 0);
      } else {
        // todo implement support for configuration by XML properties
        throw new RuntimeException(
            "This implementation dose not support XML properties at this time."); //$NON-NLS-1$
      }
      // check whether we can and we have to watch configuration for changes
      if (watch > 0L && isLocal) {
        if (isXML) {
          // DOMConfigurator.configureAndWatch( logConfig.getFile(), watch );
        } else {
          PropertyConfigurator.configureAndWatch(logConfig.getFile(), watch);
        }
      } else {
        if (isXML) {
          // DOMConfigurator.configure( logConfig );
        } else {
          PropertyConfigurator.configure(logConfig);
        }
      }
    }
    Category cat = null;
    if (category == null) {
      cat = Category.getRoot();
    } else {
      cat = Category.getInstance(category);
    }
    return cat;
  }
}
