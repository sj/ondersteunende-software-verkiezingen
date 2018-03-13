/*
 * Gruppe
 * 
 * Copyright (c) 2002-2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb;

import javax.ejb.EJBException;

/**
 * Gruppe corresponds to a P3-list in the OSV specification, i.e.
 * <ul>
 * <li>a list group or</li>
 * <li>a set of identical lists that do not belong to a list group or</li>
 * <li>an independen list.</li>
 * </ul>
 * 
 * @author cos@ivu.de IVU Traffic Technologies AG
 */
public interface Gruppe extends BasicGruppe {

  /**
   * Get region specific entity of the group for the given region (if defined, else error)
   * 
   * @param id_Gebiet
   * @return
   * @throws EJBException
   */
  GruppeGebietsspezifisch getGruppeGebietsspezifischByGebiet(String id_Gebiet) throws EJBException;

  /*
   * (non-Javadoc) Get short name of the root region of the election
   * @see de.ivu.wahl.modell.GruppeModel#getNameKurz()
   */
  String getNameKurz();

}
