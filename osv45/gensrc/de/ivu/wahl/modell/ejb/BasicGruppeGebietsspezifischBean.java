/*
 * BasicGruppeGebietsspezifischBean
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
import de.ivu.wahl.modell.GruppeGebietsspezifischModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity GruppeGebietsspezifisch as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicGruppeGebietsspezifischBean extends BMPBeanBase implements EntityBean, GruppeGebietsspezifischModel {
   private static final Category LOGGER = Log4J.configure(BasicGruppeGebietsspezifischBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicGruppeGebietsspezifischBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected GruppeGebietsspezifischModel _details = null;

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
   public String ejbCreate(GruppeGebietsspezifischModel details) throws CreateException, EJBException {
      GruppeGebietsspezifischModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_GruppeGebietsspezifisch();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_GruppeGebietsspezifisch key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_GruppeGebietsspezifisch) throws CreateException, EJBException {
      _details = (GruppeGebietsspezifischModel)createModel(id_GruppeGebietsspezifisch);
      create(_details);
      return id_GruppeGebietsspezifisch;
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
         return GruppeGebietsspezifischDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Liste ID of the objects to be searched
     * @return  {@link Collection} of the found GruppeGebietsspezifisch-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByListe(String id_Liste) throws IVUFinderException {
      try {
         return GruppeGebietsspezifischDBA.retrieveIDsByID_Liste(id_Liste);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_UebergeordneteGG ID of the objects to be searched
     * @return  {@link Collection} of the found GruppeGebietsspezifisch-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByUebergeordneteGG(String id_UebergeordneteGG) throws IVUFinderException {
      try {
         return GruppeGebietsspezifischDBA.retrieveIDsByID_UebergeordneteGG(id_UebergeordneteGG);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gebiet ID of the objects to be searched
     * @return  {@link Collection} of the found GruppeGebietsspezifisch-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByGebiet(String id_Gebiet) throws IVUFinderException {
      try {
         return GruppeGebietsspezifischDBA.retrieveIDsByID_Gebiet(id_Gebiet);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gruppe ID of the objects to be searched
     * @return  {@link Collection} of the found GruppeGebietsspezifisch-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByGruppe(String id_Gruppe) throws IVUFinderException {
      try {
         return GruppeGebietsspezifischDBA.retrieveIDsByID_Gruppe(id_Gruppe);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link GruppeGebietsspezifisch}, filtered by position.
     *
     * @param position searching condition
     * @return  {@link Collection} of primary keys of the entities GruppeGebietsspezifisch
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByPosition(int position) throws IVUFinderException {
      try {
         return GruppeGebietsspezifischDBA.retrieveIDsByPosition(position);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link GruppeGebietsspezifisch}, filtered by listeZugelassen.
     *
     * @param listeZugelassen searching condition
     * @return  {@link Collection} of primary keys of the entities GruppeGebietsspezifisch
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByListeZugelassen(boolean listeZugelassen) throws IVUFinderException {
      try {
         return GruppeGebietsspezifischDBA.retrieveIDsByListeZugelassen(listeZugelassen);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_GruppeGebietsspezifisch key element of the type {@link String}
     */
   public void ejbPostCreate(String id_GruppeGebietsspezifisch) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(GruppeGebietsspezifischModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public GruppeGebietsspezifischModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(GruppeGebietsspezifischModel details) {
      if (details instanceof GruppeGebietsspezifischModelImpl && _details instanceof GruppeGebietsspezifischModelImpl) {
         ((GruppeGebietsspezifischModelImpl)_details).copyFrom((GruppeGebietsspezifischModelImpl)details);
         checkRelations();
      } else {
         setID_UebergeordneteGG(details.getID_UebergeordneteGG());
         setID_Gruppe(details.getID_Gruppe());
         setID_Gebiet(details.getID_Gebiet());
         setID_Liste(details.getID_Liste());
         setPosition(details.getPosition());
         setListeZugelassen(details.isListeZugelassen());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (GruppeGebietsspezifischModel)details;
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
      if (null == _details.getID_UebergeordneteGG()) {
         _uebergeordneteGG = null;
         _relchk_UebergeordneteGG = true;
      } else {
         _relchk_UebergeordneteGG = false;
      }
      if (null == _details.getID_Gebiet()) {
         _gebiet = null;
         _relchk_Gebiet = true;
      } else {
         _relchk_Gebiet = false;
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
      _liste = null;
      _uebergeordneteGG = null;
      _gebiet = null;
      _gruppe = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public GruppeGebietsspezifischModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_GruppeGebietsspezifisch in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute id_GruppeGebietsspezifisch
     */
   public String getID_GruppeGebietsspezifisch() {
      return _details.getID_GruppeGebietsspezifisch();
   }

   /**
     * Sets the value of id_UebergeordneteGG in the entity GruppeGebietsspezifisch
     *
     * @param id_UebergeordneteGG new value of the attribute id_UebergeordneteGG
     */
   public void setID_UebergeordneteGG(String id_UebergeordneteGG) {
      if (null == id_UebergeordneteGG) {
         _uebergeordneteGG = null;
         _relchk_UebergeordneteGG = true;
      } else {
         String old = _details.getID_UebergeordneteGG();
         if (old == null || !old.equals(id_UebergeordneteGG)) {
            _relchk_UebergeordneteGG = false;
         }
      }
      _details.setID_UebergeordneteGG(id_UebergeordneteGG);
   }

   /**
     * Gets the value of id_UebergeordneteGG in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute id_UebergeordneteGG
     */
   public String getID_UebergeordneteGG() {
      return _details.getID_UebergeordneteGG();
   }

   /**
     * Sets the value of id_Gruppe in the entity GruppeGebietsspezifisch
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
     * Gets the value of id_Gruppe in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute id_Gruppe
     */
   public String getID_Gruppe() {
      return _details.getID_Gruppe();
   }

   /**
     * Sets the value of id_Gebiet in the entity GruppeGebietsspezifisch
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
     * Gets the value of id_Gebiet in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute id_Gebiet
     */
   public String getID_Gebiet() {
      return _details.getID_Gebiet();
   }

   /**
     * Sets the value of id_Liste in the entity GruppeGebietsspezifisch
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
     * Gets the value of id_Liste in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute id_Liste
     */
   public String getID_Liste() {
      return _details.getID_Liste();
   }

   /**
     * Sets the value of position in the entity GruppeGebietsspezifisch
     *
     * @param position new value of the attribute position
     */
   public void setPosition(int position) {
      _details.setPosition(position);
   }

   /**
     * Gets the value of position in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute position
     */
   public int getPosition() {
      return _details.getPosition();
   }

   /**
     * Sets the value of listeZugelassen in the entity GruppeGebietsspezifisch
     *
     * @param listeZugelassen new value of the attribute listeZugelassen
     */
   public void setListeZugelassen(boolean listeZugelassen) {
      _details.setListeZugelassen(listeZugelassen);
   }

   /**
     * Gets the value of listeZugelassen in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute listeZugelassen
     */
   public boolean isListeZugelassen() {
      return _details.isListeZugelassen();
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
     * Relation zu UebergeordneteGG
     */
   protected GruppeGebietsspezifisch _uebergeordneteGG;

   /**
     * Flag for the validity of the relation UebergeordneteGG
     */
   protected boolean _relchk_UebergeordneteGG = false;

   /**
     * Navigation to the associated entity of the type {@link GruppeGebietsspezifisch}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public GruppeGebietsspezifisch getUebergeordneteGG() throws EJBException {
      if (!_relchk_UebergeordneteGG) {
         if (null == _details.getID_UebergeordneteGG()) {
            _uebergeordneteGG = null;
         } else if (null == _uebergeordneteGG || !_uebergeordneteGG.getPrimaryKey().equals(_details.getID_UebergeordneteGG())) {
            try {
               GruppeGebietsspezifischHome home = GruppeGebietsspezifischHome.HomeFinder.findHome(this);
               _uebergeordneteGG = home.findByPrimaryKey(_details.getID_UebergeordneteGG());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find UebergeordneteGG", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table GruppeGebietsspezifisch", fe); //$NON-NLS-1$
            }
         }
         _relchk_UebergeordneteGG = true;
      }
      return _uebergeordneteGG;
   }

   /**
     * Setting of the associated entity of the type {@link GruppeGebietsspezifisch}
     *
     * @param uebergeordneteGG the corresponding EJBObject
     */
   public void setUebergeordneteGG(GruppeGebietsspezifisch uebergeordneteGG) {
      _uebergeordneteGG = uebergeordneteGG;
      _details.setID_UebergeordneteGG(uebergeordneteGG == null ? null : (String)uebergeordneteGG.getPrimaryKey());
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
     * Returns the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link GruppeGebietsspezifisch}
     * @throws EJBException: an error occurred
     */
   public Collection<GruppeGebietsspezifisch> getUebergeordneteGGCol() throws EJBException {
      GruppeGebietsspezifischHome gruppeGebietsspezifischHome = GruppeGebietsspezifischHome.HomeFinder.findHome(this);
      try {
         return gruppeGebietsspezifischHome.findAllByUebergeordneteGG(_details.getID_GruppeGebietsspezifisch());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @param uebergeordneteGG GruppeGebietsspezifisch-object
     */
   public void addUebergeordneteGG(GruppeGebietsspezifisch uebergeordneteGG) {
      uebergeordneteGG.setID_UebergeordneteGG(_details.getID_GruppeGebietsspezifisch());
   }

   /**
     * Adds the object to the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllUebergeordneteGGCol(Collection<GruppeGebietsspezifisch> col) {
      for (GruppeGebietsspezifisch uebergeordneteGG : col) {
         addUebergeordneteGG(uebergeordneteGG);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @param uebergeordneteGG GruppeGebietsspezifisch-EJBObject, which is removed from the set.
     */
   public void removeUebergeordneteGG(GruppeGebietsspezifisch uebergeordneteGG) {
      uebergeordneteGG.setID_UebergeordneteGG(null);
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
         return stimmergebnisHome.findAllByGruppeGebietsspezifisch(_details.getID_GruppeGebietsspezifisch());
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
      stimmergebnis.setID_GruppeGebietsspezifisch(_details.getID_GruppeGebietsspezifisch());
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
      stimmergebnis.setID_GruppeGebietsspezifisch(null);
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
         if (_details.getID_GruppeGebietsspezifisch() != null) {
            string += "id_GruppeGebietsspezifisch = " + _details.getID_GruppeGebietsspezifisch(); //$NON-NLS-1$
         }
         if (_details.getID_UebergeordneteGG() != null) {
            string +=  ", id_UebergeordneteGG = " + _details.getID_UebergeordneteGG(); //$NON-NLS-1$
         }
         if (_details.getID_Gruppe() != null) {
            string +=  ", id_Gruppe = " + _details.getID_Gruppe(); //$NON-NLS-1$
         }
         if (_details.getID_Gebiet() != null) {
            string +=  ", id_Gebiet = " + _details.getID_Gebiet(); //$NON-NLS-1$
         }
         if (_details.getID_Liste() != null) {
            string +=  ", id_Liste = " + _details.getID_Liste(); //$NON-NLS-1$
         }
         string +=  ", position = " + _details.getPosition(); //$NON-NLS-1$
         string +=  ", listeZugelassen = " + _details.isListeZugelassen(); //$NON-NLS-1$
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
