/*
 * Messages
 * 
 * Created on 05.01.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.i18n;

import static java.util.ResourceBundle.getBundle;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.jboss.logging.Logger;

import de.ivu.wahl.util.BundleHelper;

public class Messages {
  private static final String BUNDLE_NAME = "de.ivu.wahl.i18n.messages"; //$NON-NLS-1$
  private static final Logger LOGGER = Logger.getLogger(Messages.class);

  private static final Locale LOCALE = BundleHelper.getLocale();
  private static final ResourceBundle RESOURCE_BUNDLE = getBundle(BUNDLE_NAME, LOCALE);

  private Messages() {
    // hide constructor
  }

  public static String getString(String key) {
    try {
      return RESOURCE_BUNDLE.getString(key);
    } catch (MissingResourceException e) {
      return '!' + key + '!';
    }
  }

  public static String bind(String key, Object... args) {
    try {
      return applyPattern(getString(key), args);
    } catch (Exception e) {
      // an exception here may not prevent the application to execute!
      LOGGER.warn(e);
      return '?' + key + '?';
    }
  }

  public static String applyPattern(String message, Object... args) {
    return new MessageFormat(message, LOCALE).format(args);
  }
}
