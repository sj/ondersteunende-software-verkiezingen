package de.ivu.wahl.client.jsptag;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import de.ivu.wahl.util.BundleHelper;

/**
 * Liefert den übergebenen Schlüssel als übersetzten Wert aus der Internationalisierungstabelle
 * 
 * @author
 * @version
 */

public class Internationalisation extends TagSupport {
  /** long */
  private static final long serialVersionUID = 1L;

  /**
   * Logger für diese Klasse
   */
  private final static Logger LOGGER = Logger.getLogger(A.class);

  /**
   * Schluessel
   */
  protected String _key;

  /**
   * Setze Schluessel
   * 
   * @param value key
   */
  public void setKey(String value) {
    _key = value;
  }

  /**
   * Start-Tag interpretieren. Die Parameter werden von der JSP-Engine interpretiert und in der
   * Instanz gesetzt. Hier werden sie dann innerhalb eines normalen A-Tags wieder ausgegeben.
   * 
   * @return EVAL_BODY_INCLUDE = verarbeite den Inhalt des Elements
   * @see javax.servlet.jsp.tagext.Tag#doStartTag()
   */
  @Override
  public int doStartTag() {
    try {
      pageContext.getOut().print(BundleHelper.getBundleString(_key));
    } catch (Exception ex) {
      LOGGER.error("unexpected error: ", ex); //$NON-NLS-1$
    }
    return EVAL_BODY_INCLUDE;
  }

}