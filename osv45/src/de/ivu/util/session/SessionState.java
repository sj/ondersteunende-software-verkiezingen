/*
 *  --------------------------------------------------------------
 *  Company   : IVU Traffic Technologies AG
 *  ---------------------------------------------------------------
 */
package de.ivu.util.session;

import java.io.Serializable;

/**
 * SessionState represents the complex state of a (HTTP) session. That state is composed out of
 * session steps. Each session step stores data corresponding to a single browser view in the
 * browser history, regardless of the fact if it still really exists. Given such possibility, the
 * session has to retain any previous client state at the server side. Such an approach is resource
 * consuming, and therefore the number of steps within a single session has to be limited.
 * <P>
 * A SessionState object manages the steps. It can retrieve an earlier step created within the same
 * session, and create new steps, which can be empty, contain a copy of the most recent step, or a
 * copy of an arbitrary earlier step.
 * 
 * @author Dr. Domagoj Cosic (cos@ivu.de)
 * @created 25. Mai 2001
 */

public interface SessionState {
  /**
   * Default key for storing session state into a HTTP session.
   */
  public final static String DEFAULT_KEY = "de.ivu.util.session.SessionState"; //$NON-NLS-1$

  /**
   * Gets a previously created Step object by an Unique ID.
   * 
   * @param stepId Unique ID of a previously created Step as a String.
   * @return The Step object corresponding to the ID or <CODE>null </CODE> if the SessionState
   *         contains no Step with that ID.
   * @exception SessionStateException If stepId is <CODE>null</CODE>
   */
  public Step getStep(String stepId) throws SessionStateException;

  /**
   * Creates a new empty Step object.
   * 
   * @return The new Step object.
   */
  public Step createStep();

  /**
   * Creates a new Step object which is a blueprint of the most recently created Step in this
   * session.
   * 
   * @return The new step object.
   */
  public Step createNextStep();

  /**
   * Creates a new Step object which is a blueprint of the Step object corresponding to the given
   * unique ID. If the step identified by the ID cannot be found, the last Step in this session will
   * be used as prototype.
   * 
   * @param currentStepId Previous (until now current) step ID
   * @return The new step object.
   * @exception SessionStateException If the current step ID could not be found
   */
  public Step createNextStep(String currentStepId) throws SessionStateException;

  /**
   * Gets the last known step .
   * 
   * @return The Step object corresponding to the ID or <CODE>null </CODE> if the SessionHandler
   *         contains no Step with that ID.
   * @exception SessionStateException If stepId is <CODE>null</CODE>
   */
  public Step getLastStep() throws SessionStateException;

  /**
   * Stores an attribute into this session state.
   * 
   * @param value the <code>Serializable</code> to be stored
   * @param key The new attribute value
   */
  public void setAttribute(String key, Serializable value);

  /**
   * Returns the value of the named attribute as an <code>Serializable</code> , or <code>null</code>
   * if no attribute of the given name exists.
   * <p>
   * Attributes can also be set programatically using {@link SessionState#setAttribute}.
   * 
   * @param key Description of Parameter
   * @return a <code>Serializable</code> containing the value of the attribute, or <code>null</code>
   *         if the attribute does not exist
   */
  public Serializable getAttribute(String key);

  /**
   * Removes an attribute from this session state.
   * 
   * @param key Description of Parameter
   */
  public void removeAttribute(String key);

  /**
   * Invalidates steps before the current step end effectively discards history. The user cannot
   * step back to an earlier view and go on because the relevant server side information for views
   * prior to the current one is not existing after this call.
   * 
   * @param stepId Description of Parameter
   * @exception SessionStateException If the current step ID could not be found
   */
  public void invalidateStepsBefore(String stepId) throws SessionStateException;
}
