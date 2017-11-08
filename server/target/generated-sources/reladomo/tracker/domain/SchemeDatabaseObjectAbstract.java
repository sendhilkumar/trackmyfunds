package tracker.domain;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gs.fw.common.mithra.*;
import com.gs.fw.common.mithra.attribute.*;
import com.gs.fw.common.mithra.util.*;
import com.gs.fw.common.mithra.notification.*;
import com.gs.fw.common.mithra.notification.listener.*;
import com.gs.fw.common.mithra.list.cursor.Cursor;
import com.gs.fw.common.mithra.bulkloader.*;
import java.util.*;
import java.sql.*;
import com.gs.fw.common.mithra.*;
import com.gs.fw.common.mithra.attribute.update.AttributeUpdateWrapper;
import com.gs.fw.common.mithra.bulkloader.BulkLoader;
import com.gs.fw.common.mithra.bulkloader.BulkLoaderException;
import com.gs.fw.common.mithra.cache.*;
import com.gs.fw.common.mithra.cache.offheap.*;
import com.gs.fw.common.mithra.connectionmanager.*;
import com.gs.fw.common.mithra.database.*;
import com.gs.fw.common.mithra.databasetype.*;
import com.gs.fw.common.mithra.finder.*;
import com.gs.fw.common.mithra.finder.orderby.OrderBy;
import com.gs.fw.common.mithra.finder.integer.IntegerResultSetParser;
import com.gs.fw.common.mithra.querycache.CachedQuery;
import com.gs.fw.common.mithra.remote.RemoteMithraService;
import com.gs.fw.common.mithra.transaction.BatchUpdateOperation;
import com.gs.fw.common.mithra.transaction.UpdateOperation;
/**
* This file was automatically generated using Mithra 16.6.1. Please do not modify it.
* Add custom logic to its subclass instead.
*/
public abstract class SchemeDatabaseObjectAbstract extends MithraAbstractTransactionalDatabaseObject implements MithraTransactionalDatabaseObject, MithraObjectFactory
{
	private SourcelessConnectionManager connectionManager;
	private SchemaManager schemaManager;
	private TablePartitionManager tablePartitionManager;
	private static final String COL_LIST_WITHOUT_PK = "name,amc,rta_code";
	private static final String COL_LIST_WITHOUT_PK_WITH_ALIAS = "t0.name,t0.amc,t0.rta_code";
	private static final String PK_WITH_ALIAS = "t0.code = ?";
	private static final String PK_INDEX_COLS = "code";
	protected SchemeDatabaseObjectAbstract()
	{
		super("Scheme", "tracker.domain.SchemeFinder",
			4, 4,
			COL_LIST_WITHOUT_PK, COL_LIST_WITHOUT_PK_WITH_ALIAS,
			false, false, false,
			PK_WITH_ALIAS,
			PK_INDEX_COLS);
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return SchemeFinder.getMithraObjectPortal();
	}

	public RelatedFinder getFinder()
	{
		return SchemeFinder.getFinderInstance();
	}

	public static SchemeData allocateOnHeapData()
	{
		return new SchemeData();
	}

	public static SchemeData allocateOffHeapData()
	{
		throw new RuntimeException("no off heap implementation");
	}

	public MithraDataObject deserializeFullData(ObjectInput in) throws IOException, ClassNotFoundException
	{
		MithraDataObject data = new SchemeData();
		data.zDeserializeFullData(in);
		return data;
	}

	public MithraObject deserializeForRefresh(ObjectInput in) throws IOException, ClassNotFoundException
	{
		SchemeData data = new SchemeData();
		data.zDeserializePrimaryKey(in);
		return this.createObject(data);
	}

	public Cache instantiateFullCache(MithraConfigurationManager.Config config)
	{
		Cache result;
		if (config.isParticipatingInTx())
		{
			result = new FullNonDatedTransactionalCache(SchemeFinder.getPrimaryKeyAttributes(), this, SchemeFinder.getImmutableAttributes());
		}
		else
		{
			result = new FullNonDatedCache(SchemeFinder.getPrimaryKeyAttributes(), this, SchemeFinder.getImmutableAttributes(), new NonTransactionalUnderlyingObjectGetter());
		}

		initPortal(result, config);
		return result;
	}

	public Cache instantiatePartialCache(MithraConfigurationManager.Config config)
	{
		Cache result;
		if (config.isParticipatingInTx())
		{
			result = new PartialNonDatedTransactionalCache(SchemeFinder.getPrimaryKeyAttributes(), this, SchemeFinder.getImmutableAttributes(), config.getCacheTimeToLive(), config.getRelationshipCacheTimeToLive());
		}
		else
		{
			result = new PartialNonDatedCache(SchemeFinder.getPrimaryKeyAttributes(), this, SchemeFinder.getImmutableAttributes(), new NonTransactionalUnderlyingObjectGetter(), config.getCacheTimeToLive(), config.getRelationshipCacheTimeToLive());
		}

		initPortal(result, config);
		return result;
	}

	private void initPortal(Cache cache, MithraConfigurationManager.Config config)
	{
		if (config.isThreeTierClient())
		{
			SchemeFinder.initializeClientPortal(this, cache, config);
		}
		else
		{
			SchemeFinder.initializePortal(this, cache, config);
		}

		if (config.isParticipatingInTx())
		{
			Scheme.zConfigFullTx();
		}
		else
		{
			Scheme.zConfigNonTx();
		}
	}

	public List getSimulatedSequenceInitValues()
	{
		return null;
	}

	public Object getSourceAttributeValueForSelectedObjectGeneric(SqlQuery query, int queryNumber)
	{
		return null;
	}

	public Object getSourceAttributeValueFromObjectGeneric(MithraDataObject object)
	{
		return null;
	}

	public Object getSourceAttributeValueGeneric(SqlQuery query, MapperStackImpl mapperStack, int queryNumber)
	{
		return null;
	}

	public String getDatabaseIdentifierGenericSource (Object source)
	{
		return connectionManager.getDatabaseIdentifier();
	}

	public DatabaseType getDatabaseTypeGenericSource(Object source)
	{
		return connectionManager.getDatabaseType();
	}

	public TimeZone getDatabaseTimeZoneGenericSource(Object source)
	{
		return getDatabaseTimeZone();
	}

	public Connection getConnectionGenericSource(Object source)
	{
		return connectionManagerWrapper.getConnection();
	}

	public BulkLoader createBulkLoaderGenericSource(Object source) throws BulkLoaderException 
	{
		return connectionManager.createBulkLoader();
	}

	public MithraDataObject inflateDataGenericSource(ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		return inflateSchemeData(rs, dt);
	}

	public void inflateNonPkDataGenericSource(MithraDataObject data, ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		inflateNonPkSchemeData(1, (SchemeData) data, rs, dt);
	}

	public MithraDataObject inflatePkDataGenericSource(ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		return inflateSchemePkData(rs, dt);
	}

	public String getSchemaGenericSource(Object source)
	{
		if (this.schemaManager != null)
		{
			return this.schemaManager.getSchema(this.getDefaultSchema());
		}

		return this.getDefaultSchema();
	}

	public String getTableNameGenericSource(Object source) throws MithraDatabaseException
	{
		return getSchemeTableName();
	}

	public String getSchemeTableName() throws MithraDatabaseException
	{
		if (this.tablePartitionManager != null)
		{
			return this.tablePartitionManager.getTableName(this.getDefaultTableName());
		}

		return this.getDefaultTableName();
	}

	public void setPrimaryKeyAttributes(PreparedStatement stm, int pos, MithraDataObject dataObj,
		TimeZone databaseTimeZone, DatabaseType dt) throws SQLException
	{
		SchemeData data = (SchemeData)dataObj;
		TimeZone conversionTimeZone = null;
		stm.setInt(pos++, data.getCode());
	}

	public int setPrimaryKeyAttributesWithoutOptimistic(PreparedStatement stm, int pos, MithraDataObject dataObj,
		TimeZone databaseTimeZone, DatabaseType dt) throws SQLException
	{
		this.setPrimaryKeyAttributes(stm, pos, dataObj, databaseTimeZone, dt);
		return -1;
	}

	public String getPrimaryKeyWhereSql()
	{
		return "code = ?";
	}

	public String getPrimaryKeyWhereSqlWithNullableAttribute(MithraDataObject dataObj)
	{
		return "";
	}

	public String getPrimaryKeyWhereSqlWithNullableAttributeWithDefaultAlias(MithraDataObject dataObj)
	{
		return "";
	}

	public String getColumnListWithPk(String databaseAlias)
	{
		if (databaseAlias.equals(SqlQuery.DEFAULT_DATABASE_ALIAS))
		{
			return "t0.code,t0.name,t0.amc,t0.rta_code";
		}

		StringBuffer result = new StringBuffer((databaseAlias.length()+15)*4);
		result.append(databaseAlias).append(".").append("code");
		result.append(",").append(databaseAlias).append(".").append("name");
		result.append(",").append(databaseAlias).append(".").append("amc");
		result.append(",").append(databaseAlias).append(".").append("rta_code");
		return result.toString();
	}

	public Object getConnectionManager()
	{
		return connectionManager;
	}

	public void setConnectionManager(Object connectionManager, ConnectionManagerWrapper wrapper)
	{
		this.connectionManager = (SourcelessConnectionManager)connectionManager;
		this.connectionManagerWrapper = wrapper;
	}

	public SchemeData inflateSchemeData(ResultSet rs, DatabaseType dt)
	throws SQLException
	{
		SchemeData data = inflateSchemePkData(rs, dt);
		inflateNonPkSchemeData(2, data, rs, dt);
		return data;
	}

	public SchemeData inflateSchemePkData(ResultSet _rs, DatabaseType _dt)
	throws SQLException
	{
		SchemeData _data = new SchemeData();
		int _pos = 1;
		_data.setCode(_rs.getInt(_pos++));
		checkNullPrimitive(_rs, _data, "code");
		return _data;
	}

	public void inflateNonPkSchemeData(int _pos, SchemeData _datax, ResultSet _rs, DatabaseType _dt)
	throws SQLException
	{
		{
			SchemeData _data = _datax;
			_data.setName(trimString(_rs.getString(_pos++)));
			_data.setAmc(trimString(_rs.getString(_pos++)));
			_data.setRtaCode(trimString(_rs.getString(_pos++)));
		}
	}

	public DatabaseType getDatabaseType()
	{
		return connectionManager.getDatabaseType();
	}

	public TimeZone getDatabaseTimeZone()
	{
		return connectionManager.getDatabaseTimeZone();
	}

	protected String getSchema()
	{
		return this.getSchemaGenericSource(null);
	}

	public void setSchemaManager(Object schemaManager)
	{
		if( schemaManager instanceof SchemaManager )
		{
			this.schemaManager = (SchemaManager) schemaManager;
		}
		else
		{
			throw new IllegalArgumentException( "Schema manager class " + schemaManager.getClass().getName()
			+ " does not implement SchemaManager.class" );
		}
	}

	public void setTablePartitionManager(Object tablePartitionManager)
	{
		if( tablePartitionManager instanceof TablePartitionManager )
		{
			this.tablePartitionManager = (TablePartitionManager) tablePartitionManager;
		}
		else
		{
			throw new IllegalArgumentException( "Table partition manager class " + tablePartitionManager.getClass().getName()
			+ " does not implement TablePartitionManager.class" );
		}
	}

	public String getTableName()
	{
		return this.getDefaultTableName();
	}

	public String getDefaultTableName()
	{
		return "scheme";
	}

	public void setInsertAttributes(PreparedStatement stm, MithraDataObject dataObj,
		TimeZone databaseTimeZone, int pos, DatabaseType dt) throws SQLException
	{
		SchemeData data = (SchemeData)dataObj;
		TimeZone conversionTimeZone = null;
		stm.setInt(pos++, data.getCode());
		if(data.isNameNull())
		{
			throwNullAttribute("name");
		}

		stm.setString(pos++, data.getName());
		if(data.isAmcNull())
		{
			throwNullAttribute("amc");
		}

		stm.setString(pos++, data.getAmc());
		if (data.isRtaCodeNull())
		{
			stm.setNull(pos++, java.sql.Types.VARCHAR);
		}
		else
		{
			stm.setString(pos++, data.getRtaCode());
		}
	}

	public String getInsertFields()
	{
		return "code,name,amc,rta_code";
	}

	public String getInsertQuestionMarks()
	{
		return "?,?,?,?";
	}

	public String getOptimisticLockingWhereSql()
	{
		return "";
	}

	public MithraObject createObject(MithraDataObject newData)
	{
		Scheme newObject = new Scheme();
		newObject.zSetFromSchemeData((SchemeData) newData);
		return newObject;
	}

	public String getPkColumnList(String databaseAlias)
	{
		if (databaseAlias.equals(SqlQuery.DEFAULT_DATABASE_ALIAS))
		{
			return "t0.code";
		}

		StringBuffer result = new StringBuffer((databaseAlias.length()+15)*4);
		result.append(databaseAlias);
		result.append(".");
		result.append("code");
		return result.toString();
	}
}
