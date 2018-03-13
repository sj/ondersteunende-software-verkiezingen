/*
 * ListsInRegionsModel
 * 
 * Created on 16.11.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifisch;
import de.ivu.wahl.modell.ejb.Liste;
import de.ivu.wahl.modell.ejb.Listenkandidatur;

/**
 * Immutable object.
 * <p>
 * This object shall give complete information about Liste, Gruppe and GruppeGebietsspezifisch
 * throughout all the given regions.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class ListsInRegionsModel implements Serializable {
  private static final long serialVersionUID = -561232654244067668L;

  private final List<Gruppe> groups;
  private final List<Gebiet> regions;
  private final Map<String, Gruppe> groupIndex;
  private final Map<String, Liste> listeIndex;
  private final Map<String, Map<Gebiet, GruppeGebietsspezifisch>> listenAndGruppenGebietsspezifisch;
  private final Map<String, Map<Gebiet, GruppeGebietsspezifisch>> gruppenAndGruppenGebietsspezifisch;

  /**
   * Constructor
   */
  @SuppressWarnings("hiding")
  public ListsInRegionsModel(Collection<Gebiet> regions,
      Collection<Gruppe> groups,
      Collection<Liste> allListen) {
    ArrayList<Gruppe> tempGroups = new ArrayList<Gruppe>(groups);
    this.groups = Collections.unmodifiableList(tempGroups);

    Map<String, Gruppe> tempGroupIndex = new HashMap<String, Gruppe>();
    for (Gruppe gruppe : groups) {
      tempGroupIndex.put(gruppe.getID_Gruppe(), gruppe);
    }
    this.groupIndex = Collections.unmodifiableMap(tempGroupIndex);

    ArrayList<Gebiet> tempRegions = new ArrayList<Gebiet>(regions);
    this.regions = Collections.unmodifiableList(tempRegions);

    Map<String, Liste> tempListeIndex = new HashMap<String, Liste>();
    for (Liste liste : allListen) {
      tempListeIndex.put(liste.getID_Liste(), liste);
    }
    this.listeIndex = Collections.unmodifiableMap(tempListeIndex);

    listenAndGruppenGebietsspezifisch = createListenAndGruppenGebietsspezifisch(regions, allListen);
    gruppenAndGruppenGebietsspezifisch = buildGruppenAndGruppenGebietsspezifisch(regions, groups);

  }

  @SuppressWarnings("hiding")
  private Map<String, Map<Gebiet, GruppeGebietsspezifisch>> createListenAndGruppenGebietsspezifisch(Collection<Gebiet> regions,
      Collection<Liste> allListen) {
    Map<String, Map<Gebiet, GruppeGebietsspezifisch>> result = new HashMap<String, Map<Gebiet, GruppeGebietsspezifisch>>();
    for (Liste liste : allListen) {
      Collection<GruppeGebietsspezifisch> ggsCol = liste.getGruppeGebietsspezifischCol();
      Map<Gebiet, GruppeGebietsspezifisch> map = buildMapGebietAndGruppeGebietsspezifisch(regions,
          ggsCol);
      result.put(liste.getID_Liste(), Collections.unmodifiableMap(map));
    }
    return Collections.unmodifiableMap(result);
  }

  @SuppressWarnings("hiding")
  private Map<String, Map<Gebiet, GruppeGebietsspezifisch>> buildGruppenAndGruppenGebietsspezifisch(Collection<Gebiet> regions,
      Collection<Gruppe> groups) {
    Map<String, Map<Gebiet, GruppeGebietsspezifisch>> result = new HashMap<String, Map<Gebiet, GruppeGebietsspezifisch>>();
    for (Gruppe group : groups) {
      Collection<GruppeGebietsspezifisch> ggsCol = group.getGruppeGebietsspezifischCol();
      Map<Gebiet, GruppeGebietsspezifisch> map = buildMapGebietAndGruppeGebietsspezifisch(regions,
          ggsCol);
      result.put(group.getID_Gruppe(), Collections.unmodifiableMap(map));
    }
    return Collections.unmodifiableMap(result);
  }

  private Map<Gebiet, GruppeGebietsspezifisch> buildMapGebietAndGruppeGebietsspezifisch(Collection<Gebiet> regions,
      Collection<GruppeGebietsspezifisch> ggsCol) {
    Map<Gebiet, GruppeGebietsspezifisch> map = new HashMap<Gebiet, GruppeGebietsspezifisch>();
    for (GruppeGebietsspezifisch ggs : ggsCol) {
      Gebiet region = ggs.getGebiet();
      if (regions.contains(region)) {
        map.put(region, ggs);
      }
    }
    return map;
  }

  /**
   * @return lk.getListe()
   */
  public Liste getListe(Listenkandidatur lk) {
    if (lk == null) {
      return null;
    }
    return listeIndex.get(lk.getID_Liste());
  }

  public Map<Gebiet, GruppeGebietsspezifisch> getGruppenGebietsspezifisch(Liste liste) {
    return listenAndGruppenGebietsspezifisch.get(liste.getID_Liste());
  }

  public GruppeGebietsspezifisch getGruppeGebietsspezifisch(Gebiet region, String id_Gruppe) {
    Map<Gebiet, GruppeGebietsspezifisch> map = gruppenAndGruppenGebietsspezifisch.get(id_Gruppe);
    if (map == null) {
      return null;
    } else {
      return map.get(region);
    }
  }

  /**
   * @return ggs.getGruppe()
   */
  public Gruppe getGruppe(GruppeGebietsspezifisch ggs) {
    if (ggs == null) {
      return null;
    }
    return groupIndex.get(ggs.getID_Gruppe());
  }

  /**
   * @return an unmodifyable list of the chid regions of the root region
   */
  public List<Gebiet> getRegions() {
    return regions;
  }

}
