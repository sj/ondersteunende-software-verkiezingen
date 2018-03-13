<?xml version="1.0" encoding="UTF-8"?>
<structure version="20" html-doctype="HTML4 Transitional" compatibility-view="IE7" html-outputextent="Complete" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" useimportschema="1" embed-images="1" pastemode="xml" enable-authentic-scripts="1" authentic-scripts-in-debug-mode-external="0" generated-file-location="DEFAULT">
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
			<xsdschemasource name="XML" main="1" schemafile="D:\projekte\eml-kiesraad\EML-v5.0-os\210-extended.xsd"/>
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
	<importedxslt>
		<file url="D:\projekte\de.ivu.wahl.wus.reportgenerator\src\main\resources\de\ivu\wahl\wus\reportgen\text\nl\reused-parts-simple-text.xslt"/>
	</importedxslt>
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
			<globaltemplate subtype="named" match="ElectionName">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Election name including date"/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="eml:ElectionIdentifier/eml:ElectionCategory[text() != &quot;NR&quot; and text() != &quot;LR&quot; and text() != &quot;PR&quot;]">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext="de verkiezing van de leden van "/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_ElectionName_verkiezingLeden"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<calltemplate subtype="named" match="ElectionNameSimple">
										<parameters>
											<parameter name="isBold" value="$isBold"/>
										</parameters>
									</calltemplate>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext="het referendum over "/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_ElectionName_referendumOver"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<template subtype="element" match="eml:ElectionIdentifier">
										<children>
											<template subtype="element" match="eml:ElectionName">
												<children>
													<content subtype="regular"/>
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
					<calltemplate subtype="named" match="ElectionDomain">
						<parameters>
							<parameter name="isBold" value="$isBold"/>
						</parameters>
					</calltemplate>
					<template subtype="element" match="eml:ElectionIdentifier">
						<children>
							<condition>
								<children>
									<conditionbranch xpath="$lang=0">
										<children>
											<text fixtext=" op "/>
										</children>
									</conditionbranch>
									<conditionbranch>
										<children>
											<autocalc xpath="$RP_ElectionName_op"/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<template subtype="element" match="kr:ElectionDate">
								<children>
									<calltemplate subtype="named" match="Date">
										<parameters>
											<parameter name="isBold" value="$isBold"/>
										</parameters>
									</calltemplate>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ElectionNameShort">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Election name excluding date and &apos;verkiezing van...&apos;"/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<calltemplate subtype="named" match="ElectionNameSimple">
						<parameters>
							<parameter name="isBold" value="$isBold"/>
						</parameters>
					</calltemplate>
					<calltemplate subtype="named" match="ElectionDomain">
						<parameters>
							<parameter name="isBold" value="$isBold"/>
						</parameters>
					</calltemplate>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ElectionNameAcceptance">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Election name for acceptance of appointment"/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<template subtype="element" match="eml:ElectionIdentifier">
						<children>
							<template subtype="element" match="eml:ElectionCategory">
								<children>
									<condition>
										<children>
											<conditionbranch xpath=".= &quot;TK&quot; or .= &quot;EP&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="de Tweede Kamer der Staten-Generaal"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameAcceptance_tweedeKamer"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;EK&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="de Eerste Kamer der Staten-Generaal"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameAcceptance_eersteKamer"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;PS&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="provinciale staten van"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameAcceptance_provincialeStaten"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;AB&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="het algemeen bestuur van het "/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameAcceptance_algemeen_bestuur"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<calltemplate subtype="named" match="WaterschapTypeName">
														<parameters>
															<parameter name="isBold" value="0"/>
														</parameters>
													</calltemplate>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;GR&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="de raad van de gemeente"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameAcceptance_raadVanDeGemeente"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;BC&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="de bestuurscommissie"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameAcceptance_bestuurscommissie"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;GC&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="de gebiedscommissie"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameAcceptance_gebiedscommissie"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;ER&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="de eilandsraad van het openbaar lichaam"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameAcceptance_eilandsraad"/>
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
			</globaltemplate>
			<globaltemplate subtype="named" match="ElectionNameAcceptanceShort">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Election name for acceptance of appointment"/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<template subtype="element" match="eml:ElectionIdentifier">
						<children>
							<template subtype="element" match="eml:ElectionCategory">
								<children>
									<condition>
										<children>
											<conditionbranch xpath=".= &quot;TK&quot; or .= &quot;EP&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="de Tweede Kamer"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameAcceptanceShort_tweedeKamer"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;EK&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="de Eerste Kamer"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameAcceptanceShort_eersteKamer"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;PS&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="provinciale staten"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameAcceptanceShort_provincialeStaten"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;AB&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="het algemeen bestuur"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameAcceptanceShort_algemeen_bestuur"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;GR&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="de raad"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameAcceptanceShort_raadVanDeGemeente"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;BC&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="de bestuurscommissie"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameAcceptanceShort_bestuurscommissie"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;GC&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="de gebiedscommissie"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameAcceptanceShort_gebiedscommissie"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;ER&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="de eilandsraad"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameAcceptanceShort_eilandsraad"/>
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
			</globaltemplate>
			<globaltemplate subtype="named" match="Salutation">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$isBold=0">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="0=1">
												<children>
													<text fixtext="Salutation &quot;Mr. &quot; or &quot;Mrs. &quot; with subsequent blank"/>
													<newline/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<template subtype="element" match="eml:Gender">
										<children>
											<condition>
												<children>
													<conditionbranch xpath=".=&quot;male&quot;">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="dhr. "/>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_Salutation_dhr"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</conditionbranch>
													<conditionbranch xpath=".=&quot;female&quot; or .=&quot;unknown&quot;">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="mevr. "/>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_Salutation_mevr"/>
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
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$isBold=1">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="0=1">
												<children>
													<text fixtext="Salutation &quot;Mr. &quot; or &quot;Mrs. &quot; with subsequent blank"/>
													<newline/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<template subtype="element" match="eml:Gender">
										<children>
											<condition>
												<children>
													<conditionbranch xpath=".=&quot;male&quot;">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="dhr. ">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_Salutation_dhr">
																				<styles font-weight="bold"/>
																			</autocalc>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</conditionbranch>
													<conditionbranch xpath=".=&quot;female&quot; or .=&quot;unknown&quot;">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="mevr. ">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_Salutation_mevr">
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
										<variables/>
									</template>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="Address">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Street and house number &quot;Dasstraat 17&quot;"/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$isBold=0">
								<children>
									<template subtype="element" match="xal:Locality">
										<children>
											<template subtype="element" match="xal:AddressLine">
												<children>
													<content subtype="regular"/>
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
					<condition>
						<children>
							<conditionbranch xpath="$isBold=1">
								<children>
									<template subtype="element" match="xal:Locality">
										<children>
											<template subtype="element" match="xal:AddressLine">
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
			</globaltemplate>
			<globaltemplate subtype="named" match="PostalCodeCity">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Postal code and city like &quot;6361 DV Nuth&quot;"/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<template subtype="element" match="xal:Locality">
						<children>
							<template subtype="element" match="xal:PostalCode">
								<children>
									<template subtype="element" match="xal:PostalCodeNumber">
										<children>
											<content subtype="regular"/>
										</children>
										<variables/>
									</template>
								</children>
								<variables/>
							</template>
							<condition>
								<children>
									<conditionbranch xpath="count(xal:PostalCode/xal:PostalCodeNumber[text() != &quot;&quot;]) &gt; 0 and count(xal:LocalityName[text() != &quot;&quot;]) &gt; 0">
										<children>
											<calltemplate subtype="named" match="Space">
												<parameters/>
											</calltemplate>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<template subtype="element" match="xal:LocalityName">
								<children>
									<content subtype="regular"/>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="PartyName">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$SV_OutputFormat = &apos;RTF&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="./eml:AffiliationIdentifier/eml:RegisteredName[text() != &quot;&quot;]">
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
															<conditionbranch>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="niet van toepassing (blanco lijst)"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_PartyName_blancoLijst"/>
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
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="./eml:AffiliationIdentifier/eml:RegisteredName[text() != &quot;&quot;]">
																<children>
																	<template subtype="element" match="eml:AffiliationIdentifier">
																		<children>
																			<template subtype="element" match="eml:RegisteredName">
																				<children>
																					<content subtype="regular">
																						<styles font-weight="bold"/>
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
															<conditionbranch>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="niet van toepassing (blanco lijst)">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_PartyName_blancoLijst">
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
								</children>
							</conditionbranch>
							<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
								<children>
									<paragraph paragraphtag="pre-wrap">
										<styles white-space="pre-wrap"/>
										<children>
											<condition>
												<children>
													<conditionbranch xpath="$isBold=0">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="./eml:AffiliationIdentifier/eml:RegisteredName[text() != &quot;&quot;]">
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
																	<conditionbranch>
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext="niet van toepassing (blanco lijst)"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_PartyName_blancoLijst"/>
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
											<condition>
												<children>
													<conditionbranch xpath="$isBold=1">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="./eml:AffiliationIdentifier/eml:RegisteredName[text() != &quot;&quot;]">
																		<children>
																			<template subtype="element" match="eml:AffiliationIdentifier">
																				<children>
																					<template subtype="element" match="eml:RegisteredName">
																						<children>
																							<content subtype="regular">
																								<styles font-weight="bold"/>
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
																	<conditionbranch>
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext="niet van toepassing (blanco lijst)">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_PartyName_blancoLijst">
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
										</children>
									</paragraph>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="LastNameH1">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="fontSize" type="xs:token" occurrence="none-or-one" default-value="&apos;normal&apos;"/>
					<parameter name="prefixToUppercase" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Displays the last name &quot;Baron van der Zaag jr.&quot; (name components a+b+c+d)."/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$fontSize=&apos;normal&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="xnl:PersonName">
														<children>
															<template subtype="element" match="xnl:NamePrefix">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$prefixToUppercase = 0">
																				<children>
																					<content subtype="regular"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="concat(translate(substring(., 1, 1), &apos;abcdefghijklmnopqrstuvwxyz&apos;, &apos;ABCDEFGHIJKLMNOPQRSTUVWXYZ&apos;), substring(., 2))"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<condition>
																		<children>
																			<conditionbranch xpath=". != &apos;&apos;">
																				<children>
																					<calltemplate subtype="named" match="Space">
																						<parameters/>
																					</calltemplate>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
															<template subtype="element" match="xnl:LastName">
																<children>
																	<content subtype="regular"/>
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
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="xnl:PersonName">
														<children>
															<template subtype="element" match="xnl:NamePrefix">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$prefixToUppercase = 0">
																				<children>
																					<content subtype="regular">
																						<styles font-weight="bold"/>
																					</content>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="concat(translate(substring(., 1, 1), &apos;abcdefghijklmnopqrstuvwxyz&apos;, &apos;ABCDEFGHIJKLMNOPQRSTUVWXYZ&apos;), substring(., 2))">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<condition>
																		<children>
																			<conditionbranch xpath=". != &apos;&apos;">
																				<children>
																					<calltemplate subtype="named" match="Space">
																						<parameters/>
																					</calltemplate>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
															<template subtype="element" match="xnl:LastName">
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
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$fontSize=&apos;smaller&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="xnl:PersonName">
														<children>
															<template subtype="element" match="xnl:NamePrefix">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$prefixToUppercase = 0">
																				<children>
																					<content subtype="regular">
																						<styles font-size="smaller"/>
																					</content>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="concat(translate(substring(., 1, 1), &apos;abcdefghijklmnopqrstuvwxyz&apos;, &apos;ABCDEFGHIJKLMNOPQRSTUVWXYZ&apos;), substring(., 2))">
																						<styles font-size="smaller"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<condition>
																		<children>
																			<conditionbranch xpath=". != &apos;&apos;">
																				<children>
																					<calltemplate subtype="named" match="Space">
																						<parameters>
																							<parameter name="fontSize" value="$fontSize"/>
																						</parameters>
																					</calltemplate>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
															<template subtype="element" match="xnl:LastName">
																<children>
																	<content subtype="regular">
																		<styles font-size="smaller"/>
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
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="xnl:PersonName">
														<children>
															<template subtype="element" match="xnl:NamePrefix">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$prefixToUppercase = 0">
																				<children>
																					<content subtype="regular">
																						<styles font-size="smaller" font-weight="bold"/>
																					</content>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="concat(translate(substring(., 1, 1), &apos;abcdefghijklmnopqrstuvwxyz&apos;, &apos;ABCDEFGHIJKLMNOPQRSTUVWXYZ&apos;), substring(., 2))">
																						<styles font-size="smaller" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<condition>
																		<children>
																			<conditionbranch xpath=". != &apos;&apos;">
																				<children>
																					<calltemplate subtype="named" match="Space">
																						<parameters>
																							<parameter name="fontSize" value="$fontSize"/>
																						</parameters>
																					</calltemplate>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
															<template subtype="element" match="xnl:LastName">
																<children>
																	<content subtype="regular">
																		<styles font-size="smaller" font-weight="bold"/>
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
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$fontSize=&apos;x-small&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="xnl:PersonName">
														<children>
															<template subtype="element" match="xnl:NamePrefix">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$prefixToUppercase = 0">
																				<children>
																					<content subtype="regular">
																						<styles font-size="x-small"/>
																					</content>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="concat(translate(substring(., 1, 1), &apos;abcdefghijklmnopqrstuvwxyz&apos;, &apos;ABCDEFGHIJKLMNOPQRSTUVWXYZ&apos;), substring(., 2))">
																						<styles font-size="x-small"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<condition>
																		<children>
																			<conditionbranch xpath=". != &apos;&apos;">
																				<children>
																					<calltemplate subtype="named" match="Space">
																						<parameters>
																							<parameter name="fontSize" value="$fontSize"/>
																						</parameters>
																					</calltemplate>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
															<template subtype="element" match="xnl:LastName">
																<children>
																	<content subtype="regular">
																		<styles font-size="x-small"/>
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
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="xnl:PersonName">
														<children>
															<template subtype="element" match="xnl:NamePrefix">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$prefixToUppercase = 0">
																				<children>
																					<content subtype="regular">
																						<styles font-size="x-small" font-weight="bold"/>
																					</content>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="concat(translate(substring(., 1, 1), &apos;abcdefghijklmnopqrstuvwxyz&apos;, &apos;ABCDEFGHIJKLMNOPQRSTUVWXYZ&apos;), substring(., 2))">
																						<styles font-size="x-small" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<condition>
																		<children>
																			<conditionbranch xpath=". != &apos;&apos;">
																				<children>
																					<calltemplate subtype="named" match="Space">
																						<parameters>
																							<parameter name="fontSize" value="$fontSize"/>
																						</parameters>
																					</calltemplate>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
															<template subtype="element" match="xnl:LastName">
																<children>
																	<content subtype="regular">
																		<styles font-size="x-small" font-weight="bold"/>
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
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="FirstNameH1">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="fontSize" type="xs:token" occurrence="none-or-one" default-value="&apos;normal&apos;"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Displays the initials and first name like &quot;H.H.G. (Henk)&quot; (name components e+f)."/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$fontSize=&apos;normal&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="xnl:PersonName">
														<children>
															<template subtype="element" match="xnl:NameLine">
																<children>
																	<content subtype="regular"/>
																</children>
																<variables/>
															</template>
															<condition>
																<children>
																	<conditionbranch xpath="count(xnl:NameLine[@NameType=&quot;Initials&quot; and text() != &quot;&quot;]) &gt; 0 and count(xnl:FirstName) &gt; 0">
																		<children>
																			<calltemplate subtype="named" match="Space">
																				<parameters>
																					<parameter name="fontSize" value="$fontSize"/>
																				</parameters>
																			</calltemplate>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template subtype="element" match="xnl:FirstName">
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
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="xnl:PersonName">
														<children>
															<template subtype="element" match="xnl:NameLine">
																<children>
																	<content subtype="regular">
																		<styles font-weight="bold"/>
																	</content>
																</children>
																<variables/>
															</template>
															<condition>
																<children>
																	<conditionbranch xpath="count(xnl:NameLine[@NameType=&quot;Initials&quot; and text() != &quot;&quot;]) &gt; 0 and count(xnl:FirstName) &gt; 0">
																		<children>
																			<calltemplate subtype="named" match="Space">
																				<parameters>
																					<parameter name="fontSize" value="$fontSize"/>
																				</parameters>
																			</calltemplate>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template subtype="element" match="xnl:FirstName">
																<children>
																	<text fixtext="(">
																		<styles font-weight="bold"/>
																	</text>
																	<content subtype="regular">
																		<styles font-weight="bold"/>
																	</content>
																	<text fixtext=")">
																		<styles font-weight="bold"/>
																	</text>
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
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$fontSize=&apos;smaller&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="xnl:PersonName">
														<children>
															<template subtype="element" match="xnl:NameLine">
																<children>
																	<content subtype="regular">
																		<styles font-size="smaller"/>
																	</content>
																</children>
																<variables/>
															</template>
															<condition>
																<children>
																	<conditionbranch xpath="count(xnl:NameLine[@NameType=&quot;Initials&quot; and text() != &quot;&quot;]) &gt; 0 and count(xnl:FirstName) &gt; 0">
																		<children>
																			<calltemplate subtype="named" match="Space">
																				<parameters>
																					<parameter name="fontSize" value="$fontSize"/>
																				</parameters>
																			</calltemplate>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template subtype="element" match="xnl:FirstName">
																<children>
																	<text fixtext="(">
																		<styles font-size="smaller"/>
																	</text>
																	<content subtype="regular">
																		<styles font-size="smaller"/>
																	</content>
																	<text fixtext=")">
																		<styles font-size="smaller"/>
																	</text>
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
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="xnl:PersonName">
														<children>
															<template subtype="element" match="xnl:NameLine">
																<children>
																	<content subtype="regular">
																		<styles font-size="smaller" font-weight="bold"/>
																	</content>
																</children>
																<variables/>
															</template>
															<condition>
																<children>
																	<conditionbranch xpath="count(xnl:NameLine[@NameType=&quot;Initials&quot; and text() != &quot;&quot;]) &gt; 0 and count(xnl:FirstName) &gt; 0">
																		<children>
																			<calltemplate subtype="named" match="Space">
																				<parameters>
																					<parameter name="fontSize" value="$fontSize"/>
																				</parameters>
																			</calltemplate>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template subtype="element" match="xnl:FirstName">
																<children>
																	<text fixtext="(">
																		<styles font-size="smaller" font-weight="bold"/>
																	</text>
																	<content subtype="regular">
																		<styles font-size="smaller" font-weight="bold"/>
																	</content>
																	<text fixtext=")">
																		<styles font-size="smaller" font-weight="bold"/>
																	</text>
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
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$fontSize=&apos;x-small&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="xnl:PersonName">
														<children>
															<template subtype="element" match="xnl:NameLine">
																<children>
																	<content subtype="regular">
																		<styles font-size="x-small"/>
																	</content>
																</children>
																<variables/>
															</template>
															<condition>
																<children>
																	<conditionbranch xpath="count(xnl:NameLine[@NameType=&quot;Initials&quot; and text() != &quot;&quot;]) &gt; 0 and count(xnl:FirstName) &gt; 0">
																		<children>
																			<calltemplate subtype="named" match="Space">
																				<parameters>
																					<parameter name="fontSize" value="$fontSize"/>
																				</parameters>
																			</calltemplate>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template subtype="element" match="xnl:FirstName">
																<children>
																	<text fixtext="(">
																		<styles font-size="x-small"/>
																	</text>
																	<content subtype="regular">
																		<styles font-size="x-small"/>
																	</content>
																	<text fixtext=")">
																		<styles font-size="x-small"/>
																	</text>
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
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="xnl:PersonName">
														<children>
															<template subtype="element" match="xnl:NameLine">
																<children>
																	<content subtype="regular">
																		<styles font-size="x-small" font-weight="bold"/>
																	</content>
																</children>
																<variables/>
															</template>
															<condition>
																<children>
																	<conditionbranch xpath="count(xnl:NameLine[@NameType=&quot;Initials&quot; and text() != &quot;&quot;]) &gt; 0 and count(xnl:FirstName) &gt; 0">
																		<children>
																			<calltemplate subtype="named" match="Space">
																				<parameters>
																					<parameter name="fontSize" value="$fontSize"/>
																				</parameters>
																			</calltemplate>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template subtype="element" match="xnl:FirstName">
																<children>
																	<text fixtext="(">
																		<styles font-size="x-small" font-weight="bold"/>
																	</text>
																	<content subtype="regular">
																		<styles font-size="x-small" font-weight="bold"/>
																	</content>
																	<text fixtext=")">
																		<styles font-size="x-small" font-weight="bold"/>
																	</text>
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
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="Gender">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="fontSize" type="xs:token" occurrence="none-or-one" default-value="&apos;normal&apos;"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Displays the gender as &quot; (m)&quot; or &quot; (v)&quot; with preceding blank (name component g), if @PublishGender = &quot;true&quot;."/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$fontSize = &apos;normal&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="../kr:ListData/@PublishGender=&quot;true&quot;">
																<children>
																	<template subtype="element" match="eml:Gender">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath=".=&quot;male&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (m)"/>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_m"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;unknown&quot; or (.=&quot;female&quot; and ../../kr:ListData/@PublicationLanguage=&quot;fy&quot;)">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (f)"/>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_unknown"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;female&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (v)"/>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_v"/>
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
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="../kr:ListData/@PublishGender=&quot;true&quot;">
																<children>
																	<template subtype="element" match="eml:Gender">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath=".=&quot;male&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (m)">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_m">
																												<styles font-weight="bold"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;unknown&quot; or (.=&quot;female&quot; and ../../kr:ListData/@PublicationLanguage=&quot;fy&quot;)">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (f)">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_unknown">
																												<styles font-weight="bold"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;female&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (v)">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_v">
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
					<condition>
						<children>
							<conditionbranch xpath="$fontSize = &apos;smaller&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="../kr:ListData/@PublishGender=&quot;true&quot;">
																<children>
																	<template subtype="element" match="eml:Gender">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath=".=&quot;male&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (m)">
																												<styles font-size="smaller"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_m">
																												<styles font-size="smaller"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;unknown&quot; or (.=&quot;female&quot; and ../../kr:ListData/@PublicationLanguage=&quot;fy&quot;)">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (f)">
																												<styles font-size="smaller"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_unknown">
																												<styles font-size="smaller"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;female&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (v)">
																												<styles font-size="smaller"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_v">
																												<styles font-size="smaller"/>
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
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="../kr:ListData/@PublishGender=&quot;true&quot;">
																<children>
																	<template subtype="element" match="eml:Gender">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath=".=&quot;male&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (m)">
																												<styles font-size="smaller" font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_m">
																												<styles font-size="smaller" font-weight="bold"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;unknown&quot; or (.=&quot;female&quot; and ../../kr:ListData/@PublicationLanguage=&quot;fy&quot;)">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (f)">
																												<styles font-size="smaller" font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_unknown">
																												<styles font-size="smaller" font-weight="bold"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;female&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (v)">
																												<styles font-size="smaller" font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_v">
																												<styles font-size="smaller" font-weight="bold"/>
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
					<condition>
						<children>
							<conditionbranch xpath="$fontSize = &apos;x-small&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="../kr:ListData/@PublishGender=&quot;true&quot;">
																<children>
																	<template subtype="element" match="eml:Gender">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath=".=&quot;male&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (m)">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_m">
																												<styles font-size="x-small"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;unknown&quot; or (.=&quot;female&quot; and ../../kr:ListData/@PublicationLanguage=&quot;fy&quot;)">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (f)">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_unknown">
																												<styles font-size="x-small"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;female&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (v)">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_v">
																												<styles font-size="x-small"/>
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
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="../kr:ListData/@PublishGender=&quot;true&quot;">
																<children>
																	<template subtype="element" match="eml:Gender">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath=".=&quot;male&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (m)">
																												<styles font-size="x-small" font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_m">
																												<styles font-size="x-small" font-weight="bold"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;unknown&quot; or (.=&quot;female&quot; and ../../kr:ListData/@PublicationLanguage=&quot;fy&quot;)">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (f)">
																												<styles font-size="x-small" font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_unknown">
																												<styles font-size="x-small" font-weight="bold"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;female&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (v)">
																												<styles font-size="x-small" font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_Gender_v">
																												<styles font-size="x-small" font-weight="bold"/>
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
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="GenderP1">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="fontSize" type="xs:token" occurrence="none-or-one" default-value="&apos;normal&apos;"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Displays the gender as &quot; (m)&quot; or &quot; (v)&quot; with preceding blank (name component g), if @PublishGender = &quot;true&quot;. In Frysian, gender female or unknown is &quot;(f)&quot;."/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$fontSize = &apos;normal&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="../kr:ListData/@PublishGender=&quot;true&quot;">
																<children>
																	<template subtype="element" match="eml:Gender">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath=".=&quot;male&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (m)"/>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_m"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;female&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (v)"/>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_v"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;unknown&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (f)"/>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_unknown"/>
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
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="../kr:ListData/@PublishGender=&quot;true&quot;">
																<children>
																	<template subtype="element" match="eml:Gender">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath=".=&quot;male&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (m)">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_m">
																												<styles font-weight="bold"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;female&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (v)">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_v">
																												<styles font-weight="bold"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;unknown&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (f)">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_unknown">
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
					<condition>
						<children>
							<conditionbranch xpath="$fontSize = &apos;smaller&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="../kr:ListData/@PublishGender=&quot;true&quot;">
																<children>
																	<template subtype="element" match="eml:Gender">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath=".=&quot;male&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (m)">
																												<styles font-size="smaller"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_m">
																												<styles font-size="smaller"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;female&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (v)">
																												<styles font-size="smaller"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_v">
																												<styles font-size="smaller"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;unknown&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (f)">
																												<styles font-size="smaller"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_unknown">
																												<styles font-size="smaller"/>
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
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="../kr:ListData/@PublishGender=&quot;true&quot;">
																<children>
																	<template subtype="element" match="eml:Gender">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath=".=&quot;male&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (m)">
																												<styles font-size="smaller" font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_m">
																												<styles font-size="smaller" font-weight="bold"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;female&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (v)">
																												<styles font-size="smaller" font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_v">
																												<styles font-size="smaller" font-weight="bold"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;unknown&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (f)">
																												<styles font-size="smaller" font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_unknown">
																												<styles font-size="smaller" font-weight="bold"/>
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
					<condition>
						<children>
							<conditionbranch xpath="$fontSize = &apos;x-small&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="../kr:ListData/@PublishGender=&quot;true&quot;">
																<children>
																	<template subtype="element" match="eml:Gender">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath=".=&quot;male&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (m)">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_m">
																												<styles font-size="x-small"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;female&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (v)">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_v">
																												<styles font-size="x-small"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;unknown&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (f)">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_unknown">
																												<styles font-size="x-small"/>
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
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="../kr:ListData/@PublishGender=&quot;true&quot;">
																<children>
																	<template subtype="element" match="eml:Gender">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath=".=&quot;male&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (m)">
																												<styles font-size="x-small" font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_m">
																												<styles font-size="x-small" font-weight="bold"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;female&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (v)">
																												<styles font-size="x-small" font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_v">
																												<styles font-size="x-small" font-weight="bold"/>
																											</autocalc>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath=".=&quot;unknown&quot;">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="$lang=0">
																										<children>
																											<text fixtext=" (f)">
																												<styles font-size="x-small" font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch>
																										<children>
																											<autocalc xpath="$RP_GenderP1_unknown">
																												<styles font-size="x-small" font-weight="bold"/>
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
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="GenderGeneral">
				<parameters>
					<parameter name="Gender" type="xs:string"/>
					<parameter name="PublishGender" type="xs:string" occurrence="none-or-one" default-value="&quot;true&quot;"/>
					<parameter name="PublicationLanguage" type="xs:string" occurrence="none-or-one" default-value="&quot;nl&quot;"/>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="fontSize" type="xs:token" occurrence="none-or-one" default-value="&apos;normal&apos;"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Displays the gender as &quot; (m)&quot; or &quot; (v)&quot; or &quot; (f)&quot; with preceding blank (name component g) or not at all. Does not read from XML but from parameters."/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="*** $Gender = &quot;"/>
									<autocalc xpath="$Gender"/>
									<text fixtext="&quot;, $PublicationLanguage = &quot;"/>
									<autocalc xpath="$PublicationLanguage"/>
									<text fixtext="&quot;, $PublishGender = &quot;"/>
									<autocalc xpath="$PublishGender"/>
									<text fixtext="&quot;, fontSize = &quot;"/>
									<autocalc xpath="$fontSize"/>
									<text fixtext="&quot;, isBold = &quot;"/>
									<autocalc xpath="$isBold"/>
									<text fixtext="&quot; ***"/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$Gender != &quot;&quot; and $PublishGender = &quot;true&quot;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$fontSize = &apos;normal&apos;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$isBold=0">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$Gender = &quot;male&quot;">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (m)"/>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_m"/>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="$Gender=&apos;unknown&apos; or ($Gender=&apos;female&apos; and $PublicationLanguage = &apos;fy&apos;)">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (f)"/>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_unknown"/>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="$Gender=&quot;female&quot;">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (v)"/>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_v"/>
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
													<condition>
														<children>
															<conditionbranch xpath="$isBold=1">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$Gender=&quot;male&quot;">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (m)">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_m">
																										<styles font-weight="bold"/>
																									</autocalc>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="$Gender=&quot;unknown&quot; or ($Gender=&quot;female&quot; and $PublicationLanguage = &quot;fy&quot;)">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (f)">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_unknown">
																										<styles font-weight="bold"/>
																									</autocalc>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="$Gender=&quot;female&quot;">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (v)">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_v">
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
												</children>
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$fontSize = &apos;smaller&apos;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$isBold=0">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$Gender=&quot;male&quot;">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (m)">
																										<styles font-size="smaller"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_m">
																										<styles font-size="smaller"/>
																									</autocalc>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="$Gender=&quot;unknown&quot; or ($Gender=&quot;female&quot; and $PublicationLanguage = &quot;fy&quot;)">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (f)">
																										<styles font-size="smaller"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_unknown">
																										<styles font-size="smaller"/>
																									</autocalc>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="$Gender=&quot;female&quot;">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (v)">
																										<styles font-size="smaller"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_v">
																										<styles font-size="smaller"/>
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
													<condition>
														<children>
															<conditionbranch xpath="$isBold=1">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$Gender=&quot;male&quot;">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (m)">
																										<styles font-size="smaller" font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_m">
																										<styles font-size="smaller" font-weight="bold"/>
																									</autocalc>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="$Gender=&quot;unknown&quot; or ($Gender=&quot;female&quot; and $PublicationLanguage = &quot;fy&quot;)">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (f)">
																										<styles font-size="smaller" font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_unknown">
																										<styles font-size="smaller" font-weight="bold"/>
																									</autocalc>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="$Gender=&quot;female&quot;">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (v)">
																										<styles font-size="smaller" font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_v">
																										<styles font-size="smaller" font-weight="bold"/>
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
												</children>
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$fontSize = &apos;x-small&apos;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$isBold=0">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$Gender=&quot;male&quot;">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (m)">
																										<styles font-size="x-small"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_m">
																										<styles font-size="x-small"/>
																									</autocalc>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="$Gender=&quot;unknown&quot; or ($Gender=&quot;female&quot; and $PublicationLanguage = &quot;fy&quot;)">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (f)">
																										<styles font-size="x-small"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_unknown">
																										<styles font-size="x-small"/>
																									</autocalc>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="$Gender=&quot;female&quot;">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (v)">
																										<styles font-size="x-small"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_v">
																										<styles font-size="x-small"/>
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
													<condition>
														<children>
															<conditionbranch xpath="$isBold=1">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$Gender=&quot;male&quot;">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (m)">
																										<styles font-size="x-small" font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_m">
																										<styles font-size="x-small" font-weight="bold"/>
																									</autocalc>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="$Gender=&quot;unknown&quot; or ($Gender=&quot;female&quot; and $PublicationLanguage = &quot;fy&quot;)">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (f)">
																										<styles font-size="x-small" font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_unknown">
																										<styles font-size="x-small" font-weight="bold"/>
																									</autocalc>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="$Gender=&quot;female&quot;">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext=" (v)">
																										<styles font-size="x-small" font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_Gender_v">
																										<styles font-size="x-small" font-weight="bold"/>
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
			<globaltemplate subtype="named" match="CompleteAddress">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="fontSize" type="xs:token" occurrence="none-or-one" default-value="&apos;normal&apos;"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Complete adress like &quot;Dasstraat 17, 6361 DV Nuth&quot;"/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$fontSize = &apos;normal&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="xal:Locality">
														<children>
															<template subtype="element" match="xal:AddressLine">
																<children>
																	<content subtype="regular"/>
																	<text fixtext=", "/>
																</children>
																<variables/>
															</template>
															<template subtype="element" match="xal:PostalCode">
																<children>
																	<content subtype="regular"/>
																	<condition>
																		<children>
																			<conditionbranch xpath=".!=&quot;&quot;">
																				<children>
																					<text fixtext=" "/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
															<template subtype="element" match="xal:LocalityName">
																<children>
																	<content subtype="regular"/>
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
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="xal:Locality">
														<children>
															<template subtype="element" match="xal:AddressLine">
																<children>
																	<content subtype="regular">
																		<styles font-weight="bold"/>
																	</content>
																	<text fixtext=", ">
																		<styles font-weight="bold"/>
																	</text>
																</children>
																<variables/>
															</template>
															<template subtype="element" match="xal:PostalCode">
																<children>
																	<content subtype="regular">
																		<styles font-weight="bold"/>
																	</content>
																	<condition>
																		<children>
																			<conditionbranch xpath=".!=&quot;&quot;">
																				<children>
																					<text fixtext=" ">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
															<template subtype="element" match="xal:LocalityName">
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
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$fontSize = &apos;x-small&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="xal:Locality">
														<children>
															<template subtype="element" match="xal:AddressLine">
																<children>
																	<content subtype="regular">
																		<styles font-size="x-small"/>
																	</content>
																	<text fixtext=", ">
																		<styles font-size="x-small"/>
																	</text>
																</children>
																<variables/>
															</template>
															<template subtype="element" match="xal:PostalCode">
																<children>
																	<content subtype="regular">
																		<styles font-size="x-small"/>
																	</content>
																	<condition>
																		<children>
																			<conditionbranch xpath=".!=&quot;&quot;">
																				<children>
																					<text fixtext=" ">
																						<styles font-size="x-small"/>
																					</text>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
															<template subtype="element" match="xal:LocalityName">
																<children>
																	<content subtype="regular">
																		<styles font-size="x-small"/>
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
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="xal:Locality">
														<children>
															<template subtype="element" match="xal:AddressLine">
																<children>
																	<content subtype="regular">
																		<styles font-size="x-small" font-weight="bold"/>
																	</content>
																	<text fixtext=", ">
																		<styles font-size="x-small" font-weight="bold"/>
																	</text>
																</children>
																<variables/>
															</template>
															<template subtype="element" match="xal:PostalCode">
																<children>
																	<content subtype="regular">
																		<styles font-size="x-small" font-weight="bold"/>
																	</content>
																	<condition>
																		<children>
																			<conditionbranch xpath=".!=&quot;&quot;">
																				<children>
																					<text fixtext=" ">
																						<styles font-size="x-small" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
																<variables/>
															</template>
															<template subtype="element" match="xal:LocalityName">
																<children>
																	<content subtype="regular">
																		<styles font-size="x-small" font-weight="bold"/>
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
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="NameBCEF">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="prefixToUppercase" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Displays the name &quot;van der Zaag, H.H.G. (Henk)&quot; (name components b+c+e+f)."/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<calltemplate subtype="named" match="LastNameH1">
						<parameters>
							<parameter name="isBold" value="$isBold"/>
							<parameter name="fontSize"/>
							<parameter name="prefixToUppercase" value="$prefixToUppercase"/>
						</parameters>
					</calltemplate>
					<text fixtext=", "/>
					<calltemplate subtype="named" match="FirstNameH1">
						<parameters>
							<parameter name="isBold" value="$isBold"/>
						</parameters>
					</calltemplate>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="Kieskring">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="./eml:ElectionIdentifier/kr:ElectionSubcategory[text()=&quot;TK&quot; or text()=&quot;EK&quot; or text()=&quot;PS2&quot;]  or ../eml:ElectionIdentifier/kr:ElectionSubcategory[text()=&quot;TK&quot; or text()=&quot;EK&quot; or text()=&quot;PS2&quot;] or ../eml:Election/eml:ElectionIdentifier/kr:ElectionSubcategory[text()=&quot;TK&quot; or text()=&quot;EK&quot; or text()=&quot;PS2&quot;]">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext=" in kieskring "/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_Kieskring_inKieskring"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="eml:ContestIdentifier/@Id != &quot;alle&quot;">
												<children>
													<calltemplate subtype="named" match="ContestIdentifier">
														<parameters>
															<parameter name="isBold" value="1"/>
														</parameters>
													</calltemplate>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<template subtype="element" match="rg:RG210">
														<children>
															<calltemplate subtype="named" match="ContestIdentifier">
																<parameters>
																	<parameter name="isBold" value="1"/>
																</parameters>
															</calltemplate>
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
			</globaltemplate>
			<globaltemplate subtype="named" match="City">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="fontSize" type="xs:token" occurrence="none-or-one" default-value="&apos;normal&apos;"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="City (without postal code) &quot;DV Nuth&quot;"/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$fontSize = &apos;normal&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="xal:Locality">
														<children>
															<template subtype="element" match="xal:LocalityName">
																<children>
																	<content subtype="regular"/>
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
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="xal:Locality">
														<children>
															<template subtype="element" match="xal:LocalityName">
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
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$fontSize = &apos;x-small&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="xal:Locality">
														<children>
															<template subtype="element" match="xal:LocalityName">
																<children>
																	<content subtype="regular">
																		<styles font-size="x-small"/>
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
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="xal:Locality">
														<children>
															<template subtype="element" match="xal:LocalityName">
																<children>
																	<content subtype="regular">
																		<styles font-size="x-small" font-weight="bold"/>
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
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="FootnoteSign">
				<parameters/>
				<children>
					<template subtype="element" match="eml:ElectionIdentifier">
						<children>
							<template subtype="element" match="kr:ElectionSubcategory">
								<children>
									<condition>
										<children>
											<conditionbranch xpath=".=&quot;TK&quot; or .=&quot;EK&quot; or .=&quot;PS2&quot;">
												<children>
													<text fixtext=" *)"/>
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
			<globaltemplate subtype="named" match="FootnoteKieskring">
				<parameters/>
				<children>
					<template subtype="element" match="eml:ElectionIdentifier">
						<children>
							<template subtype="element" match="kr:ElectionSubcategory">
								<children>
									<condition>
										<children>
											<conditionbranch xpath=".=&quot;TK&quot; or .=&quot;EK&quot; or .=&quot;PS2&quot;">
												<children>
													<paragraph paragraphtag="p">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="Voetnoten:">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_FootnoteKieskring_voetnoten">
																				<styles font-weight="bold"/>
																			</autocalc>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</paragraph>
													<tgrid>
														<properties cellPadding="0" cellSpacing="0" class="footnotes"/>
														<styles vertical-align="top" width="90%"/>
														<children>
															<tgridbody-cols>
																<children>
																	<tgridcol/>
																	<tgridcol>
																		<styles width="100%"/>
																	</tgridcol>
																</children>
															</tgridbody-cols>
															<tgridbody-rows>
																<children>
																	<tgridrow>
																		<children>
																			<tgridcell>
																				<properties class="footnotesnr" noWrap="65535" vAlign="top"/>
																				<styles vertical-align="top" width="3%"/>
																				<children>
																					<text fixtext="*)"/>
																				</children>
																			</tgridcell>
																			<tgridcell>
																				<properties class="footnotescontent" vAlign="top"/>
																				<styles vertical-align="top"/>
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$lang=0">
																								<children>
																									<text fixtext="Alleen in te vullen, indien het een verkiezing betreft van de leden van de Tweede Kamer of van provinciale staten van een provincie die uit meer dan één kieskring bestaat. Achter “kieskring” het nummer van de kieskring en tussen haakjes de naam van de gemeente waar het hoofdstembureau is gevestigd vermelden."/>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<autocalc xpath="$RP_FootnoteKieskring_alleenInTeVullen"/>
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
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="Date">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="fontSize" type="xs:token" occurrence="none-or-one" default-value="&apos;normal&apos;"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$fontSize = &apos;normal&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="substring(text(),9, 1) != &quot;0&quot;">
																<children>
																	<autocalc xpath="substring(text(),9, 1)"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<autocalc xpath="substring(text(),10, 1)"/>
													<text fixtext=" "/>
													<condition>
														<children>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;01&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="januari"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_januari"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;02&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="februari"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_februari"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;03&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="maart"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_maart"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;04&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="april"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_april"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;05&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="mei"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_mei"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;06&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="juni"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_juni"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;07&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="juli"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_juli"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;08&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="augustus"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_augustus"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;09&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="september"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_september"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;10&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="oktober"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_oktober"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;11&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="november"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_november"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2)  = &quot;12&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="december"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_december"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<text fixtext=" "/>
													<autocalc xpath="substring(text(),1, 4)"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="substring(text(),9, 1) != &quot;0&quot;">
																<children>
																	<autocalc xpath="substring(text(),9, 1)">
																		<styles font-weight="bold"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<autocalc xpath="substring(text(),10, 1)">
														<styles font-weight="bold"/>
													</autocalc>
													<text fixtext=" ">
														<styles font-weight="bold"/>
													</text>
													<condition>
														<children>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;01&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="januari">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_januari">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;02&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="februari">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_februari">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;03&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="maart">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_maart">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;04&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="april">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_april">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;05&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="mei">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_mei">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;06&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="juni">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_juni">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;07&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="juli">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_juli">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;08&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="augustus">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_augustus">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;09&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="september">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_september">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;10&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="oktober">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_oktober">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;11&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="november">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_november">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2)  = &quot;12&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="december">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_december">
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
													<text fixtext=" ">
														<styles font-weight="bold"/>
													</text>
													<autocalc xpath="substring(text(),1, 4)">
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
					<condition>
						<children>
							<conditionbranch xpath="$fontSize = &apos;x-small&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="substring(text(),9, 1) != &quot;0&quot;">
																<children>
																	<autocalc xpath="substring(text(),9, 1)">
																		<styles font-size="x-small"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<autocalc xpath="substring(text(),10, 1)">
														<styles font-size="x-small"/>
													</autocalc>
													<text fixtext=" ">
														<styles font-size="x-small"/>
													</text>
													<condition>
														<children>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;01&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="januari">
																						<styles font-size="x-small"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_januari">
																						<styles font-size="x-small"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;02&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="februari">
																						<styles font-size="x-small"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_februari">
																						<styles font-size="x-small"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;03&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="maart">
																						<styles font-size="x-small"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_maart">
																						<styles font-size="x-small"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;04&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="april">
																						<styles font-size="x-small"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_april">
																						<styles font-size="x-small"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;05&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="mei">
																						<styles font-size="x-small"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_mei">
																						<styles font-size="x-small"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;06&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="juni">
																						<styles font-size="x-small"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_juni">
																						<styles font-size="x-small"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;07&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="juli">
																						<styles font-size="x-small"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_juli">
																						<styles font-size="x-small"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;08&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="augustus">
																						<styles font-size="x-small"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_augustus">
																						<styles font-size="x-small"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;09&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="september">
																						<styles font-size="x-small"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_september">
																						<styles font-size="x-small"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;10&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="oktober">
																						<styles font-size="x-small"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_oktober">
																						<styles font-size="x-small"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;11&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="november">
																						<styles font-size="x-small"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_november">
																						<styles font-size="x-small"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2)  = &quot;12&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="december">
																						<styles font-size="x-small"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_december">
																						<styles font-size="x-small"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<text fixtext=" ">
														<styles font-size="x-small"/>
													</text>
													<autocalc xpath="substring(text(),1, 4)">
														<styles font-size="x-small"/>
													</autocalc>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="substring(text(),9, 1) != &quot;0&quot;">
																<children>
																	<autocalc xpath="substring(text(),9, 1)">
																		<styles font-size="x-small" font-weight="bold"/>
																	</autocalc>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<autocalc xpath="substring(text(),10, 1)">
														<styles font-size="x-small" font-weight="bold"/>
													</autocalc>
													<text fixtext=" ">
														<styles font-size="x-small" font-weight="bold"/>
													</text>
													<condition>
														<children>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;01&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="januari">
																						<styles font-size="x-small" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_januari">
																						<styles font-size="x-small" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;02&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="februari">
																						<styles font-size="x-small" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_februari">
																						<styles font-size="x-small" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;03&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="maart">
																						<styles font-size="x-small" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_maart">
																						<styles font-size="x-small" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;04&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="april">
																						<styles font-size="x-small" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_april">
																						<styles font-size="x-small" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;05&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="mei">
																						<styles font-size="x-small" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_mei">
																						<styles font-size="x-small" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;06&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="juni">
																						<styles font-size="x-small" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_juni">
																						<styles font-size="x-small" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;07&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="juli">
																						<styles font-size="x-small" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_juli">
																						<styles font-size="x-small" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;08&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="augustus">
																						<styles font-size="x-small" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_augustus">
																						<styles font-size="x-small" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;09&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="september">
																						<styles font-size="x-small" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_september">
																						<styles font-size="x-small" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;10&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="oktober">
																						<styles font-size="x-small" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_oktober">
																						<styles font-size="x-small" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2) = &quot;11&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="november">
																						<styles font-size="x-small" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_november">
																						<styles font-size="x-small" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="substring(text(),6, 2)  = &quot;12&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="december">
																						<styles font-size="x-small" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_Date_december">
																						<styles font-size="x-small" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<text fixtext=" ">
														<styles font-size="x-small" font-weight="bold"/>
													</text>
													<autocalc xpath="substring(text(),1, 4)">
														<styles font-size="x-small" font-weight="bold"/>
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
			</globaltemplate>
			<globaltemplate subtype="named" match="DateOfBirth">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="fontSize" type="xs:token" occurrence="none-or-one" default-value="&apos;normal&apos;"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="eml:DateOfBirth">
								<children>
									<template subtype="element" match="eml:DateOfBirth">
										<children>
											<calltemplate subtype="named" match="Date">
												<parameters>
													<parameter name="isBold" value="$isBold"/>
													<parameter name="fontSize" value="$fontSize"/>
												</parameters>
											</calltemplate>
										</children>
										<variables/>
									</template>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$fontSize = &apos;normal&apos;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$isBold=0">
																<children>
																	<template subtype="element" match="kr:DateOfBirthAnnex">
																		<children>
																			<text fixtext="XX "/>
																			<condition>
																				<children>
																					<conditionbranch xpath="substring(text(),4, 2) = &quot;01&quot;">
																						<children>
																							<text fixtext="januari"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="substring(text(),4, 2) = &quot;02&quot;">
																						<children>
																							<text fixtext="februari"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="substring(text(),4, 2) = &quot;03&quot;">
																						<children>
																							<text fixtext="maart"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="substring(text(),4, 2) = &quot;04&quot;">
																						<children>
																							<text fixtext="april"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="substring(text(),4, 2) = &quot;05&quot;">
																						<children>
																							<text fixtext="mei"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="substring(text(),4, 2) = &quot;06&quot;">
																						<children>
																							<text fixtext="juni"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="substring(text(),4, 2) = &quot;07&quot;">
																						<children>
																							<text fixtext="juli"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="substring(text(),4, 2) = &quot;08&quot;">
																						<children>
																							<text fixtext="augustus"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="substring(text(),4, 2) = &quot;09&quot;">
																						<children>
																							<text fixtext="september"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="substring(text(),4, 2) = &quot;10&quot;">
																						<children>
																							<text fixtext="oktober"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="substring(text(),4, 2) = &quot;11&quot;">
																						<children>
																							<text fixtext="november"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="substring(text(),4, 2)  = &quot;12&quot;">
																						<children>
																							<text fixtext="december"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="substring(text(),4, 2)  = &quot;XX&quot;">
																						<children>
																							<text fixtext="XXX"/>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																			<text fixtext=" "/>
																			<autocalc xpath="substring(text(),7, 4)"/>
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
									<condition>
										<children>
											<conditionbranch xpath="$isBold = 1">
												<children>
													<template subtype="element" match="kr:DateOfBirthAnnex">
														<children>
															<text fixtext="XX ">
																<styles font-weight="bold"/>
															</text>
															<condition>
																<children>
																	<conditionbranch xpath="substring(text(),4, 2) = &quot;01&quot;">
																		<children>
																			<text fixtext="januari">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="substring(text(),4, 2) = &quot;02&quot;">
																		<children>
																			<text fixtext="februari">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="substring(text(),4, 2) = &quot;03&quot;">
																		<children>
																			<text fixtext="maart">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="substring(text(),4, 2) = &quot;04&quot;">
																		<children>
																			<text fixtext="april">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="substring(text(),4, 2) = &quot;05&quot;">
																		<children>
																			<text fixtext="mei">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="substring(text(),4, 2) = &quot;06&quot;">
																		<children>
																			<text fixtext="juni">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="substring(text(),4, 2) = &quot;07&quot;">
																		<children>
																			<text fixtext="juli">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="substring(text(),4, 2) = &quot;08&quot;">
																		<children>
																			<text fixtext="augustus">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="substring(text(),4, 2) = &quot;09&quot;">
																		<children>
																			<text fixtext="september">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="substring(text(),4, 2) = &quot;10&quot;">
																		<children>
																			<text fixtext="oktober">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="substring(text(),4, 2) = &quot;11&quot;">
																		<children>
																			<text fixtext="november">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="substring(text(),4, 2)  = &quot;12&quot;">
																		<children>
																			<text fixtext="december">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath="substring(text(),4, 2)  = &quot;XX&quot;">
																		<children>
																			<text fixtext="XXX">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<text fixtext=" ">
																<styles font-weight="bold"/>
															</text>
															<autocalc xpath="substring(text(),7, 4)">
																<styles font-weight="bold"/>
															</autocalc>
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
			</globaltemplate>
			<globaltemplate subtype="named" match="GenerateDate">
				<parameters/>
				<children>
					<autocalc xpath="$generateDate">
						<styles font-size="8pt"/>
					</autocalc>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="HashCode">
				<parameters/>
				<children>
					<autocalc xpath="$hashCode">
						<styles font-size="8pt"/>
					</autocalc>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="FooterWithoutHashcode">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$lang=0">
								<children>
									<text fixtext="Datum: ">
										<styles font-size="9pt"/>
									</text>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<autocalc xpath="$RP_FooterWithoutHashcode_datum">
										<styles font-size="9pt"/>
									</autocalc>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<calltemplate subtype="named" match="GenerateDate">
						<parameters/>
					</calltemplate>
					<condition>
						<children>
							<conditionbranch xpath="$isDraft=&quot;true&quot;">
								<children>
									<text fixtext="     -     "/>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext="(Concept) ">
														<styles font-size="9pt"/>
													</text>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_FooterWithoutHashcode_concept">
														<styles font-size="9pt"/>
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
			</globaltemplate>
			<globaltemplate subtype="named" match="Checkbox">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="text()=&quot;true&quot;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext="■"/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_Checkbox_iconChecked"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<text fixtext="	"/>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext="□"/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_Checkbox_iconUnchecked"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<text fixtext="	"/>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="FooterWithHashcode">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$isDraft=&quot;true&quot;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext="Datum: ">
														<styles font-size="9pt"/>
													</text>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_FooterWithoutHashcode_datum">
														<styles font-size="9pt"/>
													</autocalc>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<calltemplate subtype="named" match="GenerateDate">
										<parameters/>
									</calltemplate>
									<text fixtext="     -     ">
										<styles font-size="9pt"/>
									</text>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext="(Concept) ">
														<styles font-size="9pt"/>
													</text>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_FooterWithoutHashcode_concept">
														<styles font-size="9pt"/>
													</autocalc>
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
													<text fixtext="Datum: ">
														<styles font-size="8pt"/>
													</text>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_FooterWithoutHashcode_datum">
														<styles font-size="8pt"/>
													</autocalc>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<calltemplate subtype="named" match="GenerateDate">
										<parameters/>
									</calltemplate>
									<text fixtext="     -     ">
										<styles font-size="9pt"/>
									</text>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext="SHA-256-Hashcode: ">
														<styles font-size="8pt"/>
													</text>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_FooterWithHashcode_hashcode">
														<styles font-size="8pt"/>
													</autocalc>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<newline/>
									<calltemplate subtype="named" match="HashCode">
										<parameters/>
									</calltemplate>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ElectionNameP2">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Election name without date"/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$lang=0">
								<children>
									<text fixtext="verkiezing van de leden van "/>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<autocalc xpath="$RP_ElectionNameP2_verkiezingLeden"/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<calltemplate subtype="named" match="ElectionNameSimple">
						<parameters>
							<parameter name="isBold" value="1"/>
						</parameters>
					</calltemplate>
					<calltemplate subtype="named" match="ElectionDomain">
						<parameters>
							<parameter name="isBold" value="1"/>
						</parameters>
					</calltemplate>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="PostalCode">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Postal code and city like &quot;6361 DV&quot;"/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$isBold=0">
								<children>
									<template subtype="element" match="xal:Locality">
										<children>
											<template subtype="element" match="xal:PostalCode">
												<children>
													<template subtype="element" match="xal:PostalCodeNumber">
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
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$isBold=1">
								<children>
									<template subtype="element" match="xal:Locality">
										<children>
											<template subtype="element" match="xal:PostalCode">
												<children>
													<template subtype="element" match="xal:PostalCodeNumber">
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
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="PartyNameInOmissions">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="If no name is registered, display &quot;blanco lijst met als eerste kandidaat...&quot;"/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$SV_OutputFormat = &apos;RTF&apos;">
								<children>
									<template subtype="element" match="rg:CandidateListName">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="./eml:AffiliationIdentifier/eml:RegisteredName[text() != &quot;&quot;]">
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
													<conditionbranch>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="blanco lijst met als eerste kandidaat "/>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_PartyNameInOmissions_blancoLijst"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<calltemplate subtype="named" match="FirstCandidateName">
																<parameters>
																	<parameter name="prefixToUppercase" value="1"/>
																</parameters>
															</calltemplate>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<calltemplate subtype="named" match="KieskringParty">
												<parameters/>
											</calltemplate>
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
											<template subtype="element" match="rg:CandidateListName">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="./eml:AffiliationIdentifier/eml:RegisteredName[text() != &quot;&quot;]">
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
															<conditionbranch>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="blanco lijst met als eerste kandidaat "/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_PartyNameInOmissions_blancoLijst"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<calltemplate subtype="named" match="FirstCandidateName">
																		<parameters>
																			<parameter name="prefixToUppercase" value="1"/>
																		</parameters>
																	</calltemplate>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<calltemplate subtype="named" match="KieskringParty">
														<parameters/>
													</calltemplate>
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
			<globaltemplate subtype="named" match="ElectionFraction">
				<parameters>
					<parameter name="smaller" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<template subtype="element" match="rg:Fraction">
						<children>
							<autocalc xpath="floor( @numerator  div  @denominator )"/>
							<condition>
								<children>
									<conditionbranch xpath="@numerator - ( floor(@numerator div  @denominator ) * @denominator) != 0">
										<children>
											<text fixtext=" "/>
											<autocalc xpath="@numerator - ( floor(@numerator div  @denominator ) * @denominator)"/>
											<text fixtext="/"/>
											<template subtype="attribute" match="denominator">
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
			<globaltemplate subtype="named" match="Gender2">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="isSmaller" type="xsx:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Displays the gender as &quot; (m)&quot; or &quot; (v)&quot; with preceding blank (name component g)."/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$isSmaller=0">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="eml:Gender">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath=".=&quot;male&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (m)"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_m"/>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;female&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (v)"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_v"/>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;unknown&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (f)"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_unknown"/>
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
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="eml:Gender">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath=".=&quot;male&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (m)">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_m">
																								<styles font-weight="bold"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;female&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (v)">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_v">
																								<styles font-weight="bold"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;unknown&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (f)">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_unknown">
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
					<condition>
						<children>
							<conditionbranch xpath="$isSmaller=1">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="eml:Gender">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath=".=&quot;male&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (m)">
																								<styles font-size="smaller"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_m">
																								<styles font-size="smaller"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;female&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (v)">
																								<styles font-size="smaller"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_v">
																								<styles font-size="smaller"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;unknown&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (f)">
																								<styles font-size="smaller"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_unknown">
																								<styles font-size="smaller"/>
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
														<variables/>
													</template>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="eml:Gender">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath=".=&quot;male&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (m)">
																								<styles font-size="smaller" font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_m">
																								<styles font-size="smaller" font-weight="bold"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;female&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (v)">
																								<styles font-size="smaller" font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_v">
																								<styles font-size="smaller" font-weight="bold"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;unknown&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (f)">
																								<styles font-size="smaller" font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_unknown">
																								<styles font-size="smaller" font-weight="bold"/>
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
			</globaltemplate>
			<globaltemplate subtype="named" match="GenderInAffiliationVotes">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="isSmaller" type="xsx:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Displays the gender as &quot; (m)&quot; or &quot; (v)&quot; with preceding blank (name component g)."/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$isSmaller=0">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="eml:Gender">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath=".=&quot;male&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (m)"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_m"/>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;unknown&quot; or (.=&quot;female&quot; and ../../../@PublicationLanguage = &quot;fy&quot;)">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (f)"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_unknown"/>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;female&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (v)"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_v"/>
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
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="eml:Gender">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath=".=&quot;male&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (m)">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_m">
																								<styles font-weight="bold"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;unknown&quot; or (.=&quot;female&quot; and ../../../@PublicationLanguage = &quot;fy&quot;)">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (f)">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_unknown">
																								<styles font-weight="bold"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;female&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (v)">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_v">
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
					<condition>
						<children>
							<conditionbranch xpath="$isSmaller=1">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="eml:Gender">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath=".=&quot;male&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (m)">
																								<styles font-size="smaller"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_m">
																								<styles font-size="smaller"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;unknown&quot; or (.=&quot;female&quot; and ../../../@PublicationLanguage = &quot;fy&quot;)">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (f)">
																								<styles font-size="smaller"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_unknown">
																								<styles font-size="smaller"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;female&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (v)">
																								<styles font-size="smaller"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_v">
																								<styles font-size="smaller"/>
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
														<variables/>
													</template>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="eml:Gender">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath=".=&quot;male&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (m)">
																								<styles font-size="smaller" font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_m">
																								<styles font-size="smaller" font-weight="bold"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;unknown&quot; or (.=&quot;female&quot; and ../../../@PublicationLanguage = &quot;fy&quot;)">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (f)">
																								<styles font-size="smaller" font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_unknown">
																								<styles font-size="smaller" font-weight="bold"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;female&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (v)">
																								<styles font-size="smaller" font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_v">
																								<styles font-size="smaller" font-weight="bold"/>
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
			</globaltemplate>
			<globaltemplate subtype="named" match="GenderRG">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="isSmaller" type="xsx:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Displays the gender as &quot; (m)&quot; or &quot; (v)&quot; or &quot; (f)&quot; with preceding blank (name component g)."/>
									<newline/>
									<text fixtext="Based on template Gender2, but with Gender-Elements from RG namespace, not EML namspace."/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$isSmaller=0">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="rg:Gender">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath=".=&quot;male&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (m)"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_m"/>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;female&quot; and @PublicationLanguage = &quot;nl&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (v)"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_v"/>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;unknown&quot; or (.=&quot;female&quot; and @PublicationLanguage = &quot;fy&quot;)">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (f)"/>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_unknown"/>
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
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="rg:Gender">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath=".=&quot;male&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (m)">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_m">
																								<styles font-weight="bold"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;female&quot; and @PublicationLanguage = &quot;nl&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (v)">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_v">
																								<styles font-weight="bold"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;unknown&quot; or (.=&quot;female&quot; and @PublicationLanguage = &quot;fy&quot;)">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (f)">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_unknown">
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
					<condition>
						<children>
							<conditionbranch xpath="$isSmaller=1">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="rg:Gender">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath=".=&quot;male&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (m)">
																								<styles font-size="smaller"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_m">
																								<styles font-size="smaller"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;female&quot; and @PublicationLanguage = &quot;nl&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (v)">
																								<styles font-size="smaller"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_v">
																								<styles font-size="smaller"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;unknown&quot; or (.=&quot;female&quot; and @PublicationLanguage = &quot;fy&quot;)">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (f)">
																								<styles font-size="smaller"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_unknown">
																								<styles font-size="smaller"/>
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
														<variables/>
													</template>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="rg:Gender">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath=".=&quot;male&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (m)">
																								<styles font-size="smaller" font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_m">
																								<styles font-size="smaller" font-weight="bold"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;female&quot; and @PublicationLanguage = &quot;nl&quot;">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (v)">
																								<styles font-size="smaller" font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_v">
																								<styles font-size="smaller" font-weight="bold"/>
																							</autocalc>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</conditionbranch>
																	<conditionbranch xpath=".=&quot;unknown&quot; or (.=&quot;female&quot; and @PublicationLanguage = &quot;fy&quot;)">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="$lang=0">
																						<children>
																							<text fixtext=" (f)">
																								<styles font-size="smaller" font-weight="bold"/>
																							</text>
																						</children>
																					</conditionbranch>
																					<conditionbranch>
																						<children>
																							<autocalc xpath="$RP_Gender2_unknown">
																								<styles font-size="smaller" font-weight="bold"/>
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
			</globaltemplate>
			<globaltemplate subtype="named" match="FirstCandidateName">
				<parameters>
					<parameter name="prefixToUppercase" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<template subtype="element" match="rg:FirstCandidateName">
						<children>
							<calltemplate subtype="named" match="LastNameH1">
								<parameters>
									<parameter name="isBold"/>
									<parameter name="fontSize"/>
									<parameter name="prefixToUppercase" value="$prefixToUppercase"/>
								</parameters>
							</calltemplate>
							<text fixtext=", "/>
							<calltemplate subtype="named" match="FirstNameH1">
								<parameters/>
							</calltemplate>
							<calltemplate subtype="named" match="GenderRG">
								<parameters/>
							</calltemplate>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="PartyNameOrFirstCandidate">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="If no name is registered, display the &quot;blanco lijst met als eerste kandidaat&quot; + first candidates name"/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$SV_OutputFormat = &apos;RTF&apos;">
								<children>
									<template subtype="element" match="rg:CandidateListName">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="./eml:AffiliationIdentifier/eml:RegisteredName[text()!=&quot;&quot;]">
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
													<conditionbranch>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="blanco lijst met als eerste kandidaat "/>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_PartyNameOrFirstCandidate_blancoLijst"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<template subtype="element" match="rg:FirstCandidateName">
																<children>
																	<calltemplate subtype="named" match="LastNameH1">
																		<parameters>
																			<parameter name="isBold"/>
																			<parameter name="fontSize"/>
																			<parameter name="prefixToUppercase" value="1"/>
																		</parameters>
																	</calltemplate>
																	<text fixtext=","/>
																	<calltemplate subtype="named" match="Initials">
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
							</conditionbranch>
							<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
								<children>
									<paragraph paragraphtag="pre-wrap">
										<styles white-space="pre-wrap"/>
										<children>
											<template subtype="element" match="rg:CandidateListName">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="./eml:AffiliationIdentifier/eml:RegisteredName[text()!=&quot;&quot;]">
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
															<conditionbranch>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="blanco lijst met als eerste kandidaat "/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_PartyNameOrFirstCandidate_blancoLijst"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<template subtype="element" match="rg:FirstCandidateName">
																		<children>
																			<calltemplate subtype="named" match="LastNameH1">
																				<parameters>
																					<parameter name="isBold"/>
																					<parameter name="fontSize"/>
																					<parameter name="prefixToUppercase" value="1"/>
																				</parameters>
																			</calltemplate>
																			<text fixtext=","/>
																			<calltemplate subtype="named" match="Initials">
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
									</paragraph>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="SignatureBlock">
				<parameters>
					<parameter name="distance" type="xs:int" occurrence="none-or-one" default-value="1"/>
				</parameters>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="140pt"/>
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
												<properties align="left" class="entry"/>
												<children>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="Plaats: ........................................................"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_SignatureBlock_plaats"/>
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
																	<text fixtext="Datum: ......................................................."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_SignatureBlock_datum"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$distance &gt;= 1">
																<children>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<condition>
														<children>
															<conditionbranch xpath="$distance &gt;= 2">
																<children>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="......................................................................, Voorzitter"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_SignatureBlock_voorzitter"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$distance &gt;= 1">
																<children>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<condition>
														<children>
															<conditionbranch xpath="$distance &gt;= 2">
																<children>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="......................................................................"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_SignatureBlock_emptyLine"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$distance &gt;= 1">
																<children>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<condition>
														<children>
															<conditionbranch xpath="$distance &gt;= 2">
																<children>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="......................................................................"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_SignatureBlock_emptyLine"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$distance &gt;= 1">
																<children>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<condition>
														<children>
															<conditionbranch xpath="$distance &gt;= 2">
																<children>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="...................................................................... Leden"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_SignatureBlock_leden"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$distance &gt;= 1">
																<children>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<condition>
														<children>
															<conditionbranch xpath="$distance &gt;= 2">
																<children>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="......................................................................"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_SignatureBlock_emptyLine"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$distance &gt;= 1">
																<children>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<condition>
														<children>
															<conditionbranch xpath="$distance &gt;= 2">
																<children>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="......................................................................"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_SignatureBlock_emptyLine"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$distance &gt;= 1">
																<children>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<condition>
														<children>
															<conditionbranch xpath="$distance &gt;= 2">
																<children>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="......................................................................"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_SignatureBlock_emptyLine"/>
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
			<globaltemplate subtype="named" match="SignatureBlock-3">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="140pt"/>
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
												<properties align="left" class="entry"/>
												<children>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="Plaats: ........................................................"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_SignatureBlock-3_plaats"/>
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
																	<text fixtext="Datum: ......................................................."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_SignatureBlock-3_datum"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<newline/>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="............................................., Voorzitter"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_SignatureBlock-3_voorzitter"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<newline/>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="............................................."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_SignatureBlock-3_emptyLine"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<newline/>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="............................................. Leden"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_SignatureBlock-3_leden"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<newline/>
													<newline/>
													<newline/>
													<newline/>
													<newline/>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="............................................."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_SignatureBlock-3_emptyLine"/>
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
			<globaltemplate subtype="named" match="ElectionNameSimple">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Just election name"/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<template subtype="element" match="eml:ElectionIdentifier">
						<children>
							<template subtype="element" match="eml:ElectionCategory">
								<children>
									<condition>
										<children>
											<conditionbranch xpath=".=&quot;EP&quot; or .=&quot;BC&quot; or .=&quot;AB&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="het "/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameSimple_het"/>
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
																	<text fixtext="de "/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameSimple_de"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<condition>
														<children>
															<conditionbranch xpath=".= &quot;TK&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="Tweede Kamer der Staten-Generaal"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_tweedeKamer"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath=".=&quot;EK&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="Eerste Kamer der Staten-Generaal"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_eersteKamer"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath=".=&quot;PS&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="provinciale staten van"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_provincialeStaten"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath=".=&quot;AB&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="algemeen bestuur van het "/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_algemeen_bestuur"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<calltemplate subtype="named" match="WaterschapTypeName">
																		<parameters>
																			<parameter name="isBold" value="$isBold"/>
																			<parameter name="font-size"/>
																		</parameters>
																	</calltemplate>
																</children>
															</conditionbranch>
															<conditionbranch xpath=".=&quot;EP&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="Europees Parlement"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_hetEuropeesParlement"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath=".=&quot;GR&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="gemeenteraad"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_raadVanDeGemeente"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath=".=&quot;ER&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="eilandsraad"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_eilandsraad"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath=".=&quot;BC&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="algemeen bestuur van de bestuurscommissie"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_bestuurscommissie"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath=".=&quot;GC&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="gebiedscommissie"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_gebiedscommissie"/>
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
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<condition>
														<children>
															<conditionbranch xpath=".= &quot;TK&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="Tweede Kamer der Staten-Generaal">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_tweedeKamer">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath=".=&quot;EK&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="Eerste Kamer der Staten-Generaal">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_eersteKamer">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath=".=&quot;PS&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="provinciale staten">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_provincialeStaten">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath=".=&quot;AB&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="algemeen bestuur van het ">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_algemeen_bestuur">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<calltemplate subtype="named" match="WaterschapTypeName">
																		<parameters>
																			<parameter name="isBold" value="$isBold"/>
																			<parameter name="font-size"/>
																		</parameters>
																	</calltemplate>
																</children>
															</conditionbranch>
															<conditionbranch xpath=".=&quot;EP&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="Europees Parlement">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_hetEuropeesParlement">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath=".=&quot;GR&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="gemeenteraad">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_raadVanDeGemeente">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath=".=&quot;ER&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="eilandsraad">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_eilandsraad">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath=".=&quot;BC&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="algemeen bestuur van de bestuurscommissie">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_bestuurscommissie">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath=".=&quot;GC&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="gebiedscommissie">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ElectionNameSimple_gebiedscommissie">
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
									<condition>
										<children>
											<conditionbranch xpath=".=&quot;PS&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext=" van"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameSimple_van"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;GR&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext=" van"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameSimple_van"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;ER&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext=" van het openbaar lichaam"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameSimple_van_eilandsraad"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</conditionbranch>
											<conditionbranch xpath=".=&quot;BC&quot;">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext=" van stadsdeel"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ElectionNameSimple_van_stadsdeel"/>
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
			</globaltemplate>
			<globaltemplate subtype="named" match="ObjectionsByVoters1">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$lang=0">
								<children>
									<text fixtext="Door de in de zittingsruimte aanwezige kiezers zijn"/>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<autocalc xpath="$RP_ObjectionsByVoters1_aanwezigeKiezers"/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<calltemplate subtype="named" match="LineBreakTable">
						<parameters/>
					</calltemplate>
					<calltemplate subtype="named" match="ObjectionsByVoters1b">
						<parameters/>
					</calltemplate>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ObjectionsByVoters2">
				<parameters/>
				<children>
					<template subtype="element" match="rg:ObjectionsByVoters">
						<children>
							<template subtype="element" match="rg:NotesOnObjections">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="../rg:NotesOnObjections[text() != &quot;&quot;]">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$SV_OutputFormat = &apos;RTF&apos;">
																<children>
																	<paragraph paragraphtag="p">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="string"/>
																			</content>
																		</children>
																	</paragraph>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<paragraph paragraphtag="p">
																		<styles white-space="pre-wrap"/>
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="string"/>
																			</content>
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
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="......................................................................................................................................."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ObjectionsByVoters2_emptyLine"/>
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
																	<text fixtext="......................................................................................................................................."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ObjectionsByVoters2_emptyLine"/>
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
																	<text fixtext="......................................................................................................................................."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ObjectionsByVoters2_emptyLine"/>
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
																	<text fixtext="......................................................................................................................................."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ObjectionsByVoters2_emptyLine"/>
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
																	<text fixtext="......................................................................................................................................."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ObjectionsByVoters2_emptyLine"/>
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
			</globaltemplate>
			<globaltemplate subtype="named" match="ObjectionsByVoters2a">
				<parameters/>
				<children>
					<newline/>
					<template subtype="element" match="rg:ObjectionsByVoters">
						<children>
							<template subtype="element" match="rg:Objections">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="../rg:Objections[text() != &quot;&quot;]">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$SV_OutputFormat = &apos;RTF&apos;">
																<children>
																	<paragraph paragraphtag="p">
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="string"/>
																			</content>
																		</children>
																	</paragraph>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<paragraph paragraphtag="p">
																		<styles white-space="pre-wrap"/>
																		<children>
																			<content subtype="regular">
																				<format basic-type="xsd" datatype="string"/>
																			</content>
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
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="......................................................................................................................................."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ObjectionsByVoters2a_emptyLine"/>
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
																	<text fixtext="......................................................................................................................................."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ObjectionsByVoters2a_emptyLine"/>
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
																	<text fixtext="......................................................................................................................................."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ObjectionsByVoters2a_emptyLine"/>
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
																	<text fixtext="......................................................................................................................................."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ObjectionsByVoters2a_emptyLine"/>
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
																	<text fixtext="......................................................................................................................................."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ObjectionsByVoters2a_emptyLine"/>
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
			</globaltemplate>
			<globaltemplate subtype="named" match="LineBreak">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
								<children>
									<paragraph paragraphtag="p"/>
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
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="LineBreakPdf">
				<parameters/>
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
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="SmallLineBreak">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
								<children>
									<paragraph paragraphtag="p"/>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="LineBreakTable">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
								<children>
									<paragraph paragraphtag="p"/>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="KieskringParty">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Additional information about the distrinct for which a centrally submitted list is nominated"/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="eml:ContestIdentifier/@Id = &quot;alle&quot;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext=" in alle kieskringen"/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_KieskringParty_inAlleKieskringen"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
								</children>
							</conditionbranch>
							<conditionbranch xpath="eml:ContestIdentifier">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext=" in kieskring"/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_KieskringParty_inKieskring"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<text fixtext=" "/>
									<calltemplate subtype="named" match="ContestIdentifier">
										<parameters/>
									</calltemplate>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ElectionDomain">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="font-size" type="xs:token" occurrence="none-or-one" default-value="&apos;small&apos;"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Election domain as part of the elction name - if needed"/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$isBold=0 and $font-size=&apos;small&apos;">
								<children>
									<template subtype="element" match="eml:ElectionIdentifier">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="./eml:ElectionCategory[text()=&quot;PS&quot; or text()=&quot;AB&quot; or text()=&quot;GR&quot; or text()=&quot;ER&quot;]">
														<children>
															<template subtype="element" match="kr:ElectionDomain">
																<children>
																	<text fixtext=" "/>
																	<content subtype="regular"/>
																</children>
																<variables/>
															</template>
														</children>
													</conditionbranch>
													<conditionbranch xpath="./eml:ElectionCategory[ text()=&quot;BC&quot; or text()=&quot;GC&quot; ]">
														<children>
															<template subtype="element" match="kr:ElectionDomain">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath=". != &quot;Rotterdam - Centrum&quot;  and  . != &quot;Rotterdam - Pernis&quot;">
																				<children>
																					<text fixtext=" "/>
																					<autocalc xpath="substring( text() , 13 )"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<text fixtext=" "/>
																					<content subtype="regular"/>
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
										<variables/>
									</template>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$isBold=1 and $font-size=&apos;small&apos;">
								<children>
									<template subtype="element" match="eml:ElectionIdentifier">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="./eml:ElectionCategory[text()=&quot;PS&quot; or text()=&quot;AB&quot; or text()=&quot;GR&quot; or text()=&quot;ER&quot;]">
														<children>
															<template subtype="element" match="kr:ElectionDomain">
																<children>
																	<text fixtext=" ">
																		<styles font-weight="bold"/>
																	</text>
																	<content subtype="regular">
																		<styles font-weight="bold"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
													</conditionbranch>
													<conditionbranch xpath="./eml:ElectionCategory[ text()=&quot;BC&quot; or text()=&quot;GC&quot; ]">
														<children>
															<template subtype="element" match="kr:ElectionDomain">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath=". != &quot;Rotterdam - Centrum&quot;  and  . != &quot;Rotterdam - Pernis&quot;">
																				<children>
																					<text fixtext=" ">
																						<styles font-weight="bold"/>
																					</text>
																					<autocalc xpath="substring( text() , 13 )">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<text fixtext=" ">
																						<styles font-weight="bold"/>
																					</text>
																					<content subtype="regular">
																						<styles font-weight="bold"/>
																					</content>
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
										<variables/>
									</template>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="$isBold=1 and $font-size=&apos;medium&apos;">
								<children>
									<template subtype="element" match="eml:ElectionIdentifier">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="./eml:ElectionCategory[text()=&quot;PS&quot; or text()=&quot;AB&quot; or text()=&quot;GR&quot; or text()=&quot;ER&quot;]">
														<children>
															<template subtype="element" match="kr:ElectionDomain">
																<children>
																	<text fixtext=" ">
																		<styles font-size="medium" font-weight="bold"/>
																	</text>
																	<content subtype="regular">
																		<styles font-size="medium" font-weight="bold"/>
																	</content>
																</children>
																<variables/>
															</template>
														</children>
													</conditionbranch>
													<conditionbranch xpath="./eml:ElectionCategory[ text()=&quot;BC&quot; or text()=&quot;GC&quot; ]">
														<children>
															<template subtype="element" match="kr:ElectionDomain">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath=". != &quot;Rotterdam - Centrum&quot;  and  . != &quot;Rotterdam - Pernis&quot;">
																				<children>
																					<text fixtext=" ">
																						<styles font-size="medium" font-weight="bold"/>
																					</text>
																					<autocalc xpath="substring( text() , 13 )">
																						<styles font-size="medium" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<text fixtext=" ">
																						<styles font-size="medium" font-weight="bold"/>
																					</text>
																					<content subtype="regular">
																						<styles font-size="medium" font-weight="bold"/>
																					</content>
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
										<variables/>
									</template>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ContestIdentifier">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="fontSize" type="xs:token" occurrence="none-or-one" default-value="&apos;normal&apos;"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$fontSize = &apos;normal&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="eml:ContestIdentifier">
														<children>
															<template subtype="attribute" match="Id">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="NMTOKEN"/>
																	</content>
																</children>
																<variables/>
															</template>
															<text fixtext=" ("/>
															<template subtype="element" match="eml:ContestName">
																<children>
																	<content subtype="regular">
																		<format basic-type="xsd" datatype="token"/>
																	</content>
																</children>
																<variables/>
															</template>
															<text fixtext=")"/>
														</children>
														<variables/>
													</template>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=1">
												<children>
													<template subtype="element" match="eml:ContestIdentifier">
														<children>
															<template subtype="attribute" match="Id">
																<children>
																	<content subtype="regular">
																		<styles font-weight="bold"/>
																		<format basic-type="xsd" datatype="NMTOKEN"/>
																	</content>
																</children>
																<variables/>
															</template>
															<text fixtext=" (">
																<styles font-weight="bold"/>
															</text>
															<template subtype="element" match="eml:ContestName">
																<children>
																	<content subtype="regular">
																		<styles font-weight="bold"/>
																		<format basic-type="xsd" datatype="token"/>
																	</content>
																</children>
																<variables/>
															</template>
															<text fixtext=")">
																<styles font-weight="bold"/>
															</text>
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
					<condition>
						<children>
							<conditionbranch xpath="$fontSize = &apos;medium&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold=0">
												<children>
													<template subtype="element" match="eml:ContestIdentifier">
														<children>
															<template subtype="attribute" match="Id">
																<children>
																	<content subtype="regular">
																		<styles font-size="medium"/>
																		<format basic-type="xsd" datatype="NMTOKEN"/>
																	</content>
																</children>
																<variables/>
															</template>
															<text fixtext=" (">
																<styles font-size="medium"/>
															</text>
															<template subtype="element" match="eml:ContestName">
																<children>
																	<content subtype="regular">
																		<styles font-size="medium"/>
																		<format basic-type="xsd" datatype="token"/>
																	</content>
																</children>
																<variables/>
															</template>
															<text fixtext=")">
																<styles font-size="medium"/>
															</text>
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
			</globaltemplate>
			<globaltemplate subtype="named" match="ObjectionsByVoters1b">
				<parameters/>
				<children>
					<tgrid>
						<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="30pt"/>
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
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="□ geen bezwaren ingebracht."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ObjectionsByVoters1b_geenBezwarenIngebracht"/>
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
													<condition>
														<children>
															<conditionbranch xpath="string(rg:ObjectionsByVoters/rg:Objections) != &quot;&quot;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="■"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ObjectionsByVoters1b_iconChecked"/>
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
																					<text fixtext="□"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_ObjectionsByVoters1b_iconUnchecked"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext=" de volgende bezwaren ingebracht:"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ObjectionsByVoters1b_volgendeBezwarenIngebracht"/>
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
					<calltemplate subtype="named" match="ObjectionsByVoters2a">
						<parameters/>
					</calltemplate>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="Space">
				<parameters>
					<parameter name="fontSize" type="xs:string" occurrence="none-or-one" default-value="&apos;normal&apos;"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$fontSize=&quot;normal&quot;">
								<children>
									<autocalc xpath="&apos; &apos;"/>
								</children>
							</conditionbranch>
							<conditionbranch xpath="$fontSize=&quot;smaller&quot;">
								<children>
									<autocalc xpath="&apos; &apos;">
										<styles font-size="smaller"/>
									</autocalc>
								</children>
							</conditionbranch>
							<conditionbranch xpath="$fontSize=&apos;x-small&apos;">
								<children>
									<autocalc xpath="&apos; &apos;">
										<styles font-size="x-small"/>
									</autocalc>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<autocalc xpath="&apos; &apos;"/>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ElectionFractionPart1">
				<parameters>
					<parameter name="smaller" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="bold" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$bold = 0">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$smaller = 1">
												<children>
													<template subtype="element" match="rg:Fraction">
														<children>
															<autocalc xpath="floor( @numerator  div  @denominator )">
																<styles font-size="smaller"/>
															</autocalc>
														</children>
														<variables/>
													</template>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<template subtype="element" match="rg:Fraction">
														<children>
															<autocalc xpath="floor( @numerator  div  @denominator )"/>
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
											<conditionbranch xpath="$smaller = 1">
												<children>
													<template subtype="element" match="rg:Fraction">
														<children>
															<autocalc xpath="floor( @numerator  div  @denominator )">
																<styles font-size="smaller" font-weight="bold"/>
															</autocalc>
														</children>
														<variables/>
													</template>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<template subtype="element" match="rg:Fraction">
														<children>
															<autocalc xpath="floor( @numerator  div  @denominator )">
																<styles font-weight="bold"/>
															</autocalc>
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
			</globaltemplate>
			<globaltemplate subtype="named" match="ElectionFractionPart2">
				<parameters>
					<parameter name="smaller" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="bold" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$bold = 0">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$smaller = 1">
												<children>
													<template subtype="element" match="rg:Fraction">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="@numerator - ( floor(@numerator div  @denominator ) * @denominator) != 0">
																		<children>
																			<autocalc xpath="@numerator - ( floor(@numerator div  @denominator ) * @denominator)">
																				<styles font-size="smaller"/>
																			</autocalc>
																			<text fixtext="/">
																				<styles font-size="smaller"/>
																			</text>
																			<template subtype="attribute" match="denominator">
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
											</conditionbranch>
											<conditionbranch>
												<children>
													<template subtype="element" match="rg:Fraction">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="@numerator - ( floor(@numerator div  @denominator ) * @denominator) != 0">
																		<children>
																			<autocalc xpath="@numerator - ( floor(@numerator div  @denominator ) * @denominator)"/>
																			<text fixtext="/"/>
																			<template subtype="attribute" match="denominator">
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
													<template subtype="element" match="rg:Fraction">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="@numerator - ( floor(@numerator div  @denominator ) * @denominator) != 0">
																		<children>
																			<autocalc xpath="@numerator - ( floor(@numerator div  @denominator ) * @denominator)">
																				<styles font-size="smaller" font-weight="bold"/>
																			</autocalc>
																			<text fixtext="/">
																				<styles font-size="smaller" font-weight="bold"/>
																			</text>
																			<template subtype="attribute" match="denominator">
																				<children>
																					<content subtype="regular">
																						<styles font-size="smaller" font-weight="bold"/>
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
											</conditionbranch>
											<conditionbranch>
												<children>
													<template subtype="element" match="rg:Fraction">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="@numerator - ( floor(@numerator div  @denominator ) * @denominator) != 0">
																		<children>
																			<autocalc xpath="@numerator - ( floor(@numerator div  @denominator ) * @denominator)">
																				<styles font-weight="bold"/>
																			</autocalc>
																			<text fixtext="/">
																				<styles font-weight="bold"/>
																			</text>
																			<template subtype="attribute" match="denominator">
																				<children>
																					<content subtype="regular">
																						<styles font-weight="bold"/>
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
											</conditionbranch>
										</children>
									</condition>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="Initials">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Displays the initials like &quot; H.H.G.&quot; (name components f) with preceding blank."/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<template subtype="element" match="xnl:PersonName">
						<children>
							<template subtype="element" match="xnl:NameLine">
								<children>
									<calltemplate subtype="named" match="Space">
										<parameters/>
									</calltemplate>
									<content subtype="regular"/>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="Line">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
								<children>
									<text fixtext="________">
										<styles font-size="small" margin-bottom="6pt" vertical-align="bottom"/>
									</text>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<text fixtext="_________________________________________________________________________">
						<styles font-size="small" margin-bottom="6pt" vertical-align="bottom"/>
					</text>
					<newline/>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="FooterLine">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$SV_OutputFormat = &apos;PDF&apos;">
								<children>
									<text fixtext="______">
										<styles margin-bottom="6pt" vertical-align="bottom"/>
									</text>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<text fixtext="__________________________________________________________________">
						<styles margin-bottom="6pt" vertical-align="bottom"/>
					</text>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="LegacyFooter">
				<parameters>
					<parameter name="isHashcode" type="xs:int" occurrence="none-or-one" default-value="1"/>
					<parameter name="isPagenumber" type="xs:int" occurrence="none-or-one" default-value="1"/>
				</parameters>
				<children>
					<tgrid>
						<properties width="100%"/>
						<children>
							<tgridbody-cols>
								<children>
									<tgridcol>
										<styles width="83%"/>
									</tgridcol>
									<tgridcol>
										<styles width="17%"/>
									</tgridcol>
								</children>
							</tgridbody-cols>
							<tgridbody-rows>
								<children>
									<tgridrow>
										<styles height="30pt"/>
										<children>
											<tgridcell>
												<styles padding="0"/>
											</tgridcell>
											<tgridcell joinleft="1"/>
										</children>
									</tgridrow>
									<tgridrow>
										<children>
											<tgridcell>
												<styles padding="0" vertical-align="bottom"/>
												<children>
													<calltemplate subtype="named" match="FooterLine">
														<parameters/>
													</calltemplate>
												</children>
											</tgridcell>
											<tgridcell joinleft="1"/>
										</children>
									</tgridrow>
									<tgridrow>
										<styles height="30pt"/>
										<children>
											<tgridcell>
												<properties align="left"/>
												<styles font-size="9pt" padding="0" vertical-align="top"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$isHashcode=1">
																<children>
																	<calltemplate subtype="named" match="FooterWithHashcode">
																		<parameters/>
																	</calltemplate>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<condition>
														<children>
															<conditionbranch xpath="$isHashcode=0">
																<children>
																	<calltemplate subtype="named" match="FooterWithoutHashcode">
																		<parameters/>
																	</calltemplate>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell>
												<properties align="right"/>
												<styles font-size="9pt" padding="0" vertical-align="top" width="100pt"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$isPagenumber=1">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="pagina ">
																						<styles font-size="9pt"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_LegacyFooter_pagina">
																						<styles font-size="9pt"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<field>
																		<styles font-size="9pt"/>
																	</field>
																	<text fixtext=" / ">
																		<styles font-size="9pt"/>
																	</text>
																	<field type="pagetotal">
																		<styles font-size="9pt"/>
																	</field>
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
			<globaltemplate subtype="named" match="Gender3">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Displays the gender as &quot;hij&quot; or &quot;zij&quot;, if @PublishGender = &quot;true&quot;, otherwise as &quot;hij/zij&quot;"/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="../kr:ListData/@PublishGender=&quot;true&quot;">
								<children>
									<template subtype="element" match="eml:Gender">
										<children>
											<condition>
												<children>
													<conditionbranch xpath=".=&quot;male&quot;">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="hij"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_Gender3_hij"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</conditionbranch>
													<conditionbranch xpath=".=&quot;unknown&quot; or (.=&quot;female&quot; and ../../kr:ListData/@PublicationLanguage=&quot;fy&quot;)">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="zij"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_Gender3_unknown"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</conditionbranch>
													<conditionbranch xpath=".=&quot;female&quot;">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="zij"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_Gender3_zij"/>
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
							</conditionbranch>
							<conditionbranch>
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext="hij/zij"/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_Gender3_hijZij"/>
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
			<globaltemplate subtype="named" match="Gender4">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="Displays the gender as &quot;hij&quot; or &quot;zij&quot;, if @PublishGender = &quot;true&quot;, otherwise as &quot;hij/zij&quot;"/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<condition>
						<children>
							<conditionbranch xpath="../kr:ListData/@PublishGender=&quot;true&quot;">
								<children>
									<template subtype="element" match="eml:Gender">
										<children>
											<condition>
												<children>
													<conditionbranch xpath=".=&quot;male&quot;">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="zich"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_Gender4_him"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</conditionbranch>
													<conditionbranch xpath=".=&quot;unknown&quot; or (.=&quot;female&quot; and ../../kr:ListData/@PublicationLanguage=&quot;fy&quot;)">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="zich"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_Gender4_unknown"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</conditionbranch>
													<conditionbranch xpath=".=&quot;female&quot;">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="zich"/>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_Gender4_har"/>
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
							</conditionbranch>
							<conditionbranch>
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext="zich"/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_Gender4_himHar"/>
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
			<globaltemplate subtype="named" match="Contests">
				<parameters>
					<parameter name="separator" type="xs:string" occurrence="none-or-one" default-value="&apos;comma&apos;"/>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<template subtype="element" match="eml:Affiliation">
						<children>
							<template subtype="element" match="kr:ListData">
								<children>
									<template subtype="element" match="kr:Contests">
										<children>
											<template subtype="element" match="kr:Contest">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$separator=&apos;newline&apos; and position()=1">
																<children>
																	<newline/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<condition>
														<children>
															<conditionbranch xpath="$isBold = 1">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$separator=&apos;comma&apos; and position()!=last()">
																				<children>
																					<content subtype="regular">
																						<styles font-weight="bold"/>
																					</content>
																					<text fixtext=", ">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="$separator=&apos;newline&apos;">
																				<children>
																					<content subtype="regular">
																						<styles font-weight="bold"/>
																					</content>
																					<newline/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<content subtype="regular">
																						<styles font-weight="bold"/>
																					</content>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<condition>
														<children>
															<conditionbranch xpath="$isBold = 0">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$separator=&apos;comma&apos; and position()!=last()">
																				<children>
																					<content subtype="regular"/>
																					<text fixtext=", "/>
																				</children>
																			</conditionbranch>
																			<conditionbranch xpath="$separator=&apos;newline&apos;">
																				<children>
																					<content subtype="regular"/>
																					<newline/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<content subtype="regular"/>
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
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ObjectionsReference">
				<parameters>
					<parameter name="authority" type="xs:string" occurrence="none-or-one" default-value="&apos;hoofstembureau&apos;"/>
					<parameter name="skipFirstSentence" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$skipFirstSentence = 0">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext="Door de in de zittingsruimte aanwezige kiezers zijn"/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_ObjectionsReference_aanwezigeKiezers"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
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
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="□ "/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ObjectionsReference_iconUnchecked"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell>
												<styles vertical-align="top"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="geen bezwaren ingebracht."/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ObjectionsReference_geenBezwarenIngebracht"/>
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
												<styles vertical-align="top"/>
											</tgridcell>
											<tgridcell>
												<styles vertical-align="top"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="□ "/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ObjectionsReference_iconUnchecked"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
												</children>
											</tgridcell>
											<tgridcell>
												<styles vertical-align="top"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="de volgende bezwaren ingebracht (zie gewaarmerkte bijlage voor bezwaren en opmerkingen daarover van het "/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ObjectionsReference_volgendeBezwarenIngebracht"/>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<autocalc xpath="$authority"/>
													<text fixtext=")."/>
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
			<globaltemplate subtype="named" match="LivingAddress">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="0=1">
								<children>
									<text fixtext="City and Country code (if present) &quot;Amsterdam&quot; or &quot;Berlin (DE)&quot;"/>
									<newline/>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<template subtype="element" match="kr:LivingAddress">
						<children>
							<condition>
								<children>
									<conditionbranch xpath="$isBold=0">
										<children>
											<template subtype="element" match="kr:LocalityName">
												<children>
													<content subtype="regular"/>
												</children>
												<variables/>
											</template>
											<template subtype="element" match="kr:CountryNameCode">
												<children>
													<text fixtext=" ("/>
													<content subtype="regular"/>
													<text fixtext=")"/>
												</children>
												<variables/>
											</template>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<condition>
								<children>
									<conditionbranch xpath="$isBold=1">
										<children>
											<template subtype="element" match="kr:LocalityName">
												<children>
													<content subtype="regular">
														<styles font-weight="bold"/>
													</content>
												</children>
												<variables/>
											</template>
											<template subtype="element" match="kr:CountryNameCode">
												<children>
													<text fixtext=" (">
														<styles font-weight="bold"/>
													</text>
													<content subtype="regular">
														<styles font-weight="bold"/>
													</content>
													<text fixtext=")">
														<styles font-weight="bold"/>
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
			</globaltemplate>
			<globaltemplate subtype="named" match="CompleteContactAddress">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="fontSize" type="xs:token" occurrence="none-or-one" default-value="&apos;normal&apos;"/>
				</parameters>
				<children>
					<template subtype="element" match="eml:Contact">
						<children>
							<template subtype="element" match="eml:MailingAddress">
								<children>
									<calltemplate subtype="named" match="CompleteAddress">
										<parameters>
											<parameter name="fontSize" value="$fontSize"/>
											<parameter name="isBold" value="$isBold"/>
										</parameters>
									</calltemplate>
									<template subtype="element" match="xal:Country">
										<children>
											<calltemplate subtype="named" match="CompleteAddress">
												<parameters>
													<parameter name="fontSize" value="$fontSize"/>
													<parameter name="isBold" value="$isBold"/>
												</parameters>
											</calltemplate>
											<template subtype="element" match="xal:CountryNameCode">
												<children>
													<calltemplate subtype="named" match="Space">
														<parameters/>
													</calltemplate>
													<condition>
														<children>
															<conditionbranch xpath="$fontSize = &apos;normal&apos;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$isBold = 0">
																				<children>
																					<text fixtext="("/>
																					<content subtype="regular"/>
																					<text fixtext=")"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<condition>
																		<children>
																			<conditionbranch xpath="$isBold = 1">
																				<children>
																					<text fixtext="(">
																						<styles font-weight="bold"/>
																					</text>
																					<content subtype="regular">
																						<styles font-weight="bold"/>
																					</content>
																					<text fixtext=")">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<condition>
														<children>
															<conditionbranch xpath="$fontSize = &apos;x-small&apos;">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$isBold = 0">
																				<children>
																					<text fixtext="(">
																						<styles font-size="x-small"/>
																					</text>
																					<content subtype="regular">
																						<styles font-size="x-small"/>
																					</content>
																					<text fixtext=")">
																						<styles font-size="x-small"/>
																					</text>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																	<condition>
																		<children>
																			<conditionbranch xpath="$isBold = 1">
																				<children>
																					<text fixtext="(">
																						<styles font-size="x-small" font-weight="bold"/>
																					</text>
																					<content subtype="regular">
																						<styles font-size="x-small" font-weight="bold"/>
																					</content>
																					<text fixtext=")">
																						<styles font-size="x-small" font-weight="bold"/>
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
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="QualifyingAddressCity">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="fontSize" type="xs:token" occurrence="none-or-one" default-value="&apos;normal&apos;"/>
				</parameters>
				<children>
					<template subtype="element" match="eml:QualifyingAddress">
						<children>
							<calltemplate subtype="named" match="City">
								<parameters>
									<parameter name="isBold" value="$isBold"/>
									<parameter name="fontSize" value="$fontSize"/>
								</parameters>
							</calltemplate>
							<template subtype="element" match="xal:Country">
								<children>
									<calltemplate subtype="named" match="City">
										<parameters>
											<parameter name="isBold" value="$isBold"/>
											<parameter name="fontSize" value="$fontSize"/>
										</parameters>
									</calltemplate>
									<template subtype="element" match="xal:CountryNameCode">
										<children>
											<calltemplate subtype="named" match="Space">
												<parameters/>
											</calltemplate>
											<condition>
												<children>
													<conditionbranch xpath="$fontSize = &apos;normal&apos;">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$isBold = 0">
																		<children>
																			<text fixtext="("/>
																			<content subtype="regular"/>
																			<text fixtext=")"/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<condition>
																<children>
																	<conditionbranch xpath="$isBold = 1">
																		<children>
																			<text fixtext="(">
																				<styles font-weight="bold"/>
																			</text>
																			<content subtype="regular">
																				<styles font-weight="bold"/>
																			</content>
																			<text fixtext=")">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<condition>
												<children>
													<conditionbranch xpath="$fontSize = &apos;smaller&apos;">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$isBold = 0">
																		<children>
																			<text fixtext="(">
																				<styles font-size="smaller"/>
																			</text>
																			<content subtype="regular">
																				<styles font-size="smaller"/>
																			</content>
																			<text fixtext=")">
																				<styles font-size="smaller"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<condition>
																<children>
																	<conditionbranch xpath="$isBold = 1">
																		<children>
																			<text fixtext="(">
																				<styles font-size="smaller" font-weight="bold"/>
																			</text>
																			<content subtype="regular">
																				<styles font-size="smaller" font-weight="bold"/>
																			</content>
																			<text fixtext=")">
																				<styles font-size="smaller" font-weight="bold"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<condition>
												<children>
													<conditionbranch xpath="$fontSize = &apos;x-small&apos;">
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$isBold = 0">
																		<children>
																			<text fixtext="(">
																				<styles font-size="x-small"/>
																			</text>
																			<content subtype="regular">
																				<styles font-size="x-small"/>
																			</content>
																			<text fixtext=")">
																				<styles font-size="x-small"/>
																			</text>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<condition>
																<children>
																	<conditionbranch xpath="$isBold = 1">
																		<children>
																			<text fixtext="(">
																				<styles font-size="x-small" font-weight="bold"/>
																			</text>
																			<content subtype="regular">
																				<styles font-size="x-small" font-weight="bold"/>
																			</content>
																			<text fixtext=")">
																				<styles font-size="x-small" font-weight="bold"/>
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
			<globaltemplate subtype="named" match="ChapterElection">
				<parameters>
					<parameter name="isH3" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="isU16" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="province" occurrence="none-or-one" default-value="&apos;&apos;"/>
				</parameters>
				<children>
					<text fixtext="1. ">
						<properties class="heading"/>
					</text>
					<condition>
						<children>
							<conditionbranch xpath="$lang=0">
								<children>
									<text fixtext="Verkiezing">
										<properties class="heading"/>
									</text>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<autocalc xpath="$RP_ChapterElection_Verkiezing">
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
							<conditionbranch xpath="$isH3 = 0">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isU16 = 0">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="Het gaat om de verkiezing van:"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ChapterElection_HetGaatOmVerkiezing"/>
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
																	<text fixtext="Het gaat om de verkiezing van de leden van:"/>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ChapterElection_HetGaatOmVerkiezingVanLeden"/>
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
							<conditionbranch>
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext="Het gaat om de kandidatenlijst voor de verkiezing van:"/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_ChapterElection_HetGaatOmKandidatenlijst"/>
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
					<calltemplate subtype="named" match="ElectionNameShort">
						<parameters>
							<parameter name="isBold" value="1"/>
						</parameters>
					</calltemplate>
					<condition>
						<children>
							<conditionbranch xpath="$province != &apos;&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext=" voor de provincie "/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_ChapterElection_VoorDeProvincie"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<template subtype="parameter" match="$province">
										<children>
											<content subtype="regular">
												<styles font-weight="bold"/>
											</content>
										</children>
										<variables/>
									</template>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<template subtype="element" match="eml:ElectionIdentifier">
						<children>
							<template subtype="element" filter=".=&apos;EK&apos;" match="eml:ElectionCategory">
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ChapterDistrictsOptions">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="eml:ContestIdentifier[@Id != &quot;alle&quot;]">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext="uitsluitend voor de volgende kieskring(en): "/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_ChapterDistrictOptions_uitsluitend"/>
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
													<text fixtext="voor "/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_ChapterDistrictOptions_voor"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext="alle kieskringen">
														<styles font-weight="bold"/>
													</text>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_ChapterDistrictOptions_alleKieskringen">
														<styles font-weight="bold"/>
													</autocalc>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<condition>
										<children>
											<conditionbranch xpath="$lang=0">
												<children>
													<text fixtext=" waarvoor de kandidatenlijst wordt ingeleverd."/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_ChapterDistrictOptions_waarvoorIngeleverd"/>
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
			<globaltemplate subtype="named" match="ChapterDistrictsTitle">
				<parameters/>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$lang=0">
								<children>
									<text fixtext="Kieskringen">
										<properties class="heading"/>
									</text>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<autocalc xpath="$RP_ChapterDistrictTitle_Kieskringen">
										<properties class="heading"/>
									</autocalc>
								</children>
							</conditionbranch>
						</children>
					</condition>
					<text fixtext=" ">
						<properties class="heading"/>
					</text>
					<condition>
						<children>
							<conditionbranch xpath="$lang = 0">
								<children>
									<text fixtext="(alleen invullen bij de verkiezing van de Tweede Kamer, Eerste Kamer, of de provinciale staten van een provincie met meer dan één kieskring)">
										<properties class="legend"/>
									</text>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<autocalc xpath="$RP_ChapterDistrictTitle_alleenInvullen">
										<properties class="legend"/>
									</autocalc>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="ChapterPartyName">
				<parameters>
					<parameter name="isH3" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<newline/>
					<newline/>
					<text fixtext="3. ">
						<properties class="heading"/>
					</text>
					<condition>
						<children>
							<conditionbranch xpath="($isH3 = 1) or ($isH3 = 2)">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="//eml:ElectionCategory[.!=&quot;BC&quot;]">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="Aanduiding van de politieke groepering">
																		<properties class="heading"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ChapterPartyName_AanduidingVan_H3">
																		<properties class="heading"/>
																	</autocalc>
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
																	<text fixtext="Aanduiding van de (politieke) groepering">
																		<properties class="heading"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ChapterPartyName_AanduidingVan_BC_H3">
																		<properties class="heading"/>
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
							<conditionbranch>
								<children>
									<condition>
										<children>
											<conditionbranch xpath="//eml:ElectionCategory[.!=&quot;BC&quot;]">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$lang=0">
																<children>
																	<text fixtext="Aanduiding van de politieke groepering">
																		<properties class="heading"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ChapterPartyName_AanduidingVan">
																		<properties class="heading"/>
																	</autocalc>
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
																	<text fixtext="Aanduiding van de (politieke) groepering">
																		<properties class="heading"/>
																	</text>
																</children>
															</conditionbranch>
															<conditionbranch>
																<children>
																	<autocalc xpath="$RP_ChapterPartyName_AanduidingVan_BC">
																		<properties class="heading"/>
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
					<condition>
						<children>
							<conditionbranch xpath="$isH3 = 1">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$lang = 0">
												<children>
													<text fixtext="De geregistreerde aanduiding van de politieke groepering: "/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_ChapterPartyName_geregistreerdeAanduiding"/>
												</children>
											</conditionbranch>
										</children>
									</condition>
								</children>
							</conditionbranch>
							<conditionbranch xpath="$isH3 = 2">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$lang = 0">
												<children>
													<text fixtext="De samengevoegde aanduiding van de politieke groeperingen: "/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_ChapterPartyName_samengevoegdeAanduiding"/>
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
											<conditionbranch xpath="$lang = 0">
												<children>
													<text fixtext="Aanduiding boven de kandidatenlijst: "/>
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<autocalc xpath="$RP_ChapterPartyName_AanduidingBoven"/>
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
					<template subtype="element" match="eml:Affiliation">
						<children>
							<calltemplate subtype="named" match="PartyName">
								<parameters>
									<parameter name="isBold" value="1"/>
								</parameters>
							</calltemplate>
						</children>
						<variables/>
					</template>
					<newline/>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="CandidatesOnListTable">
				<parameters>
					<parameter name="showDateOfBirth" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<template subtype="element" match="eml:Affiliation">
						<children>
							<tgrid>
								<children>
									<tgridbody-cols>
										<children>
											<tgridcol>
												<styles width="20pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="145pt"/>
											</tgridcol>
											<tgridcol>
												<styles width="105pt"/>
											</tgridcol>
											<tgridcol conditional-processing="$showDateOfBirth = 1">
												<styles width="76pt"/>
											</tgridcol>
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
														<styles vertical-align="top"/>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<styles vertical-align="top"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="naam">
																				<properties class="columnheader"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_CandidatesOnListTable-naam">
																				<properties class="columnheader"/>
																			</autocalc>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<styles vertical-align="top"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="voorletters">
																				<properties class="columnheader"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_CandidatesOnListTable-voorletters">
																				<properties class="columnheader"/>
																			</autocalc>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<styles vertical-align="top"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="geboortedatum">
																				<properties class="columnheader"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_CandidatesOnListTable-geboortedatum">
																				<properties class="columnheader"/>
																			</autocalc>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
														</children>
													</tgridcell>
													<tgridcell>
														<properties align="left"/>
														<styles vertical-align="top"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="$lang=0">
																		<children>
																			<text fixtext="woonplaats">
																				<properties class="columnheader"/>
																			</text>
																		</children>
																	</conditionbranch>
																	<conditionbranch>
																		<children>
																			<autocalc xpath="$RP_CandidatesOnListTable-woonplaats">
																				<properties class="columnheader"/>
																			</autocalc>
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
											<template subtype="element" match="eml:Candidate">
												<children>
													<tgridrow>
														<children>
															<tgridcell>
																<properties align="left" class="entry"/>
																<styles font-size="x-small" vertical-align="top"/>
																<children>
																	<template subtype="element" match="eml:CandidateIdentifier">
																		<children>
																			<template subtype="attribute" match="Id">
																				<children>
																					<content subtype="regular">
																						<styles font-size="x-small"/>
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
																<styles vertical-align="top"/>
																<children>
																	<template subtype="element" match="eml:CandidateFullName">
																		<children>
																			<calltemplate subtype="named" match="LastNameH1">
																				<parameters>
																					<parameter name="isBold"/>
																					<parameter name="fontSize" value="&apos;x-small&apos;"/>
																				</parameters>
																			</calltemplate>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
															<tgridcell>
																<properties align="left" class="entry"/>
																<styles font-size="x-small" vertical-align="top"/>
																<children>
																	<template subtype="element" match="eml:CandidateFullName">
																		<children>
																			<calltemplate subtype="named" match="FirstNameH1">
																				<parameters>
																					<parameter name="isBold"/>
																					<parameter name="fontSize" value="&apos;x-small&apos;"/>
																				</parameters>
																			</calltemplate>
																		</children>
																		<variables/>
																	</template>
																	<calltemplate subtype="named" match="GenderP1">
																		<parameters>
																			<parameter name="isBold"/>
																			<parameter name="fontSize" value="&apos;x-small&apos;"/>
																		</parameters>
																	</calltemplate>
																</children>
															</tgridcell>
															<tgridcell>
																<properties align="left" class="entry"/>
																<styles vertical-align="top"/>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="eml:DateOfBirth">
																				<children>
																					<template subtype="element" match="eml:DateOfBirth">
																						<children>
																							<calltemplate subtype="named" match="Date">
																								<parameters>
																									<parameter name="isBold"/>
																									<parameter name="fontSize" value="&apos;x-small&apos;"/>
																								</parameters>
																							</calltemplate>
																						</children>
																						<variables/>
																					</template>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<template subtype="element" match="kr:DateOfBirthAnnex">
																						<children>
																							<text fixtext="XX ">
																								<styles font-size="x-small"/>
																							</text>
																							<condition>
																								<children>
																									<conditionbranch xpath="substring(text(),4, 2) = &quot;01&quot;">
																										<children>
																											<text fixtext="januari">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="substring(text(),4, 2) = &quot;02&quot;">
																										<children>
																											<text fixtext="februari">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="substring(text(),4, 2) = &quot;03&quot;">
																										<children>
																											<text fixtext="maart">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="substring(text(),4, 2) = &quot;04&quot;">
																										<children>
																											<text fixtext="april">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="substring(text(),4, 2) = &quot;05&quot;">
																										<children>
																											<text fixtext="mei">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="substring(text(),4, 2) = &quot;06&quot;">
																										<children>
																											<text fixtext="juni">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="substring(text(),4, 2) = &quot;07&quot;">
																										<children>
																											<text fixtext="juli">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="substring(text(),4, 2) = &quot;08&quot;">
																										<children>
																											<text fixtext="augustus">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="substring(text(),4, 2) = &quot;09&quot;">
																										<children>
																											<text fixtext="september">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="substring(text(),4, 2) = &quot;10&quot;">
																										<children>
																											<text fixtext="oktober">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="substring(text(),4, 2) = &quot;11&quot;">
																										<children>
																											<text fixtext="november">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="substring(text(),4, 2)  = &quot;12&quot;">
																										<children>
																											<text fixtext="december">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="substring(text(),4, 2)  = &quot;XX&quot;">
																										<children>
																											<text fixtext="XXX">
																												<styles font-size="x-small"/>
																											</text>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<text fixtext=" ">
																								<styles font-size="x-small"/>
																							</text>
																							<autocalc xpath="substring(text(),7, 4)">
																								<styles font-size="x-small"/>
																							</autocalc>
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
																<styles vertical-align="top"/>
																<children>
																	<calltemplate subtype="named" match="QualifyingAddressCity">
																		<parameters>
																			<parameter name="isBold"/>
																			<parameter name="fontSize" value="&apos;x-small&apos;"/>
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
			</globaltemplate>
			<globaltemplate subtype="named" match="Filter0">
				<parameters>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="text() = &quot;0&quot;">
								<children>
									<text fixtext="________________">
										<styles line-height="13.5pt"/>
									</text>
								</children>
							</conditionbranch>
							<conditionbranch xpath="$isBold = 1">
								<children>
									<content subtype="regular">
										<styles font-weight="bold"/>
									</content>
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<content subtype="regular"/>
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="AffiliationVotes">
				<parameters>
					<parameter name="isP22" type="xs:boolean" default-value="false()"/>
				</parameters>
				<children>
					<paragraph paragraphtag="p">
						<children>
							<template subtype="element" match="rg:AffiliationVotes">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="position() != 1">
												<children>
													<calltemplate subtype="named" match="LineBreakPdf">
														<parameters/>
													</calltemplate>
												</children>
											</conditionbranch>
										</children>
									</condition>
									<newline/>
									<tgrid>
										<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
										<children>
											<tgridbody-cols>
												<children>
													<tgridcol>
														<styles width="160pt"/>
													</tgridcol>
													<tgridcol>
														<styles width="290pt"/>
													</tgridcol>
												</children>
											</tgridbody-cols>
											<tgridbody-rows>
												<children>
													<tgridrow>
														<styles height="13.5pt"/>
														<children>
															<tgridcell>
																<styles border-left-color="#C0C0C0" border-left-style="solid" border-left-width="1px" border-top-color="#C0C0C0" border-top-style="solid" border-top-width="1px" margin-bottom="4pt"/>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$isP22">
																				<children>
																					<text fixtext="Aanduiding politieke groepering">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<text fixtext="Partijnaam">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</tgridcell>
															<tgridcell>
																<styles border-right-color="#C0C0C0" border-right-style="solid" border-right-width="1px" border-top-color="#C0C0C0" border-top-style="solid" border-top-width="1px" margin-bottom="4pt"/>
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
															</tgridcell>
														</children>
													</tgridrow>
													<tgridrow>
														<children>
															<tgridcell>
																<styles border-left-color="#C0C0C0" border-left-style="solid" border-left-width="1px" margin-bottom="4pt"/>
																<children>
																	<text fixtext="Lijstnummer">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tgridcell>
															<tgridcell>
																<styles border-right-color="#C0C0C0" border-right-style="solid" border-right-width="1px" margin-bottom="4pt"/>
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
									<condition>
										<children>
											<conditionbranch xpath="rg:CandidateVotes">
												<children>
													<tgrid>
														<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
														<children>
															<tgridbody-cols>
																<children>
																	<tgridcol>
																		<styles width="60pt"/>
																	</tgridcol>
																	<tgridcol>
																		<styles width="312pt"/>
																	</tgridcol>
																	<tgridcol>
																		<styles width="78pt"/>
																	</tgridcol>
																</children>
															</tgridbody-cols>
															<tgridheader-rows>
																<children>
																	<tgridrow>
																		<children>
																			<tgridcell>
																				<properties align="left"/>
																				<styles border-bottom-color="#C0C0C0" border-bottom-style="solid" border-bottom-width="1px" border-left-color="#C0C0C0" border-left-style="solid" border-left-width="1px" border-right-color="#C0C0C0" border-right-style="solid" border-right-width="1px" border-top-color="#C0C0C0" border-top-style="solid" border-top-width="1px" vertical-align="top"/>
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$isP22">
																								<children>
																									<text fixtext="Volgnummer">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<text fixtext="Nummer">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																					<newline/>
																					<text fixtext="op de lijst">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</tgridcell>
																			<tgridcell>
																				<properties align="left"/>
																				<styles border-bottom-color="#C0C0C0" border-bottom-style="solid" border-bottom-width="1px" border-left-color="#C0C0C0" border-left-style="solid" border-left-width="1px" border-right-color="#C0C0C0" border-right-style="solid" border-right-width="1px" border-top-color="#C0C0C0" border-top-style="solid" border-top-width="1px" vertical-align="top"/>
																				<children>
																					<text fixtext="Naam kandidaat">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</tgridcell>
																			<tgridcell>
																				<styles border-bottom-color="#C0C0C0" border-bottom-style="solid" border-bottom-width="1px" border-left-color="#C0C0C0" border-left-style="solid" border-left-width="1px" border-right-color="#C0C0C0" border-right-style="solid" border-right-width="1px" border-top-color="#C0C0C0" border-top-style="solid" border-top-width="1px" text-align="left" vertical-align="top"/>
																				<children>
																					<text fixtext="Aantal stemmen">
																						<styles font-weight="bold"/>
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
																				<children>
																					<tgridcell>
																						<properties align="left"/>
																						<styles border-bottom-color="#C0C0C0" border-bottom-style="solid" border-bottom-width="1px" border-left-color="#C0C0C0" border-left-style="solid" border-left-width="1px" border-right-color="#C0C0C0" border-right-style="solid" border-right-width="1px" border-top-color="#C0C0C0" border-top-style="solid" border-top-width="1px" vertical-align="top"/>
																						<children>
																							<template subtype="element" match="eml:Candidate">
																								<children>
																									<template subtype="element" match="eml:CandidateIdentifier">
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
																						<properties align="left"/>
																						<styles border-bottom-color="#C0C0C0" border-bottom-style="solid" border-bottom-width="1px" border-left-color="#C0C0C0" border-left-style="solid" border-left-width="1px" border-right-color="#C0C0C0" border-right-style="solid" border-right-width="1px" border-top-color="#C0C0C0" border-top-style="solid" border-top-width="1px" vertical-align="top"/>
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
																									<calltemplate subtype="named" match="GenderInAffiliationVotes">
																										<parameters/>
																									</calltemplate>
																								</children>
																								<variables/>
																							</template>
																						</children>
																					</tgridcell>
																					<tgridcell>
																						<properties align="right"/>
																						<styles border-bottom-color="#C0C0C0" border-bottom-style="solid" border-bottom-width="1px" border-left-color="#C0C0C0" border-left-style="solid" border-left-width="1px" border-right-color="#C0C0C0" border-right-style="solid" border-right-width="1px" border-top-color="#C0C0C0" border-top-style="solid" border-top-width="1px" vertical-align="top"/>
																						<children>
																							<template subtype="element" match="rg:ValidVotes">
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
																		<variables/>
																	</template>
																	<tgridrow>
																		<children>
																			<tgridcell>
																				<styles border-bottom-color="#C0C0C0" border-bottom-style="solid" border-bottom-width="1px" border-left-color="#C0C0C0" border-left-style="solid" border-left-width="1px" border-right-color="#C0C0C0" border-right-style="solid" border-right-width="1px" border-top-color="#C0C0C0" border-top-style="solid" border-top-width="1px" text-align="right" vertical-align="top"/>
																			</tgridcell>
																			<tgridcell>
																				<styles border-bottom-color="#C0C0C0" border-bottom-style="solid" border-bottom-width="1px" border-left-color="#C0C0C0" border-left-style="solid" border-left-width="1px" border-right-color="#C0C0C0" border-right-style="solid" border-right-width="1px" border-top-color="#C0C0C0" border-top-style="solid" border-top-width="1px" padding-right="5pt" text-align="right" vertical-align="top"/>
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="$isP22">
																								<children>
																									<text fixtext="stemcijfer">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																							<conditionbranch>
																								<children>
																									<text fixtext="Totaal (stemcijfer)">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</conditionbranch>
																						</children>
																					</condition>
																				</children>
																			</tgridcell>
																			<tgridcell>
																				<properties align="right"/>
																				<styles border-bottom-color="#C0C0C0" border-bottom-style="solid" border-bottom-width="1px" border-left-color="#C0C0C0" border-left-style="solid" border-left-width="1px" border-right-color="#C0C0C0" border-right-style="solid" border-right-width="1px" border-top-color="#C0C0C0" border-top-style="solid" border-top-width="1px" vertical-align="top"/>
																				<children>
																					<template subtype="element" match="rg:ValidVotes">
																						<children>
																							<content subtype="regular">
																								<styles font-weight="bold"/>
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
												</children>
											</conditionbranch>
											<conditionbranch>
												<children>
													<tgrid>
														<properties border="1"/>
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
																		<styles vertical-align="top"/>
																		<children>
																			<tgridcell>
																				<properties align="left"/>
																				<children>
																					<text fixtext="Totale aantal op de lijst"/>
																					<newline/>
																					<text fixtext="uitgebrachte geldige stemmen"/>
																					<newline/>
																				</children>
																			</tgridcell>
																			<tgridcell>
																				<properties align="left"/>
																				<styles vertical-align="bottom"/>
																				<children>
																					<template subtype="element" match="rg:ValidVotes">
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
												</children>
											</conditionbranch>
										</children>
									</condition>
									<calltemplate subtype="named" match="LineBreakPdf">
										<parameters/>
									</calltemplate>
									<newline/>
									<newline break="page"/>
								</children>
								<variables/>
							</template>
						</children>
					</paragraph>
				</children>
			</globaltemplate>
			<globaltemplate subtype="named" match="WaterschapTypeName">
				<parameters>
					<parameter name="contestId" occurrence="none-or-one" default-value="//kr:ElectionDomain/@Id"/>
					<parameter name="isBold" type="xs:int" occurrence="none-or-one" default-value="0"/>
					<parameter name="font-size" type="xs:token" occurrence="none-or-one" default-value="&apos;small&apos;"/>
				</parameters>
				<children>
					<condition>
						<children>
							<conditionbranch xpath="$font-size = &apos;medium&apos;">
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold = 0">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$contestId = 2">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="wetterskip">
																						<styles font-size="medium"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_WaterschapTypeName_Wetterskip">
																						<styles font-size="medium"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="$contestId = 9 or $contestId = 10 or $contestId = 11">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="hoogheemraadschap">
																						<styles font-size="medium"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_WaterschapTypeName_Hoogheemraadschap">
																						<styles font-size="medium"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="$contestId = 12 or $contestId = 13 or $contestId = 14">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="hoogheemraadschap van">
																						<styles font-size="medium"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_WaterschapTypeName_Hoogheemraadschap_van">
																						<styles font-size="medium"/>
																					</autocalc>
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
																					<text fixtext="waterschap">
																						<styles font-size="medium"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_WaterschapTypeName_Waterschap">
																						<styles font-size="medium"/>
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
											<conditionbranch>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$contestId = 2">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="wetterskip">
																						<styles font-size="medium" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_WaterschapTypeName_Wetterskip">
																						<styles font-size="medium" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="$contestId = 9 or $contestId = 10 or $contestId = 11">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="hoogheemraadschap">
																						<styles font-size="medium" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_WaterschapTypeName_Hoogheemraadschap">
																						<styles font-size="medium" font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="$contestId = 12 or $contestId = 13 or $contestId = 14">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="hoogheemraadschap van">
																						<styles font-size="medium" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_WaterschapTypeName_Hoogheemraadschap_van">
																						<styles font-size="medium" font-weight="bold"/>
																					</autocalc>
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
																					<text fixtext="waterschap">
																						<styles font-size="medium" font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_WaterschapTypeName_Waterschap">
																						<styles font-size="medium" font-weight="bold"/>
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
								</children>
							</conditionbranch>
							<conditionbranch>
								<children>
									<condition>
										<children>
											<conditionbranch xpath="$isBold = 0">
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$contestId = 2">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="wetterskip"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_WaterschapTypeName_Wetterskip"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="$contestId = 9 or $contestId = 10 or $contestId = 11">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="hoogheemraadschap"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_WaterschapTypeName_Hoogheemraadschap"/>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="$contestId = 12 or $contestId = 13 or $contestId = 14">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="hoogheemraadschap van"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_WaterschapTypeName_Hoogheemraadschap_van"/>
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
																					<text fixtext="waterschap"/>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_WaterschapTypeName_Waterschap"/>
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
											<conditionbranch>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="$contestId = 2">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="wetterskip">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_WaterschapTypeName_Wetterskip">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="$contestId = 9 or $contestId = 10 or $contestId = 11">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="hoogheemraadschap">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_WaterschapTypeName_Hoogheemraadschap">
																						<styles font-weight="bold"/>
																					</autocalc>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</conditionbranch>
															<conditionbranch xpath="$contestId = 12 or $contestId = 13 or $contestId = 14">
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="$lang=0">
																				<children>
																					<text fixtext="hoogheemraadschap van">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_WaterschapTypeName_Hoogheemraadschap_van">
																						<styles font-weight="bold"/>
																					</autocalc>
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
																					<text fixtext="waterschap">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<autocalc xpath="$RP_WaterschapTypeName_Waterschap">
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
								</children>
							</conditionbranch>
						</children>
					</condition>
				</children>
			</globaltemplate>
		</children>
	</designfragments>
	<xmltables/>
	<authentic-custom-toolbar-buttons/>
</structure>
