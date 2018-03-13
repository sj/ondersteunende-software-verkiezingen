/*
 * GeneratedFileExistsException
 * 
 * Created on 04.05.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.wus.reportgen;

import java.util.Collection;

import de.ivu.wahl.wus.reportgen.i18n.MessageKeys;
import de.ivu.wahl.wus.reportgen.i18n.Messages;

/**
 * This exception is thrown if the ReportGenerator is requested to generate one or more files and at
 * least one of the files with the requested names already exists. The Exception contains a message
 * that contains the names of all those filenames that already exist.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class GeneratedFileExistsException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public GeneratedFileExistsException(Collection<String> filenames) {
    super(createMessage(filenames));
  }

  private static String createMessage(Collection<String> filenames) {
    StringBuilder builder = new StringBuilder();
    if (filenames.size() == 1) {
      builder.append(Messages.getString(MessageKeys.File_already_exists));
    } else {
      builder.append(Messages.getString(MessageKeys.Files_already_exist));
    }
    boolean first = true;
    for (String filename : filenames) {
      if (first) {
        first = false;
      } else {
        builder.append(", "); //$NON-NLS-1$
      }
      builder.append(filename);
    }
    return builder.toString();
  }
}
