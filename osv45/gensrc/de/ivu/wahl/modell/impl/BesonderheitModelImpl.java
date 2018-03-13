/*
 * BesonderheitModelImpl
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
import de.ivu.wahl.modell.BesonderheitModel;

/**
  * Model implementation for the entity Besonderheit.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class BesonderheitModelImpl extends ModelImpl implements BesonderheitModel, Serializable {
   private static final Category LOGGER = Log4J.configure(BesonderheitModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BesonderheitModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public BesonderheitModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Besonderheit key element of the type {@link String}
     */
   public BesonderheitModelImpl(String id_Besonderheit) {
      setID_Besonderheit(id_Besonderheit);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(BesonderheitModelImpl other) {
      setID_Ergebniseingang(other._id_Ergebniseingang);
      setID_UebergeordneteBesonderheit(other._id_UebergeordneteBesonderheit);
      setID_Listenkombination(other._id_Listenkombination);
      setID_Gruppe(other._id_Gruppe);
      setID_Liste(other._id_Liste);
      setID_Personendaten(other._id_Personendaten);
      setBesonderheitart(other._besonderheitart);
      setAnzahl(other._anzahl);
      setText(other._text);
      setNummer(other._nummer);
   }

   /**
     * Type : VARCHAR Name : ID_Besonderheit
     */
   protected String _id_Besonderheit;

   /**
     * Sets the value of id_Besonderheit in the entity Besonderheit
     *
     * @param id_Besonderheit new value of the attribute id_Besonderheit
     */
   protected void setID_Besonderheit(String id_Besonderheit) {
      if (different(_id_Besonderheit, id_Besonderheit)) {
         _id_Besonderheit = checkLength(id_Besonderheit, BesonderheitModel.ID_BESONDERHEIT_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Besonderheit in the entity Besonderheit
     *
     * @return value of the attribute id_Besonderheit
     */
   public String getID_Besonderheit() {
      return _id_Besonderheit;
   }

   /**
     * Type : VARCHAR Name : ID_Ergebniseingang
     */
   protected String _id_Ergebniseingang;

   /**
     * Sets the value of id_Ergebniseingang in the entity Besonderheit
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   public void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (different(_id_Ergebniseingang, id_Ergebniseingang)) {
         _id_Ergebniseingang = checkLength(id_Ergebniseingang, BesonderheitModel.ID_ERGEBNISEINGANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Ergebniseingang in the entity Besonderheit
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _id_Ergebniseingang;
   }

   /**
     * Type : VARCHAR Name : ID_UebergeordneteBesonderheit
     */
   protected String _id_UebergeordneteBesonderheit;

   /**
     * Sets the value of id_UebergeordneteBesonderheit in the entity Besonderheit
     *
     * @param id_UebergeordneteBesonderheit new value of the attribute id_UebergeordneteBesonderheit
     */
   public void setID_UebergeordneteBesonderheit(String id_UebergeordneteBesonderheit) {
      if (different(_id_UebergeordneteBesonderheit, id_UebergeordneteBesonderheit)) {
         _id_UebergeordneteBesonderheit = checkLength(id_UebergeordneteBesonderheit, BesonderheitModel.ID_UEBERGEORDNETEBESONDERHEIT_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_UebergeordneteBesonderheit in the entity Besonderheit
     *
     * @return value of the attribute id_UebergeordneteBesonderheit
     */
   public String getID_UebergeordneteBesonderheit() {
      return _id_UebergeordneteBesonderheit;
   }

   /**
     * Type : VARCHAR Name : ID_Listenkombination
     */
   protected String _id_Listenkombination;

   /**
     * Sets the value of id_Listenkombination in the entity Besonderheit
     *
     * @param id_Listenkombination new value of the attribute id_Listenkombination
     */
   public void setID_Listenkombination(String id_Listenkombination) {
      if (different(_id_Listenkombination, id_Listenkombination)) {
         _id_Listenkombination = checkLength(id_Listenkombination, BesonderheitModel.ID_LISTENKOMBINATION_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Listenkombination in the entity Besonderheit
     *
     * @return value of the attribute id_Listenkombination
     */
   public String getID_Listenkombination() {
      return _id_Listenkombination;
   }

   /**
     * Type : VARCHAR Name : ID_Gruppe
     */
   protected String _id_Gruppe;

   /**
     * Sets the value of id_Gruppe in the entity Besonderheit
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   public void setID_Gruppe(String id_Gruppe) {
      if (different(_id_Gruppe, id_Gruppe)) {
         _id_Gruppe = checkLength(id_Gruppe, BesonderheitModel.ID_GRUPPE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gruppe in the entity Besonderheit
     *
     * @return value of the attribute id_Gruppe
     */
   public String getID_Gruppe() {
      return _id_Gruppe;
   }

   /**
     * Type : VARCHAR Name : ID_Liste
     */
   protected String _id_Liste;

   /**
     * Sets the value of id_Liste in the entity Besonderheit
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   public void setID_Liste(String id_Liste) {
      if (different(_id_Liste, id_Liste)) {
         _id_Liste = checkLength(id_Liste, BesonderheitModel.ID_LISTE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Liste in the entity Besonderheit
     *
     * @return value of the attribute id_Liste
     */
   public String getID_Liste() {
      return _id_Liste;
   }

   /**
     * Type : VARCHAR Name : ID_Personendaten
     */
   protected String _id_Personendaten;

   /**
     * Sets the value of id_Personendaten in the entity Besonderheit
     *
     * @param id_Personendaten new value of the attribute id_Personendaten
     */
   public void setID_Personendaten(String id_Personendaten) {
      if (different(_id_Personendaten, id_Personendaten)) {
         _id_Personendaten = checkLength(id_Personendaten, BesonderheitModel.ID_PERSONENDATEN_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Personendaten in the entity Besonderheit
     *
     * @return value of the attribute id_Personendaten
     */
   public String getID_Personendaten() {
      return _id_Personendaten;
   }

   /**
     * Type : INT Name : Besonderheitart
     */
   protected int _besonderheitart;

   /**
     * Sets the value of besonderheitart in the entity Besonderheit
     *
     * @param besonderheitart new value of the attribute besonderheitart
     */
   public void setBesonderheitart(int besonderheitart) {
      if (_besonderheitart != besonderheitart) {
         _besonderheitart = besonderheitart;
         setModified();
      }
   }

   /**
     * Gets the value of besonderheitart in the entity Besonderheit
     *
     * @return value of the attribute besonderheitart
     */
   public int getBesonderheitart() {
      return _besonderheitart;
   }

   /**
     * Type : INT Name : Anzahl
     */
   protected int _anzahl;

   /**
     * Sets the value of anzahl in the entity Besonderheit
     *
     * @param anzahl new value of the attribute anzahl
     */
   public void setAnzahl(int anzahl) {
      if (_anzahl != anzahl) {
         _anzahl = anzahl;
         setModified();
      }
   }

   /**
     * Gets the value of anzahl in the entity Besonderheit
     *
     * @return value of the attribute anzahl
     */
   public int getAnzahl() {
      return _anzahl;
   }

   /**
     * Type : VARCHAR Name : Text
     */
   protected String _text;

   /**
     * Sets the value of text in the entity Besonderheit
     *
     * @param text new value of the attribute text
     */
   public void setText(String text) {
      if (different(_text, text)) {
         _text = checkLength(text, BesonderheitModel.TEXT_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of text in the entity Besonderheit
     *
     * @return value of the attribute text
     */
   public String getText() {
      return _text;
   }

   /**
     * Type : INT Name : Nummer
     */
   protected int _nummer;

   /**
     * Sets the value of nummer in the entity Besonderheit
     *
     * @param nummer new value of the attribute nummer
     */
   public void setNummer(int nummer) {
      if (_nummer != nummer) {
         _nummer = nummer;
         setModified();
      }
   }

   /**
     * Gets the value of nummer in the entity Besonderheit
     *
     * @return value of the attribute nummer
     */
   public int getNummer() {
      return _nummer;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Besonderheit.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof BesonderheitModelImpl) {
         BesonderheitModelImpl other = (BesonderheitModelImpl)object;
         return _id_Besonderheit.equals(other._id_Besonderheit);
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
         if (getID_Besonderheit() != null) {
            string += "id_Besonderheit = " + getID_Besonderheit(); //$NON-NLS-1$
         }
         if (getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (getID_UebergeordneteBesonderheit() != null) {
            string +=  ", id_UebergeordneteBesonderheit = " + getID_UebergeordneteBesonderheit(); //$NON-NLS-1$
         }
         if (getID_Listenkombination() != null) {
            string +=  ", id_Listenkombination = " + getID_Listenkombination(); //$NON-NLS-1$
         }
         if (getID_Gruppe() != null) {
            string +=  ", id_Gruppe = " + getID_Gruppe(); //$NON-NLS-1$
         }
         if (getID_Liste() != null) {
            string +=  ", id_Liste = " + getID_Liste(); //$NON-NLS-1$
         }
         if (getID_Personendaten() != null) {
            string +=  ", id_Personendaten = " + getID_Personendaten(); //$NON-NLS-1$
         }
         string +=  ", besonderheitart = " + getBesonderheitart(); //$NON-NLS-1$
         string +=  ", anzahl = " + getAnzahl(); //$NON-NLS-1$
         if (getText() != null) {
            string +=  ", text = " + getText(); //$NON-NLS-1$
         }
         string +=  ", nummer = " + getNummer(); //$NON-NLS-1$
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
   public BesonderheitModel copy() {
      return (BesonderheitModel)clone();
   }
}
