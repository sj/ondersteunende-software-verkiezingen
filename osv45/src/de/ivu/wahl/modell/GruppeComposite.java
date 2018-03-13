/*
 * Created on 07.01.2009
 * @author ugo
 */
package de.ivu.wahl.modell;

import java.util.ArrayList;
import java.util.List;

/**
 * This class corresponds to a group of lists.
 * 
 * @created 07.01.2009
 * @author ugo
 */
public class GruppeComposite {

  List<Liste> _listen; // The list that belong to the list group
  GruppeModel _gruppe; // The model of the list group

  public GruppeComposite(GruppeModel gruppeModel) {
    _gruppe = gruppeModel;
  }

  /**
   * @return the _listen
   */
  public List<Liste> getListen() {
    return this._listen;
  }

  /**
   * @param _listen the _listen to set
   */
  public void setListen(List<Liste> listen) {
    this._listen = listen;
  }

  public void addListe(Liste liste) {
    if (this._listen == null) {
      this._listen = new ArrayList<Liste>();
    }
    this._listen.add(liste);
  }

  /**
   * @return the _gruppe
   */
  public GruppeModel getGruppe() {
    return this._gruppe;
  }

  /**
   * @param _gruppe the _gruppe to set
   */
  public void setGruppe(GruppeModel gruppe) {
    this._gruppe = gruppe;
  }

  /**
   * @return the id_Listenkombination
   */
  public String getId_Listenkombination() {
    return this._gruppe.getID_Listenkombination();
  }

  /**
   * This class corresponds to a candidate list (which belongs to one electoral district)
   * 
   * @author jon@ivu.de, IVU Traffic Technologies AG
   */
  public class Liste {
    final String id_Liste;
    final int position;
    final String id_GruppeGebietsspezifisch;
    final GebietModel _gebiet;
    List<Listenkandidat> kandidaten = new ArrayList<Listenkandidat>();

    public Liste(GruppeGebietsspezifischModel ggModel, GebietModel gebietModel) {
      this.id_Liste = ggModel.getID_Liste();
      this.position = ggModel.getPosition();
      this.id_GruppeGebietsspezifisch = ggModel.getID_GruppeGebietsspezifisch();
      this._gebiet = gebietModel;
    }

    /**
     * This corresponds to the P2-list (set of identical list or independent list)
     * 
     * @return the id_Liste
     */
    public String getId_Liste() {
      return this.id_Liste;
    }

    /**
     * This corresponds to the candidate list
     * 
     * @return the id_GruppeGebietsspezifisch
     */
    public String getId_GruppeGebietsspezifisch() {
      return this.id_GruppeGebietsspezifisch;
    }

    /**
     * @return the _gebiet
     */
    public GebietModel getGebiet() {
      return this._gebiet;
    }

    /**
     * @return the kandidaten
     */
    public List<Listenkandidat> getKandidaten() {
      return this.kandidaten;
    }

    /**
     * @param kandidaten the kandidaten to set
     */
    @SuppressWarnings("hiding")
    public void setKandidaten(List<Listenkandidat> kandidaten) {
      this.kandidaten = kandidaten;
    }

    public void addKandidat(Listenkandidat listenkandidat) {
      if (this.kandidaten == null) {
        this.kandidaten = new ArrayList<Listenkandidat>();
      }
      this.kandidaten.add(listenkandidat);
    }

    /**
     * @return the position
     */
    public int getPosition() {
      return this.position;
    }

  }

  /**
   * Corresponds to a candidate of a candidate list (not P2-list)
   * 
   * @created 07.01.2009
   * @author ugo@ivu.de, IVU Traffic Technologies AG
   */
  public class Listenkandidat {
    final PersonendatenModel _person;
    final int _listenplatz;
    final String _id_Listenkandidatur;
    long stimmen;

    public Listenkandidat(PersonendatenModel person, int listenplatz, String id_Listenkandidatur) {
      this._person = person;
      this._listenplatz = listenplatz;
      this._id_Listenkandidatur = id_Listenkandidatur;
    }

    /**
     * @return the stimmen
     */
    public long getStimmen() {
      return this.stimmen;
    }

    /**
     * @return the _person
     */
    public PersonendatenModel getPerson() {
      return this._person;
    }

    /**
     * @param stimmen the stimmen to set
     */
    @SuppressWarnings("hiding")
    public void setStimmen(long stimmen) {
      this.stimmen = stimmen;
    }

    /**
     * @return the _listenplatz
     */
    public int getListenplatz() {
      return this._listenplatz;
    }

    /**
     * @return id_Listenkandidatur.
     */
    public String getId_Listenkandidatur() {
      return _id_Listenkandidatur;
    }

    public String getName() {
      return _person.getAnzeigeName();
    }

  }

}
