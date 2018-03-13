/**
 * 
 */
package de.ivu.wahl.wus.utils.ui.commandhandler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.handlers.HandlerUtil;

import de.ivu.wahl.wus.utils.ui.dialog.KiesraadUrlDialog;

/**
 * @author tdu@ivu.de
 */
public class OpenKiesraadUrlDialogCommandHandler extends AbstractHandler implements IHandler {

  /*
   * (non-Javadoc)
   * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
   */
  public Object execute(final ExecutionEvent event) throws ExecutionException {

    new KiesraadUrlDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell()).open();

    return null;
  }
}
