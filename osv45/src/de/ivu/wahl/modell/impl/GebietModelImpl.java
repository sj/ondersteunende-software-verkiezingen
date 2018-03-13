package de.ivu.wahl.modell.impl;

/**
 * GebietModelImpl
 * 
 * @author cos@ivu.de (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.wahl.export.Roman;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.Gebietsart;

public class GebietModelImpl extends BasicGebietModelImpl {
  private static final long serialVersionUID = -4920402909228361654L;
  private static final Category LOGGER = Log4J.configure(GebietModelImpl.class);
  static {
    LOGGER.info(Log4J.dumpVersion(GebietModelImpl.class, Log4J.extractVersion("$Revision: 1.5 $"))); //$NON-NLS-1$
  }

  /**
   * @return technische, aber menschenlesbare zusammengesetzte Bezeichnung f�r das Gebiet
   */
  public String getBezeichnung() {
    return (_id_UebergeordnetesGebiet == null ? "" : getGebietsartKlartext() + " " + _nummer + ": ") + _name; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  /**
   * @return Gebietsart als Klartext aus der internen Tabelle
   */
  public String getGebietsartKlartext() {
    return Gebietsart.getGebietsartKlartext(this);
  }

  public GebietModelImpl() {
    super();
  }

  public GebietModelImpl(String id_Gebiet) {
    super(id_Gebiet);
  }

  @Override
  public String getNumber4Display() {
    if (_gebietsart == GebietModel.GEBIETSART_GEMEINDE) {
      return formatNumber(_nummer, 4);
    } else if (_roemisch) {
      return Roman.toRoman(Long.valueOf(_nummer));
    } else {
      return String.valueOf(_nummer);
    }
  }

  /**
   * Should complete the numberFormat methods
   * 
   * @param num rechtsb�ndig zu formatierende Ausgabe
   * @param length Gesamtl�nge der Ausgabe
   * @return rechtsb�ndig formatierte Ausgabe mit f�hrenden Leerzeichen
   */
  public static String formatNumber(int num, int length) {
    String numberStr = String.valueOf(num);
    while (numberStr.length() < length) {
      numberStr = "0" + numberStr; //$NON-NLS-1$
    }
    return numberStr;
  }
}
