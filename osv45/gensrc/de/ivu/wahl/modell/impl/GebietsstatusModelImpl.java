/*
 * GebietsstatusModelImpl
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
import de.ivu.wahl.modell.GebietsstatusModel;

/**
  * Model implementation for the entity Gebietsstatus.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class GebietsstatusModelImpl extends ModelImpl implements GebietsstatusModel, Serializable {
   private static final Category LOGGER = Log4J.configure(GebietsstatusModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(GebietsstatusModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public GebietsstatusModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Gebietsstatus key element of the type {@link String}
     */
   public GebietsstatusModelImpl(String id_Gebietsstatus) {
      setID_Gebietsstatus(id_Gebietsstatus);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(GebietsstatusModelImpl other) {
      setID_Ergebniseingang(other._id_Ergebniseingang);
      setID_Gebiet(other._id_Gebiet);
      setWahlergebnisart(other._wahlergebnisart);
      setKorrekturnummer(other._korrekturnummer);
      setAnzahlErgebnisseKumuliert(other._anzahlErgebnisseKumuliert);
      setVollstaendig(other._vollstaendig);
   }

   /**
     * Type : VARCHAR Name : ID_Gebietsstatus
     */
   protected String _id_Gebietsstatus;

   /**
     * Sets the value of id_Gebietsstatus in the entity Gebietsstatus
     *
     * @param id_Gebietsstatus new value of the attribute id_Gebietsstatus
     */
   protected void setID_Gebietsstatus(String id_Gebietsstatus) {
      if (different(_id_Gebietsstatus, id_Gebietsstatus)) {
         _id_Gebietsstatus = checkLength(id_Gebietsstatus, GebietsstatusModel.ID_GEBIETSSTATUS_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gebietsstatus in the entity Gebietsstatus
     *
     * @return value of the attribute id_Gebietsstatus
     */
   public String getID_Gebietsstatus() {
      return _id_Gebietsstatus;
   }

   /**
     * Type : VARCHAR Name : ID_Ergebniseingang
     */
   protected String _id_Ergebniseingang;

   /**
     * Sets the value of id_Ergebniseingang in the entity Gebietsstatus
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   public void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (different(_id_Ergebniseingang, id_Ergebniseingang)) {
         _id_Ergebniseingang = checkLength(id_Ergebniseingang, GebietsstatusModel.ID_ERGEBNISEINGANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Ergebniseingang in the entity Gebietsstatus
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _id_Ergebniseingang;
   }

   /**
     * Type : VARCHAR Name : ID_Gebiet
     */
   protected String _id_Gebiet;

   /**
     * Sets the value of id_Gebiet in the entity Gebietsstatus
     *
     * @param id_Gebiet new value of the attribute id_Gebiet
     */
   public void setID_Gebiet(String id_Gebiet) {
      if (different(_id_Gebiet, id_Gebiet)) {
         _id_Gebiet = checkLength(id_Gebiet, GebietsstatusModel.ID_GEBIET_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gebiet in the entity Gebietsstatus
     *
     * @return value of the attribute id_Gebiet
     */
   public String getID_Gebiet() {
      return _id_Gebiet;
   }

   /**
     * Type : INT Name : Wahlergebnisart
     */
   protected int _wahlergebnisart;

   /**
     * Sets the value of wahlergebnisart in the entity Gebietsstatus
     *
     * @param wahlergebnisart new value of the attribute wahlergebnisart
     */
   public void setWahlergebnisart(int wahlergebnisart) {
      if (_wahlergebnisart != wahlergebnisart) {
         _wahlergebnisart = wahlergebnisart;
         setModified();
      }
   }

   /**
     * Gets the value of wahlergebnisart in the entity Gebietsstatus
     *
     * @return value of the attribute wahlergebnisart
     */
   public int getWahlergebnisart() {
      return _wahlergebnisart;
   }

   /**
     * Type : INT Name : Korrekturnummer
     */
   protected int _korrekturnummer;

   /**
     * Sets the value of korrekturnummer in the entity Gebietsstatus
     *
     * @param korrekturnummer new value of the attribute korrekturnummer
     */
   public void setKorrekturnummer(int korrekturnummer) {
      if (_korrekturnummer != korrekturnummer) {
         _korrekturnummer = korrekturnummer;
         setModified();
      }
   }

   /**
     * Gets the value of korrekturnummer in the entity Gebietsstatus
     *
     * @return value of the attribute korrekturnummer
     */
   public int getKorrekturnummer() {
      return _korrekturnummer;
   }

   /**
     * Type : INT Name : AnzahlErgebnisseKumuliert
     */
   protected int _anzahlErgebnisseKumuliert;

   /**
     * Sets the value of anzahlErgebnisseKumuliert in the entity Gebietsstatus
     *
     * @param anzahlErgebnisseKumuliert new value of the attribute anzahlErgebnisseKumuliert
     */
   public void setAnzahlErgebnisseKumuliert(int anzahlErgebnisseKumuliert) {
      if (_anzahlErgebnisseKumuliert != anzahlErgebnisseKumuliert) {
         _anzahlErgebnisseKumuliert = anzahlErgebnisseKumuliert;
         setModified();
      }
   }

   /**
     * Gets the value of anzahlErgebnisseKumuliert in the entity Gebietsstatus
     *
     * @return value of the attribute anzahlErgebnisseKumuliert
     */
   public int getAnzahlErgebnisseKumuliert() {
      return _anzahlErgebnisseKumuliert;
   }

   /**
     * Type : SMALLINT Name : Vollstaendig
     */
   protected boolean _vollstaendig;

   /**
     * Sets the value of vollstaendig in the entity Gebietsstatus
     *
     * @param vollstaendig new value of the attribute vollstaendig
     */
   public void setVollstaendig(boolean vollstaendig) {
      if (_vollstaendig != vollstaendig) {
         _vollstaendig = vollstaendig;
         setModified();
      }
   }

   /**
     * Gets the value of vollstaendig in the entity Gebietsstatus
     *
     * @return value of the attribute vollstaendig
     */
   public boolean isVollstaendig() {
      return _vollstaendig;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Gebietsstatus.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof GebietsstatusModelImpl) {
         GebietsstatusModelImpl other = (GebietsstatusModelImpl)object;
         return _id_Gebietsstatus.equals(other._id_Gebietsstatus);
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
         if (getID_Gebietsstatus() != null) {
            string += "id_Gebietsstatus = " + getID_Gebietsstatus(); //$NON-NLS-1$
         }
         if (getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (getID_Gebiet() != null) {
            string +=  ", id_Gebiet = " + getID_Gebiet(); //$NON-NLS-1$
         }
         string +=  ", wahlergebnisart = " + getWahlergebnisart(); //$NON-NLS-1$
         string +=  ", korrekturnummer = " + getKorrekturnummer(); //$NON-NLS-1$
         string +=  ", anzahlErgebnisseKumuliert = " + getAnzahlErgebnisseKumuliert(); //$NON-NLS-1$
         string +=  ", vollstaendig = " + isVollstaendig(); //$NON-NLS-1$
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
   public GebietsstatusModel copy() {
      return (GebietsstatusModel)clone();
   }
}
