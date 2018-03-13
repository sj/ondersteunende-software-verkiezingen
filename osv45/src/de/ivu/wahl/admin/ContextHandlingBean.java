/*
 * ContextHandlingBean
 * 
 * Created on Feb 26, 2008
 * Copyright (c) 2008 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.admin;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import org.apache.catalina.connector.Connector;
import org.apache.log4j.Logger;

import de.ivu.ejb.jboss.JMXInvoker;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
@Stateless
@Local(ContextHandling.class)
public class ContextHandlingBean implements ContextHandling, Serializable {
  private static final long serialVersionUID = 5093653084461684050L;

  private static final Logger LOGGER = Logger.getLogger(ContextHandlingBean.class);
  private static final String EXCEPTION_MSG_NO_SERVER = Messages
      .getString(MessageKeys.Msg_KonnteDieAdresseFuerDenInternenZugriffAufDenEingebettetenHTTPServerNichtErmitteln);
  private static final Object[] EMPTY_OBJECT_ARRAY = new Object[]{};

  static {
    LOGGER.info(Log4J.dumpVersion(ContextHandlingBean.class, Log4J
        .extractVersion("$Revision: 1.4 $"))); //$NON-NLS-1$
  }

  @EJB
  private JMXInvoker _jmxInvoker;

  @TransactionAttribute(SUPPORTS)
  public URI getBaseURI() {
    try {
      Connector httpConnector = findHttpConnector();
      URI exportBaseURI = new URI(httpConnector.getScheme(), null, "localhost", //$NON-NLS-1$
          httpConnector.getPort(), null, null, null);
      LOGGER.info("exportBaseURL=" + exportBaseURI); //$NON-NLS-1$
      return exportBaseURI;
    } catch (URISyntaxException e) {
      throw new EJBException(EXCEPTION_MSG_NO_SERVER, e);
    }
  }

  /**
   * Vorsicht: dieser Code ist JBoss/Catalina(Tomcat)-spezifisch
   * 
   * @return HTTP-Connector MBean Proxy
   */
  private Connector findHttpConnector() {
    Connector[] connectors = (Connector[]) _jmxInvoker
        .invoke("jboss.web:serviceName=jboss.web,type=Service", //$NON-NLS-1$
            "findConnectors", //$NON-NLS-1$
            EMPTY_OBJECT_ARRAY);
    for (Connector connector : connectors) {
      String scheme = connector.getScheme();
      if ("http".equals(scheme) && connector.getProtocol().startsWith("HTTP")) { //$NON-NLS-1$//$NON-NLS-2$
        return connector;
      }
    }
    return null;
  }
}
