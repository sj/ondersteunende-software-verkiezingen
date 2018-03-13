/*
 * DHondtQuotient
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;


import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import de.ivu.wahl.modell.DHondtQuotientModel;

/**
  * Interface for the entity DHondtQuotient as Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface DHondtQuotient extends EJBLocalObject, DHondtQuotientModel {
   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details model object with new state
     */
   void setDetails(DHondtQuotientModel details);

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   DHondtQuotientModel getDetails();


   /**
     * Navigation to the associated entity of the type {@link Restsitzverteilung}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Restsitzverteilung getRestsitzverteilung() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Restsitzverteilung}
     *
     * @param restsitzverteilung the corresponding EJBObject
     */
   void setRestsitzverteilung(Restsitzverteilung restsitzverteilung);
}
