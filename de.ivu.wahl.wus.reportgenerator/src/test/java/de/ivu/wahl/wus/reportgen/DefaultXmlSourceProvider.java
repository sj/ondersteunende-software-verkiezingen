/*
 * DefaultXmlSourceProvider
 * 
 * Created on 18.11.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Provides test data.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
class DefaultXmlSourceProvider {
  private static final Logger log = Logger.getLogger(DefaultXmlSourceProvider.class);
  private static final String PATH = DefaultXmlSourceProvider.class.getResource("/de/ivu/wahl/wus/reportgen/sps/")
      .getFile();
  private static final String[] INPUT_FILENAMES = new String[]{
      "EML 210 PS2003 Venlo Hermans-rg.xml", "EML 230a PS2003 Venlo-rg.xml",
      "EML 230c PS2003 Limburg-rg.xml", "EML 510b GR 2002 Nuth-rg.xml"};
  private static final String[] BLANKO_FILENAMES = new String[]{
      "EML 210 PS2003 Venlo Hermans-rg-blanko.xml", "EML 230a PS2003 Venlo-rg-blanko.xml",
      "EML 230c PS2003 Limburg-rg-blanko.xml", "EML 510b GR 2002 Nuth-rg.xml"};
  private static final String INPUT_FILENAME_230B = "EML 230b PS2003 Venlo.xml";

  public static final DefaultXmlSourceProvider instance = new DefaultXmlSourceProvider();

  /**
   * @return an XML source from a DOM tree for testing
   */
  Source getDomEml230bSource() {
    Node domNode = readDomFromFile(new File(PATH, INPUT_FILENAME_230B));
    DOMSource src = new DOMSource(domNode);
    return src;
  }

  /**
   * @return an XML source from a DOM tree for testing
   */
  Source getDomXmlSource(int program, boolean blanko) {
    File xmlFile = getDefaultXmlFile(program, blanko);
    log.info("Loading DOM from " + xmlFile);
    Node domNode = readDomFromFile(xmlFile);
    DOMSource src = new DOMSource(domNode);
    return src;
  }

  /**
   * @return an XML source from an XML file for testing
   */
  Source getXmlFileSource(int program) {
    boolean blanko = false;
    StreamSource input = new StreamSource(getDefaultXmlFile(program, blanko));
    return input;
  }

  /**
   * Parse the given xmlfile. Return the root node.
   */
  private Node readDomFromFile(File xmlfile) {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(xmlfile);
      return doc;

    } catch (ParserConfigurationException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } catch (SAXException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private File getDefaultXmlFile(int program, boolean blanko) {
    if (!blanko) {
      return new File(PATH, INPUT_FILENAMES[program - 1]);
    } else {
      return new File(PATH, BLANKO_FILENAMES[program - 1]);
    }
  }

}
