/*
 * KonfliktModelImpl
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
import de.ivu.wahl.modell.KonfliktModel;

/**
  * Model implementation for the entity Konflikt.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class KonfliktModelImpl extends ModelImpl implements KonfliktModel, Serializable {
   private static final Category LOGGER = Log4J.configure(KonfliktModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(KonfliktModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public KonfliktModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Konflikt key element of the type {@link String}
     */
   public KonfliktModelImpl(String id_Konflikt) {
      setID_Konflikt(id_Konflikt);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(KonfliktModelImpl other) {
      setID_Ergebniseingang(other._id_Ergebniseingang);
      setID_LosAlternative(other._id_LosAlternative);
      setNummer(other._nummer);
      setKonfliktart(other._konfliktart);
   }

   /**
     * Type : VARCHAR Name : ID_Konflikt
     */
   protected String _id_Konflikt;

   /**
     * Sets the value of id_Konflikt in the entity Konflikt
     *
     * @param id_Konflikt new value of the attribute id_Konflikt
     */
   protected void setID_Konflikt(String id_Konflikt) {
      if (different(_id_Konflikt, id_Konflikt)) {
         _id_Konflikt = checkLength(id_Konflikt, KonfliktModel.ID_KONFLIKT_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Konflikt in the entity Konflikt
     *
     * @return value of the attribute id_Konflikt
     */
   public String getID_Konflikt() {
      return _id_Konflikt;
   }

   /**
     * Type : VARCHAR Name : ID_Ergebniseingang
     */
   protected String _id_Ergebniseingang;

   /**
     * Sets the value of id_Ergebniseingang in the entity Konflikt
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   public void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (different(_id_Ergebniseingang, id_Ergebniseingang)) {
         _id_Ergebniseingang = checkLength(id_Ergebniseingang, KonfliktModel.ID_ERGEBNISEINGANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Ergebniseingang in the entity Konflikt
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _id_Ergebniseingang;
   }

   /**
     * Type : VARCHAR Name : ID_LosAlternative
     */
   protected String _id_LosAlternative;

   /**
     * Sets the value of id_LosAlternative in the entity Konflikt
     *
     * @param id_LosAlternative new value of the attribute id_LosAlternative
     */
   public void setID_LosAlternative(String id_LosAlternative) {
      if (different(_id_LosAlternative, id_LosAlternative)) {
         _id_LosAlternative = checkLength(id_LosAlternative, KonfliktModel.ID_LOSALTERNATIVE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_LosAlternative in the entity Konflikt
     *
     * @return value of the attribute id_LosAlternative
     */
   public String getID_LosAlternative() {
      return _id_LosAlternative;
   }

   /**
     * Type : INT Name : Nummer
     */
   protected int _nummer;

   /**
     * Sets the value of nummer in the entity Konflikt
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
     * Gets the value of nummer in the entity Konflikt
     *
     * @return value of the attribute nummer
     */
   public int getNummer() {
      return _nummer;
   }

   /**
     * Type : INT Name : Konfliktart
     */
   protected int _konfliktart;

   /**
     * Sets the value of konfliktart in the entity Konflikt
     *
     * @param konfliktart new value of the attribute konfliktart
     */
   public void setKonfliktart(int konfliktart) {
      if (_konfliktart != konfliktart) {
         _konfliktart = konfliktart;
         setModified();
      }
   }

   /**
     * Gets the value of konfliktart in the entity Konflikt
     *
     * @return value of the attribute konfliktart
     */
   public int getKonfliktart() {
      return _konfliktart;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Konflikt.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof KonfliktModelImpl) {
         KonfliktModelImpl other = (KonfliktModelImpl)object;
         return _id_Konflikt.equals(other._id_Konflikt);
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
         if (getID_Konflikt() != null) {
            string += "id_Konflikt = " + getID_Konflikt(); //$NON-NLS-1$
         }
         if (getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (getID_LosAlternative() != null) {
            string +=  ", id_LosAlternative = " + getID_LosAlternative(); //$NON-NLS-1$
         }
         string +=  ", nummer = " + getNummer(); //$NON-NLS-1$
         string +=  ", konfliktart = " + getKonfliktart(); //$NON-NLS-1$
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
   public KonfliktModel copy() {
      return (KonfliktModel)clone();
   }
}
