/*
 * UnterverteilungModelImpl
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
import de.ivu.wahl.modell.UnterverteilungModel;

/**
  * Model implementation for the entity Unterverteilung.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class UnterverteilungModelImpl extends ModelImpl implements UnterverteilungModel, Serializable {
   private static final Category LOGGER = Log4J.configure(UnterverteilungModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(UnterverteilungModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public UnterverteilungModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Unterverteilung key element of the type {@link String}
     */
   public UnterverteilungModelImpl(String id_Unterverteilung) {
      setID_Unterverteilung(id_Unterverteilung);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(UnterverteilungModelImpl other) {
      setID_Ergebniseingang(other._id_Ergebniseingang);
      setID_Gruppe(other._id_Gruppe);
      setID_Listenkombination(other._id_Listenkombination);
      setStimmen(other._stimmen);
      setSitze(other._sitze);
   }

   /**
     * Type : VARCHAR Name : ID_Unterverteilung
     */
   protected String _id_Unterverteilung;

   /**
     * Sets the value of id_Unterverteilung in the entity Unterverteilung
     *
     * @param id_Unterverteilung new value of the attribute id_Unterverteilung
     */
   protected void setID_Unterverteilung(String id_Unterverteilung) {
      if (different(_id_Unterverteilung, id_Unterverteilung)) {
         _id_Unterverteilung = checkLength(id_Unterverteilung, UnterverteilungModel.ID_UNTERVERTEILUNG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Unterverteilung in the entity Unterverteilung
     *
     * @return value of the attribute id_Unterverteilung
     */
   public String getID_Unterverteilung() {
      return _id_Unterverteilung;
   }

   /**
     * Type : VARCHAR Name : ID_Ergebniseingang
     */
   protected String _id_Ergebniseingang;

   /**
     * Sets the value of id_Ergebniseingang in the entity Unterverteilung
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   public void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (different(_id_Ergebniseingang, id_Ergebniseingang)) {
         _id_Ergebniseingang = checkLength(id_Ergebniseingang, UnterverteilungModel.ID_ERGEBNISEINGANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Ergebniseingang in the entity Unterverteilung
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _id_Ergebniseingang;
   }

   /**
     * Type : VARCHAR Name : ID_Gruppe
     */
   protected String _id_Gruppe;

   /**
     * Sets the value of id_Gruppe in the entity Unterverteilung
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   public void setID_Gruppe(String id_Gruppe) {
      if (different(_id_Gruppe, id_Gruppe)) {
         _id_Gruppe = checkLength(id_Gruppe, UnterverteilungModel.ID_GRUPPE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gruppe in the entity Unterverteilung
     *
     * @return value of the attribute id_Gruppe
     */
   public String getID_Gruppe() {
      return _id_Gruppe;
   }

   /**
     * Type : VARCHAR Name : ID_Listenkombination
     */
   protected String _id_Listenkombination;

   /**
     * Sets the value of id_Listenkombination in the entity Unterverteilung
     *
     * @param id_Listenkombination new value of the attribute id_Listenkombination
     */
   public void setID_Listenkombination(String id_Listenkombination) {
      if (different(_id_Listenkombination, id_Listenkombination)) {
         _id_Listenkombination = checkLength(id_Listenkombination, UnterverteilungModel.ID_LISTENKOMBINATION_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Listenkombination in the entity Unterverteilung
     *
     * @return value of the attribute id_Listenkombination
     */
   public String getID_Listenkombination() {
      return _id_Listenkombination;
   }

   /**
     * Type : INT Name : Stimmen
     */
   protected int _stimmen;

   /**
     * Sets the value of stimmen in the entity Unterverteilung
     *
     * @param stimmen new value of the attribute stimmen
     */
   public void setStimmen(int stimmen) {
      if (_stimmen != stimmen) {
         _stimmen = stimmen;
         setModified();
      }
   }

   /**
     * Gets the value of stimmen in the entity Unterverteilung
     *
     * @return value of the attribute stimmen
     */
   public int getStimmen() {
      return _stimmen;
   }

   /**
     * Type : INT Name : Sitze
     */
   protected int _sitze;

   /**
     * Sets the value of sitze in the entity Unterverteilung
     *
     * @param sitze new value of the attribute sitze
     */
   public void setSitze(int sitze) {
      if (_sitze != sitze) {
         _sitze = sitze;
         setModified();
      }
   }

   /**
     * Gets the value of sitze in the entity Unterverteilung
     *
     * @return value of the attribute sitze
     */
   public int getSitze() {
      return _sitze;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Unterverteilung.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof UnterverteilungModelImpl) {
         UnterverteilungModelImpl other = (UnterverteilungModelImpl)object;
         return _id_Unterverteilung.equals(other._id_Unterverteilung);
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
         if (getID_Unterverteilung() != null) {
            string += "id_Unterverteilung = " + getID_Unterverteilung(); //$NON-NLS-1$
         }
         if (getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (getID_Gruppe() != null) {
            string +=  ", id_Gruppe = " + getID_Gruppe(); //$NON-NLS-1$
         }
         if (getID_Listenkombination() != null) {
            string +=  ", id_Listenkombination = " + getID_Listenkombination(); //$NON-NLS-1$
         }
         string +=  ", stimmen = " + getStimmen(); //$NON-NLS-1$
         string +=  ", sitze = " + getSitze(); //$NON-NLS-1$
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
   public UnterverteilungModel copy() {
      return (UnterverteilungModel)clone();
   }
}
