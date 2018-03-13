/*
 * RG520Helper
 * 
 * Created on 31.08.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import static de.ivu.wahl.auswertung.erg.sv.kandidat.KandidatInfoToolkit.NL_COLLATOR;
import static de.ivu.wahl.dataimport.XMLTags.NS_RG;
import static de.ivu.wahl.export.XMLTags.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import org.apache.commons.lang.StringUtils;

import nu.xom.Attribute;
import nu.xom.Element;

import de.ivu.ejb.IVUBeanBase;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.dataimport.XMLTags;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.BesonderheitConstants;
import de.ivu.wahl.modell.GruppeKonstanten;
import de.ivu.wahl.modell.PublicationLanguage;
import de.ivu.wahl.modell.ejb.Alternative;
import de.ivu.wahl.modell.ejb.Besonderheit;
import de.ivu.wahl.modell.ejb.DHondtQuotient;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifisch;
import de.ivu.wahl.modell.ejb.Konflikt;
import de.ivu.wahl.modell.ejb.Liste;
import de.ivu.wahl.modell.ejb.Listenkandidatur;
import de.ivu.wahl.modell.ejb.ListenkandidaturErgebnis;
import de.ivu.wahl.modell.ejb.ListenplatzNeu;
import de.ivu.wahl.modell.ejb.ListenplatzNeuHome;
import de.ivu.wahl.modell.ejb.Personendaten;
import de.ivu.wahl.modell.ejb.Restsitzverteilung;
import de.ivu.wahl.modell.ejb.SitzberechnungErgebnis;
import de.ivu.wahl.modell.ejb.Unterverteilung;
import de.ivu.wahl.result.drawlots.DecisionType;
import de.ivu.wahl.result.result.AssignmentType;
import de.ivu.wahl.result.result.Distribution;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;
import de.ivu.wahl.wus.reportgen.EMLMessageType;

/**
 * Helper class for creating the rg:RG520 element.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
@SuppressWarnings("synthetic-access")
public class RG520Helper extends BasicRGHelper {
  private static final Comparator<Listenkandidatur> SORT_LISTENKANDIDATOR_BY_LISTENPLATZ = new Comparator<Listenkandidatur>() {
    @Override
    public int compare(Listenkandidatur x, Listenkandidatur y) {
      return x.getListenplatz() - y.getListenplatz();
    }
  };
  final RG520VotesHelper votesHelper;
  ContestIdentifierService contestIdentifierService = new ContestIdentifierService();

  private final boolean inConflict;
  private final boolean isEK = WahlInfo.getWahlInfo().isEK();

  /**
   * Constructor
   * 
   * @param id_Ergebniseingang
   */
  @SuppressWarnings("hiding")
  RG520Helper(ExportHandlingBean bean, boolean inConflict, String id_Ergebniseingang) {
    super(bean);
    this.inConflict = inConflict;
    this.votesHelper = new RG520VotesHelper(bean, id_Ergebniseingang);
  }

  /**
   * Creates eml520 subtree used for generation of report P22
   * 
   * @param id_Ergebniseingang id of import
   * @return xml subtree
   * @throws FinderException
   */
  Element createReportGeneratorElement520(String id_Ergebniseingang,
      boolean forCandidateLetters,
      boolean forProtocolAppendix) {
    Element rg520 = createRGElement(RG_520_ELEMENT);
    if (!forProtocolAppendix) {
      String dateStr = bean.getPropertyHandling().getProperty(RG_DATE_OF_MEETING_O1P20);
      rg520.appendChild(createRGElementWithValue(RG_DATE_OF_MEETING_O1P20,
          XMLHelper.createDateString(dateStr)));
    }

    if (!forCandidateLetters && !forProtocolAppendix) {
      String timeStr = bean.getPropertyHandling().getProperty(RG_TIME_OF_MEETINGP20);
      rg520.appendChild(createRGElementWithValue(RG_TIME_OF_MEETINGP20, timeStr));

      // For EK only
      String dateStr = bean.getPropertyHandling().getProperty(RG_DATE_PUBLICATION_VOTE_VALUES);
      if (dateStr != null && dateStr.length() > 0) {
        rg520.appendChild(createRGElementWithValue(RG_DATE_PUBLICATION_VOTE_VALUES,
            XMLHelper.createDateString(dateStr)));
      }
      String numberOfPublication = bean.getPropertyHandling()
          .getProperty(RG_NUMBER_PUBLICATION_VOTE_VALUES);
      if (numberOfPublication != null && numberOfPublication.length() > 0) {
        rg520.appendChild(createRGElementWithValue(RG_NUMBER_PUBLICATION_VOTE_VALUES,
            numberOfPublication));
      }
    }
    Collection<Gebiet> gebiete = bean.getGebietHandling().getGebieteMitListen();

    Element presenceVotes = XMLHelper.createElement(de.ivu.wahl.export.XMLTags.RG_PRESENCE_VOTES,
        NS_RG);
    rg520.appendChild(presenceVotes);
    if (gebiete.size() == 1) {
      Gebiet gebiet = gebiete.iterator().next();
      bean.appendAffiliationVotes(id_Ergebniseingang,
          presenceVotes,
          gebiet.getID_Gebiet(),
          false,
          EMLMessageType.EML520);
    }
    appendVotesForRGGeneralGroups(presenceVotes, id_Ergebniseingang);

    if (!forProtocolAppendix) {
      appendElectoralDistrictsOverview(rg520, gebiete);
      appendOverviewOfListsAndDistricts(rg520, gebiete);
      SortedMap<Integer, VotesByRegionNumber> groupsAndDistrictsAndVotes = appendOverviewOfListsAndDistrictsAndVotes(rg520);
      appendElectoralQuota(rg520, id_Ergebniseingang, groupsAndDistrictsAndVotes);
      // appendResultWithoutRegardingCombinedLists(rg520, id_Ergebniseingang);
      appendCheckingCombinedLists(rg520); // creates an empty element
      List<SitzberechnungErgebnis> firstAssignment = appendFirstAssignment(rg520,
          id_Ergebniseingang);
      appendNiemeyerAssignment(rg520, id_Ergebniseingang, firstAssignment);
      appendDHondtAssignment(rg520, id_Ergebniseingang);
      appendAbsoluteMajority(rg520, id_Ergebniseingang);
      appendExhaustedLists(rg520, id_Ergebniseingang);
      // appendAssignmentsWithinCombinedLists(rg520, id_Ergebniseingang);
      // appendAssignmentWithinCombinedListsLines(rg520, id_Ergebniseingang);
      appendAssignmentsWithinListGroups(rg520, id_Ergebniseingang, groupsAndDistrictsAndVotes);
      rg520.appendChild(createOverviewOfCandidatesAndResults(id_Ergebniseingang));
      rg520.appendChild(createOverviewOfElectedCandidates(id_Ergebniseingang));
    }
    rg520.appendChild(bean.createVoterObjectionsRG());
    if (forCandidateLetters) {
      rg520.appendChild(createLetterForElectedCandidates());
    }
    return rg520;
  }

  private Collection<Gebiet> appendElectoralDistrictsOverview(Element rg520,
      Collection<Gebiet> gebiete) {
    Element regions = createRGElement(RG_REGIONS_OVERVIEW);
    for (Gebiet gebiet : gebiete) {
      Element child = createRGElementWithValue(RG_REGION_NAME, gebiet.getName());
      regions.appendChild(child);
      int regionId = gebiet.getNummer();
      Attribute attr = new Attribute(ATTR_REGION_NAME_ID, String.valueOf(regionId));
      child.addAttribute(attr);
      if (isEK) {
        int voteValue = Math.max(gebiet.getVoteValue(), 1);
        attr = new Attribute(ATTR_VOTE_VALUE, String.valueOf(voteValue));
        child.addAttribute(attr);
      }
    }
    rg520.appendChild(regions);
    return gebiete;
  }

  /**
   * Append list metadata to report generator subtree
   * 
   * @param gebieteMitListen all regions with lists
   */
  private void appendOverviewOfListsAndDistricts(Element parent, Collection<Gebiet> gebieteMitListen) {
    for (Gruppe gruppe : bean.getGruppeHandling().getGroupsSorted()) {
      Element gruppeData = createRGElement(RG_LISTS_REGIONS);
      appendCandidateListName(gruppeData, gruppe);
      appendType(gruppeData, gruppe);
      appendSubmittedInElectoralDistrict(gruppeData, gebieteMitListen, gruppe);
      parent.appendChild(gruppeData);
    }
  }

  private void appendCandidateListName(Element parent, Gruppe gruppe) {
    Element candidateList = createRGElement(RG_CANDIDATE_LIST);
    candidateList.appendChild(bean.createListIdentifierElement(gruppe));
    // For blank lists we need the first candidate here
    if (gruppe.getNameKurz() == null || gruppe.getNameKurz().trim().length() == 0) {
      Collection<Liste> lists = gruppe.getListeCol();
      Liste firstList = lists.iterator().next();
      Element name = createRGElement(RG_FIRST_CANDIDATE);
      name.appendChild(bean.createPersonNameElement(firstList.getListenkandidaturCol().iterator()
          .next().getPersonendaten()));
      candidateList.appendChild(name);
    }
    parent.appendChild(candidateList);
  }

  private void appendType(Element parent, Gruppe gruppe) {
    String listType = contestIdentifierService.getListType(gruppe).getEml();
    parent.appendChild(createRGElementWithValue(RG_TYPE, listType));
  }

  private void appendSubmittedInElectoralDistrict(Element parent,
      Collection<Gebiet> gebieteMitListen,
      Gruppe gruppe) {
    // Regions with/without list
    Set<Integer> regionsWithList = new HashSet<Integer>();
    for (GruppeGebietsspezifisch gg : gruppe.getGruppeGebietsspezifischCol()) {
      regionsWithList.add(gg.getGebiet().getNummer());
    }
    for (Gebiet gebiet : gebieteMitListen) {
      Element listeInGebiet = createRGElementWithAttribute(RG_SUBMITTED_IN_REGION,
          ATTR_ID,
          String.valueOf(gebiet.getNummer()));
      String trittAn = String.valueOf(regionsWithList.contains(gebiet.getNummer()));
      listeInGebiet.appendChild(trittAn);
      parent.appendChild(listeInGebiet);
    }
  }

  /**
   * Appends for each P3-list one element rg:OverviewOfListsAndDistrictsAndVotes. Also appent one
   * rg:OverviewOfListsAndDistrictsAndVotes for the sum of all P3-lists.
   */
  private SortedMap<Integer, VotesByRegionNumber> appendOverviewOfListsAndDistrictsAndVotes(Element rg520) {
    SortedSet<Integer> allDistricts = new TreeSet<Integer>();
    SortedMap<Integer, VotesByRegionNumber> groupsAndDistrictsAndVotes = new TreeMap<Integer, VotesByRegionNumber>();

    Collection<Gruppe> gruppen = bean.getGruppeHandling().getGroupsSorted();
    for (Gruppe gruppe : gruppen) {
      VotesByRegionNumber votesByRegionNumber = votesHelper.getVotesPerDistrictForP3List(gruppe);
      for (Integer regionNumber : votesByRegionNumber.getRegionNumbers()) {
        allDistricts.add(regionNumber);
      }
      Integer groupKey = Integer.valueOf(gruppe.getSchluessel());
      groupsAndDistrictsAndVotes.put(groupKey, votesByRegionNumber);
    }

    // Create EML for the P3-lists
    for (Integer groupKey : groupsAndDistrictsAndVotes.keySet()) {
      Element groupResult = createRGElementWithAttribute(RG_LISTS_REGIONS_VOTES,
          ATTR_LIST_NUMBER,
          String.valueOf(groupKey));
      VotesByRegionNumber votesByRegionNumber = groupsAndDistrictsAndVotes.get(groupKey);
      for (Integer district : allDistricts) {
        Integer votes = votesByRegionNumber.getVotes(district);
        Integer weighted = votesByRegionNumber.getWeightedVotes(district);
        appendVotesInElectoralDistrict(groupResult, votes, weighted, district.toString());
      }
      Integer sum = votesByRegionNumber.getTotalVotes();
      Integer weightedSum = votesByRegionNumber.getTotalWeightedVotes();
      appendVotesInElectoralDistrict(groupResult, sum, weightedSum, ATTR_VALUE_ALLE);
      rg520.appendChild(groupResult);
    }

    Map<Integer, Gruppe> specialGroups = bean.getGruppeHandling().getSpecialGroups();
    // Create EML for the sum of all P3-lists

    Gruppe val = specialGroups.get(GruppeKonstanten.GruppeAllgemein.GUELTIGE.schluessel);
    appendOverviewOfDistrictsVotes(rg520, allDistricts, val, RG_REGION_VOTES);

    Gruppe blanc = specialGroups.get(GruppeKonstanten.GruppeAllgemein.LEER.schluessel);
    appendOverviewOfDistrictsVotes(rg520, allDistricts, blanc, RG_REGION_BLANC_VOTES);

    Gruppe inv = specialGroups.get(GruppeKonstanten.GruppeAllgemein.UNGUELTIGE.schluessel);
    appendOverviewOfDistrictsVotes(rg520, allDistricts, inv, RG_REGION_INVALID_VOTES);

    return groupsAndDistrictsAndVotes;
  }

  private void appendOverviewOfDistrictsVotes(Element parent,
      SortedSet<Integer> allDistricts,
      Gruppe gruppe,
      String xmlTag) {
    Element totalResults = createRGElementWithAttribute(xmlTag, ATTR_LIST_NUMBER, ATTR_VALUE_ALLE);

    VotesByRegionNumber votesByRegionNumber = votesHelper.getVotesPerDistrictForP3List(gruppe);
    for (Integer district : allDistricts) {
      Integer votes = votesByRegionNumber.getVotes(district);
      Integer weighted = votesByRegionNumber.getWeightedVotes(district);
      appendVotesInElectoralDistrict(totalResults, votes, weighted, district.toString());
    }
    Integer sum = votesByRegionNumber.getTotalVotes();
    Integer weightedSum = votesByRegionNumber.getTotalWeightedVotes();
    appendVotesInElectoralDistrict(totalResults, sum, weightedSum, ATTR_VALUE_ALLE);

    parent.appendChild(totalResults);
  }

  private void appendElectoralQuota(Element rg520,
      String id_Ergebniseingang,
      SortedMap<Integer, VotesByRegionNumber> groupsAndDistrictsAndVotes) {
    int gueltigeStimmen;
    if (isEK) {
      int sum = 0;
      for (VotesByRegionNumber votesByRegionNumber : groupsAndDistrictsAndVotes.values()) {
        sum += votesByRegionNumber.getTotalWeightedVotes();
      }
      gueltigeStimmen = sum;
    } else {
      gueltigeStimmen = getGueltigeStimmen(id_Ergebniseingang);
    }
    int anzahlSitze = WahlInfo.getWahlInfo().getWahl().getAnzahlSitze();

    Element quota = createRGElement(RG_QUOTA);
    quota.appendChild(createFractionRG(gueltigeStimmen, anzahlSitze));
    rg520.appendChild(quota);
  }

  private int getGueltigeStimmen(String id_Ergebniseingang) {
    return bean.getVotesHandling().getTotalValidVotes(id_Ergebniseingang);
  }

  private Element createFractionRG(int numerator, int denominator) {
    Element fraction = createRGElement(RG_FRACTION);
    fraction.addAttribute(new Attribute(ATTR_NUMERATOR, String.valueOf(numerator)));
    fraction.addAttribute(new Attribute(ATTR_DENOMINATOR, String.valueOf(denominator)));
    return fraction;
  }

  /**
   * The CheckingCombinedLists element is always empty.
   */
  private void appendCheckingCombinedLists(Element rg520) {
    Element result = createRGElement(RG_CHECK_COMBINED_LISTS);
    rg520.appendChild(result);
  }

  /**
   * Create xml subtree with candidate/list results for report generator
   * 
   * @param id_Ergebniseingang voting result import id
   * @return xml subtree with candidate/list results for report generator
   */
  private Element createOverviewOfCandidatesAndResults(String id_Ergebniseingang) {
    Element results = createRGElement(RG_CANDIDATES_RESULTS);
    appendAnomalyInSeatDistributions(results, id_Ergebniseingang);
    for (Gruppe gruppe : bean.getGruppeHandling().getGroupsSorted()) {
      appendListGroupAndResults(results, id_Ergebniseingang, gruppe);
    }
    return results;
  }

  private void appendAnomalyInSeatDistributions(Element parent, String id_Ergebniseingang) {
    try {
      List<Besonderheit> besonderheiten = getBesonderheiten(id_Ergebniseingang);
      for (Besonderheit besonderheit : besonderheiten) {
        if (besonderheit.getBesonderheitart() == BesonderheitConstants.ART_SHALL_BE_PUBLISHED_IN_P22) {
          parent.appendChild(createRGElementWithValue(RG_ANOMALY_IN_SEAT_DISTRIBUTION,
              besonderheit.getText()));
        }
      }
    } catch (FinderException e) {
      throw new EJBException(
          Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0, "Besonderheiten"), e); //$NON-NLS-1$
    }
  }

  private List<Besonderheit> getBesonderheiten(String id_Ergebniseingang) throws FinderException {
    try {
      List<Besonderheit> list = new ArrayList<Besonderheit>(bean.getBesonderheitHome()
          .findAllByErgebniseingang(id_Ergebniseingang));
      Collections.sort(list, new Comparator<Besonderheit>() {

        @Override
        public int compare(Besonderheit x, Besonderheit y) {
          return x.getNummer() - y.getNummer();
        }
      });
      return list;
    } catch (FinderException e) {
      if (inConflict) {
        return null;
      } else {
        throw e;
      }
    }
  }

  private void appendListGroupAndResults(Element parent, String id_Ergebniseingang, Gruppe gruppe) {
    try {
      Element groupResult = createRGElement(RG_LIST_GROUP_RESULTS);
      groupResult.appendChild(bean.createListIdentifierElement(gruppe));
      appendType(groupResult, gruppe);

      Collection<Liste> listen = gruppe.getListeCol();
      int seatsSum = 0;
      int votesSum = 0;
      Map<Liste, Integer> listsAndVotes = new HashMap<Liste, Integer>();
      Map<Liste, Integer> listsAndSeats = new HashMap<Liste, Integer>();
      for (Liste liste : listen) {
        int seats = bean.getSeatsHandling().getTotalSeatsForP2List(id_Ergebniseingang,
            liste,
            inConflict);
        seatsSum += seats;
        listsAndSeats.put(liste, seats);
        VotesByRegionNumber votesByRegionNumber = votesHelper.getVotesForP2ListByRegion(liste);
        int votes = isEK ? votesByRegionNumber.getTotalWeightedVotes() : votesByRegionNumber
            .getTotalVotes();
        votesSum += votes;
        listsAndVotes.put(liste, votes);
      }
      if (inConflict) {
        // In case of drawing lots, the total seats must be calculated from Sitzberechnungsergebnis
        seatsSum = bean.getSeatsHandling().getTotalSeatsForP3List(id_Ergebniseingang, gruppe);
      }
      groupResult.appendChild(createRGElementWithValue(RG_SEATS, String.valueOf(seatsSum)));
      groupResult.appendChild(createRGElementWithValue(RG_VOTES, String.valueOf(votesSum)));

      Set<String> listPositionsChanged = getListsWithChangedPositions(id_Ergebniseingang);
      Map<Personendaten, Listenkandidatur> deadCandidates = new HashMap<Personendaten, Listenkandidatur>();
      Map<Personendaten, Integer> candidatesAndTotalVotes = new HashMap<Personendaten, Integer>();
      Set<Listenkandidatur> priorityCandidates = new LinkedHashSet<Listenkandidatur>();
      List<Listenkandidatur> candidateElectedByPositionOnList = new ArrayList<Listenkandidatur>();

      for (Liste liste : listen) {
        Integer seats = listsAndSeats.get(liste);
        Integer votes = listsAndVotes.get(liste);
        appendListAndResults(groupResult,
            id_Ergebniseingang,
            listPositionsChanged,
            liste,
            seats,
            votes,
            deadCandidates,
            priorityCandidates,
            candidateElectedByPositionOnList,
            candidatesAndTotalVotes);
      }

      appendDeadCandidates(groupResult, deadCandidates);
      appendPriorityCandidates(groupResult, candidatesAndTotalVotes, priorityCandidates);
      appendCandidatesElectedByPositionOnList(groupResult,
          candidatesAndTotalVotes,
          candidateElectedByPositionOnList);

      parent.appendChild(groupResult);
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0, "Listen"), e); //$NON-NLS-1$
    }
  }

  private void appendDeadCandidates(Element groupResult,
      Map<Personendaten, Listenkandidatur> deadCandidates) {
    for (Listenkandidatur listenkandidatur : deadCandidates.values()) {
      Element dc = createRGElement(RG_DEAD_CANDIDATE);
      Element cand = bean.createCandidateElement(listenkandidatur,
          false,
          false,
          true,
          EMLMessageType.EML520);
      dc.appendChild(cand);
      groupResult.appendChild(dc);
    }
  }

  private void appendPriorityCandidates(Element groupResult,
      Map<Personendaten, Integer> candidatesAndTotalVotes,
      Set<Listenkandidatur> priorityCandidates) {
    for (Listenkandidatur listenkandidatur : priorityCandidates) {
      Element pc = createRGElement(RG_PRIORITY_CANDIDATE);
      Liste liste = listenkandidatur.getListe();
      Collection<GruppeGebietsspezifisch> ggs = liste.getGruppeGebietsspezifischCol();
      if (ggs.size() == 1) {
        Gebiet gebiet = ggs.iterator().next().getGebiet();
        addAttributesForDistrict(pc, gebiet);
      } else {
        pc.addAttribute(new Attribute(ATTR_SET, String.valueOf(liste.getSatz())));
      }

      Element cand = bean.createCandidateElement(listenkandidatur,
          false,
          false,
          true,
          EMLMessageType.EML520);
      pc.appendChild(cand);
      int votes = candidatesAndTotalVotes.get(listenkandidatur.getPersonendaten());
      pc.appendChild(createRGElementWithValue(RG_VOTES, votes));
      groupResult.appendChild(pc);
    }
  }

  private void appendCandidatesElectedByPositionOnList(Element parent,
      Map<Personendaten, Integer> candidatesAndTotalVotes,
      List<Listenkandidatur> candidateElectedByPositionOnList) {
    Liste previous = null;
    Element candidates = null;
    for (Listenkandidatur listenkandidatur : candidateElectedByPositionOnList) {
      Liste liste = listenkandidatur.getListe();
      if (!liste.equals(previous)) {
        candidates = createRGElement(RG_CANDIDATES_ELECTED_ON_LIST);
        parent.appendChild(candidates);
        Collection<GruppeGebietsspezifisch> ggs = liste.getGruppeGebietsspezifischCol();
        if (ggs.size() == 1) {
          Gebiet gebiet = ggs.iterator().next().getGebiet();
          addAttributesForDistrict(candidates, gebiet);
        } else {
          candidates.addAttribute(new Attribute(ATTR_SET, String.valueOf(liste.getSatz())));
        }
        previous = liste;
      }

      Element pc = createRGElement(RG_CANDIDATE_ELECTED_ON_LIST);
      Element cand = bean.createCandidateElement(listenkandidatur,
          false,
          false,
          true,
          EMLMessageType.EML520);
      pc.appendChild(cand);
      int votes = candidatesAndTotalVotes.get(listenkandidatur.getPersonendaten());
      pc.appendChild(createRGElementWithValue(RG_VOTES, votes));
      candidates.appendChild(pc);
    }
  }

  private void appendListAndResults(Element parent,
      String id_Ergebniseingang,
      Set<String> listPositionsChanged,
      Liste liste,
      Integer seats,
      Integer votes,
      Map<Personendaten, Listenkandidatur> deadCandidates,
      Set<Listenkandidatur> priorityCandidates,
      List<Listenkandidatur> candidateElectedByPositionOnList,
      Map<Personendaten, Integer> candidatesAndTotalVotes) throws FinderException {
    Element listResult = createRGElement(RG_LIST_RESULTS);
    Collection<GruppeGebietsspezifisch> ggs = liste.getGruppeGebietsspezifischCol();
    if (ggs.size() == 1) {
      Gebiet gebiet = ggs.iterator().next().getGebiet();
      addAttributesForDistrict(listResult, gebiet);
    }

    listResult.appendChild(bean.createListIdentifierElement(liste));
    String listType = contestIdentifierService.getListType(liste).getEml();
    listResult.appendChild(createRGElementWithValue(RG_TYPE, listType));

    Element listData = createListData(liste);
    listResult.appendChild(listData);

    listResult.appendChild(createRGElementWithValue(RG_SEATS, seats.toString()));
    listResult.appendChild(createRGElementWithValue(RG_VOTES, votes.toString()));

    listResult.appendChild(createRGElementWithValue(RG_LIST_POSITIONS_CHANGED,
        String.valueOf(listPositionsChanged.contains(liste.getID_Liste()))));
    // Candidate results
    List<Listenkandidatur> candidates = new ArrayList<Listenkandidatur>(bean
        .getListenkandidaturHome().findAllByListe(liste.getID_Liste()));
    Collections.sort(candidates, SORT_LISTENKANDIDATOR_BY_LISTENPLATZ);

    for (Listenkandidatur lk : candidates) {
      appendCandidateAndResult(id_Ergebniseingang,
          lk,
          listResult,
          deadCandidates,
          priorityCandidates,
          candidateElectedByPositionOnList,
          candidatesAndTotalVotes);
    }
    VotesByRegionNumber votesByRegionNumber = votesHelper.getVotesForP2ListByRegion(liste);
    appendVotesInElectoralDistrictParts(listResult, votesByRegionNumber);
    parent.appendChild(listResult);
  }

  private Element createListData(Liste liste) {
    boolean publishGender = liste.isGeschlechtSichtbar();
    Element listData = XMLHelper.createElementWithAttribute(XMLTags.KR_LISTENDATEN,
        XMLTags.NS_KR,
        XMLTags.ATTR_GESCHLECHT_SICHTBAR,
        String.valueOf(publishGender));

    String publicationLanguage = PublicationLanguage.toValidAbbreviation(liste
        .getPublicationLanguage());
    listData.addAttribute(new Attribute(XMLTags.ATTR_PUBLICATION_LANGUAGE, publicationLanguage));

    int satz = liste.getSatz();
    if (satz > 0) {
      listData.addAttribute(new Attribute(ATTR_BELONGS_TO_SET, String.valueOf(satz)));
    }
    return listData;
  }

  private Set<String> getListsWithChangedPositions(String id_Ergebniseingang) {
    try {
      Set<String> listIds = new HashSet<String>();
      Collection<ListenplatzNeu> lpNeuCol = ((ListenplatzNeuHome) IVUBeanBase
          .findLocalHomeExt("ListenplatzNeu")).findAllByErgebniseingang(id_Ergebniseingang); //$NON-NLS-1$
      for (ListenplatzNeu gr : lpNeuCol) {
        if (gr.isGeaendert()) {
          listIds.add(gr.getID_Liste());
        }
      }
      return listIds;
    } catch (FinderException e) {
      throw new EJBException(
          Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0, "ListenplatzNeu"), e); //$NON-NLS-1$
    }
  }

  private List<SitzberechnungErgebnis> appendFirstAssignment(Element parent,
      String id_Ergebniseingang) {
    Element result = createRGElement(RG_FIRST_ASSIGNMENT);
    try {
      ArrayList<SitzberechnungErgebnis> firstAss = new ArrayList<SitzberechnungErgebnis>(bean
          .getSitzberechnungErgebnisHome()
          .findAllByErgebniseingangAndTypAndVerteilung(id_Ergebniseingang,
              AssignmentType.FIRST_ASSIGNMENT.getId(),
              Distribution.P42.getId()));
      Collections.sort(firstAss, new AssignmentComparator());
      int anzSitze = 0;
      for (SitzberechnungErgebnis sbe : firstAss) {
        Element line = createRGElement(RG_FIRST_ASSIGNMENT_LINE);
        line.appendChild(createListOrCombinedList(sbe, true, Distribution.P42));
        line.appendChild(createRGElementWithValue(RG_VOTES, sbe.getZaehler()));
        line.appendChild(createRGElementWithValue(RG_SEATS, sbe.getSitze()));
        result.appendChild(line);
        anzSitze += sbe.getSitze();
      }
      result.appendChild(createRGElementWithValue(RG_TOTAL_SEATS_ASSIGNED, anzSitze));
      int anzRestSitze = WahlInfo.getWahlInfo().getWahl().getAnzahlSitze() - anzSitze;
      result.appendChild(createRGElementWithValue(RG_RESIDUAL_SEATS, anzRestSitze));
      parent.appendChild(result);
      return firstAss;
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "Sitzberechnungsergebnisse"), e); //$NON-NLS-1$
    }
  }

  /**
   * Niemeyer assignment exists only for GR1 / BC / GC elections.
   * 
   * @param firstAssignment
   */
  private void appendNiemeyerAssignment(Element rg520,
      String id_Ergebniseingang,
      List<SitzberechnungErgebnis> firstAssignment) {
    try {
      // All Niemeyer assignments to P42-lists
      List<SitzberechnungErgebnis> niemeyerAss = new ArrayList<SitzberechnungErgebnis>(bean
          .getSitzberechnungErgebnisHome()
          .findAllByErgebniseingangAndTypAndVerteilung(id_Ergebniseingang,
              AssignmentType.NIEMEYER_ASSIGNMENT.getId(),
              Distribution.P42.getId()));
      if (niemeyerAss.size() == 0) {
        return;
      }
      Collections.sort(niemeyerAss, new AssignmentComparator());

      int gueltigeStimmen = getGueltigeStimmen(id_Ergebniseingang);
      int anzahlSitze = WahlInfo.getWahlInfo().getWahl().getAnzahlSitze();

      // All first Assignments
      Element result = createRGElement(RG_NIEMEYER_ASSIGNMENT);
      for (SitzberechnungErgebnis firstAss : firstAssignment) {
        int votes = firstAss.getZaehler();
        // Find out which P42-list is allowed to take part in the Niemeyer assignment (needs >= 75%
        // of KT), see OSVI-1171, i.e.
        // votes >= 3 / 4 * gueltigeStimmen / anzahlSitze
        // 4 * votes * anzahlSitze >= 3 * gueltigeStimmen
        long lhs = 4L * votes * anzahlSitze;
        long rhs = 3L * gueltigeStimmen;
        if (WahlInfo.getWahlInfo().getElectionCategory().equals(ElectionCategory.BC)) {
          // See OSV-1296: For BC elections, the relevant fraction is 1/2, not 3/4
          lhs = 2L * votes * anzahlSitze;
          rhs = 1L * gueltigeStimmen;
        }
        if (lhs >= rhs) {
          Element line = createRGElement(RG_NIEMEYER_ASSIGNMENT_LINE);
          line.appendChild(createListOrCombinedList(firstAss, false, Distribution.P42));
          line.appendChild(createRGElementWithValue(RG_PRIOR_SEATS, firstAss.getSitze()));

          // Add Niemeyer seats for each of the lists from the first assignment
          int newSeats = 0;
          P42List p42List = new P42List(firstAss);
          for (SitzberechnungErgebnis sbe : niemeyerAss) {
            P42List p42ListNiemeyer = new P42List(sbe);
            if (p42ListNiemeyer.equals(p42List)) {
              newSeats += sbe.getSitze();
            }
          }
          line.appendChild(createRGElementWithValue(RG_NEW_SEATS, newSeats));

          // The remainder fraction must be part of the supplement of the first assignment (see
          // AssignmentSupplementWithRemainderFraction)
          int remainderNumerator = firstAss.getZaehlerVomRest();
          int remainderDenominator = firstAss.getNennerVomRest();
          Element remainder = createRGElement(RG_REMAINDER);
          remainder.appendChild(createFractionRG(remainderNumerator, remainderDenominator));
          line.appendChild(remainder);
          result.appendChild(line);
        }
      }
      List<Konflikt> konflikte = getKonflikte(id_Ergebniseingang, DecisionType.NIEMEYER_P42);
      appendAllottings(result, konflikte, Distribution.P42);
      rg520.appendChild(result);
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "Sitzberechnungsergebnisse"), e); //$NON-NLS-1$
    }
  }

  /**
   * d'Hondt-Assignment to P42-lists
   */
  private void appendDHondtAssignment(Element rg520, String id_Ergebniseingang) {
    try {
      Collection<Restsitzverteilung> rsCol = bean.getRestsitzverteilungHome()
          .findAllForP42Distribution(id_Ergebniseingang);
      List<Konflikt> konflikte = getKonflikte(id_Ergebniseingang, DecisionType.DHONDT_P42);
      appendDHondtAssignment(rg520, rsCol, konflikte, "P7", Distribution.P42); //$NON-NLS-1$
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "Restsitzverteilung"), e); //$NON-NLS-1$
    }
  }

  private void appendDHondtAssignment(Element parent,
      Collection<Restsitzverteilung> rsCol,
      List<Konflikt> konflikte,
      String article,
      Distribution distribution) {
    if (rsCol.isEmpty()) {
      return;
    }

    List<Restsitzverteilung> rsList = new ArrayList<Restsitzverteilung>(rsCol);
    Collections.sort(rsList, new RestsitzverteilungComparator());

    int numberOfUnassignedSeatsBeforeDHondtAssignment = 0;
    if (!rsList.isEmpty()) {
      /*
       * The number of unassigned seats before the first assignment by largest average. This is only
       * needed for model P22-2 for GR1 elections in case a d'Hondt assignment is needed after a
       * Niemeyer assignment. The information is stored redundantly in each Restsitzverteilung
       * object.
       */
      numberOfUnassignedSeatsBeforeDHondtAssignment = rsList.get(0).getSitzeGesamtZuVerteilen();
    }

    Element child = createRGElementWithAttribute(RG_DHONDT_ASSIGNMENT, ATTR_ARTICLE, article);
    child.addAttribute(new Attribute(ATTR_NUMBER_OF_UNASSIGNED_SEATS_BEFORE_DHONDT_ASSIGNMENT,
        String.valueOf(numberOfUnassignedSeatsBeforeDHondtAssignment)));
    int maxNumberOfAssignments = appendDHondtAssignmentLines(child, rsList);
    child.addAttribute(new Attribute(ATTR_MAX_NUMBER_OF_ASSIGNMENTS, String
        .valueOf(maxNumberOfAssignments)));
    appendAllottings(child, konflikte, distribution);
    parent.appendChild(child);
  }

  private int appendDHondtAssignmentLines(Element parent, List<Restsitzverteilung> rsList) {
    int max = 0;
    for (Restsitzverteilung rs : rsList) {
      Element line = createRGElement(RG_DHONDT_ASSIGNMENT_LINE);
      line.appendChild(createListOrCombinedList(rs));
      line.appendChild(createRGElementWithValue(RG_PRIOR_SEATS, rs.getSitze()));
      line.appendChild(createRGElementWithValue(RG_NEW_SEATS, rs.getSitzeRest()));
      max = Math.max(max, rs.getDHondtQuotientCol().size());
      for (DHondtQuotient q : rs.getDHondtQuotientCol()) {
        String step = String.valueOf(q.getLauf());
        Element fraction = createRGElementWithAttribute(RG_DHONT_FRACTION, ATTR_STEP, step);
        if (q.isSitzAusRestanteil()) {
          fraction.addAttribute(new Attribute(ATTR_WINNER, "true")); //$NON-NLS-1$
        }
        fraction.appendChild(createFractionRG(q.getZaehler(), q.getNenner()));
        line.appendChild(fraction);
      }
      parent.appendChild(line);
    }
    return max;
  }

  private void appendAllottings(Element parent, List<Konflikt> konflikte, Distribution distribution) {
    for (Konflikt konflikt : konflikte) {
      String numberAlternatives = String.valueOf(konflikt.getAlternativeCol().size());
      Element allotting = createRGElementWithAttribute(RG_ALLOTTING,
          ATTR_NUMBER_CHOICES,
          numberAlternatives);
      Element winner = createRGElement(RG_WINNER);
      Alternative los = konflikt.getLosAlternative();
      winner.appendChild(createListOrCombinedList(los, distribution));
      allotting.appendChild(winner);
      for (Alternative alternative : konflikt.getAlternativeCol()) {
        if (alternative.getID_Alternative().equals(los.getID_Alternative())) {
          continue;
        }
        Element looser = createRGElement(RG_LOOSER);
        looser.appendChild(createListOrCombinedList(alternative, distribution));
        allotting.appendChild(looser);
      }
      parent.appendChild(allotting);
    }
  }

  private void appendAbsoluteMajority(final Element rg520, final String id_Ergebniseingang) {
    try {
      ArrayList<SitzberechnungErgebnis> sbeList = new ArrayList<SitzberechnungErgebnis>(bean
          .getSitzberechnungErgebnisHome().findAllByErgebniseingangAndTyp(id_Ergebniseingang,
              AssignmentType.ABSOLUTE_MAJORITY.getId()));
      if (sbeList.size() == 0) {
        return;
      }
      SitzberechnungErgebnis winnerSbe = null;
      SitzberechnungErgebnis looserSbe = null;
      for (SitzberechnungErgebnis sbe : sbeList) {
        if (sbe.getSitze() == 1) {
          if (winnerSbe != null) {
            throw new RuntimeException(Messages.getString("Error_ZweiGewinnerFuerAbsoluteMajority")); //$NON-NLS-1$
          }
          winnerSbe = sbe;
        } else if (sbe.getSitze() == -1) {
          if (looserSbe != null) {
            throw new RuntimeException(
                Messages.getString("Error_ZweiVerliererFuerAbsoluteMajority")); //$NON-NLS-1$
          }
          looserSbe = sbe;
        }
      }
      Element result = createRGElement(RG_MAJORITY);
      Element winner = createRGElement(RG_WINNER);
      Element looser = createRGElement(RG_LOOSER);
      winner.appendChild(createListOrCombinedList(winnerSbe, false, Distribution.P42));
      looser.appendChild(createListOrCombinedList(looserSbe, false, Distribution.P42));
      result.appendChild(winner);
      result.appendChild(looser);
      List<Konflikt> konflikte = getKonflikte(id_Ergebniseingang, DecisionType.ABSOLUTE_MAJORITY);
      appendAllottings(result, konflikte, Distribution.P42);
      rg520.appendChild(result);
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "Sitzberechnungsergebnisse"), e); //$NON-NLS-1$
    }
  }

  private void appendExhaustedLists(Element result, String id_Ergebniseingang) {
    try {
      List<SitzberechnungErgebnis> sbeList = new ArrayList<SitzberechnungErgebnis>(bean
          .getSitzberechnungErgebnisHome()
          .findAllByErgebniseingangAndTypAndVerteilung(id_Ergebniseingang,
              AssignmentType.EXHAUSTED_LIST.getId(),
              Distribution.P42.getId()));
      Collections.sort(sbeList, new AssignmentComparator());
      appendExhaustedLists(result, sbeList, Distribution.P42);
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "Sitzberechnungsergebnisse"), e); //$NON-NLS-1$
    }
  }

  private void appendExhaustedLists(Element parent,
      Collection<SitzberechnungErgebnis> sbeList,
      Distribution distribution) {
    for (SitzberechnungErgebnis sbe : sbeList) {
      if (sbe.getSitze() != 0) {
        // positiv angeben !!!
        String lostSeats = String.valueOf(-sbe.getSitze());
        Element exhaustedList = createRGElementWithAttribute(RG_EXHAUSTED_LIST,
            ATTR_LOST_SEATS,
            lostSeats);
        exhaustedList.appendChild(createListOrCombinedList(sbe, false, distribution));
        parent.appendChild(exhaustedList);
      }
    }
  }

  private List<Konflikt> getDecisionsForP3Lists(List<Konflikt> konflikte, String id_Gruppe) {
    List<Konflikt> result = new ArrayList<Konflikt>();
    for (Konflikt konflikt : konflikte) {
      if (belongsToP3List(konflikt, id_Gruppe)) {
        result.add(konflikt);
      }
    }
    return result;
  }

  /**
   * @return <code>true</code> if this is a decision between P2-lists within the P3-list given by
   *         <code>id_Gruppe</code>.
   */
  private boolean belongsToP3List(Konflikt konflikt, String id_Gruppe) {
    try {
      Liste liste = konflikt.getAlternativeCol().iterator().next().getListe();
      if (liste == null) {
        return false;
      }
      return liste.getGruppe().getID_Gruppe().equals(id_Gruppe);
    } catch (NullPointerException e) {
      LOGGER.error("Error in belongsToP3List()", e); //$NON-NLS-1$
      return false;
    } catch (NoSuchElementException e) {
      LOGGER.error("Error in belongsToP3List()", e); //$NON-NLS-1$
      return false;
    }
  }

  /**
   * Adds XML elements for exhausted P3-lists, if needed
   */
  private void appendExhaustedP3Lists(Element awcl,
      String combId,
      SitzberechnungErgebnisHelper sbeHelper) {
    Collection<SitzberechnungErgebnis> exhaustedLists = sbeHelper
        .getExhaustedListAssignments(combId);
    appendExhaustedLists(awcl, exhaustedLists, Distribution.P3);
  }

  /**
   * Adds XML elements for exhausted P2-lists, if needed
   */
  private void appendExhaustedP2Lists(Element awlg,
      String id_Gruppe,
      SitzberechnungErgebnisHelper sbeHelper) {
    Collection<SitzberechnungErgebnis> exhaustedLists = sbeHelper
        .getExhaustedListAssignments(id_Gruppe);
    appendExhaustedLists(awlg, exhaustedLists, Distribution.P2);
  }

  private void appendAssignmentsWithinListGroups(Element rg520,
      String id_Ergebniseingang,
      SortedMap<Integer, VotesByRegionNumber> groupsAndDistrictsAndVotes) {
    try {
      Collection<SitzberechnungErgebnis> sbes = bean.getSitzberechnungErgebnisHome()
          .findAllByErgebniseingangAndVerteilung(id_Ergebniseingang, Distribution.P2.getId());
      SitzberechnungErgebnisHelper sbeHelper = new SitzberechnungErgebnisHelper(sbes);

      List<Unterverteilung> subDistributions = new ArrayList<Unterverteilung>(bean
          .getUnterverteilungHome().findAllForP2(id_Ergebniseingang));
      Collections.sort(subDistributions, new Comparator<Unterverteilung>() {
        public int compare(Unterverteilung x, Unterverteilung y) {
          return x.getID_Gruppe().compareTo(y.getID_Gruppe());
        }
      });

      List<Konflikt> konflikteNiemeyer = getKonflikte(id_Ergebniseingang, DecisionType.NIEMEYER_P2);
      List<Konflikt> konflikteDHondt = getKonflikte(id_Ergebniseingang, DecisionType.DHONDT_P2);
      for (Unterverteilung uv : subDistributions) {
        Element awlg = createRGElement(RG_ASSIGNMENT_LIST_GROUPS);
        Gruppe gruppe = uv.getGruppe();
        VotesByRegionNumber districtsAndVotes = groupsAndDistrictsAndVotes.get(gruppe
            .getSchluessel());

        String id_Gruppe = uv.getID_Gruppe();
        awlg.appendChild(bean.createListIdentifierElement(gruppe));
        Element quota = createRGElement(RG_QUOTA);
        quota.appendChild(createFractionRG(uv.getStimmen(), uv.getSitze()));
        awlg.appendChild(quota);

        // For each P3-list in the combined list, find out the votes, first assignment seats,
        // Niemeyer seats, Niemeyer remainder
        for (String id_Liste : sbeHelper.getChildren(id_Gruppe)) {
          appendAssignmentWithinListGroupsLines(awlg, sbeHelper, id_Liste, districtsAndVotes);
        }

        // Special cases: drawing lots (Niemeyer), exhausted list, d'Hondt
        List<Konflikt> decisionsNiemeyer = getDecisionsForP3Lists(konflikteNiemeyer, id_Gruppe);
        appendAllottings(awlg, decisionsNiemeyer, Distribution.P2);
        appendExhaustedP2Lists(awlg, id_Gruppe, sbeHelper);
        List<Konflikt> decisionsDHondt = getDecisionsForP3Lists(konflikteDHondt, id_Gruppe);
        Collection<Restsitzverteilung> rsList = bean.getRestsitzverteilungHome()
            .findAllByGruppe(id_Ergebniseingang, id_Gruppe);
        appendDHondtAssignment(awlg, rsList, decisionsDHondt, "P13", Distribution.P2); //$NON-NLS-1$

        rg520.appendChild(awlg);
      }
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "Sitzberechnungsergebnisse"), e); //$NON-NLS-1$
    }
  }

  /**
   * Creates <AssignmentWithinListGroupsLine> elements that are very close to the table
   * representation in the document.
   */
  private void appendAssignmentWithinListGroupsLines(Element parent,
      SitzberechnungErgebnisHelper sbeHelper,
      String id_Liste,
      VotesByRegionNumber districtsAndVotes) {
    Element line = createRGElement(RG_ASSIGNMENT_LIST_GROUPS_LINE);
    final Integer satz;
    Gebiet gebiet;
    Integer gebietsNr;

    SitzberechnungErgebnis firstAss = sbeHelper.getFirstAssignment(id_Liste);
    Liste liste = firstAss.getListe();
    Collection<GruppeGebietsspezifisch> gruppenGebietsspezifisch = liste
        .getGruppeGebietsspezifischCol();
    int size = gruppenGebietsspezifisch.size();

    if (size > 1) {
      // If it is a stel (set of identical lists), we need one line per district and one line for
      // the sum + one empty line
      satz = liste.getSatz();

      for (GruppeGebietsspezifisch gruppeGebietsspezifisch : gruppenGebietsspezifisch) {
        gebiet = gruppeGebietsspezifisch.getGebiet();
        gebietsNr = gebiet.getNummer();
        addAttributesForDistrict(line, gebiet);
        addSortNumber(line, satz, gebietsNr, false);
        int votes = isEK ? districtsAndVotes.getWeightedVotes(gebietsNr) : districtsAndVotes
            .getVotes(gebietsNr);
        line.appendChild(createRGElementWithValue(RG_VOTES, votes));
        parent.appendChild(line);
        // Start a new line
        line = createRGElement(RG_ASSIGNMENT_LIST_GROUPS_LINE);
      }

      // Start the "stel" line
      line.addAttribute(new Attribute(ATTR_SET, String.valueOf(satz)));
      addSortNumber(line, satz, null, false);
      gebiet = null;
      gebietsNr = null;
    } else {
      // Start the single "kieskring" line
      satz = null;
      gebiet = gruppenGebietsspezifisch.iterator().next().getGebiet();
      gebietsNr = gebiet.getNummer();
      addAttributesForDistrict(line, gebiet);
      addSortNumber(line, satz, gebietsNr, false);
    }

    // Add the information about the total P2-list either to the "stel" line or to the single
    // "kieskring" line
    int niemeyerSeats = sbeHelper.getNiemeyerSeats(id_Liste);
    line.appendChild(createRGElementWithValue(RG_VOTES, firstAss.getZaehler()));
    line.appendChild(createRGElementWithValue(RG_PRIOR_SEATS, firstAss.getSitze()));
    line.appendChild(createRGElementWithValue(RG_NEW_SEATS, niemeyerSeats));
    Element remainder = createRGElement(RG_REMAINDER);
    remainder.appendChild(createFractionRG(firstAss.getZaehlerVomRest(),
        firstAss.getNennerVomRest()));
    line.appendChild(remainder);
    parent.appendChild(line);

    // add an empty line after each "stel"
    if (size > 1) {
      Element emptyLine = createRGElement(RG_ASSIGNMENT_LIST_GROUPS_LINE);
      addSortNumber(emptyLine, satz, gebietsNr, true);
      parent.appendChild(emptyLine);
    }
  }

  private void addAttributesForDistrict(Element element, Gebiet gebiet) {
    element.addAttribute(new Attribute(ATTR_DISTRICT_NUMBER, String.valueOf(gebiet.getNummer())));
    if (isEK) {
      element.addAttribute(new Attribute(ATTR_PROVINCE_NAME, gebiet.getName()));
    }
  }

  private void addSortNumber(Element line, Integer satz, Integer gebiet, boolean emptyLine) {
    assert satz != null || gebiet != null;
    assert satz == null || gebiet == null || emptyLine == false;

    int sortNumber;
    if (satz == null) {
      sortNumber = 1000 + 2 * gebiet;
    } else {
      if (gebiet == null) {
        sortNumber = 100 * satz;
      } else {
        sortNumber = 100 * satz - 30 + gebiet;
      }
    }
    if (emptyLine) {
      sortNumber++;
    }
    line.addAttribute(new Attribute(ATTR_SORT_NUMBER, String.valueOf(sortNumber)));
  }

  private Element createLetterForElectedCandidates() {
    Element data = createRGElement(RG_CANDIDATE_LETTER);

    addElementWithRepositoryDateValue(data, RG_DATE_LETTER);
    data.appendChild(createElementWithRepositoryValue(RG_PLACE_LETTER, null));
    addElementWithRepositoryDateValue(data, RG_FEEDBACK_DATE);
    data.appendChild(createElementWithRepositoryValue(RG_CHAIRPERSON, null));
    data.appendChild(createElementWithRepositoryValue(RG_ACCEPTANCE_ADDRESS, RG_REJECTION_ADDRESS));
    data.appendChild(createElementWithRepositoryValue(RG_ACCEPTANCE_LOCATION, RG_REJECTION_LOCATION));
    data.appendChild(createElementWithRepositoryValue(RG_ACCEPTANCE_POSTALCODE,
        RG_REJECTION_POSTALCODE));
    data.appendChild(createElementWithRepositoryValue(RG_REJECTION_ADDRESS, null));
    data.appendChild(createElementWithRepositoryValue(RG_REJECTION_LOCATION, null));
    data.appendChild(createElementWithRepositoryValue(RG_REJECTION_POSTALCODE, null));
    return data;
  }

  private Element createElementWithRepositoryValue(String name, String alternativeName) {
    String value = bean.getPropertyHandling().getProperty(name);
    if (StringUtils.isEmpty(value) && !StringUtils.isEmpty(alternativeName)) {
      value = bean.getPropertyHandling().getProperty(alternativeName);
    }
    return createRGElementWithValue(name, value);
  }

  private void addElementWithRepositoryDateValue(Element parent, String name) {
    String value = bean.getPropertyHandling().getProperty(name);
    if (value != null) {
      parent.appendChild(createRGElementWithValue(name, XMLHelper.createDateString(value)));
    }
  }

  private List<Konflikt> getKonflikte(String id_Ergebniseingang, DecisionType type) {
    try {
      Collection<Konflikt> result = bean.getKonfliktHome()
          .findAllByErgebniseingangAndArt(id_Ergebniseingang, type.getId());
      return new ArrayList<Konflikt>(result);
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "Sitzberechnungsergebnisse"), e); //$NON-NLS-1$
    }
  }

  private Element createListOrCombinedList(SitzberechnungErgebnis sbe,
      boolean districtNumberForIndependentLists,
      Distribution distribution) {
    return createListOrCombinedList(sbe.getGruppe(),
        sbe.getListe(),
        districtNumberForIndependentLists,
        distribution);
  }

  private Element createListOrCombinedList(Restsitzverteilung rs) {
    Distribution distribution = Distribution.byId(rs.getVerteilung());
    if (Distribution.P42.equals(distribution)) {
      return createListOrCombinedList(rs.getGruppe(), null, true, distribution);
    } else if (Distribution.P3.equals(distribution) || Distribution.FICTITIOUS.equals(distribution)) {
      return createListOrCombinedList(rs.getGruppe(), null, true, distribution);
    } else if (Distribution.P2.equals(distribution)) {
      return createListOrCombinedList(null, rs.getListe(), true, distribution);
    } else {
      // Should never happen
      return createListOrCombinedList(rs.getGruppe(), rs.getListe(), true, distribution);
    }
  }

  private Element createListOrCombinedList(Alternative los, Distribution distribution) {
    return createListOrCombinedList(los.getGruppe(), los.getListe(), false, distribution);
  }

  private Element createListOrCombinedList(Gruppe gruppe,
      Liste liste,
      boolean districtNumberForIndependentLists,
      Distribution distribution) {
    Element id = createRGElement(RG_LIST_COMBINED_LIST);

    if (gruppe != null && !distribution.equals(Distribution.P2)) {
      // Display a P3-list (Party) with the number of the list
      String key = String.valueOf(gruppe.getSchluessel());
      id.addAttribute(new Attribute(ATTR_LIST_NUMBER, key));
      if (districtNumberForIndependentLists) {
        // Only for independent lists add the number of the electoral district
        Collection<Liste> listen = gruppe.getListeCol();
        if (listen.size() == 1) {
          Liste listeInGruppe = listen.iterator().next();
          Collection<GruppeGebietsspezifisch> gruppenGebietsspezifisch = listeInGruppe
              .getGruppeGebietsspezifischCol();
          if (gruppenGebietsspezifisch.size() == 1) {
            Gebiet gebiet = gruppenGebietsspezifisch.iterator().next().getGebiet();
            addAttributesForDistrict(id, gebiet);
          }
        }
      }
    } else if (liste != null) {
      // Display a P3-list (Party) with the number of the list
      String key = String.valueOf(liste.getGruppe().getSchluessel());
      id.addAttribute(new Attribute(ATTR_LIST_NUMBER, key));
      if (liste.getSatz() > 0) {
        String set = String.valueOf(liste.getSatz());
        id.addAttribute(new Attribute(ATTR_SET, set));
      }
      // If there is only one district, add the number of the electoral district
      Collection<GruppeGebietsspezifisch> gruppenGebietsspezifisch = liste
          .getGruppeGebietsspezifischCol();
      if (gruppenGebietsspezifisch.size() == 1) {
        Gebiet gebiet = gruppenGebietsspezifisch.iterator().next().getGebiet();
        addAttributesForDistrict(id, gebiet);
      }
    }
    return id;
  }

  private void appendCandidateAndResult(String id_Ergebniseingang,
      Listenkandidatur lk,
      Element parent,
      Map<Personendaten, Listenkandidatur> deadCandidates,
      Set<Listenkandidatur> priorityCandidates,
      List<Listenkandidatur> candidateElectedByPositionOnList,
      Map<Personendaten, Integer> candidatesAndTotalVotes) {
    try {
      Personendaten personendaten = lk.getPersonendaten();

      Element child = createRGElement(RG_CANDIDATE_RESULT);
      child.appendChild(bean.createCandidateElement(lk, false, false, true, EMLMessageType.EML520));

      VotesByRegionNumber votesByRegionNumber = votesHelper.getVotesForCandidateOnP2List(lk);
      appendVotesInElectoralDistrictParts(child, votesByRegionNumber);
      int totalVotes = isEK ? votesByRegionNumber.getTotalWeightedVotes() : votesByRegionNumber
          .getTotalVotes();
      Integer oldVotes = candidatesAndTotalVotes.get(personendaten);
      if (oldVotes == null) {
        candidatesAndTotalVotes.put(personendaten, totalVotes);
      } else {
        candidatesAndTotalVotes.put(personendaten, totalVotes + oldVotes);
      }
      child.appendChild(createRGElementWithValue(RG_TOTAL_VOTES, totalVotes));

      ListenkandidaturErgebnis lke = getListenkandidaturErgebnis(id_Ergebniseingang, lk);
      final boolean gewaehlt;
      final boolean electedInP3List;
      final boolean bevorzugtGewaehlt;
      final boolean isLosteilnehmer;
      final int newListPosition;
      if (lke == null) {
        gewaehlt = false;
        electedInP3List = false;
        bevorzugtGewaehlt = false;
        isLosteilnehmer = false;
        newListPosition = lk.getListenplatz();
      } else {
        gewaehlt = lke.isGewaehlt();
        electedInP3List = lke.isGewaehltInGruppe();
        bevorzugtGewaehlt = lke.isBevorzugtGewaehlt();
        isLosteilnehmer = lke.isLosteilnehmer();
        newListPosition = lke.getListenplatz();
      }
      if (gewaehlt && bevorzugtGewaehlt) {
        priorityCandidates.add(lk);
      }
      if (gewaehlt && !bevorzugtGewaehlt) {
        candidateElectedByPositionOnList.add(lk);
      }
      child.appendChild(createRGElementWithValue(RG_ABOVE_PREF_BARRIER,
          String.valueOf(bevorzugtGewaehlt)));
      Element elected = createRGElementWithValue(RG_ELECTED, String.valueOf(gewaehlt));
      if (isLosteilnehmer) {
        elected.addAttribute(new Attribute(ATTR_BY_LOT, "true")); //$NON-NLS-1$
      }
      child.appendChild(elected);
      Element electedOnListGroup = createRGElementWithValue(RG_ELECTED_ON_LIST_GROUP,
          String.valueOf(electedInP3List));
      child.appendChild(electedOnListGroup);
      if (!personendaten.isBenennbar()) {
        deadCandidates.put(personendaten, lk);
      }
      child.appendChild(createRGElementWithValue(RG_DECEASED,
          String.valueOf(!personendaten.isBenennbar())));
      child.appendChild(createRGElementWithValue(RG_NEW_LIST_POSITION,
          String.valueOf(newListPosition)));
      parent.appendChild(child);
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "Listenkandidatur"), e); //$NON-NLS-1$
    }
  }

  /**
   * Find the result of a candidate. May be <code>null</code> in conflict mode.
   */
  private ListenkandidaturErgebnis getListenkandidaturErgebnis(String id_Ergebniseingang,
      Listenkandidatur lk) throws FinderException {
    try {
      return bean
          .getListenkandidaturErgebnisHome()
          .findByErgebniseingangAndListenkandidatur(id_Ergebniseingang, lk.getID_Listenkandidatur());
    } catch (FinderException e) {
      if (inConflict) {
        return null;
      } else {
        throw e;
      }
    }
  }

  private void appendVotesInElectoralDistrictParts(Element parent,
      VotesByRegionNumber votesByRegionNumber) {
    int breakAfter = getBreakAfter(votesByRegionNumber.size());

    Element part = createRGElement(RG_VOTES_IN_REGION_PART);
    int count = 0;
    for (Integer regionNumber : votesByRegionNumber.getRegionNumbers()) {
      String gebiet = String.valueOf(regionNumber);
      // If there is only one district, only display the sum (except EK)
      if (isEK || votesByRegionNumber.size() > 1) {
        Integer votes = votesByRegionNumber.getVotes(regionNumber);
        Integer weightedVotes = votesByRegionNumber.getWeightedVotes(regionNumber);
        appendVotesInElectoralDistrict(part, votes, weightedVotes, gebiet);
      }
      count++;
      if (breakAfter == count) {
        parent.appendChild(part);
        part = createRGElement(RG_VOTES_IN_REGION_PART);
      }
    }
    Integer totalVotes = votesByRegionNumber.getTotalVotes();
    Integer totalWeightedVotes = votesByRegionNumber.getTotalWeightedVotes();
    if (!isEK || votesByRegionNumber.size() > 1) {
      // If there is only one district, do not display the sum for EK elections
      appendVotesInElectoralDistrict(part, totalVotes, totalWeightedVotes, ATTR_VALUE_ALLE);
    }
    parent.appendChild(part);
  }

  private int getBreakAfter(int size) {
    if (isEK) {
      if (size >= 5) {
        return 5;
      }
    } else {
      if (size >= 7) {
        return 7;
      }
    }
    return 0;
  }

  private void appendVotesInElectoralDistrict(Element parent,
      Integer stimmen,
      Integer weighted,
      String gebiet) {
    Element stimmergebnis = createRGElementWithValue(RG_VOTES_IN_REGION, stimmen == null
        ? "." : stimmen.toString()); //$NON-NLS-1$
    stimmergebnis.addAttribute(new Attribute(ATTR_ID, gebiet));
    if (weighted != null) {
      stimmergebnis.addAttribute(new Attribute(ATTR_WEIGHTED, String.valueOf(weighted)));
    }
    parent.appendChild(stimmergebnis);
  }

  private Element createOverviewOfElectedCandidates(String id_Ergebniseingang) {
    try {
      Element results = createRGElement(RG_ELECTED_CANDIDATES);
      List<ListenkandidaturErgebnis> electedCol = new ArrayList<ListenkandidaturErgebnis>();
      electedCol.addAll(bean.getListenkandidaturErgebnisHome()
          .findAllByErgebniseingangAndGewaehlt(id_Ergebniseingang));
      // Sort candidates by name
      Collections.sort(electedCol, new Comparator<ListenkandidaturErgebnis>() {
        public int compare(ListenkandidaturErgebnis lke1, ListenkandidaturErgebnis lke2) {
          Personendaten p1 = lke1.getListenkandidatur().getPersonendaten();
          Personendaten p2 = lke2.getListenkandidatur().getPersonendaten();
          return NL_COLLATOR.compare(p1.getNachname() + ", " + p1.getVorname(), p2.getNachname() //$NON-NLS-1$
              + ", " + p2.getVorname()); //$NON-NLS-1$
        }
      });
      for (ListenkandidaturErgebnis lke : electedCol) {
        Element electedCandidate = createRGElement(RG_ELECTED_CANDIDATE);
        Liste liste = lke.getListenkandidatur().getListe();

        Element listData = createListData(liste);
        electedCandidate.appendChild(bean.createListIdentifierElement(liste.getGruppe()));
        electedCandidate.appendChild(listData);

        Element candidateElement = bean.createCandidateElement(lke.getListenkandidatur(),
            false,
            false,
            true,
            EMLMessageType.EML520);
        Personendaten personendatenAgent = lke.getListenkandidatur().getPersonendaten()
            .getPersonendatenAgent();
        if (personendatenAgent != null) {
          Element agentElement = createAgent(personendatenAgent);
          candidateElement.appendChild(agentElement);
        }

        electedCandidate.appendChild(candidateElement);
        results.appendChild(electedCandidate);
      }
      return results;
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "Kandidatenergebnisse"), e); //$NON-NLS-1$
    }
  }

  private Element createAgent(Personendaten personendatenAgent) {
    Element agent = XMLHelper.createElement(XMLTags.KANDIDAT_AGENT, XMLTags.NS_EML);

    // Create name
    Element agentIdentifier = XMLHelper.createElement(XMLTags.AGENT_IDENTIFIER, XMLTags.NS_EML);
    agent.appendChild(agentIdentifier);
    Element agentName = XMLHelper.createElement(XMLTags.AGENT_NAME, XMLTags.NS_EML);
    agentIdentifier.appendChild(agentName);
    agentName.appendChild(bean.createPersonNameElement(personendatenAgent));

    // create correspondence address
    bean.appendContactAddress(personendatenAgent, agent, true);

    // create living address
    String wohnort = personendatenAgent.getWohnort();
    if (!StringUtils.isEmpty(wohnort)) {
      Element livingAddress = XMLHelper.createElement(XMLTags.AGENT_LIVING_ADRESSE, XMLTags.NS_KR);
      agent.appendChild(livingAddress);
      livingAddress.appendChild(XMLHelper.createElementWithValue(XMLTags.ADRESSE_WOHNORT,
          XMLTags.NS_KR,
          wohnort));
      String land = personendatenAgent.getLand();
      if (!StringUtils.isEmpty(land)) {
        livingAddress.appendChild(XMLHelper.createElementWithValue(XMLTags.ADRESSE_LAND_ID,
            XMLTags.NS_KR,
            land));
      }
    }

    return agent;
  }

  // *********** Some special Comparators ************

  /**
   * Sort assignment lines: first combination results ordered by id, followed by its P3-lists
   * ordered by key
   * 
   * @author ugo@ivu.de, IVU Traffic Technologies AG
   */
  private class AssignmentComparator implements Comparator<SitzberechnungErgebnis> {
    public int compare(final SitzberechnungErgebnis sbe1, final SitzberechnungErgebnis sbe2) {
      if (sbe1.getSchrittnummer() != sbe2.getSchrittnummer()) {
        return Integer.signum(sbe1.getSchrittnummer() - sbe2.getSchrittnummer());
      }
      if (sbe1.getID_Listenkombination() != null && sbe2.getID_Listenkombination() != null) {
        return sbe1.getID_Listenkombination().compareTo(sbe2.getID_Listenkombination());
      }
      if (sbe1.getID_Listenkombination() != null) {
        return -1;
      }
      if (sbe2.getID_Listenkombination() != null) {
        return 1;
      }
      return Integer.signum(sbe1.getGruppe().getSchluessel() - sbe2.getGruppe().getSchluessel());
    }
  }

  /**
   * Sort assignment lines: first combination results ordered by id, followed by its P3-lists
   * ordered by key
   * 
   * @author ugo@ivu.de, IVU Traffic Technologies AG
   */
  private class RestsitzverteilungComparator implements Comparator<Restsitzverteilung> {
    public int compare(final Restsitzverteilung sbe1, final Restsitzverteilung sbe2) {
      if (sbe1.getID_Listenkombination() != null && sbe2.getID_Listenkombination() != null) {
        return sbe1.getID_Listenkombination().compareTo(sbe2.getID_Listenkombination());
      }
      if (sbe1.getID_Listenkombination() != null) {
        return -1;
      }
      if (sbe2.getID_Listenkombination() != null) {
        return 1;
      }
      return Integer.signum(sbe1.getGruppe().getSchluessel() - sbe2.getGruppe().getSchluessel());
    }
  }

  private static class P42List {
    private final String id_Listenkombination;
    private final String id_Gruppe;

    @SuppressWarnings("hiding")
    private P42List(String id_Listenkombination, String id_Gruppe) {
      this.id_Listenkombination = id_Listenkombination;
      this.id_Gruppe = id_Gruppe;
    }

    private P42List(SitzberechnungErgebnis as) {
      this(as.getID_Listenkombination(), as.getID_Gruppe());
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id_Gruppe == null) ? 0 : id_Gruppe.hashCode());
      result = prime * result
          + ((id_Listenkombination == null) ? 0 : id_Listenkombination.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      P42List other = (P42List) obj;
      if (id_Gruppe == null) {
        if (other.id_Gruppe != null) {
          return false;
        }
      } else if (!id_Gruppe.equals(other.id_Gruppe)) {
        return false;
      }
      if (id_Listenkombination == null) {
        if (other.id_Listenkombination != null) {
          return false;
        }
      } else if (!id_Listenkombination.equals(other.id_Listenkombination)) {
        return false;
      }
      return true;
    }
  }
}
