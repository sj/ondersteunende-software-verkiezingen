/*
 * RechtModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Recht.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface RechtModel extends Model {
   /**
     * Gets the value of id_Recht in the entity Recht
     *
     * @return value of the attribute id_Recht
     */
   String getID_Recht();

   /**
     * The maximum size of id_Recht
     * The size is limited by the database.
     */
   public static final int ID_RECHT_LENGTH = 30;

   /**
     * Sets the value of name in the entity Recht
     *
     * @param name new value of the attribute name
     */
   void setName(String name);

   /**
     * Gets the value of name in the entity Recht
     *
     * @return value of the attribute name
     */
   String getName();

   /**
     * The maximum size of name
     * The size is limited by the database.
     */
   public static final int NAME_LENGTH = 200;

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public RechtModel copy();
}
