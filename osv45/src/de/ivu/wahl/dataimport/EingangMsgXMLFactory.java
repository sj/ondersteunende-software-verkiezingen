/*
 * EingangMsgXMLFactory
 * 
 * Copyright (c) 2002-4 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Nodes;
import nu.xom.ParsingException;

import de.ivu.wahl.AnwContext;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.Gebietsart;
import de.ivu.wahl.modell.WahlModel;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.Wahl;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.util.XMLImportHelper;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * Implementierung der Message f�r den Eingang einer Erfassungseinheit basierend auf dem Import
 * einer Nachricht im XML-Format
 * 
 * @author cos@ivu.de klie@ivu.de ugo@ivu.de IVU Traffic Technologies AG
 */

public abstract class EingangMsgXMLFactory {

  /**
   * @author cos@ivu.de, IVU Traffic Technologies AG
   */
  private static class FactoryHelper {
    private static final long serialVersionUID = -5572325655334778136L;

    private Element _rootElement;
    private final URL _url;

    /**
     * @param ersteller
     */
    FactoryHelper(AnwContext ersteller, URL url) {
      _url = url;
      initFromURL();
    }

    protected void initFromURL() {
      try {
        InputStream is = _url.openStream();
        Document doc;
        try {
          doc = new Builder().build(is);
        } finally {
          is.close();
        }
        _rootElement = doc.getRootElement();
      } catch (ParsingException pe) {
        throw new RuntimeException("Error!", pe); //$NON-NLS-1$
      } catch (IOException ioe) {
        throw new RuntimeException("Error!", ioe); //$NON-NLS-1$
      }
    }

    /**
     * @Override protected void init(Element rootElement) throws ImportException { _rootElement =
     *           rootElement; } /**
     * @return Root-Element der XML-Nachricht
     */
    Element getRootElement() {
      return _rootElement;
    }
  }

  public static EingangMsgXML createEingangMsg(AnwContext ersteller, URL url, Gebiet gebiet) {
    FactoryHelper helper = new FactoryHelper(ersteller, url);
    Element xmsg = helper.getRootElement();
    String msgId = xmsg.getAttributeValue(XMLTags.ATTR_EML_ID);
    return new EingangMsgXML(ersteller, url, gebiet, msgId, helper.getRootElement());
  }

  /**
   * @param anwContext
   * @param url
   * @param gebiet
   * @throws ImportException
   */
  public static void checkElectionAndRegion(AnwContext ersteller, URL url, Gebiet gebiet)
      throws ImportException {
    FactoryHelper helper = new FactoryHelper(ersteller, url);
    Element xmsg = helper.getRootElement();
    checkElectionAndRegion(xmsg, gebiet, url);
  }

  /**
   * Checking if validity of the result file (region and election)
   * 
   * @param xmsg content of the result file
   * @param regionCategory region category
   * @param regionNumber region number
   * @throws ImportException wrong result file or technical problem TODO
   */
  private static void checkElectionAndRegion(Element xmsg, Gebiet region, URL url)
      throws ImportException {
    Nodes authority = xmsg.query("//eml:" + XMLTags.EML_AUTHORITY_IDENTIFIER, //$NON-NLS-1$
        XMLTags.CONTEXT_EML);
    if (authority.size() == 1) {
      String authorityId = XMLImportHelper.getAttribute((Element) authority.get(0),
          XMLTags.ATTR_EML_ID);

      // extract authority name
      String authorityName = XMLImportHelper.getText((Element) authority.get(0));
      String pollingStationPrefix = Gebietsart.STIMMBEZIRK.getKlartext() + " "; //$NON-NLS-1$
      if (authorityName.startsWith(pollingStationPrefix)) {
        authorityName = authorityName.substring(pollingStationPrefix.length());
      }
      String[] authorityNameParts = authorityName.split(" \\(" + XMLTags.KEYWORD_POSTAL_CODE); //$NON-NLS-1$
      authorityName = authorityNameParts[0].trim();

      String gebietsIdStr = null;
      // find the last number
      int level;
      if (authorityId.startsWith("CSB")) { //$NON-NLS-1$
        level = GebietModel.EBENE_CSB;
      } else if (authorityId.startsWith("HSB")) { //$NON-NLS-1$
        level = GebietModel.EBENE_HSB;
        gebietsIdStr = authorityId.substring(3);
      } else if (authorityId.startsWith("SB")) { //$NON-NLS-1$
        level = GebietModel.EBENE_SB;
        gebietsIdStr = authorityId.substring(2);
      } else {
        level = GebietModel.EBENE_PSB;
        gebietsIdStr = authorityId;
      }
      if (gebietsIdStr != null) {
        int gebietsart = WahlModel.GEBIETSART_FOR_WAHLART_UND_EBENE.get(getElectionCategory(region
            .getWahl()), level);
        int gebietsNr = Integer.parseInt(gebietsIdStr);
        if (region.getGebietsart() != gebietsart || region.getNummer() != gebietsNr) {
          String regionFile = Gebietsart.getKlartext(gebietsart) + " " + gebietsNr; //$NON-NLS-1$
          String regionExpected = Gebietsart.getGebietsartKlartext(region) + " " //$NON-NLS-1$
              + region.getNummer();
          throw new ImportException(ImportException.TYPE_CONTENT,
              Messages.bind(MessageKeys.Error_Datei_0_EnthaeltDatenFuer_1_Nicht_2,
                  url.getFile(),
                  regionFile,
                  regionExpected));
        }
        // number ok, check name
        if (!region.getName().trim().equals(authorityName.trim())
            && !region.getName().trim().equals(pollingStationPrefix + authorityName.trim())) {
          throw new ImportException(ImportException.TYPE_CONTENT,
              Messages.bind(MessageKeys.Error_Datei_0_EnthaeltDatenFuer_1_Nicht_2,
                  url.getFile(),
                  authorityName,
                  region.getName()));
        }
        // check contest if applicable
        if (gebietsart == GebietModel.GEBIETSART_WAHLKREIS) {
          Nodes contestNodes = xmsg
              .query("//eml:" + XMLTags.EML_CONTEST_IDENTIFIER, XMLTags.CONTEXT_EML); //$NON-NLS-1$
          if (contestNodes.size() == 1) {
            Element contestIdentifier = (Element) contestNodes.get(0);
            // check number
            int contestId = XMLImportHelper.getAttributeIntValue(contestIdentifier,
                XMLTags.ATTR_EML_ID,
                true);
            if (contestId != gebietsNr) {
              throw new ImportException(ImportException.TYPE_CONTENT,
                  Messages.bind(MessageKeys.Error_Datei_0_EnthaeltDatenFuer_1_Nicht_2,
                      url.getFile(),
                      contestId,
                      gebietsNr));
            }
            // check name
            String contestName = contestIdentifier.getValue();
            if (!region.getName().trim().equals(contestName.trim())) {
              throw new ImportException(ImportException.TYPE_CONTENT,
                  Messages.bind(MessageKeys.Error_Datei_0_EnthaeltDatenFuer_1_Nicht_2,
                      url.getFile(),
                      contestName,
                      region.getName()));
            }
          }

        }
      }
    }
    Nodes electionNodes = xmsg.query("//eml:" + XMLTags.EML_WAHL_IDENTIFIER, XMLTags.CONTEXT_EML); //$NON-NLS-1$
    if (electionNodes.size() == 1) {
      Element electionIdentifier = (Element) electionNodes.get(0);
      AbstractImportClient.checkWahl(electionIdentifier, region.getWahl(), xmsg.getLocalName());
    }
  }

  private static ElectionCategory getElectionCategory(Wahl wahl) {
    return ElectionCategory.fromWahlart(wahl.getWahlart());
  }

}