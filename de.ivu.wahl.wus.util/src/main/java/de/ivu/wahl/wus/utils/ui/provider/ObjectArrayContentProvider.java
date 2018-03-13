/*
 * ObjectArrayContentProvider
 * 
 * Created on Nov 19, 2008 Copyright (c) 2008 Kiesraad
 */

package de.ivu.wahl.wus.utils.ui.provider;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ObjectArrayContentProvider implements IStructuredContentProvider {

  private Object[] _values;

  public ObjectArrayContentProvider() {
  }

  public Object[] getElements(final Object inputElement) {
    return _values;
  }

  public void dispose() {
  }

  public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    _values = (Object[]) newInput;
  }

}
