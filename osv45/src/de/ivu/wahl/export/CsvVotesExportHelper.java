/*
 * Created on 04.11.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.Nodes;
import nu.xom.XPathContext;

import de.ivu.wahl.Konstanten;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.admin.PropertyHandling;
import de.ivu.wahl.auswertung.erg.CandidateVotesPerRegion;
import de.ivu.wahl.auswertung.erg.PartyWithCandidates;
import de.ivu.wahl.auswertung.erg.ResultSummary;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.Gebietsart;
import de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * Helper to generte the CSV for the votes export file "osv4-3".
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class CsvVotesExportHelper {
  private static final char SEMICOLON = ';';
  private static final char CR = '\n';
  private static final String QUOTES = "\""; //$NON-NLS-1$
  private static final String DOUBLE_QUOTES = "\"\""; //$NON-NLS-1$
  private static final String EMPTY_STRING = ""; //$NON-NLS-1$

  private static final String DOUBLE_SLASH = "//"; //$NON-NLS-1$
  private static final String COLON = ":"; //$NON-NLS-1$
  private static final String STIMMBEZIRK_PREFIX = Gebietsart.STIMMBEZIRK.getKlartext() + " "; // "Stembureau " //$NON-NLS-1$

  private final XPathContext xpathContext;
  private final boolean isReferendum = WahlInfo.getWahlInfo().isReferendum();
  private final boolean isEK = WahlInfo.getWahlInfo().isEK();

  /**
   * Constructor
   */
  CsvVotesExportHelper() {
    this.xpathContext = new XPathContext(de.ivu.wahl.dataimport.XMLTags.NS_PREFIX_EML,
        de.ivu.wahl.dataimport.XMLTags.NS_EML);
    this.xpathContext.addNamespace(de.ivu.wahl.dataimport.XMLTags.NS_PREFIX_KR,
        de.ivu.wahl.dataimport.XMLTags.NS_KR);
  }

  /**
   * @param resultSummary
   * @param propertyHandling
   * @return
   */
  public String createCsvExport(ResultSummary resultSummary,
      Document eml510,
      PropertyHandling propertyHandling) {
    List<List<String>> table = new ArrayList<List<String>>();
    List<Gebiet> regions = new ArrayList<Gebiet>(resultSummary.getGebiete());

    String query = DOUBLE_SLASH + de.ivu.wahl.dataimport.XMLTags.NS_PREFIX_EML + COLON
        + de.ivu.wahl.dataimport.XMLTags.EML_WAHL_IDENTIFIER;
    Element electionIdentifier = (Element) queryForFirstNode(eml510.getRootElement(), query);

    String electionDate = getFirstElementValue(electionIdentifier,
        de.ivu.wahl.dataimport.XMLTags.NS_KR,
        de.ivu.wahl.dataimport.XMLTags.KR_WAHL_DATUM);

    String electionName = getFirstElementValue(electionIdentifier,
        de.ivu.wahl.dataimport.XMLTags.NS_EML,
        de.ivu.wahl.dataimport.XMLTags.EML_WAHL_NAME);

    String regionType = getRegionTypePrefix(electionIdentifier, eml510.getRootElement());

    query = DOUBLE_SLASH + de.ivu.wahl.dataimport.XMLTags.NS_PREFIX_EML + COLON
        + de.ivu.wahl.dataimport.XMLTags.EML_AUTHORITY_IDENTIFIER;
    Element authorityIdentifier = (Element) queryForFirstNode(eml510.getRootElement(), query);

    String authorityIdentifierValue = authorityIdentifier.getValue();
    if ("Kiesraad".equals(authorityIdentifierValue) || "De Kiesraad".equals(authorityIdentifierValue)) { //$NON-NLS-1$ //$NON-NLS-2$
      authorityIdentifierValue = "Nederland"; //$NON-NLS-1$
    }

    String authorityIdentifierId = authorityIdentifier
        .getAttributeValue(de.ivu.wahl.dataimport.XMLTags.ATTR_EML_ID);

    table.add(Arrays.asList(Messages.getString(MessageKeys.Msg_ElectionName),
        EMPTY_STRING,
        electionName));
    table.add(Arrays.asList(Messages.getString(MessageKeys.Msg_ElectionDate),
        EMPTY_STRING,
        electionDate));
    table.add(Arrays.asList(Messages.getString(MessageKeys.Msg_ElectionDomain),
        EMPTY_STRING,
        regionType + (authorityIdentifierValue == null ? EMPTY_STRING : authorityIdentifierValue)));
    table.add(Arrays.asList(Messages.getString(MessageKeys.Msg_AuthorityIdentifier),
        EMPTY_STRING,
        authorityIdentifierId));

    List<String> emptyList = Collections.emptyList();
    table.add(emptyList);

    table.add(getFirstLine(regions));
    table.add(getLineWithRegionNumbers(regions));
    List<String> secondLine = getLineWithPostcodes(regions);
    if (secondLine != null) {
      table.add(secondLine);
    }

    for (PartyWithCandidates partyWithCandidates : resultSummary.getAllGroupsAndCandidates()) {
      if (regions.isEmpty() || isVisible(regions, partyWithCandidates.getGruppenPosition())) {
        table.add(getLineForGroup(partyWithCandidates, regions));
        for (CandidateVotesPerRegion candidateVotesPerRegion : partyWithCandidates
            .getCandidateVotesPerRegion()) {
          table.add(getLineForCandidate(candidateVotesPerRegion, regions));
        }
      }
    }
    if (isReferendum) {
      for (PartyWithCandidates partyWithCandidates : resultSummary.getAllGroupsAndCandidates()) {
        if (partyWithCandidates.getGruppenPosition() == GruppeAllgemein.GUELTIGE.getPosition()) {
          table.add(getLineForReferendumQuestion(partyWithCandidates, regions, propertyHandling));
        }
        if (partyWithCandidates.getGruppenPosition() > 0) {
          table.add(getLineForReferendumAnswer(partyWithCandidates, regions, propertyHandling));
        }
      }
    }

    return createCsvExport(table);
  }

  private boolean isVisible(List<Gebiet> regions, int gruppenPosition) {
    if (isReferendum && gruppenPosition > 0) {
      return false;
    }
    for (Gebiet gebiet : regions) {
      if (GruppeAllgemein.isVisibleInOverview(gruppenPosition, gebiet))
        return true;
    }
    return false;
  }

  /**
   * The region type prefix shall be empty for all elections but TK, PS and EP. Otherwise it shall
   * be "gemeente" or "kieskring".
   */
  private String getRegionTypePrefix(Element electionIdentifier, Element rootElement) {
    String electionCategoryName = getFirstElementValue(electionIdentifier,
        de.ivu.wahl.dataimport.XMLTags.NS_EML,
        de.ivu.wahl.dataimport.XMLTags.WAHL_ART);
    ElectionCategory electionCategory = ElectionCategory.fromValue(electionCategoryName);
    if (electionCategory.isMunicipalityElection() || electionCategory.isEK()) {
      return StringUtils.EMPTY;
    }

    String query = DOUBLE_SLASH + de.ivu.wahl.dataimport.XMLTags.NS_PREFIX_EML + COLON
        + de.ivu.wahl.dataimport.XMLTags.EML;
    Element eml = (Element) queryForFirstNode(rootElement, query);
    if (eml == null) {
      return StringUtils.EMPTY;
    }
    Attribute emlId = eml.getAttribute(de.ivu.wahl.dataimport.XMLTags.ATTR_EML_ID);
    if (emlId == null) {
      return StringUtils.EMPTY;
    }

    if (de.ivu.wahl.dataimport.XMLTags.ATTR_VAL_EML_ID_510b.equals(emlId.getValue())) {
      return Gebietsart.GEMEINDE.getKlartext() + " "; //$NON-NLS-1$
    } else if (de.ivu.wahl.dataimport.XMLTags.ATTR_VAL_EML_ID_510c.equals(emlId.getValue())) {
      return Gebietsart.WAHLKREIS.getKlartext() + " "; //$NON-NLS-1$
    } else {
      return StringUtils.EMPTY;
    }
  }

  /**
   * Generates the first line with the static headers and names of regions
   */
  private List<String> getFirstLine(List<Gebiet> regions) {
    List<String> line = new ArrayList<String>();
    line.add(Messages.getString(isReferendum
        ? MessageKeys.Msg_QuestionNumber
        : MessageKeys.Msg_ListNumber));
    line.add(Messages.getString(MessageKeys.Msg_ListName));
    line.add(Messages.getString(MessageKeys.Msg_PositionOnList));
    line.add(Messages.getString(isReferendum
        ? MessageKeys.Msg_Answer
        : MessageKeys.Msg_CandidateName));
    line.add(Messages.getString(MessageKeys.Msg_Total));
    for (Gebiet gebiet : regions) {
      String regionName = gebiet.getName();
      if (isEK && regionName.startsWith(STIMMBEZIRK_PREFIX)) {
        regionName = regionName.substring(STIMMBEZIRK_PREFIX.length());
      }

      line.add(regionName);

    }
    return line;
  }

  /**
   * Generates a line with the numbers of the regions
   */
  private List<String> getLineWithRegionNumbers(List<Gebiet> regions) {
    List<String> line = initLineForRegions(Messages.getString(MessageKeys.Msg_RegionNumber));
    for (Gebiet gebiet : regions) {
      line.add(gebiet.getNumber4Display());
    }
    return line;
  }

  /**
   * Generates the second line with the post codes of regions
   */
  private List<String> getLineWithPostcodes(List<Gebiet> regions) {
    boolean isStembureauWithPostCode = false;
    List<String> line = initLineForRegions(Messages.getString(MessageKeys.Msg_Postcode));
    for (Gebiet gebiet : regions) {
      if (gebiet.getGebietsart() == GebietModel.GEBIETSART_STIMMBEZIRK) {
        if (!StringUtils.isBlank(gebiet.getZipcode())) {
          isStembureauWithPostCode = true;
          line.add(gebiet.getZipcode());
        } else {
          line.add(EMPTY_STRING);
        }
      } else {
        line.add(EMPTY_STRING);
      }
    }
    if (isStembureauWithPostCode) {
      return line;
    }
    return null;
  }

  private List<String> initLineForRegions(String title) {
    List<String> line = new ArrayList<String>();
    line.add(title);
    line.add(EMPTY_STRING);
    line.add(EMPTY_STRING);
    line.add(EMPTY_STRING);
    line.add(EMPTY_STRING);
    return line;
  }

  /**
   * Generates the strings for the party line
   */
  private List<String> getLineForGroup(PartyWithCandidates partyWithCandidates, List<Gebiet> regions) {
    List<String> result = new ArrayList<String>();
    int gruppenPosition = partyWithCandidates.getGruppenPosition();
    if (gruppenPosition > 0) {
      add(result, gruppenPosition);
    } else {
      result.add(EMPTY_STRING);
    }

    result.add(partyWithCandidates.getGruppenName());
    result.add(EMPTY_STRING);
    result.add(EMPTY_STRING);
    add(result, partyWithCandidates.getSumme());
    for (Gebiet gebiet : regions) {
      result.add(partyWithCandidates.getGruppenstimmeProGebiet(gebiet));
    }

    return result;
  }

  /**
   * This line contains the hard-coded number 1, the text of the referendum question and the vote
   * numbers of the GruppeAllgemein.GUELTIGE.
   */
  private List<String> getLineForReferendumAnswer(PartyWithCandidates partyWithCandidates,
      List<Gebiet> regions,
      PropertyHandling propertyHandling) {
    List<String> result = new ArrayList<String>();
    result.add(EMPTY_STRING);
    result.add(EMPTY_STRING);

    int gruppenPosition = partyWithCandidates.getGruppenPosition();
    add(result, gruppenPosition);
    String answer = propertyHandling.getProperty(Konstanten.KEY_REFERENDUM_ANSW + gruppenPosition);
    result.add(answer);
    add(result, partyWithCandidates.getSumme());
    for (Gebiet gebiet : regions) {
      result.add(partyWithCandidates.getGruppenstimmeProGebiet(gebiet));
    }

    return result;
  }

  /**
   * This line contains the hard-coded number 1, the text of the referendum question and the vote
   * numbers of the GruppeAllgemein.GUELTIGE.
   */
  private List<String> getLineForReferendumQuestion(PartyWithCandidates partyWithCandidates,
      List<Gebiet> regions,
      PropertyHandling propertyHandling) {
    List<String> result = new ArrayList<String>();
    result.add("1"); //$NON-NLS-1$
    String proposalName = propertyHandling.getProperty(Konstanten.KEY_REFERENDUM_TEXT);
    result.add(proposalName);
    result.add(EMPTY_STRING);
    result.add(EMPTY_STRING);
    add(result, partyWithCandidates.getSumme());
    for (Gebiet gebiet : regions) {
      result.add(partyWithCandidates.getGruppenstimmeProGebiet(gebiet));
    }

    return result;
  }

  /**
   * Generates the strings for the candidate line
   */
  private List<String> getLineForCandidate(CandidateVotesPerRegion candidateVotesPerRegion,
      List<Gebiet> regions) {
    List<String> result = new ArrayList<String>();
    result.add(EMPTY_STRING);
    result.add(EMPTY_STRING);
    if (candidateVotesPerRegion.isPositionOnListUnique()) {
      add(result, candidateVotesPerRegion.getPositionOnList());
    } else {
      result.add("(" + candidateVotesPerRegion.getPersonenName() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    result.add(candidateVotesPerRegion.getPersonenName());
    add(result, candidateVotesPerRegion.getSumme());
    for (Gebiet gebiet : regions) {
      result.add(candidateVotesPerRegion.getKandidatenstimmeProGebiet(gebiet));
    }

    return result;
  }

  private void add(List<String> result, int aNumber) {
    result.add(String.valueOf(aNumber));
  }

  private String createCsvExport(List<List<String>> table) {
    StringBuilder builder = new StringBuilder();
    for (List<String> line : table) {
      boolean firstCell = true;
      for (String cell : line) {
        if (firstCell) {
          firstCell = false;
        } else {
          builder.append(SEMICOLON);
        }
        builder.append(QUOTES);
        builder.append(cell.replace(QUOTES, DOUBLE_QUOTES));
        builder.append(QUOTES);
      }

      builder.append(CR);
    }

    return builder.toString();
  }

  private Node queryForFirstNode(Element root, String query) {
    Nodes eidNodes = root.query(query, xpathContext);
    if (eidNodes == null || eidNodes.size() == 0) {
      return null;
    }

    return eidNodes.get(0);
  }

  /**
   * Utility method. Reads the value of a child element with given childName in given namespace from
   * the given parent.
   */
  private String getFirstElementValue(Element parent, String namespace, String childName) {
    if (parent == null) {
      return null;
    }
    Element childElement = parent.getFirstChildElement(childName, namespace);
    if (childElement == null) {
      return null;
    }
    return childElement.getValue();
  }

}
