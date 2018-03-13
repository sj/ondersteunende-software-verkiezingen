/*
 * Gebiet_GebietModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Gebiet_Gebiet.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface Gebiet_GebietModel extends Model {
   /**
     * Gets the value of id_Elterngebiet in the entity Gebiet_Gebiet
     *
     * @return value of the attribute id_Elterngebiet
     */
   String getID_Elterngebiet();

   /**
     * The maximum size of id_Elterngebiet
     * The size is limited by the database.
     */
   public static final int ID_ELTERNGEBIET_LENGTH = 13;
   /**
     * Gets the value of id_Untergebiet in the entity Gebiet_Gebiet
     *
     * @return value of the attribute id_Untergebiet
     */
   String getID_Untergebiet();

   /**
     * The maximum size of id_Untergebiet
     * The size is limited by the database.
     */
   public static final int ID_UNTERGEBIET_LENGTH = 13;

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public Gebiet_GebietModel copy();
}
