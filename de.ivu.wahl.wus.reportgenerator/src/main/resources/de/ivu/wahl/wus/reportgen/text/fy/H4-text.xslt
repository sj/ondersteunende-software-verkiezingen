<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:param name="H4_010_pretitle" select="'Model H 4'"/>
	<xsl:param name="H4_010_title" select="'Stipeferklearring'"/>
	<xsl:param name="H4_020_MetDitFormulier" select="'Mei dit formulier ferklearje jo dat jo in kandidatelist fan in politike groepearring stypje. Dat betsjut dat jo de dielname fan de oanbelangjende groepearring oan de ferkiezing mooglik meitsje. Dizze ferklearring wurdt op ynsjen lein.'"/>
	<xsl:param name="H4_020_MetDitFormulier_BC" select="'Met dit formulier verklaart u dat u een kandidatenlijst ondersteunt van een (politieke) groepering. Dit betekent dat u de deelname van de betreffende groepering aan de verkiezing mogelijk maakt. Deze verklaring wordt ter inzage gelegd.'"/>
	<xsl:param name="H4_040_LetOp" select="'Tink der om!'"/>
	<xsl:param name="H4_050_UMagZich" select="'Jo meie jo net omkeapje litte ta it ôflizzen fan dizze stipeferklearring. Dejinge dy’t jo omkeapet of jo dêrta op oare wize twingt, is tagelyk strafber. Op beide misdriuwen stiet in finzenisstraf fan maksimaal seis moannen of in jildboete.'"/>
	<xsl:param name="H4_060_PolitiekeGroepering" select="'Politike groepearring'"/>
	<xsl:param name="H4_060_PolitiekeGroepering_BC" select="'(Politieke) groepering'"/>
	<xsl:param name="H4_070_DePolitiekeGroeperingUOndersteunt" select="'De oantsjutting fan de politike groepearring dêr’t jo de kandidatelist fan stypje: '"/>
	<xsl:param name="H4_070_DePolitiekeGroeperingUOndersteunt_BC" select="'De aanduiding van de (politieke) groepering waarvan u de kandidatenlijst ondersteunt: '"/>
	<xsl:param name="H4_080_KandidatenOpDeLijst" select="'Kandidaten op de list'"/>
	<xsl:param name="H4_100_Ondertekening" select="'Undertekening troch de kiezer'"/>
	<xsl:param name="H4_110_IkVerklaar" select="'Ik ferklearje dat ik de boppeneamde kandidatelist stypje.'"/>
	<xsl:param name="H4_120_Datum" select="'Datum'"/>
	<xsl:param name="H4_130_Naam" select="'Namme'"/>
	<xsl:param name="H4_140_Handtekening" select="'Hantekening'"/>
	<xsl:param name="H4_146_VerklaringVanDe" select="'Ferklearring fan de '"/>
	<xsl:param name="H4_148_nietEK" select="''"/>
	<xsl:param name="H4_150_1_DeBurgemeesterVerklaart" select="'De '"/>
	<xsl:param name="H4_150_2_DeBurgemeesterVerklaart" select="' fan '"/>
	<xsl:param name="H4_150_3_DeBurgemeesterVerklaart" select="' ferklearret dat de stiper yn syn '"/>
	<xsl:param name="H4_150_4_DeBurgemeesterVerklaart" select="' as kiezer registrearre is.'"/>
	<xsl:param name="H4_150_5_stadsdeel" select="'stadsdeel '"/>
	<xsl:param name="H4_155_DeBurgemeesterVerklaart_GC" select="'De burgemeester van Rotterdam verklaart dat de ondersteuner in het betreffende gebied als kiezer is geregistreerd.'"/>

	<xsl:param name="H4_091_Burgemeester" select="'boargemaster'"/>
	<xsl:param name="H4_092_Gezaghebber" select="'gezaghebber'"/>
	<xsl:param name="H4_093_Voorzitter" select="'voorzitter'"/>
	<xsl:param name="H4_101_Gemeente" select="'gemeente'"/>
	<xsl:param name="H4_102_OpenbaarLichaam" select="'openbaar lichaam'"/>
	<xsl:param name="H4_103_stadsdeel" select="'stadsdeel'"/>

	<xsl:param name="H4_160_kiezerBehoort" select="'De kiezer heart ta kiesrûnte'"/>
	<xsl:param name="H4_170_Datum" select="'Datum'"/>
	<xsl:param name="H4_180_OndertekeningGemeentestempel" select="'Undertekening of gemeentestimpel'"/>
</xsl:stylesheet>
