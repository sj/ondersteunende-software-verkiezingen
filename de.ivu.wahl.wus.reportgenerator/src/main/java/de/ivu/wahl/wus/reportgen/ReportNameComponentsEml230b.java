/*
 * ReportNameComponentsEml230b
 * 
 * Created on 28.01.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.util.ArrayList;
import java.util.List;

import nu.xom.Document;
import nu.xom.converters.DOMConverter;
import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;

public class ReportNameComponentsEml230b extends AbstractReportNameComponents {

  private ElectionSubcategory _electionSubcategory;

  public static ReportNameComponents fromEML230b(org.w3c.dom.Document eml230b) {
    Document convertedEml230b = DOMConverter.convert(eml230b);

    ReportNameComponentsEml230b inst = new ReportNameComponentsEml230b();
    new ReportNameComponentsFactory(convertedEml230b).fill(inst);
    return inst;
  }

  public ReportNameComponentsEml230b() {
  }

  public ReportGeneratingProgram getProgramId() {
    return ReportGeneratingProgram.P3;
  }

  public String getEmlFileFirstName() {
    return RgConstants.FILENAME_230B;
  }

  public List<String> getNameComponents() {
    List<String> result = new ArrayList<String>();
    addElectionIdentifier(result);

    if (_electionSubcategory != null && _electionSubcategory.isElectionWithListGroups()
        && getElectoralDistrict() != null) {
      result.add(getElectoralDistrict());
    }

    return result;
  }

  public void setElectionSubcategory(ElectionSubcategory electionSubcategory) {
    _electionSubcategory = electionSubcategory;
  }

}
