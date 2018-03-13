package de.ivu.ejb.bmp;

/**
 * Base implementation of a data container
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class ModelImpl implements Model, Cloneable {
  private static final long serialVersionUID = -2697777817753612742L;

  private transient ThreadLocal<Boolean> _isModifiedTL;

  /**
   * Function returns <code>true</code>, if the data of the model was changed. The variable
   * _isModified has to be set to true by the derived classes when data is changed.
   */
  public synchronized boolean isModified() {
    if (_isModifiedTL != null) {
      Boolean isModified = _isModifiedTL.get();
      return isModified != null ? isModified.booleanValue() : false;
    }
    return false;
  }

  /**
   * Function sets the state to "modified"
   */
  public synchronized void setModified() {
    if (_isModifiedTL == null) {
      _isModifiedTL = new ThreadLocal<Boolean>();
    }
    _isModifiedTL.set(Boolean.TRUE);
  }

  /**
   * Function sets the state to "non-modified"
   */
  synchronized void resetModified() {
    _isModifiedTL = null; // non-modified is not modified for all!
  }

  protected String checkLength(String str, int maxLen) {
    if (str != null && str.length() > maxLen) {
      return str.substring(0, maxLen);
    } else {
      return str;
    }
  }

  protected static boolean different(Object a, Object b) {
    return a == null ? b != null : !a.equals(b);
  }

  @Override
  protected Object clone() {
    try {
      return super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e.getMessage(), e); // cannot occur, because this class is clonable
    }
  }

}
