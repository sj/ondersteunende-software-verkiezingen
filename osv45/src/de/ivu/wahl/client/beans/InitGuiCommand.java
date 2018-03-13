/*
 * InitGuiCommand
 * 
 * Created on 07.11.2003
 * Copyright (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import de.ivu.wahl.client.util.GUICommand;
import de.ivu.wahl.util.BundleHelper;

/**
 * @author mur@ivu.de, IVU Traffic Technologies AG
 */
public abstract class InitGuiCommand {

  interface GUICommandList extends List<GUICommand> {
    // als Alias
  }

  static class GUICommandArrayList extends ArrayList<GUICommand> implements GUICommandList {
    private static final long serialVersionUID = 527495620920545505L;
    // als Alias
  }

  /**
   * Beziehung zwischen work-Kodierung und JSPs
   */
  protected Map<Integer, String> _jspMap;

  /**
   * Gebietsart der hierarchisch _niedrigsten_ Erfassungseinheit im Gebietsbaum
   */
  protected int _gebietsartErfassungseinheitMax;

  /**
   * ResourceBundel zur Internationalisierung
   */
  protected ResourceBundle _rb;

  /**
   * @param jspMap Beziehung zwischen work-Kodierung und JSPs
   * @param gebietsartErfassungseinheitMax Gebietsart der hierarchisch <b>niedrigsten</b>
   *          Erfassungseinheit im Gebietsbaum
   */
  public InitGuiCommand(Map<Integer, String> jspMap, int gebietsartErfassungseinheitMax) {
    super();
    _jspMap = jspMap;
    _gebietsartErfassungseinheitMax = gebietsartErfassungseinheitMax;
    _rb = ResourceBundle.getBundle("wasInternationalisierung", BundleHelper.getLocale()); //$NON-NLS-1$
  }

  protected abstract int[][] getWahlartLevels();

  protected abstract int[][] getAdminWahlartLevels();

  /**
   * ALLE M�GLICHEN BEFEHLE als GUICommands initialisieren und in die entsprechenden Collections
   * eint�ten
   * 
   * @param jspLevelWorkName
   * @param befehleInitial
   * @param befehle
   * @param levelcount
   */
  protected abstract void initBefehle(Map<String, String> jspLevelWorkName,
      GUICommandList[] befehleInitial,
      GUICommandList[] befehle,
      int levelcount);

  /**
   * Erzeugt einen GUICommand und legt einen Eintrag in der Hashtable zur Verkn�pfung von view und
   * jsp dateinamen an
   */
  protected GUICommand createCommand(String bezeichnung,
      Command command,
      String recht,
      boolean gebietsabhaengig,
      String jsp,
      String tooltip,
      String guiClass) {

    _jspMap.put(command.getId(), jsp);
    return new GUICommand(bezeichnung, command.getId(), recht, gebietsabhaengig, tooltip, guiClass);
  }

  protected void putToJspMap(Command command, String jsp) {
    _jspMap.put(command.getId(), jsp);
  }

  protected String getBundleString(String key) {
    try {
      return _rb.getString(key);
    } catch (Exception e) {
      return "kI " + key; //$NON-NLS-1$
    }
  }

  public boolean isGebieteVorhanden() {
    return true;
  }

  public boolean isLevelunabhaengigVorhanden() {
    return true;
  }

  public boolean isAdminVorhanden() {
    return true;
  }

  public Command getGebieteWorkDefault() {
    return Command.GEB_ERG;
  }

  /**
   * @return diese Seite wird geöffnet, wenn man auf ein unvollständiges Gebiet (nur
   *         Erfassungseinheit) klickt
   */
  public Command getErfassungseinheitUnvollstaendigWork(int ergebniseingangStatus) {
    return Command.GEB_ERG;
  }

  public Command getAdminWorkDefault() {
    return Command.ADM_ANW_LISTE;
  }

  /**
   * @param gebietsnummerWurzelgebiet
   * @param wurzelgebiet
   * @return Name der Datei, die das Logo f�r die Konfiguration enth�lt
   */
  public String getLogoForWahl(int gebietsnummerWurzelgebiet) {
    return "kiesraad.gif"; //$NON-NLS-1$
    //    return "logo-ivu.gif"; //$NON-NLS-1$
  }

}
