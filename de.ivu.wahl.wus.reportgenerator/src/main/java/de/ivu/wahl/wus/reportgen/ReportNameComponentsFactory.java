/*
 * ReportNameComponentsFactory
 * 
 * Created on 03.04.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.Nodes;
import nu.xom.XPathContext;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;
import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;

public class ReportNameComponentsFactory {
  private Document eml;
  private XPathContext xpathContext;

  public ReportNameComponentsFactory(Document eml) {
    this.eml = eml;
    this.xpathContext = new XPathContext(XMLTags.PRAEFIX_EML, XMLTags.NS_EML);
    this.xpathContext.addNamespace(XMLTags.NS_PREFIX_RG, XMLTags.NS_RG);
  }

  /**
   * Configures the nc for P4 by reading relevant elements from the eml.
   */
  public void fill(ReportNameComponentsP4 nc) {
    Element root = eml.getRootElement();
    String emlId = root.getAttributeValue(XMLTags.ATTR_EML_ID);
    nc.setEmlType(EMLMessageType.byId(emlId));

    fillFromElectionIdentifier(nc, root);
    fillFromManagingAuthority(nc, root);
  }

  /**
   * Configures the nc for P5 by reading relevant elements from the eml.
   */
  public void fill(ReportNameComponentsP5 nc) {
    Element root = eml.getRootElement();
    fillFromElectionIdentifier(nc, root);
  }

  /**
   * Configures the nc for Eml630 (P0) by reading relevant elements from the eml.
   */
  public void fill(ReportNameComponentsP0 nc) {
    Element root = eml.getRootElement();
    fillFromElectionIdentifier(nc, root);
  }

  /**
   * Configures the nc for Eml230b (P3) by reading relevant elements from the eml.
   */
  public void fill(ReportNameComponentsEml230b nc) {
    Element root = eml.getRootElement();
    Element electionIdentifier = fillFromElectionIdentifier(nc, root);

    String electionSubcategory = getFirstElementValue(electionIdentifier,
        XMLTags.NS_KR,
        XMLTags.ELECTION_SUBCATEGORY);
    if (electionSubcategory != null) {
      nc.setElectionSubcategory(ElectionSubcategory.valueOf(electionSubcategory));
    }

    String query = "//" + XMLTags.PRAEFIX_EML + ":" + XMLTags.EML_CONTEST_IDENTIFIER;
    Element contestIdentifier = (Element) queryForFirstNode(root, query, xpathContext);
    if (contestIdentifier != null) {
      nc.setElectoralDistrict(contestIdentifier.getValue());
    }
  }

  /**
   * Reads the election identifier, election category and election domain and sets them in the
   * <code>nc</code> parameter..
   * 
   * @param nc
   * @param root
   * @return the ElectionIdentifier element
   */
  private Element fillFromElectionIdentifier(ReportNameComponentsSetter nc, Element root) {
    String query = "//" + XMLTags.PRAEFIX_EML + ":" + XMLTags.ELECTION_IDENTIFIER;
    Element electionIdentifier = (Element) queryForFirstNode(root, query, xpathContext);
    if (electionIdentifier == null) {
      return null;
    }
    String electionIdentifierId = electionIdentifier.getAttributeValue(XMLTags.ATTR_EML_ID);
    nc.setElectionIdentifier(electionIdentifierId);

    String electionCategory = getFirstElementValue(electionIdentifier,
        XMLTags.NS_EML,
        XMLTags.ELECTION_CATEGORY);
    nc.setElectionCategory(ElectionCategory.fromValue(electionCategory));

    String electionDomain = getFirstElementValue(electionIdentifier,
        XMLTags.NS_KR,
        XMLTags.ELECTION_DOMAIN);
    nc.setElectionDomain(electionDomain);

    return electionIdentifier;
  }

  private void fillFromManagingAuthority(ReportNameComponentsP4 nc, Element root) {
    Element manAuth = root.getFirstChildElement(XMLTags.EML_AUTHORITY, XMLTags.NS_EML);
    Element authId = manAuth.getFirstChildElement(XMLTags.EML_AUTHORITY_IDENTIFIER, XMLTags.NS_EML);

    String authorityIdentifier = authId.getValue();
    if (authorityIdentifier.matches(".*\\(" + XMLTags.KEYWORD_POSTAL_CODE + ".*\\)")) {
      authorityIdentifier = authorityIdentifier.split(" \\(" + XMLTags.KEYWORD_POSTAL_CODE)[0];
    }
    if (authorityIdentifier.matches(XMLTags.PREFIX_STEMBUREAU + ".*")) {
      authorityIdentifier = authorityIdentifier.substring(XMLTags.PREFIX_STEMBUREAU.length());
    }
    nc.setManagingAuthority(authorityIdentifier);

    String authorityIdentifierId = authId.getAttributeValue(XMLTags.ATTR_AUTHORITY_IDENTIFIER_ID);
    nc.setManagingAuthorityId(authorityIdentifierId);

    String createdByAuthority = getFirstElementValue(manAuth,
        XMLTags.NS_KR,
        XMLTags.KR_CREATED_BY_AUTHORITY);
    nc.setCreatedByAuthority(createdByAuthority);
  }

  private Node queryForFirstNode(Element root, String query, XPathContext context) {
    Nodes eidNodes = root.query(query, context);
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
