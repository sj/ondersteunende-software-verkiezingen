/**
 * 
 */
package de.ivu.wahl.wus.utils.ui.editingsupport;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposalListener2;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

/**
 * @author tdu@ivu.de
 */
public class TextCellEditorWithContentProposal extends TextCellEditor {

  private ContentProposalAdapter contentProposalAdapter;
  private boolean popupOpen = false; // true, if popup is currently open

  public TextCellEditorWithContentProposal(final Composite parent,
      final IContentProposalProvider contentProposalProvider,
      final KeyStroke keyStroke,
      final char[] autoActivationCharacters) {
    super(parent);

    enableContentProposal(contentProposalProvider, keyStroke, autoActivationCharacters);
  }

  private void enableContentProposal(final IContentProposalProvider contentProposalProvider,
      final KeyStroke keyStroke,
      final char[] autoActivationCharacters) {
    contentProposalAdapter = new ContentProposalAdapter(text, new TextContentAdapter(),
        contentProposalProvider, keyStroke, autoActivationCharacters);

    // Listen for popup open/close events to be able to handle focus events correctly
    contentProposalAdapter.addContentProposalListener(new IContentProposalListener2() {

      public void proposalPopupClosed(final ContentProposalAdapter adapter) {
        popupOpen = false;
      }

      public void proposalPopupOpened(final ContentProposalAdapter adapter) {
        popupOpen = true;
      }
    });
  }

  /**
   * Return the {@link ContentProposalAdapter} of this cell editor.
   * 
   * @return the {@link ContentProposalAdapter}
   */
  public ContentProposalAdapter getContentProposalAdapter() {
    return contentProposalAdapter;
  }

  @Override
  protected void focusLost() {
    if (!popupOpen) {
      // Focus lost deactivates the cell editor.
      // This must not happen if focus lost was caused by activating
      // the completion proposal popup.
      super.focusLost();
    }
  }

  @Override
  protected boolean dependsOnExternalFocusListener() {
    // Always return false;
    // Otherwise, the ColumnViewerEditor will install an additional focus listener
    // that cancels cell editing on focus lost, even if focus gets lost due to
    // activation of the completion proposal popup. See also bug 58777.
    return false;
  }
}