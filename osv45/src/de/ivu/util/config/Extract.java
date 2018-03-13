/*
 *  --------------------------------------------------------------
 *  Company   : IVU Traffic Technologies AG
 *  --------------------------------------------------------------
 */
package de.ivu.util.config;

import java.text.SimpleDateFormat;

/**
 * Helper class to extract values from string.
 * 
 * @author kie@ivu.de
 * @created 2001-06-27
 * @since 1.1
 */
public final class Extract {

  private Extract() {
    // forbidding instantiation
  }

  /**
   * Gets a int from an string
   * 
   * @param s string containing an integer
   * @return the int value
   * @exception NumberFormatException Exception
   */
  public static long asLong(String s) throws NumberFormatException {
    String tmp = s.trim();
    boolean _isHex = false;
    if (tmp.toLowerCase().startsWith("0x")) { //$NON-NLS-1$
      _isHex = true;
      tmp = tmp.substring(2).toLowerCase();
    }
    int i = 0;
    while (i < tmp.length()) {
      char c = tmp.charAt(i);
      if (Character.isDigit(c)) {
        i++;
      } else if (_isHex && (c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f')) {
        i++;
      } else {
        break;
      }
    }
    return new Long(tmp.substring(0, i)).longValue();
  }

  /**
   * Gets a int from an string
   * 
   * @param s string containing an integer
   * @param defValue Description
   * @return the int value
   */
  public static long asLong(String s, long defValue) {
    try {
      return asLong(s);
    } catch (Exception ex) {
      return defValue;
    }
  }

  /**
   * Gets a int from an string
   * 
   * @param s string containing an integer
   * @return the int value
   * @exception NumberFormatException Exception
   */
  public static int asInt(String s) throws NumberFormatException {
    String tmp = s.trim();
    boolean _isHex = false;
    if (tmp.toLowerCase().startsWith("0x")) { //$NON-NLS-1$
      _isHex = true;
      tmp = tmp.substring(2).toLowerCase();
    }
    int i = 0;
    while (i < tmp.length()) {
      char c = tmp.charAt(i);
      if (Character.isDigit(c)) {
        i++;
      } else if (_isHex && (c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f')) {
        i++;
      } else {
        break;
      }
    }
    return Integer.parseInt(tmp.substring(0, i), (_isHex ? 16 : 10));
  }

  /**
   * Gets a int from an string
   * 
   * @param s string containing an integer
   * @param defValue Description
   * @return the int value
   */
  public static int asInt(String s, int defValue) {
    try {
      return asInt(s);
    } catch (Exception ex) {
      return defValue;
    }
  }

  /**
   * gets a double from an string.
   * 
   * @param s string containing an double
   * @return the double value
   * @exception NumberFormatException Exception
   */
  public static double asDouble(String s) throws NumberFormatException {
    String tmp = s.trim();
    int i = 0;
    while (i < tmp.length()) {
      if (Character.isDigit(tmp.charAt(i)) || tmp.charAt(i) == '.' || tmp.charAt(i) == '+'
          || tmp.charAt(i) == '-') {
        i++;
      } else {
        break;
      }
    }
    double a = Double.parseDouble(tmp.substring(0, i));
    i = s.indexOf('/');
    if (i < 0) {
      return a;
    }
    // fractional number like 1/3
    tmp = tmp.substring(i + 1).trim();
    i = 0;
    while (i < tmp.length()) {
      if (Character.isDigit(tmp.charAt(i)) || tmp.charAt(i) == '.' || tmp.charAt(i) == '+'
          || tmp.charAt(i) == '-') {
        i++;
      } else {
        break;
      }
    }
    double b = Double.parseDouble(tmp.substring(0, i));
    return a / b;
  }

  /**
   * gets a double from an string.
   * 
   * @param s string containing an double
   * @param defValue Description
   * @return the double value
   */
  public static double asDouble(String s, double defValue) {
    try {
      return asDouble(s);
    } catch (Exception ex) {
      return defValue;
    }
  }

  /**
   * gets a double from an string.
   * 
   * @param s string containing an double
   * @return the double value
   * @exception NumberFormatException Exception
   */
  public static float asFloat(String s) throws NumberFormatException {
    String tmp = s.trim();
    int i = 0;
    while (i < tmp.length()) {
      if (Character.isDigit(tmp.charAt(i)) || tmp.charAt(i) == '.' || tmp.charAt(i) == '-') {
        i++;
      } else {
        break;
      }
    }
    return new Float(tmp.substring(0, i)).floatValue();
  }

  /**
   * gets a double from an string.
   * 
   * @param s string containing an double
   * @param defValue Description
   * @return the double value
   */
  public static float asFloat(String s, float defValue) {
    try {
      return asFloat(s);
    } catch (Exception ex) {
      return defValue;
    }
  }

  /**
   * Gets an boolean from an string
   * 
   * @param s string containing the boolean
   * @return the boolean value
   * @exception NumberFormatException Exception
   */
  public static boolean asBoolean(String s) throws NumberFormatException {
    String tmp = s.trim().toUpperCase();
    if (tmp.startsWith("YES") || tmp.startsWith("JA") || tmp.startsWith("1") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        || tmp.startsWith("TRUE")) { //$NON-NLS-1$
      return true;
    } else {
      return false;
    }
  }

  /**
   * Gets an boolean from an string
   * 
   * @param s string containing the boolean
   * @param defValue Description
   * @return the boolean value
   */
  public static boolean asBoolean(String s, boolean defValue) {
    try {
      return asBoolean(s);
    } catch (Exception ex) {
      return defValue;
    }
  }

  private static SimpleDateFormat __tf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //$NON-NLS-1$

  /**
   * Gets the time as long from string formated like "yyyy-MM-dd hh:mm:ss".
   * 
   * @param time string with yyyy-MM-dd hh:mm:ss
   * @return time in ms since ...
   */
  public static long asTime(String time) {
    try {
      return __tf.parse(time).getTime();
    } catch (Exception ex) {
      throw new RuntimeException("Error while decoding time from: " + time); //$NON-NLS-1$
    }
  }

  /**
   * Gets the time as long from string formated like "yyyy-MM-dd hh:mm:ss".
   * 
   * @param time string with yyyy-MM-dd hh:mm:ss
   * @param defValue default time
   * @return time in ms since ...
   */
  public static long asTime(String time, long defValue) {
    try {
      return asTime(time);
    } catch (Exception ex) {
      return defValue;
    }
  }
}
