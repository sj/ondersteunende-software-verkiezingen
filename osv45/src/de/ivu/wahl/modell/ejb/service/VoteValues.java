/*
 * Created on 18.11.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb.service;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class VoteValues implements Serializable {
  private static final long serialVersionUID = 1003397375932432129L;

  private final Map<Integer, Integer> _districtIdsAndVoteValues;

  public VoteValues() {
    this._districtIdsAndVoteValues = Collections.emptyMap();
  }

  public VoteValues(Map<Integer, Integer> districtIdsAndVoteValues) {
    this._districtIdsAndVoteValues = new TreeMap<Integer, Integer>(districtIdsAndVoteValues);
  }

  public Integer get(Integer regionNumber) {
    return _districtIdsAndVoteValues.get(regionNumber);
  }
}
