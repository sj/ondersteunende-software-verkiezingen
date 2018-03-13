/*
 * ResultSummaryHelper
 * 
 * Created on 06.11.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb.service;

import static de.ivu.wahl.WahlInfo.getWahlInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.auswertung.erg.PartyWithCandidates;
import de.ivu.wahl.auswertung.erg.ResultSummary;
import de.ivu.wahl.modell.Candidateship;
import de.ivu.wahl.modell.CandidateshipsModel;
import de.ivu.wahl.modell.ListsInRegionsModel;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifisch;
import de.ivu.wahl.modell.ejb.Liste;
import de.ivu.wahl.modell.ejb.Listenkandidatur;
import de.ivu.wahl.modell.ejb.Personendaten;
import de.ivu.wahl.modell.ejb.Stimmergebnis;

/**
 * Helper class to create and fill a ResultSummary
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class ResultSummaryHelper {
  private final VotesHandlingBean _bean;
  private final Gebiet rootRegion = getWahlInfo().getWahl().getWurzelgebiet();
  private final ListsInRegionsModel listsInRegionsModel;
  private final CandidateshipsModel candidateshipsModel;

  ResultSummaryHelper(VotesHandlingBean bean) {
    this._bean = bean;
    this.listsInRegionsModel = _bean.getListsInRegionsModel();
    this.candidateshipsModel = load();
  }

  /**
   * Filling a data object with voting results from a ResultSummary of all votes per region
   * 
   * @return data object with voting results
   * @throws EJBException any error
   */
  ResultSummary getResultSummary() throws EJBException {
    boolean isEK = WahlInfo.getWahlInfo().isEK();
    VoteValues voteValues = isEK ? _bean.getGebietHandling().getVoteValues() : null;
    ResultSummary result = new ResultSummary();

    List<Gebiet> childRegions = listsInRegionsModel.getRegions();
    result.setGebiete(childRegions);

    Collection<GruppeGebietsspezifisch> gruppeGebietsspezifischCol = rootRegion
        .getGruppeGebietsspezifischCol();

    // Load voting results from database, index by Candidateship
    Map<Candidateship, Integer> votesMap = new HashMap<Candidateship, Integer>();
    Map<String, Integer> ggsAndVotes = new HashMap<String, Integer>();
    for (Gebiet childRegion : childRegions) {
      Collection<Stimmergebnis> votingResults = _bean.getVotingResults(childRegion);
      for (Stimmergebnis votingResult : votingResults) {
        String id_Listenkandidatur = votingResult.getID_Listenkandidatur();
        int votes = votingResult.getStimmen();
        if (id_Listenkandidatur != null) {
          Candidateship key = candidateshipsModel.getCandidateship(id_Listenkandidatur,
              votingResult.getID_Gebiet());
          votesMap.put(key, votes);
        } else {
          ggsAndVotes.put(votingResult.getID_GruppeGebietsspezifisch(), votes);
        }
      }
    }

    // This includes political parties and technical groups !!!
    for (GruppeGebietsspezifisch gruppeGebietsspezifisch : gruppeGebietsspezifischCol) {
      PartyWithCandidates gruppeMitKandidaten = createPartyWithCandidates(childRegions,
          gruppeGebietsspezifisch,
          votesMap,
          ggsAndVotes,
          voteValues);
      result.addGruppeMitKandidaten(gruppeMitKandidaten);
    }

    return result;
  }

  private PartyWithCandidates createPartyWithCandidates(Collection<Gebiet> childRegions,
      GruppeGebietsspezifisch gruppeGebietsspezifisch,
      Map<Candidateship, Integer> votesMap,
      Map<String, Integer> ggsAndVotes,
      VoteValues voteValues) {
    Gruppe gruppe = gruppeGebietsspezifisch.getGruppe();
    PartyWithCandidates partyWithCandidates = new PartyWithCandidates(gruppe.getNameKurz(),
        gruppe.getID_Gruppe(), gruppeGebietsspezifisch.getPosition(), voteValues);

    // Fill results into partyWithCandidates for all child-regions
    int childRegionIndex = 0;
    for (Gebiet childRegion : childRegions) {
      childRegionIndex++;
      fillPartyWithCandidates(partyWithCandidates,
          childRegion,
          childRegionIndex,
          votesMap,
          ggsAndVotes);
    }

    return partyWithCandidates;
  }

  private void fillPartyWithCandidates(PartyWithCandidates partyWithCandidates,
      Gebiet childGebiet,
      int childRegionIndex,
      Map<Candidateship, Integer> votesMap,
      Map<String, Integer> ggsAndVotes) {

    Collection<Candidateship> candidateships = candidateshipsModel.getByRegionAndGroup(childGebiet,
        partyWithCandidates.getGruppenID());
    for (Candidateship candidateship : candidateships) {
      Integer votes = votesMap.get(candidateship);
      if (votes != null) {
        // votes of a party
        partyWithCandidates.addGruppenstimmeProGebiet(childGebiet, votes);

        // votes of a candidate
        Personendaten personendaten = candidateship.getPersonendaten();
        Listenkandidatur listenkandidatur = candidateship.getListenkandidatur();
        int positionInList = listenkandidatur.getListenplatz();
        String id_Personendaten = personendaten.getID_Personendaten();
        String nachname = personendaten.getNachname();
        String anzeigeName = personendaten.getAnzeigeName();
        partyWithCandidates.addVotes(id_Personendaten,
            anzeigeName,
            nachname,
            childGebiet,
            childRegionIndex,
            votes,
            positionInList,
            listenkandidatur.getID_Listenkandidatur());

        partyWithCandidates.setPublicationLanguage(candidateship.getPublicationLanguage());
      }
    }

    if (candidateships.isEmpty()) {
      GruppeGebietsspezifisch ggs = listsInRegionsModel.getGruppeGebietsspezifisch(childGebiet,
          partyWithCandidates.getGruppenID());
      if (ggs != null) {
        Integer votes = ggsAndVotes.get(ggs.getID_GruppeGebietsspezifisch());
        if (votes != null) {
          partyWithCandidates.addGruppenstimmeProGebiet(childGebiet, votes);
        }
      }
    }
  }

  /**
   * 
   */
  private CandidateshipsModel load() {
    try {
      // Create index for Personendaten
      Collection<Personendaten> allPersonendaten = _bean.getPersonendatenHome().findAll();
      Map<String, Personendaten> personendatenIndex = new HashMap<String, Personendaten>();
      for (Personendaten each : allPersonendaten) {
        personendatenIndex.put(each.getID_Personendaten(), each);
      }

      // Create index for Listenkandidatur
      Collection<Listenkandidatur> allListenkandidaturen = _bean.getListenkandidaturHome()
          .findAll();
      Map<String, Listenkandidatur> listenkandidaturenIndex = new HashMap<String, Listenkandidatur>();
      for (Listenkandidatur lk : allListenkandidaturen) {
        listenkandidaturenIndex.put(lk.getID_Listenkandidatur(), lk);
      }

      // Create all Candidateships
      List<Candidateship> allCandidateships = new ArrayList<Candidateship>();
      for (Listenkandidatur lk : allListenkandidaturen) {
        Liste liste = listsInRegionsModel.getListe(lk);
        Map<Gebiet, GruppeGebietsspezifisch> gruppenGebietsspezifisch = listsInRegionsModel
            .getGruppenGebietsspezifisch(liste);
        for (Gebiet region : gruppenGebietsspezifisch.keySet()) {
          GruppeGebietsspezifisch gruppeGebietsspezifisch = gruppenGebietsspezifisch.get(region);
          Gruppe gruppe = listsInRegionsModel.getGruppe(gruppeGebietsspezifisch);
          Personendaten personendaten = personendatenIndex.get(lk.getID_Personendaten());

          Candidateship candidateship = new Candidateship(personendaten, lk, region,
              gruppeGebietsspezifisch, liste, gruppe);
          allCandidateships.add(candidateship);
        }
      }

      // Create CandidateshipsModel from Candidateships
      return new CandidateshipsModel(allCandidateships, listsInRegionsModel);

    } catch (FinderException e) {
      e.printStackTrace();
      throw new EJBException(e);
    }

  }
}
