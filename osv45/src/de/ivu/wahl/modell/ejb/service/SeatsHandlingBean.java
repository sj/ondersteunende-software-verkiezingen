/*
 * Created on 24.11.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb.service;

import java.util.Collection;

import javax.ejb.FinderException;
import javax.ejb.Local;
import javax.ejb.Stateless;

import de.ivu.wahl.WahlStatelessSessionBeanBase;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.Liste;
import de.ivu.wahl.modell.ejb.SitzberechnungErgebnis;
import de.ivu.wahl.result.result.AssignmentType;
import de.ivu.wahl.result.result.Distribution;

/**
 * Business services with respect to seats
 * 
 * @author jon@ivu.de
 */
@Stateless
@Local(SeatsHandling.class)
public class SeatsHandlingBean extends WahlStatelessSessionBeanBase implements SeatsHandling {
  private static final long serialVersionUID = 7532919237823956859L;

  @Override
  public int getTotalSeatsForP2List(String id_Ergebniseingang, Liste p2List, boolean inConflict)
      throws FinderException {
    if (!inConflict) {
      return getSitzverteilungHome().findByErgebniseingangAndListe(id_Ergebniseingang,
          p2List.getID_Liste()).getSitzeGesamtzahl();
    } else {
      int result = 0;
      Collection<SitzberechnungErgebnis> p2Results = getSitzberechnungErgebnisHome()
          .findAllByErgebniseingangAndVerteilung(id_Ergebniseingang, Distribution.P2.getId());
      for (SitzberechnungErgebnis sbe : p2Results) {
        if (p2List.getID_Liste().equals(sbe.getID_Liste())) {
          result += sbe.getSitze();
        }
      }
      return result;
    }
  }

  /**
   * In conflict mode, get the total number of seats of a P3-list from the non-trivial assignments
   * to the P3-list or from (all) assignments to a P42-list with same key.
   */
  @Override
  public int getTotalSeatsForP3List(String id_Ergebniseingang, Gruppe gruppe)
      throws FinderException {
    int result = 0;
    Collection<SitzberechnungErgebnis> p3Results = getSitzberechnungErgebnisHome()
        .findAllByErgebniseingangAndVerteilung(id_Ergebniseingang, Distribution.P3.getId());
    for (SitzberechnungErgebnis sbe : p3Results) {
      // Trivial are assigments to the P3-list in a trivial P42-list.
      // A trival P42-list is a P42-list with only one P3-list.
      if (gruppe.getID_Gruppe().equals(sbe.getID_Gruppe())
          && sbe.getSchritttyp() != AssignmentType.TRIVIAL.getId()) {
        result += sbe.getSitze();
      }
    }
    Collection<SitzberechnungErgebnis> p42Results = getSitzberechnungErgebnisHome()
        .findAllByErgebniseingangAndVerteilung(id_Ergebniseingang, Distribution.P42.getId());
    for (SitzberechnungErgebnis sbe : p42Results) {
      if (gruppe.getID_Gruppe().equals(sbe.getID_Gruppe())) {
        result += sbe.getSitze();
      }
    }
    return result;
  }

}
