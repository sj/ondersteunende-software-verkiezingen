/*
 * InputMode
 * 
 * Created on 13.11.2017
 * Copyright (c) 2017 IVU Traffic Technologies AG
 */
package de.ivu.wahl;

/**
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public enum InputMode {
  UNKNOWN(0),

  INPUT_MODE_SINGLE(1),

  INPUT_MODE_DOUBLE(2),

  INPUT_MODE_FILE_WITH_MANUAL_CONFIRMATION(3);

  private final int value;

  private InputMode(int aValue) {
    this.value = aValue;
  }

  public String getProperty() {
    return String.valueOf(value);
  }

  public boolean isSingleInput() {
    return this.equals(INPUT_MODE_SINGLE);
  }

  public boolean isDoubleInput() {
    return this.equals(INPUT_MODE_DOUBLE);
  }

  public boolean isFileInputWithManualConfirmation() {
    return this.equals(INPUT_MODE_FILE_WITH_MANUAL_CONFIRMATION);
  }

  public static InputMode fromProperty(String property) {
    for (InputMode inputMode : values()) {
      if (inputMode.getProperty().equals(property)) {
        return inputMode;
      }
    }
    return UNKNOWN;
  }

}
