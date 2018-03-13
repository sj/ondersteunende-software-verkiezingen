/*
 * BasicAnwenderHome
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

import de.ivu.wahl.modell.AnwenderModel;

/**
  * LocalHome interface for the entity Anwender as BMP Entity Bean.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicAnwenderHome extends EJBLocalHome {

      public static class HomeFinder {
         public static AnwenderHome findHome(IVUBeanBase caller) {
            String simpleName = AnwenderHome.class.getSimpleName();
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
   Anwender create() throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Anwender create(AnwenderModel details) throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Anwender key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Anwender create(String id_Anwender) throws CreateException, EJBException;

   /**
     * Searches for existing Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param pkObj Key of the entity
     * @return Key of the entity
     * @throws ObjectNotFoundException if the entity was not found.
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Anwender findByPrimaryKey(String pkObj) throws ObjectNotFoundException, FinderException;

   /**
     * Searches for all existing Bean-instances. (Bean-supporting method by EJB specification.)
     *
     * @return Collection of keys of the entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Anwender> findAll() throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Rechtegruppe ID of the objects to be searched
     * @return  {@link Collection} of the found Anwender-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Anwender> findAllByRechtegruppe(String id_Rechtegruppe) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gebiet ID of the objects to be searched
     * @return  {@link Collection} of the found Anwender-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Anwender> findAllByGebiet(String id_Gebiet) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Anwender}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Anwender> findAllByName(String name) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Anwender}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Anwender> findAllLikeName(String name) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by anwendername.
     *
     * @param anwendername searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Anwender}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Anwender> findAllByAnwendername(String anwendername) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by anwendername.
     *
     * @param anwendername searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Anwender}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Anwender> findAllLikeAnwendername(String anwendername) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by passwordHash.
     *
     * @param passwordHash searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Anwender}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Anwender> findAllByPasswordHash(String passwordHash) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by passwordHash.
     *
     * @param passwordHash searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Anwender}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Anwender> findAllLikePasswordHash(String passwordHash) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by salt.
     *
     * @param salt searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Anwender}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Anwender> findAllBySalt(String salt) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by salt.
     *
     * @param salt searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Anwender}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Anwender> findAllLikeSalt(String salt) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by fehlversucheAnmeldung.
     *
     * @param fehlversucheAnmeldung searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Anwender}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Anwender> findAllByFehlversucheAnmeldung(int fehlversucheAnmeldung) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by letzterZugriff.
     *
     * @param letzterZugriff searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Anwender}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Anwender> findAllByLetzterZugriff(Timestamp letzterZugriff) throws FinderException;
}
