/*
 * Ergebniseingang
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import de.ivu.wahl.modell.ErgebniseingangModel;

/**
  * Interface for the entity Ergebniseingang as Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface Ergebniseingang extends EJBLocalObject, ErgebniseingangModel {
   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details model object with new state
     */
   void setDetails(ErgebniseingangModel details);

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   ErgebniseingangModel getDetails();


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
     * Navigation to the associated entity of the type {@link Gebiet}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Gebiet getErfassungseinheit() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Gebiet}
     *
     * @param erfassungseinheit the corresponding EJBObject
     */
   void setErfassungseinheit(Gebiet erfassungseinheit);

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
     * Returns the set of entities of the type {@link FiktiveSitzverteilung}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link FiktiveSitzverteilung}
     * @throws EJBException: an error occurred
     */
   Collection<FiktiveSitzverteilung> getFiktiveSitzverteilungCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link FiktiveSitzverteilung}.
     *
     * @param fiktiveSitzverteilung FiktiveSitzverteilung-object
     */
   void addFiktiveSitzverteilung(FiktiveSitzverteilung fiktiveSitzverteilung);

   /**
     * Adds the object to the set of entities of the type {@link FiktiveSitzverteilung}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllFiktiveSitzverteilungCol(Collection<FiktiveSitzverteilung> col);

   /**
     * Deletes an entity from the set of entities of the type {@link FiktiveSitzverteilung}.
     *
     * @param fiktiveSitzverteilung FiktiveSitzverteilung-EJBObject, which is removed from the set.
     */
   void removeFiktiveSitzverteilung(FiktiveSitzverteilung fiktiveSitzverteilung);

   /**
     * Returns the set of entities of the type {@link Gebiet}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Gebiet}
     * @throws EJBException: an error occurred
     */
   Collection<Gebiet> getLetzterEingangCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Gebiet}.
     *
     * @param letzterEingang Gebiet-object
     */
   void addLetzterEingang(Gebiet letzterEingang);

   /**
     * Adds the object to the set of entities of the type {@link Gebiet}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllLetzterEingangCol(Collection<Gebiet> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Gebiet}.
     *
     * @param letzterEingang Gebiet-EJBObject, which is removed from the set.
     */
   void removeLetzterEingang(Gebiet letzterEingang);

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
     * Adds the object, which is marked by the ID, to the set of entities of the type {@link Gebiet}.
     *
     * @param id_Gebiet ID of Gebiet entity
     * @throws EJBException: an error occurred
     */
   void addID_Gebiet(String id_Gebiet) throws EJBException;

   /**
     * Deletes an entity from the set of entities of the type {@link Gebiet}.
     *
     * @param gebiet Gebiet-EJBObject, which is removed from the set.
     */
   void removeGebiet(Gebiet gebiet);

   /**
     * Deletes an entity from the set of entities of the type {@link Gebiet}.
     *
     * @param id_Gebiet ID of the Gebiet entity to be deleted
     * @throws EJBException: an error occurred
     */
   void removeID_Gebiet(String id_Gebiet) throws EJBException;

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
     * Returns the set of entities of the type {@link ListenkombinationZulassung}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link ListenkombinationZulassung}
     * @throws EJBException: an error occurred
     */
   Collection<ListenkombinationZulassung> getListenkombinationZulassungCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link ListenkombinationZulassung}.
     *
     * @param listenkombinationZulassung ListenkombinationZulassung-object
     */
   void addListenkombinationZulassung(ListenkombinationZulassung listenkombinationZulassung);

   /**
     * Adds the object to the set of entities of the type {@link ListenkombinationZulassung}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllListenkombinationZulassungCol(Collection<ListenkombinationZulassung> col);

   /**
     * Deletes an entity from the set of entities of the type {@link ListenkombinationZulassung}.
     *
     * @param listenkombinationZulassung ListenkombinationZulassung-EJBObject, which is removed from the set.
     */
   void removeListenkombinationZulassung(ListenkombinationZulassung listenkombinationZulassung);

   /**
     * Returns the set of entities of the type {@link ListenplatzNeu}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link ListenplatzNeu}
     * @throws EJBException: an error occurred
     */
   Collection<ListenplatzNeu> getListenplatzNeuCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link ListenplatzNeu}.
     *
     * @param listenplatzNeu ListenplatzNeu-object
     */
   void addListenplatzNeu(ListenplatzNeu listenplatzNeu);

   /**
     * Adds the object to the set of entities of the type {@link ListenplatzNeu}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllListenplatzNeuCol(Collection<ListenplatzNeu> col);

   /**
     * Deletes an entity from the set of entities of the type {@link ListenplatzNeu}.
     *
     * @param listenplatzNeu ListenplatzNeu-EJBObject, which is removed from the set.
     */
   void removeListenplatzNeu(ListenplatzNeu listenplatzNeu);

   /**
     * Returns the set of entities of the type {@link Restsitzverteilung}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Restsitzverteilung}
     * @throws EJBException: an error occurred
     */
   Collection<Restsitzverteilung> getRestsitzverteilungCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Restsitzverteilung}.
     *
     * @param restsitzverteilung Restsitzverteilung-object
     */
   void addRestsitzverteilung(Restsitzverteilung restsitzverteilung);

   /**
     * Adds the object to the set of entities of the type {@link Restsitzverteilung}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllRestsitzverteilungCol(Collection<Restsitzverteilung> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Restsitzverteilung}.
     *
     * @param restsitzverteilung Restsitzverteilung-EJBObject, which is removed from the set.
     */
   void removeRestsitzverteilung(Restsitzverteilung restsitzverteilung);

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link SitzberechnungErgebnis}
     * @throws EJBException: an error occurred
     */
   Collection<SitzberechnungErgebnis> getSitzberechnungErgebnisCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link SitzberechnungErgebnis}.
     *
     * @param sitzberechnungErgebnis SitzberechnungErgebnis-object
     */
   void addSitzberechnungErgebnis(SitzberechnungErgebnis sitzberechnungErgebnis);

   /**
     * Adds the object to the set of entities of the type {@link SitzberechnungErgebnis}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllSitzberechnungErgebnisCol(Collection<SitzberechnungErgebnis> col);

   /**
     * Deletes an entity from the set of entities of the type {@link SitzberechnungErgebnis}.
     *
     * @param sitzberechnungErgebnis SitzberechnungErgebnis-EJBObject, which is removed from the set.
     */
   void removeSitzberechnungErgebnis(SitzberechnungErgebnis sitzberechnungErgebnis);

   /**
     * Returns the set of entities of the type {@link Sitzverteilung}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Sitzverteilung}
     * @throws EJBException: an error occurred
     */
   Collection<Sitzverteilung> getSitzverteilungCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Sitzverteilung}.
     *
     * @param sitzverteilung Sitzverteilung-object
     */
   void addSitzverteilung(Sitzverteilung sitzverteilung);

   /**
     * Adds the object to the set of entities of the type {@link Sitzverteilung}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllSitzverteilungCol(Collection<Sitzverteilung> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Sitzverteilung}.
     *
     * @param sitzverteilung Sitzverteilung-EJBObject, which is removed from the set.
     */
   void removeSitzverteilung(Sitzverteilung sitzverteilung);

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
     * Returns the set of entities of the type {@link Unterverteilung}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Unterverteilung}
     * @throws EJBException: an error occurred
     */
   Collection<Unterverteilung> getUnterverteilungCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link Unterverteilung}.
     *
     * @param unterverteilung Unterverteilung-object
     */
   void addUnterverteilung(Unterverteilung unterverteilung);

   /**
     * Adds the object to the set of entities of the type {@link Unterverteilung}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllUnterverteilungCol(Collection<Unterverteilung> col);

   /**
     * Deletes an entity from the set of entities of the type {@link Unterverteilung}.
     *
     * @param unterverteilung Unterverteilung-EJBObject, which is removed from the set.
     */
   void removeUnterverteilung(Unterverteilung unterverteilung);
}
