/*
 * AlternativeBean
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
import de.ivu.wahl.modell.AlternativeModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Alternative as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class AlternativeBean extends BMPBeanBase implements EntityBean, AlternativeModel {
   private static final Category LOGGER = Log4J.configure(AlternativeBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(AlternativeBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected transient boolean _readOnly;

   @Override
   protected void setConditionalReadOnly(final boolean readOnly) {
      _readOnly = readOnly;
   }

   protected AlternativeModel _details = null;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(AlternativeModel details) throws CreateException, EJBException {
      AlternativeModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Alternative();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Alternative key element of the type {@link String}
     * @param id_Konflikt key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Alternative, String id_Konflikt) throws CreateException, EJBException {
      _details = (AlternativeModel)createModel(id_Alternative);
      _details.setID_Konflikt(id_Konflikt);
      create(_details);
      return id_Alternative;
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
         return AlternativeDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Personendaten ID of the objects to be searched
     * @return  {@link Collection} of the found Alternative-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByPersonendaten(String id_Personendaten) throws IVUFinderException {
      try {
         return AlternativeDBA.retrieveIDsByID_Personendaten(id_Personendaten);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Konflikt ID of the objects to be searched
     * @return  {@link Collection} of the found Alternative-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByKonflikt(String id_Konflikt) throws IVUFinderException {
      try {
         return AlternativeDBA.retrieveIDsByID_Konflikt(id_Konflikt);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Listenkombination ID of the objects to be searched
     * @return  {@link Collection} of the found Alternative-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByListenkombination(String id_Listenkombination) throws IVUFinderException {
      try {
         return AlternativeDBA.retrieveIDsByID_Listenkombination(id_Listenkombination);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gruppe ID of the objects to be searched
     * @return  {@link Collection} of the found Alternative-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByGruppe(String id_Gruppe) throws IVUFinderException {
      try {
         return AlternativeDBA.retrieveIDsByID_Gruppe(id_Gruppe);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Liste ID of the objects to be searched
     * @return  {@link Collection} of the found Alternative-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByListe(String id_Liste) throws IVUFinderException {
      try {
         return AlternativeDBA.retrieveIDsByID_Liste(id_Liste);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Alternative}, filtered by nummer.
     *
     * @param nummer searching condition
     * @return  {@link Collection} of primary keys of the entities Alternative
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByNummer(int nummer) throws IVUFinderException {
      try {
         return AlternativeDBA.retrieveIDsByNummer(nummer);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Alternative key element of the type {@link String}
     * @param id_Konflikt key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Alternative, String id_Konflikt) {
      // No functionality required
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(AlternativeModel details) {
      // No functionality required
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public AlternativeModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(AlternativeModel details) {
      if (_readOnly) { throw new EJBException("AlternativeBean is in read-only mode");} //$NON-NLS-1$
      if (details instanceof AlternativeModelImpl && _details instanceof AlternativeModelImpl) {
         ((AlternativeModelImpl)_details).copyFrom((AlternativeModelImpl)details);
         checkRelations();
      } else {
         setID_Konflikt(details.getID_Konflikt());
         setID_Listenkombination(details.getID_Listenkombination());
         setID_Gruppe(details.getID_Gruppe());
         setID_Liste(details.getID_Liste());
         setID_Personendaten(details.getID_Personendaten());
         setNummer(details.getNummer());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (AlternativeModel)details;
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
      if (null == _details.getID_Personendaten()) {
         _personendaten = null;
         _relchk_Personendaten = true;
      } else {
         _relchk_Personendaten = false;
      }
      if (null == _details.getID_Konflikt()) {
         _konflikt = null;
         _relchk_Konflikt = true;
      } else {
         _relchk_Konflikt = false;
      }
      if (null == _details.getID_Listenkombination()) {
         _listenkombination = null;
         _relchk_Listenkombination = true;
      } else {
         _relchk_Listenkombination = false;
      }
      if (null == _details.getID_Gruppe()) {
         _gruppe = null;
         _relchk_Gruppe = true;
      } else {
         _relchk_Gruppe = false;
      }
      if (null == _details.getID_Liste()) {
         _liste = null;
         _relchk_Liste = true;
      } else {
         _relchk_Liste = false;
      }
   }

   @Override
   protected void resetRelations() {
      _personendaten = null;
      _konflikt = null;
      _listenkombination = null;
      _gruppe = null;
      _liste = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public AlternativeModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Alternative in the entity Alternative
     *
     * @return value of the attribute id_Alternative
     */
   public String getID_Alternative() {
      return _details.getID_Alternative();
   }

   /**
     * Sets the value of id_Konflikt in the entity Alternative
     *
     * @param id_Konflikt new value of the attribute id_Konflikt
     */
   public void setID_Konflikt(String id_Konflikt) {
      if (_readOnly) { throw new EJBException("AlternativeBean is in read-only mode");} //$NON-NLS-1$
      if (null == id_Konflikt) {
         _konflikt = null;
         _relchk_Konflikt = true;
      } else {
         String old = _details.getID_Konflikt();
         if (old == null || !old.equals(id_Konflikt)) {
            _relchk_Konflikt = false;
         }
      }
      _details.setID_Konflikt(id_Konflikt);
   }

   /**
     * Gets the value of id_Konflikt in the entity Alternative
     *
     * @return value of the attribute id_Konflikt
     */
   public String getID_Konflikt() {
      return _details.getID_Konflikt();
   }

   /**
     * Sets the value of id_Listenkombination in the entity Alternative
     *
     * @param id_Listenkombination new value of the attribute id_Listenkombination
     */
   public void setID_Listenkombination(String id_Listenkombination) {
      if (_readOnly) { throw new EJBException("AlternativeBean is in read-only mode");} //$NON-NLS-1$
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
     * Gets the value of id_Listenkombination in the entity Alternative
     *
     * @return value of the attribute id_Listenkombination
     */
   public String getID_Listenkombination() {
      return _details.getID_Listenkombination();
   }

   /**
     * Sets the value of id_Gruppe in the entity Alternative
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   public void setID_Gruppe(String id_Gruppe) {
      if (_readOnly) { throw new EJBException("AlternativeBean is in read-only mode");} //$NON-NLS-1$
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
     * Gets the value of id_Gruppe in the entity Alternative
     *
     * @return value of the attribute id_Gruppe
     */
   public String getID_Gruppe() {
      return _details.getID_Gruppe();
   }

   /**
     * Sets the value of id_Liste in the entity Alternative
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   public void setID_Liste(String id_Liste) {
      if (_readOnly) { throw new EJBException("AlternativeBean is in read-only mode");} //$NON-NLS-1$
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
     * Gets the value of id_Liste in the entity Alternative
     *
     * @return value of the attribute id_Liste
     */
   public String getID_Liste() {
      return _details.getID_Liste();
   }

   /**
     * Sets the value of id_Personendaten in the entity Alternative
     *
     * @param id_Personendaten new value of the attribute id_Personendaten
     */
   public void setID_Personendaten(String id_Personendaten) {
      if (_readOnly) { throw new EJBException("AlternativeBean is in read-only mode");} //$NON-NLS-1$
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
     * Gets the value of id_Personendaten in the entity Alternative
     *
     * @return value of the attribute id_Personendaten
     */
   public String getID_Personendaten() {
      return _details.getID_Personendaten();
   }

   /**
     * Sets the value of nummer in the entity Alternative
     *
     * @param nummer new value of the attribute nummer
     */
   public void setNummer(int nummer) {
      if (_readOnly) { throw new EJBException("AlternativeBean is in read-only mode");} //$NON-NLS-1$
      _details.setNummer(nummer);
   }

   /**
     * Gets the value of nummer in the entity Alternative
     *
     * @return value of the attribute nummer
     */
   public int getNummer() {
      return _details.getNummer();
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
      if (_readOnly) { throw new EJBException("AlternativeBean is in read-only mode");} //$NON-NLS-1$
      _personendaten = personendaten;
      _details.setID_Personendaten(personendaten == null ? null : (String)personendaten.getPrimaryKey());
   }

   /**
     * Relation zu Konflikt
     */
   protected Konflikt _konflikt;

   /**
     * Flag for the validity of the relation Konflikt
     */
   protected boolean _relchk_Konflikt = false;

   /**
     * Navigation to the associated entity of the type {@link Konflikt}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Konflikt getKonflikt() throws EJBException {
      if (!_relchk_Konflikt) {
         if (null == _details.getID_Konflikt()) {
            _konflikt = null;
         } else if (null == _konflikt || !_konflikt.getPrimaryKey().equals(_details.getID_Konflikt())) {
            try {
               KonfliktHome home = KonfliktHome.HomeFinder.findHome(this);
               _konflikt = home.findByPrimaryKey(_details.getID_Konflikt());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find Konflikt", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Konflikt", fe); //$NON-NLS-1$
            }
         }
         _relchk_Konflikt = true;
      }
      return _konflikt;
   }

   /**
     * Setting of the associated entity of the type {@link Konflikt}
     *
     * @param konflikt the corresponding EJBObject
     */
   public void setKonflikt(Konflikt konflikt) {
      if (_readOnly) { throw new EJBException("AlternativeBean is in read-only mode");} //$NON-NLS-1$
      _konflikt = konflikt;
      _details.setID_Konflikt(konflikt == null ? null : (String)konflikt.getPrimaryKey());
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
      if (_readOnly) { throw new EJBException("AlternativeBean is in read-only mode");} //$NON-NLS-1$
      _listenkombination = listenkombination;
      _details.setID_Listenkombination(listenkombination == null ? null : (String)listenkombination.getPrimaryKey());
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
      if (_readOnly) { throw new EJBException("AlternativeBean is in read-only mode");} //$NON-NLS-1$
      _gruppe = gruppe;
      _details.setID_Gruppe(gruppe == null ? null : (String)gruppe.getPrimaryKey());
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
      if (_readOnly) { throw new EJBException("AlternativeBean is in read-only mode");} //$NON-NLS-1$
      _liste = liste;
      _details.setID_Liste(liste == null ? null : (String)liste.getPrimaryKey());
   }

   /**
     * Returns the set of entities of the type {@link Konflikt}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Konflikt}
     * @throws EJBException: an error occurred
     */
   public Collection<Konflikt> getKonfliktCol() throws EJBException {
      KonfliktHome konfliktHome = KonfliktHome.HomeFinder.findHome(this);
      try {
         return konfliktHome.findAllByLosAlternative(_details.getID_Alternative());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Konflikt}.
     *
     * @param konflikt Konflikt-object
     */
   public void addKonflikt(Konflikt konflikt) {
      konflikt.setID_LosAlternative(_details.getID_Alternative());
   }

   /**
     * Adds the object to the set of entities of the type {@link Konflikt}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllKonfliktCol(Collection<Konflikt> col) {
      for (Konflikt konflikt : col) {
         addKonflikt(konflikt);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Konflikt}.
     *
     * @param konflikt Konflikt-EJBObject, which is removed from the set.
     */
   public void removeKonflikt(Konflikt konflikt) {
      konflikt.setID_LosAlternative(null);
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
         if (_details.getID_Alternative() != null) {
            string += "id_Alternative = " + _details.getID_Alternative(); //$NON-NLS-1$
         }
         if (_details.getID_Konflikt() != null) {
            string +=  ", id_Konflikt = " + _details.getID_Konflikt(); //$NON-NLS-1$
         }
         if (_details.getID_Listenkombination() != null) {
            string +=  ", id_Listenkombination = " + _details.getID_Listenkombination(); //$NON-NLS-1$
         }
         if (_details.getID_Gruppe() != null) {
            string +=  ", id_Gruppe = " + _details.getID_Gruppe(); //$NON-NLS-1$
         }
         if (_details.getID_Liste() != null) {
            string +=  ", id_Liste = " + _details.getID_Liste(); //$NON-NLS-1$
         }
         if (_details.getID_Personendaten() != null) {
            string +=  ", id_Personendaten = " + _details.getID_Personendaten(); //$NON-NLS-1$
         }
         string +=  ", nummer = " + _details.getNummer(); //$NON-NLS-1$
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
