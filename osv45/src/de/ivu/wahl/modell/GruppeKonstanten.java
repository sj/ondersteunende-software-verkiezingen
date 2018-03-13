/*
 * GruppeKonstanten
 * 
 * Created on 05.01.2009
 * Copyright (c) 2009-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell;

import static de.ivu.wahl.modell.GruppeKonstanten.StimmabgabeArt.BALLOT;
import static de.ivu.wahl.modell.GruppeKonstanten.StimmabgabeArt.BALLOT_AND_LETTER;
import static de.ivu.wahl.modell.GruppeKonstanten.StimmabgabeArt.LETTER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.util.BundleHelper;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public class GruppeKonstanten {
  /**
   * Kennung für die Art "Partei" einer Partei/Gruppe
   */
  public static final int GRUPPENART_PARTEI = 0;

  /**
   * Kennung für "Wähler", "Wahlbereichtigte" usw.
   */
  public static final int GRUPPENART_ALLGEMEIN = 1;

  /**
   * "Übersetzung" der Konstanten
   */
  public static final String[] GRUPPENART_KLARTEXT = {"Partei", "Allgemein"}; //$NON-NLS-1$ //$NON-NLS-2$

  public static List<Integer> KEYS_OF_SPECIAL_GROUPS;
  static {
    List<Integer> tmp = new ArrayList<Integer>();
    for (GruppeAllgemein gruppeAllgemein : GruppeAllgemein.values()) {
      tmp.add(Integer.valueOf(gruppeAllgemein.schluessel));
    }
    KEYS_OF_SPECIAL_GROUPS = Collections.unmodifiableList(tmp);
  }

  private static final String KEINE_KATEGORIE = ""; //$NON-NLS-1$
  private static final String KAT_VOTER_TYPE = "VOTER_TYPE"; //$NON-NLS-1$
  private static final String KAT_WAEHLER = "WAEHLER"; //$NON-NLS-1$
  private static final String KAT_EXPLANATIONS = "EXPLANATIONS"; //$NON-NLS-1$

  private static final boolean COLLAPSIBLE = true;
  private static final boolean NOT_COLLAPSIBLE = false;

  public static enum StimmabgabeArt {
    BALLOT, LETTER, BALLOT_AND_LETTER;
  }

  public static enum GruppeAllgemein {
    // The original general groups
    WAHLBERECHTIGTE(100, KEINE_KATEGORIE, BALLOT_AND_LETTER, NOT_COLLAPSIBLE),

    // Admitted voters
    ELECTION_NOTICES(700, KAT_VOTER_TYPE, BALLOT, NOT_COLLAPSIBLE),

    PROXY_VOTERS(800, KAT_VOTER_TYPE, BALLOT, NOT_COLLAPSIBLE),

    POLLING_CARDS(900, KAT_VOTER_TYPE, BALLOT, NOT_COLLAPSIBLE),

    ADMITTED_VOTERS(1000, KAT_VOTER_TYPE, BALLOT_AND_LETTER, NOT_COLLAPSIBLE),

    // The original general groups
    /** Valid votes to political groups */
    GUELTIGE(300, KAT_WAEHLER, BALLOT_AND_LETTER, NOT_COLLAPSIBLE),

    LEER(400, KAT_WAEHLER, BALLOT_AND_LETTER, NOT_COLLAPSIBLE),

    /** Valid votes to political groups and blank votes */
    GUELTIG_ODER_LEER(500, KAT_WAEHLER, BALLOT_AND_LETTER, NOT_COLLAPSIBLE),

    UNGUELTIGE(600, KAT_WAEHLER, BALLOT_AND_LETTER, NOT_COLLAPSIBLE),

    /** eigentlich: Anzahl abgegebene Stimmen (gueltige, ungueltige, leere) */
    WAEHLER(200, KAT_WAEHLER, KAT_VOTER_TYPE, BALLOT_AND_LETTER, NOT_COLLAPSIBLE),

    // Explaining the difference between admitted voters and valid votes
    MORE_VALID_VOTES_THAN_ADMITTED_VOTERS(1100, KAT_EXPLANATIONS, BALLOT_AND_LETTER, COLLAPSIBLE),

    LESS_VALID_VOTES_THAN_ADMITTED_VOTERS(1200, KAT_EXPLANATIONS, BALLOT_AND_LETTER, COLLAPSIBLE),

    BALLOT_PAPER_NOT_RETURNED(1300, KAT_EXPLANATIONS, BALLOT, COLLAPSIBLE),

    TOO_FEW_BALLOT_PAPER_ISSUED(1400, KAT_EXPLANATIONS, BALLOT, COLLAPSIBLE),

    TOO_MANY_BALLOT_PAPER_ISSUED(1500, KAT_EXPLANATIONS, BALLOT, COLLAPSIBLE),

    EMPTY_POSTAL_VOTES(1600, KAT_EXPLANATIONS, LETTER, COLLAPSIBLE),

    POSTAL_VOTES_WITH_MULTIPLE_BALLOT_PAPERS(1700, KAT_EXPLANATIONS, LETTER, COLLAPSIBLE),

    BALLOT_PAPERS_LOST(1800, KAT_EXPLANATIONS, LETTER, COLLAPSIBLE),

    NO_EXPLANATION(1900, KAT_EXPLANATIONS, BALLOT_AND_LETTER, COLLAPSIBLE),

    OTHER_EXPLANATIONS(2000, KAT_EXPLANATIONS, BALLOT_AND_LETTER, COLLAPSIBLE);

    private static final String S_GRAVENHAGE = "'s-Gravenhage"; //$NON-NLS-1$

    public int schluessel;
    private final String name;
    private final String nameEK;
    public String kurzname;
    public String hilfstext;
    private String kategorie;
    private String kategorieEK;
    public StimmabgabeArt stimmabgabeArt;
    public boolean collapsible;

    @SuppressWarnings("hiding")
    private GruppeAllgemein(int schluessel,
        String kategorie,
        StimmabgabeArt stimmabgabeArt,
        boolean collapsible) {
      this(schluessel, kategorie, kategorie, stimmabgabeArt, collapsible);
    }

    @SuppressWarnings("hiding")
    private GruppeAllgemein(int schluessel,
        String kategorie,
        String kategorieEK,
        StimmabgabeArt stimmabgabeArt,
        boolean collapsible) {
      String ebenenKlartext = GruppeAllgemeinEbeneProvider.getEbenenKlartext();

      this.schluessel = schluessel;
      this.name = BundleHelper
          .getBundleString("GruppeKonstanten." + ebenenKlartext + "." + this.name()); //$NON-NLS-1$ //$NON-NLS-2$
      this.nameEK = BundleHelper
          .getBundleString("GruppeKonstanten." + ebenenKlartext + ".EK." + this.name()); //$NON-NLS-1$ //$NON-NLS-2$
      this.kurzname = BundleHelper.getBundleString("GruppeKonstanten." + this.name() + ".kurz"); //$NON-NLS-1$ //$NON-NLS-2$

      if (StringUtils.isBlank(kategorie)) {
        this.kategorie = ""; //$NON-NLS-1$
      } else {
        this.kategorie = BundleHelper
            .getBundleString("GruppeKonstanten." + ebenenKlartext + ".KATEGORIE." + kategorie); //$NON-NLS-1$ //$NON-NLS-2$
      }
      if (StringUtils.isBlank(kategorieEK)) {
        this.kategorieEK = ""; //$NON-NLS-1$
      } else {
        this.kategorieEK = BundleHelper
            .getBundleString("GruppeKonstanten." + ebenenKlartext + ".EK.KATEGORIE." + kategorieEK); //$NON-NLS-1$ //$NON-NLS-2$
      }
      this.stimmabgabeArt = stimmabgabeArt;
      this.collapsible = collapsible;
    }

    public int getPosition() {
      return getPosition(WahlInfo.getWahlInfo().isEK());
    }

    public int getPosition(boolean isEK) {
      int wahlEbene = GruppeAllgemeinEbeneProvider.getWahlEbene();
      if (wahlEbene == GebietModel.EBENE_PSB) {
        return GruppePositionProvider.getPositionPSB(this);
      } else if (isEK) {
        return GruppePositionProvider.getPositionHSBCSBEK(this);
      } else {
        return GruppePositionProvider.getPositionHSBCSB(this);
      }
    }

    public boolean hasPosition(int position) {
      return position == getPosition();
    }

    public static String getHilfstext(int position) {
      GruppeAllgemein gruppeAllgemein = findByPosition(position);
      if (WahlInfo.getWahlInfo().isReferendum()
          && (Arrays.asList(LEER, GUELTIGE, null).contains(gruppeAllgemein))) {
        return BundleHelper.getBundleString("Referendumhilfstext"); //$NON-NLS-1$
      }
      if (gruppeAllgemein == null) {
        return StringUtils.EMPTY;
      }
      return BundleHelper
          .getBundleString("GruppeKonstanten." + gruppeAllgemein.name() + ".hilfstext"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public static String getKategorie(int position) {
      GruppeAllgemein gruppeAllgemein = findByPosition(position);
      if (gruppeAllgemein == null) {
        if (WahlInfo.getWahlInfo().isReferendum()) {
          return GruppeAllgemein.UNGUELTIGE.kategorie;
        }
        return ""; //$NON-NLS-1$
      }
      return WahlInfo.getWahlInfo().isEK()
          ? gruppeAllgemein.kategorieEK
          : gruppeAllgemein.kategorie;
    }

    public static String getUnterkategorie(int position) {
      GruppeAllgemein gruppeAllgemein = findByPosition(position);
      if (gruppeAllgemein == null) {
        return ""; //$NON-NLS-1$
      }
      switch (gruppeAllgemein) {
        case MORE_VALID_VOTES_THAN_ADMITTED_VOTERS :
        case LESS_VALID_VOTES_THAN_ADMITTED_VOTERS :
        case BALLOT_PAPER_NOT_RETURNED :
        case TOO_FEW_BALLOT_PAPER_ISSUED :
        case TOO_MANY_BALLOT_PAPER_ISSUED :
        case EMPTY_POSTAL_VOTES :
        case POSTAL_VOTES_WITH_MULTIPLE_BALLOT_PAPERS :
        case BALLOT_PAPERS_LOST :
        case NO_EXPLANATION :
        case OTHER_EXPLANATIONS :
          return BundleHelper
              .getBundleString("GruppeKonstanten." + GruppeAllgemeinEbeneProvider.getEbenenKlartext() + ".KATEGORIE.EXPLANATIONS.MSG"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      return ""; //$NON-NLS-1$
    }

    public static boolean isRadioButtons(int position) {
      GruppeAllgemein gruppeAllgemein = findByPosition(position);
      if (gruppeAllgemein == null) {
        return false;
      }
      if (gruppeAllgemein.equals(MORE_VALID_VOTES_THAN_ADMITTED_VOTERS)) {
        return GruppeAllgemeinEbeneProvider.getWahlEbene() == GebietModel.EBENE_PSB;
      }
      return false;
    }

    public static boolean isCollapsible(int position) {
      GruppeAllgemein gruppeAllgemein = findByPosition(position);
      if (gruppeAllgemein == null) {
        return false;
      }
      return gruppeAllgemein.collapsible
          && GruppeAllgemeinEbeneProvider.getWahlEbene() == GebietModel.EBENE_PSB;
    }

    public static String getBuchstabe(int position) {
      GruppeAllgemein gruppeAllgemein = findByPosition(position);
      if (gruppeAllgemein == null) {
        return null;
      }

      if (WahlInfo.getWahlInfo().isEK()) {
        switch (gruppeAllgemein) {
          case WAEHLER :
            return "B"; //$NON-NLS-1$
          case ADMITTED_VOTERS :
            return "A"; //$NON-NLS-1$
        }
        return null;
      }

      if (GruppeAllgemeinEbeneProvider.getWahlEbene() != GebietModel.EBENE_PSB) {
        return null;
      }

      // P4_PSB
      boolean isReferendum = WahlInfo.getWahlInfo().isReferendum();
      switch (gruppeAllgemein) {
        case ELECTION_NOTICES :
          return "A"; //$NON-NLS-1$
        case PROXY_VOTERS :
          return "B"; //$NON-NLS-1$
        case POLLING_CARDS :
          return "C"; //$NON-NLS-1$
        case ADMITTED_VOTERS :
          return "D"; //$NON-NLS-1$
        case GUELTIGE :
          return "E"; //$NON-NLS-1$
        case LEER :
          return isReferendum ? "G" : "F"; //$NON-NLS-1$ //$NON-NLS-2$
        case UNGUELTIGE :
          return isReferendum ? "H" : "G"; //$NON-NLS-1$ //$NON-NLS-2$
        case WAEHLER :
          return isReferendum ? "J" : "H"; //$NON-NLS-1$ //$NON-NLS-2$
      }
      return null;
    }

    public static boolean isSmallFontSize(int position) {
      GruppeAllgemein gruppeAllgemein = findByPosition(position);
      if (gruppeAllgemein == null) {
        return false;
      }
      switch (gruppeAllgemein) {
        case BALLOT_PAPER_NOT_RETURNED :
        case TOO_FEW_BALLOT_PAPER_ISSUED :
        case TOO_MANY_BALLOT_PAPER_ISSUED :
        case EMPTY_POSTAL_VOTES :
        case POSTAL_VOTES_WITH_MULTIPLE_BALLOT_PAPERS :
        case BALLOT_PAPERS_LOST :
        case NO_EXPLANATION :
        case OTHER_EXPLANATIONS :
          return true;
      }
      return false;
    }

    public static boolean isSmallGapAfterwards(int position) {
      GruppeAllgemein gruppeAllgemein = findByPosition(position);
      if (gruppeAllgemein == null) {
        return false;
      }
      switch (gruppeAllgemein) {
        case UNGUELTIGE :
          if (GruppeAllgemeinEbeneProvider.getWahlEbene() == GebietModel.EBENE_PSB) {
            return true;
          }
          break;

        case POLLING_CARDS :
        case LESS_VALID_VOTES_THAN_ADMITTED_VOTERS :
          return true;
      }
      return false;
    }

    public static boolean isVisible(int position, Gebiet gebiet) {
      return isVisible(findByPosition(position), gebiet);
    }

    /**
     * @return all general groups from gruppenAllgemein that are visible in the given region, but
     *         always include TotalCounted (GUELTIGE)
     */
    public static List<GruppeAllgemein> filterGruppenAllgemeinVisibleInRegionOrGueltige(Gebiet region,
        Iterable<GruppeAllgemein> gruppenAllgemein) {
      List<GruppeAllgemein> result = new ArrayList<GruppeAllgemein>();
      for (GruppeAllgemein gruppeAllgemein : gruppenAllgemein) {
        if (GUELTIGE.equals(gruppeAllgemein) || isVisible(gruppeAllgemein, region)) {
          result.add(gruppeAllgemein);
        }
      }
      return result;
    }

    public static boolean isVisible(GruppeAllgemein gruppeAllgemein, Gebiet gebiet) {
      if (gruppeAllgemein == null) {
        return true;
      }

      if (WahlInfo.getWahlInfo().isReferendum() && GUELTIGE.equals(gruppeAllgemein)) {
        return false;
      }
      if (GUELTIG_ODER_LEER.equals(gruppeAllgemein)) {
        return false;
      }

      if (WahlInfo.getWahlInfo().isEK()) {
        switch (gruppeAllgemein) {
          case GUELTIGE :
          case WAEHLER :
          case ELECTION_NOTICES :
          case ADMITTED_VOTERS :
            return true;

          case MORE_VALID_VOTES_THAN_ADMITTED_VOTERS :
          case LESS_VALID_VOTES_THAN_ADMITTED_VOTERS :
          case BALLOT_PAPER_NOT_RETURNED :
          case TOO_FEW_BALLOT_PAPER_ISSUED :
          case TOO_MANY_BALLOT_PAPER_ISSUED :
          case EMPTY_POSTAL_VOTES :
          case POSTAL_VOTES_WITH_MULTIPLE_BALLOT_PAPERS :
          case BALLOT_PAPERS_LOST :
          case NO_EXPLANATION :
          case OTHER_EXPLANATIONS :
            return false;
        }
      }

      // The following Groups shall be invisible in HSB and CSB:
      if (GruppeAllgemeinEbeneProvider.getWahlEbene() != GebietModel.EBENE_PSB) {
        switch (gruppeAllgemein) {
          case GUELTIGE : // Hinweis: Die Gültigen werden dem EML-Export nun immer hinzugefügt
                          // (siehe EML510Helper.appendGeneralVotingResults()) OSV-1462
          case WAEHLER :
          case ELECTION_NOTICES :
          case ADMITTED_VOTERS :
            return false;
        }
      }

      // The group POLLING_CARDS shall be invisible in CSB:
      if (GruppeAllgemeinEbeneProvider.getWahlEbene() != GebietModel.EBENE_PSB
          && GruppeAllgemeinEbeneProvider.getWahlEbene() != GebietModel.EBENE_HSB
          && POLLING_CARDS.equals(gruppeAllgemein)) {
        return false;
      }

      if (gebiet.isPostalvote()) {
        // For postal polling stations the general group is only visible ...
        if (gruppeAllgemein.stimmabgabeArt.equals(BALLOT)) {
          return false;
        } else {
          // ... if stimmabgabeArt == LETTER or stimmabgabeArt == BALLOT_AND_LETTER.
          return true;
        }
      }

      // The remaining cases are normal (not postal) polling stations and higher level regions

      // They always count presence votes, so ...
      if (gruppeAllgemein.stimmabgabeArt.equals(BALLOT)
          || gruppeAllgemein.stimmabgabeArt.equals(BALLOT_AND_LETTER)) {
        // .. all general groups that deal with ballot votes are visible
        return true;
      }

      // The remaining cases are stimmabgabeArt == LETTER. Here the general group is only visible if
      // the region has a sub-region that is a postal pollig station. These only exist in TK and EP
      // elections

      if (!ElectionCategory.fromWahlart(gebiet.getWahl().getWahlart()).hasPostalVotes()) {
        return false;
      }

      // Postals polling stations only exist in municipality 's-Gravenhage and kieskring
      // 's-Gravenhage
      if (isGebietOrHasUntergebietSGravenhage(gebiet)) {
        return true;
      }

      return false;
    }

    /**
     * @param gebiet
     * @return <code>true</code>, if gebiet or one of its subregions has the name S_GRAVENHAGE
     */
    public static boolean isGebietOrHasUntergebietSGravenhage(Gebiet gebiet) {
      if (S_GRAVENHAGE.equals(gebiet.getName())) {
        return true;
      }
      Collection<Gebiet> subregions = gebiet.getGebietCol();
      for (Gebiet subregion : subregions) {
        if (isGebietOrHasUntergebietSGravenhage(subregion)) {
          return true;
        }
      }

      return false;
    }

    public static boolean isVisibleInOverview(int position, Gebiet gebiet) {
      return isVisibleInOverview(findByPosition(position), gebiet);
    }

    /**
     * @return <code>true</code> for groups (special groups and political groups) that shall be
     *         visible in the result overview of the region and in the CSV result export (osv4-3)
     */
    public static boolean isVisibleInOverview(GruppeAllgemein gruppeAllgemein, Gebiet gebiet) {
      boolean isReferendum = WahlInfo.getWahlInfo().isReferendum();
      return isVisible(gruppeAllgemein, gebiet)
          || (!isReferendum && GruppeAllgemein.GUELTIGE.equals(gruppeAllgemein))
          || (isReferendum && GruppeAllgemein.GUELTIG_ODER_LEER.equals(gruppeAllgemein))
          || GruppeAllgemein.WAEHLER.equals(gruppeAllgemein);
    }

    private static GruppeAllgemein findByPosition(int position) {
      for (GruppeAllgemein gruppe : GruppeAllgemein.values()) {
        if (gruppe.getPosition() == position) {
          return gruppe;
        }
      }
      return null;
    }

    /**
     * @return true, if this group has another name in referenda than in other elections
     */
    private boolean hasSpecialNameForReferendum() {
      return this.equals(GUELTIGE) || this.equals(GUELTIG_ODER_LEER);
    }

    /**
     * @return true, if this group has another name in referenda than in other elections
     */
    private boolean hasSpecialNameForEK() {
      return Arrays.asList(WAEHLER, ELECTION_NOTICES, PROXY_VOTERS, ADMITTED_VOTERS).contains(this);
    }

    /**
     * @param isEK
     * @return my referendum-aware name
     */
    public String getName(boolean isReferendum, boolean isEK) {
      if (isReferendum && hasSpecialNameForReferendum()) {
        return BundleHelper.getBundleString("GruppeKonstanten.REFERENDUM." + this.name()); //$NON-NLS-1$
      } else {
        return getName(isEK);
      }
    }

    public String getName(boolean isEK) {
      if (isEK && hasSpecialNameForEK()) {
        return nameEK;
      } else {
        return name;
      }
    }

    public String getName() {
      return getName(WahlInfo.getWahlInfo().isReferendum(), WahlInfo.getWahlInfo().isEK());
    }

  }
}
