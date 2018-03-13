/*
 * BasicErgebniseingangBean
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
import de.ivu.wahl.modell.ErgebniseingangModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Ergebniseingang as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicErgebniseingangBean extends BMPBeanBase implements EntityBean, ErgebniseingangModel {
   private static final Category LOGGER = Log4J.configure(BasicErgebniseingangBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicErgebniseingangBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected ErgebniseingangModel _details = null;

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
   public String ejbCreate(ErgebniseingangModel details) throws CreateException, EJBException {
      ErgebniseingangModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Ergebniseingang();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Ergebniseingang key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Ergebniseingang) throws CreateException, EJBException {
      _details = (ErgebniseingangModel)createModel(id_Ergebniseingang);
      _details.setAnwenderName(""); //$NON-NLS-1$
      _details.setErgebnisHash(""); //$NON-NLS-1$
      _details.setFehlermeldung(""); //$NON-NLS-1$
      create(_details);
      return id_Ergebniseingang;
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
         return ErgebniseingangDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gebiet ID of the objects to be searched
     * @return  {@link Collection} of the found Ergebniseingang-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByGebiet(String id_Gebiet) throws IVUFinderException {
      try {
         return Gebiet_ErgebniseingangDBA.retrieveIDsByID_Gebiet(id_Gebiet);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Wahl ID of the objects to be searched
     * @return  {@link Collection} of the found Ergebniseingang-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByWahl(String id_Wahl) throws IVUFinderException {
      try {
         return ErgebniseingangDBA.retrieveIDsByID_Wahl(id_Wahl);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Erfassungseinheit ID of the objects to be searched
     * @return  {@link Collection} of the found Ergebniseingang-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByErfassungseinheit(String id_Erfassungseinheit) throws IVUFinderException {
      try {
         return ErgebniseingangDBA.retrieveIDsByID_Erfassungseinheit(id_Erfassungseinheit);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by anwenderName.
     *
     * @param anwenderName searching condition
     * @return  {@link Collection} of primary keys of the entities Ergebniseingang
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByAnwenderName(String anwenderName) throws IVUFinderException {
      try {
         return ErgebniseingangDBA.retrieveIDsByAnwenderName(anwenderName);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by anwenderName.
     *
     * @param anwenderName searching condition
     * @return  {@link Collection} of primary keys of the entities Ergebniseingang
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeAnwenderName(String anwenderName) throws IVUFinderException {
      try {
         return ErgebniseingangDBA.retrieveIDsLikeAnwenderName(anwenderName);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by zeitstempel.
     *
     * @param zeitstempel searching condition
     * @return  {@link Collection} of primary keys of the entities Ergebniseingang
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByZeitstempel(Timestamp zeitstempel) throws IVUFinderException {
      try {
         return ErgebniseingangDBA.retrieveIDsByZeitstempel(zeitstempel);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by herkunft.
     *
     * @param herkunft searching condition
     * @return  {@link Collection} of primary keys of the entities Ergebniseingang
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByHerkunft(int herkunft) throws IVUFinderException {
      try {
         return ErgebniseingangDBA.retrieveIDsByHerkunft(herkunft);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by wahlergebnisart.
     *
     * @param wahlergebnisart searching condition
     * @return  {@link Collection} of primary keys of the entities Ergebniseingang
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByWahlergebnisart(int wahlergebnisart) throws IVUFinderException {
      try {
         return ErgebniseingangDBA.retrieveIDsByWahlergebnisart(wahlergebnisart);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by unterschiedeVorhanden.
     *
     * @param unterschiedeVorhanden searching condition
     * @return  {@link Collection} of primary keys of the entities Ergebniseingang
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByUnterschiedeVorhanden(int unterschiedeVorhanden) throws IVUFinderException {
      try {
         return ErgebniseingangDBA.retrieveIDsByUnterschiedeVorhanden(unterschiedeVorhanden);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by status.
     *
     * @param status searching condition
     * @return  {@link Collection} of primary keys of the entities Ergebniseingang
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByStatus(int status) throws IVUFinderException {
      try {
         return ErgebniseingangDBA.retrieveIDsByStatus(status);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by ergebnisHash.
     *
     * @param ergebnisHash searching condition
     * @return  {@link Collection} of primary keys of the entities Ergebniseingang
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByErgebnisHash(String ergebnisHash) throws IVUFinderException {
      try {
         return ErgebniseingangDBA.retrieveIDsByErgebnisHash(ergebnisHash);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by ergebnisHash.
     *
     * @param ergebnisHash searching condition
     * @return  {@link Collection} of primary keys of the entities Ergebniseingang
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeErgebnisHash(String ergebnisHash) throws IVUFinderException {
      try {
         return ErgebniseingangDBA.retrieveIDsLikeErgebnisHash(ergebnisHash);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by fehlermeldung.
     *
     * @param fehlermeldung searching condition
     * @return  {@link Collection} of primary keys of the entities Ergebniseingang
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByFehlermeldung(String fehlermeldung) throws IVUFinderException {
      try {
         return ErgebniseingangDBA.retrieveIDsByFehlermeldung(fehlermeldung);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Ergebniseingang}, filtered by fehlermeldung.
     *
     * @param fehlermeldung searching condition
     * @return  {@link Collection} of primary keys of the entities Ergebniseingang
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeFehlermeldung(String fehlermeldung) throws IVUFinderException {
      try {
         return ErgebniseingangDBA.retrieveIDsLikeFehlermeldung(fehlermeldung);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Ergebniseingang key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Ergebniseingang) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(ErgebniseingangModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public ErgebniseingangModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(ErgebniseingangModel details) {
      if (details instanceof ErgebniseingangModelImpl && _details instanceof ErgebniseingangModelImpl) {
         ((ErgebniseingangModelImpl)_details).copyFrom((ErgebniseingangModelImpl)details);
         checkRelations();
      } else {
         setID_Erfassungseinheit(details.getID_Erfassungseinheit());
         setID_Wahl(details.getID_Wahl());
         setAnwenderName(details.getAnwenderName());
         setZeitstempel(details.getZeitstempel());
         setHerkunft(details.getHerkunft());
         setWahlergebnisart(details.getWahlergebnisart());
         setUnterschiedeVorhanden(details.getUnterschiedeVorhanden());
         setStatus(details.getStatus());
         setErgebnisHash(details.getErgebnisHash());
         setFehlermeldung(details.getFehlermeldung());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (ErgebniseingangModel)details;
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
      if (null == _details.getID_Wahl()) {
         _wahl = null;
         _relchk_Wahl = true;
      } else {
         _relchk_Wahl = false;
      }
      if (null == _details.getID_Erfassungseinheit()) {
         _erfassungseinheit = null;
         _relchk_Erfassungseinheit = true;
      } else {
         _relchk_Erfassungseinheit = false;
      }
   }

   @Override
   protected void resetRelations() {
      _wahl = null;
      _erfassungseinheit = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public ErgebniseingangModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Ergebniseingang in the entity Ergebniseingang
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _details.getID_Ergebniseingang();
   }

   /**
     * Sets the value of id_Erfassungseinheit in the entity Ergebniseingang
     *
     * @param id_Erfassungseinheit new value of the attribute id_Erfassungseinheit
     */
   public void setID_Erfassungseinheit(String id_Erfassungseinheit) {
      if (null == id_Erfassungseinheit) {
         _erfassungseinheit = null;
         _relchk_Erfassungseinheit = true;
      } else {
         String old = _details.getID_Erfassungseinheit();
         if (old == null || !old.equals(id_Erfassungseinheit)) {
            _relchk_Erfassungseinheit = false;
         }
      }
      _details.setID_Erfassungseinheit(id_Erfassungseinheit);
   }

   /**
     * Gets the value of id_Erfassungseinheit in the entity Ergebniseingang
     *
     * @return value of the attribute id_Erfassungseinheit
     */
   public String getID_Erfassungseinheit() {
      return _details.getID_Erfassungseinheit();
   }

   /**
     * Sets the value of id_Wahl in the entity Ergebniseingang
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
     * Gets the value of id_Wahl in the entity Ergebniseingang
     *
     * @return value of the attribute id_Wahl
     */
   public String getID_Wahl() {
      return _details.getID_Wahl();
   }

   /**
     * Sets the value of anwenderName in the entity Ergebniseingang
     *
     * @param anwenderName new value of the attribute anwenderName
     */
   public void setAnwenderName(String anwenderName) {
      _details.setAnwenderName(anwenderName);
   }

   /**
     * Gets the value of anwenderName in the entity Ergebniseingang
     *
     * @return value of the attribute anwenderName
     */
   public String getAnwenderName() {
      return _details.getAnwenderName();
   }

   /**
     * Sets the value of zeitstempel in the entity Ergebniseingang
     *
     * @param zeitstempel new value of the attribute zeitstempel
     */
   public void setZeitstempel(Timestamp zeitstempel) {
      _details.setZeitstempel(zeitstempel);
   }

   /**
     * Gets the value of zeitstempel in the entity Ergebniseingang
     *
     * @return value of the attribute zeitstempel
     */
   public Timestamp getZeitstempel() {
      return _details.getZeitstempel();
   }

   /**
     * Sets the value of herkunft in the entity Ergebniseingang
     *
     * @param herkunft new value of the attribute herkunft
     */
   public void setHerkunft(int herkunft) {
      _details.setHerkunft(herkunft);
   }

   /**
     * Gets the value of herkunft in the entity Ergebniseingang
     *
     * @return value of the attribute herkunft
     */
   public int getHerkunft() {
      return _details.getHerkunft();
   }

   /**
     * Sets the value of wahlergebnisart in the entity Ergebniseingang
     *
     * @param wahlergebnisart new value of the attribute wahlergebnisart
     */
   public void setWahlergebnisart(int wahlergebnisart) {
      _details.setWahlergebnisart(wahlergebnisart);
   }

   /**
     * Gets the value of wahlergebnisart in the entity Ergebniseingang
     *
     * @return value of the attribute wahlergebnisart
     */
   public int getWahlergebnisart() {
      return _details.getWahlergebnisart();
   }

   /**
     * Sets the value of unterschiedeVorhanden in the entity Ergebniseingang
     *
     * @param unterschiedeVorhanden new value of the attribute unterschiedeVorhanden
     */
   public void setUnterschiedeVorhanden(int unterschiedeVorhanden) {
      _details.setUnterschiedeVorhanden(unterschiedeVorhanden);
   }

   /**
     * Gets the value of unterschiedeVorhanden in the entity Ergebniseingang
     *
     * @return value of the attribute unterschiedeVorhanden
     */
   public int getUnterschiedeVorhanden() {
      return _details.getUnterschiedeVorhanden();
   }

   /**
     * Sets the value of status in the entity Ergebniseingang
     *
     * @param status new value of the attribute status
     */
   public void setStatus(int status) {
      _details.setStatus(status);
   }

   /**
     * Gets the value of status in the entity Ergebniseingang
     *
     * @return value of the attribute status
     */
   public int getStatus() {
      return _details.getStatus();
   }

   /**
     * Sets the value of ergebnisHash in the entity Ergebniseingang
     *
     * @param ergebnisHash new value of the attribute ergebnisHash
     */
   public void setErgebnisHash(String ergebnisHash) {
      _details.setErgebnisHash(ergebnisHash);
   }

   /**
     * Gets the value of ergebnisHash in the entity Ergebniseingang
     *
     * @return value of the attribute ergebnisHash
     */
   public String getErgebnisHash() {
      return _details.getErgebnisHash();
   }

   /**
     * Sets the value of fehlermeldung in the entity Ergebniseingang
     *
     * @param fehlermeldung new value of the attribute fehlermeldung
     */
   public void setFehlermeldung(String fehlermeldung) {
      _details.setFehlermeldung(fehlermeldung);
   }

   /**
     * Gets the value of fehlermeldung in the entity Ergebniseingang
     *
     * @return value of the attribute fehlermeldung
     */
   public String getFehlermeldung() {
      return _details.getFehlermeldung();
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
     * Relation zu Erfassungseinheit
     */
   protected Gebiet _erfassungseinheit;

   /**
     * Flag for the validity of the relation Erfassungseinheit
     */
   protected boolean _relchk_Erfassungseinheit = false;

   /**
     * Navigation to the associated entity of the type {@link Gebiet}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Gebiet getErfassungseinheit() throws EJBException {
      if (!_relchk_Erfassungseinheit) {
         if (null == _details.getID_Erfassungseinheit()) {
            _erfassungseinheit = null;
         } else if (null == _erfassungseinheit || !_erfassungseinheit.getPrimaryKey().equals(_details.getID_Erfassungseinheit())) {
            try {
               GebietHome home = GebietHome.HomeFinder.findHome(this);
               _erfassungseinheit = home.findByPrimaryKey(_details.getID_Erfassungseinheit());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find Erfassungseinheit", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Gebiet", fe); //$NON-NLS-1$
            }
         }
         _relchk_Erfassungseinheit = true;
      }
      return _erfassungseinheit;
   }

   /**
     * Setting of the associated entity of the type {@link Gebiet}
     *
     * @param erfassungseinheit the corresponding EJBObject
     */
   public void setErfassungseinheit(Gebiet erfassungseinheit) {
      _erfassungseinheit = erfassungseinheit;
      _details.setID_Erfassungseinheit(erfassungseinheit == null ? null : (String)erfassungseinheit.getPrimaryKey());
   }

   /**
     * Returns the set of entities of the type {@link Besonderheit}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Besonderheit}
     * @throws EJBException: an error occurred
     */
   public Collection<Besonderheit> getBesonderheitCol() throws EJBException {
      BesonderheitHome besonderheitHome = BesonderheitHome.HomeFinder.findHome(this);
      try {
         return besonderheitHome.findAllByErgebniseingang(_details.getID_Ergebniseingang());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Besonderheit}.
     *
     * @param besonderheit Besonderheit-object
     */
   public void addBesonderheit(Besonderheit besonderheit) {
      besonderheit.setID_Ergebniseingang(_details.getID_Ergebniseingang());
   }

   /**
     * Adds the object to the set of entities of the type {@link Besonderheit}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllBesonderheitCol(Collection<Besonderheit> col) {
      for (Besonderheit besonderheit : col) {
         addBesonderheit(besonderheit);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Besonderheit}.
     *
     * @param besonderheit Besonderheit-EJBObject, which is removed from the set.
     */
   public void removeBesonderheit(Besonderheit besonderheit) {
      besonderheit.setID_Ergebniseingang(null);
   }

   /**
     * Returns the set of entities of the type {@link FiktiveSitzverteilung}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link FiktiveSitzverteilung}
     * @throws EJBException: an error occurred
     */
   public Collection<FiktiveSitzverteilung> getFiktiveSitzverteilungCol() throws EJBException {
      FiktiveSitzverteilungHome fiktiveSitzverteilungHome = FiktiveSitzverteilungHome.HomeFinder.findHome(this);
      try {
         return fiktiveSitzverteilungHome.findAllByErgebniseingang(_details.getID_Ergebniseingang());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link FiktiveSitzverteilung}.
     *
     * @param fiktiveSitzverteilung FiktiveSitzverteilung-object
     */
   public void addFiktiveSitzverteilung(FiktiveSitzverteilung fiktiveSitzverteilung) {
      fiktiveSitzverteilung.setID_Ergebniseingang(_details.getID_Ergebniseingang());
   }

   /**
     * Adds the object to the set of entities of the type {@link FiktiveSitzverteilung}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllFiktiveSitzverteilungCol(Collection<FiktiveSitzverteilung> col) {
      for (FiktiveSitzverteilung fiktiveSitzverteilung : col) {
         addFiktiveSitzverteilung(fiktiveSitzverteilung);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link FiktiveSitzverteilung}.
     *
     * @param fiktiveSitzverteilung FiktiveSitzverteilung-EJBObject, which is removed from the set.
     */
   public void removeFiktiveSitzverteilung(FiktiveSitzverteilung fiktiveSitzverteilung) {
      fiktiveSitzverteilung.setID_Ergebniseingang(null);
   }

   /**
     * Returns the set of entities of the type {@link Gebiet}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Gebiet}
     * @throws EJBException: an error occurred
     */
   public Collection<Gebiet> getLetzterEingangCol() throws EJBException {
      GebietHome gebietHome = GebietHome.HomeFinder.findHome(this);
      try {
         return gebietHome.findAllByLetzterEingang(_details.getID_Ergebniseingang());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Gebiet}.
     *
     * @param letzterEingang Gebiet-object
     */
   public void addLetzterEingang(Gebiet letzterEingang) {
      letzterEingang.setID_LetzterEingang(_details.getID_Ergebniseingang());
   }

   /**
     * Adds the object to the set of entities of the type {@link Gebiet}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllLetzterEingangCol(Collection<Gebiet> col) {
      for (Gebiet letzterEingang : col) {
         addLetzterEingang(letzterEingang);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Gebiet}.
     *
     * @param letzterEingang Gebiet-EJBObject, which is removed from the set.
     */
   public void removeLetzterEingang(Gebiet letzterEingang) {
      letzterEingang.setID_LetzterEingang(null);
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
         return gebietHome.findAllByErgebniseingang(_details.getID_Ergebniseingang());
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
      addID_Gebiet((String)gebiet.getPrimaryKey());
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
     * Adds the object, which is marked by the ID, to the set of entities of the type {@link Gebiet}.
     *
     * @param id_Gebiet ID of Gebiet entity
     * @throws EJBException: an error occurred
     */
   public void addID_Gebiet(String id_Gebiet) throws EJBException {
      try {
         Gebiet_ErgebniseingangModelImpl model =
            new Gebiet_ErgebniseingangModelImpl (id_Gebiet, _details.getID_Ergebniseingang());
         Gebiet_ErgebniseingangDBA.insert (model);
      } catch (Exception e) {
         throw new EJBException("Unable to change Table Gebiet_Ergebniseingang Exception: " + //$NON-NLS-1$
            e.getMessage(), e);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Gebiet}.
     *
     * @param gebiet Gebiet-EJBObject, which is removed from the set.
     */
   public void removeGebiet(Gebiet gebiet) {
      removeID_Gebiet((String)gebiet.getPrimaryKey());
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Gebiet}.
     *
     * @param id_Gebiet ID of the Gebiet entity to be deleted
     * @throws EJBException: an error occurred
     */
   public void removeID_Gebiet(String id_Gebiet) throws EJBException {
      try {
         Gebiet_ErgebniseingangDBA.deleteByKey(id_Gebiet, _details.getID_Ergebniseingang());
      } catch (Exception e) {
         throw new EJBException("Unable to remove the Gebiet Entry from the table Gebiet_Ergebniseingang Exception: " + //$NON-NLS-1$
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
         return gebietsstatusHome.findAllByErgebniseingang(_details.getID_Ergebniseingang());
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
      gebietsstatus.setID_Ergebniseingang(_details.getID_Ergebniseingang());
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
      gebietsstatus.setID_Ergebniseingang(null);
   }

   /**
     * Returns the set of entities of the type {@link Konflikt}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Konflikt}
     * @throws EJBException: an error occurred
     */
   public Collection<Konflikt> getKonfliktCol() throws EJBException {
      KonfliktHome konfliktHome = KonfliktHome.HomeFinder.findHome(this);
      try {
         return konfliktHome.findAllByErgebniseingang(_details.getID_Ergebniseingang());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Konflikt}.
     *
     * @param konflikt Konflikt-object
     */
   public void addKonflikt(Konflikt konflikt) {
      konflikt.setID_Ergebniseingang(_details.getID_Ergebniseingang());
   }

   /**
     * Adds the object to the set of entities of the type {@link Konflikt}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllKonfliktCol(Collection<Konflikt> col) {
      for (Konflikt konflikt : col) {
         addKonflikt(konflikt);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Konflikt}.
     *
     * @param konflikt Konflikt-EJBObject, which is removed from the set.
     */
   public void removeKonflikt(Konflikt konflikt) {
      konflikt.setID_Ergebniseingang(null);
   }

   /**
     * Returns the set of entities of the type {@link ListenkandidaturErgebnis}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link ListenkandidaturErgebnis}
     * @throws EJBException: an error occurred
     */
   public Collection<ListenkandidaturErgebnis> getListenkandidaturErgebnisCol() throws EJBException {
      ListenkandidaturErgebnisHome listenkandidaturErgebnisHome = ListenkandidaturErgebnisHome.HomeFinder.findHome(this);
      try {
         return listenkandidaturErgebnisHome.findAllByErgebniseingang(_details.getID_Ergebniseingang());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link ListenkandidaturErgebnis}.
     *
     * @param listenkandidaturErgebnis ListenkandidaturErgebnis-object
     */
   public void addListenkandidaturErgebnis(ListenkandidaturErgebnis listenkandidaturErgebnis) {
      listenkandidaturErgebnis.setID_Ergebniseingang(_details.getID_Ergebniseingang());
   }

   /**
     * Adds the object to the set of entities of the type {@link ListenkandidaturErgebnis}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllListenkandidaturErgebnisCol(Collection<ListenkandidaturErgebnis> col) {
      for (ListenkandidaturErgebnis listenkandidaturErgebnis : col) {
         addListenkandidaturErgebnis(listenkandidaturErgebnis);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link ListenkandidaturErgebnis}.
     *
     * @param listenkandidaturErgebnis ListenkandidaturErgebnis-EJBObject, which is removed from the set.
     */
   public void removeListenkandidaturErgebnis(ListenkandidaturErgebnis listenkandidaturErgebnis) {
      listenkandidaturErgebnis.setID_Ergebniseingang(null);
   }

   /**
     * Returns the set of entities of the type {@link ListenkombinationZulassung}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link ListenkombinationZulassung}
     * @throws EJBException: an error occurred
     */
   public Collection<ListenkombinationZulassung> getListenkombinationZulassungCol() throws EJBException {
      ListenkombinationZulassungHome listenkombinationZulassungHome = ListenkombinationZulassungHome.HomeFinder.findHome(this);
      try {
         return listenkombinationZulassungHome.findAllByErgebniseingang(_details.getID_Ergebniseingang());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link ListenkombinationZulassung}.
     *
     * @param listenkombinationZulassung ListenkombinationZulassung-object
     */
   public void addListenkombinationZulassung(ListenkombinationZulassung listenkombinationZulassung) {
      listenkombinationZulassung.setID_Ergebniseingang(_details.getID_Ergebniseingang());
   }

   /**
     * Adds the object to the set of entities of the type {@link ListenkombinationZulassung}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllListenkombinationZulassungCol(Collection<ListenkombinationZulassung> col) {
      for (ListenkombinationZulassung listenkombinationZulassung : col) {
         addListenkombinationZulassung(listenkombinationZulassung);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link ListenkombinationZulassung}.
     *
     * @param listenkombinationZulassung ListenkombinationZulassung-EJBObject, which is removed from the set.
     */
   public void removeListenkombinationZulassung(ListenkombinationZulassung listenkombinationZulassung) {
      listenkombinationZulassung.setID_Ergebniseingang(null);
   }

   /**
     * Returns the set of entities of the type {@link ListenplatzNeu}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link ListenplatzNeu}
     * @throws EJBException: an error occurred
     */
   public Collection<ListenplatzNeu> getListenplatzNeuCol() throws EJBException {
      ListenplatzNeuHome listenplatzNeuHome = ListenplatzNeuHome.HomeFinder.findHome(this);
      try {
         return listenplatzNeuHome.findAllByErgebniseingang(_details.getID_Ergebniseingang());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link ListenplatzNeu}.
     *
     * @param listenplatzNeu ListenplatzNeu-object
     */
   public void addListenplatzNeu(ListenplatzNeu listenplatzNeu) {
      listenplatzNeu.setID_Ergebniseingang(_details.getID_Ergebniseingang());
   }

   /**
     * Adds the object to the set of entities of the type {@link ListenplatzNeu}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllListenplatzNeuCol(Collection<ListenplatzNeu> col) {
      for (ListenplatzNeu listenplatzNeu : col) {
         addListenplatzNeu(listenplatzNeu);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link ListenplatzNeu}.
     *
     * @param listenplatzNeu ListenplatzNeu-EJBObject, which is removed from the set.
     */
   public void removeListenplatzNeu(ListenplatzNeu listenplatzNeu) {
      listenplatzNeu.setID_Ergebniseingang(null);
   }

   /**
     * Returns the set of entities of the type {@link Restsitzverteilung}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Restsitzverteilung}
     * @throws EJBException: an error occurred
     */
   public Collection<Restsitzverteilung> getRestsitzverteilungCol() throws EJBException {
      RestsitzverteilungHome restsitzverteilungHome = RestsitzverteilungHome.HomeFinder.findHome(this);
      try {
         return restsitzverteilungHome.findAllByErgebniseingang(_details.getID_Ergebniseingang());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Restsitzverteilung}.
     *
     * @param restsitzverteilung Restsitzverteilung-object
     */
   public void addRestsitzverteilung(Restsitzverteilung restsitzverteilung) {
      restsitzverteilung.setID_Ergebniseingang(_details.getID_Ergebniseingang());
   }

   /**
     * Adds the object to the set of entities of the type {@link Restsitzverteilung}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllRestsitzverteilungCol(Collection<Restsitzverteilung> col) {
      for (Restsitzverteilung restsitzverteilung : col) {
         addRestsitzverteilung(restsitzverteilung);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Restsitzverteilung}.
     *
     * @param restsitzverteilung Restsitzverteilung-EJBObject, which is removed from the set.
     */
   public void removeRestsitzverteilung(Restsitzverteilung restsitzverteilung) {
      restsitzverteilung.setID_Ergebniseingang(null);
   }

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link SitzberechnungErgebnis}
     * @throws EJBException: an error occurred
     */
   public Collection<SitzberechnungErgebnis> getSitzberechnungErgebnisCol() throws EJBException {
      SitzberechnungErgebnisHome sitzberechnungErgebnisHome = SitzberechnungErgebnisHome.HomeFinder.findHome(this);
      try {
         return sitzberechnungErgebnisHome.findAllByErgebniseingang(_details.getID_Ergebniseingang());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link SitzberechnungErgebnis}.
     *
     * @param sitzberechnungErgebnis SitzberechnungErgebnis-object
     */
   public void addSitzberechnungErgebnis(SitzberechnungErgebnis sitzberechnungErgebnis) {
      sitzberechnungErgebnis.setID_Ergebniseingang(_details.getID_Ergebniseingang());
   }

   /**
     * Adds the object to the set of entities of the type {@link SitzberechnungErgebnis}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllSitzberechnungErgebnisCol(Collection<SitzberechnungErgebnis> col) {
      for (SitzberechnungErgebnis sitzberechnungErgebnis : col) {
         addSitzberechnungErgebnis(sitzberechnungErgebnis);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link SitzberechnungErgebnis}.
     *
     * @param sitzberechnungErgebnis SitzberechnungErgebnis-EJBObject, which is removed from the set.
     */
   public void removeSitzberechnungErgebnis(SitzberechnungErgebnis sitzberechnungErgebnis) {
      sitzberechnungErgebnis.setID_Ergebniseingang(null);
   }

   /**
     * Returns the set of entities of the type {@link Sitzverteilung}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Sitzverteilung}
     * @throws EJBException: an error occurred
     */
   public Collection<Sitzverteilung> getSitzverteilungCol() throws EJBException {
      SitzverteilungHome sitzverteilungHome = SitzverteilungHome.HomeFinder.findHome(this);
      try {
         return sitzverteilungHome.findAllByErgebniseingang(_details.getID_Ergebniseingang());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Sitzverteilung}.
     *
     * @param sitzverteilung Sitzverteilung-object
     */
   public void addSitzverteilung(Sitzverteilung sitzverteilung) {
      sitzverteilung.setID_Ergebniseingang(_details.getID_Ergebniseingang());
   }

   /**
     * Adds the object to the set of entities of the type {@link Sitzverteilung}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllSitzverteilungCol(Collection<Sitzverteilung> col) {
      for (Sitzverteilung sitzverteilung : col) {
         addSitzverteilung(sitzverteilung);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Sitzverteilung}.
     *
     * @param sitzverteilung Sitzverteilung-EJBObject, which is removed from the set.
     */
   public void removeSitzverteilung(Sitzverteilung sitzverteilung) {
      sitzverteilung.setID_Ergebniseingang(null);
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
         return stimmergebnisHome.findAllByErgebniseingang(_details.getID_Ergebniseingang());
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
      stimmergebnis.setID_Ergebniseingang(_details.getID_Ergebniseingang());
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
      stimmergebnis.setID_Ergebniseingang(null);
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
         return stimmergebnisseUntergebieteHome.findAllByErgebniseingang(_details.getID_Ergebniseingang());
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
      stimmergebnisseUntergebiete.setID_Ergebniseingang(_details.getID_Ergebniseingang());
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
      stimmergebnisseUntergebiete.setID_Ergebniseingang(null);
   }

   /**
     * Returns the set of entities of the type {@link Unterverteilung}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Unterverteilung}
     * @throws EJBException: an error occurred
     */
   public Collection<Unterverteilung> getUnterverteilungCol() throws EJBException {
      UnterverteilungHome unterverteilungHome = UnterverteilungHome.HomeFinder.findHome(this);
      try {
         return unterverteilungHome.findAllByErgebniseingang(_details.getID_Ergebniseingang());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Unterverteilung}.
     *
     * @param unterverteilung Unterverteilung-object
     */
   public void addUnterverteilung(Unterverteilung unterverteilung) {
      unterverteilung.setID_Ergebniseingang(_details.getID_Ergebniseingang());
   }

   /**
     * Adds the object to the set of entities of the type {@link Unterverteilung}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllUnterverteilungCol(Collection<Unterverteilung> col) {
      for (Unterverteilung unterverteilung : col) {
         addUnterverteilung(unterverteilung);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Unterverteilung}.
     *
     * @param unterverteilung Unterverteilung-EJBObject, which is removed from the set.
     */
   public void removeUnterverteilung(Unterverteilung unterverteilung) {
      unterverteilung.setID_Ergebniseingang(null);
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
         if (_details.getID_Ergebniseingang() != null) {
            string += "id_Ergebniseingang = " + _details.getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (_details.getID_Erfassungseinheit() != null) {
            string +=  ", id_Erfassungseinheit = " + _details.getID_Erfassungseinheit(); //$NON-NLS-1$
         }
         if (_details.getID_Wahl() != null) {
            string +=  ", id_Wahl = " + _details.getID_Wahl(); //$NON-NLS-1$
         }
         if (_details.getAnwenderName() != null) {
            string +=  ", anwenderName = " + _details.getAnwenderName(); //$NON-NLS-1$
         }
         if (_details.getZeitstempel() != null) {
            string +=  ", zeitstempel = " + _details.getZeitstempel(); //$NON-NLS-1$
         }
         string +=  ", herkunft = " + _details.getHerkunft(); //$NON-NLS-1$
         string +=  ", wahlergebnisart = " + _details.getWahlergebnisart(); //$NON-NLS-1$
         string +=  ", unterschiedeVorhanden = " + _details.getUnterschiedeVorhanden(); //$NON-NLS-1$
         string +=  ", status = " + _details.getStatus(); //$NON-NLS-1$
         if (_details.getErgebnisHash() != null) {
            string +=  ", ergebnisHash = " + _details.getErgebnisHash(); //$NON-NLS-1$
         }
         if (_details.getFehlermeldung() != null) {
            string +=  ", fehlermeldung = " + _details.getFehlermeldung(); //$NON-NLS-1$
         }
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
