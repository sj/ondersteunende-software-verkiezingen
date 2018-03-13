/*
 * WahlModelImpl
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.impl;

import java.sql.Timestamp;
import java.io.Serializable;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.ejb.bmp.ModelImpl;
import de.ivu.wahl.modell.WahlModel;

/**
  * Model implementation for the entity Wahl.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class WahlModelImpl extends ModelImpl implements WahlModel, Serializable {
   private static final Category LOGGER = Log4J.configure(WahlModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(WahlModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public WahlModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Wahl key element of the type {@link String}
     */
   public WahlModelImpl(String id_Wahl) {
      setID_Wahl(id_Wahl);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(WahlModelImpl other) {
      setID_Wurzelgebiet(other._id_Wurzelgebiet);
      setID_Wahlgebiet(other._id_Wahlgebiet);
      setWahlart(other._wahlart);
      setWahlebene(other._wahlebene);
      setWahlkategorie(other._wahlkategorie);
      setName(other._name);
      setElectionDomain(other._electionDomain);
      setElectionDomainId(other._electionDomainId);
      setTermin(other._termin);
      setVorrangschwelle(other._vorrangschwelle);
      setAnzahlSitze(other._anzahlSitze);
      setGebietsartAuswertungseinheit(other._gebietsartAuswertungseinheit);
      setGebietsartErfassungseinheit(other._gebietsartErfassungseinheit);
      setAktuelleWahlergebnisart(other._aktuelleWahlergebnisart);
      setDatumNominierung(other._datumNominierung);
      setStandMetadaten(other._standMetadaten);
      setGeschlossenMetadaten(other._geschlossenMetadaten);
      setStatus(other._status);
      setFreigegeben(other._freigegeben);
      setLetzteAenderung(other._letzteAenderung);
   }

   /**
     * Type : VARCHAR Name : ID_Wahl
     */
   protected String _id_Wahl;

   /**
     * Sets the value of id_Wahl in the entity Wahl
     *
     * @param id_Wahl new value of the attribute id_Wahl
     */
   protected void setID_Wahl(String id_Wahl) {
      if (different(_id_Wahl, id_Wahl)) {
         _id_Wahl = checkLength(id_Wahl, WahlModel.ID_WAHL_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Wahl in the entity Wahl
     *
     * @return value of the attribute id_Wahl
     */
   public String getID_Wahl() {
      return _id_Wahl;
   }

   /**
     * Type : VARCHAR Name : ID_Wurzelgebiet
     */
   protected String _id_Wurzelgebiet;

   /**
     * Sets the value of id_Wurzelgebiet in the entity Wahl
     *
     * @param id_Wurzelgebiet new value of the attribute id_Wurzelgebiet
     */
   public void setID_Wurzelgebiet(String id_Wurzelgebiet) {
      if (different(_id_Wurzelgebiet, id_Wurzelgebiet)) {
         _id_Wurzelgebiet = checkLength(id_Wurzelgebiet, WahlModel.ID_WURZELGEBIET_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Wurzelgebiet in the entity Wahl
     *
     * @return value of the attribute id_Wurzelgebiet
     */
   public String getID_Wurzelgebiet() {
      return _id_Wurzelgebiet;
   }

   /**
     * Type : VARCHAR Name : ID_Wahlgebiet
     */
   protected String _id_Wahlgebiet;

   /**
     * Sets the value of id_Wahlgebiet in the entity Wahl
     *
     * @param id_Wahlgebiet new value of the attribute id_Wahlgebiet
     */
   public void setID_Wahlgebiet(String id_Wahlgebiet) {
      if (different(_id_Wahlgebiet, id_Wahlgebiet)) {
         _id_Wahlgebiet = checkLength(id_Wahlgebiet, WahlModel.ID_WAHLGEBIET_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Wahlgebiet in the entity Wahl
     *
     * @return value of the attribute id_Wahlgebiet
     */
   public String getID_Wahlgebiet() {
      return _id_Wahlgebiet;
   }

   /**
     * Type : INT Name : Wahlart
     */
   protected int _wahlart;

   /**
     * Sets the value of wahlart in the entity Wahl
     *
     * @param wahlart new value of the attribute wahlart
     */
   public void setWahlart(int wahlart) {
      if (_wahlart != wahlart) {
         _wahlart = wahlart;
         setModified();
      }
   }

   /**
     * Gets the value of wahlart in the entity Wahl
     *
     * @return value of the attribute wahlart
     */
   public int getWahlart() {
      return _wahlart;
   }

   /**
     * Type : INT Name : Wahlebene
     */
   protected int _wahlebene;

   /**
     * Sets the value of wahlebene in the entity Wahl
     *
     * @param wahlebene new value of the attribute wahlebene
     */
   public void setWahlebene(int wahlebene) {
      if (_wahlebene != wahlebene) {
         _wahlebene = wahlebene;
         setModified();
      }
   }

   /**
     * Gets the value of wahlebene in the entity Wahl
     *
     * @return value of the attribute wahlebene
     */
   public int getWahlebene() {
      return _wahlebene;
   }

   /**
     * Type : VARCHAR Name : Wahlkategorie
     */
   protected String _wahlkategorie;

   /**
     * Sets the value of wahlkategorie in the entity Wahl
     *
     * @param wahlkategorie new value of the attribute wahlkategorie
     */
   public void setWahlkategorie(String wahlkategorie) {
      if (different(_wahlkategorie, wahlkategorie)) {
         _wahlkategorie = checkLength(wahlkategorie, WahlModel.WAHLKATEGORIE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of wahlkategorie in the entity Wahl
     *
     * @return value of the attribute wahlkategorie
     */
   public String getWahlkategorie() {
      return _wahlkategorie;
   }

   /**
     * Type : VARCHAR Name : Name
     */
   protected String _name;

   /**
     * Sets the value of name in the entity Wahl
     *
     * @param name new value of the attribute name
     */
   public void setName(String name) {
      if (different(_name, name)) {
         _name = checkLength(name, WahlModel.NAME_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of name in the entity Wahl
     *
     * @return value of the attribute name
     */
   public String getName() {
      return _name;
   }

   /**
     * Type : VARCHAR Name : ElectionDomain
     */
   protected String _electionDomain;

   /**
     * Sets the value of electionDomain in the entity Wahl
     *
     * @param electionDomain new value of the attribute electionDomain
     */
   public void setElectionDomain(String electionDomain) {
      if (different(_electionDomain, electionDomain)) {
         _electionDomain = checkLength(electionDomain, WahlModel.ELECTIONDOMAIN_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of electionDomain in the entity Wahl
     *
     * @return value of the attribute electionDomain
     */
   public String getElectionDomain() {
      return _electionDomain;
   }

   /**
     * Type : VARCHAR Name : ElectionDomainId
     */
   protected String _electionDomainId;

   /**
     * Sets the value of electionDomainId in the entity Wahl
     *
     * @param electionDomainId new value of the attribute electionDomainId
     */
   public void setElectionDomainId(String electionDomainId) {
      if (different(_electionDomainId, electionDomainId)) {
         _electionDomainId = checkLength(electionDomainId, WahlModel.ELECTIONDOMAINID_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of electionDomainId in the entity Wahl
     *
     * @return value of the attribute electionDomainId
     */
   public String getElectionDomainId() {
      return _electionDomainId;
   }

   /**
     * Type : TIMESTAMP Name : Termin
     */
   protected Timestamp _termin;

   /**
     * Sets the value of termin in the entity Wahl
     *
     * @param termin new value of the attribute termin
     */
   public void setTermin(Timestamp termin) {
      if (different(_termin, termin)) {
         _termin = termin;
         setModified();
      }
   }

   /**
     * Gets the value of termin in the entity Wahl
     *
     * @return value of the attribute termin
     */
   public Timestamp getTermin() {
      return _termin;
   }

   /**
     * Type : INT Name : Vorrangschwelle
     */
   protected int _vorrangschwelle;

   /**
     * Sets the value of vorrangschwelle in the entity Wahl
     *
     * @param vorrangschwelle new value of the attribute vorrangschwelle
     */
   public void setVorrangschwelle(int vorrangschwelle) {
      if (_vorrangschwelle != vorrangschwelle) {
         _vorrangschwelle = vorrangschwelle;
         setModified();
      }
   }

   /**
     * Gets the value of vorrangschwelle in the entity Wahl
     *
     * @return value of the attribute vorrangschwelle
     */
   public int getVorrangschwelle() {
      return _vorrangschwelle;
   }

   /**
     * Type : INT Name : AnzahlSitze
     */
   protected int _anzahlSitze;

   /**
     * Sets the value of anzahlSitze in the entity Wahl
     *
     * @param anzahlSitze new value of the attribute anzahlSitze
     */
   public void setAnzahlSitze(int anzahlSitze) {
      if (_anzahlSitze != anzahlSitze) {
         _anzahlSitze = anzahlSitze;
         setModified();
      }
   }

   /**
     * Gets the value of anzahlSitze in the entity Wahl
     *
     * @return value of the attribute anzahlSitze
     */
   public int getAnzahlSitze() {
      return _anzahlSitze;
   }

   /**
     * Type : INT Name : GebietsartAuswertungseinheit
     */
   protected int _gebietsartAuswertungseinheit;

   /**
     * Sets the value of gebietsartAuswertungseinheit in the entity Wahl
     *
     * @param gebietsartAuswertungseinheit new value of the attribute gebietsartAuswertungseinheit
     */
   public void setGebietsartAuswertungseinheit(int gebietsartAuswertungseinheit) {
      if (_gebietsartAuswertungseinheit != gebietsartAuswertungseinheit) {
         _gebietsartAuswertungseinheit = gebietsartAuswertungseinheit;
         setModified();
      }
   }

   /**
     * Gets the value of gebietsartAuswertungseinheit in the entity Wahl
     *
     * @return value of the attribute gebietsartAuswertungseinheit
     */
   public int getGebietsartAuswertungseinheit() {
      return _gebietsartAuswertungseinheit;
   }

   /**
     * Type : INT Name : GebietsartErfassungseinheit
     */
   protected int _gebietsartErfassungseinheit;

   /**
     * Sets the value of gebietsartErfassungseinheit in the entity Wahl
     *
     * @param gebietsartErfassungseinheit new value of the attribute gebietsartErfassungseinheit
     */
   public void setGebietsartErfassungseinheit(int gebietsartErfassungseinheit) {
      if (_gebietsartErfassungseinheit != gebietsartErfassungseinheit) {
         _gebietsartErfassungseinheit = gebietsartErfassungseinheit;
         setModified();
      }
   }

   /**
     * Gets the value of gebietsartErfassungseinheit in the entity Wahl
     *
     * @return value of the attribute gebietsartErfassungseinheit
     */
   public int getGebietsartErfassungseinheit() {
      return _gebietsartErfassungseinheit;
   }

   /**
     * Type : INT Name : AktuelleWahlergebnisart
     */
   protected int _aktuelleWahlergebnisart;

   /**
     * Sets the value of aktuelleWahlergebnisart in the entity Wahl
     *
     * @param aktuelleWahlergebnisart new value of the attribute aktuelleWahlergebnisart
     */
   public void setAktuelleWahlergebnisart(int aktuelleWahlergebnisart) {
      if (_aktuelleWahlergebnisart != aktuelleWahlergebnisart) {
         _aktuelleWahlergebnisart = aktuelleWahlergebnisart;
         setModified();
      }
   }

   /**
     * Gets the value of aktuelleWahlergebnisart in the entity Wahl
     *
     * @return value of the attribute aktuelleWahlergebnisart
     */
   public int getAktuelleWahlergebnisart() {
      return _aktuelleWahlergebnisart;
   }

   /**
     * Type : TIMESTAMP Name : DatumNominierung
     */
   protected Timestamp _datumNominierung;

   /**
     * Sets the value of datumNominierung in the entity Wahl
     *
     * @param datumNominierung new value of the attribute datumNominierung
     */
   public void setDatumNominierung(Timestamp datumNominierung) {
      if (different(_datumNominierung, datumNominierung)) {
         _datumNominierung = datumNominierung;
         setModified();
      }
   }

   /**
     * Gets the value of datumNominierung in the entity Wahl
     *
     * @return value of the attribute datumNominierung
     */
   public Timestamp getDatumNominierung() {
      return _datumNominierung;
   }

   /**
     * Type : TIMESTAMP Name : StandMetadaten
     */
   protected Timestamp _standMetadaten;

   /**
     * Sets the value of standMetadaten in the entity Wahl
     *
     * @param standMetadaten new value of the attribute standMetadaten
     */
   public void setStandMetadaten(Timestamp standMetadaten) {
      if (different(_standMetadaten, standMetadaten)) {
         _standMetadaten = standMetadaten;
         setModified();
      }
   }

   /**
     * Gets the value of standMetadaten in the entity Wahl
     *
     * @return value of the attribute standMetadaten
     */
   public Timestamp getStandMetadaten() {
      return _standMetadaten;
   }

   /**
     * Type : TIMESTAMP Name : GeschlossenMetadaten
     */
   protected Timestamp _geschlossenMetadaten;

   /**
     * Sets the value of geschlossenMetadaten in the entity Wahl
     *
     * @param geschlossenMetadaten new value of the attribute geschlossenMetadaten
     */
   public void setGeschlossenMetadaten(Timestamp geschlossenMetadaten) {
      if (different(_geschlossenMetadaten, geschlossenMetadaten)) {
         _geschlossenMetadaten = geschlossenMetadaten;
         setModified();
      }
   }

   /**
     * Gets the value of geschlossenMetadaten in the entity Wahl
     *
     * @return value of the attribute geschlossenMetadaten
     */
   public Timestamp getGeschlossenMetadaten() {
      return _geschlossenMetadaten;
   }

   /**
     * Type : INT Name : Status
     */
   protected int _status;

   /**
     * Sets the value of status in the entity Wahl
     *
     * @param status new value of the attribute status
     */
   public void setStatus(int status) {
      if (_status != status) {
         _status = status;
         setModified();
      }
   }

   /**
     * Gets the value of status in the entity Wahl
     *
     * @return value of the attribute status
     */
   public int getStatus() {
      return _status;
   }

   /**
     * Type : TIMESTAMP Name : Freigegeben
     */
   protected Timestamp _freigegeben;

   /**
     * Sets the value of freigegeben in the entity Wahl
     *
     * @param freigegeben new value of the attribute freigegeben
     */
   public void setFreigegeben(Timestamp freigegeben) {
      if (different(_freigegeben, freigegeben)) {
         _freigegeben = freigegeben;
         setModified();
      }
   }

   /**
     * Gets the value of freigegeben in the entity Wahl
     *
     * @return value of the attribute freigegeben
     */
   public Timestamp getFreigegeben() {
      return _freigegeben;
   }

   /**
     * Type : TIMESTAMP Name : LetzteAenderung
     */
   protected Timestamp _letzteAenderung;

   /**
     * Sets the value of letzteAenderung in the entity Wahl
     *
     * @param letzteAenderung new value of the attribute letzteAenderung
     */
   public void setLetzteAenderung(Timestamp letzteAenderung) {
      if (different(_letzteAenderung, letzteAenderung)) {
         _letzteAenderung = letzteAenderung;
         setModified();
      }
   }

   /**
     * Gets the value of letzteAenderung in the entity Wahl
     *
     * @return value of the attribute letzteAenderung
     */
   public Timestamp getLetzteAenderung() {
      return _letzteAenderung;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Wahl.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof WahlModelImpl) {
         WahlModelImpl other = (WahlModelImpl)object;
         return _id_Wahl.equals(other._id_Wahl);
      } else {
         return false;
      }
   }

   /**
     * Overwrites the toString method in Object
     *
     * @return string representation of the current state as listing of the attributes and their values
     */
   @Override
   public String toString() {
      try {
         String string = "[" + getClass().getName()+ ": "; //$NON-NLS-1$ //$NON-NLS-2$
         if (getID_Wahl() != null) {
            string += "id_Wahl = " + getID_Wahl(); //$NON-NLS-1$
         }
         if (getID_Wurzelgebiet() != null) {
            string +=  ", id_Wurzelgebiet = " + getID_Wurzelgebiet(); //$NON-NLS-1$
         }
         if (getID_Wahlgebiet() != null) {
            string +=  ", id_Wahlgebiet = " + getID_Wahlgebiet(); //$NON-NLS-1$
         }
         string +=  ", wahlart = " + getWahlart(); //$NON-NLS-1$
         string +=  ", wahlebene = " + getWahlebene(); //$NON-NLS-1$
         if (getWahlkategorie() != null) {
            string +=  ", wahlkategorie = " + getWahlkategorie(); //$NON-NLS-1$
         }
         if (getName() != null) {
            string +=  ", name = " + getName(); //$NON-NLS-1$
         }
         if (getElectionDomain() != null) {
            string +=  ", electionDomain = " + getElectionDomain(); //$NON-NLS-1$
         }
         if (getElectionDomainId() != null) {
            string +=  ", electionDomainId = " + getElectionDomainId(); //$NON-NLS-1$
         }
         if (getTermin() != null) {
            string +=  ", termin = " + getTermin(); //$NON-NLS-1$
         }
         string +=  ", vorrangschwelle = " + getVorrangschwelle(); //$NON-NLS-1$
         string +=  ", anzahlSitze = " + getAnzahlSitze(); //$NON-NLS-1$
         string +=  ", gebietsartAuswertungseinheit = " + getGebietsartAuswertungseinheit(); //$NON-NLS-1$
         string +=  ", gebietsartErfassungseinheit = " + getGebietsartErfassungseinheit(); //$NON-NLS-1$
         string +=  ", aktuelleWahlergebnisart = " + getAktuelleWahlergebnisart(); //$NON-NLS-1$
         if (getDatumNominierung() != null) {
            string +=  ", datumNominierung = " + getDatumNominierung(); //$NON-NLS-1$
         }
         if (getStandMetadaten() != null) {
            string +=  ", standMetadaten = " + getStandMetadaten(); //$NON-NLS-1$
         }
         if (getGeschlossenMetadaten() != null) {
            string +=  ", geschlossenMetadaten = " + getGeschlossenMetadaten(); //$NON-NLS-1$
         }
         string +=  ", status = " + getStatus(); //$NON-NLS-1$
         if (getFreigegeben() != null) {
            string +=  ", freigegeben = " + getFreigegeben(); //$NON-NLS-1$
         }
         if (getLetzteAenderung() != null) {
            string +=  ", letzteAenderung = " + getLetzteAenderung(); //$NON-NLS-1$
         }
         return string  + "]" ; //$NON-NLS-1$
      } catch (Exception e) {
         LOGGER.error(e, e);
         return ""; //$NON-NLS-1$
      }
   }


   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public WahlModel copy() {
      return (WahlModel)clone();
   }
}
