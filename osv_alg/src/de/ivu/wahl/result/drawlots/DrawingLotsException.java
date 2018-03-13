/*
 * DrawingLotsException
 * 
 * Created on 03.03.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.drawlots;

import java.util.List;

/**
 * Unchecked exception thrown in the algorithm when a drawing lots is required but the
 * DrawingLotsCallback cannot decide which one shall be chosen.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class DrawingLotsException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private final List<? extends DrawingLotsAlternative> _alternatives;
  private final DecisionType _decisionType;

  public DrawingLotsException(final List<? extends DrawingLotsAlternative> alternatives,
      final DecisionType decisionType) {
    super();
    this._alternatives = alternatives;
    this._decisionType = decisionType;
  }

  public List<? extends DrawingLotsAlternative> getAlternatives() {
    return _alternatives;
  }

  public DecisionType getDecisionType() {
    return _decisionType;
  }
}
