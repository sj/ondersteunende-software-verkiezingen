/*
 * BasicUploadBean
 * 
 * Created on 26.04.2007
 * Copyright (c) 2007 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import static de.ivu.ejb.EJBUtil.lookupLocal;
import static de.ivu.wahl.Konstanten.PROP_UPLOADDIR;
import static java.io.File.separatorChar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.log4j.Logger;

import de.ivu.wahl.admin.PropertyHandling;
import de.ivu.wahl.admin.PropertyHandlingBean;
import de.ivu.wahl.dataimport.IImportEML;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.util.EMLFilenameCheck;

/**
 * Gemeinsame Funktionen aller Beans, die Upload-Funktionalität bereit stellen
 * 
 * @author cos@ivu.de - IVU Traffic Technologies AG
 */
public abstract class BasicUploadBean implements Executer, Serializable {
  private static final long serialVersionUID = 3786965277688743366L;

  /** Logger for instances of this class */
  private final static Logger LOGGER = Logger.getLogger(BasicUploadBean.class);
  private static final int BIN_KILO = 1024;
  protected static final int SIZE_MAX_MBYTE = 500;
  public static final int SIZE_MAX_BYTE = SIZE_MAX_MBYTE * BIN_KILO * BIN_KILO;
  private transient FileUpload _fileUpload;

  protected CommandMap _commandMap = new CommandMapImpl();

  
  PropertyHandling _propHandling = null;

  /**
   * @return PropertyHandling
   */
  PropertyHandling getPropertyHandling() {
    if (_propHandling == null) {
      _propHandling = lookupLocal(PropertyHandlingBean.class.getSimpleName());
    }
    return _propHandling;
  }

  /**
   * Erfüllt gewünschte Commands vom HTML-Client
   * 
   * @param request Der Request mit allen Informationen
   * @param n Nummer des auszuführenden Commands
   */
  public void executeCommand(HttpServletRequest request, int n) {
    String cmd = getCommand(request, n);
    if (cmd != null) {
      Command command = _commandMap.get(cmd);
      if (command != null) {
        LOGGER.info(command.getDescription());
        command.execute(request);
      } else {
        throw new RuntimeException(Messages.bind(MessageKeys.Error_Command_0_Unknown, cmd));
      }
    }
  }

  /**
   * @return korrekt initialisierte {@link FileUpload}-Struktur für diesen Anwendungsfall
   */
  protected FileUpload getFileUpload() {
    if (_fileUpload == null) {
      _fileUpload = new ServletFileUpload();
      DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
      diskFileItemFactory.setSizeThreshold(SIZE_MAX_BYTE);
      _fileUpload.setFileItemFactory(diskFileItemFactory);
      _fileUpload.setSizeMax(SIZE_MAX_BYTE);
    }

    return _fileUpload;
  }

  protected String getCommand(HttpServletRequest request, int n) {
    return request.getParameter("cmd" + (n == 0 ? "" : "" + n)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  @SuppressWarnings("unchecked")
  protected List<FileItem> getItems(HttpServletRequest request) throws FileUploadException {
    return getFileUpload().parseRequest(new ServletRequestContext(request));
  }

  /**
   * Write uploading file into archive folder.
   */
  protected void writeFileIntoArchiveFolder(FileItem item, IImportEML emlImport)
      throws Exception, FileNotFoundException, IOException {
    String fileName = item.getName();
    fileName = new File(fileName.replace('\\', separatorChar)).getName();

    // write file to archive
    File dpath = getPropertyHandling().getFileProperty(PROP_UPLOADDIR);
    String unterverzeichnis = EMLFilenameCheck.getUnterverzeichnis(EMLFilenameCheck
        .reduceFilenameFromSlashAsPrefix(fileName));

    File parentFile = new File(dpath, unterverzeichnis);
    File realFile = new File(parentFile, fileName);
    parentFile.mkdirs();
    if (!parentFile.isDirectory() && !parentFile.canWrite()) {
      emlImport.setFehlermeldung(Messages
          .bind(MessageKeys.Error_Verzeichnis_0_KonnteNichtAngelegtWerden, parentFile
              .getPath()));
      throw new Exception(Messages
          .getString(MessageKeys.Error_VerzeichnisWurdeNichtAngelegt));
    }
    FileOutputStream fout = new FileOutputStream(realFile);
    fout.write(item.get());
    fout.close();
  }
}
