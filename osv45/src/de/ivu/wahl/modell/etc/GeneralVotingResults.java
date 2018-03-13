/*
 * GeneralVotingResults
 * 
 * Created on 05.02.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.etc;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein;
import de.ivu.wahl.modell.ejb.Stimmergebnis;

/**
 * Voting results for the general groups (votes cast, valid votes, invalid votes, blank votes) for a
 * given region.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class GeneralVotingResults {
  private final Map<Integer, Integer> generalResults;

  public GeneralVotingResults(Collection<Stimmergebnis> votingResults) {
    Map<Integer, Integer> temp = new HashMap<Integer, Integer>();
    for (Stimmergebnis votes : votingResults) {
      temp.put(votes.getGruppeGebietsspezifisch().getPosition(), votes.getStimmen());
    }
    generalResults = Collections.unmodifiableMap(temp);
  }

  public Integer getVotes(GruppeAllgemein generalGroup) {
    return generalResults.get(generalGroup.getPosition());
  }

}
