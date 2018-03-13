/*
 * PropertyHandling
 * 
 * Created on 13.10.2003
 * Copyright (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.admin;

import java.io.File;

import javax.ejb.EJBException;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public interface PropertyHandling {

  /**
   * Lesen einer bezeichneten Property
   * 
   * @return property-Wert als String
   * @param key Schl�ssel/Propertyname
   * @throws EJBException genereller Fehler
   */
  String getProperty(String key) throws EJBException;

  /**
   * Setzen einer Property in der Datenbank
   * 
   * @param key Schl�sselstring
   * @param value Wert (String)
   * @throws EJBException genereller Fehler
   */
  void setProperty(String key, String value) throws EJBException;

  /**
   * Lesen einer bezeichneten Property als int-Wert
   * 
   * @param key Name der Property
   * @return int-Wert der Property
   */
  int getIntProperty(String key);

  /**
   * Lesen einer bezeichneten Property als int-Wert
   * 
   * @param key Name der Property
   * @param defaultValue Vorbelegung
   * @return int-Wert der Property
   */
  int getIntProperty(String key, int defaultValue);

  /**
   * Lesen und Postinkremenieren einer bezeichneten Property als int wert
   * 
   * @param key Name der Property
   * @return Wert vor der Inkrementierung als int
   */
  int incIntProperty(String key);

  /**
   * Lesen und Postinkremenieren einer bezeichneten Property als int wert
   * 
   * @param key Name der Property
   * @param defaultValue Vorbelegung
   * @return Wert vor der Inkrementierung als int
   */
  int incIntProperty(String key, int defaultValue);

  /**
   * Lesen und Postdekremenieren einer bezeichneten Property als int wert
   * 
   * @param key Name der Property
   * @return Wert vor der Dekrementierung als int
   */
  int decIntProperty(String key);

  /**
   * Lesen und Postdekremenieren einer bezeichneten Property als int wert
   * 
   * @param key Name der Property
   * @param defaultValue Vorbelegung
   * @return Wert vor der Dekrementierung als int
   */
  int decIntProperty(String key, int defaultValue);

  /**
   * @param key
   * @param wert
   */
  void setIntProperty(String key, int wert);

  /**
   * Lesen einer bezeichneten Property als long-Wert
   * 
   * @param key Name der Property
   * @return long-Wert der Property
   */
  long getLongProperty(String key);

  /**
   * Lesen und Postinkremenieren einer bezeichneten Property als long-Wert
   * 
   * @param key Name der Property
   * @return Wert vor der Inkrementierung als long
   */
  long incLongProperty(String key);

  /**
   * Lesen und Postdecrementieren einer bezeichneten Property als long-Wert
   * 
   * @param key Name der Property
   * @return Wert vor der Dekrementierung als long
   */
  long decLongProperty(String key);

  /**
   * @param key
   * @param wert
   */
  void setLongProperty(String key, long wert);

  /**
   * Lesen einer bezeichneten Property als boolean-Wert
   * 
   * @param key Name der Property
   * @return boolean-Wert der Property
   */
  boolean getBooleanProperty(String key);

  /**
   * @param key
   * @param wert
   */
  void setBooleanProperty(String key, boolean wert);

  /**
   * Setzen aller Properties in der Datenbank, die mit dem angegebenen Schl�sselstring anfangen, auf
   * denselben Wert.
   * 
   * @param prefix Prefix des Schl�sselstrings
   * @param value Wert (String)
   */
  void setProperties(String prefix, String value);

  /**
   * Loeschen aller Properties mit Schluesselwertanteil des uebergebenen keyinfix'
   * 
   * @param keyInfix
   * @throws EJBException
   */
  void removePropertiesLike(String keyInfix) throws EJBException;

  File getFileProperty(String key);
}
