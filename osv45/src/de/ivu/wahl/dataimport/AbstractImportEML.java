/*
 * AbstractImportEML
 * 
 * Created on 02.10.2009
 * Copyright (c) 2009-2017 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import static de.ivu.wahl.dataimport.XMLTags.ANZAHL_SITZE;
import static de.ivu.wahl.dataimport.XMLTags.EML_AUTHORITY;
import static de.ivu.wahl.dataimport.XMLTags.EML_AUTHORITY_IDENTIFIER;
import static de.ivu.wahl.dataimport.XMLTags.EML_AUTHORITY_IDENTIFIER_P3_PREFIX;
import static de.ivu.wahl.dataimport.XMLTags.EML_WAHL_NAME;
import static de.ivu.wahl.dataimport.XMLTags.KR_CREATION_DATE;
import static de.ivu.wahl.dataimport.XMLTags.KR_NOMINIERUNGS_DATUM;
import static de.ivu.wahl.dataimport.XMLTags.KR_WAHL_DATUM;
import static de.ivu.wahl.dataimport.XMLTags.NS_EML;
import static de.ivu.wahl.dataimport.XMLTags.NS_KR;
import static de.ivu.wahl.dataimport.XMLTags.VORRANGSCHWELLE;
import static de.ivu.wahl.dataimport.XMLTags.WAHL_ART;
import static de.ivu.wahl.dataimport.XMLTags.WAHL_ATTRIBUT_WAHLID;
import static de.ivu.wahl.dataimport.XMLTags.WAHL_KATEGORIE;
import static de.ivu.wahl.util.XMLImportHelper.getAttribute;
import static de.ivu.wahl.util.XMLImportHelper.getDateTimeValue;
import static de.ivu.wahl.util.XMLImportHelper.getDateValue;
import static de.ivu.wahl.util.XMLImportHelper.getText;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;

import nu.xom.Attribute;
import nu.xom.Element;

import de.ivu.util.debug.Log4J;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.WahlModel;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.modell.impl.WahlModelImpl;
import de.ivu.wahl.util.XMLImportHelper;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;
import de.ivu.wahl.wus.xmlsecurity.IXmlDigestCreator;
import de.ivu.wahl.wus.xmlsecurity.SHAXmlDigestCreator;

/**
 * @author tdu@ivu.de, IVU Traffic Technologies AG
 */
public abstract class AbstractImportEML implements IImportEML {

  protected static final String SINGLE_SPACE = " "; //$NON-NLS-1$
  protected static final String NULL_STR = "null"; //$NON-NLS-1$
  protected static final String EMPTY_STR = ""; //$NON-NLS-1$
  protected static final String LF = "\n"; //$NON-NLS-1$

  protected static final int TEILINDEX = 5;

  protected static final Logger LOGGER = Logger.getLogger(ImportElectionMetadata.class);
  protected static final Category APP_LOGGER = Log4J.configure(Konstanten.APPLOG);
  protected static IXmlDigestCreator _hashWertErmittlung = new SHAXmlDigestCreator();

  /** This is program P4 */
  public final static int MODE_DB_P4 = 0;

  /** This is program P5 */
  public final static int MODE_DB_P5 = 1;

  /** The software is running test cases */
  public final static int MODE_STANDALONE = 2;

  public final static int STATUS_INIT = 0;
  public final static int STATUS_URL_KOMPLETT = 1;
  public final static int STATUS_ELECTIONDEFINITION_ACCEPTED = 2;
  public final static int STATUS_HASH_CHECKED = 3;
  public final static int STATUS_LEVEL_KOMPLETT = 4;
  public final static int STATUS_KOMPLETT = 5;
  public final static int STATUS_ERROR = 6;

  public static class UploadStreamHandler extends URLStreamHandler {

    Map<String, byte[]> _map = new HashMap<String, byte[]>();

    public URL add(String host, String name, byte[] input) throws MalformedURLException {
      URL url = new URL("upload", host, -1, name, this); //$NON-NLS-1$
      _map.put(url.toExternalForm(), input);
      return url;
    }

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
      return new URLConnection(u) {

        @Override
        public void connect() throws IOException {
          connected = true;
        }

        @Override
        public InputStream getInputStream() throws IOException {
          return new ByteArrayInputStream(_map.get(url.toExternalForm()));
        }
      };
    }
  }

  protected ImportType _importType = null;
  protected URL _definition = null;
  protected boolean _definitionAccepted = false;
  protected String _hashWertDefinition = null;

  protected URL _EML230 = null;
  protected HashCodeSplitter _hashCodeSplitter230 = new HashCodeSplitter();

  protected URL _EML510 = null;
  protected HashCodeSplitter _hashCodeSplitter510 = new HashCodeSplitter();

  protected WahlModel _electionDetails = null;

  protected SecurityLevel _securityLevel = SecurityLevel.ASK_FOR_HASH_CODE;

  protected final int _modus;
  protected final int _level;
  protected ElectionCategory _electionCategory = ElectionCategory.NONE;
  protected String _electionDomain = null;
  protected String _electionDomainId = null;
  protected int _gebietsart = -1;
  protected int _gebietsNr = -1;
  protected List<GebietModel> _gebietsauswahl = new ArrayList<GebietModel>();
  protected int _status = STATUS_INIT;
  protected String _fehlermeldung = EMPTY_STR;

  protected AbstractImportEML(int modus, int level) {
    _modus = modus;
    _level = level;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getImportType()
   */
  @Override
  public ImportType getImportType() {
    return _importType;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#setImportType(de.ivu.wahl.dataimport.ImportType)
   */
  @Override
  public void setImportType(ImportType importType) {
    this._importType = importType;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getDefinition()
   */
  @Override
  public URL getDefinition() {
    return _definition;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#setDefinition(java.net.URL)
   */
  @Override
  public void setDefinition(URL definition) {
    _definition = definition;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#isDefinitionAccepted()
   */
  @Override
  public boolean isDefinitionAccepted() {
    return _definitionAccepted;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#setDefinitionAccepted(boolean)
   */
  @Override
  public void setDefinitionAccepted(boolean definitionAccepted) {
    _definitionAccepted = definitionAccepted;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getEML230()
   */
  @Override
  public URL getEML230() {
    return _EML230;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#setEML230(java.net.URL)
   */
  @Override
  public void setEML230(URL eml230) {
    _EML230 = eml230;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getEML510()
   */
  @Override
  public URL getEML510() {
    return this._EML510;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#setEML510(java.net.URL)
   */
  @Override
  public void setEML510(URL eml510) {
    this._EML510 = eml510;
  }

  /*
   * Hashert getter/setter
   */
  @Override
  public String getHashWertWahldefinition() {
    return _hashWertDefinition;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#setHashWertWahldefinition(java.lang.String)
   */
  @Override
  public void setHashWertWahldefinition(String hashWert) {
    _hashWertDefinition = hashWert;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getHashWert230()
   */
  @Override
  public String getHashWert230() {
    return _hashCodeSplitter230.getHashCode();
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getHashWert230ToConfirm(int)
   */
  @Override
  public String getHashWert230ToConfirm(int index) {
    return _hashCodeSplitter230.getPartToConfirm(index);
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#setHashWert230Input(java.lang.String, int)
   */
  @Override
  public void setHashWert230Input(int index, String part) {
    _hashCodeSplitter230.setInput(index, calcTeilHashWert(part));
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getHashWert510()
   */
  @Override
  public String getHashWert510() {
    return _hashCodeSplitter510.getHashCode();
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getHashWert510ToConfirm(int)
   */
  @Override
  public String getHashWert510ToConfirm(int index) {
    return _hashCodeSplitter510.getPartToConfirm(index);
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#setHashWert510Input(java.lang.String, int)
   */
  @Override
  public void setHashWert510Input(int index, String part) {
    _hashCodeSplitter510.setInput(index, calcTeilHashWert(part));
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getWahlModel()
   */
  @Override
  public WahlModel getElectionDetails() {
    return _electionDetails;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#setWahlModel(de.ivu.wahl.modell.WahlModel)
   */
  @Override
  public void setElectionDetails(WahlModel wahlModel) {
    _electionDetails = wahlModel;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getSecurityLevel()
   */
  @Override
  public SecurityLevel getSecurityLevel() {
    return _securityLevel;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getModus()
   */
  @Override
  public int getModus() {
    return this._modus;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getLevel()
   */
  @Override
  public int getLevel() {
    return _level;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getElectionCategory()
   */
  @Override
  public ElectionCategory getElectionCategory() {
    return _electionCategory;
  }

  /*
   * (non-Javadoc)
   * @seede.ivu.wahl.dataimport.IImportEML#setElectionCategory(de.ivu.wahl.wus.electioncategory.
   * ElectionCategory)
   */
  @Override
  public void setElectionCategory(ElectionCategory electionCategory) {
    _electionCategory = electionCategory;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getElectionDomain()
   */
  @Override
  public String getElectionDomain() {
    return _electionDomain;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getElectionDomain()
   */
  @Override
  public String getElectionDomainId() {
    return _electionDomainId;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getGebietsart()
   */
  @Override
  public int getGebietsart() {
    return _gebietsart;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#setGebietsart(int)
   */
  @Override
  public void setGebietsart(int gebietsart) {
    _gebietsart = gebietsart;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getGebietsNr()
   */
  @Override
  public int getGebietsNr() {
    return _gebietsNr;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#setGebietsNr(int)
   */
  @Override
  public void setGebietsNr(int gebietsNr) {
    _gebietsNr = gebietsNr;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getGebietsauswahl()
   */
  @Override
  public List<GebietModel> getGebietsauswahl() {
    return _gebietsauswahl;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getStatus()
   */
  @Override
  public int getStatus() {
    return _status;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#setStatus(int)
   */
  @Override
  public void setStatus(int status) {
    _status = status;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#getFehlermeldung()
   */
  @Override
  public String getFehlermeldung() {
    return _fehlermeldung != null && !_fehlermeldung.isEmpty() ? _fehlermeldung : null;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#setFehlermeldung(java.lang.String)
   */
  @Override
  public void setFehlermeldung(String fehlermeldung) {
    _fehlermeldung = fehlermeldung;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.IImportEML#reset()
   */
  @Override
  public void reset() {
    _importType = null;
    _definition = null;
    _definitionAccepted = false;
    _hashWertDefinition = null;
    _EML230 = null;
    _hashCodeSplitter230 = new HashCodeSplitter();
    _EML510 = null;
    _hashCodeSplitter510 = new HashCodeSplitter();
    _electionCategory = ElectionCategory.NONE;
    _gebietsauswahl = null;
    _status = STATUS_INIT;
    _fehlermeldung = EMPTY_STR;
    _electionDetails = null;
  }

  private String calcTeilHashWert(String teil1) {
    if (teil1 == null || teil1.isEmpty()) {
      return null;
    } else {
      return teil1;
    }
  }

  static String readManagingAuthorityName(Element eml) {
    String result = null;
    String[] stringsToRemove = {EML_AUTHORITY_IDENTIFIER_P3_PREFIX};
    Element managingAuthority = eml.getFirstChildElement(EML_AUTHORITY, NS_EML);
    if (managingAuthority != null) {
      Element authorityIdentifier = managingAuthority
          .getFirstChildElement(EML_AUTHORITY_IDENTIFIER, NS_EML);
      if (authorityIdentifier != null) {
        result = authorityIdentifier.getValue();
        for (String s : stringsToRemove) {
          result = result.replace(s, ""); //$NON-NLS-1$
        }
      }
    }
    return result;
  }

  static WahlModel readElectionDetails(Element eml,
      Element wahldefinition,
      Element electionIdentifier) throws ImportException {

    String idWahl = getAttribute(electionIdentifier, WAHL_ATTRIBUT_WAHLID);
    WahlModel electionDetails = new WahlModelImpl(idWahl);

    // election name
    electionDetails.setName(getText(electionIdentifier, EML_WAHL_NAME, NS_EML));

    // election category
    Element art = electionIdentifier.getFirstChildElement(WAHL_ART, NS_EML);
    ElectionCategory electionCategory = AbstractImportClient.getElectionCategory(getText(art));
    electionDetails.setWahlart(electionCategory.getWahlart());
    electionDetails.setWahlkategorie(getText(electionIdentifier, WAHL_KATEGORIE, NS_KR));

    // election domain
    String electionDomain = readElectionDomain(electionIdentifier);
    electionDetails.setElectionDomain(electionDomain);

    // election domainId
    String electionDomainId = readElectionDomainId(electionIdentifier);
    electionDetails.setElectionDomainId(electionDomainId);

    // polling day
    Timestamp wahltermin = null;
    try {
      wahltermin = getDateValue(electionIdentifier.getFirstChildElement(KR_WAHL_DATUM, NS_KR));
    } catch (Exception e) {
      throw new ImportException(Messages.bind(MessageKeys.Error_UngueltigesDatumsFormat) + " " //$NON-NLS-1$
          + e.getMessage());
    }
    electionDetails.setTermin(wahltermin);

    // number of seats
    Element numberOfSeats = wahldefinition.getFirstChildElement(ANZAHL_SITZE, NS_KR);
    if (numberOfSeats != null) {
      int anzSitze = XMLImportHelper.getIntValue(numberOfSeats);
      electionDetails.setAnzahlSitze(anzSitze);
    }

    // preference threshold
    Element preferenceThreshold = wahldefinition.getFirstChildElement(VORRANGSCHWELLE, NS_KR);
    if (preferenceThreshold != null) {
      electionDetails.setVorrangschwelle(XMLImportHelper.getIntValue(preferenceThreshold));
    }

    // nomination date
    Element nominationDateElement = electionIdentifier.getFirstChildElement(KR_NOMINIERUNGS_DATUM,
        NS_KR);
    if (nominationDateElement != null) {
      Timestamp nominationDate = null;
      try {
        nominationDate = getDateValue(nominationDateElement);
      } catch (Exception e) {
        throw new ImportException(Messages.bind(MessageKeys.Error_UngueltigesDatumsFormat) + " " //$NON-NLS-1$
            + e.getMessage());
      }
      electionDetails.setDatumNominierung(nominationDate);
    }

    // creation timestamp
    Timestamp creationDate = null;
    try {
      creationDate = getDateTimeValue(eml.getFirstChildElement(KR_CREATION_DATE, NS_KR));
    } catch (Exception e) {
      throw new ImportException(Messages.bind(MessageKeys.Error_UngueltigesDatumsFormat) + " " //$NON-NLS-1$
          + e.getMessage());
    }
    electionDetails.setStandMetadaten(creationDate);

    return electionDetails;
  }

  /**
   * @return the value of the ElectionDomain element of the <code>electionIdentifier</code>
   */
  protected static String readElectionDomain(final Element electionIdentifier)
      throws ImportException {
    return getText(electionIdentifier, XMLTags.KR_ELECTION_DOMAIN, NS_KR);
  }

  /**
   * @return the Id attribute of the ElectionDomain element of the <code>electionIdentifier</code>
   */
  protected static String readElectionDomainId(Element electionIdentifier) {
    Element electionDomainElement = electionIdentifier
        .getFirstChildElement(XMLTags.KR_ELECTION_DOMAIN, NS_KR);
    if (electionDomainElement != null) {
      Attribute attribute = electionDomainElement.getAttribute(XMLTags.ELECTION_DOMAIN_ATTRIBUT_ID);
      if (attribute != null)
        return attribute.getValue();
    }
    return null;
  }

  @Override
  public void updateStatus() {
    switch (_modus) {
      case MODE_STANDALONE :
        updateStatusStandAlone();
        break;
      default :
        updateStatusDb();
        break;
    }
  }

  abstract protected void updateStatusStandAlone();

  abstract protected void updateStatusDb();

  abstract protected void updateSecurityLevel(Element eml);

  protected void appendLevelAndStatusAndCategoryAndFehlermeldung(StringBuilder sb) {
    sb.append(Messages.bind(MessageKeys.Result_Tracelog_Ebene)).append(_level).append(LF);
    sb.append(Messages.bind(MessageKeys.Result_Tracelog_Status)).append(_status).append(LF);
    sb.append(Messages.bind(MessageKeys.Result_Tracelog_Wahlart)).append(_electionCategory)
        .append(LF);
    sb.append(Messages.bind(MessageKeys.Result_Tracelog_Fehler)).append(_fehlermeldung).append(LF);
  }

}
