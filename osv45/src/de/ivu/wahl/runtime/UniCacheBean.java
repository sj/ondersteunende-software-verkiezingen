/*
 * UniCacheBean
 * 
 * Created on 17.03.2005
 * Copyright (c) 2005 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.runtime;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

/**
 * @author COS@ivu.de, IVU Traffic Technologies AG
 * @param <T> Typ der Objekte, die gehalten werden
 */
public class UniCacheBean<T> implements EntityBean {
  private static final long serialVersionUID = 8959866464104657487L;

  private T _object;

  public void setEntityContext(EntityContext ctx) throws EJBException {
    // tut nix
  }

  public void unsetEntityContext() throws EJBException {
    // tut nix
  }

  public void ejbRemove() throws RemoveException, EJBException {
    _object = null;
  }

  public void ejbActivate() throws EJBException {
    // tut nix
  }

  public void ejbPassivate() throws EJBException {
    // Zustand zur�cksetzen
    _object = null;
  }

  public void ejbLoad() throws EJBException {
    // keine Persistenz
  }

  public void ejbStore() throws EJBException {
    // keine Persistenz
  }

  /**
   * Gibt den �bergebenen Schl�ssel wieder zur�ck
   * 
   * @param cachePK Schl�ssel des UniCache-Objekts, welches geholt werden soll
   * @return Schl�ssel des UniCache-Objekts
   */
  public UniCachePK ejbFindByPrimaryKey(UniCachePK cachePK) {
    return cachePK;
  }

  /**
   * Gibt das gespeicherte Objekt zur�ck
   * 
   * @return das gespeicherte Objekt
   */
  public T get() {
    return _object;
  }

  /**
   * Gibt das gespeicherte Objekt zur�ck (nach Einstellung in assembly-descriptor in einer neuen
   * Transaktion)
   * 
   * @return das gespeicherte Objekt
   */
  public T getNewTX() {
    return _object;
  }

  /**
   * Speichert die ein beliebiges Objekt als Eigenschaft im Bean
   * 
   * @param object das zu speichernde Objekt
   */
  public void set(T object) {
    _object = object;
  }

  /**
   * Erlangt ein Schreib-Lock auf das Cache-Objekt, ohne das Objekt zu beschreiben
   */
  public void lock() {
    // keine weitere Funktion
  }
}
