/*
 * BundleHelper
 * 
 * Created on 17.12.2008
 * Copyright (c) 2008 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.util;

import static java.lang.System.getProperty;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public abstract class BundleHelper {

  public static ResourceBundle __rb;

  /**
   * Convenience method for reading strings from the default resource bundle
   * 
   * @param key Schl�ssel f�r den String-Eintrag in der �bersetzungstabelle
   * @return �bersetzung in der Sprache/Version der Locale der Anwendung
   */
  public static String getBundleString(String key) {
    if (__rb == null) {
      __rb = ResourceBundle.getBundle("wasInternationalisierung", getLocale()); //$NON-NLS-1$
    }
    try {
      return __rb.getString(key);
    } catch (Exception e) {
      return "kI " + key; //$NON-NLS-1$
    }
  }

  /**
   * @return {@link Locale} f�r die Anwendung
   */
  public static Locale getLocale() {
    final String language = getProperty("user.language"); //$NON-NLS-1$
    final String country = getProperty("user.country"); //$NON-NLS-1$
    final String variant = getProperty("user.variant"); //$NON-NLS-1$
    if (country == null) {
      return new Locale(language);
    } else if (variant == null) {
      return new Locale(language, country);
    }
    return new Locale(language, country, variant);
  }

}
