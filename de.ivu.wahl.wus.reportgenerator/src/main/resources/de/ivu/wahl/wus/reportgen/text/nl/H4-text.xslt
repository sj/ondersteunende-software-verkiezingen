<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:param name="H4_010_pretitle" select="'Model H 4'"/>
	<xsl:param name="H4_010_title" select="'Ondersteuningsverklaring'"/>
	<xsl:param name="H4_020_MetDitFormulier" select="'Met dit formulier verklaart u dat u een kandidatenlijst ondersteunt van een politieke groepering. Dit betekent dat u de deelname van de betreffende groepering aan de verkiezing mogelijk maakt. Deze verklaring wordt ter inzage gelegd.'"/>
	<xsl:param name="H4_020_MetDitFormulier_BC" select="'Met dit formulier verklaart u dat u een kandidatenlijst ondersteunt van een (politieke) groepering. Dit betekent dat u de deelname van de betreffende groepering aan de verkiezing mogelijk maakt. Deze verklaring wordt ter inzage gelegd.'"/>
	<xsl:param name="H4_040_LetOp" select="'Let op!'"/>
	<xsl:param name="H4_050_UMagZich" select="'U mag zich niet laten omkopen tot het afleggen van deze ondersteuningsverklaring. Degene die u omkoopt of u hiertoe anderszins dwingt, is tevens strafbaar. Op beide misdrijven staat een gevangenisstraf van maximaal zes maanden of een geldboete.'"/>
	<xsl:param name="H4_060_PolitiekeGroepering" select="'Politieke groepering'"/>
	<xsl:param name="H4_060_PolitiekeGroepering_BC" select="'(Politieke) groepering'"/>
	<xsl:param name="H4_070_DePolitiekeGroeperingUOndersteunt" select="'De aanduiding van de politieke groepering waarvan u de kandidatenlijst ondersteunt: '"/>
	<xsl:param name="H4_070_DePolitiekeGroeperingUOndersteunt_BC" select="'De aanduiding van de (politieke) groepering waarvan u de kandidatenlijst ondersteunt: '"/>
	<xsl:param name="H4_080_KandidatenOpDeLijst" select="'Kandidaten op de lijst'"/>
	<xsl:param name="H4_100_Ondertekening" select="'Ondertekening door de kiezer'"/>
	<xsl:param name="H4_110_IkVerklaar" select="'Ik verklaar dat ik de bovengenoemde kandidatenlijst ondersteun.'"/>
	<xsl:param name="H4_120_Datum" select="'Datum'"/>
	<xsl:param name="H4_130_Naam" select="'Naam'"/>
	<xsl:param name="H4_140_Handtekening" select="'Handtekening'"/>
	<xsl:param name="H4_146_VerklaringVanDe" select="'Verklaring van de '"/>
	<xsl:param name="H4_148_nietEK" select="'(niet bij de Eerste Kamerverkiezing)'"/>
	<xsl:param name="H4_150_1_DeBurgemeesterVerklaart" select="'De '"/>
	<xsl:param name="H4_150_2_DeBurgemeesterVerklaart" select="' van '"/>
	<xsl:param name="H4_150_3_DeBurgemeesterVerklaart" select="' verklaart dat de ondersteuner in zijn '"/>
	<xsl:param name="H4_150_4_DeBurgemeesterVerklaart" select="' als kiezer is geregistreerd.'"/>
	<xsl:param name="H4_150_5_stadsdeel" select="'stadsdeel '"/>
	<xsl:param name="H4_155_DeBurgemeesterVerklaart_GC" select="'De burgemeester van Rotterdam verklaart dat de ondersteuner in het betreffende gebied als kiezer is geregistreerd.'"/>

	<xsl:param name="H4_091_Burgemeester" select="'burgemeester'"/>
	<xsl:param name="H4_092_Gezaghebber" select="'gezaghebber'"/>
	<xsl:param name="H4_093_Voorzitter" select="'voorzitter'"/>
	<xsl:param name="H4_101_Gemeente" select="'gemeente'"/>
	<xsl:param name="H4_102_OpenbaarLichaam" select="'openbaar lichaam'"/>
	<xsl:param name="H4_103_stadsdeel" select="'stadsdeel'"/>

	<xsl:param name="H4_160_kiezerBehoort" select="'De kiezer behoort tot kieskring'"/>
	<xsl:param name="H4_170_Datum" select="'Datum'"/>
	<xsl:param name="H4_180_OndertekeningGemeentestempel" select="'Ondertekening of gemeentestempel'"/>
</xsl:stylesheet>
