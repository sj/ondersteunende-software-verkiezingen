/*
 * PartlyRamdomIterator
 * 
 * Created on 14.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.gsda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.determination.P2List;
import de.ivu.wahl.result.drawlots.DecisionType;
import de.ivu.wahl.result.drawlots.DrawingLotsCallback;

/**
 * Implementation of a sequence of P2-lists that will be requested one-by-one. The order may partly
 * be subject to a decision by drawing lots. This is only performed if this is required by a
 * request.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class PartlyRamdomIterator<T> implements Iterator<T> {
  protected final List<Set<T>> _listOfSets;
  protected final DrawingLotsCallback _drawingLotsCallback;
  protected final Iterator<Set<T>> baseIterator;
  private final DecisionType _decisionType;

  protected Set<T> currentSet = Collections.emptySet();

  public PartlyRamdomIterator(List<Set<T>> listOfSets,
      DrawingLotsCallback drawingLotsCallback,
      DecisionType decisionType) {
    this._listOfSets = Util.createUnmodifiableDeepCopy(listOfSets);
    this.baseIterator = _listOfSets.iterator();
    this._drawingLotsCallback = drawingLotsCallback;
    this._decisionType = decisionType;
  }

  public boolean hasNext() {
    while (currentSet.isEmpty() && baseIterator.hasNext()) {
      currentSet = new HashSet<T>(baseIterator.next());
    }
    return !currentSet.isEmpty();
  }

  public void remove() {
    throw new UnsupportedOperationException();
  }

  public T next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    if (currentSet.size() == 1) {
      T result = currentSet.iterator().next();
      currentSet = Collections.emptySet();
      return result;
    }

    return drawNextByLot();
  }

  @SuppressWarnings("unchecked")
  private T drawNextByLot() {
    Set<P2List> set = (Set<P2List>) currentSet;
    List<P2List> alternatives = new ArrayList<P2List>(set);
    Collections.sort(alternatives, P2List.SORT_BY_ELECTORAL_DISTRICT_NUMBER);

    int index = _drawingLotsCallback.selectAlternative(alternatives, _decisionType);
    P2List result = alternatives.get(index);
    currentSet.remove(result);
    return (T) result;
  }

}
