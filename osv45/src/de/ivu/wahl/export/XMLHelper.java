/*
 * XMLHelper
 * 
 * Created on 23.02.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.batik.apps.rasterizer.Messages;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;

import de.ivu.wahl.dataimport.XMLTags;

/**
 * Helper class for creating xml result documents
 * 
 * @author ugo@ivu.de, IVU Traffic Technologies AG

 */
public abstract class XMLHelper {

  final static Map<String, String> NAMESPACE_PREFIXES = new HashMap<String, String>();

  static {
    NAMESPACE_PREFIXES.put(XMLTags.NS_DS, XMLTags.NS_PREFIX_DS);
    NAMESPACE_PREFIXES.put(XMLTags.NS_ED, XMLTags.NS_PREFIX_ED);
    NAMESPACE_PREFIXES.put(XMLTags.NS_EML, XMLTags.NS_PREFIX_EML);
    NAMESPACE_PREFIXES.put(XMLTags.NS_KR, XMLTags.NS_PREFIX_KR);
    NAMESPACE_PREFIXES.put(XMLTags.NS_RG, XMLTags.NS_PREFIX_RG);
    NAMESPACE_PREFIXES.put(XMLTags.NS_XAL, XMLTags.NS_PREFIX_XAL);
    NAMESPACE_PREFIXES.put(XMLTags.NS_XNL, XMLTags.NS_PREFIX_XNL);
    NAMESPACE_PREFIXES.put(XMLTags.NS_XSI, XMLTags.NS_PREFIX_XSI);
  }

  public static Document createDocument(String rootElement, String defaultNamespace) {
    Element root = new Element(rootElement, defaultNamespace);
    return new Document(root);
  }

  public static Element createElement(String elementName, String namespace) {
    String nameWithPrefix;
    if (!namespace.equals(XMLTags.NS_EML)) {
      nameWithPrefix = NAMESPACE_PREFIXES.get(namespace) + ":" + elementName; //$NON-NLS-1$
    } else {
      nameWithPrefix = elementName;
    }
    return new Element(nameWithPrefix, namespace);
  }

  public static Element createElementWithValue(String elementName, String namespace, int value) {
    return createElementWithValue(elementName, namespace, String.valueOf(value));
  }

  public static Element createElementWithValue(String elementName, String namespace, String value) {
    Element element = createElement(elementName, namespace);
    element.appendChild(value);
    return element;
  }

  public static Element createElementWithAttribute(String elementName,
      String namespace,
      String attribute,
      String attrValue) {
    Element element = createElement(elementName, namespace);
    element.addAttribute(new Attribute(attribute, attrValue));
    return element;
  }

  static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS"); //$NON-NLS-1$

  public static String createTimeString(long time) {
    return dateFormat.format(new Date(time));
  }

  static SimpleDateFormat dateFormatIN = new SimpleDateFormat("dd-MM-yyyy"); //$NON-NLS-1$
  static SimpleDateFormat dateFormatXML = new SimpleDateFormat("yyyy-MM-dd"); //$NON-NLS-1$

  public static String createDateString(String dateInput) {
    try {
      return dateFormatXML.format(dateFormatIN.parse(dateInput));
    } catch (ParseException e) {
      throw new RuntimeException(
          Messages.get("Error_UngueltigesDatumsFormat") + ": " + dateInput, e); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }
}
