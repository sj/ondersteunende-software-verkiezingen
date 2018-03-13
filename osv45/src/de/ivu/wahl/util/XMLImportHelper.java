/*
 * XMLHelper
 * 
 * Copyright (c) 2002-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Category;
import org.w3c.dom.DOMException;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;

import de.ivu.util.debug.Log4J;
import de.ivu.wahl.client.util.ClientHelper;
import de.ivu.wahl.dataimport.XMLTags;
import de.ivu.wahl.export.Roman;
import de.ivu.wahl.modell.exception.ImportException;

/**
 * Kleine Hilfsmethoden f�r das Parsen von XML �ber DOM
 * 
 * @author klie@ivu.de Copyright (c) 2002 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public abstract class XMLImportHelper {

  static final Category LOGGER = Log4J.configure(XMLImportHelper.class);

  static {
    LOGGER.info(Log4J.dumpVersion(XMLImportHelper.class, Log4J.extractVersion("$Revision: 1.3 $"))); //$NON-NLS-1$
  }

  /**
   * @return Wert eines beannten Attributs oder <code>null</code>, wenn nicht gefunden
   * @param node Node
   * @param name Name des Attributs
   */
  public static String getAttribute(Element node, String name) {
    Attribute attr = node.getAttribute(name);
    if (attr != null) {
      return attr.getValue();
    } else {
      return null;
    }
  }

  /**
   * @return ein benanntes Kind oder <code>null</code>
   * @param node ElternKnoten
   * @param name name des gesuchten Kindes
   */
  public static Element getNamedChild(Element node, String name) {
    Element workNode = node.getFirstChildElement(name);
    // int idx = 0;
    // while (workNode != null && !workNode.getQualifiedName().equals(name)) {
    // workNode = workNode.getChildElements()
    // }
    return workNode;
  }

  // /**
  // * Holt die Konketenation aller text-nodes und f�gt f�r ander nodes Zeilenumbr�che ein ...
  // *
  // * @param node der Node
  // * @return den zusammengef�gten Text
  // */
  // public static String getAllText(Node node) {
  // StringBuilder erg = new StringBuilder();
  // Node workNode = node;
  // while (workNode != null) {
  // if (workNode.getNodeType() == Node.TEXT_NODE) {
  // erg.append(workNode.getNodeValue());
  // } else {
  // erg.append(Konstanten.BR);
  // }
  // workNode = workNode.getNextSibling();
  // }
  // return erg.toString();
  // }
  //
  /**
   * Gibt den Inhalt eines Textknotens bzw. des ersten Kindknotens, der ein Textknoten ist
   * 
   * @param node der Node
   * @return den zusammengef�gten Text
   */
  public static String getText(Element node) {
    if (node == null) {
      return null;
    }
    return node.getValue();
  }

  public static int getIntValue(Element node) throws NumberFormatException {
    return Integer.parseInt(node.getValue());
  }

  public static Timestamp getDateValue(Element node) throws ParseException, DOMException {
    String value = getText(node);
    if (value == null || value.length() == 0) {
      return null;
    } else {
      return parseDate(value);
    }
  }

  public static Timestamp getDateTimeValue(Element node) throws ParseException, DOMException {
    String value = getText(node);
    if (value == null || value.length() == 0) {
      return null;
    } else {
      return new Timestamp(ClientHelper.parseDateTime(value).getTime());
    }
  }

  public static int getAttributeIntValue(Element node, String attribute)
      throws NumberFormatException, DOMException {
    return getAttributeIntValue(node, attribute, false);
  }

  public static int getAttributeIntValue(Element node,
      String attribute,
      boolean isRomanNumeralsAllowed) throws NumberFormatException, DOMException {

    String attributeValue = node.getAttributeValue(attribute);
    int result = -1;
    if (attributeValue == null || attributeValue.length() == 0
        || (XMLTags.NOT_APPLICABLE.equals(attributeValue))) {
      result = -1;
    } else {
      try {
        result = Integer.parseInt(attributeValue);
      } catch (NumberFormatException e) {
        if (isRomanNumeralsAllowed) {
          result = Roman.toLong(attributeValue).intValue();
        } else {
          throw e;
        }
      }
    }
    return result;
  }

  public static int getAttributeIntValueFromRomanNumerals(Element node, String attribute)
      throws NumberFormatException, DOMException {

    String attributeValue = node.getAttributeValue(attribute);
    if (attributeValue == null || attributeValue.length() == 0
        || (XMLTags.NOT_APPLICABLE.equals(attributeValue))) {
      return -1;
    } else {
      return Roman.toLong(attributeValue).intValue();
    }
  }

  public static Timestamp getAttributeDateValue(Element node, String attribute)
      throws DOMException, ParseException {

    String attributeValue = node.getAttributeValue(attribute);
    if (attributeValue.length() == 0) {
      return null;
    } else {
      return parseDate(attributeValue);
    }
  }

  public static Date getAttributeDateTimeValue(Element node, String attribute)
      throws DOMException, ParseException {
    String attributeValue = node.getAttributeValue(attribute);
    if (attributeValue.length() == 0) {
      return null;
    } else {
      return ClientHelper.parseDateTime(attributeValue);
    }
  }

  public static boolean getAttributeBoolValue(Element node, String attribute, boolean defaultValue)
      throws NumberFormatException, DOMException {
    if (node == null) {
      return defaultValue;
    }
    String value = node.getAttributeValue(attribute);
    if (value == null || value.length() == 0) {
      return defaultValue;
    } else {
      return "true".equalsIgnoreCase(value) || "1".equalsIgnoreCase(value); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd"); //$NON-NLS-1$

  public static Timestamp parseDate(String dateStr) throws ParseException {
    return new Timestamp(DATE_FORMAT.parse(dateStr).getTime());
  }

  /**
   * Gibt den Inhalt eines Textknotens bzw. des ersten Kindknotens, der ein Textknoten ist
   * 
   * @param node der Node
   * @return den zusammengef�gten Text
   * @throws ImportException
   */
  public static String getText(Element node, String elementName, String namespace)
      throws ImportException {
    Elements children = node.getChildElements(elementName, namespace);
    if (children.size() == 0) {
      LOGGER.debug("Child " + elementName + " not found "); //$NON-NLS-1$ //$NON-NLS-2$
      return null;
    }
    if (children.size() > 1) {
      throw new ImportException(ImportException.TYPE_CONTENT, "Only one element " + elementName //$NON-NLS-1$
          + " expected!"); //$NON-NLS-1$
    }
    return children.get(0).getValue();
  }

  /**
   * Returns an Integer value, if all parameters are given correct. The last parameter is a default
   * value, that has to be given to the method. It's used to set the result to a default value if
   * the node, namespace or element name cannot be read.
   * 
   * @return
   * @throws NumberFormatException
   * @throws ImportException
   */
  public static int getIntValue(Element node, String elementName, String namespace, int defaultVal)
      throws NumberFormatException, ImportException {
    String strValue = getText(node, elementName, namespace);
    if (strValue == null) {
      return defaultVal;
    }
    return Integer.parseInt(strValue);
  }

}