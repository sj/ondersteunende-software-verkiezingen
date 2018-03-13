/*
 * ImportReferendumMetadata
 * 
 * Created on 02.10.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
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
import static de.ivu.wahl.dataimport.XMLTags.EML_OPTIONS_LIST;
import static de.ivu.wahl.dataimport.XMLTags.NS_EML;
import static de.ivu.wahl.dataimport.XMLTags.NS_KR;
import static de.ivu.wahl.dataimport.XMLTags.WAHL_ART;
import static de.ivu.wahl.dataimport.XMLTags.WAHL_IDENTIFIER;
import static de.ivu.wahl.modell.GebietModel.EBENE_CSB;
import static de.ivu.wahl.modell.GebietModel.EBENE_HSB;
import static de.ivu.wahl.modell.GebietModel.EBENE_PSB;
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

import nu.xom.Element;
import nu.xom.Elements;

import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.modell.impl.GebietModelImpl;
import de.ivu.wahl.util.EMLFilenameCheck;
import de.ivu.wahl.util.XMLImportHelper;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;
import de.ivu.wahl.wus.xmlsecurity.XmlParseException;

/**
 * @author tdu@ivu.de, IVU Traffic Technologies AG
 */
public class ImportReferendumMetadata extends AbstractImportMetadata {

  /**
   * Constructor
   */
  public ImportReferendumMetadata(int modus, int level) {
    super(modus, level);
    reset();
    setImportType(ImportType.EML630);
  }

  @Override
  protected void updateStatusDb() {
    try {
      _fehlermeldung = EMPTY_STR;
      if (STATUS_INIT == _status) {
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
      if (!checkEML630Id(eml)) {
        return;
      }
      // check election definition accepted
      readReferendumMetadata();

      _status = STATUS_URL_KOMPLETT;

      if (!_definitionAccepted) {
        return;
      }
      _status = STATUS_HASH_CHECKED;

      finishInitialization();

      // print log
      APP_LOGGER.info(Messages.bind(MessageKeys.Logger_ImportFile_0_WithHash_1, EMLFilenameCheck
          .reduceFilenameFromSlashAsPrefix(_definition.getFile()), _hashWertDefinition));
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
      readReferendumMetadata();
      finishInitialization();
    } catch (ImportException e) {
      LOGGER.error(e, e);
      _fehlermeldung = e.getMessage();
    }
  }

  private boolean checkEML630Id(Element eml630) {
    String id = XMLImportHelper.getAttribute(eml630, XMLTags.ATTR_EML_ID);
    String idExpected = XMLTags.ATTR_VAL_EML_ID_630;
    if (!idExpected.equals(id)) {
      _fehlermeldung += Messages.bind(MessageKeys.Error_Erwartet_0_Gefunden_1, idExpected, id);
      return false;
    }
    return true;
  }

  private boolean checkURLs() {
    if (_definition != null) {

      try {
        _hashWertDefinition = _hashWertErmittlung.createDigest(_definition.openStream());
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
    }
    return false;
  }

  private Element readXMLRootAndCheckSchemas() {
    Element eml = null;
    try {
      eml = ImportUtil.readXMLRoot4Schema(_definition, XMLTags.SCHEMA_EML630);
    } catch (Exception e) {
      reset();
      _fehlermeldung = Messages.getString(MessageKeys.Error_UngueltigeReferendumdefinition)
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

  /**
   * Determine referendum metadata from referendum definition
   * 
   * @throws ImportException
   */
  private void readReferendumMetadata() throws ImportException {
    try {
      Element eml630 = readXMLRoot(_definition, EML);
      Element optionsList = eml630.getFirstChildElement(EML_OPTIONS_LIST, NS_EML);
      Element wahldefinition = optionsList.getFirstChildElement(EML_ELECTION, NS_EML);

      Element electionIdentifier = wahldefinition.getFirstChildElement(WAHL_IDENTIFIER, NS_EML);
      Element art = electionIdentifier.getFirstChildElement(WAHL_ART, NS_EML);
      _electionCategory = AbstractImportClient.getElectionCategory(getText(art));

      String source = "referendum definition"; //$NON-NLS-1$
      readAndCheckElectionDomain(electionIdentifier, art, _electionCategory, source);
      readAndCheckElectionDomainId(electionIdentifier, art, _electionCategory, source);

      _electionDetails = readElectionDetails(eml630, wahldefinition, electionIdentifier);

    } catch (ImportException e) {
      throw new ImportException(Messages
          .getString(MessageKeys.Error_FehlerBeimLesenDerReferendumdefinition)
          + " " + e.getMessage()); //$NON-NLS-1$
    }
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.AbstractImportMetadata#readGebietsauswahl()
   */
  @Override
  public List<GebietModel> readGebietsauswahl() throws ImportException {
    List<GebietModel> ret = new ArrayList<GebietModel>();

    Element eml630 = readXMLRoot(_definition, EML);
    Element optionsList = eml630.getFirstChildElement(EML_OPTIONS_LIST, NS_EML);
    Element election = optionsList.getFirstChildElement(EML_ELECTION, NS_EML);

    Element gebiete = election.getFirstChildElement(ED_ELECTION_TREE, NS_KR);
    Elements nodes = gebiete.getChildElements(ED_REGION, NS_KR);

    Element electionIdentifier = election.getFirstChildElement(WAHL_IDENTIFIER, NS_EML);
    Element ec = electionIdentifier.getFirstChildElement(WAHL_ART, NS_EML);
    String value = ec.getValue();
    ElectionCategory electionCategory = ElectionCategory.valueOf(value);
    for (int gebietsIdx = 0; gebietsIdx < nodes.size(); ++gebietsIdx) {
      Element node = nodes.get(gebietsIdx);
      String gebietsartStr = getAttribute(node, ATTR_ED_REGION_CATEGORY);

      // For Eilands Referenda the region type in the EML630 is GEMEENTE, but in program 4 it must
      // be ISLANDSGEMEENTE
      int gebietsart = _gebietsart;
      if (electionCategory.isOnIsland()) {
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
        throw new ImportException(_definition, Messages
            .bind(MessageKeys.Error_GebietNr_0_HatKeinenText, nummer));
      }
      gebiet.setName(name.getValue());
      ret.add(gebiet);
    }
    if (ret.size() == 1) {
      _gebietsNr = ret.get(0).getNummer();
    }
    Collections.sort(ret, new GebietModel.GebietsComparator());
    LOGGER.debug(Messages.bind(MessageKeys.Result_Tracelog_AnzahlGebiete_0_1, _gebietsart, ret
        .size()));
    return ret;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.AbstractImportMetadata#updateGebietsartWurzel()
   */
  @Override
  protected void updateGebietsartWurzel() {
    if (_electionCategory.isOnIsland()) {
      _gebietsart = GEBIETSART_INSELGEMEINDE;
    } else if (_electionCategory.equals(ElectionCategory.LR) || _level == EBENE_PSB) {
      _gebietsart = GEBIETSART_GEMEINDE;
    } else if (_level == EBENE_CSB) {
      if (_electionCategory.equals(ElectionCategory.PR)) {
        _gebietsart = GEBIETSART_LAND;
      } else {
        _gebietsart = GEBIETSART_BUND;
      }
    } else if (_level == EBENE_HSB) {
      _gebietsart = GEBIETSART_WAHLKREIS;
    }
    LOGGER.info(Messages
        .bind(MessageKeys.Result_Tracelog_BestimmeGebietsartFuerWahlart_0_Ebene_1_2,
            _electionCategory,
            _level,
            _gebietsart));
    if (_gebietsart < 0) {
      throw new RuntimeException(Messages
          .bind(MessageKeys.Error_KeineGebietsartBestimmtFuerWahlart_0_undEbene_1,
              _electionCategory,
              _level));
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(Messages.bind(MessageKeys.Result_Tracelog_Referendumdefinition))
        .append(_definition == null ? NULL_STR : _definition.getPath()).append(LF);
    appendWurzelAndGebietsauswahlAndLevelAndStatusAndCategoryAndFehlermeldung(sb);
    return sb.toString();
  }

  @Override
  protected void updateSecurityLevel(Element eml) {
    // nothing to do
  }

}
