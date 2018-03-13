/*
 * RechtegruppeModelImpl
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
import de.ivu.wahl.modell.RechtegruppeModel;

/**
  * Model implementation for the entity Rechtegruppe.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class RechtegruppeModelImpl extends ModelImpl implements RechtegruppeModel, Serializable {
   private static final Category LOGGER = Log4J.configure(RechtegruppeModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(RechtegruppeModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public RechtegruppeModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Rechtegruppe key element of the type {@link String}
     */
   public RechtegruppeModelImpl(String id_Rechtegruppe) {
      setID_Rechtegruppe(id_Rechtegruppe);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(RechtegruppeModelImpl other) {
      setName(other._name);
      setBeschreibung(other._beschreibung);
   }

   /**
     * Type : VARCHAR Name : ID_Rechtegruppe
     */
   protected String _id_Rechtegruppe;

   /**
     * Sets the value of id_Rechtegruppe in the entity Rechtegruppe
     *
     * @param id_Rechtegruppe new value of the attribute id_Rechtegruppe
     */
   protected void setID_Rechtegruppe(String id_Rechtegruppe) {
      if (different(_id_Rechtegruppe, id_Rechtegruppe)) {
         _id_Rechtegruppe = checkLength(id_Rechtegruppe, RechtegruppeModel.ID_RECHTEGRUPPE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Rechtegruppe in the entity Rechtegruppe
     *
     * @return value of the attribute id_Rechtegruppe
     */
   public String getID_Rechtegruppe() {
      return _id_Rechtegruppe;
   }

   /**
     * Type : VARCHAR Name : Name
     */
   protected String _name;

   /**
     * Sets the value of name in the entity Rechtegruppe
     *
     * @param name new value of the attribute name
     */
   public void setName(String name) {
      if (different(_name, name)) {
         _name = checkLength(name, RechtegruppeModel.NAME_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of name in the entity Rechtegruppe
     *
     * @return value of the attribute name
     */
   public String getName() {
      return _name;
   }

   /**
     * Type : VARCHAR Name : Beschreibung
     */
   protected String _beschreibung;

   /**
     * Sets the value of beschreibung in the entity Rechtegruppe
     *
     * @param beschreibung new value of the attribute beschreibung
     */
   public void setBeschreibung(String beschreibung) {
      if (different(_beschreibung, beschreibung)) {
         _beschreibung = checkLength(beschreibung, RechtegruppeModel.BESCHREIBUNG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of beschreibung in the entity Rechtegruppe
     *
     * @return value of the attribute beschreibung
     */
   public String getBeschreibung() {
      return _beschreibung;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Rechtegruppe.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof RechtegruppeModelImpl) {
         RechtegruppeModelImpl other = (RechtegruppeModelImpl)object;
         return _id_Rechtegruppe.equals(other._id_Rechtegruppe);
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
         if (getID_Rechtegruppe() != null) {
            string += "id_Rechtegruppe = " + getID_Rechtegruppe(); //$NON-NLS-1$
         }
         if (getName() != null) {
            string +=  ", name = " + getName(); //$NON-NLS-1$
         }
         if (getBeschreibung() != null) {
            string +=  ", beschreibung = " + getBeschreibung(); //$NON-NLS-1$
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
   public RechtegruppeModel copy() {
      return (RechtegruppeModel)clone();
   }
}
