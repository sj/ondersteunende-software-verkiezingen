/*
 * PersonendatenBean
 * 
 * Created on Dec 15, 2008
 * Copyright (c) 2008 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.FinderException;

import de.ivu.ejb.IVUFinderException;
import de.ivu.ejb.fw.DBABase;
import de.ivu.wahl.modell.impl.PersonendatenDBA;

/**
 * @author mur@ivu.de, IVU Traffic Technologies AG
 */
public class PersonendatenBean extends BasicPersonendatenBean {

  private static final long serialVersionUID = -8805006408532042299L;

  /**
   * Get data of all persons
   * 
   * @return alle Personendaten
   * @throws FinderException
   * @throws FinderException
   */
  @Override
  public Collection<String> ejbFindAll() throws IVUFinderException {

    try {
      return DBABase.retrieveIDs("select " + PersonendatenDBA.ID_PERSONENDATEN + " from " //$NON-NLS-1$ //$NON-NLS-2$
          + PersonendatenDBA.TABLENAME + " order by " + PersonendatenDBA.NACHNAME); //$NON-NLS-1$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  @Override
  public String getAnzeigeName() {
    return getDetails().getAnzeigeName();
  }

  @Override
  public boolean hasListenkandidatur() {
    return getListenkandidaturCol() != null && getListenkandidaturCol().size() > 0;
  }

  @Override
  public List<Integer> getListenkandidaturenGruppenSchluesselSortiert() {
    if (hasListenkandidatur()) {
      Set<Integer> listen = new TreeSet<Integer>();
      for (Listenkandidatur lk : getListenkandidaturCol()) {
        listen.add(lk.getGruppe().getSchluessel());
      }
      return new ArrayList<Integer>(listen);
    }
    return Collections.emptyList();
  }
}
