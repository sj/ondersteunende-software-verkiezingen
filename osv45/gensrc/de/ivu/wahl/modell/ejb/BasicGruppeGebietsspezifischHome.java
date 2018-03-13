/*
 * BasicGruppeGebietsspezifischHome
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

import de.ivu.wahl.modell.GruppeGebietsspezifischModel;

/**
  * LocalHome interface for the entity GruppeGebietsspezifisch as BMP Entity Bean.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicGruppeGebietsspezifischHome extends EJBLocalHome {

      public static class HomeFinder {
         public static GruppeGebietsspezifischHome findHome(IVUBeanBase caller) {
            String simpleName = GruppeGebietsspezifischHome.class.getSimpleName();
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
   GruppeGebietsspezifisch create() throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   GruppeGebietsspezifisch create(GruppeGebietsspezifischModel details) throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_GruppeGebietsspezifisch key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   GruppeGebietsspezifisch create(String id_GruppeGebietsspezifisch) throws CreateException, EJBException;

   /**
     * Searches for existing Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param pkObj Key of the entity
     * @return Key of the entity
     * @throws ObjectNotFoundException if the entity was not found.
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   GruppeGebietsspezifisch findByPrimaryKey(String pkObj) throws ObjectNotFoundException, FinderException;

   /**
     * Searches for all existing Bean-instances. (Bean-supporting method by EJB specification.)
     *
     * @return Collection of keys of the entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<GruppeGebietsspezifisch> findAll() throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Liste ID of the objects to be searched
     * @return  {@link Collection} of the found GruppeGebietsspezifisch-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<GruppeGebietsspezifisch> findAllByListe(String id_Liste) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_UebergeordneteGG ID of the objects to be searched
     * @return  {@link Collection} of the found GruppeGebietsspezifisch-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<GruppeGebietsspezifisch> findAllByUebergeordneteGG(String id_UebergeordneteGG) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gebiet ID of the objects to be searched
     * @return  {@link Collection} of the found GruppeGebietsspezifisch-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<GruppeGebietsspezifisch> findAllByGebiet(String id_Gebiet) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gruppe ID of the objects to be searched
     * @return  {@link Collection} of the found GruppeGebietsspezifisch-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<GruppeGebietsspezifisch> findAllByGruppe(String id_Gruppe) throws FinderException;

   /**
     * Returns the set of entities of the type {@link GruppeGebietsspezifisch}, filtered by position.
     *
     * @param position searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link GruppeGebietsspezifisch}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<GruppeGebietsspezifisch> findAllByPosition(int position) throws FinderException;

   /**
     * Returns the set of entities of the type {@link GruppeGebietsspezifisch}, filtered by listeZugelassen.
     *
     * @param listeZugelassen searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link GruppeGebietsspezifisch}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<GruppeGebietsspezifisch> findAllByListeZugelassen(boolean listeZugelassen) throws FinderException;
}
