/*
 *  --------------------------------------------------------------
 *  Project   : common used utils
 *  Comment   : copy data from InputStream to OutputStream
 *  Company   : IVU Traffic Technologies AG
 *  Author    : KIE
 *  --------------------------------------------------------------
 */
package de.ivu.wahl.wus.reportgen;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Copies data from <tt>InputStream</tt> to <tt>OutputStream</tt> .
 * 
 * @author kie@ivu.de
 * @created 2001-06-27
 * @since 1.0
 */
public final class StreamCopy {

  final static int BUFFER_SIZE = 100000;
  final static byte[] buffer = new byte[BUFFER_SIZE];

  /** Constructor for the StreamCopy object */
  private StreamCopy() {
  }

  /**
   * copies data from <tt>InputStream</tt> to <tt>OutputStream</tt> , doesn't close the streams.
   * 
   * @param in
   * @param out
   * @throws IOException
   */
  public static void copy(InputStream in, OutputStream out) throws IOException {
    while (true) {
      synchronized (buffer) {
        int amountRead = in.read(buffer);
        if (amountRead == -1) {
          break;
        }
        out.write(buffer, 0, amountRead);
      }
    }
  }
}
