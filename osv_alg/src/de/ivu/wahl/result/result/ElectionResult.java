/*
 * ElectionResult
 * 
 * Created on 15.12.2008
 * Copyright (c) 2008 Kiesraad
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
 * This Interface is implemented by the persistancy layer of the algorithm. It needs to be called by
 * the algorithm to make the result persistant.
 * <p>
 * The following pieces of information are generated here:
 * <ul>
 * <li>Which P3-lists may be part of a combined list or not</li>
 * <li>All assignments of seats to P42-lists, P3-lists and P2-lists.</li>
 * <li>(redundant, only for the report) D'Hondt fraction from assignments to P42-lists, P3-lists and
 * P2-lists.</li>
 * <li>The result for each candidate in each P2-list (elected, dead, by lot, above preferential
 * barrier, ...)</li>
 * <li>(redundant) The number of seats assigned to P2-lists.</li>
 * <li>In case a decision by lot needs to be made: Type of decision and alternatives to draw from.</li>
 * </ul>
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface ElectionResult {

  /**
   * Set the result of a fictitious seat distribution that needs to be published in the model P22.
   */
  public void setFictitiousDistributionResult(Map<P3List, Long> listsAndSeats);

  /**
   * A combined list has been checked for validity.
   */
  public void checkedCombinedList(CheckedCombinedList checkedCombinedList);

  /**
   * For the given list, <code>maxNoOfDHondtSeatsPlus1</code> DHondt-Quotients have to be created.
   * The numerator in each case is <code>votes</code>, the denominator is priorSeats + 1, priorSeats
   * + 2 etc. upto priorSeats + maxNoOfDHondtSeatsPlus1. The first <code>noOfDHondtSeats</code> of
   * them refer to seats the list actually receives.
   */
  public void addDHondtFractions(GeneralList list, GeneralList parent, Distribution distribution,
      long priorSeats, long noOfDHondtSeats, List<DHondtFraction> dHondtFractions, long votes);

  /**
   * Assignment of seats to a GeneralList.
   */
  public void assignSeats(Assignment assignment);

  /**
   * Notes a piece of information about an exceptional event
   */
  public void addAnomalitiy(Anomaly anomaly);

  /**
   * This is called when the assignment of seats within a combined list or within a list group is
   * started. This data is needed to publish the base data of this sub-distribution in model P22.
   */
  public void subDistributionStarted(GeneralList p42OrP3List, long votes, long seats,
      Distribution distribution);

  /**
   * The election result for a candidate of a P2-list. This method must be called exactly once for
   * each candidate of each P2-list.
   */
  public void candidateResult(CandidateResult candidateResult);

  /**
   * Write the total number of seats assigned per P2-list into the ElectionResult
   */
  public void totalSeatsAssigned(P2List p2List, long seats);

  /**
   * If a conflict occurs during calculation, set here type and alternatives
   * 
   * @param type one of the types defined in SitzverteilungKonstanten
   * @param alternatives of type P42List, P3List, P2List, Candidate
   */
  public void setConflict(DecisionType type, List<? extends DrawingLotsAlternative> alternatives);

  /**
   * Remember the number of unassigned seats before the first d'Hondt assignment. This is only
   * needed for model P22-2 for GR1 elections in case a d'Hondt assignment is needed after a
   * Niemeyer assignment
   */
  public void setNumberOfUnassignedSeatsBeforeDHondt(int numberOfUnassignedSeatsBeforeDHondt);

}
