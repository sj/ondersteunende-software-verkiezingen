<?xml version="1.0" encoding="UTF-8"?>
<structure version="20" xsltversion="1" html-doctype="HTML4 Transitional" compatibility-view="IE9" html-outputextent="Complete" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" useimportschema="1" embed-images="1" pastemode="xml" enable-authentic-scripts="1" authentic-scripts-in-debug-mode-external="0" generated-file-location="DEFAULT">
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
			<xsdschemasource name="XML" main="1" schemafile="D:\projekte\Eml-kiesraad\EML-v5.0-os\520-extended.xsd" workingxmlfile="D:\projekte\EML-kiesraad\Examples-2.14\EML 520 Testcase-TK111.P22-1.emlpp.xml"/>
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
			<designfragment match="CandidateInTable2" spsfile="reused-parts-P22.sps" isactive="1"/>
			<designfragment match="O3-H-CandidateVotes" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="O3CandidateVotes" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewCandidateListsEK" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewCandidateListsEP" spsfile="reused-parts-P22.sps" isactive="1"/>
			<designfragment match="OverviewCandidateListsTK" spsfile="reused-parts-P22.sps" isactive="1"/>
			<designfragment match="OverviewCandidateResultP22-2" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewCandidateResultP22-2-H" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="OverviewVotesP22-2" spsfile="reused-parts-P22.sps" isactive="0"/>
			<designfragment match="CandidatesOnListTable" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="ElectionNameAcceptance" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="ElectionNameAcceptanceShort" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="FirstCandidateName" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="Gender3" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="Gender4" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="GenderP1" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="GenderRG" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="PartyNameInOmissions" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="PartyNameOrFirstCandidate" spsfile="reused-parts-simple.sps" isactive="0"/>
		</designfragments>
		<pagelayouts/>
		<xpath-functions/>
	</flags>
	<scripts>
		<script language="javascript">function doInitialize() {  markLoaded();  return false;  }  function doIntref(inVerwijzingRef, inLabelRef) {  var myWin = window;  var myLabel = &apos;label-&apos; + inLabelRef;  myWin.location.replace(&apos;#&apos; + myLabel);  }  //</script>
	</scripts>
	<script-project>
		<Project version="4" app="AuthenticView"/>
	</script-project>
	<importedxslt>
		<file url="D:\projekte\de.ivu.wahl.wus.reportgenerator\src\main\resources\de\ivu\wahl\wus\reportgen\text\nl\reused-parts-simple-text.xslt"/>
	</importedxslt>
	<globalstyles/>
	<mainparts>
		<children>
			<globaltemplate subtype="main" match="/">
				<document-properties title="Model P 22-1"/>
				<children>
					<documentsection>
						<properties columncount="1" columngap="0.50in" headerfooterheight="variable" pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="auto" pagestart="next" paperheight="11.69in" papermarginbottom="1.2in" papermarginfooter="0.0in" papermarginheader="0.0in" papermarginleft="1.0in" papermarginright="1.0in" papermargintop="0.9in" paperwidth="8.27in" title="Model P 22-1"/>
						<children>
							<globaltemplate subtype="pagelayout" match="footerall">
								<children>
									<calltemplate subtype="named" match="LegacyFooter">
										<parameters>
											<parameter name="isHashcode"/>
											<parameter name="isPagenumber"/>
										</parameters>
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
															<template subtype="element" match="eml:ElectionIdentifier">
																<children>
																	<calltemplate subtype="named" match="Line">
																		<parameters/>
																	</calltemplate>
																	<calltemplate subtype="named" match="LineBreakPdf">
																		<parameters/>
																	</calltemplate>
																	<newline/>
																	<text fixtext="Model P 22-1">
																		<properties class="pretitle"/>
																	</text>
																	<newline/>
																	<newline/>
																	<text fixtext="Proces-verbaal van de verkiezingsuitslag van ">
																		<properties class="title"/>
																	</text>
																	<condition>
																		<children>
																			<conditionbranch xpath="eml:ElectionCategory[ text() = &quot;TK&quot; ]">
																				<children>
																					<text fixtext="de Tweede Kamer">
																						<properties class="title"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="eml:ElectionCategory[ text() = &quot;EP&quot; ]">
																				<children>
																					<text fixtext="het Europees Parlement">
																						<properties class="title"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="eml:ElectionCategory[ text() = &quot;PS&quot; ]">
																				<children>
																					<text fixtext="provinciale staten">
																						<properties class="title"/>
																					</text>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<newline/>
																	<newline/>
																	<text fixtext="Met dit formulier doet het centraal stembureau verslag van de zitting waarin de uitslag is vastgesteld van de verkiezing voor "/>
																	<condition>
																		<children>
																			<conditionbranch xpath="eml:ElectionCategory[ text() = &quot;TK&quot; ]">
																				<children>
																					<text fixtext="de Tweede Kamer"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="eml:ElectionCategory[ text() = &quot;EP&quot; ]">
																				<children>
																					<text fixtext="het Europees Parlement"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="eml:ElectionCategory[ text() = &quot;PS&quot; ]">
																				<children>
																					<text fixtext="de provinciale staten van een provincie met meer dan één kieskring"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<text fixtext="."/>
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
															<text fixtext="1. Verkiezing">
																<properties class="heading"/>
															</text>
															<newline/>
															<newline/>
															<text fixtext="Dit proces-verbaal heeft betrekking op de verkiezing van "/>
															<calltemplate subtype="named" match="ElectionNameShort">
																<parameters>
																	<parameter name="isBold" value="1"/>
																</parameters>
															</calltemplate>
															<template subtype="element" match="eml:ElectionIdentifier">
																<children>
																	<template subtype="element" filter=".=&apos;EK&apos;" match="eml:ElectionCategory">
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
															<newline/>
															<newline/>
															<text fixtext="Datum verkiezing "/>
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
															<text fixtext="Het betreft de openbare zitting van het centraal stembureau in "/>
															<template subtype="element" match="eml:ElectionIdentifier">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="eml:ElectionCategory[ text() = &quot;TK&quot; or text() = &quot;EP&quot; ]">
																				<children>
																					<text fixtext="&apos;s-Gravenhage"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="eml:ElectionCategory[ text() = &quot;PS&quot; ]">
																				<children>
																					<template subtype="source" match="XML">
																						<children>
																							<template subtype="element" match="eml:EML">
																								<children>
																									<template subtype="element" match="eml:ManagingAuthority">
																										<children>
																											<template subtype="element" match="eml:AuthorityIdentifier">
																												<children>
																													<content subtype="regular">
																														<styles font-weight="bold"/>
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
																						<variables/>
																					</template>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
															<text fixtext="."/>
														</children>
														<variables/>
													</template>
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
															<newline break="page"/>
															<calltemplate subtype="named" match="Line">
																<parameters/>
															</calltemplate>
															<calltemplate subtype="named" match="LineBreakPdf">
																<parameters/>
															</calltemplate>
															<newline/>
															<text fixtext="3. Ingeleverde kandidatenlijsten">
																<properties class="heading"/>
															</text>
															<newline/>
															<newline/>
															<text fixtext="De volgende politieke groeperingen hebben deelgenomen aan de verkiezing (in de volgende kieskringen):"/>
															<newline/>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="//eml:ElectionCategory = &quot;TK&quot;">
																		<children>
																			<calltemplate subtype="named" match="OverviewCandidateListsTK">
																				<parameters/>
																			</calltemplate>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="//eml:ElectionCategory = &quot;PS&quot;">
																		<children>
																			<calltemplate subtype="named" match="OverviewCandidateListsPS2">
																				<parameters/>
																			</calltemplate>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="//eml:ElectionCategory = &quot;EP&quot;">
																		<children>
																			<calltemplate subtype="named" match="OverviewCandidateListsEP">
																				<parameters/>
																			</calltemplate>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="count(rg:ElectoralDistrictsOverview/rg:ElectoralDistrictName) + count(rg:OverviewOfListsAndDistricts) &gt; 32">
																		<children>
																			<newline break="page"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<newline/>
																			<newline/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<calltemplate subtype="named" match="Line">
																<parameters/>
															</calltemplate>
															<calltemplate subtype="named" match="LineBreakPdf">
																<parameters/>
															</calltemplate>
															<newline/>
															<calltemplate subtype="named" match="ElectoralDistrictsOverview">
																<parameters/>
															</calltemplate>
															<newline/>
															<newline break="page"/>
															<calltemplate subtype="named" match="Line">
																<parameters/>
															</calltemplate>
															<calltemplate subtype="named" match="LineBreakPdf">
																<parameters/>
															</calltemplate>
															<newline/>
															<text fixtext="4. Aantal stemmen per lijst">
																<properties class="heading"/>
															</text>
															<calltemplate subtype="named" match="LineBreakPdf">
																<parameters/>
															</calltemplate>
															<newline/>
															<condition>
																<children>
																	<conditionbranch xpath="//kr:ElectionSubcategory[text() = &apos;PS2&apos;]">
																		<children>
																			<newline/>
																			<condition>
																				<children>
																					<conditionbranch xpath="count(rg:OverviewOfListsAndDistrictsAndVotes[position() = 1]/rg:VotesInElectoralDistrict) = 5">
																						<children>
																							<calltemplate subtype="named" match="OverviewVotes4">
																								<parameters/>
																							</calltemplate>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="count(rg:OverviewOfListsAndDistrictsAndVotes[position() = 1]/rg:VotesInElectoralDistrict) = 4">
																						<children>
																							<calltemplate subtype="named" match="OverviewVotes3">
																								<parameters/>
																							</calltemplate>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="count(rg:OverviewOfListsAndDistrictsAndVotes[position() = 1]/rg:VotesInElectoralDistrict) = 3">
																						<children>
																							<calltemplate subtype="named" match="OverviewVotes2">
																								<parameters/>
																							</calltemplate>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<calltemplate subtype="named" match="Space">
																				<parameters/>
																			</calltemplate>
																			<newline/>
																			<calltemplate subtype="named" match="OverviewVotes1-10">
																				<parameters/>
																			</calltemplate>
																			<newline/>
																			<newline break="page"/>
																			<calltemplate subtype="named" match="Line">
																				<parameters/>
																			</calltemplate>
																			<calltemplate subtype="named" match="LineBreakPdf">
																				<parameters/>
																			</calltemplate>
																			<calltemplate subtype="named" match="Space">
																				<parameters/>
																			</calltemplate>
																			<newline/>
																			<text fixtext="4. Aantal stemmen per lijst (invisible)">
																				<properties class="heading"/>
																				<styles color="white"/>
																			</text>
																			<calltemplate subtype="named" match="LineBreakPdf">
																				<parameters/>
																			</calltemplate>
																			<newline/>
																			<calltemplate subtype="named" match="Space">
																				<parameters/>
																			</calltemplate>
																			<newline/>
																			<calltemplate subtype="named" match="OverviewVotes11-20">
																				<parameters/>
																			</calltemplate>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<newline break="page"/>
															<calltemplate subtype="named" match="Line">
																<parameters/>
															</calltemplate>
															<calltemplate subtype="named" match="LineBreakPdf">
																<parameters/>
															</calltemplate>
															<newline/>
															<text fixtext="5. Verschil tussen het aantal toegelaten kiezers en het aantal getelde stemmen">
																<properties class="heading"/>
															</text>
															<newline/>
															<newline/>
															<template subtype="element" match="rg:PresenceVotes">
																<children>
																	<newline/>
																	<paragraph>
																		<styles line-height="13.5pt"/>
																		<children>
																			<text fixtext="In de processen-verbaal is vastgesteld in hoeverre er:">
																				<styles font-style="italic"/>
																			</text>
																		</children>
																	</paragraph>
																	<paragraph>
																		<styles line-height="13.5pt"/>
																		<children>
																			<text fixtext="-	meer stembiljetten zijn geteld, dan dat er kiezers zijn toegelaten;">
																				<styles font-style="italic"/>
																			</text>
																		</children>
																	</paragraph>
																	<paragraph>
																		<styles line-height="13.5pt"/>
																		<children>
																			<text fixtext="-	minder stembiljetten zijn geteld, dan dat er kiezers zijn toegelaten.">
																				<styles font-style="italic"/>
																			</text>
																		</children>
																	</paragraph>
																	<paragraph>
																		<styles line-height="13.5pt"/>
																		<children>
																			<text fixtext="Voor beide categorieën afzonderlijk stelt u het aantal voor de verkiezing vast.">
																				<styles font-style="italic"/>
																			</text>
																		</children>
																	</paragraph>
																	<paragraph>
																		<styles line-height="13.5pt"/>
																		<children>
																			<text fixtext=" "/>
																		</children>
																	</paragraph>
																	<paragraph>
																		<styles line-height="13.5pt"/>
																		<children>
																			<text fixtext="Welke verschillen zijn in de processen-verbaal vastgesteld tussen het aantal toegelaten kiezers en het aantal getelde stembiljetten?">
																				<styles line-height="13.5pt" text-decoration="underline"/>
																			</text>
																		</children>
																	</paragraph>
																	<paragraph>
																		<styles line-height="13.5pt"/>
																		<children>
																			<text fixtext="■"/>
																			<text fixtext="   In de stembureaus zijn ">
																				<styles line-height="13.5pt"/>
																			</text>
																			<template subtype="element" match="rg:MoreValidVotesThanAdmittedVoters">
																				<children>
																					<content subtype="regular">
																						<styles font-weight="bold"/>
																						<format basic-type="xsd" datatype="nonNegativeInteger"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																			<text fixtext=" stembiljetten ">
																				<styles line-height="13.5pt"/>
																			</text>
																			<text fixtext="meer ">
																				<styles font-weight="bold" line-height="13.5pt"/>
																			</text>
																			<text fixtext="geteld, dan dat er kiezers zijn toegelaten tot de stemming.">
																				<styles line-height="13.5pt"/>
																			</text>
																		</children>
																	</paragraph>
																	<paragraph>
																		<styles line-height="13.5pt"/>
																		<children>
																			<text fixtext="■"/>
																			<text fixtext="   In de stembureaus zijn ">
																				<styles line-height="13.5pt"/>
																			</text>
																			<template subtype="element" match="rg:LessValidVotesThanAdmittedVoters">
																				<children>
																					<content subtype="regular">
																						<styles font-weight="bold"/>
																						<format basic-type="xsd" datatype="nonNegativeInteger"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																			<text fixtext=" stembiljetten ">
																				<styles line-height="13.5pt"/>
																			</text>
																			<text fixtext="minder ">
																				<styles font-weight="bold" line-height="13.5pt"/>
																			</text>
																			<text fixtext="geteld, dan dat er kiezers zijn toegelaten tot de stemming.">
																				<styles line-height="13.5pt"/>
																			</text>
																		</children>
																	</paragraph>
																	<paragraph>
																		<styles line-height="13.5pt"/>
																		<children>
																			<text fixtext=" "/>
																		</children>
																	</paragraph>
																	<paragraph>
																		<styles line-height="13.5pt"/>
																		<children>
																			<text fixtext="Hoe worden deze verschillen in de processen-verbaal verklaard?">
																				<styles line-height="13.5pt" text-decoration="underline"/>
																			</text>
																		</children>
																	</paragraph>
																	<newline/>
																	<tgrid>
																		<properties width="100%"/>
																		<children>
																			<tgridbody-cols>
																				<children>
																					<tgridcol>
																						<styles width="88%"/>
																					</tgridcol>
																					<tgridcol>
																						<styles width="12%"/>
																					</tgridcol>
																				</children>
																			</tgridbody-cols>
																			<tgridheader-rows>
																				<children>
																					<tgridrow>
																						<children>
																							<tgridcell>
																								<properties align="left"/>
																								<styles background="#e8e8e8" border-bottom="1pt solid #000" padding-left="0" vertical-align="top"/>
																								<children>
																									<text fixtext="Mogelijke verklaringen voor het verschil">
																										<styles font-weight="bold"/>
																									</text>
																									<newline/>
																									<newline/>
																									<text fixtext="Vermeld hoe vaak de volgende situaties zich hebben voorgedaan volgens de processen-verbaal. ">
																										<styles font-style="italic"/>
																									</text>
																									<newline/>
																								</children>
																							</tgridcell>
																							<tgridcell joinleft="1">
																								<styles padding-left="0" text-align="right" vertical-align="top"/>
																							</tgridcell>
																						</children>
																					</tgridrow>
																				</children>
																			</tgridheader-rows>
																			<tgridbody-rows>
																				<children>
																					<tgridrow>
																						<styles line-height="14pt"/>
																						<children>
																							<tgridcell>
																								<styles padding-left="0"/>
																								<children>
																									<text fixtext="Hoe vaak heeft een kiezer het stembiljet niet ingeleverd?"/>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<styles padding-left="0" text-align="right"/>
																								<children>
																									<template subtype="element" match="rg:BallotPaperNotReturned">
																										<children>
																											<content subtype="regular">
																												<format basic-type="xsd" datatype="nonNegativeInteger"/>
																											</content>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																						</children>
																					</tgridrow>
																					<tgridrow>
																						<styles line-height="14pt"/>
																						<children>
																							<tgridcell>
																								<styles padding-left="0"/>
																								<children>
																									<text fixtext="Hoe vaak is er een stembiljet te weinig uitgereikt? "/>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<styles padding-left="0" text-align="right"/>
																								<children>
																									<template subtype="element" match="rg:TooFewBallotPaperIssued">
																										<children>
																											<content subtype="regular">
																												<format basic-type="xsd" datatype="nonNegativeInteger"/>
																											</content>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																						</children>
																					</tgridrow>
																					<tgridrow>
																						<styles line-height="14pt"/>
																						<children>
																							<tgridcell>
																								<styles padding-left="0"/>
																								<children>
																									<text fixtext="Hoe vaak is er een stembiljet te veel uitgereikt?"/>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<styles padding-left="0" text-align="right"/>
																								<children>
																									<template subtype="element" match="rg:TooManyBallotPaperIssued">
																										<children>
																											<content subtype="regular">
																												<format basic-type="xsd" datatype="nonNegativeInteger"/>
																											</content>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																						</children>
																					</tgridrow>
																					<tgridrow conditional-processing="$includesPostalVotes">
																						<styles line-height="14pt"/>
																						<children>
																							<tgridcell>
																								<styles padding-left="0"/>
																								<children>
																									<text fixtext="Hoe vaak zat er geen stembiljet in de envelop? "/>
																									<text fixtext="(bij briefstembureaus)">
																										<styles font-style="italic"/>
																									</text>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<styles padding-left="0" text-align="right"/>
																								<children>
																									<template subtype="element" match="rg:EmptyPostalVotes">
																										<children>
																											<content subtype="regular">
																												<format basic-type="xsd" datatype="nonNegativeInteger"/>
																											</content>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																						</children>
																					</tgridrow>
																					<tgridrow conditional-processing="$includesPostalVotes">
																						<styles line-height="14pt"/>
																						<children>
																							<tgridcell>
																								<styles padding-left="0"/>
																								<children>
																									<text fixtext="Hoe vaak zaten er twee of meer stembiljetten in een envelop? "/>
																									<text fixtext="(bij briefstembureaus)">
																										<styles font-style="italic"/>
																									</text>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<styles padding-left="0" text-align="right"/>
																								<children>
																									<template subtype="element" match="rg:PostalVotesWithMultipleBallotPapers">
																										<children>
																											<content subtype="regular">
																												<format basic-type="xsd" datatype="nonNegativeInteger"/>
																											</content>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																						</children>
																					</tgridrow>
																					<tgridrow conditional-processing="$includesPostalVotes">
																						<styles line-height="14pt"/>
																						<children>
																							<tgridcell>
																								<styles padding-left="0"/>
																								<children>
																									<text fixtext="Hoe vaak zijn er stembiljetten kwijtgeraakt? "/>
																									<text fixtext="(bij briefstembureaus)">
																										<styles font-style="italic"/>
																									</text>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<styles padding-left="0" text-align="right"/>
																								<children>
																									<template subtype="element" match="rg:BallotPapersLost">
																										<children>
																											<content subtype="regular">
																												<format basic-type="xsd" datatype="nonNegativeInteger"/>
																											</content>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																						</children>
																					</tgridrow>
																					<tgridrow>
																						<styles line-height="14pt"/>
																						<children>
																							<tgridcell>
																								<styles padding-left="0"/>
																								<children>
																									<text fixtext="Hoe vaak kon er geen verklaring gegeven worden voor het verschil?"/>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<styles padding-left="0" text-align="right"/>
																								<children>
																									<template subtype="element" match="rg:NoExplanation">
																										<children>
																											<content subtype="regular">
																												<format basic-type="xsd" datatype="nonNegativeInteger"/>
																											</content>
																										</children>
																										<variables/>
																									</template>
																								</children>
																							</tgridcell>
																						</children>
																					</tgridrow>
																					<tgridrow>
																						<styles line-height="14pt"/>
																						<children>
																							<tgridcell>
																								<styles padding-left="0"/>
																								<children>
																									<text fixtext="Hoe vaak is er een andere verklaring gegeven?"/>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<styles padding-left="0" text-align="right"/>
																								<children>
																									<template subtype="element" match="rg:OtherExplanations">
																										<children>
																											<content subtype="regular">
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
																	<newline/>
																	<calltemplate subtype="named" match="Line">
																		<parameters/>
																	</calltemplate>
																	<calltemplate subtype="named" match="LineBreakPdf">
																		<parameters/>
																	</calltemplate>
																	<newline/>
																	<text fixtext="6. Aantal kiezers dat bij volmacht mocht stemmen">
																		<properties class="heading"/>
																	</text>
																	<newline/>
																	<paragraph paragraphtag="p">
																		<children>
																			<text fixtext="Het aantal kiezers dat mocht stemmen met een geldig volmachtbewijs (schriftelijk of via ingevulde stem- of kiezerspas), bedraagt "/>
																			<template subtype="element" match="rg:ProxyVoters">
																				<children>
																					<content subtype="regular">
																						<styles font-weight="bold"/>
																						<format basic-type="xsd" datatype="nonNegativeInteger"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																			<text fixtext="."/>
																		</children>
																	</paragraph>
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
															<text fixtext="7. Kiesdeler">
																<properties class="heading"/>
															</text>
															<newline/>
															<newline/>
															<calltemplate subtype="named" match="Kiesdeler">
																<parameters/>
															</calltemplate>
														</children>
														<variables/>
													</template>
													<newline/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
													<newline/>
													<text fixtext="8. Verdeling van de volle zetels">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<template subtype="element" match="rg:RG520">
														<children>
															<calltemplate subtype="named" match="OverviewFirstAssigment">
																<parameters/>
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
													<text fixtext="9. Verdeling van de restzetels">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<text fixtext="De restzetels zijn achtereenvolgens toegewezen aan de lijsten die met een zetel erbij het grootste gemiddelde aantal stemmen per zetel hebben. Bij de verkiezing van de Tweede Kamer of het Europees Parlement komt een lijst pas in aanmerking voor een restzetel als de lijst ten minste de kiesdeler heeft gehaald."/>
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
													<newline/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
													<newline/>
													<text fixtext="10. Totaal aantal zetels per politieke groepering">
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
													<condition>
														<children>
															<conditionbranch xpath="count(rg:RG520/rg:AssignmentWithinListGroups) &gt; 0">
																<children>
																	<newline break="page"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<calltemplate subtype="named" match="LineBreak">
																		<parameters/>
																	</calltemplate>
																	<calltemplate subtype="named" match="LineBreakPdf">
																		<parameters/>
																	</calltemplate>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
													<newline/>
													<text fixtext="11. Verdeling van de zetels binnen de lijstengroepen ">
														<properties class="heading"/>
													</text>
													<text fixtext="(alleen van toepassing bij de verkiezing van de Tweede Kamer of provinciale staten van een provincie met meer dan één kieskring)">
														<properties class="legend"/>
													</text>
													<newline/>
													<newline/>
													<calltemplate subtype="named" match="AssignmentWithinListGroups">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
													<newline/>
													<text fixtext="12. Stemmen per lijst met tenminste één zetel ">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<template subtype="element" match="rg:RG520">
														<children>
															<calltemplate subtype="named" match="OverviewCandidateResultP22-1-14">
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
													<text fixtext="13. Toewijzing van zetels aan kandidaten en rangschikking per lijst">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<template subtype="element" match="rg:RG520">
														<children>
															<template subtype="element" match="rg:OverviewOfCandidatesAndResults">
																<children>
																	<template subtype="element" match="rg:AnomalyInSeatDistribution">
																		<children>
																			<content subtype="regular"/>
																			<calltemplate subtype="named" match="LineBreakPdf">
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
													<text fixtext=" ">
														<properties class="heading"/>
													</text>
													<newline break="page"/>
													<newline/>
													<template subtype="element" match="rg:RG520">
														<children>
															<calltemplate subtype="named" match="OverviewCandidateResultP22-1-15">
																<parameters/>
															</calltemplate>
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
													<text fixtext="14. Stemmen per lijst zonder zetel">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="count( rg:RG520/rg:OverviewOfCandidatesAndResults/rg:ListGroupAndResults[ rg:Seats = 0 ] ) = 0">
																<children>
																	<text fixtext="Niet van toepassing."/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<template subtype="element" match="rg:RG520">
														<children>
															<calltemplate subtype="named" match="OverviewCandidateResultP22-1-14">
																<parameters>
																	<parameter name="isSeats" value="0"/>
																</parameters>
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
													<text fixtext="15. Gekozen kandidaten in alfabetische volgorde">
														<properties class="heading"/>
													</text>
													<newline/>
													<newline/>
													<template subtype="element" match="rg:RG520">
														<children>
															<calltemplate subtype="named" match="OverviewElectedCandidate">
																<parameters/>
															</calltemplate>
															<newline break="page"/>
															<newline/>
															<calltemplate subtype="named" match="Line">
																<parameters/>
															</calltemplate>
															<calltemplate subtype="named" match="LineBreakPdf">
																<parameters/>
															</calltemplate>
															<newline/>
															<text fixtext="16. Bezwaren van aanwezige kiezers">
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
															<calltemplate subtype="named" match="Line">
																<parameters/>
															</calltemplate>
															<calltemplate subtype="named" match="LineBreakPdf">
																<parameters/>
															</calltemplate>
															<newline/>
															<text fixtext="17. Ondertekening">
																<properties class="heading"/>
															</text>
															<newline/>
															<newline/>
															<text fixtext="Hierbij verklaar ik, de voorzitter van het centraal stembureau, dat de tellingen waarvan de resultaten zijn weergegeven in dit proces-verbaal, zijn uitgevoerd volgens de voorgeschreven procedures."/>
															<newline/>
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
															<calltemplate subtype="named" match="Line">
																<parameters/>
															</calltemplate>
														</children>
														<variables/>
													</template>
												</children>
												<variables>
													<variable name="includesPostalVotes" value="//eml:ElectionCategory[ text() = &quot;TK&quot; or text() = &quot;EP&quot; ]" valuetype="xs:boolean"/>
												</variables>
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
	<designfragments/>
	<xmltables/>
	<authentic-custom-toolbar-buttons/>
</structure>
