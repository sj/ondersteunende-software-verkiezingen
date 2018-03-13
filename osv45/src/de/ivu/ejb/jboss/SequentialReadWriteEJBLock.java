/*
 * SequentialReadWriteEJBLock
 * 
 * Created on 21.07.2005
 * Copyright (c) 2005 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.ejb.jboss;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transaction;

import org.jboss.ejb.BeanLock;
import org.jboss.logging.Logger;

/**
 * This lock allows multiple read locks concurrently. Once a writer has requested the lock, future
 * read-lock requests whose transactions do not already have the read lock will block until all
 * writers are done -- then all the waiting readers will concurrently go (depending on the reentrant
 * setting / methodLock). Of course, writers have to wait for all read-locks to release before
 * taking the write lock.
 */
public class SequentialReadWriteEJBLock extends SimpleReadWriteEJBLock {
  /** Logger instance */
  private static final Logger LOGGER = Logger.getLogger(BeanLock.class);

  /**
   * @author cos@ivu.de, IVU Traffic Technologies AG
   */
  private static class WaitInfo {
    private boolean _waiting;
    private final boolean _write;

    /**
     * @param waiting
     * @param write
     */
    public WaitInfo(boolean waiting, boolean write) {
      _waiting = waiting;
      _write = write;
    }

    /**
     * Gibt waiting zur�ck.
     * 
     * @return waiting.
     */
    public boolean isWaiting() {
      return _waiting;
    }

    /**
     * Setzt den neuen Wert f�r waiting auf waiting.
     * 
     * @param waiting neuer Wert f�r waiting
     */
    public void setWaiting(boolean waiting) {
      _waiting = waiting;
    }

    /**
     * Gibt write zur�ck.
     * 
     * @return write.
     */
    public boolean isWrite() {
      return _write;
    }

    @Override
    public String toString() {
      return "Waiting: " + _waiting + " Write: " + _write; //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  Map<Thread, WaitInfo> _queue = new LinkedHashMap<Thread, WaitInfo>();

  @Override
  protected void getReadLock(Transaction aTx) {
    try {
      boolean done = false;
      long lockEntry = currentTimeMillis();

      while (!done) {
        if (!isInMethod()) {
          if (aTx == null) {
            done = _writer == null;
          } else if (_readers.containsKey(aTx)) {
            if (trace)
              trace(aTx, "already a reader"); //$NON-NLS-1$
            done = true;
          } else if (_writer == null && (_writersWaiting.isEmpty() || readerBeforeNextWriter())) {
            // Sonderfall: es k�nnen zwar wartende Schreibthreads vorhanden sein, aber dieser Thread
            // ist mit dem Lesen an der Reihe

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

          waitAWhile(aTx, false);
        }
      }
    } finally {
      synchronized (_queue) {
        _queue.remove(currentThread());
      }
    }
  }

  private boolean readerBeforeNextWriter() {
    Thread currentThread = currentThread();
    synchronized (_queue) {
      if (_queue.containsKey(currentThread)) {
        for (Entry<Thread, WaitInfo> wartenummerEntry : _queue.entrySet()) {
          final WaitInfo wartenummer = wartenummerEntry.getValue();
          if (currentThread.equals(wartenummerEntry.getKey())) {
            LOGGER.debug("Reader before next writer"); //$NON-NLS-1$
            return true;
          }
          if (wartenummer.isWrite() && wartenummer.isWaiting()) {
            return false;
          }
        }
      } else {
        return false;
      }
    }
    return true;
  }

  @Override
  protected void getWriteLock(Transaction aTx) {
    try {
      super.getWriteLock(aTx);
    } finally {
      synchronized (_queue) {
        _queue.remove(currentThread());
      }
    }
  }

  @Override
  protected void waitRAWhile(Transaction aTx) {
    waitAWhile(aTx, false);
  }

  @Override
  protected void waitWAWhile(Transaction aTx) {
    waitAWhile(aTx, true);
  }

  private void waitAWhile(Transaction aTx, boolean write) {
    checkPromotion();

    Thread currentThread = currentThread();
    try {
      Object lock = write ? currentThread : _readers;
      synchronized (lock) {
        releaseSync();
        try {
          synchronized (_queue) {
            WaitInfo waitInfo = _queue.get(currentThread);
            if (waitInfo == null) {
              waitInfo = new WaitInfo(true, write);
              _queue.put(currentThread, waitInfo);
            } else {
              waitInfo.setWaiting(true);
            }
          }
          trace(aTx, "Wait in"); //$NON-NLS-1$
          lock.wait(txTimeout);
          trace(aTx, "Wait out"); //$NON-NLS-1$
        } catch (InterruptedException e) {
          LOGGER.debug("Thread was interrupted while waiting, going on"); //$NON-NLS-1$
        }
        checkTransaction(aTx, write);
      }
    } finally {
      sync();
      synchronized (_queue) {
        _queue.get(currentThread).setWaiting(false);
      }
    }
  }

  @Override
  protected void notifyRWaiters(Transaction aTx) {
    notifyFirstWaiters(aTx);
  }

  @Override
  protected void notifyWWaiters(Transaction aTx) {
    // nothing
  }

  /**
   * If the first waiting Thread is a writer, notify it, else notify all waiting readers
   */
  private void notifyFirstWaiters(Transaction aTx) {
    Object lock = null;
    synchronized (_queue) {
      if (!isInMethod() && _writer == null) {
        boolean writePossible = _readers.isEmpty();
        for (Entry<Thread, WaitInfo> entry : _queue.entrySet()) {
          WaitInfo waitInfo = entry.getValue();
          if (waitInfo.isWaiting()) { // only if the Thread is currently really waiting
            if (waitInfo.isWrite()) {
              // when a waiting writer is before a reader, no reader will be notified
              if (writePossible) {
                // notify the first waiting writer
                lock = entry.getKey();
              }
            } else {
              // notify all waiting readers
              lock = _readers;
            }
            break;
          }
        }
      }
    }

    if (lock != null) {
      final String message = "Notifying " + ((lock == _readers) ? "all readers" : "waiting writer"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      if (synched != null) {
        releaseSync();
        synchronized (lock) {
          trace(aTx, message);
          lock.notifyAll();
        }
        sync();
      } else {
        synchronized (lock) {
          trace(aTx, message);
          lock.notifyAll();
        }
      }
    }
  }

  @Override
  public int getRefs() {
    return _readers.size() + (_writer == null ? 0 : 1) + _queue.size();
  }
}