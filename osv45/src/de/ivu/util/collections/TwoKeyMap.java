/*
 * TwoKeyMap
 * 
 * Created on 03.11.2003
 * Copyright (c) 2002-4 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.util.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * A TwoKeyMap maps each combination of two keys to a single value.
 * 
 * @author jon@ivu.de cos@ivu.de
 * @since 03.11.2003
 * @param < K1 > Datentyp des ersten Schl�ssels
 * @param < K2 > Datentyp des zweiten Schl�ssels
 * @param < V > Datentyp f�r den Wert
 */
public class TwoKeyMap<K1, K2, V> {
  private final Map<K1, Map<K2, V>> _baseMap;
  private final TwoKeyMap.Constructor<K2, V> _constructor;

  /**
   * Constructor
   */
  public TwoKeyMap() {
    _baseMap = new HashMap<K1, Map<K2, V>>();
    _constructor = defaultConstructor();
  }

  /**
   * Constructor
   * 
   * @param initialCapacity Estimate for the number of first keys
   */
  public TwoKeyMap(int initialCapacity) {
    _baseMap = new HashMap<K1, Map<K2, V>>(initialCapacity);
    _constructor = defaultConstructor();
  }

  /**
   * Constructor
   * 
   * @param constructor A constructor creates the Maps used for the second key mapping.
   */
  public TwoKeyMap(Constructor<K2, V> constructor) {
    _baseMap = new HashMap<K1, Map<K2, V>>();
    _constructor = constructor;
  }

  /**
   * Constructor
   * 
   * @param initialCapacity Estimate for the number of first keys
   * @param constructor A constructor creates the Maps used for the second key mapping.
   */
  public TwoKeyMap(int initialCapacity, Constructor<K2, V> constructor) {
    _baseMap = new HashMap<K1, Map<K2, V>>(initialCapacity);
    _constructor = constructor;
  }

  /**
   * Associates the specified value with the combination of the specified keys key1 and key2 in this
   * map If the map previously contained a mapping for these keys, the old value is replaced by the
   * specified value.
   * 
   * @param key1
   * @param key2
   * @param value
   * @return previous value associated with specified keys, or <tt>null</tt> if there was no mapping
   *         for keys.
   */
  public V put(K1 key1, K2 key2, V value) {
    Map<K2, V> map = getMap(key1);
    if (map == null) {
      map = _constructor.execute();
      _baseMap.put(key1, map);
    }
    return map.put(key2, value);
  }

  public V get(K1 key1, K2 key2) {
    Map<K2, V> map = getMap(key1);
    if (map == null) {
      return null;
    }
    return map.get(key2);
  }

  /**
   * Delete all Objects
   */
  public void clear() {
    _baseMap.clear();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof TwoKeyMap) {
      TwoKeyMap<?, ?, ?> mm = (TwoKeyMap<?, ?, ?>) obj;
      return _baseMap.equals(mm._baseMap);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return _baseMap.hashCode();
  }

  /**
   * Returns <tt>true</tt> if this map contains no key-key-value mappings.
   * 
   * @return boolean
   */
  public boolean isEmpty() {
    Iterator<K1> iter = firstKeyIterator();
    while (iter.hasNext()) {
      if (!secondKeySet(iter.next()).isEmpty()) {
        return false;
      }
    }
    return true;
  }

  public Set<K1> firstKeySet() {
    return _baseMap.keySet();
  }

  public Iterator<K1> firstKeyIterator() {
    return firstKeySet().iterator();
  }

  public Set<K2> secondKeySet(K1 key1) {
    Map<K2, V> map = getMap(key1);
    if (map == null) {
      return null;
    }
    return map.keySet();
  }

  public Iterator<K2> secondKeyIterator(K1 key1) {
    return secondKeySet(key1) != null ? secondKeySet(key1).iterator() : null;
  }

  public boolean containsKeys(K1 key1, K2 key2) {
    Map<K2, V> map = getMap(key1);
    if (map == null) {
      return false;
    }
    return map.containsKey(key2);
  }

  public Map<K2, V> getMap(Object key1) {
    return _baseMap.get(key1);
  }

  /**
   * @author Joachim Nottebaum
   * @param <K2> Datentyp f�r den Schl�ssel
   * @param <V> Datentyp f�r den Wert
   * @since 03.11.2003 Simple Interface for an Object that creates new Map for each first key
   */
  public interface Constructor<K2, V> {
    public Map<K2, V> execute();
  }

  private Constructor<K2, V> defaultConstructor() {
    return new Constructor<K2, V>() {
      public Map<K2, V> execute() {
        return new HashMap<K2, V>(5);
      }
    };
  }

}
