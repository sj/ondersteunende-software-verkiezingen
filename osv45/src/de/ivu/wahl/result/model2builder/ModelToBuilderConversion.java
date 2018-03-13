/*
 * Created on 07.01.2009
 * @author ugo
 */
package de.ivu.wahl.result.model2builder;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.Wahldaten;
import de.ivu.wahl.result.CandidateList;
import de.ivu.wahl.result.Election;
import de.ivu.wahl.result.ElectionAndCandidates;
import de.ivu.wahl.result.ElectionAndCandidatesAndVotes;
import de.ivu.wahl.result.builder.CandidatesBuilder;
import de.ivu.wahl.result.builder.ElectionBuilder;
import de.ivu.wahl.result.builder.VotesBuilder;
import de.ivu.wahl.result.i18n.Messages;
import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;

/**
 * Converts the information relevant to the Determination of the Election Result to the model
 * expected by the algorithm. This is done using some builders.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class ModelToBuilderConversion {
  private static final Logger LOGGER = Logger.getLogger(ModelToBuilderConversion.class);

  /**
   * Converts the information relevant to the Determination of the Election Result to the model
   * expected by the algorithm.
   * 
   * @return a model expected by the algorithm to the Determination of the Election Result
   */
  public static ElectionAndCandidatesAndVotes convert(Wahldaten wahldaten) {
    List<String> emptyList = Collections.emptyList();
    Map<Integer, Integer> emptyMap = Collections.emptyMap();
    return convert(wahldaten, emptyList, emptyMap);
  }

  /**
   * CAUTION: Use the method with non-empty <code>deadCandidates</code> for testing purpose only
   * when it is sure that the dead candidates can be uniquely identified by ther names.
   * <p>
   * Unless testing, DO NOT CALL THIS DIRECTLY. Use {@link #convert(Wahldaten)} instead.
   * 
   * @param wahldaten
   * @param deadCandidates Names of candidates that are recorded to be deceased
   * @param voteValuesFromTest The vote values of the electoral districts. Missing vote values are
   *          1. If <code>null</code>, all vote values are 1.
   * @return
   */
  public static ElectionAndCandidatesAndVotes convert(Wahldaten wahldaten,
      List<String> deadCandidates,
      Map<Integer, Integer> voteValuesFromTest) {
    LOGGER.info("Starting conversion"); //$NON-NLS-1$

    // Election constants
    Election election = buildElection(wahldaten, voteValuesFromTest);

    // Lists and candidates
    CandidatesBuilder builder2 = new CandidatesBuilder(election);
    Map<CandidateList, long[]> votesMap = new ListsAndCandidatesConverter(builder2)
        .buildListsAndCandidates(wahldaten, election.getElectionSubcategory(), deadCandidates);
    ElectionAndCandidates electionAndCandidates = builder2.getElectionAndCandidates();

    // Set the votes
    VotesBuilder builder3 = new VotesBuilder(electionAndCandidates);
    for (CandidateList candidateList : electionAndCandidates.getAllCandidateLists()) {
      long[] votes = votesMap.get(candidateList);
      builder3.setVotesCast(candidateList, votes);
    }

    ElectionAndCandidatesAndVotes ecv = builder3.getElectionAndCandidatesAndVotes();
    return ecv;
  }

  private static Election buildElection(Wahldaten wahldaten,
      Map<Integer, Integer> voteValuesFromTest) {
    int numberOfSeats = wahldaten.getWahl().getAnzahlSitze();
    int preferencialBarrierNumerator = wahldaten.getWahl().getVorrangschwelle();
    int preferencialBarrierDenominator = 100;
    ElectionSubcategory electionSubcategory = getElectionSubcategory(wahldaten);

    // Electoral districts
    ElectionBuilder builder = new ElectionBuilder(numberOfSeats, preferencialBarrierNumerator,
        preferencialBarrierDenominator, electionSubcategory);
    for (GebietModel gebiet : wahldaten.getGebiete()) {
      int gebietNummer = gebiet.getNummer();
      String name = gebiet.getBezeichnung();

      int voteValue = Math.max(1, gebiet.getVoteValue());
      Integer voteValueFromTest = voteValuesFromTest.get(gebietNummer);
      if (voteValueFromTest != null) {
        voteValue = voteValueFromTest.intValue();
      }
      builder.createElectoralDistrict(name, gebietNummer, gebiet, voteValue);
      LOGGER.info(Messages
          .applyPattern("Creating electoral district {0}. Vote value = {1}", name, voteValue)); //$NON-NLS-1$
    }
    Election election = builder.getElection();
    return election;
  }

  private static ElectionSubcategory getElectionSubcategory(Wahldaten wahldaten) {
    // The attribute wahlkategorie contains the election subcategory !!!
    String subcategory = wahldaten.getWahl().getWahlkategorie();
    return ElectionSubcategory.fromEmlRepresentation(subcategory);
  }
}
