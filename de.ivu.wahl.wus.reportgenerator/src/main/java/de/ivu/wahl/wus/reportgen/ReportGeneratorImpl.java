/*
 * ReportGeneratorImpl
 * 
 * Created on 11.11.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import nu.xom.Document;
import nu.xom.converters.DOMConverter;

import org.apache.fop.apps.Fop;
import org.apache.log4j.Logger;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import de.ivu.wahl.wus.loggerinterface.IUserActionLogger;
import de.ivu.wahl.wus.reportgen.ReportConfiguration.ExportEml;
import de.ivu.wahl.wus.reportgen.i18n.MessageKeys;
import de.ivu.wahl.wus.reportgen.i18n.Messages;
import de.ivu.wahl.wus.xmlsecurity.IXmlDigestCreator;
import de.ivu.wahl.wus.xmlsecurity.SHAXmlDigestCreator;
import de.ivu.wahl.wus.xmlsecurity.XmlParseException;

public class ReportGeneratorImpl implements ReportGenerator {
  static final String TEMPLATE_PATH = "/de/ivu/wahl/wus/reportgen/templates/"; //$NON-NLS-1$
  static final String SHORT_TEMPLATE_PATH = "./templates/"; //$NON-NLS-1$
  static final String TEXT_PATH = "/de/ivu/wahl/wus/reportgen/text/"; //$NON-NLS-1$
  static final String SHORT_TEXT_PATH = "./text/"; //$NON-NLS-1$
  private static final String P1_RG_FILTER_STYLESHEET_NAME = "rg-filter-P1.xslt"; //$NON-NLS-1$
  private static final String PX_RG_FILTER_STYLESHEET_NAME = "rg-filter-Px.xslt"; //$NON-NLS-1$
  private static final String RG_PREPROCESS_OSV3_2_STYLESHEET_NAME = "rg-preprocess-osv3-2.xslt"; //$NON-NLS-1$
  private static final String GENERATION_DATE = "generateDate"; //$NON-NLS-1$
  private static final String HASH_CODE = "hashCode"; //$NON-NLS-1$
  private static final String IS_DRAFT = "isDraft"; //$NON-NLS-1$
  private static final String IS_INCOMPLETE = "isIncomplete"; //$NON-NLS-1$
  private static final String TRUE = "true"; //$NON-NLS-1$
  private static final String PARAMETER1 = "parameter1"; //$NON-NLS-1$

  private static final Logger log = Logger.getLogger(ReportGenerator.class);
  private IUserActionLogger userActionLogger = null;

  private final boolean debug = "true".equals(System.getProperty("rgdebug")); //$NON-NLS-1$ $NON-NLS-2$
  private final int debugLevel = 1;

  public ReportGeneratorImpl() {
  }

  public ReportGeneratorImpl(IUserActionLogger userActionLogger) {
    this.userActionLogger = userActionLogger;
  }

  public String createReport(ReportConfiguration config, Source src) throws IOException {
    checkReportConfig(config);

    SourceAndHash sourceAndHash = removeRgElementsAndCanonize(config, src);
    ReportConfiguration configurationWithHash = config.copyWithHashCode(sourceAndHash.hashCode);
    FilenameProvider fp = new FilenameProvider(configurationWithHash);

    if (!config.overwriteAllDocuments()) {
      checkIfDocumentsExist(fp, configurationWithHash);
    }

    // regular EML
    if (!ExportEml.NO.equals(config.exportEml())) {
      serializeEml(config, sourceAndHash, fp);
    }

    if (config.generatesDocument()) {
      if (debug && debugLevel > 1) {
        serializeAdditionalEmlFiles(config, src, sourceAndHash, fp);
      }

      // Preprocessed EML (with RG elements, transformed by rg-preprocess*.xslt). This is used to
      // generate the documents.
      Source preprocessedSource = preprocessEml(src, configurationWithHash);
      if (debug) {
        serializePreprocessedEml(configurationWithHash, fp, preprocessedSource);
      }

      fp.getOutputSubdirectory().mkdirs();
      MultiReportConfiguration multiReportConfiguration = configurationWithHash.getMultiReportConfiguration();
      if (multiReportConfiguration != null) {
        for (Entry<String, String> entry : multiReportConfiguration.getNamesAndParameters1()
            .entrySet()) {
          String name = entry.getKey();
          String param = entry.getValue();
          Map<String, String> additionalParameters = new HashMap<String, String>();
          additionalParameters.put(PARAMETER1, param);

          generateDocument(configurationWithHash,
              preprocessedSource,
              fp.getOutputFile(name),
              additionalParameters);
        }
      } else {
        Map<String, String> additionalParameters = null;
        if (configurationWithHash.getTemplate().isIncomplete()) {
          additionalParameters = new HashMap<String, String>();
          additionalParameters.put(IS_INCOMPLETE, TRUE);
        }
        generateDocument(configurationWithHash,
            preprocessedSource,
            fp.getOutputFile(),
            additionalParameters);
      }
    }

    return fp.getOutputDirectory().toString();
  }

  private void serializeEml(ReportConfiguration config,
      SourceAndHash sourceAndHash,
      FilenameProvider fp) {
    File emlFile = fp.getOutputEmlFile(RgConstants.SUFFIX_EML_XML);
    deleteIfNecessary(emlFile, config);
    if (emlFile.exists() && ExportEml.OVERWRITE.equals(config.exportEml())) {
      throw new RuntimeException("EML file could not be deleted: " + emlFile);
    }
    fp.getEmlDirectory().mkdirs();
    createEmlIfNecessary(sourceAndHash.canonizedEml, sourceAndHash.hashCode, emlFile);
  }

  private void generateDocument(ReportConfiguration configurationWithHash,
      Source preprocessedSource,
      File output,
      Map<String, String> additionalParameters) {
    ReportOutputFormatEnum format = configurationWithHash.getFormat();
    if (ReportOutputFormatEnum.PDF.equals(format)) {
      // Setup input for XSLT transformation
      createPdfReportFromSource(configurationWithHash,
          preprocessedSource,
          output,
          additionalParameters);
    } else if (ReportOutputFormatEnum.RTF.equals(format)) {
      processXSLTtoRTF(configurationWithHash, preprocessedSource, output, additionalParameters);
    } else if (ReportOutputFormatEnum.HTML.equals(format)) {
      processXSLTtoHTML(configurationWithHash, preprocessedSource, output, additionalParameters);
    } else {
      String message = Messages.bind(MessageKeys.UNEXPECTED_OUTPUT_FORMAT, format);
      log.error(message);
      assert false : message;
    }
  }

  /**
   * Serializes the EML after the pre-processing. Only for debugging purpose.
   */
  private void serializePreprocessedEml(ReportConfiguration config,
      FilenameProvider fp,
      Source ppSource) throws TransformerFactoryConfigurationError {
    File emlPpFile = fp.getOutputEmlFile("." + config.getTemplate().getFilePrefix() + ".emlpp.xml"); //$NON-NLS-1$ $NON-NLS-2$
    try {
      deleteIfExists(emlPpFile);
    } catch (SecurityException exc) {
      // Not serious, but write trace to the log
      exc.printStackTrace();
    }
    if (!emlPpFile.exists()) {
      fp.getEmlDirectory().mkdirs();
      log.info("Creating EML file: " + emlPpFile);
      new Serializer().serialize(ppSource, emlPpFile);
    }
  }

  /**
   * Serializes additional EML files (with RG elements, non-canonized) for debugging purpose.
   */
  private void serializeAdditionalEmlFiles(ReportConfiguration config,
      Source src,
      SourceAndHash sourceAndHash,
      FilenameProvider fp) throws TransformerFactoryConfigurationError {
    // EML as created by the program (including RG elements)
    File emlRgFile = fp.getOutputEmlFile(".emlrg.xml"); //$NON-NLS-1$
    deleteIfNecessary(emlRgFile, config);
    if (!emlRgFile.exists()) {
      fp.getEmlDirectory().mkdirs();
      log.info("Creating EML file: " + emlRgFile);
      new Serializer().serialize(src, emlRgFile);
    }

    // EML without RG elements, but not yet canonized
    File emlNcFile = fp.getOutputEmlFile(".emlnc.xml"); //$NON-NLS-1$
    deleteIfNecessary(emlNcFile, config);
    if (!emlNcFile.exists() || ExportEml.OVERWRITE.equals(config.exportEml())) {
      fp.getEmlDirectory().mkdirs();
      Source src1 = new DOMSource(sourceAndHash.emlWithoutRgElements);
      log.info("Creating EML file: " + emlNcFile);
      new Serializer().serialize(src1, emlNcFile);
    }
  }

  /**
   * Check if one of the documents that shall be created already exist. If so, throw a
   * {@link GeneratedFileExistsException}.
   * 
   * @param config
   * @param fp
   * @throws GeneratedFileExistsException
   */
  private void checkIfDocumentsExist(FilenameProvider fp, ReportConfiguration config) {
    Collection<File> files = getFilesToGenerate(fp, config);
    List<String> filesThatExist = new ArrayList<String>();
    for (File file : files) {
      if (file.exists()) {
        filesThatExist.add(file.toString());
      }
    }
    if (!filesThatExist.isEmpty()) {
      throw new GeneratedFileExistsException(filesThatExist);
    }
  }

  /**
   * @return The list of the files that shall be generated (excluding those that are only generated
   *         in debug mode)
   */
  private Collection<File> getFilesToGenerate(FilenameProvider fp, ReportConfiguration config) {
    Collection<File> result = new ArrayList<File>();
    if (!ExportEml.NO.equals(config.exportEml())) {
      File emlFile = fp.getOutputEmlFile(RgConstants.SUFFIX_EML_XML);
      result.add(emlFile);
    }
    if (config.generatesDocument()) {
      MultiReportConfiguration mrc = config.getMultiReportConfiguration();
      if (mrc != null) {
        Set<String> keys = mrc.getNamesAndParameters1().keySet();
        for (String key : keys) {
          result.add(fp.getOutputFile(key));
        }
      } else {
        result.add(fp.getOutputFile());
      }
    }

    return result;
  }

  private void deleteIfNecessary(File file, ReportConfiguration config) {
    if (file.exists() && ExportEml.OVERWRITE.equals(config.exportEml())) {
      try {
        file.delete();
      } catch (SecurityException exc) {
        log.info("File could not be deleted: " + file, exc); //$NON-NLS-1$
      }
    }
  }

  private void deleteIfExists(File file) {
    if (file.exists()) {
      try {
        file.delete();
      } catch (SecurityException exc) {
        log.info("File could not be deleted: " + file, exc); //$NON-NLS-1$
      }
    }
  }

  public String createReport(ReportConfiguration config, Document eml)
      throws IOException, RuntimeException, ParserConfigurationException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    DOMImplementation impl = builder.getDOMImplementation();
    org.w3c.dom.Document convertedEML = DOMConverter.convert(eml, impl);
    DOMSource source = new DOMSource(convertedEML);

    return createReport(config, source);
  }

  public String createEML230bReport(ReportConfiguration config, org.w3c.dom.Document eml)
      throws IOException, RuntimeException, ParserConfigurationException {
    DOMSource source = new DOMSource(eml);

    return createReport(config, source);
  }

  /**
   * Modify the given EML document. Determine the EML node and add the attribute
   * "xsi:schemaLocation" with a value that corresponds to the type of EML document.
   * 
   * @param emlWithoutRgElements the document node of the EML message
   */
  private void addSchemaLocation(Node emlWithoutRgElements, ReportConfiguration config) {
    Node emlNode = emlWithoutRgElements.getFirstChild();
    org.w3c.dom.Document ownerDocument = emlNode.getOwnerDocument();
    String createdByOsv = MessageFormat.format(ReportGenerator.CREATED_BY_OSV_PROGRAM_VERSION,
        config.getProgramName(),
        config.getProgramVersion());
    emlNode.insertBefore(ownerDocument.createComment(createdByOsv), emlNode.getFirstChild());

    assert emlNode instanceof Element : "emlNode should be an Element"; //$NON-NLS-1$
    Element emlElement = (Element) emlNode;
    String id = emlElement.getAttribute("Id"); //$NON-NLS-1$
    assert id != null && id.length() > 0 : "attribute must not be null : EML node has no Id."; //$NON-NLS-1$ $NON-NLS-2$
    EMLSchemaFile schema = EMLMessageType.getEMLSchemaFileById(id);
    assert schema != null : "Unexpected id of EML element: " + id; //$NON-NLS-1$

    emlElement.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", //$NON-NLS-1$
        "xsi:schemaLocation", //$NON-NLS-1$
        "urn:oasis:names:tc:evs:schema:eml " + schema.getFilename() + " http://www.kiesraad.nl/extensions kiesraad-eml-extensions.xsd"); //$NON-NLS-1$ $NON-NLS-2$
  }

  /**
   * If necessary perform a preprocessing of the EML message before generating the document.
   * 
   * @param src
   * @param config
   * @return
   */
  private Source preprocessEml(Source src, ReportConfiguration config) {
    String prepocessingStylesheetName = getPreprocessingStylesheetName(config);
    if (prepocessingStylesheetName == null) {
      return src;
    }
    StreamSource stylesheet = getTemplateStream(prepocessingStylesheetName);
    DOMResult result = new DOMResult();
    new XsltTransformer().processXSLT(src,
        result,
        stylesheet,
        null,
        config.getLanguage(),
        config.getExportSelectionPath());
    return new DOMSource(result.getNode());
  }

  private String getPreprocessingStylesheetName(ReportConfiguration config) {
    if (ReportTemplateEnum.OSV3_2.equals(config.getTemplate())
        || ReportTemplateEnum.I4.equals(config.getTemplate())
        || ReportTemplateEnum.I4_O.equals(config.getTemplate())) {
      if (!config.getReportNameComponents().getElectionIdentifier().startsWith("EP")) {
        return RG_PREPROCESS_OSV3_2_STYLESHEET_NAME;
      }
    }
    return null;
  }

  public List<String> createReports(Collection<ReportConfiguration> configs, Source source)
      throws IOException, RuntimeException {
    assert configs != null : "configs must not be null";
    assert source != null : "source must not be null";

    List<String> result = new ArrayList<String>();
    for (ReportConfiguration config : configs) {
      String path = createReport(config, source);
      if (!result.contains(path)) {
        result.add(path);
      }
    }
    return result;
  }

  private void createEmlIfNecessary(InputStream src, String hashCode, File emlFile) {
    if (emlFile.exists()) {
      return;
    }

    FileOutputStream out = null;
    try {
      // logging
      String logMsgEmlFile = "Creating EML file: " + emlFile;
      String logMsgHashCode = "SHA-256-Hashcode: " + hashCode;
      if (userActionLogger != null) {
        userActionLogger.logInfo(logMsgEmlFile);
        userActionLogger.logInfo(logMsgHashCode);
      }
      log.info(logMsgEmlFile);
      log.info(logMsgHashCode);

      // file IO
      out = new FileOutputStream(emlFile);
      StreamCopy.copy(src, out);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {
      if (out != null) {
        try {
          out.close();
        } catch (IOException e) {
          e.printStackTrace();
          throw new RuntimeException(e);
        }
      }
    }
  }

  /**
   * @throws IllegalArgumentException if the ReportConfiguration is <code>null</code> or mandatory
   *           attributes of the config are <code>null</code>
   */
  private void checkReportConfig(ReportConfiguration config) {
    if (config == null) {
      throw new IllegalArgumentException("createReport() called with config == null");
    } else if (config.getExportSelectionPath() == null) {
      throw new IllegalArgumentException(
          "createReport() called with config.getOutputPath() == null");
    } else if (config.getTemplate() == null) {
      throw new IllegalArgumentException("createReport() called with config.getTemplate() == null");
    } else if (config.getFormat() == null) {
      throw new IllegalArgumentException("createReport() called with config.getFormat() == null");
    } else if (config.getTimestamp() == null) {
      throw new IllegalArgumentException("createReport() called with config.getTimestamp() == null");
    } else if (config.getReportNameComponents() == null) {
      throw new IllegalArgumentException(
          "createReport() called with config.getReportNameComponents() == null");
    } else if (config.getReportNameComponents().getElectionIdentifier() == null) {
      throw new IllegalArgumentException(
          "createReport() called with config.getReportNameComponents().getElectionIdentifier() == null");
    }
  }

  /**
   * Process XML to RTF using XSLT-Transformer and RTFUnicodeFilter.
   */
  private void processXSLTtoRTF(ReportConfiguration config,
      Source src,
      File output,
      Map<String, String> additionalParameters) {
    try {
      log.info("Preparing...");
      FileWriter writer = new FileWriter(output);

      String logMsgRtfFile = "Output: RTF (" + output + ")";
      if (userActionLogger != null) {
        userActionLogger.logInfo(logMsgRtfFile);
      }
      log.info(logMsgRtfFile);
      log.info("");
      log.info("Transforming...");

      try {
        StreamResult result = new StreamResult(new RTFUnicodeFilter(writer));
        StreamSource stylesheet = getTemplateStream(config.getStylesheetName());
        Map<String, String> params = getXsltTransformerParameters(config, additionalParameters);
        new XsltTransformer().processXSLT(src,
            result,
            stylesheet,
            params,
            config.getLanguage(),
            config.getExportSelectionPath());
        log.info("Success!");
      } finally {
        writer.close();
      }
    } catch (IOException e) {
      log.error("Cannot create output file" + output);
      throw new RuntimeException(e);
    }
  }

  /**
   * Process XML to RTF using XSLT-Transformer.
   * 
   * @param pAdditionalParameters
   */
  private void processXSLTtoHTML(ReportConfiguration config,
      Source src,
      File output,
      Map<String, String> additionalParameters) {
    try {
      OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(output), "UTF-8"); //$NON-NLS-1$
      try {
        StreamResult result = new StreamResult(writer);
        StreamSource stylesheet = getTemplateStream(config.getStylesheetName());
        Map<String, String> params = getXsltTransformerParameters(config, additionalParameters);
        new XsltTransformer().processXSLT(src,
            result,
            stylesheet,
            params,
            config.getLanguage(),
            config.getExportSelectionPath());
      } finally {
        writer.close();
      }
    } catch (IOException e) {
      log.error("Cannot create output file" + output);
      throw new RuntimeException(e);
    }
  }

  /**
   * Process XML to PDF using XSLT-Transformer and properly configured FOP.
   * 
   * @param additionalParameters
   */
  private void createPdfReportFromSource(ReportConfiguration config,
      Source src,
      File output,
      Map<String, String> additionalParameters) {
    try {
      log.info("Preparing...");
      StreamSource stylesheet = getTemplateStream(config.getStylesheetName());

      String logMsgPdfFile = "Output: PDF (" + output + ")";
      if (userActionLogger != null) {
        userActionLogger.logInfo(logMsgPdfFile);
      }
      log.info(logMsgPdfFile);
      log.info("");
      log.info("Transforming...");

      // Setup output
      OutputStream out = new BufferedOutputStream(new FileOutputStream(output));

      try {
        // provide a ready configured FOP
        Fop fop = new FopProvider().provideFop(out);

        // Resulting SAX events (the generated FO) must be piped through to FOP
        Result result = new SAXResult(fop.getDefaultHandler());

        Map<String, String> params = getXsltTransformerParameters(config, additionalParameters);
        new XsltTransformer().processXSLT(src,
            result,
            stylesheet,
            params,
            config.getLanguage(),
            config.getExportSelectionPath());
      } finally {
        out.close();
      }

      log.info("Success!");

      if (config.openDocumentInViewer() && config.getPdfOpener() != null) {
        log.info("Start PDF reader!");
        config.getPdfOpener().openPDFReader(output.toString());
      }
    } catch (Exception e) {
      e.printStackTrace(System.err);
      log.error("Exception in Formatting Object Processing", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * @return Parameter Map needed for the XSLT Stylesheets for RG
   */
  private Map<String, String> getXsltTransformerParameters(ReportConfiguration config,
      Map<String, String> additionalParameters) {
    Map<String, String> params = new HashMap<String, String>();
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); //$NON-NLS-1$
    DateFormat tf = SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM);
    Date timestamp = config.getTimestamp();
    String formattedTimestamp = df.format(timestamp) + " " + tf.format(timestamp); //$NON-NLS-1$

    params.put(GENERATION_DATE, formattedTimestamp);
    params.put(HASH_CODE, config.getShaHashCode());
    params.put(IS_DRAFT, String.valueOf(config.isDraft()));

    if (additionalParameters != null) {
      params.putAll(additionalParameters);
    }
    return params;
  }

  /**
   * @return A File resource in the TEMPLATE_PATH as XML StreamSource.
   */
  private StreamSource getTemplateStream(String templateName) {
    String resourceName = TEMPLATE_PATH + templateName;

    // Setup input and output files
    log.info("Stylesheet: " + resourceName);

    URL resource = getClass().getResource(resourceName);
    if (resource == null) {
      throw new RuntimeException("Could not find the XSLT template with the resource name "
          + resourceName);
    }
    try {
      return new StreamSource(resource.openStream());
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  /**
   * @return Result object containing the hash code (as String), the src without RG elements (as
   *         Node) and the final canonized XML (as InputStream)
   */
  private SourceAndHash removeRgElementsAndCanonize(ReportConfiguration config, Source src) {
    try {
      DOMResult domResult = new DOMResult();
      StreamSource stylesheet = getTemplateStream(PX_RG_FILTER_STYLESHEET_NAME);
      boolean isProgram1 = ReportGeneratingProgram.P1.equals(config.getReportNameComponents()
          .getProgramId());
      if (isProgram1) {
        stylesheet = getTemplateStream(P1_RG_FILTER_STYLESHEET_NAME);
      }
      new XsltTransformer().processXSLT(src,
          domResult,
          stylesheet,
          null,
          config.getLanguage(),
          config.getExportSelectionPath());
      Node emlWithoutRgElements = domResult.getNode();
      if (!ReportGeneratingProgram.MTP.equals(config.getReportNameComponents().getProgramId())) {
        addSchemaLocation(emlWithoutRgElements, config);
      }
      DOMSource domSource = new DOMSource(emlWithoutRgElements);

      IXmlDigestCreator digestCreator = new SHAXmlDigestCreator();
      String hashCode = digestCreator.createDigest(domSource);
      InputStream canonicalXmlContent = digestCreator.getCanonicalXmlContent();

      return new SourceAndHash(canonicalXmlContent, emlWithoutRgElements, hashCode);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } catch (XmlParseException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  /**
   * Inner class. Result object for method #removeRgElementsAndCanonize(Source).
   */
  private static class SourceAndHash {
    final InputStream canonizedEml;
    final Node emlWithoutRgElements;
    final String hashCode;

    private SourceAndHash(InputStream source, Node emlWithoutRgElements, String hashCode) {
      this.canonizedEml = source;
      this.emlWithoutRgElements = emlWithoutRgElements;
      this.hashCode = hashCode;
    }
  }

  public String createMTP1Report(ReportConfigurationMTP config, Source src)
      throws IOException, RuntimeException {

    checkReportConfig(config);

    SourceAndHash sourceAndHash = removeRgElementsAndCanonize(config, src);
    ReportConfiguration configurationWithHash = config.copyWithHashCode(sourceAndHash.hashCode);

    File output = config.getExportSelectionPath();
    output.getParentFile().mkdirs();

    Source preprocessedSource = preprocessEml(src, configurationWithHash);

    generateDocument(configurationWithHash, preprocessedSource, output, null);

    return output.toString();
  }

  public String createCsvExportFile(ReportConfiguration config, String csv)
      throws IOException, RuntimeException {
    checkReportConfig(config);

    FilenameProvider fp = new FilenameProvider(config);
    if (!config.overwriteAllDocuments()) {
      checkIfDocumentsExist(fp, config);
    }

    fp.getOutputSubdirectory().mkdirs();
    File output = fp.getOutputFile();

    try {
      OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(output), "UTF-8"); //$NON-NLS-1$
      try {
        writer.write('\ufeff'); // UTF-8 byte order marker (BOM), see OSV-1908
        writer.append(csv);
      } finally {
        writer.close();
      }
    } catch (IOException e) {
      log.error("Cannot create output file " + output);
      if (userActionLogger != null) {
        userActionLogger.logInfo(e.getMessage());
      }
      throw new RuntimeException(e.getMessage(), e);
    }

    return output.toString();
  }

}
