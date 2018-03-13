/*
 * BasicRepositoryBean
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;

import java.util.Collection;
import java.sql.SQLException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.ejb.bmp.BMPBeanBase;
import de.ivu.ejb.bmp.Model;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.modell.RepositoryModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Repository as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicRepositoryBean extends BMPBeanBase implements EntityBean, RepositoryModel {
   private static final Category LOGGER = Log4J.configure(BasicRepositoryBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicRepositoryBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected RepositoryModel _details = null;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(RepositoryModel details) throws CreateException, EJBException {
      RepositoryModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Repository();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Repository key element of the type {@link String}
     * @param name key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Repository, String name) throws CreateException, EJBException {
      _details = (RepositoryModel)createModel(id_Repository);
      _details.setName(""); //$NON-NLS-1$
      _details.setName(name);
      _details.setWert(""); //$NON-NLS-1$
      create(_details);
      return id_Repository;
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
         return RepositoryDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Repository}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of primary keys of the entities Repository
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByName(String name) throws IVUFinderException {
      try {
         return RepositoryDBA.retrieveIDsByName(name);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Repository}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of primary keys of the entities Repository
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeName(String name) throws IVUFinderException {
      try {
         return RepositoryDBA.retrieveIDsLikeName(name);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Repository}, filtered by wert.
     *
     * @param wert searching condition
     * @return  {@link Collection} of primary keys of the entities Repository
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByWert(String wert) throws IVUFinderException {
      try {
         return RepositoryDBA.retrieveIDsByWert(wert);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Repository}, filtered by wert.
     *
     * @param wert searching condition
     * @return  {@link Collection} of primary keys of the entities Repository
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeWert(String wert) throws IVUFinderException {
      try {
         return RepositoryDBA.retrieveIDsLikeWert(wert);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Repository key element of the type {@link String}
     * @param name key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Repository, String name) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(RepositoryModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public RepositoryModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(RepositoryModel details) {
      if (details instanceof RepositoryModelImpl && _details instanceof RepositoryModelImpl) {
         ((RepositoryModelImpl)_details).copyFrom((RepositoryModelImpl)details);
      } else {
         setName(details.getName());
         setWert(details.getWert());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (RepositoryModel)details;
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
   public RepositoryModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Repository in the entity Repository
     *
     * @return value of the attribute id_Repository
     */
   public String getID_Repository() {
      return _details.getID_Repository();
   }

   /**
     * Sets the value of name in the entity Repository
     *
     * @param name new value of the attribute name
     */
   public void setName(String name) {
      _details.setName(name);
   }

   /**
     * Gets the value of name in the entity Repository
     *
     * @return value of the attribute name
     */
   public String getName() {
      return _details.getName();
   }

   /**
     * Sets the value of wert in the entity Repository
     *
     * @param wert new value of the attribute wert
     */
   public void setWert(String wert) {
      _details.setWert(wert);
   }

   /**
     * Gets the value of wert in the entity Repository
     *
     * @return value of the attribute wert
     */
   public String getWert() {
      return _details.getWert();
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
         if (_details.getID_Repository() != null) {
            string += "id_Repository = " + _details.getID_Repository(); //$NON-NLS-1$
         }
         if (_details.getName() != null) {
            string +=  ", name = " + _details.getName(); //$NON-NLS-1$
         }
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
