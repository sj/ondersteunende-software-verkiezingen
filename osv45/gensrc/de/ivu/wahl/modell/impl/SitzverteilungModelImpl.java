/*
 * SitzverteilungModelImpl
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
import de.ivu.wahl.modell.SitzverteilungModel;

/**
  * Model implementation for the entity Sitzverteilung.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class SitzverteilungModelImpl extends ModelImpl implements SitzverteilungModel, Serializable {
   private static final Category LOGGER = Log4J.configure(SitzverteilungModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(SitzverteilungModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public SitzverteilungModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Sitzverteilung key element of the type {@link String}
     */
   public SitzverteilungModelImpl(String id_Sitzverteilung) {
      setID_Sitzverteilung(id_Sitzverteilung);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(SitzverteilungModelImpl other) {
      setID_Ergebniseingang(other._id_Ergebniseingang);
      setID_Liste(other._id_Liste);
      setID_Gruppe(other._id_Gruppe);
      setID_Listenkombination(other._id_Listenkombination);
      setSitzeGesamtzahl(other._sitzeGesamtzahl);
   }

   /**
     * Type : VARCHAR Name : ID_Sitzverteilung
     */
   protected String _id_Sitzverteilung;

   /**
     * Sets the value of id_Sitzverteilung in the entity Sitzverteilung
     *
     * @param id_Sitzverteilung new value of the attribute id_Sitzverteilung
     */
   protected void setID_Sitzverteilung(String id_Sitzverteilung) {
      if (different(_id_Sitzverteilung, id_Sitzverteilung)) {
         _id_Sitzverteilung = checkLength(id_Sitzverteilung, SitzverteilungModel.ID_SITZVERTEILUNG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Sitzverteilung in the entity Sitzverteilung
     *
     * @return value of the attribute id_Sitzverteilung
     */
   public String getID_Sitzverteilung() {
      return _id_Sitzverteilung;
   }

   /**
     * Type : VARCHAR Name : ID_Ergebniseingang
     */
   protected String _id_Ergebniseingang;

   /**
     * Sets the value of id_Ergebniseingang in the entity Sitzverteilung
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   public void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (different(_id_Ergebniseingang, id_Ergebniseingang)) {
         _id_Ergebniseingang = checkLength(id_Ergebniseingang, SitzverteilungModel.ID_ERGEBNISEINGANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Ergebniseingang in the entity Sitzverteilung
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _id_Ergebniseingang;
   }

   /**
     * Type : VARCHAR Name : ID_Liste
     */
   protected String _id_Liste;

   /**
     * Sets the value of id_Liste in the entity Sitzverteilung
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   public void setID_Liste(String id_Liste) {
      if (different(_id_Liste, id_Liste)) {
         _id_Liste = checkLength(id_Liste, SitzverteilungModel.ID_LISTE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Liste in the entity Sitzverteilung
     *
     * @return value of the attribute id_Liste
     */
   public String getID_Liste() {
      return _id_Liste;
   }

   /**
     * Type : VARCHAR Name : ID_Gruppe
     */
   protected String _id_Gruppe;

   /**
     * Sets the value of id_Gruppe in the entity Sitzverteilung
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   public void setID_Gruppe(String id_Gruppe) {
      if (different(_id_Gruppe, id_Gruppe)) {
         _id_Gruppe = checkLength(id_Gruppe, SitzverteilungModel.ID_GRUPPE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gruppe in the entity Sitzverteilung
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
     * Sets the value of id_Listenkombination in the entity Sitzverteilung
     *
     * @param id_Listenkombination new value of the attribute id_Listenkombination
     */
   public void setID_Listenkombination(String id_Listenkombination) {
      if (different(_id_Listenkombination, id_Listenkombination)) {
         _id_Listenkombination = checkLength(id_Listenkombination, SitzverteilungModel.ID_LISTENKOMBINATION_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Listenkombination in the entity Sitzverteilung
     *
     * @return value of the attribute id_Listenkombination
     */
   public String getID_Listenkombination() {
      return _id_Listenkombination;
   }

   /**
     * Type : INT Name : SitzeGesamtzahl
     */
   protected int _sitzeGesamtzahl;

   /**
     * Sets the value of sitzeGesamtzahl in the entity Sitzverteilung
     *
     * @param sitzeGesamtzahl new value of the attribute sitzeGesamtzahl
     */
   public void setSitzeGesamtzahl(int sitzeGesamtzahl) {
      if (_sitzeGesamtzahl != sitzeGesamtzahl) {
         _sitzeGesamtzahl = sitzeGesamtzahl;
         setModified();
      }
   }

   /**
     * Gets the value of sitzeGesamtzahl in the entity Sitzverteilung
     *
     * @return value of the attribute sitzeGesamtzahl
     */
   public int getSitzeGesamtzahl() {
      return _sitzeGesamtzahl;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Sitzverteilung.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof SitzverteilungModelImpl) {
         SitzverteilungModelImpl other = (SitzverteilungModelImpl)object;
         return _id_Sitzverteilung.equals(other._id_Sitzverteilung);
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
         if (getID_Sitzverteilung() != null) {
            string += "id_Sitzverteilung = " + getID_Sitzverteilung(); //$NON-NLS-1$
         }
         if (getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (getID_Liste() != null) {
            string +=  ", id_Liste = " + getID_Liste(); //$NON-NLS-1$
         }
         if (getID_Gruppe() != null) {
            string +=  ", id_Gruppe = " + getID_Gruppe(); //$NON-NLS-1$
         }
         if (getID_Listenkombination() != null) {
            string +=  ", id_Listenkombination = " + getID_Listenkombination(); //$NON-NLS-1$
         }
         string +=  ", sitzeGesamtzahl = " + getSitzeGesamtzahl(); //$NON-NLS-1$
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
   public SitzverteilungModel copy() {
      return (SitzverteilungModel)clone();
   }
}
