package de.ivu.wahl.modell;

import java.util.List;

/**
 * PersonendatenModel
 * 
 * @author cos@ivu.de (c) 2003-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public interface PersonendatenModel extends BasicPersonendatenModel {

  String getAnzeigeName();

  /**
   * Checks if this person is on a candidate list.
   * 
   * @return {@link true} if listenkandidatur collection contain at least on element, else {@link
   *         false}.
   */
  boolean hasListenkandidatur();

  List<Integer> getListenkandidaturenGruppenSchluesselSortiert();
}
