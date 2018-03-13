/*
 * UnterverteilungModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Unterverteilung.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface UnterverteilungModel extends Model {
   /**
     * Gets the value of id_Unterverteilung in the entity Unterverteilung
     *
     * @return value of the attribute id_Unterverteilung
     */
   String getID_Unterverteilung();

   /**
     * The maximum size of id_Unterverteilung
     * The size is limited by the database.
     */
   public static final int ID_UNTERVERTEILUNG_LENGTH = 13;

   /**
     * Sets the value of id_Ergebniseingang in the entity Unterverteilung
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   void setID_Ergebniseingang(String id_Ergebniseingang);

   /**
     * Gets the value of id_Ergebniseingang in the entity Unterverteilung
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
     * Sets the value of id_Gruppe in the entity Unterverteilung
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   void setID_Gruppe(String id_Gruppe);

   /**
     * Gets the value of id_Gruppe in the entity Unterverteilung
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
     * Sets the value of id_Listenkombination in the entity Unterverteilung
     *
     * @param id_Listenkombination new value of the attribute id_Listenkombination
     */
   void setID_Listenkombination(String id_Listenkombination);

   /**
     * Gets the value of id_Listenkombination in the entity Unterverteilung
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
     * Sets the value of stimmen in the entity Unterverteilung
     *
     * @param stimmen new value of the attribute stimmen
     */
   void setStimmen(int stimmen);

   /**
     * Gets the value of stimmen in the entity Unterverteilung
     *
     * @return value of the attribute stimmen
     */
   int getStimmen();

   /**
     * Sets the value of sitze in the entity Unterverteilung
     *
     * @param sitze new value of the attribute sitze
     */
   void setSitze(int sitze);

   /**
     * Gets the value of sitze in the entity Unterverteilung
     *
     * @return value of the attribute sitze
     */
   int getSitze();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public UnterverteilungModel copy();
}
