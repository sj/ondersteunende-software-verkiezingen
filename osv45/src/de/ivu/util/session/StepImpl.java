/*--------------------------------------------------------------
   Company   : IVU Traffic Technologies AG
 ---------------------------------------------------------------*/
package de.ivu.util.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * StepImpl implements a Session Step. A Session Step represents the server side state corresponding
 * to a particular browser view either in the browser history or in one of the browser windows
 * opened within the same session. Because there is a Session Step for each of the previous views
 * (not necessarily vice versa), the user can return to whatever he has seen in the past, as long as
 * it was within the same session. A Session Step is uniquely identified by an internal ID.
 * 
 * @author Dr. Domagoj Cosic (cos@ivu.de)
 * @created 23. Mai 2001
 */
public class StepImpl implements Step, HttpSessionBindingListener, Comparable<Step> {
  private static final long serialVersionUID = 4548516999171231008L;

  /**
   * Unique ID of the StepImpl
   */
  protected long _id;

  /**
   * Internal storage for the named parameters
   */
  protected Map<String, Object> _state = Collections.emptyMap();

  /**
   * Description of the Field
   */
  protected boolean _independent = false;

  /**
   * When the StepImpl is going to be bound to a session, the event will be stored here
   */
  protected HttpSessionBindingEvent _bEvent = null;

  /**
   * Constructor for the StepImpl object
   * 
   * @param id unique ID of the StepImpl object
   */
  protected StepImpl(long id) {
    super();
    _id = id;
  }

  /**
   * Constructor for the StepImpl object
   * 
   * @param id unique ID of the StepImpl object
   * @param oldStep predecessor step object of the current step
   */
  protected StepImpl(long id, StepImpl oldStep) {
    this(id);
    _state = oldStep._state;
  }

  /**
   * Gets the unique ID of the StepImpl object
   * 
   * @return The unique ID value
   */
  public String getId() {
    return Long.toString(_id);
  }

  /**
   * Returns <tt>true</tt> if this step contains no key-value mappings.
   * 
   * @return <tt>true</tt> if this step contains no key-value mappings.
   */
  public boolean isEmpty() {
    return _state.isEmpty();
  }

  /**
   * Returns the value to which this step maps the specified key. Returns <tt>
    *  null</tt> if the step
   * contains no mapping for this key. A return value of <tt>null</tt> indicates that the step
   * contains no mapping for the key.
   * 
   * @param key key whose associated value is to be returned.
   * @return the value to which this step maps the specified key, or <tt>null</tt> if the step
   *         contains no mapping for this key.
   * @throws ClassCastException if the key is of an inappropriate type for a step.
   * @throws NullPointerException key is <tt>null</tt> .
   * @see #containsKey(Object)
   */
  public Object get(Object key) throws ClassCastException, NullPointerException {
    checkKey(key);
    return _state.get(key);
  }

  /**
   * Gets the int attribute of the StepImpl object
   * 
   * @param key key whose associated value is to be returned.
   * @return The int value
   */
  public int getInt(String key) {
    Integer i = (Integer) get(key);
    if (i != null) {
      return i.intValue();
    } else {
      throw new RuntimeException("key" + key + " not found in step"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  /**
   * Gets a boolean from the step
   * 
   * @param key key whose associated value is to be returned.
   * @return The boolean value
   */
  public boolean getBoolean(String key) {
    Boolean b = (Boolean) get(key);
    if (b != null) {
      return b.booleanValue();
    } else {
      throw new RuntimeException("key " + key + " not found in step"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  /**
   * Gets a string from the step
   * 
   * @param key key whose associated value is to be returned.
   * @return The stored string
   */
  public String getString(String key) {
    return (String) get(key);
  }

  /**
   * Returns the number of key-value mappings in this step. If the step contains more than
   * <tt>Integer.MAX_VALUE</tt> elements, returns <tt>
    *  Integer.MAX_VALUE</tt> .
   * 
   * @return the number of key-value mappings in this step.
   */
  public int size() {
    return _state.size();
  }

  /**
   * Returns <tt>true</tt> if this step contains a mapping for the specified key.
   * 
   * @param key key whose presence in this step is to be tested.
   * @return <tt>true</tt> if this step contains a mapping for the specified key.
   * @throws ClassCastException if the key is of an inappropriate type for a step.
   * @throws NullPointerException if the key is <tt>null</tt> .
   */
  public boolean containsKey(Object key) throws ClassCastException, NullPointerException {

    checkKey(key);
    return _state.containsKey(key);
  }

  /**
   * Returns <tt>true</tt> if this step maps one or more keys to the specified value. More formally,
   * returns <tt>true</tt> if and only if this step contains at least one mapping to a value
   * <tt>v</tt> such that <tt>
    *  (value==null ? v==null : value.equals(v))</tt> . This operation requires
   * time linear in the step size.
   * 
   * @param value value whose presence in this step is to be tested.
   * @return <tt>true</tt> if this step maps one or more keys to the specified value.
   * @exception ClassCastException Description of Exception
   */
  public boolean containsValue(Object value) throws ClassCastException {
    return _state.containsValue(value);
  }

  /**
   * Associates the specified value with the specified key in this step (optional operation). If the
   * step previously contained a mapping for this key, the old value is replaced.
   * 
   * @param key key with which the specified value is to be associated.
   * @param value value to be associated with the specified key.
   * @return previous value associated with specified key, or <tt>null</tt> if there was no mapping
   *         for key. A <tt> null </tt> return can also indicate that the step previously associated
   *         <tt>null</tt> with the specified key.
   * @throws ClassCastException if the class of the specified key or value prevents it from being
   *           stored in this step.
   * @throws IllegalArgumentException if some aspect of this key or value prevents it from being
   *           stored in this step.
   * @throws NullPointerException this step does not permit <tt>null
    *      </tt> keys or values, and the
   *           specified key or value is <tt>null</tt> .
   */
  public Object put(String key, Object value)
      throws ClassCastException, IllegalArgumentException, NullPointerException {

    checkKey(key);
    checkValue(value);
    checkWritable();
    return _state.put(key, value);
  }

  /**
   * Removes the mapping for this key from this step if present.
   * 
   * @param key key whose mapping is to be removed from the step.
   * @return previous value associated with specified key, or <tt>null
    *      </tt> if there was no mapping for
   *         key. A <tt> null</tt> return can also indicate that the step previously associated
   *         <tt>null</tt> with the specified key.
   */
  public Object remove(Object key) {
    Object value = _state.remove(key);
    if (_bEvent != null && value instanceof HttpSessionBindingListener) {
      ((HttpSessionBindingListener) value).valueUnbound(_bEvent);
    }
    return value;
  }

  /**
   * Copies all of the mappings from the specified step to this step. These mappings will replace
   * any mappings that this step had for any of the keys currently in the specified step. You cannot
   * use a general Map as argument.
   * 
   * @param <S>
   * @param <O>
   * @param t Mappings to be stored in this step.
   * @throws ClassCastException if the class of a key or value in the specified map prevents it from
   *           being stored in this step.
   * @throws IllegalArgumentException some aspect of a key or value in the specified map prevents it
   *           from being stored in this step.
   * @throws NullPointerException this step does not permit <tt>null
    *      </tt> keys or values, and the
   *           specified key or value is <tt>null</tt>
   */
  @Override
  public void putAll(Map<? extends String, ? extends Object> t)
      throws ClassCastException, IllegalArgumentException, NullPointerException {

    Set<?> tSet = t.entrySet();
    for (Iterator<?> tSetItr = tSet.iterator(); tSetItr.hasNext();) {
      Map.Entry<?, ?> entry = (Map.Entry<?, ?>) tSetItr.next();
      put0((String) entry.getKey(), entry.getValue());
    }
  }

  /**
   * Removes all mappings from this step (optional operation).
   * 
   * @throws UnsupportedOperationException clear is not supported by a step.
   */
  public void clear() {
    throw new UnsupportedOperationException("You cannot remove all entries from a step"); //$NON-NLS-1$
  }

  /**
   * Returns a set view of the keys contained in this step. The set is backed by the step, so
   * changes to the step are reflected in the set, and vice-versa. If the step is modified while an
   * iteration over the set is in progress, the results of the iteration are undefined. The set does
   * not support element removal, via the <tt> Iterator.remove</tt> , <tt>
    *  Set.remove</tt> ,
   * <tt>removeAll</tt> <tt> retainAll</tt> , and <tt>clear
    *  </tt> operations. It also does not support the
   * add or <tt>addAll</tt> operations.
   * 
   * @return a set view of the keys contained in this step.
   */
  public Set<String> keySet() {
    return Collections.unmodifiableSet(_state.keySet());
  }

  /**
   * Returns a collection view of the values contained in this step. The collection is backed by the
   * step, so changes to the step are reflected in the collection, and vice-versa. If the step is
   * modified while an iteration over the collection is in progress, the results of the iteration
   * are undefined. The collection does not support element removal, via the
   * <tt>Iterator.remove</tt> , <tt> Collection.remove</tt> , <tt>
    *  removeAll</tt> , <tt>retainAll</tt>
   * and <tt> clear</tt> operations. It also does not support the add or <tt>addAll</tt> operations.
   * 
   * @return a collection view of the values contained in this step.
   */
  public Collection<Object> values() {
    return Collections.unmodifiableCollection(_state.values());
  }

  /**
   * Returns a set view of the mappings contained in this step. Each element in the returned set is
   * a <tt>Map.Entry</tt> . The set is backed by the step, so changes to the step are reflected in
   * the set, and vice-versa. If the step is modified while an iteration over the set is in
   * progress, the results of the iteration are undefined. The set does not support element removal,
   * via the <tt>Iterator.remove</tt> , <tt>Set.remove</tt> , <tt>
    *  removeAll</tt> , <tt>retainAll</tt>
   * and <tt>clear </tt> operations. It also does not support the <tt>add</tt> or <tt>addAll</tt>
   * operations.
   * 
   * @return a set view of the mappings contained in this step.
   */
  public Set<Entry<String, Object>> entrySet() {
    return Collections.unmodifiableSet(_state.entrySet());
  }

  /**
   * Compares the specified object with this step for equality. Returns <tt>
    *  true </tt> if the given
   * object is also a step with the same stepId and their corresponding Maps represent the same
   * mappings. More formally, two maps <tt>t1</tt> and <tt> t2</tt> represent the same mappings if
   * <tt>
    *  t1.entrySet().equals(t2.entrySet()) </tt> . This ensures that the <tt>
    *  equals</tt> method works
   * properly across different implementations of the <tt>Map</tt> interface.
   * 
   * @param o object to be compared for equality with this step.
   * @return <tt>true</tt> if the specified object is equal to this step.
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Step)) {
      return false;
    }
    if (!getId().equals(((Step) o).getId())) {
      return false;
    }
    if (!(o instanceof StepImpl)) {
      return ((Step) o).equals(this); // do not bother now, handle it there
    }
    return _state.equals(((StepImpl) o)._state);
  }

  /**
   * Returns the hash code value for this step. The hash code of a step is defined to be the sum of
   * the hashCodes of each entry in the step's entrySet view XORed with the stepId. This ensures
   * that <tt>t1.equals(t2)</tt> implies that <tt>t1.hashCode()==t2.hashCode()</tt> for any two maps
   * <tt>t1</tt> and <tt>t2</tt>, as required by the general contract of Object.hashCode.
   * 
   * @return the hash code value for this step.
   * @see java.util.Map.Entry#hashCode()
   * @see Object#hashCode()
   * @see Object#equals(Object)
   * @see #equals(Object)
   */
  @Override
  public int hashCode() {
    return _state.hashCode() ^ (int) _id ^ (int) (_id >>> 32);
  }

  /**
   * Description of the Method
   * 
   * @param key Description of Parameter
   * @param val Description of Parameter
   */
  public void putInt(String key, int val) {
    put(key, new Integer(val));
  }

  /**
   * Description of the Method
   * 
   * @param key Description of Parameter
   * @param val Description of Parameter
   */
  public void putBoolean(String key, boolean val) {
    put(key, Boolean.valueOf(val));
  }

  /**
   * Description of the Method
   * 
   * @param key Description of Parameter
   * @param val Description of Parameter
   */
  public void putString(String key, String val) {
    put(key, val);
  }

  /**
   * Description of the Method
   * 
   * @param event Description of Parameter
   */
  public void valueBound(HttpSessionBindingEvent event) {
    if (_independent) {
      Iterator<Object> it = _state.values().iterator();
      while (it.hasNext()) {
        Object current = it.next();
        if (current instanceof HttpSessionBindingListener) {
          ((HttpSessionBindingListener) current).valueBound(event);
        }
      }
    }
    _bEvent = event;
  }

  /**
   * Description of the Method
   * 
   * @param event Description of Parameter
   */
  public void valueUnbound(HttpSessionBindingEvent event) {
    if (_independent) {
      Iterator<Object> it = _state.values().iterator();
      while (it.hasNext()) {
        Object current = it.next();
        if (current instanceof HttpSessionBindingListener) {
          ((HttpSessionBindingListener) current).valueUnbound(event);
        }
      }
    }
    _bEvent = null;
  }

  /**
   * Description of the Method
   * 
   * @param o Description of Parameter
   * @return Description of the Returned Value
   * @throws ClassCastException if compared objects belong to incompatible types
   */
  public int compareTo(Step o) {
    return getId().compareTo(o.getId());
  }

  /**
   * Description of the Method
   */
  protected void checkWritable() {
    if (!_independent) {
      Map<String, Object> oldState = _state;
      _state = new HashMap<String, Object>(10);
      _state.putAll(oldState);
      _independent = true;
    }
  }

  /**
   * Description of the Method
   * 
   * @param key Description of Parameter
   * @param value Description of Parameter
   * @return Description of the Returned Value
   * @exception ClassCastException Description of Exception
   * @exception IllegalArgumentException Description of Exception
   * @exception NullPointerException Description of Exception
   */
  private Object put0(String key, Object value)
      throws ClassCastException, IllegalArgumentException, NullPointerException {

    checkKey(key);
    checkValue(value);
    if (value.equals(_state.get(key))) {
      return value;
    } else {
      checkWritable();
      if (_bEvent != null && value instanceof HttpSessionBindingListener) {
        ((HttpSessionBindingListener) value).valueBound(_bEvent);
      }
      return _state.put(key, value);
    }
  }

  /**
   * Checks if the key has an allowed value, which is a String.
   * 
   * @param key Key to be ckecked for constraints.
   * @exception ClassCastException Thrown if the key is not a String.
   * @exception NullPointerException Thrown if the key is null.
   */
  private void checkKey(Object key) throws ClassCastException, NullPointerException {

    if (key == null) {
      throw new NullPointerException("null is not allowed as key in a Step"); //$NON-NLS-1$
    }
    if (!(key instanceof String)) {
      throw new ClassCastException("Only a String is allowed as key in a Step"); //$NON-NLS-1$
    }
  }

  /**
   * Checks if the value is an allowed value, it has to be a Serializable.
   * 
   * @param value Value to be checked for constraints.
   * @exception IllegalArgumentException Thrown if the value is not Serializable.
   */
  private void checkValue(Object value) throws IllegalArgumentException {

    if (value != null && !(value instanceof Serializable)) {
      throw new IllegalArgumentException("Only a Serializable is allowed as value in a Step"); //$NON-NLS-1$
    }
  }

  @Override
  public String toString() {
    return _id + (_state.isEmpty() ? "" : (" : " + _state)); //$NON-NLS-1$ //$NON-NLS-2$
  }
}
