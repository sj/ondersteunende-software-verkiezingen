package de.ivu.wahl.modell;

/**
 * StimmergebnisModel
 * 
 * @author cos@ivu.de (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public interface StimmergebnisModel extends BasicStimmergebnisModel {
  int STIMMART_KEINE = 0;
  int STIMMART_LISTENSTIMME = 2;

  int STIMMEN_LEER = -1;
  int STIMMEN_EINTRAG_NICHT_VORHANDEN = -2;

}
