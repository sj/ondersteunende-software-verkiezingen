/*
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.eingang;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.ivu.wahl.eingang.GUIEingangMsg.Gruppendaten;
import de.ivu.wahl.eingang.GUIEingangMsg.Kandidat;
import de.ivu.wahl.export.WeightedVotesByGroup;
import de.ivu.wahl.modell.BasicEingangMsg.Gruppenergebnis;
import de.ivu.wahl.modell.GruppeKonstanten;

/**
 * Wrapps GUIEingangMsg with some additional information for the EK (first chamber) election.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */

public class GUIEingangMsgEK implements Serializable {
  private static final long serialVersionUID = -2500832971436001123L;

  private final GUIEingangMsg _msg;
  private final Integer _voteValue;
  private final Map<Integer, GruppendatenEK> _gruppendaten;
  private final WeightedVotesByGroup _weightedVotesByGroup;

  public GUIEingangMsgEK(GUIEingangMsg msg, Integer voteValue) {
    this(msg, voteValue, null);
  }

  public GUIEingangMsgEK(GUIEingangMsg msg, WeightedVotesByGroup weightedVotesByGroup) {
    this(msg, null, weightedVotesByGroup);
  }

  private GUIEingangMsgEK(GUIEingangMsg msg,
      Integer voteValue,
      WeightedVotesByGroup weightedVotesByGroup) {
    _msg = msg;
    _voteValue = voteValue;
    _weightedVotesByGroup = weightedVotesByGroup;
    Map<Integer, GruppendatenEK> map = new LinkedHashMap<Integer, GruppendatenEK>();
    for (Entry<Integer, GUIEingangMsg.Gruppendaten> entry : msg.getGruppendaten().entrySet()) {
      map.put(entry.getKey(), new GruppendatenEK(entry.getValue()));
    }
    _gruppendaten = Collections.unmodifiableMap(map);
  }

  public int getGruppenstimmen(int gruppenposition) {
    return _msg.getGruppenstimmen(gruppenposition);
  }

  public int getGruppenstimmenGewichtet(int gruppenposition) {
    int votes = _msg.getGruppenstimmen(gruppenposition);
    if (_voteValue != null) {
      return votes * _voteValue;
    } else {
      if (GruppeKonstanten.GruppeAllgemein.GUELTIGE.getPosition() == gruppenposition) {
        return _weightedVotesByGroup.getTotalWeightedVotes();
      } else {
        return _weightedVotesByGroup.getWeightedVotesByGroupPosition(gruppenposition);
      }
    }
  }

  public double getStimmenprozentGewichtet(int gruppenposition) {
    if (_voteValue == null) {
      return (double) 100 * (double) getGruppenstimmenGewichtet(gruppenposition)
          / Math.max(1, _weightedVotesByGroup.getTotalWeightedVotes());
    } else {
      return _gruppendaten.get(gruppenposition).getStimmenprozent();
    }
  }

  public int getStimmen(int gruppenposition, int listenposition) {
    return _msg.getStimmen(gruppenposition, listenposition);
  }

  public int getStimmenGewichtet(int gruppenposition, int listenposition) {
    int votes = _msg.getStimmen(gruppenposition, listenposition);
    if (_voteValue != null) {
      return votes * _voteValue;
    } else {
      throw new IllegalStateException(
          "GUIEingangMsgEK does not provide candidate votes for the root region"); //$NON-NLS-1$
    }
  }

  public Map<Integer, GruppendatenEK> getGruppendaten() {
    return _gruppendaten;
  }

  public Gruppenergebnis getGruppenergebnis(int gruppenposition) {
    return _msg.getGruppenergebnis(gruppenposition);
  }

  public String getConfirmationText() {
    return _msg.getConfirmationText();
  }

  public String getInfoText() {
    return _msg.getInfoText();
  }

  public void setConfirmationText(String confirmationText) {
    _msg.setConfirmationText(confirmationText);
  }

  public void setInfoText(String infoText) {
    _msg.setInfoText(infoText);
  }

  public class GruppendatenEK {
    private final Gruppendaten _delegate;

    public boolean hasWeightedValue() {
      return _delegate.getPosition() >= 0
          || GruppeKonstanten.GruppeAllgemein.GUELTIGE.getPosition() == _delegate.getPosition();
    }

    public GruppendatenEK(Gruppendaten gruppendaten) {
      this._delegate = gruppendaten;
    }

    public String getFarbe() {
      return _delegate.getFarbe();
    }

    public String getName() {
      return _delegate.getName();
    }

    public int getPosition() {
      return _delegate.getPosition();
    }

    public double getStimmenprozent() {
      return _delegate.getStimmenprozent();
    }

    public Map<Integer, Kandidat> getKandidaten() {
      return _delegate.getKandidaten();
    }

    public String getHelptext() {
      return _delegate.getHelptext();
    }

    public String getKategorie() {
      return _delegate.getKategorie();
    }

    public boolean isSmallFontSize() {
      return _delegate.isSmallFontSize();
    }

    public boolean isVisible() {
      return _delegate.isVisible();
    }

    public boolean isVisibleInOverview() {
      return _delegate.isVisibleInOverview();
    }
  }
}