<?xml version="1.0" encoding="UTF-8"?>
<structure version="16" xsltversion="1" html-doctype="HTML4 Transitional" compatibility-view="IE9" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" useimportschema="1" embed-images="1" pastemode="xml" enable-authentic-scripts="1" authentic-scripts-in-debug-mode-external="0" generated-file-location="DEFAULT">
	<parameters>
		<parameter name="generateDate" default="01-02-2003 04:05:06"/>
		<parameter name="hashCode" default="1234 5678 90AB CDEF 1234 5678 90AB CDEF 1234 5678 90AB CDEF 1234 5678 90AB CDEF"/>
		<parameter name="isDraft" default="true"/>
		<parameter name="lang" default="0"/>
	</parameters>
	<schemasources>
		<namespaces>
			<nspair prefix="ds" uri="http://www.w3.org/2000/09/xmldsig#"/>
			<nspair prefix="eml" uri="urn:oasis:names:tc:evs:schema:eml"/>
			<nspair prefix="kr" uri="http://www.kiesraad.nl/extensions"/>
			<nspair prefix="rg" uri="http://www.kiesraad.nl/reportgenerator"/>
			<nspair prefix="ts" uri="urn:oasis:names:tc:evs:schema:eml:ts"/>
			<nspair prefix="xal" uri="urn:oasis:names:tc:ciq:xsdschema:xAL:2.0"/>
			<nspair prefix="xnl" uri="urn:oasis:names:tc:ciq:xsdschema:xNL:2.0"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="XML" main="1" schemafile="D:\projekte\eml-kiesraad\EML-v5.0-os\210-extended.xsd" workingxmlfile="D:\projekte\Eml-kiesraad\Examples-2.7\EML 210 PS2011 Venlo Hermans-rg.xml"/>
		</schemasources>
	</schemasources>
	<modules/>
	<flags>
		<scripts/>
		<mainparts/>
		<globalparts/>
		<designfragments/>
		<pagelayouts/>
		<xpath-functions/>
	</flags>
	<scripts>
		<script language="javascript">function doInitialize() {  markLoaded();  return false;  }  function doIntref(inVerwijzingRef, inLabelRef) {  var myWin = window;  var myLabel = &apos;label-&apos; + inLabelRef;  myWin.location.replace(&apos;#&apos; + myLabel);  }  //</script>
	</scripts>
	<script-project>
		<Project version="2" app="AuthenticView"/>
	</script-project>
	<importedxslt>
		<file url="D:\projekte\de.ivu.wahl.wus.reportgenerator\src\main\resources\de\ivu\wahl\wus\reportgen\text\nl\reused-parts-simple-text.xslt"/>
		<file url="D:\projekte\de.ivu.wahl.wus.reportgenerator\src\main\resources\de\ivu\wahl\wus\reportgen\text\nl\H1-text.xslt"/>
	</importedxslt>
	<globalstyles>
		<rules selector="*">
			<media>
				<media value="all"/>
			</media>
			<rule font-family="Arial" font-size="small"/>
		</rules>
		<rules selector="td">
			<media>
				<media value="all"/>
			</media>
			<rule font-size="small"/>
		</rules>
		<rules selector="div">
			<media>
				<media value="all"/>
			</media>
			<rule font-size="small" margin="0" padding="0"/>
		</rules>
		<rules selector="p">
			<media>
				<media value="all"/>
			</media>
			<rule font-size="small"/>
		</rules>
	</globalstyles>
	<mainparts>
		<children>
			<globaltemplate subtype="main" match="/">
				<document-properties title="Model H 1. Kandidatenlijst"/>
				<children>
					<documentsection>
						<properties columncount="1" columngap="0.50in" headerfooterheight="variable" pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="auto" pagestart="next" paperheight="11.69in" papermarginbottom="1.2in" papermarginfooter="0.0in" papermarginheader="0.0in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.79in" paperwidth="8.27in" title="Model H 1. Kandidatenlijst"/>
						<children>
							<globaltemplate subtype="pagelayout" match="footerall"/>
						</children>
						<watermark>
							<image transparency="50" fill-page="1" center-if-not-fill="1"/>
							<text transparency="50"/>
						</watermark>
					</documentsection>
					<newline/>
					<text fixtext="xx-large - 36 - 20,5">
						<styles font-size="xx-large"/>
					</text>
					<newline/>
					<text fixtext="x-large - 24 - 17,5">
						<styles font-size="x-large"/>
					</text>
					<newline/>
					<text fixtext="large - 18 - 14,5">
						<styles font-size="large"/>
					</text>
					<newline/>
					<text fixtext="larger - 14 - 12 ">
						<styles font-size="larger"/>
					</text>
					<newline/>
					<text fixtext="medium - 13 - 12">
						<styles font-size="medium"/>
					</text>
					<newline/>
					<text fixtext="small - 11">
						<styles font-size="small"/>
					</text>
					<text fixtext=" - 10"/>
					<newline/>
					<text fixtext="smaller - 10 - 8,5">
						<styles font-size="smaller"/>
					</text>
					<newline/>
					<text fixtext="x-small - 9 - 8,5">
						<styles font-size="x-small"/>
					</text>
					<newline/>
					<text fixtext="xx-small - 8 - 7">
						<styles font-size="xx-small"/>
					</text>
					<newline/>
					<newline/>
					<newline/>
					<text fixtext="xx-large - 36 - 20,5">
						<styles font-size="xx-large" font-weight="bold"/>
					</text>
					<newline/>
					<text fixtext="x-large - 24 - 17,5">
						<styles font-size="x-large" font-weight="bold"/>
					</text>
					<newline/>
					<text fixtext="large - 18 - 14,5">
						<styles font-size="large" font-weight="bold"/>
					</text>
					<newline/>
					<text fixtext="larger - 14 - 12 ">
						<styles font-size="larger" font-weight="bold"/>
					</text>
					<newline/>
					<text fixtext="medium - 13 - 12">
						<styles font-size="medium" font-weight="bold"/>
					</text>
					<newline/>
					<text fixtext="small - 11">
						<styles font-size="small" font-weight="bold"/>
					</text>
					<text fixtext=" - 10">
						<styles font-weight="bold"/>
					</text>
					<newline/>
					<text fixtext="smaller - 10 - 8,5">
						<styles font-size="smaller" font-weight="bold"/>
					</text>
					<newline/>
					<text fixtext="x-small - 9 - 8,5">
						<styles font-size="x-small" font-weight="bold"/>
					</text>
					<newline/>
					<text fixtext="xx-small - 8 - 7">
						<styles font-size="xx-small" font-weight="bold"/>
					</text>
					<newline/>
					<newline/>
					<text fixtext="20pt">
						<styles font-size="20pt"/>
					</text>
					<newline/>
					<newline/>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<designfragments/>
	<xmltables/>
	<authentic-custom-toolbar-buttons/>
</structure>
