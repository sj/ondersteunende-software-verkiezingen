/*
 * BasicGebietModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Gebiet.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicGebietModel extends Model {
   /**
     * Gets the value of id_Gebiet in the entity Gebiet
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
     * Sets the value of id_UebergeordnetesGebiet in the entity Gebiet
     *
     * @param id_UebergeordnetesGebiet new value of the attribute id_UebergeordnetesGebiet
     */
   void setID_UebergeordnetesGebiet(String id_UebergeordnetesGebiet);

   /**
     * Gets the value of id_UebergeordnetesGebiet in the entity Gebiet
     *
     * @return value of the attribute id_UebergeordnetesGebiet
     */
   String getID_UebergeordnetesGebiet();

   /**
     * The maximum size of id_UebergeordnetesGebiet
     * The size is limited by the database.
     */
   public static final int ID_UEBERGEORDNETESGEBIET_LENGTH = 13;

   /**
     * Sets the value of id_Wahl in the entity Gebiet
     *
     * @param id_Wahl new value of the attribute id_Wahl
     */
   void setID_Wahl(String id_Wahl);

   /**
     * Gets the value of id_Wahl in the entity Gebiet
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
     * Sets the value of id_LetzterEingang in the entity Gebiet
     *
     * @param id_LetzterEingang new value of the attribute id_LetzterEingang
     */
   void setID_LetzterEingang(String id_LetzterEingang);

   /**
     * Gets the value of id_LetzterEingang in the entity Gebiet
     *
     * @return value of the attribute id_LetzterEingang
     */
   String getID_LetzterEingang();

   /**
     * The maximum size of id_LetzterEingang
     * The size is limited by the database.
     */
   public static final int ID_LETZTEREINGANG_LENGTH = 13;

   /**
     * Sets the value of erfassungseinheit in the entity Gebiet
     *
     * @param erfassungseinheit new value of the attribute erfassungseinheit
     */
   void setErfassungseinheit(boolean erfassungseinheit);

   /**
     * Gets the value of erfassungseinheit in the entity Gebiet
     *
     * @return value of the attribute erfassungseinheit
     */
   boolean isErfassungseinheit();

   /**
     * Sets the value of wahleinheit in the entity Gebiet
     *
     * @param wahleinheit new value of the attribute wahleinheit
     */
   void setWahleinheit(boolean wahleinheit);

   /**
     * Gets the value of wahleinheit in the entity Gebiet
     *
     * @return value of the attribute wahleinheit
     */
   boolean isWahleinheit();

   /**
     * Sets the value of gebietsart in the entity Gebiet
     *
     * @param gebietsart new value of the attribute gebietsart
     */
   void setGebietsart(int gebietsart);

   /**
     * Gets the value of gebietsart in the entity Gebiet
     *
     * @return value of the attribute gebietsart
     */
   int getGebietsart();

   /**
     * Sets the value of nummer in the entity Gebiet
     *
     * @param nummer new value of the attribute nummer
     */
   void setNummer(int nummer);

   /**
     * Gets the value of nummer in the entity Gebiet
     *
     * @return value of the attribute nummer
     */
   int getNummer();

   /**
     * Sets the value of roemisch in the entity Gebiet
     *
     * @param roemisch new value of the attribute roemisch
     */
   void setRoemisch(boolean roemisch);

   /**
     * Gets the value of roemisch in the entity Gebiet
     *
     * @return value of the attribute roemisch
     */
   boolean isRoemisch();

   /**
     * Sets the value of name in the entity Gebiet
     *
     * @param name new value of the attribute name
     */
   void setName(String name);

   /**
     * Gets the value of name in the entity Gebiet
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
     * Sets the value of kuerzel in the entity Gebiet
     *
     * @param kuerzel new value of the attribute kuerzel
     */
   void setKuerzel(String kuerzel);

   /**
     * Gets the value of kuerzel in the entity Gebiet
     *
     * @return value of the attribute kuerzel
     */
   String getKuerzel();

   /**
     * The maximum size of kuerzel
     * The size is limited by the database.
     */
   public static final int KUERZEL_LENGTH = 200;

   /**
     * Sets the value of position in the entity Gebiet
     *
     * @param position new value of the attribute position
     */
   void setPosition(int position);

   /**
     * Gets the value of position in the entity Gebiet
     *
     * @return value of the attribute position
     */
   int getPosition();

   /**
     * Sets the value of wahlberechtigte in the entity Gebiet
     *
     * @param wahlberechtigte new value of the attribute wahlberechtigte
     */
   void setWahlberechtigte(int wahlberechtigte);

   /**
     * Gets the value of wahlberechtigte in the entity Gebiet
     *
     * @return value of the attribute wahlberechtigte
     */
   int getWahlberechtigte();

   /**
     * Sets the value of gUIEingabeErlaubt in the entity Gebiet
     *
     * @param gUIEingabeErlaubt new value of the attribute gUIEingabeErlaubt
     */
   void setGUIEingabeErlaubt(boolean gUIEingabeErlaubt);

   /**
     * Gets the value of gUIEingabeErlaubt in the entity Gebiet
     *
     * @return value of the attribute gUIEingabeErlaubt
     */
   boolean isGUIEingabeErlaubt();

   /**
     * Sets the value of postalvote in the entity Gebiet
     *
     * @param postalvote new value of the attribute postalvote
     */
   void setPostalvote(boolean postalvote);

   /**
     * Gets the value of postalvote in the entity Gebiet
     *
     * @return value of the attribute postalvote
     */
   boolean isPostalvote();

   /**
     * Sets the value of voteValue in the entity Gebiet
     *
     * @param voteValue new value of the attribute voteValue
     */
   void setVoteValue(int voteValue);

   /**
     * Gets the value of voteValue in the entity Gebiet
     *
     * @return value of the attribute voteValue
     */
   int getVoteValue();

   /**
     * Sets the value of zipcode in the entity Gebiet
     *
     * @param zipcode new value of the attribute zipcode
     */
   void setZipcode(String zipcode);

   /**
     * Gets the value of zipcode in the entity Gebiet
     *
     * @return value of the attribute zipcode
     */
   String getZipcode();

   /**
     * The maximum size of zipcode
     * The size is limited by the database.
     */
   public static final int ZIPCODE_LENGTH = 12;

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public GebietModel copy();
}
