/*
 * BasicStimmergebnisBean
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
import de.ivu.wahl.modell.StimmergebnisModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Stimmergebnis as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicStimmergebnisBean extends BMPBeanBase implements EntityBean, StimmergebnisModel {
   private static final Category LOGGER = Log4J.configure(BasicStimmergebnisBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicStimmergebnisBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected transient boolean _readOnly;

   @Override
   protected void setConditionalReadOnly(final boolean readOnly) {
      _readOnly = readOnly;
   }

   protected StimmergebnisModel _details = null;

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
   public String ejbCreate(StimmergebnisModel details) throws CreateException, EJBException {
      StimmergebnisModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Stimmergebnis();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Stimmergebnis key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Stimmergebnis) throws CreateException, EJBException {
      _details = (StimmergebnisModel)createModel(id_Stimmergebnis);
      create(_details);
      return id_Stimmergebnis;
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
         return StimmergebnisDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Ergebniseingang ID of the objects to be searched
     * @return  {@link Collection} of the found Stimmergebnis-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByErgebniseingang(String id_Ergebniseingang) throws IVUFinderException {
      try {
         return StimmergebnisDBA.retrieveIDsByID_Ergebniseingang(id_Ergebniseingang);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_GruppeGebietsspezifisch ID of the objects to be searched
     * @return  {@link Collection} of the found Stimmergebnis-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByGruppeGebietsspezifisch(String id_GruppeGebietsspezifisch) throws IVUFinderException {
      try {
         return StimmergebnisDBA.retrieveIDsByID_GruppeGebietsspezifisch(id_GruppeGebietsspezifisch);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gebiet ID of the objects to be searched
     * @return  {@link Collection} of the found Stimmergebnis-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByGebiet(String id_Gebiet) throws IVUFinderException {
      try {
         return StimmergebnisDBA.retrieveIDsByID_Gebiet(id_Gebiet);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Listenkandidatur ID of the objects to be searched
     * @return  {@link Collection} of the found Stimmergebnis-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByListenkandidatur(String id_Listenkandidatur) throws IVUFinderException {
      try {
         return StimmergebnisDBA.retrieveIDsByID_Listenkandidatur(id_Listenkandidatur);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Stimmergebnis}, filtered by wahlergebnisart.
     *
     * @param wahlergebnisart searching condition
     * @return  {@link Collection} of primary keys of the entities Stimmergebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByWahlergebnisart(int wahlergebnisart) throws IVUFinderException {
      try {
         return StimmergebnisDBA.retrieveIDsByWahlergebnisart(wahlergebnisart);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Stimmergebnis}, filtered by stimmen.
     *
     * @param stimmen searching condition
     * @return  {@link Collection} of primary keys of the entities Stimmergebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByStimmen(int stimmen) throws IVUFinderException {
      try {
         return StimmergebnisDBA.retrieveIDsByStimmen(stimmen);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Stimmergebnis}, filtered by stimmart.
     *
     * @param stimmart searching condition
     * @return  {@link Collection} of primary keys of the entities Stimmergebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByStimmart(int stimmart) throws IVUFinderException {
      try {
         return StimmergebnisDBA.retrieveIDsByStimmart(stimmart);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Stimmergebnis key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Stimmergebnis) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(StimmergebnisModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public StimmergebnisModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(StimmergebnisModel details) {
      if (_readOnly) { throw new EJBException("StimmergebnisBean is in read-only mode");} //$NON-NLS-1$
      if (details instanceof StimmergebnisModelImpl && _details instanceof StimmergebnisModelImpl) {
         ((StimmergebnisModelImpl)_details).copyFrom((StimmergebnisModelImpl)details);
         checkRelations();
      } else {
         setID_Ergebniseingang(details.getID_Ergebniseingang());
         setID_Gebiet(details.getID_Gebiet());
         setID_GruppeGebietsspezifisch(details.getID_GruppeGebietsspezifisch());
         setID_Listenkandidatur(details.getID_Listenkandidatur());
         setWahlergebnisart(details.getWahlergebnisart());
         setStimmen(details.getStimmen());
         setStimmart(details.getStimmart());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (StimmergebnisModel)details;
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
      if (null == _details.getID_GruppeGebietsspezifisch()) {
         _gruppeGebietsspezifisch = null;
         _relchk_GruppeGebietsspezifisch = true;
      } else {
         _relchk_GruppeGebietsspezifisch = false;
      }
      if (null == _details.getID_Gebiet()) {
         _gebiet = null;
         _relchk_Gebiet = true;
      } else {
         _relchk_Gebiet = false;
      }
      if (null == _details.getID_Listenkandidatur()) {
         _listenkandidatur = null;
         _relchk_Listenkandidatur = true;
      } else {
         _relchk_Listenkandidatur = false;
      }
   }

   @Override
   protected void resetRelations() {
      _ergebniseingang = null;
      _gruppeGebietsspezifisch = null;
      _gebiet = null;
      _listenkandidatur = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public StimmergebnisModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Stimmergebnis in the entity Stimmergebnis
     *
     * @return value of the attribute id_Stimmergebnis
     */
   public String getID_Stimmergebnis() {
      return _details.getID_Stimmergebnis();
   }

   /**
     * Sets the value of id_Ergebniseingang in the entity Stimmergebnis
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   public void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (_readOnly) { throw new EJBException("StimmergebnisBean is in read-only mode");} //$NON-NLS-1$
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
     * Gets the value of id_Ergebniseingang in the entity Stimmergebnis
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _details.getID_Ergebniseingang();
   }

   /**
     * Sets the value of id_Gebiet in the entity Stimmergebnis
     *
     * @param id_Gebiet new value of the attribute id_Gebiet
     */
   public void setID_Gebiet(String id_Gebiet) {
      if (_readOnly) { throw new EJBException("StimmergebnisBean is in read-only mode");} //$NON-NLS-1$
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
     * Gets the value of id_Gebiet in the entity Stimmergebnis
     *
     * @return value of the attribute id_Gebiet
     */
   public String getID_Gebiet() {
      return _details.getID_Gebiet();
   }

   /**
     * Sets the value of id_GruppeGebietsspezifisch in the entity Stimmergebnis
     *
     * @param id_GruppeGebietsspezifisch new value of the attribute id_GruppeGebietsspezifisch
     */
   public void setID_GruppeGebietsspezifisch(String id_GruppeGebietsspezifisch) {
      if (_readOnly) { throw new EJBException("StimmergebnisBean is in read-only mode");} //$NON-NLS-1$
      if (null == id_GruppeGebietsspezifisch) {
         _gruppeGebietsspezifisch = null;
         _relchk_GruppeGebietsspezifisch = true;
      } else {
         String old = _details.getID_GruppeGebietsspezifisch();
         if (old == null || !old.equals(id_GruppeGebietsspezifisch)) {
            _relchk_GruppeGebietsspezifisch = false;
         }
      }
      _details.setID_GruppeGebietsspezifisch(id_GruppeGebietsspezifisch);
   }

   /**
     * Gets the value of id_GruppeGebietsspezifisch in the entity Stimmergebnis
     *
     * @return value of the attribute id_GruppeGebietsspezifisch
     */
   public String getID_GruppeGebietsspezifisch() {
      return _details.getID_GruppeGebietsspezifisch();
   }

   /**
     * Sets the value of id_Listenkandidatur in the entity Stimmergebnis
     *
     * @param id_Listenkandidatur new value of the attribute id_Listenkandidatur
     */
   public void setID_Listenkandidatur(String id_Listenkandidatur) {
      if (_readOnly) { throw new EJBException("StimmergebnisBean is in read-only mode");} //$NON-NLS-1$
      if (null == id_Listenkandidatur) {
         _listenkandidatur = null;
         _relchk_Listenkandidatur = true;
      } else {
         String old = _details.getID_Listenkandidatur();
         if (old == null || !old.equals(id_Listenkandidatur)) {
            _relchk_Listenkandidatur = false;
         }
      }
      _details.setID_Listenkandidatur(id_Listenkandidatur);
   }

   /**
     * Gets the value of id_Listenkandidatur in the entity Stimmergebnis
     *
     * @return value of the attribute id_Listenkandidatur
     */
   public String getID_Listenkandidatur() {
      return _details.getID_Listenkandidatur();
   }

   /**
     * Sets the value of wahlergebnisart in the entity Stimmergebnis
     *
     * @param wahlergebnisart new value of the attribute wahlergebnisart
     */
   public void setWahlergebnisart(int wahlergebnisart) {
      if (_readOnly) { throw new EJBException("StimmergebnisBean is in read-only mode");} //$NON-NLS-1$
      _details.setWahlergebnisart(wahlergebnisart);
   }

   /**
     * Gets the value of wahlergebnisart in the entity Stimmergebnis
     *
     * @return value of the attribute wahlergebnisart
     */
   public int getWahlergebnisart() {
      return _details.getWahlergebnisart();
   }

   /**
     * Sets the value of stimmen in the entity Stimmergebnis
     *
     * @param stimmen new value of the attribute stimmen
     */
   public void setStimmen(int stimmen) {
      if (_readOnly) { throw new EJBException("StimmergebnisBean is in read-only mode");} //$NON-NLS-1$
      _details.setStimmen(stimmen);
   }

   /**
     * Gets the value of stimmen in the entity Stimmergebnis
     *
     * @return value of the attribute stimmen
     */
   public int getStimmen() {
      return _details.getStimmen();
   }

   /**
     * Sets the value of stimmart in the entity Stimmergebnis
     *
     * @param stimmart new value of the attribute stimmart
     */
   public void setStimmart(int stimmart) {
      if (_readOnly) { throw new EJBException("StimmergebnisBean is in read-only mode");} //$NON-NLS-1$
      _details.setStimmart(stimmart);
   }

   /**
     * Gets the value of stimmart in the entity Stimmergebnis
     *
     * @return value of the attribute stimmart
     */
   public int getStimmart() {
      return _details.getStimmart();
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
      if (_readOnly) { throw new EJBException("StimmergebnisBean is in read-only mode");} //$NON-NLS-1$
      _ergebniseingang = ergebniseingang;
      _details.setID_Ergebniseingang(ergebniseingang == null ? null : (String)ergebniseingang.getPrimaryKey());
   }

   /**
     * Relation zu GruppeGebietsspezifisch
     */
   protected GruppeGebietsspezifisch _gruppeGebietsspezifisch;

   /**
     * Flag for the validity of the relation GruppeGebietsspezifisch
     */
   protected boolean _relchk_GruppeGebietsspezifisch = false;

   /**
     * Navigation to the associated entity of the type {@link GruppeGebietsspezifisch}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public GruppeGebietsspezifisch getGruppeGebietsspezifisch() throws EJBException {
      if (!_relchk_GruppeGebietsspezifisch) {
         if (null == _details.getID_GruppeGebietsspezifisch()) {
            _gruppeGebietsspezifisch = null;
         } else if (null == _gruppeGebietsspezifisch || !_gruppeGebietsspezifisch.getPrimaryKey().equals(_details.getID_GruppeGebietsspezifisch())) {
            try {
               GruppeGebietsspezifischHome home = GruppeGebietsspezifischHome.HomeFinder.findHome(this);
               _gruppeGebietsspezifisch = home.findByPrimaryKey(_details.getID_GruppeGebietsspezifisch());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find GruppeGebietsspezifisch", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table GruppeGebietsspezifisch", fe); //$NON-NLS-1$
            }
         }
         _relchk_GruppeGebietsspezifisch = true;
      }
      return _gruppeGebietsspezifisch;
   }

   /**
     * Setting of the associated entity of the type {@link GruppeGebietsspezifisch}
     *
     * @param gruppeGebietsspezifisch the corresponding EJBObject
     */
   public void setGruppeGebietsspezifisch(GruppeGebietsspezifisch gruppeGebietsspezifisch) {
      if (_readOnly) { throw new EJBException("StimmergebnisBean is in read-only mode");} //$NON-NLS-1$
      _gruppeGebietsspezifisch = gruppeGebietsspezifisch;
      _details.setID_GruppeGebietsspezifisch(gruppeGebietsspezifisch == null ? null : (String)gruppeGebietsspezifisch.getPrimaryKey());
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
      if (_readOnly) { throw new EJBException("StimmergebnisBean is in read-only mode");} //$NON-NLS-1$
      _gebiet = gebiet;
      _details.setID_Gebiet(gebiet == null ? null : (String)gebiet.getPrimaryKey());
   }

   /**
     * Relation zu Listenkandidatur
     */
   protected Listenkandidatur _listenkandidatur;

   /**
     * Flag for the validity of the relation Listenkandidatur
     */
   protected boolean _relchk_Listenkandidatur = false;

   /**
     * Navigation to the associated entity of the type {@link Listenkandidatur}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Listenkandidatur getListenkandidatur() throws EJBException {
      if (!_relchk_Listenkandidatur) {
         if (null == _details.getID_Listenkandidatur()) {
            _listenkandidatur = null;
         } else if (null == _listenkandidatur || !_listenkandidatur.getPrimaryKey().equals(_details.getID_Listenkandidatur())) {
            try {
               ListenkandidaturHome home = ListenkandidaturHome.HomeFinder.findHome(this);
               _listenkandidatur = home.findByPrimaryKey(_details.getID_Listenkandidatur());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find Listenkandidatur", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Listenkandidatur", fe); //$NON-NLS-1$
            }
         }
         _relchk_Listenkandidatur = true;
      }
      return _listenkandidatur;
   }

   /**
     * Setting of the associated entity of the type {@link Listenkandidatur}
     *
     * @param listenkandidatur the corresponding EJBObject
     */
   public void setListenkandidatur(Listenkandidatur listenkandidatur) {
      if (_readOnly) { throw new EJBException("StimmergebnisBean is in read-only mode");} //$NON-NLS-1$
      _listenkandidatur = listenkandidatur;
      _details.setID_Listenkandidatur(listenkandidatur == null ? null : (String)listenkandidatur.getPrimaryKey());
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
         if (_details.getID_Stimmergebnis() != null) {
            string += "id_Stimmergebnis = " + _details.getID_Stimmergebnis(); //$NON-NLS-1$
         }
         if (_details.getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + _details.getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (_details.getID_Gebiet() != null) {
            string +=  ", id_Gebiet = " + _details.getID_Gebiet(); //$NON-NLS-1$
         }
         if (_details.getID_GruppeGebietsspezifisch() != null) {
            string +=  ", id_GruppeGebietsspezifisch = " + _details.getID_GruppeGebietsspezifisch(); //$NON-NLS-1$
         }
         if (_details.getID_Listenkandidatur() != null) {
            string +=  ", id_Listenkandidatur = " + _details.getID_Listenkandidatur(); //$NON-NLS-1$
         }
         string +=  ", wahlergebnisart = " + _details.getWahlergebnisart(); //$NON-NLS-1$
         string +=  ", stimmen = " + _details.getStimmen(); //$NON-NLS-1$
         string +=  ", stimmart = " + _details.getStimmart(); //$NON-NLS-1$
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
