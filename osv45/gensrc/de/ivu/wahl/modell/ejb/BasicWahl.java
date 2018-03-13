/*
 * BasicWahl
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import de.ivu.wahl.modell.WahlModel;

/**
  * Interface for the entity Wahl as Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicWahl extends EJBLocalObject, WahlModel {
   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details model object with new state
     */
   void setDetails(WahlModel details);

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   WahlModel getDetails();


   /**
     * Navigation to the associated entity of the type {@link Gebiet}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Gebiet getWahlgebiet() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Gebiet}
     *
     * @param wahlgebiet the corresponding EJBObject
     */
   void setWahlgebiet(Gebiet wahlgebiet);

   /**
     * Navigation to the associated entity of the type {@link Gebiet}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Gebiet getWurzelgebiet() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Gebiet}
     *
     * @param wurzelgebiet the corresponding EJBObject
     */
   void setWurzelgebiet(Gebiet wurzelgebiet);

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Ergebniseingang}
     * @throws EJBException: an error occurred
     */
   Collection<Ergebniseingang> getErgebniseingangCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Ergebniseingang}.
     *
     * @param ergebniseingang Ergebniseingang-object
     */
   void addErgebniseingang(Ergebniseingang ergebniseingang);

   /**
     * Adds the object to the set of entities of the type {@link Ergebniseingang}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllErgebniseingangCol(Collection<Ergebniseingang> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Ergebniseingang}.
     *
     * @param ergebniseingang Ergebniseingang-EJBObject, which is removed from the set.
     */
   void removeErgebniseingang(Ergebniseingang ergebniseingang);

   /**
     * Returns the set of entities of the type {@link Gebiet}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Gebiet}
     * @throws EJBException: an error occurred
     */
   Collection<Gebiet> getGebietCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Gebiet}.
     *
     * @param gebiet Gebiet-object
     */
   void addGebiet(Gebiet gebiet);

   /**
     * Adds the object to the set of entities of the type {@link Gebiet}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllGebietCol(Collection<Gebiet> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Gebiet}.
     *
     * @param gebiet Gebiet-EJBObject, which is removed from the set.
     */
   void removeGebiet(Gebiet gebiet);

   /**
     * Returns the set of entities of the type {@link Gruppe}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Gruppe}
     * @throws EJBException: an error occurred
     */
   Collection<Gruppe> getGruppeCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Gruppe}.
     *
     * @param gruppe Gruppe-object
     */
   void addGruppe(Gruppe gruppe);

   /**
     * Adds the object to the set of entities of the type {@link Gruppe}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllGruppeCol(Collection<Gruppe> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Gruppe}.
     *
     * @param gruppe Gruppe-EJBObject, which is removed from the set.
     */
   void removeGruppe(Gruppe gruppe);

   /**
     * Returns the set of entities of the type {@link Liste}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Liste}
     * @throws EJBException: an error occurred
     */
   Collection<Liste> getListeCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Liste}.
     *
     * @param liste Liste-object
     */
   void addListe(Liste liste);

   /**
     * Adds the object to the set of entities of the type {@link Liste}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllListeCol(Collection<Liste> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Liste}.
     *
     * @param liste Liste-EJBObject, which is removed from the set.
     */
   void removeListe(Liste liste);

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
     * Returns the set of entities of the type {@link Listenkombination}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Listenkombination}
     * @throws EJBException: an error occurred
     */
   Collection<Listenkombination> getListenkombinationCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Listenkombination}.
     *
     * @param listenkombination Listenkombination-object
     */
   void addListenkombination(Listenkombination listenkombination);

   /**
     * Adds the object to the set of entities of the type {@link Listenkombination}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllListenkombinationCol(Collection<Listenkombination> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Listenkombination}.
     *
     * @param listenkombination Listenkombination-EJBObject, which is removed from the set.
     */
   void removeListenkombination(Listenkombination listenkombination);

   /**
     * Returns the set of entities of the type {@link Schwellwert}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Schwellwert}
     * @throws EJBException: an error occurred
     */
   Collection<Schwellwert> getSchwellwertCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Schwellwert}.
     *
     * @param schwellwert Schwellwert-object
     */
   void addSchwellwert(Schwellwert schwellwert);

   /**
     * Adds the object to the set of entities of the type {@link Schwellwert}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllSchwellwertCol(Collection<Schwellwert> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Schwellwert}.
     *
     * @param schwellwert Schwellwert-EJBObject, which is removed from the set.
     */
   void removeSchwellwert(Schwellwert schwellwert);
}
