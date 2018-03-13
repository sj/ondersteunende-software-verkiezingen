/*
 * ReportNameComponents
 * 
 * Created on 13.01.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.util.List;

/**
 * Provides name components for file names and folder names to the report generator
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface ReportNameComponents {

  /**
   * @return the election identifier that is also the name of the export root folder
   */
  public String getElectionIdentifier();

  /**
   * @return <code>true</code>, if all files are in a subfolder of the root folder that is named by
   *         the election identifier. <code>false</code> otherwise
   */
  public boolean useElectionIdentifier();

  /**
   * @return the id of the program like P1, P2 etc. that is part of the folders in the export root
   *         folder
   */
  public ReportGeneratingProgram getProgramId();

  /**
   * @return Components of the name that are independent of the document type
   */
  public List<String> getNameComponents();

  /**
   * @return The first component of the EML file name
   */
  public String getEmlFileFirstName();

  /**
   * @return the program specific extension to the path of the output directory
   */
  public String getSpecificPathExtension();

  /**
   * @return <code>true</code> if the managing authority is a regional public body (openbaar
   *         lichaam) of the BES islands
   */
  public boolean isOpenbaarLichaam();

}