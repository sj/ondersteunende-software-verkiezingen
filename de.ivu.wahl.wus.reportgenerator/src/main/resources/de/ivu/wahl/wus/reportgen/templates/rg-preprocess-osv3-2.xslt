<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:eml="urn:oasis:names:tc:evs:schema:eml" xmlns:rg="http://www.kiesraad.nl/reportgenerator">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	
	<xsl:template match="/">
		<xsl:apply-templates/>
	</xsl:template>

	<!-- Copy all Affiliations from Contest "alle" into the other Contest -->
	<xsl:template match="eml:Contest[eml:ContestIdentifier/@Id='alle']"/>
	<xsl:template match="eml:Contest[eml:ContestIdentifier/@Id!='alle']">
		<xsl:copy>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
			<xsl:copy-of select="../eml:Contest[eml:ContestIdentifier/@Id='alle']/eml:Affiliation"/>
		</xsl:copy>
	</xsl:template>

	<!-- Within the eml:Election, after normally applying templates, add MissingContests -->
	<xsl:template match="eml:Election">
		<xsl:copy>
			<xsl:apply-templates select="*|@*|text()"/>
			<xsl:for-each select="//rg:ContestIdentifiers/eml:ContestIdentifier[@Id!='alle']">
			<xsl:call-template name="MissingContest">
				<xsl:with-param name="contest_indentifier" select="."/>
			</xsl:call-template>
			</xsl:for-each>
		</xsl:copy>
	</xsl:template>
	
	<!-- Only if no Contest with the same Id exists, create a Contest with the given eml:ContestIdentifier and copy all eml:Affiliation's from the Contest "alle". -->
	<xsl:template name="MissingContest">
		<xsl:param name="contest_indentifier"/>
		<xsl:if test="count(//eml:Contest/eml:ContestIdentifier[@Id=$contest_indentifier/@Id]) = 0">
			<eml:Contest>
				<xsl:copy-of select="$contest_indentifier"/>
				<xsl:apply-templates select="//eml:Contest[eml:ContestIdentifier/@Id='alle']/eml:Affiliation"/>
			</eml:Contest>
		</xsl:if>
	</xsl:template>

	<xsl:template match="*">
		<xsl:copy>
			<xsl:apply-templates select="*|@*|text()"/>
		</xsl:copy>
	</xsl:template>

	<xsl:template match="@*">
		<xsl:copy-of select="."/>
	</xsl:template>
</xsl:stylesheet>
