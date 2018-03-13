/*
 *  --------------------------------------------------------------
 *  Company   : IVU Traffic Technologies AG
 *  --------------------------------------------------------------
 */
package de.ivu.util.debug;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Category;
import org.apache.log4j.Priority;

/**
 * helper class for initialization of log4j. <BR>
 * for information about log4j see <A HREF="http://jakarta.apache.org/log4j/docs/index.html">
 * http://jakarta.apache.org/log4j/docs/index.html</A> <BR>
 * sample code:<BR>
 * 
 * <PRE>
 *  import org.apache.log4j.Category;
 *  import de.ivu.util.debug.Log4J;
 *  ...
 *  public class Sample {
 *    // this creates a logger for this class
 *    static Category log = Log4J.configure( Sample.class );
 *    // and dumps out the current version with priority info
 *    static {
 *       log.info( Log4J.dumpVersion( Sample.class, &quot;$Revision: 1.4 $&quot; ) );
 *    }
 * 
 *    void doSomething( void ) {
 *      log.debug(&quot;output&quot;);
 *      log.info(&quot;info output&quot;);
 *      try {
 *          ...
 *      }
 *      catch (Exception ex) {
 *          // dumps the exception
 *          log.error(&quot;oops: &quot;,ex);
 *      }
 *    }
 *  }
 * 
 *  In the main application class you should configure Log4J
 *  public class Application {
 * 
 *    Category log = null;
 *    // initialize and configure log4j, file name may be null
 *    // if filename is null, default initialization of log4j takes place
 *    // means a log4j.properties file in the classpath will be searched
 *    // if this fails, level DEBUG and stdout will be used
 *    public Application(...) {
 *      ... get configuration parameter
 *      log = Log4J.configure( Application.class, filename );
 *    }
 * 
 * 
 *  }
 * </PRE>
 * 
 * <H3>sample for :<tt>log4.properties</tt></h3>
 * 
 * <PRE>
 *  # uncomment this to enable periodically check (in seconds) of this property file
 *  # WatchInterval=10
 * 
 *  # uncomment this line with the passphrase to allow any DEBUG priority,
 *  # otherwise all debug will surpressed
 *  EnableDebug=IVU AG Berlin
 * 
 *  # Set root category priority to DEBUG and its only appender to A1.
 *  # this is the default configuration of the root category
 *  # all other categories will be inherit thes level from the root
 *  # the known priorities: DEBUG &lt; INFO &lt; WARN &lt; ERROR &lt; FATAL
 * 
 *  log4j.rootCategory=DEBUG, A1
 * 
 *  # this sets the log level for the package de.ivu.gis.components.map
 *  # to WARN and writes the output additional to a file (A2)
 *  log4j.category.de.ivu.gis.components=WARN, A2
 * 
 *  # A1 is set to be a ConsoleAppender.
 *  log4j.appender.A1=org.apache.log4j.ConsoleAppender
 *  # A1 uses PatternLayout.
 *  log4j.appender.A1.layout=org.apache.log4j.PatternLayout
 *  log4j.appender.A1.layout.ConversionPattern=%C{1}.%M(): %m%n
 *  #log4j.appender.A1.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} - %C{1}.%M(): %m%n
 * 
 *  # A2 is set to be a FileApender
 *  log4j.appender.A2=org.apache.log4j.FileAppender
 *  log4j.appender.A2.File=c:\example.log
 *  # A2 uses PatternLayout.
 *  log4j.appender.A2.layout=org.apache.log4j.PatternLayout
 *  log4j.appender.A2.layout.ConversionPattern=%p %t %c - %m%n
 * </PRE>
 * 
 * <BR>
 * The using of full qualified class names results in an practical and hierarchical structure for
 * categories.
 * 
 * @author KIE
 * @created 12. Juni 2001
 * @version $Revision: 1.4 $
 * @see <A href="http://jakarta.apache.org/log4j/docs/index.html">log4j project</A>
 */
public class Log4J {

  public static final int VERSION_12 = 0;
  private static final String[] __adapterClasses = {de.ivu.util.debug.Log4JAdapter12.class
      .getName()};

  /** name of property defines the watch intervall */
  static final String PROPERTY_WATCH_INTERVAL = "WatchInterval"; //$NON-NLS-1$

  private static final SimpleDateFormat TF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //$NON-NLS-1$

  /** Description of the Field */
  public final static String STRING_EMPTY = "<empty>"; //$NON-NLS-1$
  /** Description of the Field */
  public final static String STRING_NULL = "<null>"; //$NON-NLS-1$

  private static Log4JAdapter __adapter;

  static {
    int version = VERSION_12;

    try {
      __adapter = (Log4JAdapter) Class.forName(__adapterClasses[version]).newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates a logger using the full qualified class name of the given class as category and
   * configures the Log4J system if neccessarey.
   * 
   * @param c a class whose name is used for the categeory
   * @return a category / logger or null if any error occurs
   */
  public static Category configure(Class<?> c) {
    return configure(c, (URL) null);
  }

  /**
   * Creates a logger using the full qualified class name of the given class as category and
   * configures the Log4J system.
   * 
   * @param c a class whose name is used for the categeory
   * @param logConfig the full qualified name of the log4j property file
   * @return a category / logger or null if any error occurs
   */
  public static Category configure(Class<?> c, String logConfig) {
    if (logConfig == null) {
      return configure(c);
    }
    return configure(c, new File(logConfig));
  }

  /**
   * Creates a logger using the full qualified class name of the given class as category and
   * configures the Log4J system.
   * 
   * @param c a class whose name is used for the categeory
   * @param logConfig a File object pointing to log4j property file
   * @return a category / logger or null if any error occurs
   */
  public static Category configure(Class<?> c, File logConfig) {
    URL url = null;
    try {
      url = logConfig.toURI().toURL();
    } catch (Exception ex) {
      url = null;
    }
    return configure(c, url);
  }

  /**
   * Creates a logger using the full qualified class name of the given class as category and
   * configures the Log4J system.
   * 
   * @param c a class whose name is used for the categeory
   * @param logConfig a URL pointing to the log4j properties
   * @return a category / logger or null if any error occurs
   */
  public static Category configure(Class<?> c, URL logConfig) {
    return configure((c == null ? null : c.getName()), logConfig);
  }

  /**
   * Creates a logger using the full qualified class name of the given object as category and
   * configures the Log4J system if neccessary.
   * 
   * @param o a object whose class name is used as category name
   * @return a category / logger or null if any error occurs
   */
  public static Category configure(Object o) {
    return configure(o, (URL) null);
  }

  /**
   * Creates a logger using the full qualified class name of the given object as category and
   * configures the Log4J system.
   * 
   * @param o a object whose class name is used as category name
   * @param logConfig the full qualified name of the log4j property file
   * @return a category / logger or null if any error occurs
   */
  public static Category configure(Object o, String logConfig) {
    if (logConfig == null) {
      return configure(o);
    }
    return configure(o, new File(logConfig));
  }

  /**
   * Creates a logger using the full qualified class name of the given object as category and
   * configures the Log4J system.
   * 
   * @param o a object whose class name is used as category name
   * @param logConfig a File object pointing to log4j property file
   * @return a category / logger or null if any error occurs
   */
  public static Category configure(Object o, File logConfig) {
    URL url = null;
    try {
      url = logConfig.toURI().toURL();
    } catch (Exception ex) {
      url = null;
    }
    return configure(o, url);
  }

  /**
   * Creates a logger using the full qualified class name of the given object as category and
   * configures the Log4J system.
   * 
   * @param o a object whose class name is used as category name
   * @param logConfig URL pointing to the log4j properties
   * @return a category / logger or null if any error occurs
   */
  public static Category configure(Object o, URL logConfig) {
    return configure((o == null ? null : o.getClass().getName()), logConfig);
  }

  /**
   * Creates a logger using the given name as categeory and configures the Log4J system if
   * neccessarey.
   * 
   * @param category Description of the Parameter
   * @return a category / logger or null if any error occurs
   */
  public static Category configure(String category) {
    return configure(category, (URL) null);
  }

  /**
   * Creates a logger using the given name as categeory and configures the Log4J system.
   * 
   * @param category name of category
   * @param logConfig the full qualified name of the log4j property file
   * @return a category / logger or null if any error occurs
   */
  public static Category configure(String category, String logConfig) {
    if (logConfig == null) {
      return configure(category);
    }
    return configure(category, new File(logConfig));
  }

  /**
   * Creates a logger using the given name as categeory and configures the Log4J system.
   * 
   * @param category name of category
   * @param logConfig a File object pointing to log4j property file
   * @return a category / logger or null if any error occurs
   */
  public static Category configure(String category, File logConfig) {
    URL url = null;
    try {
      url = logConfig.toURI().toURL();
    } catch (Exception ex) {
      url = null;
    }
    return configure(category, url);
  }

  /**
   * Creates a logger using the given name as categeory and configures the Log4J system.<BR>
   * This implementation don't configure Log4J if any configuration allready done. This is a
   * workaround, to prevent multiple instance of the same appender. In future we will change this
   * behavior and supporting differential configuration.
   * 
   * @param category name of category
   * @param logConfig URL pointing to the log4j properties
   * @return a category / logger or null if any error occurs
   */
  public static Category configure(String category, URL logConfig) {
    // this method is used by all other configure() methods
    // check if any appender is configured, because there is no default appender in log4j
    // this is a workaround, to prevent multiple instance of the same appender

    // delegated to the particular Adapter implementation for the used log4j library
    if (__adapter != null) {
      try {
        return __adapter.configure(category, logConfig);
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Logging not possible: " + e); //$NON-NLS-1$
      }
    } else {
      return null;
    }
  }

  /**
   * Extracts the version/revision number from a String like "$Revision: 1.42"
   * 
   * @param version the revision as String like "$Revision: 1.42"
   * @return the revision or 0.0
   */
  public final static String extractVersion(String version) {
    if (version == null) {
      return "0.0"; //$NON-NLS-1$
    }
    String v = version.substring(10).replace('$', ' ').trim();
    if (v.length() == 0) {
      return "0.0"; //$NON-NLS-1$
    }
    return v;
  }

  /**
   * This method formats the vesion number in a unique way as 'v1.42 de.ivu.bla.Blub'.
   * 
   * @param c the class whose name is appended to the version
   * @param version the version number, also supports "$Revision: 1.4 $"
   * @return formated version
   */
  public final static String dumpVersion(Class<? extends Object> c, String version) {
    return dumpVersion((c == null ? null : c.getName()), version);
  }

  /**
   * This method formats the vesion number in a unique way as 'v1.42 de.ivu.bla.Blub'.
   * 
   * @param o the object whose class name is appended to the version
   * @param version the version number, also supports "$Revision: 1.4 $"
   * @return formated version
   */
  public final static String dumpVersion(Object o, String version) {
    return dumpVersion((o == null ? null : o.getClass()), version);
  }

  /**
   * This method formats the vesion number in a unique way as 'v1.42 de.ivu.bla.Blub'.
   * 
   * @param name the class name is appended to the version
   * @param pVersion the version number, also supports "$Revision: 1.4 $"
   * @return formated version
   */
  public final static String dumpVersion(String name, String pVersion) {
    if (pVersion == null) {
      return "v0.0 " + name; //$NON-NLS-1$
    }
    String version;
    if (pVersion.toLowerCase().indexOf("$revision") != -1) { //$NON-NLS-1$
      version = extractVersion(pVersion);
    } else {
      version = pVersion;
    }
    return "v" + version + " " + name; //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * Dumps each entry of a array on a single line.
   * 
   * @param array Description of the Parameter
   * @return the array dump as string
   */
  public static String dumpArray(Object[] array) {
    if (array == null) {
      return STRING_NULL;
    }
    if (array.length == 0) {
      return STRING_EMPTY;
    }
    StringBuilder buf = new StringBuilder();
    for (int i = 0; i < array.length; i++) {
      buf.append(array[i] == null ? STRING_NULL : array[i].toString());
      if (i < array.length) {
        buf.append('\n');
      }
    }
    return buf.toString();
  }

  /**
   * Dumps each entry of a array on a single line using the given logger and priority.
   * 
   * @param log category/logger to use
   * @param priority priority to use
   * @param array Description of the Parameter
   */
  public static void dumpArray(Category log, Priority priority, Object[] array) {
    dumpArray(log, priority, array, null);
  }

  /**
   * Dumps each entry of a array on a single line using the given logger and priority.
   * 
   * @param log category/logger to use
   * @param priority priority to use
   * @param array the array to dump
   * @param pPrefix a optional prefix, may be null
   */
  public static void dumpArray(Category log, Priority priority, Object[] array, String pPrefix) {
    if (!log.isEnabledFor(priority)) {
      return;
    }
    String prefix = pPrefix == null ? "" : pPrefix; //$NON-NLS-1$
    if (array == null) {
      log.log(priority, prefix + STRING_NULL);
      return;
    }
    if (array.length == 0) {
      log.log(priority, prefix + STRING_EMPTY);
      return;
    }
    for (int i = 0; i < array.length; i++) {
      log.log(priority, prefix + (array[i] == null ? STRING_NULL : array[i].toString()));
    }
  }

  /**
   * Dumps each entry of a map on a single line.
   * 
   * @param map Description of the Parameter
   * @return the map dump as string
   */
  public static String dumpMap(Map<?, ?> map) {
    if (map.isEmpty()) {
      return STRING_EMPTY;
    }
    StringBuffer buf = new StringBuffer();
    Iterator<?> it = map.keySet().iterator();
    while (it.hasNext()) {
      Object o = it.next();
      buf.append(o.toString());
      buf.append(" = "); //$NON-NLS-1$
      buf.append(map.get(o).toString());
      if (it.hasNext()) {
        buf.append('\n');
      }
    }
    return buf.toString();
  }

  /**
   * Dumps each entry of a map on a single line using the given logger and priority.
   * 
   * @param log category/logger to use
   * @param priority priority to use
   * @param map the map to dump
   */
  public static void dumpMap(Category log, Priority priority, Map<?, ?> map) {
    dumpMap(log, priority, map, null);
  }

  /**
   * Dumps each entry of a map on a single line using the given logger and priority.
   * 
   * @param log category/logger to use
   * @param priority priority to use
   * @param map the map to dump
   * @param pPrefix a optional prefix, may be null
   */
  public static void dumpMap(Category log, Priority priority, Map<?, ?> map, String pPrefix) {
    if (!log.isEnabledFor(priority)) {
      return;
    }
    String prefix = pPrefix == null ? "" : pPrefix; //$NON-NLS-1$
    if (map == null) {
      log.log(priority, prefix + STRING_NULL);
      return;
    }
    if (map.isEmpty()) {
      log.log(priority, prefix + STRING_EMPTY);
    } else {
      Iterator<?> it = map.keySet().iterator();
      while (it.hasNext()) {
        Object o = it.next();
        log.log(priority, prefix + o.toString() + " = " + map.get(o).toString()); //$NON-NLS-1$
      }
    }
  }

  /**
   * Dumps each entry of a collection on a single line.
   * 
   * @param col collection to dump
   * @return the collection dump as string
   */
  public static String dumpCollection(Collection<?> col) {
    if (col == null) {
      return STRING_NULL;
    }

    if (col.isEmpty()) {
      return STRING_EMPTY;
    }
    StringBuffer buf = new StringBuffer();
    Iterator<?> it = col.iterator();
    while (it.hasNext()) {
      Object o = it.next();
      buf.append(o.toString());
      if (it.hasNext()) {
        buf.append('\n');
      }
    }
    return buf.toString();
  }

  /**
   * Dumps each entry of a collection on a single line using the given logger and priority.
   * 
   * @param log category/logger to use
   * @param priority priority to use
   * @param col collection to dump
   */
  public static void dumpCollection(Category log, Priority priority, Collection<?> col) {
    dumpCollection(log, priority, col, null);
  }

  /**
   * Dumps each entry of a map on a single line using the given logger and priority.
   * 
   * @param log category/logger to use
   * @param priority priority to use
   * @param pPrefix a optional prefix, may be null
   * @param col collection to dump
   */
  public static void dumpCollection(Category log,
      Priority priority,
      Collection<?> col,
      String pPrefix) {

    if (!log.isEnabledFor(priority)) {
      return;
    }

    String prefix = pPrefix == null ? "" : pPrefix; //$NON-NLS-1$
    if (col == null) {
      log.log(priority, prefix + STRING_NULL);
      return;
    }
    if (col.isEmpty()) {
      log.log(priority, prefix + STRING_EMPTY);
    } else {
      Iterator<?> it = col.iterator();
      while (it.hasNext()) {
        Object obj = it.next();
        log.log(priority, prefix + (obj == null ? STRING_NULL : obj.toString()));
      }
    }
  }

  /**
   * Returns a dump of all private, protected and public members of a given object including those
   * of the first superclass using reflection.
   * 
   * @param obj object to dump
   * @return string with dump
   */
  public final static String dumpObject(Object obj) {
    StringBuffer buf = new StringBuffer("--- object dump ---\n"); //$NON-NLS-1$
    final String offset = "  "; //$NON-NLS-1$
    if (obj == null) {
      buf.append("no object given."); //$NON-NLS-1$
      return buf.toString();
    }
    Class<? extends Object> c = obj.getClass();
    Class<?> sc = c.getSuperclass();
    if (!sc.equals(Object.class)) {
      buf.append(offset);
      buf.append("supperclass: "); //$NON-NLS-1$
      buf.append(sc.getName());
      buf.append('\n');
      dumpObjectFields(buf, sc.getDeclaredFields(), obj);
    }
    buf.append(offset);
    buf.append("class: "); //$NON-NLS-1$
    buf.append(c.getName());
    buf.append('\n');
    dumpObjectFields(buf, c.getDeclaredFields(), obj);
    return buf.toString();
  }

  /**
   * @param buf Description of the Parameter
   * @param fields Description of the Parameter
   * @param obj Description of the Parameter
   */
  private final static void dumpObjectFields(StringBuffer buf, Field[] fields, Object obj) {
    final String offset = "    "; //$NON-NLS-1$
    try {
      AccessibleObject.setAccessible(fields, true);
    } catch (SecurityException e) {
      buf.append("Got a SecurityException"); //$NON-NLS-1$
    }
    for (int i = 0; i < fields.length; i++) {
      Field f = fields[i];
      if (f.getName().startsWith("class$")) { //$NON-NLS-1$
        continue;
      }
      buf.append(offset);
      buf.append("{"); //$NON-NLS-1$
      buf.append(f.getName());
      buf.append("} "); //$NON-NLS-1$
      try {
        Object val = f.get(obj);
        if (val == null) {
          buf.append(STRING_NULL);
          continue;
        }
        Class<? extends Object> fc = val.getClass();
        buf.append(val.toString());
        if (fc.isArray()) {
          buf.append(", length="); //$NON-NLS-1$
          int len = Array.getLength(val);
          buf.append(len);
          buf.append(", "); //$NON-NLS-1$
          int n = 0;
          for (n = 0; n < Math.min(3, len); n++) {
            buf.append('[');
            buf.append(Array.get(val, n).toString());
            buf.append(']');
          }
          if (n < len - 1) {
            buf.append("..."); //$NON-NLS-1$
          }
          if (n <= len - 1) {
            buf.append('[');
            buf.append(Array.get(val, len - 1).toString());
            buf.append(']');
          }
        }
      } catch (IllegalAccessException e) {
        buf.append("<unknown>"); //$NON-NLS-1$
      }
      if (i < fields.length - 1) {
        buf.append('\n');
      }
    }
  }

  /**
   * Returns a dump of all private, protected and public members of a given object including those
   * of the first superclass using reflection.
   * 
   * @param log category/logger to use
   * @param priority priority to use
   * @param obj object to dump
   */
  public static void dumpObject(Category log, Priority priority, Object obj) {
    dumpObject(log, priority, obj, null);
  }

  /**
   * Returns a dump of all private, protected and public members of a given object including those
   * of the first superclass using reflection.
   * 
   * @param log category/logger to use
   * @param priority priority to use
   * @param obj object to dump
   * @param pPrefix a optional prefix, may be null
   */
  public static void dumpObject(Category log, Priority priority, Object obj, String pPrefix) {
    if (!log.isEnabledFor(priority)) {
      return;
    }
    String prefix = pPrefix == null ? "" : pPrefix; //$NON-NLS-1$
    if (obj == null) {
      log.log(priority, STRING_NULL);
    } else {
      Class<? extends Object> c = obj.getClass();
      Class<?> sc = c.getSuperclass();
      if (!sc.equals(Object.class)) {
        log.log(priority, prefix + "supperclass: " + sc.getName()); //$NON-NLS-1$
        dumpObjectFields(log, priority, obj, prefix + " ", sc.getDeclaredFields()); //$NON-NLS-1$
      }
      log.log(priority, prefix + "class: " + c.getName()); //$NON-NLS-1$
      dumpObjectFields(log, priority, obj, prefix + " ", c.getDeclaredFields()); //$NON-NLS-1$
    }
  }

  /**
   * @param fields Description of the Parameter
   * @param obj Description of the Parameter
   * @param log Description of the Parameter
   * @param priority Description of the Parameter
   * @param prefix Description of the Parameter
   */
  private final static void dumpObjectFields(Category log,
      Priority priority,
      Object obj,
      String prefix,
      Field[] fields) {

    try {
      AccessibleObject.setAccessible(fields, true);
    } catch (SecurityException e) {
      log.log(priority, prefix + "got a SecurityException."); //$NON-NLS-1$
    }
    for (int i = 0; i < fields.length; i++) {
      Field f = fields[i];
      if (f.getName().startsWith("class$")) { //$NON-NLS-1$
        continue;
      }
      try {
        StringBuffer buf = new StringBuffer();
        buf.append(prefix);
        buf.append('{');
        buf.append(f.getName());
        buf.append("} "); //$NON-NLS-1$
        Object val = f.get(obj);
        if (val == null) {
          buf.append(STRING_NULL);
          log.log(priority, buf.toString());
          continue;
        }
        Class<? extends Object> fc = val.getClass();
        buf.append(val.toString());
        if (fc.isArray()) {
          buf.append(", length="); //$NON-NLS-1$
          int len = Array.getLength(val);
          buf.append(len);
          buf.append(", "); //$NON-NLS-1$
          int n = 0;
          for (n = 0; n < Math.min(3, len); n++) {
            buf.append('[');
            buf.append(Array.get(val, n).toString());
            buf.append(']');
          }
          if (n < len - 1) {
            buf.append("..."); //$NON-NLS-1$
          }
          if (n <= len - 1) {
            buf.append('[');
            buf.append(Array.get(val, len - 1).toString());
            buf.append(']');
          }
        }
        log.log(priority, buf.toString());
      } catch (IllegalAccessException e) {
        log.log(priority, prefix + "<unknown>"); //$NON-NLS-1$
      }
    }
  }

  /**
   * Returns the StackTrace of an Exception as String, including the message and class
   * 
   * @param t the exception
   * @return the stack trace
   */
  public final static String dumpException(Throwable t) {
    StringBuffer buf = new StringBuffer();
    buf.append("--- exception dump ---\n"); //$NON-NLS-1$
    if (t == null) {
      buf.append(STRING_NULL);
    } else {
      StringWriter out = new StringWriter();
      t.printStackTrace(new PrintWriter(out));
      buf.append(out.toString());
      out = null;
    }
    return buf.toString();
  }

  /**
   * Description of the Method
   * 
   * @param condition Description of the Parameter
   * @param message Description of the Parameter
   * @exception RuntimeException Description of the Exception
   */
  public final static void assertTrue(boolean condition, String message) throws RuntimeException {
    if (!condition) {
      throw new RuntimeException(message);
    }
  }

  /**
   * Return either the string representaion of object or STRING_NULL.
   * 
   * @param obj object to convert to string
   * @return obj.toString() or STRING_NULL
   */
  public static String fmtNull(Object obj) {
    return (obj == null ? STRING_NULL : obj.toString());
  }

  /**
   * Returns the value as hex string.
   * 
   * @param value Description
   * @return Description
   */
  public static String fmtHex(int value) {
    return "0x" + Integer.toHexString(value).toUpperCase(); //$NON-NLS-1$
  }

  /**
   * Returns the hash of a object as hex string.
   * 
   * @param obj Description
   * @return Description
   */
  public static String fmtHex(Object obj) {
    if (obj == null) {
      return "@" + STRING_NULL; //$NON-NLS-1$
    } else {
      return "@" + Integer.toHexString(obj.hashCode()).toUpperCase(); //$NON-NLS-1$
    }
  }

  /**
   * Returns the time value as "yyyy-MM-dd hh:mm:ss"
   * 
   * @param time time to format
   * @return yyyy-MM-dd hh:mm:ss
   */
  public static synchronized String fmtTime(long time) {
    return TF.format(new Date(time));
  }

  /**
   * Description of the Method
   * 
   * @return Description of the Return Value
   */
  public static String displayStack() {
    return displayStack("", 0); //$NON-NLS-1$
  }

  /**
   * Description of the Method
   * 
   * @param deep Description of the Parameter
   * @return Description of the Return Value
   */
  public static String displayStack(int deep) {
    return displayStack("", deep); //$NON-NLS-1$
  }

  /**
   * Description of the Method
   * 
   * @param msg Description of the Parameter
   * @return Description of the Return Value
   */
  public static String displayStack(String msg) {
    return displayStack(msg, 0);
  }

  /**
   * Description of the Method
   * 
   * @param msg a message to appear in the Trace
   * @param pDeep pDeep = 0 complete stack
   * @return stack trace
   */
  public static String displayStack(String msg, int pDeep) {
    // msg
    // deep = 0 complete stack
    // n from "n" levels
    StringBuilder buf = new StringBuilder(""); //$NON-NLS-1$
    StringWriter sw = new StringWriter();
    new Throwable("").printStackTrace(new PrintWriter(sw)); //$NON-NLS-1$
    String stackTrace = sw.toString();
    // to clean up the stacktrace
    StringTokenizer st = new StringTokenizer(stackTrace, "\n"); //$NON-NLS-1$
    // get ride of the first line
    String s = st.nextToken();
    // get ride of line(s) for the Trace class
    if (!msg.equals("")) { //$NON-NLS-1$
      buf.append(msg).append('\n');
    }
    s = st.nextToken();
    s = st.nextToken();
    if (s.indexOf("StackTrace.displayStack") < 0) { //$NON-NLS-1$
      buf.append(s).append('\n');
    }
    // process the stack
    if (pDeep == 0) {
      while (st.hasMoreTokens()) {
        buf.append(st.nextToken()).append('\n');
      }
    } else {
      int deep = pDeep;
      while (deep > 1) {
        if (st.hasMoreTokens()) {
          buf.append(st.nextToken()).append('\n');
        } else {
          deep = 0;
        }
      }
    }
    return buf.toString();
  }
}
