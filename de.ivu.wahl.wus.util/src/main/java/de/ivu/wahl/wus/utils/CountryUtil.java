package de.ivu.wahl.wus.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;

import de.ivu.wahl.wus.utils.ui.ReadFileUtil;

public class CountryUtil {

  public static Map<String, String> getNameToIsoCodeMap() {
    final Map<String, String> nameToIsoCodeMap = new HashMap<String, String>();
    final List<String> lines = ReadFileUtil.readListFromFile("countries.txt"); //$NON-NLS-1$
    for (final String l : lines) {
      final String[] tokens = l.split("\t", 2); //$NON-NLS-1$
      Validate.isTrue(tokens.length == 2, "" + tokens.length + l); //$NON-NLS-1$
      nameToIsoCodeMap.put(tokens[1], tokens[0]);
    }
    return nameToIsoCodeMap;
  }
}
