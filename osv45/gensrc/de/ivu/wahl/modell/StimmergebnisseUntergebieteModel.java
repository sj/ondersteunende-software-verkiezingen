/*
 * StimmergebnisseUntergebieteModel
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell;

import de.ivu.ejb.bmp.Model;

/**
  * Model interface for the entity StimmergebnisseUntergebiete.
  * Contains the list of properties including the IDs of the associated objects.
  * A navigation is not implemented (1:1, 1:n, m:n)
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface StimmergebnisseUntergebieteModel extends Model {
   /**
     * Gets the value of id_StimmergebnisseUntergebiete in the entity StimmergebnisseUntergebiete
     *
     * @return value of the attribute id_StimmergebnisseUntergebiete
     */
   String getID_StimmergebnisseUntergebiete();

   /**
     * The maximum size of id_StimmergebnisseUntergebiete
     * The size is limited by the database.
     */
   public static final int ID_STIMMERGEBNISSEUNTERGEBIETE_LENGTH = 13;

   /**
     * Sets the value of id_Ergebniseingang in the entity StimmergebnisseUntergebiete
     *
     * @param id_Ergebniseingang new value of the attribute id_Ergebniseingang
     */
   void setID_Ergebniseingang(String id_Ergebniseingang);

   /**
     * Gets the value of id_Ergebniseingang in the entity StimmergebnisseUntergebiete
     *
     * @return value of the attribute id_Ergebniseingang
     */
   String getID_Ergebniseingang();

   /**
     * The maximum size of id_Ergebniseingang
     * The size is limited by the database.
     */
   public static final int ID_ERGEBNISEINGANG_LENGTH = 13;

   /**
     * Sets the value of id_Gebiet in the entity StimmergebnisseUntergebiete
     *
     * @param id_Gebiet new value of the attribute id_Gebiet
     */
   void setID_Gebiet(String id_Gebiet);

   /**
     * Gets the value of id_Gebiet in the entity StimmergebnisseUntergebiete
     *
     * @return value of the attribute id_Gebiet
     */
   String getID_Gebiet();

   /**
     * The maximum size of id_Gebiet
     * The size is limited by the database.
     */
   public static final int ID_GEBIET_LENGTH = 13;

   /**
     * Sets the value of ergebnisseXML in the entity StimmergebnisseUntergebiete
     *
     * @param ergebnisseXML new value of the attribute ergebnisseXML
     */
   void setErgebnisseXML(String ergebnisseXML);

   /**
     * Gets the value of ergebnisseXML in the entity StimmergebnisseUntergebiete
     *
     * @return value of the attribute ergebnisseXML
     */
   String getErgebnisseXML();

   /**
     * The maximum size of ergebnisseXML
     * The size is limited by the database.
     */
   public static final int ERGEBNISSEXML_LENGTH = 65535;

   /**
     * Copies the object via Object.clone()
     *
     * @return Copy of the model object
     */
   public StimmergebnisseUntergebieteModel copy();
}
