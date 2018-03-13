package de.ivu.util.tools.tablegen;

import java.util.ArrayList;
import java.util.List;

/**
 * A Container for a Foreign Key definition for a table
 * 
 * @author <a href="mailto:cos@ivu.de">Dr. Domagoj Cosic </a>
 */
public class FKDefinition {
  protected List<String> _fkFields;
  protected String _fkName;
  protected String _fkTable;
  protected List<String> _pkFields;
  protected String _pkTtable;

  /**
   * Constructor
   * 
   * @param pPKTable Name of the table, which has the primary key as target of the reference
   * @param pFKTable Name of the table, which has the foreign key as source of the reference
   * @param pFKName Name of the foreign key
   */
  public FKDefinition(String pPKTable, String pFKTable, String pFKName) {
    _fkTable = pFKTable;
    _pkTtable = pPKTable;
    _fkName = pFKName != null ? pFKName : (_fkTable + _pkTtable);
    _fkFields = new ArrayList<String>();
    _pkFields = new ArrayList<String>();
    // System.out.println ("Created FK: " + this);
  }

  /**
   * Add the corresponding Foreign Key Name and Primary Key name
   * 
   * @param fkFieldName Name of the foreign key column
   * @param pkFieldName Name of the primary key column
   */
  public void addField(String fkFieldName, String pkFieldName) {
    _fkFields.add(fkFieldName);
    _pkFields.add(pkFieldName);
    // System.out.println ("added field: FK " + FKFieldName + ", PK " + PKFieldName);
  }

  /**
   * Returns a comma separated list of the columns, which among to the foreign key
   */
  public String getFKColList() {
    String sRet = null;

    for (String fkField : _fkFields) {
      if (sRet != null) {
        sRet += ", " + fkField; //$NON-NLS-1$
      } else {
        sRet = fkField;
      }
    }
    return sRet;
  }

  /**
   * Returns a list of the column names, which among to the foreign key, as a list of String
   */
  public List<String> getFKFields() {
    return _fkFields;
  }

  /**
   * Returns a list of the column names, which among to the primary key, as a list of String
   */
  public List<String> getPKFields() {
    return _pkFields;
  }

  /**
   * Returns a list of the column names, which among to the foreign key, with the set prefix and a
   * "." after it as a list of String
   */
  public List<String> getFKFields(String prefix) {
    List<String> ret = new ArrayList<String>();
    for (String string : _fkFields) {
      ret.add(prefix + '.' + string);
    }
    return ret;
  }

  /**
   * Returns a list of the column names, which among to the primary key, with the set prefix and a
   * "." after it as a list of String
   */
  public List<String> getPKFields(String prefix) {
    List<String> ret = new ArrayList<String>();
    for (String string : _pkFields) {
      ret.add(prefix + '.' + string);
    }
    return ret;
  }

  /**
   * Returns the name of the foreign key
   */
  public String getFKName() {
    return _fkFields.get(0);
  }

  /**
   * Returns the name of the primary key
   */
  public String getPKName() {
    return _pkFields.get(0);
  }

  /**
   * Returns the table name of the foreign key
   */
  public String getFKTableName() {
    return _fkTable;
  }

  /**
   * Returns the table name of the primary key
   */
  public String getPKTableName() {
    return _pkTtable;
  }

  /**
   * Checks, whether the foreign table is a m:n table (simple name convention)
   * 
   * @return <code>true</code> if the foreign table is a m:n table
   */
  public boolean isJoinTable() {
    return -1 != _fkTable.indexOf('_');
  }

  @Override
  public String toString() {
    StringBuilder fks = new StringBuilder();
    for (int i = 0; i < _fkFields.size(); i++) {
      fks.append(_fkFields.get(i)).append(',');
    }
    StringBuilder pks = new StringBuilder();
    for (int i = 0; i < _pkFields.size(); i++) {
      pks.append(_pkFields.get(i)).append(',');
    }
    return "[FKDefinition: FKname: " + _fkName + ",FKtable: " + _fkTable + ",PKtable: " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + _pkTtable + ",# FKfields: " + _fkFields.size() + ": [" + fks + "],# PKfields: " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + _pkFields.size() + ": [" + pks + "]]"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}
