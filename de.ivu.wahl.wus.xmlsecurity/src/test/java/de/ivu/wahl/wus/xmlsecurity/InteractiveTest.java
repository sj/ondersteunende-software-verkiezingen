/*
 * InteractiveTest
 * 
 * Created on 17.04.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.xmlsecurity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;

import javax.swing.JFileChooser;

import org.junit.Test;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 * @version $Id: InteractiveTest.java,v 1.1 2009/04/17 12:21:23 cos Exp $
 */
public class InteractiveTest {
  private static IXmlDigestCreator __hashWertErmittlung = new SHAXmlDigestCreator();

  @Test
  public void loadAndCalculate() throws Exception {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fileChooser.setDialogTitle("Choose directory");
    int returnVal = fileChooser.showOpenDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      System.out.println("You chose to open this file: " + fileChooser.getSelectedFile().getName());
      File selectedFile = fileChooser.getSelectedFile();
      calculate(selectedFile);
    }
  }

  /**
   * @param dir
   * @param className
   * @throws MalformedURLException
   * @throws FileNotFoundException
   */
  private static void calculate(File dir) throws Exception {
    System.out.println("Directory " + dir);
    File[] files = dir.listFiles();
    for (int i = 0; i < files.length; i++) {
      File file = files[i];
      if (file.isDirectory()) {
        calculate(file);
      } else {
        InputStream source = new FileInputStream(file);
        try {
          String digest = __hashWertErmittlung.unconditionalCreateDigest(source);
          System.out.println("SHA-256(" + file.getName() + ")=" + digest);
        } catch (Exception e) {
          System.out.println("Cannot calculate hash for " + file.getName());
        }
      }
    }
  }
}
