/*
 * BasicSitzberechnungErgebnisHome
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

import de.ivu.wahl.modell.SitzberechnungErgebnisModel;

/**
  * LocalHome interface for the entity SitzberechnungErgebnis as BMP Entity Bean.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicSitzberechnungErgebnisHome extends EJBLocalHome {

      public static class HomeFinder {
         public static SitzberechnungErgebnisHome findHome(IVUBeanBase caller) {
            String simpleName = SitzberechnungErgebnisHome.class.getSimpleName();
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
   SitzberechnungErgebnis create(SitzberechnungErgebnisModel details) throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_SitzberechnungErgebnis key element of the type {@link String}
     * @param id_Ergebniseingang key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   SitzberechnungErgebnis create(String id_SitzberechnungErgebnis, String id_Ergebniseingang) throws CreateException, EJBException;

   /**
     * Searches for existing Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param pkObj Key of the entity
     * @return Key of the entity
     * @throws ObjectNotFoundException if the entity was not found.
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   SitzberechnungErgebnis findByPrimaryKey(String pkObj) throws ObjectNotFoundException, FinderException;

   /**
     * Searches for all existing Bean-instances. (Bean-supporting method by EJB specification.)
     *
     * @return Collection of keys of the entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<SitzberechnungErgebnis> findAll() throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Listenkombination ID of the objects to be searched
     * @return  {@link Collection} of the found SitzberechnungErgebnis-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<SitzberechnungErgebnis> findAllByListenkombination(String id_Listenkombination) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Ergebniseingang ID of the objects to be searched
     * @return  {@link Collection} of the found SitzberechnungErgebnis-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<SitzberechnungErgebnis> findAllByErgebniseingang(String id_Ergebniseingang) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Liste ID of the objects to be searched
     * @return  {@link Collection} of the found SitzberechnungErgebnis-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<SitzberechnungErgebnis> findAllByListe(String id_Liste) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gruppe ID of the objects to be searched
     * @return  {@link Collection} of the found SitzberechnungErgebnis-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<SitzberechnungErgebnis> findAllByGruppe(String id_Gruppe) throws FinderException;

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by verteilung.
     *
     * @param verteilung searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link SitzberechnungErgebnis}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<SitzberechnungErgebnis> findAllByVerteilung(int verteilung) throws FinderException;

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by schrittnummer.
     *
     * @param schrittnummer searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link SitzberechnungErgebnis}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<SitzberechnungErgebnis> findAllBySchrittnummer(int schrittnummer) throws FinderException;

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by schritttyp.
     *
     * @param schritttyp searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link SitzberechnungErgebnis}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<SitzberechnungErgebnis> findAllBySchritttyp(int schritttyp) throws FinderException;

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by sitze.
     *
     * @param sitze searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link SitzberechnungErgebnis}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<SitzberechnungErgebnis> findAllBySitze(int sitze) throws FinderException;

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by zaehler.
     *
     * @param zaehler searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link SitzberechnungErgebnis}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<SitzberechnungErgebnis> findAllByZaehler(int zaehler) throws FinderException;

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by nenner.
     *
     * @param nenner searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link SitzberechnungErgebnis}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<SitzberechnungErgebnis> findAllByNenner(int nenner) throws FinderException;

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by zaehlerVomNenner.
     *
     * @param zaehlerVomNenner searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link SitzberechnungErgebnis}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<SitzberechnungErgebnis> findAllByZaehlerVomNenner(int zaehlerVomNenner) throws FinderException;

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by nennerVomNenner.
     *
     * @param nennerVomNenner searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link SitzberechnungErgebnis}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<SitzberechnungErgebnis> findAllByNennerVomNenner(int nennerVomNenner) throws FinderException;

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by zaehlerVomRest.
     *
     * @param zaehlerVomRest searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link SitzberechnungErgebnis}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<SitzberechnungErgebnis> findAllByZaehlerVomRest(int zaehlerVomRest) throws FinderException;

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by nennerVomRest.
     *
     * @param nennerVomRest searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link SitzberechnungErgebnis}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<SitzberechnungErgebnis> findAllByNennerVomRest(int nennerVomRest) throws FinderException;

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by losentscheid.
     *
     * @param losentscheid searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link SitzberechnungErgebnis}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<SitzberechnungErgebnis> findAllByLosentscheid(boolean losentscheid) throws FinderException;
}
