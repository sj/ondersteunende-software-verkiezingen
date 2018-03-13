/*
 * AbstractElectionResultDeterminator
 * 
 * Created on 02.01.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.determination;

import static de.ivu.wahl.result.determination.ElectionResultDeterminator.APPLOG;

import java.util.Map;

import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.drawlots.DrawingLotsCallback;
import de.ivu.wahl.result.gsda.GeneralSeatDistributor;
import de.ivu.wahl.result.gsda.GsdaParameters;
import de.ivu.wahl.result.gsda.GsdaResult;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;
import de.ivu.wahl.result.result.AnomalyFactory;
import de.ivu.wahl.result.result.AssignmentSupplement;
import de.ivu.wahl.result.result.AssignmentType;
import de.ivu.wahl.result.result.Distribution;
import de.ivu.wahl.result.result.ElectionResult;
import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;

/**
 * Utility superclass implementing some methods used by the concrete subclasses.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public abstract class AbstractElectionResultDeterminator<T extends GeneralList, P extends GeneralList> {

  /**
   * @return an unmodifiable Map with the number of seats for each P2 list or P3 list
   */
  public GsdaResult<T> calculate(DrawingLotsCallback drawingLotsCallback, P parent,
      long totalSeats, Map<T, Long> listsAndVotes, Map<T, Long> listsAndCandidates,
      ElectionResult electionResult, AnomalyFactory anomalyFactory,
      ElectionSubcategory electionSubcategory, Distribution distribution) {

    long totalVotes = getTotalVotes(listsAndVotes);
    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_AssignmentSeatsWithin,
        totalSeats,
        parent.getName()));

    if (totalSeats > 0 && listsAndVotes.keySet().size() > 1) {
      APPLOG.info("*** " //$NON-NLS-1$
          + Messages.bind(MessageKeys.Result_Tracelog_ElectoralQuota,
              parent.getName(),
              Util.displayQuotient(totalVotes, totalSeats)));
      assignmentStarted(parent, totalVotes, totalSeats, electionResult);

      GsdaParameters parameters;
      if (needsCosalgMode(parent)) {
        parameters = GsdaParameters.forP2DistributionTkPs2();
      } else {
        parameters = GsdaParameters.forP2P3Distribution();
      }
      GeneralSeatDistributor<T, P> generalSeatDistributor = new GeneralSeatDistributor<T, P>(
          drawingLotsCallback, parent, totalSeats, listsAndVotes, listsAndCandidates, parameters,
          electionResult, anomalyFactory, distribution);
      return generalSeatDistributor.calculate();
    } else {
      return new GsdaResult<T>(trivialDistribution(parent,
          totalSeats,
          listsAndCandidates,
          electionResult));
    }
  }

  /**
   * @param parent
   * @return <code>false</code> in general. May be overwritten in subclasses.
   */
  protected boolean needsCosalgMode(P parent) {
    return false;
  }

  private long getTotalVotes(Map<T, Long> listsAndVotes) {
    long totalVotes = 0;
    for (Long votes : listsAndVotes.values()) {
      totalVotes += votes;
    }
    return totalVotes;
  }

  /**
   * The trivial cases: no seats or only one list
   */
  private Map<T, Long> trivialDistribution(P parent, long totalSeats,
      Map<T, Long> listsAndCandidates, ElectionResult electionResult) {

    final int index = 1;
    final boolean byLot = false;
    final boolean cosalgMode = false;
    final boolean fictitious = false;
    final AssignmentType assignmentType = AssignmentType.TRIVIAL;

    AssignmentTracer<T, P> assignmentTracer = new AssignmentTracer<T, P>(listsAndCandidates,
        parent, electionResult);
    for (T list : listsAndCandidates.keySet()) {
      assignmentTracer.assignment(index,
          list,
          assignmentType,
          byLot,
          totalSeats,
          cosalgMode,
          fictitious,
          AssignmentSupplement.NULL);
    }
    assignmentTracer.finishAssignment(index, cosalgMode);

    return assignmentTracer.getResult();
  }

  abstract protected void assignmentStarted(P parent, long totalVotes, long totalSeats,
      ElectionResult electionResult);

}
