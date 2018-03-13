<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	
	<xsl:param name="program" select="'P5'"/>

	<xsl:template match="/">
		<xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="local-jndi-name">
		<xsl:copy>
			<xsl:value-of select="concat($program,substring-after(.,'jboss/wahl'))"/>
		</xsl:copy>
	</xsl:template>
	
	<xsl:template match="res-jndi-name">
		<xsl:copy>
			<xsl:value-of select="concat(.,'-',$program)"/>
		</xsl:copy>
	</xsl:template>

	<xsl:template match="*">
		<xsl:copy>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>
</xsl:stylesheet>
