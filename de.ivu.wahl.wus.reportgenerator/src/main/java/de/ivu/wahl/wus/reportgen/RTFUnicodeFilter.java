package de.ivu.wahl.wus.reportgen;

import static java.lang.Math.min;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class RTFUnicodeFilter extends FilterWriter {
  private static final int LINE_FEED = 10;

  public RTFUnicodeFilter(Writer out) {
    super(out);
  }

  @Override
  public void write(int c) throws IOException {
    if (c > 0x7f) {
      super.write("\\u" + Integer.toString(c) + '?'); //$NON-NLS-1$
    } else if (c == LINE_FEED) {
      // Replace a LF by the string "\par"
      super.write("\\line "); //$NON-NLS-1$
    } else {
      super.write(c);
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    int end = min(cbuf.length, off + len);
    for (int i = off; i < end; i++) {
      write(cbuf[i]);
    }
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }
}
