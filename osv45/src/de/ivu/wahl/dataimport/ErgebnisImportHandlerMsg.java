/*
 * ErgebnisImportHandlerMsg
 * 
 * Created on 25.03.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import java.util.Collection;

import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;

import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ErgebniseingangKonstanten;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.exception.ImportException;

/**
 * Handels import of sub region results in P4
 * 
 * @author ugo@ivu.de, IVU Traffic Technologies AG

 */
public class ErgebnisImportHandlerMsg extends AbstractErgebnisImportHandler {

  EingangMsgXML _msg;

  public ErgebnisImportHandlerMsg(WahlInfo wahlInfo, EingangMsgXML msg) {
    _wahlInfo = wahlInfo;
    _msg = msg;
  }

  public GebietModel getImportRegion() throws ImportException {
    try {
      return _gebietHome.findByWahlAndGebietsartAndNummer(_wahlInfo.getID_Wahl(), _msg
          .getGebietsartErfassungseinheit(), _msg.getNummerErfassungseinheit());
    } catch (Exception e) {
      throw new ImportException(Messages
          .getString(MessageKeys.Error_FehlerBeimHolenDesWurzelgebietes), e);
    }
  }

  public boolean checkConsistency() {
    return false;
  }

  @Override
  public void addStimmen(GebietModel gebiet,
      int gruppenschluessel,
      String id_Listenkandidatur,
      int stimmen) throws ImportException {
    try {
      if (id_Listenkandidatur == null) {
        _msg.setGruppenstimmen(gruppenschluessel, stimmen);
      } else {
        int listenposition = _lkHome.findByPrimaryKey(id_Listenkandidatur).getListenplatz();
        _msg.setStimmen(gruppenschluessel, listenposition, stimmen);
      }
    } catch (ObjectNotFoundException e) {
      throw new ImportException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "Listenkandidatur"), e); //$NON-NLS-1$
    } catch (FinderException e) {
      throw new ImportException(Messages.bind(MessageKeys.Error_FehlerBeimLesenDer_0,
          "Listenkandidatur"), e); //$NON-NLS-1$
    }
  }

  @Override
  public void finishErgebnisimport(Collection<GebietModel> gebieteEingegangen)
      throws ImportException {
    _msg.setStatus(ErgebniseingangKonstanten.STATE_OK);
  }

  public EingangMsgXML getEingangMsg() {
    return _msg;
  }

  @Override
  protected void saveStimmergebnisAllgemein(GebietModel gebiet,
      int position,
      String id_Ergebniseingang,
      int stimmen) throws ImportException {
    _msg.setGruppenstimmen(position, stimmen);

  }

}
