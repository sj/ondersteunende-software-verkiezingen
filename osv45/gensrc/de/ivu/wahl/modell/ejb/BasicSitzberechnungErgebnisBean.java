/*
 * BasicSitzberechnungErgebnisBean
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
import de.ivu.wahl.modell.SitzberechnungErgebnisModel;
import de.ivu.wahl.modell.impl.*;

/**
  * Implementation for the entity SitzberechnungErgebnis as BMP Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicSitzberechnungErgebnisBean extends BMPBeanBase implements EntityBean, SitzberechnungErgebnisModel {
   private static final Category LOGGER = Log4J.configure(BasicSitzberechnungErgebnisBean.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicSitzberechnungErgebnisBean.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   protected SitzberechnungErgebnisModel _details = null;

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param details Value Object containing data of the Bean
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(SitzberechnungErgebnisModel details) throws CreateException, EJBException {
      SitzberechnungErgebnisModel tmpDetails = details.copy();
      create(tmpDetails);
      // was saved at the insert and is not modified any longer
      resetModified(tmpDetails);
      return _details.getID_SitzberechnungErgebnis();
   }

   /**
     * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)
     *
     * @param id_SitzberechnungErgebnis key element of the type {@link String}
     * @param id_Ergebniseingang key element of the type {@link String}
     * @return Primary key of the entity
     * @throws CreateException Bean could not be instantiated 
     * @throws EJBException not used here but allows a cleanly derivative
     */
   public String ejbCreate(String id_SitzberechnungErgebnis, String id_Ergebniseingang) throws CreateException, EJBException {
      _details = (SitzberechnungErgebnisModel)createModel(id_SitzberechnungErgebnis);
      _details.setID_Ergebniseingang(id_Ergebniseingang);
      create(_details);
      return id_SitzberechnungErgebnis;
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
         return SitzberechnungErgebnisDBA.retrieveAllIDs();
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Listenkombination ID of the objects to be searched
     * @return  {@link Collection} of the found SitzberechnungErgebnis-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByListenkombination(String id_Listenkombination) throws IVUFinderException {
      try {
         return SitzberechnungErgebnisDBA.retrieveIDsByID_Listenkombination(id_Listenkombination);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Ergebniseingang ID of the objects to be searched
     * @return  {@link Collection} of the found SitzberechnungErgebnis-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByErgebniseingang(String id_Ergebniseingang) throws IVUFinderException {
      try {
         return SitzberechnungErgebnisDBA.retrieveIDsByID_Ergebniseingang(id_Ergebniseingang);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Liste ID of the objects to be searched
     * @return  {@link Collection} of the found SitzberechnungErgebnis-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByListe(String id_Liste) throws IVUFinderException {
      try {
         return SitzberechnungErgebnisDBA.retrieveIDsByID_Liste(id_Liste);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**  
     * Bean-supporting method by EJB standard.
     * Method for support of the navigation of the Bean model.
     *
     * @param id_Gruppe ID of the objects to be searched
     * @return  {@link Collection} of the found SitzberechnungErgebnis-entities
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found".
     */
   public Collection<String> ejbFindAllByGruppe(String id_Gruppe) throws IVUFinderException {
      try {
         return SitzberechnungErgebnisDBA.retrieveIDsByID_Gruppe(id_Gruppe);
      } catch (SQLException se) {
         throw new IVUFinderException (se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by verteilung.
     *
     * @param verteilung searching condition
     * @return  {@link Collection} of primary keys of the entities SitzberechnungErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByVerteilung(int verteilung) throws IVUFinderException {
      try {
         return SitzberechnungErgebnisDBA.retrieveIDsByVerteilung(verteilung);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by schrittnummer.
     *
     * @param schrittnummer searching condition
     * @return  {@link Collection} of primary keys of the entities SitzberechnungErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllBySchrittnummer(int schrittnummer) throws IVUFinderException {
      try {
         return SitzberechnungErgebnisDBA.retrieveIDsBySchrittnummer(schrittnummer);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by schritttyp.
     *
     * @param schritttyp searching condition
     * @return  {@link Collection} of primary keys of the entities SitzberechnungErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllBySchritttyp(int schritttyp) throws IVUFinderException {
      try {
         return SitzberechnungErgebnisDBA.retrieveIDsBySchritttyp(schritttyp);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by sitze.
     *
     * @param sitze searching condition
     * @return  {@link Collection} of primary keys of the entities SitzberechnungErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllBySitze(int sitze) throws IVUFinderException {
      try {
         return SitzberechnungErgebnisDBA.retrieveIDsBySitze(sitze);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by zaehler.
     *
     * @param zaehler searching condition
     * @return  {@link Collection} of primary keys of the entities SitzberechnungErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByZaehler(int zaehler) throws IVUFinderException {
      try {
         return SitzberechnungErgebnisDBA.retrieveIDsByZaehler(zaehler);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by nenner.
     *
     * @param nenner searching condition
     * @return  {@link Collection} of primary keys of the entities SitzberechnungErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByNenner(int nenner) throws IVUFinderException {
      try {
         return SitzberechnungErgebnisDBA.retrieveIDsByNenner(nenner);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by zaehlerVomNenner.
     *
     * @param zaehlerVomNenner searching condition
     * @return  {@link Collection} of primary keys of the entities SitzberechnungErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByZaehlerVomNenner(int zaehlerVomNenner) throws IVUFinderException {
      try {
         return SitzberechnungErgebnisDBA.retrieveIDsByZaehlerVomNenner(zaehlerVomNenner);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by nennerVomNenner.
     *
     * @param nennerVomNenner searching condition
     * @return  {@link Collection} of primary keys of the entities SitzberechnungErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByNennerVomNenner(int nennerVomNenner) throws IVUFinderException {
      try {
         return SitzberechnungErgebnisDBA.retrieveIDsByNennerVomNenner(nennerVomNenner);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by zaehlerVomRest.
     *
     * @param zaehlerVomRest searching condition
     * @return  {@link Collection} of primary keys of the entities SitzberechnungErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByZaehlerVomRest(int zaehlerVomRest) throws IVUFinderException {
      try {
         return SitzberechnungErgebnisDBA.retrieveIDsByZaehlerVomRest(zaehlerVomRest);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by nennerVomRest.
     *
     * @param nennerVomRest searching condition
     * @return  {@link Collection} of primary keys of the entities SitzberechnungErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByNennerVomRest(int nennerVomRest) throws IVUFinderException {
      try {
         return SitzberechnungErgebnisDBA.retrieveIDsByNennerVomRest(nennerVomRest);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   /**
     * Returns the set of entities of the type {@link SitzberechnungErgebnis}, filtered by losentscheid.
     *
     * @param losentscheid searching condition
     * @return  {@link Collection} of primary keys of the entities SitzberechnungErgebnis
     * @throws IVUFinderException if an error occurred while searching (does NOT mean "not found").
     */
   public Collection<String> ejbFindAllByLosentscheid(boolean losentscheid) throws IVUFinderException {
      try {
         return SitzberechnungErgebnisDBA.retrieveIDsByLosentscheid(losentscheid);
      } catch (SQLException se) {
         throw new IVUFinderException(se.getMessage(), se);
      }
   }

   // internal Bean methods

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param id_SitzberechnungErgebnis key element of the type {@link String}
     * @param id_Ergebniseingang key element of the type {@link String}
     */
   public void ejbPostCreate(String id_SitzberechnungErgebnis, String id_Ergebniseingang) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Bean-supporting method by EJB standard.
     *
     * @param details Value Object containing data of the Bean
     */
   public void ejbPostCreate(SitzberechnungErgebnisModel details) {
      // Subclass has to implement real functionality, if it needs some
   }

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   public SitzberechnungErgebnisModel getDetails() {
      return _details.copy();
   }

   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details Model object with new internal state
     */
   public void setDetails(SitzberechnungErgebnisModel details) {
      if (details instanceof SitzberechnungErgebnisModelImpl && _details instanceof SitzberechnungErgebnisModelImpl) {
         ((SitzberechnungErgebnisModelImpl)_details).copyFrom((SitzberechnungErgebnisModelImpl)details);
         checkRelations();
      } else {
         setID_Ergebniseingang(details.getID_Ergebniseingang());
         setID_Liste(details.getID_Liste());
         setID_Gruppe(details.getID_Gruppe());
         setID_Listenkombination(details.getID_Listenkombination());
         setVerteilung(details.getVerteilung());
         setSchrittnummer(details.getSchrittnummer());
         setSchritttyp(details.getSchritttyp());
         setSitze(details.getSitze());
         setZaehler(details.getZaehler());
         setNenner(details.getNenner());
         setZaehlerVomNenner(details.getZaehlerVomNenner());
         setNennerVomNenner(details.getNennerVomNenner());
         setZaehlerVomRest(details.getZaehlerVomRest());
         setNennerVomRest(details.getNennerVomRest());
         setLosentscheid(details.isLosentscheid());
      }
   }

   @Override
   protected Model setDetails (Model details) {
      _details = (SitzberechnungErgebnisModel)details;
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
   public SitzberechnungErgebnisModel copy() {
      return getDetails();
   }
   /**
     * Gets the value of id_SitzberechnungErgebnis in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute id_SitzberechnungErgebnis
     */
   public String getID_SitzberechnungErgebnis() {
      return _details.getID_SitzberechnungErgebnis();
   }

   /**
     * Sets the value of id_Ergebniseingang in the entity SitzberechnungErgebnis
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
     * Gets the value of id_Ergebniseingang in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _details.getID_Ergebniseingang();
   }

   /**
     * Sets the value of id_Liste in the entity SitzberechnungErgebnis
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
     * Gets the value of id_Liste in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute id_Liste
     */
   public String getID_Liste() {
      return _details.getID_Liste();
   }

   /**
     * Sets the value of id_Gruppe in the entity SitzberechnungErgebnis
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
     * Gets the value of id_Gruppe in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute id_Gruppe
     */
   public String getID_Gruppe() {
      return _details.getID_Gruppe();
   }

   /**
     * Sets the value of id_Listenkombination in the entity SitzberechnungErgebnis
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
     * Gets the value of id_Listenkombination in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute id_Listenkombination
     */
   public String getID_Listenkombination() {
      return _details.getID_Listenkombination();
   }

   /**
     * Sets the value of verteilung in the entity SitzberechnungErgebnis
     *
     * @param verteilung new value of the attribute verteilung
     */
   public void setVerteilung(int verteilung) {
      _details.setVerteilung(verteilung);
   }

   /**
     * Gets the value of verteilung in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute verteilung
     */
   public int getVerteilung() {
      return _details.getVerteilung();
   }

   /**
     * Sets the value of schrittnummer in the entity SitzberechnungErgebnis
     *
     * @param schrittnummer new value of the attribute schrittnummer
     */
   public void setSchrittnummer(int schrittnummer) {
      _details.setSchrittnummer(schrittnummer);
   }

   /**
     * Gets the value of schrittnummer in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute schrittnummer
     */
   public int getSchrittnummer() {
      return _details.getSchrittnummer();
   }

   /**
     * Sets the value of schritttyp in the entity SitzberechnungErgebnis
     *
     * @param schritttyp new value of the attribute schritttyp
     */
   public void setSchritttyp(int schritttyp) {
      _details.setSchritttyp(schritttyp);
   }

   /**
     * Gets the value of schritttyp in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute schritttyp
     */
   public int getSchritttyp() {
      return _details.getSchritttyp();
   }

   /**
     * Sets the value of sitze in the entity SitzberechnungErgebnis
     *
     * @param sitze new value of the attribute sitze
     */
   public void setSitze(int sitze) {
      _details.setSitze(sitze);
   }

   /**
     * Gets the value of sitze in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute sitze
     */
   public int getSitze() {
      return _details.getSitze();
   }

   /**
     * Sets the value of zaehler in the entity SitzberechnungErgebnis
     *
     * @param zaehler new value of the attribute zaehler
     */
   public void setZaehler(int zaehler) {
      _details.setZaehler(zaehler);
   }

   /**
     * Gets the value of zaehler in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute zaehler
     */
   public int getZaehler() {
      return _details.getZaehler();
   }

   /**
     * Sets the value of nenner in the entity SitzberechnungErgebnis
     *
     * @param nenner new value of the attribute nenner
     */
   public void setNenner(int nenner) {
      _details.setNenner(nenner);
   }

   /**
     * Gets the value of nenner in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute nenner
     */
   public int getNenner() {
      return _details.getNenner();
   }

   /**
     * Sets the value of zaehlerVomNenner in the entity SitzberechnungErgebnis
     *
     * @param zaehlerVomNenner new value of the attribute zaehlerVomNenner
     */
   public void setZaehlerVomNenner(int zaehlerVomNenner) {
      _details.setZaehlerVomNenner(zaehlerVomNenner);
   }

   /**
     * Gets the value of zaehlerVomNenner in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute zaehlerVomNenner
     */
   public int getZaehlerVomNenner() {
      return _details.getZaehlerVomNenner();
   }

   /**
     * Sets the value of nennerVomNenner in the entity SitzberechnungErgebnis
     *
     * @param nennerVomNenner new value of the attribute nennerVomNenner
     */
   public void setNennerVomNenner(int nennerVomNenner) {
      _details.setNennerVomNenner(nennerVomNenner);
   }

   /**
     * Gets the value of nennerVomNenner in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute nennerVomNenner
     */
   public int getNennerVomNenner() {
      return _details.getNennerVomNenner();
   }

   /**
     * Sets the value of zaehlerVomRest in the entity SitzberechnungErgebnis
     *
     * @param zaehlerVomRest new value of the attribute zaehlerVomRest
     */
   public void setZaehlerVomRest(int zaehlerVomRest) {
      _details.setZaehlerVomRest(zaehlerVomRest);
   }

   /**
     * Gets the value of zaehlerVomRest in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute zaehlerVomRest
     */
   public int getZaehlerVomRest() {
      return _details.getZaehlerVomRest();
   }

   /**
     * Sets the value of nennerVomRest in the entity SitzberechnungErgebnis
     *
     * @param nennerVomRest new value of the attribute nennerVomRest
     */
   public void setNennerVomRest(int nennerVomRest) {
      _details.setNennerVomRest(nennerVomRest);
   }

   /**
     * Gets the value of nennerVomRest in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute nennerVomRest
     */
   public int getNennerVomRest() {
      return _details.getNennerVomRest();
   }

   /**
     * Sets the value of losentscheid in the entity SitzberechnungErgebnis
     *
     * @param losentscheid new value of the attribute losentscheid
     */
   public void setLosentscheid(boolean losentscheid) {
      _details.setLosentscheid(losentscheid);
   }

   /**
     * Gets the value of losentscheid in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute losentscheid
     */
   public boolean isLosentscheid() {
      return _details.isLosentscheid();
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
         if (_details.getID_SitzberechnungErgebnis() != null) {
            string += "id_SitzberechnungErgebnis = " + _details.getID_SitzberechnungErgebnis(); //$NON-NLS-1$
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
         string +=  ", schrittnummer = " + _details.getSchrittnummer(); //$NON-NLS-1$
         string +=  ", schritttyp = " + _details.getSchritttyp(); //$NON-NLS-1$
         string +=  ", sitze = " + _details.getSitze(); //$NON-NLS-1$
         string +=  ", zaehler = " + _details.getZaehler(); //$NON-NLS-1$
         string +=  ", nenner = " + _details.getNenner(); //$NON-NLS-1$
         string +=  ", zaehlerVomNenner = " + _details.getZaehlerVomNenner(); //$NON-NLS-1$
         string +=  ", nennerVomNenner = " + _details.getNennerVomNenner(); //$NON-NLS-1$
         string +=  ", zaehlerVomRest = " + _details.getZaehlerVomRest(); //$NON-NLS-1$
         string +=  ", nennerVomRest = " + _details.getNennerVomRest(); //$NON-NLS-1$
         string +=  ", losentscheid = " + _details.isLosentscheid(); //$NON-NLS-1$
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }
}
