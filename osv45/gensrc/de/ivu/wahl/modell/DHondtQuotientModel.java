/*
 * DHondtQuotientModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity DHondtQuotient.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface DHondtQuotientModel extends Model {
   /**
     * Gets the value of iD_DHondtQuotient in the entity DHondtQuotient
     *
     * @return value of the attribute iD_DHondtQuotient
     */
   String getID_DHondtQuotient();

   /**
     * The maximum size of iD_DHondtQuotient
     * The size is limited by the database.
     */
   public static final int ID_DHONDTQUOTIENT_LENGTH = 13;

   /**
     * Sets the value of id_Restsitzverteilung in the entity DHondtQuotient
     *
     * @param id_Restsitzverteilung new value of the attribute id_Restsitzverteilung
     */
   void setID_Restsitzverteilung(String id_Restsitzverteilung);

   /**
     * Gets the value of id_Restsitzverteilung in the entity DHondtQuotient
     *
     * @return value of the attribute id_Restsitzverteilung
     */
   String getID_Restsitzverteilung();

   /**
     * The maximum size of id_Restsitzverteilung
     * The size is limited by the database.
     */
   public static final int ID_RESTSITZVERTEILUNG_LENGTH = 13;

   /**
     * Sets the value of lauf in the entity DHondtQuotient
     *
     * @param lauf new value of the attribute lauf
     */
   void setLauf(int lauf);

   /**
     * Gets the value of lauf in the entity DHondtQuotient
     *
     * @return value of the attribute lauf
     */
   int getLauf();

   /**
     * Sets the value of zaehler in the entity DHondtQuotient
     *
     * @param zaehler new value of the attribute zaehler
     */
   void setZaehler(int zaehler);

   /**
     * Gets the value of zaehler in the entity DHondtQuotient
     *
     * @return value of the attribute zaehler
     */
   int getZaehler();

   /**
     * Sets the value of nenner in the entity DHondtQuotient
     *
     * @param nenner new value of the attribute nenner
     */
   void setNenner(int nenner);

   /**
     * Gets the value of nenner in the entity DHondtQuotient
     *
     * @return value of the attribute nenner
     */
   int getNenner();

   /**
     * Sets the value of sitzAusRestanteil in the entity DHondtQuotient
     *
     * @param sitzAusRestanteil new value of the attribute sitzAusRestanteil
     */
   void setSitzAusRestanteil(boolean sitzAusRestanteil);

   /**
     * Gets the value of sitzAusRestanteil in the entity DHondtQuotient
     *
     * @return value of the attribute sitzAusRestanteil
     */
   boolean isSitzAusRestanteil();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public DHondtQuotientModel copy();
}
