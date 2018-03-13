/*
 * EML510Helper
 * 
 * Created on 05.02.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import static de.ivu.wahl.dataimport.XMLTags.NS_EML;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import org.apache.log4j.Logger;

import nu.xom.Element;

import de.ivu.wahl.Konstanten;
import de.ivu.wahl.SystemInfo;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.dataimport.GruppeAllgemeinXmlAdapter;
import de.ivu.wahl.dataimport.XMLTags;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.Gesamtstimmen;
import de.ivu.wahl.modell.GesamtstimmenImpl;
import de.ivu.wahl.modell.GruppeKonstanten;
import de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein;
import de.ivu.wahl.modell.ejb.Ergebniseingang;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.Liste;
import de.ivu.wahl.modell.ejb.Listenkandidatur;
import de.ivu.wahl.modell.ejb.Stimmergebnis;
import de.ivu.wahl.modell.ejb.Wahl;
import de.ivu.wahl.modell.etc.GeneralVotingResults;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;
import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;
import de.ivu.wahl.wus.reportgen.EMLMessageType;

/**
 * Helper of ExportHandlingBean to create the core part of the EML 510 message.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class EML510Helper {
  private static final Logger LOGGER = Logger.getLogger(ExportHandlingBean.class);

  private final ExportHandlingBean bean;

  @SuppressWarnings("hiding")
  EML510Helper(ExportHandlingBean bean) {
    this.bean = bean;
  }

  Element createVotingResultElement(Gebiet region,
      boolean create510d4PSB,
      boolean emptyResults,
      EMLMessageType emlType) throws FinderException {
    ElectionSubcategory electionSubcategory = WahlInfo.getWahlInfo().getElectionSubcategory();

    final Wahl wahl = region.getWahl();
    Element contests = XMLHelper.createElement(XMLTags.EML_CONTESTS, NS_EML);
    Element contest = XMLHelper.createElement(XMLTags.EML_CONTEST, NS_EML);
    boolean eml510InElectionWithMultipleDistrictsOrAB = electionSubcategory
        .isElectionWithMultipleDistricts()
        || isWaterschapElection(wahl)
        || ElectionSubcategory.NR.equals(electionSubcategory);
    Element contestIdentifier = bean.createContestIdentifier(region,
        eml510InElectionWithMultipleDistrictsOrAB);
    contest.appendChild(contestIdentifier);

    // for root region find total results first, than subregion votes
    boolean isTotalResult = region.getUebergeordnetesGebiet() == null;
    int targetLevel = SystemInfo.getSystemInfo().getWahlEbene() + 1;

    // Create <TotalVotes> and determine the regions for which a <ReportingUnitVotes> shall be
    // created
    List<Gebiet> subRegions = createTotalVotesAndGetSubRegions(region,
        create510d4PSB,
        emptyResults,
        contest,
        emlType);

    for (Gebiet subRegion : subRegions) {
      createReportingUnitVotes(create510d4PSB,
          emptyResults,
          contest,
          isTotalResult,
          targetLevel,
          wahl,
          subRegion,
          emlType);
    }
    contests.appendChild(contest);
    return contests;
  }

  private boolean isWaterschapElection(Wahl wahl) {
    return ElectionCategory.AB.equals(ElectionCategory.fromWahlart(wahl.getWahlart()));
  }

  private void createReportingUnitVotes(boolean create510d4PSB,
      boolean emptyResults,
      Element contest,
      boolean isTotalResult,
      int targetLevel,
      final Wahl wahl,
      Gebiet subRegion,
      EMLMessageType emlType) throws FinderException {

    String id_Ergebniseingang = null;
    if (!emptyResults) {
      Ergebniseingang letzterGueltigerEingang = subRegion.getLetzterGueltigerEingang(wahl
          .getAktuelleWahlergebnisart());
      if (letzterGueltigerEingang == null) {
        return;
      } else {
        id_Ergebniseingang = letzterGueltigerEingang.getID_Ergebniseingang();
      }
    }

    Element regionResults = XMLHelper.createElement(XMLTags.EML_REPORTING_UNIT_VOTES, NS_EML);
    regionResults.appendChild(bean.createReportingUnitIdentifier(subRegion, isTotalResult
        && !create510d4PSB, targetLevel));
    appendCandidateResults(id_Ergebniseingang, subRegion, regionResults, emptyResults, emlType);
    contest.appendChild(regionResults);
  }

  /**
   * Creates the <TotalVotes> element for EML 510.
   * 
   * @param emlType
   * @return list of regions for which a <ReportingUnitVotes> element has to be created
   */
  private List<Gebiet> createTotalVotesAndGetSubRegions(Gebiet region,
      boolean create510d4PSB,
      boolean emptyResults,
      Element contest,
      EMLMessageType emlType) throws FinderException {
    if (region.getGebietsart() == GebietModel.GEBIETSART_STIMMBEZIRK) {
      // For 510a do not create <TotalVotes> Element
      return Collections.singletonList(region);
    }

    // create <TotalVotes> Element
    contest.appendChild(createTotalResult(region, emptyResults, emlType));

    if (create510d4PSB) {
      // Special case where 510d is created by the PSB (the municipality)
      return Collections.singletonList(region);
    }

    if (Konstanten.EXPORT_SUB_RESULTS
        || SystemInfo.getSystemInfo().getWahlEbene() == GebietModel.EBENE_CSB) {
      // export subregions for CSB or if explicitly configured
      return new ArrayList<Gebiet>(bean.getGebietHome()
          .findAllByUebergeordnetesGebietSortByPosition(region.getID_Gebiet()));
    }

    return Collections.emptyList();
  }

  /**
   * Create <TotalVotes> element for EML 510
   * 
   * @param region root region
   * @param emptyResults
   * @param emlType
   * @return the xml element
   * @throws FinderException
   */
  private Element createTotalResult(Gebiet region, boolean emptyResults, EMLMessageType emlType)
      throws FinderException {
    Element totalVotes = XMLHelper.createElement(XMLTags.EML_TOTAL_VOTES, NS_EML);
    if (emptyResults) {
      if (bean.useShortCode(emlType)
          && WahlInfo.getWahlInfo().getGebietsartWurzelgebiet() == region.getGebietsart()) {
        appendTotalVotes(totalVotes, emptyResults);
      } else {
        appendCandidateResults(null, region, totalVotes, emptyResults, emlType);
      }
    } else {
      if (region.getLetzterGueltigerEingang() == null) {
        throw new RuntimeException("No result found for " + region.getName()); //$NON-NLS-1$
      }
      String id_Ergebniseingang = region.getLetzterGueltigerEingang().getID_Ergebniseingang();
      // if there are no lists at this region use Gesamtstimmen
      if (bean.useShortCode(emlType)
          && WahlInfo.getWahlInfo().getGebietsartWurzelgebiet() == region.getGebietsart()) {
        appendTotalVotes(totalVotes, emptyResults);
      } else {
        appendCandidateResults(id_Ergebniseingang, region, totalVotes, emptyResults, emlType);
      }
    }
    return totalVotes;
  }

  private void appendTotalVotes(Element totalVotes, boolean emptyResults) {
    Gesamtstimmen gesamtstimmen = bean.getVotesHandling().getGesamtstimmen(false);
    Collection<Gruppe> gruppen;
    try {
      if (emptyResults) {
        gruppen = bean.getGruppeHome().findAllByGruppenart(GruppeKonstanten.GRUPPENART_PARTEI);
        for (Gruppe gruppe : gruppen) {
          Element groupResult = XMLHelper.createElement(XMLTags.EML_SELECTION, NS_EML);
          groupResult.appendChild(bean.createListIdentifierElement(gruppe));
          groupResult.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_VALID_VOTES,
              NS_EML,
              0));
          totalVotes.appendChild(groupResult);
          // candidate votes
          Collection<Listenkandidatur> candidates = bean.getListenkandidaturHome()
              .findDistinctPersonendatenByGruppe(gruppe.getID_Gruppe());
          for (Listenkandidatur lk : candidates) {
            Element selection = XMLHelper.createElement(XMLTags.EML_SELECTION, NS_EML);
            Element candidate = XMLHelper.createElement(XMLTags.EML_CANDIDATE, NS_EML);
            candidate.appendChild(XMLHelper
                .createElementWithAttribute(XMLTags.EML_CANDIDATE_IDENTIFIER,
                    NS_EML,
                    XMLTags.ATTR_SHORTCODE,
                    lk.getID_Personendaten()));
            selection.appendChild(candidate);
            selection.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_VALID_VOTES,
                NS_EML,
                0));
            totalVotes.appendChild(selection);
          }
        }
        // General results
        GruppeAllgemeinXmlAdapter adapter = new GruppeAllgemeinXmlAdapter();
        Iterable<GruppeAllgemein> gruppenAllgemein = adapter.getGruppenAllgemein();
        for (GruppeAllgemein gruppeAllgemein : gruppenAllgemein) {
          adapter.putEmlXml(totalVotes, gruppeAllgemein, 0);
        }
      } else {
        gruppen = bean.getGruppeHome().findAllByGruppenart(GruppeKonstanten.GRUPPENART_PARTEI);
        for (Gruppe gruppe : gruppen) {
          int votes = gesamtstimmen.getGruppenstimmen(gruppe.getSchluessel());
          Element groupResult = XMLHelper.createElement(XMLTags.EML_SELECTION, NS_EML);
          groupResult.appendChild(bean.createListIdentifierElement(gruppe));
          groupResult.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_VALID_VOTES,
              NS_EML,
              votes));
          totalVotes.appendChild(groupResult);
          // candidate votes
          for (GesamtstimmenImpl.Kandidatenergebnis erg : gesamtstimmen
              .getKandidatenergebnisse(gruppe.getSchluessel())) {
            Element selection = XMLHelper.createElement(XMLTags.EML_SELECTION, NS_EML);
            Element candidate = XMLHelper.createElement(XMLTags.EML_CANDIDATE, NS_EML);
            candidate.appendChild(XMLHelper
                .createElementWithAttribute(XMLTags.EML_CANDIDATE_IDENTIFIER,
                    NS_EML,
                    XMLTags.ATTR_SHORTCODE,
                    erg.getShortCode()));
            selection.appendChild(candidate);
            selection.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_VALID_VOTES,
                NS_EML,
                erg.getStimmen()));
            totalVotes.appendChild(selection);
          }
        }
        // General results
        GruppeAllgemeinXmlAdapter adapter = new GruppeAllgemeinXmlAdapter();
        Iterable<GruppeAllgemein> gruppenAllgemein = adapter.getGruppenAllgemein();
        for (GruppeAllgemein gruppeAllgemein : gruppenAllgemein) {
          int value = gesamtstimmen.getGruppenstimmen(gruppeAllgemein.schluessel);
          adapter.putEmlXml(totalVotes, gruppeAllgemein, value);
        }
      }
    } catch (FinderException e) {
      // TODO: message
      throw new EJBException(e);
    }
  }

  /**
   * @param emptyResults true if all results should be zero
   */
  private void appendCandidateResults(String id_Ergebniseingang,
      Gebiet region,
      Element result,
      boolean emptyResults,
      EMLMessageType emlType) throws FinderException {
    Long start = new Date().getTime();

    String id_Gebiet = region.getID_Gebiet();
    // Find lists for that region
    Collection<Liste> listCol;
    if (region.getGebietsart() < WahlInfo.getWahlInfo().getGebietsartMitListen()) {
      // We are at root region without lists and have to use all lists in that case
      listCol = bean.getListeHome().findAll();
    } else {
      listCol = bean.getListeHome().findAllByGebiet(id_Gebiet);
    }
    List<Liste> lists = new ArrayList<Liste>(listCol);
    // Sort lists by Affiliation Id
    Collections.sort(lists, new Comparator<Liste>() {
      @Override
      public int compare(Liste x, Liste y) {
        return Integer.signum(x.getGruppe().getSchluessel() - y.getGruppe().getSchluessel());
      }
    });
    for (Liste list : lists) {
      appendCandidateVotingResults(result,
          list,
          id_Gebiet,
          id_Ergebniseingang,
          emptyResults,
          emlType);
    }
    appendGeneralVotingResults(result, region, id_Ergebniseingang, emptyResults);
    System.out.print("appendCandidateResults in ms: " + (new Date().getTime() - start)); //$NON-NLS-1$
  }

  // The restricted complex data type ReportingUnitIdentifierStructure510
  // uses the type ReportingUnitIdentifierStructure as its base type, makes
  // the Id attribute mandatory, and restricts its type to a hierarchical pattern. The
  // hierarchy starts one level below the level to which the whole EML 510 file belongs.
  // For 510d, the highest reporting unit is a HSB. For 510c, the highest reporting unit is
  // the municipality. For 510b, and 510a, the reporting unit is the polling unit. A HSB is
  // denoted by the sequence HSB followed by its number, a municipality just by a four
  // digit number, a polling unit by the sequence SB followed by its number. Reporting
  // units that are not the highest ones in the given EML 510 file are prepended by the
  // codes of the next higher unit up to the higest one. The delimiter is always a double
  // colon (::).

  /**
   * Adding list results with candidate's voting results.
   * 
   * @param regionVotes
   * @param liste
   * @param id_Ergebniseingang
   * @param emptyResults true if all results should be zero
   * @param emlType
   * @throws FinderException
   */
  private void appendCandidateVotingResults(Element regionVotes,
      Liste liste,
      String id_Gebiet,
      String id_Ergebniseingang,
      boolean emptyResults,
      EMLMessageType emlType) throws EJBException {
    try {
      if (emptyResults) {
        appendEmptyCandidateVotingResults(regionVotes, liste, emlType);
      } else {
        // get and sort candidate results
        List<Stimmergebnis> candidateResults = bean.getVotesHandling()
            .getSortedCandidateResults(liste, id_Gebiet, id_Ergebniseingang);
        // create element with total votes of the list
        int votes = bean.getVotesHandling().getVotesForListInRegion(liste,
            id_Gebiet,
            id_Ergebniseingang);
        Element affiliationResults = XMLHelper.createElement(XMLTags.EML_SELECTION, NS_EML);
        affiliationResults.appendChild(bean.createListIdentifierElement(liste));
        affiliationResults.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_VALID_VOTES,
            NS_EML,
            votes));
        regionVotes.appendChild(affiliationResults);

        // create elements with votes of the candidates
        for (Stimmergebnis ste : candidateResults) {
          Element candidateResult = XMLHelper.createElement(XMLTags.EML_SELECTION, NS_EML);
          candidateResult.appendChild(bean.createCandidateElement(ste.getListenkandidatur(),
              true,
              false,
              false,
              emlType));
          candidateResult.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_VALID_VOTES,
              NS_EML,
              ste.getStimmen()));
          regionVotes.appendChild(candidateResult);
        }
      }
    } catch (FinderException e) {
      LOGGER.error(Messages.getString(MessageKeys.Error_ErrorRetrievingCandidateResults), e);
      throw new EJBException(Messages.getString(MessageKeys.Error_ErrorRetrievingCandidateResults),
          e);
    }
  }

  private void appendEmptyCandidateVotingResults(Element regionVotes,
      Liste liste,
      EMLMessageType emlType) throws FinderException {
    // create element with total votes of the list
    Element affiliationResults = XMLHelper.createElement(XMLTags.EML_SELECTION, NS_EML);
    affiliationResults.appendChild(bean.createListIdentifierElement(liste));
    affiliationResults.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_VALID_VOTES,
        NS_EML,
        0));
    regionVotes.appendChild(affiliationResults);

    // create elements with votes of the candidates
    Collection<Listenkandidatur> candidates = bean.getListenkandidaturHome()
        .findAllByListeOrderByListenplatz(liste.getID_Liste());
    for (Listenkandidatur candidate : candidates) {
      Element candidateResult = XMLHelper.createElement(XMLTags.EML_SELECTION, NS_EML);
      candidateResult.appendChild(bean.createCandidateElement(candidate,
          true,
          false,
          false,
          emlType));
      candidateResult.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_VALID_VOTES,
          NS_EML,
          0));
      regionVotes.appendChild(candidateResult);
    }
  }

  // <Cast>13011</Cast>
  // <TotalCounted>6480</TotalCounted>
  // <RejectedVotes ReasonCode="blanco">0</RejectedVotes>
  // <RejectedVotes ReasonCode="ongeldig">13</RejectedVotes>

  private void appendGeneralVotingResults(Element result,
      Gebiet region,
      String id_Ergebniseingang,
      boolean emptyResults) {
    GruppeAllgemeinXmlAdapter adapter = new GruppeAllgemeinXmlAdapter();
    List<GruppeAllgemein> gruppenAllgemein = GruppeKonstanten.GruppeAllgemein
        .filterGruppenAllgemeinVisibleInRegionOrGueltige(region, adapter.getGruppenAllgemein());

    if (emptyResults) {
      for (GruppeAllgemein gruppeAllgemein : gruppenAllgemein) {
        adapter.putEmlXml(result, gruppeAllgemein, 0);
      }
    } else {
      GeneralVotingResults generalVotingResults = bean.getVotesHandling()
          .getGeneralVotingResults(id_Ergebniseingang, region.getID_Gebiet());
      for (GruppeAllgemein gruppeAllgemein : gruppenAllgemein) {
        int value = generalVotingResults.getVotes(gruppeAllgemein);
        adapter.putEmlXml(result, gruppeAllgemein, value);
      }
    }
  }

}
