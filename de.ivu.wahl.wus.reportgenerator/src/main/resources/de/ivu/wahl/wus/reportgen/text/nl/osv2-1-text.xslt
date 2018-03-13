<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:param name="o2-1_010_Aantekenen" select="'AANTEKENEN'"/>
	<xsl:param name="o2-1_020_Onderwerp" select="'Onderwerp'"/>
	<xsl:param name="o2-1_030_title" select="'Verzuim kandidatenlijst voor de verkiezing van de leden van '"/>
	<xsl:param name="o2-1_040_Geachte" select="'Geachte heer/mevrouw '"/>
	<xsl:param name="o2-1_050_01_verzuimenMeedelen" select="'Hierbij deel ik u mede dat het '"/>
	<xsl:param name="o2-1_050_02_verzuimenMeedelen" select="'centraal stembureau'"/>
	<xsl:param name="o2-1_050_05_verzuimenMeedelen" select="' voor de verkiezing van de leden van '"/>
	<xsl:param name="o2-1_050_06_verzuimenMeedelen" select="' in zijn vergadering van heden een of meerdere verzuimen heeft geconstateerd met betrekking tot de kandidatenlijst '"/>
	<xsl:param name="o2-1_050_07_verzuimenMeedelen" select="'voor kieskring '"/>
	<xsl:param name="o2-1_050_07_EK_verzuimenMeedelen" select="'voor de provincie '"/>
	<xsl:param name="o2-1_050_08_verzuimenMeedelen" select="'voor alle kieskringen '"/>
	<xsl:param name="o2-1_050_08_EK_alle_verzuimenMeedelen" select="'voor alle provincies '"/>
	<xsl:param name="o2-1_050_08_EK_verzuimenMeedelen" select="'voor de provincies '"/>
	<xsl:param name="o2-1_050_09_verzuimenMeedelen" select="'met de aanduiding '"/>
	<xsl:param name="o2-1_050_10_verzuimenMeedelen" select="'met als eerste kandidaat '"/>
	<xsl:param name="o2-1_060_1_HetBetreft" select="'Het betreft het volgende verzuim:'"/>
	<xsl:param name="o2-1_060_2_HetBetreft" select="'Het betreft de volgende verzuimen:'"/>
	<xsl:param name="o2-1_080_1_verzuimenHerstellen" select="'Dit verzuim kan/deze verzuimen kunnen door de inleveraar van de kandidatenlijst of door een van de andere personen die op de kandidatenlijst gemachtigd zijn om bij verhindering van de inleveraar verzuimen te herstellen, worden hersteld tot en met '"/>
	<xsl:param name="o2-1_080_2_verzuimenHerstellen" select="' (uiterlijk de derde dag na de zitting van het centraal stembureau tot het onderzoeken van de kandidatenlijsten, op de 1e en 2e dag van 9-17 uur en op de 3e dag van 9-15 uur), '"/>
	<xsl:param name="o2-1_080_2_EK_verzuimenHerstellen" select="' (uiterlijk de derde dag na de zitting van het centraal stembureau tot het onderzoeken van de kandidatenlijsten van 9-17 uur), '"/>
	<xsl:param name="o2-1_080_3_verzuimenHerstellen" select="'op het secretariaat van de Kiesraad, Herengracht 21 te ’s-Gravenhage (of op het bestuurskantoor van het openbaar lichaam waar de kandidatenlijst is ingeleverd)'"/>
	<xsl:param name="o2-1_080_3_EK_verzuimenHerstellen" select="'op het secretariaat van de Kiesraad, Herengracht 21 te ’s-Gravenhage'"/>
	<xsl:param name="o2-1_080_4_verzuimenHerstellen" select="'ter secretarie van de gemeente waar het centraal stembureau is gevestigd'"/>
	<xsl:param name="o2-1_080_5_verzuimenHerstellen" select="'op het bestuurskantoor waar de kandidatenlijst is ingeleverd'"/>
	<xsl:param name="o2-1_080_6_AB_verzuimenHerstellen" select="'bij het centraal stembureau van het waterschap'"/>
	<xsl:param name="o2-1_090_contactOpTeNemen" select="'Ik verzoek u van tevoren contact op te nemen (contactgegevens rechtsboven), over het tijdstip waarop degene die het verzuim/de verzuimen komt herstellen verwacht te komen.'"/>
	<xsl:param name="o2-1_100_Hoogachtend" select="'Hoogachtend,'"/>
	<xsl:param name="o2-1_110_DeKiesraad" select="'DE KIESRAAD,'"/>
	<xsl:param name="o2-1_120_voorDeze" select="'voor deze'"/>
	<xsl:param name="o2-1_130_voorzitterHoofdstembureau" select="'De voorzitter van het centraal stembureau,'"/>
	<xsl:param name="o2-1_140_WegensTechnischeRedenen" select="'Wegens technische redenen wordt dit document gegenereerd.'"/>
	<xsl:param name="o2-1_150_geenVerzuimen" select="'Verzuimbrieven: Er zijn geen verzuimen ingevoerd.'"/>
</xsl:stylesheet>
