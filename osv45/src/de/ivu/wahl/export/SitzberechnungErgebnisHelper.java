/*
 * SitzberechnungErgebnisHelper
 * 
 * Created on 02.09.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.ivu.wahl.modell.ejb.SitzberechnungErgebnis;
import de.ivu.wahl.result.MultimapUtil;
import de.ivu.wahl.result.result.AssignmentType;
import de.ivu.wahl.result.result.Distribution;

/**
 * Helps to get information from a list of SitzberechnungErgebnis.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class SitzberechnungErgebnisHelper {
  private final Map<String, SitzberechnungErgebnis> listAndFirstAssignment = new HashMap<String, SitzberechnungErgebnis>();
  private final Map<String, Set<String>> parentAndChildren = new HashMap<String, Set<String>>();
  private final Map<String, Set<SitzberechnungErgebnis>> parentAndExhaustedListAssignments = new HashMap<String, Set<SitzberechnungErgebnis>>();
  private final Map<String, Set<SitzberechnungErgebnis>> parentAndDHondtAssignments = new HashMap<String, Set<SitzberechnungErgebnis>>();
  private final Map<String, Set<SitzberechnungErgebnis>> childAndNiemeyerAssignments = new HashMap<String, Set<SitzberechnungErgebnis>>();

  /**
   * Constructor
   */
  public SitzberechnungErgebnisHelper(Collection<SitzberechnungErgebnis> sbes) {
    ArrayList<SitzberechnungErgebnis> sbeList = new ArrayList<SitzberechnungErgebnis>(sbes);
    Collections.sort(sbeList, new P3DistributionComparator());

    for (SitzberechnungErgebnis sbe : sbeList) {
      final String child;
      final String parent;
      if (sbe.getVerteilung() == Distribution.P3.getId()) {
        // sbe.getVerteilung() == Distribution.FICTITIOUS.getId() never occurs in
        // SitzberechnungErgebnis
        child = sbe.getID_Gruppe();
        parent = sbe.getID_Listenkombination();
      } else if (sbe.getVerteilung() == Distribution.P2.getId()) {
        child = sbe.getID_Liste();
        parent = sbe.getID_Gruppe();
      } else {
        child = sbe.getID_Gruppe() == null ? sbe.getID_Listenkombination() : sbe.getID_Gruppe();
        parent = null;
      }
      if (sbe.getSchritttyp() == AssignmentType.FIRST_ASSIGNMENT.getId()) {
        listAndFirstAssignment.put(child, sbe);
        MultimapUtil.addToSet(parentAndChildren, parent, child);
      } else if (sbe.getSchritttyp() == AssignmentType.EXHAUSTED_LIST.getId()) {
        MultimapUtil.addToSet(parentAndExhaustedListAssignments, parent, sbe);
      } else if (sbe.getSchritttyp() == AssignmentType.DHONDT_ASSIGNMENT.getId()) {
        MultimapUtil.addToSet(parentAndDHondtAssignments, parent, sbe);
      } else if (sbe.getSchritttyp() == AssignmentType.NIEMEYER_ASSIGNMENT.getId()) {
        MultimapUtil.addToSet(childAndNiemeyerAssignments, child, sbe);
      }
    }
  }

  /**
   * Sort assignment lines: first combination results ordered by id, followed by its P3-lists
   * ordered by key
   * 
   * @author ugo@ivu.de, IVU Traffic Technologies AG
   */
  class P3DistributionComparator implements Comparator<SitzberechnungErgebnis> {
    public int compare(final SitzberechnungErgebnis sbe1, final SitzberechnungErgebnis sbe2) {
      // Without combination comes last
      if (sbe1.getID_Listenkombination() == null) {
        return -1;
      }
      if (sbe2.getID_Listenkombination() == null) {
        return 1;
      }
      if (!sbe1.getID_Listenkombination().equals(sbe2.getID_Listenkombination())) {
        return sbe1.getID_Listenkombination().compareTo(sbe2.getID_Listenkombination());
      }
      // Assigment to combination comes first
      if (sbe1.getID_Gruppe() == null) {
        return -1;
      }
      if (sbe2.getID_Gruppe() == null) {
        return 1;
      }
      if (sbe1.getGruppe().getSchluessel() != sbe2.getGruppe().getSchluessel()) {
        return Integer.signum(sbe1.getGruppe().getSchluessel() - sbe2.getGruppe().getSchluessel());
      }
      if (sbe1.getSchritttyp() != sbe2.getSchritttyp()) {
        return Integer.signum(sbe1.getSchritttyp() - sbe2.getSchritttyp());
      }
      return Integer.signum(sbe1.getSchrittnummer() - sbe2.getSchrittnummer());
    }
  }

  /**
   * @return all P3-lists that had a first assignment in the distribution within the combined list
   *         given by id_Listenkombination.
   */
  public Collection<String> getChildren(String id_Parent) {
    return unmodifiable(parentAndChildren.get(id_Parent));
  }

  /**
   * @return Exhausted list assignments to P3-lists within the combined list given by
   *         id_Listenkombination.
   */
  public Collection<SitzberechnungErgebnis> getExhaustedListAssignments(String id_Parent) {
    return unmodifiable(parentAndExhaustedListAssignments.get(id_Parent));
  }

  private <T> Collection<T> unmodifiable(Collection<T> set) {
    if (set == null) {
      return Collections.emptySet();
    } else {
      return Collections.unmodifiableCollection(set);
    }
  }

  /**
   * @return First assignments to P3-lists given by id_Gruppe.
   */
  public SitzberechnungErgebnis getFirstAssignment(String id_Gruppe) {
    return listAndFirstAssignment.get(id_Gruppe);
  }

  /**
   * @param id_Child
   * @return
   */
  public int getNiemeyerSeats(String id_Child) {
    int sum = 0;
    Collection<SitzberechnungErgebnis> sbes = unmodifiable(childAndNiemeyerAssignments
        .get(id_Child));
    for (SitzberechnungErgebnis sbe : sbes) {
      sum += sbe.getSitze();
    }
    return sum;
  }

  /**
   * @param id_Parent
   * @return
   */
  public Collection<SitzberechnungErgebnis> getDHondtAssignments(String id_Parent) {
    return unmodifiable(parentAndDHondtAssignments.get(id_Parent));
  }

}
