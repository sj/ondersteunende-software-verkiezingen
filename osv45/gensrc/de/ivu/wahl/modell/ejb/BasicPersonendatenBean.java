/*
 * BasicPersonendatenBean
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
import de.ivu.wahl.modell.PersonendatenModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity Personendaten as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicPersonendatenBean extends BMPBeanBase implements EntityBean, PersonendatenModel {
   private static final Category LOGGER = Log4J.configure(BasicPersonendatenBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicPersonendatenBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected PersonendatenModel _details = null;

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
   public String ejbCreate(PersonendatenModel details) throws CreateException, EJBException {
      PersonendatenModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_Personendaten();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_Personendaten key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_Personendaten) throws CreateException, EJBException {
      _details = (PersonendatenModel)createModel(id_Personendaten);
      _details.setNachname(""); //$NON-NLS-1$
      _details.setVorname(""); //$NON-NLS-1$
      _details.setPraefix(""); //$NON-NLS-1$
      _details.setInitialen(""); //$NON-NLS-1$
      _details.setTitel(""); //$NON-NLS-1$
      _details.setGeneration(""); //$NON-NLS-1$
      _details.setLand(""); //$NON-NLS-1$
      _details.setWohnort(""); //$NON-NLS-1$
      _details.setKontakt_Land(""); //$NON-NLS-1$
      _details.setKontakt_Wohnort(""); //$NON-NLS-1$
      _details.setKontakt_PLZ(""); //$NON-NLS-1$
      _details.setKontakt_Strasse(""); //$NON-NLS-1$
      create(_details);
      return id_Personendaten;
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
         return PersonendatenDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_PersonendatenAgent ID of the objects to be searched
     * @return  {@link Collection} of the found Personendaten-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByPersonendatenAgent(String id_PersonendatenAgent) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsByID_PersonendatenAgent(id_PersonendatenAgent);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by nachname.
     *
     * @param nachname searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByNachname(String nachname) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsByNachname(nachname);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by nachname.
     *
     * @param nachname searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeNachname(String nachname) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsLikeNachname(nachname);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by vorname.
     *
     * @param vorname searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByVorname(String vorname) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsByVorname(vorname);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by vorname.
     *
     * @param vorname searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeVorname(String vorname) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsLikeVorname(vorname);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by praefix.
     *
     * @param praefix searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByPraefix(String praefix) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsByPraefix(praefix);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by praefix.
     *
     * @param praefix searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikePraefix(String praefix) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsLikePraefix(praefix);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by initialen.
     *
     * @param initialen searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByInitialen(String initialen) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsByInitialen(initialen);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by initialen.
     *
     * @param initialen searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeInitialen(String initialen) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsLikeInitialen(initialen);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by titel.
     *
     * @param titel searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByTitel(String titel) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsByTitel(titel);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by titel.
     *
     * @param titel searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeTitel(String titel) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsLikeTitel(titel);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by geschlecht.
     *
     * @param geschlecht searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByGeschlecht(int geschlecht) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsByGeschlecht(geschlecht);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by generation.
     *
     * @param generation searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByGeneration(String generation) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsByGeneration(generation);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by generation.
     *
     * @param generation searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeGeneration(String generation) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsLikeGeneration(generation);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by land.
     *
     * @param land searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByLand(String land) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsByLand(land);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by land.
     *
     * @param land searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeLand(String land) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsLikeLand(land);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by wohnort.
     *
     * @param wohnort searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByWohnort(String wohnort) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsByWohnort(wohnort);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by wohnort.
     *
     * @param wohnort searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeWohnort(String wohnort) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsLikeWohnort(wohnort);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by kontakt_Land.
     *
     * @param kontakt_Land searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByKontakt_Land(String kontakt_Land) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsByKontakt_Land(kontakt_Land);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by kontakt_Land.
     *
     * @param kontakt_Land searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeKontakt_Land(String kontakt_Land) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsLikeKontakt_Land(kontakt_Land);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by kontakt_Wohnort.
     *
     * @param kontakt_Wohnort searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByKontakt_Wohnort(String kontakt_Wohnort) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsByKontakt_Wohnort(kontakt_Wohnort);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by kontakt_Wohnort.
     *
     * @param kontakt_Wohnort searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeKontakt_Wohnort(String kontakt_Wohnort) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsLikeKontakt_Wohnort(kontakt_Wohnort);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by kontakt_PLZ.
     *
     * @param kontakt_PLZ searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByKontakt_PLZ(String kontakt_PLZ) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsByKontakt_PLZ(kontakt_PLZ);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by kontakt_PLZ.
     *
     * @param kontakt_PLZ searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeKontakt_PLZ(String kontakt_PLZ) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsLikeKontakt_PLZ(kontakt_PLZ);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by kontakt_Strasse.
     *
     * @param kontakt_Strasse searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByKontakt_Strasse(String kontakt_Strasse) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsByKontakt_Strasse(kontakt_Strasse);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by kontakt_Strasse.
     *
     * @param kontakt_Strasse searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllLikeKontakt_Strasse(String kontakt_Strasse) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsLikeKontakt_Strasse(kontakt_Strasse);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}, filtered by benennbar.
     *
     * @param benennbar searching condition
     * @return  {@link Collection} of primary keys of the entities Personendaten
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByBenennbar(boolean benennbar) throws IVUFinderException {
      try {
         return PersonendatenDBA.retrieveIDsByBenennbar(benennbar);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_Personendaten key element of the type {@link String}
     */
   public void ejbPostCreate(String id_Personendaten) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(PersonendatenModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public PersonendatenModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(PersonendatenModel details) {
      if (details instanceof PersonendatenModelImpl && _details instanceof PersonendatenModelImpl) {
         ((PersonendatenModelImpl)_details).copyFrom((PersonendatenModelImpl)details);
         checkRelations();
      } else {
         setID_PersonendatenAgent(details.getID_PersonendatenAgent());
         setNachname(details.getNachname());
         setVorname(details.getVorname());
         setPraefix(details.getPraefix());
         setInitialen(details.getInitialen());
         setTitel(details.getTitel());
         setGeschlecht(details.getGeschlecht());
         setGeneration(details.getGeneration());
         setLand(details.getLand());
         setWohnort(details.getWohnort());
         setKontakt_Land(details.getKontakt_Land());
         setKontakt_Wohnort(details.getKontakt_Wohnort());
         setKontakt_PLZ(details.getKontakt_PLZ());
         setKontakt_Strasse(details.getKontakt_Strasse());
         setBenennbar(details.isBenennbar());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (PersonendatenModel)details;
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
      if (null == _details.getID_PersonendatenAgent()) {
         _personendatenAgent = null;
         _relchk_PersonendatenAgent = true;
      } else {
         _relchk_PersonendatenAgent = false;
      }
   }

   @Override
   protected void resetRelations() {
      _personendatenAgent = null;
   }

   /**
     * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface
     *
     * @return Copy of the model object
     */
   public PersonendatenModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_Personendaten in the entity Personendaten
     *
     * @return value of the attribute id_Personendaten
     */
   public String getID_Personendaten() {
      return _details.getID_Personendaten();
   }

   /**
     * Sets the value of id_PersonendatenAgent in the entity Personendaten
     *
     * @param id_PersonendatenAgent new value of the attribute id_PersonendatenAgent
     */
   public void setID_PersonendatenAgent(String id_PersonendatenAgent) {
      if (null == id_PersonendatenAgent) {
         _personendatenAgent = null;
         _relchk_PersonendatenAgent = true;
      } else {
         String old = _details.getID_PersonendatenAgent();
         if (old == null || !old.equals(id_PersonendatenAgent)) {
            _relchk_PersonendatenAgent = false;
         }
      }
      _details.setID_PersonendatenAgent(id_PersonendatenAgent);
   }

   /**
     * Gets the value of id_PersonendatenAgent in the entity Personendaten
     *
     * @return value of the attribute id_PersonendatenAgent
     */
   public String getID_PersonendatenAgent() {
      return _details.getID_PersonendatenAgent();
   }

   /**
     * Sets the value of nachname in the entity Personendaten
     *
     * @param nachname new value of the attribute nachname
     */
   public void setNachname(String nachname) {
      _details.setNachname(nachname);
   }

   /**
     * Gets the value of nachname in the entity Personendaten
     *
     * @return value of the attribute nachname
     */
   public String getNachname() {
      return _details.getNachname();
   }

   /**
     * Sets the value of vorname in the entity Personendaten
     *
     * @param vorname new value of the attribute vorname
     */
   public void setVorname(String vorname) {
      _details.setVorname(vorname);
   }

   /**
     * Gets the value of vorname in the entity Personendaten
     *
     * @return value of the attribute vorname
     */
   public String getVorname() {
      return _details.getVorname();
   }

   /**
     * Sets the value of praefix in the entity Personendaten
     *
     * @param praefix new value of the attribute praefix
     */
   public void setPraefix(String praefix) {
      _details.setPraefix(praefix);
   }

   /**
     * Gets the value of praefix in the entity Personendaten
     *
     * @return value of the attribute praefix
     */
   public String getPraefix() {
      return _details.getPraefix();
   }

   /**
     * Sets the value of initialen in the entity Personendaten
     *
     * @param initialen new value of the attribute initialen
     */
   public void setInitialen(String initialen) {
      _details.setInitialen(initialen);
   }

   /**
     * Gets the value of initialen in the entity Personendaten
     *
     * @return value of the attribute initialen
     */
   public String getInitialen() {
      return _details.getInitialen();
   }

   /**
     * Sets the value of titel in the entity Personendaten
     *
     * @param titel new value of the attribute titel
     */
   public void setTitel(String titel) {
      _details.setTitel(titel);
   }

   /**
     * Gets the value of titel in the entity Personendaten
     *
     * @return value of the attribute titel
     */
   public String getTitel() {
      return _details.getTitel();
   }

   /**
     * Sets the value of geschlecht in the entity Personendaten
     *
     * @param geschlecht new value of the attribute geschlecht
     */
   public void setGeschlecht(int geschlecht) {
      _details.setGeschlecht(geschlecht);
   }

   /**
     * Gets the value of geschlecht in the entity Personendaten
     *
     * @return value of the attribute geschlecht
     */
   public int getGeschlecht() {
      return _details.getGeschlecht();
   }

   /**
     * Sets the value of generation in the entity Personendaten
     *
     * @param generation new value of the attribute generation
     */
   public void setGeneration(String generation) {
      _details.setGeneration(generation);
   }

   /**
     * Gets the value of generation in the entity Personendaten
     *
     * @return value of the attribute generation
     */
   public String getGeneration() {
      return _details.getGeneration();
   }

   /**
     * Sets the value of land in the entity Personendaten
     *
     * @param land new value of the attribute land
     */
   public void setLand(String land) {
      _details.setLand(land);
   }

   /**
     * Gets the value of land in the entity Personendaten
     *
     * @return value of the attribute land
     */
   public String getLand() {
      return _details.getLand();
   }

   /**
     * Sets the value of wohnort in the entity Personendaten
     *
     * @param wohnort new value of the attribute wohnort
     */
   public void setWohnort(String wohnort) {
      _details.setWohnort(wohnort);
   }

   /**
     * Gets the value of wohnort in the entity Personendaten
     *
     * @return value of the attribute wohnort
     */
   public String getWohnort() {
      return _details.getWohnort();
   }

   /**
     * Sets the value of kontakt_Land in the entity Personendaten
     *
     * @param kontakt_Land new value of the attribute kontakt_Land
     */
   public void setKontakt_Land(String kontakt_Land) {
      _details.setKontakt_Land(kontakt_Land);
   }

   /**
     * Gets the value of kontakt_Land in the entity Personendaten
     *
     * @return value of the attribute kontakt_Land
     */
   public String getKontakt_Land() {
      return _details.getKontakt_Land();
   }

   /**
     * Sets the value of kontakt_Wohnort in the entity Personendaten
     *
     * @param kontakt_Wohnort new value of the attribute kontakt_Wohnort
     */
   public void setKontakt_Wohnort(String kontakt_Wohnort) {
      _details.setKontakt_Wohnort(kontakt_Wohnort);
   }

   /**
     * Gets the value of kontakt_Wohnort in the entity Personendaten
     *
     * @return value of the attribute kontakt_Wohnort
     */
   public String getKontakt_Wohnort() {
      return _details.getKontakt_Wohnort();
   }

   /**
     * Sets the value of kontakt_PLZ in the entity Personendaten
     *
     * @param kontakt_PLZ new value of the attribute kontakt_PLZ
     */
   public void setKontakt_PLZ(String kontakt_PLZ) {
      _details.setKontakt_PLZ(kontakt_PLZ);
   }

   /**
     * Gets the value of kontakt_PLZ in the entity Personendaten
     *
     * @return value of the attribute kontakt_PLZ
     */
   public String getKontakt_PLZ() {
      return _details.getKontakt_PLZ();
   }

   /**
     * Sets the value of kontakt_Strasse in the entity Personendaten
     *
     * @param kontakt_Strasse new value of the attribute kontakt_Strasse
     */
   public void setKontakt_Strasse(String kontakt_Strasse) {
      _details.setKontakt_Strasse(kontakt_Strasse);
   }

   /**
     * Gets the value of kontakt_Strasse in the entity Personendaten
     *
     * @return value of the attribute kontakt_Strasse
     */
   public String getKontakt_Strasse() {
      return _details.getKontakt_Strasse();
   }

   /**
     * Sets the value of benennbar in the entity Personendaten
     *
     * @param benennbar new value of the attribute benennbar
     */
   public void setBenennbar(boolean benennbar) {
      _details.setBenennbar(benennbar);
   }

   /**
     * Gets the value of benennbar in the entity Personendaten
     *
     * @return value of the attribute benennbar
     */
   public boolean isBenennbar() {
      return _details.isBenennbar();
   }

   /**
     * Relation zu PersonendatenAgent
     */
   protected Personendaten _personendatenAgent;

   /**
     * Flag for the validity of the relation PersonendatenAgent
     */
   protected boolean _relchk_PersonendatenAgent = false;

   /**
     * Navigation to the associated entity of the type {@link Personendaten}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   public Personendaten getPersonendatenAgent() throws EJBException {
      if (!_relchk_PersonendatenAgent) {
         if (null == _details.getID_PersonendatenAgent()) {
            _personendatenAgent = null;
         } else if (null == _personendatenAgent || !_personendatenAgent.getPrimaryKey().equals(_details.getID_PersonendatenAgent())) {
            try {
               PersonendatenHome home = PersonendatenHome.HomeFinder.findHome(this);
               _personendatenAgent = home.findByPrimaryKey(_details.getID_PersonendatenAgent());
            } catch (ObjectNotFoundException onfe) {
               throw new EJBException("Unable to find PersonendatenAgent", onfe); //$NON-NLS-1$
            } catch (FinderException fe) {
               throw new EJBException("Probably DB inconsistence in table Personendaten", fe); //$NON-NLS-1$
            }
         }
         _relchk_PersonendatenAgent = true;
      }
      return _personendatenAgent;
   }

   /**
     * Setting of the associated entity of the type {@link Personendaten}
     *
     * @param personendatenAgent the corresponding EJBObject
     */
   public void setPersonendatenAgent(Personendaten personendatenAgent) {
      _personendatenAgent = personendatenAgent;
      _details.setID_PersonendatenAgent(personendatenAgent == null ? null : (String)personendatenAgent.getPrimaryKey());
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
         return alternativeHome.findAllByPersonendaten(_details.getID_Personendaten());
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
      alternative.setID_Personendaten(_details.getID_Personendaten());
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
      alternative.setID_Personendaten(null);
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
         return besonderheitHome.findAllByPersonendaten(_details.getID_Personendaten());
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
      besonderheit.setID_Personendaten(_details.getID_Personendaten());
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
      besonderheit.setID_Personendaten(null);
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
         return listenkandidaturHome.findAllByPersonendaten(_details.getID_Personendaten());
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
      listenkandidatur.setID_Personendaten(_details.getID_Personendaten());
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
      listenkandidatur.setID_Personendaten(null);
   }

   /**
     * Returns the set of entities of the type {@link Personendaten}.
     *
     * @return  {@link Collection} of {@link EJBLocalObject} type {@link Personendaten}
     * @throws EJBException: an error occurred
     */
   public Collection<Personendaten> getPersonendatenAgentCol() throws EJBException {
      PersonendatenHome personendatenHome = PersonendatenHome.HomeFinder.findHome(this);
      try {
         return personendatenHome.findAllByPersonendatenAgent(_details.getID_Personendaten());
      } catch (FinderException fe) {
         throw new EJBException(fe);
      }
   }

   /**
     * Adds the object to the set of entities of the type {@link Personendaten}.
     *
     * @param personendatenAgent Personendaten-object
     */
   public void addPersonendatenAgent(Personendaten personendatenAgent) {
      personendatenAgent.setID_PersonendatenAgent(_details.getID_Personendaten());
   }

   /**
     * Adds the object to the set of entities of the type {@link Personendaten}.
     *
     * @param col {@link Collection} of {@link EJBObject}s, which are added to the set.
     */
   public void addAllPersonendatenAgentCol(Collection<Personendaten> col) {
      for (Personendaten personendatenAgent : col) {
         addPersonendatenAgent(personendatenAgent);
      }
   }

   /**
     * Deletes an entity from the set of entities of the type {@link Personendaten}.
     *
     * @param personendatenAgent Personendaten-EJBObject, which is removed from the set.
     */
   public void removePersonendatenAgent(Personendaten personendatenAgent) {
      personendatenAgent.setID_PersonendatenAgent(null);
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
         if (_details.getID_Personendaten() != null) {
            string += "id_Personendaten = " + _details.getID_Personendaten(); //$NON-NLS-1$
         }
         if (_details.getID_PersonendatenAgent() != null) {
            string +=  ", id_PersonendatenAgent = " + _details.getID_PersonendatenAgent(); //$NON-NLS-1$
         }
         if (_details.getNachname() != null) {
            string +=  ", nachname = " + _details.getNachname(); //$NON-NLS-1$
         }
         if (_details.getVorname() != null) {
            string +=  ", vorname = " + _details.getVorname(); //$NON-NLS-1$
         }
         if (_details.getPraefix() != null) {
            string +=  ", praefix = " + _details.getPraefix(); //$NON-NLS-1$
         }
         if (_details.getInitialen() != null) {
            string +=  ", initialen = " + _details.getInitialen(); //$NON-NLS-1$
         }
         if (_details.getTitel() != null) {
            string +=  ", titel = " + _details.getTitel(); //$NON-NLS-1$
         }
         string +=  ", geschlecht = " + _details.getGeschlecht(); //$NON-NLS-1$
         if (_details.getGeneration() != null) {
            string +=  ", generation = " + _details.getGeneration(); //$NON-NLS-1$
         }
         if (_details.getLand() != null) {
            string +=  ", land = " + _details.getLand(); //$NON-NLS-1$
         }
         if (_details.getWohnort() != null) {
            string +=  ", wohnort = " + _details.getWohnort(); //$NON-NLS-1$
         }
         if (_details.getKontakt_Land() != null) {
            string +=  ", kontakt_Land = " + _details.getKontakt_Land(); //$NON-NLS-1$
         }
         if (_details.getKontakt_Wohnort() != null) {
            string +=  ", kontakt_Wohnort = " + _details.getKontakt_Wohnort(); //$NON-NLS-1$
         }
         if (_details.getKontakt_PLZ() != null) {
            string +=  ", kontakt_PLZ = " + _details.getKontakt_PLZ(); //$NON-NLS-1$
         }
         if (_details.getKontakt_Strasse() != null) {
            string +=  ", kontakt_Strasse = " + _details.getKontakt_Strasse(); //$NON-NLS-1$
         }
         string +=  ", benennbar = " + _details.isBenennbar(); //$NON-NLS-1$
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
