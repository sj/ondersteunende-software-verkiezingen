package de.ivu.wahl.client.jsptag;

import static de.ivu.ejb.EJBUtil.lookupLocal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import de.ivu.wahl.SystemInfo;
import de.ivu.wahl.admin.PropertyHandling;
import de.ivu.wahl.admin.PropertyHandlingBean;
import de.ivu.wahl.client.util.ClientHelper;
import de.ivu.wahl.util.BundleHelper;

/**
 * Returns the Capitel of the HelpManual atached to the comitted key
 * 
 * @author
 * @version
 */

public class Help extends TagSupport {
  private static final long serialVersionUID = -9011268726181429852L;

  /**
   * Logger für diese Klasse
   */
  private final static Logger LOGGER = Logger.getLogger(Help.class);

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
   * <a href='/help/P5/index.html' style='text-decoration:none' target='help'> <span
   * class='linkdklrot'> <img src='/img/icon/help.gif' width='24' height='9' alt='' border='0'
   * /><ivu:int key='Hilfe'/> </span> </a>
   * 
   * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
   */
  @Override
  public int doStartTag() {
    try {
      String contextPath = ""; // pageContext.getServletContext().getContextPath();
      HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
      HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

      String htmlBind = getProperties().getProperty(_programmode + '_' + _key);
      String title = ""; //$NON-NLS-1$
      if (htmlBind != null && !htmlBind.isEmpty()) {
        htmlBind = '?' + htmlBind;
      } else {
        htmlBind = ""; //$NON-NLS-1$
        title = _key;
      }
      StringBuilder tmp = new StringBuilder();
      //      String helpURL = ClientHelper.rewriteURL("/help/" + _programmode + "/index.html" + htmlBind, //$NON-NLS-1$ //$NON-NLS-2$
      // request,
      // response);
      String helpURL = contextPath + "/help/" + _programmode + "/index.html" + htmlBind; //$NON-NLS-1$ //$NON-NLS-2$

      String infoURL = ClientHelper.rewriteURL("/osv/wahl/Info", request, response); //$NON-NLS-1$

      tmp.append("<a href='" + helpURL //$NON-NLS-1$
          + "' style='text-decoration:none' target='help' title='" + title + "'>"); //$NON-NLS-1$ //$NON-NLS-2$
      tmp.append("<span class='linkdklrot'>"); //$NON-NLS-1$
      tmp.append("<img src='" + contextPath + "/img/icon/help.gif' width='24'"); //$NON-NLS-1$ //$NON-NLS-2$
      tmp.append("height='9' alt='' border='0' />"); //$NON-NLS-1$
      tmp.append(__hilfeI18N);
      tmp.append("</span></a>"); //$NON-NLS-1$
      tmp.append("<a href='" + infoURL //$NON-NLS-1$ 
          + "' style='text-decoration:none' target='info' title='" + title + "'>"); //$NON-NLS-1$ //$NON-NLS-2$
      tmp.append("<span class='linkdklrot'>"); //$NON-NLS-1$
      tmp.append("<img src='" + contextPath + "/img/icon/Info.gif' width='24'"); //$NON-NLS-1$ //$NON-NLS-2$
      tmp.append("height='9' alt='' border='0' />"); //$NON-NLS-1$
      tmp.append(__infoI18N);
      tmp.append("</span></a>"); //$NON-NLS-1$

      pageContext.getOut().print(tmp.toString());
    } catch (Exception ex) {
      LOGGER.error("unexpected error: ", ex); //$NON-NLS-1$
    }
    return EVAL_BODY_INCLUDE;
  }

  static final PropertyHandling __propHandling = lookupLocal(PropertyHandlingBean.class
      .getSimpleName());
  static final String __hilfeI18N = BundleHelper.getBundleString("Hilfe"); //$NON-NLS-1$
  static final String __infoI18N = BundleHelper.getBundleString("Info"); //$NON-NLS-1$

  static Properties __prop = null;

  private synchronized Properties getProperties() {
    if (__prop == null) {
      final String name = "/htmlHelp.properties"; //$NON-NLS-1$
      final InputStream is = getClass().getResourceAsStream(name);
      __prop = new Properties();
      try {
        if (is != null) {
          __prop.load(is);
          is.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return __prop;

  }

  final String _programmode = SystemInfo.getSystemInfo().getModusklartext();

}
