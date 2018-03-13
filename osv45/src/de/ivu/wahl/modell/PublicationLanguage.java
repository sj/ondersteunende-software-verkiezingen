package de.ivu.wahl.modell;

/**
 * Corresponds to the values of the XML attributes PublicationLanguage in kr:ListData and rg:Gender.
 * 
 * @author Joachim Nottebaum
 */
public enum PublicationLanguage {
  NL("nl", "Nederlands"), //$NON-NLS-1$ //$NON-NLS-2$

  FY("fy", "Fries"); //$NON-NLS-1$ //$NON-NLS-2$

  private final String abbreviation;
  private final String description;

  private PublicationLanguage(String abbreviation, String description) {
    this.abbreviation = abbreviation;
    this.description = description;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public String getDescription() {
    return description;
  }

  public static PublicationLanguage fromAbbreviation(String abbreviation) {
    for (PublicationLanguage ePublicationLanguage : values()) {
      if (ePublicationLanguage.getAbbreviation().equals(abbreviation)) {
        return ePublicationLanguage;
      }
    }

    return NL; // The default value
  }

  public static PublicationLanguage fromDescription(String description) {
    for (PublicationLanguage language : values()) {
      if (language.getDescription().equals(description)) {
        return language;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return getDescription();
  }

  public static String abbreviationOf(PublicationLanguage publicationLanguage) {
    return publicationLanguage == null ? null : publicationLanguage.getAbbreviation();
  }

  public static String toValidAbbreviation(String publicationLanguage) {
    return fromAbbreviation(publicationLanguage).abbreviation;
  }
}
