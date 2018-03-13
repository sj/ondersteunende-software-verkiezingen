/*
 * ElectionResultImpl
 * 
 * Created on 04.02.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.result.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Category;

import de.ivu.ejb.EJBUtil;
import de.ivu.ejb.bmp.Model;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.modell.BesonderheitConstants;
import de.ivu.wahl.modell.BesonderheitModel;
import de.ivu.wahl.modell.DHondtQuotientModel;
import de.ivu.wahl.modell.FiktiveSitzverteilungModel;
import de.ivu.wahl.modell.ListenkandidaturErgebnisTmpModel;
import de.ivu.wahl.modell.ListenkombinationZulassungModel;
import de.ivu.wahl.modell.RestsitzverteilungModel;
import de.ivu.wahl.modell.SitzberechnungErgebnisModel;
import de.ivu.wahl.modell.SitzverteilungModel;
import de.ivu.wahl.modell.UnterverteilungModel;
import de.ivu.wahl.modell.impl.BesonderheitModelImpl;
import de.ivu.wahl.modell.impl.DHondtQuotientModelImpl;
import de.ivu.wahl.modell.impl.FiktiveSitzverteilungModelImpl;
import de.ivu.wahl.modell.impl.ListenkandidaturErgebnisTmpModelImpl;
import de.ivu.wahl.modell.impl.ListenkombinationZulassungModelImpl;
import de.ivu.wahl.modell.impl.RestsitzverteilungModelImpl;
import de.ivu.wahl.modell.impl.SitzberechnungErgebnisModelImpl;
import de.ivu.wahl.modell.impl.SitzverteilungModelImpl;
import de.ivu.wahl.modell.impl.UnterverteilungModelImpl;
import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.determination.GeneralList;
import de.ivu.wahl.result.determination.P2List;
import de.ivu.wahl.result.determination.P3List;
import de.ivu.wahl.result.determination.P42List;
import de.ivu.wahl.result.drawlots.Decision;
import de.ivu.wahl.result.drawlots.DecisionType;
import de.ivu.wahl.result.drawlots.DrawingLotsAlternative;

/**
 * Receives the results of the seat calculation from the algorithm that performs the calculation.
 * 
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public class ElectionResultImpl implements ElectionResult {

  private static final Category LOGGER = Log4J.configure(ElectionResultImpl.class);

  private final List<Model> _models = new ArrayList<Model>();
  private final List<SitzverteilungModel> _sitze = new ArrayList<SitzverteilungModel>();
  private final List<ListenkandidaturErgebnisTmpModel> _kandidatenErgebnisse = new ArrayList<ListenkandidaturErgebnisTmpModel>();
  private Decision _decision;
  private int _numberOfUnassignedSeatsBeforeDHondt;

  private final String _id_Ergebniseingang;

  public ElectionResultImpl(final String id_Ergebniseingang) {
    _id_Ergebniseingang = id_Ergebniseingang;
  }

  public List<Model> getModels() {
    return _models;
  }

  public List<SitzverteilungModel> getSitze() {
    return _sitze;
  }

  public List<ListenkandidaturErgebnisTmpModel> getKandidatenErgebnisse() {
    return _kandidatenErgebnisse;
  }

  public String getId_Ergebniseingang() {
    return _id_Ergebniseingang;
  }

  @Override
  public void setFictitiousDistributionResult(Map<P3List, Long> listsAndSeats) {
    for (P3List p3List : listsAndSeats.keySet()) {
      FiktiveSitzverteilungModel fs = new FiktiveSitzverteilungModelImpl(EJBUtil.getUniqueKey());
      fs.setID_Ergebniseingang(_id_Ergebniseingang);
      fs.setID_Gruppe(p3List.getExternalKey().toString());
      int seats = Integer.valueOf(listsAndSeats.get(p3List).intValue());
      fs.setSitzeGesamtzahl(seats);
      _models.add(fs);
    }
  }

  /**
   * Creates a persistent {@link ListenkombinationZulassungModel}
   */
  @Override
  public void checkedCombinedList(CheckedCombinedList parameterObject) {
    String id_Listenkombination = parameterObject._combinedList.getExternalKey().toString();
    // Are the lists approved?
    // This is only the case if they are over KT and if the combined list is valid.
    addZulassungListenkombination(parameterObject._listsOverKT,
        id_Listenkombination,
        parameterObject._valid);
    addZulassungListenkombination(parameterObject._listsUnderKT, id_Listenkombination, false);
    // Is the combination still valid?
    ListenkombinationZulassungModel lkZulassung = new ListenkombinationZulassungModelImpl(
        EJBUtil.getUniqueKey());
    lkZulassung.setID_Ergebniseingang(_id_Ergebniseingang);
    lkZulassung.setID_Listenkombination(id_Listenkombination);
    lkZulassung.setZugelassen(parameterObject._valid);
    _models.add(lkZulassung);
  }

  /**
   * Creates for each element of <code>p3Listen</code> a persistent
   * {@link ListenkombinationZulassungModel}. It indicates that a P3-list is allowed or disallowed
   * to be part of the P42-list.
   */
  private void addZulassungListenkombination(final List<P3List> p3Listen,
      final String id_Listenkombination,
      final boolean zugelassen) {
    for (P3List p3List : p3Listen) {
      ListenkombinationZulassungModel lkZulassung = new ListenkombinationZulassungModelImpl(
          EJBUtil.getUniqueKey());
      lkZulassung.setID_Ergebniseingang(_id_Ergebniseingang);
      lkZulassung.setID_Listenkombination(id_Listenkombination);
      lkZulassung.setID_Gruppe(p3List.getExternalKey().toString());
      lkZulassung.setZugelassen(zugelassen);
      _models.add(lkZulassung);
    }
  }

  /**
   * Special knowledge about the RestsitzverteilungModel attributes id_Listenkombination, id_Gruppe
   * and id_Liste:
   * <ul>
   * <li>If only id_Listenkombination is set, this is a P42-assignment to a combined list</li>
   * <li>If only id_Gruppe is set, this is a P42-assignment to a lists that is not in a combined
   * list</li>
   * <li>If id_Listenkombination and id_Gruppe are set, this is a P3-assignment.
   * id_Listenkombination denotes the parent.</li>
   * <li>If id_Gruppe and id_Liste are set, this is a P2-assignment. id_Gruppe denotes the parent.</li>
   * <li>Everything else is illegal
   * </ul>
   */
  @Override
  public void addDHondtFractions(GeneralList list,
      GeneralList parent,
      Distribution distribution,
      long priorSeats,
      long noOfDHondtSeats,
      List<DHondtFraction> dHondtFractions,
      long votes) {
    LOGGER.debug("d'HondtFractions for " + list.getClass().getName()); //$NON-NLS-1$

    String id_Liste = null;
    String id_Gruppe = null;
    String id_Listenkombination = null;
    // Determine list type
    if (Distribution.P42.equals(distribution)) {
      id_Listenkombination = getExternalKeyString(list);
      if (id_Listenkombination == null) {
        id_Gruppe = getExternalKeyString(((P42List) list).getP3Lists().iterator().next());
      }
    } else if (Distribution.P3.equals(distribution)) {
      id_Listenkombination = getExternalKeyString(parent);
      id_Gruppe = getExternalKeyString(list);
    } else if (Distribution.P2.equals(distribution)) {
      id_Gruppe = getExternalKeyString(parent);
      id_Liste = getExternalKeyString(list);
    } else {
      throw new RuntimeException("Unexpected list" + list); //$NON-NLS-1$
    }

    String id_Restsitzverteilung = EJBUtil.getUniqueKey();
    RestsitzverteilungModel rv = new RestsitzverteilungModelImpl(id_Restsitzverteilung);
    rv.setID_Ergebniseingang(_id_Ergebniseingang);
    rv.setSitze((int) priorSeats);
    rv.setSitzeRest((int) noOfDHondtSeats);
    rv.setSitzeGesamtZuVerteilen(_numberOfUnassignedSeatsBeforeDHondt); // see OSV-1481
    rv.setID_Liste(id_Liste);
    rv.setID_Gruppe(id_Gruppe);
    rv.setID_Listenkombination(id_Listenkombination);
    rv.setVerteilung(distribution.getId());
    _models.add(rv);

    // Create models for d'Hondt-Numbers
    for (DHondtFraction dHondtFraction : dHondtFractions) {
      DHondtQuotientModel dHondtQuotient = new DHondtQuotientModelImpl(EJBUtil.getUniqueKey());
      dHondtQuotient.setID_Restsitzverteilung(id_Restsitzverteilung);
      dHondtQuotient.setLauf(dHondtFraction.getColumnIndex());
      dHondtQuotient.setNenner((int) dHondtFraction.getDenominator());
      dHondtQuotient.setSitzAusRestanteil(dHondtFraction.receivesSeat());
      dHondtQuotient.setZaehler((int) dHondtFraction.getNumerator());
      _models.add(dHondtQuotient);
    }
  }

  /**
   * Creates a persistent {@link SitzberechnungErgebnisModel} to store the assignment of seats..
   */
  @Override
  public void assignSeats(Assignment assignment) {
    Distribution distribution = assignment.getDistribution();
    String id_Listenkombination = null;
    String id_Gruppe = null;
    String id_Liste = null;

    GeneralList list = assignment.getList();
    GeneralList parent = assignment.getAssignmentParent();
    if (Distribution.P42.equals(distribution)) {
      id_Listenkombination = getExternalKeyString(list);
      if (id_Listenkombination == null) {
        P3List p3List = ((P42List) list).getP3Lists().iterator().next();
        id_Gruppe = getExternalKeyString(p3List);
      }
      if (parent != null) {
        LOGGER.info("Parent of P42 is " + parent.getClass().getName()); //$NON-NLS-1$
      }
    } else if (Distribution.P3.equals(distribution)) {
      id_Listenkombination = getExternalKeyString(parent);
      id_Gruppe = getExternalKeyString(list);
    } else if (Distribution.P2.equals(distribution)) {
      id_Gruppe = getExternalKeyString(parent);
      id_Liste = getExternalKeyString(list);
    } else {
      throw new IllegalArgumentException();
    }

    SitzberechnungErgebnisModel sbe = new SitzberechnungErgebnisModelImpl(EJBUtil.getUniqueKey());
    sbe.setVerteilung(assignment.getDistribution().getId());
    sbe.setID_Ergebniseingang(_id_Ergebniseingang);
    sbe.setSchritttyp(assignment.getAssignmentType().getId());
    sbe.setSchrittnummer(assignment.getIndex());
    sbe.setID_Liste(id_Liste);
    sbe.setID_Gruppe(id_Gruppe);
    sbe.setID_Listenkombination(id_Listenkombination);
    sbe.setLosentscheid(assignment.isByLot());
    sbe.setSitze((int) assignment.getSeats());

    AssignmentSupplement supplement = assignment.getSupplement();
    if (supplement instanceof AssignmentSupplementVotes) {
      AssignmentSupplementVotes sup = (AssignmentSupplementVotes) supplement;
      sbe.setZaehler(sup.getVotes());
    }
    if (supplement instanceof AssignmentSupplementWithRemainderFraction) {
      AssignmentSupplementWithRemainderFraction sup = (AssignmentSupplementWithRemainderFraction) supplement;
      int remainderDenominator = (int) sup.getRemainder().getDenominator();
      int remainderNumerator = (int) sup.getRemainder().getNumerator();
      sbe.setZaehlerVomRest(remainderNumerator);
      sbe.setNennerVomRest(remainderDenominator);
    }

    _models.add(sbe);
  }

  @Override
  public void addAnomalitiy(Anomaly anomaly) {
    BesonderheitModel bes = new BesonderheitModelImpl(EJBUtil.getUniqueKey());
    bes.setID_Ergebniseingang(_id_Ergebniseingang);
    bes.setNummer(anomaly.getNumber());
    bes.setText(anomaly.getText());
    bes.setBesonderheitart(anomaly.isShallBePublishedInModelP22()
        ? BesonderheitConstants.ART_SHALL_BE_PUBLISHED_IN_P22
        : BesonderheitConstants.ART_SHALL_NOT_BE_PUBLISHED_IN_P22);

    _models.add(bes);
  }

  /**
   * @param list
   * @return
   */
  private String getExternalKeyString(GeneralList list) {
    if (list == null) {
      return null;
    } else {
      Object externalKey = list.getExternalKey();
      if (externalKey == null) {
        return null;
      } else {
        return externalKey.toString();
      }
    }
  }

  /**
   * CAUTION: Here the assignment step is mis-used to store some information. The step number is 0
   * and the numbers of seats and votes of a P42-list is stored.
   * 
   * @see de.ivu.wahl.result.result.ElectionResult#subDistributionStarted(GeneralList, long, long,
   *      Distribution)
   */
  @Override
  public void subDistributionStarted(final GeneralList p42OrP3List,
      final long votes,
      final long seats,
      Distribution distribution) {

    UnterverteilungModel uv = new UnterverteilungModelImpl(EJBUtil.getUniqueKey());
    uv.setID_Ergebniseingang(_id_Ergebniseingang);
    uv.setSitze((int) seats);
    uv.setStimmen((int) votes);
    if (Distribution.P3.equals(distribution)) {
      uv.setID_Listenkombination(getExternalKeyString(p42OrP3List));
      uv.setID_Gruppe(null);
    } else if (Distribution.P2.equals(distribution)) {
      uv.setID_Listenkombination(null);
      uv.setID_Gruppe(getExternalKeyString(p42OrP3List));
    } else {
      throw new IllegalArgumentException("Distribution must be either P2 or P3."); //$NON-NLS-1$
    }
    _models.add(uv);
  }

  /**
   * Creates a persistent {@link SitzverteilungModel} to store the number of seats of a P2-list
   */
  @Override
  public void totalSeatsAssigned(final P2List p2List, final long seats) {
    SitzverteilungModel sv = new SitzverteilungModelImpl(EJBUtil.getUniqueKey());
    sv.setID_Ergebniseingang(_id_Ergebniseingang);
    sv.setID_Liste(getExternalKeyString(p2List));
    sv.setSitzeGesamtzahl((int) seats);
    _sitze.add(sv);
  }

  /**
   * Creates a persistent {@link ListenkandidaturErgebnisTmpModel}
   * 
   * @see de.ivu.wahl.result.result.ElectionResult#candidateResult(de.ivu.wahl.result.determination.P2List,
   *      de.ivu.wahl.result.Candidate, int, int, boolean, boolean, boolean, boolean, boolean)
   */
  public void candidateResult(final P2List p2List,
      final Candidate candidate,
      final int newPositionInList,
      final int positionInList,
      final boolean tookPartInAllotting,
      final boolean wasAllotted,
      final boolean abovePreferencialBarrier,
      final boolean isElected,
      final boolean isDeceased) {
    ListenkandidaturErgebnisTmpModel erg = new ListenkandidaturErgebnisTmpModelImpl(
        EJBUtil.getUniqueKey(), getExternalKeyString(p2List), positionInList);
    erg.setID_Ergebniseingang(_id_Ergebniseingang);
    erg.setListenplatz(newPositionInList);
    erg.setGewaehlt(isElected);
    erg.setLosteilnehmer(tookPartInAllotting);
    erg.setLosgewinner(wasAllotted);
    erg.setBevorzugtGewaehlt(abovePreferencialBarrier);
    _kandidatenErgebnisse.add(erg);
  }

  @Override
  public void candidateResult(CandidateResult cr) {
    ListenkandidaturErgebnisTmpModel erg = new ListenkandidaturErgebnisTmpModelImpl(
        EJBUtil.getUniqueKey(), getExternalKeyString(cr.getP2List()), cr.getOldListPosition());
    erg.setID_Ergebniseingang(_id_Ergebniseingang);
    erg.setListenplatz(cr.getNewListPosition());
    erg.setGewaehlt(cr.isElectedHere());
    erg.setGewaehltInGruppe(cr.isElectedOnListGroup());
    erg.setLosteilnehmer(cr.tookPartInDrawingLots());
    erg.setLosgewinner(cr.wasDrawnByLot());
    erg.setBevorzugtGewaehlt(cr.isAbovePreferentialBarrier());
    _kandidatenErgebnisse.add(erg);
  }

  @Override
  public void setConflict(final DecisionType type,
      final List<? extends DrawingLotsAlternative> alternatives) {
    _decision = new Decision(type, alternatives, -1);
  }

  public Decision getConflict() {
    return _decision;
  }

  @Override
  public void setNumberOfUnassignedSeatsBeforeDHondt(int numberOfUnassignedSeatsBeforeDHondt) {
    _numberOfUnassignedSeatsBeforeDHondt = numberOfUnassignedSeatsBeforeDHondt;
  }
}
