/*
 * BasicPersonendatenModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Personendaten.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicPersonendatenModel extends Model {
   /**
     * Gets the value of id_Personendaten in the entity Personendaten
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
     * Sets the value of id_PersonendatenAgent in the entity Personendaten
     *
     * @param id_PersonendatenAgent new value of the attribute id_PersonendatenAgent
     */
   void setID_PersonendatenAgent(String id_PersonendatenAgent);

   /**
     * Gets the value of id_PersonendatenAgent in the entity Personendaten
     *
     * @return value of the attribute id_PersonendatenAgent
     */
   String getID_PersonendatenAgent();

   /**
     * The maximum size of id_PersonendatenAgent
     * The size is limited by the database.
     */
   public static final int ID_PERSONENDATENAGENT_LENGTH = 15;

   /**
     * Sets the value of nachname in the entity Personendaten
     *
     * @param nachname new value of the attribute nachname
     */
   void setNachname(String nachname);

   /**
     * Gets the value of nachname in the entity Personendaten
     *
     * @return value of the attribute nachname
     */
   String getNachname();

   /**
     * The maximum size of nachname
     * The size is limited by the database.
     */
   public static final int NACHNAME_LENGTH = 200;

   /**
     * Sets the value of vorname in the entity Personendaten
     *
     * @param vorname new value of the attribute vorname
     */
   void setVorname(String vorname);

   /**
     * Gets the value of vorname in the entity Personendaten
     *
     * @return value of the attribute vorname
     */
   String getVorname();

   /**
     * The maximum size of vorname
     * The size is limited by the database.
     */
   public static final int VORNAME_LENGTH = 200;

   /**
     * Sets the value of praefix in the entity Personendaten
     *
     * @param praefix new value of the attribute praefix
     */
   void setPraefix(String praefix);

   /**
     * Gets the value of praefix in the entity Personendaten
     *
     * @return value of the attribute praefix
     */
   String getPraefix();

   /**
     * The maximum size of praefix
     * The size is limited by the database.
     */
   public static final int PRAEFIX_LENGTH = 20;

   /**
     * Sets the value of initialen in the entity Personendaten
     *
     * @param initialen new value of the attribute initialen
     */
   void setInitialen(String initialen);

   /**
     * Gets the value of initialen in the entity Personendaten
     *
     * @return value of the attribute initialen
     */
   String getInitialen();

   /**
     * The maximum size of initialen
     * The size is limited by the database.
     */
   public static final int INITIALEN_LENGTH = 20;

   /**
     * Sets the value of titel in the entity Personendaten
     *
     * @param titel new value of the attribute titel
     */
   void setTitel(String titel);

   /**
     * Gets the value of titel in the entity Personendaten
     *
     * @return value of the attribute titel
     */
   String getTitel();

   /**
     * The maximum size of titel
     * The size is limited by the database.
     */
   public static final int TITEL_LENGTH = 200;

   /**
     * Sets the value of geschlecht in the entity Personendaten
     *
     * @param geschlecht new value of the attribute geschlecht
     */
   void setGeschlecht(int geschlecht);

   /**
     * Gets the value of geschlecht in the entity Personendaten
     *
     * @return value of the attribute geschlecht
     */
   int getGeschlecht();

   /**
     * Sets the value of generation in the entity Personendaten
     *
     * @param generation new value of the attribute generation
     */
   void setGeneration(String generation);

   /**
     * Gets the value of generation in the entity Personendaten
     *
     * @return value of the attribute generation
     */
   String getGeneration();

   /**
     * The maximum size of generation
     * The size is limited by the database.
     */
   public static final int GENERATION_LENGTH = 10;

   /**
     * Sets the value of land in the entity Personendaten
     *
     * @param land new value of the attribute land
     */
   void setLand(String land);

   /**
     * Gets the value of land in the entity Personendaten
     *
     * @return value of the attribute land
     */
   String getLand();

   /**
     * The maximum size of land
     * The size is limited by the database.
     */
   public static final int LAND_LENGTH = 5;

   /**
     * Sets the value of wohnort in the entity Personendaten
     *
     * @param wohnort new value of the attribute wohnort
     */
   void setWohnort(String wohnort);

   /**
     * Gets the value of wohnort in the entity Personendaten
     *
     * @return value of the attribute wohnort
     */
   String getWohnort();

   /**
     * The maximum size of wohnort
     * The size is limited by the database.
     */
   public static final int WOHNORT_LENGTH = 200;

   /**
     * Sets the value of kontakt_Land in the entity Personendaten
     *
     * @param kontakt_Land new value of the attribute kontakt_Land
     */
   void setKontakt_Land(String kontakt_Land);

   /**
     * Gets the value of kontakt_Land in the entity Personendaten
     *
     * @return value of the attribute kontakt_Land
     */
   String getKontakt_Land();

   /**
     * The maximum size of kontakt_Land
     * The size is limited by the database.
     */
   public static final int KONTAKT_LAND_LENGTH = 5;

   /**
     * Sets the value of kontakt_Wohnort in the entity Personendaten
     *
     * @param kontakt_Wohnort new value of the attribute kontakt_Wohnort
     */
   void setKontakt_Wohnort(String kontakt_Wohnort);

   /**
     * Gets the value of kontakt_Wohnort in the entity Personendaten
     *
     * @return value of the attribute kontakt_Wohnort
     */
   String getKontakt_Wohnort();

   /**
     * The maximum size of kontakt_Wohnort
     * The size is limited by the database.
     */
   public static final int KONTAKT_WOHNORT_LENGTH = 200;

   /**
     * Sets the value of kontakt_PLZ in the entity Personendaten
     *
     * @param kontakt_PLZ new value of the attribute kontakt_PLZ
     */
   void setKontakt_PLZ(String kontakt_PLZ);

   /**
     * Gets the value of kontakt_PLZ in the entity Personendaten
     *
     * @return value of the attribute kontakt_PLZ
     */
   String getKontakt_PLZ();

   /**
     * The maximum size of kontakt_PLZ
     * The size is limited by the database.
     */
   public static final int KONTAKT_PLZ_LENGTH = 200;

   /**
     * Sets the value of kontakt_Strasse in the entity Personendaten
     *
     * @param kontakt_Strasse new value of the attribute kontakt_Strasse
     */
   void setKontakt_Strasse(String kontakt_Strasse);

   /**
     * Gets the value of kontakt_Strasse in the entity Personendaten
     *
     * @return value of the attribute kontakt_Strasse
     */
   String getKontakt_Strasse();

   /**
     * The maximum size of kontakt_Strasse
     * The size is limited by the database.
     */
   public static final int KONTAKT_STRASSE_LENGTH = 200;

   /**
     * Sets the value of benennbar in the entity Personendaten
     *
     * @param benennbar new value of the attribute benennbar
     */
   void setBenennbar(boolean benennbar);

   /**
     * Gets the value of benennbar in the entity Personendaten
     *
     * @return value of the attribute benennbar
     */
   boolean isBenennbar();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public PersonendatenModel copy();
}
