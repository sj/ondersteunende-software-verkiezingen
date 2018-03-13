/*
 * RepositoryModelImpl
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
import de.ivu.wahl.modell.RepositoryModel;

/**
  * Model implementation for the entity Repository.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class RepositoryModelImpl extends ModelImpl implements RepositoryModel, Serializable {
   private static final Category LOGGER = Log4J.configure(RepositoryModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(RepositoryModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public RepositoryModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Repository key element of the type {@link String}
     */
   public RepositoryModelImpl(String id_Repository) {
      setID_Repository(id_Repository);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(RepositoryModelImpl other) {
      setName(other._name);
      setWert(other._wert);
   }

   /**
     * Type : VARCHAR Name : ID_Repository
     */
   protected String _id_Repository;

   /**
     * Sets the value of id_Repository in the entity Repository
     *
     * @param id_Repository new value of the attribute id_Repository
     */
   protected void setID_Repository(String id_Repository) {
      if (different(_id_Repository, id_Repository)) {
         _id_Repository = checkLength(id_Repository, RepositoryModel.ID_REPOSITORY_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Repository in the entity Repository
     *
     * @return value of the attribute id_Repository
     */
   public String getID_Repository() {
      return _id_Repository;
   }

   /**
     * Type : VARCHAR Name : Name
     */
   protected String _name;

   /**
     * Sets the value of name in the entity Repository
     *
     * @param name new value of the attribute name
     */
   public void setName(String name) {
      if (different(_name, name)) {
         _name = checkLength(name, RepositoryModel.NAME_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of name in the entity Repository
     *
     * @return value of the attribute name
     */
   public String getName() {
      return _name;
   }

   /**
     * Type : VARCHAR Name : Wert
     */
   protected String _wert;

   /**
     * Sets the value of wert in the entity Repository
     *
     * @param wert new value of the attribute wert
     */
   public void setWert(String wert) {
      if (different(_wert, wert)) {
         _wert = checkLength(wert, RepositoryModel.WERT_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of wert in the entity Repository
     *
     * @return value of the attribute wert
     */
   public String getWert() {
      return _wert;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Repository.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof RepositoryModelImpl) {
         RepositoryModelImpl other = (RepositoryModelImpl)object;
         return _id_Repository.equals(other._id_Repository);
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
         if (getID_Repository() != null) {
            string += "id_Repository = " + getID_Repository(); //$NON-NLS-1$
         }
         if (getName() != null) {
            string +=  ", name = " + getName(); //$NON-NLS-1$
         }
         if (getWert() != null) {
            string +=  ", wert = " + getWert(); //$NON-NLS-1$
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
   public RepositoryModel copy() {
      return (RepositoryModel)clone();
   }
}
