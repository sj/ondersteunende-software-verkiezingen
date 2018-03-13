package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.FinderException;

/**
 * UnterverteilungHome
 * 
 * @author jon@ivu.de (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
public interface UnterverteilungHome extends BasicUnterverteilungHome {
  public Collection<Unterverteilung> findAllForP3(String id_Ergebniseingang) throws FinderException;

  public Collection<Unterverteilung> findAllForP2(String id_Ergebniseingang) throws FinderException;

}
