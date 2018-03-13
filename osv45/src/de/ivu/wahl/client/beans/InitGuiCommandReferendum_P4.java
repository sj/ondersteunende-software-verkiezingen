/*
 * InitGuiCommandTweedeKamerBund
 * 
 * Created on Oct 29, 2008
 * Copyright (c) 2008 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import static de.ivu.wahl.anwender.Rechte.R_ADM_ANGEMELDETE;
import static de.ivu.wahl.anwender.Rechte.R_ADM_ANW_AENDERN;
import static de.ivu.wahl.anwender.Rechte.R_ADM_ANW_ANLEGEN;
import static de.ivu.wahl.anwender.Rechte.R_ADM_EMPTY_EML_EXPORT;
import static de.ivu.wahl.anwender.Rechte.R_ADM_N10_1_EXPORT;
import static de.ivu.wahl.anwender.Rechte.R_ADM_PROPS;
import static de.ivu.wahl.anwender.Rechte.R_ADM_STIMMBEZIRKE_EDIT;
import static de.ivu.wahl.anwender.Rechte.R_EINGABE;
import static de.ivu.wahl.anwender.Rechte.R_IMPORT;
import static de.ivu.wahl.anwender.Rechte.R_RE_INDEX_DATABASE;
import static de.ivu.wahl.client.util.GUICommand.GUI_CLASS_1;
import static de.ivu.wahl.modell.GebietModel.GEBIETSART_GEMEINDE;
import static de.ivu.wahl.modell.GebietModel.GEBIETSART_STIMMBEZIRK;
import static de.ivu.wahl.modell.GebietModel.GEBIETSART_WAHLKREIS;

import java.util.Map;

import de.ivu.wahl.anwender.Rechte;
import de.ivu.wahl.client.util.GUICommand;
import de.ivu.wahl.modell.ErgebniseingangKonstanten;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * @author mur@ivu.de, IVU Traffic Technologies AG
 */
public class InitGuiCommandReferendum_P4 extends InitGuiCommand
    implements
      ApplicationBeanKonstanten {

  private final int _ebene;
  private final ElectionCategory _electionCategory;

  private static final int[][] WAHL_ART_LEVEL_CSB = {{GEBIETSART_WAHLKREIS}};
  private static final int[][] WAHL_ART_LEVEL_HSB = {{GEBIETSART_GEMEINDE}};
  private static final int[][] WAHL_ART_LEVEL_PSB = {{GEBIETSART_STIMMBEZIRK}};

  private static final int[][] ADMIN_WAHL_ART_LEVEL_CSB = {{GEBIETSART_WAHLKREIS}};
  private static final int[][] ADMIN_WAHL_ART_LEVEL_HSB = {{GEBIETSART_GEMEINDE}};
  private static final int[][] ADMIN_WAHL_ART_LEVEL_PSB = {{GEBIETSART_STIMMBEZIRK}};

  /**
   * @param jspMap Beziehung zwischen work-Kodierung und JSPs
   * @param ebene
   * @param gebietsartErfassungseinheitMax Gebietsart der hierarchisch <b>niedrigsten</b>
   *          Erfassungseinheit im Gebietsbaum
   * @param electionCategory
   */
  public InitGuiCommandReferendum_P4(Map<Integer, String> jspMap,
      int gebietsartErfassungseinheitMax,
      int ebene,
      ElectionCategory electionCategory) {
    super(jspMap, gebietsartErfassungseinheitMax);
    _ebene = ebene;
    _electionCategory = electionCategory;
  }

  @Override
  protected int[][] getWahlartLevels() {
    switch (_ebene) {
      case GebietModel.EBENE_CSB :
        return WAHL_ART_LEVEL_CSB;
      case GebietModel.EBENE_HSB :
        return WAHL_ART_LEVEL_HSB;
      case GebietModel.EBENE_PSB :
        return WAHL_ART_LEVEL_PSB;
      default :
        return WAHL_ART_LEVEL_CSB;
    }
  }

  @Override
  protected int[][] getAdminWahlartLevels() {
    switch (_ebene) {
      case GebietModel.EBENE_CSB :
        return ADMIN_WAHL_ART_LEVEL_CSB;
      case GebietModel.EBENE_HSB :
        return ADMIN_WAHL_ART_LEVEL_HSB;
      case GebietModel.EBENE_PSB :
        return ADMIN_WAHL_ART_LEVEL_PSB;
      default :
        return ADMIN_WAHL_ART_LEVEL_CSB;
    }
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.client.beans.InitGuiCommand#initBefehle(java.util.Map,
   * de.ivu.wahl.client.beans.InitGuiCommand.GUICommandList[],
   * de.ivu.wahl.client.beans.InitGuiCommand.GUICommandList[], int, java.lang.String,
   * java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  protected void initBefehle(Map<String, String> jspLevelWorkName,
      GUICommandList[] befehleInitial,
      GUICommandList[] befehle,
      int levelcount) {
    for (int level = 0; level < levelcount; level++) {
      befehleInitial[level] = new GUICommandArrayList();
      befehle[level] = new GUICommandArrayList();
    }
    initLevelUnabhaengig(jspLevelWorkName, befehleInitial);
    initLevelAdmin(jspLevelWorkName, befehleInitial);
  }

  /**
   * initLevelAdmin
   * 
   * @param jspLevelWorkName
   * @param befehleInitial
   */
  private void initLevelAdmin(Map<String, String> jspLevelWorkName, GUICommandList[] befehleInitial) {

    InitGuiCommandHelper helper = new InitGuiCommandHelper(this, jspLevelWorkName,
        befehleInitial[LEVEL_ADMIN], LEVEL_ADMIN);
    helper.setGuiClass(GUI_CLASS_1);

    helper.setRights(null);
    helper.addCommand(Command.ANWENDER_VERAENDERN_PASSWORT,
        "Passwort_veraendern", "Passwort_veraendern_titel", "adm_anwender_change_pw.jsp"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

    helper.setRights(R_ADM_ANGEMELDETE);
    helper
        .addCommand(Command.ADM_ANW_LISTE,
            "Angemeldete_Anwender_anzeigen", "Angemeldete_Anwender_anzeigen_titel", "adm_angemeldeteAnwender.jsp"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

    helper.setRights(R_ADM_ANW_ANLEGEN);
    helper.addCommand(Command.ANWENDER_ANLEGEN,
        "Anwender_anlegen", "Anwender_anlegen_titel", "adm_anwender_edit.jsp"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
    helper.setRights(R_ADM_ANW_AENDERN);
    helper.addCommand(Command.ANWENDER_VERAENDERN_1_AUSWAHLEN,
        "Anwender_veraendern", "Anwender_veraendern_titel", "adm_anwender_select.jsp"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

    // die zweite Seite in die HashMap
    helper.addJspPage(Command.ANWENDER_VERAENDERN_2_EDIT,
        "Anwender_veraendern", "adm_anwender_edit.jsp"); //$NON-NLS-1$//$NON-NLS-2$

    helper.setRights(R_ADM_ANW_ANLEGEN);
    helper.addCommand(Command.ANWENDER_LOESCHEN,
        "Anwender_loeschen", "Anwender_loeschen_titel", "adm_anwender_select.jsp"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

    if (GebietModel.EBENE_PSB == _ebene) {
      helper.setRights(R_ADM_STIMMBEZIRKE_EDIT);
      helper.addCommand(Command.ADM_STIMMBEZIRKE_EDIT,
          "Stimmbezirke_bearbeiten", "Stimmbezirke_bearbeiten_titel", "adm_stimmbezirke.jsp"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
    }

    helper.setRights(R_ADM_PROPS);
    helper.addCommand(Command.ADM_PROPS,
        "Grundeinstellungen_aendern", "Grundeinstellungen_aendern_titel", "adm_props.jsp"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

    helper.setRights(R_RE_INDEX_DATABASE);
    helper.addCommand(Command.ADM_RE_INDEX_DATABASE,
        "re_index_database", "re_index_database_titel", "adm_re_index_database.jsp"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

    final String titleKey;
    final String nameKey;
    if (GebietModel.EBENE_CSB == _ebene) {
      nameKey = "Export_P4_EmptyTotaaltelling"; //$NON-NLS-1$
      titleKey = "Export_P4_titel_EmptyTotaaltelling"; //$NON-NLS-1$
    } else {
      nameKey = "Export_P4_EmptyTelling"; //$NON-NLS-1$
      titleKey = "Export_P4_titel_EmptyTelling"; //$NON-NLS-1$
    }
    helper.setRights(R_ADM_EMPTY_EML_EXPORT);
    helper.addCommand(Command.ADM_EMPTY_EML_EXPORT, nameKey, titleKey, "adm_empty_export.jsp"); //$NON-NLS-1$

    if (GebietModel.EBENE_PSB == _ebene) {
      helper.setRights(R_ADM_N10_1_EXPORT);
      helper.addCommand(Command.ADM_N10_1_EXPORT,
          "Export_P4_N10_1", "Export_P4_titel_N10_1", "adm_n10_1_export.jsp"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
    }
  }

  /**
   * initLevelUnabhaengig
   * 
   * @param jspLevelWorkName
   * @param befehleInitial
   */
  private void initLevelUnabhaengig(Map<String, String> jspLevelWorkName,
      GUICommandList[] befehleInitial) {

    InitGuiCommandHelper helper = new InitGuiCommandHelper(this, jspLevelWorkName,
        befehleInitial[LEVEL_UNABHAENGIG], LEVEL_UNABHAENGIG);

    GUICommand cmd;
    /*
     * Gebietseingabe auf allen Gebieten
     */
    helper.setRights(R_EINGABE);
    cmd = helper.addCommand(Command.GEBE,
        "Referendum_Eingabe", "Referendum_Eingabe_titel", "gebietEingang.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    cmd.setNurErfassungseinheit(true);
    cmd.setPosition(0);

    helper.setRights(R_IMPORT);
    cmd = helper.addCommand(Command.IMPORT_ERGEBNISSE,
        "Ergebnisimport_P4", "Ergebnisimport_titel_P4", "ergebnisImport.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    cmd.setNurErfassungseinheit(true);
    cmd.setPosition(1);

    /*
     * Gebietsergebnis auf allen Gebieten
     */
    helper.setRights(null);
    cmd = helper.addCommand(Command.GEB_ERG,
        "Referendum_Ergebnis", "Referendum_Ergebnis_titel", "gebietErgebnisKandidat.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    cmd.setNurGebiete(true);

    helper.setRights(Rechte.R_GEB_STATUS);
    cmd = helper.addCommand(Command.STATUS_GEB,
        "Gebiet_Status", "Gebiet_Status_titel", "Status_Gebiet.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    cmd.setNurErfassungseinheit(true);

    helper.setRights(Rechte.R_EINGABE);
    cmd = helper.addCommand(Command.STATUS, "Status", tooltipStatusCommandKey(), "Status.jsp"); //$NON-NLS-1$ //$NON-NLS-2$
    cmd.setNurWurzelgebiet(true);

    helper.setRights(Rechte.R_FREIGABE);
    cmd = helper.addCommand(Command.ADM_FREIGABE,
        "Freigabesteuerung_Referendum", "Freigabesteuerung_Referendum_titel", "adm_freigabe.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    cmd.setNurWurzelgebiet(true);

    helper.setRights(Rechte.R_EXPORT);

    if (ElectionCategory.NR.equals(_electionCategory)) {
      // Export N11 only for area Gemeente --> only PSB
      if (GebietModel.EBENE_PSB == _ebene) {
        cmd = helper.addCommand(Command.N11,
            "Export_P4_N11", "Export_P4_titel_N11", "P4_Export_N11.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        cmd.setNurWurzelgebiet(true);
      }
      // Export O3 only, when 510c will be exported--> only HSB
      if (GebietModel.EBENE_HSB == _ebene) {
        cmd = helper.addCommand(Command.O3,
            "Export_P4_O3", "Export_P4_titel_O3", "P4_Export_O3.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        cmd.setNurWurzelgebiet(true);

        cmd = helper.addCommand(Command.OSV4_4,
            "Export_P4_Appendix_O3", "Export_P4_titel_Appendix_O3", "P4_Export_OSV4_4.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        cmd.setNurWurzelgebiet(true);
      }
      if (GebietModel.EBENE_CSB == _ebene) {
        cmd = helper.addCommand(Command.WRR83,
            "Export_P4_Wrr83", "Export_P4_titel_Wrr83", "P4_Export_Wrr83.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        cmd.setNurWurzelgebiet(true);
      }
    } else {
      cmd = helper.addCommand(Command.REF_EXP,
          "Export_P4_Referendum", "Export_P4_titel_Referendum", "P4_Export_Referendum.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      cmd.setNurWurzelgebiet(true);
    }

    cmd = helper.addCommand(Command.EXPORT_VERZEICHNIS,
        "ExportVerzeichnis", "Export_Verzeichnis_titel", "dateiExportVerzeichnis.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    cmd.setNurWurzelgebiet(true);

    // Export CSV
    cmd = helper.addCommand(Command.VOTES_CSV,
        "Export_P4_CSV", "Export_P4_titel_CSV", "P4_Export_CSV.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    cmd.setNurWurzelgebiet(true);

    helper.setGuiClass(GUICommand.GUI_CLASS_6);
    helper.setRights(null);

    cmd = helper.addCommand(Command.REFERENDUM, "Referendum", "Referendum", "referendum.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    cmd.setNurGebiete(true);

    cmd = helper.addCommand(Command.HELP, "Hilfe", "Hilfe_titel", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    cmd.setAlleLevel(true);

    // logoutcmd kommt in alle Arrays hinten dran
    cmd = helper.addCommand(Command.SONST_LOGOUT, "Abmelden", "Abmelden_titel", "logout.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    cmd.setAlleLevel(true);

    // wahldetails cmd kommt in alle Arrays hinten dran
    cmd = helper.addCommand(Command.SONST_ELECTIONDETAILS,
        "ElectionDetails", "ElectionDetails_titel", "electiondetails.jsp"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    cmd.setAlleLevel(true);
  }

  private String tooltipStatusCommandKey() {
    if (_ebene == GebietModel.EBENE_CSB) {
      return "Status_titel_CSB"; //$NON-NLS-1$
    } else if (_ebene == GebietModel.EBENE_HSB) {
      return "Status_titel_HSB"; //$NON-NLS-1$
    } else {
      return "Status_titel"; //$NON-NLS-1$
    }
  }

  @Override
  public Command getGebieteWorkDefault() {
    return Command.GEB_ERG;
  }

  @Override
  public Command getErfassungseinheitUnvollstaendigWork(int ergebniseingangStatus) {
    switch (_ebene) {
      case GebietModel.EBENE_PSB :
        return Command.GEBE;

      case GebietModel.EBENE_HSB :
      case GebietModel.EBENE_CSB :
        if (ergebniseingangStatus == ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK
            || ergebniseingangStatus == ErgebniseingangKonstanten.STATE_WARNING
            || ergebniseingangStatus == ErgebniseingangKonstanten.STATE_ERROR) {
          return Command.GEBE;
        }
        return Command.IMPORT_ERGEBNISSE;

      default :
        return Command.GEB_ERG;
    }
  }

}
