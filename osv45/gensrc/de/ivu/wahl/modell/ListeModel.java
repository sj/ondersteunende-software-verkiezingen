/*
 * ListeModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Liste.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface ListeModel extends Model {
   /**
     * Gets the value of id_Liste in the entity Liste
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
     * Sets the value of id_Wahl in the entity Liste
     *
     * @param id_Wahl new value of the attribute id_Wahl
     */
   void setID_Wahl(String id_Wahl);

   /**
     * Gets the value of id_Wahl in the entity Liste
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
     * Sets the value of id_Gruppe in the entity Liste
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   void setID_Gruppe(String id_Gruppe);

   /**
     * Gets the value of id_Gruppe in the entity Liste
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
     * Sets the value of typ in the entity Liste
     *
     * @param typ new value of the attribute typ
     */
   void setTyp(String typ);

   /**
     * Gets the value of typ in the entity Liste
     *
     * @return value of the attribute typ
     */
   String getTyp();

   /**
     * The maximum size of typ
     * The size is limited by the database.
     */
   public static final int TYP_LENGTH = 30;

   /**
     * Sets the value of satz in the entity Liste
     *
     * @param satz new value of the attribute satz
     */
   void setSatz(int satz);

   /**
     * Gets the value of satz in the entity Liste
     *
     * @return value of the attribute satz
     */
   int getSatz();

   /**
     * Sets the value of name in the entity Liste
     *
     * @param name new value of the attribute name
     */
   void setName(String name);

   /**
     * Gets the value of name in the entity Liste
     *
     * @return value of the attribute name
     */
   String getName();

   /**
     * The maximum size of name
     * The size is limited by the database.
     */
   public static final int NAME_LENGTH = 80;

   /**
     * Sets the value of geschlechtSichtbar in the entity Liste
     *
     * @param geschlechtSichtbar new value of the attribute geschlechtSichtbar
     */
   void setGeschlechtSichtbar(boolean geschlechtSichtbar);

   /**
     * Gets the value of geschlechtSichtbar in the entity Liste
     *
     * @return value of the attribute geschlechtSichtbar
     */
   boolean isGeschlechtSichtbar();

   /**
     * Sets the value of publicationLanguage in the entity Liste
     *
     * @param publicationLanguage new value of the attribute publicationLanguage
     */
   void setPublicationLanguage(String publicationLanguage);

   /**
     * Gets the value of publicationLanguage in the entity Liste
     *
     * @return value of the attribute publicationLanguage
     */
   String getPublicationLanguage();

   /**
     * The maximum size of publicationLanguage
     * The size is limited by the database.
     */
   public static final int PUBLICATIONLANGUAGE_LENGTH = 5;

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public ListeModel copy();
}
