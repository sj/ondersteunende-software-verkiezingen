/*--------------------------------------------------------------
   Company   : IVU Traffic Technologies AG
 ---------------------------------------------------------------*/
package de.ivu.util.session;

import java.io.Serializable;
import java.util.Map;

/**
 * Step is the interface for a Session Step. A Session Step represents the server side state
 * corresponding to a particular browser view either in the browser history or in one of the browser
 * windows opened within the same session. Because there is a Session Step for each of the previous
 * views (not necessarily vice versa), the user can return to whatever he used to see in the past,
 * as long as it is within the same session. A Session Step is uniquely identified by an ID.
 * 
 * @author Dr. Domagoj Cosic (cos@ivu.de)
 * @created 23. Mai 2001
 */
public interface Step extends Map<String, Object>, Serializable {
  /**
   * Description of the Field
   */
  public final static String DEFAULT_PARAMETER_NAME = "stepId"; //$NON-NLS-1$
  /**
   * Description of the Field
   */
  public final static String DEFAULT_ERROR_KEY = "errorMap"; //$NON-NLS-1$

  /**
   * Gets the id attribute of the Step object
   * 
   * @return The id value
   */
  public String getId();

  /**
   * convenience methods
   * 
   * @param key Description of Parameter
   * @return The int value
   */
  public int getInt(String key);

  /**
   * Description of the Method
   * 
   * @param key Description of Parameter
   * @param val Description of Parameter
   */
  public void putInt(String key, int val);

  /**
   * Gets the boolean attribute of the Step object
   * 
   * @param key Description of Parameter
   * @return The boolean value
   */
  public boolean getBoolean(String key);

  /**
   * Description of the Method
   * 
   * @param key Description of Parameter
   * @param val Description of Parameter
   */
  public void putBoolean(String key, boolean val);

  /**
   * Gets the string attribute of the Step object
   * 
   * @param key Description of Parameter
   * @return The string value
   */
  public String getString(String key);

  /**
   * Description of the Method
   * 
   * @param key Description of Parameter
   * @param val Description of Parameter
   */
  public void putString(String key, String val);

}
