package de.ivu.wahl.client.jsptag;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

/**
 * HTML-Tag, der selbständig den Value auf null überprüft und ggf durch einen Leerstring oder
 * Defaultwert ersetzt.
 * 
 * @author Michael Murdfield <mur@ivu.de>
 */

public class NullCheck extends TagSupport {
  private static final long serialVersionUID = 2249437336421732232L;

  /**
   * Logger für diese Klasse
   */
  private final static Logger LOGGER = Logger.getLogger(NullCheck.class);

  /**
   * Wert
   */
  protected String _value;

  /**
   * Defaultwert
   */
  protected String _default = ""; //$NON-NLS-1$

  /**
   * Start-Tag interpretieren. Die Parameter werden von der JSP-Engine interpretiert und in der
   * Instanz gesetzt.
   * 
   * @return EVAL_BODY_INCLUDE = verarbeite den Inhalt des Elements
   * @see javax.servlet.jsp.tagext.Tag#doStartTag()
   */
  @Override
  public int doStartTag() {
    try {
      pageContext.getOut().print(_value == null || "null".equals(_value) ? _default : _value); //$NON-NLS-1$
    } catch (Exception ex) {
      LOGGER.error("unexpected error: ", ex); //$NON-NLS-1$
    }
    return EVAL_BODY_INCLUDE;
  }

  public void setValue(String value) {
    _value = value;
  }

  public void setDefault(String default1) {
    _default = default1;
  }
}