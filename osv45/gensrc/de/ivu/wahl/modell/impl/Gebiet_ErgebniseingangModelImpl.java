/*
 * Gebiet_ErgebniseingangModelImpl
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
import de.ivu.wahl.modell.Gebiet_ErgebniseingangModel;

/**
  * Model implementation for the entity Gebiet_Ergebniseingang.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class Gebiet_ErgebniseingangModelImpl extends ModelImpl implements Gebiet_ErgebniseingangModel, Serializable {
   private static final Category LOGGER = Log4J.configure(Gebiet_ErgebniseingangModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(Gebiet_ErgebniseingangModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public Gebiet_ErgebniseingangModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Gebiet key element of the type {@link String}
     * @param id_Ergebniseingang key element of the type {@link String}
     */
   public Gebiet_ErgebniseingangModelImpl(String id_Gebiet, String id_Ergebniseingang) {
      setID_Gebiet(id_Gebiet);
      setID_Ergebniseingang(id_Ergebniseingang);
   }

   /**
     * Type : VARCHAR Name : ID_Gebiet
     */
   protected String _id_Gebiet;

   /**
     * Sets the value of id_Gebiet in the entity Gebiet_Ergebniseingang
     *
     * @param id_Gebiet new value of the attribute id_Gebiet
     */
   protected void setID_Gebiet(String id_Gebiet) {
      if (different(_id_Gebiet, id_Gebiet)) {
         _id_Gebiet = checkLength(id_Gebiet, Gebiet_ErgebniseingangModel.ID_GEBIET_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gebiet in the entity Gebiet_Ergebniseingang
     *
     * @return value of the attribute id_Gebiet
     */
   public String getID_Gebiet() {
      return _id_Gebiet;
   }

   /**
     * Type : VARCHAR Name : ID_Ergebniseingang
     */
   protected String _id_Ergebniseingang;

   /**
     * Sets the value of id_Ergebniseingang in the entity Gebiet_Ergebniseingang
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   protected void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (different(_id_Ergebniseingang, id_Ergebniseingang)) {
         _id_Ergebniseingang = checkLength(id_Ergebniseingang, Gebiet_ErgebniseingangModel.ID_ERGEBNISEINGANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Ergebniseingang in the entity Gebiet_Ergebniseingang
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _id_Ergebniseingang;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Gebiet.hashCode() ^ _id_Ergebniseingang.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof Gebiet_ErgebniseingangModelImpl) {
         Gebiet_ErgebniseingangModelImpl other = (Gebiet_ErgebniseingangModelImpl)object;
         return _id_Gebiet.equals(other._id_Gebiet) && _id_Ergebniseingang.equals(other._id_Ergebniseingang);
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
         if (getID_Gebiet() != null) {
            string += "id_Gebiet = " + getID_Gebiet(); //$NON-NLS-1$
         }
         if (getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + getID_Ergebniseingang(); //$NON-NLS-1$
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
   public Gebiet_ErgebniseingangModel copy() {
      return (Gebiet_ErgebniseingangModel)clone();
   }
}
