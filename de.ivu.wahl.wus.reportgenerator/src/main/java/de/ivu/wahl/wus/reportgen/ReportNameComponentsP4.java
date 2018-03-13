/*
 * ReportNameComponentsP4
 * 
 * Created on 31.03.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nu.xom.Document;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * To create an instance, use the factory method.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class ReportNameComponentsP4 implements ReportNameComponents, ReportNameComponentsSetter {
  private static final String UNDERSCORE = "_";

  public static ReportNameComponents fromEML510(Document eml510) {
    return fromEML510(eml510, true);
  }

  /**
   * @param complete <code>true</code> if candidate votes are included in the eml510,
   *          <code>false</code> otherwise
   */
  public static ReportNameComponents fromEML510(Document eml510, boolean complete) {
    ReportNameComponentsP4 inst = new ReportNameComponentsP4(complete);
    new ReportNameComponentsFactory(eml510).fill(inst);
    return inst;
  }

  /**
   * @return <code>true</code> if candidate results are included, <code>false</code> if only the
   *         list results are
   */
  private final boolean _complete;

  private EMLMessageType _emlType;
  private ElectionCategory _electionCategory;
  private String _createdByAuthority;
  private String _managingAuthority;
  private String _managingAuthorityId;
  private String _electionIdentifier;
  private String _electionDomain;

  /**
   * Constructor
   * 
   * @param complete <code>true</code> if candidate votes are included in the eml510,
   *          <code>false</code> otherwise
   */
  public ReportNameComponentsP4(boolean complete) {
    this._complete = complete;
  }

  public ReportGeneratingProgram getProgramId() {
    return ReportGeneratingProgram.P4;
  }

  public String getEmlFileFirstName() {
    if (isEML510d()) {
      if (_complete) {
        return RgConstants.FILENAME_510D_COMPLETE;
      } else {
        return RgConstants.FILENAME_510D_LISTS;
      }
    } else {
      if (_complete) {
        return RgConstants.FILENAME_510_COMPLETE;
      } else {
        return RgConstants.FILENAME_510_LISTS;
      }
    }
  }

  public String getElectionIdentifier() {
    return _electionIdentifier;
  }

  private boolean isEML510a() {
    return EMLMessageType.EML510a.equals(_emlType);
  }

  private boolean isEML510b() {
    return EMLMessageType.EML510b.equals(_emlType);
  }

  private boolean isEML510c() {
    return EMLMessageType.EML510c.equals(_emlType);
  }

  private boolean isEML510d() {
    return EMLMessageType.EML510d.equals(_emlType);
  }

  public List<String> getNameComponents() {
    List<String> result = new ArrayList<String>();

    if (isEML510d() && _electionCategory.isElectionDomainNeeded()) {
      // add ElectionIdentifier part upto (excluding) the first underscore
      String ei = getElectionIdentifier();
      int pos = ei.indexOf(UNDERSCORE);
      if (pos > 0) {
        ei = ei.substring(0, pos);
      }
      result.add(ei);

      // add text "provincie" or "gemeente" etc.
      if (ElectionCategory.PS.equals(_electionCategory)
          || ElectionCategory.PR.equals(_electionCategory)) {
        add(RgConstants.FILENAME_FRAGMENT_PROVINCE, result);
      } else if (ElectionCategory.AB.equals(_electionCategory)) {
        add(RgConstants.FILENAME_FRAGMENT_WATER_BOARD, result);
      } else if (ElectionCategory.GR.equals(_electionCategory)
          || ElectionCategory.LR.equals(_electionCategory)) {
        add(RgConstants.FILENAME_FRAGMENT_MUNICIPALITY, result);
      } else if (ElectionCategory.ER.equals(_electionCategory)
          || ElectionCategory.IR.equals(_electionCategory)) {
        add(RgConstants.FILENAME_FRAGMENT_ISLAND_MUNICIPALITY, result);
      } else if (isBcAmsterdam()) {
        add(RgConstants.FILENAME_FRAGMENT_BOROUGH_AMSTERDAM, result);
      } else if (isGcRotterdam()) {
        add(RgConstants.FILENAME_FRAGMENT_BOROUGH_ROTTERDAM, result);
      }

      // add election domain (= rest of the election identifier)
      add(_electionDomain, result);

    } else {
      // Not EML 510d -> take the complete ElectionIdentifier
      result.add(getElectionIdentifier());
    }

    // add text "gemeente" or "kieskring" etc.
    if (isEML510b()) {
      if (isEK()) {
        add(RgConstants.FILENAME_FRAGMENT_STEMBUREAU, result);
      } else if (!isGrOrErOrBcOrGcOrLrOrIr()) {
        if (isOpenbaarLichaam()) {
          add(RgConstants.FILENAME_FRAGMENT_ISLAND_MUNICIPALITY, result);
        } else {
          add(RgConstants.FILENAME_FRAGMENT_MUNICIPALITY, result);
        }
      }
    }
    if (isEML510c()) {
      if (isEK()) {
        add(RgConstants.FILENAME_FRAGMENT_PROVINCE, result);
      } else {
        add(RgConstants.FILENAME_FRAGMENT_DISTRICT, result);
      }
    }

    // add "stembureau" + ID
    if (isEML510a()) {
      if (!isGrOrErOrBcOrGcOrLrOrIr()) {
        add(_createdByAuthority, result);
      }
      if (_managingAuthorityId != null) {
        add(RgConstants.FILENAME_FRAGMENT_STEMBUREAU + " " + _managingAuthorityId.substring(2), //$NON-NLS-1$
            result);
      }
    }

    // omit managingAuthority for 510d and for 510b in GR, BC and GC elections
    if (!isEML510d() && !(isEML510b() && isGrOrErOrBcOrGcOrLrOrIr())) {
      add(_managingAuthority, result);
    }

    return result;
  }

  public boolean isOpenbaarLichaam() {
    return Arrays.asList("Bonaire", "SintEustatius", "Sint Eustatius", "Saba")
        .contains(_managingAuthority);
  }

  private boolean isEK() {
    return _electionCategory != null && _electionCategory.isEK();
  }

  private boolean isGrOrErOrBcOrGcOrLrOrIr() {
    return ElectionCategory.GR.equals(_electionCategory)
        || ElectionCategory.ER.equals(_electionCategory)
        || ElectionCategory.BC.equals(_electionCategory)
        || ElectionCategory.GC.equals(_electionCategory)
        || ElectionCategory.LR.equals(_electionCategory)
        || ElectionCategory.IR.equals(_electionCategory);
  }

  private boolean isBcAmsterdam() {
    return ElectionCategory.BC.equals(_electionCategory)
        && _electionDomain.startsWith(RgConstants.CITY_AMSTERDAM);
  }

  private boolean isGcRotterdam() {
    return ElectionCategory.GC.equals(_electionCategory)
        && !_electionDomain.startsWith(RgConstants.CITY_AMSTERDAM);
  }

  private void add(String string, List<String> list) {
    if (string != null) {
      list.add(string);
    }
  }

  public String getSpecificPathExtension() {
    return ""; //$NON-NLS-1$
  }

  public boolean useElectionIdentifier() {
    return false;
  }

  // ********* Setter *************

  public void setElectionIdentifier(String electionIdentifier) {
    _electionIdentifier = electionIdentifier;
  }

  public void setEmlType(EMLMessageType emlType) {
    _emlType = emlType;
  }

  public void setElectionCategory(ElectionCategory electionCategory) {
    _electionCategory = electionCategory;
  }

  public void setCreatedByAuthority(String createdByAuthority) {
    _createdByAuthority = createdByAuthority;
  }

  public void setManagingAuthority(String managingAuthority) {
    _managingAuthority = managingAuthority;
  }

  public void setManagingAuthorityId(String managingAuthorityId) {
    _managingAuthorityId = managingAuthorityId;
  }

  public void setElectionDomain(String electionDomain) {
    _electionDomain = electionDomain;
  }

  // TODO [Prio 3] jon 17.12.2009 Should not be public, because this should be inside the
  // ReportConfiguration
  boolean isComplete() {
    return _complete;
  }

}
