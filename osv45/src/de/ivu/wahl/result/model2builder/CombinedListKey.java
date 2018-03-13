/*
 * CombinedListKey
 * 
 * Created on 26.08.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.result.model2builder;

/**
 * External key for combined lists in the determination of the election result.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class CombinedListKey {

  private final String _combinationId;

  /**
   * Constructor
   */
  public CombinedListKey(String combinationId) {
    this._combinationId = combinationId;
  }

  @Override
  public String toString() {
    return _combinationId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((_combinationId == null) ? 0 : _combinationId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    CombinedListKey other = (CombinedListKey) obj;
    if (_combinationId == null) {
      if (other._combinationId != null) {
        return false;
      }
    } else if (!_combinationId.equals(other._combinationId)) {
      return false;
    }
    return true;
  }

}
