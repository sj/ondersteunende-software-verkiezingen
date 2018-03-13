package de.ivu.wahl.anwender;

import static de.ivu.wahl.AnwContext.ID_PWAHL_SUPER_ADMIN;
import static de.ivu.wahl.Konstanten.LOCALE;
import static java.lang.System.currentTimeMillis;

import java.io.Serializable;
import java.text.Collator;

import javax.ejb.EJBException;

import de.ivu.ejb.IVUBeanBase;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.modell.ejb.Anwender;

/**
 * Datensatz eines angemeldeten Anwenders mit allen Laufzeitdaten
 * 
 * @author cos@ivu.de - IVU Traffic Technologies AG
 */
public class Anmeldung implements Serializable, Comparable<Anmeldung> {
  private static final long serialVersionUID = -7287400978198666243L;
  public String _gebietsname; // null, wenn keinem Land zugeordnet
  public String _id;
  public String _loginName;
  public String _name;
  public long _time;
  public String _idWahl = null;
  public String _wahlName;

  /**
   * Anmeldung erzeugen
   * 
   * @throws EJBException bei einem Problem
   */
  Anmeldung(Anwender a, String id_Wahl) throws EJBException {
    _time = currentTimeMillis();
    _name = a.getName();
    _loginName = a.getAnwendername();
    _id = a.getID_Anwender();
    _gebietsname = a.getGebiet() != null ? a.getGebiet().getName() : null;
    _idWahl = id_Wahl;
    if (ID_PWAHL_SUPER_ADMIN.equals(id_Wahl)) {
      _wahlName = ""; //$NON-NLS-1$
    } else {
      _wahlName = WahlInfo.getWahlInfo(id_Wahl).getWahlName();
    }
  }

  public int compareTo(Anmeldung anm2) {
    if (equals(anm2)) {
      return 0;
    }
    Collator deCollator = Collator.getInstance(LOCALE);
    try {
      String gName1 = _gebietsname;
      String gName2 = anm2._gebietsname;
      if (IVUBeanBase.equals(gName1, gName2)) {
        return deCollator.compare(_name, anm2._name);
      }
      if (gName1 == null) {
        return -1;
      }
      if (gName2 == null) {
        return 1;
      }
      return deCollator.compare(gName1, gName2);
    } catch (RuntimeException ex) {
      AnwenderHandlingBean.LOGGER.error(ex, ex);
      throw ex;
    }
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj) || (obj instanceof Anmeldung && _id.equals(((Anmeldung) obj)._id));
  }

  @Override
  public int hashCode() {
    return _id == null ? super.hashCode() : _id.hashCode();
  }

  @Override
  public String toString() {
    return _loginName + " (" + _name + ')' + (_gebietsname == null ? "" : (" @" + _gebietsname)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }
}