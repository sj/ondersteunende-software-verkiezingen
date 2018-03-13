/*
 * VotesPerCandidate
 * 
 * Created on 04.02.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.etc;

import java.util.Map;

import de.ivu.wahl.result.Util;

/**
 * Map that holds the votes of a candidate by id_Listenkandidatur.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class VotesPerCandidate {
  private final Map<String, Integer> votes;

  public VotesPerCandidate(Map<String, Integer> votesPerCandidate) {
    this.votes = Util.createUnmodifiableCopy(votesPerCandidate);
  }

  public Integer getVotes(String id_Listenkandidatur) {
    return votes.get(id_Listenkandidatur);
  }
}
