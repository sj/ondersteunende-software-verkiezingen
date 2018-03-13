/*
 * RgConstants
 * 
 * Created on 05.10.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

public interface RgConstants {
  /** The following may be part of the filenames for EML510 */
  public static final String FILENAME_FRAGMENT_STEMBUREAU = "stembureau"; //$NON-NLS-1$
  public static final String FILENAME_FRAGMENT_MUNICIPALITY = "gemeente"; //$NON-NLS-1$
  public static final String FILENAME_FRAGMENT_DISTRICT = "kieskring"; //$NON-NLS-1$
  public static final String FILENAME_FRAGMENT_PROVINCE = "provincie"; //$NON-NLS-1$
  public static final String FILENAME_FRAGMENT_WATER_BOARD = "waterschap"; //$NON-NLS-1$
  public static final String FILENAME_FRAGMENT_ISLAND_MUNICIPALITY = "openbaar lichaam"; //$NON-NLS-1$
  public static final String FILENAME_FRAGMENT_ISLAND_MUNICIPALITY_IN_FILENAME = "openbaarlichaam"; //$NON-NLS-1

  /** This may be part of the election domain in deelraad elections */
  public static final String CITY_AMSTERDAM = "Amsterdam"; //$NON-NLS-1$

  /**
   * The following may be part of the filenames for EML510 for BC elections in Amsterdam or GC
   * elections Rotterdam
   */
  public static final String FILENAME_FRAGMENT_BOROUGH_AMSTERDAM = "stadsdeel"; //$NON-NLS-1$ 
  public static final String FILENAME_FRAGMENT_BOROUGH_ROTTERDAM = "deelgemeente"; //$NON-NLS-1$ 

  /** The following may be part of the path for EML510 files */
  public static final String PATH_FRAGMENT_STEMBUREAUS = "."; //$NON-NLS-1$
  public static final String PATH_FRAGMENT_MUNICIPALITIES = "."; //$NON-NLS-1$
  public static final String PATH_FRAGMENT_DISTRICTS = "."; //$NON-NLS-1$
  public static final String PATH_FRAGMENT_OTHERS = "andere"; //$NON-NLS-1$

  /** The following are the main components of the export files */
  public static final String FILENAME_110A = "Verkiezingsdefinitie"; //$NON-NLS-1$;
  public static final String FILENAME_110B = "Stembureaus"; //$NON-NLS-1$;
  public static final String FILENAME_210 = "Kandidaten"; //$NON-NLS-1$;
  public static final String FILENAME_230A = "Geldige lijsten"; //$NON-NLS-1$;
  public static final String FILENAME_230B = "Kandidatenlijsten"; //$NON-NLS-1$;
  public static final String FILENAME_230C = "Totaallijsten"; //$NON-NLS-1$;
  public static final String FILENAME_510D_COMPLETE = "Totaaltelling"; //$NON-NLS-1$
  public static final String FILENAME_510D_LISTS = "Lijstentotaltelling"; //$NON-NLS-1$
  public static final String FILENAME_510_COMPLETE = "Telling"; //$NON-NLS-1$
  public static final String FILENAME_510_LISTS = "Lijstentelling"; //$NON-NLS-1$
  public static final String FILENAME_520 = "Resultaat"; //$NON-NLS-1$
  public static final String FILENAME_630 = "Referendumvraag"; //$NON-NLS-1$;

  /** The following are components of directory names */
  public static final String DIRNAME_EML = "."; //$NON-NLS-1$;

  /** File suffixes */
  public static final String SUFFIX_EML_XML = ".eml.xml"; //$NON-NLS-1$;

  /** Suffix for empty EML */
  public static final String EMPTY_EML_SUFFIX = "leeg"; //$NON-NLS-1$;
}
