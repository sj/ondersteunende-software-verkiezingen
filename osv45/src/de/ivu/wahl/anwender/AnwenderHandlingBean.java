/*
 * AnwenderHandlingBean
 * 
 * Copyright (c) 2002-9 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.anwender;

import static de.ivu.wahl.AnwContext.ID_PWAHL_SUPER_ADMIN;
import static de.ivu.wahl.Konstanten.RECHTE_LAST_CHANGE;
import static de.ivu.wahl.Konstanten.SYSTEM_ANWENDER;
import static de.ivu.wahl.Konstanten.SYSTEM_ANWENDERID;
import static de.ivu.wahl.WahlInfo.getWahlInfo;
import static de.ivu.wahl.anwender.AnwRechte.resetAnwRechte;
import static de.ivu.wahl.anwender.Rechte.RECHTE;
import static de.ivu.wahl.anwender.Rechte.RGRUPPEN_P4;
import static de.ivu.wahl.anwender.Rechte.RGRUPPEN_P5;
import static de.ivu.wahl.anwender.Rechte.RG_ADMIN;
import static java.lang.System.currentTimeMillis;
import static java.util.Collections.emptyList;
import static java.util.Collections.sort;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.wahl.AnwContext;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.PasswordService;
import de.ivu.wahl.SystemInfo;
import de.ivu.wahl.WahlStatelessSessionBeanBase;
import de.ivu.wahl.admin.AdminHandling;
import de.ivu.wahl.admin.PropertyHandling;
import de.ivu.wahl.dataimport.AbstractImportEML;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.RechtegruppeModel;
import de.ivu.wahl.modell.WahlModel;
import de.ivu.wahl.modell.ejb.Anwender;
import de.ivu.wahl.modell.ejb.AnwenderHome;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.GebietHome;
import de.ivu.wahl.modell.ejb.Recht;
import de.ivu.wahl.modell.ejb.RechtHome;
import de.ivu.wahl.modell.ejb.Rechtegruppe;
import de.ivu.wahl.modell.ejb.RechtegruppeHome;
import de.ivu.wahl.modell.ejb.Wahl;
import de.ivu.wahl.modell.ejb.WahlHome;

/**
 * Dieses SessionBean enth�lt eigentlich alles, was zur Handhabung von Anwendern auf der Serverseite
 * notwendig ist. Als eine kleine Besonderheit h�lt es in statischem Code die Liste aller
 * angemeldeten Anwender.
 * 
 * @author cos@ivu.de klie@ivu.de mur@ivu.de
 */
@Stateless
@Local(AnwenderHandling.class)
public class AnwenderHandlingBean extends WahlStatelessSessionBeanBase implements AnwenderHandling {
  private static final long serialVersionUID = 8384306471342163350L;

  // Liste der angemeldeten Benutzer
  private static final Set<Anmeldung> __angemeldeteAnwender = new HashSet<Anmeldung>();
  static final Category LOGGER = Log4J.configure(AnwenderHandlingBean.class);
  static final int MAX_PRINTER_IDLE_MINUTES = 5;
  static final int MAX_PRINTER_IDLE = 1000 * 60 * MAX_PRINTER_IDLE_MINUTES;

  /**
   * Lokales Zwischenspeichern des Home IF von Anwender ist hier angebracht, au�erdem behebt es mit
   * default-Zugriff das Problem des sauberen Zugriffs aus der inneren Klasse
   */
  AnwenderHome _anwenderHome;

  @EJB
  private AdminHandling _adminHandling;

  @EJB
  private PropertyHandling _propertyHandling;

  @PostConstruct
  public void ejbCreate() throws EJBException {
    _anwenderHome = AnwenderHome.HomeFinder.findHome(this);
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#checkRight(de.ivu.wahl.AnwContext, java.lang.String)
   */
  public boolean checkRight(AnwContext ac, String right) throws EJBException {
    return getAnwRechte(ac).checkRight(right);
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#createWurzelAnwender()
   */
  public void createWurzelAnwender() throws EJBException {
    // Administrator
    List<String> id_Rechtegruppen = new ArrayList<String>();
    id_Rechtegruppen.add(RG_ADMIN);

    createOrModifyAnwender(Konstanten.DEFAULT_USER_ID,
        Konstanten.DEFAULT_USER_LOGINNAME,
        Konstanten.DEFAULT_USER_NAME,
        Konstanten.DEFAULT_USER_PASSWORD,
        id_Rechtegruppen,
        null);

  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#delAnwender(java.lang.String)
   */
  public void delAnwender(String id_Anwender) throws FinderException, EJBException, RemoveException {
    Anwender anwender = _anwenderHome.findByPrimaryKey(id_Anwender);

    // L�schen eines Anwenders

    // L�schen der Beziehung zu den Rechtegruppen
    for (Rechtegruppe rechtegruppe : anwender.getRechtegruppeCol()) {
      anwender.removeRechtegruppe(rechtegruppe);
    }
    LOGGER.info(Messages.bind(MessageKeys.Logger_Anwender_0_1_2_WurdeGeloescht,
        anwender.getAnwendername(),
        "", anwender //$NON-NLS-1$
            .getName()));
    anwender.remove();
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#getAllGebieteForWahl(java.lang.String, int)
   */
  public List<GebietModel> getAllGebieteForWahl(String id_Wahl, int gebietsart) {
    getWahlInfo(id_Wahl).getWahl().readLock();
    try {
      return toModelList(GebietHome.HomeFinder.findHome(this)
          .findAllByWahlAndGebietsartSortByPosition(id_Wahl, gebietsart));
    } catch (FinderException e) {
      LOGGER.error(e, e);
      return emptyList();
    }
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#getAllChildGebieteForGebiet(java.lang.String)
   */
  public List<GebietModel> getAllChildGebieteForGebiet(String id_Gebiet) {
    try {
      return toModelList(GebietHome.HomeFinder.findHome(this)
          .findAllByUebergeordnetesGebietSortByNummer(id_Gebiet));
    } catch (FinderException e) {
      LOGGER.error(e, e);
      return emptyList();
    }
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#getAllRechtegruppen()
   */
  public List<RechtegruppeModel> getAllRechtegruppen() throws EJBException {
    try {
      return toModelList(RechtegruppeHome.HomeFinder.findHome(this).findAll());
    } catch (FinderException e) {
      LOGGER.error(e, e);
      return emptyList();
    }
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#getAngemeldeteAnwender()
   */
  public Collection<Anmeldung> getAngemeldeteAnwender() {
    List<Anmeldung> angemeldeteAnwender = new ArrayList<Anmeldung>(__angemeldeteAnwender);
    sort(angemeldeteAnwender);
    return angemeldeteAnwender;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#getAngemeldeteAnwender(java.lang.String)
   */
  public Collection<Anmeldung> getAngemeldeteAnwender(String id_Wahl) {
    List<Anmeldung> angemAnwFuerWahl = new ArrayList<Anmeldung>();
    // DruckerClients: pr�fe, wann der letzte Zugriff war
    for (Anmeldung an : __angemeldeteAnwender) {
      if (id_Wahl.equals(an._idWahl)) {
        angemAnwFuerWahl.add(an);
      }
    }
    sort(angemAnwFuerWahl);
    return angemAnwFuerWahl;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#getAnwRechte(de.ivu.wahl.AnwContext)
   */
  public AnwRechte getAnwRechte(AnwContext ac) throws EJBException {
    return AnwRechte.getAnwRechte(ac);
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#initRechteUndRechteGruppen()
   */
  public void initRechteUndRechteGruppen() throws EJBException {
    // zun�chst Erzeugen der Rechtegruppen
    RechtegruppeHome rgh = RechtegruppeHome.HomeFinder.findHome(this);
    // merken in Map f�r schneller gleich ...
    Map<String, Rechtegruppe> rgMap = new HashMap<String, Rechtegruppe>();
    SystemInfo systemInfo = SystemInfo.getSystemInfo();
    int wahlModus = systemInfo.getWahlModus();
    final String[][] rgruppen = AbstractImportEML.MODE_DB_P4 == wahlModus
        ? RGRUPPEN_P4
        : RGRUPPEN_P5;

    for (String[] rechtegruppeInfo : rgruppen) {
      Rechtegruppe rg = null;
      String rgKey = rechtegruppeInfo[2];
      try {
        rg = rgh.findByPrimaryKey(rgKey);
        try {
          // L�schen der Beziehungen zu den rechten
          // damit k�nnen nun auch Rechte aus der Definition entzogen
          // werden
          for (Recht r : rg.getRechtCol()) {
            LOGGER.info(Messages.bind(MessageKeys.Logger_Loesche_0_1,
                rg.getID_Rechtegruppe(),
                r.getID_Recht()));
            rg.removeRecht(r);
          }
        } catch (Exception ex) {
          LOGGER.error(ex, ex);
        }
      } catch (Exception e) {
        LOGGER.info(Messages.bind(MessageKeys.Logger_Rechtegruppe_0_WirdAngelegt, rgKey));
        try {
          rg = rgh.create(rgKey);
        } catch (Exception e2) {
          throw new EJBException("", e2); //$NON-NLS-1$
        }
      }
      rg.setName(rechtegruppeInfo[0]);
      rg.setBeschreibung(rechtegruppeInfo[1]);
      rgMap.put(rgKey, rg);
    }
    // Jetzt die Rechte und ihre Zuordnung zu den Gruppen
    RechtHome rh = RechtHome.HomeFinder.findHome(this);
    for (String[] rechtInfo : RECHTE) {
      Recht r = null;
      String rKey = rechtInfo[0];
      try {
        r = rh.findByPrimaryKey(rKey);
      } catch (Exception e) {
        LOGGER.info(Messages.bind(MessageKeys.Logger_Recht_0_WirdAngelegt, rKey));
        try {
          r = rh.create(rKey);
        } catch (Exception e2) {
          throw new EJBException("", e2); //$NON-NLS-1$
        }
      }
      r.setName(rKey);
      String rgKey = rechtInfo[1];
      Rechtegruppe rg = rgMap.get(rgKey);
      if (rg != null) {
        try {
          rg.addRecht(r);
          LOGGER.info(Messages.bind(MessageKeys.Logger_LegeAn_0_1,
              rg.getID_Rechtegruppe(),
              r.getID_Recht()));
        } catch (Exception e) {
          LOGGER
              .error(Messages
                  .bind(MessageKeys.Logger_BeziehungKonnteNichtHergestelltWerdenBestandAlsoVielleichtSchon));
        }
      } else {
        LOGGER.error(Messages.bind(MessageKeys.Logger_UnbekannteRechtegruppe_0, rgKey));
      }
    }
  }

  /**
   * Anmeldung der "out of process" Systemteile f�r alle Wahlen. Die Systemteile werden als Anwender
   * nicht in die Liste eingetragen oder sonstwie erfasst.
   * 
   * @return Anwenderkontext oder <code>null</code> wenn ung�ltig
   */
  private AnwContext loginSystem() {
    return new AnwContext(null, SYSTEM_ANWENDERID, SYSTEM_ANWENDER, null, null);
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#logout(de.ivu.wahl.AnwContext)
   */
  public void logout(AnwContext ac) {
    synchronized (__angemeldeteAnwender) {
      for (Anmeldung an : __angemeldeteAnwender) {
        if (an._id == ac.getID_Anwender() && equals(an._idWahl, ac.getID_WahlPWahl())) {
          writeAppLog(ac, "Logout: " + an);
          __angemeldeteAnwender.remove(an);
          try {
            Anwender a = _anwenderHome.findByPrimaryKey(ac.getID_Anwender());
            a.setFehlversucheAnmeldung(0);
          } catch (Exception ee) {
            LOGGER.error(Messages
                .bind(MessageKeys.Logger_EsWurdeKeinAnwenderMitDemNamen_0_gefunden,
                    ac.getAnmeldename()), ee);
            // user not existant
          }

          break;
        }
      }
    }
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#rightsChanged(java.lang.String)
   */
  public void rightsChanged(String id_Anwender) {
    try {
      resetAnwRechte(id_Anwender);
      _propertyHandling.setLongProperty(RECHTE_LAST_CHANGE, currentTimeMillis());
    } catch (Exception e) {
      LOGGER.error(e, e);
    }
  }

  /**
   * Interne Methode zur Administration von Anwendern
   * 
   * @param id_Anwender Prim�rschl�ssel des Anwenders
   * @param anmeldename User ID
   * @param name echter Name des Anwenders
   * @param passwort Passwort des Anwenders
   * @param id_Rechtegruppen Collection von Prim�rschl�sseln der Rechtegruppen, denen der Anwender
   *          angeh�rt
   * @param id_Gebiet Prim�rschl�ssel des Gebietes, dem der Anwender angeh�rt
   * @throws EJBException bei einem Problem
   */
  private void createOrModifyAnwender(String id_Anwender,
      String anmeldename,
      String name,
      String passwort,
      Collection<String> id_Rechtegruppen,
      String id_Gebiet) throws EJBException {

    LOGGER.info(Messages.bind(MessageKeys.Logger_0_wirdAngelegtOderModifiziert, anmeldename));
    try {
      _anwenderHome.findByPrimaryKey(id_Anwender);
    } catch (FinderException e) {
      try {
        _anwenderHome.create(id_Anwender);
      } catch (CreateException ce) {
        throw new EJBException("" + anmeldename, ce); //$NON-NLS-1$
      }

    }
    modifyAnwender(loginSystem(),
        id_Anwender,
        anmeldename,
        name,
        passwort,
        id_Rechtegruppen,
        id_Gebiet,
        0);
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#createOrModifyAnwender(de.ivu.wahl.AnwContext,
   * java.lang.String, java.lang.String, java.lang.String, java.util.Collection, java.lang.String,
   * int, boolean, boolean)
   */
  @Override
  public void createOrModifyAnwender(AnwContext anwContext,
      String anmeldename,
      String name,
      String passwort,
      Collection<String> id_Rechtegruppen,
      String id_Gebiet,
      int anzahlFehlversuche,
      boolean create,
      boolean modify) throws AnwenderException, EJBException {

    Anwender a = null;
    try {
      a = _anwenderHome.findByAnwendername(anmeldename);
      if (!modify) {
        throw new AnwenderException(Messages.bind(MessageKeys.Error_FehlerAnwender_0_Existiert,
            anmeldename));
      }
    } catch (ObjectNotFoundException e) {
      if (create) {
        LOGGER.info(Messages.bind(MessageKeys.Logger_0_wirdAngelegt, anmeldename));
        try {
          a = _anwenderHome.create();
        } catch (CreateException ce) {
          throw new EJBException(Messages.bind(MessageKeys.Logger_FehlerBeimAnlegenDesAnwenders_0,
              anmeldename), ce);
        }
      } else {
        LOGGER.info(Messages.bind(MessageKeys.Logger_0_wurdeNichtGefunden, anmeldename));
        throw new AnwenderException(""); //$NON-NLS-1$
      }
    } catch (FinderException e) {
      throw new EJBException("" + anmeldename, e); //$NON-NLS-1$
    }
    AnwContext workAnwContext = anwContext;
    if (workAnwContext == null) {
      workAnwContext = loginSystem();
    }
    modifyAnwender(workAnwContext,
        a.getID_Anwender(),
        anmeldename,
        name,
        passwort,
        id_Rechtegruppen,
        id_Gebiet,
        anzahlFehlversuche);
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#changePassword(de.ivu.wahl.AnwContext,
   * java.lang.String, java.lang.String)
   */
  @Override
  public void changePassword(AnwContext anwContext, String oldPW, String newPW)
      throws AnwenderException, EJBException {

    if (oldPW == null || newPW == null) {
      // "Fehler in der Anwendung!"
      throw new AnwenderException(Messages.bind(MessageKeys.Error_Passwort_null));
    }

    String id_Anwender = anwContext.getID_Anwender();
    try {
      Anwender anwender = _anwenderHome.findByPrimaryKey(id_Anwender);
      String oldSalt = anwender.getSalt();
      String oldPWHash = PasswordService.INSTANCE.calcHash(anwContext.getAnmeldename() + oldPW,
          oldSalt);
      if (!anwender.getPasswordHash().equals(oldPWHash)) {
        // "Fehler: Das Passwort konnte nicht geaendert werden, das angegebene alte Passwort war falsch!"
        throw new AnwenderException(Messages.bind(MessageKeys.Error_PasswortAltFalsch));
      }
      String newPWHashWithOldSalt = PasswordService.INSTANCE.calcHash(anwContext.getAnmeldename()
          + newPW, oldSalt);

      if (oldPWHash.equals(newPWHashWithOldSalt)) {
        // "Fehler: Das Passwort konnte nicht geaendert werden, da es mit dem alten ueberein stimmt!"
        throw new AnwenderException(Messages.bind(MessageKeys.Error_PasswortAltUngleichNeu));
      }
      _adminHandling.writeAppLog(anwContext, "" + anwender.getAnwendername()); //$NON-NLS-1$
      modifyAnwender(anwContext,
          id_Anwender,
          anwender.getAnwendername(),
          anwender.getName(),
          newPW,
          null,
          anwender.getID_Gebiet(),
          0);
    } catch (AnwenderException e) {
      throw e;
    } catch (Exception e) {
      // "Fehler bei der Suche nach "
      throw new EJBException(Messages.bind(MessageKeys.Error_PasswortAnwenderNichtGefunden,
          id_Anwender), e);
    }
  }

  /**
   * Zentrale internen Methode zum �ndern eines Anwenders (ob neu oder vorhanden). Erledigt auch die
   * Replikation.
   * 
   * @param anwContext Anwenderkontext des Aufrufers
   * @param anmeldename User ID
   * @param name echter Name des Anwenders
   * @param passwortHash Pr�fsumme aus Anmeldename und Passwort
   * @param id_Rechtegruppen Collection von Prim�rschl�sseln der Rechtegruppen, denen der Anwender
   *          angeh�rt
   * @param id_Gebiet Prim�rschl�ssel des Gebietes, dem der Anwender angeh�rt
   * @param anzahlFehlversuche Anzahl fehlerhafter Anmeldungen (nur sinnvoll bei der Replikation)
   * @throws EJBException bei einem Problem
   */
  private void modifyAnwender(AnwContext anwContext,
      String id_Anwender,
      String anmeldename,
      String name,
      String passwort,
      Collection<String> id_Rechtegruppen,
      String id_Gebiet,
      int anzahlFehlversuche) throws EJBException {

    String salt = PasswordService.INSTANCE.getSalt();
    String passwortHash = PasswordService.INSTANCE.calcHash(anmeldename + passwort, salt);

    // Administrator
    Anwender a = null;
    try {
      a = _anwenderHome.findByPrimaryKey(id_Anwender);
    } catch (FinderException e) {
      throw new EJBException("" + id_Anwender, e); //$NON-NLS-1$
    }
    a.setAnwendername(anmeldename);
    a.setLetzterZugriff(new Timestamp(currentTimeMillis()));
    a.setFehlversucheAnmeldung(anzahlFehlversuche);
    a.setName(name);
    a.setID_Gebiet(id_Gebiet);
    if (passwortHash != null) {
      a.setPasswordHash(passwortHash);
      a.setSalt(salt);
    }
    if (id_Rechtegruppen != null) {
      // einmal alle Rechte zur�cksetzen
      rightsChanged(a.getID_Anwender());
      for (Rechtegruppe rechtegruppe : a.getRechtegruppeCol()) {
        a.removeID_Rechtegruppe(rechtegruppe.getID_Rechtegruppe());
      }
      for (String id_Rechtegruppe : id_Rechtegruppen) {
        a.addID_Rechtegruppe(id_Rechtegruppe);
      }
    }
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#getSalt(java.lang.String)
   */
  @Override
  public String getSalt(String anwendername) throws EJBException {
    try {
      Anwender a = _anwenderHome.findByAnwendername(anwendername);
      if (a != null) {
        return a.getSalt();
      }
      return StringUtils.EMPTY;
    } catch (ObjectNotFoundException e) {
      return StringUtils.EMPTY;
    } catch (FinderException fe) {
      return StringUtils.EMPTY;
    }
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#login(java.lang.String, java.lang.String,
   * java.lang.String, java.lang.String)
   */
  @Override
  public AnwContext login(String anwendername, String hash, String id_Wahl, String sessionID)
      throws EJBException, AnmeldeFehlversuchException {

    int maxFehlversuche = 6;

    try {
      boolean uebergeordneteAdministration = false;
      Anwender a = _anwenderHome.findByAnwendernameAndPasswordHash(anwendername, hash);
      // direkt Check der Fehlversuche
      // Wenn mehr als 5 -> nix da anmelden
      if (a.getFehlversucheAnmeldung() < maxFehlversuche) {
        // erfolgreiche Anmeldung -> Fehlversuche zur�cksetzen,
        // registrieren und id zur�ckgeben
        a.setFehlversucheAnmeldung(0);
        AnwContext ac;
        Gebiet gebiet = a.getGebiet();
        if (gebiet != null) {
          ac = new AnwContext(gebiet.getID_Wahl(), a.getID_Anwender(), a.getAnwendername(),
              a.getID_Gebiet(), sessionID);
        } else {
          // pr�fen, ob nur eine Wahl vorliegt
          Collection<Wahl> wahlen = WahlHome.HomeFinder.findHome(this).findAll();
          ac = new AnwContext(ID_PWAHL_SUPER_ADMIN, a.getID_Anwender(), a.getAnwendername(), null,
              sessionID);
          if (wahlen.size() == 1) {
            Wahl wahl = wahlen.iterator().next();
            if (WahlModel.STATE_STEMDISTRICT_IMPORT == wahl.getStatus()) {
              uebergeordneteAdministration = true;
            } else {
              ac = new AnwContext(wahl.getID_Wahl(), a.getID_Anwender(), a.getAnwendername(),
                  wahl.getID_Wurzelgebiet(), sessionID);
            }
          } else {
            uebergeordneteAdministration = true;
          }
        }
        if (ac.getID_WahlPWahl() != null) {
          if (!registerLogin(ac) && uebergeordneteAdministration) {
            LOGGER.info(Messages.bind(MessageKeys.Logger_AnwenderBereitsAmSystemAngemeldet_0,
                a.getAnwendername()));
            return AnwContext.ADM_USER_ALREADY_LOGGED_IN;
          }
        }
        return ac;
      } else {
        LOGGER.info(Messages.bind(MessageKeys.Logger_AnzahlFehlversucheGroesserGleich_0_1,
            maxFehlversuche,
            a.getAnwendername()));
        throw new AnmeldeFehlversuchException(maxFehlversuche - a.getFehlversucheAnmeldung());
      }
    } catch (ObjectNotFoundException e) {
      registerFehlversuch(anwendername, maxFehlversuche);
      return null;
    } catch (FinderException fe) {
      throw new EJBException(Messages.bind(MessageKeys.Logger_BeimLogin_0, anwendername), fe);
    }
  }

  private void registerFehlversuch(String anwendername, int maxFehlversuche)
      throws AnmeldeFehlversuchException {
    boolean fehlversuch = false;
    Anwender a = null;
    try {
      a = _anwenderHome.findByAnwendername(anwendername);
      a.setFehlversucheAnmeldung(a.getFehlversucheAnmeldung() + 1);
      fehlversuch = true;
    } catch (Exception ee) {
      LOGGER.info(Messages.bind(MessageKeys.Logger_EsWurdeKeinAnwenderMitDemNamen_0_gefunden,
          anwendername), ee);
      // user not existant
    }
    if (fehlversuch && a != null) {
      throw new AnmeldeFehlversuchException(maxFehlversuche - a.getFehlversucheAnmeldung());
    }
  }

  /**
   * Trägt einen Anwender in die Liste der angemeldeten Anwender ein
   * 
   * @param ac Anwenderkontext des Anwenders
   * @throws EJBException bei einem Problem
   */
  private boolean registerLogin(AnwContext ac) throws EJBException {
    try {
      Anmeldung anmeldung = new Anmeldung(_anwenderHome.findByPrimaryKey(ac.getID_Anwender()),
          ac.getID_WahlPWahl());
      writeAppLog(ac, "Login: " + anmeldung);
      synchronized (__angemeldeteAnwender) {
        return __angemeldeteAnwender.add(anmeldung);
      }
    } catch (FinderException e) {
      throw new EJBException(e);
    }
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#hasAnwenderGebiet(java.lang.String)
   */
  public boolean hasAnwenderGebiet(String id_Anwender) throws FinderException {
    return _anwenderHome.findByPrimaryKey(id_Anwender).getID_Gebiet() != null;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.anwender.AnwenderHandling#hasDefaultUserUnchangedPasswort()
   */
  @Override
  public boolean hasDefaultUserUnchangedPasswort() {
    try {
      Anwender defaultUser = _anwenderHome.findByAnwendername(Konstanten.DEFAULT_USER_LOGINNAME);
      String salt = defaultUser.getSalt();
      String defaultPasswortHash = PasswordService.INSTANCE
          .calcHash(Konstanten.DEFAULT_USER_LOGINNAME + Konstanten.DEFAULT_USER_PASSWORD, salt);
      return defaultPasswortHash.equals(defaultUser.getPasswordHash());
    } catch (FinderException e) {
      LOGGER.error(Messages.bind(MessageKeys.Logger_DefaultUserNotFound));
      LOGGER.error(e, e);
      return false;
    }
  }
}