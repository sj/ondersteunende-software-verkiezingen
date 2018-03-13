/*
 * BasicRestsitzverteilungBean
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
import de.ivu.wahl.modell.RestsitzverteilungModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Restsitzverteilung as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicRestsitzverteilungBean extends BMPBeanBase implements EntityBean, RestsitzverteilungModel {
   private static final Category LOGGER = Log4J.configure(BasicRestsitzverteilungBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicRestsitzverteilungBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected RestsitzverteilungModel _details = null;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(RestsitzverteilungModel details) throws CreateException, EJBException {
      RestsitzverteilungModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Restsitzverteilung();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Restsitzverteilung key element of the type {@link String}
     * @param id_Ergebniseingang key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Restsitzverteilung, String id_Ergebniseingang) throws CreateException, EJBException {
      _details = (RestsitzverteilungModel)createModel(id_Restsitzverteilung);
      _details.setID_Ergebniseingang(id_Ergebniseingang);
      create(_details);
      return id_Restsitzverteilung;
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
         return RestsitzverteilungDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Listenkombination ID of the objects to be searched
     * @return  {@link Collection} of the found Restsitzverteilung-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByListenkombination(String id_Listenkombination) throws IVUFinderException {
      try {
         return RestsitzverteilungDBA.retrieveIDsByID_Listenkombination(id_Listenkombination);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Ergebniseingang ID of the objects to be searched
     * @return  {@link Collection} of the found Restsitzverteilung-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByErgebniseingang(String id_Ergebniseingang) throws IVUFinderException {
      try {
         return RestsitzverteilungDBA.retrieveIDsByID_Ergebniseingang(id_Ergebniseingang);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Liste ID of the objects to be searched
     * @return  {@link Collection} of the found Restsitzverteilung-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByListe(String id_Liste) throws IVUFinderException {
      try {
         return RestsitzverteilungDBA.retrieveIDsByID_Liste(id_Liste);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gruppe ID of the objects to be searched
     * @return  {@link Collection} of the found Restsitzverteilung-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByGruppe(String id_Gruppe) throws IVUFinderException {
      try {
         return RestsitzverteilungDBA.retrieveIDsByID_Gruppe(id_Gruppe);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Restsitzverteilung}, filtered by verteilung.
     *
     * @param verteilung searching condition
     * @return  {@link Collection} of primary keys of the entities Restsitzverteilung
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByVerteilung(int verteilung) throws IVUFinderException {
      try {
         return RestsitzverteilungDBA.retrieveIDsByVerteilung(verteilung);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Restsitzverteilung}, filtered by sitze.
     *
     * @param sitze searching condition
     * @return  {@link Collection} of primary keys of the entities Restsitzverteilung
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllBySitze(int sitze) throws IVUFinderException {
      try {
         return RestsitzverteilungDBA.retrieveIDsBySitze(sitze);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Restsitzverteilung}, filtered by sitzeRest.
     *
     * @param sitzeRest searching condition
     * @return  {@link Collection} of primary keys of the entities Restsitzverteilung
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllBySitzeRest(int sitzeRest) throws IVUFinderException {
      try {
         return RestsitzverteilungDBA.retrieveIDsBySitzeRest(sitzeRest);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Restsitzverteilung}, filtered by sitzeGesamtZuVerteilen.
     *
     * @param sitzeGesamtZuVerteilen searching condition
     * @return  {@link Collection} of primary keys of the entities Restsitzverteilung
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllBySitzeGesamtZuVerteilen(int sitzeGesamtZuVerteilen) throws IVUFinderException {
      try {
         return RestsitzverteilungDBA.retrieveIDsBySitzeGesamtZuVerteilen(sitzeGesamtZuVerteilen);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Restsitzverteilung key element of the type {@link String}
     * @param id_Ergebniseingang key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Restsitzverteilung, String id_Ergebniseingang) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(RestsitzverteilungModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public RestsitzverteilungModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(RestsitzverteilungModel details) {
      if (details instanceof RestsitzverteilungModelImpl && _details instanceof RestsitzverteilungModelImpl) {
         ((RestsitzverteilungModelImpl)_details).copyFrom((RestsitzverteilungModelImpl)details);
         checkRelations();
      } else {
         setID_Ergebniseingang(details.getID_Ergebniseingang());
         setID_Liste(details.getID_Liste());
         setID_Gruppe(details.getID_Gruppe());
         setID_Listenkombination(details.getID_Listenkombination());
         setVerteilung(details.getVerteilung());
         setSitze(details.getSitze());
         setSitzeRest(details.getSitzeRest());
         setSitzeGesamtZuVerteilen(details.getSitzeGesamtZuVerteilen());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (RestsitzverteilungModel)details;
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
      if (null == _details.getID_Liste()) {
         _liste = null;
         _relchk_Liste = true;
      } else {
         _relchk_Liste = false;
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
      _liste = null;
      _gruppe = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public RestsitzverteilungModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Restsitzverteilung in the entity Restsitzverteilung
     *
     * @return value of the attribute id_Restsitzverteilung
     */
   public String getID_Restsitzverteilung() {
      return _details.getID_Restsitzverteilung();
   }

   /**
     * Sets the value of id_Ergebniseingang in the entity Restsitzverteilung
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
     * Gets the value of id_Ergebniseingang in the entity Restsitzverteilung
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _details.getID_Ergebniseingang();
   }

   /**
     * Sets the value of id_Liste in the entity Restsitzverteilung
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
     * Gets the value of id_Liste in the entity Restsitzverteilung
     *
     * @return value of the attribute id_Liste
     */
   public String getID_Liste() {
      return _details.getID_Liste();
   }

   /**
     * Sets the value of id_Gruppe in the entity Restsitzverteilung
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
     * Gets the value of id_Gruppe in the entity Restsitzverteilung
     *
     * @return value of the attribute id_Gruppe
     */
   public String getID_Gruppe() {
      return _details.getID_Gruppe();
   }

   /**
     * Sets the value of id_Listenkombination in the entity Restsitzverteilung
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
     * Gets the value of id_Listenkombination in the entity Restsitzverteilung
     *
     * @return value of the attribute id_Listenkombination
     */
   public String getID_Listenkombination() {
      return _details.getID_Listenkombination();
   }

   /**
     * Sets the value of verteilung in the entity Restsitzverteilung
     *
     * @param verteilung new value of the attribute verteilung
     */
   public void setVerteilung(int verteilung) {
      _details.setVerteilung(verteilung);
   }

   /**
     * Gets the value of verteilung in the entity Restsitzverteilung
     *
     * @return value of the attribute verteilung
     */
   public int getVerteilung() {
      return _details.getVerteilung();
   }

   /**
     * Sets the value of sitze in the entity Restsitzverteilung
     *
     * @param sitze new value of the attribute sitze
     */
   public void setSitze(int sitze) {
      _details.setSitze(sitze);
   }

   /**
     * Gets the value of sitze in the entity Restsitzverteilung
     *
     * @return value of the attribute sitze
     */
   public int getSitze() {
      return _details.getSitze();
   }

   /**
     * Sets the value of sitzeRest in the entity Restsitzverteilung
     *
     * @param sitzeRest new value of the attribute sitzeRest
     */
   public void setSitzeRest(int sitzeRest) {
      _details.setSitzeRest(sitzeRest);
   }

   /**
     * Gets the value of sitzeRest in the entity Restsitzverteilung
     *
     * @return value of the attribute sitzeRest
     */
   public int getSitzeRest() {
      return _details.getSitzeRest();
   }

   /**
     * Sets the value of sitzeGesamtZuVerteilen in the entity Restsitzverteilung
     *
     * @param sitzeGesamtZuVerteilen new value of the attribute sitzeGesamtZuVerteilen
     */
   public void setSitzeGesamtZuVerteilen(int sitzeGesamtZuVerteilen) {
      _details.setSitzeGesamtZuVerteilen(sitzeGesamtZuVerteilen);
   }

   /**
     * Gets the value of sitzeGesamtZuVerteilen in the entity Restsitzverteilung
     *
     * @return value of the attribute sitzeGesamtZuVerteilen
     */
   public int getSitzeGesamtZuVerteilen() {
      return _details.getSitzeGesamtZuVerteilen();
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
     * Returns the set of entities of the type {@link DHondtQuotient}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link DHondtQuotient}
     * @throws EJBException: an error occurred
     */
   public Collection<DHondtQuotient> getDHondtQuotientCol() throws EJBException {
      DHondtQuotientHome dHondtQuotientHome = DHondtQuotientHome.HomeFinder.findHome(this);
      try {
         return dHondtQuotientHome.findAllByRestsitzverteilung(_details.getID_Restsitzverteilung());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link DHondtQuotient}.
     *
     * @param dHondtQuotient DHondtQuotient-object
     */
   public void addDHondtQuotient(DHondtQuotient dHondtQuotient) {
      dHondtQuotient.setID_Restsitzverteilung(_details.getID_Restsitzverteilung());
   }

   /**
     * Adds the object to the set of entities of the type {@link DHondtQuotient}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllDHondtQuotientCol(Collection<DHondtQuotient> col) {
      for (DHondtQuotient dHondtQuotient : col) {
         addDHondtQuotient(dHondtQuotient);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link DHondtQuotient}.
     *
     * @param dHondtQuotient DHondtQuotient-EJBObject, which is removed from the set.
     */
   public void removeDHondtQuotient(DHondtQuotient dHondtQuotient) {
      dHondtQuotient.setID_Restsitzverteilung(null);
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
         if (_details.getID_Restsitzverteilung() != null) {
            string += "id_Restsitzverteilung = " + _details.getID_Restsitzverteilung(); //$NON-NLS-1$
         }
         if (_details.getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + _details.getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (_details.getID_Liste() != null) {
            string +=  ", id_Liste = " + _details.getID_Liste(); //$NON-NLS-1$
         }
         if (_details.getID_Gruppe() != null) {
            string +=  ", id_Gruppe = " + _details.getID_Gruppe(); //$NON-NLS-1$
         }
         if (_details.getID_Listenkombination() != null) {
            string +=  ", id_Listenkombination = " + _details.getID_Listenkombination(); //$NON-NLS-1$
         }
         string +=  ", verteilung = " + _details.getVerteilung(); //$NON-NLS-1$
         string +=  ", sitze = " + _details.getSitze(); //$NON-NLS-1$
         string +=  ", sitzeRest = " + _details.getSitzeRest(); //$NON-NLS-1$
         string +=  ", sitzeGesamtZuVerteilen = " + _details.getSitzeGesamtZuVerteilen(); //$NON-NLS-1$
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
