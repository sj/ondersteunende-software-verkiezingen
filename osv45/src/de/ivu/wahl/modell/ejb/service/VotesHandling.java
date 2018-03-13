/*
 * VotesHandling
 * 
 * Created on 15.10.2003
 * Copyright (c) 2003-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb.service;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import de.ivu.wahl.AnwContext;
import de.ivu.wahl.auswertung.erg.ResultSummary;
import de.ivu.wahl.auswertung.erg.sv.SitzverteilungErg;
import de.ivu.wahl.auswertung.erg.sv.kandidat.KandidatInfo;
import de.ivu.wahl.export.VotesByRegionNumber;
import de.ivu.wahl.export.WeightedVotesByGroup;
import de.ivu.wahl.modell.Gesamtstimmen;
import de.ivu.wahl.modell.ejb.Liste;
import de.ivu.wahl.modell.ejb.Stimmergebnis;
import de.ivu.wahl.modell.etc.GeneralVotingResults;
import de.ivu.wahl.modell.etc.VotesPerCandidate;
import de.ivu.wahl.modell.exception.ImportException;

/**
 * Provides Services with information about votes and elected candidates
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface VotesHandling {
  /**
   * @param id_Ergebniseingang
   * @return the number of blank votes in the root region. <code>null</code> if not recorded, yet.
   */
  public Integer getTotalBlankVotes(String id_Ergebniseingang);

  /**
   * @param id_Ergebniseingang
   * @return the number of valid votes in the root region. <code>null</code> if not recorded, yet.
   */
  public Integer getTotalValidVotes(String id_Ergebniseingang);

  /**
   * @param id_Ergebniseingang
   * @return the number of invalid votes in the root region. <code>null</code> if not recorded, yet.
   */
  public Integer getTotalInvalidVotes(String id_Ergebniseingang);

  public VotesPerCandidate getVotesPerCandidate(String id_Ergebniseingang,
      String id_GruppeGebietsspezifisch) throws ImportException;

  /**
   * @return the number of votes cast for a P2-list in a region
   */
  public int getVotesForListInRegion(Liste liste, String id_Gebiet, String id_Ergebniseingang)
      throws FinderException;

  /**
   * Votes of the general groups (GruppeKonstanten.GruppeAllgemein) in a given region, in a Map by
   * position of the general group.
   */
  public GeneralVotingResults getGeneralVotingResults(String id_Ergebniseingang, String id_Gebiet);

  public List<Stimmergebnis> getSortedCandidateResults(Liste liste,
      String id_Gebiet,
      String id_Ergebniseingang) throws FinderException;

  /**
   * Liefert die gew�hlten Kandidaten eines Gebietes alphabetisch sortiert
   * 
   * @param anwContext Anwenderkontext f�r die Bestimmung der Wahl und der Rechte
   * @param id_ergebniseingang die ID des Ergebniseinganges, zu dem die Sitzverteilung bestimmt
   *          wurde
   * @param id_Gebiet Prim�rschl�ssel des Gebietes
   * @return gew�hlten Direkt- und Listenkandidaten des Gebietes, alphabetisch sortiert
   * @throws EJBException Bei einem allgemeinen Problem
   */
  public List<KandidatInfo> getGewaehltKandidatenForGebietAlphabetisch(String id_ergebniseingang,
      String id_Gebiet) throws EJBException;

  /**
   * Liefert die gew�hlten Kandidaten eines Gebietes nach Parteien sortiert - innerhalb der Parteien
   * wird nach der Reihenfolge der erhaltenen Sitze sortiert
   * 
   * @param anwContext Anwenderkontext f�r die Bestimmung der Wahl und der Rechte
   * @param id_ergebniseingang die ID des Ergebniseinganges, zu dem die Sitzverteilung bestimmt
   *          wurde
   * @param id_Gebiet Prim�rschl�ssel des Gebietes
   * @return gew�hlten Listenkandidaten des Gebietes nach Parteien und dann alphabetisch sortiert
   * @throws EJBException Bei einem allgemeinen Problem
   */
  public List<KandidatInfo> getGewaehltKandidatenForGebietOrderByGruppe(AnwContext anwContext,
      String id_ergebniseingang,
      String id_Gebiet) throws EJBException;

  public SitzverteilungErg getSitzverteilungErgebnis(String id_ergebniseingang) throws EJBException;

  /**
   * Get the total votes object. Needed if there are no lists at root region level
   * 
   * @return the Gesamtstimmen object
   */
  public Gesamtstimmen getGesamtstimmen(boolean isEK);

  /**
   * Filling a data object with voting results from a Resultsummary of all votes per region
   * 
   * @param anwContext user context
   * @param regionCategory region category
   * @param regionNumber region number
   * @return data object with voting results
   * @throws EJBException any error
   */
  public ResultSummary getResultSummary(AnwContext anwContext, int regionCategory, int regionNumber)
      throws EJBException;

  /**
   * Filling a data object with voting results from a Resultsummary of all votes per region
   */
  public ResultSummary getResultSummary() throws EJBException;

  /**
   * For EK elections return the weighted votes per party in the root region (Netherland)
   */
  public WeightedVotesByGroup getWeightedVotesByGroup() throws EJBException;

  public VotesByRegionNumber getVotesForP2ListByRegion(String id_Ergebniseingang,
      Liste liste,
      VoteValues voteValues);

  public VotesByRegionNumber getVotesForP2ListByRegion(Liste liste);
}
