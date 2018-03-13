/*
 * Konstanten
 * 
 * Copyright (c) 2002-11 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl;

import java.util.Locale;

import de.ivu.wahl.export.XMLTags;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.util.BundleHelper;

/**
 * Konstanten, die ueberall im System Verwendung finden
 * 
 * @author klie@ivu.de cos@ivu.de IVU Traffic Technologies AG
 */

public interface Konstanten {
  /** Version des Wahlabwicklungssystems */
  String VERSION_P4 = "2.21.4"; //$NON-NLS-1$
  String VERSION_P5 = "2.21.4"; //$NON-NLS-1$

  /** Datum aus dem CVS (beim Einchecken vergeben) */
  String CVS_DATUM = "$Date: 2018-01-31 14:16:53 +0100 (Mi, 31. Jan 2018) $"; //$NON-NLS-1$

  /** Aus CVS_DATUM automatisch gebildetes Datum in normaler Schreibweise */
  String DATUM = CVS_DATUM.substring(15, 17) + '.' + CVS_DATUM.substring(12, 14) + '.'
      + CVS_DATUM.substring(7, 11);

  /** Java Text-Encoding-Kennung */
  String ENCODING = "UTF-8"; //$NON-NLS-1$

  /** Value for all three RGB components */
  int DEFAULT_BACKGROUND_COLOR_GREY = 238;

  /**
   * Wenn diese Konstante <code>true</code> gesetzt ist, wird beim Import von Wahlkreisergebnissen
   * aus Dateien (insbesondere Replikation) das Dateidatum verwendet. --- Testing only ---
   */
  boolean USE_FILE_DATE = false;

  /** boolean if <code>true</code> export sub region results (input level) in EML 510 */
  boolean EXPORT_SUB_RESULTS = true;

  /** Konstante fuer undefinierte Werte. */
  int UNDEFINIERT = -1;

  /** Konstante zur Bezeichnung des ApplicationLogs */
  String APPLOG = "applog"; //$NON-NLS-1$

  /** Primaerschluessel des Systemanwenders */
  String SYSTEM_ANWENDERID = "SystemAnwenderID"; //$NON-NLS-1$

  /** Anwendername des Systemanwenders */
  String SYSTEM_ANWENDER = "SystemAnwender"; //$NON-NLS-1$

  // Repository-Eintraege

  /** Wird bei Aenderung von Rechten eines Anwenders gesetzt */
  String RECHTE_LAST_CHANGE = "rechte_aenderung"; //$NON-NLS-1$

  /**
   * Praefix des Propertynamen fuer die Speicherung der Entscheidung ueber die zu veroeffentlichende
   * Variante des Wahlausgangs; Propertyname bekommt den Primaerschluessel der betroffenen Wahl als
   * Suffix
   */
  String PROP_ENTSCHEIDUNG_PREFIX = "Entscheidung."; //$NON-NLS-1$

  /**
   * Property fuer das Basisverzeichnis, in das die Formulare und EML-Dateien Exportiert werden
   * erstellt wird
   */
  String PROP_LISTING_STYLESHEET_FILE = "listingStylesheet.file"; //$NON-NLS-1$

  /**
   * Property fuer das Basisverzeichnis, in das die Formulare und EML-Dateien exportiert werden
   */
  String PROP_EXPORT_FORMULAR_DIR = "export.formularDir"; //$NON-NLS-1$

  /**
   * System architecture: number of bits
   */
  String PROP_SYSTEM_ARCHITECTURE_BITS = "arch.bits"; //$NON-NLS-1$

  /** Property zum Ein- und Ausschalten der Replikation */
  String PROP_REPENABLE = "repenable"; //$NON-NLS-1$

  /**
   * Wenn <code>true</code>, werden Eingaben (ausser Replikationsnachrichten natuerlich!)
   * unterdrueckt, und es erfolgt die Anzeige, dass es sich um ein Replikat handelt.
   */
  String PROP_INPUTDISABLE = "inputdisable"; //$NON-NLS-1$

  /** Property fuer das Upload-Verzeichnis */
  String PROP_UPLOADDIR = "uploaddir"; //$NON-NLS-1$

  /** Property fuer die erwartete Nummer der Replikationsnachricht */
  String PROP_REPNR = "repnr"; //$NON-NLS-1$

  /** Property fuer die naechste zu schreibende Nummer bei Replikationsnachricht */
  String PROP_REPNRIN = "repnrin"; //$NON-NLS-1$

  /** Property for last choice of export format */
  String PROP_LAST_EXPORT_FORMAT = "LAST_EXPORT_FORMAT"; //$NON-NLS-1$

  /** Property for last choice of central counting */
  String PROP_EXPORTS_FOR_CENTRAL_COUNTING = "EXPORTS_FOR_CENTRAL_COUNTING"; //$NON-NLS-1$

  // Verlinkung einer externen URL 1
  /** Name der Property, welche die URL 1 enthaelt */
  String PROP_EXT_LINK_1 = "externlink_1"; //$NON-NLS-1$
  /** Name der Property, welche den Namen fuer Button 1 enthaelt */
  String PROP_EXT_LINK_BUTTON_1 = "externlinkbutton_1"; //$NON-NLS-1$

  // Verlinkung einer externen URL 2
  /** Name der Property, welche die URL 2 enthaelt */
  String PROP_EXT_LINK_2 = "externlink_2"; //$NON-NLS-1$
  /** Name der Property, welche den Namen fuer Button 2 enthaelt */
  String PROP_EXT_LINK_BUTTON_2 = "externlinkbutton_2"; //$NON-NLS-1$

  String PREFILL_DB = "prefillDB"; //$NON-NLS-1$

  // Properties describing the system status
  /** election level (authority) **/
  public static final String PROP_ELECTION_LEVEL = "Wahlebene"; //$NON-NLS-1$

  /** election mode **/
  public static final String PROP_ELECTION_MODE = "Wahlmodus"; //$NON-NLS-1$

  /** installation suffix **/
  public static final String PROP_INSTALLATION_SUFFIX = "InstallationSuffix"; //$NON-NLS-1$

  /** input of candidate votes required **/
  public static final String PROP_IS_INPUT_MODE_COMPLETE = "InputmodusComplete"; //$NON-NLS-1$

  /** The red part of the background color **/
  public static final String PROP_BACKGROUND_COLOR_RED = "BackgroundColorRed"; //$NON-NLS-1$

  /** The red part of the background color **/
  public static final String PROP_BACKGROUND_COLOR_GREEN = "BackgroundColorGreen"; //$NON-NLS-1$

  /** The red part of the background color **/
  public static final String PROP_BACKGROUND_COLOR_BLUE = "BackgroundColorBlue"; //$NON-NLS-1$

  /** double input required, see de.ivu.wahl.InputMode **/
  public static final String PROP_DOUBLE_INPUT = "DOUBLE_INPUT"; //$NON-NLS-1$

  /** Repositorykey for the Referendum text **/
  public static final String KEY_REFERENDUM_TEXT = "REFERENDUM_TEXT"; //$NON-NLS-1$
  /** Repositoryprekey for the Referendum answer **/
  public static final String KEY_REFERENDUM_ANSW = "REFERENDUM_ANSW_"; //$NON-NLS-1$

  /** Repository key for the id of the electoral district **/
  public static final String KEY_ELECTORAL_DISTRICT_ID = "ELECTORAL_DISTRICT_ID"; //$NON-NLS-1$

  /** Repository key for the name of the electoral district **/
  public static final String KEY_ELECTORAL_DISTRICT_NAME = "ELECTORAL_DISTRICT_NAME"; //$NON-NLS-1$

  /** Repository key for the name of the parent region the root region in a bigger context **/
  public static final String KEY_PARENT_REGION_NAME = "PARENT_REGION_NAME"; //$NON-NLS-1$

  /** Repository key for the name of the region where the CSB committee is located **/
  public static final String KEY_CSB_REGION_NAME = "CSB_REGION_NAME"; //$NON-NLS-1$

  final boolean psb = SystemInfo.getSystemInfo().getWahlEbene() == GebietModel.EBENE_PSB;
  final boolean referendum = WahlInfo.getWahlInfo() != null
      ? WahlInfo.getWahlInfo().isReferendum()
      : false;

  BasiseinstellungList PROP_ALLG_BASIS = new BasiseinstellungList() {
    private static final long serialVersionUID = -6161543905739882482L;
    {
      // Only for PSB
      if (psb && !referendum) {
        addBool(Konstanten.PROP_IS_INPUT_MODE_COMPLETE, "Input_Modus_info", //$NON-NLS-1$ 
            "Input_Modus_info2"); //$NON-NLS-1$
      }

      Basiseinstellung inputModeSetting = addInteger(Konstanten.PROP_DOUBLE_INPUT,
          "DOUBLE_INPUT_info", //$NON-NLS-1$
          "DOUBLE_INPUT_info2"); //$NON-NLS-1$
      inputModeSetting.setRegex("[123]"); //$NON-NLS-1$
      inputModeSetting.setRegexErrorMsgKey(MessageKeys.Error_IllegalInputModus_0);

      // Only for PSB
      if (psb && !referendum) {
        // Regular expression for the numbers 0 to 255
        String regex = "([1-9]?\\d|1\\d\\d|2[0-4]\\d)|25[0-5]"; //$NON-NLS-1$
        Basiseinstellung field = addInteger(Konstanten.PROP_BACKGROUND_COLOR_RED,
            "BackgroundColorRed_info", //$NON-NLS-1$ 
            "BackgroundColorRed_info2"); //$NON-NLS-1$
        field.setRegex(regex);
        field.setRegexErrorMsgKey(MessageKeys.Error_IllegalColorValue);
        field = addInteger(Konstanten.PROP_BACKGROUND_COLOR_GREEN, "BackgroundColorGreen_info", //$NON-NLS-1$ 
            "BackgroundColorGreen_info2"); //$NON-NLS-1$
        field.setRegex(regex);
        field.setRegexErrorMsgKey(MessageKeys.Error_IllegalColorValue);
        field = addInteger(Konstanten.PROP_BACKGROUND_COLOR_BLUE, "BackgroundColorBlue_info", //$NON-NLS-1$ 
            "BackgroundColorBlue_info2"); //$NON-NLS-1$
        field.setRegex(regex);
        field.setRegexErrorMsgKey(MessageKeys.Error_IllegalColorValue);
      }
    }
  };
  String PROP_ALLG_KEY = BundleHelper.getBundleString("DOUBLE_INPUT_titel"); //$NON-NLS-1$
  BasiseinstellungMultiMap PROP_ALLG = BasiseinstellungMultiMap.key(PROP_ALLG_KEY, PROP_ALLG_BASIS);

  BasiseinstellungList PROP_P22_1_D1_BASIS = new BasiseinstellungList() {
    private static final long serialVersionUID = -7774900510662017516L;
    {
      addDate(XMLTags.RG_DATE_OF_MEETING_O1P20, "Export_P22_1_D1_Datum"); //$NON-NLS-1$
      addTime(XMLTags.RG_TIME_OF_MEETINGP20, "Export_P22_1_D1_Uhrzeit"); //$NON-NLS-1$
    }
  };
  BasiseinstellungMultiMap PROP_P22_1_D1 = new BasiseinstellungMultiMap(
      "Export_P22_1_D1", PROP_P22_1_D1_BASIS); //$NON-NLS-1$

  BasiseinstellungList PROP_P22_2_BASIS = new BasiseinstellungList() {
    private static final long serialVersionUID = -7774900510662017516L;
    {
      addDate(XMLTags.RG_DATE_OF_MEETING_O1P20, "Export_P22_1_D1_Datum"); //$NON-NLS-1$
      addTime(XMLTags.RG_TIME_OF_MEETINGP20, "Export_P22_1_D1_Uhrzeit"); //$NON-NLS-1$
    }
  };
  BasiseinstellungMultiMap PROP_P22_2 = new BasiseinstellungMultiMap(
      "Export_P22_2_D1", PROP_P22_2_BASIS); //$NON-NLS-1$

  BasiseinstellungList PROP_U16_D1_BASIS = new BasiseinstellungList() {
    private static final long serialVersionUID = -7774900510662017516L;
    {
      addDate(XMLTags.RG_DATE_OF_MEETING_O1P20, "Export_U16_D1_Datum"); //$NON-NLS-1$
      addTime(XMLTags.RG_TIME_OF_MEETINGP20, "Export_U16_D1_Uhrzeit"); //$NON-NLS-1$
      addDate(XMLTags.RG_DATE_PUBLICATION_VOTE_VALUES, "Export_U16_D1_Date_Publication_Vote_Values"); //$NON-NLS-1$
      addString(XMLTags.RG_NUMBER_PUBLICATION_VOTE_VALUES,
          "Export_U16_D1_Number_Publication_Vote_Values"); //$NON-NLS-1$
    }
  };
  BasiseinstellungMultiMap PROP_U16_D1 = new BasiseinstellungMultiMap(
      "Export_U16_D1", PROP_U16_D1_BASIS); //$NON-NLS-1$

  // OSV 5-3: Protocol appendix

  BasiseinstellungList PROP_P22_1_APPENDIX_BASIS = new BasiseinstellungList() {
    private static final long serialVersionUID = -6161543905739882486L;
    {
      addTextarea(XMLTags.RG_OBJECTIONS, "Export_Protocol_Appendix_D2_Einspruch_CSB"); //$NON-NLS-1$
      addTextarea(XMLTags.RG_NOTES, "Export_Protocol_Appendix_D2_Anmerkung_CSB"); //$NON-NLS-1$
    }
  };
  BasiseinstellungMultiMap PROP_P22_1_APPENDIX = new BasiseinstellungMultiMap(
      "Export_P22_1_D3", PROP_P22_1_APPENDIX_BASIS); //$NON-NLS-1$

  BasiseinstellungList PROP_P22_2_APPENDIX_BASIS = new BasiseinstellungList() {
    private static final long serialVersionUID = -6161543905739882486L;
    {
      addTextarea(XMLTags.RG_OBJECTIONS, "Export_Protocol_Appendix_D2_Einspruch_CSB"); //$NON-NLS-1$
      addTextarea(XMLTags.RG_NOTES, "Export_Protocol_Appendix_D2_Anmerkung_CSB"); //$NON-NLS-1$
    }
  };
  BasiseinstellungMultiMap PROP_P22_2_APPENDIX = new BasiseinstellungMultiMap(
      "Export_P22_1_D3", PROP_P22_2_APPENDIX_BASIS); //$NON-NLS-1$

  // OSV 5-1: Letters of appointment

  BasiseinstellungList PROP_BENACHRICHTIGUNG_GEWAEHLTE_BASIS = new BasiseinstellungList() {
    private static final long serialVersionUID = -7774900510662017516L;
    {
      addDate(XMLTags.RG_DATE_OF_MEETING_O1P20, "Export_Gew_Ben_Date_CSB_meeting"); //$NON-NLS-1$
      addDate(XMLTags.RG_DATE_LETTER, "Export_Gew_Ben_Date_Letter").optional(); //$NON-NLS-1$
      addString(XMLTags.RG_PLACE_LETTER, "Export_Gew_Ben_Place_Letter").optional(); //$NON-NLS-1$
      addDate(XMLTags.RG_FEEDBACK_DATE, "Export_Gew_Ben_Rueckmeldung_bis_zum"); //$NON-NLS-1$
      addString(XMLTags.RG_CHAIRPERSON, "Export_Gew_Ben_Vorsitzender"); //$NON-NLS-1$
      addString(XMLTags.RG_REJECTION_ADDRESS,
          "Export_Gew_Ben_Adresse_bei_Ablehnung", "Export_Gew_Ben_Ablehnung"); //$NON-NLS-1$ //$NON-NLS-2$
      addZip(XMLTags.RG_REJECTION_POSTALCODE, "Export_Gew_Ben_PLZ_bei_Ablehnung").optional(); //$NON-NLS-1$
      addString(XMLTags.RG_REJECTION_LOCATION, "Export_Gew_Ben_Ort_bei_Ablehnung"); //$NON-NLS-1$
      addString(XMLTags.RG_ACCEPTANCE_ADDRESS,
          "Export_Gew_Ben_Adresse_bei_Annahme", "Export_Gew_Ben_Annahme").optional(); //$NON-NLS-1$ //$NON-NLS-2$
      addZip(XMLTags.RG_ACCEPTANCE_POSTALCODE, "Export_Gew_Ben_PLZ_bei_Annahme").optional(); //$NON-NLS-1$
      addString(XMLTags.RG_ACCEPTANCE_LOCATION, "Export_Gew_Ben_Ort_bei_Annahme").optional(); //$NON-NLS-1$
    }
  };

  BasiseinstellungMultiMap PROP_BENACHRICHTIGUNG_GEWAEHLTE = new BasiseinstellungMultiMap(
      "Export_Gew_Ben", PROP_BENACHRICHTIGUNG_GEWAEHLTE_BASIS); //$NON-NLS-1$

  BasiseinstellungList PROP_O3_D1_BASIS = new BasiseinstellungList() {
    private static final long serialVersionUID = -7774900510662017516L;
    {
      addDate(XMLTags.RG_DATE_OF_MEETING, "Export_P4_O3_D1_Datum"); //$NON-NLS-1$
      addTime(XMLTags.RG_TIME_OF_MEETING, "Export_P4_O3_D1_Time"); //$NON-NLS-1$
    }
  };
  BasiseinstellungMultiMap PROP_O3_D1 = new BasiseinstellungMultiMap(
      "Export_P4_O3", PROP_O3_D1_BASIS); //$NON-NLS-1$

  BasiseinstellungList PROP_OSV4_4_D1_BASIS = new BasiseinstellungList() {
    private static final long serialVersionUID = -7774900510662017516L;
    {
      addTextarea(XMLTags.RG_OBJECTIONS, "Export_P4_Appendix_O3_D1_Einspruch"); //$NON-NLS-1$
      addTextarea(XMLTags.RG_NOTES, "Export_P4_Appendix_O3_D1_Anmerkung"); //$NON-NLS-1$
    }
  };
  BasiseinstellungMultiMap PROP_OSV4_4_D1 = new BasiseinstellungMultiMap(
      "Export_P4_Appendix_O3", PROP_OSV4_4_D1_BASIS); //$NON-NLS-1$

  BasiseinstellungList PROP_OSV4_5_D1_BASIS = new BasiseinstellungList() {
    private static final long serialVersionUID = -7774900510662017516L;
    {
      addDate(XMLTags.RG_DATE_OF_MEETING, "Export_P4_T11_D1_Datum"); //$NON-NLS-1$
      addTime(XMLTags.RG_TIME_OF_MEETING, "Export_P4_T11_D1_Time"); //$NON-NLS-1$
    }
  };
  BasiseinstellungMultiMap PROP_OSV4_5_D1 = new BasiseinstellungMultiMap(
      "Export_P4_OSV4_5", PROP_OSV4_5_D1_BASIS); //$NON-NLS-1$

  BasiseinstellungList PROP_T11_D1_BASIS = new BasiseinstellungList() {
    private static final long serialVersionUID = -7774900510662017517L;
    {
      addDate(XMLTags.RG_DATE_OF_MEETING, "Export_P4_T11_D1_Datum"); //$NON-NLS-1$
      addTime(XMLTags.RG_TIME_OF_MEETING, "Export_P4_T11_D1_Time"); //$NON-NLS-1$
      addString(XMLTags.RG_CHAIRMAN_OF_COMMITTEE, "Export_P4_T11_D1_Chairman"); //$NON-NLS-1$
      addString(XMLTags.RG_MEMBER_OF_COMMITTEE_1, "Export_P4_T11_D1_Member_1"); //$NON-NLS-1$
      addString(XMLTags.RG_MEMBER_OF_COMMITTEE_2, "Export_P4_T11_D1_Member_2"); //$NON-NLS-1$
      addString(XMLTags.RG_MEMBER_OF_COMMITTEE_3, "Export_P4_T11_D1_Member_3"); //$NON-NLS-1$
    }
  };
  BasiseinstellungMultiMap PROP_T11_D1 = new BasiseinstellungMultiMap(
      "Export_P4_T11", PROP_T11_D1_BASIS); //$NON-NLS-1$

  BasiseinstellungList PROP_WRR83_D1_BASIS = new BasiseinstellungList() {
    private static final long serialVersionUID = -7774900510662017516L;
    {
      addDate(XMLTags.RG_DATE_OF_MEETING, "Export_P4_O3_D1_Datum"); //$NON-NLS-1$
      addTime(XMLTags.RG_TIME_OF_MEETING, "Export_P4_O3_D1_Time"); //$NON-NLS-1$
    }
  };
  BasiseinstellungMultiMap PROP_WRR83_D1 = new BasiseinstellungMultiMap(
      "Export_P4_Wrr83", PROP_WRR83_D1_BASIS); //$NON-NLS-1$

  BasiseinstellungList PROP_OSV4_6_D1_BASIS = new BasiseinstellungList() {
    private static final long serialVersionUID = -7774900510662017516L;
    {
      addTextarea(XMLTags.RG_OBJECTIONS, "Export_P4_Appendix_Wrr83_D1_Einspruch"); //$NON-NLS-1$
      addTextarea(XMLTags.RG_NOTES, "Export_P4_Appendix_Wrr83_D1_Anmerkung"); //$NON-NLS-1$
    }
  };
  BasiseinstellungMultiMap PROP_OSV4_6_D1 = new BasiseinstellungMultiMap(
      "Export_P4_Appendix_Wrr83", PROP_OSV4_6_D1_BASIS); //$NON-NLS-1$

  // moegliche Zustaende der Applikation
  /** Anwendung ist im Testzustand */
  int APPSTATE_TEST = 0;

  /** Anwendung ist im Produktionszustand */
  int APPSTATE_PRODUKTION = 1;

  /** Klartextentsprechungen der moeglichen Applikationszustaende */
  String[] APPSTATE_KLARTEXT = {"Test", "Produktion"}; //$NON-NLS-1$ //$NON-NLS-2$

  // ///////////////////////////////////////////////////////////////////////////////////////////////
  // Konstanten fuer die Datenbank-Queries
  // ///////////////////////////////////////////////////////////////////////////////////////////////

  /** Konstante fuer <code>true</code> in der Datenbank */
  String DB_TRUE = "1"; //$NON-NLS-1$

  /** Konstante fuer <code>false</code> in der Datenbank */
  String DB_FALSE = "0"; //$NON-NLS-1$

  /** HTML-Konstante fuer den Zeilenumbruch */
  String BR = "<br />"; //$NON-NLS-1$

  /**
   * Fest vorgegebene Locale, damit die Software auch bei falscher Betriebssystemeinstellung korrekt
   * funktioniert
   */
  Locale LOCALE = BundleHelper.getLocale();

  String PARTEINIVEAU = BundleHelper.getBundleString("Input_Modus_Parteiniveau"); //$NON-NLS-1$
  String KANDIDATENNIVEAU = BundleHelper.getBundleString("Input_Modus_Kandidatenniveau"); //$NON-NLS-1$
  String DOUBLE_INPUT = BundleHelper.getBundleString("Input_Modus_Double_Input"); //$NON-NLS-1$
  String SINGLE_INPUT = BundleHelper.getBundleString("Input_Modus_Single_Input"); //$NON-NLS-1$
  String FILE_INPUT_WITH_MANUAL_CONFIRMATION = BundleHelper
      .getBundleString("Input_Modus_File_Input_With_Manual_Confirmation"); //$NON-NLS-1$

  public final static String DEFAULT_USER_PASSWORD = "wachtwoord"; //$NON-NLS-1$

  public final static String DEFAULT_USER_NAME = "Verkiezingsleider"; //$NON-NLS-1$

  public final static String DEFAULT_USER_LOGINNAME = "osv"; //$NON-NLS-1$

  public final static String DEFAULT_USER_ID = "ADM"; //$NON-NLS-1$

}
