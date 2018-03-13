/*
 * MetadatenDb
 * 
 * Created on 06.02.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import static de.ivu.ejb.EJBUtil.getUniqueKey;
import static de.ivu.ejb.EJBUtil.lookupLocal;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import org.apache.log4j.Category;

import de.ivu.ejb.EJBUtil;
import de.ivu.ejb.bmp.Model;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.SystemInfo;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ErgebniseingangKonstanten;
import de.ivu.wahl.modell.ErgebniseingangModel;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.GebietsstatusModel;
import de.ivu.wahl.modell.StimmergebnisModel;
import de.ivu.wahl.modell.WahlModel;
import de.ivu.wahl.modell.ejb.Ergebniseingang;
import de.ivu.wahl.modell.ejb.ErgebniseingangHome;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifisch;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.modell.impl.GebietsstatusModelImpl;
import de.ivu.wahl.modell.impl.StimmergebnisModelImpl;

/**
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public class ErgebnisImportHandlerDb extends AbstractErgebnisImportHandler {

  private static final Category LOGGER = Log4J.configure(ErgebnisImportHandlerDb.class);

  static {
    LOGGER.info(Log4J
        .dumpVersion(ErgebnisImportHandlerDb.class, Log4J.extractVersion("$Revision$"))); //$NON-NLS-1$
  }

  final static List<Model> _models = new ArrayList<Model>();

  public ErgebnisImportHandlerDb(WahlInfo wahlInfo) throws ImportException {
    _wahlInfo = wahlInfo;
    String id_Wahl = _wahlInfo.getWahl().getID_Wahl();
    initErgebnisimport(id_Wahl);
  }

  public boolean checkConsistency() {
    return true;
  }

  private void initErgebnisimport(String id_Wahl) throws ImportException {
    _ergebniseingang = createErgebniseingang(id_Wahl);
  }

  @Override
  public GebietModel getImportRegion() throws ImportException {
    try {
      return _gebietHome.findByPrimaryKey(_wahlInfo.getID_Wurzelgebiet());
    } catch (Exception e) {
      throw new ImportException(Messages
          .getString(MessageKeys.Error_FehlerBeimHolenDesWurzelgebietes));
    }
  }

  /**
   * Create an object identifing the current import action
   * 
   * @param id_Wahl the election identifier
   * @return the object identifing the current import action
   * @throws ImportException
   */
  private Ergebniseingang createErgebniseingang(String id_Wahl) throws ImportException {
    try {
      Ergebniseingang erg = ((ErgebniseingangHome) EJBUtil.findLocalHomeNoCache("Ergebniseingang")) //$NON-NLS-1$
          .create();
      _id_Ergebniseingang = erg.getID_Ergebniseingang();
      LOGGER.info("Ergebniseingang " + _id_Ergebniseingang); //$NON-NLS-1$
      ErgebniseingangModel model = erg.getDetails();
      model.setZeitstempel(new Timestamp(System.currentTimeMillis()));
      model.setID_Wahl(id_Wahl);
      model.setUnterschiedeVorhanden(ErgebniseingangKonstanten.UnterschiedeVorhandenTyp.UNTERSCHIEDE_UNBEKANNT.getValue());
      model.setStatus(ErgebniseingangKonstanten.STATE_ERROR);

      int herkunft = ErgebniseingangKonstanten.SOURCE_FILE_IMPORT;
      if (SystemInfo.getSystemInfo().isFileInputWithManualConfirmation()) {
        herkunft = ErgebniseingangKonstanten.SOURCE_FILE_IMPORT_AS_FIRST_INPUT;
      }
      model.setHerkunft(herkunft);
      
      model.setID_Erfassungseinheit(_wahlInfo.getID_Wurzelgebiet());
      model.setWahlergebnisart(_wahlInfo.getAktuelleWahlergebnisart());
      erg.setDetails(model);
      return erg;
    } catch (Exception e) {
      throw new ImportException(Messages
          .getString(MessageKeys.Error_FehlerBeimAnlegenDesErgebniseingangs), e);
    }
  }

  void updateGebietsstatus(GebietModel gebietModel, int anzEingegangen) throws ImportException {
    Gebiet gebiet;
    try {
      gebiet = _gebietHome.findByPrimaryKey(gebietModel.getID_Gebiet());
    } catch (Exception e) {
      throw new ImportException(Messages.getString(MessageKeys.Error_FehlerBeimHolenDesGebietes));
    }
    int wahlergebnisart = _wahlInfo.getAktuelleWahlergebnisart();
    createGebietsstatus(gebiet,
        _ergebniseingang.getID_Ergebniseingang(),
        wahlergebnisart,
        anzEingegangen);
    gebiet.setLetzterEingang(_ergebniseingang);
    gebiet.setLetzterGueltigerEingang(wahlergebnisart, _ergebniseingang);
  }

  /**
   * Creating new state object for a region where results where imported
   * 
   * @param _gebiet region object
   * @param id_Ergebniseingang id for the current import action
   * @param anzEingegangen results of superior results contributing to this state
   * @throws ImportException
   * @throws EJBException
   */
  private void createGebietsstatus(Gebiet gebiet,
      String id_Ergebniseingang,
      int wahlergebnisart,
      int anzEingegangen) throws EJBException, ImportException {
    GebietsstatusModel model = new GebietsstatusModelImpl(getUniqueKey());
    model.setWahlergebnisart(wahlergebnisart);
    model.setAnzahlErgebnisseKumuliert(anzEingegangen);
    model.setID_Ergebniseingang(id_Ergebniseingang);
    model.setID_Gebiet(gebiet.getID_Gebiet());
    // result is complete if alle inferior regions have results or there are no inferior regions
    boolean isVollstaendig = gebiet.getGebietCol().size() == 0
        || gebiet.getGebietCol().size() == anzEingegangen;
    // Check if we have results for all subregions!!!
    if (!isVollstaendig) {
      throw new ImportException(Messages
          .bind(MessageKeys.Error_Nur_0_von_1_GebietsergebnissenGefunden, anzEingegangen, gebiet
              .getGebietCol().size()));
    }
    model.setVollstaendig(isVollstaendig);
    int korrekturnummer = 0;
    if (gebiet.getCurrentGebietsstatus(wahlergebnisart) != null) {
      korrekturnummer = gebiet.getCurrentGebietsstatus(wahlergebnisart).getKorrekturnummer() + 1;
    }
    model.setKorrekturnummer(korrekturnummer);
    _models.add(model);
  }

  ImportHandling getImportHandling() throws ImportException {
    try {
      if (_impHandling == null) {
        _impHandling = lookupLocal(ImportHandlingBean.class.getSimpleName());
      }
      return _impHandling;
    } catch (Exception e) {
      throw new ImportException(e);
    }
  }

  /**
   * Create StimmergebnisModel object for general results
   * 
   * @param id_Gebiet id of Region object
   * @param gruppeAllgemein kind of vote
   * @param stimmen number of votes
   * @throws ImportException
   */
  static void addStimmergebnisAllgemein(String id_Gebiet,
      String id_GruppeGebietsspezifisch,
      String id_Ergebniseingang,
      int stimmen) throws ImportException {
    StimmergebnisModel stimmergebnis = new StimmergebnisModelImpl(getUniqueKey());
    stimmergebnis.setID_Gebiet(id_Gebiet);
    stimmergebnis.setID_GruppeGebietsspezifisch(id_GruppeGebietsspezifisch);
    stimmergebnis.setID_Ergebniseingang(id_Ergebniseingang);
    stimmergebnis.setStimmart(StimmergebnisModel.STIMMART_KEINE);
    stimmergebnis.setStimmen(stimmen);
    _models.add(stimmergebnis);
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.Metadaten#finishErgebnisimport()
   */
  @Override
  public void finishErgebnisimport(Collection<GebietModel> gebieteEingegangen)
      throws ImportException {
    updateGebietsstatus(gebieteEingegangen, getImportRegion());
    flushModels(_models);
    // Import ok, state ok in Ergebniseingang can be set
    // _ergebniseingang.addGebiet(_wurzelgebiet);
    _ergebniseingang.setStatus(ErgebniseingangKonstanten.STATE_OK);
    _wahlInfo.getWahl().setLetzteAenderung(_ergebniseingang.getZeitstempel());
    _wahlInfo.getWahl().setStatus(WahlModel.STATE_NEW_RESULT);
    _wahlInfo.synchronize();
  }

  /**
   * @param gebieteEingegangen
   * @param wurzelgebiet
   * @throws ImportException
   */
  private void updateGebietsstatus(Collection<GebietModel> gebieteEingegangen,
      GebietModel wurzelgebiet) throws ImportException {
    boolean readRootRegion = false;
    for (GebietModel gebiet : gebieteEingegangen) {
      readRootRegion |= gebiet.getGebietsart() == wurzelgebiet.getGebietsart();
      updateGebietsstatus(gebiet, 1);
    }
    // check if root region was already handled
    if (!readRootRegion) {
      updateGebietsstatus(wurzelgebiet, gebieteEingegangen.size());
    }
  }

  void addModel(Model model) {
    _models.add(model);
  }

  @Override
  public void addStimmen(GebietModel gebiet, int schluessel, String id_Listenkandidatur, int stimmen)
      throws ImportException {
    if (id_Listenkandidatur != null && !saveCandidateVotes(gebiet.getGebietsart())) {
      return;
    }
    GruppeGebietsspezifisch gg;
    try {
      gg = _ggHome.findByGebietAndGruppenschluessel(gebiet.getID_Gebiet(), schluessel);
    } catch (FinderException e) {
      throw new ImportException(Messages
          .bind(MessageKeys.Error_In_0_KeineGruppeMitSchluessel_1_Angelegt,
              gebiet.getName(),
              schluessel));
    }
    addModel(createStimmergebnisModel(gebiet.getID_Gebiet(),
        gg.getID_GruppeGebietsspezifisch(),
        id_Listenkandidatur,
        _ergebniseingang.getID_Ergebniseingang(),
        stimmen));
  }

  /**
   * Save StimmergebnisModel object for general results
   * 
   * @param id_Gebiet id of Region object
   * @param gruppeAllgemein kind of vote
   * @param stimmen number of votes
   * @throws ImportException
   */
  @Override
  protected void saveStimmergebnisAllgemein(GebietModel region,
      int position,
      String id_Ergebniseingang,
      int stimmen) throws ImportException {

    String id_Gebiet = region.getID_Gebiet();
    StimmergebnisModel stimmergebnis = new StimmergebnisModelImpl(getUniqueKey());
    stimmergebnis.setID_Gebiet(id_Gebiet);
    stimmergebnis.setID_GruppeGebietsspezifisch(getID_GruppeGebietsspezifisch(id_Gebiet, position));
    stimmergebnis.setID_Ergebniseingang(_id_Ergebniseingang);
    stimmergebnis.setStimmart(StimmergebnisModel.STIMMART_KEINE);
    stimmergebnis.setStimmen(stimmen);
    _models.add(stimmergebnis);
  }

  Map<String, Map<Integer, String>> ggIds = new HashMap<String, Map<Integer, String>>();

  private String getID_GruppeGebietsspezifisch(String id_Gebiet, int position)
      throws ImportException {
    if (ggIds.get(id_Gebiet) == null) {
      // Get all GG IDs for region
      Collection<GruppeGebietsspezifisch> ggCollection;
      Map<Integer, String> idMap = new HashMap<Integer, String>();
      try {
        ggCollection = _ggHome.findAllByGebiet(id_Gebiet);
      } catch (FinderException e) {
        throw new ImportException(Messages
            .getString(MessageKeys.Error_FehlerBeimHolenDerGruppeGebietsspezifisch));
      }
      for (GruppeGebietsspezifisch gg : ggCollection) {
        if (gg.getPosition() < 0) {
          idMap.put(gg.getPosition(), gg.getID_GruppeGebietsspezifisch());
        }
      }
      ggIds.put(id_Gebiet, idMap);
    }
    return ggIds.get(id_Gebiet).get(new Integer(position));

  }

  /**
   * Create the model object for a single voting result
   * 
   * @param id_Gebiet region of the the result
   * @param id_GruppeGebietsspezifisch region specifoc group for the result
   * @param id_Listenkandidatur candidate who became the votes (or null for general/group result)
   * @param id_Ergebniseingang id of the import action
   * @param stimmen number of votes
   * @return the model object
   */
  static StimmergebnisModel createStimmergebnisModel(String id_Gebiet,
      String id_GruppeGebietsspezifisch,
      String id_Listenkandidatur,
      String id_Ergebniseingang,
      int stimmen) {
    StimmergebnisModel stimmergebnis = new StimmergebnisModelImpl(getUniqueKey());
    stimmergebnis.setID_Gebiet(id_Gebiet);
    stimmergebnis.setID_GruppeGebietsspezifisch(id_GruppeGebietsspezifisch);
    stimmergebnis.setID_Listenkandidatur(id_Listenkandidatur);
    stimmergebnis.setID_Ergebniseingang(id_Ergebniseingang);
    stimmergebnis.setStimmart(StimmergebnisModel.STIMMART_LISTENSTIMME);
    stimmergebnis.setStimmen(stimmen);
    return stimmergebnis;
  }

  void flushModels(List<Model> models) throws ImportException {
    LOGGER.debug(Messages.bind(MessageKeys.Result_Tracelog_CommittingBeans));
    try {
      getImportHandling().createEntities(models);
      models.clear();
    } catch (Exception e) {
      throw new ImportException(Messages.bind(MessageKeys.Error_FehlerBeiCommit), e);
    }
  }

}
