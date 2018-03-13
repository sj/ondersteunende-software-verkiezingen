package de.ivu.wahl.client.jsptag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import de.ivu.wahl.client.util.ClientHelper;

/**
 * Erweitertes HTML-FRAME-Tag, der selbständig für die Source-URL das URL-Rewriting im ClientHelper
 * aufruft. Außerdem werden sinnvolle Vorgaben gesetzt.
 * 
 * @author Dr. Domagoj Cosic <cos@ivu.de>
 */

public class Frame extends TagSupport {
  private static final long serialVersionUID = -8924418570276400966L;

  /**
   * Logger für diese Klasse
   */
  private final static Logger LOGGER = Logger.getLogger(Frame.class);

  /**
   * URL für das Frame
   */
  protected String src;

  /**
   * Name des Frames
   */
  protected String name;

  /**
   * Randbreite
   */
  protected String marginwidth = "0"; //$NON-NLS-1$

  /**
   * Randhöhe
   */
  protected String marginheight = "0"; //$NON-NLS-1$

  /**
   * Keine Größenänderung durch den Benutzer, Voreinstellung definiert
   */
  protected boolean noresize = true;

  /**
   * Rahmen des Frames an/aus (IE 3D-Rahmen)
   */
  protected String frameborder = "0"; //$NON-NLS-1$

  /**
   * Abstand zu anderen Frames (IE)
   */
  protected String framespacing = "0"; //$NON-NLS-1$

  /**
   * Abstand zu anderen Frames (Netscape)
   */
  protected String border = "0"; //$NON-NLS-1$

  /**
   * Scrolling an/aus
   */
  protected String scrolling;

  /**
   * Setze URL für das Frame.
   * 
   * @param value URL für das Frame
   */
  public void setSrc(String value) {
    src = value;
  }

  /**
   * Setze Name des Frames.
   * 
   * @param value Name des Frames
   */
  public void setName(String value) {
    name = value;
  }

  /**
   * Setze Randbreite.
   * 
   * @param value Randbreite
   */
  public void setMarginwidth(String value) {
    marginwidth = value;
  }

  /**
   * Setze Randhöhe.
   * 
   * @param value Randhöhe
   */
  public void setMarginheight(String value) {
    marginheight = value;
  }

  /**
   * Setze Verbot Größenänderung durch den Benutzer, Voreinstellung definiert.
   * 
   * @param value Verbot Größenänderung durch den Benutzer, Voreinstellung definiert
   */
  public void setNoresize(String value) {
    noresize = Boolean.valueOf(value);
  }

  /**
   * Setze Rahmen des Frames an/aus (IE 3D-Rahmen).
   * 
   * @param value Rahmen des Frames an/aus (IE 3D-Rahmen)
   */
  public void setFrameborder(String value) {
    frameborder = value;
  }

  /**
   * Setze Abstand zu anderen Frames (IE).
   * 
   * @param value Abstand zu anderen Frames (IE)
   */
  public void setFramespacing(String value) {
    framespacing = value;
  }

  /**
   * Setze Abstand zu anderen Frames (Netscape).
   * 
   * @param value Abstand zu anderen Frames (Netscape)
   */
  public void setBorder(String value) {
    border = value;
  }

  /**
   * Setze Scrolling an/aus.
   * 
   * @param value Scrolling an/aus
   */
  public void setScrolling(String value) {
    scrolling = value;
  }

  /**
   * Start-Tag interpretieren. Die Parameter werden von der JSP-Engine interpretiert und in der
   * Instanz gesetzt. Hier werden sie dann innerhalb eines normalen FRAME-Tags wieder ausgegeben.
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
      StringBuffer tmp = new StringBuffer(80);

      tmp.append("<frame src='"); //$NON-NLS-1$
      tmp.append(ClientHelper.rewriteURL(src, request, response));
      tmp.append('\'');
      if (name.length() > 0) {
        tmp.append(" name='"); //$NON-NLS-1$
        tmp.append(name);
        tmp.append('\'');
      }
      if (marginwidth.length() > 0) {
        tmp.append(" marginwidth='"); //$NON-NLS-1$
        tmp.append(marginwidth);
        tmp.append('\'');
      }
      if (marginheight.length() > 0) {
        tmp.append(" marginheight='"); //$NON-NLS-1$
        tmp.append(marginheight);
        tmp.append('\'');
      }
      if (noresize) {
        tmp.append(" noresize"); //$NON-NLS-1$
      }
      if (frameborder.length() > 0) {
        tmp.append(" frameborder='"); //$NON-NLS-1$
        tmp.append(frameborder);
        tmp.append('\'');
      }
      if (framespacing.length() > 0) {
        tmp.append(" framespacing='"); //$NON-NLS-1$
        tmp.append(framespacing);
        tmp.append('\'');
      }
      if (border.length() > 0) {
        tmp.append(" border='"); //$NON-NLS-1$
        tmp.append(border);
        tmp.append('\'');
      }
      if (scrolling != null) {
        tmp.append(" scrolling='"); //$NON-NLS-1$
        tmp.append(scrolling);
        tmp.append('\'');
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
   * End-Tag interpretieren. Schließt nur den FRAME-Tag ab.
   * 
   * @return EVAL_PAGE = setze die Seitenverarbeitung fort
   * @see javax.servlet.jsp.tagext.Tag#doEndTag()
   */
  @Override
  public int doEndTag() {
    if (!"text/html".equals(pageContext.getResponse().getContentType())) { //$NON-NLS-1$
      try {
        JspWriter out = pageContext.getOut();
        out.print("</frame>"); //$NON-NLS-1$
      } catch (Exception ex) {
        LOGGER.error("unexpected error: ", ex); //$NON-NLS-1$
      }
    }
    return EVAL_PAGE;
  }
}