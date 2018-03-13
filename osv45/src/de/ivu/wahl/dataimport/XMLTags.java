/*
 * Created on 10.02.2004
 * 
 * Konstanten zur Bezeichnung der XML-Tags und Attribute in Import- und Exportdateien 
 */
package de.ivu.wahl.dataimport;

import nu.xom.XPathContext;

/**
 * @author tst
 */
public interface XMLTags {

  public String SCHEMA_ELECTIONDEFINTION = "http://www.kiesraad.nl/electiondefinition electiondefinition.xsd"; //$NON-NLS-1$
  public String SCHEMA_EML110B = "urn:oasis:names:tc:evs:schema:eml 110-electionevent-kiesraad-strict.xsd"; //$NON-NLS-1$
  public String SCHEMA_EML110A = "urn:oasis:names:tc:evs:schema:eml 110a-electionevent-kiesraad-strict.xsd http://www.kiesraad.nl/extensions kiesraad-eml-extensions.xsd"; //$NON-NLS-1$
  public String SCHEMA_EML230 = "urn:oasis:names:tc:evs:schema:eml 230-candidatelist-kiesraad-strict.xsd http://www.kiesraad.nl/extensions kiesraad-eml-extensions.xsd http://www.kiesraad.nl/reportgenerator reportgenerator-eml-extensions.xsd"; //$NON-NLS-1$
  public String SCHEMA_EML510 = "urn:oasis:names:tc:evs:schema:eml 510-count-kiesraad-strict.xsd http://www.kiesraad.nl/extensions kiesraad-eml-extensions.xsd http://www.kiesraad.nl/reportgenerator reportgenerator-eml-extensions.xsd"; //$NON-NLS-1$
  public String SCHEMA_EML520 = "urn:oasis:names:tc:evs:schema:eml 520-result-kiesraad-strict.xsd http://www.kiesraad.nl/extensions kiesraad-eml-extensions.xsd http://www.kiesraad.nl/reportgenerator reportgenerator-eml-extensions.xsd"; //$NON-NLS-1$
  public String SCHEMA_EML630 = "urn:oasis:names:tc:evs:schema:eml 630-optionslist-kiesraad-strict.xsd http://www.kiesraad.nl/extensions kiesraad-eml-extensions.xsd"; //$NON-NLS-1$
  public String SCHEMA_RG = "http://www.kiesraad.nl/reportgenerator reportgenerator-eml-extensions.xsd"; //$NON-NLS-1$

  public String NS_XSI = "http://www.w3.org/2001/XMLSchema-instance"; //$NON-NLS-1$
  public String NS_EML = "urn:oasis:names:tc:evs:schema:eml"; //$NON-NLS-1$
  public String NS_DS = "http://www.w3.org/2000/09/xmldsig#"; //$NON-NLS-1$
  public String NS_RG = "http://www.kiesraad.nl/reportgenerator"; //$NON-NLS-1$
  public String NS_KR = "http://www.kiesraad.nl/extensions"; //$NON-NLS-1$
  public String NS_ED = "http://www.kiesraad.nl/electiondefinition"; //$NON-NLS-1$
  public String NS_XNL = "urn:oasis:names:tc:ciq:xsdschema:xNL:2.0"; //$NON-NLS-1$
  public String NS_XAL = "urn:oasis:names:tc:ciq:xsdschema:xAL:2.0"; //$NON-NLS-1$

  public String NS_PREFIX_XSI = "xsi"; //$NON-NLS-1$
  public String NS_PREFIX_EML = "eml"; //$NON-NLS-1$
  public String NS_PREFIX_DS = "ds"; //$NON-NLS-1$
  public String NS_PREFIX_RG = "rg"; //$NON-NLS-1$
  public String NS_PREFIX_KR = "kr"; //$NON-NLS-1$
  public String NS_PREFIX_ED = "ed"; //$NON-NLS-1$
  public String NS_PREFIX_XNL = "xnl"; //$NON-NLS-1$
  public String NS_PREFIX_XAL = "xal"; //$NON-NLS-1$

  public String ATTR_SCHEMA = "schemaLocation"; //$NON-NLS-1$

  public String PRAEFIX_EML = "eml"; //$NON-NLS-1$
  XPathContext CONTEXT_EML = new XPathContext(PRAEFIX_EML, NS_EML);

  public String NOT_APPLICABLE = "n/a"; //$NON-NLS-1$

  // ElectionDefinition
  public String WAHLDEFINITION = "ElectionDefinition"; //$NON-NLS-1$
  public String WAHL_IDENTIFIER = "ElectionIdentifier"; //$NON-NLS-1$
  public String EML_EVENT_IDENTIFIER = "EventIdentifier"; //$NON-NLS-1$
  public String WAHL_ART = "ElectionCategory"; //$NON-NLS-1$
  public String WAHL_KATEGORIE = "ElectionSubcategory"; //$NON-NLS-1$
  public String KR_WAHL_DATUM = "ElectionDate"; //$NON-NLS-1$
  public String KR_ELECTION_DOMAIN = "ElectionDomain"; //$NON-NLS-1$
  public String ELECTION_DOMAIN_ATTRIBUT_ID = "Id"; //$NON-NLS-1$
  public String WAHL_ATTRIBUT_WAHLID = "Id"; //$NON-NLS-1$
  public String ANZAHL_SITZE = "NumberOfSeats"; //$NON-NLS-1$
  public String VORRANGSCHWELLE = "PreferenceThreshold"; //$NON-NLS-1$

  public String ED_ELECTION_TREE = "ElectionTree"; //$NON-NLS-1$
  public String ED_REGION = "Region"; //$NON-NLS-1$
  public String ED_REGION_NAME = "RegionName"; //$NON-NLS-1$
  public String ATTR_ED_REGION_CATEGORY = "RegionCategory"; //$NON-NLS-1$
  public String ATTR_ED_REGION_NUMBER = "RegionNumber"; //$NON-NLS-1$
  public String GEBIET_ATTRIBUT_NR_ROEMISCH = "RomanNumerals"; //$NON-NLS-1$
  public String GEBIET_ATTRIBUT_UEGGEBIETSART = "SuperiorRegionCategory"; //$NON-NLS-1$
  public String GEBIET_ATTRIBUT_UEGGEBIETSNUMMER = "SuperiorRegionNumber"; //$NON-NLS-1$
  public String ED_COMMITTEE = "Committee"; //$NON-NLS-1$
  public String ED_ATTR_COMMITTEE_CATEGORY = "CommitteeCategory"; //$NON-NLS-1$
  public String ED_ATTR_COMMITTEE_NAME = "CommitteeName"; //$NON-NLS-1$

  // 230
  public String EML = "EML"; //$NON-NLS-1$
  public String ATTR_EML_ID = "Id"; //$NON-NLS-1$
  // Attribute values
  public String ATTR_VAL_EML_ID_110a = "110a"; //$NON-NLS-1$
  public String ATTR_VAL_EML_ID_110b = "110b"; //$NON-NLS-1$
  public String ATTR_VAL_EML_ID_230b = "230b"; //$NON-NLS-1$
  public String ATTR_VAL_EML_ID_230c = "230c"; //$NON-NLS-1$
  public String ATTR_VAL_EML_ID_510a = "510a"; //$NON-NLS-1$
  public String ATTR_VAL_EML_ID_510b = "510b"; //$NON-NLS-1$
  public String ATTR_VAL_EML_ID_510c = "510c"; //$NON-NLS-1$
  public String ATTR_VAL_EML_ID_510d = "510d"; //$NON-NLS-1$
  public String ATTR_VAL_EML_ID_520 = "520"; //$NON-NLS-1$
  public String ATTR_VAL_EML_ID_630 = "630"; //$NON-NLS-1$
  public String EML_WAHL = "Election"; //$NON-NLS-1$
  public String EML_WAHL_IDENTIFIER = "ElectionIdentifier"; //$NON-NLS-1$
  public String EML_WAHL_NAME = "ElectionName"; //$NON-NLS-1$
  public String EML_CONTESTS = "Contests"; //$NON-NLS-1$
  public String EML_CONTEST = "Contest"; //$NON-NLS-1$
  public String EML_CONTEST_IDENTIFIER = "ContestIdentifier"; //$NON-NLS-1$
  public String EML_ATTR_CONTEST_IDENTIFIER = "Id"; //$NON-NLS-1$
  public String ATTR_VAL_CONTEST_ID_GEEN = "geen"; //$NON-NLS-1$
  public String ATTR_VAL_CONTEST_ID_ALLE = "alle"; //$NON-NLS-1$
  public String EML_CONTEST_NAME = "ContestName"; //$NON-NLS-1$
  public String EML_LISTEN = "Affiliation"; //$NON-NLS-1$
  public String EML_LISTEN_IDENTIFIER = "AffiliationIdentifier"; //$NON-NLS-1$
  public String EML_GRUPPEN_NAME = "RegisteredName"; //$NON-NLS-1$
  public String EML_TYP = "Type"; //$NON-NLS-1$
  public String KR_LISTENDATEN = "ListData"; //$NON-NLS-1$
  public String KR_LISTEN_DATUM = "ListDate"; //$NON-NLS-1$
  public String KR_NOMINIERUNGS_DATUM = "NominationDate"; //$NON-NLS-1$
  public String ATTR_GESCHLECHT_SICHTBAR = "PublishGender"; //$NON-NLS-1$
  public String ATTR_PUBLICATION_LANGUAGE = "PublicationLanguage"; //$NON-NLS-1$
  public String ATTR_IDENTISCHE_LISTE = "BelongsToSet"; //$NON-NLS-1$
  public String ATTR_LISTENKOMBINATION = "BelongsToCombination"; //$NON-NLS-1$
  public String LISTEN_TYP_KOMBINATION = "lijstencombinatie"; //$NON-NLS-1$
  public String LISTEN_TYP_GRUPPE = "lijstengroep"; //$NON-NLS-1$
  public String LISTEN_TYP_UNABHAENGIG = "op zichzelf staande lijst"; //$NON-NLS-1$
  public String LISTEN_TYP_IDENTISCH = "stel gelijkleidende lijsten"; //$NON-NLS-1$
  public String ATTR_SHORTCODE = "ShortCode"; //$NON-NLS-1$
  public String KANDIDATEN = "CandidateList"; //$NON-NLS-1$
  public String EML_CANDIDATE = "Candidate"; //$NON-NLS-1$
  // Name
  public String EML_CANDIDATE_IDENTIFIER = "CandidateIdentifier"; //$NON-NLS-1$
  public String EML_CANDIDATE_NAME = "CandidateFullName"; //$NON-NLS-1$
  public String EML_CANDIDATE_DATE_OF_BIRTH = "DateOfBirth"; //$NON-NLS-1$
  public String EML_GENDER = "Gender"; //$NON-NLS-1$
  public String PERSONENDATEN_NAME = "PersonName"; //$NON-NLS-1$
  public String ATTR_NAME_TYPE = "NameType"; //$NON-NLS-1$
  public String ATTR_VAL_NAME_TYPE_INITIAL = "Initials"; //$NON-NLS-1$
  public String NAME_TITEL = "Title"; //$NON-NLS-1$
  public String NAME_PRAEFIX = "NamePrefix"; //$NON-NLS-1$
  public String NAME_VORNAME = "FirstName"; //$NON-NLS-1$
  public String NAME_NAMENSZUSATZ = "NameLine"; //$NON-NLS-1$
  public String NAME_NACHNAME = "LastName"; //$NON-NLS-1$
  public String NAME_GENERATION_ID = "GenerationIdentifier"; //$NON-NLS-1$
  // Adresse
  public String PERSONENDATEN_ADRESSE = "QualifyingAddress"; //$NON-NLS-1$
  public String PERSONENDATEN_KONTAKT = "Contact"; //$NON-NLS-1$
  public String PERSONENDATEN_KONTAKTADRESSE = "MailingAddress"; //$NON-NLS-1$
  public String PERSONENDATEN_ADRESSE_ORT = "Locality"; //$NON-NLS-1$
  public String PERSONENDATEN_ADRESSE_LAND = "Country"; //$NON-NLS-1$
  public String ADRESSE_LAND_ID = "CountryNameCode"; //$NON-NLS-1$
  public String ADRESSE_PLZ = "PostalCode"; //$NON-NLS-1$
  public String PLZ_NUMMER = "PostalCodeNumber"; //$NON-NLS-1$
  public String ADRESSE_WOHNORT = "LocalityName"; //$NON-NLS-1$
  public String ADRESSE_STRASSE = "AddressLine"; //$NON-NLS-1$
  public String KANDIDAT_AGENT = "Agent"; //$NON-NLS-1$
  public String AGENT_IDENTIFIER = "AgentIdentifier"; //$NON-NLS-1$
  public String AGENT_NAME = "AgentName"; //$NON-NLS-1$
  public String AGENT_KONTAKT = "Contact"; //$NON-NLS-1$
  public String AGENT_ADRESSE = "MailingAddress"; //$NON-NLS-1$
  public String AGENT_LIVING_ADRESSE = "LivingAddress"; //$NON-NLS-1$
  // 510
  public String EML_ELECTION = "Election"; //$NON-NLS-1$
  public String EML_REPORTING_UNIT = "ReportingUnit"; //$NON-NLS-1$
  public String EML_REPORTING_UNIT_VOTES = "ReportingUnitVotes"; //$NON-NLS-1$
  public String EML_REPORTING_UNIT_IDENTIFIER = "ReportingUnitIdentifier"; //$NON-NLS-1$
  public String EML_TOTAL_VOTES = "TotalVotes"; //$NON-NLS-1$
  public String EML_SELECTION = "Selection"; //$NON-NLS-1$
  public String EML_CAST = "Cast"; //$NON-NLS-1$
  public String EML_COUNT = "Count"; //$NON-NLS-1$
  public String EML_TOTAL_COUNTED = "TotalCounted"; //$NON-NLS-1$
  public String EML_VALID_VOTES = "ValidVotes"; //$NON-NLS-1$
  public String EML_REJECTED = "RejectedVotes"; //$NON-NLS-1$
  public String EML_ATTR_INVALID_REASON = "ReasonCode"; //$NON-NLS-1$
  public String ATTR_VAL_INVALID_INVALID = "ongeldig"; //$NON-NLS-1$
  public String ATTR_VAL_INVALID_BLANCO = "blanco"; //$NON-NLS-1$
  public String EML_UNCOUNTED = "UncountedVotes"; //$NON-NLS-1$
  public String ATTR_VAL_VOTERS_WITH_ELECTION_NOTICES = "geldige stempassen"; //$NON-NLS-1$
  public String ATTR_VAL_PROXY_VOTERS = "geldige volmachtbewijzen"; //$NON-NLS-1$
  public String ATTR_VAL_VOTERS_WITH_POLLING_CARDS = "geldige kiezerspassen"; //$NON-NLS-1$
  public String ATTR_VAL_ADMITTED_VOTERS = "toegelaten kiezers"; //$NON-NLS-1$

  public String ATTR_VAL_MORE_VALID_VOTES_THAN_ADMITTED_VOTERS = "meer getelde stembiljetten"; //$NON-NLS-1$
  public String ATTR_VAL_LESS_VALID_VOTES_THAN_ADMITTED_VOTERS = "minder getelde stembiljetten"; //$NON-NLS-1$
  public String ATTR_VAL_BALLOT_PAPER_NOT_RETURNED = "meegenomen stembiljetten"; //$NON-NLS-1$
  public String ATTR_VAL_TOO_FEW_BALLOT_PAPER_ISSUED = "te weinig uitgereikte stembiljetten"; //$NON-NLS-1$
  public String ATTR_VAL_TOO_MANY_BALLOT_PAPER_ISSUED = "te veel uitgereikte stembiljetten"; //$NON-NLS-1$
  public String ATTR_VAL_EMPTY_POSTAL_VOTES = "geen briefstembiljetten"; //$NON-NLS-1$
  public String ATTR_VAL_POSTAL_VOTES_WITH_MULTIPLE_BALLOT_PAPERS = "te veel briefstembiljetten"; //$NON-NLS-1$
  public String ATTR_VAL_BALLOT_PAPERS_LOST = "kwijtgeraakte stembiljetten"; //$NON-NLS-1$
  public String ATTR_VAL_NO_EXPLANATION = "geen verklaring"; //$NON-NLS-1$
  public String ATTR_VAL_OTHER_EXPLANATIONS = "andere verklaring"; //$NON-NLS-1$

  // Creation

  public String EML_TRANSACTION = "TransactionId"; //$NON-NLS-1$
  public String EML_AUTHORITY = "ManagingAuthority"; //$NON-NLS-1$
  public String EML_AUTHORITY_IDENTIFIER = "AuthorityIdentifier"; //$NON-NLS-1$
  public String EML_AUTHORITY_IDENTIFIER_P3_PREFIX = "Centraal stembureau "; //$NON-NLS-1$
  public String EML_ATTR_AUTHORITY_IDENTIFIER = "Id"; //$NON-NLS-1$
  public String EML_AUTHORITY_ADDRESS = "AuthorityAddress"; //$NON-NLS-1$
  public String KR_CREATED_BY_AUTHORITY = "CreatedByAuthority"; //$NON-NLS-1$
  public String DS_CANONIZATION_METHOD = "CanonicalizationMethod"; //$NON-NLS-1$
  public String ATTR_ALGORITHM = "Algorithm"; //$NON-NLS-1$
  public String ATTR_VAL_ALGORITHM = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments"; //$NON-NLS-1$
  public String KR_CREATION_DATE = "CreationDateTime"; //$NON-NLS-1$
  public String EML_RESULT = "Result"; //$NON-NLS-1$
  public String EML_ELECTED = "Elected"; //$NON-NLS-1$
  public String EML_PREF_ELECTED = "Ranking"; //$NON-NLS-1$

  // Polling station
  public String EML_ELECTION_EVENT = "ElectionEvent"; //$NON-NLS-1$
  public String EML_POLLING_PLACE = "PollingPlace"; //$NON-NLS-1$
  public String ATTR_POLLING_PLACE_CHANNEL = "Channel"; //$NON-NLS-1$
  public String ATTR_VAL_CHANNEL_POLLING = "polling"; //$NON-NLS-1$
  public String ATTR_VAL_CHANNEL_POSTAL = "postal"; //$NON-NLS-1$
  public String EML_PHYSICAL_LOCATION = "PhysicalLocation"; //$NON-NLS-1$
  public String EML_VOTING_METHOD = "VotingMethod"; //$NON-NLS-1$
  public String EML_MAX_VOTES = "MaxVotes"; //$NON-NLS-1$
  public String EML_ADDRESS = "Address"; //$NON-NLS-1$
  public String EML_LOCALITY = "Locality"; //$NON-NLS-1$
  public String EML_LOCALITY_NAME = "LocalityName"; //$NON-NLS-1$
  public String EML_POLLING_STATION = "PollingStation"; //$NON-NLS-1$
  public String EML_DATE = "Date"; //$NON-NLS-1$
  public String ATTR_DATE_TYPE = "Type"; //$NON-NLS-1$
  public String EML_SINGLE_DATE = "SingleDate"; //$NON-NLS-1$
  public int MUNICIPALITY_WITH_POSTAL_VOTE_OFFICE = 518;
  public String KEYWORD_POSTAL_CODE = "postcode: "; //$NON-NLS-1$

  // 630
  public String EML_OPTIONS_LIST = "OptionsList"; //$NON-NLS-1$
  public String EML_PROPOSAL = "Proposal"; //$NON-NLS-1$
  public String EML_PROPOSAL_IDENTIFIER = "ProposalIdentifier"; //$NON-NLS-1$
  public String EML_PROPOSAL_NAME = "ProposalName"; //$NON-NLS-1$
  public String EML_OPTIONS = "Options"; //$NON-NLS-1$
  public String EML_REFERENDUM_OPTION_IDENTIFIER = "ReferendumOptionIdentifier"; //$NON-NLS-1$
}
