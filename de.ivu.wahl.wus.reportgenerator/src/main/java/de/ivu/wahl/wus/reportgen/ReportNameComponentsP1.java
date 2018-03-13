/*
 * ReportNameComponentsP1
 * 
 * Created on 13.01.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.util.ArrayList;
import java.util.List;

public class ReportNameComponentsP1 extends AbstractReportNameComponents {
  private static final String UNDERSCORE = "_"; //$NON-NLS-1$
  private static final String MINUS = "-"; //$NON-NLS-1$
  private static final String GEEN_INLEVERAAR = "geen-inleveraar"; //$NON-NLS-1$
  private static final String GEEN_AANDUIDING = "geen-aanduiding"; //$NON-NLS-1$

  private String listName;
  private String submitterName;

  /**
   * Sets the name of the list.
   * 
   * @param listName Name of the list submitted. In case of blanko lists, the String "Lijst" + last
   *          name of first candidate should be passed as argument. (e.g. "LijstHermans")
   */
  public void setListName(String listName) {
    this.listName = listName;
  }

  public void setSubmitterName(String submitterName) {
    this.submitterName = submitterName;
  }

  public ReportGeneratingProgram getProgramId() {
    return ReportGeneratingProgram.P1;
  }

  public String getEmlFileFirstName() {
    return RgConstants.FILENAME_210;
  }

  public List<String> getNameComponents() {
    List<String> result = new ArrayList<String>();
    addElectionIdentifier(result);
    if (getElectoralDistrict() != null) {
      result.add(getElectoralDistrict());
    }
    result.add(listName == null ? GEEN_AANDUIDING : listName);
    result.add(submitterName == null ? GEEN_INLEVERAAR : submitterName);

    return result;
  }

  @Override
  public String getSpecificPathExtension() {
    String part1 = (listName == null ? GEEN_AANDUIDING : listName);
    String part2 = (submitterName == null ? GEEN_INLEVERAAR : submitterName);
    return UNDERSCORE + part1 + MINUS + part2;
  }

}
