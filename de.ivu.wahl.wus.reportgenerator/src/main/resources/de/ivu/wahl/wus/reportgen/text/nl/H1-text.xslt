<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:param name="H1_010_pretitle" select="'Model H 1'"/>
	<xsl:param name="H1_010_title" select="'Kandidatenlijst'"/>
	<xsl:param name="H1_020_MetDitFormulier" select="'Met dit formulier stelt u, als inleveraar van de kandidatenlijst, kandidaten verkiesbaar voor een verkiezing.'"/>

	<xsl:param name="H1_022_kandidatenlijstWordtIngeleverd" select="'De kandidatenlijst wordt ingeleverd voor:'" />
	<xsl:param name="H1_024_volgendeKieskringen" select="'de volgende kieskring(en): '" />
	<xsl:param name="H1_026_alleKieskringen" select="'alle kieskringen.'" />

	<xsl:param name="H1_030_KandidatenOpDeLijst" select="'Kandidaten op de lijst'"/>
	<xsl:param name="H1_040_Vervangers" select="'Vervanger(s) voor het herstel van verzuimen'"/>

	<xsl:param name="H1_100_InTeLeveren" select="'In te leveren bij de kandidatenlijst'"/>
	<xsl:param name="H1_110_IkBenVerplicht" select="'Ik ben verplicht de volgende bijlag(e) in te leveren bij de kandidatenlijst (aanvinken wat van toepassing is):'"/>
	<xsl:param name="H1_120_modelH3" select="'Een verklaring van de gemachtigde(n) van de politieke groepering(en) waarmee aan mij toestemming wordt gegeven om de aanduiding boven de kandidatenlijst te plaatsen, want ik heb een aanduiding boven de lijst geplaatst (model H 3-1 of H 3-2).'"/>
	<xsl:param name="H1_120_modelH3_BC" select="'Een verklaring van de gemachtigde(n) van de (politieke) groepering(en) waarmee aan mij toestemming wordt gegeven om de aanduiding boven de kandidatenlijst te plaatsen, want ik heb een aanduiding boven de lijst geplaatst (model H 3-1 of H 3-2).'"/>
	<xsl:param name="H1_130_modelH4" select="'Verklaringen van kiezers dat zij de lijst ondersteunen, want de lijst komt niet in aanmerking voor de ontheffing van deze verplichting (model H 4).'"/>
	<xsl:param name="H1_140_modelH9" select="'Een verklaring van iedere op de lijst voorkomende kandidaat dat hij instemt met zijn kandidaatstelling op de lijst (model H 9).'"/>
	<xsl:param name="H1_150_modelIdentiteitsbewijs" select="'Een kopie van een geldig identiteitsbewijs van iedere kandidaat die géén zitting heeft in het orgaan waarvoor de verkiezing wordt gehouden.'"/>
	<xsl:param name="H1_160_modelH12" select="'Een betalingsbewijs van de waarborgsom, want de lijst komt niet in aanmerking voor de ontheffing van deze verplichting (model H 12).'"/>
	<xsl:param name="H1_170_model_verklaring_voorgenomen_vestiging"    select="'Een verklaring van voorgenomen vestiging van iedere op de lijst voorkomende kandidaat die niet woonachtig is in het gebied waarop de verkiezing betrekking heeft (alleen bij een verkiezing van provinciale staten, het algemeen bestuur van een waterschap, een gemeenteraad en de eilandsraden van de openbare lichamen Bonaire, Saba of Sint Eustatius).'"/>
	<xsl:param name="H1_170_model_verklaring_voorgenomen_vestiging_BC" select="'Een verklaring van voorgenomen vestiging van iedere op de lijst voorkomende kandidaat die niet woonachtig is in het gebied waarop de verkiezing betrekking heeft (alleen bij een verkiezing van het algemeen bestuur van de bestuurscommissie, provinciale staten, het algemeen bestuur van een waterschap, een gemeenteraad en de eilandsraden van de openbare lichamen Bonaire, Saba of Sint Eustatius).'"/>
	<xsl:param name="H1_180_modelY13" select="'Een verklaring van iedere op de lijst voorkomende  kandidaat dat hij niet in een andere lidstaat kandidaat zal zijn voor het Europees Parlement (model Y 13).'"/>
	<xsl:param name="H1_190_modelY35" select="'Een verklaring van kandidaten die onderdaan zijn van een andere lidstaat, dat zij in die lidstaat niet zijn uitgesloten van het recht om gekozen te worden voor de verkiezingen van het Europees Parlement (model Y 35).'"/>

	<xsl:param name="H1_200_Ondertekening" select="'Ondertekening door de inleveraar'"/>
	<xsl:param name="H1_205_NaamEnVoorletters" select="'Naam en voorletters'"/>
	<xsl:param name="H1_210_PostadresPostcodePlaats" select="'Postadres, postcode en plaats'"/>
	<xsl:param name="H1_220_Datum" select="'Datum'"/>
	<xsl:param name="H1_230_Handtekening" select="'Handtekening'"/>
	
	<xsl:param name="H1_300_AgentLabel-naam" select="'naam'"/>
	<xsl:param name="H1_310_AgentLabel-voorletters" select="'voorletters'"/>
	<xsl:param name="H1_320_AgentLabel-postadres" select="'postadres'"/>
	<xsl:param name="H1_330_AgentLabel-postcode" select="'postcode'"/>
	<xsl:param name="H1_340_AgentLabel-plaats" select="'plaats'"/>
</xsl:stylesheet>
