/*
 * ImportEML510
 * 
 * Created on 27.04.2007
 * Copyright (c) 2007-2017 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import java.io.IOException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.FinderException;

import nu.xom.Element;

import de.ivu.ejb.IVUBeanBase;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.Repository;
import de.ivu.wahl.modell.ejb.RepositoryHome;
import de.ivu.wahl.util.EMLFilenameCheck;
import de.ivu.wahl.util.XMLImportHelper;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;
import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;
import de.ivu.wahl.wus.xmlsecurity.XmlParseException;

/**
 * Java-Entsprechung der Konfigurationsdatei f�r den Import der Metadaten einer Wahl
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class ImportEML510 extends AbstractImportEML {

  public static final String REPOSITORY_FILENAME_510 = "FILENAME_510"; //$NON-NLS-1$
  public static final String REPOSITORY_LAST_GEBIETNAME_510 = "LAST_GEBIETNAME_510"; //$NON-NLS-1$
  public static final String REPOSITORY_LAST_IMPORT_510 = "LAST_IMPORT_510"; //$NON-NLS-1$

  private String _lastGebietName;
  private String _lastFileName;
  private String _lastImport;

  /**
   * @param level
   * @param modus
   * @param gebArt
   * @param gebNr
   */
  public ImportEML510(int level, int modus, int gebArt, int gebNr) {
    super(modus, level);
    reset();
    setImportType(ImportType.EML510);
    _gebietsart = gebArt;
    _gebietsNr = gebNr;
  }

  @Override
  public void updateStatusDb() {
    _fehlermeldung = EMPTY_STR;
    if (STATUS_INIT == _status) {
      if (_EML510 == null) {
        _fehlermeldung += Messages.bind(MessageKeys.Error_BitteGebenSieDie510An) + "<br>"; //$NON-NLS-1$
        return;
      }
      if (!checkFilename()) {
        _fehlermeldung += Messages.bind(MessageKeys.Error_FalschesDateinamenFormat);
        return;
      }
      try {
        _hashCodeSplitter510 = new HashCodeSplitter(_hashWertErmittlung.createDigest(_EML510
            .openStream()));
      } catch (XmlParseException e) {
        _fehlermeldung += Messages.bind(MessageKeys.Error_FalschesDateiFormat);
        return;
      } catch (IOException e) {
        _fehlermeldung += Messages.bind(MessageKeys.Error_FehlerBeimLesenDerDatei_0,
            _EML510.toExternalForm())
            + ' ' + e.getMessage();
        return;
      }
    }
    // check for schema
    Element eml = readXMLRootAndCheckSchemas();
    if (eml == null) {
      return;
    }
    // check EML id
    if (!checkEML510Id(eml)) {
      return;
    }
    _status = STATUS_URL_KOMPLETT;
    // check hash code
    updateSecurityLevel(eml);
    if (!checkHashwert510()) {
      return;
    }
    // _status = STATUS_HASH_CHECKED; /* obsolete */
    _status = STATUS_KOMPLETT;

    // print log
    APP_LOGGER.info(Messages.bind(MessageKeys.Logger_ImportFile_0_WithHash_1,
        EMLFilenameCheck.reduceFilenameFromSlashAsPrefix(_EML510.getFile()),
        getEML510()));
  }

  @Override
  protected void updateStatusStandAlone() {
    // there is only status in DB for EML 510 import
    updateStatusDb();
  }

  private boolean checkEML510Id(Element eml510) {
    String id = XMLImportHelper.getAttribute(eml510, XMLTags.ATTR_EML_ID);
    String idExpected;
    // Set expected id
    switch (_level) {
      case GebietModel.EBENE_CSB :
        switch (_modus) {
          case MODE_DB_P5 :
            idExpected = XMLTags.ATTR_VAL_EML_ID_510d;
            break;
          default :
            idExpected = XMLTags.ATTR_VAL_EML_ID_510c;
        }
        break;
      case GebietModel.EBENE_HSB :
        idExpected = XMLTags.ATTR_VAL_EML_ID_510b;
        break;
      default :
        idExpected = XMLTags.ATTR_VAL_EML_ID_510a;
    }
    if (!idExpected.equals(id)) {
      _fehlermeldung += Messages.bind(MessageKeys.Error_Erwartet_0_Gefunden_1, idExpected, id);
      return false;
    }
    return true;
  }

  /**
   * @return
   */
  private boolean checkFilename() {
    if (_EML510 == null) {
      return false;
    }
    String filename = _EML510.getFile();
    switch (_level) {
      case GebietModel.EBENE_CSB :
        switch (_modus) {
          case MODE_DB_P4 :
            return EMLFilenameCheck.is510cFilename(filename);
          case MODE_DB_P5 :
            return EMLFilenameCheck.is510dFilename(filename);
        }
        break;
      case GebietModel.EBENE_HSB :
        return EMLFilenameCheck.is510bFilename(filename);
      case GebietModel.EBENE_PSB :
        return EMLFilenameCheck.is510aFilename(filename);
    }
    return false;
  }

  private boolean checkHashwert510() {
    if (SecurityLevel.NO_HASH_CODE.equals(_securityLevel)) {
      return true;
    }
    if (_hashCodeSplitter510.isInputComplete()) {
      if (_hashCodeSplitter510.checkInput()) {
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

  private Element readXMLRootAndCheckSchemas() {
    Element eml = null;
    try {
      eml = ImportUtil.readXMLRoot4Schema(_EML510, XMLTags.SCHEMA_EML510);
    } catch (Exception e) {
      LOGGER.error(e, e);
      _fehlermeldung = Messages.bind(MessageKeys.Error_DateiEntsprichtNichtDerSchemadefinition);
      if (e.getMessage() != null) {
        _fehlermeldung += ": " + e.getMessage(); //$NON-NLS-1$
      }
      return null;
    }
    return eml;
  }

  /**
   * Initializing for a new Import
   */
  @Override
  public void reset() {
    super.reset();
    _lastGebietName = null;
    _lastFileName = null;
    _lastImport = null;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("EML: ").append(_EML510 == null ? NULL_STR : _EML510.getPath()).append(LF); //$NON-NLS-1$
    appendLevelAndStatusAndCategoryAndFehlermeldung(sb);
    return sb.toString();
  }

  public String getLastGebietName() throws Exception {
    if (_lastGebietName == null) {
      Collection<Repository> findAllByName;
      final String lastGebietnameKey = REPOSITORY_LAST_GEBIETNAME_510 + '_' + getGebietsart() + '_'
          + getGebietsNr();
      try {
        findAllByName = rHome.findAllByName(lastGebietnameKey);
        if (!findAllByName.isEmpty()) {
          _lastGebietName = findAllByName.iterator().next().getWert();
        }
      } catch (FinderException e) {
        LOGGER.error(e);
        throw new Exception(e);
      }
    }
    return _lastGebietName;
  }

  public void setLastGebietName(String lastGebietName) throws Exception {
    _lastGebietName = lastGebietName;
    Collection<Repository> findAllByName;
    final String lastGebietnameKey = REPOSITORY_LAST_GEBIETNAME_510 + '_' + getGebietsart() + '_'
        + getGebietsNr();
    try {
      findAllByName = rHome.findAllByName(lastGebietnameKey);
      if (!findAllByName.isEmpty()) {
        Repository next = findAllByName.iterator().next();
        next.setWert(lastGebietName);
      } else {
        Repository rm = rHome.create(IVUBeanBase.getUniqueKey(), lastGebietnameKey);
        rm.setWert(lastGebietName);
      }
    } catch (FinderException e) {
      LOGGER.error(e);
      throw new Exception(e);
    } catch (CreateException e) {
      LOGGER.error(e);
      throw new Exception(e);
    }
  }

  public String getLastFileName() throws Exception {
    if (_lastFileName == null) {
      Collection<Repository> findAllByName;
      final String lastNameKey = REPOSITORY_FILENAME_510 + '_' + getGebietsart() + '_'
          + getGebietsNr();
      try {
        findAllByName = rHome.findAllByName(lastNameKey);
        if (!findAllByName.isEmpty()) {
          _lastFileName = findAllByName.iterator().next().getWert();
        }
      } catch (FinderException e) {
        LOGGER.error(e);
        throw new Exception(e);
      }
    }
    return _lastFileName;
  }

  public void setLastFileName(String lastFileName) throws Exception {
    _lastFileName = lastFileName;
    if (lastFileName.startsWith("/")) { //$NON-NLS-1$
      _lastFileName = lastFileName.substring(1);
    }
    Collection<Repository> findAllByName;
    try {
      final String lastFilenameKey = REPOSITORY_FILENAME_510 + '_' + getGebietsart() + '_'
          + getGebietsNr();
      findAllByName = rHome.findAllByName(lastFilenameKey);
      if (!findAllByName.isEmpty()) {
        Repository next = findAllByName.iterator().next();
        next.setWert(_lastFileName);
      } else {
        Repository rm = rHome.create(IVUBeanBase.getUniqueKey(), lastFilenameKey);
        rm.setWert(_lastFileName);
      }
    } catch (FinderException e) {
      LOGGER.error(e);
      throw new Exception(e);
    } catch (CreateException e) {
      LOGGER.error(e);
      throw new Exception(e);
    }
  }

  public String getLastImport() throws Exception {
    if (_lastImport == null) {
      Collection<Repository> findAllByName;
      String lastFilenameKey = REPOSITORY_LAST_IMPORT_510 + '_' + getGebietsart() + '_'
          + getGebietsNr();
      try {
        findAllByName = rHome.findAllByName(lastFilenameKey);
        if (!findAllByName.isEmpty()) {
          _lastImport = findAllByName.iterator().next().getWert();
        }
      } catch (FinderException e) {
        LOGGER.error(e);
        throw new Exception(e);
      }
    }
    return _lastImport;
  }

  public void setLastImport(String lastImport) throws Exception {
    _lastImport = lastImport;
    Collection<Repository> findAllByName;
    String lastImportnameKey = REPOSITORY_LAST_IMPORT_510 + '_' + getGebietsart() + '_'
        + getGebietsNr();
    try {
      findAllByName = rHome.findAllByName(lastImportnameKey);
      if (!findAllByName.isEmpty()) {
        Repository next = findAllByName.iterator().next();
        next.setWert(lastImport);
      } else {
        Repository rm = rHome.create(IVUBeanBase.getUniqueKey(), lastImportnameKey);
        rm.setWert(lastImport);
      }
    } catch (FinderException e) {
      LOGGER.error(e);
      throw new Exception(e);
    } catch (CreateException e) {
      LOGGER.error(e);
      throw new Exception(e);
    }
  }

  RepositoryHome rHome = IVUBeanBase.findLocalHomeExt("Repository"); //$NON-NLS-1$

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.AbstractImportEML#updateSecurityLevel(nu.xom.Element)
   */
  @Override
  protected void updateSecurityLevel(Element eml510) {

    if (_modus == MODE_STANDALONE) {
      // No hashcode checking in test cases
      _securityLevel = SecurityLevel.NO_HASH_CODE;

    } else if (_modus == MODE_DB_P5) {
      // Hashcode confirmation in P5
      _securityLevel = SecurityLevel.CONFIRM_HASH_CODE;

    } else {
      WahlInfo wahlInfo = WahlInfo.getWahlInfo();
      _electionCategory = wahlInfo.getElectionCategory();
      boolean isAuthoritySameRegion = isAuthoritySameRegion(eml510);
      switch (_level) {
        case GebietModel.EBENE_PSB :
          _securityLevel = SecurityLevel.CONFIRM_HASH_CODE;
          break;

        case GebietModel.EBENE_HSB :
          if (isAuthoritySameRegion) {
            _securityLevel = SecurityLevel.CONFIRM_HASH_CODE;
          } else {
            _securityLevel = SecurityLevel.ASK_FOR_HASH_CODE;
          }
          break;

        case GebietModel.EBENE_CSB :
          if (_electionCategory.isMunicipalityElection()
              || _electionCategory.equals(ElectionCategory.LR)
              || _electionCategory.equals(ElectionCategory.IR)) {
            _securityLevel = SecurityLevel.CONFIRM_HASH_CODE;
          } else if (wahlInfo.getElectionSubcategory().equals(ElectionSubcategory.PS1)
              || _electionCategory.equals(ElectionCategory.AB)) {
            _securityLevel = SecurityLevel.CONFIRM_HASH_CODE;
          } else if ((_electionCategory.equals(ElectionCategory.PS) || _electionCategory
              .equals(ElectionCategory.PR)) && isAuthoritySameRegion) {
            _securityLevel = SecurityLevel.CONFIRM_HASH_CODE;
          } else {
            _securityLevel = SecurityLevel.ASK_FOR_HASH_CODE;
          }
          break;

        default :
          _fehlermeldung = Messages.bind(MessageKeys.Error_UnbekannteWahlebene_0, _level);
      }
    }
  }

  private boolean isAuthoritySameRegion(Element eml510) {
    Gebiet rootRegion = WahlInfo.getWahlInfo().getWahl().getWurzelgebiet();
    String authorityName = readManagingAuthorityName(eml510);
    if (rootRegion != null && authorityName != null) {
      return authorityName.equals(rootRegion.getName());
    }
    return false;
  }

}
