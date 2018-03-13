/*
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb.service;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJBException;

import de.ivu.wahl.modell.ejb.Gebiet;

/**
 * SessionBean interface for business services related to the entities {@link Gebiet}
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface GebietHandling {

  /**
   * @return Collection of the regions of the level where candidate lists may be nominated, sorted
   *         by list number.
   */
  public Collection<Gebiet> getGebieteMitListen();

  /**
   * @return a list of all provinces sorted by number
   */
  public List<Gebiet> getProvinces();

  /**
   * @return a collection of the regions that have vote values
   */
  public Collection<Gebiet> getRegionsForVoteValues() throws EJBException;

  /**
   * @return
   */
  public VoteValues getVoteValues();

}