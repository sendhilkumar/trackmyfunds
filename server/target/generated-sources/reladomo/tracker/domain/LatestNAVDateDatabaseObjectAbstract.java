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
public abstract class LatestNAVDateDatabaseObjectAbstract extends MithraAbstractTransactionalDatabaseObject implements MithraTransactionalDatabaseObject, MithraObjectFactory
{
	private SourcelessConnectionManager connectionManager;
	private SchemaManager schemaManager;
	private TablePartitionManager tablePartitionManager;
	private static final String COL_LIST_WITHOUT_PK = "";
	private static final String COL_LIST_WITHOUT_PK_WITH_ALIAS = "";
	private static final String PK_WITH_ALIAS = "t0.date = ?";
	private static final String PK_INDEX_COLS = "date";
	protected LatestNAVDateDatabaseObjectAbstract()
	{
		super("LatestNAVDate", "tracker.domain.LatestNAVDateFinder",
			1, 1,
			COL_LIST_WITHOUT_PK, COL_LIST_WITHOUT_PK_WITH_ALIAS,
			false, false, false,
			PK_WITH_ALIAS,
			PK_INDEX_COLS);
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return LatestNAVDateFinder.getMithraObjectPortal();
	}

	public RelatedFinder getFinder()
	{
		return LatestNAVDateFinder.getFinderInstance();
	}

	public static LatestNAVDateData allocateOnHeapData()
	{
		return new LatestNAVDateData();
	}

	public static LatestNAVDateData allocateOffHeapData()
	{
		throw new RuntimeException("no off heap implementation");
	}

	public MithraDataObject deserializeFullData(ObjectInput in) throws IOException, ClassNotFoundException
	{
		MithraDataObject data = new LatestNAVDateData();
		data.zDeserializeFullData(in);
		return data;
	}

	public MithraObject deserializeForRefresh(ObjectInput in) throws IOException, ClassNotFoundException
	{
		LatestNAVDateData data = new LatestNAVDateData();
		data.zDeserializePrimaryKey(in);
		return this.createObject(data);
	}

	public Cache instantiateFullCache(MithraConfigurationManager.Config config)
	{
		Cache result;
		if (config.isParticipatingInTx())
		{
			result = new FullNonDatedTransactionalCache(LatestNAVDateFinder.getPrimaryKeyAttributes(), this, LatestNAVDateFinder.getImmutableAttributes());
		}
		else
		{
			result = new FullNonDatedCache(LatestNAVDateFinder.getPrimaryKeyAttributes(), this, LatestNAVDateFinder.getImmutableAttributes(), new NonTransactionalUnderlyingObjectGetter());
		}

		initPortal(result, config);
		return result;
	}

	public Cache instantiatePartialCache(MithraConfigurationManager.Config config)
	{
		Cache result;
		if (config.isParticipatingInTx())
		{
			result = new PartialNonDatedTransactionalCache(LatestNAVDateFinder.getPrimaryKeyAttributes(), this, LatestNAVDateFinder.getImmutableAttributes(), config.getCacheTimeToLive(), config.getRelationshipCacheTimeToLive());
		}
		else
		{
			result = new PartialNonDatedCache(LatestNAVDateFinder.getPrimaryKeyAttributes(), this, LatestNAVDateFinder.getImmutableAttributes(), new NonTransactionalUnderlyingObjectGetter(), config.getCacheTimeToLive(), config.getRelationshipCacheTimeToLive());
		}

		initPortal(result, config);
		return result;
	}

	private void initPortal(Cache cache, MithraConfigurationManager.Config config)
	{
		if (config.isThreeTierClient())
		{
			LatestNAVDateFinder.initializeClientPortal(this, cache, config);
		}
		else
		{
			LatestNAVDateFinder.initializePortal(this, cache, config);
		}

		if (config.isParticipatingInTx())
		{
			LatestNAVDate.zConfigFullTx();
		}
		else
		{
			LatestNAVDate.zConfigNonTx();
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
		return inflateLatestNAVDateData(rs, dt);
	}

	public void inflateNonPkDataGenericSource(MithraDataObject data, ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		inflateNonPkLatestNAVDateData(1, (LatestNAVDateData) data, rs, dt);
	}

	public MithraDataObject inflatePkDataGenericSource(ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		return inflateLatestNAVDatePkData(rs, dt);
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
		return getLatestNAVDateTableName();
	}

	public String getLatestNAVDateTableName() throws MithraDatabaseException
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
		LatestNAVDateData data = (LatestNAVDateData)dataObj;
		TimeZone conversionTimeZone = null;
		conversionTimeZone = MithraTimestamp.DefaultTimeZone;
		dt.setTimestamp(stm, pos, data.getDate(), false, conversionTimeZone);
		pos++;
	}

	public int setPrimaryKeyAttributesWithoutOptimistic(PreparedStatement stm, int pos, MithraDataObject dataObj,
		TimeZone databaseTimeZone, DatabaseType dt) throws SQLException
	{
		this.setPrimaryKeyAttributes(stm, pos, dataObj, databaseTimeZone, dt);
		return -1;
	}

	public String getPrimaryKeyWhereSql()
	{
		return "date = ?";
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
			return "t0.date";
		}

		StringBuffer result = new StringBuffer((databaseAlias.length()+15)*1);
		result.append(databaseAlias).append(".").append("date");
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

	public LatestNAVDateData inflateLatestNAVDateData(ResultSet rs, DatabaseType dt)
	throws SQLException
	{
		LatestNAVDateData data = inflateLatestNAVDatePkData(rs, dt);
		inflateNonPkLatestNAVDateData(2, data, rs, dt);
		return data;
	}

	public LatestNAVDateData inflateLatestNAVDatePkData(ResultSet _rs, DatabaseType _dt)
	throws SQLException
	{
		LatestNAVDateData _data = new LatestNAVDateData();
		int _pos = 1;
		Timestamp datetimestampValue = _dt.getTimestampFromResultSet(_rs, _pos++, MithraTimestamp.DefaultTimeZone);
		_data.setDate(datetimestampValue);
		return _data;
	}

	public void inflateNonPkLatestNAVDateData(int _pos, LatestNAVDateData _datax, ResultSet _rs, DatabaseType _dt)
	throws SQLException
	{
		{
			LatestNAVDateData _data = _datax;
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
		return "latest_nav_date";
	}

	public void setInsertAttributes(PreparedStatement stm, MithraDataObject dataObj,
		TimeZone databaseTimeZone, int pos, DatabaseType dt) throws SQLException
	{
		LatestNAVDateData data = (LatestNAVDateData)dataObj;
		TimeZone conversionTimeZone = null;
		if(data.isDateNull())
		{
			throwNullAttribute("date");
		}

		conversionTimeZone = MithraTimestamp.DefaultTimeZone;
		dt.setTimestamp(stm, pos, data.getDate(), false, conversionTimeZone);
		pos++;
	}

	public String getInsertFields()
	{
		return "date";
	}

	public String getInsertQuestionMarks()
	{
		return "?";
	}

	public String getOptimisticLockingWhereSql()
	{
		return "";
	}

	public MithraObject createObject(MithraDataObject newData)
	{
		LatestNAVDate newObject = new LatestNAVDate();
		newObject.zSetFromLatestNAVDateData((LatestNAVDateData) newData);
		return newObject;
	}

	public String getPkColumnList(String databaseAlias)
	{
		if (databaseAlias.equals(SqlQuery.DEFAULT_DATABASE_ALIAS))
		{
			return "t0.date";
		}

		StringBuffer result = new StringBuffer((databaseAlias.length()+15)*1);
		result.append(databaseAlias);
		result.append(".");
		result.append("date");
		return result.toString();
	}
}
