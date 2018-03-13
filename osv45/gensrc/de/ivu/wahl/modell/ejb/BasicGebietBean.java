/*
 * BasicGebietBean
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;
import java.sql.SQLException;
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
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Gebiet as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicGebietBean extends BMPBeanBase implements EntityBean, GebietModel {
   private static final Category LOGGER = Log4J.configure(BasicGebietBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicGebietBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected GebietModel _details = null;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate() throws CreateException, EJBException {
      return ejbCreate(getUniqueKey());
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(GebietModel details) throws CreateException, EJBException {
      GebietModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Gebiet();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Gebiet key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Gebiet) throws CreateException, EJBException {
      _details = (GebietModel)createModel(id_Gebiet);
      _details.setName(""); //$NON-NLS-1$
      _details.setKuerzel(""); //$NON-NLS-1$
      _details.setZipcode(""); //$NON-NLS-1$
      create(_details);
      return id_Gebiet;
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
         return GebietDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Ergebniseingang ID of the objects to be searched
     * @return  {@link Collection} of the found Gebiet-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByErgebniseingang(String id_Ergebniseingang) throws IVUFinderException {
      try {
         return Gebiet_ErgebniseingangDBA.retrieveIDsByID_Ergebniseingang(id_Ergebniseingang);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Untergebiet ID of the objects to be searched
     * @return  {@link Collection} of the found Gebiet-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByUntergebiet(String id_Untergebiet) throws IVUFinderException {
      try {
         return Gebiet_GebietDBA.retrieveIDsByID_Untergebiet(id_Untergebiet);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Elterngebiet ID of the objects to be searched
     * @return  {@link Collection} of the found Gebiet-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByElterngebiet(String id_Elterngebiet) throws IVUFinderException {
      try {
         return Gebiet_GebietDBA.retrieveIDsByID_Elterngebiet(id_Elterngebiet);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_LetzterEingang ID of the objects to be searched
     * @return  {@link Collection} of the found Gebiet-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByLetzterEingang(String id_LetzterEingang) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsByID_LetzterEingang(id_LetzterEingang);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_UebergeordnetesGebiet ID of the objects to be searched
     * @return  {@link Collection} of the found Gebiet-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByUebergeordnetesGebiet(String id_UebergeordnetesGebiet) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsByID_UebergeordnetesGebiet(id_UebergeordnetesGebiet);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Wahl ID of the objects to be searched
     * @return  {@link Collection} of the found Gebiet-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByWahl(String id_Wahl) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsByID_Wahl(id_Wahl);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by erfassungseinheit.
     *
     * @param erfassungseinheit searching condition
     * @return  {@link Collection} of primary keys of the entities Gebiet
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByErfassungseinheit(boolean erfassungseinheit) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsByErfassungseinheit(erfassungseinheit);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by wahleinheit.
     *
     * @param wahleinheit searching condition
     * @return  {@link Collection} of primary keys of the entities Gebiet
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByWahleinheit(boolean wahleinheit) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsByWahleinheit(wahleinheit);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by gebietsart.
     *
     * @param gebietsart searching condition
     * @return  {@link Collection} of primary keys of the entities Gebiet
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByGebietsart(int gebietsart) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsByGebietsart(gebietsart);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by nummer.
     *
     * @param nummer searching condition
     * @return  {@link Collection} of primary keys of the entities Gebiet
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByNummer(int nummer) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsByNummer(nummer);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by roemisch.
     *
     * @param roemisch searching condition
     * @return  {@link Collection} of primary keys of the entities Gebiet
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByRoemisch(boolean roemisch) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsByRoemisch(roemisch);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of primary keys of the entities Gebiet
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByName(String name) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsByName(name);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of primary keys of the entities Gebiet
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeName(String name) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsLikeName(name);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by kuerzel.
     *
     * @param kuerzel searching condition
     * @return  {@link Collection} of primary keys of the entities Gebiet
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByKuerzel(String kuerzel) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsByKuerzel(kuerzel);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by kuerzel.
     *
     * @param kuerzel searching condition
     * @return  {@link Collection} of primary keys of the entities Gebiet
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeKuerzel(String kuerzel) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsLikeKuerzel(kuerzel);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by position.
     *
     * @param position searching condition
     * @return  {@link Collection} of primary keys of the entities Gebiet
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByPosition(int position) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsByPosition(position);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by wahlberechtigte.
     *
     * @param wahlberechtigte searching condition
     * @return  {@link Collection} of primary keys of the entities Gebiet
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByWahlberechtigte(int wahlberechtigte) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsByWahlberechtigte(wahlberechtigte);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by gUIEingabeErlaubt.
     *
     * @param gUIEingabeErlaubt searching condition
     * @return  {@link Collection} of primary keys of the entities Gebiet
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByGUIEingabeErlaubt(boolean gUIEingabeErlaubt) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsByGUIEingabeErlaubt(gUIEingabeErlaubt);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by postalvote.
     *
     * @param postalvote searching condition
     * @return  {@link Collection} of primary keys of the entities Gebiet
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByPostalvote(boolean postalvote) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsByPostalvote(postalvote);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by voteValue.
     *
     * @param voteValue searching condition
     * @return  {@link Collection} of primary keys of the entities Gebiet
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByVoteValue(int voteValue) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsByVoteValue(voteValue);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by zipcode.
     *
     * @param zipcode searching condition
     * @return  {@link Collection} of primary keys of the entities Gebiet
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByZipcode(String zipcode) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsByZipcode(zipcode);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}, filtered by zipcode.
     *
     * @param zipcode searching condition
     * @return  {@link Collection} of primary keys of the entities Gebiet
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeZipcode(String zipcode) throws IVUFinderException {
      try {
         return GebietDBA.retrieveIDsLikeZipcode(zipcode);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Gebiet key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Gebiet) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(GebietModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public GebietModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(GebietModel details) {
      if (details instanceof GebietModelImpl && _details instanceof GebietModelImpl) {
         ((GebietModelImpl)_details).copyFrom((GebietModelImpl)details);
         checkRelations();
      } else {
         setID_UebergeordnetesGebiet(details.getID_UebergeordnetesGebiet());
         setID_Wahl(details.getID_Wahl());
         setID_LetzterEingang(details.getID_LetzterEingang());
         setErfassungseinheit(details.isErfassungseinheit());
         setWahleinheit(details.isWahleinheit());
         setGebietsart(details.getGebietsart());
         setNummer(details.getNummer());
         setRoemisch(details.isRoemisch());
         setName(details.getName());
         setKuerzel(details.getKuerzel());
         setPosition(details.getPosition());
         setWahlberechtigte(details.getWahlberechtigte());
         setGUIEingabeErlaubt(details.isGUIEingabeErlaubt());
         setPostalvote(details.isPostalvote());
         setVoteValue(details.getVoteValue());
         setZipcode(details.getZipcode());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (GebietModel)details;
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
      if (null == _details.getID_LetzterEingang()) {
         _letzterEingang = null;
         _relchk_LetzterEingang = true;
      } else {
         _relchk_LetzterEingang = false;
      }
      if (null == _details.getID_UebergeordnetesGebiet()) {
         _uebergeordnetesGebiet = null;
         _relchk_UebergeordnetesGebiet = true;
      } else {
         _relchk_UebergeordnetesGebiet = false;
      }
      if (null == _details.getID_Wahl()) {
         _wahl = null;
         _relchk_Wahl = true;
      } else {
         _relchk_Wahl = false;
      }
   }

   @Override
   protected void resetRelations() {
      _letzterEingang = null;
      _uebergeordnetesGebiet = null;
      _wahl = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public GebietModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Gebiet in the entity Gebiet
     *
     * @return value of the attribute id_Gebiet
     */
   public String getID_Gebiet() {
      return _details.getID_Gebiet();
   }

   /**
     * Sets the value of id_UebergeordnetesGebiet in the entity Gebiet
     *
     * @param id_UebergeordnetesGebiet new value of the attribute id_UebergeordnetesGebiet
     */
   public void setID_UebergeordnetesGebiet(String id_UebergeordnetesGebiet) {
      if (null == id_UebergeordnetesGebiet) {
         _uebergeordnetesGebiet = null;
         _relchk_UebergeordnetesGebiet = true;
      } else {
         String old = _details.getID_UebergeordnetesGebiet();
         if (old == null || !old.equals(id_UebergeordnetesGebiet)) {
            _relchk_UebergeordnetesGebiet = false;
         }
      }
      _details.setID_UebergeordnetesGebiet(id_UebergeordnetesGebiet);
   }

   /**
     * Gets the value of id_UebergeordnetesGebiet in the entity Gebiet
     *
     * @return value of the attribute id_UebergeordnetesGebiet
     */
   public String getID_UebergeordnetesGebiet() {
      return _details.getID_UebergeordnetesGebiet();
   }

   /**
     * Sets the value of id_Wahl in the entity Gebiet
     *
     * @param id_Wahl new value of the attribute id_Wahl
     */
   public void setID_Wahl(String id_Wahl) {
      if (null == id_Wahl) {
         _wahl = null;
         _relchk_Wahl = true;
      } else {
         String old = _details.getID_Wahl();
         if (old == null || !old.equals(id_Wahl)) {
            _relchk_Wahl = false;
         }
      }
      _details.setID_Wahl(id_Wahl);
   }

   /**
     * Gets the value of id_Wahl in the entity Gebiet
     *
     * @return value of the attribute id_Wahl
     */
   public String getID_Wahl() {
      return _details.getID_Wahl();
   }

   /**
     * Sets the value of id_LetzterEingang in the entity Gebiet
     *
     * @param id_LetzterEingang new value of the attribute id_LetzterEingang
     */
   public void setID_LetzterEingang(String id_LetzterEingang) {
      if (null == id_LetzterEingang) {
         _letzterEingang = null;
         _relchk_LetzterEingang = true;
      } else {
         String old = _details.getID_LetzterEingang();
         if (old == null || !old.equals(id_LetzterEingang)) {
            _relchk_LetzterEingang = false;
         }
      }
      _details.setID_LetzterEingang(id_LetzterEingang);
   }

   /**
     * Gets the value of id_LetzterEingang in the entity Gebiet
     *
     * @return value of the attribute id_LetzterEingang
     */
   public String getID_LetzterEingang() {
      return _details.getID_LetzterEingang();
   }

   /**
     * Sets the value of erfassungseinheit in the entity Gebiet
     *
     * @param erfassungseinheit new value of the attribute erfassungseinheit
     */
   public void setErfassungseinheit(boolean erfassungseinheit) {
      _details.setErfassungseinheit(erfassungseinheit);
   }

   /**
     * Gets the value of erfassungseinheit in the entity Gebiet
     *
     * @return value of the attribute erfassungseinheit
     */
   public boolean isErfassungseinheit() {
      return _details.isErfassungseinheit();
   }

   /**
     * Sets the value of wahleinheit in the entity Gebiet
     *
     * @param wahleinheit new value of the attribute wahleinheit
     */
   public void setWahleinheit(boolean wahleinheit) {
      _details.setWahleinheit(wahleinheit);
   }

   /**
     * Gets the value of wahleinheit in the entity Gebiet
     *
     * @return value of the attribute wahleinheit
     */
   public boolean isWahleinheit() {
      return _details.isWahleinheit();
   }

   /**
     * Sets the value of gebietsart in the entity Gebiet
     *
     * @param gebietsart new value of the attribute gebietsart
     */
   public void setGebietsart(int gebietsart) {
      _details.setGebietsart(gebietsart);
   }

   /**
     * Gets the value of gebietsart in the entity Gebiet
     *
     * @return value of the attribute gebietsart
     */
   public int getGebietsart() {
      return _details.getGebietsart();
   }

   /**
     * Sets the value of nummer in the entity Gebiet
     *
     * @param nummer new value of the attribute nummer
     */
   public void setNummer(int nummer) {
      _details.setNummer(nummer);
   }

   /**
     * Gets the value of nummer in the entity Gebiet
     *
     * @return value of the attribute nummer
     */
   public int getNummer() {
      return _details.getNummer();
   }

   /**
     * Sets the value of roemisch in the entity Gebiet
     *
     * @param roemisch new value of the attribute roemisch
     */
   public void setRoemisch(boolean roemisch) {
      _details.setRoemisch(roemisch);
   }

   /**
     * Gets the value of roemisch in the entity Gebiet
     *
     * @return value of the attribute roemisch
     */
   public boolean isRoemisch() {
      return _details.isRoemisch();
   }

   /**
     * Sets the value of name in the entity Gebiet
     *
     * @param name new value of the attribute name
     */
   public void setName(String name) {
      _details.setName(name);
   }

   /**
     * Gets the value of name in the entity Gebiet
     *
     * @return value of the attribute name
     */
   public String getName() {
      return _details.getName();
   }

   /**
     * Sets the value of kuerzel in the entity Gebiet
     *
     * @param kuerzel new value of the attribute kuerzel
     */
   public void setKuerzel(String kuerzel) {
      _details.setKuerzel(kuerzel);
   }

   /**
     * Gets the value of kuerzel in the entity Gebiet
     *
     * @return value of the attribute kuerzel
     */
   public String getKuerzel() {
      return _details.getKuerzel();
   }

   /**
     * Sets the value of position in the entity Gebiet
     *
     * @param position new value of the attribute position
     */
   public void setPosition(int position) {
      _details.setPosition(position);
   }

   /**
     * Gets the value of position in the entity Gebiet
     *
     * @return value of the attribute position
     */
   public int getPosition() {
      return _details.getPosition();
   }

   /**
     * Sets the value of wahlberechtigte in the entity Gebiet
     *
     * @param wahlberechtigte new value of the attribute wahlberechtigte
     */
   public void setWahlberechtigte(int wahlberechtigte) {
      _details.setWahlberechtigte(wahlberechtigte);
   }

   /**
     * Gets the value of wahlberechtigte in the entity Gebiet
     *
     * @return value of the attribute wahlberechtigte
     */
   public int getWahlberechtigte() {
      return _details.getWahlberechtigte();
   }

   /**
     * Sets the value of gUIEingabeErlaubt in the entity Gebiet
     *
     * @param gUIEingabeErlaubt new value of the attribute gUIEingabeErlaubt
     */
   public void setGUIEingabeErlaubt(boolean gUIEingabeErlaubt) {
      _details.setGUIEingabeErlaubt(gUIEingabeErlaubt);
   }

   /**
     * Gets the value of gUIEingabeErlaubt in the entity Gebiet
     *
     * @return value of the attribute gUIEingabeErlaubt
     */
   public boolean isGUIEingabeErlaubt() {
      return _details.isGUIEingabeErlaubt();
   }

   /**
     * Sets the value of postalvote in the entity Gebiet
     *
     * @param postalvote new value of the attribute postalvote
     */
   public void setPostalvote(boolean postalvote) {
      _details.setPostalvote(postalvote);
   }

   /**
     * Gets the value of postalvote in the entity Gebiet
     *
     * @return value of the attribute postalvote
     */
   public boolean isPostalvote() {
      return _details.isPostalvote();
   }

   /**
     * Sets the value of voteValue in the entity Gebiet
     *
     * @param voteValue new value of the attribute voteValue
     */
   public void setVoteValue(int voteValue) {
      _details.setVoteValue(voteValue);
   }

   /**
     * Gets the value of voteValue in the entity Gebiet
     *
     * @return value of the attribute voteValue
     */
   public int getVoteValue() {
      return _details.getVoteValue();
   }

   /**
     * Sets the value of zipcode in the entity Gebiet
     *
     * @param zipcode new value of the attribute zipcode
     */
   public void setZipcode(String zipcode) {
      _details.setZipcode(zipcode);
   }

   /**
     * Gets the value of zipcode in the entity Gebiet
     *
     * @return value of the attribute zipcode
     */
   public String getZipcode() {
      return _details.getZipcode();
   }

   /**
     * Relation zu LetzterEingang
     */
   protected Ergebniseingang _letzterEingang;

   /**
     * Flag for the validity of the relation LetzterEingang
     */
   protected boolean _relchk_LetzterEingang = false;

   /**
     * Navigation to the associated entity of the type {@link Ergebniseingang}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Ergebniseingang getLetzterEingang() throws EJBException {
      if (!_relchk_LetzterEingang) {
         if (null == _details.getID_LetzterEingang()) {
            _letzterEingang = null;
         } else if (null == _letzterEingang || !_letzterEingang.getPrimaryKey().equals(_details.getID_LetzterEingang())) {
            try {
               ErgebniseingangHome home = ErgebniseingangHome.HomeFinder.findHome(this);
               _letzterEingang = home.findByPrimaryKey(_details.getID_LetzterEingang());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find LetzterEingang", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Ergebniseingang", fe); //$NON-NLS-1$
            }
         }
         _relchk_LetzterEingang = true;
      }
      return _letzterEingang;
   }

   /**
     * Setting of the associated entity of the type {@link Ergebniseingang}
     *
     * @param letzterEingang the corresponding EJBObject
     */
   public void setLetzterEingang(Ergebniseingang letzterEingang) {
      _letzterEingang = letzterEingang;
      _details.setID_LetzterEingang(letzterEingang == null ? null : (String)letzterEingang.getPrimaryKey());
   }

   /**
     * Relation zu UebergeordnetesGebiet
     */
   protected Gebiet _uebergeordnetesGebiet;

   /**
     * Flag for the validity of the relation UebergeordnetesGebiet
     */
   protected boolean _relchk_UebergeordnetesGebiet = false;

   /**
     * Navigation to the associated entity of the type {@link Gebiet}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Gebiet getUebergeordnetesGebiet() throws EJBException {
      if (!_relchk_UebergeordnetesGebiet) {
         if (null == _details.getID_UebergeordnetesGebiet()) {
            _uebergeordnetesGebiet = null;
         } else if (null == _uebergeordnetesGebiet || !_uebergeordnetesGebiet.getPrimaryKey().equals(_details.getID_UebergeordnetesGebiet())) {
            try {
               GebietHome home = GebietHome.HomeFinder.findHome(this);
               _uebergeordnetesGebiet = home.findByPrimaryKey(_details.getID_UebergeordnetesGebiet());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find UebergeordnetesGebiet", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Gebiet", fe); //$NON-NLS-1$
            }
         }
         _relchk_UebergeordnetesGebiet = true;
      }
      return _uebergeordnetesGebiet;
   }

   /**
     * Setting of the associated entity of the type {@link Gebiet}
     *
     * @param uebergeordnetesGebiet the corresponding EJBObject
     */
   public void setUebergeordnetesGebiet(Gebiet uebergeordnetesGebiet) {
      _uebergeordnetesGebiet = uebergeordnetesGebiet;
      _details.setID_UebergeordnetesGebiet(uebergeordnetesGebiet == null ? null : (String)uebergeordnetesGebiet.getPrimaryKey());
   }

   /**
     * Relation zu Wahl
     */
   protected Wahl _wahl;

   /**
     * Flag for the validity of the relation Wahl
     */
   protected boolean _relchk_Wahl = false;

   /**
     * Navigation to the associated entity of the type {@link Wahl}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Wahl getWahl() throws EJBException {
      if (!_relchk_Wahl) {
         if (null == _details.getID_Wahl()) {
            _wahl = null;
         } else if (null == _wahl || !_wahl.getPrimaryKey().equals(_details.getID_Wahl())) {
            try {
               WahlHome home = WahlHome.HomeFinder.findHome(this);
               _wahl = home.findByPrimaryKey(_details.getID_Wahl());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find Wahl", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Wahl", fe); //$NON-NLS-1$
            }
         }
         _relchk_Wahl = true;
      }
      return _wahl;
   }

   /**
     * Setting of the associated entity of the type {@link Wahl}
     *
     * @param wahl the corresponding EJBObject
     */
   public void setWahl(Wahl wahl) {
      _wahl = wahl;
      _details.setID_Wahl(wahl == null ? null : (String)wahl.getPrimaryKey());
   }

   /**
     * Returns the set of entities of the type {@link Anwender}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Anwender}
     * @throws EJBException: an error occurred
     */
   public Collection<Anwender> getAnwenderCol() throws EJBException {
      AnwenderHome anwenderHome = AnwenderHome.HomeFinder.findHome(this);
      try {
         return anwenderHome.findAllByGebiet(_details.getID_Gebiet());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Anwender}.
     *
     * @param anwender Anwender-object
     */
   public void addAnwender(Anwender anwender) {
      anwender.setID_Gebiet(_details.getID_Gebiet());
   }

   /**
     * Adds the object to the set of entities of the type {@link Anwender}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllAnwenderCol(Collection<Anwender> col) {
      for (Anwender anwender : col) {
         addAnwender(anwender);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Anwender}.
     *
     * @param anwender Anwender-EJBObject, which is removed from the set.
     */
   public void removeAnwender(Anwender anwender) {
      anwender.setID_Gebiet(null);
   }

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Ergebniseingang}
     * @throws EJBException: an error occurred
     */
   public Collection<Ergebniseingang> getErfassungseinheitCol() throws EJBException {
      ErgebniseingangHome ergebniseingangHome = ErgebniseingangHome.HomeFinder.findHome(this);
      try {
         return ergebniseingangHome.findAllByErfassungseinheit(_details.getID_Gebiet());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Ergebniseingang}.
     *
     * @param erfassungseinheit Ergebniseingang-object
     */
   public void addErfassungseinheit(Ergebniseingang erfassungseinheit) {
      erfassungseinheit.setID_Erfassungseinheit(_details.getID_Gebiet());
   }

   /**
     * Adds the object to the set of entities of the type {@link Ergebniseingang}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllErfassungseinheitCol(Collection<Ergebniseingang> col) {
      for (Ergebniseingang erfassungseinheit : col) {
         addErfassungseinheit(erfassungseinheit);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Ergebniseingang}.
     *
     * @param erfassungseinheit Ergebniseingang-EJBObject, which is removed from the set.
     */
   public void removeErfassungseinheit(Ergebniseingang erfassungseinheit) {
      erfassungseinheit.setID_Erfassungseinheit(null);
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
         return gebietHome.findAllByUebergeordnetesGebiet(_details.getID_Gebiet());
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
      gebiet.setID_UebergeordnetesGebiet(_details.getID_Gebiet());
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
      gebiet.setID_UebergeordnetesGebiet(null);
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
         return ergebniseingangHome.findAllByGebiet(_details.getID_Gebiet());
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
      addID_Ergebniseingang((String)ergebniseingang.getPrimaryKey());
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
     * Adds the object, which is marked by the ID, to the set of entities of the type {@link Ergebniseingang}.
     *
     * @param id_Ergebniseingang ID of Ergebniseingang entity
     * @throws EJBException: an error occurred
     */
   public void addID_Ergebniseingang(String id_Ergebniseingang) throws EJBException {
      try {
         Gebiet_ErgebniseingangModelImpl model =
            new Gebiet_ErgebniseingangModelImpl (_details.getID_Gebiet(), id_Ergebniseingang);
         Gebiet_ErgebniseingangDBA.insert (model);
      } catch (Exception e) {
         throw new EJBException("Unable to change Table Gebiet_Ergebniseingang Exception: " + //$NON-NLS-1$
            e.getMessage(), e);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Ergebniseingang}.
     *
     * @param ergebniseingang Ergebniseingang-EJBObject, which is removed from the set.
     */
   public void removeErgebniseingang(Ergebniseingang ergebniseingang) {
      removeID_Ergebniseingang((String)ergebniseingang.getPrimaryKey());
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Ergebniseingang}.
     *
     * @param id_Ergebniseingang ID of the Ergebniseingang entity to be deleted
     * @throws EJBException: an error occurred
     */
   public void removeID_Ergebniseingang(String id_Ergebniseingang) throws EJBException {
      try {
         Gebiet_ErgebniseingangDBA.deleteByKey(_details.getID_Gebiet(), id_Ergebniseingang);
      } catch (Exception e) {
         throw new EJBException("Unable to remove the Ergebniseingang Entry from the table Gebiet_Ergebniseingang Exception: " + //$NON-NLS-1$
            e.getMessage(), e);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Gebiet}
     * @throws EJBException: an error occurred
     */
   public Collection<Gebiet> getUntergebietCol() throws EJBException {
      GebietHome gebietHome = GebietHome.HomeFinder.findHome(this);
      try {
         return gebietHome.findAllByElterngebiet(_details.getID_Gebiet());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Gebiet}.
     *
     * @param untergebiet Gebiet-object
     */
   public void addUntergebiet(Gebiet untergebiet) {
      addID_Untergebiet((String)untergebiet.getPrimaryKey());
   }

   /**
     * Adds the object to the set of entities of the type {@link Gebiet}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllUntergebietCol(Collection<Gebiet> col) {
      for (Gebiet untergebiet : col) {
         addUntergebiet(untergebiet);
      }
   }

   /**
     * Adds the object, which is marked by the ID, to the set of entities of the type {@link Untergebiet}.
     *
     * @param id_Untergebiet ID of Untergebiet entity
     * @throws EJBException: an error occurred
     */
   public void addID_Untergebiet(String id_Untergebiet) throws EJBException {
      try {
         Gebiet_GebietModelImpl model =
            new Gebiet_GebietModelImpl (_details.getID_Gebiet(), id_Untergebiet);
         Gebiet_GebietDBA.insert (model);
      } catch (Exception e) {
         throw new EJBException("Unable to change Table Gebiet_Gebiet Exception: " + //$NON-NLS-1$
            e.getMessage(), e);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Gebiet}.
     *
     * @param untergebiet Gebiet-EJBObject, which is removed from the set.
     */
   public void removeUntergebiet(Gebiet untergebiet) {
      removeID_Untergebiet((String)untergebiet.getPrimaryKey());
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Gebiet}.
     *
     * @param id_Untergebiet ID of the Gebiet entity to be deleted
     * @throws EJBException: an error occurred
     */
   public void removeID_Untergebiet(String id_Untergebiet) throws EJBException {
      try {
         Gebiet_GebietDBA.deleteByKey(_details.getID_Gebiet(), id_Untergebiet);
      } catch (Exception e) {
         throw new EJBException("Unable to remove the Gebiet Entry from the table Gebiet_Gebiet Exception: " + //$NON-NLS-1$
            e.getMessage(), e);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Gebiet}
     * @throws EJBException: an error occurred
     */
   public Collection<Gebiet> getElterngebietCol() throws EJBException {
      GebietHome gebietHome = GebietHome.HomeFinder.findHome(this);
      try {
         return gebietHome.findAllByUntergebiet(_details.getID_Gebiet());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Gebiet}.
     *
     * @param elterngebiet Gebiet-object
     */
   public void addElterngebiet(Gebiet elterngebiet) {
      addID_Elterngebiet((String)elterngebiet.getPrimaryKey());
   }

   /**
     * Adds the object to the set of entities of the type {@link Gebiet}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllElterngebietCol(Collection<Gebiet> col) {
      for (Gebiet elterngebiet : col) {
         addElterngebiet(elterngebiet);
      }
   }

   /**
     * Adds the object, which is marked by the ID, to the set of entities of the type {@link Elterngebiet}.
     *
     * @param id_Elterngebiet ID of Elterngebiet entity
     * @throws EJBException: an error occurred
     */
   public void addID_Elterngebiet(String id_Elterngebiet) throws EJBException {
      try {
         Gebiet_GebietModelImpl model =
            new Gebiet_GebietModelImpl (id_Elterngebiet, _details.getID_Gebiet());
         Gebiet_GebietDBA.insert (model);
      } catch (Exception e) {
         throw new EJBException("Unable to change Table Gebiet_Gebiet Exception: " + //$NON-NLS-1$
            e.getMessage(), e);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Gebiet}.
     *
     * @param elterngebiet Gebiet-EJBObject, which is removed from the set.
     */
   public void removeElterngebiet(Gebiet elterngebiet) {
      removeID_Elterngebiet((String)elterngebiet.getPrimaryKey());
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Gebiet}.
     *
     * @param id_Elterngebiet ID of the Gebiet entity to be deleted
     * @throws EJBException: an error occurred
     */
   public void removeID_Elterngebiet(String id_Elterngebiet) throws EJBException {
      try {
         Gebiet_GebietDBA.deleteByKey(id_Elterngebiet, _details.getID_Gebiet());
      } catch (Exception e) {
         throw new EJBException("Unable to remove the Gebiet Entry from the table Gebiet_Gebiet Exception: " + //$NON-NLS-1$
            e.getMessage(), e);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebietsstatus}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Gebietsstatus}
     * @throws EJBException: an error occurred
     */
   public Collection<Gebietsstatus> getGebietsstatusCol() throws EJBException {
      GebietsstatusHome gebietsstatusHome = GebietsstatusHome.HomeFinder.findHome(this);
      try {
         return gebietsstatusHome.findAllByGebiet(_details.getID_Gebiet());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Gebietsstatus}.
     *
     * @param gebietsstatus Gebietsstatus-object
     */
   public void addGebietsstatus(Gebietsstatus gebietsstatus) {
      gebietsstatus.setID_Gebiet(_details.getID_Gebiet());
   }

   /**
     * Adds the object to the set of entities of the type {@link Gebietsstatus}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllGebietsstatusCol(Collection<Gebietsstatus> col) {
      for (Gebietsstatus gebietsstatus : col) {
         addGebietsstatus(gebietsstatus);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Gebietsstatus}.
     *
     * @param gebietsstatus Gebietsstatus-EJBObject, which is removed from the set.
     */
   public void removeGebietsstatus(Gebietsstatus gebietsstatus) {
      gebietsstatus.setID_Gebiet(null);
   }

   /**
     * Returns the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link GruppeGebietsspezifisch}
     * @throws EJBException: an error occurred
     */
   public Collection<GruppeGebietsspezifisch> getGruppeGebietsspezifischCol() throws EJBException {
      GruppeGebietsspezifischHome gruppeGebietsspezifischHome = GruppeGebietsspezifischHome.HomeFinder.findHome(this);
      try {
         return gruppeGebietsspezifischHome.findAllByGebiet(_details.getID_Gebiet());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @param gruppeGebietsspezifisch GruppeGebietsspezifisch-object
     */
   public void addGruppeGebietsspezifisch(GruppeGebietsspezifisch gruppeGebietsspezifisch) {
      gruppeGebietsspezifisch.setID_Gebiet(_details.getID_Gebiet());
   }

   /**
     * Adds the object to the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllGruppeGebietsspezifischCol(Collection<GruppeGebietsspezifisch> col) {
      for (GruppeGebietsspezifisch gruppeGebietsspezifisch : col) {
         addGruppeGebietsspezifisch(gruppeGebietsspezifisch);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @param gruppeGebietsspezifisch GruppeGebietsspezifisch-EJBObject, which is removed from the set.
     */
   public void removeGruppeGebietsspezifisch(GruppeGebietsspezifisch gruppeGebietsspezifisch) {
      gruppeGebietsspezifisch.setID_Gebiet(null);
   }

   /**
     * Returns the set of entities of the type {@link Stimmergebnis}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Stimmergebnis}
     * @throws EJBException: an error occurred
     */
   public Collection<Stimmergebnis> getStimmergebnisCol() throws EJBException {
      StimmergebnisHome stimmergebnisHome = StimmergebnisHome.HomeFinder.findHome(this);
      try {
         return stimmergebnisHome.findAllByGebiet(_details.getID_Gebiet());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Stimmergebnis}.
     *
     * @param stimmergebnis Stimmergebnis-object
     */
   public void addStimmergebnis(Stimmergebnis stimmergebnis) {
      stimmergebnis.setID_Gebiet(_details.getID_Gebiet());
   }

   /**
     * Adds the object to the set of entities of the type {@link Stimmergebnis}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllStimmergebnisCol(Collection<Stimmergebnis> col) {
      for (Stimmergebnis stimmergebnis : col) {
         addStimmergebnis(stimmergebnis);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Stimmergebnis}.
     *
     * @param stimmergebnis Stimmergebnis-EJBObject, which is removed from the set.
     */
   public void removeStimmergebnis(Stimmergebnis stimmergebnis) {
      stimmergebnis.setID_Gebiet(null);
   }

   /**
     * Returns the set of entities of the type {@link StimmergebnisseUntergebiete}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link StimmergebnisseUntergebiete}
     * @throws EJBException: an error occurred
     */
   public Collection<StimmergebnisseUntergebiete> getStimmergebnisseUntergebieteCol()
      throws EJBException {

      StimmergebnisseUntergebieteHome stimmergebnisseUntergebieteHome = StimmergebnisseUntergebieteHome.HomeFinder.findHome(this);
      try {
         return stimmergebnisseUntergebieteHome.findAllByGebiet(_details.getID_Gebiet());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link StimmergebnisseUntergebiete}.
     *
     * @param stimmergebnisseUntergebiete StimmergebnisseUntergebiete-object
     */
   public void addStimmergebnisseUntergebiete(StimmergebnisseUntergebiete stimmergebnisseUntergebiete) {
      stimmergebnisseUntergebiete.setID_Gebiet(_details.getID_Gebiet());
   }

   /**
     * Adds the object to the set of entities of the type {@link StimmergebnisseUntergebiete}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllStimmergebnisseUntergebieteCol(Collection<StimmergebnisseUntergebiete> col) {
      for (StimmergebnisseUntergebiete stimmergebnisseUntergebiete : col) {
         addStimmergebnisseUntergebiete(stimmergebnisseUntergebiete);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link StimmergebnisseUntergebiete}.
     *
     * @param stimmergebnisseUntergebiete StimmergebnisseUntergebiete-EJBObject, which is removed from the set.
     */
   public void removeStimmergebnisseUntergebiete(StimmergebnisseUntergebiete stimmergebnisseUntergebiete) {
      stimmergebnisseUntergebiete.setID_Gebiet(null);
   }

   /**
     * Returns the set of entities of the type {@link Wahl}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Wahl}
     * @throws EJBException: an error occurred
     */
   public Collection<Wahl> getWahlCol() throws EJBException {
      WahlHome wahlHome = WahlHome.HomeFinder.findHome(this);
      try {
         return wahlHome.findAllByWahlgebiet(_details.getID_Gebiet());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Wahl}.
     *
     * @param wahl Wahl-object
     */
   public void addWahl(Wahl wahl) {
      wahl.setID_Wahlgebiet(_details.getID_Gebiet());
   }

   /**
     * Adds the object to the set of entities of the type {@link Wahl}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllWahlCol(Collection<Wahl> col) {
      for (Wahl wahl : col) {
         addWahl(wahl);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Wahl}.
     *
     * @param wahl Wahl-EJBObject, which is removed from the set.
     */
   public void removeWahl(Wahl wahl) {
      wahl.setID_Wahlgebiet(null);
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
         if (_details.getID_Gebiet() != null) {
            string += "id_Gebiet = " + _details.getID_Gebiet(); //$NON-NLS-1$
         }
         if (_details.getID_UebergeordnetesGebiet() != null) {
            string +=  ", id_UebergeordnetesGebiet = " + _details.getID_UebergeordnetesGebiet(); //$NON-NLS-1$
         }
         if (_details.getID_Wahl() != null) {
            string +=  ", id_Wahl = " + _details.getID_Wahl(); //$NON-NLS-1$
         }
         if (_details.getID_LetzterEingang() != null) {
            string +=  ", id_LetzterEingang = " + _details.getID_LetzterEingang(); //$NON-NLS-1$
         }
         string +=  ", erfassungseinheit = " + _details.isErfassungseinheit(); //$NON-NLS-1$
         string +=  ", wahleinheit = " + _details.isWahleinheit(); //$NON-NLS-1$
         string +=  ", gebietsart = " + _details.getGebietsart(); //$NON-NLS-1$
         string +=  ", nummer = " + _details.getNummer(); //$NON-NLS-1$
         string +=  ", roemisch = " + _details.isRoemisch(); //$NON-NLS-1$
         if (_details.getName() != null) {
            string +=  ", name = " + _details.getName(); //$NON-NLS-1$
         }
         if (_details.getKuerzel() != null) {
            string +=  ", kuerzel = " + _details.getKuerzel(); //$NON-NLS-1$
         }
         string +=  ", position = " + _details.getPosition(); //$NON-NLS-1$
         string +=  ", wahlberechtigte = " + _details.getWahlberechtigte(); //$NON-NLS-1$
         string +=  ", gUIEingabeErlaubt = " + _details.isGUIEingabeErlaubt(); //$NON-NLS-1$
         string +=  ", postalvote = " + _details.isPostalvote(); //$NON-NLS-1$
         string +=  ", voteValue = " + _details.getVoteValue(); //$NON-NLS-1$
         if (_details.getZipcode() != null) {
            string +=  ", zipcode = " + _details.getZipcode(); //$NON-NLS-1$
         }
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
