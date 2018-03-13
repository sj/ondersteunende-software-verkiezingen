/*
 * BasicErgebniseingangHome
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;
import java.sql.Timestamp;
import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;

import de.ivu.ejb.IVUBeanBase;

import de.ivu.wahl.modell.ErgebniseingangModel;

/**
  * LocalHome interface for the entity Ergebniseingang as BMP Entity Bean.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicErgebniseingangHome extends EJBLocalHome {

      public static class HomeFinder {
         public static ErgebniseingangHome findHome(IVUBeanBase caller) {
            String simpleName = ErgebniseingangHome.class.getSimpleName();
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
   Ergebniseingang create() throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Ergebniseingang create(ErgebniseingangModel details) throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Ergebniseingang key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Ergebniseingang create(String id_Ergebniseingang) throws CreateException, EJBException;

   /**
     * Searches for existing Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param pkObj Key of the entity
     * @return Key of the entity
     * @throws ObjectNotFoundException if the entity was not found.
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Ergebniseingang findByPrimaryKey(String pkObj) throws ObjectNotFoundException, FinderException;

   /**
     * Searches for all existing Bean-instances. (Bean-supporting method by EJB specification.)
     *
     * @return Collection of keys of the entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Ergebniseingang> findAll() throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gebiet ID of the objects to be searched
     * @return  {@link Collection} of the found Ergebniseingang-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Ergebniseingang> findAllByGebiet(String id_Gebiet) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Wahl ID of the objects to be searched
     * @return  {@link Collection} of the found Ergebniseingang-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Ergebniseingang> findAllByWahl(String id_Wahl) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Erfassungseinheit ID of the objects to be searched
     * @return  {@link Collection} of the found Ergebniseingang-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Ergebniseingang> findAllByErfassungseinheit(String id_Erfassungseinheit) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by anwenderName.
     *
     * @param anwenderName searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Ergebniseingang}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Ergebniseingang> findAllByAnwenderName(String anwenderName) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by anwenderName.
     *
     * @param anwenderName searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Ergebniseingang}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Ergebniseingang> findAllLikeAnwenderName(String anwenderName) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by zeitstempel.
     *
     * @param zeitstempel searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Ergebniseingang}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Ergebniseingang> findAllByZeitstempel(Timestamp zeitstempel) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by herkunft.
     *
     * @param herkunft searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Ergebniseingang}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Ergebniseingang> findAllByHerkunft(int herkunft) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by wahlergebnisart.
     *
     * @param wahlergebnisart searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Ergebniseingang}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Ergebniseingang> findAllByWahlergebnisart(int wahlergebnisart) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by unterschiedeVorhanden.
     *
     * @param unterschiedeVorhanden searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Ergebniseingang}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Ergebniseingang> findAllByUnterschiedeVorhanden(int unterschiedeVorhanden) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by status.
     *
     * @param status searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Ergebniseingang}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Ergebniseingang> findAllByStatus(int status) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by ergebnisHash.
     *
     * @param ergebnisHash searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Ergebniseingang}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Ergebniseingang> findAllByErgebnisHash(String ergebnisHash) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by ergebnisHash.
     *
     * @param ergebnisHash searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Ergebniseingang}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Ergebniseingang> findAllLikeErgebnisHash(String ergebnisHash) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by fehlermeldung.
     *
     * @param fehlermeldung searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Ergebniseingang}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Ergebniseingang> findAllByFehlermeldung(String fehlermeldung) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by fehlermeldung.
     *
     * @param fehlermeldung searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Ergebniseingang}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Ergebniseingang> findAllLikeFehlermeldung(String fehlermeldung) throws FinderException;
}
