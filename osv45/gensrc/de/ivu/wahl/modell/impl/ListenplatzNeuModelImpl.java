/*
 * ListenplatzNeuModelImpl
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
import de.ivu.wahl.modell.ListenplatzNeuModel;

/**
  * Model implementation for the entity ListenplatzNeu.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class ListenplatzNeuModelImpl extends ModelImpl implements ListenplatzNeuModel, Serializable {
   private static final Category LOGGER = Log4J.configure(ListenplatzNeuModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(ListenplatzNeuModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public ListenplatzNeuModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_ListenplatzNeu key element of the type {@link String}
     */
   public ListenplatzNeuModelImpl(String id_ListenplatzNeu) {
      setID_ListenplatzNeu(id_ListenplatzNeu);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(ListenplatzNeuModelImpl other) {
      setID_Liste(other._id_Liste);
      setID_Ergebniseingang(other._id_Ergebniseingang);
      setGeaendert(other._geaendert);
   }

   /**
     * Type : VARCHAR Name : ID_ListenplatzNeu
     */
   protected String _id_ListenplatzNeu;

   /**
     * Sets the value of id_ListenplatzNeu in the entity ListenplatzNeu
     *
     * @param id_ListenplatzNeu new value of the attribute id_ListenplatzNeu
     */
   protected void setID_ListenplatzNeu(String id_ListenplatzNeu) {
      if (different(_id_ListenplatzNeu, id_ListenplatzNeu)) {
         _id_ListenplatzNeu = checkLength(id_ListenplatzNeu, ListenplatzNeuModel.ID_LISTENPLATZNEU_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_ListenplatzNeu in the entity ListenplatzNeu
     *
     * @return value of the attribute id_ListenplatzNeu
     */
   public String getID_ListenplatzNeu() {
      return _id_ListenplatzNeu;
   }

   /**
     * Type : VARCHAR Name : ID_Liste
     */
   protected String _id_Liste;

   /**
     * Sets the value of id_Liste in the entity ListenplatzNeu
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   public void setID_Liste(String id_Liste) {
      if (different(_id_Liste, id_Liste)) {
         _id_Liste = checkLength(id_Liste, ListenplatzNeuModel.ID_LISTE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Liste in the entity ListenplatzNeu
     *
     * @return value of the attribute id_Liste
     */
   public String getID_Liste() {
      return _id_Liste;
   }

   /**
     * Type : VARCHAR Name : ID_Ergebniseingang
     */
   protected String _id_Ergebniseingang;

   /**
     * Sets the value of id_Ergebniseingang in the entity ListenplatzNeu
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   public void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (different(_id_Ergebniseingang, id_Ergebniseingang)) {
         _id_Ergebniseingang = checkLength(id_Ergebniseingang, ListenplatzNeuModel.ID_ERGEBNISEINGANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Ergebniseingang in the entity ListenplatzNeu
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _id_Ergebniseingang;
   }

   /**
     * Type : SMALLINT Name : Geaendert
     */
   protected boolean _geaendert;

   /**
     * Sets the value of geaendert in the entity ListenplatzNeu
     *
     * @param geaendert new value of the attribute geaendert
     */
   public void setGeaendert(boolean geaendert) {
      if (_geaendert != geaendert) {
         _geaendert = geaendert;
         setModified();
      }
   }

   /**
     * Gets the value of geaendert in the entity ListenplatzNeu
     *
     * @return value of the attribute geaendert
     */
   public boolean isGeaendert() {
      return _geaendert;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_ListenplatzNeu.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof ListenplatzNeuModelImpl) {
         ListenplatzNeuModelImpl other = (ListenplatzNeuModelImpl)object;
         return _id_ListenplatzNeu.equals(other._id_ListenplatzNeu);
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
         if (getID_ListenplatzNeu() != null) {
            string += "id_ListenplatzNeu = " + getID_ListenplatzNeu(); //$NON-NLS-1$
         }
         if (getID_Liste() != null) {
            string +=  ", id_Liste = " + getID_Liste(); //$NON-NLS-1$
         }
         if (getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + getID_Ergebniseingang(); //$NON-NLS-1$
         }
         string +=  ", geaendert = " + isGeaendert(); //$NON-NLS-1$
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
   public ListenplatzNeuModel copy() {
      return (ListenplatzNeuModel)clone();
   }
}
