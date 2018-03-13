/*
 * Personendaten
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import de.ivu.wahl.modell.PersonendatenModel;

/**
  * Interface for the entity Personendaten as Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface Personendaten extends EJBLocalObject, PersonendatenModel {
   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details model object with new state
     */
   void setDetails(PersonendatenModel details);

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   PersonendatenModel getDetails();


   /**
     * Navigation to the associated entity of the type {@link Personendaten}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Personendaten getPersonendatenAgent() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Personendaten}
     *
     * @param personendatenAgent the corresponding EJBObject
     */
   void setPersonendatenAgent(Personendaten personendatenAgent);

   /**
     * Returns the set of entities of the type {@link Alternative}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Alternative}
     * @throws EJBException: an error occurred
     */
   Collection<Alternative> getAlternativeCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Alternative}.
     *
     * @param alternative Alternative-object
     */
   void addAlternative(Alternative alternative);

   /**
     * Adds the object to the set of entities of the type {@link Alternative}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllAlternativeCol(Collection<Alternative> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Alternative}.
     *
     * @param alternative Alternative-EJBObject, which is removed from the set.
     */
   void removeAlternative(Alternative alternative);

   /**
     * Returns the set of entities of the type {@link Besonderheit}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Besonderheit}
     * @throws EJBException: an error occurred
     */
   Collection<Besonderheit> getBesonderheitCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Besonderheit}.
     *
     * @param besonderheit Besonderheit-object
     */
   void addBesonderheit(Besonderheit besonderheit);

   /**
     * Adds the object to the set of entities of the type {@link Besonderheit}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllBesonderheitCol(Collection<Besonderheit> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Besonderheit}.
     *
     * @param besonderheit Besonderheit-EJBObject, which is removed from the set.
     */
   void removeBesonderheit(Besonderheit besonderheit);

   /**
     * Returns the set of entities of the type {@link Listenkandidatur}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Listenkandidatur}
     * @throws EJBException: an error occurred
     */
   Collection<Listenkandidatur> getListenkandidaturCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Listenkandidatur}.
     *
     * @param listenkandidatur Listenkandidatur-object
     */
   void addListenkandidatur(Listenkandidatur listenkandidatur);

   /**
     * Adds the object to the set of entities of the type {@link Listenkandidatur}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllListenkandidaturCol(Collection<Listenkandidatur> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Listenkandidatur}.
     *
     * @param listenkandidatur Listenkandidatur-EJBObject, which is removed from the set.
     */
   void removeListenkandidatur(Listenkandidatur listenkandidatur);

   /**
     * Returns the set of entities of the type {@link Personendaten}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Personendaten}
     * @throws EJBException: an error occurred
     */
   Collection<Personendaten> getPersonendatenAgentCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Personendaten}.
     *
     * @param personendatenAgent Personendaten-object
     */
   void addPersonendatenAgent(Personendaten personendatenAgent);

   /**
     * Adds the object to the set of entities of the type {@link Personendaten}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllPersonendatenAgentCol(Collection<Personendaten> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Personendaten}.
     *
     * @param personendatenAgent Personendaten-EJBObject, which is removed from the set.
     */
   void removePersonendatenAgent(Personendaten personendatenAgent);
}
