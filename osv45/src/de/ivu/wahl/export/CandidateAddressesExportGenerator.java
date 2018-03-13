/*
 * Created on 30.08.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.PersonendatenKonstanten;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifisch;
import de.ivu.wahl.modell.ejb.Liste;
import de.ivu.wahl.modell.ejb.Listenkandidatur;
import de.ivu.wahl.modell.ejb.Personendaten;
import de.ivu.wahl.wus.reportgen.csv.CsvGenerator;

/**
 * Generates the CSV String for the Export of the Candidate names and addresses (osv5-5)
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class CandidateAddressesExportGenerator {
  private final static String EMPTY_STRING = ""; //$NON-NLS-1$
  private final static String SPACE = " "; //$NON-NLS-1$

  private final static String YES = Messages.getString(MessageKeys.CandidateExportGenerator_yes);
  private final static String NO = Messages.getString(MessageKeys.CandidateExportGenerator_no);

  private final CsvGenerator table = new CsvGenerator();
  private final WahlInfo wahlInfo = WahlInfo.getWahlInfo();
  private final List<GruppeGebietsspezifisch> allCandidateLists;
  private final boolean isElectionWithListGroups = wahlInfo.getElectionSubcategory()
      .isElectionWithListGroups();

  @SuppressWarnings("hiding")
  public CandidateAddressesExportGenerator(List<GruppeGebietsspezifisch> allCandidateLists) {
    this.allCandidateLists = allCandidateLists;
  }

  public String generateCandidateExport() {
    table.startLine();
    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_title));
    table.newLine();
    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_subtitle));
    table.newLine();
    table.add(Messages.bind(MessageKeys.CandidateExportGenerator_election, wahlInfo.getWahlName()));
    table.newLine();
    table.newLine();
    addHeadings();
    table.finishLine();

    Collections.sort(allCandidateLists, new Comparator<GruppeGebietsspezifisch>() {
      @SuppressWarnings("synthetic-access")
      @Override
      public int compare(GruppeGebietsspezifisch x, GruppeGebietsspezifisch y) {
        int result = x.getGebiet().getNummer() - y.getGebiet().getNummer();
        if (result != 0) {
          return result;
        }
        result = x.getGruppe().getSchluessel() - y.getGruppe().getSchluessel();
        if (result != 0) {
          return result;
        }
        result = getPoliticalGroupName(x).compareTo(getPoliticalGroupName(y));
        return result;
      }
    });
    for (GruppeGebietsspezifisch candidateList : allCandidateLists) {
      Liste liste = candidateList.getListe();
      if (liste != null) {
        Collection<Listenkandidatur> candidates = liste.getListenkandidaturCol();
        for (Listenkandidatur candidate : candidates) {
          table.startLine();
          addListData(candidateList);
          addCandidateData(candidate);

          table.finishLine();
        }
      }
    }

    return table.getCsv();
  }

  private void addHeadings() {
    if (isElectionWithListGroups) {
      table.add(Messages.getString(MessageKeys.CandidateExportGenerator_district)); // 1. kieskring
      // number
    }
    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_listNumber));
    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_politicalgroup));
    if (isElectionWithListGroups) {
      table.add(Messages.getString(MessageKeys.CandidateExportGenerator_type));
    }
    // Removed because of OSV-1009, re-introduce for OSV-1203
    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_publishGender));
    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_publicationLanguage));

    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_positionOnList)); // 1.
    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_lastname));
    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_initials));
    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_firstname));
    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_city));
    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_countrycode));
    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_gender));
    // VOID !!! date of birth
    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_street));
    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_postcode));
    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_city));
    table.add(Messages.getString(MessageKeys.CandidateExportGenerator_countrycode));
  }

  private void addListData(GruppeGebietsspezifisch candidateList) {
    if (isElectionWithListGroups) {
      // 1. position on list
      table.add(getDistrictNumber(candidateList, wahlInfo));
    }

    // 2. list number (if known)
    Gruppe politicalGroup = candidateList.getGruppe();
    int position = politicalGroup.getSchluessel();
    if (position > 0) {
      table.add(position);
    } else {
      table.addEmptyCell();
    }

    // 3. Name of list or "Lijst <last name of first candidate>" for blanko lists
    table.add(getPoliticalGroupName(candidateList));

    if (isElectionWithListGroups) {
      // 4. type of list ("op zichzelf staande lijst" or "lijstengroep" or
      // "stel gelijkluidende lijsten")
      ListType affiliationType = new ContestIdentifierService().getListType(politicalGroup);
      if (affiliationType != null) {
        table.add(affiliationType.getEml());
      } else {
        table.addEmptyCell();
      }
    }

    // 5. publish gender (ja/nee)
    if (candidateList.getListe().isGeschlechtSichtbar()) {
      table.add(YES);
    } else {
      table.add(NO);
    }

    // 6. publicationLanguage
    table.add(candidateList.getListe().getPublicationLanguage());
  }

  private String getPoliticalGroupName(GruppeGebietsspezifisch x) {
    Gruppe politicalGroup = x.getGruppe();
    String politicalGroupName = politicalGroup.getNameKurz();
    if (politicalGroupName != null && politicalGroupName.length() > 0) {
      return politicalGroupName;
    } else {
      Listenkandidatur firstCandidate = x.getListe().getListenkandidaturCol().iterator().next();
      if (firstCandidate == null) {
        return Messages.getString(MessageKeys.CandidateExportGenerator_blancList);
      } else {
        Personendaten person = firstCandidate.getPersonendaten();
        return Messages.bind(MessageKeys.CandidateExportGenerator_blancListName,
            person.getNachname());
      }
    }
  }

  private String getDistrictNumber(GruppeGebietsspezifisch candidateList, WahlInfo election) {
    Gebiet district = candidateList.getGebiet();
    ContestIdentifierService ciService = new ContestIdentifierService();
    String districtNumber = ciService.getContestIdentifierId(candidateList, district, election);
    return districtNumber;
  }

  private void addCandidateData(Listenkandidatur candidate) {
    Personendaten person = candidate.getPersonendaten();

    // 1. position on list
    table.add(candidate.getListenplatz());

    // 2. last name (including tussenvoegsel)
    String lastName = nullSafe(person.getNachname());
    String namePrefix = nullSafe(person.getPraefix());
    if (namePrefix.length() > 0 && lastName.length() > 0) {
      table.add(namePrefix + SPACE + lastName);
    } else {
      table.add(namePrefix + lastName);
    }

    // 3. initials
    table.add(person.getInitialen());

    // 4. first name
    table.add(person.getVorname());

    // 5. and 6. city, country code
    table.add(person.getWohnort());
    table.add(nullSafe(person.getLand()));

    // 7. gender (m or v or f or -)
    table.add(PersonendatenKonstanten.Geschlecht.getName(person.getGeschlecht(), candidate
        .getListe().getPublicationLanguage()));

    // 8. VOID !!! date of birth (in the format "dd-mm-yyyy")

    // 9.-12. (correspondence) street, postcode, city, country code
    table.add(person.getKontakt_Strasse());
    table.add(person.getKontakt_PLZ());
    table.add(person.getKontakt_Wohnort());
    table.add(nullSafe(person.getKontakt_Land()));

    // 13. address on candidate list (ja/nee)
    // Removed because of OSV-1009, re-introduce for OSVI-1343
    // table.addEmptyCell();
  }

  private String nullSafe(String string) {
    return string == null ? EMPTY_STRING : string;
  }

}
