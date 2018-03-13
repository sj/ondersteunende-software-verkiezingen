/*
 * Alternative
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import de.ivu.wahl.modell.AlternativeModel;

/**
  * Interface for the entity Alternative as Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface Alternative extends EJBLocalObject, AlternativeModel {
   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details model object with new state
     */
   void setDetails(AlternativeModel details);

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   AlternativeModel getDetails();


   /**
     * Navigation to the associated entity of the type {@link Personendaten}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Personendaten getPersonendaten() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Personendaten}
     *
     * @param personendaten the corresponding EJBObject
     */
   void setPersonendaten(Personendaten personendaten);

   /**
     * Navigation to the associated entity of the type {@link Konflikt}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Konflikt getKonflikt() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Konflikt}
     *
     * @param konflikt the corresponding EJBObject
     */
   void setKonflikt(Konflikt konflikt);

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
     * Returns the set of entities of the type {@link Konflikt}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Konflikt}
     * @throws EJBException: an error occurred
     */
   Collection<Konflikt> getKonfliktCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Konflikt}.
     *
     * @param konflikt Konflikt-object
     */
   void addKonflikt(Konflikt konflikt);

   /**
     * Adds the object to the set of entities of the type {@link Konflikt}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllKonfliktCol(Collection<Konflikt> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Konflikt}.
     *
     * @param konflikt Konflikt-EJBObject, which is removed from the set.
     */
   void removeKonflikt(Konflikt konflikt);
}
