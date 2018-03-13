/*
 * Created on 25.08.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl;

import static de.ivu.wahl.util.BundleHelper.getBundleString;

import java.util.ArrayList;

import de.ivu.wahl.Basiseinstellung.Typ;

/**
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class BasiseinstellungList extends ArrayList<Basiseinstellung> {
  private static final long serialVersionUID = 1L;

  public Basiseinstellung addBool(String property, String beschreibungKey, String hinweisKey) {
    String hinweis = getBundleString(hinweisKey);
    Basiseinstellung result = new Basiseinstellung(property, beschreibungKey, Typ.Boolean, false,
        hinweis, true);
    this.add(result);
    return result;
  }

  public Basiseinstellung addInteger(String property, String beschreibungKey) {
    Basiseinstellung result = new Basiseinstellung(property, beschreibungKey, Typ.Integer, false,
        null, true);
    this.add(result);
    return result;
  }

  public Basiseinstellung addInteger(String property, String beschreibungKey, String hinweisKey) {
    String hinweis = getBundleString(hinweisKey);
    Basiseinstellung result = new Basiseinstellung(property, beschreibungKey, Typ.Integer, false,
        hinweis, true);
    this.add(result);
    return result;
  }
  
  public Basiseinstellung addDate(String property, String beschreibungKey) {
    Basiseinstellung result = new Basiseinstellung(property, beschreibungKey, Typ.Date, false,
        null, true);
    this.add(result);
    return result;
  }

  public Basiseinstellung addTime(String property, String beschreibungKey) {
    Basiseinstellung result = new Basiseinstellung(property, beschreibungKey, Typ.Time, false,
        null, true);
    this.add(result);
    return result;
  }

  public Basiseinstellung addString(String property, String beschreibungKey) {
    Basiseinstellung result = new Basiseinstellung(property, beschreibungKey, Typ.String, false,
        null, true);
    this.add(result);
    return result;
  }

  public Basiseinstellung addString(String property, String beschreibungKey, String hinweisKey) {
    String hinweis = getBundleString(hinweisKey);
    Basiseinstellung result = new Basiseinstellung(property, beschreibungKey, Typ.String, false,
        hinweis, true);
    this.add(result);
    return result;
  }

  public Basiseinstellung addZip(String property, String beschreibungKey) {
    Basiseinstellung result = new Basiseinstellung(property, beschreibungKey, Typ.ZIP, false, null,
        true);
    this.add(result);
    return result;
  }

  /**
   * Textareas are always optional!
   */
  public Basiseinstellung addTextarea(String property, String beschreibungKey) {
    Basiseinstellung result = new Basiseinstellung(property, beschreibungKey, Typ.Textarea, false,
        null, false);
    this.add(result);
    return result;
  }

}
