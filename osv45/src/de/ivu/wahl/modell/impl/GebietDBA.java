package de.ivu.wahl.modell.impl;

/**
 * GebietDBA
 *
 * @author cos@ivu.de  (c) 2003-4 IVU Traffic Technologies AG
 */

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;

public class GebietDBA extends BasicGebietDBA {
  private static final long serialVersionUID = -4152392337169714239L;

  private static final Category LOGGER = Log4J.configure(GebietDBA.class);

  static {
    LOGGER.info(Log4J.dumpVersion(GebietDBA.class, Log4J.extractVersion("$Revision: 1.6 $"))); //$NON-NLS-1$
  }

  public static Map<String, BigDecimal> getWahlbeteiligungWahleinheiten(String id_Wahl, boolean asc)
      throws SQLException {
    return retrieveIDBigDecimalMap(null,
        "select sWAE.ID_Gebiet, sWAE.Stimmen/sWB.Stimmen*100 as btg " //$NON-NLS-1$
            + "from Wahl join Gebiet using(ID_Wahl) " //$NON-NLS-1$
            + "join GruppeGebietsspezifisch ggWB on Gebiet.ID_Gebiet=ggWB.ID_Gebiet " //$NON-NLS-1$
            + "join Stimmergebnis sWB on ggWB.ID_GruppeGebietsspezifisch=sWB.ID_GruppeGebietsspezifisch " //$NON-NLS-1$
            + "join Gebiet_Ergebniseingang on sWB.ID_Gebiet=Gebiet_Ergebniseingang.ID_Gebiet " //$NON-NLS-1$
            + "and sWB.ID_Ergebniseingang=Gebiet_Ergebniseingang.ID_Ergebniseingang " //$NON-NLS-1$
            + "join Stimmergebnis sWAE on Gebiet_Ergebniseingang.ID_Gebiet=sWAE.ID_Gebiet " //$NON-NLS-1$
            + "and Gebiet_Ergebniseingang.ID_Ergebniseingang=sWAE.ID_Ergebniseingang " //$NON-NLS-1$
            + "join GruppeGebietsspezifisch ggWAE on sWAE.ID_GruppeGebietsspezifisch=ggWAE.ID_GruppeGebietsspezifisch " //$NON-NLS-1$
            + "where ggWB.Position=-4 " + "and ggWAE.Position=-3 " + "and Wahleinheit!=0 " //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
            + "and sWB.Stimmen != 0 " + "and sWAE.Wahlergebnisart=AktuelleWahlergebnisart " //$NON-NLS-1$ //$NON-NLS-2$
            + "and sWB.Wahlergebnisart=AktuelleWahlergebnisart " + "and Wahl.ID_Wahl = ? " //$NON-NLS-1$ //$NON-NLS-2$
            + "order by btg " + (asc ? "asc" : "desc"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        new Object[]{id_Wahl},
        null);
  }

}
