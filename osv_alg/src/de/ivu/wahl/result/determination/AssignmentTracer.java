/*
 * AssignmentTracer
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

import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;
import de.ivu.wahl.result.result.Assignment;
import de.ivu.wahl.result.result.AssignmentSupplement;
import de.ivu.wahl.result.result.AssignmentType;
import de.ivu.wahl.result.result.ElectionResult;

/**
 * An AssignmentTracer keeps track of the assignments of seats to lists. It also checks if lists are
 * exhausted.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class AssignmentTracer<T extends GeneralList, P extends GeneralList> {

  private final Map<T, List<Assignment>> _assignmentLists = new HashMap<T, List<Assignment>>();
  private final Map<T, Long> _totalAssignment = new HashMap<T, Long>();
  private final Map<T, Long> _listsAndNumberOfCandidates;
  private final P _assignmentParent;
  private final ElectionResult _electionResult;
  private Map<T, Long> _assignmentsBeforeDHondt;
  private long _numberOfUnassignedSeatsBeforeDHondt;

  /**
   * Constructor
   * 
   * @param listsAndNumberOfCandidates contains for each list the number of candidates that may
   *          receive a seat (this excludes the deceased candidates)
   */
  public AssignmentTracer(final Map<T, Long> listsAndNumberOfCandidates,
      final P assignmentParent,
      final ElectionResult electionResult) {
    this._listsAndNumberOfCandidates = Util.createUnmodifiableCopy(listsAndNumberOfCandidates);
    this._assignmentParent = assignmentParent;
    this._electionResult = electionResult;
    for (T key : this._listsAndNumberOfCandidates.keySet()) {
      _assignmentLists.put(key, new ArrayList<Assignment>());
      _totalAssignment.put(key, 0L);
    }
  }

  public void assignment(int index, T list, AssignmentType assignmentType, boolean byLot,
      long seats, boolean cosalgMode, boolean fictitious, AssignmentSupplement supplement) {
    List<Assignment> ass = _assignmentLists.get(list);
    long total = _totalAssignment.get(list) + seats;
    // Write trace log
    String key = seats == 1
        ? MessageKeys.Result_Tracelog_AssigningSeat
        : MessageKeys.Result_Tracelog_AssigningSeats;
    APPLOG.info(Messages.bind(key, seats, list.getName(), index, total));

    // add seats
    Assignment assignment = new Assignment(index, list, _assignmentParent, seats, assignmentType,
        byLot, cosalgMode, supplement);
    ass.add(assignment);
    _totalAssignment.put(list, total);
    assert ass.size() == index : Messages
        .bind(MessageKeys.Builder_Assert_0_has_1_AssignmentsExpectedAssignments_2,
            list.getName(),
            ass.size(),
            index);

    boolean makeAssignmentPersistent = !cosalgMode && !fictitious;
    if (makeAssignmentPersistent) {
      _electionResult.assignSeats(assignment);
    }
  }

  public void finishAssignment(int index, boolean cosalgMode) {
    for (T key : this._listsAndNumberOfCandidates.keySet()) {
      List<Assignment> ass = _assignmentLists.get(key);
      if (ass.size() == index - 1) {
        ass.add(new Assignment(index, key, _assignmentParent, cosalgMode));
      } else {
        assert ass.size() == index : Messages
            .bind(MessageKeys.Builder_Assert_0_has_1_AssignmentsExpectedAssignments_2,
                key.getName(),
                ass.size(),
                index);
      }
    }
  }

  /**
   * Before the first assignment by largest average (d'Hondt), remember for all the lists taking
   * part, how many seats they had before.
   */
  public void preDHondtAssignment(Set<T> listsTakingPart, long totalNumberOfSeats) {
    if (_assignmentsBeforeDHondt == null) {
      Map<T, Long> tmp = new HashMap<T, Long>();
      for (T list : listsTakingPart) {
        tmp.put(list, getTotalAssignment(list));
      }
      _assignmentsBeforeDHondt = Collections.unmodifiableMap(tmp);
      _numberOfUnassignedSeatsBeforeDHondt = totalNumberOfSeats - getTotalAssignment();
    }
  }

  /**
   * May be <code>null</code> if no assignment by d'Hondt has taken place.
   */
  public Map<T, Long> getAssignmentsBeforeDHondt() {
    return _assignmentsBeforeDHondt;
  }

  public boolean isExhausted(final T key) {
    return _totalAssignment.get(key).longValue() >= _listsAndNumberOfCandidates.get(key)
        .longValue();
  }

  public long getTotalAssignment(final T key) {
    return _totalAssignment.get(key).longValue();
  }

  public long getTotalAssignment() {
    long result = 0;
    for (Long assignment : _totalAssignment.values()) {
      result += assignment;
    }
    return result;
  }

  /**
   * @return Unmodifiable Map of the total number of seats assigned to the lists
   */
  public Map<T, Long> getResult() {
    return Util.createUnmodifiableCopy(_totalAssignment);
  }

  /**
   * @return The assignment in the step given by <code>index</code> for the list given by
   *         <code>key</code>
   */
  public long getAssignment(final int index, final T list) {
    List<Assignment> ass = _assignmentLists.get(list);
    if (ass == null) {
      return 0;
    }
    if (index < 1 || index > ass.size()) {
      return 0;
    }
    return ass.get(index - 1).getSeats();
  }

  /**
   * @return The total number of seats assigned to the list given by <code>key</code> in steps of
   *         the given <code>assignmentType</code>.
   */
  public long getSumOfAssignmentsOfType(final AssignmentType assignmentType, final T key) {
    List<Assignment> ass = _assignmentLists.get(key);
    assert ass != null : Messages.bind(MessageKeys.Builder_Assert_MustBeNull,
        "_assignmentLists.get(key)"); //$NON-NLS-1$

    long sum = 0L;
    for (Assignment assignment : ass) {
      AssignmentType type = assignment.getAssignmentType();
      if ((assignmentType == null && type == null)
          || (assignmentType != null && assignmentType.equals(type))) {
        sum += assignment.getSeats();
      }
    }
    return sum;
  }

  public P getAssignmentParent() {
    return _assignmentParent;
  }

  /**
   * @return <code>true</code>, if a list has more seats assigned than it has candidates
   */
  public boolean isAnyListExhausted(final Collection<T> lists) {
    for (T list : lists) {
      if (_totalAssignment.get(list).longValue() > _listsAndNumberOfCandidates.get(list)
          .longValue()) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return a Map of all lists that have more or equal seats than candidates.
   */
  public Map<T, Long> getExhaustedListsMap(final Collection<T> lists) {
    Map<T, Long> result = new HashMap<T, Long>();

    for (T list : lists) {
      long diff = _totalAssignment.get(list).longValue()
          - _listsAndNumberOfCandidates.get(list).longValue();
      if (diff >= 0) {
        result.put(list, Long.valueOf(diff));
      }
    }
    return result;
  }

  public List<Set<T>> getRollBackSequence() {
    List<Set<T>> result = new ArrayList<Set<T>>();
    int maxNumberOfAssignments = getMaxNumberOfAssignments();

    for (int i = maxNumberOfAssignments; i > 0; i--) {
      Set<T> set = new HashSet<T>();
      for (T key : this._listsAndNumberOfCandidates.keySet()) {
        if (getAssignment(i, key) > 0) {
          set.add(key);
        }
      }
      result.add(Collections.unmodifiableSet(set));
    }

    return Collections.unmodifiableList(result);
  }

  public int getMaxNumberOfAssignments() {
    int maxNumberOfAssignments = 0;
    for (T key : this._listsAndNumberOfCandidates.keySet()) {
      List<Assignment> ass = _assignmentLists.get(key);
      maxNumberOfAssignments = Math.max(maxNumberOfAssignments, ass.size());
    }
    return maxNumberOfAssignments;
  }

  public int getNumberOfUnassignedSeatsBeforeDHondt() {
    return (int) _numberOfUnassignedSeatsBeforeDHondt;
  }

  public List<Set<T>> getRollForwardSequence(int start) {
    List<Set<T>> result = new ArrayList<Set<T>>();
    int maxNumberOfAssignments = getMaxNumberOfAssignments();

    for (int i = start; i <= maxNumberOfAssignments; i++) {
      Set<T> set = new HashSet<T>();
      for (T key : this._listsAndNumberOfCandidates.keySet()) {
        if (getAssignment(i, key) > 0) {
          set.add(key);
        }
      }
      result.add(Collections.unmodifiableSet(set));
    }

    return Collections.unmodifiableList(result);
  }

  public long getTotalNumberOfCandidates() {
    long sum = 0;
    for (Long noOfCandidates : _listsAndNumberOfCandidates.values()) {
      sum += noOfCandidates;
    }
    return sum;
  }

}
