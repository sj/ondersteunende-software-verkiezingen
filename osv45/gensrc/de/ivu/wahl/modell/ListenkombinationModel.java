/*
 * ListenkombinationModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Listenkombination.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface ListenkombinationModel extends Model {
   /**
     * Gets the value of id_Listenkombination in the entity Listenkombination
     *
     * @return value of the attribute id_Listenkombination
     */
   String getID_Listenkombination();

   /**
     * The maximum size of id_Listenkombination
     * The size is limited by the database.
     */
   public static final int ID_LISTENKOMBINATION_LENGTH = 13;

   /**
     * Sets the value of id_Wahl in the entity Listenkombination
     *
     * @param id_Wahl new value of the attribute id_Wahl
     */
   void setID_Wahl(String id_Wahl);

   /**
     * Gets the value of id_Wahl in the entity Listenkombination
     *
     * @return value of the attribute id_Wahl
     */
   String getID_Wahl();

   /**
     * The maximum size of id_Wahl
     * The size is limited by the database.
     */
   public static final int ID_WAHL_LENGTH = 100;

   /**
     * Sets the value of bezeichnung in the entity Listenkombination
     *
     * @param bezeichnung new value of the attribute bezeichnung
     */
   void setBezeichnung(String bezeichnung);

   /**
     * Gets the value of bezeichnung in the entity Listenkombination
     *
     * @return value of the attribute bezeichnung
     */
   String getBezeichnung();

   /**
     * The maximum size of bezeichnung
     * The size is limited by the database.
     */
   public static final int BEZEICHNUNG_LENGTH = 1;

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public ListenkombinationModel copy();
}
