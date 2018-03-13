package de.ivu.wahl.wus.utils;

/**
 * Constant definitions for report generator preferences
 */
public final class SystemPropertyConstants {

  private SystemPropertyConstants() {
  }

  public static final String OUTPUT_DIRECTORY_PATH = "outputdirectory"; //$NON-NLS-1$

  public static final String ASK_FOR_OUTPUT_DIRECTORY_PATH = "askforoutputdirectory"; //$NON-NLS-1$

  public static final String COPY_ALL_FILES_DESTINATION = "copyallfilesdestination"; //$NON-NLS-1$

  /*
   * Testing
   */

  public static boolean isMacOS() {
    if (System.getProperty("os.name").toLowerCase().matches(".*mac.*")) { //$NON-NLS-1$ //$NON-NLS-2$
      return true;
    }
    return false;
  }

}
