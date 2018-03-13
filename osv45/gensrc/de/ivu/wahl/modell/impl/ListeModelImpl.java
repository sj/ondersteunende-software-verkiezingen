/*
 * ListeModelImpl
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
import de.ivu.wahl.modell.ListeModel;

/**
  * Model implementation for the entity Liste.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */

public class ListeModelImpl extends ModelImpl implements ListeModel, Serializable {
   private static final Category LOGGER = Log4J.configure(ListeModelImpl.class);
   static {
      LOGGER.info(Log4J.dumpVersion(ListeModelImpl.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   /**
     * Default constructor
     */
   public ListeModelImpl() {
      // No inititalization, only generation of the instance
   }

   /**
     * Constructor with key
     *
     * @param id_Liste key element of the type {@link String}
     */
   public ListeModelImpl(String id_Liste) {
      setID_Liste(id_Liste);
   }

   /**
     * Method for copying
     *
     * @param other the model object the data is copied from
     */
   public void copyFrom(ListeModelImpl other) {
      setID_Wahl(other._id_Wahl);
      setID_Gruppe(other._id_Gruppe);
      setTyp(other._typ);
      setSatz(other._satz);
      setName(other._name);
      setGeschlechtSichtbar(other._geschlechtSichtbar);
      setPublicationLanguage(other._publicationLanguage);
   }

   /**
     * Type : VARCHAR Name : ID_Liste
     */
   protected String _id_Liste;

   /**
     * Sets the value of id_Liste in the entity Liste
     *
     * @param id_Liste new value of the attribute id_Liste
     */
   protected void setID_Liste(String id_Liste) {
      if (different(_id_Liste, id_Liste)) {
         _id_Liste = checkLength(id_Liste, ListeModel.ID_LISTE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Liste in the entity Liste
     *
     * @return value of the attribute id_Liste
     */
   public String getID_Liste() {
      return _id_Liste;
   }

   /**
     * Type : VARCHAR Name : ID_Wahl
     */
   protected String _id_Wahl;

   /**
     * Sets the value of id_Wahl in the entity Liste
     *
     * @param id_Wahl new value of the attribute id_Wahl
     */
   public void setID_Wahl(String id_Wahl) {
      if (different(_id_Wahl, id_Wahl)) {
         _id_Wahl = checkLength(id_Wahl, ListeModel.ID_WAHL_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Wahl in the entity Liste
     *
     * @return value of the attribute id_Wahl
     */
   public String getID_Wahl() {
      return _id_Wahl;
   }

   /**
     * Type : VARCHAR Name : ID_Gruppe
     */
   protected String _id_Gruppe;

   /**
     * Sets the value of id_Gruppe in the entity Liste
     *
     * @param id_Gruppe new value of the attribute id_Gruppe
     */
   public void setID_Gruppe(String id_Gruppe) {
      if (different(_id_Gruppe, id_Gruppe)) {
         _id_Gruppe = checkLength(id_Gruppe, ListeModel.ID_GRUPPE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of id_Gruppe in the entity Liste
     *
     * @return value of the attribute id_Gruppe
     */
   public String getID_Gruppe() {
      return _id_Gruppe;
   }

   /**
     * Type : VARCHAR Name : Typ
     */
   protected String _typ;

   /**
     * Sets the value of typ in the entity Liste
     *
     * @param typ new value of the attribute typ
     */
   public void setTyp(String typ) {
      if (different(_typ, typ)) {
         _typ = checkLength(typ, ListeModel.TYP_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of typ in the entity Liste
     *
     * @return value of the attribute typ
     */
   public String getTyp() {
      return _typ;
   }

   /**
     * Type : INT Name : Satz
     */
   protected int _satz;

   /**
     * Sets the value of satz in the entity Liste
     *
     * @param satz new value of the attribute satz
     */
   public void setSatz(int satz) {
      if (_satz != satz) {
         _satz = satz;
         setModified();
      }
   }

   /**
     * Gets the value of satz in the entity Liste
     *
     * @return value of the attribute satz
     */
   public int getSatz() {
      return _satz;
   }

   /**
     * Type : VARCHAR Name : Name
     */
   protected String _name;

   /**
     * Sets the value of name in the entity Liste
     *
     * @param name new value of the attribute name
     */
   public void setName(String name) {
      if (different(_name, name)) {
         _name = checkLength(name, ListeModel.NAME_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of name in the entity Liste
     *
     * @return value of the attribute name
     */
   public String getName() {
      return _name;
   }

   /**
     * Type : SMALLINT Name : GeschlechtSichtbar
     */
   protected boolean _geschlechtSichtbar;

   /**
     * Sets the value of geschlechtSichtbar in the entity Liste
     *
     * @param geschlechtSichtbar new value of the attribute geschlechtSichtbar
     */
   public void setGeschlechtSichtbar(boolean geschlechtSichtbar) {
      if (_geschlechtSichtbar != geschlechtSichtbar) {
         _geschlechtSichtbar = geschlechtSichtbar;
         setModified();
      }
   }

   /**
     * Gets the value of geschlechtSichtbar in the entity Liste
     *
     * @return value of the attribute geschlechtSichtbar
     */
   public boolean isGeschlechtSichtbar() {
      return _geschlechtSichtbar;
   }

   /**
     * Type : VARCHAR Name : PublicationLanguage
     */
   protected String _publicationLanguage;

   /**
     * Sets the value of publicationLanguage in the entity Liste
     *
     * @param publicationLanguage new value of the attribute publicationLanguage
     */
   public void setPublicationLanguage(String publicationLanguage) {
      if (different(_publicationLanguage, publicationLanguage)) {
         _publicationLanguage = checkLength(publicationLanguage, ListeModel.PUBLICATIONLANGUAGE_LENGTH);
         setModified();
      }
   }

   /**
     * Gets the value of publicationLanguage in the entity Liste
     *
     * @return value of the attribute publicationLanguage
     */
   public String getPublicationLanguage() {
      return _publicationLanguage;
   }

   /**
     * Overwrites the hashCode method in Object
     *
     * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys
     */
   @Override
   public int hashCode() {
      return _id_Liste.hashCode();
   }

   /**
     * Overwrites the equals method in Object
     *
     * @param object Objekt, by what this object can be testet for equality
     * @return <code>true</code> if the compared objects have an equal primary key
     */
   @Override
   public boolean equals(Object object) {
      if (object instanceof ListeModelImpl) {
         ListeModelImpl other = (ListeModelImpl)object;
         return _id_Liste.equals(other._id_Liste);
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
         if (getID_Liste() != null) {
            string += "id_Liste = " + getID_Liste(); //$NON-NLS-1$
         }
         if (getID_Wahl() != null) {
            string +=  ", id_Wahl = " + getID_Wahl(); //$NON-NLS-1$
         }
         if (getID_Gruppe() != null) {
            string +=  ", id_Gruppe = " + getID_Gruppe(); //$NON-NLS-1$
         }
         if (getTyp() != null) {
            string +=  ", typ = " + getTyp(); //$NON-NLS-1$
         }
         string +=  ", satz = " + getSatz(); //$NON-NLS-1$
         if (getName() != null) {
            string +=  ", name = " + getName(); //$NON-NLS-1$
         }
         string +=  ", geschlechtSichtbar = " + isGeschlechtSichtbar(); //$NON-NLS-1$
         if (getPublicationLanguage() != null) {
            string +=  ", publicationLanguage = " + getPublicationLanguage(); //$NON-NLS-1$
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
   public ListeModel copy() {
      return (ListeModel)clone();
   }
}
