/*
 * ErgebnisImportHandlerTest
 * 
 * Created on 06.02.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import java.util.Collection;

import nu.xom.Element;

import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.GesamtstimmenImpl;
import de.ivu.wahl.modell.GruppeComposite;
import de.ivu.wahl.modell.GruppeComposite.Liste;
import de.ivu.wahl.modell.GruppeComposite.Listenkandidat;
import de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein;
import de.ivu.wahl.modell.Wahldaten;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.modell.impl.GebietModelImpl;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * Import handler for test cases.
 * 
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public class ErgebnisImportHandlerTest implements ErgebnisImportHandler {
  private static final String NO_CANDIDATE_ID = "NO_CANDIDATE_ID"; //$NON-NLS-1$

  private final Wahldaten _wahldaten;
  GebietModel _wurzelgebiet;

  public ErgebnisImportHandlerTest(Wahldaten wahldaten) {
    _wahldaten = wahldaten;
    _wurzelgebiet = new GebietModelImpl("wurzel"); //$NON-NLS-1$
    _wurzelgebiet.setGebietsart(0);
    _wurzelgebiet.setName("Root region"); //$NON-NLS-1$
    _wurzelgebiet.setNummer(0);
  }

  @Override
  public boolean saveCandidateVotes(int regionCategory) {
    return true;
  }

  @Override
  public boolean checkConsistency() {
    return false;
  }

  @Override
  public void addStimmen(GebietModel gebiet,
      int gruppenschluessel,
      String id_Listenkandidatur,
      int stimmen) throws ImportException {
    if (NO_CANDIDATE_ID.equals(id_Listenkandidatur)) {
      return;
    }
    for (GruppeComposite gruppe : _wahldaten.getGruppen()) {
      if (gruppe.getGruppe().getSchluessel() == gruppenschluessel) {
        for (Liste liste : gruppe.getListen()) {
          if (liste.getGebiet().getID_Gebiet().equals(gebiet.getID_Gebiet())) {
            for (Listenkandidat kandidat : liste.getKandidaten()) {
              if (kandidat.getId_Listenkandidatur().equals(id_Listenkandidatur)) {
                kandidat.setStimmen(stimmen);
                break;
              }
            }
          }
        }
      }
    }
  }

  @Override
  public GebietModel findGebietByGebietsartAndNummer(int gebietsart, int nummer)
      throws ImportException {
    for (GebietModel gebiet : _wahldaten.getGebiete()) {
      if (gebiet.getGebietsart() == gebietsart && gebiet.getNummer() == nummer) {
        return gebiet;
      }
    }
    throw new ImportException(Messages.bind(MessageKeys.Error_GebietWurdeNichtAngelegt_0, nummer));
  }

  @Override
  public void finishErgebnisimport(Collection<GebietModel> gebieteEingegangen)
      throws ImportException {
    if (gebieteEingegangen.size() < _wahldaten.getGebiete().size()) {
      throw new ImportException(
          Messages.bind(MessageKeys.Error_Nur_0_von_1_GebietsergebnissenGefunden,
              gebieteEingegangen.size(),
              _wahldaten.getGebiete().size()));
    }
  }

  @Override
  public ElectionCategory getElectionCategory() {
    return ElectionCategory.fromWahlart(_wahldaten.getWahl().getWahlart());
  }

  @Override
  public boolean importRegionResults(int regionCategory) {
    return true;
  }

  @Override
  public String getID_Listenkandidatur(GebietModel gebiet,
      int gruppenschluessel,
      String shortCode,
      int listenplatz) {
    if (gebiet == null) {
      return NO_CANDIDATE_ID;
    }
    for (GruppeComposite gruppe : _wahldaten.getGruppen()) {
      if (gruppe.getGruppe().getSchluessel() == gruppenschluessel) {
        for (Liste liste : gruppe.getListen()) {
          if (liste.getGebiet().getID_Gebiet().equals(gebiet.getID_Gebiet())) {
            for (Listenkandidat kandidat : liste.getKandidaten()) {
              if (kandidat.getPerson().getID_Personendaten().equals(shortCode)
                  || listenplatz == kandidat.getListenplatz()) {
                return kandidat.getId_Listenkandidatur();
              }
            }
          }
        }
      }
    }
    return null;
  }

  @Override
  public GebietModel getImportRegion() throws ImportException {
    return _wurzelgebiet;
  }

  @Override
  public void readStimmergebnisseAllgemein(Element resultNode,
      GebietModel gebiet,
      GesamtstimmenImpl gesamtstimmen,
      int gesamtstimmenGebiet) throws ImportException {
    boolean isWurzelgebiet = resultNode.getLocalName().equals(XMLTags.EML_TOTAL_VOTES);

    // Number of votes
    GruppeAllgemeinXmlAdapter adapter = new GruppeAllgemeinXmlAdapter();

    // Determine the number of voters by adding valid, invalid and empty votes
    int anzGueltige = adapter.getXml(resultNode, GruppeAllgemein.GUELTIGE);
    int anzUngueltige = adapter.getXml(resultNode, GruppeAllgemein.UNGUELTIGE);
    int anzLeere = adapter.getXml(resultNode, GruppeAllgemein.LEER);
    int anzWaehler = anzGueltige + anzUngueltige + anzLeere;
    int anzGueltigOderLeer = anzGueltige + anzLeere;

    if (!isWurzelgebiet) {
      gesamtstimmen.addGruppenstimmen(GruppeAllgemein.WAEHLER.schluessel, anzWaehler);
      gesamtstimmen.addGruppenstimmen(GruppeAllgemein.GUELTIG_ODER_LEER.schluessel,
          anzGueltigOderLeer);
    }

    // Admitted voters
    // Proxy votes
    // Explaining the difference between admitted voters and counted votes
    Iterable<GruppeAllgemein> gruppen = adapter.getGruppenAllgemein();
    for (GruppeAllgemein gruppeAllgemein : gruppen) {
      int value = adapter.getFromEmlOr0(resultNode, gruppeAllgemein);
      if (!isWurzelgebiet) {
        gesamtstimmen.addGruppenstimmen(gruppeAllgemein.schluessel, value);
      }
    }
  }

  @Override
  public int getNumberOfCandidates(GebietModel gebiet, int gruppenschluessel)
      throws ImportException {
    for (GruppeComposite gruppe : _wahldaten.getGruppen()) {
      if (gruppe.getGruppe().getSchluessel() == gruppenschluessel) {
        for (Liste liste : gruppe.getListen()) {
          if (liste.getGebiet().getID_Gebiet().equals(gebiet.getID_Gebiet())) {
            return liste.getKandidaten().size();
          }
        }
      }
    }
    throw new ImportException(
        Messages.bind(MessageKeys.Error_In_0_KeineGruppeMitSchluessel_1_Angelegt,
            gebiet.getName(),
            gruppenschluessel));
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.ErgebnisImportHandler#getRegionCategoryWithLists()
   */
  @Override
  public int getRegionCategoryWithLists() {
    return _wahldaten.getGebiete().get(0).getGebietsart();
  }

}
