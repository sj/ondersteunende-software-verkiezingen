/*
 * GruppePositionProvider
 * 
 * Created on 06.01.2016
 * Copyright (c) 2016 IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell;

import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.ADMITTED_VOTERS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.BALLOT_PAPERS_LOST;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.BALLOT_PAPER_NOT_RETURNED;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.ELECTION_NOTICES;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.EMPTY_POSTAL_VOTES;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.GUELTIGE;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.GUELTIG_ODER_LEER;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.LEER;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.LESS_VALID_VOTES_THAN_ADMITTED_VOTERS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.MORE_VALID_VOTES_THAN_ADMITTED_VOTERS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.NO_EXPLANATION;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.OTHER_EXPLANATIONS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.POLLING_CARDS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.POSTAL_VOTES_WITH_MULTIPLE_BALLOT_PAPERS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.PROXY_VOTERS;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.TOO_FEW_BALLOT_PAPER_ISSUED;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.TOO_MANY_BALLOT_PAPER_ISSUED;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.UNGUELTIGE;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.WAEHLER;
import static de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein.WAHLBERECHTIGTE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein;

public class GruppePositionProvider {

  // Position of the last special group
  private static final int MAX_POSITION = -6;
  // Number of special groups
  private static final int NO_OF_SPECIAL_GROUPS = GruppeAllgemein.values().length;
  // Position of the first special group
  private static final int MIN_POSITION = MAX_POSITION - NO_OF_SPECIAL_GROUPS + 1;

  private static final GruppeAllgemein[] A1 = {WAHLBERECHTIGTE};

  private static final GruppeAllgemein[] A2 = {ELECTION_NOTICES, PROXY_VOTERS, POLLING_CARDS,
      ADMITTED_VOTERS};

  // Sub-order for PSB
  private static final GruppeAllgemein[] A3_PSB = {GUELTIGE, LEER, GUELTIG_ODER_LEER, UNGUELTIGE,
      WAEHLER};

  // Sub-order for HSB and CSB in EK elections
  private static final GruppeAllgemein[] A3_EK = {WAEHLER, LEER, GUELTIG_ODER_LEER, UNGUELTIGE,
      GUELTIGE};

  // Sub-order for HSB and CSB in non-EK elections
  private static final GruppeAllgemein[] A3_HSB_CSB = {WAEHLER, GUELTIGE, LEER, GUELTIG_ODER_LEER,
      UNGUELTIGE};

  // Explaining the difference between admitted voters and valid votes
  private static final GruppeAllgemein[] A4 = {MORE_VALID_VOTES_THAN_ADMITTED_VOTERS,
      LESS_VALID_VOTES_THAN_ADMITTED_VOTERS, BALLOT_PAPER_NOT_RETURNED,
      TOO_FEW_BALLOT_PAPER_ISSUED, TOO_MANY_BALLOT_PAPER_ISSUED, EMPTY_POSTAL_VOTES,
      POSTAL_VOTES_WITH_MULTIPLE_BALLOT_PAPERS, BALLOT_PAPERS_LOST, NO_EXPLANATION,
      OTHER_EXPLANATIONS};

  /** Order of special groups in PSB for all elections */
  private static final List<GruppeAllgemein> ORDER_FOR_PSB = concat(A1, A2, A3_PSB, A4);

  /** Order of special groups in HSB and CSB for all EK elections */
  private static final List<GruppeAllgemein> ORDER_FOR_HSB_AND_CSB_IN_EK = concat(A1, A2, A3_EK, A4);

  /** Order of special groups in HSB and CSB for all elections EXCEPT EK */
  private static final List<GruppeAllgemein> ORDER_FOR_HSB_AND_CSB = concat(A1, A3_HSB_CSB, A4, A2);

  private static List<GruppeAllgemein> concat(GruppeAllgemein[]... arrays) {
    List<GruppeAllgemein> result = new ArrayList<GruppeAllgemein>();
    for (GruppeAllgemein[] array : arrays) {
      for (GruppeAllgemein gruppeAllgemein : array) {
        result.add(gruppeAllgemein);
      }
    }

    assert result.size() == NO_OF_SPECIAL_GROUPS;

    return Collections.unmodifiableList(result);
  }

  public static int getPositionPSB(GruppeAllgemein specialGroup) {
    return ORDER_FOR_PSB.indexOf(specialGroup) + MIN_POSITION;
  }

  public static int getPositionHSBCSBEK(GruppeAllgemein specialGroup) {
    return ORDER_FOR_HSB_AND_CSB_IN_EK.indexOf(specialGroup) + MIN_POSITION;
  }

  public static int getPositionHSBCSB(GruppeAllgemein specialGroup) {
    return ORDER_FOR_HSB_AND_CSB.indexOf(specialGroup) + MIN_POSITION;
  }
}
