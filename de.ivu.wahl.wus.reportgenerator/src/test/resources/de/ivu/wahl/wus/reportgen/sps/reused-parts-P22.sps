<?xml version="1.0" encoding="UTF-8"?>
<structure version="20" html-doctype="HTML4 Transitional" compatibility-view="IE9" html-outputextent="Complete" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" useimportschema="1" embed-images="1" pastemode="xml" enable-authentic-scripts="1" authentic-scripts-in-debug-mode-external="0" generated-file-location="DEFAULT">
	<parameters/>
	<schemasources xpathdefaultns="urn:oasis:names:tc:evs:schema:eml">
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
			<xsdschemasource name="XML" main="1" schemafile="D:\projekte\Eml-kiesraad\EML-v5.0-os\520-extended.xsd"/>
		</schemasources>
	</schemasources>
	<modules>
		<module spsfile="reused-parts-simple.sps"/>
	</modules>
	<flags>
		<scripts/>
		<mainparts>
			<mainpart match="/" spsfile="reused-parts-simple.sps" isactive="0"/>
		</mainparts>
		<globalparts/>
		<designfragments>
			<designfragment match="CandidateInTable2" isactive="1"/>
			<designfragment match="O3-H-CandidateVotes" isactive="1"/>
			<designfragment match="O3CandidateVotes" isactive="1"/>
			<designfragment match="OverviewCandidateListsEP" isactive="1"/>
			<designfragment match="OverviewCandidateListsTK" isactive="1"/>
			<designfragment match="ElectionNameAcceptance" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="ElectionNameAcceptanceShort" spsfile="reused-parts-simple.sps" isactive="0"/>
		</designfragments>
		<pagelayouts/>
		<xpath-functions/>
	</flags>
	<scripts>
		<script language="javascript">
 function doInitialize() {
 markLoaded();
 return false;
 }
 function doIntref(inVerwijzingRef, inLabelRef) {
 var myWin = window;
 var myLabel = &apos;label-&apos; + inLabelRef;
 myWin.location.replace(&apos;#&apos; + myLabel);
 }
 // </script>
	</scripts>
	<script-project>
		<Project version="4" app="AuthenticView"/>
	</script-project>
	<importedxslt/>
	<globalstyles>
		<rules selector="*">
			<media>
				<media value="all"/>
			</media>
			<rule font-family="Arial" font-size="9pt"/>
		</rules>
		<rules selector=".pretitle">
			<media>
				<media value="all"/>
			</media>
			<rule font-size="11pt" font-weight="bold"/>
		</rules>
		<rules selector=".title">
			<media>
				<media value="all"/>
			</media>
			<rule font-size="16pt" font-weight="bold"/>
		</rules>
		<rules selector=".heading">
			<media>
				<media value="all"/>
			</media>
			<rule font-size="11pt" font-weight="bold" margin-bottom="6pt"/>
		</rules>
		<rules selector=".legend">
			<media>
				<media value="all"/>
			</media>
			<rule font-size="9pt" font-style="italic"/>
		</rules>
		<rules selector=".columnheader">
			<media>
				<media value="all"/>
			</media>
			<rule font-size="8pt" font-style="italic" font-weight="normal"/>
		</rules>
		<rules selector=".toplabel">
			<media>
				<media value="all"/>
			</media>
			<rule font-size="8pt" font-style="italic"/>
		</rules>
		<rules selector=".underline">
			<media>
				<media value="all"/>
			</media>
			<rule color="#a6a6a6"/>
		</rules>
	</globalstyles>
	<mainparts>
		<children>
			<globaltemplate subtype="main" match="/">
				<document-properties/>
				<children>
					<documentsection>
						<properties columncount="1" columngap="0.50in" headerfooterheight="variable" pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="auto" pagestart="next" paperheight="11.69in" papermarginbottom="1.0in" papermarginfooter="0.0in" papermarginheader="0.0in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.79in" paperwidth="8.27in"/>
						<watermark>
							<image transparency="50" fill-page="1" center-if-not-fill="1"/>
							<text transparency="50"/>
						</watermark>
					</documentsection>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<designfragments>
		<children>
			<globaltemplate subtype="named" match="OverviewCandidateListsTK">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles border-top="1px solid #000" width="100%"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="20pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="20pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="112pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="22pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="22pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="22pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="13pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" text-align="left" vertical-align="top"/>
												<children>
													<text fixtext="Lijstnr.">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" text-align="left" vertical-align="top"/>
												<children>
													<text fixtext="aanduiding">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="center"/>
												<styles border-top="1px solid #000" text-align="center" vertical-align="top"/>
												<children>
													<text fixtext="Ingeleverd">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center" vertical-align="top"/>
												<children>
													<text fixtext="Kieskring">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" line-height="97pt" vertical-align="bottom"/>
												<children>
													<text fixtext=" ">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="bottom"/>
												<children>
													<text fixtext=" ">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<text fixtext=" ">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<text fixtext="lijstengroep (gelijkluidende">
														<styles font-size="8pt"/>
													</text>
													<text fixtext="t">
														<styles color="white" font-size="8pt"/>
													</text>
													<text fixtext="lijsten)">
														<styles font-size="8pt"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<text fixtext="lijstengroep">
														<styles font-size="8pt"/>
													</text>
													<newline/>
													<text fixtext="(niet">
														<styles font-size="8pt"/>
													</text>
													<text fixtext="t">
														<styles color="white" font-size="8pt"/>
													</text>
													<text fixtext="gelijkluidende">
														<styles font-size="8pt"/>
													</text>
													<text fixtext="t">
														<styles color="white" font-size="8pt"/>
													</text>
													<text fixtext="lijsten)">
														<styles font-size="8pt"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<text fixtext="op">
														<styles font-size="8pt"/>
													</text>
													<text fixtext="t">
														<styles color="white" font-size="8pt"/>
													</text>
													<text fixtext="zichzelf">
														<styles font-size="8pt"/>
													</text>
													<text fixtext="t">
														<styles color="white" font-size="8pt"/>
													</text>
													<text fixtext="staande">
														<styles font-size="8pt"/>
													</text>
													<text fixtext="t">
														<styles color="white" font-size="8pt"/>
													</text>
													<text fixtext="lijst">
														<styles font-size="8pt"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="3"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="4"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="5"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="6"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="7"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="8"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="9"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="10"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="11"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="12"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="13"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="14"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="15"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="16"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="17"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="18"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="19"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="20"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridheader-rows>
							<tgridbody-rows>
								<children>
									<template subtype="element" match="rg:OverviewOfListsAndDistricts">
										<sort>
											<key match="rg:CandidateListName/@Id" datatype="Number"/>
										</sort>
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<template subtype="element" match="rg:CandidateListName">
																<children>
																	<template subtype="element" match="eml:AffiliationIdentifier">
																		<children>
																			<template subtype="attribute" match="Id">
																				<children>
																					<content subtype="regular">
																						<styles font-size="smaller"/>
																						<format basic-type="xsd" datatype="NMTOKEN"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="rg:CandidateListName/eml:AffiliationIdentifier/eml:RegisteredName != &apos;&apos;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$SV_OutputFormat = &apos;RTF&apos;">
																						<children>
																							<text fixtext=" ">
																								<styles font-size="smaller"/>
																							</text>
																							<template subtype="element" match="rg:CandidateListName">
																								<children>
																									<template subtype="element" match="eml:AffiliationIdentifier">
																										<children>
																											<template subtype="element" match="eml:RegisteredName">
																												<children>
																													<content subtype="regular">
																														<styles font-size="smaller"/>
																														<format basic-type="xsd" datatype="token"/>
																													</content>
																												</children>
																												<variables/>
																											</template>
																										</children>
																										<variables/>
																									</template>
																								</children>
																								<variables/>
																							</template>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<styles white-space="pre-wrap"/>
																								<children>
																									<text fixtext=" ">
																										<styles font-size="smaller"/>
																									</text>
																									<template subtype="element" match="rg:CandidateListName">
																										<children>
																											<template subtype="element" match="eml:AffiliationIdentifier">
																												<children>
																													<template subtype="element" match="eml:RegisteredName">
																														<children>
																															<content subtype="regular">
																																<styles font-size="smaller"/>
																																<format basic-type="xsd" datatype="token"/>
																															</content>
																														</children>
																														<variables/>
																													</template>
																												</children>
																												<variables/>
																											</template>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</paragraph>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<text fixtext="invisible">
																				<styles color="white" font-size="smaller"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tgridcell>
													<tgridcell joinleft="1"/>
													<tgridcell>
														<styles text-align="center"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="./rg:Type[text() = &quot;stel gelijkluidende lijsten&quot;]">
																		<children>
																			<text fixtext="*"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch/>
																</children>
															</condition>
														</children>
													</tgridcell>
													<tgridcell>
														<styles text-align="center"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="./rg:Type[text() = &quot;lijstengroep&quot;]">
																		<children>
																			<text fixtext="*"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch/>
																</children>
															</condition>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="center"/>
														<styles text-align="center"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="./rg:Type[text() = &quot;op zichzelf staande lijst&quot;]">
																		<children>
																			<text fixtext="*"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch/>
																</children>
															</condition>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="3"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="4"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="5"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="6"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="7"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="8"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="9"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="10"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="11"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="12"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="13"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="14"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="15"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="16"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="17"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="18"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="19"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="20"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<tgridrow>
										<children>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewCandidateListsPS2">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles border-top="1px solid #000" width="100%"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="20pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="68pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="140pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="30pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="30pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="30pt"/>
									</tgridcol>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Lijstnr."/>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="aanduiding"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="center"/>
												<styles border-top="1px solid #000" text-align="center" vertical-align="top"/>
												<children>
													<text fixtext="Ingeleverd"/>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center" vertical-align="top"/>
												<children>
													<text fixtext="Kieskring"/>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<styles border-bottom="1px solid #000" line-height="97pt" vertical-align="bottom"/>
												<children>
													<text fixtext=" ">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="bottom"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="bottom"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<styles border-bottom="1px solid #000"/>
												<children>
													<text fixtext="lijstengroep (gelijkluidende">
														<styles font-size="8pt"/>
													</text>
													<text fixtext="t">
														<styles color="white" font-size="8pt"/>
													</text>
													<text fixtext="lijsten)">
														<styles font-size="8pt"/>
													</text>
													<newline/>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<styles border-bottom="1px solid #000"/>
												<children>
													<text fixtext="lijstengroep">
														<styles font-size="8pt"/>
													</text>
													<newline/>
													<text fixtext="(niet">
														<styles font-size="8pt"/>
													</text>
													<text fixtext="t">
														<styles color="white" font-size="8pt"/>
													</text>
													<text fixtext="gelijkluidende">
														<styles font-size="8pt"/>
													</text>
													<text fixtext="t">
														<styles color="white" font-size="8pt"/>
													</text>
													<text fixtext="lijsten)">
														<styles font-size="8pt"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<styles border-bottom="1px solid #000"/>
												<children>
													<text fixtext="op">
														<styles font-size="8pt"/>
													</text>
													<text fixtext="t">
														<styles color="white" font-size="8pt"/>
													</text>
													<text fixtext="zichzelf">
														<styles font-size="8pt"/>
													</text>
													<text fixtext="t">
														<styles color="white" font-size="8pt"/>
													</text>
													<text fixtext="staande">
														<styles font-size="8pt"/>
													</text>
													<text fixtext="t">
														<styles color="white" font-size="8pt"/>
													</text>
													<text fixtext="lijst">
														<styles font-size="8pt"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="1"/>
															<parameter name="smaller" value="0"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="2"/>
															<parameter name="smaller" value="0"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="3"/>
															<parameter name="smaller" value="0"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="CandidateListSubmittedHeader">
														<parameters>
															<parameter name="districtId" value="4"/>
															<parameter name="smaller" value="0"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridheader-rows>
							<tgridbody-rows>
								<children>
									<template subtype="element" match="rg:OverviewOfListsAndDistricts">
										<sort>
											<key match="rg:CandidateListName/@Id" datatype="Number"/>
										</sort>
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<children>
															<template subtype="element" match="rg:CandidateListName">
																<children>
																	<template subtype="element" match="eml:AffiliationIdentifier">
																		<children>
																			<template subtype="attribute" match="Id">
																				<children>
																					<content subtype="regular">
																						<format basic-type="xsd" datatype="NMTOKEN"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<styles text-align="left"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="rg:CandidateListName/eml:AffiliationIdentifier/eml:RegisteredName != &apos;&apos;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$SV_OutputFormat = &apos;RTF&apos;">
																						<children>
																							<text fixtext=" ">
																								<styles font-size="smaller"/>
																							</text>
																							<template subtype="element" match="rg:CandidateListName">
																								<children>
																									<template subtype="element" match="eml:AffiliationIdentifier">
																										<children>
																											<template subtype="element" match="eml:RegisteredName">
																												<children>
																													<content subtype="regular">
																														<format basic-type="xsd" datatype="token"/>
																													</content>
																												</children>
																												<variables/>
																											</template>
																										</children>
																										<variables/>
																									</template>
																								</children>
																								<variables/>
																							</template>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<styles white-space="pre-wrap"/>
																								<children>
																									<text fixtext=" ">
																										<styles font-size="smaller"/>
																									</text>
																									<template subtype="element" match="rg:CandidateListName">
																										<children>
																											<template subtype="element" match="eml:AffiliationIdentifier">
																												<children>
																													<template subtype="element" match="eml:RegisteredName">
																														<children>
																															<content subtype="regular">
																																<format basic-type="xsd" datatype="token"/>
																															</content>
																														</children>
																														<variables/>
																													</template>
																												</children>
																												<variables/>
																											</template>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</paragraph>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<text fixtext=" ">
																				<styles font-size="smaller"/>
																			</text>
																			<text fixtext="invisible">
																				<styles color="white" font-size="smaller"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles text-align="left"/>
													</tgridcell>
													<tgridcell>
														<styles text-align="center"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="./rg:Type[text() = &quot;stel gelijkluidende lijsten&quot;]">
																		<children>
																			<text fixtext="*"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch/>
																</children>
															</condition>
														</children>
													</tgridcell>
													<tgridcell>
														<styles text-align="center"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="./rg:Type[text() = &quot;lijstengroep&quot;]">
																		<children>
																			<text fixtext="*"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch/>
																</children>
															</condition>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="center"/>
														<styles text-align="center"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="./rg:Type[text() = &quot;op zichzelf staande lijst&quot;]">
																		<children>
																			<text fixtext="*"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch/>
																</children>
															</condition>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="1"/>
																	<parameter name="smaller" value="0"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="2"/>
																	<parameter name="smaller" value="0"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="3"/>
																	<parameter name="smaller" value="0"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="4"/>
																	<parameter name="smaller" value="0"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<tgridrow>
										<children>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
											<tgridcell>
												<styles border-top-color="black" border-top-style="solid" border-top-width="1px"/>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewCandidateListsEK">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles border-top="1px solid #000" width="100%"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="28pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="150pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="25pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="25pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="25pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="18pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="18pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="18pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="18pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="18pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="18pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="18pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="18pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="18pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="18pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="18pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="18pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<template subtype="element" match="rg:ElectoralDistrictsOverview">
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="left"/>
														<styles border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="left" vertical-align="top"/>
														<children>
															<text fixtext="lijstnr.">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<styles border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="left" vertical-align="top"/>
														<children>
															<text fixtext="aanduiding">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="center"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="left" vertical-align="top"/>
														<children>
															<text fixtext="ingeleverd">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="left" vertical-align="top"/>
														<children>
															<text fixtext="provincie">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
													</tgridcell>
												</children>
											</tgridrow>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" line-height="97pt" vertical-align="bottom"/>
														<children>
															<text fixtext=" ">
																<styles font-size="smaller"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell rotation="vertical-left">
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" vertical-align="bottom"/>
														<children>
															<text fixtext="  ">
																<styles font-size="smaller"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell rotation="vertical-left">
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
														<children>
															<text fixtext="lijstengroep">
																<styles font-size="8pt"/>
															</text>
															<text fixtext="t">
																<styles color="white" font-size="8pt"/>
															</text>
															<text fixtext="(gelijkluidende lijsten)">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell rotation="vertical-left">
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
														<children>
															<text fixtext="lijstengroep">
																<styles font-size="8pt"/>
															</text>
															<newline/>
															<text fixtext="(niet">
																<styles font-size="8pt"/>
															</text>
															<text fixtext="t">
																<styles color="white" font-size="8pt"/>
															</text>
															<text fixtext="gelijkluidende">
																<styles font-size="8pt"/>
															</text>
															<text fixtext="t">
																<styles color="white" font-size="8pt"/>
															</text>
															<text fixtext="lijsten)">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell rotation="vertical-left">
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
														<children>
															<text fixtext="op">
																<styles font-size="8pt"/>
															</text>
															<text fixtext="t">
																<styles color="white" font-size="8pt"/>
															</text>
															<text fixtext="zichzelf">
																<styles font-size="8pt"/>
															</text>
															<text fixtext="t">
																<styles color="white" font-size="8pt"/>
															</text>
															<text fixtext="staande">
																<styles font-size="8pt"/>
															</text>
															<text fixtext="t">
																<styles color="white" font-size="8pt"/>
															</text>
															<text fixtext="lijst">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell rotation="vertical-left">
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="bottom"/>
														<children>
															<calltemplate subtype="named" match="ProvinceNameEK">
																<parameters>
																	<parameter name="id" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell rotation="vertical-left">
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="bottom"/>
														<children>
															<calltemplate subtype="named" match="ProvinceNameEK">
																<parameters>
																	<parameter name="id" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell rotation="vertical-left">
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="bottom"/>
														<children>
															<calltemplate subtype="named" match="ProvinceNameEK">
																<parameters>
																	<parameter name="id" value="3"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell rotation="vertical-left">
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="bottom"/>
														<children>
															<calltemplate subtype="named" match="ProvinceNameEK">
																<parameters>
																	<parameter name="id" value="4"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell rotation="vertical-left">
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="bottom"/>
														<children>
															<calltemplate subtype="named" match="ProvinceNameEK">
																<parameters>
																	<parameter name="id" value="5"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell rotation="vertical-left">
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="bottom"/>
														<children>
															<calltemplate subtype="named" match="ProvinceNameEK">
																<parameters>
																	<parameter name="id" value="6"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell rotation="vertical-left">
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="bottom"/>
														<children>
															<calltemplate subtype="named" match="ProvinceNameEK">
																<parameters>
																	<parameter name="id" value="7"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell rotation="vertical-left">
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="bottom"/>
														<children>
															<calltemplate subtype="named" match="ProvinceNameEK">
																<parameters>
																	<parameter name="id" value="8"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell rotation="vertical-left">
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="bottom"/>
														<children>
															<calltemplate subtype="named" match="ProvinceNameEK">
																<parameters>
																	<parameter name="id" value="9"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell rotation="vertical-left">
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="bottom"/>
														<children>
															<calltemplate subtype="named" match="ProvinceNameEK">
																<parameters>
																	<parameter name="id" value="10"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell rotation="vertical-left">
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="bottom"/>
														<children>
															<calltemplate subtype="named" match="ProvinceNameEK">
																<parameters>
																	<parameter name="id" value="11"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell rotation="vertical-left">
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="bottom"/>
														<children>
															<calltemplate subtype="named" match="ProvinceNameEK">
																<parameters>
																	<parameter name="id" value="12"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
								</children>
							</tgridheader-rows>
							<tgridbody-rows>
								<children>
									<template subtype="element" match="rg:OverviewOfListsAndDistricts">
										<sort>
											<key match="rg:CandidateListName/@Id" datatype="Number"/>
										</sort>
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<template subtype="element" match="rg:CandidateListName">
																<children>
																	<template subtype="element" match="eml:AffiliationIdentifier">
																		<children>
																			<template subtype="attribute" match="Id">
																				<children>
																					<content subtype="regular">
																						<styles font-size="smaller"/>
																						<format basic-type="xsd" datatype="NMTOKEN"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="rg:CandidateListName/eml:AffiliationIdentifier/eml:RegisteredName != &apos;&apos;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$SV_OutputFormat = &apos;RTF&apos;">
																						<children>
																							<text fixtext=" ">
																								<styles font-size="smaller"/>
																							</text>
																							<template subtype="element" match="rg:CandidateListName">
																								<children>
																									<template subtype="element" match="eml:AffiliationIdentifier">
																										<children>
																											<template subtype="element" match="eml:RegisteredName">
																												<children>
																													<content subtype="regular">
																														<styles font-size="smaller"/>
																														<format basic-type="xsd" datatype="token"/>
																													</content>
																												</children>
																												<variables/>
																											</template>
																										</children>
																										<variables/>
																									</template>
																								</children>
																								<variables/>
																							</template>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<styles white-space="pre-wrap"/>
																								<children>
																									<text fixtext=" ">
																										<styles font-size="smaller"/>
																									</text>
																									<template subtype="element" match="rg:CandidateListName">
																										<children>
																											<template subtype="element" match="eml:AffiliationIdentifier">
																												<children>
																													<template subtype="element" match="eml:RegisteredName">
																														<children>
																															<content subtype="regular">
																																<styles font-size="smaller"/>
																																<format basic-type="xsd" datatype="token"/>
																															</content>
																														</children>
																														<variables/>
																													</template>
																												</children>
																												<variables/>
																											</template>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</paragraph>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<text fixtext="invisible">
																				<styles color="white" font-size="smaller"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="center"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="./rg:Type[text() = &quot;stel gelijkluidende lijsten&quot;]">
																		<children>
																			<text fixtext="*"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch/>
																</children>
															</condition>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="center"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="./rg:Type[text() = &quot;lijstengroep&quot;]">
																		<children>
																			<text fixtext="*"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch/>
																</children>
															</condition>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="center"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="center"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="./rg:Type[text() = &quot;op zichzelf staande lijst&quot;]">
																		<children>
																			<text fixtext="*"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch/>
																</children>
															</condition>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="3"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="4"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="5"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="6"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="7"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="8"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="9"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="10"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="11"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties valign="top"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="center"/>
														<children>
															<calltemplate subtype="named" match="CandidateListSubmitted">
																<parameters>
																	<parameter name="districtId" value="12"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewElectoralDistricts">
				<parameters/>
				<children>
					<template subtype="element" match="rg:RG520">
						<children>
							<template subtype="element" match="rg:ElectoralDistrictsOverview">
								<children>
									<tgrid>
										<properties border="0" cellpadding="1" cellspacing="0" width="90%"/>
										<styles border-bottom="1px solid #000" border-top="1px solid #000" font-size="smaller"/>
										<children>
											<tgridbody-cols>
												<children>
													<tgridcol/>
													<tgridcol/>
												</children>
											</tgridbody-cols>
											<tgridbody-rows>
												<children>
													<tgridrow>
														<children>
															<tgridcell>
																<styles font-size="smaller" vertical-align="top" width="55pt"/>
																<children>
																	<template subtype="element" match="rg:ElectoralDistrictName">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
																						<children>
																							<paragraph>
																								<children>
																									<autocalc xpath="position()"/>
																									<text fixtext="."/>
																								</children>
																							</paragraph>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="position()"/>
																							<text fixtext="."/>
																							<newline/>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<styles font-size="smaller" vertical-align="top"/>
																<children>
																	<template subtype="element" match="rg:ElectoralDistrictName">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
																						<children>
																							<paragraph>
																								<children>
																									<content subtype="regular"/>
																								</children>
																							</paragraph>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<content subtype="regular"/>
																							<newline/>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
												</children>
											</tgridbody-rows>
										</children>
										<wizard-data-repeat>
											<children/>
										</wizard-data-repeat>
										<wizard-data-rows>
											<children/>
										</wizard-data-rows>
										<wizard-data-columns>
											<children/>
										</wizard-data-columns>
									</tgrid>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewVotes1-10">
				<parameters>
					<parameter name="addSpaceAfterNumbers" type="xs:boolean" occurrence="none-or-one" default-value="true()"/>
				</parameters>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles border-bottom="1px solid #000" border-top="1px solid #000" font-size="xx-small"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="60pt"/>
									</tgridcol>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
								</children>
							</tgridbody-cols>
							<tgridbody-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<styles text-align="center" vertical-align="top"/>
												<children>
													<text fixtext="Kieskring"/>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
										</children>
									</tgridrow>
									<template subtype="element" match="rg:ElectoralDistrictsOverview">
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid #000" vertical-align="top" width="50pt"/>
														<children>
															<text fixtext="Lijstnr."/>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="1"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="2"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="3"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="4"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="5"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="6"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="7"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="8"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="9"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="10"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<template subtype="element" match="rg:OverviewOfListsAndDistrictsAndVotes">
										<children>
											<tgridrow>
												<styles height="1pt"/>
												<children>
													<tgridcell>
														<properties align="center"/>
														<styles vertical-align="top"/>
														<children>
															<template subtype="attribute" match="listNumber">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="NMTOKEN"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="1"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="2"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="3"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="4"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="5"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="6"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="7"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="8"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="9"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="10"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Totaal"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="1"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="2"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="3"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="4"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="5"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="6"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="7"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="8"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="9"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="10"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Blanco stemmen"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="1"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="2"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="3"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="4"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="5"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="6"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="7"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="8"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="9"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="10"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Ongeldige stemmen"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="1"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="2"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="3"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="4"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="5"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="6"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="7"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="8"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="9"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="10"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewVotes11-20">
				<parameters>
					<parameter name="addSpaceAfterNumbers" type="xs:boolean" occurrence="none-or-one" default-value="true()"/>
				</parameters>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles border-bottom="1px solid #000" border-top="1px solid #000" font-size="xx-small"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="60pt"/>
									</tgridcol>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
								</children>
							</tgridbody-cols>
							<tgridbody-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<styles text-align="center" vertical-align="top"/>
												<children>
													<text fixtext="Kieskring"/>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
										</children>
									</tgridrow>
									<template subtype="element" match="rg:ElectoralDistrictsOverview">
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid #000" vertical-align="top" width="50pt"/>
														<children>
															<text fixtext="Lijstnr."/>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="11"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="12"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="13"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="14"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="15"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="16"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="17"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="18"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="19"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="ElectoralDistrictNumber">
																<parameters>
																	<parameter name="districtId" value="20"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<text fixtext="Totaal"/>
															<condition>
																<children>
																	<conditionbranch xpath="$addSpaceAfterNumbers">
																		<children>
																			<text fixtext=" "/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<template subtype="element" match="rg:OverviewOfListsAndDistrictsAndVotes">
										<children>
											<tgridrow>
												<styles height="1pt"/>
												<children>
													<tgridcell>
														<properties align="center"/>
														<styles vertical-align="top"/>
														<children>
															<template subtype="attribute" match="listNumber">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="NMTOKEN"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="11"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="12"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="13"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="14"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="15"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="16"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="17"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="18"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="19"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="20"/>
																	<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<template subtype="element" match="rg:VotesInElectoralDistrict">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@id = &quot;alle&quot;">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
															<condition>
																<children>
																	<conditionbranch xpath="$addSpaceAfterNumbers">
																		<children>
																			<text fixtext=" "/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Totaal"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="11"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="12"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="13"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="14"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="15"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="16"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="17"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="18"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="19"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="20"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="middle"/>
												<children>
													<template subtype="element" match="rg:OverviewOfDistrictsVotes">
														<children>
															<template subtype="element" match="rg:VotesInElectoralDistrict">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@id = &quot;alle&quot;">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
													<condition>
														<children>
															<conditionbranch xpath="$addSpaceAfterNumbers">
																<children>
																	<text fixtext=" "/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Blanco stemmen"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="11"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="12"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="13"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="14"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="15"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="16"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="17"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="18"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="19"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="20"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<template subtype="element" match="rg:OverviewOfBlancVotes">
														<children>
															<template subtype="element" match="rg:VotesInElectoralDistrict">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@id = &quot;alle&quot;">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
													<condition>
														<children>
															<conditionbranch xpath="$addSpaceAfterNumbers">
																<children>
																	<text fixtext=" "/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Ongeldige stemmen"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="11"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="12"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="13"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="14"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="15"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="16"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="17"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="18"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="19"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="20"/>
															<parameter name="addSpaceAfterNumbers" value="$addSpaceAfterNumbers"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<template subtype="element" match="rg:OverviewOfInvalidVotes">
														<children>
															<template subtype="element" match="rg:VotesInElectoralDistrict">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@id = &quot;alle&quot;">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
													<condition>
														<children>
															<conditionbranch xpath="$addSpaceAfterNumbers">
																<children>
																	<text fixtext=" "/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewFirstAssigment">
				<parameters>
					<parameter name="isEK" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<template subtype="element" match="rg:FirstAssignment">
						<children>
							<tgrid>
								<properties border="0" cellpadding="1" cellspacing="0"/>
								<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
								<children>
									<tgridbody-cols>
										<children>
											<tgridcol>
												<styles width="30pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="70pt"/>
											</tgridcol>
											<tgridcol/>
											<tgridcol>
												<styles width="20pt"/>
											</tgridcol>
											<tgridcol/>
											<tgridcol>
												<styles width="20pt"/>
											</tgridcol>
											<tgridcol/>
										</children>
									</tgridbody-cols>
									<tgridheader-rows>
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<text fixtext="lijstnummer">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell joinleft="1"/>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<text fixtext="aantal stemmen">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" text-align="center" vertical-align="top"/>
														<children>
															<text fixtext=":">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<text fixtext="kiesdeler">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" text-align="center" vertical-align="top"/>
														<children>
															<text fixtext="=">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<text fixtext="aantal volle zetels">
																<properties class="columnheader"/>
															</text>
															<newline/>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
									</tgridheader-rows>
									<tgridbody-rows>
										<children>
											<template subtype="element" match="rg:FirstAssignmentLine">
												<children>
													<tgridrow>
														<children>
															<tgridcell>
																<properties align="right"/>
																<styles padding-top="2pt"/>
																<children>
																	<template subtype="element" match="rg:ListOrCombinedList">
																		<children>
																			<template subtype="attribute" match="combinationId">
																				<children>
																					<content subtype="regular">
																						<format basic-type="xsd" datatype="token"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																			<template subtype="attribute" match="listNumber">
																				<children>
																					<content subtype="regular">
																						<format basic-type="xsd" datatype="integer"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<styles padding-left="5pt" padding-top="2pt"/>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="count(rg:ListOrCombinedList/rg:ListInCombination) &gt;0">
																				<children>
																					<text fixtext="("/>
																					<template subtype="element" match="rg:ListOrCombinedList">
																						<children>
																							<template subtype="element" match="rg:ListInCombination">
																								<children>
																									<content subtype="regular">
																										<format basic-type="xsd" datatype="integer"/>
																									</content>
																									<condition>
																										<children>
																											<conditionbranch xpath="position() != last()">
																												<children>
																													<text fixtext=", "/>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
																								</children>
																								<variables/>
																							</template>
																						</children>
																						<variables/>
																					</template>
																					<text fixtext=")"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<condition>
																		<children>
																			<conditionbranch xpath="//kr:ElectionSubcategory[text()= &apos;PS2&apos; or text()= &apos;TK&apos;]">
																				<children>
																					<template subtype="element" match="rg:ListOrCombinedList">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="@districtNumber">
																										<children>
																											<text fixtext="(Kieskring "/>
																											<template subtype="attribute" match="districtNumber">
																												<children>
																													<content subtype="regular">
																														<format basic-type="xsd" datatype="integer"/>
																													</content>
																													<text fixtext=")"/>
																												</children>
																												<variables/>
																											</template>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																						<variables/>
																					</template>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="//kr:ElectionSubcategory[text()= &apos;EK&apos;]">
																				<children>
																					<template subtype="element" match="rg:ListOrCombinedList">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="@provinceName">
																										<children>
																											<template subtype="attribute" match="provinceName">
																												<children>
																													<text fixtext="("/>
																													<content subtype="regular">
																														<format basic-type="xsd" datatype="integer"/>
																													</content>
																													<text fixtext=")"/>
																												</children>
																												<variables/>
																											</template>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																						<variables/>
																					</template>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</tgridcell>
															<tgridcell>
																<properties align="right"/>
																<styles padding-top="2pt" vertical-align="top"/>
																<children>
																	<template subtype="element" match="rg:Votes">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<styles padding-top="2pt" text-align="center" vertical-align="top"/>
																<children>
																	<text fixtext=":"/>
																</children>
															</tgridcell>
															<tgridcell>
																<styles padding-top="2pt" vertical-align="top"/>
																<children>
																	<template subtype="userdefined" match="..">
																		<children>
																			<template subtype="userdefined" match="..">
																				<children>
																					<template subtype="element" match="rg:ElectoralQuota">
																						<children>
																							<calltemplate subtype="named" match="ElectionFraction">
																								<parameters/>
																							</calltemplate>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<styles padding-top="2pt" text-align="center" vertical-align="top"/>
																<children>
																	<text fixtext="="/>
																</children>
															</tgridcell>
															<tgridcell>
																<properties align="right"/>
																<styles padding-top="2pt" vertical-align="top"/>
																<children>
																	<template subtype="element" match="rg:Seats">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
												</children>
												<variables/>
											</template>
										</children>
									</tgridbody-rows>
								</children>
								<wizard-data-repeat>
									<children/>
								</wizard-data-repeat>
								<wizard-data-rows>
									<children/>
								</wizard-data-rows>
								<wizard-data-columns>
									<children/>
								</wizard-data-columns>
							</tgrid>
							<calltemplate subtype="named" match="LineBreakTable">
								<parameters/>
							</calltemplate>
							<tgrid>
								<children>
									<tgridbody-cols>
										<children>
											<tgridcol>
												<styles width="200pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="100pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="15pt"/>
											</tgridcol>
										</children>
									</tgridbody-cols>
									<tgridbody-rows>
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<children>
															<text fixtext="Totaal aantal zetels"/>
														</children>
													</tgridcell>
													<tgridcell>
														<styles text-align="right"/>
														<children>
															<autocalc xpath="rg:ResidualSeats + rg:TotalSeatsAssigned"/>
														</children>
													</tgridcell>
													<tgridcell/>
												</children>
											</tgridrow>
											<tgridrow>
												<children>
													<tgridcell>
														<children>
															<text fixtext="Totaal aantal toegewezen volle zetels"/>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" text-align="right"/>
														<children>
															<template subtype="element" match="rg:TotalSeatsAssigned">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<children>
															<text fixtext="–"/>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
											<tgridrow>
												<children>
													<tgridcell>
														<children>
															<text fixtext="Restzetels"/>
														</children>
													</tgridcell>
													<tgridcell>
														<styles text-align="right"/>
														<children>
															<template subtype="element" match="rg:ResidualSeats">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell/>
												</children>
											</tgridrow>
										</children>
									</tgridbody-rows>
								</children>
								<wizard-data-repeat>
									<children/>
								</wizard-data-repeat>
								<wizard-data-rows>
									<children/>
								</wizard-data-rows>
								<wizard-data-columns>
									<children/>
								</wizard-data-columns>
							</tgrid>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewDHondtAssignment">
				<parameters>
					<parameter name="articleLoting" type="xs:token" occurrence="none-or-one" default-value="&apos;&apos;"/>
				</parameters>
				<children>
					<template subtype="element" match="rg:DHondtAssignment">
						<children>
							<condition>
								<children>
									<conditionbranch xpath="$numberOfSteps  &lt;= 2">
										<children>
											<calltemplate subtype="named" match="OverviewDHondtTable2">
												<parameters>
													<parameter name="maxNumberOfAssignments" value="$numberOfSteps"/>
												</parameters>
											</calltemplate>
										</children>
									</conditionbranch>
									<conditionbranch xpath="$numberOfSteps  = 3">
										<children>
											<calltemplate subtype="named" match="OverviewDHondtTable3">
												<parameters>
													<parameter name="maxNumberOfAssignments" value="$numberOfSteps"/>
												</parameters>
											</calltemplate>
										</children>
									</conditionbranch>
									<conditionbranch xpath="$numberOfSteps  = 4">
										<children>
											<calltemplate subtype="named" match="OverviewDHondtTable4">
												<parameters>
													<parameter name="maxNumberOfAssignments" value="$numberOfSteps"/>
												</parameters>
											</calltemplate>
										</children>
									</conditionbranch>
									<conditionbranch>
										<children>
											<calltemplate subtype="named" match="OverviewDHondtTable6">
												<parameters>
													<parameter name="maxNumberOfAssignments" value="$numberOfSteps"/>
													<parameter name="offset" value="0"/>
												</parameters>
											</calltemplate>
											<condition>
												<children>
													<conditionbranch xpath="$numberOfSteps &gt; 6">
														<children>
															<calltemplate subtype="named" match="LineBreak">
																<parameters/>
															</calltemplate>
															<condition>
																<children>
																	<conditionbranch xpath="count( rg:DHondtAssignmentLine )  &gt;= 15">
																		<children>
																			<newline break="page"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<calltemplate subtype="named" match="OverviewDHondtTable6">
																<parameters>
																	<parameter name="maxNumberOfAssignments" value="$numberOfSteps"/>
																	<parameter name="offset" value="6"/>
																</parameters>
															</calltemplate>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<condition>
												<children>
													<conditionbranch xpath="$numberOfSteps &gt; 12">
														<children>
															<calltemplate subtype="named" match="LineBreak">
																<parameters/>
															</calltemplate>
															<condition>
																<children>
																	<conditionbranch xpath="count( rg:DHondtAssignmentLine )  &gt;= 15">
																		<children>
																			<newline break="page"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<calltemplate subtype="named" match="OverviewDHondtTable6">
																<parameters>
																	<parameter name="maxNumberOfAssignments" value="$numberOfSteps"/>
																	<parameter name="offset" value="12"/>
																</parameters>
															</calltemplate>
														</children>
													</conditionbranch>
												</children>
											</condition>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<template subtype="element" match="rg:Allotting">
								<children>
									<newline/>
									<newline/>
									<text fixtext="Overeenkomstig artikel "/>
									<condition>
										<children>
											<conditionbranch xpath="$articleLoting != &quot;&quot;">
												<children>
													<autocalc xpath="$articleLoting"/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="@article = &quot;P13&quot;">
																<children>
																	<text fixtext="P 13"/>
																</children>
															</conditionbranch>
															<conditionbranch xpath="@article = &quot;U13&quot;">
																<children>
																	<text fixtext="U 13"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<text fixtext="P 7"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<text fixtext=", eerste lid van de Kieswet heeft een loting plaatsgevonden tussen "/>
									<template subtype="element" match="rg:Winner">
										<children>
											<calltemplate subtype="named" match="ListOrCombinedList">
												<parameters/>
											</calltemplate>
										</children>
										<variables/>
									</template>
									<template subtype="element" match="rg:Looser">
										<children>
											<text fixtext=" en "/>
											<calltemplate subtype="named" match="ListOrCombinedList">
												<parameters/>
											</calltemplate>
										</children>
										<variables/>
									</template>
									<text fixtext=". Als gevolg hiervan is een restzetel toegewezen aan "/>
									<template subtype="element" match="rg:Winner">
										<children>
											<calltemplate subtype="named" match="ListOrCombinedList">
												<parameters/>
											</calltemplate>
										</children>
										<variables/>
									</template>
									<text fixtext="."/>
								</children>
								<variables/>
							</template>
						</children>
						<variables>
							<variable name="numberOfSteps" value="./@maxNumberOfAssignments" valuetype="&lt;auto&gt;"/>
						</variables>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewListResult">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0"/>
						<styles border-bottom="1px solid #000"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol/>
									<tgridcol/>
									<tgridcol>
										<styles width="80pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
												<children>
													<text fixtext="aanduiding politieke groepering">
														<properties class="columnheader"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" border-top="1px solid #000" text-align="center" width="90pt"/>
												<children>
													<text fixtext="lijstnummer">
														<properties class="columnheader"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" border-top="1px solid #000" text-align="center"/>
												<children>
													<text fixtext="aantal zetels">
														<properties class="columnheader"/>
													</text>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridheader-rows>
							<tgridbody-rows>
								<children>
									<template subtype="element" match="rg:OverviewOfCandidatesAndResults">
										<children>
											<template subtype="element" match="rg:ListGroupAndResults">
												<sort>
													<key match="rg:Seats" datatype="Number" order="descending"/>
													<key match="rg:Votes" datatype="Number" order="descending"/>
													<key match="eml:AffiliationIdentifier/@Id" datatype="Number"/>
												</sort>
												<children>
													<tgridrow>
														<children>
															<tgridcell>
																<styles vertical-align="top"/>
																<children>
																	<calltemplate subtype="named" match="PartyNamePdfWrapped">
																		<parameters/>
																	</calltemplate>
																</children>
															</tgridcell>
															<tgridcell>
																<styles text-align="center" vertical-align="top" width="90pt"/>
																<children>
																	<template subtype="element" match="eml:AffiliationIdentifier">
																		<children>
																			<template subtype="attribute" match="Id">
																				<children>
																					<content subtype="regular">
																						<format basic-type="xsd" datatype="NMTOKEN"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<styles text-align="center" vertical-align="top"/>
																<children>
																	<template subtype="element" match="rg:Seats">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
												</children>
												<variables/>
											</template>
										</children>
										<variables/>
									</template>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
					<newline/>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewListResultAndSeats">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0"/>
						<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol/>
									<tgridcol>
										<styles width="100pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="100pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="100pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<text fixtext="Aanduiding van de groepering"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<text fixtext="Lijst"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<text fixtext="Totaal aantal stemmen"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<text fixtext="Toegewezen aantal zetels"/>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridheader-rows>
							<tgridbody-rows>
								<children>
									<template subtype="element" match="rg:OverviewOfCandidatesAndResults">
										<children>
											<template subtype="element" match="rg:ListGroupAndResults">
												<sort>
													<key match="eml:AffiliationIdentifier/@Id" datatype="Number"/>
												</sort>
												<children>
													<tgridrow>
														<children>
															<tgridcell>
																<styles vertical-align="top"/>
																<children>
																	<calltemplate subtype="named" match="PartyNamePdfWrapped">
																		<parameters/>
																	</calltemplate>
																</children>
															</tgridcell>
															<tgridcell>
																<styles vertical-align="top"/>
																<children>
																	<template subtype="element" match="eml:AffiliationIdentifier">
																		<children>
																			<template subtype="attribute" match="Id">
																				<children>
																					<content subtype="regular">
																						<format basic-type="xsd" datatype="NMTOKEN"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<styles vertical-align="top"/>
																<children>
																	<template subtype="element" match="rg:Votes">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<styles vertical-align="top"/>
																<children>
																	<template subtype="element" match="rg:Seats">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
												</children>
												<variables/>
											</template>
										</children>
										<variables/>
									</template>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewCandidateResultP22-1-14">
				<parameters>
					<parameter name="isSeats" type="xs:int" occurrence="none-or-one" default-value="1"/>
				</parameters>
				<children>
					<template subtype="element" match="rg:OverviewOfCandidatesAndResults">
						<children>
							<template subtype="element" match="rg:ListGroupAndResults">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="($isSeats = 1 and rg:Seats &gt; 0) or ($isSeats = 0 and rg:Seats = 0)">
												<children>
													<template subtype="element" match="rg:ListAndResults">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$candidateCount != ($noOfCandidatesPerPage + 1) and 
$candidateCount != ($noOfCandidatesPerPage * 2 + 1) and 
$candidateCount != ($noOfCandidatesPerPage * 2 + 2)">
																		<children>
																			<calltemplate subtype="named" match="OverviewCandidateResultP22-1-14-Base">
																				<parameters>
																					<parameter name="isSeats" value="$isSeats"/>
																					<parameter name="fromLine" value="1"/>
																					<parameter name="tillLine" value="$noOfCandidatesPerPage"/>
																					<parameter name="candidateCount" value="count(rg:CandidateAndResult)"/>
																				</parameters>
																			</calltemplate>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<calltemplate subtype="named" match="OverviewCandidateResultP22-1-14-Base">
																				<parameters>
																					<parameter name="isSeats" value="$isSeats"/>
																					<parameter name="fromLine" value="1"/>
																					<parameter name="tillLine" value="$noOfCandidatesPerPage + 1"/>
																					<parameter name="candidateCount" value="count(rg:CandidateAndResult)"/>
																				</parameters>
																			</calltemplate>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<condition>
																<children>
																	<conditionbranch xpath="$candidateCount &gt;= ($noOfCandidatesPerPage + 2) and 
$candidateCount != ($noOfCandidatesPerPage * 2 + 1) and 
$candidateCount != ($noOfCandidatesPerPage * 2 + 2)">
																		<children>
																			<calltemplate subtype="named" match="OverviewCandidateResultP22-1-14-Base">
																				<parameters>
																					<parameter name="isSeats" value="$isSeats"/>
																					<parameter name="fromLine" value="$noOfCandidatesPerPage + 1"/>
																					<parameter name="tillLine" value="$noOfCandidatesPerPage * 2"/>
																					<parameter name="candidateCount" value="count(rg:CandidateAndResult)"/>
																				</parameters>
																			</calltemplate>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="$candidateCount = ($noOfCandidatesPerPage * 2 + 1) or 
$candidateCount = ($noOfCandidatesPerPage * 2 + 2)">
																		<children>
																			<calltemplate subtype="named" match="OverviewCandidateResultP22-1-14-Base">
																				<parameters>
																					<parameter name="isSeats" value="$isSeats"/>
																					<parameter name="fromLine" value="$noOfCandidatesPerPage + 2"/>
																					<parameter name="tillLine" value="$noOfCandidatesPerPage * 2 + 2"/>
																					<parameter name="candidateCount" value="count(rg:CandidateAndResult)"/>
																				</parameters>
																			</calltemplate>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<condition>
																<children>
																	<conditionbranch xpath="count(rg:CandidateAndResult) &gt;= 2 * $noOfCandidatesPerPage + 3">
																		<children>
																			<calltemplate subtype="named" match="OverviewCandidateResultP22-1-14-Base">
																				<parameters>
																					<parameter name="isSeats" value="$isSeats"/>
																					<parameter name="fromLine" value="$noOfCandidatesPerPage * 2 + 1"/>
																					<parameter name="tillLine" value="80"/>
																					<parameter name="candidateCount" value="count(rg:CandidateAndResult)"/>
																				</parameters>
																			</calltemplate>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
														<variables>
															<variable name="candidateCount" value="count(rg:CandidateAndResult)" valuetype="&lt;auto&gt;"/>
														</variables>
													</template>
												</children>
											</conditionbranch>
										</children>
									</condition>
								</children>
								<variables/>
							</template>
						</children>
						<variables>
							<variable name="noOfCandidatesPerPage" value="39" valuetype="&lt;auto&gt;"/>
						</variables>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewCandidateResultP22-1-14-Base">
				<parameters>
					<parameter name="isSeats" type="xs:int" occurrence="none-or-one" default-value="1"/>
					<parameter name="fromLine" type="xs:int"/>
					<parameter name="tillLine" type="xs:int"/>
					<parameter name="candidateCount" type="xs:int"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="count(rg:CandidateAndResult[position() = 1]/rg:VotesInElectoralDistrictPart[position() = 1]/rg:VotesInElectoralDistrict) &lt;= 4">
								<children>
									<calltemplate subtype="named" match="PartyHeaderEvenOdd">
										<parameters>
											<parameter name="isEvenOdd" value="1"/>
											<parameter name="isSeats" value="$isSeats"/>
										</parameters>
									</calltemplate>
									<calltemplate subtype="named" match="OverviewCandidateResult1-1-4">
										<parameters>
											<parameter name="fromLine" value="$fromLine"/>
											<parameter name="tillLine" value="$tillLine"/>
											<parameter name="remainingCandidates" value="$candidateCount - $tillLine"/>
										</parameters>
									</calltemplate>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="count(rg:CandidateAndResult[position() = 1]/rg:VotesInElectoralDistrictPart[position() = 1]/rg:VotesInElectoralDistrict) &gt; 4">
								<children>
									<calltemplate subtype="named" match="PartyHeaderEvenOdd">
										<parameters>
											<parameter name="isEvenOdd" value="1"/>
											<parameter name="isSeats" value="$isSeats"/>
										</parameters>
									</calltemplate>
									<calltemplate subtype="named" match="OverviewCandidateResult1-1-7">
										<parameters>
											<parameter name="fromLine" value="$fromLine"/>
											<parameter name="tillLine" value="$tillLine"/>
											<parameter name="remainingCandidates" value="$candidateCount - $tillLine"/>
											<parameter name="hasSecondPage" value="count(rg:CandidateAndResult[position() = 1]/rg:VotesInElectoralDistrictPart) = 2"/>
										</parameters>
									</calltemplate>
									<condition>
										<children>
											<conditionbranch xpath="count(rg:CandidateAndResult[position() = 1]/rg:VotesInElectoralDistrictPart) = 2">
												<children>
													<calltemplate subtype="named" match="PartyHeaderEvenOdd">
														<parameters>
															<parameter name="isEvenOdd" value="0"/>
															<parameter name="isSeats" value="$isSeats"/>
														</parameters>
													</calltemplate>
													<calltemplate subtype="named" match="OverviewCandidateResult1-8-20">
														<parameters>
															<parameter name="fromLine" value="$fromLine"/>
															<parameter name="tillLine" value="$tillLine"/>
															<parameter name="remainingCandidates" value="$candidateCount - $tillLine"/>
														</parameters>
													</calltemplate>
												</children>
											</conditionbranch>
										</children>
									</condition>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewCandidateResultP22-1-15">
				<parameters/>
				<children>
					<template subtype="element" match="rg:OverviewOfCandidatesAndResults">
						<children>
							<template subtype="element" match="rg:ListGroupAndResults">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="rg:Seats &gt; 0">
												<children>
													<newline/>
													<calltemplate subtype="named" match="PartyHeader3Lines">
														<parameters/>
													</calltemplate>
													<newline/>
													<newline/>
													<calltemplate subtype="named" match="OverviewCandidateResultElected">
														<parameters/>
													</calltemplate>
													<newline/>
													<calltemplate subtype="named" match="OverviewCandidateResultNewListOrder">
														<parameters/>
													</calltemplate>
													<newline/>
													<newline break="page"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewCandidateResultP22-2-H">
				<parameters/>
				<children>
					<template subtype="element" match="rg:OverviewOfCandidatesAndResults">
						<children>
							<template subtype="element" match="rg:ListGroupAndResults">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="rg:Seats &gt; 0">
												<children>
													<template subtype="element" match="rg:ListAndResults">
														<children>
															<calltemplate subtype="named" match="PartyHeader4Lines">
																<parameters>
																	<parameter name="withList" value="false()"/>
																	<parameter name="withVotes" value="true()"/>
																	<parameter name="toegewezen"/>
																	<parameter name="withListnumber" value="true()"/>
																</parameters>
															</calltemplate>
															<calltemplate subtype="named" match="O3-H-CandidateVotes">
																<parameters/>
															</calltemplate>
															<newline break="page"/>
															<calltemplate subtype="named" match="LineBreakRTF">
																<parameters/>
															</calltemplate>
														</children>
														<variables/>
													</template>
												</children>
											</conditionbranch>
										</children>
									</condition>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewCandidateResultP22-2">
				<parameters/>
				<children>
					<template subtype="element" match="rg:OverviewOfCandidatesAndResults">
						<children>
							<template subtype="element" filter="rg:Seats &gt; 0" match="rg:ListGroupAndResults">
								<children>
									<newline/>
									<template subtype="element" match="rg:ListAndResults">
										<children>
											<calltemplate subtype="named" match="PartyHeader4Lines">
												<parameters>
													<parameter name="withList"/>
													<parameter name="withVotes" value="false()"/>
													<parameter name="toegewezen" value="true()"/>
												</parameters>
											</calltemplate>
											<newline/>
										</children>
										<variables/>
									</template>
									<newline/>
									<calltemplate subtype="named" match="OverviewCandidateResultElected">
										<parameters/>
									</calltemplate>
									<newline/>
									<calltemplate subtype="named" match="OverviewCandidateResultNewListOrder">
										<parameters/>
									</calltemplate>
									<newline break="page"/>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewCandidateResultElected">
				<parameters>
					<parameter name="PublishGender" occurrence="none-or-one" default-value="rg:ListAndResults[ position() = 1 ]/kr:ListData/@PublishGender"/>
					<parameter name="PublicationLanguage" occurrence="none-or-one" default-value="rg:ListAndResults[ position() = 1 ]/kr:ListData/@PublicationLanguage"/>
					<parameter name="isEK" type="xs:int" occurrence="none-or-one" default-value="count(//eml:ElectionCategory[text() = &apos;EK&apos;])"/>
				</parameters>
				<children>
					<calltemplate subtype="named" match="LineBreakPdf">
						<parameters/>
					</calltemplate>
					<calltemplate subtype="named" match="LineBreakPdf">
						<parameters/>
					</calltemplate>
					<template subtype="element" match="rg:DeadCandidate">
						<children>
							<text fixtext="Bij de toewijzing van de zetels aan de kandidaten wordt ingevolge artikel P 19a van de Kieswet de kandidaat "/>
							<calltemplate subtype="named" match="CandidateInTable2">
								<parameters>
									<parameter name="PublishGender" value="$PublishGender"/>
									<parameter name="PublicationLanguage" value="$PublicationLanguage"/>
								</parameters>
							</calltemplate>
							<text fixtext=" buiten beschouwing gelaten, aangezien deze is overleden."/>
							<calltemplate subtype="named" match="LineBreak">
								<parameters/>
							</calltemplate>
							<condition>
								<children>
									<conditionbranch xpath="position() = last()">
										<children>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
						</children>
						<variables/>
					</template>
					<text fixtext="I. Met voorkeurstemmen gekozen kandidaten">
						<styles font-weight="bold"/>
					</text>
					<newline/>
					<newline/>
					<condition>
						<children>
							<conditionbranch xpath="rg:PriorityCandidate">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isEK = 0">
												<children>
													<text fixtext="De volgende kandidaten zijn met voorkeurstemmen gekozen. Deze kandidaten hebben "/>
													<condition>
														<children>
															<conditionbranch xpath="//kr:ElectionSubcategory[text() = &quot;EK&quot;]">
																<children>
																	<text fixtext="ten minste of gelijk aan"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<text fixtext="meer dan "/>
																	<condition>
																		<children>
																			<conditionbranch xpath="//kr:ElectionSubcategory[ text() = &quot;GR1&quot; or text()  = &quot;BC&quot; ]">
																				<children>
																					<text fixtext="50% van"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="//kr:ElectionSubcategory[ text()  = &quot;EP&quot; ]">
																				<children>
																					<text fixtext="10% van"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="//kr:ElectionSubcategory[ text()  = &quot;GC&quot; ]">
																				<children>
																					<text fixtext="100% van"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<text fixtext="25% van"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<text fixtext=" de kiesdeler gehaald."/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<text fixtext="Op basis van artikel U 15 jo. artikel P 15 en artikel P 16 van de Kieswet zijn de volgende kandidaten met voorkeurstemmen gekozen. Deze kandidaten hebben de kiesdeler behaald en krijgen een zetel toegewezen."/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<calltemplate subtype="named" match="LineBreakRTF">
										<parameters/>
									</calltemplate>
									<condition>
										<children>
											<conditionbranch xpath="$isEK = 1 or rg:Type = &apos;lijstengroep&apos;">
												<children>
													<newline/>
													<tgrid>
														<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
														<styles vertical-align="top"/>
														<children>
															<tgridbody-cols>
																<children>
																	<tgridcol>
																		<styles width="177pt"/>
																	</tgridcol>
																	<tgridcol>
																		<styles width="106pt"/>
																	</tgridcol>
																	<tgridcol>
																		<styles width="98pt"/>
																	</tgridcol>
																	<tgridcol>
																		<styles width="70pt"/>
																	</tgridcol>
																</children>
															</tgridbody-cols>
															<tgridheader-rows>
																<children>
																	<tgridrow>
																		<children>
																			<tgridcell>
																				<properties align="left"/>
																				<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
																				<children>
																					<text fixtext="naam">
																						<properties class="columnheader"/>
																					</text>
																				</children>
																			</tgridcell>
																			<tgridcell>
																				<properties align="left"/>
																				<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
																				<children>
																					<text fixtext="woonplaats">
																						<properties class="columnheader"/>
																					</text>
																				</children>
																			</tgridcell>
																			<tgridcell>
																				<properties align="right"/>
																				<styles border-bottom="1px solid #000" border-top="1px solid #000" padding-right="5pt" vertical-align="top"/>
																				<children>
																					<text fixtext="aantal stemmen op">
																						<properties class="columnheader"/>
																					</text>
																					<newline/>
																					<text fixtext="gezamenlijke lijsten">
																						<properties class="columnheader"/>
																					</text>
																				</children>
																			</tgridcell>
																			<tgridcell>
																				<properties align="left"/>
																				<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
																				<children>
																					<text fixtext="lijst waarop zetel">
																						<properties class="columnheader"/>
																					</text>
																					<newline/>
																					<text fixtext="wordt toegewezen">
																						<properties class="columnheader"/>
																					</text>
																				</children>
																			</tgridcell>
																		</children>
																	</tgridrow>
																</children>
															</tgridheader-rows>
															<tgridbody-rows>
																<children>
																	<template subtype="element" match="rg:PriorityCandidate">
																		<sort>
																			<key match="rg:Votes" datatype="Number" order="descending"/>
																		</sort>
																		<children>
																			<tgridrow>
																				<children>
																					<tgridcell>
																						<properties align="left"/>
																						<styles padding-top="2pt" vertical-align="top"/>
																						<children>
																							<calltemplate subtype="named" match="CandidateInTable2">
																								<parameters>
																									<parameter name="PublishGender" value="$PublishGender"/>
																									<parameter name="PublicationLanguage" value="$PublicationLanguage"/>
																								</parameters>
																							</calltemplate>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<properties align="left"/>
																						<styles padding-top="2pt" vertical-align="top"/>
																						<children>
																							<calltemplate subtype="named" match="CandidateCityInTable2">
																								<parameters/>
																							</calltemplate>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<properties align="right"/>
																						<styles padding-right="5pt" padding-top="2pt" vertical-align="top"/>
																						<children>
																							<template subtype="element" match="rg:Votes">
																								<children>
																									<content subtype="regular">
																										<format basic-type="xsd" datatype="integer"/>
																									</content>
																								</children>
																								<variables/>
																							</template>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<properties align="left"/>
																						<styles padding-top="2pt" vertical-align="top"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="@setNumber">
																										<children>
																											<text fixtext="Stel "/>
																											<template subtype="attribute" match="setNumber">
																												<children>
																													<content subtype="regular">
																														<format basic-type="xsd" datatype="integer"/>
																													</content>
																												</children>
																												<variables/>
																											</template>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="@districtNumber">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="//eml:ElectionCategory[ text() != &quot;EK&quot; ]">
																														<children>
																															<text fixtext="Kieskring "/>
																															<template subtype="attribute" match="districtNumber">
																																<children>
																																	<content subtype="regular">
																																		<format basic-type="xsd" datatype="integer"/>
																																	</content>
																																</children>
																																<variables/>
																															</template>
																														</children>
																													</conditionbranch>
																													<conditionbranch>
																														<children>
																															<template subtype="attribute" match="provinceName">
																																<children>
																																	<content subtype="regular">
																																		<format basic-type="xsd" datatype="integer"/>
																																	</content>
																																</children>
																																<variables/>
																															</template>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</tgridcell>
																				</children>
																			</tgridrow>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridbody-rows>
														</children>
														<wizard-data-repeat>
															<children/>
														</wizard-data-repeat>
														<wizard-data-rows>
															<children/>
														</wizard-data-rows>
														<wizard-data-columns>
															<children/>
														</wizard-data-columns>
													</tgrid>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<newline/>
													<tgrid>
														<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
														<styles vertical-align="top"/>
														<children>
															<tgridbody-cols>
																<children>
																	<tgridcol>
																		<styles width="50%"/>
																	</tgridcol>
																	<tgridcol/>
																	<tgridcol>
																		<styles width="110pt"/>
																	</tgridcol>
																</children>
															</tgridbody-cols>
															<tgridheader-rows>
																<children>
																	<tgridrow>
																		<children>
																			<tgridcell>
																				<properties align="left"/>
																				<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
																				<children>
																					<text fixtext="naam">
																						<properties class="columnheader"/>
																					</text>
																				</children>
																			</tgridcell>
																			<tgridcell>
																				<properties align="left"/>
																				<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
																				<children>
																					<text fixtext="woonplaats">
																						<properties class="columnheader"/>
																					</text>
																				</children>
																			</tgridcell>
																			<tgridcell>
																				<properties align="right"/>
																				<styles border-bottom="1px solid #000" border-top="1px solid #000" padding-right="5pt" vertical-align="top"/>
																				<children>
																					<text fixtext="aantal stemmen">
																						<properties class="columnheader"/>
																					</text>
																				</children>
																			</tgridcell>
																		</children>
																	</tgridrow>
																</children>
															</tgridheader-rows>
															<tgridbody-rows>
																<children>
																	<template subtype="element" match="rg:PriorityCandidate">
																		<sort>
																			<key match="rg:Votes" datatype="Number" order="descending"/>
																		</sort>
																		<children>
																			<tgridrow>
																				<children>
																					<tgridcell>
																						<properties align="left"/>
																						<styles padding-top="2pt" vertical-align="top"/>
																						<children>
																							<calltemplate subtype="named" match="CandidateInTable2">
																								<parameters>
																									<parameter name="PublishGender" value="$PublishGender"/>
																									<parameter name="PublicationLanguage" value="$PublicationLanguage"/>
																								</parameters>
																							</calltemplate>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<properties align="left"/>
																						<styles padding-top="2pt" vertical-align="top"/>
																						<children>
																							<calltemplate subtype="named" match="CandidateCityInTable2">
																								<parameters/>
																							</calltemplate>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<properties align="right"/>
																						<styles padding-right="5pt" padding-top="2pt" vertical-align="top"/>
																						<children>
																							<template subtype="element" match="rg:Votes">
																								<children>
																									<content subtype="regular">
																										<format basic-type="xsd" datatype="integer"/>
																									</content>
																								</children>
																								<variables/>
																							</template>
																						</children>
																					</tgridcell>
																				</children>
																			</tgridrow>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridbody-rows>
														</children>
														<wizard-data-repeat>
															<children/>
														</wizard-data-repeat>
														<wizard-data-rows>
															<children/>
														</wizard-data-rows>
														<wizard-data-columns>
															<children/>
														</wizard-data-columns>
													</tgrid>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<calltemplate subtype="named" match="LineBreakTable">
										<parameters/>
									</calltemplate>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="count( rg:ListAndResults/rg:CandidateAndResult/rg:Elected[ @byLot = &quot;true&quot; ] ) &gt; 0">
								<children>
									<text fixtext="Overeenkomstig artikel P 15, eerste lid van de Kieswet heeft een loting plaatsgevonden tuss"/>
									<template subtype="element" match="rg:ListAndResults">
										<children>
											<template subtype="element" match="rg:CandidateAndResult">
												<sort>
													<key match="rg:TotalVotes" datatype="Number" order="descending"/>
												</sort>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="rg:Elected/@byLot = &quot;true&quot;">
																<children>
																	<text fixtext="en "/>
																	<calltemplate subtype="named" match="CandidateInTable2">
																		<parameters>
																			<parameter name="PublishGender" value="$PublishGender"/>
																			<parameter name="PublicationLanguage" value="$PublicationLanguage"/>
																		</parameters>
																	</calltemplate>
																	<text fixtext=" "/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
												<variables/>
											</template>
										</children>
										<variables/>
									</template>
									<text fixtext=". Als gevolg hiervan is/zijn gekozen "/>
									<newline/>
									<tgrid>
										<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
										<styles vertical-align="top"/>
										<children>
											<tgridbody-cols>
												<children>
													<tgridcol/>
													<tgridcol/>
												</children>
											</tgridbody-cols>
											<tgridbody-rows>
												<children>
													<tgridrow>
														<children>
															<tgridcell>
																<properties align="left"/>
																<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top" width="60%"/>
																<children>
																	<text fixtext="Naam"/>
																</children>
															</tgridcell>
															<tgridcell>
																<properties align="left"/>
																<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
																<children>
																	<text fixtext="Woonplaats"/>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
													<template subtype="element" match="rg:ListAndResults">
														<children>
															<template subtype="element" filter="rg:Elected = &quot;true&quot; and rg:Elected/@byLot = &quot;true&quot;" match="rg:CandidateAndResult">
																<sort>
																	<key match="rg:TotalVotes" datatype="Number" order="descending"/>
																</sort>
																<children>
																	<tgridrow>
																		<children>
																			<tgridcell>
																				<properties align="left"/>
																				<styles vertical-align="top" width="60%"/>
																				<children>
																					<calltemplate subtype="named" match="CandidateInTable2">
																						<parameters>
																							<parameter name="PublishGender" value="$PublishGender"/>
																							<parameter name="PublicationLanguage" value="$PublicationLanguage"/>
																						</parameters>
																					</calltemplate>
																				</children>
																			</tgridcell>
																			<tgridcell>
																				<properties align="left"/>
																				<styles vertical-align="top"/>
																				<children>
																					<calltemplate subtype="named" match="CandidateCityInTable2">
																						<parameters/>
																					</calltemplate>
																				</children>
																			</tgridcell>
																		</children>
																	</tgridrow>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
												</children>
											</tgridbody-rows>
										</children>
										<wizard-data-repeat>
											<children/>
										</wizard-data-repeat>
										<wizard-data-rows>
											<children/>
										</wizard-data-rows>
										<wizard-data-columns>
											<children/>
										</wizard-data-columns>
									</tgrid>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<calltemplate subtype="named" match="LineBreakTable">
						<parameters/>
					</calltemplate>
					<text fixtext="II. Overige gekozen kandidaten">
						<styles font-weight="bold"/>
					</text>
					<newline/>
					<newline/>
					<condition>
						<children>
							<conditionbranch xpath="$isEK = 0">
								<children>
									<text fixtext="De overige aan de lijst toegewezen zetels gaan naar de volgende kandidaten."/>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<text fixtext="Op basis van artikel U 15 jo. artikel P 17 en artikel P 18 van de Kieswet gaan de overige aan de lijst toegewezen zetels naar de volgende kandidaten:"/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<newline/>
					<condition>
						<children>
							<conditionbranch xpath="rg:CandidatesElectedByPositionOnList/rg:CandidateElectedByPositionOnList">
								<children>
									<calltemplate subtype="named" match="LineBreakPdf">
										<parameters/>
									</calltemplate>
									<template subtype="element" match="rg:CandidatesElectedByPositionOnList">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="../rg:Type = &quot;lijstengroep&quot;">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="position() &gt; 1">
																		<children>
																			<newline/>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<calltemplate subtype="named" match="LineBreakRTF">
																				<parameters/>
																			</calltemplate>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<condition>
																<children>
																	<conditionbranch xpath="@setNumber">
																		<children>
																			<text fixtext="Stel gelijkluidende lijsten: "/>
																			<template subtype="attribute" match="setNumber">
																				<children>
																					<content subtype="regular">
																						<format basic-type="xsd" datatype="integer"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="@districtNumber">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="//eml:ElectionCategory[ text() != &quot;EK&quot; ]">
																						<children>
																							<text fixtext="Kieskring: "/>
																							<template subtype="attribute" match="districtNumber">
																								<children>
																									<content subtype="regular">
																										<format basic-type="xsd" datatype="integer"/>
																									</content>
																								</children>
																								<variables/>
																							</template>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<text fixtext="Provincie: "/>
																							<template subtype="attribute" match="provinceName">
																								<children>
																									<content subtype="regular">
																										<format basic-type="xsd" datatype="integer"/>
																									</content>
																								</children>
																								<variables/>
																							</template>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<newline/>
											<tgrid>
												<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
												<styles vertical-align="top"/>
												<children>
													<tgridbody-cols>
														<children>
															<tgridcol>
																<styles width="50%"/>
															</tgridcol>
															<tgridcol/>
															<tgridcol>
																<styles width="110pt"/>
															</tgridcol>
														</children>
													</tgridbody-cols>
													<tgridheader-rows>
														<children>
															<tgridrow>
																<children>
																	<tgridcell>
																		<properties align="left"/>
																		<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
																		<children>
																			<text fixtext="naam">
																				<properties class="columnheader"/>
																			</text>
																		</children>
																	</tgridcell>
																	<tgridcell>
																		<properties align="left"/>
																		<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
																		<children>
																			<text fixtext="woonplaats">
																				<properties class="columnheader"/>
																			</text>
																		</children>
																	</tgridcell>
																	<tgridcell>
																		<properties align="right"/>
																		<styles border-bottom="1px solid #000" border-top="1px solid #000" padding-right="5pt" vertical-align="top"/>
																		<children>
																			<text fixtext="aantal stemmen">
																				<properties class="columnheader"/>
																			</text>
																		</children>
																	</tgridcell>
																</children>
															</tgridrow>
														</children>
													</tgridheader-rows>
													<tgridbody-rows>
														<children>
															<template subtype="element" match="rg:CandidateElectedByPositionOnList">
																<children>
																	<tgridrow>
																		<children>
																			<tgridcell>
																				<properties align="left"/>
																				<styles padding-top="2pt" vertical-align="top"/>
																				<children>
																					<calltemplate subtype="named" match="CandidateInTable2">
																						<parameters>
																							<parameter name="PublishGender" value="$PublishGender"/>
																							<parameter name="PublicationLanguage" value="$PublicationLanguage"/>
																						</parameters>
																					</calltemplate>
																				</children>
																			</tgridcell>
																			<tgridcell>
																				<properties align="left"/>
																				<styles padding-top="2pt" vertical-align="top"/>
																				<children>
																					<calltemplate subtype="named" match="CandidateCityInTable2">
																						<parameters/>
																					</calltemplate>
																				</children>
																			</tgridcell>
																			<tgridcell>
																				<properties align="right"/>
																				<styles padding-right="5pt" padding-top="2pt" vertical-align="top"/>
																				<children>
																					<template subtype="element" match="rg:Votes">
																						<children>
																							<content subtype="regular">
																								<format basic-type="xsd" datatype="integer"/>
																							</content>
																						</children>
																						<variables/>
																					</template>
																				</children>
																			</tgridcell>
																		</children>
																	</tgridrow>
																</children>
																<variables/>
															</template>
														</children>
													</tgridbody-rows>
												</children>
												<wizard-data-repeat>
													<children/>
												</wizard-data-repeat>
												<wizard-data-rows>
													<children/>
												</wizard-data-rows>
												<wizard-data-columns>
													<children/>
												</wizard-data-columns>
											</tgrid>
										</children>
										<variables/>
									</template>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewCandidateResultNewListOrder">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="This fragment must start on the page with the elected candidates. First there is the text if the order was changed or not. Now a page break BEFORE each stel or kieskring, but NOT at the END."/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<calltemplate subtype="named" match="LineBreakPdf">
						<parameters/>
					</calltemplate>
					<calltemplate subtype="named" match="LineBreakPdf">
						<parameters/>
					</calltemplate>
					<newline/>
					<text fixtext="III. Rangschikking kandidaten">
						<styles font-weight="bold"/>
					</text>
					<newline/>
					<newline/>
					<text fixtext="De kandidaten zijn gerangschikt in de volgorde zoals hiervoor is aangegeven."/>
					<newline/>
					<newline/>
					<condition>
						<children>
							<conditionbranch xpath="rg:ListAndResults/rg:PositionsOnListChanged[text() = &quot;true&quot;]">
								<children>
									<text fixtext="Uitzondering hierop zijn de volgende lijsten:"/>
									<newline/>
									<template subtype="element" match="rg:ListAndResults">
										<children>
											<newline break="page"/>
											<newline/>
											<calltemplate subtype="named" match="PartyHeader2Lines">
												<parameters/>
											</calltemplate>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
														<children>
															<newline/>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<text fixtext="Kandidaten in volgorde van rangschikking:"/>
											<newline/>
											<newline/>
											<tgrid>
												<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
												<styles vertical-align="top"/>
												<children>
													<tgridbody-cols>
														<children>
															<tgridcol/>
														</children>
													</tgridbody-cols>
													<tgridbody-rows>
														<children>
															<template subtype="element" filter="rg:Deceased = &quot;false&quot;" match="rg:CandidateAndResult">
																<sort>
																	<key match="rg:NewPositionOnList" datatype="Number"/>
																</sort>
																<children>
																	<tgridrow>
																		<children>
																			<tgridcell>
																				<properties align="left"/>
																				<styles vertical-align="top"/>
																				<children>
																					<calltemplate subtype="named" match="CandidateInTable2">
																						<parameters>
																							<parameter name="PublishGender" value="../kr:ListData/@PublishGender"/>
																							<parameter name="PublicationLanguage" value="../kr:ListData/@PublicationLanguage"/>
																						</parameters>
																					</calltemplate>
																				</children>
																			</tgridcell>
																		</children>
																	</tgridrow>
																</children>
																<variables/>
															</template>
														</children>
													</tgridbody-rows>
												</children>
												<wizard-data-repeat>
													<children/>
												</wizard-data-repeat>
												<wizard-data-rows>
													<children/>
												</wizard-data-rows>
												<wizard-data-columns>
													<children/>
												</wizard-data-columns>
											</tgrid>
										</children>
										<variables/>
									</template>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewElectedCandidate">
				<parameters/>
				<children>
					<template subtype="element" match="rg:OverviewOfElectedCandidates">
						<children>
							<tgrid>
								<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
								<styles vertical-align="top"/>
								<children>
									<tgridbody-cols>
										<children>
											<tgridcol/>
											<tgridcol/>
										</children>
									</tgridbody-cols>
									<tgridheader-rows>
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" width="60%"/>
														<children>
															<text fixtext="naam kandidaat">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
														<children>
															<text fixtext="woonplaats">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
									</tgridheader-rows>
									<tgridbody-rows>
										<children>
											<template subtype="element" match="rg:ElectedCandidate">
												<children>
													<template subtype="element" match="eml:Candidate">
														<children>
															<tgridrow>
																<children>
																	<tgridcell>
																		<properties align="left"/>
																		<styles padding-top="2pt" vertical-align="top" width="60%"/>
																		<children>
																			<template subtype="element" match="eml:CandidateFullName">
																				<children>
																					<calltemplate subtype="named" match="LastNameH1">
																						<parameters/>
																					</calltemplate>
																					<text fixtext=", "/>
																					<calltemplate subtype="named" match="FirstNameH1">
																						<parameters/>
																					</calltemplate>
																				</children>
																				<variables/>
																			</template>
																			<calltemplate subtype="named" match="GenderGeneral">
																				<parameters>
																					<parameter name="Gender" value="eml:Gender"/>
																					<parameter name="PublishGender" value="$PublishGender"/>
																					<parameter name="PublicationLanguage" value="$PublicationLanguage"/>
																					<parameter name="isBold"/>
																					<parameter name="fontSize"/>
																				</parameters>
																			</calltemplate>
																		</children>
																	</tgridcell>
																	<tgridcell>
																		<properties align="left"/>
																		<styles padding-top="2pt" vertical-align="top"/>
																		<children>
																			<template subtype="element" match="eml:QualifyingAddress">
																				<children>
																					<calltemplate subtype="named" match="City">
																						<parameters/>
																					</calltemplate>
																					<template subtype="element" match="xal:Country">
																						<children>
																							<calltemplate subtype="named" match="City">
																								<parameters/>
																							</calltemplate>
																							<text fixtext=" "/>
																							<template subtype="element" match="xal:CountryNameCode">
																								<children>
																									<text fixtext="("/>
																									<content subtype="regular"/>
																									<text fixtext=")"/>
																								</children>
																								<variables/>
																							</template>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
																		</children>
																	</tgridcell>
																</children>
															</tgridrow>
														</children>
														<variables/>
													</template>
												</children>
												<variables>
													<variable name="PublishGender" value="kr:ListData/@PublishGender" valuetype="&lt;auto&gt;"/>
													<variable name="PublicationLanguage" value="kr:ListData/@PublicationLanguage" valuetype="&lt;auto&gt;"/>
												</variables>
											</template>
										</children>
									</tgridbody-rows>
								</children>
								<wizard-data-repeat>
									<children/>
								</wizard-data-repeat>
								<wizard-data-rows>
									<children/>
								</wizard-data-rows>
								<wizard-data-columns>
									<children/>
								</wizard-data-columns>
							</tgrid>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewCandidateListsEP">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0"/>
						<styles border-bottom="1px solid #000" font-size="smaller"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="60pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="400pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
												<children>
													<text fixtext="Lijstnr."/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
												<children>
													<text fixtext="aanduiding"/>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridheader-rows>
							<tgridbody-rows>
								<children>
									<template subtype="element" match="rg:OverviewOfListsAndDistricts">
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<styles margin-right="15pt" text-align="right" vertical-align="top"/>
														<children>
															<template subtype="element" match="rg:CandidateListName">
																<children>
																	<template subtype="element" match="eml:AffiliationIdentifier">
																		<children>
																			<template subtype="attribute" match="Id">
																				<children>
																					<content subtype="regular">
																						<format basic-type="xsd" datatype="NMTOKEN"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<styles vertical-align="top"/>
														<children>
															<template subtype="element" match="rg:CandidateListName">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$SV_OutputFormat = &apos;RTF&apos;">
																				<children>
																					<template subtype="element" match="eml:AffiliationIdentifier">
																						<children>
																							<template subtype="element" match="eml:RegisteredName">
																								<children>
																									<content subtype="regular">
																										<format basic-type="xsd" datatype="token"/>
																									</content>
																								</children>
																								<variables/>
																							</template>
																						</children>
																						<variables/>
																					</template>
																					<text fixtext=" "/>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
																				<children>
																					<paragraph paragraphtag="pre-wrap">
																						<styles white-space="pre-wrap"/>
																						<children>
																							<template subtype="element" match="eml:AffiliationIdentifier">
																								<children>
																									<template subtype="element" match="eml:RegisteredName">
																										<children>
																											<content subtype="regular">
																												<format basic-type="xsd" datatype="token"/>
																											</content>
																										</children>
																										<variables/>
																									</template>
																								</children>
																								<variables/>
																							</template>
																							<text fixtext=" "/>
																						</children>
																					</paragraph>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ListOrCombinedList">
				<parameters>
					<parameter name="smaller" type="xs:int" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$smaller = 0">
								<children>
									<template subtype="element" match="rg:ListOrCombinedList">
										<children>
											<template subtype="attribute" match="listNumber">
												<children>
													<text fixtext="lijst "/>
													<content subtype="regular">
														<format basic-type="xsd" datatype="integer"/>
													</content>
												</children>
												<variables/>
											</template>
											<condition>
												<children>
													<conditionbranch xpath="count( rg:ListInCombination ) &gt; 0">
														<children>
															<text fixtext=" ("/>
															<template subtype="element" match="rg:ListInCombination">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<condition>
																		<children>
																			<conditionbranch xpath="position() != last()">
																				<children>
																					<text fixtext=", "/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
															<text fixtext=")"/>
														</children>
													</conditionbranch>
												</children>
											</condition>
										</children>
										<variables/>
									</template>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$smaller = 1">
								<children>
									<template subtype="element" match="rg:ListOrCombinedList">
										<children>
											<template subtype="attribute" match="combinationId">
												<children>
													<text fixtext="lijstencombinatie ">
														<styles font-size="smaller"/>
													</text>
													<content subtype="regular">
														<styles font-size="smaller"/>
														<format basic-type="xsd" datatype="token"/>
													</content>
												</children>
												<variables/>
											</template>
											<template subtype="attribute" match="listNumber">
												<children>
													<text fixtext="lijst ">
														<styles font-size="smaller"/>
													</text>
													<content subtype="regular">
														<styles font-size="smaller"/>
														<format basic-type="xsd" datatype="integer"/>
													</content>
												</children>
												<variables/>
											</template>
											<condition>
												<children>
													<conditionbranch xpath="count( rg:ListInCombination ) &gt; 0">
														<children>
															<text fixtext=" (">
																<styles font-size="smaller"/>
															</text>
															<template subtype="element" match="rg:ListInCombination">
																<children>
																	<content subtype="regular">
																		<styles font-size="smaller"/>
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<condition>
																		<children>
																			<conditionbranch xpath="position() != last()">
																				<children>
																					<text fixtext=", ">
																						<styles font-size="smaller"/>
																					</text>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
															<text fixtext=")">
																<styles font-size="smaller"/>
															</text>
														</children>
													</conditionbranch>
												</children>
											</condition>
										</children>
										<variables/>
									</template>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewDHondtAssignmentSpecial">
				<parameters/>
				<children>
					<template subtype="element" match="rg:AbsoluteMajority">
						<children>
							<calltemplate subtype="named" match="LineBreak">
								<parameters/>
							</calltemplate>
							<text fixtext="Door toepassing van artikel P 9 van de Kieswet is een extra zetel toegewezen aan "/>
							<template subtype="element" match="rg:Winner">
								<children>
									<calltemplate subtype="named" match="ListOrCombinedList">
										<parameters>
											<parameter name="smaller"/>
										</parameters>
									</calltemplate>
								</children>
								<variables/>
							</template>
							<text fixtext=". "/>
							<condition>
								<children>
									<conditionbranch xpath="count( rg:Allotting ) = 0">
										<children>
											<text fixtext="Daartegenover vervalt een zetel die was toegewezen aan "/>
											<template subtype="element" match="rg:Looser">
												<children>
													<calltemplate subtype="named" match="ListOrCombinedList">
														<parameters>
															<parameter name="smaller"/>
														</parameters>
													</calltemplate>
												</children>
												<variables/>
											</template>
											<text fixtext="."/>
										</children>
									</conditionbranch>
									<conditionbranch>
										<children>
											<template subtype="element" match="rg:Allotting">
												<children>
													<text fixtext="Daartegenover heeft een loting plaatsgevonden tussen "/>
													<template subtype="element" match="rg:Winner">
														<children>
															<calltemplate subtype="named" match="ListOrCombinedList">
																<parameters>
																	<parameter name="smaller"/>
																</parameters>
															</calltemplate>
														</children>
														<variables/>
													</template>
													<template subtype="element" match="rg:Looser">
														<children>
															<text fixtext=" en "/>
															<calltemplate subtype="named" match="ListOrCombinedList">
																<parameters>
																	<parameter name="smaller"/>
																</parameters>
															</calltemplate>
														</children>
														<variables/>
													</template>
													<text fixtext=". Als gevolg hiervan vervalt een zetel die was toegewezen aan "/>
													<template subtype="element" match="rg:Winner">
														<children>
															<calltemplate subtype="named" match="ListOrCombinedList">
																<parameters>
																	<parameter name="smaller"/>
																</parameters>
															</calltemplate>
														</children>
														<variables/>
													</template>
													<text fixtext="."/>
												</children>
												<variables/>
											</template>
										</children>
									</conditionbranch>
								</children>
							</condition>
						</children>
						<variables/>
					</template>
					<template subtype="element" match="rg:ExhaustedList">
						<children>
							<calltemplate subtype="named" match="LineBreak">
								<parameters/>
							</calltemplate>
							<text fixtext="Door toepassing van artikel "/>
							<condition>
								<children>
									<conditionbranch xpath="//kr:ElectionSubcategory[ text() != &quot;EK&quot; ]">
										<children>
											<text fixtext="P 10"/>
										</children>
									</conditionbranch>
									<conditionbranch>
										<children>
											<text fixtext="U 10"/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<text fixtext=" van de Kieswet "/>
							<condition>
								<children>
									<conditionbranch xpath="@lostSeats = 1">
										<children>
											<text fixtext="is"/>
										</children>
									</conditionbranch>
									<conditionbranch>
										<children>
											<text fixtext="zijn"/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<text fixtext=" als gevolg van lijstuitputting bij "/>
							<calltemplate subtype="named" match="ListOrCombinedList">
								<parameters>
									<parameter name="smaller"/>
								</parameters>
							</calltemplate>
							<text fixtext=" "/>
							<condition>
								<children>
									<conditionbranch xpath="@lostSeats = 1">
										<children>
											<text fixtext="een extra zetel toegewezen aan een ander lijst"/>
										</children>
									</conditionbranch>
									<conditionbranch>
										<children>
											<template subtype="attribute" match="lostSeats">
												<children>
													<content subtype="regular">
														<format basic-type="xsd" datatype="integer"/>
													</content>
												</children>
												<variables/>
											</template>
											<text fixtext=" extra zetels toegewezen aan andere lijsten"/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<text fixtext="."/>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="CandidateInTable">
				<parameters>
					<parameter name="PublishGender" type="xs:string"/>
					<parameter name="PublicationLanguage" type="xs:string"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="false()">
								<children>
									<text fixtext="In font-size=smaller"/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<template subtype="element" match="eml:Candidate">
						<children>
							<template subtype="element" match="eml:CandidateFullName">
								<children>
									<calltemplate subtype="named" match="LastNameH1">
										<parameters>
											<parameter name="isBold"/>
											<parameter name="fontSize" value="&apos;smaller&apos;"/>
										</parameters>
									</calltemplate>
									<text fixtext=", ">
										<styles font-size="smaller"/>
									</text>
									<calltemplate subtype="named" match="FirstNameH1">
										<parameters>
											<parameter name="isBold"/>
											<parameter name="fontSize" value="&apos;smaller&apos;"/>
										</parameters>
									</calltemplate>
								</children>
								<variables/>
							</template>
							<calltemplate subtype="named" match="GenderGeneral">
								<parameters>
									<parameter name="Gender" value="eml:Gender"/>
									<parameter name="PublishGender" value="$PublishGender"/>
									<parameter name="PublicationLanguage" value="$PublicationLanguage"/>
									<parameter name="isBold"/>
									<parameter name="fontSize" value="&apos;smaller&apos;"/>
								</parameters>
							</calltemplate>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="CandidateInTable2">
				<parameters>
					<parameter name="PublishGender" type="xs:string"/>
					<parameter name="PublicationLanguage" type="xs:string"/>
				</parameters>
				<children>
					<template subtype="element" match="eml:Candidate">
						<children>
							<template subtype="element" match="eml:CandidateFullName">
								<children>
									<calltemplate subtype="named" match="LastNameH1">
										<parameters>
											<parameter name="isBold"/>
											<parameter name="fontSize"/>
										</parameters>
									</calltemplate>
									<text fixtext=", "/>
									<calltemplate subtype="named" match="FirstNameH1">
										<parameters>
											<parameter name="isBold"/>
											<parameter name="fontSize"/>
										</parameters>
									</calltemplate>
								</children>
								<variables/>
							</template>
							<calltemplate subtype="named" match="GenderGeneral">
								<parameters>
									<parameter name="Gender" value="eml:Gender"/>
									<parameter name="PublishGender" value="$PublishGender"/>
									<parameter name="PublicationLanguage" value="$PublicationLanguage"/>
									<parameter name="isBold"/>
									<parameter name="fontSize"/>
								</parameters>
							</calltemplate>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="CandidateCityInTable2">
				<parameters/>
				<children>
					<template subtype="element" match="eml:Candidate">
						<children>
							<template subtype="element" match="eml:QualifyingAddress">
								<children>
									<calltemplate subtype="named" match="City">
										<parameters/>
									</calltemplate>
									<template subtype="element" match="xal:Country">
										<children>
											<calltemplate subtype="named" match="City">
												<parameters/>
											</calltemplate>
											<text fixtext=" "/>
											<template subtype="element" match="xal:CountryNameCode">
												<children>
													<text fixtext="("/>
													<content subtype="regular"/>
													<text fixtext=")"/>
												</children>
												<variables/>
											</template>
										</children>
										<variables/>
									</template>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="PartyHeader2Lines">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="../rg:Type = &quot;lijstengroep&quot;">
								<children>
									<tgrid>
										<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
										<styles vertical-align="top"/>
										<children>
											<tgridbody-cols>
												<children>
													<tgridcol>
														<styles width="160pt"/>
													</tgridcol>
													<tgridcol/>
													<tgridcol>
														<styles width="170pt"/>
													</tgridcol>
												</children>
											</tgridbody-cols>
											<tgridbody-rows>
												<children>
													<tgridrow>
														<children>
															<tgridcell>
																<properties align="left"/>
																<styles border-top="1px solid #000"/>
																<children>
																	<text fixtext="Aanduiding politieke groepering"/>
																</children>
															</tgridcell>
															<tgridcell>
																<properties align="left"/>
																<styles border-top="1px solid #000"/>
																<children>
																	<calltemplate subtype="named" match="PartyNamePdfWrapped">
																		<parameters/>
																	</calltemplate>
																</children>
															</tgridcell>
															<tgridcell joinleft="1"/>
														</children>
													</tgridrow>
													<tgridrow>
														<children>
															<tgridcell>
																<properties align="left"/>
																<children>
																	<text fixtext="Lijstengroep:"/>
																</children>
															</tgridcell>
															<tgridcell>
																<properties align="left"/>
																<children>
																	<template subtype="element" match="eml:AffiliationIdentifier">
																		<children>
																			<template subtype="attribute" match="Id">
																				<children>
																					<content subtype="regular">
																						<format basic-type="xsd" datatype="NMTOKEN"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<properties align="left"/>
																<styles width="200pt"/>
															</tgridcell>
														</children>
													</tgridrow>
													<tgridrow>
														<children>
															<tgridcell>
																<properties align="left"/>
																<styles border-bottom="1px solid #000"/>
																<children>
																	<template subtype="element" match="rg:Type">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="text() = &quot;op zichzelf staande lijst&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="//kr:ElectionSubcategory[ text() != &quot;EK&quot; ]">
																										<children>
																											<text fixtext="Kieskring:"/>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<text fixtext="Provincie:"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="text() = &quot;stel gelijkluidende lijsten&quot;">
																						<children>
																							<text fixtext="Stel:"/>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<properties align="left"/>
																<styles border-bottom="1px solid #000"/>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="rg:Type/text() = &quot;op zichzelf staande lijst&quot;">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="//eml:ElectionCategory[ text() != &quot;EK&quot; ]">
																								<children>
																									<autocalc xpath="@districtNumber"/>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="@provinceName"/>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<template subtype="element" match="kr:ListData">
																						<children>
																							<template subtype="attribute" match="BelongsToSet">
																								<children>
																									<content subtype="regular">
																										<format basic-type="xsd" datatype="positiveInteger"/>
																									</content>
																								</children>
																								<variables/>
																							</template>
																						</children>
																						<variables/>
																					</template>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</tgridcell>
															<tgridcell>
																<properties align="left"/>
																<styles border-bottom="1px solid #000"/>
															</tgridcell>
														</children>
													</tgridrow>
												</children>
											</tgridbody-rows>
										</children>
										<wizard-data-repeat>
											<children/>
										</wizard-data-repeat>
										<wizard-data-rows>
											<children/>
										</wizard-data-rows>
										<wizard-data-columns>
											<children/>
										</wizard-data-columns>
									</tgrid>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<tgrid>
										<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
										<styles vertical-align="top"/>
										<children>
											<tgridbody-cols>
												<children>
													<tgridcol>
														<styles width="160pt"/>
													</tgridcol>
													<tgridcol/>
												</children>
											</tgridbody-cols>
											<tgridbody-rows>
												<children>
													<tgridrow>
														<children>
															<tgridcell>
																<properties align="left"/>
																<styles border-top="1px solid #000"/>
																<children>
																	<text fixtext="Aanduiding van de groepering:"/>
																</children>
															</tgridcell>
															<tgridcell>
																<properties align="left"/>
																<styles border-top="1px solid #000"/>
																<children>
																	<template subtype="element" match="eml:AffiliationIdentifier">
																		<children>
																			<template subtype="element" match="eml:RegisteredName">
																				<children>
																					<content subtype="regular">
																						<format basic-type="xsd" datatype="token"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
													<tgridrow>
														<children>
															<tgridcell>
																<properties align="left"/>
																<styles border-bottom="1px solid #000"/>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="//kr:ElectionSubcategory[ text() != &quot;TK&quot; and text() != &quot;PS2&quot; ]">
																				<children>
																					<text fixtext="Lijst:"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="../rg:Type = &quot;op zichzelf staande lijst&quot;">
																				<children>
																					<text fixtext="Op zichzelf staande lijst:"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="../rg:Type = &quot;stel gelijkluidende lijsten&quot;">
																				<children>
																					<text fixtext="Stel gelijkluidende lijsten:"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</tgridcell>
															<tgridcell>
																<properties align="left"/>
																<styles border-bottom="1px solid #000"/>
																<children>
																	<template subtype="element" match="eml:AffiliationIdentifier">
																		<children>
																			<template subtype="attribute" match="Id">
																				<children>
																					<content subtype="regular">
																						<format basic-type="xsd" datatype="NMTOKEN"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
												</children>
											</tgridbody-rows>
										</children>
										<wizard-data-repeat>
											<children/>
										</wizard-data-repeat>
										<wizard-data-rows>
											<children/>
										</wizard-data-rows>
										<wizard-data-columns>
											<children/>
										</wizard-data-columns>
									</tgrid>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="PartyHeader3Lines">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles vertical-align="top"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="160pt"/>
									</tgridcol>
									<tgridcol/>
								</children>
							</tgridbody-cols>
							<tgridbody-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<text fixtext="Aanduiding politieke groepering"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="PartyNamePdfWrapped">
														<parameters/>
													</calltemplate>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<children>
													<text fixtext="Lijst"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<children>
													<template subtype="element" match="eml:AffiliationIdentifier">
														<children>
															<template subtype="attribute" match="Id">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="NMTOKEN"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<text fixtext="Toegewezen aantal zetels"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<template subtype="element" match="rg:Seats">
														<children>
															<content subtype="regular">
																<format basic-type="xsd" datatype="integer"/>
															</content>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="PartyHeader4Lines">
				<parameters>
					<parameter name="withList" occurrence="none-or-one" default-value="true()"/>
					<parameter name="withListnumber" occurrence="none-or-one" default-value="false()"/>
					<parameter name="withVotes" occurrence="none-or-one" default-value="true()"/>
					<parameter name="toegewezen" type="xs:boolean" occurrence="none-or-one" default-value="false()"/>
				</parameters>
				<children>
					<template subtype="userdefined" match="self::*">
						<children>
							<tgrid>
								<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
								<styles vertical-align="top"/>
								<children>
									<tgridbody-cols>
										<children>
											<tgridcol>
												<styles width="160pt"/>
											</tgridcol>
											<tgridcol/>
										</children>
									</tgridbody-cols>
									<tgridbody-rows>
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="left"/>
														<styles border-top="1px solid #000"/>
														<children>
															<text fixtext="Aanduiding politieke groepering:"/>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<styles border-top="1px solid #000"/>
														<children>
															<calltemplate subtype="named" match="PartyNamePdfWrapped">
																<parameters/>
															</calltemplate>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
											<tgridrow conditional-processing="$withList and $isListengroup">
												<children>
													<tgridcell>
														<properties align="left"/>
														<children>
															<text fixtext="Lijstengroep:"/>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<children>
															<template subtype="element" match="eml:AffiliationIdentifier">
																<children>
																	<template subtype="attribute" match="Id">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="NMTOKEN"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
											<tgridrow conditional-processing="$withList and $isListengroup">
												<children>
													<tgridcell>
														<properties align="left"/>
														<children>
															<template subtype="element" match="rg:Type">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="text() = &quot;op zichzelf staande lijst&quot;">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="//kr:ElectionSubcategory[ text() != &quot;EK&quot; ]">
																								<children>
																									<text fixtext="Kieskring:"/>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<text fixtext="Provincie:"/>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="text() = &quot;stel gelijkluidende lijsten&quot;">
																				<children>
																					<text fixtext="Stel:"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="rg:Type/text() = &quot;op zichzelf staande lijst&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="//eml:ElectionCategory[ text() != &quot;EK&quot; ]">
																						<children>
																							<autocalc xpath="@districtNumber"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="@provinceName"/>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<template subtype="element" match="kr:ListData">
																				<children>
																					<template subtype="attribute" match="BelongsToSet">
																						<children>
																							<content subtype="regular">
																								<format basic-type="xsd" datatype="positiveInteger"/>
																							</content>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
											<tgridrow conditional-processing="$withList and not($isListengroup)">
												<children>
													<tgridcell>
														<properties align="left"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="//kr:ElectionSubcategory[ text() != &quot;TK&quot; and text() != &quot;PS2&quot; ]">
																		<children>
																			<text fixtext="Lijst:"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="../rg:Type = &quot;op zichzelf staande lijst&quot;">
																		<children>
																			<text fixtext="Op zichzelf staande lijst:"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="../rg:Type = &quot;stel gelijkluidende lijsten&quot;">
																		<children>
																			<text fixtext="Stel gelijkluidende lijsten:"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<children>
															<template subtype="element" match="eml:AffiliationIdentifier">
																<children>
																	<template subtype="attribute" match="Id">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="NMTOKEN"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
											<tgridrow conditional-processing="$withListnumber">
												<children>
													<tgridcell>
														<children>
															<text fixtext="Lijstnummer:"/>
														</children>
													</tgridcell>
													<tgridcell>
														<children>
															<template subtype="element" match="eml:AffiliationIdentifier">
																<children>
																	<template subtype="attribute" match="Id">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="NMTOKEN"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
											<tgridrow conditional-processing="$withVotes">
												<children>
													<tgridcell>
														<properties align="left"/>
														<children>
															<text fixtext="Totaal aantal stemmen:"/>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<children>
															<template subtype="element" match="rg:Votes">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid #000"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$toegewezen">
																		<children>
																			<text fixtext="Toegewezen"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<text fixtext="Totaal"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext=" aantal zetels:"/>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid #000"/>
														<children>
															<template subtype="element" match="rg:Seats">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
									</tgridbody-rows>
								</children>
								<wizard-data-repeat>
									<children/>
								</wizard-data-repeat>
								<wizard-data-rows>
									<children/>
								</wizard-data-rows>
								<wizard-data-columns>
									<children/>
								</wizard-data-columns>
							</tgrid>
						</children>
						<variables>
							<variable name="isListengroup" value="../rg:Type = &quot;lijstengroep&quot;" valuetype="xs:boolean"/>
						</variables>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="PartyHeader5Lines">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles font-size="smaller" vertical-align="top"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="160pt"/>
									</tgridcol>
									<tgridcol/>
								</children>
							</tgridbody-cols>
							<tgridbody-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<text fixtext="Aanduiding politieke groepering:"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="PartyNamePdfWrapped">
														<parameters/>
													</calltemplate>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<children>
													<text fixtext="Lijstengroepnummer:"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<children>
													<template subtype="element" match="eml:AffiliationIdentifier">
														<children>
															<template subtype="attribute" match="Id">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="NMTOKEN"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<text fixtext="Toegewezen aantal zetels lijstengroep:"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<template subtype="element" match="rg:ElectoralQuota">
														<children>
															<template subtype="element" match="rg:Fraction">
																<children>
																	<template subtype="attribute" match="denominator">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="O3CandidateVotes">
				<parameters/>
				<children>
					<template subtype="element" match="rg:AffiliationVotes">
						<children>
							<condition>
								<children>
									<conditionbranch xpath="$SV_OutputFormat = &apos;RTF&apos;">
										<children>
											<text fixtext="Aanduiding van de groepering: ">
												<styles font-size="small"/>
											</text>
											<template subtype="element" match="eml:AffiliationIdentifier">
												<children>
													<template subtype="element" match="eml:RegisteredName">
														<children>
															<content subtype="regular">
																<styles font-size="small"/>
																<format basic-type="xsd" datatype="token"/>
															</content>
														</children>
														<variables/>
													</template>
												</children>
												<variables/>
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
										<children>
											<paragraph paragraphtag="pre-wrap">
												<styles white-space="pre-wrap"/>
												<children>
													<text fixtext="Aanduiding van de groepering: ">
														<styles font-size="small"/>
													</text>
													<template subtype="element" match="eml:AffiliationIdentifier">
														<children>
															<template subtype="element" match="eml:RegisteredName">
																<children>
																	<content subtype="regular">
																		<styles font-size="small"/>
																		<format basic-type="xsd" datatype="token"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
												</children>
											</paragraph>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<text fixtext="Lijstnummer: ">
								<styles font-size="small"/>
							</text>
							<template subtype="element" match="eml:AffiliationIdentifier">
								<children>
									<template subtype="attribute" match="Id">
										<children>
											<content subtype="regular">
												<styles font-size="small"/>
												<format basic-type="xsd" datatype="NMTOKEN"/>
											</content>
										</children>
										<variables/>
									</template>
								</children>
								<variables/>
							</template>
							<newline/>
							<tgrid>
								<properties border="0" cellpadding="1" cellspacing="0"/>
								<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
								<children>
									<tgridbody-cols>
										<children>
											<tgridcol>
												<styles width="70pt"/>
											</tgridcol>
											<tgridcol/>
											<tgridcol>
												<styles width="60pt"/>
											</tgridcol>
										</children>
									</tgridbody-cols>
									<tgridheader-rows>
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<text fixtext="volgnummer op de lijst">
																<styles font-size="small"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<text fixtext="Kandidaten">
																<styles font-size="small"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid #000" text-align="right" vertical-align="top"/>
														<children>
															<text fixtext="Aantal stemmen">
																<styles font-size="small"/>
															</text>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
									</tgridheader-rows>
									<tgridbody-rows>
										<children>
											<template subtype="element" match="rg:CandidateVotes">
												<sort>
													<key match="eml:Candidate/eml:CandidateIdentifier/@Id" datatype="Number"/>
												</sort>
												<children>
													<tgridrow>
														<styles vertical-align="top"/>
														<children>
															<tgridcell>
																<properties align="left"/>
																<styles text-align="center"/>
																<children>
																	<template subtype="element" match="eml:Candidate">
																		<children>
																			<template subtype="element" match="eml:CandidateIdentifier">
																				<children>
																					<template subtype="attribute" match="Id">
																						<children>
																							<content subtype="regular">
																								<styles font-size="small" text-align="center"/>
																								<format basic-type="xsd" datatype="NMTOKEN"/>
																							</content>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<properties align="left"/>
																<children>
																	<template subtype="element" match="eml:Candidate">
																		<children>
																			<template subtype="element" match="eml:CandidateFullName">
																				<children>
																					<calltemplate subtype="named" match="LastNameH1">
																						<parameters/>
																					</calltemplate>
																					<text fixtext=", ">
																						<styles font-size="small"/>
																					</text>
																					<calltemplate subtype="named" match="FirstNameH1">
																						<parameters/>
																					</calltemplate>
																				</children>
																				<variables/>
																			</template>
																			<calltemplate subtype="named" match="Gender2">
																				<parameters/>
																			</calltemplate>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<properties align="left"/>
																<styles text-align="right"/>
																<children>
																	<template subtype="element" match="rg:ValidVotes">
																		<children>
																			<content subtype="regular">
																				<styles font-size="small"/>
																				<format basic-type="xsd" datatype="nonNegativeInteger"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
												</children>
												<variables/>
											</template>
											<tgridrow>
												<styles vertical-align="top"/>
												<children>
													<tgridcell>
														<properties align="left"/>
														<styles border-top="1px solid #000"/>
														<children>
															<text fixtext="Stemcijfer: ">
																<styles font-size="small"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<styles border-top="1px solid #000"/>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<styles border-top="1px solid #000" text-align="right"/>
														<children>
															<template subtype="element" match="rg:ValidVotes">
																<children>
																	<content subtype="regular">
																		<styles font-size="small"/>
																		<format basic-type="xsd" datatype="nonNegativeInteger"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
									</tgridbody-rows>
								</children>
								<wizard-data-repeat>
									<children/>
								</wizard-data-repeat>
								<wizard-data-rows>
									<children/>
								</wizard-data-rows>
								<wizard-data-columns>
									<children/>
								</wizard-data-columns>
							</tgrid>
							<newline break="page"/>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="O3-H-CandidateVotes">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0"/>
						<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="70pt"/>
									</tgridcol>
									<tgridcol/>
									<tgridcol>
										<styles width="60pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="volgnummer op de lijst"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="naam kandidaat"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" text-align="right" vertical-align="top"/>
												<children>
													<text fixtext="aantal  stemmen"/>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridheader-rows>
							<tgridbody-rows>
								<children>
									<template subtype="element" match="rg:CandidateAndResult">
										<sort>
											<key match="eml:Candidate/eml:CandidateIdentifier/@Id" datatype="Number"/>
										</sort>
										<children>
											<tgridrow>
												<styles vertical-align="top"/>
												<children>
													<tgridcell>
														<properties align="left"/>
														<styles text-align="center"/>
														<children>
															<template subtype="element" match="eml:Candidate">
																<children>
																	<template subtype="element" match="eml:CandidateIdentifier">
																		<children>
																			<template subtype="attribute" match="Id">
																				<children>
																					<content subtype="regular">
																						<styles text-align="center"/>
																						<format basic-type="xsd" datatype="NMTOKEN"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<children>
															<template subtype="element" match="eml:Candidate">
																<children>
																	<template subtype="element" match="eml:CandidateFullName">
																		<children>
																			<calltemplate subtype="named" match="LastNameH1">
																				<parameters/>
																			</calltemplate>
																			<text fixtext=", "/>
																			<calltemplate subtype="named" match="FirstNameH1">
																				<parameters/>
																			</calltemplate>
																		</children>
																		<variables/>
																	</template>
																	<calltemplate subtype="named" match="GenderGeneral">
																		<parameters>
																			<parameter name="Gender" value="eml:Gender"/>
																			<parameter name="PublishGender" value="../../kr:ListData/@PublishGender"/>
																			<parameter name="PublicationLanguage" value="../../kr:ListData/@PublicationLanguage"/>
																			<parameter name="isBold"/>
																			<parameter name="fontSize"/>
																		</parameters>
																	</calltemplate>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<styles text-align="right"/>
														<children>
															<template subtype="element" match="rg:TotalVotes">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<tgridrow>
										<styles vertical-align="top"/>
										<children>
											<tgridcell>
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="right"/>
												<children>
													<text fixtext="totaal"/>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="right"/>
												<children>
													<template subtype="element" match="rg:Votes">
														<children>
															<content subtype="regular">
																<format basic-type="xsd" datatype="integer"/>
															</content>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewVotesP22-2">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="90pt"/>
									</tgridcol>
									<tgridcol/>
									<tgridcol>
										<styles width="120pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<text fixtext="lijstnummer"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<text fixtext="aanduiding politieke groepering"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" text-align="right"/>
												<children>
													<text fixtext="aantal stemmen"/>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridheader-rows>
							<tgridbody-rows>
								<children>
									<template subtype="element" match="rg:OverviewOfCandidatesAndResults">
										<children>
											<template subtype="element" match="rg:ListGroupAndResults">
												<sort>
													<key match="eml:AffiliationIdentifier/@Id" datatype="Number"/>
												</sort>
												<children>
													<tgridrow>
														<children>
															<tgridcell>
																<styles padding-top="2pt" text-align="center" vertical-align="top"/>
																<children>
																	<template subtype="element" match="eml:AffiliationIdentifier">
																		<children>
																			<template subtype="attribute" match="Id">
																				<children>
																					<content subtype="regular">
																						<format basic-type="xsd" datatype="NMTOKEN"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<styles padding-top="2pt" vertical-align="top"/>
																<children>
																	<calltemplate subtype="named" match="PartyNamePdfWrapped">
																		<parameters/>
																	</calltemplate>
																</children>
															</tgridcell>
															<tgridcell>
																<styles padding-top="2pt" text-align="right" vertical-align="top"/>
																<children>
																	<template subtype="element" match="rg:Votes">
																		<children>
																			<content subtype="regular">
																				<styles text-align="right"/>
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
												</children>
												<variables/>
											</template>
										</children>
										<variables/>
									</template>
									<tgridrow>
										<children>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="right" vertical-align="top"/>
												<children>
													<text fixtext="                                                                             totaal"/>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="right" vertical-align="top"/>
												<children>
													<template subtype="element" match="rg:ElectoralQuota">
														<children>
															<template subtype="element" match="rg:Fraction">
																<children>
																	<template subtype="attribute" match="numerator">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ListOrCombinedList2">
				<parameters/>
				<children>
					<template subtype="element" match="rg:ListOrCombinedList">
						<children>
							<template subtype="attribute" match="listNumber">
								<children>
									<content subtype="regular">
										<format basic-type="xsd" datatype="integer"/>
									</content>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
					<condition>
						<children>
							<conditionbranch xpath="count(rg:ListOrCombinedList/rg:ListInCombination) &gt;0">
								<children>
									<text fixtext=" ("/>
									<template subtype="element" match="rg:ListOrCombinedList">
										<children>
											<template subtype="element" match="rg:ListInCombination">
												<children>
													<content subtype="regular">
														<format basic-type="xsd" datatype="integer"/>
													</content>
													<condition>
														<children>
															<conditionbranch xpath="position() != last()">
																<children>
																	<text fixtext=", "/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
												<variables/>
											</template>
										</children>
										<variables/>
									</template>
									<text fixtext=")"/>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewNiemeyerAssignment">
				<parameters/>
				<children>
					<template subtype="element" match="rg:NiemeyerAssignment">
						<children>
							<tgrid>
								<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
								<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
								<children>
									<tgridbody-cols>
										<children>
											<tgridcol>
												<styles width="130pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="70pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="90pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="80pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="80pt"/>
											</tgridcol>
										</children>
									</tgridbody-cols>
									<tgridheader-rows>
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<text fixtext="lijstnummer">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" text-align="right" vertical-align="top"/>
														<children>
															<text fixtext="aantal zetels">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" text-align="right" vertical-align="top"/>
														<children>
															<text fixtext="overschot">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid #000" vertical-align="top"/>
														<children>
															<text fixtext="aantal restzetels">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
									</tgridheader-rows>
									<tgridbody-rows>
										<children>
											<template subtype="element" match="rg:NiemeyerAssignmentLine">
												<children>
													<tgridrow>
														<children>
															<tgridcell>
																<styles vertical-align="top"/>
																<children>
																	<calltemplate subtype="named" match="ListOrCombinedList2">
																		<parameters/>
																	</calltemplate>
																</children>
															</tgridcell>
															<tgridcell>
																<styles padding-right="10pt" text-align="right" vertical-align="top"/>
																<children>
																	<template subtype="element" match="rg:PriorSeats">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<styles text-align="right" vertical-align="top"/>
																<children>
																	<template subtype="element" match="rg:Remainder">
																		<children>
																			<calltemplate subtype="named" match="ElectionFractionPart1">
																				<parameters/>
																			</calltemplate>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<styles vertical-align="top"/>
																<children>
																	<template subtype="element" match="rg:Remainder">
																		<children>
																			<calltemplate subtype="named" match="ElectionFractionPart2">
																				<parameters/>
																			</calltemplate>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<styles vertical-align="top"/>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="rg:NewSeats != 0">
																				<children>
																					<template subtype="element" match="rg:NewSeats">
																						<children>
																							<content subtype="regular">
																								<format basic-type="xsd" datatype="integer"/>
																							</content>
																						</children>
																						<variables/>
																					</template>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
												</children>
												<variables/>
											</template>
										</children>
									</tgridbody-rows>
								</children>
								<wizard-data-repeat>
									<children/>
								</wizard-data-repeat>
								<wizard-data-rows>
									<children/>
								</wizard-data-rows>
								<wizard-data-columns>
									<children/>
								</wizard-data-columns>
							</tgrid>
							<template subtype="element" match="rg:Allotting">
								<children>
									<calltemplate subtype="named" match="LineBreak">
										<parameters/>
									</calltemplate>
									<text fixtext="Overeenkomstig artikel "/>
									<condition>
										<children>
											<conditionbranch xpath="@article = &quot;P13&quot;">
												<children>
													<text fixtext="P 13"/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<text fixtext="P 8"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<text fixtext=", eerste lid van de Kieswet heeft een loting plaatsgevonden tussen "/>
									<template subtype="element" match="rg:Winner">
										<children>
											<calltemplate subtype="named" match="ListOrCombinedList">
												<parameters>
													<parameter name="smaller"/>
												</parameters>
											</calltemplate>
										</children>
										<variables/>
									</template>
									<template subtype="element" match="rg:Looser">
										<children>
											<text fixtext=" en "/>
											<calltemplate subtype="named" match="ListOrCombinedList">
												<parameters>
													<parameter name="smaller"/>
												</parameters>
											</calltemplate>
										</children>
										<variables/>
									</template>
									<text fixtext=". Als gevolg hiervan is een restzetel toegewezen aan "/>
									<template subtype="element" match="rg:Winner">
										<children>
											<calltemplate subtype="named" match="ListOrCombinedList">
												<parameters>
													<parameter name="smaller"/>
												</parameters>
											</calltemplate>
										</children>
										<variables/>
									</template>
									<text fixtext="."/>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewAssignmentWithinListGroups">
				<parameters/>
				<children>
					<template subtype="element" match="rg:AssignmentWithinListGroups">
						<children>
							<newline/>
							<calltemplate subtype="named" match="PartyHeader5Lines">
								<parameters/>
							</calltemplate>
							<newline/>
							<newline/>
							<calltemplate subtype="named" match="Kiesdeler">
								<parameters>
									<parameter name="inCombinedList" value="false()"/>
									<parameter name="inListGroup" value="true()"/>
								</parameters>
							</calltemplate>
							<newline/>
							<newline/>
							<tgrid>
								<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
								<styles font-size="smaller"/>
								<children>
									<tgridbody-cols>
										<children>
											<tgridcol>
												<styles width="90pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="60pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="70pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="55pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="55pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="60pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="60pt"/>
											</tgridcol>
										</children>
									</tgridbody-cols>
									<tgridheader-rows>
										<children>
											<tgridrow>
												<styles border-bottom="1px solid #000" text-align="left"/>
												<children>
													<tgridcell>
														<styles border-bottom="1px solid #000"/>
														<children>
															<text fixtext="lijst of stel gelijkluidende lijsten ">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" text-align="right"/>
														<children>
															<text fixtext="aantal stemmen">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" padding-left="10pt" text-align="center"/>
														<children>
															<text fixtext="aantal volle zetels per lijst">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" text-align="center"/>
														<children>
															<text fixtext="overschot">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell joinleft="1"/>
													<tgridcell>
														<styles border-bottom="1px solid #000" text-align="center"/>
														<children>
															<text fixtext="toegewezen aantal restzetels">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" text-align="center"/>
														<children>
															<text fixtext="totaal aantal zetels">
																<properties class="columnheader"/>
															</text>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
									</tgridheader-rows>
									<tgridbody-rows>
										<children>
											<template subtype="element" match="rg:AssignmentWithinListGroupsLine">
												<sort>
													<key match="@sortNumber" datatype="Number"/>
												</sort>
												<children>
													<tgridrow>
														<children>
															<tgridcell>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@districtNumber != &apos;&apos;">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="//kr:ElectionSubcategory[ text() != &quot;EK&quot; ]">
																								<children>
																									<text fixtext="Kieskring "/>
																									<template subtype="attribute" match="districtNumber">
																										<children>
																											<content subtype="regular">
																												<format basic-type="xsd" datatype="NMTOKEN"/>
																											</content>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<template subtype="attribute" match="provinceName">
																										<children>
																											<content subtype="regular">
																												<format basic-type="xsd" datatype="NMTOKEN"/>
																											</content>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="@setNumber != &apos;&apos;">
																				<children>
																					<text fixtext="Totaal stel "/>
																					<template subtype="attribute" match="setNumber">
																						<children>
																							<content subtype="regular">
																								<format basic-type="xsd" datatype="NMTOKEN"/>
																							</content>
																						</children>
																						<variables/>
																					</template>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<text fixtext=" "/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</tgridcell>
															<tgridcell>
																<styles text-align="right"/>
																<children>
																	<template subtype="element" match="rg:Votes">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<styles text-align="center"/>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="rg:PriorSeats != &apos;&apos; and rg:PriorSeats != &apos;0&apos;">
																				<children>
																					<template subtype="element" match="rg:PriorSeats">
																						<children>
																							<content subtype="regular">
																								<format basic-type="xsd" datatype="integer"/>
																							</content>
																						</children>
																						<variables/>
																					</template>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</tgridcell>
															<tgridcell>
																<styles text-align="right"/>
																<children>
																	<template subtype="element" match="rg:Remainder">
																		<children>
																			<calltemplate subtype="named" match="ElectionFractionPart1">
																				<parameters/>
																			</calltemplate>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<children>
																	<template subtype="element" match="rg:Remainder">
																		<children>
																			<text fixtext=" "/>
																			<calltemplate subtype="named" match="ElectionFractionPart2">
																				<parameters/>
																			</calltemplate>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<styles text-align="center"/>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="rg:NewSeats != &apos;&apos; and rg:NewSeats != &apos;0&apos;">
																				<children>
																					<template subtype="element" match="rg:NewSeats">
																						<children>
																							<content subtype="regular">
																								<format basic-type="xsd" datatype="integer"/>
																							</content>
																						</children>
																						<variables/>
																					</template>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</tgridcell>
															<tgridcell>
																<styles text-align="center"/>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="(rg:PriorSeats != &apos;&apos; and rg:PriorSeats != &apos;0&apos;) or (rg:NewSeats != &apos;&apos; and rg:NewSeats != &apos;0&apos;)">
																				<children>
																					<autocalc xpath="rg:PriorSeats +  rg:NewSeats"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
												</children>
												<variables/>
											</template>
										</children>
									</tgridbody-rows>
								</children>
								<wizard-data-repeat>
									<children/>
								</wizard-data-repeat>
								<wizard-data-rows>
									<children/>
								</wizard-data-rows>
								<wizard-data-columns>
									<children/>
								</wizard-data-columns>
							</tgrid>
							<calltemplate subtype="named" match="LineBreakTable">
								<parameters/>
							</calltemplate>
							<template subtype="element" match="rg:Allotting">
								<children>
									<text fixtext="Overeenkomstig artikel "/>
									<condition>
										<children>
											<conditionbranch xpath="//kr:ElectionSubcategory[ text() != &quot;EK&quot; ]">
												<children>
													<text fixtext="P 12"/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<text fixtext="U 12"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<text fixtext=", vijfde lid van de Kieswet heeft een loting plaatsgevonden tussen "/>
									<template subtype="element" match="rg:Winner">
										<children>
											<calltemplate subtype="named" match="ListOrCombinedListUni">
												<parameters/>
											</calltemplate>
										</children>
										<variables/>
									</template>
									<template subtype="element" match="rg:Looser">
										<children>
											<text fixtext=" en "/>
											<calltemplate subtype="named" match="ListOrCombinedListUni">
												<parameters/>
											</calltemplate>
										</children>
										<variables/>
									</template>
									<text fixtext=". Als gevolg hiervan is een restzetel toegewezen aan "/>
									<template subtype="element" match="rg:Winner">
										<children>
											<calltemplate subtype="named" match="ListOrCombinedListUni">
												<parameters/>
											</calltemplate>
										</children>
										<variables/>
									</template>
									<text fixtext="."/>
									<calltemplate subtype="named" match="LineBreak">
										<parameters/>
									</calltemplate>
								</children>
								<variables/>
							</template>
							<template subtype="element" match="rg:ExhaustedList">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="@lostSeats = 1">
												<children>
													<text fixtext="Overeenkomstig artikel "/>
													<condition>
														<children>
															<conditionbranch xpath="//kr:ElectionSubcategory[ text() != &quot;EK&quot; ]">
																<children>
																	<text fixtext="P 13"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<text fixtext="U 13"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<text fixtext=", eerste lid van de Kieswet, vindt er een overgang plaats van een zetel van "/>
													<calltemplate subtype="named" match="ListOrCombinedListUni">
														<parameters/>
													</calltemplate>
													<text fixtext=" naar een ander lijst."/>
												</children>
											</conditionbranch>
											<conditionbranch xpath="@lostSeats &gt; 1">
												<children>
													<text fixtext="Overeenkomstig artikel "/>
													<condition>
														<children>
															<conditionbranch xpath="//kr:ElectionSubcategory[ text() != &quot;EK&quot; ]">
																<children>
																	<text fixtext="P 13"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<text fixtext="U 13"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<text fixtext=", eerste lid van de Kieswet, vindt er een overgang plaats van "/>
													<template subtype="attribute" match="lostSeats">
														<children>
															<content subtype="regular">
																<format basic-type="xsd" datatype="integer"/>
															</content>
														</children>
														<variables/>
													</template>
													<text fixtext=" zetels van "/>
													<calltemplate subtype="named" match="ListOrCombinedListUni">
														<parameters/>
													</calltemplate>
													<text fixtext=" naar andere lijsten."/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<calltemplate subtype="named" match="LineBreak">
										<parameters/>
									</calltemplate>
								</children>
								<variables/>
							</template>
							<template subtype="element" match="rg:DHondtAssignment">
								<children>
									<text fixtext="Hierna zijn overeenkomstig artikel "/>
									<condition>
										<children>
											<conditionbranch xpath="//kr:ElectionSubcategory[ text() != &quot;EK&quot; ]">
												<children>
													<text fixtext="P 13"/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<text fixtext="U 13"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<text fixtext=", tweede lid van de Kieswet volgende restzetels toegewezen volgens het stelsel van de grootste gemiddelden:"/>
									<calltemplate subtype="named" match="LineBreakTable">
										<parameters/>
									</calltemplate>
								</children>
								<variables/>
							</template>
							<calltemplate subtype="named" match="OverviewDHondtAssignment">
								<parameters/>
							</calltemplate>
							<template subtype="element" match="rg:DHondtAssignment">
								<children>
									<calltemplate subtype="named" match="LineBreakTable">
										<parameters/>
									</calltemplate>
								</children>
								<variables/>
							</template>
							<newline break="page"/>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ListOrCombinedListUni">
				<parameters/>
				<children>
					<template subtype="element" match="rg:ListOrCombinedList">
						<children>
							<condition>
								<children>
									<conditionbranch xpath="@combinationId">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="count( rg:ListInCombination ) &gt; 0">
														<children>
															<text fixtext=" ("/>
															<template subtype="element" match="rg:ListInCombination">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<condition>
																		<children>
																			<conditionbranch xpath="position() != last()">
																				<children>
																					<text fixtext=", "/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
															<text fixtext=")"/>
														</children>
													</conditionbranch>
												</children>
											</condition>
										</children>
									</conditionbranch>
									<conditionbranch xpath="@districtNumber">
										<children>
											<text fixtext="kieskring "/>
											<template subtype="attribute" match="districtNumber">
												<children>
													<content subtype="regular">
														<format basic-type="xsd" datatype="integer"/>
													</content>
												</children>
												<variables/>
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="@setNumber">
										<children>
											<text fixtext="stel "/>
											<template subtype="attribute" match="setNumber">
												<children>
													<content subtype="regular">
														<format basic-type="xsd" datatype="integer"/>
													</content>
												</children>
												<variables/>
											</template>
										</children>
									</conditionbranch>
									<conditionbranch xpath="@listNumber">
										<children>
											<text fixtext="lijst "/>
											<template subtype="attribute" match="listNumber">
												<children>
													<content subtype="regular">
														<format basic-type="xsd" datatype="integer"/>
													</content>
												</children>
												<variables/>
											</template>
										</children>
									</conditionbranch>
								</children>
							</condition>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewVotes2">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles border-bottom="1px solid #000" border-top="1px solid #000" font-size="xx-small"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="60pt"/>
									</tgridcol>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
								</children>
							</tgridbody-cols>
							<tgridbody-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top" width="60pt"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<styles vertical-align="top"/>
											</tgridcell>
											<tgridcell>
												<styles text-align="center" vertical-align="top"/>
												<children>
													<text fixtext="Kieskring"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top"/>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<styles height="31pt"/>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Lijstnr."/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="1"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="2"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Totaal"/>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<template subtype="element" match="rg:OverviewOfListsAndDistrictsAndVotes">
										<children>
											<tgridrow>
												<styles height="1pt"/>
												<children>
													<tgridcell>
														<properties align="center"/>
														<styles vertical-align="top"/>
														<children>
															<template subtype="attribute" match="listNumber">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="NMTOKEN"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<template subtype="element" match="rg:VotesInElectoralDistrict">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@id = &quot;alle&quot;">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Totaal"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="middle"/>
												<children>
													<template subtype="element" match="rg:OverviewOfDistrictsVotes">
														<children>
															<template subtype="element" match="rg:VotesInElectoralDistrict">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@id = &quot;alle&quot;">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" text-align="left" vertical-align="top"/>
												<children>
													<text fixtext="Blanco stemmen"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<template subtype="element" match="rg:OverviewOfBlancVotes">
														<children>
															<template subtype="element" match="rg:VotesInElectoralDistrict">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@id = &quot;alle&quot;">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Ongeldige stemmen"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<template subtype="element" match="rg:OverviewOfInvalidVotes">
														<children>
															<template subtype="element" match="rg:VotesInElectoralDistrict">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@id = &quot;alle&quot;">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewVotes3">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles border-bottom="1px solid #000" border-top="1px solid #000" font-size="xx-small"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="60pt"/>
									</tgridcol>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
								</children>
							</tgridbody-cols>
							<tgridbody-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top" width="60pt"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<styles vertical-align="top"/>
											</tgridcell>
											<tgridcell>
												<styles text-align="center" vertical-align="top"/>
												<children>
													<text fixtext="Kieskring"/>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles vertical-align="top"/>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top"/>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<styles height="31pt"/>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Lijstnr."/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="1"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="2"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="3"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Totaal"/>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<template subtype="element" match="rg:OverviewOfListsAndDistrictsAndVotes">
										<children>
											<tgridrow>
												<styles height="1pt"/>
												<children>
													<tgridcell>
														<properties align="center"/>
														<styles vertical-align="top"/>
														<children>
															<template subtype="attribute" match="listNumber">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="NMTOKEN"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="3"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<template subtype="element" match="rg:VotesInElectoralDistrict">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@id = &quot;alle&quot;">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Totaal"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="3"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="middle"/>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="middle"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="middle"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="middle"/>
												<children>
													<template subtype="element" match="rg:OverviewOfDistrictsVotes">
														<children>
															<template subtype="element" match="rg:VotesInElectoralDistrict">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@id = &quot;alle&quot;">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" text-align="left" vertical-align="top"/>
												<children>
													<text fixtext="Blanco stemmen"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="3"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<template subtype="element" match="rg:OverviewOfBlancVotes">
														<children>
															<template subtype="element" match="rg:VotesInElectoralDistrict">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@id = &quot;alle&quot;">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Ongeldige stemmen"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="3"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<template subtype="element" match="rg:OverviewOfInvalidVotes">
														<children>
															<template subtype="element" match="rg:VotesInElectoralDistrict">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@id = &quot;alle&quot;">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewVotes4">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles border-bottom="1px solid #000" border-top="1px solid #000" font-size="xx-small"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="60pt"/>
									</tgridcol>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
								</children>
							</tgridbody-cols>
							<tgridbody-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top" width="60pt"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top"/>
											</tgridcell>
											<tgridcell>
												<styles text-align="center" vertical-align="top"/>
												<children>
													<text fixtext="Kieskring"/>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles vertical-align="top"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles vertical-align="top"/>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top"/>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<styles height="31pt"/>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Lijstnr."/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="1"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="2"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="3"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="4"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Totaal aantal"/>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<template subtype="element" match="rg:OverviewOfListsAndDistrictsAndVotes">
										<children>
											<tgridrow>
												<styles height="1pt"/>
												<children>
													<tgridcell>
														<properties align="center"/>
														<styles vertical-align="top"/>
														<children>
															<template subtype="attribute" match="listNumber">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="NMTOKEN"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="3"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="VotesInElectoralDistrict">
																<parameters>
																	<parameter name="districtId" value="4"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<template subtype="element" match="rg:VotesInElectoralDistrict">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@id = &quot;alle&quot;">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Totaal"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="3"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="4"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top"/>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top"/>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="middle"/>
												<children>
													<template subtype="element" match="rg:OverviewOfDistrictsVotes">
														<children>
															<template subtype="element" match="rg:VotesInElectoralDistrict">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@id = &quot;alle&quot;">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Blanco stemmen"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="3"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="BlancVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="4"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<template subtype="element" match="rg:OverviewOfBlancVotes">
														<children>
															<template subtype="element" match="rg:VotesInElectoralDistrict">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@id = &quot;alle&quot;">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="Ongeldige stemmen"/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="3"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<calltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
														<parameters>
															<parameter name="districtId" value="4"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="middle"/>
												<children>
													<template subtype="element" match="rg:OverviewOfInvalidVotes">
														<children>
															<template subtype="element" match="rg:VotesInElectoralDistrict">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@id = &quot;alle&quot;">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="PostTitle">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
								<children>
									<paragraph paragraphtag="p"/>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="Kiesdeler">
				<parameters>
					<parameter name="inListGroup" type="xs:boolean" occurrence="none-or-one" default-value="false()"/>
				</parameters>
				<children>
					<template subtype="element" match="rg:ElectoralQuota">
						<children>
							<tgrid>
								<children>
									<tgridbody-cols>
										<children>
											<tgridcol>
												<styles width="180pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="15pt"/>
											</tgridcol>
											<tgridcol/>
											<tgridcol>
												<styles width="15pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="100pt"/>
											</tgridcol>
										</children>
									</tgridbody-cols>
									<tgridheader-rows>
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<styles text-align="left"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$inListGroup">
																		<children>
																			<text fixtext="aantal stemmen">
																				<properties class="columnheader"/>
																				<styles font-weight="normal"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<text fixtext="totaal aantal geldige stemmen op kandidaten">
																				<properties class="columnheader"/>
																				<styles font-weight="normal"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tgridcell>
													<tgridcell/>
													<tgridcell>
														<styles text-align="left"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$inListGroup and //kr:ElectionSubcategory = &apos;EK&apos;">
																		<children>
																			<text fixtext="toegewezen ">
																				<properties class="columnheader"/>
																				<styles font-weight="normal"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext="aantal zetels">
																<properties class="columnheader"/>
																<styles font-weight="normal"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell/>
													<tgridcell>
														<styles text-align="left"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$inListGroup">
																		<children>
																			<text fixtext="groepskiesdeler ">
																				<properties class="columnheader"/>
																				<styles font-weight="normal"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<text fixtext="kiesdeler">
																				<properties class="columnheader"/>
																				<styles font-weight="normal"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
									</tgridheader-rows>
									<tgridbody-rows>
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<styles text-align="left"/>
														<children>
															<template subtype="element" match="rg:Fraction">
																<children>
																	<template subtype="attribute" match="numerator">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<styles text-align="center"/>
														<children>
															<text fixtext=":"/>
														</children>
													</tgridcell>
													<tgridcell>
														<styles text-align="left"/>
														<children>
															<template subtype="element" match="rg:Fraction">
																<children>
																	<template subtype="attribute" match="denominator">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<styles text-align="center"/>
														<children>
															<text fixtext="="/>
														</children>
													</tgridcell>
													<tgridcell>
														<styles text-align="left"/>
														<children>
															<calltemplate subtype="named" match="ElectionFraction">
																<parameters/>
															</calltemplate>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
									</tgridbody-rows>
								</children>
								<wizard-data-repeat>
									<children/>
								</wizard-data-repeat>
								<wizard-data-rows>
									<children/>
								</wizard-data-rows>
								<wizard-data-columns>
									<children/>
								</wizard-data-columns>
							</tgrid>
							<text fixtext=" "/>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewDHondtTableOneSeat">
				<parameters>
					<parameter name="maxNumberOfAssignments" type="xs:integer"/>
				</parameters>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="30pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="70pt"/>
									</tgridcol>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol>
										<styles width="62pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="lijstnummer">
														<properties class="columnheader"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-bottom="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center"/>
												<children>
													<text fixtext="reeds toegewezen">
														<properties class="columnheader"/>
													</text>
													<newline/>
													<text fixtext="aantal zetels">
														<properties class="columnheader"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid #000" text-align="center"/>
												<children>
													<text fixtext="gemiddeld aantal stemmen">
														<properties class="columnheader"/>
													</text>
													<newline/>
													<text fixtext="bij toewijzing restzetel">
														<properties class="columnheader"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-bottom="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="aantal restzetels">
														<properties class="columnheader"/>
													</text>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridheader-rows>
							<tgridbody-rows>
								<children>
									<template subtype="element" match="rg:DHondtAssignmentLine">
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="right"/>
														<children>
															<template subtype="element" match="rg:ListOrCombinedList">
																<children>
																	<template subtype="attribute" match="combinationId">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="token"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																	<template subtype="attribute" match="listNumber">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<styles padding-left="5pt"/>
														<children>
															<calltemplate subtype="named" match="ListOrCombinedListPart2">
																<parameters/>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles text-align="center"/>
														<children>
															<template subtype="element" match="rg:PriorSeats">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles margin-left="0pt"/>
														<children>
															<template subtype="element" match="rg:DHondtFraction">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@step = 1">
																				<children>
																					<calltemplate subtype="named" match="ElectionFractionPart1">
																						<parameters>
																							<parameter name="smaller" value="1"/>
																							<parameter name="bold" value="false()"/>
																						</parameters>
																					</calltemplate>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<styles margin-left="5pt"/>
														<children>
															<template subtype="element" match="rg:DHondtFraction">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="@step = 1">
																				<children>
																					<calltemplate subtype="named" match="ElectionFractionPart2">
																						<parameters>
																							<parameter name="smaller" value="1"/>
																							<parameter name="bold" value="false()"/>
																						</parameters>
																					</calltemplate>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="center"/>
														<styles margin-left="0pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="rg:NewSeats != 0">
																		<children>
																			<template subtype="element" match="rg:NewSeats">
																				<children>
																					<content subtype="regular">
																						<format basic-type="xsd" datatype="integer"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewDHondtTable2">
				<parameters>
					<parameter name="maxNumberOfAssignments" type="xs:integer"/>
				</parameters>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="30pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="70pt"/>
									</tgridcol>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol>
										<styles width="62pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="//kr:ElectionSubcategory[ text() = &quot;EK&quot; ]">
																<children>
																	<text fixtext="lijstnummer">
																		<properties class="columnheader"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch xpath="@article = &quot;P13&quot;">
																<children>
																	<text fixtext="Lijst">
																		<properties class="columnheader"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<text fixtext="lijstnummer">
																		<properties class="columnheader"/>
																	</text>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<styles text-align="center"/>
												<children>
													<text fixtext="restzetel">
														<properties class="columnheader"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="aantal restzetels">
														<properties class="columnheader"/>
													</text>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell joinabove="1">
												<styles border-bottom="1px solid #000"/>
											</tgridcell>
											<tgridcell joinleft="1" joinabove="1">
												<styles border-bottom="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<properties align="center"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="DHondtHeader">
														<parameters>
															<parameter name="step" value="1"/>
															<parameter name="maxNumberOfAssignments" value="$maxNumberOfAssignments"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="center"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="DHondtHeader">
														<parameters>
															<parameter name="step" value="2"/>
															<parameter name="maxNumberOfAssignments" value="$maxNumberOfAssignments"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinabove="1">
												<styles border-bottom="1px solid #000"/>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridheader-rows>
							<tgridbody-rows>
								<children>
									<template subtype="element" match="rg:DHondtAssignmentLine">
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="right"/>
														<children>
															<template subtype="element" match="rg:ListOrCombinedList">
																<children>
																	<template subtype="attribute" match="combinationId">
																		<children>
																			<content subtype="regular">
																				<styles font-size="smaller"/>
																				<format basic-type="xsd" datatype="token"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																	<template subtype="attribute" match="listNumber">
																		<children>
																			<content subtype="regular">
																				<styles font-size="smaller"/>
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<styles padding-left="5pt"/>
														<children>
															<calltemplate subtype="named" match="ListOrCombinedListPart2">
																<parameters/>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart1">
																<parameters>
																	<parameter name="step" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles margin-left="5pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart2">
																<parameters>
																	<parameter name="step" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles margin-left="0pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart1">
																<parameters>
																	<parameter name="step" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles margin-left="5pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart2">
																<parameters>
																	<parameter name="step" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="center"/>
														<styles margin-left="0pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="rg:NewSeats != 0">
																		<children>
																			<template subtype="element" match="rg:NewSeats">
																				<children>
																					<content subtype="regular">
																						<styles font-size="smaller"/>
																						<format basic-type="xsd" datatype="integer"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<tgridrow>
										<children>
											<tgridcell>
												<styles border-top="1px solid #000"/>
												<children>
													<text fixtext="restzetel toegekend aan">
														<properties class="columnheader"/>
														<styles vertical-align="bottom"/>
													</text>
													<newline/>
													<text fixtext="lijst">
														<properties class="columnheader"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center"/>
												<children>
													<calltemplate subtype="named" match="DHondtWinners">
														<parameters>
															<parameter name="step" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center"/>
												<children>
													<calltemplate subtype="named" match="DHondtWinners">
														<parameters>
															<parameter name="step" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000"/>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewDHondtTable3">
				<parameters>
					<parameter name="maxNumberOfAssignments" type="xs:integer"/>
				</parameters>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="30pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="70pt"/>
									</tgridcol>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol>
										<styles width="62pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="//kr:ElectionSubcategory[ text() = &quot;EK&quot; ]">
																<children>
																	<text fixtext="lijstnummer">
																		<properties class="columnheader"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch xpath="@article = &quot;P13&quot;">
																<children>
																	<text fixtext="Lijst">
																		<properties class="columnheader"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<text fixtext="lijstnummer">
																		<properties class="columnheader"/>
																	</text>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<styles text-align="center"/>
												<children>
													<text fixtext="restzetel">
														<properties class="columnheader"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="aantal restzetels">
														<properties class="columnheader"/>
													</text>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell joinabove="1">
												<styles border-bottom="1px solid #000"/>
											</tgridcell>
											<tgridcell joinleft="1" joinabove="1">
												<styles border-bottom="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<properties align="center"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="DHondtHeader">
														<parameters>
															<parameter name="step" value="1"/>
															<parameter name="maxNumberOfAssignments" value="$maxNumberOfAssignments"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="center"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="DHondtHeader">
														<parameters>
															<parameter name="step" value="2"/>
															<parameter name="maxNumberOfAssignments" value="$maxNumberOfAssignments"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="center"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="DHondtHeader">
														<parameters>
															<parameter name="step" value="3"/>
															<parameter name="maxNumberOfAssignments" value="$maxNumberOfAssignments"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinabove="1">
												<styles border-bottom="1px solid #000"/>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridheader-rows>
							<tgridbody-rows>
								<children>
									<template subtype="element" match="rg:DHondtAssignmentLine">
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="right"/>
														<children>
															<template subtype="element" match="rg:ListOrCombinedList">
																<children>
																	<template subtype="attribute" match="combinationId">
																		<children>
																			<content subtype="regular">
																				<styles font-size="smaller"/>
																				<format basic-type="xsd" datatype="token"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																	<template subtype="attribute" match="listNumber">
																		<children>
																			<content subtype="regular">
																				<styles font-size="smaller"/>
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<styles padding-left="5pt"/>
														<children>
															<calltemplate subtype="named" match="ListOrCombinedListPart2">
																<parameters/>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart1">
																<parameters>
																	<parameter name="step" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles margin-left="5pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart2">
																<parameters>
																	<parameter name="step" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles margin-left="0pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart1">
																<parameters>
																	<parameter name="step" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles margin-left="5pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart2">
																<parameters>
																	<parameter name="step" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles margin-left="0pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart1">
																<parameters>
																	<parameter name="step" value="3"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles margin-left="5pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart2">
																<parameters>
																	<parameter name="step" value="3"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="center"/>
														<styles margin-left="0pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="rg:NewSeats != 0">
																		<children>
																			<template subtype="element" match="rg:NewSeats">
																				<children>
																					<content subtype="regular">
																						<styles font-size="smaller"/>
																						<format basic-type="xsd" datatype="integer"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<tgridrow>
										<children>
											<tgridcell>
												<styles border-top="1px solid #000"/>
												<children>
													<text fixtext="restzetel toegekend aan">
														<properties class="columnheader"/>
													</text>
													<newline/>
													<text fixtext="lijst">
														<properties class="columnheader"/>
														<styles vertical-align="bottom"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center"/>
												<children>
													<calltemplate subtype="named" match="DHondtWinners">
														<parameters>
															<parameter name="step" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center"/>
												<children>
													<calltemplate subtype="named" match="DHondtWinners">
														<parameters>
															<parameter name="step" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center"/>
												<children>
													<calltemplate subtype="named" match="DHondtWinners">
														<parameters>
															<parameter name="step" value="3"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000"/>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewDHondtTable4">
				<parameters>
					<parameter name="maxNumberOfAssignments" type="xs:integer"/>
				</parameters>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="30pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="70pt"/>
									</tgridcol>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol>
										<styles width="62pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="//kr:ElectionSubcategory[ text() = &quot;EK&quot; ]">
																<children>
																	<text fixtext="lijstnummer">
																		<properties class="columnheader"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch xpath="@article = &quot;P13&quot;">
																<children>
																	<text fixtext="Lijst">
																		<properties class="columnheader"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<text fixtext="lijstnummer">
																		<properties class="columnheader"/>
																	</text>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<styles text-align="center"/>
												<children>
													<text fixtext="restzetel">
														<properties class="columnheader"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="aantal restzetels">
														<properties class="columnheader"/>
													</text>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell joinabove="1">
												<styles border-bottom="1px solid #000"/>
											</tgridcell>
											<tgridcell joinleft="1" joinabove="1">
												<styles border-bottom="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<properties align="center"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="DHondtHeader">
														<parameters>
															<parameter name="step" value="1"/>
															<parameter name="maxNumberOfAssignments" value="$maxNumberOfAssignments"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="center"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="DHondtHeader">
														<parameters>
															<parameter name="step" value="2"/>
															<parameter name="maxNumberOfAssignments" value="$maxNumberOfAssignments"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="center"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="DHondtHeader">
														<parameters>
															<parameter name="step" value="3"/>
															<parameter name="maxNumberOfAssignments" value="$maxNumberOfAssignments"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="center"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="DHondtHeader">
														<parameters>
															<parameter name="step" value="4"/>
															<parameter name="maxNumberOfAssignments" value="$maxNumberOfAssignments"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinabove="1">
												<styles border-bottom="1px solid #000"/>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridheader-rows>
							<tgridbody-rows>
								<children>
									<template subtype="element" match="rg:DHondtAssignmentLine">
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="right"/>
														<children>
															<template subtype="element" match="rg:ListOrCombinedList">
																<children>
																	<template subtype="attribute" match="combinationId">
																		<children>
																			<content subtype="regular">
																				<styles font-size="smaller"/>
																				<format basic-type="xsd" datatype="token"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																	<template subtype="attribute" match="listNumber">
																		<children>
																			<content subtype="regular">
																				<styles font-size="smaller"/>
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<styles padding-left="5pt"/>
														<children>
															<calltemplate subtype="named" match="ListOrCombinedListPart2">
																<parameters/>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles margin-left="0pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart1">
																<parameters>
																	<parameter name="step" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles margin-left="3pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart2">
																<parameters>
																	<parameter name="step" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles margin-left="0pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart1">
																<parameters>
																	<parameter name="step" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles margin-left="3pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart2">
																<parameters>
																	<parameter name="step" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles margin-left="0pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart1">
																<parameters>
																	<parameter name="step" value="3"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles margin-left="3pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart2">
																<parameters>
																	<parameter name="step" value="3"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles margin-left="0pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart1">
																<parameters>
																	<parameter name="step" value="4"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles margin-left="3pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart2">
																<parameters>
																	<parameter name="step" value="4"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="center"/>
														<styles margin-left="0pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="rg:NewSeats != 0">
																		<children>
																			<template subtype="element" match="rg:NewSeats">
																				<children>
																					<content subtype="regular">
																						<styles font-size="smaller"/>
																						<format basic-type="xsd" datatype="integer"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<tgridrow>
										<children>
											<tgridcell>
												<styles border-top="1px solid #000"/>
												<children>
													<text fixtext="restzetel toegekend aan">
														<properties class="columnheader"/>
													</text>
													<newline/>
													<text fixtext="lijst">
														<properties class="columnheader"/>
														<styles vertical-align="bottom"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center"/>
												<children>
													<calltemplate subtype="named" match="DHondtWinners">
														<parameters>
															<parameter name="step" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center"/>
												<children>
													<calltemplate subtype="named" match="DHondtWinners">
														<parameters>
															<parameter name="step" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000" text-align="center"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center"/>
												<children>
													<calltemplate subtype="named" match="DHondtWinners">
														<parameters>
															<parameter name="step" value="3"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center"/>
												<children>
													<calltemplate subtype="named" match="DHondtWinners">
														<parameters>
															<parameter name="step" value="4"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000"/>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewDHondtTable6">
				<parameters>
					<parameter name="maxNumberOfAssignments" type="xs:integer"/>
					<parameter name="offset" type="xs:integer"/>
				</parameters>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="30pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="70pt"/>
									</tgridcol>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol conditional-processing="$maxNumberOfAssignments &lt;= 6 + $offset">
										<styles width="62pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="//kr:ElectionSubcategory[ text() = &quot;EK&quot; ]">
																<children>
																	<text fixtext="lijstnummer">
																		<properties class="columnheader"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch xpath="@article = &quot;P13&quot;">
																<children>
																	<text fixtext="Lijst">
																		<properties class="columnheader"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<text fixtext="lijstnummer">
																		<properties class="columnheader"/>
																	</text>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<styles text-align="center"/>
												<children>
													<text fixtext="restzetel">
														<properties class="columnheader"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$maxNumberOfAssignments &lt;= 6 + $offset">
																<children>
																	<text fixtext="aantal restzetels">
																		<properties class="columnheader"/>
																	</text>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell joinabove="1">
												<styles border-bottom="1px solid #000"/>
											</tgridcell>
											<tgridcell joinleft="1" joinabove="1">
												<styles border-bottom="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<properties align="center"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="DHondtHeader">
														<parameters>
															<parameter name="step" value="1 + $offset"/>
															<parameter name="maxNumberOfAssignments" value="$maxNumberOfAssignments"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="center"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="DHondtHeader">
														<parameters>
															<parameter name="step" value="2 + $offset"/>
															<parameter name="maxNumberOfAssignments" value="$maxNumberOfAssignments"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="center"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="DHondtHeader">
														<parameters>
															<parameter name="step" value="3 + $offset"/>
															<parameter name="maxNumberOfAssignments" value="$maxNumberOfAssignments"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="center"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="DHondtHeader">
														<parameters>
															<parameter name="step" value="4 + $offset"/>
															<parameter name="maxNumberOfAssignments" value="$maxNumberOfAssignments"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="center"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="DHondtHeader">
														<parameters>
															<parameter name="step" value="5 + $offset"/>
															<parameter name="maxNumberOfAssignments" value="$maxNumberOfAssignments"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="center"/>
												<styles border-bottom="1px solid #000"/>
												<children>
													<calltemplate subtype="named" match="DHondtHeader">
														<parameters>
															<parameter name="step" value="6 + $offset"/>
															<parameter name="maxNumberOfAssignments" value="$maxNumberOfAssignments"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinabove="1">
												<styles border-bottom="1px solid #000"/>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridheader-rows>
							<tgridbody-rows>
								<children>
									<template subtype="element" match="rg:DHondtAssignmentLine">
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="right"/>
														<children>
															<template subtype="element" match="rg:ListOrCombinedList">
																<children>
																	<template subtype="attribute" match="combinationId">
																		<children>
																			<content subtype="regular">
																				<styles font-size="smaller"/>
																				<format basic-type="xsd" datatype="token"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																	<template subtype="attribute" match="listNumber">
																		<children>
																			<content subtype="regular">
																				<styles font-size="smaller"/>
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
													<tgridcell>
														<styles padding-left="5pt"/>
														<children>
															<calltemplate subtype="named" match="ListOrCombinedListPart2">
																<parameters/>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles margin-left="0pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart1">
																<parameters>
																	<parameter name="step" value="1 + $offset"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles margin-left="3pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart2">
																<parameters>
																	<parameter name="step" value="1 + $offset"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles margin-left="0pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart1">
																<parameters>
																	<parameter name="step" value="2 + $offset"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles margin-left="3pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart2">
																<parameters>
																	<parameter name="step" value="2 + $offset"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles margin-left="0pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart1">
																<parameters>
																	<parameter name="step" value="3 + $offset"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles margin-left="3pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart2">
																<parameters>
																	<parameter name="step" value="3 + $offset"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles margin-left="0pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart1">
																<parameters>
																	<parameter name="step" value="4 + $offset"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles margin-left="3pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart2">
																<parameters>
																	<parameter name="step" value="4 + $offset"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles margin-left="0pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart1">
																<parameters>
																	<parameter name="step" value="5 + $offset"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles margin-left="3pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart2">
																<parameters>
																	<parameter name="step" value="5 + $offset"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles margin-left="0pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart1">
																<parameters>
																	<parameter name="step" value="6 + $offset"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles margin-left="3pt"/>
														<children>
															<calltemplate subtype="named" match="DHondtFractionPart2">
																<parameters>
																	<parameter name="step" value="6 + $offset"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="center"/>
														<styles margin-left="0pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$maxNumberOfAssignments &lt;= 6 + $offset">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="rg:NewSeats != 0">
																						<children>
																							<template subtype="element" match="rg:NewSeats">
																								<children>
																									<content subtype="regular">
																										<styles font-size="smaller"/>
																										<format basic-type="xsd" datatype="integer"/>
																									</content>
																								</children>
																								<variables/>
																							</template>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<tgridrow>
										<children>
											<tgridcell>
												<styles border-top="1px solid #000"/>
												<children>
													<text fixtext="restzetel toegekend aan">
														<properties class="columnheader"/>
													</text>
													<newline/>
													<text fixtext="lijst">
														<properties class="columnheader"/>
														<styles vertical-align="bottom"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center"/>
												<children>
													<calltemplate subtype="named" match="DHondtWinners">
														<parameters>
															<parameter name="step" value="1 + $offset"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center"/>
												<children>
													<calltemplate subtype="named" match="DHondtWinners">
														<parameters>
															<parameter name="step" value="2 + $offset"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center"/>
												<children>
													<calltemplate subtype="named" match="DHondtWinners">
														<parameters>
															<parameter name="step" value="3 + $offset"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center"/>
												<children>
													<calltemplate subtype="named" match="DHondtWinners">
														<parameters>
															<parameter name="step" value="4 + $offset"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center"/>
												<children>
													<calltemplate subtype="named" match="DHondtWinners">
														<parameters>
															<parameter name="step" value="5 + $offset"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="center"/>
												<children>
													<calltemplate subtype="named" match="DHondtWinners">
														<parameters>
															<parameter name="step" value="6 + $offset"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000"/>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewCandidateResult1-1-4">
				<parameters>
					<parameter name="fromLine" type="xs:int" occurrence="none-or-one" default-value="1"/>
					<parameter name="tillLine" type="xs:int" occurrence="none-or-one" default-value="80"/>
					<parameter name="remainingCandidates" type="xs:int"/>
				</parameters>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="25pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="210pt"/>
									</tgridcol>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<text fixtext="volgnummer     naam kandidaat">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="count ( rg:Type[ text() = &quot;op zichzelf staande lijst&quot; ] ) = 0">
																<children>
																	<text fixtext="aantal stemmen per kieskring">
																		<styles font-size="smaller"/>
																	</text>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="bottom"/>
												<children>
													<text fixtext="totaal">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext="op de lijst">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="3"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="bottom"/>
												<children>
													<text fixtext="aantal">
														<styles font-size="smaller"/>
													</text>
													<newline/>
													<text fixtext="stemmen">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridheader-rows>
							<tgridbody-rows>
								<children>
									<template subtype="element" filter="position()&gt;=$fromLine and position()&lt;=$tillLine" match="rg:CandidateAndResult">
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="CandidateId">
																<parameters/>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles padding-left="5pt" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="CandidateInTable">
																<parameters>
																	<parameter name="PublishGender" value="../kr:ListData/@PublishGender"/>
																	<parameter name="PublicationLanguage" value="../kr:ListData/@PublicationLanguage"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="3"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="4"/>
																	<parameter name="alle" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<template subtype="element" filter="position()=1 and $remainingCandidates &lt; 1" match="rg:VotesInElectoralDistrictPart">
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
														<children>
															<text fixtext="totaal">
																<styles font-size="smaller"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell joinleft="1"/>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="3"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="4"/>
																	<parameter name="alle" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewCandidateResult1-1-7">
				<parameters>
					<parameter name="fromLine" type="xs:int" occurrence="none-or-one" default-value="1"/>
					<parameter name="tillLine" type="xs:int" occurrence="none-or-one" default-value="80"/>
					<parameter name="remainingCandidates" type="xs:int"/>
					<parameter name="hasSecondPage" type="xs:boolean" default-value="false()"/>
				</parameters>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="25pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="185pt"/>
									</tgridcol>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$hasSecondPage">
																<children>
																	<text fixtext="volgnummer     naam kandidaat">
																		<styles font-size="smaller"/>
																	</text>
																	<newline/>
																	<text fixtext="op de lijst">
																		<styles font-size="smaller"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<text fixtext="volgnummer     naam kandidaat">
																		<styles font-size="smaller"/>
																	</text>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="count ( rg:Type[ text() = &quot;op zichzelf staande lijst&quot; ] ) = 0">
																<children>
																	<text fixtext="aantal stemmen per kieskring">
																		<styles font-size="smaller"/>
																	</text>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumberTop">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="7"/>
															<parameter name="alle" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="not($hasSecondPage)">
																<children>
																	<text fixtext="op de lijst">
																		<styles font-size="smaller"/>
																	</text>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="3"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="4"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="5"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="6"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumberBottom">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="7"/>
															<parameter name="alle" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridheader-rows>
							<tgridbody-rows>
								<children>
									<template subtype="element" filter="position()&gt;=$fromLine and position()&lt;=$tillLine" match="rg:CandidateAndResult">
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="CandidateId">
																<parameters/>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles padding-left="5pt" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="CandidateInTable">
																<parameters>
																	<parameter name="PublishGender" value="../kr:ListData/@PublishGender"/>
																	<parameter name="PublicationLanguage" value="../kr:ListData/@PublicationLanguage"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="3"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="4"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="5"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="6"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="7"/>
																	<parameter name="alle" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<template subtype="element" filter="position()=1 and $remainingCandidates &lt; 1" match="rg:VotesInElectoralDistrictPart">
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
														<children>
															<text fixtext="totaal">
																<styles font-size="smaller"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell joinleft="1"/>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="3"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="4"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="5"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="6"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="7"/>
																	<parameter name="alle" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewCandidateResult1-8-20">
				<parameters>
					<parameter name="fromLine" type="xs:int" occurrence="none-or-one" default-value="1"/>
					<parameter name="tillLine" type="xs:int" occurrence="none-or-one" default-value="80"/>
					<parameter name="remainingCandidates" type="xs:int"/>
				</parameters>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles font-size="smaller"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="26pt"/>
									</tgridcol>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol/>
									<tgridcol>
										<styles width="43pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<text fixtext="volgnummer">
														<styles font-size="smaller"/>
													</text>
													<newline/>
													<text fixtext="op de lijst">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="count ( rg:Type[ text() = &quot;op zichzelf staande lijst&quot; ] ) = 0 and count(rg:CandidateAndResult[position() = 1]/rg:VotesInElectoralDistrictPart[position() = 2]/rg:VotesInElectoralDistrict) &gt; 1">
																<children>
																	<text fixtext="aantal stemmen per kieskring">
																		<styles font-size="smaller"/>
																	</text>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<properties align="right"/>
												<styles border-top="1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumberTop">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="14"/>
															<parameter name="alle" value="1"/>
															<parameter name="aantal" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="3"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="4"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="5"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="6"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="7"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="8"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="9"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="10"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="11"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="12"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumber">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="13"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid #000" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="DistrictNumberBottom">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="14"/>
															<parameter name="alle" value="1"/>
															<parameter name="aantal" value="0"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
								</children>
							</tgridheader-rows>
							<tgridbody-rows>
								<children>
									<template subtype="element" filter="position()&gt;=$fromLine and position()&lt;=$tillLine" match="rg:CandidateAndResult">
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="CandidateId">
																<parameters/>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="3"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="4"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="5"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="6"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="7"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="8"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="9"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="10"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="11"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="12"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="13"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="14"/>
																	<parameter name="alle" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<template subtype="element" filter="position()=2 and $remainingCandidates &lt; 1" match="rg:VotesInElectoralDistrictPart">
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
														<children>
															<text fixtext="totaal">
																<styles font-size="xx-small"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="2"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="3"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="4"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="5"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="6"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="7"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="8"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="9"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="10"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="11"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="12"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="13"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="14"/>
																	<parameter name="alle" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="AssignmentWithinListGroups">
				<parameters/>
				<children>
					<template subtype="element" match="rg:RG520">
						<children>
							<condition>
								<children>
									<conditionbranch xpath="count(rg:AssignmentWithinListGroups) &gt; 0">
										<children>
											<text fixtext="Bij de verdeling van zetels binnen een lijstengroep wordt eerst de groepskiesdeler bepaald. Vervolgens wordt het aantal stemmen per lijst gedeeld door de groepskiesdeler. Dat levert het aantal volle zetels per lijst op. De lijst die na verdeling van de zetels het grootste overschot heeft, krijgt een restzetel."/>
											<calltemplate subtype="named" match="LineBreak">
												<parameters/>
											</calltemplate>
											<newline break="page"/>
											<calltemplate subtype="named" match="OverviewAssignmentWithinListGroups">
												<parameters/>
												<styles font-family="inherit" font-size="inherit"/>
											</calltemplate>
										</children>
									</conditionbranch>
									<conditionbranch>
										<children>
											<text fixtext="Niet van toepassing."/>
											<calltemplate subtype="named" match="LineBreak">
												<parameters/>
											</calltemplate>
											<newline break="page"/>
										</children>
									</conditionbranch>
								</children>
							</condition>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="CandidateId">
				<parameters/>
				<children>
					<template subtype="element" match="eml:Candidate">
						<children>
							<template subtype="element" match="eml:CandidateIdentifier">
								<children>
									<template subtype="attribute" match="Id">
										<children>
											<content subtype="regular">
												<styles font-size="smaller"/>
												<format basic-type="xsd" datatype="NMTOKEN"/>
											</content>
										</children>
										<variables/>
									</template>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="Vote">
				<parameters>
					<parameter name="part" occurrence="none-or-one" default-value="1"/>
					<parameter name="position" default-value="&apos;&apos;"/>
					<parameter name="alle" default-value="0"/>
					<parameter name="weighted" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="rg:VotesInElectoralDistrictPart[position() = $part] / rg:VotesInElectoralDistrict[($alle = 1 and (@id = &apos;alle&apos; or position() = $position)) or ($alle = 0 and @id != &apos;alle&apos; and position() = $position)]">
								<children>
									<template subtype="element" filter="position() = $part" match="rg:VotesInElectoralDistrictPart">
										<children>
											<template subtype="element" filter="($alle = 1 and (@id = &apos;alle&apos; or position() = $position)) or ($alle = 0 and @id != &apos;alle&apos; and position() = $position)" match="rg:VotesInElectoralDistrict">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$weighted = 0">
																<children>
																	<content subtype="regular">
																		<styles font-size="smaller"/>
																	</content>
																</children>
															</conditionbranch>
															<conditionbranch xpath="$weighted = 1">
																<children>
																	<content subtype="regular">
																		<styles font-size="smaller"/>
																	</content>
																	<newline/>
																	<template subtype="attribute" match="weighted">
																		<children>
																			<content subtype="regular">
																				<styles font-size="smaller"/>
																				<format basic-type="xsd" datatype="integer"/>
																			</content>
																		</children>
																		<variables/>
																	</template>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
												<variables/>
											</template>
										</children>
										<variables/>
									</template>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<text fixtext=".">
										<styles color="white" font-size="smaller"/>
									</text>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="DistrictNumber">
				<parameters>
					<parameter name="part" occurrence="none-or-one" default-value="1"/>
					<parameter name="position" default-value="&apos;&apos;"/>
					<parameter name="alle" default-value="0"/>
				</parameters>
				<children>
					<template subtype="element" filter="position() = 1" match="rg:CandidateAndResult">
						<children>
							<template subtype="element" filter="position() = $part" match="rg:VotesInElectoralDistrictPart">
								<children>
									<template subtype="element" match="rg:VotesInElectoralDistrict">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="$alle = 1 and @id = &apos;alle&apos;">
														<children>
															<text fixtext="Totaal">
																<styles font-size="smaller"/>
															</text>
															<newline/>
															<text fixtext="aantal">
																<styles font-size="smaller"/>
															</text>
															<newline/>
															<text fixtext="stemmen">
																<styles font-size="smaller"/>
															</text>
														</children>
													</conditionbranch>
													<conditionbranch xpath="position() = $position and @id != &apos;alle&apos;">
														<children>
															<template subtype="attribute" match="id">
																<children>
																	<content subtype="regular">
																		<styles font-size="smaller"/>
																		<format basic-type="xsd" datatype="NMTOKEN"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
													</conditionbranch>
												</children>
											</condition>
										</children>
										<variables/>
									</template>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="DistrictNumberTop">
				<parameters>
					<parameter name="part" occurrence="none-or-one" default-value="1"/>
					<parameter name="position" default-value="&apos;&apos;"/>
					<parameter name="alle" default-value="0"/>
					<parameter name="aantal" default-value="0"/>
				</parameters>
				<children>
					<template subtype="element" filter="position() = 1" match="rg:CandidateAndResult">
						<children>
							<template subtype="element" filter="position() = $part" match="rg:VotesInElectoralDistrictPart">
								<children>
									<template subtype="element" match="rg:VotesInElectoralDistrict">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="$alle = 1 and @id = &apos;alle&apos;">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$aantal = 1">
																		<children>
																			<text fixtext="totaal">
																				<styles font-size="smaller"/>
																			</text>
																			<newline/>
																			<text fixtext="aantal">
																				<styles font-size="smaller"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<text fixtext="totaal">
																				<styles font-size="smaller"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</conditionbranch>
												</children>
											</condition>
										</children>
										<variables/>
									</template>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="DistrictNumberBottom">
				<parameters>
					<parameter name="part" occurrence="none-or-one" default-value="1"/>
					<parameter name="position" default-value="&apos;&apos;"/>
					<parameter name="alle" default-value="0"/>
					<parameter name="aantal" default-value="1"/>
				</parameters>
				<children>
					<template subtype="element" filter="position() = 1" match="rg:CandidateAndResult">
						<children>
							<template subtype="element" filter="position() = $part" match="rg:VotesInElectoralDistrictPart">
								<children>
									<template subtype="element" match="rg:VotesInElectoralDistrict">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="$alle = 1 and @id = &apos;alle&apos;">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$aantal = 1">
																		<children>
																			<text fixtext="aantal">
																				<styles font-size="smaller"/>
																			</text>
																			<newline/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext="stemmen">
																<styles font-size="smaller"/>
															</text>
														</children>
													</conditionbranch>
													<conditionbranch xpath="position() = $position and @id != &apos;alle&apos;">
														<children>
															<template subtype="attribute" match="id">
																<children>
																	<content subtype="regular">
																		<styles font-size="smaller"/>
																		<format basic-type="xsd" datatype="NMTOKEN"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
													</conditionbranch>
												</children>
											</condition>
										</children>
										<variables/>
									</template>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="TotalVotes">
				<parameters>
					<parameter name="part" occurrence="none-or-one" default-value="1"/>
					<parameter name="position" default-value="&apos;&apos;"/>
					<parameter name="alle" default-value="0"/>
					<parameter name="weighted" default-value="0"/>
				</parameters>
				<children>
					<template subtype="element" filter="position() = $part" match="rg:VotesInElectoralDistrictPart">
						<children>
							<template subtype="element" filter="($alle = 1 and (@id = &apos;alle&apos; or position() = $position)) or ($alle = 0 and @id != &apos;alle&apos; and position() = $position)" match="rg:VotesInElectoralDistrict">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$weighted = 0">
												<children>
													<content subtype="regular">
														<styles font-size="smaller"/>
													</content>
												</children>
											</conditionbranch>
											<conditionbranch xpath="$weighted = 1">
												<children>
													<template subtype="attribute" match="weighted">
														<children>
															<content subtype="regular">
																<styles font-size="smaller"/>
																<format basic-type="xsd" datatype="integer"/>
															</content>
														</children>
														<variables/>
													</template>
												</children>
											</conditionbranch>
										</children>
									</condition>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="TotalVotes2">
				<parameters>
					<parameter name="position" default-value="&apos;&apos;"/>
					<parameter name="alle" default-value="0"/>
					<parameter name="weighted" default-value="0"/>
				</parameters>
				<children>
					<template subtype="element" filter="($alle = 1 and (@id = &apos;alle&apos; or position() = $position)) or ($alle = 0 and @id != &apos;alle&apos; and position() = $position)" match="rg:VotesInElectoralDistrict">
						<children>
							<condition>
								<children>
									<conditionbranch xpath="$weighted = 0">
										<children>
											<content subtype="regular">
												<styles font-size="smaller"/>
											</content>
										</children>
									</conditionbranch>
									<conditionbranch xpath="$weighted = 1">
										<children>
											<template subtype="attribute" match="weighted">
												<children>
													<content subtype="regular">
														<styles font-size="smaller"/>
														<format basic-type="xsd" datatype="integer"/>
													</content>
												</children>
												<variables/>
											</template>
										</children>
									</conditionbranch>
								</children>
							</condition>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="PartyHeaderEvenOdd">
				<parameters>
					<parameter name="isEvenOdd" type="xs:int"/>
					<parameter name="isSeats" type="xs:int"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="count(rg:CandidateAndResult[position() = 1]/rg:VotesInElectoralDistrictPart) = 2 and $isEvenOdd = 1">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$SV_OutputFormat = &apos;RTF&apos;">
												<children>
													<newline break="page"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<paragraph paragraphtag="p">
										<styles break-before="even-page"/>
										<children>
											<condition>
												<children>
													<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
														<children>
															<newline/>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<calltemplate subtype="named" match="PartyHeader2or4Lines">
												<parameters>
													<parameter name="isSeats" value="$isSeats"/>
												</parameters>
											</calltemplate>
										</children>
									</paragraph>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<newline break="page"/>
									<paragraph paragraphtag="p">
										<children>
											<calltemplate subtype="named" match="PartyHeader2or4Lines">
												<parameters>
													<parameter name="isSeats" value="$isSeats"/>
												</parameters>
											</calltemplate>
										</children>
									</paragraph>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="PartyHeader2or4Lines">
				<parameters>
					<parameter name="isSeats" type="xs:int"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$isSeats = 1">
								<children>
									<calltemplate subtype="named" match="PartyHeader4Lines">
										<parameters/>
									</calltemplate>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<calltemplate subtype="named" match="PartyHeader2Lines">
										<parameters/>
									</calltemplate>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="CandidateListSubmitted">
				<parameters>
					<parameter name="districtId" type="xs:int"/>
					<parameter name="smaller" type="xs:int" occurrence="none-or-one" default-value="1"/>
				</parameters>
				<children>
					<template subtype="element" filter="position() = $districtId" match="rg:SubmittedInElectoralDistrict">
						<children>
							<condition>
								<children>
									<conditionbranch xpath=". = &quot;true&quot;">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="$smaller = 1">
														<children>
															<text fixtext="*">
																<styles font-size="smaller" vertical-align="middle"/>
															</text>
														</children>
													</conditionbranch>
													<conditionbranch>
														<children>
															<text fixtext="*">
																<styles vertical-align="middle"/>
															</text>
														</children>
													</conditionbranch>
												</children>
											</condition>
										</children>
									</conditionbranch>
									<conditionbranch>
										<children>
											<condition>
												<children>
													<conditionbranch xpath="$smaller = 1">
														<children>
															<text fixtext="·">
																<styles font-size="smaller"/>
															</text>
														</children>
													</conditionbranch>
													<conditionbranch>
														<children>
															<text fixtext="·"/>
														</children>
													</conditionbranch>
												</children>
											</condition>
										</children>
									</conditionbranch>
								</children>
							</condition>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="CandidateListSubmittedHeader">
				<parameters>
					<parameter name="districtId" type="xs:int"/>
					<parameter name="smaller" type="xs:int" occurrence="none-or-one" default-value="1"/>
				</parameters>
				<children>
					<template subtype="element" filter="position() = 1" match="rg:OverviewOfListsAndDistricts">
						<children>
							<template subtype="element" filter="position() = $districtId" match="rg:SubmittedInElectoralDistrict">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$smaller = 1">
												<children>
													<template subtype="attribute" match="id">
														<children>
															<content subtype="regular">
																<styles font-size="smaller"/>
																<format basic-type="xsd" datatype="integer"/>
															</content>
														</children>
														<variables/>
													</template>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<template subtype="attribute" match="id">
														<children>
															<content subtype="regular">
																<format basic-type="xsd" datatype="integer"/>
															</content>
														</children>
														<variables/>
													</template>
												</children>
											</conditionbranch>
										</children>
									</condition>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ListOrCombinedListPart2">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="count(rg:ListOrCombinedList/rg:ListInCombination) &gt;0">
								<children>
									<text fixtext="(">
										<styles font-size="smaller"/>
									</text>
									<template subtype="element" match="rg:ListOrCombinedList">
										<children>
											<template subtype="element" match="rg:ListInCombination">
												<children>
													<content subtype="regular">
														<styles font-size="smaller"/>
														<format basic-type="xsd" datatype="integer"/>
													</content>
													<condition>
														<children>
															<conditionbranch xpath="position() != last()">
																<children>
																	<text fixtext=", ">
																		<styles font-size="smaller"/>
																	</text>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
												<variables/>
											</template>
										</children>
										<variables/>
									</template>
									<text fixtext=")">
										<styles font-size="smaller"/>
									</text>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="//kr:ElectionSubcategory[text()= &apos;PS2&apos; or text()= &apos;TK&apos;]">
								<children>
									<template subtype="element" match="rg:ListOrCombinedList">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="@districtNumber">
														<children>
															<text fixtext="(Kieskring ">
																<styles font-size="smaller"/>
															</text>
															<template subtype="attribute" match="districtNumber">
																<children>
																	<content subtype="regular">
																		<styles font-size="smaller"/>
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<text fixtext=")">
																		<styles font-size="smaller"/>
																	</text>
																</children>
																<variables/>
															</template>
														</children>
													</conditionbranch>
												</children>
											</condition>
										</children>
										<variables/>
									</template>
								</children>
							</conditionbranch>
							<conditionbranch xpath="//kr:ElectionSubcategory[text()= &apos;EK&apos;]">
								<children>
									<template subtype="element" match="rg:ListOrCombinedList">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="@provinceName">
														<children>
															<template subtype="attribute" match="provinceName">
																<children>
																	<text fixtext="(">
																		<styles font-size="smaller"/>
																	</text>
																	<content subtype="regular">
																		<styles font-size="smaller"/>
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<text fixtext=")">
																		<styles font-size="smaller"/>
																	</text>
																</children>
																<variables/>
															</template>
														</children>
													</conditionbranch>
												</children>
											</condition>
										</children>
										<variables/>
									</template>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="DHondtFractionPart1">
				<parameters>
					<parameter name="step" type="xs:integer"/>
				</parameters>
				<children>
					<template subtype="element" match="rg:DHondtFraction">
						<children>
							<condition>
								<children>
									<conditionbranch xpath="@step = $step">
										<children>
											<calltemplate subtype="named" match="ElectionFractionPart1">
												<parameters>
													<parameter name="smaller" value="1"/>
													<parameter name="bold" value="count (self::rg:DHondtFraction[@winner = &quot;true&quot;])"/>
												</parameters>
											</calltemplate>
										</children>
									</conditionbranch>
								</children>
							</condition>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="DHondtFractionPart2">
				<parameters>
					<parameter name="step" type="xs:integer"/>
				</parameters>
				<children>
					<template subtype="element" match="rg:DHondtFraction">
						<children>
							<condition>
								<children>
									<conditionbranch xpath="@step = $step">
										<children>
											<calltemplate subtype="named" match="ElectionFractionPart2">
												<parameters>
													<parameter name="smaller" value="1"/>
													<parameter name="bold" value="count (self::rg:DHondtFraction[@winner = &quot;true&quot;])"/>
												</parameters>
											</calltemplate>
										</children>
									</conditionbranch>
								</children>
							</condition>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="DHondtHeader">
				<parameters>
					<parameter name="step" type="xs:integer"/>
					<parameter name="maxNumberOfAssignments" type="xs:integer"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$maxNumberOfAssignments &gt;= $step">
								<children>
									<autocalc xpath="$step">
										<properties class="columnheader"/>
										<styles font-size="smaller"/>
									</autocalc>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="DHondtWinners">
				<parameters>
					<parameter name="step" type="xs:integer"/>
				</parameters>
				<children>
					<template subtype="element" filter="rg:DHondtFraction[@step = $step and @winner=&quot;true&quot;]" match="rg:DHondtAssignmentLine">
						<children>
							<template subtype="element" match="rg:ListOrCombinedList">
								<children>
									<template subtype="attribute" match="combinationId">
										<children>
											<content subtype="regular">
												<styles font-size="smaller"/>
												<format basic-type="xsd" datatype="token"/>
											</content>
										</children>
										<variables/>
									</template>
									<template subtype="attribute" match="listNumber">
										<children>
											<content subtype="regular">
												<styles font-size="smaller"/>
												<format basic-type="xsd" datatype="integer"/>
											</content>
										</children>
										<variables/>
									</template>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="LineBreakRTF">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$SV_OutputFormat = &apos;RTF&apos;">
								<children>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="VotesInElectoralDistrict">
				<parameters>
					<parameter name="districtId"/>
					<parameter name="addSpaceAfterNumbers" type="xs:boolean" occurrence="none-or-one" default-value="false()"/>
				</parameters>
				<children>
					<template subtype="element" match="rg:VotesInElectoralDistrict">
						<children>
							<condition>
								<children>
									<conditionbranch xpath="@id != &quot;alle&quot; and position() = $districtId">
										<children>
											<content subtype="regular"/>
										</children>
									</conditionbranch>
								</children>
							</condition>
						</children>
						<variables/>
					</template>
					<condition>
						<children>
							<conditionbranch xpath="$addSpaceAfterNumbers">
								<children>
									<text fixtext=" "/>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="TotalVotesInElectoralDistrict">
				<parameters>
					<parameter name="districtId"/>
					<parameter name="addSpaceAfterNumbers" type="xs:boolean" occurrence="none-or-one" default-value="false()"/>
				</parameters>
				<children>
					<template subtype="element" match="rg:OverviewOfDistrictsVotes">
						<children>
							<template subtype="element" match="rg:VotesInElectoralDistrict">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="@id != &quot;alle&quot; and position() = $districtId">
												<children>
													<content subtype="regular"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
					<condition>
						<children>
							<conditionbranch xpath="$addSpaceAfterNumbers">
								<children>
									<text fixtext=" "/>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="BlancVotesInElectoralDistrict">
				<parameters>
					<parameter name="districtId"/>
					<parameter name="addSpaceAfterNumbers" type="xs:boolean" occurrence="none-or-one" default-value="false()"/>
				</parameters>
				<children>
					<template subtype="element" match="rg:OverviewOfBlancVotes">
						<children>
							<template subtype="element" match="rg:VotesInElectoralDistrict">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="@id != &quot;alle&quot; and position() = $districtId">
												<children>
													<content subtype="regular"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
					<condition>
						<children>
							<conditionbranch xpath="$addSpaceAfterNumbers">
								<children>
									<text fixtext=" "/>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="InvalidVotesInElectoralDistrict">
				<parameters>
					<parameter name="districtId"/>
					<parameter name="addSpaceAfterNumbers" type="xs:boolean" occurrence="none-or-one" default-value="false()"/>
				</parameters>
				<children>
					<template subtype="element" match="rg:OverviewOfInvalidVotes">
						<children>
							<template subtype="element" match="rg:VotesInElectoralDistrict">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="@id != &quot;alle&quot; and position() = $districtId">
												<children>
													<content subtype="regular"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
					<condition>
						<children>
							<conditionbranch xpath="$addSpaceAfterNumbers">
								<children>
									<text fixtext=" "/>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ElectoralDistrictNumber">
				<parameters>
					<parameter name="districtId"/>
					<parameter name="addSpaceAfterNumbers" type="xs:boolean" occurrence="none-or-one" default-value="false()"/>
				</parameters>
				<children>
					<template subtype="element" filter="position() = $districtId" match="rg:ElectoralDistrictName">
						<children>
							<template subtype="attribute" match="id">
								<children>
									<content subtype="regular">
										<format basic-type="xsd" datatype="positiveInteger"/>
									</content>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
					<condition>
						<children>
							<conditionbranch xpath="$addSpaceAfterNumbers">
								<children>
									<text fixtext=" "/>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="PartyNamePdfWrapped">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$SV_OutputFormat = &apos;RTF&apos;">
								<children>
									<template subtype="element" match="eml:AffiliationIdentifier">
										<children>
											<template subtype="element" match="eml:RegisteredName">
												<children>
													<content subtype="regular">
														<format basic-type="xsd" datatype="token"/>
													</content>
												</children>
												<variables/>
											</template>
										</children>
										<variables/>
									</template>
								</children>
							</conditionbranch>
							<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
								<children>
									<paragraph paragraphtag="pre-wrap">
										<styles white-space="pre-wrap"/>
										<children>
											<template subtype="element" match="eml:AffiliationIdentifier">
												<children>
													<template subtype="element" match="eml:RegisteredName">
														<children>
															<content subtype="regular">
																<format basic-type="xsd" datatype="token"/>
															</content>
														</children>
														<variables/>
													</template>
												</children>
												<variables/>
											</template>
										</children>
									</paragraph>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ElectoralDistrictsOverview">
				<parameters/>
				<children>
					<text fixtext="Kieskringen en gemeente of openbaar lichaam waar het hoofdstembureau is gevestigd:"/>
					<newline/>
					<newline/>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol/>
									<tgridcol/>
								</children>
							</tgridbody-cols>
							<tgridbody-rows>
								<children>
									<template subtype="element" match="rg:ElectoralDistrictsOverview">
										<children>
											<template subtype="element" match="rg:ElectoralDistrictName">
												<children>
													<tgridrow>
														<children>
															<tgridcell>
																<styles vertical-align="top" width="30pt"/>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
																				<children>
																					<paragraph>
																						<children>
																							<autocalc xpath="@id"/>
																							<text fixtext="."/>
																						</children>
																					</paragraph>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="@id"/>
																					<text fixtext="."/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</tgridcell>
															<tgridcell>
																<styles vertical-align="top"/>
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="string"/>
																	</content>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
												</children>
												<variables/>
											</template>
										</children>
										<variables/>
									</template>
								</children>
							</tgridbody-rows>
						</children>
						<wizard-data-repeat>
							<children/>
						</wizard-data-repeat>
						<wizard-data-rows>
							<children/>
						</wizard-data-rows>
						<wizard-data-columns>
							<children/>
						</wizard-data-columns>
					</tgrid>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ProvinceNameEK">
				<parameters>
					<parameter name="id" type="xs:int"/>
				</parameters>
				<children>
					<paragraph paragraphtag="pre">
						<styles white-space="pre"/>
						<children>
							<condition>
								<children>
									<conditionbranch xpath="rg:ElectoralDistrictName[position() = $id and text() != &apos;&apos;]">
										<children>
											<template subtype="element" filter="position() = $id" match="rg:ElectoralDistrictName">
												<children>
													<content subtype="regular"/>
												</children>
												<variables/>
											</template>
										</children>
									</conditionbranch>
									<conditionbranch>
										<children>
											<template subtype="source" match="XML">
												<children>
													<template subtype="element" match="eml:EML">
														<children>
															<template subtype="element" match="eml:Result">
																<children>
																	<template subtype="element" match="rg:RG520">
																		<children>
																			<template subtype="element" match="rg:ElectoralDistrictsOverview">
																				<children>
																					<template subtype="element" filter="position() = $id" match="rg:ElectoralDistrictName">
																						<children>
																							<content subtype="regular"/>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
												</children>
												<variables/>
											</template>
										</children>
									</conditionbranch>
								</children>
							</condition>
						</children>
					</paragraph>
				</children>
			</globaltemplate>
		</children>
	</designfragments>
	<xmltables/>
	<authentic-custom-toolbar-buttons/>
</structure>
