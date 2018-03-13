/*
 * Rechtegruppe_AnwenderModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Rechtegruppe_Anwender.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface Rechtegruppe_AnwenderModel extends Model {
   /**
     * Gets the value of id_Rechtegruppe in the entity Rechtegruppe_Anwender
     *
     * @return value of the attribute id_Rechtegruppe
     */
   String getID_Rechtegruppe();

   /**
     * The maximum size of id_Rechtegruppe
     * The size is limited by the database.
     */
   public static final int ID_RECHTEGRUPPE_LENGTH = 30;
   /**
     * Gets the value of id_Anwender in the entity Rechtegruppe_Anwender
     *
     * @return value of the attribute id_Anwender
     */
   String getID_Anwender();

   /**
     * The maximum size of id_Anwender
     * The size is limited by the database.
     */
   public static final int ID_ANWENDER_LENGTH = 30;

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public Rechtegruppe_AnwenderModel copy();
}
