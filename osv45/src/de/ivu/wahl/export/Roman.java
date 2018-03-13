package de.ivu.wahl.export;

import java.util.HashMap;
import java.util.Map;

/**
 * lookup for roman numbers, only numbers I=1 and II=2 are required....
 * 
 * @author msc
 */
public final class Roman {

  private Roman() {
    // hide constructor
  }

  private static final Map<String, Long> ROMAN_LONG_MAP = new HashMap<String, Long>();
  private static final Map<Long, String> LONG_ROMAN_MAP = new HashMap<Long, String>();
  @SuppressWarnings("nls")
  private static final String[] ROMAN_NUMBERS = new String[]{"I", "II", "III", "IV", "V", "VI",
      "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX",
      "XX"};

  static {
    for (int i = 0; i < ROMAN_NUMBERS.length; i++) {
      String romanNumber = ROMAN_NUMBERS[i];
      Long number = Long.valueOf(i + 1);
      ROMAN_LONG_MAP.put(romanNumber, number);
      LONG_ROMAN_MAP.put(number, romanNumber);
    }
  }

  public static Long toLong(String id) {
    if (id == null) {
      return null;
    }
    Long result = ROMAN_LONG_MAP.get(id.toUpperCase());
    if (result == null) {
      throw new NumberFormatException("unknown roman number " + id); //$NON-NLS-1$
    }
    return result;
  }

  public static String toRoman(Long l) {
    if (l == null) {
      return null;
    }
    String result = LONG_ROMAN_MAP.get(l);
    if (result == null) {
      throw new NumberFormatException("unknown roman number " + l); //$NON-NLS-1$
    }

    return result;
  }
}
