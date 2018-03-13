/*
 * DrawingLotsCallback
 * 
 * Created on 15.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.drawlots;

import java.util.List;

/**
 * Interface that must be implemented by an instance that makes the decision to the algorithm in the
 * event that a decision has to be made by drawing lots.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface DrawingLotsCallback {

  /**
   * @param alternatives is a list of alternatives to draw from
   * @param decisionType is the kind of event that requires that a decision it made by lot
   * @return the 0-based index of the selected alternative. Must be &ge; 0 and &lt;
   *         alternatives.size()
   * @throws DrawingLotsException if I cannot decide which alternative shall be chosen. Usually in
   *           this case the algorithm is terminated and the user is asked to make the decision.
   */
  public int selectAlternative(List<? extends DrawingLotsAlternative> alternatives,
      DecisionType decisionType) throws DrawingLotsException;

}
