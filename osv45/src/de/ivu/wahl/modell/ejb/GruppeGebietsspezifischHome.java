package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;

/**
 * GruppeGebietsspezifischHome
 */

public interface GruppeGebietsspezifischHome extends BasicGruppeGebietsspezifischHome {

  /**
   * Find all by group and region. Warning: combination of group an region does not necessarily lead
   * to an explicit region specific group (e.g. majority vote)
   * 
   * @param id_Gruppe
   * @param id_Gebiet
   * @return
   * @throws FinderException
   * @throws EJBException
   */
  Collection<GruppeGebietsspezifisch> findAllByGruppeAndGebiet(String id_Gruppe, String id_Gebiet)
      throws FinderException, EJBException;

  /**
   * Find by group and region. Warning: combination of group an region does not necessarily lead to
   * an explicit region specific group (e.g. majority vote)
   * 
   * @param id_Gruppe
   * @param id_Gebiet
   * @return
   * @throws ObjectNotFoundException
   * @throws FinderException
   * @throws EJBException
   */
  GruppeGebietsspezifisch findByGruppeAndGebiet(String id_Gruppe, String id_Gebiet)
      throws ObjectNotFoundException, FinderException, EJBException;

  /**
   * Find region specific group with specified key
   * 
   * @param id_Gebiet PG of region object Gebiet
   * @param gruppenschluessel Key for group or party
   * @return PK of the corresponding region specific group
   * @throws FinderException genereller Fehler bei der Durchführung der Suche
   * @throws EJBException bei einem allgemeinem Problem
   */
  GruppeGebietsspezifisch findByGebietAndGruppenschluessel(String id_Gebiet, int gruppenschluessel)
      throws FinderException;

}