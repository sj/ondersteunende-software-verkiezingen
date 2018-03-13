/*
 * CombinedListValidator
 * 
 * Created on 18.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.determination;

import static de.ivu.wahl.result.determination.ElectionResultDeterminator.APPLOG;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.ivu.wahl.result.ElectionAndCandidatesAndVotes;
import de.ivu.wahl.result.Fraction;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.builder.CombinedList;
import de.ivu.wahl.result.drawlots.DrawingLotsCallback;
import de.ivu.wahl.result.gsda.GeneralSeatDistributor;
import de.ivu.wahl.result.gsda.GsdaParameters;
import de.ivu.wahl.result.gsda.GsdaResult;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;
import de.ivu.wahl.result.result.AnomalyFactory;
import de.ivu.wahl.result.result.CheckedCombinedList;
import de.ivu.wahl.result.result.Distribution;
import de.ivu.wahl.result.result.ElectionResult;
import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;

/**
 * Determines the validity of combined lists. Removes P3-lists from a combined list if it did not
 * reach the electoral quota. Validates if the remaining combined lists have at least 2 elements.
 * Creates P4.2-lists from the valid combined lists and the remaining P3-lists.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class CombinedListValidator {
  private final ElectionAndCandidatesAndVotes ecv;
  private final VotesCounter votesCounter;
  private final Fraction electoralQuota;

  @SuppressWarnings("hiding")
  public CombinedListValidator(ElectionAndCandidatesAndVotes ecv, VotesCounter votesCounter) {
    this.ecv = ecv;
    this.votesCounter = votesCounter;
    this.electoralQuota = new Fraction(votesCounter.getTotalNumberOfVotes(), ecv.getNumberOfSeats());
  }

  /**
   * @return an unmodifiable map of P42 lists (key) and the respective number of votes (value)
   */
  public Map<P42List, Long> determineValidityOfCombinedLists(ElectionResult electionResult,
      AnomalyFactory anomalyFactory) {
    boolean isFictitiousDistributionNecessary = isFictitiousDistributionNecessary();
    GsdaResult<P3List> fictitiousDistribution = null;

    if (isFictitiousDistributionNecessary) {
      GsdaParameters parameters = getGsdaParameters(ecv.getElectionSubcategory());
      DrawingLotsCallback drawingLotsCallback = null;
      ParentOfP42List parent = null;
      Map<P3List, Long> votesPerP3List = votesCounter.getVotesPerP3List();
      CandidatesCounter candidatesCounter = new CandidatesCounter(votesPerP3List.keySet(), ecv);
      Map<P3List, Long> numbersOfCandidatesPerP3List = candidatesCounter.getCandidatesPerP3List();
      APPLOG.info(Messages.getString(MessageKeys.Result_Tracelog_FicitiousSeatDistribution));
      GeneralSeatDistributor<P3List, ParentOfP42List> generalSeatDistributor = new GeneralSeatDistributor<P3List, ParentOfP42List>(
          drawingLotsCallback, parent, ecv.getNumberOfSeats(), votesPerP3List,
          numbersOfCandidatesPerP3List, parameters, electionResult, anomalyFactory,
          Distribution.FICTITIOUS);
      fictitiousDistribution = generalSeatDistributor.calculate();
      electionResult.setFictitiousDistributionResult(fictitiousDistribution.getListsAndSeats());
    }

    List<P42List> result = new ArrayList<P42List>();

    // In remainingP3Lists collect all P3-lists that do not belong to a combined list.
    // Initialize remainingP3Lists with ALL P3-lists
    Set<P3List> remainingP3Lists = new HashSet<P3List>(ecv.getP3Lists());
    Collection<CombinedList> combinedLists = ecv.getCombinedLists();
    if (ElectionSubcategory.EK.equals(ecv.getElectionSubcategory()) && !combinedLists.isEmpty()) {
      throw new UnsupportedOperationException(
          "There shall not be any combined lists for EK elections."); //$NON-NLS-1$
    }
    for (CombinedList combinedList : combinedLists) {
      APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_DeterminingValidityCombinedList)
          + Util.displayNamedObjects(combinedList.getP3Lists()));
      List<P3List> p3ListsOverKT = new ArrayList<P3List>();
      List<P3List> p3ListsUnderKT = new ArrayList<P3List>();
      for (P3List p3List : combinedList.getP3Lists()) {
        if (hasReachedElectoralQuota(p3List)
            || reachedASeatInFictitiousDistribution(fictitiousDistribution, p3List)) {
          // remove a P3-list from remainingP3Lists, if it belongs to a combined list
          // and has reachted the electoral quota or received a seat in the fictitious distribution
          p3ListsOverKT.add(p3List);
          remainingP3Lists.remove(p3List);
        } else {
          p3ListsUnderKT.add(p3List);
          APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_ListRemovedFromCombinedList,
              p3List.getNumber(),
              p3List.getName()));
        }
      }
      if (p3ListsOverKT.size() >= 2) {
        P42List p42List = new P42List(p3ListsOverKT, combinedList);
        result.add(p42List);
        APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_ListsFormValidCombinedList)
            + Util.displayNamedObjects(p3ListsOverKT));
      } else if (p3ListsOverKT.size() == 1) {
        APPLOG.info(Messages
            .bind(MessageKeys.Result_Tracelog_CombinedListHasOnlyOneListReachedElectoralQuota));
        // Re-insert a P3-list into remainingP3Lists, if the combined list contains only this one
        // element
        remainingP3Lists.addAll(p3ListsOverKT);
      } else if (p3ListsOverKT.size() == 0) {
        APPLOG.info(Messages
            .bind(MessageKeys.Result_Tracelog_CombinedListHasNoListReachedElectoralQuota));
      }
      boolean valid = (p3ListsOverKT.size() >= 2);
      CheckedCombinedList checkedCombinedList = new CheckedCombinedList(combinedList,
          p3ListsOverKT, p3ListsUnderKT, valid);
      electionResult.checkedCombinedList(checkedCombinedList);
    }

    // Create one-element P42-lists for all P3-lists that do not belong to a combined list
    for (P3List p3List : remainingP3Lists) {
      P42List p42List = new P42List(Collections.singleton(p3List), null);
      result.add(p42List);
    }

    return addVotesFor(result);
  }

  /**
   * @return GsdaParameters for the fictitious seat distribution
   */
  private GsdaParameters getGsdaParameters(ElectionSubcategory electionSubcategory) {
    if (ElectionSubcategory.GR1.equals(electionSubcategory)
        || ElectionSubcategory.AB1.equals(electionSubcategory)
        || ElectionSubcategory.ER1.equals(electionSubcategory)
        || ElectionSubcategory.GC.equals(electionSubcategory)) {
      return GsdaParameters.forFictitiousDistributionGR1();
    } else if (ElectionSubcategory.TK.equals(electionSubcategory)
        || ElectionSubcategory.EP.equals(electionSubcategory)) {
      return GsdaParameters.forFictitiousDistributionEpTk();
    } else if (ElectionSubcategory.BC.equals(electionSubcategory)) {
      return GsdaParameters.forFictitiousDistributionBC();
    } else if (ElectionSubcategory.EK.equals(electionSubcategory)) {
      // No fictitious seat distribution for EK election
      throw new UnsupportedOperationException("No fictitious seat distribution for EK election"); //$NON-NLS-1$
    } else {
      return GsdaParameters.forFictitiousDistribution();
    }
  }

  private boolean reachedASeatInFictitiousDistribution(GsdaResult<P3List> fictitiousDistribution,
      P3List p3List) {
    return fictitiousDistribution != null && fictitiousDistribution.get(p3List).longValue() > 0;
  }

  /**
   * Fictitious seat distribution is necessary for most election types (other than EK) and if there
   * is a combined list. For TK and EP elections the fictitious seat distribution is only necessary
   * to fill the model P22-1. If all P3-lists in all combined lists have reached the electoral
   * quota, the fictitious seat distribution is only necessary to fill the model P22-2 or P22-1.
   * 
   * @return <code>true</code>, if a fictitious seat distribution is necessary
   */
  private boolean isFictitiousDistributionNecessary() {
    return !ElectionSubcategory.EK.equals(ecv.getElectionSubcategory())
        && !ecv.getCombinedLists().isEmpty();
  }

  /**
   * @return <code>true</code>, if the p3List has as many votes as the electoral quota or more,
   *         otherwise <code>false</code>
   */
  private boolean hasReachedElectoralQuota(P3List p3List) {
    return electoralQuota.compareTo(getVotes(p3List)) <= 0;
  }

  /**
   * @return the total number of votes of a p3List
   */
  private long getVotes(P3List p3List) {
    return votesCounter.getVotesPerP3List().get(p3List).longValue();
  }

  /**
   * Helper method to add the votes of the P3 lists contained in a P42 list.
   */
  private Map<P42List, Long> addVotesFor(List<P42List> p42Lists) {
    Map<P42List, Long> result = new HashMap<P42List, Long>();
    for (P42List p42List : p42Lists) {
      long sum = 0;
      for (P3List p3List : p42List.getP3Lists()) {
        sum += votesCounter.getVotesPerP3List().get(p3List);
      }
      result.put(p42List, Long.valueOf(sum));
    }
    return Collections.unmodifiableMap(result);
  }

}
