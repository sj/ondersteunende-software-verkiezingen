/*
 * XMLTags
 * 
 * Created on 24.02.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

/**
 * Attribute and element names used for Report Generator schema
 * 
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public interface XMLTags {

  String ATTR_SCHEMA_VERSION = "SchemaVersion"; //$NON-NLS-1$
  String RG_510_ELEMENT = "RG510"; //$NON-NLS-1$
  String RG_520_ELEMENT = "RG520"; //$NON-NLS-1$
  String RG_CONTEST_NAME = "ContestName"; //$NON-NLS-1$
  String RG_DATE_OF_MEETING = "DateOfMeeting"; //$NON-NLS-1$
  String RG_TIME_OF_MEETING = "TimeOfMeeting"; //$NON-NLS-1$
  String RG_DATE_OF_MEETING_O1P20 = "DateOfMeetingO1P20"; //$NON-NLS-1$
  String RG_TIME_OF_MEETINGP20 = "TimeOfMeetingP20"; //$NON-NLS-1$
  String RG_DATE_PUBLICATION_VOTE_VALUES = "PublicationVoteValuesDate"; //$NON-NLS-1$
  String RG_NUMBER_PUBLICATION_VOTE_VALUES = "PublicationVoteValuesNumber"; //$NON-NLS-1$
  String RG_REGIONS_OVERVIEW = "ElectoralDistrictsOverview"; //$NON-NLS-1$
  String RG_REGION_NAME = "ElectoralDistrictName"; //$NON-NLS-1$
  String ATTR_REGION_NAME_ID = "id"; //$NON-NLS-1$
  String ATTR_VOTE_VALUE = "voteValue"; //$NON-NLS-1$
  String RG_LISTS_REGIONS = "OverviewOfListsAndDistricts"; //$NON-NLS-1$
  String RG_CANDIDATE_LIST = "CandidateListName"; //$NON-NLS-1$
  String RG_FIRST_CANDIDATE = "FirstCandidateName"; //$NON-NLS-1$
  String RG_TYPE = "Type"; //$NON-NLS-1$
  String RG_SUBMITTED_IN_REGION = "SubmittedInElectoralDistrict"; //$NON-NLS-1$
  String RG_LISTS_REGIONS_VOTES = "OverviewOfListsAndDistrictsAndVotes"; //$NON-NLS-1$
  String RG_REGION_VOTES = "OverviewOfDistrictsVotes"; //$NON-NLS-1$
  String RG_REGION_BLANC_VOTES = "OverviewOfBlancVotes"; //$NON-NLS-1$
  String RG_REGION_INVALID_VOTES = "OverviewOfInvalidVotes"; //$NON-NLS-1$
  String ATTR_LIST_NUMBER = "listNumber"; //$NON-NLS-1$
  String ATTR_SET = "setNumber"; //$NON-NLS-1$
  String ATTR_DISTRICT_NUMBER = "districtNumber"; //$NON-NLS-1$
  String ATTR_PROVINCE_NAME = "provinceName"; //$NON-NLS-1$
  String ATTR_BELONGS_TO_SET = "BelongsToSet"; //$NON-NLS-1$
  String ATTR_ID = "id"; //$NON-NLS-1$
  String ATTR_VALUE_ALLE = "alle"; //$NON-NLS-1$
  String RG_VOTES_IN_REGION = "VotesInElectoralDistrict"; //$NON-NLS-1$
  String ATTR_WEIGHTED = "weighted"; //$NON-NLS-1$
  String RG_VOTES_IN_REGION_PART = "VotesInElectoralDistrictPart"; //$NON-NLS-1$
  String RG_QUOTA = "ElectoralQuota"; //$NON-NLS-1$
  String RG_FRACTION = "Fraction"; //$NON-NLS-1$
  String ATTR_NUMERATOR = "numerator"; //$NON-NLS-1$
  String ATTR_DENOMINATOR = "denominator"; //$NON-NLS-1$
  String RG_WITHOUT_COMBINED_LISTS = "ResultWithoutRegardingCombinedLists"; //$NON-NLS-1$
  String RG_LISTS_SEATS = "ListAndSeats"; //$NON-NLS-1$
  String ATTR_SEATS = "seats"; //$NON-NLS-1$
  String RG_CHECK_COMBINED_LISTS = "CheckingCombinedLists"; //$NON-NLS-1$
  String RG_CHECK_COMBINED_LISTS_LINE = "CheckingCombinedListsLine"; //$NON-NLS-1$
  String RG_COMBINED_LIST_ID = "CombinedListId"; //$NON-NLS-1$
  String RG_VOTES_COMBINED_LIST = "NumberOfVotesCombinedList"; //$NON-NLS-1$
  String RG_CHECKED_LIST = "CheckedList"; //$NON-NLS-1$
  String RG_NUMBER_LIST = "NumberList"; //$NON-NLS-1$
  String RG_VOTES_FOR_LIST = "NumberOfVotesList"; //$NON-NLS-1$
  String RG_REGARDED = "Regarded"; //$NON-NLS-1$
  String RG_FIRST_ASSIGNMENT = "FirstAssignment"; //$NON-NLS-1$
  String RG_FIRST_ASSIGNMENT_LINE = "FirstAssignmentLine"; //$NON-NLS-1$
  String RG_TOTAL_SEATS_ASSIGNED = "TotalSeatsAssigned"; //$NON-NLS-1$
  String RG_LIST_COMBINED_LIST = "ListOrCombinedList"; //$NON-NLS-1$
  String ATTR_COMBINATION_ID = "combinationId"; //$NON-NLS-1$
  String ATTR_PRIOR_SEATS = "PriorSeats"; //$NON-NLS-1$
  String ATTR_NEW_SEATS = "NewSeats"; //$NON-NLS-1$
  String RG_LIST_IN_COMBINATION = "ListInCombination"; //$NON-NLS-1$
  String RG_VOTES = "Votes"; //$NON-NLS-1$
  String RG_SEATS = "Seats"; //$NON-NLS-1$
  String RG_DHONDT_ASSIGNMENT = "DHondtAssignment"; //$NON-NLS-1$
  String ATTR_ARTICLE = "article"; //$NON-NLS-1$
  String ATTR_MAX_NUMBER_OF_ASSIGNMENTS = "maxNumberOfAssignments"; //$NON-NLS-1$
  String ATTR_NUMBER_OF_UNASSIGNED_SEATS_BEFORE_DHONDT_ASSIGNMENT = "numberOfUnassignedSeatsBeforeDHondtAssignment"; //$NON-NLS-1$
  String RG_DHONDT_ASSIGNMENT_LINE = "DHondtAssignmentLine"; //$NON-NLS-1$
  String RG_PRIOR_SEATS = "PriorSeats"; //$NON-NLS-1$
  String RG_NEW_SEATS = "NewSeats"; //$NON-NLS-1$
  String RG_DHONT_FRACTION = "DHondtFraction"; //$NON-NLS-1$
  String ATTR_STEP = "step"; //$NON-NLS-1$
  String ATTR_WINNER = "winner"; //$NON-NLS-1$
  String RG_NIEMEYER_ASSIGNMENT = "NiemeyerAssignment"; //$NON-NLS-1$
  String RG_NIEMEYER_ASSIGNMENT_LINE = "NiemeyerAssignmentLine"; //$NON-NLS-1$
  String RG_MAJORITY = "AbsoluteMajority"; //$NON-NLS-1$
  String RG_WINNER = "Winner"; //$NON-NLS-1$
  String RG_LOOSER = "Looser"; //$NON-NLS-1$
  String RG_ALLOTTING = "Allotting"; //$NON-NLS-1$
  String ATTR_NUMBER_CHOICES = "numberOfChoices"; //$NON-NLS-1$
  String RG_EXHAUSTED_LIST = "ExhaustedList"; //$NON-NLS-1$
  String ATTR_LOST_SEATS = "lostSeats"; //$NON-NLS-1$
  String RG_ASSIGNMENT_COMBINED_LISTS = "AssignmentWithinCombinedLists"; //$NON-NLS-1$
  String RG_ASSIGNMENT_COMBINED_LISTS_LINE = "AssignmentWithinCombinedListsLine"; //$NON-NLS-1$
  String RG_ASSIGNMENT_LIST_GROUPS = "AssignmentWithinListGroups"; //$NON-NLS-1$
  String RG_ASSIGNMENT_LIST_GROUPS_LINE = "AssignmentWithinListGroupsLine"; //$NON-NLS-1$
  String ATTR_SORT_NUMBER = "sortNumber"; //$NON-NLS-1$
  String RG_RESIDUAL_SEATS = "ResidualSeats"; //$NON-NLS-1$
  String RG_REMAINDER = "Remainder"; //$NON-NLS-1$
  String RG_CANDIDATES_RESULTS = "OverviewOfCandidatesAndResults"; //$NON-NLS-1$
  String RG_ANOMALY_IN_SEAT_DISTRIBUTION = "AnomalyInSeatDistribution"; //$NON-NLS-1$
  String RG_LIST_RESULTS = "ListAndResults"; //$NON-NLS-1$
  String RG_LIST_GROUP_RESULTS = "ListGroupAndResults"; //$NON-NLS-1$
  String RG_DEAD_CANDIDATE = "DeadCandidate"; //$NON-NLS-1$
  String RG_PRIORITY_CANDIDATE = "PriorityCandidate"; //$NON-NLS-1$
  String RG_CANDIDATE_ELECTED_ON_LIST = "CandidateElectedByPositionOnList"; //$NON-NLS-1$
  String RG_CANDIDATES_ELECTED_ON_LIST = "CandidatesElectedByPositionOnList"; //$NON-NLS-1$
  String RG_LIST_POSITIONS_CHANGED = "PositionsOnListChanged"; //$NON-NLS-1$
  String RG_CANDIDATE_RESULT = "CandidateAndResult"; //$NON-NLS-1$
  String RG_TOTAL_VOTES = "TotalVotes"; //$NON-NLS-1$
  String RG_ABOVE_PREF_BARRIER = "AbovePreferencialBarrier"; //$NON-NLS-1$
  String RG_ELECTED = "Elected"; //$NON-NLS-1$
  String RG_ELECTED_ON_LIST_GROUP = "ElectedOnListGroup"; //$NON-NLS-1$
  String ATTR_BY_LOT = "byLot"; //$NON-NLS-1$
  String RG_DECEASED = "Deceased"; //$NON-NLS-1$
  String RG_NEW_LIST_POSITION = "NewPositionOnList"; //$NON-NLS-1$
  String RG_ELECTED_CANDIDATE = "ElectedCandidate"; //$NON-NLS-1$
  String RG_ELECTED_CANDIDATES = "OverviewOfElectedCandidates"; //$NON-NLS-1$
  String RG_VOTERS_OBJECTIONS = "ObjectionsByVoters"; //$NON-NLS-1$
  String RG_OBJECTIONS = "Objections"; //$NON-NLS-1$
  String RG_NOTES = "NotesOnObjections"; //$NON-NLS-1$
  String RG_MEMBER_OF_COMMITTEE = "MemberOfElectoralCommittee"; //$NON-NLS-1$
  String RG_CHAIRMAN_OF_COMMITTEE = "ChairmanOfElectoralCommittee"; //$NON-NLS-1$ // Not an XML element
  String RG_MEMBER_OF_COMMITTEE_1 = "MemberOfElectoralCommittee1"; //$NON-NLS-1$ // Not an XML element
  String RG_MEMBER_OF_COMMITTEE_2 = "MemberOfElectoralCommittee2"; //$NON-NLS-1$ // Not an XML element
  String RG_MEMBER_OF_COMMITTEE_3 = "MemberOfElectoralCommittee3"; //$NON-NLS-1$ // Not an XML element
  String RG_POSTAL_VOTES = "PostalVotes"; //$NON-NLS-1$
  String RG_PRESENCE_VOTES = "PresenceVotes"; //$NON-NLS-1$
  String RG_CAST = "Cast"; //$NON-NLS-1$
  String RG_TOTAL_COUNTED = "TotalCounted"; //$NON-NLS-1$
  String RG_BLANC_VOTES = "BlancVotes"; //$NON-NLS-1$
  String RG_INVALID_VOTES = "InvalidVotes"; //$NON-NLS-1$
  // Bericht aan de verkozen kandidaten
  String RG_CANDIDATE_LETTER = "CandidateLetter"; //$NON-NLS-1$
  String RG_DATE_LETTER = "DateLetter"; //$NON-NLS-1$
  String RG_PLACE_LETTER = "PlaceLetter"; //$NON-NLS-1$
  String RG_FEEDBACK_DATE = "DateFeedback"; //$NON-NLS-1$
  String RG_FIRST_MEETING = "DateFirstMeeting"; //$NON-NLS-1$
  String RG_CHAIRPERSON = "Chairperson"; //$NON-NLS-1$
  String RG_ACCEPTANCE_ADDRESS = "AddressLineForAcceptance"; //$NON-NLS-1$
  String RG_ACCEPTANCE_LOCATION = "LocationForAcceptance"; //$NON-NLS-1$
  String RG_ACCEPTANCE_POSTALCODE = "PostalCodeForAcceptance"; //$NON-NLS-1$
  String RG_REJECTION_ADDRESS = "AddressLineForRejection"; //$NON-NLS-1$
  String RG_REJECTION_LOCATION = "LocationForRejection"; //$NON-NLS-1$
  String RG_REJECTION_POSTALCODE = "PostalCodeForRejection"; //$NON-NLS-1$
  // EML 510 and 520
  String RG_AFFILIATION_VOTES = "AffiliationVotes"; //$NON-NLS-1$
  String ATTR_PUBLICATION_LANGUAGE = "PublicationLanguage"; //$NON-NLS-1$
  String RG_CANDIDATE_VOTES = "CandidateVotes"; //$NON-NLS-1$
  String RG_PROPOSAL_NAME = "ProposalName"; //$NON-NLS-1$

  String RG_VOTERS_WITH_ELECTION_NOTICES = "VotersWithElectionNotice"; //$NON-NLS-1$
  String RG_PROXY_VOTERS = "ProxyVoters"; //$NON-NLS-1$
  String RG_VOTERS_WITH_POLLING_CARDS = "VotersWithPollingCard"; //$NON-NLS-1$
  String RG_ADMITTED_VOTERS = "AdmittedVoters"; //$NON-NLS-1$

  String RG_MORE_VALID_VOTES_THAN_ADMITTED_VOTERS = "MoreValidVotesThanAdmittedVoters"; //$NON-NLS-1$
  String RG_LESS_VALID_VOTES_THAN_ADMITTED_VOTERS = "LessValidVotesThanAdmittedVoters"; //$NON-NLS-1$
  String RG_BALLOT_PAPER_NOT_RETURNED = "BallotPaperNotReturned"; //$NON-NLS-1$
  String RG_TOO_FEW_BALLOT_PAPER_ISSUED = "TooFewBallotPaperIssued"; //$NON-NLS-1$
  String RG_TOO_MANY_BALLOT_PAPER_ISSUED = "TooManyBallotPaperIssued"; //$NON-NLS-1$
  String RG_EMPTY_POSTAL_VOTES = "EmptyPostalVotes"; //$NON-NLS-1$
  String RG_POSTAL_VOTES_WITH_MULTIPLE_BALLOT_PAPERS = "PostalVotesWithMultipleBallotPapers"; //$NON-NLS-1$
  String RG_BALLOT_PAPERS_LOST = "BallotPapersLost"; //$NON-NLS-1$
  String RG_NO_EXPLANATION = "NoExplanation"; //$NON-NLS-1$
  String RG_OTHER_EXPLANATIONS = "OtherExplanations"; //$NON-NLS-1$

}
