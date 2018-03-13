<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:param name="I1_010_pretitle" select="'Model I 1'"/>
	<xsl:param name="I1_020_title" select="'Proces-verbaal van het onderzoek naar de kandidatenlijsten'"/>
	<xsl:param name="I1_030_MetDitFormulier" select="'Met dit formulier doet het centraal stembureau verslag van het onderzoek naar de kandidatenlijsten.'"/>
	<xsl:param name="I1_040_Zitting" select="'Zitting'"/>
	<xsl:param name="I1_050_HetBetreft" select="'Het betreft de zitting van het centraal stembureau in '"/>
	<xsl:param name="I1_060_DatumZitting" select="'Datum zitting'"/>
	<xsl:param name="I1_070_TijdstipZitting" select="'Tijdstip zitting'"/>
	<xsl:param name="I1_080_Kandidatenlijsten" select="'Kandidatenlijsten'"/>
	<xsl:param name="I1_090_DeVolgendeKandidatenlijsten" select="'De volgende kandidatenlijsten zijn ingeleverd:'"/>
	<xsl:param name="I1_100_Kieskring" select="'Kieskring'"/>
	<xsl:param name="I1_105_KieskringNederland" select="'Kieskring Nederland'"/>
	<xsl:param name="I1_110_aanduidingBovenKandidatenlijst" select="'aanduiding boven kandidatenlijst'"/>
	<xsl:param name="I1_120_eersteKandidaat" select="'naam en voorletters eerste kandidaat'"/>
	<xsl:param name="I1_130_aantalNamen" select="'aantal namen op lijst'"/>
	<xsl:param name="I1_140_GeconstateerdeVerzuimen" select="'Geconstateerde verzuimen '"/>
	<xsl:param name="I1_150_BijHetOnderzoek" select="'Bij het onderzoek naar de kandidatenlijsten zijn:'"/>
	<xsl:param name="I1_160_geenVerzuimen" select="' geen herstelbare verzuimen geconstateerd.'"/>
	<xsl:param name="I1_170_deVolgende" select="' de volgende herstelbare verzuimen geconstateerd:'"/>
	<xsl:param name="I1_180_Aanduiding" select="'Aanduiding in de kieskring(en)'"/>
	<xsl:param name="I1_190_omschrijvingVerzuim" select="'omschrijving verzuim'"/>
	<xsl:param name="I1_200_Ondertekening" select="'Ondertekening'"/>
	<xsl:param name="I1_210_Datum" select="'Datum'"/>
	<xsl:param name="I1_220_NaamEnHandtekeningVoorzitter" select="'Naam en handtekening voorzitter'"/>
	<xsl:param name="I1_230_NaamEnHandtekeningLeden" select="'Naam en handtekening leden'"/>
</xsl:stylesheet>
