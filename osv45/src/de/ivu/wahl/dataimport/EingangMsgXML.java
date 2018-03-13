/*
 * EingangMsgXML
 * 
 * Copyright (c) 2002-4 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;

import nu.xom.Element;

import de.ivu.wahl.AnwContext;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.SystemInfo;
import de.ivu.wahl.eingang.EingangMsg;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.BasicEingangMsg;
import de.ivu.wahl.modell.ErgebniseingangKonstanten;
import de.ivu.wahl.modell.Gebietsart;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifisch;

/**
 * EingangMsgXML Reading an eml 510 result file for one sub region
 * 
 * @author ugo@ivu.de, cos@ivu.de - IVU Traffic Technologies AG
 */
public class EingangMsgXML extends BasicEingangMsg {

  private static final long serialVersionUID = -4487607568077201558L;

  private static final Logger LOGGER = Logger.getLogger(EingangMsgXML.class);

  private final String _emlId;
  private final Element _rootElement;

  public EingangMsgXML(AnwContext ersteller,
      URL url,
      Gebiet gebiet,
      String emlId,
      Element rootElement) {
    super(ersteller, url);
    _emlId = emlId;
    _rootElement = rootElement;
    // use file date, not import date
    if (Konstanten.USE_FILE_DATE) {
      if ("file".equals(url.getProtocol())) { //$NON-NLS-1$
        setEingangszeit(new Date(new File(url.getPath()).lastModified()));
      } else {
        try {
          setEingangszeit(new Date(url.openConnection().getDate()));
        } catch (IOException e) {
          if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Cannot get timestamp from url " + url + " : " + e); //$NON-NLS-1$ //$NON-NLS-2$
          }
        }
      }
    } else {
      setEingangszeit(new Date());
    }

    int source = ErgebniseingangKonstanten.SOURCE_FILE_IMPORT;
    if (SystemInfo.getSystemInfo().isFileInputWithManualConfirmation()) {
      source = ErgebniseingangKonstanten.SOURCE_FILE_IMPORT_AS_FIRST_INPUT;
    }
    setSource(source);

    setInputMode(EingangMsg.MODE_IGNORE_WARNINGS);
    init(gebiet);
  }

  private void init(Gebiet gebiet) {
    _gebietsartErfassungseinheit = gebiet.getGebietsart();
    _nummerErfassungseinheit = gebiet.getNummer();
    Collection<GruppeGebietsspezifisch> gruppeGebietsspezifischCol = gebiet
        .getGruppeGebietsspezifischCol();
    for (GruppeGebietsspezifisch gruppeGebietsspezifisch : gruppeGebietsspezifischCol) {
      int gruppenposition = gruppeGebietsspezifisch.getPosition();
      addGruppenergebnis(gruppenposition);
    }
  }

  @Override
  public String getMsgName() {
    // Klartextidentifikation
    return Messages.bind(MessageKeys.Msg_GebietsergebnisXML_0_1,
        Gebietsart.getKlartext(_gebietsartErfassungseinheit),
        _nummerErfassungseinheit);
  }

  /**
   * Gibt emlId zur�ck.
   * 
   * @return emlId.
   */
  public String getEmlId() {
    return _emlId;
  }

  /**
   * Gibt subRegionResults zur�ck.
   * 
   * @return subRegionResults.
   */
  public Element getRootElement() {
    return _rootElement;
  }
}