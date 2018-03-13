/*
 * ImportClientDb
 * 
 * Created on 21.01.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import static de.ivu.wahl.dataimport.XMLTags.NS_EML;

import java.net.URL;
import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Category;

import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Nodes;

import de.ivu.util.debug.Log4J;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.Gesamtstimmen;
import de.ivu.wahl.modell.GesamtstimmenImpl;
import de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein;
import de.ivu.wahl.modell.WahlModel;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.util.XMLImportHelper;

/**
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public class ImportClientErgebnis {
  private static final Category LOGGER = Log4J.configure(ImportClientErgebnis.class);

  private final GesamtstimmenImpl _gesamtstimmen = new GesamtstimmenImpl();
  private final ErgebnisImportHandler _importHandler;

  /**
   * Constructor
   */
  public ImportClientErgebnis(ErgebnisImportHandler importHandler) {
    this._importHandler = importHandler;
  }

  /**
   * Import results from EML message 510d
   * 
   * @param url510 URL for message 510 as provided by user
   * @param importRegionCategory
   * @throws ImportException
   */
  public void importVotingResults(URL url510) throws ImportException {
    Element emlNode = ImportUtil.readXMLRoot(url510, XMLTags.EML);
    Element contestNode = emlNode.getFirstChildElement(XMLTags.EML_COUNT, NS_EML)
        .getFirstChildElement(XMLTags.EML_ELECTION, NS_EML)
        .getFirstChildElement(XMLTags.EML_CONTESTS, NS_EML)
        .getFirstChildElement(XMLTags.EML_CONTEST, NS_EML);
    Elements gebietsergebnisse = contestNode.getChildElements(XMLTags.EML_REPORTING_UNIT_VOTES,
        NS_EML);
    int rootRegionLevel = _importHandler.getImportRegion().getGebietsart();
    boolean readRootLevel = false;

    // Iterate over regions
    Collection<GebietModel> gebieteEingegangen = new HashSet<GebietModel>();
    for (int idx = 0; idx < gebietsergebnisse.size(); ++idx) {
      Element reportingUnitVotes = gebietsergebnisse.get(idx);
      // Initialize gebiet and isRootRegion
      final GebietModel region = getGebietModel(reportingUnitVotes);
      if (region != null) {
        if (rootRegionLevel == region.getGebietsart()) {
          readRootLevel = true;
        }
        readRegionResults(reportingUnitVotes, region);
        if (gebieteEingegangen.contains(region)) {
          throw new ImportException(
              Messages.bind(MessageKeys.Error_GebietMitId_0_HatMehrereErgebnisse,
                  region.getNummer()));
        }
        gebieteEingegangen.add(region);
      }
    }

    // TODO: ugo: implement consistency check working for all elections
    if (true || _importHandler.getImportRegion().getGebietsart() >= _importHandler
        .getRegionCategoryWithLists()) {
      Element totalVotes = getTotalVotesElement(emlNode);
      // read total votes only if they belong to another region
      if (totalVotes != null && !readRootLevel) {
        readRegionResults(totalVotes, _importHandler.getImportRegion());
        if (_importHandler.checkConsistency()) {
          checkGesamtstimmenAllgemein(totalVotes, _gesamtstimmen);
        }
        // Check data consistency
        readAndCheckTotalVotes(totalVotes, _gesamtstimmen);
      }
    }
    _importHandler.finishErgebnisimport(gebieteEingegangen);
  }

  /**
   * Read single voting results from region result node <TotalVotes> or <ReportingUntitVotes>
   * 
   * @param regionVotes xml element <TotalVotes> or <ReportingUntitVotes> resp.
   * @param id_Gebiet id of region
   * @param id_Ergebniseingang id for current import action
   * @param gebietsart region category
   * @return region the result belongs to
   * @throws ImportException TODO: Check if all lists and candidates got a voting result!!!!!
   */
  @SuppressWarnings("synthetic-access")
  private GebietModel readRegionResults(Element regionVotes, GebietModel gebiet)
      throws ImportException {
    boolean hasCandidates = gebiet.getGebietsart() >= _importHandler.getRegionCategoryWithLists();
    Elements ergebnisse = regionVotes.getChildElements(XMLTags.EML_SELECTION, NS_EML);
    LOGGER.info(Messages.bind(MessageKeys.Result_Tracelog_Fand_0_SchluesselFuerGebiet,
        ergebnisse.size(),
        gebiet.getNummer()));

    Gruppe gruppe = new Gruppe();
    // total votes for one group in one region

    int gesamtstimmenGebiet = 0;
    int anzKandidaten = 0;
    // Searching for list and candidate
    for (int i = 0; i < ergebnisse.size(); ++i) {
      Element selectionNode = ergebnisse.get(i);
      // Check, if new list starts
      if (selectionNode.getFirstChildElement(XMLTags.EML_LISTEN_IDENTIFIER, NS_EML) != null) {
        checkFinishedGroup(gebiet, !hasCandidates, gruppe, anzKandidaten);

        gruppe = startNewGroup(selectionNode);
        anzKandidaten = 0;
        gesamtstimmenGebiet += gruppe.getGesamtstimmenGruppe();

        // Save total votes for the party
        _importHandler.addStimmen(gebiet,
            gruppe.getSchluessel(),
            null,
            gruppe.getGesamtstimmenGruppe());
      } else if (hasCandidates) {
        anzKandidaten++;
        internalReadRegionResults(gebiet, gruppe, selectionNode);
      }
    }

    // Read general results
    _importHandler.readStimmergebnisseAllgemein(regionVotes,
        gebiet,
        _gesamtstimmen,
        gesamtstimmenGebiet);
    return gebiet;
  }

  /**
   * Read a single &lt;Selection&gt; element
   */
  private void internalReadRegionResults(final GebietModel gebiet,
      Gruppe gruppe,
      Element selectionNode) throws ImportException {
    // Each candidate either is identified by his ShortCode or by his list position (within the
    // Affiliation).
    String shortCode = selectionNode.getFirstChildElement(XMLTags.EML_CANDIDATE, NS_EML)
        .getFirstChildElement(XMLTags.EML_CANDIDATE_IDENTIFIER, NS_EML)
        .getAttributeValue(XMLTags.ATTR_SHORTCODE);
    int listenplatz = -1;
    if (shortCode == null) {
      listenplatz = AbstractImportClient.getListenplatz(selectionNode
          .getFirstChildElement(XMLTags.EML_CANDIDATE, NS_EML));
    }

    String id_Listenkandidatur = _importHandler.getID_Listenkandidatur(gebiet,
        gruppe.getSchluessel(),
        shortCode,
        listenplatz);
    // ShortCode resp. list position
    String kandidatIdent = shortCode == null ? String.valueOf(listenplatz) : shortCode;
    if (id_Listenkandidatur == null) {
      throw new ImportException(
          Messages.bind(MessageKeys.Error_Kandidat_0_FuerGruppe_1_in_2_nichtGefunden,
              kandidatIdent,
              gruppe.getSchluessel(),
              gebiet.getBezeichnung()));
    }
    int stimmen = XMLImportHelper.getIntValue(selectionNode
        .getFirstChildElement(XMLTags.EML_VALID_VOTES, NS_EML));
    gruppe.subtract(stimmen);
    _gesamtstimmen.addKandidatenstimmen(gruppe.getSchluessel(),
        listenplatz,
        shortCode,
        stimmen,
        gebiet.getNummer());
    _importHandler.addStimmen(gebiet, gruppe.getSchluessel(), id_Listenkandidatur, stimmen);
  }

  private void checkFinishedGroup(final GebietModel gebiet,
      final boolean isRootRegion,
      Gruppe gruppe,
      int anzKandidaten) throws ImportException {
    // Check total votes for last party first
    if (!isRootRegion && gruppe.getSchluessel() != -1 && gruppe.getGesamtstimmenGruppe() != 0) {
      throw new ImportException(
          Messages.bind(MessageKeys.Error_GesamtstimmenFuerPartei_0_InGebiet_1_FalschAbweichung_2,
              gruppe.getSchluessel(),
              gebiet.getName(),
              gruppe.getGesamtstimmenGruppe()));
    }
    if (gruppe.getSchluessel() != -1 && !isRootRegion) {
      // Check number of candidate votes
      int anzKandidatenErwartet = _importHandler.getNumberOfCandidates(gebiet,
          gruppe.getSchluessel());
      if (anzKandidaten < anzKandidatenErwartet) {
        throw new ImportException(
            Messages.bind(MessageKeys.Error_Nur_0_von_1_Kandidatenergebnissen_fuer_2_in_3_Gefunden,
                anzKandidaten,
                anzKandidatenErwartet,
                gruppe.getSchluessel(),
                gebiet.getGebietsartKlartext() + " " + gebiet.getName())); //$NON-NLS-1$
      }
    }
  }

  /**
   * Read total Votes from EML 510 and compare with gesamtstimmen object
   * 
   * @param ergebnisse <EML> element
   * @param gesamtstimmen Gesamtstimmen object with region results
   * @throws ImportException
   */
  @SuppressWarnings("synthetic-access")
  private void readAndCheckTotalVotes(Element totalVotes, Gesamtstimmen gesamtstimmen)
      throws ImportException {
    Elements ergebnisse = totalVotes.getChildElements(XMLTags.EML_SELECTION, NS_EML);

    // Searching for list and candidate
    Gruppe gruppe = new Gruppe();
    for (int i = 0; i < ergebnisse.size(); ++i) {
      Element ergebnis = ergebnisse.get(i);

      // Check, if new list starts
      if (ergebnis.getFirstChildElement(XMLTags.EML_LISTEN_IDENTIFIER, NS_EML) != null) {
        // Check total votes for last party first
        if (_importHandler.checkConsistency() && gruppe.getSchluessel() != -1
            && gruppe.getGesamtstimmenGruppe() != 0) {
          throw new ImportException(
              Messages
                  .bind(MessageKeys.Error_GesamtstimmenFuerPartei_0_ImWurzelgebietFalschAbweichung_1,
                      gruppe.getSchluessel(),
                      gruppe.getGesamtstimmenGruppe()));
        }

        gruppe = startNewGroup(ergebnis);
      } else if (_importHandler.checkConsistency()) {
        internCheckGesamtstimmen(ergebnis, gruppe, gesamtstimmen);
      }
    }
  }

  /**
   * @return the unique TotalVotes element
   * @throws ImportException if there are more or less than exactly one TotalVotes element.
   */
  private Element getTotalVotesElement(Element ergebnisKnoten) throws ImportException {
    // Check EML node
    Nodes totalVotesNodes = ergebnisKnoten.query("//eml:" + XMLTags.EML_TOTAL_VOTES, //$NON-NLS-1$
        XMLTags.CONTEXT_EML);
    if (totalVotesNodes.size() == 1) {
      return (Element) totalVotesNodes.get(0);
    }
    return null;
  }

  private void internCheckGesamtstimmen(Element ergebnis, Gruppe gruppe, Gesamtstimmen gesamtstimmen)
      throws ImportException {
    // Candidate votes, determine identifier by ShortCode or list position respectively
    String kandidatIdent = null;
    String shortCode = ergebnis.getFirstChildElement(XMLTags.EML_CANDIDATE, NS_EML)
        .getFirstChildElement(XMLTags.EML_CANDIDATE_IDENTIFIER, NS_EML)
        .getAttributeValue(XMLTags.ATTR_SHORTCODE);
    int listenplatz = -1;
    if (shortCode == null) {
      // use list position as identifier
      listenplatz = AbstractImportClient.getListenplatz(ergebnis
          .getFirstChildElement(XMLTags.EML_CANDIDATE, NS_EML));
      kandidatIdent = String.valueOf(listenplatz);
    } else {
      kandidatIdent = shortCode;
    }

    int stimmen = XMLImportHelper.getIntValue(ergebnis
        .getFirstChildElement(XMLTags.EML_VALID_VOTES, NS_EML));
    if (_importHandler.checkConsistency()) {
      gruppe.subtract(stimmen);
      int stimmenVgl = gesamtstimmen.getStimmen(gruppe.getSchluessel(), listenplatz, shortCode);
      if (stimmenVgl == -2) {
        throw new ImportException(
            Messages.bind(MessageKeys.Error_EsWurdenKeineRegionalstimmenFuerGruppe_0_Gefunden,
                gruppe.getSchluessel()));
      }
      if (stimmenVgl == -1) {
        throw new ImportException(
            Messages.bind(MessageKeys.Error_EsWurdenKeineRegionalstimmenFuerKandidat_0_Gefunden,
                kandidatIdent));
      }
      if (stimmenVgl != stimmen) {
        // TODO [Prio 2] ugo 14.08.2009 Diese Pr�fung korrigieren und ggf. wieder eine Exception
        // werfen.
        throw new ImportException(
            Messages
                .bind(MessageKeys.Error_SummeDerRegionalstimmenFuerKandidat_0_1_Gesamtstimmen_2,
                    kandidatIdent,
                    stimmenVgl,
                    stimmen));
      }
    }

    if (_importHandler.saveCandidateVotes(_importHandler.getImportRegion().getGebietsart())) {

      String id_Listenkandidatur = _importHandler.getID_Listenkandidatur(null,
          gruppe.getSchluessel(),
          shortCode,
          listenplatz);
      if (id_Listenkandidatur == null) {
        throw new ImportException(
            Messages.bind(MessageKeys.Error_Kandidat_0_FuerGruppe_1_in_2_nichtGefunden,
                kandidatIdent,
                gruppe.getSchluessel(),
                _importHandler.getImportRegion().getBezeichnung()));
      }
      _importHandler.addStimmen(_importHandler.getImportRegion(),
          gruppe.getSchluessel(),
          id_Listenkandidatur,
          stimmen);
    }
  }

  private GebietModel getGebietModel(Element reportingUnitVotes) throws ImportException {
    String gebietsIdStr = reportingUnitVotes
        .getFirstChildElement(XMLTags.EML_REPORTING_UNIT_IDENTIFIER, NS_EML)
        .getAttributeValue(XMLTags.ATTR_EML_ID);
    if (gebietsIdStr.indexOf("::") > 0) { //$NON-NLS-1$
      gebietsIdStr = gebietsIdStr.split("::")[1]; //$NON-NLS-1$
    }
    int level;
    if (gebietsIdStr.indexOf("CSB") > -1) { //$NON-NLS-1$
      level = GebietModel.EBENE_CSB;
    } else if (gebietsIdStr.indexOf("HSB") > -1) { //$NON-NLS-1$
      level = GebietModel.EBENE_HSB;
    } else if (gebietsIdStr.indexOf("SB") > -1) { //$NON-NLS-1$
      level = GebietModel.EBENE_SB;
    } else {
      level = GebietModel.EBENE_PSB;
    }
    Integer regionCategory = WahlModel.GEBIETSART_FOR_WAHLART_UND_EBENE.get(_importHandler
        .getElectionCategory(), level);
    if (regionCategory == null) {
      throw new NullPointerException("Unknown region category: " + regionCategory); //$NON-NLS-1$
    }
    if (!_importHandler.importRegionResults(regionCategory.intValue())) {
      return null;
    }
    int regionNumber = AbstractImportClient.readGebietsnummer510(reportingUnitVotes);
    return _importHandler.findGebietByGebietsartAndNummer(regionCategory.intValue(), regionNumber);
  }

  /**
   * @param totalVotes
   * @param id_Gebiet
   * @param id_Ergebniseingang
   * @throws ImportException
   */
  void checkGesamtstimmenAllgemein(Element totalVotes, Gesamtstimmen gesamtstimmen)
      throws ImportException {
    GruppeAllgemeinXmlAdapter adapter = new GruppeAllgemeinXmlAdapter();

    // Persons entitled to vote
    int anzWahlberechtigte = adapter.getXml(totalVotes, GruppeAllgemein.WAHLBERECHTIGTE);
    int summeRegionalerWahlberechtigter = gesamtstimmen
        .getGruppenstimmen(GruppeAllgemein.WAHLBERECHTIGTE.schluessel);
    if (anzWahlberechtigte != summeRegionalerWahlberechtigter) {
      throw new ImportException(
          Messages
              .bind(MessageKeys.Error_SummeDerRegionalenWahlberechtigten_0_EntsprichtNichtDerGesamtanzahlInDatei_1,
                  summeRegionalerWahlberechtigter,
                  anzWahlberechtigte));
    }

    // Number of votes
    int anzGueltige = adapter.getXml(totalVotes, GruppeAllgemein.GUELTIGE);
    if (anzGueltige != gesamtstimmen.getGruppenstimmen(GruppeAllgemein.GUELTIGE.schluessel)) {
      throw new ImportException(
          Messages
              .bind(MessageKeys.Error_SummeDerRegionalstimmen_0_EntsprichtNichtAnzahlWaehlerInDatei_1,
                  gesamtstimmen.getGruppenstimmen(GruppeAllgemein.GUELTIGE.schluessel),
                  anzGueltige));
    }

    // Invalid votes
    int anzUngueltige = adapter.getXml(totalVotes, GruppeAllgemein.UNGUELTIGE);
    if (anzUngueltige != gesamtstimmen.getGruppenstimmen(GruppeAllgemein.UNGUELTIGE.schluessel)) {
      throw new ImportException(
          Messages.bind(MessageKeys.Error_SummeDerOngeldigRegionalstimmen_0_EntsprichtNicht_1,
              GruppeAllgemein.UNGUELTIGE.getName(),
              anzUngueltige));
    }
    int anzLeere = adapter.getXml(totalVotes, GruppeAllgemein.LEER);
    if (anzLeere != gesamtstimmen.getGruppenstimmen(GruppeAllgemein.LEER.schluessel)) {
      throw new ImportException(
          Messages
              .bind(MessageKeys.Error_SummeDerBlancoRegionalstimmen_0_EntsprichtNichtAnzahlInDatei_1,
                  GruppeAllgemein.LEER.getName(),
                  anzLeere));
    }
    int anzWaehler = anzGueltige + anzUngueltige + anzLeere;
    if (anzWaehler != gesamtstimmen.getGruppenstimmen(GruppeAllgemein.WAEHLER.schluessel)) {
      throw new ImportException(
          Messages.bind(MessageKeys.Error_SummeDerRegionalstimmen_0_1_EntsprichtNicht_2,
              gesamtstimmen.getGruppenstimmen(GruppeAllgemein.WAEHLER.schluessel),
              GruppeAllgemein.WAEHLER.getName(),
              anzGueltige));
    }
    // GUELTIG_ODER_LEER is not in the EML so there is no need to check it
  }

  @SuppressWarnings("synthetic-access")
  private Gruppe startNewGroup(Element selectionNode) {
    int gesamtstimmenGruppe = XMLImportHelper.getIntValue(selectionNode
        .getFirstChildElement(XMLTags.EML_VALID_VOTES, NS_EML));
    Element listIdentifier = selectionNode.getFirstChildElement(XMLTags.EML_LISTEN_IDENTIFIER,
        NS_EML);
    int schluessel = XMLImportHelper.getAttributeIntValue(listIdentifier, XMLTags.ATTR_EML_ID);
    return new Gruppe(schluessel, gesamtstimmenGruppe);
  }

  /**
   * Small helper class to memorize the key and number of votes of a list group
   */
  private static class Gruppe {
    private final int _schluessel;
    int _gesamtstimmenGruppe;

    private Gruppe() {
      this._schluessel = -1;
      this._gesamtstimmenGruppe = -1;
    }

    public void subtract(int stimmen) {
      _gesamtstimmenGruppe -= stimmen;
    }

    private Gruppe(int schluessel, int gesamtstimmenGruppe) {
      this._schluessel = schluessel;
      this._gesamtstimmenGruppe = gesamtstimmenGruppe;
    }

    public int getGesamtstimmenGruppe() {
      return _gesamtstimmenGruppe;
    }

    public int getSchluessel() {
      return _schluessel;
    }
  }
}
