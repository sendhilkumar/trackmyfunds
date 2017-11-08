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
public abstract class PortfolioHoldingDatabaseObjectAbstract extends MithraAbstractTransactionalDatabaseObject implements MithraTransactionalDatabaseObject, MithraObjectFactory
{
	private SourcelessConnectionManager connectionManager;
	private SchemaManager schemaManager;
	private TablePartitionManager tablePartitionManager;
	private static final String COL_LIST_WITHOUT_PK = "";
	private static final String COL_LIST_WITHOUT_PK_WITH_ALIAS = "";
	private static final String PK_WITH_ALIAS = "t0.portfolio_id = ? AND t0.scheme_code = ?";
	private static final String PK_INDEX_COLS = "portfolio_id,scheme_code";
	protected PortfolioHoldingDatabaseObjectAbstract()
	{
		super("PortfolioHolding", "tracker.domain.PortfolioHoldingFinder",
			2, 2,
			COL_LIST_WITHOUT_PK, COL_LIST_WITHOUT_PK_WITH_ALIAS,
			false, false, false,
			PK_WITH_ALIAS,
			PK_INDEX_COLS);
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return PortfolioHoldingFinder.getMithraObjectPortal();
	}

	public RelatedFinder getFinder()
	{
		return PortfolioHoldingFinder.getFinderInstance();
	}

	public static PortfolioHoldingData allocateOnHeapData()
	{
		return new PortfolioHoldingData();
	}

	public static PortfolioHoldingData allocateOffHeapData()
	{
		throw new RuntimeException("no off heap implementation");
	}

	public MithraDataObject deserializeFullData(ObjectInput in) throws IOException, ClassNotFoundException
	{
		MithraDataObject data = new PortfolioHoldingData();
		data.zDeserializeFullData(in);
		return data;
	}

	public MithraObject deserializeForRefresh(ObjectInput in) throws IOException, ClassNotFoundException
	{
		PortfolioHoldingData data = new PortfolioHoldingData();
		data.zDeserializePrimaryKey(in);
		return this.createObject(data);
	}

	public Cache instantiateFullCache(MithraConfigurationManager.Config config)
	{
		Cache result;
		if (config.isParticipatingInTx())
		{
			result = new FullNonDatedTransactionalCache(PortfolioHoldingFinder.getPrimaryKeyAttributes(), this, PortfolioHoldingFinder.getImmutableAttributes());
		}
		else
		{
			result = new FullNonDatedCache(PortfolioHoldingFinder.getPrimaryKeyAttributes(), this, PortfolioHoldingFinder.getImmutableAttributes(), new NonTransactionalUnderlyingObjectGetter());
		}

		initPortal(result, config);
		return result;
	}

	public Cache instantiatePartialCache(MithraConfigurationManager.Config config)
	{
		Cache result;
		if (config.isParticipatingInTx())
		{
			result = new PartialNonDatedTransactionalCache(PortfolioHoldingFinder.getPrimaryKeyAttributes(), this, PortfolioHoldingFinder.getImmutableAttributes(), config.getCacheTimeToLive(), config.getRelationshipCacheTimeToLive());
		}
		else
		{
			result = new PartialNonDatedCache(PortfolioHoldingFinder.getPrimaryKeyAttributes(), this, PortfolioHoldingFinder.getImmutableAttributes(), new NonTransactionalUnderlyingObjectGetter(), config.getCacheTimeToLive(), config.getRelationshipCacheTimeToLive());
		}

		initPortal(result, config);
		return result;
	}

	private void initPortal(Cache cache, MithraConfigurationManager.Config config)
	{
		if (config.isThreeTierClient())
		{
			PortfolioHoldingFinder.initializeClientPortal(this, cache, config);
		}
		else
		{
			PortfolioHoldingFinder.initializePortal(this, cache, config);
		}

		if (config.isParticipatingInTx())
		{
			PortfolioHolding.zConfigFullTx();
		}
		else
		{
			PortfolioHolding.zConfigNonTx();
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
		return inflatePortfolioHoldingData(rs, dt);
	}

	public void inflateNonPkDataGenericSource(MithraDataObject data, ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		inflateNonPkPortfolioHoldingData(1, (PortfolioHoldingData) data, rs, dt);
	}

	public MithraDataObject inflatePkDataGenericSource(ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		return inflatePortfolioHoldingPkData(rs, dt);
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
		return getPortfolioHoldingTableName();
	}

	public String getPortfolioHoldingTableName() throws MithraDatabaseException
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
		PortfolioHoldingData data = (PortfolioHoldingData)dataObj;
		TimeZone conversionTimeZone = null;
		stm.setInt(pos++, data.getPortfolioId());
		stm.setInt(pos++, data.getSchemeCode());
	}

	public int setPrimaryKeyAttributesWithoutOptimistic(PreparedStatement stm, int pos, MithraDataObject dataObj,
		TimeZone databaseTimeZone, DatabaseType dt) throws SQLException
	{
		this.setPrimaryKeyAttributes(stm, pos, dataObj, databaseTimeZone, dt);
		return -1;
	}

	public String getPrimaryKeyWhereSql()
	{
		return "portfolio_id = ? AND scheme_code = ?";
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
			return "t0.portfolio_id,t0.scheme_code";
		}

		StringBuffer result = new StringBuffer((databaseAlias.length()+15)*2);
		result.append(databaseAlias).append(".").append("portfolio_id");
		result.append(",").append(databaseAlias).append(".").append("scheme_code");
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

	public PortfolioHoldingData inflatePortfolioHoldingData(ResultSet rs, DatabaseType dt)
	throws SQLException
	{
		PortfolioHoldingData data = inflatePortfolioHoldingPkData(rs, dt);
		inflateNonPkPortfolioHoldingData(3, data, rs, dt);
		return data;
	}

	public PortfolioHoldingData inflatePortfolioHoldingPkData(ResultSet _rs, DatabaseType _dt)
	throws SQLException
	{
		PortfolioHoldingData _data = new PortfolioHoldingData();
		int _pos = 1;
		_data.setPortfolioId(_rs.getInt(_pos++));
		checkNullPrimitive(_rs, _data, "portfolioId");
		_data.setSchemeCode(_rs.getInt(_pos++));
		checkNullPrimitive(_rs, _data, "schemeCode");
		return _data;
	}

	public void inflateNonPkPortfolioHoldingData(int _pos, PortfolioHoldingData _datax, ResultSet _rs, DatabaseType _dt)
	throws SQLException
	{
		{
			PortfolioHoldingData _data = _datax;
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
		return "portfolio_holding";
	}

	public void setInsertAttributes(PreparedStatement stm, MithraDataObject dataObj,
		TimeZone databaseTimeZone, int pos, DatabaseType dt) throws SQLException
	{
		PortfolioHoldingData data = (PortfolioHoldingData)dataObj;
		TimeZone conversionTimeZone = null;
		stm.setInt(pos++, data.getPortfolioId());
		stm.setInt(pos++, data.getSchemeCode());
	}

	public String getInsertFields()
	{
		return "portfolio_id,scheme_code";
	}

	public String getInsertQuestionMarks()
	{
		return "?,?";
	}

	public String getOptimisticLockingWhereSql()
	{
		return "";
	}

	public MithraObject createObject(MithraDataObject newData)
	{
		PortfolioHolding newObject = new PortfolioHolding();
		newObject.zSetFromPortfolioHoldingData((PortfolioHoldingData) newData);
		return newObject;
	}

	public String getPkColumnList(String databaseAlias)
	{
		if (databaseAlias.equals(SqlQuery.DEFAULT_DATABASE_ALIAS))
		{
			return "t0.portfolio_id,t0.scheme_code";
		}

		StringBuffer result = new StringBuffer((databaseAlias.length()+15)*2);
		result.append(databaseAlias);
		result.append(".");
		result.append("portfolio_id");
		result.append(",");
		result.append(databaseAlias);
		result.append(".");
		result.append("scheme_code");
		return result.toString();
	}
}
