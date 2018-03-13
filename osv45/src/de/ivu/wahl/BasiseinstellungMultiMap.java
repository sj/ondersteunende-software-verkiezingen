/*
 * BasiseinstellungMultiMap
 * 
 * Created on 25.08.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl;

import static de.ivu.wahl.util.BundleHelper.getBundleString;
import static java.util.Collections.unmodifiableList;

import java.util.List;

import de.ivu.wahl.util.ConstantMap;

/**
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class BasiseinstellungMultiMap extends ConstantMap<String, List<Basiseinstellung>> {
  private List<Basiseinstellung> basis;

  static BasiseinstellungMultiMap key(String key, List<Basiseinstellung> list) {
    BasiseinstellungMultiMap inst = new BasiseinstellungMultiMap();
    inst.add(key, unmodifiableList(list));
    inst.basis = list;
    return inst;
  }

  private BasiseinstellungMultiMap() {
    super();
  }

  BasiseinstellungMultiMap(String key, List<Basiseinstellung> list) {
    super();
    this.basis = list;
    addLocalize(key, list);
  }

  void addLocalize(String key, List<Basiseinstellung> list) {
    add(getBundleString(key), unmodifiableList(list));
  }

  public List<Basiseinstellung> getBasis() {
    return basis;
  }
}
