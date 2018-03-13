/*
 * AlternativeModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Alternative.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface AlternativeModel extends Model {
   /**
     * Gets the value of id_Alternative in the entity Alternative
     *
     * @return value of the attribute id_Alternative
     */
   String getID_Alternative();

   /**
     * The maximum size of id_Alternative
     * The size is limited by the database.
     */
   public static final int ID_ALTERNATIVE_LENGTH = 13;

   /**
     * Sets the value of id_Konflikt in the entity Alternative
     *
     * @param id_Konflikt new value of the attribute id_Konflikt
     */
   void setID_Konflikt(String id_Konflikt);

   /**
     * Gets the value of id_Konflikt in the entity Alternative
     *
     * @return value of the attribute id_Konflikt
     */
   String getID_Konflikt();

   /**
     * The maximum size of id_Konflikt
     * The size is limited by the database.
     */
   public static final int ID_KONFLIKT_LENGTH = 13;

   /**
     * Sets the value of id_Listenkombination in the entity Alternative
     *
     * @param id_Listenkombination new value of the attribute id_Listenkombination
     */
   void setID_Listenkombination(String id_Listenkombination);

   /**
     * Gets the value of id_Listenkombination in the entity Alternative
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
     * Sets the value of id_Gruppe in the entity Alternative
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   void setID_Gruppe(String id_Gruppe);

   /**
     * Gets the value of id_Gruppe in the entity Alternative
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
     * Sets the value of id_Liste in the entity Alternative
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   void setID_Liste(String id_Liste);

   /**
     * Gets the value of id_Liste in the entity Alternative
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
     * Sets the value of id_Personendaten in the entity Alternative
     *
     * @param id_Personendaten new value of the attribute id_Personendaten
     */
   void setID_Personendaten(String id_Personendaten);

   /**
     * Gets the value of id_Personendaten in the entity Alternative
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
     * Sets the value of nummer in the entity Alternative
     *
     * @param nummer new value of the attribute nummer
     */
   void setNummer(int nummer);

   /**
     * Gets the value of nummer in the entity Alternative
     *
     * @return value of the attribute nummer
     */
   int getNummer();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public AlternativeModel copy();
}
