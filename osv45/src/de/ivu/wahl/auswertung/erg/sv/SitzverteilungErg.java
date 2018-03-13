package de.ivu.wahl.auswertung.erg.sv;

import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import de.ivu.util.debug.Log4J;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.auswertung.erg.Ergebnis;

/**
 * Ergebnisobjekt zum Transport der Sitzverteilung vom Server zum Client
 * 
 * @author mur@ivu.de, cos@ivu.de Copyright (c) 2004 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
public class SitzverteilungErg extends Ergebnis {
  private static final long serialVersionUID = 1831519522587865476L;

  private final Set<Gruppenzeile> _gruppenzeileCol = new TreeSet<Gruppenzeile>();

  private static final Logger LOGGER = Logger.getLogger(SitzverteilungErg.class);
  static {
    LOGGER.info(Log4J
        .dumpVersion(SitzverteilungErg.class, Log4J.extractVersion("$Revision: 1.10 $"))); //$NON-NLS-1$
  }

  /**
   * Liefert eine Collection von Gebietlistenquote f�r die Partei
   * 
   * @return Collection von Gebietlistenquote f�r die Partei
   */
  public Set<Gruppenzeile> getGruppenzeileCol() {
    return _gruppenzeileCol;
  }

  public boolean addGruppenzeile(Gruppenzeile gz) {
    return _gruppenzeileCol.add(gz);
  }

  /**
   * Constructor
   */
  @SuppressWarnings("unused")
  public SitzverteilungErg(String ergBezeichnung, WahlInfo wahlInfo) {
    super(ergBezeichnung);
  }

}