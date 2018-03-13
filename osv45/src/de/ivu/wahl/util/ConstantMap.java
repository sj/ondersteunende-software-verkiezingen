/*
 * ConstantMap
 * 
 * Created on 06.02.2007
 * Copyright (c) 2007 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.util;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * {@link Map} zum Vorhalten von konstanten Abbildungen
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 * @param <K> Datentyp f�r Key
 * @param <V> Datentyp f�r Value
 */
public abstract class ConstantMap<K, V> extends AbstractMap<K, V> {
  private Set<Entry<K, V>> _entrySet = new LinkedHashSet<Entry<K, V>>();
  private boolean _mutable = true;

  protected void add(K key, V value) {
    _entrySet.add(new SimpleMapEntry<K, V>(key, value));
  }

  @Override
  public Set<Map.Entry<K, V>> entrySet() {
    if (_mutable) {
      _entrySet = Collections.unmodifiableSet(_entrySet);
      _mutable = false;
    }
    return _entrySet;
  }
}
