/*
 * FopProvider
 * 
 * Created on 28.01.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

public class FopProvider {
  private static final UniversalURIResolver URI_RESOLVER = new UniversalURIResolver();
  private static final String FOP_CONFIG_FILE = "/de/ivu/wahl/wus/reportgen/fopconfig/fop.xconf"; //$NON-NLS-1$

  public Fop provideFop(OutputStream out) {
    try {
      // configure fopFactory as desired
      FopFactory fopFactory = FopFactory.newInstance();
      URL resourceUrl = getClass().getResource(FOP_CONFIG_FILE);
      URL baseUrl = new URL(resourceUrl, "."); //$NON-NLS-1$
      URL fixedURL = fixMalformedURL(baseUrl);

      fopFactory.getFontManager().setFontBaseURL(fixedURL.toExternalForm());
      fopFactory.setURIResolver(URI_RESOLVER);
      DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
      InputStream fopConfigFileStream = getClass().getResourceAsStream(FOP_CONFIG_FILE);
      Configuration cfg = cfgBuilder.build(fopConfigFileStream);
      fopFactory.setUserConfig(cfg);

      FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
      // configure foUserAgent as desired

      // Construct fop with desired output format
      Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

      return fop;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private URL fixMalformedURL(URL url) throws MalformedURLException {
    if ("bundleresource".equalsIgnoreCase(url.getProtocol())) {
      return url;
    }
    File file = null;
    try {
      file = new File(url.toURI());
    } catch (URISyntaxException e) {
      file = new File(url.getPath());
    }

    return file.toURI().toURL();
  }

  private static class UniversalURIResolver implements URIResolver {
    public Source resolve(String href, String base) throws TransformerException {
      try {
        URL url = new URL(base + href);
        return new StreamSource(url.openStream(), url.toExternalForm());
      } catch (Exception e) {
        throw new TransformerException(e);
      }
    }
  }
}
