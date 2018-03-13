/*
 * ListsAndCandidatesConverter
 * 
 * Created on 03.02.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.result.model2builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import de.ivu.wahl.modell.GruppeComposite;
import de.ivu.wahl.modell.GruppeComposite.Liste;
import de.ivu.wahl.modell.GruppeComposite.Listenkandidat;
import de.ivu.wahl.modell.Wahldaten;
import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.CandidateList;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.builder.CandidatesBuilder;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;
import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;

/**
 * Helper to the ModelToBuilderConversion that performs the conversion of lists and candidates.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
class ListsAndCandidatesConverter {
  private static final Logger LOGGER = Logger.getLogger(ModelToBuilderConversion.class);

  private final CandidatesBuilder candidatesBuilder;
  private final Set<Candidate> deceasedCandidates = new HashSet<Candidate>();

  /**
   * Constructor
   */
  public ListsAndCandidatesConverter(CandidatesBuilder builder2) {
    this.candidatesBuilder = builder2;
  }

  /**
   * Add all information to the candidatesBuilder that is needed to create the lists and candidates.
   * 
   * @param electionSubcategory
   * @return the votesMap
   */
  Map<CandidateList, long[]> buildListsAndCandidates(Wahldaten wahldaten,
      ElectionSubcategory electionSubcategory,
      List<String> deadCandidates) {
    Map<CandidateList, long[]> votesMap = new HashMap<CandidateList, long[]>();

    // Iterate over all P3-lists
    for (GruppeComposite gruppe : wahldaten.getGruppen()) {
      String p3NameOrListWithNumber = getP3Name(gruppe); // used for logging
      LOGGER.info(Messages.applyPattern("Creating P3 list {0}.", p3NameOrListWithNumber)); //$NON-NLS-1$

      String p3Name = gruppe.getGruppe().getNameLang(); // may be empty
      if (p3Name == null) {
        p3Name = ""; //$NON-NLS-1$
      }
      P3ListKey externalP3ListKey = new P3ListKey(gruppe);
      candidatesBuilder.startP3List(externalP3ListKey, p3Name);

      // Iterate over all Candidate lists of the P3-list
      for (Liste liste : gruppe.getListen()) {
        P2ListKey externalP2ListKey = new P2ListKey(liste);
        candidatesBuilder.startCandidateList(liste.getGebiet(),
            liste.getPosition(),
            externalP2ListKey);
        LOGGER.trace(Messages.applyPattern("Creating candidate list in district {0}.", liste //$NON-NLS-1$
            .getGebiet().getName()));
        List<Listenkandidat> kandidaten = getSortedCandidates(liste);

        long[] votes = getVotesForCandidates(deadCandidates, p3NameOrListWithNumber, kandidaten);

        CandidateList candidateList = candidatesBuilder.finishCandidateList();
        votesMap.put(candidateList, votes);
      }

      candidatesBuilder.finishP3List();
    }

    setDeceasedCandidates();

    return votesMap;
  }

  private String getP3Name(GruppeComposite gruppe) {
    String p3Name = gruppe.getGruppe().getNameLang();
    // Dummy name for blank lists
    if (p3Name == null || p3Name.length() == 0) {
      p3Name = "lijst " + gruppe.getGruppe().getSchluessel(); //$NON-NLS-1$
    }
    return p3Name;
  }

  /**
   * @return List of Listenkandidat sorted by position on list
   */
  private List<Listenkandidat> getSortedCandidates(Liste liste) {
    List<Listenkandidat> kandidaten = new ArrayList<Listenkandidat>(liste.getKandidaten());
    Collections.sort(kandidaten, new Comparator<Listenkandidat>() {
      @Override
      public int compare(final Listenkandidat x, final Listenkandidat y) {
        return Integer.valueOf(x.getListenplatz()).compareTo(Integer.valueOf(y.getListenplatz()));
      }
    });
    return kandidaten;
  }

  /**
   * Create an array with the numbers of votes of the candidates. As a side effect, fill the
   * collection of <code>deceasedCandidates</code>.
   * 
   * @param deadCandidates Names of dead canddiates for testing purpose. Should be empty if not
   *          called from a test case.
   * @param p3NameOrListWithNumber name of the political grouping or "lijst &lt;list number>"
   * @param kandidaten
   * @return Array of votes of the candidates
   */
  private long[] getVotesForCandidates(List<String> deadCandidates,
      String p3Name,
      List<Listenkandidat> kandidaten) {
    long[] votes = new long[kandidaten.size()];

    // Iterate over all candidates
    int i = 0;
    for (Listenkandidat listenkandidat : kandidaten) {
      String candidateName = listenkandidat.getName();
      CandidateKey candidateId = new CandidateKey(listenkandidat.getPerson());
      Candidate candidate = candidatesBuilder.addCandidateToPendingList(candidateName, candidateId);
      LOGGER.trace(Messages.applyPattern("Creating candidate for list {0}: {1}. Votes cast: {2}", //$NON-NLS-1$
          p3Name,
          candidateName,
          listenkandidat.getStimmen()));
      if (listenkandidat.getListenplatz() != i + 1) {
        LOGGER.info(Messages.bind(MessageKeys.Convert_Tracelog_ListPositionInconsistent,
            candidateName,
            listenkandidat.getListenplatz(),
            i + 1));
      }
      votes[i] = listenkandidat.getStimmen();
      i++;

      // The information, if a candidate is dead, may come from two sources:
      // For testing it comes from the deadCandidates parameter
      // In production it comes from the wahldaten model
      boolean deceased = !listenkandidat.getPerson().isBenennbar()
          || deadCandidates.contains(candidateName);
      if (deceased) {
        deceasedCandidates.add(candidate);
      }
    }
    return votes;
  }

  /**
   * Inform the candidatesBuilder about the deceased candidates
   */
  private void setDeceasedCandidates() {
    if (!deceasedCandidates.isEmpty()) {
      LOGGER.info(Messages.applyPattern("Deceased candidate(s): {0}.", //$NON-NLS-1$
          Util.displayNamedObjects(deceasedCandidates)));
    }
    candidatesBuilder.setDeceasedCandidates(deceasedCandidates);
  }

}
