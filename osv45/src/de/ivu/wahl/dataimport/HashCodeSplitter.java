/*
 * HashCodeSplitter
 * 
 * Created on 09.10.2017
 * Copyright (c) 2017 IVU Traffic Technologies AG
 */
package de.ivu.wahl.dataimport;

import java.util.Arrays;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

/**
 * Splits the hashcode into parts that are either shown to the use or must be entered (completed) by
 * the user. There 2 * NUMBER_OF_INPUTS + 1 alternating parts, the first is shown, the second must
 * be entered, the third is shown and so on.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class HashCodeSplitter {
  public static final String HIDDEN_INPUT = "XXXX"; //$NON-NLS-1$

  private static final int NUMBER_OF_INPUTS = 2;
  private static final int TOKEN_LENGTH = 4;

  private final String hashCode;

  private final String[] inputs = new String[NUMBER_OF_INPUTS];
  private final int[] inputStartIndex = new int[NUMBER_OF_INPUTS];
  private final int[] inputStopIndex = new int[NUMBER_OF_INPUTS];

  public HashCodeSplitter() {
    this(null);
  }

  public HashCodeSplitter(@SuppressWarnings("hiding") String hashCode) {
    this.hashCode = (hashCode == null ? StringUtils.EMPTY : hashCode.trim());

    int minLength = NUMBER_OF_INPUTS * (TOKEN_LENGTH + 1) - 1;
    if (this.hashCode.length() < minLength) {
      Arrays.fill(inputStartIndex, 0);
      Arrays.fill(inputStopIndex, 0);
    } else {
      fillIndecesAtRandom();
    }
  }

  private void fillIndecesAtRandom() {
    int numberOfTokens = (this.hashCode.length() + 1) / (TOKEN_LENGTH + 1);
    if (numberOfTokens < NUMBER_OF_INPUTS) {
      Arrays.fill(inputStartIndex, 0);
      Arrays.fill(inputStopIndex, 0);
      return;
    }

    SortedSet<Integer> set = new TreeSet<Integer>();
    Random random = new Random();
    while (set.size() < NUMBER_OF_INPUTS) {
      set.add(random.nextInt(numberOfTokens));
    }

    int index = 0;
    for (Integer integer : set) {
      inputStartIndex[index] = integer * (TOKEN_LENGTH + 1);
      inputStopIndex[index] = integer * (TOKEN_LENGTH + 1) + TOKEN_LENGTH;
      index++;
    }
  }

  /**
   * @param index must be >= 0 and &le; NUMBER_OF_INPUTS
   * @return a part that is shown to the user
   */
  public String getPartToConfirm(int index) {
    if (index < 0 || index > NUMBER_OF_INPUTS) {
      throw new IllegalArgumentException(
          "HashCodeSplitter.getPartToConfirm(): index must be between 0 and " + NUMBER_OF_INPUTS //$NON-NLS-1$
              + " but was " + index); //$NON-NLS-1$
    }
    int startIndex = (index == 0 ? 0 : inputStopIndex[index - 1]);
    int stopIndex = (index == NUMBER_OF_INPUTS ? Integer.MAX_VALUE : inputStartIndex[index]);
    return getHashCodeSubstring(startIndex, stopIndex);
  }

  private String getHashCodeSubstring(int startIndex, int stopIndex) {
    if (hashCode == null) {
      return null;
    }
    int start = Math.min(startIndex, hashCode.length());
    int stop = Math.min(stopIndex, hashCode.length());
    return hashCode.substring(start, stop);
  }

  /**
   * @param index must be >= 0 and &lt; NUMBER_OF_INPUTS
   * @return the user input for one of the the parts that need to be entered by the user
   */
  public String getInput(int index) {
    return inputs[index];
  }

  /**
   * Sets the user input for one of the parts that need to be entered by the user to
   * <code>value</code>
   * 
   * @param index must be >= 0 and &lt; NUMBER_OF_INPUTS
   * @param value the value to be set
   */
  public void setInput(int index, String value) {
    inputs[index] = value;
  }

  public String getHashCode() {
    return hashCode;
  }

  public boolean isInputComplete() {
    for (int i = 0; i < inputs.length; i++) {
      if (StringUtils.isEmpty(inputs[i])) {
        return false;
      }
    }
    return true;
  }

  public boolean checkInput() {
    for (int i = 0; i < inputs.length; i++) {
      if (!HIDDEN_INPUT.equals(inputs[i]) && !getExpectedInput(i).equalsIgnoreCase(inputs[i])) {
        return false;
      }
    }
    return true;
  }

  private String getExpectedInput(int i) {
    return hashCode.substring(inputStartIndex[i], inputStopIndex[i]);
  }
}
