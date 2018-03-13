/*
 * Created on 24.11.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.FinderException;

import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.PersonendatenKonstanten;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.Liste;
import de.ivu.wahl.modell.ejb.Listenkandidatur;
import de.ivu.wahl.modell.ejb.ListenkandidaturErgebnis;
import de.ivu.wahl.modell.ejb.Personendaten;
import de.ivu.wahl.modell.ejb.service.VoteValues;
import de.ivu.wahl.wus.reportgen.csv.CsvGenerator;

/**
 * Generates the CSV String for the Export of the details of elected candidates (osv5-6)
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class CandidateDetailsExportGenerator {
  private static final String EMPTY_STRING = ""; //$NON-NLS-1$
  private final static String SPACE = " "; //$NON-NLS-1$

  private final ExportHandlingBean _bean;
  private final ContestIdentifierService contestIdentifierService = new ContestIdentifierService();
  private final CsvGenerator table = new CsvGenerator();
  private final boolean isElectionsWithListGroups = WahlInfo.getWahlInfo().getElectionSubcategory()
      .isElectionWithListGroups();

  /**
   * Constructor
   */
  public CandidateDetailsExportGenerator(ExportHandlingBean bean) {
    _bean = bean;
  }

  public String generateExport() throws FinderException {
    table.startLine();
    table.add(Messages.bind(MessageKeys.CandidateExportGenerator_election, WahlInfo.getWahlInfo()
        .getWahlName()));
    table.newLine();
    table.newLine();
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_subtitle));
    table.newLine();
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_subsubtitle));
    table.newLine();
    if (isElectionsWithListGroups) {
      table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_legend));
      table.newLine();
    }
    table.newLine();
    addHeadings();
    table.finishLine();
    addData();

    return table.getCsv();
  }

  private void addData() throws FinderException {
    String id_Ergebniseingang = WahlInfo.getWahlInfo().getWahl().getWurzelgebiet()
        .getID_LetzterEingang();
    VoteValues voteValues = _bean.getGebietHandling().getVoteValues();
    boolean isEK = WahlInfo.getWahlInfo().isEK();

    List<Gruppe> groups = _bean.getGruppeHandling().getGroupsSorted();
    for (Gruppe p3List : groups) {
      int p3ListSeats = _bean.getSeatsHandling().getTotalSeatsForP3List(id_Ergebniseingang, p3List);
      if (p3ListSeats > 0) {
        addDataForP3List(id_Ergebniseingang, voteValues, isEK, p3List);
      }
    }
  }

  private void addDataForP3List(String id_Ergebniseingang,
      VoteValues voteValues,
      boolean isEK,
      Gruppe p3List) throws FinderException {
    Collection<Liste> listen = p3List.getListeCol();
    ListType listType = contestIdentifierService.getListType(p3List);
    boolean isListGroup = ListType.LIST_GROUP.equals(listType);
    for (Liste p2List : listen) {
      Collection<Listenkandidatur> candidates = _bean.getListenkandidaturHome()
          .findAllByListe(p2List.getID_Liste());
      SortedSet<SortableCandidate> candidatesSorted = new TreeSet<SortableCandidate>();
      for (Listenkandidatur lk : candidates) {
        ListenkandidaturErgebnis lke = getListenkandidaturErgebnis(id_Ergebniseingang, lk);
        candidatesSorted.add(new SortableCandidate(lk, lke));
      }

      RG520VotesHelper helper = new RG520VotesHelper(_bean, id_Ergebniseingang);
      for (SortableCandidate cand : candidatesSorted) {
        Listenkandidatur lk = cand._lk;
        VotesByRegionNumber votes = helper.getVotesForCandidateOnP2List(lk);

        Personendaten personendaten = lk.getPersonendaten();
        // P3-list data
        table.add(p3List.getNameKurz());
        table.add(p3List.getSchluessel());
        // P2-list data
        if (isElectionsWithListGroups) {
          table.add(isListGroup ? p2List.getSatz() > 0 ? Messages
              .getString(MessageKeys.CandidateDetailsExportGenerator_set) : Messages
              .getString(MessageKeys.CandidateDetailsExportGenerator_district) : EMPTY_STRING);
          table.add(isListGroup ? getStelOrKieskring(p2List) : EMPTY_STRING);
        }
        table.add(isEK ? votes.getTotalWeightedVotes() : votes.getTotalVotes());
        // candidate data
        table.add(lk.getListenplatz());
        table.add(cand.displayElected());
        // personal data
        String lastName = nullSafe(personendaten.getNachname());
        String namePrefix = nullSafe(personendaten.getPraefix());
        if (namePrefix.length() > 0 && lastName.length() > 0) {
          table.add(namePrefix + SPACE + lastName);
        } else {
          table.add(namePrefix + lastName);
        }
        table.add(personendaten.getInitialen());
        table.add(personendaten.getVorname());
        table.add(personendaten.getWohnort());
        table.add(personendaten.getLand());
        table.add(getGeschlecht(lk, personendaten));
        table.add(personendaten.getKontakt_Strasse());
        table.add(personendaten.getKontakt_PLZ());
        table.add(personendaten.getKontakt_Wohnort());
        table.add(personendaten.getKontakt_Land());

        // agent
        Personendaten agent = personendaten.getPersonendatenAgent();
        if (agent != null) {
          addAgentData(agent);
        }

        table.finishLine();
      }
    }
  }

  private void addAgentData(Personendaten agent) {
    String agentLastName = nullSafe(agent.getNachname());
    String agentNamePrefix = nullSafe(agent.getPraefix());
    if (agentNamePrefix.length() > 0 && agentLastName.length() > 0) {
      table.add(agentNamePrefix + SPACE + agentLastName);
    } else {
      table.add(agentNamePrefix + agentLastName);
    }
    table.add(agent.getInitialen());
    table.add(agent.getVorname());
    table.add(agent.getWohnort());
    table.add(agent.getLand());
    table.add(agent.getKontakt_Strasse());
    table.add(agent.getKontakt_PLZ());
    table.add(agent.getKontakt_Wohnort());
    table.add(agent.getKontakt_Land());
  }

  /**
   * Find the result of a candidate. May be <code>null</code> in conflict mode.
   */
  private ListenkandidaturErgebnis getListenkandidaturErgebnis(String id_Ergebniseingang,
      Listenkandidatur lk) throws FinderException {
    return _bean.getListenkandidaturErgebnisHome()
        .findByErgebniseingangAndListenkandidatur(id_Ergebniseingang, lk.getID_Listenkandidatur());
  }

  private void addHeadings() {
    // P3 list data
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_politicalgroup));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_listNumber));
    // P2-list data
    if (isElectionsWithListGroups) {
      table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_setOrDistrict));
      table.add(Messages
          .getString(MessageKeys.CandidateDetailsExportGenerator_numberOfSetOrDistrict));
    }
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_votes));
    // candidate data
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_positionOnList));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_elected));
    // personal data
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_lastname));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_initials));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_firstname));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_city));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_countrycode));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_gender));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_street));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_postcode));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_city));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_countrycode));
    // agent
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_agent_lastname));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_agent_initials));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_agent_firstname));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_agent_city));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_agent_countrycode));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_agent_street));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_agent_postcode));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_agent_city));
    table.add(Messages.getString(MessageKeys.CandidateDetailsExportGenerator_agent_countrycode));
  }

  private int getStelOrKieskring(Liste p2List) {
    int result = p2List.getSatz();
    if (result > 0) {
      return result;
    } else {
      return p2List.getGruppeGebietsspezifischCol().iterator().next().getGebiet().getNummer();
    }
  }

  private String getGeschlecht(Listenkandidatur lk, Personendaten personendaten) {
    if (personendaten.getGeschlecht() != -1 && lk.getListe().isGeschlechtSichtbar()) {
      return PersonendatenKonstanten.Geschlecht.getName(personendaten.getGeschlecht(), lk
          .getListe().getPublicationLanguage());
    } else {
      return EMPTY_STRING;
    }
  }

  private static class SortableCandidate implements Comparable<SortableCandidate> {
    final Listenkandidatur _lk;
    final ListenkandidaturErgebnis _lke; // may be null

    SortableCandidate(Listenkandidatur lk, ListenkandidaturErgebnis lke) {
      _lk = lk;
      _lke = lke;
    }

    public String displayElected() {
      if (_lke == null) {
        return Messages.getString(MessageKeys.CandidateDetailsExportGenerator_no);
      } else if (_lke.isGewaehlt()) {
        return Messages.getString(MessageKeys.CandidateDetailsExportGenerator_yes);
      } else if (_lke.isGewaehltInGruppe()) {
        return Messages.getString(MessageKeys.CandidateDetailsExportGenerator_yesInOtherDistrict);
      }
      return Messages.getString(MessageKeys.CandidateDetailsExportGenerator_no);
    }

    private int sortIndex() {
      if (_lke == null) {
        return 200 + _lk.getListenplatz();
      } else if (_lke.getListenplatz() == 0) {
        return 100 + _lk.getListenplatz();
      } else {
        return _lke.getListenplatz();
      }
    }

    @Override
    public int compareTo(SortableCandidate other) {
      return this.sortIndex() - other.sortIndex();
    }
  }

  private String nullSafe(String string) {
    return string == null ? EMPTY_STRING : string;
  }

}
