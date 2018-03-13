/*
 * SitzverteilungStatus
 * 
 * Created on 11.02.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.auswertung.sv;

import java.util.ArrayList;
import java.util.List;

import de.ivu.wahl.modell.AlternativeModel;
import de.ivu.wahl.modell.KonfliktModel;

/**
 * @author ugo@ivu.de, IVU Traffic Technologies AG

 */
public class SitzverteilungStatus {
  private String _errorMsg;
  private KonfliktModel _konflikt;
  private List<Alternative> _alternativen;

  public KonfliktModel getKonflikt() {
    return _konflikt;
  }

  public List<Alternative> getAlternativen() {
    return _alternativen;
  }

  /**
   * Sets the new value for conflict to conflict
   * 
   * @param konflikt
   */
  public void setKonflikt(KonfliktModel konflikt) {
    _konflikt = konflikt;
  }

  /**
   * Sets the new value for alternatives to alternatives
   * 
   * @param alternativen
   */
  public void setAlternativen(List<Alternative> alternativen) {
    _alternativen = alternativen;
  }

  /**
   * Adds alternativeText to alternative
   * 
   * @param alternative
   * @param alternativeText
   */
  public void addAlternaltive(AlternativeModel alternative, String alternativeText) {
    if (_alternativen == null) {
      _alternativen = new ArrayList<Alternative>();
    }
    _alternativen.add(new Alternative(alternative, alternativeText));
  }

  public static class Alternative {

    /**
     * Constructor
     * 
     * @param id_alternative
     * @param alternativText
     */
    public Alternative(AlternativeModel alternativeModel, String alternativText) {
      super();
      _alternativeModel = alternativeModel;
      _alternativeText = alternativText;
    }

    AlternativeModel _alternativeModel;
    String _alternativeText;

    /**
     * Get back alternativText
     * 
     * @return alternativText.
     */
    public String getAlternativeText() {
      return _alternativeText;
    }

    /**
     * Get back id_alternative
     * 
     * @return id_alternative.
     */
    public String getId_Alternative() {
      return _alternativeModel.getID_Alternative();
    }

    /**
     * Get back alternative
     * 
     * @return alternative.
     */
    public AlternativeModel getAlternativeModel() {
      return _alternativeModel;
    }

  }

  public String getErrorMsg() {
    return _errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    _errorMsg = errorMsg;
  }

}
