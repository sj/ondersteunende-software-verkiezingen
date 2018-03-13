/*
 * AuswertungHandlingBean
 *
 * Copyright (c) 2002-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.auswertung;

import static de.ivu.wahl.WahlInfo.getWahlInfo;
import static de.ivu.wahl.modell.GruppeKonstanten.GRUPPENART_PARTEI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import de.ivu.wahl.AnwContext;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.WahlStatelessSessionBeanBase;
import de.ivu.wahl.admin.StateHandling;
import de.ivu.wahl.auswertung.erg.EingangsHistorieErgebnis;
import de.ivu.wahl.auswertung.erg.EingangsHistorieErgebnis.EingangsContainer;
import de.ivu.wahl.auswertung.erg.Status;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ErgebniseingangModel;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.GruppeGebietsspezifischModel;
import de.ivu.wahl.modell.GruppeModel;
import de.ivu.wahl.modell.ejb.Ergebniseingang;
import de.ivu.wahl.modell.ejb.ErgebniseingangHome;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.GebietHome;
import de.ivu.wahl.modell.ejb.Gebietsstatus;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifischHome;
import de.ivu.wahl.modell.ejb.GruppeHome;
import de.ivu.wahl.modell.ejb.Personendaten;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * Wahlabwicklungssystem, Methoden zur Auswertung der Daten und zur Zusammenstellung der Ergebnisse.
 * 
 * @author klie@ivu.de mur@ivu.de cos@ivu.de ugo@ivu.de
 */
@Stateless
@Local(AuswertungHandling.class)
public class AuswertungHandlingBean extends WahlStatelessSessionBeanBase
    implements
      AuswertungHandling {

  private static final long serialVersionUID = -1698028693845863132L;
  private static final Logger LOGGER = Logger.getLogger(AuswertungHandlingBean.class);

  @EJB
  protected StateHandling _stateHandling;

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.auswertung.AuswertungHandling#getStatus(de.ivu.wahl.AnwContext)
   */
  @Override
  public Status getStatus(AnwContext anwContext) throws EJBException {
    WahlInfo info = getWahlInfo(anwContext);
    if (info != null) {
      String name = info.getWahlName();
      ElectionCategory electionCategory = info.getElectionCategory();
      int wahlergebnisart = info.getAktuelleWahlergebnisart();
      boolean geschlossen = _stateHandling.isInputDisabled(anwContext);
      boolean freigegeben = _stateHandling.isFreigegeben(anwContext, wahlergebnisart);
      return new Status(
          "Status", name, Konstanten.APPSTATE_TEST, electionCategory, wahlergebnisart, geschlossen, freigegeben); //$NON-NLS-1$
    } else {
      return null;
    }
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.auswertung.AuswertungHandling#getParteienForWahl(de.ivu.wahl.AnwContext)
   */
  @Override
  public List<GruppeModel> getParteienForWahl(AnwContext anwContext) {
    WahlInfo wahlInfo = getWahlInfo(anwContext);
    wahlInfo.getWahl().readLock();
    try {
      List<GruppeModel> parteien = new ArrayList<GruppeModel>();
      GruppeHome gHome = GruppeHome.HomeFinder.findHome(this);
      for (Gruppe g : gHome.findAllByWahlAndGruppenart(wahlInfo.getID_Wahl(), GRUPPENART_PARTEI)) {
        parteien.add(g.getDetails());
      }
      return parteien;
    } catch (Exception ex) {
      LOGGER.error(Messages
          .getString(MessageKeys.Error_EineListeVonParteienKonnteNichtErstelltWerden), ex);
      throw new EJBException(ex);
    }
  }

  /*
   * (non-Javadoc)
   * @seede.ivu.wahl.auswertung.AuswertungHandling#
   * getUntergeordneteAusstehendeWahleinheitenFuerUebergeordnetenGebiet(de.ivu.wahl.AnwContext,
   * java.lang.String)
   */
  @Override
  public List<GebietModel> getUntergeordneteAusstehendeWahleinheitenFuerUebergeordnetenGebiet(final AnwContext anwContext,
      final String id_UebergeordnetesGebiet) throws EJBException {

    try {
      getWahlInfo(anwContext).getWahl().readLock();
      GebietHome gebietHome = GebietHome.HomeFinder.findHome(this);
      return toModelList(gebietHome
          .findAllIsWahleinheitAusstehendByUebergeordnetesGebiet(id_UebergeordnetesGebiet));
    } catch (Exception ex) {
      LOGGER.error(Messages
          .getString(MessageKeys.Error_EineListeVonWahleinheitsgebietenKonnteNichtErstelltWerden),
          ex);
      throw new EJBException(ex);
    }
  }

  @Override
  public EingangsHistorieErgebnis getEingangshistorie(final String id_Gebiet) {

    EingangsHistorieErgebnis eingangsHistorieErgebnis = new EingangsHistorieErgebnis(
        Messages.getString(MessageKeys.AlleErgebniseingaengeChronologischSortiert));
    ErgebniseingangHome ergebniseingangHome = ErgebniseingangHome.HomeFinder.findHome(this);
    Collection<Ergebniseingang> ergEingaengeByGebiet;
    try {
      ergEingaengeByGebiet = ergebniseingangHome.findAllByIDErfassungseinheit(id_Gebiet);
      for (Ergebniseingang ergebniseingang : ergEingaengeByGebiet) {
        eingangsHistorieErgebnis.addEingang(ergebniseingang.getDetails(), ergebniseingang
            .getErfassungseinheit() != null
            ? ergebniseingang.getErfassungseinheit().getDetails()
            : null);
      }
      eingangsHistorieErgebnis.sortByZeitstempel();
    } catch (FinderException e) {
      e.printStackTrace();
    }

    return eingangsHistorieErgebnis;
  }

  @Override
  public List<EingangsContainer> getEingangsStatus() {
    List<EingangsContainer> eingangsHistorieErgebnisList = new ArrayList<EingangsContainer>();
    WahlInfo wahlInfo = WahlInfo.getWahlInfo();
    try {
      final Collection<Gebiet> findAllByWahlAndGebietsartSortByPosition = getGebietHome()
          .findAllByWahlAndGebietsartSortByPosition(wahlInfo.getID_Wahl(),
              wahlInfo.getGebietsartErfassungseinheit());
      for (Gebiet gebiet : findAllByWahlAndGebietsartSortByPosition) {
        Ergebniseingang lastInput = gebiet.getLastInput(wahlInfo.getAktuelleWahlergebnisart());
        Gebietsstatus gebietsstatus = null;
        if (gebiet.getLetzterGueltigerEingang() != null
            && gebiet.getID_LetzterEingang() != null
            && gebiet.getID_LetzterEingang().equals(gebiet.getLetzterGueltigerEingang()
                .getID_Ergebniseingang())) {
          try {
            gebietsstatus = getGebietsstatusHome()
                .findByErgebniseingangAndGebiet(gebiet.getID_LetzterEingang(),
                    gebiet.getID_Gebiet());
          } catch (FinderException e) {
            e.printStackTrace();
          }
        }
        eingangsHistorieErgebnisList.add(new EingangsContainer(lastInput != null ? lastInput
            .getDetails() : null, gebiet.getDetails(), gebietsstatus));
      }
      return eingangsHistorieErgebnisList;
    } catch (FinderException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Collection<Personendaten> getPersonenAlphabetisch(AnwContext anwContext)
      throws EJBException {
    WahlInfo wahlInfo = getWahlInfo(anwContext);
    wahlInfo.getWahl().readLock();
    try {
      return getPersonendatenHome().findAll();
    } catch (EJBException e) {
      throw new EJBException(e);
    } catch (FinderException e) {
      throw new EJBException(
          Messages.getString(MessageKeys.Error_ExceptionBeimErmittelnDerPersonendaten), e);
    }
  }

  @Override
  public String getKonfigurationString(AnwContext anwContext) throws EJBException, FinderException {
    String result = ""; //$NON-NLS-1$
    return result;
  }

  /**
   * TODO: ggfs sortieren!!!
   * 
   * @see de.ivu.wahl.auswertung.AuswertungHandling#getAllGruppen(java.lang.String)
   */
  @Override
  public List<GruppeGebietsspezifischModel> getAllGruppen(final String id_Gebiet)
      throws EJBException, FinderException {

    return toModelList(GruppeGebietsspezifischHome.HomeFinder.findHome(this)
        .findAllByGebiet(id_Gebiet));
  }

  public List<ErgebniseingangModel> getStatusInfos() {
    return null;
  }

}