/*
 * ContestIdentifierService
 * 
 * Created on 30.08.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifisch;
import de.ivu.wahl.modell.ejb.Liste;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;
import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;

public class ContestIdentifierService {
  public final String ALLE = "alle"; //$NON-NLS-1$
  public final String GEEN = "geen"; //$NON-NLS-1$

  public String getContestIdentifierId(GruppeGebietsspezifisch candidateList,
      Gebiet district,
      WahlInfo election) {
    ElectionCategory category = election.getElectionCategory();
    Gruppe politicalGroup = candidateList.getGruppe();

    if (category.isMunicipalityElection()) {
      return GEEN;
    } else if (ElectionCategory.EP.equals(category)) {
      return ALLE;
    } else if (ElectionCategory.EK.equals(category) || ElectionCategory.TK.equals(category)) {
      if (isCentralNomination(politicalGroup)) {
        return ALLE;
      } else {
        return getNumericalContestIdentifierId(district);
      }
    } else if (ElectionCategory.AB.equals(category)) {
      return Integer.toString(1);
    } else if (ElectionCategory.PS.equals(category)) {
      ElectionSubcategory subcategory = election.getElectionSubcategory();
      if (ElectionSubcategory.PS1.equals(subcategory)) {
        return Integer.toString(1);
      } else if (ElectionSubcategory.PS2.equals(subcategory)) {
        if (isCentralNomination(politicalGroup)) {
          return ALLE;
        } else {
          return getNumericalContestIdentifierId(district);
        }
      }
    }

    return null;
  }

  private String getNumericalContestIdentifierId(Gebiet district) {
    if (district.isRoemisch()) {
      return Roman.toRoman(Long.valueOf(district.getNummer()));
    } else {
      return Integer.toString(district.getNummer());
    }
  }

  public String getContestIdentifierName(GruppeGebietsspezifisch candidateList,
      Gebiet district,
      WahlInfo election) {
    String id = getContestIdentifierId(candidateList, district, election);
    if (GEEN.equals(id) || ALLE.equals(id)) {
      return null;
    }
    return district.getName();
  }

  private boolean isCentralNomination(Gruppe gruppe) {
    return ListType.STEL.equals(getListType(gruppe));
  }

  public ListType getListType(Gruppe gruppe) {
    if (gruppe.getListeCol().size() != 1) {
      return ListType.LIST_GROUP;
    } else {
      return getListType(gruppe.getListeCol().iterator().next());
    }
  }

  public ListType getListType(Liste onlyList) {
    if (onlyList.getGruppeGebietsspezifischCol().size() == 1) {
      return ListType.INDEPENDENT;
    } else {
      return ListType.STEL;
    }
  }

}
