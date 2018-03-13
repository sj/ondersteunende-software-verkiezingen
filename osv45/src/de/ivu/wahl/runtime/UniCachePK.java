/*
 * UniCachePK
 * 
 * Created on 16.03.2005
 * Copyright (c) 2005 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.runtime;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class UniCachePK {

  private final String _cacheName;
  private final String _id_Wahl;
  private final String _id_Ergebniseingang;

  /**
   * @param cacheName
   * @param id_Wahl
   * @param id_Ergebniseingang
   */
  public UniCachePK(final String cacheName, final String id_Wahl, final String id_Ergebniseingang) {
    _cacheName = cacheName;
    _id_Wahl = id_Wahl;
    _id_Ergebniseingang = id_Ergebniseingang;
  }

  /**
   * @param name
   * @param id_Wahl
   */
  public UniCachePK(final String name, final String id_Wahl) {
    this(name, id_Wahl, null);
  }

  @Override
  public boolean equals(Object obj) {
    UniCachePK uniCachePKObj = (UniCachePK) obj;
    return super.equals(obj)
        || (obj instanceof UniCachePK && equals(uniCachePKObj._cacheName, _cacheName)
            && equals(uniCachePKObj._id_Wahl, _id_Wahl) && equals(uniCachePKObj._id_Ergebniseingang,
            _id_Ergebniseingang));
  }

  @Override
  public int hashCode() {
    return _cacheName.hashCode()
        ^ (_id_Ergebniseingang == null ? _id_Wahl.hashCode() : _id_Ergebniseingang.hashCode());
  }

  @Override
  public String toString() {
    return "Cache: " + _cacheName + " id_Wahl: " + _id_Wahl + " id_Ergebniseingang: " + _id_Ergebniseingang; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  private static boolean equals(Object a, Object b) {
    return a == null ? b == null : a.equals(b);
  }
}
