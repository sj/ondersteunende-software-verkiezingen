package de.ivu.wahl.client;

/**
 * Exception zur Übermittlung des Abmeldewunsches
 * 
 * @author Dr. Domagoj Cosic
 * @created 28. September 2001
 */

public class LogoutException extends RuntimeException {

  /** long */
  private static final long serialVersionUID = -8035420844809039739L;

  /**
   * Constructor for the LogoutException object
   */
  public LogoutException() {
    super("LOGOUT"); //$NON-NLS-1$
  }
}
