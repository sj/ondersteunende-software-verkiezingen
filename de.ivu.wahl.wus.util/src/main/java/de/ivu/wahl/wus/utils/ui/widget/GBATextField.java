/**
 * 
 */
package de.ivu.wahl.wus.utils.ui.widget;

import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import de.ivu.wahl.wus.utils.ui.GBAAutoCompleteField;

/**
 * @author tdu@ivu.de
 */
public class GBATextField {

  private final Text text;
  private GBAAutoCompleteField gbaAutoCompleteField = null;

  public GBATextField(final Composite parent, final int style) {
    text = new Text(parent, style);
  }

  /**
   * @return the text
   */
  public Text getText() {
    return text;
  }

  /**
   * @param gbaAutoCompleteField the gbaAutoCompleteField to set
   */
  public void setGbaAutoCompleteField(final GBAAutoCompleteField gbaAutoCompleteField) {
    this.gbaAutoCompleteField = gbaAutoCompleteField;
  }

  /**
   * @return the gbaAutoCompleteField
   */
  public GBAAutoCompleteField getGbaAutoCompleteField() {
    return gbaAutoCompleteField;
  }

  /**
   * @return the proposalAdapter
   */
  public ContentProposalAdapter getProposalAdapter() {
    if (getGbaAutoCompleteField() != null) {
      return getGbaAutoCompleteField().getProposalAdapter();
    }
    return null;
  }

}
