/*
 * SchwellwertHandling
 * 
 * Created on 12.09.2008
 * Copyright (c) 2008 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.admin;

import javax.ejb.EJBException;

import de.ivu.wahl.AnwContext;

/**
 * Manipulation der Schwellwerte
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public interface SchwellwertHandling {
  /**
   * @param c Anwenderkontext
   * @param name Name des Schwellwerts
   * @return den durch <code>name</code> bezeichneten Schwellwert
   * @throws EJBException genereller Fehler
   */
  int getSchwellwert(AnwContext c, String name) throws EJBException;

  /**
   * Setzt den angegebenen Schwellwert, welcher f�r die gesammte Wahl gilt
   * 
   * @param c Anwenderkontext
   * @param schwellwertKey Bezeichnung des Schwellwerts
   * @param wert Wert
   * @throws EJBException genereller Fehler
   */
  void setSchwellwert(AnwContext c, String schwellwertKey, int wert) throws EJBException;

  /**
   * Initial values for threshold values
   * 
   * @param c User
   * @throws EJBException general problem
   */
  void initSchwellwerte(AnwContext c) throws EJBException;
}