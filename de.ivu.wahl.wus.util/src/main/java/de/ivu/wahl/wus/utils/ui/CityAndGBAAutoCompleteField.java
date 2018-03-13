/*
 * CityAndGBAAutoCompleteField
 * 
 * Created on Oct 23, 2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.utils.ui;

import org.eclipse.jface.bindings.keys.IKeyLookup;
import org.eclipse.jface.bindings.keys.KeyLookupFactory;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.swt.widgets.Control;

/**
 * This class adds support for GBA characters for a control. GBA characters are shown as proposals.
 * It works similar to the JFace AutoCompleteField
 * 
 * @author tdu@ivu.de, IVU Traffic Technologies AG
 */
public class CityAndGBAAutoCompleteField {

  private final SimpleContentProposalProvider cityProposalProvider;
  private final ContentProposalAdapter cityProposalAdapter;

  private final GBAProposalProvider gbaProposalProvider;
  private final ContentProposalAdapter gbaProposalAdapter;

  /**
   * Construct an AutoComplete field on the specified control, whose completions are characterized
   * by the specified GBA characters
   * 
   * @param control the control for which autocomplete is desired. May not be <code>null</code>.
   * @param cityControlContentAdapter the <code>IControlContentAdapter</code> used to obtain and
   *          update the control's contents. May not be <code>null</code>.
   * @param cityProposals the array of Strings representing valid city proposals for the field.
   * @param gbaControlContentAdapter the <code>IControlContentAdapter</code> used to obtain and
   *          update the control's contents. May not be <code>null</code>.
   * @param gbaProposals the array of Strings representing valid GBA proposals for the field.
   */
  public CityAndGBAAutoCompleteField(final Control control,
      final IControlContentAdapter cityControlContentAdapter,
      final String[] cityProposals,
      final IControlContentAdapter gbaControlContentAdapter,
      final String[] gbaProposals) {

    // cities
    cityProposalProvider = new SimpleContentProposalProvider(cityProposals);
    cityProposalProvider.setFiltering(true);

    cityProposalAdapter = new ContentProposalAdapter(control, cityControlContentAdapter,
        cityProposalProvider, null, null);
    cityProposalAdapter.setPropagateKeys(true);
    cityProposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);

    // GBA
    gbaProposalProvider = new GBAProposalProvider(gbaProposals);

    final int modifierKey = KeyLookupFactory.getDefault().getCtrl();
    final int naturalKey = KeyLookupFactory.getDefault().formalKeyLookup(IKeyLookup.SPACE_NAME);
    final KeyStroke keyStroke = KeyStroke.getInstance(modifierKey, naturalKey);
    gbaProposalAdapter = new ContentProposalAdapter(control, gbaControlContentAdapter,
        gbaProposalProvider, keyStroke, null);
    gbaProposalAdapter.setPropagateKeys(true);
    gbaProposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
  }

}
