/*
 *  --------------------------------------------------------------
 *  Company   : IVU Traffic Technologies AG
 *  --------------------------------------------------------------
 */
package de.ivu.util.misc;

import java.io.Reader;
import java.io.Writer;

import org.apache.log4j.Logger;

/**
 * Copies data from a {@link Reader} to a {@link Writer} .
 * 
 * @author cos@ivu.de
 * @created 2009/04/03
 * @version $Id $
 */
public final class CharacterStreamCopy {

  private static final Logger LOGGER = Logger.getLogger(CharacterStreamCopy.class);

  final static int BUFFER_SIZE = 100000;
  final static char[] buffer = new char[BUFFER_SIZE];

  /** Constructor for the StreamCopy object */
  private CharacterStreamCopy() {
    // forbidding instantiation
  }

  /**
   * copies data from a {@link Reader} to a {@link Writer}, doesn't close the streams.
   * 
   * @param in input reader
   * @param out output writer
   */
  public static void copy(Reader in, Writer out) {
    try {
      while (true) {
        synchronized (buffer) {
          int amountRead = in.read(buffer);
          if (amountRead == -1) {
            break;
          }
          out.write(buffer, 0, amountRead);
        }
      }
    } catch (Exception ex) {
      LOGGER.error("Copy not successful: " + ex); //$NON-NLS-1$
    }
  }
}
