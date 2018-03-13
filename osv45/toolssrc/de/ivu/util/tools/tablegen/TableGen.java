package de.ivu.util.tools.tablegen;

/**
 * TableGen
 * <p>
 * A generator of container classes and beans for a database. This application uses the database metadata to
 * create one object per table designed to hold the type of data stored within that table.
 * </p>
 * It also generate methods to update,retrieve and insert the object data within the database. The update and
 * retrieve methods come in two flavours, ByKey and ByIndex.
 * </p>
 * <p>
 * If keys are found on a table then updateByKey and retrieveByKey methods are created
 * </p>
 * <p>
 * If foreign_keys are found on the table retrieveByXXX methods are created for each key as well insert and
 * getFromResultSet methods are always created.
 * 
 * @author J.A.Carter / D.Cosic
 * @version 2.0 (c) Joe Carter 1998, Domagoj Cosic 2004 Released under GPL. See LICENSE for full details.
 */
import static java.lang.Character.isLowerCase;
import static java.lang.System.getProperty;
import static java.lang.System.out;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectStreamClass;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 * @author SMA@ivu.de, IVU Traffic Technologies AG
 */
public class TableGen {

  private static final boolean EMF_MODEL_ANNOTATIONS = false;

  /**
   * Variable name for the instance variable of an Entity Bean, which referencees the Value Object
   */
  private static final String BEAN_STATE_VAR = "_details"; //$NON-NLS-1$

  /**
   * URL of the database whose meta data is used for the generation
   */
  private String _url;

  /**
   * User name of the database user, whose meta data is used for the generation
   */
  private String _username;

  /**
   * Password of the database user, whose meta data is used for the generation
   */
  private String _password;

  /**
   * Name of the JDBC driver class of the database, whose meta data is used for the generation
   */
  private String _driver;

  /**
   * Catalog of the database, whose meta data is used for the generation
   */
  String _catalog;

  /**
   * Scheme of the database, whose meta data is used for the generation
   */
  String _schema;

  /**
   * Name of the (highest) packages, in which the generated classes are generated
   */
  private String _packageName;

  /**
   * Physical directory name, in which the generated data is saved, which equals to the package name
   */
  private String _tablesDir;

  /**
   * Physical directory name for the manual written classes, which equals to the package name
   */
  private String _srcDir;

  /**
   * Prefix for the name of the classes and interfaces
   */
  private String _prefix;

  /**
   * Path of the Jaws-file for JBoss CMP 1.0 Descriptor
   */
  private String _jawFile;

  /**
   * Version string for the generated classes
   */
  private String _defVersion;

  /**
   * Revision string for the generated classes
   */
  private String _defRevision;

  /**
   * JDBC meta data of the database used to the generation
   */
  DatabaseMetaData _metaData;

  /**
   * Converted and local temporary saved meta data of the database used to the generation
   */
  private Meta _meta;

  /**
   * Generate methods, which access to the data via the primary keys
   */
  private boolean _keys;

  /**
   * Generate Methods, which access to the data via the foreign keys
   */
  private boolean _foreignKeys;

  /**
   * Generate Methods, which access to the data via the indices
   */
  private boolean _indexes;

  /**
   * The local interface has to inherit from model interface (else remote)
   */
  private boolean _modelTypeIsLocal;

  /**
   * Generate local interfaces
   */
  private boolean _local;

  /**
   * Generte remote interfaces
   */
  private boolean _remote;

  /**
   * Remove all from the generated directories, which does not comply to the generated state (equals
   * to the removal of the directories with additional complete new generation, but more efficient)
   */
  private boolean _genClean;

  /**
   * Enforces the generation, independent of all other parameters
   */
  private boolean _force;

  /**
   * Database connection
   */
  private Connection _connection;

  /**
   * Output channel for writing the actual generated class file/ interface file to the hard disk
   */
  private Writer _currentOutput;

  /**
   * Effective list of the table names for which the access code has to be generated
   */
  private List<String> _tableNames;

  /**
   * List of the table names for which the Entity Beans have to be generated
   */
  private List<String> _ejbTableNames;

  /**
   * List of the table names for which the write-once Entity Beans have to be generated
   */
  private List<String> _ejbWriteOnceTableNames;

  /**
   * List of the file names, which are generated manual (in src-path). For theses files only one
   * abstract super class is generated by TableGen, from which the manual handled class has to be
   * derived. A frame is generated in the src-path, if the class does not exist.
   */
  private List<String> _deriveFileList;

  /**
   * Declared list of the table names for which the access code has to be generated or
   * <code>null</code>, if the code has to be generated for all tables of the scheme.
   */
  private Set<String> _tablesSet;

  /**
   * Declared list of table names for which the access code has not to be generated or
   * <code>null</code>, if the code has to be generated for all tables of the scheme.
   */
  private Set<String> _nonTablesSet;

  /**
   * Declared list of the table names for which the Entity Beans have to be generated or
   * <code>null</code>, if Entity Beans have to be generated for all tables of the scheme.
   */
  private Set<String> _ejbTablesSet;

  /**
   * Declared list of table names for which the write-once Entity Beans have to be generated.
   */
  private Set<String> _ejbWriteOnceTablesSet;

  /**
   * Additional packages with classes and interfaces of the model objects and EJBs, which are
   * referenced by the generated classes.
   */
  private List<String> _additionalImports;

  /**
   * All file names, which comply to the generated state. Everything that is not in it, will be
   * removed from the directories after generation, if _genClean is set.
   * 
   * @see #_genClean
   */
  private Set<File> _cleanSet;

  // static part
  /**
   * Amount of the insertion of a shifting of a layer of the generated source codes in blanks/ space
   * characters.
   */
  private static final int __indentStep = 3;

  /**
   * Prefix for the generated abstract super classes (when derived manually)
   */
  private static final String ABSTR_PREFIX = "Basic"; //$NON-NLS-1$

  /**
   * Complete name of this class
   */
  public static final String __className = TableGen.class.getName();

  /**
   * List of the configuration files, which have to be executed at the generation.
   */
  private static final List<String> __propFileNames = new ArrayList<String>();

  /**
   * Generate home interfaces for the Beans.
   */
  private static boolean __genBeanHome;

  /**
   * Generate Bean implementations for the Beans.
   */
  private static boolean __genBean;

  /**
   * Generate Bean interfaces for the Beans.
   */
  private static boolean __genBeanInterface;

  /**
   * Generate Jaws-Descriptor
   */
  private static boolean __genJaws;

  /**
   * Generate interfaces for Value Objects
   */
  private static boolean __genValueObjectInterface;

  /**
   * Generate implementations for the Value Objects
   */
  private static boolean __genValueObject;

  /**
   * Generate JDBC access code (DataBase-Access)
   */
  private static boolean __genDBA;

  /**
   * Generate fragment of the EJB descriptor, which describes the Entity Beans and their connection
   * among each other.
   */
  private static boolean __genEJBDescriptorFragment;

  /**
   * Generate fragment of the JBoss EJB descriptor, which describes the Entity Beans
   */
  private static boolean __genJBossDescriptorFragment;

  /**
   * Generic classes for the database access objects
   */
  private static final String[] __dbaGenericFiles = {"de.ivu.ejb.fw.DBABase"}; //$NON-NLS-1$

  /**
   * Separator inside of the file paths
   */
  private static final String __sep = "/"; //$NON-NLS-1$

  /**
   * Alias for the table in the SELECT clause
   */
  private static final String __objAlias = null;

  /**
   * Prefix, which contains the EJBs in the JBoss Naming Service
   */
  private static final String __jbossJNDIPrefix = "jboss/wahl/"; //$NON-NLS-1$

  /**
   * Super interface of the interfaces of the Value Objects
   */
  private static final String __voRootInterface = "de.ivu.ejb.bmp.Model"; //$NON-NLS-1$

  /**
   * Super class of the implementations of the Value Objects
   */
  private static final String __voImplRootClass = "de.ivu.ejb.bmp.ModelImpl"; //$NON-NLS-1$

  /**
   * Super class of the implementations of the Value Objects, which picture the M:N tables
   */
  private static final String __mnVoImplRootClass = "de.ivu.ejb.bmp.ModelImpl"; //$NON-NLS-1$

  /**
   * Super class of the Entity Beans
   */
  private static final String __beanImplRootClass = "de.ivu.ejb.bmp.BMPBeanBase"; //$NON-NLS-1$

  /**
   * Relative name of the packages for the implementations of the Value Objects
   */
  private static final String __implPackage = "impl"; //$NON-NLS-1$

  /**
   * Name prefix of the interfaces of the Value Objects
   */
  private static final String __voInterfacePrefix = ""; //$NON-NLS-1$

  /**
   * Name suffix of the interfaces of the Value Objects
   */
  private static final String __voInterfaceSuffix = "Model"; //$NON-NLS-1$

  /**
   * Name suffix of the implementations of the Value objects
   */
  private static final String __voClassSuffix = "ModelImpl"; //$NON-NLS-1$

  /**
   * Name suffix of the implementations of the database access adapter
   */
  private static final String __dbaClassSuffix = "DBA"; //$NON-NLS-1$

  /**
   * Relative name of the packages for the EJBs
   */
  private static final String __ejbPackage = "ejb"; //$NON-NLS-1$

  /**
   * Name suffix of the implementations of the EJBs
   */
  private static final String __beanClassSuffix = "Bean"; //$NON-NLS-1$

  /**
   * Name suffix of the home interfaces of the EJBs
   */
  private static final String __beanHomeSuffix = "RemoteHome"; //$NON-NLS-1$

  /**
   * Name suffix of the remote interfaces of the EJBs
   */
  private static final String __beanRemoteSuffix = "Remote"; //$NON-NLS-1$

  /**
   * Name suffix of the LocalHome interfaces of the EJBs
   */
  private static final String __beanLocalHomeSuffix = "Home"; //$NON-NLS-1$

  /**
   * Name suffix of the Local interfaces of the EJBs
   */
  private static final String __beanLocalSuffix = ""; //$NON-NLS-1$

  /**
   * Name in the generated fragment of the JBoss-EJB-Descriptor for the Entity Beans preset EJB
   * container configuration
   */
  private static final String __jbossConfigurationName = "Instance Per Transaction BMP EntityBean"; //$NON-NLS-1$

  /**
   * Name of the data source referenced in the EJB descriptor of the Entity Beans
   */
  private static final String __datasourceRefName = "jdbc/wahl"; //$NON-NLS-1$

  /**
   * Type of the data source referenced in the EJB descriptor of the Entity Beans
   */
  private static final String __datasourceType = "javax.sql.DataSource"; //$NON-NLS-1$

  /**
   * Prefix of the names of the key fields in the database
   */
  private static final String __idPrefix = "ID_"; //$NON-NLS-1$

  /**
   * Buffer of the intern representations of the database meta data
   */
  private static final Map<String, Meta> __metaCache = new HashMap<String, Meta>();

  /**
   * Database connections to the (different) database URLs for the reading of the meta data.
   */
  private static final Map<String, Connection> __connections = new HashMap<String, Connection>();

  /**
   * Get meta data in the internal format for the specified scheme.
   * 
   * @param dmd
   * @param schema
   * @return
   * @throws SQLException
   */
  private Meta getMeta(DatabaseMetaData dmd, String schema) throws SQLException {
    String url = dmd.getURL();
    String id = url + "#" + schema; //$NON-NLS-1$
    Meta meta = __metaCache.get(id);
    if (meta == null) {
      meta = new Meta(dmd);
      __metaCache.put(id, meta);
    } else {
      System.out.println("Found cached metadata"); //$NON-NLS-1$
    }
    return meta;
  }

  /**
   * Internal representation of a foreign key in the cache of the meta data
   */
  private class ForeignKey {
    public String pkTable;
    public String pkColumn;
    public String fkTable;
    public String fkColumn;
    public short sequence;
    public String fkName;

    ForeignKey(ResultSet rs, Map<String, String> lower2OriginalTableName) throws SQLException {
      pkTable = lower2OriginalTableName.get(rs.getString(3).toLowerCase());
      pkColumn = rs.getString(4);
      fkTable = lower2OriginalTableName.get(rs.getString(7).toLowerCase());
      fkColumn = rs.getString(8);
      sequence = rs.getShort(9);
      fkName = rs.getString(12);
    }

    @Override
    public String toString() {
      return "ForeignKey pkTable=" + pkTable + " pkColumn=" + pkColumn + " fkTable=" + fkTable //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + " fkColumn=" + fkColumn + " sequence=" + sequence + " fkName=" + fkName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof ForeignKey)) {
        return false;
      }
      ForeignKey other = (ForeignKey) obj;
      return eq(pkTable, other.pkTable) && eq(pkColumn, other.pkColumn)
          && eq(fkTable, other.fkTable) && eq(fkColumn, other.fkColumn);
    }

    private boolean eq(String a, String b) {
      return a == null ? b == null : a.equals(b);
    }

    @Override
    public int hashCode() {
      return pkColumn.hashCode() ^ fkColumn.hashCode();
    }
  }

  /**
   * Internal representation of a table column in the cache of the meta data.
   */
  private class Column {
    public String column;
    public int dataType;
    public int size;
    public boolean isNullable;

    Column(ResultSet rs) throws SQLException {
      column = rs.getString(4);
      dataType = rs.getInt(5);
      size = rs.getInt(7);
      isNullable = rs.getInt("NULLABLE") == DatabaseMetaData.columnNullable; //$NON-NLS-1$
    }

    @Override
    public String toString() {
      return "Column column=" + column + " dataType=" + dataType + " size=" + size; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
  }

  /**
   * Internal representation of an index in the cache of the meta data.
   */
  private class IndexInfo {
    public short type;
    public String name;
    boolean isUnique;

    IndexInfo(ResultSet rs) throws SQLException {
      type = rs.getShort("TYPE"); //$NON-NLS-1$
      name = rs.getString("COLUMN_NAME"); //$NON-NLS-1$
      isUnique = !rs.getBoolean("NON_UNIQUE"); //$NON-NLS-1$
    }

    @Override
    public String toString() {
      return name;
    }
  }

  /**
   * Interanal representation of a table in the cache of the meta data.
   */
  private class TableMeta {
    String _name;
    List<Column> _columns = new ArrayList<Column>();
    Map<Object, String> _primaryKeys;
    LinkedHashSet<ForeignKey> _importedKeys = new LinkedHashSet<ForeignKey>();
    LinkedHashSet<ForeignKey> _exportedKeys = new LinkedHashSet<ForeignKey>();
    List<IndexInfo> _indexInfos = new ArrayList<IndexInfo>();

    public TableMeta(String name) {
      _name = name;
      // System.out.println(toString());
    }

    /**
     * Get foreign keys as array exported from this table.
     */
    public ForeignKey[] getExportedKeys() {
      return _exportedKeys.toArray(new ForeignKey[_exportedKeys.size()]);
    }

    public void addExportedKey(ForeignKey key) {
      _exportedKeys.add(key);
    }

    /**
     * Get foreign keys as array imported in this table.
     */
    public ForeignKey[] getImportedKeys() {
      return _importedKeys.toArray(new ForeignKey[_importedKeys.size()]);
    }

    public void addImportedKey(ForeignKey key) {
      _importedKeys.add(key);
    }

    /**
     * Get information structures for the indices of this table
     */
    public List<IndexInfo> getIndexInfos() {
      return _indexInfos;
    }

    public void addIndexInfo(IndexInfo info) {
      _indexInfos.add(info);
    }

    /**
     * Get table name
     */
    public String getName() {
      return _name;
    }

    /**
     * @param dmd meta data of the database
     * @return components of the primary keys of the table as array in the correct order
     * @throws SQLException
     */
    public List<String> getPrimaryKeys(DatabaseMetaData dmd) throws SQLException {

      if (_primaryKeys == null) {
        _primaryKeys = new TreeMap<Object, String>();
        ResultSet pks = dmd.getPrimaryKeys(_catalog, _schema, _name);
        while (pks.next()) {
          _primaryKeys.put(pks.getObject("KEY_SEQ"), pks.getString("COLUMN_NAME")); //$NON-NLS-1$ //$NON-NLS-2$
        }
        pks.close();
      }

      return new ArrayList<String>(_primaryKeys.values());
    }

    /**
     * @return Columns of the table (with meta data)
     */
    public Column[] getColumns() {
      return _columns.toArray(new Column[_columns.size()]);
    }

    public void addColumn(Column column) {
      _columns.add(column);
    }

    @Override
    public String toString() {
      return "Table name=" + _name; //$NON-NLS-1$
    }
  }

  /**
   * Internal representation of the database meta data in the cache of the meta data.
   */
  private class Meta {
    Map<String, TableMeta> _tableMetaMap = new TreeMap<String, TableMeta>();
    private final Map<String, String> _lower2OriginalTableName = new HashMap<String, String>();

    private String normalize(String tableName) {
      String normalized = _lower2OriginalTableName.get(tableName.toLowerCase());
      return normalized == null ? tableName : normalized;
    }

    Meta(DatabaseMetaData dmd) throws SQLException {
      ResultSet cols = dmd.getColumns(_catalog, _schema, "%", "%"); //$NON-NLS-1$ //$NON-NLS-2$
      while (cols.next()) {
        getOrCreateTableMeta(cols.getString(3)).addColumn(new Column(cols));
      }
      cols.close();

      for (Iterator<String> tItr = getTables(); tItr.hasNext();) {
        String tableName = tItr.next();
        _lower2OriginalTableName.put(tableName.toLowerCase(), tableName);
      }

      for (Iterator<String> tItr = getTables(); tItr.hasNext();) {
        String tableName = tItr.next();
        ResultSet imk = dmd.getImportedKeys(_catalog, _schema, tableName);
        while (imk.next()) {
          getOrCreateTableMeta(imk.getString(7)).addImportedKey(new ForeignKey(imk,
              _lower2OriginalTableName));
        }
        imk.close();

        // System.out.println("Exported Keys");
        ResultSet exk = dmd.getExportedKeys(_catalog, _schema, tableName);
        while (exk.next()) {
          getOrCreateTableMeta(exk.getString(3)).addExportedKey(new ForeignKey(exk,
              _lower2OriginalTableName));
        }
        exk.close();

        Set<String> checkIndexes = new HashSet<String>();
        TableMeta tm = getOrCreateTableMeta(tableName);
        ResultSet inx = _metaData.getIndexInfo(_catalog, _schema, tableName, false, false);
        while (inx.next()) {
          short indexType = inx.getShort(7);
          String indexName = inx.getString(9);

          if (indexType != DatabaseMetaData.tableIndexStatistic) {
            // ensure that it is not a duplicate value.
            if (checkIndexes.add(indexName)) {
              tm.addIndexInfo(new IndexInfo(inx));
            }
          }
        }
        inx.close();
      }
    }

    TableMeta getTableMeta(String tableName) {
      return _tableMetaMap.get(tableName);
    }

    private TableMeta getOrCreateTableMeta(String tableName) {
      String normalizedTableName = normalize(tableName);
      TableMeta tMeta = _tableMetaMap.get(normalizedTableName);
      if (tMeta == null) {
        tMeta = new TableMeta(normalizedTableName);
        _tableMetaMap.put(normalizedTableName, tMeta);
      }
      return tMeta;
    }

    Iterator<String> getTables() {
      return _tableMetaMap.keySet().iterator();
    }
  }

  /**
   * Application start.
   * 
   * @param argv arguments
   * @throws IOException
   */
  public static void main(String[] argv) throws IOException {
    TableGen tg = null;
    if (argv.length >= 1) {
      if (argv.length == 1) {
        makeAll(true);
        __propFileNames.add(argv[0]);
      } else {
        if (!parseArg(argv)) {
          System.exit(-1);
        }
      }
    } else {
      makeAll(true);
      __propFileNames.add(null);
    }
    for (String propFileName : __propFileNames) {
      try {
        tg = new TableGen(propFileName);

        // Check for changes since the last generation
        if (!tg._force && tg._defVersion != null) {
          File versionFile = new File(tg._tablesDir, "version"); //$NON-NLS-1$
          if (versionFile.exists()) {
            long lastBuilt = versionFile.lastModified();
            final long tgpLastModified = new File(propFileName).lastModified();
            if (tgpLastModified < lastBuilt) {
              final Class<TableGen> myClass = TableGen.class;
              final long tgLastModified = myClass.getResource(myClass.getSimpleName() + ".class") //$NON-NLS-1$
                  .openConnection().getLastModified();

              if (tgLastModified < lastBuilt) {
                System.out.println("No changes since the last build (" + new Date(lastBuilt) + ")"); //$NON-NLS-1$ //$NON-NLS-2$
                continue;
              }
            }
          }
        }

        for (String name : tg._ejbTablesSet) {
          System.out.println("EJB: " + name); //$NON-NLS-1$
        }

        for (String name : tg._ejbWriteOnceTablesSet) {
          System.out.println("EJB Write Once: " + name); //$NON-NLS-1$
        }

        if (tg._genClean) {
          tg.prepareCleanGen(new File(tg._tablesDir));
        }
        tg.connect(); // get MetaData as well
        tg.getTableData();
        if (__genValueObjectInterface) {
          tg.generateValueObjectInterfaces();
        }
        if (__genValueObject) {
          tg.generateValueObjects();
        }
        if (__genDBA) {
          tg.generateDBAClasses();
        }
        if (__genBeanHome) {
          tg.generateBeanHomes();
        }
        if (__genBeanInterface) {
          tg.generateBeanInterfaces();
        }
        if (__genBean) {
          tg.generateBeans();
        }
        if (__genJaws) {
          tg.generateJawFinder();
        }
        if (__genEJBDescriptorFragment) {
          tg.generateEJBDescriptorFragment();
          tg.generateEJBDescriptorRefFragment();
          tg.generateWebDescriptorRefFragment();
        }
        if (__genJBossDescriptorFragment) {
          tg.generateJBossDescriptorFragment();
          tg.generateJBossWebDescriptorFragment();
          tg.generateTomcatServerDescriptorFragment();
        }
        if (tg._defVersion != null) {
          File versionFile = new File(tg._tablesDir, "version"); //$NON-NLS-1$
          tg._cleanSet.remove(versionFile);
          Writer fw = new OutputStreamWriter(new FileOutputStream(versionFile), System
              .getProperty("file.encoding")); //$NON-NLS-1$
          fw.write(tg._defVersion);
          fw.close();
        }
        if (tg._genClean) {
          tg.cleanGen();
        }
      } catch (SQLException ex1) {
        ex1.printStackTrace(System.out);
      } catch (ClassNotFoundException ex2) {
        ex2.printStackTrace(System.out);
      }
    }
  }

  /**
   * Parse arguments
   * 
   * @param argv
   * @return
   */
  private static boolean parseArg(String[] argv) {
    makeAll(false);
    for (int i = 0; i < argv.length; i++) {
      // Check that the command line has specified a filename
      String arg = argv[i];
      if (arg.equals("-h")) { //$NON-NLS-1$
        System.out.println("usage: "); //$NON-NLS-1$
        System.out.println("[switches] [property file name]"); //$NON-NLS-1$
        System.out.println("  switches:"); //$NON-NLS-1$
        System.out.println("       -a   default switch -> make all"); //$NON-NLS-1$
        System.out.println("       -j   make jaws.xml finder"); //$NON-NLS-1$
        System.out.println("       -b   make the bean files"); //$NON-NLS-1$
        System.out.println("       -i   make the value object interfaces"); //$NON-NLS-1$
        System.out.println("       -c   make the value objects"); //$NON-NLS-1$
        System.out.println("       -d   make the DBA files"); //$NON-NLS-1$
        System.out.println("       -e   make the ejb-jar.xml fragment"); //$NON-NLS-1$
        System.out.println("       -s   make the jboss.xml fragment"); //$NON-NLS-1$
        return false;
      } else if (arg.startsWith("-")) { //$NON-NLS-1$
        if (arg.indexOf("a") != -1) { //$NON-NLS-1$
          makeAll(true);
        }
        if (arg.indexOf("j") != -1) { //$NON-NLS-1$
          __genJaws = true;
        }
        if (arg.indexOf("b") != -1) { //$NON-NLS-1$
          __genBeanHome = true;
          __genBean = true;
          __genBeanInterface = true;
        }
        if (arg.indexOf("i") != -1) { //$NON-NLS-1$
          __genValueObjectInterface = true;
        }
        if (arg.indexOf("c") != -1) { //$NON-NLS-1$
          __genValueObject = true;
        }
        if (arg.indexOf("d") != -1) { //$NON-NLS-1$
          __genDBA = true;
        }
        if (arg.indexOf("e") != -1) { //$NON-NLS-1$
          __genEJBDescriptorFragment = true;
        }
        if (arg.indexOf("s") != -1) { //$NON-NLS-1$
          __genJBossDescriptorFragment = true;
        }
      } else {
        __propFileNames.add(arg);
      }
    }
    return true;
  }

  /**
   * Displays the usage of the TableGen program and exits with the error code.
   * 
   * @param code wanted exit code
   */
  public static void usageAndExit(int code) {
    System.err.println("Usage: java TableGen properties_file_name"); //$NON-NLS-1$
    System.exit(code);
  }

  /**
   * Constructor from a properties file. Default file is "tablegen.properties"
   * <TABLE>
   * <TR>
   * <TH>Parameter
   * <TH>Description
   * </TR>
   * <TR>
   * <TD>driver</TD>
   * <TD>JDBC driver name</TD>
   * </TR>
   * <TR>
   * <TD>url</TD>
   * <TD>JDBC URL (path)</TD>
   * </TR>
   * <TR>
   * <TD>username</TD>
   * <TD>DB username</TD>
   * </TR>
   * <TR>
   * <TD>password</TD>
   * <TD>DB password</TD>
   * </TR>
   * <TR>
   * <TD>tables_dir</TD>
   * <TD>directory where generated classes are placed.</TD>
   * </TR>
   * <TR>
   * <TD>package_name</TD>
   * <TD>package name to use for gen classes.</TD>
   * </TR>
   * <TR>
   * <TD>keys</TD>
   * <TD>(optional) Generate key methods if keys available</TD>
   * </TR>
   * <TR>
   * <TD>foreign_keys</TD>
   * <TD>(optional) Generate retrieve methods for each foreign_key available</TD>
   * </TR>
   * <TR>
   * <TD>indexes</TD>
   * <TD>(optional) Generate index methods if indexes available</TD>
   * </TR>
   * <TR>
   * <TD>schema</TD>
   * <TD>(optional) DB schema to use</TD>
   * </TR>
   * <TR>
   * <TD>table_list</TD>
   * <TD>(optional) Comma separated list of tables to build</TD>
   * </TR>
   * <TR>
   * <TD>types_are_strings</TD>
   * <TD>Some drivers (e.g. MM MySql) return the types as String. If so set this to yes. Leave out
   * otherwise</TD>
   * <TR>
   * <TD>prefix</TD>
   * <TD>A string to prefix to classes names. To prevent namespace problems</TD>
   * </TR>
   * </TABLE>
   * 
   * @param filename file path to the property file
   */
  public TableGen(String filename) {
    String propertiesFilename = (filename == null) ? "tablegen.properties" : filename; //$NON-NLS-1$

    try {
      Properties props = new Properties();
      FileInputStream fis = new FileInputStream(propertiesFilename);

      props.load(fis);
      // e.g. connect.sybase.SybaseDriver (for Fast Forward drivers)
      _driver = props.getProperty("driver"); //$NON-NLS-1$
      // eg. jdbc:ff-sybase://servername:1024/dbname
      _url = props.getProperty("url"); //$NON-NLS-1$
      _username = props.getProperty("username"); //$NON-NLS-1$
      _password = props.getProperty("password"); //$NON-NLS-1$
      _tablesDir = props.getProperty("tables_dir"); //$NON-NLS-1$
      _srcDir = props.getProperty("src_dir"); //$NON-NLS-1$
      _packageName = props.getProperty("package_name"); //$NON-NLS-1$
      _schema = props.getProperty("schema"); //$NON-NLS-1$
      _catalog = props.getProperty("catalog"); //$NON-NLS-1$
      _jawFile = props.getProperty("jawfile"); //$NON-NLS-1$
      _defVersion = props.getProperty("defversion"); //$NON-NLS-1$
      _defRevision = props.getProperty("defrevision"); //$NON-NLS-1$

      // decide whether to enable the use of keys and indexes...
      // (some drivers do not support this
      _keys = getPropertyValue(props, "keys", true); //$NON-NLS-1$
      _foreignKeys = getPropertyValue(props, "foreign_keys", true); //$NON-NLS-1$
      _indexes = getPropertyValue(props, "indexes", false); //$NON-NLS-1$
      _remote = getPropertyValue(props, "remote", true); //$NON-NLS-1$
      _local = getPropertyValue(props, "local", false); //$NON-NLS-1$
      _modelTypeIsLocal = getPropertyValue(props, "modelType", false, "local"); //$NON-NLS-1$ //$NON-NLS-2$
      _genClean = getPropertyValue(props, "genclean", false); //$NON-NLS-1$
      _force = getPropertyValue(props, "force", false); //$NON-NLS-1$

      // remote and local may not be off at the same time
      _remote |= !(_remote || _local);
      // model type can be local only if local interfaces are generated
      _modelTypeIsLocal &= _local;
      // model type has to be local if no remote interfaces are generated
      _modelTypeIsLocal |= !_remote;

      _prefix = props.getProperty("prefix", ""); //$NON-NLS-1$ //$NON-NLS-2$

      parseTableNames(props.getProperty("table_list")); //$NON-NLS-1$
      parseEjbTableNames(props.getProperty("ejb_list")); //$NON-NLS-1$
      parseEjbWOTableNames(props.getProperty("write_once")); //$NON-NLS-1$
      _deriveFileList = parseList(props.getProperty("derive")); //$NON-NLS-1$

      _additionalImports = parseList(props.getProperty("additional_imports")); //$NON-NLS-1$

      fis.close();
    } catch (IOException e) {
      e.printStackTrace(System.err);
      usageAndExit(-3);
    }
  }

  private boolean getPropertyValue(Properties props, String propName, boolean defaultValue) {
    return getPropertyValue(props, propName, defaultValue, "yes"); //$NON-NLS-1$
  }

  private boolean getPropertyValue(Properties props,
      String propName,
      boolean defaultValue,
      String positive) {
    String propString = props.getProperty(propName);
    boolean value;
    if (propString != null) {
      value = propString.equalsIgnoreCase(positive);
    } else {
      value = defaultValue;
    }
    return value;
  }

  /**
   * Parses a comma separated list of table names into the tablesList.
   */
  protected void parseTableNames(String names) {
    if (names != null) {
      StringTokenizer st = new StringTokenizer(names, ","); //$NON-NLS-1$
      String name = null;
      while (st.hasMoreElements()) {
        name = st.nextToken();
        if (name.startsWith("~")) { //$NON-NLS-1$
          if (_nonTablesSet == null) {
            _nonTablesSet = new HashSet<String>();
          }
          _nonTablesSet.add(name.substring(1));
        } else {
          if (_tablesSet == null) {
            _tablesSet = new HashSet<String>();
          }
          _tablesSet.add(name);
        }
        out.println(name);
      }
    }
  }

  /**
   * Parses a comma separated list of EJB table names into the ejbTablesList
   * 
   * @param names
   */
  protected void parseEjbTableNames(String names) {
    if (names != null) {
      _ejbTablesSet = new HashSet<String>();
      StringTokenizer st = new StringTokenizer(names, ","); //$NON-NLS-1$
      String name = null;
      while (st.hasMoreElements()) {
        name = st.nextToken();
        _ejbTablesSet.add(name);
      }
    }
  }

  /**
   * Parses a comma separated list of the EJB write-once table names into the EJBWriteOnceTablesList
   * 
   * @param names
   */
  protected void parseEjbWOTableNames(String names) {
    if (names != null) {
      _ejbWriteOnceTablesSet = new HashSet<String>();
      StringTokenizer st = new StringTokenizer(names, ","); //$NON-NLS-1$
      String name = null;
      while (st.hasMoreElements()) {
        name = st.nextToken();
        _ejbWriteOnceTablesSet.add(name);
      }
    }
  }

  /**
   * Parses a comma separated list and return list.
   */
  protected List<String> parseList(String names) {
    List<String> ret = new ArrayList<String>();
    if (names != null) {
      StringTokenizer st = new StringTokenizer(names, ","); //$NON-NLS-1$
      while (st.hasMoreElements()) {
        ret.add(st.nextToken());
      }
    }
    return ret;
  }

  private void prepareCleanGen(File dir) {
    _cleanSet = new TreeSet<File>();
    if (dir.isDirectory()) {
      File[] children = dir.listFiles();
      for (File child : children) {
        prepareClean(child);
      }
    }
  }

  private void prepareClean(File file) {
    if (file.isDirectory()) {
      File[] children = file.listFiles();
      for (File child : children) {
        prepareClean(child);
      }
    }
    _cleanSet.add(file);
  }

  private void cleanGen() {
    for (Iterator<File> cleanItr = _cleanSet.iterator(); cleanItr.hasNext();) {
      File file = cleanItr.next();
      if (file.isFile()) {
        out.println("Delete: " + file); //$NON-NLS-1$
        file.delete();
        cleanItr.remove();
      }
    }

    for (Iterator<File> cleanItr = _cleanSet.iterator(); cleanItr.hasNext();) {
      File file = cleanItr.next();
      out.println("Delete: " + file); //$NON-NLS-1$
      file.delete();
      cleanItr.remove();
    }
  }

  /**
   * Connects to the database a get the MetaData.
   */
  /**
   * @throws SQLException no connection to the database
   * @throws ClassNotFoundException driver not found
   */
  public void connect() throws SQLException, ClassNotFoundException {
    Class.forName(_driver);
    _connection = initConnection(_url, _username, _password);
    _metaData = _connection.getMetaData();

    out.println(""); //$NON-NLS-1$
    out.println("TableGen Database->EJB Generator v2.0 by IVU"); //$NON-NLS-1$
    out.println("-----------------------------------------------"); //$NON-NLS-1$
    out.println("Database version : " + _metaData.getDatabaseProductVersion()); //$NON-NLS-1$
    out.println("Driver Name : " + _metaData.getDriverName()); //$NON-NLS-1$
    out.println("Driver Version : " + _metaData.getDriverMajorVersion() + "." //$NON-NLS-1$ //$NON-NLS-2$
        + _metaData.getDriverMinorVersion());
    out.println("url=" + _url); //$NON-NLS-1$
    out.println("username=" + _username); //$NON-NLS-1$
    out.println("password=" + _password); //$NON-NLS-1$
    out.println("catalog=" + _catalog); //$NON-NLS-1$
    out.println("schema=" + _schema); //$NON-NLS-1$

    _meta = getMeta(_metaData, _schema);
  }

  private Connection initConnection(String url, String username, String password)
      throws SQLException {
    String id = url + "#" + username + "#" + password; //$NON-NLS-1$ //$NON-NLS-2$
    Connection con = __connections.get(id);
    if (con == null) {
      con = DriverManager.getConnection(url, username, password);
      __connections.put(id, con);
    } else {
      out.println("Found cached connection"); //$NON-NLS-1$
    }

    return con;
  }

  /**
   * Retrieves all the table data required.
   */
  public void getTableData() {
    _tableNames = new ArrayList<String>();
    _ejbTableNames = new ArrayList<String>();
    _ejbWriteOnceTableNames = new ArrayList<String>();

    out.println("\nClasses being created for the following tables..."); //$NON-NLS-1$
    for (Iterator<String> tableItr = _meta.getTables(); tableItr.hasNext();) {
      String tableName = tableItr.next();

      // If we are using a table list
      // then check against that first.
      // If no list then we use everything.
      //
      if ((_tablesSet == null || _tablesSet.contains(tableName))
          && (_nonTablesSet == null || !_nonTablesSet.contains(tableName))) {
        _tableNames.add(tableName);
        out.println(tableName);
      }

      if (_ejbTablesSet == null || _ejbTablesSet.contains(tableName)) {
        _ejbTableNames.add(tableName);
        out.println("ejb add: " + tableName); //$NON-NLS-1$
      }

      if (_ejbWriteOnceTablesSet != null && _ejbWriteOnceTablesSet.contains(tableName)) {
        _ejbWriteOnceTableNames.add(tableName);
        out.println("ejb add: " + tableName); //$NON-NLS-1$
      }
    }
    out.println();
  }

  /**
   * Generates the value object interface for each table.
   * 
   * @throws SQLException problem with file access
   */
  public void generateValueObjectInterfaces() throws SQLException {
    try {
      for (String tableName : _tableNames) {
        String name = voInterfaceName(tableName) + ".java"; //$NON-NLS-1$
        File origFile = new File(_tablesDir, name);
        File abstrFile = new File(_tablesDir, ABSTR_PREFIX + name);
        File derivedFile = checkFile(origFile, _srcDir);
        File file;
        String iName = voInterfaceName(tableName);
        boolean useInheritance = false;
        if (derivedFile == origFile) {
          file = origFile;
        } else {
          file = abstrFile;
          useInheritance = true;
          iName = ABSTR_PREFIX + iName;
        }

        out.println("Generating Value Object Interface for : " + tableName + " to file " + file); //$NON-NLS-1$ //$NON-NLS-2$
        createOutput(file);
        writeValueObjectInterfaceHeader(_modelTypeIsLocal, tableName, iName);

        for (ColumnData columnData : getColumnData(tableName)) {
          writeProperty(_modelTypeIsLocal, columnData, true, false);
        }

        outln();
        out1("/**"); //$NON-NLS-1$
        out1("  * Copies the object via Object.clone()"); //$NON-NLS-1$
        out1("  *"); //$NON-NLS-1$
        out1("  * @return Copy of the model object"); //$NON-NLS-1$
        out1("  */"); //$NON-NLS-1$
        out1("public " + voInterfaceName(tableName) + " copy();"); //$NON-NLS-1$ //$NON-NLS-2$
        out("}"); //$NON-NLS-1$
        _currentOutput.close();

        if (useInheritance && !derivedFile.exists()) {
          createOutput(derivedFile);
          out.println("Generating extended Value Object Interface for : " + tableName + " to file " //$NON-NLS-1$ //$NON-NLS-2$
              + derivedFile);
          writeGen(null, __voInterfacePrefix, tableName, __voInterfaceSuffix, false);
          _currentOutput.close();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Generates Home Interfaces for Bean Objects
   * 
   * @throws SQLException Problem with database access
   */
  public void generateBeanHomes() throws SQLException {
    if (_local) {
      generateBeanHomes(true);
    }
    if (_remote) {
      generateBeanHomes(false);
    }
  }

  /**
   * Generates Home Interfaces for Bean Objects
   * 
   * @param local
   * @throws SQLException Problem with database access
   */
  public void generateBeanHomes(boolean local) throws SQLException {
    try {
      for (String tableName : _ejbTableNames) {
        String path = getPath(__ejbPackage);
        String condBeanHomeSuffix = local ? __beanLocalHomeSuffix : __beanHomeSuffix;
        String origClassName = firstUp(tableName) + condBeanHomeSuffix;
        String className;
        String name = origClassName + ".java"; //$NON-NLS-1$
        File origFile = new File(path, name);
        File abstrFile = new File(path, ABSTR_PREFIX + name);
        File derivedFile = checkFile(origFile, getSrcPath(__ejbPackage));
        File file;
        boolean useInheritance = false;
        if (derivedFile == origFile) {
          file = origFile;
          className = origClassName;
        } else {
          file = abstrFile;
          useInheritance = true;
          className = ABSTR_PREFIX + origClassName;
        }
        String condLocal = local ? "LocalHome" : "Home"; //$NON-NLS-1$ //$NON-NLS-2$
        out.println("Generating " + condLocal + " Interface for Bean: " + tableName + " to file " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            + file);
        createOutput(file);
        writeBeanHomeHeader(local, tableName, className);
        String iName = _prefix + origClassName;
        outln();
        out2("public static class HomeFinder {"); //$NON-NLS-1$
        out3("public static " + iName + " findHome(IVUBeanBase caller) {"); //$NON-NLS-1$ //$NON-NLS-2$
        out4("String simpleName = " + iName + ".class.getSimpleName();"); //$NON-NLS-1$ //$NON-NLS-2$
        out4("return caller.find" + condLocal //$NON-NLS-1$
            + "(simpleName.substring(0, simpleName.length() - 4));"); //$NON-NLS-1$
        out3("}"); //$NON-NLS-1$
        out2("}"); //$NON-NLS-1$
        writeBeanHomeBody(local, tableName);
        _currentOutput.close();

        if (useInheritance && !derivedFile.exists()) {
          createOutput(derivedFile);
          out.println("Generating extended " + condLocal + " Interface for Bean: " + tableName //$NON-NLS-1$ //$NON-NLS-2$
              + " to file " + derivedFile); //$NON-NLS-1$
          writeGen(__ejbPackage, "", tableName, condBeanHomeSuffix, false); //$NON-NLS-1$
          _currentOutput.close();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Generates the Bean Object
   * 
   * @throws SQLException Problem with database access
   */
  public void generateJawFinder() throws SQLException {
    Jawsxml jaws = new Jawsxml(_jawFile);
    for (String tableName : _ejbTableNames) {
      out.println("Generating Jaws Finder for: " + tableName); //$NON-NLS-1$
      String finderxml = ""; //$NON-NLS-1$

      for (FKDefinition def : getTableImportedKeys(tableName)) {
        finderxml = ""; //$NON-NLS-1$
        String finderName = "find" + firstUp(tableName) + "By" //$NON-NLS-1$ //$NON-NLS-2$
            + firstUp(generateRelatedPropName(tableName, def, false));
        boolean exist = jaws.existFinder(firstUp(tableName), finderName);
        if (exist) {
          finderxml += "<!-- \n"; //$NON-NLS-1$
        }
        finderxml += getFinderMethods(firstUp(tableName), finderName, def, false);
        if (exist) {
          finderxml += " -->\n"; //$NON-NLS-1$
        }
        jaws.addFinder(firstUp(tableName), finderxml);
      }
      List<FKDefinition> masterForeignTabs = getTableExportedKeys(tableName);
      for (FKDefinition def : masterForeignTabs) {
        finderxml = ""; //$NON-NLS-1$
        String finderName = "find" + firstUp(tableName) + "By" //$NON-NLS-1$ //$NON-NLS-2$
            + firstUp(generateRelatedPropName(tableName, def, false));
        boolean exist = jaws.existFinder(firstUp(tableName), finderName);
        if (exist) {
          finderxml += "<!-- \n"; //$NON-NLS-1$
        }
        finderxml += getFinderMethods(firstUp(tableName), finderName, def, true);
        if (exist) {
          finderxml += " -->\n"; //$NON-NLS-1$
        }
        jaws.addFinder(firstUp(tableName), finderxml.toString());
      }
    }
    jaws.write();
  }

  /**
   * Generates the Bean Object
   * 
   * @throws SQLException Problem with database access
   */
  public void generateBeans() throws SQLException {
    try {
      for (String tableName : _ejbTableNames) {
        String path = getPath(__ejbPackage);
        String className = firstUp(tableName) + __beanClassSuffix;
        String name = className + ".java"; //$NON-NLS-1$
        File origFile = new File(path, name);
        File abstrFile = new File(path, ABSTR_PREFIX + name);
        File derivedFile = checkFile(origFile, getSrcPath(__ejbPackage));
        File file;
        boolean useInheritance = false;
        if (derivedFile == origFile) {
          file = origFile;
        } else {
          file = abstrFile;
          useInheritance = true;
          className = ABSTR_PREFIX + className;
        }

        out.println("Generating Bean: " + tableName + " to file " + file); //$NON-NLS-1$ //$NON-NLS-2$
        createOutput(file);

        writeBeanHeader(tableName, className, useInheritance);
        writeEJBCommon(true, tableName, false, useInheritance);

        // handle the properties with thier access functions (set/get)
        //
        getTableImportedKeys(tableName);
        for (ColumnData columnData : getColumnData(tableName)) {
          writeProperty(false, columnData, false, true);
        }

        if (_foreignKeys) {
          Set<String> propertySet = new HashSet<String>();
          for (FKDefinition def : getTableImportedKeys(tableName)) {
            if (_local) {
              propertySet.add(writeImportedMethods(true, tableName, def, false));
            }
            if (_remote) {
              propertySet.add(writeImportedMethods(false, tableName, def, false));
            }
          }

          for (FKDefinition def : getTableExportedKeys(tableName)) {
            if (_local) {
              writeExportedMethods(true, tableName, def, false, propertySet);
            }
            if (_remote) {
              writeExportedMethods(false, tableName, def, false, propertySet);
            }
          }
        }

        writeTableToString(tableName, true);

        out("}"); //$NON-NLS-1$
        _currentOutput.close();

        if (useInheritance && !derivedFile.exists()) {
          createOutput(derivedFile);
          out.println("Generating extended Bean: " + tableName + " to file " + derivedFile); //$NON-NLS-1$ //$NON-NLS-2$
          writeGen(__ejbPackage, "", tableName, __beanClassSuffix, true); //$NON-NLS-1$
          _currentOutput.close();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void writePropertyFinders(boolean local,
      String tableName,
      boolean isAbstract,
      String retClass) throws SQLException, IOException {

    String collection = "Collection<" + retClass + ">"; //$NON-NLS-1$ //$NON-NLS-2$
    String finderPfx = (isAbstract ? (collection + " find") : ("public " + collection + " ejbFind")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + "All"; //$NON-NLS-1$
    String finderException = isAbstract ? "FinderException" : "IVUFinderException"; //$NON-NLS-1$ //$NON-NLS-2$
    String finderSfx = ") throws " + finderException + (local ? "" : ", RemoteException") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + (isAbstract ? ";" : " {"); //$NON-NLS-1$ //$NON-NLS-2$
    for (ColumnData cd : getColumnData(tableName)) {
      String propName = cd.getName();
      if (!cd.isFK() && !cd.isPK()) {
        String propType = cd.getJavaType();
        String fuPropName = firstUp(propName);
        String typeName = firstUp(tableName);
        propName = propertyName(propName);
        List<String> fields = new ArrayList<String>(1);
        fields.add(propName);
        writePropertyFinder(local,
            isAbstract,
            finderPfx,
            finderSfx,
            propType,
            propName,
            fuPropName,
            fields,
            typeName,
            "By"); //$NON-NLS-1$

        if (propType.equals("String")) { //$NON-NLS-1$
          writePropertyFinder(local,
              isAbstract,
              finderPfx,
              finderSfx,
              propType,
              propName,
              fuPropName,
              fields,
              typeName,
              "Like"); //$NON-NLS-1$
        }
      }
    }
  }

  private void writePropertyFinder(boolean local,
      boolean isAbstract,
      String finderPfx,
      String finderSfx,
      String propType,
      String propName,
      String fuPropName,
      List<String> fields,
      String typeName,
      String how) throws IOException {

    String dbaClassName = typeName + __dbaClassSuffix;
    String finderException = isAbstract ? "FinderException" : "IVUFinderException"; //$NON-NLS-1$ //$NON-NLS-2$
    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Returns the set of entities of the type {@link " + typeName + "}, filtered by " //$NON-NLS-1$ //$NON-NLS-2$
        + propName + "."); //$NON-NLS-1$
    out1("  *"); //$NON-NLS-1$
    writeJavadocParams(fields);
    String objectType = local ? "EJBLocalObject" : "EJBObject"; //$NON-NLS-1$ //$NON-NLS-2$
    String what = isAbstract
        ? ("{@link " + objectType + "}s of the type" + " {@link " + typeName + "}") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        : ("primary keys of the entities " + typeName); //$NON-NLS-1$
    out1("  * @return  {@link Collection} of " + what); //$NON-NLS-1$
    out1("  * @throws " + finderException //$NON-NLS-1$
        + " if an error occurred while searching (does NOT mean \"not found\")."); //$NON-NLS-1$
    writeJDException(!local, false);
    out1("  */"); //$NON-NLS-1$
    out1(finderPfx + how + fuPropName + '(' + propType + ' ' + propName + finderSfx);
    if (!isAbstract) {
      out2("try {"); //$NON-NLS-1$
      out3("return " + dbaClassName + ".retrieveIDs" + how + fuPropName + "(" + propName + ");"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
      out2("} catch (SQLException se) {"); //$NON-NLS-1$
      out3("throw new IVUFinderException(se.getMessage(), se);"); //$NON-NLS-1$
      out2("}"); //$NON-NLS-1$
      out1("}"); //$NON-NLS-1$
    }
  }

  /**
   * Generates the Remote Interface for the Bean Object
   * 
   * @throws SQLException Problem with database access
   */
  public void generateBeanInterfaces() throws SQLException {
    if (_local) {
      generateBeanInterfaces(true);
    }
    if (_remote) {
      generateBeanInterfaces(false);
    }
  }

  /**
   * Generates remote interfaces for all beans
   * 
   * @param local <code>true</code> for EJBLocalObject-Interfaces
   * @throws SQLException Problem with database access
   */
  public void generateBeanInterfaces(boolean local) throws SQLException {
    try {
      for (String tableName : _ejbTableNames) {
        String path = getPath(__ejbPackage);
        String condBeanSuffix = local ? __beanLocalSuffix : __beanRemoteSuffix;
        String className = firstUp(tableName) + condBeanSuffix;
        String name = className + ".java"; //$NON-NLS-1$
        File origFile = new File(path, name);
        File abstrFile = new File(path, ABSTR_PREFIX + name);
        File derivedFile = checkFile(origFile, getSrcPath(__ejbPackage));
        File file;
        boolean useInheritance = false;
        if (derivedFile == origFile) {
          file = origFile;
        } else {
          file = abstrFile;
          useInheritance = true;
          className = ABSTR_PREFIX + className;
        }
        String condLocal = local ? "Local" : "Remote"; //$NON-NLS-1$ //$NON-NLS-2$
        out.println("Generating " + condLocal + " Interface for Bean: " + tableName + " to file " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            + file);
        createOutput(file);
        writeBeanInterface(local, tableName, className);
        _currentOutput.close();

        if (useInheritance && !derivedFile.exists()) {
          createOutput(derivedFile);
          out.println("Generating extended " + condLocal + " Interface for Bean: " + tableName //$NON-NLS-1$ //$NON-NLS-2$
              + " to file " + derivedFile); //$NON-NLS-1$
          writeGen(__ejbPackage, "", tableName, condBeanSuffix, false); //$NON-NLS-1$
          _currentOutput.close();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Generates EJB descriptor fragments for all beans
   */
  public void generateEJBDescriptorFragment() {
    try {
      File file = new File(_tablesDir, "ejb-jar.fragment.xml"); //$NON-NLS-1$
      out.println("Generating ejb-jar.xml fragment to file " + file); //$NON-NLS-1$
      createOutput(file);
      out1("<enterprise-beans>"); //$NON-NLS-1$
      for (String ejbTableName : _ejbTableNames) {
        writeEJBDescriptorFragment(ejbTableName);
      }
      out1("</enterprise-beans>"); //$NON-NLS-1$
      outln();
      outln();
      out1("<assembly-descriptor>"); //$NON-NLS-1$
      for (String ejbTableName : _ejbTableNames) {
        writeEJBAssemblyDescriptorFragment(ejbTableName);
      }
      out1("</assembly-descriptor>"); //$NON-NLS-1$
      _currentOutput.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Generates EJB descriptor fragments for all bean references (to be used when assembling session
   * beans)
   */
  public void generateEJBDescriptorRefFragment() {
    try {
      File file = new File(_tablesDir, "ejb-jar.ref.fragment.xml"); //$NON-NLS-1$
      out.println("Generating ejb-jar.xml reference fragment to file " + file); //$NON-NLS-1$
      createOutput(file);
      if (_remote) {
        for (String ejbTableName : _ejbTableNames) {
          writeEJBRef(false, true, ejbTableName);
        }
      }
      if (_local) {
        for (String ejbTableName : _ejbTableNames) {
          writeEJBRef(true, true, ejbTableName);
        }
      }
      _currentOutput.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Generates Web descriptor fragments for all bean references
   */
  public void generateWebDescriptorRefFragment() {
    try {
      File file = new File(_tablesDir, "web.ref.fragment.xml"); //$NON-NLS-1$
      out.println("Generating web.xml reference fragment to file " + file); //$NON-NLS-1$
      createOutput(file);
      if (_remote) {
        for (String ejbTableName : _ejbTableNames) {
          writeEJBRef(false, false, ejbTableName);
        }
      }
      _currentOutput.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Generates JBoss descriptor fragments for all beans
   */
  public void generateJBossDescriptorFragment() {
    try {
      File file = new File(_tablesDir, "jboss.fragment.xml"); //$NON-NLS-1$
      out.println("Generating jboss.xml fragment to file " + file); //$NON-NLS-1$
      createOutput(file);
      if (_remote) {
        for (String ejbTableName : _ejbTableNames) {
          writeJBossDescriptorFragment(ejbTableName);
        }
      }
      _currentOutput.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Generates JBoss Web descriptor fragments for all beans
   */
  public void generateJBossWebDescriptorFragment() {
    try {
      File file = new File(_tablesDir, "jboss-web.fragment.xml"); //$NON-NLS-1$
      out.println("Generating jboss-web.xml fragment to file " + file); //$NON-NLS-1$
      createOutput(file);
      if (_remote) {
        for (String ejbTableName : _ejbTableNames) {
          writeJBossRef(ejbTableName);
        }
      }
      _currentOutput.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Generates Tomcat server.xml descriptor fragments for all beans
   */
  public void generateTomcatServerDescriptorFragment() {
    try {
      File file = new File(_tablesDir, "server.fragment.xml"); //$NON-NLS-1$
      out.println("Generating Tomcat server.xml fragment to file " + file); //$NON-NLS-1$
      createOutput(file);
      if (_remote) {
        for (String ejbTableName : _ejbTableNames) {
          writeTomcatRef(ejbTableName);
        }
      }
      _currentOutput.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void createOutput(File file) throws IOException {
    _cleanSet.remove(file);
    File parentFile = file.getParentFile();
    _cleanSet.remove(parentFile);
    parentFile.mkdirs();
    _currentOutput = new ComparingWriter(file);
  }

  static char[] __buffer = new char[0x10000];

  static void copy(Reader r, Writer w) throws IOException {
    int amount = r.read(__buffer);
    while (amount >= 0) {
      if (amount > 0) {
        w.write(__buffer, 0, amount);
      }
      amount = r.read(__buffer);
    }
  }

  /**
   * Intelligent writer, which writes a file to the hard disk only, if there is no file of the same
   * name AND content. This prevents the new translation of the file, if it has not changed.
   * 
   * @author cos@ivu.de, IVU Traffic Technologies AG
   */
  private class ComparingWriter extends Writer {

    private static final String FILE_ENCODING = "file.encoding"; //$NON-NLS-1$
    File _file;
    StringWriter _sWriter;
    Writer _out;

    /**
     * @param file
     * @throws IOException
     */
    public ComparingWriter(File file) throws IOException {
      super();
      _file = file;
      if (file.exists() && file.isFile()) {
        // deferred mode
        _out = _sWriter = new StringWriter();
      } else {
        _out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),
            getProperty(FILE_ENCODING)));
      }
    }

    @Override
    public void close() throws IOException {
      _out.close();
      if (_sWriter != null) {
        Reader fr = new InputStreamReader(new FileInputStream(_file), getProperty(FILE_ENCODING));
        StringWriter orig = new StringWriter();
        copy(fr, orig);
        fr.close();
        orig.close();

        if (_sWriter.toString().equals(orig.toString())) {
          out.println("File did not change"); //$NON-NLS-1$
        } else {
          _out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(_file),
              getProperty(FILE_ENCODING)));
          _out.write(_sWriter.toString());
          _out.close();
        }
      }
    }

    @Override
    public void flush() throws IOException {
      _out.flush();
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
      _out.write(cbuf, off, len);
    }
  }

  /**
   * Generates the value object class for each table.
   * 
   * @throws SQLException Problem with database access
   */
  public void generateValueObjects() throws SQLException {
    try {
      for (Iterator<String> i = _tableNames.iterator(); i.hasNext();) {
        String tableName = i.next();
        String path = getPath(__implPackage);
        String className = voImplName(tableName);
        String name = className + ".java"; //$NON-NLS-1$
        File origFile = new File(path, name);
        File abstrFile = new File(path, ABSTR_PREFIX + name);
        File derivedFile = checkFile(origFile, getSrcPath(__implPackage));
        File file;
        boolean useInheritance = false;
        if (derivedFile == origFile) {
          file = origFile;
        } else {
          file = abstrFile;
          useInheritance = true;
          className = ABSTR_PREFIX + className;
        }

        out.println("Generating Value Object : " + tableName + " to file " + file); //$NON-NLS-1$ //$NON-NLS-2$
        createOutput(file);
        writeValueObjectHeader(tableName, className, useInheritance);
        if (_keys) {
          List<String> keyData = getTableKeys(tableName);
          // variable
          if (keyData.size() > 0) {
            if (!isJoinTable(tableName, getTableImportedKeys(tableName))) {
              writeBeanSupport(keyData);
            }
          }
        }

        // handle the properties with their access functions (set/get)
        //
        String localVarName = "other"; //$NON-NLS-1$
        String eqConst = ".equals(" + localVarName + '.'; //$NON-NLS-1$
        StringBuilder hashBuffer = new StringBuilder();
        StringBuilder equalsBuffer = new StringBuilder();
        for (ColumnData columnData : getColumnData(tableName)) {
          writeProperty(false, columnData, false, false);
          if (columnData.isPK()) {
            String privatePropName = '_' + propertyName(columnData.getName());
            if (hashBuffer.length() > 0) {
              hashBuffer.append(" ^ "); //$NON-NLS-1$
              equalsBuffer.append(" && "); //$NON-NLS-1$
            }
            hashBuffer.append(privatePropName).append(".hashCode()"); //$NON-NLS-1$
            equalsBuffer.append(privatePropName).append(eqConst).append(privatePropName)
                .append(')');
          }
        }

        outln();
        out1("/**"); //$NON-NLS-1$
        out1("  * Overwrites the hashCode method in Object"); //$NON-NLS-1$
        out1("  *"); //$NON-NLS-1$
        out1("  * @return hash code, calculated as XOR-composition of the hash codes of the elements of the primary keys"); //$NON-NLS-1$
        out1("  */"); //$NON-NLS-1$
        out1("@Override"); //$NON-NLS-1$
        out1("public int hashCode() {"); //$NON-NLS-1$
        out2("return " + hashBuffer + ";"); //$NON-NLS-1$ //$NON-NLS-2$
        out1("}"); //$NON-NLS-1$

        outln();
        out1("/**"); //$NON-NLS-1$
        out1("  * Overwrites the equals method in Object"); //$NON-NLS-1$
        out1("  *"); //$NON-NLS-1$
        out1("  * @param object Objekt, by what this object can be testet for equality"); //$NON-NLS-1$
        out1("  * @return <code>true</code> if the compared objects have an equal primary key"); //$NON-NLS-1$
        out1("  */"); //$NON-NLS-1$
        out1("@Override"); //$NON-NLS-1$
        out1("public boolean equals(Object object) {"); //$NON-NLS-1$
        out2("if (object instanceof " + className + ") {"); //$NON-NLS-1$ //$NON-NLS-2$
        out3(className + " " + localVarName + " = (" + className + ")object;"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        out3("return " + equalsBuffer + ";"); //$NON-NLS-1$ //$NON-NLS-2$
        out2("} else {"); //$NON-NLS-1$
        out3("return false;"); //$NON-NLS-1$
        out2("}"); //$NON-NLS-1$
        out1("}"); //$NON-NLS-1$

        writeTableToString(tableName, false);
        outln();
        writeVOCopy(voInterfaceName(tableName));
        out("}"); //$NON-NLS-1$
        _currentOutput.close();

        if (useInheritance && !derivedFile.exists()) {
          createOutput(derivedFile);
          out.println("Generating extended Value Object : " + tableName + " to file " //$NON-NLS-1$ //$NON-NLS-2$
              + derivedFile);
          writeGen(__implPackage, "", tableName, __voClassSuffix, true); //$NON-NLS-1$
          _currentOutput.close();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Generates the database access functions for each table.
   * 
   * @throws SQLException Problem with database access
   */
  public void generateDBAClasses() throws SQLException {
    try {
      for (String tableName : _tableNames) {
        String path = getPath(__implPackage);
        String className = firstUp(tableName) + __dbaClassSuffix;
        String name = className + ".java"; //$NON-NLS-1$
        File origFile = new File(path, name);
        File abstrFile = new File(path, ABSTR_PREFIX + name);
        File derivedFile = checkFile(origFile, getSrcPath(__implPackage));
        File file;
        boolean useInheritance = false;
        if (derivedFile == origFile) {
          file = origFile;
        } else {
          file = abstrFile;
          useInheritance = true;
          className = ABSTR_PREFIX + className;
        }

        out.println("Generating database access class for : " + tableName + " to file " + file); //$NON-NLS-1$ //$NON-NLS-2$
        createOutput(file);
        getColumnData(tableName);
        writeDBAHeader(tableName, className, useInheritance);

        // do the retrieve,insert and update functions
        // We have pair of updates and retrieve, depending
        // upon whether keys and/or indexes are present on
        // the database table.
        //
        // If no keys/indexes are present, then you'll just have to
        // write your own retrieve and update functions.
        //
        writeGetFromResultSet(tableName);
        writePutIntoPreparedStatement(tableName);
        writeConvertModelToStringArray(tableName);

        writeCount(tableName, null, false, "count"); //$NON-NLS-1$

        if (_keys) {
          List<String> keyData = getTableKeys(tableName);
          // variable
          if (keyData.size() > 0) {
            writeCount(tableName, keyData, false, "countByKey"); //$NON-NLS-1$
            writeRetrieve(tableName, keyData, "retrieveByKey"); //$NON-NLS-1$
            writeRetrieveAllIDs(tableName, "retrieveAllIDs"); //$NON-NLS-1$
            writeDelete(tableName, keyData, "deleteByKey"); //$NON-NLS-1$
            if (-1 == tableName.indexOf("_")) { //$NON-NLS-1$
              writeUpdate(tableName, keyData, "updateByKey"); //$NON-NLS-1$
            }
          }
        }

        writeInsert(tableName);

        if (_foreignKeys) {
          List<FKDefinition> foreignKeyData = getTableImportedKeys(tableName);
          if (isJoinTable(tableName, foreignKeyData)) {
            FKDefinition fk0 = foreignKeyData.get(0);
            FKDefinition fk1 = foreignKeyData.get(1);
            // writeJoinTableAcc(tableName, fk0, fk1);
            // writeJoinTableAcc(tableName, fk1, fk0);
            writeJoinTableIDsAcc(tableName, fk0, fk1);
            writeJoinTableIDsAcc(tableName, fk1, fk0);
          } else {
            for (Iterator<FKDefinition> fkItr = foreignKeyData.iterator(); fkItr.hasNext();) {
              FKDefinition def = fkItr.next();
              String fkName = def.getFKName();
              List<String> fKFields = def.getFKFields();
              // writeRetrieveAll(tableName, fKFields, false,
              // "retrieveBy" + fkName, false);
              writeRetrieveAllIDs(tableName, fKFields, "retrieveIDsBy" + fkName, false); //$NON-NLS-1$
            }
          }
        }

        if (_indexes) {
          // updates the indexData variable
          List<IndexInfo> indexData = getTableIndexes(tableName);

          if (indexData.size() > 0) {
            List<String> indexNames = new ArrayList<String>();
            copy(indexNames, indexData, new Converter<IndexInfo, String>() {
              public String convert(IndexInfo u) {
                return u.toString();
              }
            });

            writeRetrieve(tableName, indexNames, "retrieveByIndex"); //$NON-NLS-1$
            writeRetrieveAll(tableName, indexNames, false, "retrieveAllLikeIndex", true); //$NON-NLS-1$
            writeUpdate(tableName, indexNames, "updateByIndex"); //$NON-NLS-1$
            writeDelete(tableName, indexNames, "deleteByIndex"); //$NON-NLS-1$
            writeCount(tableName, indexNames, false, "countByIndex"); //$NON-NLS-1$
            writeCount(tableName, indexNames, true, "countLikeIndex"); //$NON-NLS-1$
          }
        }

        for (ColumnData cd : getColumnData(tableName)) {
          if (!cd.isFK() && !cd.isPK()) {
            List<String> fields = new ArrayList<String>(1);
            fields.add(cd.getName());
            writeRetrieveAllIDs(tableName, fields, "retrieveIDsBy" + firstUp(cd.getName()), false); //$NON-NLS-1$
            if (cd.getJavaType().equals("String")) { //$NON-NLS-1$
              writeRetrieveAllIDs(tableName, fields, "retrieveIDsLike" + firstUp(cd.getName()), //$NON-NLS-1$
                  true);
            }
          }
        }

        out("}"); //$NON-NLS-1$
        _currentOutput.close();

        if (useInheritance && !derivedFile.exists()) {
          createOutput(derivedFile);
          out.println("Generating extended database access class for : " + tableName + " to file " //$NON-NLS-1$ //$NON-NLS-2$
              + derivedFile);
          writeGen(__implPackage, "", tableName, __dbaClassSuffix, true); //$NON-NLS-1$
          _currentOutput.close();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean isJoinTable(String tableName, List<FKDefinition> fkData) throws SQLException {
    if (fkData.size() != 2) {
      // System.out.print ("isJoinTable for "+tableName+" has more or less
      // than 2 fk definionts, so no jointable.");
      return false;
    }
    for (ColumnData cd : getColumnData(tableName)) {
      if (!cd.isPK()) {
        // System.out.println (" not a key, so no jointable");
        return false;
      }
    }
    return true;
  }

  /**
   * Writes the header for a Value Object Interface.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeValueObjectInterfaceHeader(boolean local, String tableName, String iName)
      throws SQLException, IOException {

    writeCommonHeader(iName);
    out("package " + _packageName + ";"); //$NON-NLS-1$ //$NON-NLS-2$
    outln();
    writeCondDateTime(tableName);
    if (!local) {
      out("import java.rmi.RemoteException;"); //$NON-NLS-1$
      outln();
    }
    out("import " + __voRootInterface + ";"); //$NON-NLS-1$ //$NON-NLS-2$

    for (int i = 0; i < _additionalImports.size(); i++) {
      out("import " + _additionalImports.get(i) + ".*;"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    outln();
    out("/**"); //$NON-NLS-1$
    out("  * Model interface for the entity " + tableName + "."); //$NON-NLS-1$//$NON-NLS-2$
    out("  * Contains the list of properties including the IDs of the associated objects."); //$NON-NLS-1$
    out("  * A navigation is not implemented (1:1, 1:n, m:n)"); //$NON-NLS-1$
    writeAuthorHeader();
    if (EMF_MODEL_ANNOTATIONS) {
      out("  * @model"); //$NON-NLS-1$
    }
    out("  */"); //$NON-NLS-1$
    out("public interface " + iName + " extends " + lastPart(__voRootInterface) + " {"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  private String lastPart(String fullClassName) {
    return fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
  }

  private void writeAuthorHeader() throws IOException {
    out("  *"); //$NON-NLS-1$
    out("  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG"); //$NON-NLS-1$
    if (_defVersion != null) {
      out("  * @version " + _defVersion); //$NON-NLS-1$
    } else {
      out("  * @version $Id: TableGen.java,v 1.19 2010/12/21 08:31:50 jon Exp $"); //$NON-NLS-1$
    }
  }

  /**
   * Generates the bean home interface body for a table.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeBeanHomeBody(boolean local, String tableName)
      throws IOException, SQLException {

    writeEJBCommon(local, tableName, true, false);
    out("}"); //$NON-NLS-1$
  }

  /**
   * Generates the bean home interface header for a table.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeBeanHomeHeader(boolean local, String tableName, String className)
      throws SQLException, IOException {

    writeCommonHeader(className);
    out("package " + _packageName + "." + __ejbPackage + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    outln();
    boolean importCollection = (_foreignKeys && (!getTableExportedKeys(tableName).isEmpty() || !getTableImportedKeys(tableName)
        .isEmpty()))
        || getColumnData(tableName).size() > getTableKeys(tableName).size();

    if (importCollection) {
      out("import java.util.Collection;"); //$NON-NLS-1$
    }
    writeCondDateTime(tableName);
    if (!local) {
      out("import java.rmi.RemoteException;"); //$NON-NLS-1$
    }
    if (local) {
      out("import javax.ejb.EJBLocalHome;"); //$NON-NLS-1$
    } else {
      out("import javax.ejb.EJBHome;"); //$NON-NLS-1$
    }
    out("import javax.ejb.CreateException;"); //$NON-NLS-1$
    out("import javax.ejb.EJBException;"); //$NON-NLS-1$
    out("import javax.ejb.FinderException;"); //$NON-NLS-1$
    out("import javax.ejb.ObjectNotFoundException;"); //$NON-NLS-1$
    outln();
    out("import de.ivu.ejb.IVUBeanBase;"); //$NON-NLS-1$
    outln();
    String voInterfaceName = voInterfaceName(tableName);
    out("import " + _packageName + "." + voInterfaceName + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    outln();
    String condLocal = (local ? "Local" : ""); //$NON-NLS-1$ //$NON-NLS-2$
    out("/**"); //$NON-NLS-1$
    out("  * " + condLocal + "Home interface for the entity " + tableName + " as BMP Entity Bean."); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
    writeAuthorHeader();
    out("  */"); //$NON-NLS-1$
    out("public interface " + _prefix + className + " extends EJB" + condLocal + "Home {"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  /**
   * Generates the EJB interface functions for a table.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeBeanInterface(boolean local, String tableName, String className)
      throws IOException, SQLException {

    String iName = voInterfaceName(tableName);
    writeCommonHeader(className);
    out("package " + _packageName + "." + __ejbPackage + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    outln();
    getColumnData(tableName);
    if (_modelTypeIsLocal != local) {
      writeCondDateTime(tableName);
    }
    boolean hasExports = _foreignKeys && !getTableExportedKeys(tableName).isEmpty();
    boolean hasImports = _foreignKeys && !getTableImportedKeys(tableName).isEmpty();
    boolean hasRelations = hasExports || hasImports;
    if (hasExports) {
      out("import java.util.Collection;"); //$NON-NLS-1$
    }
    if (hasRelations) {
      outln();
      out("import javax.ejb.EJBException;"); //$NON-NLS-1$
    }
    if (local) {
      out("import javax.ejb.EJBLocalObject;"); //$NON-NLS-1$
    } else {
      out("import javax.ejb.EJBObject;"); //$NON-NLS-1$
    }
    if (!local || (hasRelations && !_modelTypeIsLocal)) {
      out("import java.rmi.RemoteException;"); //$NON-NLS-1$
    }
    outln();
    out("import " + _packageName + "." + iName + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    outln();
    writeBeanJavadoc(tableName, true);
    StringBuilder methodDeclaration = new StringBuilder("public interface "); //$NON-NLS-1$
    methodDeclaration.append(_prefix).append(className);
    methodDeclaration.append(" extends "); //$NON-NLS-1$
    methodDeclaration.append(local ? "EJBLocalObject" : "EJBObject"); //$NON-NLS-1$ //$NON-NLS-2$
    methodDeclaration.append(_modelTypeIsLocal == local ? (", " + iName) : ""); //$NON-NLS-1$ //$NON-NLS-2$
    methodDeclaration.append(" {"); //$NON-NLS-1$
    out(methodDeclaration.toString());
    out1("/**"); //$NON-NLS-1$
    out1("  * Method to receive a model object for the setting of the current internal object state."); //$NON-NLS-1$
    out1("  * Optimizes the Client-Server-Performance"); //$NON-NLS-1$
    out1("  *"); //$NON-NLS-1$
    out1("  * @param details model object with new state"); //$NON-NLS-1$
    writeJDException(!local, false);
    out1("  */"); //$NON-NLS-1$
    out1(createVoidMethodLine("setDetails", iName + " details", true, true, !local, false)); //$NON-NLS-1$ //$NON-NLS-2$
    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Method to receive a model object, which represents the current internal state of the object."); //$NON-NLS-1$
    out1("  * Optimizes the Client-Server-Performance"); //$NON-NLS-1$
    out1("  *"); //$NON-NLS-1$
    out1("  * @return Model object with new internal state"); //$NON-NLS-1$
    writeJDException(!local, false);
    out1("  */"); //$NON-NLS-1$
    out1(createGetterMethodLine("getDetails", iName, true, !local, false)); //$NON-NLS-1$
    outln();

    if (_modelTypeIsLocal != local) {
      for (ColumnData columnData : getColumnData(tableName)) {
        writeProperty(local, columnData, true, false);
      }
    }

    if (_foreignKeys) {
      Set<String> propertySet = new HashSet<String>();
      for (FKDefinition definition : getTableImportedKeys(tableName)) {
        propertySet.add(writeImportedMethods(local, tableName, definition, true));
      }

      for (FKDefinition definition : getTableExportedKeys(tableName)) {
        writeExportedMethods(local, tableName, definition, true, propertySet);
      }
    }

    out("}"); //$NON-NLS-1$
  }

  /**
   * Generates the EJB descriptor fragment for a table.
   */
  protected void writeEJBDescriptorFragment(String tableName) throws IOException {

    String typeBaseName = _prefix + tableName;
    String typeFullBaseName = _packageName + "." + __ejbPackage + "." + typeBaseName; //$NON-NLS-1$ //$NON-NLS-2$
    outln();
    out2("<entity>"); //$NON-NLS-1$
    out3("<ejb-name>" + typeBaseName + "</ejb-name>"); //$NON-NLS-1$ //$NON-NLS-2$
    if (_remote) {
      out3("<home>" + typeFullBaseName + __beanHomeSuffix + "</home>"); //$NON-NLS-1$ //$NON-NLS-2$
      out3("<remote>" + typeFullBaseName + __beanRemoteSuffix + "</remote>"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    if (_local) {
      out3("<local-home>" + typeFullBaseName + __beanLocalHomeSuffix + "</local-home>"); //$NON-NLS-1$ //$NON-NLS-2$
      out3("<local>" + typeFullBaseName + __beanLocalSuffix + "</local>"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    out3("<ejb-class>" + typeFullBaseName + __beanClassSuffix + "</ejb-class>"); //$NON-NLS-1$ //$NON-NLS-2$
    out3("<persistence-type>Bean</persistence-type>"); //$NON-NLS-1$
    out3("<prim-key-class>java.lang.String</prim-key-class>"); //$NON-NLS-1$
    out3("<reentrant>False</reentrant>"); //$NON-NLS-1$
    if (_foreignKeys) {
      Set<String> foreignEntities = new TreeSet<String>();
      for (FKDefinition definition : getTableExportedKeys(tableName)) {
        foreignEntities.add(generateRelatedPropType(tableName, definition));
      }
      for (FKDefinition definition : getTableImportedKeys(tableName)) {
        foreignEntities.add(firstUp(definition.getPKTableName()));
      }
      if (_remote) {
        for (String foreignEntity : foreignEntities) {
          writeEJBRef(false, true, foreignEntity);
        }
      }
      if (_local) {
        for (String foreignEntity : foreignEntities) {
          writeEJBRef(true, true, foreignEntity);
        }
      }
    }
    out3("<resource-ref>"); //$NON-NLS-1$
    out4("<res-ref-name>" + __datasourceRefName + "</res-ref-name>"); //$NON-NLS-1$ //$NON-NLS-2$
    out4("<res-type>" + __datasourceType + "</res-type>"); //$NON-NLS-1$ //$NON-NLS-2$
    out4("<res-auth>Container</res-auth>"); //$NON-NLS-1$
    out3("</resource-ref>"); //$NON-NLS-1$
    out2("</entity>"); //$NON-NLS-1$
  }

  /**
   * Generates the EJB descriptor fragment for a table.
   */
  protected void writeEJBAssemblyDescriptorFragment(String tableName) throws IOException {

    String typeBaseName = _prefix + tableName;
    outln();
    out2("<container-transaction>"); //$NON-NLS-1$
    if (_remote) {
      out3("<method>"); //$NON-NLS-1$
      out4("<ejb-name>" + typeBaseName + "</ejb-name>"); //$NON-NLS-1$ //$NON-NLS-2$
      out4("<method-intf>Remote</method-intf>"); //$NON-NLS-1$
      out4("<method-name>*</method-name>"); //$NON-NLS-1$
      out3("</method>"); //$NON-NLS-1$
    }
    if (_local) {
      out3("<method>"); //$NON-NLS-1$
      out4("<ejb-name>" + typeBaseName + "</ejb-name>"); //$NON-NLS-1$ //$NON-NLS-2$
      out4("<method-intf>Local</method-intf>"); //$NON-NLS-1$
      out4("<method-name>*</method-name>"); //$NON-NLS-1$
      out3("</method>"); //$NON-NLS-1$
    }
    out3("<trans-attribute>Required</trans-attribute>"); //$NON-NLS-1$
    out2("</container-transaction>"); //$NON-NLS-1$
  }

  /**
   * Generates the EJB descriptor fragment for a table.
   */
  protected void writeJBossDescriptorFragment(String tableName) throws IOException {

    String typeBaseName = _prefix + tableName;
    outln();
    out2("<entity>"); //$NON-NLS-1$
    out3("<ejb-name>" + typeBaseName + "</ejb-name>"); //$NON-NLS-1$ //$NON-NLS-2$
    // configuration-name?
    if (_remote) {
      out3("<jndi-name>" + __jbossJNDIPrefix + typeBaseName + __beanRemoteSuffix + "</jndi-name>"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    if (_local) {
      out3("<local-jndi-name>" + __jbossJNDIPrefix + typeBaseName + __beanLocalSuffix //$NON-NLS-1$
          + "</local-jndi-name>"); //$NON-NLS-1$
    }
    if (false && _foreignKeys) {
      Set<String> foreignEntities = new TreeSet<String>();
      for (FKDefinition definition : getTableExportedKeys(tableName)) {
        foreignEntities.add(generateRelatedPropType(tableName, definition));
      }
      for (FKDefinition definition : getTableImportedKeys(tableName)) {
        foreignEntities.add(firstUp(definition.getPKTableName()));
      }
      for (String foreignEntity : foreignEntities) {
        writeJBossRef(foreignEntity);
      }
    }
    out3("<configuration-name>" + __jbossConfigurationName + "</configuration-name>"); //$NON-NLS-1$ //$NON-NLS-2$
    out2("</entity>"); //$NON-NLS-1$
  }

  /**
   * Generates the value object header for each table.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeValueObjectHeader(String tableName, String className, boolean isAbstract)
      throws SQLException, IOException {
    String condAbstract = isAbstract ? "abstract " : ""; //$NON-NLS-1$ //$NON-NLS-2$
    boolean isJoinTable = isJoinTable(tableName, getTableImportedKeys(tableName));
    String modelImplRootClass = isJoinTable ? __mnVoImplRootClass : __voImplRootClass;
    String voClassName = lastPart(modelImplRootClass);
    writeCommonHeader(className);
    out("package " + _packageName + "." + __implPackage + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    outln();
    String fullClassName = _prefix + className;
    writeCondDateTime(tableName);
    out("import java.io.Serializable;"); //$NON-NLS-1$
    outln();
    out("import org.apache.log4j.Category;"); //$NON-NLS-1$
    outln();
    out("import de.ivu.util.debug.Log4J;"); //$NON-NLS-1$
    out("import " + modelImplRootClass + ";"); //$NON-NLS-1$ //$NON-NLS-2$
    String iName = voInterfaceName(tableName);
    out("import " + _packageName + "." + iName + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    for (int i = 0; i < _additionalImports.size(); i++) {
      out("import " + _additionalImports.get(i) + ".*;"); //$NON-NLS-1$ //$NON-NLS-2$
      out("import " + _additionalImports.get(i) + "." + __implPackage + ".*;"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
    outln();
    out("/**"); //$NON-NLS-1$
    out("  * Model implementation for the entity " + tableName + "."); //$NON-NLS-1$ //$NON-NLS-2$
    out("  * Contains the list of properties including the IDs of the associated objects."); //$NON-NLS-1$
    out("  * A navigation is not implemented (1:1, 1:n, m:n)"); //$NON-NLS-1$
    writeAuthorHeader();
    out("  */"); //$NON-NLS-1$
    outln();
    out("public " + condAbstract + "class " + fullClassName + " extends " + voClassName //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + " implements " + iName + ", Serializable {"); //$NON-NLS-1$ //$NON-NLS-2$

    writeSerialVersionUIDSupport(_packageName + "." + __implPackage, fullClassName); //$NON-NLS-1$
    writeLoggingSupport(fullClassName);

    StringBuilder createParam = new StringBuilder();
    StringBuilder setters = new StringBuilder();
    StringBuilder javadoc = new StringBuilder();
    for (ColumnData c : getColumnData(tableName)) {
      if (c.isPK()) {
        String name = propertyName(c.getName());
        String type = c.getJavaType();
        if (createParam.length() > 0) {
          createParam.append(", "); //$NON-NLS-1$
          setters.append("\n"); //$NON-NLS-1$
          javadoc.append("\n"); //$NON-NLS-1$
        }
        // JavaType
        createParam.append(type);
        createParam.append(" "); //$NON-NLS-1$
        // varname
        createParam.append(name);
        setters.append(SPACES, 0, __indentStep * 2);
        setters.append("setID_" + firstUp(stripID(name)) + "(" + name + ");"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        javadoc.append(SPACES, 0, __indentStep);
        javadoc.append("  * @param " + name + " key element of the type {@link " + type + "}"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
    }

    String constructorPrefix = "public " + fullClassName; //$NON-NLS-1$

    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Default constructor"); //$NON-NLS-1$
    out1("  */"); //$NON-NLS-1$
    out1(constructorPrefix + "() {"); //$NON-NLS-1$
    out2("// No inititalization, only generation of the instance"); //$NON-NLS-1$
    out1("}"); //$NON-NLS-1$

    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Constructor with key"); //$NON-NLS-1$
    out1("  *"); //$NON-NLS-1$
    out(javadoc.toString());
    out1("  */"); //$NON-NLS-1$
    out1(constructorPrefix + "(" + createParam + ") {"); //$NON-NLS-1$ //$NON-NLS-2$
    out(setters.toString());
    out1("}"); //$NON-NLS-1$

    if (!isJoinTable) {
      StringBuilder propList = new StringBuilder();
      boolean first = true;
      for (ColumnData cd : getColumnData(tableName)) {
        if (cd.isPK()) {
          continue;
        }
        if (first) {
          first = false;
        } else {
          propList.append('\n');
        }
        String nameSuffix = firstUp(cd.getName());
        String propName = propertyName(cd.getName());
        String start = new String(SPACES, 0, __indentStep * 2) + "set" + nameSuffix + "(other._"; //$NON-NLS-1$ //$NON-NLS-2$
        propList.append(start).append(propName).append(");"); //$NON-NLS-1$
      }
      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * Method for copying"); //$NON-NLS-1$
      out1("  *"); //$NON-NLS-1$
      out1("  * @param other the model object the data is copied from"); //$NON-NLS-1$
      out1("  */"); //$NON-NLS-1$
      out1(createVoidMethodLine("copyFrom", fullClassName + " other", false, true, false, false)); //$NON-NLS-1$ //$NON-NLS-2$
      if (propList.length() > 0) {
        out(propList.toString());
      }
      out1("}"); //$NON-NLS-1$
    }
  }

  private void writeSerialVersionUIDSupport(String packageName, String className)
      throws IOException {
    try {
      Class<?> clazz = Class.forName(packageName + '.' + className);
      ObjectStreamClass objectstreamclass = ObjectStreamClass.lookup(clazz);
      if (objectstreamclass != null) {
        out1("private static final long serialVersionUID = " //$NON-NLS-1$
            + objectstreamclass.getSerialVersionUID() + "L;"); //$NON-NLS-1$
      }
    } catch (ClassNotFoundException e) {
      System.err.println("Couldn't load the previous version of the class " + className //$NON-NLS-1$
          + " for the generation of serialVersionUID"); //$NON-NLS-1$
    }
  }

  private void writeCondDateTime(String tableName) throws SQLException, IOException {
    boolean hasDate = false;
    boolean hasTimestamp = false;
    boolean hasBigDecimal = false;
    for (ColumnData columnData : getColumnData(tableName)) {
      String javaType = columnData.getJavaType();
      if (javaType.equals("Date")) { //$NON-NLS-1$
        hasDate = true;
      } else if (javaType.equals("Timestamp")) { //$NON-NLS-1$
        hasTimestamp = true;
      } else if (javaType.equals("BigDecimal")) { //$NON-NLS-1$
        hasBigDecimal = true;
      }
    }
    if (hasDate) {
      out("import java.sql.Date;"); //$NON-NLS-1$
    }
    if (hasTimestamp) {
      out("import java.sql.Timestamp;"); //$NON-NLS-1$
    }
    if (hasBigDecimal) {
      out("import java.math.BigDecimal;"); //$NON-NLS-1$
    }
  }

  protected void writeCommonHeader(String className) throws IOException {
    out("/*"); //$NON-NLS-1$
    out(" * " + className); //$NON-NLS-1$
    out(" * "); //$NON-NLS-1$
    out(" * WARNING! Automatically generated file! Do not edit!"); //$NON-NLS-1$
    out(" * Code Generator by IVU"); //$NON-NLS-1$
    if (_defVersion != null) {
      out(" * Definition Version: " + _defVersion); //$NON-NLS-1$
    }
    out(" */"); //$NON-NLS-1$
    outln();
  }

  /**
   * Generates the bean header for a table.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeBeanHeader(String tableName, String className, boolean isAbstract)
      throws SQLException, IOException {

    String condAbstract = isAbstract ? "abstract " : ""; //$NON-NLS-1$ //$NON-NLS-2$
    writeCommonHeader(className);
    out("package " + _packageName + "." + __ejbPackage + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    outln();
    String fullClassName = _prefix + className;
    List<FKDefinition> keys = getTableImportedKeys(tableName);
    boolean hasImports = !keys.isEmpty();
    keys = getTableExportedKeys(tableName);
    boolean hasExports = !keys.isEmpty();
    if (hasExports || hasImports
        || getColumnData(tableName).size() > getTableKeys(tableName).size()) {
      out("import java.util.Collection;"); //$NON-NLS-1$
      out("import java.sql.SQLException;"); //$NON-NLS-1$
    }
    writeCondDateTime(tableName);
    if (_foreignKeys && (hasExports || hasImports) && _remote || !_modelTypeIsLocal) {
      out("import java.rmi.RemoteException;"); //$NON-NLS-1$
    }
    if (hasImports) {
      // out("import javax.ejb.ObjectNotFoundException;");
    } else if (hasExports) {
      boolean hasReverse11Relation = false;
      Set<String> propertySet = new HashSet<String>();
      for (FKDefinition definition : getTableImportedKeys(tableName)) {
        propertySet.add(propertyName(stripID(definition.getFKName())));
      }

      for (FKDefinition def : getTableExportedKeys(tableName)) {
        String propName = propertyName(generateRelatedPropName(tableName, def, true));
        boolean isJoinTable = def.isJoinTable();
        List<String> fkFields = def.getFKFields();
        String foreignFkName = fkFields.size() == 1 ? fkFields.get(0) : null;
        boolean one2one = !isJoinTable && foreignFkName != null
            && getColumnDataMap(def.getFKTableName()).get(foreignFkName).isUnique();

        if (one2one && !propertySet.contains(propName)) { // Forward references of the same name
          // have priority to the backward
          // references
          hasReverse11Relation = true;
          break;
        }
      }

      if (hasReverse11Relation) {
        //
      }
    }
    out("import javax.ejb.ObjectNotFoundException;"); //$NON-NLS-1$
    if (hasImports || hasExports) {
      out("import javax.ejb.FinderException;"); //$NON-NLS-1$
    }
    out("import javax.ejb.CreateException;"); //$NON-NLS-1$
    out("import javax.ejb.EJBException;"); //$NON-NLS-1$
    out("import javax.ejb.EntityBean;"); //$NON-NLS-1$
    outln();
    out("import org.apache.log4j.Category;"); //$NON-NLS-1$
    outln();
    out("import de.ivu.ejb.IVUFinderException;"); //$NON-NLS-1$
    out("import " + __beanImplRootClass + ";"); //$NON-NLS-1$ //$NON-NLS-2$
    out("import " + __voRootInterface + ";"); //$NON-NLS-1$ //$NON-NLS-2$
    out("import de.ivu.util.debug.Log4J;"); //$NON-NLS-1$
    String voInterfaceName = voInterfaceName(tableName);
    out("import " + _packageName + "." + voInterfaceName + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    out("import " + _packageName + "." + __implPackage + ".*;"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    for (int i = 0; i < _additionalImports.size(); i++) {
      out("import " + _additionalImports.get(i) + ".*;"); //$NON-NLS-1$ //$NON-NLS-2$
      out("import " + _additionalImports.get(i) + "." + __ejbPackage + ".*;"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      out("import " + _additionalImports.get(i) + "." + __implPackage + ".*;"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
    outln();
    writeBeanJavadoc(tableName, false);
    String beanImplClassOnly = __beanImplRootClass
        .substring(__beanImplRootClass.lastIndexOf('.') + 1);
    String condVOIF = _modelTypeIsLocal ? (", " + voInterfaceName) : ""; //$NON-NLS-1$ //$NON-NLS-2$
    out("public " + condAbstract + "class " + fullClassName + " extends " + beanImplClassOnly //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + " implements EntityBean" + condVOIF + " {"); //$NON-NLS-1$ //$NON-NLS-2$
    writeSerialVersionUIDSupport(_packageName + "." + __ejbPackage, fullClassName); //$NON-NLS-1$
    writeLoggingSupport(fullClassName);

    if (_ejbWriteOnceTableNames.contains(tableName)) {
      outln();
      out1("protected transient boolean _readOnly;"); //$NON-NLS-1$
      outln();
      out1("@Override"); //$NON-NLS-1$
      out1("protected void setConditionalReadOnly(final boolean readOnly) {"); //$NON-NLS-1$
      out2("_readOnly = readOnly;"); //$NON-NLS-1$
      out1("}"); //$NON-NLS-1$
    }
  }

  /**
   * Generates EJB methods common for all beans.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeEJBCommon(boolean local,
      String tableName,
      boolean isAbstract,
      boolean useInheritance) throws SQLException, IOException {

    String end = isAbstract ? (local ? ";" : ", RemoteException;") : " {"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    String find = isAbstract ? "find" : "ejbFind"; //$NON-NLS-1$ //$NON-NLS-2$
    String create = isAbstract ? "create" : "ejbCreate"; //$NON-NLS-1$ //$NON-NLS-2$
    String iName = voInterfaceName(tableName);
    if (!isAbstract) {
      outln();
      out1("protected " + iName + " " + BEAN_STATE_VAR + " = null;"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    // create the Parameterlist for the create Methode
    StringBuilder createParam = new StringBuilder();
    StringBuilder aktParam = new StringBuilder();
    String pkClass = null;
    String pkAttr = null;
    boolean hadPkPart = false;
    StringBuilder javadoc = new StringBuilder();
    List<ColumnData> columnData = getColumnData(tableName);
    Set<ColumnData> insertColumn = new HashSet<ColumnData>();
    for (ColumnData c : columnData) {
      if (!c.isNullable()) {
        String name = propertyName(c.getName());
        String type = c.getJavaType();
        if (createParam.length() > 0) {
          createParam.append(", "); //$NON-NLS-1$
          javadoc.append("\n"); //$NON-NLS-1$
        }
        // JavaType
        createParam.append(type);
        createParam.append(" "); //$NON-NLS-1$
        // varname
        createParam.append(name);
        javadoc.append(SPACES, 0, __indentStep).append("  * @param " + name //$NON-NLS-1$
            + " key element of the type {@link " + type + "}"); //$NON-NLS-1$ //$NON-NLS-2$

        if (c.isPK()) {
          if (aktParam.length() > 0) {
            aktParam.append(", "); //$NON-NLS-1$
          }
          // varname
          aktParam.append(name);
          if (hadPkPart) {
            pkClass = "Object"; //$NON-NLS-1$
          } else {
            // the return Java class and attribute
            pkClass = type;
            pkAttr = name;
          }
          hadPkPart = true;
        } else {
          insertColumn.add(c);
        }
      }
    }

    String retClass;
    String suffix = local ? __beanLocalSuffix : __beanRemoteSuffix;
    if (isAbstract) {
      retClass = firstUp(tableName) + suffix;
    } else {
      retClass = pkClass;
    }

    String publicDecl = isAbstract ? "" : "public "; //$NON-NLS-1$ //$NON-NLS-2$
    String createSfx = ") throws CreateException, EJBException" + end; //$NON-NLS-1$
    if ("String".equals(pkClass) && insertColumn.isEmpty()) { //$NON-NLS-1$
      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)"); //$NON-NLS-1$
      out1("  *"); //$NON-NLS-1$
      out1("  * @return Primary key of the entity"); //$NON-NLS-1$
      out1("  * @throws CreateException Bean could not be instantiated "); //$NON-NLS-1$
      out1("  * @throws EJBException not used here but allows a cleanly derivative"); //$NON-NLS-1$
      writeJDException(!local && isAbstract, false);
      out1("  */"); //$NON-NLS-1$
      out1(publicDecl + retClass + ' ' + create + '(' + createSfx);
      if (!isAbstract) {
        out2("return " + create + "(getUniqueKey());"); //$NON-NLS-1$ //$NON-NLS-2$
        out1("}"); //$NON-NLS-1$
      }
    }

    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)"); //$NON-NLS-1$
    out1("  *"); //$NON-NLS-1$
    out1("  * @param details Value Object containing data of the Bean"); //$NON-NLS-1$
    out1("  * @return Primary key of the entity"); //$NON-NLS-1$
    out1("  * @throws CreateException Bean could not be instantiated "); //$NON-NLS-1$
    out1("  * @throws EJBException not used here but allows a cleanly derivative"); //$NON-NLS-1$
    writeJDException(!local && isAbstract, false);
    out1("  */"); //$NON-NLS-1$
    out1(publicDecl + retClass + " " + create + "(" + iName + " details" + createSfx); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    if (!isAbstract) {
      out2(iName + " tmpDetails = details.copy();"); //$NON-NLS-1$
      out2("create(tmpDetails);"); //$NON-NLS-1$
      out2("// was saved at the insert and is not modified any longer"); //$NON-NLS-1$
      out2("resetModified(tmpDetails);"); //$NON-NLS-1$
      out2("return " + BEAN_STATE_VAR + ".getID_" + stripID(pkAttr) + "();"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      out1("}"); //$NON-NLS-1$
    }

    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Initializes the Bean-instance. (Bean-supporting method by EJB specification.)"); //$NON-NLS-1$
    out1("  *"); //$NON-NLS-1$
    out(javadoc.toString());
    out1("  * @return Primary key of the entity"); //$NON-NLS-1$
    out1("  * @throws CreateException Bean could not be instantiated "); //$NON-NLS-1$
    out1("  * @throws EJBException not used here but allows a cleanly derivative"); //$NON-NLS-1$
    writeJDException(!local && isAbstract, false);
    out1("  */"); //$NON-NLS-1$
    out1(publicDecl + retClass + " " + create + "(" + createParam + createSfx); //$NON-NLS-1$ //$NON-NLS-2$
    if (!isAbstract) {
      out2(BEAN_STATE_VAR + " = (" + iName + ")createModel(" + aktParam + ");"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      for (ColumnData c : columnData) {
        if ("String".equals(c.getJavaType()) && !c.getName().startsWith(__idPrefix)) { //$NON-NLS-1$
          out2(BEAN_STATE_VAR + ".set" + firstUp(c.getName()) + "(\"\"); //$NON-NLS-1$"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        if (insertColumn.contains(c)) {
          out2(BEAN_STATE_VAR + ".set" + firstUp(c.getName()) + "(" + propertyName(c.getName()) //$NON-NLS-1$ //$NON-NLS-2$
              + ");"); //$NON-NLS-1$
        }
      }
      out2("create(" + BEAN_STATE_VAR + ");"); //$NON-NLS-1$ //$NON-NLS-2$
      out2("return " + pkAttr + ";"); //$NON-NLS-1$ //$NON-NLS-2$
      out1("}"); //$NON-NLS-1$
    }

    String finderException = isAbstract ? "FinderException" : "IVUFinderException"; //$NON-NLS-1$ //$NON-NLS-2$
    String finderSfx = ") throws " + finderException + end; //$NON-NLS-1$
    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Searches for existing Bean-instance. (Bean-supporting method by EJB specification.)"); //$NON-NLS-1$
    out1("  *"); //$NON-NLS-1$
    out1("  * @param pkObj Key of the entity"); //$NON-NLS-1$
    out1("  * @return Key of the entity"); //$NON-NLS-1$
    out1("  * @throws ObjectNotFoundException if the entity was not found."); //$NON-NLS-1$
    out1("  * @throws " + finderException //$NON-NLS-1$
        + " if an error occurred while searching (does NOT mean \"not found\"."); //$NON-NLS-1$
    writeJDException(!local && isAbstract, false);
    out1("  */"); //$NON-NLS-1$
    out1(publicDecl + retClass + " " + find + "ByPrimaryKey(" + pkClass + " pkObj" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + (") throws ObjectNotFoundException, " + finderException + end)); //$NON-NLS-1$
    if (!isAbstract) {
      out2("find(pkObj);"); //$NON-NLS-1$
      out2("return pkObj;"); //$NON-NLS-1$
      out1("}"); //$NON-NLS-1$
    }

    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Searches for all existing Bean-instances. (Bean-supporting method by EJB specification.)"); //$NON-NLS-1$
    out1("  *"); //$NON-NLS-1$
    out1("  * @return Collection of keys of the entities"); //$NON-NLS-1$
    out1("  * @throws " + finderException //$NON-NLS-1$
        + " if an error occurred while searching (does NOT mean \"not found\"."); //$NON-NLS-1$
    writeJDException(!local && isAbstract, false);
    out1("  */"); //$NON-NLS-1$
    out1(publicDecl
        + "Collection<" + retClass + "> " + find + "All() throws " + finderException + end); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
    if (!isAbstract) {
      out2("try {"); //$NON-NLS-1$
      out3("return " + tableName + __dbaClassSuffix + ".retrieveAllIDs();"); //$NON-NLS-1$ //$NON-NLS-2$
      out2("} catch (SQLException se) {"); //$NON-NLS-1$
      out3("throw new IVUFinderException (se.getMessage(), se);"); //$NON-NLS-1$
      out2("}"); //$NON-NLS-1$
      out1("}"); //$NON-NLS-1$
    }

    List<FKDefinition> importedKeys = getTableImportedKeys(tableName);
    if (_keys) {
      for (FKDefinition fk : getTableExportedKeys(tableName)) {
        String fuPropName = firstUp(generateRelatedPropName(tableName, fk, false));
        String propName = __idPrefix.toLowerCase() + fuPropName;
        if (fk.isJoinTable()) {
          String fkTableName = fk.getFKTableName();
          String propType = isAbstract ? (firstUp(tableName) + suffix) : "String"; //$NON-NLS-1$
          String finder = find + "AllBy" + fuPropName; //$NON-NLS-1$
          outln();
          out1("/**  "); //$NON-NLS-1$
          out1("  * Bean-supporting method by EJB standard."); //$NON-NLS-1$
          out1("  * Method for support of the navigation of the Bean model."); //$NON-NLS-1$
          out1("  *"); //$NON-NLS-1$
          out1("  * @param " + propName + " ID of the objects to be searched"); //$NON-NLS-1$ //$NON-NLS-2$
          out1("  * @return  {@link Collection} of the found " + tableName + "-entities"); //$NON-NLS-1$ //$NON-NLS-2$
          out1("  * @throws " + finderException //$NON-NLS-1$
              + " if an error occurred while searching (does NOT mean \"not found\"."); //$NON-NLS-1$
          writeJDException(!local && isAbstract, false);
          out1("  */"); //$NON-NLS-1$
          out1(publicDecl + "Collection<" + propType + "> " + finder + "(String " + propName //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
              + finderSfx);
          if (!isAbstract) {
            out2("try {"); //$NON-NLS-1$
            out3("return " + fkTableName + __dbaClassSuffix + ".retrieveIDsByID_" + fuPropName //$NON-NLS-1$ //$NON-NLS-2$
                + "(" + propName + ");"); //$NON-NLS-1$ //$NON-NLS-2$
            out2("} catch (SQLException se) {"); //$NON-NLS-1$
            out3("throw new IVUFinderException (se.getMessage(), se);"); //$NON-NLS-1$
            out2("}"); //$NON-NLS-1$
            out1("}"); //$NON-NLS-1$
          }
        }
      }

      Map<String, ColumnData> columnDataMap = getColumnDataMap(tableName);

      for (FKDefinition fk : importedKeys) {
        String fuPropName = firstUp(generateRelatedPropName(tableName, fk, false));
        String propType = isAbstract ? (generateRelatedPropType(tableName, fk) + suffix) : "String"; //$NON-NLS-1$
        String propName = __idPrefix.toLowerCase() + fuPropName;
        List<String> fkFields = fk.getFKFields();
        boolean isUnique = fkFields.size() == 1 && columnDataMap.get(fkFields.get(0)).isUnique();
        outln();
        out1("/**  "); //$NON-NLS-1$
        out1("  * Bean-supporting method by EJB standard."); //$NON-NLS-1$
        out1("  * Method for support of the navigation of the Bean model."); //$NON-NLS-1$
        out1("  *"); //$NON-NLS-1$
        out1("  * @param " + propName + " ID of the objects to be searched"); //$NON-NLS-1$ //$NON-NLS-2$
        if (isUnique) {
          out1("  * @return Key of the entity"); //$NON-NLS-1$
          out1("  * @throws ObjectNotFoundException if the entity was not found."); //$NON-NLS-1$
        } else {
          out1("  * @return  {@link Collection} of the found " + tableName + "-entities"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        out1("  * @throws " + finderException //$NON-NLS-1$
            + " if an error occurred while searching (does NOT mean \"not found\"."); //$NON-NLS-1$
        writeJDException(!local && isAbstract, false);
        out1("  */"); //$NON-NLS-1$
        out1(publicDecl + (isUnique ? propType : ("Collection<" + retClass + ">")) + " " + find //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            + (isUnique ? "" : "All") + "By" + fuPropName + "(String " + propName //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            + (") throws " + finderException + (isUnique ? ", ObjectNotFoundException" : "") + end)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        if (!isAbstract) {
          String encapStart = isUnique ? "findSingle(" : ""; //$NON-NLS-1$ //$NON-NLS-2$
          String encapEnd = isUnique ? ")" : ""; //$NON-NLS-1$ //$NON-NLS-2$

          out2("try {"); //$NON-NLS-1$
          out3("return " + encapStart + tableName + __dbaClassSuffix + ".retrieveIDsByID_" //$NON-NLS-1$//$NON-NLS-2$
              + fuPropName + "(" + propName + encapEnd + ");"); //$NON-NLS-1$//$NON-NLS-2$
          out2("} catch (SQLException se) {"); //$NON-NLS-1$
          out3("throw new IVUFinderException (se.getMessage(), se);"); //$NON-NLS-1$
          out2("}"); //$NON-NLS-1$
          out1("}"); //$NON-NLS-1$
        }
      }
    }

    writePropertyFinders(local, tableName, isAbstract, retClass);

    // internal Bean methods
    if (!isAbstract) {
      outln();
      out1("// internal Bean methods"); //$NON-NLS-1$

      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * Bean-supporting method by EJB standard."); //$NON-NLS-1$
      out1("  *"); //$NON-NLS-1$
      out(javadoc.toString());
      out1("  */"); //$NON-NLS-1$
      out1("public void ejbPostCreate(" + createParam + ") {"); //$NON-NLS-1$ //$NON-NLS-2$
      if (useInheritance) {
        out2("// Subclass has to implement real functionality, if it needs some"); //$NON-NLS-1$
      } else {
        out2("// No functionality required"); //$NON-NLS-1$
      }
      out1("}"); //$NON-NLS-1$

      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * Bean-supporting method by EJB standard."); //$NON-NLS-1$
      out1("  *"); //$NON-NLS-1$
      out1("  * @param details Value Object containing data of the Bean"); //$NON-NLS-1$
      out1("  */"); //$NON-NLS-1$
      out1("public void ejbPostCreate(" + iName + " details) {"); //$NON-NLS-1$ //$NON-NLS-2$
      if (useInheritance) {
        out2("// Subclass has to implement real functionality, if it needs some"); //$NON-NLS-1$
      } else {
        out2("// No functionality required"); //$NON-NLS-1$
      }
      out1("}"); //$NON-NLS-1$

      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * Method to receive a model object, which represents the current internal state of the object."); //$NON-NLS-1$
      out1("  * Optimizes the Client-Server-Performance"); //$NON-NLS-1$
      out1("  *"); //$NON-NLS-1$
      out1("  * @return Model object with new internal state"); //$NON-NLS-1$
      out1("  */"); //$NON-NLS-1$
      out1(createGetterMethodLine("getDetails", iName, isAbstract, false, false)); //$NON-NLS-1$
      if (!isAbstract) {
        out2("return " + BEAN_STATE_VAR + ".copy();"); //$NON-NLS-1$ //$NON-NLS-2$
        out1("}"); //$NON-NLS-1$
      }

      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * Method to receive a model object for the setting of the current internal object state."); //$NON-NLS-1$
      out1("  * Optimizes the Client-Server-Performance"); //$NON-NLS-1$
      out1("  *"); //$NON-NLS-1$
      out1("  * @param details Model object with new internal state"); //$NON-NLS-1$
      List<String> tableKeys = getTableKeys(tableName);
      boolean optimized = columnData.size() - tableKeys.size() > 1;
      boolean check = _foreignKeys && !importedKeys.isEmpty();
      boolean rEx2eEx = _remote && check;
      writeJDException(!_modelTypeIsLocal, rEx2eEx);
      out1("  */"); //$NON-NLS-1$
      out1(createVoidMethodLine("setDetails", //$NON-NLS-1$
          iName + " details", //$NON-NLS-1$
          isAbstract,
          true,
          !_modelTypeIsLocal,
          rEx2eEx));
      if (!isAbstract) {
        if (_ejbWriteOnceTableNames.contains(tableName)) {
          out2("if (_readOnly) { throw new EJBException(\"" + tableName //$NON-NLS-1$
              + "Bean is in read-only mode\");} //$NON-NLS-1$"); //$NON-NLS-1$
        }
        int indent = 2;
        if (optimized) {
          String cName = voImplName(tableName);
          out2("if (details instanceof " + cName + " && " + BEAN_STATE_VAR + " instanceof " + cName //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
              + ") {"); //$NON-NLS-1$
          out3("((" + cName + ")" + BEAN_STATE_VAR + ").copyFrom((" + cName + ")details);"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
          if (check) {
            out3("checkRelations();"); //$NON-NLS-1$
          }
          out2("} else {"); //$NON-NLS-1$
          indent++;
        }
        StringBuilder iPropList = new StringBuilder();
        for (Iterator<ColumnData> i = columnData.iterator(); i.hasNext();) {
          ColumnData cd = i.next();
          if (cd.isPK()) {
            continue;
          }
          iPropList.append(SPACES, 0, __indentStep * indent).append(getSetterName(cd));
          iPropList.append("(details.").append(getGetterName(cd)).append("());"); //$NON-NLS-1$ //$NON-NLS-2$
          if (i.hasNext()) {
            iPropList.append('\n');
          }
        }
        out(iPropList.toString());
        if (optimized) {
          out2("}"); //$NON-NLS-1$
        }
        out1("}"); //$NON-NLS-1$
      }

      outln();
      out1("@Override"); //$NON-NLS-1$
      out1("protected Model setDetails (Model details) {"); //$NON-NLS-1$
      out2(BEAN_STATE_VAR + " = (" + iName + ")details;"); //$NON-NLS-1$ //$NON-NLS-2$
      if (check) {
        out2("if (details != null) {"); //$NON-NLS-1$
        out3("checkRelations();"); //$NON-NLS-1$
        out2("}"); //$NON-NLS-1$
      }
      out2("return " + BEAN_STATE_VAR + ";"); //$NON-NLS-1$ //$NON-NLS-2$
      out1("}"); //$NON-NLS-1$

      outln();
      out1("@Override"); //$NON-NLS-1$
      out1("protected Model getDetailsInternal () {"); //$NON-NLS-1$
      out2("return " + BEAN_STATE_VAR + ";"); //$NON-NLS-1$ //$NON-NLS-2$
      out1("}"); //$NON-NLS-1$

      outln();
      out1("@Override"); //$NON-NLS-1$
      out1(createVoidMethodLine("checkRelations", "", false, false, false, rEx2eEx)); //$NON-NLS-1$ //$NON-NLS-2$
      if (check) {
        for (FKDefinition definition : importedKeys) {
          String foreignPropName = propertyName(stripID(definition.getFKName()));
          String foreignGetterName = "getID_" + firstUp(foreignPropName) + "()"; //$NON-NLS-1$ //$NON-NLS-2$
          writeCheckRelation(foreignPropName, foreignGetterName);
        }
      } else {
        out2("// no relations to be checked in this class, hence, only fulfilment of the interface"); //$NON-NLS-1$
      }
      out1("}"); //$NON-NLS-1$

      if (check) {
        outln();
        out1("@Override"); //$NON-NLS-1$
        out1(createVoidMethodLine("resetRelations", "", false, false, false, false)); //$NON-NLS-1$ //$NON-NLS-2$
        for (FKDefinition def : importedKeys) {
          String foreignPropName = propertyName(stripID(def.getFKName()));
          if (_local) {
            out2("_" + (foreignPropName + __beanLocalSuffix) + " = null;"); //$NON-NLS-1$//$NON-NLS-2$
          }
          if (_remote) {
            out2("_" + (foreignPropName + __beanRemoteSuffix) + " = null;"); //$NON-NLS-1$ //$NON-NLS-2$
          }
        }
        out1("}"); //$NON-NLS-1$
      }

      if (_modelTypeIsLocal) {
        String voInterfaceName = voInterfaceName(tableName);
        outln();
        out1("/**"); //$NON-NLS-1$
        out1("  * The same as <code>{@link #getDetails()}</code>, only to fulfil the interface"); //$NON-NLS-1$
        out1("  *"); //$NON-NLS-1$
        out1("  * @return Copy of the model object"); //$NON-NLS-1$
        out1("  */"); //$NON-NLS-1$
        out1("public " + voInterfaceName + " copy() {"); //$NON-NLS-1$ //$NON-NLS-2$
        out2("return getDetails();"); //$NON-NLS-1$
        out1("}"); //$NON-NLS-1$
      }
    }
  }

  private static String getGetterName(ColumnData columnData) {
    return ("boolean".equals(columnData.getJavaType()) ? "is" : "get") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + firstUp(columnData.getName());
  }

  private static String getSetterName(ColumnData columnData) {
    return "set" + firstUp(columnData.getName()); //$NON-NLS-1$
  }

  private void writeCheckRelation(String foreignPropName, String foreignGtrName) throws IOException {

    String sPropName = stripID(foreignPropName);
    String fuPropName = firstUp(sPropName);
    out2("if (null == " + BEAN_STATE_VAR + "." + foreignGtrName + ") {"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    if (_local) {
      out3("_" + propertyName(sPropName) + __beanLocalSuffix + " = null;"); //$NON-NLS-1$//$NON-NLS-2$
      out3(getRelchkPropName(true, fuPropName) + " = true;"); //$NON-NLS-1$
    }
    if (_remote) {
      out3("_" + propertyName(sPropName) + __beanRemoteSuffix + " = null;"); //$NON-NLS-1$ //$NON-NLS-2$
      out3(getRelchkPropName(false, fuPropName) + " = true;"); //$NON-NLS-1$
    }
    out2("} else {"); //$NON-NLS-1$
    if (_local) {
      out3(getRelchkPropName(true, fuPropName) + " = false;"); //$NON-NLS-1$
    }
    if (_remote) {
      out3(getRelchkPropName(false, fuPropName) + " = false;"); //$NON-NLS-1$
    }
    out2("}"); //$NON-NLS-1$
  }

  /**
   * Generates the container/database access functions for each table.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeDBAHeader(String tableName, String className, boolean isAbstract)
      throws SQLException, IOException {
    String condAbstract = isAbstract ? "abstract " : ""; //$NON-NLS-1$ //$NON-NLS-2$
    writeCommonHeader(className);
    out("package " + _packageName + "." + __implPackage + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    outln();
    out("import java.sql.Connection;"); //$NON-NLS-1$
    out("import java.sql.PreparedStatement;"); //$NON-NLS-1$
    out("import java.sql.ResultSet;"); //$NON-NLS-1$
    out("import java.sql.SQLException;"); //$NON-NLS-1$
    writeCondDateTime(tableName);
    // when table has more than the primary key
    if (getColumnData(tableName).size() > getTableKeys(tableName).size()
        || !getTableImportedKeys(tableName).isEmpty()) {

      out("import java.util.Collection;"); //$NON-NLS-1$
    }
    out("import java.util.Map;"); //$NON-NLS-1$
    out("import java.util.List;"); //$NON-NLS-1$
    out("import java.util.ArrayList;"); //$NON-NLS-1$
    outln();
    out("import org.apache.log4j.Category;"); //$NON-NLS-1$
    outln();
    out("import de.ivu.util.debug.Log4J;"); //$NON-NLS-1$
    for (int i = 0; i < __dbaGenericFiles.length; i++)
      out("import " + __dbaGenericFiles[i] + ";"); //$NON-NLS-1$ //$NON-NLS-2$
    outln();
    for (int i = 0; i < _additionalImports.size(); i++) {
      out("import " + _additionalImports.get(i) + ".*;"); //$NON-NLS-1$ //$NON-NLS-2$
      out("import " + _additionalImports.get(i) + "." + __implPackage + ".*;"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
    outln();
    out("/**"); //$NON-NLS-1$
    out("  * Implementation of the persistency layer for the entity " + tableName + "."); //$NON-NLS-1$ //$NON-NLS-2$
    out("  * Contains all SQL access functions."); //$NON-NLS-1$
    writeAuthorHeader();
    out("  */"); //$NON-NLS-1$
    String classFullName = _prefix + className;
    out("public " + condAbstract + "class " + classFullName + " extends DBABase {"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    writeSerialVersionUIDSupport(_packageName + "." + __implPackage, className); //$NON-NLS-1$
    writeLoggingSupport(className);
    // writeSelectClause(tableName);
    writeTableConstants(tableName);

    out1("private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);"); //$NON-NLS-1$
  }

  /**
   * Generates the container/database access functions for each table.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeTableConstants(String tableName) throws SQLException, IOException {
    StringBuilder colSB = new StringBuilder();
    outln();
    out1("public static final String TABLENAME = \"" + tableName + "\"; //$NON-NLS-1$"); //$NON-NLS-1$ //$NON-NLS-2$
    for (Iterator<ColumnData> i = getColumnData(tableName).iterator(); i.hasNext();) {
      ColumnData cd = i.next();
      String name = cd.getName();
      String upperName = name.toUpperCase();
      out1("public static final String " + upperName + " = \"" + name + "\"; //$NON-NLS-1$"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      out1("public static final String " + upperName + "_QUAL = \"" + tableName + "." + name //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + "\"; //$NON-NLS-1$"); //$NON-NLS-1$
      colSB.append(upperName);
      if (i.hasNext()) {
        colSB.append(',');
      }
    }
    outln();
    out1("private static final String[] COLUMNS = {" + colSB + "};"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * Generates the container/database access functions for each table.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeSelectClause(String tableName) throws SQLException, IOException {
    StringBuilder list = new StringBuilder();
    outln();
    out1("public static final String PROPERTYLIST = "); //$NON-NLS-1$
    for (Iterator<ColumnData> i = getColumnData(tableName).iterator(); i.hasNext();) {
      ColumnData cd = i.next();
      if (__objAlias != null) {
        list.append(__objAlias + "."); //$NON-NLS-1$
      }
      list.append(cd.getName());
      if (i.hasNext()) {
        list.append(","); //$NON-NLS-1$
      }
    }
    out2("\"" + list + "\";"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * Generates the container/database access functions for each table.
   */
  protected void writeBeanSupport(List<String> keys) {
    StringBuilder keyList = new StringBuilder();
    for (Iterator<String> i = keys.iterator(); i.hasNext();) {
      String k = i.next();
      keyList.append(propertyName(k));
      if (i.hasNext()) {
        keyList.append(","); //$NON-NLS-1$
      }
    }
  }

  /**
   * Writes out the set and get functions for each variable.
   */
  protected void writeProperty(boolean local, ColumnData cd, boolean isAbstract, boolean withinEJB)
      throws IOException {

    String propName = propertyName(cd.getName());
    String typeName = cd.getJavaType();
    String getterName = getGetterName(cd);
    String setterName = getSetterName(cd);

    boolean remoteEx = (isAbstract || (withinEJB && !_modelTypeIsLocal)) && !local;
    boolean isModelImpl = !isAbstract && !withinEJB;
    if (isModelImpl) {
      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * " + cd); //$NON-NLS-1$
      out1("  */"); //$NON-NLS-1$
      out1("protected " + typeName + " _" + propName + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
    boolean isPrimarySetter = cd.isPK();
    if (!isPrimarySetter || isModelImpl) {
      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * Sets the value of " + propName + " in the entity " + cd.getTablename()); //$NON-NLS-1$ //$NON-NLS-2$
      out1("  *"); //$NON-NLS-1$
      out1("  * @param " + propName + " new value of the attribute " + propName); //$NON-NLS-1$ //$NON-NLS-2$
      writeJDException(remoteEx, false);
      out1("  */"); //$NON-NLS-1$
      out1(createVoidMethodLine(setterName,
          typeName + ' ' + propName,
          isAbstract,
          !isPrimarySetter,
          remoteEx,
          false));
      if (!isAbstract) {
        boolean isPrimitive = Character.isLowerCase(typeName.charAt(0)) && !typeName.endsWith("]"); //$NON-NLS-1$
        if (!withinEJB) {
          if (isPrimitive) {
            out2("if (_" + propName + " != " + propName + ") {"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          } else {
            out2("if (different(_" + propName + ", " + propName + ")) {"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          }
          if (typeName.equals("String")) { //$NON-NLS-1$
            out3("_" + propName + " = checkLength(" + propName + ", " + firstUp(cd.getTablename()) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + "Model." + propName.toUpperCase() + "_LENGTH);"); //$NON-NLS-1$ //$NON-NLS-2$
          } else {
            out3("_" + propName + " = " + propName + ';'); //$NON-NLS-1$ //$NON-NLS-2$
          }
          out3("setModified();"); //$NON-NLS-1$
          out2("}"); //$NON-NLS-1$
        } else {
          if (_ejbWriteOnceTableNames.contains(cd.getTablename())) {
            out2("if (_readOnly) { throw new EJBException(\"" + cd.getTablename() //$NON-NLS-1$
                + "Bean is in read-only mode\");} //$NON-NLS-1$"); //$NON-NLS-1$
          }

          if (cd.isFK()) {
            String fuPropName = firstUp(stripID(propName));
            out2("if (null == " + propName + ") {"); //$NON-NLS-1$ //$NON-NLS-2$
            if (_local) {
              out3("_" + propertyName(stripID(propName)) + __beanLocalSuffix + " = null;"); //$NON-NLS-1$ //$NON-NLS-2$
              out3(getRelchkPropName(true, fuPropName) + " = true;"); //$NON-NLS-1$
            }
            if (_remote) {
              out3("_" + propertyName(stripID(propName)) + __beanRemoteSuffix + " = null;"); //$NON-NLS-1$//$NON-NLS-2$
              out3(getRelchkPropName(false, fuPropName) + " = true;"); //$NON-NLS-1$
            }
            out2("} else {"); //$NON-NLS-1$
            String old = "old"; //$NON-NLS-1$
            out3(typeName + ' ' + old + " = " + BEAN_STATE_VAR + "." + getterName + "();"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            if (isPrimitive) {
              out3("if (" + old + " != " + propName + ") {"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            } else {
              out3("if (" + old + " == null || !" + old + ".equals(" + propName + ")) {"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            }
            if (_local) {
              out4(getRelchkPropName(true, fuPropName) + " = false;"); //$NON-NLS-1$
            }
            if (_remote) {
              out4(getRelchkPropName(false, fuPropName) + " = false;"); //$NON-NLS-1$
            }
            out3("}"); //$NON-NLS-1$
            out2("}"); //$NON-NLS-1$
          }
          out2(BEAN_STATE_VAR + "." + setterName + "(" + propName + ");"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        out1("}"); //$NON-NLS-1$
      }
      outln();
    }
    out1("/**"); //$NON-NLS-1$
    out1("  * Gets the value of " + propName + " in the entity " + cd.getTablename()); //$NON-NLS-1$ //$NON-NLS-2$
    out1("  *"); //$NON-NLS-1$
    out1("  * @return value of the attribute " + propName); //$NON-NLS-1$
    writeJDException(remoteEx, false);
    if (EMF_MODEL_ANNOTATIONS && isAbstract) {
      out1("  * @model"); //$NON-NLS-1$
    }
    out1("  */"); //$NON-NLS-1$
    out1(createGetterMethodLine(getterName, typeName, isAbstract, remoteEx, false));
    if (!isAbstract) {
      out2("return _" + (withinEJB ? ("details." + getterName + "()") : propName) + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
      out1("}"); //$NON-NLS-1$
    }
    // if we have a String property, we code the field length of the database
    // colum as constant to
    // the Interface...
    if (isAbstract && typeName.equals("String")) { //$NON-NLS-1$
      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * The maximum size of " + propName); //$NON-NLS-1$
      out1("  * The size is limited by the database."); //$NON-NLS-1$
      out1("  */"); //$NON-NLS-1$
      out1("public static final int " + propName.toUpperCase() + "_LENGTH = " + cd.getColumnSize() //$NON-NLS-1$ //$NON-NLS-2$
          + ";"); //$NON-NLS-1$
    }
  }

  /**
   * Writes out the retrieveAll function. This retrieves all the rows that match the search pattern.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeJoinTableIDsAcc(String tableName, FKDefinition def1, FKDefinition def2)
      throws IOException, SQLException {

    String fk1TableName = def1.getFKTableName();
    String fk1Name = def1.getFKName();
    String fk2Name = def2.getFKName();
    String c1Name = stripID(fk1Name);
    String methodName = "retrieveIDsBy" + fk2Name; //$NON-NLS-1$
    StringBuilder query = new StringBuilder("select "); //$NON-NLS-1$
    List<String> fk1Fields = def1.getFKFields();
    String keyType;
    if (fk1Fields.size() == 1) {
      keyType = getColType(fk1TableName, fk1Fields.get(0));
    } else {
      keyType = "Object"; //$NON-NLS-1$
    }

    // find props of RELATED table...
    query.append(createSelectClause(null, fk1Fields));
    query.append(" from \" + TABLENAME "); //$NON-NLS-1$
    List<String> params = def2.getFKFields();
    String where = createPrepWhereClause(tableName, null, params, false);
    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Gets a {@link Collection} of IDs from " + c1Name + "-entities, which is described by"); //$NON-NLS-1$ //$NON-NLS-2$
    out1("  * N:M relation " + fk1TableName + " and " + fk2Name + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    out1("  *"); //$NON-NLS-1$
    writeJavadocParams(params);
    writeRetrieveIDsGeneric(tableName, methodName, "\"" + query + where, params, c1Name, keyType); //$NON-NLS-1$
  }

  /**
   * Writes out the retrieveAll function. This retrieves all the rows that match the search pattern.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeRetrieveAll(String tableName,
      List<String> params,
      boolean customWhere,
      String methodName,
      boolean bLike) throws SQLException, IOException {

    String query = "select * from \" + TABLENAME"; //$NON-NLS-1$
    String where = null;

    String objName = _prefix + tableName;
    String modelClassName = voImplName(tableName);
    if (customWhere) {
      where = createWhereClause(tableName, params, bLike);
      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * Method " + methodName + " returns a {@link Collection} of {@link " + objName //$NON-NLS-1$ //$NON-NLS-2$
          + "} objects"); //$NON-NLS-1$
      out1("  *"); //$NON-NLS-1$
      writeJavadocParams(params);
      out1("  * @return  {@link Collection} of {@link " + objName + "} objects"); //$NON-NLS-1$ //$NON-NLS-2$
      out1("  * @throws SQLException Communication with database is failing"); //$NON-NLS-1$
      out1("  */"); //$NON-NLS-1$
      out1("public static Collection<" + objName + "> " + methodName //$NON-NLS-1$ //$NON-NLS-2$
          + "(String where) throws SQLException {"); //$NON-NLS-1$
    } else {
      where = createPrepWhereClause(tableName, params, bLike);
      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * Query for the method " + methodName); //$NON-NLS-1$
      out1("  */"); //$NON-NLS-1$
      out1("final static String query" + firstUp(methodName) + " = "); //$NON-NLS-1$ //$NON-NLS-2$
      out2("\"" + query + where + "; // $NON-NLS-1$"); //$NON-NLS-1$ //$NON-NLS-2$
      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * Method " + methodName + " returns a {@link Collection} of {@link " + objName //$NON-NLS-1$ //$NON-NLS-2$
          + "} objects"); //$NON-NLS-1$
      out1("  *"); //$NON-NLS-1$
      writeJavadocParams(params);
      out1("  * @return  {@link Collection} of {@link " + objName + "} objects"); //$NON-NLS-1$ //$NON-NLS-2$
      out1("  * @throws SQLException Communication with database is failing"); //$NON-NLS-1$
      out1("  */"); //$NON-NLS-1$
      out1(createMethodLine(tableName, methodName, null, params, "Collection<" + objName + ">", //$NON-NLS-1$ //$NON-NLS-2$
          false));
    }
    out2("List retRows = new ArrayList();"); //$NON-NLS-1$
    out2("Connection con = connect();"); //$NON-NLS-1$
    if (customWhere) {
      out2("Statement statement = con.createStatement();"); //$NON-NLS-1$
      out2("ResultSet r = statement.executeQuery(\"" + query + " + \" where \" + where); //$NON-NLS-1$"); //$NON-NLS-1$ //$NON-NLS-2$
    } else {
      out2("PreparedStatement prepstatement ="); //$NON-NLS-1$
      out3("con.prepareStatement(query" + firstUp(methodName) + "); //$NON-NLS-1$"); //$NON-NLS-1$ //$NON-NLS-2$
      out2("prepstatement.setQueryTimeout(QUERY_TIMEOUT);"); //$NON-NLS-1$
      writeParams(tableName, params);
      out2("ResultSet r = prepstatement.executeQuery();"); //$NON-NLS-1$
    }
    out2("while (r.next()) {"); //$NON-NLS-1$
    out3(modelClassName + " curRow = new " + modelClassName + "();"); //$NON-NLS-1$ //$NON-NLS-2$
    out3("getFromResultSet(r, curRow);"); //$NON-NLS-1$
    out3("retRows.add(curRow);"); //$NON-NLS-1$
    out2("}"); //$NON-NLS-1$
    out2("r.close ();"); //$NON-NLS-1$
    if (customWhere) {
      out2("statement.close();"); //$NON-NLS-1$
    } else {
      out2("prepstatement.close();"); //$NON-NLS-1$
    }
    out2("release(con);"); //$NON-NLS-1$
    out2("return retRows;"); //$NON-NLS-1$
    out1("}"); //$NON-NLS-1$
  }

  /**
   * Writes out the retrieveAllIDs function. This retrieves all the rows that match the search
   * pattern.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeRetrieveAllIDs(String tableName, String methodName)
      throws SQLException, IOException {

    String query = "select " + createSelectClause(null, getTableKeys(tableName)) + " from " + tableName + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Method " + methodName + " returns a {@link Collection} of " + tableName + " IDs"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    out1("  *"); //$NON-NLS-1$

    List<String> tableKeys = getTableKeys(tableName);

    String keyType = getColType(tableName, tableKeys.get(0));

    writeRetrieveIDsGeneric(tableName, methodName, "\"" + query, null, tableName, keyType); //$NON-NLS-1$
  }

  /**
   * Writes out the retrieveAllIDs function. This retrieves all the rows that match the search
   * pattern.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeRetrieveAllIDs(String tableName,
      List<String> params,
      String methodName,
      boolean withLike) throws SQLException, IOException {

    String query = "select " + createSelectClause(null, getTableKeys(tableName)) //$NON-NLS-1$
        + " from \" + TABLENAME "; //$NON-NLS-1$
    String where = createPrepWhereClause(tableName, params, withLike);

    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Method " + methodName + " returns a {@link Collection} of " + tableName + " IDs"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    out1("  *"); //$NON-NLS-1$
    writeJavadocParams(params);

    List<String> tableKeys = getTableKeys(tableName);

    String keyType;
    if (tableKeys.size() == 1) {
      keyType = getColType(tableName, tableKeys.get(0));
    } else {
      keyType = "Object"; //$NON-NLS-1$
    }

    writeRetrieveIDsGeneric(tableName, methodName, "\"" + query + where, params, tableName, keyType); //$NON-NLS-1$
  }

  private void writeRetrieveIDsGeneric(String tableName,
      String methodName,
      String query,
      List<String> params,
      String eName,
      String pkType) throws IOException, SQLException {

    outln();
    out1("  * @return a {@link Collection} of " + eName + " IDs"); //$NON-NLS-1$ //$NON-NLS-2$
    out1("  * @throws SQLException Communication with database is failing"); //$NON-NLS-1$
    out1("  */"); //$NON-NLS-1$
    out1(createMethodLine(tableName, methodName, null, params, "Collection<" + pkType + ">", false)); //$NON-NLS-1$ //$NON-NLS-2$
    out2("return retrieveIDs("); //$NON-NLS-1$
    out3(query + ",  //$NON-NLS-1$"); //$NON-NLS-1$
    if (params != null) {
      out3(" //$NON-NLS-1$"); //$NON-NLS-1$
    }
    out3(wrapParams(tableName, params) + ");"); //$NON-NLS-1$
    out1("}"); //$NON-NLS-1$
  }

  private String wrapParams(String tableName, List<String> params) throws SQLException {
    StringBuilder paramSB = new StringBuilder("new Object[]{"); //$NON-NLS-1$
    if (params != null) {
      for (Iterator<String> i = params.iterator(); i.hasNext();) {
        String key = i.next();
        String keyType = getColType(tableName, key);
        String wrapperClass = null;
        char startChar = keyType.charAt(0);
        if (Character.isLowerCase(startChar)) {
          try {
            if (keyType.equals("int")) { //$NON-NLS-1$
              wrapperClass = "Integer"; //$NON-NLS-1$
            } else if (keyType.equals("char")) { //$NON-NLS-1$
              wrapperClass = "Character"; //$NON-NLS-1$
            } else {
              wrapperClass = Character.toUpperCase(startChar) + keyType.substring(1);
            }
            Class.forName("java.lang." + wrapperClass); //$NON-NLS-1$
          } catch (ClassNotFoundException e) {
            wrapperClass = null;
          }
        }

        if (wrapperClass == null) {
          paramSB.append(propertyName(key));
        } else {
          paramSB.append(wrapperClass).append(".valueOf(").append(propertyName(key)) //$NON-NLS-1$
              .append(')');
        }
        if (i.hasNext()) {
          paramSB.append(", "); //$NON-NLS-1$
        }
      }
    }
    paramSB.append("}"); //$NON-NLS-1$
    return paramSB.toString();
  }

  /**
   * Writes out the retrieve function.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeRetrieve(String tableName, List<String> params, String methodName)
      throws SQLException, IOException {

    String query = "select " + (tableName != null ? "*" : params.get(0)) + " from \" + TABLENAME "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    String where = createPrepWhereClause(tableName, params, false);

    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Query for the method " + methodName); //$NON-NLS-1$
    out1("  */"); //$NON-NLS-1$
    out1("final static String query" + firstUp(methodName) + " = "); //$NON-NLS-1$ //$NON-NLS-2$
    out2("\"" + query + where + "; //$NON-NLS-1$ //$NON-NLS-2$"); //$NON-NLS-1$ //$NON-NLS-2$

    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Fills the first parameter by a WHERE-clause from other parameters."); //$NON-NLS-1$
    out1("  *"); //$NON-NLS-1$
    out1("  * @param  m the object to be filled"); //$NON-NLS-1$
    writeJavadocParams(params);
    out1("  * @return <code>true</code> if the row was found in the database, else <code>false</code>"); //$NON-NLS-1$
    out1("  * @throws SQLException Communication with database is failing"); //$NON-NLS-1$
    out1("  */"); //$NON-NLS-1$
    out1(createMethodLine(tableName, methodName, voImplName(tableName), params, "boolean", false)); //$NON-NLS-1$
    out2("Connection con = connect();"); //$NON-NLS-1$
    out2("try {"); //$NON-NLS-1$
    String queryName = "query" + firstUp(methodName); //$NON-NLS-1$
    out3("PreparedStatement prepstatement = con.prepareStatement(" + queryName + ");"); //$NON-NLS-1$ //$NON-NLS-2$
    out3("try {"); //$NON-NLS-1$
    out4("prepstatement.setQueryTimeout(QUERY_TIMEOUT);"); //$NON-NLS-1$
    writeParams(tableName, params);
    out4("ResultSet r = prepstatement.executeQuery();"); //$NON-NLS-1$
    out4("try {"); //$NON-NLS-1$
    if (tableName != null) {
      out5("if (r.next()) {"); //$NON-NLS-1$
      out6("getFromResultSet(r, m);"); //$NON-NLS-1$
      out6("return true;"); //$NON-NLS-1$
      out5("} else {"); //$NON-NLS-1$
      out6("return false;"); //$NON-NLS-1$
      out5("}"); //$NON-NLS-1$
    } else {
      out5("return r.next();"); //$NON-NLS-1$
    }
    out4("} finally {"); //$NON-NLS-1$
    out5("r.close ();"); //$NON-NLS-1$
    out4("}"); //$NON-NLS-1$
    out3("} finally {"); //$NON-NLS-1$
    out4("prepstatement.close();"); //$NON-NLS-1$
    out3("}"); //$NON-NLS-1$
    out2("} catch (SQLException se) {"); //$NON-NLS-1$
    out3("String[] values = new String[" + params.size() + "];"); //$NON-NLS-1$ //$NON-NLS-2$
    out3("int idx = 0;"); //$NON-NLS-1$
    for (String param : params) {
      out3("values[idx++] = toString(" + propertyName(param) + ");"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    out3("logError(LOGGER, se, " + queryName + ", values);"); //$NON-NLS-1$ //$NON-NLS-2$
    out3("throw se;"); //$NON-NLS-1$
    out2("} finally {"); //$NON-NLS-1$
    out3("release(con);"); //$NON-NLS-1$
    out2("}"); //$NON-NLS-1$
    out1("}"); //$NON-NLS-1$
  }

  /**
   * Writes out the delete function.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeDelete(String tableName, List<String> params, String methodName)
      throws SQLException, IOException {

    String query = "delete from \" + TABLENAME "; //$NON-NLS-1$
    String where = createPrepWhereClause(tableName, params, false);

    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Deletes from the table " + tableName + " by a from parameters builded WHERE-clause"); //$NON-NLS-1$ //$NON-NLS-2$
    out1("  *"); //$NON-NLS-1$
    writeJavadocParams(params);
    out1("  * @return <code>true</code> if the object was deleted successfully"); //$NON-NLS-1$
    out1("  *         <code>false</code> otherwise"); //$NON-NLS-1$
    out1("  * @throws SQLException Communication with database is failing"); //$NON-NLS-1$
    out1("  */"); //$NON-NLS-1$
    out1(createMethodLine(tableName, methodName, null, params, "boolean", false)); //$NON-NLS-1$
    out2("return delete("); //$NON-NLS-1$
    out3("\"" + query + where + ", //$NON-NLS-1$ //$NON-NLS-2$"); //$NON-NLS-1$ //$NON-NLS-2$
    out3(wrapParams(tableName, params) + ");"); //$NON-NLS-1$
    out1("}"); //$NON-NLS-1$
  }

  /**
   * Writes out the retrieveAll function.
   * 
   * @throws SQLException
   * @throws SQLException Problem with database access
   */
  protected void writeRetrieveAll(String tableName, String methodName)
      throws IOException, SQLException {
    String query = "select * from \" + TABLENAME"; //$NON-NLS-1$

    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Query for the method " + methodName); //$NON-NLS-1$
    out1("  */"); //$NON-NLS-1$
    out1("final static String query" + firstUp(methodName) + " = "); //$NON-NLS-1$ //$NON-NLS-2$
    out2("\"" + query + "; //$NON-NLS-1$"); //$NON-NLS-1$ //$NON-NLS-2$

    out1("  * @return <code>true</code> if the row was found in the database, else <code>false</code>"); //$NON-NLS-1$
    out1("  * @throws SQLException Communication with database is failing"); //$NON-NLS-1$
    out1("  */"); //$NON-NLS-1$
    out1(createMethodLine(tableName, methodName, voImplName(tableName), null, "boolean", false)); //$NON-NLS-1$
    out2("Connection con = connect();"); //$NON-NLS-1$
    out2("try {"); //$NON-NLS-1$
    String queryName = "query" + firstUp(methodName); //$NON-NLS-1$
    out3("PreparedStatement prepstatement = con.prepareStatement(" + queryName + ");"); //$NON-NLS-1$ //$NON-NLS-2$
    out3("try {"); //$NON-NLS-1$
    out4("prepstatement.setQueryTimeout(QUERY_TIMEOUT);"); //$NON-NLS-1$
    out4("ResultSet r = prepstatement.executeQuery();"); //$NON-NLS-1$
    out4("try {"); //$NON-NLS-1$
    if (tableName != null) {
      out5("if (r.next()) {"); //$NON-NLS-1$
      out6("getFromResultSet(r, m);"); //$NON-NLS-1$
      out6("return true;"); //$NON-NLS-1$
      out5("} else {"); //$NON-NLS-1$
      out6("return false;"); //$NON-NLS-1$
      out5("}"); //$NON-NLS-1$
    } else {
      out5("return r.next();"); //$NON-NLS-1$
    }
    out4("} finally {"); //$NON-NLS-1$
    out5("r.close ();"); //$NON-NLS-1$
    out4("}"); //$NON-NLS-1$
    out3("} finally {"); //$NON-NLS-1$
    out4("prepstatement.close();"); //$NON-NLS-1$
    out3("}"); //$NON-NLS-1$
    out2("} catch (SQLException se) {"); //$NON-NLS-1$
    out3("logError(LOGGER, se, " + queryName + ");"); //$NON-NLS-1$ //$NON-NLS-2$
    out3("throw se;"); //$NON-NLS-1$
    out2("} finally {"); //$NON-NLS-1$
    out3("release(con);"); //$NON-NLS-1$
    out2("}"); //$NON-NLS-1$
    out1("}"); //$NON-NLS-1$
  }

  /**
   * Writes out the insert function.
   */
  protected void writeInsert(String tableName) throws IOException {
    String cName = voImplName(tableName);
    String methodName = "insert"; //$NON-NLS-1$

    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Inserts the given object into the database"); //$NON-NLS-1$
    out1("  *"); //$NON-NLS-1$
    out1("  * @param m the object " + cName + "to be written "); //$NON-NLS-1$//$NON-NLS-2$
    out1("  * @return <code>true</code> if the object was written successfully"); //$NON-NLS-1$
    out1("  *         <code>false</code> otherwise"); //$NON-NLS-1$
    out1("  * @throws SQLException Communication with database is failing"); //$NON-NLS-1$
    out1("  */"); //$NON-NLS-1$
    out1("public static boolean " + methodName + " (" + cName + " m) throws SQLException {"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    out2("if (m != null) {"); //$NON-NLS-1$
    out3("return insertOrUpdate(m, "); //$NON-NLS-1$
    out4("\"insert into \" + TABLENAME + \" (\" + META_CONTAINER.getPropertyList() + \") \" +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$"); //$NON-NLS-1$
    out4("\"values (\" + META_CONTAINER.getValuesWildCards() + \")\"); //$NON-NLS-1$ //$NON-NLS-2$"); //$NON-NLS-1$
    out2("} else {"); //$NON-NLS-1$
    out3("return true;"); //$NON-NLS-1$
    out2("}"); //$NON-NLS-1$
    out1("}"); //$NON-NLS-1$
  }

  private void writePutIntoPreparedStatement(String tableName) throws IOException, SQLException {
    String cName = voImplName(tableName);
    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Writes the data from a <code>" + cName //$NON-NLS-1$
        + "</code> object into a <code>PreparedStatement</code>."); //$NON-NLS-1$
    out1("  *"); //$NON-NLS-1$
    out1("  * @param m Model object with the data"); //$NON-NLS-1$
    out1("  * @param p PreparedStatement"); //$NON-NLS-1$
    out1("  * @throws SQLException Communication with database is failing"); //$NON-NLS-1$
    out1("  */"); //$NON-NLS-1$
    out1("protected static void putIntoPreparedStatement(" + cName //$NON-NLS-1$
        + " m, PreparedStatement p) throws SQLException {"); //$NON-NLS-1$
    out2("int idx = 1;"); //$NON-NLS-1$
    List<ColumnData> columnDataCol = getColumnData(tableName);
    boolean haveNonPK = false;
    for (ColumnData columnData : columnDataCol) {
      haveNonPK |= !columnData.isPK();
    }
    if (haveNonPK) {
      out2("Map<String, Integer> columns = META_CONTAINER.getColumns();"); //$NON-NLS-1$
    }
    out2("p.setQueryTimeout(QUERY_TIMEOUT);"); //$NON-NLS-1$

    // int idx = 1;
    // Map columns = META_CONTAINER.getColumns();
    // p.setQueryTimeout(QUERY_TIMEOUT);
    // if (columns.containsKey(NAME)) {
    // p.setString(idx++, m._name);
    // }
    // p.setString(idx++, m._id_Recht);

    for (ColumnData columnData : columnDataCol) {
      if (!columnData.isPK()) {
        String name = columnData.getName();
        out2("if (columns.containsKey(" + name.toUpperCase(Locale.US) + ".toUpperCase())) {"); //$NON-NLS-1$ //$NON-NLS-2$
        out3("p." + getPrepFktCall(tableName, name, "idx++", "m", null) + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        out2("}"); //$NON-NLS-1$
      }
    }
    for (ColumnData columnData : columnDataCol) {
      if (columnData.isPK()) {
        out2("p." + getPrepFktCall(tableName, columnData.getName(), "idx++", "m", null) + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
      }
    }
    out1("}"); //$NON-NLS-1$
    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Inserts or changes the data of the given object in the table " + tableName //$NON-NLS-1$
        + " (generic)"); //$NON-NLS-1$
    out1("  *"); //$NON-NLS-1$
    out1("  * @param m Model object, which's current state has to be written into the database"); //$NON-NLS-1$
    out1("  * @param query Query, which runs the operations"); //$NON-NLS-1$
    out1("  * @return <code>true</code> if the object was written or changed successfully"); //$NON-NLS-1$
    out1("  *         <code>false</code> otherwise"); //$NON-NLS-1$
    out1("  * @throws SQLException Communication with database is failing"); //$NON-NLS-1$
    out1("  */"); //$NON-NLS-1$
    String methodName = "insertOrUpdate"; //$NON-NLS-1$
    out1("public static boolean " + methodName + " (" + cName //$NON-NLS-1$ //$NON-NLS-2$
        + " m, String query) throws SQLException {"); //$NON-NLS-1$
    out2("Connection con = connect();"); //$NON-NLS-1$
    out2("try {"); //$NON-NLS-1$
    out3("PreparedStatement prepstatement = con.prepareStatement(query);"); //$NON-NLS-1$
    out3("try {"); //$NON-NLS-1$
    out4("putIntoPreparedStatement(m, prepstatement);"); //$NON-NLS-1$
    out4("return calculateModificationSuccessStatus(prepstatement.executeUpdate());"); //$NON-NLS-1$
    out3("} finally {"); //$NON-NLS-1$
    out4("prepstatement.close();"); //$NON-NLS-1$
    out3("}"); //$NON-NLS-1$
    out2("} catch (SQLException se) {"); //$NON-NLS-1$
    out3("logError(LOGGER, se, query, convertModelToStringArray(m));"); //$NON-NLS-1$
    out3("throw se;"); //$NON-NLS-1$
    out2("} finally {"); //$NON-NLS-1$
    out3("release(con);"); //$NON-NLS-1$
    out2("}"); //$NON-NLS-1$
    out1("}"); //$NON-NLS-1$
  }

  private void writeConvertModelToStringArray(String tableName) throws IOException, SQLException {
    String cName = voImplName(tableName);
    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Writes the data from a <code>" + cName //$NON-NLS-1$
        + "</code> object in <code>String[]</code> for diagnostical reasons."); //$NON-NLS-1$
    out1("  *"); //$NON-NLS-1$
    out1("  * @param m Model object with the data"); //$NON-NLS-1$
    out1("  * @return String[] with the data from the model object"); //$NON-NLS-1$
    out1("  */"); //$NON-NLS-1$
    out1("protected static String[] convertModelToStringArray(" + cName + " m) {"); //$NON-NLS-1$ //$NON-NLS-2$
    List<ColumnData> columnDataCol = getColumnData(tableName);
    boolean haveNonPK = false;
    for (ColumnData columnData : columnDataCol) {
      haveNonPK |= !columnData.isPK();
    }
    if (haveNonPK) {
      out2("Map<String, Integer> columns = META_CONTAINER.getColumns();"); //$NON-NLS-1$
    }
    out2("List<String> values = new ArrayList<String>();"); //$NON-NLS-1$
    for (ColumnData columnData : columnDataCol) {
      if (!columnData.isPK()) {
        boolean isBoolean = "boolean".equals(columnData.getJavaType()); //$NON-NLS-1$
        String col = columnData.getName();
        String fuCol = firstUp(col);
        out2("if (columns.containsKey(" + col.toUpperCase(Locale.US) + ")) {"); //$NON-NLS-1$ //$NON-NLS-2$
        out3("values.add(toString(m." + (isBoolean ? "is" : "get") + fuCol + "()));"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        out2("}"); //$NON-NLS-1$
      }
    }
    for (ColumnData columnData : columnDataCol) {
      if (columnData.isPK()) {
        out2("values.add(toString(m.get" + firstUp(columnData.getName()) + "()));"); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    out2("return values.toArray(new String[values.size()]);"); //$NON-NLS-1$
    out1("}"); //$NON-NLS-1$
  }

  /**
   * Writes out the update function.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeUpdate(String tableName, List<String> params, String methodName)
      throws SQLException, IOException {

    String where = createPrepWhereClause(tableName, params, false);

    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Changes the data of the given object in the table " + tableName + " "); //$NON-NLS-1$ //$NON-NLS-2$
    out1("  *"); //$NON-NLS-1$
    out1("  * @param m Model object, which's current state has to be written into the database"); //$NON-NLS-1$
    out1("  * @return <code>true</code> if the object was changed successfully"); //$NON-NLS-1$
    out1("  *         <code>false</code> otherwise"); //$NON-NLS-1$
    out1("  * @throws SQLException Communication with database is failing"); //$NON-NLS-1$
    out1("  */"); //$NON-NLS-1$
    out1(createMethodLine(tableName, methodName, voImplName(tableName), null, "boolean", false)); //$NON-NLS-1$
    out2("return insertOrUpdate(m, "); //$NON-NLS-1$
    out3("\"update \" + TABLENAME + \" set \" + META_CONTAINER.getUpdateSets() " + where + "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$"); //$NON-NLS-1$ //$NON-NLS-2$
    out1("}"); //$NON-NLS-1$
  }

  /**
   * Writes out the count function. If "key" is not null then a search based on the key is added. If
   * "withLike" then the search is based on a "like" not an exact match.
   * 
   * @throws SQLException Problem with database access
   */
  protected void writeCount(String tableName,
      List<String> params,
      boolean withLike,
      String methodName) throws SQLException, IOException {

    String query = "select count(*) from \" + TABLENAME" + (params != null ? " " : ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    String where = createPrepWhereClause(tableName, params, withLike);
    String fullQuery = "\"" + query + where; //$NON-NLS-1$

    outln();
    out1("/**"); //$NON-NLS-1$
    if (params != null) {
      out1("  * Method returns the number of objects, which were limited by a WHERE-clause by the parameters "); //$NON-NLS-1$
      out1("  *"); //$NON-NLS-1$
      writeJavadocParams(params);
      out1("  * @return Number of objects"); //$NON-NLS-1$
    } else {
      out1("  * Method returns the number of rows of the table " + tableName); //$NON-NLS-1$
      out1("  *"); //$NON-NLS-1$
      out1("  * @return Number of rows"); //$NON-NLS-1$
    }
    out1("  * @throws SQLException Communication with database is failing"); //$NON-NLS-1$
    out1("  */"); //$NON-NLS-1$
    out1(createMethodLine(tableName, methodName, null, params, "int", false)); //$NON-NLS-1$
    if (params != null) {
      out2("return count("); //$NON-NLS-1$
      out3(fullQuery + ",  //$NON-NLS-1$ //$NON-NLS-2$"); //$NON-NLS-1$
      out3(wrapParams(tableName, params) + ");"); //$NON-NLS-1$
    } else {
      out2("return count(" + fullQuery + "); //$NON-NLS-1$"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    out1("}"); //$NON-NLS-1$
  }

  private void writeBeanJavadoc(String tableName, boolean isInterface) throws IOException {
    out("/**"); //$NON-NLS-1$
    out("  * " + (isInterface ? "Interface" : "Implementation") + " for the entity " + tableName //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        + " as " + (isInterface ? "" : "BMP ") + "Entity Bean."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    out("  * The navigation (1:1, 1:n, m:n) is contained"); //$NON-NLS-1$
    writeAuthorHeader();
    if (EMF_MODEL_ANNOTATIONS && isInterface) {
      out("  * @model"); //$NON-NLS-1$
    }
    out("  */"); //$NON-NLS-1$
  }

  private void writeParams(String tableName, List<String> params) throws IOException, SQLException {
    if (params != null) {
      iterateParams(tableName, params.iterator());
    }
  }

  private void iterateParams(String tableName, Iterator<String> i) throws IOException, SQLException {
    for (int x = 1; i.hasNext(); x++) {
      out4("prepstatement." + getPrepFktCall(tableName, i.next(), Integer.toString(x), null, null) //$NON-NLS-1$
          + ";"); //$NON-NLS-1$
    }
  }

  private void writeJavadocParams(List<String> params) throws IOException {
    if (params != null) {
      Iterator<String> i = params.iterator();
      while (i.hasNext()) {
        out1("  * @param " + propertyName(i.next().toString()) + " searching condition"); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
  }

  /**
   * Writes out the getFromResultSet method. The getFromResultSet method interprets the returned
   * result set from a retrieve and update the object with those values.
   * 
   * @throws SQLException Problem with database access
   */
  private void writeGetFromResultSet(String tableName) throws SQLException, IOException {
    String col = null;
    String lcol = null;
    ColumnData cd = null;
    String colType = null;
    Map<String, String> gets = new LinkedHashMap<String, String>(); // used to store the
    // r1=r.getString("r1");
    // thingies
    String get = null;

    String getterName = "getFromResultSet"; //$NON-NLS-1$

    for (Iterator<ColumnData> i = getColumnData(tableName).iterator(); i.hasNext();) {
      cd = i.next();
      col = cd.getName();
      lcol = propertyName(col);
      col = col.toUpperCase(Locale.US);
      colType = cd.getJavaType();

      // work out which data type we are getting for each variable
      //
      if (colType.equals("String")) //$NON-NLS-1$
        get = "m._" + lcol + " = r.getString(idx.intValue());"; //$NON-NLS-1$ //$NON-NLS-2$
      else if (colType.equals("BigDecimal")) //$NON-NLS-1$
        get = "m._" + lcol + " = r.getBigDecimal(idx.intValue());"; //$NON-NLS-1$ //$NON-NLS-2$
      else if (colType.equals("byte")) //$NON-NLS-1$
        get = "m._" + lcol + " = r.getByte(idx.intValue());"; //$NON-NLS-1$ //$NON-NLS-2$
      else if (colType.equals("long")) //$NON-NLS-1$
        get = "m._" + lcol + " = r.getLong(idx.intValue());"; //$NON-NLS-1$//$NON-NLS-2$
      else if (colType.equals("int")) //$NON-NLS-1$
        get = "m._" + lcol + " = r.getInt(idx.intValue());"; //$NON-NLS-1$ //$NON-NLS-2$
      else if (colType.equals("boolean")) //$NON-NLS-1$
        get = "m._" + lcol + " = r.getBoolean(idx.intValue());"; //$NON-NLS-1$ //$NON-NLS-2$
      else if (colType.equals("short")) //$NON-NLS-1$
        get = "m._" + lcol + " = r.getShort(idx.intValue());"; //$NON-NLS-1$//$NON-NLS-2$
      else if (colType.equals("float")) //$NON-NLS-1$
        get = "m._" + lcol + " = r.getFloat(idx.intValue());"; //$NON-NLS-1$//$NON-NLS-2$
      else if (colType.equals("double")) //$NON-NLS-1$
        get = "m._" + lcol + " = r.getDouble(idx.intValue());"; //$NON-NLS-1$//$NON-NLS-2$
      else if (colType.equals("Date")) //$NON-NLS-1$
        get = "m._" + lcol + " = r.getDate(idx.intValue());"; //$NON-NLS-1$//$NON-NLS-2$
      else if (colType.equals("Timestamp")) //$NON-NLS-1$
        get = "m._" + lcol + " = r.getTimestamp(idx.intValue());"; //$NON-NLS-1$ //$NON-NLS-2$
      else if (colType.equals("Time")) //$NON-NLS-1$
        get = "m._" + lcol + " = r.getTime(idx.intValue());"; //$NON-NLS-1$ //$NON-NLS-2$
      else if (colType.equals("char")) //$NON-NLS-1$
        get = "m._" + lcol + " = r.getString(idx.intValue()).charAt(0);"; //$NON-NLS-1$//$NON-NLS-2$
      else if (colType.equals("byte[]")) //$NON-NLS-1$
        get = "m._" + lcol + " = r.getBytes(idx.intValue());"; //$NON-NLS-1$ //$NON-NLS-2$
      else if (colType.equals("Object")) { //$NON-NLS-1$
        get = "try {\n"; //$NON-NLS-1$
        get += "      m._" + lcol + " = new ObjectInputStream (r.getBinaryStream(" + col //$NON-NLS-1$ //$NON-NLS-2$
            + ")).readObject ();\n"; //$NON-NLS-1$
        get += "    }\n"; //$NON-NLS-1$
        get += "    catch (Exception e) {\n"; //$NON-NLS-1$
        get += "      LOGGER.error(\"Exception: reading Blob " + col + ": \" + e);\n"; //$NON-NLS-1$ //$NON-NLS-2$
        get += "      m._" + lcol + " = null;\n"; //$NON-NLS-1$ //$NON-NLS-2$
        get += "    }\n"; //$NON-NLS-1$
      } else {
        out.println("Warning! Unknown type : " + colType + " in writeGetFromResultSet"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      gets.put(col, get);
    }

    // Integer idx;
    // Map columns = META_CONTAINER.getColumns();
    // if ((idx = (Integer)columns.get(ID_RECHT)) != null) {
    // m._id_Recht = r.getString(idx.intValue());
    // }
    // if ((idx = (Integer)columns.get(NAME)) != null) {
    // m._name = r.getString(idx.intValue());
    // }

    outln();
    out1("/**"); //$NON-NLS-1$
    String cName = voImplName(tableName);
    out1("  * Writes the data from a <code>ResultSet</code> to <code>" + cName + "</code> object."); //$NON-NLS-1$ //$NON-NLS-2$
    out1("  *"); //$NON-NLS-1$
    out1("  * @param r the ResultSet with the data"); //$NON-NLS-1$
    out1("  * @param m the object to be filled"); //$NON-NLS-1$
    out1("  * @throws SQLException Communication with database is failing"); //$NON-NLS-1$
    out1("  */"); //$NON-NLS-1$
    out1("protected static void " + getterName + " (ResultSet r, " + cName //$NON-NLS-1$ //$NON-NLS-2$
        + " m) throws SQLException {"); //$NON-NLS-1$

    out2("Integer idx;"); //$NON-NLS-1$
    out2("Map<String, Integer> columns = META_CONTAINER.getColumns();"); //$NON-NLS-1$
    for (Entry<String, String> entry : gets.entrySet()) {
      out2("if ((idx = columns.get(" + entry.getKey() + ".toUpperCase())) != null) {"); //$NON-NLS-1$ //$NON-NLS-2$
      out3(entry.getValue());
      out2("}"); //$NON-NLS-1$
    }

    out1("}"); //$NON-NLS-1$
  }

  public String createSelectClause(String prefix, List<String> params) {
    String select = ""; //$NON-NLS-1$

    // if we have keys passed in then we add a "where" to the count
    // e.g. se
    //
    if (params != null) {
      // work out the "where" part of the update first..
      //
      for (Iterator<String> i = params.iterator(); i.hasNext();) {
        String param = i.next();
        String col = prefix == null ? param : prefix + "." + param; //$NON-NLS-1$
        select += col;

        if (i.hasNext()) {
          select += ", "; //$NON-NLS-1$
        }
      }
    }

    return select;
  }

  /**
   * Creates a where clause. If params are <code>null</code> then an empty string is returned. If
   * "withLike" is <code>true</code> then a "like" clause is used. e.g. "select count(*) from
   * languages". "select count(*) from languages where lang='english'" "select count(*) from
   * languages where lang='english'"
   * 
   * @param tableName table name
   * @param params filter parameter
   * @param withLike if <code>true</code> then a "like" clause is used
   * @return a where clause
   * @throws SQLException Problem with database access
   */
  public String createWhereClause(String tableName, List<String> params, boolean withLike)
      throws SQLException {

    return createWhereClause(tableName, null, params, withLike);
  }

  public String createPrepWhereClause(String tableName, List<String> params, boolean withLike)
      throws SQLException {

    return createPrepWhereClause(tableName, null, params, withLike);
  }

  public String createWhereClause(String tableName,
      String prefix,
      List<String> params,
      boolean withLike) throws SQLException {

    String where = ""; //$NON-NLS-1$

    // if we have keys passed in then we add a "where" to the count
    // e.g. se
    //
    if (params != null) {
      // work out the "where" part of the update first..
      //
      where = "+\" where "; //$NON-NLS-1$
      for (Iterator<String> i = params.iterator(); i.hasNext();) {
        String value = i.next();
        String keyType = getColType(tableName, value);
        String col = prefix == null ? value : prefix + "." + value; //$NON-NLS-1$

        // only allow likes on String types...
        // Hmmm should we allow more than this???
        //
        if ((withLike) && (keyType.equals("String"))) //$NON-NLS-1$
          where += col + " like \"+" + sqlQuote(value.toLowerCase(), keyType); //$NON-NLS-1$
        else
          where += col + "=\" + " + sqlQuote(value.toLowerCase(), keyType); //$NON-NLS-1$

        if (i.hasNext()) {
          where += "+ \" and "; //$NON-NLS-1$
        }
      }
    }

    return where;
  }

  public String createPrepWhereClause(String tableName,
      String prefix,
      List<String> params,
      boolean withLike) throws SQLException {

    String where = ""; //$NON-NLS-1$

    // if we have keys passed in then we add a "where" to the count
    // e.g. se
    //
    if (params != null) {
      // work out the "where" part of the update first..
      //
      where = "+ \" where "; //$NON-NLS-1$
      for (Iterator<String> i = params.iterator(); i.hasNext();) {
        String param = i.next();
        String keyType = getColType(tableName, param);
        String col = prefix == null ? param : prefix + "." + param; //$NON-NLS-1$

        // only allow likes on String types...
        // Hmmm should we allow more than this???
        //
        if (withLike && "String".equals(keyType)) { //$NON-NLS-1$
          where += col + " like ?"; //$NON-NLS-1$
        } else {
          where += col + "=?"; //$NON-NLS-1$
        }

        if (i.hasNext()) {
          where += " and "; //$NON-NLS-1$
        } else {
          where += "\""; //$NON-NLS-1$
        }
      }
    }

    return where;
  }

  /**
   * Creates a method head.
   * 
   * @param isAbstract <code>true</code>, if it is an abstract method
   * @param remoteEx <code>true</code>, if the method can cause a {@link RemoteException}
   * @param ejbEx <code>true</code>, if the method can cause a {@link EJBException}
   * @return method head
   */
  public String createGetterMethodLine(String methodName,
      String returnType,
      boolean isAbstract,
      boolean remoteEx,
      boolean ejbEx) {

    return createMethodLine(methodName, "", //$NON-NLS-1$
        returnType,
        false,
        isAbstract,
        true,
        remoteEx,
        false,
        ejbEx);
  }

  /**
   * Creates a method head.
   * 
   * @param isAbstract <code>true</code>, if it is an abstract method
   * @param isPublic <code>true</code>, if the method is public
   * @param remoteEx <code>true</code>, if the method can cause a {@link RemoteException}
   * @param ejbEx <code>true</code>, if the method can cause a {@link EJBException}
   * @return method head
   */
  String createVoidMethodLine(String methodName,
      String param,
      boolean isAbstract,
      boolean isPublic,
      boolean remoteEx,
      boolean ejbEx) {

    return createMethodLine(methodName, param, "void", //$NON-NLS-1$
        false,
        isAbstract,
        isPublic,
        remoteEx,
        false,
        ejbEx);
  }

  /**
   * Creates a method head.
   * 
   * @param voImplName Name of the implementation class of the Value Object
   * @param isAbstract <code>true</code>, if it is an abstract method
   * @return method head
   * @throws SQLException Problem with database access
   */
  public String createMethodLine(String tableName,
      String methodName,
      String voImplName,
      List<String> params,
      String returnType,
      boolean isAbstract) throws SQLException {

    StringBuilder paramStr = new StringBuilder();

    if (voImplName != null) {
      paramStr.append(voImplName).append(" m"); //$NON-NLS-1$
      if (params != null) {
        paramStr.append(", "); //$NON-NLS-1$
      }
    }

    if (params != null) {
      // work out the query string and the method parameters.
      for (Iterator<String> i = params.iterator(); i.hasNext();) {
        String key = i.next();
        String keyType = getColType(tableName, key);
        paramStr.append(keyType).append(' ').append(propertyName(key));

        if (i.hasNext()) {
          paramStr.append(", "); //$NON-NLS-1$
        }
      }
    }
    return createMethodLine(methodName,
        paramStr.toString(),
        returnType,
        true,
        isAbstract,
        true,
        isAbstract,
        true,
        false);
  }

  /**
   * Creates a method head.
   * 
   * @param staticDecl <code>true</code>, if it is a static mehtod
   * @param isAbstract <code>true</code>, if it is an abstract method
   * @param isPublic <code>true</code>, if the method is public
   * @param remoteEx <code>true</code>, if the method can cause a {@link RemoteException}
   * @param sqlEx <code>true</code>, if the method can cause a {@link SQLException}
   * @param ejbEx <code>true</code>, if the method can cause a {@link EJBException}
   * @return method head
   */
  String createMethodLine(String methodName,
      String params,
      String returnType,
      boolean staticDecl,
      boolean isAbstract,
      boolean isPublic,
      boolean remoteEx,
      boolean sqlEx,
      boolean ejbEx) {

    StringBuilder methodLine = new StringBuilder();
    methodLine.append(isAbstract ? "" : (isPublic ? "public " : "protected ")).append(staticDecl //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        ? "static " //$NON-NLS-1$
        : ""); //$NON-NLS-1$
    methodLine.append(returnType).append(' ').append(methodName).append('(');
    methodLine.append(params).append(")"); //$NON-NLS-1$
    boolean layout = false;
    if (remoteEx || sqlEx || ejbEx) {
      if (methodLine.length() > 80) {
        methodLine.append('\n').append(SPACES, 0, __indentStep * 2);
        layout = !isAbstract;
      } else {
        methodLine.append(' ');
      }
      methodLine.append("throws "); //$NON-NLS-1$
    }
    if (remoteEx) {
      methodLine.append("RemoteException"); //$NON-NLS-1$
    }
    if (sqlEx) {
      if (remoteEx) {
        methodLine.append(", "); //$NON-NLS-1$
      }
      methodLine.append("SQLException"); //$NON-NLS-1$
    }
    if (ejbEx) {
      if (remoteEx) {
        methodLine.append(", "); //$NON-NLS-1$
      }
      methodLine.append("EJBException"); //$NON-NLS-1$
    }
    methodLine.append(isAbstract ? ";" : " {"); //$NON-NLS-1$ //$NON-NLS-2$
    if (layout) {
      methodLine.append('\n');
    }
    return methodLine.toString();
  }

  private final Map<String, List<ColumnData>> _tableName2columnDataMap = new HashMap<String, List<ColumnData>>();
  private final Map<String, Map<String, ColumnData>> _tableName2columnDataMapMap = new HashMap<String, Map<String, ColumnData>>();

  /**
   * Gets the column data for a specified table.
   * 
   * @return column data for a specified table
   * @throws SQLException Problem with database access
   */
  public List<ColumnData> getColumnData(String tableName) throws SQLException {
    List<ColumnData> colData = _tableName2columnDataMap.get(tableName);

    if (colData == null) {
      colData = new ArrayList<ColumnData>();
      Set<String> fk = new HashSet<String>();
      TableMeta tableMeta = _meta.getTableMeta(tableName);
      ForeignKey[] iKeys = tableMeta.getImportedKeys();
      for (ForeignKey iKey : iKeys) {
        fk.add(iKey.fkColumn);
      }
      Set<String> pk = new HashSet<String>(getTableKeys(tableName));
      Column[] columns = tableMeta.getColumns();
      List<IndexInfo> indexes = tableMeta.getIndexInfos();
      Map<String, IndexInfo> indexMap = new HashMap<String, IndexInfo>();
      for (IndexInfo indexInfo : indexes) {
        indexMap.put(indexInfo.name, indexInfo);
      }

      ColumnData cd;
      for (Column col : columns) {
        String colName = col.column;
        IndexInfo indexInfo = indexMap.get(colName);
        boolean isUnique = indexInfo != null && indexInfo.isUnique;
        cd = new ColumnData(colName, tableName, col.dataType, col.size, pk.contains(colName), fk
            .contains(colName), col.isNullable, isUnique);
        colData.add(cd);
      }
      _tableName2columnDataMap.put(tableName, colData);
    }

    return colData;
  }

  /**
   * Gets the column data for a specified table.
   * 
   * @throws SQLException Problem with database access
   */
  public Map<String, ColumnData> getColumnDataMap(String tableName) throws SQLException {
    Map<String, ColumnData> colDataMap = _tableName2columnDataMapMap.get(tableName);
    if (colDataMap == null) {
      colDataMap = new HashMap<String, ColumnData>();
      List<ColumnData> colData = getColumnData(tableName);
      for (ColumnData columnData : colData) {
        colDataMap.put(columnData.getName(), columnData);
      }
      _tableName2columnDataMapMap.put(tableName, colDataMap);
    }
    return colDataMap;
  }

  /**
   * Retrieves the primary keys for a particular table.
   * 
   * @param tableName
   * @return primary keys for a particular table
   * @throws SQLException
   */
  public List<String> getTableKeys(String tableName) throws SQLException {
    try {
      return _meta.getTableMeta(tableName).getPrimaryKeys(_metaData);
    } catch (SQLException e) {
      System.err.println("Error whilst getting keys for " + tableName); //$NON-NLS-1$
      System.err.println(e);
      throw e;
    }
  }

  /**
   * Retrieves the exported or imported keys defined for a particular table.
   */
  private List<FKDefinition> getTableForeignKeys(ForeignKey[] keys) {
    List<FKDefinition> foreignKeyData = new ArrayList<FKDefinition>();
    for (int i = 0; i < keys.length;) {
      ForeignKey key = keys[i];
      FKDefinition f = new FKDefinition(key.pkTable, key.fkTable, key.fkName);
      f.addField(keys[i].fkColumn, keys[i].pkColumn);
      short oldSequence = keys[i].sequence;
      ++i;
      for (; i < keys.length && (keys[i].sequence > oldSequence); i++) {
        f.addField(keys[i].fkColumn, keys[i].pkColumn);
        oldSequence = keys[i].sequence;
      }
      foreignKeyData.add(f);
    }
    return foreignKeyData;
  }

  /**
   * Retrieves the Exported Keys defined for a particular table.
   */
  protected List<FKDefinition> getTableExportedKeys(String tableName) {
    return getTableForeignKeys(_meta.getTableMeta(tableName).getExportedKeys());
  }

  /**
   * Retrieves the Imported Keys defined for a particular table.
   */
  protected List<FKDefinition> getTableImportedKeys(String tableName) {
    return getTableForeignKeys(_meta.getTableMeta(tableName).getImportedKeys());
  }

  /**
   * Retrieves the Imported Keys defined for a particular table.
   */
  protected List<IndexInfo> getTableIndexes(String tableName) {
    return _meta.getTableMeta(tableName).getIndexInfos();
  }

  /**
   * Write the methods associated to this exported foreign key entry. <br>
   * todo saubere Handling of join tables
   */
  protected void writeExportedMethods(boolean local,
      String tableName,
      FKDefinition def,
      boolean isAbstract,
      Set<String> propertySet) throws IOException, SQLException {

    String propName = propertyName(generateRelatedPropName(tableName, def, true));
    String typeBaseName = generateRelatedPropType(tableName, def);
    String typeName = typeBaseName + (local ? __beanLocalSuffix : __beanRemoteSuffix);
    String fuPropName = firstUp(propName);
    String fuTableName = firstUp(tableName);
    boolean isJoinTable = def.isJoinTable();
    String joinName = isJoinTable
        ? generateRelatedJoinName(tableName, def)
        : generateRelatedPropName(typeName, def, false);

    List<String> fkFields = def.getFKFields();
    String foreignFkName = fkFields.size() == 1 ? fkFields.get(0) : null;
    final Map<String, ColumnData> fkColumnDataMap = getColumnDataMap(def.getFKTableName());
    final ColumnData fkColumnData = foreignFkName == null ? null : fkColumnDataMap
        .get(foreignFkName);
    boolean one2one = !isJoinTable && foreignFkName != null && fkColumnData.isUnique();

    if (!one2one) {
      String methodInfix = fuPropName + (local ? __beanLocalSuffix : __beanRemoteSuffix);
      String getterName = "get" + methodInfix + "Col"; //$NON-NLS-1$ //$NON-NLS-2$
      String addName = "add" + methodInfix; //$NON-NLS-1$
      String idAddName = "addID_" + fuPropName; //$NON-NLS-1$
      String removeName = "remove" + methodInfix; //$NON-NLS-1$
      String idRemoveName = "removeID_" + fuPropName; //$NON-NLS-1$
      String addAllName = "addAll" + methodInfix + "Col"; //$NON-NLS-1$ //$NON-NLS-2$
      String pkType = fkColumnData == null ? "Object" : fkColumnData.getJavaType(); //$NON-NLS-1$

      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * Returns the set of entities of the type {@link " + typeName + "}."); //$NON-NLS-1$ //$NON-NLS-2$
      out1("  *"); //$NON-NLS-1$
      out1("  * @return  {@link Collection} of {@link EJB" + (local ? "Local" : "") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + "Object} type {@link " + typeName + "}"); //$NON-NLS-1$ //$NON-NLS-2$
      writeJDException(!local || !_modelTypeIsLocal, true);
      if (EMF_MODEL_ANNOTATIONS && isAbstract) {
        out1("  * @model opposite=\"" + modifyName(generateFKPropName(def)) + "\""); //$NON-NLS-1$ //$NON-NLS-2$
        out1("" + def); //$NON-NLS-1$
      }
      out1("  */"); //$NON-NLS-1$
      out1(createGetterMethodLine(getterName, "Collection<" + typeName + ">", isAbstract, !local //$NON-NLS-1$ //$NON-NLS-2$
          || !_modelTypeIsLocal, true));
      if (!isAbstract) {
        // Bean
        String homeType = typeBaseName + (local ? __beanLocalHomeSuffix : __beanHomeSuffix);
        String homeProperty = propertyName(typeBaseName) + "Home"; //$NON-NLS-1$
        out2(homeType + " " + homeProperty + " = " + homeType + ".HomeFinder.findHome(this);"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        out2("try {"); //$NON-NLS-1$
        out3("return " + homeProperty + ".findAllBy" + joinName + "(" + BEAN_STATE_VAR + ".getID_" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            + fuTableName + "());"); //$NON-NLS-1$
        out2("} catch (FinderException fe) {"); //$NON-NLS-1$
        out3("throw new EJBException(fe);"); //$NON-NLS-1$
        out2("}"); //$NON-NLS-1$
        out1("}"); //$NON-NLS-1$
      }

      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * Adds the object to the set of entities of the type {@link " + typeName + "}."); //$NON-NLS-1$ //$NON-NLS-2$
      out1("  *"); //$NON-NLS-1$
      out1("  * @param " + propName + " " + typeName + "-object"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      boolean throwsRemote = !local || (!_modelTypeIsLocal && !isJoinTable);
      writeJDException(throwsRemote, false);
      out1("  */"); //$NON-NLS-1$
      out1(createVoidMethodLine(addName, typeName + " " + propName, //$NON-NLS-1$
          isAbstract,
          true,
          throwsRemote,
          false));
      String fuFkPropName = firstUp(foreignFkName);
      if (!isAbstract) {
        // Bean
        if (isJoinTable) {
          out2(idAddName + "((" + pkType + ")" + propName + ".getPrimaryKey());"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        } else {
          out2(propName + ".set" + fuFkPropName + "(" + BEAN_STATE_VAR + ".getID_" + fuTableName //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
              + "());"); //$NON-NLS-1$
        }
        out1("}"); //$NON-NLS-1$
      }
      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * Adds the object to the set of entities of the type {@link " + typeName + "}."); //$NON-NLS-1$ //$NON-NLS-2$
      out1("  *"); //$NON-NLS-1$
      out1("  * @param col {@link Collection} of {@link EJBObject}s, which are added to the set."); //$NON-NLS-1$
      writeJDException(throwsRemote, false);
      out1("  */"); //$NON-NLS-1$
      out1(createVoidMethodLine(addAllName, "Collection<" + typeName + "> col", //$NON-NLS-1$ //$NON-NLS-2$
          isAbstract,
          true,
          throwsRemote,
          false));
      if (!isAbstract) {
        out2("for (" + typeName + " " + propName + " : col) {"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        out3(addName + "(" + propName + ");"); //$NON-NLS-1$ //$NON-NLS-2$
        out2("}"); //$NON-NLS-1$
        out1("}"); //$NON-NLS-1$
      }
      String fKTableName = def.getFKTableName();
      boolean nonRepeating = isAbstract || !local || !_remote;
      String joinModelParameters = null;
      if (isJoinTable && nonRepeating) {
        joinModelParameters = getJoinModelParameters(def, propName, fuTableName);
        throwsRemote = !local && isAbstract;
        outln();
        out1("/**"); //$NON-NLS-1$
        out1("  * Adds the object, which is marked by the ID, to the set of entities of the type {@link " //$NON-NLS-1$
            + fuPropName + "}."); //$NON-NLS-1$
        out1("  *"); //$NON-NLS-1$
        out1("  * @param id_" + fuPropName + " ID of " + fuPropName + " entity"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        writeJDException(throwsRemote, true);
        out1("  */"); //$NON-NLS-1$
        out1(createVoidMethodLine(idAddName, pkType + " id_" + fuPropName, //$NON-NLS-1$
            isAbstract,
            true,
            throwsRemote,
            true));
        if (!isAbstract) {
          String modelClassName = voImplName(fKTableName);
          out2("try {"); //$NON-NLS-1$
          out3(modelClassName + " model ="); //$NON-NLS-1$
          out4("new " + modelClassName + " (" + joinModelParameters + ");"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          out3(fKTableName + __dbaClassSuffix + ".insert (model);"); //$NON-NLS-1$
          out2("} catch (Exception e) {"); //$NON-NLS-1$
          out3("throw new EJBException(\"Unable to change Table " + fKTableName //$NON-NLS-1$
              + " Exception: \" + //$NON-NLS-1$"); //$NON-NLS-1$
          out4("e.getMessage(), e);"); //$NON-NLS-1$
          out2("}"); //$NON-NLS-1$
          out1("}"); //$NON-NLS-1$
        }
      }
      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * Deletes an entity from the set of entities of the type {@link " + typeName + "}."); //$NON-NLS-1$ //$NON-NLS-2$
      out1("  *"); //$NON-NLS-1$
      out1("  * @param " + propName + " " + typeName + "-EJBObject, which is removed from the set."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      writeJDException(!local, false);
      out1("  */"); //$NON-NLS-1$
      out1(createVoidMethodLine(removeName, typeName + " " + propName, //$NON-NLS-1$
          isAbstract,
          true,
          !local,
          false));
      if (!isAbstract) {
        if (isJoinTable) {
          out2(idRemoveName + "((" + pkType + ")" + propName + ".getPrimaryKey());"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        } else {
          out2(propName + ".set" + fuFkPropName + "(null);"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        out1("}"); //$NON-NLS-1$
      }
      if (isJoinTable && nonRepeating) {
        throwsRemote = !local && isAbstract;
        outln();
        out1("/**"); //$NON-NLS-1$
        out1("  * Deletes an entity from the set of entities of the type {@link " + typeName + "}."); //$NON-NLS-1$ //$NON-NLS-2$
        out1("  *"); //$NON-NLS-1$
        out1("  * @param id_" + fuPropName + " ID of the " + typeName + " entity to be deleted"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        writeJDException(throwsRemote, true);
        out1("  */"); //$NON-NLS-1$
        out1(createVoidMethodLine(idRemoveName, pkType + " id_" + fuPropName, //$NON-NLS-1$
            isAbstract,
            true,
            throwsRemote,
            true));
        if (!isAbstract) {
          out2("try {"); //$NON-NLS-1$
          out3(fKTableName + "DBA.deleteByKey(" + joinModelParameters + ");"); //$NON-NLS-1$ //$NON-NLS-2$
          out2("} catch (Exception e) {"); //$NON-NLS-1$
          out3("throw new EJBException(\"Unable to remove the " + typeBaseName //$NON-NLS-1$
              + " Entry from the table " + fKTableName + " Exception: \" + //$NON-NLS-1$"); //$NON-NLS-1$//$NON-NLS-2$
          out4("e.getMessage(), e);"); //$NON-NLS-1$
          out2("}"); //$NON-NLS-1$
          out1("}"); //$NON-NLS-1$
        }
      }
    } else {
      if (!propertySet.contains(propName)) {
        // Forward references of the same name have priority to backward references
        String condLocal = local ? __beanLocalSuffix : __beanRemoteSuffix;
        String condLocalPropName = propName + condLocal;
        String methodSuffix = fuPropName + condLocal;
        String getterName = "get" + methodSuffix; //$NON-NLS-1$
        String setterName = "set" + methodSuffix; //$NON-NLS-1$
        boolean throwsRemote = !local || !_modelTypeIsLocal;
        outln();
        out1("/**"); //$NON-NLS-1$
        out1("  * Navigation to the associated entity of the type {@link " + typeName + "}"); //$NON-NLS-1$ //$NON-NLS-2$
        out1("  *"); //$NON-NLS-1$
        out1("  * @return the corresponding EJBObject"); //$NON-NLS-1$
        writeJDException(throwsRemote, true);
        out1("  */"); //$NON-NLS-1$
        out1(createGetterMethodLine(getterName, typeName, isAbstract, throwsRemote, true));
        if (!isAbstract) {
          // Bean
          String homeType = typeBaseName + (local ? __beanLocalHomeSuffix : __beanHomeSuffix);
          String homeProperty = propertyName(typeBaseName) + "Home"; //$NON-NLS-1$
          out2(homeType + " " + homeProperty + " = " + homeType + ".HomeFinder.findHome(this);"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          out2("try {"); //$NON-NLS-1$
          out3("return " + homeProperty + ".findBy" + joinName + "(" + BEAN_STATE_VAR + ".getID_" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
              + fuTableName + "());"); //$NON-NLS-1$
          out2("} catch (ObjectNotFoundException onfe) {"); //$NON-NLS-1$
          out3("return null;"); //$NON-NLS-1$
          out2("} catch (FinderException fe) {"); //$NON-NLS-1$
          out3("throw new EJBException(fe);"); //$NON-NLS-1$
          out2("}"); //$NON-NLS-1$
          out1("}"); //$NON-NLS-1$
        }
        outln();
        out1("/**"); //$NON-NLS-1$
        out1("  * Setting of the associated entity of the type {@link " + typeName + "}"); //$NON-NLS-1$ //$NON-NLS-2$
        out1("  *"); //$NON-NLS-1$
        out1("  * @param " + condLocalPropName + " the corresponding EJBObject"); //$NON-NLS-1$ //$NON-NLS-2$
        writeJDException(throwsRemote, false);
        out1("  */"); //$NON-NLS-1$
        out1(createVoidMethodLine(setterName, typeName + " " + condLocalPropName, //$NON-NLS-1$
            isAbstract,
            true,
            throwsRemote,
            false));
        if (!isAbstract) {
          // Bean
          if (_ejbWriteOnceTableNames.contains(tableName)) {
            out2("if (_readOnly) { throw new EJBException(\"" + tableName //$NON-NLS-1$
                + "Bean is in read-only mode\");} //$NON-NLS-1$"); //$NON-NLS-1$
          }
          String fuFkPropName = firstUp(def.getFKFields().get(0));
          String oldValue = "old" + typeName; //$NON-NLS-1$
          out2(typeName + " " + oldValue + " = " + getterName + "();"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          out2("if (" + oldValue + " != null) {"); //$NON-NLS-1$ //$NON-NLS-2$
          out3(oldValue + ".set" + fuFkPropName + "(null);"); //$NON-NLS-1$ //$NON-NLS-2$
          out2("}"); //$NON-NLS-1$
          out2("if (" + condLocalPropName + " != null) {"); //$NON-NLS-1$ //$NON-NLS-2$
          out3(condLocalPropName + ".set" + fuFkPropName + "(" + BEAN_STATE_VAR + ".getID_" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
              + fuTableName + "());"); //$NON-NLS-1$
          out2("}"); //$NON-NLS-1$
          out1("}"); //$NON-NLS-1$
        }
      }
    }
  }

  private String getJoinModelParameters(FKDefinition def, String propName, String fuTableName)
      throws SQLException, IOException {

    String parameter = ""; //$NON-NLS-1$
    int pos = getJoinPropertyPosition(propName, def);
    if (pos == 1) {
      parameter = "id_" + firstUp(propName) + ", " + BEAN_STATE_VAR + ".getID_" + fuTableName //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + "()"; //$NON-NLS-1$
    } else if (pos == 2) {
      parameter = BEAN_STATE_VAR + ".getID_" + fuTableName + "(), " + "id_" + firstUp(propName); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    } else {
      out("An error occurred: the order of the parameter cannot be determined!!!!"); //$NON-NLS-1$
    }
    return parameter;
  }

  /**
   * Write the methods associated to this imported foreign key entry.
   */
  protected String writeImportedMethods(boolean local,
      String tableName,
      FKDefinition def,
      boolean isAbstract) throws IOException {

    String typeBaseName = firstUp(def.getPKTableName());
    String typeName = typeBaseName + (local ? __beanLocalSuffix : __beanRemoteSuffix);
    String propName = propertyName(stripID(def.getFKName()));
    String fuPropName = firstUp(propName);
    String condLocal = local ? __beanLocalSuffix : __beanRemoteSuffix;
    String condLocalPropName = propName + condLocal;
    String privatePropName = '_' + condLocalPropName;
    String methodSuffix = fuPropName + condLocal;
    String getterName = "get" + methodSuffix; //$NON-NLS-1$
    String setterName = "set" + methodSuffix; //$NON-NLS-1$
    String relchkPropName = getRelchkPropName(local, fuPropName);
    if (!isAbstract) {
      // Property of referenced Object
      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * Relation zu " + fuPropName); //$NON-NLS-1$
      out1("  */"); //$NON-NLS-1$
      out1("protected " + typeName + " " + privatePropName + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      outln();
      out1("/**"); //$NON-NLS-1$
      out1("  * Flag for the validity of the relation " + fuPropName); //$NON-NLS-1$
      out1("  */"); //$NON-NLS-1$
      out1("protected boolean " + relchkPropName + " = false;"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    boolean throwsRemote = !local || !_modelTypeIsLocal;
    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Navigation to the associated entity of the type {@link " + typeName + "}"); //$NON-NLS-1$ //$NON-NLS-2$
    out1("  *"); //$NON-NLS-1$
    out1("  * @return the corresponding EJBObject"); //$NON-NLS-1$
    writeJDException(throwsRemote, true);
    if (EMF_MODEL_ANNOTATIONS && isAbstract) {
      out1("  * @model opposite =\"" + modifyName(tableName) + "Col\""); //$NON-NLS-1$ //$NON-NLS-2$
    }
    out1("  */"); //$NON-NLS-1$
    out1(createGetterMethodLine(getterName, typeName, isAbstract, throwsRemote, true));
    if (!isAbstract) {
      String foreignIdAccess = BEAN_STATE_VAR + ".getID_" + fuPropName + "()"; //$NON-NLS-1$ //$NON-NLS-2$
      out2("if (!" + relchkPropName + ") {"); //$NON-NLS-1$ //$NON-NLS-2$
      out3("if (null == " + foreignIdAccess + ") {"); //$NON-NLS-1$ //$NON-NLS-2$
      out4(privatePropName + " = null;"); //$NON-NLS-1$
      out3("} else if (null == " + privatePropName + " || !" + privatePropName //$NON-NLS-1$ //$NON-NLS-2$
          + ".getPrimaryKey().equals(" + foreignIdAccess + ")) {"); //$NON-NLS-1$ //$NON-NLS-2$
      out4("try {"); //$NON-NLS-1$
      String homeType = typeBaseName + (local ? __beanLocalHomeSuffix : __beanHomeSuffix);
      out5(homeType + " home = " + homeType + ".HomeFinder.findHome(this);"); //$NON-NLS-1$ //$NON-NLS-2$
      out5(privatePropName + " = home.findByPrimaryKey(" + foreignIdAccess + ");"); //$NON-NLS-1$ //$NON-NLS-2$
      out4("} catch (ObjectNotFoundException onfe) {"); //$NON-NLS-1$
      out5("throw new EJBException(\"Unable to find " + fuPropName + "\", onfe); //$NON-NLS-1$"); //$NON-NLS-1$//$NON-NLS-2$
      out4("} catch (FinderException fe) {"); //$NON-NLS-1$
      out5("throw new EJBException(\"Probably DB inconsistence in table " + def.getPKTableName() //$NON-NLS-1$
          + "\", fe); //$NON-NLS-1$"); //$NON-NLS-1$
      out4("}"); //$NON-NLS-1$
      out3("}"); //$NON-NLS-1$
      out3(relchkPropName + " = true;"); //$NON-NLS-1$
      out2("}"); //$NON-NLS-1$
      out2("return " + privatePropName + ";"); //$NON-NLS-1$ //$NON-NLS-2$
      out1("}"); //$NON-NLS-1$
    }
    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Setting of the associated entity of the type {@link " + typeName + "}"); //$NON-NLS-1$ //$NON-NLS-2$
    out1("  *"); //$NON-NLS-1$
    out1("  * @param " + condLocalPropName + " the corresponding EJBObject"); //$NON-NLS-1$ //$NON-NLS-2$
    writeJDException(throwsRemote, false);
    out1("  */"); //$NON-NLS-1$
    out1(createVoidMethodLine(setterName, typeName + " " + condLocalPropName, //$NON-NLS-1$
        isAbstract,
        true,
        throwsRemote,
        false));
    if (!isAbstract) {
      if (_ejbWriteOnceTableNames.contains(tableName)) {
        out2("if (_readOnly) { throw new EJBException(\"" + tableName //$NON-NLS-1$
            + "Bean is in read-only mode\");} //$NON-NLS-1$"); //$NON-NLS-1$
      }
      if (_local) {
        if (local) {
          out2('_' + propName + __beanLocalSuffix + " = " + condLocalPropName + ";"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
          out2(getRelchkPropName(true, fuPropName) + " = false;"); //$NON-NLS-1$
        }
      }
      if (_remote) {
        if (local) {
          out2(getRelchkPropName(false, fuPropName) + " = false;"); //$NON-NLS-1$
        } else {
          out2('_' + propName + __beanRemoteSuffix + " = " + condLocalPropName + ";"); //$NON-NLS-1$//$NON-NLS-2$
        }
      }
      String idValue = "(String)" + condLocalPropName + ".getPrimaryKey()"; //$NON-NLS-1$//$NON-NLS-2$
      out2(BEAN_STATE_VAR + ".setID_" + fuPropName + "(" + condLocalPropName + " == null ? null : " //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
          + idValue + ");"); //$NON-NLS-1$
      out1("}"); //$NON-NLS-1$
    }
    return propName;
  }

  /**
   * @param local <code>true</code>, if it is {@link EJBLocalObject}
   * @param fuPropName name of the Bean property
   * @return name of the RelChk-Property for a Bean property
   */
  private String getRelchkPropName(boolean local, String fuPropName) {
    return "_relchk_" + fuPropName + (local ? __beanLocalSuffix : __beanRemoteSuffix); //$NON-NLS-1$
  }

  /**
   * Write the references to this foreing entity.
   */
  protected void writeEJBRef(boolean local, boolean writeLink, String typeBaseName)
      throws IOException {

    String typeName = typeBaseName + (local ? __beanLocalSuffix : __beanRemoteSuffix);
    String homeType = typeBaseName + (local ? __beanLocalHomeSuffix : __beanHomeSuffix);
    String pkg = _packageName + "." + __ejbPackage + "."; //$NON-NLS-1$ //$NON-NLS-2$
    String hClass = pkg + homeType;
    String iClass = pkg + typeName;
    out2(local ? "<ejb-local-ref>" : "<ejb-ref>"); //$NON-NLS-1$ //$NON-NLS-2$
    out3("<ejb-ref-name>ejb/" + typeName + "</ejb-ref-name>"); //$NON-NLS-1$//$NON-NLS-2$
    out3("<ejb-ref-type>Entity</ejb-ref-type>"); //$NON-NLS-1$
    out3((local ? "<local-home>" : "<home>") + hClass + (local ? "</local-home>" : "</home>")); //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
    out3((local ? "<local>" : "<remote>") + iClass + (local ? "</local>" : "</remote>")); //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
    if (writeLink) {
      out3("<ejb-link>" + typeBaseName + "</ejb-link>"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    out2(local ? "</ejb-local-ref>" : "</ejb-ref>"); //$NON-NLS-1$//$NON-NLS-2$
  }

  /**
   * Write the references to this foreing entity.
   */
  protected void writeJBossRef(String typeBaseName) throws IOException {

    String typeName = typeBaseName + __beanRemoteSuffix;
    out3("<ejb-ref>"); //$NON-NLS-1$
    out4("<ejb-ref-name>ejb/" + typeName + "</ejb-ref-name>"); //$NON-NLS-1$//$NON-NLS-2$
    out4("<jndi-name>" + __jbossJNDIPrefix + typeName + "</jndi-name>"); //$NON-NLS-1$ //$NON-NLS-2$
    out3("</ejb-ref>"); //$NON-NLS-1$
  }

  /**
   * Write the references to this foreing entity.
   */
  protected void writeTomcatRef(String typeBaseName) throws IOException {

    String typeName = typeBaseName + __beanRemoteSuffix;
    out3("<ResourceParams name=\"ejb/" + typeName + "\">"); //$NON-NLS-1$ //$NON-NLS-2$
    out4("<parameter>"); //$NON-NLS-1$
    out5("<name>link</name>"); //$NON-NLS-1$
    out5("<value>" + __jbossJNDIPrefix + typeName + "</value>"); //$NON-NLS-1$//$NON-NLS-2$
    out4("</parameter>"); //$NON-NLS-1$
    out3("</ResourceParams>"); //$NON-NLS-1$
  }

  /**
   * Ret type type of a particular column name. Cannot use Hashtables to store columns as it screws
   * up the ordering, so we have to do a crap search. (and yes I know it could be better - it's good
   * enuf).
   * 
   * @param tableName table name
   * @param key column name
   * @return Java type for teh column
   * @throws SQLException Problem with database access
   */
  public String getColType(String tableName, String key) throws SQLException {
    for (ColumnData tmp : getColumnData(tableName)) {
      if (tmp.getName().equalsIgnoreCase(key)) {
        return tmp.getJavaType();
      }
    }

    return "unknown"; //$NON-NLS-1$
  }

  /**
   * Generation of the setter method for the Prepared Statement, which reads a field from the Value
   * Object
   * 
   * @param tableName table name
   * @param key name of the field
   * @param number position in the statement
   * @param var name of the variable of the Value Object
   * @param prefix prefix for the access to the fields of the Value Object
   * @return Text of the calling of the setter method
   * @throws SQLException Problem beim Datenbankzugriff
   */
  public String getPrepFktCall(String tableName,
      String key,
      String number,
      String var,
      String prefix) throws SQLException {

    for (ColumnData columnData : getColumnData(tableName)) {
      ColumnData tmp = columnData;
      if (tmp.getName().equalsIgnoreCase(key)) {
        return tmp.getPrepSetFktCall(modifyName(key), number, var, prefix);
      }
    }
    return "unknownCall"; //$NON-NLS-1$
  }

  /**
   * Wrapper for text output. So we can change where the output goes easily!
   * 
   * @param text
   * @throws IOException
   */
  public void out(String text) throws IOException {
    _currentOutput.write(text + "\n"); //$NON-NLS-1$
  }

  private static final char[] SPACES = "                                                      " //$NON-NLS-1$
  .toCharArray();

  public void out(int indent, String text) throws IOException {
    out(new String(SPACES, 0, indent * __indentStep) + text);
  }

  public void out1(String text) throws IOException {
    out(1, text);
  }

  public void out2(String text) throws IOException {
    out(2, text);
  }

  public void out3(String text) throws IOException {
    out(3, text);
  }

  public void out4(String text) throws IOException {
    out(4, text);
  }

  public void out5(String text) throws IOException {
    out(5, text);
  }

  public void out6(String text) throws IOException {
    out(6, text);
  }

  public void outln() throws IOException {
    out(""); //$NON-NLS-1$
  }

  /**
   * Wrap single quotes around the appropriate types for sql statements.
   */
  protected String sqlQuote(String name, String type) {
    String item = name;

    if (type.equals("String")) //$NON-NLS-1$
      item = "formatString(" + name + ")"; //$NON-NLS-1$//$NON-NLS-2$
    // else if (type.compareTo("byte") == 0)
    // item = "\"\'\"+"+name+"+\"\'\"";
    else if (type.equals("Date")) //$NON-NLS-1$
      item = "\"\'\"+" + name + "+\"\'\""; //$NON-NLS-1$//$NON-NLS-2$
    else if (type.equals("Timestamp")) //$NON-NLS-1$
      item = "formatTimeStamp(" + name + ")"; //$NON-NLS-1$//$NON-NLS-2$
    else if (type.equals("Timestamp")) //$NON-NLS-1$
      item = "\"\'\"+" + name + "+\"\'\""; //$NON-NLS-1$//$NON-NLS-2$
    else if (type.equals("Time")) //$NON-NLS-1$
      item = "\"\'\"+" + name + "+\"\'\""; //$NON-NLS-1$//$NON-NLS-2$
    else if (type.equals("long")) //$NON-NLS-1$
      item = name;
    else if (type.equals("int")) //$NON-NLS-1$
      item = name;
    else if (type.equals("boolean")) //$NON-NLS-1$
      item = "formatBoolean(" + name + ")"; //$NON-NLS-1$//$NON-NLS-2$
    else if (type.equals("short")) //$NON-NLS-1$
      item = name;
    else if (type.equals("float")) //$NON-NLS-1$
      item = name;
    else if (type.equals("double")) //$NON-NLS-1$
      item = name;
    else if (type.equals("byte")) //$NON-NLS-1$
      item = name;
    else if (type.equals("byte[]")) // this ain't gonna work! //$NON-NLS-1$
      item = name;
    else if (type.equals("char")) //$NON-NLS-1$
      item = "\"\'\"+" + name + "+\"\'\""; //$NON-NLS-1$//$NON-NLS-2$
    else if (type.equals("Object")) //$NON-NLS-1$
      item = "\"\'\"+" + name + "+\"\'\""; //$NON-NLS-1$//$NON-NLS-2$
    else
      out.println("Warning! Unknown type : " + type + " in sqlQuote"); //$NON-NLS-1$//$NON-NLS-2$

    return item;
  }

  /**
   * Writes an empty generated derived class, to be used as user written extension point.
   */
  protected void writeGen(String pkg, String pfx, String tableName, String sfx, boolean isClass)
      throws IOException {
    out("package " + _packageName + (pkg == null ? "" : ("." + pkg)) + ";"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
    out("/**"); //$NON-NLS-1$
    String className = firstUp(tableName) + sfx;
    String name = pfx + _prefix + className;
    out("  * " + name); //$NON-NLS-1$
    writeAuthorHeader();
    out("  */"); //$NON-NLS-1$
    outln();
    String commonSignature = name + " extends " + _prefix + ABSTR_PREFIX + className + " {"; //$NON-NLS-1$ //$NON-NLS-2$
    if (isClass) {
      out("import de.ivu.util.debug.Log4J;"); //$NON-NLS-1$
      out("import org.apache.log4j.Category;"); //$NON-NLS-1$
      outln();
      out("public class " + commonSignature); //$NON-NLS-1$
      writeLoggingSupport(name);
    } else {
      out("public interface " + commonSignature); //$NON-NLS-1$
    }
    out("}"); //$NON-NLS-1$
  }

  private void writeLoggingSupport(String name) throws IOException {
    out1("private static final Category LOGGER = Log4J.configure(" + name + ".class);"); //$NON-NLS-1$ //$NON-NLS-2$
    out1("static {"); //$NON-NLS-1$
    String revision = _defRevision != null ? _defRevision : "$Revision: 1.19 $"; //$NON-NLS-1$
    out2("LOGGER.info(Log4J.dumpVersion(" + name + ".class, Log4J.extractVersion(\"" + revision //$NON-NLS-1$//$NON-NLS-2$
        + "\"))); //$NON-NLS-1$"); //$NON-NLS-1$
    out1("}"); //$NON-NLS-1$
  }

  protected String generateRelatedPropName(String tableName, FKDefinition def, boolean exported) {
    // tricky code here: when jointable name is NAME_NAME, then try to find
    // out the names of fk fields to return the right name....
    // else (jointable name is LEFT_RIGHT), simply return the
    // other part (that is not like tableName)...
    String joinTable = modifyName(def.getFKTableName());
    String lJoinTable = joinTable.toLowerCase();
    String lTab = tableName.toLowerCase();
    if (lJoinTable.startsWith(lTab + "_") && lJoinTable.endsWith("_" + lTab)) { //$NON-NLS-1$ //$NON-NLS-2$
      return generateFKPropName(def);
    }
    if (lJoinTable.startsWith(lTab + "_")) { //$NON-NLS-1$
      String rightTable = firstUp(joinTable.substring(tableName.length() + 1));
      return rightTable;
    }
    if (lJoinTable.endsWith("_" + lTab)) { //$NON-NLS-1$
      return joinTable.substring(0, lJoinTable.lastIndexOf(lTab) - 1);
    }
    String fkName = ""; //$NON-NLS-1$
    if (def.getFKFields().size() == 1) {
      fkName = def.getFKFields().get(0);
    }
    if (fkName.length() > 0 && fkName.startsWith(__idPrefix)) {
      boolean doFK;
      if (exported) {
        doFK = !fkName.toLowerCase().endsWith(lTab);
      } else {
        doFK = !fkName.equalsIgnoreCase((__idPrefix + lTab));
      }
      if (doFK) {
        return fkName.substring(__idPrefix.length());
      }
    }
    return joinTable;
  }

  protected String generateRelatedJoinName(String tableName, FKDefinition def) throws SQLException {
    if (!def.isJoinTable()) {
      return null;
    }
    List<String> tableKeys = getTableKeys(def.getFKTableName());
    String propName = generateRelatedPropName(tableName, def, false);
    String key;
    if (2 != tableKeys.size()) {
      out.println("SOMETHING WENT WRONG"); //$NON-NLS-1$
      return "SOMETHING WENT WRONG"; //$NON-NLS-1$
    }
    Iterator<String> i = tableKeys.iterator();
    key = i.next();
    if (key.equalsIgnoreCase("id_" + propName)) { //$NON-NLS-1$
      key = i.next();
    }
    return stripID(key);
  }

  protected int getJoinPropertyPosition(String property, FKDefinition def) throws SQLException {
    if (!def.isJoinTable()) {
      return -1;
    }
    List<String> tableKeys = getTableKeys(def.getFKTableName());
    String key;
    if (2 != tableKeys.size()) {
      out.println("SOMETHING WENT WRONG"); //$NON-NLS-1$
      return -1;
    } else {
      Iterator<String> i = tableKeys.iterator();
      key = i.next();
      if (key.equalsIgnoreCase("id_" + property)) { //$NON-NLS-1$
        return 1;
      }
      key = i.next();
      if (key.equalsIgnoreCase("id_" + property)) { //$NON-NLS-1$
        return 2;
      }
    }
    out.println("getJoinPropertyPosition return -1"); //$NON-NLS-1$
    return -1;
  }

  protected String generateRelatedPropType(String tableName, FKDefinition def) {
    // for types, no adjustment needed, we need the type only...
    String joinTable = def.getFKTableName();
    String lTableName = tableName.toLowerCase();
    String lJoinTable = joinTable.toLowerCase();
    String relatedPropType = firstUp(joinTable);

    if (lJoinTable.startsWith(lTableName + "_")) { //$NON-NLS-1$
      relatedPropType = firstUp(joinTable.substring(tableName.length() + 1));
    }

    if (lJoinTable.endsWith("_" + lTableName)) { //$NON-NLS-1$
      relatedPropType = firstUp(joinTable.substring(0, lJoinTable.lastIndexOf(lTableName) - 1));
    }
    return relatedPropType;
  }

  protected String generateFKPropName(FKDefinition def) {
    StringBuilder ret = new StringBuilder();
    List<String> fkFields = def.getFKFields();
    for (int i = 0; i < fkFields.size(); i++) {
      String fkName = fkFields.get(i);
      ret.append(firstUp(stripID(fkName)));
    }
    return ret.toString();
  }

  private void writeJDException(boolean remoteEx, boolean ejbEx) throws IOException {
    if (remoteEx) {
      out1("  * @throws RemoteException communication failed"); //$NON-NLS-1$
    }
    if (ejbEx) {
      out1("  * @throws EJBException: " + (remoteEx ? "another " : "an ") + "error occurred"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
    }
  }

  protected String getFinderMethods(String tableName,
      String finderName,
      FKDefinition def,
      boolean exportKey) throws SQLException {

    String finder = ""; //$NON-NLS-1$
    String propName = generateRelatedPropName(tableName, def, false).toUpperCase();
    if (exportKey) {
      if (def.isJoinTable()) {
        finder += ("      <finder>\n"); //$NON-NLS-1$
        finder += ("        <name>" + finderName + "</name>\n"); //$NON-NLS-1$//$NON-NLS-2$
        finder += ("        <query>"); //$NON-NLS-1$
        String joinTab = def.getFKTableName();
        String joinKeyName = def.getPKFields().get(0);
        String joinName = generateRelatedJoinName(tableName, def);
        finder += ("," + joinTab + "\n"); //$NON-NLS-1$//$NON-NLS-2$
        finder += ("                WHERE " + joinTab + ".ID_" + propName + " = {0} AND \n"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
        finder += ("                      " + joinTab + ".ID_" + joinName + " = " + tableName + "." //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
            + joinKeyName + "\n"); //$NON-NLS-1$
        finder += ("        </query>\n"); //$NON-NLS-1$
        finder += ("        <order></order>\n"); //$NON-NLS-1$
        finder += ("      </finder>\n"); //$NON-NLS-1$
      }
    } else {
      finder += ("      <finder>\n"); //$NON-NLS-1$
      finder += ("        <name>" + "find" + firstUp(tableName) + "By" + firstUp(generateRelatedPropName(tableName, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          def,
          false)
          + "</name>\n")); //$NON-NLS-1$
      finder += ("        <query>"); //$NON-NLS-1$
      finder += ("ID_" + generateRelatedPropName(tableName, def, false) + " = {0}\n"); //$NON-NLS-1$ //$NON-NLS-2$
      finder += ("        </query>\n"); //$NON-NLS-1$
      finder += ("        <order></order>\n"); //$NON-NLS-1$
      finder += ("      </finder>\n"); //$NON-NLS-1$
    }
    return finder;
  }

  protected File checkFile(File file, String srcPath) {
    if (_deriveFileList == null || !_deriveFileList.contains(file.getName())) {
      return file;
    }
    if (srcPath == null) {
      return new File(file.getPath(), file.getName() + "_gen"); //$NON-NLS-1$
    } else {
      return new File(srcPath, file.getName());
    }
  }

  private static void makeAll(boolean value) {
    __genBeanHome = value;
    __genBean = value;
    __genBeanInterface = value;
    __genJaws = value;
    __genValueObjectInterface = value;
    __genValueObject = value;
    __genDBA = value;
    __genEJBDescriptorFragment = value;
    __genJBossDescriptorFragment = value;
  }

  protected void writeTableToString(String tableName, boolean isBean)
      throws SQLException, IOException {
    StringBuilder propList = new StringBuilder();

    String fktName = "toString"; //$NON-NLS-1$
    boolean isFirst = true;
    StringBuilder prop = new StringBuilder();
    StringBuilder term = new StringBuilder();
    for (ColumnData cd : getColumnData(tableName)) {
      String propertyName = propertyName(cd.getName());
      boolean isObject = !cd.getJavaClass().isPrimitive();

      if (isBean) {
        term.append(BEAN_STATE_VAR + "."); //$NON-NLS-1$
      }
      term.append(getGetterName(cd)).append("()"); //$NON-NLS-1$

      if (!isFirst) {
        propList.append('\n');
      }

      if (isObject) {
        propList.append(SPACES, 0, __indentStep * 3).append("if (").append(term) //$NON-NLS-1$
            .append(" != null) {\n"); //$NON-NLS-1$
      }
      prop.append(SPACES, 0, __indentStep * (isObject ? 4 : 3));
      prop.append("string += "); //$NON-NLS-1$
      if (isFirst) {
        isFirst = false;
        prop.append('"');
      } else {
        prop.append(" \", "); //$NON-NLS-1$
      }
      prop.append(propertyName).append(" = \" + "); //$NON-NLS-1$
      prop.append(term).append(';').append(" //$NON-NLS-1$"); //$NON-NLS-1$
      propList.append(prop);
      if (isObject) {
        propList.append('\n').append(SPACES, 0, __indentStep * 3).append('}');
      }

      prop.setLength(0);
      term.setLength(0);
    }

    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Overwrites the " + fktName + " method in Object"); //$NON-NLS-1$ //$NON-NLS-2$
    out1("  *"); //$NON-NLS-1$
    out1("  * @return string representation of the current state as listing of the attributes and their values"); //$NON-NLS-1$
    out1("  */"); //$NON-NLS-1$
    out1("@Override"); //$NON-NLS-1$
    out1("public String " + fktName + "() {"); //$NON-NLS-1$ //$NON-NLS-2$
    if (isBean) {
      out2("if (" + BEAN_STATE_VAR + " == null) {"); //$NON-NLS-1$ //$NON-NLS-2$
      out3("return super.toString() + \": Bean is passive\"; //$NON-NLS-1$"); //$NON-NLS-1$
      out2("}"); //$NON-NLS-1$
    }
    out2("try {"); //$NON-NLS-1$
    out3("String string = \"[\" + getClass().getName()+ \": \"; //$NON-NLS-1$ //$NON-NLS-2$"); //$NON-NLS-1$
    out(propList.toString());
    out3("return string  + \"]\" ; //$NON-NLS-1$"); //$NON-NLS-1$
    out2("} catch (Exception e) {"); //$NON-NLS-1$
    out3("LOGGER.error(e, e);"); //$NON-NLS-1$
    out3("return \"\"; //$NON-NLS-1$"); //$NON-NLS-1$
    out2("}"); //$NON-NLS-1$
    out1("}"); //$NON-NLS-1$
  }

  protected void writeVOCopy(String className) throws IOException {
    outln();
    out1("/**"); //$NON-NLS-1$
    out1("  * Copies the object via Object.clone()"); //$NON-NLS-1$
    out1("  *"); //$NON-NLS-1$
    out1("  * @return Copy of the model object"); //$NON-NLS-1$
    out1("  */"); //$NON-NLS-1$
    out1("public " + className + " copy() {"); //$NON-NLS-1$//$NON-NLS-2$
    out2("return (" + className + ")clone();"); //$NON-NLS-1$ //$NON-NLS-2$
    out1("}"); //$NON-NLS-1$
  }

  private static String modifyName(String name) {

    if (name == null) {
      return name;
    }
    final int pfxPlsLen = __idPrefix.length() + 1;
    if (!(name.startsWith(__idPrefix) && name.length() > pfxPlsLen && isLowerCase(name
        .charAt(pfxPlsLen)))) {
      return name.substring(0, 1).toLowerCase() + name.substring(1);
    }
    return name;
  }

  protected static String propertyName(String name) {
    if (name == null) {
      return name;
    }
    String modifiedName = modifyName(name);
    if (modifiedName.startsWith(__idPrefix)) {
      modifiedName = __idPrefix.toLowerCase() + modifiedName.substring(__idPrefix.length());
    }
    return modifiedName;
  }

  /**
   * Upper cases the first character in a String. Includes a compatibility flag for earlier users.
   * This will be removed at some stage.
   */
  protected static String firstUp(String p) {
    if (p == null) {
      return p;
    }
    // return (p.substring(0, 1).toUpperCase() + modifyName(p).substring(1));
    return (p.substring(0, 1).toUpperCase() + p.substring(1));
  }

  protected String stripID(String p) {
    if (p.toLowerCase().startsWith(__idPrefix.toLowerCase())) {
      return p.substring(__idPrefix.length());
    }
    return p;
  }

  private String voInterfaceName(String tabName) {
    return __voInterfacePrefix + _prefix + tabName + __voInterfaceSuffix;
  }

  private String voImplName(String tabName) {
    return __voInterfacePrefix + _prefix + tabName + __voClassSuffix;
  }

  private String getPath(String pkg) {
    return _tablesDir + __sep + pkg + __sep + _prefix;
  }

  private String getSrcPath(String pkg) {
    if (_srcDir == null) {
      return null;
    } else {
      return _srcDir + __sep + pkg + __sep + _prefix;
    }
  }

  private static <T, U> void copy(List<T> dest, List<U> src, Converter<U, T> converter) {
    for (U u : src) {
      dest.add(converter.convert(u));
    }
  }

  private interface Converter<U, T> {
    T convert(U u);
  }
}
