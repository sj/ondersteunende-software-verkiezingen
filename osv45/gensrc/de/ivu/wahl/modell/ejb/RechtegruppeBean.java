/*
 * RechtegruppeBean
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
import de.ivu.wahl.modell.RechtegruppeModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Rechtegruppe as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class RechtegruppeBean extends BMPBeanBase implements EntityBean, RechtegruppeModel {
   private static final Category LOGGER = Log4J.configure(RechtegruppeBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(RechtegruppeBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected RechtegruppeModel _details = null;

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
   public String ejbCreate(RechtegruppeModel details) throws CreateException, EJBException {
      RechtegruppeModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Rechtegruppe();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Rechtegruppe key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Rechtegruppe) throws CreateException, EJBException {
      _details = (RechtegruppeModel)createModel(id_Rechtegruppe);
      _details.setName(""); //$NON-NLS-1$
      _details.setBeschreibung(""); //$NON-NLS-1$
      create(_details);
      return id_Rechtegruppe;
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
         return RechtegruppeDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Anwender ID of the objects to be searched
     * @return  {@link Collection} of the found Rechtegruppe-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByAnwender(String id_Anwender) throws IVUFinderException {
      try {
         return Rechtegruppe_AnwenderDBA.retrieveIDsByID_Anwender(id_Anwender);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Recht ID of the objects to be searched
     * @return  {@link Collection} of the found Rechtegruppe-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByRecht(String id_Recht) throws IVUFinderException {
      try {
         return Rechtegruppe_RechtDBA.retrieveIDsByID_Recht(id_Recht);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Rechtegruppe}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of primary keys of the entities Rechtegruppe
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByName(String name) throws IVUFinderException {
      try {
         return RechtegruppeDBA.retrieveIDsByName(name);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Rechtegruppe}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of primary keys of the entities Rechtegruppe
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeName(String name) throws IVUFinderException {
      try {
         return RechtegruppeDBA.retrieveIDsLikeName(name);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Rechtegruppe}, filtered by beschreibung.
     *
     * @param beschreibung searching condition
     * @return  {@link Collection} of primary keys of the entities Rechtegruppe
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByBeschreibung(String beschreibung) throws IVUFinderException {
      try {
         return RechtegruppeDBA.retrieveIDsByBeschreibung(beschreibung);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Rechtegruppe}, filtered by beschreibung.
     *
     * @param beschreibung searching condition
     * @return  {@link Collection} of primary keys of the entities Rechtegruppe
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeBeschreibung(String beschreibung) throws IVUFinderException {
      try {
         return RechtegruppeDBA.retrieveIDsLikeBeschreibung(beschreibung);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Rechtegruppe key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Rechtegruppe) {
      // No functionality required
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(RechtegruppeModel details) {
      // No functionality required
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public RechtegruppeModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(RechtegruppeModel details) {
      if (details instanceof RechtegruppeModelImpl && _details instanceof RechtegruppeModelImpl) {
         ((RechtegruppeModelImpl)_details).copyFrom((RechtegruppeModelImpl)details);
      } else {
         setName(details.getName());
         setBeschreibung(details.getBeschreibung());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (RechtegruppeModel)details;
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
   public RechtegruppeModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Rechtegruppe in the entity Rechtegruppe
     *
     * @return value of the attribute id_Rechtegruppe
     */
   public String getID_Rechtegruppe() {
      return _details.getID_Rechtegruppe();
   }

   /**
     * Sets the value of name in the entity Rechtegruppe
     *
     * @param name new value of the attribute name
     */
   public void setName(String name) {
      _details.setName(name);
   }

   /**
     * Gets the value of name in the entity Rechtegruppe
     *
     * @return value of the attribute name
     */
   public String getName() {
      return _details.getName();
   }

   /**
     * Sets the value of beschreibung in the entity Rechtegruppe
     *
     * @param beschreibung new value of the attribute beschreibung
     */
   public void setBeschreibung(String beschreibung) {
      _details.setBeschreibung(beschreibung);
   }

   /**
     * Gets the value of beschreibung in the entity Rechtegruppe
     *
     * @return value of the attribute beschreibung
     */
   public String getBeschreibung() {
      return _details.getBeschreibung();
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
         return anwenderHome.findAllByRechtegruppe(_details.getID_Rechtegruppe());
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
      addID_Anwender((String)anwender.getPrimaryKey());
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
     * Adds the object, which is marked by the ID, to the set of entities of the type {@link Anwender}.
     *
     * @param id_Anwender ID of Anwender entity
     * @throws EJBException: an error occurred
     */
   public void addID_Anwender(String id_Anwender) throws EJBException {
      try {
         Rechtegruppe_AnwenderModelImpl model =
            new Rechtegruppe_AnwenderModelImpl (_details.getID_Rechtegruppe(), id_Anwender);
         Rechtegruppe_AnwenderDBA.insert (model);
      } catch (Exception e) {
         throw new EJBException("Unable to change Table Rechtegruppe_Anwender Exception: " + //$NON-NLS-1$
            e.getMessage(), e);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Anwender}.
     *
     * @param anwender Anwender-EJBObject, which is removed from the set.
     */
   public void removeAnwender(Anwender anwender) {
      removeID_Anwender((String)anwender.getPrimaryKey());
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Anwender}.
     *
     * @param id_Anwender ID of the Anwender entity to be deleted
     * @throws EJBException: an error occurred
     */
   public void removeID_Anwender(String id_Anwender) throws EJBException {
      try {
         Rechtegruppe_AnwenderDBA.deleteByKey(_details.getID_Rechtegruppe(), id_Anwender);
      } catch (Exception e) {
         throw new EJBException("Unable to remove the Anwender Entry from the table Rechtegruppe_Anwender Exception: " + //$NON-NLS-1$
            e.getMessage(), e);
      }
   }

   /**
     * Returns the set of entities of the type {@link Recht}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Recht}
     * @throws EJBException: an error occurred
     */
   public Collection<Recht> getRechtCol() throws EJBException {
      RechtHome rechtHome = RechtHome.HomeFinder.findHome(this);
      try {
         return rechtHome.findAllByRechtegruppe(_details.getID_Rechtegruppe());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Recht}.
     *
     * @param recht Recht-object
     */
   public void addRecht(Recht recht) {
      addID_Recht((String)recht.getPrimaryKey());
   }

   /**
     * Adds the object to the set of entities of the type {@link Recht}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllRechtCol(Collection<Recht> col) {
      for (Recht recht : col) {
         addRecht(recht);
      }
   }

   /**
     * Adds the object, which is marked by the ID, to the set of entities of the type {@link Recht}.
     *
     * @param id_Recht ID of Recht entity
     * @throws EJBException: an error occurred
     */
   public void addID_Recht(String id_Recht) throws EJBException {
      try {
         Rechtegruppe_RechtModelImpl model =
            new Rechtegruppe_RechtModelImpl (_details.getID_Rechtegruppe(), id_Recht);
         Rechtegruppe_RechtDBA.insert (model);
      } catch (Exception e) {
         throw new EJBException("Unable to change Table Rechtegruppe_Recht Exception: " + //$NON-NLS-1$
            e.getMessage(), e);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Recht}.
     *
     * @param recht Recht-EJBObject, which is removed from the set.
     */
   public void removeRecht(Recht recht) {
      removeID_Recht((String)recht.getPrimaryKey());
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Recht}.
     *
     * @param id_Recht ID of the Recht entity to be deleted
     * @throws EJBException: an error occurred
     */
   public void removeID_Recht(String id_Recht) throws EJBException {
      try {
         Rechtegruppe_RechtDBA.deleteByKey(_details.getID_Rechtegruppe(), id_Recht);
      } catch (Exception e) {
         throw new EJBException("Unable to remove the Recht Entry from the table Rechtegruppe_Recht Exception: " + //$NON-NLS-1$
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
         if (_details.getID_Rechtegruppe() != null) {
            string += "id_Rechtegruppe = " + _details.getID_Rechtegruppe(); //$NON-NLS-1$
         }
         if (_details.getName() != null) {
            string +=  ", name = " + _details.getName(); //$NON-NLS-1$
         }
         if (_details.getBeschreibung() != null) {
            string +=  ", beschreibung = " + _details.getBeschreibung(); //$NON-NLS-1$
         }
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
