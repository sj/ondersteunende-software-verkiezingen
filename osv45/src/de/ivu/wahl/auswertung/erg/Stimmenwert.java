/*
 * Created on 04.02.2004
 */
package de.ivu.wahl.auswertung.erg;

import java.io.Serializable;

/**
 * Stimmenwert
 * 
 * @author ugo
 * @created 04.02.2004
 */
public class Stimmenwert implements Serializable {
  private static final long serialVersionUID = 2593295122119447060L;

  public final static int AKT = 0;
  public final static int VGL = 1;
  public final static int VGL2 = 2;

  private final int _stimmart;
  private final int[] _stimmen = {-1, -1, -1};
  private int[] _gesamt = {-1, -1, -1};

  public Stimmenwert(int stimmart) {
    _stimmart = stimmart;
  }

  /**
   * @param akt
   * @param vgl
   * @param stimmart
   */
  public Stimmenwert(int akt, int vgl, int stimmart) {
    _stimmen[AKT] = akt;
    _stimmen[VGL] = vgl;
    _stimmart = stimmart;
  }

  public double getProzent(int art) {
    if (_stimmen[art] < 0 || _gesamt[art] <= 0) {
      return -1;
    } else {
      return _stimmen[art] * 100.0 / _gesamt[art];
    }
  }

  public void setGesamtStimmen(int gesamt, int art) {
    _gesamt[art] = gesamt;
  }

  public void setGesamtStimmen(int[] gesamt) {
    _gesamt = gesamt;
  }

  public int[] getGesamtStimmen() {
    return _gesamt;
  }

  public void setStimmen(int stimmen, int art) {
    _stimmen[art] = stimmen;
  }

  public int getStimmen(int art) {
    return _stimmen[art];
  }

  /**
   * @return Tripel der Stimmenanzahl (aktuell, Vergleich, Vergleich 2)
   */
  public int[] getStimmen() {
    return _stimmen;
  }

  public double getProzDiff() {
    double prozAkt = getProzent(AKT);
    double prozVgl = getProzent(VGL);
    if (prozAkt < 0 || prozVgl < 0) {
      return -1;
    } else {
      return prozAkt - prozVgl;
    }
  }

  public int getDiff() {
    return (_stimmen[AKT] < 0 ? 0 : _stimmen[AKT]) - (_stimmen[VGL] < 0 ? 0 : _stimmen[VGL]);
  }

  /**
   * @return Stimmart
   */
  public int getStimmart() {
    return _stimmart;
  }
}
