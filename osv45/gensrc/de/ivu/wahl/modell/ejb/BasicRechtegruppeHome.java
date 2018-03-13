/*
 * BasicRechtegruppeHome
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

import de.ivu.wahl.modell.RechtegruppeModel;

/**
  * LocalHome interface for the entity Rechtegruppe as BMP Entity Bean.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicRechtegruppeHome extends EJBLocalHome {

      public static class HomeFinder {
         public static RechtegruppeHome findHome(IVUBeanBase caller) {
            String simpleName = RechtegruppeHome.class.getSimpleName();
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
   Rechtegruppe create() throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Rechtegruppe create(RechtegruppeModel details) throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Rechtegruppe key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Rechtegruppe create(String id_Rechtegruppe) throws CreateException, EJBException;

   /**
     * Searches for existing Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param pkObj Key of the entity
     * @return Key of the entity
     * @throws ObjectNotFoundException if the entity was not found.
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Rechtegruppe findByPrimaryKey(String pkObj) throws ObjectNotFoundException, FinderException;

   /**
     * Searches for all existing Bean-instances. (Bean-supporting method by EJB specification.)
     *
     * @return Collection of keys of the entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Rechtegruppe> findAll() throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Anwender ID of the objects to be searched
     * @return  {@link Collection} of the found Rechtegruppe-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Rechtegruppe> findAllByAnwender(String id_Anwender) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Recht ID of the objects to be searched
     * @return  {@link Collection} of the found Rechtegruppe-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Rechtegruppe> findAllByRecht(String id_Recht) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Rechtegruppe}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Rechtegruppe}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Rechtegruppe> findAllByName(String name) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Rechtegruppe}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Rechtegruppe}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Rechtegruppe> findAllLikeName(String name) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Rechtegruppe}, filtered by beschreibung.
     *
     * @param beschreibung searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Rechtegruppe}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Rechtegruppe> findAllByBeschreibung(String beschreibung) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Rechtegruppe}, filtered by beschreibung.
     *
     * @param beschreibung searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Rechtegruppe}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Rechtegruppe> findAllLikeBeschreibung(String beschreibung) throws FinderException;
}
