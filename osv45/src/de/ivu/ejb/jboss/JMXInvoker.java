/*
 * JMXInvoker
 * 
 * Created on 04.08.2005
 * Copyright (c) 2005-8 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.ejb.jboss;

import javax.ejb.EJBException;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public interface JMXInvoker {
  Object get(String objectName, String attributeName) throws EJBException;

  void set(String objectName, String attributeName, Object value) throws EJBException;

  Object invoke(String objectName, String actionName, Object[] parameters) throws EJBException;

  Object invoke(String objectName, String actionName, Object[] parameters, String[] sig)
      throws EJBException;
}
