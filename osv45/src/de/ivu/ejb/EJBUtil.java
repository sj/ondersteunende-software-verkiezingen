/*
 * EJBUtil
 * 
 * Copyright (c) 2002-2008 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.ejb;

import static java.lang.Boolean.getBoolean;
import static java.lang.System.getProperties;
import static java.lang.System.getProperty;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJBException;
import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.modell.AuthorityLevel;
import de.ivu.wahl.modell.WahlModel;

/**
 * Service code for finding EJB instances withing application. Used as a bridge between the EJB 2.1
 * and EJB 3.x world.
 * 
 * @author cos@ivu.de
 */
public class EJBUtil {
  public static final String VERSION = Log4J.extractVersion("$Revision: 1.12 $"); //$NON-NLS-1$
  private static final Category LOGGER = Log4J.configure(EJBUtil.class);
  private static final String CANNOT_FIND = "Cannot find: "; //$NON-NLS-1$
  private static volatile InitialContext __jndiContext;
  private static String __jndiPrefix;
  private static String __installationSuffix;
  private static final Map<String, Object> __homeCache = new HashMap<String, Object>();
  private static final String EJB_PROPS_NAME = "ivuejb.properties"; //$NON-NLS-1$

  static {
    LOGGER.info(Log4J.dumpVersion(EJBUtil.class, VERSION));
  }

  public static void resetContext() {
    __jndiContext = null;
  }

  /**
   * Configures and returns initial naming context
   * 
   * @return initial naming context
   * @throws EJBException on any exception
   */
  public static InitialContext getInitialContext() throws EJBException {
    if (__jndiContext == null) {
      try {
        if (!getBoolean("de.ivu.ejb.keepNaming")) { //$NON-NLS-1$
          getProperties().putAll(getProps(EJB_PROPS_NAME));
        }
      } catch (EJBException e) {
        throw e; // escape the next catch clause
      } catch (Exception e) {
        LOGGER.info("Can't read '" + EJB_PROPS_NAME + "'"); //$NON-NLS-1$ //$NON-NLS-2$
        if (getProperty("java.naming.factory.initial") == null) { //$NON-NLS-1$
          throw new EJBException("Exception while getting initial context.", e); //$NON-NLS-1$
        }
      }
      try {
        __jndiContext = new InitialContext();
      } catch (Exception e) {
        LOGGER.error("Exception while getting initial context.", e); //$NON-NLS-1$
        throw new EJBException("Exception while getting initial context.", e); //$NON-NLS-1$
      }
    }
    return __jndiContext;
  }

  private static Properties getProps(String propsName) throws IOException {
    Properties ejbProps = new Properties();
    ejbProps.load(EJBUtil.class.getClassLoader().getResourceAsStream(propsName));
    return ejbProps;
  }

  /**
   * @return "P4_PSB", "P4_HSB", "P4_CSB" or "P5"
   */
  public static String getProgramSpecificAffix() {
    if (__jndiPrefix == null) {
      readProperties();
    }
    return __jndiPrefix;
  }

  /**
   * @return "P4_PSB", "P4_HSB", "P4_CSB" or "P5" + installation suffix
   */
  public static String getInstallationSpecificAffix() {
    return getProgramSpecificAffix() + getInstallationSuffix();
  }

  public static String getInstallationSuffix() {
    if (__jndiPrefix == null) {
      readProperties();
    }
    return __installationSuffix;
  }

  /**
   * Initialization of both __jndiPrefix and __installationSuffix from the was.properties file
   */
  private static void readProperties() {
    final String wasPropsName = "was.properties"; //$NON-NLS-1$
    try {
      Properties wasProps = getProps(wasPropsName);
      int electionMode = Integer.parseInt(wasProps.getProperty(Konstanten.PROP_ELECTION_MODE));
      int electionLevel = Integer.parseInt(wasProps.getProperty(Konstanten.PROP_ELECTION_LEVEL));

      String installationSuffix = wasProps.getProperty(Konstanten.PROP_INSTALLATION_SUFFIX);
      __installationSuffix = installationSuffix == null ? StringUtils.EMPTY : installationSuffix;
      String electionModeKlartext = WahlModel.WAHLMODUS_KLARTEXT.get(electionMode);

      if (electionMode == 1) {
        __jndiPrefix = electionModeKlartext;
      } else {
        __jndiPrefix = electionModeKlartext
            + "_" + AuthorityLevel.byId(electionLevel).getShortName(); //$NON-NLS-1$
      }
    } catch (Exception e) {
      throw new EJBException("Can't read '" + wasPropsName + "'", e); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  @SuppressWarnings("unchecked")
  public static <T extends EJBHome> T findHome(String ejbName) throws EJBException {
    final Object home = findHomeGeneric(ejbName, true);
    try {
      return (T) home;
    } catch (ClassCastException e) {
      StringBuilder implement = new StringBuilder("Implemented:\n"); //$NON-NLS-1$
      Class<?> homeClass = home.getClass();
      Class<?>[] interfaces = homeClass.getInterfaces();
      for (Class<?> iface : interfaces) {
        implement.append(iface.getName()).append('\n');
      }
      LOGGER.error(implement);
      throw e;
    }
  }

  @SuppressWarnings("unchecked")
  public static <T extends EJBHome> T findHomeNoCache(String ejbName) throws EJBException {
    return (T) findHomeGeneric(ejbName, false);
  }

  @SuppressWarnings("unchecked")
  public static <T extends EJBLocalHome> T findLocalHomeNoCache(String ejbName) throws EJBException {
    return (T) findHomeGeneric(ejbName, false);
  }

  @SuppressWarnings("unchecked")
  private static <T extends Object> T findHomeGeneric(String ejbName, boolean useCache)
      throws EJBException {

    try {
      Object homeIF = null;
      if (useCache) {
        homeIF = __homeCache.get(ejbName);
      }
      if (homeIF == null) {
        homeIF = getInitialContext().lookup(getInstallationSpecificAffix() + "/" + ejbName); //$NON-NLS-1$
        __homeCache.put(ejbName, homeIF);
      }
      return (T) homeIF;
    } catch (NameNotFoundException e) {
      try {
        throw new EJBException(CANNOT_FIND + ejbName + " : " + getScope("/"), e); //$NON-NLS-1$ //$NON-NLS-2$
      } catch (Exception e2) {
        throw new EJBException(CANNOT_FIND + ejbName + " : " + e, e2); //$NON-NLS-1$
      }
    } catch (Exception e) {
      throw new EJBException(CANNOT_FIND + ejbName + " : " + e, e); //$NON-NLS-1$
    }
  }

  public static String getScope(String root) throws NamingException {
    StringBuilder seeing = new StringBuilder("See:\n"); //$NON-NLS-1$
    NamingEnumeration<Binding> list = getInitialContext().listBindings(root);
    descend(seeing, list, 0);
    return seeing.toString();
  }

  private static void descend(StringBuilder seeing, final NamingEnumeration<Binding> list, int depth)
      throws NamingException {

    while (list.hasMoreElements()) {
      Binding binding = list.nextElement();
      final String name = binding.getName();
      for (int i = 0; i < depth; i++) {
        seeing.append(' ');
      }
      seeing.append(name);

      final Object object = binding.getObject();
      if (!(object instanceof Context)) {
        seeing.append("->"); //$NON-NLS-1$
        final Class<?> clazz = object.getClass();
        if (object instanceof Proxy) {
          Class<?>[] interfaces = clazz.getInterfaces();
          for (Class<?> iface : interfaces) {
            if (!Proxy.class.isAssignableFrom(iface)) {
              seeing.append(iface.getName()).append(' ');
            }
          }
        } else if (object instanceof Reference) {
          Enumeration<RefAddr> all = ((Reference) object).getAll();
          while (all.hasMoreElements()) {
            RefAddr refAddr = all.nextElement();
            seeing.append(refAddr.getType()).append(':').append(refAddr.getContent()).append(' ');
          }
        } else {
          seeing.append(clazz.getName());
        }
      }
      seeing.append('\n');

      if (object instanceof Context) {
        descend(seeing, ((Context) object).listBindings(""), depth + 1); //$NON-NLS-1$
      }
    }
  }

  @SuppressWarnings("unchecked")
  public static <T extends Object> T lookupLocal(String beanName) throws EJBException {
    try {
      final String what = "osv" + getInstallationSpecificAffix() + "/" + beanName + "/local"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      return (T) getInitialContext().lookup(what);
    } catch (NamingException e) {
      try {
        throw new EJBException(CANNOT_FIND + beanName + " : " + getScope("/"), e); //$NON-NLS-1$ //$NON-NLS-2$
      } catch (Exception e2) {
        throw new EJBException(CANNOT_FIND + beanName + " : " + e, e2); //$NON-NLS-1$
      }
    }
  }

  /**
   * @return JVM-wide well-defined key (even after various starts)
   */
  public static String getUniqueKey() {
    return new UID().toString();
  }

  public static Object getEnv(String key) throws EJBException {
    try {
      return getInitialContext().lookup("java:comp/env/" + key); //$NON-NLS-1$
    } catch (Exception e) {
      throw new EJBException("Cannot find environment key " + key + ".", e); //$NON-NLS-1$//$NON-NLS-2$
    }
  }
}