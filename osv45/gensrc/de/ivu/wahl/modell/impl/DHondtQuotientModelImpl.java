/*
 * DHondtQuotientModelImpl
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
import de.ivu.wahl.modell.DHondtQuotientModel;

/**
  * Model implementation for the entity DHondtQuotient.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class DHondtQuotientModelImpl extends ModelImpl implements DHondtQuotientModel, Serializable {
   private static final Category LOGGER = Log4J.configure(DHondtQuotientModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(DHondtQuotientModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public DHondtQuotientModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param iD_DHondtQuotient key element of the type {@link String}
     */
   public DHondtQuotientModelImpl(String iD_DHondtQuotient) {
      setID_DHondtQuotient(iD_DHondtQuotient);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(DHondtQuotientModelImpl other) {
      setID_Restsitzverteilung(other._id_Restsitzverteilung);
      setLauf(other._lauf);
      setZaehler(other._zaehler);
      setNenner(other._nenner);
      setSitzAusRestanteil(other._sitzAusRestanteil);
   }

   /**
     * Type : VARCHAR Name : ID_DHondtQuotient
     */
   protected String _iD_DHondtQuotient;

   /**
     * Sets the value of iD_DHondtQuotient in the entity DHondtQuotient
     *
     * @param iD_DHondtQuotient new value of the attribute iD_DHondtQuotient
     */
   protected void setID_DHondtQuotient(String iD_DHondtQuotient) {
      if (different(_iD_DHondtQuotient, iD_DHondtQuotient)) {
         _iD_DHondtQuotient = checkLength(iD_DHondtQuotient, DHondtQuotientModel.ID_DHONDTQUOTIENT_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of iD_DHondtQuotient in the entity DHondtQuotient
     *
     * @return value of the attribute iD_DHondtQuotient
     */
   public String getID_DHondtQuotient() {
      return _iD_DHondtQuotient;
   }

   /**
     * Type : VARCHAR Name : ID_Restsitzverteilung
     */
   protected String _id_Restsitzverteilung;

   /**
     * Sets the value of id_Restsitzverteilung in the entity DHondtQuotient
     *
     * @param id_Restsitzverteilung new value of the attribute id_Restsitzverteilung
     */
   public void setID_Restsitzverteilung(String id_Restsitzverteilung) {
      if (different(_id_Restsitzverteilung, id_Restsitzverteilung)) {
         _id_Restsitzverteilung = checkLength(id_Restsitzverteilung, DHondtQuotientModel.ID_RESTSITZVERTEILUNG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Restsitzverteilung in the entity DHondtQuotient
     *
     * @return value of the attribute id_Restsitzverteilung
     */
   public String getID_Restsitzverteilung() {
      return _id_Restsitzverteilung;
   }

   /**
     * Type : INT Name : Lauf
     */
   protected int _lauf;

   /**
     * Sets the value of lauf in the entity DHondtQuotient
     *
     * @param lauf new value of the attribute lauf
     */
   public void setLauf(int lauf) {
      if (_lauf != lauf) {
         _lauf = lauf;
         setModified();
      }
   }

   /**
     * Gets the value of lauf in the entity DHondtQuotient
     *
     * @return value of the attribute lauf
     */
   public int getLauf() {
      return _lauf;
   }

   /**
     * Type : INT Name : Zaehler
     */
   protected int _zaehler;

   /**
     * Sets the value of zaehler in the entity DHondtQuotient
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
     * Gets the value of zaehler in the entity DHondtQuotient
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
     * Sets the value of nenner in the entity DHondtQuotient
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
     * Gets the value of nenner in the entity DHondtQuotient
     *
     * @return value of the attribute nenner
     */
   public int getNenner() {
      return _nenner;
   }

   /**
     * Type : SMALLINT Name : SitzAusRestanteil
     */
   protected boolean _sitzAusRestanteil;

   /**
     * Sets the value of sitzAusRestanteil in the entity DHondtQuotient
     *
     * @param sitzAusRestanteil new value of the attribute sitzAusRestanteil
     */
   public void setSitzAusRestanteil(boolean sitzAusRestanteil) {
      if (_sitzAusRestanteil != sitzAusRestanteil) {
         _sitzAusRestanteil = sitzAusRestanteil;
         setModified();
      }
   }

   /**
     * Gets the value of sitzAusRestanteil in the entity DHondtQuotient
     *
     * @return value of the attribute sitzAusRestanteil
     */
   public boolean isSitzAusRestanteil() {
      return _sitzAusRestanteil;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _iD_DHondtQuotient.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof DHondtQuotientModelImpl) {
         DHondtQuotientModelImpl other = (DHondtQuotientModelImpl)object;
         return _iD_DHondtQuotient.equals(other._iD_DHondtQuotient);
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
         if (getID_DHondtQuotient() != null) {
            string += "iD_DHondtQuotient = " + getID_DHondtQuotient(); //$NON-NLS-1$
         }
         if (getID_Restsitzverteilung() != null) {
            string +=  ", id_Restsitzverteilung = " + getID_Restsitzverteilung(); //$NON-NLS-1$
         }
         string +=  ", lauf = " + getLauf(); //$NON-NLS-1$
         string +=  ", zaehler = " + getZaehler(); //$NON-NLS-1$
         string +=  ", nenner = " + getNenner(); //$NON-NLS-1$
         string +=  ", sitzAusRestanteil = " + isSitzAusRestanteil(); //$NON-NLS-1$
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
   public DHondtQuotientModel copy() {
      return (DHondtQuotientModel)clone();
   }
}
