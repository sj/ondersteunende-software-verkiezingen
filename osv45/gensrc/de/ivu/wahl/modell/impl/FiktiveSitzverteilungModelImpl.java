/*
 * FiktiveSitzverteilungModelImpl
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
import de.ivu.wahl.modell.FiktiveSitzverteilungModel;

/**
  * Model implementation for the entity FiktiveSitzverteilung.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class FiktiveSitzverteilungModelImpl extends ModelImpl implements FiktiveSitzverteilungModel, Serializable {
   private static final Category LOGGER = Log4J.configure(FiktiveSitzverteilungModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(FiktiveSitzverteilungModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public FiktiveSitzverteilungModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_FiktiveSitzverteilung key element of the type {@link String}
     */
   public FiktiveSitzverteilungModelImpl(String id_FiktiveSitzverteilung) {
      setID_FiktiveSitzverteilung(id_FiktiveSitzverteilung);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(FiktiveSitzverteilungModelImpl other) {
      setID_Ergebniseingang(other._id_Ergebniseingang);
      setID_Gruppe(other._id_Gruppe);
      setSitzeGesamtzahl(other._sitzeGesamtzahl);
   }

   /**
     * Type : VARCHAR Name : ID_FiktiveSitzverteilung
     */
   protected String _id_FiktiveSitzverteilung;

   /**
     * Sets the value of id_FiktiveSitzverteilung in the entity FiktiveSitzverteilung
     *
     * @param id_FiktiveSitzverteilung new value of the attribute id_FiktiveSitzverteilung
     */
   protected void setID_FiktiveSitzverteilung(String id_FiktiveSitzverteilung) {
      if (different(_id_FiktiveSitzverteilung, id_FiktiveSitzverteilung)) {
         _id_FiktiveSitzverteilung = checkLength(id_FiktiveSitzverteilung, FiktiveSitzverteilungModel.ID_FIKTIVESITZVERTEILUNG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_FiktiveSitzverteilung in the entity FiktiveSitzverteilung
     *
     * @return value of the attribute id_FiktiveSitzverteilung
     */
   public String getID_FiktiveSitzverteilung() {
      return _id_FiktiveSitzverteilung;
   }

   /**
     * Type : VARCHAR Name : ID_Ergebniseingang
     */
   protected String _id_Ergebniseingang;

   /**
     * Sets the value of id_Ergebniseingang in the entity FiktiveSitzverteilung
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   public void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (different(_id_Ergebniseingang, id_Ergebniseingang)) {
         _id_Ergebniseingang = checkLength(id_Ergebniseingang, FiktiveSitzverteilungModel.ID_ERGEBNISEINGANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Ergebniseingang in the entity FiktiveSitzverteilung
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
     * Sets the value of id_Gruppe in the entity FiktiveSitzverteilung
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   public void setID_Gruppe(String id_Gruppe) {
      if (different(_id_Gruppe, id_Gruppe)) {
         _id_Gruppe = checkLength(id_Gruppe, FiktiveSitzverteilungModel.ID_GRUPPE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gruppe in the entity FiktiveSitzverteilung
     *
     * @return value of the attribute id_Gruppe
     */
   public String getID_Gruppe() {
      return _id_Gruppe;
   }

   /**
     * Type : INT Name : SitzeGesamtzahl
     */
   protected int _sitzeGesamtzahl;

   /**
     * Sets the value of sitzeGesamtzahl in the entity FiktiveSitzverteilung
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
     * Gets the value of sitzeGesamtzahl in the entity FiktiveSitzverteilung
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
      return _id_FiktiveSitzverteilung.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof FiktiveSitzverteilungModelImpl) {
         FiktiveSitzverteilungModelImpl other = (FiktiveSitzverteilungModelImpl)object;
         return _id_FiktiveSitzverteilung.equals(other._id_FiktiveSitzverteilung);
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
         if (getID_FiktiveSitzverteilung() != null) {
            string += "id_FiktiveSitzverteilung = " + getID_FiktiveSitzverteilung(); //$NON-NLS-1$
         }
         if (getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (getID_Gruppe() != null) {
            string +=  ", id_Gruppe = " + getID_Gruppe(); //$NON-NLS-1$
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
   public FiktiveSitzverteilungModel copy() {
      return (FiktiveSitzverteilungModel)clone();
   }
}
