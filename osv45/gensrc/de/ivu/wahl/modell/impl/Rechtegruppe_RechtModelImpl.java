/*
 * Rechtegruppe_RechtModelImpl
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
import de.ivu.wahl.modell.Rechtegruppe_RechtModel;

/**
  * Model implementation for the entity Rechtegruppe_Recht.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class Rechtegruppe_RechtModelImpl extends ModelImpl implements Rechtegruppe_RechtModel, Serializable {
   private static final Category LOGGER = Log4J.configure(Rechtegruppe_RechtModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(Rechtegruppe_RechtModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public Rechtegruppe_RechtModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Rechtegruppe key element of the type {@link String}
     * @param id_Recht key element of the type {@link String}
     */
   public Rechtegruppe_RechtModelImpl(String id_Rechtegruppe, String id_Recht) {
      setID_Rechtegruppe(id_Rechtegruppe);
      setID_Recht(id_Recht);
   }

   /**
     * Type : VARCHAR Name : ID_Rechtegruppe
     */
   protected String _id_Rechtegruppe;

   /**
     * Sets the value of id_Rechtegruppe in the entity Rechtegruppe_Recht
     *
     * @param id_Rechtegruppe new value of the attribute id_Rechtegruppe
     */
   protected void setID_Rechtegruppe(String id_Rechtegruppe) {
      if (different(_id_Rechtegruppe, id_Rechtegruppe)) {
         _id_Rechtegruppe = checkLength(id_Rechtegruppe, Rechtegruppe_RechtModel.ID_RECHTEGRUPPE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Rechtegruppe in the entity Rechtegruppe_Recht
     *
     * @return value of the attribute id_Rechtegruppe
     */
   public String getID_Rechtegruppe() {
      return _id_Rechtegruppe;
   }

   /**
     * Type : VARCHAR Name : ID_Recht
     */
   protected String _id_Recht;

   /**
     * Sets the value of id_Recht in the entity Rechtegruppe_Recht
     *
     * @param id_Recht new value of the attribute id_Recht
     */
   protected void setID_Recht(String id_Recht) {
      if (different(_id_Recht, id_Recht)) {
         _id_Recht = checkLength(id_Recht, Rechtegruppe_RechtModel.ID_RECHT_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Recht in the entity Rechtegruppe_Recht
     *
     * @return value of the attribute id_Recht
     */
   public String getID_Recht() {
      return _id_Recht;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Rechtegruppe.hashCode() ^ _id_Recht.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof Rechtegruppe_RechtModelImpl) {
         Rechtegruppe_RechtModelImpl other = (Rechtegruppe_RechtModelImpl)object;
         return _id_Rechtegruppe.equals(other._id_Rechtegruppe) && _id_Recht.equals(other._id_Recht);
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
         if (getID_Recht() != null) {
            string +=  ", id_Recht = " + getID_Recht(); //$NON-NLS-1$
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
   public Rechtegruppe_RechtModel copy() {
      return (Rechtegruppe_RechtModel)clone();
   }
}
