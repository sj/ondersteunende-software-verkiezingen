package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBLocalObject;

/**
 * Listenkandidatur is the relation between a candidate (Personendaten) and his P2-list (Liste) that
 * denotes that the candidate is nominated on the P2-list on the given list position (int
 * getListenplatz()).
 * 
 * @author cos@ivu.de (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public interface Listenkandidatur extends BasicListenkandidatur {

  /**
   * The region the candidate is running for.
   * 
   * @return {@link EJBLocalObject} vom Typ {@link Gebiet}
   */
  Collection<Gebiet> getGebietCol();

  /**
   * The group the candidate is runnig for.
   * 
   * @return {@link EJBLocalObject} vom Typ {@link Gruppe}
   */
  Gruppe getGruppe();

  /**
   * Candidate's Name followed by group and list position
   * 
   * @return
   */
  String getAnzeigeName();
}
