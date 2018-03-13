/*
 * ErgebniseingangModelImpl
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.impl;

import java.sql.Timestamp;
import java.io.Serializable;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.ejb.bmp.ModelImpl;
import de.ivu.wahl.modell.ErgebniseingangModel;

/**
  * Model implementation for the entity Ergebniseingang.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class ErgebniseingangModelImpl extends ModelImpl implements ErgebniseingangModel, Serializable {
   private static final Category LOGGER = Log4J.configure(ErgebniseingangModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(ErgebniseingangModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public ErgebniseingangModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Ergebniseingang key element of the type {@link String}
     */
   public ErgebniseingangModelImpl(String id_Ergebniseingang) {
      setID_Ergebniseingang(id_Ergebniseingang);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(ErgebniseingangModelImpl other) {
      setID_Erfassungseinheit(other._id_Erfassungseinheit);
      setID_Wahl(other._id_Wahl);
      setAnwenderName(other._anwenderName);
      setZeitstempel(other._zeitstempel);
      setHerkunft(other._herkunft);
      setWahlergebnisart(other._wahlergebnisart);
      setUnterschiedeVorhanden(other._unterschiedeVorhanden);
      setStatus(other._status);
      setErgebnisHash(other._ergebnisHash);
      setFehlermeldung(other._fehlermeldung);
   }

   /**
     * Type : VARCHAR Name : ID_Ergebniseingang
     */
   protected String _id_Ergebniseingang;

   /**
     * Sets the value of id_Ergebniseingang in the entity Ergebniseingang
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   protected void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (different(_id_Ergebniseingang, id_Ergebniseingang)) {
         _id_Ergebniseingang = checkLength(id_Ergebniseingang, ErgebniseingangModel.ID_ERGEBNISEINGANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Ergebniseingang in the entity Ergebniseingang
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _id_Ergebniseingang;
   }

   /**
     * Type : VARCHAR Name : ID_Erfassungseinheit
     */
   protected String _id_Erfassungseinheit;

   /**
     * Sets the value of id_Erfassungseinheit in the entity Ergebniseingang
     *
     * @param id_Erfassungseinheit new value of the attribute id_Erfassungseinheit
     */
   public void setID_Erfassungseinheit(String id_Erfassungseinheit) {
      if (different(_id_Erfassungseinheit, id_Erfassungseinheit)) {
         _id_Erfassungseinheit = checkLength(id_Erfassungseinheit, ErgebniseingangModel.ID_ERFASSUNGSEINHEIT_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Erfassungseinheit in the entity Ergebniseingang
     *
     * @return value of the attribute id_Erfassungseinheit
     */
   public String getID_Erfassungseinheit() {
      return _id_Erfassungseinheit;
   }

   /**
     * Type : VARCHAR Name : ID_Wahl
     */
   protected String _id_Wahl;

   /**
     * Sets the value of id_Wahl in the entity Ergebniseingang
     *
     * @param id_Wahl new value of the attribute id_Wahl
     */
   public void setID_Wahl(String id_Wahl) {
      if (different(_id_Wahl, id_Wahl)) {
         _id_Wahl = checkLength(id_Wahl, ErgebniseingangModel.ID_WAHL_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Wahl in the entity Ergebniseingang
     *
     * @return value of the attribute id_Wahl
     */
   public String getID_Wahl() {
      return _id_Wahl;
   }

   /**
     * Type : VARCHAR Name : AnwenderName
     */
   protected String _anwenderName;

   /**
     * Sets the value of anwenderName in the entity Ergebniseingang
     *
     * @param anwenderName new value of the attribute anwenderName
     */
   public void setAnwenderName(String anwenderName) {
      if (different(_anwenderName, anwenderName)) {
         _anwenderName = checkLength(anwenderName, ErgebniseingangModel.ANWENDERNAME_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of anwenderName in the entity Ergebniseingang
     *
     * @return value of the attribute anwenderName
     */
   public String getAnwenderName() {
      return _anwenderName;
   }

   /**
     * Type : TIMESTAMP Name : Zeitstempel
     */
   protected Timestamp _zeitstempel;

   /**
     * Sets the value of zeitstempel in the entity Ergebniseingang
     *
     * @param zeitstempel new value of the attribute zeitstempel
     */
   public void setZeitstempel(Timestamp zeitstempel) {
      if (different(_zeitstempel, zeitstempel)) {
         _zeitstempel = zeitstempel;
         setModified();
      }
   }

   /**
     * Gets the value of zeitstempel in the entity Ergebniseingang
     *
     * @return value of the attribute zeitstempel
     */
   public Timestamp getZeitstempel() {
      return _zeitstempel;
   }

   /**
     * Type : INT Name : Herkunft
     */
   protected int _herkunft;

   /**
     * Sets the value of herkunft in the entity Ergebniseingang
     *
     * @param herkunft new value of the attribute herkunft
     */
   public void setHerkunft(int herkunft) {
      if (_herkunft != herkunft) {
         _herkunft = herkunft;
         setModified();
      }
   }

   /**
     * Gets the value of herkunft in the entity Ergebniseingang
     *
     * @return value of the attribute herkunft
     */
   public int getHerkunft() {
      return _herkunft;
   }

   /**
     * Type : INT Name : Wahlergebnisart
     */
   protected int _wahlergebnisart;

   /**
     * Sets the value of wahlergebnisart in the entity Ergebniseingang
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
     * Gets the value of wahlergebnisart in the entity Ergebniseingang
     *
     * @return value of the attribute wahlergebnisart
     */
   public int getWahlergebnisart() {
      return _wahlergebnisart;
   }

   /**
     * Type : INT Name : UnterschiedeVorhanden
     */
   protected int _unterschiedeVorhanden;

   /**
     * Sets the value of unterschiedeVorhanden in the entity Ergebniseingang
     *
     * @param unterschiedeVorhanden new value of the attribute unterschiedeVorhanden
     */
   public void setUnterschiedeVorhanden(int unterschiedeVorhanden) {
      if (_unterschiedeVorhanden != unterschiedeVorhanden) {
         _unterschiedeVorhanden = unterschiedeVorhanden;
         setModified();
      }
   }

   /**
     * Gets the value of unterschiedeVorhanden in the entity Ergebniseingang
     *
     * @return value of the attribute unterschiedeVorhanden
     */
   public int getUnterschiedeVorhanden() {
      return _unterschiedeVorhanden;
   }

   /**
     * Type : INT Name : Status
     */
   protected int _status;

   /**
     * Sets the value of status in the entity Ergebniseingang
     *
     * @param status new value of the attribute status
     */
   public void setStatus(int status) {
      if (_status != status) {
         _status = status;
         setModified();
      }
   }

   /**
     * Gets the value of status in the entity Ergebniseingang
     *
     * @return value of the attribute status
     */
   public int getStatus() {
      return _status;
   }

   /**
     * Type : VARCHAR Name : ErgebnisHash
     */
   protected String _ergebnisHash;

   /**
     * Sets the value of ergebnisHash in the entity Ergebniseingang
     *
     * @param ergebnisHash new value of the attribute ergebnisHash
     */
   public void setErgebnisHash(String ergebnisHash) {
      if (different(_ergebnisHash, ergebnisHash)) {
         _ergebnisHash = checkLength(ergebnisHash, ErgebniseingangModel.ERGEBNISHASH_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of ergebnisHash in the entity Ergebniseingang
     *
     * @return value of the attribute ergebnisHash
     */
   public String getErgebnisHash() {
      return _ergebnisHash;
   }

   /**
     * Type : TEXT Name : Fehlermeldung
     */
   protected String _fehlermeldung;

   /**
     * Sets the value of fehlermeldung in the entity Ergebniseingang
     *
     * @param fehlermeldung new value of the attribute fehlermeldung
     */
   public void setFehlermeldung(String fehlermeldung) {
      if (different(_fehlermeldung, fehlermeldung)) {
         _fehlermeldung = checkLength(fehlermeldung, ErgebniseingangModel.FEHLERMELDUNG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of fehlermeldung in the entity Ergebniseingang
     *
     * @return value of the attribute fehlermeldung
     */
   public String getFehlermeldung() {
      return _fehlermeldung;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Ergebniseingang.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof ErgebniseingangModelImpl) {
         ErgebniseingangModelImpl other = (ErgebniseingangModelImpl)object;
         return _id_Ergebniseingang.equals(other._id_Ergebniseingang);
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
         if (getID_Ergebniseingang() != null) {
            string += "id_Ergebniseingang = " + getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (getID_Erfassungseinheit() != null) {
            string +=  ", id_Erfassungseinheit = " + getID_Erfassungseinheit(); //$NON-NLS-1$
         }
         if (getID_Wahl() != null) {
            string +=  ", id_Wahl = " + getID_Wahl(); //$NON-NLS-1$
         }
         if (getAnwenderName() != null) {
            string +=  ", anwenderName = " + getAnwenderName(); //$NON-NLS-1$
         }
         if (getZeitstempel() != null) {
            string +=  ", zeitstempel = " + getZeitstempel(); //$NON-NLS-1$
         }
         string +=  ", herkunft = " + getHerkunft(); //$NON-NLS-1$
         string +=  ", wahlergebnisart = " + getWahlergebnisart(); //$NON-NLS-1$
         string +=  ", unterschiedeVorhanden = " + getUnterschiedeVorhanden(); //$NON-NLS-1$
         string +=  ", status = " + getStatus(); //$NON-NLS-1$
         if (getErgebnisHash() != null) {
            string +=  ", ergebnisHash = " + getErgebnisHash(); //$NON-NLS-1$
         }
         if (getFehlermeldung() != null) {
            string +=  ", fehlermeldung = " + getFehlermeldung(); //$NON-NLS-1$
         }
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
   public ErgebniseingangModel copy() {
      return (ErgebniseingangModel)clone();
   }
}
