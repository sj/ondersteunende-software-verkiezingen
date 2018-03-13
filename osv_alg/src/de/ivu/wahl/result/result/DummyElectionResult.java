/*
 * DummyElectionResult
 * 
 * Created on 04.02.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.result;

import java.util.List;
import java.util.Map;

import de.ivu.wahl.result.determination.GeneralList;
import de.ivu.wahl.result.determination.P2List;
import de.ivu.wahl.result.determination.P3List;
import de.ivu.wahl.result.drawlots.DecisionType;
import de.ivu.wahl.result.drawlots.DrawingLotsAlternative;

/**
 * Dummy-Implementation for use in test environment
 * 
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public class DummyElectionResult implements ElectionResult {

  public void setFictitiousDistributionResult(Map<P3List, Long> listsAndSeats) {
    // dummy
  }

  public void addDHondtFractions(GeneralList list, GeneralList parent, Distribution distribution,
      long priorSeats, long noOfDHondtSeats, List<DHondtFraction> dHondtFractions, long votes) {
    // dummy
  }

  public void checkedCombinedList(CheckedCombinedList parameterObject) {
    // dummy
  }

  public void assignSeats(Assignment assignment) {
    // dummy
  }

  public void addAnomalitiy(Anomaly anomaly) {
    // dummy
  }

  public void subDistributionStarted(final GeneralList p42OrP3List, final long votes,
      final long seats, Distribution distribution) {
    // dummy
  }

  public void candidateResult(CandidateResult candidateResult) {
    // dummy
  }

  public void setConflict(final DecisionType type,
      final List<? extends DrawingLotsAlternative> alternatives) {
    // dummy
  }

  public void totalSeatsAssigned(final P2List p2List, final long seats) {
    // dummy
  }

  @Override
  public void setNumberOfUnassignedSeatsBeforeDHondt(int numberOfUnassignedSeatsBeforeDHondt) {
    // dummy
  }

}
