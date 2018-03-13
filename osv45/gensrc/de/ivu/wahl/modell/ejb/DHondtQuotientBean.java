/*
 * DHondtQuotientBean
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
import de.ivu.wahl.modell.DHondtQuotientModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity DHondtQuotient as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class DHondtQuotientBean extends BMPBeanBase implements EntityBean, DHondtQuotientModel {
   private static final Category LOGGER = Log4J.configure(DHondtQuotientBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(DHondtQuotientBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected DHondtQuotientModel _details = null;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(DHondtQuotientModel details) throws CreateException, EJBException {
      DHondtQuotientModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_DHondtQuotient();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param iD_DHondtQuotient key element of the type {@link String}
     * @param id_Restsitzverteilung key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String iD_DHondtQuotient, String id_Restsitzverteilung) throws CreateException, EJBException {
      _details = (DHondtQuotientModel)createModel(iD_DHondtQuotient);
      _details.setID_Restsitzverteilung(id_Restsitzverteilung);
      create(_details);
      return iD_DHondtQuotient;
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
         return DHondtQuotientDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Restsitzverteilung ID of the objects to be searched
     * @return  {@link Collection} of the found DHondtQuotient-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByRestsitzverteilung(String id_Restsitzverteilung) throws IVUFinderException {
      try {
         return DHondtQuotientDBA.retrieveIDsByID_Restsitzverteilung(id_Restsitzverteilung);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link DHondtQuotient}, filtered by lauf.
     *
     * @param lauf searching condition
     * @return  {@link Collection} of primary keys of the entities DHondtQuotient
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByLauf(int lauf) throws IVUFinderException {
      try {
         return DHondtQuotientDBA.retrieveIDsByLauf(lauf);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link DHondtQuotient}, filtered by zaehler.
     *
     * @param zaehler searching condition
     * @return  {@link Collection} of primary keys of the entities DHondtQuotient
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByZaehler(int zaehler) throws IVUFinderException {
      try {
         return DHondtQuotientDBA.retrieveIDsByZaehler(zaehler);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link DHondtQuotient}, filtered by nenner.
     *
     * @param nenner searching condition
     * @return  {@link Collection} of primary keys of the entities DHondtQuotient
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByNenner(int nenner) throws IVUFinderException {
      try {
         return DHondtQuotientDBA.retrieveIDsByNenner(nenner);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link DHondtQuotient}, filtered by sitzAusRestanteil.
     *
     * @param sitzAusRestanteil searching condition
     * @return  {@link Collection} of primary keys of the entities DHondtQuotient
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllBySitzAusRestanteil(boolean sitzAusRestanteil) throws IVUFinderException {
      try {
         return DHondtQuotientDBA.retrieveIDsBySitzAusRestanteil(sitzAusRestanteil);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param iD_DHondtQuotient key element of the type {@link String}
     * @param id_Restsitzverteilung key element of the type {@link String}
     */
   public void ejbPostCreate(String iD_DHondtQuotient, String id_Restsitzverteilung) {
      // No functionality required
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(DHondtQuotientModel details) {
      // No functionality required
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public DHondtQuotientModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(DHondtQuotientModel details) {
      if (details instanceof DHondtQuotientModelImpl && _details instanceof DHondtQuotientModelImpl) {
         ((DHondtQuotientModelImpl)_details).copyFrom((DHondtQuotientModelImpl)details);
         checkRelations();
      } else {
         setID_Restsitzverteilung(details.getID_Restsitzverteilung());
         setLauf(details.getLauf());
         setZaehler(details.getZaehler());
         setNenner(details.getNenner());
         setSitzAusRestanteil(details.isSitzAusRestanteil());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (DHondtQuotientModel)details;
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
      if (null == _details.getID_Restsitzverteilung()) {
         _restsitzverteilung = null;
         _relchk_Restsitzverteilung = true;
      } else {
         _relchk_Restsitzverteilung = false;
      }
   }

   @Override
   protected void resetRelations() {
      _restsitzverteilung = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public DHondtQuotientModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of iD_DHondtQuotient in the entity DHondtQuotient
     *
     * @return value of the attribute iD_DHondtQuotient
     */
   public String getID_DHondtQuotient() {
      return _details.getID_DHondtQuotient();
   }

   /**
     * Sets the value of id_Restsitzverteilung in the entity DHondtQuotient
     *
     * @param id_Restsitzverteilung new value of the attribute id_Restsitzverteilung
     */
   public void setID_Restsitzverteilung(String id_Restsitzverteilung) {
      if (null == id_Restsitzverteilung) {
         _restsitzverteilung = null;
         _relchk_Restsitzverteilung = true;
      } else {
         String old = _details.getID_Restsitzverteilung();
         if (old == null || !old.equals(id_Restsitzverteilung)) {
            _relchk_Restsitzverteilung = false;
         }
      }
      _details.setID_Restsitzverteilung(id_Restsitzverteilung);
   }

   /**
     * Gets the value of id_Restsitzverteilung in the entity DHondtQuotient
     *
     * @return value of the attribute id_Restsitzverteilung
     */
   public String getID_Restsitzverteilung() {
      return _details.getID_Restsitzverteilung();
   }

   /**
     * Sets the value of lauf in the entity DHondtQuotient
     *
     * @param lauf new value of the attribute lauf
     */
   public void setLauf(int lauf) {
      _details.setLauf(lauf);
   }

   /**
     * Gets the value of lauf in the entity DHondtQuotient
     *
     * @return value of the attribute lauf
     */
   public int getLauf() {
      return _details.getLauf();
   }

   /**
     * Sets the value of zaehler in the entity DHondtQuotient
     *
     * @param zaehler new value of the attribute zaehler
     */
   public void setZaehler(int zaehler) {
      _details.setZaehler(zaehler);
   }

   /**
     * Gets the value of zaehler in the entity DHondtQuotient
     *
     * @return value of the attribute zaehler
     */
   public int getZaehler() {
      return _details.getZaehler();
   }

   /**
     * Sets the value of nenner in the entity DHondtQuotient
     *
     * @param nenner new value of the attribute nenner
     */
   public void setNenner(int nenner) {
      _details.setNenner(nenner);
   }

   /**
     * Gets the value of nenner in the entity DHondtQuotient
     *
     * @return value of the attribute nenner
     */
   public int getNenner() {
      return _details.getNenner();
   }

   /**
     * Sets the value of sitzAusRestanteil in the entity DHondtQuotient
     *
     * @param sitzAusRestanteil new value of the attribute sitzAusRestanteil
     */
   public void setSitzAusRestanteil(boolean sitzAusRestanteil) {
      _details.setSitzAusRestanteil(sitzAusRestanteil);
   }

   /**
     * Gets the value of sitzAusRestanteil in the entity DHondtQuotient
     *
     * @return value of the attribute sitzAusRestanteil
     */
   public boolean isSitzAusRestanteil() {
      return _details.isSitzAusRestanteil();
   }

   /**
     * Relation zu Restsitzverteilung
     */
   protected Restsitzverteilung _restsitzverteilung;

   /**
     * Flag for the validity of the relation Restsitzverteilung
     */
   protected boolean _relchk_Restsitzverteilung = false;

   /**
     * Navigation to the associated entity of the type {@link Restsitzverteilung}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Restsitzverteilung getRestsitzverteilung() throws EJBException {
      if (!_relchk_Restsitzverteilung) {
         if (null == _details.getID_Restsitzverteilung()) {
            _restsitzverteilung = null;
         } else if (null == _restsitzverteilung || !_restsitzverteilung.getPrimaryKey().equals(_details.getID_Restsitzverteilung())) {
            try {
               RestsitzverteilungHome home = RestsitzverteilungHome.HomeFinder.findHome(this);
               _restsitzverteilung = home.findByPrimaryKey(_details.getID_Restsitzverteilung());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find Restsitzverteilung", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Restsitzverteilung", fe); //$NON-NLS-1$
            }
         }
         _relchk_Restsitzverteilung = true;
      }
      return _restsitzverteilung;
   }

   /**
     * Setting of the associated entity of the type {@link Restsitzverteilung}
     *
     * @param restsitzverteilung the corresponding EJBObject
     */
   public void setRestsitzverteilung(Restsitzverteilung restsitzverteilung) {
      _restsitzverteilung = restsitzverteilung;
      _details.setID_Restsitzverteilung(restsitzverteilung == null ? null : (String)restsitzverteilung.getPrimaryKey());
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
         if (_details.getID_DHondtQuotient() != null) {
            string += "iD_DHondtQuotient = " + _details.getID_DHondtQuotient(); //$NON-NLS-1$
         }
         if (_details.getID_Restsitzverteilung() != null) {
            string +=  ", id_Restsitzverteilung = " + _details.getID_Restsitzverteilung(); //$NON-NLS-1$
         }
         string +=  ", lauf = " + _details.getLauf(); //$NON-NLS-1$
         string +=  ", zaehler = " + _details.getZaehler(); //$NON-NLS-1$
         string +=  ", nenner = " + _details.getNenner(); //$NON-NLS-1$
         string +=  ", sitzAusRestanteil = " + _details.isSitzAusRestanteil(); //$NON-NLS-1$
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
