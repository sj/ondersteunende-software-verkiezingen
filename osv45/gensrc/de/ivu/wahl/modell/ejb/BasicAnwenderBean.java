/*
 * BasicAnwenderBean
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
import de.ivu.wahl.modell.AnwenderModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Anwender as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicAnwenderBean extends BMPBeanBase implements EntityBean, AnwenderModel {
   private static final Category LOGGER = Log4J.configure(BasicAnwenderBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicAnwenderBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected AnwenderModel _details = null;

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
   public String ejbCreate(AnwenderModel details) throws CreateException, EJBException {
      AnwenderModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Anwender();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Anwender key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Anwender) throws CreateException, EJBException {
      _details = (AnwenderModel)createModel(id_Anwender);
      _details.setName(""); //$NON-NLS-1$
      _details.setAnwendername(""); //$NON-NLS-1$
      _details.setPasswordHash(""); //$NON-NLS-1$
      _details.setSalt(""); //$NON-NLS-1$
      create(_details);
      return id_Anwender;
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
         return AnwenderDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Rechtegruppe ID of the objects to be searched
     * @return  {@link Collection} of the found Anwender-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByRechtegruppe(String id_Rechtegruppe) throws IVUFinderException {
      try {
         return Rechtegruppe_AnwenderDBA.retrieveIDsByID_Rechtegruppe(id_Rechtegruppe);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gebiet ID of the objects to be searched
     * @return  {@link Collection} of the found Anwender-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByGebiet(String id_Gebiet) throws IVUFinderException {
      try {
         return AnwenderDBA.retrieveIDsByID_Gebiet(id_Gebiet);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of primary keys of the entities Anwender
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByName(String name) throws IVUFinderException {
      try {
         return AnwenderDBA.retrieveIDsByName(name);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of primary keys of the entities Anwender
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeName(String name) throws IVUFinderException {
      try {
         return AnwenderDBA.retrieveIDsLikeName(name);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by anwendername.
     *
     * @param anwendername searching condition
     * @return  {@link Collection} of primary keys of the entities Anwender
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByAnwendername(String anwendername) throws IVUFinderException {
      try {
         return AnwenderDBA.retrieveIDsByAnwendername(anwendername);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by anwendername.
     *
     * @param anwendername searching condition
     * @return  {@link Collection} of primary keys of the entities Anwender
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeAnwendername(String anwendername) throws IVUFinderException {
      try {
         return AnwenderDBA.retrieveIDsLikeAnwendername(anwendername);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by passwordHash.
     *
     * @param passwordHash searching condition
     * @return  {@link Collection} of primary keys of the entities Anwender
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByPasswordHash(String passwordHash) throws IVUFinderException {
      try {
         return AnwenderDBA.retrieveIDsByPasswordHash(passwordHash);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by passwordHash.
     *
     * @param passwordHash searching condition
     * @return  {@link Collection} of primary keys of the entities Anwender
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikePasswordHash(String passwordHash) throws IVUFinderException {
      try {
         return AnwenderDBA.retrieveIDsLikePasswordHash(passwordHash);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by salt.
     *
     * @param salt searching condition
     * @return  {@link Collection} of primary keys of the entities Anwender
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllBySalt(String salt) throws IVUFinderException {
      try {
         return AnwenderDBA.retrieveIDsBySalt(salt);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by salt.
     *
     * @param salt searching condition
     * @return  {@link Collection} of primary keys of the entities Anwender
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeSalt(String salt) throws IVUFinderException {
      try {
         return AnwenderDBA.retrieveIDsLikeSalt(salt);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by fehlversucheAnmeldung.
     *
     * @param fehlversucheAnmeldung searching condition
     * @return  {@link Collection} of primary keys of the entities Anwender
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByFehlversucheAnmeldung(int fehlversucheAnmeldung) throws IVUFinderException {
      try {
         return AnwenderDBA.retrieveIDsByFehlversucheAnmeldung(fehlversucheAnmeldung);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Anwender}, filtered by letzterZugriff.
     *
     * @param letzterZugriff searching condition
     * @return  {@link Collection} of primary keys of the entities Anwender
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByLetzterZugriff(Timestamp letzterZugriff) throws IVUFinderException {
      try {
         return AnwenderDBA.retrieveIDsByLetzterZugriff(letzterZugriff);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Anwender key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Anwender) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(AnwenderModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public AnwenderModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(AnwenderModel details) {
      if (details instanceof AnwenderModelImpl && _details instanceof AnwenderModelImpl) {
         ((AnwenderModelImpl)_details).copyFrom((AnwenderModelImpl)details);
         checkRelations();
      } else {
         setID_Gebiet(details.getID_Gebiet());
         setName(details.getName());
         setAnwendername(details.getAnwendername());
         setPasswordHash(details.getPasswordHash());
         setSalt(details.getSalt());
         setFehlversucheAnmeldung(details.getFehlversucheAnmeldung());
         setLetzterZugriff(details.getLetzterZugriff());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (AnwenderModel)details;
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
      if (null == _details.getID_Gebiet()) {
         _gebiet = null;
         _relchk_Gebiet = true;
      } else {
         _relchk_Gebiet = false;
      }
   }

   @Override
   protected void resetRelations() {
      _gebiet = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public AnwenderModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Anwender in the entity Anwender
     *
     * @return value of the attribute id_Anwender
     */
   public String getID_Anwender() {
      return _details.getID_Anwender();
   }

   /**
     * Sets the value of id_Gebiet in the entity Anwender
     *
     * @param id_Gebiet new value of the attribute id_Gebiet
     */
   public void setID_Gebiet(String id_Gebiet) {
      if (null == id_Gebiet) {
         _gebiet = null;
         _relchk_Gebiet = true;
      } else {
         String old = _details.getID_Gebiet();
         if (old == null || !old.equals(id_Gebiet)) {
            _relchk_Gebiet = false;
         }
      }
      _details.setID_Gebiet(id_Gebiet);
   }

   /**
     * Gets the value of id_Gebiet in the entity Anwender
     *
     * @return value of the attribute id_Gebiet
     */
   public String getID_Gebiet() {
      return _details.getID_Gebiet();
   }

   /**
     * Sets the value of name in the entity Anwender
     *
     * @param name new value of the attribute name
     */
   public void setName(String name) {
      _details.setName(name);
   }

   /**
     * Gets the value of name in the entity Anwender
     *
     * @return value of the attribute name
     */
   public String getName() {
      return _details.getName();
   }

   /**
     * Sets the value of anwendername in the entity Anwender
     *
     * @param anwendername new value of the attribute anwendername
     */
   public void setAnwendername(String anwendername) {
      _details.setAnwendername(anwendername);
   }

   /**
     * Gets the value of anwendername in the entity Anwender
     *
     * @return value of the attribute anwendername
     */
   public String getAnwendername() {
      return _details.getAnwendername();
   }

   /**
     * Sets the value of passwordHash in the entity Anwender
     *
     * @param passwordHash new value of the attribute passwordHash
     */
   public void setPasswordHash(String passwordHash) {
      _details.setPasswordHash(passwordHash);
   }

   /**
     * Gets the value of passwordHash in the entity Anwender
     *
     * @return value of the attribute passwordHash
     */
   public String getPasswordHash() {
      return _details.getPasswordHash();
   }

   /**
     * Sets the value of salt in the entity Anwender
     *
     * @param salt new value of the attribute salt
     */
   public void setSalt(String salt) {
      _details.setSalt(salt);
   }

   /**
     * Gets the value of salt in the entity Anwender
     *
     * @return value of the attribute salt
     */
   public String getSalt() {
      return _details.getSalt();
   }

   /**
     * Sets the value of fehlversucheAnmeldung in the entity Anwender
     *
     * @param fehlversucheAnmeldung new value of the attribute fehlversucheAnmeldung
     */
   public void setFehlversucheAnmeldung(int fehlversucheAnmeldung) {
      _details.setFehlversucheAnmeldung(fehlversucheAnmeldung);
   }

   /**
     * Gets the value of fehlversucheAnmeldung in the entity Anwender
     *
     * @return value of the attribute fehlversucheAnmeldung
     */
   public int getFehlversucheAnmeldung() {
      return _details.getFehlversucheAnmeldung();
   }

   /**
     * Sets the value of letzterZugriff in the entity Anwender
     *
     * @param letzterZugriff new value of the attribute letzterZugriff
     */
   public void setLetzterZugriff(Timestamp letzterZugriff) {
      _details.setLetzterZugriff(letzterZugriff);
   }

   /**
     * Gets the value of letzterZugriff in the entity Anwender
     *
     * @return value of the attribute letzterZugriff
     */
   public Timestamp getLetzterZugriff() {
      return _details.getLetzterZugriff();
   }

   /**
     * Relation zu Gebiet
     */
   protected Gebiet _gebiet;

   /**
     * Flag for the validity of the relation Gebiet
     */
   protected boolean _relchk_Gebiet = false;

   /**
     * Navigation to the associated entity of the type {@link Gebiet}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Gebiet getGebiet() throws EJBException {
      if (!_relchk_Gebiet) {
         if (null == _details.getID_Gebiet()) {
            _gebiet = null;
         } else if (null == _gebiet || !_gebiet.getPrimaryKey().equals(_details.getID_Gebiet())) {
            try {
               GebietHome home = GebietHome.HomeFinder.findHome(this);
               _gebiet = home.findByPrimaryKey(_details.getID_Gebiet());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find Gebiet", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Gebiet", fe); //$NON-NLS-1$
            }
         }
         _relchk_Gebiet = true;
      }
      return _gebiet;
   }

   /**
     * Setting of the associated entity of the type {@link Gebiet}
     *
     * @param gebiet the corresponding EJBObject
     */
   public void setGebiet(Gebiet gebiet) {
      _gebiet = gebiet;
      _details.setID_Gebiet(gebiet == null ? null : (String)gebiet.getPrimaryKey());
   }

   /**
     * Returns the set of entities of the type {@link Rechtegruppe}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Rechtegruppe}
     * @throws EJBException: an error occurred
     */
   public Collection<Rechtegruppe> getRechtegruppeCol() throws EJBException {
      RechtegruppeHome rechtegruppeHome = RechtegruppeHome.HomeFinder.findHome(this);
      try {
         return rechtegruppeHome.findAllByAnwender(_details.getID_Anwender());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Rechtegruppe}.
     *
     * @param rechtegruppe Rechtegruppe-object
     */
   public void addRechtegruppe(Rechtegruppe rechtegruppe) {
      addID_Rechtegruppe((String)rechtegruppe.getPrimaryKey());
   }

   /**
     * Adds the object to the set of entities of the type {@link Rechtegruppe}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllRechtegruppeCol(Collection<Rechtegruppe> col) {
      for (Rechtegruppe rechtegruppe : col) {
         addRechtegruppe(rechtegruppe);
      }
   }

   /**
     * Adds the object, which is marked by the ID, to the set of entities of the type {@link Rechtegruppe}.
     *
     * @param id_Rechtegruppe ID of Rechtegruppe entity
     * @throws EJBException: an error occurred
     */
   public void addID_Rechtegruppe(String id_Rechtegruppe) throws EJBException {
      try {
         Rechtegruppe_AnwenderModelImpl model =
            new Rechtegruppe_AnwenderModelImpl (id_Rechtegruppe, _details.getID_Anwender());
         Rechtegruppe_AnwenderDBA.insert (model);
      } catch (Exception e) {
         throw new EJBException("Unable to change Table Rechtegruppe_Anwender Exception: " + //$NON-NLS-1$
            e.getMessage(), e);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Rechtegruppe}.
     *
     * @param rechtegruppe Rechtegruppe-EJBObject, which is removed from the set.
     */
   public void removeRechtegruppe(Rechtegruppe rechtegruppe) {
      removeID_Rechtegruppe((String)rechtegruppe.getPrimaryKey());
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Rechtegruppe}.
     *
     * @param id_Rechtegruppe ID of the Rechtegruppe entity to be deleted
     * @throws EJBException: an error occurred
     */
   public void removeID_Rechtegruppe(String id_Rechtegruppe) throws EJBException {
      try {
         Rechtegruppe_AnwenderDBA.deleteByKey(id_Rechtegruppe, _details.getID_Anwender());
      } catch (Exception e) {
         throw new EJBException("Unable to remove the Rechtegruppe Entry from the table Rechtegruppe_Anwender Exception: " + //$NON-NLS-1$
            e.getMessage(), e);
      }
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
         if (_details.getID_Anwender() != null) {
            string += "id_Anwender = " + _details.getID_Anwender(); //$NON-NLS-1$
         }
         if (_details.getID_Gebiet() != null) {
            string +=  ", id_Gebiet = " + _details.getID_Gebiet(); //$NON-NLS-1$
         }
         if (_details.getName() != null) {
            string +=  ", name = " + _details.getName(); //$NON-NLS-1$
         }
         if (_details.getAnwendername() != null) {
            string +=  ", anwendername = " + _details.getAnwendername(); //$NON-NLS-1$
         }
         if (_details.getPasswordHash() != null) {
            string +=  ", passwordHash = " + _details.getPasswordHash(); //$NON-NLS-1$
         }
         if (_details.getSalt() != null) {
            string +=  ", salt = " + _details.getSalt(); //$NON-NLS-1$
         }
         string +=  ", fehlversucheAnmeldung = " + _details.getFehlversucheAnmeldung(); //$NON-NLS-1$
         if (_details.getLetzterZugriff() != null) {
            string +=  ", letzterZugriff = " + _details.getLetzterZugriff(); //$NON-NLS-1$
         }
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
