/*
 * RepositoryModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Repository.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface RepositoryModel extends Model {
   /**
     * Gets the value of id_Repository in the entity Repository
     *
     * @return value of the attribute id_Repository
     */
   String getID_Repository();

   /**
     * The maximum size of id_Repository
     * The size is limited by the database.
     */
   public static final int ID_REPOSITORY_LENGTH = 13;

   /**
     * Sets the value of name in the entity Repository
     *
     * @param name new value of the attribute name
     */
   void setName(String name);

   /**
     * Gets the value of name in the entity Repository
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
     * Sets the value of wert in the entity Repository
     *
     * @param wert new value of the attribute wert
     */
   void setWert(String wert);

   /**
     * Gets the value of wert in the entity Repository
     *
     * @return value of the attribute wert
     */
   String getWert();

   /**
     * The maximum size of wert
     * The size is limited by the database.
     */
   public static final int WERT_LENGTH = 4000;

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public RepositoryModel copy();
}
