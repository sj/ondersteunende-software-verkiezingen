/*
 * ListenkandidaturErgebnisModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity ListenkandidaturErgebnis.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface ListenkandidaturErgebnisModel extends Model {
   /**
     * Gets the value of id_ListenkandidaturErgebnis in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute id_ListenkandidaturErgebnis
     */
   String getID_ListenkandidaturErgebnis();

   /**
     * The maximum size of id_ListenkandidaturErgebnis
     * The size is limited by the database.
     */
   public static final int ID_LISTENKANDIDATURERGEBNIS_LENGTH = 13;

   /**
     * Sets the value of id_Listenkandidatur in the entity ListenkandidaturErgebnis
     *
     * @param id_Listenkandidatur new value of the attribute id_Listenkandidatur
     */
   void setID_Listenkandidatur(String id_Listenkandidatur);

   /**
     * Gets the value of id_Listenkandidatur in the entity ListenkandidaturErgebnis
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
     * Sets the value of id_Ergebniseingang in the entity ListenkandidaturErgebnis
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   void setID_Ergebniseingang(String id_Ergebniseingang);

   /**
     * Gets the value of id_Ergebniseingang in the entity ListenkandidaturErgebnis
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
     * Sets the value of listenplatz in the entity ListenkandidaturErgebnis
     *
     * @param listenplatz new value of the attribute listenplatz
     */
   void setListenplatz(int listenplatz);

   /**
     * Gets the value of listenplatz in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute listenplatz
     */
   int getListenplatz();

   /**
     * Sets the value of gewaehlt in the entity ListenkandidaturErgebnis
     *
     * @param gewaehlt new value of the attribute gewaehlt
     */
   void setGewaehlt(boolean gewaehlt);

   /**
     * Gets the value of gewaehlt in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute gewaehlt
     */
   boolean isGewaehlt();

   /**
     * Sets the value of gewaehltInGruppe in the entity ListenkandidaturErgebnis
     *
     * @param gewaehltInGruppe new value of the attribute gewaehltInGruppe
     */
   void setGewaehltInGruppe(boolean gewaehltInGruppe);

   /**
     * Gets the value of gewaehltInGruppe in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute gewaehltInGruppe
     */
   boolean isGewaehltInGruppe();

   /**
     * Sets the value of bevorzugtGewaehlt in the entity ListenkandidaturErgebnis
     *
     * @param bevorzugtGewaehlt new value of the attribute bevorzugtGewaehlt
     */
   void setBevorzugtGewaehlt(boolean bevorzugtGewaehlt);

   /**
     * Gets the value of bevorzugtGewaehlt in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute bevorzugtGewaehlt
     */
   boolean isBevorzugtGewaehlt();

   /**
     * Sets the value of losteilnehmer in the entity ListenkandidaturErgebnis
     *
     * @param losteilnehmer new value of the attribute losteilnehmer
     */
   void setLosteilnehmer(boolean losteilnehmer);

   /**
     * Gets the value of losteilnehmer in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute losteilnehmer
     */
   boolean isLosteilnehmer();

   /**
     * Sets the value of losgewinner in the entity ListenkandidaturErgebnis
     *
     * @param losgewinner new value of the attribute losgewinner
     */
   void setLosgewinner(boolean losgewinner);

   /**
     * Gets the value of losgewinner in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute losgewinner
     */
   boolean isLosgewinner();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public ListenkandidaturErgebnisModel copy();
}
