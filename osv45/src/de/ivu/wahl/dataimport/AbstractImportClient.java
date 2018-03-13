/*
 * Created on 06.01.2009
 * @author ugo
 */
package de.ivu.wahl.dataimport;

import static de.ivu.ejb.EJBUtil.getUniqueKey;
import static de.ivu.wahl.dataimport.ImportUtil.readXMLRoot;
import static de.ivu.wahl.dataimport.XMLTags.ATTR_ED_REGION_CATEGORY;
import static de.ivu.wahl.dataimport.XMLTags.ATTR_ED_REGION_NUMBER;
import static de.ivu.wahl.dataimport.XMLTags.ED_ELECTION_TREE;
import static de.ivu.wahl.dataimport.XMLTags.ED_REGION;
import static de.ivu.wahl.dataimport.XMLTags.ED_REGION_NAME;
import static de.ivu.wahl.dataimport.XMLTags.EML;
import static de.ivu.wahl.dataimport.XMLTags.EML_CANDIDATE_IDENTIFIER;
import static de.ivu.wahl.dataimport.XMLTags.EML_ELECTION;
import static de.ivu.wahl.dataimport.XMLTags.EML_ELECTION_EVENT;
import static de.ivu.wahl.dataimport.XMLTags.EML_OPTIONS_LIST;
import static de.ivu.wahl.dataimport.XMLTags.GEBIET_ATTRIBUT_UEGGEBIETSNUMMER;
import static de.ivu.wahl.dataimport.XMLTags.NS_ED;
import static de.ivu.wahl.dataimport.XMLTags.NS_EML;
import static de.ivu.wahl.dataimport.XMLTags.NS_KR;
import static de.ivu.wahl.dataimport.XMLTags.NS_XAL;
import static de.ivu.wahl.dataimport.XMLTags.NS_XNL;
import static de.ivu.wahl.dataimport.XMLTags.WAHL_ART;
import static de.ivu.wahl.dataimport.XMLTags.WAHL_IDENTIFIER;
import static de.ivu.wahl.modell.WahlModel.WAHLGEBIETSARTEN;
import static de.ivu.wahl.modell.exception.ImportException.TYPE_CONTENT;
import static de.ivu.wahl.util.XMLImportHelper.getAttribute;
import static de.ivu.wahl.util.XMLImportHelper.getText;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Category;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;

import de.ivu.ejb.EJBUtil;
import de.ivu.ejb.bmp.Model;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.Gebietsart;
import de.ivu.wahl.modell.GruppeComposite;
import de.ivu.wahl.modell.GruppeComposite.Liste;
import de.ivu.wahl.modell.GruppeComposite.Listenkandidat;
import de.ivu.wahl.modell.GruppeGebietsspezifischModel;
import de.ivu.wahl.modell.GruppeKonstanten;
import de.ivu.wahl.modell.GruppeModel;
import de.ivu.wahl.modell.ListeModel;
import de.ivu.wahl.modell.ListenkandidaturModel;
import de.ivu.wahl.modell.ListenkombinationModel;
import de.ivu.wahl.modell.PersonendatenKonstanten;
import de.ivu.wahl.modell.PersonendatenModel;
import de.ivu.wahl.modell.PublicationLanguage;
import de.ivu.wahl.modell.RepositoryModel;
import de.ivu.wahl.modell.WahlModel;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.modell.impl.GebietModelImpl;
import de.ivu.wahl.modell.impl.GruppeGebietsspezifischModelImpl;
import de.ivu.wahl.modell.impl.GruppeModelImpl;
import de.ivu.wahl.modell.impl.ListeModelImpl;
import de.ivu.wahl.modell.impl.ListenkandidaturModelImpl;
import de.ivu.wahl.modell.impl.ListenkombinationModelImpl;
import de.ivu.wahl.modell.impl.PersonendatenModelImpl;
import de.ivu.wahl.modell.impl.RepositoryModelImpl;
import de.ivu.wahl.util.XMLImportHelper;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * Common superclass for different kinds of import clients
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public abstract class AbstractImportClient {

  private static final Category LOGGER = Log4J.configure(AbstractImportClient.class);

  static {
    LOGGER.info(Log4J.dumpVersion(AbstractImportClient.class, Log4J.extractVersion("$Revision$"))); //$NON-NLS-1$
  }

  final IImportEML _importMetadata;
  final String _electionDomain;
  final String _electionDomainId;

  int[] _gebietsarten;

  WahlModel _wahlModel;
  GebietModel _wurzelgebiet;
  String _id_Wahlgebiet;
  int _gebietsartAwE = -1;

  final List<Model> _models = new ArrayList<Model>();

  // Mappings f�r Konsistenzchecks
  /**
   * H�lt die angelegten Gruppen zwecks Aufl�sen der Referenzen in der EML-Nachricht
   */
  final Map<String, String> _gruppeKeyMap = new HashMap<String, String>();

  /**
   * Comment for <code>_gebietsMap</code> <Gebietsart_Nummer, Gebiet>
   */
  final Map<String, GebietModel> _gebietsMap = new HashMap<String, GebietModel>();

  /**
   * H�lt die angelegten Gebiete zwecks Aufl�sen der Referenz in der EML-Nachricht
   */
  final Map<String, GebietModel> _gebietKeyMap = new HashMap<String, GebietModel>();

  /**
   * H�lt die IDs der GruppeGebietsspezifisch des Wurzelgebietes
   */
  final Map<String, String> _uegGgKeyMap = new HashMap<String, String>();

  /**
   * Holding id's for GruppeGebietsspezifisch objects per id_Gebiet and id_Gruppe
   */
  final Map<GruppeGebietsspezifischKey, String> _ggKeyMap = new HashMap<GruppeGebietsspezifischKey, String>();

  /**
   * Liste aller Untergebiets-IDs
   */
  final List<GebietModel> _gebietsListe = new ArrayList<GebietModel>();

  /**
   * H�lt die Personendaten, um sp�tere Referenzen aufzul�sen, zu pr�fen und zu setzen
   */
  final Map<String, PersonendatenModel> _personenMap = new HashMap<String, PersonendatenModel>();

  /**
   * Holding special group objects for the algorithm
   */
  final Map<String, GruppeComposite> _gruppeMap = new HashMap<String, GruppeComposite>();

  /**
   * Holding parties for list combinations Set<id_Listenkombination> Set<String>
   */
  final Set<String> _listenkombinationen = new HashSet<String>();

  /** Map<identicalListId,id_Liste> */
  final Map<String, String> _identicalLists = new HashMap<String, String>();

  public AbstractImportClient(IImportEML importMetadata) {
    if (importMetadata.getStatus() != AbstractImportEML.STATUS_KOMPLETT) {
      LOGGER.info(importMetadata.getFehlermeldung());
      System.out.println(importMetadata.getFehlermeldung()); // For jenkins output
      throw new RuntimeException(
          Messages.bind(MessageKeys.Error_DatenimportNichtVollstaendigInitialisiert));
    }
    _importMetadata = importMetadata;
    _electionDomain = importMetadata.getElectionDomain();
    _electionDomainId = importMetadata.getElectionDomainId();
  }

  /**
   * Evaluate election definition file and create election metadata and regions
   * 
   * @throws ImportException
   */
  void createWahl() throws ImportException {
    URL wahldefinitionURL = _importMetadata.getDefinition();
    Element eml;
    Element wahldefinition;
    boolean isReferendum = false;
    switch (_importMetadata.getImportType()) {
    // election
      case EML110A_AND_EML230 :
        eml = readXMLRoot(wahldefinitionURL, EML);
        Element electionEvent = eml.getFirstChildElement(EML_ELECTION_EVENT, NS_EML);
        wahldefinition = electionEvent.getFirstChildElement(EML_ELECTION, NS_EML);
        break;

      // referendum
      case EML630 :
        eml = readXMLRoot(wahldefinitionURL, EML);
        Element optionsList = eml.getFirstChildElement(EML_OPTIONS_LIST, NS_EML);
        wahldefinition = optionsList.getFirstChildElement(EML_ELECTION, NS_EML);
        isReferendum = true;
        break;

      default :
        throw new ImportException(Messages.bind(MessageKeys.Error_UnerwarteterImportTyp,
            _importMetadata.getImportType()));
    }

    Element identifier = wahldefinition.getFirstChildElement(WAHL_IDENTIFIER, NS_EML);
    checkElectionDomain(identifier, _electionDomain, _electionDomainId, wahldefinitionURL.getFile());

    ElectionCategory electionCategory = _importMetadata.getElectionCategory();
    _gebietsarten = getGebietsarten(electionCategory, _importMetadata.getGebietsart());
    String id_Wurzelgebiet = getUniqueKey();
    _wurzelgebiet = new GebietModelImpl(id_Wurzelgebiet);

    _wahlModel = AbstractImportEML.readElectionDetails(eml, wahldefinition, identifier);
    _wahlModel.setStatus(WahlModel.STATE_INITIAL);
    _wahlModel.setWahlebene(_importMetadata.getLevel());
    if (_importMetadata.getModus() == AbstractImportEML.MODE_DB_P5) {
      _wahlModel.setGebietsartErfassungseinheit(_gebietsarten[0]);
    } else {
      _wahlModel.setGebietsartErfassungseinheit(_gebietsarten[1]);
    }
    _wahlModel.setAktuelleWahlergebnisart(WahlModel.INPUT_MODE_COMPLETE);

    _wurzelgebiet.setID_Wahl(_wahlModel.getID_Wahl());
    _models.add(_wahlModel);
    _models.add(_wurzelgebiet);

    // Zun�chst mal die allgemeinen Gruppen anlegen
    createGruppenAllgemein(isReferendum);
    createGebiete(wahldefinition.getFirstChildElement(ED_ELECTION_TREE, NS_KR),
        NS_KR,
        wahldefinitionURL);
  }

  /**
   * Check consistency of different input files
   * 
   * @throws ImportException
   */
  public static void checkWahl(Element wahlIdentifier, WahlModel wahlModel, String filename)
      throws ImportException {
    Element art = wahlIdentifier.getFirstChildElement(WAHL_ART, XMLTags.NS_ED);
    if (art == null) {
      art = wahlIdentifier.getFirstChildElement(WAHL_ART, XMLTags.NS_EML);
    }
    ElectionCategory electionCategoryFromWahlModel = getElectionCategory(wahlModel);
    ElectionCategory electionCategoryFromEml = getElectionCategory(getText(art));
    if (!electionCategoryFromEml.equals(electionCategoryFromWahlModel)) {
      throw new ImportException(Messages.getString(MessageKeys.Error_FalscheWahlart)
          + Messages.bind(MessageKeys.Error_Erwartet_0_Gefunden_1,
              electionCategoryFromWahlModel,
              electionCategoryFromEml));
    }
    if (!wahlModel.getID_Wahl().equals(getAttribute(wahlIdentifier, XMLTags.ATTR_EML_ID))) {
      throw new ImportException(Messages.getString(MessageKeys.Error_FalscheWahl)
          + Messages.bind(MessageKeys.Error_Erwartet_0_Gefunden_1,
              wahlModel.getID_Wahl(),
              getAttribute(wahlIdentifier, XMLTags.ATTR_EML_ID)));
    }
    try {
      Date wahldatum = XMLImportHelper.getDateValue(wahlIdentifier
          .getFirstChildElement(XMLTags.KR_WAHL_DATUM, XMLTags.NS_KR));
      LOGGER.info("vorh " + wahlModel.getTermin() + " aus " + filename + " " + wahldatum); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      if (!wahlModel.getTermin().equals(wahldatum)) {
        // TODO: ext., aufh�bschen
        throw new ImportException(Messages.getString(MessageKeys.Error_FalscherWahltermin)
            + Messages.bind(MessageKeys.Error_Erwartet_0_Gefunden_1,
                wahlModel.getTermin(),
                wahldatum));
      }
    } catch (ImportException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error(Messages.getString(MessageKeys.Error_FehlerBeimAuswertenDesWahldatums), e);
      throw new ImportException(
          Messages.getString(MessageKeys.Error_FehlerBeimAuswertenDesWahldatums));
    }
    checkElectionDomain(wahlIdentifier,
        wahlModel.getElectionDomain(),
        wahlModel.getElectionDomainId(),
        filename);
  }

  public static void checkElectionDomain(Element electionIdentifier,
      String electionDomainIs,
      String electionDomainIdIs,
      String filename) throws ImportException {
    Element domainElement = electionIdentifier.getFirstChildElement(XMLTags.KR_ELECTION_DOMAIN,
        NS_KR);
    if (domainElement == null) {
      domainElement = electionIdentifier.getFirstChildElement(XMLTags.KR_ELECTION_DOMAIN, NS_ED);
    }
    if (domainElement == null) {
      if (electionDomainIs != null) {
        throw new ImportException(Messages.bind(MessageKeys.Error_NoElectionDomainFoundIn_0,
            filename));
      } else {
        // ok
        return;
      }
    }
    String electionDomain = domainElement.getValue();
    if (electionDomain != null && electionDomainIs == null) {
      throw new ImportException(Messages.bind(MessageKeys.Error_NoElectionDomainExpectedFor_0,
          filename));
    }
    if (electionDomainIs != null && !electionDomainIs.equals(electionDomain)) {
      throw new ImportException(
          Messages.bind(MessageKeys.Error_WrongElectionDomainFoundIn_0_Expected_1_found_2,
              filename,
              electionDomainIs,
              electionDomain));
    }

    // Check election domain id
    Attribute electionDomainIdAttribute = domainElement
        .getAttribute(XMLTags.ELECTION_DOMAIN_ATTRIBUT_ID);
    if (electionDomainIdAttribute == null) {
      if (electionDomainIdIs != null) {
        throw new ImportException(Messages.bind(MessageKeys.Error_NoElectionDomainIdFoundIn_0,
            filename));
      } else {
        // ok
        return;
      }
    }
    String electionDomainId = electionDomainIdAttribute.getValue();
    if (electionDomainId != null && electionDomainIdIs == null) {
      throw new ImportException(Messages.bind(MessageKeys.Error_NoElectionDomainIdExpectedFor_0,
          filename));
    }
    if (electionDomainIdIs != null && !electionDomainIdIs.equals(electionDomainId)) {
      throw new ImportException(
          Messages.bind(MessageKeys.Error_WrongElectionDomainIdFoundIn_0_Expected_1_found_2,
              filename,
              electionDomainIdIs,
              electionDomainId));
    }
  }

  /**
   * Create regions
   * 
   * @param gebiete der Gebietsknoten
   * @param wahldefinitionURL
   * @throws ImportException
   */
  void createGebiete(Element gebiete, String namespace, URL wahldefinitionURL)
      throws ImportException {
    try {
      // Wenn bislang keine Gebietsart für die Auswertungseinheit gesetzt ist, nimm' die kleinste
      // verfügbare
      if (_gebietsartAwE < 0) {
        _gebietsartAwE = _gebietsarten[_gebietsarten.length - 1];
        _wahlModel.setGebietsartAuswertungseinheit(_gebietsartAwE);
        LOGGER.debug(Messages
            .bind(MessageKeys.Result_Tracelog_GebietsartAuswertungseinheitAuf_0_Gesetzt,
                Gebietsart.getKlartext(_gebietsartAwE)));
      }

      // Existing regions
      Set<Integer> gebietsnummern = new HashSet<Integer>();

      String idElterngebiet = null;
      String artElterngebiet = null;
      Elements nodes = gebiete.getChildElements(ED_REGION, namespace);
      for (int gebietsIdx = 0; gebietsIdx < nodes.size(); ++gebietsIdx) {
        Element gebietKnoten = nodes.get(gebietsIdx);
        String gebietsartStr = getAttribute(gebietKnoten, ATTR_ED_REGION_CATEGORY);
        int gebietsart = getGebietsart(gebietsartStr);
        if (_importMetadata.getElectionCategory().isOnIsland()) {
          gebietsart = GebietModel.GEBIETSART_INSELGEMEINDE;
        }

        if (gebietsart != _gebietsarten[0] && gebietsart != _gebietsarten[1]) {
          continue;
        }

        int nummer = 0;
        boolean isRoman = false;
        // If there is no region number, it must be STATE, take 0
        if (gebietKnoten.getAttribute(ATTR_ED_REGION_NUMBER) != null) {
          nummer = XMLImportHelper.getAttributeIntValue(gebietKnoten, ATTR_ED_REGION_NUMBER);
          isRoman = XMLImportHelper.getAttributeBoolValue(gebietKnoten,
              XMLTags.GEBIET_ATTRIBUT_NR_ROEMISCH,
              false);
        } else {
          // Only STATE has no number
          if (gebietsart != GebietModel.GEBIETSART_BUND) {
            throw new ImportException(Messages.bind(MessageKeys.Error_GebietsartFehltInXml));
          }
        }

        GebietModel gebiet;
        if (gebietsart == _gebietsarten[0]) {
          if (nummer == _importMetadata.getGebietsNr()) {
            gebiet = _wurzelgebiet;
            gebiet.setNummer(nummer);
            gebiet.setGebietsart(gebietsart);
            // Wahlgebiet?
            LOGGER.info(Messages.bind(MessageKeys.Result_Tracelog_Wahlart_0,
                _importMetadata.getElectionCategory()));
            if (gebietsart == GebietModel.GEBIETSARTEN_WAHLGEBIET.get(_importMetadata
                .getElectionCategory())) {
              _id_Wahlgebiet = gebiet.getID_Gebiet();
            } else {
              _id_Wahlgebiet = null;
            }

            idElterngebiet = XMLImportHelper.getAttribute(gebietKnoten,
                XMLTags.GEBIET_ATTRIBUT_UEGGEBIETSNUMMER);
            artElterngebiet = XMLImportHelper.getAttribute(gebietKnoten,
                XMLTags.GEBIET_ATTRIBUT_UEGGEBIETSART);
          } else {
            continue;
          }
        } else {
          // Check for duplicate region number
          if (gebietsnummern.contains(new Integer(nummer))) {
            throw new ImportException(wahldefinitionURL,
                Messages.bind(MessageKeys.Error_DoppelteGebietsnummer, gebietsartStr, nummer));
          }
          int uegNummer = XMLImportHelper.getAttributeIntValue(gebietKnoten,
              GEBIET_ATTRIBUT_UEGGEBIETSNUMMER);
          if (uegNummer == _importMetadata.getGebietsNr()
              || gebietKnoten.getAttribute(GEBIET_ATTRIBUT_UEGGEBIETSNUMMER) == null) {
            gebiet = createGebietModelByArtAndNummer(gebietsart, nummer);
            // Important for navigation
            gebiet.setErfassungseinheit(true);
            // CSB input only for root region
            gebiet.setGUIEingabeErlaubt(_importMetadata.getLevel() != GebietModel.EBENE_CSB);
            gebiet.setID_UebergeordnetesGebiet(_wurzelgebiet.getID_Gebiet());
          } else {
            continue;
          }
        }

        Elements tmpList = gebietKnoten.getChildElements(ED_REGION_NAME, namespace);
        if (tmpList.size() != 1) {
          throw new ImportException(wahldefinitionURL,
              Messages.bind(MessageKeys.Error_GebietNr_0_HatKeinenText, nummer));
        }
        gebiet.setGUIEingabeErlaubt(gebiet.isErfassungseinheit());
        gebiet.setRoemisch(isRoman);
        gebiet.setName(tmpList.get(0).getValue());
        if (gebietsart == _gebietsarten[1]) {
          _gebietsListe.add(gebiet);
        }

        Elements committees = gebietKnoten.getChildElements(XMLTags.ED_COMMITTEE, namespace);
        for (int i = 0; i < committees.size(); ++i) {
          Element committee = committees.get(i);
          if (XMLImportHelper.getAttribute(committee, XMLTags.ED_ATTR_COMMITTEE_NAME) != null) {
            RepositoryModel prop = new RepositoryModelImpl(EJBUtil.getUniqueKey());
            String committeeId = XMLImportHelper.getAttribute(committee,
                XMLTags.ED_ATTR_COMMITTEE_CATEGORY) + gebiet.getNummer();
            prop.setName(committeeId);
            prop.setWert(XMLImportHelper.getAttribute(committee, XMLTags.ED_ATTR_COMMITTEE_NAME));
            _models.add(prop);
          }
          String committeeCategory = XMLImportHelper.getAttribute(committee,
              XMLTags.ED_ATTR_COMMITTEE_CATEGORY);
          if ("CSB".equals(committeeCategory)) { //$NON-NLS-1$
            System.out.println("############# CSB committee found ##############"); //$NON-NLS-1$
            RepositoryModel prop = new RepositoryModelImpl(EJBUtil.getUniqueKey());
            prop.setName(Konstanten.KEY_CSB_REGION_NAME);
            prop.setWert(gebiet.getName());
            _models.add(prop);
          }
        }

        _gebietKeyMap.put(gebiet.getName(), gebiet);

      }

      // For P4_PSB in EP elections ...
      ElectionCategory electionCategory = getElectionCategory(_wahlModel);
      if (_importMetadata.getLevel() == GebietModel.EBENE_PSB
          && (electionCategory.isEP() || ElectionCategory.NR.equals(electionCategory))) {
        searchForParentRegion(idElterngebiet, artElterngebiet, nodes, electionCategory);
      }

      // If no regions add root region
      if (_gebietsListe.isEmpty()) {
        _gebietsListe.add(_wurzelgebiet);
      }

      // Set region order
      setzeGebietspositionen(_models);
    } catch (Exception e) {
      throw new ImportException(Messages.bind(MessageKeys.Error_ImportGebietsdaten), e);
    }
  }

  private void searchForParentRegion(String idElterngebiet,
      String artElterngebiet,
      Elements nodes,
      ElectionCategory electionCategory) throws ImportException {
    for (int gebietsIdx = 0; gebietsIdx < nodes.size(); ++gebietsIdx) {
      Element gebietKnoten = nodes.get(gebietsIdx);
      String gebietsartStr = getAttribute(gebietKnoten, ATTR_ED_REGION_CATEGORY);
      String gebietsNummerStr = getAttribute(gebietKnoten, ATTR_ED_REGION_NUMBER);
      if (ObjectUtils.equals(artElterngebiet, gebietsartStr)
          && ObjectUtils.equals(idElterngebiet, gebietsNummerStr)) {
        // Parent region found
        Element regionNameElement = gebietKnoten
            .getFirstChildElement(ED_REGION_NAME, XMLTags.NS_KR);
        if (regionNameElement != null) {
          if (electionCategory.isEK()) {
            setParentRegionName(regionNameElement.getValue());
          } else if (ElectionCategory.NR.equals(electionCategory)) {
            setElectioalDistrictNameAndNumber(regionNameElement.getValue(), idElterngebiet);
          }
        }
        return;
      }
    }
  }

  /**
   * Reading candidate lists (EML230) or referendum options list (EML630)
   * 
   * @throws ImportException
   */
  void createListen() throws ImportException {
    switch (_importMetadata.getImportType()) {
      case EML110A_AND_EML230 :
        createKandidatenListen();
        break;
      case EML630 :
        createReferendumListen();
        break;
      default :
        throw new ImportException(Messages.bind(MessageKeys.Error_UnerwarteterImportTyp,
            _importMetadata.getImportType()));
    }
  }

  /**
   * Reading EML230: candidate lists
   * 
   * @throws ImportException
   */
  private void createKandidatenListen() throws ImportException {
    URL emlURL = _importMetadata.getEML230();
    try {
      Element eml230 = readXMLRoot(emlURL, XMLTags.EML);
      Element kandidaten = eml230.getFirstChildElement(XMLTags.KANDIDATEN, XMLTags.NS_EML);
      Element wahldaten = kandidaten.getFirstChildElement(XMLTags.EML_WAHL, XMLTags.NS_EML);
      checkWahl(wahldaten.getFirstChildElement(XMLTags.EML_WAHL_IDENTIFIER, NS_EML),
          _wahlModel,
          emlURL.getFile());
      ElectionCategory electionCategory = getElectionCategory(_wahlModel);

      // Iterate regions
      Elements gebiete = wahldaten.getChildElements(XMLTags.EML_CONTEST, NS_EML);
      LOGGER.info(Messages.bind(MessageKeys.Result_Tracelog_Fand_0_Gebiete, gebiete.size()));
      // If lists are connected with regions at or above root region, connect it to root region
      // Only one list should be in eml230 in this case
      if (GebietModel.GEBIETSARTEN_MIT_LISTEN.get(getElectionCategory()) <= _wurzelgebiet
          .getGebietsart()) {
        if (gebiete.size() > 1) {
          throw new ImportException(
              Messages.bind(MessageKeys.Error_NurEinGebietMitListenErwartetNicht_0, gebiete.size()));
        }
        createListen(gebiete.get(0),
            _wurzelgebiet.getName(),
            Collections.singletonList(_wurzelgebiet),
            electionCategory);
        return;
      }
      for (int idxGebiet = 0; idxGebiet < gebiete.size(); ++idxGebiet) {
        Element gebietKnoten = gebiete.get(idxGebiet);
        String gebietIdent = gebietKnoten.getFirstChildElement(XMLTags.EML_CONTEST_IDENTIFIER,
            NS_EML).getAttributeValue(XMLTags.ATTR_EML_ID);
        if (XMLTags.ATTR_VAL_CONTEST_ID_ALLE.equals(gebietIdent)) {
          // Create identical lists for all regions
          createListen(gebietKnoten,
              XMLTags.ATTR_VAL_CONTEST_ID_ALLE,
              _gebietsListe,
              electionCategory);
          continue;
        }
        Element identifier = gebietKnoten.getFirstChildElement(XMLTags.EML_CONTEST_IDENTIFIER,
            NS_EML);
        String gebietName = getText(identifier, XMLTags.EML_CONTEST_NAME, NS_EML);
        GebietModel gebiet = _gebietKeyMap.get(gebietName);
        if (gebiet == null) {
          throw new ImportException(Messages.bind(MessageKeys.Error_ImportGebietsdaten) + ": " //$NON-NLS-1$
              + gebietName);
        }
        createListen(gebietKnoten, gebietName, Collections.singletonList(gebiet), electionCategory);
      }
    } catch (ImportException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error(e, e);
      throw new ImportException(e);
    }
  }

  /**
   * Create identical lists for all regions from gebiete
   * 
   * @param gebietKnoten xml-element <Contest>
   * @param gebietName name of region
   * @param gebiete list of regions to create lists for
   * @throws ImportException
   */
  void createListen(Element gebietKnoten,
      String gebietName,
      List<GebietModel> gebiete,
      ElectionCategory electionCategory) throws ImportException {
    Elements listen = gebietKnoten.getChildElements(XMLTags.EML_LISTEN, NS_EML);
    LOGGER.info(Messages.bind(MessageKeys.Result_Tracelog_AnzahlListenFuer_0_1_2,
        gebietName,
        listen.size(),
        gebiete.size()));
    for (int idxListe = 0; idxListe < listen.size(); ++idxListe) {
      Element listeKnoten = listen.get(idxListe);
      Element listenIdentifier = listeKnoten.getFirstChildElement(XMLTags.EML_LISTEN_IDENTIFIER,
          NS_EML);
      String name = getText(listenIdentifier, XMLTags.EML_GRUPPEN_NAME, NS_EML);
      // list metadata
      Element listenMetadaten = listeKnoten.getFirstChildElement(XMLTags.KR_LISTENDATEN,
          XMLTags.NS_KR);
      // Check for list combination
      String id_Listenkombination = XMLImportHelper.getAttribute(listenMetadaten,
          XMLTags.ATTR_LISTENKOMBINATION);
      if (id_Listenkombination != null) {
        if (electionCategory.isEK()) {
          throw new ImportException(
              Messages
                  .bind(MessageKeys.Error_FoundCombinedListAtEKElection_0, id_Listenkombination));
        }
        checkOrCreateListenkombination(id_Listenkombination);
      }
      GruppeComposite wdGruppe = getOrCreateGruppe(listenIdentifier, id_Listenkombination);
      GruppeModel gruppe = wdGruppe.getGruppe();
      // publish gender for this list?
      boolean isGeschlechtSichtbar = XMLImportHelper.getAttributeBoolValue(listenMetadaten,
          XMLTags.ATTR_GESCHLECHT_SICHTBAR,
          false);
      String publicationLanguage = XMLImportHelper.getAttribute(listenMetadaten,
          XMLTags.ATTR_PUBLICATION_LANGUAGE);
      publicationLanguage = PublicationLanguage.toValidAbbreviation(publicationLanguage);
      String id_Liste = null;
      String listenTyp = getText(listeKnoten, XMLTags.EML_TYP, XMLTags.NS_EML);
      // Check for identical list
      boolean hasMasterlist = false;
      String identicalListIdent = XMLImportHelper.getAttribute(listenMetadaten,
          XMLTags.ATTR_IDENTISCHE_LISTE);
      if (identicalListIdent != null) {
        // This candidate list is part of a set of identical lists.
        // identicalListIdent is the key for this set of identical lists.
        String identicalListKey = wdGruppe.getGruppe().getSchluessel() + "_" + identicalListIdent; //$NON-NLS-1$
        id_Liste = _identicalLists.get(identicalListKey);
        if (id_Liste == null) {
          // Create new list object
          id_Liste = createListe(identicalListIdent,
              gruppe.getID_Gruppe(),
              listenTyp,
              isGeschlechtSichtbar,
              publicationLanguage,
              name);
          _identicalLists.put(identicalListKey, id_Liste);
        } else {
          hasMasterlist = true;
        }
      } else {
        id_Liste = createListe(null,
            gruppe.getID_Gruppe(),
            listenTyp,
            isGeschlechtSichtbar,
            publicationLanguage,
            name);
      }
      // Create region specific group data
      Set<String> idsGruppeGebietsspezifisch = new HashSet<String>();
      for (GebietModel gebiet : gebiete) {
        LOGGER.debug(Messages.bind(MessageKeys.Result_Tracelog_GGFuerGebiet_0,
            gebiet.getBezeichnung()));
        String id = createGruppeGebietsspezifisch(gruppe.getID_Gruppe(),
            gebiet,
            id_Liste,
            gruppe.getSchluessel());
        idsGruppeGebietsspezifisch.add(id);
      }
      if (wdGruppe.getListen() == null) {
        throw new ImportException(
            Messages.bind(MessageKeys.Error_KeineListenInGruppeMitSchluessel_0_Gefunden,
                gruppe.getSchluessel()));
      }
      // Create list candidates only if there is no master list
      if (hasMasterlist) {
        LOGGER.info(Messages
            .getString(MessageKeys.Result_Tracelog_FoundMasterListNoCandidatesAreCreated));
        copyCandidatesFromMasterlist(idsGruppeGebietsspezifisch, id_Liste, wdGruppe);
        continue;
      }
      Elements bewerber = listeKnoten.getChildElements(XMLTags.EML_CANDIDATE, NS_EML);
      LOGGER.info(Messages.bind(MessageKeys.Result_Tracelog_Fand_0_BewerberFuerGruppe_1,
          bewerber.size(),
          gruppe.getSchluessel()));
      for (int idxKand = 0; idxKand < bewerber.size(); idxKand++) {
        ListenkandidaturModel listenkandidatur = new ListenkandidaturModelImpl(getUniqueKey());
        listenkandidatur.setID_Liste(id_Liste);
        listenkandidatur.setID_Wahl(_wahlModel.getID_Wahl());
        // list position for the candidate
        int listenplatz = getListenplatz(bewerber.get(idxKand));
        listenkandidatur.setListenplatz(listenplatz);
        // read personal data for the candidate and create Personendaten object
        PersonendatenModel personendaten = createBewerber(bewerber.get(idxKand));
        listenkandidatur.setID_Personendaten(personendaten.getID_Personendaten());
        addModel(listenkandidatur);
        // Adding candidate objects for algorithm for each list of the group
        for (GruppeComposite.Liste liste : wdGruppe.getListen()) {
          if (id_Liste.equals(liste.getId_Liste())) {
            liste.addKandidat(wdGruppe.new Listenkandidat(personendaten, listenplatz,
                listenkandidatur.getID_Listenkandidatur()));
          }
        }
      }
    }
  }

  /**
   * @param idsGruppeGebietsspezifisch
   * @param id_Liste
   * @param wdGruppe
   */
  private void copyCandidatesFromMasterlist(Set<String> idsGruppeGebietsspezifisch,
      String id_Liste,
      GruppeComposite wdGruppe) {
    GruppeComposite.Liste masterlist = null;
    for (Liste liste : wdGruppe.getListen()) {
      if (liste.getId_Liste().equals(id_Liste)
          && !idsGruppeGebietsspezifisch.contains(liste.getId_GruppeGebietsspezifisch())) {
        masterlist = liste;
      }
    }
    if (masterlist == null) {
      throw new RuntimeException("No masterlist found"); //$NON-NLS-1$
    }
    for (Liste liste : wdGruppe.getListen()) {
      if (idsGruppeGebietsspezifisch.contains(liste.getId_GruppeGebietsspezifisch())) {
        for (Listenkandidat master : masterlist.getKandidaten()) {
          PersonendatenModel personendaten = master.getPerson();
          int listenplatz = master.getListenplatz();
          String id_Listenkandidatur = master.getId_Listenkandidatur();
          liste.addKandidat(wdGruppe.new Listenkandidat(personendaten, listenplatz,
              id_Listenkandidatur));
        }
      }
    }
  }

  /**
   * Reading EML630: referendum options list
   * 
   * @throws ImportException
   */
  protected abstract void createReferendumListen() throws ImportException;

  protected abstract void setParentRegionName(String electoralDistrictName) throws ImportException;

  protected abstract void setElectioalDistrictNameAndNumber(String value, String idElterngebiet)
      throws ImportException;

  static final Pattern REGION_NR_PATTERN_230 = Pattern.compile("(\\d+)|(I|II|III)"); //$NON-NLS-1$

  /**
   * Read region number from eml230
   * 
   * @param ergebnis element <ReportingUnitVotes>
   * @return
   * @throws ImportException
   */
  static int readGebietsnummer230(Element ergebnis) throws ImportException {
    String gebietsIdStr = ergebnis.getFirstChildElement(XMLTags.EML_CONTEST_IDENTIFIER, NS_EML)
        .getAttributeValue(XMLTags.ATTR_EML_ID);
    Matcher nrMatcher = REGION_NR_PATTERN_230.matcher(gebietsIdStr);
    if (!nrMatcher.matches()) {
      throw new ImportException(Messages.bind(MessageKeys.Error_FormatGebietsnummerUngueltig_0_1,
          gebietsIdStr,
          "(\\d+)|(I|II|III)")); //$NON-NLS-1$
    }
    int gebietsNr = -1;
    if (nrMatcher.group(1) != null) {
      gebietsNr = Integer.parseInt(gebietsIdStr);
    } else {
      if ("I".equals(gebietsIdStr)) { //$NON-NLS-1$
        gebietsNr = 1;
      }
      if ("II".equals(gebietsIdStr)) { //$NON-NLS-1$
        gebietsNr = 2;
      }
      if ("III".equals(gebietsIdStr)) { //$NON-NLS-1$
        gebietsNr = 3;
      }
    }
    // TODO: Consider identifer with HSB/CSB etc.
    // int gebietsNr = Integer.parseInt(gebietsIdStr.substring(3));
    return gebietsNr;
  }

  static final Pattern REGION_NR_PATTERN_510 = Pattern
      .compile("(HSB(\\d+))|((HSB\\d+::)?(\\d{4}))|(((HSB\\d+::)?\\d{4}::)?SB(\\d+))"); //$NON-NLS-1$

  /**
   * Read region number from eml510
   * 
   * @param ergebnis element <ReportingUnitVotes>
   * @return
   * @throws ImportException
   */
  public static int readGebietsnummer510(Element ergebnis) throws ImportException {
    String gebietsIdStr = ergebnis.getFirstChildElement(XMLTags.EML_REPORTING_UNIT_IDENTIFIER,
        NS_EML).getAttributeValue(XMLTags.ATTR_EML_ID);
    Matcher nrMatcher = REGION_NR_PATTERN_510.matcher(gebietsIdStr);
    if (!nrMatcher.matches()) {
      throw new ImportException(Messages.bind(MessageKeys.Error_FormatGebietsnummerUngueltig_0_1,
          gebietsIdStr,
          "(HSB(\\d+))|((HSB\\d+::)?(\\d{4}))|(((HSB\\d+::)?\\d{4}::)?SB(\\d+))")); //$NON-NLS-1$
    }
    // find the last number
    for (int idx = 1; idx <= nrMatcher.groupCount(); ++idx) {
      if (nrMatcher.group(idx) != null) {
        gebietsIdStr = nrMatcher.group(idx);
      }
    }
    int gebietsNr = Integer.parseInt(gebietsIdStr);
    return gebietsNr;
  }

  /**
   * Evaluate xml-element and search for the corresponding Gruppe-object
   * 
   * @param ergebnis xml element <Selection>
   * @return Gruppe object
   * @throws ImportException
   */
  GruppeComposite findGruppe(Element ergebnis) throws ImportException {
    int gruppenSchluessel = XMLImportHelper.getAttributeIntValue(ergebnis
        .getFirstChildElement(XMLTags.EML_LISTEN_IDENTIFIER, NS_EML), XMLTags.ATTR_EML_ID);
    String id_Gruppe = _gruppeKeyMap.get(String.valueOf(gruppenSchluessel));
    GruppeComposite gruppe = _gruppeMap.get(id_Gruppe);
    if (gruppe == null) {
      throw new ImportException(
          Messages.bind(MessageKeys.Error_NichtDefiniert_GruppeMitSchluessel_0, gruppenSchluessel));
    }
    return gruppe;
  }

  /**
   * Create new list
   * 
   * @return id of list model object
   * @throws ImportException
   */
  String createListe(String identicalListIdent,
      String id_Gruppe,
      String listenTyp,
      boolean isGeschlechtSichtbar,
      String publicationLanguage,
      String name) throws ImportException {
    String id_Liste = getUniqueKey();
    ListeModel liste = new ListeModelImpl(id_Liste);
    liste.setID_Wahl(_wahlModel.getID_Wahl());
    liste.setID_Gruppe(id_Gruppe);
    liste.setTyp(listenTyp);
    liste.setGeschlechtSichtbar(isGeschlechtSichtbar);
    liste.setPublicationLanguage(publicationLanguage);
    liste.setName(name);
    if (identicalListIdent != null) {
      try {
        int satz = Integer.parseInt(identicalListIdent);
        liste.setSatz(satz);
      } catch (NumberFormatException e) {
        LOGGER.error("identicalListIdent = " + identicalListIdent + " is not an integer", e); //$NON-NLS-1$//$NON-NLS-2$
      }
    }
    addModel(liste);
    return id_Liste;
  }

  /**
   * Create list combination from xml element
   * 
   * @param listenkombinationKnoten xml element <Affiliation>
   * @throws ImportException
   */
  void checkOrCreateListenkombination(String id_Listenkombination) throws ImportException {
    if (!_listenkombinationen.contains(id_Listenkombination)) {
      // Create list combination object
      ListenkombinationModel lk = new ListenkombinationModelImpl(id_Listenkombination);
      lk.setID_Wahl(_wahlModel.getID_Wahl());
      lk.setBezeichnung(id_Listenkombination);
      // Read groups from <AffiliationIdentifier>-elements
      addModel(lk);
      _listenkombinationen.add(id_Listenkombination);
    }
  }

  /**
   * Evaluate xml element <AffiliationIdentifier> and get or create corresponding GruppeModel object
   * 
   * @param listenIdentifier xml element <AffiliationIdentifier>
   * @return GruppeModel object
   * @throws ImportException
   */
  GruppeComposite getOrCreateGruppe(Element listenIdentifier, String id_Listenkombination)
      throws ImportException {
    String gruppenNr = listenIdentifier.getAttributeValue(XMLTags.ATTR_EML_ID);
    try {
      Integer.parseInt(gruppenNr);
    } catch (NumberFormatException nfe) {
      throw new ImportException(Messages.bind(MessageKeys.Error_UngueltigerGruppenschluessel_0,
          listenIdentifier.getAttributeValue(XMLTags.ATTR_EML_ID)));
    }
    String id_Gruppe = _gruppeKeyMap.get(gruppenNr);
    if (id_Gruppe == null) {
      // Gruppe anlegen
      String gruppeName = listenIdentifier.getFirstChildElement(XMLTags.EML_GRUPPEN_NAME, NS_EML)
          .getValue();
      LOGGER.info(Messages.bind(MessageKeys.Result_Tracelog_Gruppe_0_WirdAngelegt, gruppeName));
      id_Gruppe = getUniqueKey();
      GruppeModel gruppe = new GruppeModelImpl(id_Gruppe);
      gruppe.setGruppenart(GruppeKonstanten.GRUPPENART_PARTEI);
      gruppe.setID_Wahl(_wahlModel.getID_Wahl());
      gruppe.setNameLang(gruppeName);

      if (gruppeName != null && !gruppeName.isEmpty()) {
        gruppe.setNameKurz(gruppeName);
      } else {
        String name = readFirstCandidateName(listenIdentifier);
        gruppe.setNameKurz(Messages.bind(MessageKeys.BlancListname, name));
      }

      gruppe.setSchluessel(Integer.parseInt(gruppenNr));
      gruppe.setID_Listenkombination(id_Listenkombination);
      _gruppeKeyMap.put(gruppenNr, id_Gruppe);
      addModel(gruppe);
      GruppeComposite wdGruppe = new GruppeComposite(gruppe);
      _gruppeMap.put(id_Gruppe, wdGruppe);
    }
    return _gruppeMap.get(id_Gruppe);
  }

  private String readFirstCandidateName(Element listenIdentifier) {
    Element affilation = (Element) listenIdentifier.getParent();
    Element candidate = affilation.getFirstChildElement(XMLTags.EML_CANDIDATE, NS_EML);
    Element firstCandidateName = candidate.getFirstChildElement(XMLTags.EML_CANDIDATE_NAME, NS_EML);
    Element firstCandidatePersonName = firstCandidateName
        .getFirstChildElement(XMLTags.PERSONENDATEN_NAME, NS_XNL);
    Element firstCandidateLastName = firstCandidatePersonName
        .getFirstChildElement(XMLTags.NAME_NACHNAME, NS_XNL);
    Element firstCandidateNamePrefix = firstCandidatePersonName
        .getFirstChildElement(XMLTags.NAME_PRAEFIX, NS_XNL);
    String name = firstCandidateLastName.getValue();
    if (firstCandidateNamePrefix != null) {
      name = firstCandidateNamePrefix.getValue() + " " + name; //$NON-NLS-1$
    }
    return name;
  }

  /**
   * Create objects for general groups (voters, valid, invalid and so on...)
   * 
   * @param isReferendum
   */
  void createGruppenAllgemein(boolean isReferendum) {
    boolean isEK = ElectionCategory.fromWahlart(_wahlModel.getWahlart()).isEK();
    for (GruppeKonstanten.GruppeAllgemein gruppeAllg : GruppeKonstanten.GruppeAllgemein.values()) {
      String id_Gruppe = getUniqueKey();
      GruppeModel gruppe = new GruppeModelImpl(id_Gruppe);
      gruppe.setGruppenart(GruppeKonstanten.GRUPPENART_ALLGEMEIN);
      gruppe.setID_Wahl(_wahlModel.getID_Wahl());
      gruppe.setNameLang(gruppeAllg.getName(isReferendum, isEK));
      gruppe.setNameKurz(gruppeAllg.kurzname);
      gruppe.setSchluessel(gruppeAllg.schluessel);
      _gruppeKeyMap.put(String.valueOf(gruppeAllg.schluessel), id_Gruppe);
      _models.add(gruppe);
    }
  }

  /**
   * Create region specific group objects
   * 
   * @param id_Gruppe id for group object
   * @param gebiet region object
   * @param id_Liste id for list object
   * @param position position on list
   * @return id for region specific group object
   * @throws ImportException
   */
  String createGruppeGebietsspezifisch(String id_Gruppe,
      GebietModel gebiet,
      String id_Liste,
      int position) throws ImportException {
    // Get or create superior object
    String id_UebergeordneteGG = _uegGgKeyMap.get(id_Gruppe);
    String id_Gebiet = gebiet.getID_Gebiet();
    boolean isRootRegion = id_Gebiet.equals(_wurzelgebiet.getID_Gebiet());
    if (isRootRegion && id_UebergeordneteGG != null) {
      return id_UebergeordneteGG;
    }
    if (id_UebergeordneteGG == null && !isRootRegion) {
      id_UebergeordneteGG = createGruppeGebietsspezifisch(id_Gruppe, _wurzelgebiet, null, position);
    }
    String id_Gg = getUniqueKey();
    GruppeGebietsspezifischModel gg = new GruppeGebietsspezifischModelImpl(id_Gg);
    gg.setID_Gebiet(id_Gebiet);
    gg.setID_Gruppe(id_Gruppe);
    gg.setID_Liste(id_Liste);
    if (!isRootRegion) {
      gg.setID_UebergeordneteGG(id_UebergeordneteGG);
    }
    if (id_Liste != null) {
      gg.setListeZugelassen(true);
    }
    gg.setPosition(position);
    addModel(gg);
    // create algorithm specific list object, for "real" groups only
    if (id_Liste != null) {
      GruppeComposite gruppe = _gruppeMap.get(id_Gruppe);
      gruppe.addListe(gruppe.new Liste(gg, gebiet));
    }
    if (isRootRegion) {
      _uegGgKeyMap.put(id_Gruppe, id_Gg);
      // If we have lists for the root region only, create Gg objects for all inferior regions
      if (id_Liste != null
          && GebietModel.GEBIETSARTEN_MIT_LISTEN.get(getElectionCategory()) <= _wurzelgebiet
              .getGebietsart()) {
        for (GebietModel tmpGebiet : _gebietsListe) {
          createGruppeGebietsspezifisch(id_Gruppe, tmpGebiet, id_Liste, position);
        }
      }
    }
    _ggKeyMap.put(new GruppeGebietsspezifischKey(id_Gruppe, id_Gebiet), id_Gg);
    return id_Gg;
  }

  private ElectionCategory getElectionCategory() {
    return ElectionCategory.fromWahlart(_wahlModel.getWahlart());
  }

  /**
   * Create general region specific groups
   * 
   * @throws ImportException
   */
  void createAllgemeineGruppeGebietsspezifisch() throws ImportException {
    boolean isEK = isEK();
    try {
      for (GebietModel gebiet : _gebietKeyMap.values()) {
        for (GruppeKonstanten.GruppeAllgemein gruppeAllg : GruppeKonstanten.GruppeAllgemein
            .values()) {
          createGruppeGebietsspezifisch(_gruppeKeyMap.get(String.valueOf(gruppeAllg.schluessel)),
              gebiet,
              null,
              gruppeAllg.getPosition(isEK));
        }
      }
    } catch (Exception e) {
      LOGGER.error(Messages.bind(MessageKeys.Error_FehlerBeimAnlegenDerAllgemeinenGruppen), e);
      throw new ImportException(
          Messages.bind(MessageKeys.Error_FehlerBeimAnlegenDerAllgemeinenGruppen), e);
    }
  }

  boolean isEK() {
    return (_wahlModel != null) && ElectionCategory.fromWahlart(_wahlModel.getWahlart()).isEK();
  }

  /**
   * Create GebietModel object
   * 
   * @param gebietsart
   * @param nummer
   * @return
   */
  GebietModel createGebietModelByArtAndNummer(int gebietsart, int nummer) {

    String key = gebietsart + "_" + nummer; //$NON-NLS-1$
    GebietModel gebiet = _gebietsMap.get(key);
    if (gebiet == null) {
      // Gebiet noch nicht gefunden
      gebiet = new GebietModelImpl(getUniqueKey());
      gebiet.setID_Wahl(_wahlModel.getID_Wahl());
      gebiet.setNummer(nummer);
      gebiet.setGebietsart(gebietsart);
      _gebietsMap.put(key, gebiet);
      addModel(gebiet);
    }
    return gebiet;
  }

  /**
   * Evaluate xml and create the personal data object for a candidate
   * 
   * @param bewerber xml element <Candidate> from candidate list EML230
   * @return id of Personendaten object
   * @throws ImportException
   */
  PersonendatenModel createBewerber(Element bewerber) throws ImportException {
    try {
      // Searching for reference resp. creating new object
      Element idNode = bewerber.getFirstChildElement(EML_CANDIDATE_IDENTIFIER, NS_EML);
      String shortcode = getText(idNode, XMLTags.ATTR_SHORTCODE, NS_EML);
      // if reference is found, no new data object will be created
      if (shortcode != null) {
        // check for corresponding data
        if (!_personenMap.containsKey(shortcode)) {
          throw new ImportException(TYPE_CONTENT,
              Messages.bind(MessageKeys.Error_KeinePersonendatenMitKey_0_Gefunden, shortcode));
        }
        return _personenMap.get(shortcode);
      }
      // Read short code to save data for future reference
      shortcode = getAttribute(idNode, XMLTags.ATTR_SHORTCODE);
      // Create personal data object
      Elements nodeList = bewerber.getChildElements(XMLTags.EML_CANDIDATE_NAME, NS_EML);
      if (nodeList.size() != 1) {
        throw new ImportException(TYPE_CONTENT,
            Messages
                .bind(MessageKeys.Error_KandidatMitKeyHatKeineOderMehrereCandidateFullNameElemente,
                    shortcode));
      }
      PersonendatenModel personModel = createPersonendatenModel(nodeList.get(0), shortcode);
      // gender
      String geschlecht = getText(bewerber, XMLTags.EML_GENDER, NS_EML);
      if (geschlecht != null) {
        personModel.setGeschlecht(PersonendatenKonstanten.Geschlecht.getId(geschlecht));
      } else {
        personModel.setGeschlecht(PersonendatenKonstanten.Geschlecht.KEINE_ANGABE.id);
      }
      // at the moment all candidates can be elected
      personModel.setBenennbar(true);
      // living address
      nodeList = bewerber.getChildElements(XMLTags.PERSONENDATEN_ADRESSE, NS_EML);
      if (nodeList.size() == 0) {
        String name = personModel.getNachname();
        if (personModel.getVorname() != null) {
          name += ", " + personModel.getVorname(); //$NON-NLS-1$
        }
        throw new ImportException(Messages.bind(MessageKeys.Error_Kandidat_0_HatKeineAdresse, name));
      } else {
        addLivingAddress2Person(personModel, nodeList.get(0));
      }
      // contact address
      nodeList = bewerber.getChildElements(XMLTags.PERSONENDATEN_KONTAKT, NS_EML);
      if (nodeList.size() > 0) {
        nodeList = nodeList.get(0).getChildElements(XMLTags.PERSONENDATEN_KONTAKTADRESSE, NS_EML);
        if (nodeList.size() > 0) {
          addContactAddress2Person(personModel, nodeList.get(0));
        }
      }
      // agent
      nodeList = bewerber.getChildElements(XMLTags.KANDIDAT_AGENT, NS_EML);
      if (nodeList.size() > 0) {
        PersonendatenModel agentModel = getAgent(nodeList.get(0));
        if (agentModel != null) {
          addModel(agentModel);
          personModel.setID_PersonendatenAgent(agentModel.getID_Personendaten());
        }
      }

      addModel(personModel);
      _personenMap.put(shortcode, personModel);
      return personModel;
    } catch (Exception e) {
      throw new ImportException(Messages.bind(MessageKeys.Error_FehlerBeimImportDerKandidaten)
          + ": " + e.getMessage(), e); //$NON-NLS-1$
    }
  }

  /**
   * Create a PersonendatenModel for an agent of a candidate using the &lt;Agent&gt; EML element
   * agentNode
   * 
   * @throws ImportException
   */
  private PersonendatenModel getAgent(Element agentNode) throws ImportException {
    Elements nodeList = agentNode.getChildElements(XMLTags.AGENT_IDENTIFIER, NS_EML);
    if (nodeList.size() == 0) {
      throw new ImportException(Messages.bind(MessageKeys.Error_AgentHatKeinenAgentIdentifier));
    }
    Element agentIdentifier = nodeList.get(0);
    nodeList = agentIdentifier.getChildElements(XMLTags.AGENT_NAME, NS_EML);
    if (nodeList.size() == 0) {
      throw new ImportException(Messages.bind(MessageKeys.Error_AgentHatKeinenNamen));
    }
    Element agentName = nodeList.get(0);
    PersonendatenModel personModel = createPersonendatenModel(agentName, null);

    personModel.setGeschlecht(PersonendatenKonstanten.Geschlecht.KEINE_ANGABE.id);
    personModel.setBenennbar(false);

    // living address
    nodeList = agentNode.getChildElements(XMLTags.AGENT_LIVING_ADRESSE, NS_KR);
    if (nodeList.size() == 0) {
      // no living address -> throw exceptions
      String name = personModel.getNachname();
      if (personModel.getVorname() != null) {
        name += ", " + personModel.getVorname(); //$NON-NLS-1$
      }
      throw new ImportException(Messages.bind(MessageKeys.Error_Agent_0_HatKeineWohnadresse, name));
    } else {
      // read living address
      Element livingAddress = nodeList.get(0);
      personModel.setLand(getText(livingAddress, XMLTags.ADRESSE_LAND_ID, NS_KR));
      personModel.setWohnort(getText(livingAddress, XMLTags.ADRESSE_WOHNORT, NS_KR));
    }

    // contact address
    nodeList = agentNode.getChildElements(XMLTags.PERSONENDATEN_KONTAKT, NS_EML);
    if (nodeList.size() > 0) {
      nodeList = nodeList.get(0).getChildElements(XMLTags.PERSONENDATEN_KONTAKTADRESSE, NS_EML);
      if (nodeList.size() > 0) {
        addContactAddress2Person(personModel, nodeList.get(0));
      }
    }

    return personModel;
  }

  /**
   * Read list posion from <CandidateIdentifier> element
   * 
   * @param bewerber
   * @return
   * @throws ImportException
   */
  public static int getListenplatz(Element bewerber) throws ImportException {
    Elements nodeList = bewerber.getChildElements(EML_CANDIDATE_IDENTIFIER, NS_EML);
    if (nodeList.size() != 1) {
      throw new ImportException(TYPE_CONTENT,
          Messages.bind(MessageKeys.Error_KandidatHatEineOderMehrereIdentifierElemente));
    }
    return XMLImportHelper.getAttributeIntValue(nodeList.get(0), XMLTags.ATTR_EML_ID);
  }

  /**
   * Create PersonendatenModel object from xml
   * 
   * @param personenname xml element <CandidateFullName>
   * @return PersonendatenModel object
   * @throws ImportException
   */
  PersonendatenModel createPersonendatenModel(Element personenname, String shortcode)
      throws ImportException {
    // Name node
    Elements nodeList = personenname.getChildElements(XMLTags.PERSONENDATEN_NAME, NS_XNL);
    if (nodeList.size() != 1) {
      throw new ImportException(TYPE_CONTENT,
          Messages.bind(MessageKeys.Error_KandidatHatKeineNamensdaten));
    }
    // Use shortcode as PK if it is set
    String id_Personendaten = shortcode == null ? getUniqueKey() : shortcode;
    PersonendatenModel datenModel = new PersonendatenModelImpl(id_Personendaten);
    Element name = nodeList.get(0);
    datenModel.setNachname(getText(name, XMLTags.NAME_NACHNAME, NS_XNL));
    datenModel.setVorname(getText(name, XMLTags.NAME_VORNAME, NS_XNL));
    datenModel.setTitel(getText(name, XMLTags.NAME_TITEL, NS_XNL));
    datenModel.setPraefix(getText(name, XMLTags.NAME_PRAEFIX, NS_XNL));
    datenModel.setGeneration(getText(name, XMLTags.NAME_GENERATION_ID, NS_XNL));
    // Read initials
    nodeList = name.getChildElements(XMLTags.NAME_NAMENSZUSATZ, NS_XNL);
    if (nodeList.size() > 0) {
      if (XMLTags.ATTR_VAL_NAME_TYPE_INITIAL.equals(getAttribute(nodeList.get(0),
          XMLTags.ATTR_NAME_TYPE))) {
        datenModel.setInitialen(getText(nodeList.get(0)));
      }
    }
    return datenModel;
  }

  /**
   * Read living address data from xml and add them to PersonendatenModel object
   * 
   * @param personModel
   * @param adresse xml element <QualifyingAddress>
   * @throws ImportException
   */
  void addLivingAddress2Person(PersonendatenModel personModel, Element adresse)
      throws ImportException {
    // Check for country node
    Element land = adresse.getFirstChildElement(XMLTags.PERSONENDATEN_ADRESSE_LAND, NS_XAL);
    Element ort;
    if (land != null) {
      personModel.setLand(getText(land, XMLTags.ADRESSE_LAND_ID, NS_XAL));
      ort = land.getFirstChildElement(XMLTags.PERSONENDATEN_ADRESSE_ORT, NS_XAL);
    } else {
      ort = adresse.getFirstChildElement(XMLTags.PERSONENDATEN_ADRESSE_ORT, NS_XAL);
    }
    if (ort == null) {
      throw new ImportException(Messages.bind(MessageKeys.Error_UngueltigeAdressdatenFuer_0,
          personModel.getNachname()));
    }
    personModel.setWohnort(getText(ort, XMLTags.ADRESSE_WOHNORT, NS_XAL));
  }

  /**
   * Read contact address data from xml and add them to PersonendatenModel object
   * 
   * @param personModel
   * @param adresse xml element <MailingAddress>
   * @throws ImportException
   */
  void addContactAddress2Person(PersonendatenModel personModel, Element adresse)
      throws ImportException {
    // Check for country node
    Element land = adresse.getFirstChildElement(XMLTags.PERSONENDATEN_ADRESSE_LAND, NS_XAL);
    Element ort;
    if (land != null) {
      personModel.setKontakt_Land(getText(land, XMLTags.ADRESSE_LAND_ID, NS_XAL));
      ort = land.getFirstChildElement(XMLTags.PERSONENDATEN_ADRESSE_ORT, NS_XAL);
    } else {
      ort = adresse.getFirstChildElement(XMLTags.PERSONENDATEN_ADRESSE_ORT, NS_XAL);
    }
    if (ort == null) {
      throw new ImportException(Messages.bind(MessageKeys.Error_UngueltigeAdressdatenFuer_0,
          personModel.getNachname()));
    }
    personModel.setKontakt_Wohnort(getText(ort, XMLTags.ADRESSE_WOHNORT, NS_XAL));
    personModel.setKontakt_Strasse(getText(ort, XMLTags.ADRESSE_STRASSE, NS_XAL));
    if (ort.getFirstChildElement(XMLTags.ADRESSE_PLZ, NS_XAL) != null) {
      personModel.setKontakt_PLZ(getText(ort.getFirstChildElement(XMLTags.ADRESSE_PLZ, NS_XAL),
          XMLTags.PLZ_NUMMER,
          NS_XAL));
    }
  }

  /**
   * All regions but GEMEINDE are sorted by number, GEMEINDE is sorted alphabetically
   * 
   * @param models
   */
  void setzeGebietspositionen(Collection<Model> models) {
    List<GebietModel> gebiete = new ArrayList<GebietModel>();
    for (Model model : models) {
      if (model instanceof GebietModel) {
        gebiete.add((GebietModel) model);
      }
    }
    Collections.sort(gebiete, new GebietModel.GebietsComparator());
    int pos = 1;
    int gebietsartAlt = -1;
    for (GebietModel gebiet : gebiete) {
      if (gebiet.getGebietsart() != gebietsartAlt) {
        pos = 1;
      }
      gebiet.setPosition(pos++);
      gebietsartAlt = gebiet.getGebietsart();
    }
  }

  protected static ElectionCategory getElectionCategory(WahlModel wahl) {
    if (wahl == null) {
      return ElectionCategory.NONE;
    }
    return ElectionCategory.fromWahlart(wahl.getWahlart());
  }

  /**
   * @param wahlartStr
   * @throws ImportException
   */
  protected static ElectionCategory getElectionCategory(String wahlartStr) throws ImportException {
    ElectionCategory result = ElectionCategory.fromValue(wahlartStr);
    if (result.equals(ElectionCategory.NONE)) {
      throw new ImportException(Messages.bind(MessageKeys.Error_UnbekannteWahlart_0, wahlartStr));
    }
    return result;
  }

  protected static int getGebietsart(String gebietStr) throws ImportException {
    for (Gebietsart gebietsart : Gebietsart.values()) {
      if (gebietsart.getKlartextExport().equals(gebietStr)) {
        return gebietsart.getId();
      }
    }
    throw new ImportException(Messages.bind(MessageKeys.Error_UnbekanntesGebiet) + ": " + gebietStr); //$NON-NLS-1$
  }

  /**
   * Determine region categories for this election from root region category and election category
   * 
   * @param gebietsartWurzel region category for root region
   * @return
   */
  int[] getGebietsarten(ElectionCategory electionCategory, int gebietsartWurzel) {
    int[] gebietsarten = new int[2];
    int[] alleGebiete = WAHLGEBIETSARTEN.get(electionCategory);
    for (int idx = 0; idx < alleGebiete.length; ++idx) {
      if (gebietsartWurzel == alleGebiete[idx] && idx + 1 < alleGebiete.length) {
        gebietsarten[0] = gebietsartWurzel;
        gebietsarten[1] = alleGebiete[idx + 1];
        break;
      }
    }
    LOGGER.info(Messages.bind(MessageKeys.Result_Tracelog_Gebietsarten,
        gebietsarten[0],
        gebietsarten[1]));
    return gebietsarten;
  }

  // Persistance related methods

  protected void addModel(Model model) {
    List<Model> list = _models;
    int idx = list.indexOf(model);
    // Wenn model schon drin, l�schen und an derselben Stelle wieder einf�gen
    if (idx > -1) {
      list.remove(model);
      list.add(idx, model);
    } else {
      list.add(model);
    }
  }

  /**
   * Container for adding and holding total votes
   * 
   * @author ugo@ivu.de, IVU Traffic Technologies AG
   */
  class TotalVotes {
    // <id_Listenkandidatur, votes>
    Map<String, Integer> candidateVotes = new HashMap<String, Integer>();
    // <id_GruppeGebietsspezifisch, votes>
    Map<String, Integer> groupVotes = new HashMap<String, Integer>();

    void addCandidateVotes(String id_Listenkandidatur, int votes) {
      if (candidateVotes.get(id_Listenkandidatur) == null) {
        candidateVotes.put(id_Listenkandidatur, votes);
      } else {
        candidateVotes.put(id_Listenkandidatur, candidateVotes.get(id_Listenkandidatur) + votes);
      }
    }

    void addGroupRegionVotes(String id_GruppeGebietsspezifisch, int votes) {
      if (candidateVotes.get(id_GruppeGebietsspezifisch) == null) {
        candidateVotes.put(id_GruppeGebietsspezifisch, votes);
      } else {
        candidateVotes.put(id_GruppeGebietsspezifisch,
            candidateVotes.get(id_GruppeGebietsspezifisch) + votes);
      }
    }

  }

  static class GruppeGebietsspezifischKey {
    final String id_Gruppe;
    final String id_Gebiet;

    public GruppeGebietsspezifischKey(String id_Gruppe, String id_Gebiet) {
      this.id_Gruppe = id_Gruppe;
      this.id_Gebiet = id_Gebiet;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof GruppeGebietsspezifischKey) {
        return ((GruppeGebietsspezifischKey) o).id_Gebiet.equals(id_Gebiet)
            && ((GruppeGebietsspezifischKey) o).id_Gruppe.equals(id_Gruppe);
      }
      return false;
    }

    @Override
    public int hashCode() {
      return id_Gebiet.hashCode() + id_Gruppe.hashCode();
    }
  }
}
