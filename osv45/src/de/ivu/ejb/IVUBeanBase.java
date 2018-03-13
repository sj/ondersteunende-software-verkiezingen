/*
 * IVUBeanBase
 * 
 * Created on 08.10.2003 Copyright (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.ejb;

import static de.ivu.ejb.EJBUtil.findHomeNoCache;
import static de.ivu.ejb.EJBUtil.findLocalHomeNoCache;

import java.text.DateFormat;
import java.util.Date;

import javax.ejb.EJBException;
import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.ejb.EnterpriseBean;
import javax.naming.InitialContext;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.wahl.AnwContext;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;

/**
 * Base class for an Enterprise Java Bean
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public abstract class IVUBeanBase implements EnterpriseBean {
  private static final long serialVersionUID = -753173590929345271L;
  public static final String VERSION = Log4J.extractVersion("$Revision: 1.8 $"); //$NON-NLS-1$
  private static final Category APP_LOGGER = Log4J.configure(Konstanten.APPLOG);
  private static final Category LOGGER = Log4J.configure(IVUBeanBase.class);
  private static volatile InitialContext __jndiContext = null;

  static {
    LOGGER.info(Log4J.dumpVersion(IVUBeanBase.class, VERSION));
  }

  public static void resetContext() {
    __jndiContext = null;
  }

  public static InitialContext getInitialContext() throws EJBException {
    if (__jndiContext == null) {
      try {
        __jndiContext = new InitialContext();
      } catch (Exception e) {
        throw new EJBException("Exception while getting initial context.", e); //$NON-NLS-1$
      }
    }
    return __jndiContext;
  }

  @SuppressWarnings("unchecked")
  public <T extends EJBLocalHome> T findLocalHome(String ejbName) throws EJBException {
    try {
      return (T) findHomeGeneric(ejbName);
    } catch (EJBException e) {
      if (LOGGER.isInfoEnabled()) {
        logHint(ejbName, "local-home", "local", "ejb-local-ref"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
      LOGGER.warn(Messages
          .bind(MessageKeys.LOGGER_Konnte_0_InternNichtFinden_1_VersucheEsUeberJNDI, ejbName, e
              .getCausedByException()));
      return (T) findLocalHomeNoCache(ejbName);
    }
  }

  private void logHint(String ejbName, String homeTag, String ifTag, String refKind) {
    LOGGER.info(Messages.bind(MessageKeys.LOGGER_Konnte_0_NichtFinden, ejbName));
    if (ejbName.length() != ejbName.trim().length()) {
      LOGGER.warn(Messages.bind(MessageKeys.LOGGER_SieHabenLeerzeichenImNamen_0, ejbName));
    } else {
      String className = getClass().getName();
      String myEjbName = className.substring(className.lastIndexOf('.') + 1, className
          .lastIndexOf("Bean")); //$NON-NLS-1$
      boolean iAmEntity = isEntity(myEjbName);
      boolean isEntity = isEntity(ejbName);
      String linkName;
      if (isEntity) {
        int rmtIdx = ejbName.indexOf("Remote"); //$NON-NLS-1$
        linkName = rmtIdx > 0 ? ejbName.substring(0, rmtIdx) : ejbName;
      } else {
        int locIdx = ejbName.indexOf("Local"); //$NON-NLS-1$
        linkName = locIdx > 0 ? ejbName.substring(0, locIdx) : ejbName;
      }
      StringBuilder eintrag = new StringBuilder();
      if (iAmEntity) {
        eintrag.append("      <entity>\n"); //$NON-NLS-1$
      } else {
        eintrag.append("      <session>\n"); //$NON-NLS-1$
      }
      eintrag.append("         <ejb-name>" + myEjbName + "</ejb-name>\n"); //$NON-NLS-1$ //$NON-NLS-2$
      eintrag.append("...\n"); //$NON-NLS-1$
      eintrag.append("         <" + refKind + ">\n"); //$NON-NLS-1$ //$NON-NLS-2$
      eintrag.append("            <ejb-ref-name>ejb/" + ejbName + "</ejb-ref-name>\n"); //$NON-NLS-1$ //$NON-NLS-2$
      eintrag.append("            <ejb-ref-type>" + (isEntity ? "Entity" : "Session") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + "</ejb-ref-type>\n"); //$NON-NLS-1$
      String pkgEjbName = (isEntity ? "modell.ejb" : "[Directory]") + "." + ejbName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      eintrag.append("            <" + homeTag + ">de.ivu.wahl." + pkgEjbName + "Home</" + homeTag //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ">\n"); //$NON-NLS-1$
      eintrag.append("            <" + ifTag + ">de.ivu.wahl." + pkgEjbName + "</" + ifTag + ">\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
      eintrag.append("            <ejb-link>" + linkName + "</ejb-link>\n"); //$NON-NLS-1$ //$NON-NLS-2$
      eintrag.append("         </" + refKind + ">\n"); //$NON-NLS-1$ //$NON-NLS-2$
      eintrag.append("...\n"); //$NON-NLS-1$
      if (iAmEntity) {
        eintrag.append("      </entity>\n"); //$NON-NLS-1$
      } else {
        eintrag.append("      </session>\n"); //$NON-NLS-1$
      }
      LOGGER.info(Messages
          .bind(MessageKeys.LOGGER_BitteUeberpruefenObFuer_0_InEJBJAR_XMLFolgendesEingetragenIst_1,
              myEjbName,
              eintrag));

      try {
        LOGGER.info(EJBUtil.getScope("java:comp/env")); //$NON-NLS-1$
      } catch (Exception e) {
        LOGGER
            .debug("Cannot determine scope of the component in order to provide further diagnostic data"); //$NON-NLS-1$
      }
    }
  }

  /**
   * @param name The name to check
   * @return <code>true</code> if the given name equals the name convention for Entity Beans
   */
  private boolean isEntity(String name) {
    return name.indexOf("Handling") < 0 && name.indexOf("Invoker") < 0; //$NON-NLS-1$ //$NON-NLS-2$
  }

  @SuppressWarnings("unchecked")
  public static final <T extends EJBHome> T findHomeExt(String ejbName) throws EJBException {
    return (T) findHomeNoCache(ejbName);
  }

  @SuppressWarnings("unchecked")
  public static final <T extends EJBLocalHome> T findLocalHomeExt(String ejbName)
      throws EJBException {

    return (T) findLocalHomeNoCache(ejbName);
  }

  @SuppressWarnings("unchecked")
  private static <T extends Object> T findHomeGeneric(String ejbName) throws EJBException {
    try {
      return (T) getInitialContext().lookup("java:comp/env/ejb/" + ejbName); //$NON-NLS-1$
    } catch (Exception e) {
      throw new EJBException("Cannot find: " + ejbName + ": " + e, e); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  /**
   * @return The uniqueKey value
   */
  public static String getUniqueKey() {
    return new UID().toString();
  }

  protected final Object getEnv(String key) throws EJBException {
    try {
      return getInitialContext().lookup("java:comp/env/" + key); //$NON-NLS-1$
    } catch (Exception e) {
      throw new EJBException("Cannot find environment " + key + ".", e); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  /**
   * Bean supporting method by EJB standard
   * 
   * @throws EJBException not used here, but allows well derivation
   */
  public void ejbActivate() throws EJBException {
    // no initial functionality, sub classes can implement some, if needs
  }

  /**
   * Bean supporting method by EJB standard
   * 
   * @throws EJBException not used here, but allows well derivation
   */
  public void ejbPassivate() throws EJBException {
    // no initial functionality, sub classes can implement some, if needs
  }

  /**
   * Write in the ApplicationLog
   * 
   * @param c User context
   * @param message Message for the log
   */
  protected void writeAppLog(AnwContext c, String message) {
    String anwenderID = c.getID_Anwender();
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
    String dateString = df.format(new Date());
    String anwenderString = null;
    if (Konstanten.SYSTEM_ANWENDERID.equals(anwenderID)) {
      anwenderString = "System"; //$NON-NLS-1$
    } else {
      /**
       * perhaps find out anwenderName (user name)
       */
      anwenderString = anwenderID;
    }
    APP_LOGGER.info(dateString + ":[" + anwenderString + "]" + message); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * Convenience comparing method, which bypasses the problem of the <code>null</code> - objects
   * 
   * @param a first object to compare
   * @param b second object to compare
   * @return <code>true</code> if the objects do not equal each other
   */
  public static boolean equals(Object a, Object b) {
    return a == null ? b == null : a.equals(b);
  }
}