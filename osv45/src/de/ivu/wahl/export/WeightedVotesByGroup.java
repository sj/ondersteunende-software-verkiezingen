/*
 * Created on 23.11.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.ivu.wahl.modell.ejb.Stimmergebnis;
import de.ivu.wahl.modell.ejb.service.VoteValues;

/**
 * Utility class to provide the total weighted votes per party for the Netherlands.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class WeightedVotesByGroup implements Serializable {
  private static final long serialVersionUID = 2321173923485126514L;

  private final Map<Integer, Integer> _weightedVotesByGroupPosition = new HashMap<Integer, Integer>();
  private int _totalWeightedVotes = 0;

  public WeightedVotesByGroup(Collection<Stimmergebnis> votes, VoteValues voteValues) {
    for (Stimmergebnis stimmergebnis : votes) {
      if (stimmergebnis.getID_Listenkandidatur() == null) {
        int position = stimmergebnis.getGruppeGebietsspezifisch().getPosition();
        int regionNumber = stimmergebnis.getGebiet().getNummer();
        int vote = stimmergebnis.getStimmen();
        Integer voteValue = voteValues.get(regionNumber);
        Integer oldVotes = _weightedVotesByGroupPosition.get(position);
        int weightedVotes = vote * voteValue;
        _totalWeightedVotes += weightedVotes;
        if (oldVotes == null) {
          _weightedVotesByGroupPosition.put(position, weightedVotes);
        } else {
          _weightedVotesByGroupPosition.put(position, oldVotes + weightedVotes);
        }
      }
    }
  }

  public int getWeightedVotesByGroupPosition(int position) {
    Integer result = _weightedVotesByGroupPosition.get(position);
    return result == null ? 0 : result;
  }

  public int getTotalWeightedVotes() {
    return _totalWeightedVotes;
  }

}
