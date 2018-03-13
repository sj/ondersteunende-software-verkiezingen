/*
 * ResizableDialog
 * 
 * Created on Jan 15, 2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.utils.ui.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

/**
 * This is a resizable dialog
 * 
 * @author mike@ivu.de, IVU Traffic Technologies AG
 */
public class ResizableDialog extends Dialog {

  public ResizableDialog(final Shell parentShell) {
    super(parentShell);
    setShellStyle(SWT.CLOSE | SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.RESIZE);

  }

  public ResizableDialog(final IShellProvider parentShell) {
    super(parentShell);
    setShellStyle(SWT.CLOSE | SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.RESIZE);
  }

}
