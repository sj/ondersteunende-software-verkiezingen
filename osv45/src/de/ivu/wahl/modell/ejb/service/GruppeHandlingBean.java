/*
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.Local;
import javax.ejb.Stateless;

import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.WahlStatelessSessionBeanBase;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GruppeKonstanten;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.GruppeHome;
import de.ivu.wahl.modell.ejb.Liste;

/**
 * SessionBean for the Gruppe related business services
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
@Stateless
@Local(GruppeHandling.class)
public class GruppeHandlingBean extends WahlStatelessSessionBeanBase implements GruppeHandling {
  private static final long serialVersionUID = -5234628662533525779L;

  private GruppeHome _gruppeHome = null;

  private GruppeHome getGruppeHome() {
    if (_gruppeHome == null) {
      _gruppeHome = GruppeHome.HomeFinder.findHome(this);
    }
    return _gruppeHome;
  }

  @Override
  public List<Gruppe> getGroupsSorted() {
    try {
      Collection<Gruppe> gruppen = getGruppeHome().findAllByWahl(WahlInfo.getWahlInfo()
          .getID_Wahl());
      List<Gruppe> result = new ArrayList<Gruppe>();
      for (Gruppe gruppe : gruppen) {
        Integer groupKey = Integer.valueOf(gruppe.getSchluessel());
        if (!GruppeKonstanten.KEYS_OF_SPECIAL_GROUPS.contains(groupKey)) {
          result.add(gruppe);
        }
      }

      Collections.sort(result, new Comparator<Gruppe>() {
        public int compare(Gruppe x, Gruppe y) {
          return x.getSchluessel() - y.getSchluessel();
        }
      });
      return result;
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "Gruppenmetadaten"), e); //$NON-NLS-1$
    }
  }

  @Override
  public Map<Integer, Gruppe> getSpecialGroups() {
    try {
      Map<Integer, Gruppe> result = new HashMap<Integer, Gruppe>();

      Collection<Gruppe> gruppen = getGruppeHome().findAllByWahl(WahlInfo.getWahlInfo()
          .getID_Wahl());
      for (Gruppe gruppe : gruppen) {
        Integer groupKey = Integer.valueOf(gruppe.getSchluessel());
        if (GruppeKonstanten.KEYS_OF_SPECIAL_GROUPS.contains(groupKey)) {
          result.put(groupKey, gruppe);
        }
      }

      return result;
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "Gruppenmetadaten"), e); //$NON-NLS-1$
    }
  }

  @Override
  public List<Liste> getAllP2ListsSortedByGruppenschluessel() {
    Collection<Liste> unsortedP2Lists = WahlInfo.getWahlInfo().getWahl().getListeCol();
    List<Liste> p2ListsSorted = new ArrayList<Liste>(unsortedP2Lists);
    Collections.sort(p2ListsSorted, new Comparator<Liste>() {
      @Override
      public int compare(Liste o1, Liste o2) {
        if (o1.getGruppe() != null && o2.getGruppe() != null) {
          return Integer.signum(o1.getGruppe().getSchluessel() - o2.getGruppe().getSchluessel());
        }
        return 0;
      }
    });
    return p2ListsSorted;
  }

}
