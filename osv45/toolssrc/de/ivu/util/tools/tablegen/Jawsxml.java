package de.ivu.util.tools.tablegen;

/**
 * Generation of Jaws-XML CMP-Descriptor for JBoss 2.x and higher
 * 
 * @author <a href="mailto:cos@ivu.de">Dr. Domagoj Cosic</a>
 * 
 * (c) 2004 IVU Traffic Technologies AG
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class Jawsxml {
  private final String fileName;
  private StringBuilder content;

  public Jawsxml(String jawsFileName) {
    fileName = jawsFileName;
    content = new StringBuilder();
    read();
  }

  /**
   * Add finder to the Bean
   * 
   * @param beanName Name of the Bean
   * @param finderContent XML-Code of the finders
   * @return complete Descriptor entry
   */
  public boolean addFinder(String beanName, String finderContent) {
    int beanLine = getBeanLine(beanName);
    StringTokenizer token;
    StringBuilder sb;
    if (beanLine == -1) {
      // bean does not exist
      sb = new StringBuilder();
      // boolean go = true;
      // boolean existFinder = false;
      token = new StringTokenizer(content.toString(), "\n"); //$NON-NLS-1$
      while (token.hasMoreTokens()) {
        String oriLine = token.nextToken();
        String line = oriLine.trim().toLowerCase();
        if (line.equals("</enterprise-beans>")) { //$NON-NLS-1$
          sb.append(getEmptyBean(beanName));
        }
        sb.append(oriLine).append('\n');
      }
      content = sb;
      beanLine = getBeanLine(beanName);
      if (beanLine == -1) {
        System.err.println("State not possible"); //$NON-NLS-1$
        return false;
      }
    }
    sb = new StringBuilder();
    token = new StringTokenizer(content.toString(), "\n"); //$NON-NLS-1$
    for (int x = 0; x <= beanLine; x++) {
      sb.append(token.nextToken()).append('\n');
    }

    sb.append(finderContent);
    while (token.hasMoreTokens()) {
      sb.append(token.nextToken()).append('\n');
    }
    content = sb;
    return true;
  }

  /**
   * Write Jaws-Descriptor into the file
   * 
   * @return <code>true</code> when success
   */
  public boolean write() {
    try {
      BufferedWriter file = new BufferedWriter(new FileWriter(fileName));
      file.write(content.toString());
      file.close();
      return true;
    } catch (IOException ioe) {
      System.err.println("Error writing File " + fileName); //$NON-NLS-1$
      ioe.printStackTrace();
      return false;
    }
  }

  /**
   * Read Jaws-Descriptor from file
   * 
   * @return <code>true</code> when success
   */
  protected boolean read() {
    try {
      BufferedReader file = new BufferedReader(new FileReader(fileName));
      String line;
      while ((line = file.readLine()) != null) {
        content.append(line).append('\n');
      }
      return true;
    } catch (IOException ioe) {
      System.err.println("Error reading File " + fileName); //$NON-NLS-1$
      ioe.printStackTrace();
      return false;
    }
  }

  /**
   * Find row number of the descriptor to a certain Bean
   * 
   * @return row number of the Bean's descriptor
   */
  protected int getBeanLine(String beanName) {
    StringTokenizer token = new StringTokenizer(content.toString(), "\n"); //$NON-NLS-1$
    // boolean beanFound = false;
    int lineNumber = 0;
    while (token.hasMoreTokens()) {
      String line = token.nextToken().trim().toLowerCase();
      if (line.equals("<ejb-name>" + beanName.toLowerCase() + "</ejb-name>")) { //$NON-NLS-1$ //$NON-NLS-2$
        return lineNumber;
      }
      lineNumber++;
    }
    return -1;
  }

  /**
   * Check, whether a finder is still existing
   * 
   * @param beanName Name of the Bean
   * @param finderMethode Finder searched for
   * @return <code>true</code> , if the finder is existing
   */
  protected boolean existFinder(String beanName, String finderMethode) {
    int beanLine = getBeanLine(beanName);
    StringTokenizer token = new StringTokenizer(content.toString(), "\n"); //$NON-NLS-1$
    for (int x = 0; x <= beanLine; x++) {
      token.nextToken();
    }
    boolean done = false;
    boolean comment = false;
    while (done == false) {
      String line = token.nextToken().toLowerCase().trim();
      if (line.indexOf("<!--") != -1) { //$NON-NLS-1$
        // found comment
        comment = true;
      }
      if (line.indexOf("-->") != -1) { //$NON-NLS-1$
        // end of comment
        comment = false;
      }
      if (line.equals("</entity>")) { //$NON-NLS-1$
        done = true;
      } else if (!comment && line.equals("<name>" + finderMethode.toLowerCase() + "</name>")) { //$NON-NLS-1$ //$NON-NLS-2$
        return true;
      }
      if (line.equals("</enterprise-beans>")) { //$NON-NLS-1$
        done = true;
      }
    }
    return false;
  }

  /**
   * Generate an empty entry for a Bean
   * 
   * @param beanName Name of the Bean
   * @return Empty entry for the Bean
   */
  protected String getEmptyBean(String beanName) {
    return "    <entity>\n      <ejb-name>" + beanName + "</ejb-name>\n    </entity>\n"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Override
  public String toString() {
    return content.toString();
  }
}
