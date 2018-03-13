/*
 * RG510Helper
 * 
 * Created on 25.11.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import static de.ivu.wahl.dataimport.XMLTags.NS_RG;
import static de.ivu.wahl.export.XMLTags.RG_510_ELEMENT;
import static de.ivu.wahl.export.XMLTags.RG_CONTEST_NAME;
import static de.ivu.wahl.export.XMLTags.RG_DATE_OF_MEETING;
import static de.ivu.wahl.export.XMLTags.RG_PROPOSAL_NAME;
import static de.ivu.wahl.export.XMLTags.RG_TIME_OF_MEETING;
import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import org.apache.commons.lang.StringUtils;

import nu.xom.Element;

import de.ivu.wahl.Konstanten;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.auswertung.erg.ResultSummary;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.GebietHome;
import de.ivu.wahl.modell.ejb.service.VotesHandling;
import de.ivu.wahl.wus.reportgen.EMLMessageType;

/**
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class RG510Helper extends BasicRGHelper {

  /**
   * Constructor
   */
  @SuppressWarnings("hiding")
  RG510Helper(ExportHandlingBean bean) {
    super(bean);
  }

  /**
   * Creates eml510 subtree used for generation of report P22
   * 
   * @param emptyResults
   * @return xml subtree
   */
  Element createReportGeneratorElement510(VotesHandling votesHandling,
      GebietHome gebietHome,
      Gebiet region,
      EMLMessageType emlType,
      boolean emptyResults) {
    WahlInfo wahlInfo = WahlInfo.getWahlInfo();
    boolean isReferendum = wahlInfo.isReferendum();
    Element rg510 = XMLHelper.createElement(RG_510_ELEMENT, NS_RG);

    // Contest name
    String contestName = bean.getAdminHandling()
        .getProperty(Konstanten.KEY_ELECTORAL_DISTRICT_NAME);
    if (isEmpty(contestName) && wahlInfo.getElectionCategory().isMunicipalityElection()) {
      // For GR and ER elections take the election domain as contestName
      contestName = wahlInfo.getWahl().getElectionDomain();
    }
    // For EP elections in P4_PSB take the parent region name
    String parentRegionName = bean.getAdminHandling()
        .getProperty(Konstanten.KEY_PARENT_REGION_NAME);
    if (StringUtils.isNotBlank(parentRegionName)) {
      contestName = parentRegionName;
    }
    if (!isEmpty(contestName)) {
      rg510.appendChild(XMLHelper.createElementWithValue(RG_CONTEST_NAME, NS_RG, contestName));
    }

    // meeting data
    String dateStr = bean.getPropertyHandling().getProperty(RG_DATE_OF_MEETING);
    if (dateStr != null) {
      rg510.appendChild(XMLHelper.createElementWithValue(RG_DATE_OF_MEETING,
          NS_RG,
          XMLHelper.createDateString(dateStr)));
    }
    String timeStr = bean.getPropertyHandling().getProperty(RG_TIME_OF_MEETING);
    if (timeStr != null) {
      rg510.appendChild(XMLHelper.createElementWithValue(RG_TIME_OF_MEETING, NS_RG, timeStr));
    }

    // Names of committee members
    for (String prop : Arrays.asList(XMLTags.RG_CHAIRMAN_OF_COMMITTEE,
        XMLTags.RG_MEMBER_OF_COMMITTEE_1,
        XMLTags.RG_MEMBER_OF_COMMITTEE_2,
        XMLTags.RG_MEMBER_OF_COMMITTEE_3)) {
      String memberName = bean.getPropertyHandling().getProperty(prop);
      if (memberName != null) {
        rg510.appendChild(XMLHelper.createElementWithValue(XMLTags.RG_MEMBER_OF_COMMITTEE,
            NS_RG,
            memberName));
      }
    }

    // votes
    String id_Gebiet = region.getID_Gebiet();
    if (isPostalVoteOfficeExists(gebietHome) && !emptyResults) {
      if (id_Gebiet != null && id_Gebiet.equals(wahlInfo.getID_Wurzelgebiet())) {
        ResultSummary resultSummary = votesHandling.getResultSummary();
        if (resultSummary != null) {
          // differ between regions
          List<Gebiet> gebiete = new ArrayList<Gebiet>(resultSummary.getGebiete());
          List<Gebiet> normalVoteRegions = new ArrayList<Gebiet>();
          List<Gebiet> postalVoteRegions = new ArrayList<Gebiet>();
          for (Gebiet gebiet : gebiete) {
            if (gebiet.isPostalvote()) {
              postalVoteRegions.add(gebiet);
            } else {
              normalVoteRegions.add(gebiet);
            }
          }

          try {
            // append normal votes
            bean.appendAffiliationVotesAndGeneralGroups(resultSummary,
                rg510,
                region,
                normalVoteRegions,
                isReferendum,
                ExportHandlingBean.NORMAL_VOTES,
                emlType);

            // append postal votes
            bean.appendAffiliationVotesAndGeneralGroups(resultSummary,
                rg510,
                region,
                postalVoteRegions,
                isReferendum,
                ExportHandlingBean.POSTAL_VOTES,
                emlType);

          } catch (EJBException e) {
            e.printStackTrace();
          } catch (FinderException e) {
            e.printStackTrace();
          }
        }
      } else {
        // should not happen
      }

    } else {
      Element presenceVotes = XMLHelper.createElement(de.ivu.wahl.export.XMLTags.RG_PRESENCE_VOTES,
          NS_RG);
      rg510.appendChild(presenceVotes);
      if (emptyResults) {
        bean.appendEmptyAffiliationVotes(presenceVotes, id_Gebiet, emlType);
      } else {

        // without any postal vote office
        String id_Ergebniseingang = region.getLetzterGueltigerEingang().getID_Ergebniseingang();
        bean.appendAffiliationVotes(id_Ergebniseingang,
            presenceVotes,
            id_Gebiet,
            isReferendum,
            emlType);
        appendVotesForRGGeneralGroups(presenceVotes, id_Ergebniseingang);
      }
    }

    // objections
    rg510.appendChild(bean.createVoterObjectionsRG());
    if (isReferendum) {
      appendProposalName(rg510);
    }

    return rg510;
  }

  /**
   * @return
   */
  private boolean isPostalVoteOfficeExists(GebietHome gebietHome) {
    try {
      for (GebietModel gebiet : gebietHome.findAll()) {
        if (gebiet.isPostalvote()) {
          return true;
        }
      }
    } catch (FinderException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Only for Referendum, append child with the referendum question
   */
  private void appendProposalName(Element parent) {
    String proposalName = bean.getPropertyHandling().getProperty(Konstanten.KEY_REFERENDUM_TEXT);
    parent.appendChild(XMLHelper.createElementWithValue(RG_PROPOSAL_NAME, NS_RG, proposalName));
  }

}
