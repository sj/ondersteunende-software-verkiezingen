/*
 * SitzberechnungErgebnisModelImpl
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
import de.ivu.wahl.modell.SitzberechnungErgebnisModel;

/**
  * Model implementation for the entity SitzberechnungErgebnis.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class SitzberechnungErgebnisModelImpl extends ModelImpl implements SitzberechnungErgebnisModel, Serializable {
   private static final Category LOGGER = Log4J.configure(SitzberechnungErgebnisModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(SitzberechnungErgebnisModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public SitzberechnungErgebnisModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_SitzberechnungErgebnis key element of the type {@link String}
     */
   public SitzberechnungErgebnisModelImpl(String id_SitzberechnungErgebnis) {
      setID_SitzberechnungErgebnis(id_SitzberechnungErgebnis);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(SitzberechnungErgebnisModelImpl other) {
      setID_Ergebniseingang(other._id_Ergebniseingang);
      setID_Liste(other._id_Liste);
      setID_Gruppe(other._id_Gruppe);
      setID_Listenkombination(other._id_Listenkombination);
      setVerteilung(other._verteilung);
      setSchrittnummer(other._schrittnummer);
      setSchritttyp(other._schritttyp);
      setSitze(other._sitze);
      setZaehler(other._zaehler);
      setNenner(other._nenner);
      setZaehlerVomNenner(other._zaehlerVomNenner);
      setNennerVomNenner(other._nennerVomNenner);
      setZaehlerVomRest(other._zaehlerVomRest);
      setNennerVomRest(other._nennerVomRest);
      setLosentscheid(other._losentscheid);
   }

   /**
     * Type : VARCHAR Name : ID_SitzberechnungErgebnis
     */
   protected String _id_SitzberechnungErgebnis;

   /**
     * Sets the value of id_SitzberechnungErgebnis in the entity SitzberechnungErgebnis
     *
     * @param id_SitzberechnungErgebnis new value of the attribute id_SitzberechnungErgebnis
     */
   protected void setID_SitzberechnungErgebnis(String id_SitzberechnungErgebnis) {
      if (different(_id_SitzberechnungErgebnis, id_SitzberechnungErgebnis)) {
         _id_SitzberechnungErgebnis = checkLength(id_SitzberechnungErgebnis, SitzberechnungErgebnisModel.ID_SITZBERECHNUNGERGEBNIS_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_SitzberechnungErgebnis in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute id_SitzberechnungErgebnis
     */
   public String getID_SitzberechnungErgebnis() {
      return _id_SitzberechnungErgebnis;
   }

   /**
     * Type : VARCHAR Name : ID_Ergebniseingang
     */
   protected String _id_Ergebniseingang;

   /**
     * Sets the value of id_Ergebniseingang in the entity SitzberechnungErgebnis
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   public void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (different(_id_Ergebniseingang, id_Ergebniseingang)) {
         _id_Ergebniseingang = checkLength(id_Ergebniseingang, SitzberechnungErgebnisModel.ID_ERGEBNISEINGANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Ergebniseingang in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _id_Ergebniseingang;
   }

   /**
     * Type : VARCHAR Name : ID_Liste
     */
   protected String _id_Liste;

   /**
     * Sets the value of id_Liste in the entity SitzberechnungErgebnis
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   public void setID_Liste(String id_Liste) {
      if (different(_id_Liste, id_Liste)) {
         _id_Liste = checkLength(id_Liste, SitzberechnungErgebnisModel.ID_LISTE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Liste in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute id_Liste
     */
   public String getID_Liste() {
      return _id_Liste;
   }

   /**
     * Type : VARCHAR Name : ID_Gruppe
     */
   protected String _id_Gruppe;

   /**
     * Sets the value of id_Gruppe in the entity SitzberechnungErgebnis
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   public void setID_Gruppe(String id_Gruppe) {
      if (different(_id_Gruppe, id_Gruppe)) {
         _id_Gruppe = checkLength(id_Gruppe, SitzberechnungErgebnisModel.ID_GRUPPE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gruppe in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute id_Gruppe
     */
   public String getID_Gruppe() {
      return _id_Gruppe;
   }

   /**
     * Type : VARCHAR Name : ID_Listenkombination
     */
   protected String _id_Listenkombination;

   /**
     * Sets the value of id_Listenkombination in the entity SitzberechnungErgebnis
     *
     * @param id_Listenkombination new value of the attribute id_Listenkombination
     */
   public void setID_Listenkombination(String id_Listenkombination) {
      if (different(_id_Listenkombination, id_Listenkombination)) {
         _id_Listenkombination = checkLength(id_Listenkombination, SitzberechnungErgebnisModel.ID_LISTENKOMBINATION_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Listenkombination in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute id_Listenkombination
     */
   public String getID_Listenkombination() {
      return _id_Listenkombination;
   }

   /**
     * Type : INT Name : Verteilung
     */
   protected int _verteilung;

   /**
     * Sets the value of verteilung in the entity SitzberechnungErgebnis
     *
     * @param verteilung new value of the attribute verteilung
     */
   public void setVerteilung(int verteilung) {
      if (_verteilung != verteilung) {
         _verteilung = verteilung;
         setModified();
      }
   }

   /**
     * Gets the value of verteilung in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute verteilung
     */
   public int getVerteilung() {
      return _verteilung;
   }

   /**
     * Type : INT Name : Schrittnummer
     */
   protected int _schrittnummer;

   /**
     * Sets the value of schrittnummer in the entity SitzberechnungErgebnis
     *
     * @param schrittnummer new value of the attribute schrittnummer
     */
   public void setSchrittnummer(int schrittnummer) {
      if (_schrittnummer != schrittnummer) {
         _schrittnummer = schrittnummer;
         setModified();
      }
   }

   /**
     * Gets the value of schrittnummer in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute schrittnummer
     */
   public int getSchrittnummer() {
      return _schrittnummer;
   }

   /**
     * Type : INT Name : Schritttyp
     */
   protected int _schritttyp;

   /**
     * Sets the value of schritttyp in the entity SitzberechnungErgebnis
     *
     * @param schritttyp new value of the attribute schritttyp
     */
   public void setSchritttyp(int schritttyp) {
      if (_schritttyp != schritttyp) {
         _schritttyp = schritttyp;
         setModified();
      }
   }

   /**
     * Gets the value of schritttyp in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute schritttyp
     */
   public int getSchritttyp() {
      return _schritttyp;
   }

   /**
     * Type : INT Name : Sitze
     */
   protected int _sitze;

   /**
     * Sets the value of sitze in the entity SitzberechnungErgebnis
     *
     * @param sitze new value of the attribute sitze
     */
   public void setSitze(int sitze) {
      if (_sitze != sitze) {
         _sitze = sitze;
         setModified();
      }
   }

   /**
     * Gets the value of sitze in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute sitze
     */
   public int getSitze() {
      return _sitze;
   }

   /**
     * Type : INT Name : Zaehler
     */
   protected int _zaehler;

   /**
     * Sets the value of zaehler in the entity SitzberechnungErgebnis
     *
     * @param zaehler new value of the attribute zaehler
     */
   public void setZaehler(int zaehler) {
      if (_zaehler != zaehler) {
         _zaehler = zaehler;
         setModified();
      }
   }

   /**
     * Gets the value of zaehler in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute zaehler
     */
   public int getZaehler() {
      return _zaehler;
   }

   /**
     * Type : INT Name : Nenner
     */
   protected int _nenner;

   /**
     * Sets the value of nenner in the entity SitzberechnungErgebnis
     *
     * @param nenner new value of the attribute nenner
     */
   public void setNenner(int nenner) {
      if (_nenner != nenner) {
         _nenner = nenner;
         setModified();
      }
   }

   /**
     * Gets the value of nenner in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute nenner
     */
   public int getNenner() {
      return _nenner;
   }

   /**
     * Type : INT Name : ZaehlerVomNenner
     */
   protected int _zaehlerVomNenner;

   /**
     * Sets the value of zaehlerVomNenner in the entity SitzberechnungErgebnis
     *
     * @param zaehlerVomNenner new value of the attribute zaehlerVomNenner
     */
   public void setZaehlerVomNenner(int zaehlerVomNenner) {
      if (_zaehlerVomNenner != zaehlerVomNenner) {
         _zaehlerVomNenner = zaehlerVomNenner;
         setModified();
      }
   }

   /**
     * Gets the value of zaehlerVomNenner in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute zaehlerVomNenner
     */
   public int getZaehlerVomNenner() {
      return _zaehlerVomNenner;
   }

   /**
     * Type : INT Name : NennerVomNenner
     */
   protected int _nennerVomNenner;

   /**
     * Sets the value of nennerVomNenner in the entity SitzberechnungErgebnis
     *
     * @param nennerVomNenner new value of the attribute nennerVomNenner
     */
   public void setNennerVomNenner(int nennerVomNenner) {
      if (_nennerVomNenner != nennerVomNenner) {
         _nennerVomNenner = nennerVomNenner;
         setModified();
      }
   }

   /**
     * Gets the value of nennerVomNenner in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute nennerVomNenner
     */
   public int getNennerVomNenner() {
      return _nennerVomNenner;
   }

   /**
     * Type : INT Name : ZaehlerVomRest
     */
   protected int _zaehlerVomRest;

   /**
     * Sets the value of zaehlerVomRest in the entity SitzberechnungErgebnis
     *
     * @param zaehlerVomRest new value of the attribute zaehlerVomRest
     */
   public void setZaehlerVomRest(int zaehlerVomRest) {
      if (_zaehlerVomRest != zaehlerVomRest) {
         _zaehlerVomRest = zaehlerVomRest;
         setModified();
      }
   }

   /**
     * Gets the value of zaehlerVomRest in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute zaehlerVomRest
     */
   public int getZaehlerVomRest() {
      return _zaehlerVomRest;
   }

   /**
     * Type : INT Name : NennerVomRest
     */
   protected int _nennerVomRest;

   /**
     * Sets the value of nennerVomRest in the entity SitzberechnungErgebnis
     *
     * @param nennerVomRest new value of the attribute nennerVomRest
     */
   public void setNennerVomRest(int nennerVomRest) {
      if (_nennerVomRest != nennerVomRest) {
         _nennerVomRest = nennerVomRest;
         setModified();
      }
   }

   /**
     * Gets the value of nennerVomRest in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute nennerVomRest
     */
   public int getNennerVomRest() {
      return _nennerVomRest;
   }

   /**
     * Type : SMALLINT Name : Losentscheid
     */
   protected boolean _losentscheid;

   /**
     * Sets the value of losentscheid in the entity SitzberechnungErgebnis
     *
     * @param losentscheid new value of the attribute losentscheid
     */
   public void setLosentscheid(boolean losentscheid) {
      if (_losentscheid != losentscheid) {
         _losentscheid = losentscheid;
         setModified();
      }
   }

   /**
     * Gets the value of losentscheid in the entity SitzberechnungErgebnis
     *
     * @return value of the attribute losentscheid
     */
   public boolean isLosentscheid() {
      return _losentscheid;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_SitzberechnungErgebnis.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof SitzberechnungErgebnisModelImpl) {
         SitzberechnungErgebnisModelImpl other = (SitzberechnungErgebnisModelImpl)object;
         return _id_SitzberechnungErgebnis.equals(other._id_SitzberechnungErgebnis);
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
         if (getID_SitzberechnungErgebnis() != null) {
            string += "id_SitzberechnungErgebnis = " + getID_SitzberechnungErgebnis(); //$NON-NLS-1$
         }
         if (getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (getID_Liste() != null) {
            string +=  ", id_Liste = " + getID_Liste(); //$NON-NLS-1$
         }
         if (getID_Gruppe() != null) {
            string +=  ", id_Gruppe = " + getID_Gruppe(); //$NON-NLS-1$
         }
         if (getID_Listenkombination() != null) {
            string +=  ", id_Listenkombination = " + getID_Listenkombination(); //$NON-NLS-1$
         }
         string +=  ", verteilung = " + getVerteilung(); //$NON-NLS-1$
         string +=  ", schrittnummer = " + getSchrittnummer(); //$NON-NLS-1$
         string +=  ", schritttyp = " + getSchritttyp(); //$NON-NLS-1$
         string +=  ", sitze = " + getSitze(); //$NON-NLS-1$
         string +=  ", zaehler = " + getZaehler(); //$NON-NLS-1$
         string +=  ", nenner = " + getNenner(); //$NON-NLS-1$
         string +=  ", zaehlerVomNenner = " + getZaehlerVomNenner(); //$NON-NLS-1$
         string +=  ", nennerVomNenner = " + getNennerVomNenner(); //$NON-NLS-1$
         string +=  ", zaehlerVomRest = " + getZaehlerVomRest(); //$NON-NLS-1$
         string +=  ", nennerVomRest = " + getNennerVomRest(); //$NON-NLS-1$
         string +=  ", losentscheid = " + isLosentscheid(); //$NON-NLS-1$
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
   public SitzberechnungErgebnisModel copy() {
      return (SitzberechnungErgebnisModel)clone();
   }
}
