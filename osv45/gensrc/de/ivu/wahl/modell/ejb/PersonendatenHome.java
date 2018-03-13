/*
 * PersonendatenHome
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;
import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;

import de.ivu.ejb.IVUBeanBase;

import de.ivu.wahl.modell.PersonendatenModel;

/**
  * LocalHome interface for the entity Personendaten as BMP Entity Bean.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface PersonendatenHome extends EJBLocalHome {

      public static class HomeFinder {
         public static PersonendatenHome findHome(IVUBeanBase caller) {
            String simpleName = PersonendatenHome.class.getSimpleName();
            return caller.findLocalHome(simpleName.substring(0, simpleName.length() - 4));
         }
      }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Personendaten create() throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Personendaten create(PersonendatenModel details) throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Personendaten key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Personendaten create(String id_Personendaten) throws CreateException, EJBException;

   /**
     * Searches for existing Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param pkObj Key of the entity
     * @return Key of the entity
     * @throws ObjectNotFoundException if the entity was not found.
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Personendaten findByPrimaryKey(String pkObj) throws ObjectNotFoundException, FinderException;

   /**
     * Searches for all existing Bean-instances. (Bean-supporting method by EJB specification.)
     *
     * @return Collection of keys of the entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Personendaten> findAll() throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_PersonendatenAgent ID of the objects to be searched
     * @return  {@link Collection} of the found Personendaten-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Personendaten> findAllByPersonendatenAgent(String id_PersonendatenAgent) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by nachname.
     *
     * @param nachname searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllByNachname(String nachname) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by nachname.
     *
     * @param nachname searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllLikeNachname(String nachname) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by vorname.
     *
     * @param vorname searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllByVorname(String vorname) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by vorname.
     *
     * @param vorname searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllLikeVorname(String vorname) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by praefix.
     *
     * @param praefix searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllByPraefix(String praefix) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by praefix.
     *
     * @param praefix searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllLikePraefix(String praefix) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by initialen.
     *
     * @param initialen searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllByInitialen(String initialen) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by initialen.
     *
     * @param initialen searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllLikeInitialen(String initialen) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by titel.
     *
     * @param titel searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllByTitel(String titel) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by titel.
     *
     * @param titel searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllLikeTitel(String titel) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by geschlecht.
     *
     * @param geschlecht searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllByGeschlecht(int geschlecht) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by generation.
     *
     * @param generation searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllByGeneration(String generation) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by generation.
     *
     * @param generation searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllLikeGeneration(String generation) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by land.
     *
     * @param land searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllByLand(String land) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by land.
     *
     * @param land searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllLikeLand(String land) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by wohnort.
     *
     * @param wohnort searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllByWohnort(String wohnort) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by wohnort.
     *
     * @param wohnort searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllLikeWohnort(String wohnort) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by kontakt_Land.
     *
     * @param kontakt_Land searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllByKontakt_Land(String kontakt_Land) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by kontakt_Land.
     *
     * @param kontakt_Land searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllLikeKontakt_Land(String kontakt_Land) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by kontakt_Wohnort.
     *
     * @param kontakt_Wohnort searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllByKontakt_Wohnort(String kontakt_Wohnort) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by kontakt_Wohnort.
     *
     * @param kontakt_Wohnort searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllLikeKontakt_Wohnort(String kontakt_Wohnort) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by kontakt_PLZ.
     *
     * @param kontakt_PLZ searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllByKontakt_PLZ(String kontakt_PLZ) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by kontakt_PLZ.
     *
     * @param kontakt_PLZ searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllLikeKontakt_PLZ(String kontakt_PLZ) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by kontakt_Strasse.
     *
     * @param kontakt_Strasse searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllByKontakt_Strasse(String kontakt_Strasse) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by kontakt_Strasse.
     *
     * @param kontakt_Strasse searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllLikeKontakt_Strasse(String kontakt_Strasse) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by benennbar.
     *
     * @param benennbar searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Personendaten}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Personendaten> findAllByBenennbar(boolean benennbar) throws FinderException;
}
