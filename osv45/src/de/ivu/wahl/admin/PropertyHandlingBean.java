/*
 * PropertyHandlingBean
 * 
 * Copyright (c) 2002-8 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;

import org.apache.log4j.Category;

import de.ivu.ejb.EJBUtil;
import de.ivu.ejb.SessionBeanBase;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ejb.Repository;
import de.ivu.wahl.modell.ejb.RepositoryHome;

/**
 * Zugriff auf Properties in Property-Dateien und der Datenbank
 * 
 * @author cos@ivu.de, klie@ivu.de
 */
@Stateless
@Local(PropertyHandling.class)
public class PropertyHandlingBean extends SessionBeanBase implements PropertyHandling {

  private static final long serialVersionUID = -3648554800254103319L;

  static final Category LOGGER = Log4J.configure(PropertyHandlingBean.class);

  static {
    LOGGER.info(Log4J.dumpVersion(PropertyHandlingBean.class, Log4J
        .extractVersion("$Revision: 1.10 $"))); //$NON-NLS-1$
  }

  private Properties _props;

  @Resource(name = "propfileName", type = String.class)
  private String _propfileName;

  private RepositoryHome _repositoryHome;

  @PostConstruct
  public void ejbCreate() throws EJBException {
    try {
      _props = new Properties();
      // first try to load user dependent properties from $HOME/.<propfileName>
      File userPropfile = new File(System.getProperty("user.home"), ".osv" + //$NON-NLS-1$ //$NON-NLS-2$
          EJBUtil.getProgramSpecificAffix() + ".properties"); //$NON-NLS-1$
      InputStream is;
      try {
        is = new FileInputStream(userPropfile);
      } catch (FileNotFoundException e) {
        try {
          LOGGER
              .info(Messages
                  .bind(MessageKeys.Logger_BenutzerabhaengigeKonfigurationsdatei_0_nichtGefundenSucheDefaultKonfiguration,
                      userPropfile));
        } catch (Exception me) {
          // In case the application is not yet fully loaded, fallback to English
          LOGGER.info("Cannot find user specific configuration file '" + userPropfile + "'"); //$NON-NLS-1$//$NON-NLS-2$
        }
        is = getClass().getResourceAsStream(_propfileName);
      }
      if (is != null) {
        _props.load(is);
        is.close();
      } else {
        LOGGER.error(Messages
            .bind(MessageKeys.Logger_Ressource_propfileName_KannNichtGeladenWerdenPropfileName_0,
                _propfileName));
      }
    } catch (Exception ex) {
      LOGGER.error(Messages.bind(MessageKeys.Logger_KannPropertyDatei_0_NichtLesen, _propfileName),
          ex);
    }

    _repositoryHome = RepositoryHome.HomeFinder.findHome(this);
  }

  /**
   * Lesen einer bezeichneten Property
   * 
   * @return property-Wert als String
   * @param key Schl�ssel/Propertyname
   * @throws EJBException
   */
  public String getProperty(String key) throws EJBException {
    return getPropertyInternal(key);
  }

  /**
   * Setzen einer Property in der Datenbank
   * 
   * @param key Schl�sselstring
   * @param value Wert (String)
   * @throws EJBException
   */
  public void setProperty(String key, String value) throws EJBException {
    setDBProperty(key, value);
  }

  /**
   * Setzen aller Properties in der Datenbank, die mit dem angegebenen Schl�sselstring anfangen, auf
   * denselben Wert.
   * 
   * @param prefix Prefix des Schl�sselstrings
   * @param value Wert (String)
   * @throws EJBException
   */
  public void setProperties(String prefix, String value) throws EJBException {
    setDBProperties(prefix, value);
  }

  /**
   * Lesen einer bezeichneten Property als int-Wert
   * 
   * @param key Name der Property
   * @return int-Wert der Property
   * @throws EJBException
   */
  public int getIntProperty(String key) throws EJBException {
    try {
      return Integer.parseInt(getPropertyInternal(key));
    } catch (EJBException e) {
      throw e;
    } catch (Exception e) {
      throw new EJBException(Messages.bind(MessageKeys.Logger_Property_0_WurdeNichtGefunden, key),
          e);
    }
  }

  /**
   * Lesen einer bezeichneten Property als int-Wert
   * 
   * @param key Name der Property
   * @param defaultValue Vorbelegung
   * @return int-Wert der Property
   * @throws EJBException
   */
  public int getIntProperty(String key, int defaultValue) throws EJBException {
    try {
      String intStr = getPropertyInternal(key);
      if (intStr != null) {
        return Integer.parseInt(intStr);
      } else {
        return defaultValue;
      }
    } catch (EJBException e) {
      throw e;
    } catch (Exception e) {
      throw new EJBException(Messages.bind(MessageKeys.Logger_Property_0_WurdeNichtGefunden, key),
          e);
    }
  }

  /**
   * Lesen einer bezeichneten Property als int-Wert
   * 
   * @param key Name der Property
   * @return int-Wert der Property
   * @throws EJBException
   */
  public int incIntProperty(String key) throws EJBException {
    int i = getIntProperty(key);
    setIntProperty(key, i + 1);
    return i;
  }

  /**
   * Lesen einer bezeichneten Property als int-Wert mit dem anschlie�enden Hochz�hlen
   * 
   * @param key Name der Property
   * @param defaultValue Vorbelegung
   * @return int-Wert der Property vor dem Hochz�hlen
   * @throws EJBException
   */
  public int incIntProperty(String key, int defaultValue) throws EJBException {
    int i = getIntProperty(key, defaultValue);
    setIntProperty(key, i + 1);
    return i;
  }

  /**
   * Lesen einer bezeichneten Property als int-Wert mit dem anschlie�enden Herunterz�hlen
   * 
   * @param key Name der Property
   * @return int-Wert der Property vor dem Herunterz�hlen
   * @throws EJBException
   */
  public int decIntProperty(String key) throws EJBException {
    int i = getIntProperty(key);
    setIntProperty(key, i - 1);
    return i;
  }

  /**
   * Lesen einer bezeichneten Property als int-Wert mit dem anschlie�enden Herunterz�hlen
   * 
   * @param key Name der Property
   * @param defaultValue Vorbelegung
   * @return int-Wert der Property vor dem Herunterz�hlen
   * @throws EJBException
   */
  public int decIntProperty(String key, int defaultValue) throws EJBException {
    int i = getIntProperty(key, defaultValue);
    setIntProperty(key, i - 1);
    return i;
  }

  /**
   * Lesen einer bezeichneten Property als long-Wert
   * 
   * @param key Name der Property
   * @return long-Wert der Property
   * @throws EJBException
   */
  public long getLongProperty(String key) throws EJBException {
    try {
      return Long.parseLong(getPropertyInternal(key));
    } catch (EJBException e) {
      throw e;
    } catch (Exception e) {
      throw new EJBException(Messages.bind(MessageKeys.Logger_Property_0_WurdeNichtGefunden, key),
          e);
    }
  }

  /**
   * Lesen einer bezeichneten Property als long-Wert mit dem anschlie�enden Hochz�hlen
   * 
   * @param key Name der Property
   * @return long-Wert der Property vor dem Hochz�hlen
   * @throws EJBException
   */
  public long incLongProperty(String key) throws EJBException {
    long l = getLongProperty(key);
    setLongProperty(key, l + 1);
    return l;
  }

  /**
   * Lesen einer bezeichneten Property als long-Wert mit dem anschlie�enden Herunterz�hlen
   * 
   * @param key Name der Property
   * @return long-Wert der Property vor dem Herunterz�hlen
   * @throws EJBException
   */
  public long decLongProperty(String key) throws EJBException {
    long l = getLongProperty(key);
    setLongProperty(key, l - 1);
    return l;
  }

  /**
   * Lesen einer bezeichneten Property als boolean-Wert
   * 
   * @param key Name der Property
   * @return boolean-Wert der Property
   * @throws EJBException
   */
  public boolean getBooleanProperty(String key) throws EJBException {
    return Boolean.valueOf((getProperty(key)));
  }

  /**
   * Setzen einer Property als boolean-Wert
   * 
   * @param key Name der Property
   * @param value boolean-Wert
   * @throws EJBException
   */
  public void setBooleanProperty(String key, boolean value) throws EJBException {
    setProperty(key, Boolean.toString(value));
  }

  /**
   * Setzen einer Property als int-Wert
   * 
   * @param key Bezeichner
   * @param val Wert als int
   * @throws EJBException
   */
  public void setIntProperty(String key, int val) throws EJBException {
    setProperty(key, Integer.toString(val));
  }

  /**
   * Setzen einer Property als int-Wert
   * 
   * @param key Bezeichner
   * @param val Wert als long
   * @throws EJBException
   */
  public void setLongProperty(String key, long val) throws EJBException {
    setProperty(key, Long.toString(val));
  }

  /**
   * @param key Name (Schl�ssel) der Property
   * @return long-Property aus der DB (temp)
   * @throws EJBException
   */
  public long getDBLongProperty(String key) throws EJBException {
    try {
      return Long.parseLong(getDBProperty(key));
    } catch (Exception e) {
      throw new EJBException(Messages.bind(MessageKeys.Logger_RepositoryeintragNichtGefunden), e);
    }
  }

  /**
   * setze long-Property in der DB (temp)
   * 
   * @param key Name (Schl�ssel) der Property
   * @param val long-Wert
   * @throws EJBException
   */
  public void setDBLongProperty(String key, long val) throws EJBException {
    setDBProperty(key, Long.toString(val));
  }

  private String getPropertyInternal(String key) {
    String val;
    try {
      val = getDBProperty(key);
    } catch (Exception e) {
      val = System.getProperty(key);
      if (val == null) {
        val = _props.getProperty(key);
      } else {
        try {
          val = URLDecoder.decode(val, System.getProperty("file.encoding", Konstanten.ENCODING)); //$NON-NLS-1$
        } catch (UnsupportedEncodingException uee) {
          LOGGER.warn(uee.toString());
        }
      }
    }
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug(key + "=" + val); //$NON-NLS-1$
    }
    return val;
  }

  private String getDBProperty(String key) throws EJBException {
    try {
      Repository rep = _repositoryHome.findByName(key);
      return rep.getWert();
    } catch (Exception e) {
      throw new EJBException(Messages.bind(MessageKeys.Logger_RepositoryeintragNichtGefunden), e);
    }
  }

  /**
   * setze Property in der DB
   */
  private void setDBProperty(String key, String val) throws EJBException {
    Repository rep = null;
    try {
      rep = _repositoryHome.findByName(key);
    } catch (ObjectNotFoundException e) {
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Property " + key + " is not (yet) in the database"); //$NON-NLS-1$//$NON-NLS-2$
      }
    } catch (FinderException e) {
      throw new EJBException(e);
    }
    if (rep == null) {
      try {
        rep = _repositoryHome.create(getUniqueKey(), key);
      } catch (Exception e) {
        throw new EJBException(Messages.bind(MessageKeys.Logger_FehlerBeimAnlegen), e);
      }
    }
    rep.setWert(val);
  }

  /**
   * setze Properties mit einem Prefix in der DB auf einen Wert
   */
  private void setDBProperties(String prefix, String val) throws EJBException {
    try {
      Collection<Repository> rep = null;
      rep = _repositoryHome.findAllLikeName(prefix + "%"); //$NON-NLS-1$
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug(Messages.bind(MessageKeys.Logger_0_RepositoryEintraegeMitPrefix_1_Gefunden,
            rep.size(),
            prefix));
      }
      for (Repository eintrag : rep) {
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug(Messages.bind(MessageKeys.Logger_SetzeRepositoryEintrag_0_auf_1, eintrag
              .getName(), val));
        }
        eintrag.setWert(val);
      }
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Logger_FehlerBeimSetzen), e);
    }
  }

  public void removePropertiesLike(String keyInfix) throws EJBException {
    try {
      Collection<Repository> repositoryEintraege = _repositoryHome.findAllLikeName("%" + keyInfix //$NON-NLS-1$
          + "%"); //$NON-NLS-1$
      for (Repository repository : repositoryEintraege) {
        repository.remove();
      }
    } catch (FinderException e) {
      throw new EJBException(Messages.bind(MessageKeys.Logger_FehlerBeimLoeschen), e);
    } catch (RemoveException e) {
      throw new EJBException(Messages.bind(MessageKeys.Logger_FehlerBeimLoeschen), e);
    }
  }

  @Override
  public File getFileProperty(String key) {
    final String property = getProperty(key);
    return property == null ? null : new File(property).getAbsoluteFile();
  }
}
