/*
 * Metadaten
 * 
 * Created on 06.02.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import java.util.Collection;

import nu.xom.Element;

import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.GesamtstimmenImpl;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public interface ErgebnisImportHandler {

  /**
   */
  boolean checkConsistency();

  /**
   */
  boolean saveCandidateVotes(int regionCategory) throws ImportException;

  /**
   */
  boolean importRegionResults(int gebietsart);

  /**
   * @return Id of election category (from WahlModel)
   */

  ElectionCategory getElectionCategory();

  /**
   * Finder for the GebietModel of the root region.
   */
  GebietModel getImportRegion() throws ImportException;

  /**
   * Finder for the GebietModel by region category and number of the region.
   */
  GebietModel findGebietByGebietsartAndNummer(int gebietsart, int nummer) throws ImportException;

  /**
   * @param gebieteEingegangen
   * @throws ImportException
   */
  void finishErgebnisimport(Collection<GebietModel> gebieteEingegangen) throws ImportException;

  /**
   * @param setStimmen
   * @throws ImportException
   */
  void addStimmen(GebietModel gebiet, int gruppenschluessel, String id_Listenkandidatur, int stimmen)
      throws ImportException;

  void readStimmergebnisseAllgemein(Element ergebnis,
      GebietModel gebiet,
      GesamtstimmenImpl gesamtstimmen,
      int gesamtstimmenGebiet) throws ImportException;

  /**
   * Determine the ID_Listenkandidatur, which again identifies the P2-list and candidate.
   */
  String getID_Listenkandidatur(GebietModel gebiet,
      int gruppenschluessel,
      String shortCode,
      int listenplatz);

  int getNumberOfCandidates(GebietModel gebiet, int gruppenschluessel) throws ImportException;

  int getRegionCategoryWithLists();

}
