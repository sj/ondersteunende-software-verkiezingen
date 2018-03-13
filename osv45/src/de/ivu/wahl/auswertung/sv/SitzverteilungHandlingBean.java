/*
 * SitzverteilungHandlingBean
 * 
 * Copyright (c) 2002-8 Statistisches Bundesamt und IVU Traffic Technologies AG
 * Created on 06.02.2004 
 */
package de.ivu.wahl.auswertung.sv;

import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import de.ivu.ejb.EJBUtil;
import de.ivu.ejb.IVUBeanBase;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.WahlStatelessSessionBeanBase;
import de.ivu.wahl.auswertung.erg.Besonderheiten;
import de.ivu.wahl.dataimport.ImportHandling;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.AlternativeModel;
import de.ivu.wahl.modell.Gebietsart;
import de.ivu.wahl.modell.GruppeComposite;
import de.ivu.wahl.modell.GruppeComposite.Listenkandidat;
import de.ivu.wahl.modell.GruppeKonstanten;
import de.ivu.wahl.modell.KonfliktModel;
import de.ivu.wahl.modell.ListenkandidaturErgebnisTmpModel;
import de.ivu.wahl.modell.ListenplatzNeuModel;
import de.ivu.wahl.modell.SitzverteilungModel;
import de.ivu.wahl.modell.WahlModel;
import de.ivu.wahl.modell.Wahldaten;
import de.ivu.wahl.modell.ejb.*;
import de.ivu.wahl.modell.ejb.service.GebietHandling;
import de.ivu.wahl.modell.etc.VotesPerCandidate;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.modell.impl.AlternativeModelImpl;
import de.ivu.wahl.modell.impl.KonfliktModelImpl;
import de.ivu.wahl.modell.impl.ListenplatzNeuModelImpl;
import de.ivu.wahl.modell.impl.SitzverteilungModelImpl;
import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.ElectionAndCandidatesAndVotes;
import de.ivu.wahl.result.P3ListCandidate;
import de.ivu.wahl.result.builder.CombinedList;
import de.ivu.wahl.result.determination.ElectionResultDeterminator;
import de.ivu.wahl.result.determination.P2List;
import de.ivu.wahl.result.determination.P3List;
import de.ivu.wahl.result.drawlots.Decision;
import de.ivu.wahl.result.drawlots.DecisionType;
import de.ivu.wahl.result.drawlots.DrawingLotsAlternative;
import de.ivu.wahl.result.drawlots.DrawingLotsCallbackImpl;
import de.ivu.wahl.result.drawlots.DrawingLotsIdentifier;
import de.ivu.wahl.result.model2builder.CandidateKey;
import de.ivu.wahl.result.model2builder.CombinedListKey;
import de.ivu.wahl.result.model2builder.ModelToBuilderConversion;
import de.ivu.wahl.result.model2builder.P2ListKey;
import de.ivu.wahl.result.model2builder.P3ListKey;
import de.ivu.wahl.result.result.ElectionResultImpl;
import de.ivu.wahl.runtime.UniCacheHome;
import de.ivu.wahl.runtime.UniCachePK;

/**
 * Session Bean which calculates the seat distribution
 * 
 * @author cos@ivu.de IVU Traffic Technologies AG
 */
@Stateless
@Local(SitzverteilungHandling.class)
public class SitzverteilungHandlingBean extends WahlStatelessSessionBeanBase
    implements
      SitzverteilungHandling {

  private static final String BLANK = " "; //$NON-NLS-1$
  private static final long serialVersionUID = 7167333797777195535L;

  static final Logger LOGGER = Logger.getLogger(SitzverteilungHandlingBean.class);

  static {
    LOGGER.info(Log4J.dumpVersion(SitzverteilungHandlingBean.class,
        Log4J.extractVersion("$Revision: 1.87 $"))); //$NON-NLS-1$
  }

  /*
   * (non-Javadoc) Distribute seats according to the latest valid voting results
   * @see de.ivu.wahl.auswertung.sv.SitzverteilungHandling#berechneSitzverteilung(java.lang.String)
   */
  public SitzverteilungStatus berechneSitzverteilung(final String id_Ergebniseingang)
      throws EJBException {
    WahlInfo.getWahlInfo().getWahl().lock();
    String id_Wahl = WahlInfo.getWahlInfo().getWahl().getID_Wahl();
    int programState = WahlInfo.getWahlInfo().getWahl().getStatus();
    try {
      if (WahlModel.STATE_CALCULATING == WahlInfo.getWahlInfo().getWahl().getStatus()) {
        throw new EJBException(Messages.bind(MessageKeys.Error_SitzberechnungLaeuft));
      }
      WahlInfo.getWahlInfo().getWahl().setStatus(WahlModel.STATE_CALCULATING);
      Wahldaten wahldaten = WahlInfo.getWahlInfo().getWahldaten();
      if (wahldaten == null) {
        wahldaten = initWahldaten(WahlInfo.getWahlInfo(), id_Ergebniseingang);
      } else if (!id_Ergebniseingang.equals(wahldaten.getId_Ergebniseingang())) {
        wahldaten = updateWahldaten(wahldaten, id_Ergebniseingang);
      }
      ElectionAndCandidatesAndVotes ecv = ModelToBuilderConversion.convert(wahldaten);
      DrawingLotsCallbackImpl lotingCallback = initLotingCallback(id_Ergebniseingang, ecv);
      LOGGER.info(Messages.getString(MessageKeys.Result_Tracelog_DatenkonvertierungAbgeschlossen));
      ElectionResultImpl result = new ElectionResultImpl(id_Ergebniseingang);
      ElectionResultDeterminator.determineElectionResult(lotingCallback, ecv, result);
      SitzverteilungStatus status = new SitzverteilungStatus();
      writeElectionResult(result);
      if (result.getConflict() == null) {
        programState = WahlModel.STATE_CALCULATION_SUCCESSFUL;
      } else {
        fillSitzverteilungStatus(result.getConflict(),
            id_Ergebniseingang,
            lotingCallback.getNumberOfDecisions(),
            status);
        programState = WahlModel.STATE_CALCULATION_CONFLICT;
      }
      WahlInfo.getWahlInfo().getWahl().setStatus(programState);
      WahlInfo.getWahlInfo().synchronize();
      return status;
    } catch (Error e) {
      LOGGER.error(e.getCause(), e);
      LOGGER.error(Messages.getString(MessageKeys.Error_FehlerBeiBerechnungDerSitzverteilung), e);
      throw new EJBException(
          Messages.getString(MessageKeys.Error_FehlerBeiBerechnungDerSitzverteilung)
              + ": " + e.getMessage()); //$NON-NLS-1$
    } catch (Exception e) {
      LOGGER.error(Messages.getString(MessageKeys.Error_FehlerBeiBerechnungDerSitzverteilung), e);
      throw new EJBException(
          Messages.getString(MessageKeys.Error_FehlerBeiBerechnungDerSitzverteilung)
              + ": " + e.getMessage(), e); //$NON-NLS-1$
    } finally {
      WahlInfo.getWahlInfo().getWahl().setStatus(programState);
      try {
        getUniCacheHome().remove(new UniCachePK(id_Wahl, "calculation_running"));
      } catch (RemoveException e) {
        throw new EJBException(
            Messages.getString(MessageKeys.Error_FehlerBeiBerechnungDerSitzverteilung));
      }
    }
  }

  /**
   * Fills empty Wahldaten object with metadata and voting results
   * 
   * @param wahldaten
   * @return
   * @throws ImportException
   */
  private Wahldaten initWahldaten(WahlInfo wahlInfo, String id_Ergebniseingang)
      throws ImportException {
    try {
      Wahldaten wahldaten = new Wahldaten();
      wahldaten.setWahl(wahlInfo.getWahl().getDetails());
      Collection<Gebiet> gebiete = _gebietHandling.getGebieteMitListen();
      for (Gebiet gebiet : gebiete) {
        wahldaten.addGebiet(gebiet.getDetails());
      }
      Collection<Gruppe> gruppen = getGruppeHome()
          .findAllByGruppenart(GruppeKonstanten.GRUPPENART_PARTEI);
      for (Gruppe gruppe : gruppen) {
        GruppeComposite gruppeComp = new GruppeComposite(gruppe.getDetails());
        // Check for list combination
        Collection<GruppeGebietsspezifisch> gg4gruppe = gruppe.getGruppeGebietsspezifischCol();
        for (GruppeGebietsspezifisch gg : gg4gruppe) {
          // Only lists needed here
          if (gg.getListe() == null) {
            continue;
          }
          VotesPerCandidate votesPerCandidate = getVotesHandling()
              .getVotesPerCandidate(id_Ergebniseingang, gg.getID_GruppeGebietsspezifisch());
          GruppeComposite.Liste listeComp = gruppeComp.new Liste(gg.getDetails(), gg.getGebiet()
              .getDetails());
          Collection<Listenkandidatur> kandidaten = gg.getListe().getListenkandidaturCol();
          for (Listenkandidatur lk : kandidaten) {
            if (votesPerCandidate.getVotes(lk.getID_Listenkandidatur()) == null) {
              throw new ImportException(
                  Messages
                      .bind(MessageKeys.Error_KeineStimmergebnisseFuerListenkandidat_0_in_1_gefunden,
                          lk.getPersonendaten().getAnzeigeName(),
                          gg.getGebiet().getName()));
            }
            GruppeComposite.Listenkandidat lkComp = gruppeComp.new Listenkandidat(lk
                .getPersonendaten().getDetails(), lk.getListenplatz(), lk.getID_Listenkandidatur());
            lkComp.setStimmen(votesPerCandidate.getVotes(lk.getID_Listenkandidatur()));
            listeComp.addKandidat(lkComp);
          }
          gruppeComp.addListe(listeComp);
        }
        wahldaten.addGruppe(gruppeComp);
      }
      return wahldaten;
    } catch (FinderException e) {
      throw new ImportException(Messages.getString(MessageKeys.Error_FehlerBeimFuellenDerWahldaten));
    }
  }

  /**
   * Updates voting results in Wahldaten object
   * 
   * @param wahldaten
   * @return
   * @throws ImportException
   */
  private Wahldaten updateWahldaten(Wahldaten wahldaten, String id_Ergebniseingang)
      throws ImportException {
    for (GruppeComposite gruppeComp : wahldaten.getGruppen()) {
      for (GruppeComposite.Liste listeComp : gruppeComp.getListen()) {
        VotesPerCandidate votesPerCandidate = getVotesHandling()
            .getVotesPerCandidate(id_Ergebniseingang, listeComp.getId_GruppeGebietsspezifisch());
        for (Listenkandidat lkComp : listeComp.getKandidaten()) {
          lkComp.setStimmen(votesPerCandidate.getVotes(lkComp.getId_Listenkandidatur()));
        }
      }
    }
    return wahldaten;
  }

  /**
   * Initialize LotingCallback for algorithm with all conflicts already solved
   * 
   * @param id_Ergebniseingang
   * @return Loting callback
   */
  private DrawingLotsCallbackImpl initLotingCallback(final String id_Ergebniseingang,
      ElectionAndCandidatesAndVotes ecv) {
    try {
      List<Konflikt> conflicts = new ArrayList<Konflikt>(getKonfliktHome()
          .findAllByErgebniseingangOrderByNummer(id_Ergebniseingang));
      DrawingLotsCallbackImpl lotingCallback = new DrawingLotsCallbackImpl();
      for (Konflikt conflict : conflicts) {
        List<DrawingLotsAlternative> alternatives = new ArrayList<DrawingLotsAlternative>();
        int indexSelectedAlternative = conflict.getLosAlternative().getNummer();
        for (Alternative alternative : conflict.getAlternativeCol()) {
          // Initialize alternative object
          String id_Listenkombination = alternative.getID_Listenkombination();
          String id_Gruppe = alternative.getID_Gruppe();
          String id_Liste = alternative.getID_Liste();
          String id_Personendaten = alternative.getID_Personendaten();
          if (id_Listenkombination != null) {
            CombinedListKey key = new CombinedListKey(id_Listenkombination);
            CombinedList combinedList = ecv.getCombinedList(key);
            alternatives.add(combinedList);
          } else if (id_Personendaten != null) {
            CandidateKey key = new CandidateKey(id_Personendaten);
            Candidate candidate = ecv.getCandidatesMap().get(key);
            P3ListKey p3ListKey = new P3ListKey(id_Gruppe);
            P3List p3List = ecv.getP3ListsMap().get(p3ListKey);
            alternatives.add(new P3ListCandidate(p3List, candidate));
          } else if (id_Gruppe != null) {
            P3ListKey key = new P3ListKey(id_Gruppe);
            P3List p3List = ecv.getP3ListsMap().get(key);
            alternatives.add(p3List);
          } else if (id_Liste != null) {
            P2ListKey key = new P2ListKey(id_Liste);
            P2List p2List = ecv.getP2ListsMap().get(key);
            alternatives.add(p2List);
          } else {
            throw new IllegalArgumentException();
          }

        }
        lotingCallback.addDecision(DecisionType.byId(conflict.getKonfliktart()),
            alternatives,
            indexSelectedAlternative);
      }
      return lotingCallback;
    } catch (Exception e) {
      LOGGER.error(Messages.getString(MessageKeys.Error_FehlerBeiBerechnungDerSitzverteilung), e);
      throw new EJBException(e.getMessage(), e);
    }
  }

  /**
   * Calculation successful, write results to DB
   * 
   * @param electionResult
   */
  private void writeElectionResult(final ElectionResultImpl electionResult) {
    try {
      // Before saving delete obsolete results
      deleteResults();
      // Write down models, which need no further handling
      _importHandling.createEntities(electionResult.getModels());
      // Fill models from temporary objects
      writeListenkandidaturErgebnisse(electionResult.getKandidatenErgebnisse(),
          electionResult.getId_Ergebniseingang());
      writeSitzverteilung(electionResult.getSitze(), electionResult.getId_Ergebniseingang());
    } catch (ImportException e) {
      throw new EJBException(
          Messages.getString(MessageKeys.Error_FehlerBeimSpeichernDerSitzberechnungsergebnisse)
              + e.getMessage(), e);
    } catch (RemoveException e) {
      throw new EJBException(
          Messages.getString(MessageKeys.Error_FehlerBeimSpeichernDerSitzberechnungsergebnisse)
              + e.getMessage(), e);
    } catch (FinderException e) {
      throw new EJBException(
          Messages.getString(MessageKeys.Error_FehlerBeimSpeichernDerSitzberechnungsergebnisse)
              + e.getMessage(), e);
    }
  }

  /**
   * Delete old results before inserting the new
   * 
   * @throws RemoveException
   * @throws FinderException
   */
  private void deleteResults() throws RemoveException, FinderException {
    removeBesonderheiten(getBesonderheitHome().findAll());
    removeListenkandidaturErgebnisCol(getListenkandidaturErgebnisHome().findAll());
    removeListenplatzNeuCol(getListenplatzNeuHome().findAll());
    removeRestsitzverteilungen(getRestsitzverteilungHome().findAll());
    removeSitzberechnungErgebnisCol(getSitzberechnungErgebnisHome().findAll());
    removeSitzverteilungCol(getSitzverteilungHome().findAll());
    removeUnterverteilungCol(getUnterverteilungHome().findAll());
  }

  private void removeBesonderheiten(Collection<Besonderheit> besCol) throws RemoveException {
    for (Besonderheit bes : besCol) {
      bes.remove();
    }
  }

  private void removeListenkandidaturErgebnisCol(Collection<ListenkandidaturErgebnis> lkECol)
      throws RemoveException {
    for (ListenkandidaturErgebnis lkE : lkECol) {
      lkE.remove();
    }
  }

  private void removeListenplatzNeuCol(Collection<ListenplatzNeu> lPlNeuCol) throws RemoveException {
    for (ListenplatzNeu lPlNeu : lPlNeuCol) {
      lPlNeu.remove();
    }
  }

  private void removeSitzverteilungCol(Collection<Sitzverteilung> sitzverteilungCol)
      throws RemoveException {
    for (Sitzverteilung sitzverteilung : sitzverteilungCol) {
      sitzverteilung.remove();
    }
  }

  private void removeSitzberechnungErgebnisCol(Collection<SitzberechnungErgebnis> sitzberechnungErgebnisCol)
      throws RemoveException {
    for (SitzberechnungErgebnis sbE : sitzberechnungErgebnisCol) {
      sbE.remove();
    }
  }

  private void removeRestsitzverteilungen(Collection<Restsitzverteilung> rCol)
      throws RemoveException {
    for (Restsitzverteilung r : rCol) {
      for (DHondtQuotient dHQ : r.getDHondtQuotientCol()) {
        dHQ.remove();
      }
      r.remove();
    }
  }

  private void removeUnterverteilungCol(Collection<Unterverteilung> unterverteilungCol)
      throws RemoveException {
    for (Unterverteilung unterverteilung : unterverteilungCol) {
      unterverteilung.remove();
    }
  }

  /**
   * Fill state of seat distribution with the information of the conflict and the possible
   * alternatives.
   */
  private void fillSitzverteilungStatus(final Decision conflict,
      final String id_Ergebniseingang,
      final int anzKonflikte,
      final SitzverteilungStatus status) {
    String id_Konflikt = EJBUtil.getUniqueKey();
    KonfliktModel konflikt = new KonfliktModelImpl(id_Konflikt);
    konflikt.setID_Ergebniseingang(id_Ergebniseingang);
    konflikt.setKonfliktart(conflict.getType().getId());
    konflikt.setNummer(anzKonflikte + 1);
    List<AlternativeModel> alternativen = new ArrayList<AlternativeModel>();
    int idx = 0;
    for (DrawingLotsAlternative alternative : conflict.getAlternatives()) {
      alternativen.add(createAlternativeModel(alternative, id_Konflikt, idx++));
    }
    status.setKonflikt(konflikt);
    for (AlternativeModel alternative : alternativen) {
      status.addAlternaltive(alternative, getAlternativenText(alternative, id_Ergebniseingang));
    }
  }

  /**
   * Create alternative model
   * 
   * @param alternativeAlg alternative delivered by the algorithm module
   * @param id_Konflikt database reference to conflict
   * @return database model object
   */
  private AlternativeModel createAlternativeModel(final DrawingLotsAlternative alternativeAlg,
      final String id_Konflikt,
      final int index) {
    AlternativeModel alternative = new AlternativeModelImpl(getUniqueKey());
    alternative.setID_Konflikt(id_Konflikt);
    alternative.setNummer(index);

    DrawingLotsIdentifier identifier = alternativeAlg.getIdentifier();
    alternative.setID_Liste(toString(identifier.getExternalP2ListKey()));
    alternative.setID_Gruppe(toString(identifier.getExternalP3ListKey()));
    alternative.setID_Listenkombination(toString(identifier.getExternalCombinedListKey()));
    alternative.setID_Personendaten(toString(identifier.getExternalCandidateKey()));

    return alternative;
  }

  /**
   * @return
   */
  private String toString(Object externalKey) {
    return externalKey == null ? null : externalKey.toString();
  }

  /**
   * Write results of list candidature
   * 
   * @param kandidatenErgebnisse
   * @param id_Ergebniseingang
   */
  private void writeListenkandidaturErgebnisse(final List<ListenkandidaturErgebnisTmpModel> kandidatenErgebnisse,
      String id_Ergebniseingang) {
    ListenkandidaturErgebnisHome lkeHome = (ListenkandidaturErgebnisHome) IVUBeanBase
        .findLocalHomeExt("ListenkandidaturErgebnis"); //$NON-NLS-1$
    ListenkandidaturHome lkHome = (ListenkandidaturHome) IVUBeanBase
        .findLocalHomeExt("Listenkandidatur"); //$NON-NLS-1$
    ListenplatzNeuHome lpNeuHome = (ListenplatzNeuHome) IVUBeanBase
        .findLocalHomeExt("ListenplatzNeu"); //$NON-NLS-1$
    Map<String, Boolean> listenplatzGeaendert = new HashMap<String, Boolean>();
    try {
      for (ListenkandidaturErgebnisTmpModel lkTmp : kandidatenErgebnisse) {
        try {
          Listenkandidatur lk = lkHome.findByListeAndListenplatz(lkTmp.getID_Liste(),
              lkTmp.getListenplatzAlt());
          lkTmp.setID_Listenkandidatur(lk.getID_Listenkandidatur());
          String id_Liste = lk.getID_Liste();
          if (lkTmp.getListenplatz() != lkTmp.getListenplatzAlt() && lkTmp.getListenplatz() > 0) {
            listenplatzGeaendert.put(id_Liste, Boolean.TRUE);
          } else {
            if (!listenplatzGeaendert.containsKey(id_Liste)) {
              listenplatzGeaendert.put(id_Liste, Boolean.FALSE);
            }
          }
          lkeHome.create(lkTmp);
        } catch (FinderException e) {
          LOGGER.error(Messages
              .bind(MessageKeys.Error_ListenkandidaturAufListe_0_Platz_1_NichtGefunden,
                  lkTmp.getID_Liste(),
                  lkTmp.getListenplatzAlt()));
        }
      }
      for (String id_Liste : listenplatzGeaendert.keySet()) {
        ListenplatzNeuModel lpNeu = new ListenplatzNeuModelImpl(EJBUtil.getUniqueKey());
        lpNeu.setID_Ergebniseingang(id_Ergebniseingang);
        lpNeu.setID_Liste(id_Liste);
        lpNeu.setGeaendert(listenplatzGeaendert.get(id_Liste));
        lpNeuHome.create(lpNeu);
      }
    } catch (CreateException e) {
      throw new EJBException(
          Messages.getString("Error_FehlerBeimAnlegenVonListenkandidaturErgebnis")); //$NON-NLS-1$
    }
  }

  /**
   * Write seat distribution
   * 
   * @param listenSitze
   * @param id_Ergebniseingang
   */
  private void writeSitzverteilung(final List<SitzverteilungModel> listenSitze,
      final String id_Ergebniseingang) {
    try {
      Map<String, Integer> _gruppenSitze = new HashMap<String, Integer>();
      Map<String, Integer> _lkSitze = new HashMap<String, Integer>();
      Map<String, String> _lk4gruppe = new HashMap<String, String>();
      for (SitzverteilungModel lkTmp : listenSitze) {
        Sitzverteilung sv = getSitzverteilungHome().create(lkTmp);
        String id_Gruppe = sv.getListe().getGruppe().getID_Gruppe();
        sv.setID_Gruppe(id_Gruppe);
        if (!_gruppenSitze.containsKey(id_Gruppe)) {
          _gruppenSitze.put(id_Gruppe, sv.getSitzeGesamtzahl());
        } else {
          int anzSitze = _gruppenSitze.get(id_Gruppe) + sv.getSitzeGesamtzahl();
          _gruppenSitze.put(id_Gruppe, anzSitze);
        }
        String id_Listenkombination = sv.getListe().getGruppe().getID_Listenkombination();
        if (id_Listenkombination != null) {
          _lk4gruppe.put(id_Gruppe, id_Listenkombination);
          sv.setID_Listenkombination(id_Listenkombination);
          if (!_lkSitze.containsKey(id_Listenkombination)) {
            _lkSitze.put(id_Listenkombination, sv.getSitzeGesamtzahl());
          } else {
            int anzSitze = _lkSitze.get(id_Listenkombination) + sv.getSitzeGesamtzahl();
            _lkSitze.put(id_Listenkombination, anzSitze);
          }
        }
      }
      for (String id_Gruppe : _gruppenSitze.keySet()) {
        createSitzverteilung(id_Ergebniseingang,
            id_Gruppe,
            _lk4gruppe.get(id_Gruppe),
            _gruppenSitze.get(id_Gruppe));
      }
      for (String id_Listenkombination : _lkSitze.keySet()) {
        createSitzverteilung(id_Ergebniseingang,
            null,
            id_Listenkombination,
            _lkSitze.get(id_Listenkombination));
      }
    } catch (CreateException e) {
      LOGGER.error(Messages.getString(MessageKeys.Error_AnlegenDerSitzverteilungenSchlugFehl));
      throw new EJBException(
          Messages.getString(MessageKeys.Error_AnlegenDerSitzverteilungenSchlugFehl), e);
    }
  }

  /**
   * create seat distribution
   * 
   * @param id_Ergebniseingang
   * @param id_Gruppe
   * @param id_Listenkombination
   * @param sitze
   * @throws EJBException
   * @throws CreateException
   */
  private void createSitzverteilung(final String id_Ergebniseingang,
      final String id_Gruppe,
      final String id_Listenkombination,
      final int sitze) throws EJBException, CreateException {
    SitzverteilungModel sv = new SitzverteilungModelImpl(EJBUtil.getUniqueKey());
    sv.setID_Ergebniseingang(id_Ergebniseingang);
    sv.setID_Liste(null);
    sv.setID_Gruppe(id_Gruppe);
    sv.setID_Listenkombination(id_Listenkombination);
    sv.setSitzeGesamtzahl(sitze);
    getSitzverteilungHome().create(sv);
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.auswertung.sv.SitzverteilungHandling#getBesonderheiten(java.lang.String)
   */
  public Besonderheiten getBesonderheiten(String id_Ergebniseingang) throws EJBException {
    Besonderheiten result = new Besonderheiten(id_Ergebniseingang);
    // add conflicts
    try {
      List<Konflikt> conflicts = new ArrayList<Konflikt>(getKonfliktHome()
          .findAllByErgebniseingangOrderByNummer(id_Ergebniseingang));
      for (Konflikt conflict : conflicts) {
        List<String> alternatives = new ArrayList<String>();
        int indexSelectedAlternative = conflict.getLosAlternative().getNummer();
        for (Alternative alternative : conflict.getAlternativeCol()) {
          alternatives.add(getAlternativenText(alternative, id_Ergebniseingang));
        }
        result.addKonflikt(conflict.getKonfliktart(), alternatives, indexSelectedAlternative);
      }

      List<Besonderheit> besCol = new ArrayList<Besonderheit>(getBesonderheitHome()
          .findAllByErgebniseingang(id_Ergebniseingang));
      Collections.sort(besCol, new Comparator<Besonderheit>() {
        @Override
        public int compare(Besonderheit x, Besonderheit y) {
          return x.getNummer() - y.getNummer();
        }
      });
      List<String> besonderheitenTexte = new ArrayList<String>();
      for (Besonderheit bes : besCol) {
        besonderheitenTexte.add(bes.getText());
      }
      result.addBesonderheiten(besonderheitenTexte);

      List<Personendaten> persons = new ArrayList<Personendaten>(getPersonendatenHome()
          .findAllByBenennbar(false));
      for (Personendaten person : persons) {
        String name = person.getAnzeigeName();
        // Collect groups of the candidate
        Map<Integer, String> groups = new HashMap<Integer, String>();
        for (Listenkandidatur lk : person.getListenkandidaturCol()) {
          String gruppenName = lk.getListe().getGruppe().getNameLang();
          int gruppenSchluessel = lk.getListe().getGruppe().getSchluessel();
          String gruppeForDisplay = "lijst " + gruppenSchluessel;
          if (!isEmpty(gruppenName)) {
            gruppeForDisplay = gruppeForDisplay + ", " + gruppenName;
          }
          groups.put(lk.getListenplatz(), gruppeForDisplay);
        }
        for (Map.Entry<Integer, String> group : groups.entrySet()) {
          String candidateStr = name + " (" + group.getValue() + ", pos. " + group.getKey() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          result.addDeceasedCandidate(candidateStr);
        }
      }
    } catch (Exception e) {
      LOGGER.error(Messages.getString(MessageKeys.Error_FehlerBeiBerechnungDerSitzverteilung), e);
      throw new EJBException(e.getMessage(), e);
    }
    return result;
  }

  /**
   * Text for displaying an alternative. This may be a combined list, a party, a set of identical
   * lists (stel) or a candidate.
   */
  private String getAlternativenText(final AlternativeModel alternative, String id_Ergebniseingang) {
    try {
      if (alternative.getID_Listenkombination() != null) {
        return "Error alternative.getID_Listenkombination() must always be null"; //$NON-NLS-1$
      } else if (alternative.getID_Personendaten() != null) {
        Personendaten person = getPersonendatenHome()
            .findByPrimaryKey(alternative.getID_Personendaten());
        Gruppe gruppe = getGruppeHome().findByPrimaryKey(alternative.getID_Gruppe());
        return person.getAnzeigeName() + ", " + getGruppeText(gruppe, false); //$NON-NLS-1$
      } else if (alternative.getID_Gruppe() != null) {
        Gruppe gruppe = getGruppeHome().findByPrimaryKey(alternative.getID_Gruppe());
        return getGruppeText(gruppe, true);
      } else if (alternative.getID_Liste() != null) {
        return getTextForAlternativeP2List(alternative);
      }
      return Messages.getString(MessageKeys.Error_ErrorNoConflictInformation);
    } catch (ObjectNotFoundException e) {
      throw new EJBException(
          Messages.getString(MessageKeys.Error_FehlerBeimSchreibenDesKonfliktes), e);
    } catch (FinderException e) {
      throw new EJBException(
          Messages.getString(MessageKeys.Error_FehlerBeimSchreibenDesKonfliktes), e);
    }
  }

  private String getTextForAlternativeP2List(final AlternativeModel alternative)
      throws ObjectNotFoundException, FinderException {
    Liste list = getListeHome().findByPrimaryKey(alternative.getID_Liste());
    String alternativtext = list.getName();
    if (alternativtext == null || alternativtext.length() == 0) {
      alternativtext = "Lijst" + BLANK + list.getGruppe().getSchluessel();
    }
    if (list.getSatz() != 0) {
      alternativtext += BLANK + "Stel" + BLANK + list.getSatz();
    } else {
      Gebiet gebiet = list.getGruppeGebietsspezifischCol().iterator().next().getGebiet();
      alternativtext += BLANK + Gebietsart.getGebietsartKlartext(gebiet) + BLANK
          + gebiet.getNumber4Display();
    }
    return alternativtext;
  }

  private String getGruppeText(Gruppe gruppe, boolean capital) {
    return (capital ? "Lijst" : "lijst") + BLANK + gruppe.getSchluessel() + " (" + gruppe.getNameKurz() + ")"; //$NON-NLS-3$ //$NON-NLS-4$
  }

  /*
   * @see
   * de.ivu.wahl.auswertung.sv.SitzverteilungHandling#writeKonfliktWithLosAlternative(de.ivu.wahl
   * .auswertung.sv.SitzverteilungStatus, java.lang.String)
   */
  public void writeKonfliktWithLosAlternative(final SitzverteilungStatus status,
      final String id_LosAlternative) throws EJBException {
    try {
      Konflikt konflikt = getKonfliktHome().create(status.getKonflikt());
      for (SitzverteilungStatus.Alternative alternative : status.getAlternativen()) {
        getAlternativeHome().create(alternative.getAlternativeModel());
      }
      // Setting allotting result
      konflikt.setID_LosAlternative(id_LosAlternative);
    } catch (CreateException e) {
      LOGGER.error(e);
      throw new EJBException(
          Messages.getString(MessageKeys.Error_ErrorWritingConflictAndAlternativeObjectsToDB), e);
    }
  }

  AlternativeHome _alternativeHome = null;

  /**
   * Get back alternative
   * 
   * @return alternativeHome.
   */
  public AlternativeHome getAlternativeHome() {
    if (_alternativeHome == null) {
      _alternativeHome = (AlternativeHome) IVUBeanBase.findLocalHomeExt("Alternative"); //$NON-NLS-1$
    }
    return _alternativeHome;
  }

  GruppeHome _gruppeHome = null;

  /**
   * Get back gruppeHome
   * 
   * @return alternativeHome.
   */
  private GruppeHome getGruppeHome() {
    if (_gruppeHome == null) {
      _gruppeHome = (GruppeHome) IVUBeanBase.findLocalHomeExt("Gruppe"); //$NON-NLS-1$
    }
    return _gruppeHome;
  }

  @EJB
  private GebietHandling _gebietHandling;

  @EJB
  private ImportHandling _importHandling;

  KonfliktHome _konfliktHome = null;

  /**
   * Get back konfliktHome
   * 
   * @return alternativeHome.
   */
  private KonfliktHome getKonfliktHome() {
    if (_konfliktHome == null) {
      _konfliktHome = (KonfliktHome) IVUBeanBase.findLocalHomeExt("Konflikt"); //$NON-NLS-1$
    }
    return _konfliktHome;
  }

  ListenkombinationHome _listenkombinationHome = null;

  /**
   * Get back ListenkombinationHome
   */
  private ListenkombinationHome getListenkombinationHome() {
    if (_listenkombinationHome == null) {
      _listenkombinationHome = (ListenkombinationHome) findLocalHome("Listenkombination"); //$NON-NLS-1$
    }
    return _listenkombinationHome;
  }

  UniCacheHome _uniCacheHome = null;

  private UniCacheHome getUniCacheHome() {
    if (_uniCacheHome == null) {
      _uniCacheHome = (UniCacheHome) findLocalHome("UniCache"); //$NON-NLS-1$      
    }
    return _uniCacheHome;
  }

}