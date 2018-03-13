/*
 * AbstractReportNameComponents
 * 
 * Created on 27.01.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.util.List;

import de.ivu.wahl.wus.electioncategory.ElectionCategory;

public abstract class AbstractReportNameComponents
    implements
      ReportNameComponents,
      ReportNameComponentsSetter {

  private String electionIdentifier;
  private String electoralDistrict;
  private ElectionCategory _electionCategory;

  public String getElectionIdentifier() {
    return electionIdentifier;
  }

  public void setElectionIdentifier(String electionIdentifier) {
    this.electionIdentifier = electionIdentifier;
  }

  public void setElectionDomain(String electionDomain) {
    // Not needed by my subclasses
  }

  public String getElectoralDistrict() {
    return electoralDistrict;
  }

  public void setElectoralDistrict(String electoralDistrict) {
    this.electoralDistrict = electoralDistrict;
  }

  public String getSpecificPathExtension() {
    return ""; //$NON-NLS-1$
  }

  /**
   * @return <code>true</code>, if all files are in a subfolder of the root folder that is named by
   *         the election identifier. <code>false</code> otherwise
   */
  public boolean useElectionIdentifier() {
    ReportGeneratingProgram programId = getProgramId();
    return ReportGeneratingProgram.P1.equals(getProgramId())
        || ReportGeneratingProgram.P2.equals(getProgramId())
        || ReportGeneratingProgram.P3.equals(programId);
  }

  public abstract ReportGeneratingProgram getProgramId();

  public ElectionCategory getElectionCategory() {
    return _electionCategory;
  }

  public void setElectionCategory(ElectionCategory electionCategory) {
    _electionCategory = electionCategory;
  }

  protected void addElectionIdentifier(List<String> list) {
    String ei = getElectionIdentifier();
    list.add(ei);
  }

  public boolean isOpenbaarLichaam() {
    return false;
  }
}
