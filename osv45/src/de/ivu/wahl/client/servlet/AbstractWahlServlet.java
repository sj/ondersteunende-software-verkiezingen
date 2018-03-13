package de.ivu.wahl.client.servlet;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;

/**
 * Description of the Class
 * 
 * @author cos
 * @created 31. August 2001
 */
public abstract class AbstractWahlServlet extends HttpServlet {
  private static final long serialVersionUID = 1098985475993701901L;

  /**
   * Cache for the result of the container type query
   */
  protected static boolean __servletContainerNewType = false;

  /**
   * Flag if the container type was already queried
   */
  protected static boolean __gotServletContainerType = false;

  /**
   * Logger for the particular servlet instance
   */
  protected Category _log;

  /**
   * Writes the logger for the current subclass into the field log
   */
  public void initLog() {
    _log = getLogger(this);
  }

  /**
   * Gets the logger for a particular subclass
   * 
   * @param instance instance of the subclass (this)
   * @return The logger
   */
  protected static Logger getLogger(Object instance) {
    return Logger.getLogger(instance.getClass());
  }

  /**
   * Globale Variablen initialisieren
   * 
   * @param config Description of Parameter
   * @exception ServletException Description of Exception
   */
  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    initLog();
  }

  /**
   * Gets a resource
   * 
   * @param resource Description of Parameter
   * @return The resource value
   * @exception ServletException Description of Exception
   */
  protected URL getResource(String resource) throws ServletException {
    if (resource == null) {
      String message = "Resource cannot be null"; //$NON-NLS-1$
      _log.error(message);
      throw new ServletException(message);
    }

    try {
      if (isServletContainerNewType()) {
        return getServletContext().getResource(resource);
      } else {
        String resourcePath = getServletContext().getRealPath("/") + resource; //$NON-NLS-1$
        return new URL("file", null, resourcePath); //$NON-NLS-1$
      }
    } catch (MalformedURLException mue) {
      String message = "Cannot get resource " + resource; //$NON-NLS-1$
      _log.error(message + mue.toString());
      throw new ServletException(message, mue);
    }
  }

  /**
   * Gets the servletContainerNewType attribute of the BLISServlet object
   * 
   * @return The servletContainerNewType value
   */
  protected boolean isServletContainerNewType() {
    if (!__gotServletContainerType) {
      int containerMajorVersion = getContainerMajorVersion();
      int containerMinorVersion = getContainerMinorVersion();

      __servletContainerNewType = containerMajorVersion >= 2 && containerMinorVersion >= 2
          || containerMajorVersion >= 3;
      __gotServletContainerType = true;
    }
    return __servletContainerNewType;
  }

  /**
   * This method tries to guess the container major version.
   * 
   * @return The containerMajorVersion value
   */
  private int getContainerMajorVersion() {
    int v;
    try {
      v = getServletConfig().getServletContext().getMajorVersion();
    } catch (NoSuchMethodError e) {
      v = 2; // using pre 2.1 servlet container
    }
    return v;
  }

  /**
   * This method tries to guess the container minor version.
   * 
   * @return The containerMinorVersion value
   */
  private int getContainerMinorVersion() {
    int v;
    try {
      v = getServletConfig().getServletContext().getMinorVersion();
    } catch (NoSuchMethodError e) {
      v = 0; // using pre 2.1 servlet container
    }
    return v;
  }
}
