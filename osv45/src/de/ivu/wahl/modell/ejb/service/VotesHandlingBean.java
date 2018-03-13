/*
 * VotesHandlingBean
 *
 * Copyright (c) 2002-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb.service;

import static de.ivu.ejb.EJBUtil.lookupLocal;
import static de.ivu.wahl.WahlInfo.getWahlInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import de.ivu.wahl.AnwContext;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.WahlStatelessSessionBeanBase;
import de.ivu.wahl.admin.PropertyHandling;
import de.ivu.wahl.admin.PropertyHandlingBean;
import de.ivu.wahl.auswertung.erg.ResultSummary;
import de.ivu.wahl.auswertung.erg.sv.Gruppenzeile;
import de.ivu.wahl.auswertung.erg.sv.SitzverteilungErg;
import de.ivu.wahl.auswertung.erg.sv.kandidat.KandidatInfo;
import de.ivu.wahl.auswertung.erg.sv.kandidat.KandidatInfoToolkit;
import de.ivu.wahl.export.RG520VotesHelper;
import de.ivu.wahl.export.VotesByRegionNumber;
import de.ivu.wahl.export.WeightedVotesByGroup;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.Gesamtstimmen;
import de.ivu.wahl.modell.GesamtstimmenImpl;
import de.ivu.wahl.modell.GruppeGebietsspezifischModel;
import de.ivu.wahl.modell.GruppeKonstanten;
import de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein;
import de.ivu.wahl.modell.ListsInRegionsModel;
import de.ivu.wahl.modell.StimmergebnisModel;
import de.ivu.wahl.modell.ejb.Ergebniseingang;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifisch;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifischHome;
import de.ivu.wahl.modell.ejb.Liste;
import de.ivu.wahl.modell.ejb.Listenkandidatur;
import de.ivu.wahl.modell.ejb.ListenkandidaturErgebnis;
import de.ivu.wahl.modell.ejb.ListenkandidaturErgebnisHome;
import de.ivu.wahl.modell.ejb.SitzberechnungErgebnis;
import de.ivu.wahl.modell.ejb.Stimmergebnis;
import de.ivu.wahl.modell.etc.GeneralVotingResults;
import de.ivu.wahl.modell.etc.VotesPerCandidate;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.result.result.AssignmentType;
import de.ivu.wahl.result.result.Distribution;

/**
 * Business services with respect to votes
 * 
 * @author jon@ivu.de
 */
@Stateless
@Local(VotesHandling.class)
public class VotesHandlingBean extends WahlStatelessSessionBeanBase implements VotesHandling {

  private static final long serialVersionUID = -1698028693845863132L;
  private static final Logger LOGGER = Logger.getLogger(VotesHandlingBean.class);

  @Override
  public Integer getTotalBlankVotes(String id_Ergebniseingang) {
    String id_WurzelGebiet = WahlInfo.getWahlInfo().getID_Wurzelgebiet();
    GeneralVotingResults generalVotingResults = getGeneralVotingResults(id_Ergebniseingang,
        id_WurzelGebiet);
    return generalVotingResults.getVotes(GruppeKonstanten.GruppeAllgemein.LEER);
  }

  @Override
  public Integer getTotalValidVotes(String id_Ergebniseingang) {
    String id_WurzelGebiet = WahlInfo.getWahlInfo().getID_Wurzelgebiet();
    GeneralVotingResults generalVotingResults = getGeneralVotingResults(id_Ergebniseingang,
        id_WurzelGebiet);
    return generalVotingResults.getVotes(GruppeKonstanten.GruppeAllgemein.GUELTIGE);
  }

  @Override
  public Integer getTotalInvalidVotes(String id_Ergebniseingang) {
    String id_WurzelGebiet = WahlInfo.getWahlInfo().getID_Wurzelgebiet();
    GeneralVotingResults generalVotingResults = getGeneralVotingResults(id_Ergebniseingang,
        id_WurzelGebiet);
    return generalVotingResults.getVotes(GruppeKonstanten.GruppeAllgemein.UNGUELTIGE);
  }

  @Override
  public VotesPerCandidate getVotesPerCandidate(String id_Ergebniseingang,
      String id_GruppeGebietsspezifisch) throws ImportException {
    try {
      Collection<Stimmergebnis> stimmergebnisseGg = getStimmergebnisHome()
          .findAllByErgebniseingangAndGruppeGebietsspezifisch(id_Ergebniseingang,
              id_GruppeGebietsspezifisch);
      Map<String, Integer> stimmergebnisse = new HashMap<String, Integer>();
      for (Stimmergebnis stimmergebnis : stimmergebnisseGg) {
        if (stimmergebnis.getID_Listenkandidatur() != null) {
          stimmergebnisse.put(stimmergebnis.getID_Listenkandidatur(), stimmergebnis.getStimmen());
        }
      }
      return new VotesPerCandidate(stimmergebnisse);
    } catch (FinderException e) {
      throw new ImportException(Messages.getString(MessageKeys.Error_FehlerBeimFuellenDerWahldaten));
    }
  }

  @Override
  public int getVotesForListInRegion(Liste liste, String id_Gebiet, String id_Ergebniseingang)
      throws FinderException {
    Stimmergebnis listVotes = getStimmergebnisHome()
        .findByErgebniseingangAndGebietAndGruppeForGruppe(id_Ergebniseingang,
            id_Gebiet,
            liste.getID_Gruppe());
    return listVotes.getStimmen();
  }

  @Override
  public GeneralVotingResults getGeneralVotingResults(String id_Ergebniseingang, String id_Gebiet) {
    try {
      Collection<Stimmergebnis> votingResults = getStimmergebnisHome()
          .findAllByGebietAndErgebniseingangAllgemein(id_Ergebniseingang, id_Gebiet);
      return new GeneralVotingResults(votingResults);
    } catch (FinderException e) {
      e.printStackTrace();
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "general voting results"), e); //$NON-NLS-1$
    }

  }

  @Override
  public List<Stimmergebnis> getSortedCandidateResults(Liste liste,
      String id_Gebiet,
      String id_Ergebniseingang) throws FinderException {
    Collection<Stimmergebnis> unsortedCandidateResults = getStimmergebnisHome()
        .findAllByErgebniseingangAndGebietAndListe(id_Ergebniseingang,
            id_Gebiet,
            liste.getID_Liste());
    List<Stimmergebnis> candidateResults = new ArrayList<Stimmergebnis>(unsortedCandidateResults);
    Collections.sort(candidateResults, new Comparator<Stimmergebnis>() {
      @Override
      public int compare(Stimmergebnis x, Stimmergebnis y) {
        return Integer.signum(x.getListenkandidatur().getListenplatz()
            - y.getListenkandidatur().getListenplatz());
      }
    });
    return candidateResults;
  }

  /**
   * Funktion wandelt ein EJB-Bean eines Listenkandidaten in eine Infoklasse mit Modelattributen
   * Diese Klasse wird f�r die clienseitige Anzeige verwendet
   * 
   * @param gesamtstimmen may be null
   * @param listenkandidatur das EJB-Bean eines Listenkandidaten
   * @return die Infoklasse f�r den Client
   */
  private static KandidatInfo createListenkandidaten(ListenkandidaturErgebnis listenkandidaturErgebnis,
      String id_Gebiet,
      Gesamtstimmen gesamtstimmen) {
    Listenkandidatur listenkandidatur = listenkandidaturErgebnis.getListenkandidatur();

    WahlInfo info = WahlInfo.getWahlInfo();
    GebietModel gebiet = info.getModel4Gebiet(id_Gebiet);
    boolean useShortCode = info.useShortCode();
    int stimmen;
    // gesamtstimmen is null for all election types except EK, TK, PS2
    // (@see ElectionSubcategory#isElectionWithListGroups())
    if (gesamtstimmen == null) {
      // Votes of a candidate in EP, GR, BC, GC and PS1 elections (only one electoral district)
      stimmen = getStimmen(id_Gebiet, listenkandidatur, useShortCode);
    } else {
      // Votes of a candidate in EK, TK and PS2 elections (more than one electoral district)
      String shortCode = listenkandidatur.getID_Personendaten();
      int gruppenschluessel = listenkandidatur.getGruppe().getSchluessel();
      stimmen = gesamtstimmen.getStimmen(gruppenschluessel, -1, shortCode);
    }

    return new KandidatInfo(listenkandidatur.getPersonendaten(),
        listenkandidaturErgebnis.getDetails(), gebiet, listenkandidatur.getGruppe(), stimmen,
        listenkandidatur.getListe(), listenkandidatur.getListenplatz());
  }

  private static int getStimmen(String id_Gebiet,
      Listenkandidatur listenkandidatur,
      boolean useShortCode) {

    Collection<Stimmergebnis> stimmergebnisCol = listenkandidatur.getStimmergebnisCol();
    int stimmen = 0;
    for (Stimmergebnis stimmergebnis : stimmergebnisCol) {
      if (!useShortCode && id_Gebiet.equals(stimmergebnis.getID_Gebiet())) {
        return stimmergebnis.getStimmen();
      } else {
        stimmen += stimmergebnis.getStimmen();
      }
    }
    return stimmen;
  }

  @Override
  public List<KandidatInfo> getGewaehltKandidatenForGebietAlphabetisch(final String id_ergebniseingang,
      final String id_Gebiet) throws EJBException {

    // _sitzverteilungHandling.pruefeSitzverteilung(anwContext.getID_Wahl());
    WahlInfo wahlInfo = getWahlInfo();
    wahlInfo.getWahl().readLock();
    List<KandidatInfo> ret = getGewaehltKandidaten4Gebiet(id_ergebniseingang, id_Gebiet);
    KandidatInfoToolkit.sortiereNameAlphabetisch(ret);
    return ret;
  }

  @Override
  public List<KandidatInfo> getGewaehltKandidatenForGebietOrderByGruppe(final AnwContext anwContext,
      final String id_ergebniseingang,
      final String id_Gebiet) throws EJBException {

    // _sitzverteilungHandling.pruefeSitzverteilung(anwContext.getID_Wahl());
    WahlInfo wahlInfo = getWahlInfo(anwContext);
    wahlInfo.getWahl().readLock();
    List<KandidatInfo> ret = getGewaehltKandidaten4Gebiet(id_ergebniseingang, id_Gebiet);
    KandidatInfoToolkit.sortiereNameGruppeUndListenplatz(ret);
    return ret;
  }

  /**
   * @param anwContext Anwenderkontext f�r die Bestimmung der Wahl und der Rechte
   * @param id_Gebiet
   * @return Gew�hlte Listenkandidaten als Info-Objekte, unsortiert
   */
  private List<KandidatInfo> getGewaehltKandidaten4Gebiet(String id_Ergebniseingang,
      String id_Gebiet) {
    long start = new Date().getTime();

    Gesamtstimmen gesamtstimmen = null;
    if (WahlInfo.getWahlInfo().isElectionWithListGroups()) {
      boolean isEK = WahlInfo.getWahlInfo().isEK();
      gesamtstimmen = getGesamtstimmen(isEK);
      LOGGER.info("getGesamtstimmen in ms " + (new Date().getTime() - start)); //$NON-NLS-1$
    }

    Collection<ListenkandidaturErgebnis> electedCandidates = getGewaehlteListenkandidaten(id_Ergebniseingang);
    return generateClientList(electedCandidates, id_Gebiet, gesamtstimmen);
  }

  /**
   * Liefert die Liste der gew�hlten Listenkandidaten eines Gebietes bzw. der Untergebiete des
   * �bergebenen Gebietes
   * 
   * @param anwContext Anwenderkontext
   * @param id_Gebiet Prim�rschl�ssel des Gebietes
   * @return Liste der gew�hlten Listenkandidaten eines Gebietes
   * @throws EJBException Bei einem allgemeinen Problem
   */
  private Collection<ListenkandidaturErgebnis> getGewaehlteListenkandidaten(final String id_Ergebniseingang)
      throws EJBException {

    WahlInfo wahlInfo = getWahlInfo();
    wahlInfo.getWahl().readLock();

    try {
      ListenkandidaturErgebnisHome listenkandidaturHome = ListenkandidaturErgebnisHome.HomeFinder
          .findHome(this);
      return listenkandidaturHome.findAllByErgebniseingangAndGewaehlt(id_Ergebniseingang);
    } catch (EJBException e) {
      throw new EJBException(e);
    } catch (FinderException e) {
      throw new EJBException(
          Messages.bind(MessageKeys.Error_ExceptionBeimErmittelnDerListenkandidatenFuerErgebniseingang_0,
              id_Ergebniseingang), e);
    }
  }

  /**
   * Erzeugt die KandidatenInfo-Objekte
   * 
   * @param beanCollection Liste der Beans, die die Original-Kandidateninformationen enthalten
   * @param gesamtstimmen may be null
   * @return Liste der Datencontainer mit Kandidateninformationen (ohne Beziehungen zu den Beans)
   */
  private List<KandidatInfo> generateClientList(Collection<ListenkandidaturErgebnis> beanCollection,
      String id_Gebiet,
      Gesamtstimmen gesamtstimmen) {
    List<KandidatInfo> result = new ArrayList<KandidatInfo>();
    for (ListenkandidaturErgebnis listenkandidat : beanCollection) {
      result.add(createListenkandidaten(listenkandidat, id_Gebiet, gesamtstimmen));
    }
    return result;
  }

  /**
   * TODO: ggfs sortieren!!!
   * 
   * @see de.ivu.wahl.auswertung.AuswertungHandling#getAllGruppen(java.lang.String)
   */
  public List<GruppeGebietsspezifischModel> getAllGruppen(String id_Gebiet)
      throws EJBException, FinderException {

    return toModelList(GruppeGebietsspezifischHome.HomeFinder.findHome(this)
        .findAllByGebiet(id_Gebiet));
  }

  /**
   * Sitzverteilung aus der Datenbank lesen, vornehmlich f�r Niemeyer-basierte Auswertungen
   * 
   * @return Sitzverteilung
   * @throws EJBException Bei einem allgemeinen Problem
   */
  @Override
  public SitzverteilungErg getSitzverteilungErgebnis(String id_ergebniseingang) throws EJBException {
    WahlInfo wahlInfo = getWahlInfo();
    wahlInfo.getWahl().readLock();
    boolean isEK = wahlInfo.isEK();
    try {
      SitzverteilungErg sitzverteilungErg = new SitzverteilungErg("BEZEICHNUNG", wahlInfo); //$NON-NLS-1$
      Map<String, Integer> id_GruppeAndSitze = getGruppenAndSitze(id_ergebniseingang);

      Collection<Stimmergebnis> stimmergebnisseWurzelgebiet = getStimmergebnisHome()
          .findAllByGebietAndErgebniseingang(wahlInfo.getID_Wurzelgebiet(), id_ergebniseingang);

      // Feststellen der Gesamtanzahl der g�ltigen Stimmen
      float gueltigeStimmen;
      Map<Gruppe, VotesByRegionNumber> votesByRegionByGruppe = null;
      if (isEK) {
        votesByRegionByGruppe = new RG520VotesHelper(this, id_ergebniseingang)
            .getVotesByRegionByGruppe();
        gueltigeStimmen = 0;
        for (VotesByRegionNumber votesByRegionNumber : votesByRegionByGruppe.values()) {
          gueltigeStimmen += votesByRegionNumber.getTotalWeightedVotes();
        }
      } else {
        gueltigeStimmen = getGueltigeStimmen(stimmergebnisseWurzelgebiet);
      }

      for (Stimmergebnis stimmergebnis : stimmergebnisseWurzelgebiet) {
        if (stimmergebnis.getStimmart() == StimmergebnisModel.STIMMART_LISTENSTIMME) {
          GruppeGebietsspezifisch gebietGruppe = stimmergebnis.getGruppeGebietsspezifisch();
          Gruppe gruppe = gebietGruppe.getGruppe();
          Integer sitze = id_GruppeAndSitze.get(gruppe.getID_Gruppe());
          if (sitze == null) {
            sitze = 0;
          }
          int stimmen = isEK
              ? (votesByRegionByGruppe.get(gruppe).getTotalWeightedVotes())
              : stimmergebnis.getStimmen();
          if (stimmergebnis.getListenkandidatur() == null) {
            if (gueltigeStimmen > 0) {
              float stimmenprozent = (float) (stimmen * 100.0 / gueltigeStimmen);
              sitzverteilungErg.addGruppenzeile(new Gruppenzeile(gruppe, gebietGruppe, stimmen,
                  stimmenprozent, sitze));
            }
          }
        }
      }

      return sitzverteilungErg;
    } catch (FinderException e) {
      throw new EJBException(e);
    }
  }

  private Map<String, Integer> getGruppenAndSitze(final String id_ergebniseingang)
      throws FinderException {
    Map<String, Integer> result = new HashMap<String, Integer>();
    Collection<SitzberechnungErgebnis> sitzberechnungErgebnisseP42 = getSitzberechnungErgebnisHome()
        .findAllByErgebniseingangAndVerteilung(id_ergebniseingang, Distribution.P42.getId());
    for (SitzberechnungErgebnis sbe : sitzberechnungErgebnisseP42) {
      addSitzeGruppe(result, sbe);
    }
    Collection<SitzberechnungErgebnis> findSitzberechnungErgebnisP3 = getSitzberechnungErgebnisHome()
        .findAllByErgebniseingangAndVerteilung(id_ergebniseingang, Distribution.P3.getId());
    for (SitzberechnungErgebnis sbe : findSitzberechnungErgebnisP3) {
      if (sbe.getSchritttyp() != AssignmentType.TRIVIAL.getId()) {
        addSitzeGruppe(result, sbe);
      }
    }
    return result;
  }

  private void addSitzeGruppe(Map<String, Integer> result, SitzberechnungErgebnis sbe) {
    String id_gruppe = sbe.getID_Gruppe();
    if (id_gruppe != null) {
      Integer oldSeats = result.get(id_gruppe);
      if (oldSeats == null) {
        result.put(id_gruppe, sbe.getSitze());
      } else {
        result.put(id_gruppe, oldSeats + sbe.getSitze());
      }
    }
  }

  /**
   * Feststellen der Gesamtanzahl der g�ltigen Stimmen
   */
  private float getGueltigeStimmen(Collection<Stimmergebnis> allErfassungseinheitStimmergebnis) {
    float gueltigeStimmen = 0;
    for (Stimmergebnis stimmergebnis : allErfassungseinheitStimmergebnis) {
      GruppeGebietsspezifisch gebietGruppe = stimmergebnis.getGruppeGebietsspezifisch();
      int position = gebietGruppe.getPosition();
      if (position == GruppeAllgemein.GUELTIGE.getPosition()
          && stimmergebnis.getListenkandidatur() == null) {
        gueltigeStimmen = stimmergebnis.getStimmen();
      }
    }
    return gueltigeStimmen;
  }

  /**
   * Gesamtstimmen contains the votes for candidates and candidate lists in all sub-regions of the
   * root region.
   * <p>
   * The Gesamtstimmen is filled using getStimmergebnisHome().findAllByGebietAndErgebniseingang()
   * for the most recent results of each sub-region of the root region.
   * 
   * @see de.ivu.wahl.auswertung.AuswertungHandling#getGesamtstimmen(java.lang.String)
   */
  // TODO cachen!!!
  @Override
  public Gesamtstimmen getGesamtstimmen(boolean considerVoteValues) {
    GesamtstimmenImpl gesamtstimmen;
    if (considerVoteValues) {
      gesamtstimmen = new GesamtstimmenImpl(getGebietHandling().getVoteValues());
    } else {
      gesamtstimmen = new GesamtstimmenImpl();
    }
    Gebiet root = WahlInfo.getWahlInfo().getWahl().getWurzelgebiet();
    for (Gebiet subRegion : root.getGebietCol()) {
      for (Stimmergebnis votingResult : getVotingResults(subRegion)) {
        int gruppenschluessel = votingResult.getGruppeGebietsspezifisch().getGruppe()
            .getSchluessel();
        int stimmen = votingResult.getStimmen();
        if (votingResult.getID_Listenkandidatur() != null) {
          // votingResult for a candidate
          String shortCode = votingResult.getListenkandidatur().getPersonendaten()
              .getID_Personendaten();
          gesamtstimmen.addKandidatenstimmen(gruppenschluessel,
              -1,
              shortCode,
              stimmen,
              subRegion.getNummer());
        } else if (!votingResult.getGruppeGebietsspezifisch().isListeZugelassen()) {
          // votingResult for a candidate list (GruppeGebietsspezifisch)
          // Do not consider GruppeGebietsspezifisch where a list is submitted, because those votes
          // are already counted as candidate votes
          gesamtstimmen.addGruppenstimmen(gruppenschluessel, stimmen);
        }
      }
    }
    return gesamtstimmen;
  }

  PropertyHandling _propertyHandling = null;

  /**
   * @return PropertyHandling
   */
  public PropertyHandling getPropertyHandling() {
    if (_propertyHandling == null) {
      _propertyHandling = lookupLocal(PropertyHandlingBean.class.getSimpleName());
    }
    return _propertyHandling;
  }

  /**
   * @return a model that has all information about groups and lists in the child regions of the
   *         root region
   */
  public ListsInRegionsModel getListsInRegionsModel() {
    try {
      Gebiet rootRegion = getWahlInfo().getWahl().getWurzelgebiet();
      Collection<GruppeGebietsspezifisch> groupsInRootRegion = rootRegion
          .getGruppeGebietsspezifischCol();
      Collection<Gruppe> groups = new ArrayList<Gruppe>();
      for (GruppeGebietsspezifisch ggs : groupsInRootRegion) {
        groups.add(ggs.getGruppe());
      }
      Collection<Gebiet> childRegions = rootRegion.getGebietErwartetCol();

      Collection<Liste> allListen = getListeHome().findAll();

      return new ListsInRegionsModel(childRegions, groups, allListen);
    } catch (FinderException fe) {
      throw new EJBException(fe);
    }
  }

  @Override
  public ResultSummary getResultSummary() throws EJBException {
    return new ResultSummaryHelper(this).getResultSummary();
  }

  @Override
  public ResultSummary getResultSummary(AnwContext anwContext, int gebietsart, int gebietsnummer)
      throws EJBException {
    Gebiet wurzelgebiet = WahlInfo.getWahlInfo().getWahl().getWurzelgebiet();
    Gebiet gebiet = getGebiet(anwContext, gebietsart, gebietsnummer);
    if (!wurzelgebiet.getID_Gebiet().equals(gebiet.getID_Gebiet())) {
      // Empty ResultSummary for all regions except for the root region
      return new ResultSummary();
    }

    // only for the root region return a filled ResultSummary
    return getResultSummary();
  }

  /**
   * Convenience zum Holen eines Gebiets
   * 
   * @return Gebiet anhand der Gebietsart und der Gebietsnummer
   * @throws EJBException genereller Fehler
   */
  private Gebiet getGebiet(AnwContext anwContext, int gebietsart, int gebietsnummer)
      throws EJBException {
    try {
      return getGebietHome().findByWahlAndGebietsartAndNummer(anwContext.getID_Wahl(),
          gebietsart,
          gebietsnummer);
    } catch (FinderException fe) {
      throw new EJBException(fe);
    }
  }

  /**
   * @return the most recent voting results for a region (both for candidates and candidate lists)
   */
  public Collection<Stimmergebnis> getVotingResults(Gebiet region) {
    Ergebniseingang letzterGueltigerEingang = region.getLetzterGueltigerEingang();
    if (letzterGueltigerEingang == null) {
      // No results for this childRegion
      return Collections.emptyList();
    }

    try {
      return getStimmergebnisHome().findAllByGebietAndErgebniseingang(region.getID_Gebiet(),
          letzterGueltigerEingang.getID_Ergebniseingang());
    } catch (FinderException e) {
      throw new EJBException(e);
    }
  }

  @Override
  public WeightedVotesByGroup getWeightedVotesByGroup() throws EJBException {
    Ergebniseingang letzterGueltigerEingang = WahlInfo.getWahlInfo().getWahl().getWurzelgebiet()
        .getLetzterGueltigerEingang();
    if (letzterGueltigerEingang == null) {
      // No results for this childRegion
      Collection<Stimmergebnis> col = Collections.emptyList();
      return new WeightedVotesByGroup(col, new VoteValues());
    }

    try {
      Collection<Stimmergebnis> stimmergebnisse = getStimmergebnisHome()
          .findAllByErgebniseingangAndStimmartForGroups(letzterGueltigerEingang.getID_Ergebniseingang(),
              StimmergebnisModel.STIMMART_LISTENSTIMME);
      VoteValues voteValues = getGebietHandling().getVoteValues();
      return new WeightedVotesByGroup(stimmergebnisse, voteValues);
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "Listenergebnisse"), e); //$NON-NLS-1$
    }
  }

  @Override
  public VotesByRegionNumber getVotesForP2ListByRegion(String id_Ergebniseingang,
      Liste liste,
      VoteValues voteValues) {
    try {
      Collection<Stimmergebnis> stimmergebnisse = getStimmergebnisHome()
          .findAllByErgebniseingangAndListe(id_Ergebniseingang, liste.getID_Liste());
      return new VotesByRegionNumber(stimmergebnisse, voteValues);
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          Messages.getString(MessageKeys.Msg_StimmergebnisseFuerListen)), e);
    }
  }

  @Override
  public VotesByRegionNumber getVotesForP2ListByRegion(Liste liste) {
    String id_Ergebniseingang = WahlInfo.getWahlInfo().getWahl().getWurzelgebiet()
        .getID_LetzterEingang();
    VoteValues voteValues = getGebietHandling().getVoteValues();
    return getVotesForP2ListByRegion(id_Ergebniseingang, liste, voteValues);
  }

}
