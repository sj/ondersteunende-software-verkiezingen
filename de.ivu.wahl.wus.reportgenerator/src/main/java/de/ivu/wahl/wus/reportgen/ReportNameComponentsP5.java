/*
 * Created on 27.01.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.util.ArrayList;
import java.util.List;

import nu.xom.Document;

/**
 * To create an instance, use one of the factory methods.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class ReportNameComponentsP5 extends AbstractReportNameComponents {

  public static ReportNameComponents fromEML520(Document eml520) {
    ReportNameComponentsP5 inst = new ReportNameComponentsP5();
    new ReportNameComponentsFactory(eml520).fill(inst);
    return inst;
  }

  /**
   * Constructor
   */
  public ReportNameComponentsP5() {
    super();
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
