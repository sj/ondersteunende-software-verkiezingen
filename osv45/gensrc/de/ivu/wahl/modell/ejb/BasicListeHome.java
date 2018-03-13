/*
 * BasicListeHome
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

import de.ivu.wahl.modell.ListeModel;

/**
  * LocalHome interface for the entity Liste as BMP Entity Bean.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicListeHome extends EJBLocalHome {

      public static class HomeFinder {
         public static ListeHome findHome(IVUBeanBase caller) {
            String simpleName = ListeHome.class.getSimpleName();
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
   Liste create() throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Liste create(ListeModel details) throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Liste key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Liste create(String id_Liste) throws CreateException, EJBException;

   /**
     * Searches for existing Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param pkObj Key of the entity
     * @return Key of the entity
     * @throws ObjectNotFoundException if the entity was not found.
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Liste findByPrimaryKey(String pkObj) throws ObjectNotFoundException, FinderException;

   /**
     * Searches for all existing Bean-instances. (Bean-supporting method by EJB specification.)
     *
     * @return Collection of keys of the entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Liste> findAll() throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gruppe ID of the objects to be searched
     * @return  {@link Collection} of the found Liste-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Liste> findAllByGruppe(String id_Gruppe) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Wahl ID of the objects to be searched
     * @return  {@link Collection} of the found Liste-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Liste> findAllByWahl(String id_Wahl) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Liste}, filtered by typ.
     *
     * @param typ searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Liste}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Liste> findAllByTyp(String typ) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Liste}, filtered by typ.
     *
     * @param typ searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Liste}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Liste> findAllLikeTyp(String typ) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Liste}, filtered by satz.
     *
     * @param satz searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Liste}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Liste> findAllBySatz(int satz) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Liste}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Liste}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Liste> findAllByName(String name) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Liste}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Liste}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Liste> findAllLikeName(String name) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Liste}, filtered by geschlechtSichtbar.
     *
     * @param geschlechtSichtbar searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Liste}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Liste> findAllByGeschlechtSichtbar(boolean geschlechtSichtbar) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Liste}, filtered by publicationLanguage.
     *
     * @param publicationLanguage searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Liste}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Liste> findAllByPublicationLanguage(String publicationLanguage) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Liste}, filtered by publicationLanguage.
     *
     * @param publicationLanguage searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Liste}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Liste> findAllLikePublicationLanguage(String publicationLanguage) throws FinderException;
}
