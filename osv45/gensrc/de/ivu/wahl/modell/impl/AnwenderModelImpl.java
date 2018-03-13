/*
 * AnwenderModelImpl
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
import de.ivu.wahl.modell.AnwenderModel;

/**
  * Model implementation for the entity Anwender.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class AnwenderModelImpl extends ModelImpl implements AnwenderModel, Serializable {
   private static final Category LOGGER = Log4J.configure(AnwenderModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(AnwenderModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public AnwenderModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Anwender key element of the type {@link String}
     */
   public AnwenderModelImpl(String id_Anwender) {
      setID_Anwender(id_Anwender);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(AnwenderModelImpl other) {
      setID_Gebiet(other._id_Gebiet);
      setName(other._name);
      setAnwendername(other._anwendername);
      setPasswordHash(other._passwordHash);
      setSalt(other._salt);
      setFehlversucheAnmeldung(other._fehlversucheAnmeldung);
      setLetzterZugriff(other._letzterZugriff);
   }

   /**
     * Type : VARCHAR Name : ID_Anwender
     */
   protected String _id_Anwender;

   /**
     * Sets the value of id_Anwender in the entity Anwender
     *
     * @param id_Anwender new value of the attribute id_Anwender
     */
   protected void setID_Anwender(String id_Anwender) {
      if (different(_id_Anwender, id_Anwender)) {
         _id_Anwender = checkLength(id_Anwender, AnwenderModel.ID_ANWENDER_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Anwender in the entity Anwender
     *
     * @return value of the attribute id_Anwender
     */
   public String getID_Anwender() {
      return _id_Anwender;
   }

   /**
     * Type : VARCHAR Name : ID_Gebiet
     */
   protected String _id_Gebiet;

   /**
     * Sets the value of id_Gebiet in the entity Anwender
     *
     * @param id_Gebiet new value of the attribute id_Gebiet
     */
   public void setID_Gebiet(String id_Gebiet) {
      if (different(_id_Gebiet, id_Gebiet)) {
         _id_Gebiet = checkLength(id_Gebiet, AnwenderModel.ID_GEBIET_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gebiet in the entity Anwender
     *
     * @return value of the attribute id_Gebiet
     */
   public String getID_Gebiet() {
      return _id_Gebiet;
   }

   /**
     * Type : VARCHAR Name : Name
     */
   protected String _name;

   /**
     * Sets the value of name in the entity Anwender
     *
     * @param name new value of the attribute name
     */
   public void setName(String name) {
      if (different(_name, name)) {
         _name = checkLength(name, AnwenderModel.NAME_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of name in the entity Anwender
     *
     * @return value of the attribute name
     */
   public String getName() {
      return _name;
   }

   /**
     * Type : VARCHAR Name : Anwendername
     */
   protected String _anwendername;

   /**
     * Sets the value of anwendername in the entity Anwender
     *
     * @param anwendername new value of the attribute anwendername
     */
   public void setAnwendername(String anwendername) {
      if (different(_anwendername, anwendername)) {
         _anwendername = checkLength(anwendername, AnwenderModel.ANWENDERNAME_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of anwendername in the entity Anwender
     *
     * @return value of the attribute anwendername
     */
   public String getAnwendername() {
      return _anwendername;
   }

   /**
     * Type : VARCHAR Name : PasswordHash
     */
   protected String _passwordHash;

   /**
     * Sets the value of passwordHash in the entity Anwender
     *
     * @param passwordHash new value of the attribute passwordHash
     */
   public void setPasswordHash(String passwordHash) {
      if (different(_passwordHash, passwordHash)) {
         _passwordHash = checkLength(passwordHash, AnwenderModel.PASSWORDHASH_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of passwordHash in the entity Anwender
     *
     * @return value of the attribute passwordHash
     */
   public String getPasswordHash() {
      return _passwordHash;
   }

   /**
     * Type : VARCHAR Name : Salt
     */
   protected String _salt;

   /**
     * Sets the value of salt in the entity Anwender
     *
     * @param salt new value of the attribute salt
     */
   public void setSalt(String salt) {
      if (different(_salt, salt)) {
         _salt = checkLength(salt, AnwenderModel.SALT_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of salt in the entity Anwender
     *
     * @return value of the attribute salt
     */
   public String getSalt() {
      return _salt;
   }

   /**
     * Type : INT Name : FehlversucheAnmeldung
     */
   protected int _fehlversucheAnmeldung;

   /**
     * Sets the value of fehlversucheAnmeldung in the entity Anwender
     *
     * @param fehlversucheAnmeldung new value of the attribute fehlversucheAnmeldung
     */
   public void setFehlversucheAnmeldung(int fehlversucheAnmeldung) {
      if (_fehlversucheAnmeldung != fehlversucheAnmeldung) {
         _fehlversucheAnmeldung = fehlversucheAnmeldung;
         setModified();
      }
   }

   /**
     * Gets the value of fehlversucheAnmeldung in the entity Anwender
     *
     * @return value of the attribute fehlversucheAnmeldung
     */
   public int getFehlversucheAnmeldung() {
      return _fehlversucheAnmeldung;
   }

   /**
     * Type : TIMESTAMP Name : LetzterZugriff
     */
   protected Timestamp _letzterZugriff;

   /**
     * Sets the value of letzterZugriff in the entity Anwender
     *
     * @param letzterZugriff new value of the attribute letzterZugriff
     */
   public void setLetzterZugriff(Timestamp letzterZugriff) {
      if (different(_letzterZugriff, letzterZugriff)) {
         _letzterZugriff = letzterZugriff;
         setModified();
      }
   }

   /**
     * Gets the value of letzterZugriff in the entity Anwender
     *
     * @return value of the attribute letzterZugriff
     */
   public Timestamp getLetzterZugriff() {
      return _letzterZugriff;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Anwender.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof AnwenderModelImpl) {
         AnwenderModelImpl other = (AnwenderModelImpl)object;
         return _id_Anwender.equals(other._id_Anwender);
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
         if (getID_Anwender() != null) {
            string += "id_Anwender = " + getID_Anwender(); //$NON-NLS-1$
         }
         if (getID_Gebiet() != null) {
            string +=  ", id_Gebiet = " + getID_Gebiet(); //$NON-NLS-1$
         }
         if (getName() != null) {
            string +=  ", name = " + getName(); //$NON-NLS-1$
         }
         if (getAnwendername() != null) {
            string +=  ", anwendername = " + getAnwendername(); //$NON-NLS-1$
         }
         if (getPasswordHash() != null) {
            string +=  ", passwordHash = " + getPasswordHash(); //$NON-NLS-1$
         }
         if (getSalt() != null) {
            string +=  ", salt = " + getSalt(); //$NON-NLS-1$
         }
         string +=  ", fehlversucheAnmeldung = " + getFehlversucheAnmeldung(); //$NON-NLS-1$
         if (getLetzterZugriff() != null) {
            string +=  ", letzterZugriff = " + getLetzterZugriff(); //$NON-NLS-1$
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
   public AnwenderModel copy() {
      return (AnwenderModel)clone();
   }
}
