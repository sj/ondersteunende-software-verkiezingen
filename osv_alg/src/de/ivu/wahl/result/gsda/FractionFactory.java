/*
 * FractionFactory
 * 
 * Created on 04.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.gsda;

import de.ivu.wahl.result.NamedObject;

public interface FractionFactory<T extends NamedObject> {
  public FractionFromList<T> newFraction(T list);
}
