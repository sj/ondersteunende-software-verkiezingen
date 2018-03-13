/*
 * BasicRepositoryHome
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

import de.ivu.wahl.modell.RepositoryModel;

/**
  * LocalHome interface for the entity Repository as BMP Entity Bean.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicRepositoryHome extends EJBLocalHome {

      public static class HomeFinder {
         public static RepositoryHome findHome(IVUBeanBase caller) {
            String simpleName = RepositoryHome.class.getSimpleName();
            return caller.findLocalHome(simpleName.substring(0, simpleName.length() - 4));
         }
      }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Repository create(RepositoryModel details) throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Repository key element of the type {@link String}
     * @param name key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Repository create(String id_Repository, String name) throws CreateException, EJBException;

   /**
     * Searches for existing Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param pkObj Key of the entity
     * @return Key of the entity
     * @throws ObjectNotFoundException if the entity was not found.
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Repository findByPrimaryKey(String pkObj) throws ObjectNotFoundException, FinderException;

   /**
     * Searches for all existing Bean-instances. (Bean-supporting method by EJB specification.)
     *
     * @return Collection of keys of the entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Repository> findAll() throws FinderException;

   /**
     * Returns the set of entities of the type {@link Repository}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Repository}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Repository> findAllByName(String name) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Repository}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Repository}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Repository> findAllLikeName(String name) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Repository}, filtered by wert.
     *
     * @param wert searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Repository}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Repository> findAllByWert(String wert) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Repository}, filtered by wert.
     *
     * @param wert searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Repository}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Repository> findAllLikeWert(String wert) throws FinderException;
}
