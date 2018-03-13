/*
 * ReportNameComponentsP3
 * 
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
public class ReportNameComponentsP0 extends AbstractReportNameComponents {

  public static ReportNameComponents fromEML630(Document eml630) {
    ReportNameComponentsP0 inst = new ReportNameComponentsP0();
    new ReportNameComponentsFactory(eml630).fill(inst);
    return inst;
  }

  /**
   * Constructor
   */
  public ReportNameComponentsP0() {
    super();
  }

  public ReportGeneratingProgram getProgramId() {
    return ReportGeneratingProgram.P0;
  }

  public String getEmlFileFirstName() {
    if (getElectionCategory().isReferendum()) {
      return RgConstants.FILENAME_630;
    }
    return RgConstants.FILENAME_110A;
  }

  public List<String> getNameComponents() {
    List<String> result = new ArrayList<String>();
    addElectionIdentifier(result);

    return result;
  }

}
