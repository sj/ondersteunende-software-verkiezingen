<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:param name="RP_ChapterElection_Verkiezing" select="'Ferkiezing'" />
	<xsl:param name="RP_ChapterElection_HetGaatOmVerkiezing" select="'It giet om de ferkiezing fan:'" />
	<xsl:param name="RP_ChapterElection_HetGaatOmVerkiezingVanLeden" select="'It giet om de ferkiezing fan:'" />
	<xsl:param name="RP_ChapterElection_HetGaatOmKandidatenlijst" select="'It giet om de kandidatelist foar de ferkiezing fan:'" />
	<xsl:param name="RP_ChapterElection_VoorDeProvincie" select="' voor de provincie '" />

	<xsl:param name="RP_ChapterPartyName_AanduidingVan" select="'De politike groepearring'" />
	<xsl:param name="RP_ChapterPartyName_AanduidingVan_H3" select="'Oantsjutting fan de politike groepearring'" />
	<xsl:param name="RP_ChapterPartyName_AanduidingVan_BC" select="'De (politike) groepearring'" />
	<xsl:param name="RP_ChapterPartyName_AanduidingVan_BC_H3" select="'Oantsjutting fan de (politike) groepearring'" />
	<xsl:param name="RP_ChapterPartyName_AanduidingBoven" select="'De politike groepearring dêr’t jo de kandidatelist fan stypje '" />
	<xsl:param name="RP_ChapterPartyName_geregistreerdeAanduiding" select="'De registrearre oantsjutting fan de politike groepearring: '" />
	<xsl:param name="RP_ChapterPartyName_samengevoegdeAanduiding" select="'De gearfoege oantsjutting fan de politike groepearrings: '" />
	<xsl:param name="RP_ChapterPartyName_Kieskringen" select="'Kiesrûnten'" />

	<xsl:param name="RP_ChapterDistrictTitle_Kieskringen" select="'Kiesrûnten'" />
	<xsl:param name="RP_ChapterDistrictTitle_alleenInvullen" select="'(allinnich ynfolje by de ferkiezing fan de Twadde Keamer, Earste Keamer of de provinsjale steaten fan in provinsje mei mear as ien kiesrûnte)'" />

	<xsl:param name="RP_ChapterDistrictOptions_uitsluitend" select="'uitsluitend voor de volgende kieskring(en): '"/>
	<xsl:param name="RP_ChapterDistrictOptions_voor" select="'voor '"/>
	<xsl:param name="RP_ChapterDistrictOptions_alleKieskringen" select="'alle kieskringen'"/>
	<xsl:param name="RP_ChapterDistrictOptions_waarvoorIngeleverd" select="' waarvoor de kandidatenlijst wordt ingeleverd.'"/>

	<xsl:param name="RP_CandidatesOnListTable-naam" select="'namme'" />
	<xsl:param name="RP_CandidatesOnListTable-voorletters" select="'foarletters'" />
	<xsl:param name="RP_CandidatesOnListTable-geboortedatum" select="'bertedatum'" />
	<xsl:param name="RP_CandidatesOnListTable-woonplaats" select="'wenplak'" />
	
	<xsl:param name="RP_ElectionName_verkiezingLeden" select="'de ferkiezing fan de leden fan '" />
	<xsl:param name="RP_ElectionName_referendumOver" select="'it referendum oer '" />
	<xsl:param name="RP_ElectionName_op" select="' op '" />

	<xsl:param name="RP_ElectionNameP2_verkiezingLeden" select="'ferkiezing fan de leden fan '" />

	<xsl:param name="RP_Salutation_dhr" select="'dhr. '" />
	<xsl:param name="RP_Salutation_mevr" select="'mfr. '" />

	<xsl:param name="RP_GenderP1_m" select="' (m)'" />
	<xsl:param name="RP_GenderP1_v" select="' (f)'" />
	<xsl:param name="RP_GenderP1_unknown" select="' (f)'" />

	<xsl:param name="RP_Gender_m" select="' (m)'" />
	<xsl:param name="RP_Gender_v" select="' (v)'" />
	<xsl:param name="RP_Gender_unknown" select="' (f)'" />

	<xsl:param name="RP_Gender2_m" select="' (m)'" />
	<xsl:param name="RP_Gender2_v" select="' (v)'" />
	<xsl:param name="RP_Gender2_unknown" select="' (f)'" />

	<xsl:param name="RP_Gender3_hij" select="'hy'" />
	<xsl:param name="RP_Gender3_zij" select="'sy'" />
	<xsl:param name="RP_Gender3_hijZij" select="'hy/sy'" />
	<xsl:param name="RP_Gender3_unknown" select="'sy'" />

	<xsl:param name="RP_Gender4_him" select="'him'" />
	<xsl:param name="RP_Gender4_har" select="'har'" />
	<xsl:param name="RP_Gender4_himHar" select="'him/har'" />
	<xsl:param name="RP_Gender4_unknown" select="'har'" />

	<xsl:param name="RP_Date_januari" select="'jannewaris'" />
	<xsl:param name="RP_Date_februari" select="'febrewaris'" />
	<xsl:param name="RP_Date_maart" select="'maart'" />
	<xsl:param name="RP_Date_april" select="'april'" />
	<xsl:param name="RP_Date_mei" select="'maaie'" />
	<xsl:param name="RP_Date_juni" select="'juny'" />
	<xsl:param name="RP_Date_juli" select="'july'" />
	<xsl:param name="RP_Date_augustus" select="'augustus'" />
	<xsl:param name="RP_Date_september" select="'septimber'" />
	<xsl:param name="RP_Date_oktober" select="'oktober'" />
	<xsl:param name="RP_Date_november" select="'novimber'" />
	<xsl:param name="RP_Date_december" select="'desimber'" />

	<xsl:param name="RP_PartyName_blancoLijst" select="'net fan tapassing (blanko list)'" />

	<xsl:param name="RP_PartyName2_blancoLijst" select="'blanko list mei as earste kandidaat'" />

	<xsl:param name="RP_PartyNameInOmissions_blancoLijst" select="'blanko list mei as earste kandidaat '" />

	<xsl:param name="RP_PartyNameInOmissionsEK_blancoLijst" select="'blanko list mei as earste kandidaat '" />

	<xsl:param name="RP_PartyNameOrFirstCandidate_blancoLijst" select="'blanko list mei as earste kandidaat '" />

	<xsl:param name="RP_Kieskring_inKieskring" select="' yn kiesrûnte '" />

	<xsl:param name="RP_KieskringParty_inKieskring" select="' yn kiesrûnte'" />
	<xsl:param name="RP_KieskringParty_inAlleKieskringen" select="' yn alle kiesrûnten'" />

	<xsl:param name="RP_FootnoteKieskring_voetnoten" select="'Voetnoten:'" />
	<xsl:param name="RP_FootnoteKieskring_alleenInTeVullen" select="'Allinnich yn te foljen as it om in ferkiezing giet fan de leden fan de Twadde Keamer of fan provinsjale steaten fan in provinsje dy’t út mear as ien kiesrûnte bestiet. Efter ‘kiesrûnte’ it nûmer fan de kiesrûnte en tusken heakjes de namme fan de gemeente dêr’t it haadstimburo fêstige is oanjaan.'" />
	
	<xsl:param name="RP_Checkbox_iconChecked" select="'■'" />
	<xsl:param name="RP_Checkbox_iconUnchecked" select="'□'" />

	<xsl:param name="RP_FooterWithoutHashcode_concept" select="'(Konsept) '" />
	<xsl:param name="RP_FooterWithoutHashcode_datum" select="'Datum: '" />

	<xsl:param name="RP_FooterWithHashcode_concept" select="'(Konsept) | '" />
	<xsl:param name="RP_FooterWithHashcode_hashcode" select="'SHA-256-Hashcode: '" />

	<xsl:param name="RP_CombinedList_en" select="'en '" />

	<xsl:param name="RP_CombinedList2_en" select="'en '" />
	<xsl:param name="RP_CombinedList2_lijst" select="' (list '" />

	<xsl:param name="RP_CombinedListListName_blancoLijst" select="'blanko list mei as earste kandidaat '" />

	<xsl:param name="RP_SignatureBlock_plaats" select="'Plak: ........................................................'" />
	<xsl:param name="RP_SignatureBlock_datum" select="'Datum: .......................................................'" />
	<xsl:param name="RP_SignatureBlock_voorzitter" select="'......................................................................, Foarsitter'" />
	<xsl:param name="RP_SignatureBlock_leden" select="'...................................................................... Leden'" />
	<xsl:param name="RP_SignatureBlock_emptyLine" select="'......................................................................'" />

	<xsl:param name="RP_SignatureBlock-3_plaats" select="'Plak: ........................................................'" />
	<xsl:param name="RP_SignatureBlock-3_datum" select="'Datum: .......................................................'" />
	<xsl:param name="RP_SignatureBlock-3_voorzitter" select="'............................................., Foarsitter'" />
	<xsl:param name="RP_SignatureBlock-3_leden" select="'............................................. Leden'" />
	<xsl:param name="RP_SignatureBlock-3_emptyLine" select="'.............................................'" />

	<xsl:param name="RP_ElectionNameSimple_de" select="'de '" />
	<xsl:param name="RP_ElectionNameSimple_het" select="'it '" />

	<xsl:param name="RP_ElectionNameSimple_tweedeKamer" select="'Twadde Keamer fan de Steaten-Generaal'" />
	<xsl:param name="RP_ElectionNameSimple_eersteKamer" select="'Earste Keamer fan de Steaten-Generaal'" />
	<xsl:param name="RP_ElectionNameSimple_provincialeStaten" select="'provinsjale steaten'" />
	<xsl:param name="RP_ElectionNameSimple_algemeen_bestuur" select="'algemien bestjoer fan it '" />
	<xsl:param name="RP_ElectionNameSimple_hetEuropeesParlement" select="'Europeesk Parlemint'" />
	<xsl:param name="RP_ElectionNameSimple_raadVanDeGemeente" select="'gemeenterie'" />
	<xsl:param name="RP_ElectionNameSimple_eilandsraad" select="'eilânsrie'" />
	<xsl:param name="RP_ElectionNameSimple_bestuurscommissie" select="'algemeen bestuur van de bestuurscommissie'" />
	<xsl:param name="RP_ElectionNameSimple_gebiedscommissie" select="'gebiedscommissie'" />

	<xsl:param name="RP_ElectionNameSimple_deelraad" select="'deelraad - deprecated'" />
	<xsl:param name="RP_ElectionNameSimple_deelgemeenteraad" select="'deelgemeenteraad - deprecated'" />

	<xsl:param name="RP_ElectionNameSimple_van" select="' fan'" />
	<xsl:param name="RP_ElectionNameSimple_van_eilandsraad" select="' fan it iepenbier lichem'" />
	<xsl:param name="RP_ElectionNameSimple_van_stadsdeel" select="' fan stadsdeel'" />

	<xsl:param name="RP_WaterschapTypeName_Waterschap" select="'wetterskip'" />
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

	<xsl:param name="RP_LegacyFooter_pagina" select="'side '" />
</xsl:stylesheet>
