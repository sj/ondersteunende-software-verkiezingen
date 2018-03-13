/*
 * SystemProperty
 * 
 * Created on 06.12.2017
 * Copyright (c) 2017 IVU Traffic Technologies AG
 */
package de.ivu.wahl;

public enum SystemProperty {
  PROP_CREATE_MODEL_N10_1("CreateModel_N10_1", Boolean.TRUE.toString()), //$NON-NLS-1$

  PROP_CREATE_MODEL_I("CreateModel_I_AndAppendix2OfModel_II", Boolean.FALSE.toString()), //$NON-NLS-1$

  PROP_CREATE_MODEL_N11("CreateModel_N11", Boolean.TRUE.toString()), //$NON-NLS-1$

  PROP_CREATE_MODEL_II("CreateModel_II_AndAppendix1OfModel_II", Boolean.FALSE.toString()); //$NON-NLS-1$

  private final String key;
  private final String initialValue;

  private SystemProperty(String key, String initialValue) {
    this.key = key;
    this.initialValue = initialValue;
  }

  public String getKey() {
    return key;
  }

  public String getInitialValue() {
    return initialValue;
  }
}
