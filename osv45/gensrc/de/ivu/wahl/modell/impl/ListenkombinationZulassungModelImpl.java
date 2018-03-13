/*
 * ListenkombinationZulassungModelImpl
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
import de.ivu.wahl.modell.ListenkombinationZulassungModel;

/**
  * Model implementation for the entity ListenkombinationZulassung.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class ListenkombinationZulassungModelImpl extends ModelImpl implements ListenkombinationZulassungModel, Serializable {
   private static final Category LOGGER = Log4J.configure(ListenkombinationZulassungModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(ListenkombinationZulassungModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public ListenkombinationZulassungModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_ListenkombinationZulassung key element of the type {@link String}
     */
   public ListenkombinationZulassungModelImpl(String id_ListenkombinationZulassung) {
      setID_ListenkombinationZulassung(id_ListenkombinationZulassung);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(ListenkombinationZulassungModelImpl other) {
      setID_Ergebniseingang(other._id_Ergebniseingang);
      setID_Listenkombination(other._id_Listenkombination);
      setID_Gruppe(other._id_Gruppe);
      setZugelassen(other._zugelassen);
   }

   /**
     * Type : VARCHAR Name : ID_ListenkombinationZulassung
     */
   protected String _id_ListenkombinationZulassung;

   /**
     * Sets the value of id_ListenkombinationZulassung in the entity ListenkombinationZulassung
     *
     * @param id_ListenkombinationZulassung new value of the attribute id_ListenkombinationZulassung
     */
   protected void setID_ListenkombinationZulassung(String id_ListenkombinationZulassung) {
      if (different(_id_ListenkombinationZulassung, id_ListenkombinationZulassung)) {
         _id_ListenkombinationZulassung = checkLength(id_ListenkombinationZulassung, ListenkombinationZulassungModel.ID_LISTENKOMBINATIONZULASSUNG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_ListenkombinationZulassung in the entity ListenkombinationZulassung
     *
     * @return value of the attribute id_ListenkombinationZulassung
     */
   public String getID_ListenkombinationZulassung() {
      return _id_ListenkombinationZulassung;
   }

   /**
     * Type : VARCHAR Name : ID_Ergebniseingang
     */
   protected String _id_Ergebniseingang;

   /**
     * Sets the value of id_Ergebniseingang in the entity ListenkombinationZulassung
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   public void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (different(_id_Ergebniseingang, id_Ergebniseingang)) {
         _id_Ergebniseingang = checkLength(id_Ergebniseingang, ListenkombinationZulassungModel.ID_ERGEBNISEINGANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Ergebniseingang in the entity ListenkombinationZulassung
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _id_Ergebniseingang;
   }

   /**
     * Type : VARCHAR Name : ID_Listenkombination
     */
   protected String _id_Listenkombination;

   /**
     * Sets the value of id_Listenkombination in the entity ListenkombinationZulassung
     *
     * @param id_Listenkombination new value of the attribute id_Listenkombination
     */
   public void setID_Listenkombination(String id_Listenkombination) {
      if (different(_id_Listenkombination, id_Listenkombination)) {
         _id_Listenkombination = checkLength(id_Listenkombination, ListenkombinationZulassungModel.ID_LISTENKOMBINATION_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Listenkombination in the entity ListenkombinationZulassung
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
     * Sets the value of id_Gruppe in the entity ListenkombinationZulassung
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   public void setID_Gruppe(String id_Gruppe) {
      if (different(_id_Gruppe, id_Gruppe)) {
         _id_Gruppe = checkLength(id_Gruppe, ListenkombinationZulassungModel.ID_GRUPPE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gruppe in the entity ListenkombinationZulassung
     *
     * @return value of the attribute id_Gruppe
     */
   public String getID_Gruppe() {
      return _id_Gruppe;
   }

   /**
     * Type : SMALLINT Name : Zugelassen
     */
   protected boolean _zugelassen;

   /**
     * Sets the value of zugelassen in the entity ListenkombinationZulassung
     *
     * @param zugelassen new value of the attribute zugelassen
     */
   public void setZugelassen(boolean zugelassen) {
      if (_zugelassen != zugelassen) {
         _zugelassen = zugelassen;
         setModified();
      }
   }

   /**
     * Gets the value of zugelassen in the entity ListenkombinationZulassung
     *
     * @return value of the attribute zugelassen
     */
   public boolean isZugelassen() {
      return _zugelassen;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_ListenkombinationZulassung.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof ListenkombinationZulassungModelImpl) {
         ListenkombinationZulassungModelImpl other = (ListenkombinationZulassungModelImpl)object;
         return _id_ListenkombinationZulassung.equals(other._id_ListenkombinationZulassung);
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
         if (getID_ListenkombinationZulassung() != null) {
            string += "id_ListenkombinationZulassung = " + getID_ListenkombinationZulassung(); //$NON-NLS-1$
         }
         if (getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (getID_Listenkombination() != null) {
            string +=  ", id_Listenkombination = " + getID_Listenkombination(); //$NON-NLS-1$
         }
         if (getID_Gruppe() != null) {
            string +=  ", id_Gruppe = " + getID_Gruppe(); //$NON-NLS-1$
         }
         string +=  ", zugelassen = " + isZugelassen(); //$NON-NLS-1$
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
   public ListenkombinationZulassungModel copy() {
      return (ListenkombinationZulassungModel)clone();
   }
}
