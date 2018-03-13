/*
 * BasicRGHelper
 * 
 * Created on 27.11.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import static de.ivu.wahl.dataimport.XMLTags.NS_RG;

import java.util.List;

import javax.ejb.FinderException;

import org.apache.log4j.Logger;

import nu.xom.Element;

import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.dataimport.GruppeAllgemeinXmlAdapter;
import de.ivu.wahl.modell.GruppeKonstanten;
import de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.etc.GeneralVotingResults;

/**
 * @author tdu@ivu.de, IVU Traffic Technologies AG
 */
public class BasicRGHelper {

  protected final Logger LOGGER = Logger.getLogger(BasicRGHelper.class);
  protected final ExportHandlingBean bean;

  @SuppressWarnings("hiding")
  BasicRGHelper(ExportHandlingBean bean) {
    this.bean = bean;
  }

  protected void appendVotesForRGGeneralGroups(Element parent, String id_Ergebniseingang) {
    String id_WurzelGebiet = WahlInfo.getWahlInfo().getID_Wurzelgebiet();
    Gebiet wurzelGebiet;
    try {
      wurzelGebiet = bean.getGebietHome().findByPrimaryKey(id_WurzelGebiet);
    } catch (FinderException e) {
      e.printStackTrace();
      return;
    }
    GruppeAllgemeinXmlAdapter adapter = new GruppeAllgemeinXmlAdapter();
    List<GruppeAllgemein> gruppenAllgemein = GruppeKonstanten.GruppeAllgemein
        .filterGruppenAllgemeinVisibleInRegionOrGueltige(wurzelGebiet,
            adapter.getGruppenAllgemein());

    GeneralVotingResults generalVotingResults = bean.getVotesHandling()
        .getGeneralVotingResults(id_Ergebniseingang, id_WurzelGebiet);
    for (GruppeAllgemein gruppeAllgemein : gruppenAllgemein) {
      int value = generalVotingResults.getVotes(gruppeAllgemein);
      adapter.putRgXml(parent, gruppeAllgemein, value);
    }
  }

  // *********** XMLHelper.createElement(name, NS_RG);

  protected Element createRGElement(String name) {
    return XMLHelper.createElement(name, NS_RG);
  }

  protected Element createRGElementWithValue(String name, String value) {
    return XMLHelper.createElementWithValue(name, NS_RG, value);
  }

  protected Element createRGElementWithValue(String name, int value) {
    return XMLHelper.createElementWithValue(name, NS_RG, value);
  }

  protected Element createRGElementWithAttribute(String name, String attrName, String attrValue) {
    return XMLHelper.createElementWithAttribute(name, NS_RG, attrName, attrValue);
  }

}
