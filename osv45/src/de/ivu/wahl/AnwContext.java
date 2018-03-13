/*
 * AnwContext
 * 
 * Created on 13.10.2003 Copyright (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl;

import static de.ivu.ejb.EJBUtil.lookupLocal;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.wahl.anwender.AnwenderHandling;
import de.ivu.wahl.anwender.AnwenderHandlingBean;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;

/**
 * Tr�gt die Identit�t und die wichtigsten Session-Daten des Anwenders. Dient insbesondere der
 * Kommunikation zwischen dem Client und dem Server.
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class AnwContext implements Serializable {
  private static final long serialVersionUID = 307267054192640768L;

  /**
   * Logger f�r die Klasse
   */
  private final static Category LOGGER = Log4J.configure(AnwContext.class);

  private final static String TRENNER = "#_#_#_#_#_#";//$NON-NLS-1$

  /**
   * Invalid user context if the same user is already logged in
   */
  public static final AnwContext ADM_USER_ALREADY_LOGGED_IN = new AnwContext(null, null, null,
      null, null);

  /** Pseudo-Wahl-ID f�r die �bergeordete Administration */
  public static final String ID_PWAHL_SUPER_ADMIN = "_S_A_"; //$NON-NLS-1$

  /**
   * Prim�rschl�ssel des Anwenders
   */
  private final String _id_Anwender;

  /**
   * User ID des Anwenders
   */
  private final String _anmeldeName;

  /**
   * Prim�rschl�ssel der Wahl, an der der Anwender angemeldet ist
   */
  private final String _id_Wahl;

  /**
   * Prim�rschl�ssel des Gebietes, an dem der Anwender angemeldet ist
   */
  private final String _id_Gebiet;

  private boolean _changePasswordForced = false;

  private final String _sessionID;

  /**
   * Erzeugt einen neuen Anwenderkontext (gew�hnlich aufgerufen bei der Anmeldung)
   * 
   * @param id_Wahl Prim�rschl�ssel der Wahl, an die sich der Anwender anmeldet
   * @param id_Anwender Prim�rschl�ssel des Anwenders
   * @param anmeldeName User ID des Anwenders
   * @param id_Gebiet Prim�rschl�ssel des Gebietes, an das sich der Anwender anmeldet
   * @param sessionID SessionID f�r die der Anwender angemeldet ist
   */
  public AnwContext(String id_Wahl,
      String id_Anwender,
      String anmeldeName,
      String id_Gebiet,
      String sessionID) {
    _id_Wahl = id_Wahl;
    _id_Anwender = id_Anwender;
    _anmeldeName = anmeldeName;
    _id_Gebiet = id_Gebiet;
    _sessionID = sessionID;
  }

  /**
   * Gibt den Prim�rschl�ssel des Anwenders zur�ck
   * 
   * @return Prim�rschl�ssel des Anwenders
   */
  public String getID_Anwender() {
    return _id_Anwender;
  }

  /**
   * Gibt die User ID des Anwenders zur�ck
   * 
   * @return User ID des Anwenders
   */
  public String getAnmeldename() {
    return _anmeldeName;
  }

  /**
   * Gibt den Prim�rschl�ssel der Wahl, an der der Anwender angemeldet ist, zur�ck
   * 
   * @return Prim�rschl�ssel der Wahl, an der der Anwender angemeldet ist (bei Pseudo-Wahl
   *         <code>null</code>)
   */
  public String getID_Wahl() {
    return ID_PWAHL_SUPER_ADMIN.equals(_id_Wahl) ? null : _id_Wahl;
  }

  /**
   * Gibt den Prim�rschl�ssel der Wahl, an der der Anwender angemeldet ist, oder, eine
   * Pseudo-Wahl-ID, wenn es sich um keine Wahl handelt, zur�ck
   * 
   * @return Prim�rschl�ssel der Wahl, an der der Anwender angemeldet ist
   */
  public String getID_WahlPWahl() {
    return _id_Wahl;
  }

  /**
   * Gibt den Prim�rschl�ssel des Gebietes, an dem der Anwender angemeldet ist, zur�ck
   * 
   * @return Prim�rschl�ssel des Gebietes, an dem der Anwender angemeldet ist
   */
  public String getID_Gebiet() {
    return _id_Gebiet;
  }

  @Override
  public boolean equals(Object obj) {
    AnwContext other = (AnwContext) obj;
    return eq(_id_Anwender, other._id_Anwender) && eq(_id_Wahl, other._id_Wahl);
  }

  @Override
  public int hashCode() {
    return hc(_id_Wahl) ^ hc(_id_Anwender);
  }

  /**
   * Eine NPE-sichere Version der <code>equals()</code> -Methode. Vergleicht <code>null</code>
   * miteinander als gleich, mit allen anderen als verschieden.
   * 
   * @param s1 erster String-Parameter
   * @param s2 zweiter String-Parameter
   * @return <code>true</code> wenn beide <code>null</code> oder <code>equals()</code>
   *         <code>true</code> liefert, sonst <code>false</code>
   */
  private static boolean eq(String s1, String s2) {
    return s1 == null ? s2 == null : s1.equals(s2);
  }

  /**
   * Eine NPE-sichere Version der <code>hashCode()</code> -Methode. Wenn der String
   * <code>null</code> ist, wird <code>0</code> als Hash-Code zur�ckgegeben.
   * 
   * @param s String, von dem der Hash-Code gebildet werden soll
   * @return <code>0</code> wenn der Parameter <code>null</code> ist, sonst das Ergebnis der
   *         <code>hashCode()</code> -Methode
   */
  private static int hc(String s) {
    return s == null ? 0 : s.hashCode();
  }

  /**
   * Ein einzelnes Recht des Anwenders direkt pr�fen (relativ ineffizient, da ein Server-Aufruf
   * erfolgt)
   * 
   * @param right das zu pr�fende Recht
   * @return <code>true</code>, wenn der Anwender darf
   */
  public boolean checkRight(String right) {
    try {
      AnwenderHandling ah = lookupLocal(AnwenderHandlingBean.class.getSimpleName());
      return ah.checkRight(this, right);
    } catch (Exception e) {
      LOGGER.error(Messages.bind(MessageKeys.Error_KannDasRecht_0_FuerDenAnwender_1_NichtPruefen,
          right,
          _id_Anwender));
      return false;
    }
  }

  /**
   * Wird in der Session auf <code>true</code> gesetzt, wenn man sich mit dem default user und
   * default passwort einloggt. Wird dann nach dem Ändern des Passworts wieder auf
   * <code>false</code> zurückgesetzt.
   * <p/>
   * Ähnliches tut {@link AnwenderHandling#hasDefaultUserUnchangedPasswort()}, nur nicht
   * Session-bezogen sondern quasi für das Gesamtsystem.
   */
  public boolean isChangePasswordForced() {
    return _changePasswordForced;
  }

  public void setChangePasswordForced(boolean forced) {
    _changePasswordForced = forced;
  }

  public String getSessionID() {
    return _sessionID;
  }

  public String getKeyForViewlock() {
    return _sessionID + TRENNER + _anmeldeName;
  }

  public static String getUsernameFromLockvalue(String value) {
    if (value != null && !value.isEmpty()) {
      final String[] split = value.split(TRENNER);
      return split[1];
    }
    return "";
  }
}
