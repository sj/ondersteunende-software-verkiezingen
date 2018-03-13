/*
 * CandidateVotesPerRegion
 * 
 * Created on 06.11.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.auswertung.erg;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import de.ivu.wahl.modell.ejb.Gebiet;

/**
 * Holds the counted votes of a candidate per region (i.e. for each region). compareTo() sorts by
 * last name of the candidate
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class CandidateVotesPerRegion implements Comparable<CandidateVotesPerRegion> {
  static Comparator<CandidateVotesPerRegion> SORT_BY_POSITION_ON_LIST = new Comparator<CandidateVotesPerRegion>() {
    @Override
    public int compare(CandidateVotesPerRegion x, CandidateVotesPerRegion y) {
      int result = x._minPositionOnList - y._minPositionOnList;
      if (result != 0) {
        return result;
      } else {
        return x._minRegionIndex - y._minRegionIndex;
      }
    }
  };

  private final String _idPerson;
  private final String _personenName;
  private final String _personenNachname;
  private final SortedSet<Integer> _positionsOnList = new TreeSet<Integer>();
  int _minPositionOnList = 999;
  int _minRegionIndex = 999;
  private final String _idListenkandidatur;
  private final Map<Gebiet, Integer> _votesPerRegion = new HashMap<Gebiet, Integer>();
  private int _totalVotes = 0;
  private final Map<Gebiet, Integer> _weightedVotesPerRegion = new HashMap<Gebiet, Integer>();
  private int _totalWeightedVotes = 0;

  CandidateVotesPerRegion(String idPerson,
      String personenName,
      String personenNachname,
      String idListenkandidatur) {
    this._idPerson = idPerson;
    this._personenName = personenName;
    this._personenNachname = personenNachname;
    this._idListenkandidatur = idListenkandidatur;
  }

  public String getIdPerson() {
    return _idPerson;
  }

  public String getPersonenName() {
    return _personenName;
  }

  public Map<Gebiet, Integer> getPersonenstimmenProGebiet() {
    return _votesPerRegion;
  }

  public String getKandidatenstimmeProGebiet(Gebiet gebiet) {
    return _votesPerRegion.containsKey(gebiet)
        ? Integer.toString(_votesPerRegion.get(gebiet))
        : "-"; //$NON-NLS-1$
  }

  public String getKandidatenstimmeGewichtetProGebiet(Gebiet gebiet) {
    return _weightedVotesPerRegion.containsKey(gebiet) ? Integer.toString(_weightedVotesPerRegion
        .get(gebiet)) : "-"; //$NON-NLS-1$
  }

  /**
   * @return <code>true</code>, if the minPositionOnList and / or minRegionIndex were changed
   */
  public boolean addVotesAndPositionOnListPerRegion(Gebiet region,
      int votes,
      int weightedVotes,
      int positionOnList,
      int childRegionIndex) {
    assert !_votesPerRegion.containsKey(region) : "Sollte noch kein Ergebnis gespeichert sein"; //$NON-NLS-1$
    _totalVotes += votes;
    _totalWeightedVotes += weightedVotes;
    this._votesPerRegion.put(region, votes);
    this._weightedVotesPerRegion.put(region, weightedVotes);
    return updatePositionOnList(positionOnList, childRegionIndex);
  }

  /**
   * Remember the smallest value of <code>positionsOnList</code> and the smallest corresponding
   * <code>childRegionIndex</code>.
   * 
   * @return <code>true</code>, if the minPositionOnList and / or minRegionIndex were changed
   */
  private boolean updatePositionOnList(int positionOnList, int childRegionIndex) {
    this._positionsOnList.add(positionOnList);
    if (positionOnList < _minPositionOnList
        || (positionOnList == _minPositionOnList && childRegionIndex < _minRegionIndex)) {
      _minPositionOnList = positionOnList;
      _minRegionIndex = childRegionIndex;
      return true;
    }
    return false;
  }

  @Override
  public int compareTo(CandidateVotesPerRegion o) {
    return _personenNachname.compareTo(o._personenNachname);
  }

  public int getPositionOnList() {
    return _positionsOnList.iterator().next(); // Smallest position on list
  }

  public boolean isPositionOnListUnique() {
    return _positionsOnList.size() == 1;
  }

  public String getIdListenkandidatur() {
    return _idListenkandidatur;
  }

  public int getSumme() {
    return _totalVotes;
  }

  public int getSummeGewichtet() {
    return _totalWeightedVotes;
  }

}