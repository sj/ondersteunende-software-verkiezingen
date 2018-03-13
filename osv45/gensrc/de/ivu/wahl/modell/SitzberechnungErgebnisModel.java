/*
 * SitzberechnungErgebnisModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity SitzberechnungErgebnis.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface SitzberechnungErgebnisModel extends Model {
   /**
     * Gets the value of id_SitzberechnungErgebnis in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute id_SitzberechnungErgebnis
     */
   String getID_SitzberechnungErgebnis();

   /**
     * The maximum size of id_SitzberechnungErgebnis
     * The size is limited by the database.
     */
   public static final int ID_SITZBERECHNUNGERGEBNIS_LENGTH = 13;

   /**
     * Sets the value of id_Ergebniseingang in the entity SitzberechnungErgebnis
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   void setID_Ergebniseingang(String id_Ergebniseingang);

   /**
     * Gets the value of id_Ergebniseingang in the entity SitzberechnungErgebnis
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
     * Sets the value of id_Liste in the entity SitzberechnungErgebnis
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   void setID_Liste(String id_Liste);

   /**
     * Gets the value of id_Liste in the entity SitzberechnungErgebnis
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
     * Sets the value of id_Gruppe in the entity SitzberechnungErgebnis
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   void setID_Gruppe(String id_Gruppe);

   /**
     * Gets the value of id_Gruppe in the entity SitzberechnungErgebnis
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
     * Sets the value of id_Listenkombination in the entity SitzberechnungErgebnis
     *
     * @param id_Listenkombination new value of the attribute id_Listenkombination
     */
   void setID_Listenkombination(String id_Listenkombination);

   /**
     * Gets the value of id_Listenkombination in the entity SitzberechnungErgebnis
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
     * Sets the value of verteilung in the entity SitzberechnungErgebnis
     *
     * @param verteilung new value of the attribute verteilung
     */
   void setVerteilung(int verteilung);

   /**
     * Gets the value of verteilung in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute verteilung
     */
   int getVerteilung();

   /**
     * Sets the value of schrittnummer in the entity SitzberechnungErgebnis
     *
     * @param schrittnummer new value of the attribute schrittnummer
     */
   void setSchrittnummer(int schrittnummer);

   /**
     * Gets the value of schrittnummer in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute schrittnummer
     */
   int getSchrittnummer();

   /**
     * Sets the value of schritttyp in the entity SitzberechnungErgebnis
     *
     * @param schritttyp new value of the attribute schritttyp
     */
   void setSchritttyp(int schritttyp);

   /**
     * Gets the value of schritttyp in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute schritttyp
     */
   int getSchritttyp();

   /**
     * Sets the value of sitze in the entity SitzberechnungErgebnis
     *
     * @param sitze new value of the attribute sitze
     */
   void setSitze(int sitze);

   /**
     * Gets the value of sitze in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute sitze
     */
   int getSitze();

   /**
     * Sets the value of zaehler in the entity SitzberechnungErgebnis
     *
     * @param zaehler new value of the attribute zaehler
     */
   void setZaehler(int zaehler);

   /**
     * Gets the value of zaehler in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute zaehler
     */
   int getZaehler();

   /**
     * Sets the value of nenner in the entity SitzberechnungErgebnis
     *
     * @param nenner new value of the attribute nenner
     */
   void setNenner(int nenner);

   /**
     * Gets the value of nenner in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute nenner
     */
   int getNenner();

   /**
     * Sets the value of zaehlerVomNenner in the entity SitzberechnungErgebnis
     *
     * @param zaehlerVomNenner new value of the attribute zaehlerVomNenner
     */
   void setZaehlerVomNenner(int zaehlerVomNenner);

   /**
     * Gets the value of zaehlerVomNenner in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute zaehlerVomNenner
     */
   int getZaehlerVomNenner();

   /**
     * Sets the value of nennerVomNenner in the entity SitzberechnungErgebnis
     *
     * @param nennerVomNenner new value of the attribute nennerVomNenner
     */
   void setNennerVomNenner(int nennerVomNenner);

   /**
     * Gets the value of nennerVomNenner in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute nennerVomNenner
     */
   int getNennerVomNenner();

   /**
     * Sets the value of zaehlerVomRest in the entity SitzberechnungErgebnis
     *
     * @param zaehlerVomRest new value of the attribute zaehlerVomRest
     */
   void setZaehlerVomRest(int zaehlerVomRest);

   /**
     * Gets the value of zaehlerVomRest in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute zaehlerVomRest
     */
   int getZaehlerVomRest();

   /**
     * Sets the value of nennerVomRest in the entity SitzberechnungErgebnis
     *
     * @param nennerVomRest new value of the attribute nennerVomRest
     */
   void setNennerVomRest(int nennerVomRest);

   /**
     * Gets the value of nennerVomRest in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute nennerVomRest
     */
   int getNennerVomRest();

   /**
     * Sets the value of losentscheid in the entity SitzberechnungErgebnis
     *
     * @param losentscheid new value of the attribute losentscheid
     */
   void setLosentscheid(boolean losentscheid);

   /**
     * Gets the value of losentscheid in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute losentscheid
     */
   boolean isLosentscheid();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public SitzberechnungErgebnisModel copy();
}
