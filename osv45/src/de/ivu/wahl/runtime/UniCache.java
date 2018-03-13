/*
 * UniCache
 * 
 * Created on 17.03.2005
 * Copyright (c) 2005 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.runtime;

import javax.ejb.EJBLocalObject;

/**
 * @author COS@ivu.de, IVU Traffic Technologies AG
 * @param <T> Typ der Objekte, die gehalten werden
 */
public interface UniCache<T> extends EJBLocalObject {
  /**
   * Gibt das gespeicherte Objekt zur�ck
   * 
   * @return das gespeicherte Objekt
   */
  T get();

  /**
   * Gibt das gespeicherte Objekt zur�ck (nach Einstellung in assembly-descriptor in einer neuen
   * Transaktion)
   * 
   * @return das gespeicherte Objekt
   */
  T getNewTX();

  /**
   * Speichert die ein beliebiges Objekt als Eigenschaft im Bean
   * 
   * @param object das zu speichernde Objekt
   */
  void set(T object);

  /**
   * Erlangt ein Schreib-Lock auf das Cache-Objekt, ohne das Objekt zu beschreiben
   */
  void lock();
}
