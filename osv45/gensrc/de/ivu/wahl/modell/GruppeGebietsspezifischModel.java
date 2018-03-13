/*
 * GruppeGebietsspezifischModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity GruppeGebietsspezifisch.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface GruppeGebietsspezifischModel extends Model {
   /**
     * Gets the value of id_GruppeGebietsspezifisch in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute id_GruppeGebietsspezifisch
     */
   String getID_GruppeGebietsspezifisch();

   /**
     * The maximum size of id_GruppeGebietsspezifisch
     * The size is limited by the database.
     */
   public static final int ID_GRUPPEGEBIETSSPEZIFISCH_LENGTH = 13;

   /**
     * Sets the value of id_UebergeordneteGG in the entity GruppeGebietsspezifisch
     *
     * @param id_UebergeordneteGG new value of the attribute id_UebergeordneteGG
     */
   void setID_UebergeordneteGG(String id_UebergeordneteGG);

   /**
     * Gets the value of id_UebergeordneteGG in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute id_UebergeordneteGG
     */
   String getID_UebergeordneteGG();

   /**
     * The maximum size of id_UebergeordneteGG
     * The size is limited by the database.
     */
   public static final int ID_UEBERGEORDNETEGG_LENGTH = 13;

   /**
     * Sets the value of id_Gruppe in the entity GruppeGebietsspezifisch
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   void setID_Gruppe(String id_Gruppe);

   /**
     * Gets the value of id_Gruppe in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute id_Gruppe
     */
   String getID_Gruppe();

   /**
     * The maximum size of id_Gruppe
     * The size is limited by the database.
     */
   public static final int ID_GRUPPE_LENGTH = 13;

   /**
     * Sets the value of id_Gebiet in the entity GruppeGebietsspezifisch
     *
     * @param id_Gebiet new value of the attribute id_Gebiet
     */
   void setID_Gebiet(String id_Gebiet);

   /**
     * Gets the value of id_Gebiet in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute id_Gebiet
     */
   String getID_Gebiet();

   /**
     * The maximum size of id_Gebiet
     * The size is limited by the database.
     */
   public static final int ID_GEBIET_LENGTH = 13;

   /**
     * Sets the value of id_Liste in the entity GruppeGebietsspezifisch
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   void setID_Liste(String id_Liste);

   /**
     * Gets the value of id_Liste in the entity GruppeGebietsspezifisch
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
     * Sets the value of position in the entity GruppeGebietsspezifisch
     *
     * @param position new value of the attribute position
     */
   void setPosition(int position);

   /**
     * Gets the value of position in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute position
     */
   int getPosition();

   /**
     * Sets the value of listeZugelassen in the entity GruppeGebietsspezifisch
     *
     * @param listeZugelassen new value of the attribute listeZugelassen
     */
   void setListeZugelassen(boolean listeZugelassen);

   /**
     * Gets the value of listeZugelassen in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute listeZugelassen
     */
   boolean isListeZugelassen();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public GruppeGebietsspezifischModel copy();
}
