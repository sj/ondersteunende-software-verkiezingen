/*
 * EingangHandling
 * 
 * Copyright (c) 2002-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

/**
 * Hilfsfunktionen zum Import
 * 
 * @author klie@ivu.de cos@ivu.de - IVU Traffic Technologies AG
 */

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Category;
import org.apache.log4j.xml.SAXErrorHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import de.ivu.util.debug.Log4J;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.exception.ImportException;

public abstract class ImportUtil {
  final static Category LOGGER = Log4J.configure(ImportUtil.class);

  static {
    LOGGER.info(Log4J.dumpVersion(ImportUtil.class, Log4J.extractVersion("$Revision: 1.23 $"))); //$NON-NLS-1$
  }

  /**
   * Pr�ft, on das Wurzelelement den Namen <code>nodeName</code> hat, und gibt das Element dann
   * zur�ck
   * 
   * @param filename Dateiname
   * @param nodeName Name des gesuchten Wurzelelements
   * @return das Wurzelelement
   * @throws ImportException Fehler beim Lesen oder Decodieren des Datensatzes oder das
   *           Wurzelelement hat nicht den erwarteten Namen
   */
  public static Element readXMLRoot(String filename, String nodeName) throws ImportException {
    File f = new File(filename);
    try {
      return readXMLRoot(f.toURI().toURL(), nodeName);
    } catch (MalformedURLException e) {
      throw new ImportException(Messages.getString(MessageKeys.Error_UngueltigerFilename)
          + filename, e);
    }
  }

  public static Element readXMLRoot(URL url, String nodeName) throws ImportException {
    InputStream inputStream = null;
    try {
      inputStream = url.openStream();
      Element root = getRootElement(inputStream);
      if (root.getQualifiedName().equals(nodeName)) {
        return root;
      } else {
        throw new ImportException(url, Messages.bind(MessageKeys.Error_Wurzelelement_0_erwartet,
            nodeName));
      }
    } catch (IOException e) {
      LOGGER.error(e, e);
      throw new ImportException(url, nodeName
          + ": " + Messages.getString(MessageKeys.Error_URLNichtLesbar), //$NON-NLS-1$
          0, e);
    } catch (ValidityException e) {
      throw new ImportException(e.getMessage());
    } catch (ParsingException e) {
      throw new ImportException(e.getMessage());
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          throw new ImportException(
              Messages.getString(MessageKeys.Error_FehlerBeimSchliessenDesInputStreams));
        }
      }
    }
  }

  public static Element readXMLRoot(URL url) throws ImportException {
    try {
      InputStream inputStream = url.openStream();
      try {
        return getRootElement(inputStream);
      } catch (Exception e) {
        LOGGER.error(e, e);
        throw new ImportException(url, Messages.getString(MessageKeys.Error_KannXMLNichtParsen), 0,
            e);
      } finally {
        inputStream.close();
      }
    } catch (Exception e) {
      throw new ImportException(url, Messages.getString(MessageKeys.Error_URLNichtLesbar), 0, e);
    }
  }

  public static Element readXMLRoot(String docAsString) throws ImportException {
    try {
      return new Builder().build(new StringReader(docAsString)).getRootElement();
    } catch (Exception e) {
      LOGGER.error(e, e);
      throw new ImportException(
          Messages.getString(MessageKeys.ERROR_DATEI_KONNTE_NICHT_GELADEN_WERDEN), e);
    }
  }

  public static Element readXMLRoot4Schema(URL url, String schema) throws ImportException {
    InputStream inputStream = null;
    try {
      inputStream = url.openStream();
      return getRootElement(inputStream, schema);
    } catch (IOException e) {
      throw new ImportException(url, Messages.getString(MessageKeys.Error_URLNichtLesbar), 0, e);
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          inputStream = null;
        }
      }
    }
  }

  /**
   * @param f zu lesende Datei
   * @return Wurzelelement des Dokuments aus der Datei
   * @throws IOException
   * @throws ParsingException
   * @throws ValidityException
   * @throws SAXException Problem beim Decodieren der XML-Struktur
   * @throws IOException Problem beim Lesen der Datei
   * @throws ParserConfigurationException if a DocumentBuilder cannot be created which satisfies the
   *           configuration requested.
   * @throws ParsingException
   * @throws ValidityException
   */
  private static Element getRootElement(InputStream inputStream)
      throws ValidityException, ParsingException, IOException {
    Document doc = new Builder().build(inputStream);
    // Wurzelelement
    return doc.getRootElement();
  }

  private static Element getRootElement(InputStream inputStream, String schema)
      throws ImportException {
    SAXParserFactory factory = getSAXParserFactory();
    XMLReader reader = null;
    try {
      SAXParser saxParser = factory.newSAXParser();
      reader = saxParser.getXMLReader();
      reader.setEntityResolver(new OSVResolver());
      reader.setErrorHandler(new SAXErrorHandler());
      reader.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", //$NON-NLS-1$
          "http://www.w3.org/2001/XMLSchema"); //$NON-NLS-1$
      reader.setFeature("http://apache.org/xml/features/validation/schema", false); //$NON-NLS-1$
    } catch (ParserConfigurationException e) {
      LOGGER.error(e, e);
      throw new ImportException(Messages.getString(MessageKeys.Error_FehlerBeimParserBauen), e);
    } catch (SAXException e) {
      LOGGER.error(e, e);
      throw new ImportException(Messages.getString(MessageKeys.Error_FehlerBeimParserBauen), e);
    }
    Document doc = null;
    try {
      doc = new Builder(reader, false).build(inputStream);
      reader.setFeature("http://apache.org/xml/features/validation/schema", true); //$NON-NLS-1$
      Document validatedDoc = new Builder(reader, true).build(getStreamWithSchema(doc, schema));
      // return document root
      return validatedDoc.getRootElement();
    } catch (ValidityException e) {
      LOGGER.error(e, e);
      if (doc != null && e.getMessage().contains("kr:RegisteredParty") //$NON-NLS-1$
          && e.getMessage().contains("xml:space")) { //$NON-NLS-1$
        // Ignore error
        // "cvc-complex-type.3.2.2: Attribute 'xml:space' is not allowed to appear in element 'kr:RegisteredParty'"
        return doc.getRootElement();
      }
      throw new ImportException(e.getMessage(), e);
    } catch (ParsingException e) {
      LOGGER.error(e, e);
      throw new ImportException(e.getMessage(), e);
    } catch (IOException e) {
      LOGGER.error(e, e);
      throw new ImportException(e.getMessage(), e);
    } catch (SAXNotRecognizedException e) {
      LOGGER.error(e, e);
      throw new ImportException(e.getMessage(), e);
    } catch (SAXNotSupportedException e) {
      LOGGER.error(e, e);
      throw new ImportException(e.getMessage(), e);
    }
  }

  private static SAXParserFactory getSAXParserFactory() throws ImportException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(true);
    factory.setNamespaceAware(true);

    // The following code is based on
    // https://www.owasp.org/index.php/XML_External_Entity_(XXE)_Prevention_Cheat_Sheet#JAXP_DocumentBuilderFactory.2C_SAXParserFactory_and_DOM4J
    // to prevent XML External Entity (XXE) attacks
    String feature = null;
    try {
      // This is the PRIMARY defense. If DTDs (doctypes) are disallowed, almost all XML entity
      // attacks are prevented
      // Xerces 2 only - http://xerces.apache.org/xerces2-j/features.html#disallow-doctype-decl
      feature = "http://apache.org/xml/features/disallow-doctype-decl"; //$NON-NLS-1$
      factory.setFeature(feature, true);

      // If you can't completely disable DTDs, then at least do the following:
      // Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-general-entities
      // Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-general-entities
      // JDK7+ - http://xml.org/sax/features/external-general-entities
      feature = "http://xml.org/sax/features/external-general-entities"; //$NON-NLS-1$
      factory.setFeature(feature, false);

      // Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-parameter-entities
      // Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-parameter-entities
      // JDK7+ - http://xml.org/sax/features/external-parameter-entities
      feature = "http://xml.org/sax/features/external-parameter-entities"; //$NON-NLS-1$
      factory.setFeature(feature, false);

      // Disable external DTDs as well
      feature = "http://apache.org/xml/features/nonvalidating/load-external-dtd"; //$NON-NLS-1$
      factory.setFeature(feature, false);

      // and these as well, per Timothy Morgan's 2014 paper: "XML Schema, DTD, and Entity Attacks"
      factory.setXIncludeAware(false);
      // does not compile: factory.setExpandEntityReferences(false);

      // And, per Timothy Morgan: "If for some reason support for inline DOCTYPEs are a requirement,
      // then
      // ensure the entity settings are disabled (as shown above) and beware that SSRF attacks
      // (http://cwe.mitre.org/data/definitions/918.html) and denial
      // of service attacks (such as billion laughs or decompression bombs via "jar:") are a risk."

    } catch (ParserConfigurationException e) {
      // This should catch a failed setFeature feature
      LOGGER.info("ParserConfigurationException was thrown. The feature '" + feature //$NON-NLS-1$
          + "' is probably not supported by your XML processor."); //$NON-NLS-1$
      LOGGER.error(e, e);
      throw new ImportException(e.getMessage(), e);
    } catch (SAXException e) {
      // On Apache, this should be thrown when disallowing DOCTYPE
      LOGGER.warn("A DOCTYPE was passed into the XML document"); //$NON-NLS-1$
      LOGGER.error(e, e);
    }

    return factory;
  }

  private static InputStream getStreamWithSchema(Document doc, String schema)
      throws ImportException {
    try {
      Attribute schemaAttr = doc.getRootElement().getAttribute(XMLTags.ATTR_SCHEMA, XMLTags.NS_XSI);
      if (schemaAttr != null) {
        doc.getRootElement().removeAttribute(schemaAttr);
      }
      schemaAttr = new Attribute(XMLTags.ATTR_SCHEMA, schema);
      schemaAttr.setNamespace(XMLTags.NS_PREFIX_XSI, XMLTags.NS_XSI);
      doc.getRootElement().addAttribute(schemaAttr);
      return new ByteArrayInputStream(doc.toXML().getBytes("UTF-8")); //$NON-NLS-1$
    } catch (UnsupportedEncodingException e) {
      LOGGER.error(e, e);
      throw new ImportException(e.getMessage(), e);
    }
  }

  public static class OSVResolver implements EntityResolver {
    public InputSource resolveEntity(String publicId, String systemId) {
      String schema = null;
      if (systemId != null) {
        schema = "/schemas/" + systemId.substring(systemId.lastIndexOf("/") + 1); //$NON-NLS-1$ //$NON-NLS-2$
      }
      if (schema != null) {
        // return a special input source
        InputStream resourceAsStream = this.getClass().getResourceAsStream(schema);
        if (resourceAsStream == null) {
          throw new RuntimeException(
              "Schema file not found. EML schema files are copied to the <schema> folder using the ant task <compile>."); //$NON-NLS-1$
        }
        Reader reader = new InputStreamReader(resourceAsStream);
        InputSource inputSource = new InputSource(reader);
        inputSource.setSystemId(schema);
        return inputSource;
      } else {
        // use the default behaviour
        return null;
      }
    }
  }
}