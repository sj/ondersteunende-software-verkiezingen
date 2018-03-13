/*
 * ImportClientDb
 * 
 * Created on 21.01.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import static de.ivu.ejb.EJBUtil.getUniqueKey;
import static de.ivu.ejb.EJBUtil.lookupLocal;
import static de.ivu.wahl.dataimport.ImportUtil.readXMLRoot;
import static de.ivu.wahl.dataimport.XMLTags.NS_EML;
import static de.ivu.wahl.dataimport.XMLTags.NS_XAL;
import static de.ivu.wahl.dataimport.XMLTags.NS_XNL;
import static de.ivu.wahl.modell.GebietModel.EBENE_PSB;
import static de.ivu.wahl.modell.GebietModel.GEBIETSART_GEMEINDE;
import static de.ivu.wahl.modell.GebietModel.GEBIETSART_INSELGEMEINDE;
import static de.ivu.wahl.modell.GebietModel.GEBIETSART_ORTSTEIL;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Category;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;

import de.ivu.ejb.EJBUtil;
import de.ivu.ejb.bmp.Model;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.InputMode;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.SystemProperty;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.admin.PropertyHandling;
import de.ivu.wahl.admin.PropertyHandlingBean;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GruppeComposite;
import de.ivu.wahl.modell.GruppeKonstanten;
import de.ivu.wahl.modell.GruppeModel;
import de.ivu.wahl.modell.ListenkandidaturModel;
import de.ivu.wahl.modell.PersonendatenModel;
import de.ivu.wahl.modell.PublicationLanguage;
import de.ivu.wahl.modell.RepositoryModel;
import de.ivu.wahl.modell.WahlModel;
import de.ivu.wahl.modell.ejb.Wahl;
import de.ivu.wahl.modell.ejb.WahlHome;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.modell.impl.GruppeModelImpl;
import de.ivu.wahl.modell.impl.ListenkandidaturModelImpl;
import de.ivu.wahl.modell.impl.RepositoryModelImpl;
import de.ivu.wahl.util.XMLImportHelper;

/**
 * ImportClient implementation that imports the EML files into the database
 * 
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public class ImportClientDb extends AbstractImportClient {

  private static final String DEFAULT_TIME_OF_MEETING_T11 = "15:00"; //$NON-NLS-1$

  private static final Category LOGGER = Log4J.configure(ImportClientDb.class);

  private static boolean __importRunning;

  ImportHandling _impHandling = null;
  PropertyHandling _propertyHandling = null;

  /**
   * Import metadata and fill Wahldaten
   * 
   * @throws ImportException
   */
  public synchronized void run() throws ImportException {
    if (isImportRunning()) {
      throw new ImportException(Messages.bind(MessageKeys.Error_MetadatenImportLaeuft));
    }
    try {
      setImportRunning(true);
      createWahl();
      LOGGER.info(Messages.bind(MessageKeys.Result_Tracelog_WahlMitGebietenFertig));
      // create region specific group object for each group
      createAllgemeineGruppeGebietsspezifisch();
      createListen();
      setContestIdentifierProperties();
      finish();
      getImportHandling().createAdministrationValues(_wahlModel.getID_Wahl());
      LOGGER.info(Messages.bind(MessageKeys.Result_Tracelog_ImportErfolgreichBeendet));
      WahlInfo.getWahlInfo().synchronize();
    } finally {
      setImportRunning(false);
    }
  }

  private void setContestIdentifierProperties() throws ImportException {
    if (!ImportType.EML110A_AND_EML230.equals(_importMetadata.getImportType())) {
      return;
    }

    URL emlURL = _importMetadata.getEML230();
    try {
      Element eml230 = readXMLRoot(emlURL, XMLTags.EML);
      Element kandidaten = eml230.getFirstChildElement(XMLTags.KANDIDATEN, XMLTags.NS_EML);
      Element wahldaten = kandidaten.getFirstChildElement(XMLTags.EML_WAHL, XMLTags.NS_EML);
      Elements gebiete = wahldaten.getChildElements(XMLTags.EML_CONTEST, NS_EML);
      if (gebiete.size() != 1) {
        return;
      }
      Element contest = gebiete.get(0);
      Element contestIdentifier = contest.getFirstChildElement(XMLTags.EML_CONTEST_IDENTIFIER,
          XMLTags.NS_EML);
      Attribute gebietIDAttr = contestIdentifier.getAttribute(XMLTags.EML_ATTR_CONTEST_IDENTIFIER);
      if (gebietIDAttr == null) {
        return;
      }
      PropertyHandling propertyHandling = getPropertyHandling();
      propertyHandling.setProperty(Konstanten.KEY_ELECTORAL_DISTRICT_ID, gebietIDAttr.getValue());

      String contestName = XMLImportHelper.getText(contestIdentifier,
          XMLTags.EML_CONTEST_NAME,
          XMLTags.NS_EML);
      if (contestName != null) {
        propertyHandling.setProperty(Konstanten.KEY_ELECTORAL_DISTRICT_NAME, contestName);
      }
    } catch (ImportException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error(e, e);
      throw new ImportException(e);
    }
  }

  @Override
  protected void setParentRegionName(String parentRegionName) throws ImportException {
    PropertyHandling propertyHandling = getPropertyHandling();
    propertyHandling.setProperty(Konstanten.KEY_PARENT_REGION_NAME, parentRegionName);
  }

  @Override
  protected void setElectioalDistrictNameAndNumber(String parentRegionName, String idElterngebiet)
      throws ImportException {
    PropertyHandling propertyHandling = getPropertyHandling();
    propertyHandling.setProperty(Konstanten.KEY_ELECTORAL_DISTRICT_NAME, parentRegionName);
    propertyHandling.setProperty(Konstanten.KEY_ELECTORAL_DISTRICT_ID, idElterngebiet);
  }

  /**
   * Constructor
   * 
   * @param importMetadata
   */
  public ImportClientDb(IImportEML importMetadata) {
    super(importMetadata);
    setImportRunning(false);
  }

  private void finish() throws ImportException {
    initProperties();

    // Create other Objects
    flushModels(_models);
    // // set Names for blank lists
    // getImportHandling().updateGruppennamen();
    // Get Wahl object
    Wahl wahl;
    try {
      wahl = ((WahlHome) EJBUtil.findLocalHomeNoCache("Wahl")).findByPrimaryKey(_wahlModel //$NON-NLS-1$
          .getID_Wahl());
    } catch (Exception e) {
      throw new ImportException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0, "Wahl")); //$NON-NLS-1$
    }
    // Setting Election Region after committing regions
    wahl.setID_Wahlgebiet(_id_Wahlgebiet);
    wahl.setID_Wurzelgebiet(_wurzelgebiet.getID_Gebiet());
    wahl.setGeschlossenMetadaten(new Timestamp(System.currentTimeMillis()));
    wahl.setLetzteAenderung(new Timestamp(System.currentTimeMillis()));
    wahl.setGebietsartAuswertungseinheit(_wurzelgebiet.getGebietsart());
    int status;
    if (_importMetadata.getModus() == AbstractImportEML.MODE_DB_P4) {
      if ((_wurzelgebiet.getGebietsart() == GEBIETSART_ORTSTEIL
          || _wurzelgebiet.getGebietsart() == GEBIETSART_INSELGEMEINDE || _wurzelgebiet
          .getGebietsart() == GEBIETSART_GEMEINDE) && _importMetadata.getLevel() == EBENE_PSB) {
        // If root is GEMEENTE and level PSB import of STEMLOKAL is needed
        status = WahlModel.STATE_STEMDISTRICT_IMPORT;
      } else {
        status = WahlModel.STATE_METADATA_P4;
      }
    } else {
      status = WahlModel.STATE_METADATA_P5;
    }
    // For GEMEENTE Polling stations can be entered
    if (status != WahlModel.STATE_STEMDISTRICT_IMPORT) {
      wahl.setGeschlossenMetadaten(new Timestamp(System.currentTimeMillis()));
      // In case we found only one region, this must be "Erfassungseinheit"
      if (wahl.getGebietCol().size() == 1) {
        wahl.getWurzelgebiet().setErfassungseinheit(true);
        _gebietsartAwE = _wurzelgebiet.getGebietsart();
        wahl.setGebietsartErfassungseinheit(_wurzelgebiet.getGebietsart());
      }
    }

    wahl.setStatus(status);
  }

  private void initProperties() {
    RepositoryModel prop = new RepositoryModelImpl(EJBUtil.getUniqueKey());
    prop.setName(Konstanten.PROP_DOUBLE_INPUT);
    prop.setWert(InputMode.INPUT_MODE_DOUBLE.getProperty());
    _models.add(prop);
    prop = new RepositoryModelImpl(EJBUtil.getUniqueKey());
    prop.setName(Konstanten.PROP_IS_INPUT_MODE_COMPLETE);
    prop.setWert(String.valueOf(Boolean.TRUE));
    _models.add(prop);

    for (SystemProperty systemProperty : SystemProperty.values()) {
      prop = new RepositoryModelImpl(EJBUtil.getUniqueKey());
      prop.setName(systemProperty.getKey());
      prop.setWert(systemProperty.getInitialValue());
      _models.add(prop);
    }

    if (isEK()) {
      prop = new RepositoryModelImpl(EJBUtil.getUniqueKey());
      prop.setName(de.ivu.wahl.export.XMLTags.RG_TIME_OF_MEETING);
      prop.setWert(String.valueOf(DEFAULT_TIME_OF_MEETING_T11));
      _models.add(prop);
      prop = new RepositoryModelImpl(EJBUtil.getUniqueKey());
      prop.setName(de.ivu.wahl.export.XMLTags.RG_DATE_OF_MEETING);
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); //$NON-NLS-1$
      prop.setWert(dateFormat.format(_wahlModel.getTermin()));
      _models.add(prop);
    }
  }

  private void flushModels(List<Model> models) throws ImportException {
    LOGGER.debug(Messages.bind(MessageKeys.Result_Tracelog_CommittingBeans));
    try {
      getImportHandling().createEntities(models);
      models.clear();
    } catch (Exception e) {
      throw new ImportException(Messages.bind(MessageKeys.Error_FehlerBeiCommit), e);
    }
  }

  private ImportHandling getImportHandling() throws ImportException {
    try {
      if (_impHandling == null) {
        _impHandling = lookupLocal(ImportHandlingBean.class.getSimpleName());
      }
      return _impHandling;
    } catch (Exception e) {
      throw new ImportException(e);
    }
  }

  /**
   * @param importRunning neuer Wert f�r importRunning
   */
  private static synchronized void setImportRunning(boolean importRunning) {
    __importRunning = importRunning;
  }

  /**
   * @return importRunning.
   */
  private static synchronized boolean isImportRunning() {
    return __importRunning;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.AbstractImportClient#createReferendumListen()
   */
  @Override
  protected void createReferendumListen() throws ImportException {
    URL emlURL = _importMetadata.getDefinition();
    Element eml630 = readXMLRoot(emlURL, XMLTags.EML);
    Element optionsList = eml630.getFirstChildElement(XMLTags.EML_OPTIONS_LIST, XMLTags.NS_EML);
    Element wahldaten = optionsList.getFirstChildElement(XMLTags.EML_ELECTION, XMLTags.NS_EML);
    checkWahl(wahldaten.getFirstChildElement(XMLTags.EML_WAHL_IDENTIFIER, NS_EML),
        _wahlModel,
        emlURL.getFile());
    Element proposal = wahldaten.getFirstChildElement(XMLTags.EML_PROPOSAL, NS_EML);
    createReferendumQuestion(proposal);
    // iterate answers
    Element options = proposal.getFirstChildElement(XMLTags.EML_OPTIONS, NS_EML);
    Elements answers = options.getChildElements(XMLTags.EML_REFERENDUM_OPTION_IDENTIFIER, NS_EML);
    for (int i = 0; i < answers.size(); i++) {
      createReferendumAnswer(answers.get(i));
    }
  }

  /**
   * @param element Proposal
   * @throws ImportException
   */
  private void createReferendumQuestion(Element proposal) throws ImportException {
    Element proposalIdentifier = proposal.getFirstChildElement(XMLTags.EML_PROPOSAL_IDENTIFIER,
        NS_EML);
    Element proposalName = proposalIdentifier.getFirstChildElement(XMLTags.EML_PROPOSAL_NAME,
        NS_EML);
    String repositoryKey = Konstanten.KEY_REFERENDUM_TEXT;
    String questionText = proposalName.getValue();
    getPropertyHandling().setProperty(repositoryKey, questionText);
  }

  /**
   * @param element ReferendumOptionIdentifer
   * @throws ImportException
   */
  private void createReferendumAnswer(Element referendumOptionIdentifer) throws ImportException {
    String answerId = referendumOptionIdentifer.getAttribute(XMLTags.ATTR_EML_ID).getValue();
    String repositoryKey = Konstanten.KEY_REFERENDUM_ANSW + answerId;
    String answerText = referendumOptionIdentifer.getValue();
    getPropertyHandling().setProperty(repositoryKey, answerText);

    // build a candidate list
    GruppeComposite wdGruppe = createReferendumGruppe(answerId);
    GruppeModel gruppe = wdGruppe.getGruppe();
    String idListe = createListe(null,
        gruppe.getID_Gruppe(),
        XMLTags.LISTEN_TYP_IDENTISCH,
        false,
        PublicationLanguage.NL.getAbbreviation(),
        gruppe.getNameLang());

    // create region specific group data
    Set<String> idsGruppeGebietsspezifisch = new HashSet<String>();
    LOGGER.debug(Messages.bind(MessageKeys.Result_Tracelog_GGFuerGebiet_0,
        _wurzelgebiet.getBezeichnung()));
    String id = createGruppeGebietsspezifisch(gruppe.getID_Gruppe(),
        _wurzelgebiet,
        idListe,
        gruppe.getSchluessel());
    idsGruppeGebietsspezifisch.add(id);
    if (wdGruppe.getListen() == null) {
      throw new ImportException(
          Messages.bind(MessageKeys.Error_KeineListenInGruppeMitSchluessel_0_Gefunden,
              gruppe.getSchluessel()));
    }

    // build a candidate
    Element answerDummyCandidate = createReferendumDummyCandidate(answerId);
    ListenkandidaturModel listenkandidatur = new ListenkandidaturModelImpl(getUniqueKey());
    listenkandidatur.setID_Liste(idListe);
    listenkandidatur.setID_Wahl(_wahlModel.getID_Wahl());
    // list position for the candidate
    int listenplatz = getListenplatz(answerDummyCandidate);
    listenkandidatur.setListenplatz(listenplatz);
    // read personal data for the candidate and create Personendaten object
    PersonendatenModel personendaten = createBewerber(answerDummyCandidate);
    listenkandidatur.setID_Personendaten(personendaten.getID_Personendaten());
    addModel(listenkandidatur);
    // Adding candidate objects for algorithm for each list of the group
    for (GruppeComposite.Liste liste : wdGruppe.getListen()) {
      if (idListe.equals(liste.getId_Liste())) {
        liste.addKandidat(wdGruppe.new Listenkandidat(personendaten, listenplatz, listenkandidatur
            .getID_Listenkandidatur()));
      }
    }
  }

  private PropertyHandling getPropertyHandling() throws ImportException {
    try {
      if (_propertyHandling == null) {
        _propertyHandling = lookupLocal(PropertyHandlingBean.class.getSimpleName());
      }
      return _propertyHandling;
    } catch (Exception e) {
      throw new ImportException(e);
    }
  }

  /**
   * @param answerId
   * @return GruppeModel object
   * @throws ImportException
   */
  private GruppeComposite createReferendumGruppe(String gruppenNr) throws ImportException {
    try {
      Integer.parseInt(gruppenNr);
    } catch (NumberFormatException nfe) {
      throw new ImportException(Messages.bind(MessageKeys.Error_UngueltigerGruppenschluessel_0,
          gruppenNr));
    }
    String id_Gruppe = _gruppeKeyMap.get(gruppenNr);
    if (id_Gruppe == null) {
      // Gruppe anlegen
      String gruppeName = Messages.bind(MessageKeys.ReferendumAnswer, gruppenNr);
      LOGGER.info(Messages.bind(MessageKeys.Result_Tracelog_Gruppe_0_WirdAngelegt, gruppeName));
      id_Gruppe = getUniqueKey();
      GruppeModel gruppe = new GruppeModelImpl(id_Gruppe);
      gruppe.setGruppenart(GruppeKonstanten.GRUPPENART_PARTEI);
      gruppe.setID_Wahl(_wahlModel.getID_Wahl());
      gruppe.setNameLang(gruppeName);
      gruppe.setNameKurz(gruppeName);
      gruppe.setSchluessel(Integer.parseInt(gruppenNr));
      gruppe.setID_Listenkombination(null);
      _gruppeKeyMap.put(gruppenNr, id_Gruppe);
      addModel(gruppe);
      GruppeComposite wdGruppe = new GruppeComposite(gruppe);
      _gruppeMap.put(id_Gruppe, wdGruppe);
    }
    return _gruppeMap.get(id_Gruppe);
  }

  /**
   * @param answerId
   * @return eml candidate element
   */
  private Element createReferendumDummyCandidate(String answerId) {
    Element result = new Element(XMLTags.EML_CANDIDATE, NS_EML);

    // identifier
    Element identifier = new Element(XMLTags.EML_CANDIDATE_IDENTIFIER, NS_EML);
    identifier.addAttribute(new Attribute(XMLTags.ATTR_EML_ID, "1")); //$NON-NLS-1$
    identifier.addAttribute(new Attribute(XMLTags.ATTR_SHORTCODE, Messages
        .bind(MessageKeys.ReferendumAnswer, answerId)));

    // name
    Element fullName = new Element(XMLTags.EML_CANDIDATE_NAME, NS_EML);
    Element personName = new Element(XMLTags.PERSONENDATEN_NAME, NS_XNL);
    Element initials = new Element(XMLTags.NAME_NAMENSZUSATZ, NS_XNL);
    initials
        .addAttribute(new Attribute(XMLTags.ATTR_NAME_TYPE, XMLTags.ATTR_VAL_NAME_TYPE_INITIAL));
    Element firstName = new Element(XMLTags.NAME_VORNAME, NS_XNL);
    Element lastName = new Element(XMLTags.NAME_NACHNAME, NS_XNL);
    lastName.appendChild(Messages.bind(MessageKeys.ReferendumAnswer, answerId));
    personName.appendChild(initials);
    personName.appendChild(firstName);
    personName.appendChild(lastName);
    fullName.appendChild(personName);

    // gender
    Element gender = new Element(XMLTags.EML_GENDER, NS_EML);
    gender.appendChild("male"); //$NON-NLS-1$

    // address
    Element address = new Element(XMLTags.PERSONENDATEN_ADRESSE, NS_EML);
    Element locality = new Element(XMLTags.PERSONENDATEN_ADRESSE_ORT, NS_XAL);
    Element addressLine = new Element(XMLTags.ADRESSE_STRASSE, NS_XAL);
    Element localityName = new Element(XMLTags.ADRESSE_WOHNORT, NS_XAL);
    Element postalCode = new Element(XMLTags.ADRESSE_PLZ, NS_XAL);
    Element postcodeNumber = new Element(XMLTags.PLZ_NUMMER, NS_XAL);
    postalCode.appendChild(postcodeNumber);
    locality.appendChild(addressLine);
    locality.appendChild(localityName);
    locality.appendChild(postalCode);
    address.appendChild(locality);

    // result
    result.appendChild(identifier);
    result.appendChild(fullName);
    result.appendChild(gender);
    result.appendChild(address);
    return result;
  }

}
