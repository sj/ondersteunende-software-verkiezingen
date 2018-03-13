/*
 * ToStringLabelProvider
 * 
 * Created on Nov 19, 2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.wus.utils.ui.provider;

import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;

public class ToStringLabelProvider extends BaseLabelProvider implements ILabelProvider {

  public Image getImage(Object element) {
    return null;
  }

  public String getText(Object element) {
    return "" +element; //$NON-NLS-1$
  }

}
