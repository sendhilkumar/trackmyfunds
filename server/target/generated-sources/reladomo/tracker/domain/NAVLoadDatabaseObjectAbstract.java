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
public abstract class NAVLoadDatabaseObjectAbstract extends MithraAbstractTransactionalDatabaseObject implements MithraTransactionalDatabaseObject, MithraObjectFactory
{
	private SourcelessConnectionManager connectionManager;
	private SchemaManager schemaManager;
	private TablePartitionManager tablePartitionManager;
	private static final String COL_LIST_WITHOUT_PK = "load_time,latest_nav_date,max_occuring_date";
	private static final String COL_LIST_WITHOUT_PK_WITH_ALIAS = "t0.load_time,t0.latest_nav_date,t0.max_occuring_date";
	private static final String PK_WITH_ALIAS = "t0.id = ?";
	private static final String PK_INDEX_COLS = "id";
	protected NAVLoadDatabaseObjectAbstract()
	{
		super("NAVLoad", "tracker.domain.NAVLoadFinder",
			4, 4,
			COL_LIST_WITHOUT_PK, COL_LIST_WITHOUT_PK_WITH_ALIAS,
			false, false, false,
			PK_WITH_ALIAS,
			PK_INDEX_COLS);
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return NAVLoadFinder.getMithraObjectPortal();
	}

	public RelatedFinder getFinder()
	{
		return NAVLoadFinder.getFinderInstance();
	}

	public static NAVLoadData allocateOnHeapData()
	{
		return new NAVLoadData();
	}

	public static NAVLoadData allocateOffHeapData()
	{
		throw new RuntimeException("no off heap implementation");
	}

	public MithraDataObject deserializeFullData(ObjectInput in) throws IOException, ClassNotFoundException
	{
		MithraDataObject data = new NAVLoadData();
		data.zDeserializeFullData(in);
		return data;
	}

	public MithraObject deserializeForRefresh(ObjectInput in) throws IOException, ClassNotFoundException
	{
		NAVLoadData data = new NAVLoadData();
		data.zDeserializePrimaryKey(in);
		return this.createObject(data);
	}

	public Cache instantiateFullCache(MithraConfigurationManager.Config config)
	{
		Cache result;
		if (config.isParticipatingInTx())
		{
			result = new FullNonDatedTransactionalCache(NAVLoadFinder.getPrimaryKeyAttributes(), this, NAVLoadFinder.getImmutableAttributes());
		}
		else
		{
			result = new FullNonDatedCache(NAVLoadFinder.getPrimaryKeyAttributes(), this, NAVLoadFinder.getImmutableAttributes(), new NonTransactionalUnderlyingObjectGetter());
		}

		initPortal(result, config);
		return result;
	}

	public Cache instantiatePartialCache(MithraConfigurationManager.Config config)
	{
		Cache result;
		if (config.isParticipatingInTx())
		{
			result = new PartialNonDatedTransactionalCache(NAVLoadFinder.getPrimaryKeyAttributes(), this, NAVLoadFinder.getImmutableAttributes(), config.getCacheTimeToLive(), config.getRelationshipCacheTimeToLive());
		}
		else
		{
			result = new PartialNonDatedCache(NAVLoadFinder.getPrimaryKeyAttributes(), this, NAVLoadFinder.getImmutableAttributes(), new NonTransactionalUnderlyingObjectGetter(), config.getCacheTimeToLive(), config.getRelationshipCacheTimeToLive());
		}

		initPortal(result, config);
		return result;
	}

	private void initPortal(Cache cache, MithraConfigurationManager.Config config)
	{
		if (config.isThreeTierClient())
		{
			NAVLoadFinder.initializeClientPortal(this, cache, config);
		}
		else
		{
			NAVLoadFinder.initializePortal(this, cache, config);
		}

		if (config.isParticipatingInTx())
		{
			NAVLoad.zConfigFullTx();
		}
		else
		{
			NAVLoad.zConfigNonTx();
		}
	}

	public List getSimulatedSequenceInitValues()
	{
		ArrayList simulatedSequenceInitValues = new ArrayList(1);
		Attribute[] primaryKeyAttributes = NAVLoadFinder.getPrimaryKeyAttributes();
		SimulatedSequenceInitValues initValues = null;
		return simulatedSequenceInitValues;
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
		return inflateNAVLoadData(rs, dt);
	}

	public void inflateNonPkDataGenericSource(MithraDataObject data, ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		inflateNonPkNAVLoadData(1, (NAVLoadData) data, rs, dt);
	}

	public MithraDataObject inflatePkDataGenericSource(ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		return inflateNAVLoadPkData(rs, dt);
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
		return getNAVLoadTableName();
	}

	public String getNAVLoadTableName() throws MithraDatabaseException
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
		NAVLoadData data = (NAVLoadData)dataObj;
		TimeZone conversionTimeZone = null;
		stm.setInt(pos++, data.getId());
	}

	public int setPrimaryKeyAttributesWithoutOptimistic(PreparedStatement stm, int pos, MithraDataObject dataObj,
		TimeZone databaseTimeZone, DatabaseType dt) throws SQLException
	{
		this.setPrimaryKeyAttributes(stm, pos, dataObj, databaseTimeZone, dt);
		return -1;
	}

	public String getPrimaryKeyWhereSql()
	{
		return "id = ?";
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
			return "t0.id,t0.load_time,t0.latest_nav_date,t0.max_occuring_date";
		}

		StringBuffer result = new StringBuffer((databaseAlias.length()+15)*4);
		result.append(databaseAlias).append(".").append("id");
		result.append(",").append(databaseAlias).append(".").append("load_time");
		result.append(",").append(databaseAlias).append(".").append("latest_nav_date");
		result.append(",").append(databaseAlias).append(".").append("max_occuring_date");
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

	public NAVLoadData inflateNAVLoadData(ResultSet rs, DatabaseType dt)
	throws SQLException
	{
		NAVLoadData data = inflateNAVLoadPkData(rs, dt);
		inflateNonPkNAVLoadData(2, data, rs, dt);
		return data;
	}

	public NAVLoadData inflateNAVLoadPkData(ResultSet _rs, DatabaseType _dt)
	throws SQLException
	{
		NAVLoadData _data = new NAVLoadData();
		int _pos = 1;
		_data.setId(_rs.getInt(_pos++));
		checkNullPrimitive(_rs, _data, "id");
		return _data;
	}

	public void inflateNonPkNAVLoadData(int _pos, NAVLoadData _datax, ResultSet _rs, DatabaseType _dt)
	throws SQLException
	{
		{
			NAVLoadData _data = _datax;
			Timestamp loadTimetimestampValue = _dt.getTimestampFromResultSet(_rs, _pos++, MithraTimestamp.DefaultTimeZone);
			_data.setLoadTime(loadTimetimestampValue);
			Timestamp latestNavDatetimestampValue = _dt.getTimestampFromResultSet(_rs, _pos++, MithraTimestamp.DefaultTimeZone);
			_data.setLatestNavDate(latestNavDatetimestampValue);
			Timestamp maxOccuringDatetimestampValue = _dt.getTimestampFromResultSet(_rs, _pos++, MithraTimestamp.DefaultTimeZone);
			_data.setMaxOccuringDate(maxOccuringDatetimestampValue);
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
		return "nav_load";
	}

	public void setInsertAttributes(PreparedStatement stm, MithraDataObject dataObj,
		TimeZone databaseTimeZone, int pos, DatabaseType dt) throws SQLException
	{
		NAVLoadData data = (NAVLoadData)dataObj;
		TimeZone conversionTimeZone = null;
		stm.setInt(pos++, data.getId());
		if (data.isLoadTimeNull())
		{
			stm.setNull(pos++, java.sql.Types.TIMESTAMP);
		}
		else
		{
			conversionTimeZone = MithraTimestamp.DefaultTimeZone;
			dt.setTimestamp(stm, pos, data.getLoadTime(), false, conversionTimeZone);
			pos++;
		}

		if (data.isLatestNavDateNull())
		{
			stm.setNull(pos++, java.sql.Types.TIMESTAMP);
		}
		else
		{
			conversionTimeZone = MithraTimestamp.DefaultTimeZone;
			dt.setTimestamp(stm, pos, data.getLatestNavDate(), false, conversionTimeZone);
			pos++;
		}

		if (data.isMaxOccuringDateNull())
		{
			stm.setNull(pos++, java.sql.Types.TIMESTAMP);
		}
		else
		{
			conversionTimeZone = MithraTimestamp.DefaultTimeZone;
			dt.setTimestamp(stm, pos, data.getMaxOccuringDate(), false, conversionTimeZone);
			pos++;
		}
	}

	public String getInsertFields()
	{
		return "id,load_time,latest_nav_date,max_occuring_date";
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
		NAVLoad newObject = new NAVLoad();
		newObject.zSetFromNAVLoadData((NAVLoadData) newData);
		return newObject;
	}

	public String getPkColumnList(String databaseAlias)
	{
		if (databaseAlias.equals(SqlQuery.DEFAULT_DATABASE_ALIAS))
		{
			return "t0.id";
		}

		StringBuffer result = new StringBuffer((databaseAlias.length()+15)*4);
		result.append(databaseAlias);
		result.append(".");
		result.append("id");
		return result.toString();
	}
}
