<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="xhtml" encoding="iso-8859-1" indent="no"/>
	<xsl:template match="listing">
		<html>
			<head>
				<title>
        Sample Directory Listing For
        <xsl:value-of select="@directory"/>
				</title>
				<style>
td {
	font-family: Verdana, Arial, Helvetica;
	font-size: 11px;
	color: #093C69;
}
      
th {
      	font-family: Verdana, Arial, Helvetica;
      	font-size: 11px;
      	font-weight: bold;
      	text-align: left;
      	color: #093C69;
}
.hgrot {
	font-family: Verdana, Arial, Helvetica;
	font-size: 11px;
	font-weight: bold;
	background-color: #093C69;
	color: white !important;
}

.hghell {
	font-family: Verdana, Arial, Helvetica;
	font-size: 11px;
	background-color: #CCC;
	color: black !important;
}
.hgeeeeee {
   	font-family: Verdana, Arial, Helvetica;
	background-color: #EEE;
}
.hgweiss {
	background-color: white;
}

.hgweisshervorg {
	background-color: white;
	font-weight: bold;
	color: red;
}

/* Linien in befehl.jsp */
.hggrau {
	font-family: Verdana, Arial, Helvetica;
	font-size: 11px;
	font-weight: bold;
	background-color: #CCC;
	color: #093C69;
}

.box2a {
	color: white;
	background: #0047B6;
	border-left: 1px solid #CCC;
	border-bottom: 1px solid #093C69;
	border-right: 1px solid #093C69;
	border-top: 1px solid #CCC;
	text-align: center;
	text-decoration:none;
	font-family: Arial, Helvetica, Verdana;
	font-size: 11px;
	cursor: pointer;
	width: inherit;
	padding-left: 10px; 
	padding-right: 10px;
}

body {background-color: #E3E6ED;}
b {color: white; background-color: #0086b2;}
a {color: #093C69;} 
        
      </style>
			</head>
			<body>
				<table width="99%" cellspacing="0" cellpadding="1" border="0">
					<tbody>
						<tr class="hgrot">
							<td valign="top">
								<table width="100%" cellspacing="0" cellpadding="0" border="0" class="hgweiss">
									<tbody>
										<tr class="hgeeeeee">
											<td height="18" width="5"> </td>
											<td>map<xsl:value-of select="@directory"/>
											</td>
											<td height="18" width="5"> </td>
										</tr>
										<tr>
											<td class="hgrot" colspan="3">
												<img height="1" width="1" src="/img/icon/blind.gif"/>
											</td>
										</tr>
										<tr>
											<td colspan="3">
												<img height="10" width="1" src="/img/icon/blind.gif"/>
											</td>
										</tr>
										<tr>
											<td width="10">
												<img height="1" width="1" src="/img/icon/blind.gif"/>
											</td>
											<td valign="top">
												<table width="100%" cellspacing="2" cellpadding="4" border="0" class="hgweiss">
													<tbody>
														<tr>
															<th align="left">Bestandsnaam</th>
															<th align="right" width="15%">Type</th>
															<th align="center" width="20%">Omvang</th>
														</tr>
														<xsl:apply-templates select="entries"/>
													</tbody>
												</table>
											</td>
											<td width="10"> </td>
										</tr>
										<tr>
											<td colspan="3">
												<img height="10" width="1" src="/img/icon/blind.gif"/>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<img height="10" width="1" src="/img/icon/blind.gif" alt="" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<xsl:for-each select="entries">
									<xsl:for-each select="entry[text()='.Gecomprimeerde_Verkiezingsbestanden/']">
										<xsl:call-template name="zipped_entry">
											<xsl:with-param name="urlPath" select="@urlPath"/>
											<xsl:with-param name="date" select="concat('?',@date)"/>
										</xsl:call-template>
									</xsl:for-each>
								</xsl:for-each>
								<xsl:for-each select="entries">
									<xsl:for-each select="entry[starts-with(text(),'Resultaat_')]">
										&#160;
										<xsl:call-template name="results_zip_entry">
											<xsl:with-param name="urlPath" select="concat(substring-before(@urlPath,'Resultaat_'),'.Gecomprimeerde_Verkiezingsbestanden/','Verkiezingsuitslagen_',substring-before(substring-after(text(),'Resultaat_'),'.eml.xml'),'.zip')"/>
											<xsl:with-param name="electionId" select="substring-before(substring-after(text(),'Resultaat_'),'.eml.xml')"/>
											<xsl:with-param name="date" select="concat('?',@date)"/>
										</xsl:call-template>
									</xsl:for-each>
								</xsl:for-each>
							</td>
						</tr>
					</tbody>
				</table>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="entries[../@hasParent='true']">
		<xsl:call-template name="entry_core">
			<xsl:with-param name="urlPath" select="concat(../@contextPath,../@directory,'..')"/>
			<xsl:with-param name="name" select="'[..]'"/>
		</xsl:call-template>
		<xsl:apply-templates select="entry"/>
	</xsl:template>
	<xsl:template match="entries">
		<xsl:apply-templates select="entry"/>
	</xsl:template>
	<xsl:template match="readme">
		<hr size="1"/>
		<pre>
			<xsl:apply-templates/>
		</pre>
	</xsl:template>
	<xsl:template match="entry[substring(text(),1,1)='.']">
	</xsl:template>
	<xsl:template match="entry">
		<xsl:call-template name="entry_core">
			<xsl:with-param name="urlPath" select="@urlPath"/>
			<xsl:with-param name="name" select="text()"/>
			<xsl:with-param name="size" select="@size"/>
			<xsl:with-param name="type" select="@type"/>
			<xsl:with-param name="date" select="concat('?',@date)"/>
		</xsl:call-template>
	</xsl:template>
	<xsl:template name="entry_core">
		<xsl:param name="urlPath"/>
		<xsl:param name="name"/>
		<xsl:param name="size" select="'-'"/>
		<xsl:param name="type" select="'-'"/>
		<xsl:param name="date"/>
		<tr>
			<td align="left" class="hgeeeeee">
				<a href="{$urlPath}{$date}">
					<tt>
						<xsl:value-of select="$name"/>
					</tt>
				</a>
			</td>
			<td align="left" class="hgeeeeee">
				<tt>
					<xsl:value-of select="$type"/>
				</tt>
			</td>
			<td align="left" class="hgeeeeee">
				<tt>
					<xsl:value-of select="$size"/>
				</tt>
			</td>
		</tr>
	</xsl:template>
	<xsl:template name="zipped_entry">
		<xsl:param name="urlPath"/>
		<xsl:param name="date"/>
			<a class="box2a" href="{$urlPath}{$date}" title="Samenvoegen alle verkiezingsbestanden in 1 zip-bestand">Comprimeren</a>
	</xsl:template>
	<xsl:template name="results_zip_entry">
		<xsl:param name="urlPath"/>
		<xsl:param name="electionId"/>
		<xsl:param name="date"/>
			<a class="box2a" href="{$urlPath}{$date}" title="De Kiesraad verzoekt u om de volgende bestanden te verstrekken voor de publicatie in de Databank met verkiezingsuitslagen (www.verkiezingsuitslagen.nl)">Export verkiezingsuitslagen <xsl:value-of select="$electionId" /></a>
	</xsl:template>
</xsl:stylesheet>