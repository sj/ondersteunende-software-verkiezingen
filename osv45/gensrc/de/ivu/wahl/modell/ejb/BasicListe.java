/*
 * BasicListe
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import de.ivu.wahl.modell.ListeModel;

/**
  * Interface for the entity Liste as Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicListe extends EJBLocalObject, ListeModel {
   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details model object with new state
     */
   void setDetails(ListeModel details);

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   ListeModel getDetails();


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
}
