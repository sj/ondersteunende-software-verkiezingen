/*
 * BesonderheitModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Besonderheit.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BesonderheitModel extends Model {
   /**
     * Gets the value of id_Besonderheit in the entity Besonderheit
     *
     * @return value of the attribute id_Besonderheit
     */
   String getID_Besonderheit();

   /**
     * The maximum size of id_Besonderheit
     * The size is limited by the database.
     */
   public static final int ID_BESONDERHEIT_LENGTH = 13;

   /**
     * Sets the value of id_Ergebniseingang in the entity Besonderheit
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   void setID_Ergebniseingang(String id_Ergebniseingang);

   /**
     * Gets the value of id_Ergebniseingang in the entity Besonderheit
     *
     * @return value of the attribute id_Ergebniseingang
     */
   String getID_Ergebniseingang();

   /**
     * The maximum size of id_Ergebniseingang
     * The size is limited by the database.
     */
   public static final int ID_ERGEBNISEINGANG_LENGTH = 13;

   /**
     * Sets the value of id_UebergeordneteBesonderheit in the entity Besonderheit
     *
     * @param id_UebergeordneteBesonderheit new value of the attribute id_UebergeordneteBesonderheit
     */
   void setID_UebergeordneteBesonderheit(String id_UebergeordneteBesonderheit);

   /**
     * Gets the value of id_UebergeordneteBesonderheit in the entity Besonderheit
     *
     * @return value of the attribute id_UebergeordneteBesonderheit
     */
   String getID_UebergeordneteBesonderheit();

   /**
     * The maximum size of id_UebergeordneteBesonderheit
     * The size is limited by the database.
     */
   public static final int ID_UEBERGEORDNETEBESONDERHEIT_LENGTH = 13;

   /**
     * Sets the value of id_Listenkombination in the entity Besonderheit
     *
     * @param id_Listenkombination new value of the attribute id_Listenkombination
     */
   void setID_Listenkombination(String id_Listenkombination);

   /**
     * Gets the value of id_Listenkombination in the entity Besonderheit
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
     * Sets the value of id_Gruppe in the entity Besonderheit
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   void setID_Gruppe(String id_Gruppe);

   /**
     * Gets the value of id_Gruppe in the entity Besonderheit
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
     * Sets the value of id_Liste in the entity Besonderheit
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   void setID_Liste(String id_Liste);

   /**
     * Gets the value of id_Liste in the entity Besonderheit
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
     * Sets the value of id_Personendaten in the entity Besonderheit
     *
     * @param id_Personendaten new value of the attribute id_Personendaten
     */
   void setID_Personendaten(String id_Personendaten);

   /**
     * Gets the value of id_Personendaten in the entity Besonderheit
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
     * Sets the value of besonderheitart in the entity Besonderheit
     *
     * @param besonderheitart new value of the attribute besonderheitart
     */
   void setBesonderheitart(int besonderheitart);

   /**
     * Gets the value of besonderheitart in the entity Besonderheit
     *
     * @return value of the attribute besonderheitart
     */
   int getBesonderheitart();

   /**
     * Sets the value of anzahl in the entity Besonderheit
     *
     * @param anzahl new value of the attribute anzahl
     */
   void setAnzahl(int anzahl);

   /**
     * Gets the value of anzahl in the entity Besonderheit
     *
     * @return value of the attribute anzahl
     */
   int getAnzahl();

   /**
     * Sets the value of text in the entity Besonderheit
     *
     * @param text new value of the attribute text
     */
   void setText(String text);

   /**
     * Gets the value of text in the entity Besonderheit
     *
     * @return value of the attribute text
     */
   String getText();

   /**
     * The maximum size of text
     * The size is limited by the database.
     */
   public static final int TEXT_LENGTH = 4000;

   /**
     * Sets the value of nummer in the entity Besonderheit
     *
     * @param nummer new value of the attribute nummer
     */
   void setNummer(int nummer);

   /**
     * Gets the value of nummer in the entity Besonderheit
     *
     * @return value of the attribute nummer
     */
   int getNummer();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public BesonderheitModel copy();
}
