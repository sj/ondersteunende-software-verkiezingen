/*
 * GsdaResult
 * 
 * Created on 14.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.gsda;

import java.util.Map;
import java.util.Set;

/**
 * The three result types of the General Seat Distribution Algorithm including the roll back
 * sequence and the roll forware sequence.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class GsdaResult<T> {
  public GsdaResult(Map<T, Long> result, PartlyRamdomIterator<T> rbs, PartlyRamdomIterator<T> rfs) {
    _result = result;
    _rbs = rbs;
    _rfs = rfs;
  }

  public GsdaResult(Map<T, Long> map) {
    this(map, null, null);
  }

  private final Map<T, Long> _result;
  private final PartlyRamdomIterator<T> _rbs;
  private final PartlyRamdomIterator<T> _rfs;

  public PartlyRamdomIterator<T> getRbs() {
    return _rbs;
  }

  public PartlyRamdomIterator<T> getRfs() {
    return _rfs;
  }

  public Long get(Object key) {
    return _result.get(key);
  }

  public Set<T> keySet() {
    return _result.keySet();
  }

  public Map<T, Long> getListsAndSeats() {
    return _result;
  }

}
