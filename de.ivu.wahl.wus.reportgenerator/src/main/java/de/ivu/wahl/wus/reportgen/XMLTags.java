/*
 * Created on 03.04.2009
 * 
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

/**
 * Konstanten zur Bezeichnung der XML-Tags und Attribute in Import- und Exportdateien
 * 
 * @author jon
 */
public interface XMLTags {

  String NS_EML = "urn:oasis:names:tc:evs:schema:eml"; //$NON-NLS-1$
  String NS_RG = "http://www.kiesraad.nl/reportgenerator"; //$NON-NLS-1$
  String NS_KR = "http://www.kiesraad.nl/extensions"; //$NON-NLS-1$

  String NS_PREFIX_EML = "eml"; //$NON-NLS-1$
  String NS_PREFIX_RG = "rg"; //$NON-NLS-1$
  String NS_PREFIX_KR = "kr"; //$NON-NLS-1$

  String PRAEFIX_EML = "eml"; //$NON-NLS-1$

  // ElectionDefinition
  String ELECTION_IDENTIFIER = "ElectionIdentifier"; //$NON-NLS-1$
  String ATTR_ELECTION_IDENTIFIER_ID = "Id"; //$NON-NLS-1$
  String ELECTION_CATEGORY = "ElectionCategory"; //$NON-NLS-1$
  String ELECTION_SUBCATEGORY = "ElectionSubcategory"; //$NON-NLS-1$
  String ELECTION_DOMAIN = "ElectionDomain"; //$NON-NLS-1$

  // Creation
  String EML_AUTHORITY = "ManagingAuthority"; //$NON-NLS-1$
  String EML_AUTHORITY_IDENTIFIER = "AuthorityIdentifier"; //$NON-NLS-1$
  String ATTR_AUTHORITY_IDENTIFIER_ID = "Id"; //$NON-NLS-1$
  String KR_CREATED_BY_AUTHORITY = "CreatedByAuthority"; //$NON-NLS-1$

  // 230
  String EML = "EML"; //$NON-NLS-1$
  String ATTR_EML_ID = "Id"; //$NON-NLS-1$
  String EML_CONTEST_IDENTIFIER = "ContestIdentifier"; //$NON-NLS-1$
  String RG_RECEIVING_HSB = "ReceivingHSB"; //$NON-NLS-1$
  String RG_RG230A = "RG230a"; //$NON-NLS-1$

  // Attribute values
  String ATTR_VAL_EML_ID_510a = "510a"; //$NON-NLS-1$
  String ATTR_VAL_EML_ID_510b = "510b"; //$NON-NLS-1$
  String ATTR_VAL_EML_ID_510c = "510c"; //$NON-NLS-1$
  String ATTR_VAL_EML_ID_510d = "510d"; //$NON-NLS-1$

  String EML_TYP = "Type"; //$NON-NLS-1$
  // 510

  // Polling station prefix and keywords
  String PREFIX_STEMBUREAU = "Stembureau "; //$NON-NLS-1$
  String KEYWORD_POSTAL_CODE = "postcode: "; //$NON-NLS-1$

}
