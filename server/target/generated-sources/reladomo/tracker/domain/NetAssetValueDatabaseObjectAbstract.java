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
public abstract class NetAssetValueDatabaseObjectAbstract extends MithraAbstractTransactionalDatabaseObject implements MithraTransactionalDatabaseObject, MithraObjectFactory
{
	private SourcelessConnectionManager connectionManager;
	private SchemaManager schemaManager;
	private TablePartitionManager tablePartitionManager;
	private static final String COL_LIST_WITHOUT_PK = "net_asset_value,repurchase_price,sale_price,load_id";
	private static final String COL_LIST_WITHOUT_PK_WITH_ALIAS = "t0.net_asset_value,t0.repurchase_price,t0.sale_price,t0.load_id";
	private static final String PK_WITH_ALIAS = "t0.scheme_code = ? AND t0.date = ?";
	private static final String PK_INDEX_COLS = "scheme_code,date";
	protected NetAssetValueDatabaseObjectAbstract()
	{
		super("NetAssetValue", "tracker.domain.NetAssetValueFinder",
			6, 6,
			COL_LIST_WITHOUT_PK, COL_LIST_WITHOUT_PK_WITH_ALIAS,
			false, false, false,
			PK_WITH_ALIAS,
			PK_INDEX_COLS);
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return NetAssetValueFinder.getMithraObjectPortal();
	}

	public RelatedFinder getFinder()
	{
		return NetAssetValueFinder.getFinderInstance();
	}

	public static NetAssetValueData allocateOnHeapData()
	{
		return new NetAssetValueData();
	}

	public static NetAssetValueData allocateOffHeapData()
	{
		throw new RuntimeException("no off heap implementation");
	}

	public MithraDataObject deserializeFullData(ObjectInput in) throws IOException, ClassNotFoundException
	{
		MithraDataObject data = new NetAssetValueData();
		data.zDeserializeFullData(in);
		return data;
	}

	public MithraObject deserializeForRefresh(ObjectInput in) throws IOException, ClassNotFoundException
	{
		NetAssetValueData data = new NetAssetValueData();
		data.zDeserializePrimaryKey(in);
		return this.createObject(data);
	}

	public Cache instantiateFullCache(MithraConfigurationManager.Config config)
	{
		Cache result;
		if (config.isParticipatingInTx())
		{
			result = new FullNonDatedTransactionalCache(NetAssetValueFinder.getPrimaryKeyAttributes(), this, NetAssetValueFinder.getImmutableAttributes());
		}
		else
		{
			result = new FullNonDatedCache(NetAssetValueFinder.getPrimaryKeyAttributes(), this, NetAssetValueFinder.getImmutableAttributes(), new NonTransactionalUnderlyingObjectGetter());
		}

		initPortal(result, config);
		return result;
	}

	public Cache instantiatePartialCache(MithraConfigurationManager.Config config)
	{
		Cache result;
		if (config.isParticipatingInTx())
		{
			result = new PartialNonDatedTransactionalCache(NetAssetValueFinder.getPrimaryKeyAttributes(), this, NetAssetValueFinder.getImmutableAttributes(), config.getCacheTimeToLive(), config.getRelationshipCacheTimeToLive());
		}
		else
		{
			result = new PartialNonDatedCache(NetAssetValueFinder.getPrimaryKeyAttributes(), this, NetAssetValueFinder.getImmutableAttributes(), new NonTransactionalUnderlyingObjectGetter(), config.getCacheTimeToLive(), config.getRelationshipCacheTimeToLive());
		}

		initPortal(result, config);
		return result;
	}

	private void initPortal(Cache cache, MithraConfigurationManager.Config config)
	{
		if (config.isThreeTierClient())
		{
			NetAssetValueFinder.initializeClientPortal(this, cache, config);
		}
		else
		{
			NetAssetValueFinder.initializePortal(this, cache, config);
		}

		if (config.isParticipatingInTx())
		{
			NetAssetValue.zConfigFullTx();
		}
		else
		{
			NetAssetValue.zConfigNonTx();
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
		return inflateNetAssetValueData(rs, dt);
	}

	public void inflateNonPkDataGenericSource(MithraDataObject data, ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		inflateNonPkNetAssetValueData(1, (NetAssetValueData) data, rs, dt);
	}

	public MithraDataObject inflatePkDataGenericSource(ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		return inflateNetAssetValuePkData(rs, dt);
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
		return getNetAssetValueTableName();
	}

	public String getNetAssetValueTableName() throws MithraDatabaseException
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
		NetAssetValueData data = (NetAssetValueData)dataObj;
		TimeZone conversionTimeZone = null;
		stm.setInt(pos++, data.getSchemeCode());
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
		return "scheme_code = ? AND date = ?";
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
			return "t0.scheme_code,t0.date,t0.net_asset_value,t0.repurchase_price,t0.sale_price,t0.load_id";
		}

		StringBuffer result = new StringBuffer((databaseAlias.length()+15)*6);
		result.append(databaseAlias).append(".").append("scheme_code");
		result.append(",").append(databaseAlias).append(".").append("date");
		result.append(",").append(databaseAlias).append(".").append("net_asset_value");
		result.append(",").append(databaseAlias).append(".").append("repurchase_price");
		result.append(",").append(databaseAlias).append(".").append("sale_price");
		result.append(",").append(databaseAlias).append(".").append("load_id");
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

	public NetAssetValueData inflateNetAssetValueData(ResultSet rs, DatabaseType dt)
	throws SQLException
	{
		NetAssetValueData data = inflateNetAssetValuePkData(rs, dt);
		inflateNonPkNetAssetValueData(3, data, rs, dt);
		return data;
	}

	public NetAssetValueData inflateNetAssetValuePkData(ResultSet _rs, DatabaseType _dt)
	throws SQLException
	{
		NetAssetValueData _data = new NetAssetValueData();
		int _pos = 1;
		_data.setSchemeCode(_rs.getInt(_pos++));
		checkNullPrimitive(_rs, _data, "schemeCode");
		Timestamp datetimestampValue = _dt.getTimestampFromResultSet(_rs, _pos++, MithraTimestamp.DefaultTimeZone);
		_data.setDate(datetimestampValue);
		return _data;
	}

	public void inflateNonPkNetAssetValueData(int _pos, NetAssetValueData _datax, ResultSet _rs, DatabaseType _dt)
	throws SQLException
	{
		{
			NetAssetValueData _data = _datax;
			_data.setNetAssetValue(_rs.getDouble(_pos++));
			if (_rs.wasNull())
			{
				_data.setNetAssetValueNull();
			}

			_data.setRepurchasePrice(_rs.getDouble(_pos++));
			if (_rs.wasNull())
			{
				_data.setRepurchasePriceNull();
			}

			_data.setSalePrice(_rs.getDouble(_pos++));
			if (_rs.wasNull())
			{
				_data.setSalePriceNull();
			}

			_data.setLoadId(_rs.getInt(_pos++));
			if (_rs.wasNull())
			{
				_data.setLoadIdNull();
			}
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
		return "net_asset_value";
	}

	public void setInsertAttributes(PreparedStatement stm, MithraDataObject dataObj,
		TimeZone databaseTimeZone, int pos, DatabaseType dt) throws SQLException
	{
		NetAssetValueData data = (NetAssetValueData)dataObj;
		TimeZone conversionTimeZone = null;
		stm.setInt(pos++, data.getSchemeCode());
		if(data.isDateNull())
		{
			throwNullAttribute("date");
		}

		conversionTimeZone = MithraTimestamp.DefaultTimeZone;
		dt.setTimestamp(stm, pos, data.getDate(), false, conversionTimeZone);
		pos++;
		if (data.isNetAssetValueNull())
		{
			stm.setNull(pos++, java.sql.Types.DOUBLE);
		}
		else
		{
			stm.setDouble(pos++, data.getNetAssetValue());
		}

		if (data.isRepurchasePriceNull())
		{
			stm.setNull(pos++, java.sql.Types.DOUBLE);
		}
		else
		{
			stm.setDouble(pos++, data.getRepurchasePrice());
		}

		if (data.isSalePriceNull())
		{
			stm.setNull(pos++, java.sql.Types.DOUBLE);
		}
		else
		{
			stm.setDouble(pos++, data.getSalePrice());
		}

		if (data.isLoadIdNull())
		{
			stm.setNull(pos++, java.sql.Types.INTEGER);
		}
		else
		{
			stm.setInt(pos++, data.getLoadId());
		}
	}

	public String getInsertFields()
	{
		return "scheme_code,date,net_asset_value,repurchase_price,sale_price,load_id";
	}

	public String getInsertQuestionMarks()
	{
		return "?,?,?,?,?,?";
	}

	public String getOptimisticLockingWhereSql()
	{
		return "";
	}

	public MithraObject createObject(MithraDataObject newData)
	{
		NetAssetValue newObject = new NetAssetValue();
		newObject.zSetFromNetAssetValueData((NetAssetValueData) newData);
		return newObject;
	}

	public String getPkColumnList(String databaseAlias)
	{
		if (databaseAlias.equals(SqlQuery.DEFAULT_DATABASE_ALIAS))
		{
			return "t0.scheme_code,t0.date";
		}

		StringBuffer result = new StringBuffer((databaseAlias.length()+15)*6);
		result.append(databaseAlias);
		result.append(".");
		result.append("scheme_code");
		result.append(",");
		result.append(databaseAlias);
		result.append(".");
		result.append("date");
		return result.toString();
	}
}
