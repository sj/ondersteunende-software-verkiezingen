/*
 * BasicListenkandidaturBean
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
import de.ivu.wahl.modell.ListenkandidaturModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Listenkandidatur as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicListenkandidaturBean extends BMPBeanBase implements EntityBean, ListenkandidaturModel {
   private static final Category LOGGER = Log4J.configure(BasicListenkandidaturBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicListenkandidaturBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected ListenkandidaturModel _details = null;

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
   public String ejbCreate(ListenkandidaturModel details) throws CreateException, EJBException {
      ListenkandidaturModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Listenkandidatur();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Listenkandidatur key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Listenkandidatur) throws CreateException, EJBException {
      _details = (ListenkandidaturModel)createModel(id_Listenkandidatur);
      create(_details);
      return id_Listenkandidatur;
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
         return ListenkandidaturDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Liste ID of the objects to be searched
     * @return  {@link Collection} of the found Listenkandidatur-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByListe(String id_Liste) throws IVUFinderException {
      try {
         return ListenkandidaturDBA.retrieveIDsByID_Liste(id_Liste);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Personendaten ID of the objects to be searched
     * @return  {@link Collection} of the found Listenkandidatur-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByPersonendaten(String id_Personendaten) throws IVUFinderException {
      try {
         return ListenkandidaturDBA.retrieveIDsByID_Personendaten(id_Personendaten);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Wahl ID of the objects to be searched
     * @return  {@link Collection} of the found Listenkandidatur-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByWahl(String id_Wahl) throws IVUFinderException {
      try {
         return ListenkandidaturDBA.retrieveIDsByID_Wahl(id_Wahl);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Listenkandidatur}, filtered by listenplatz.
     *
     * @param listenplatz searching condition
     * @return  {@link Collection} of primary keys of the entities Listenkandidatur
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByListenplatz(int listenplatz) throws IVUFinderException {
      try {
         return ListenkandidaturDBA.retrieveIDsByListenplatz(listenplatz);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Listenkandidatur key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Listenkandidatur) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(ListenkandidaturModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public ListenkandidaturModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(ListenkandidaturModel details) {
      if (details instanceof ListenkandidaturModelImpl && _details instanceof ListenkandidaturModelImpl) {
         ((ListenkandidaturModelImpl)_details).copyFrom((ListenkandidaturModelImpl)details);
         checkRelations();
      } else {
         setID_Liste(details.getID_Liste());
         setID_Wahl(details.getID_Wahl());
         setID_Personendaten(details.getID_Personendaten());
         setListenplatz(details.getListenplatz());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (ListenkandidaturModel)details;
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
      if (null == _details.getID_Liste()) {
         _liste = null;
         _relchk_Liste = true;
      } else {
         _relchk_Liste = false;
      }
      if (null == _details.getID_Personendaten()) {
         _personendaten = null;
         _relchk_Personendaten = true;
      } else {
         _relchk_Personendaten = false;
      }
      if (null == _details.getID_Wahl()) {
         _wahl = null;
         _relchk_Wahl = true;
      } else {
         _relchk_Wahl = false;
      }
   }

   @Override
   protected void resetRelations() {
      _liste = null;
      _personendaten = null;
      _wahl = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public ListenkandidaturModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Listenkandidatur in the entity Listenkandidatur
     *
     * @return value of the attribute id_Listenkandidatur
     */
   public String getID_Listenkandidatur() {
      return _details.getID_Listenkandidatur();
   }

   /**
     * Sets the value of id_Liste in the entity Listenkandidatur
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   public void setID_Liste(String id_Liste) {
      if (null == id_Liste) {
         _liste = null;
         _relchk_Liste = true;
      } else {
         String old = _details.getID_Liste();
         if (old == null || !old.equals(id_Liste)) {
            _relchk_Liste = false;
         }
      }
      _details.setID_Liste(id_Liste);
   }

   /**
     * Gets the value of id_Liste in the entity Listenkandidatur
     *
     * @return value of the attribute id_Liste
     */
   public String getID_Liste() {
      return _details.getID_Liste();
   }

   /**
     * Sets the value of id_Wahl in the entity Listenkandidatur
     *
     * @param id_Wahl new value of the attribute id_Wahl
     */
   public void setID_Wahl(String id_Wahl) {
      if (null == id_Wahl) {
         _wahl = null;
         _relchk_Wahl = true;
      } else {
         String old = _details.getID_Wahl();
         if (old == null || !old.equals(id_Wahl)) {
            _relchk_Wahl = false;
         }
      }
      _details.setID_Wahl(id_Wahl);
   }

   /**
     * Gets the value of id_Wahl in the entity Listenkandidatur
     *
     * @return value of the attribute id_Wahl
     */
   public String getID_Wahl() {
      return _details.getID_Wahl();
   }

   /**
     * Sets the value of id_Personendaten in the entity Listenkandidatur
     *
     * @param id_Personendaten new value of the attribute id_Personendaten
     */
   public void setID_Personendaten(String id_Personendaten) {
      if (null == id_Personendaten) {
         _personendaten = null;
         _relchk_Personendaten = true;
      } else {
         String old = _details.getID_Personendaten();
         if (old == null || !old.equals(id_Personendaten)) {
            _relchk_Personendaten = false;
         }
      }
      _details.setID_Personendaten(id_Personendaten);
   }

   /**
     * Gets the value of id_Personendaten in the entity Listenkandidatur
     *
     * @return value of the attribute id_Personendaten
     */
   public String getID_Personendaten() {
      return _details.getID_Personendaten();
   }

   /**
     * Sets the value of listenplatz in the entity Listenkandidatur
     *
     * @param listenplatz new value of the attribute listenplatz
     */
   public void setListenplatz(int listenplatz) {
      _details.setListenplatz(listenplatz);
   }

   /**
     * Gets the value of listenplatz in the entity Listenkandidatur
     *
     * @return value of the attribute listenplatz
     */
   public int getListenplatz() {
      return _details.getListenplatz();
   }

   /**
     * Relation zu Liste
     */
   protected Liste _liste;

   /**
     * Flag for the validity of the relation Liste
     */
   protected boolean _relchk_Liste = false;

   /**
     * Navigation to the associated entity of the type {@link Liste}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Liste getListe() throws EJBException {
      if (!_relchk_Liste) {
         if (null == _details.getID_Liste()) {
            _liste = null;
         } else if (null == _liste || !_liste.getPrimaryKey().equals(_details.getID_Liste())) {
            try {
               ListeHome home = ListeHome.HomeFinder.findHome(this);
               _liste = home.findByPrimaryKey(_details.getID_Liste());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find Liste", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Liste", fe); //$NON-NLS-1$
            }
         }
         _relchk_Liste = true;
      }
      return _liste;
   }

   /**
     * Setting of the associated entity of the type {@link Liste}
     *
     * @param liste the corresponding EJBObject
     */
   public void setListe(Liste liste) {
      _liste = liste;
      _details.setID_Liste(liste == null ? null : (String)liste.getPrimaryKey());
   }

   /**
     * Relation zu Personendaten
     */
   protected Personendaten _personendaten;

   /**
     * Flag for the validity of the relation Personendaten
     */
   protected boolean _relchk_Personendaten = false;

   /**
     * Navigation to the associated entity of the type {@link Personendaten}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Personendaten getPersonendaten() throws EJBException {
      if (!_relchk_Personendaten) {
         if (null == _details.getID_Personendaten()) {
            _personendaten = null;
         } else if (null == _personendaten || !_personendaten.getPrimaryKey().equals(_details.getID_Personendaten())) {
            try {
               PersonendatenHome home = PersonendatenHome.HomeFinder.findHome(this);
               _personendaten = home.findByPrimaryKey(_details.getID_Personendaten());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find Personendaten", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Personendaten", fe); //$NON-NLS-1$
            }
         }
         _relchk_Personendaten = true;
      }
      return _personendaten;
   }

   /**
     * Setting of the associated entity of the type {@link Personendaten}
     *
     * @param personendaten the corresponding EJBObject
     */
   public void setPersonendaten(Personendaten personendaten) {
      _personendaten = personendaten;
      _details.setID_Personendaten(personendaten == null ? null : (String)personendaten.getPrimaryKey());
   }

   /**
     * Relation zu Wahl
     */
   protected Wahl _wahl;

   /**
     * Flag for the validity of the relation Wahl
     */
   protected boolean _relchk_Wahl = false;

   /**
     * Navigation to the associated entity of the type {@link Wahl}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Wahl getWahl() throws EJBException {
      if (!_relchk_Wahl) {
         if (null == _details.getID_Wahl()) {
            _wahl = null;
         } else if (null == _wahl || !_wahl.getPrimaryKey().equals(_details.getID_Wahl())) {
            try {
               WahlHome home = WahlHome.HomeFinder.findHome(this);
               _wahl = home.findByPrimaryKey(_details.getID_Wahl());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find Wahl", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Wahl", fe); //$NON-NLS-1$
            }
         }
         _relchk_Wahl = true;
      }
      return _wahl;
   }

   /**
     * Setting of the associated entity of the type {@link Wahl}
     *
     * @param wahl the corresponding EJBObject
     */
   public void setWahl(Wahl wahl) {
      _wahl = wahl;
      _details.setID_Wahl(wahl == null ? null : (String)wahl.getPrimaryKey());
   }

   /**
     * Returns the set of entities of the type {@link ListenkandidaturErgebnis}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link ListenkandidaturErgebnis}
     * @throws EJBException: an error occurred
     */
   public Collection<ListenkandidaturErgebnis> getListenkandidaturErgebnisCol() throws EJBException {
      ListenkandidaturErgebnisHome listenkandidaturErgebnisHome = ListenkandidaturErgebnisHome.HomeFinder.findHome(this);
      try {
         return listenkandidaturErgebnisHome.findAllByListenkandidatur(_details.getID_Listenkandidatur());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link ListenkandidaturErgebnis}.
     *
     * @param listenkandidaturErgebnis ListenkandidaturErgebnis-object
     */
   public void addListenkandidaturErgebnis(ListenkandidaturErgebnis listenkandidaturErgebnis) {
      listenkandidaturErgebnis.setID_Listenkandidatur(_details.getID_Listenkandidatur());
   }

   /**
     * Adds the object to the set of entities of the type {@link ListenkandidaturErgebnis}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllListenkandidaturErgebnisCol(Collection<ListenkandidaturErgebnis> col) {
      for (ListenkandidaturErgebnis listenkandidaturErgebnis : col) {
         addListenkandidaturErgebnis(listenkandidaturErgebnis);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link ListenkandidaturErgebnis}.
     *
     * @param listenkandidaturErgebnis ListenkandidaturErgebnis-EJBObject, which is removed from the set.
     */
   public void removeListenkandidaturErgebnis(ListenkandidaturErgebnis listenkandidaturErgebnis) {
      listenkandidaturErgebnis.setID_Listenkandidatur(null);
   }

   /**
     * Returns the set of entities of the type {@link Stimmergebnis}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Stimmergebnis}
     * @throws EJBException: an error occurred
     */
   public Collection<Stimmergebnis> getStimmergebnisCol() throws EJBException {
      StimmergebnisHome stimmergebnisHome = StimmergebnisHome.HomeFinder.findHome(this);
      try {
         return stimmergebnisHome.findAllByListenkandidatur(_details.getID_Listenkandidatur());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Stimmergebnis}.
     *
     * @param stimmergebnis Stimmergebnis-object
     */
   public void addStimmergebnis(Stimmergebnis stimmergebnis) {
      stimmergebnis.setID_Listenkandidatur(_details.getID_Listenkandidatur());
   }

   /**
     * Adds the object to the set of entities of the type {@link Stimmergebnis}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllStimmergebnisCol(Collection<Stimmergebnis> col) {
      for (Stimmergebnis stimmergebnis : col) {
         addStimmergebnis(stimmergebnis);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Stimmergebnis}.
     *
     * @param stimmergebnis Stimmergebnis-EJBObject, which is removed from the set.
     */
   public void removeStimmergebnis(Stimmergebnis stimmergebnis) {
      stimmergebnis.setID_Listenkandidatur(null);
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
         if (_details.getID_Listenkandidatur() != null) {
            string += "id_Listenkandidatur = " + _details.getID_Listenkandidatur(); //$NON-NLS-1$
         }
         if (_details.getID_Liste() != null) {
            string +=  ", id_Liste = " + _details.getID_Liste(); //$NON-NLS-1$
         }
         if (_details.getID_Wahl() != null) {
            string +=  ", id_Wahl = " + _details.getID_Wahl(); //$NON-NLS-1$
         }
         if (_details.getID_Personendaten() != null) {
            string +=  ", id_Personendaten = " + _details.getID_Personendaten(); //$NON-NLS-1$
         }
         string +=  ", listenplatz = " + _details.getListenplatz(); //$NON-NLS-1$
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
