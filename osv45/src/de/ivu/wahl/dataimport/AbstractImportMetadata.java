/*
 * AbstractImportMetadata
 * 
 * Created on 12.08.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import java.util.List;

import nu.xom.Element;

import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public abstract class AbstractImportMetadata extends AbstractImportEML {

  /**
   * Constructor
   */
  protected AbstractImportMetadata(int modus, int level) {
    super(modus, level);
  }

  /**
   * Reads <code>_electionDomain</code> from the <code>electionIdentifier</code> element. Checks it
   * against the <code>electionCategory</code>. Errors are written into <code>_fehlermeldung</code>.
   * 
   * @throws ImportException
   */
  protected void readAndCheckElectionDomain(Element electionIdentifier,
      Element art,
      ElectionCategory electionCategory,
      String source) throws ImportException {
    _electionDomain = readElectionDomain(electionIdentifier);
    if (_electionDomain == null && electionCategory.isElectionDomainNeeded()) {
      LOGGER.error(Messages.bind(MessageKeys.Error_NoElectionDomainFoundIn_0, source));
      throw new ImportException(Messages.bind(MessageKeys.Error_NoElectionDomainFoundIn_0, source));
    } else if (_electionDomain != null && !_electionCategory.isElectionDomainNeeded()) {
      LOGGER.error(Messages.bind(MessageKeys.Error_NoElectionDomainExpectedFor_0, art));
      throw new ImportException(Messages.getString(Messages
          .bind(MessageKeys.Error_NoElectionDomainExpectedFor_0, art)));
    }
  }

  protected void readAndCheckElectionDomainId(Element electionIdentifier,
      Element art,
      ElectionCategory electionCategory,
      String source) throws ImportException {
    _electionDomainId = readElectionDomainId(electionIdentifier);
    if (_electionDomainId == null && _electionCategory.isElectionDomainIdNeeded()) {
      LOGGER.error(Messages.bind(MessageKeys.Error_NoElectionDomainIdFoundIn_0, source));
      throw new ImportException(Messages
          .bind(MessageKeys.Error_NoElectionDomainIdFoundIn_0, source));
    } else if (_electionDomainId != null && !electionCategory.isElectionDomainIdNeeded()) {
      LOGGER.error(Messages.bind(MessageKeys.Error_NoElectionDomainIdExpectedFor_0, art));
      throw new ImportException(Messages.getString(Messages
          .bind(MessageKeys.Error_NoElectionDomainIdExpectedFor_0, art)));
    }
  }

  protected void finishInitialization() throws ImportException {
    if (_modus == MODE_DB_P4) {
      finishInitializationP4();
    } else {
      finishInitializationP5();
    }
  }

  protected void finishInitializationP4() throws ImportException {
    if (_gebietsart == -1) {
      updateGebietsartWurzel();
      _gebietsauswahl = readGebietsauswahl();
    }
    if (_gebietsauswahl.size() == 1 || _gebietsNr != -1) {
      _status = STATUS_KOMPLETT;
    }
  }

  protected void finishInitializationP5() throws ImportException {
    updateGebietsartWurzel();
    _gebietsauswahl = readGebietsauswahl();
    if (_gebietsauswahl.size() == 1 && _gebietsNr != -1) {
      _status = STATUS_KOMPLETT;
    } else {
      // Should never happen
      _fehlermeldung = Messages.getString(MessageKeys.Error_GebietNichtEindeutigBestimmt);
    }
  }

  /**
   * Determines the proposal list for the selection of root region by the user
   * 
   * @return List of regions
   * @throws ImportException
   */
  abstract protected List<GebietModel> readGebietsauswahl() throws ImportException;

  /**
   * Determines root region category from election level and election category
   */
  abstract protected void updateGebietsartWurzel();

  protected void appendWurzelAndGebietsauswahlAndLevelAndStatusAndCategoryAndFehlermeldung(StringBuilder sb) {
    sb.append(Messages.bind(MessageKeys.Result_Tracelog_GebietsartWurzel)).append(_gebietsart)
        .append(LF);
    sb.append(Messages.bind(MessageKeys.Result_Tracelog_GebietsnummerWurzel)).append(_gebietsNr)
        .append(LF);
    sb.append(Messages.bind(MessageKeys.Result_Tracelog_Gebietsauswahl))
        .append(_gebietsauswahl == null ? NULL_STR : _gebietsauswahl.size()).append(LF);
    appendLevelAndStatusAndCategoryAndFehlermeldung(sb);
  }

}
