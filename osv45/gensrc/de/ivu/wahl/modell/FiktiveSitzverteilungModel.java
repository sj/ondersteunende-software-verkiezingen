/*
 * FiktiveSitzverteilungModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity FiktiveSitzverteilung.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface FiktiveSitzverteilungModel extends Model {
   /**
     * Gets the value of id_FiktiveSitzverteilung in the entity FiktiveSitzverteilung
     *
     * @return value of the attribute id_FiktiveSitzverteilung
     */
   String getID_FiktiveSitzverteilung();

   /**
     * The maximum size of id_FiktiveSitzverteilung
     * The size is limited by the database.
     */
   public static final int ID_FIKTIVESITZVERTEILUNG_LENGTH = 13;

   /**
     * Sets the value of id_Ergebniseingang in the entity FiktiveSitzverteilung
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   void setID_Ergebniseingang(String id_Ergebniseingang);

   /**
     * Gets the value of id_Ergebniseingang in the entity FiktiveSitzverteilung
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
     * Sets the value of id_Gruppe in the entity FiktiveSitzverteilung
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   void setID_Gruppe(String id_Gruppe);

   /**
     * Gets the value of id_Gruppe in the entity FiktiveSitzverteilung
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
     * Sets the value of sitzeGesamtzahl in the entity FiktiveSitzverteilung
     *
     * @param sitzeGesamtzahl new value of the attribute sitzeGesamtzahl
     */
   void setSitzeGesamtzahl(int sitzeGesamtzahl);

   /**
     * Gets the value of sitzeGesamtzahl in the entity FiktiveSitzverteilung
     *
     * @return value of the attribute sitzeGesamtzahl
     */
   int getSitzeGesamtzahl();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public FiktiveSitzverteilungModel copy();
}
