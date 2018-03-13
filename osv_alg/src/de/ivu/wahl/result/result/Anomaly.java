/*
 * Anomaly
 * 
 * Created on 12.10.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.result;

/**
 * An Anomaly contains a piece of information about an exceptional event in the seat distribution
 * process, like
 * <ul>
 * <li>exhausted list</li>
 * <li>absolute majority</li>
 * </ul>
 * For one exceptional event usually more than one Anomaly object will be created.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class Anomaly {
  private final String _text;
  private final int _number;
  private final boolean _shallBePublishedInModelP22;

  Anomaly(String text, int number, boolean shallBePublishedInModelP22) {
    this._text = text;
    this._number = number;
    this._shallBePublishedInModelP22 = shallBePublishedInModelP22;
  }

  public String getText() {
    return _text;
  }

  public int getNumber() {
    return _number;
  }

  public boolean isShallBePublishedInModelP22() {
    return _shallBePublishedInModelP22;
  }
}
