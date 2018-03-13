/*
 * PersonendatenKonstanten
 * 
 * Created on 19.12.2008
 * Copyright (c) 2008 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell;

import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.util.BundleHelper;

/**
 * @author ugo@ivu.de, IVU Traffic Technologies AG

 */
public interface PersonendatenKonstanten {

  public static enum Geschlecht {
    WEIBLICH(0, "female", "female"), //$NON-NLS-1$ //$NON-NLS-2$

    WEIBLICH_FRIESISCH(1, "unknown", "female"), //$NON-NLS-1$ //$NON-NLS-2$

    MAENNLICH(2, "male", "male"), //$NON-NLS-1$ //$NON-NLS-2$

    KEINE_ANGABE(-1, "", ""); //$NON-NLS-1$ //$NON-NLS-2$

    private static final String UNKNOWN_GENDER = Messages
        .getString(MessageKeys.Msg_UnbekanntesGeschlecht);

    public String name;
    public String nameEML;
    private final String exportEML;
    public int id;

    @SuppressWarnings("hiding")
    Geschlecht(int id, String nameEML, String exportEML) {
      this.id = id;
      this.name = BundleHelper.getBundleString("PersonendatenKonstanten." + this.name()); //$NON-NLS-1$
      this.nameEML = nameEML;
      this.exportEML = exportEML;
    }

    public static int getId(String nameEML) {
      for (Geschlecht geschlecht : values()) {
        if (geschlecht.nameEML.equals(nameEML)) {
          return geschlecht.id;
        }
      }
      throw new RuntimeException(UNKNOWN_GENDER + nameEML);
    }

    public static String getName(int id, String publicationLanguage) {
      return forDisplay(id, publicationLanguage).name;
    }

    public static Geschlecht forDisplay(int id, String publicationLanguage) {
      for (Geschlecht geschlecht : values()) {
        if (geschlecht.id == id) {
          if (geschlecht.isWeiblich()) {
            // Weibliches Geschlecht muss noch gemäß PublicationLanguage konvertiert werden
            if (PublicationLanguage.fromAbbreviation(publicationLanguage)
                .equals(PublicationLanguage.FY)) {
              return WEIBLICH_FRIESISCH;
            } else {
              return WEIBLICH;
            }
          } else {
            return geschlecht;
          }
        }
      }
      throw new RuntimeException(UNKNOWN_GENDER + id);
    }

    private boolean isWeiblich() {
      return this.equals(WEIBLICH) || this.equals(WEIBLICH_FRIESISCH);
    }

    /**
     * @return my String representation if exported in an EML file
     */
    public String getExportEML() {
      return exportEML;
    }

    public static String getNameEML(int id) {
      for (Geschlecht geschlecht : values()) {
        if (geschlecht.id == id) {
          return geschlecht.nameEML;
        }
      }
      throw new RuntimeException(UNKNOWN_GENDER + id);
    }

    /**
     * @param id
     * @return the EML representation of the Gender with the given id as used in export.
     */
    public static String getExportEML(int id) {
      for (Geschlecht geschlecht : values()) {
        if (geschlecht.id == id) {
          return geschlecht.exportEML;
        }
      }
      throw new RuntimeException(UNKNOWN_GENDER + id);
    }
  }
}
