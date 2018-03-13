/*
 * DialogStateHolder
 * 
 * Created on 25.05.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.admin;

import java.io.Serializable;

/**
 * Dialog-State and constant holder for export control
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class DialogStateHolder implements Serializable {
  private static final long serialVersionUID = -5144938836055463223L;

  // Initial state for all subclasses
  public static final int INITIAL_STATE = 1;

  public String _errorMsg;
  public int _modus;

  public DialogStateHolder() {
    this(INITIAL_STATE);
  }

  /**
   * Constructor
   */
  public DialogStateHolder(int modus) {
    super();
    _modus = modus;
  }

  public void setModus(int modus) {
    _modus = modus;
  }

  public int getModus() {
    return _modus;
  }

  public void reset() {
    _modus = INITIAL_STATE;
  }

  public void back() {
    _modus--;
  }

}