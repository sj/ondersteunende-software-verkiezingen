/*
 * JMXInvokerBean
 * 
 * Created on 18.01.2005
 * Copyright (c) 2005-8 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.ejb.jboss;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
@Stateless
@Local(JMXInvoker.class)
@TransactionAttribute(SUPPORTS)
public class JMXInvokerBean implements JMXInvoker, Serializable {
  private static final long serialVersionUID = 6931159589005220622L;
  private static final Class<JMXInvokerBean> MY_CLASS = JMXInvokerBean.class;
  private static final Category LOGGER = Log4J.configure(MY_CLASS);

  static {
    LOGGER.info(Log4J.dumpVersion(MY_CLASS, Log4J.extractVersion("$Revision: 1.4 $"))); //$NON-NLS-1$
  }

  @Resource(mappedName = "jmx/invoker/RMIAdaptor")
  private MBeanServerConnection _server;

  public Object get(String objectName, String attributeName) throws EJBException {
    try {
      return _server.getAttribute(new ObjectName(objectName), attributeName);
    } catch (Exception e) {
      throw new EJBException(e.toString(), e);
    }
  }

  public void set(String objectName, String attributeName, Object value) throws EJBException {
    try {
      _server.setAttribute(new ObjectName(objectName), new Attribute(attributeName, value));
    } catch (Exception e) {
      throw new EJBException(e.toString(), e);
    }
  }

  public Object invoke(String objectName, String actionName, Object[] parameters)
      throws EJBException {

    List<String> sigList = new ArrayList<String>();
    for (Object parameter : parameters) {
      sigList.add(parameter.getClass().getName());
    }
    return invoke(objectName, actionName, parameters, sigList.toArray(new String[sigList.size()]));
  }

  public Object invoke(String objectName, String actionName, Object[] parameters, String[] sig)
      throws EJBException {

    try {
      return _server.invoke(new ObjectName(objectName), actionName, parameters, sig);
    } catch (Exception e) {
      throw new EJBException(e.toString(), e);
    }
  }
}
