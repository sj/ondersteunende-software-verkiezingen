/*
 * BasicListenkandidatur
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import de.ivu.wahl.modell.ListenkandidaturModel;

/**
  * Interface for the entity Listenkandidatur as Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicListenkandidatur extends EJBLocalObject, ListenkandidaturModel {
   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details model object with new state
     */
   void setDetails(ListenkandidaturModel details);

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   ListenkandidaturModel getDetails();


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
     * Navigation to the associated entity of the type {@link Wahl}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Wahl getWahl() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Wahl}
     *
     * @param wahl the corresponding EJBObject
     */
   void setWahl(Wahl wahl);

   /**
     * Returns the set of entities of the type {@link ListenkandidaturErgebnis}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link ListenkandidaturErgebnis}
     * @throws EJBException: an error occurred
     */
   Collection<ListenkandidaturErgebnis> getListenkandidaturErgebnisCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link ListenkandidaturErgebnis}.
     *
     * @param listenkandidaturErgebnis ListenkandidaturErgebnis-object
     */
   void addListenkandidaturErgebnis(ListenkandidaturErgebnis listenkandidaturErgebnis);

   /**
     * Adds the object to the set of entities of the type {@link ListenkandidaturErgebnis}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllListenkandidaturErgebnisCol(Collection<ListenkandidaturErgebnis> col);

   /**
     * Deletes an entity from the set of entities of the type {@link ListenkandidaturErgebnis}.
     *
     * @param listenkandidaturErgebnis ListenkandidaturErgebnis-EJBObject, which is removed from the set.
     */
   void removeListenkandidaturErgebnis(ListenkandidaturErgebnis listenkandidaturErgebnis);

   /**
     * Returns the set of entities of the type {@link Stimmergebnis}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Stimmergebnis}
     * @throws EJBException: an error occurred
     */
   Collection<Stimmergebnis> getStimmergebnisCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Stimmergebnis}.
     *
     * @param stimmergebnis Stimmergebnis-object
     */
   void addStimmergebnis(Stimmergebnis stimmergebnis);

   /**
     * Adds the object to the set of entities of the type {@link Stimmergebnis}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllStimmergebnisCol(Collection<Stimmergebnis> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Stimmergebnis}.
     *
     * @param stimmergebnis Stimmergebnis-EJBObject, which is removed from the set.
     */
   void removeStimmergebnis(Stimmergebnis stimmergebnis);
}
