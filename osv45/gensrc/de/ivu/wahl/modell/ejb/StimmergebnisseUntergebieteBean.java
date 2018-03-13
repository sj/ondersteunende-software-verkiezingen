/*
 * StimmergebnisseUntergebieteBean
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
import de.ivu.wahl.modell.StimmergebnisseUntergebieteModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity StimmergebnisseUntergebiete as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class StimmergebnisseUntergebieteBean extends BMPBeanBase implements EntityBean, StimmergebnisseUntergebieteModel {
   private static final Category LOGGER = Log4J.configure(StimmergebnisseUntergebieteBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(StimmergebnisseUntergebieteBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected StimmergebnisseUntergebieteModel _details = null;

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
   public String ejbCreate(StimmergebnisseUntergebieteModel details) throws CreateException, EJBException {
      StimmergebnisseUntergebieteModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_StimmergebnisseUntergebiete();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_StimmergebnisseUntergebiete key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_StimmergebnisseUntergebiete) throws CreateException, EJBException {
      _details = (StimmergebnisseUntergebieteModel)createModel(id_StimmergebnisseUntergebiete);
      _details.setErgebnisseXML(""); //$NON-NLS-1$
      create(_details);
      return id_StimmergebnisseUntergebiete;
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
         return StimmergebnisseUntergebieteDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Ergebniseingang ID of the objects to be searched
     * @return  {@link Collection} of the found StimmergebnisseUntergebiete-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByErgebniseingang(String id_Ergebniseingang) throws IVUFinderException {
      try {
         return StimmergebnisseUntergebieteDBA.retrieveIDsByID_Ergebniseingang(id_Ergebniseingang);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gebiet ID of the objects to be searched
     * @return  {@link Collection} of the found StimmergebnisseUntergebiete-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByGebiet(String id_Gebiet) throws IVUFinderException {
      try {
         return StimmergebnisseUntergebieteDBA.retrieveIDsByID_Gebiet(id_Gebiet);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link StimmergebnisseUntergebiete}, filtered by ergebnisseXML.
     *
     * @param ergebnisseXML searching condition
     * @return  {@link Collection} of primary keys of the entities StimmergebnisseUntergebiete
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByErgebnisseXML(String ergebnisseXML) throws IVUFinderException {
      try {
         return StimmergebnisseUntergebieteDBA.retrieveIDsByErgebnisseXML(ergebnisseXML);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link StimmergebnisseUntergebiete}, filtered by ergebnisseXML.
     *
     * @param ergebnisseXML searching condition
     * @return  {@link Collection} of primary keys of the entities StimmergebnisseUntergebiete
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeErgebnisseXML(String ergebnisseXML) throws IVUFinderException {
      try {
         return StimmergebnisseUntergebieteDBA.retrieveIDsLikeErgebnisseXML(ergebnisseXML);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_StimmergebnisseUntergebiete key element of the type {@link String}
     */
   public void ejbPostCreate(String id_StimmergebnisseUntergebiete) {
      // No functionality required
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(StimmergebnisseUntergebieteModel details) {
      // No functionality required
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public StimmergebnisseUntergebieteModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(StimmergebnisseUntergebieteModel details) {
      if (details instanceof StimmergebnisseUntergebieteModelImpl && _details instanceof StimmergebnisseUntergebieteModelImpl) {
         ((StimmergebnisseUntergebieteModelImpl)_details).copyFrom((StimmergebnisseUntergebieteModelImpl)details);
         checkRelations();
      } else {
         setID_Ergebniseingang(details.getID_Ergebniseingang());
         setID_Gebiet(details.getID_Gebiet());
         setErgebnisseXML(details.getErgebnisseXML());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (StimmergebnisseUntergebieteModel)details;
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
      if (null == _details.getID_Ergebniseingang()) {
         _ergebniseingang = null;
         _relchk_Ergebniseingang = true;
      } else {
         _relchk_Ergebniseingang = false;
      }
      if (null == _details.getID_Gebiet()) {
         _gebiet = null;
         _relchk_Gebiet = true;
      } else {
         _relchk_Gebiet = false;
      }
   }

   @Override
   protected void resetRelations() {
      _ergebniseingang = null;
      _gebiet = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public StimmergebnisseUntergebieteModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_StimmergebnisseUntergebiete in the entity StimmergebnisseUntergebiete
     *
     * @return value of the attribute id_StimmergebnisseUntergebiete
     */
   public String getID_StimmergebnisseUntergebiete() {
      return _details.getID_StimmergebnisseUntergebiete();
   }

   /**
     * Sets the value of id_Ergebniseingang in the entity StimmergebnisseUntergebiete
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
     * Gets the value of id_Ergebniseingang in the entity StimmergebnisseUntergebiete
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _details.getID_Ergebniseingang();
   }

   /**
     * Sets the value of id_Gebiet in the entity StimmergebnisseUntergebiete
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
     * Gets the value of id_Gebiet in the entity StimmergebnisseUntergebiete
     *
     * @return value of the attribute id_Gebiet
     */
   public String getID_Gebiet() {
      return _details.getID_Gebiet();
   }

   /**
     * Sets the value of ergebnisseXML in the entity StimmergebnisseUntergebiete
     *
     * @param ergebnisseXML new value of the attribute ergebnisseXML
     */
   public void setErgebnisseXML(String ergebnisseXML) {
      _details.setErgebnisseXML(ergebnisseXML);
   }

   /**
     * Gets the value of ergebnisseXML in the entity StimmergebnisseUntergebiete
     *
     * @return value of the attribute ergebnisseXML
     */
   public String getErgebnisseXML() {
      return _details.getErgebnisseXML();
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
         if (_details.getID_StimmergebnisseUntergebiete() != null) {
            string += "id_StimmergebnisseUntergebiete = " + _details.getID_StimmergebnisseUntergebiete(); //$NON-NLS-1$
         }
         if (_details.getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + _details.getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (_details.getID_Gebiet() != null) {
            string +=  ", id_Gebiet = " + _details.getID_Gebiet(); //$NON-NLS-1$
         }
         if (_details.getErgebnisseXML() != null) {
            string +=  ", ergebnisseXML = " + _details.getErgebnisseXML(); //$NON-NLS-1$
         }
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
