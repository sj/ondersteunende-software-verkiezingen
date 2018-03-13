/*
 * AlternativeHome
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

import de.ivu.wahl.modell.AlternativeModel;

/**
  * LocalHome interface for the entity Alternative as BMP Entity Bean.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface AlternativeHome extends EJBLocalHome {

      public static class HomeFinder {
         public static AlternativeHome findHome(IVUBeanBase caller) {
            String simpleName = AlternativeHome.class.getSimpleName();
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
   Alternative create(AlternativeModel details) throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Alternative key element of the type {@link String}
     * @param id_Konflikt key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Alternative create(String id_Alternative, String id_Konflikt) throws CreateException, EJBException;

   /**
     * Searches for existing Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param pkObj Key of the entity
     * @return Key of the entity
     * @throws ObjectNotFoundException if the entity was not found.
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Alternative findByPrimaryKey(String pkObj) throws ObjectNotFoundException, FinderException;

   /**
     * Searches for all existing Bean-instances. (Bean-supporting method by EJB specification.)
     *
     * @return Collection of keys of the entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Alternative> findAll() throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Personendaten ID of the objects to be searched
     * @return  {@link Collection} of the found Alternative-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Alternative> findAllByPersonendaten(String id_Personendaten) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Konflikt ID of the objects to be searched
     * @return  {@link Collection} of the found Alternative-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Alternative> findAllByKonflikt(String id_Konflikt) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Listenkombination ID of the objects to be searched
     * @return  {@link Collection} of the found Alternative-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Alternative> findAllByListenkombination(String id_Listenkombination) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gruppe ID of the objects to be searched
     * @return  {@link Collection} of the found Alternative-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Alternative> findAllByGruppe(String id_Gruppe) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Liste ID of the objects to be searched
     * @return  {@link Collection} of the found Alternative-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Alternative> findAllByListe(String id_Liste) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Alternative}, filtered by nummer.
     *
     * @param nummer searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Alternative}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Alternative> findAllByNummer(int nummer) throws FinderException;
}
