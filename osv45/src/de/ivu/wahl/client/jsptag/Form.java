package de.ivu.wahl.client.jsptag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import de.ivu.wahl.Konstanten;
import de.ivu.wahl.client.util.ClientHelper;

/**
 * Erweitertes HTML-FORM-Tag, der selbständig für die Action-URL das URL-Rewriting im ClientHelper
 * aufruft. Außerdem werden sinnvolle Vorgaben gesetzt.
 * 
 * @author Dr. Domagoj Cosic <cos@ivu.de>
 */

public class Form extends TagSupport {
  private static final long serialVersionUID = -2634284041938856263L;

  /**
   * Logger für diese Klasse
   */
  private final static Logger LOGGER = Logger.getLogger(Form.class);

  /**
   * URL der Action
   */
  protected String action;

  /**
   * Name des Formulars
   */
  protected String name;

  /**
   * Ziel-Fenster im Browser, Voreinstellung definiert
   */
  protected String target = "_top"; //$NON-NLS-1$

  /**
   * HTTP-Request-Methode, Voreinstellung definiert
   */
  protected String method = "post"; //$NON-NLS-1$

  /**
   * Encoding der mit dem HTTP-Request gesendeten Daten, Voreinstellung definiert
   */
  protected String enctype = "application/x-www-form-urlencoded"; //$NON-NLS-1$

  /** Character set that the server accepts for POST data */
  protected String acceptcharset = Konstanten.ENCODING;

  /**
   * JavaScript auf dem Submit-Button
   */
  protected String onsubmit = ""; //$NON-NLS-1$

  /**
   * Class-Param auf dem Submit-Button
   */
  protected String _clazz = ""; //$NON-NLS-1$

  /**
   * style-Param auf dem Submit-Button
   */
  protected String _style = ""; //$NON-NLS-1$

  /**
   * autocomplete-Param auf dem Submit-Button
   */
  protected String _autocomplete = ""; //$NON-NLS-1$

  /**
   * Setze URL der Action.
   * 
   * @param value URL der Action
   */
  public void setAction(String value) {
    action = value;
  }

  /**
   * Setze Name des Formulars.
   * 
   * @param value Name des Formulars
   */
  public void setName(String value) {
    name = value;
  }

  /**
   * Setze Ziel-Fenster im Browser.
   * 
   * @param value Ziel-Fenster im Browser
   */
  public void setTarget(String value) {
    target = value;
  }

  /**
   * Setze HTTP-Request-Methode.
   * 
   * @param value HTTP-Request-Methode
   */
  public void setMethod(String value) {
    method = value;
  }

  /**
   * Setze Encoding der mit dem HTTP-Request gesendeten Daten.
   * 
   * @param value Encoding der mit dem HTTP-Request gesendeten Daten
   */
  public void setEnctype(String value) {
    enctype = value;
  }

  /**
   * Setzt den neuen Wert für accept_charset auf accept_charset.
   * 
   * @param value neuer Wert für accept_charset
   */
  public void setAcceptcharset(String value) {
    this.acceptcharset = value;
  }

  /**
   * Setze JavaScript auf dem Submit-Button.
   * 
   * @param value JavaScript auf dem Submit-Button
   */
  public void setOnsubmit(String value) {
    onsubmit = value;
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
   * Setze Style.
   * 
   * @param value style
   */
  public void setStyle(String value) {
    _style = value;
  }

  /**
   * Setze autocomplete.
   * 
   * @param value autocomplete
   */
  public void setAutocomplete(String value) {
    _autocomplete = value;
  }

  /**
   * Start-Tag interpretieren. Die Parameter werden von der JSP-Engine interpretiert und in der
   * Instanz gesetzt. Hier werden sie dann innerhalb eines normalen FORM-Tags wieder ausgegeben.
   * 
   * @return EVAL_BODY_INCLUDE = verarbeite den Inhalt des Elements
   * @see javax.servlet.jsp.tagext.Tag#doStartTag()
   */
  @Override
  public int doStartTag() {
    try {
      HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
      HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
      JspWriter out = pageContext.getOut();
      StringBuilder tmp = new StringBuilder(80);

      tmp.append("<form action='"); //$NON-NLS-1$
      tmp.append(ClientHelper.rewriteURL(action, request, response));
      tmp.append('\'');
      if (target.length() > 0) {
        tmp.append(" target='"); //$NON-NLS-1$
        tmp.append(target);
        tmp.append('\'');
      }
      if (name != null) {
        tmp.append(" name='"); //$NON-NLS-1$
        tmp.append(name);
        tmp.append('\'');
      }
      if (method.length() > 0) {
        tmp.append(" method='"); //$NON-NLS-1$
        tmp.append(method);
        tmp.append('\'');
      }
      if (enctype.length() > 0) {
        tmp.append(" enctype='"); //$NON-NLS-1$
        tmp.append(enctype);
        tmp.append('\'');
      }
      if (acceptcharset.length() > 0) {
        tmp.append(" accept-charset='"); //$NON-NLS-1$
        tmp.append(acceptcharset);
        tmp.append('\'');
      }
      if (onsubmit != null && onsubmit.length() > 0) {
        tmp.append(" onsubmit='"); //$NON-NLS-1$
        tmp.append(onsubmit);
        tmp.append('\'');
      }
      if (_clazz != null && _clazz.length() > 0) {
        tmp.append(" class='").append(_clazz).append('\''); //$NON-NLS-1$
      }
      if (_style != null && _style.length() > 0) {
        tmp.append(" style='").append(_style).append('\''); //$NON-NLS-1$
      }
      if (_autocomplete != null && _autocomplete.length() > 0) {
        tmp.append(" autocomplete='").append(_autocomplete).append('\''); //$NON-NLS-1$
      }
      tmp.append(">"); //$NON-NLS-1$
      LOGGER.debug("Writing tag: " + tmp.toString()); //$NON-NLS-1$
      out.print(tmp.toString());
    } catch (Exception ex) {
      LOGGER.error("unexpected error: ", ex); //$NON-NLS-1$
    }
    return EVAL_BODY_INCLUDE;
  }

  /**
   * End-Tag interpretieren. Schließt nur den FORM-Tag ab.
   * 
   * @return EVAL_PAGE = setze die Seitenverarbeitung fort
   * @see javax.servlet.jsp.tagext.Tag#doEndTag()
   */
  @Override
  public int doEndTag() {
    try {
      JspWriter out = pageContext.getOut();
      out.print("</form>"); //$NON-NLS-1$
    } catch (Exception ex) {
      LOGGER.error("unexpected error: ", ex); //$NON-NLS-1$
    }
    return EVAL_PAGE;
  }
}