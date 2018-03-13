/*
 * ImportElectionMetadata
 * 
 * Created on 27.04.2007
 * Copyright (c) 2007-2017 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import static de.ivu.wahl.dataimport.ImportUtil.readXMLRoot;
import static de.ivu.wahl.dataimport.XMLTags.ATTR_ED_REGION_CATEGORY;
import static de.ivu.wahl.dataimport.XMLTags.ATTR_ED_REGION_NUMBER;
import static de.ivu.wahl.dataimport.XMLTags.ED_ELECTION_TREE;
import static de.ivu.wahl.dataimport.XMLTags.ED_REGION;
import static de.ivu.wahl.dataimport.XMLTags.ED_REGION_NAME;
import static de.ivu.wahl.dataimport.XMLTags.EML;
import static de.ivu.wahl.dataimport.XMLTags.EML_ELECTION;
import static de.ivu.wahl.dataimport.XMLTags.EML_ELECTION_EVENT;
import static de.ivu.wahl.dataimport.XMLTags.NS_EML;
import static de.ivu.wahl.dataimport.XMLTags.NS_KR;
import static de.ivu.wahl.dataimport.XMLTags.WAHL_ART;
import static de.ivu.wahl.dataimport.XMLTags.WAHL_IDENTIFIER;
import static de.ivu.wahl.modell.GebietModel.EBENE_CSB;
import static de.ivu.wahl.modell.GebietModel.EBENE_HSB;
import static de.ivu.wahl.modell.GebietModel.EBENE_PSB;
import static de.ivu.wahl.modell.GebietModel.GEBIETSART_ALGEMEEN_BESTUUR;
import static de.ivu.wahl.modell.GebietModel.GEBIETSART_BUND;
import static de.ivu.wahl.modell.GebietModel.GEBIETSART_GEMEINDE;
import static de.ivu.wahl.modell.GebietModel.GEBIETSART_INSELGEMEINDE;
import static de.ivu.wahl.modell.GebietModel.GEBIETSART_LAND;
import static de.ivu.wahl.modell.GebietModel.GEBIETSART_WAHLKREIS;
import static de.ivu.wahl.util.XMLImportHelper.getAttribute;
import static de.ivu.wahl.util.XMLImportHelper.getText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;

import de.ivu.wahl.SystemInfo;
import de.ivu.wahl.export.Roman;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.Gebietsart;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.modell.impl.GebietModelImpl;
import de.ivu.wahl.util.EMLFilenameCheck;
import de.ivu.wahl.util.XMLImportHelper;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;
import de.ivu.wahl.wus.xmlsecurity.XmlParseException;

/**
 * Java-Entsprechung der Konfigurationsdatei für den Import der Metadaten einer Wahl
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class ImportElectionMetadata extends AbstractImportMetadata {

  protected GebietModel _wurzelGebiet = null;

  private Element _doc510;

  /**
   * @param modus
   * @param level
   */
  public ImportElectionMetadata(int modus, int level) {
    super(modus, level);
    reset();
  }

  @Override
  protected void updateStatusDb() {
    try {
      _fehlermeldung = EMPTY_STR;
      if (STATUS_INIT == _status) {
        if (!checkFilename()) {
          return;
        }
        if (!checkURLs()) {
          return;
        }
      }
      // check for schema
      Element eml = readXMLRootAndCheckSchemas();
      if (eml == null) {
        return;
      }
      // check EML id
      if (!checkEML230Id(eml)) {
        return;
      }
      // check election definition accepted
      readElectionMetadata();

      _status = STATUS_URL_KOMPLETT;
      if (!_definitionAccepted) {
        return;
      }
      _status = STATUS_ELECTIONDEFINITION_ACCEPTED;
      // check hash code
      updateSecurityLevel(eml);
      if (!checkHashwerte230()) {
        return;
      }
      _status = STATUS_HASH_CHECKED;

      finishInitialization();

      // print log
      APP_LOGGER.info(Messages.bind(MessageKeys.Logger_ImportFile_0_WithHash_1,
          EMLFilenameCheck.reduceFilenameFromSlashAsPrefix(_definition.getFile()),
          _hashWertDefinition));
      APP_LOGGER.info(Messages.bind(MessageKeys.Logger_ImportFile_0_WithHash_1,
          EMLFilenameCheck.reduceFilenameFromSlashAsPrefix(_EML230.getFile()),
          getHashWert230()));
    } catch (ImportException e) {
      LOGGER.error(e, e);
      _fehlermeldung = e.getMessage();
    }
  }

  @Override
  protected void updateStatusStandAlone() {

    try {
      _fehlermeldung = EMPTY_STR;
      if (readXMLRootAndCheckSchemas() == null) {
        return;
      }
      readElectionMetadata();
      finishInitialization();
    } catch (ImportException e) {
      LOGGER.error(e, e);
      _fehlermeldung = e.getMessage();
    }
  }

  private Element readXMLRootAndCheckSchemas() {
    Element eml = null;
    try {
      // eml 110a
      ImportUtil.readXMLRoot4Schema(_definition, XMLTags.SCHEMA_EML110A);
    } catch (Exception e) {
      reset();
      _fehlermeldung = Messages.getString(MessageKeys.Error_UngueltigeWahldefinition)
          + e.getMessage();
      return null;
    }
    try {
      // eml 230
      eml = ImportUtil.readXMLRoot4Schema(_EML230, XMLTags.SCHEMA_EML230);
    } catch (Exception e) {
      reset();
      _fehlermeldung = Messages.getString(MessageKeys.Error_UngueltigeKandidatenliste)
          + e.getMessage();
      return null;
    }
    if (_EML510 != null) {
      try {
        ImportUtil.readXMLRoot4Schema(_EML510, XMLTags.SCHEMA_EML510);
      } catch (Exception e) {
        reset();
        _fehlermeldung = Messages.getString(MessageKeys.Error_UngueltigeErgebnisdatei)
            + e.getMessage();
        return null;
      }
    }
    return eml;
  }

  private boolean checkHashwerte230() {
    if (SecurityLevel.NO_HASH_CODE.equals(_securityLevel)) {
      return true;
    }
    if (_hashCodeSplitter230.isInputComplete()) {
      if (_hashCodeSplitter230.checkInput()) {
        return true;
      } else {
        _fehlermeldung += Messages.bind(MessageKeys.Error_FalscherHashWerte);
      }
    } else {
      if (SecurityLevel.ASK_FOR_HASH_CODE.equals(_securityLevel)) {
        _fehlermeldung += Messages
            .bind(MessageKeys.Error_BitteGebenSieDieFehlendenStellenDesHashWertesAn);
      }
    }
    return false;
  }

  private boolean checkEML230Id(Element eml230) {
    String id = XMLImportHelper.getAttribute(eml230, XMLTags.ATTR_EML_ID);
    String idExpected = _level == GebietModel.EBENE_CSB
        ? XMLTags.ATTR_VAL_EML_ID_230c
        : XMLTags.ATTR_VAL_EML_ID_230b;
    if (!idExpected.equals(id)) {
      _fehlermeldung += Messages.bind(MessageKeys.Error_Erwartet_0_Gefunden_1, idExpected, id);
      return false;
    }
    return true;
  }

  private boolean checkURLs() {
    if (_EML230 != null && _definition != null) {

      try {
        _hashWertDefinition = _hashWertErmittlung.createDigest(_definition.openStream());
        _hashCodeSplitter230 = new HashCodeSplitter(_hashWertErmittlung.createDigest(_EML230
            .openStream()));
        return true;
      } catch (XmlParseException e) {
        _fehlermeldung += Messages.bind(MessageKeys.Error_FalschesDateiFormat);
      } catch (IOException e) {
        _fehlermeldung += Messages.bind(MessageKeys.Error_FalschesDateiFormat);
      }
    } else {
      _fehlermeldung = EMPTY_STR;
      if (_definition == null) {
        _fehlermeldung += Messages.bind(MessageKeys.Error_BitteGebenSieDieWahldefinitionAn)
            + "<br>"; //$NON-NLS-1$
      }
      if (_EML230 == null) {
        _fehlermeldung += Messages.bind(MessageKeys.Error_BitteGebenSieDieKandidatendateiAn);
      }
    }
    return false;
  }

  private boolean checkFilename() {
    if (_modus == MODE_DB_P4) {
      return checkFilenameForP4();
    }
    if (_modus == MODE_DB_P5) {
      return checkFilenameForP5();
    }
    _fehlermeldung = "CheckFileName not expected for mode " + _modus; //$NON-NLS-1$
    return false;
  }

  private boolean checkFilenameForP4() {
    if (_EML230 == null) {
      return false;
    }
    boolean ret = true;
    switch (_level) {
      case GebietModel.EBENE_CSB :
        ret = EMLFilenameCheck.is230cFilename(EMLFilenameCheck
            .reduceFilenameFromSlashAsPrefix(_EML230.getFile()));
        break;
      case GebietModel.EBENE_HSB :
      case GebietModel.EBENE_PSB :
        ret = EMLFilenameCheck.is230bFilename(EMLFilenameCheck
            .reduceFilenameFromSlashAsPrefix(_EML230.getFile()));
        break;
      default :
        ret = false;
    }
    if (!ret) {
      _fehlermeldung += Messages.bind(MessageKeys.Error_FalschesDateinamenFormat);
      return false;
    }
    return true;
  }

  private boolean checkFilenameForP5() {
    if (_EML230 == null) {
      return false;
    }
    if (!EMLFilenameCheck.is230cFilename(EMLFilenameCheck.reduceFilenameFromSlashAsPrefix(_EML230
        .getFile()))) {
      _fehlermeldung += Messages.bind(MessageKeys.Error_FalschesDateinamenFormat);
      return false;
    }
    return true;
  }

  /**
   * Determine election metadata from election definition
   * 
   * @throws ImportException
   */
  private void readElectionMetadata() throws ImportException {
    try {
      // try to read eml 110a
      Element eml110a = readXMLRoot(_definition, EML);
      Element electionEvent = eml110a.getFirstChildElement(EML_ELECTION_EVENT, NS_EML);
      Element election = electionEvent.getFirstChildElement(EML_ELECTION, NS_EML);
      setImportType(ImportType.EML110A_AND_EML230);

      Element electionIdentifier = election.getFirstChildElement(WAHL_IDENTIFIER, NS_EML);
      Element art = electionIdentifier.getFirstChildElement(WAHL_ART, NS_EML);
      _electionCategory = AbstractImportClient.getElectionCategory(getText(art));
      if (_electionCategory.isEK() && _modus == MODE_DB_P4) {
        // Do not accept EK election in P4_PSB
        if (SystemInfo.getSystemInfo().getWahlEbene() == GebietModel.EBENE_PSB) {
          throw new ImportException(Messages.getString(MessageKeys.Error_NoEkElectionInP4PSB));
        }
      }

      String source = "election definition"; //$NON-NLS-1$
      readAndCheckElectionDomain(electionIdentifier, art, _electionCategory, source);
      readAndCheckElectionDomainId(electionIdentifier, art, _electionCategory, source);

      Element electionTree = election.getFirstChildElement(ED_ELECTION_TREE, NS_KR);
      _wurzelGebiet = readRootRegion(electionTree);

      _electionDetails = readElectionDetails(eml110a, election, electionIdentifier);

    } catch (ImportException e) {
      e.printStackTrace();
      throw new ImportException(
          Messages.getString(MessageKeys.Error_FehlerBeimLesenDerWahldefinition)
              + " " + e.getMessage()); //$NON-NLS-1$
    }
  }

  /**
   * @param electionTree
   * @return
   * @throws ImportException
   */
  private GebietModel readRootRegion(Element electionTree) throws ImportException {
    GebietModel result = null;
    if (electionTree != null) {
      Elements regions = electionTree.getChildElements(ED_REGION, NS_KR);
      if (regions.size() > 0) {
        Element rootRegion = regions.get(0);
        result = new GebietModelImpl();
        String gebietsartStr = getAttribute(rootRegion, ATTR_ED_REGION_CATEGORY);
        result.setGebietsart(AbstractImportClient.getGebietsart(gebietsartStr));
        int nummer = 0;
        boolean isRoman = false;
        if (rootRegion.getAttribute(ATTR_ED_REGION_NUMBER) != null) {
          nummer = XMLImportHelper.getAttributeIntValue(rootRegion, ATTR_ED_REGION_NUMBER);
          if (nummer == -1) {
            nummer = 0;
          }
          isRoman = XMLImportHelper.getAttributeBoolValue(rootRegion,
              XMLTags.GEBIET_ATTRIBUT_NR_ROEMISCH,
              false);
        }
        result.setNummer(nummer);
        result.setRoemisch(isRoman);
        Element name = rootRegion.getFirstChildElement(ED_REGION_NAME, NS_KR);
        if (name == null) {
          throw new ImportException(_definition,
              Messages.bind(MessageKeys.Error_GebietNr_0_HatKeinenText, nummer));
        }
        result.setName(name.getValue());
      }
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.AbstractImportMetadata#readGebietsauswahl()
   */
  @Override
  public List<GebietModel> readGebietsauswahl() throws ImportException {
    // try to read eml 110a
    Element eml110a = readXMLRoot(_definition, EML);
    Element electionEvent = eml110a.getFirstChildElement(EML_ELECTION_EVENT, NS_EML);
    Element wahldefinition = electionEvent.getFirstChildElement(EML_ELECTION, NS_EML);

    Element eml230 = readXMLRoot(_EML230, EML);
    Element kandidatenlist = eml230.getFirstChildElement(XMLTags.KANDIDATEN, NS_EML);
    Element election = kandidatenlist.getFirstChildElement(XMLTags.EML_ELECTION, NS_EML);
    Element contest = election.getFirstChildElement(XMLTags.EML_CONTEST, NS_EML);
    Element contestIdentifier = contest
        .getFirstChildElement(XMLTags.EML_CONTEST_IDENTIFIER, NS_EML);
    Attribute gebietIDAttr = contestIdentifier.getAttribute(XMLTags.EML_ATTR_CONTEST_IDENTIFIER);
    String gebietIdContestIdentifier = gebietIDAttr.getValue();

    Element electionIdentifier = wahldefinition.getFirstChildElement(WAHL_IDENTIFIER, NS_EML);
    Element ec = electionIdentifier.getFirstChildElement(WAHL_ART, NS_EML);
    String value = ec.getValue();
    ElectionCategory electionCategory = ElectionCategory.valueOf(value);
    Gebietsart gebietsartMitContest = (ElectionCategory.EK.equals(electionCategory)
        ? Gebietsart.LAND
        : Gebietsart.WAHLKREIS);

    Element gebiete = wahldefinition.getFirstChildElement(ED_ELECTION_TREE, NS_KR);
    List<GebietModel> ret = new ArrayList<GebietModel>();
    Elements nodes = gebiete.getChildElements(ED_REGION, NS_KR);
    for (int gebietsIdx = 0; gebietsIdx < nodes.size(); ++gebietsIdx) {
      Element node = nodes.get(gebietsIdx);
      String gebietsartStr = getAttribute(node, ATTR_ED_REGION_CATEGORY);
      int gebietsart = _gebietsart;
      if (ElectionCategory.ER.equals(electionCategory)) {
        gebietsart = GebietModel.GEBIETSART_GEMEINDE;
      }
      if (gebietsart != AbstractImportClient.getGebietsart(gebietsartStr)) {
        continue;
      }
      GebietModel gebiet = new GebietModelImpl();
      gebiet.setGebietsart(_gebietsart);
      int nummer = 0;
      if (node.getAttribute(ATTR_ED_REGION_NUMBER) != null) {
        nummer = XMLImportHelper.getAttributeIntValue(node, ATTR_ED_REGION_NUMBER);
        if (nummer == -1) {
          nummer = 0;
        }
      }
      gebiet.setNummer(nummer);
      Element name = node.getFirstChildElement(ED_REGION_NAME, NS_KR);
      if (name == null) {
        throw new ImportException(_definition,
            Messages.bind(MessageKeys.Error_GebietNr_0_HatKeinenText, nummer));
      }
      gebiet.setName(name.getValue());
      if (gebiet.getGebietsart() < gebietsartMitContest.getId()
          || XMLTags.ATTR_VAL_CONTEST_ID_ALLE.equals(gebietIdContestIdentifier)
          || XMLTags.ATTR_VAL_CONTEST_ID_GEEN.equals(gebietIdContestIdentifier)
          || hasParentWithIDandArt(node,
              nodes,
              gebietIdContestIdentifier,
              gebietsartMitContest.getKlartextExport())) {
        ret.add(gebiet);
      }
    }
    if (ret.size() == 1) {
      _gebietsNr = ret.get(0).getNummer();
    }
    Collections.sort(ret, new GebietModel.GebietsComparator());
    LOGGER.debug(Messages.bind(MessageKeys.Result_Tracelog_AnzahlGebiete_0_1,
        _gebietsart,
        ret.size()));
    return ret;
  }

  private boolean hasParentWithIDandArt(Element gebiet,
      Elements gebiete,
      String gebietContestId,
      String gebietContestArt) {

    // It is the root region
    if (isGebietWithIdAndArt(gebiet, gebietContestId, gebietContestArt)) {
      return true;
    }
    // Filter for correct parent region
    String idElterngebiet = XMLImportHelper.getAttribute(gebiet,
        XMLTags.GEBIET_ATTRIBUT_UEGGEBIETSNUMMER);
    String artElterngebiet = XMLImportHelper.getAttribute(gebiet,
        XMLTags.GEBIET_ATTRIBUT_UEGGEBIETSART);

    for (int gebietsIdx = 0; gebietsIdx < gebiete.size(); gebietsIdx++) {
      Element elterngebiet = gebiete.get(gebietsIdx);
      if (isGebietWithIdAndArt(elterngebiet, idElterngebiet, artElterngebiet)) {
        return hasParentWithIDandArt(elterngebiet, gebiete, gebietContestId, gebietContestArt);
      }
    }
    return false;
  }

  /**
   * @param gebiet
   * @return
   */
  private boolean isGebietWithIdAndArt(Element gebiet, String id, String art) {
    String idGebiet = XMLImportHelper.getAttribute(gebiet, XMLTags.ATTR_ED_REGION_NUMBER);
    String artGebiet = XMLImportHelper.getAttribute(gebiet, XMLTags.ATTR_ED_REGION_CATEGORY);
    if (artGebiet.equals(art)) {
      if (idGebiet == null) {
        return true;
      } else {
        String arabicId = null;
        try {
          arabicId = Roman.toLong(id).toString();
        } catch (final NumberFormatException e) {
          // in most cases id already was an arabic before -> do nothing here
        }
        if (idGebiet.equals(id) || idGebiet.equals(arabicId)) {
          return true;
        }
      }
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.AbstractImportMetadata#updateGebietsartWurzel()
   */
  @Override
  protected void updateGebietsartWurzel() {
    if (_electionCategory.equals(ElectionCategory.BC)
        || _electionCategory.equals(ElectionCategory.GC)) {
      _gebietsart = GebietModel.GEBIETSART_ORTSTEIL;
    } else if (_electionCategory.equals(ElectionCategory.ER)) {
      _gebietsart = GEBIETSART_INSELGEMEINDE;
    } else if (_electionCategory.equals(ElectionCategory.GR) || _level == EBENE_PSB) {
      _gebietsart = GEBIETSART_GEMEINDE;

    } else if (_level == EBENE_CSB) {
      if (_electionCategory.equals(ElectionCategory.PS)) {
        _gebietsart = GEBIETSART_LAND;
      } else if (_electionCategory.equals(ElectionCategory.AB)) {
        _gebietsart = GEBIETSART_ALGEMEEN_BESTUUR;
      } else {
        _gebietsart = GEBIETSART_BUND;
      }

    } else if (_level == EBENE_HSB) {
      if (_electionCategory.equals(ElectionCategory.EK)) {
        _gebietsart = GEBIETSART_LAND;
      } else {
        _gebietsart = GEBIETSART_WAHLKREIS;
      }
    }
    LOGGER.info(Messages
        .bind(MessageKeys.Result_Tracelog_BestimmeGebietsartFuerWahlart_0_Ebene_1_2,
            _electionCategory,
            _level,
            _gebietsart));
    if (_gebietsart < 0) {
      throw new RuntimeException(
          Messages.bind(MessageKeys.Error_KeineGebietsartBestimmtFuerWahlart_0_undEbene_1,
              _electionCategory,
              _level));
    }
  }

  /**
   * Reads results from url _EML510
   * 
   * @return Root element from _EML510
   * @throws ImportException
   */
  public Element getErgebnisse() throws ImportException {
    if (_EML510 != null && _doc510 == null) {
      _doc510 = readXMLRoot(_EML510, XMLTags.EML);
    }
    return _doc510;
  }

  /**
   * Initializing the ImportElectionMetadata for a new Import
   */
  @Override
  public void reset() {
    super.reset();
    _doc510 = null;
    _gebietsart = -1;
    _gebietsNr = -1;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(Messages.bind(MessageKeys.Result_Tracelog_Wahldefinition))
        .append(_definition == null ? NULL_STR : _definition.getPath()).append(LF);
    sb.append("EML: ").append(_EML230 == null ? NULL_STR : _EML230.getPath()).append(LF); //$NON-NLS-1$
    appendWurzelAndGebietsauswahlAndLevelAndStatusAndCategoryAndFehlermeldung(sb);
    return sb.toString();
  }

  @Override
  protected void updateSecurityLevel(Element eml230) {

    if (_modus == MODE_STANDALONE) {
      _securityLevel = SecurityLevel.NO_HASH_CODE;

    } else if (_modus == MODE_DB_P5) {
      if (_electionCategory.isMunicipalityElection()) {
        _securityLevel = SecurityLevel.CONFIRM_HASH_CODE;
      } else {
        _securityLevel = SecurityLevel.NO_HASH_CODE;
      }

    } else {
      switch (_level) {

        case GebietModel.EBENE_PSB :
          if (_electionCategory.isMunicipalityElection()) {
            _securityLevel = SecurityLevel.CONFIRM_HASH_CODE;
          } else if (_electionCategory.equals(ElectionCategory.PS)
              || _electionCategory.equals(ElectionCategory.AB)) {
            // no current location at this point of time
            // compare of managing authority and current location not possible
            _securityLevel = SecurityLevel.ASK_FOR_HASH_CODE;
          } else {
            _securityLevel = SecurityLevel.ASK_FOR_HASH_CODE;
          }
          break;

        case GebietModel.EBENE_HSB :
          if (_electionCategory.isMunicipalityElection()) {
            _securityLevel = SecurityLevel.CONFIRM_HASH_CODE;
          } else if (_electionCategory.equals(ElectionCategory.PS)
              || _electionCategory.equals(ElectionCategory.AB)) {
            String authorityName = readManagingAuthorityName(eml230);
            if (_wurzelGebiet != null && authorityName != null
                && authorityName.equals(_wurzelGebiet.getName())) {
              _securityLevel = SecurityLevel.CONFIRM_HASH_CODE;
            } else {
              _securityLevel = SecurityLevel.ASK_FOR_HASH_CODE;
            }
          } else {
            _securityLevel = SecurityLevel.ASK_FOR_HASH_CODE;
          }
          break;

        case GebietModel.EBENE_CSB :
          _securityLevel = SecurityLevel.CONFIRM_HASH_CODE;
          break;

        default :
          _fehlermeldung = Messages.bind(MessageKeys.Error_UnbekannteWahlebene_0, _level);
      }
    }
  }

}
