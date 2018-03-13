/*
 * Stimmergebnis
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.ejb;


import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import de.ivu.wahl.modell.StimmergebnisModel;

/**
  * Interface for the entity Stimmergebnis as Entity Bean.
  * The navigation (1:1, 1:n, m:n) is contained
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public interface Stimmergebnis extends EJBLocalObject, StimmergebnisModel {
   /**
     * Method to receive a model object for the setting of the current internal object state.
     * Optimizes the Client-Server-Performance
     *
     * @param details model object with new state
     */
   void setDetails(StimmergebnisModel details);

   /**
     * Method to receive a model object, which represents the current internal state of the object.
     * Optimizes the Client-Server-Performance
     *
     * @return Model object with new internal state
     */
   StimmergebnisModel getDetails();


   /**
     * Navigation to the associated entity of the type {@link Ergebniseingang}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Ergebniseingang getErgebniseingang() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Ergebniseingang}
     *
     * @param ergebniseingang the corresponding EJBObject
     */
   void setErgebniseingang(Ergebniseingang ergebniseingang);

   /**
     * Navigation to the associated entity of the type {@link GruppeGebietsspezifisch}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   GruppeGebietsspezifisch getGruppeGebietsspezifisch() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link GruppeGebietsspezifisch}
     *
     * @param gruppeGebietsspezifisch the corresponding EJBObject
     */
   void setGruppeGebietsspezifisch(GruppeGebietsspezifisch gruppeGebietsspezifisch);

   /**
     * Navigation to the associated entity of the type {@link Gebiet}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Gebiet getGebiet() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Gebiet}
     *
     * @param gebiet the corresponding EJBObject
     */
   void setGebiet(Gebiet gebiet);

   /**
     * Navigation to the associated entity of the type {@link Listenkandidatur}
     *
     * @return the corresponding EJBObject
     * @throws EJBException: an error occurred
     */
   Listenkandidatur getListenkandidatur() throws EJBException;

   /**
     * Setting of the associated entity of the type {@link Listenkandidatur}
     *
     * @param listenkandidatur the corresponding EJBObject
     */
   void setListenkandidatur(Listenkandidatur listenkandidatur);
}
