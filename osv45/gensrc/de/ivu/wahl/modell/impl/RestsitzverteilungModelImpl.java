/*
 * RestsitzverteilungModelImpl
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
import de.ivu.wahl.modell.RestsitzverteilungModel;

/**
  * Model implementation for the entity Restsitzverteilung.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class RestsitzverteilungModelImpl extends ModelImpl implements RestsitzverteilungModel, Serializable {
   private static final Category LOGGER = Log4J.configure(RestsitzverteilungModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(RestsitzverteilungModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public RestsitzverteilungModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Restsitzverteilung key element of the type {@link String}
     */
   public RestsitzverteilungModelImpl(String id_Restsitzverteilung) {
      setID_Restsitzverteilung(id_Restsitzverteilung);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(RestsitzverteilungModelImpl other) {
      setID_Ergebniseingang(other._id_Ergebniseingang);
      setID_Liste(other._id_Liste);
      setID_Gruppe(other._id_Gruppe);
      setID_Listenkombination(other._id_Listenkombination);
      setVerteilung(other._verteilung);
      setSitze(other._sitze);
      setSitzeRest(other._sitzeRest);
      setSitzeGesamtZuVerteilen(other._sitzeGesamtZuVerteilen);
   }

   /**
     * Type : VARCHAR Name : ID_Restsitzverteilung
     */
   protected String _id_Restsitzverteilung;

   /**
     * Sets the value of id_Restsitzverteilung in the entity Restsitzverteilung
     *
     * @param id_Restsitzverteilung new value of the attribute id_Restsitzverteilung
     */
   protected void setID_Restsitzverteilung(String id_Restsitzverteilung) {
      if (different(_id_Restsitzverteilung, id_Restsitzverteilung)) {
         _id_Restsitzverteilung = checkLength(id_Restsitzverteilung, RestsitzverteilungModel.ID_RESTSITZVERTEILUNG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Restsitzverteilung in the entity Restsitzverteilung
     *
     * @return value of the attribute id_Restsitzverteilung
     */
   public String getID_Restsitzverteilung() {
      return _id_Restsitzverteilung;
   }

   /**
     * Type : VARCHAR Name : ID_Ergebniseingang
     */
   protected String _id_Ergebniseingang;

   /**
     * Sets the value of id_Ergebniseingang in the entity Restsitzverteilung
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   public void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (different(_id_Ergebniseingang, id_Ergebniseingang)) {
         _id_Ergebniseingang = checkLength(id_Ergebniseingang, RestsitzverteilungModel.ID_ERGEBNISEINGANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Ergebniseingang in the entity Restsitzverteilung
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
     * Sets the value of id_Liste in the entity Restsitzverteilung
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   public void setID_Liste(String id_Liste) {
      if (different(_id_Liste, id_Liste)) {
         _id_Liste = checkLength(id_Liste, RestsitzverteilungModel.ID_LISTE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Liste in the entity Restsitzverteilung
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
     * Sets the value of id_Gruppe in the entity Restsitzverteilung
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   public void setID_Gruppe(String id_Gruppe) {
      if (different(_id_Gruppe, id_Gruppe)) {
         _id_Gruppe = checkLength(id_Gruppe, RestsitzverteilungModel.ID_GRUPPE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gruppe in the entity Restsitzverteilung
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
     * Sets the value of id_Listenkombination in the entity Restsitzverteilung
     *
     * @param id_Listenkombination new value of the attribute id_Listenkombination
     */
   public void setID_Listenkombination(String id_Listenkombination) {
      if (different(_id_Listenkombination, id_Listenkombination)) {
         _id_Listenkombination = checkLength(id_Listenkombination, RestsitzverteilungModel.ID_LISTENKOMBINATION_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Listenkombination in the entity Restsitzverteilung
     *
     * @return value of the attribute id_Listenkombination
     */
   public String getID_Listenkombination() {
      return _id_Listenkombination;
   }

   /**
     * Type : INT Name : Verteilung
     */
   protected int _verteilung;

   /**
     * Sets the value of verteilung in the entity Restsitzverteilung
     *
     * @param verteilung new value of the attribute verteilung
     */
   public void setVerteilung(int verteilung) {
      if (_verteilung != verteilung) {
         _verteilung = verteilung;
         setModified();
      }
   }

   /**
     * Gets the value of verteilung in the entity Restsitzverteilung
     *
     * @return value of the attribute verteilung
     */
   public int getVerteilung() {
      return _verteilung;
   }

   /**
     * Type : INT Name : Sitze
     */
   protected int _sitze;

   /**
     * Sets the value of sitze in the entity Restsitzverteilung
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
     * Gets the value of sitze in the entity Restsitzverteilung
     *
     * @return value of the attribute sitze
     */
   public int getSitze() {
      return _sitze;
   }

   /**
     * Type : INT Name : SitzeRest
     */
   protected int _sitzeRest;

   /**
     * Sets the value of sitzeRest in the entity Restsitzverteilung
     *
     * @param sitzeRest new value of the attribute sitzeRest
     */
   public void setSitzeRest(int sitzeRest) {
      if (_sitzeRest != sitzeRest) {
         _sitzeRest = sitzeRest;
         setModified();
      }
   }

   /**
     * Gets the value of sitzeRest in the entity Restsitzverteilung
     *
     * @return value of the attribute sitzeRest
     */
   public int getSitzeRest() {
      return _sitzeRest;
   }

   /**
     * Type : INT Name : SitzeGesamtZuVerteilen
     */
   protected int _sitzeGesamtZuVerteilen;

   /**
     * Sets the value of sitzeGesamtZuVerteilen in the entity Restsitzverteilung
     *
     * @param sitzeGesamtZuVerteilen new value of the attribute sitzeGesamtZuVerteilen
     */
   public void setSitzeGesamtZuVerteilen(int sitzeGesamtZuVerteilen) {
      if (_sitzeGesamtZuVerteilen != sitzeGesamtZuVerteilen) {
         _sitzeGesamtZuVerteilen = sitzeGesamtZuVerteilen;
         setModified();
      }
   }

   /**
     * Gets the value of sitzeGesamtZuVerteilen in the entity Restsitzverteilung
     *
     * @return value of the attribute sitzeGesamtZuVerteilen
     */
   public int getSitzeGesamtZuVerteilen() {
      return _sitzeGesamtZuVerteilen;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Restsitzverteilung.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof RestsitzverteilungModelImpl) {
         RestsitzverteilungModelImpl other = (RestsitzverteilungModelImpl)object;
         return _id_Restsitzverteilung.equals(other._id_Restsitzverteilung);
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
         if (getID_Restsitzverteilung() != null) {
            string += "id_Restsitzverteilung = " + getID_Restsitzverteilung(); //$NON-NLS-1$
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
         string +=  ", verteilung = " + getVerteilung(); //$NON-NLS-1$
         string +=  ", sitze = " + getSitze(); //$NON-NLS-1$
         string +=  ", sitzeRest = " + getSitzeRest(); //$NON-NLS-1$
         string +=  ", sitzeGesamtZuVerteilen = " + getSitzeGesamtZuVerteilen(); //$NON-NLS-1$
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
   public RestsitzverteilungModel copy() {
      return (RestsitzverteilungModel)clone();
   }
}
