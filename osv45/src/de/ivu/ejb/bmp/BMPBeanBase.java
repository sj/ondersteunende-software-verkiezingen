/*
 * BMPBeanBase
 * 
 * Copyright (c) 2004-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.ejb.bmp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.NoSuchEntityException;
import javax.ejb.ObjectNotFoundException;
import javax.naming.NamingException;
import javax.transaction.RollbackException;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUBeanBase;
import de.ivu.ejb.IVUFinderException;
import de.ivu.ejb.fw.DBABase;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;

/**
 * Base class for an Entity Enterprise Java Bean with Bean Managed Persistence
 * 
 * @author cos@ivu.de - IVU Traffic Technologies AG
 */
public abstract class BMPBeanBase extends IVUBeanBase implements EntityBean {

  /** long */
  private static final long serialVersionUID = 8755777932335927827L;
  final static Category LOGGER = Log4J.configure(BMPBeanBase.class);
  static {
    LOGGER.info(Log4J.dumpVersion(BMPBeanBase.class, Log4J.extractVersion("$Revision: 1.7 $"))); //$NON-NLS-1$
  }

  private static final Factory<? extends DBABase> __dbaFactory = new Factory<DBABase>("DBA", //$NON-NLS-1$
      "DBAClass"); //$NON-NLS-1$
  private static final Factory<? extends Model> __modelFactory = new Factory<Model>("ModelImpl", //$NON-NLS-1$
      "ModelClass"); //$NON-NLS-1$

  private static final Object[] NO_PARAMS = new Object[]{};
  private static final Class<?>[] NO_PARAMS_DECL = new Class<?>[]{};
  private static final Class<?>[] STRING_PARAM_DECL = new Class<?>[]{String.class};

  private static final boolean USE_OBJECT_EXISTENCE_CACHE = true;

  private static class Factory<T> {
    private final Map<String, Class<T>> _classes = new HashMap<String, Class<T>>();
    private final String _ext;
    private final String _plainName;

    public Factory(String ext, String plainName) {
      _ext = ext;
      _plainName = plainName;
    }

    public Class<T> getClass(String className) throws EJBException {

      Class<T> ret = _classes.get(className);
      if (ret == null) {
        try {
          String baseName = className.substring(className.lastIndexOf("."), //$NON-NLS-1$
              className.lastIndexOf("Bean")); //$NON-NLS-1$
          String withExtName = className.substring(0, className.lastIndexOf(".ejb")) + ".impl" //$NON-NLS-1$ //$NON-NLS-2$
              + baseName + _ext;
          ret = forName(withExtName);
          _classes.put(className, ret);
          if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("" + _plainName + ": " + ret.getName()); //$NON-NLS-1$//$NON-NLS-2$
          }
        } catch (Exception e) {
          throw new EJBException(e);
        }
      }
      return ret;
    }

    /**
     * @param modelName
     * @return
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    private Class<T> forName(String modelName) throws ClassNotFoundException {
      return (Class<T>) Class.forName(modelName);
    }
  }

  private static final Map<Class<? extends BMPBeanBase>, Set<Object>> __class2ExistingSet = new HashMap<Class<? extends BMPBeanBase>, Set<Object>>();

  protected EntityContext _ctx;

  protected boolean _usesLazyLoading;

  protected boolean _pendingLoad;

  static final Map<Transaction, Collection<Object>> __tx2Deleted = new HashMap<Transaction, Collection<Object>>();

  /**
   * Bean supporting method by EJB standard.
   * 
   * @param context EJB Entity Context
   * @throws EJBException Not used here, but allows well derivation
   */
  public void setEntityContext(EntityContext context) throws EJBException {
    _ctx = context;
  }

  /**
   * Bean supporting method by EJB standard.
   * 
   * @throws EJBException Not used here, but allows well derivation
   */
  public void unsetEntityContext() throws EJBException {
    _ctx = null;
  }

  /**
   * Bean supporting method by EJB standard.
   */
  public void ejbPostCreate() {
    // no initial functionality, sub classes can implement some if needs
  }

  /**
   * Bean supporting method by EJB standard.
   * 
   * @throws EJBException
   */
  public void ejbRemove() throws EJBException {
    if (getDetailsInternal() != null || _pendingLoad) {
      removeModel(_ctx.getPrimaryKey());
      setDetails((Model) null);
    }
  }

  /**
   * Bean supporting method by EJB standard.
   * 
   * @throws EJBException
   */
  public void ejbStore() throws EJBException {
    if (isModified()) {
      storeModel(getDetailsInternal());
    }
  }

  /**
   * Bean supporting method by EJB standard.
   * 
   * @throws EJBException
   */
  public void ejbLoad() throws EJBException {
    if (_usesLazyLoading) {
      _pendingLoad = true;
    } else {
      load();
    }
    setConditionalReadOnly(true);
  }

  protected void condLoad() throws EJBException {
    if (_pendingLoad) {
      load();
      _pendingLoad = false;
    }
  }

  /**
   * 
   */
  private void load() throws EJBException {
    setDetails(loadModel(_ctx.getPrimaryKey()));
  }

  @Override
  public void ejbPassivate() throws EJBException {
    LOGGER.debug("Passivating " + getClass().getName()); //$NON-NLS-1$
    super.ejbPassivate();
    resetRelations();
    setDetails(null);
  }

  /**
   * Bean supporting method for storage optimization.
   * 
   * @return <code>true</code> if the inner state opposite to the persistence storage got changeable
   */
  public boolean isModified() {
    Model details = getDetailsInternal();
    return details != null && details.isModified();
  }

  /**
   * Bean supporting method for storage optimization.
   */
  public void setModified() {
    getDetailsInternal().setModified();
  }

  /**
   * Internal method to receive a model object to set the object state
   * 
   * @param details Model object with new state
   */
  protected abstract Model setDetails(Model model);

  /**
   * Internal method to provide the model object, which represents the internal object state
   * 
   * @return Model object with internal state
   */
  protected abstract Model getDetailsInternal();

  /**
   * Method to check the validity of the relationship after the setting of foreign keys
   */
  protected abstract void checkRelations();

  /**
   * Method to reset the temporary saved foreign relationships
   */
  protected void resetRelations() {
    // can be derived in the sub class, if it contains relations
  }

  protected void setConditionalReadOnly(@SuppressWarnings("unused") boolean ro) {
    // does nothing, overwritten by WriteOnce-Beans
  }

  @SuppressWarnings("unchecked")
  protected <T extends Model> T createModel(String pk) throws EJBException {
    try {
      return (T) createModelInternal(pk);
    } catch (EJBException e) {
      throw e;
    } catch (Exception e) {
      throw new EJBException(e);
    }
  }

  @SuppressWarnings("unchecked")
  private <T extends Model> T createModelInternal(String pk)
      throws EJBException, InvocationTargetException {
    try {
      Class modelClass = getModelClass();
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("CREATING model-object (" + modelClass.getName() + ") with ID " + pk); //$NON-NLS-1$ //$NON-NLS-2$
      }
      if (pk == null) {
        return (T) modelClass.getConstructor(NO_PARAMS_DECL).newInstance(NO_PARAMS);
      } else {
        return (T) modelClass.getConstructor(STRING_PARAM_DECL).newInstance(new Object[]{pk});
      }
    } catch (InvocationTargetException ite) {
      throw ite;
    } catch (EJBException ejbe) {
      throw ejbe;
    } catch (Error e) {
      throw e;
    } catch (Exception e) {
      throw new EJBException(e);
    }
  }

  protected void create(Model model) throws EJBException, CreateException {
    setDetails(model);
    insertModel(model);
    checkRelations();
    setConditionalReadOnly(false);
  }

  private void insertModel(Model model) throws EJBException, CreateException {
    Class<? extends Model> modelClass = getModelClass();
    try {
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("INSERTING new entity (" + modelClass.getName() + ")\n" + model); //$NON-NLS-1$ //$NON-NLS-2$
      }

      Method m = getDBAClass().getMethod("insert", new Class[]{modelClass}); //$NON-NLS-1$
      m.invoke(null, new Object[]{model});
      // saved, set state to non-modified..
      ((ModelImpl) model).resetModified();
    } catch (EJBException e) {
      throw e;
    } catch (InvocationTargetException e) {
      CreateException createException = new CreateException(modelClass.getName());
      createException.initCause(e.getTargetException());
      throw createException;
    } catch (Exception e) {
      throw new EJBException(e);
    }
  }

  protected void find(Object pkObj)
      throws EJBException, IVUFinderException, ObjectNotFoundException {
    if (USE_OBJECT_EXISTENCE_CACHE && getExistingSet().contains(pkObj)) {
      return; // Existence already was detected
    }

    try {
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("LOOKING FOR entity with ID " + pkObj); //$NON-NLS-1$
      }
      Method m = getDBAClass().getMethod("countByKey", new Class[]{pkObj.getClass()}); //$NON-NLS-1$
      Integer ret = (Integer) m.invoke(null, new Object[]{pkObj});
      String errmsg;
      switch (ret.intValue()) {
        case 0 :
          errmsg = getClass().getName() + " with the ID '" + pkObj + "' not found! "; //$NON-NLS-1$ //$NON-NLS-2$
          if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(errmsg);
          }
          throw new ObjectNotFoundException(errmsg);

        case 1 :
          if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("found"); //$NON-NLS-1$
          }
          if (USE_OBJECT_EXISTENCE_CACHE) {
            getExistingSet().add(pkObj);
          }
          break;

        default :
          errmsg = getClass().getName() + " with the ID '" + pkObj + "' not definite! "; //$NON-NLS-1$ //$NON-NLS-2$
          if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(errmsg);
          }
          throw new IVUFinderException(errmsg);
      }
    } catch (InvocationTargetException ite) {
      final Throwable targetException = ite.getTargetException();
      if (targetException instanceof EJBException) {
        throw (EJBException) targetException;
      } else if (targetException instanceof Error) {
        throw (Error) targetException;
      } else if (targetException instanceof Exception) {
        throw new EJBException((Exception) targetException);
      } else {
        throw throwable2EJBException(targetException);
      }
    } catch (EJBException ejbe) {
      throw ejbe;
    } catch (Error e) {
      throw e;
    } catch (IVUFinderException e) {
      throw e;
    } catch (ObjectNotFoundException e) {
      throw e;
    } catch (Exception e) {
      throw new EJBException(e);
    }
  }

  @SuppressWarnings("unchecked")
  protected <T extends Model> T loadModel(Object pkObj) throws EJBException {
    try {
      T modelObj = (T) createModelInternal(null);
      Class<? extends Model> modelClass = modelObj.getClass();
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("LOADING entity  (" + modelClass.getName() + ") with ID " + pkObj); //$NON-NLS-1$ //$NON-NLS-2$
      }
      Method m = getDBAClass()
          .getMethod("retrieveByKey", new Class[]{modelClass, pkObj.getClass()}); //$NON-NLS-1$
      Boolean ret = (Boolean) m.invoke(null, new Object[]{modelObj, pkObj});
      if (ret.booleanValue()) {
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug("LOADED:\n" + modelObj); //$NON-NLS-1$
        }
        return modelObj;
      } else {
        throw new NoSuchEntityException(Messages
            .bind(MessageKeys.Error_0_MitDerPK_1_KonnteNichtGefundenWerden, modelClass, pkObj));
      }
    } catch (InvocationTargetException ite) {
      Throwable targetException = ite.getTargetException();
      if (targetException instanceof EJBException) {
        throw (EJBException) targetException;
      } else if (targetException instanceof Error) {
        throw (Error) targetException;
      } else if (targetException instanceof Exception) {
        throw new EJBException((Exception) targetException);
      } else {
        throw throwable2EJBException(targetException);
      }
    } catch (EJBException ejbe) {
      throw ejbe;
    } catch (Error e) {
      throw e;
    } catch (Exception e) {
      throw new EJBException(e);
    }
  }

  protected void removeModel(Object pkObj) throws EJBException {
    Transaction transaction = getTransaction();
    if (transaction != null) {
      synchronized (__tx2Deleted) {
        Collection<Object> deleted = __tx2Deleted.get(transaction);
        if (deleted != null && deleted.contains(pkObj)) {
          return;
        }
      }
    }

    try {
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("REMOVING entity with ID " + pkObj); //$NON-NLS-1$
      }
      if (pkObj != null) {
        Method m = getDBAClass().getMethod("deleteByKey", new Class[]{pkObj.getClass()}); //$NON-NLS-1$
        m.invoke(null, new Object[]{pkObj});
        if (USE_OBJECT_EXISTENCE_CACHE) {
          getExistingSet().remove(pkObj);
        }
      } else {
        LOGGER.warn(Messages.bind(MessageKeys.Error_EntityBean_0_BereitsGeloescht, getClass()
            .getName()));
      }
    } catch (InvocationTargetException ite) {
      Throwable targetException = ite.getTargetException();
      if (targetException instanceof EJBException) {
        throw (EJBException) targetException;
      } else if (targetException instanceof Error) {
        throw (Error) targetException;
      } else if (targetException instanceof Exception) {
        throw new EJBException((Exception) targetException);
      } else {
        throw throwable2EJBException(targetException);
      }
    } catch (EJBException ejbe) {
      throw ejbe;
    } catch (Error e) {
      throw e;
    } catch (Exception e) {
      throw new EJBException(e);
    }
  }

  protected void resetModified(Model modelObj) {
    ((ModelImpl) modelObj).resetModified();
  }

  protected void storeModel(Model modelObj) throws EJBException {
    try {
      if (modelObj != null) {
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug("UPDATING entity (" + getClass().getName() + "): " + modelObj); //$NON-NLS-1$ //$NON-NLS-2$
        }
        Method m = getDBAClass().getMethod("updateByKey", new Class[]{getModelClass()}); //$NON-NLS-1$
        m.invoke(null, new Object[]{modelObj});
        // saved, set state to non-modified...
        ((ModelImpl) modelObj).resetModified();
      }
    } catch (InvocationTargetException ite) {
      Throwable targetException = ite.getTargetException();
      if (targetException instanceof EJBException) {
        throw (EJBException) targetException;
      } else if (targetException instanceof Error) {
        throw (Error) targetException;
      } else if (targetException instanceof Exception) {
        throw new EJBException((Exception) targetException);
      } else {
        throw throwable2EJBException(targetException);
      }
    } catch (EJBException ejbe) {
      LOGGER.error(ejbe, ejbe);
      throw ejbe;
    } catch (Error e) {
      LOGGER.error(e, e);
      throw e;
    } catch (Exception e) {
      LOGGER.error(e, e);
      throw new EJBException(e);
    }
  }

  private EJBException throwable2EJBException(Throwable targetException) {
    EJBException ejbException = new EJBException(targetException.toString());
    ejbException.initCause(targetException);
    return ejbException;
  }

  protected Transaction getTransaction() throws EJBException {
    try {
      return ((TransactionManager) getInitialContext().lookup("java:/TransactionManager")).getTransaction(); //$NON-NLS-1$
    } catch (SystemException e) {
      throw new EJBException(e);
    } catch (NamingException e) {
      throw new EJBException(e);
    }
  }

  protected <T> T findSingle(Collection<T> col) throws IVUFinderException, ObjectNotFoundException {
    int len = col.size();
    if (len == 0) {
      throw new ObjectNotFoundException(Messages.bind(MessageKeys.Error_0_WurdeNichtGefunden,
          getClass().getName()));
    }
    if (len > 1) {
      throw new IVUFinderException(Messages.bind(MessageKeys.Error_0_IstNichtEindeutigBestimmt,
          getClass().getName()));
    }
    return col.iterator().next();
  }

  protected <T> T findSingleEJB(Collection<T> col) throws EJBException {
    int len = col.size();
    if (len == 0) {
      return null;
    }
    if (len > 1) {
      throw new EJBException(Messages.getString(MessageKeys.Error_NichtEindeutigBestimmt));
    }
    return col.iterator().next();
  }

  protected Map<Object, EJBLocalObject> toMap(Collection<EJBLocalObject> entityCol) {
    Map<Object, EJBLocalObject> entityMap = new HashMap<Object, EJBLocalObject>();
    for (EJBLocalObject entity : entityCol) {
      entityMap.put(entity.getPrimaryKey(), entity);
    }
    return entityMap;
  }

  private Class<? extends DBABase> getDBAClass() throws EJBException {
    return __dbaFactory.getClass(getClass().getName());
  }

  private Class<? extends Model> getModelClass() throws EJBException {
    return __modelFactory.getClass(getClass().getName());
  }

  /**
   * Returns a String with a comma separated list of Strings in 'apostrophes' from the given String
   * collection �bergebenen String-Collection
   * 
   * @param itb
   * @return new String with comma separated list of Strings in 'apostrophes' from the given String
   */
  protected static String getIDCollectionAsString(Iterable<?> itb) {
    StringBuilder ret = new StringBuilder();
    for (Iterator<?> iter = itb.iterator(); iter.hasNext();) {
      ret.append('\'').append(iter.next()).append('\'').append(iter.hasNext() ? "," : ""); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return ret.toString();
  }

  /**
   * Returns a String with a comma separated list of string sin 'apostrophes' from the given array
   */
  protected static String getIDCollectionAsString(Object[] arr) {
    StringBuilder ret = new StringBuilder();
    for (int i = 0; i < arr.length; i++) {
      ret.append('\'').append(arr[i]).append('\'').append(i + 1 < arr.length ? "," : ""); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return ret.toString();
  }

  /**
   * Returns the set of primary keys for an entity, for which the existence already was checked
   */
  private Set<Object> getExistingSet() {
    Set<Object> existingSet;
    Class<? extends BMPBeanBase> clazz = getClass();
    synchronized (__class2ExistingSet) {
      existingSet = __class2ExistingSet.get(clazz);
      if (existingSet == null) {
        existingSet = new HashSet<Object>();
        __class2ExistingSet.put(clazz, existingSet);
      }
    }
    return existingSet;
  }

  protected void addDeleted(Collection<? extends Object> deleted) throws EJBException {
    Transaction transaction = getTransaction();
    if (transaction != null) {
      synchronized (__tx2Deleted) {
        Collection<Object> alreadyDeleted = __tx2Deleted.get(transaction);
        if (alreadyDeleted != null) {
          alreadyDeleted.addAll(deleted);
        } else {
          alreadyDeleted = new ArrayList<Object>(deleted);
          try {
            transaction.registerSynchronization(new DeleteSynchronization(transaction));
          } catch (RollbackException e) {
            throw new EJBException(e);
          } catch (SystemException e) {
            throw new EJBException(e);
          }
        }
        __tx2Deleted.put(transaction, alreadyDeleted);
      }
    }
  }

  private static class DeleteSynchronization implements Synchronization {
    private final Transaction _transaction;

    /**
     * @param transaction
     */
    public DeleteSynchronization(Transaction transaction) {
      _transaction = transaction;
    }

    public void beforeCompletion() {
      // no function
    }

    public void afterCompletion(int status) {
      LOGGER.info("Removing 'delete' entry for Transaction " + _transaction); //$NON-NLS-1$
      synchronized (__tx2Deleted) {
        __tx2Deleted.remove(_transaction);
      }
    }
  }
}