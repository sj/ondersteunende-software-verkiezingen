/*
 * Created on 16.11.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.WahlStatelessSessionBeanBase;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ejb.Ergebniseingang;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.Liste;
import de.ivu.wahl.modell.ejb.Listenkandidatur;
import de.ivu.wahl.modell.ejb.Stimmergebnis;
import de.ivu.wahl.modell.ejb.StimmergebnisHome;
import de.ivu.wahl.modell.ejb.service.VoteValues;

/**
 * Helper for access to the numbers of votes of candiates, lists and groups for program P5. All
 * votes must come from a single {@link Ergebniseingang}.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class RG520VotesHelper {
  private final boolean isEK = WahlInfo.getWahlInfo().isEK();
  private final int gebietsart = WahlInfo.getWahlInfo().getGebietsartMitListen();
  private final String id_Ergebniseingang;
  private final WahlStatelessSessionBeanBase bean;
  private VoteValues districtIdsAndVoteValues = null;

  /**
   * @param bean
   * @param id_Ergebniseingang
   */
  @SuppressWarnings("hiding")
  public RG520VotesHelper(WahlStatelessSessionBeanBase bean, String id_Ergebniseingang) {
    this.bean = bean;
    this.id_Ergebniseingang = id_Ergebniseingang;
  }

  private StimmergebnisHome getStimmergebnisHome() {
    return bean.getStimmergebnisHome();
  }

  /**
   * For elections other than EK, the resulting Map is empty.
   * 
   * @return an unmodifyable Map of numbers of provinces and the corresponding vote values
   */
  public VoteValues getVoteValues() {
    if (districtIdsAndVoteValues == null) {
      if (isEK) {
        districtIdsAndVoteValues = bean.getGebietHandling().getVoteValues();
      } else {
        districtIdsAndVoteValues = new VoteValues();
      }
    }
    return districtIdsAndVoteValues;
  }

  /**
   * @return the votes of a P3-list (Gruppe) in a by region number.
   */
  public VotesByRegionNumber getVotesPerDistrictForP3List(Gruppe gruppe) {
    try {
      Collection<Stimmergebnis> stimmergebnisse = getStimmergebnisHome()
          .findAllByErgebniseingangAndGruppe(id_Ergebniseingang, gruppe.getID_Gruppe());
      return new VotesByRegionNumber(stimmergebnisse, getVoteValues());
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "Listenergebnisse"), e); //$NON-NLS-1$
    }
  }

  public Map<Gruppe, VotesByRegionNumber> getVotesByRegionByGruppe() {
    Map<Gruppe, VotesByRegionNumber> result = new HashMap<Gruppe, VotesByRegionNumber>();
    Collection<Gruppe> gruppen = bean.getGruppeHandling().getGroupsSorted();
    for (Gruppe gruppe : gruppen) {
      result.put(gruppe, getVotesPerDistrictForP3List(gruppe));
    }
    return result;
  }

  /**
   * Note: Listenkandidatur is the relation between a candidate (Personendaten) and his P2-list
   * (Liste) that denotes that the candidate is nominated on the P2-list on the given list position
   * (int getListenplatz()).
   * 
   * @return the votes of a candidate on a P2-list in the regions where the P2-list is submitted
   */
  public VotesByRegionNumber getVotesForCandidateOnP2List(Listenkandidatur lk) {
    try {
      Collection<Stimmergebnis> votes = getStimmergebnisHome()
          .findAllByErgebniseingangAndListenkandidatur(id_Ergebniseingang,
              lk.getID_Listenkandidatur());
      List<Stimmergebnis> result = new ArrayList<Stimmergebnis>();
      for (Stimmergebnis se : votes) {
        if (se.getGebiet().getGebietsart() == gebietsart) {
          result.add(se);
        }
      }
      return new VotesByRegionNumber(result, getVoteValues());
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "Stimmergebnisse"), e); //$NON-NLS-1$
    }
  }

  /**
   * @return the votes of a P2-list in the regions where it is submitted
   */
  public VotesByRegionNumber getVotesForP2ListByRegion(Liste liste) {
    try {
      Collection<Stimmergebnis> stimmergebnisse = getStimmergebnisHome()
          .findAllByErgebniseingangAndListe(id_Ergebniseingang, liste.getID_Liste());
      return new VotesByRegionNumber(stimmergebnisse, getVoteValues());
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0, Messages
          .getString(MessageKeys.Msg_StimmergebnisseFuerListen)), e);
    }
  }

  public int getStimmenForErgebniseingangAndListe(Liste liste) {
    return getVotesForP2ListByRegion(liste).getTotalVotes();
  }

  /**
   * @return A map that for each P3-list (Gruppe) contains the number of votes it attained. And for
   *         each combined list it contains a 0.
   */
  public Map<String, Integer> getVotesForP3ListsAnd0ForCombinations() {
    try {
      Map<String, Integer> listVotes = new HashMap<String, Integer>();
      Collection<Stimmergebnis> ergCol = getStimmergebnisHome()
          .findAllByGebietAndErgebniseingang(WahlInfo.getWahlInfo().getID_Wurzelgebiet(),
              id_Ergebniseingang);
      for (Stimmergebnis erg : ergCol) {
        if (erg.getID_Listenkandidatur() != null) {
          continue;
        }
        Gruppe gruppe = erg.getGruppeGebietsspezifisch().getGruppe();
        String id_Listenkombination = gruppe.getID_Listenkombination();
        if (id_Listenkombination != null) {
          int stimmen = erg.getStimmen();
          listVotes.put(gruppe.getID_Gruppe(), stimmen);
          listVotes.put(id_Listenkombination, 0);
        }
      }
      return listVotes;
    } catch (FinderException e) {
      e.printStackTrace();
      throw new EJBException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "ListenkombinationZulassung"), e); //$NON-NLS-1$
    }
  }

}
