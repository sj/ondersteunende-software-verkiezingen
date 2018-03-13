/*
 * Copyright (c) 2002-11 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import static de.ivu.wahl.dataimport.XMLTags.ATTR_EML_ID;
import static de.ivu.wahl.dataimport.XMLTags.ATTR_PUBLICATION_LANGUAGE;
import static de.ivu.wahl.dataimport.XMLTags.ATTR_SHORTCODE;
import static de.ivu.wahl.dataimport.XMLTags.KR_CREATED_BY_AUTHORITY;
import static de.ivu.wahl.dataimport.XMLTags.NS_EML;
import static de.ivu.wahl.dataimport.XMLTags.NS_KR;
import static de.ivu.wahl.dataimport.XMLTags.NS_RG;
import static de.ivu.wahl.dataimport.XMLTags.NS_XAL;
import static de.ivu.wahl.dataimport.XMLTags.NS_XNL;
import static de.ivu.wahl.export.XMLTags.ATTR_SCHEMA_VERSION;
import static de.ivu.wahl.export.XMLTags.RG_AFFILIATION_VOTES;
import static de.ivu.wahl.export.XMLTags.RG_NOTES;
import static de.ivu.wahl.export.XMLTags.RG_OBJECTIONS;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jboss.annotation.ejb.TransactionTimeout;

import nu.xom.Attribute;
import nu.xom.Comment;
import nu.xom.Document;
import nu.xom.Element;

import de.ivu.ejb.EJBUtil;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.SystemInfo;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.WahlStatelessSessionBeanBase;
import de.ivu.wahl.admin.AdminHandling;
import de.ivu.wahl.admin.PropertyHandling;
import de.ivu.wahl.auswertung.erg.CandidateVotesPerRegion;
import de.ivu.wahl.auswertung.erg.PartyWithCandidates;
import de.ivu.wahl.auswertung.erg.ResultSummary;
import de.ivu.wahl.client.beans.ApplicationBean;
import de.ivu.wahl.dataimport.GruppeAllgemeinXmlAdapter;
import de.ivu.wahl.dataimport.XMLTags;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.AuthorityLevel;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.Gebietsart;
import de.ivu.wahl.modell.GruppeKonstanten;
import de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein;
import de.ivu.wahl.modell.PersonendatenKonstanten;
import de.ivu.wahl.modell.PublicationLanguage;
import de.ivu.wahl.modell.WahlModel;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifisch;
import de.ivu.wahl.modell.ejb.GruppeHome;
import de.ivu.wahl.modell.ejb.KonfliktHome;
import de.ivu.wahl.modell.ejb.Liste;
import de.ivu.wahl.modell.ejb.Listenkandidatur;
import de.ivu.wahl.modell.ejb.Personendaten;
import de.ivu.wahl.modell.ejb.Stimmergebnis;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;
import de.ivu.wahl.wus.reportgen.EMLMessageType;
import de.ivu.wahl.wus.reportgen.ReportGenerator;

/**
 * Wahlabwicklungssystem, Methoden fuer das Exportieren.
 * 
 * @author klie@ivu.de mur@ivu.de cos@ivu.de ugo@ivu.de
 */
@Stateless
@Local(ExportHandling.class)
public class ExportHandlingBean extends WahlStatelessSessionBeanBase implements ExportHandling {

  private static final String ZIP_FILE_EXTENSION = ".zip"; //$NON-NLS-1$
  private static final long serialVersionUID = -1698028693845863132L;
  private static final Logger LOGGER = Logger.getLogger(ExportHandlingBean.class);

  static final int ALL_VOTES = 0;
  static final int POSTAL_VOTES = 1;
  static final int NORMAL_VOTES = 2;

  private static final int TRANSACTION_TIMEOUT_MINUTES = 30;

  /**
   * Creates the EML 110b document (list of polling stations)
   */
  @Override
  public Document createEML110() {
    try {
      WahlInfo.getWahlInfo().getWahl().readLock();
      WahlInfo wahlInfo = WahlInfo.getWahlInfo();
      String votingMethod = "SPV"; //$NON-NLS-1$
      Gebiet region = wahlInfo.getWahl().getWurzelgebiet();
      Document emlDocument = createEMLDocument(XMLTags.ATTR_VAL_EML_ID_110b,
          XMLTags.SCHEMA_EML110B,
          region,
          false);
      String createdByOsv = Messages.applyPattern(ReportGenerator.CREATED_BY_OSV_PROGRAM_VERSION,
          EJBUtil.getProgramSpecificAffix(),
          ApplicationBean.getVersionString());
      emlDocument.getRootElement().insertChild(new Comment(createdByOsv), 0);
      List<Gebiet> pollingStations = new ArrayList<Gebiet>(getGebietHome()
          .findAllByUebergeordnetesGebietSortByNummer(region.getID_Gebiet()));
      // Node "ElectionEvent"
      Element electionEvent = new Element(XMLTags.EML_ELECTION_EVENT, NS_EML);
      // Child "EventIdentifier"
      electionEvent.appendChild(XMLHelper.createElement(XMLTags.EML_EVENT_IDENTIFIER, NS_EML));
      // Node "Election"
      Element election = new Element(XMLTags.EML_ELECTION, NS_EML);
      election.appendChild(createElectionIdentifierElement());
      // Node "Contest"
      Element contest = new Element(XMLTags.EML_CONTEST, NS_EML);
      contest.appendChild(createContestIdentifier(region, false));
      // Node "ReportingUnit"
      Element reportingUnit = XMLHelper.createElement(XMLTags.EML_REPORTING_UNIT, NS_EML);
      reportingUnit.appendChild(createReportingUnitIdentifier(region, false, SystemInfo
          .getSystemInfo().getWahlEbene()));
      contest.appendChild(reportingUnit);
      // Child "VotingMethod"
      contest.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_VOTING_METHOD,
          NS_EML,
          votingMethod));
      // Child "MaxVotes"
      int maxVotes = region.getWahlberechtigte() > 0 ? region.getWahlberechtigte() : 1;
      contest.appendChild(de.ivu.wahl.export.XMLHelper
          .createElementWithValue(XMLTags.EML_MAX_VOTES, NS_EML, maxVotes));
      // iterate over pollingStations
      for (Gebiet pollingStation : pollingStations) {
        // Node "PollingPlace"
        String votingChannelValue = XMLTags.ATTR_VAL_CHANNEL_POLLING;
        if (pollingStation.isPostalvote()) {
          votingChannelValue = XMLTags.ATTR_VAL_CHANNEL_POSTAL;
        }
        Element pollingPlace = XMLHelper.createElementWithAttribute(XMLTags.EML_POLLING_PLACE,
            NS_EML,
            XMLTags.ATTR_POLLING_PLACE_CHANNEL,
            votingChannelValue);
        // Node "PhysicalLocation"
        Element physicalLocation = new Element(XMLTags.EML_PHYSICAL_LOCATION, NS_EML);
        // Node "Address"
        Element address = new Element(XMLTags.EML_ADDRESS, NS_EML);
        Element locality = new Element(XMLTags.EML_LOCALITY, NS_EML);
        locality.appendChild(de.ivu.wahl.export.XMLHelper
            .createElementWithValue(XMLTags.EML_LOCALITY_NAME,
                XMLTags.NS_XAL,
                pollingStation.getName()));
        if (!StringUtils.isEmpty(pollingStation.getZipcode())) {
          Element postalCode = de.ivu.wahl.export.XMLHelper.createElement(XMLTags.ADRESSE_PLZ,
              NS_XAL);
          postalCode.appendChild(de.ivu.wahl.export.XMLHelper
              .createElementWithValue(XMLTags.PLZ_NUMMER,
                  XMLTags.NS_XAL,
                  pollingStation.getZipcode()));
          locality.appendChild(postalCode);
        }
        address.appendChild(locality);
        physicalLocation.appendChild(address);
        // Node "PollingStation"
        Element pollingStationElement = XMLHelper
            .createElementWithValue(XMLTags.EML_POLLING_STATION,
                NS_EML,
                pollingStation.getWahlberechtigte());
        pollingStationElement.addAttribute(new Attribute(XMLTags.ATTR_EML_ID, String
            .valueOf(pollingStation.getNummer())));
        physicalLocation.appendChild(pollingStationElement);
        pollingPlace.appendChild(physicalLocation);
        contest.appendChild(pollingPlace);
      }
      election.appendChild(contest);
      electionEvent.appendChild(election);
      emlDocument.getRootElement().appendChild(electionEvent);
      return emlDocument;
    } catch (FinderException e) {
      throw new EJBException(e);
    }
  }

  public static String appendZipcode(final String name, final String zipcode) {
    String result = name;
    if (!StringUtils.isEmpty(zipcode)) {
      result += " (" + XMLTags.KEYWORD_POSTAL_CODE + zipcode + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    return result;
  }

  @Override
  @TransactionTimeout(TRANSACTION_TIMEOUT_MINUTES * 60)
  public Document createEML520(String id_Ergebniseingang,
      boolean forCandidateLetters,
      boolean forProtocolAppendix) throws EJBException, ImportException {
    WahlInfo.getWahlInfo().getWahl().readLock();
    Gebiet region = WahlInfo.getWahlInfo().getWahl().getWurzelgebiet();
    Document emlDocument = createEMLDocument(XMLTags.ATTR_VAL_EML_ID_520,
        XMLTags.SCHEMA_EML520,
        region,
        true);
    Element election = XMLHelper.createElement(XMLTags.EML_WAHL, NS_EML);
    election.appendChild(createElectionIdentifierElement());

    Element contestResult = new EML520Helper(this).createContestResultElement(id_Ergebniseingang,
        region,
        EMLMessageType.EML520);
    election.appendChild(contestResult);

    Element result = XMLHelper.createElement(XMLTags.EML_RESULT, NS_EML);
    result.appendChild(election);

    // append RG elements
    boolean inConflict = WahlInfo.getWahlInfo().getStatus() == WahlModel.STATE_CALCULATION_CONFLICT;
    result.appendChild(new RG520Helper(this, inConflict, id_Ergebniseingang)
        .createReportGeneratorElement520(id_Ergebniseingang,
            forCandidateLetters,
            forProtocolAppendix));

    emlDocument.getRootElement().appendChild(result);
    return emlDocument;
  }

  @Override
  @TransactionTimeout(TRANSACTION_TIMEOUT_MINUTES * 60)
  public Document createEML510(Gebiet gebiet, boolean createRGNodes)
      throws EJBException, ImportException {
    return createEML510(gebiet, createRGNodes, false, false);

  }

  @Override
  @TransactionTimeout(TRANSACTION_TIMEOUT_MINUTES * 60)
  public Document createEmptyEML510(Gebiet gebiet) throws EJBException, ImportException {
    return createEML510(gebiet, false, false, true);
  }

  @Override
  @TransactionTimeout(TRANSACTION_TIMEOUT_MINUTES * 60)
  public Document createEML510(Gebiet gebiet,
      boolean createRGNodes,
      boolean create510dForPSB,
      boolean emptyResults) throws EJBException, ImportException {
    String id = getIdForEml510(gebiet, create510dForPSB);
    EMLMessageType emlType = EMLMessageType.byId(id);
    Document emlDocument = createEMLDocument(id, XMLTags.SCHEMA_EML510, gebiet, createRGNodes);

    Element election = XMLHelper.createElement(XMLTags.EML_WAHL, NS_EML);
    election.appendChild(createElectionIdentifierElement());

    try {
      election.appendChild(new EML510Helper(this).createVotingResultElement(gebiet,
          create510dForPSB,
          emptyResults,
          emlType));
    } catch (FinderException e) {
      throw new EJBException(e);
    }
    Element result = XMLHelper.createElement(XMLTags.EML_COUNT, NS_EML);
    result.appendChild(XMLHelper.createElement(XMLTags.EML_EVENT_IDENTIFIER, NS_EML));
    result.appendChild(election);

    // For total result generate report generator specific subtree
    if (createRGNodes) {
      result.appendChild(createReportGeneratorElement510(gebiet, emlType, emptyResults));
    }
    emlDocument.getRootElement().appendChild(result);
    return emlDocument;
  }

  private String getIdForEml510(Gebiet gebiet, boolean create510dForPSB) {
    WahlInfo.getWahlInfo().getWahl().readLock();
    String id;
    boolean isWurzelGebiet = gebiet.getID_UebergeordnetesGebiet() == null;
    int level = SystemInfo.getSystemInfo().getWahlEbene();
    switch (level) {
      case GebietModel.EBENE_PSB :
        if (create510dForPSB) {
          id = isWurzelGebiet ? XMLTags.ATTR_VAL_EML_ID_510d : XMLTags.ATTR_VAL_EML_ID_510c;
        } else {
          id = isWurzelGebiet ? XMLTags.ATTR_VAL_EML_ID_510b : XMLTags.ATTR_VAL_EML_ID_510a;
        }
        break;
      case GebietModel.EBENE_HSB :
        id = isWurzelGebiet ? XMLTags.ATTR_VAL_EML_ID_510c : XMLTags.ATTR_VAL_EML_ID_510b;
        break;
      case GebietModel.EBENE_CSB :
        id = isWurzelGebiet ? XMLTags.ATTR_VAL_EML_ID_510d : XMLTags.ATTR_VAL_EML_ID_510c;
        break;
      default :
        throw new RuntimeException("What's that level??? " + level); //$NON-NLS-1$
    }
    return id;
  }

  /**
   * Create general eml dokument
   * 
   * @param id
   * @param schema
   * @param gebiet
   * @param withRGNodes
   * @param level
   * @return
   */
  private Document createEMLDocument(String id, String schema, Gebiet gebiet, boolean withRGNodes) {
    WahlInfo.getWahlInfo().getWahl().readLock();
    int level = SystemInfo.getSystemInfo().getWahlEbene();
    Element root = XMLHelper.createElement(XMLTags.EML, XMLTags.NS_EML);
    root.addAttribute(new Attribute(XMLTags.ATTR_EML_ID, id));
    root.addAttribute(new Attribute(ATTR_SCHEMA_VERSION, "5")); //$NON-NLS-1$
    String schemaWithRG = withRGNodes ? schema + " " + XMLTags.SCHEMA_RG : schema; //$NON-NLS-1$
    Attribute schemaAttr = new Attribute(XMLTags.ATTR_SCHEMA, schemaWithRG);
    schemaAttr.setNamespace(XMLTags.NS_PREFIX_XSI, XMLTags.NS_XSI);
    root.addAttribute(schemaAttr);
    addNamespacesEML(root, id);
    // Fix value : 1
    root.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_TRANSACTION, NS_EML, "1")); //$NON-NLS-1$
    Element authority = createAuthorityElement(level, gebiet, id);
    root.appendChild(authority);
    // Creation time
    root.appendChild(XMLHelper.createElementWithValue(XMLTags.KR_CREATION_DATE,
        XMLTags.NS_KR,
        XMLHelper.createTimeString(System.currentTimeMillis())));
    // Can. method
    root.appendChild(XMLHelper.createElementWithAttribute(XMLTags.DS_CANONIZATION_METHOD,
        XMLTags.NS_DS,
        XMLTags.ATTR_ALGORITHM,
        XMLTags.ATTR_VAL_ALGORITHM));
    return new Document(root);
  }

  /**
   * Create the <ManagingAuthority> element
   * 
   * @param level
   * @param gebiet
   * @param emlId
   * @return the xml element
   */
  private Element createAuthorityElement(int level, Gebiet gebiet, String emlId) {
    WahlInfo wahlInfo = WahlInfo.getWahlInfo();
    // Authority
    Element authority = XMLHelper.createElement(XMLTags.EML_AUTHORITY, NS_EML);
    int targetLevel;
    boolean forRootRegion = gebiet.getID_Gebiet().equals(wahlInfo.getID_Wurzelgebiet());
    if (!forRootRegion) {
      targetLevel = level + 1;
    } else {
      targetLevel = level;
    }
    Element identifier = XMLHelper.createElementWithValue(XMLTags.EML_AUTHORITY_IDENTIFIER,
        NS_EML,
        getAuthorityName(targetLevel, gebiet));
    identifier.addAttribute(new Attribute(ATTR_EML_ID, getRegionIdentifier(gebiet,
        false,
        targetLevel)));
    authority.appendChild(identifier);
    authority.appendChild(XMLHelper.createElement(XMLTags.EML_AUTHORITY_ADDRESS, NS_EML));
    // if result is not for the rootregion, add createdBy
    // TODO: strict schema for 110b
    if (!forRootRegion && !XMLTags.ATTR_VAL_EML_ID_110b.equals(emlId)) {
      Gebiet wurzelgebiet = wahlInfo.getWahl().getWurzelgebiet();
      String id = getRegionIdentifier(wurzelgebiet, false, level);
      Element createdBy = XMLHelper.createElementWithAttribute(KR_CREATED_BY_AUTHORITY,
          NS_KR,
          ATTR_EML_ID,
          id);
      createdBy.appendChild(getAuthorityName(level, wurzelgebiet));
      authority.appendChild(createdBy);
    }
    return authority;
  }

  /**
   * Build the authorities name: if given in election definition, take that
   * 
   * @param level authority
   * @param region
   * @return
   */
  private String getAuthorityName(int level, Gebiet region) {
    // Check id there is one defined in election definition
    String levelName = getAdminHandling().getProperty(AuthorityLevel.byId(level).getShortName()
        + region.getNummer());
    if (levelName == null) {
      // no level needed, see OSV-440
      levelName = region.getName();
    }
    return levelName;
  }

  /**
   * Add namespace declarations for xml report
   * 
   * @param rootElement
   * @param emlId
   */
  private void addNamespacesEML(Element rootElement, String emlId) {
    rootElement.addNamespaceDeclaration(XMLTags.NS_PREFIX_XSI, XMLTags.NS_XSI);
    rootElement.addNamespaceDeclaration(XMLTags.NS_PREFIX_KR, XMLTags.NS_KR);
    rootElement.addNamespaceDeclaration(XMLTags.NS_PREFIX_DS, XMLTags.NS_DS);
    rootElement.addNamespaceDeclaration(XMLTags.NS_PREFIX_XAL, XMLTags.NS_XAL);
    if (!XMLTags.ATTR_VAL_EML_ID_110b.equals(emlId)) {
      rootElement.addNamespaceDeclaration(XMLTags.NS_PREFIX_XNL, XMLTags.NS_XNL);
      rootElement.addNamespaceDeclaration(XMLTags.NS_PREFIX_RG, XMLTags.NS_RG);
    }
  }

  /**
   * Create ElectionIdentifier element
   */
  public Element createElectionIdentifierElement() {
    WahlInfo wahlInfo = WahlInfo.getWahlInfo();
    Element electionIdentifier = XMLHelper.createElementWithAttribute(XMLTags.EML_WAHL_IDENTIFIER,
        NS_EML,
        XMLTags.ATTR_EML_ID,
        wahlInfo.getID_Wahl());
    electionIdentifier.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_WAHL_NAME,
        NS_EML,
        wahlInfo.getWahlName()));
    electionIdentifier.appendChild(XMLHelper.createElementWithValue(XMLTags.WAHL_ART,
        NS_EML,
        wahlInfo.getElectionCategory().getEmlRepresentation()));
    electionIdentifier.appendChild(XMLHelper.createElementWithValue(XMLTags.WAHL_KATEGORIE,
        NS_KR,
        wahlInfo.getElectionSubcategory().getEmlRepresentation()));
    String electionDomain = wahlInfo.getWahl().getElectionDomain();
    if (electionDomain != null) {
      Element electionDomainElement = XMLHelper.createElementWithValue(XMLTags.KR_ELECTION_DOMAIN,
          NS_KR,
          electionDomain);
      electionIdentifier.appendChild(electionDomainElement);
      String electionDomainId = wahlInfo.getWahl().getElectionDomainId();
      if (electionDomainId != null) {
        Attribute idAttribute = new Attribute(XMLTags.ELECTION_DOMAIN_ATTRIBUT_ID, electionDomainId);
        electionDomainElement.addAttribute(idAttribute);
      }
    }
    electionIdentifier.appendChild(XMLHelper.createElementWithValue(XMLTags.KR_WAHL_DATUM,
        NS_KR,
        wahlInfo.getWahltermin()));
    return electionIdentifier;
  }

  /**
   * ContestIdentifier: region identifier for election districts, "alle" for STAAT or PROVINCIE
   * resp. or "geen" otherwise
   * 
   * @param region
   * @param eml510InElectionWithMultipleDistrictsOrAB
   * @return
   */
  Element createContestIdentifier(Gebiet region, boolean eml510InElectionWithMultipleDistrictsOrAB) {
    switch (Gebietsart.byId(region.getGebietsart())) {
      case BUND :
      case LAND :
      case ALGEMEEN_BESTUUR :
        if (region.getGebietCol().size() == 1) {
          // only one Kieskring, use its name and number
          Gebiet kieskring = region.getGebietCol().iterator().next();
          return createContestIdentifier(kieskring);
        } else if (region.getGebietCol().size() == 0
            && ElectionCategory.fromWahlart(region.getWahl().getWahlart()).isEK()) {
          return createContestIdentifier(region.getGebietsnummerAnzeige(), region.getName());
        } else {
          return createContestIdentifier(XMLTags.ATTR_VAL_CONTEST_ID_ALLE, null);
        }
      case WAHLKREIS :
        return createContestIdentifier(region);
      case GEMEINDE :
      case INSELGEMEINDE :
      case STIMMBEZIRK :
        // Only for EML 510a and EML 510b at TK, EP or PS2 or AB elections or NR referendum
        if (eml510InElectionWithMultipleDistrictsOrAB) {
          Gebiet kieskring = getKieskringFrom(region);
          if (kieskring != null) {
            return createContestIdentifier(kieskring);
          } else {
            String id = getAdminHandling().getProperty(Konstanten.KEY_ELECTORAL_DISTRICT_ID);
            if (id != null && !id.isEmpty()) {
              String name = getAdminHandling().getProperty(Konstanten.KEY_ELECTORAL_DISTRICT_NAME);
              return createContestIdentifier(id, name);
            }
          }
        }
    }
    // The default case
    return createContestIdentifier(XMLTags.ATTR_VAL_CONTEST_ID_GEEN, null);
  }

  private Element createContestIdentifier(Gebiet region) {
    return createContestIdentifier(region.getGebietsnummerAnzeige(), region.getName());
  }

  private Element createContestIdentifier(final String id, String name) {
    Element contestIdentifier = XMLHelper
        .createElementWithAttribute(XMLTags.EML_CONTEST_IDENTIFIER, NS_EML, XMLTags.ATTR_EML_ID, id);
    if (name != null) {
      contestIdentifier.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_CONTEST_NAME,
          NS_EML,
          name));
    }
    return contestIdentifier;
  }

  /**
   * Search the region hierarchy up to find a region of GEBIETSART_WAHLKREIS
   */
  private Gebiet getKieskringFrom(Gebiet region) {
    Gebiet next = region.getUebergeordnetesGebiet();
    while (next != null) {
      if (next.getGebietsart() == GebietModel.GEBIETSART_WAHLKREIS) {
        return next;
      } else {
        next = next.getUebergeordnetesGebiet();
      }
    }
    return null;
  }

  // //////// EML 520 specific methods

  /**
   * Create element <AffiliationIdentifier> with subnode <RegisteredName>
   */
  Element createListIdentifierElement(Liste liste) {
    Gruppe gruppe = liste.getGruppe();
    return createListIdentifierElement(gruppe);
  }

  private Element createListIdentifierElementReferendum(Gruppe gruppe) {
    String gruppenschluessel = String.valueOf(gruppe.getSchluessel());
    Element listenidentifier = XMLHelper.createElementWithAttribute(XMLTags.EML_LISTEN_IDENTIFIER,
        NS_EML,
        XMLTags.ATTR_EML_ID,
        gruppenschluessel);
    String optionName = _propertyHandling.getProperty(Konstanten.KEY_REFERENDUM_ANSW
        + gruppenschluessel);
    listenidentifier.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_GRUPPEN_NAME,
        NS_EML,
        optionName));
    return listenidentifier;
  }

  /**
   * Create element <AffiliationIdentifier> with subnode <RegisteredName>
   */
  Element createListIdentifierElement(Gruppe gruppe) {
    String gruppenschluessel = String.valueOf(gruppe.getSchluessel());
    Element listenidentifier = XMLHelper.createElementWithAttribute(XMLTags.EML_LISTEN_IDENTIFIER,
        NS_EML,
        XMLTags.ATTR_EML_ID,
        gruppenschluessel);
    listenidentifier.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_GRUPPEN_NAME,
        NS_EML,
        gruppe.getNameLang()));
    return listenidentifier;
  }

  Element createCandidateElement(Listenkandidatur lk,
      boolean identifierOnly,
      boolean idAndNameOnly,
      boolean withAddress,
      EMLMessageType emlType) {
    return createCandidateElement(lk, identifierOnly, idAndNameOnly, withAddress, null, emlType);
  }

  Element createCandidateElement(Listenkandidatur lk,
      boolean identifierOnly,
      boolean idAndNameOnly,
      boolean withAddress,
      Integer id,
      EMLMessageType emlType) {
    Personendaten daten = lk.getPersonendaten();
    Integer idToUse = id;
    if (idToUse == null || idToUse < 1) {
      idToUse = lk.getListenplatz();
    }
    Element candidate = XMLHelper.createElement(XMLTags.EML_CANDIDATE, NS_EML);
    Element candidateIdentifier = XMLHelper
        .createElementWithAttribute(XMLTags.EML_CANDIDATE_IDENTIFIER,
            NS_EML,
            XMLTags.ATTR_EML_ID,
            String.valueOf(idToUse));
    // for more than one list add shortCode
    if (useShortCode(emlType)) {
      candidateIdentifier.addAttribute(new Attribute(ATTR_SHORTCODE, daten.getID_Personendaten()));
    }
    candidate.appendChild(candidateIdentifier);
    if (identifierOnly) {
      return candidate;
    }
    // candidate name
    Element name = XMLHelper.createElement(XMLTags.EML_CANDIDATE_NAME, NS_EML);
    name.appendChild(createPersonNameElement(daten));
    candidate.appendChild(name);
    if (daten.getGeschlecht() != -1 && lk.getListe().isGeschlechtSichtbar()) {
      String geschlecht = PersonendatenKonstanten.Geschlecht.getExportEML(daten.getGeschlecht());
      candidate.appendChild(XMLHelper
          .createElementWithValue(XMLTags.EML_GENDER, NS_EML, geschlecht));
    }
    if (idAndNameOnly) {
      return candidate;
    }

    appendLivingAddress(daten, candidate);
    if (withAddress) {
      appendContactAddress(daten, candidate, true);
    }

    return candidate;
  }

  private void appendLivingAddress(Personendaten daten, Element candidate) {
    Element adresse = XMLHelper.createElement(XMLTags.PERSONENDATEN_ADRESSE, NS_EML);
    Element ort = XMLHelper.createElement(XMLTags.PERSONENDATEN_ADRESSE_ORT, NS_XAL);
    ort.appendChild(XMLHelper.createElementWithValue(XMLTags.ADRESSE_WOHNORT,
        NS_XAL,
        daten.getWohnort()));
    if (daten.getLand() != null) {
      Element land = XMLHelper.createElement(XMLTags.PERSONENDATEN_ADRESSE_LAND, NS_XAL);
      land.appendChild(XMLHelper.createElementWithValue(XMLTags.ADRESSE_LAND_ID,
          NS_XAL,
          daten.getLand()));
      land.appendChild(ort);
      adresse.appendChild(land);
    } else {
      adresse.appendChild(ort);
    }
    candidate.appendChild(adresse);
  }

  /**
   * @param withAddressDetails is always true currently (03/2011). Maybe we should remove this
   *          parameter.
   */
  void appendContactAddress(Personendaten daten, Element parent, boolean withAddressDetails) {
    Element kontakt = XMLHelper.createElement(XMLTags.PERSONENDATEN_KONTAKT, NS_EML);
    Element adresse = XMLHelper.createElement(XMLTags.PERSONENDATEN_KONTAKTADRESSE, NS_EML);
    Element ort = XMLHelper.createElement(XMLTags.PERSONENDATEN_ADRESSE_ORT, NS_XAL);
    if (withAddressDetails) {
      ort.appendChild(XMLHelper.createElementWithValue(XMLTags.ADRESSE_STRASSE,
          NS_XAL,
          daten.getKontakt_Strasse()));
    }
    ort.appendChild(XMLHelper.createElementWithValue(XMLTags.ADRESSE_WOHNORT,
        NS_XAL,
        daten.getKontakt_Wohnort()));
    if (withAddressDetails) {
      Element plz = XMLHelper.createElement(XMLTags.ADRESSE_PLZ, NS_XAL);
      plz.appendChild(XMLHelper.createElementWithValue(XMLTags.PLZ_NUMMER,
          NS_XAL,
          daten.getKontakt_PLZ()));
      ort.appendChild(plz);
    }
    if (daten.getLand() != null) {
      Element land = XMLHelper.createElement(XMLTags.PERSONENDATEN_ADRESSE_LAND, NS_XAL);
      land.appendChild(XMLHelper.createElementWithValue(XMLTags.ADRESSE_LAND_ID,
          NS_XAL,
          daten.getKontakt_Land()));
      land.appendChild(ort);
      adresse.appendChild(land);
    } else {
      adresse.appendChild(ort);
    }
    kontakt.appendChild(adresse);
    parent.appendChild(kontakt);
  }

  Element createPersonNameElement(Personendaten daten) {
    Element personname = XMLHelper.createElement(XMLTags.PERSONENDATEN_NAME, NS_XNL);
    if (daten.getInitialen() != null) {
      Element initialen = XMLHelper.createElementWithAttribute(XMLTags.NAME_NAMENSZUSATZ,
          NS_XNL,
          XMLTags.ATTR_NAME_TYPE,
          XMLTags.ATTR_VAL_NAME_TYPE_INITIAL);
      initialen.appendChild(daten.getInitialen());
      personname.appendChild(initialen);
    }
    String vorname = daten.getVorname();
    if (vorname != null && vorname.length() > 0) {
      personname.appendChild(XMLHelper
          .createElementWithValue(XMLTags.NAME_VORNAME, NS_XNL, vorname));
    }
    String praefix = daten.getPraefix();
    if (praefix != null && praefix.length() > 0) {
      personname.appendChild(XMLHelper
          .createElementWithValue(XMLTags.NAME_PRAEFIX, NS_XNL, praefix));
    }
    personname.appendChild(XMLHelper.createElementWithValue(XMLTags.NAME_NACHNAME,
        NS_XNL,
        daten.getNachname()));
    return personname;
  }

  // //////// EML 510 specific methods

  Element createReportingUnitIdentifier(Gebiet region, boolean totalResult, int targetLevel) {
    String id = getRegionIdentifier(region, totalResult, targetLevel);
    Element identifier = XMLHelper
        .createElementWithAttribute(XMLTags.EML_REPORTING_UNIT_IDENTIFIER,
            NS_EML,
            XMLTags.ATTR_EML_ID,
            id);
    String name = region.getName();
    if (region.getGebietsart() == GebietModel.GEBIETSART_STIMMBEZIRK) {
      name = appendZipcode(name, region.getZipcode());
    }
    identifier.appendChild(Gebietsart.getGebietsartKlartext(region) + " " + name); //$NON-NLS-1$

    return identifier;
  }

  /**
   * @param region
   * @param isSubregion
   * @return
   */
  private String getRegionIdentifier(Gebiet region, boolean isSubregion, int level) {
    String identifier = String.valueOf(region.getNummer());
    // municipalities with 4 digit numbers
    if (region.getGebietsart() == GebietModel.GEBIETSART_GEMEINDE
        || region.getGebietsart() == GebietModel.GEBIETSART_ORTSTEIL) {
      while (identifier.length() < 4) {
        identifier = "0" + identifier; //$NON-NLS-1$
      }
    }
    // Polling stations are marked with "SB"
    if (region.getGebietsart() == GebietModel.GEBIETSART_STIMMBEZIRK) {
      identifier = AuthorityLevel.EBENE_SB.getShortName() + identifier;
    }
    if (level == GebietModel.EBENE_HSB) {
      identifier = AuthorityLevel.EBENE_HSB.getShortName() + identifier;
    }
    if (level == GebietModel.EBENE_CSB) {
      identifier = AuthorityLevel.EBENE_CSB.getShortName();
    }
    // prepend superior region
    if (isSubregion && level != GebietModel.EBENE_HSB) {
      identifier = getRegionIdentifier(region.getUebergeordnetesGebiet(), false, level - 1) + "::" //$NON-NLS-1$
          + identifier;
    }
    return identifier;
  }

  /**
   * Creates eml510 subtree used for generation of reports
   * 
   * @param emptyResults
   * @return xml subtree
   */
  private Element createReportGeneratorElement510(Gebiet region,
      EMLMessageType emlType,
      boolean emptyResults) {
    return new RG510Helper(this).createReportGeneratorElement510(getVotesHandling(),
        getGebietHome(),
        region,
        emlType,
        emptyResults);
  }

  /**
   * For both the RG510 and RG520 (P22-2 only) element, create the rg:AffiliationVotes element.
   */
  void appendAffiliationVotes(String id_Ergebniseingang,
      Element parent,
      String id_Gebiet,
      boolean isReferendum,
      EMLMessageType emlType) {
    try {
      Collection<Liste> lists = getListeHome().findAllByGebiet(id_Gebiet);
      // Append voting results for each list in a separate node
      for (Liste list : lists) {
        Element affiliationResults = XMLHelper.createElementWithAttribute(RG_AFFILIATION_VOTES,
            NS_RG,
            ATTR_PUBLICATION_LANGUAGE,
            PublicationLanguage.toValidAbbreviation(list.getPublicationLanguage()));
        if (isReferendum) {
          affiliationResults.appendChild(createListIdentifierElementReferendum(list.getGruppe()));
          affiliationResults.appendChild(createValidVotesForListe(list,
              id_Gebiet,
              id_Ergebniseingang));
        } else {
          affiliationResults.appendChild(createListIdentifierElement(list));
          appendCandidateResultsRG510(affiliationResults,
              list,
              id_Gebiet,
              id_Ergebniseingang,
              emlType);
        }
        parent.appendChild(affiliationResults);
      }
    } catch (FinderException e) {
      LOGGER.error(Messages.getString(MessageKeys.Msg_FehlerInAnwendung), e);
      throw new EJBException(Messages.getString(MessageKeys.Msg_FehlerInAnwendung), e);
    }
  }

  /**
   * Create the AffiliationVotes elements for all lists before any votes have been entered.
   */
  void appendEmptyAffiliationVotes(Element parent, String id_Gebiet, EMLMessageType emlType) {
    try {
      Collection<Liste> lists = getListeHome().findAllByGebiet(id_Gebiet);
      for (Liste list : lists) {
        Element affiliationResults = XMLHelper.createElementWithAttribute(RG_AFFILIATION_VOTES,
            NS_RG,
            ATTR_PUBLICATION_LANGUAGE,
            PublicationLanguage.toValidAbbreviation(list.getPublicationLanguage()));
        affiliationResults.appendChild(createListIdentifierElement(list));
        appendEmptyCandidateResultsRG510(affiliationResults, list, emlType);
        parent.appendChild(affiliationResults);
      }
    } catch (FinderException e) {
      LOGGER.error(Messages.getString(MessageKeys.Msg_FehlerInAnwendung), e);
      throw new EJBException(Messages.getString(MessageKeys.Msg_FehlerInAnwendung), e);
    }
  }

  /**
   * Creates the AffiliationVotes elements from only a subset <code>regions</code> of the subregions
   * of the <code>parentRegion</code>. Also creates the elements for the general groups. All vote
   * results needed are in <code>resultSummary</code> already.
   */
  void appendAffiliationVotesAndGeneralGroups(ResultSummary resultSummary,
      Element rg510,
      Gebiet parentRegion,
      List<Gebiet> regions,
      boolean isReferendum,
      int votingChannel,
      EMLMessageType emlType) throws EJBException, FinderException {

    Element parent;
    if (votingChannel == ExportHandlingBean.POSTAL_VOTES) {
      parent = XMLHelper.createElement(de.ivu.wahl.export.XMLTags.RG_POSTAL_VOTES, NS_RG);
    } else {
      parent = XMLHelper.createElement(de.ivu.wahl.export.XMLTags.RG_PRESENCE_VOTES, NS_RG);
    }
    rg510.appendChild(parent);

    Map<Integer, Integer> position2Votes = new HashMap<Integer, Integer>();
    for (PartyWithCandidates party : resultSummary.getAllGroupsAndCandidates()) {
      // party
      int partyVotes = 0;
      Map<Gebiet, Integer> gruppenstimmenProGebiet = party.getGruppenstimmenProGebiet();
      for (Gebiet region : regions) {
        partyVotes += gruppenstimmenProGebiet.get(region);
      }

      if (party.getGruppenPosition() > 0) {
        Element affiliationResults = XMLHelper.createElementWithAttribute(RG_AFFILIATION_VOTES,
            NS_RG,
            ATTR_PUBLICATION_LANGUAGE,
            PublicationLanguage.toValidAbbreviation(party.getPublicationLanguage()));
        Gruppe gruppe = getGruppeHome().findByPrimaryKey(party.getGruppenID());
        Element validVotesElement = XMLHelper.createElementWithValue(XMLTags.EML_VALID_VOTES,
            NS_RG,
            partyVotes);
        if (isReferendum) {
          affiliationResults.appendChild(createListIdentifierElementReferendum(gruppe));
          affiliationResults.appendChild(validVotesElement);
        } else {
          affiliationResults.appendChild(createListIdentifierElement(gruppe));
          affiliationResults.appendChild(validVotesElement);

          // candidates
          for (CandidateVotesPerRegion candidate : party.getCandidateVotesPerRegion()) {
            int candidateVotes = 0;
            Map<Gebiet, Integer> personenstimmenProGebiet = candidate.getPersonenstimmenProGebiet();
            for (Gebiet region : regions) {
              candidateVotes += personenstimmenProGebiet.get(region);
            }

            Element candidateResult = XMLHelper
                .createElement(de.ivu.wahl.export.XMLTags.RG_CANDIDATE_VOTES, NS_RG);
            Listenkandidatur listenkandidatur = getListenkandidaturHome()
                .findByPrimaryKey(candidate.getIdListenkandidatur());
            candidateResult.appendChild(createCandidateElement(listenkandidatur,
                false,
                true,
                false,
                emlType));
            candidateResult.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_VALID_VOTES,
                NS_RG,
                candidateVotes));
            affiliationResults.appendChild(candidateResult);
          }
        }

        parent.appendChild(affiliationResults);

      } else {
        position2Votes.put(party.getGruppenPosition(), partyVotes);
      }
    }

    appendVotesForRGGeneralGroups(parentRegion, parent, position2Votes);
  }

  private void appendVotesForRGGeneralGroups(Gebiet region,
      Element parent,
      Map<Integer, Integer> position2Votes) {
    GruppeAllgemeinXmlAdapter adapter = new GruppeAllgemeinXmlAdapter();
    List<GruppeAllgemein> gruppenAllgemein = GruppeKonstanten.GruppeAllgemein
        .filterGruppenAllgemeinVisibleInRegionOrGueltige(region, adapter.getGruppenAllgemein());
    for (GruppeAllgemein gruppeAllgemein : gruppenAllgemein) {
      Integer value = position2Votes.get(gruppeAllgemein.getPosition());
      if (value != null) {
        adapter.putRgXml(parent, gruppeAllgemein, value);
      }
    }
  }

  Element createVoterObjectionsRG() {
    Element objectionElement = XMLHelper
        .createElement(de.ivu.wahl.export.XMLTags.RG_VOTERS_OBJECTIONS, NS_RG);
    objectionElement.appendChild(createElementWithRepositoryValue(RG_OBJECTIONS));
    objectionElement.appendChild(createElementWithRepositoryValue(RG_NOTES));
    return objectionElement;
  }

  private Element createElementWithRepositoryValue(String name) {
    String value = _propertyHandling.getProperty(name);
    return XMLHelper.createElementWithValue(name, NS_RG, value);
  }

  private void appendCandidateResultsRG510(Element listVotesElement,
      Liste liste,
      String id_Gebiet,
      String id_Ergebniseingang,
      EMLMessageType emlType) {
    try {
      listVotesElement.appendChild(createValidVotesForListe(liste, id_Gebiet, id_Ergebniseingang));

      Collection<Stimmergebnis> candidateResults = getVotesHandling()
          .getSortedCandidateResults(liste, id_Gebiet, id_Ergebniseingang);
      for (Stimmergebnis ste : candidateResults) {
        Element candidateResult = XMLHelper
            .createElement(de.ivu.wahl.export.XMLTags.RG_CANDIDATE_VOTES, NS_RG);
        candidateResult.appendChild(createCandidateElement(ste.getListenkandidatur(),
            false,
            true,
            false,
            emlType));
        candidateResult.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_VALID_VOTES,
            NS_RG,
            ste.getStimmen()));
        listVotesElement.appendChild(candidateResult);
      }
    } catch (FinderException e) {
      LOGGER.error(Messages.getString(MessageKeys.Error_ErrorRetrievingCandidateResults), e);
      throw new EJBException(Messages.getString(MessageKeys.Error_ErrorRetrievingCandidateResults),
          e);
    }
  }

  /**
   * Create empty rg:CandidateVotes elements with 0 votes for each candidate. Needed for model T11
   * (before votes have been entered)
   */
  private void appendEmptyCandidateResultsRG510(Element listVotesElement,
      Liste liste,
      EMLMessageType emlType) {

    String noVotes = "0"; //$NON-NLS-1$
    listVotesElement.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_VALID_VOTES,
        NS_RG,
        noVotes));

    for (Listenkandidatur listenkandidatur : liste.getListenkandidaturCol()) {
      Element candidateResult = XMLHelper
          .createElement(de.ivu.wahl.export.XMLTags.RG_CANDIDATE_VOTES, NS_RG);
      candidateResult.appendChild(createCandidateElement(listenkandidatur,
          false,
          true,
          false,
          emlType));
      candidateResult.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_VALID_VOTES,
          NS_RG,
          noVotes));
      listVotesElement.appendChild(candidateResult);
    }
  }

  private Element createValidVotesForListe(Liste liste, String id_Gebiet, String id_Ergebniseingang)
      throws FinderException {
    int votes = getVotesHandling().getVotesForListInRegion(liste, id_Gebiet, id_Ergebniseingang);
    return XMLHelper.createElementWithValue(XMLTags.EML_VALID_VOTES, NS_RG, votes);
  }

  @Override
  public String createVotesCsvExport(ResultSummary resultSummary, Document eml510)
      throws EJBException, ImportException {
    return new CsvVotesExportHelper().createCsvExport(resultSummary, eml510, getPropertyHandling());
  }

  @Override
  public String createCandidateAddressesCsvExport() throws EJBException, ImportException {
    List<GruppeGebietsspezifisch> ggs = new ArrayList<GruppeGebietsspezifisch>();
    if (WahlInfo.getWahlInfo().getElectionCategory().isEP()) {
      // Für die Europawahl sind auf allen Gebieten die gleichen Listen aufgestellt
      Gebiet gebiet = getGebietHandling().getGebieteMitListen().iterator().next();
      ggs.addAll(gebiet.getGruppeGebietsspezifischCol());
    } else {
      for (Gebiet gebiet : getGebietHandling().getGebieteMitListen()) {
        ggs.addAll(gebiet.getGruppeGebietsspezifischCol());
      }
    }
    return new CandidateAddressesExportGenerator(ggs).generateCandidateExport();
  }

  @Override
  public String createCandidateDetailsCsvExport() throws EJBException, ImportException {
    try {
      return new CandidateDetailsExportGenerator(this).generateExport();
    } catch (FinderException e) {
      e.printStackTrace();
      throw new EJBException(e);
    }
  }

  /**
   * ShortCode is needed on CSB-Level for TK and PS2 elections only
   */
  boolean useShortCode(final EMLMessageType emlType) {
    return WahlInfo.getWahlInfo().useShortCode()
        && (EMLMessageType.EML510d.equals(emlType) || EMLMessageType.EML520.equals(emlType));
  }

  @Override
  public void updateBackupArchive() {
    long start = System.currentTimeMillis();
    File exportFolder = getPropertyHandling().getFileProperty(Konstanten.PROP_EXPORT_FORMULAR_DIR);
    File backupFolder = getBackupFolder(exportFolder);

    String backupFilePrefix = Messages.getString(MessageKeys.BackupFilePrefix);
    String electionId = WahlInfo.getWahlInfo().getID_Wahl();
    String programSpecificAffix = EJBUtil.getProgramSpecificAffix();
    File backupFile = new File(backupFolder, backupFilePrefix + electionId + "_" //$NON-NLS-1$
        + programSpecificAffix + ZIP_FILE_EXTENSION);

    // Generate random filename for backup
    int id = new Random().nextInt(1000000);
    File tempBackupFile = new File(backupFolder, backupFilePrefix + id + ZIP_FILE_EXTENSION);
    try {
      // Create ZIP file with the content of the export and archive folders
      FolderZiper.zipFolder(tempBackupFile.getPath(), exportFolder.getPath());
      if (tempBackupFile.exists()) {
        // Only replace backup by newer backups
        if (backupFile.exists() && tempBackupFile.lastModified() > backupFile.lastModified()) {
          backupFile.delete();
        }
        if (backupFile.exists()) {
          tempBackupFile.delete();
        } else {
          tempBackupFile.renameTo(backupFile);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new EJBException("An error occured while creating " + backupFile, e); //$NON-NLS-1$
    }
    LOGGER.info("updateBackupArchive() in " + (System.currentTimeMillis() - start) + " ms"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  private File getBackupFolder(File exportFolder) {
    File backupFolder = new File(exportFolder, ".Gecomprimeerde_Verkiezingsbestanden"); //$NON-NLS-1$
    if (!backupFolder.exists() && !backupFolder.mkdirs()) {
      throw new EJBException("Could not create directory " + backupFolder); //$NON-NLS-1$
    }
    return backupFolder;
  }

  // /// End of EML generation

  @Override
  public void updateElectionResultArchive() {
    long start = System.currentTimeMillis();
    File exportFolder = getPropertyHandling().getFileProperty(Konstanten.PROP_EXPORT_FORMULAR_DIR);
    File backupFolder = getBackupFolder(exportFolder);

    String resultsBackupFilePrefix = Messages.getString(MessageKeys.ResultsBackupFilePrefix);
    String electionId = WahlInfo.getWahlInfo().getID_Wahl();
    File resultsBackupFile = new File(backupFolder, resultsBackupFilePrefix + electionId
        + ZIP_FILE_EXTENSION);

    String overallExportPath = exportFolder.getPath().substring(0,
        exportFolder.getPath().lastIndexOf(File.separator) + 1);
    String[] exportFolders = new String[4];
    exportFolders[0] = overallExportPath + "P4_PSB"; //$NON-NLS-1$
    exportFolders[1] = overallExportPath + "P4_HSB"; //$NON-NLS-1$
    exportFolders[2] = overallExportPath + "P4_CSB"; //$NON-NLS-1$
    exportFolders[3] = overallExportPath + "P5"; //$NON-NLS-1$

    // Generate random filename for backup
    int id = new Random().nextInt(1000000);
    File tempResultsBackupFile = new File(backupFolder, resultsBackupFilePrefix + id
        + ZIP_FILE_EXTENSION);
    try {
      // Create ZIP file with the content of the export and archive folders
      FolderZiper
          .zipFolderFilterResults(electionId, tempResultsBackupFile.getPath(), exportFolders);
      if (tempResultsBackupFile.exists()) {
        // Only replace backup by newer backups
        if (resultsBackupFile.exists()
            && tempResultsBackupFile.lastModified() > resultsBackupFile.lastModified()) {
          resultsBackupFile.delete();
        }
        if (resultsBackupFile.exists()) {
          tempResultsBackupFile.delete();
        } else {
          tempResultsBackupFile.renameTo(resultsBackupFile);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new EJBException("An error occured while creating " + resultsBackupFile, e); //$NON-NLS-1$
    }
    LOGGER.info("updateBackupArchive() in " + (System.currentTimeMillis() - start) + " ms"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  KonfliktHome _konfliktHome = null;

  KonfliktHome getKonfliktHome() {
    if (_konfliktHome == null) {
      _konfliktHome = (KonfliktHome) findLocalHome("Konflikt"); //$NON-NLS-1$
    }
    return _konfliktHome;
  }

  GruppeHome _gruppeHome = null;

  GruppeHome getGruppeHome() {
    if (_gruppeHome == null) {
      _gruppeHome = GruppeHome.HomeFinder.findHome(this);
    }
    return _gruppeHome;
  }

  @EJB
  PropertyHandling _propertyHandling;

  public PropertyHandling getPropertyHandling() {
    return _propertyHandling;
  }

  @Override
  public AdminHandling getAdminHandling() throws EJBException {
    return super.getAdminHandling();
  }
}