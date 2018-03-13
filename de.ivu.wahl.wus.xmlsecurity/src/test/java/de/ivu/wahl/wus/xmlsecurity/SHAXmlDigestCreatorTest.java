/*
 * SHAXmlDigestCreatorTest
 * 
 * Created on Nov 14, 2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.wus.xmlsecurity;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.w3c.dom.Document;

public class SHAXmlDigestCreatorTest {
  private static final String UTF8 = "UTF-8";
  private final SHAXmlDigestCreator cut = new SHAXmlDigestCreator();

  @Test
  public void testCanonizeXml() throws Exception {
    assertEquals("<root></root>", canonicalize("<root  ></root>"));
    assertEquals("<root>\n\n</root>", canonicalize("<root>\n\n</root>"));
    // assertEquals("<root>\n</root>", xomCanonize("<root>\n\r</root>"));
    assertEquals("<root>\n</root>", canonicalize("<root>\r\n</root>"));
    assertEquals("<root></root>", canonicalize("<root/>"));
    assertEquals("<root id=\"some\"></root>", canonicalize("<root   id  =\"some\" ></root>"));
    assertEquals("<root id=\"some\"></root>", canonicalize("<root   id  ='some' ></root>"));
  }

  @Test
  public void testCanonizeXmlFilebased() throws Exception {
    // some test cases are created using the examples
    // at: http://www.w3.org/TR/xml-c14n.html#Examples
    checkCanonizationForFile("input1");
    checkCanonizationForFile("input2");
    checkCanonizationForFile("input3");
    checkCanonizationForFile("input4");
    checkCanonizationForFile("input5");
    checkCanonizationForFile("input6");
  }

  @Test
  public void testCreateDigestFromBytes() throws UnsupportedEncodingException {
    assertEquals("A1FC E436 3854 FF88 8CFF 4B8E 7875 D600 C268 2390 412A 8CF7 9B37 D0B1 1148 B0FA".toUpperCase(),
        cut.createDigestFromBytes("y".getBytes(UTF8), true));
    assertEquals("5916 2C6B 059F 619B 0538 F592 DE24 E163 0613 1657 2869 FFC9 A264 8315 DBE7 5997".toUpperCase(),
        cut.createDigestFromBytes("sample message".getBytes(UTF8), true));
    assertEquals("59162c6b059f619b0538f592de24e163061316572869ffc9a2648315dbe75997".toUpperCase(),
        cut.createDigestFromBytes("sample message".getBytes(UTF8), false));
  }

  @Test(expected = XmlParseException.class)
  public void testCreateDigestNoXmlInput() throws Exception {
    cut.unconditionalCreateDigest(asStream("xyz"));
  }

  @Test
  public void testCreateDigest() throws Exception {
    // refernce SHA created using: http://holger.thoelking.name/skripten/md5
    {
      String expDigest =
                         "b51d c19c c2e1 5197 81d4 49d7 641c 66fb 1368 e85a 01fc 89ad 40a3 c0d2 9db0 c38d"
                             .toUpperCase();
      assertEquals(expDigest, cut.unconditionalCreateDigest(asStream("<a>xyz</a>")));
      assertEquals(expDigest, cut.unconditionalCreateDigest(asSource("<a>xyz</a>")));
      assertEquals(expDigest, cut.unconditionalCreateDigest(asStream("<a  >xyz</a>")));
      assertEquals(expDigest, cut.unconditionalCreateDigest(asSource("<a  >xyz</a>")));
    }

    String expDigest =
                       "79d7 f437 1feb 9cd6 3371 3020 9232 b70c dc68 8d5a b4eb dff7 8172 4fe2 44c9 8445"
                           .toUpperCase();
    assertEquals(expDigest,
        cut.unconditionalCreateDigest(getClass().getResourceAsStream("input4.xml")));
    assertEquals(expDigest, cut.unconditionalCreateDigest(new DOMSource(parse(getClass()
        .getResourceAsStream("input4.xml")))));
    assertEquals(expDigest,
        cut.unconditionalCreateDigest(getClass().getResourceAsStream("input4.canonized")));
    assertEquals(expDigest, cut.unconditionalCreateDigest(new DOMSource(parse(getClass()
        .getResourceAsStream("input4.canonized")))));
  }

  @Test
  public void testGetCanonicalXmlContent() throws Exception {
    {
      String input = "<a>xyz</a>";
      cut.unconditionalCreateDigest(asStream(input));
      String result = inToString(cut.getCanonicalXmlContent());
      assertEquals("<a>xyz</a>", result);
    }
    {
      String input = "<a>xyz</a>";
      cut.unconditionalCreateDigest(asSource(input));
      String result = inToString(cut.getCanonicalXmlContent());
      assertEquals("<a>xyz</a>", result);
    }
    {
      String input = "<a  >xyz</a>";
      cut.unconditionalCreateDigest(asStream(input));
      String result = inToString(cut.getCanonicalXmlContent());
      assertEquals("<a>xyz</a>", result);
    }
    {
      String input = "<a  >xyz</a>";
      cut.unconditionalCreateDigest(asSource(input));
      String result = inToString(cut.getCanonicalXmlContent());
      assertEquals("<a>xyz</a>", result);
    }
    {
      cut.unconditionalCreateDigest(asStream("<a  >xyz</a>"));
      assertEquals("<a>xyz</a>", inToString(cut.getCanonicalXmlContent()));
      assertEquals("<a>xyz</a>", inToString(cut.getCanonicalXmlContent()));
      cut.unconditionalCreateDigest(asStream("<a\n\n><b/></a>"));
      assertEquals("<a><b></b></a>", inToString(cut.getCanonicalXmlContent()));
    }
    {
      cut.unconditionalCreateDigest(asSource("<a  >xyz</a>"));
      assertEquals("<a>xyz</a>", inToString(cut.getCanonicalXmlContent()));
      assertEquals("<a>xyz</a>", inToString(cut.getCanonicalXmlContent()));
      cut.unconditionalCreateDigest(asSource("<a\n\n><b/></a>"));
      assertEquals("<a><b></b></a>", inToString(cut.getCanonicalXmlContent()));
    }
  }

  @Test(expected = RuntimeException.class)
  public void testGetCanonicalXmlContentNoInputSupplied() throws Exception {
    cut.getCanonicalXmlContent();
  }

  private InputStream asStream(String string) {
    try {
      return new ByteArrayInputStream(string.getBytes(UTF8));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("not expected", e);
    }
  }

  private Source asSource(String string) {
    try {
      return new StreamSource(new ByteArrayInputStream(string.getBytes(UTF8)));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("not expected", e);
    }
  }

  private void checkCanonizationForFile(String file)
      throws IOException, XmlParseException, UnsupportedEncodingException {

    InputStream is = getClass().getResourceAsStream(file + ".xml");
    ByteArrayOutputStream os = new ByteArrayOutputStream();

    cut.conditionalCanonicalizeXml(is, os);

    InputStream refInputStream = getClass().getResourceAsStream(file + ".canonized");

    String refString = inToString(refInputStream);
    String result = new String(os.toByteArray(), UTF8);
    assertEquals(refString, result);
  }

  private String inToString(InputStream in) throws IOException, UnsupportedEncodingException {
    BufferedInputStream ref = new BufferedInputStream(in);
    byte[] buffer = new byte[4096];
    int numBytes = ref.read(buffer);
    String refString = new String(buffer, 0, numBytes, UTF8);
    return refString;
  }

  private String canonicalize(String rawXml) throws Exception {
    ByteArrayInputStream bis = new ByteArrayInputStream(rawXml.getBytes(UTF8));
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    cut.canonicalizeXml(bis, bos);
    return new String(bos.toByteArray(), UTF8);
  }

  private static Document parse(InputStream template) throws Exception {
    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
    Document document = docBuilder.parse(template);
    return document;
  }
}
