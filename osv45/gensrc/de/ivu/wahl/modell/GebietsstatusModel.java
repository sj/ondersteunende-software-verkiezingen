/*
 * GebietsstatusModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Gebietsstatus.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface GebietsstatusModel extends Model {
   /**
     * Gets the value of id_Gebietsstatus in the entity Gebietsstatus
     *
     * @return value of the attribute id_Gebietsstatus
     */
   String getID_Gebietsstatus();

   /**
     * The maximum size of id_Gebietsstatus
     * The size is limited by the database.
     */
   public static final int ID_GEBIETSSTATUS_LENGTH = 13;

   /**
     * Sets the value of id_Ergebniseingang in the entity Gebietsstatus
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   void setID_Ergebniseingang(String id_Ergebniseingang);

   /**
     * Gets the value of id_Ergebniseingang in the entity Gebietsstatus
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
     * Sets the value of id_Gebiet in the entity Gebietsstatus
     *
     * @param id_Gebiet new value of the attribute id_Gebiet
     */
   void setID_Gebiet(String id_Gebiet);

   /**
     * Gets the value of id_Gebiet in the entity Gebietsstatus
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
     * Sets the value of wahlergebnisart in the entity Gebietsstatus
     *
     * @param wahlergebnisart new value of the attribute wahlergebnisart
     */
   void setWahlergebnisart(int wahlergebnisart);

   /**
     * Gets the value of wahlergebnisart in the entity Gebietsstatus
     *
     * @return value of the attribute wahlergebnisart
     */
   int getWahlergebnisart();

   /**
     * Sets the value of korrekturnummer in the entity Gebietsstatus
     *
     * @param korrekturnummer new value of the attribute korrekturnummer
     */
   void setKorrekturnummer(int korrekturnummer);

   /**
     * Gets the value of korrekturnummer in the entity Gebietsstatus
     *
     * @return value of the attribute korrekturnummer
     */
   int getKorrekturnummer();

   /**
     * Sets the value of anzahlErgebnisseKumuliert in the entity Gebietsstatus
     *
     * @param anzahlErgebnisseKumuliert new value of the attribute anzahlErgebnisseKumuliert
     */
   void setAnzahlErgebnisseKumuliert(int anzahlErgebnisseKumuliert);

   /**
     * Gets the value of anzahlErgebnisseKumuliert in the entity Gebietsstatus
     *
     * @return value of the attribute anzahlErgebnisseKumuliert
     */
   int getAnzahlErgebnisseKumuliert();

   /**
     * Sets the value of vollstaendig in the entity Gebietsstatus
     *
     * @param vollstaendig new value of the attribute vollstaendig
     */
   void setVollstaendig(boolean vollstaendig);

   /**
     * Gets the value of vollstaendig in the entity Gebietsstatus
     *
     * @return value of the attribute vollstaendig
     */
   boolean isVollstaendig();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public GebietsstatusModel copy();
}
