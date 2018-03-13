/*
 * ListeBean
 * 
 * Copyright (c) 2002-4 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb;

import java.sql.SQLException;
import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.ejb.fw.DBABase;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.modell.impl.GruppeGebietsspezifischDBA;
import de.ivu.wahl.modell.impl.ListeDBA;

/**
 * ListeBean
 * 
 * @author cos@ivu.de IVU Traffic Technologies AG
 */
public class ListeBean extends BasicListeBean {
  private static final long serialVersionUID = -463469871914822049L;

  private static final Category LOGGER = Log4J.configure(ListeBean.class);
  static {
    LOGGER.info(Log4J.dumpVersion(ListeBean.class, Log4J.extractVersion("$Revision: 1.13 $"))); //$NON-NLS-1$
  }

  /**
   * Query to find all lists nominated for this region
   */
  private final static String LISTS_FOR_REGIONS_QUERY = " select " + ListeDBA.ID_LISTE_QUAL + " from " + //$NON-NLS-1$ //$NON-NLS-2$
      ListeDBA.TABLENAME + "," + GruppeGebietsspezifischDBA.TABLENAME //$NON-NLS-1$
      + " where " + ListeDBA.ID_LISTE_QUAL + " = " + GruppeGebietsspezifischDBA.ID_LISTE_QUAL + //$NON-NLS-1$ //$NON-NLS-2$
      " and " + GruppeGebietsspezifischDBA.ID_GEBIET_QUAL //$NON-NLS-1$
      + "=? order by " + GruppeGebietsspezifischDBA.POSITION_QUAL;//$NON-NLS-1$ 

  /**
   */
  public Collection<String> ejbFindAllByGebiet(String id_Gebiet)
      throws FinderException, EJBException {
    try {
      return DBABase.retrieveIDs(LISTS_FOR_REGIONS_QUERY, new Object[]{id_Gebiet});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

}
