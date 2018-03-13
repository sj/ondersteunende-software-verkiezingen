/*
 * Created on 13.01.2004
 */
package de.ivu.wahl.auswertung.erg.sv.kandidat;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;

/**
 * @author tst
 */
public class KandidatInfoToolkit {
  final static Category LOGGER = Log4J.configure(KandidatInfoToolkit.class);

  public static final transient Collator NL_COLLATOR = Collator.getInstance(new Locale("nl", "NL", //$NON-NLS-1$ //$NON-NLS-2$
      "")); //$NON-NLS-1$

  /**
   * Funktion sortiert die Kandidaten nach dem Namen
   * 
   * @param kandidaten Liste der Kandidaten
   */
  public static void sortiereNameAlphabetisch(List<KandidatInfo> kandidaten) {
    Collections.sort(kandidaten, new Comparator<KandidatInfo>() {
      public int compare(KandidatInfo k1, KandidatInfo k2) {
        try {
          return compareNamen(k1, k2);
        } catch (Exception ex) {
          LOGGER.error(ex, ex);
          throw new RuntimeException("Cannot compare: " + ex.getMessage(), ex); //$NON-NLS-1$
        }
      }
    });
  }

  /**
   * Funktion sortiert die Listenkandidaten nach dem Namen
   * 
   * @param listenkandidaten Liste der Kandidaten
   */
  public static void sortiereListenkandidatenNachListenplatz(List<KandidatInfo> listenkandidaten) {
    Collections.sort(listenkandidaten, new Comparator<KandidatInfo>() {
      public int compare(KandidatInfo p1, KandidatInfo p2) {
        return p1.getListenplatz() - p2.getListenplatz();
      }
    });
  }

  /**
   * Funktion sortiert die Listenkandidaten wie folgt: zunächst bevorzugt gewählte nach Stimmenzahl,
   * danach die übrigen gewählten nach Listenplatz
   * 
   * @param listenkandidaten Liste der Kandidaten
   */
  public static void sortiereListenkandidatenNachBevorzugtGewaehltUndListenplatz(List<KandidatInfo> listenkandidaten) {
    Collections.sort(listenkandidaten, new Comparator<KandidatInfo>() {
      public int compare(KandidatInfo k1, KandidatInfo k2) {
        if (k1.isBevorzugtGewaehlt()) {
          if (k2.isBevorzugtGewaehlt()) {
            return -Integer.signum(k1.getStimmenAnzahl() - k2.getStimmenAnzahl());
          } else {
            return -1;
          }
        }
        if (k2.isBevorzugtGewaehlt()) {
          return 1;
        }
        return Integer.signum(k1.getListenplatz() - k2.getListenplatz());
      }
    });
  }

  /**
   * sortiert die Kandidaten nach der Gebietsposition ihres Gebietes
   * 
   * @param kandidaten die zu sortierenden Kandidaten
   */
  public static void sortiereKandidatenNachGebietsposition(List<KandidatInfo> kandidaten) {
    Collections.sort(kandidaten, new Comparator<KandidatInfo>() {
      public int compare(KandidatInfo k1, KandidatInfo k2) {
        return k1.getGebietsPosition() - k2.getGebietsPosition();
      }
    });
  }

  /**
   * Sortiert die Kandidaten nach der Gruppenreihenfolge des übergebenen Gebietes und innerhalb der
   * Gruppe nach Vorzugssitz und Anzahl Stimmen, oder ohne Vorzugssitz nach Listenplatz
   * 
   * @param kandidaten die Liste von KandidatInfos zum sortiern
   */
  public static void sortiereNameGruppeUndListenplatz(List<KandidatInfo> kandidaten) {
    Collections.sort(kandidaten, new Comparator<KandidatInfo>() {
      public int compare(KandidatInfo k1, KandidatInfo k2) {
        // 1st sort by P3-list (=Gruppe)
        int result = k1.getGruppenSchluessel() - k2.getGruppenSchluessel();
        if (result != 0) {
          return result;
        }
        // Then sort priority candidates on top
        result = Boolean.valueOf(k2.isBevorzugtGewaehlt()).compareTo(k1.isBevorzugtGewaehlt());
        if (result != 0) {
          return result;
        }
        if (k1.isBevorzugtGewaehlt()) {
          // Sort priority candidates by number of votes (reversed)
          return k2.getStimmenAnzahl() - k1.getStimmenAnzahl();
        } else {
          // Sort other candidates by position on list and eventually by name.
          return compareListenplatz(k1, k2);
        }
      }
    });
  }

  static int compareListenplatz(KandidatInfo k1, KandidatInfo k2) {
    final int listenplatzK1 = k1.getListenplatz();
    final int listenplatzK2 = k2.getListenplatz();
    if (listenplatzK1 == listenplatzK2) {
      return compareNamen(k1, k2);
    }
    return listenplatzK1 - listenplatzK2;
  }

  static int compareNamen(KandidatInfo k1, KandidatInfo k2) {
    String n1 = k1.getNachname() + ", " + k1.getVorname(); //$NON-NLS-1$
    String n2 = k2.getNachname() + ", " + k2.getVorname(); //$NON-NLS-1$

    return NL_COLLATOR.compare(n1, n2);
  }

  /**
   * sortiert die Listenkandidaten nach Listenplätzen. Die Listen werden nach
   * 
   * @param kandidaten die zu sortierenden Listenkandiaten
   */
  public static void sortiereListenKandidatenNachGebietUndListenplatz(List<KandidatInfo> kandidaten) {
    Collections.sort(kandidaten, new Comparator<KandidatInfo>() {
      public int compare(KandidatInfo k1, KandidatInfo k2) {
        if (k1.getGebietsart() != k2.getGebietsart()) {
          return k1.getGebietsart() - k2.getGebietsart();
        }
        if (k1.getGebietsnummer() != k2.getGebietsnummer()) {
          return k1.getGebietsnummer() - k2.getGebietsnummer();
        }
        return k1.getListenplatz() - k2.getListenplatz();
      }
    });
  }
}
