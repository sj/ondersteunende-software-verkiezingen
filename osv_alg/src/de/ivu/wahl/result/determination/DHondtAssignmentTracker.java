package de.ivu.wahl.result.determination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ivu.wahl.result.gsda.FractionFromList;
import de.ivu.wahl.result.result.DHondtFraction;

/**
 * An AssignmentTracer keeps track of the assignments of seats by largest average and the D'Hondt
 * fractions involved.
 */
public class DHondtAssignmentTracker<T extends GeneralList> {
  private Map<T, List<DHondtFraction>> list2FractionInformation = new HashMap<T, List<DHondtFraction>>();
  private List<FractionFromList<T>> currentFractionsFromList = Collections.emptyList();
  private int fractionsIndexCounter = 0;
  private int columnIndexCounter = 0;

  public void setFractionsForAnAssignment(List<FractionFromList<T>> fractions) {
    currentFractionsFromList = new ArrayList<FractionFromList<T>>(fractions);
    fractionsIndexCounter++;
  }

  public void triggerAssignment(List<T> assignments) {
    // TODO jon 08.01.2014: Sort assignments, see OSV-1458, issue 4
    for (T list : assignments) {
      triggerAssignment(list);
    }
  }

  private void triggerAssignment(T assignedList) {
    columnIndexCounter++;
    for (FractionFromList<T> fractionFromList : currentFractionsFromList) {
      T list = fractionFromList.getList();
      List<DHondtFraction> fractions = getDHondtFractions(list);
      boolean receivesSeat = (assignedList.equals(list));
      DHondtFraction fractionInformation = new DHondtFraction(fractionFromList.getNumerator(),
          fractionFromList.getDenominator(), receivesSeat, fractionsIndexCounter,
          columnIndexCounter);
      fractions.add(fractionInformation);
    }
  }

  public List<DHondtFraction> getDHondtFractions(T list) {
    List<DHondtFraction> fractions = list2FractionInformation.get(list);
    if (fractions == null) {
      fractions = new ArrayList<DHondtFraction>();
      list2FractionInformation.put(list, fractions);
    }
    return fractions;
  }

}
