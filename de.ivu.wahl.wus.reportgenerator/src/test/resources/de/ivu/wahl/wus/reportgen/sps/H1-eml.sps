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
			<xsdschemasource name="XML" main="1" schemafile="D:\projekte\eml-kiesraad\EML-v5.0-os\210-extended.xsd" workingxmlfile="D:\projekte\EML-kiesraad\Examples-2.14\EML 210 PS2011 Venlo Hermans-rg.xml"/>
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
			<designfragment match="AffiliationVotes" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="ElectionFraction" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="ElectionFractionPart1" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="ElectionFractionPart2" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="ElectionNameAcceptance" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="ElectionNameAcceptanceShort" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="ElectionNameP2" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="ElectionNameSimple" spsfile="reused-parts-simple.sps" isactive="1"/>
			<designfragment match="Filter0" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="FirstCandidateName" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="FootnoteKieskring" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="Gender2" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="Gender3" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="Gender4" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="GenderInAffiliationVotes" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="GenderRG" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="Kieskring" spsfile="reused-parts-simple.sps" isactive="1"/>
			<designfragment match="ObjectionsByVoters1" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="ObjectionsByVoters1b" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="ObjectionsByVoters2" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="ObjectionsByVoters2a" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="PartyNameInOmissions" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="PartyNameOrFirstCandidate" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="Salutation" spsfile="reused-parts-simple.sps" isactive="1"/>
			<designfragment match="SignatureBlock" spsfile="reused-parts-simple.sps" isactive="0"/>
			<designfragment match="SignatureBlock-3" spsfile="reused-parts-simple.sps" isactive="0"/>
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
		<file url="D:\projekte\de.ivu.wahl.wus.reportgenerator\src\main\resources\de\ivu\wahl\wus\reportgen\text\nl\H1-text.xslt"/>
	</importedxslt>
	<globalstyles/>
	<mainparts>
		<children>
			<globaltemplate subtype="main" match="/">
				<document-properties/>
				<children>
					<documentsection>
						<properties columncount="1" columngap="0.50in" headerfooterheight="variable" pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="auto" pagestart="next" paperheight="11.69in" papermarginbottom="1.2in" papermarginfooter="0.0in" papermarginheader="0.0in" papermarginleft="1.0in" papermarginright="1.0in" papermargintop="0.9in" paperwidth="8.27in" title="Model H 1"/>
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
						<styles font-size="3pt"/>
						<children>
							<template subtype="source" match="XML">
								<children>
									<template subtype="element" match="eml:EML">
										<children>
											<template subtype="element" match="eml:Nomination">
												<children>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<newline/>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="Model H 1">
																		<properties class="pretitle"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_010_pretitle">
																		<properties class="pretitle"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="Kandidatenlijst">
																		<properties class="title"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_010_title">
																		<properties class="title"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="Met dit formulier stelt u, als inleveraar van de kandidatenlijst, kandidaten verkiesbaar voor een verkiezing."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_020_MetDitFormulier"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<newline/>
													<newline/>
													<newline/>
													<calltemplate subtype="named" match="ChapterElection">
														<parameters/>
													</calltemplate>
													<newline/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<newline/>
													<newline/>
													<newline/>
													<text fixtext="2. ">
														<properties class="heading"/>
													</text>
													<calltemplate subtype="named" match="ChapterDistrictsTitle">
														<parameters/>
													</calltemplate>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="eml:ElectionIdentifier/kr:ElectionSubcategory[ text() = &quot;TK&quot; or text() = &quot;EK&quot; or text() = &quot;PS2&quot; ]">
																<children>
																	<newline/>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="De kandidatenlijst wordt ingeleverd voor:"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$H1_022_kandidatenlijstWordtIngeleverd"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<newline/>
																	<newline/>
																	<condition>
																		<children>
																			<conditionbranch xpath="eml:ContestIdentifier[@Id != &quot;alle&quot;]">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext="de volgende kieskring(en): "/>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$H1_024_volgendeKieskringen"/>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																					<condition>
																						<children>
																							<conditionbranch xpath="count(eml:Affiliation/kr:ListData/kr:Contests/kr:Contest) &gt; 0">
																								<children>
																									<calltemplate subtype="named" match="Contests">
																										<parameters>
																											<parameter name="separator"/>
																											<parameter name="isBold" value="1"/>
																										</parameters>
																									</calltemplate>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<template subtype="element" match="eml:ContestIdentifier">
																										<children>
																											<template subtype="element" match="eml:ContestName">
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
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext="alle kieskringen">
																										<styles font-weight="bold"/>
																									</text>
																									<text fixtext="."/>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$H1_026_alleKieskringen">
																										<styles font-weight="bold"/>
																									</autocalc>
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
													<newline/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<newline/>
													<calltemplate subtype="named" match="ChapterPartyName">
														<parameters/>
													</calltemplate>
													<newline/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<newline/>
													<newline/>
													<newline/>
													<text fixtext="4. ">
														<properties class="heading"/>
													</text>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="Kandidaten op de lijst">
																		<properties class="heading"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_030_KandidatenOpDeLijst">
																		<properties class="heading"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<calltemplate subtype="named" match="CandidatesOnListTable">
														<parameters>
															<parameter name="showDateOfBirth" value="1"/>
														</parameters>
													</calltemplate>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<newline/>
													<newline/>
													<newline/>
													<text fixtext="5. ">
														<properties class="heading"/>
													</text>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="Vervanger(s) voor het herstel van verzuimen">
																		<properties class="heading"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_040_Vervangers">
																		<properties class="heading"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="count(eml:Nominate/eml:Proposer/eml:JobTitle[text()=&quot;plaatsvervanger van de inleveraar&quot;]) = 0">
																<children>
																	<calltemplate subtype="named" match="AgentEmpty">
																		<parameters/>
																	</calltemplate>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<template subtype="element" match="eml:Nominate">
														<children>
															<template subtype="element" filter="eml:JobTitle[text()=&quot;plaatsvervanger van de inleveraar&quot;]" match="eml:Proposer">
																<children>
																	<calltemplate subtype="named" match="Agent">
																		<parameters/>
																	</calltemplate>
																</children>
																<variables/>
															</template>
														</children>
														<variables/>
													</template>
													<newline/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<newline/>
													<newline/>
													<newline/>
													<text fixtext="6. ">
														<properties class="heading"/>
													</text>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="In te leveren bij de kandidatenlijst">
																		<properties class="heading"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_100_InTeLeveren">
																		<properties class="heading"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="Ik ben verplicht de volgende bijlage(n) in te leveren bij de kandidatenlijst (aanvinken wat van toepassing is):"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_110_IkBenVerplicht"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<template subtype="element" match="rg:RG210">
														<children>
															<tgrid>
																<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
																<children>
																	<tgridbody-cols>
																		<children>
																			<tgridcol>
																				<styles width="20pt"/>
																			</tgridcol>
																			<tgridcol/>
																		</children>
																	</tgridbody-cols>
																	<tgridbody-rows>
																		<children>
																			<tgridrow>
																				<children>
																					<tgridcell/>
																					<tgridcell>
																						<children>
																							<template subtype="element" match="rg:AuthorisationOfGroupNameAttached">
																								<children>
																									<calltemplate subtype="named" match="Checkbox">
																										<parameters/>
																									</calltemplate>
																								</children>
																								<variables/>
																							</template>
																							<condition>
																								<children>
																									<conditionbranch xpath="//eml:ElectionCategory[.!=&quot;BC&quot;]">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="$lang=0">
																														<children>
																															<text fixtext=" Een verklaring van de gemachtigde(n) van de politieke groepering(en) waarmee aan mij toestemming wordt gegeven om de aanduiding boven de kandidatenlijst te plaatsen, want ik heb een aanduiding boven de lijst geplaatst (model H 3-1 of H 3-2)."/>
																														</children>
																													</conditionbranch>
																													<conditionbranch>
																														<children>
																															<autocalc xpath="$H1_120_modelH3"/>
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
																													<conditionbranch xpath="$lang=0">
																														<children>
																															<text fixtext=" Een verklaring van de gemachtigde(n) van de (politieke) groepering(en) waarmee aan mij toestemming wordt gegeven om de aanduiding boven de kandidatenlijst te plaatsen, want ik heb een aanduiding boven de lijst geplaatst (model H 3-1 of H 3-2)."/>
																														</children>
																													</conditionbranch>
																													<conditionbranch>
																														<children>
																															<autocalc xpath="$H1_120_modelH3_BC"/>
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
																			<tgridrow>
																				<children>
																					<tgridcell/>
																					<tgridcell>
																						<children>
																							<template subtype="element" match="rg:SupportStatementsAttached">
																								<children>
																									<calltemplate subtype="named" match="Checkbox">
																										<parameters/>
																									</calltemplate>
																								</children>
																								<variables/>
																							</template>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" Verklaringen van kiezers dat zij de lijst ondersteunen, want de lijst komt niet in aanmerking voor de ontheffing van deze verplichting (model H4)."/>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$H1_130_modelH4"/>
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
																					<tgridcell/>
																					<tgridcell>
																						<children>
																							<template subtype="element" match="rg:AgreementStatementsAttached">
																								<children>
																									<calltemplate subtype="named" match="Checkbox">
																										<parameters/>
																									</calltemplate>
																								</children>
																								<variables/>
																							</template>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" Een verklaring van iedere op de lijst voorkomende kandidaat dat hij instemt met zijn kandidaatstelling op de lijst (model H 9)."/>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$H1_140_modelH9"/>
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
																					<tgridcell/>
																					<tgridcell>
																						<children>
																							<template subtype="element" match="rg:IdentificationDocumentsAttached">
																								<children>
																									<calltemplate subtype="named" match="Checkbox">
																										<parameters/>
																									</calltemplate>
																								</children>
																								<variables/>
																							</template>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" Een kopie van een geldig identiteitsbewijs van iedere kandidaat die géén zitting heeft in het orgaan waarvoor de verkiezing wordt gehouden."/>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$H1_150_modelIdentiteitsbewijs"/>
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
															<condition>
																<children>
																	<conditionbranch xpath="//eml:ElectionCategory[ text() != &quot;EK&quot; and text() != &quot;GC&quot; ]">
																		<children>
																			<tgrid>
																				<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
																				<children>
																					<tgridbody-cols>
																						<children>
																							<tgridcol>
																								<styles width="20pt"/>
																							</tgridcol>
																							<tgridcol/>
																						</children>
																					</tgridbody-cols>
																					<tgridbody-rows>
																						<children>
																							<tgridrow>
																								<children>
																									<tgridcell/>
																									<tgridcell>
																										<children>
																											<template subtype="element" match="rg:DepositReceiptAttached">
																												<children>
																													<calltemplate subtype="named" match="Checkbox">
																														<parameters/>
																													</calltemplate>
																												</children>
																												<variables/>
																											</template>
																											<condition>
																												<children>
																													<conditionbranch xpath="$lang=0">
																														<children>
																															<text fixtext=" Een betalingsbewijs van de waarborgsom, want de lijst komt niet in aanmerking voor de ontheffing van deze verplichting (model H 12)."/>
																														</children>
																													</conditionbranch>
																													<conditionbranch>
																														<children>
																															<autocalc xpath="$H1_160_modelH12"/>
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
																	</conditionbranch>
																</children>
															</condition>
															<condition>
																<children>
																	<conditionbranch xpath="//eml:ElectionCategory[ text() = &quot;PS&quot; or text() = &quot;AB&quot; or text() = &quot;GR&quot; or text() = &quot;BC&quot; or text() = &quot;ER&quot; ]">
																		<children>
																			<tgrid>
																				<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
																				<children>
																					<tgridbody-cols>
																						<children>
																							<tgridcol>
																								<styles width="20pt"/>
																							</tgridcol>
																							<tgridcol/>
																						</children>
																					</tgridbody-cols>
																					<tgridbody-rows>
																						<children>
																							<tgridrow>
																								<children>
																									<tgridcell/>
																									<tgridcell>
																										<children>
																											<template subtype="element" match="rg:PlannedSettlementStatementsAttached">
																												<children>
																													<calltemplate subtype="named" match="Checkbox">
																														<parameters/>
																													</calltemplate>
																												</children>
																												<variables/>
																											</template>
																											<condition>
																												<children>
																													<conditionbranch xpath="//eml:ElectionCategory[.!=&quot;BC&quot;]">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="$lang=0">
																																		<children>
																																			<text fixtext=" Een verklaring van voorgenomen vestiging van iedere op de lijst voorkomende kandidaat die niet woonachtig is in het gebied waarop de verkiezing betrekking heeft (alleen bij een verkiezing van provinciale staten, het algemeen bestuur van een waterschap, een gemeenteraad en de eilandsraden van de openbare lichamen Bonaire, Saba of Sint Eustatius)."/>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch>
																																		<children>
																																			<autocalc xpath="$H1_170_model_verklaring_voorgenomen_vestiging"/>
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
																																	<conditionbranch xpath="$lang=0">
																																		<children>
																																			<text fixtext=" Een verklaring van voorgenomen vestiging van iedere op de lijst voorkomende kandidaat die niet woonachtig is in het gebied waarop de verkiezing betrekking heeft (alleen bij een verkiezing van het algemeen bestuur van de bestuurscommissie, provinciale staten, het algemeen bestuur van een waterschap, een gemeenteraad en de eilandsraden van de openbare lichamen Bonaire, Saba of Sint Eustatius)."/>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch>
																																		<children>
																																			<autocalc xpath="$H1_170_model_verklaring_voorgenomen_vestiging_BC"/>
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
															<condition>
																<children>
																	<conditionbranch xpath="//eml:ElectionCategory[ text() = &quot;EP&quot; ]">
																		<children>
																			<tgrid>
																				<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
																				<children>
																					<tgridbody-cols>
																						<children>
																							<tgridcol>
																								<styles width="20pt"/>
																							</tgridcol>
																							<tgridcol/>
																						</children>
																					</tgridbody-cols>
																					<tgridbody-rows>
																						<children>
																							<tgridrow>
																								<children>
																									<tgridcell/>
																									<tgridcell>
																										<children>
																											<template subtype="element" match="rg:NoForeignNominationStatementsAttached">
																												<children>
																													<calltemplate subtype="named" match="Checkbox">
																														<parameters/>
																													</calltemplate>
																												</children>
																												<variables/>
																											</template>
																											<condition>
																												<children>
																													<conditionbranch xpath="$lang=0">
																														<children>
																															<text fixtext=" Een verklaring van iedere op de lijst voorkomende  kandidaat dat hij niet in een andere lidstaat kandidaat zal zijn voor het Europees Parlement (model Y 13)."/>
																														</children>
																													</conditionbranch>
																													<conditionbranch>
																														<children>
																															<autocalc xpath="$H1_180_modelY13"/>
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
																									<tgridcell/>
																									<tgridcell>
																										<children>
																											<template subtype="element" match="rg:NotExcludedInOtherEUCountryStatementsAttached">
																												<children>
																													<calltemplate subtype="named" match="Checkbox">
																														<parameters/>
																													</calltemplate>
																												</children>
																												<variables/>
																											</template>
																											<condition>
																												<children>
																													<conditionbranch xpath="$lang=0">
																														<children>
																															<text fixtext=" Een verklaring van kandidaten die onderdaan zijn van een andere lidstaat, dat zij in die lidstaat niet zijn uitgesloten van het recht om gekozen te worden voor de verkiezingen van het Europees Parlement (model Y 35)."/>
																														</children>
																													</conditionbranch>
																													<conditionbranch>
																														<children>
																															<autocalc xpath="$H1_190_modelY35"/>
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
																	</conditionbranch>
																</children>
															</condition>
														</children>
														<variables/>
													</template>
													<newline/>
													<calltemplate subtype="named" match="Line">
														<parameters/>
													</calltemplate>
													<newline/>
													<newline/>
													<newline/>
													<text fixtext="7. ">
														<properties class="heading"/>
													</text>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="Ondertekening door de inleveraar">
																		<properties class="heading"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_200_Ondertekening">
																		<properties class="heading"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<template subtype="element" match="eml:Nominate">
														<children>
															<template subtype="element" filter="eml:JobTitle[text()=&quot;inleveraar&quot;]" match="eml:Proposer">
																<children>
																	<newline/>
																	<tgrid>
																		<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
																		<children>
																			<tgridbody-cols>
																				<children>
																					<tgridcol>
																						<styles width="35%"/>
																					</tgridcol>
																					<tgridcol>
																						<styles width="60%"/>
																					</tgridcol>
																				</children>
																			</tgridbody-cols>
																			<tgridbody-rows>
																				<children>
																					<tgridrow>
																						<children>
																							<tgridcell>
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="$lang=0">
																												<children>
																													<text fixtext="Naam en voorletters"/>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
																												<children>
																													<autocalc xpath="$H1_205_NaamEnVoorletters"/>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<children>
																									<template subtype="element" match="eml:Name">
																										<children>
																											<calltemplate subtype="named" match="NameBCEF">
																												<parameters>
																													<parameter name="isBold" value="0"/>
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
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="$lang=0">
																												<children>
																													<text fixtext="Postadres, postcode en plaats"/>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
																												<children>
																													<autocalc xpath="$H1_210_PostadresPostcodePlaats"/>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<children>
																									<calltemplate subtype="named" match="CompleteContactAddress">
																										<parameters>
																											<parameter name="fontSize"/>
																											<parameter name="isBold" value="0"/>
																										</parameters>
																									</calltemplate>
																								</children>
																							</tgridcell>
																						</children>
																					</tgridrow>
																					<tgridrow>
																						<children>
																							<tgridcell>
																								<children>
																									<text fixtext=" "/>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<children>
																									<text fixtext=" "/>
																								</children>
																							</tgridcell>
																						</children>
																					</tgridrow>
																					<tgridrow>
																						<children>
																							<tgridcell>
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="$lang=0">
																												<children>
																													<text fixtext="Datum"/>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
																												<children>
																													<autocalc xpath="$H1_220_Datum"/>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<properties valign="bottom"/>
																								<children>
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
																									<text fixtext=" "/>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<children>
																									<text fixtext=" "/>
																								</children>
																							</tgridcell>
																						</children>
																					</tgridrow>
																					<tgridrow>
																						<children>
																							<tgridcell>
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="$lang=0">
																												<children>
																													<text fixtext="Handtekening"/>
																												</children>
																											</conditionbranch>
																											<conditionbranch>
																												<children>
																													<autocalc xpath="$H1_230_Handtekening"/>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
																								</children>
																							</tgridcell>
																							<tgridcell>
																								<properties valign="bottom"/>
																								<children>
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
					</paragraph>
					<calltemplate subtype="named" match="Line">
						<parameters/>
					</calltemplate>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<designfragments>
		<children>
			<globaltemplate subtype="named" match="Agent">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" class="table-list"/>
						<styles vertical-align="top"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="130pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="90pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="120pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridbody-rows>
								<children>
									<tgridrow>
										<properties vAlign="top"/>
										<children>
											<tgridcell>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="naam">
																		<properties class="toplabel"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_300_AgentLabel-naam">
																		<properties class="toplabel"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="voorletters">
																		<properties class="toplabel"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_310_AgentLabel-voorletters">
																		<properties class="toplabel"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell/>
										</children>
									</tgridrow>
									<tgridrow>
										<properties vAlign="top"/>
										<children>
											<tgridcell>
												<children>
													<template subtype="element" match="eml:Name">
														<children>
															<calltemplate subtype="named" match="LastNameH1">
																<parameters>
																	<parameter name="isBold"/>
																	<parameter name="fontSize" value="&apos;normal&apos;"/>
																</parameters>
															</calltemplate>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
											<tgridcell>
												<children>
													<template subtype="element" match="eml:Name">
														<children>
															<calltemplate subtype="named" match="FirstNameH1">
																<parameters>
																	<parameter name="isBold"/>
																	<parameter name="fontSize" value="&apos;normal&apos;"/>
																</parameters>
															</calltemplate>
														</children>
														<variables/>
													</template>
												</children>
											</tgridcell>
											<tgridcell/>
										</children>
									</tgridrow>
									<tgridrow>
										<properties vAlign="top"/>
										<children>
											<tgridcell>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="postadres">
																		<properties class="toplabel"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_320_AgentLabel-postadres">
																		<properties class="toplabel"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="postcode">
																		<properties class="toplabel"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_330_AgentLabel-postcode">
																		<properties class="toplabel"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="plaats">
																		<properties class="toplabel"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_340_AgentLabel-plaats">
																		<properties class="toplabel"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<properties vAlign="top"/>
										<children>
											<tgridcell>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="eml:Contact/eml:MailingAddress/xal:Locality/xal:AddressLine[ text() != &quot;&quot; ]">
																<children>
																	<template subtype="element" match="eml:Contact">
																		<children>
																			<template subtype="element" match="eml:MailingAddress">
																				<children>
																					<calltemplate subtype="named" match="Address">
																						<parameters/>
																					</calltemplate>
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
																	<calltemplate subtype="named" match="LineBreak">
																		<parameters/>
																	</calltemplate>
																	<text fixtext="_______________________________">
																		<properties class="underline"/>
																	</text>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="eml:Contact/eml:MailingAddress/xal:Locality/xal:PostalCode/xal:PostalCodeNumber[ text() != &quot;&quot; ]">
																<children>
																	<template subtype="element" match="eml:Contact">
																		<children>
																			<template subtype="element" match="eml:MailingAddress">
																				<children>
																					<calltemplate subtype="named" match="PostalCode">
																						<parameters/>
																					</calltemplate>
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
																	<calltemplate subtype="named" match="LineBreak">
																		<parameters/>
																	</calltemplate>
																	<text fixtext="_______________________________">
																		<properties class="underline"/>
																	</text>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="eml:Contact/eml:MailingAddress/xal:Locality/xal:LocalityName[ text() != &quot;&quot; ]">
																<children>
																	<template subtype="element" match="eml:Contact">
																		<children>
																			<template subtype="element" match="eml:MailingAddress">
																				<children>
																					<calltemplate subtype="named" match="City">
																						<parameters/>
																					</calltemplate>
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
																	<calltemplate subtype="named" match="LineBreak">
																		<parameters/>
																	</calltemplate>
																	<text fixtext="_______________________________">
																		<properties class="underline"/>
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
					<newline/>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="AgentEmpty">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" class="table-list"/>
						<styles vertical-align="top"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="130pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="90pt"/>
									</tgridcol>
									<tgridcol>
										<styles width="120pt"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridbody-rows>
								<children>
									<tgridrow>
										<properties vAlign="top"/>
										<children>
											<tgridcell>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="naam">
																		<properties class="toplabel"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_300_AgentLabel-naam">
																		<properties class="toplabel"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="voorletters">
																		<properties class="toplabel"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_310_AgentLabel-voorletters">
																		<properties class="toplabel"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell/>
										</children>
									</tgridrow>
									<tgridrow>
										<properties vAlign="top"/>
										<children>
											<tgridcell>
												<children>
													<calltemplate subtype="named" match="LineBreak">
														<parameters/>
													</calltemplate>
													<text fixtext="_________________________">
														<properties class="underline"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<children>
													<calltemplate subtype="named" match="LineBreak">
														<parameters/>
													</calltemplate>
													<text fixtext="_________________">
														<properties class="underline"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell/>
										</children>
									</tgridrow>
									<tgridrow>
										<properties vAlign="top"/>
										<children>
											<tgridcell>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="postadres">
																		<properties class="toplabel"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_320_AgentLabel-postadres">
																		<properties class="toplabel"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="postcode">
																		<properties class="toplabel"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_330_AgentLabel-postcode">
																		<properties class="toplabel"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="plaats">
																		<properties class="toplabel"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$H1_340_AgentLabel-plaats">
																		<properties class="toplabel"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
										</children>
									</tgridrow>
									<tgridrow>
										<properties vAlign="top"/>
										<children>
											<tgridcell>
												<children>
													<calltemplate subtype="named" match="LineBreak">
														<parameters/>
													</calltemplate>
													<text fixtext="_________________________">
														<properties class="underline"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<children>
													<calltemplate subtype="named" match="LineBreak">
														<parameters/>
													</calltemplate>
													<text fixtext="_________________">
														<properties class="underline"/>
													</text>
												</children>
											</tgridcell>
											<tgridcell>
												<children>
													<calltemplate subtype="named" match="LineBreak">
														<parameters/>
													</calltemplate>
													<text fixtext="________________________">
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
					<calltemplate subtype="named" match="LineBreakTable">
						<parameters/>
					</calltemplate>
					<newline/>
				</children>
			</globaltemplate>
		</children>
	</designfragments>
	<xmltables/>
	<authentic-custom-toolbar-buttons/>
</structure>
