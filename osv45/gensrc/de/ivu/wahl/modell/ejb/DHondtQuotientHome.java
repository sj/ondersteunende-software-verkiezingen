/*
 * DHondtQuotientHome
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

import de.ivu.wahl.modell.DHondtQuotientModel;

/**
  * LocalHome interface for the entity DHondtQuotient as BMP Entity Bean.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface DHondtQuotientHome extends EJBLocalHome {

      public static class HomeFinder {
         public static DHondtQuotientHome findHome(IVUBeanBase caller) {
            String simpleName = DHondtQuotientHome.class.getSimpleName();
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
   DHondtQuotient create(DHondtQuotientModel details) throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param iD_DHondtQuotient key element of the type {@link String}
     * @param id_Restsitzverteilung key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   DHondtQuotient create(String iD_DHondtQuotient, String id_Restsitzverteilung) throws CreateException, EJBException;

   /**
     * Searches for existing Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param pkObj Key of the entity
     * @return Key of the entity
     * @throws ObjectNotFoundException if the entity was not found.
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   DHondtQuotient findByPrimaryKey(String pkObj) throws ObjectNotFoundException, FinderException;

   /**
     * Searches for all existing Bean-instances. (Bean-supporting method by EJB specification.)
     *
     * @return Collection of keys of the entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<DHondtQuotient> findAll() throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Restsitzverteilung ID of the objects to be searched
     * @return  {@link Collection} of the found DHondtQuotient-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<DHondtQuotient> findAllByRestsitzverteilung(String id_Restsitzverteilung) throws FinderException;

   /**
     * Returns the set of entities of the type {@link DHondtQuotient}, filtered by lauf.
     *
     * @param lauf searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link DHondtQuotient}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<DHondtQuotient> findAllByLauf(int lauf) throws FinderException;

   /**
     * Returns the set of entities of the type {@link DHondtQuotient}, filtered by zaehler.
     *
     * @param zaehler searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link DHondtQuotient}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<DHondtQuotient> findAllByZaehler(int zaehler) throws FinderException;

   /**
     * Returns the set of entities of the type {@link DHondtQuotient}, filtered by nenner.
     *
     * @param nenner searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link DHondtQuotient}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<DHondtQuotient> findAllByNenner(int nenner) throws FinderException;

   /**
     * Returns the set of entities of the type {@link DHondtQuotient}, filtered by sitzAusRestanteil.
     *
     * @param sitzAusRestanteil searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link DHondtQuotient}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<DHondtQuotient> findAllBySitzAusRestanteil(boolean sitzAusRestanteil) throws FinderException;
}
