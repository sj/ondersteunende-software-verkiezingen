/*
 * GBAProposalProvider
 * 
 * Created on Jan 6, 2009 Copyright (c) 2009 Kiesraad
 */

package de.ivu.wahl.wus.utils.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;

/*
 * This proposal provider proposes replacement of currently entered characters
 * by GBA characters. The character in front of the current cursor position is
 * replaced. Entering a letter proposes characters of the same letter class,
 * entering a blank " " proposes all gba characters
 * 
 * @author mike@ivu.de, IVU Traffic Technologies AG
 * 
 * @version $Id: GBAProposalProvider.java,v 1.1 2009/10/26 10:22:46 tdu Exp $
 */
public class GBAProposalProvider implements IContentProposalProvider {

  private static final char BLANK = ' ';
  /*
   * The proposals provided.
   */
  private final String[]    gbaCharClasses;

  /**
   * each pro Constructor
   * 
   * @param gbaCharClasses each String contains a set of gba characters. the first character
   *          specifies the "standard character" to be substituted
   */
  public GBAProposalProvider(final String[] gbaCharClasses) {
    super();
    this.gbaCharClasses = gbaCharClasses;
  }

  public IContentProposal[] getProposals(final String contents, final int position) {
    if (position < 1 || contents.length() == 0) {
      return new IContentProposal[0];
    }

    final int positionToReplace = position - 1;

    final List<IContentProposal> contentProposals = new ArrayList<IContentProposal>();
    for (int i = 0; i < gbaCharClasses.length; i++) {
      final char[] gbaChars = gbaCharClasses[i].toCharArray();
      final char charClass = gbaChars[0];
      final char charToReplace = contents.charAt(positionToReplace);
      final boolean classMatches = charClass == charToReplace || charToReplace == BLANK;
      if (classMatches) for (int g = 1; g < gbaChars.length; g++) {
        contentProposals.add(makeContentProposal(contents, position, positionToReplace, gbaChars[g]));
      }
    }
    return contentProposals.toArray(new IContentProposal[contentProposals.size()]);
  }

  IContentProposal makeContentProposal(final String contents, final int cursorPosition, final int positionToReplace,
      final char gbaChar) {
    final String proposal;
    if (positionToReplace < 0) {
      proposal = gbaChar + contents;
    } else {
      final StringBuilder sb = new StringBuilder(contents);
      sb.setCharAt(positionToReplace, gbaChar);
      proposal = sb.toString();
      // proposal = contents.substring(0, contents.length() - 1) + gbaChar;
    }
    return new IContentProposal() {

      public String getContent() {
        return proposal;
      }

      public String getDescription() {
        return null;
      }

      public String getLabel() {
        return gbaChar + " - " + proposal; //$NON-NLS-1$
      }

      public int getCursorPosition() {
        return cursorPosition;
      }
    };
  }

}
