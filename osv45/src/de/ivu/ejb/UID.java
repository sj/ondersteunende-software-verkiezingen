package de.ivu.ejb;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * A <code>UID</code> represents an identifier that is unique over time with respect to the host it
 * is generated on, or one of 2 <sup>16</sup> "well-known" identifiers.
 * <p>
 * The {@link #UID()} constructor can be used to generate an identifier that is unique over time
 * with respect to the host it is generated on. The {@link #UID(byte)} constructor can be used to
 * create one of 2 <sup>8 </sup> well-known identifiers.
 * <p>
 * A <code>UID</code> instance contains two primitive values:
 * <ul>
 * <li><code>time</code>, a <code>long</code> equal to a time (as returned by
 * {@link System#currentTimeMillis()} ) at which the VM that this <code>UID</code> was generated in
 * was alive, or zero for a well-known <code>UID</code>
 * <li><code>count</code>, a <code>short</code> to distinguish <code>UID</code> s generated in the
 * same VM with the same <code>time</code> value
 * </ul>
 * <p>
 * An independently generated <code>UID</code> instance is unique over time with respect to the host
 * it is generated on as long as the host requires more than one millisecond to reboot and its
 * system clock is never set backward.
 * <p>
 * Reset to 1.1.2009 as the begining of counting (12.05.2009)
 * 
 * @author cos@ivu.de
 * @since 2.0.2
 */
public final class UID {

  private static final Object __lock = new Object();
  private static volatile long __lastTime = System.currentTimeMillis();
  private static volatile byte __lastCount = Byte.MIN_VALUE;
  private static final String __padLong;
  private static final String __padShort;
  private static final int __padLengthShort = 9;
  private static final long __eraBeginLong = 0;
  private static final long __eraBeginShort; // 1.1.2005 with new 8-char UIDs

  static {
    char[] pad = new char[Long.toString(Long.MAX_VALUE, Character.MAX_RADIX).length()];
    for (int i = 0; i < pad.length; i++) {
      pad[i] = '0';
    }
    __padLong = new String(pad);
    __padShort = __padLong.substring(0, __padLengthShort);

    Calendar begin = Calendar.getInstance(TimeZone.getTimeZone("GMT")); //$NON-NLS-1$
    begin.setTimeInMillis(0);
    begin.set(Calendar.YEAR, 2009);
    __eraBeginShort = begin.getTimeInMillis();
  }

  /**
   * a time (as returned by {@link System#currentTimeMillis()} ) at which the VM that this
   * <code>UID</code> was generated in was alive
   * 
   * @serial
   */
  private final long _time;

  /**
   * 16-bit number to distinguish <code>UID</code> instances created in the same VM with the same
   * time value
   * 
   * @serial
   */
  private final byte _count;

  /**
   * Generates a <code>UID</code> that is unique over time with respect to the host that it was
   * generated on.
   */
  public UID() {
    synchronized (__lock) {
      if (__lastCount == Byte.MAX_VALUE) {
        boolean done = false;
        while (!done) {
          long now = System.currentTimeMillis();
          if (now == __lastTime) {
            // pause for a moment to wait for time to change
            try {
              Thread.sleep(1);
            } catch (java.lang.InterruptedException e) {
              System.out.println("Thread was interrupted while sleeping 1 millisecond, " //$NON-NLS-1$
                  + "if it did not sleap long enough, it will be sent sleeping again"); //$NON-NLS-1$
            }
            continue;
          } else {
            __lastTime = now;
            __lastCount = Byte.MIN_VALUE;
            done = true;
          }
        }
      }
      _time = __lastTime;
      _count = __lastCount++;
    }
  }

  /**
   * Creates a "well-known" <code>UID</code>. There are 2 <sup>8</sup> possible such well-known ids.
   * <p>
   * A <code>UID</code> created via this constructor will not clash with any <code>UID</code> s
   * generated via the no-arg constructor.
   * 
   * @param num number for well-known <code>UID</code>
   */
  public UID(byte num) {
    _time = 0;
    _count = num;
  }

  @Override
  public int hashCode() {
    return (int) _time + _count;
  }

  @Override
  public boolean equals(Object obj) {
    if ((obj != null) && (obj instanceof UID)) {
      UID uid = (UID) obj;
      return (_count == uid._count && _time == uid._time);
    } else {
      return false;
    }
  }

  /**
   * Returns a string representation of this <code>UID</code>. This method will only work correctly
   * until the Year 6432.
   * 
   * @return a string representation of this <code>UID</code>
   */
  @Override
  public String toString() {
    String strVal = Long.toString(((_time - __eraBeginShort) << 8) + _count, Character.MAX_RADIX);
    if (strVal.length() <= __padLengthShort) {
      return (__padShort.substring(strVal.length()) + strVal).toUpperCase();
    }
    strVal = Long.toString(((_time - __eraBeginLong) << 8) + _count, Character.MAX_RADIX);
    return (__padLong.substring(strVal.length()) + strVal).toUpperCase();
  }
}
