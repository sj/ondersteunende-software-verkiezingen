/*
 * Created on 08.01.2004
 * 
 * Copyright (c) 2004-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUBeanBase;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.modell.GebietInfo;
import de.ivu.wahl.modell.ejb.Ergebniseingang;
import de.ivu.wahl.modell.ejb.ErgebniseingangHome;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.GebietHome;
import de.ivu.wahl.modell.ejb.Wahl;

/**
 * @author bae@ivu.de cos@ivu.de mur@ivu.de - IVU Traffic Technologies AG
 */
public class GebietsBaum implements Serializable {
  private static final long serialVersionUID = -3055801084585686392L;
  private static final String SEPARATOR = "_"; //$NON-NLS-1$
  private static final Category LOGGER = Log4J.configure(GebietsBaum.class);
  static {
    LOGGER.info(Log4J.dumpVersion(GebietsBaum.class, Log4J.extractVersion("$Revision: 1.15 $"))); //$NON-NLS-1$
  }

  private static final Map<String, GebietsBaum> __gebietsBaeume = new HashMap<String, GebietsBaum>();

  public static GebietsBaum getGebietsBaum(AnwContext c) throws EJBException {
    return getGebietsBaum(c.getID_Wahl());
  }

  public static GebietsBaum getGebietsBaum(String id_Wahl) throws EJBException {

    String id;
    Wahl wahl;
    if (id_Wahl != null) {
      WahlInfo wahlInfo = WahlInfo.getWahlInfo(id_Wahl);
      wahl = wahlInfo.getWahl();
      id = id_Wahl;
    } else {
      wahl = null;
      id = ""; //$NON-NLS-1$
    }
    GebietsBaum gebietsBaum;
    synchronized (__gebietsBaeume) {
      gebietsBaum = __gebietsBaeume.get(id);
      if (gebietsBaum == null) {
        gebietsBaum = new GebietsBaum(wahl);
        __gebietsBaeume.put(id, gebietsBaum);
      }
    }

    if (wahl != null) {
      wahl.readLock();
      gebietsBaum.refreshGebietNodes();
      gebietsBaum.berechneJavaScriptForNaviPart();
    }
    return gebietsBaum;
  }

  public String getJavaScriptForNaviPart() {
    return _javaScriptForNaviPart.toString();
  }

  boolean _lock;

  private synchronized void getLock() {
    try {
      while (_lock) {
        wait();
      }
      _lock = true;
    } catch (InterruptedException e) {
      LOGGER.debug("Tried to get a lock, but did not succed - thread was interrupted. " //$NON-NLS-1$
          + "It is now Somebody Else's Problem (TM by D. Adams"); //$NON-NLS-1$
    }
  }

  private synchronized void releaseLock() {
    _lock = false;
    notify();
  }

  private void berechneJavaScriptForNaviPart() {
    getLock();
    try {
      if (_javaScriptForNaviPart == null) {
        GebietHome gebietHome = (GebietHome) IVUBeanBase.findLocalHomeExt("Gebiet"); //$NON-NLS-1$
        StringBuilder ret = new StringBuilder();
        try {
          Collection<Gebiet> gebietCol = gebietHome
              .findAllIsErfassungseinheitByWahlOrderByGebietsartAndNummer(_wahl.getID_Wahl());
          int gebietsartCache = -1;
          int letzteNummer = -1;
          int anzahlIntervalle = 0;
          StringBuilder whileBuffer = new StringBuilder();
          int minNummer = -1;
          int maxNummer = -1;

          for (Iterator<Gebiet> gebietItr = gebietCol.iterator(); gebietItr.hasNext();) {
            Gebiet gebiet = gebietItr.next();
            int nummer = gebiet.getNummer();
            int gebietsart = gebiet.getGebietsart();
            if (gebietsartCache != gebietsart
                || (letzteNummer != -1 && nummer != (letzteNummer + 1))) {
              if (gebietsartCache != -1) {
                whileBuffer.append("var interval" + anzahlIntervalle + " = new Array(3);\n"); //$NON-NLS-1$ //$NON-NLS-2$
                whileBuffer.append("interval" + anzahlIntervalle + "[0] = " + minNummer + ";\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                whileBuffer.append("interval" + anzahlIntervalle + "[1] = " + maxNummer + ";\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                whileBuffer.append("interval" + anzahlIntervalle + "[2] = " + gebietsartCache //$NON-NLS-1$ //$NON-NLS-2$
                    + ";\n"); //$NON-NLS-1$
                whileBuffer.append("intervalle[" + (anzahlIntervalle - 1) + "]= interval" //$NON-NLS-1$ //$NON-NLS-2$
                    + anzahlIntervalle + ";\n"); //$NON-NLS-1$
              }
              minNummer = maxNummer = nummer;
              gebietsartCache = gebietsart;
              /*
               * nach vollst�ndigem Durchlauf der while-Schleife steht die Anzahl der Intervalle
               * fest
               */
              anzahlIntervalle++;
            }
            letzteNummer = nummer;
            minNummer = minNummer <= nummer ? minNummer : nummer;
            maxNummer = maxNummer >= nummer ? maxNummer : nummer;
            if (!gebietItr.hasNext()) {
              whileBuffer.append("var interval" + anzahlIntervalle + " = new Array(3);\n"); //$NON-NLS-1$ //$NON-NLS-2$
              whileBuffer.append("interval" + anzahlIntervalle + "[0] = " + minNummer + ";\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
              whileBuffer.append("interval" + anzahlIntervalle + "[1] = " + maxNummer + ";\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
              whileBuffer.append("interval" + anzahlIntervalle + "[2] = " + gebietsart + ";\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
              whileBuffer.append("intervalle[" + (anzahlIntervalle - 1) + "]= interval" //$NON-NLS-1$ //$NON-NLS-2$
                  + anzahlIntervalle + ";\n"); //$NON-NLS-1$
            }
          }
          ret.append("var intervalle = new Array(" + anzahlIntervalle + ");\n"); //$NON-NLS-1$ //$NON-NLS-2$
          ret.append(whileBuffer);
        } catch (FinderException e) {
          LOGGER.error(e, e);
        }
        _javaScriptForNaviPart = ret.toString();
      }
    } finally {
      releaseLock();
    }
  }

  private transient final Wahl _wahl;
  private transient GebietHome _gebietHome;
  private transient ErgebniseingangHome _ergebniseingangHome;
  private final String _id_Wahl;
  private DefaultMutableTreeNode _gebietRootNode;
  private Map<Object, DefaultMutableTreeNode> _gebietNo2TreeNode;
  private Map<String, Map<String, DefaultMutableTreeNode>> _gebietArt2TreeNodeMap;
  private Map<String, DefaultMutableTreeNode> _gebietId2TreeNode;

  private String _javaScriptForNaviPart;

  /**
   * <code>_timestampGebietNodes</code> dient zur Erkennung ob sich �berhaupt etwas ge�ndert hat
   */
  private long _timestampGebietNodes;

  /** int input mode, needed to rebuild Gebietsbaum after changes */
  private int _wahlergebnisartGebietNodes;

  /**
   * <code>_id_LetzterErgebniseingang</code> ist die Referenz zur inkrementellen �nderung des Baums
   */
  private String _id_LetzterErgebniseingang;

  /**
   * dient zum Erkennen des vollst�ndigen R�cksetztens der Wahl
   */
  private int _countErgebniseingang;
  private boolean _baumErzeugt;

  public void invalidate() {
    _baumErzeugt = false;
  }

  private GebietsBaum(Wahl wahl) {
    _wahl = wahl;
    if (wahl == null) {
      _id_Wahl = null;
      _wahlergebnisartGebietNodes = 0; // illegal value, but there is no election
    } else {
      _id_Wahl = (String) wahl.getPrimaryKey();
      _wahlergebnisartGebietNodes = _wahl.getAktuelleWahlergebnisart();
    }
  }

  private void refreshGebietNodes() {
    getLock();
    try {
      if (!_baumErzeugt) {
        _gebietRootNode = null;
      }
      long zeitLetzteAenderung;
      if (_wahl == null) {
        zeitLetzteAenderung = 0L;
      } else {
        final Timestamp letzteAenderung = _wahl.getLetzteAenderung();
        zeitLetzteAenderung = letzteAenderung == null ? 0L : letzteAenderung.getTime();

        if (_wahlergebnisartGebietNodes != _wahl.getAktuelleWahlergebnisart()) {
          _id_LetzterErgebniseingang = null;
          _gebietRootNode = null;
          _wahlergebnisartGebietNodes = _wahl.getAktuelleWahlergebnisart();
        }
      }

      if (_gebietRootNode == null || _timestampGebietNodes != zeitLetzteAenderung) {
        _baumErzeugt = false;
        boolean changed = true;
        String id_LetzterErgebniseingang = _id_LetzterErgebniseingang;
        try {
          if (_ergebniseingangHome == null) {
            _ergebniseingangHome = (ErgebniseingangHome) IVUBeanBase
                .findLocalHomeExt("Ergebniseingang"); //$NON-NLS-1$
          }
          // Erkennung des laufenden Zur�cksetzens der Wahl oder Wechsel der WEA: Baum muss komplett
          // regeneriert werden
          int countErgebniseingang = _ergebniseingangHome.countByWahlAndWahlergebnisart(_id_Wahl,
              _wahlergebnisartGebietNodes);
          if (countErgebniseingang < _countErgebniseingang) {
            LOGGER.info("Election is resetted at the moment, regions are renewed"); //$NON-NLS-1$
            _gebietRootNode = null;
          }
          _countErgebniseingang = countErgebniseingang;
          Ergebniseingang letzterErgebniseingang = _ergebniseingangHome
              .findLetzterByWahlAndWahlergebnisart(_id_Wahl, _wahlergebnisartGebietNodes);
          if (letzterErgebniseingang != null) {
            id_LetzterErgebniseingang = (String) letzterErgebniseingang.getPrimaryKey();
            changed = !id_LetzterErgebniseingang.equals(_id_LetzterErgebniseingang)
                || _gebietRootNode == null;
          }
        } catch (ObjectNotFoundException fe) {
          LOGGER.info("Election was reset, regions are renewed"); //$NON-NLS-1$
          _gebietRootNode = null;
        } catch (FinderException fe) {
          LOGGER.warn(fe);
          _gebietRootNode = null;
        }
        if (changed) {
          LOGGER.info("Generating regions"); //$NON-NLS-1$
          long start = System.currentTimeMillis();
          _timestampGebietNodes = zeitLetzteAenderung;
          Set<Object> hint = null;
          if (_gebietRootNode != null) {
            try {
              if (_gebietHome == null) {
                _gebietHome = (GebietHome) _wahl.getWurzelgebiet().getEJBLocalHome();
              }
              Collection<Gebiet> geaenderteGebiete = _gebietHome
                  .findAllByWahlAndWahlergebnisartGeaendertSeitErgebniseingang(_id_Wahl,
                      _wahlergebnisartGebietNodes,
                      _id_LetzterErgebniseingang);
              LOGGER.info(geaenderteGebiete.size()
                  + " regions were modified since the last calculation"); //$NON-NLS-1$
              hint = new HashSet<Object>();
              for (Gebiet gebiet : geaenderteGebiete) {
                hint.add(gebiet.getPrimaryKey());
              }
            } catch (FinderException fe) {
              LOGGER.warn(fe);
            }
          }
          berechneGesamtbaum(hint);
          berechneXRefs();
          _id_LetzterErgebniseingang = id_LetzterErgebniseingang;
          long elapsed = System.currentTimeMillis() - start;
          String elapsedStr = (elapsed / 1000) + "," + ((elapsed % 1000 + 50) / 100); //$NON-NLS-1$
          LOGGER.info("Generation of regions ready: " + elapsedStr + " s"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        _baumErzeugt = true;
      }
    } catch (FinderException fe) {
      throw new EJBException(fe);
    } finally {
      releaseLock();
    }
  }

  /**
   * @return Gebietsstruktur als Tree. Jeder Node enthaelt als UserObj ein Element von Gebietsinfo
   */
  public DefaultMutableTreeNode getWurzel() {
    return _gebietRootNode;
  }

  /**
   * @return {@link GebietInfo}-Objekt der Wurzel
   */
  public GebietInfo getWurzelInfo() {
    return (GebietInfo) getWurzel().getUserObject();
  }

  /**
   * Liefert den entsprechenden Knoten.
   * 
   * @param id_Gebiet Prim�rschl�ssel des Gebiets
   * @return Knoten, enthaelt als UserObj ein Element von {@link GebietInfo}. Eine Navigation ist
   *         von diesem Knoten aus moglich.
   */
  public DefaultMutableTreeNode getGebietsNode(String id_Gebiet) {
    return _gebietId2TreeNode.get(id_Gebiet);
  }

  /**
   * Liefert das {@link GebietInfo} zum entsprechenden Knoten.
   * 
   * @param id_Gebiet Prim�rschl�ssel des Gebiets
   * @return {@link GebietInfo}-Objekt des Gebiets
   */
  public GebietInfo getGebietInfo(String id_Gebiet) {
    return (GebietInfo) getGebietsNode(id_Gebiet).getUserObject();
  }

  /**
   * Liefert den entsprechenden Knoten.
   * 
   * @param gebietsart Gebietsart
   * @param nummer Gebietsnummer
   * @return Knoten, enthaelt als UserObj ein Element von {@link GebietInfo}. Eine Navigation ist
   *         von diesem Knoten aus moglich.
   */
  public DefaultMutableTreeNode getGebietsNode(int gebietsart, int nummer) {
    return _gebietNo2TreeNode.get(createKey(gebietsart, nummer));
  }

  /**
   * Liefert das {@link GebietInfo} zum entsprechenden Knoten.
   * 
   * @param gebietsart Gebietsart
   * @param nummer Gebietsnummer
   * @return {@link GebietInfo}-Objekt des Gebiets
   */
  public GebietInfo getGebietInfo(int gebietsart, int nummer) {
    return (GebietInfo) getGebietsNode(gebietsart, nummer).getUserObject();
  }

  public Map<String, Map<String, DefaultMutableTreeNode>> getGebietsNodes() {
    return _gebietArt2TreeNodeMap;
  }

  /**
   * @param gebietsart
   * @param gebietsnummer
   * @return Schl�sselobjekt (opaque)
   */
  public static Object createKey(final int gebietsart, final int gebietsnummer) {
    class GebietKey {
      private final int _gebietsart;
      private final int _gebietsnummer;

      /**
       */
      public GebietKey() {
        _gebietsart = gebietsart;
        _gebietsnummer = gebietsnummer;
      }

      @Override
      public boolean equals(Object obj) {
        GebietKey other = (GebietKey) obj;
        return _gebietsnummer == other._gebietsnummer && _gebietsart == other._gebietsart;
      }

      @Override
      public int hashCode() {
        return _gebietsnummer + (_gebietsart << 16);
      }
    }
    return new GebietKey();
  }

  /**
   */
  private void berechneXRefs() {
    Map<Object, DefaultMutableTreeNode> gebietNo2TreeNode = new HashMap<Object, DefaultMutableTreeNode>(
        500);
    Map<String, DefaultMutableTreeNode> gebietId2TreeNode = new HashMap<String, DefaultMutableTreeNode>(
        500);
    Map<String, Map<String, DefaultMutableTreeNode>> gebietArt2TreeNodeMap = new HashMap<String, Map<String, DefaultMutableTreeNode>>(
        10);
    Enumeration<?> enumeration = _gebietRootNode.preorderEnumeration();
    while (enumeration.hasMoreElements()) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
      if (node.getUserObject() instanceof GebietInfo) {
        GebietInfo gebietInfo = (GebietInfo) node.getUserObject();
        int nummer = gebietInfo.getNummer();
        int gebietsart = gebietInfo.getGebietsart();
        gebietNo2TreeNode.put(createKey(gebietsart, nummer), node);
        gebietId2TreeNode.put(gebietInfo.getID_Gebiet(), node);
        String gebietsartObj = String.valueOf(gebietsart);
        Map<String, DefaultMutableTreeNode> gebietArt2TreeNode = gebietArt2TreeNodeMap
            .get(gebietsartObj);
        if (gebietArt2TreeNode == null) {
          gebietArt2TreeNode = new HashMap<String, DefaultMutableTreeNode>(100);
          gebietArt2TreeNodeMap.put(gebietsartObj, gebietArt2TreeNode);
        }
        gebietArt2TreeNode.put(String.valueOf(nummer), node);
      }
    }
    _gebietNo2TreeNode = gebietNo2TreeNode;
    _gebietId2TreeNode = gebietId2TreeNode;
    _gebietArt2TreeNodeMap = gebietArt2TreeNodeMap;
  }

  /**
   * Berechnet einen vollst�ndig aufgebauten Baum. Dieser enth�lt s�mtliche Objekte f�r die
   * �bergebenen Wahl
   * 
   * @param hint Set der ge�nderten Gebiete; wenn <code>null</code> wird der Baum vollst�ndig neu
   *          berechnet
   * @throws FinderException wenn die untergeordneten Gebiete nicht gefunden werden konnten
   */
  private void berechneGesamtbaum(Set<Object> hint) throws FinderException {
    if (_gebietHome == null) {
      _gebietHome = (GebietHome) _wahl.getWurzelgebiet().getEJBLocalHome();
    }
    Gebiet ebene0 = _wahl.getWurzelgebiet();
    String id_Ebene0 = (String) ebene0.getPrimaryKey();
    Collection<Gebiet> kinder = _gebietHome.findAllByUebergeordnetesGebietSortByNummer(id_Ebene0);
    if (_gebietRootNode == null || hint == null || hint.contains(id_Ebene0)) {
      GebietInfo gebietInfo = new GebietInfo(ebene0);
      gebietInfo.setNodePath("0"); //$NON-NLS-1$
      _gebietRootNode = new DefaultMutableTreeNode(gebietInfo);
      gebietInfo.setNode(_gebietRootNode);
    } else {
      _gebietRootNode = new DefaultMutableTreeNode(_gebietRootNode.getUserObject());
    }
    berechneUntergebiete(_gebietRootNode, kinder, "0", hint); //$NON-NLS-1$
  }

  private void berechneUntergebiete(DefaultMutableTreeNode parentNode,
      Collection<Gebiet> unterGebieteCol,
      String nodePathPrefix,
      Set<Object> hint) throws FinderException {

    int erwartet = 0;
    int eingegangen = 0;
    int gesamt = 0;
    Iterator<Gebiet> unterGebieteIter = unterGebieteCol.iterator();
    for (int i = 0; unterGebieteIter.hasNext(); i++) {
      Gebiet gebiet = unterGebieteIter.next();
      DefaultMutableTreeNode node = createNode(nodePathPrefix, i, gebiet, hint);
      GebietInfo untergebietInfo = (GebietInfo) node.getUserObject();
      boolean isElterngebiet = !untergebietInfo.isErfassungseinheit();
      if (isElterngebiet) {
        Collection<Gebiet> gebieteSortByNummerCol = _gebietHome
            .findAllByUebergeordnetesGebietSortByNummer((String) gebiet.getPrimaryKey());
        if (!gebieteSortByNummerCol.isEmpty()) {
          // isElterngebiet = true;
          berechneUntergebiete(node, gebieteSortByNummerCol, untergebietInfo.getNodePath(), hint);
        }
      }

      if (untergebietInfo.isAuswertungseinheitOderTiefer()) {
        // Pr�fung wegen Sondergebiete und �nhliche die eventuell nicht gez�hlt werden
        if (untergebietInfo.getAnzahlGesamt() > 0) {
          gesamt++;
        }
      } else {
        gesamt += untergebietInfo.getAnzahlGesamt();
      }

      if (untergebietInfo.isAuswertungseinheitOderTiefer()) {
        // Pr�fung wegen Sondergebiete und �nhliche die eventuell nicht gez�hlt werden
        if (untergebietInfo.getAnzahlGesamt() > 0) {
          erwartet++;
          if (untergebietInfo.isVollstaendig()) {
            eingegangen++;
          }
        }
      } else {
        erwartet += untergebietInfo.getAnzahlErwartet();
        eingegangen += untergebietInfo.getAnzahlEingegangen();
      }

      parentNode.add(node);
    }

    if (parentNode.getUserObject() instanceof GebietInfo) {
      GebietInfo parentGebietInfo = (GebietInfo) parentNode.getUserObject();
      parentGebietInfo.setAnzahlGesamt(gesamt);
      parentGebietInfo.setAnzahlErwartet(erwartet);
      parentGebietInfo.setAnzahlEingegangen(eingegangen);
    }
  }

  private DefaultMutableTreeNode createNode(String nodePathPrefix,
      int i,
      Gebiet gebiet,
      Set<Object> hint) {
    // diesen Level mit aufnehmen
    GebietInfo gebietInfo;
    String id_Gebiet = (String) gebiet.getPrimaryKey();
    if (hint != null && !hint.contains(id_Gebiet) && _gebietId2TreeNode != null) {
      gebietInfo = (GebietInfo) getGebietsNode(id_Gebiet).getUserObject();
    } else {
      gebietInfo = new GebietInfo(gebiet);
      if (nodePathPrefix != null) {
        gebietInfo.setNodePath(nodePathPrefix + SEPARATOR + i);
      } else {
        gebietInfo.setNodePath("" + i); //$NON-NLS-1$
      }
    }
    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(gebietInfo);
    gebietInfo.setNode(newNode);
    return newNode;
  }
}