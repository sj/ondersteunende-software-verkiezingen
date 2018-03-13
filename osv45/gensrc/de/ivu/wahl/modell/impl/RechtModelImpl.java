/*
 * RechtModelImpl
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
import de.ivu.wahl.modell.RechtModel;

/**
  * Model implementation for the entity Recht.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class RechtModelImpl extends ModelImpl implements RechtModel, Serializable {
   private static final Category LOGGER = Log4J.configure(RechtModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(RechtModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public RechtModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Recht key element of the type {@link String}
     */
   public RechtModelImpl(String id_Recht) {
      setID_Recht(id_Recht);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(RechtModelImpl other) {
      setName(other._name);
   }

   /**
     * Type : VARCHAR Name : ID_Recht
     */
   protected String _id_Recht;

   /**
     * Sets the value of id_Recht in the entity Recht
     *
     * @param id_Recht new value of the attribute id_Recht
     */
   protected void setID_Recht(String id_Recht) {
      if (different(_id_Recht, id_Recht)) {
         _id_Recht = checkLength(id_Recht, RechtModel.ID_RECHT_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Recht in the entity Recht
     *
     * @return value of the attribute id_Recht
     */
   public String getID_Recht() {
      return _id_Recht;
   }

   /**
     * Type : VARCHAR Name : Name
     */
   protected String _name;

   /**
     * Sets the value of name in the entity Recht
     *
     * @param name new value of the attribute name
     */
   public void setName(String name) {
      if (different(_name, name)) {
         _name = checkLength(name, RechtModel.NAME_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of name in the entity Recht
     *
     * @return value of the attribute name
     */
   public String getName() {
      return _name;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Recht.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof RechtModelImpl) {
         RechtModelImpl other = (RechtModelImpl)object;
         return _id_Recht.equals(other._id_Recht);
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
         if (getID_Recht() != null) {
            string += "id_Recht = " + getID_Recht(); //$NON-NLS-1$
         }
         if (getName() != null) {
            string +=  ", name = " + getName(); //$NON-NLS-1$
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
   public RechtModel copy() {
      return (RechtModel)clone();
   }
}
