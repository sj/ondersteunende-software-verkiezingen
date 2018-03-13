/*
 * ReportGenerator
 * 
 * Created on 11.11.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import nu.xom.Document;

public interface ReportGenerator {
  public static final String CREATED_BY_OSV = "Created by: Ondersteunende Software Verkiezingen by IVU Traffic Technologies AG";
  public static final String CREATED_BY_OSV_PROGRAM_VERSION = CREATED_BY_OSV
      + ", program: {0}, version: {1}";

  /**
   * Generates output documents from XML data from a given source as specified in the
   * ReportConfiguration.
   * 
   * @param config configures the report that is to be generated
   * @param source The XML Source. May for example be a file source (see {@link StreamSource}) or a
   *          DOM source (see {@link DOMSource}).
   * @return the output directory as String
   * @throws IOException in case of various file related problems
   * @throws RuntimeException in case of any unexpected errors
   */
  public String createReport(ReportConfiguration config, Source source)
      throws IOException, RuntimeException;

  /**
   * Creates multiple documents with the same source.
   * {@link ReportGenerator#createReport(ReportConfiguration, Source)}.
   */
  public List<String> createReports(Collection<ReportConfiguration> configs, Source source)
      throws IOException, RuntimeException;

  /**
   * Generates output documents from XML data from a given source as specified in the
   * ReportConfiguration.
   * 
   * @param config configures the report that is to be generated
   * @param source XOM Document
   * @return the output directory as String
   * @throws IOException in case of various file related problems
   * @throws RuntimeException in case of any unexpected errors
   * @throws ParserConfigurationException in case of invalid xom structure
   */
  public String createReport(ReportConfiguration config, Document source)
      throws IOException, RuntimeException, ParserConfigurationException;

  /**
   * Generates the contract document for the MTP (
   * 
   * @param config configures the report that is to be generated
   * @param source The XML Source. May for example be a file source (see {@link StreamSource}) or a
   *          DOM source (see {@link DOMSource}).
   * @return the output directory as String
   * @throws IOException in case of various file related problems
   * @throws RuntimeException in case of any unexpected errors
   */
  public String createMTP1Report(ReportConfigurationMTP config, Source source)
      throws IOException, RuntimeException;

  /**
   * Creates a pure text file with the content of the String csv. This contains an overview of the
   * counted votes.
   * 
   * @return the output directory as String
   * @throws IOException
   * @throws RuntimeException
   */
  public String createCsvExportFile(ReportConfiguration config, String csv)
      throws IOException, RuntimeException;

}
