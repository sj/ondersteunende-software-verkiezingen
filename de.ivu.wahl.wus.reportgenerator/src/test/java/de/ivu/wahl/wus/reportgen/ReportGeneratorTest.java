/*
 * ReportGeneratorTest
 * 
 * Created on 19.11.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.transform.Source;

import org.junit.Test;

import de.ivu.wahl.wus.electioncategory.ElectionCategory;
import de.ivu.wahl.wus.electioncategory.ElectionSubcategory;
import de.ivu.wahl.wus.reportgen.ReportConfiguration.ExportEml;
import de.ivu.wahl.wus.reportgen.extension.IPDFOpener;

public class ReportGeneratorTest {
  // private static final String OUTPUT_PATH =
  // "/de/ivu/wahl/wus/reportgen/sps/";
  // private static final String OUTPUT_PATH =
  // "C:\\Documents and Settings\\jon\\My Documents\\";
  private static final String DOUBLE_QUOTE = "\"";
  private static final String OUTPUT_PATH = "D:\\temp\\rg-out\\";
  private static final IPDFOpener PDF_READER = new IPDFOpener() {
    public void openPDFReader(String pDocumentPath) {
      try {
        String commandLine = DOUBLE_QUOTE
            + "C:\\Program Files (x86)\\Adobe\\Reader 9.0\\Reader\\AcroRd32.exe" + DOUBLE_QUOTE
            + " " + DOUBLE_QUOTE + pDocumentPath + DOUBLE_QUOTE;
        Runtime.getRuntime().exec(commandLine);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  };

  @Test
  public void testWithDomSource() {
    Date timestamp = new Date();
    boolean openDocumentViewer = true;
    boolean blanko = true;
    boolean isDraft = true;

    int program = 1;
    Source src = DefaultXmlSourceProvider.instance.getDomXmlSource(program, blanko);

    create3Formats(src, ReportTemplateEnum.H1, timestamp, isDraft, openDocumentViewer, program);
    create3Formats(src, ReportTemplateEnum.H3_1, timestamp, isDraft, openDocumentViewer, program);
    create3Formats(src, ReportTemplateEnum.H3_2, timestamp, isDraft, openDocumentViewer, program);
    create3Formats(src, ReportTemplateEnum.H4, timestamp, isDraft, openDocumentViewer, program);
    create3Formats(src, ReportTemplateEnum.H9, timestamp, isDraft, openDocumentViewer, program);
    create3Formats(src, ReportTemplateEnum.Y13, timestamp, isDraft, openDocumentViewer, program);
    create3Formats(src, ReportTemplateEnum.Y35, timestamp, isDraft, openDocumentViewer, program);

    program = 2;
    src = DefaultXmlSourceProvider.instance.getDomXmlSource(program, blanko);
    create3Formats(src, ReportTemplateEnum.I1, timestamp, isDraft, openDocumentViewer, program);
    create3Formats(src, ReportTemplateEnum.OSV2_1, timestamp, isDraft, openDocumentViewer, program);
    create3Formats(src, ReportTemplateEnum.I4, timestamp, isDraft, openDocumentViewer, program);

    program = 3;
    src = DefaultXmlSourceProvider.instance.getDomXmlSource(program, blanko);
    create3Formats(src, ReportTemplateEnum.OSV3_2, timestamp, isDraft, openDocumentViewer, program);
    create3Formats(src, ReportTemplateEnum.OSV3_4, timestamp, isDraft, openDocumentViewer, program);

    program = 4;
    src = DefaultXmlSourceProvider.instance.getDomXmlSource(program, blanko);
    create3Formats(src, ReportTemplateEnum.O3, timestamp, isDraft, openDocumentViewer, program);
    create3Formats(src, ReportTemplateEnum.N11, timestamp, isDraft, openDocumentViewer, program);

    src = DefaultXmlSourceProvider.instance.getDomEml230bSource();
    // create3Formats(src, ReportTemplateEnum.OSV3_3, timestamp, isDraft, openDocumentViewer,
    // program);
  }

  // @Test
  public void testI4PdfAndOpenDocumentViewer() {
    int program = 2;
    Source src = DefaultXmlSourceProvider.instance.getDomXmlSource(program, false);
    try {
      ReportConfigurer rc = new ReportConfigurer();
      rc.setDocument(ReportTemplateEnum.I4, ReportOutputFormatEnum.PDF);
      rc.configureFileName(new File(OUTPUT_PATH), getReportNameComponents(program), false);
      rc.configureWhichFiles(ExportEml.OVERWRITE, true);
      rc.setPdfOpener(PDF_READER);

      ReportGenerator inst = new ReportGeneratorImpl();
      inst.createReport(rc.getReportConfiguration(), src);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testI17_3RTF() {
    int program = 3;
    Source src = DefaultXmlSourceProvider.instance.getDomXmlSource(program, false);
    try {
      // Setup directories
      ReportConfigurer rc = new ReportConfigurer();
      rc.setDocument(ReportTemplateEnum.OSV3_4, ReportOutputFormatEnum.RTF);
      rc.configureFileName(new File(OUTPUT_PATH), getReportNameComponents(program), false);
      rc.configureWhichFiles(ExportEml.OVERWRITE, true);

      ReportGenerator inst = new ReportGeneratorImpl();
      inst.createReport(rc.getReportConfiguration(), src);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * @param src
   * @param reportTemplate
   * @param timestamp
   * @param isDraft
   * @param openDocumentViewer
   * @param program
   */
  private void create3Formats(Source src,
      ReportTemplateEnum reportTemplate,
      Date timestamp,
      boolean isDraft,
      boolean openDocumentViewer,
      int program) {
    try {
      ReportGenerator inst = new ReportGeneratorImpl();

      // Setup directories
      // File outputPath = new
      // File(getClass().getResource(OUTPUT_PATH).getFile());
      ReportNameComponents reportNameComponents = getReportNameComponents(program);
      if (ReportTemplateEnum.OSV3_3.equals(reportTemplate)) {
        ReportNameComponentsEml230b eml230bNameComponents = new ReportNameComponentsEml230b();
        eml230bNameComponents.setElectionCategory(ElectionCategory.EP);
        eml230bNameComponents.setElectionSubcategory(ElectionSubcategory.EP);
        eml230bNameComponents.setElectionIdentifier("EP2009");
        eml230bNameComponents.setElectoralDistrict("Limburg");
        reportNameComponents = eml230bNameComponents;
      }

      ReportConfigurer rc = new ReportConfigurer();
      rc.setDocument(reportTemplate, ReportOutputFormatEnum.PDF);
      rc.configureFileName(new File(OUTPUT_PATH), reportNameComponents, isDraft);
      rc.configureWhichFiles(ExportEml.OVERWRITE, true);
      rc.setTimestamp(timestamp);
      if (openDocumentViewer) {
        rc.setPdfOpener(PDF_READER);
      }

      ReportConfiguration pdfConfig = rc.getReportConfiguration();
      // ReportConfiguration htmlConfig =
      rc.setDocument(reportTemplate, ReportOutputFormatEnum.RTF);
      ReportConfiguration rtfConfig = rc.getReportConfiguration();
      List<String> path = inst.createReports(Arrays.asList(pdfConfig, rtfConfig), src);
      System.out.println(path);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private ReportNameComponents getReportNameComponents(int program) {
    if (program == 1) {
      ReportNameComponentsP1 result = new ReportNameComponentsP1();
      result.setElectionIdentifier("EP2009");
      result.setListName("Hermans Lijst");
      result.setSubmitterName("\u0106osi\u0107");
      return result;
    } else if (program == 2) {
      ReportNameComponentsP2 result = new ReportNameComponentsP2();
      result.setElectionIdentifier("EP2009");
      result.setElectoralDistrict("Limburg");
      return result;
    } else if (program == 3) {
      ReportNameComponentsP3 result = new ReportNameComponentsP3();
      result.setElectionIdentifier("EP2009");
      return result;
    } else {
      ReportNameComponentsP4 result = new ReportNameComponentsP4(true);
      result.setElectionIdentifier("EP2009");
      result.setElectionDomain(null);
      return result;
    }
  }
}
