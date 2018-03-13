/*
 * AlternativeModelImpl
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
import de.ivu.wahl.modell.AlternativeModel;

/**
  * Model implementation for the entity Alternative.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class AlternativeModelImpl extends ModelImpl implements AlternativeModel, Serializable {
   private static final Category LOGGER = Log4J.configure(AlternativeModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(AlternativeModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public AlternativeModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Alternative key element of the type {@link String}
     */
   public AlternativeModelImpl(String id_Alternative) {
      setID_Alternative(id_Alternative);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(AlternativeModelImpl other) {
      setID_Konflikt(other._id_Konflikt);
      setID_Listenkombination(other._id_Listenkombination);
      setID_Gruppe(other._id_Gruppe);
      setID_Liste(other._id_Liste);
      setID_Personendaten(other._id_Personendaten);
      setNummer(other._nummer);
   }

   /**
     * Type : VARCHAR Name : ID_Alternative
     */
   protected String _id_Alternative;

   /**
     * Sets the value of id_Alternative in the entity Alternative
     *
     * @param id_Alternative new value of the attribute id_Alternative
     */
   protected void setID_Alternative(String id_Alternative) {
      if (different(_id_Alternative, id_Alternative)) {
         _id_Alternative = checkLength(id_Alternative, AlternativeModel.ID_ALTERNATIVE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Alternative in the entity Alternative
     *
     * @return value of the attribute id_Alternative
     */
   public String getID_Alternative() {
      return _id_Alternative;
   }

   /**
     * Type : VARCHAR Name : ID_Konflikt
     */
   protected String _id_Konflikt;

   /**
     * Sets the value of id_Konflikt in the entity Alternative
     *
     * @param id_Konflikt new value of the attribute id_Konflikt
     */
   public void setID_Konflikt(String id_Konflikt) {
      if (different(_id_Konflikt, id_Konflikt)) {
         _id_Konflikt = checkLength(id_Konflikt, AlternativeModel.ID_KONFLIKT_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Konflikt in the entity Alternative
     *
     * @return value of the attribute id_Konflikt
     */
   public String getID_Konflikt() {
      return _id_Konflikt;
   }

   /**
     * Type : VARCHAR Name : ID_Listenkombination
     */
   protected String _id_Listenkombination;

   /**
     * Sets the value of id_Listenkombination in the entity Alternative
     *
     * @param id_Listenkombination new value of the attribute id_Listenkombination
     */
   public void setID_Listenkombination(String id_Listenkombination) {
      if (different(_id_Listenkombination, id_Listenkombination)) {
         _id_Listenkombination = checkLength(id_Listenkombination, AlternativeModel.ID_LISTENKOMBINATION_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Listenkombination in the entity Alternative
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
     * Sets the value of id_Gruppe in the entity Alternative
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   public void setID_Gruppe(String id_Gruppe) {
      if (different(_id_Gruppe, id_Gruppe)) {
         _id_Gruppe = checkLength(id_Gruppe, AlternativeModel.ID_GRUPPE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gruppe in the entity Alternative
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
     * Sets the value of id_Liste in the entity Alternative
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   public void setID_Liste(String id_Liste) {
      if (different(_id_Liste, id_Liste)) {
         _id_Liste = checkLength(id_Liste, AlternativeModel.ID_LISTE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Liste in the entity Alternative
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
     * Sets the value of id_Personendaten in the entity Alternative
     *
     * @param id_Personendaten new value of the attribute id_Personendaten
     */
   public void setID_Personendaten(String id_Personendaten) {
      if (different(_id_Personendaten, id_Personendaten)) {
         _id_Personendaten = checkLength(id_Personendaten, AlternativeModel.ID_PERSONENDATEN_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Personendaten in the entity Alternative
     *
     * @return value of the attribute id_Personendaten
     */
   public String getID_Personendaten() {
      return _id_Personendaten;
   }

   /**
     * Type : INT Name : Nummer
     */
   protected int _nummer;

   /**
     * Sets the value of nummer in the entity Alternative
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
     * Gets the value of nummer in the entity Alternative
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
      return _id_Alternative.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof AlternativeModelImpl) {
         AlternativeModelImpl other = (AlternativeModelImpl)object;
         return _id_Alternative.equals(other._id_Alternative);
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
         if (getID_Alternative() != null) {
            string += "id_Alternative = " + getID_Alternative(); //$NON-NLS-1$
         }
         if (getID_Konflikt() != null) {
            string +=  ", id_Konflikt = " + getID_Konflikt(); //$NON-NLS-1$
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
   public AlternativeModel copy() {
      return (AlternativeModel)clone();
   }
}
