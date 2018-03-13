/*
 * Created on 16.11.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import java.util.Collection;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

import de.ivu.wahl.modell.ejb.Stimmergebnis;
import de.ivu.wahl.modell.ejb.service.VoteValues;

/**
 * Utility class to store the votes of a candidate, list, group or whatever per region. The regions
 * are sorted by region number. For EK elections, automatic weighting of the votes is supported.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class VotesByRegionNumber {
  private final SortedMap<Integer, Integer> votesMap;
  private final SortedMap<Integer, Integer> weightedVotesMap;
  private int totalVotes;
  private Integer totalWeightedVotes;

  /**
   * Constructor
   */
  public VotesByRegionNumber(Collection<Stimmergebnis> votes, VoteValues voteValues) {
    votesMap = new TreeMap<Integer, Integer>();
    weightedVotesMap = new TreeMap<Integer, Integer>();
    totalVotes = 0;
    totalWeightedVotes = null;
    for (Stimmergebnis stimmergebnis : votes) {
      int regionNumber = stimmergebnis.getGebiet().getNummer();
      int votesInRegion = stimmergebnis.getStimmen();
      votesMap.put(regionNumber, votesInRegion);
      totalVotes += votesInRegion;
      Integer voteValue = voteValues.get(regionNumber);
      if (voteValue != null) {
        int weightedVotes = voteValue.intValue() * votesInRegion;
        weightedVotesMap.put(regionNumber, weightedVotes);
        if (totalWeightedVotes == null) {
          totalWeightedVotes = 0;
        }
        totalWeightedVotes += weightedVotes;
      }
    }
  }

  /**
   * @return an Iterable that delivers the region numbers is ascending order
   */
  public Iterable<Integer> getRegionNumbers() {
    return Collections.unmodifiableCollection(votesMap.keySet());
  }

  public int size() {
    return votesMap.size();
  }

  /**
   * Returns the number of votes cast in the given region. Is not <code>null</code> for each
   * regionNumber that is in <code>getRegionNumbers()</code>, <code>null</code> for all other
   * regionNumbers.
   * 
   * @return the number of votes cast in the given region
   */
  public Integer getVotes(Integer regionNumber) {
    return votesMap.get(regionNumber);
  }

  /**
   * Returns the number of weighted votes in the given region. Is not <code>null</code> only for a
   * regionNumber of a province in EK elections that is in <code>getRegionNumbers()</code>,
   * <code>null</code> for all other regionNumbers.
   * 
   * @return the number of weighted votes cast in the given region
   */
  public Integer getWeightedVotes(Integer regionNumber) {
    return weightedVotesMap.get(regionNumber);

  }

  /**
   * Returns the sum of the number of votes cast for the regions given in
   * <code>getRegionNumbers()</code>.
   * 
   * @return the total number of votes cast
   */
  public int getTotalVotes() {
    return totalVotes;
  }

  /**
   * Returns the sum of the weighted votes for the regions given in <code>getRegionNumbers()</code>.
   * Is <code>null</code> if none of the regions given in <code>getRegionNumbers()</code> has
   * weighted votes, especially in all elections other than EK elections.
   * 
   * @return the total number of weighted votes
   */
  public Integer getTotalWeightedVotes() {
    return totalWeightedVotes;
  }

}
