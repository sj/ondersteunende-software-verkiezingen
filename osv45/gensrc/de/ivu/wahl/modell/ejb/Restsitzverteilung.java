/*
 * Restsitzverteilung
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import de.ivu.wahl.modell.RestsitzverteilungModel;

/**
  * Interface for the entity Restsitzverteilung as Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface Restsitzverteilung extends EJBLocalObject, RestsitzverteilungModel {
   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details model object with new state
     */
   void setDetails(RestsitzverteilungModel details);

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   RestsitzverteilungModel getDetails();


   /**
     * Navigation to the associated entity of the type {@link Listenkombination}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Listenkombination getListenkombination() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Listenkombination}
     *
     * @param listenkombination the corresponding EJBObject
     */
   void setListenkombination(Listenkombination listenkombination);

   /**
     * Navigation to the associated entity of the type {@link Ergebniseingang}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Ergebniseingang getErgebniseingang() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Ergebniseingang}
     *
     * @param ergebniseingang the corresponding EJBObject
     */
   void setErgebniseingang(Ergebniseingang ergebniseingang);

   /**
     * Navigation to the associated entity of the type {@link Liste}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Liste getListe() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Liste}
     *
     * @param liste the corresponding EJBObject
     */
   void setListe(Liste liste);

   /**
     * Navigation to the associated entity of the type {@link Gruppe}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Gruppe getGruppe() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Gruppe}
     *
     * @param gruppe the corresponding EJBObject
     */
   void setGruppe(Gruppe gruppe);

   /**
     * Returns the set of entities of the type {@link DHondtQuotient}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link DHondtQuotient}
     * @throws EJBException: an error occurred
     */
   Collection<DHondtQuotient> getDHondtQuotientCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link DHondtQuotient}.
     *
     * @param dHondtQuotient DHondtQuotient-object
     */
   void addDHondtQuotient(DHondtQuotient dHondtQuotient);

   /**
     * Adds the object to the set of entities of the type {@link DHondtQuotient}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllDHondtQuotientCol(Collection<DHondtQuotient> col);

   /**
     * Deletes an entity from the set of entities of the type {@link DHondtQuotient}.
     *
     * @param dHondtQuotient DHondtQuotient-EJBObject, which is removed from the set.
     */
   void removeDHondtQuotient(DHondtQuotient dHondtQuotient);
}
