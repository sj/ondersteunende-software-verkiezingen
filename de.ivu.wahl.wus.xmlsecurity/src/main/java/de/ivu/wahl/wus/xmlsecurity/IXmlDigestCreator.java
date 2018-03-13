/*
 * IXmlDigestCreator
 * 
 * Created on Nov 14, 2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.wus.xmlsecurity;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.Source;

/**
 * This interface defines method to create digest values (SHA) for XML messages
 * 
 * @author mike@ivu.de, IVU Traffic Technologies AG
 * @version $Id: IXmlDigestCreator.java,v 1.2 2009/04/17 12:12:51 cos Exp $
 */
public interface IXmlDigestCreator {
  /**
   * Read the original XML and convert it to the canonicalized form if there is a
   * CanonicalizationMethod element in the document specifying the algorithm for canonicalization.
   * If latter is not the case, the input data is left unchanged. The SHA-256 hash will be then
   * calculated on the result.
   * 
   * @param xmlInput stream with XML input to be read
   * @throws IOException if reading the input fails
   * @throws XmlParseException if the xml input could not be parsed
   * @return the digest value string representation (hex code, each hex number separated by blank
   */
  String createDigest(InputStream xmlInput) throws IOException, XmlParseException;

  /**
   * Read the original XML and convert it to the canonicalized form if there is a
   * CanonicalizationMethod element in the document specifying the algorithm for canonicalization.
   * If latter is not the case, the input data is left unchanged. The SHA-256 hash will be then
   * calculated on the result.
   * 
   * @param xmlInput source with XML input to be read
   * @throws IOException if reading the input fails
   * @throws XmlParseException if the xml input could not be parsed
   * @return the digest value string representation (hex code, each hex number separated by blank
   */
  String createDigest(Source xmlInput) throws IOException, XmlParseException;

  /**
   * get the content of the input as canonical XML for further processing. Prior to calling this
   * method, createDigest needs to be called
   * 
   * @throws FoundationRuntimeException if no input has been read (i.e. creatDigestt has not been
   *           called before)
   * @return
   */
  InputStream getCanonicalXmlContent() throws RuntimeException;

  /**
   * Read the original XML and convert it to the canonicalized form. The SHA-256 hash will be then
   * calculated on the result.
   * 
   * @param xmlInput source with XML input to be read
   * @throws IOException if reading the input fails
   * @throws XmlParseException if the xml input could not be parsed
   * @return the digest value string representation (hex code, each hex number separated by blank
   */
  String unconditionalCreateDigest(Source xmlInput) throws IOException, XmlParseException;

  /**
   * Read the original XML and convert it to the canonicalized form. The SHA-256 hash will be then
   * calculated on the result.
   * 
   * @param xmlInput source with XML input to be read
   * @throws IOException if reading the input fails
   * @throws XmlParseException if the xml input could not be parsed
   * @return the digest value string representation (hex code, each hex number separated by blank
   */
  String unconditionalCreateDigest(InputStream xmlInput) throws IOException, XmlParseException;
}
