/*
 * StepType
 * 
 * Created on 25.02.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.result;

/**
 * All types of steps as specified. The id corresponds to <b>kappa_i</b> in the formal
 * specification.
 * <p>
 * For each value of this enum there must be a text resource in
 * /osv_alg/src/de/ivu/wahl/result/i18n/messages.properties
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public enum StepType {
  FIRST_ASSIGNMENT(1),

  ALL_LISTS_EXHAUSTED(2),

  NIEMEYER_ASSIGNMENT(3),

  DHONDT_ASSIGNMENT_RESTRICTED(4),

  DHONDT_ASSIGNMENT(5),

  ABSOLUTE_MAJORITY(6),

  EXHAUSTED_LIST(7),

  CONTINUED_DRAWING_LOTS_NIEMEYER(8),

  CONTINUED_DRAWING_LOTS_DHONDT_RESTRICTED(9),

  CONTINUED_DRAWING_LOTS_DHONDT(10),

  ALL_SEATS_ASSIGNED(11),

  SWITCH_COSALG_MODE_ON(12);

  int _id;

  StepType(final int id) {
    this._id = id;
  }

  public int getId() {
    return _id;
  }
}
