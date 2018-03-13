/*
 * KonfliktModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Konflikt.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface KonfliktModel extends Model {
   /**
     * Gets the value of id_Konflikt in the entity Konflikt
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
     * Sets the value of id_Ergebniseingang in the entity Konflikt
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   void setID_Ergebniseingang(String id_Ergebniseingang);

   /**
     * Gets the value of id_Ergebniseingang in the entity Konflikt
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
     * Sets the value of id_LosAlternative in the entity Konflikt
     *
     * @param id_LosAlternative new value of the attribute id_LosAlternative
     */
   void setID_LosAlternative(String id_LosAlternative);

   /**
     * Gets the value of id_LosAlternative in the entity Konflikt
     *
     * @return value of the attribute id_LosAlternative
     */
   String getID_LosAlternative();

   /**
     * The maximum size of id_LosAlternative
     * The size is limited by the database.
     */
   public static final int ID_LOSALTERNATIVE_LENGTH = 13;

   /**
     * Sets the value of nummer in the entity Konflikt
     *
     * @param nummer new value of the attribute nummer
     */
   void setNummer(int nummer);

   /**
     * Gets the value of nummer in the entity Konflikt
     *
     * @return value of the attribute nummer
     */
   int getNummer();

   /**
     * Sets the value of konfliktart in the entity Konflikt
     *
     * @param konfliktart new value of the attribute konfliktart
     */
   void setKonfliktart(int konfliktart);

   /**
     * Gets the value of konfliktart in the entity Konflikt
     *
     * @return value of the attribute konfliktart
     */
   int getKonfliktart();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public KonfliktModel copy();
}
