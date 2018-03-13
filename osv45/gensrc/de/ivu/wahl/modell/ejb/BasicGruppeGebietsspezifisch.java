/*
 * BasicGruppeGebietsspezifisch
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import de.ivu.wahl.modell.GruppeGebietsspezifischModel;

/**
  * Interface for the entity GruppeGebietsspezifisch as Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicGruppeGebietsspezifisch extends EJBLocalObject, GruppeGebietsspezifischModel {
   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details model object with new state
     */
   void setDetails(GruppeGebietsspezifischModel details);

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   GruppeGebietsspezifischModel getDetails();


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
     * Navigation to the associated entity of the type {@link GruppeGebietsspezifisch}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   GruppeGebietsspezifisch getUebergeordneteGG() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link GruppeGebietsspezifisch}
     *
     * @param uebergeordneteGG the corresponding EJBObject
     */
   void setUebergeordneteGG(GruppeGebietsspezifisch uebergeordneteGG);

   /**
     * Navigation to the associated entity of the type {@link Gebiet}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Gebiet getGebiet() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Gebiet}
     *
     * @param gebiet the corresponding EJBObject
     */
   void setGebiet(Gebiet gebiet);

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
     * Returns the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link GruppeGebietsspezifisch}
     * @throws EJBException: an error occurred
     */
   Collection<GruppeGebietsspezifisch> getUebergeordneteGGCol() throws EJBException;

   /**
     * Adds the object to the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @param uebergeordneteGG GruppeGebietsspezifisch-object
     */
   void addUebergeordneteGG(GruppeGebietsspezifisch uebergeordneteGG);

   /**
     * Adds the object to the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   void addAllUebergeordneteGGCol(Collection<GruppeGebietsspezifisch> col);

   /**
     * Deletes an entity from the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @param uebergeordneteGG GruppeGebietsspezifisch-EJBObject, which is removed from the set.
     */
   void removeUebergeordneteGG(GruppeGebietsspezifisch uebergeordneteGG);

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
