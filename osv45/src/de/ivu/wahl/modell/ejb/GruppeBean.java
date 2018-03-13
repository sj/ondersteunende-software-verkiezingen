/*
 * Copyright (c) 2002-2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb;

import static de.ivu.ejb.fw.DBABase.retrieveIDs;
import static de.ivu.wahl.modell.impl.BasicGruppeDBA.TABLENAME;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;

import de.ivu.ejb.IVUFinderException;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class GruppeBean extends BasicGruppeBean {
  private static final long serialVersionUID = 6622995079828863472L;

  /**
   * Find all groups by election and kind of group
   */
  public Collection<String> ejbFindAllByWahlAndGruppenart(String id_Wahl, int gruppenart)
      throws FinderException, EJBException {
    try {
      return retrieveIDs("select ID_Gruppe from " + TABLENAME + " where ID_Wahl=? and Gruppenart=?", //$NON-NLS-1$ //$NON-NLS-2$
          new Object[]{id_Wahl, gruppenart});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Get region specific entity of the group for the given region (if defined, else error)
   * 
   * @param id_Gebiet
   * @return
   * @throws EJBException
   */
  public GruppeGebietsspezifisch getGruppeGebietsspezifischByGebiet(String id_Gebiet)
      throws EJBException {

    try {
      return ((GruppeGebietsspezifischHome) findLocalHome("GruppeGebietsspezifisch")) //$NON-NLS-1$
          .findByGruppeAndGebiet(_details.getID_Gruppe(), id_Gebiet);
    } catch (ObjectNotFoundException onfe) {
      return null;
    } catch (FinderException fe) {
      throw new EJBException(fe);
    }
  }

  @Deprecated
  public Map<String, Integer> ejbHomeGetAnzahlSitzeProGruppeAufGebiet(String id_Gebiet,
      int wahlergebnisart) throws FinderException, EJBException {
    return null;
  }
}