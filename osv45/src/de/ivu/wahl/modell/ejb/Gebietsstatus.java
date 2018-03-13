package de.ivu.wahl.modell.ejb;

import javax.ejb.EJBException;

/**
 * Gebietsstatus
 * 
 * @author cos@ivu.de (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public interface Gebietsstatus extends BasicGebietsstatus {

  int getAnzahlErfassungseinheitenEingenangen() throws EJBException;

  int getAnzahlEingegangenByGebietsart(int gebietsart) throws EJBException;
}
