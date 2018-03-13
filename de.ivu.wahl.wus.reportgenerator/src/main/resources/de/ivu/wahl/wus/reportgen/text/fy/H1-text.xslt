<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:param name="H1_010_pretitle" select="'Model H 1'"/>
	<xsl:param name="H1_010_title" select="'Kandidatelist'"/>
	<xsl:param name="H1_020_MetDitFormulier" select="'Mei dit formulier stelle jo, as dejinge dy’t de kandidatelist ynleveret, kandidaten ferkiesber foar in ferkiezing.'"/>

	<xsl:param name="H1_022_kandidatenlijstWordtIngeleverd" select="'De kandidatenlijst wordt ingeleverd voor:'" />
	<xsl:param name="H1_024_volgendeKieskringen" select="'de volgende kieskring(en): '" />
	<xsl:param name="H1_026_alleKieskringen" select="'alle kieskringen.'" />

	<xsl:param name="H1_030_KandidatenOpDeLijst" select="'Kandidaten op de list'"/>
	<xsl:param name="H1_040_Vervangers" select="'Ferfanger(s) foar it ferhelpen fan fersommen'"/>

	<xsl:param name="H1_100_InTeLeveren" select="'Yn te leverjen by de kandidatelist'"/>
	<xsl:param name="H1_110_IkBenVerplicht" select="'Ik bin ferplichte de neikommende taheakke by de kandidatelist yn te leverjen (oanfinke wat fan tapassing is):'"/>
	<xsl:param name="H1_120_modelH3"    select="'In ferklearring fan de lêsthawwer(s) fan de politike groepearring(s) dêr’t my tastimming mei jûn wurdt om de oantsjutting boppe de kandidatelist te pleatsen, want ik haw in oantsjutting boppe de list pleatst (model H 3-1 of H 3-2).'"/>
	<xsl:param name="H1_120_modelH3_BC" select="'In ferklearring fan de lêsthawwer(s) fan de politike groepearring(s) dêr’t my tastimming mei jûn wurdt om de oantsjutting boppe de kandidatelist te pleatsen, want ik haw in oantsjutting boppe de list pleatst (model H 3-1 of H 3-2).'"/>
	<xsl:param name="H1_130_modelH4" select="'Ferklearrings fan kiezers dat hja de list stypje, want de list komt net yn oanmerking foar de ûntheffing fan dy ferplichtings (model H 4).'"/>
	<xsl:param name="H1_140_modelH9" select="'In ferklearring fan alle op de list foarkommende kandidaten dat se ynstimme mei harren kandidaatstelling op de list (model H 9).'"/>
	<xsl:param name="H1_150_modelIdentiteitsbewijs" select="'In kopy fan in jildich identiteitsbewiis fan alle kandidaten dy’t gjin sit hawwe yn it orgaan dêr’t de ferkiezing foar hâlden wurdt.'"/>
	<xsl:param name="H1_160_modelH12" select="'In betellingsbewiis fan de boarchsom, want de list komt net yn oanmerking foar de ûntheffing fan dy ferplichting (model H 12).'"/>
	<xsl:param name="H1_170_model_verklaring_voorgenomen_vestiging"    select="'In ferklearring fan foarnommen fêstiging foar alle op de list foarkommende kandidaten dy’t net wenjend binne yn it gebiet dêr’t de ferkiezing op slacht (allinnich by in ferkiezing fan provinsjale steaten, it algemien bestjoer fan in wetterskip, in gemeenteried en de eilânrieden fan it iepenbiere lichem Bonêre, Saba of Sint Eustaasjus).'"/>
	<xsl:param name="H1_170_model_verklaring_voorgenomen_vestiging_BC" select="'In ferklearring fan foarnommen fêstiging foar alle op de list foarkommende kandidaten dy’t net wenjend binne yn it gebiet dêr’t de ferkiezing op slacht (allinnich by gemeenterie- of provinsjale steateferkiezings).'"/>
	<xsl:param name="H1_180_modelY13" select="'Een verklaring van iedere op de lijst voorkomende  kandidaat dat hij niet in een andere lidstaat kandidaat zal zijn voor het Europees Parlement (model Y 13).'"/>
	<xsl:param name="H1_190_modelY35" select="'Een verklaring van kandidaten die onderdaan zijn van een andere lidstaat, dat zij in die lidstaat niet zijn uitgesloten van het recht om gekozen te worden voor de verkiezingen van het Europees Parlement (model Y 35).'"/>

	<xsl:param name="H1_200_Ondertekening" select="'Undertekening troch dejinge dy’t ynleveret'"/>
	<xsl:param name="H1_205_NaamEnVoorletters" select="'Namme en foarletters'"/>
	<xsl:param name="H1_210_PostadresPostcodePlaats" select="'Postadres, postkoade en plak'"/>
	<xsl:param name="H1_220_Datum" select="'Datum'"/>
	<xsl:param name="H1_230_Handtekening" select="'Hantekening'"/>
	
	<xsl:param name="H1_300_AgentLabel-naam" select="'namme'"/>
	<xsl:param name="H1_310_AgentLabel-voorletters" select="'foarletters'"/>
	<xsl:param name="H1_320_AgentLabel-postadres" select="'postadres'"/>
	<xsl:param name="H1_330_AgentLabel-postcode" select="'postkoade'"/>
	<xsl:param name="H1_340_AgentLabel-plaats" select="'plak'"/>
</xsl:stylesheet>
