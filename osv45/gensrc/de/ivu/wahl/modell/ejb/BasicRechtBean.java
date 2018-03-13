/*
 * BasicRechtBean
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
import de.ivu.wahl.modell.RechtModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Recht as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicRechtBean extends BMPBeanBase implements EntityBean, RechtModel {
   private static final Category LOGGER = Log4J.configure(BasicRechtBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicRechtBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected RechtModel _details = null;

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
   public String ejbCreate(RechtModel details) throws CreateException, EJBException {
      RechtModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Recht();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Recht key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Recht) throws CreateException, EJBException {
      _details = (RechtModel)createModel(id_Recht);
      _details.setName(""); //$NON-NLS-1$
      create(_details);
      return id_Recht;
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
         return RechtDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Rechtegruppe ID of the objects to be searched
     * @return  {@link Collection} of the found Recht-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByRechtegruppe(String id_Rechtegruppe) throws IVUFinderException {
      try {
         return Rechtegruppe_RechtDBA.retrieveIDsByID_Rechtegruppe(id_Rechtegruppe);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Recht}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of primary keys of the entities Recht
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByName(String name) throws IVUFinderException {
      try {
         return RechtDBA.retrieveIDsByName(name);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Recht}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of primary keys of the entities Recht
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeName(String name) throws IVUFinderException {
      try {
         return RechtDBA.retrieveIDsLikeName(name);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Recht key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Recht) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(RechtModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public RechtModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(RechtModel details) {
      setName(details.getName());
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (RechtModel)details;
      return _details;
   }

   @Override
   protected Model getDetailsInternal () {
      return _details;
   }

   @Override
   protected void checkRelations() {
      // no relations to be checked in this class, hence, only fulfilment of the interface
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public RechtModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Recht in the entity Recht
     *
     * @return value of the attribute id_Recht
     */
   public String getID_Recht() {
      return _details.getID_Recht();
   }

   /**
     * Sets the value of name in the entity Recht
     *
     * @param name new value of the attribute name
     */
   public void setName(String name) {
      _details.setName(name);
   }

   /**
     * Gets the value of name in the entity Recht
     *
     * @return value of the attribute name
     */
   public String getName() {
      return _details.getName();
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
         return rechtegruppeHome.findAllByRecht(_details.getID_Recht());
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
         Rechtegruppe_RechtModelImpl model =
            new Rechtegruppe_RechtModelImpl (id_Rechtegruppe, _details.getID_Recht());
         Rechtegruppe_RechtDBA.insert (model);
      } catch (Exception e) {
         throw new EJBException("Unable to change Table Rechtegruppe_Recht Exception: " + //$NON-NLS-1$
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
         Rechtegruppe_RechtDBA.deleteByKey(id_Rechtegruppe, _details.getID_Recht());
      } catch (Exception e) {
         throw new EJBException("Unable to remove the Rechtegruppe Entry from the table Rechtegruppe_Recht Exception: " + //$NON-NLS-1$
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
         if (_details.getID_Recht() != null) {
            string += "id_Recht = " + _details.getID_Recht(); //$NON-NLS-1$
         }
         if (_details.getName() != null) {
            string +=  ", name = " + _details.getName(); //$NON-NLS-1$
         }
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
