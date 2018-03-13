/*
 * BasicGebietsstatusBean
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
import de.ivu.wahl.modell.GebietsstatusModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Gebietsstatus as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicGebietsstatusBean extends BMPBeanBase implements EntityBean, GebietsstatusModel {
   private static final Category LOGGER = Log4J.configure(BasicGebietsstatusBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicGebietsstatusBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected GebietsstatusModel _details = null;

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
   public String ejbCreate(GebietsstatusModel details) throws CreateException, EJBException {
      GebietsstatusModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Gebietsstatus();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Gebietsstatus key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Gebietsstatus) throws CreateException, EJBException {
      _details = (GebietsstatusModel)createModel(id_Gebietsstatus);
      create(_details);
      return id_Gebietsstatus;
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
         return GebietsstatusDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Ergebniseingang ID of the objects to be searched
     * @return  {@link Collection} of the found Gebietsstatus-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByErgebniseingang(String id_Ergebniseingang) throws IVUFinderException {
      try {
         return GebietsstatusDBA.retrieveIDsByID_Ergebniseingang(id_Ergebniseingang);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gebiet ID of the objects to be searched
     * @return  {@link Collection} of the found Gebietsstatus-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByGebiet(String id_Gebiet) throws IVUFinderException {
      try {
         return GebietsstatusDBA.retrieveIDsByID_Gebiet(id_Gebiet);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebietsstatus}, filtered by wahlergebnisart.
     *
     * @param wahlergebnisart searching condition
     * @return  {@link Collection} of primary keys of the entities Gebietsstatus
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByWahlergebnisart(int wahlergebnisart) throws IVUFinderException {
      try {
         return GebietsstatusDBA.retrieveIDsByWahlergebnisart(wahlergebnisart);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebietsstatus}, filtered by korrekturnummer.
     *
     * @param korrekturnummer searching condition
     * @return  {@link Collection} of primary keys of the entities Gebietsstatus
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByKorrekturnummer(int korrekturnummer) throws IVUFinderException {
      try {
         return GebietsstatusDBA.retrieveIDsByKorrekturnummer(korrekturnummer);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebietsstatus}, filtered by anzahlErgebnisseKumuliert.
     *
     * @param anzahlErgebnisseKumuliert searching condition
     * @return  {@link Collection} of primary keys of the entities Gebietsstatus
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByAnzahlErgebnisseKumuliert(int anzahlErgebnisseKumuliert) throws IVUFinderException {
      try {
         return GebietsstatusDBA.retrieveIDsByAnzahlErgebnisseKumuliert(anzahlErgebnisseKumuliert);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gebietsstatus}, filtered by vollstaendig.
     *
     * @param vollstaendig searching condition
     * @return  {@link Collection} of primary keys of the entities Gebietsstatus
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByVollstaendig(boolean vollstaendig) throws IVUFinderException {
      try {
         return GebietsstatusDBA.retrieveIDsByVollstaendig(vollstaendig);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Gebietsstatus key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Gebietsstatus) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(GebietsstatusModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public GebietsstatusModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(GebietsstatusModel details) {
      if (details instanceof GebietsstatusModelImpl && _details instanceof GebietsstatusModelImpl) {
         ((GebietsstatusModelImpl)_details).copyFrom((GebietsstatusModelImpl)details);
         checkRelations();
      } else {
         setID_Ergebniseingang(details.getID_Ergebniseingang());
         setID_Gebiet(details.getID_Gebiet());
         setWahlergebnisart(details.getWahlergebnisart());
         setKorrekturnummer(details.getKorrekturnummer());
         setAnzahlErgebnisseKumuliert(details.getAnzahlErgebnisseKumuliert());
         setVollstaendig(details.isVollstaendig());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (GebietsstatusModel)details;
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
   public GebietsstatusModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Gebietsstatus in the entity Gebietsstatus
     *
     * @return value of the attribute id_Gebietsstatus
     */
   public String getID_Gebietsstatus() {
      return _details.getID_Gebietsstatus();
   }

   /**
     * Sets the value of id_Ergebniseingang in the entity Gebietsstatus
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
     * Gets the value of id_Ergebniseingang in the entity Gebietsstatus
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _details.getID_Ergebniseingang();
   }

   /**
     * Sets the value of id_Gebiet in the entity Gebietsstatus
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
     * Gets the value of id_Gebiet in the entity Gebietsstatus
     *
     * @return value of the attribute id_Gebiet
     */
   public String getID_Gebiet() {
      return _details.getID_Gebiet();
   }

   /**
     * Sets the value of wahlergebnisart in the entity Gebietsstatus
     *
     * @param wahlergebnisart new value of the attribute wahlergebnisart
     */
   public void setWahlergebnisart(int wahlergebnisart) {
      _details.setWahlergebnisart(wahlergebnisart);
   }

   /**
     * Gets the value of wahlergebnisart in the entity Gebietsstatus
     *
     * @return value of the attribute wahlergebnisart
     */
   public int getWahlergebnisart() {
      return _details.getWahlergebnisart();
   }

   /**
     * Sets the value of korrekturnummer in the entity Gebietsstatus
     *
     * @param korrekturnummer new value of the attribute korrekturnummer
     */
   public void setKorrekturnummer(int korrekturnummer) {
      _details.setKorrekturnummer(korrekturnummer);
   }

   /**
     * Gets the value of korrekturnummer in the entity Gebietsstatus
     *
     * @return value of the attribute korrekturnummer
     */
   public int getKorrekturnummer() {
      return _details.getKorrekturnummer();
   }

   /**
     * Sets the value of anzahlErgebnisseKumuliert in the entity Gebietsstatus
     *
     * @param anzahlErgebnisseKumuliert new value of the attribute anzahlErgebnisseKumuliert
     */
   public void setAnzahlErgebnisseKumuliert(int anzahlErgebnisseKumuliert) {
      _details.setAnzahlErgebnisseKumuliert(anzahlErgebnisseKumuliert);
   }

   /**
     * Gets the value of anzahlErgebnisseKumuliert in the entity Gebietsstatus
     *
     * @return value of the attribute anzahlErgebnisseKumuliert
     */
   public int getAnzahlErgebnisseKumuliert() {
      return _details.getAnzahlErgebnisseKumuliert();
   }

   /**
     * Sets the value of vollstaendig in the entity Gebietsstatus
     *
     * @param vollstaendig new value of the attribute vollstaendig
     */
   public void setVollstaendig(boolean vollstaendig) {
      _details.setVollstaendig(vollstaendig);
   }

   /**
     * Gets the value of vollstaendig in the entity Gebietsstatus
     *
     * @return value of the attribute vollstaendig
     */
   public boolean isVollstaendig() {
      return _details.isVollstaendig();
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
         if (_details.getID_Gebietsstatus() != null) {
            string += "id_Gebietsstatus = " + _details.getID_Gebietsstatus(); //$NON-NLS-1$
         }
         if (_details.getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + _details.getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (_details.getID_Gebiet() != null) {
            string +=  ", id_Gebiet = " + _details.getID_Gebiet(); //$NON-NLS-1$
         }
         string +=  ", wahlergebnisart = " + _details.getWahlergebnisart(); //$NON-NLS-1$
         string +=  ", korrekturnummer = " + _details.getKorrekturnummer(); //$NON-NLS-1$
         string +=  ", anzahlErgebnisseKumuliert = " + _details.getAnzahlErgebnisseKumuliert(); //$NON-NLS-1$
         string +=  ", vollstaendig = " + _details.isVollstaendig(); //$NON-NLS-1$
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
