/*
 * Country
 * 
 * Created on Jan 29, 2009
 * Copyright (c) 2009 Kiesraad
 */

package de.ivu.wahl.wus.utils.ui;

public class Country {

  private String isoCode;
  private String name;

  public Country(String isoCode, String name) {
    super();
    this.isoCode = isoCode;
    this.name = name;
  }

  @Override
  public String toString() {
    return getDisplayValue();
  }

  public String getDisplayValue() {
    String label = isoCode + " - " + name; //$NON-NLS-1$
    return label;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((isoCode == null) ? 0 : isoCode.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Country other = (Country) obj;
    if (isoCode == null) {
      if (other.isoCode != null) return false;
    } else if (!isoCode.equals(other.isoCode)) return false;
    return true;
  }

  public String getIsoCode() {
    return isoCode;
  }

  public String getName() {
    return name;
  }

}
