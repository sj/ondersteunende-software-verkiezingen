/*
 * MetadatenDb
 * 
 * Created on 06.02.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import java.util.Collection;

import javax.ejb.FinderException;

import org.apache.log4j.Category;

import nu.xom.Element;

import de.ivu.ejb.EJBUtil;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.SystemInfo;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.GesamtstimmenImpl;
import de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein;
import de.ivu.wahl.modell.ListenkandidaturModel;
import de.ivu.wahl.modell.ejb.Ergebniseingang;
import de.ivu.wahl.modell.ejb.GebietHome;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifischHome;
import de.ivu.wahl.modell.ejb.GruppeHome;
import de.ivu.wahl.modell.ejb.ListeHome;
import de.ivu.wahl.modell.ejb.Listenkandidatur;
import de.ivu.wahl.modell.ejb.ListenkandidaturHome;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public abstract class AbstractErgebnisImportHandler implements ErgebnisImportHandler {

  private static final Category LOGGER = Log4J.configure(AbstractErgebnisImportHandler.class);

  static {
    LOGGER.info(Log4J.dumpVersion(AbstractErgebnisImportHandler.class,
        Log4J.extractVersion("$Revision$"))); //$NON-NLS-1$
  }

  ImportHandling _impHandling = null;

  GruppeHome _gruppeHome = EJBUtil.findLocalHomeNoCache("Gruppe"); //$NON-NLS-1$
  GebietHome _gebietHome = EJBUtil.findLocalHomeNoCache("Gebiet"); //$NON-NLS-1$
  GruppeGebietsspezifischHome _ggHome = EJBUtil.findLocalHomeNoCache("GruppeGebietsspezifisch"); //$NON-NLS-1$
  ListenkandidaturHome _lkHome = EJBUtil.findLocalHomeNoCache("Listenkandidatur"); //$NON-NLS-1$
  ListeHome _listeHome = EJBUtil.findLocalHomeNoCache("Liste"); //$NON-NLS-1$
  Ergebniseingang _ergebniseingang;
  String _id_Ergebniseingang = null;
  WahlInfo _wahlInfo;

  /*
   * Save total votes if lists are unique at root level (non-Javadoc)
   * @see de.ivu.wahl.dataimport.ErgebnisImportHandler#saveTotalVotes()
   */
  @Override
  public boolean saveCandidateVotes(int regionCategory) throws ImportException {
    return regionCategory != _wahlInfo.getGebietsartWurzelgebiet()
        || !_wahlInfo.isElectionWithListGroups();
  }

  @Override
  public ElectionCategory getElectionCategory() {
    return _wahlInfo.getElectionCategory();
  }

  @Override
  public boolean importRegionResults(int regionCategory) {
    if (SystemInfo.getSystemInfo().getWahlModus() != AbstractImportEML.MODE_DB_P5) {
      return regionCategory <= _wahlInfo.getGebietsartErfassungseinheit();
    }
    if (_wahlInfo.getElectionCategory().isMunicipalityElection()) {
      return regionCategory < GebietModel.GEBIETSART_STIMMBEZIRK;
    }
    return regionCategory <= _wahlInfo.getGebietsarten()[1];
  }

  @Override
  public GebietModel findGebietByGebietsartAndNummer(int gebietsart, int gebietsNr)
      throws ImportException {
    try {
      return ((GebietHome) EJBUtil.findLocalHomeNoCache("Gebiet")) //$NON-NLS-1$
          .findByWahlAndGebietsartAndNummer(_wahlInfo.getWahl().getID_Wahl(), gebietsart, gebietsNr);
    } catch (FinderException e) {
      throw new ImportException(Messages.bind(MessageKeys.Error_GebietWurdeNichtAngelegt_0,
          gebietsNr));
    }
  }

  @Override
  public int getNumberOfCandidates(GebietModel gebiet, int gruppenschluessel)
      throws ImportException {
    try {
      return _lkHome.findAllByGebietAndGruppenschluessel(gebiet.getID_Gebiet(), gruppenschluessel)
          .size();
    } catch (FinderException e) {
      throw new ImportException(Messages.getString(MessageKeys.Error_FehlerBeimFuellenDerWahldaten));
    }
  }

  @Override
  public String getID_Listenkandidatur(GebietModel gebiet,
      int gruppenschluessel,
      String shortCode,
      int listenplatz) {
    try {
      ListenkandidaturModel listenkandidaturModel = getListenkandidaturModel(gebiet,
          gruppenschluessel,
          shortCode,
          listenplatz);
      return listenkandidaturModel == null ? null : listenkandidaturModel.getID_Listenkandidatur();
    } catch (FinderException e) {
      // throw exception in next step
      return null;
    }
  }

  private ListenkandidaturModel getListenkandidaturModel(GebietModel gebiet,
      int gruppenschluessel,
      String shortCode,
      int listenplatz) throws FinderException {
    if (gebiet == null && shortCode == null) {
      // Search by listenplatz
      Collection<Listenkandidatur> lkCol = _lkHome
          .findAllByGruppenschluesselAndListenplatz(gruppenschluessel, Integer.valueOf(listenplatz));
      if (lkCol.size() == 1) {
        return lkCol.iterator().next();
      } else {
        LOGGER.warn(Messages.bind(MessageKeys.Warn_Fand_0_KandidatenFuerGruppe_1_LP_2,
            lkCol.size(),
            gruppenschluessel,
            listenplatz));
        return null;
      }
    } else if (gebiet == null && shortCode != null) {
      // Search by shortCode
      Collection<Listenkandidatur> lkCol = _lkHome.findAllByPersonendaten(shortCode);
      if (lkCol.size() == 1) {
        return lkCol.iterator().next();
      } else {
        return null;
      }
    } else if (gebiet != null && shortCode == null) {
      // Search by listenplatz + gebiet
      return _lkHome.findByGebietGruppenschluesselAndListenplatz(gebiet.getID_Gebiet(),
          gruppenschluessel,
          Integer.valueOf(listenplatz));
    } else {
      // Search by shortCode + gebiet
      return _lkHome.findByGebietAndPerson(gebiet.getID_Gebiet(), shortCode);
    }
  }

  /**
   * Read general results (total votes, valid/invalid votes and so on) from region result node
   * 
   * @param resultNode xml element &lt;TotalVotes> or &lt;ReportingUntitVotes> resp.
   * @param id_Gebiet id of region
   * @param id_Ergebniseingang id for current import action
   * @throws ImportException
   */
  @Override
  public void readStimmergebnisseAllgemein(Element resultNode,
      GebietModel gebiet,
      GesamtstimmenImpl gesamtstimmen,
      int gesamtstimmenGebiet) throws ImportException {
    boolean isWurzelgebiet = resultNode.getLocalName().equals(XMLTags.EML_TOTAL_VOTES);

    // Determine the number of voters by adding valid, invalid and empty votes
    // Similarly determine the number of valid or empty votes
    GruppeAllgemeinXmlAdapter adapter = new GruppeAllgemeinXmlAdapter();
    int anzGueltige = adapter.getXml(resultNode, GruppeAllgemein.GUELTIGE);
    int anzUngueltige = adapter.getXml(resultNode, GruppeAllgemein.UNGUELTIGE);
    int anzLeere = adapter.getXml(resultNode, GruppeAllgemein.LEER);
    int anzWaehler = anzGueltige + anzUngueltige + anzLeere;
    int anzGueltigOderLeer = anzGueltige + anzLeere;
    saveStimmergebnisAllgemein(gebiet,
        GruppeAllgemein.WAEHLER.getPosition(),
        _id_Ergebniseingang,
        anzWaehler);
    saveStimmergebnisAllgemein(gebiet,
        GruppeAllgemein.GUELTIG_ODER_LEER.getPosition(),
        _id_Ergebniseingang,
        anzGueltigOderLeer);
    if (!isWurzelgebiet) {
      gesamtstimmen.addGruppenstimmen(GruppeAllgemein.WAEHLER.schluessel, anzWaehler);
      gesamtstimmen.addGruppenstimmen(GruppeAllgemein.GUELTIG_ODER_LEER.schluessel,
          anzGueltigOderLeer);
    }

    // Invalid and empty votes, admitted voters, proxy votes
    // Explaining the difference between admitted voters and counted votes
    Iterable<GruppeAllgemein> gruppen = adapter.getGruppenAllgemein();
    for (GruppeAllgemein gruppeAllgemein : gruppen) {
      int value = adapter.getFromEmlOr0(resultNode, gruppeAllgemein);
      saveStimmergebnisAllgemein(gebiet, gruppeAllgemein.getPosition(), _id_Ergebniseingang, value);
      if (!isWurzelgebiet) {
        gesamtstimmen.addGruppenstimmen(gruppeAllgemein.schluessel, value);
      }
    }

    // Check region results
    if (anzGueltige != gesamtstimmenGebiet) {
      String id_Gebiet = gebiet.getID_Gebiet();
      throw new ImportException(
          Messages.bind(MessageKeys.Error_SummeDerKandidatenstimmen_0_in_1_entspricht_nicht_2,
              gesamtstimmenGebiet,
              id_Gebiet,
              anzGueltige));
    }
  }

  protected abstract void saveStimmergebnisAllgemein(GebietModel gebiet,
      int position,
      String id_Ergebniseingang,
      int anzStimmen) throws ImportException;

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.ErgebnisImportHandler#getRegionCategoryWithLists()
   */
  @Override
  public int getRegionCategoryWithLists() {
    return _wahlInfo.getGebietsartMitListen();
  }

}
