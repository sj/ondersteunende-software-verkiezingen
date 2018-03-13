package de.ivu.util.tools.tablegen;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;

/**
 * Container for details about a table column.
 * 
 * @author <a href="mailto:cos@ivu.de">Dr. Domagoj Cosic</a> (c) 2004 IVU Traffic Technologies AG
 */

public class ColumnData {
  // NB "TEXT" is my own add on!
  private static final int TINYBLOB = 18;
  private static final int TEXT = 22;
  private static final int TIMESTAMP = 14;
  private static final int DATE = 11;
  private static final int OTHER = 21;
  private static final int LONG_RAW = 19;
  private static final String[] sqlTypes = {
      "CHAR", "TINYINT", "BIGINT", "INT", "SMALLINT", "FLOAT", "REAL", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
      "DOUBLE", "NUMERIC", "DECIMAL", "DATE", "VARCHAR", "LONGVARCHAR", "TIMESTAMP", "TIME", "BIT", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
      "BINARY", "VARBINARY", "LONGVARBINARY", "NULL", "OTHER", "TEXT"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

  private int _type;
  private final int _columnSize;
  private final String _name;
  private final String _tablename;
  private final boolean _isPK;
  private final boolean _isFK;
  private final boolean _isNullable;
  private final boolean _isUnique;

  /**
   * Standard constructor. Requires name, XOPEN type, number of columns. The last is only used for
   * CHAR(x) and DATE fields.
   * 
   * @param name column name
   * @param tablename table name
   * @param origType SQL type from java.sql.Types
   * @param columnSize for char or date types this is the maximum number of characters, for numeric
   *          or decimal types this is precision
   * @param isPK <code>true</code> if the column is a primary key
   * @param isFK <code>true</code> if the column is a foreign key
   * @param isNullable <code>true</code> if the column is nullable
   * @param isUnique <code>true</code> if the column has a UNIQUE constraint
   */
  public ColumnData(String name,
      String tablename,
      int origType,
      int columnSize,
      boolean isPK,
      boolean isFK,
      boolean isNullable,
      boolean isUnique) {
    _name = name;
    _tablename = tablename;
    _type = origType;
    _columnSize = columnSize;
    _isPK = isPK;
    _isFK = isFK;
    _isNullable = isNullable;
    _isUnique = isUnique;

    // Uncomment this is you want to see the raw types used
    // for the columns. Usefully for debugging the inconsistent
    // use in between drivers/databases.
    //
    // System.out.println("ColumnData: name: "+name+", type: "+type+",
    // columns: "+columns);

    //
    switch (_type) {
      case Types.LONGVARCHAR :
        // Sybase
        // TEXT
        _type = TEXT;
        break;

      case Types.BINARY :
        // MySQL
        // TINYBLOB - use VARBINARY for now. Cobble alert!
        _type = TINYBLOB;
        break;

      case Types.VARBINARY :
        // MySQL
        // medium blob / blob to LONGVARBINARY
        _type = LONG_RAW;

        // Arse -> Some serious type conflicts here
        // This issue needs sorting out. Need a heavy duty
        // dig down the JDBC specs. Sigh...

        // OOOPS - conflict. MySQL gets priority for now.
        // PostGres
        // abstime / timestamp
        // type = 14; // use timestamp

        // OOOPS - conflict. Postgres gets priority for now.
        // Oracle
        // RAW which maps to VARBINARY
        // type = 18;
        break;

      case Types.LONGVARBINARY :
        // Oracle
        // LONG RAW which maps to LONGVARBINARY
        _type = LONG_RAW;
        break;

      case Types.BIGINT :
        // MySQL
        // bigint
        _type = Types.DECIMAL;
        break;

      case Types.TINYINT :
        // Sybase
        // TINYINT
        _type = Types.NUMERIC;
        break;

      case Types.BIT :
        // Sybase
        // BIT
        _type = Types.BOOLEAN;
        break;

      case Types.OTHER :
        _type = OTHER;
        break;

      case Types.DATE :
        // PostGres
        // date
        _type = DATE;
        break;

      case Types.TIME :
        // PostGres
        // timestamp
        _type = TIMESTAMP;
        break;

      case Types.TIMESTAMP :
        // MySQL
        // timestamp
        _type = TIMESTAMP;
        break;

      default :
        break;
    }

    if ((_type < 1) || (_type > 24)) {
      System.err
          .println("Warning! - column name : " + name + " is of a type not recognised. Value : " //$NON-NLS-1$ //$NON-NLS-2$
              + _type);
      System.err.println("Defaulting to string"); //$NON-NLS-1$
      _type = 12;
    }
  }

  @Override
  public String toString() {
    String res;
    String digits;

    if (_type == 1 || _type == DATE)
      digits = "(" + _columnSize + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    else
      digits = ""; //$NON-NLS-1$

    if (_type > sqlTypes.length || _type < 0)
      res = "Type : " + _type + "  Name : " + _name; //$NON-NLS-1$ //$NON-NLS-2$
    else
      res = "Type : " + sqlTypes[_type - 1] + digits + " Name : " + _name; //$NON-NLS-1$ //$NON-NLS-2$

    return res;
  }

  /**
   * Writes out the equivalent Java type of the column SQL type.
   * 
   * @return Java-Type as String
   */
  public String getJavaType() {
    String jType = null;

    switch (_type) {
      case Types.CHAR :
      case Types.VARCHAR :
      case 13 :
      case TEXT :
        jType = "String"; //$NON-NLS-1$
        break;

      case Types.NUMERIC :
        jType = "byte"; //$NON-NLS-1$
        break;

      case Types.DECIMAL :
        if (_columnSize == 1) {
          // we use NUMBER(1) for boolean values...
          jType = "boolean"; //$NON-NLS-1$
        } else {
          // jType = "long";
          // int will fit...
          jType = "BigDecimal"; //$NON-NLS-1$
        }
        break;

      case Types.INTEGER :
        jType = "int"; //$NON-NLS-1$
        break;

      case Types.SMALLINT :
        jType = "boolean"; //$NON-NLS-1$
        // jType = "short";
        break;

      case Types.FLOAT :
      case Types.DOUBLE :
        jType = "double"; //$NON-NLS-1$
        break;

      case 9 :
      case 10 :
        jType = "BigDecimal"; //$NON-NLS-1$
        break;

      case Types.REAL :
        jType = "float"; //$NON-NLS-1$
        break;

      case DATE :
        // jType = "Date";
        // always return Timestamp as it's more accurate
        // and is a superset of Date
        // NOPE - lets follow Suns recommendations, so...
        jType = "Timestamp"; //$NON-NLS-1$
        break;

      case TIMESTAMP :
        // jType = "Date";
        jType = "Timestamp"; //$NON-NLS-1$
        break;

      case 15 :
        // jType = "Date";
        jType = "Time"; //$NON-NLS-1$
        break;

      case Types.BOOLEAN :
        jType = "boolean"; //$NON-NLS-1$
        break;

      case 17 :
      case TINYBLOB :
      case LONG_RAW :
        jType = "byte[]"; //$NON-NLS-1$
        break;

      case 20 :
        jType = "null"; //$NON-NLS-1$
        break;

      case OTHER :
        jType = "Object"; //$NON-NLS-1$
        break;

      default :
        System.out.println("Warning - col type : " + _type + " is unknown"); //$NON-NLS-1$ //$NON-NLS-2$
        jType = "unknown"; //$NON-NLS-1$
        break;
    }

    return jType;
  }

  /**
   * Writes out the equivalent Java type of the column SQL type.
   * 
   * @return Java-Type as Class-Object
   */
  public Class<?> getJavaClass() {
    switch (_type) {
      case Types.CHAR :
      case Types.VARCHAR :
      case 13 :
      case TEXT :
        return String.class;
      case Types.NUMERIC :
        return byte.class;
      case Types.DECIMAL :
        if (_columnSize == 1) {
          // we use NUMBER(1) for boolean values...
          return boolean.class;
        } else {
          return BigDecimal.class;
        }
      case Types.INTEGER :
        return int.class;
      case Types.SMALLINT :
        return boolean.class;
      case Types.FLOAT :
      case Types.DOUBLE :
        return double.class;
      case 9 :
      case 10 :
        return BigDecimal.class;
      case Types.REAL :
        return float.class;
      case DATE :
      case TIMESTAMP :
        return Timestamp.class;
      case 15 :
        return Time.class;
      case Types.BOOLEAN :
        return boolean.class;
      case 17 :
      case TINYBLOB :
      case LONG_RAW :
        return byte[].class;
      case 20 :
        return null;
      case OTHER :
        return Object.class;
      default :
        System.out.println("Warning - col type : " + _type + " is unknown"); //$NON-NLS-1$ //$NON-NLS-2$
        return null;
    }
  }

  /**
   * Generation of the setting method for the Prepared Statement, which reads a field from the Value
   * Object
   * 
   * @param fieldName Name of the field
   * @param pos Position in the statement
   * @param var Name of the Variable of the Value Object
   * @param prefix Prefix for the Access to the fields of the Value Object
   * @return Text of the call of the setting method
   */
  public String getPrepSetFktCall(String fieldName, String pos, String var, String prefix) {
    String prepFkt = null;
    String varzugriff = prefix == null ? TableGen.propertyName(fieldName) : (prefix
        + TableGen.firstUp(fieldName) + "()"); //$NON-NLS-1$
    if (var != null) {
      varzugriff = var + "._" + varzugriff; //$NON-NLS-1$
    }

    switch (_type) {
      case Types.CHAR :
      case Types.VARCHAR :
      case 13 :
      case TEXT :
        prepFkt = "setString"; //$NON-NLS-1$
        break;

      case Types.NUMERIC :
        prepFkt = "setByte"; //$NON-NLS-1$
        break;

      case Types.DECIMAL :
        prepFkt = "setBigDecimal"; //$NON-NLS-1$
        break;

      case Types.INTEGER :
        prepFkt = "setInt"; //$NON-NLS-1$
        break;

      case Types.SMALLINT :
        // prepFkt = "setShort";
        prepFkt = "setBoolean"; //$NON-NLS-1$
        break;

      case Types.FLOAT :
      case Types.DOUBLE :
        prepFkt = "setDouble"; //$NON-NLS-1$
        break;

      case 9 :
      case 10 :
        prepFkt = "setBigDecimal"; //$NON-NLS-1$
        break;

      case Types.REAL :
        prepFkt = "setFloat"; //$NON-NLS-1$
        break;

      case DATE :
        prepFkt = "setTimestamp"; //$NON-NLS-1$
        break;

      case TIMESTAMP :
        prepFkt = "setTimestamp"; //$NON-NLS-1$
        break;

      case 15 :
        prepFkt = "setTime"; //$NON-NLS-1$
        break;

      case Types.BOOLEAN :
        prepFkt = "setBoolean"; //$NON-NLS-1$
        break;

      case 17 :
      case TINYBLOB :
      case LONG_RAW :
        prepFkt = "setBytes"; //$NON-NLS-1$
        break;

      case 20 :
        prepFkt = "setNull"; //$NON-NLS-1$
        break;

      case OTHER :
        prepFkt = "setObject"; //$NON-NLS-1$
        break;

      default :
        System.out.println("Warning - col type : " + _type + " is unknown"); //$NON-NLS-1$ //$NON-NLS-2$
        prepFkt = "unknown"; //$NON-NLS-1$
        break;
    }
    // add parameter
    if (_type == 3 & _columnSize == 1) {
      // boolean
      prepFkt += ("(" + pos + ", " + varzugriff + "?1:0)"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    } else {
      prepFkt += ("(" + pos + ", " + varzugriff + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
    return prepFkt;
  }

  /**
   * Returns columnSize
   */
  int getColumnSize() {
    return _columnSize;
  }

  /**
   * Checks for foreign key
   */
  boolean isFK() {
    return _isFK;
  }

  /**
   * Checks for nullable
   * 
   * @return isNullable.
   */
  boolean isNullable() {
    return _isNullable;
  }

  /**
   * Checks for primary key
   * 
   * @return isPK.
   */
  boolean isPK() {
    return _isPK;
  }

  /**
   * Checks for unique
   * 
   * @return isUnique.
   */
  boolean isUnique() {
    return _isUnique;
  }

  /**
   * Returns name
   * 
   * @return name.
   */
  String getName() {
    return _name;
  }

  /**
   * Returns name of the table
   * 
   * @return tablename.
   */
  String getTablename() {
    return _tablename;
  }
}
