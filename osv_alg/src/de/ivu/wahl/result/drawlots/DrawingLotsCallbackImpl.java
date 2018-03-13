/*
 * DrawingLotsCallbackImpl
 * 
 * Created on 25.02.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.drawlots;

import static de.ivu.wahl.result.determination.ElectionResultDeterminator.APPLOG;

import java.util.ArrayList;
import java.util.List;

import de.ivu.wahl.result.NamedObject;
import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;

/**
 * This implementation of the interface {@link DrawingLotsCallback} is initialized with a number of
 * {@link Decision}s. Whenever drawing lots is required, one of them provides the {@link Decision},
 * which lot is drawn. If no decision is available any more, the calculation must be terminated by
 * throwing a {@link DrawingLotsException}.
 * 
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public class DrawingLotsCallbackImpl implements DrawingLotsCallback {
  private int _count = 0;
  private final List<Decision> _decisions = new ArrayList<Decision>();

  public int selectAlternative(List<? extends DrawingLotsAlternative> alternatives,
      DecisionType decisionType) throws DrawingLotsException {
    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_SelectFrom_0_ElementsByLot, alternatives
        .size()));
    int i = 1;
    for (NamedObject each : alternatives) {
      APPLOG.info(Messages.applyPattern("{0}. {1}", i, each.getName())); //$NON-NLS-1$
      i++;
    }

    _count++;
    APPLOG.info(Messages.bind(MessageKeys.Result_Tracelog_ThisIsDecisionNo_0, _count));
    if (_count > _decisions.size()) {
      APPLOG.info(""); //$NON-NLS-1$
      APPLOG.info(Messages.getString(MessageKeys.Result_Tracelog_TheUserMustSelect));
      APPLOG.info(""); //$NON-NLS-1$
      throw new DrawingLotsException(Util.createUnmodifiableCopy(alternatives), decisionType);
    }

    Decision currentDecision = _decisions.get(_count - 1);
    APPLOG.info(Messages.getString(MessageKeys.Result_Tracelog_DecisionAlreadyMade));
    APPLOG.info(currentDecision.toString());
    checkDecision(alternatives, currentDecision);
    return currentDecision.getSelectedIndex();
  }

  public void addDecision(final DecisionType type,
      final List<DrawingLotsAlternative> alternatives,
      final int selectedAlternative) {
    _decisions.add(new Decision(type, alternatives, selectedAlternative));
  }

  /**
   * Check if this is the right decision. Throw a RuntimeException otherwise.
   */
  private void checkDecision(final List<? extends DrawingLotsAlternative> alternatives,
      final Decision decision) {
    int numberOfAlternatives = alternatives.size();
    if (decision.getAlternatives().size() != numberOfAlternatives) {
      throw new RuntimeException(Messages
          .bind(MessageKeys.Result_Assert_WrongNumberOfAlternatives_0_expected_1, decision
              .getAlternatives().size(), numberOfAlternatives));
    }
    for (int idx = 0; idx < numberOfAlternatives; ++idx) {
      compare(idx + 1, decision.getAlternatives().get(idx), alternatives.get(idx));
    }
  }

  private void compare(int oneBasedIndex,
      DrawingLotsAlternative expected,
      DrawingLotsAlternative given) {
    if (expected.getIdentifier().equals(given.getIdentifier())) {
      return;
    } else {
      throw new RuntimeException(Messages
          .bind(MessageKeys.Result_Assert_ErrorInAlternative_0_1_expected_2, oneBasedIndex, given
              .getIdentifier(), expected.getIdentifier()));
    }
  }

  public int getNumberOfDecisions() {
    return _decisions.size();
  }

}
