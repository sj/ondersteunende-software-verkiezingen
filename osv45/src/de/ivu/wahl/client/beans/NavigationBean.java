/*
 * Created on 01.12.2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.ivu.wahl.client.beans;

import java.io.Serializable;
import java.util.Enumeration;

import javax.ejb.EJBException;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Logger;

import de.ivu.wahl.GebietsBaum;
import de.ivu.wahl.auswertung.erg.NavigationErgebnis;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietInfo;
import de.ivu.wahl.modell.Gebietsart;

/**
 * Clientbean zur Beschaffung eines Navigationsbaumes. Die Arbeit wird weitgehend auf der
 * Serverseite durch. de.ivu.wahl.auswertung.NavigationHandlingBean erbracht.
 * 
 * @author bae@ivu.de Copyright (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
public class NavigationBean implements Serializable {
  private static final long serialVersionUID = -8562972341530053407L;

  /** Logger for instances of this class */
  protected static final Logger LOGGER = Logger.getLogger(NavigationBean.class);

  /**
   * liefert einen Navigationsbaum mit den darzustellenden Treenodes, und nur diesen Nodes. Er
   * stellt somit einen Ausschnitt aus dem Gesamtnode dar. Der Nodepath eines jeden Knotens ist
   * Vergleichbar mit dem des Gesamttrees, jedoch nicht gleich. Dem Nodepath ist eine Roothierarchie
   * hinzugef�gt, die die einzelnen Wahlartlevel representieren. Zum Beispiel 0_1_2 k�nnte im
   * GesamtNode 1_2 heissen.
   * 
   * @param appBean
   * @param strNaviankerPath
   * @return Navigationsbaum mit den darzustellenden Treenodes
   * @throws EJBException
   */
  public NavigationErgebnis getNavigationTree(ApplicationBean appBean, String strNaviankerPath)
      throws EJBException {

    return extractNavigationTreeFromGesamtTree(appBean, appBean.getInitGuiCommand()
        .getWahlartLevels(), strNaviankerPath, null);
  }

  /**
   * liefert einen AdminNavigationsbaum mit den darzustellenden Treenodes, und nur diesen Nodes. Er
   * stellt somit einen Ausschnitt aus dem Gesamtnode dar. Der Nodepath eines jeden Knotens ist
   * Vergleichbar mit dem des Gesamttrees, jedoch nicht gleich. Dem Nodepath ist eine Roothierarchie
   * hinzugef�gt, die die einzelnen Wahlartlevel representieren. Zum Beispiel 0_1_2 k�nnte im
   * GesamtNode 1_2 heissen.
   * 
   * @param appBean
   * @param strNaviankerPath
   * @return AdminNavigationsbaum mit den darzustellenden Treenodes
   * @throws EJBException
   */
  public NavigationErgebnis getAdminTree(ApplicationBean appBean, String strNaviankerPath)
      throws EJBException {
    return getAdminTree(appBean, strNaviankerPath, null);

  }

  public NavigationErgebnis getAdminTreeFilterAnwGebiet(ApplicationBean appBean,
      String strNaviankerPath) throws EJBException {
    return getAdminTree(appBean, strNaviankerPath, appBean.getAnwContext().getID_Gebiet());
  }

  public NavigationErgebnis getAdminTree(ApplicationBean appBean,
      String strNaviankerPath,
      String filterGebiet) throws EJBException {
    return extractNavigationTreeFromGesamtTree(appBean, appBean.getInitGuiCommand()
        .getAdminWahlartLevels(), strNaviankerPath, filterGebiet);
  }

  private NavigationErgebnis extractNavigationTreeFromGesamtTree(ApplicationBean appBean,
      int[][] wahlartLevels,
      String strNaviankerPath,
      String filterGebiet) throws EJBException {

    NavigationErgebnis navErg = new NavigationErgebnis("NavigationTree"); //$NON-NLS-1$
    DefaultMutableTreeNode gesamtTreeRootNode = appBean.getGebietsBaum().getWurzel();

    if (wahlartLevels == null) {
      // TODO was ist zu tun?
      return null;
    }
    int[] naviankerPath = strPath2int(strNaviankerPath);

    for (int posInWahlartLevels = 0; posInWahlartLevels < wahlartLevels.length; posInWahlartLevels++) {
      int[] anzuzeigendeLevel = wahlartLevels[posInWahlartLevels];
      navErg.addLevel(genterateTeilBaum(anzuzeigendeLevel,
          gesamtTreeRootNode,
          posInWahlartLevels,
          naviankerPath,
          filterGebiet));
    }
    return navErg;
  }

  /**
   * Bearbeitet den Baum f�r einen wahlart-Level
   * 
   * @return rootNode gefuellt mit allen darzustellenden Kindern. Der rootNode ist nicht
   *         darzustellen, sondern nur ein Container. Die Kinder enthalten die Informationen im
   *         UserObj.
   */
  private DefaultMutableTreeNode genterateTeilBaum(int[] anzuzeigendeLevels,
      DefaultMutableTreeNode rootNode,
      int posInWahlartLevel_noTeilbaum,
      int[] naviankerpath,
      String filterGebiet) {

    String gebietId = ((GebietInfo) rootNode.getUserObject()).getID_Gebiet();
    boolean isFilterGebietAchse = isFilterGebietAchse(filterGebiet, gebietId, false);
    int aktuellerLevel = anzuzeigendeLevels[0];
    DefaultMutableTreeNode retNode = new DefaultMutableTreeNode(
        Gebietsart.getKlartext(aktuellerLevel));
    if (isAddTeilBaum(naviankerpath, posInWahlartLevel_noTeilbaum)) {
      addGebiet(retNode,
          anzuzeigendeLevels,
          0,
          1,
          naviankerpath,
          0,
          rootNode,
          filterGebiet,
          isFilterGebietAchse);
    }
    return retNode;
  }

  private boolean isFilterGebietAchse(String filterGebiet,
      String node_ID,
      boolean isFilterGebietAchse) {
    if (filterGebiet == null || isFilterGebietAchse) {
      return true;
    }
    return filterGebiet.equals(node_ID);
  }

  /**
   * Helper: soll dieser Teilbaum angezeigt werden.
   * 
   * @param naviankerPath Definition der Treevorgabe
   * @param posWahlartLevels_noTeilBaum aktuelle Position im Tree
   * @return true, wenn die folgende Ebene mit aufgenommen werden soll
   */
  private boolean isAddTeilBaum(int[] naviankerPath, int posWahlartLevels_noTeilBaum) {
    if (naviankerPath.length > 0) {
      // Es liegt ein naviPath vor
      boolean retValue = naviankerPath[0] == posWahlartLevels_noTeilBaum;
      LOGGER.debug(Messages
          .getString(MessageKeys.Logger_NavipathVorhanden_isAddTeilBaum_FuerTeilbaum)
          + posWahlartLevels_noTeilBaum + " " + retValue); //$NON-NLS-1$
      return retValue;
    }
    LOGGER.debug(Messages.getString(MessageKeys.Logger_Not_isAddTeilBaum_DaKeinNavipathVorhanden));
    return false;
  }

  /**
   * rekursives Aufbauen der Baumstruktur mit der Einschraenkung auf die anzuzeigenden Wahlarten
   */
  private void addUntergebiete(final DefaultMutableTreeNode gesamtTreeParentNode,
      final DefaultMutableTreeNode newTreeParent,
      final int[] anzuzeigendeWahlArten,
      final int posAnzuzeigendeWahlArten,
      final int posNaviankerPath,
      final int[] naviankerPath,
      final String filterGebiet,
      final boolean filterGebietAchse) {

    // Iterator unterGebieteIter = unterGebieteCol.iterator();
    Enumeration<?> children = gesamtTreeParentNode.children();
    for (int posChildren = 0; children.hasMoreElements(); posChildren++) {
      DefaultMutableTreeNode childNodeGesamtTree = (DefaultMutableTreeNode) children.nextElement();
      addGebiet(newTreeParent,
          anzuzeigendeWahlArten,
          posAnzuzeigendeWahlArten,
          posNaviankerPath,
          naviankerPath,
          posChildren,
          childNodeGesamtTree,
          filterGebiet,
          filterGebietAchse);
    }
  }

  private void addGebiet(final DefaultMutableTreeNode newTreeParent,
      final int[] anzuzeigendeLevels,
      final int posInAnzuzeigendeLevels,
      final int posNaviankerPath,
      final int[] naviankerPath,
      final int posChildren,
      final DefaultMutableTreeNode childNodeGesamtTree,
      final String filterGebiet,
      final boolean pFilterGebietAchse) {

    int nextPosNaviankerPath = posNaviankerPath + 1;
    int nextPosInAnzuzeigendeLevels = posInAnzuzeigendeLevels;
    GebietInfo gebietInfo = (GebietInfo) childNodeGesamtTree.getUserObject();
    boolean fittingLevel = gebietInfo.getGebietsart() == anzuzeigendeLevels[posInAnzuzeigendeLevels];
    if (fittingLevel) {
      nextPosInAnzuzeigendeLevels++;
    }
    boolean filterGebietAchse = isFilterGebietAchse(filterGebiet,
        gebietInfo.getID_Gebiet(),
        pFilterGebietAchse);
    if (fittingLevel && filterGebietAchse) {
      DefaultMutableTreeNode nodeNewTree = addNode(newTreeParent, gebietInfo);
      // weitere Untergebiete rekursiv laden
      if ((posInAnzuzeigendeLevels + 1) < anzuzeigendeLevels.length
          && isAddNextUntergebiet(naviankerPath, posNaviankerPath, posChildren)) {
        addUntergebiete(childNodeGesamtTree,
            nodeNewTree,
            anzuzeigendeLevels,
            nextPosInAnzuzeigendeLevels,
            nextPosNaviankerPath,
            naviankerPath,
            filterGebiet,
            filterGebietAchse);
      }
    } else if (anzuzeigendeLevels.length >= nextPosInAnzuzeigendeLevels) {
      LOGGER.debug(Messages
          .getString(MessageKeys.Logger_DiesenLevelUeberspringenAberAbtauchenInDenNaechsten));
      addUntergebiete(childNodeGesamtTree,
          newTreeParent,
          anzuzeigendeLevels,
          nextPosInAnzuzeigendeLevels,
          nextPosNaviankerPath,
          naviankerPath,
          filterGebiet,
          filterGebietAchse);
    }
  }

  private boolean isAddNextUntergebiet(int[] naviankerPath, int posNaviankerPath, int posChildren) {
    boolean retValue = naviankerPath.length > posNaviankerPath
        && naviankerPath[posNaviankerPath] == posChildren;
    LOGGER.debug("isAddNextUntergebiet " + posNaviankerPath + " " + posChildren + " " + retValue); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return retValue;
  }

  private DefaultMutableTreeNode addNode(DefaultMutableTreeNode parentNode, GebietInfo gebietInfo) {

    /*
     * todo evtl. hier dynamisch die Sortierung unterhalb des Parent nach einer lokalen Eigenschaft
     * von Child sicherstellen und mit insert einf�gen
     */
    DefaultMutableTreeNode node = new DefaultMutableTreeNode(gebietInfo);
    parentNode.add(node);

    return node;
  }

  /**
   * liefert eine Instanz aus dem GesamtTree mit �bereinstimmenden NodePath
   * 
   * @see #getNavigationTree(ApplicationBean, String)
   * @param appBean
   * @param nodePath
   * @param isNavigationPath Falls true wird ein NodePath mit vorarngestelltem Wahlartlevel erwartet
   * @return Instanz aus dem GesamtTree mit �bereinstimmenden NodePath
   * @throws EJBException
   */
  public static DefaultMutableTreeNode getTreeNode(ApplicationBean appBean,
      String nodePath,
      boolean isNavigationPath) throws EJBException {

    DefaultMutableTreeNode rootNode = getRootEbene(appBean);
    if (nodePath == null) {
      return rootNode;
    }
    int[] path = strPath2int(nodePath);

    int startPunkt = 0;
    if (isNavigationPath) {
      startPunkt = 2;
    }

    for (int i = startPunkt; i < path.length; i++) {
      rootNode = (DefaultMutableTreeNode) rootNode.getChildAt(path[i]);
    }
    return rootNode;
  }

  /**
   * liefert ein Navigation-Objekt, bei dem das NavigationErgebnis-Objekt nur mit dem Root-Element
   * (z.B. Bund) gef�llt ist Ermittelt wird der Wert aus dem Wahlobjekt
   * 
   * @param appBean
   * @return Navigation-Objekt, bei dem das Navigation-Ergebnisobjekt nur mit dem Root-Element
   *         gef�llt ist
   * @throws EJBException
   */
  public static DefaultMutableTreeNode getRootEbene(ApplicationBean appBean) throws EJBException {
    return appBean.getGebietsBaum().getWurzel();
  }

  /**
   * Liefert einen Enumerator, der immer nur ein Element enth�lt, n�mlich das �bergebene. Verwendung
   * findet dieser Enumerator im UebersichtWahlkreise.jsp, f�r den Fall, dass die zweite eben sich
   * aus Wahleinheiten zusammensetzen und somit eine horizontal Darstellung gew�nscht ist.
   * 
   * @param treeNode
   * @return Enumerator, der nur das �bergebene Element enth�lt
   */
  public Enumeration<DefaultMutableTreeNode> getSingleEnumeration(DefaultMutableTreeNode treeNode) {
    return new SingleEnumeration(treeNode);
  }

  /**
   * Helper: Umwandlung des Navigationspathes in ein leichter zu verabeitendes int[] Wenn kein
   * NaviankerPath uebergeben wurde, dann wird ein Array der Laenge 0 zurueckgegeben.
   * 
   * @param naviankerPath Navigationspath
   * @return int[] aus dem Navigationspath
   */
  private static int[] strPath2int(String naviankerPath) {
    int[] retValue = new int[1];
    if (naviankerPath != null) {
      String[] tokens = naviankerPath.split("_"); //$NON-NLS-1$
      retValue = new int[tokens.length];
      for (int i = 0; i < tokens.length; i++) {
        retValue[i] = Integer.parseInt(tokens[i]);
      }
    }
    LOGGER.debug("strPath2int " + naviankerPath + " retValue.length " + retValue.length); //$NON-NLS-1$ //$NON-NLS-2$
    return retValue;
  }

  private static class SingleEnumeration implements Enumeration<DefaultMutableTreeNode> {

    private final DefaultMutableTreeNode _treeNode;

    private boolean _is1stStep = true;

    public SingleEnumeration(DefaultMutableTreeNode treeNode) {
      _treeNode = treeNode;
    }

    /** {@inheritDoc} */
    public boolean hasMoreElements() {
      return _is1stStep;
    }

    /** {@inheritDoc} */
    public DefaultMutableTreeNode nextElement() {
      if (_is1stStep) {
        _is1stStep = false;
        return _treeNode;
      }
      return null;
    }
  }

  /**
   * Liefert den Nodepath zu einem Gebiet. Der Treecounter (z.b. 0_ f�r den ersten Baum) muss noch
   * hinzugef�gt werden. Wird das Gebiet nicht gefunden, wird der Pfad zum Root-Knoten geliefert
   * 
   * @param appBean
   * @param id_Gebiet
   * @return Nodepath zu einem Gebiet
   */
  public String getGebietsNodePath(ApplicationBean appBean, String id_Gebiet) {
    GebietsBaum gb = appBean.getGebietsBaum();
    DefaultMutableTreeNode tn = gb.getGebietsNode(id_Gebiet);
    if (tn == null) {
      tn = gb.getWurzel();
    }
    return ((GebietInfo) tn.getUserObject()).getNodePath();
  }
}
