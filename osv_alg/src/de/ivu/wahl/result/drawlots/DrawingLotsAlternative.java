/*
 * DrawingLotsAlternative
 * 
 * Created on 05.03.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.drawlots;

import de.ivu.wahl.result.NamedObject;

/**
 * All objects that may be an alternative in the event of drawing lots must implement this
 * interface.
 * 
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public interface DrawingLotsAlternative extends NamedObject {

  public DrawingLotsIdentifier getIdentifier();

}
