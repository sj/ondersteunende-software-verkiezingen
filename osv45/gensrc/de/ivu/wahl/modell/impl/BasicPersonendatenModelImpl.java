/*
 * BasicPersonendatenModelImpl
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.impl;

import java.io.Serializable;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.ejb.bmp.ModelImpl;
import de.ivu.wahl.modell.PersonendatenModel;

/**
  * Model implementation for the entity Personendaten.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public abstract class BasicPersonendatenModelImpl extends ModelImpl implements PersonendatenModel, Serializable {
   private static final Category LOGGER = Log4J.configure(BasicPersonendatenModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicPersonendatenModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public BasicPersonendatenModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Personendaten key element of the type {@link String}
     */
   public BasicPersonendatenModelImpl(String id_Personendaten) {
      setID_Personendaten(id_Personendaten);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(BasicPersonendatenModelImpl other) {
      setID_PersonendatenAgent(other._id_PersonendatenAgent);
      setNachname(other._nachname);
      setVorname(other._vorname);
      setPraefix(other._praefix);
      setInitialen(other._initialen);
      setTitel(other._titel);
      setGeschlecht(other._geschlecht);
      setGeneration(other._generation);
      setLand(other._land);
      setWohnort(other._wohnort);
      setKontakt_Land(other._kontakt_Land);
      setKontakt_Wohnort(other._kontakt_Wohnort);
      setKontakt_PLZ(other._kontakt_PLZ);
      setKontakt_Strasse(other._kontakt_Strasse);
      setBenennbar(other._benennbar);
   }

   /**
     * Type : VARCHAR Name : ID_Personendaten
     */
   protected String _id_Personendaten;

   /**
     * Sets the value of id_Personendaten in the entity Personendaten
     *
     * @param id_Personendaten new value of the attribute id_Personendaten
     */
   protected void setID_Personendaten(String id_Personendaten) {
      if (different(_id_Personendaten, id_Personendaten)) {
         _id_Personendaten = checkLength(id_Personendaten, PersonendatenModel.ID_PERSONENDATEN_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Personendaten in the entity Personendaten
     *
     * @return value of the attribute id_Personendaten
     */
   public String getID_Personendaten() {
      return _id_Personendaten;
   }

   /**
     * Type : VARCHAR Name : ID_PersonendatenAgent
     */
   protected String _id_PersonendatenAgent;

   /**
     * Sets the value of id_PersonendatenAgent in the entity Personendaten
     *
     * @param id_PersonendatenAgent new value of the attribute id_PersonendatenAgent
     */
   public void setID_PersonendatenAgent(String id_PersonendatenAgent) {
      if (different(_id_PersonendatenAgent, id_PersonendatenAgent)) {
         _id_PersonendatenAgent = checkLength(id_PersonendatenAgent, PersonendatenModel.ID_PERSONENDATENAGENT_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_PersonendatenAgent in the entity Personendaten
     *
     * @return value of the attribute id_PersonendatenAgent
     */
   public String getID_PersonendatenAgent() {
      return _id_PersonendatenAgent;
   }

   /**
     * Type : VARCHAR Name : Nachname
     */
   protected String _nachname;

   /**
     * Sets the value of nachname in the entity Personendaten
     *
     * @param nachname new value of the attribute nachname
     */
   public void setNachname(String nachname) {
      if (different(_nachname, nachname)) {
         _nachname = checkLength(nachname, PersonendatenModel.NACHNAME_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of nachname in the entity Personendaten
     *
     * @return value of the attribute nachname
     */
   public String getNachname() {
      return _nachname;
   }

   /**
     * Type : VARCHAR Name : Vorname
     */
   protected String _vorname;

   /**
     * Sets the value of vorname in the entity Personendaten
     *
     * @param vorname new value of the attribute vorname
     */
   public void setVorname(String vorname) {
      if (different(_vorname, vorname)) {
         _vorname = checkLength(vorname, PersonendatenModel.VORNAME_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of vorname in the entity Personendaten
     *
     * @return value of the attribute vorname
     */
   public String getVorname() {
      return _vorname;
   }

   /**
     * Type : VARCHAR Name : Praefix
     */
   protected String _praefix;

   /**
     * Sets the value of praefix in the entity Personendaten
     *
     * @param praefix new value of the attribute praefix
     */
   public void setPraefix(String praefix) {
      if (different(_praefix, praefix)) {
         _praefix = checkLength(praefix, PersonendatenModel.PRAEFIX_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of praefix in the entity Personendaten
     *
     * @return value of the attribute praefix
     */
   public String getPraefix() {
      return _praefix;
   }

   /**
     * Type : VARCHAR Name : Initialen
     */
   protected String _initialen;

   /**
     * Sets the value of initialen in the entity Personendaten
     *
     * @param initialen new value of the attribute initialen
     */
   public void setInitialen(String initialen) {
      if (different(_initialen, initialen)) {
         _initialen = checkLength(initialen, PersonendatenModel.INITIALEN_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of initialen in the entity Personendaten
     *
     * @return value of the attribute initialen
     */
   public String getInitialen() {
      return _initialen;
   }

   /**
     * Type : VARCHAR Name : Titel
     */
   protected String _titel;

   /**
     * Sets the value of titel in the entity Personendaten
     *
     * @param titel new value of the attribute titel
     */
   public void setTitel(String titel) {
      if (different(_titel, titel)) {
         _titel = checkLength(titel, PersonendatenModel.TITEL_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of titel in the entity Personendaten
     *
     * @return value of the attribute titel
     */
   public String getTitel() {
      return _titel;
   }

   /**
     * Type : INT Name : Geschlecht
     */
   protected int _geschlecht;

   /**
     * Sets the value of geschlecht in the entity Personendaten
     *
     * @param geschlecht new value of the attribute geschlecht
     */
   public void setGeschlecht(int geschlecht) {
      if (_geschlecht != geschlecht) {
         _geschlecht = geschlecht;
         setModified();
      }
   }

   /**
     * Gets the value of geschlecht in the entity Personendaten
     *
     * @return value of the attribute geschlecht
     */
   public int getGeschlecht() {
      return _geschlecht;
   }

   /**
     * Type : VARCHAR Name : Generation
     */
   protected String _generation;

   /**
     * Sets the value of generation in the entity Personendaten
     *
     * @param generation new value of the attribute generation
     */
   public void setGeneration(String generation) {
      if (different(_generation, generation)) {
         _generation = checkLength(generation, PersonendatenModel.GENERATION_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of generation in the entity Personendaten
     *
     * @return value of the attribute generation
     */
   public String getGeneration() {
      return _generation;
   }

   /**
     * Type : VARCHAR Name : Land
     */
   protected String _land;

   /**
     * Sets the value of land in the entity Personendaten
     *
     * @param land new value of the attribute land
     */
   public void setLand(String land) {
      if (different(_land, land)) {
         _land = checkLength(land, PersonendatenModel.LAND_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of land in the entity Personendaten
     *
     * @return value of the attribute land
     */
   public String getLand() {
      return _land;
   }

   /**
     * Type : VARCHAR Name : Wohnort
     */
   protected String _wohnort;

   /**
     * Sets the value of wohnort in the entity Personendaten
     *
     * @param wohnort new value of the attribute wohnort
     */
   public void setWohnort(String wohnort) {
      if (different(_wohnort, wohnort)) {
         _wohnort = checkLength(wohnort, PersonendatenModel.WOHNORT_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of wohnort in the entity Personendaten
     *
     * @return value of the attribute wohnort
     */
   public String getWohnort() {
      return _wohnort;
   }

   /**
     * Type : VARCHAR Name : Kontakt_Land
     */
   protected String _kontakt_Land;

   /**
     * Sets the value of kontakt_Land in the entity Personendaten
     *
     * @param kontakt_Land new value of the attribute kontakt_Land
     */
   public void setKontakt_Land(String kontakt_Land) {
      if (different(_kontakt_Land, kontakt_Land)) {
         _kontakt_Land = checkLength(kontakt_Land, PersonendatenModel.KONTAKT_LAND_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of kontakt_Land in the entity Personendaten
     *
     * @return value of the attribute kontakt_Land
     */
   public String getKontakt_Land() {
      return _kontakt_Land;
   }

   /**
     * Type : VARCHAR Name : Kontakt_Wohnort
     */
   protected String _kontakt_Wohnort;

   /**
     * Sets the value of kontakt_Wohnort in the entity Personendaten
     *
     * @param kontakt_Wohnort new value of the attribute kontakt_Wohnort
     */
   public void setKontakt_Wohnort(String kontakt_Wohnort) {
      if (different(_kontakt_Wohnort, kontakt_Wohnort)) {
         _kontakt_Wohnort = checkLength(kontakt_Wohnort, PersonendatenModel.KONTAKT_WOHNORT_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of kontakt_Wohnort in the entity Personendaten
     *
     * @return value of the attribute kontakt_Wohnort
     */
   public String getKontakt_Wohnort() {
      return _kontakt_Wohnort;
   }

   /**
     * Type : VARCHAR Name : Kontakt_PLZ
     */
   protected String _kontakt_PLZ;

   /**
     * Sets the value of kontakt_PLZ in the entity Personendaten
     *
     * @param kontakt_PLZ new value of the attribute kontakt_PLZ
     */
   public void setKontakt_PLZ(String kontakt_PLZ) {
      if (different(_kontakt_PLZ, kontakt_PLZ)) {
         _kontakt_PLZ = checkLength(kontakt_PLZ, PersonendatenModel.KONTAKT_PLZ_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of kontakt_PLZ in the entity Personendaten
     *
     * @return value of the attribute kontakt_PLZ
     */
   public String getKontakt_PLZ() {
      return _kontakt_PLZ;
   }

   /**
     * Type : VARCHAR Name : Kontakt_Strasse
     */
   protected String _kontakt_Strasse;

   /**
     * Sets the value of kontakt_Strasse in the entity Personendaten
     *
     * @param kontakt_Strasse new value of the attribute kontakt_Strasse
     */
   public void setKontakt_Strasse(String kontakt_Strasse) {
      if (different(_kontakt_Strasse, kontakt_Strasse)) {
         _kontakt_Strasse = checkLength(kontakt_Strasse, PersonendatenModel.KONTAKT_STRASSE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of kontakt_Strasse in the entity Personendaten
     *
     * @return value of the attribute kontakt_Strasse
     */
   public String getKontakt_Strasse() {
      return _kontakt_Strasse;
   }

   /**
     * Type : SMALLINT Name : Benennbar
     */
   protected boolean _benennbar;

   /**
     * Sets the value of benennbar in the entity Personendaten
     *
     * @param benennbar new value of the attribute benennbar
     */
   public void setBenennbar(boolean benennbar) {
      if (_benennbar != benennbar) {
         _benennbar = benennbar;
         setModified();
      }
   }

   /**
     * Gets the value of benennbar in the entity Personendaten
     *
     * @return value of the attribute benennbar
     */
   public boolean isBenennbar() {
      return _benennbar;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Personendaten.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof BasicPersonendatenModelImpl) {
         BasicPersonendatenModelImpl other = (BasicPersonendatenModelImpl)object;
         return _id_Personendaten.equals(other._id_Personendaten);
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
         if (getID_Personendaten() != null) {
            string += "id_Personendaten = " + getID_Personendaten(); //$NON-NLS-1$
         }
         if (getID_PersonendatenAgent() != null) {
            string +=  ", id_PersonendatenAgent = " + getID_PersonendatenAgent(); //$NON-NLS-1$
         }
         if (getNachname() != null) {
            string +=  ", nachname = " + getNachname(); //$NON-NLS-1$
         }
         if (getVorname() != null) {
            string +=  ", vorname = " + getVorname(); //$NON-NLS-1$
         }
         if (getPraefix() != null) {
            string +=  ", praefix = " + getPraefix(); //$NON-NLS-1$
         }
         if (getInitialen() != null) {
            string +=  ", initialen = " + getInitialen(); //$NON-NLS-1$
         }
         if (getTitel() != null) {
            string +=  ", titel = " + getTitel(); //$NON-NLS-1$
         }
         string +=  ", geschlecht = " + getGeschlecht(); //$NON-NLS-1$
         if (getGeneration() != null) {
            string +=  ", generation = " + getGeneration(); //$NON-NLS-1$
         }
         if (getLand() != null) {
            string +=  ", land = " + getLand(); //$NON-NLS-1$
         }
         if (getWohnort() != null) {
            string +=  ", wohnort = " + getWohnort(); //$NON-NLS-1$
         }
         if (getKontakt_Land() != null) {
            string +=  ", kontakt_Land = " + getKontakt_Land(); //$NON-NLS-1$
         }
         if (getKontakt_Wohnort() != null) {
            string +=  ", kontakt_Wohnort = " + getKontakt_Wohnort(); //$NON-NLS-1$
         }
         if (getKontakt_PLZ() != null) {
            string +=  ", kontakt_PLZ = " + getKontakt_PLZ(); //$NON-NLS-1$
         }
         if (getKontakt_Strasse() != null) {
            string +=  ", kontakt_Strasse = " + getKontakt_Strasse(); //$NON-NLS-1$
         }
         string +=  ", benennbar = " + isBenennbar(); //$NON-NLS-1$
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
   public PersonendatenModel copy() {
      return (PersonendatenModel)clone();
   }
}
