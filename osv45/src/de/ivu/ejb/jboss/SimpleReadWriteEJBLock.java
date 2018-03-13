/*
 * SimpleReadWriteEJBLock
 * 
 * Created on 13.10.2004
 * Copyright (c) 2004 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.ejb.jboss;

import static java.lang.Integer.toHexString;
import static java.lang.Math.min;
import static java.lang.System.arraycopy;
import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;
import static javax.transaction.Status.STATUS_ACTIVE;
import static javax.transaction.Status.STATUS_COMMITTED;
import static javax.transaction.Status.STATUS_MARKED_ROLLBACK;
import static javax.transaction.Status.STATUS_NO_TRANSACTION;
import static javax.transaction.Status.STATUS_ROLLEDBACK;
import static javax.transaction.Status.STATUS_ROLLING_BACK;
import static javax.transaction.Status.STATUS_UNKNOWN;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.ejb.EJBException;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.jboss.ejb.BeanLock;
import org.jboss.ejb.plugins.lock.BeanLockSupport;
import org.jboss.invocation.Invocation;
import org.jboss.logging.Logger;

import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionImple;

/**
 * This lock allows multiple read locks concurrently. Once a writer has requested the lock, future
 * read-lock requests whose transactions do not already have the read lock will block until all
 * writers are done -- then all the waiting readers will concurrently go (depending on the reentrant
 * setting / methodLock). A reader who promotes gets first crack at the write lock -- ahead of other
 * waiting writers. If there is already a reader that is promoting, we throw an inconsistent read
 * exception. Of course, writers have to wait for all read-locks to release before taking the write
 * lock.
 * 
 * @author <a href="pete@subx.com">Peter Murray </a>
 */
public class SimpleReadWriteEJBLock extends BeanLockSupport {
  static final boolean DEBUG = false;

  /**
   * @author cos@ivu.de, IVU Traffic Technologies AG
   */
  protected static class CompetitorRecord {
    private final Transaction _transaction;
    private final Thread _thread;
    private StackTraceElement[] _initialStackTraceElements;

    private StackTraceElement[] _previousStackTraceElements;

    private int _queries;

    /**
     * @param transaction
     * @param thread
     */
    public CompetitorRecord(Transaction transaction, Thread thread) {
      _transaction = transaction;
      _thread = thread;
      if (DEBUG) {
        StackTraceElement[] tmp = _thread.getStackTrace();
        final int topOffset = 3;
        int len = tmp.length - topOffset;
        _initialStackTraceElements = new StackTraceElement[len];
        arraycopy(tmp, topOffset, _initialStackTraceElements, 0, len);
      }
    }

    /**
     * Gibt thread zur�ck.
     * 
     * @return thread.
     */
    public Thread getThread() {
      return _thread;
    }

    /**
     * Gibt transaction zur�ck.
     * 
     * @return transaction.
     */
    public Transaction getTransaction() {
      return _transaction;
    }

    /**
     * Gibt stackTraceElements zur�ck.
     * 
     * @return stackTraceElements.
     */
    public StackTraceElement[] getInitialStackTraceElements() {
      return _initialStackTraceElements;
    }

    public int incQuery() {
      return _queries++;
    }

    public StackTraceElement[] getPreviousStackTraceElements() {
      return _previousStackTraceElements;
    }

    public void setPreviousStackTraceElements(StackTraceElement[] previousStackTraceElements) {
      _previousStackTraceElements = previousStackTraceElements;
    }
  }

  /** Logger instance */
  private static final Logger LOGGER = Logger.getLogger(BeanLock.class);
  protected CompetitorRecord _writer = null;

  /** Writer is maculated (suspect); interesting only for debugging output */
  protected boolean _writerMaculated;

  protected final Map<Transaction, CompetitorRecord> _readers = new HashMap<Transaction, CompetitorRecord>();
  protected final Map<Transaction, CompetitorRecord> _writersWaiting = new HashMap<Transaction, CompetitorRecord>();
  protected boolean trace = LOGGER.isTraceEnabled();

  private boolean _inMethod = false;

  protected void trace(Transaction aTx, String message) {
    trace(aTx, message, null);
  }

  protected void trace(Transaction aTx, String message, Method method) {
    String baseMsg = getBaseMsg(aTx, message);
    if (method != null) {
      LOGGER.trace(baseMsg + " - " + method.getDeclaringClass().getName() + "." + method.getName()); //$NON-NLS-1$ //$NON-NLS-2$
    } else {
      LOGGER.trace(baseMsg);
    }
  }

  protected void debug(Transaction aTx, String message) {
    debug(aTx, message, null);
  }

  protected void debug(Transaction aTx, String message, Method method) {
    if (LOGGER.isDebugEnabled()) {
      String baseMsg = getBaseMsg(aTx, message);
      if (method != null) {
        LOGGER.debug(baseMsg + " - " + method.getDeclaringClass().getName() + "." //$NON-NLS-1$ //$NON-NLS-2$
            + method.getName());
      } else {
        LOGGER.debug(baseMsg);
      }
    }
  }

  /**
   * @param aTx transaction causing the action
   * @param message message part of the output
   * @return base of all log outputs
   */
  private String getBaseMsg(Transaction aTx, String message) {
    String txString = getTxString(aTx);
    return "LOCK(" + id + "-" + container.getBeanClass().getSimpleName() + ")" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + getClass().getSimpleName() + "@" + toHexString(hashCode()).toUpperCase() + ":" + message //$NON-NLS-1$ //$NON-NLS-2$
        + " TX: " + txString; //$NON-NLS-1$
  }

  /**
   * @param aTx transaction causing the action
   * @return concise description of the transaction
   */
  protected String getTxString(Transaction aTx) {
    String txString;
    if (aTx != null && aTx instanceof TransactionImple) {
      txString = ((TransactionImple) aTx).get_uid().toString();
      int lastColon = txString.lastIndexOf(':');
      if (lastColon >= 0 && lastColon + 1 < txString.length()) {
        txString = txString.substring(lastColon + 1).toUpperCase();
      }
    } else {
      txString = String.valueOf(aTx);
    }
    return txString;
  }

  protected String getTxStrings(Collection<Transaction> txs) {
    StringBuilder sb = new StringBuilder("("); //$NON-NLS-1$
    for (Transaction transaction : txs) {
      sb.append(getTxString(transaction));
      sb.append(',');
    }
    int length = sb.length();
    if (length > 1) {
      sb.setLength(length - 1);
    }
    sb.append(')');
    return sb.toString();
  }

  @Override
  public int getRefs() {
    return _readers.size() + _writersWaiting.size() + (_writer == null ? 0 : 1);
  }

  @Override
  public void schedule(Invocation mi) {
    boolean reading = container.getBeanMetaData().isMethodReadOnly(mi.getMethod().getName());
    Transaction miTx = mi.getTransaction();

    sync();
    try {
      if (reading) {
        if (trace)
          trace(miTx, "READ  (RQ)", mi.getMethod()); //$NON-NLS-1$
        getReadLock(miTx);
        if (trace)
          trace(miTx, "READ  (GT)", mi.getMethod()); //$NON-NLS-1$
      } else {
        if (trace)
          trace(miTx, "WRITE (RQ)", mi.getMethod()); //$NON-NLS-1$
        getWriteLock(miTx);
        if (trace)
          trace(miTx, "WRITE (GT)", mi.getMethod()); //$NON-NLS-1$
      }
      _inMethod = true;
    } finally {
      releaseSync();
    }
  }

  protected void getReadLock(Transaction aTx) {
    boolean done = false;
    long lockEntry = currentTimeMillis();

    while (!done) {
      if (!isInMethod()) {
        if (aTx == null) {
          done = _writer == null;
        } else if (_readers.containsKey(aTx)) {
          if (trace) {
            trace(aTx, "already a reader"); //$NON-NLS-1$
          }
          done = true;
        } else if (_writer == null && _writersWaiting.isEmpty()) {
          registerReadLock(aTx);
          done = true;
        } else if (_writer != null && _writer.getTransaction().equals(aTx)) {
          if (trace) {
            trace(aTx, "reader because already the writer"); //$NON-NLS-1$
          }
          done = true;
        }
      }

      if (!done && !checkBogusWriter(aTx)) {
        if (LOGGER.isDebugEnabled()) {
          if (currentTimeMillis() - lockEntry >= txTimeout) {
            debug(aTx, "READ (WT)" + getWriterReaderInfo() + testQueries()); //$NON-NLS-1$
            _writerMaculated = _writer != null;
          } else {
            trace(aTx, "READ (WT)" + getWriterReaderInfo()); //$NON-NLS-1$
          }
        }

        waitRAWhile(aTx);
      }
    }
  }

  protected void registerReadLock(Transaction aTx) {
    try {
      // zuerst pr�fen, ob die Transaktion noch in Ordnung ist:
      // wenn nicht, Zugriff erlauben, die Operation wird weiter schietern, sonst w�re kein
      // Verlassen des Locks m�glich
      if (aTx.getStatus() == STATUS_ACTIVE) {
        try {
          ReadLockReliever reliever = getReliever();
          reliever.setup(this, aTx);
          aTx.registerSynchronization(reliever);
        } catch (Exception e) {
          throw new EJBException(toString() + " TX: " + getTxString(aTx), e); //$NON-NLS-1$
        }
        _readers.put(aTx, new CompetitorRecord(aTx, currentThread()));
        if (trace) {
          trace(aTx, "became reader now"); //$NON-NLS-1$
        }
      } else {
        debug(aTx, "transaction not active"); //$NON-NLS-1$
      }
    } catch (SystemException e) {
      throw new EJBException(toString() + " TX: " + getTxString(aTx), e); //$NON-NLS-1$
    }
  }

  protected void getWriteLock(Transaction aTx) {
    boolean done = false;
    long lockEntry = currentTimeMillis();
    boolean isReader;

    if (aTx == null)
      throw new EJBException("Write lock requested without transaction."); //$NON-NLS-1$

    isReader = _readers.containsKey(aTx);
    CompetitorRecord cr = new CompetitorRecord(aTx, Thread.currentThread());
    _writersWaiting.put(aTx, cr);
    try {
      while (!done) {
        if (!isInMethod()) {
          if (_writer == null && (_readers.isEmpty() || (isReader && _readers.size() == 1))) {
            try {
              if (aTx.getStatus() == STATUS_ACTIVE) {
                _writer = cr;
                if (trace && _readers.isEmpty()) {
                  trace(aTx, "got write lock while no readers"); //$NON-NLS-1$
                }
                if (trace && _readers.size() == 1 && isReader) {
                  trace(aTx, "got write lock being already a reader"); //$NON-NLS-1$
                }
              }
            } catch (SystemException e) {
              throw new EJBException(e);
            }
            done = true;
          } else if (_writer != null && _writer.getTransaction().equals(aTx)) {
            if (trace)
              trace(aTx, "already the writer"); //$NON-NLS-1$
            done = true;
          }
        }

        if (!done && !checkBogusWriter(aTx)) {
          if (LOGGER.isDebugEnabled()) {
            if (currentTimeMillis() - lockEntry >= txTimeout) {
              debug(aTx, "WRITE (WT)" + getWriterReaderInfo() + testQueries()); //$NON-NLS-1$
              _writerMaculated = _writer != null;
            } else {
              trace(aTx, "WRITE (WT)" + getWriterReaderInfo()); //$NON-NLS-1$
            }
          }

          waitWAWhile(aTx);
        }
      }
    } finally {
      _writersWaiting.remove(aTx);
    }
  }

  /**
   * Pr�fe die haltende Transaktion; manchmal ist sie schon beendet, aber nicht ausgetragen
   */
  protected boolean checkBogusWriter(Transaction aTx) {
    if (_writer != null) {
      try {
        Transaction writerTransaction = _writer.getTransaction();
        int status = writerTransaction.getStatus();
        switch (status) {
          case STATUS_NO_TRANSACTION :
          case STATUS_COMMITTED :
          case STATUS_ROLLEDBACK :
            if (trace) {
              trace(writerTransaction, "Bogus writer, unregistering"); //$NON-NLS-1$
            }
            _writer = null;
            _writerMaculated = false;
            notifyRWaiters(aTx);
            notifyWWaiters(aTx);
            return true;
        }
      } catch (Exception e) {
        LOGGER.error(toString(), e);
      }
    }
    return false;
  }

  /**
   * Use readers as a semaphore object to avoid creating another object
   */
  protected void waitRAWhile(Transaction aTx) {
    waitAWhile(aTx, _readers, false);
  }

  /**
   * Use readers as a semaphore object to avoid creating another object
   */
  protected void waitWAWhile(Transaction aTx) {
    waitAWhile(aTx, _writersWaiting, true);
  }

  private void waitAWhile(Transaction aTx,
      Map<Transaction, CompetitorRecord> txMap,
      boolean isWriter) {

    checkPromotion();

    releaseSync();
    try {
      synchronized (txMap) {
        try {
          txMap.wait(txTimeout);
        } catch (InterruptedException e) {
          LOGGER.debug("Thread was interrupted while waiting, going on"); //$NON-NLS-1$
        }
        checkTransaction(aTx, isWriter);
      }
    } finally {
      sync();
    }
  }

  protected void checkPromotion() {
    // Problemanalyse
    Set<Transaction> tmp = new HashSet<Transaction>(_readers.keySet());
    tmp.retainAll(_writersWaiting.keySet());
    int size = tmp.size();
    if (size >= 2) {
      LOGGER.warn("More than one transaction requests an upgrade from read lock to write lock: " //$NON-NLS-1$
          + size);
      for (Transaction transaction : tmp) {
        LOGGER.warn(getTxString(transaction));
      }
    }
  }

  /**
   * Use readers as a semaphore object to avoid creating another object
   */
  protected void notifyRWaiters(Transaction aTx) {
    synchronized (_readers) {
      if (!_inMethod && _writer == null) {
        if (synched != null) {
          releaseSync();
          _readers.notifyAll();
          sync();
        } else {
          _readers.notifyAll();
        }
      }
    }
  }

  /**
   * Use writers waiting as a semaphore object to avoid creating another object
   */
  protected void notifyWWaiters(Transaction aTx) {
    synchronized (_writersWaiting) {
      if (!_inMethod && _writer == null && _readers.isEmpty() && !_writersWaiting.isEmpty()) {
        if (synched != null) {
          releaseSync();
          _writersWaiting.notify();
          sync();
        } else {
          _writersWaiting.notify();
        }
      }
    }
  }

  void releaseReadLock(Transaction transaction) {
    if (trace)
      trace(transaction, "READ  (UL)"); //$NON-NLS-1$

    if (_readers.remove(transaction) == null) {
      throw new IllegalStateException("ReadWriteEJBLock: Read lock released when it wasn't taken"); //$NON-NLS-1$
    }

    notifyRWaiters(transaction);
    notifyWWaiters(transaction);
  }

  void releaseWriteLock(Transaction transaction) {
    if (trace)
      trace(transaction, "WRITE (UL)"); //$NON-NLS-1$

    _writerMaculated = _writerMaculated && _writer != null;

    try {
      if (synched == null) {
        throw new IllegalStateException(
            "ReadWriteEJBLock: Do not call releaseWriteLock while not synched!"); //$NON-NLS-1$
      }

      if (_writer != null && !_writer.getTransaction().equals(transaction)) {
        throw new IllegalStateException(
            "ReadWriteEJBLock: can't unlock a write lock with a different transaction"); //$NON-NLS-1$
      }

      _writer = null;

      notifyRWaiters(transaction);
      notifyWWaiters(transaction);
    } finally {
      if (_writerMaculated) {
        _writerMaculated = false;
        try {
          debug(transaction, "WRITE (UL) releaseWriteLock, TX status " + transaction.getStatus()); //$NON-NLS-1$
        } catch (SystemException e) {
          LOGGER.error(toString(), e);
        }
      }
    }
  }

  @Override
  public void endTransaction(Transaction transaction) {
    releaseWriteLock(transaction);
  }

  @Override
  public void wontSynchronize(Transaction transaction) {
    releaseWriteLock(transaction);
  }

  @Override
  public void endInvocation(Invocation mi) {
    _inMethod = false;
    if (_writer == null) {
      final Transaction aTx = mi.getTransaction();
      notifyRWaiters(aTx);
      notifyWWaiters(aTx);
    }
  }

  static final Stack<ReadLockReliever> kRecycledRelievers = new Stack<ReadLockReliever>();

  static synchronized ReadLockReliever getReliever() {
    return kRecycledRelievers.empty() ? new ReadLockReliever() : kRecycledRelievers.pop();
  }

  /**
   * @author cos@ivu.de, IVU Traffic Technologies AG
   */
  protected static class ReadLockReliever implements Synchronization {
    SimpleReadWriteEJBLock lock;
    Transaction transaction;

    @Override
    protected void finalize() {
      recycle();
    }

    protected void recycle() {
      lock = null;
      transaction = null;
      kRecycledRelievers.push(this);
    }

    void setup(SimpleReadWriteEJBLock aLock, Transaction aTransaction) {
      this.lock = aLock;
      this.transaction = aTransaction;
    }

    public void beforeCompletion() {
      // nothing to do
    }

    public void afterCompletion(int status) {
      lock.sync();
      try {
        lock.releaseReadLock(transaction);
      } finally {
        lock.releaseSync();
      }
      recycle();
    }
  }

  protected void checkTransaction(Transaction aTx, boolean isWriter) {
    try {
      if (aTx != null) {
        final int txStatus = aTx.getStatus();
        if (txStatus != STATUS_ACTIVE) {
          debug(aTx, "transaction not active"); //$NON-NLS-1$
        }

        switch (txStatus) {
          case STATUS_MARKED_ROLLBACK :
          case STATUS_ROLLEDBACK :
          case STATUS_ROLLING_BACK :
          case STATUS_NO_TRANSACTION :
          case STATUS_UNKNOWN :
            StringBuilder stackTraces = new StringBuilder();
            if (_writer != null) {
              // writer blocks the lock for everyone
              addStackTrace(stackTraces, "Writer", _writer); //$NON-NLS-1$

            } else if (isWriter) {
              // readers also block the lock for a writer
              for (CompetitorRecord cr : _readers.values()) {
                addStackTrace(stackTraces, "Reader", cr); //$NON-NLS-1$
              }
            }
            throw new EJBException("Transaction " + getTxString(aTx) //$NON-NLS-1$
                + " not active - probably a timeout.\n" + toString() + stackTraces); //$NON-NLS-1$
        }
      }
    } catch (EJBException e) {
      throw e;
    } catch (Exception e) {
      throw new EJBException(toString() + " TX: " + getTxString(aTx), e); //$NON-NLS-1$
    }
  }

  private void addStackTrace(StringBuilder stackTraces, String type, CompetitorRecord cr) {
    stackTraces.append('\n');
    stackTraces.append('\n');
    stackTraces.append(type);
    stackTraces.append(" ("); //$NON-NLS-1$
    stackTraces.append(getTxString(cr.getTransaction()));
    stackTraces.append(',');
    stackTraces.append(cr.getThread().getName());
    stackTraces.append(") Stack:\n"); //$NON-NLS-1$
    StackTraceElement[] stackTrace = cr.getThread().getStackTrace();
    StackTraceElement[] previousStackTraceElements = cr.getPreviousStackTraceElements();
    cr.setPreviousStackTraceElements(stackTrace.clone());

    boolean abreviated = false;
    if (previousStackTraceElements != null) {
      int minLen = min(stackTrace.length, previousStackTraceElements.length);
      int offset1 = stackTrace.length - minLen;
      int offset2 = previousStackTraceElements.length - minLen;
      int firstDifference = -1; // ung�ltig, vor dem Anfang

      for (int i = minLen - 1; i >= 0; i--) {
        if (!stackTrace[i + offset1].toString().equals(previousStackTraceElements[i + offset2]
            .toString())) {

          firstDifference = i;
          break;
        }

        abreviated = true;
      }

      if (abreviated) {
        // show last common stack frame
        int lastCommon = min(firstDifference + 1, minLen - 1);
        final int abreviatedLen = lastCommon + offset1 + 1;
        StackTraceElement[] abreviatedStackTrace = new StackTraceElement[abreviatedLen];
        arraycopy(stackTrace, 0, abreviatedStackTrace, 0, abreviatedLen);
        stackTrace = abreviatedStackTrace;
      }
    }

    addStackTrace(stackTraces, stackTrace);

    if (abreviated) {
      stackTraces.append("--- abreviated ---\n"); //$NON-NLS-1$
    }

    // look for inital stack traces
    stackTrace = cr.getInitialStackTraceElements();
    if (stackTrace != null) {
      stackTraces.append("\nInitially acquired lock at:\n"); //$NON-NLS-1$
      addStackTrace(stackTraces, stackTrace);
    }
  }

  private void addStackTrace(StringBuilder stackTraces, StackTraceElement[] stackTrace) {
    for (StackTraceElement traceElement : stackTrace) {
      stackTraces.append('\t').append(traceElement.toString()).append('\n');
    }
  }

  @Override
  public String toString() {
    return "Lock data: " + toHexString(hashCode()).toUpperCase() + getWriterReaderInfo() //$NON-NLS-1$
        + testQueries();
  }

  protected String getWriterReaderInfo() {
    return " I: " + isInMethod() + " W: " //$NON-NLS-1$ //$NON-NLS-2$
        + getTxString(_writer == null ? null : _writer.getTransaction()) + " WW: " //$NON-NLS-1$
        + getTxStrings(_writersWaiting.keySet()) + " R: " + getTxStrings(_readers.keySet()); //$NON-NLS-1$
  }

  protected String testQueries() {
    StringBuilder stackTraceSB = new StringBuilder();
    for (CompetitorRecord cr : _readers.values()) {
      if (cr.incQuery() > 50) {
        addStackTrace(stackTraceSB, "Reader", cr); //$NON-NLS-1$
      }
    }
    final CompetitorRecord writer = _writer;
    if (writer != null && writer.incQuery() > 50) {
      addStackTrace(stackTraceSB, "Writer", writer); //$NON-NLS-1$
    }
    return stackTraceSB.toString();
  }

  protected boolean isInMethod() {
    return _inMethod;
  }

  @Override
  public void sync() {
    synchronized (this) {
      Thread thread = currentThread();
      long waitStart = currentTimeMillis();
      while (synched != null && !thread.equals(synched)) {
        try {
          wait(60000);
        } catch (InterruptedException ex) {
          LOGGER.debug("Thread was interrupted while waiting, going on"); //$NON-NLS-1$
        }
        if (synched != null && currentTimeMillis() - waitStart >= 60000) {
          debug(null, "Sync holding thread: " + synched.getName() + " depth: " + synchedDepth); //$NON-NLS-1$ //$NON-NLS-2$

          // try to find what thread it is
          StringBuilder stackTraceSB = new StringBuilder();
          if (_writer != null && synched.equals(_writer.getThread())) {
            addStackTrace(stackTraceSB, "Writer", _writer); //$NON-NLS-1$
          } else if (_writersWaiting.containsKey(synched)) {
            addStackTrace(stackTraceSB, "Waiting Writer", _writersWaiting.get(synched)); //$NON-NLS-1$
          } else if (_readers.containsKey(synched)) {
            addStackTrace(stackTraceSB, "Reader", _readers.get(synched)); //$NON-NLS-1$
          } else {
            for (StackTraceElement stackTraceElement : synched.getStackTrace()) {
              debug(null, stackTraceElement.toString());
            }
          }
          if (stackTraceSB.length() > 0) {
            debug(null, stackTraceSB.toString());
          }
        }
      }
      synched = thread;
      ++synchedDepth;

      if (synchedDepth > 1) {
        debug(null, "Sync acquiring thread: " + synched.getName() + " depth: " + synchedDepth); //$NON-NLS-1$ //$NON-NLS-2$
        for (StackTraceElement stackTraceElement : synched.getStackTrace()) {
          debug(null, stackTraceElement.toString());
        }
      }
    }
  }
}