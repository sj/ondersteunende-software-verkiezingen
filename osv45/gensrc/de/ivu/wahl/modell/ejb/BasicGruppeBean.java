/*
 * BasicGruppeBean
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
import de.ivu.wahl.modell.GruppeModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Gruppe as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicGruppeBean extends BMPBeanBase implements EntityBean, GruppeModel {
   private static final Category LOGGER = Log4J.configure(BasicGruppeBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicGruppeBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected GruppeModel _details = null;

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
   public String ejbCreate(GruppeModel details) throws CreateException, EJBException {
      GruppeModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Gruppe();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Gruppe key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Gruppe) throws CreateException, EJBException {
      _details = (GruppeModel)createModel(id_Gruppe);
      _details.setNameLang(""); //$NON-NLS-1$
      _details.setNameKurz(""); //$NON-NLS-1$
      _details.setFarbe(""); //$NON-NLS-1$
      create(_details);
      return id_Gruppe;
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
         return GruppeDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Listenkombination ID of the objects to be searched
     * @return  {@link Collection} of the found Gruppe-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByListenkombination(String id_Listenkombination) throws IVUFinderException {
      try {
         return GruppeDBA.retrieveIDsByID_Listenkombination(id_Listenkombination);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Wahl ID of the objects to be searched
     * @return  {@link Collection} of the found Gruppe-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByWahl(String id_Wahl) throws IVUFinderException {
      try {
         return GruppeDBA.retrieveIDsByID_Wahl(id_Wahl);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by schluessel.
     *
     * @param schluessel searching condition
     * @return  {@link Collection} of primary keys of the entities Gruppe
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllBySchluessel(int schluessel) throws IVUFinderException {
      try {
         return GruppeDBA.retrieveIDsBySchluessel(schluessel);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by gruppenart.
     *
     * @param gruppenart searching condition
     * @return  {@link Collection} of primary keys of the entities Gruppe
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByGruppenart(int gruppenart) throws IVUFinderException {
      try {
         return GruppeDBA.retrieveIDsByGruppenart(gruppenart);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by nameLang.
     *
     * @param nameLang searching condition
     * @return  {@link Collection} of primary keys of the entities Gruppe
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByNameLang(String nameLang) throws IVUFinderException {
      try {
         return GruppeDBA.retrieveIDsByNameLang(nameLang);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by nameLang.
     *
     * @param nameLang searching condition
     * @return  {@link Collection} of primary keys of the entities Gruppe
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeNameLang(String nameLang) throws IVUFinderException {
      try {
         return GruppeDBA.retrieveIDsLikeNameLang(nameLang);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by nameKurz.
     *
     * @param nameKurz searching condition
     * @return  {@link Collection} of primary keys of the entities Gruppe
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByNameKurz(String nameKurz) throws IVUFinderException {
      try {
         return GruppeDBA.retrieveIDsByNameKurz(nameKurz);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by nameKurz.
     *
     * @param nameKurz searching condition
     * @return  {@link Collection} of primary keys of the entities Gruppe
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeNameKurz(String nameKurz) throws IVUFinderException {
      try {
         return GruppeDBA.retrieveIDsLikeNameKurz(nameKurz);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by kautionGestellt.
     *
     * @param kautionGestellt searching condition
     * @return  {@link Collection} of primary keys of the entities Gruppe
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByKautionGestellt(boolean kautionGestellt) throws IVUFinderException {
      try {
         return GruppeDBA.retrieveIDsByKautionGestellt(kautionGestellt);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by farbe.
     *
     * @param farbe searching condition
     * @return  {@link Collection} of primary keys of the entities Gruppe
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByFarbe(String farbe) throws IVUFinderException {
      try {
         return GruppeDBA.retrieveIDsByFarbe(farbe);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Gruppe}, filtered by farbe.
     *
     * @param farbe searching condition
     * @return  {@link Collection} of primary keys of the entities Gruppe
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeFarbe(String farbe) throws IVUFinderException {
      try {
         return GruppeDBA.retrieveIDsLikeFarbe(farbe);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Gruppe key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Gruppe) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(GruppeModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public GruppeModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(GruppeModel details) {
      if (details instanceof GruppeModelImpl && _details instanceof GruppeModelImpl) {
         ((GruppeModelImpl)_details).copyFrom((GruppeModelImpl)details);
         checkRelations();
      } else {
         setID_Wahl(details.getID_Wahl());
         setID_Listenkombination(details.getID_Listenkombination());
         setSchluessel(details.getSchluessel());
         setGruppenart(details.getGruppenart());
         setNameLang(details.getNameLang());
         setNameKurz(details.getNameKurz());
         setKautionGestellt(details.isKautionGestellt());
         setFarbe(details.getFarbe());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (GruppeModel)details;
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
      if (null == _details.getID_Wahl()) {
         _wahl = null;
         _relchk_Wahl = true;
      } else {
         _relchk_Wahl = false;
      }
   }

   @Override
   protected void resetRelations() {
      _listenkombination = null;
      _wahl = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public GruppeModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Gruppe in the entity Gruppe
     *
     * @return value of the attribute id_Gruppe
     */
   public String getID_Gruppe() {
      return _details.getID_Gruppe();
   }

   /**
     * Sets the value of id_Wahl in the entity Gruppe
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
     * Gets the value of id_Wahl in the entity Gruppe
     *
     * @return value of the attribute id_Wahl
     */
   public String getID_Wahl() {
      return _details.getID_Wahl();
   }

   /**
     * Sets the value of id_Listenkombination in the entity Gruppe
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
     * Gets the value of id_Listenkombination in the entity Gruppe
     *
     * @return value of the attribute id_Listenkombination
     */
   public String getID_Listenkombination() {
      return _details.getID_Listenkombination();
   }

   /**
     * Sets the value of schluessel in the entity Gruppe
     *
     * @param schluessel new value of the attribute schluessel
     */
   public void setSchluessel(int schluessel) {
      _details.setSchluessel(schluessel);
   }

   /**
     * Gets the value of schluessel in the entity Gruppe
     *
     * @return value of the attribute schluessel
     */
   public int getSchluessel() {
      return _details.getSchluessel();
   }

   /**
     * Sets the value of gruppenart in the entity Gruppe
     *
     * @param gruppenart new value of the attribute gruppenart
     */
   public void setGruppenart(int gruppenart) {
      _details.setGruppenart(gruppenart);
   }

   /**
     * Gets the value of gruppenart in the entity Gruppe
     *
     * @return value of the attribute gruppenart
     */
   public int getGruppenart() {
      return _details.getGruppenart();
   }

   /**
     * Sets the value of nameLang in the entity Gruppe
     *
     * @param nameLang new value of the attribute nameLang
     */
   public void setNameLang(String nameLang) {
      _details.setNameLang(nameLang);
   }

   /**
     * Gets the value of nameLang in the entity Gruppe
     *
     * @return value of the attribute nameLang
     */
   public String getNameLang() {
      return _details.getNameLang();
   }

   /**
     * Sets the value of nameKurz in the entity Gruppe
     *
     * @param nameKurz new value of the attribute nameKurz
     */
   public void setNameKurz(String nameKurz) {
      _details.setNameKurz(nameKurz);
   }

   /**
     * Gets the value of nameKurz in the entity Gruppe
     *
     * @return value of the attribute nameKurz
     */
   public String getNameKurz() {
      return _details.getNameKurz();
   }

   /**
     * Sets the value of kautionGestellt in the entity Gruppe
     *
     * @param kautionGestellt new value of the attribute kautionGestellt
     */
   public void setKautionGestellt(boolean kautionGestellt) {
      _details.setKautionGestellt(kautionGestellt);
   }

   /**
     * Gets the value of kautionGestellt in the entity Gruppe
     *
     * @return value of the attribute kautionGestellt
     */
   public boolean isKautionGestellt() {
      return _details.isKautionGestellt();
   }

   /**
     * Sets the value of farbe in the entity Gruppe
     *
     * @param farbe new value of the attribute farbe
     */
   public void setFarbe(String farbe) {
      _details.setFarbe(farbe);
   }

   /**
     * Gets the value of farbe in the entity Gruppe
     *
     * @return value of the attribute farbe
     */
   public String getFarbe() {
      return _details.getFarbe();
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
     * Returns the set of entities of the type {@link Alternative}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Alternative}
     * @throws EJBException: an error occurred
     */
   public Collection<Alternative> getAlternativeCol() throws EJBException {
      AlternativeHome alternativeHome = AlternativeHome.HomeFinder.findHome(this);
      try {
         return alternativeHome.findAllByGruppe(_details.getID_Gruppe());
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
      alternative.setID_Gruppe(_details.getID_Gruppe());
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
      alternative.setID_Gruppe(null);
   }

   /**
     * Returns the set of entities of the type {@link Besonderheit}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Besonderheit}
     * @throws EJBException: an error occurred
     */
   public Collection<Besonderheit> getBesonderheitCol() throws EJBException {
      BesonderheitHome besonderheitHome = BesonderheitHome.HomeFinder.findHome(this);
      try {
         return besonderheitHome.findAllByGruppe(_details.getID_Gruppe());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Besonderheit}.
     *
     * @param besonderheit Besonderheit-object
     */
   public void addBesonderheit(Besonderheit besonderheit) {
      besonderheit.setID_Gruppe(_details.getID_Gruppe());
   }

   /**
     * Adds the object to the set of entities of the type {@link Besonderheit}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllBesonderheitCol(Collection<Besonderheit> col) {
      for (Besonderheit besonderheit : col) {
         addBesonderheit(besonderheit);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Besonderheit}.
     *
     * @param besonderheit Besonderheit-EJBObject, which is removed from the set.
     */
   public void removeBesonderheit(Besonderheit besonderheit) {
      besonderheit.setID_Gruppe(null);
   }

   /**
     * Returns the set of entities of the type {@link FiktiveSitzverteilung}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link FiktiveSitzverteilung}
     * @throws EJBException: an error occurred
     */
   public Collection<FiktiveSitzverteilung> getFiktiveSitzverteilungCol() throws EJBException {
      FiktiveSitzverteilungHome fiktiveSitzverteilungHome = FiktiveSitzverteilungHome.HomeFinder.findHome(this);
      try {
         return fiktiveSitzverteilungHome.findAllByGruppe(_details.getID_Gruppe());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link FiktiveSitzverteilung}.
     *
     * @param fiktiveSitzverteilung FiktiveSitzverteilung-object
     */
   public void addFiktiveSitzverteilung(FiktiveSitzverteilung fiktiveSitzverteilung) {
      fiktiveSitzverteilung.setID_Gruppe(_details.getID_Gruppe());
   }

   /**
     * Adds the object to the set of entities of the type {@link FiktiveSitzverteilung}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllFiktiveSitzverteilungCol(Collection<FiktiveSitzverteilung> col) {
      for (FiktiveSitzverteilung fiktiveSitzverteilung : col) {
         addFiktiveSitzverteilung(fiktiveSitzverteilung);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link FiktiveSitzverteilung}.
     *
     * @param fiktiveSitzverteilung FiktiveSitzverteilung-EJBObject, which is removed from the set.
     */
   public void removeFiktiveSitzverteilung(FiktiveSitzverteilung fiktiveSitzverteilung) {
      fiktiveSitzverteilung.setID_Gruppe(null);
   }

   /**
     * Returns the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link GruppeGebietsspezifisch}
     * @throws EJBException: an error occurred
     */
   public Collection<GruppeGebietsspezifisch> getGruppeGebietsspezifischCol() throws EJBException {
      GruppeGebietsspezifischHome gruppeGebietsspezifischHome = GruppeGebietsspezifischHome.HomeFinder.findHome(this);
      try {
         return gruppeGebietsspezifischHome.findAllByGruppe(_details.getID_Gruppe());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @param gruppeGebietsspezifisch GruppeGebietsspezifisch-object
     */
   public void addGruppeGebietsspezifisch(GruppeGebietsspezifisch gruppeGebietsspezifisch) {
      gruppeGebietsspezifisch.setID_Gruppe(_details.getID_Gruppe());
   }

   /**
     * Adds the object to the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllGruppeGebietsspezifischCol(Collection<GruppeGebietsspezifisch> col) {
      for (GruppeGebietsspezifisch gruppeGebietsspezifisch : col) {
         addGruppeGebietsspezifisch(gruppeGebietsspezifisch);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link GruppeGebietsspezifisch}.
     *
     * @param gruppeGebietsspezifisch GruppeGebietsspezifisch-EJBObject, which is removed from the set.
     */
   public void removeGruppeGebietsspezifisch(GruppeGebietsspezifisch gruppeGebietsspezifisch) {
      gruppeGebietsspezifisch.setID_Gruppe(null);
   }

   /**
     * Returns the set of entities of the type {@link Liste}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Liste}
     * @throws EJBException: an error occurred
     */
   public Collection<Liste> getListeCol() throws EJBException {
      ListeHome listeHome = ListeHome.HomeFinder.findHome(this);
      try {
         return listeHome.findAllByGruppe(_details.getID_Gruppe());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Liste}.
     *
     * @param liste Liste-object
     */
   public void addListe(Liste liste) {
      liste.setID_Gruppe(_details.getID_Gruppe());
   }

   /**
     * Adds the object to the set of entities of the type {@link Liste}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllListeCol(Collection<Liste> col) {
      for (Liste liste : col) {
         addListe(liste);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Liste}.
     *
     * @param liste Liste-EJBObject, which is removed from the set.
     */
   public void removeListe(Liste liste) {
      liste.setID_Gruppe(null);
   }

   /**
     * Returns the set of entities of the type {@link ListenkombinationZulassung}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link ListenkombinationZulassung}
     * @throws EJBException: an error occurred
     */
   public Collection<ListenkombinationZulassung> getListenkombinationZulassungCol() throws EJBException {
      ListenkombinationZulassungHome listenkombinationZulassungHome = ListenkombinationZulassungHome.HomeFinder.findHome(this);
      try {
         return listenkombinationZulassungHome.findAllByGruppe(_details.getID_Gruppe());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link ListenkombinationZulassung}.
     *
     * @param listenkombinationZulassung ListenkombinationZulassung-object
     */
   public void addListenkombinationZulassung(ListenkombinationZulassung listenkombinationZulassung) {
      listenkombinationZulassung.setID_Gruppe(_details.getID_Gruppe());
   }

   /**
     * Adds the object to the set of entities of the type {@link ListenkombinationZulassung}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllListenkombinationZulassungCol(Collection<ListenkombinationZulassung> col) {
      for (ListenkombinationZulassung listenkombinationZulassung : col) {
         addListenkombinationZulassung(listenkombinationZulassung);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link ListenkombinationZulassung}.
     *
     * @param listenkombinationZulassung ListenkombinationZulassung-EJBObject, which is removed from the set.
     */
   public void removeListenkombinationZulassung(ListenkombinationZulassung listenkombinationZulassung) {
      listenkombinationZulassung.setID_Gruppe(null);
   }

   /**
     * Returns the set of entities of the type {@link Restsitzverteilung}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Restsitzverteilung}
     * @throws EJBException: an error occurred
     */
   public Collection<Restsitzverteilung> getRestsitzverteilungCol() throws EJBException {
      RestsitzverteilungHome restsitzverteilungHome = RestsitzverteilungHome.HomeFinder.findHome(this);
      try {
         return restsitzverteilungHome.findAllByGruppe(_details.getID_Gruppe());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Restsitzverteilung}.
     *
     * @param restsitzverteilung Restsitzverteilung-object
     */
   public void addRestsitzverteilung(Restsitzverteilung restsitzverteilung) {
      restsitzverteilung.setID_Gruppe(_details.getID_Gruppe());
   }

   /**
     * Adds the object to the set of entities of the type {@link Restsitzverteilung}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllRestsitzverteilungCol(Collection<Restsitzverteilung> col) {
      for (Restsitzverteilung restsitzverteilung : col) {
         addRestsitzverteilung(restsitzverteilung);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Restsitzverteilung}.
     *
     * @param restsitzverteilung Restsitzverteilung-EJBObject, which is removed from the set.
     */
   public void removeRestsitzverteilung(Restsitzverteilung restsitzverteilung) {
      restsitzverteilung.setID_Gruppe(null);
   }

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link SitzberechnungErgebnis}
     * @throws EJBException: an error occurred
     */
   public Collection<SitzberechnungErgebnis> getSitzberechnungErgebnisCol() throws EJBException {
      SitzberechnungErgebnisHome sitzberechnungErgebnisHome = SitzberechnungErgebnisHome.HomeFinder.findHome(this);
      try {
         return sitzberechnungErgebnisHome.findAllByGruppe(_details.getID_Gruppe());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link SitzberechnungErgebnis}.
     *
     * @param sitzberechnungErgebnis SitzberechnungErgebnis-object
     */
   public void addSitzberechnungErgebnis(SitzberechnungErgebnis sitzberechnungErgebnis) {
      sitzberechnungErgebnis.setID_Gruppe(_details.getID_Gruppe());
   }

   /**
     * Adds the object to the set of entities of the type {@link SitzberechnungErgebnis}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllSitzberechnungErgebnisCol(Collection<SitzberechnungErgebnis> col) {
      for (SitzberechnungErgebnis sitzberechnungErgebnis : col) {
         addSitzberechnungErgebnis(sitzberechnungErgebnis);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link SitzberechnungErgebnis}.
     *
     * @param sitzberechnungErgebnis SitzberechnungErgebnis-EJBObject, which is removed from the set.
     */
   public void removeSitzberechnungErgebnis(SitzberechnungErgebnis sitzberechnungErgebnis) {
      sitzberechnungErgebnis.setID_Gruppe(null);
   }

   /**
     * Returns the set of entities of the type {@link Sitzverteilung}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Sitzverteilung}
     * @throws EJBException: an error occurred
     */
   public Collection<Sitzverteilung> getSitzverteilungCol() throws EJBException {
      SitzverteilungHome sitzverteilungHome = SitzverteilungHome.HomeFinder.findHome(this);
      try {
         return sitzverteilungHome.findAllByGruppe(_details.getID_Gruppe());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Sitzverteilung}.
     *
     * @param sitzverteilung Sitzverteilung-object
     */
   public void addSitzverteilung(Sitzverteilung sitzverteilung) {
      sitzverteilung.setID_Gruppe(_details.getID_Gruppe());
   }

   /**
     * Adds the object to the set of entities of the type {@link Sitzverteilung}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllSitzverteilungCol(Collection<Sitzverteilung> col) {
      for (Sitzverteilung sitzverteilung : col) {
         addSitzverteilung(sitzverteilung);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Sitzverteilung}.
     *
     * @param sitzverteilung Sitzverteilung-EJBObject, which is removed from the set.
     */
   public void removeSitzverteilung(Sitzverteilung sitzverteilung) {
      sitzverteilung.setID_Gruppe(null);
   }

   /**
     * Returns the set of entities of the type {@link Unterverteilung}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Unterverteilung}
     * @throws EJBException: an error occurred
     */
   public Collection<Unterverteilung> getUnterverteilungCol() throws EJBException {
      UnterverteilungHome unterverteilungHome = UnterverteilungHome.HomeFinder.findHome(this);
      try {
         return unterverteilungHome.findAllByGruppe(_details.getID_Gruppe());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Unterverteilung}.
     *
     * @param unterverteilung Unterverteilung-object
     */
   public void addUnterverteilung(Unterverteilung unterverteilung) {
      unterverteilung.setID_Gruppe(_details.getID_Gruppe());
   }

   /**
     * Adds the object to the set of entities of the type {@link Unterverteilung}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllUnterverteilungCol(Collection<Unterverteilung> col) {
      for (Unterverteilung unterverteilung : col) {
         addUnterverteilung(unterverteilung);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Unterverteilung}.
     *
     * @param unterverteilung Unterverteilung-EJBObject, which is removed from the set.
     */
   public void removeUnterverteilung(Unterverteilung unterverteilung) {
      unterverteilung.setID_Gruppe(null);
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
         if (_details.getID_Gruppe() != null) {
            string += "id_Gruppe = " + _details.getID_Gruppe(); //$NON-NLS-1$
         }
         if (_details.getID_Wahl() != null) {
            string +=  ", id_Wahl = " + _details.getID_Wahl(); //$NON-NLS-1$
         }
         if (_details.getID_Listenkombination() != null) {
            string +=  ", id_Listenkombination = " + _details.getID_Listenkombination(); //$NON-NLS-1$
         }
         string +=  ", schluessel = " + _details.getSchluessel(); //$NON-NLS-1$
         string +=  ", gruppenart = " + _details.getGruppenart(); //$NON-NLS-1$
         if (_details.getNameLang() != null) {
            string +=  ", nameLang = " + _details.getNameLang(); //$NON-NLS-1$
         }
         if (_details.getNameKurz() != null) {
            string +=  ", nameKurz = " + _details.getNameKurz(); //$NON-NLS-1$
         }
         string +=  ", kautionGestellt = " + _details.isKautionGestellt(); //$NON-NLS-1$
         if (_details.getFarbe() != null) {
            string +=  ", farbe = " + _details.getFarbe(); //$NON-NLS-1$
         }
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
