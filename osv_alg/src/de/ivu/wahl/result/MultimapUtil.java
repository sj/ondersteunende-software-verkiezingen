/*
 * MultimapUtil
 * 
 * Created on 21.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A multimap is a Map of Lists or a Map of Sets. This class provides utility methods for inserting
 * a new value into one of the Lists or Sets respectively that corresponds to a given key. If the
 * multimap does not contain the key, yet, a new Lists or Sets respectively is inserted.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class MultimapUtil {
  public static <K, V> List<V> addToList(Map<K, List<V>> multimap, K key, V value) {
    List<V> list = multimap.get(key);
    if (list == null) {
      list = new ArrayList<V>();
      multimap.put(key, list);
    }
    list.add(value);
    return list;
  }

  public static <K, V> Set<V> addToSet(Map<K, Set<V>> multimap, K key, V value) {
    Set<V> list = multimap.get(key);
    if (list == null) {
      list = new HashSet<V>();
      multimap.put(key, list);
    }
    list.add(value);
    return list;
  }

  public static <K, V> Collection<V> addToCollection(Map<K, Collection<V>> multimap, K key, V value) {
    Collection<V> list = multimap.get(key);
    if (list == null) {
      list = new ArrayList<V>();
      multimap.put(key, list);
    }
    list.add(value);
    return list;
  }

  public static <K1, K2, V> Map<K2, V> getMap(Map<K1, Map<K2, V>> multimap, K1 key) {
    Map<K2, V> innerMap = multimap.get(key);
    if (innerMap == null) {
      innerMap = new HashMap<K2, V>();
      multimap.put(key, innerMap);
    }
    return innerMap;
  }

}
