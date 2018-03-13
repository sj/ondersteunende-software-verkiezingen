/*
 * ErgebniseingangModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import java.sql.Timestamp;
import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Ergebniseingang.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface ErgebniseingangModel extends Model {
   /**
     * Gets the value of id_Ergebniseingang in the entity Ergebniseingang
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
     * Sets the value of id_Erfassungseinheit in the entity Ergebniseingang
     *
     * @param id_Erfassungseinheit new value of the attribute id_Erfassungseinheit
     */
   void setID_Erfassungseinheit(String id_Erfassungseinheit);

   /**
     * Gets the value of id_Erfassungseinheit in the entity Ergebniseingang
     *
     * @return value of the attribute id_Erfassungseinheit
     */
   String getID_Erfassungseinheit();

   /**
     * The maximum size of id_Erfassungseinheit
     * The size is limited by the database.
     */
   public static final int ID_ERFASSUNGSEINHEIT_LENGTH = 13;

   /**
     * Sets the value of id_Wahl in the entity Ergebniseingang
     *
     * @param id_Wahl new value of the attribute id_Wahl
     */
   void setID_Wahl(String id_Wahl);

   /**
     * Gets the value of id_Wahl in the entity Ergebniseingang
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
     * Sets the value of anwenderName in the entity Ergebniseingang
     *
     * @param anwenderName new value of the attribute anwenderName
     */
   void setAnwenderName(String anwenderName);

   /**
     * Gets the value of anwenderName in the entity Ergebniseingang
     *
     * @return value of the attribute anwenderName
     */
   String getAnwenderName();

   /**
     * The maximum size of anwenderName
     * The size is limited by the database.
     */
   public static final int ANWENDERNAME_LENGTH = 200;

   /**
     * Sets the value of zeitstempel in the entity Ergebniseingang
     *
     * @param zeitstempel new value of the attribute zeitstempel
     */
   void setZeitstempel(Timestamp zeitstempel);

   /**
     * Gets the value of zeitstempel in the entity Ergebniseingang
     *
     * @return value of the attribute zeitstempel
     */
   Timestamp getZeitstempel();

   /**
     * Sets the value of herkunft in the entity Ergebniseingang
     *
     * @param herkunft new value of the attribute herkunft
     */
   void setHerkunft(int herkunft);

   /**
     * Gets the value of herkunft in the entity Ergebniseingang
     *
     * @return value of the attribute herkunft
     */
   int getHerkunft();

   /**
     * Sets the value of wahlergebnisart in the entity Ergebniseingang
     *
     * @param wahlergebnisart new value of the attribute wahlergebnisart
     */
   void setWahlergebnisart(int wahlergebnisart);

   /**
     * Gets the value of wahlergebnisart in the entity Ergebniseingang
     *
     * @return value of the attribute wahlergebnisart
     */
   int getWahlergebnisart();

   /**
     * Sets the value of unterschiedeVorhanden in the entity Ergebniseingang
     *
     * @param unterschiedeVorhanden new value of the attribute unterschiedeVorhanden
     */
   void setUnterschiedeVorhanden(int unterschiedeVorhanden);

   /**
     * Gets the value of unterschiedeVorhanden in the entity Ergebniseingang
     *
     * @return value of the attribute unterschiedeVorhanden
     */
   int getUnterschiedeVorhanden();

   /**
     * Sets the value of status in the entity Ergebniseingang
     *
     * @param status new value of the attribute status
     */
   void setStatus(int status);

   /**
     * Gets the value of status in the entity Ergebniseingang
     *
     * @return value of the attribute status
     */
   int getStatus();

   /**
     * Sets the value of ergebnisHash in the entity Ergebniseingang
     *
     * @param ergebnisHash new value of the attribute ergebnisHash
     */
   void setErgebnisHash(String ergebnisHash);

   /**
     * Gets the value of ergebnisHash in the entity Ergebniseingang
     *
     * @return value of the attribute ergebnisHash
     */
   String getErgebnisHash();

   /**
     * The maximum size of ergebnisHash
     * The size is limited by the database.
     */
   public static final int ERGEBNISHASH_LENGTH = 100;

   /**
     * Sets the value of fehlermeldung in the entity Ergebniseingang
     *
     * @param fehlermeldung new value of the attribute fehlermeldung
     */
   void setFehlermeldung(String fehlermeldung);

   /**
     * Gets the value of fehlermeldung in the entity Ergebniseingang
     *
     * @return value of the attribute fehlermeldung
     */
   String getFehlermeldung();

   /**
     * The maximum size of fehlermeldung
     * The size is limited by the database.
     */
   public static final int FEHLERMELDUNG_LENGTH = 16777215;

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public ErgebniseingangModel copy();
}
