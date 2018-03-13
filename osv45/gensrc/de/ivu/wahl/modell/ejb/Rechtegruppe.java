/*
 * Rechtegruppe
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import de.ivu.wahl.modell.RechtegruppeModel;

/**
  * Interface for the entity Rechtegruppe as Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface Rechtegruppe extends EJBLocalObject, RechtegruppeModel {
   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details model object with new state
     */
   void setDetails(RechtegruppeModel details);

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   RechtegruppeModel getDetails();


   /**
     * Returns the set of entities of the type {@link Anwender}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Anwender}
     * @throws EJBException: an error occurred
     */
   Collection<Anwender> getAnwenderCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Anwender}.
     *
     * @param anwender Anwender-object
     */
   void addAnwender(Anwender anwender);

   /**
     * Adds the object to the set of entities of the type {@link Anwender}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllAnwenderCol(Collection<Anwender> col);

   /**
     * Adds the object, which is marked by the ID, to the set of entities of the type {@link Anwender}.
     *
     * @param id_Anwender ID of Anwender entity
     * @throws EJBException: an error occurred
     */
   void addID_Anwender(String id_Anwender) throws EJBException;

   /**
     * Deletes an entity from the set of entities of the type {@link Anwender}.
     *
     * @param anwender Anwender-EJBObject, which is removed from the set.
     */
   void removeAnwender(Anwender anwender);

   /**
     * Deletes an entity from the set of entities of the type {@link Anwender}.
     *
     * @param id_Anwender ID of the Anwender entity to be deleted
     * @throws EJBException: an error occurred
     */
   void removeID_Anwender(String id_Anwender) throws EJBException;

   /**
     * Returns the set of entities of the type {@link Recht}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Recht}
     * @throws EJBException: an error occurred
     */
   Collection<Recht> getRechtCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Recht}.
     *
     * @param recht Recht-object
     */
   void addRecht(Recht recht);

   /**
     * Adds the object to the set of entities of the type {@link Recht}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllRechtCol(Collection<Recht> col);

   /**
     * Adds the object, which is marked by the ID, to the set of entities of the type {@link Recht}.
     *
     * @param id_Recht ID of Recht entity
     * @throws EJBException: an error occurred
     */
   void addID_Recht(String id_Recht) throws EJBException;

   /**
     * Deletes an entity from the set of entities of the type {@link Recht}.
     *
     * @param recht Recht-EJBObject, which is removed from the set.
     */
   void removeRecht(Recht recht);

   /**
     * Deletes an entity from the set of entities of the type {@link Recht}.
     *
     * @param id_Recht ID of the Recht entity to be deleted
     * @throws EJBException: an error occurred
     */
   void removeID_Recht(String id_Recht) throws EJBException;
}
