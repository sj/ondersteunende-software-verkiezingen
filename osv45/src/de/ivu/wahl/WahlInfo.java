/*
 * WahlInfo
 * 
 * Created on 16.10.2003 Copyright (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl;

import static de.ivu.ejb.IVUBeanBase.findLocalHomeExt;
import static de.ivu.wahl.modell.GebietModel.GEBIETSART_BUND;
import static de.ivu.wahl.modell.WahlModel.WAHLART_KLARTEXT;
import static de.ivu.wahl.modell.WahlModel.WAHLGEBIETSARTEN;
import static java.lang.System.currentTimeMillis;
import static java.util.Calendar.YEAR;
import static java.util.Locale.GERMANY;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.wahl.GebietHierarchie.GebietKey;
import de.ivu.wahl.admin.PropertyHandling;
import de.ivu.wahl.dataimport.XMLTags;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ErgebniseingangKonstanten;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.Gebietsart;
import de.ivu.wahl.modell.WahlModel;
import de.ivu.wahl.modell.Wahldaten;
import de.ivu.wahl.modell.ejb.Ergebniseingang;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.Wahl;
import de.ivu.wahl.modell.ejb.WahlHome;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;
import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;
import de.ivu.wahl.wus.reportgen.EMLMessageType;
import de.ivu.wahl.wus.reportgen.FilenameProvider;
import de.ivu.wahl.wus.reportgen.ReportNameComponentsEml230b;
import de.ivu.wahl.wus.reportgen.ReportNameComponentsP0;
import de.ivu.wahl.wus.reportgen.ReportNameComponentsP4;
import de.ivu.wahl.wus.reportgen.ReportNameComponentsP5;
import de.ivu.wahl.wus.reportgen.RgConstants;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 * @author tdu@ivu.de, IVU Traffic Technologies AG
 */
public class WahlInfo implements Serializable, Cloneable {
  private static final long serialVersionUID = "$Id: WahlInfo.java,v 1.51 2011/02/10 09:14:41 jon Exp $" //$NON-NLS-1$
  .hashCode();
  private static final Category LOGGER = Log4J.configure(WahlInfo.class);
  static {
    LOGGER.info(Log4J.dumpVersion(WahlInfo.class, Log4J.extractVersion("$Revision: 1.51 $"))); //$NON-NLS-1$
  }

  private static final long UPDATE_INTERVAL = 10000; // 10 sec
  private static final int GEBIET_HASH_SIZE = 499;
  private static final Date __start = new Date();
  private static WahlInfo __wahlInfo = null;

  public static Date getStart() {
    return __start;
  }

  /**
   * @param c user context
   * @return
   * @throws EJBException
   */
  public static WahlInfo getWahlInfo(AnwContext c) throws EJBException {
    return getWahlInfo();
  }

  public static synchronized WahlInfo getWahlInfo(@SuppressWarnings("unused") String id_Wahl)
      throws EJBException {
    return getWahlInfo();
  }

  /**
   * Finding WahlInfo if only one Wahl object exists
   * 
   * @return WahlInfo
   * @throws EJBException
   */
  public static synchronized WahlInfo getWahlInfo() throws EJBException {
    if (__wahlInfo == null) {
      Collection<Wahl> wahlen;
      try {
        wahlen = ((WahlHome) findLocalHomeExt("Wahl")).findAll(); //$NON-NLS-1$
      } catch (FinderException fe) {
        throw new EJBException(fe);
      }
      // no election yet
      if (wahlen.size() == 0) {
        return null;
      }
      // this system does not support many elections
      if (wahlen.size() > 1) {
        throw new RuntimeException(
            Messages.getString(MessageKeys.Error_WahlInfoKannNichtErzeugtWerdenEsGibtMehrereWahlen));
      }
      Wahl wahl = wahlen.iterator().next();
      if (wahl.getStatus() != WahlModel.STATE_INITIAL
          && wahl.getStatus() != WahlModel.STATE_STEMDISTRICT_IMPORT) {
        __wahlInfo = new WahlInfo(wahl);
      } else {
        return new WahlInfo(wahl);
      }
    }
    return __wahlInfo;
  }

  private final transient Wahl _wahl;
  private WahlModel _wahlModel;
  private transient long _lastSync;

  /**
   * Privater Konstruktor zum Erzeugen von WahlInfo aus Daten einer Wahl. Wird nur von der
   * Factory-Methode verwendet.
   */
  private WahlInfo(Wahl wahl) {
    _wahl = wahl;
    fillGebietXRef();
    synchronize();
  }

  /**
   * Just in case: wenn eine Transaktion schief geht, kann es sein, da� der Zustand nicht mehr dem
   * DB-Zustand entspricht
   */
  private void condSynchronize() {
    if (_lastSync - currentTimeMillis() > UPDATE_INTERVAL) {
      synchronize();
    }
  }

  /**
   *  
   */
  public void synchronize() {
    _wahlModel = _wahl.getDetails();
    _lastSync = currentTimeMillis();
  }

  /**
   * @return Wahlergebnisart f�r die ausgewerteten Daten
   */
  public int getAktuelleWahlergebnisart() {
    return _wahlModel.getAktuelleWahlergebnisart();
  }

  public int getGebietsartAuswertungseinheit() {
    return _wahlModel.getGebietsartAuswertungseinheit();
  }

  /**
   * @return ID der Wahl
   */
  public String getID_Wahl() {
    return _wahlModel.getID_Wahl();
  }

  /**
   * @return ID des Wurzelgebietes
   */
  public String getID_Wurzelgebiet() {
    return _wahlModel.getID_Wurzelgebiet();
  }

  /**
   * @return the ElectionCategory. May be ElectionCategory.NONE.
   */
  public ElectionCategory getElectionCategory() {
    return ElectionCategory.fromWahlart(_wahlModel.getWahlart());
  }

  /**
   * @return the ElectionSubcategory. May be ElectionSubcategory.NONE.
   */
  public ElectionSubcategory getElectionSubcategory() {
    return ElectionSubcategory.fromEmlRepresentation(_wahlModel.getWahlkategorie());
  }

  /**
   * return name of root-region
   * 
   * @return
   */
  public String getNameWurzelgebiet() {
    Gebiet wurzelgebiet = _wahl.getWurzelgebiet();
    if (wurzelgebiet == null) {
      return StringUtils.EMPTY;
    }
    return " - " //$NON-NLS-1$
        + Gebietsart.getGebietsartKlartext(wurzelgebiet) + " " //$NON-NLS-1$
        + wurzelgebiet.getName();
  }

  /**
   * @return Name der Wahl
   */
  public String getWahlName() {
    return _wahlModel.getName();
  }

  /**
   * @return Kurzname der Wahl
   */
  public String getWahlNameKurz() {
    return _wahlModel.getID_Wahl();
  }

  /**
   * @return gibt das Wahl-EJB zur�ck.
   * @throws UnsupportedOperationException
   */
  public Wahl getWahl() throws UnsupportedOperationException {
    if (_wahl != null) {
      return _wahl;
    } else {
      throw new UnsupportedOperationException(
          Messages.getString(MessageKeys.Error_Wahl_getWahIstNurAufDerServerseiteVorhanden));
    }
  }

  /**
   * Gibt die wahlspezifische Auswahl der verwendeten Gebiete.
   * <p>
   * Um die Klartextversion f�r die Ebenen zu erhalten, bitte folgenden Konstrukt verwenden:
   * </p>
   * 
   * <pre>
   * int[] wahlGebietsarten = wahlInfo.getGebietsarten();
   * String klartext = GebietModel.GEBIETSART_KLARTEXT[wahlGebietsarten[ebene]];
   * </pre>
   * 
   * @return Eine Array von Gebietsarten, die in dieser Wahl vorkommen, angefangen mit dem
   *         Wahlgebiet
   */
  public int[] getGebietsarten() {
    return WAHLGEBIETSARTEN.get(getElectionCategory());
  }

  // Holding Metadata and voting results for the last Ergebniseingang
  // Used for the algorithm
  private Wahldaten _wahldaten = null;

  public Wahldaten getWahldaten() {
    return _wahldaten;
  }

  public void setWahldaten(Wahldaten wahldaten) {
    _wahldaten = wahldaten;
  }

  private Map<GebietKey, GebietModel> _gebiet2Model;
  private Map<GebietKey, GebietModel> _gebiet2ModelUebergeordnet;
  private Map<String, GebietKey> _gebietId2Key;
  private Map<String, GebietModel> _gebietId2Model;
  private Map<String, GebietModel> _gebietId2ModelUebergeordnet;
  private int _gebietsartErfassungseinheitMax = GEBIETSART_BUND;
  private final int _gebietsartErfassungseinheitMin = GebietModel.GEBIETSART_STIMMBEZIRK;
  private int _gebietsartWurzelgebiet;
  private int _gebietsnummerWurzelgebiet;

  private String _fileString;

  public GebietModel getModel4Gebiet(int gebietsart, int gebietsnummer) {
    return _gebiet2Model.get(new GebietHierarchie.GebietKey(gebietsart, gebietsnummer));
  }

  public GebietModel getModel4Gebiet(String id_Gebiet) {
    return _gebietId2Model.get(id_Gebiet);
  }

  public String getName4Gebiet(int gebietsart, int gebietsnummer) {
    GebietModel model = getModel4Gebiet(gebietsart, gebietsnummer);
    return model == null ? null : model.getName();
  }

  public String getID4Gebiet(int gebietsart, int gebietsnummer) {
    GebietModel model = getModel4Gebiet(gebietsart, gebietsnummer);
    return model == null ? null : model.getID_Gebiet();
  }

  public GebietModel getModelUebergeordnet4Gebiet(int gebietsart, int gebietsnummer) {
    return _gebiet2ModelUebergeordnet
        .get(new GebietHierarchie.GebietKey(gebietsart, gebietsnummer));
  }

  public GebietModel getModelUebergeordnet4Gebiet(String id_Gebiet) {
    return _gebietId2ModelUebergeordnet.get(id_Gebiet);
  }

  public String getNameUebergeordnet4Gebiet(int gebietsart, int gebietsnummer) {
    GebietModel model = getModelUebergeordnet4Gebiet(gebietsart, gebietsnummer);
    return model == null ? null : model.getName();
  }

  public String getIDUebergeordnet4Gebiet(int gebietsart, int gebietsnummer) {
    GebietModel model = getModelUebergeordnet4Gebiet(gebietsart, gebietsnummer);
    return model == null ? null : model.getID_Gebiet();
  }

  public GebietHierarchie.GebietKey getGebietKey4Gebiet(String id_Gebiet) {
    return _gebietId2Key.get(id_Gebiet);
  }

  /**
   * Gibt gebietsartErfassungseinheitMax zur�ck.
   * 
   * @return gebietsartErfassungseinheitMax.
   */
  public int getGebietsartErfassungseinheitMax() {
    return _gebietsartErfassungseinheitMax;
  }

  /**
   * Gibt gebietsartErfassungseinheitMin zur�ck.
   * 
   * @return gebietsartErfassungseinheitMin.
   */
  public int getGebietsartErfassungseinheitMin() {
    return _gebietsartErfassungseinheitMin;
  }

  /**
   * Gibt gebietsartWurzelgebiet zur�ck.
   * 
   * @return gebietsartWurzelgebiet.
   */
  public int getGebietsartWurzelgebiet() {
    return _gebietsartWurzelgebiet;
  }

  /**
   * The region category where you will find lists for this election
   * 
   * @return gebietsartWurzelgebiet.
   */
  public int getGebietsartMitListen() {
    return GebietModel.GEBIETSARTEN_MIT_LISTEN.get(getElectionCategory());
  }

  /**
   * The region category where you will input data
   * 
   * @return gebietsartErfassungseinhaeit.
   */
  public int getGebietsartErfassungseinheit() {
    return _wahlModel.getGebietsartErfassungseinheit();
  }

  /**
   * Gibt gebietsnummerWurzelgebiet zur�ck.
   * 
   * @return gebietsnummerWurzelgebiet.
   */
  public int getGebietsnummerWurzelgebiet() {
    return _gebietsnummerWurzelgebiet;
  }

  private synchronized void fillGebietXRef() throws EJBException {
    if (_gebiet2Model == null) {
      Map<GebietKey, GebietModel> gebiet2Model = new HashMap<GebietKey, GebietModel>(
          GEBIET_HASH_SIZE);
      _gebiet2ModelUebergeordnet = new HashMap<GebietKey, GebietModel>(GEBIET_HASH_SIZE);
      _gebietId2Key = new HashMap<String, GebietKey>(GEBIET_HASH_SIZE);
      _gebietId2Model = new HashMap<String, GebietModel>(GEBIET_HASH_SIZE);
      _gebietId2ModelUebergeordnet = new HashMap<String, GebietModel>(GEBIET_HASH_SIZE);
      _wahl.readLock();
      List<Gebiet> gebiete = new ArrayList<Gebiet>(_wahl.getGebietCol());
      for (Gebiet gebiet : gebiete) {
        GebietModel gebietModel = gebiet.getDetails();
        Gebiet gebietUbergeordnet = gebiet.getUebergeordnetesGebiet();
        int gebietsart = gebietModel.getGebietsart();
        if (gebietModel.isErfassungseinheit()) {
          if (gebietsart > _gebietsartErfassungseinheitMax) {
            _gebietsartErfassungseinheitMax = gebietsart;
          }
          if (gebietsart < _gebietsartErfassungseinheitMin) {
            _gebietsartErfassungseinheitMax = gebietsart;
          }
        }
        GebietHierarchie.GebietKey gebietKey = new GebietHierarchie.GebietKey(gebietsart,
            gebietModel.getNummer());
        String id_Gebiet = gebietModel.getID_Gebiet();
        gebiet2Model.put(gebietKey, gebietModel);
        _gebietId2Model.put(id_Gebiet, gebietModel);
        _gebietId2Key.put(gebietModel.getID_Gebiet(), gebietKey);
        if (gebietUbergeordnet != null) {
          _gebiet2ModelUebergeordnet.put(gebietKey, gebietUbergeordnet.getDetails());
          _gebietId2ModelUebergeordnet.put(id_Gebiet, gebietUbergeordnet.getDetails());
        }
      }
      _gebiet2Model = gebiet2Model;

      GebietModel wurzelgebietModel = _wahl.getWurzelgebiet().getDetails();
      _gebietsartWurzelgebiet = wurzelgebietModel.getGebietsart();
      _gebietsnummerWurzelgebiet = wurzelgebietModel.getNummer();
    }
  }

  @Override
  public String toString() {
    return _wahlModel.toString();
  }

  public int getStatus() {
    return _wahlModel.getStatus();
  }

  public boolean isFreigegeben() {
    return _wahlModel.getFreigegeben() != null;
  }

  public String getWahltermin() {
    return _wahl.getWahltermin();
  }

  /**
   * Gibt einen String zur�ck, der aus Wahlart und Wahljahr besteht, und keine Umlaute enth�lt, so
   * dass er f�r Datei- oder Verzeichnisnamen verwendet werden kann.
   * 
   * @return String aus Wahlart und Wahljahr, verwendbar f�r Dateinamen
   */
  public String getFileString() {
    if (_fileString == null) {
      String wahlart = WAHLART_KLARTEXT.get(getElectionCategory());
      Locale locale = GERMANY;
      wahlart = wahlart.toLowerCase(locale);
      wahlart = wahlart.replaceAll("�", "ae").replaceAll("�", "oe").replaceAll("�", "ue") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
          .replaceAll("�", "ss"); //$NON-NLS-1$ //$NON-NLS-2$
      Calendar calendar = Calendar.getInstance(locale);
      calendar.setTime(_wahlModel.getTermin());
      _fileString = wahlart + calendar.get(YEAR);
    }
    return _fileString;
  }

  /**
   * ShortCode is needed on CSB-Level for TK and PS2 elections only
   */
  public boolean useShortCode() {
    int level = SystemInfo.getSystemInfo().getWahlEbene();
    return (level == GebietModel.EBENE_CSB) && isElectionWithListGroups();
  }

  /**
   * @return if the election may be released
   */
  public boolean darfWahlFreigegebenWerden() {
    return isWahlVollstaendig() && !hasMissingSecondInput();
  }

  /**
   * @return if a region has a last result with status STATE_FIRST_RESULT_OK, STATE_WARNING or
   *         STATE_ERROR
   */
  private boolean hasMissingSecondInput() {
    Collection<Gebiet> gebiete = _wahl.getGebietCol();
    for (Gebiet gebiet : gebiete) {
      if (gebiet.isErfassungseinheit()) {
        Ergebniseingang letzterEingang = gebiet.getLetzterEingang();
        if (letzterEingang != null) {
          if (letzterEingang.getStatus() == ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK
              || letzterEingang.getStatus() == ErgebniseingangKonstanten.STATE_WARNING
              || letzterEingang.getStatus() == ErgebniseingangKonstanten.STATE_ERROR) {
            return true;
          }
        }
      }
    }

    return false;
  }

  /**
   * @return if the election results are complete
   */
  public boolean isWahlVollstaendig() {
    Gebiet wurzelgebiet = _wahl.getWurzelgebiet();
    return wurzelgebiet != null && wurzelgebiet.isVollstaendig();
  }

  /**
   * @return if the election type is referendum
   */
  public boolean isReferendum() {
    return getElectionCategory().isReferendum();
  }

  /**
   * @return if the election type is EK (Eerste Kamer)
   */
  public boolean isEK() {
    return getElectionCategory().isEK();
  }

  /**
   * @return <code>true</code>, if list groups may occur in this election. This is the case only for
   *         TK and PS2 elections, i.e. if a party may nominate different candidate lists in
   *         different regions
   */
  public boolean isElectionWithListGroups() {
    return getElectionSubcategory().isElectionWithListGroups();
  }

  /**
   * @return true, if postal vote office is a selectable option for stembureaus.
   */
  public boolean isPostalVoteOfficeSelectable() {
    if (getElectionCategory().hasPostalVotes()
        && getGebietsnummerWurzelgebiet() == XMLTags.MUNICIPALITY_WITH_POSTAL_VOTE_OFFICE
        && getGebietsartWurzelgebiet() == GebietModel.GEBIETSART_GEMEINDE) {
      return true;
    }
    return false;
  }

  /**
   * On some jsp pages the file name of the EML 230b message must be shown. This is usually an
   * information that is known to the ReportGenerator. But here we have to implement that
   * redundantly.
   */
  public String getEML110aFilename() {
    ElectionCategory electionCategory = getElectionCategory();

    ReportNameComponentsP0 nameComponentsP0 = new ReportNameComponentsP0();
    nameComponentsP0.setElectionIdentifier(getWahlNameKurz());
    nameComponentsP0.setElectionCategory(electionCategory);
    // nameComponentsP0.setElectionSubcategory(getElectionSubcategory());
    if (_wahl != null) {
      if (_wahl.getWurzelgebiet() != null
          && (ElectionCategory.GR.equals(electionCategory) || ElectionCategory.ER
              .equals(electionCategory))) {
        nameComponentsP0.setElectoralDistrict(_wahl.getWurzelgebiet().getName());
      } else {
        nameComponentsP0.setElectoralDistrict(Messages
            .getString(MessageKeys.placeholder_electoralDistrict));
      }
    }
    return nameComponentsP0.getEmlFileFirstName()
        + FilenameProvider.getConstantFileComponents(nameComponentsP0) + RgConstants.SUFFIX_EML_XML;
  }

  public String getEML230bFilename() {
    ElectionCategory electionCategory = getElectionCategory();

    ReportNameComponentsEml230b nameComponentsEml230b = new ReportNameComponentsEml230b();
    nameComponentsEml230b.setElectionIdentifier(getWahlNameKurz());
    nameComponentsEml230b.setElectionCategory(electionCategory);
    nameComponentsEml230b.setElectionSubcategory(getElectionSubcategory());
    if (_wahl != null) {
      if (_wahl.getWurzelgebiet() != null
          && (ElectionCategory.GR.equals(electionCategory) || ElectionCategory.ER
              .equals(electionCategory))) {
        nameComponentsEml230b.setElectoralDistrict(_wahl.getWurzelgebiet().getName());
      } else {
        nameComponentsEml230b.setElectoralDistrict(Messages
            .getString(MessageKeys.placeholder_electoralDistrict));
      }
    }
    return nameComponentsEml230b.getEmlFileFirstName()
        + FilenameProvider.getConstantFileComponents(nameComponentsEml230b)
        + RgConstants.SUFFIX_EML_XML;
  }

  public String getEML510Filename(boolean complete) {
    ReportNameComponentsP4 nameComponentsP4 = new ReportNameComponentsP4(complete);
    nameComponentsP4.setElectionIdentifier(getWahlNameKurz());
    nameComponentsP4.setElectionCategory(getElectionCategory());
    if (_wahl != null) {
      nameComponentsP4.setElectionDomain(_wahl.getElectionDomain());
      if (_wahl.getWurzelgebiet() != null
          && _wahl.getWurzelgebiet().getGebietsart() == GebietModel.GEBIETSART_GEMEINDE) {
        nameComponentsP4.setManagingAuthority(_wahl.getWurzelgebiet().getName());
      } else {
        nameComponentsP4.setManagingAuthority(Messages
            .getString(MessageKeys.placeholder_municipality));
      }
    }
    nameComponentsP4.setEmlType(EMLMessageType.EML510b);
    return nameComponentsP4.getEmlFileFirstName()
        + FilenameProvider.getConstantFileComponents(nameComponentsP4) + RgConstants.SUFFIX_EML_XML;
  }

  public String getEML510dFilename(boolean complete) {
    ReportNameComponentsP4 nameComponentsP4 = new ReportNameComponentsP4(complete);
    nameComponentsP4.setElectionIdentifier(getWahlNameKurz());
    nameComponentsP4.setElectionCategory(getElectionCategory());
    if (_wahl != null) {
      nameComponentsP4.setElectionDomain(_wahl.getElectionDomain());
      if (_wahl.getWurzelgebiet() != null) {
        nameComponentsP4.setManagingAuthority(_wahl.getWurzelgebiet().getName());
      }
    }
    nameComponentsP4.setEmlType(EMLMessageType.EML510d);
    return nameComponentsP4.getEmlFileFirstName()
        + FilenameProvider.getConstantFileComponents(nameComponentsP4) + RgConstants.SUFFIX_EML_XML;
  }

  public String getEML520Filename() {
    ReportNameComponentsP5 nameComponentsP5 = new ReportNameComponentsP5();
    nameComponentsP5.setElectionIdentifier(getWahlNameKurz());
    nameComponentsP5.setElectionCategory(getElectionCategory());
    if (_wahl != null) {
      if (_wahl.getWurzelgebiet() != null) {
        nameComponentsP5.setElectoralDistrict(_wahl.getWurzelgebiet().getName());
      }
    }
    return nameComponentsP5.getEmlFileFirstName()
        + FilenameProvider.getConstantFileComponents(nameComponentsP5) + RgConstants.SUFFIX_EML_XML;
  }

  public String getMissingExportFoldersForResultsZipMsgKey(PropertyHandling propertyHandling) {
    File exportFolder = propertyHandling.getFileProperty(Konstanten.PROP_EXPORT_FORMULAR_DIR);
    String overallExportPath = exportFolder.getPath().substring(0,
        exportFolder.getPath().lastIndexOf(File.separator) + 1);

    if (ElectionCategory.AB.equals(getElectionCategory())
        || ElectionCategory.PS.equals(getElectionCategory())
        || ElectionCategory.TK.equals(getElectionCategory())
        || ElectionCategory.EP.equals(getElectionCategory())) {
      // check for P4_HSB
      if (!new File(overallExportPath + "P4_HSB").isDirectory()) { //$NON-NLS-1$
        return "Export_FinalMessage_Warning_P4_HSB"; //$NON-NLS-1$
      }

    } else {
      // check for P4_PSB
      if (!new File(overallExportPath + "P4_PSB").isDirectory()) { //$NON-NLS-1$
        return "Export_FinalMessage_Warning_P4_PSB"; //$NON-NLS-1$
      }

    }

    return null;
  }
}
