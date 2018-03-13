/*
 * EingangsHistorieErgebnis
 * 
 * Created on 02.03.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.auswertung.erg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.ivu.wahl.GebietsBaum;
import de.ivu.wahl.SystemInfo;
import de.ivu.wahl.client.util.ClientHelper;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ErgebniseingangKonstanten;
import de.ivu.wahl.modell.ErgebniseingangModel;
import de.ivu.wahl.modell.GebietInfo;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.ejb.Gebietsstatus;

public class EingangsHistorieErgebnis extends Ergebnis {
  private static final long serialVersionUID = 4775001975217139723L;
  private final List<EingangsContainer> _ergEingaenge = new ArrayList<EingangsContainer>();

  private static final String STATUSMELDUNG_OK = "OK"; //$NON-NLS-1$

  public EingangsHistorieErgebnis(String ergbezeichnung) {
    super(ergbezeichnung);
  }

  public void addEingang(ErgebniseingangModel ergModel, GebietModel gebietModel) {
    _ergEingaenge.add(new EingangsContainer(ergModel, gebietModel));
  }

  public List<EingangsContainer> getErgebniseingaenge() {
    return _ergEingaenge;
  }

  public boolean hasResults() {
    return !_ergEingaenge.isEmpty();
  }

  public int getSize() {
    return _ergEingaenge.size();
  }

  /**
   * Sortiert ueber den Zeitstempel absteigend
   */
  public void sortByZeitstempel() {
    Collections.sort(_ergEingaenge);
  }

  public static class EingangsContainer implements Comparable<EingangsContainer> {
    private static final String DASH = "-"; //$NON-NLS-1$

    private enum Color {
      RED, GREEN, YELLOW, WHITE, LIGHTGREEN, YELLOWGREEN;
      @Override
      public String toString() {
        return name().toLowerCase();
      }
    }

    private final ErgebniseingangModel _ergModel;
    private final GebietModel _gebietModel;
    private final Gebietsstatus _gebietsstatus;

    public EingangsContainer(ErgebniseingangModel ergModel, GebietModel gebietModel) {
      this(ergModel, gebietModel, null);
    }

    /**
     * Constructor
     * 
     * @param ergModel
     * @param gebietModel
     * @param gebietsstatus
     */
    public EingangsContainer(ErgebniseingangModel ergModel,
        GebietModel gebietModel,
        Gebietsstatus gebietsstatus) {
      _ergModel = ergModel;
      _gebietModel = gebietModel;
      _gebietsstatus = gebietsstatus;
    }

    /**
     * @return Wertobjekt des Ergebniseingangs
     */
    public ErgebniseingangModel getErgModel() {
      return _ergModel;
    }

    /**
     * @return Gebiet
     */
    public GebietModel getGebietModel() {
      return _gebietModel;
    }

    /**
     * Sortiert �ber den Zeitstempel absteigend
     */
    public int compareTo(EingangsContainer e) {
      return e._ergModel.getZeitstempel().compareTo(_ergModel.getZeitstempel());
    }

    public String getUserID() {
      if (_ergModel == null || _ergModel.getAnwenderName() == null) {
        return DASH;
      }
      return _ergModel.getAnwenderName();
    }

    public String getStatusColor() {
      if (_ergModel == null) {
        return "";// Color.WHITE.toString(); //$NON-NLS-1$
      }
      switch (_ergModel.getStatus()) {
        case ErgebniseingangKonstanten.STATE_OK :
          if (_gebietsstatus != null && _gebietsstatus.getKorrekturnummer() > 0) {
            return Color.YELLOWGREEN.toString();
          }
          return Color.GREEN.toString();
        case ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK :
          return Color.LIGHTGREEN.toString();
        case ErgebniseingangKonstanten.STATE_ERROR :
          return Color.RED.toString();
        case ErgebniseingangKonstanten.STATE_WARNING :
          return Color.YELLOW.toString();
        default :
          return Color.WHITE.toString();

      }
    }

    public String getStatusIcon() {
      if (_ergModel == null) {
        return "blind.gif";// Color.WHITE.toString(); //$NON-NLS-1$
      }
      switch (_ergModel.getStatus()) {
        case ErgebniseingangKonstanten.STATE_OK :
          if (_gebietsstatus != null && _gebietsstatus.getKorrekturnummer() > 0) {
            return "accept_twice.png"; //$NON-NLS-1$
          }
          return "accept.png"; //$NON-NLS-1$
        case ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK :
          return "layers.png"; //$NON-NLS-1$
        case ErgebniseingangKonstanten.STATE_ERROR :
          return "exclamation.png"; //$NON-NLS-1$
        case ErgebniseingangKonstanten.STATE_WARNING :
          return "warning.png"; //$NON-NLS-1$
        default :
          return "blind.gif"; //$NON-NLS-1$

      }
    }

    public String getStatusMeldung() {
      if (_ergModel == null) {
        return DASH;
      }
      String result;
      switch (_ergModel.getStatus()) {
        case ErgebniseingangKonstanten.STATE_OK :
          result = STATUSMELDUNG_OK;
          if (_gebietsstatus != null && _gebietsstatus.getKorrekturnummer() > 0) {
            result += " (" + Messages.getString(MessageKeys.MultipleInputsOnRegion) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
          }
          return result;
        case ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK :
          result = STATUSMELDUNG_OK;
          GebietInfo gebietInfo = GebietsBaum.getGebietsBaum(getErgModel().getID_Wahl())
              .getGebietInfo(getID_Gebiet());
          if (gebietInfo != null && gebietInfo.isVollstaendig()) {
            result += " (" + Messages.getString(MessageKeys.MultipleInputsOnRegion) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
          }
          return result;
        case ErgebniseingangKonstanten.STATE_ERROR :
          return _ergModel.getFehlermeldung() != null ? _ergModel.getFehlermeldung() : "ERROR"; //$NON-NLS-1$
        case ErgebniseingangKonstanten.STATE_WARNING :
          return _ergModel.getFehlermeldung() != null ? _ergModel.getFehlermeldung() : "WARNING"; //$NON-NLS-1$
        default :
          return DASH;

      }
    }

    public String getStatusMeldungWithoutMultipleInputs() {
      if (_ergModel == null) {
        return DASH;
      }
      switch (_ergModel.getStatus()) {
        case ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK :
          return STATUSMELDUNG_OK;
        default :
          return getStatusMeldung();
      }
    }

    public String getInputTypeImg() {
      if (_ergModel == null) {
        return "blind.gif"; //$NON-NLS-1$
      }
      switch (_ergModel.getHerkunft()) {
        case ErgebniseingangKonstanten.SOURCE_FILE_IMPORT :
        case ErgebniseingangKonstanten.SOURCE_FILE_IMPORT_AS_FIRST_INPUT :
          return "emlfile.gif"; //$NON-NLS-1$
        case ErgebniseingangKonstanten.SOURCE_GUI_1 :
        case ErgebniseingangKonstanten.SOURCE_GUI_2 :
          return "hand.gif"; //$NON-NLS-1$
        default :
          return "blind.gif"; //$NON-NLS-1$
      }
    }

    public String getInputSequenceType() {
      if (_ergModel == null) {
        return ""; //$NON-NLS-1$
      }
      switch (_ergModel.getHerkunft()) {
        case ErgebniseingangKonstanten.SOURCE_GUI_1 :
        case ErgebniseingangKonstanten.SOURCE_FILE_IMPORT_AS_FIRST_INPUT :
          return "(1)"; //$NON-NLS-1$
        case ErgebniseingangKonstanten.SOURCE_GUI_2 :
          return "(2)"; //$NON-NLS-1$
        default :
          return ""; //$NON-NLS-1$
      }
    }

    public String getGebietBez() {
      return _gebietModel == null
          ? DASH
          : "(" + _gebietModel.getNumber4Display() + ") " + _gebietModel.getName(); //$NON-NLS-1$//$NON-NLS-2$
    }

    public String getID_Gebiet() {
      return _gebietModel == null ? DASH : _gebietModel.getID_Gebiet();
    }

    public String getZeitstempel() {
      if (_ergModel == null || _ergModel.getZeitstempel() == null) {
        return DASH;
      }
      return ClientHelper.formatDateTime(_ergModel.getZeitstempel());
    }

    public String getWahlergebnisart() {
      if (SystemInfo.getSystemInfo().getWahlEbene() == GebietModel.EBENE_PSB) {
        switch (_ergModel.getWahlergebnisart()) {
          case 1 :
            return "K "; //$NON-NLS-1$
          case 2 :
            return "P "; //$NON-NLS-1$
        }
      }
      return ""; //$NON-NLS-1$
    }

    public int getStatus() {
      if (_ergModel == null) {
        return -1;
      }
      return _ergModel.getStatus();
    }
  }

}
