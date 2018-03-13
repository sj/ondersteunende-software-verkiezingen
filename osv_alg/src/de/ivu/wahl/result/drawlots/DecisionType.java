/*
 * DecisionType
 * 
 * Created on 03.03.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.drawlots;

import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;
import de.ivu.wahl.result.result.Distribution;
import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;

/**
 * Enumeration of all the different kinds of events that may require that a {@link Decision} is made
 * by lot. The id is also the number of the article in the electoral law where the respective
 * {@link DecisionType} is described.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public enum DecisionType {

  DHONDT_P42(7, Messages.getString(MessageKeys.DHONDT_P42_TEXT), Messages
      .getString(MessageKeys.DHONDT_P42_MESSAGE)),

  NIEMEYER_P42(8, Messages.getString(MessageKeys.NIEMEYER_P42_TEXT), Messages
      .getString(MessageKeys.NIEMEYER_P42_MESSAGE)),

  ABSOLUTE_MAJORITY(9, Messages.getString(MessageKeys.ABSOLUTE_MAJORITY_TEXT), Messages
      .getString(MessageKeys.ABSOLUTE_MAJORITY_MESSAGE)),

  NIEMEYER_P3(11, Messages.getString(MessageKeys.NIEMEYER_P3_TEXT), Messages
      .getString(MessageKeys.NIEMEYER_P3_MESSAGE)),

  DHONDT_P3(13, Messages.getString(MessageKeys.DHONDT_P3_TEXT), Messages
      .getString(MessageKeys.DHONDT_P3_MESSAGE)),

  NIEMEYER_P2(12, Messages.getString(MessageKeys.NIEMEYER_P2_TEXT), Messages
      .getString(MessageKeys.NIEMEYER_P2_MESSAGE)),

  // NOT P 31, but P13 like DHONDT_P3, but id = 13 is already taken
  DHONDT_P2(31, Messages.getString(MessageKeys.DHONDT_P2_TEXT), Messages
      .getString(MessageKeys.DHONDT_P2_MESSAGE)),

  CANDIDATES(15, Messages.getString(MessageKeys.CANDIDATES_TEXT), Messages
      .getString(MessageKeys.CANDIDATES_MESSAGE)),

  ROLL_BACK(16, Messages.getString(MessageKeys.ROLL_BACK_TEXT), Messages
      .getString(MessageKeys.ROLL_BACK_MESSAGE)),

  ROLL_FORWARD(18, Messages.getString(MessageKeys.ROLL_FORWARD_TEXT), Messages
      .getString(MessageKeys.ROLL_FORWARD_MESSAGE));

  final int _id;
  final String _reason;
  final String _message;

  /**
   * @return the DecisionType with the given <code>id</code>.
   */
  public static DecisionType byId(int id) {
    for (DecisionType each : values()) {
      if (each._id == id) {
        return each;
      }
    }
    return null;
  }

  private DecisionType(int id, String reason, String message) {
    this._id = id;
    this._reason = reason;
    this._message = message;
  }

  public int getId() {
    return _id;
  }

  /**
   * @return a text that cites from the electoral law the reason why drawing lots is / was required
   *         at the event of this conflict
   */
  public String getReason() {
    return _reason;
  }

  /**
   * The reason may depend on the election category
   */
  public String getReasonAndAppendix(ElectionSubcategory electionSubcategory) {
    return _reason + getAppendixFor(electionSubcategory);
  }

  /**
   * The reason may depend on the election category
   */
  public String getReasonComplete() {
    return _reason + getAppendixFor(ElectionSubcategory.GR1)
        + getAppendixFor(ElectionSubcategory.EP);
  }

  /**
   * @return Additional message that indicates the consequence to the list or candidate that is
   *         drawn. Typically the consequence is, that the list or candidate receives a seat but may
   *         in case of article P 9 be that the lists looses a seat.
   */
  public String getMessage() {
    return _message;
  }

  /**
   * Getter for the ConflitTypes that occur in residual seat assignment by d'Hondt or Niemeyer.
   * 
   * @param dHondt <code>true</code> for assignment by d'Hondt (greatest average),
   *          <code>false</code> for assignment by Niemeyer (greatest average)
   * @param isP42
   * @return
   */
  public static DecisionType getResidualSeatConflictType(boolean dHondt, Distribution distribution) {
    if (Distribution.P42.equals(distribution)) {
      if (dHondt) {
        return DecisionType.DHONDT_P42;
      } else {
        return DecisionType.NIEMEYER_P42;
      }
    } else if (Distribution.P3.equals(distribution)) {
      if (dHondt) {
        return DecisionType.DHONDT_P3;
      } else {
        return DecisionType.NIEMEYER_P3;
      }
    } else if (Distribution.P2.equals(distribution)) {
      if (dHondt) {
        return DecisionType.DHONDT_P2;
      } else {
        return DecisionType.NIEMEYER_P2;
      }
    }
    return null;
  }

  public String getAppendixFor(ElectionSubcategory electionSubcategory) {
    if (!this.equals(CANDIDATES)) {
      return ""; //$NON-NLS-1$
    }
    if (ElectionSubcategory.EP.equals(electionSubcategory)) {
      return Messages.getString(MessageKeys.CANDIDATES_TEXT_APPENDIX_EP);
    } else if (ElectionSubcategory.GR1.equals(electionSubcategory)
        || ElectionSubcategory.ER1.equals(electionSubcategory)
        || ElectionSubcategory.BC.equals(electionSubcategory)
        || ElectionSubcategory.GC.equals(electionSubcategory)) {
      return Messages.getString(MessageKeys.CANDIDATES_TEXT_APPENDIX_GR1);
    } else {
      return ""; //$NON-NLS-1$
    }
  }
}
