/*
 * Created on 06.09.2010
 * Copyright (c) 2010 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.util.ArrayList;
import java.util.List;

import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class ReportNameComponentsP5CSV extends AbstractReportNameComponents {

  /**
   * Constructor
   */
  public ReportNameComponentsP5CSV(String electionIdentifier,
      ElectionCategory electionCategory,
      String electionDomain) {
    super();
    setElectionIdentifier(electionIdentifier);
    setElectionCategory(electionCategory);
  }

  public ReportGeneratingProgram getProgramId() {
    return ReportGeneratingProgram.P5;
  }

  public String getEmlFileFirstName() {
    return RgConstants.FILENAME_520;
  }

  public List<String> getNameComponents() {
    List<String> result = new ArrayList<String>();
    addElectionIdentifier(result);

    return result;
  }

}
