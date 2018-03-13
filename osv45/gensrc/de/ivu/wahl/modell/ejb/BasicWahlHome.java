/*
 * BasicWahlHome
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

import de.ivu.wahl.modell.WahlModel;

/**
  * LocalHome interface for the entity Wahl as BMP Entity Bean.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicWahlHome extends EJBLocalHome {

      public static class HomeFinder {
         public static WahlHome findHome(IVUBeanBase caller) {
            String simpleName = WahlHome.class.getSimpleName();
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
   Wahl create(WahlModel details) throws CreateException, EJBException;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Wahl key element of the type {@link String}
     * @param termin key element of the type {@link Timestamp}
     * @param datumNominierung key element of the type {@link Timestamp}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   Wahl create(String id_Wahl, Timestamp termin, Timestamp datumNominierung) throws CreateException, EJBException;

   /**
     * Searches for existing Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param pkObj Key of the entity
     * @return Key of the entity
     * @throws ObjectNotFoundException if the entity was not found.
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Wahl findByPrimaryKey(String pkObj) throws ObjectNotFoundException, FinderException;

   /**
     * Searches for all existing Bean-instances. (Bean-supporting method by EJB specification.)
     *
     * @return Collection of keys of the entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Wahl> findAll() throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Wahlgebiet ID of the objects to be searched
     * @return  {@link Collection} of the found Wahl-entities
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Collection<Wahl> findAllByWahlgebiet(String id_Wahlgebiet) throws FinderException;

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Wurzelgebiet ID of the objects to be searched
     * @return Key of the entity
     * @throws ObjectNotFoundException if the entity was not found.
     * @throws FinderException if an error occurred while searching (does NOT mean "not found".
     */
   Wahl findByWurzelgebiet(String id_Wurzelgebiet) throws FinderException, ObjectNotFoundException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by wahlart.
     *
     * @param wahlart searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByWahlart(int wahlart) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by wahlebene.
     *
     * @param wahlebene searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByWahlebene(int wahlebene) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by wahlkategorie.
     *
     * @param wahlkategorie searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByWahlkategorie(String wahlkategorie) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by wahlkategorie.
     *
     * @param wahlkategorie searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllLikeWahlkategorie(String wahlkategorie) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByName(String name) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllLikeName(String name) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by electionDomain.
     *
     * @param electionDomain searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByElectionDomain(String electionDomain) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by electionDomain.
     *
     * @param electionDomain searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllLikeElectionDomain(String electionDomain) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by electionDomainId.
     *
     * @param electionDomainId searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByElectionDomainId(String electionDomainId) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by electionDomainId.
     *
     * @param electionDomainId searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllLikeElectionDomainId(String electionDomainId) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by termin.
     *
     * @param termin searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByTermin(Timestamp termin) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by vorrangschwelle.
     *
     * @param vorrangschwelle searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByVorrangschwelle(int vorrangschwelle) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by anzahlSitze.
     *
     * @param anzahlSitze searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByAnzahlSitze(int anzahlSitze) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by gebietsartAuswertungseinheit.
     *
     * @param gebietsartAuswertungseinheit searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByGebietsartAuswertungseinheit(int gebietsartAuswertungseinheit) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by gebietsartErfassungseinheit.
     *
     * @param gebietsartErfassungseinheit searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByGebietsartErfassungseinheit(int gebietsartErfassungseinheit) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by aktuelleWahlergebnisart.
     *
     * @param aktuelleWahlergebnisart searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByAktuelleWahlergebnisart(int aktuelleWahlergebnisart) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by datumNominierung.
     *
     * @param datumNominierung searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByDatumNominierung(Timestamp datumNominierung) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by standMetadaten.
     *
     * @param standMetadaten searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByStandMetadaten(Timestamp standMetadaten) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by geschlossenMetadaten.
     *
     * @param geschlossenMetadaten searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByGeschlossenMetadaten(Timestamp geschlossenMetadaten) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by status.
     *
     * @param status searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByStatus(int status) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by freigegeben.
     *
     * @param freigegeben searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByFreigegeben(Timestamp freigegeben) throws FinderException;

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by letzteAenderung.
     *
     * @param letzteAenderung searching condition
     * @return  {@link Collection} of {@link EJBLocalObject}s of the type {@link Wahl}
     * @throws FinderException if an error occurred while searching (does NOT mean "not found").
     */
   Collection<Wahl> findAllByLetzteAenderung(Timestamp letzteAenderung) throws FinderException;
}
