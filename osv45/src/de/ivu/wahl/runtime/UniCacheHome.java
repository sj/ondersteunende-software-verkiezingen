/*
 * UniCacheHome
 * 
 * Created on 17.03.2005
 * Copyright (c) 2005 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.runtime;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

import de.ivu.ejb.IVUBeanBase;

/**
 * @author COS@ivu.de, IVU Traffic Technologies AG
 */
public interface UniCacheHome extends EJBLocalHome {

  public static class HomeFinder {
    public static UniCacheHome findHome(IVUBeanBase caller) {
      String simpleName = UniCacheHome.class.getSimpleName();
      return caller.findLocalHome(simpleName.substring(0, simpleName.length() - 4));
    }
  }

  /**
   * Gibt das UniCache-Objekt f�r den Schl�ssel zur�ck
   * 
   * @param cachePK Schl�ssel des UniCache-Objekts, welches geholt werden soll
   * @param <T> Typ der Objekte, die im Cache gehalten werden
   * @return UniCache-Objekt f�r den angegebenen Schl�ssel
   * @throws FinderException nie, aber notwendig laut Spez.
   */
  <T extends Object> UniCache<T> findByPrimaryKey(UniCachePK cachePK) throws FinderException;
}
