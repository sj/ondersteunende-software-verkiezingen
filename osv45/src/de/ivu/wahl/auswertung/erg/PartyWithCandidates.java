/*
 * Created on 06.11.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.auswertung.erg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ivu.wahl.modell.PublicationLanguage;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.service.VoteValues;

/**
 * Information about a party (political grouping) and the candidates that are nominated for the
 * list(s) of this party
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class PartyWithCandidates implements Comparable<PartyWithCandidates> {
  private static final String MINUS = "-"; //$NON-NLS-1$

  private final String _gruppenName;
  private final String _gruppenID;
  private final int _gruppenPosition;
  private final Map<Gebiet, Integer> _gruppenstimmenProGebiet = new HashMap<Gebiet, Integer>();
  private final Map<Gebiet, Integer> _gruppenstimmenGewichtetProGebiet = new HashMap<Gebiet, Integer>();
  private int _summe = 0;
  private int _summeGewichtet = 0;
  private final VoteValues _voteValues; // may be null
  private String _publicationLanguage = PublicationLanguage.NL.getAbbreviation();

  private final Map<String, CandidateVotesPerRegion> _kandidatenstimmenDerGebiete = new HashMap<String, CandidateVotesPerRegion>();
  private List<CandidateVotesPerRegion> _kandidatenstimmenDerGebieteSorted;

  /**
   * Constructor
   */
  public PartyWithCandidates(String gruppenName,
      String gruppenID,
      int gruppenPosition,
      VoteValues voteValues) {
    this._gruppenName = gruppenName;
    this._gruppenID = gruppenID;
    this._gruppenPosition = gruppenPosition;
    this._voteValues = voteValues;
  }

  public String getGruppenName() {
    return _gruppenName;
  }

  public String getGruppenID() {
    return _gruppenID;
  }

  public int getGruppenPosition() {
    return _gruppenPosition;
  }

  public String getPublicationLanguage() {
    return _publicationLanguage;
  }

  public void setPublicationLanguage(String publicationLanguage) {
    _publicationLanguage = publicationLanguage;
  }

  public Collection<CandidateVotesPerRegion> getCandidateVotesPerRegion() {
    if (_kandidatenstimmenDerGebieteSorted == null) {
      _kandidatenstimmenDerGebieteSorted = new ArrayList<CandidateVotesPerRegion>(
          _kandidatenstimmenDerGebiete.values());
      Collections.sort(_kandidatenstimmenDerGebieteSorted,
          CandidateVotesPerRegion.SORT_BY_POSITION_ON_LIST);
    }
    return _kandidatenstimmenDerGebieteSorted;
  }

  public Map<Gebiet, Integer> getGruppenstimmenProGebiet() {
    return _gruppenstimmenProGebiet;
  }

  public Map<Gebiet, Integer> getGruppenstimmenGewichtetProGebiet() {
    return _gruppenstimmenGewichtetProGebiet;
  }

  public String getGruppenstimmeProGebiet(Gebiet gebiet) {
    return _gruppenstimmenProGebiet.containsKey(gebiet) ? Integer.toString(_gruppenstimmenProGebiet
        .get(gebiet)) : MINUS;
  }

  public String getGruppenstimmeGewichtetProGebiet(Gebiet gebiet) {
    return _gruppenstimmenGewichtetProGebiet.containsKey(gebiet) ? Integer
        .toString(_gruppenstimmenGewichtetProGebiet.get(gebiet)) : MINUS;
  }

  public void addGruppenstimmeProGebiet(Gebiet gebiet, int gruppenstimme) {
    // assert _gruppenstimmenProGebiet.containsKey(gebiet) :
    // "Sollte noch kein Ergebniss gespeichert sein";
    _summe += gruppenstimme;
    addVotes(gebiet, gruppenstimme, _gruppenstimmenProGebiet);

    if (_voteValues != null) {
      Integer voteValue = _voteValues.get(gebiet.getNummer());
      if (voteValue != null) {
        _summeGewichtet += gruppenstimme * voteValue;
        addVotes(gebiet, gruppenstimme * voteValue, _gruppenstimmenGewichtetProGebiet);
      }
    }
  }

  private void addVotes(Gebiet gebiet, int votes, Map<Gebiet, Integer> map) {
    Integer oldVotes = map.get(gebiet);
    if (oldVotes == null) {
      map.put(gebiet, votes);
    } else {
      map.put(gebiet, oldVotes + votes);
    }
  }

  public int getSumme() {
    return _summe;
  }

  public int getSummeGewichtet() {
    return _summeGewichtet;
  }

  @Override
  public int compareTo(PartyWithCandidates o) {
    return _gruppenPosition - o._gruppenPosition;
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PartyWithCandidates other = (PartyWithCandidates) obj;
    if (_gruppenID == null) {
      if (other._gruppenID != null)
        return false;
    } else if (!_gruppenID.equals(other._gruppenID))
      return false;
    if (_gruppenName == null) {
      if (other._gruppenName != null)
        return false;
    } else if (!_gruppenName.equals(other._gruppenName))
      return false;
    if (_gruppenPosition != other._gruppenPosition)
      return false;
    return true;
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((_gruppenID == null) ? 0 : _gruppenID.hashCode());
    result = prime * result + ((_gruppenName == null) ? 0 : _gruppenName.hashCode());
    result = prime * result + _gruppenPosition;
    return result;
  }

  /**
   * Adds votes for a person in a region
   */
  public void addVotes(String id_Personendaten,
      String anzeigeName,
      String nachname,
      Gebiet childGebiet,
      int childRegionIndex,
      int votes,
      int positionOnList,
      String id_Listenkandidatur) {
    CandidateVotesPerRegion candidateVotesPerRegion = _kandidatenstimmenDerGebiete
        .get(id_Personendaten);
    if (candidateVotesPerRegion == null) {
      candidateVotesPerRegion = new CandidateVotesPerRegion(id_Personendaten, anzeigeName,
          nachname, id_Listenkandidatur);
      _kandidatenstimmenDerGebiete.put(id_Personendaten, candidateVotesPerRegion);
      _kandidatenstimmenDerGebieteSorted = null;
    }
    int weightedVotes = 0;
    if (_voteValues != null) {
      Integer voteValue = _voteValues.get(childGebiet.getNummer());
      if (voteValue != null) {
        weightedVotes = votes * voteValue;
      }
    }
    boolean resortNeeded = candidateVotesPerRegion.addVotesAndPositionOnListPerRegion(childGebiet,
        votes,
        weightedVotes,
        positionOnList,
        childRegionIndex);
    if (resortNeeded) {
      _kandidatenstimmenDerGebieteSorted = null;
    }
  }

}