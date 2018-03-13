/*
 * Created on 25.08.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl;

import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.util.BundleHelper;

public class Basiseinstellung {
  public static enum Typ {
    String, Boolean, Integer, RelURL, Date, Textarea, Time, ZIP
  }

  private static String COUNCIL_NAME = null;

  private final String _property;
  private final String _beschreibungKey;
  private String _beschreibung;
  private final Basiseinstellung.Typ _typ;
  private final boolean _restart;
  private final String _hinweis;
  private boolean _pflichtfeld;
  private String _regex = null;
  private String _regexErrorMsgKey = null;

  public Basiseinstellung(String property,
      String beschreibungKey,
      Basiseinstellung.Typ typ,
      boolean restart,
      String hinweis,
      boolean pflichtfeld) {
    _property = property;
    _beschreibungKey = beschreibungKey;
    _beschreibung = null;
    _typ = typ;
    _restart = restart;
    _hinweis = hinweis;
    _pflichtfeld = pflichtfeld;
  }

  /**
   * Textareas are always optional!
   */
  public static Basiseinstellung textarea(String property, String beschreibungKey) {
    return new Basiseinstellung(property, beschreibungKey, Typ.Textarea, false, null, false);
  }

  public String getBeschreibung() {
    if (_beschreibung == null) {
      if (COUNCIL_NAME == null) {
        switch (WahlInfo.getWahlInfo().getElectionCategory()) {
          case BC :
            COUNCIL_NAME = "de Bestuurscommissie ";
            break;
          case GC :
            COUNCIL_NAME = "de Gebiedscommissie ";
            break;
          case GR :
            COUNCIL_NAME = "de Gemeenteraad ";
            break;
          case PS :
            COUNCIL_NAME = "de Provinciale Staten ";
            break;
          case TK :
          case EP : // EP = TK
            COUNCIL_NAME = "de Tweede Kamer";
            break;
          case EK :
            COUNCIL_NAME = "de Eerste Kamer";
            break;
          default :
            COUNCIL_NAME = "het desbetreffende orgaan";
        }
      }
      _beschreibung = Messages.applyPattern(BundleHelper.getBundleString(_beschreibungKey),
          COUNCIL_NAME);
    }
    return _beschreibung;
  }

  public String getHinweis() {
    return _hinweis == null ? getBeschreibung() : _hinweis;
  }

  public String getProperty() {
    return _property;
  }

  public boolean isRestart() {
    return _restart;
  }

  public Basiseinstellung.Typ getTyp() {
    return _typ;
  }

  public boolean isPflichtfeld() {
    return _pflichtfeld;
  }

  Basiseinstellung optional() {
    _pflichtfeld = false;
    return this;
  }

  public String getRegex() {
    return _regex;
  }

  public void setRegex(String regex) {
    _regex = regex;
  }

  public String getRegexErrorMsgKey() {
    return _regexErrorMsgKey;
  }

  public void setRegexErrorMsgKey(String regexErrorMsgKey) {
    _regexErrorMsgKey = regexErrorMsgKey;
  }
}