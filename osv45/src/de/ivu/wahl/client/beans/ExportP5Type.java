/*
 * Created on 26.08.2010
 * Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import de.ivu.wahl.admin.P5ExportStateCandidateAddress;
import de.ivu.wahl.admin.P5ExportStateKanBen;
import de.ivu.wahl.admin.P5ExportStateP22_1;
import de.ivu.wahl.admin.P5ExportStateP22_1Appendix;
import de.ivu.wahl.admin.P5ExportStateP22_2;
import de.ivu.wahl.admin.P5ExportStateP22_2Appendix;
import de.ivu.wahl.admin.P5ExportStateU16;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.wus.reportgen.ReportOutputFormatEnum;
import de.ivu.wahl.wus.reportgen.ReportTemplateEnum;

/**
 * Summary of the most important properties of the different exports of P4.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
enum ExportP5Type implements ExportP5Commands {
  P22_1(CMD_ADM_EXPORT_P22_1, P5ExportStateP22_1.STATUS_P22_1_D3,
      MessageKeys.Error_P22_KonnteNichtExportiertWerden, null, ReportTemplateEnum.P22_1),

  P22_2(CMD_ADM_EXPORT_P22_2, P5ExportStateP22_2.STATUS_P22_2_D3,
      MessageKeys.Error_P22_KonnteNichtExportiertWerden, null, ReportTemplateEnum.P22_2),

  U16(CMD_ADM_EXPORT_U16, P5ExportStateU16.STATUS_U16_D3,
      MessageKeys.Error_U16_KonnteNichtExportiertWerden, null, ReportTemplateEnum.U16),

  // Also used to create the appendix to U16
  P22_1_APPENDIX(CMD_ADM_EXPORT_P22_1_APPENDIX,
      P5ExportStateP22_1Appendix.STATUS_P22_1_APPENDIX_D3,
      MessageKeys.Error_ProtocolAppenix_KonnteNichtExportiertWerden, null,
      ReportTemplateEnum.OSV5_3),

  P22_2_APPENDIX(CMD_ADM_EXPORT_P22_2_APPENDIX,
      P5ExportStateP22_2Appendix.STATUS_P22_2_APPENDIX_D3,
      MessageKeys.Error_ProtocolAppenix_KonnteNichtExportiertWerden, null,
      ReportTemplateEnum.OSV5_3),

  CANDIDATE_ADDRESS(CMD_ADM_EXPORT_CANDIDATE_ADDRESS,
      P5ExportStateCandidateAddress.STATUS_CandidateAddress_D2,
      MessageKeys.Error_CandidateAddress_KonnteNichtExportiertWerden, ReportOutputFormatEnum.CSV,
      ReportTemplateEnum.OSV5_5),

  KAN_BEN(CMD_ADM_EXPORT_KAN_BEN, P5ExportStateKanBen.STATUS_KanBen_D3,
      MessageKeys.Error_GewBen_KonnteNichtExportiertWerden, ReportOutputFormatEnum.RTF,
      ReportTemplateEnum.OSV5_1, ReportTemplateEnum.OSV5_2, ReportTemplateEnum.OSV5_6);

  // Command that triggers the export
  private final String exportCommand;
  // not in use
  private final int finalState;
  // Error message shown to the user in case the export fails
  private final String errorMsgKey;
  // The format in which the document is exported
  private final ReportOutputFormatEnum reportOutputFormat;
  // Template for the export
  private final ReportTemplateEnum template;
  // Templates of additional documents that accompany the export
  private final ReportTemplateEnum[] templateExts;

  @SuppressWarnings("hiding")
  ExportP5Type(String exportCommand,
      int finalState,
      String errorMsgKey,
      ReportOutputFormatEnum reportOutputFormat,
      ReportTemplateEnum template,
      ReportTemplateEnum... templateExts) {
    this.exportCommand = exportCommand;
    this.errorMsgKey = errorMsgKey;
    this.reportOutputFormat = reportOutputFormat;
    this.finalState = finalState;
    this.template = template;
    this.templateExts = templateExts;
  }

  public String getExportCommand() {
    return exportCommand;
  }

  public int getFinalState() {
    return finalState;
  }

  public String getErrorMsgKey() {
    return errorMsgKey;
  }

  public ReportOutputFormatEnum getReportOutputFormat() {
    return reportOutputFormat;
  }

  public ReportTemplateEnum getTemplate() {
    return template;
  }

  public ReportTemplateEnum[] getTemplates() {
    return templateExts;
  }

}