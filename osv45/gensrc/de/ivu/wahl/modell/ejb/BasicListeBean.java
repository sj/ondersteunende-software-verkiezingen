/*
 * BasicListeBean
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
import de.ivu.wahl.modell.ListeModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Liste as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicListeBean extends BMPBeanBase implements EntityBean, ListeModel {
   private static final Category LOGGER = Log4J.configure(BasicListeBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicListeBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected ListeModel _details = null;

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
   public String ejbCreate(ListeModel details) throws CreateException, EJBException {
      ListeModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Liste();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Liste key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Liste) throws CreateException, EJBException {
      _details = (ListeModel)createModel(id_Liste);
      _details.setTyp(""); //$NON-NLS-1$
      _details.setName(""); //$NON-NLS-1$
      _details.setPublicationLanguage(""); //$NON-NLS-1$
      create(_details);
      return id_Liste;
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
         return ListeDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gruppe ID of the objects to be searched
     * @return  {@link Collection} of the found Liste-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByGruppe(String id_Gruppe) throws IVUFinderException {
      try {
         return ListeDBA.retrieveIDsByID_Gruppe(id_Gruppe);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Wahl ID of the objects to be searched
     * @return  {@link Collection} of the found Liste-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByWahl(String id_Wahl) throws IVUFinderException {
      try {
         return ListeDBA.retrieveIDsByID_Wahl(id_Wahl);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Liste}, filtered by typ.
     *
     * @param typ searching condition
     * @return  {@link Collection} of primary keys of the entities Liste
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByTyp(String typ) throws IVUFinderException {
      try {
         return ListeDBA.retrieveIDsByTyp(typ);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Liste}, filtered by typ.
     *
     * @param typ searching condition
     * @return  {@link Collection} of primary keys of the entities Liste
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeTyp(String typ) throws IVUFinderException {
      try {
         return ListeDBA.retrieveIDsLikeTyp(typ);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Liste}, filtered by satz.
     *
     * @param satz searching condition
     * @return  {@link Collection} of primary keys of the entities Liste
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllBySatz(int satz) throws IVUFinderException {
      try {
         return ListeDBA.retrieveIDsBySatz(satz);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Liste}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of primary keys of the entities Liste
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByName(String name) throws IVUFinderException {
      try {
         return ListeDBA.retrieveIDsByName(name);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Liste}, filtered by name.
     *
     * @param name searching condition
     * @return  {@link Collection} of primary keys of the entities Liste
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeName(String name) throws IVUFinderException {
      try {
         return ListeDBA.retrieveIDsLikeName(name);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Liste}, filtered by geschlechtSichtbar.
     *
     * @param geschlechtSichtbar searching condition
     * @return  {@link Collection} of primary keys of the entities Liste
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByGeschlechtSichtbar(boolean geschlechtSichtbar) throws IVUFinderException {
      try {
         return ListeDBA.retrieveIDsByGeschlechtSichtbar(geschlechtSichtbar);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Liste}, filtered by publicationLanguage.
     *
     * @param publicationLanguage searching condition
     * @return  {@link Collection} of primary keys of the entities Liste
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByPublicationLanguage(String publicationLanguage) throws IVUFinderException {
      try {
         return ListeDBA.retrieveIDsByPublicationLanguage(publicationLanguage);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Liste}, filtered by publicationLanguage.
     *
     * @param publicationLanguage searching condition
     * @return  {@link Collection} of primary keys of the entities Liste
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikePublicationLanguage(String publicationLanguage) throws IVUFinderException {
      try {
         return ListeDBA.retrieveIDsLikePublicationLanguage(publicationLanguage);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Liste key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Liste) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(ListeModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public ListeModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(ListeModel details) {
      if (details instanceof ListeModelImpl && _details instanceof ListeModelImpl) {
         ((ListeModelImpl)_details).copyFrom((ListeModelImpl)details);
         checkRelations();
      } else {
         setID_Wahl(details.getID_Wahl());
         setID_Gruppe(details.getID_Gruppe());
         setTyp(details.getTyp());
         setSatz(details.getSatz());
         setName(details.getName());
         setGeschlechtSichtbar(details.isGeschlechtSichtbar());
         setPublicationLanguage(details.getPublicationLanguage());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (ListeModel)details;
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
      if (null == _details.getID_Gruppe()) {
         _gruppe = null;
         _relchk_Gruppe = true;
      } else {
         _relchk_Gruppe = false;
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
      _gruppe = null;
      _wahl = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public ListeModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Liste in the entity Liste
     *
     * @return value of the attribute id_Liste
     */
   public String getID_Liste() {
      return _details.getID_Liste();
   }

   /**
     * Sets the value of id_Wahl in the entity Liste
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
     * Gets the value of id_Wahl in the entity Liste
     *
     * @return value of the attribute id_Wahl
     */
   public String getID_Wahl() {
      return _details.getID_Wahl();
   }

   /**
     * Sets the value of id_Gruppe in the entity Liste
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
     * Gets the value of id_Gruppe in the entity Liste
     *
     * @return value of the attribute id_Gruppe
     */
   public String getID_Gruppe() {
      return _details.getID_Gruppe();
   }

   /**
     * Sets the value of typ in the entity Liste
     *
     * @param typ new value of the attribute typ
     */
   public void setTyp(String typ) {
      _details.setTyp(typ);
   }

   /**
     * Gets the value of typ in the entity Liste
     *
     * @return value of the attribute typ
     */
   public String getTyp() {
      return _details.getTyp();
   }

   /**
     * Sets the value of satz in the entity Liste
     *
     * @param satz new value of the attribute satz
     */
   public void setSatz(int satz) {
      _details.setSatz(satz);
   }

   /**
     * Gets the value of satz in the entity Liste
     *
     * @return value of the attribute satz
     */
   public int getSatz() {
      return _details.getSatz();
   }

   /**
     * Sets the value of name in the entity Liste
     *
     * @param name new value of the attribute name
     */
   public void setName(String name) {
      _details.setName(name);
   }

   /**
     * Gets the value of name in the entity Liste
     *
     * @return value of the attribute name
     */
   public String getName() {
      return _details.getName();
   }

   /**
     * Sets the value of geschlechtSichtbar in the entity Liste
     *
     * @param geschlechtSichtbar new value of the attribute geschlechtSichtbar
     */
   public void setGeschlechtSichtbar(boolean geschlechtSichtbar) {
      _details.setGeschlechtSichtbar(geschlechtSichtbar);
   }

   /**
     * Gets the value of geschlechtSichtbar in the entity Liste
     *
     * @return value of the attribute geschlechtSichtbar
     */
   public boolean isGeschlechtSichtbar() {
      return _details.isGeschlechtSichtbar();
   }

   /**
     * Sets the value of publicationLanguage in the entity Liste
     *
     * @param publicationLanguage new value of the attribute publicationLanguage
     */
   public void setPublicationLanguage(String publicationLanguage) {
      _details.setPublicationLanguage(publicationLanguage);
   }

   /**
     * Gets the value of publicationLanguage in the entity Liste
     *
     * @return value of the attribute publicationLanguage
     */
   public String getPublicationLanguage() {
      return _details.getPublicationLanguage();
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
         return alternativeHome.findAllByListe(_details.getID_Liste());
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
      alternative.setID_Liste(_details.getID_Liste());
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
      alternative.setID_Liste(null);
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
         return besonderheitHome.findAllByListe(_details.getID_Liste());
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
      besonderheit.setID_Liste(_details.getID_Liste());
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
      besonderheit.setID_Liste(null);
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
         return gruppeGebietsspezifischHome.findAllByListe(_details.getID_Liste());
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
      gruppeGebietsspezifisch.setID_Liste(_details.getID_Liste());
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
      gruppeGebietsspezifisch.setID_Liste(null);
   }

   /**
     * Returns the set of entities of the type {@link Listenkandidatur}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Listenkandidatur}
     * @throws EJBException: an error occurred
     */
   public Collection<Listenkandidatur> getListenkandidaturCol() throws EJBException {
      ListenkandidaturHome listenkandidaturHome = ListenkandidaturHome.HomeFinder.findHome(this);
      try {
         return listenkandidaturHome.findAllByListe(_details.getID_Liste());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Listenkandidatur}.
     *
     * @param listenkandidatur Listenkandidatur-object
     */
   public void addListenkandidatur(Listenkandidatur listenkandidatur) {
      listenkandidatur.setID_Liste(_details.getID_Liste());
   }

   /**
     * Adds the object to the set of entities of the type {@link Listenkandidatur}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllListenkandidaturCol(Collection<Listenkandidatur> col) {
      for (Listenkandidatur listenkandidatur : col) {
         addListenkandidatur(listenkandidatur);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Listenkandidatur}.
     *
     * @param listenkandidatur Listenkandidatur-EJBObject, which is removed from the set.
     */
   public void removeListenkandidatur(Listenkandidatur listenkandidatur) {
      listenkandidatur.setID_Liste(null);
   }

   /**
     * Returns the set of entities of the type {@link ListenplatzNeu}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link ListenplatzNeu}
     * @throws EJBException: an error occurred
     */
   public Collection<ListenplatzNeu> getListenplatzNeuCol() throws EJBException {
      ListenplatzNeuHome listenplatzNeuHome = ListenplatzNeuHome.HomeFinder.findHome(this);
      try {
         return listenplatzNeuHome.findAllByListe(_details.getID_Liste());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link ListenplatzNeu}.
     *
     * @param listenplatzNeu ListenplatzNeu-object
     */
   public void addListenplatzNeu(ListenplatzNeu listenplatzNeu) {
      listenplatzNeu.setID_Liste(_details.getID_Liste());
   }

   /**
     * Adds the object to the set of entities of the type {@link ListenplatzNeu}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllListenplatzNeuCol(Collection<ListenplatzNeu> col) {
      for (ListenplatzNeu listenplatzNeu : col) {
         addListenplatzNeu(listenplatzNeu);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link ListenplatzNeu}.
     *
     * @param listenplatzNeu ListenplatzNeu-EJBObject, which is removed from the set.
     */
   public void removeListenplatzNeu(ListenplatzNeu listenplatzNeu) {
      listenplatzNeu.setID_Liste(null);
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
         return restsitzverteilungHome.findAllByListe(_details.getID_Liste());
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
      restsitzverteilung.setID_Liste(_details.getID_Liste());
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
      restsitzverteilung.setID_Liste(null);
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
         return sitzberechnungErgebnisHome.findAllByListe(_details.getID_Liste());
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
      sitzberechnungErgebnis.setID_Liste(_details.getID_Liste());
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
      sitzberechnungErgebnis.setID_Liste(null);
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
         return sitzverteilungHome.findAllByListe(_details.getID_Liste());
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
      sitzverteilung.setID_Liste(_details.getID_Liste());
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
      sitzverteilung.setID_Liste(null);
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
         if (_details.getID_Liste() != null) {
            string += "id_Liste = " + _details.getID_Liste(); //$NON-NLS-1$
         }
         if (_details.getID_Wahl() != null) {
            string +=  ", id_Wahl = " + _details.getID_Wahl(); //$NON-NLS-1$
         }
         if (_details.getID_Gruppe() != null) {
            string +=  ", id_Gruppe = " + _details.getID_Gruppe(); //$NON-NLS-1$
         }
         if (_details.getTyp() != null) {
            string +=  ", typ = " + _details.getTyp(); //$NON-NLS-1$
         }
         string +=  ", satz = " + _details.getSatz(); //$NON-NLS-1$
         if (_details.getName() != null) {
            string +=  ", name = " + _details.getName(); //$NON-NLS-1$
         }
         string +=  ", geschlechtSichtbar = " + _details.isGeschlechtSichtbar(); //$NON-NLS-1$
         if (_details.getPublicationLanguage() != null) {
            string +=  ", publicationLanguage = " + _details.getPublicationLanguage(); //$NON-NLS-1$
         }
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
