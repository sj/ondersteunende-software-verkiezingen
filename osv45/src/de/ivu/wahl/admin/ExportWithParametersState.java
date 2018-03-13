/*
 * ExportWithParametersState
 * 
 * Copyright (c) 2017 IVU Traffic Technologies AG
 */
package de.ivu.wahl.admin;

/**
 * Dialog-State and constant holder for export control for an export with parameter input
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class ExportWithParametersState extends DialogStateHolder {
  private static final long serialVersionUID = -2707559965515384489L;

  @Deprecated
  public final static int STATUS_P4_D1 = INITIAL_STATE; // Parameter input

  public final static int STATUS_INITIAL_PAGE = STATUS_P4_D1; // Parameter input

  public final static int STATUS_P4_D2 = 2; // Parameter acknowledgement

  @Deprecated
  public final static int STATUS_P4_D3 = 3; // Export finished
  public final static int STATUS_EXPORT_FINISHED = STATUS_P4_D3; // export finished

  @Override
  public void back() {
    if (_modus == STATUS_P4_D3) {
      _modus = INITIAL_STATE;
    } else {
      super.back();
    }
  }
}
