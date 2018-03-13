/*
 * GruppeAllgemeinXmlAdapter
 * 
 * Created on 08.11.2013
 * Copyright (c) 2013 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import static de.ivu.wahl.dataimport.XMLTags.NS_EML;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.ADMITTED_VOTERS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.BALLOT_PAPERS_LOST;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.BALLOT_PAPER_NOT_RETURNED;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.ELECTION_NOTICES;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.EMPTY_POSTAL_VOTES;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.GUELTIGE;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.LEER;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.LESS_VALID_VOTES_THAN_ADMITTED_VOTERS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.MORE_VALID_VOTES_THAN_ADMITTED_VOTERS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.NO_EXPLANATION;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.OTHER_EXPLANATIONS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.POLLING_CARDS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.POSTAL_VOTES_WITH_MULTIPLE_BALLOT_PAPERS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.PROXY_VOTERS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.TOO_FEW_BALLOT_PAPER_ISSUED;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.TOO_MANY_BALLOT_PAPER_ISSUED;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.UNGUELTIGE;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.WAHLBERECHTIGTE;

import java.util.LinkedHashMap;
import java.util.Map;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Nodes;

import de.ivu.wahl.export.XMLHelper;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.util.XMLImportHelper;

/**
 * Can be used to read or write values for special groups from / to an EML XML structure or write it
 * to an report generator extension XML structure.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class GruppeAllgemeinXmlAdapter {
  private final Map<GruppeAllgemein, ValueAccess> gruppeAllgemeinToReasonCode = new LinkedHashMap<GruppeAllgemein, ValueAccess>();

  public GruppeAllgemeinXmlAdapter() {
    addFromElement(WAHLBERECHTIGTE, XMLTags.EML_CAST, de.ivu.wahl.export.XMLTags.RG_CAST);
    addFromElement(GUELTIGE, XMLTags.EML_TOTAL_COUNTED, de.ivu.wahl.export.XMLTags.RG_TOTAL_COUNTED);
    addRejected(UNGUELTIGE,
        XMLTags.ATTR_VAL_INVALID_INVALID,
        de.ivu.wahl.export.XMLTags.RG_INVALID_VOTES);
    addRejected(LEER, XMLTags.ATTR_VAL_INVALID_BLANCO, de.ivu.wahl.export.XMLTags.RG_BLANC_VOTES);
    // GUELTIG_ODER_LEER is not in the EML, so no mapping is needed

    addUncounted(ELECTION_NOTICES,
        XMLTags.ATTR_VAL_VOTERS_WITH_ELECTION_NOTICES,
        de.ivu.wahl.export.XMLTags.RG_VOTERS_WITH_ELECTION_NOTICES);
    addUncounted(PROXY_VOTERS,
        XMLTags.ATTR_VAL_PROXY_VOTERS,
        de.ivu.wahl.export.XMLTags.RG_PROXY_VOTERS);
    addUncounted(POLLING_CARDS,
        XMLTags.ATTR_VAL_VOTERS_WITH_POLLING_CARDS,
        de.ivu.wahl.export.XMLTags.RG_VOTERS_WITH_POLLING_CARDS);
    addUncounted(ADMITTED_VOTERS,
        XMLTags.ATTR_VAL_ADMITTED_VOTERS,
        de.ivu.wahl.export.XMLTags.RG_ADMITTED_VOTERS);

    addUncounted(MORE_VALID_VOTES_THAN_ADMITTED_VOTERS,
        XMLTags.ATTR_VAL_MORE_VALID_VOTES_THAN_ADMITTED_VOTERS,
        de.ivu.wahl.export.XMLTags.RG_MORE_VALID_VOTES_THAN_ADMITTED_VOTERS);
    addUncounted(LESS_VALID_VOTES_THAN_ADMITTED_VOTERS,
        XMLTags.ATTR_VAL_LESS_VALID_VOTES_THAN_ADMITTED_VOTERS,
        de.ivu.wahl.export.XMLTags.RG_LESS_VALID_VOTES_THAN_ADMITTED_VOTERS);
    addUncounted(BALLOT_PAPER_NOT_RETURNED,
        XMLTags.ATTR_VAL_BALLOT_PAPER_NOT_RETURNED,
        de.ivu.wahl.export.XMLTags.RG_BALLOT_PAPER_NOT_RETURNED);
    addUncounted(TOO_FEW_BALLOT_PAPER_ISSUED,
        XMLTags.ATTR_VAL_TOO_FEW_BALLOT_PAPER_ISSUED,
        de.ivu.wahl.export.XMLTags.RG_TOO_FEW_BALLOT_PAPER_ISSUED);
    addUncounted(TOO_MANY_BALLOT_PAPER_ISSUED,
        XMLTags.ATTR_VAL_TOO_MANY_BALLOT_PAPER_ISSUED,
        de.ivu.wahl.export.XMLTags.RG_TOO_MANY_BALLOT_PAPER_ISSUED);
    addUncounted(EMPTY_POSTAL_VOTES,
        XMLTags.ATTR_VAL_EMPTY_POSTAL_VOTES,
        de.ivu.wahl.export.XMLTags.RG_EMPTY_POSTAL_VOTES);
    addUncounted(POSTAL_VOTES_WITH_MULTIPLE_BALLOT_PAPERS,
        XMLTags.ATTR_VAL_POSTAL_VOTES_WITH_MULTIPLE_BALLOT_PAPERS,
        de.ivu.wahl.export.XMLTags.RG_POSTAL_VOTES_WITH_MULTIPLE_BALLOT_PAPERS);
    addUncounted(BALLOT_PAPERS_LOST,
        XMLTags.ATTR_VAL_BALLOT_PAPERS_LOST,
        de.ivu.wahl.export.XMLTags.RG_BALLOT_PAPERS_LOST);
    addUncounted(NO_EXPLANATION,
        XMLTags.ATTR_VAL_NO_EXPLANATION,
        de.ivu.wahl.export.XMLTags.RG_NO_EXPLANATION);
    addUncounted(OTHER_EXPLANATIONS,
        XMLTags.ATTR_VAL_OTHER_EXPLANATIONS,
        de.ivu.wahl.export.XMLTags.RG_OTHER_EXPLANATIONS);
  }

  /**
   * The values for this special group can be found in <eml:emlElementName> and <rg:rgElementName>
   */
  private void addFromElement(GruppeAllgemein gruppeAllgemein,
      String emlElementName,
      String rgElementName) {
    gruppeAllgemeinToReasonCode
        .put(gruppeAllgemein, new ValueAccess(emlElementName, rgElementName));
  }

  /**
   * The values for this special group can be found in <eml:UncountedVotes ReasonCode="reasonCode">
   * and <rg:rgElementName>
   */
  private void addUncounted(GruppeAllgemein gruppeAllgemein, String reasonCode, String rgElementName) {
    gruppeAllgemeinToReasonCode.put(gruppeAllgemein, new ValueAccess(XMLTags.EML_UNCOUNTED,
        reasonCode, rgElementName));
  }

  /**
   * The values for this special group can be found in <eml:RejectedVotes ReasonCode="reasonCode">
   * and <rg:rgElementName>
   */
  private void addRejected(GruppeAllgemein gruppeAllgemein, String reasonCode, String rgElementName) {
    gruppeAllgemeinToReasonCode.put(gruppeAllgemein, new ValueAccess(XMLTags.EML_REJECTED,
        reasonCode, rgElementName));
  }

  public int getXml(Element totalVotes, GruppeAllgemein gruppeAllgemein) throws ImportException {
    ValueAccess valueAccess = gruppeAllgemeinToReasonCode.get(gruppeAllgemein);
    if (valueAccess._attributeValue != null) {
      return getFromElementWithAttributeValue(totalVotes, valueAccess, false);
    } else {
      return getFromElement(totalVotes, valueAccess, false);
    }
  }

  /**
   * Reads the value from an EML structure. Returns 0, if not found.
   */
  public int getFromEmlOr0(Element totalVotes, GruppeAllgemein gruppeAllgemein)
      throws ImportException {
    Integer result = getFromEmlOrNull(totalVotes, gruppeAllgemein);
    return result == null ? 0 : result;
  }

  /**
   * Reads the value from an EML structure. Returns <code>null</code>, if not found.
   */
  public Integer getFromEmlOrNull(Element totalVotes, GruppeAllgemein gruppeAllgemein)
      throws ImportException {
    ValueAccess valueAccess = gruppeAllgemeinToReasonCode.get(gruppeAllgemein);
    if (valueAccess._attributeValue != null) {
      return getFromElementWithAttributeValue(totalVotes, valueAccess, true);
    } else {
      return getFromElement(totalVotes, valueAccess, true);
    }
  }

  /**
   * Insert the value into the EML XML structure
   */
  public void putEmlXml(Element parent, GruppeAllgemein gruppeAllgemein, int value) {
    ValueAccess valueAccess = gruppeAllgemeinToReasonCode.get(gruppeAllgemein);
    if (valueAccess._attributeValue != null) {
      addEmlElementWithAttributeValue(parent, valueAccess, value);
    } else {
      addEmlElement(parent, valueAccess, value);
    }
  }

  /**
   * Insert the value into the report generator extension XML structure
   */
  public void putRgXml(Element parent, GruppeAllgemein gruppeAllgemein, int value) {
    ValueAccess valueAccess = gruppeAllgemeinToReasonCode.get(gruppeAllgemein);
    String rgElementName = valueAccess._rgElementName;
    if (rgElementName != null) {
      parent.appendChild(XMLHelper.createElementWithValue(rgElementName,
          de.ivu.wahl.dataimport.XMLTags.NS_RG,
          value));
    }
  }

  public Iterable<GruppeAllgemein> getGruppenAllgemein() {
    return gruppeAllgemeinToReasonCode.keySet();
  }

  /**
   * @param totalVotes the XML parent element, either TotalVotes or ReportingUnitVotes
   * @param valueAccess information how to find the value in totalVotes
   * @param isOptional determines what happens, if the element is not found. In case
   *          <code>isOptional == true</code>, <code>null</code> is returned, otherwise an
   *          ImportException is thrown
   * @return
   * @throws ImportException if the element is not found
   */
  private Integer getFromElement(Element totalVotes, ValueAccess valueAccess, boolean isOptional)
      throws ImportException {
    Element firstChild = totalVotes.getFirstChildElement(valueAccess._emlElementName, NS_EML);
    if (firstChild == null) {
      if (isOptional) {
        return null;
      } else {
        String msg = Messages.bind(MessageKeys.Msg_Element_0_NichtGefunden,
            valueAccess._emlElementName);
        throw new ImportException(msg);
      }
    }
    return XMLImportHelper.getIntValue(firstChild);
  }

  /**
   * Reads a number value from an element UncountedVotes[@ReasonCode = "art]"
   * 
   * @param totalVotes the XML parent element, either TotalVotes or ReportingUnitVotes
   * @param valueAccess information how to find the value in totalVotes
   * @param isOptional determines what happens, if the element is not found. In case
   *          <code>isOptional == true</code>, <code>null</code> is returned, otherwise an
   *          ImportException is thrown
   * @return the value of the UncountedVotes element
   * @throws ImportException if the element is not found or is not unique
   */
  private Integer getFromElementWithAttributeValue(Element totalVotes,
      ValueAccess valueAccess,
      boolean isOptional) throws ImportException {
    String elementName = valueAccess._emlElementName;
    String attributeName = valueAccess._attributeName;
    String attributeValue = valueAccess._attributeValue;
    Nodes ungezaehlteNodes = totalVotes.query(XMLTags.PRAEFIX_EML + ":" + elementName //$NON-NLS-1$
        + "[@" + attributeName + "='" + attributeValue + "']", XMLTags.CONTEXT_EML); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
    if (ungezaehlteNodes.size() != 1) {
      String msg;
      if (ungezaehlteNodes.size() == 0) {
        if (isOptional) {
          return null;
        }
        msg = Messages.bind(MessageKeys.Msg_Element_0_MitAttribut_1_Gleich_2_NichtGefunden,
            elementName,
            attributeName,
            attributeValue);
      } else {
        msg = Messages.bind(MessageKeys.Msg_Element_0_MitAttribut_1_Gleich_2_MehrfachGefunden,
            elementName,
            attributeName,
            attributeValue);
      }
      throw new ImportException(msg);
    }
    return XMLImportHelper.getIntValue((Element) ungezaehlteNodes.get(0));
  }

  /**
   * Creates a child element of parent to store the given value
   */
  private void addEmlElementWithAttributeValue(Element parent, ValueAccess valueAccess, int value) {
    String elementName = valueAccess._emlElementName;
    String attributeName = valueAccess._attributeName;
    String attributeValue = valueAccess._attributeValue;
    Element child = XMLHelper.createElementWithValue(elementName, NS_EML, value);
    child.addAttribute(new Attribute(attributeName, attributeValue));
    parent.appendChild(child);
  }

  /**
   * Creates a child element of parent to store the given value
   */
  private void addEmlElement(Element parent, ValueAccess valueAccess, int value) {
    String elementName = valueAccess._emlElementName;
    parent.appendChild(XMLHelper.createElementWithValue(elementName, NS_EML, value));
  }

  static class ValueAccess {
    // Needed for EML structure
    final String _emlElementName;
    final String _attributeValue;
    final String _attributeName = XMLTags.EML_ATTR_INVALID_REASON;
    // Needed for report generator extension (RG) structure
    final String _rgElementName;

    ValueAccess(String elementName, String rgElementName) {
      this._emlElementName = elementName;
      this._attributeValue = null;
      this._rgElementName = rgElementName;
    }

    ValueAccess(String emlElementName, String attributeValue, String rgElementName) {
      this._emlElementName = emlElementName;
      this._attributeValue = attributeValue;
      this._rgElementName = rgElementName;
    }
  }

}
