package de.ivu.wahl.auswertung.erg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * NavigationErgebnis.java NavigationErgebnis-Objekt stellt alle Gebiets-Informationen f�r den
 * Navigationsbaum zur Verf�gung.
 * 
 * @author bae@ivu.de 16.11.2003 Copyright (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public class NavigationErgebnis extends Ergebnis {
  private static final long serialVersionUID = 4189880013309930150L;

  private final List<DefaultMutableTreeNode> _levels;

  public NavigationErgebnis(String ergBezeichnung) {
    super(ergBezeichnung);
    _levels = new ArrayList<DefaultMutableTreeNode>();
  }

  /**
   * Weiter Wahlart hinzuf�gen
   * 
   * @param level
   */
  public void addLevel(DefaultMutableTreeNode level) {
    _levels.add(level);
  }

  /**
   * Liste mit allen Wahlarten
   * 
   * @return Liste mit allen Wahlarten
   */
  public List<DefaultMutableTreeNode> getList() {
    return _levels;
  }

  /**
   * Iterator �ber die Wahlarten
   * 
   * @return Iterator �ber die Wahlarten
   */
  public Iterator<DefaultMutableTreeNode> getLevelIterator() {
    return _levels.iterator();
  }
}
