/*
 * ListenkombinationModelImpl
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
import de.ivu.wahl.modell.ListenkombinationModel;

/**
  * Model implementation for the entity Listenkombination.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class ListenkombinationModelImpl extends ModelImpl implements ListenkombinationModel, Serializable {
   private static final Category LOGGER = Log4J.configure(ListenkombinationModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(ListenkombinationModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public ListenkombinationModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Listenkombination key element of the type {@link String}
     */
   public ListenkombinationModelImpl(String id_Listenkombination) {
      setID_Listenkombination(id_Listenkombination);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(ListenkombinationModelImpl other) {
      setID_Wahl(other._id_Wahl);
      setBezeichnung(other._bezeichnung);
   }

   /**
     * Type : VARCHAR Name : ID_Listenkombination
     */
   protected String _id_Listenkombination;

   /**
     * Sets the value of id_Listenkombination in the entity Listenkombination
     *
     * @param id_Listenkombination new value of the attribute id_Listenkombination
     */
   protected void setID_Listenkombination(String id_Listenkombination) {
      if (different(_id_Listenkombination, id_Listenkombination)) {
         _id_Listenkombination = checkLength(id_Listenkombination, ListenkombinationModel.ID_LISTENKOMBINATION_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Listenkombination in the entity Listenkombination
     *
     * @return value of the attribute id_Listenkombination
     */
   public String getID_Listenkombination() {
      return _id_Listenkombination;
   }

   /**
     * Type : VARCHAR Name : ID_Wahl
     */
   protected String _id_Wahl;

   /**
     * Sets the value of id_Wahl in the entity Listenkombination
     *
     * @param id_Wahl new value of the attribute id_Wahl
     */
   public void setID_Wahl(String id_Wahl) {
      if (different(_id_Wahl, id_Wahl)) {
         _id_Wahl = checkLength(id_Wahl, ListenkombinationModel.ID_WAHL_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Wahl in the entity Listenkombination
     *
     * @return value of the attribute id_Wahl
     */
   public String getID_Wahl() {
      return _id_Wahl;
   }

   /**
     * Type : CHAR(1) Name : Bezeichnung
     */
   protected String _bezeichnung;

   /**
     * Sets the value of bezeichnung in the entity Listenkombination
     *
     * @param bezeichnung new value of the attribute bezeichnung
     */
   public void setBezeichnung(String bezeichnung) {
      if (different(_bezeichnung, bezeichnung)) {
         _bezeichnung = checkLength(bezeichnung, ListenkombinationModel.BEZEICHNUNG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of bezeichnung in the entity Listenkombination
     *
     * @return value of the attribute bezeichnung
     */
   public String getBezeichnung() {
      return _bezeichnung;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Listenkombination.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof ListenkombinationModelImpl) {
         ListenkombinationModelImpl other = (ListenkombinationModelImpl)object;
         return _id_Listenkombination.equals(other._id_Listenkombination);
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
         if (getID_Listenkombination() != null) {
            string += "id_Listenkombination = " + getID_Listenkombination(); //$NON-NLS-1$
         }
         if (getID_Wahl() != null) {
            string +=  ", id_Wahl = " + getID_Wahl(); //$NON-NLS-1$
         }
         if (getBezeichnung() != null) {
            string +=  ", bezeichnung = " + getBezeichnung(); //$NON-NLS-1$
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
   public ListenkombinationModel copy() {
      return (ListenkombinationModel)clone();
   }
}
