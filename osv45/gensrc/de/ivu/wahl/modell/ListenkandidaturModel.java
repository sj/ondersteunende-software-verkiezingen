/*
 * ListenkandidaturModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Listenkandidatur.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface ListenkandidaturModel extends Model {
   /**
     * Gets the value of id_Listenkandidatur in the entity Listenkandidatur
     *
     * @return value of the attribute id_Listenkandidatur
     */
   String getID_Listenkandidatur();

   /**
     * The maximum size of id_Listenkandidatur
     * The size is limited by the database.
     */
   public static final int ID_LISTENKANDIDATUR_LENGTH = 13;

   /**
     * Sets the value of id_Liste in the entity Listenkandidatur
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   void setID_Liste(String id_Liste);

   /**
     * Gets the value of id_Liste in the entity Listenkandidatur
     *
     * @return value of the attribute id_Liste
     */
   String getID_Liste();

   /**
     * The maximum size of id_Liste
     * The size is limited by the database.
     */
   public static final int ID_LISTE_LENGTH = 13;

   /**
     * Sets the value of id_Wahl in the entity Listenkandidatur
     *
     * @param id_Wahl new value of the attribute id_Wahl
     */
   void setID_Wahl(String id_Wahl);

   /**
     * Gets the value of id_Wahl in the entity Listenkandidatur
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
     * Sets the value of id_Personendaten in the entity Listenkandidatur
     *
     * @param id_Personendaten new value of the attribute id_Personendaten
     */
   void setID_Personendaten(String id_Personendaten);

   /**
     * Gets the value of id_Personendaten in the entity Listenkandidatur
     *
     * @return value of the attribute id_Personendaten
     */
   String getID_Personendaten();

   /**
     * The maximum size of id_Personendaten
     * The size is limited by the database.
     */
   public static final int ID_PERSONENDATEN_LENGTH = 15;

   /**
     * Sets the value of listenplatz in the entity Listenkandidatur
     *
     * @param listenplatz new value of the attribute listenplatz
     */
   void setListenplatz(int listenplatz);

   /**
     * Gets the value of listenplatz in the entity Listenkandidatur
     *
     * @return value of the attribute listenplatz
     */
   int getListenplatz();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public ListenkandidaturModel copy();
}
