/*
 * FinderException
 * 
 * Created on 13.04.2005
 * Copyright (c) 2005 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.ejb;

/**
 * The FinderException exception must be included in the throws clause of every findMETHOD(...)
 * method of an entity Bean's home interface.
 * <p>
 * The exception is used as a standard application-level exception to report a failure to find the
 * requested EJB object(s).
 * <p>
 * This version is enhanced by passing the cause exception to the superclass using the newly
 * established Java 1.4 mechanism.
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class IVUFinderException extends javax.ejb.FinderException {

  /** long */
  private static final long serialVersionUID = 8967397735404562097L;

  /**
   * Constructs a FinderException with no detail message.
   */
  public IVUFinderException() {
    super();
  }

  /**
   * Constructs an FinderException that embeds the originally thrown exception.
   * 
   * @param ex originally thrown exception
   */
  public IVUFinderException(Exception ex) {
    super();
    if (getCause() == null) {
      initCause(ex);
    }
  }

  /**
   * Constructs a FinderException with the specified detail message.
   * 
   * @param message detail message
   */
  public IVUFinderException(String message) {
    super(message);
  }

  /**
   * Constructs an FinderException that embeds the originally thrown exception with the specified
   * detail message.
   * 
   * @param message detail message
   * @param ex originally thrown exception
   */
  public IVUFinderException(String message, Exception ex) {
    super(message);
    if (getCause() == null) {
      initCause(ex);
    }
  }
}
