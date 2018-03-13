/*
 * GruppeModelImpl
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
import de.ivu.wahl.modell.GruppeModel;

/**
  * Model implementation for the entity Gruppe.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class GruppeModelImpl extends ModelImpl implements GruppeModel, Serializable {
   private static final Category LOGGER = Log4J.configure(GruppeModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(GruppeModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public GruppeModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Gruppe key element of the type {@link String}
     */
   public GruppeModelImpl(String id_Gruppe) {
      setID_Gruppe(id_Gruppe);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(GruppeModelImpl other) {
      setID_Wahl(other._id_Wahl);
      setID_Listenkombination(other._id_Listenkombination);
      setSchluessel(other._schluessel);
      setGruppenart(other._gruppenart);
      setNameLang(other._nameLang);
      setNameKurz(other._nameKurz);
      setKautionGestellt(other._kautionGestellt);
      setFarbe(other._farbe);
   }

   /**
     * Type : VARCHAR Name : ID_Gruppe
     */
   protected String _id_Gruppe;

   /**
     * Sets the value of id_Gruppe in the entity Gruppe
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   protected void setID_Gruppe(String id_Gruppe) {
      if (different(_id_Gruppe, id_Gruppe)) {
         _id_Gruppe = checkLength(id_Gruppe, GruppeModel.ID_GRUPPE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gruppe in the entity Gruppe
     *
     * @return value of the attribute id_Gruppe
     */
   public String getID_Gruppe() {
      return _id_Gruppe;
   }

   /**
     * Type : VARCHAR Name : ID_Wahl
     */
   protected String _id_Wahl;

   /**
     * Sets the value of id_Wahl in the entity Gruppe
     *
     * @param id_Wahl new value of the attribute id_Wahl
     */
   public void setID_Wahl(String id_Wahl) {
      if (different(_id_Wahl, id_Wahl)) {
         _id_Wahl = checkLength(id_Wahl, GruppeModel.ID_WAHL_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Wahl in the entity Gruppe
     *
     * @return value of the attribute id_Wahl
     */
   public String getID_Wahl() {
      return _id_Wahl;
   }

   /**
     * Type : VARCHAR Name : ID_Listenkombination
     */
   protected String _id_Listenkombination;

   /**
     * Sets the value of id_Listenkombination in the entity Gruppe
     *
     * @param id_Listenkombination new value of the attribute id_Listenkombination
     */
   public void setID_Listenkombination(String id_Listenkombination) {
      if (different(_id_Listenkombination, id_Listenkombination)) {
         _id_Listenkombination = checkLength(id_Listenkombination, GruppeModel.ID_LISTENKOMBINATION_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Listenkombination in the entity Gruppe
     *
     * @return value of the attribute id_Listenkombination
     */
   public String getID_Listenkombination() {
      return _id_Listenkombination;
   }

   /**
     * Type : INT Name : Schluessel
     */
   protected int _schluessel;

   /**
     * Sets the value of schluessel in the entity Gruppe
     *
     * @param schluessel new value of the attribute schluessel
     */
   public void setSchluessel(int schluessel) {
      if (_schluessel != schluessel) {
         _schluessel = schluessel;
         setModified();
      }
   }

   /**
     * Gets the value of schluessel in the entity Gruppe
     *
     * @return value of the attribute schluessel
     */
   public int getSchluessel() {
      return _schluessel;
   }

   /**
     * Type : INT Name : Gruppenart
     */
   protected int _gruppenart;

   /**
     * Sets the value of gruppenart in the entity Gruppe
     *
     * @param gruppenart new value of the attribute gruppenart
     */
   public void setGruppenart(int gruppenart) {
      if (_gruppenart != gruppenart) {
         _gruppenart = gruppenart;
         setModified();
      }
   }

   /**
     * Gets the value of gruppenart in the entity Gruppe
     *
     * @return value of the attribute gruppenart
     */
   public int getGruppenart() {
      return _gruppenart;
   }

   /**
     * Type : VARCHAR Name : NameLang
     */
   protected String _nameLang;

   /**
     * Sets the value of nameLang in the entity Gruppe
     *
     * @param nameLang new value of the attribute nameLang
     */
   public void setNameLang(String nameLang) {
      if (different(_nameLang, nameLang)) {
         _nameLang = checkLength(nameLang, GruppeModel.NAMELANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of nameLang in the entity Gruppe
     *
     * @return value of the attribute nameLang
     */
   public String getNameLang() {
      return _nameLang;
   }

   /**
     * Type : VARCHAR Name : NameKurz
     */
   protected String _nameKurz;

   /**
     * Sets the value of nameKurz in the entity Gruppe
     *
     * @param nameKurz new value of the attribute nameKurz
     */
   public void setNameKurz(String nameKurz) {
      if (different(_nameKurz, nameKurz)) {
         _nameKurz = checkLength(nameKurz, GruppeModel.NAMEKURZ_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of nameKurz in the entity Gruppe
     *
     * @return value of the attribute nameKurz
     */
   public String getNameKurz() {
      return _nameKurz;
   }

   /**
     * Type : SMALLINT Name : KautionGestellt
     */
   protected boolean _kautionGestellt;

   /**
     * Sets the value of kautionGestellt in the entity Gruppe
     *
     * @param kautionGestellt new value of the attribute kautionGestellt
     */
   public void setKautionGestellt(boolean kautionGestellt) {
      if (_kautionGestellt != kautionGestellt) {
         _kautionGestellt = kautionGestellt;
         setModified();
      }
   }

   /**
     * Gets the value of kautionGestellt in the entity Gruppe
     *
     * @return value of the attribute kautionGestellt
     */
   public boolean isKautionGestellt() {
      return _kautionGestellt;
   }

   /**
     * Type : VARCHAR Name : Farbe
     */
   protected String _farbe;

   /**
     * Sets the value of farbe in the entity Gruppe
     *
     * @param farbe new value of the attribute farbe
     */
   public void setFarbe(String farbe) {
      if (different(_farbe, farbe)) {
         _farbe = checkLength(farbe, GruppeModel.FARBE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of farbe in the entity Gruppe
     *
     * @return value of the attribute farbe
     */
   public String getFarbe() {
      return _farbe;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Gruppe.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof GruppeModelImpl) {
         GruppeModelImpl other = (GruppeModelImpl)object;
         return _id_Gruppe.equals(other._id_Gruppe);
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
         if (getID_Gruppe() != null) {
            string += "id_Gruppe = " + getID_Gruppe(); //$NON-NLS-1$
         }
         if (getID_Wahl() != null) {
            string +=  ", id_Wahl = " + getID_Wahl(); //$NON-NLS-1$
         }
         if (getID_Listenkombination() != null) {
            string +=  ", id_Listenkombination = " + getID_Listenkombination(); //$NON-NLS-1$
         }
         string +=  ", schluessel = " + getSchluessel(); //$NON-NLS-1$
         string +=  ", gruppenart = " + getGruppenart(); //$NON-NLS-1$
         if (getNameLang() != null) {
            string +=  ", nameLang = " + getNameLang(); //$NON-NLS-1$
         }
         if (getNameKurz() != null) {
            string +=  ", nameKurz = " + getNameKurz(); //$NON-NLS-1$
         }
         string +=  ", kautionGestellt = " + isKautionGestellt(); //$NON-NLS-1$
         if (getFarbe() != null) {
            string +=  ", farbe = " + getFarbe(); //$NON-NLS-1$
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
   public GruppeModel copy() {
      return (GruppeModel)clone();
   }
}
