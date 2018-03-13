/*
 * ListenplatzNeuModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity ListenplatzNeu.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface ListenplatzNeuModel extends Model {
   /**
     * Gets the value of id_ListenplatzNeu in the entity ListenplatzNeu
     *
     * @return value of the attribute id_ListenplatzNeu
     */
   String getID_ListenplatzNeu();

   /**
     * The maximum size of id_ListenplatzNeu
     * The size is limited by the database.
     */
   public static final int ID_LISTENPLATZNEU_LENGTH = 13;

   /**
     * Sets the value of id_Liste in the entity ListenplatzNeu
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   void setID_Liste(String id_Liste);

   /**
     * Gets the value of id_Liste in the entity ListenplatzNeu
     *
     * @return value of the attribute id_Liste
     */
   String getID_Liste();

   /**
     * The maximum size of id_Liste
     * The size is limited by the database.
     */
   public static final int ID_LISTE_LENGTH = 13;

   /**
     * Sets the value of id_Ergebniseingang in the entity ListenplatzNeu
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   void setID_Ergebniseingang(String id_Ergebniseingang);

   /**
     * Gets the value of id_Ergebniseingang in the entity ListenplatzNeu
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
     * Sets the value of geaendert in the entity ListenplatzNeu
     *
     * @param geaendert new value of the attribute geaendert
     */
   void setGeaendert(boolean geaendert);

   /**
     * Gets the value of geaendert in the entity ListenplatzNeu
     *
     * @return value of the attribute geaendert
     */
   boolean isGeaendert();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public ListenplatzNeuModel copy();
}
