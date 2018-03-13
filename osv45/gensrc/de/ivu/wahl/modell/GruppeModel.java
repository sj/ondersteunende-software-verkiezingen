/*
 * GruppeModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Gruppe.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface GruppeModel extends Model {
   /**
     * Gets the value of id_Gruppe in the entity Gruppe
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
     * Sets the value of id_Wahl in the entity Gruppe
     *
     * @param id_Wahl new value of the attribute id_Wahl
     */
   void setID_Wahl(String id_Wahl);

   /**
     * Gets the value of id_Wahl in the entity Gruppe
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
     * Sets the value of id_Listenkombination in the entity Gruppe
     *
     * @param id_Listenkombination new value of the attribute id_Listenkombination
     */
   void setID_Listenkombination(String id_Listenkombination);

   /**
     * Gets the value of id_Listenkombination in the entity Gruppe
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
     * Sets the value of schluessel in the entity Gruppe
     *
     * @param schluessel new value of the attribute schluessel
     */
   void setSchluessel(int schluessel);

   /**
     * Gets the value of schluessel in the entity Gruppe
     *
     * @return value of the attribute schluessel
     */
   int getSchluessel();

   /**
     * Sets the value of gruppenart in the entity Gruppe
     *
     * @param gruppenart new value of the attribute gruppenart
     */
   void setGruppenart(int gruppenart);

   /**
     * Gets the value of gruppenart in the entity Gruppe
     *
     * @return value of the attribute gruppenart
     */
   int getGruppenart();

   /**
     * Sets the value of nameLang in the entity Gruppe
     *
     * @param nameLang new value of the attribute nameLang
     */
   void setNameLang(String nameLang);

   /**
     * Gets the value of nameLang in the entity Gruppe
     *
     * @return value of the attribute nameLang
     */
   String getNameLang();

   /**
     * The maximum size of nameLang
     * The size is limited by the database.
     */
   public static final int NAMELANG_LENGTH = 80;

   /**
     * Sets the value of nameKurz in the entity Gruppe
     *
     * @param nameKurz new value of the attribute nameKurz
     */
   void setNameKurz(String nameKurz);

   /**
     * Gets the value of nameKurz in the entity Gruppe
     *
     * @return value of the attribute nameKurz
     */
   String getNameKurz();

   /**
     * The maximum size of nameKurz
     * The size is limited by the database.
     */
   public static final int NAMEKURZ_LENGTH = 80;

   /**
     * Sets the value of kautionGestellt in the entity Gruppe
     *
     * @param kautionGestellt new value of the attribute kautionGestellt
     */
   void setKautionGestellt(boolean kautionGestellt);

   /**
     * Gets the value of kautionGestellt in the entity Gruppe
     *
     * @return value of the attribute kautionGestellt
     */
   boolean isKautionGestellt();

   /**
     * Sets the value of farbe in the entity Gruppe
     *
     * @param farbe new value of the attribute farbe
     */
   void setFarbe(String farbe);

   /**
     * Gets the value of farbe in the entity Gruppe
     *
     * @return value of the attribute farbe
     */
   String getFarbe();

   /**
     * The maximum size of farbe
     * The size is limited by the database.
     */
   public static final int FARBE_LENGTH = 200;

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public GruppeModel copy();
}
