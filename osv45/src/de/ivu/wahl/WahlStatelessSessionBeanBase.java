/*
 * WahlStatelessSessionBeanBase
 * 
 * Created on 27.10.2003
 * Copyright (c) 2003-8 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl;

import static de.ivu.ejb.EJBUtil.lookupLocal;

import javax.ejb.EJBException;

import org.apache.log4j.Category;

import de.ivu.ejb.SessionBeanBase;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.admin.AdminHandling;
import de.ivu.wahl.admin.AdminHandlingBean;
import de.ivu.wahl.anwender.AnwenderHandling;
import de.ivu.wahl.anwender.AnwenderHandlingBean;
import de.ivu.wahl.auswertung.AuswertungHandling;
import de.ivu.wahl.auswertung.AuswertungHandlingBean;
import de.ivu.wahl.modell.ejb.BesonderheitHome;
import de.ivu.wahl.modell.ejb.FiktiveSitzverteilungHome;
import de.ivu.wahl.modell.ejb.GebietHome;
import de.ivu.wahl.modell.ejb.GebietsstatusHome;
import de.ivu.wahl.modell.ejb.ListeHome;
import de.ivu.wahl.modell.ejb.ListenkandidaturErgebnisHome;
import de.ivu.wahl.modell.ejb.ListenkandidaturHome;
import de.ivu.wahl.modell.ejb.ListenplatzNeuHome;
import de.ivu.wahl.modell.ejb.PersonendatenHome;
import de.ivu.wahl.modell.ejb.RestsitzverteilungHome;
import de.ivu.wahl.modell.ejb.SitzberechnungErgebnisHome;
import de.ivu.wahl.modell.ejb.SitzverteilungHome;
import de.ivu.wahl.modell.ejb.StimmergebnisHome;
import de.ivu.wahl.modell.ejb.UnterverteilungHome;
import de.ivu.wahl.modell.ejb.service.GebietHandling;
import de.ivu.wahl.modell.ejb.service.GebietHandlingBean;
import de.ivu.wahl.modell.ejb.service.GruppeHandling;
import de.ivu.wahl.modell.ejb.service.GruppeHandlingBean;
import de.ivu.wahl.modell.ejb.service.SeatsHandling;
import de.ivu.wahl.modell.ejb.service.SeatsHandlingBean;
import de.ivu.wahl.modell.ejb.service.VotesHandling;
import de.ivu.wahl.modell.ejb.service.VotesHandlingBean;

/**
 * Basisklasse f�r ein Stateless Session Enterprise Java Bean mit wahlspezifischen Methoden
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public abstract class WahlStatelessSessionBeanBase extends SessionBeanBase {

  private static final long serialVersionUID = 5536112318504005093L;
  private final static Category LOGGER = Log4J.configure(WahlStatelessSessionBeanBase.class);
  static {
    LOGGER.info(Log4J.dumpVersion(WahlStatelessSessionBeanBase.class,
        Log4J.extractVersion("$Revision: 1.14 $"))); //$NON-NLS-1$
  }

  private transient AdminHandling _adminHandling;

  /**
   * @return AdminHandling Dienst
   * @throws EJBException
   */
  protected AdminHandling getAdminHandling() throws EJBException {
    if (_adminHandling == null) {
      _adminHandling = lookupLocal(AdminHandlingBean.class.getSimpleName());
    }
    return _adminHandling;
  }

  private transient AnwenderHandling _anwenderHandling;

  /**
   * @return AnwenderHandling Dienst
   * @throws EJBException
   */
  protected AnwenderHandling getAnwenderHandling() throws EJBException {
    if (_anwenderHandling == null) {
      _anwenderHandling = lookupLocal(AnwenderHandlingBean.class.getSimpleName());
    }
    return _anwenderHandling;
  }

  private transient AuswertungHandling _auswertungHandling;

  /**
   * @return AuswertungHandling Dienst
   * @throws EJBException
   */
  public AuswertungHandling getAuswertungHandling() throws EJBException {
    if (_auswertungHandling == null) {
      _auswertungHandling = lookupLocal(AuswertungHandlingBean.class.getSimpleName());
    }
    return _auswertungHandling;
  }

  private transient BesonderheitHome _besHome = null;

  public BesonderheitHome getBesonderheitHome() {
    if (_besHome == null) {
      _besHome = BesonderheitHome.HomeFinder.findHome(this);
    }
    return _besHome;
  }

  private transient FiktiveSitzverteilungHome _fsHome = null;

  public FiktiveSitzverteilungHome getFiktiveSitzverteilungHome() {
    if (_fsHome == null) {
      _fsHome = FiktiveSitzverteilungHome.HomeFinder.findHome(this);
    }
    return _fsHome;
  }

  GebietHandling _gebietHandling = null;

  public GebietHandling getGebietHandling() {
    if (_gebietHandling == null) {
      _gebietHandling = lookupLocal(GebietHandlingBean.class.getSimpleName());
    }
    return _gebietHandling;
  }

  private transient GebietHome _gebietHome = null;

  public GebietHome getGebietHome() {
    if (_gebietHome == null) {
      _gebietHome = (GebietHome) findLocalHome("Gebiet"); //$NON-NLS-1$
    }
    return _gebietHome;
  }

  private transient GebietsstatusHome _gebietsstatusHome = null;

  protected GebietsstatusHome getGebietsstatusHome() {
    if (_gebietsstatusHome == null) {
      _gebietsstatusHome = ((GebietsstatusHome) findLocalHome("Gebietsstatus")); //$NON-NLS-1$
    }
    return _gebietsstatusHome;
  }

  private transient GruppeHandling _gruppeHandling = null;

  public GruppeHandling getGruppeHandling() {
    if (_gruppeHandling == null) {
      _gruppeHandling = lookupLocal(GruppeHandlingBean.class.getSimpleName());
    }
    return _gruppeHandling;
  }

  private transient ListenkandidaturHome _lkHome = null;

  public ListenkandidaturHome getListenkandidaturHome() {
    if (_lkHome == null) {
      _lkHome = ListenkandidaturHome.HomeFinder.findHome(this);
    }
    return _lkHome;
  }

  private transient ListeHome _liHome = null;

  public ListeHome getListeHome() {
    if (_liHome == null) {
      _liHome = ListeHome.HomeFinder.findHome(this);
    }
    return _liHome;
  }

  private transient ListenkandidaturErgebnisHome _lkeHome = null;

  public ListenkandidaturErgebnisHome getListenkandidaturErgebnisHome() {
    if (_lkeHome == null) {
      _lkeHome = ListenkandidaturErgebnisHome.HomeFinder.findHome(this);
    }
    return _lkeHome;
  }

  private transient ListenplatzNeuHome _lpNeuHome = null;

  protected ListenplatzNeuHome getListenplatzNeuHome() {
    if (_lpNeuHome == null) {
      _lpNeuHome = ListenplatzNeuHome.HomeFinder.findHome(this);
    }
    return _lpNeuHome;
  }

  PersonendatenHome _personendatenHome = null;

  public PersonendatenHome getPersonendatenHome() {
    if (_personendatenHome == null) {
      _personendatenHome = PersonendatenHome.HomeFinder.findHome(this);
    }
    return _personendatenHome;
  }

  RestsitzverteilungHome _restsitzverteilungHome = null;

  public RestsitzverteilungHome getRestsitzverteilungHome() {
    if (_restsitzverteilungHome == null) {
      _restsitzverteilungHome = RestsitzverteilungHome.HomeFinder.findHome(this);
    }
    return _restsitzverteilungHome;
  }

  SeatsHandling _seatsHandling = null;

  public SeatsHandling getSeatsHandling() {
    if (_seatsHandling == null) {
      _seatsHandling = lookupLocal(SeatsHandlingBean.class.getSimpleName());
    }
    return _seatsHandling;
  }

  SitzberechnungErgebnisHome _sitzberechnungErgebnisHome = null;

  public SitzberechnungErgebnisHome getSitzberechnungErgebnisHome() {
    if (_sitzberechnungErgebnisHome == null) {
      _sitzberechnungErgebnisHome = SitzberechnungErgebnisHome.HomeFinder.findHome(this);
    }
    return _sitzberechnungErgebnisHome;
  }

  SitzverteilungHome _sitzverteilungHome = null;

  public SitzverteilungHome getSitzverteilungHome() {
    if (_sitzverteilungHome == null) {
      _sitzverteilungHome = SitzverteilungHome.HomeFinder.findHome(this);
    }
    return _sitzverteilungHome;
  }

  private transient StimmergebnisHome _stimmergebnisHome = null;

  public StimmergebnisHome getStimmergebnisHome() {
    if (_stimmergebnisHome == null) {
      _stimmergebnisHome = StimmergebnisHome.HomeFinder.findHome(this);
    }
    return _stimmergebnisHome;
  }

  UnterverteilungHome _uvHome = null;

  public UnterverteilungHome getUnterverteilungHome() {
    if (_uvHome == null) {
      _uvHome = UnterverteilungHome.HomeFinder.findHome(this);
    }
    return _uvHome;
  }

  VotesHandling _votesHandling = null;

  public VotesHandling getVotesHandling() {
    if (_votesHandling == null) {
      _votesHandling = lookupLocal(VotesHandlingBean.class.getSimpleName());
    }
    return _votesHandling;
  }

}
