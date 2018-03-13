/*
 * GBAAutoCompleteField
 * 
 * Created on Jan 6, 2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.utils.ui;

import org.eclipse.jface.bindings.keys.IKeyLookup;
import org.eclipse.jface.bindings.keys.KeyLookupFactory;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.swt.widgets.Control;

/**
 * This class adds support for GBA characters for a control. GBA characters are shown as proposals.
 * It works similar to the JFace AutoCompleteField
 * 
 * @author mike@ivu.de, IVU Traffic Technologies AG
 * @author tdu@ivu.de, IVU Traffic Technologies AG
 */
public class GBAAutoCompleteField {

  private final GBAProposalProvider proposalProvider;
  private final ContentProposalAdapter adapter;

  /**
   * Construct an AutoComplete field on the specified control, whose completions are characterized
   * by the specified GBA characters
   * 
   * @param control the control for which autocomplete is desired. May not be <code>null</code>.
   * @param controlContentAdapter the <code>IControlContentAdapter</code> used to obtain and update
   *          the control's contents. May not be <code>null</code>.
   * @param proposals the array of Strings representing valid content proposals for the field.
   * @param propagateKeys
   */
  public GBAAutoCompleteField(final Control control,
      final IControlContentAdapter controlContentAdapter,
      final String[] proposals) {
    proposalProvider = new GBAProposalProvider(proposals);

    final int modifierKey = KeyLookupFactory.getDefault().getCtrl();
    final int naturalKey = KeyLookupFactory.getDefault().formalKeyLookup(IKeyLookup.SPACE_NAME);
    final KeyStroke keyStroke = KeyStroke.getInstance(modifierKey, naturalKey);
    adapter = new ContentProposalAdapter(control, controlContentAdapter, proposalProvider,
        keyStroke, null);
    adapter.setPropagateKeys(true);
    adapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);

  }

  public ContentProposalAdapter getProposalAdapter() {
    return adapter;
  }
}
