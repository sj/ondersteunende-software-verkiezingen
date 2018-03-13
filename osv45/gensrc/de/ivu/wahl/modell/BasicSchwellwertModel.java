/*
 * BasicSchwellwertModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import java.math.BigDecimal;
import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Schwellwert.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicSchwellwertModel extends Model {
   /**
     * Gets the value of id_Schwellwert in the entity Schwellwert
     *
     * @return value of the attribute id_Schwellwert
     */
   String getID_Schwellwert();

   /**
     * The maximum size of id_Schwellwert
     * The size is limited by the database.
     */
   public static final int ID_SCHWELLWERT_LENGTH = 13;

   /**
     * Sets the value of id_Wahl in the entity Schwellwert
     *
     * @param id_Wahl new value of the attribute id_Wahl
     */
   void setID_Wahl(String id_Wahl);

   /**
     * Gets the value of id_Wahl in the entity Schwellwert
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
     * Sets the value of name in the entity Schwellwert
     *
     * @param name new value of the attribute name
     */
   void setName(String name);

   /**
     * Gets the value of name in the entity Schwellwert
     *
     * @return value of the attribute name
     */
   String getName();

   /**
     * The maximum size of name
     * The size is limited by the database.
     */
   public static final int NAME_LENGTH = 200;

   /**
     * Sets the value of schwellwertart in the entity Schwellwert
     *
     * @param schwellwertart new value of the attribute schwellwertart
     */
   void setSchwellwertart(int schwellwertart);

   /**
     * Gets the value of schwellwertart in the entity Schwellwert
     *
     * @return value of the attribute schwellwertart
     */
   int getSchwellwertart();

   /**
     * Sets the value of wert in the entity Schwellwert
     *
     * @param wert new value of the attribute wert
     */
   void setWert(BigDecimal wert);

   /**
     * Gets the value of wert in the entity Schwellwert
     *
     * @return value of the attribute wert
     */
   BigDecimal getWert();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public SchwellwertModel copy();
}
