/*
 * GruppeGebietsspezifischModelImpl
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
import de.ivu.wahl.modell.GruppeGebietsspezifischModel;

/**
  * Model implementation for the entity GruppeGebietsspezifisch.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class GruppeGebietsspezifischModelImpl extends ModelImpl implements GruppeGebietsspezifischModel, Serializable {
   private static final Category LOGGER = Log4J.configure(GruppeGebietsspezifischModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(GruppeGebietsspezifischModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public GruppeGebietsspezifischModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_GruppeGebietsspezifisch key element of the type {@link String}
     */
   public GruppeGebietsspezifischModelImpl(String id_GruppeGebietsspezifisch) {
      setID_GruppeGebietsspezifisch(id_GruppeGebietsspezifisch);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(GruppeGebietsspezifischModelImpl other) {
      setID_UebergeordneteGG(other._id_UebergeordneteGG);
      setID_Gruppe(other._id_Gruppe);
      setID_Gebiet(other._id_Gebiet);
      setID_Liste(other._id_Liste);
      setPosition(other._position);
      setListeZugelassen(other._listeZugelassen);
   }

   /**
     * Type : VARCHAR Name : ID_GruppeGebietsspezifisch
     */
   protected String _id_GruppeGebietsspezifisch;

   /**
     * Sets the value of id_GruppeGebietsspezifisch in the entity GruppeGebietsspezifisch
     *
     * @param id_GruppeGebietsspezifisch new value of the attribute id_GruppeGebietsspezifisch
     */
   protected void setID_GruppeGebietsspezifisch(String id_GruppeGebietsspezifisch) {
      if (different(_id_GruppeGebietsspezifisch, id_GruppeGebietsspezifisch)) {
         _id_GruppeGebietsspezifisch = checkLength(id_GruppeGebietsspezifisch, GruppeGebietsspezifischModel.ID_GRUPPEGEBIETSSPEZIFISCH_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_GruppeGebietsspezifisch in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute id_GruppeGebietsspezifisch
     */
   public String getID_GruppeGebietsspezifisch() {
      return _id_GruppeGebietsspezifisch;
   }

   /**
     * Type : VARCHAR Name : ID_UebergeordneteGG
     */
   protected String _id_UebergeordneteGG;

   /**
     * Sets the value of id_UebergeordneteGG in the entity GruppeGebietsspezifisch
     *
     * @param id_UebergeordneteGG new value of the attribute id_UebergeordneteGG
     */
   public void setID_UebergeordneteGG(String id_UebergeordneteGG) {
      if (different(_id_UebergeordneteGG, id_UebergeordneteGG)) {
         _id_UebergeordneteGG = checkLength(id_UebergeordneteGG, GruppeGebietsspezifischModel.ID_UEBERGEORDNETEGG_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_UebergeordneteGG in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute id_UebergeordneteGG
     */
   public String getID_UebergeordneteGG() {
      return _id_UebergeordneteGG;
   }

   /**
     * Type : VARCHAR Name : ID_Gruppe
     */
   protected String _id_Gruppe;

   /**
     * Sets the value of id_Gruppe in the entity GruppeGebietsspezifisch
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   public void setID_Gruppe(String id_Gruppe) {
      if (different(_id_Gruppe, id_Gruppe)) {
         _id_Gruppe = checkLength(id_Gruppe, GruppeGebietsspezifischModel.ID_GRUPPE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gruppe in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute id_Gruppe
     */
   public String getID_Gruppe() {
      return _id_Gruppe;
   }

   /**
     * Type : VARCHAR Name : ID_Gebiet
     */
   protected String _id_Gebiet;

   /**
     * Sets the value of id_Gebiet in the entity GruppeGebietsspezifisch
     *
     * @param id_Gebiet new value of the attribute id_Gebiet
     */
   public void setID_Gebiet(String id_Gebiet) {
      if (different(_id_Gebiet, id_Gebiet)) {
         _id_Gebiet = checkLength(id_Gebiet, GruppeGebietsspezifischModel.ID_GEBIET_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gebiet in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute id_Gebiet
     */
   public String getID_Gebiet() {
      return _id_Gebiet;
   }

   /**
     * Type : VARCHAR Name : ID_Liste
     */
   protected String _id_Liste;

   /**
     * Sets the value of id_Liste in the entity GruppeGebietsspezifisch
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   public void setID_Liste(String id_Liste) {
      if (different(_id_Liste, id_Liste)) {
         _id_Liste = checkLength(id_Liste, GruppeGebietsspezifischModel.ID_LISTE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Liste in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute id_Liste
     */
   public String getID_Liste() {
      return _id_Liste;
   }

   /**
     * Type : INT Name : Position
     */
   protected int _position;

   /**
     * Sets the value of position in the entity GruppeGebietsspezifisch
     *
     * @param position new value of the attribute position
     */
   public void setPosition(int position) {
      if (_position != position) {
         _position = position;
         setModified();
      }
   }

   /**
     * Gets the value of position in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute position
     */
   public int getPosition() {
      return _position;
   }

   /**
     * Type : SMALLINT Name : ListeZugelassen
     */
   protected boolean _listeZugelassen;

   /**
     * Sets the value of listeZugelassen in the entity GruppeGebietsspezifisch
     *
     * @param listeZugelassen new value of the attribute listeZugelassen
     */
   public void setListeZugelassen(boolean listeZugelassen) {
      if (_listeZugelassen != listeZugelassen) {
         _listeZugelassen = listeZugelassen;
         setModified();
      }
   }

   /**
     * Gets the value of listeZugelassen in the entity GruppeGebietsspezifisch
     *
     * @return value of the attribute listeZugelassen
     */
   public boolean isListeZugelassen() {
      return _listeZugelassen;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_GruppeGebietsspezifisch.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof GruppeGebietsspezifischModelImpl) {
         GruppeGebietsspezifischModelImpl other = (GruppeGebietsspezifischModelImpl)object;
         return _id_GruppeGebietsspezifisch.equals(other._id_GruppeGebietsspezifisch);
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
         if (getID_GruppeGebietsspezifisch() != null) {
            string += "id_GruppeGebietsspezifisch = " + getID_GruppeGebietsspezifisch(); //$NON-NLS-1$
         }
         if (getID_UebergeordneteGG() != null) {
            string +=  ", id_UebergeordneteGG = " + getID_UebergeordneteGG(); //$NON-NLS-1$
         }
         if (getID_Gruppe() != null) {
            string +=  ", id_Gruppe = " + getID_Gruppe(); //$NON-NLS-1$
         }
         if (getID_Gebiet() != null) {
            string +=  ", id_Gebiet = " + getID_Gebiet(); //$NON-NLS-1$
         }
         if (getID_Liste() != null) {
            string +=  ", id_Liste = " + getID_Liste(); //$NON-NLS-1$
         }
         string +=  ", position = " + getPosition(); //$NON-NLS-1$
         string +=  ", listeZugelassen = " + isListeZugelassen(); //$NON-NLS-1$
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
   public GruppeGebietsspezifischModel copy() {
      return (GruppeGebietsspezifischModel)clone();
   }
}
