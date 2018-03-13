/*
 * BasicGebietHome
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

import de.ivu.wahl.modell.GebietModel;

/**
  * LocalHome interface for the entity Gebiet as BMP Entity Bean.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicGebietHome extends EJBLocalHome {

      public static class HomeFinder {
         public static GebietHome findHome(IVUBeanBase caller) {
            String simpleName = GebietHome.class.getSimpleName();
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
   Gebiet create() throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Gebiet create(GebietModel details) throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Gebiet key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Gebiet create(String id_Gebiet) throws CreateException, EJBException;

   /**
     * Searches for existing Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param pkObj Key of the entity
     * @return Key of the entity
     * @throws ObjectNotFoundException if the entity was not found.
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Gebiet findByPrimaryKey(String pkObj) throws ObjectNotFoundException, FinderException;

   /**
     * Searches for all existing Bean-instances. (Bean-supporting method by EJB specification.)
     *
     * @return Collection of keys of the entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Gebiet> findAll() throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Ergebniseingang ID of the objects to be searched
     * @return  {@link Collection} of the found Gebiet-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Gebiet> findAllByErgebniseingang(String id_Ergebniseingang) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Untergebiet ID of the objects to be searched
     * @return  {@link Collection} of the found Gebiet-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Gebiet> findAllByUntergebiet(String id_Untergebiet) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Elterngebiet ID of the objects to be searched
     * @return  {@link Collection} of the found Gebiet-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Gebiet> findAllByElterngebiet(String id_Elterngebiet) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_LetzterEingang ID of the objects to be searched
     * @return  {@link Collection} of the found Gebiet-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Gebiet> findAllByLetzterEingang(String id_LetzterEingang) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_UebergeordnetesGebiet ID of the objects to be searched
     * @return  {@link Collection} of the found Gebiet-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Gebiet> findAllByUebergeordnetesGebiet(String id_UebergeordnetesGebiet) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Wahl ID of the objects to be searched
     * @return  {@link Collection} of the found Gebiet-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Gebiet> findAllByWahl(String id_Wahl) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by erfassungseinheit.
     *
     * @param erfassungseinheit searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gebiet}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gebiet> findAllByErfassungseinheit(boolean erfassungseinheit) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by wahleinheit.
     *
     * @param wahleinheit searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gebiet}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gebiet> findAllByWahleinheit(boolean wahleinheit) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by gebietsart.
     *
     * @param gebietsart searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gebiet}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gebiet> findAllByGebietsart(int gebietsart) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by nummer.
     *
     * @param nummer searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gebiet}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gebiet> findAllByNummer(int nummer) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by roemisch.
     *
     * @param roemisch searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gebiet}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gebiet> findAllByRoemisch(boolean roemisch) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gebiet}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gebiet> findAllByName(String name) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gebiet}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gebiet> findAllLikeName(String name) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by kuerzel.
     *
     * @param kuerzel searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gebiet}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gebiet> findAllByKuerzel(String kuerzel) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by kuerzel.
     *
     * @param kuerzel searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gebiet}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gebiet> findAllLikeKuerzel(String kuerzel) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by position.
     *
     * @param position searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gebiet}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gebiet> findAllByPosition(int position) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by wahlberechtigte.
     *
     * @param wahlberechtigte searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gebiet}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gebiet> findAllByWahlberechtigte(int wahlberechtigte) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by gUIEingabeErlaubt.
     *
     * @param gUIEingabeErlaubt searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gebiet}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gebiet> findAllByGUIEingabeErlaubt(boolean gUIEingabeErlaubt) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by postalvote.
     *
     * @param postalvote searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gebiet}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gebiet> findAllByPostalvote(boolean postalvote) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by voteValue.
     *
     * @param voteValue searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gebiet}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gebiet> findAllByVoteValue(int voteValue) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by zipcode.
     *
     * @param zipcode searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gebiet}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gebiet> findAllByZipcode(String zipcode) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by zipcode.
     *
     * @param zipcode searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Gebiet}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Gebiet> findAllLikeZipcode(String zipcode) throws FinderException;
}
