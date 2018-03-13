/*
 * BasicGebietModelImpl
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
import de.ivu.wahl.modell.GebietModel;

/**
  * Model implementation for the entity Gebiet.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public abstract class BasicGebietModelImpl extends ModelImpl implements GebietModel, Serializable {
   private static final Category LOGGER = Log4J.configure(BasicGebietModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicGebietModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public BasicGebietModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Gebiet key element of the type {@link String}
     */
   public BasicGebietModelImpl(String id_Gebiet) {
      setID_Gebiet(id_Gebiet);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(BasicGebietModelImpl other) {
      setID_UebergeordnetesGebiet(other._id_UebergeordnetesGebiet);
      setID_Wahl(other._id_Wahl);
      setID_LetzterEingang(other._id_LetzterEingang);
      setErfassungseinheit(other._erfassungseinheit);
      setWahleinheit(other._wahleinheit);
      setGebietsart(other._gebietsart);
      setNummer(other._nummer);
      setRoemisch(other._roemisch);
      setName(other._name);
      setKuerzel(other._kuerzel);
      setPosition(other._position);
      setWahlberechtigte(other._wahlberechtigte);
      setGUIEingabeErlaubt(other._gUIEingabeErlaubt);
      setPostalvote(other._postalvote);
      setVoteValue(other._voteValue);
      setZipcode(other._zipcode);
   }

   /**
     * Type : VARCHAR Name : ID_Gebiet
     */
   protected String _id_Gebiet;

   /**
     * Sets the value of id_Gebiet in the entity Gebiet
     *
     * @param id_Gebiet new value of the attribute id_Gebiet
     */
   protected void setID_Gebiet(String id_Gebiet) {
      if (different(_id_Gebiet, id_Gebiet)) {
         _id_Gebiet = checkLength(id_Gebiet, GebietModel.ID_GEBIET_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gebiet in the entity Gebiet
     *
     * @return value of the attribute id_Gebiet
     */
   public String getID_Gebiet() {
      return _id_Gebiet;
   }

   /**
     * Type : VARCHAR Name : ID_UebergeordnetesGebiet
     */
   protected String _id_UebergeordnetesGebiet;

   /**
     * Sets the value of id_UebergeordnetesGebiet in the entity Gebiet
     *
     * @param id_UebergeordnetesGebiet new value of the attribute id_UebergeordnetesGebiet
     */
   public void setID_UebergeordnetesGebiet(String id_UebergeordnetesGebiet) {
      if (different(_id_UebergeordnetesGebiet, id_UebergeordnetesGebiet)) {
         _id_UebergeordnetesGebiet = checkLength(id_UebergeordnetesGebiet, GebietModel.ID_UEBERGEORDNETESGEBIET_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_UebergeordnetesGebiet in the entity Gebiet
     *
     * @return value of the attribute id_UebergeordnetesGebiet
     */
   public String getID_UebergeordnetesGebiet() {
      return _id_UebergeordnetesGebiet;
   }

   /**
     * Type : VARCHAR Name : ID_Wahl
     */
   protected String _id_Wahl;

   /**
     * Sets the value of id_Wahl in the entity Gebiet
     *
     * @param id_Wahl new value of the attribute id_Wahl
     */
   public void setID_Wahl(String id_Wahl) {
      if (different(_id_Wahl, id_Wahl)) {
         _id_Wahl = checkLength(id_Wahl, GebietModel.ID_WAHL_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Wahl in the entity Gebiet
     *
     * @return value of the attribute id_Wahl
     */
   public String getID_Wahl() {
      return _id_Wahl;
   }

   /**
     * Type : VARCHAR Name : ID_LetzterEingang
     */
   protected String _id_LetzterEingang;

   /**
     * Sets the value of id_LetzterEingang in the entity Gebiet
     *
     * @param id_LetzterEingang new value of the attribute id_LetzterEingang
     */
   public void setID_LetzterEingang(String id_LetzterEingang) {
      if (different(_id_LetzterEingang, id_LetzterEingang)) {
         _id_LetzterEingang = checkLength(id_LetzterEingang, GebietModel.ID_LETZTEREINGANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_LetzterEingang in the entity Gebiet
     *
     * @return value of the attribute id_LetzterEingang
     */
   public String getID_LetzterEingang() {
      return _id_LetzterEingang;
   }

   /**
     * Type : SMALLINT Name : Erfassungseinheit
     */
   protected boolean _erfassungseinheit;

   /**
     * Sets the value of erfassungseinheit in the entity Gebiet
     *
     * @param erfassungseinheit new value of the attribute erfassungseinheit
     */
   public void setErfassungseinheit(boolean erfassungseinheit) {
      if (_erfassungseinheit != erfassungseinheit) {
         _erfassungseinheit = erfassungseinheit;
         setModified();
      }
   }

   /**
     * Gets the value of erfassungseinheit in the entity Gebiet
     *
     * @return value of the attribute erfassungseinheit
     */
   public boolean isErfassungseinheit() {
      return _erfassungseinheit;
   }

   /**
     * Type : SMALLINT Name : Wahleinheit
     */
   protected boolean _wahleinheit;

   /**
     * Sets the value of wahleinheit in the entity Gebiet
     *
     * @param wahleinheit new value of the attribute wahleinheit
     */
   public void setWahleinheit(boolean wahleinheit) {
      if (_wahleinheit != wahleinheit) {
         _wahleinheit = wahleinheit;
         setModified();
      }
   }

   /**
     * Gets the value of wahleinheit in the entity Gebiet
     *
     * @return value of the attribute wahleinheit
     */
   public boolean isWahleinheit() {
      return _wahleinheit;
   }

   /**
     * Type : INT Name : Gebietsart
     */
   protected int _gebietsart;

   /**
     * Sets the value of gebietsart in the entity Gebiet
     *
     * @param gebietsart new value of the attribute gebietsart
     */
   public void setGebietsart(int gebietsart) {
      if (_gebietsart != gebietsart) {
         _gebietsart = gebietsart;
         setModified();
      }
   }

   /**
     * Gets the value of gebietsart in the entity Gebiet
     *
     * @return value of the attribute gebietsart
     */
   public int getGebietsart() {
      return _gebietsart;
   }

   /**
     * Type : INT Name : Nummer
     */
   protected int _nummer;

   /**
     * Sets the value of nummer in the entity Gebiet
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
     * Gets the value of nummer in the entity Gebiet
     *
     * @return value of the attribute nummer
     */
   public int getNummer() {
      return _nummer;
   }

   /**
     * Type : SMALLINT Name : Roemisch
     */
   protected boolean _roemisch;

   /**
     * Sets the value of roemisch in the entity Gebiet
     *
     * @param roemisch new value of the attribute roemisch
     */
   public void setRoemisch(boolean roemisch) {
      if (_roemisch != roemisch) {
         _roemisch = roemisch;
         setModified();
      }
   }

   /**
     * Gets the value of roemisch in the entity Gebiet
     *
     * @return value of the attribute roemisch
     */
   public boolean isRoemisch() {
      return _roemisch;
   }

   /**
     * Type : VARCHAR Name : Name
     */
   protected String _name;

   /**
     * Sets the value of name in the entity Gebiet
     *
     * @param name new value of the attribute name
     */
   public void setName(String name) {
      if (different(_name, name)) {
         _name = checkLength(name, GebietModel.NAME_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of name in the entity Gebiet
     *
     * @return value of the attribute name
     */
   public String getName() {
      return _name;
   }

   /**
     * Type : VARCHAR Name : Kuerzel
     */
   protected String _kuerzel;

   /**
     * Sets the value of kuerzel in the entity Gebiet
     *
     * @param kuerzel new value of the attribute kuerzel
     */
   public void setKuerzel(String kuerzel) {
      if (different(_kuerzel, kuerzel)) {
         _kuerzel = checkLength(kuerzel, GebietModel.KUERZEL_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of kuerzel in the entity Gebiet
     *
     * @return value of the attribute kuerzel
     */
   public String getKuerzel() {
      return _kuerzel;
   }

   /**
     * Type : INT Name : Position
     */
   protected int _position;

   /**
     * Sets the value of position in the entity Gebiet
     *
     * @param position new value of the attribute position
     */
   public void setPosition(int position) {
      if (_position != position) {
         _position = position;
         setModified();
      }
   }

   /**
     * Gets the value of position in the entity Gebiet
     *
     * @return value of the attribute position
     */
   public int getPosition() {
      return _position;
   }

   /**
     * Type : INT Name : Wahlberechtigte
     */
   protected int _wahlberechtigte;

   /**
     * Sets the value of wahlberechtigte in the entity Gebiet
     *
     * @param wahlberechtigte new value of the attribute wahlberechtigte
     */
   public void setWahlberechtigte(int wahlberechtigte) {
      if (_wahlberechtigte != wahlberechtigte) {
         _wahlberechtigte = wahlberechtigte;
         setModified();
      }
   }

   /**
     * Gets the value of wahlberechtigte in the entity Gebiet
     *
     * @return value of the attribute wahlberechtigte
     */
   public int getWahlberechtigte() {
      return _wahlberechtigte;
   }

   /**
     * Type : SMALLINT Name : GUIEingabeErlaubt
     */
   protected boolean _gUIEingabeErlaubt;

   /**
     * Sets the value of gUIEingabeErlaubt in the entity Gebiet
     *
     * @param gUIEingabeErlaubt new value of the attribute gUIEingabeErlaubt
     */
   public void setGUIEingabeErlaubt(boolean gUIEingabeErlaubt) {
      if (_gUIEingabeErlaubt != gUIEingabeErlaubt) {
         _gUIEingabeErlaubt = gUIEingabeErlaubt;
         setModified();
      }
   }

   /**
     * Gets the value of gUIEingabeErlaubt in the entity Gebiet
     *
     * @return value of the attribute gUIEingabeErlaubt
     */
   public boolean isGUIEingabeErlaubt() {
      return _gUIEingabeErlaubt;
   }

   /**
     * Type : SMALLINT Name : Postalvote
     */
   protected boolean _postalvote;

   /**
     * Sets the value of postalvote in the entity Gebiet
     *
     * @param postalvote new value of the attribute postalvote
     */
   public void setPostalvote(boolean postalvote) {
      if (_postalvote != postalvote) {
         _postalvote = postalvote;
         setModified();
      }
   }

   /**
     * Gets the value of postalvote in the entity Gebiet
     *
     * @return value of the attribute postalvote
     */
   public boolean isPostalvote() {
      return _postalvote;
   }

   /**
     * Type : INT Name : VoteValue
     */
   protected int _voteValue;

   /**
     * Sets the value of voteValue in the entity Gebiet
     *
     * @param voteValue new value of the attribute voteValue
     */
   public void setVoteValue(int voteValue) {
      if (_voteValue != voteValue) {
         _voteValue = voteValue;
         setModified();
      }
   }

   /**
     * Gets the value of voteValue in the entity Gebiet
     *
     * @return value of the attribute voteValue
     */
   public int getVoteValue() {
      return _voteValue;
   }

   /**
     * Type : VARCHAR Name : Zipcode
     */
   protected String _zipcode;

   /**
     * Sets the value of zipcode in the entity Gebiet
     *
     * @param zipcode new value of the attribute zipcode
     */
   public void setZipcode(String zipcode) {
      if (different(_zipcode, zipcode)) {
         _zipcode = checkLength(zipcode, GebietModel.ZIPCODE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of zipcode in the entity Gebiet
     *
     * @return value of the attribute zipcode
     */
   public String getZipcode() {
      return _zipcode;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Gebiet.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof BasicGebietModelImpl) {
         BasicGebietModelImpl other = (BasicGebietModelImpl)object;
         return _id_Gebiet.equals(other._id_Gebiet);
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
         if (getID_Gebiet() != null) {
            string += "id_Gebiet = " + getID_Gebiet(); //$NON-NLS-1$
         }
         if (getID_UebergeordnetesGebiet() != null) {
            string +=  ", id_UebergeordnetesGebiet = " + getID_UebergeordnetesGebiet(); //$NON-NLS-1$
         }
         if (getID_Wahl() != null) {
            string +=  ", id_Wahl = " + getID_Wahl(); //$NON-NLS-1$
         }
         if (getID_LetzterEingang() != null) {
            string +=  ", id_LetzterEingang = " + getID_LetzterEingang(); //$NON-NLS-1$
         }
         string +=  ", erfassungseinheit = " + isErfassungseinheit(); //$NON-NLS-1$
         string +=  ", wahleinheit = " + isWahleinheit(); //$NON-NLS-1$
         string +=  ", gebietsart = " + getGebietsart(); //$NON-NLS-1$
         string +=  ", nummer = " + getNummer(); //$NON-NLS-1$
         string +=  ", roemisch = " + isRoemisch(); //$NON-NLS-1$
         if (getName() != null) {
            string +=  ", name = " + getName(); //$NON-NLS-1$
         }
         if (getKuerzel() != null) {
            string +=  ", kuerzel = " + getKuerzel(); //$NON-NLS-1$
         }
         string +=  ", position = " + getPosition(); //$NON-NLS-1$
         string +=  ", wahlberechtigte = " + getWahlberechtigte(); //$NON-NLS-1$
         string +=  ", gUIEingabeErlaubt = " + isGUIEingabeErlaubt(); //$NON-NLS-1$
         string +=  ", postalvote = " + isPostalvote(); //$NON-NLS-1$
         string +=  ", voteValue = " + getVoteValue(); //$NON-NLS-1$
         if (getZipcode() != null) {
            string +=  ", zipcode = " + getZipcode(); //$NON-NLS-1$
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
   public GebietModel copy() {
      return (GebietModel)clone();
   }
}
