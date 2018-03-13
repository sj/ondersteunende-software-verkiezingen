/*
 * BasicWahlModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import java.sql.Timestamp;
import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity Wahl.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface BasicWahlModel extends Model {
   /**
     * Gets the value of id_Wahl in the entity Wahl
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
     * Sets the value of id_Wurzelgebiet in the entity Wahl
     *
     * @param id_Wurzelgebiet new value of the attribute id_Wurzelgebiet
     */
   void setID_Wurzelgebiet(String id_Wurzelgebiet);

   /**
     * Gets the value of id_Wurzelgebiet in the entity Wahl
     *
     * @return value of the attribute id_Wurzelgebiet
     */
   String getID_Wurzelgebiet();

   /**
     * The maximum size of id_Wurzelgebiet
     * The size is limited by the database.
     */
   public static final int ID_WURZELGEBIET_LENGTH = 13;

   /**
     * Sets the value of id_Wahlgebiet in the entity Wahl
     *
     * @param id_Wahlgebiet new value of the attribute id_Wahlgebiet
     */
   void setID_Wahlgebiet(String id_Wahlgebiet);

   /**
     * Gets the value of id_Wahlgebiet in the entity Wahl
     *
     * @return value of the attribute id_Wahlgebiet
     */
   String getID_Wahlgebiet();

   /**
     * The maximum size of id_Wahlgebiet
     * The size is limited by the database.
     */
   public static final int ID_WAHLGEBIET_LENGTH = 13;

   /**
     * Sets the value of wahlart in the entity Wahl
     *
     * @param wahlart new value of the attribute wahlart
     */
   void setWahlart(int wahlart);

   /**
     * Gets the value of wahlart in the entity Wahl
     *
     * @return value of the attribute wahlart
     */
   int getWahlart();

   /**
     * Sets the value of wahlebene in the entity Wahl
     *
     * @param wahlebene new value of the attribute wahlebene
     */
   void setWahlebene(int wahlebene);

   /**
     * Gets the value of wahlebene in the entity Wahl
     *
     * @return value of the attribute wahlebene
     */
   int getWahlebene();

   /**
     * Sets the value of wahlkategorie in the entity Wahl
     *
     * @param wahlkategorie new value of the attribute wahlkategorie
     */
   void setWahlkategorie(String wahlkategorie);

   /**
     * Gets the value of wahlkategorie in the entity Wahl
     *
     * @return value of the attribute wahlkategorie
     */
   String getWahlkategorie();

   /**
     * The maximum size of wahlkategorie
     * The size is limited by the database.
     */
   public static final int WAHLKATEGORIE_LENGTH = 5;

   /**
     * Sets the value of name in the entity Wahl
     *
     * @param name new value of the attribute name
     */
   void setName(String name);

   /**
     * Gets the value of name in the entity Wahl
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
     * Sets the value of electionDomain in the entity Wahl
     *
     * @param electionDomain new value of the attribute electionDomain
     */
   void setElectionDomain(String electionDomain);

   /**
     * Gets the value of electionDomain in the entity Wahl
     *
     * @return value of the attribute electionDomain
     */
   String getElectionDomain();

   /**
     * The maximum size of electionDomain
     * The size is limited by the database.
     */
   public static final int ELECTIONDOMAIN_LENGTH = 200;

   /**
     * Sets the value of electionDomainId in the entity Wahl
     *
     * @param electionDomainId new value of the attribute electionDomainId
     */
   void setElectionDomainId(String electionDomainId);

   /**
     * Gets the value of electionDomainId in the entity Wahl
     *
     * @return value of the attribute electionDomainId
     */
   String getElectionDomainId();

   /**
     * The maximum size of electionDomainId
     * The size is limited by the database.
     */
   public static final int ELECTIONDOMAINID_LENGTH = 4;

   /**
     * Sets the value of termin in the entity Wahl
     *
     * @param termin new value of the attribute termin
     */
   void setTermin(Timestamp termin);

   /**
     * Gets the value of termin in the entity Wahl
     *
     * @return value of the attribute termin
     */
   Timestamp getTermin();

   /**
     * Sets the value of vorrangschwelle in the entity Wahl
     *
     * @param vorrangschwelle new value of the attribute vorrangschwelle
     */
   void setVorrangschwelle(int vorrangschwelle);

   /**
     * Gets the value of vorrangschwelle in the entity Wahl
     *
     * @return value of the attribute vorrangschwelle
     */
   int getVorrangschwelle();

   /**
     * Sets the value of anzahlSitze in the entity Wahl
     *
     * @param anzahlSitze new value of the attribute anzahlSitze
     */
   void setAnzahlSitze(int anzahlSitze);

   /**
     * Gets the value of anzahlSitze in the entity Wahl
     *
     * @return value of the attribute anzahlSitze
     */
   int getAnzahlSitze();

   /**
     * Sets the value of gebietsartAuswertungseinheit in the entity Wahl
     *
     * @param gebietsartAuswertungseinheit new value of the attribute gebietsartAuswertungseinheit
     */
   void setGebietsartAuswertungseinheit(int gebietsartAuswertungseinheit);

   /**
     * Gets the value of gebietsartAuswertungseinheit in the entity Wahl
     *
     * @return value of the attribute gebietsartAuswertungseinheit
     */
   int getGebietsartAuswertungseinheit();

   /**
     * Sets the value of gebietsartErfassungseinheit in the entity Wahl
     *
     * @param gebietsartErfassungseinheit new value of the attribute gebietsartErfassungseinheit
     */
   void setGebietsartErfassungseinheit(int gebietsartErfassungseinheit);

   /**
     * Gets the value of gebietsartErfassungseinheit in the entity Wahl
     *
     * @return value of the attribute gebietsartErfassungseinheit
     */
   int getGebietsartErfassungseinheit();

   /**
     * Sets the value of aktuelleWahlergebnisart in the entity Wahl
     *
     * @param aktuelleWahlergebnisart new value of the attribute aktuelleWahlergebnisart
     */
   void setAktuelleWahlergebnisart(int aktuelleWahlergebnisart);

   /**
     * Gets the value of aktuelleWahlergebnisart in the entity Wahl
     *
     * @return value of the attribute aktuelleWahlergebnisart
     */
   int getAktuelleWahlergebnisart();

   /**
     * Sets the value of datumNominierung in the entity Wahl
     *
     * @param datumNominierung new value of the attribute datumNominierung
     */
   void setDatumNominierung(Timestamp datumNominierung);

   /**
     * Gets the value of datumNominierung in the entity Wahl
     *
     * @return value of the attribute datumNominierung
     */
   Timestamp getDatumNominierung();

   /**
     * Sets the value of standMetadaten in the entity Wahl
     *
     * @param standMetadaten new value of the attribute standMetadaten
     */
   void setStandMetadaten(Timestamp standMetadaten);

   /**
     * Gets the value of standMetadaten in the entity Wahl
     *
     * @return value of the attribute standMetadaten
     */
   Timestamp getStandMetadaten();

   /**
     * Sets the value of geschlossenMetadaten in the entity Wahl
     *
     * @param geschlossenMetadaten new value of the attribute geschlossenMetadaten
     */
   void setGeschlossenMetadaten(Timestamp geschlossenMetadaten);

   /**
     * Gets the value of geschlossenMetadaten in the entity Wahl
     *
     * @return value of the attribute geschlossenMetadaten
     */
   Timestamp getGeschlossenMetadaten();

   /**
     * Sets the value of status in the entity Wahl
     *
     * @param status new value of the attribute status
     */
   void setStatus(int status);

   /**
     * Gets the value of status in the entity Wahl
     *
     * @return value of the attribute status
     */
   int getStatus();

   /**
     * Sets the value of freigegeben in the entity Wahl
     *
     * @param freigegeben new value of the attribute freigegeben
     */
   void setFreigegeben(Timestamp freigegeben);

   /**
     * Gets the value of freigegeben in the entity Wahl
     *
     * @return value of the attribute freigegeben
     */
   Timestamp getFreigegeben();

   /**
     * Sets the value of letzteAenderung in the entity Wahl
     *
     * @param letzteAenderung new value of the attribute letzteAenderung
     */
   void setLetzteAenderung(Timestamp letzteAenderung);

   /**
     * Gets the value of letzteAenderung in the entity Wahl
     *
     * @return value of the attribute letzteAenderung
     */
   Timestamp getLetzteAenderung();

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public WahlModel copy();
}
