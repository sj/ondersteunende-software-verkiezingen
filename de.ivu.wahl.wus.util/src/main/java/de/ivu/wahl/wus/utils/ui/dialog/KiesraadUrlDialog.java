/**
 * 
 */
package de.ivu.wahl.wus.utils.ui.dialog;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;

import de.ivu.wahl.wus.utils.i18n.Messages;

/**
 * @author tdu@ivu.de
 */
public class KiesraadUrlDialog extends MessageDialog {

  private static final int TEXT_WIDTH = 350;
  static String[] buttonLabels = {"OK"};

  public KiesraadUrlDialog(final Shell parentShell) {
    super(parentShell, Messages.kiesraadUrlDialogTitle, null, null, INFORMATION, buttonLabels, 0);
  }

  @Override
  protected Control createDialogArea(final Composite parent) {
    // create composite
    final Composite container = new Composite(parent, SWT.NONE);
    final GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    layout.marginHeight = 0;
    layout.marginWidth = 0;
    container.setLayout(layout);
    final GridData gd = new GridData(GridData.FILL_BOTH);
    container.setLayoutData(gd);

    // create image
    final Image image = getImage();
    if (image != null) {
      imageLabel = new Label(container, SWT.NULL);
      image.setBackground(imageLabel.getBackground());
      imageLabel.setImage(image);
      GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.BEGINNING).applyTo(imageLabel);
    }

    // explanation text
    final Label explaination = new Label(container, SWT.WRAP);
    explaination.setText(Messages.kiesraadUrlDialogMessage);
    final GridData labelGd = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
    labelGd.widthHint = TEXT_WIDTH;
    explaination.setLayoutData(labelGd);

    // space
    new Label(container, SWT.NONE);

    // URL
    final Link link = new Link(container, SWT.NONE);
    link.setText("<a>" + Messages.kiesraadUrlDialogLink + "</a>");
    link.addSelectionListener(new SelectionListener() {

      public void widgetDefaultSelected(final SelectionEvent e) {
      }

      public void widgetSelected(final SelectionEvent e) {
        org.eclipse.swt.program.Program.launch("http://" + Messages.kiesraadUrlDialogLink + "/");
      }
    });

    return container;
  }

}
