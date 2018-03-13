package de.ivu.wahl.client.beans;

import static de.ivu.ejb.EJBUtil.lookupLocal;
import static de.ivu.wahl.Konstanten.PROP_UPLOADDIR;
import static java.io.File.separatorChar;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.log4j.Logger;

import de.ivu.wahl.admin.PropertyHandling;
import de.ivu.wahl.admin.PropertyHandlingBean;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.util.EMLFilenameCheck;

/**
 * Geschaffen f�r das Ansto�en des Importes aus der GUI-Oberfl�che vom Wahlabwicklungssystem und zum
 * Upload von Ergebnissen der Erfassungseinheiten
 * 
 * @author mur@ivu.de cos@ivu.de - Copyright (c) 2002-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
public class UploadBean extends BasicUploadBean {
  private static final long serialVersionUID = 1684684774094348017L;

  /** Logger for instances of this class */
  private final static Logger LOGGER = Logger.getLogger(UploadBean.class);
  protected final Properties prop = new Properties();

  /**
   * anwenderMsg wird von der Webseite benutzt um eine Satusmeldung f�r den Anwender anzuzeigen
   */
  public String _adminMsg;
  public String _adminConfirmationMsg = null;

  private transient PropertyHandling _propHandling;

  public UploadBean() {

    _commandMap
        .put("upl_Upload_EE", //$NON-NLS-1$
            new Command(Messages
                .getString(MessageKeys.Msg_UploadVonDateiMitErfassungseinheitGestartet)) {
              private static final long serialVersionUID = 1708738635734248799L;

              @Override
              void execute(HttpServletRequest request) {
                upload(request);
              }
            });
  }

  /**
   * Hochladen der Datei auf den Server
   */
  void upload(HttpServletRequest request) {
    _adminMsg = null;
    _adminConfirmationMsg = null;
    try {
      File dpath = getPropertyHandling().getFileProperty(PROP_UPLOADDIR);
      /*
       * Die Ordnerstruktur ist /-/ wurzelverzeichnis: 230c; 510d;520; /-/ stembureaus: 510a Telling
       * stembureau /-/ gemeenten: 510b Telling gemeente /-/ kieskringen: 510c Telling kieskring /-/
       * andere: Alles, was den anderen konventionen nicht entspricht /-/
       */
      LOGGER.info("DPATH  : " + dpath); //$NON-NLS-1$
      try {
        for (FileItem item : getItems(request)) {
          if (item.isFormField()) {
            LOGGER.debug(Messages
                .bind(MessageKeys.Logger_POSTEnthaeltUnerwarteteDatenSieWerdenIgnoriert_0_1, item
                    .getFieldName(), item.getString()));
          } else {
            String fileName = item.getName();
            if (fileName != null && !fileName.isEmpty()) {
              // 510abc
              String unterverzeichnis = EMLFilenameCheck.getUnterverzeichnis(EMLFilenameCheck
                  .reduceFilenameFromSlashAsPrefix(fileName));
              LOGGER.info("filename  : " + fileName); //$NON-NLS-1$
              fileName = new File(fileName.replace('\\', separatorChar)).getName();
              File parentFile = new File(dpath, unterverzeichnis);
              File realFile = new File(parentFile, fileName);
              parentFile.mkdirs();
              if (!parentFile.isDirectory()) {
                _adminMsg = Messages
                    .bind(MessageKeys.Error_Verzeichnis_0_KonnteNichtAngelegtWerden, parentFile
                        .getPath());
                throw new Exception(Messages.getString(MessageKeys.VerzeichnisWurdeNichtAngelegt));
              }
              FileOutputStream fout = new FileOutputStream(realFile);
              fout.write(item.get());
              fout.close();
              _adminConfirmationMsg = Messages
                  .bind(MessageKeys.Die_Datei_wurde_erfolgreich_nach_0_gespeichert, parentFile
                      .getPath());
            } else {
              _adminMsg = Messages.bind(MessageKeys.ERROR_DateinameWurdeNichtAngegeben);
              // throw new Exception(Messages.getString(MessageKeys.DateinameWurdeNichtAngegeben));
            }
          }
        }
      } catch (SizeLimitExceededException slee) {
        _adminMsg = Messages.bind(MessageKeys.ERROR_DieDateiIstZuGross_0, SIZE_MAX_MBYTE);

        throw new Exception(_adminMsg);
      }
    } catch (Exception e) {
      _adminMsg = Messages.bind(MessageKeys.ERROR_DATEI_KONNTE_NICHT_GELADEN_WERDEN)
          + e.getMessage();
      LOGGER.error(e.getMessage(), e);
    }
  }

  /**
   * @return {@link PropertyHandling}
   */
  public PropertyHandling getPropertyHandling() {
    if (_propHandling == null) {
      _propHandling = lookupLocal(PropertyHandlingBean.class.getSimpleName());
    }
    return _propHandling;
  }
}