/*
 * SessionBeanBase
 * 
 * Created on 08.10.2003 
 * Copyright (c) 2003-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.ejb;

import static de.ivu.ejb.EJBUtil.findLocalHomeNoCache;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.SessionContext;

import org.apache.log4j.Category;

import de.ivu.ejb.bmp.Model;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;

/**
 * Base class for a Session Enterprise Java Bean
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public abstract class SessionBeanBase extends IVUBeanBase {
  private static final long serialVersionUID = -5590534380467919179L;

  private static final Object[] EMPTY_PARAMETERS = new Object[0];
  private static final Class<?>[] EMPTY_SIGNATURE = new Class<?>[0];
  static final Category LOGGER = Log4J.configure(SessionBeanBase.class);

  static {
    LOGGER.info(Log4J.dumpVersion(SessionBeanBase.class, Log4J.extractVersion("$Revision: 1.7 $"))); //$NON-NLS-1$
  }

  @Resource
  protected SessionContext _ctx;

  public void setSessionContext(SessionContext context) throws EJBException {
    _ctx = context;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends EJBLocalHome> T findLocalHome(String ejbName) throws EJBException {
    return (T) findLocalHomeNoCache(ejbName);
  }

  @SuppressWarnings("unchecked")
  protected <M extends Model> List<M> toModelList(Collection<?> entityEJBCollection)
      throws EJBException {
    try {
      List<M> modelCollection = new ArrayList<M>();
      Method getDetails = null;
      for (Object ejb : entityEJBCollection) {
        if (getDetails == null) {
          getDetails = ejb.getClass().getMethod("getDetails", EMPTY_SIGNATURE); //$NON-NLS-1$
        }
        M details = (M) invoke(ejb, getDetails, EMPTY_PARAMETERS);
        modelCollection.add(details);
      }
      return modelCollection;
    } catch (NoSuchMethodException e) {
      throw new EJBException(Messages.getString(MessageKeys.Logger_MethodeGetDetailsNichtGefunden),
          e);
    } catch (IllegalAccessException e) {
      throw new EJBException(Messages
          .getString(MessageKeys.Logger_DarfMethodeGetDetailsNichtAufrufen), e);
    } catch (InvocationTargetException e) {
      Throwable cause = e.getCause();
      if (cause instanceof Exception) {
        throw new EJBException(Messages
            .getString(MessageKeys.Logger_KonnteMethodeGetDetailsNichtAusfuehren),
            (Exception) cause);
      } else {
        throw new EJBException(Messages
            .getString(MessageKeys.Logger_KonnteMethodeGetDetailsNichtAusfuehren)
            + ": " + cause); //$NON-NLS-1$
      }
    }
  }

  @SuppressWarnings("unchecked")
  private <T> T invoke(Object instance, Method method, Object[] params)
      throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

    return (T) method.invoke(instance, params);
  }
}
