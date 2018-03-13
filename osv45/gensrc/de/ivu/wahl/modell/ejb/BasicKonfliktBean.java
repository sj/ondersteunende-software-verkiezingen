/*
 * BasicKonfliktBean
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
import de.ivu.wahl.modell.KonfliktModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Konflikt as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicKonfliktBean extends BMPBeanBase implements EntityBean, KonfliktModel {
   private static final Category LOGGER = Log4J.configure(BasicKonfliktBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicKonfliktBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected KonfliktModel _details = null;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(KonfliktModel details) throws CreateException, EJBException {
      KonfliktModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Konflikt();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Konflikt key element of the type {@link String}
     * @param id_Ergebniseingang key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Konflikt, String id_Ergebniseingang) throws CreateException, EJBException {
      _details = (KonfliktModel)createModel(id_Konflikt);
      _details.setID_Ergebniseingang(id_Ergebniseingang);
      create(_details);
      return id_Konflikt;
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
         return KonfliktDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_LosAlternative ID of the objects to be searched
     * @return  {@link Collection} of the found Konflikt-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByLosAlternative(String id_LosAlternative) throws IVUFinderException {
      try {
         return KonfliktDBA.retrieveIDsByID_LosAlternative(id_LosAlternative);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Ergebniseingang ID of the objects to be searched
     * @return  {@link Collection} of the found Konflikt-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByErgebniseingang(String id_Ergebniseingang) throws IVUFinderException {
      try {
         return KonfliktDBA.retrieveIDsByID_Ergebniseingang(id_Ergebniseingang);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Konflikt}, filtered by nummer.
     *
     * @param nummer searching condition
     * @return  {@link Collection} of primary keys of the entities Konflikt
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByNummer(int nummer) throws IVUFinderException {
      try {
         return KonfliktDBA.retrieveIDsByNummer(nummer);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Konflikt}, filtered by konfliktart.
     *
     * @param konfliktart searching condition
     * @return  {@link Collection} of primary keys of the entities Konflikt
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByKonfliktart(int konfliktart) throws IVUFinderException {
      try {
         return KonfliktDBA.retrieveIDsByKonfliktart(konfliktart);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Konflikt key element of the type {@link String}
     * @param id_Ergebniseingang key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Konflikt, String id_Ergebniseingang) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(KonfliktModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public KonfliktModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(KonfliktModel details) {
      if (details instanceof KonfliktModelImpl && _details instanceof KonfliktModelImpl) {
         ((KonfliktModelImpl)_details).copyFrom((KonfliktModelImpl)details);
         checkRelations();
      } else {
         setID_Ergebniseingang(details.getID_Ergebniseingang());
         setID_LosAlternative(details.getID_LosAlternative());
         setNummer(details.getNummer());
         setKonfliktart(details.getKonfliktart());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (KonfliktModel)details;
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
      if (null == _details.getID_LosAlternative()) {
         _losAlternative = null;
         _relchk_LosAlternative = true;
      } else {
         _relchk_LosAlternative = false;
      }
      if (null == _details.getID_Ergebniseingang()) {
         _ergebniseingang = null;
         _relchk_Ergebniseingang = true;
      } else {
         _relchk_Ergebniseingang = false;
      }
   }

   @Override
   protected void resetRelations() {
      _losAlternative = null;
      _ergebniseingang = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public KonfliktModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Konflikt in the entity Konflikt
     *
     * @return value of the attribute id_Konflikt
     */
   public String getID_Konflikt() {
      return _details.getID_Konflikt();
   }

   /**
     * Sets the value of id_Ergebniseingang in the entity Konflikt
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
     * Gets the value of id_Ergebniseingang in the entity Konflikt
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _details.getID_Ergebniseingang();
   }

   /**
     * Sets the value of id_LosAlternative in the entity Konflikt
     *
     * @param id_LosAlternative new value of the attribute id_LosAlternative
     */
   public void setID_LosAlternative(String id_LosAlternative) {
      if (null == id_LosAlternative) {
         _losAlternative = null;
         _relchk_LosAlternative = true;
      } else {
         String old = _details.getID_LosAlternative();
         if (old == null || !old.equals(id_LosAlternative)) {
            _relchk_LosAlternative = false;
         }
      }
      _details.setID_LosAlternative(id_LosAlternative);
   }

   /**
     * Gets the value of id_LosAlternative in the entity Konflikt
     *
     * @return value of the attribute id_LosAlternative
     */
   public String getID_LosAlternative() {
      return _details.getID_LosAlternative();
   }

   /**
     * Sets the value of nummer in the entity Konflikt
     *
     * @param nummer new value of the attribute nummer
     */
   public void setNummer(int nummer) {
      _details.setNummer(nummer);
   }

   /**
     * Gets the value of nummer in the entity Konflikt
     *
     * @return value of the attribute nummer
     */
   public int getNummer() {
      return _details.getNummer();
   }

   /**
     * Sets the value of konfliktart in the entity Konflikt
     *
     * @param konfliktart new value of the attribute konfliktart
     */
   public void setKonfliktart(int konfliktart) {
      _details.setKonfliktart(konfliktart);
   }

   /**
     * Gets the value of konfliktart in the entity Konflikt
     *
     * @return value of the attribute konfliktart
     */
   public int getKonfliktart() {
      return _details.getKonfliktart();
   }

   /**
     * Relation zu LosAlternative
     */
   protected Alternative _losAlternative;

   /**
     * Flag for the validity of the relation LosAlternative
     */
   protected boolean _relchk_LosAlternative = false;

   /**
     * Navigation to the associated entity of the type {@link Alternative}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Alternative getLosAlternative() throws EJBException {
      if (!_relchk_LosAlternative) {
         if (null == _details.getID_LosAlternative()) {
            _losAlternative = null;
         } else if (null == _losAlternative || !_losAlternative.getPrimaryKey().equals(_details.getID_LosAlternative())) {
            try {
               AlternativeHome home = AlternativeHome.HomeFinder.findHome(this);
               _losAlternative = home.findByPrimaryKey(_details.getID_LosAlternative());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find LosAlternative", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Alternative", fe); //$NON-NLS-1$
            }
         }
         _relchk_LosAlternative = true;
      }
      return _losAlternative;
   }

   /**
     * Setting of the associated entity of the type {@link Alternative}
     *
     * @param losAlternative the corresponding EJBObject
     */
   public void setLosAlternative(Alternative losAlternative) {
      _losAlternative = losAlternative;
      _details.setID_LosAlternative(losAlternative == null ? null : (String)losAlternative.getPrimaryKey());
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
     * Returns the set of entities of the type {@link Alternative}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Alternative}
     * @throws EJBException: an error occurred
     */
   public Collection<Alternative> getAlternativeCol() throws EJBException {
      AlternativeHome alternativeHome = AlternativeHome.HomeFinder.findHome(this);
      try {
         return alternativeHome.findAllByKonflikt(_details.getID_Konflikt());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Alternative}.
     *
     * @param alternative Alternative-object
     */
   public void addAlternative(Alternative alternative) {
      alternative.setID_Konflikt(_details.getID_Konflikt());
   }

   /**
     * Adds the object to the set of entities of the type {@link Alternative}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllAlternativeCol(Collection<Alternative> col) {
      for (Alternative alternative : col) {
         addAlternative(alternative);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Alternative}.
     *
     * @param alternative Alternative-EJBObject, which is removed from the set.
     */
   public void removeAlternative(Alternative alternative) {
      alternative.setID_Konflikt(null);
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
         if (_details.getID_Konflikt() != null) {
            string += "id_Konflikt = " + _details.getID_Konflikt(); //$NON-NLS-1$
         }
         if (_details.getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + _details.getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (_details.getID_LosAlternative() != null) {
            string +=  ", id_LosAlternative = " + _details.getID_LosAlternative(); //$NON-NLS-1$
         }
         string +=  ", nummer = " + _details.getNummer(); //$NON-NLS-1$
         string +=  ", konfliktart = " + _details.getKonfliktart(); //$NON-NLS-1$
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
