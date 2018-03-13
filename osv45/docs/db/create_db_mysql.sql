CREATE TABLE Alternative (
       ID_Alternative       VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Konflikt          VARCHAR(13) NOT NULL,
       ID_Listenkombination VARCHAR(13),
       ID_Gruppe            VARCHAR(13),
       ID_Liste             VARCHAR(13),
       ID_Personendaten     VARCHAR(15),
       Nummer               INTEGER
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Anwender (
       ID_Anwender          VARCHAR(30) NOT NULL PRIMARY KEY,
       ID_Gebiet            VARCHAR(13),
       Name                 VARCHAR(200),
       Anwendername         VARCHAR(200),
       PasswordHash         VARCHAR(200),
       Salt                 VARCHAR(200),
       FehlversucheAnmeldung INTEGER,
       LetzterZugriff       TIMESTAMP NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Besonderheit (
       ID_Besonderheit      VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Ergebniseingang   VARCHAR(13) NOT NULL,
       ID_UebergeordneteBesonderheit VARCHAR(13),
       ID_Listenkombination VARCHAR(13),
       ID_Gruppe            VARCHAR(13),
       ID_Liste             VARCHAR(13),
       ID_Personendaten     VARCHAR(15),
       Besonderheitart      INTEGER,
       Anzahl               INTEGER,
       Text                 VARCHAR(4000),
       Nummer               INTEGER
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE DHondtQuotient (
       ID_DHondtQuotient    VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Restsitzverteilung VARCHAR(13) NOT NULL,
       Lauf                 INTEGER,
       Zaehler              INTEGER,
       Nenner               INTEGER,
       SitzAusRestanteil    SMALLINT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Ergebniseingang (
       ID_Ergebniseingang   VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Erfassungseinheit VARCHAR(13),
       ID_Wahl              VARCHAR(100),
       AnwenderName         VARCHAR(200),       
       Zeitstempel          TIMESTAMP NULL,
       Herkunft             INTEGER,
       Wahlergebnisart      INTEGER,
       UnterschiedeVorhanden INTEGER DEFAULT -1,
       Status               INTEGER,
       ErgebnisHash         VARCHAR(100),
       Fehlermeldung        LONG VARCHAR
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE FiktiveSitzverteilung (
       ID_FiktiveSitzverteilung    VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Ergebniseingang   VARCHAR(13),
       ID_Gruppe            VARCHAR(13),
       SitzeGesamtzahl      INTEGER
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Gebiet (
       ID_Gebiet            VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_UebergeordnetesGebiet VARCHAR(13),
       ID_Wahl              VARCHAR(100),
       ID_LetzterEingang    VARCHAR(13),
       Erfassungseinheit    SMALLINT,
       Wahleinheit          SMALLINT,
       Gebietsart           INTEGER,
       Nummer               INTEGER,
       Roemisch             SMALLINT,
       Name                 VARCHAR(200),
       Kuerzel              VARCHAR(200),
       Position             INTEGER,
       Wahlberechtigte      INTEGER,
       GUIEingabeErlaubt    SMALLINT,
       Postalvote           SMALLINT,
       VoteValue            INTEGER,
       Zipcode              VARCHAR(12)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Gebiet_Ergebniseingang (
       ID_Gebiet            VARCHAR(13) NOT NULL,
       ID_Ergebniseingang   VARCHAR(13) NOT NULL,
       PRIMARY KEY (ID_Gebiet,ID_Ergebniseingang)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Gebiet_Gebiet (
       ID_Elterngebiet      VARCHAR(13) NOT NULL,
       ID_Untergebiet       VARCHAR(13) NOT NULL,
       PRIMARY KEY (ID_Elterngebiet, ID_Untergebiet)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Gebietsstatus (
       ID_Gebietsstatus     VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Ergebniseingang   VARCHAR(13),
       ID_Gebiet            VARCHAR(13),
       Wahlergebnisart      INTEGER,
       Korrekturnummer      INTEGER,
       AnzahlErgebnisseKumuliert INTEGER,
       Vollstaendig         SMALLINT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Gruppe (
       ID_Gruppe            VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Wahl              VARCHAR(100),
       ID_Listenkombination VARCHAR(13),
       Schluessel           INTEGER,
       Gruppenart           INTEGER,
       NameLang             VARCHAR(80),
       NameKurz             VARCHAR(80),
       KautionGestellt      SMALLINT,
       Farbe                VARCHAR(200)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE GruppeGebietsspezifisch (
       ID_GruppeGebietsspezifisch VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_UebergeordneteGG  VARCHAR(13),
       ID_Gruppe            VARCHAR(13),
       ID_Gebiet            VARCHAR(13),
       ID_Liste             VARCHAR(13),
       Position             INTEGER,
       ListeZugelassen      SMALLINT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Konflikt (
       ID_Konflikt          VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Ergebniseingang   VARCHAR(13) NOT NULL,
       ID_LosAlternative    VARCHAR(13),
       Nummer               INTEGER,
       Konfliktart          INTEGER
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Liste (
       ID_Liste             VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Wahl              VARCHAR(100),
       ID_Gruppe            VARCHAR(13),
       Typ                  VARCHAR(30),
       Satz                 INTEGER,     
       Name                 VARCHAR(80),       
       GeschlechtSichtbar   SMALLINT,
       PublicationLanguage  VARCHAR(5)       
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Listenkandidatur (
       ID_Listenkandidatur  VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Liste             VARCHAR(13),
       ID_Wahl              VARCHAR(100),
       ID_Personendaten     VARCHAR(15),
       Listenplatz          INTEGER
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ListenkandidaturErgebnis (
       ID_ListenkandidaturErgebnis  VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Listenkandidatur  VARCHAR(13) NOT NULL,
       ID_Ergebniseingang   VARCHAR(13) NOT NULL,
       Listenplatz          INTEGER,
       Gewaehlt             SMALLINT,
       GewaehltInGruppe     SMALLINT,
       BevorzugtGewaehlt    SMALLINT,
       Losteilnehmer        SMALLINT,
       Losgewinner          SMALLINT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Listenkombination (
       ID_Listenkombination VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Wahl              VARCHAR(100),
       Bezeichnung          CHAR(1)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ListenkombinationZulassung (
       ID_ListenkombinationZulassung VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Ergebniseingang   VARCHAR(13) NOT NULL,
       ID_Listenkombination VARCHAR(13) NOT NULL,
       ID_Gruppe            VARCHAR(13),
       Zugelassen           SMALLINT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ListenplatzNeu (
       ID_ListenplatzNeu    VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Liste             VARCHAR(13) NOT NULL,
       ID_Ergebniseingang   VARCHAR(13) NOT NULL,
       Geaendert            SMALLINT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Personendaten (
       ID_Personendaten     VARCHAR(15) NOT NULL PRIMARY KEY,
       ID_PersonendatenAgent     VARCHAR(15),
       Nachname             VARCHAR(200),
       Vorname              VARCHAR(200),
       Praefix              VARCHAR(20),
       Initialen            VARCHAR(20),
       Titel                VARCHAR(200),
       Geschlecht           INTEGER,
       Generation           VARCHAR(10),
       Land                 VARCHAR(5),
       Wohnort              VARCHAR(200),
       Kontakt_Land         VARCHAR(5),
       Kontakt_Wohnort      VARCHAR(200),
       Kontakt_PLZ          VARCHAR(200),
       Kontakt_Strasse      VARCHAR(200),
       Benennbar            SMALLINT DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Recht (
       ID_Recht             VARCHAR(30) NOT NULL PRIMARY KEY,
       Name                 VARCHAR(200)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Rechtegruppe (
       ID_Rechtegruppe      VARCHAR(30) NOT NULL PRIMARY KEY,
       Name                 VARCHAR(200),
       Beschreibung         VARCHAR(200)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Rechtegruppe_Anwender (
       ID_Rechtegruppe      VARCHAR(30) NOT NULL,
       ID_Anwender          VARCHAR(30) NOT NULL,
       PRIMARY KEY (ID_Rechtegruppe, ID_Anwender)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Rechtegruppe_Recht (
       ID_Rechtegruppe      VARCHAR(30) NOT NULL,
       ID_Recht             VARCHAR(30) NOT NULL,
       PRIMARY KEY (ID_Rechtegruppe, ID_Recht)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Repository (
       ID_Repository        VARCHAR(13) NOT NULL PRIMARY KEY,
       Name                 VARCHAR(200) NOT NULL UNIQUE,
       Wert                 VARCHAR(4000)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Restsitzverteilung (
       ID_Restsitzverteilung VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Ergebniseingang   VARCHAR(13) NOT NULL,
       ID_Liste             VARCHAR(13),
       ID_Gruppe            VARCHAR(13),
       ID_Listenkombination VARCHAR(13),
       Verteilung           INTEGER,
       Sitze                INTEGER,
       SitzeRest            INTEGER,
       SitzeGesamtZuVerteilen INTEGER
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Schwellwert (
       ID_Schwellwert       VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Wahl VARCHAR(100),
       Name                 VARCHAR(200),
       Schwellwertart       INTEGER,
       Wert                 DECIMAL(12,2)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE SitzberechnungErgebnis (
       ID_SitzberechnungErgebnis VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Ergebniseingang   VARCHAR(13) NOT NULL,
       ID_Liste             VARCHAR(13),
       ID_Gruppe            VARCHAR(13),
       ID_Listenkombination VARCHAR(13),
       Verteilung           INTEGER,
       Schrittnummer        INTEGER,
       Schritttyp           INTEGER,
       Sitze                INTEGER,
       Zaehler              INTEGER,
       Nenner               INTEGER,
       ZaehlerVomNenner     INTEGER,
       NennerVomNenner      INTEGER,
       ZaehlerVomRest       INTEGER,
       NennerVomRest        INTEGER,
       Losentscheid         SMALLINT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Sitzverteilung (
       ID_Sitzverteilung    VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Ergebniseingang   VARCHAR(13),
       ID_Liste             VARCHAR(13),
       ID_Gruppe            VARCHAR(13),
       ID_Listenkombination VARCHAR(13),
       SitzeGesamtzahl      INTEGER
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Stimmergebnis (
       ID_Stimmergebnis     VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Ergebniseingang   VARCHAR(13),
       ID_Gebiet            VARCHAR(13),
       ID_GruppeGebietsspezifisch VARCHAR(13),
       ID_Listenkandidatur VARCHAR(13),
       Wahlergebnisart      INTEGER,
       Stimmen              INTEGER,
       Stimmart             INTEGER
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE StimmergebnisseUntergebiete (
       ID_StimmergebnisseUntergebiete VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Ergebniseingang   VARCHAR(13),
       ID_Gebiet            VARCHAR(13),
       ErgebnisseXML        TEXT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Unterverteilung (
       ID_Unterverteilung   VARCHAR(13) NOT NULL PRIMARY KEY,
       ID_Ergebniseingang   VARCHAR(13) NOT NULL,
       ID_Gruppe            VARCHAR(13),
       ID_Listenkombination VARCHAR(13),
       Stimmen              INTEGER,
       Sitze                INTEGER
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Wahl (
       ID_Wahl              VARCHAR(100) NOT NULL PRIMARY KEY,
       ID_Wurzelgebiet      VARCHAR(13) NULL UNIQUE,
       ID_Wahlgebiet        VARCHAR(13) NULL,
       Wahlart              INTEGER,
       Wahlebene            INTEGER,
       Wahlkategorie        VARCHAR(5),
       Name                 VARCHAR(200),
       ElectionDomain       VARCHAR(200),
       ElectionDomainId     VARCHAR(4),
       Termin               TIMESTAMP,       
       Vorrangschwelle      INTEGER,
       AnzahlSitze          INTEGER,
       GebietsartAuswertungseinheit INTEGER,
       GebietsartErfassungseinheit INTEGER,
       AktuelleWahlergebnisart INTEGER,
       DatumNominierung     TIMESTAMP,
       StandMetadaten       TIMESTAMP NULL,
       GeschlossenMetadaten TIMESTAMP NULL,
       Status               INTEGER,
       Freigegeben          TIMESTAMP NULL,
       LetzteAenderung      TIMESTAMP NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE Alternative ADD FOREIGN KEY(ID_Konflikt) REFERENCES Konflikt (ID_Konflikt);
ALTER TABLE Alternative ADD FOREIGN KEY (ID_Listenkombination) REFERENCES Listenkombination (ID_Listenkombination);
ALTER TABLE Alternative ADD FOREIGN KEY (ID_Gruppe) REFERENCES Gruppe (ID_Gruppe);
ALTER TABLE Alternative ADD FOREIGN KEY (ID_Liste) REFERENCES Liste (ID_Liste);
ALTER TABLE Alternative ADD FOREIGN KEY (ID_Personendaten) REFERENCES Personendaten (ID_Personendaten);

ALTER TABLE Anwender ADD FOREIGN KEY (ID_Gebiet) REFERENCES Gebiet (ID_Gebiet);

ALTER TABLE Besonderheit ADD FOREIGN KEY (ID_Ergebniseingang) REFERENCES Ergebniseingang (ID_Ergebniseingang);
ALTER TABLE Besonderheit ADD FOREIGN KEY (ID_Listenkombination) REFERENCES Listenkombination (ID_Listenkombination);
ALTER TABLE Besonderheit ADD FOREIGN KEY (ID_Gruppe) REFERENCES Gruppe (ID_Gruppe);
ALTER TABLE Besonderheit ADD FOREIGN KEY (ID_Liste) REFERENCES Liste (ID_Liste);
ALTER TABLE Besonderheit ADD FOREIGN KEY (ID_Personendaten) REFERENCES Personendaten (ID_Personendaten);
ALTER TABLE Besonderheit ADD FOREIGN KEY (ID_UebergeordneteBesonderheit) REFERENCES Besonderheit (ID_Besonderheit);

ALTER TABLE  DHondtQuotient ADD FOREIGN KEY (ID_Restsitzverteilung) REFERENCES Restsitzverteilung (ID_Restsitzverteilung);

ALTER TABLE  Ergebniseingang ADD FOREIGN KEY (ID_Erfassungseinheit) REFERENCES Gebiet (ID_Gebiet);
ALTER TABLE  Ergebniseingang ADD FOREIGN KEY (ID_Wahl) REFERENCES Wahl (ID_Wahl);

ALTER TABLE  FiktiveSitzverteilung ADD FOREIGN KEY (ID_Ergebniseingang) REFERENCES Ergebniseingang (ID_Ergebniseingang);
ALTER TABLE  FiktiveSitzverteilung ADD FOREIGN KEY (ID_Gruppe) REFERENCES Gruppe (ID_Gruppe);

ALTER TABLE  Gebiet ADD FOREIGN KEY (ID_UebergeordnetesGebiet) REFERENCES Gebiet (ID_Gebiet);
ALTER TABLE  Gebiet ADD FOREIGN KEY (ID_Wahl) REFERENCES Wahl (ID_Wahl);
ALTER TABLE  Gebiet ADD FOREIGN KEY (ID_LetzterEingang) REFERENCES Ergebniseingang (ID_Ergebniseingang);

ALTER TABLE  Gebiet_Ergebniseingang ADD FOREIGN KEY (ID_Ergebniseingang) REFERENCES Ergebniseingang (ID_Ergebniseingang);
ALTER TABLE  Gebiet_Ergebniseingang ADD FOREIGN KEY (ID_Gebiet) REFERENCES Gebiet (ID_Gebiet);

ALTER TABLE  Gebiet_Gebiet ADD FOREIGN KEY (ID_Elterngebiet) REFERENCES Gebiet (ID_Gebiet);
ALTER TABLE  Gebiet_Gebiet ADD FOREIGN KEY (ID_Untergebiet) REFERENCES Gebiet (ID_Gebiet);

ALTER TABLE  Gebietsstatus ADD FOREIGN KEY (ID_Gebiet) REFERENCES Gebiet (ID_Gebiet);
ALTER TABLE  Gebietsstatus ADD FOREIGN KEY (ID_Ergebniseingang) REFERENCES Ergebniseingang (ID_Ergebniseingang);

ALTER TABLE  Gruppe ADD FOREIGN KEY (ID_Wahl) REFERENCES Wahl (ID_Wahl);
ALTER TABLE  Gruppe ADD FOREIGN KEY (ID_Listenkombination) REFERENCES Listenkombination (ID_Listenkombination);

ALTER TABLE  GruppeGebietsspezifisch ADD FOREIGN KEY (ID_UebergeordneteGG) 
        REFERENCES GruppeGebietsspezifisch (ID_GruppeGebietsspezifisch);
ALTER TABLE GruppeGebietsspezifisch ADD FOREIGN KEY (ID_Gebiet) REFERENCES Gebiet (ID_Gebiet);
ALTER TABLE GruppeGebietsspezifisch ADD  FOREIGN KEY (ID_Gruppe) REFERENCES Gruppe (ID_Gruppe);
ALTER TABLE GruppeGebietsspezifisch ADD FOREIGN KEY (ID_Liste) REFERENCES Liste (ID_Liste);

ALTER TABLE  Konflikt ADD FOREIGN KEY (ID_Ergebniseingang) REFERENCES Ergebniseingang (ID_Ergebniseingang);
ALTER TABLE  Konflikt ADD FOREIGN KEY (ID_LosAlternative) REFERENCES Alternative (ID_Alternative);

ALTER TABLE  Liste ADD FOREIGN KEY (ID_Wahl) REFERENCES Wahl (ID_Wahl);
ALTER TABLE  Liste ADD FOREIGN KEY (ID_Gruppe) REFERENCES Gruppe (ID_Gruppe);

ALTER TABLE  Listenkandidatur ADD FOREIGN KEY (ID_Personendaten) REFERENCES Personendaten (ID_Personendaten);
ALTER TABLE  Listenkandidatur ADD FOREIGN KEY (ID_Wahl) REFERENCES Wahl (ID_Wahl);
ALTER TABLE  Listenkandidatur ADD FOREIGN KEY (ID_Liste) REFERENCES Liste (ID_Liste);

ALTER TABLE  ListenkandidaturErgebnis ADD FOREIGN KEY (ID_Ergebniseingang) REFERENCES Ergebniseingang (ID_Ergebniseingang);
ALTER TABLE  ListenkandidaturErgebnis ADD FOREIGN KEY (ID_Listenkandidatur) REFERENCES Listenkandidatur (ID_Listenkandidatur);

ALTER TABLE  Listenkombination ADD FOREIGN KEY (ID_Wahl) REFERENCES Wahl (ID_Wahl);

ALTER TABLE  ListenkombinationZulassung ADD FOREIGN KEY (ID_Ergebniseingang) REFERENCES Ergebniseingang (ID_Ergebniseingang);
ALTER TABLE  ListenkombinationZulassung ADD FOREIGN KEY (ID_Listenkombination) REFERENCES Listenkombination (ID_Listenkombination);
ALTER TABLE  ListenkombinationZulassung ADD FOREIGN KEY (ID_Gruppe) REFERENCES Gruppe (ID_Gruppe);

ALTER TABLE  ListenplatzNeu ADD FOREIGN KEY (ID_Ergebniseingang) REFERENCES Ergebniseingang (ID_Ergebniseingang);
ALTER TABLE  ListenplatzNeu ADD FOREIGN KEY (ID_Liste) REFERENCES Liste (ID_Liste);

ALTER TABLE  Personendaten ADD FOREIGN KEY (ID_PersonendatenAgent) REFERENCES Personendaten (ID_Personendaten);

ALTER TABLE  Rechtegruppe_Anwender ADD FOREIGN KEY (ID_Anwender) REFERENCES Anwender (ID_Anwender);
ALTER TABLE  Rechtegruppe_Anwender ADD FOREIGN KEY (ID_Rechtegruppe) REFERENCES Rechtegruppe (ID_Rechtegruppe);

ALTER TABLE  Rechtegruppe_Recht ADD FOREIGN KEY (ID_Recht) REFERENCES Recht (ID_Recht);
ALTER TABLE  Rechtegruppe_Recht ADD FOREIGN KEY (ID_Rechtegruppe) REFERENCES Rechtegruppe (ID_Rechtegruppe);

ALTER TABLE  Restsitzverteilung ADD FOREIGN KEY (ID_Ergebniseingang) REFERENCES Ergebniseingang (ID_Ergebniseingang);
ALTER TABLE  Restsitzverteilung ADD FOREIGN KEY (ID_Liste) REFERENCES Liste (ID_Liste);
ALTER TABLE  Restsitzverteilung ADD FOREIGN KEY (ID_Gruppe) REFERENCES Gruppe (ID_Gruppe);
ALTER TABLE  Restsitzverteilung ADD FOREIGN KEY (ID_Listenkombination) REFERENCES Listenkombination (ID_Listenkombination);

ALTER TABLE  Schwellwert ADD FOREIGN KEY (ID_Wahl) REFERENCES Wahl (ID_Wahl);

ALTER TABLE  SitzberechnungErgebnis ADD FOREIGN KEY (ID_Ergebniseingang) REFERENCES Ergebniseingang (ID_Ergebniseingang);
ALTER TABLE  SitzberechnungErgebnis ADD FOREIGN KEY (ID_Liste) REFERENCES Liste (ID_Liste);
ALTER TABLE  SitzberechnungErgebnis ADD FOREIGN KEY (ID_Gruppe) REFERENCES Gruppe (ID_Gruppe);
ALTER TABLE  SitzberechnungErgebnis ADD FOREIGN KEY (ID_Listenkombination) REFERENCES Listenkombination (ID_Listenkombination);

ALTER TABLE  Sitzverteilung ADD FOREIGN KEY (ID_Ergebniseingang) REFERENCES Ergebniseingang (ID_Ergebniseingang);
ALTER TABLE  Sitzverteilung ADD FOREIGN KEY (ID_Liste) REFERENCES Liste (ID_Liste);
ALTER TABLE  Sitzverteilung ADD FOREIGN KEY (ID_Gruppe) REFERENCES Gruppe (ID_Gruppe);
ALTER TABLE  Sitzverteilung ADD FOREIGN KEY (ID_Listenkombination) REFERENCES Listenkombination (ID_Listenkombination);

ALTER TABLE  Stimmergebnis ADD FOREIGN KEY (ID_GruppeGebietsspezifisch)
        REFERENCES GruppeGebietsspezifisch (ID_GruppeGebietsspezifisch);
ALTER TABLE  Stimmergebnis ADD FOREIGN KEY (ID_Gebiet) REFERENCES Gebiet (ID_Gebiet);
ALTER TABLE  Stimmergebnis ADD FOREIGN KEY (ID_Listenkandidatur) REFERENCES Listenkandidatur (ID_Listenkandidatur);
ALTER TABLE  Stimmergebnis ADD FOREIGN KEY (ID_Ergebniseingang) REFERENCES Ergebniseingang (ID_Ergebniseingang);

ALTER TABLE  StimmergebnisseUntergebiete ADD FOREIGN KEY (ID_Gebiet) REFERENCES Gebiet (ID_Gebiet);
ALTER TABLE  StimmergebnisseUntergebiete ADD FOREIGN KEY (ID_Ergebniseingang) REFERENCES Ergebniseingang (ID_Ergebniseingang);

ALTER TABLE  Unterverteilung ADD FOREIGN KEY (ID_Ergebniseingang) REFERENCES Ergebniseingang (ID_Ergebniseingang);
ALTER TABLE  Unterverteilung ADD FOREIGN KEY (ID_Gruppe) REFERENCES Gruppe (ID_Gruppe);
ALTER TABLE  Unterverteilung ADD FOREIGN KEY (ID_Listenkombination) REFERENCES Listenkombination (ID_Listenkombination);

ALTER TABLE  Wahl ADD FOREIGN KEY (ID_Wurzelgebiet) REFERENCES Gebiet (ID_Gebiet);
ALTER TABLE  Wahl ADD FOREIGN KEY (ID_Wahlgebiet) REFERENCES Gebiet (ID_Gebiet);

CREATE INDEX stimmergebnis_index_1 ON Stimmergebnis (ID_Ergebniseingang, ID_Gebiet, ID_GruppeGebietsspezifisch, ID_Listenkandidatur);
CREATE INDEX stimmergebnis_index_2 ON Stimmergebnis (ID_Ergebniseingang, ID_Gebiet, Stimmart, ID_Listenkandidatur);
CREATE INDEX stimmergebnis_index_3 ON Stimmergebnis (ID_Ergebniseingang, ID_Gebiet, Stimmart);
CREATE INDEX stimmergebnis_index_4 ON Stimmergebnis (ID_Ergebniseingang, ID_Gebiet);