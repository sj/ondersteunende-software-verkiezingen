/*
 * ReportNameComponentsSetter
 * 
 * Created on 03.04.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import de.ivu.wahl.wus.electioncategory.ElectionCategory;

public interface ReportNameComponentsSetter {

  public void setElectionIdentifier(String electionIdentifier);

  public void setElectionCategory(ElectionCategory electionCategory);

  public void setElectionDomain(String electionDomain);

}