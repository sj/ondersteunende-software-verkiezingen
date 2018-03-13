/*
 * BesonderheitBean
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
import de.ivu.wahl.modell.BesonderheitModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Besonderheit as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class BesonderheitBean extends BMPBeanBase implements EntityBean, BesonderheitModel {
   private static final Category LOGGER = Log4J.configure(BesonderheitBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BesonderheitBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected BesonderheitModel _details = null;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(BesonderheitModel details) throws CreateException, EJBException {
      BesonderheitModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Besonderheit();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Besonderheit key element of the type {@link String}
     * @param id_Ergebniseingang key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Besonderheit, String id_Ergebniseingang) throws CreateException, EJBException {
      _details = (BesonderheitModel)createModel(id_Besonderheit);
      _details.setID_Ergebniseingang(id_Ergebniseingang);
      _details.setText(""); //$NON-NLS-1$
      create(_details);
      return id_Besonderheit;
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
         return BesonderheitDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_UebergeordneteBesonderheit ID of the objects to be searched
     * @return  {@link Collection} of the found Besonderheit-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByUebergeordneteBesonderheit(String id_UebergeordneteBesonderheit) throws IVUFinderException {
      try {
         return BesonderheitDBA.retrieveIDsByID_UebergeordneteBesonderheit(id_UebergeordneteBesonderheit);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Ergebniseingang ID of the objects to be searched
     * @return  {@link Collection} of the found Besonderheit-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByErgebniseingang(String id_Ergebniseingang) throws IVUFinderException {
      try {
         return BesonderheitDBA.retrieveIDsByID_Ergebniseingang(id_Ergebniseingang);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Listenkombination ID of the objects to be searched
     * @return  {@link Collection} of the found Besonderheit-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByListenkombination(String id_Listenkombination) throws IVUFinderException {
      try {
         return BesonderheitDBA.retrieveIDsByID_Listenkombination(id_Listenkombination);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gruppe ID of the objects to be searched
     * @return  {@link Collection} of the found Besonderheit-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByGruppe(String id_Gruppe) throws IVUFinderException {
      try {
         return BesonderheitDBA.retrieveIDsByID_Gruppe(id_Gruppe);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Liste ID of the objects to be searched
     * @return  {@link Collection} of the found Besonderheit-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByListe(String id_Liste) throws IVUFinderException {
      try {
         return BesonderheitDBA.retrieveIDsByID_Liste(id_Liste);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Personendaten ID of the objects to be searched
     * @return  {@link Collection} of the found Besonderheit-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByPersonendaten(String id_Personendaten) throws IVUFinderException {
      try {
         return BesonderheitDBA.retrieveIDsByID_Personendaten(id_Personendaten);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Besonderheit}, filtered by besonderheitart.
     *
     * @param besonderheitart searching condition
     * @return  {@link Collection} of primary keys of the entities Besonderheit
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByBesonderheitart(int besonderheitart) throws IVUFinderException {
      try {
         return BesonderheitDBA.retrieveIDsByBesonderheitart(besonderheitart);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Besonderheit}, filtered by anzahl.
     *
     * @param anzahl searching condition
     * @return  {@link Collection} of primary keys of the entities Besonderheit
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByAnzahl(int anzahl) throws IVUFinderException {
      try {
         return BesonderheitDBA.retrieveIDsByAnzahl(anzahl);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Besonderheit}, filtered by text.
     *
     * @param text searching condition
     * @return  {@link Collection} of primary keys of the entities Besonderheit
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByText(String text) throws IVUFinderException {
      try {
         return BesonderheitDBA.retrieveIDsByText(text);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Besonderheit}, filtered by text.
     *
     * @param text searching condition
     * @return  {@link Collection} of primary keys of the entities Besonderheit
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeText(String text) throws IVUFinderException {
      try {
         return BesonderheitDBA.retrieveIDsLikeText(text);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Besonderheit}, filtered by nummer.
     *
     * @param nummer searching condition
     * @return  {@link Collection} of primary keys of the entities Besonderheit
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByNummer(int nummer) throws IVUFinderException {
      try {
         return BesonderheitDBA.retrieveIDsByNummer(nummer);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Besonderheit key element of the type {@link String}
     * @param id_Ergebniseingang key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Besonderheit, String id_Ergebniseingang) {
      // No functionality required
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(BesonderheitModel details) {
      // No functionality required
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public BesonderheitModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(BesonderheitModel details) {
      if (details instanceof BesonderheitModelImpl && _details instanceof BesonderheitModelImpl) {
         ((BesonderheitModelImpl)_details).copyFrom((BesonderheitModelImpl)details);
         checkRelations();
      } else {
         setID_Ergebniseingang(details.getID_Ergebniseingang());
         setID_UebergeordneteBesonderheit(details.getID_UebergeordneteBesonderheit());
         setID_Listenkombination(details.getID_Listenkombination());
         setID_Gruppe(details.getID_Gruppe());
         setID_Liste(details.getID_Liste());
         setID_Personendaten(details.getID_Personendaten());
         setBesonderheitart(details.getBesonderheitart());
         setAnzahl(details.getAnzahl());
         setText(details.getText());
         setNummer(details.getNummer());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (BesonderheitModel)details;
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
      if (null == _details.getID_UebergeordneteBesonderheit()) {
         _uebergeordneteBesonderheit = null;
         _relchk_UebergeordneteBesonderheit = true;
      } else {
         _relchk_UebergeordneteBesonderheit = false;
      }
      if (null == _details.getID_Ergebniseingang()) {
         _ergebniseingang = null;
         _relchk_Ergebniseingang = true;
      } else {
         _relchk_Ergebniseingang = false;
      }
      if (null == _details.getID_Listenkombination()) {
         _listenkombination = null;
         _relchk_Listenkombination = true;
      } else {
         _relchk_Listenkombination = false;
      }
      if (null == _details.getID_Gruppe()) {
         _gruppe = null;
         _relchk_Gruppe = true;
      } else {
         _relchk_Gruppe = false;
      }
      if (null == _details.getID_Liste()) {
         _liste = null;
         _relchk_Liste = true;
      } else {
         _relchk_Liste = false;
      }
      if (null == _details.getID_Personendaten()) {
         _personendaten = null;
         _relchk_Personendaten = true;
      } else {
         _relchk_Personendaten = false;
      }
   }

   @Override
   protected void resetRelations() {
      _uebergeordneteBesonderheit = null;
      _ergebniseingang = null;
      _listenkombination = null;
      _gruppe = null;
      _liste = null;
      _personendaten = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public BesonderheitModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Besonderheit in the entity Besonderheit
     *
     * @return value of the attribute id_Besonderheit
     */
   public String getID_Besonderheit() {
      return _details.getID_Besonderheit();
   }

   /**
     * Sets the value of id_Ergebniseingang in the entity Besonderheit
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   public void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (null == id_Ergebniseingang) {
         _ergebniseingang = null;
         _relchk_Ergebniseingang = true;
      } else {
         String old = _details.getID_Ergebniseingang();
         if (old == null || !old.equals(id_Ergebniseingang)) {
            _relchk_Ergebniseingang = false;
         }
      }
      _details.setID_Ergebniseingang(id_Ergebniseingang);
   }

   /**
     * Gets the value of id_Ergebniseingang in the entity Besonderheit
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _details.getID_Ergebniseingang();
   }

   /**
     * Sets the value of id_UebergeordneteBesonderheit in the entity Besonderheit
     *
     * @param id_UebergeordneteBesonderheit new value of the attribute id_UebergeordneteBesonderheit
     */
   public void setID_UebergeordneteBesonderheit(String id_UebergeordneteBesonderheit) {
      if (null == id_UebergeordneteBesonderheit) {
         _uebergeordneteBesonderheit = null;
         _relchk_UebergeordneteBesonderheit = true;
      } else {
         String old = _details.getID_UebergeordneteBesonderheit();
         if (old == null || !old.equals(id_UebergeordneteBesonderheit)) {
            _relchk_UebergeordneteBesonderheit = false;
         }
      }
      _details.setID_UebergeordneteBesonderheit(id_UebergeordneteBesonderheit);
   }

   /**
     * Gets the value of id_UebergeordneteBesonderheit in the entity Besonderheit
     *
     * @return value of the attribute id_UebergeordneteBesonderheit
     */
   public String getID_UebergeordneteBesonderheit() {
      return _details.getID_UebergeordneteBesonderheit();
   }

   /**
     * Sets the value of id_Listenkombination in the entity Besonderheit
     *
     * @param id_Listenkombination new value of the attribute id_Listenkombination
     */
   public void setID_Listenkombination(String id_Listenkombination) {
      if (null == id_Listenkombination) {
         _listenkombination = null;
         _relchk_Listenkombination = true;
      } else {
         String old = _details.getID_Listenkombination();
         if (old == null || !old.equals(id_Listenkombination)) {
            _relchk_Listenkombination = false;
         }
      }
      _details.setID_Listenkombination(id_Listenkombination);
   }

   /**
     * Gets the value of id_Listenkombination in the entity Besonderheit
     *
     * @return value of the attribute id_Listenkombination
     */
   public String getID_Listenkombination() {
      return _details.getID_Listenkombination();
   }

   /**
     * Sets the value of id_Gruppe in the entity Besonderheit
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   public void setID_Gruppe(String id_Gruppe) {
      if (null == id_Gruppe) {
         _gruppe = null;
         _relchk_Gruppe = true;
      } else {
         String old = _details.getID_Gruppe();
         if (old == null || !old.equals(id_Gruppe)) {
            _relchk_Gruppe = false;
         }
      }
      _details.setID_Gruppe(id_Gruppe);
   }

   /**
     * Gets the value of id_Gruppe in the entity Besonderheit
     *
     * @return value of the attribute id_Gruppe
     */
   public String getID_Gruppe() {
      return _details.getID_Gruppe();
   }

   /**
     * Sets the value of id_Liste in the entity Besonderheit
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   public void setID_Liste(String id_Liste) {
      if (null == id_Liste) {
         _liste = null;
         _relchk_Liste = true;
      } else {
         String old = _details.getID_Liste();
         if (old == null || !old.equals(id_Liste)) {
            _relchk_Liste = false;
         }
      }
      _details.setID_Liste(id_Liste);
   }

   /**
     * Gets the value of id_Liste in the entity Besonderheit
     *
     * @return value of the attribute id_Liste
     */
   public String getID_Liste() {
      return _details.getID_Liste();
   }

   /**
     * Sets the value of id_Personendaten in the entity Besonderheit
     *
     * @param id_Personendaten new value of the attribute id_Personendaten
     */
   public void setID_Personendaten(String id_Personendaten) {
      if (null == id_Personendaten) {
         _personendaten = null;
         _relchk_Personendaten = true;
      } else {
         String old = _details.getID_Personendaten();
         if (old == null || !old.equals(id_Personendaten)) {
            _relchk_Personendaten = false;
         }
      }
      _details.setID_Personendaten(id_Personendaten);
   }

   /**
     * Gets the value of id_Personendaten in the entity Besonderheit
     *
     * @return value of the attribute id_Personendaten
     */
   public String getID_Personendaten() {
      return _details.getID_Personendaten();
   }

   /**
     * Sets the value of besonderheitart in the entity Besonderheit
     *
     * @param besonderheitart new value of the attribute besonderheitart
     */
   public void setBesonderheitart(int besonderheitart) {
      _details.setBesonderheitart(besonderheitart);
   }

   /**
     * Gets the value of besonderheitart in the entity Besonderheit
     *
     * @return value of the attribute besonderheitart
     */
   public int getBesonderheitart() {
      return _details.getBesonderheitart();
   }

   /**
     * Sets the value of anzahl in the entity Besonderheit
     *
     * @param anzahl new value of the attribute anzahl
     */
   public void setAnzahl(int anzahl) {
      _details.setAnzahl(anzahl);
   }

   /**
     * Gets the value of anzahl in the entity Besonderheit
     *
     * @return value of the attribute anzahl
     */
   public int getAnzahl() {
      return _details.getAnzahl();
   }

   /**
     * Sets the value of text in the entity Besonderheit
     *
     * @param text new value of the attribute text
     */
   public void setText(String text) {
      _details.setText(text);
   }

   /**
     * Gets the value of text in the entity Besonderheit
     *
     * @return value of the attribute text
     */
   public String getText() {
      return _details.getText();
   }

   /**
     * Sets the value of nummer in the entity Besonderheit
     *
     * @param nummer new value of the attribute nummer
     */
   public void setNummer(int nummer) {
      _details.setNummer(nummer);
   }

   /**
     * Gets the value of nummer in the entity Besonderheit
     *
     * @return value of the attribute nummer
     */
   public int getNummer() {
      return _details.getNummer();
   }

   /**
     * Relation zu UebergeordneteBesonderheit
     */
   protected Besonderheit _uebergeordneteBesonderheit;

   /**
     * Flag for the validity of the relation UebergeordneteBesonderheit
     */
   protected boolean _relchk_UebergeordneteBesonderheit = false;

   /**
     * Navigation to the associated entity of the type {@link Besonderheit}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Besonderheit getUebergeordneteBesonderheit() throws EJBException {
      if (!_relchk_UebergeordneteBesonderheit) {
         if (null == _details.getID_UebergeordneteBesonderheit()) {
            _uebergeordneteBesonderheit = null;
         } else if (null == _uebergeordneteBesonderheit || !_uebergeordneteBesonderheit.getPrimaryKey().equals(_details.getID_UebergeordneteBesonderheit())) {
            try {
               BesonderheitHome home = BesonderheitHome.HomeFinder.findHome(this);
               _uebergeordneteBesonderheit = home.findByPrimaryKey(_details.getID_UebergeordneteBesonderheit());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find UebergeordneteBesonderheit", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Besonderheit", fe); //$NON-NLS-1$
            }
         }
         _relchk_UebergeordneteBesonderheit = true;
      }
      return _uebergeordneteBesonderheit;
   }

   /**
     * Setting of the associated entity of the type {@link Besonderheit}
     *
     * @param uebergeordneteBesonderheit the corresponding EJBObject
     */
   public void setUebergeordneteBesonderheit(Besonderheit uebergeordneteBesonderheit) {
      _uebergeordneteBesonderheit = uebergeordneteBesonderheit;
      _details.setID_UebergeordneteBesonderheit(uebergeordneteBesonderheit == null ? null : (String)uebergeordneteBesonderheit.getPrimaryKey());
   }

   /**
     * Relation zu Ergebniseingang
     */
   protected Ergebniseingang _ergebniseingang;

   /**
     * Flag for the validity of the relation Ergebniseingang
     */
   protected boolean _relchk_Ergebniseingang = false;

   /**
     * Navigation to the associated entity of the type {@link Ergebniseingang}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Ergebniseingang getErgebniseingang() throws EJBException {
      if (!_relchk_Ergebniseingang) {
         if (null == _details.getID_Ergebniseingang()) {
            _ergebniseingang = null;
         } else if (null == _ergebniseingang || !_ergebniseingang.getPrimaryKey().equals(_details.getID_Ergebniseingang())) {
            try {
               ErgebniseingangHome home = ErgebniseingangHome.HomeFinder.findHome(this);
               _ergebniseingang = home.findByPrimaryKey(_details.getID_Ergebniseingang());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find Ergebniseingang", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Ergebniseingang", fe); //$NON-NLS-1$
            }
         }
         _relchk_Ergebniseingang = true;
      }
      return _ergebniseingang;
   }

   /**
     * Setting of the associated entity of the type {@link Ergebniseingang}
     *
     * @param ergebniseingang the corresponding EJBObject
     */
   public void setErgebniseingang(Ergebniseingang ergebniseingang) {
      _ergebniseingang = ergebniseingang;
      _details.setID_Ergebniseingang(ergebniseingang == null ? null : (String)ergebniseingang.getPrimaryKey());
   }

   /**
     * Relation zu Listenkombination
     */
   protected Listenkombination _listenkombination;

   /**
     * Flag for the validity of the relation Listenkombination
     */
   protected boolean _relchk_Listenkombination = false;

   /**
     * Navigation to the associated entity of the type {@link Listenkombination}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Listenkombination getListenkombination() throws EJBException {
      if (!_relchk_Listenkombination) {
         if (null == _details.getID_Listenkombination()) {
            _listenkombination = null;
         } else if (null == _listenkombination || !_listenkombination.getPrimaryKey().equals(_details.getID_Listenkombination())) {
            try {
               ListenkombinationHome home = ListenkombinationHome.HomeFinder.findHome(this);
               _listenkombination = home.findByPrimaryKey(_details.getID_Listenkombination());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find Listenkombination", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Listenkombination", fe); //$NON-NLS-1$
            }
         }
         _relchk_Listenkombination = true;
      }
      return _listenkombination;
   }

   /**
     * Setting of the associated entity of the type {@link Listenkombination}
     *
     * @param listenkombination the corresponding EJBObject
     */
   public void setListenkombination(Listenkombination listenkombination) {
      _listenkombination = listenkombination;
      _details.setID_Listenkombination(listenkombination == null ? null : (String)listenkombination.getPrimaryKey());
   }

   /**
     * Relation zu Gruppe
     */
   protected Gruppe _gruppe;

   /**
     * Flag for the validity of the relation Gruppe
     */
   protected boolean _relchk_Gruppe = false;

   /**
     * Navigation to the associated entity of the type {@link Gruppe}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Gruppe getGruppe() throws EJBException {
      if (!_relchk_Gruppe) {
         if (null == _details.getID_Gruppe()) {
            _gruppe = null;
         } else if (null == _gruppe || !_gruppe.getPrimaryKey().equals(_details.getID_Gruppe())) {
            try {
               GruppeHome home = GruppeHome.HomeFinder.findHome(this);
               _gruppe = home.findByPrimaryKey(_details.getID_Gruppe());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find Gruppe", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Gruppe", fe); //$NON-NLS-1$
            }
         }
         _relchk_Gruppe = true;
      }
      return _gruppe;
   }

   /**
     * Setting of the associated entity of the type {@link Gruppe}
     *
     * @param gruppe the corresponding EJBObject
     */
   public void setGruppe(Gruppe gruppe) {
      _gruppe = gruppe;
      _details.setID_Gruppe(gruppe == null ? null : (String)gruppe.getPrimaryKey());
   }

   /**
     * Relation zu Liste
     */
   protected Liste _liste;

   /**
     * Flag for the validity of the relation Liste
     */
   protected boolean _relchk_Liste = false;

   /**
     * Navigation to the associated entity of the type {@link Liste}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Liste getListe() throws EJBException {
      if (!_relchk_Liste) {
         if (null == _details.getID_Liste()) {
            _liste = null;
         } else if (null == _liste || !_liste.getPrimaryKey().equals(_details.getID_Liste())) {
            try {
               ListeHome home = ListeHome.HomeFinder.findHome(this);
               _liste = home.findByPrimaryKey(_details.getID_Liste());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find Liste", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Liste", fe); //$NON-NLS-1$
            }
         }
         _relchk_Liste = true;
      }
      return _liste;
   }

   /**
     * Setting of the associated entity of the type {@link Liste}
     *
     * @param liste the corresponding EJBObject
     */
   public void setListe(Liste liste) {
      _liste = liste;
      _details.setID_Liste(liste == null ? null : (String)liste.getPrimaryKey());
   }

   /**
     * Relation zu Personendaten
     */
   protected Personendaten _personendaten;

   /**
     * Flag for the validity of the relation Personendaten
     */
   protected boolean _relchk_Personendaten = false;

   /**
     * Navigation to the associated entity of the type {@link Personendaten}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Personendaten getPersonendaten() throws EJBException {
      if (!_relchk_Personendaten) {
         if (null == _details.getID_Personendaten()) {
            _personendaten = null;
         } else if (null == _personendaten || !_personendaten.getPrimaryKey().equals(_details.getID_Personendaten())) {
            try {
               PersonendatenHome home = PersonendatenHome.HomeFinder.findHome(this);
               _personendaten = home.findByPrimaryKey(_details.getID_Personendaten());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find Personendaten", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Personendaten", fe); //$NON-NLS-1$
            }
         }
         _relchk_Personendaten = true;
      }
      return _personendaten;
   }

   /**
     * Setting of the associated entity of the type {@link Personendaten}
     *
     * @param personendaten the corresponding EJBObject
     */
   public void setPersonendaten(Personendaten personendaten) {
      _personendaten = personendaten;
      _details.setID_Personendaten(personendaten == null ? null : (String)personendaten.getPrimaryKey());
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
         return besonderheitHome.findAllByUebergeordneteBesonderheit(_details.getID_Besonderheit());
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
      besonderheit.setID_UebergeordneteBesonderheit(_details.getID_Besonderheit());
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
      besonderheit.setID_UebergeordneteBesonderheit(null);
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
         if (_details.getID_Besonderheit() != null) {
            string += "id_Besonderheit = " + _details.getID_Besonderheit(); //$NON-NLS-1$
         }
         if (_details.getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + _details.getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (_details.getID_UebergeordneteBesonderheit() != null) {
            string +=  ", id_UebergeordneteBesonderheit = " + _details.getID_UebergeordneteBesonderheit(); //$NON-NLS-1$
         }
         if (_details.getID_Listenkombination() != null) {
            string +=  ", id_Listenkombination = " + _details.getID_Listenkombination(); //$NON-NLS-1$
         }
         if (_details.getID_Gruppe() != null) {
            string +=  ", id_Gruppe = " + _details.getID_Gruppe(); //$NON-NLS-1$
         }
         if (_details.getID_Liste() != null) {
            string +=  ", id_Liste = " + _details.getID_Liste(); //$NON-NLS-1$
         }
         if (_details.getID_Personendaten() != null) {
            string +=  ", id_Personendaten = " + _details.getID_Personendaten(); //$NON-NLS-1$
         }
         string +=  ", besonderheitart = " + _details.getBesonderheitart(); //$NON-NLS-1$
         string +=  ", anzahl = " + _details.getAnzahl(); //$NON-NLS-1$
         if (_details.getText() != null) {
            string +=  ", text = " + _details.getText(); //$NON-NLS-1$
         }
         string +=  ", nummer = " + _details.getNummer(); //$NON-NLS-1$
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
