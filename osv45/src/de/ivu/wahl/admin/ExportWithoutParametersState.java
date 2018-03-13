/*
 * ExportWithParametersState
 * 
 * Copyright (c) 2017 IVU Traffic Technologies AG
 */
package de.ivu.wahl.admin;

/**
 * Dialog-State and constant holder for export control for an export without parameter input: Just a
 * page to start the export and a page for the result.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class ExportWithoutParametersState extends DialogStateHolder {
  private static final long serialVersionUID = -2707559965515384489L;

  public final static int STATUS_P4_D1 = INITIAL_STATE; // start export
  public final static int STATUS_EXPORT_FINISHED = 2; // export finished

}
