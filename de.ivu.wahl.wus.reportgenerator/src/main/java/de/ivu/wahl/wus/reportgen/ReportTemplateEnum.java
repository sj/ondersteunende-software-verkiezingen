/*
 * ReportTemplateEnum
 * 
 * Created on 17.11.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

public enum ReportTemplateEnum {
  // Machine testing program
  MTP1("MTP 1", "Helpdesk overeenkomst P4"),

  MTP1_ON_MAC("MTP 1 mac", "Helpdesk overeenkomst P4"),

  // Program 0 - Election definition
  OSV0_1("osv 0-1", "Referendum vraag"),

  // Program 1
  H1("H 1", "Kandidatenlijst"),

  H3_1("H 3-1", "Machtiging plaatsen aanduiding"),

  H3_2("H 3-2", "Machtiging plaatsen samenvoeging"),

  H4("H 4", "Ondersteuningsverklaring"),

  OSV1_1("osv 1-1", "Verklaring van voorgenomen vestiging"),

  H9("H 9", "Instemmingsverklaring"),

  Y13("Y 13", "Verklaring niet kandidaatstelling in andere lidstaat"),

  Y35("Y 35", "Verklaring van kiesgerechtigdheid"),

  OSV1_2("osv 1-2", "Verklaringen H9 en Y13 van kandidaat"),

  // Program 2 - 3
  I1("I 1", "PV csb onderzoek kandidatenlijsten"),

  OSV2_1("osv 2-1", "Verzuimbrief"),

  OSV2_5("osv 2-5", "Overzicht kandidaatgegevens"), // CSV

  OSV2_6("osv 2-6", "Ontvangstbevestiging"),

  I4_O("I 4", "PV geldigheid kandidatenlijsten en lijstnummering-onvolledig"),

  I4("I 4", "PV geldigheid kandidatenlijsten en lijstnummering"),

  OSV3_2("osv 3-2", "Publicatie kandidatenlijsten"),

  OSV3_3("osv 3-3", "Hashcode Kandidatenlijsten"),

  OSV3_4("osv 3-4", "Overzicht kandidatenlijsten"),

  OSV3_5("osv 3-5", "Overzicht kandidaatgegevens"), // CSV

  OSV3_6("osv 3-6", "Hashcode Totaallijsten"),

  OSV3_7("osv 3-7", "Publicatie kandidatenlijsten"),

  OSV2_7("osv 2-7", "Bijlage PV geldigheid kandidatenlijsten en lijstnummering"),

  OSV3_9("osv 3-9", "Definitieve kandidatenlijsten"),

  // Program 4
  EML("EML", "EML"),

  N10_1("N 10-1", "PV stembureau uitkomst stemming-leeg"), // document without numbers of votes

  I("I", "PV stembureau uitkomst centrale stemopneming-leeg"),

  N11("N 11", "Vaststelling aantallen stemmen in gemeente"),

  N11_OL("N 11", "Vaststelling aantallen stemmen in openbaar lichaam"),

  II("II", "PV gemeentelijk stembureau uitkomst centrale stemopneming"),

  OSV4_7("osv 4-7", "Bijlage 1 PV gemeentelijk stembureau uitkomst centrale stemopneming"),

  OSV4_8("osv 4-8", "Bijlage 2 PV gemeentelijk stembureau uitkomst centrale stemopneming"),

  O3("O 3", "PV hsb uitkomst stemming"),

  T11("T 11", "PV uitkomst stemming provincie"),

  OSV4_1("osv 4-1", "Hashcode Telling", "Hashcode Totaaltelling"),

  OSV4_2("osv 4-2", "Aantallen stemmen referendum"),

  OSV4_3("osv 4-3", "Telling", "Totaaltelling"), // CSV

  OSV4_4("osv 4-4", "Bijlage PV hsb uitkomst stemming"),

  OSV4_5("osv 4-5", "Controlelijst"),

  Wrr83("Wrr 83", "PV csb vaststelling uitslag referendum"),

  OSV4_6("osv 4-6", "Bijlage PV csb uitslag referendum"),

  // Program 5
  P22_1("P 22-1", "PV csb vaststelling uitslag"),

  P22_2("P 22-2", "PV csb vaststelling uitslag"),

  U16("U 16", "PV csb vaststelling uitslag"),

  OSV5_1("osv 5-1", "Benoemingsbrief"),

  OSV5_2("osv 5-2", "Geloofsbrief (kennisgeving benoeming)"),

  OSV5_3("osv 5-3", "Bijlage PV csb vaststelling uitslag"),

  OSV5_5("osv 5-5", "Overzicht kandidaatgegevens"), // CSV

  OSV5_6("osv 5-6", "Adresgegevens kandidaten"); // CSV

  private final String abbreviation;
  private final String stylesheetPrefix;
  private final String description;
  /** Special description used when the file is created by the CSB */
  private final String descriptionCSB;

  private ReportTemplateEnum(String abbreviation, String description) {
    this(abbreviation, description, description);
  }

  /**
   * Constructor used for osv 4-1 and osv 4-3, where there is a special description if the file is
   * created by the CSB.
   */
  private ReportTemplateEnum(String abbreviation, String description, String descriptionCSB) {
    this.abbreviation = abbreviation;
    this.description = description;
    this.descriptionCSB = descriptionCSB;
    this.stylesheetPrefix = abbreviation.replace(" ", "");
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public String getDescription() {
    return description;
  }

  public String getDescriptionCSB() {
    return descriptionCSB;
  }

  public String getFilePrefix() {
    return abbreviation.replace(" ", "");
  }

  public String getStylesheetPrefix() {
    return stylesheetPrefix;
  }

  public boolean isIncomplete() {
    return I4_O.equals(this);
  }

}
