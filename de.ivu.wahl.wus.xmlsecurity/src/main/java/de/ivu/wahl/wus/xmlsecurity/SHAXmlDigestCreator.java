/*
 * SHAXmlDigestCreator
 * 
 * Created on Nov 14, 2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.wus.xmlsecurity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import nu.xom.XPathContext;
import nu.xom.canonical.Canonicalizer;

public class SHAXmlDigestCreator implements IXmlDigestCreator {
  private static final String MD = "SHA-256"; //$NON-NLS-1$
  final static int BUFFER_SIZE = 10000;
  final static byte[] BUFFER = new byte[BUFFER_SIZE];
  private byte[] _canonicalXml;

  public String createDigest(Source xmlInput) throws IOException, XmlParseException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    serialize(xmlInput, bos);
    return createDigest(new ByteArrayInputStream(bos.toByteArray()));
  }

  public String createDigest(InputStream xmlInput) throws IOException, XmlParseException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    conditionalCanonicalizeXml(xmlInput, bos);
    byte[] canonicalizedContent = bos.toByteArray();
    String digest = createDigestFromBytes(canonicalizedContent, true);
    _canonicalXml = canonicalizedContent;
    return digest;
  }

  public String unconditionalCreateDigest(Source xmlInput) throws IOException, XmlParseException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    serialize(xmlInput, bos);
    return unconditionalCreateDigest(new ByteArrayInputStream(bos.toByteArray()));
  }

  public String unconditionalCreateDigest(InputStream xmlInput)
      throws IOException, XmlParseException {

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    canonicalizeXml(xmlInput, bos);
    byte[] canonizedContent = bos.toByteArray();
    String digest = createDigestFromBytes(canonizedContent, true);
    _canonicalXml = canonizedContent;
    return digest;
  }

  /**
   * Returns canonical form of the last canonicalized document, if the document specifies a
   * canonalization, orginal document, if there was no canonicalization specified, or else a
   * {@link RuntimeException}, if there was no call for canonalization prior to calling this method.
   */
  public InputStream getCanonicalXmlContent() throws RuntimeException {
    if (null == _canonicalXml) {
      throw new RuntimeException("no input has been supplied"); //$NON-NLS-1$
    }
    return new ByteArrayInputStream(_canonicalXml);
  }

  String createDigestFromBytes(byte[] content, boolean separateCharactersWithBlank) {
    try {
      MessageDigest md = MessageDigest.getInstance(MD);
      final String charSeparator = separateCharactersWithBlank ? " " : ""; //$NON-NLS-1$ //$NON-NLS-2$
      StringBuilder sb = new StringBuilder();
      boolean withSeparator = false;

      byte[] digest = md.digest(content);
      for (byte digit : digest) {
        String hexString = Integer.toHexString(digit & 0xff).toUpperCase();
        if (hexString.length() < 2) {
          hexString = "0" + hexString; //$NON-NLS-1$
        }
        sb.append(hexString);
        if (withSeparator) {
          sb.append(charSeparator);
        }
        withSeparator = !withSeparator;
      }

      return sb.toString().trim();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Digest algorithm not found", e); //$NON-NLS-1$
    }
  }

  /**
   * Read the original XML and convert it to canonicalized form if there is a CanonicalizationMethod
   * element in the document specifying the algorithm for canonicalization. If latter is not the
   * case, NO OUTPUT WILL BE WRITTEN. In case that canonicalization takes place, both streams are
   * closed prior to the completion of the method.
   * 
   * @param origXml original XML to read
   * @param processedXml output for canonized XML
   * @throws IOException cannot write or read the streams
   * @throws ParsingException origXml does not provide valid XML
   */
  void conditionalCanonicalizeXml(InputStream origXml, OutputStream processedXml)
      throws IOException, XmlParseException {

    // buffer the whole source XML in order to re-read it later
    ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
    copyStream(origXml, bufferStream);
    origXml.close();
    final byte[] buffer = bufferStream.toByteArray();
    Document doc = parseXml(new ByteArrayInputStream(buffer));
    XPathContext namespaces = new XPathContext("ds", "http://www.w3.org/2000/09/xmldsig#");
    Nodes canonicalizationNodes = doc.query("//ds:CanonicalizationMethod", namespaces);
    if (canonicalizationNodes.size() > 0) {
      String algoritm = ((Element) canonicalizationNodes.get(0)).getAttributeValue("Algorithm");
      Canonicalizer canonicalizer = new Canonicalizer(processedXml, algoritm);
      canonicalizer.write(doc);
    } else {
      processedXml.write(buffer);
    }
    processedXml.close();
  }

  /**
   * Read the original XML and convert it to canonized form. Both streams are closed prior to the
   * completion of the method.
   * 
   * @param origXml original XML to read
   * @param processedXml output for canonized XML
   * @throws IOException cannot write or read the streams
   * @throws ParsingException origXml does not provide valid XML
   */
  void canonicalizeXml(InputStream origXml, OutputStream processedXml)
      throws IOException, XmlParseException {

    new Canonicalizer(processedXml, false).write(parseXml(origXml));
    origXml.close();
    processedXml.close();
  }

  /**
   * Copies input stream to the output stream.
   * 
   * @param in {@link InputStream}
   * @param out {@link OutputStream}
   * @throws IOException if {@link InputStream} cannot be read or {@link OutputStream} written
   */
  void copyStream(InputStream in, OutputStream out) throws IOException {
    boolean readBytes;
    do {
      synchronized (BUFFER) {
        int amountRead = in.read(BUFFER);
        readBytes = amountRead > -1;
        if (readBytes) {
          out.write(BUFFER, 0, amountRead);
        }
      }
    } while (readBytes);
  }

  private Document parseXml(InputStream origXml) throws IOException, XmlParseException {
    Document doc;
    final boolean validate = false;
    Builder parser = new Builder(validate);
    try {
      doc = parser.build(origXml);
    } catch (ValidityException ex) {
      doc = ex.getDocument();
    } catch (ParsingException e) {
      throw new XmlParseException("Failed to parse xml", e); //$NON-NLS-1$
    }
    return doc;
  }

  private void serialize(Source doc, OutputStream out) throws XmlParseException {
    try {
      StreamResult streamResult = new StreamResult(out);
      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer serializer = tf.newTransformer();
      serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); //$NON-NLS-1$
      serializer.transform(doc, streamResult);
    } catch (Exception e) {
      throw new XmlParseException(e.getMessage(), e);
    }
  }
}
