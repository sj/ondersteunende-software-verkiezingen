/*
 * ListenkombinationBean
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
import de.ivu.wahl.modell.ListenkombinationModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Listenkombination as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class ListenkombinationBean extends BMPBeanBase implements EntityBean, ListenkombinationModel {
   private static final Category LOGGER = Log4J.configure(ListenkombinationBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(ListenkombinationBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected ListenkombinationModel _details = null;

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
   public String ejbCreate(ListenkombinationModel details) throws CreateException, EJBException {
      ListenkombinationModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Listenkombination();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Listenkombination key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Listenkombination) throws CreateException, EJBException {
      _details = (ListenkombinationModel)createModel(id_Listenkombination);
      _details.setBezeichnung(""); //$NON-NLS-1$
      create(_details);
      return id_Listenkombination;
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
         return ListenkombinationDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Wahl ID of the objects to be searched
     * @return  {@link Collection} of the found Listenkombination-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByWahl(String id_Wahl) throws IVUFinderException {
      try {
         return ListenkombinationDBA.retrieveIDsByID_Wahl(id_Wahl);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Listenkombination}, filtered by bezeichnung.
     *
     * @param bezeichnung searching condition
     * @return  {@link Collection} of primary keys of the entities Listenkombination
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByBezeichnung(String bezeichnung) throws IVUFinderException {
      try {
         return ListenkombinationDBA.retrieveIDsByBezeichnung(bezeichnung);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Listenkombination}, filtered by bezeichnung.
     *
     * @param bezeichnung searching condition
     * @return  {@link Collection} of primary keys of the entities Listenkombination
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeBezeichnung(String bezeichnung) throws IVUFinderException {
      try {
         return ListenkombinationDBA.retrieveIDsLikeBezeichnung(bezeichnung);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Listenkombination key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Listenkombination) {
      // No functionality required
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(ListenkombinationModel details) {
      // No functionality required
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public ListenkombinationModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(ListenkombinationModel details) {
      if (details instanceof ListenkombinationModelImpl && _details instanceof ListenkombinationModelImpl) {
         ((ListenkombinationModelImpl)_details).copyFrom((ListenkombinationModelImpl)details);
         checkRelations();
      } else {
         setID_Wahl(details.getID_Wahl());
         setBezeichnung(details.getBezeichnung());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (ListenkombinationModel)details;
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
      if (null == _details.getID_Wahl()) {
         _wahl = null;
         _relchk_Wahl = true;
      } else {
         _relchk_Wahl = false;
      }
   }

   @Override
   protected void resetRelations() {
      _wahl = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public ListenkombinationModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Listenkombination in the entity Listenkombination
     *
     * @return value of the attribute id_Listenkombination
     */
   public String getID_Listenkombination() {
      return _details.getID_Listenkombination();
   }

   /**
     * Sets the value of id_Wahl in the entity Listenkombination
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
     * Gets the value of id_Wahl in the entity Listenkombination
     *
     * @return value of the attribute id_Wahl
     */
   public String getID_Wahl() {
      return _details.getID_Wahl();
   }

   /**
     * Sets the value of bezeichnung in the entity Listenkombination
     *
     * @param bezeichnung new value of the attribute bezeichnung
     */
   public void setBezeichnung(String bezeichnung) {
      _details.setBezeichnung(bezeichnung);
   }

   /**
     * Gets the value of bezeichnung in the entity Listenkombination
     *
     * @return value of the attribute bezeichnung
     */
   public String getBezeichnung() {
      return _details.getBezeichnung();
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
         return alternativeHome.findAllByListenkombination(_details.getID_Listenkombination());
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
      alternative.setID_Listenkombination(_details.getID_Listenkombination());
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
      alternative.setID_Listenkombination(null);
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
         return besonderheitHome.findAllByListenkombination(_details.getID_Listenkombination());
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
      besonderheit.setID_Listenkombination(_details.getID_Listenkombination());
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
      besonderheit.setID_Listenkombination(null);
   }

   /**
     * Returns the set of entities of the type {@link Gruppe}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Gruppe}
     * @throws EJBException: an error occurred
     */
   public Collection<Gruppe> getGruppeCol() throws EJBException {
      GruppeHome gruppeHome = GruppeHome.HomeFinder.findHome(this);
      try {
         return gruppeHome.findAllByListenkombination(_details.getID_Listenkombination());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Gruppe}.
     *
     * @param gruppe Gruppe-object
     */
   public void addGruppe(Gruppe gruppe) {
      gruppe.setID_Listenkombination(_details.getID_Listenkombination());
   }

   /**
     * Adds the object to the set of entities of the type {@link Gruppe}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllGruppeCol(Collection<Gruppe> col) {
      for (Gruppe gruppe : col) {
         addGruppe(gruppe);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Gruppe}.
     *
     * @param gruppe Gruppe-EJBObject, which is removed from the set.
     */
   public void removeGruppe(Gruppe gruppe) {
      gruppe.setID_Listenkombination(null);
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
         return listenkombinationZulassungHome.findAllByListenkombination(_details.getID_Listenkombination());
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
      listenkombinationZulassung.setID_Listenkombination(_details.getID_Listenkombination());
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
      listenkombinationZulassung.setID_Listenkombination(null);
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
         return restsitzverteilungHome.findAllByListenkombination(_details.getID_Listenkombination());
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
      restsitzverteilung.setID_Listenkombination(_details.getID_Listenkombination());
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
      restsitzverteilung.setID_Listenkombination(null);
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
         return sitzberechnungErgebnisHome.findAllByListenkombination(_details.getID_Listenkombination());
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
      sitzberechnungErgebnis.setID_Listenkombination(_details.getID_Listenkombination());
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
      sitzberechnungErgebnis.setID_Listenkombination(null);
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
         return sitzverteilungHome.findAllByListenkombination(_details.getID_Listenkombination());
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
      sitzverteilung.setID_Listenkombination(_details.getID_Listenkombination());
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
      sitzverteilung.setID_Listenkombination(null);
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
         return unterverteilungHome.findAllByListenkombination(_details.getID_Listenkombination());
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
      unterverteilung.setID_Listenkombination(_details.getID_Listenkombination());
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
      unterverteilung.setID_Listenkombination(null);
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
         if (_details.getID_Listenkombination() != null) {
            string += "id_Listenkombination = " + _details.getID_Listenkombination(); //$NON-NLS-1$
         }
         if (_details.getID_Wahl() != null) {
            string +=  ", id_Wahl = " + _details.getID_Wahl(); //$NON-NLS-1$
         }
         if (_details.getBezeichnung() != null) {
            string +=  ", bezeichnung = " + _details.getBezeichnung(); //$NON-NLS-1$
         }
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
