/*
 * ErgebniseingangKonstanten
 * 
 * Created on Dec 5, 2008
 * Copyright (c) 2008 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell;


/**
 * @author mur@ivu.de, IVU Traffic Technologies AG
 */
public interface ErgebniseingangKonstanten {

  public static final String THREE_ASTERISKS = "*** "; //$NON-NLS-1$

  /**
   * Source: GUI
   */
  int SOURCE_GUI_1 = 0;
  int SOURCE_GUI_2 = 1;
  /**
   * Source: Import of EML
   */
  int SOURCE_FILE_IMPORT = 2;
  int SOURCE_FILE_IMPORT_AS_FIRST_INPUT = 3;

  // /**
  // * Klartext-Umschreibung der Stati
  // */
  // String[] STATE_TEXT = {STATE_NO_INPUT_S, STATE_FIRST_MANUAL_INPUT_S,
  // STATE_FIRST_MANUAL_INPUT_DONE_CORRECTION_S, STATE_FIRST_MANUAL_INPUT_DONE_SECOND_S,
  // STATE_SECOND_MANUAL_INPUT_S, STATE_SECOND_MANUAL_INPUT_DONE_CORRECTION_S,
  // STATE_MANUAL_INPUT_CHANGE_S, STATE_MANUAL_INPUT_SUCCESSFUL_S, STATE_EML_INPUT_S,
  // STATE_EML_INPUT_UNSUCCESSFUL_S, STATE_EML_INPUT_SUCCESSFUL_S};

  int STATE_WARNING = 100;
  int STATE_ERROR = 200;
  int STATE_FIRST_RESULT_OK = 90;
  int STATE_OK = 50;
  int STATE_NO_INPUT = 0;

  /**
   * Used to store radio buttons choice about differences
   */
  String UNTERSCHIEDE_FORM_ELEMENT_NAME = "inputUnterschiedeVorhanden"; //$NON-NLS-1$

  public enum UnterschiedeVorhandenTyp {
    UNTERSCHIEDE_UNBEKANNT(-1) {
      @Override
      public Boolean getBoolean() {
        return null;
      }
    },
    UNTERSCHIEDE_NEIN(0) {
      @Override
      public Boolean getBoolean() {
        return false;
      }
    },
    UNTERSCHIEDE_JA(1) {
      @Override
      public Boolean getBoolean() {
        return true;
      }
    };

    private final int value;

    UnterschiedeVorhandenTyp(int valueDB) {
      value = valueDB;
    }

    public int getValue() {
      return value;
    }

    public abstract Boolean getBoolean();

    public static UnterschiedeVorhandenTyp getByValue(int unterschiedeVorhandenValue) {
      for (UnterschiedeVorhandenTyp unterschiedeVorhandenTyp : values()) {
        if (unterschiedeVorhandenTyp.getValue() == unterschiedeVorhandenValue) {
          return unterschiedeVorhandenTyp;
        }
      }
      throw new IllegalArgumentException(
          "Unknown value for enum UnterschiedeVorhandenTyp: " + unterschiedeVorhandenValue); //$NON-NLS-1$
    }

    public static Boolean getBooleanByValue(int unterschiedeVorhandenValue) {
      return getByValue(unterschiedeVorhandenValue).getBoolean();
    }

    public static int getValueByBoolean(Boolean unterschiedeVorhandenBoolean) {
      if (unterschiedeVorhandenBoolean == null) {
        return UNTERSCHIEDE_UNBEKANNT.getValue();
      }
      if (unterschiedeVorhandenBoolean) {
        return UNTERSCHIEDE_JA.getValue();
      }
      return UNTERSCHIEDE_NEIN.getValue();
    }
  }
}
