/*
 * XsltTransformer
 * 
 * Created on 28.01.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.io.File;
import java.util.Collections;
import java.util.Map;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

public class XsltTransformer {
  private static final Logger log = Logger.getLogger(XsltTransformer.class);

  /**
   * @param params Parameters for the transformer. May be <code>null</code>.
   */
  public void processXSLT(Source src,
      Result result,
      StreamSource stylesheet,
      Map<String, String> params,
      ReportLanguage language,
      File exportSelectionPath) {
    if (params == null) {
      params = Collections.emptyMap();
    }
    try {
      // 1. Instantiate a TransformerFactory.
      TransformerFactory tFactory = TransformerFactory.newInstance();

      // URI Resolver for resolving imported XSLTs
      tFactory.setURIResolver(new XSLTImportURIResolver(language, exportSelectionPath));

      // 2. Use the TransformerFactory to process the stylesheet Source
      // and generate a Transformer.
      Transformer transformer = tFactory.newTransformer(stylesheet);

      // set transformer parameters
      for (Map.Entry<String, String> entry : params.entrySet()) {
        transformer.setParameter(entry.getKey(), entry.getValue());
      }

      // 3. Use the Transformer to transform an XML Source and send the
      // output to a Result object.
      transformer.transform(src, result);
    } catch (Exception e) {
      e.printStackTrace();
      log.error("Exception in XSLT transformation", e); //$NON-NLS-1$
      throw new RuntimeException(e);
    }
  }
}
