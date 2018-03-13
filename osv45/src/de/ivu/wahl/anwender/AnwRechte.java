package de.ivu.wahl.anwender;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import de.ivu.ejb.IVUBeanBase;
import de.ivu.wahl.AnwContext;
import de.ivu.wahl.GebietHierarchie;
import de.ivu.wahl.GebietHierarchie.GebietKey;
import de.ivu.wahl.modell.ejb.Recht;
import de.ivu.wahl.modell.ejb.RechtHome;

/**
 * Tr�gt alle Rechte des Anwenders bei sich tr�gt und kann pr�fen, ob ein Anwender was darf ...
 * 
 * @author cos@ivu.de klie@ivu.de (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public class AnwRechte implements Serializable {
  private static final long serialVersionUID = -5275883713636226636L;

  private static final Map<String, AnwRechte> __anwRechte = new HashMap<String, AnwRechte>();
  private static final Map<String, List<String>> __anw = new HashMap<String, List<String>>();

  /**
   * Gibt die aktuellen Rechte des Anwenders zur�ck (AnwRechte-Instanz wird hier
   * zwischengespeichert)
   * 
   * @param c Anwenderkontext
   * @return aktuellen Rechte des Anwenders als <code>AnwRechte</code>-Objekt
   * @throws EJBException bei einem Problem
   */
  public static synchronized AnwRechte getAnwRechte(AnwContext c) throws EJBException {
    String id_Anwender = c.getID_Anwender();
    String id_Gebiet = c.getID_Gebiet();

    String key = id_Anwender + '_' + id_Gebiet;
    AnwRechte anwRechte = __anwRechte.get(key);
    if (anwRechte == null) {
      anwRechte = new AnwRechte(id_Anwender, id_Gebiet);
      __anwRechte.put(key, anwRechte);
      List<String> gebietRechte = __anw.get(id_Anwender);
      if (gebietRechte == null) {
        gebietRechte = new ArrayList<String>();
        __anw.put(id_Anwender, gebietRechte);
      }
      gebietRechte.add(key);
    }
    return anwRechte;
  }

  /**
   * Setzt die zwischengespeicherten Rechte eines Anwenders zur�ck. Sie werden beim n�chsten Zugriff
   * neu aus der Datenbank geholt.
   * 
   * @param id_Anwender Prim�rschl�ssel des betroffenen Anwenders
   */
  public static void resetAnwRechte(String id_Anwender) {
    List<String> gebietRechte = __anw.get(id_Anwender);
    if (gebietRechte != null) {
      for (String key : gebietRechte) {
        __anwRechte.remove(key);
      }
    }
  }

  private final long _timestamp = System.currentTimeMillis();
  private final Set<String> _rights = new HashSet<String>();
  // wird bei gleichbleibender Objekt-ID von RMI
  // nur einmal vom Server zum Client �bertragen!
  private final Set<GebietKey> _gebiete;

  protected AnwRechte(String id_Anwender, String id_Gebiet) throws EJBException {
    try {
      RechtHome rechtHome = IVUBeanBase.findLocalHomeExt("Recht"); //$NON-NLS-1$
      for (Recht recht : rechtHome.findAllByAnwender(id_Anwender)) {
        _rights.add(recht.getName());
      }
      _gebiete = GebietHierarchie.getAlleGebiete(id_Gebiet);
    } catch (FinderException e) {
      throw new EJBException(e);
    }
  }

  /**
   * Gibt den Zeitstempel des zwischengespeicherten Objekts zur�ck (wegen G�ltigkeitspr�fung)
   * 
   * @return Zeitstempel des zwischengespeicherten Objekts
   */
  public long getTimestamp() {
    return _timestamp;
  }

  /**
   * Pr�fen, ob ein Anwender etwas darf
   * 
   * @param right das Recht, das gepr�ft wird
   * @return <code>true</code>, wenn der Anwender darf
   */
  public boolean checkRight(String right) {
    return _rights.contains(right);
  }

  /**
   * Pr�fen, ob ein Anwender etwas auf einem Gebiet darf
   * 
   * @param right das Recht, das gepr�ft wird
   * @param gebietsart Gebietsart des gepr�ften Gebietes
   * @param gebietsnummer Nummer des gepr�ften Gebietes
   * @return <code>true</code>, wenn der Anwender darf
   */
  public boolean checkRightForGebiet(String right, int gebietsart, int gebietsnummer) {
    return _rights.contains(right)
        && _gebiete.contains(new GebietHierarchie.GebietKey(gebietsart, gebietsnummer));
  }
}