/*
 * EML520Helper
 * 
 * Created on 05.02.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import static de.ivu.wahl.dataimport.XMLTags.NS_EML;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import org.apache.log4j.Logger;

import nu.xom.Element;

import de.ivu.wahl.dataimport.XMLTags;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.Liste;
import de.ivu.wahl.modell.ejb.ListenkandidaturErgebnis;
import de.ivu.wahl.wus.reportgen.EMLMessageType;

/**
 * Helper of ExportHandlingBean to create the core part of the EML 520 message.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
class EML520Helper {
  private static final Logger LOGGER = Logger.getLogger(EML520Helper.class);

  private final ExportHandlingBean bean;

  @SuppressWarnings("hiding")
  EML520Helper(ExportHandlingBean bean) {
    this.bean = bean;
  }

  Element createContestResultElement(String id_Ergebniseingang,
      Gebiet region,
      EMLMessageType emlType) {
    Element contest = XMLHelper.createElement(XMLTags.EML_CONTEST, NS_EML);
    contest.appendChild(bean.createContestIdentifier(region, false));

    List<Liste> p2ListsSorted = bean.getGruppeHandling().getAllP2ListsSortedByGruppenschluessel();
    Map<Gruppe, List<ListenkandidaturErgebnis>> map = getCandidateResultsByGroup(id_Ergebniseingang,
        p2ListsSorted);
    for (Entry<Gruppe, List<ListenkandidaturErgebnis>> entry : map.entrySet()) {
      Gruppe gruppe = entry.getKey();
      List<ListenkandidaturErgebnis> candidateResults = entry.getValue();
      Collections.sort(candidateResults, getListenkandidaturErgebnisComparator());
      addFinalListResultElements(contest, gruppe, candidateResults, emlType);
    }

    return contest;
  }

  /**
   * Adding list results with elected candidates
   * 
   * @param contest
   * @param gruppe
   * @param candidateResults
   * @param emlType
   */
  private void addFinalListResultElements(Element contest,
      Gruppe gruppe,
      List<ListenkandidaturErgebnis> candidateResults,
      EMLMessageType emlType) {
    if (candidateResults.size() == 0) {
      return;
    }
    // Only lists withs seats added here
    // insert list result as first node
    contest.appendChild(createListResultElement(gruppe, false, 0));
    for (ListenkandidaturErgebnis lke : candidateResults) {
      Element candidateResult = XMLHelper.createElement(XMLTags.EML_SELECTION, NS_EML);
      candidateResult.appendChild(bean.createCandidateElement(lke.getListenkandidatur(),
          false,
          false,
          false,
          lke.getListenplatz(),
          emlType));
      String ranking = lke.isBevorzugtGewaehlt() ? "1" : "2"; //$NON-NLS-1$ //$NON-NLS-2$
      candidateResult.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_PREF_ELECTED,
          NS_EML,
          ranking));
      if (lke.isGewaehlt()) {
        candidateResult.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_ELECTED,
            NS_EML,
            "yes")); //$NON-NLS-1$
      }
      contest.appendChild(candidateResult);
    }
  }

  private Element createListResultElement(Gruppe gruppe, boolean withVotes, int votes) {
    Element listenergebnis = XMLHelper.createElement(XMLTags.EML_SELECTION, NS_EML);
    listenergebnis.appendChild(bean.createListIdentifierElement(gruppe));
    if (withVotes) {
      listenergebnis.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_VALID_VOTES,
          NS_EML,
          votes));
    } else {
      listenergebnis.appendChild(XMLHelper.createElementWithValue(XMLTags.EML_ELECTED,
          NS_EML,
          "yes")); //$NON-NLS-1$
    }
    return listenergebnis;
  }

  /**
   * Returns a LinkedHashMap with the P3-lists (Gruppe) as Keys and Lists of candidate results as
   * values. The map. Iterating gives the P3-lists in the order of ascending <code>schluessel</code>
   * attribute.
   */
  protected LinkedHashMap<Gruppe, List<ListenkandidaturErgebnis>> getCandidateResultsByGroup(String id_Ergebniseingang,
      List<Liste> allP2ListsSortedByGruppenschluessel) {
    LinkedHashMap<Gruppe, List<ListenkandidaturErgebnis>> map = new LinkedHashMap<Gruppe, List<ListenkandidaturErgebnis>>();

    for (Liste liste : allP2ListsSortedByGruppenschluessel) {
      Gruppe gruppe = liste.getGruppe();
      if (gruppe != null) {
        List<ListenkandidaturErgebnis> list = map.get(gruppe);
        if (list == null) {
          list = new ArrayList<ListenkandidaturErgebnis>();
          map.put(gruppe, list);
        }
        try {
          // get candidate results
          Collection<ListenkandidaturErgebnis> unsortedCandidateResults = bean
              .getListenkandidaturErgebnisHome()
              .findAllByErgebniseingangAndListeAndGewaehlt(id_Ergebniseingang, liste.getID_Liste());
          list.addAll(unsortedCandidateResults);
        } catch (FinderException e) {
          LOGGER.error(Messages.getString(MessageKeys.Error_ErrorRetrievingCandidateResults), e);
          throw new EJBException(Messages
              .getString(MessageKeys.Error_ErrorRetrievingCandidateResults), e);
        }
      }
    }
    return map;
  }

  protected Comparator<ListenkandidaturErgebnis> getListenkandidaturErgebnisComparator() {
    return new Comparator<ListenkandidaturErgebnis>() {
      @Override
      public int compare(ListenkandidaturErgebnis o1, ListenkandidaturErgebnis o2) {
        return Integer.signum(o1.getListenplatz() - o2.getListenplatz());
      }
    };
  }

}
