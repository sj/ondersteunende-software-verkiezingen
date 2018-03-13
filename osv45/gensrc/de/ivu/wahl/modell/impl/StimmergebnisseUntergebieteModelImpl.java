/*
 * StimmergebnisseUntergebieteModelImpl
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
import de.ivu.wahl.modell.StimmergebnisseUntergebieteModel;

/**
  * Model implementation for the entity StimmergebnisseUntergebiete.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class StimmergebnisseUntergebieteModelImpl extends ModelImpl implements StimmergebnisseUntergebieteModel, Serializable {
   private static final Category LOGGER = Log4J.configure(StimmergebnisseUntergebieteModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(StimmergebnisseUntergebieteModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public StimmergebnisseUntergebieteModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_StimmergebnisseUntergebiete key element of the type {@link String}
     */
   public StimmergebnisseUntergebieteModelImpl(String id_StimmergebnisseUntergebiete) {
      setID_StimmergebnisseUntergebiete(id_StimmergebnisseUntergebiete);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(StimmergebnisseUntergebieteModelImpl other) {
      setID_Ergebniseingang(other._id_Ergebniseingang);
      setID_Gebiet(other._id_Gebiet);
      setErgebnisseXML(other._ergebnisseXML);
   }

   /**
     * Type : VARCHAR Name : ID_StimmergebnisseUntergebiete
     */
   protected String _id_StimmergebnisseUntergebiete;

   /**
     * Sets the value of id_StimmergebnisseUntergebiete in the entity StimmergebnisseUntergebiete
     *
     * @param id_StimmergebnisseUntergebiete new value of the attribute id_StimmergebnisseUntergebiete
     */
   protected void setID_StimmergebnisseUntergebiete(String id_StimmergebnisseUntergebiete) {
      if (different(_id_StimmergebnisseUntergebiete, id_StimmergebnisseUntergebiete)) {
         _id_StimmergebnisseUntergebiete = checkLength(id_StimmergebnisseUntergebiete, StimmergebnisseUntergebieteModel.ID_STIMMERGEBNISSEUNTERGEBIETE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_StimmergebnisseUntergebiete in the entity StimmergebnisseUntergebiete
     *
     * @return value of the attribute id_StimmergebnisseUntergebiete
     */
   public String getID_StimmergebnisseUntergebiete() {
      return _id_StimmergebnisseUntergebiete;
   }

   /**
     * Type : VARCHAR Name : ID_Ergebniseingang
     */
   protected String _id_Ergebniseingang;

   /**
     * Sets the value of id_Ergebniseingang in the entity StimmergebnisseUntergebiete
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   public void setID_Ergebniseingang(String id_Ergebniseingang) {
      if (different(_id_Ergebniseingang, id_Ergebniseingang)) {
         _id_Ergebniseingang = checkLength(id_Ergebniseingang, StimmergebnisseUntergebieteModel.ID_ERGEBNISEINGANG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Ergebniseingang in the entity StimmergebnisseUntergebiete
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
     * Sets the value of id_Gebiet in the entity StimmergebnisseUntergebiete
     *
     * @param id_Gebiet new value of the attribute id_Gebiet
     */
   public void setID_Gebiet(String id_Gebiet) {
      if (different(_id_Gebiet, id_Gebiet)) {
         _id_Gebiet = checkLength(id_Gebiet, StimmergebnisseUntergebieteModel.ID_GEBIET_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gebiet in the entity StimmergebnisseUntergebiete
     *
     * @return value of the attribute id_Gebiet
     */
   public String getID_Gebiet() {
      return _id_Gebiet;
   }

   /**
     * Type : TEXT Name : ErgebnisseXML
     */
   protected String _ergebnisseXML;

   /**
     * Sets the value of ergebnisseXML in the entity StimmergebnisseUntergebiete
     *
     * @param ergebnisseXML new value of the attribute ergebnisseXML
     */
   public void setErgebnisseXML(String ergebnisseXML) {
      if (different(_ergebnisseXML, ergebnisseXML)) {
         _ergebnisseXML = checkLength(ergebnisseXML, StimmergebnisseUntergebieteModel.ERGEBNISSEXML_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of ergebnisseXML in the entity StimmergebnisseUntergebiete
     *
     * @return value of the attribute ergebnisseXML
     */
   public String getErgebnisseXML() {
      return _ergebnisseXML;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_StimmergebnisseUntergebiete.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof StimmergebnisseUntergebieteModelImpl) {
         StimmergebnisseUntergebieteModelImpl other = (StimmergebnisseUntergebieteModelImpl)object;
         return _id_StimmergebnisseUntergebiete.equals(other._id_StimmergebnisseUntergebiete);
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
         if (getID_StimmergebnisseUntergebiete() != null) {
            string += "id_StimmergebnisseUntergebiete = " + getID_StimmergebnisseUntergebiete(); //$NON-NLS-1$
         }
         if (getID_Ergebniseingang() != null) {
            string +=  ", id_Ergebniseingang = " + getID_Ergebniseingang(); //$NON-NLS-1$
         }
         if (getID_Gebiet() != null) {
            string +=  ", id_Gebiet = " + getID_Gebiet(); //$NON-NLS-1$
         }
         if (getErgebnisseXML() != null) {
            string +=  ", ergebnisseXML = " + getErgebnisseXML(); //$NON-NLS-1$
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
   public StimmergebnisseUntergebieteModel copy() {
      return (StimmergebnisseUntergebieteModel)clone();
   }
}
