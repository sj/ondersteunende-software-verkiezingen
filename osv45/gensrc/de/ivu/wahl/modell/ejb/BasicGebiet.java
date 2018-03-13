/*
 * BasicGebiet
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import de.ivu.wahl.modell.GebietModel;

/**
  * Interface for the entity Gebiet as Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicGebiet extends EJBLocalObject, GebietModel {
   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details model object with new state
     */
   void setDetails(GebietModel details);

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   GebietModel getDetails();


   /**
     * Navigation to the associated entity of the type {@link Ergebniseingang}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Ergebniseingang getLetzterEingang() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Ergebniseingang}
     *
     * @param letzterEingang the corresponding EJBObject
     */
   void setLetzterEingang(Ergebniseingang letzterEingang);

   /**
     * Navigation to the associated entity of the type {@link Gebiet}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Gebiet getUebergeordnetesGebiet() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Gebiet}
     *
     * @param uebergeordnetesGebiet the corresponding EJBObject
     */
   void setUebergeordnetesGebiet(Gebiet uebergeordnetesGebiet);

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
     * Deletes an entity from the set of entities of the type {@link Anwender}.
     *
     * @param anwender Anwender-EJBObject, which is removed from the set.
     */
   void removeAnwender(Anwender anwender);

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Ergebniseingang}
     * @throws EJBException: an error occurred
     */
   Collection<Ergebniseingang> getErfassungseinheitCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Ergebniseingang}.
     *
     * @param erfassungseinheit Ergebniseingang-object
     */
   void addErfassungseinheit(Ergebniseingang erfassungseinheit);

   /**
     * Adds the object to the set of entities of the type {@link Ergebniseingang}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllErfassungseinheitCol(Collection<Ergebniseingang> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Ergebniseingang}.
     *
     * @param erfassungseinheit Ergebniseingang-EJBObject, which is removed from the set.
     */
   void removeErfassungseinheit(Ergebniseingang erfassungseinheit);

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
     * Adds the object, which is marked by the ID, to the set of entities of the type {@link Ergebniseingang}.
     *
     * @param id_Ergebniseingang ID of Ergebniseingang entity
     * @throws EJBException: an error occurred
     */
   void addID_Ergebniseingang(String id_Ergebniseingang) throws EJBException;

   /**
     * Deletes an entity from the set of entities of the type {@link Ergebniseingang}.
     *
     * @param ergebniseingang Ergebniseingang-EJBObject, which is removed from the set.
     */
   void removeErgebniseingang(Ergebniseingang ergebniseingang);

   /**
     * Deletes an entity from the set of entities of the type {@link Ergebniseingang}.
     *
     * @param id_Ergebniseingang ID of the Ergebniseingang entity to be deleted
     * @throws EJBException: an error occurred
     */
   void removeID_Ergebniseingang(String id_Ergebniseingang) throws EJBException;

   /**
     * Returns the set of entities of the type {@link Gebiet}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Gebiet}
     * @throws EJBException: an error occurred
     */
   Collection<Gebiet> getUntergebietCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Gebiet}.
     *
     * @param untergebiet Gebiet-object
     */
   void addUntergebiet(Gebiet untergebiet);

   /**
     * Adds the object to the set of entities of the type {@link Gebiet}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllUntergebietCol(Collection<Gebiet> col);

   /**
     * Adds the object, which is marked by the ID, to the set of entities of the type {@link Untergebiet}.
     *
     * @param id_Untergebiet ID of Untergebiet entity
     * @throws EJBException: an error occurred
     */
   void addID_Untergebiet(String id_Untergebiet) throws EJBException;

   /**
     * Deletes an entity from the set of entities of the type {@link Gebiet}.
     *
     * @param untergebiet Gebiet-EJBObject, which is removed from the set.
     */
   void removeUntergebiet(Gebiet untergebiet);

   /**
     * Deletes an entity from the set of entities of the type {@link Gebiet}.
     *
     * @param id_Untergebiet ID of the Gebiet entity to be deleted
     * @throws EJBException: an error occurred
     */
   void removeID_Untergebiet(String id_Untergebiet) throws EJBException;

   /**
     * Returns the set of entities of the type {@link Gebiet}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Gebiet}
     * @throws EJBException: an error occurred
     */
   Collection<Gebiet> getElterngebietCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Gebiet}.
     *
     * @param elterngebiet Gebiet-object
     */
   void addElterngebiet(Gebiet elterngebiet);

   /**
     * Adds the object to the set of entities of the type {@link Gebiet}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllElterngebietCol(Collection<Gebiet> col);

   /**
     * Adds the object, which is marked by the ID, to the set of entities of the type {@link Elterngebiet}.
     *
     * @param id_Elterngebiet ID of Elterngebiet entity
     * @throws EJBException: an error occurred
     */
   void addID_Elterngebiet(String id_Elterngebiet) throws EJBException;

   /**
     * Deletes an entity from the set of entities of the type {@link Gebiet}.
     *
     * @param elterngebiet Gebiet-EJBObject, which is removed from the set.
     */
   void removeElterngebiet(Gebiet elterngebiet);

   /**
     * Deletes an entity from the set of entities of the type {@link Gebiet}.
     *
     * @param id_Elterngebiet ID of the Gebiet entity to be deleted
     * @throws EJBException: an error occurred
     */
   void removeID_Elterngebiet(String id_Elterngebiet) throws EJBException;

   /**
     * Returns the set of entities of the type {@link Gebietsstatus}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Gebietsstatus}
     * @throws EJBException: an error occurred
     */
   Collection<Gebietsstatus> getGebietsstatusCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Gebietsstatus}.
     *
     * @param gebietsstatus Gebietsstatus-object
     */
   void addGebietsstatus(Gebietsstatus gebietsstatus);

   /**
     * Adds the object to the set of entities of the type {@link Gebietsstatus}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllGebietsstatusCol(Collection<Gebietsstatus> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Gebietsstatus}.
     *
     * @param gebietsstatus Gebietsstatus-EJBObject, which is removed from the set.
     */
   void removeGebietsstatus(Gebietsstatus gebietsstatus);

   /**
     * Returns the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link GruppeGebietsspezifisch}
     * @throws EJBException: an error occurred
     */
   Collection<GruppeGebietsspezifisch> getGruppeGebietsspezifischCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @param gruppeGebietsspezifisch GruppeGebietsspezifisch-object
     */
   void addGruppeGebietsspezifisch(GruppeGebietsspezifisch gruppeGebietsspezifisch);

   /**
     * Adds the object to the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllGruppeGebietsspezifischCol(Collection<GruppeGebietsspezifisch> col);

   /**
     * Deletes an entity from the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @param gruppeGebietsspezifisch GruppeGebietsspezifisch-EJBObject, which is removed from the set.
     */
   void removeGruppeGebietsspezifisch(GruppeGebietsspezifisch gruppeGebietsspezifisch);

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

   /**
     * Returns the set of entities of the type {@link StimmergebnisseUntergebiete}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link StimmergebnisseUntergebiete}
     * @throws EJBException: an error occurred
     */
   Collection<StimmergebnisseUntergebiete> getStimmergebnisseUntergebieteCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link StimmergebnisseUntergebiete}.
     *
     * @param stimmergebnisseUntergebiete StimmergebnisseUntergebiete-object
     */
   void addStimmergebnisseUntergebiete(StimmergebnisseUntergebiete stimmergebnisseUntergebiete);

   /**
     * Adds the object to the set of entities of the type {@link StimmergebnisseUntergebiete}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllStimmergebnisseUntergebieteCol(Collection<StimmergebnisseUntergebiete> col);

   /**
     * Deletes an entity from the set of entities of the type {@link StimmergebnisseUntergebiete}.
     *
     * @param stimmergebnisseUntergebiete StimmergebnisseUntergebiete-EJBObject, which is removed from the set.
     */
   void removeStimmergebnisseUntergebiete(StimmergebnisseUntergebiete stimmergebnisseUntergebiete);

   /**
     * Returns the set of entities of the type {@link Wahl}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Wahl}
     * @throws EJBException: an error occurred
     */
   Collection<Wahl> getWahlCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Wahl}.
     *
     * @param wahl Wahl-object
     */
   void addWahl(Wahl wahl);

   /**
     * Adds the object to the set of entities of the type {@link Wahl}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllWahlCol(Collection<Wahl> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Wahl}.
     *
     * @param wahl Wahl-EJBObject, which is removed from the set.
     */
   void removeWahl(Wahl wahl);
}
