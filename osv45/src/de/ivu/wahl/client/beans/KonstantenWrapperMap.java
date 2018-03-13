/*
 * KonstantenWrapperMap
 * 
 * Created on 29.11.2005
 * Copyright (c) 2005 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public abstract class KonstantenWrapperMap implements Map<String, Object>, Serializable {
  private static final long serialVersionUID = 5403038935021989663L;

  static class KonstantenWrapperPrivateMap extends HashMap<String, Object> {
    private static final long serialVersionUID = 2652272097202015961L;

    KonstantenWrapperPrivateMap(Class<?> c) {
      Field[] fields = c.getDeclaredFields();
      for (Field field : fields) {
        int modifier = field.getModifiers();
        if (Modifier.isStatic(modifier) && Modifier.isFinal(modifier)
            && !Modifier.isPrivate(modifier)) {
          try {
            put(field.getName(), field.get(null));
          } catch (IllegalAccessException e) {
            Logger
                .getLogger(KonstantenWrapperPrivateMap.class)
                .fatal("Cannot ever get here because all access modifiers were correctly checked beforehand " + e); //$NON-NLS-1$
          }
        }
      }
    }
  }

  private final Map<String, Object> _privateMap;

  public KonstantenWrapperMap(Class<?> c) {
    _privateMap = Collections.unmodifiableMap(new KonstantenWrapperPrivateMap(c));
  }

  public int size() {
    return _privateMap.size();
  }

  public void clear() {
    _privateMap.clear();
  }

  public boolean isEmpty() {
    return _privateMap.isEmpty();
  }

  public boolean containsKey(Object key) {
    return _privateMap.containsKey(key);
  }

  public boolean containsValue(Object value) {
    return _privateMap.containsValue(value);
  }

  public Collection<Object> values() {
    return _privateMap.values();
  }

  public void putAll(Map<? extends String, ? extends Object> t) {
    _privateMap.putAll(t);
  }

  public Set<Map.Entry<String, Object>> entrySet() {
    return _privateMap.entrySet();
  }

  public Set<String> keySet() {
    return _privateMap.keySet();
  }

  public Object get(Object key) {
    return _privateMap.get(key);
  }

  public Object remove(Object key) {
    return _privateMap.remove(key);
  }

  public Object put(String key, Object value) {
    return _privateMap.put(key, value);
  }
}