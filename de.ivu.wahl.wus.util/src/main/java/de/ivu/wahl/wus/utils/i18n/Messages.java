package de.ivu.wahl.wus.utils.i18n;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

  private static final String BUNDLE_NAME = "de.ivu.wahl.wus.utils.i18n.messages"; //$NON-NLS-1$

  public static String widgetFactoryHelpTooltipCity;
  public static String widgetFactoryHelpTooltipCountry;

  public static String kiesraadUrlDialogTitle;
  public static String kiesraadUrlDialogMessage;
  public static String kiesraadUrlDialogLink;

  public static String fileDialogFilterNameAllFiles;
  public static String fileDialogFilterNameXML;
  public static String fileDialogFilterNameEML;
  public static String fileDialogFilterNameZIP;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
