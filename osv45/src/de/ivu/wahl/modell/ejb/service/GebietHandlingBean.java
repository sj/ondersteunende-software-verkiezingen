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
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.ejb.Gebiet;

/**
 * SessionBean for business services related to the entities {@link Gebiet}
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
@Stateless
@Local(GebietHandling.class)
public class GebietHandlingBean extends WahlStatelessSessionBeanBase implements GebietHandling {
  private static final long serialVersionUID = -5234628662533525779L;

  public Collection<Gebiet> getGebieteMitListen() {
    try {
      WahlInfo wahlInfo = WahlInfo.getWahlInfo();
      Collection<Gebiet> gebiete = getGebietHome()
          .findAllByWahlAndGebietsartSortByPosition(wahlInfo.getID_Wahl(),
              wahlInfo.getGebietsartMitListen());
      return gebiete;
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0, "Gebiete"), e); //$NON-NLS-1$
    }
  }

  public List<Gebiet> getProvinces() {
    try {
      List<Gebiet> gebiete = new ArrayList<Gebiet>(getGebietHome()
          .findAllByGebietsart(GebietModel.GEBIETSART_LAND));
      Collections.sort(gebiete, new Comparator<Gebiet>() {
        @Override
        public int compare(Gebiet x, Gebiet y) {
          return x.getNummer() - y.getNummer();
        }
      });
      return gebiete;
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0, "Provinces"), e); //$NON-NLS-1$
    }
  }

  public Collection<Gebiet> getRegionsForVoteValues() throws EJBException {
    try {
      return getGebietHome().findAllByGebietsart(GebietModel.GEBIETSART_LAND);
    } catch (EJBException e) {
      throw new EJBException(e);
    } catch (FinderException e) {
      throw new EJBException(
          Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0, "vote values"), e); //$NON-NLS-1$
    }
  }

  /**
   * For elections other than EK, the resulting Map is empty.
   * 
   * @return a Map of numbers of provinces and the corresponding vote values
   */
  public VoteValues getVoteValues() {
    if (!WahlInfo.getWahlInfo().isEK()) {
      // For elections other than EK, the resulting Map is empty.
      Map<Integer, Integer> emptyMap = Collections.emptyMap();
      return new VoteValues(emptyMap);
    }
    Map<Integer, Integer> result = new HashMap<Integer, Integer>();
    Collection<Gebiet> provinces = getRegionsForVoteValues();
    for (Gebiet province : provinces) {
      result.put(province.getNummer(), Math.max(1, province.getVoteValue()));
    }
    return new VoteValues(result);
  }

}
