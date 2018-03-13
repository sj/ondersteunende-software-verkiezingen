/*
 * Created on 25.11.2003
 */
package de.ivu.wahl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUBeanBase;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GruppeGebietsspezifischModel;
import de.ivu.wahl.modell.GruppeKonstanten;
import de.ivu.wahl.modell.GruppeModel;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifisch;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifischHome;
import de.ivu.wahl.modell.ejb.GruppeHome;

/**
 * GruppenInfo
 * 
 * @author ugo@ivu.de cos@ivu.de, IVU Traffic Technologies AG
 * @since 25.11.2003
 */
public class GruppenInfo implements Serializable, Cloneable {
  private static final long serialVersionUID = 6853382423533077808L;
  private static final Category LOGGER = Log4J.configure(GruppenInfo.class);
  static {
    LOGGER.info(Log4J.dumpVersion(GruppenInfo.class, Log4J.extractVersion("$Revision: 1.8 $"))); //$NON-NLS-1$
  }

  private final Map<GGKey, Integer> _gruppePositionBySchluesselGebietsnummerUndArt = new HashMap<GGKey, Integer>();
  private final Map<GGKey, Integer> _gruppeSchluesselByPositionGebietsnummerUndArt = new HashMap<GGKey, Integer>();
  private final Map<String, GruppeModel> _gruppe2Model = new HashMap<String, GruppeModel>();
  private final Map<String, GruppeGebietsspezifischModel> _gruppeGebietsspezifisch2Model = new HashMap<String, GruppeGebietsspezifischModel>();
  private final Map<GKey, Set<Integer>> _zugelassenePositionen = new HashMap<GKey, Set<Integer>>();
  private final Map<GGKey, Set<Integer>> _zugelasseneKandidaten = new HashMap<GGKey, Set<Integer>>();
  private final Map<GKey, Map<Integer, GruppeGebietsspezifischModel>> _gebiete = new HashMap<GKey, Map<Integer, GruppeGebietsspezifischModel>>();
  private final transient GruppeGebietsspezifischHome _ggHome;
  private final WahlInfo _wahlInfo;

  /**
   * @param wahlInfo
   * @throws EJBException
   */
  public GruppenInfo(WahlInfo wahlInfo) throws EJBException {
    try {
      _ggHome = IVUBeanBase.findLocalHomeExt("GruppeGebietsspezifisch"); //$NON-NLS-1$
      _wahlInfo = wahlInfo;
      GruppeHome grHome = IVUBeanBase.findLocalHomeExt("Gruppe"); //$NON-NLS-1$
      for (Gruppe gruppe : grHome.findAllByWahl(wahlInfo.getID_Wahl())) {
        GruppeModel gruppeModel = gruppe.getDetails();
        _gruppe2Model.put(gruppeModel.getID_Gruppe(), gruppeModel);
      }
    } catch (FinderException e) {
      throw new EJBException(e);
    }
  }

  private void init4Gebiet(int gebietsart, int gebietsnummer) throws EJBException {
    GKey key = new GKey(gebietsart, gebietsnummer);
    if (!_gebiete.containsKey(key)) {
      try {
        Set<Integer> positionen = new HashSet<Integer>();
        Map<Integer, GruppeGebietsspezifischModel> position2GG = new TreeMap<Integer, GruppeGebietsspezifischModel>();

        Collection<GruppeGebietsspezifisch> ggCol = _ggHome.findAllByGebiet(_wahlInfo
            .getID4Gebiet(gebietsart, gebietsnummer));
        for (GruppeGebietsspezifisch gruppeGebietsspezifisch : ggCol) {
          Set<Integer> kandidaten = new HashSet<Integer>();
          GruppeGebietsspezifischModel ggm = gruppeGebietsspezifisch.getDetails();
          _gruppeGebietsspezifisch2Model.put(ggm.getID_GruppeGebietsspezifisch(), ggm);
          GruppeModel gruppeModel = _gruppe2Model.get(ggm.getID_Gruppe());
          int schluessel = gruppeModel.getSchluessel();
          int position = ggm.getPosition();
          if (ggm.isListeZugelassen()
              || position == GruppeKonstanten.GruppeAllgemein.WAEHLER.getPosition()
              || position == GruppeKonstanten.GruppeAllgemein.WAHLBERECHTIGTE.getPosition()) {
            positionen.add(position);
          }
          _gruppePositionBySchluesselGebietsnummerUndArt.put(new GGKey(schluessel, gebietsart,
              gebietsnummer), position);
          _gruppeSchluesselByPositionGebietsnummerUndArt.put(new GGKey(position, gebietsart,
              gebietsnummer), schluessel);

          position2GG.put(position, ggm);
          // TODO: �ber Kandidaten iterieren
          _zugelasseneKandidaten.put(new GGKey(schluessel, gebietsart, gebietsnummer), kandidaten);
        }
        _gebiete.put(key, position2GG);
        _zugelassenePositionen.put(key, positionen);
      } catch (FinderException fe) {
        throw new EJBException(fe);
      }
    }
  }

  public Collection<GruppeGebietsspezifischModel> getGruppeGebietsspezifischCol4Gebiet(int gebietsart,
      int gebietsnummer) {

    init4Gebiet(gebietsart, gebietsnummer);
    return _gebiete.get(new GKey(gebietsart, gebietsnummer)).values();
  }

  public int getPosition(int gruppe, int gebietsnummer, int gebietsart) throws EJBException {
    init4Gebiet(gebietsart, gebietsnummer);
    GGKey key = new GGKey(gruppe, gebietsart, gebietsnummer);
    Integer position = _gruppePositionBySchluesselGebietsnummerUndArt.get(key);
    if (position == null) {
      throw new EJBException(Messages
          .bind(MessageKeys.Error_KeineGueltigeGruppeOderGebietsnummerArtGruppe_0_In_1,
              gruppe,
              gebietsnummer));
    }
    return position;
  }

  public Integer getSchluessel(int position, int gebietsnummer, int gebietsart) throws EJBException {
    init4Gebiet(gebietsart, gebietsnummer);
    GGKey key = new GGKey(position, gebietsart, gebietsnummer);
    Integer schluessel = _gruppeSchluesselByPositionGebietsnummerUndArt.get(key);
    if (schluessel == null) {
      throw new EJBException(Messages
          .bind(MessageKeys.Error_KeineGueltigePositionOderGebietsnummerArtPosition_0_In_1,
              position,
              gebietsnummer));
    }
    return schluessel;
  }

  public GruppeModel getModel4Gruppe(String id_Gruppe) {
    return _gruppe2Model.get(id_Gruppe);
  }

  public GruppeGebietsspezifischModel getModel4GruppeGebietsspezifisch(String id_GruppeGebietsspezifisch) {
    return _gruppeGebietsspezifisch2Model.get(id_GruppeGebietsspezifisch);
  }

  public Set<Integer> getZugelassenePositionen(int gebietsart, int gebietsnummer) {
    init4Gebiet(gebietsart, gebietsnummer);
    return _zugelassenePositionen.get(new GKey(gebietsart, gebietsnummer));
  }

  /**
   * Gibt zugelasseneKandidaten zur�ck.
   * 
   * @return zugelasseneKandidaten.
   */
  public Set<Integer> getZugelasseneKandidaten(int schluessel, int gebietsart, int gebietsnummer) {
    init4Gebiet(gebietsart, gebietsnummer);
    return _zugelasseneKandidaten.get(new GGKey(schluessel, gebietsart, gebietsnummer));
  }

  public String getWahlkurzname() {
    return _wahlInfo.getWahlNameKurz();
  }

  private static class GKey implements Serializable {
    private static final long serialVersionUID = -4686942418959653250L;
    private final int _gebietsart;
    private final int _gebietsnummer;

    /**
     * @param gebietsart
     * @param gebietsnummer
     */
    public GKey(int gebietsart, int gebietsnummer) {
      _gebietsart = gebietsart;
      _gebietsnummer = gebietsnummer;
    }

    @Override
    public boolean equals(Object obj) {
      GKey other = (GKey) obj;
      return _gebietsnummer == other._gebietsnummer && _gebietsart == other._gebietsart;
    }

    @Override
    public int hashCode() {
      return _gebietsnummer + (_gebietsart << 8);
    }
  }

  private static class GGKey extends GKey {
    private static final long serialVersionUID = -2505751589573498269L;
    private final int _gruppenkennung;

    /**
     * @param gruppenkennung
     * @param gebietsart
     * @param gebietsnummer
     */
    public GGKey(int gruppenkennung, int gebietsart, int gebietsnummer) {
      super(gebietsart, gebietsnummer);
      _gruppenkennung = gruppenkennung;
    }

    @Override
    public boolean equals(Object obj) {
      return _gruppenkennung == ((GGKey) obj)._gruppenkennung && super.equals(obj);
    }

    @Override
    public int hashCode() {
      return super.hashCode() + (_gruppenkennung << 16);
    }
  }
}