package de.ivu.wahl.client.jsptag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import de.ivu.wahl.client.util.ClientHelper;

/**
 * Erweitertes HTML-A-Tag, der selbständig für die Ziel-URL das URL-Rewriting im ClientHelper
 * aufruft. Außerdem werden sinnvolle Vorgaben gesetzt.
 * 
 * @author Dr. Domagoj Cosic <cos@ivu.de>
 */

public class A extends TagSupport {
  private static final long serialVersionUID = 4535084324725749179L;

  /**
   * Logger für diese Klasse
   */
  private final static Logger LOGGER = Logger.getLogger(A.class);

  /**
   * URL des Hyperlink-Ziels
   */
  protected String _href;

  /**
   * Name des Ankers
   */
  protected String _name;

  /**
   * Ziel-Fenster im Browser, Voreinstellung definiert
   */
  protected String _target = "_top"; //$NON-NLS-1$

  /**
   * CSS-Klasse
   */
  protected String _clazz;

  /**
   * MIME-Typ des Hyperlink-Ziels
   */
  protected String _type;

  /**
   * Tooltip
   */
  protected String _title;

  /**
   * Encoding für das Hyperlink-Ziel
   */
  protected String _enctype;

  /**
   * Style für das Hyperlink-Ziel
   */
  protected String _style;

  /**
   * javascript-event für das Hyperlink-Ziel
   */
  protected String _onclick;

  /**
   * Setze Tooltip-Inhalt
   * 
   * @param value Tooltip-Inhalt
   */
  public void setTitle(String value) {
    _title = value;
  }

  /**
   * Setze URL des Hyperlink-Ziels
   * 
   * @param value URL des Hyperlink-Ziels
   */
  public void setHref(String value) {
    _href = value;
  }

  /**
   * Setze den Namen des Ankers
   * 
   * @param value Name des Ankers
   */
  public void setName(String value) {
    _name = value;
  }

  /**
   * Setze Ziel-Fenster im Browser.
   * 
   * @param value Ziel-Fenster im Browser
   */
  public void setTarget(String value) {
    _target = value;
  }

  /**
   * Setze CSS-Klasse.
   * 
   * @param value CSS-Klasse
   */
  public void setClazz(String value) {
    _clazz = value;
  }

  /**
   * Setze MIME-Typ des Hyperlink-Ziels.
   * 
   * @param value MIME-Typ des Hyperlink-Ziels
   */
  public void setType(String value) {
    _type = value;
  }

  /**
   * Setze Encoding für das Hyperlink-Ziel
   * 
   * @param value Encoding für das Hyperlink-Ziel
   */
  public void setEnctype(String value) {
    _enctype = value;
  }

  /**
   * Setze Style für das Hyperlink-Ziel
   * 
   * @param value Style für das Hyperlink-Ziel
   */
  public void setStyle(String value) {
    _style = value;
  }

  /**
   * Setze javascript-event onclick für das Hyperlink-Ziel
   * 
   * @param onclick Style für das Hyperlink-Ziel
   */
  public void setOnclick(String onclick) {
    _onclick = onclick;
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
      HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
      HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
      StringBuilder tmp = new StringBuilder(80);

      tmp.append("<a href='"); //$NON-NLS-1$
      tmp.append(ClientHelper.rewriteURL(_href, request, response));
      tmp.append("'"); //$NON-NLS-1$
      if (_target.length() > 0) {
        tmp.append(" target='").append(_target).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      if (_name != null) {
        tmp.append(" name='").append(_name).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      if (_clazz != null) {
        tmp.append(" class='").append(_clazz).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      if (_type != null) {
        tmp.append(" type='").append(_type).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      if (_title != null) {
        tmp.append(" title='").append(_title).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      if (_enctype != null) {
        tmp.append(" enctype ='").append(_enctype).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      if (_style != null) {
        tmp.append(" style ='").append(_style).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      if (getId() != null) {
        tmp.append(" id ='").append(getId()).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      tmp.append(">"); //$NON-NLS-1$
      LOGGER.debug("Writing tag: " + tmp.toString()); //$NON-NLS-1$
      pageContext.getOut().print(tmp);
    } catch (Exception ex) {
      LOGGER.error("unexpected error: ", ex); //$NON-NLS-1$
    }
    return EVAL_BODY_INCLUDE;
  }

  /**
   * End-Tag interpretieren. Schließt nur den A-Tag ab.
   * 
   * @return EVAL_PAGE = setze die Seitenverarbeitung fort
   * @see javax.servlet.jsp.tagext.Tag#doEndTag()
   */
  @Override
  public int doEndTag() {
    try {
      pageContext.getOut().print("</a>"); //$NON-NLS-1$
    } catch (Exception ex) {
      LOGGER.error("unexpected error: ", ex); //$NON-NLS-1$
    }
    return EVAL_PAGE;
  }
}