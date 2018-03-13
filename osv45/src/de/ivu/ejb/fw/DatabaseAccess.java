package de.ivu.ejb.fw;

import java.io.Serializable;
import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;

/**
 * Base class for database accessor classes. <BR>
 * Provides a set of common database functions.<BR>
 * These include making connections and running queries.
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class DatabaseAccess implements Serializable {
  private static final long serialVersionUID = 5135443026095726837L;
  private static final Category LOGGER = Log4J.configure(DatabaseAccess.class);

  static {
    LOGGER.info(Log4J.dumpVersion(DatabaseAccess.class, Log4J.extractVersion("$Revision: 1.6 $"))); //$NON-NLS-1$
  }

  /**
   * The name, that is used to connect to database. This name is referenced in ejb.xml, and then
   * mapped in jboss.xml to a resource manager, who is able to connect to the *real* DataSource with
   * the given JNDI name from jboss.jcml. Puh!
   */
  private static final String DB_NAME = "java:comp/env/jdbc/wahl"; //$NON-NLS-1$
  protected static final int QUERY_TIMEOUT = 0;

  /**
   * Stores the most recent error in the class
   */
  private static volatile String __errorString = ""; //$NON-NLS-1$
  private static volatile InitialContext __ic = null;

  /**
   * Sets the error string.
   * 
   * @param error der zu speichernde Fehlerstring
   */
  public static void setError(String error) {
    __errorString = error;
    LOGGER.error("db access error : " + __errorString); //$NON-NLS-1$
  }

  /**
   * Gets the error string.
   * 
   * @return der gespeicherte Fehlerstring
   */
  public static String getError() {
    return __errorString;
  }

  /**
   * Gets a valid connection to the database via JNDI
   * 
   * @return Datenbankverbindung über die eingestellte Datenquelle
   */
  public static Connection connect() {
    try {
      if (__ic == null) {
        __ic = new InitialContext();
      }
      return ((DataSource) __ic.lookup(DB_NAME)).getConnection();
    } catch (Exception e) {
      LOGGER.error("Could not get connection to DataSource " + DB_NAME + "!", e); //$NON-NLS-1$ //$NON-NLS-2$
      if (e instanceof RuntimeException) {
        throw (RuntimeException) e;
      } else {
        throw new RuntimeException(e.getMessage(), e);
      }
    }
  }

  /**
   * Unblocks the database connection (back to the Pool)
   * 
   * @param con connection, which is given back
   * @return <code>true</code>, if the operation was successful
   */
  public static boolean release(Connection con) {
    if (con != null) { // is only null when error in connect()
      try {
        con.close();
        return true;
      } catch (Exception e) {
        LOGGER.error("Could not disconnect from to DataSource " + DB_NAME + "!", e); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    return false;
  }

  protected static void setCorruptionError() {
    setError("Error! Database corruption!! inserted multiple records"); //$NON-NLS-1$
  }
}
