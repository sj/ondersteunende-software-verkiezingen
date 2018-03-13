<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:param name="RP_ChapterElection_Verkiezing" select="'Verkiezing'" />
	<xsl:param name="RP_ChapterElection_HetGaatOmVerkiezing" select="'Het gaat om de verkiezing van:'" />
	<xsl:param name="RP_ChapterElection_HetGaatOmVerkiezingVanLeden" select="'Het gaat om de verkiezing van de leden van:'" />
	<xsl:param name="RP_ChapterElection_HetGaatOmKandidatenlijst" select="'Het gaat om de kandidatenlijst voor de verkiezing van:'" />
	<xsl:param name="RP_ChapterElection_VoorDeProvincie" select="' voor de provincie '" />

	<xsl:param name="RP_ChapterPartyName_AanduidingVan" select="'Aanduiding van de politieke groepering'" />
	<xsl:param name="RP_ChapterPartyName_AanduidingVan_H3" select="'Aanduiding van de politieke groepering'" />
	<xsl:param name="RP_ChapterPartyName_AanduidingVan_BC" select="'Aanduiding van de (politieke) groepering'" />
	<xsl:param name="RP_ChapterPartyName_AanduidingVan_BC_H3" select="'Aanduiding van de (politieke) groepering'" />
	<xsl:param name="RP_ChapterPartyName_AanduidingBoven" select="'Aanduiding boven de kandidatenlijst: '" />
	<xsl:param name="RP_ChapterPartyName_geregistreerdeAanduiding" select="'De geregistreerde aanduiding van de politieke groepering: '" />
	<xsl:param name="RP_ChapterPartyName_samengevoegdeAanduiding" select="'De samengevoegde aanduiding van de politieke groeperingen: '" />
	<xsl:param name="RP_ChapterPartyName_Kieskringen" select="'Kieskringen'" />

	<xsl:param name="RP_ChapterDistrictTitle_Kieskringen" select="'Kieskringen'" />
	<xsl:param name="RP_ChapterDistrictTitle_alleenInvullen" select="'(alleen invullen bij de verkiezing van de Tweede Kamer, Eerste Kamer, of de provinciale staten van een provincie met meer dan één kieskring)'" />

	<xsl:param name="RP_ChapterDistrictOptions_uitsluitend" select="'uitsluitend voor de volgende kieskring(en): '"/>
	<xsl:param name="RP_ChapterDistrictOptions_voor" select="'voor '"/>
	<xsl:param name="RP_ChapterDistrictOptions_alleKieskringen" select="'alle kieskringen'"/>
	<xsl:param name="RP_ChapterDistrictOptions_waarvoorIngeleverd" select="' waarvoor de kandidatenlijst wordt ingeleverd.'"/>

	<xsl:param name="RP_CandidatesOnListTable-naam" select="'naam'" />
	<xsl:param name="RP_CandidatesOnListTable-voorletters" select="'voorletters'" />
	<xsl:param name="RP_CandidatesOnListTable-geboortedatum" select="'geboortedatum'" />
	<xsl:param name="RP_CandidatesOnListTable-woonplaats" select="'woonplaats'" />
	
	<xsl:param name="RP_ElectionName_verkiezingLeden" select="'de verkiezing van de leden van '" />
	<xsl:param name="RP_ElectionName_referendumOver" select="'het referendum over '" />
	<xsl:param name="RP_ElectionName_op" select="' op '" />

	<xsl:param name="RP_ElectionNameP2_verkiezingLeden" select="'verkiezing van de leden van '" />

	<xsl:param name="RP_Salutation_dhr" select="'dhr. '" />
	<xsl:param name="RP_Salutation_mevr" select="'mevr. '" />

	<xsl:param name="RP_GenderP1_m" select="' (m)'" />
	<xsl:param name="RP_GenderP1_v" select="' (v)'" />
	<xsl:param name="RP_GenderP1_unknown" select="' (v)'" />

	<xsl:param name="RP_Gender_m" select="' (m)'" />
	<xsl:param name="RP_Gender_v" select="' (v)'" />
	<xsl:param name="RP_Gender_unknown" select="' (f)'" />

	<xsl:param name="RP_Gender2_m" select="' (m)'" />
	<xsl:param name="RP_Gender2_v" select="' (v)'" />
	<xsl:param name="RP_Gender2_unknown" select="' (f)'" />

	<xsl:param name="RP_Gender3_hij" select="'hij'" />
	<xsl:param name="RP_Gender3_zij" select="'zij'" />
	<xsl:param name="RP_Gender3_hijZij" select="'hij/zij'" />
	<xsl:param name="RP_Gender3_unknown" select="'zij'" />

	<xsl:param name="RP_Gender4_him" select="'zich'" />
	<xsl:param name="RP_Gender4_har" select="'zich'" />
	<xsl:param name="RP_Gender4_himHar" select="'zich'" />
	<xsl:param name="RP_Gender4_unknown" select="'zich'" />

	<xsl:param name="RP_Date_januari" select="'januari'" />
	<xsl:param name="RP_Date_februari" select="'februari'" />
	<xsl:param name="RP_Date_maart" select="'maart'" />
	<xsl:param name="RP_Date_april" select="'april'" />
	<xsl:param name="RP_Date_mei" select="'mei'" />
	<xsl:param name="RP_Date_juni" select="'juni'" />
	<xsl:param name="RP_Date_juli" select="'juli'" />
	<xsl:param name="RP_Date_augustus" select="'augustus'" />
	<xsl:param name="RP_Date_september" select="'september'" />
	<xsl:param name="RP_Date_oktober" select="'oktober'" />
	<xsl:param name="RP_Date_november" select="'november'" />
	<xsl:param name="RP_Date_december" select="'december'" />

	<xsl:param name="RP_PartyName_blancoLijst" select="'niet van toepassing (blanco lijst)'" />

	<xsl:param name="RP_PartyName2_blancoLijst" select="'blanco lijst met als eerste kandidaat'" />

	<xsl:param name="RP_PartyNameInOmissions_blancoLijst" select="'blanco lijst met als eerste kandidaat '" />

	<xsl:param name="RP_PartyNameInOmissionsEK_blancoLijst" select="'blanco lijst met als eerste kandidaat '" />

	<xsl:param name="RP_PartyNameOrFirstCandidate_blancoLijst" select="'blanco lijst met als eerste kandidaat '" />

	<xsl:param name="RP_Kieskring_inKieskring" select="' in kieskring '" />

	<xsl:param name="RP_KieskringParty_inKieskring" select="' in kieskring'" />
	<xsl:param name="RP_KieskringParty_inAlleKieskringen" select="' in alle kieskringen'" />

	<xsl:param name="RP_FootnoteKieskring_voetnoten" select="'Voetnoten:'" />
	<xsl:param name="RP_FootnoteKieskring_alleenInTeVullen" select="'Alleen in te vullen, indien het een verkiezing betreft van de leden van de Tweede Kamer of van provinciale staten van een provincie die uit meer dan één kieskring bestaat. Achter “kieskring” het nummer van de kieskring en tussen haakjes de naam van de gemeente waar het hoofdstembureau is gevestigd vermelden.'" />

	<xsl:param name="RP_Checkbox_iconChecked" select="'■'" />
	<xsl:param name="RP_Checkbox_iconUnchecked" select="'□'" />

	<xsl:param name="RP_FooterWithoutHashcode_concept" select="'(Concept) '" />
	<xsl:param name="RP_FooterWithoutHashcode_datum" select="'Datum: '" />

	<xsl:param name="RP_FooterWithHashcode_concept" select="'(Concept) | '" />
	<xsl:param name="RP_FooterWithHashcode_hashcode" select="'SHA-256-Hashcode: '" />

	<xsl:param name="RP_CombinedList_en" select="'en '" />

	<xsl:param name="RP_CombinedList2_en" select="'en '" />
	<xsl:param name="RP_CombinedList2_lijst" select="' (lijst '" />

	<xsl:param name="RP_CombinedListListName_blancoLijst" select="'blanco lijst met als eerste kandidaat '" />

	<xsl:param name="RP_SignatureBlock_plaats" select="'Plaats: ........................................................'" />
	<xsl:param name="RP_SignatureBlock_datum" select="'Datum: .......................................................'" />
	<xsl:param name="RP_SignatureBlock_voorzitter" select="'......................................................................, Voorzitter'" />
	<xsl:param name="RP_SignatureBlock_leden" select="'...................................................................... Leden'" />
	<xsl:param name="RP_SignatureBlock_emptyLine" select="'......................................................................'" />

	<xsl:param name="RP_SignatureBlock-3_plaats" select="'Plaats: ........................................................'" />
	<xsl:param name="RP_SignatureBlock-3_datum" select="'Datum: .......................................................'" />
	<xsl:param name="RP_SignatureBlock-3_voorzitter" select="'............................................., Voorzitter'" />
	<xsl:param name="RP_SignatureBlock-3_leden" select="'............................................. Leden'" />
	<xsl:param name="RP_SignatureBlock-3_emptyLine" select="'.............................................'" />

	<xsl:param name="RP_ElectionNameSimple_de" select="'de '" />
	<xsl:param name="RP_ElectionNameSimple_het" select="'het '" />

	<xsl:param name="RP_ElectionNameSimple_tweedeKamer" select="'Tweede Kamer der Staten-Generaal'" />
	<xsl:param name="RP_ElectionNameSimple_eersteKamer" select="'Eerste Kamer der Staten-Generaal'" />
	<xsl:param name="RP_ElectionNameSimple_provincialeStaten" select="'provinciale staten'" />
	<xsl:param name="RP_ElectionNameSimple_algemeen_bestuur" select="'algemeen bestuur van het '" />
	<xsl:param name="RP_ElectionNameSimple_hetEuropeesParlement" select="'Europees Parlement'" />
	<xsl:param name="RP_ElectionNameSimple_raadVanDeGemeente" select="'gemeenteraad'" />
	<xsl:param name="RP_ElectionNameSimple_eilandsraad" select="'eilandsraad'" />
	<xsl:param name="RP_ElectionNameSimple_bestuurscommissie" select="'algemeen bestuur van de bestuurscommissie'" />
	<xsl:param name="RP_ElectionNameSimple_gebiedscommissie" select="'gebiedscommissie'" />

	<xsl:param name="RP_ElectionNameSimple_deelraad" select="'deelraad - deprecated'" />
	<xsl:param name="RP_ElectionNameSimple_deelgemeenteraad" select="'deelgemeenteraad - deprecated'" />

	<xsl:param name="RP_ElectionNameSimple_van" select="' van'" />
	<xsl:param name="RP_ElectionNameSimple_van_eilandsraad" select="' van het openbaar lichaam'" />
	<xsl:param name="RP_ElectionNameSimple_van_stadsdeel" select="' van stadsdeel'" />

	<xsl:param name="RP_WaterschapTypeName_Waterschap" select="'waterschap'" />
	<xsl:param name="RP_WaterschapTypeName_Wetterskip" select="'wetterskip'" />
	<xsl:param name="RP_WaterschapTypeName_Hoogheemraadschap" select="'hoogheemraadschap'" />
	<xsl:param name="RP_WaterschapTypeName_Hoogheemraadschap_van" select="'hoogheemraadschap van'" />

	<xsl:param name="RP_ElectionNameAcceptance_tweedeKamer" select="'de Tweede Kamer der Staten-Generaal'" />
	<xsl:param name="RP_ElectionNameAcceptance_eersteKamer" select="'de Eerste Kamer der Staten-Generaal'" />
	<xsl:param name="RP_ElectionNameAcceptance_provincialeStaten" select="'provinciale staten van'" />
	<xsl:param name="RP_ElectionNameAcceptance_algemeen_bestuur" select="'het algemeen bestuur van het '" />
	<xsl:param name="RP_ElectionNameAcceptance_raadVanDeGemeente" select="'de raad van de gemeente'" />
	<xsl:param name="RP_ElectionNameAcceptance_eilandsraad" select="'de eilandsraad van het openbaar lichaam'" />
	<xsl:param name="RP_ElectionNameAcceptance_bestuurscommissie" select="'de bestuurscommissie'" />
	<xsl:param name="RP_ElectionNameAcceptance_gebiedscommissie" select="'de gebiedscommissie'" />

	<xsl:param name="RP_ElectionNameAcceptanceShort_tweedeKamer" select="'de Tweede Kamer'" />
	<xsl:param name="RP_ElectionNameAcceptanceShort_eersteKamer" select="'de Eerste Kamer'" />
	<xsl:param name="RP_ElectionNameAcceptanceShort_provincialeStaten" select="'provinciale staten'" />
	<xsl:param name="RP_ElectionNameAcceptanceShort_algemeen_bestuur" select="'het algemeen bestuur'" />
	<xsl:param name="RP_ElectionNameAcceptanceShort_raadVanDeGemeente" select="'de raad'" />
	<xsl:param name="RP_ElectionNameAcceptanceShort_eilandsraad" select="'de eilandsraad'" />
	<xsl:param name="RP_ElectionNameAcceptanceShort_bestuurscommissie" select="'de bestuurscommissie'" />
	<xsl:param name="RP_ElectionNameAcceptanceShort_gebiedscommissie" select="'de gebiedscommissie'" />

	<xsl:param name="RP_ObjectionsByVoters1_aanwezigeKiezers" select="'Door de in de zittingsruimte aanwezige kiezers zijn'" />

	<xsl:param name="RP_ObjectionsByVoters2_emptyLine" select="'.......................................................................................................................................'" />

	<xsl:param name="RP_ObjectionsByVoters2a_emptyLine" select="'.......................................................................................................................................'" />

	<xsl:param name="RP_ObjectionsByVoters1b_geenBezwarenIngebracht" select="'□ geen bezwaren ingebracht.'" />
	<xsl:param name="RP_ObjectionsByVoters1b_iconChecked" select="'■'" />
	<xsl:param name="RP_ObjectionsByVoters1b_iconUnchecked" select="'□'" />
	<xsl:param name="RP_ObjectionsByVoters1b_volgendeBezwarenIngebracht" select="' de volgende bezwaren ingebracht:'" />

	<xsl:param name="RP_ObjectionsReference_aanwezigeKiezers" select="'Door de in de zittingsruimte aanwezige kiezers zijn'" />
	<xsl:param name="RP_ObjectionsReference_iconUnchecked" select="'□ '" />
	<xsl:param name="RP_ObjectionsReference_geenBezwarenIngebracht" select="'geen bezwaren ingebracht.'" />
	<xsl:param name="RP_ObjectionsReference_volgendeBezwarenIngebracht" select="'de volgende bezwaren ingebracht (zie gewaarmerkte bijlage voor bezwaren en opmerkingen daarover van het '" />

	<xsl:param name="RP_LegacyFooter_pagina" select="'pagina '" />
</xsl:stylesheet>
