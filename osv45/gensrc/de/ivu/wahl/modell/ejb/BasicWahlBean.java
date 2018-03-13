/*
 * BasicWahlBean
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.ejb.bmp.BMPBeanBase;
import de.ivu.ejb.bmp.Model;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.modell.WahlModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Wahl as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicWahlBean extends BMPBeanBase implements EntityBean, WahlModel {
   private static final Category LOGGER = Log4J.configure(BasicWahlBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicWahlBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected WahlModel _details = null;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(WahlModel details) throws CreateException, EJBException {
      WahlModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Wahl();
   }

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
   public String ejbCreate(String id_Wahl, Timestamp termin, Timestamp datumNominierung) throws CreateException, EJBException {
      _details = (WahlModel)createModel(id_Wahl);
      _details.setWahlkategorie(""); //$NON-NLS-1$
      _details.setName(""); //$NON-NLS-1$
      _details.setElectionDomain(""); //$NON-NLS-1$
      _details.setElectionDomainId(""); //$NON-NLS-1$
      _details.setTermin(termin);
      _details.setDatumNominierung(datumNominierung);
      create(_details);
      return id_Wahl;
   }

   /**
     * Searches for existing Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param pkObj Key of the entity
     * @return Key of the entity
     * @throws ObjectNotFoundException if the entity was not found.
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public String ejbFindByPrimaryKey(String pkObj) throws ObjectNotFoundException, IVUFinderException {
      find(pkObj);
      return pkObj;
   }

   /**
     * Searches for all existing Bean-instances. (Bean-supporting method by EJB specification.)
     *
     * @return Collection of keys of the entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAll() throws IVUFinderException {
      try {
         return WahlDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Wahlgebiet ID of the objects to be searched
     * @return  {@link Collection} of the found Wahl-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByWahlgebiet(String id_Wahlgebiet) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByID_Wahlgebiet(id_Wahlgebiet);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Wurzelgebiet ID of the objects to be searched
     * @return Key of the entity
     * @throws ObjectNotFoundException if the entity was not found.
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public String ejbFindByWurzelgebiet(String id_Wurzelgebiet) throws IVUFinderException, ObjectNotFoundException {
      try {
         return findSingle(WahlDBA.retrieveIDsByID_Wurzelgebiet(id_Wurzelgebiet));
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by wahlart.
     *
     * @param wahlart searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByWahlart(int wahlart) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByWahlart(wahlart);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by wahlebene.
     *
     * @param wahlebene searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByWahlebene(int wahlebene) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByWahlebene(wahlebene);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by wahlkategorie.
     *
     * @param wahlkategorie searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByWahlkategorie(String wahlkategorie) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByWahlkategorie(wahlkategorie);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by wahlkategorie.
     *
     * @param wahlkategorie searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeWahlkategorie(String wahlkategorie) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsLikeWahlkategorie(wahlkategorie);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByName(String name) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByName(name);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeName(String name) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsLikeName(name);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by electionDomain.
     *
     * @param electionDomain searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByElectionDomain(String electionDomain) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByElectionDomain(electionDomain);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by electionDomain.
     *
     * @param electionDomain searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeElectionDomain(String electionDomain) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsLikeElectionDomain(electionDomain);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by electionDomainId.
     *
     * @param electionDomainId searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByElectionDomainId(String electionDomainId) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByElectionDomainId(electionDomainId);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by electionDomainId.
     *
     * @param electionDomainId searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeElectionDomainId(String electionDomainId) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsLikeElectionDomainId(electionDomainId);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by termin.
     *
     * @param termin searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByTermin(Timestamp termin) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByTermin(termin);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by vorrangschwelle.
     *
     * @param vorrangschwelle searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByVorrangschwelle(int vorrangschwelle) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByVorrangschwelle(vorrangschwelle);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by anzahlSitze.
     *
     * @param anzahlSitze searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByAnzahlSitze(int anzahlSitze) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByAnzahlSitze(anzahlSitze);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by gebietsartAuswertungseinheit.
     *
     * @param gebietsartAuswertungseinheit searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByGebietsartAuswertungseinheit(int gebietsartAuswertungseinheit) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByGebietsartAuswertungseinheit(gebietsartAuswertungseinheit);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by gebietsartErfassungseinheit.
     *
     * @param gebietsartErfassungseinheit searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByGebietsartErfassungseinheit(int gebietsartErfassungseinheit) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByGebietsartErfassungseinheit(gebietsartErfassungseinheit);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by aktuelleWahlergebnisart.
     *
     * @param aktuelleWahlergebnisart searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByAktuelleWahlergebnisart(int aktuelleWahlergebnisart) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByAktuelleWahlergebnisart(aktuelleWahlergebnisart);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by datumNominierung.
     *
     * @param datumNominierung searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByDatumNominierung(Timestamp datumNominierung) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByDatumNominierung(datumNominierung);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by standMetadaten.
     *
     * @param standMetadaten searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByStandMetadaten(Timestamp standMetadaten) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByStandMetadaten(standMetadaten);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by geschlossenMetadaten.
     *
     * @param geschlossenMetadaten searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByGeschlossenMetadaten(Timestamp geschlossenMetadaten) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByGeschlossenMetadaten(geschlossenMetadaten);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by status.
     *
     * @param status searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByStatus(int status) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByStatus(status);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by freigegeben.
     *
     * @param freigegeben searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByFreigegeben(Timestamp freigegeben) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByFreigegeben(freigegeben);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Wahl}, filtered by letzteAenderung.
     *
     * @param letzteAenderung searching condition
     * @return  {@link Collection} of primary keys of the entities Wahl
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByLetzteAenderung(Timestamp letzteAenderung) throws IVUFinderException {
      try {
         return WahlDBA.retrieveIDsByLetzteAenderung(letzteAenderung);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Wahl key element of the type {@link String}
     * @param termin key element of the type {@link Timestamp}
     * @param datumNominierung key element of the type {@link Timestamp}
     */
   public void ejbPostCreate(String id_Wahl, Timestamp termin, Timestamp datumNominierung) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(WahlModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public WahlModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(WahlModel details) {
      if (details instanceof WahlModelImpl && _details instanceof WahlModelImpl) {
         ((WahlModelImpl)_details).copyFrom((WahlModelImpl)details);
         checkRelations();
      } else {
         setID_Wurzelgebiet(details.getID_Wurzelgebiet());
         setID_Wahlgebiet(details.getID_Wahlgebiet());
         setWahlart(details.getWahlart());
         setWahlebene(details.getWahlebene());
         setWahlkategorie(details.getWahlkategorie());
         setName(details.getName());
         setElectionDomain(details.getElectionDomain());
         setElectionDomainId(details.getElectionDomainId());
         setTermin(details.getTermin());
         setVorrangschwelle(details.getVorrangschwelle());
         setAnzahlSitze(details.getAnzahlSitze());
         setGebietsartAuswertungseinheit(details.getGebietsartAuswertungseinheit());
         setGebietsartErfassungseinheit(details.getGebietsartErfassungseinheit());
         setAktuelleWahlergebnisart(details.getAktuelleWahlergebnisart());
         setDatumNominierung(details.getDatumNominierung());
         setStandMetadaten(details.getStandMetadaten());
         setGeschlossenMetadaten(details.getGeschlossenMetadaten());
         setStatus(details.getStatus());
         setFreigegeben(details.getFreigegeben());
         setLetzteAenderung(details.getLetzteAenderung());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (WahlModel)details;
      if (details != null) {
         checkRelations();
      }
      return _details;
   }

   @Override
   protected Model getDetailsInternal () {
      return _details;
   }

   @Override
   protected void checkRelations() {
      if (null == _details.getID_Wahlgebiet()) {
         _wahlgebiet = null;
         _relchk_Wahlgebiet = true;
      } else {
         _relchk_Wahlgebiet = false;
      }
      if (null == _details.getID_Wurzelgebiet()) {
         _wurzelgebiet = null;
         _relchk_Wurzelgebiet = true;
      } else {
         _relchk_Wurzelgebiet = false;
      }
   }

   @Override
   protected void resetRelations() {
      _wahlgebiet = null;
      _wurzelgebiet = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public WahlModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Wahl in the entity Wahl
     *
     * @return value of the attribute id_Wahl
     */
   public String getID_Wahl() {
      return _details.getID_Wahl();
   }

   /**
     * Sets the value of id_Wurzelgebiet in the entity Wahl
     *
     * @param id_Wurzelgebiet new value of the attribute id_Wurzelgebiet
     */
   public void setID_Wurzelgebiet(String id_Wurzelgebiet) {
      if (null == id_Wurzelgebiet) {
         _wurzelgebiet = null;
         _relchk_Wurzelgebiet = true;
      } else {
         String old = _details.getID_Wurzelgebiet();
         if (old == null || !old.equals(id_Wurzelgebiet)) {
            _relchk_Wurzelgebiet = false;
         }
      }
      _details.setID_Wurzelgebiet(id_Wurzelgebiet);
   }

   /**
     * Gets the value of id_Wurzelgebiet in the entity Wahl
     *
     * @return value of the attribute id_Wurzelgebiet
     */
   public String getID_Wurzelgebiet() {
      return _details.getID_Wurzelgebiet();
   }

   /**
     * Sets the value of id_Wahlgebiet in the entity Wahl
     *
     * @param id_Wahlgebiet new value of the attribute id_Wahlgebiet
     */
   public void setID_Wahlgebiet(String id_Wahlgebiet) {
      if (null == id_Wahlgebiet) {
         _wahlgebiet = null;
         _relchk_Wahlgebiet = true;
      } else {
         String old = _details.getID_Wahlgebiet();
         if (old == null || !old.equals(id_Wahlgebiet)) {
            _relchk_Wahlgebiet = false;
         }
      }
      _details.setID_Wahlgebiet(id_Wahlgebiet);
   }

   /**
     * Gets the value of id_Wahlgebiet in the entity Wahl
     *
     * @return value of the attribute id_Wahlgebiet
     */
   public String getID_Wahlgebiet() {
      return _details.getID_Wahlgebiet();
   }

   /**
     * Sets the value of wahlart in the entity Wahl
     *
     * @param wahlart new value of the attribute wahlart
     */
   public void setWahlart(int wahlart) {
      _details.setWahlart(wahlart);
   }

   /**
     * Gets the value of wahlart in the entity Wahl
     *
     * @return value of the attribute wahlart
     */
   public int getWahlart() {
      return _details.getWahlart();
   }

   /**
     * Sets the value of wahlebene in the entity Wahl
     *
     * @param wahlebene new value of the attribute wahlebene
     */
   public void setWahlebene(int wahlebene) {
      _details.setWahlebene(wahlebene);
   }

   /**
     * Gets the value of wahlebene in the entity Wahl
     *
     * @return value of the attribute wahlebene
     */
   public int getWahlebene() {
      return _details.getWahlebene();
   }

   /**
     * Sets the value of wahlkategorie in the entity Wahl
     *
     * @param wahlkategorie new value of the attribute wahlkategorie
     */
   public void setWahlkategorie(String wahlkategorie) {
      _details.setWahlkategorie(wahlkategorie);
   }

   /**
     * Gets the value of wahlkategorie in the entity Wahl
     *
     * @return value of the attribute wahlkategorie
     */
   public String getWahlkategorie() {
      return _details.getWahlkategorie();
   }

   /**
     * Sets the value of name in the entity Wahl
     *
     * @param name new value of the attribute name
     */
   public void setName(String name) {
      _details.setName(name);
   }

   /**
     * Gets the value of name in the entity Wahl
     *
     * @return value of the attribute name
     */
   public String getName() {
      return _details.getName();
   }

   /**
     * Sets the value of electionDomain in the entity Wahl
     *
     * @param electionDomain new value of the attribute electionDomain
     */
   public void setElectionDomain(String electionDomain) {
      _details.setElectionDomain(electionDomain);
   }

   /**
     * Gets the value of electionDomain in the entity Wahl
     *
     * @return value of the attribute electionDomain
     */
   public String getElectionDomain() {
      return _details.getElectionDomain();
   }

   /**
     * Sets the value of electionDomainId in the entity Wahl
     *
     * @param electionDomainId new value of the attribute electionDomainId
     */
   public void setElectionDomainId(String electionDomainId) {
      _details.setElectionDomainId(electionDomainId);
   }

   /**
     * Gets the value of electionDomainId in the entity Wahl
     *
     * @return value of the attribute electionDomainId
     */
   public String getElectionDomainId() {
      return _details.getElectionDomainId();
   }

   /**
     * Sets the value of termin in the entity Wahl
     *
     * @param termin new value of the attribute termin
     */
   public void setTermin(Timestamp termin) {
      _details.setTermin(termin);
   }

   /**
     * Gets the value of termin in the entity Wahl
     *
     * @return value of the attribute termin
     */
   public Timestamp getTermin() {
      return _details.getTermin();
   }

   /**
     * Sets the value of vorrangschwelle in the entity Wahl
     *
     * @param vorrangschwelle new value of the attribute vorrangschwelle
     */
   public void setVorrangschwelle(int vorrangschwelle) {
      _details.setVorrangschwelle(vorrangschwelle);
   }

   /**
     * Gets the value of vorrangschwelle in the entity Wahl
     *
     * @return value of the attribute vorrangschwelle
     */
   public int getVorrangschwelle() {
      return _details.getVorrangschwelle();
   }

   /**
     * Sets the value of anzahlSitze in the entity Wahl
     *
     * @param anzahlSitze new value of the attribute anzahlSitze
     */
   public void setAnzahlSitze(int anzahlSitze) {
      _details.setAnzahlSitze(anzahlSitze);
   }

   /**
     * Gets the value of anzahlSitze in the entity Wahl
     *
     * @return value of the attribute anzahlSitze
     */
   public int getAnzahlSitze() {
      return _details.getAnzahlSitze();
   }

   /**
     * Sets the value of gebietsartAuswertungseinheit in the entity Wahl
     *
     * @param gebietsartAuswertungseinheit new value of the attribute gebietsartAuswertungseinheit
     */
   public void setGebietsartAuswertungseinheit(int gebietsartAuswertungseinheit) {
      _details.setGebietsartAuswertungseinheit(gebietsartAuswertungseinheit);
   }

   /**
     * Gets the value of gebietsartAuswertungseinheit in the entity Wahl
     *
     * @return value of the attribute gebietsartAuswertungseinheit
     */
   public int getGebietsartAuswertungseinheit() {
      return _details.getGebietsartAuswertungseinheit();
   }

   /**
     * Sets the value of gebietsartErfassungseinheit in the entity Wahl
     *
     * @param gebietsartErfassungseinheit new value of the attribute gebietsartErfassungseinheit
     */
   public void setGebietsartErfassungseinheit(int gebietsartErfassungseinheit) {
      _details.setGebietsartErfassungseinheit(gebietsartErfassungseinheit);
   }

   /**
     * Gets the value of gebietsartErfassungseinheit in the entity Wahl
     *
     * @return value of the attribute gebietsartErfassungseinheit
     */
   public int getGebietsartErfassungseinheit() {
      return _details.getGebietsartErfassungseinheit();
   }

   /**
     * Sets the value of aktuelleWahlergebnisart in the entity Wahl
     *
     * @param aktuelleWahlergebnisart new value of the attribute aktuelleWahlergebnisart
     */
   public void setAktuelleWahlergebnisart(int aktuelleWahlergebnisart) {
      _details.setAktuelleWahlergebnisart(aktuelleWahlergebnisart);
   }

   /**
     * Gets the value of aktuelleWahlergebnisart in the entity Wahl
     *
     * @return value of the attribute aktuelleWahlergebnisart
     */
   public int getAktuelleWahlergebnisart() {
      return _details.getAktuelleWahlergebnisart();
   }

   /**
     * Sets the value of datumNominierung in the entity Wahl
     *
     * @param datumNominierung new value of the attribute datumNominierung
     */
   public void setDatumNominierung(Timestamp datumNominierung) {
      _details.setDatumNominierung(datumNominierung);
   }

   /**
     * Gets the value of datumNominierung in the entity Wahl
     *
     * @return value of the attribute datumNominierung
     */
   public Timestamp getDatumNominierung() {
      return _details.getDatumNominierung();
   }

   /**
     * Sets the value of standMetadaten in the entity Wahl
     *
     * @param standMetadaten new value of the attribute standMetadaten
     */
   public void setStandMetadaten(Timestamp standMetadaten) {
      _details.setStandMetadaten(standMetadaten);
   }

   /**
     * Gets the value of standMetadaten in the entity Wahl
     *
     * @return value of the attribute standMetadaten
     */
   public Timestamp getStandMetadaten() {
      return _details.getStandMetadaten();
   }

   /**
     * Sets the value of geschlossenMetadaten in the entity Wahl
     *
     * @param geschlossenMetadaten new value of the attribute geschlossenMetadaten
     */
   public void setGeschlossenMetadaten(Timestamp geschlossenMetadaten) {
      _details.setGeschlossenMetadaten(geschlossenMetadaten);
   }

   /**
     * Gets the value of geschlossenMetadaten in the entity Wahl
     *
     * @return value of the attribute geschlossenMetadaten
     */
   public Timestamp getGeschlossenMetadaten() {
      return _details.getGeschlossenMetadaten();
   }

   /**
     * Sets the value of status in the entity Wahl
     *
     * @param status new value of the attribute status
     */
   public void setStatus(int status) {
      _details.setStatus(status);
   }

   /**
     * Gets the value of status in the entity Wahl
     *
     * @return value of the attribute status
     */
   public int getStatus() {
      return _details.getStatus();
   }

   /**
     * Sets the value of freigegeben in the entity Wahl
     *
     * @param freigegeben new value of the attribute freigegeben
     */
   public void setFreigegeben(Timestamp freigegeben) {
      _details.setFreigegeben(freigegeben);
   }

   /**
     * Gets the value of freigegeben in the entity Wahl
     *
     * @return value of the attribute freigegeben
     */
   public Timestamp getFreigegeben() {
      return _details.getFreigegeben();
   }

   /**
     * Sets the value of letzteAenderung in the entity Wahl
     *
     * @param letzteAenderung new value of the attribute letzteAenderung
     */
   public void setLetzteAenderung(Timestamp letzteAenderung) {
      _details.setLetzteAenderung(letzteAenderung);
   }

   /**
     * Gets the value of letzteAenderung in the entity Wahl
     *
     * @return value of the attribute letzteAenderung
     */
   public Timestamp getLetzteAenderung() {
      return _details.getLetzteAenderung();
   }

   /**
     * Relation zu Wahlgebiet
     */
   protected Gebiet _wahlgebiet;

   /**
     * Flag for the validity of the relation Wahlgebiet
     */
   protected boolean _relchk_Wahlgebiet = false;

   /**
     * Navigation to the associated entity of the type {@link Gebiet}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Gebiet getWahlgebiet() throws EJBException {
      if (!_relchk_Wahlgebiet) {
         if (null == _details.getID_Wahlgebiet()) {
            _wahlgebiet = null;
         } else if (null == _wahlgebiet || !_wahlgebiet.getPrimaryKey().equals(_details.getID_Wahlgebiet())) {
            try {
               GebietHome home = GebietHome.HomeFinder.findHome(this);
               _wahlgebiet = home.findByPrimaryKey(_details.getID_Wahlgebiet());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find Wahlgebiet", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Gebiet", fe); //$NON-NLS-1$
            }
         }
         _relchk_Wahlgebiet = true;
      }
      return _wahlgebiet;
   }

   /**
     * Setting of the associated entity of the type {@link Gebiet}
     *
     * @param wahlgebiet the corresponding EJBObject
     */
   public void setWahlgebiet(Gebiet wahlgebiet) {
      _wahlgebiet = wahlgebiet;
      _details.setID_Wahlgebiet(wahlgebiet == null ? null : (String)wahlgebiet.getPrimaryKey());
   }

   /**
     * Relation zu Wurzelgebiet
     */
   protected Gebiet _wurzelgebiet;

   /**
     * Flag for the validity of the relation Wurzelgebiet
     */
   protected boolean _relchk_Wurzelgebiet = false;

   /**
     * Navigation to the associated entity of the type {@link Gebiet}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Gebiet getWurzelgebiet() throws EJBException {
      if (!_relchk_Wurzelgebiet) {
         if (null == _details.getID_Wurzelgebiet()) {
            _wurzelgebiet = null;
         } else if (null == _wurzelgebiet || !_wurzelgebiet.getPrimaryKey().equals(_details.getID_Wurzelgebiet())) {
            try {
               GebietHome home = GebietHome.HomeFinder.findHome(this);
               _wurzelgebiet = home.findByPrimaryKey(_details.getID_Wurzelgebiet());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find Wurzelgebiet", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Gebiet", fe); //$NON-NLS-1$
            }
         }
         _relchk_Wurzelgebiet = true;
      }
      return _wurzelgebiet;
   }

   /**
     * Setting of the associated entity of the type {@link Gebiet}
     *
     * @param wurzelgebiet the corresponding EJBObject
     */
   public void setWurzelgebiet(Gebiet wurzelgebiet) {
      _wurzelgebiet = wurzelgebiet;
      _details.setID_Wurzelgebiet(wurzelgebiet == null ? null : (String)wurzelgebiet.getPrimaryKey());
   }

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Ergebniseingang}
     * @throws EJBException: an error occurred
     */
   public Collection<Ergebniseingang> getErgebniseingangCol() throws EJBException {
      ErgebniseingangHome ergebniseingangHome = ErgebniseingangHome.HomeFinder.findHome(this);
      try {
         return ergebniseingangHome.findAllByWahl(_details.getID_Wahl());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Ergebniseingang}.
     *
     * @param ergebniseingang Ergebniseingang-object
     */
   public void addErgebniseingang(Ergebniseingang ergebniseingang) {
      ergebniseingang.setID_Wahl(_details.getID_Wahl());
   }

   /**
     * Adds the object to the set of entities of the type {@link Ergebniseingang}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllErgebniseingangCol(Collection<Ergebniseingang> col) {
      for (Ergebniseingang ergebniseingang : col) {
         addErgebniseingang(ergebniseingang);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Ergebniseingang}.
     *
     * @param ergebniseingang Ergebniseingang-EJBObject, which is removed from the set.
     */
   public void removeErgebniseingang(Ergebniseingang ergebniseingang) {
      ergebniseingang.setID_Wahl(null);
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Gebiet}
     * @throws EJBException: an error occurred
     */
   public Collection<Gebiet> getGebietCol() throws EJBException {
      GebietHome gebietHome = GebietHome.HomeFinder.findHome(this);
      try {
         return gebietHome.findAllByWahl(_details.getID_Wahl());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Gebiet}.
     *
     * @param gebiet Gebiet-object
     */
   public void addGebiet(Gebiet gebiet) {
      gebiet.setID_Wahl(_details.getID_Wahl());
   }

   /**
     * Adds the object to the set of entities of the type {@link Gebiet}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllGebietCol(Collection<Gebiet> col) {
      for (Gebiet gebiet : col) {
         addGebiet(gebiet);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Gebiet}.
     *
     * @param gebiet Gebiet-EJBObject, which is removed from the set.
     */
   public void removeGebiet(Gebiet gebiet) {
      gebiet.setID_Wahl(null);
   }

   /**
     * Returns the set of entities of the type {@link Gruppe}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Gruppe}
     * @throws EJBException: an error occurred
     */
   public Collection<Gruppe> getGruppeCol() throws EJBException {
      GruppeHome gruppeHome = GruppeHome.HomeFinder.findHome(this);
      try {
         return gruppeHome.findAllByWahl(_details.getID_Wahl());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Gruppe}.
     *
     * @param gruppe Gruppe-object
     */
   public void addGruppe(Gruppe gruppe) {
      gruppe.setID_Wahl(_details.getID_Wahl());
   }

   /**
     * Adds the object to the set of entities of the type {@link Gruppe}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllGruppeCol(Collection<Gruppe> col) {
      for (Gruppe gruppe : col) {
         addGruppe(gruppe);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Gruppe}.
     *
     * @param gruppe Gruppe-EJBObject, which is removed from the set.
     */
   public void removeGruppe(Gruppe gruppe) {
      gruppe.setID_Wahl(null);
   }

   /**
     * Returns the set of entities of the type {@link Liste}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Liste}
     * @throws EJBException: an error occurred
     */
   public Collection<Liste> getListeCol() throws EJBException {
      ListeHome listeHome = ListeHome.HomeFinder.findHome(this);
      try {
         return listeHome.findAllByWahl(_details.getID_Wahl());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Liste}.
     *
     * @param liste Liste-object
     */
   public void addListe(Liste liste) {
      liste.setID_Wahl(_details.getID_Wahl());
   }

   /**
     * Adds the object to the set of entities of the type {@link Liste}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllListeCol(Collection<Liste> col) {
      for (Liste liste : col) {
         addListe(liste);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Liste}.
     *
     * @param liste Liste-EJBObject, which is removed from the set.
     */
   public void removeListe(Liste liste) {
      liste.setID_Wahl(null);
   }

   /**
     * Returns the set of entities of the type {@link Listenkandidatur}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Listenkandidatur}
     * @throws EJBException: an error occurred
     */
   public Collection<Listenkandidatur> getListenkandidaturCol() throws EJBException {
      ListenkandidaturHome listenkandidaturHome = ListenkandidaturHome.HomeFinder.findHome(this);
      try {
         return listenkandidaturHome.findAllByWahl(_details.getID_Wahl());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Listenkandidatur}.
     *
     * @param listenkandidatur Listenkandidatur-object
     */
   public void addListenkandidatur(Listenkandidatur listenkandidatur) {
      listenkandidatur.setID_Wahl(_details.getID_Wahl());
   }

   /**
     * Adds the object to the set of entities of the type {@link Listenkandidatur}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllListenkandidaturCol(Collection<Listenkandidatur> col) {
      for (Listenkandidatur listenkandidatur : col) {
         addListenkandidatur(listenkandidatur);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Listenkandidatur}.
     *
     * @param listenkandidatur Listenkandidatur-EJBObject, which is removed from the set.
     */
   public void removeListenkandidatur(Listenkandidatur listenkandidatur) {
      listenkandidatur.setID_Wahl(null);
   }

   /**
     * Returns the set of entities of the type {@link Listenkombination}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Listenkombination}
     * @throws EJBException: an error occurred
     */
   public Collection<Listenkombination> getListenkombinationCol() throws EJBException {
      ListenkombinationHome listenkombinationHome = ListenkombinationHome.HomeFinder.findHome(this);
      try {
         return listenkombinationHome.findAllByWahl(_details.getID_Wahl());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Listenkombination}.
     *
     * @param listenkombination Listenkombination-object
     */
   public void addListenkombination(Listenkombination listenkombination) {
      listenkombination.setID_Wahl(_details.getID_Wahl());
   }

   /**
     * Adds the object to the set of entities of the type {@link Listenkombination}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllListenkombinationCol(Collection<Listenkombination> col) {
      for (Listenkombination listenkombination : col) {
         addListenkombination(listenkombination);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Listenkombination}.
     *
     * @param listenkombination Listenkombination-EJBObject, which is removed from the set.
     */
   public void removeListenkombination(Listenkombination listenkombination) {
      listenkombination.setID_Wahl(null);
   }

   /**
     * Returns the set of entities of the type {@link Schwellwert}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Schwellwert}
     * @throws EJBException: an error occurred
     */
   public Collection<Schwellwert> getSchwellwertCol() throws EJBException {
      SchwellwertHome schwellwertHome = SchwellwertHome.HomeFinder.findHome(this);
      try {
         return schwellwertHome.findAllByWahl(_details.getID_Wahl());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Schwellwert}.
     *
     * @param schwellwert Schwellwert-object
     */
   public void addSchwellwert(Schwellwert schwellwert) {
      schwellwert.setID_Wahl(_details.getID_Wahl());
   }

   /**
     * Adds the object to the set of entities of the type {@link Schwellwert}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllSchwellwertCol(Collection<Schwellwert> col) {
      for (Schwellwert schwellwert : col) {
         addSchwellwert(schwellwert);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Schwellwert}.
     *
     * @param schwellwert Schwellwert-EJBObject, which is removed from the set.
     */
   public void removeSchwellwert(Schwellwert schwellwert) {
      schwellwert.setID_Wahl(null);
   }

   /**
     * Overwrites the toString method in Object
     *
     * @return string representation of the current state as listing of the attributes and their values
     */
   @Override
   public String toString() {
      if (_details == null) {
         return super.toString() + ": Bean is passive"; //$NON-NLS-1$
      }
      try {
         String string = "[" + getClass().getName()+ ": "; //$NON-NLS-1$ //$NON-NLS-2$
         if (_details.getID_Wahl() != null) {
            string += "id_Wahl = " + _details.getID_Wahl(); //$NON-NLS-1$
         }
         if (_details.getID_Wurzelgebiet() != null) {
            string +=  ", id_Wurzelgebiet = " + _details.getID_Wurzelgebiet(); //$NON-NLS-1$
         }
         if (_details.getID_Wahlgebiet() != null) {
            string +=  ", id_Wahlgebiet = " + _details.getID_Wahlgebiet(); //$NON-NLS-1$
         }
         string +=  ", wahlart = " + _details.getWahlart(); //$NON-NLS-1$
         string +=  ", wahlebene = " + _details.getWahlebene(); //$NON-NLS-1$
         if (_details.getWahlkategorie() != null) {
            string +=  ", wahlkategorie = " + _details.getWahlkategorie(); //$NON-NLS-1$
         }
         if (_details.getName() != null) {
            string +=  ", name = " + _details.getName(); //$NON-NLS-1$
         }
         if (_details.getElectionDomain() != null) {
            string +=  ", electionDomain = " + _details.getElectionDomain(); //$NON-NLS-1$
         }
         if (_details.getElectionDomainId() != null) {
            string +=  ", electionDomainId = " + _details.getElectionDomainId(); //$NON-NLS-1$
         }
         if (_details.getTermin() != null) {
            string +=  ", termin = " + _details.getTermin(); //$NON-NLS-1$
         }
         string +=  ", vorrangschwelle = " + _details.getVorrangschwelle(); //$NON-NLS-1$
         string +=  ", anzahlSitze = " + _details.getAnzahlSitze(); //$NON-NLS-1$
         string +=  ", gebietsartAuswertungseinheit = " + _details.getGebietsartAuswertungseinheit(); //$NON-NLS-1$
         string +=  ", gebietsartErfassungseinheit = " + _details.getGebietsartErfassungseinheit(); //$NON-NLS-1$
         string +=  ", aktuelleWahlergebnisart = " + _details.getAktuelleWahlergebnisart(); //$NON-NLS-1$
         if (_details.getDatumNominierung() != null) {
            string +=  ", datumNominierung = " + _details.getDatumNominierung(); //$NON-NLS-1$
         }
         if (_details.getStandMetadaten() != null) {
            string +=  ", standMetadaten = " + _details.getStandMetadaten(); //$NON-NLS-1$
         }
         if (_details.getGeschlossenMetadaten() != null) {
            string +=  ", geschlossenMetadaten = " + _details.getGeschlossenMetadaten(); //$NON-NLS-1$
         }
         string +=  ", status = " + _details.getStatus(); //$NON-NLS-1$
         if (_details.getFreigegeben() != null) {
            string +=  ", freigegeben = " + _details.getFreigegeben(); //$NON-NLS-1$
         }
         if (_details.getLetzteAenderung() != null) {
            string +=  ", letzteAenderung = " + _details.getLetzteAenderung(); //$NON-NLS-1$
         }
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
