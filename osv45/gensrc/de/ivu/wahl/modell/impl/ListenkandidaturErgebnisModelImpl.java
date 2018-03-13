/*
 * ListenkandidaturErgebnisModelImpl
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
import de.ivu.wahl.modell.ListenkandidaturErgebnisModel;

/**
  * Model implementation for the entity ListenkandidaturErgebnis.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class ListenkandidaturErgebnisModelImpl extends ModelImpl implements ListenkandidaturErgebnisModel, Serializable {
   private static final Category LOGGER = Log4J.configure(ListenkandidaturErgebnisModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(ListenkandidaturErgebnisModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public ListenkandidaturErgebnisModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_ListenkandidaturErgebnis key element of the type {@link String}
     */
   public ListenkandidaturErgebnisModelImpl(String id_ListenkandidaturErgebnis) {
      setID_ListenkandidaturErgebnis(id_ListenkandidaturErgebnis);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(ListenkandidaturErgebnisModelImpl other) {
      setID_Listenkandidatur(other._id_Listenkandidatur);
      setID_Ergebniseingang(other._id_Ergebniseingang);
      setListenplatz(other._listenplatz);
      setGewaehlt(other._gewaehlt);
      setGewaehltInGruppe(other._gewaehltInGruppe);
      setBevorzugtGewaehlt(other._bevorzugtGewaehlt);
      setLosteilnehmer(other._losteilnehmer);
      setLosgewinner(other._losgewinner);
   }

   /**
     * Type : VARCHAR Name : ID_ListenkandidaturErgebnis
     */
   protected String _id_ListenkandidaturErgebnis;

   /**
     * Sets the value of id_ListenkandidaturErgebnis in the entity ListenkandidaturErgebnis
     *
     * @param id_ListenkandidaturErgebnis new value of the attribute id_ListenkandidaturErgebnis
     */
   protected void setID_ListenkandidaturErgebnis(String id_ListenkandidaturErgebnis) {
      if (different(_id_ListenkandidaturErgebnis, id_ListenkandidaturErgebnis)) {
         _id_ListenkandidaturErgebnis = checkLength(id_ListenkandidaturErgebnis, ListenkandidaturErgebnisModel.ID_LISTENKANDIDATURERGEBNIS_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_ListenkandidaturErgebnis in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute id_ListenkandidaturErgebnis
     */
   public String getID_ListenkandidaturErgebnis() {
      return _id_ListenkandidaturErgebnis;
   }

   /**
     * Type : VARCHAR Name : ID_Listenkandidatur
     */
   protected String _id_Listenkandidatur;

   /**
     * Sets the value of id_Listenkandidatur in the entity ListenkandidaturErgebnis
     *
     * @param id_Listenkandidatur new value of the attribute id_Listenkandidatur
     */
   public void setID_Listenkandidatur(String id_Listenkandidatur) {
      if (different(_id_Listenkandidatur, id_Listenkandidatur)) {
         _id_Listenkandidatur = checkLength(id_Listenkandidatur, ListenkandidaturErgebnisModel.ID_LISTENKANDIDATUR_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Listenkandidatur in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute id_Listenkandidatur
     */
   public String getID_Listenkandidatur() {
      return _id_Listenkandidatur;
   }

   /**
     * Type : VARCHAR Name : ID_Ergebniseingang
     */
   protected String _id_Ergebniseingang;

   /**
     * Sets the value of id_Ergebniseingang in the entity ListenkandidaturErgebnis
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   public void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (different(_id_Ergebniseingang, id_Ergebniseingang)) {
         _id_Ergebniseingang = checkLength(id_Ergebniseingang, ListenkandidaturErgebnisModel.ID_ERGEBNISEINGANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Ergebniseingang in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute id_Ergebniseingang
     */
   public String getID_Ergebniseingang() {
      return _id_Ergebniseingang;
   }

   /**
     * Type : INT Name : Listenplatz
     */
   protected int _listenplatz;

   /**
     * Sets the value of listenplatz in the entity ListenkandidaturErgebnis
     *
     * @param listenplatz new value of the attribute listenplatz
     */
   public void setListenplatz(int listenplatz) {
      if (_listenplatz != listenplatz) {
         _listenplatz = listenplatz;
         setModified();
      }
   }

   /**
     * Gets the value of listenplatz in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute listenplatz
     */
   public int getListenplatz() {
      return _listenplatz;
   }

   /**
     * Type : SMALLINT Name : Gewaehlt
     */
   protected boolean _gewaehlt;

   /**
     * Sets the value of gewaehlt in the entity ListenkandidaturErgebnis
     *
     * @param gewaehlt new value of the attribute gewaehlt
     */
   public void setGewaehlt(boolean gewaehlt) {
      if (_gewaehlt != gewaehlt) {
         _gewaehlt = gewaehlt;
         setModified();
      }
   }

   /**
     * Gets the value of gewaehlt in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute gewaehlt
     */
   public boolean isGewaehlt() {
      return _gewaehlt;
   }

   /**
     * Type : SMALLINT Name : GewaehltInGruppe
     */
   protected boolean _gewaehltInGruppe;

   /**
     * Sets the value of gewaehltInGruppe in the entity ListenkandidaturErgebnis
     *
     * @param gewaehltInGruppe new value of the attribute gewaehltInGruppe
     */
   public void setGewaehltInGruppe(boolean gewaehltInGruppe) {
      if (_gewaehltInGruppe != gewaehltInGruppe) {
         _gewaehltInGruppe = gewaehltInGruppe;
         setModified();
      }
   }

   /**
     * Gets the value of gewaehltInGruppe in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute gewaehltInGruppe
     */
   public boolean isGewaehltInGruppe() {
      return _gewaehltInGruppe;
   }

   /**
     * Type : SMALLINT Name : BevorzugtGewaehlt
     */
   protected boolean _bevorzugtGewaehlt;

   /**
     * Sets the value of bevorzugtGewaehlt in the entity ListenkandidaturErgebnis
     *
     * @param bevorzugtGewaehlt new value of the attribute bevorzugtGewaehlt
     */
   public void setBevorzugtGewaehlt(boolean bevorzugtGewaehlt) {
      if (_bevorzugtGewaehlt != bevorzugtGewaehlt) {
         _bevorzugtGewaehlt = bevorzugtGewaehlt;
         setModified();
      }
   }

   /**
     * Gets the value of bevorzugtGewaehlt in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute bevorzugtGewaehlt
     */
   public boolean isBevorzugtGewaehlt() {
      return _bevorzugtGewaehlt;
   }

   /**
     * Type : SMALLINT Name : Losteilnehmer
     */
   protected boolean _losteilnehmer;

   /**
     * Sets the value of losteilnehmer in the entity ListenkandidaturErgebnis
     *
     * @param losteilnehmer new value of the attribute losteilnehmer
     */
   public void setLosteilnehmer(boolean losteilnehmer) {
      if (_losteilnehmer != losteilnehmer) {
         _losteilnehmer = losteilnehmer;
         setModified();
      }
   }

   /**
     * Gets the value of losteilnehmer in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute losteilnehmer
     */
   public boolean isLosteilnehmer() {
      return _losteilnehmer;
   }

   /**
     * Type : SMALLINT Name : Losgewinner
     */
   protected boolean _losgewinner;

   /**
     * Sets the value of losgewinner in the entity ListenkandidaturErgebnis
     *
     * @param losgewinner new value of the attribute losgewinner
     */
   public void setLosgewinner(boolean losgewinner) {
      if (_losgewinner != losgewinner) {
         _losgewinner = losgewinner;
         setModified();
      }
   }

   /**
     * Gets the value of losgewinner in the entity ListenkandidaturErgebnis
     *
     * @return value of the attribute losgewinner
     */
   public boolean isLosgewinner() {
      return _losgewinner;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_ListenkandidaturErgebnis.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof ListenkandidaturErgebnisModelImpl) {
         ListenkandidaturErgebnisModelImpl other = (ListenkandidaturErgebnisModelImpl)object;
         return _id_ListenkandidaturErgebnis.equals(other._id_ListenkandidaturErgebnis);
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
         if (getID_ListenkandidaturErgebnis() != null) {
            string += "id_ListenkandidaturErgebnis = " + getID_ListenkandidaturErgebnis(); //$NON-NLS-1$
         }
         if (getID_Listenkandidatur() != null) {
            string +=  ", id_Listenkandidatur = " + getID_Listenkandidatur(); //$NON-NLS-1$
         }
         if (getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + getID_Ergebniseingang(); //$NON-NLS-1$
         }
         string +=  ", listenplatz = " + getListenplatz(); //$NON-NLS-1$
         string +=  ", gewaehlt = " + isGewaehlt(); //$NON-NLS-1$
         string +=  ", gewaehltInGruppe = " + isGewaehltInGruppe(); //$NON-NLS-1$
         string +=  ", bevorzugtGewaehlt = " + isBevorzugtGewaehlt(); //$NON-NLS-1$
         string +=  ", losteilnehmer = " + isLosteilnehmer(); //$NON-NLS-1$
         string +=  ", losgewinner = " + isLosgewinner(); //$NON-NLS-1$
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
   public ListenkandidaturErgebnisModel copy() {
      return (ListenkandidaturErgebnisModel)clone();
   }
}
