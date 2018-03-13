/*
 *  --------------------------------------------------------------
 *  Company   : IVU Traffic Technologies AG
 *  --------------------------------------------------------------
 */
package de.ivu.util.misc;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

/**
 * Copies data from an {@link InputStream} to an {@link OutputStream} .
 * 
 * @author kie@ivu.de
 * @created 2001-06-27
 * @version $Id $
 */
public final class StreamCopy {

  private static final Logger LOGGER = Logger.getLogger(StreamCopy.class);

  final static int BUFFER_SIZE = 100000;
  final static byte[] buffer = new byte[BUFFER_SIZE];

  /** Constructor for the StreamCopy object */
  private StreamCopy() {
    // forbidding instantiation
  }

  /**
   * copies data from <tt>InputStream</tt> to <tt>OutputStream</tt> , doesn't close the streams.
   * 
   * @param in input stream
   * @param out output stream
   */
  public static void copy(InputStream in, OutputStream out) {
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
