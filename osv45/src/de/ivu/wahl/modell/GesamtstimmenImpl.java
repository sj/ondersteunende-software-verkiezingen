/*
 * Gesamtstimmen
 * 
 * Created on 15.09.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ivu.util.collections.TwoKeyMap;
import de.ivu.wahl.auswertung.erg.ResultSummary;
import de.ivu.wahl.modell.ejb.service.VoteValues;

/**
 * This class holds all data for the eml510d &lt;TotalVotes&gt;-node for TK and PS2 elections. It is
 * used for consistency checks during import and for generation the &lt;TotalVotes&gt; for eml
 * export
 * <p>
 * {@link ResultSummary} is doing very similar things to {@link GesamtstimmenImpl}. Maybe one of
 * them can be replaced by the other. To my (JON, 24-11-2009) knowledge, {@link GesamtstimmenImpl}
 * is better in performance.
 * 
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public class GesamtstimmenImpl implements Gesamtstimmen {
  private final TwoKeyMap<Integer, String, Kandidatenergebnis> _kandidatenergebnisse = new TwoKeyMap<Integer, String, Kandidatenergebnis>();
  private final Map<Integer, Integer> _gruppenergebnisse = new HashMap<Integer, Integer>();
  private final VoteValues _voteValues;
  private int _gesamtstimmen; // is not used

  public GesamtstimmenImpl() {
    this(null);
  }

  public GesamtstimmenImpl(VoteValues voteValues) {
    super();
    this._voteValues = voteValues;
  }

  public List<Kandidatenergebnis> getKandidatenergebnisse(int gruppenschluessel) {
    List<Kandidatenergebnis> candidateList = new ArrayList<Kandidatenergebnis>(
        _kandidatenergebnisse.getMap(gruppenschluessel).values());
    Collections.sort(candidateList, new Comparator<Kandidatenergebnis>() {
      public int compare(Kandidatenergebnis erg1, Kandidatenergebnis erg2) {
        return Integer.signum(erg1.listenplatz - erg2.listenplatz);
      }
    });
    return candidateList;
  }

  public void addKandidatenstimmen(int gruppenschluessel,
      int listenplatz,
      String shortCode,
      int stimmen,
      Integer regionNumber) {
    int voteValue = getVoteValue(regionNumber);
    String key = getKandidatenschluessel(listenplatz, shortCode);
    Kandidatenergebnis erg = _kandidatenergebnisse.get(gruppenschluessel, key);
    if (erg == null) {
      erg = new Kandidatenergebnis(listenplatz, shortCode);
      _kandidatenergebnisse.put(gruppenschluessel, key, erg);
    }
    erg.addStimmen(stimmen * voteValue);
    addGruppenstimmen(gruppenschluessel, stimmen * voteValue);
  }

  private int getVoteValue(Integer regionNumber) {
    if (regionNumber == null || _voteValues == null) {
      return 1;
    }
    Integer result = _voteValues.get(regionNumber);
    if (result == null) {
      return 1;
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.modell.Gesamtstimmen#getStimmen(int, int, java.lang.String)
   */
  public int getStimmen(int gruppenschluessel, int listenplatz, String shortCode) {
    String kandidatenschluessel = getKandidatenschluessel(listenplatz, shortCode);
    Kandidatenergebnis kandidatenergebnis = _kandidatenergebnisse.get(Integer
        .valueOf(gruppenschluessel), kandidatenschluessel);
    if (kandidatenergebnis != null) {
      return kandidatenergebnis.getStimmen();
    }
    return 0;
  }

  @Deprecated
  // is not used
  public void setGruppenstimmen(int gruppenschluessel, int stimmen) {
    _gruppenergebnisse.put(gruppenschluessel, stimmen);
  }

  public void addGruppenstimmen(int gruppenschluessel, int stimmen) {
    Integer oldValue = _gruppenergebnisse.get(gruppenschluessel);
    if (oldValue == null) {
      _gruppenergebnisse.put(gruppenschluessel, stimmen);
    } else {
      _gruppenergebnisse.put(gruppenschluessel, oldValue + stimmen);
    }
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.modell.Gesamtstimmen#getGruppenstimmen(int)
   */
  public int getGruppenstimmen(int gruppenschluessel) {
    Integer result = _gruppenergebnisse.get(new Integer(gruppenschluessel));
    if (result == null) {
      return 0;
    }
    return result;
  }

  @Deprecated
  // is not used
  public int getGesamtstimmen() {
    return _gesamtstimmen;
  }

  @Deprecated
  // is not used
  public void setGesamtstimmen(int gesamtstimmen) {
    this._gesamtstimmen = gesamtstimmen;
  }

  public static class Kandidatenergebnis {
    final int listenplatz;
    String shortCode;
    int stimmenGesamt = 0;

    /**
     * Constructor
     */
    @SuppressWarnings("hiding")
    public Kandidatenergebnis(int listenplatz, final String shortCode) {
      this.listenplatz = listenplatz;
      this.shortCode = shortCode;
    }

    public int getStimmen() {
      return stimmenGesamt;
    }

    public void addStimmen(int stimmen) {
      this.stimmenGesamt += stimmen;
    }

    public String getShortCode() {
      return shortCode;
    }

    @SuppressWarnings("hiding")
    public void setShortCode(String shortCode) {
      this.shortCode = shortCode;
    }
  }

  private String getKandidatenschluessel(int listenplatz, String shortCode) {
    return shortCode == null ? String.valueOf(listenplatz) : shortCode;
  }

}
