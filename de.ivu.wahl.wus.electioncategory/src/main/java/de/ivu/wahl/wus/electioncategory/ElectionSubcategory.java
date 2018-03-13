/*
 * ElectionSubcategory
 * 
 * Created on 03.04.2009
 * Copyright (c) 2009-2013 Kiesraad
 */
package de.ivu.wahl.wus.electioncategory;

import java.util.Arrays;

public enum ElectionSubcategory {
  /** Should be used instead of null */
  NONE(ElectionCategory.NONE),

  EP(ElectionCategory.EP),

  EK(ElectionCategory.EK),

  TK(ElectionCategory.TK),

  /** Provinciale Staaten with more than one electoral district */
  PS2(ElectionCategory.PS),

  /** Provinciale Staaten with one electoral district */
  PS1(ElectionCategory.PS),

  /** Waterschapsverkiezing with 19 or more members */
  AB2(ElectionCategory.AB),

  /** Waterschapsverkiezing with less than 19 members */
  AB1(ElectionCategory.AB),

  /** Gemeenteraad with 19 or more members */
  GR2(ElectionCategory.GR),

  /** Gemeenteraad with less than 19 members */
  GR1(ElectionCategory.GR),

  /** Eilandsraad with less than 19 members */
  ER1(ElectionCategory.ER),

  NR(ElectionCategory.NR),

  PR(ElectionCategory.PR),

  LR(ElectionCategory.LR),

  IR(ElectionCategory.IR),

  /** Bestuurscommissie - Amsterdam */
  BC(ElectionCategory.BC),

  /** Gebiedscommissie - Rotterdam */
  GC(ElectionCategory.GC);

  private ElectionCategory electionCategory;

  /**
   * Constructor
   */
  private ElectionSubcategory(ElectionCategory electionCategory) {
    this.electionCategory = electionCategory;
  }

  public ElectionCategory getElectionCategory() {
    return electionCategory;
  }

  // ******** String - Enum - Conversion *********** //

  /**
   * @return the EML representation
   */
  public String getEmlRepresentation() {
    if (this.equals(NONE)) {
      return "";
    }
    return toString();
  }

  /**
   * @param emlRepresentation
   * @return the enum value for the given emlRepresentation. If no enum value is found, return NONE.
   */
  public static ElectionSubcategory fromEmlRepresentation(String emlRepresentation) {
    for (ElectionSubcategory c : ElectionSubcategory.values()) {
      if (c.getEmlRepresentation().equals(emlRepresentation)) {
        return c;
      }
    }
    return NONE;
  }

  /**
   * @return <code>true</code>, if there is more than one electoral district in this election.
   */
  public boolean isElectionWithMultipleDistricts() {
    return Arrays.asList(EP, EK, TK, PS2).contains(this);
  }

  /**
   * @return <code>true</code>, if there is more than one electoral district in this election and a
   *         political grouping may submit different lists in different electoral districts.
   */
  public boolean isElectionWithListGroups() {
    return Arrays.asList(EK, TK, PS2).contains(this);
  }

  /**
   * @return <code>true</code>, if there is more that one authority where lists may be submitted for
   *         different electoral districts.
   */
  // TODO probably deprecated, see OSV-1345
  public boolean isElectionWithDecentralSubmission() {
    return Arrays.asList(TK, PS2).contains(this);
  }

  /**
   * @return <code>true</code> if candidates may designate an agent according to articles H 10a or R
   *         9a resp. of the Kieswet.
   */
  public boolean isH10aAgentAllowed() {
    return Arrays.asList(EK, TK, PS2).contains(this);
  }

  public boolean isEK() {
    return electionCategory.isEK();
  }

}