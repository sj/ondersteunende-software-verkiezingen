/*
 * BasicListenkandidaturErgebnisBean
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
import de.ivu.wahl.modell.ListenkandidaturErgebnisModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity ListenkandidaturErgebnis as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicListenkandidaturErgebnisBean extends BMPBeanBase implements EntityBean, ListenkandidaturErgebnisModel {
   private static final Category LOGGER = Log4J.configure(BasicListenkandidaturErgebnisBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicListenkandidaturErgebnisBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected ListenkandidaturErgebnisModel _details = null;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(ListenkandidaturErgebnisModel details) throws CreateException, EJBException {
      ListenkandidaturErgebnisModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_ListenkandidaturErgebnis();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_ListenkandidaturErgebnis key element of the type {@link String}
     * @param id_Listenkandidatur key element of the type {@link String}
     * @param id_Ergebniseingang key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_ListenkandidaturErgebnis, String id_Listenkandidatur, String id_Ergebniseingang) throws CreateException, EJBException {
      _details = (ListenkandidaturErgebnisModel)createModel(id_ListenkandidaturErgebnis);
      _details.setID_Listenkandidatur(id_Listenkandidatur);
      _details.setID_Ergebniseingang(id_Ergebniseingang);
      create(_details);
      return id_ListenkandidaturErgebnis;
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
         return ListenkandidaturErgebnisDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Listenkandidatur ID of the objects to be searched
     * @return  {@link Collection} of the found ListenkandidaturErgebnis-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByListenkandidatur(String id_Listenkandidatur) throws IVUFinderException {
      try {
         return ListenkandidaturErgebnisDBA.retrieveIDsByID_Listenkandidatur(id_Listenkandidatur);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Ergebniseingang ID of the objects to be searched
     * @return  {@link Collection} of the found ListenkandidaturErgebnis-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByErgebniseingang(String id_Ergebniseingang) throws IVUFinderException {
      try {
         return ListenkandidaturErgebnisDBA.retrieveIDsByID_Ergebniseingang(id_Ergebniseingang);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link ListenkandidaturErgebnis}, filtered by listenplatz.
     *
     * @param listenplatz searching condition
     * @return  {@link Collection} of primary keys of the entities ListenkandidaturErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByListenplatz(int listenplatz) throws IVUFinderException {
      try {
         return ListenkandidaturErgebnisDBA.retrieveIDsByListenplatz(listenplatz);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link ListenkandidaturErgebnis}, filtered by gewaehlt.
     *
     * @param gewaehlt searching condition
     * @return  {@link Collection} of primary keys of the entities ListenkandidaturErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByGewaehlt(boolean gewaehlt) throws IVUFinderException {
      try {
         return ListenkandidaturErgebnisDBA.retrieveIDsByGewaehlt(gewaehlt);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link ListenkandidaturErgebnis}, filtered by gewaehltInGruppe.
     *
     * @param gewaehltInGruppe searching condition
     * @return  {@link Collection} of primary keys of the entities ListenkandidaturErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByGewaehltInGruppe(boolean gewaehltInGruppe) throws IVUFinderException {
      try {
         return ListenkandidaturErgebnisDBA.retrieveIDsByGewaehltInGruppe(gewaehltInGruppe);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link ListenkandidaturErgebnis}, filtered by bevorzugtGewaehlt.
     *
     * @param bevorzugtGewaehlt searching condition
     * @return  {@link Collection} of primary keys of the entities ListenkandidaturErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByBevorzugtGewaehlt(boolean bevorzugtGewaehlt) throws IVUFinderException {
      try {
         return ListenkandidaturErgebnisDBA.retrieveIDsByBevorzugtGewaehlt(bevorzugtGewaehlt);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link ListenkandidaturErgebnis}, filtered by losteilnehmer.
     *
     * @param losteilnehmer searching condition
     * @return  {@link Collection} of primary keys of the entities ListenkandidaturErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByLosteilnehmer(boolean losteilnehmer) throws IVUFinderException {
      try {
         return ListenkandidaturErgebnisDBA.retrieveIDsByLosteilnehmer(losteilnehmer);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link ListenkandidaturErgebnis}, filtered by losgewinner.
     *
     * @param losgewinner searching condition
     * @return  {@link Collection} of primary keys of the entities ListenkandidaturErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByLosgewinner(boolean losgewinner) throws IVUFinderException {
      try {
         return ListenkandidaturErgebnisDBA.retrieveIDsByLosgewinner(losgewinner);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_ListenkandidaturErgebnis key element of the type {@link String}
     * @param id_Listenkandidatur key element of the type {@link String}
     * @param id_Ergebniseingang key element of the type {@link String}
     */
   public void ejbPostCreate(String id_ListenkandidaturErgebnis, String id_Listenkandidatur, String id_Ergebniseingang) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(ListenkandidaturErgebnisModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public ListenkandidaturErgebnisModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(ListenkandidaturErgebnisModel details) {
      if (details instanceof ListenkandidaturErgebnisModelImpl && _details instanceof ListenkandidaturErgebnisModelImpl) {
         ((ListenkandidaturErgebnisModelImpl)_details).copyFrom((ListenkandidaturErgebnisModelImpl)details);
         checkRelations();
      } else {
         setID_Listenkandidatur(details.getID_Listenkandidatur());
         setID_Ergebniseingang(details.getID_Ergebniseingang());
         setListenplatz(details.getListenplatz());
         setGewaehlt(details.isGewaehlt());
         setGewaehltInGruppe(details.isGewaehltInGruppe());
         setBevorzugtGewaehlt(details.isBevorzugtGewaehlt());
         setLosteilnehmer(details.isLosteilnehmer());
         setLosgewinner(details.isLosgewinner());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (ListenkandidaturErgebnisModel)details;
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
      if (null == _details.getID_Listenkandidatur()) {
         _listenkandidatur = null;
         _relchk_Listenkandidatur = true;
      } else {
         _relchk_Listenkandidatur = false;
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
      _listenkandidatur = null;
      _ergebniseingang = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public ListenkandidaturErgebnisModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_ListenkandidaturErgebnis in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute id_ListenkandidaturErgebnis
     */
   public String getID_ListenkandidaturErgebnis() {
      return _details.getID_ListenkandidaturErgebnis();
   }

   /**
     * Sets the value of id_Listenkandidatur in the entity ListenkandidaturErgebnis
     *
     * @param id_Listenkandidatur new value of the attribute id_Listenkandidatur
     */
   public void setID_Listenkandidatur(String id_Listenkandidatur) {
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
     * Gets the value of id_Listenkandidatur in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute id_Listenkandidatur
     */
   public String getID_Listenkandidatur() {
      return _details.getID_Listenkandidatur();
   }

   /**
     * Sets the value of id_Ergebniseingang in the entity ListenkandidaturErgebnis
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
     * Gets the value of id_Ergebniseingang in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _details.getID_Ergebniseingang();
   }

   /**
     * Sets the value of listenplatz in the entity ListenkandidaturErgebnis
     *
     * @param listenplatz new value of the attribute listenplatz
     */
   public void setListenplatz(int listenplatz) {
      _details.setListenplatz(listenplatz);
   }

   /**
     * Gets the value of listenplatz in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute listenplatz
     */
   public int getListenplatz() {
      return _details.getListenplatz();
   }

   /**
     * Sets the value of gewaehlt in the entity ListenkandidaturErgebnis
     *
     * @param gewaehlt new value of the attribute gewaehlt
     */
   public void setGewaehlt(boolean gewaehlt) {
      _details.setGewaehlt(gewaehlt);
   }

   /**
     * Gets the value of gewaehlt in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute gewaehlt
     */
   public boolean isGewaehlt() {
      return _details.isGewaehlt();
   }

   /**
     * Sets the value of gewaehltInGruppe in the entity ListenkandidaturErgebnis
     *
     * @param gewaehltInGruppe new value of the attribute gewaehltInGruppe
     */
   public void setGewaehltInGruppe(boolean gewaehltInGruppe) {
      _details.setGewaehltInGruppe(gewaehltInGruppe);
   }

   /**
     * Gets the value of gewaehltInGruppe in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute gewaehltInGruppe
     */
   public boolean isGewaehltInGruppe() {
      return _details.isGewaehltInGruppe();
   }

   /**
     * Sets the value of bevorzugtGewaehlt in the entity ListenkandidaturErgebnis
     *
     * @param bevorzugtGewaehlt new value of the attribute bevorzugtGewaehlt
     */
   public void setBevorzugtGewaehlt(boolean bevorzugtGewaehlt) {
      _details.setBevorzugtGewaehlt(bevorzugtGewaehlt);
   }

   /**
     * Gets the value of bevorzugtGewaehlt in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute bevorzugtGewaehlt
     */
   public boolean isBevorzugtGewaehlt() {
      return _details.isBevorzugtGewaehlt();
   }

   /**
     * Sets the value of losteilnehmer in the entity ListenkandidaturErgebnis
     *
     * @param losteilnehmer new value of the attribute losteilnehmer
     */
   public void setLosteilnehmer(boolean losteilnehmer) {
      _details.setLosteilnehmer(losteilnehmer);
   }

   /**
     * Gets the value of losteilnehmer in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute losteilnehmer
     */
   public boolean isLosteilnehmer() {
      return _details.isLosteilnehmer();
   }

   /**
     * Sets the value of losgewinner in the entity ListenkandidaturErgebnis
     *
     * @param losgewinner new value of the attribute losgewinner
     */
   public void setLosgewinner(boolean losgewinner) {
      _details.setLosgewinner(losgewinner);
   }

   /**
     * Gets the value of losgewinner in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute losgewinner
     */
   public boolean isLosgewinner() {
      return _details.isLosgewinner();
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
      _listenkandidatur = listenkandidatur;
      _details.setID_Listenkandidatur(listenkandidatur == null ? null : (String)listenkandidatur.getPrimaryKey());
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
         if (_details.getID_ListenkandidaturErgebnis() != null) {
            string += "id_ListenkandidaturErgebnis = " + _details.getID_ListenkandidaturErgebnis(); //$NON-NLS-1$
         }
         if (_details.getID_Listenkandidatur() != null) {
            string +=  ", id_Listenkandidatur = " + _details.getID_Listenkandidatur(); //$NON-NLS-1$
         }
         if (_details.getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + _details.getID_Ergebniseingang(); //$NON-NLS-1$
         }
         string +=  ", listenplatz = " + _details.getListenplatz(); //$NON-NLS-1$
         string +=  ", gewaehlt = " + _details.isGewaehlt(); //$NON-NLS-1$
         string +=  ", gewaehltInGruppe = " + _details.isGewaehltInGruppe(); //$NON-NLS-1$
         string +=  ", bevorzugtGewaehlt = " + _details.isBevorzugtGewaehlt(); //$NON-NLS-1$
         string +=  ", losteilnehmer = " + _details.isLosteilnehmer(); //$NON-NLS-1$
         string +=  ", losgewinner = " + _details.isLosgewinner(); //$NON-NLS-1$
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
