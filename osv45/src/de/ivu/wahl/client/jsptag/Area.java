package de.ivu.wahl.client.jsptag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import de.ivu.wahl.client.util.ClientHelper;

/**
 * Erweitertes HTML-AREA-Tag, der selbständig für die Ziel-URL das URL-Rewriting im ClientHelper
 * aufruft. Außerdem werden sinnvolle Vorgaben gesetzt.
 * 
 * @author Dr. Domagoj Cosic <cos@ivu.de>
 */
public class Area extends TagSupport {
  private static final long serialVersionUID = 276006408528394429L;

  /**
   * Logger für diese Klasse
   */
  private final static Logger LOGGER = Logger.getLogger(Area.class);

  /**
   * URL des Hyperlink-Ziels
   */
  protected String href;

  /**
   * Ziel-Fenster im Browser, Voreinstellung definiert
   */
  protected String target = "_top"; //$NON-NLS-1$

  /**
   * Umriss-Form der Clickable-Area, Voreinstellung definiert
   */
  protected String shape = "rect"; //$NON-NLS-1$

  /**
   * Koordinaten
   */
  protected String coords;

  /**
   * Alternative Beschriftung (Tooltip)
   */
  protected String alt;

  /**
   * JavaScript für Maus über der Area
   */
  protected String onmouseover;

  /**
   * JavaScript für Maus verlässt die Area
   */
  protected String onmouseout;

  /**
   * Setze URL des Hyperlink-Ziels.
   * 
   * @param value URL des Hyperlink-Ziels
   */
  public void setHref(String value) {
    href = value;
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
   * Setze Umriss-Form der Clickable-Area.
   * 
   * @param value Umriss-Form der Clickable-Area
   */
  public void setShape(String value) {
    shape = value;
  }

  /**
   * Setze Koordinaten.
   * 
   * @param value Koordinaten
   */
  public void setCoords(String value) {
    coords = value;
  }

  /**
   * Setze Alternative Beschriftung (Tooltip).
   * 
   * @param value Alternative Beschriftung (Tooltip)
   */
  public void setAlt(String value) {
    alt = value;
  }

  /**
   * Setze JavaScript für Maus über der Area.
   * 
   * @param value JavaScript für Maus über der Area
   */
  public void setOnmouseover(String value) {
    onmouseover = value;
  }

  /**
   * Setze JavaScript für Maus verlässt die Area.
   * 
   * @param value JavaScript für Maus verlässt die Area
   */
  public void setOnmouseout(String value) {
    onmouseout = value;
  }

  /**
   * Start-Tag interpretieren. Die Parameter werden von der JSP-Engine interpretiert und in der
   * Instanz gesetzt. Hier werden sie dann innerhalb eines normalen AREA-Tags wieder ausgegeben.
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

      tmp.append("<area href='"); //$NON-NLS-1$
      tmp.append(ClientHelper.rewriteURL(href, request, response));
      tmp.append("'"); //$NON-NLS-1$

      if (target.length() > 0) {
        tmp.append(" target='"); //$NON-NLS-1$
        tmp.append(target);
        tmp.append("'"); //$NON-NLS-1$
      }

      tmp.append(" shape='"); //$NON-NLS-1$
      tmp.append(shape);
      tmp.append("'"); //$NON-NLS-1$

      if (coords != null) {
        tmp.append(" coords='"); //$NON-NLS-1$
        tmp.append(coords);
        tmp.append("'"); //$NON-NLS-1$
      }

      if (alt != null) {
        tmp.append(" alt='"); //$NON-NLS-1$
        tmp.append(alt);
        tmp.append("'"); //$NON-NLS-1$
      }

      if (onmouseover != null) {
        tmp.append(" onmouseover='"); //$NON-NLS-1$
        tmp.append(onmouseover);
        tmp.append("'"); //$NON-NLS-1$
      }

      if (onmouseout != null) {
        tmp.append(" onmouseout='"); //$NON-NLS-1$
        tmp.append(onmouseout);
        tmp.append("'"); //$NON-NLS-1$
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
   * End-Tag interpretieren. Schließt nur den AREA-Tag ab.
   * 
   * @return EVAL_PAGE = setze die Seitenverarbeitung fort
   * @see javax.servlet.jsp.tagext.Tag#doEndTag()
   */
  @Override
  public int doEndTag() {
    if (!"text/html".equals(pageContext.getResponse().getContentType())) { //$NON-NLS-1$
      try {
        JspWriter out = pageContext.getOut();
        out.print("</area>"); //$NON-NLS-1$
      } catch (Exception ex) {
        LOGGER.error("unexpected error: ", ex); //$NON-NLS-1$
      }
    }
    return EVAL_PAGE;
  }
}
