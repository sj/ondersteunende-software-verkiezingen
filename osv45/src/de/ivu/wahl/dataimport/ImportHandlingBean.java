/*
 * ImportHandlingBean
 * 
 * Copyright (c) 2004-8 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import static de.ivu.wahl.dataimport.XMLTags.NS_EML;
import static de.ivu.wahl.util.XMLImportHelper.getIntValue;
import static de.ivu.wahl.util.XMLImportHelper.getText;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;

import org.apache.log4j.Category;

import nu.xom.Element;
import nu.xom.Elements;

import de.ivu.ejb.EJBUtil;
import de.ivu.ejb.bmp.Model;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.WahlStatelessSessionBeanBase;
import de.ivu.wahl.admin.AdminHandling;
import de.ivu.wahl.admin.AdminHandlingBean;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.Gebietsart;
import de.ivu.wahl.modell.GruppeGebietsspezifischModel;
import de.ivu.wahl.modell.GruppeKonstanten;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.GebietHome;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifisch;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifischHome;
import de.ivu.wahl.modell.ejb.GruppeHome;
import de.ivu.wahl.modell.ejb.Personendaten;
import de.ivu.wahl.modell.ejb.Wahl;
import de.ivu.wahl.modell.exception.ImportException;
import de.ivu.wahl.modell.impl.GebietModelImpl;
import de.ivu.wahl.modell.impl.GruppeGebietsspezifischModelImpl;
import de.ivu.wahl.util.XMLImportHelper;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * SessionBean f�r die Unterst�tzung des ImportClient
 * 
 * @author cos@ivu.de
 */
@Stateless
@Local(ImportHandling.class)
public class ImportHandlingBean extends WahlStatelessSessionBeanBase implements ImportHandling {

  private static final long serialVersionUID = 1025396171122479258L;
  private final static Category LOGGER = Log4J.configure(ImportHandlingBean.class);
  private static final String MODEL = "Model"; //$NON-NLS-1$

  public void createEntities(Collection<Model> detailsCol) throws ImportException {
    Map<Class<? extends Model>, EJBLocalHome> tmpLocalHomeCache = new HashMap<Class<? extends Model>, EJBLocalHome>();
    Map<Class<? extends Model>, Method> tmpCreateCache = new HashMap<Class<? extends Model>, Method>();

    for (Model details : detailsCol) {
      Class<? extends Model> detailsClass = details.getClass();
      EJBLocalHome home = tmpLocalHomeCache.get(detailsClass);
      Method create = null;
      if (home == null) {
        String specificInterfaceName = null;
        Class<?> clazz = detailsClass;
        Class<?> modelInterface = null;
        findIF : do {
          Class<?>[] interfaces = clazz.getInterfaces();
          for (int i = 0; i < interfaces.length; i++) {
            modelInterface = interfaces[i];
            String tmpName = modelInterface.getName();
            if (tmpName.endsWith(MODEL)) {
              specificInterfaceName = tmpName.substring(0, tmpName.lastIndexOf(MODEL));
              break findIF;
            }
          }
          clazz = clazz.getSuperclass();
        } while (clazz != null);

        if (specificInterfaceName != null) {
          specificInterfaceName = specificInterfaceName.substring(specificInterfaceName
              .lastIndexOf('.') + 1);
          home = findLocalHome(specificInterfaceName);
          tmpLocalHomeCache.put(detailsClass, home);
          try {
            create = home.getClass().getMethod("create", new Class[]{modelInterface}); //$NON-NLS-1$
            tmpCreateCache.put(detailsClass, create);
          } catch (NoSuchMethodException e) {
            throw new ImportException(Messages.getString(MessageKeys.Error_InBeanHome) + home, e);
          }
          if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Messages
                .bind(MessageKeys.Error_PerReflectionGefundenBeanHome_0_ModelInterface_1,
                    home,
                    (modelInterface == null ? null : modelInterface.getName())));
          }
        }
        if (home == null) {
          throw new ImportException(
              Messages.bind(MessageKeys.Error_BeanInterfaceFuer_0_KonnteNichtErmitteltWerden,
                  detailsClass));
        }
      } else {
        create = tmpCreateCache.get(detailsClass);
      }
      try {
        if (create != null) {
          create.invoke(home, new Object[]{details});
        } else {
          throw new ImportException(
              Messages
                  .bind(MessageKeys.Error_MethodeCreateExtendsModelFuer_0_KonnteNichtErmitteltWerden,
                      detailsClass.getName()));
        }
      } catch (IllegalAccessException e) {
        throw new ImportException(Messages.getString(MessageKeys.Error_InBeanHome)
            + home.getClass().getName(), e);
      } catch (InvocationTargetException e) {
        throw new ImportException(Messages.getString(MessageKeys.Error_InBeanHome)
            + home.getClass().getName(), e);
      }
    }
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.ImportHandling#createAdministrationValues()
   */
  @Override
  public void createAdministrationValues(String id_Wahl) throws ImportException {
    try {
      // Set default threshold values
      AdminHandling admHandling = EJBUtil.lookupLocal(AdminHandlingBean.class.getSimpleName());
      admHandling.initAdministrationValues(id_Wahl);
    } catch (Exception e) {
      throw new ImportException(
          Messages.getString(MessageKeys.Error_FehlerBeimErzeugenDerAdministrativenDaten), e);
    }
  }

  /*
   * This method should be used to create names for blanco lists It's not wanted anymore, but kept
   * here in case problems with differnt list names have to be solved (for other elections)
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.ImportHandling#createListenkombinationen(java.util.Map)
   */
  @Override
  public void updateGruppennamen() throws ImportException {
    try {
      GruppeHome gruppeHome = (GruppeHome) EJBUtil.findLocalHomeNoCache("Gruppe"); //$NON-NLS-1$
      Collection<Gruppe> gruppen = gruppeHome
          .findAllByGruppenart(GruppeKonstanten.GRUPPENART_PARTEI);
      for (Gruppe gruppe : gruppen) {
        if (gruppe.getNameLang() == null || gruppe.getNameLang().trim().length() == 0) {
          // We assume that all blanco lists in a group have the same first candidate
          Personendaten ersterKandidat = gruppe.getListeCol().iterator().next()
              .getListenkandidaturCol().iterator().next().getPersonendaten();
          String name = "Lijst " + ersterKandidat.getNachname(); //$NON-NLS-1$
          gruppe.setNameLang(name);
        }
      }
    } catch (ObjectNotFoundException e) {
      throw new ImportException(
          Messages.getString(MessageKeys.Error_FehlerBeimAktualisierenDerGruppennamen), e);
    } catch (EJBException e) {
      throw new ImportException(
          Messages.getString(MessageKeys.Error_FehlerBeimAktualisierenDerGruppennamen), e);
    } catch (FinderException e) {
      throw new ImportException(
          Messages.getString(MessageKeys.Error_FehlerBeimAktualisierenDerGruppennamen), e);
    }
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.ImportHandling#createStimmbezirke(int)
   */
  @Override
  public void createStimmbezirke(int anzahl) throws ImportException {
    try {
      WahlInfo wahlInfo = WahlInfo.getWahlInfo();
      String id_Wurzelgebiet = wahlInfo.getID_Wurzelgebiet();
      List<Gebiet> stimmbezirke = getPollingStations(id_Wurzelgebiet);
      int maxNumber;
      if (stimmbezirke.size() > 0) {
        maxNumber = stimmbezirke.get(stimmbezirke.size() - 1).getNummer();
      } else {
        maxNumber = 0;
      }
      List<Gebiet> stimmbezirkeNeu = new ArrayList<Gebiet>();
      // max existing region number
      for (int idx = 1 + maxNumber; idx <= anzahl + maxNumber; ++idx) {
        GebietModel gebiet = new GebietModelImpl(EJBUtil.getUniqueKey());
        gebiet.setName(Gebietsart.STIMMBEZIRK.getKlartext() + "_" //$NON-NLS-1$
            + idx);
        gebiet.setNummer(idx);
        gebiet.setID_Wahl(wahlInfo.getID_Wahl());
        gebiet.setID_UebergeordnetesGebiet(id_Wurzelgebiet);
        gebiet.setGUIEingabeErlaubt(true);
        gebiet.setErfassungseinheit(true);
        gebiet.setGebietsart(GebietModel.GEBIETSART_STIMMBEZIRK);
        gebiet.setPosition(idx);
        stimmbezirkeNeu.add(getGebietHome().create(gebiet));
      }
      finishCreatePollingStations(stimmbezirkeNeu);
    } catch (CreateException e) {
      throw new EJBException(Messages.getString(MessageKeys.Error_FehlerBeimAnlegenDerStimmbezirke));
    } catch (FinderException e) {
      throw new EJBException(Messages.getString(MessageKeys.Error_FehlerBeimAnlegenDerStimmbezirke));
    }
  }

  /**
   * Create region specific groups for list gebiete
   * 
   * @param gebiete
   * @throws FinderException
   * @throws CreateException
   * @throws EJBException
   */
  private void finishCreatePollingStations(List<Gebiet> gebiete)
      throws FinderException, EJBException, CreateException {
    WahlInfo wahlInfo = WahlInfo.getWahlInfo();
    getGebietHome().initGebietGebiet();
    Collection<GruppeGebietsspezifisch> ggCol = getGruppeGebietsspezifischHome()
        .findAllByGebiet(wahlInfo.getID_Wurzelgebiet());
    for (GruppeGebietsspezifisch gg : ggCol) {
      for (Gebiet gebiet : gebiete) {
        GruppeGebietsspezifischModel ggModel = new GruppeGebietsspezifischModelImpl(
            EJBUtil.getUniqueKey());
        ggModel.setID_Gebiet(gebiet.getID_Gebiet());
        ggModel.setID_Gruppe(gg.getID_Gruppe());
        ggModel.setID_UebergeordneteGG(gg.getID_GruppeGebietsspezifisch());
        ggModel.setID_Liste(gg.getID_Liste());
        ggModel.setPosition(gg.getPosition());
        getGruppeGebietsspezifischHome().create(ggModel);
      }
    }
  }

  GruppeGebietsspezifischHome _ggHome = null;

  private GruppeGebietsspezifischHome getGruppeGebietsspezifischHome() {
    if (_ggHome == null) {
      _ggHome = (GruppeGebietsspezifischHome) EJBUtil
          .findLocalHomeNoCache("GruppeGebietsspezifisch"); //$NON-NLS-1$
    }
    return _ggHome;
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.dataimport.ImportHandling#createStimmbezirke(java.net.URL)
   */
  @Override
  public void createStimmbezirke(URL url110b) throws ImportException {
    WahlInfo wahlInfo = WahlInfo.getWahlInfo();
    Wahl wahl = wahlInfo.getWahl();
    String id_Wahl = wahl.getID_Wahl();
    Gebiet supRegion = wahl.getWurzelgebiet();
    GebietHome gebietHome = (GebietHome) EJBUtil.findLocalHomeNoCache("Gebiet"); //$NON-NLS-1$
    // check schema
    Element ergebnisKnoten;
    try {
      ergebnisKnoten = ImportUtil.readXMLRoot4Schema(url110b, XMLTags.SCHEMA_EML110B);
    } catch (ImportException e) {
      String msg = Messages.getString(MessageKeys.Error_DateiEntsprichtNichtDerSchemadefinition);
      if (e.getMessage() != null) {
        msg += ": " + e.getMessage(); //$NON-NLS-1$
      }
      throw new ImportException(msg, e);
    }
    // Don't check election metadata
    Element gebietsergebnis = ergebnisKnoten
        .getFirstChildElement(XMLTags.EML_ELECTION_EVENT, NS_EML)
        .getFirstChildElement(XMLTags.EML_ELECTION, NS_EML)
        .getFirstChildElement(XMLTags.EML_CONTEST, NS_EML);
    // Check superior region
    // reportingUnitNode
    Element reportingUnitNode = gebietsergebnis.getFirstChildElement(XMLTags.EML_REPORTING_UNIT,
        NS_EML);
    int nrUebergeordnetesGebiet = supRegion.getNummer();
    int nrUebergeordnetesGebietFromFile = XMLImportHelper.getAttributeIntValue(reportingUnitNode
        .getFirstChildElement(XMLTags.EML_REPORTING_UNIT_IDENTIFIER, XMLTags.NS_EML),
        XMLTags.ATTR_EML_ID);
    if (nrUebergeordnetesGebiet != nrUebergeordnetesGebietFromFile) {
      String regionFound = Gebietsart.GEMEINDE.getKlartext() + " " //$NON-NLS-1$
          + nrUebergeordnetesGebietFromFile;
      String regionExpected = Gebietsart.GEMEINDE.getKlartext() + " " + nrUebergeordnetesGebiet; //$NON-NLS-1$
      LOGGER.error(Messages.bind(MessageKeys.Error_Datei_0_EnthaeltDatenFuer_1_Nicht_2,
          url110b.toExternalForm(),
          regionFound,
          regionExpected));
      throw new ImportException(
          Messages.bind(MessageKeys.Error_Datei_0_EnthaeltDatenFuer_1_Nicht_2,
              url110b.getFile(),
              regionFound,
              regionExpected));
    }
    // Check rootregiontype --> if (Deelraad == Deelgemeente) else Gemeente
    final Gebietsart gebietsart;
    if (wahlInfo.getElectionCategory().equals(ElectionCategory.BC)
        || wahlInfo.getElectionCategory().equals(ElectionCategory.GC)) {
      gebietsart = Gebietsart.ORTSTEIL;
    } else if (wahlInfo.getElectionCategory().isOnIsland()) {
      gebietsart = Gebietsart.INSELGEMEINDE;
    } else {
      gebietsart = Gebietsart.GEMEINDE;
    }
    String nameUebergeordnetesGebiet = gebietsart.getKlartext() + " " + supRegion.getName(); //$NON-NLS-1$
    String nameUebergeordnetesGebietFromFile = XMLImportHelper.getText(reportingUnitNode
        .getFirstChildElement(XMLTags.EML_REPORTING_UNIT_IDENTIFIER, XMLTags.NS_EML));
    if (!nameUebergeordnetesGebiet.equals(nameUebergeordnetesGebietFromFile)) {
      String regionFound = nameUebergeordnetesGebietFromFile;
      LOGGER.error(Messages.bind(MessageKeys.Error_Datei_0_EnthaeltDatenFuer_1_Nicht_2,
          url110b.toExternalForm(),
          regionFound,
          nameUebergeordnetesGebiet));
      throw new ImportException(
          Messages.bind(MessageKeys.Error_Datei_0_EnthaeltDatenFuer_1_Nicht_2,
              url110b.getFile(),
              regionFound,
              nameUebergeordnetesGebiet));
    }
    int wahlberechtigteUeg = XMLImportHelper.getIntValue(gebietsergebnis
        .getFirstChildElement(XMLTags.EML_MAX_VOTES, NS_EML));
    supRegion.setWahlberechtigte(wahlberechtigteUeg);

    // Iterate over PollingPlaces
    Elements pollingPlaces = gebietsergebnis.getChildElements(XMLTags.EML_POLLING_PLACE, NS_EML);

    List<Gebiet> pollingStations = new ArrayList<Gebiet>();
    try {
      for (int idx = 0; idx < pollingPlaces.size(); ++idx) {
        Element pollingPlace = pollingPlaces.get(idx);
        String votingChannelValue = XMLImportHelper.getAttribute(pollingPlace,
            XMLTags.ATTR_POLLING_PLACE_CHANNEL);
        // String votingChannelValue =
        // pollingPlace.getAttributeValue(XMLTags.ATTR_POLLING_PLACE_CHANNEL,
        // XMLTags.NS_EML);
        // tmp = Knoten PhysicalLocation
        Element physicalLocation = pollingPlace.getFirstChildElement(XMLTags.EML_PHYSICAL_LOCATION,
            XMLTags.NS_EML);
        Element address = physicalLocation
            .getFirstChildElement(XMLTags.EML_ADDRESS, XMLTags.NS_EML);
        Element locality = address.getFirstChildElement(XMLTags.EML_LOCALITY, XMLTags.NS_EML);
        String name = null;
        String zipcode = null;
        if (locality == null) {
          // older version with xal:Address element
          String importName = getText(address, XMLTags.EML_ADDRESS, XMLTags.NS_XAL);
          if (importName != null) {
            String namePrefix = Gebietsart.STIMMBEZIRK.getKlartext() + " "; //$NON-NLS-1$
            if (importName.length() >= namePrefix.length()
                && namePrefix.equals(importName.substring(0, namePrefix.length()))) {
              importName = importName.substring(namePrefix.length());
            }
            String[] importNameParts = importName.split(" \\(" + XMLTags.KEYWORD_POSTAL_CODE); //$NON-NLS-1$
            name = importNameParts[0].trim();
            if (importNameParts.length > 1) {
              zipcode = importNameParts[1].substring(0, importNameParts[1].length() - 1).trim();
            }
          }
        } else {
          // current version with Locality element
          name = getText(locality, XMLTags.EML_LOCALITY_NAME, XMLTags.NS_XAL);
          Element postalCode = locality.getFirstChildElement(XMLTags.ADRESSE_PLZ, XMLTags.NS_XAL);
          if (postalCode != null) {
            zipcode = getText(postalCode, XMLTags.PLZ_NUMMER, XMLTags.NS_XAL);
          }
        }
        int nummer = XMLImportHelper
            .getAttributeIntValue(physicalLocation
                .getFirstChildElement(XMLTags.EML_POLLING_STATION, XMLTags.NS_EML),
                XMLTags.ATTR_EML_ID);
        int anzWahlberechtigte = getIntValue(physicalLocation,
            XMLTags.EML_POLLING_STATION,
            XMLTags.NS_EML,
            -1);
        GebietModel gebiet = new GebietModelImpl(EJBUtil.getUniqueKey());
        gebiet.setID_Wahl(id_Wahl);
        gebiet.setGebietsart(GebietModel.GEBIETSART_STIMMBEZIRK);
        gebiet.setGUIEingabeErlaubt(true);
        gebiet.setName(name);
        gebiet.setZipcode(zipcode);
        gebiet.setPosition(idx + 1);
        gebiet.setNummer(nummer);
        if (anzWahlberechtigte != -1) {
          gebiet.setWahlberechtigte(anzWahlberechtigte);
        }
        gebiet.setID_UebergeordnetesGebiet(supRegion.getID_Gebiet());
        gebiet.setRoemisch(false);
        gebiet.setErfassungseinheit(true);
        if (XMLTags.ATTR_VAL_CHANNEL_POSTAL.equals(votingChannelValue)) {
          gebiet.setPostalvote(true);
        } else {
          gebiet.setPostalvote(false);
        }
        pollingStations.add(gebietHome.create(gebiet));
      }
      finishCreatePollingStations(pollingStations);
    } catch (EJBException e) {
      throw new ImportException(Messages.bind(MessageKeys.Logger_FehlerBeimAnlegen), e);
    } catch (CreateException e) {
      throw new ImportException(Messages.bind(MessageKeys.Logger_FehlerBeimAnlegen), e);
    } catch (FinderException e) {
      throw new ImportException(Messages.bind(MessageKeys.Logger_FehlerBeimAnlegen), e);
    }
  }

  /**
   * @return all direct sub-regions of the region given by id_RootRegion, sorted by number.
   * @throws ImportException
   */
  private List<Gebiet> getPollingStations(String id_RootRegion) throws ImportException {
    GebietHome gebietHome = (GebietHome) EJBUtil.findLocalHomeNoCache("Gebiet"); //$NON-NLS-1$
    try {
      return new ArrayList<Gebiet>(
          gebietHome.findAllByUebergeordnetesGebietSortByNummer(id_RootRegion));
    } catch (FinderException e) {
      throw new ImportException(Messages.bind(MessageKeys.Logger_FehlerBeimAnlegen), e);
    }
  }

}
