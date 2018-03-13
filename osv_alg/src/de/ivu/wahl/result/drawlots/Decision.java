/*
 * Decision
 * 
 * Created on 25.02.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.drawlots;

import java.util.List;

import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.i18n.MessageKeys;
import de.ivu.wahl.result.i18n.Messages;

/**
 * A {@link Decision} holds information about the event that one alternative out of a given list of
 * alternatives was drawn.
 * 
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public class Decision {

  final DecisionType _type;
  final List<? extends DrawingLotsAlternative> _alternatives;
  final int _selectedAlternative; // 0-based index

  public Decision(final DecisionType type,
      final List<? extends DrawingLotsAlternative> alternatives,
      final int selectedAlternative) {
    this._type = type;
    this._alternatives = Util.createUnmodifiableCopy(alternatives);
    this._selectedAlternative = selectedAlternative;
  }

  public DecisionType getType() {
    return _type;
  }

  /**
   * @return the list of alternatives from which one alternative was drawn.
   */
  public List<? extends DrawingLotsAlternative> getAlternatives() {
    return _alternatives;
  }

  /**
   * @return the 0-based index of the alternative that was drawn
   */
  public int getSelectedIndex() {
    return _selectedAlternative;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(Messages
        .bind(MessageKeys.Result_Tracelog_DrawingLotsAccordingToP_0_Selected_1_Alternatives_2,
            _type.getId(),
            _selectedAlternative + 1,
            Util.displayNamedObjects(_alternatives)));

    return builder.toString();
  }
}
