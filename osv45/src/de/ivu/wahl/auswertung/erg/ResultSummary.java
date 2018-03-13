/*
 * ErgebnisZusammenfassung
 * 
 * Created on 16.09.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.auswertung.erg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import de.ivu.wahl.modell.GesamtstimmenImpl;
import de.ivu.wahl.modell.ejb.Gebiet;

/**
 * Data needed to show the summary of all counted votes. This is
 * <ul>
 * <li>a list of the regions and</li>
 * <li>for each party the total votes and the total votes per region</li>
 * <li>for each party the candidates and their total votes and votes per region</li>
 * </ul>
 * <p>
 * {@link ResultSummary} is doing very similar things to {@link GesamtstimmenImpl}. Maybe one of
 * them can be replaced by the other. To my (JON, 24-11-2009) knowledge, {@link GesamtstimmenImpl}
 * is better in performance.
 * 
 * @author mur@ivu.de, IVU Traffic Technologies AG
 */
public class ResultSummary {
  private static final long serialVersionUID = -6529062328338123485L;

  private Collection<Gebiet> _gebiete = new ArrayList<Gebiet>();
  private final SortedSet<PartyWithCandidates> _gruppenMitKandidaten = new TreeSet<PartyWithCandidates>();

  public ResultSummary() {
    super();
  }

  /**
   * @return List of groups (=Parties, but not technical) sorted by group number
   */
  public List<PartyWithCandidates> getGruppenMitKandidaten() {
    ArrayList<PartyWithCandidates> result = new ArrayList<PartyWithCandidates>();
    for (PartyWithCandidates partyWithCandidates : _gruppenMitKandidaten) {
      if (partyWithCandidates.getGruppenPosition() > 0) {
        result.add(partyWithCandidates);
      }
    }
    return result;
  }

  /**
   * @return List of all groups (=Parties and technical groups) sorted by group number
   */
  public List<PartyWithCandidates> getAllGroupsAndCandidates() {
    return new ArrayList<PartyWithCandidates>(_gruppenMitKandidaten);
  }

  public void addGruppeMitKandidaten(PartyWithCandidates gruppeMitKandidaten) {
    _gruppenMitKandidaten.add(gruppeMitKandidaten);
  }

  public void setGebiete(Collection<Gebiet> gebiete) {
    _gebiete = gebiete;
  }

  public Collection<Gebiet> getGebiete() {
    return _gebiete;
  }

}
