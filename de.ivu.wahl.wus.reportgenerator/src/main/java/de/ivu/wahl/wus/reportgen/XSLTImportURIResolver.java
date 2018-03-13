/*
 * Created on 04.02.2011
 * Copyright (c) 2011-2017 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import static de.ivu.wahl.wus.reportgen.ReportGeneratorImpl.SHORT_TEXT_PATH;
import static de.ivu.wahl.wus.reportgen.ReportGeneratorImpl.TEXT_PATH;

import java.io.File;
import java.io.InputStream;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

class XSLTImportURIResolver implements URIResolver {
  private static final String ABSOLUTE_TEXT_PATH_FRAGMENT = "\\de.ivu.wahl.wus.reportgenerator\\src\\main\\resources\\de\\ivu\\wahl\\wus\\reportgen\\text\\nl\\"; //$NON-NLS-1$
  private static final String RELATIVE_TEXT_PATH_FRAGMENT = "\\..\\..\\..\\..\\..\\..\\main\\resources\\de\\ivu\\wahl\\wus\\reportgen\\text\\nl\\"; //$NON-NLS-1$
  private static final String ABSOLUTE_TEXT_LOCAL_TEMPLATE_PATH = "D:/projekte/de.ivu.wahl.wus.reportgenerator/src/main/resources/de/ivu/wahl/wus/reportgen/text/nl/"; //$NON-NLS-1$

  private final ReportLanguage language;

  XSLTImportURIResolver(ReportLanguage language, File exportSelectionPath) {
    this.language = language;
  }

  public Source resolve(String href, String base) throws TransformerException {
    String mainPart = getMainPart(href);
    InputStream xslStream = getXslStream(href, mainPart);
    return new StreamSource(xslStream);
  }

  private String getMainPart(String href) {
    if (href.contains(ABSOLUTE_TEXT_PATH_FRAGMENT) || href.contains(RELATIVE_TEXT_PATH_FRAGMENT)) {
      return href.substring(1 + href.lastIndexOf("\\")); //$NON-NLS-1$
    } else if (href.contains(ABSOLUTE_TEXT_LOCAL_TEMPLATE_PATH)) {
      return href.substring(1 + href.lastIndexOf("/")); //$NON-NLS-1$
    } else {
      return href;
    }
  }

  /**
   * Try to find the import file in different places
   */
  private InputStream getXslStream(String href, String mainPart) {
    InputStream xslStream = null;

    // 1. and 2. rgtext folder is void
    // 3. in a relative path where the texts usually lay
    xslStream = getXslStreamIfNull(xslStream, getLanguagePath() + mainPart);
    // 4. in the (literal) path from the import statement
    xslStream = getXslStreamIfNull(xslStream, href);
    // 5. in a relative path "./text/"
    xslStream = getXslStreamIfNull(xslStream, getShortLanguagePath() + mainPart);

    return xslStream;
  }

  private InputStream getXslStreamIfNull(InputStream xslStream, String name) {
    if (xslStream == null) {
      return ReportGeneratorImpl.class.getResourceAsStream(name);
    } else {
      return xslStream;
    }
  }

  private String getLanguagePath() {
    return TEXT_PATH + language.getAbbreviation() + "/"; //$NON-NLS-1$
  }

  private String getShortLanguagePath() {
    return SHORT_TEXT_PATH + language.getAbbreviation() + "/"; //$NON-NLS-1$
  }

}