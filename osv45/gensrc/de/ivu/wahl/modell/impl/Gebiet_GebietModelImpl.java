/*
 * Gebiet_GebietModelImpl
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
import de.ivu.wahl.modell.Gebiet_GebietModel;

/**
  * Model implementation for the entity Gebiet_Gebiet.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class Gebiet_GebietModelImpl extends ModelImpl implements Gebiet_GebietModel, Serializable {
   private static final Category LOGGER = Log4J.configure(Gebiet_GebietModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(Gebiet_GebietModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public Gebiet_GebietModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Elterngebiet key element of the type {@link String}
     * @param id_Untergebiet key element of the type {@link String}
     */
   public Gebiet_GebietModelImpl(String id_Elterngebiet, String id_Untergebiet) {
      setID_Elterngebiet(id_Elterngebiet);
      setID_Untergebiet(id_Untergebiet);
   }

   /**
     * Type : VARCHAR Name : ID_Elterngebiet
     */
   protected String _id_Elterngebiet;

   /**
     * Sets the value of id_Elterngebiet in the entity Gebiet_Gebiet
     *
     * @param id_Elterngebiet new value of the attribute id_Elterngebiet
     */
   protected void setID_Elterngebiet(String id_Elterngebiet) {
      if (different(_id_Elterngebiet, id_Elterngebiet)) {
         _id_Elterngebiet = checkLength(id_Elterngebiet, Gebiet_GebietModel.ID_ELTERNGEBIET_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Elterngebiet in the entity Gebiet_Gebiet
     *
     * @return value of the attribute id_Elterngebiet
     */
   public String getID_Elterngebiet() {
      return _id_Elterngebiet;
   }

   /**
     * Type : VARCHAR Name : ID_Untergebiet
     */
   protected String _id_Untergebiet;

   /**
     * Sets the value of id_Untergebiet in the entity Gebiet_Gebiet
     *
     * @param id_Untergebiet new value of the attribute id_Untergebiet
     */
   protected void setID_Untergebiet(String id_Untergebiet) {
      if (different(_id_Untergebiet, id_Untergebiet)) {
         _id_Untergebiet = checkLength(id_Untergebiet, Gebiet_GebietModel.ID_UNTERGEBIET_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Untergebiet in the entity Gebiet_Gebiet
     *
     * @return value of the attribute id_Untergebiet
     */
   public String getID_Untergebiet() {
      return _id_Untergebiet;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Elterngebiet.hashCode() ^ _id_Untergebiet.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof Gebiet_GebietModelImpl) {
         Gebiet_GebietModelImpl other = (Gebiet_GebietModelImpl)object;
         return _id_Elterngebiet.equals(other._id_Elterngebiet) && _id_Untergebiet.equals(other._id_Untergebiet);
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
         if (getID_Elterngebiet() != null) {
            string += "id_Elterngebiet = " + getID_Elterngebiet(); //$NON-NLS-1$
         }
         if (getID_Untergebiet() != null) {
            string +=  ", id_Untergebiet = " + getID_Untergebiet(); //$NON-NLS-1$
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
   public Gebiet_GebietModel copy() {
      return (Gebiet_GebietModel)clone();
   }
}
