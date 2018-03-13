/*
 * GeneralSeatDistributor
 * 
 * Created on 03.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.gsda;

import static de.ivu.wahl.result.determination.ElectionResultDeterminator.APPLOG;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.ivu.wahl.result.Fraction;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.determination.AssignmentTracer;
import de.ivu.wahl.result.determination.DHondtAssignmentTracker;
import de.ivu.wahl.result.determination.GeneralList;
import de.ivu.wahl.result.determination.P2List;
import de.ivu.wahl.result.determination.P3List;
import de.ivu.wahl.result.determination.P42List;
import de.ivu.wahl.result.drawlots.DecisionType;
import de.ivu.wahl.result.drawlots.DrawingLotsAlternative;
import de.ivu.wahl.result.drawlots.DrawingLotsCallback;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;
import de.ivu.wahl.result.result.AnomalyFactory;
import de.ivu.wahl.result.result.AssignmentSupplement;
import de.ivu.wahl.result.result.AssignmentSupplementVotes;
import de.ivu.wahl.result.result.AssignmentSupplementWithRemainderFraction;
import de.ivu.wahl.result.result.AssignmentType;
import de.ivu.wahl.result.result.DHondtFraction;
import de.ivu.wahl.result.result.Distribution;
import de.ivu.wahl.result.result.ElectionResult;
import de.ivu.wahl.result.result.StepType;

/**
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class GeneralSeatDistributor<T extends GeneralList, P extends GeneralList> {
  private final FractionFactory<T> _dHondtFractionFactory = new FractionFactory<T>() {
    @SuppressWarnings("synthetic-access")
    public FractionFromList<T> newFraction(T aList) {
      long votes = getVotes(aList);
      long seats = _assignmentTracer.getTotalAssignment(aList);
      return new FractionFromList<T>(aList, votes, seats + 1);
    }
  };

  // Unmodifiable attributes
  protected final ElectionResult _electionResult;
  private final AnomalyFactory _anomalyFactory;
  private final long _totalSeats;
  private final long _totalVotes;
  private final Fraction _quota;
  private final Set<T> _lists;
  private final Map<T, Long> _numbersOfVotes;
  // private final Map<T, Long> _numbersOfCandidates;
  private final GsdaParameters _parameters;
  private final DrawingLotsCallback _drawingLotsCallback;
  private final Distribution _distribution;

  // State
  private final AssignmentTracer<T, P> _assignmentTracer;
  private final DHondtAssignmentTracker<T> _dHondtAssignmentTracer;
  private int _index = 0;
  private StepType _stepType;
  private Set<T> _listsTakingPart = Collections.emptySet(); // P_i-1
  private Set<T> _drawingLotsLoosers = Collections.emptySet(); // D_i-1
  private Set<T> _assignmentForSmallestAverageOrRemainder = Collections.emptySet(); // J_i
  private final Set<T> _receivedLargestAverageRestrictedSeat = new HashSet<T>(); // M_i-1
  private boolean _cosalgModeOn = false;

  // Result before cosalg mode
  private Map<T, Long> _resultBeforeCosalgMode;
  private int _cosalgSwitchStepNumber = -1;
  private PartlyRamdomIterator<T> _rbs;

  /**
   * Constructor
   */
  public GeneralSeatDistributor(DrawingLotsCallback drawingLotsCallback,
      P parent,
      long seats,
      Map<T, Long> numbersOfVotes,
      Map<T, Long> numbersOfCandidates,
      GsdaParameters parameters,
      ElectionResult electionResult,
      AnomalyFactory anomalyFactory,
      Distribution distribution) {
    assert numbersOfVotes.keySet().equals(numbersOfCandidates.keySet()) : Messages
        .bind(MessageKeys.Result_Assert_NumberOfVotesAndNumberOfCandidatesBaesdOnDifferentSets,
            numbersOfVotes.keySet(),
            numbersOfCandidates.keySet());
    this._lists = Util.createUnmodifiableCopy(numbersOfVotes.keySet());
    this._drawingLotsCallback = drawingLotsCallback;

    this._totalSeats = seats;
    long totalVotes = 0;
    for (Long votes : numbersOfVotes.values()) {
      totalVotes += votes;
    }
    // total votes = 0 causes a division by zero.
    // In that case setting total votes to 1 causes the seats to be distributed evenly between all
    // lists.
    totalVotes = Math.max(totalVotes, 1);

    this._totalVotes = totalVotes;
    this._quota = new Fraction(totalVotes, seats);
    this._numbersOfVotes = Util.createUnmodifiableCopy(numbersOfVotes);
    // this._numbersOfCandidates = Util.createUnmodifiableCopy(numbersOfCandidates);
    this._parameters = parameters;

    this._assignmentTracer = new AssignmentTracer<T, P>(numbersOfCandidates, parent, electionResult);
    this._dHondtAssignmentTracer = new DHondtAssignmentTracker<T>();
    this._electionResult = electionResult;
    this._anomalyFactory = anomalyFactory;
    this._distribution = distribution;
  }

  /**
   * Determine the assignment of seats to the lists.
   */
  public GsdaResult<T> calculate() {
    mainLoop();

    if (!_parameters.isB7_assignmentInListGroup()) {
      // return the normal result
      writeDHondtFractionsToElectionResult();
      return new GsdaResult<T>(_assignmentTracer.getResult());
    }

    // Special treatment if parameter b7 == true
    if (!_cosalgModeOn) {
      // This is the case that all lists are exhausted without switching on the cosalg mode.
      // This probably can never happen, but I will handle it correctly anyway.
      _rbs = new PartlyRamdomIterator<T>(_assignmentTracer.getRollBackSequence(),
          _drawingLotsCallback, DecisionType.ROLL_BACK);
      List<Set<T>> emptyList = Collections.emptyList();
      PartlyRamdomIterator<T> _rfs = new PartlyRamdomIterator<T>(emptyList, _drawingLotsCallback,
          DecisionType.ROLL_FORWARD);
      writeDHondtFractionsToElectionResult();
      return new GsdaResult<T>(_assignmentTracer.getResult(), _rbs, _rfs);
    }

    // Now the "normal" special case with cosalg mode on.
    PartlyRamdomIterator<T> _rfs = new PartlyRamdomIterator<T>(
        _assignmentTracer.getRollForwardSequence(_cosalgSwitchStepNumber + 1),
        _drawingLotsCallback, DecisionType.ROLL_FORWARD);
    // do not writeDHondtFractionsToElectionResult, because this has already happened
    return new GsdaResult<T>(_resultBeforeCosalgMode, _rbs, _rfs);
  }

  private void mainLoop() {
    // init state
    _listsTakingPart = new HashSet<T>(_lists); // P_0

    // start calculation loop
    _index = 1;
    _stepType = determineNextStepType();
    traceStepType(_stepType, _index);
    while (!isTerminationStep()) {
      executeStep();
      _index++;
      _stepType = determineNextStepType();
      traceStepType(_stepType, _index);
    }
  }

  /**
   * @return <code>true</code>, if the current _stepType is one that is infinitely repeated. In this
   *         case the assignment in terminated.
   */
  private boolean isTerminationStep() {
    return StepType.ALL_LISTS_EXHAUSTED.equals(_stepType)
        || StepType.ALL_SEATS_ASSIGNED.equals(_stepType);
  }

  // ************************************************
  // ********* Determine next step type **********
  // ************************************************

  /**
   * @return
   */
  private StepType determineNextStepType() {
    if (_index == 1) {
      return StepType.FIRST_ASSIGNMENT; // 1
    }
    if (_listsTakingPart.isEmpty()) {
      return StepType.ALL_LISTS_EXHAUSTED; // 2
    }

    long unassignedSeats = getUnassignedSeats(); // r_i-1

    // Continued drawing lots
    if (unassignedSeats > 0 && !_drawingLotsLoosers.isEmpty()) {
      if (_stepType.equals(StepType.NIEMEYER_ASSIGNMENT)
          || _stepType.equals(StepType.CONTINUED_DRAWING_LOTS_NIEMEYER)) {
        return StepType.CONTINUED_DRAWING_LOTS_NIEMEYER; // 8
      } else {
        if (_stepType.equals(StepType.DHONDT_ASSIGNMENT_RESTRICTED)
            || _stepType.equals(StepType.CONTINUED_DRAWING_LOTS_DHONDT_RESTRICTED)) {
          return StepType.CONTINUED_DRAWING_LOTS_DHONDT_RESTRICTED; // 9
        }
        return StepType.CONTINUED_DRAWING_LOTS_DHONDT; // 10
      }
    }

    // We know that i > 0 and P_i-1 is not empty.
    boolean c0_residualSeatCondition = (unassignedSeats > 0 && _drawingLotsLoosers.isEmpty());
    // If c_0 = true, residual seats are assigned.
    if (c0_residualSeatCondition) {
      if (c3ConditionForLargestRemainder()) {
        return StepType.NIEMEYER_ASSIGNMENT; // 3
      }
      if (c4ConditionForLargestAverageRestricted()) {
        return StepType.DHONDT_ASSIGNMENT_RESTRICTED; // 4
      }
      return StepType.DHONDT_ASSIGNMENT; // 5
    }

    // We know that i > 0 and P_i-1 is not empty and unassignedSeats <= 0
    if (c6ConditionForAbsoluteMajority()) {
      return StepType.ABSOLUTE_MAJORITY;
    }
    if (isAnyListExhausted()) {
      return StepType.EXHAUSTED_LIST;
    }
    if (!_parameters.isB7_assignmentInListGroup() || _cosalgModeOn) {
      return StepType.ALL_SEATS_ASSIGNED;
    }
    return StepType.SWITCH_COSALG_MODE_ON;
  }

  /**
   * PRE: c0_residualSeatCondition = true
   * 
   * @return if the next step shall be a StepType.NIEMEYER_ASSIGNMENT
   */
  private boolean c3ConditionForLargestRemainder() {
    if (!_parameters.isB4_includeLargestRemainder()) {
      return false;
    }

    return !getListsWithoutResidualSeat().isEmpty();
  }

  /**
   * PRE: c0_residualSeatCondition == true && c3ConditionForLargestRemainder() == false
   * 
   * @return if the next step shall be a StepType.DHONDT_ASSIGNMENT_RESTRICTED
   */
  private boolean c4ConditionForLargestAverageRestricted() {
    if (!_parameters.isB5_oneSeatByLargestAverage()) {
      return false;
    }

    for (T list : _listsTakingPart) {
      if (!_receivedLargestAverageRestrictedSeat.contains(list)) {
        return true;
      }
    }
    return false;
  }

  /**
   * PRE: i > 0 && P_i-1 is not empty && unassignedSeats <= 0
   * 
   * @return true, if a P4.2-list that takes part in the step has the absolute majority of votes but
   *         not the absolute majority of seats.
   */
  private boolean c6ConditionForAbsoluteMajority() {
    return _parameters.isB6_withAbsoluteMajorityRegulation() && getAbsoluteMajorityList() != null;
  }

  /**
   * @return the P4.2-list that takes part in the step has the absolute majority of votes but not
   *         the absolute majority of seats. <code>null</code> if no such list exists.
   */
  private T getAbsoluteMajorityList() {
    for (T list : _listsTakingPart) {
      long votes = getVotes(list);
      long seats = _assignmentTracer.getTotalAssignment(list);
      if (2 * votes > _totalVotes & 2 * seats <= _totalSeats) {
        return list;
      }
    }
    return null;
  }

  /**
   * @return <code>true</code>, if the algorithm is parameterized to consider exhausted lists and
   *         any of the lists taking part in this step has more seats assigned than it has
   *         candidates.
   */
  private boolean isAnyListExhausted() {
    return _assignmentTracer.isAnyListExhausted(_listsTakingPart);
  }

  // ************************************************
  // *************** Execute steps ****************
  // ************************************************

  private void executeStep() {
    assert _stepType != null : Messages.bind(MessageKeys.Builder_Assert_MustNotBeNull, "_stepType"); //$NON-NLS-1$

    // First Assignment
    if (_stepType.equals(StepType.FIRST_ASSIGNMENT)) {
      performFirstAssignment();
      setNoDrawingLotsLoosers();
      setNoAssignmentForSmallestAverageOrRemainder();
      return;
    }

    // Assignment by Niemeyer
    if (_stepType.equals(StepType.NIEMEYER_ASSIGNMENT)) {
      performNiemeyerAssignment();
      return;
    }

    // Assignment by d'Hondt restricted to one seat per list
    if (_stepType.equals(StepType.DHONDT_ASSIGNMENT_RESTRICTED)) {
      performDHondtAssignmentRestricted();
      return;
    }

    // Assignment by d'Hondt
    if (_stepType.equals(StepType.DHONDT_ASSIGNMENT)) {
      performDHondtAssignment();
      return;
    }

    // Consider the case that a list has received the absolute majority of votes but not the
    // absolute majority of seats.
    if (_stepType.equals(StepType.ABSOLUTE_MAJORITY)) {
      considerAbsoluteMajority();
      setNoDrawingLotsLoosers();
      setNoAssignmentForSmallestAverageOrRemainder();
      return;
    }

    // Lists are exhausted
    if (_stepType.equals(StepType.EXHAUSTED_LIST)) {
      performExhaustedListsStep();
      setNoDrawingLotsLoosers();
      setNoAssignmentForSmallestAverageOrRemainder();
      return;
    }

    // In the previous step drawing lots was required and has to be continued in this step
    if (_stepType.equals(StepType.CONTINUED_DRAWING_LOTS_NIEMEYER)
        || _stepType.equals(StepType.CONTINUED_DRAWING_LOTS_DHONDT_RESTRICTED)
        || _stepType.equals(StepType.CONTINUED_DRAWING_LOTS_DHONDT)) {
      performContinuedDrawingLots(_drawingLotsCallback);
      return;
    }

    if (_stepType.equals(StepType.SWITCH_COSALG_MODE_ON)) {
      switchCosalgModeOn(_drawingLotsCallback);
      return;
    }
  }

  /**
   * Performs the first assignment of seats to lists by only considering the whole-number part of
   * the fraction
   */
  private void performFirstAssignment() {
    List<T> listsWithSeats = new ArrayList<T>();
    List<T> listsWithoutSeat = new ArrayList<T>();
    APPLOG.info(Messages.getString(MessageKeys.Result_Tracelog_FirstAssignment));
    AssignmentType assignmentType = AssignmentType.FIRST_ASSIGNMENT;

    // For each list calculate the first assignment (even if it is 0)
    for (T list : _lists) {
      long votes = getVotes(list);
      long seats = votes * _totalSeats / _totalVotes;

      // Prepare supplementary information for the process verbal
      final AssignmentSupplement supplement;
      long remainderNumerator = votes * _totalSeats - seats * _totalVotes;
      if ((list instanceof P2List) || (list instanceof P3List)
          || (list instanceof P42List && _parameters.isB4_includeLargestRemainder())) {
        long remainderDenominator = _totalSeats;
        Fraction remainder = new Fraction(remainderNumerator, remainderDenominator);
        supplement = new AssignmentSupplementWithRemainderFraction(votes, remainder);
      } else if (list instanceof P42List) {
        supplement = new AssignmentSupplementVotes(votes);
      } else {
        supplement = AssignmentSupplement.NULL;
      }
      // Assign seats
      assignment(list, assignmentType, false, seats, supplement);

      // Write log
      APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_FirstAssignmentFor,
          list.getName(),
          votes,
          _totalSeats,
          _totalVotes,
          Util.displayQuotient(votes * _totalSeats, _totalVotes)));

      if (seats == 0) {
        listsWithoutSeat.add(list);
      } else {
        listsWithSeats.add(list);
      }
    }
    finishAssignment();

    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_ResidualSeatsAfterFirstAssignment,
        getUnassignedSeats()));
    boolean b2 = _parameters.isB2_minimumForResidualSeat();
    if (b2) {
      APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_ListsThatReceivedNoSeat,
          Util.displayNamedObjects(listsWithoutSeat)));
      APPLOG.info(Messages
          .bind(MessageKeys.Result_Tracelog_ListsThatReceivedOneOrMoreSeatsInFirstAssignment,
              Util.displayNamedObjects(listsWithSeats)));
    }

    if (b2) {
      // Only lists with at least one seat may take part in the following iterations
      _listsTakingPart = new HashSet<T>(listsWithSeats);
    } else {
      // Only lists with at least one vote may take part in the following iterations
      _listsTakingPart = new HashSet<T>();
      for (T list : _lists) {
        if (getVotes(list) > 0) {
          _listsTakingPart.add(list);
        }
      }
    }
  }

  /**
   * One assignment step of residual seats according to Hare-Niemeyer.
   */
  private void performNiemeyerAssignment() {
    final Collection<T> lists = getListsWithoutResidualSeat();
    FractionFactory<T> fractionFactory = new FractionFactory<T>() {
      @SuppressWarnings("synthetic-access")
      public FractionFromList<T> newFraction(T aList) {
        long votes = getVotes(aList);
        long seats = _assignmentTracer.getTotalAssignment(aList);
        return new FractionFromList<T>(aList, votes * _totalSeats - _totalVotes * seats,
            _totalVotes);
      }
    };
    assignResidualSeats(lists, fractionFactory, AssignmentType.NIEMEYER_ASSIGNMENT);
  }

  /**
   * @return all lists that take part in the step and that have no more seats than after the first
   *         assignment. If b3 > 0, also check if the list attained 75% (25% for BC elections) of
   *         the electoral quota.
   */
  private List<T> getListsWithoutResidualSeat() {
    Fraction fraction = _parameters.getB3_minimumForLargestRemainder();
    Fraction fractionOfQuota = fraction.times(_quota);

    List<T> result = new ArrayList<T>();
    for (T list : _listsTakingPart) {
      long firstAssignment = _assignmentTracer.getAssignment(1, list);
      long totalAssignment = _assignmentTracer.getTotalAssignment(list);
      if (totalAssignment == firstAssignment && (fractionOfQuota.compareTo(getVotes(list)) <= 0)) {
        result.add(list);
      }
    }
    return result;
  }

  /**
   * One assignment of residual seats according to d'Hondt restricted to one seat per list.
   */
  private void performDHondtAssignmentRestricted() {
    if (!_cosalgModeOn) {
      _assignmentTracer.preDHondtAssignment(_listsTakingPart, _totalSeats);
    }
    Set<T> lists = new HashSet<T>(_listsTakingPart);
    lists.removeAll(_receivedLargestAverageRestrictedSeat);

    Collection<T> nextAssignment = assignResidualSeats(lists,
        _dHondtFractionFactory,
        AssignmentType.DHONDT_ASSIGNMENT);
    _receivedLargestAverageRestrictedSeat.addAll(nextAssignment);
  }

  /**
   * One assignment of residual seats according to d'Hondt
   */
  private void performDHondtAssignment() {
    if (!_cosalgModeOn) {
      _assignmentTracer.preDHondtAssignment(_listsTakingPart, _totalSeats);
    }
    Set<T> lists = new HashSet<T>(_listsTakingPart);
    assignResidualSeats(lists, _dHondtFractionFactory, AssignmentType.DHONDT_ASSIGNMENT);
  }

  /**
   * Consider the case that a P42 list has received the absolute majority of votes but not the
   * absolute majority of seats.
   */
  private void considerAbsoluteMajority() {
    // Determine winner
    final T listWithAbsoluteMajority = getAbsoluteMajorityList();
    // Determine potential loosers
    final List<T> lastAssignedLists = asSortedList(_assignmentForSmallestAverageOrRemainder);
    assert lastAssignedLists.size() > 0 : Messages.bind(MessageKeys.Builder_Assert_MustNotBeEmpty,
        "lastAssignedLists"); //$NON-NLS-1$
    // Determine looser
    final T listLosingOneSeat; // may be null
    final boolean drawingLotsRequired = (lastAssignedLists.size() > 1);
    if (drawingLotsRequired) {
      if (_parameters.isB1_fictitious()) {
        // In fictitious seat distribution no list looses a seat here
        listLosingOneSeat = null;
      } else {
        listLosingOneSeat = selectListByLot(_drawingLotsCallback,
            lastAssignedLists,
            DecisionType.ABSOLUTE_MAJORITY);
      }
    } else {
      listLosingOneSeat = lastAssignedLists.get(0);
    }
    assert !listWithAbsoluteMajority.equals(listLosingOneSeat) : Messages
        .bind(MessageKeys.Builder_Assert_ListWithAbsoluteMajorityCannotReceiveLastResidualSeat);

    // assign seats
    final AssignmentType assignmentType = AssignmentType.ABSOLUTE_MAJORITY;
    for (T list : _listsTakingPart) {
      final boolean byLot;
      final long seats;
      if (list.equals(listWithAbsoluteMajority)) {
        byLot = false;
        seats = 1;
      } else if (list.equals(listLosingOneSeat)) {
        byLot = drawingLotsRequired;
        seats = -1;
      } else if (lastAssignedLists.contains(list)) {
        byLot = drawingLotsRequired;
        seats = 0;
      } else {
        byLot = false;
        seats = 0;
      }
      assignment(list, assignmentType, byLot, seats);
    }

    finishAssignment();

    // Write trace log
    final long absoluteMajorityOfSeats = (_totalSeats / 2) + 1;
    final long absoluteMajorityOfVotes = (_totalVotes / 2) + 1;
    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_ReceivedAbsoluteMajority,
        listWithAbsoluteMajority.getName(),
        absoluteMajorityOfVotes,
        absoluteMajorityOfSeats));
    String nameOfListLoosingSeat;
    if (listLosingOneSeat == null) {
      nameOfListLoosingSeat = Messages
          .bind(MessageKeys.Result_Tracelog_notApplicableInCurrentSituationInFictitiousDistribution);
    } else {
      nameOfListLoosingSeat = listLosingOneSeat.getName();
    }
    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_IsAssignedAnAdditionalSeat,
        listWithAbsoluteMajority.getName(),
        nameOfListLoosingSeat));
    _anomalyFactory.absoluteMajority(listWithAbsoluteMajority,
        listLosingOneSeat,
        _parameters.isB1_fictitious());
  }

  private void assignment(T list, AssignmentType assignmentType, boolean byLot, long seats) {
    assignment(list, assignmentType, byLot, seats, AssignmentSupplement.NULL);
  }

  /**
   * Generic method for assigning residual seats by the methods of largest remainder or largest
   * average.
   * 
   * @return the lists that received a seat
   */
  private Collection<T> assignResidualSeats(final Collection<T> lists,
      FractionFactory<T> fractionFactory, AssignmentType assignmentType) {
    boolean isNiemeyer = AssignmentType.NIEMEYER_ASSIGNMENT.equals(assignmentType);
    final long numberOfResidualSeats = getUnassignedSeats();

    // Create list of fractions
    final List<FractionFromList<T>> fractions = new ArrayList<FractionFromList<T>>();
    for (T list : lists) {
      fractions.add(fractionFactory.newFraction(list));
    }

    // Sort the fractions, determine the lists that reach the maximum
    Collections.sort(fractions);
    if (isNiemeyer) {
      APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_RemaindersInDescendingOrder, _index));
    } else {
      _dHondtAssignmentTracer.setFractionsForAnAssignment(fractions);
      APPLOG
          .info(Messages.bind(MessageKeys.Result_Tracelog_AverageValuesInDescendingOrder, _index));
    }
    FractionFromList<T> max = fractions.get(0);
    List<T> listsWithLargestFraction = new ArrayList<T>();
    for (FractionFromList<T> fraction : fractions) {
      if ((max.compareTo(fraction) == 0)) {
        APPLOG.info("(*) " + fraction.toString()); //$NON-NLS-1$
        listsWithLargestFraction.add(fraction.getList());
      } else {
        APPLOG.info(fraction.toString());
      }
    }

    // Determine the next assignment (if necessary by lot)
    List<T> nextAssignment = assignOneSeatToEachList(listsWithLargestFraction,
        numberOfResidualSeats,
        assignmentType,
        _drawingLotsCallback);

    if (1 < numberOfResidualSeats && numberOfResidualSeats < listsWithLargestFraction.size()) {
      _drawingLotsLoosers = new HashSet<T>(listsWithLargestFraction);
      _drawingLotsLoosers.removeAll(nextAssignment);
    } else {
      setNoDrawingLotsLoosers();
    }
    _assignmentForSmallestAverageOrRemainder = Util.asSet(nextAssignment);
    if (!isNiemeyer) {
      _dHondtAssignmentTracer.triggerAssignment(nextAssignment);
    }

    long newResidualSeats = getUnassignedSeats();
    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_ResidualSeatsAfterAssignment,
        _index,
        newResidualSeats));

    if (newResidualSeats == 0) {
      if (isNiemeyer) {
        APPLOG.info(Messages
            .bind(MessageKeys.Result_Tracelog_AllResidualSeatsAssignedByLargestRemainder));
      } else {
        APPLOG.info(Messages
            .bind(MessageKeys.Result_Tracelog_AllResidualSeatsAssignedByLargestAverage));
      }
    }

    return nextAssignment;
  }

  /**
   * After the lists have been determined that should receive the next seats, this method checks if
   * there is a sufficient number of residual seats available. If not, one of them is selected by
   * lot. This list is assigned one seat.
   * 
   * @param assignmentType
   * @return the lists that were assigned a seat
   */
  private final List<T> assignOneSeatToEachList(final List<T> lists,
      final long numberOfResidualSeats, final AssignmentType assignmentType,
      final DrawingLotsCallback lotingCallback) {
    // Determine the next assignment (if necessary by lot)
    final List<T> nextAssignment;

    if (lists.size() <= numberOfResidualSeats || _parameters.isB1_fictitious() || _cosalgModeOn) {
      nextAssignment = lists;
      assignOneSeatTo(nextAssignment, assignmentType);
    } else {
      // Drawing lots required
      final boolean dHondt = AssignmentType.DHONDT_ASSIGNMENT.equals(assignmentType);
      final DecisionType conflictType = DecisionType.getResidualSeatConflictType(dHondt,
          _distribution);

      T winner = selectListByLot(lotingCallback, lists, conflictType);
      nextAssignment = Collections.singletonList(winner);
      List<T> loosers = new ArrayList<T>(lists);
      loosers.remove(winner);
      assignSeatByLot(winner, loosers, assignmentType);
    }

    return nextAssignment;
  }

  /**
   * Assign one seat to each list in <code>nextAssignment</code> and no seat to all other lists.
   */
  private void assignOneSeatTo(final List<T> nextAssignment, final AssignmentType assignmentType) {
    final boolean byLot = false;
    for (T list : _listsTakingPart) {
      final long seats;
      if (nextAssignment.contains(list)) {
        seats = 1;
      } else {
        seats = 0;
      }
      assignment(list, assignmentType, byLot, seats);
    }

    finishAssignment();
  }

  private void assignment(T list, AssignmentType assignmentType, boolean byLot, long seats,
      AssignmentSupplement supplement) {
    _assignmentTracer.assignment(_index,
        list,
        assignmentType,
        byLot,
        seats,
        _cosalgModeOn,
        _parameters.isB1_fictitious(),
        supplement);
  }

  private void finishAssignment() {
    _assignmentTracer.finishAssignment(_index, _cosalgModeOn);
  }

  /**
   * Select one element from the given <code>base</code> list by lot.
   * 
   * @param base List to select from
   * @param drawingLotsCallback Implementation of the drawing lots
   * @return the selected element
   */
  final private T selectListByLot(DrawingLotsCallback drawingLotsCallback, List<T> base,
      DecisionType decisionType) {
    assert base != null : Messages.bind(MessageKeys.Builder_Assert_MustNotBeNull, "base"); //$NON-NLS-1$
    assert drawingLotsCallback != null : Messages.bind(MessageKeys.Builder_Assert_MustNotBeNull,
        "drawingLotsCallback"); //$NON-NLS-1$

    List<T> selection = asSortedList(base);
    List<DrawingLotsAlternative> copy = new ArrayList<DrawingLotsAlternative>();
    for (T each : selection) {
      copy.add((DrawingLotsAlternative) each);
    }
    int index = drawingLotsCallback.selectAlternative(copy, decisionType);
    return selection.get(index);
  }

  /**
   * Continue drawing lots if drawin more than one lists was required in the d'Hondt assignment.
   */
  private void performContinuedDrawingLots(final DrawingLotsCallback lotingCallback) {
    final long unassignedSeats = getUnassignedSeats(); // Before the assignment !!!
    List<T> sortedDrawingLotsLoosers = asSortedList(_drawingLotsLoosers);

    // Determine assignment type
    final AssignmentType assignmentType;
    if (_stepType.equals(StepType.CONTINUED_DRAWING_LOTS_NIEMEYER)) {
      assignmentType = AssignmentType.NIEMEYER_ASSIGNMENT;
    } else {
      assignmentType = AssignmentType.DHONDT_ASSIGNMENT;
    }

    // Assign one seat to the selected list
    final boolean isDHondt = AssignmentType.DHONDT_ASSIGNMENT.equals(assignmentType);
    final DecisionType conflictType = DecisionType.getResidualSeatConflictType(isDHondt,
        _distribution);
    final T winner = selectListByLot(lotingCallback, sortedDrawingLotsLoosers, conflictType);
    sortedDrawingLotsLoosers.remove(winner);

    // Assign seats
    assignSeatByLot(winner, sortedDrawingLotsLoosers, assignmentType);
    if (isDHondt) {
      _dHondtAssignmentTracer.triggerAssignment(Collections.singletonList(winner));
    }

    // Re-calculate the _drawingLotsLoosers
    if (1 < unassignedSeats && unassignedSeats < _drawingLotsLoosers.size()) {
      _drawingLotsLoosers.remove(winner);
    } else {
      setNoDrawingLotsLoosers();
    }

    // Add the winner to _assignmentForSmallestAverageOrRemainder
    Set<T> tempSet = Util.copy(_assignmentForSmallestAverageOrRemainder);
    tempSet.add(winner);
    _assignmentForSmallestAverageOrRemainder = Collections.unmodifiableSet(tempSet);
  }

  /**
   * List that have more or equal number of seats than candidates are removed from the
   * _listsTakingPart. List that have more seats than candidates loose these seats.
   */
  private void performExhaustedListsStep() {
    final Map<T, Long> exhaustedListsMap = _assignmentTracer.getExhaustedListsMap(_listsTakingPart);
    final List<T> sortedKeys = asSortedList(exhaustedListsMap.keySet());

    final boolean byLot = false;
    final AssignmentType assignmentType = AssignmentType.EXHAUSTED_LIST;
    for (T list : sortedKeys) {
      final long seats = -exhaustedListsMap.get(list); // seats is negative or 0
      if (_listsTakingPart.remove(list)) {
        // Only if the list has not been removed before, perform the assignment
        assignment(list, assignmentType, byLot, seats);
        if (seats != 0 && !_parameters.isB1_fictitious() && !_cosalgModeOn) {
          _anomalyFactory.listExhaustionToOtherList(list, -seats);
        }
      } else {
        // Otherwise list is already exhausted and must not have more seats than candidates
        assert seats == 0;
      }
    }
    finishAssignment();
  }

  private void switchCosalgModeOn(DrawingLotsCallback drawingLotsCallback) {
    // save current result for later
    _rbs = new PartlyRamdomIterator<T>(_assignmentTracer.getRollBackSequence(),
        drawingLotsCallback, DecisionType.ROLL_BACK);
    _resultBeforeCosalgMode = _assignmentTracer.getResult();
    writeDHondtFractionsToElectionResult();

    _cosalgModeOn = true;
    _cosalgSwitchStepNumber = _index;

    finishAssignment(); // No assignments in this step
    setNoDrawingLotsLoosers();
    setNoAssignmentForSmallestAverageOrRemainder();
  }

  /**
   * Assign one seat to the <code>drawingLotsWinner</code> by lot. Assign no seat to the
   * <code>drawingLotsLoosers</code> by lot. Assign no seat to all other lists (not by lot). Finish
   * the assignment.
   * 
   * @param drawingLotsWinner the list that receive one seat by lot
   * @param drawingLotsLoosers the lists that receive no seat by lot
   */
  private void assignSeatByLot(T drawingLotsWinner, Collection<T> drawingLotsLoosers,
      AssignmentType assignmentType) {
    for (T list : _listsTakingPart) {
      final boolean byLot;
      final long seats;
      if (list.equals(drawingLotsWinner)) {
        byLot = true;
        seats = 1;
      } else {
        if (drawingLotsLoosers.contains(list)) {
          byLot = true;
          seats = 0;
        } else {
          byLot = false;
          seats = 0;
        }
      }
      assignment(list, assignmentType, byLot, seats);
    }

    finishAssignment();
  }

  // *****************************************
  // ******** Setting state variables ********
  // *****************************************

  /**
   * Sets the _drawingLotsLoosers (D_i) to an empty Set.
   */
  private void setNoDrawingLotsLoosers() {
    _drawingLotsLoosers = Collections.emptySet();
  }

  /**
   * Sets the _assignmentForSmallestAverage (J_i) to an empty Set.
   */
  private void setNoAssignmentForSmallestAverageOrRemainder() {
    _assignmentForSmallestAverageOrRemainder = Collections.emptySet();
  }

  /**
   * Write information on the step type into the trace log
   */
  private void traceStepType(StepType stepType, int index) {
    if (stepType == null) {
      return;
    }
    String localizedStepType = Messages.getString(MessageKeys.StepType + stepType.toString());
    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_Step_0_IsOfType_1_2,
        index,
        localizedStepType,
        stepType.getId()));
  }

  /**
   * @return a new sorted List containing the elements of <code>toSort</code>
   */
  @SuppressWarnings("unchecked")
  private List<T> asSortedList(Collection<T> toSort) {
    ArrayList<T> base = new ArrayList<T>(toSort);
    if (toSort.isEmpty()) {
      return base;
    }
    T any = toSort.iterator().next();

    if (any instanceof P2List) {
      List<P2List> lists = (List<P2List>) base;
      Collections.sort(lists);
      List<T> result = (List<T>) lists;
      return result;
    }

    if (any instanceof P3List) {
      List<P3List> lists = (List<P3List>) base;
      Collections.sort(lists);
      List<T> result = (List<T>) lists;
      return result;
    }

    if (any instanceof P42List) {
      List<P42List> lists = (List<P42List>) base;
      Collections.sort(lists);
      List<T> result = (List<T>) lists;
      return result;
    }

    throw new IllegalArgumentException("Cannot sort collection " + toSort); //$NON-NLS-1$
  }

  /**
   * @return the number of seats that are not yet assigned
   */
  private long getUnassignedSeats() {
    if (!_cosalgModeOn) {
      return _totalSeats - _assignmentTracer.getTotalAssignment();
    } else {
      return _assignmentTracer.getTotalNumberOfCandidates()
          - _assignmentTracer.getTotalAssignment();
    }
  }

  private long getVotes(T list) {
    return _numbersOfVotes.get(list).longValue();
  }

  /**
   * Provide the election result object with a tabular information of d'Hondt fraction for the
   * protocol
   */
  private void writeDHondtFractionsToElectionResult() {
    if (_parameters.isB1_fictitious()) {
      return;
    }
    Map<T, Long> assignmentsBeforeDHondt = _assignmentTracer.getAssignmentsBeforeDHondt();
    if (assignmentsBeforeDHondt == null) {
      return;
    }

    // Remember the number of unassigned seats before the first assignment by largest
    // average. This is only needed for model P22-2 for GR1 elections in case a d'Hondt assignment
    // is needed after a Niemeyer assignment
    _electionResult.setNumberOfUnassignedSeatsBeforeDHondt(_assignmentTracer
        .getNumberOfUnassignedSeatsBeforeDHondt());

    // Determine the number of residual seats a list received
    for (T list : assignmentsBeforeDHondt.keySet()) {
      List<DHondtFraction> dHondtFractions = _dHondtAssignmentTracer.getDHondtFractions(list);

      long dHondtSeats = _assignmentTracer
          .getSumOfAssignmentsOfType(AssignmentType.DHONDT_ASSIGNMENT, list);
      long priorSeats = assignmentsBeforeDHondt.get(list);
      long votes = _numbersOfVotes.get(list);
      _electionResult.addDHondtFractions(list,
          _assignmentTracer.getAssignmentParent(),
          _distribution,
          priorSeats,
          dHondtSeats,
          dHondtFractions,
          votes);
    }
  }
}
