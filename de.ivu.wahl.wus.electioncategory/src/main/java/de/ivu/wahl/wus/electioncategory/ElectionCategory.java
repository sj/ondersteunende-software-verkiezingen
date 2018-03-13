/*
 * ElectionCategory
 * 
 * Created on 03.04.2009
 * Copyright (c) 2009-2013 Kiesraad
 */
package de.ivu.wahl.wus.electioncategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Corresponds to the values of the EML element &lt;eml:ElectionCategory&gt;
 * <p>
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public enum ElectionCategory {
  /** Should be used instead of null */
  NONE(0),

  /** European Parliament */
  EP(0x31),

  /** Eerste Kamer (Senate) */
  EK(0x39),

  /** Tweede Kamer (House of Representatives) */
  TK(0x30),

  /** Provinciale Staaten */
  PS(0x32),

  /** Gemeenteraad */
  GR(0x33),

  /** Eilandsraad */
  ER(0x34),

  /** National referendum */
  NR(0x36),

  /** Provincial referendum */
  PR(0x37),

  /** Local referendum */
  LR(0x38),

  /** Islands referendum */
  IR(0x40),

  /** Bestuurscommissie - Amsterdam */
  BC(0x41),

  /** Gebiedscommissie - Rotterdam */
  GC(0x42),

  /** Waterschapsverkiezing */
  AB(0x43);

  private int wahlart;

  private ElectionCategory(int wahlart) {
    this.wahlart = wahlart;
  }

  // ******** String / Integer - Enum - Conversion *********** //

  /**
   * @return the EML representation
   */
  public String getEmlRepresentation() {
    if (this.equals(NONE)) {
      return "";
    } else {
      return toString();
    }
  }

  /**
   * @return the enum value for the given emlRepresentation. If no enum is found, return NONE.
   */
  public static ElectionCategory fromValue(String emlRepresentation) {
    for (ElectionCategory c : ElectionCategory.values()) {
      if (c.getEmlRepresentation().equals(emlRepresentation)) {
        return c;
      }
    }
    return NONE;
  }

  /**
   * @return the corresponding value of the attribute wahlart used in programs P4 and P5
   */
  public int getWahlart() {
    return wahlart;
  }

  /**
   * @return the enum value for the given wahlart. If no enum is found, return NONE.
   */
  public static ElectionCategory fromWahlart(int wahlart) {
    for (ElectionCategory each : ElectionCategory.values()) {
      if (each.getWahlart() == wahlart) {
        return each;
      }
    }
    return NONE;
  }

  public List<ElectionSubcategory> getSubcategories() {
    ArrayList<ElectionSubcategory> result = new ArrayList<ElectionSubcategory>();
    switch (this) {
      case EP :
        result.add(ElectionSubcategory.EP);
        break;
      case EK :
        result.add(ElectionSubcategory.EK);
        break;
      case TK :
        result.add(ElectionSubcategory.TK);
        break;
      case PS :
        result.add(ElectionSubcategory.PS1);
        result.add(ElectionSubcategory.PS2);
        break;
      case AB :
        result.add(ElectionSubcategory.AB1);
        result.add(ElectionSubcategory.AB2);
        break;
      default :
      case GR :
        result.add(ElectionSubcategory.GR1);
        result.add(ElectionSubcategory.GR2);
        break;
      case ER :
        result.add(ElectionSubcategory.ER1);
        break;
      case NR :
        result.add(ElectionSubcategory.NR);
        break;
      case PR :
        result.add(ElectionSubcategory.PR);
        break;
      case LR :
        result.add(ElectionSubcategory.LR);
        break;
      case IR :
        result.add(ElectionSubcategory.IR);
        break;
      case BC :
        result.add(ElectionSubcategory.BC);
        break;
      case GC :
        result.add(ElectionSubcategory.GC);
        break;
    // nothing to add
    }
    return result;
  }

  // ******** Testing ********** /

  public boolean isElectionDomainNeeded() {
    return !Arrays.asList(EP, EK, TK, NR, NONE).contains(this);
  }

  public boolean isElectionDomainIdNeeded() {
    return isMunicipalityElection() || LR.equals(this) || IR.equals(this) || AB.equals(this);
  }

  public boolean isReferendum() {
    return Arrays.asList(NR, PR, LR, IR).contains(this);
  }

  public boolean isOnIsland() {
    return ER.equals(this) || IR.equals(this);
  }

  /**
   * @return <code>true</code> for elections to the Gemeenteraad, Eilandsraad and Deelraad
   */
  public boolean isMunicipalityElection() {
    return Arrays.asList(GR, ER, BC, GC).contains(this);
  }

  /**
   * @return true, if for this election there is a postal electoral office (which is always in
   *         's-Gravenhage)
   */
  public boolean hasPostalVotes() {
    return Arrays.asList(TK, EP, NR).contains(this);
  }

  public boolean isEP() {
    return this.equals(EP);
  }

  public boolean isEK() {
    return this.equals(EK);
  }

  public boolean isIntendedSettlementAgreementsRequired() {
    return isMunicipalityElection() && !this.equals(GC) || this.equals(PS) || this.equals(AB);
  }

}