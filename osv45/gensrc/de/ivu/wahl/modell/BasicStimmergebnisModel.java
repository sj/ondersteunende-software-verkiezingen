/*
 * BasicStimmergebnisModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Stimmergebnis.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicStimmergebnisModel extends Model {
   /**
     * Gets the value of id_Stimmergebnis in the entity Stimmergebnis
     *
     * @return value of the attribute id_Stimmergebnis
     */
   String getID_Stimmergebnis();

   /**
     * The maximum size of id_Stimmergebnis
     * The size is limited by the database.
     */
   public static final int ID_STIMMERGEBNIS_LENGTH = 13;

   /**
     * Sets the value of id_Ergebniseingang in the entity Stimmergebnis
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   void setID_Ergebniseingang(String id_Ergebniseingang);

   /**
     * Gets the value of id_Ergebniseingang in the entity Stimmergebnis
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
     * Sets the value of id_Gebiet in the entity Stimmergebnis
     *
     * @param id_Gebiet new value of the attribute id_Gebiet
     */
   void setID_Gebiet(String id_Gebiet);

   /**
     * Gets the value of id_Gebiet in the entity Stimmergebnis
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
     * Sets the value of id_GruppeGebietsspezifisch in the entity Stimmergebnis
     *
     * @param id_GruppeGebietsspezifisch new value of the attribute id_GruppeGebietsspezifisch
     */
   void setID_GruppeGebietsspezifisch(String id_GruppeGebietsspezifisch);

   /**
     * Gets the value of id_GruppeGebietsspezifisch in the entity Stimmergebnis
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
     * Sets the value of id_Listenkandidatur in the entity Stimmergebnis
     *
     * @param id_Listenkandidatur new value of the attribute id_Listenkandidatur
     */
   void setID_Listenkandidatur(String id_Listenkandidatur);

   /**
     * Gets the value of id_Listenkandidatur in the entity Stimmergebnis
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
     * Sets the value of wahlergebnisart in the entity Stimmergebnis
     *
     * @param wahlergebnisart new value of the attribute wahlergebnisart
     */
   void setWahlergebnisart(int wahlergebnisart);

   /**
     * Gets the value of wahlergebnisart in the entity Stimmergebnis
     *
     * @return value of the attribute wahlergebnisart
     */
   int getWahlergebnisart();

   /**
     * Sets the value of stimmen in the entity Stimmergebnis
     *
     * @param stimmen new value of the attribute stimmen
     */
   void setStimmen(int stimmen);

   /**
     * Gets the value of stimmen in the entity Stimmergebnis
     *
     * @return value of the attribute stimmen
     */
   int getStimmen();

   /**
     * Sets the value of stimmart in the entity Stimmergebnis
     *
     * @param stimmart new value of the attribute stimmart
     */
   void setStimmart(int stimmart);

   /**
     * Gets the value of stimmart in the entity Stimmergebnis
     *
     * @return value of the attribute stimmart
     */
   int getStimmart();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public StimmergebnisModel copy();
}
