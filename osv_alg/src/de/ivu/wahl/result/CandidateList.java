/*
 * CandidateList
 * 
 * Created on 15.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result;

import java.util.List;

import de.ivu.wahl.result.determination.P2List;
import de.ivu.wahl.result.determination.P3List;

/**
 * List of candidates admitted to an election in an electoral district
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface CandidateList extends NamedObject, Comparable<CandidateList> {
  /**
   * @return the {@link ElectoralDistrict} the list belongs to
   */
  public ElectoralDistrict getElectoralDistrict();

  /**
   * @return the name of the political party, empty for blanko lists.
   */
  public String getPoliticalGroupingName();

  /**
   * @return The candidates on the list in the order of the nomination
   */
  public List<Candidate> getCandidates();

  /**
   * @return The official number of the list. Is the same for all {@link CandidateList}'s in the
   *         {@link P3List}.
   */
  public int getListNumber();

  /**
   * For blanko lists this looks like "lijst 13 (Groningen)", for others like "ABC (Groningen)"
   * 
   * @return Complete name combined of politicalGroupingName and electoralDistrict name.
   */
  public String getName();

  /**
   * For blanko lists this looks like "lijst 13", for others like "lijst 25, ABC"
   * 
   * @return "lijst" + list number + comma + Name of the political group (if present)
   */
  public String getListNumberWithName();

  /**
   * For blanko lists this looks like "lijst 13", for others like "ABC"
   * 
   * @return "lijst" + list number for blanko lists, otherwise the name of the political group
   */
  public String getListNumberOrName();

  /**
   * @return the {@link P2List} the {@link CandidateList} belongs to
   */
  public P2List getP2List();

}
