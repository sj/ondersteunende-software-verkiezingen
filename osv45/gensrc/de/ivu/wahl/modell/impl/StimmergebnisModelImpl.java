/*
 * StimmergebnisModelImpl
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.impl;

import java.io.Serializable;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.ejb.bmp.ModelImpl;
import de.ivu.wahl.modell.StimmergebnisModel;

/**
  * Model implementation for the entity Stimmergebnis.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class StimmergebnisModelImpl extends ModelImpl implements StimmergebnisModel, Serializable {
   private static final Category LOGGER = Log4J.configure(StimmergebnisModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(StimmergebnisModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public StimmergebnisModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Stimmergebnis key element of the type {@link String}
     */
   public StimmergebnisModelImpl(String id_Stimmergebnis) {
      setID_Stimmergebnis(id_Stimmergebnis);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(StimmergebnisModelImpl other) {
      setID_Ergebniseingang(other._id_Ergebniseingang);
      setID_Gebiet(other._id_Gebiet);
      setID_GruppeGebietsspezifisch(other._id_GruppeGebietsspezifisch);
      setID_Listenkandidatur(other._id_Listenkandidatur);
      setWahlergebnisart(other._wahlergebnisart);
      setStimmen(other._stimmen);
      setStimmart(other._stimmart);
   }

   /**
     * Type : VARCHAR Name : ID_Stimmergebnis
     */
   protected String _id_Stimmergebnis;

   /**
     * Sets the value of id_Stimmergebnis in the entity Stimmergebnis
     *
     * @param id_Stimmergebnis new value of the attribute id_Stimmergebnis
     */
   protected void setID_Stimmergebnis(String id_Stimmergebnis) {
      if (different(_id_Stimmergebnis, id_Stimmergebnis)) {
         _id_Stimmergebnis = checkLength(id_Stimmergebnis, StimmergebnisModel.ID_STIMMERGEBNIS_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Stimmergebnis in the entity Stimmergebnis
     *
     * @return value of the attribute id_Stimmergebnis
     */
   public String getID_Stimmergebnis() {
      return _id_Stimmergebnis;
   }

   /**
     * Type : VARCHAR Name : ID_Ergebniseingang
     */
   protected String _id_Ergebniseingang;

   /**
     * Sets the value of id_Ergebniseingang in the entity Stimmergebnis
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   public void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (different(_id_Ergebniseingang, id_Ergebniseingang)) {
         _id_Ergebniseingang = checkLength(id_Ergebniseingang, StimmergebnisModel.ID_ERGEBNISEINGANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Ergebniseingang in the entity Stimmergebnis
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _id_Ergebniseingang;
   }

   /**
     * Type : VARCHAR Name : ID_Gebiet
     */
   protected String _id_Gebiet;

   /**
     * Sets the value of id_Gebiet in the entity Stimmergebnis
     *
     * @param id_Gebiet new value of the attribute id_Gebiet
     */
   public void setID_Gebiet(String id_Gebiet) {
      if (different(_id_Gebiet, id_Gebiet)) {
         _id_Gebiet = checkLength(id_Gebiet, StimmergebnisModel.ID_GEBIET_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gebiet in the entity Stimmergebnis
     *
     * @return value of the attribute id_Gebiet
     */
   public String getID_Gebiet() {
      return _id_Gebiet;
   }

   /**
     * Type : VARCHAR Name : ID_GruppeGebietsspezifisch
     */
   protected String _id_GruppeGebietsspezifisch;

   /**
     * Sets the value of id_GruppeGebietsspezifisch in the entity Stimmergebnis
     *
     * @param id_GruppeGebietsspezifisch new value of the attribute id_GruppeGebietsspezifisch
     */
   public void setID_GruppeGebietsspezifisch(String id_GruppeGebietsspezifisch) {
      if (different(_id_GruppeGebietsspezifisch, id_GruppeGebietsspezifisch)) {
         _id_GruppeGebietsspezifisch = checkLength(id_GruppeGebietsspezifisch, StimmergebnisModel.ID_GRUPPEGEBIETSSPEZIFISCH_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_GruppeGebietsspezifisch in the entity Stimmergebnis
     *
     * @return value of the attribute id_GruppeGebietsspezifisch
     */
   public String getID_GruppeGebietsspezifisch() {
      return _id_GruppeGebietsspezifisch;
   }

   /**
     * Type : VARCHAR Name : ID_Listenkandidatur
     */
   protected String _id_Listenkandidatur;

   /**
     * Sets the value of id_Listenkandidatur in the entity Stimmergebnis
     *
     * @param id_Listenkandidatur new value of the attribute id_Listenkandidatur
     */
   public void setID_Listenkandidatur(String id_Listenkandidatur) {
      if (different(_id_Listenkandidatur, id_Listenkandidatur)) {
         _id_Listenkandidatur = checkLength(id_Listenkandidatur, StimmergebnisModel.ID_LISTENKANDIDATUR_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Listenkandidatur in the entity Stimmergebnis
     *
     * @return value of the attribute id_Listenkandidatur
     */
   public String getID_Listenkandidatur() {
      return _id_Listenkandidatur;
   }

   /**
     * Type : INT Name : Wahlergebnisart
     */
   protected int _wahlergebnisart;

   /**
     * Sets the value of wahlergebnisart in the entity Stimmergebnis
     *
     * @param wahlergebnisart new value of the attribute wahlergebnisart
     */
   public void setWahlergebnisart(int wahlergebnisart) {
      if (_wahlergebnisart != wahlergebnisart) {
         _wahlergebnisart = wahlergebnisart;
         setModified();
      }
   }

   /**
     * Gets the value of wahlergebnisart in the entity Stimmergebnis
     *
     * @return value of the attribute wahlergebnisart
     */
   public int getWahlergebnisart() {
      return _wahlergebnisart;
   }

   /**
     * Type : INT Name : Stimmen
     */
   protected int _stimmen;

   /**
     * Sets the value of stimmen in the entity Stimmergebnis
     *
     * @param stimmen new value of the attribute stimmen
     */
   public void setStimmen(int stimmen) {
      if (_stimmen != stimmen) {
         _stimmen = stimmen;
         setModified();
      }
   }

   /**
     * Gets the value of stimmen in the entity Stimmergebnis
     *
     * @return value of the attribute stimmen
     */
   public int getStimmen() {
      return _stimmen;
   }

   /**
     * Type : INT Name : Stimmart
     */
   protected int _stimmart;

   /**
     * Sets the value of stimmart in the entity Stimmergebnis
     *
     * @param stimmart new value of the attribute stimmart
     */
   public void setStimmart(int stimmart) {
      if (_stimmart != stimmart) {
         _stimmart = stimmart;
         setModified();
      }
   }

   /**
     * Gets the value of stimmart in the entity Stimmergebnis
     *
     * @return value of the attribute stimmart
     */
   public int getStimmart() {
      return _stimmart;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Stimmergebnis.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof StimmergebnisModelImpl) {
         StimmergebnisModelImpl other = (StimmergebnisModelImpl)object;
         return _id_Stimmergebnis.equals(other._id_Stimmergebnis);
      } else {
         return false;
      }
   }

   /**
     * Overwrites the toString method in Object
     *
     * @return string representation of the current state as listing of the attributes and their values
     */
   @Override
   public String toString() {
      try {
         String string = "[" + getClass().getName()+ ": "; //$NON-NLS-1$ //$NON-NLS-2$
         if (getID_Stimmergebnis() != null) {
            string += "id_Stimmergebnis = " + getID_Stimmergebnis(); //$NON-NLS-1$
         }
         if (getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (getID_Gebiet() != null) {
            string +=  ", id_Gebiet = " + getID_Gebiet(); //$NON-NLS-1$
         }
         if (getID_GruppeGebietsspezifisch() != null) {
            string +=  ", id_GruppeGebietsspezifisch = " + getID_GruppeGebietsspezifisch(); //$NON-NLS-1$
         }
         if (getID_Listenkandidatur() != null) {
            string +=  ", id_Listenkandidatur = " + getID_Listenkandidatur(); //$NON-NLS-1$
         }
         string +=  ", wahlergebnisart = " + getWahlergebnisart(); //$NON-NLS-1$
         string +=  ", stimmen = " + getStimmen(); //$NON-NLS-1$
         string +=  ", stimmart = " + getStimmart(); //$NON-NLS-1$
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }


   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public StimmergebnisModel copy() {
      return (StimmergebnisModel)clone();
   }
}
