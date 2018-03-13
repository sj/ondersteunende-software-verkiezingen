/*
 * Created on 27.01.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.util.ArrayList;
import java.util.List;

/**
 * Report name components for creating most documents of the former program P2 - model I1 and
 * omission letters.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class ReportNameComponentsP2 extends AbstractReportNameComponents {

  public ReportGeneratingProgram getProgramId() {
    return ReportGeneratingProgram.P2;
  }

  public String getEmlFileFirstName() {
    return RgConstants.FILENAME_230A;
  }

  public List<String> getNameComponents() {
    List<String> result = new ArrayList<String>();
    addElectionIdentifier(result);

    return result;
  }

}
