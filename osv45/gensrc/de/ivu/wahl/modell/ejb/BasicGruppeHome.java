/*
 * BasicGruppeHome
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

import de.ivu.wahl.modell.GruppeModel;

/**
  * LocalHome interface for the entity Gruppe as BMP Entity Bean.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicGruppeHome extends EJBLocalHome {

      public static class HomeFinder {
         public static GruppeHome findHome(IVUBeanBase caller) {
            String simpleName = GruppeHome.class.getSimpleName();
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
   Gruppe create() throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Gruppe create(GruppeModel details) throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Gruppe key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Gruppe create(String id_Gruppe) throws CreateException, EJBException;

   /**
     * Searches for existing Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param pkObj Key of the entity
     * @return Key of the entity
     * @throws ObjectNotFoundException if the entity was not found.
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Gruppe findByPrimaryKey(String pkObj) throws ObjectNotFoundException, FinderException;

   /**
     * Searches for all existing Bean-instances. (Bean-supporting method by EJB specification.)
     *
     * @return Collection of keys of the entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Gruppe> findAll() throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Listenkombination ID of the objects to be searched
     * @return  {@link Collection} of the found Gruppe-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Gruppe> findAllByListenkombination(String id_Listenkombination) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Wahl ID of the objects to be searched
     * @return  {@link Collection} of the found Gruppe-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Gruppe> findAllByWahl(String id_Wahl) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by schluessel.
     *
     * @param schluessel searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gruppe}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gruppe> findAllBySchluessel(int schluessel) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by gruppenart.
     *
     * @param gruppenart searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gruppe}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gruppe> findAllByGruppenart(int gruppenart) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by nameLang.
     *
     * @param nameLang searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gruppe}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gruppe> findAllByNameLang(String nameLang) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by nameLang.
     *
     * @param nameLang searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gruppe}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gruppe> findAllLikeNameLang(String nameLang) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by nameKurz.
     *
     * @param nameKurz searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gruppe}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gruppe> findAllByNameKurz(String nameKurz) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by nameKurz.
     *
     * @param nameKurz searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gruppe}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gruppe> findAllLikeNameKurz(String nameKurz) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by kautionGestellt.
     *
     * @param kautionGestellt searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gruppe}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gruppe> findAllByKautionGestellt(boolean kautionGestellt) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by farbe.
     *
     * @param farbe searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gruppe}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gruppe> findAllByFarbe(String farbe) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by farbe.
     *
     * @param farbe searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gruppe}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gruppe> findAllLikeFarbe(String farbe) throws FinderException;
}
