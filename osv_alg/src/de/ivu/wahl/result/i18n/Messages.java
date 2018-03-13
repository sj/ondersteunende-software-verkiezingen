/*
 * Messages
 * 
 * Created on 05.01.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class Messages {
  private static final String BUNDLE_NAME = "de.ivu.wahl.result.i18n.messages"; //$NON-NLS-1$
  private static final Logger LOGGER = Logger.getLogger(Messages.class);

  private static final Locale LOCALE = getLocale();
  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME,
      LOCALE);

  private Messages() {
    // hide constructor
  }

  /**
   * @return Localized message (without parameters)
   */
  public static String getString(String key) {
    try {
      return RESOURCE_BUNDLE.getString(key);
    } catch (MissingResourceException e) {
      return '!' + key + '!';
    }
  }

  /**
   * @return Localized message with binding <code>args</code>
   */
  public static String bind(String key, Object... args) {
    try {
      return applyPattern(getString(key), args);
    } catch (Exception e) {
      // an exception here may not prevent the application to execute!
      LOGGER.warn(e);
      return '?' + key + '?';
    }
  }

  /**
   * @return mesage with binding <code>args</code> (not localized)
   */
  public static String applyPattern(String message, Object... args) {
    return new MessageFormat(message, LOCALE).format(args);
  }

  /**
   * @return the locale according to the System properties <code>user.language</code>,
   *         <code>user.country</code> and <code>user.variant</code>
   */
  public static Locale getLocale() {
    final String language = System.getProperty("user.language"); //$NON-NLS-1$
    final String country = System.getProperty("user.country"); //$NON-NLS-1$
    final String variant = System.getProperty("user.variant"); //$NON-NLS-1$
    if (country == null) {
      return new Locale(language);
    } else if (variant == null) {
      return new Locale(language, country);
    }
    return new Locale(language, country, variant);
  }

}
