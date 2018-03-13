/*
 * AnwenderModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import java.sql.Timestamp;
import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Anwender.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface AnwenderModel extends Model {
   /**
     * Gets the value of id_Anwender in the entity Anwender
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
     * Sets the value of id_Gebiet in the entity Anwender
     *
     * @param id_Gebiet new value of the attribute id_Gebiet
     */
   void setID_Gebiet(String id_Gebiet);

   /**
     * Gets the value of id_Gebiet in the entity Anwender
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
     * Sets the value of name in the entity Anwender
     *
     * @param name new value of the attribute name
     */
   void setName(String name);

   /**
     * Gets the value of name in the entity Anwender
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
     * Sets the value of anwendername in the entity Anwender
     *
     * @param anwendername new value of the attribute anwendername
     */
   void setAnwendername(String anwendername);

   /**
     * Gets the value of anwendername in the entity Anwender
     *
     * @return value of the attribute anwendername
     */
   String getAnwendername();

   /**
     * The maximum size of anwendername
     * The size is limited by the database.
     */
   public static final int ANWENDERNAME_LENGTH = 200;

   /**
     * Sets the value of passwordHash in the entity Anwender
     *
     * @param passwordHash new value of the attribute passwordHash
     */
   void setPasswordHash(String passwordHash);

   /**
     * Gets the value of passwordHash in the entity Anwender
     *
     * @return value of the attribute passwordHash
     */
   String getPasswordHash();

   /**
     * The maximum size of passwordHash
     * The size is limited by the database.
     */
   public static final int PASSWORDHASH_LENGTH = 200;

   /**
     * Sets the value of salt in the entity Anwender
     *
     * @param salt new value of the attribute salt
     */
   void setSalt(String salt);

   /**
     * Gets the value of salt in the entity Anwender
     *
     * @return value of the attribute salt
     */
   String getSalt();

   /**
     * The maximum size of salt
     * The size is limited by the database.
     */
   public static final int SALT_LENGTH = 200;

   /**
     * Sets the value of fehlversucheAnmeldung in the entity Anwender
     *
     * @param fehlversucheAnmeldung new value of the attribute fehlversucheAnmeldung
     */
   void setFehlversucheAnmeldung(int fehlversucheAnmeldung);

   /**
     * Gets the value of fehlversucheAnmeldung in the entity Anwender
     *
     * @return value of the attribute fehlversucheAnmeldung
     */
   int getFehlversucheAnmeldung();

   /**
     * Sets the value of letzterZugriff in the entity Anwender
     *
     * @param letzterZugriff new value of the attribute letzterZugriff
     */
   void setLetzterZugriff(Timestamp letzterZugriff);

   /**
     * Gets the value of letzterZugriff in the entity Anwender
     *
     * @return value of the attribute letzterZugriff
     */
   Timestamp getLetzterZugriff();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public AnwenderModel copy();
}
