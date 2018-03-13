package de.ivu.wahl.wus.reportgen.rtfupdate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.InputStreamReader;

/**
 * 
 * @author anha@ivu.de, IVU Traffic Technologies AG
 */
public class AddImportStateToXSLT implements FilenameFilter {

  private static final String fromDirName = "D:/projekte/de.ivu.wahl.wus.reportgenerator/src/main/resources/de/ivu/wahl/wus/reportgen/templates/";
  private static final String toDirName = "D:/projekte/de.ivu.wahl.wus.reportgenerator/src/main/resources/de/ivu/wahl/wus/reportgen/templates/";
  private static final String filePattern = "-to-RTF.xslt";

  public boolean accept(File dir, String name) {
    return name.endsWith(filePattern);
  }

  public static void main(String args[]) {

    AddImportStateToXSLT addImportState = new AddImportStateToXSLT();

    File dir = new File(fromDirName);
    String[] fileArr = dir.list(addImportState);
    
    for (int i = 0; i < fileArr.length; i++) {
      // get file name to be updated
      String fromFileName = fileArr[i];

      try {
        // construct import statement
        String importStatement = "<xsl:import href=\"" + fromDirName + fromFileName.substring(0, fromFileName.indexOf(filePattern)) + "-text.xslt" + "\"/>";

        StringBuffer sb = new StringBuffer();
        FileInputStream fin = new FileInputStream(fromDirName + fromFileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fin));
        String line;
        while ((line = br.readLine()) != null) {
          // skip line if import statement is already there
          if (line.contains("<xsl:import ")) {
            continue;
          }
          // insert import statement
          if (line.contains("<xsl:output method=\"text\" encoding=\"UTF-8\"/>")) {
            sb.append("\t");
            sb.append(importStatement);
            sb.append("\n");
          }
          sb.append(line);
          sb.append("\n");
        }
        br.close();
        
        // update file with import statement
        String toFileName = fromFileName;
        FileWriter fw = new FileWriter(toDirName + toFileName);
        fw.write(sb.toString());
        fw.close();

        System.out.println(toFileName + " added: " + importStatement);

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
