/*
 * BasicUnterverteilungBean
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
import de.ivu.wahl.modell.UnterverteilungModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Unterverteilung as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicUnterverteilungBean extends BMPBeanBase implements EntityBean, UnterverteilungModel {
   private static final Category LOGGER = Log4J.configure(BasicUnterverteilungBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicUnterverteilungBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected UnterverteilungModel _details = null;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(UnterverteilungModel details) throws CreateException, EJBException {
      UnterverteilungModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Unterverteilung();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Unterverteilung key element of the type {@link String}
     * @param id_Ergebniseingang key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Unterverteilung, String id_Ergebniseingang) throws CreateException, EJBException {
      _details = (UnterverteilungModel)createModel(id_Unterverteilung);
      _details.setID_Ergebniseingang(id_Ergebniseingang);
      create(_details);
      return id_Unterverteilung;
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
         return UnterverteilungDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Listenkombination ID of the objects to be searched
     * @return  {@link Collection} of the found Unterverteilung-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByListenkombination(String id_Listenkombination) throws IVUFinderException {
      try {
         return UnterverteilungDBA.retrieveIDsByID_Listenkombination(id_Listenkombination);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Ergebniseingang ID of the objects to be searched
     * @return  {@link Collection} of the found Unterverteilung-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByErgebniseingang(String id_Ergebniseingang) throws IVUFinderException {
      try {
         return UnterverteilungDBA.retrieveIDsByID_Ergebniseingang(id_Ergebniseingang);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gruppe ID of the objects to be searched
     * @return  {@link Collection} of the found Unterverteilung-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByGruppe(String id_Gruppe) throws IVUFinderException {
      try {
         return UnterverteilungDBA.retrieveIDsByID_Gruppe(id_Gruppe);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Unterverteilung}, filtered by stimmen.
     *
     * @param stimmen searching condition
     * @return  {@link Collection} of primary keys of the entities Unterverteilung
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByStimmen(int stimmen) throws IVUFinderException {
      try {
         return UnterverteilungDBA.retrieveIDsByStimmen(stimmen);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Unterverteilung}, filtered by sitze.
     *
     * @param sitze searching condition
     * @return  {@link Collection} of primary keys of the entities Unterverteilung
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllBySitze(int sitze) throws IVUFinderException {
      try {
         return UnterverteilungDBA.retrieveIDsBySitze(sitze);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Unterverteilung key element of the type {@link String}
     * @param id_Ergebniseingang key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Unterverteilung, String id_Ergebniseingang) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(UnterverteilungModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public UnterverteilungModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(UnterverteilungModel details) {
      if (details instanceof UnterverteilungModelImpl && _details instanceof UnterverteilungModelImpl) {
         ((UnterverteilungModelImpl)_details).copyFrom((UnterverteilungModelImpl)details);
         checkRelations();
      } else {
         setID_Ergebniseingang(details.getID_Ergebniseingang());
         setID_Gruppe(details.getID_Gruppe());
         setID_Listenkombination(details.getID_Listenkombination());
         setStimmen(details.getStimmen());
         setSitze(details.getSitze());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (UnterverteilungModel)details;
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
      if (null == _details.getID_Listenkombination()) {
         _listenkombination = null;
         _relchk_Listenkombination = true;
      } else {
         _relchk_Listenkombination = false;
      }
      if (null == _details.getID_Ergebniseingang()) {
         _ergebniseingang = null;
         _relchk_Ergebniseingang = true;
      } else {
         _relchk_Ergebniseingang = false;
      }
      if (null == _details.getID_Gruppe()) {
         _gruppe = null;
         _relchk_Gruppe = true;
      } else {
         _relchk_Gruppe = false;
      }
   }

   @Override
   protected void resetRelations() {
      _listenkombination = null;
      _ergebniseingang = null;
      _gruppe = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public UnterverteilungModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Unterverteilung in the entity Unterverteilung
     *
     * @return value of the attribute id_Unterverteilung
     */
   public String getID_Unterverteilung() {
      return _details.getID_Unterverteilung();
   }

   /**
     * Sets the value of id_Ergebniseingang in the entity Unterverteilung
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
     * Gets the value of id_Ergebniseingang in the entity Unterverteilung
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _details.getID_Ergebniseingang();
   }

   /**
     * Sets the value of id_Gruppe in the entity Unterverteilung
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
     * Gets the value of id_Gruppe in the entity Unterverteilung
     *
     * @return value of the attribute id_Gruppe
     */
   public String getID_Gruppe() {
      return _details.getID_Gruppe();
   }

   /**
     * Sets the value of id_Listenkombination in the entity Unterverteilung
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
     * Gets the value of id_Listenkombination in the entity Unterverteilung
     *
     * @return value of the attribute id_Listenkombination
     */
   public String getID_Listenkombination() {
      return _details.getID_Listenkombination();
   }

   /**
     * Sets the value of stimmen in the entity Unterverteilung
     *
     * @param stimmen new value of the attribute stimmen
     */
   public void setStimmen(int stimmen) {
      _details.setStimmen(stimmen);
   }

   /**
     * Gets the value of stimmen in the entity Unterverteilung
     *
     * @return value of the attribute stimmen
     */
   public int getStimmen() {
      return _details.getStimmen();
   }

   /**
     * Sets the value of sitze in the entity Unterverteilung
     *
     * @param sitze new value of the attribute sitze
     */
   public void setSitze(int sitze) {
      _details.setSitze(sitze);
   }

   /**
     * Gets the value of sitze in the entity Unterverteilung
     *
     * @return value of the attribute sitze
     */
   public int getSitze() {
      return _details.getSitze();
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
         if (_details.getID_Unterverteilung() != null) {
            string += "id_Unterverteilung = " + _details.getID_Unterverteilung(); //$NON-NLS-1$
         }
         if (_details.getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + _details.getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (_details.getID_Gruppe() != null) {
            string +=  ", id_Gruppe = " + _details.getID_Gruppe(); //$NON-NLS-1$
         }
         if (_details.getID_Listenkombination() != null) {
            string +=  ", id_Listenkombination = " + _details.getID_Listenkombination(); //$NON-NLS-1$
         }
         string +=  ", stimmen = " + _details.getStimmen(); //$NON-NLS-1$
         string +=  ", sitze = " + _details.getSitze(); //$NON-NLS-1$
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
