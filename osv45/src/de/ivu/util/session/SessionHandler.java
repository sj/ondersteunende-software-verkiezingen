/*
 *  --------------------------------------------------------------
 *  Company   : IVU Traffic Technologies AG
 *  ---------------------------------------------------------------
 */
package de.ivu.util.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.log4j.Logger;

/**
 * SessionStep implements the SessionState interface. Besides the public session step management
 * methods it contains methods for creation of unique step IDs, and for cleanup of older steps in
 * order to reduce consumed resources.
 * 
 * @author Dr. Domagoj Cosic (cos@ivu.de)
 * @created 23. Mai 2001
 */
public class SessionHandler implements SessionState, HttpSessionBindingListener {

  /**
   * Maximum number of Steps in a Session. Something between 10 and 100 depending on configuration.
   */
  protected final long _max_entries;

  /** Unique ID of the most recently created step. */
  protected long lastID = 0;

  /** Most recently created step. */
  protected Step lastStep = null;

  /**
   * Internal storage for the Steps: a balanced tree is a compromise. Search is logarithmic, meaning
   * slightliy worse than hash table with few elements. A balanced tree is ordered, and therefore
   * the cleanup is straightforward.
   */
  protected final TreeMap<String, StepImpl> stepsMap;

  /** Internal storage for data not stored in the steps (session constant data). */
  protected final HashMap<String, Object> internalState;

  /** When the SessionHandler is going to be bound to a session, the event will be stored here */
  protected HttpSessionBindingEvent bEvent = null;

  /**
   * Constructor for the SessionHandler object
   * 
   * @param max_entries Maximum number of Steps in a Session. Something between 10 and 100 depending
   *          on configuration.
   */
  public SessionHandler(long max_entries) {
    super();
    _max_entries = max_entries;
    stepsMap = new TreeMap<String, StepImpl>();
    internalState = new HashMap<String, Object>();
  }

  /**
   * Stores an attribute into this session state.
   * 
   * @param value the <code>Serializable</code> to be stored
   * @param key The new attribute value
   */
  public void setAttribute(String key, Serializable value) {
    if (bEvent != null && value instanceof HttpSessionBindingListener) {
      ((HttpSessionBindingListener) value).valueBound(bEvent);
    }

    internalState.put(key, value);
  }

  /**
   * Gets a previously created Step object by an Unique ID.
   * 
   * @param stepId Unique ID of a previously created Step as a String.
   * @return The Step object corresponding to the ID or <CODE>null </CODE> if the SessionHandler
   *         contains no Step with that ID.
   * @exception SessionStateException If stepId is <CODE>null</CODE>
   */
  public Step getStep(String stepId) throws SessionStateException {
    if (stepId == null) {
      throw new SessionStateException("Session Step must not be null"); //$NON-NLS-1$
    }
    return stepsMap.get(stepId);
  }

  /**
   * Gets the last known step .
   * 
   * @return The Step object corresponding to the ID or <CODE>null </CODE> if the SessionHandler
   *         contains no Step with that ID.
   * @exception SessionStateException If stepId is <CODE>null</CODE>
   */
  public Step getLastStep() throws SessionStateException {
    return stepsMap.get(lastStep.getId());
  }

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
  public Serializable getAttribute(String key) {
    return (Serializable) internalState.get(key);
  }

  /**
   * Creates a new Step object which is a blueprint of the most recently created Step in this
   * session.
   * 
   * @return The new step object.
   */
  public Step createNextStep() {
    if (lastStep != null) {
      try {
        return createNextStep(lastStep.getId());
      } catch (SessionStateException sse) {
        Logger.getLogger(SessionHandler.class)
            .fatal("Incorrect internal state of SessionHandler (not possible with current code) " //$NON-NLS-1$
                + sse);
      }
    }
    return createStep();
  }

  /**
   * Creates a new Step object which is a blueprint of the Step object corresponding to the given
   * unique ID. If the current step ID is <CODE>
    *  null </CODE> , an empty step is returned. If the step
   * identified by a non <CODE> null</CODE> ID cannot be found, a SessionStepException is thrown.
   * 
   * @param currentStepId the unique ID of the received current step.
   * @return the new step object.
   * @exception SessionStateException if the current step cannot be found.
   */
  public synchronized Step createNextStep(String currentStepId) throws SessionStateException {

    if (currentStepId != null) {
      StepImpl oldStep = stepsMap.get(currentStepId);
      if (oldStep != null) {
        doCheck();
        long id = createID();
        StepImpl step = createStep(id, oldStep);
        if (bEvent != null) {
          step.valueBound(bEvent);
        }
        // stepsMap.put(Long.toString(id), step);
        stepsMap.put(step.getId(), step);
        lastStep = step;
        return step;
      } else {
        throw new SessionStateException(
            "Session Step was already discarded or did not exist at all"); //$NON-NLS-1$
      }
    } else {
      return createStep();
    }
  }

  /**
   * Creates a new empty Step object.
   * 
   * @return The new Step object.
   */
  public synchronized Step createStep() {
    doCheck();
    long id = createID();
    StepImpl step = createStep(id);
    if (bEvent != null) {
      step.valueBound(bEvent);
    }
    // stepsMap.put(Long.toString(id), step);
    stepsMap.put(step.getId(), step);
    lastStep = step;
    return step;
  }

  /**
   * Removes an attribute from this session state.
   * 
   * @param key Description of Parameter
   */
  public void removeAttribute(String key) {
    Object value = internalState.remove(key);
    if (bEvent != null && value instanceof HttpSessionBindingListener) {
      ((HttpSessionBindingListener) value).valueUnbound(bEvent);
    }
  }

  /**
   * Invalidates steps before the current step end effectively discards history. The user cannot
   * step back to an earlier view and go on because the relevant server side information for views
   * prior to the current one is not existing after this call.
   * 
   * @param stepId Description of Parameter
   * @exception SessionStateException If the current step ID could not be found
   */
  public synchronized void invalidateStepsBefore(String stepId) throws SessionStateException {

    if (!stepsMap.containsKey(stepId)) {
      throw new SessionStateException("Cannot invalidate steps before an invalid step"); //$NON-NLS-1$
    }

    Iterator<String> toRemove = stepsMap.headMap(stepId).keySet().iterator();
    while (toRemove.hasNext()) {
      toRemove.next();
      toRemove.remove();
    }
  }

  /**
   * Description of the Method
   * 
   * @param event Description of Parameter
   */
  public synchronized void valueBound(HttpSessionBindingEvent event) {
    Iterator<Object> internal = internalState.values().iterator();
    while (internal.hasNext()) {
      Object current = internal.next();
      if (current instanceof HttpSessionBindingListener) {
        ((HttpSessionBindingListener) current).valueBound(event);
      }
    }
    Iterator<StepImpl> steps = stepsMap.values().iterator();
    while (steps.hasNext()) {
      Object current = steps.next();
      if (current instanceof HttpSessionBindingListener) {
        ((HttpSessionBindingListener) current).valueBound(event);
      }
    }
    bEvent = event;
  }

  /**
   * Description of the Method
   * 
   * @param event Description of Parameter
   */
  public synchronized void valueUnbound(HttpSessionBindingEvent event) {
    Iterator<Object> internal = internalState.values().iterator();
    while (internal.hasNext()) {
      Object current = internal.next();
      if (current instanceof HttpSessionBindingListener) {
        // System.err.println( "Unbinding " + current + " from session " +
        // event.getSession().getId() );
        ((HttpSessionBindingListener) current).valueUnbound(event);
      }
    }
    Iterator<StepImpl> steps = stepsMap.values().iterator();
    while (steps.hasNext()) {
      Object current = steps.next();
      if (current instanceof HttpSessionBindingListener) {
        ((HttpSessionBindingListener) current).valueUnbound(event);
      }
    }
    bEvent = null;
  }

  /**
   * Factory method: instanciates the correct subclass of the StepImpl
   * 
   * @param id unique ID of the StepImpl object
   * @return Description of the Returned Value
   */
  protected StepImpl createStep(long id) {
    return new StepImpl(id);
  }

  /**
   * Factory method: instanciates the correct subclass of the StepImpl
   * 
   * @param id unique ID of the StepImpl object
   * @param oldStep predecessor step object of the current step
   * @return Description of the Returned Value
   */
  protected StepImpl createStep(long id, StepImpl oldStep) {
    return new StepImpl(id, oldStep);
  }

  /**
   * Checks if the number of stored Steps for this session is above a limit. If this is the case,
   * the least recently created step is removed.
   */
  protected void doCheck() {
    if (stepsMap.size() > _max_entries) {
      stepsMap.remove(stepsMap.firstKey());
    }
  }

  /**
   * Creates a new unique ID for a new Step. The Step IDs are locally unique in respect to the
   * Session object.
   * 
   * @return New unique ID.
   */
  private long createID() {
    long newID = System.currentTimeMillis();
    if (newID <= lastID) {
      newID = lastID + 1;
    }
    lastID = newID;
    return newID;
  }
}
