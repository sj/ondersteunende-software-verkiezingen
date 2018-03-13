/*
 * CandidateshipsModel
 * 
 * Created on 17.11.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.result.MultimapUtil;

/**
 * Immutable object.
 * <p>
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class CandidateshipsModel {
  private final Map<Gebiet, Map<String, List<Candidateship>>> candidateshipsByRegionAndGroup = new HashMap<Gebiet, Map<String, List<Candidateship>>>();
  private final Map<String, Map<Gebiet, Candidateship>> candidateshipByListenkandidaturAndRegion = new HashMap<String, Map<Gebiet, Candidateship>>();
  private final Map<String, Gebiet> regionIndex = new HashMap<String, Gebiet>();

  /**
   * Constructor
   * 
   * @param listsInRegionsModel
   */
  public CandidateshipsModel(List<Candidateship> allCandidateships,
      ListsInRegionsModel listsInRegionsModel) {
    for (Candidateship candidateship : allCandidateships) {
      Gebiet key1 = candidateship.getRegion();
      regionIndex.put(key1.getID_Gebiet(), key1);

      Map<String, List<Candidateship>> map1 = MultimapUtil.getMap(candidateshipsByRegionAndGroup,
          key1);
      String key2 = candidateship.getID_Gruppe();
      MultimapUtil.addToList(map1, key2, candidateship);

      Map<Gebiet, Candidateship> innerMap = MultimapUtil
          .getMap(candidateshipByListenkandidaturAndRegion, candidateship.getListenkandidatur()
              .getID_Listenkandidatur());
      innerMap.put(key1, candidateship);
    }

    for (Map<String, List<Candidateship>> map1 : candidateshipsByRegionAndGroup.values()) {
      for (List<Candidateship> list2 : map1.values()) {
        Collections.sort(list2, Candidateship.SORT_BY_POSITION_IN_LIST);
      }
    }

  }

  /**
   * @return a collection containing the Candidateship of a given party (political grouping,
   *         P3-list) in a given region.
   */
  public Collection<Candidateship> getByRegionAndGroup(Gebiet region, String id_Gruppe) {
    Map<String, List<Candidateship>> map1 = candidateshipsByRegionAndGroup.get(region);
    if (map1 == null) {
      return Collections.emptyList();
    } else {
      List<Candidateship> result = map1.get(id_Gruppe);
      if (result == null) {
        return Collections.emptyList();
      } else {
        return result;
      }
    }
  }

  /**
   * @param listenkandidatur
   * @param gebiet
   * @return
   */
  public Candidateship getCandidateship(String id_Listenkandidatur, String id_Gebiet) {
    Map<Gebiet, Candidateship> innerMap = candidateshipByListenkandidaturAndRegion
        .get(id_Listenkandidatur);
    if (innerMap == null) {
      return null;
    } else {
      Gebiet gebiet = regionIndex.get(id_Gebiet);
      return innerMap.get(gebiet);
    }
  }
}
