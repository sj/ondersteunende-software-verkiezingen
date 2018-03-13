/*
 * Recht
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import de.ivu.wahl.modell.RechtModel;

/**
  * Interface for the entity Recht as Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface Recht extends EJBLocalObject, RechtModel {
   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details model object with new state
     */
   void setDetails(RechtModel details);

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   RechtModel getDetails();


   /**
     * Returns the set of entities of the type {@link Rechtegruppe}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Rechtegruppe}
     * @throws EJBException: an error occurred
     */
   Collection<Rechtegruppe> getRechtegruppeCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Rechtegruppe}.
     *
     * @param rechtegruppe Rechtegruppe-object
     */
   void addRechtegruppe(Rechtegruppe rechtegruppe);

   /**
     * Adds the object to the set of entities of the type {@link Rechtegruppe}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllRechtegruppeCol(Collection<Rechtegruppe> col);

   /**
     * Adds the object, which is marked by the ID, to the set of entities of the type {@link Rechtegruppe}.
     *
     * @param id_Rechtegruppe ID of Rechtegruppe entity
     * @throws EJBException: an error occurred
     */
   void addID_Rechtegruppe(String id_Rechtegruppe) throws EJBException;

   /**
     * Deletes an entity from the set of entities of the type {@link Rechtegruppe}.
     *
     * @param rechtegruppe Rechtegruppe-EJBObject, which is removed from the set.
     */
   void removeRechtegruppe(Rechtegruppe rechtegruppe);

   /**
     * Deletes an entity from the set of entities of the type {@link Rechtegruppe}.
     *
     * @param id_Rechtegruppe ID of the Rechtegruppe entity to be deleted
     * @throws EJBException: an error occurred
     */
   void removeID_Rechtegruppe(String id_Rechtegruppe) throws EJBException;
}
