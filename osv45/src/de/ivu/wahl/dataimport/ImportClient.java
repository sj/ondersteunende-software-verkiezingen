/*
 * Created on 06.01.2009
 * @author ugo
 */
package de.ivu.wahl.dataimport;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.Wahldaten;
import de.ivu.wahl.modell.exception.ImportException;

/**
 * ImportClient implementation used for directly creating Wahldaten from EML files (bypassing the
 * database).
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class ImportClient extends AbstractImportClient {
  private static final Category LOGGER = Log4J.configure(ImportClient.class);

  /**
   * Constructor
   */
  public ImportClient(ImportElectionMetadata importElectionMetadata) {
    super(importElectionMetadata);
  }

  public void run(Wahldaten wahldaten) throws ImportException {
    createWahl();
    wahldaten.setWahl(_wahlModel);
    LOGGER.info(Messages.bind(MessageKeys.Result_Tracelog_WahlMitGebietenFertig));
    wahldaten.setGebiete(_gebietsListe);
    // F�r jedes Gebiet allgemeine GruppeGebietsspezifisch anlegen
    createAllgemeineGruppeGebietsspezifisch();
    createListen();
    wahldaten.setGruppen(_gruppeMap.values());
    new ImportClientErgebnis(new ErgebnisImportHandlerTest(wahldaten))
        .importVotingResults(_importMetadata.getEML510());
    LOGGER.info(Messages.bind(MessageKeys.Result_Tracelog_ImportErfolgreichBeendet));
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.AbstractImportClient#createReferendumListen()
   */
  @Override
  protected void createReferendumListen() throws ImportException {
    throw new UnsupportedOperationException(
        "Unimplemented method ImportClient.createReferendumListen().");
  }

  @Override
  protected void setParentRegionName(String parentRegionName) throws ImportException {
    // Do nothing
  }

  @Override
  protected void setElectioalDistrictNameAndNumber(String value, String idElterngebiet) {
    // Do nothing
  }
}
