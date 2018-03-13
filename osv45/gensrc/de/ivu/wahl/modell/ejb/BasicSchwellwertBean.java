/*
 * BasicSchwellwertBean
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;
import java.sql.SQLException;
import java.math.BigDecimal;
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
import de.ivu.wahl.modell.SchwellwertModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Schwellwert as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicSchwellwertBean extends BMPBeanBase implements EntityBean, SchwellwertModel {
   private static final Category LOGGER = Log4J.configure(BasicSchwellwertBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicSchwellwertBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected SchwellwertModel _details = null;

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
   public String ejbCreate(SchwellwertModel details) throws CreateException, EJBException {
      SchwellwertModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Schwellwert();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Schwellwert key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Schwellwert) throws CreateException, EJBException {
      _details = (SchwellwertModel)createModel(id_Schwellwert);
      _details.setName(""); //$NON-NLS-1$
      create(_details);
      return id_Schwellwert;
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
         return SchwellwertDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Wahl ID of the objects to be searched
     * @return  {@link Collection} of the found Schwellwert-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByWahl(String id_Wahl) throws IVUFinderException {
      try {
         return SchwellwertDBA.retrieveIDsByID_Wahl(id_Wahl);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Schwellwert}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of primary keys of the entities Schwellwert
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByName(String name) throws IVUFinderException {
      try {
         return SchwellwertDBA.retrieveIDsByName(name);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Schwellwert}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of primary keys of the entities Schwellwert
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeName(String name) throws IVUFinderException {
      try {
         return SchwellwertDBA.retrieveIDsLikeName(name);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Schwellwert}, filtered by schwellwertart.
     *
     * @param schwellwertart searching condition
     * @return  {@link Collection} of primary keys of the entities Schwellwert
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllBySchwellwertart(int schwellwertart) throws IVUFinderException {
      try {
         return SchwellwertDBA.retrieveIDsBySchwellwertart(schwellwertart);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Schwellwert}, filtered by wert.
     *
     * @param wert searching condition
     * @return  {@link Collection} of primary keys of the entities Schwellwert
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByWert(BigDecimal wert) throws IVUFinderException {
      try {
         return SchwellwertDBA.retrieveIDsByWert(wert);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Schwellwert key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Schwellwert) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(SchwellwertModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public SchwellwertModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(SchwellwertModel details) {
      if (details instanceof SchwellwertModelImpl && _details instanceof SchwellwertModelImpl) {
         ((SchwellwertModelImpl)_details).copyFrom((SchwellwertModelImpl)details);
         checkRelations();
      } else {
         setID_Wahl(details.getID_Wahl());
         setName(details.getName());
         setSchwellwertart(details.getSchwellwertart());
         setWert(details.getWert());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (SchwellwertModel)details;
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
   }

   @Override
   protected void resetRelations() {
      _wahl = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public SchwellwertModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Schwellwert in the entity Schwellwert
     *
     * @return value of the attribute id_Schwellwert
     */
   public String getID_Schwellwert() {
      return _details.getID_Schwellwert();
   }

   /**
     * Sets the value of id_Wahl in the entity Schwellwert
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
     * Gets the value of id_Wahl in the entity Schwellwert
     *
     * @return value of the attribute id_Wahl
     */
   public String getID_Wahl() {
      return _details.getID_Wahl();
   }

   /**
     * Sets the value of name in the entity Schwellwert
     *
     * @param name new value of the attribute name
     */
   public void setName(String name) {
      _details.setName(name);
   }

   /**
     * Gets the value of name in the entity Schwellwert
     *
     * @return value of the attribute name
     */
   public String getName() {
      return _details.getName();
   }

   /**
     * Sets the value of schwellwertart in the entity Schwellwert
     *
     * @param schwellwertart new value of the attribute schwellwertart
     */
   public void setSchwellwertart(int schwellwertart) {
      _details.setSchwellwertart(schwellwertart);
   }

   /**
     * Gets the value of schwellwertart in the entity Schwellwert
     *
     * @return value of the attribute schwellwertart
     */
   public int getSchwellwertart() {
      return _details.getSchwellwertart();
   }

   /**
     * Sets the value of wert in the entity Schwellwert
     *
     * @param wert new value of the attribute wert
     */
   public void setWert(BigDecimal wert) {
      _details.setWert(wert);
   }

   /**
     * Gets the value of wert in the entity Schwellwert
     *
     * @return value of the attribute wert
     */
   public BigDecimal getWert() {
      return _details.getWert();
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
         if (_details.getID_Schwellwert() != null) {
            string += "id_Schwellwert = " + _details.getID_Schwellwert(); //$NON-NLS-1$
         }
         if (_details.getID_Wahl() != null) {
            string +=  ", id_Wahl = " + _details.getID_Wahl(); //$NON-NLS-1$
         }
         if (_details.getName() != null) {
            string +=  ", name = " + _details.getName(); //$NON-NLS-1$
         }
         string +=  ", schwellwertart = " + _details.getSchwellwertart(); //$NON-NLS-1$
         if (_details.getWert() != null) {
            string +=  ", wert = " + _details.getWert(); //$NON-NLS-1$
         }
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
