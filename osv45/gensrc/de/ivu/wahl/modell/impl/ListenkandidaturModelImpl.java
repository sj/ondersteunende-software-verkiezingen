/*
 * ListenkandidaturModelImpl
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
import de.ivu.wahl.modell.ListenkandidaturModel;

/**
  * Model implementation for the entity Listenkandidatur.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class ListenkandidaturModelImpl extends ModelImpl implements ListenkandidaturModel, Serializable {
   private static final Category LOGGER = Log4J.configure(ListenkandidaturModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(ListenkandidaturModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public ListenkandidaturModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Listenkandidatur key element of the type {@link String}
     */
   public ListenkandidaturModelImpl(String id_Listenkandidatur) {
      setID_Listenkandidatur(id_Listenkandidatur);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(ListenkandidaturModelImpl other) {
      setID_Liste(other._id_Liste);
      setID_Wahl(other._id_Wahl);
      setID_Personendaten(other._id_Personendaten);
      setListenplatz(other._listenplatz);
   }

   /**
     * Type : VARCHAR Name : ID_Listenkandidatur
     */
   protected String _id_Listenkandidatur;

   /**
     * Sets the value of id_Listenkandidatur in the entity Listenkandidatur
     *
     * @param id_Listenkandidatur new value of the attribute id_Listenkandidatur
     */
   protected void setID_Listenkandidatur(String id_Listenkandidatur) {
      if (different(_id_Listenkandidatur, id_Listenkandidatur)) {
         _id_Listenkandidatur = checkLength(id_Listenkandidatur, ListenkandidaturModel.ID_LISTENKANDIDATUR_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Listenkandidatur in the entity Listenkandidatur
     *
     * @return value of the attribute id_Listenkandidatur
     */
   public String getID_Listenkandidatur() {
      return _id_Listenkandidatur;
   }

   /**
     * Type : VARCHAR Name : ID_Liste
     */
   protected String _id_Liste;

   /**
     * Sets the value of id_Liste in the entity Listenkandidatur
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   public void setID_Liste(String id_Liste) {
      if (different(_id_Liste, id_Liste)) {
         _id_Liste = checkLength(id_Liste, ListenkandidaturModel.ID_LISTE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Liste in the entity Listenkandidatur
     *
     * @return value of the attribute id_Liste
     */
   public String getID_Liste() {
      return _id_Liste;
   }

   /**
     * Type : VARCHAR Name : ID_Wahl
     */
   protected String _id_Wahl;

   /**
     * Sets the value of id_Wahl in the entity Listenkandidatur
     *
     * @param id_Wahl new value of the attribute id_Wahl
     */
   public void setID_Wahl(String id_Wahl) {
      if (different(_id_Wahl, id_Wahl)) {
         _id_Wahl = checkLength(id_Wahl, ListenkandidaturModel.ID_WAHL_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Wahl in the entity Listenkandidatur
     *
     * @return value of the attribute id_Wahl
     */
   public String getID_Wahl() {
      return _id_Wahl;
   }

   /**
     * Type : VARCHAR Name : ID_Personendaten
     */
   protected String _id_Personendaten;

   /**
     * Sets the value of id_Personendaten in the entity Listenkandidatur
     *
     * @param id_Personendaten new value of the attribute id_Personendaten
     */
   public void setID_Personendaten(String id_Personendaten) {
      if (different(_id_Personendaten, id_Personendaten)) {
         _id_Personendaten = checkLength(id_Personendaten, ListenkandidaturModel.ID_PERSONENDATEN_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Personendaten in the entity Listenkandidatur
     *
     * @return value of the attribute id_Personendaten
     */
   public String getID_Personendaten() {
      return _id_Personendaten;
   }

   /**
     * Type : INT Name : Listenplatz
     */
   protected int _listenplatz;

   /**
     * Sets the value of listenplatz in the entity Listenkandidatur
     *
     * @param listenplatz new value of the attribute listenplatz
     */
   public void setListenplatz(int listenplatz) {
      if (_listenplatz != listenplatz) {
         _listenplatz = listenplatz;
         setModified();
      }
   }

   /**
     * Gets the value of listenplatz in the entity Listenkandidatur
     *
     * @return value of the attribute listenplatz
     */
   public int getListenplatz() {
      return _listenplatz;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Listenkandidatur.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof ListenkandidaturModelImpl) {
         ListenkandidaturModelImpl other = (ListenkandidaturModelImpl)object;
         return _id_Listenkandidatur.equals(other._id_Listenkandidatur);
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
         if (getID_Listenkandidatur() != null) {
            string += "id_Listenkandidatur = " + getID_Listenkandidatur(); //$NON-NLS-1$
         }
         if (getID_Liste() != null) {
            string +=  ", id_Liste = " + getID_Liste(); //$NON-NLS-1$
         }
         if (getID_Wahl() != null) {
            string +=  ", id_Wahl = " + getID_Wahl(); //$NON-NLS-1$
         }
         if (getID_Personendaten() != null) {
            string +=  ", id_Personendaten = " + getID_Personendaten(); //$NON-NLS-1$
         }
         string +=  ", listenplatz = " + getListenplatz(); //$NON-NLS-1$
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
   public ListenkandidaturModel copy() {
      return (ListenkandidaturModel)clone();
   }
}
