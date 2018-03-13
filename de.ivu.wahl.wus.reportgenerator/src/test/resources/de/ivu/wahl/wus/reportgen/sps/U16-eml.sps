<?xml version="1.0" encoding="UTF-8"?>
<structure version="18" xsltversion="1" html-doctype="HTML4 Transitional" compatibility-view="IE9" html-outputextent="Complete" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" useimportschema="1" embed-images="1" pastemode="xml" enable-authentic-scripts="1" authentic-scripts-in-debug-mode-external="0" generated-file-location="DEFAULT">
	<parameters>
		<parameter name="generateDate" default="01-02-2003 04:05:06"/>
		<parameter name="hashCode" default="1234 5678 90AB CDEF 1234 5678 90AB CDEF 1234 5678 90AB CDEF 1234 5678 90AB CDEF"/>
		<parameter name="isDraft" default="false"/>
		<parameter name="lang" default="1"/>
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
			<xsdschemasource name="XML" main="1" schemafile="D:\projekte\Eml-kiesraad\EML-v5.0-os\520-extended.xsd" workingxmlfile="D:\projekte\EML-kiesraad\Examples-2.14\Resultaat_EK2004.U16-eml-test.emlpp.xml"/>
		</schemasources>
	</schemasources>
	<modules>
		<module spsfile="reused-parts-P22.sps"/>
	</modules>
	<flags>
		<scripts/>
		<mainparts>
			<mainpart match="/" spsfile="reused-parts-P22.sps" isactive="0"/>
			<mainpart match="/" spsfile="reused-parts-simple.sps" isactive="0"/>
		</mainparts>
		<globalparts/>
		<designfragments>
			<designfragment match="CandidateCityInTable2" spsfile="reused-parts-P22.sps" isactive="1"/>
			<designfragment match="CandidateInTable2" spsfile="reused-parts-P22.sps" isactive="1"/>
			<designfragment match="O3-H-CandidateVotes" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="O3CandidateVotes" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewCandidateListsEK" spsfile="reused-parts-P22.sps" isactive="1"/>
			<designfragment match="OverviewCandidateListsEP" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewCandidateListsTK" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewCandidateResult1-1-4" spsfile="reused-parts-P22.sps" isactive="1"/>
			<designfragment match="OverviewCandidateResult1-1-7" spsfile="reused-parts-P22.sps" isactive="1"/>
			<designfragment match="OverviewCandidateResult1-8-20" spsfile="reused-parts-P22.sps" isactive="1"/>
			<designfragment match="OverviewCandidateResultP22-1-14" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewCandidateResultP22-1-14-Base" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewCandidateResultP22-1-15" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewCandidateResultP22-2" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewCandidateResultP22-2-H" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewVotes1-10" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewVotes11-20" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewVotes2" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewVotes3" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewVotes4" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewVotesP22-2" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="ElectionNameAcceptance" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="ElectionNameAcceptanceShort" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="PartyNameOrFirstCandidate" spsfile="reused-parts-simple.sps" isactive="0"/>
		</designfragments>
		<pagelayouts/>
		<xpath-functions/>
	</flags>
	<scripts>
		<script language="javascript">function doInitialize() {  markLoaded();  return false;  }  function doIntref(inVerwijzingRef, inLabelRef) {  var myWin = window;  var myLabel = &apos;label-&apos; + inLabelRef;  myWin.location.replace(&apos;#&apos; + myLabel);  }  //</script>
	</scripts>
	<script-project>
		<Project version="3" app="AuthenticView"/>
	</script-project>
	<importedxslt>
		<file url="D:\projekte\de.ivu.wahl.wus.reportgenerator\src\main\resources\de\ivu\wahl\wus\reportgen\text\nl\reused-parts-simple-text.xslt"/>
	</importedxslt>
	<globalstyles/>
	<mainparts>
		<children>
			<globaltemplate subtype="main" match="/">
				<document-properties title="Model U 16"/>
				<children>
					<documentsection>
						<properties columncount="1" columngap="0.50in" headerfooterheight="variable" pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="auto" pagestart="next" paperheight="11.69in" papermarginbottom="1.2in" papermarginfooter="0.0in" papermarginheader="0.0in" papermarginleft="1.0in" papermarginright="1.0in" papermargintop="0.9in" paperwidth="8.27in" title="Model U 16"/>
						<children>
							<globaltemplate subtype="pagelayout" match="footerall">
								<children>
									<calltemplate subtype="named" match="LegacyFooter">
										<parameters/>
									</calltemplate>
								</children>
							</globaltemplate>
						</children>
						<watermark>
							<image transparency="50" fill-page="1" center-if-not-fill="1"/>
							<text transparency="50"/>
						</watermark>
					</documentsection>
					<paragraph>
						<children>
							<template subtype="source" match="XML">
								<children>
									<template subtype="element" match="eml:EML">
										<children>
											<template subtype="element" match="eml:Result">
												<children>
													<template subtype="element" match="eml:Election">
														<children>
															<calltemplate subtype="named" match="Line">
																<parameters/>
															</calltemplate>
															<calltemplate subtype="named" match="LineBreakPdf">
																<parameters/>
															</calltemplate>
															<newline/>
															<text fixtext="Model U 16">
																<properties class="pretitle"/>
															</text>
															<newline/>
															<newline/>
															<text fixtext="Proces-verbaal van de verkiezingsuitslag van de Eerste Kamer">
																<properties class="title"/>
															</text>
															<newline/>
															<newline/>
															<text fixtext="Met dit formulier wordt verslag gedaan van de zitting waarin de uitslag is vastgesteld van de verkiezing voor de Eerste Kamer."/>
															<newline/>
															<newline/>
															<calltemplate subtype="named" match="Line">
																<parameters/>
															</calltemplate>
															<calltemplate subtype="named" match="LineBreakPdf">
																<parameters/>
															</calltemplate>
															<newline/>
															<calltemplate subtype="named" match="ChapterElection">
																<parameters>
																	<parameter name="isH3"/>
																	<parameter name="isU16" value="1"/>
																</parameters>
															</calltemplate>
															<newline/>
															<newline/>
															<text fixtext="Datum verkiezing: "/>
															<template subtype="element" match="eml:ElectionIdentifier">
																<children>
																	<template subtype="element" match="kr:ElectionDate">
																		<children>
																			<calltemplate subtype="named" match="Date">
																				<parameters>
																					<parameter name="isBold" value="1"/>
																					<parameter name="fontSize"/>
																				</parameters>
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
													<newline/>
													<newline/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
													<newline/>
													<text fixtext="2. Zitting">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<text fixtext="Het betreft de zitting van het centraal stembureau in &apos;s-Gravenhage."/>
													<template subtype="element" match="rg:RG520">
														<children>
															<newline/>
															<newline/>
															<tgrid>
																<children>
																	<tgridbody-cols>
																		<children>
																			<tgridcol>
																				<styles width="100pt"/>
																			</tgridcol>
																			<tgridcol>
																				<styles width="240pt"/>
																			</tgridcol>
																		</children>
																	</tgridbody-cols>
																	<tgridbody-rows>
																		<children>
																			<tgridrow>
																				<children>
																					<tgridcell>
																						<styles font-size="small"/>
																						<children>
																							<text fixtext="Datum zitting"/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<styles text-align="left"/>
																						<children>
																							<template subtype="element" match="rg:DateOfMeetingO1P20">
																								<children>
																									<calltemplate subtype="named" match="Date">
																										<parameters>
																											<parameter name="isBold" value="1"/>
																											<parameter name="fontSize"/>
																										</parameters>
																									</calltemplate>
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
																						<styles font-size="small"/>
																						<children>
																							<text fixtext="Tijdstip zitting"/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<styles text-align="left"/>
																						<children>
																							<template subtype="element" match="rg:TimeOfMeetingP20">
																								<children>
																									<content subtype="regular">
																										<styles font-weight="bold"/>
																										<format basic-type="xsd" datatype="NMTOKEN"/>
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
														<variables/>
													</template>
													<calltemplate subtype="named" match="LineBreakTable">
														<parameters/>
													</calltemplate>
													<newline/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
													<newline/>
													<text fixtext="3. Stemwaarden per provincie">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<text fixtext="Volgens artikel U 2 van de Kieswet zijn de stemwaarden vastgesteld op basis van de inwonersaantallen per provincie op 1 januari van het verkiezingsjaar, zoals gepubliceerd door het Centraal Bureau voor de Statistiek (Stcrt. "/>
													<template subtype="element" match="rg:RG520">
														<children>
															<template subtype="element" match="rg:PublicationVoteValuesDate">
																<children>
																	<autocalc xpath="substring(text(),1, 4)"/>
																</children>
																<variables/>
															</template>
															<text fixtext=", "/>
															<template subtype="element" match="rg:PublicationVoteValuesNumber">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																</children>
																<variables/>
															</template>
															<text fixtext=")."/>
															<newline/>
															<newline/>
															<template subtype="element" match="rg:ElectoralDistrictsOverview">
																<children>
																	<tgrid>
																		<properties border="0" cellpadding="1" cellspacing="3" width="100%"/>
																		<styles width="80%"/>
																		<children>
																			<tgridbody-cols>
																				<children>
																					<tgridcol>
																						<styles width="30%"/>
																					</tgridcol>
																					<tgridcol>
																						<styles width="20%"/>
																					</tgridcol>
																					<tgridcol>
																						<styles width="30%"/>
																					</tgridcol>
																					<tgridcol>
																						<styles width="20%"/>
																					</tgridcol>
																				</children>
																			</tgridbody-cols>
																			<tgridbody-rows>
																				<children>
																					<tgridrow>
																						<children>
																							<tgridcell/>
																							<tgridcell>
																								<children>
																									<text fixtext="stemwaarde">
																										<properties class="columnheader"/>
																									</text>
																								</children>
																							</tgridcell>
																							<tgridcell/>
																							<tgridcell>
																								<children>
																									<text fixtext="stemwaarde">
																										<properties class="columnheader"/>
																									</text>
																								</children>
																							</tgridcell>
																						</children>
																					</tgridrow>
																					<tgridrow>
																						<children>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 1" match="rg:ElectoralDistrictName">
																										<children>
																											<content subtype="regular"/>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 1" match="rg:ElectoralDistrictName">
																										<children>
																											<template subtype="attribute" match="voteValue">
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
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 7" match="rg:ElectoralDistrictName">
																										<children>
																											<content subtype="regular"/>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 7" match="rg:ElectoralDistrictName">
																										<children>
																											<template subtype="attribute" match="voteValue">
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
																						</children>
																					</tgridrow>
																					<tgridrow>
																						<children>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 2" match="rg:ElectoralDistrictName">
																										<children>
																											<content subtype="regular"/>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 2" match="rg:ElectoralDistrictName">
																										<children>
																											<template subtype="attribute" match="voteValue">
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
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 8" match="rg:ElectoralDistrictName">
																										<children>
																											<content subtype="regular"/>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 8" match="rg:ElectoralDistrictName">
																										<children>
																											<template subtype="attribute" match="voteValue">
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
																						</children>
																					</tgridrow>
																					<tgridrow>
																						<children>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 3" match="rg:ElectoralDistrictName">
																										<children>
																											<content subtype="regular"/>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 3" match="rg:ElectoralDistrictName">
																										<children>
																											<template subtype="attribute" match="voteValue">
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
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 9" match="rg:ElectoralDistrictName">
																										<children>
																											<content subtype="regular"/>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 9" match="rg:ElectoralDistrictName">
																										<children>
																											<template subtype="attribute" match="voteValue">
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
																						</children>
																					</tgridrow>
																					<tgridrow>
																						<children>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 4" match="rg:ElectoralDistrictName">
																										<children>
																											<content subtype="regular"/>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 4" match="rg:ElectoralDistrictName">
																										<children>
																											<template subtype="attribute" match="voteValue">
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
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 10" match="rg:ElectoralDistrictName">
																										<children>
																											<content subtype="regular"/>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 10" match="rg:ElectoralDistrictName">
																										<children>
																											<template subtype="attribute" match="voteValue">
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
																						</children>
																					</tgridrow>
																					<tgridrow>
																						<children>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 5" match="rg:ElectoralDistrictName">
																										<children>
																											<content subtype="regular"/>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 5" match="rg:ElectoralDistrictName">
																										<children>
																											<template subtype="attribute" match="voteValue">
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
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 11" match="rg:ElectoralDistrictName">
																										<children>
																											<content subtype="regular"/>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 11" match="rg:ElectoralDistrictName">
																										<children>
																											<template subtype="attribute" match="voteValue">
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
																						</children>
																					</tgridrow>
																					<tgridrow>
																						<children>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 6" match="rg:ElectoralDistrictName">
																										<children>
																											<content subtype="regular"/>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 6" match="rg:ElectoralDistrictName">
																										<children>
																											<template subtype="attribute" match="voteValue">
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
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 12" match="rg:ElectoralDistrictName">
																										<children>
																											<content subtype="regular"/>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<properties align="left"/>
																								<children>
																									<template subtype="element" filter="position() = 12" match="rg:ElectoralDistrictName">
																										<children>
																											<template subtype="attribute" match="voteValue">
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
															<calltemplate subtype="named" match="LineBreakTable">
																<parameters/>
															</calltemplate>
														</children>
														<variables/>
													</template>
													<newline break="page"/>
													<newline/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
													<newline/>
													<text fixtext="4. Ingeleverde kandidatenlijsten">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<text fixtext="De volgende politieke groeperingen hebben deelgenomen aan de verkiezing:"/>
													<calltemplate subtype="named" match="LineBreak">
														<parameters/>
													</calltemplate>
													<template subtype="element" match="rg:RG520">
														<children>
															<calltemplate subtype="named" match="OverviewCandidateListsEK">
																<parameters/>
															</calltemplate>
														</children>
														<variables/>
													</template>
													<newline/>
													<newline/>
													<newline break="page"/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
													<newline/>
													<text fixtext="5. Stemmen per lijst ">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<template subtype="element" match="rg:RG520">
														<children>
															<calltemplate subtype="named" match="OverviewVotesEK">
																<parameters/>
															</calltemplate>
														</children>
														<variables/>
													</template>
													<newline/>
													<newline/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
													<newline/>
													<text fixtext="6. Kiesdeler ">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<template subtype="element" match="rg:RG520">
														<children>
															<calltemplate subtype="named" match="Kiesdeler">
																<parameters>
																	<parameter name="isEK"/>
																</parameters>
															</calltemplate>
														</children>
														<variables/>
													</template>
													<newline break="page"/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
													<newline/>
													<text fixtext="7. Verdeling volle zetels">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<template subtype="element" match="rg:RG520">
														<children>
															<calltemplate subtype="named" match="OverviewFirstAssigment">
																<parameters>
																	<parameter name="isEK" value="1"/>
																</parameters>
															</calltemplate>
														</children>
														<variables/>
													</template>
													<newline break="page"/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
													<newline/>
													<text fixtext="8. Verdeling restzetels">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<text fixtext="De restzetels zijn achtereenvolgens toegewezen aan de lijsten die met een zetel erbij het grootste gemiddelde aantal stemmen per zetel hebben."/>
													<newline/>
													<newline/>
													<template subtype="element" match="rg:RG520">
														<children>
															<calltemplate subtype="named" match="OverviewDHondtAssignment">
																<parameters/>
															</calltemplate>
															<calltemplate subtype="named" match="OverviewDHondtAssignmentSpecial">
																<parameters/>
															</calltemplate>
														</children>
														<variables/>
													</template>
													<newline/>
													<newline break="page"/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
													<newline/>
													<text fixtext="9. Totaal aantal zetels per politieke groepering">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<template subtype="element" match="rg:RG520">
														<children>
															<calltemplate subtype="named" match="OverviewListResult">
																<parameters/>
															</calltemplate>
														</children>
														<variables/>
													</template>
													<newline/>
													<newline break="page"/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
													<newline/>
													<text fixtext="10. Verdeling zetels binnen lijstengroep">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<template subtype="element" match="rg:RG520">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="count(rg:AssignmentWithinListGroups) &gt; 0">
																		<children>
																			<text fixtext="Op basis van artikel U 12 van de Kieswet wordt bij de verdeling van zetels binnen een lijstengroep eerst de groepskiesdeler bepaald. Vervolgens wordt het aantal stemmen per lijst gedeeld door de groepskiesdeler. Dat levert het aantal volle zetels per lijst op. De lijst die na verdeling van de zetels het grootste overschot heeft, krijgt de restzetel(s)."/>
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
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
													<newline/>
													<text fixtext="11. Stemmen per lijst met tenminste één zetel en toewijzing zetels aan kandidaten">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<template subtype="element" match="rg:RG520">
														<children>
															<calltemplate subtype="named" match="OverviewCandidateResultU16-IJ">
																<parameters>
																	<parameter name="isSeats" value="1"/>
																</parameters>
															</calltemplate>
														</children>
														<variables/>
													</template>
													<newline break="page"/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
													<newline/>
													<text fixtext="12. Stemmen per lijst zonder zetel">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="count( rg:RG520/rg:OverviewOfCandidatesAndResults/rg:ListGroupAndResults[ rg:Seats = 0 ] ) = 0">
																<children>
																					<text fixtext="Niet van toepassing"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<template subtype="element" match="rg:RG520">
														<children>
															<calltemplate subtype="named" match="OverviewCandidateResultU16-IJ">
																<parameters>
																	<parameter name="isSeats" value="0"/>
																</parameters>
															</calltemplate>
														</children>
														<variables/>
													</template>
													<newline break="page"/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
													<newline/>
													<text fixtext="13. Gekozen kandidaten in alfabetische volgorde">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<template subtype="element" match="rg:RG520">
														<children>
															<template subtype="element" match="rg:OverviewOfElectedCandidates">
																<children>
																	<tgrid>
																		<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
																		<styles vertical-align="top"/>
																		<children>
																			<tgridbody-cols>
																				<children>
																					<tgridcol>
																						<styles width="35%"/>
																					</tgridcol>
																					<tgridcol>
																						<styles width="10%"/>
																					</tgridcol>
																					<tgridcol>
																						<styles width="35%"/>
																					</tgridcol>
																					<tgridcol>
																						<styles width="20%"/>
																					</tgridcol>
																				</children>
																			</tgridbody-cols>
																			<tgridheader-rows>
																				<children>
																					<tgridrow>
																						<children>
																							<tgridcell>
																								<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
																								<children>
																									<text fixtext="aanduiding politieke groepering">
																										<properties class="columnheader"/>
																									</text>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
																								<children>
																									<text fixtext="lijst">
																										<properties class="columnheader"/>
																									</text>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<properties align="left"/>
																								<styles border-bottom="1px solid #000" border-top="1px solid #000"/>
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
																							<tgridrow>
																								<children>
																									<tgridcell>
																										<children>
																											<template subtype="element" match="eml:AffiliationIdentifier">
																												<children>
																													<template subtype="element" match="eml:RegisteredName">
																														<children>
																															<content subtype="regular"/>
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
																											<template subtype="element" match="eml:AffiliationIdentifier">
																												<children>
																													<template subtype="attribute" match="Id">
																														<children>
																															<content subtype="regular"/>
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
																										<styles padding-top="2pt" vertical-align="top"/>
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
																									</tgridcell>
																								</children>
																							</tgridrow>
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
															<newline break="page"/>
															<calltemplate subtype="named" match="Line">
																<parameters/>
															</calltemplate>
															<calltemplate subtype="named" match="LineBreakPdf">
																<parameters/>
															</calltemplate>
															<newline/>
															<text fixtext="14. Bezwaren van aanwezige kiezers">
																<properties class="heading"/>
															</text>
															<newline/>
															<newline/>
															<text fixtext="Tijdens de zitting zijn:"/>
															<newline/>
															<newline/>
															<tgrid>
																<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
																<children>
																	<tgridbody-cols>
																		<children>
																			<tgridcol>
																				<styles width="30pt"/>
																			</tgridcol>
																			<tgridcol>
																				<styles width="15pt"/>
																			</tgridcol>
																			<tgridcol/>
																		</children>
																	</tgridbody-cols>
																	<tgridbody-rows>
																		<children>
																			<tgridrow>
																				<children>
																					<tgridcell>
																						<styles vertical-align="top"/>
																					</tgridcell>
																					<tgridcell>
																						<styles vertical-align="top"/>
																						<children>
																											<text fixtext="□ "/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<styles vertical-align="top"/>
																						<children>
																							<text fixtext="geen bezwaren ingebracht."/>
																						</children>
																					</tgridcell>
																				</children>
																			</tgridrow>
																			<tgridrow>
																				<children>
																					<tgridcell>
																						<styles vertical-align="top"/>
																					</tgridcell>
																					<tgridcell>
																						<styles vertical-align="top"/>
																						<children>
																											<text fixtext="□ "/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<styles vertical-align="top"/>
																						<children>
																							<text fixtext="bezwaren ingebracht: zie gewaarmerkte bijlage met opmerkingen van het centraal stembureau."/>
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
															<newline/>
															<newline/>
															<calltemplate subtype="named" match="Line">
																<parameters/>
															</calltemplate>
															<calltemplate subtype="named" match="LineBreakPdf">
																<parameters/>
															</calltemplate>
															<newline/>
															<text fixtext="15. Ondertekening">
																<properties class="heading"/>
															</text>
															<calltemplate subtype="named" match="LineBreakPdf">
																<parameters/>
															</calltemplate>
															<newline/>
															<tgrid>
																<properties border="0" width="100%"/>
																<children>
																	<tgridbody-cols>
																		<children>
																			<tgridcol>
																				<styles width="157.50pt"/>
																			</tgridcol>
																			<tgridcol/>
																		</children>
																	</tgridbody-cols>
																	<tgridbody-rows>
																		<children>
																			<tgridrow>
																				<children>
																					<tgridcell>
																						<children>
																							<newline/>
																							<newline/>
																							<text fixtext="Datum"/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<children>
																							<newline/>
																							<newline/>
																							<text fixtext="_________________________________________________________">
																								<properties class="underline"/>
																							</text>
																						</children>
																					</tgridcell>
																				</children>
																			</tgridrow>
																			<tgridrow>
																				<children>
																					<tgridcell>
																						<children>
																							<newline/>
																							<newline/>
																							<newline/>
																							<text fixtext="Naam en handtekening voorzitter "/>
																							<newline/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<children>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<text fixtext="_________________________________________________________">
																								<properties class="underline"/>
																							</text>
																						</children>
																					</tgridcell>
																				</children>
																			</tgridrow>
																			<tgridrow>
																				<children>
																					<tgridcell>
																						<children>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<text fixtext="Naam en handtekening leden"/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<children>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<text fixtext="_________________________________________________________">
																								<properties class="underline"/>
																							</text>
																						</children>
																					</tgridcell>
																				</children>
																			</tgridrow>
																			<tgridrow>
																				<children>
																					<tgridcell>
																						<children>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<text fixtext=" "/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<children>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<text fixtext="_________________________________________________________">
																								<properties class="underline"/>
																							</text>
																						</children>
																					</tgridcell>
																				</children>
																			</tgridrow>
																			<tgridrow>
																				<children>
																					<tgridcell>
																						<children>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<text fixtext=" "/>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<children>
																							<newline/>
																							<newline/>
																							<newline/>
																							<newline/>
																							<text fixtext="_________________________________________________________">
																								<properties class="underline"/>
																							</text>
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
															<newline/>
															<newline/>
															<newline/>
															<calltemplate subtype="named" match="Line">
																<parameters/>
															</calltemplate>
															<newline/>
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
					</paragraph>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<designfragments>
		<children>
			<globaltemplate subtype="named" match="OverviewVotesEK">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="40pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="12pt"/>
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
									<tgridcol>
										<styles width="30pt"/>
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
									<tgridcol>
										<styles width="30pt"/>
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
									<tgridcol>
										<styles width="30pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="40pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridbody-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
												<children>
													<text fixtext="lijst-">
														<properties class="columnheader"/>
													</text>
													<newline/>
													<text fixtext="nummer">
														<properties class="columnheader"/>
													</text>
													<newline/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
												<children>
													<text fixtext="A: aantal stemmen per provincie">
														<properties class="columnheader"/>
													</text>
													<newline/>
													<text fixtext="B: gelden voor">
														<properties class="columnheader"/>
													</text>
													<newline/>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" text-align="center" vertical-align="top"/>
												<children>
													<text fixtext="totaal">
														<properties class="columnheader"/>
													</text>
													<newline/>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<template subtype="element" match="rg:ElectoralDistrictsOverview">
										<children>
											<tgridrow>
												<children>
													<tgridcell>
														<properties align="left"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" line-height="66pt" vertical-align="top"/>
														<children>
															<text fixtext=" ">
																<styles font-size="smaller"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
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
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" vertical-align="top"/>
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
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top" width="13pt"/>
														<children>
															<template subtype="attribute" match="listNumber">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="NMTOKEN"/>
																	</content>
																</children>
																<variables/>
															</template>
															<newline/>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<text fixtext="A"/>
															<newline/>
															<text fixtext="B"/>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<template subtype="element" filter="position() = 1" match="rg:VotesInElectoralDistrict">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<newline/>
																	<template subtype="attribute" match="weighted">
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
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<template subtype="element" filter="position() = 2" match="rg:VotesInElectoralDistrict">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<newline/>
																	<template subtype="attribute" match="weighted">
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
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<template subtype="element" filter="position() = 3" match="rg:VotesInElectoralDistrict">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<newline/>
																	<template subtype="attribute" match="weighted">
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
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<template subtype="element" filter="position() = 4" match="rg:VotesInElectoralDistrict">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<newline/>
																	<template subtype="attribute" match="weighted">
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
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<template subtype="element" filter="position() = 5" match="rg:VotesInElectoralDistrict">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<newline/>
																	<template subtype="attribute" match="weighted">
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
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<template subtype="element" filter="position() = 6" match="rg:VotesInElectoralDistrict">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<newline/>
																	<template subtype="attribute" match="weighted">
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
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<template subtype="element" filter="position() = 7" match="rg:VotesInElectoralDistrict">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<newline/>
																	<template subtype="attribute" match="weighted">
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
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<template subtype="element" filter="position() = 8" match="rg:VotesInElectoralDistrict">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<newline/>
																	<template subtype="attribute" match="weighted">
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
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<template subtype="element" filter="position() = 9" match="rg:VotesInElectoralDistrict">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<newline/>
																	<template subtype="attribute" match="weighted">
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
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<template subtype="element" filter="position() = 10" match="rg:VotesInElectoralDistrict">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<newline/>
																	<template subtype="attribute" match="weighted">
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
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<template subtype="element" filter="position() = 11" match="rg:VotesInElectoralDistrict">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<newline/>
																	<template subtype="attribute" match="weighted">
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
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<template subtype="element" filter="position() = 12" match="rg:VotesInElectoralDistrict">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="integer"/>
																	</content>
																	<newline/>
																	<template subtype="attribute" match="weighted">
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
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<template subtype="element" filter=" @id = &quot;alle&quot; " match="rg:VotesInElectoralDistrict">
																<children>
																	<text fixtext=" "/>
																	<newline/>
																	<template subtype="attribute" match="weighted">
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
												</children>
											</tgridrow>
										</children>
										<variables/>
									</template>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
												<children>
													<text fixtext="totaal">
														<properties class="columnheader"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesPerProvince">
														<parameters>
															<parameter name="position" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesPerProvince">
														<parameters>
															<parameter name="position" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesPerProvince">
														<parameters>
															<parameter name="position" value="3"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesPerProvince">
														<parameters>
															<parameter name="position" value="4"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesPerProvince">
														<parameters>
															<parameter name="position" value="5"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesPerProvince">
														<parameters>
															<parameter name="position" value="6"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesPerProvince">
														<parameters>
															<parameter name="position" value="7"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesPerProvince">
														<parameters>
															<parameter name="position" value="8"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesPerProvince">
														<parameters>
															<parameter name="position" value="9"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesPerProvince">
														<parameters>
															<parameter name="position" value="10"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesPerProvince">
														<parameters>
															<parameter name="position" value="11"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
												<children>
													<calltemplate subtype="named" match="TotalVotesPerProvince">
														<parameters>
															<parameter name="position" value="12"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="1px solid gray" border-left="1px solid gray" border-right="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
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
					<newline/>
					<text fixtext="Totaal aantal uitgebrachte stemmen "/>
					<template subtype="element" match="rg:OverviewOfDistrictsVotes">
						<children>
							<template subtype="element" filter="@id = &quot;alle&quot;" match="rg:VotesInElectoralDistrict">
								<children>
									<template subtype="attribute" match="weighted">
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
					<newline/>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="TotalVotesPerProvince">
				<parameters>
					<parameter name="position" default-value="&apos;&apos;"/>
				</parameters>
				<children>
					<template subtype="element" match="rg:OverviewOfDistrictsVotes">
						<children>
							<template subtype="element" filter="position() = $position" match="rg:VotesInElectoralDistrict">
								<children>
									<template subtype="attribute" match="weighted">
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
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewCandidateResultU16-IJ">
				<parameters>
					<parameter name="isSeats" type="xs:int" occurrence="none-or-one" default-value="1"/>
				</parameters>
				<children>
					<template subtype="element" match="rg:OverviewOfCandidatesAndResults">
						<children>
							<template subtype="element" filter="($isSeats = 1 and rg:Seats &gt; 0) or ($isSeats = 0 and rg:Seats = 0)" match="rg:ListGroupAndResults">
								<children>
									<template subtype="element" match="rg:ListAndResults">
										<children>
											<calltemplate subtype="named" match="OverviewCandidateResultU16-IJ-Base">
												<parameters>
													<parameter name="isSeats" value="$isSeats"/>
													<parameter name="fromLine" value="1"/>
													<parameter name="tillLine" value="20"/>
													<parameter name="candidateCount" value="$candidateCount"/>
												</parameters>
											</calltemplate>
											<condition>
												<children>
													<conditionbranch xpath="$candidateCount &gt;= 21">
														<children>
															<calltemplate subtype="named" match="OverviewCandidateResultU16-IJ-Base">
																<parameters>
																	<parameter name="isSeats" value="$isSeats"/>
																	<parameter name="fromLine" value="21"/>
																	<parameter name="tillLine" value="40"/>
																	<parameter name="candidateCount" value="$candidateCount"/>
																</parameters>
															</calltemplate>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<condition>
												<children>
													<conditionbranch xpath="$candidateCount &gt;= 41">
														<children>
															<calltemplate subtype="named" match="OverviewCandidateResultU16-IJ-Base">
																<parameters>
																	<parameter name="isSeats" value="$isSeats"/>
																	<parameter name="fromLine" value="41"/>
																	<parameter name="tillLine" value="60"/>
																	<parameter name="candidateCount" value="$candidateCount"/>
																</parameters>
															</calltemplate>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<condition>
												<children>
													<conditionbranch xpath="$candidateCount &gt;= 61">
														<children>
															<calltemplate subtype="named" match="OverviewCandidateResultU16-IJ-Base">
																<parameters>
																	<parameter name="isSeats" value="$isSeats"/>
																	<parameter name="fromLine" value="61"/>
																	<parameter name="tillLine" value="80"/>
																	<parameter name="candidateCount" value="$candidateCount"/>
																</parameters>
															</calltemplate>
														</children>
													</conditionbranch>
												</children>
											</condition>
										</children>
										<variables>
											<variable name="candidateCount" value="count( rg:CandidateAndResult )" valuetype="&lt;auto&gt;"/>
										</variables>
									</template>
									<condition>
										<children>
											<conditionbranch xpath="$isSeats = 1">
												<children>
													<newline/>
													<newline break="page"/>
													<newline/>
													<calltemplate subtype="named" match="PartyHeader3Lines">
														<parameters/>
													</calltemplate>
													<newline/>
													<newline/>
													<calltemplate subtype="named" match="OverviewCandidateResultElected">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakTable">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="OverviewCandidateResultNewListOrder">
														<parameters/>
													</calltemplate>
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
			<globaltemplate subtype="named" match="OverviewCandidateResultU16-IJ-Base">
				<parameters>
					<parameter name="isSeats" type="xs:int" occurrence="none-or-one" default-value="1"/>
					<parameter name="fromLine" type="xs:int"/>
					<parameter name="tillLine" type="xs:int"/>
					<parameter name="candidateCount" type="xs:int"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="count(rg:CandidateAndResult[position() = 1]/rg:VotesInElectoralDistrictPart[position() = 1]/rg:VotesInElectoralDistrict) &lt;= 3">
								<children>
									<calltemplate subtype="named" match="PartyHeaderEvenOdd">
										<parameters>
											<parameter name="isEvenOdd" value="0"/>
											<parameter name="isSeats" value="$isSeats"/>
										</parameters>
									</calltemplate>
									<calltemplate subtype="named" match="OverviewCandidateResult-EK-1-2">
										<parameters>
											<parameter name="fromLine" value="$fromLine"/>
											<parameter name="tillLine" value="$tillLine"/>
											<parameter name="remainingCandidates" value="$candidateCount - $tillLine"/>
										</parameters>
									</calltemplate>
								</children>
							</conditionbranch>
							<conditionbranch xpath="count(rg:CandidateAndResult[position() = 1]/rg:VotesInElectoralDistrictPart) = 1">
								<children>
									<calltemplate subtype="named" match="PartyHeaderEvenOdd">
										<parameters>
											<parameter name="isEvenOdd" value="0"/>
											<parameter name="isSeats" value="$isSeats"/>
										</parameters>
									</calltemplate>
									<calltemplate subtype="named" match="OverviewCandidateResult-EK-1-4">
										<parameters>
											<parameter name="fromLine" value="$fromLine"/>
											<parameter name="tillLine" value="$tillLine"/>
											<parameter name="remainingCandidates" value="$candidateCount - $tillLine"/>
										</parameters>
									</calltemplate>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<calltemplate subtype="named" match="PartyHeaderEvenOdd">
										<parameters>
											<parameter name="isEvenOdd" value="1"/>
											<parameter name="isSeats" value="$isSeats"/>
										</parameters>
									</calltemplate>
									<calltemplate subtype="named" match="OverviewCandidateResult-EK-1-5">
										<parameters>
											<parameter name="fromLine" value="$fromLine"/>
											<parameter name="tillLine" value="$tillLine"/>
											<parameter name="remainingCandidates" value="$candidateCount - $tillLine"/>
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
													<calltemplate subtype="named" match="OverviewCandidateResult-EK-6-12">
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
					<newline/>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="OverviewCandidateResult-EK-1-2">
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
										<styles width="15pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="45pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="155pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="10pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="75pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="75pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="75pt"/>
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
													<text fixtext="volgnr.">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<text fixtext="naam kandidaat">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<text fixtext="A: aantal stemmen per provincie">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="right"/>
												<children>
													<template subtype="element" filter="position() = 1" match="rg:CandidateAndResult">
														<children>
															<template subtype="element" filter="position() = 1" match="rg:VotesInElectoralDistrictPart">
																<children>
																	<template subtype="element" filter="@id = &apos;alle&apos;" match="rg:VotesInElectoralDistrict">
																		<children>
																			<text fixtext="totaal">
																				<styles font-size="smaller"/>
																			</text>
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
									<tgridrow>
										<children>
											<tgridcell>
												<styles text-align="left"/>
												<children>
													<text fixtext="op de lijst">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell/>
											<tgridcell>
												<styles text-align="left"/>
												<children>
													<text fixtext="B: gelden voor">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<styles text-align="right"/>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<styles border-bottom="!1px solid #000" text-align="left"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-bottom="!1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="!1px solid #000" line-height="66pt"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="!1px solid #000"/>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" text-align="left" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" text-align="left" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="!1px solid #000" text-align="right" vertical-align="top"/>
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
														<styles border-bottom="1px solid gray" border-top="1px solid gray" text-align="left" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="CandidateId">
																<parameters/>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" padding-left="5pt" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="CandidateInTable">
																<parameters>
																	<parameter name="PublishGender" value="../kr:ListData/@PublishGender"/>
																	<parameter name="PublicationLanguage" value="../kr:ListData/@PublicationLanguage"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-bottom="1px solid gray" border-top="1px solid gray"/>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-top="1px solid gray"/>
														<children>
															<text fixtext="A">
																<styles font-size="smaller"/>
															</text>
															<newline/>
															<text fixtext="B">
																<styles font-size="smaller"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="1"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="2"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="3"/>
																	<parameter name="alle" value="1"/>
																	<parameter name="weighted" value="1"/>
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
														<styles border-bottom="1px solid #000" border-top="!1px solid #000"/>
														<children>
															<text fixtext="totaal">
																<styles font-size="smaller"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-top="!1px solid #000"/>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-top="!1px solid #000"/>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000"/>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="1"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="2"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="3"/>
																	<parameter name="alle" value="1"/>
																	<parameter name="weighted" value="1"/>
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
			<globaltemplate subtype="named" match="OverviewCandidateResult-EK-1-4">
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
										<styles width="15pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="45pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="155pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="10pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="45pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="45pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="45pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="45pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="45pt"/>
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
													<text fixtext="volgnr.">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<text fixtext="naam kandidaat">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="count ( rg:Type[ text() = &quot;op zichzelf staande lijst&quot; ] ) = 0">
																<children>
																	<text fixtext="A: aantal stemmen per provincie">
																		<styles font-size="smaller"/>
																	</text>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-top="1px solid #000" text-align="right"/>
												<children>
													<text fixtext="totaal">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<styles height="0.19in"/>
										<children>
											<tgridcell>
												<styles text-align="left"/>
												<children>
													<text fixtext="op de lijst">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<styles text-align="left"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="count ( rg:Type[ text() = &quot;op zichzelf staande lijst&quot; ] ) = 0 and count(rg:CandidateAndResult[position() = 1]/rg:VotesInElectoralDistrictPart[position() = 2]/rg:VotesInElectoralDistrict) &gt; 1">
																<children>
																	<text fixtext="B: gelden voor">
																		<styles font-size="smaller"/>
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
											<tgridcell joinleft="1">
												<styles text-align="left"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles text-align="left"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles text-align="left"/>
											</tgridcell>
											<tgridcell>
												<styles text-align="right"/>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="top"/>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" line-height="66pt" vertical-align="top"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="top"/>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="!1px solid #000"/>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="3"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="4"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="!1px solid #000" vertical-align="top" width="44pt"/>
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
														<styles border-bottom="1px solid gray" border-top="1px solid gray" text-align="left" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="CandidateId">
																<parameters/>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="CandidateInTable">
																<parameters>
																	<parameter name="PublishGender" value="../kr:ListData/@PublishGender"/>
																	<parameter name="PublicationLanguage" value="../kr:ListData/@PublicationLanguage"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-bottom="1px solid gray" border-top="1px solid gray" padding-left="5pt" vertical-align="top"/>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-top="1px solid gray"/>
														<children>
															<text fixtext="A">
																<styles font-size="smaller"/>
															</text>
															<newline/>
															<text fixtext="B">
																<styles font-size="smaller"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="1"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="2"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="3"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" text-align="right" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="4"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" text-align="right"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="5"/>
																	<parameter name="alle" value="1"/>
																	<parameter name="weighted" value="1"/>
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
														<styles border-bottom="1px solid #000" border-top="!1px solid #000"/>
														<children>
															<text fixtext="totaal">
																<styles font-size="smaller"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-top="!1px solid #000"/>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-top="!1px solid #000"/>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000"/>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="1"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="2"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="3"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" text-align="right" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="4"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" text-align="right" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="5"/>
																	<parameter name="alle" value="1"/>
																	<parameter name="weighted" value="1"/>
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
			<globaltemplate subtype="named" match="OverviewCandidateResult-EK-1-5">
				<parameters>
					<parameter name="fromLine" type="xs:int" occurrence="none-or-one" default-value="1"/>
					<parameter name="tillLine" type="xs:int" occurrence="none-or-one" default-value="80"/>
					<parameter name="remainingCandidates" type="xs:int"/>
				</parameters>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles vertical-align="top"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="15pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="45pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="155pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="10pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="45pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="45pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="45pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="45pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="45pt"/>
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
													<text fixtext="volgnr.">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<text fixtext="naam kandidaat">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="count ( rg:Type[ text() = &quot;op zichzelf staande lijst&quot; ] ) = 0">
																<children>
																	<text fixtext="A: aantal stemmen per provincie">
																		<styles font-size="smaller"/>
																	</text>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles border-top="1px solid #000" text-align="right"/>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<styles text-align="left"/>
												<children>
													<text fixtext="op de lijst">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
											<tgridcell joinleft="1"/>
											<tgridcell>
												<styles text-align="left"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="count ( rg:Type[ text() = &quot;op zichzelf staande lijst&quot; ] ) = 0 and count(rg:CandidateAndResult[position() = 1]/rg:VotesInElectoralDistrictPart[position() = 2]/rg:VotesInElectoralDistrict) &gt; 1">
																<children>
																	<text fixtext="B: gelden voor">
																		<styles font-size="smaller"/>
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
											<tgridcell joinleft="1">
												<styles text-align="right"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles text-align="right"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles text-align="right"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles text-align="right"/>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="!1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" line-height="66pt"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-bottom="!1px solid #000"/>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="!1px solid #000"/>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="3"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="4"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part"/>
															<parameter name="position" value="5"/>
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
														<styles border-bottom="1px solid gray" border-top="1px solid gray" text-align="left" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="CandidateId">
																<parameters/>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="CandidateInTable">
																<parameters>
																	<parameter name="PublishGender" value="../kr:ListData/@PublishGender"/>
																	<parameter name="PublicationLanguage" value="../kr:ListData/@PublicationLanguage"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-bottom="1px solid gray" border-top="1px solid gray" padding-left="5pt"/>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-top="1px solid gray"/>
														<children>
															<text fixtext="A">
																<styles font-size="smaller"/>
															</text>
															<newline/>
															<text fixtext="B">
																<styles font-size="smaller"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="1"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="2"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="3"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" text-align="right"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="4"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" text-align="right"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part"/>
																	<parameter name="position" value="5"/>
																	<parameter name="weighted" value="1"/>
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
														<styles border-bottom="1px solid #000" border-top="!1px solid #000"/>
														<children>
															<text fixtext="totaal">
																<styles font-size="smaller"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-top="!1px solid #000"/>
													</tgridcell>
													<tgridcell joinleft="1">
														<styles border-top="!1px solid #000"/>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000"/>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="1"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="2"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="3"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" text-align="right"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="4"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" text-align="right"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="5"/>
																	<parameter name="weighted" value="1"/>
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
			<globaltemplate subtype="named" match="OverviewCandidateResult-EK-6-12">
				<parameters>
					<parameter name="fromLine" type="xs:int" occurrence="none-or-one" default-value="1"/>
					<parameter name="tillLine" type="xs:int" occurrence="none-or-one" default-value="80"/>
					<parameter name="remainingCandidates" type="xs:int"/>
				</parameters>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<styles vertical-align="top"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="26pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="10pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="51pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="51pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="51pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="51pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="51pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="51pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="51pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="51pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridheader-rows>
								<children>
									<tgridrow>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000" width="40pt"/>
												<children>
													<text fixtext="volgnr.">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="count ( rg:Type[ text() = &quot;op zichzelf staande lijst&quot; ] ) = 0 and count(rg:CandidateAndResult[position() = 1]/rg:VotesInElectoralDistrictPart[position() = 2]/rg:VotesInElectoralDistrict) &gt; 1">
																<children>
																	<text fixtext="A: aantal stemmen per provincie">
																		<styles font-size="smaller"/>
																	</text>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell joinleft="1">
												<properties align="left"/>
												<styles border-top="1px solid #000"/>
											</tgridcell>
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
													<text fixtext="Totaal">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<styles text-align="left" width="40pt"/>
												<children>
													<text fixtext="op de ">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<styles text-align="left"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="count ( rg:Type[ text() = &quot;op zichzelf staande lijst&quot; ] ) = 0 and count(rg:CandidateAndResult[position() = 1]/rg:VotesInElectoralDistrictPart[position() = 2]/rg:VotesInElectoralDistrict) &gt; 1">
																<children>
																	<text fixtext="B: gelden voor">
																		<styles font-size="smaller"/>
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
											<tgridcell joinleft="1">
												<styles text-align="right"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles text-align="right"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles text-align="right"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles text-align="right"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles text-align="right"/>
											</tgridcell>
											<tgridcell joinleft="1">
												<styles text-align="right"/>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles vertical-align="bottom"/>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<styles border-bottom="!1px solid #000" text-align="left" vertical-align="top" width="40pt"/>
												<children>
													<text fixtext="lijst">
														<styles font-size="smaller"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<styles border-bottom="!1px solid #000" line-height="66pt"/>
												<children>
													<text fixtext=" "/>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" text-align="left" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="2"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="3"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="4"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="5"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="6"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell rotation="vertical-left">
												<properties align="left"/>
												<styles border-bottom="!1px solid #000" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="ProvinceNameByPosition">
														<parameters>
															<parameter name="part" value="2"/>
															<parameter name="position" value="7"/>
														</parameters>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles border-bottom="!1px solid #000" vertical-align="top"/>
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
														<styles border-bottom="1px solid gray" border-top="1px solid gray" text-align="left" vertical-align="top" width="40pt"/>
														<children>
															<calltemplate subtype="named" match="CandidateId">
																<parameters/>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid gray" border-top="1px solid gray"/>
														<children>
															<text fixtext="A">
																<styles font-size="smaller"/>
															</text>
															<newline/>
															<text fixtext="B">
																<styles font-size="smaller"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="1"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="2"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="3"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="4"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="5"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="6"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="7"/>
																	<parameter name="weighted" value="1"/>
																	<parameter name="alle"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid gray" border-top="1px solid gray" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="Vote">
																<parameters>
																	<parameter name="part" value="2"/>
																	<parameter name="position" value="8"/>
																	<parameter name="alle" value="1"/>
																	<parameter name="weighted" value="1"/>
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
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" width="40pt"/>
														<children>
															<text fixtext="totaal">
																<styles font-size="smaller"/>
															</text>
														</children>
													</tgridcell>
													<tgridcell>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000"/>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="1"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="2"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="3"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="4"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="5"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="6"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="7"/>
																	<parameter name="weighted" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="right"/>
														<styles border-bottom="1px solid #000" border-top="!1px solid #000" vertical-align="top"/>
														<children>
															<calltemplate subtype="named" match="TotalVotes2">
																<parameters>
																	<parameter name="position" value="8"/>
																	<parameter name="alle" value="1"/>
																	<parameter name="weighted" value="1"/>
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
			<globaltemplate subtype="named" match="ProvinceNameByPosition">
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
															<calltemplate subtype="named" match="ProvinceNameEK">
																<parameters>
																	<parameter name="id" value="@id"/>
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
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
		</children>
	</designfragments>
	<xmltables/>
	<authentic-custom-toolbar-buttons/>
</structure>
