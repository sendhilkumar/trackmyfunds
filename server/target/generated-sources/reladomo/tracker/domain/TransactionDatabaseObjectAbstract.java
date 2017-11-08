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
public abstract class TransactionDatabaseObjectAbstract extends MithraAbstractTransactionalDatabaseObject implements MithraTransactionalDatabaseObject, MithraObjectFactory
{
	private SourcelessConnectionManager connectionManager;
	private SchemaManager schemaManager;
	private TablePartitionManager tablePartitionManager;
	private static final String COL_LIST_WITHOUT_PK = "date,scheme_code,amount,description,units,price,unitBalance,rta_code,schemeName,folioNumber,registrar,source";
	private static final String COL_LIST_WITHOUT_PK_WITH_ALIAS = "t0.date,t0.scheme_code,t0.amount,t0.description,t0.units,t0.price,t0.unitBalance,t0.rta_code,t0.schemeName,t0.folioNumber,t0.registrar,t0.source";
	private static final String PK_WITH_ALIAS = "t0.id = ?";
	private static final String PK_INDEX_COLS = "id";
	protected TransactionDatabaseObjectAbstract()
	{
		super("Transaction", "tracker.domain.TransactionFinder",
			13, 13,
			COL_LIST_WITHOUT_PK, COL_LIST_WITHOUT_PK_WITH_ALIAS,
			false, false, false,
			PK_WITH_ALIAS,
			PK_INDEX_COLS);
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return TransactionFinder.getMithraObjectPortal();
	}

	public RelatedFinder getFinder()
	{
		return TransactionFinder.getFinderInstance();
	}

	public static TransactionData allocateOnHeapData()
	{
		return new TransactionData();
	}

	public static TransactionData allocateOffHeapData()
	{
		throw new RuntimeException("no off heap implementation");
	}

	public MithraDataObject deserializeFullData(ObjectInput in) throws IOException, ClassNotFoundException
	{
		MithraDataObject data = new TransactionData();
		data.zDeserializeFullData(in);
		return data;
	}

	public MithraObject deserializeForRefresh(ObjectInput in) throws IOException, ClassNotFoundException
	{
		TransactionData data = new TransactionData();
		data.zDeserializePrimaryKey(in);
		return this.createObject(data);
	}

	public Cache instantiateFullCache(MithraConfigurationManager.Config config)
	{
		Cache result;
		if (config.isParticipatingInTx())
		{
			result = new FullNonDatedTransactionalCache(TransactionFinder.getPrimaryKeyAttributes(), this, TransactionFinder.getImmutableAttributes());
		}
		else
		{
			result = new FullNonDatedCache(TransactionFinder.getPrimaryKeyAttributes(), this, TransactionFinder.getImmutableAttributes(), new NonTransactionalUnderlyingObjectGetter());
		}

		initPortal(result, config);
		return result;
	}

	public Cache instantiatePartialCache(MithraConfigurationManager.Config config)
	{
		Cache result;
		if (config.isParticipatingInTx())
		{
			result = new PartialNonDatedTransactionalCache(TransactionFinder.getPrimaryKeyAttributes(), this, TransactionFinder.getImmutableAttributes(), config.getCacheTimeToLive(), config.getRelationshipCacheTimeToLive());
		}
		else
		{
			result = new PartialNonDatedCache(TransactionFinder.getPrimaryKeyAttributes(), this, TransactionFinder.getImmutableAttributes(), new NonTransactionalUnderlyingObjectGetter(), config.getCacheTimeToLive(), config.getRelationshipCacheTimeToLive());
		}

		initPortal(result, config);
		return result;
	}

	private void initPortal(Cache cache, MithraConfigurationManager.Config config)
	{
		if (config.isThreeTierClient())
		{
			TransactionFinder.initializeClientPortal(this, cache, config);
		}
		else
		{
			TransactionFinder.initializePortal(this, cache, config);
		}

		if (config.isParticipatingInTx())
		{
			Transaction.zConfigFullTx();
		}
		else
		{
			Transaction.zConfigNonTx();
		}
	}

	public List getSimulatedSequenceInitValues()
	{
		ArrayList simulatedSequenceInitValues = new ArrayList(1);
		Attribute[] primaryKeyAttributes = TransactionFinder.getPrimaryKeyAttributes();
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
		return inflateTransactionData(rs, dt);
	}

	public void inflateNonPkDataGenericSource(MithraDataObject data, ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		inflateNonPkTransactionData(1, (TransactionData) data, rs, dt);
	}

	public MithraDataObject inflatePkDataGenericSource(ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		return inflateTransactionPkData(rs, dt);
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
		return getTransactionTableName();
	}

	public String getTransactionTableName() throws MithraDatabaseException
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
		TransactionData data = (TransactionData)dataObj;
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
			return "t0.id,t0.date,t0.scheme_code,t0.amount,t0.description,t0.units,t0.price,t0.unitBalance,t0.rta_code,t0.schemeName,t0.folioNumber,t0.registrar,t0.source";
		}

		StringBuffer result = new StringBuffer((databaseAlias.length()+15)*13);
		result.append(databaseAlias).append(".").append("id");
		result.append(",").append(databaseAlias).append(".").append("date");
		result.append(",").append(databaseAlias).append(".").append("scheme_code");
		result.append(",").append(databaseAlias).append(".").append("amount");
		result.append(",").append(databaseAlias).append(".").append("description");
		result.append(",").append(databaseAlias).append(".").append("units");
		result.append(",").append(databaseAlias).append(".").append("price");
		result.append(",").append(databaseAlias).append(".").append("unitBalance");
		result.append(",").append(databaseAlias).append(".").append("rta_code");
		result.append(",").append(databaseAlias).append(".").append("schemeName");
		result.append(",").append(databaseAlias).append(".").append("folioNumber");
		result.append(",").append(databaseAlias).append(".").append("registrar");
		result.append(",").append(databaseAlias).append(".").append("source");
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

	public TransactionData inflateTransactionData(ResultSet rs, DatabaseType dt)
	throws SQLException
	{
		TransactionData data = inflateTransactionPkData(rs, dt);
		inflateNonPkTransactionData(2, data, rs, dt);
		return data;
	}

	public TransactionData inflateTransactionPkData(ResultSet _rs, DatabaseType _dt)
	throws SQLException
	{
		TransactionData _data = new TransactionData();
		int _pos = 1;
		_data.setId(_rs.getInt(_pos++));
		checkNullPrimitive(_rs, _data, "id");
		return _data;
	}

	public void inflateNonPkTransactionData(int _pos, TransactionData _datax, ResultSet _rs, DatabaseType _dt)
	throws SQLException
	{
		{
			TransactionData _data = _datax;
			Timestamp datetimestampValue = _dt.getTimestampFromResultSet(_rs, _pos++, MithraTimestamp.DefaultTimeZone);
			_data.setDate(datetimestampValue);
			_data.setSchemeCode(_rs.getInt(_pos++));
			checkNullPrimitive(_rs, _data, "schemeCode");
			_data.setAmount(_rs.getDouble(_pos++));
			checkNullPrimitive(_rs, _data, "amount");
			_data.setDescription(trimString(_rs.getString(_pos++)));
			_data.setUnits(_rs.getDouble(_pos++));
			if (_rs.wasNull())
			{
				_data.setUnitsNull();
			}

			_data.setPrice(_rs.getDouble(_pos++));
			if (_rs.wasNull())
			{
				_data.setPriceNull();
			}

			_data.setUnitBalance(_rs.getDouble(_pos++));
			if (_rs.wasNull())
			{
				_data.setUnitBalanceNull();
			}

			_data.setRtaCode(trimString(_rs.getString(_pos++)));
			_data.setSchemeName(trimString(_rs.getString(_pos++)));
			_data.setFolioNumber(trimString(_rs.getString(_pos++)));
			_data.setRegistrar(trimString(_rs.getString(_pos++)));
			_data.setSource(trimString(_rs.getString(_pos++)));
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
		return "transaction";
	}

	public void setInsertAttributes(PreparedStatement stm, MithraDataObject dataObj,
		TimeZone databaseTimeZone, int pos, DatabaseType dt) throws SQLException
	{
		TransactionData data = (TransactionData)dataObj;
		TimeZone conversionTimeZone = null;
		stm.setInt(pos++, data.getId());
		if(data.isDateNull())
		{
			throwNullAttribute("date");
		}

		conversionTimeZone = MithraTimestamp.DefaultTimeZone;
		dt.setTimestamp(stm, pos, data.getDate(), false, conversionTimeZone);
		pos++;
		stm.setInt(pos++, data.getSchemeCode());
		stm.setDouble(pos++, data.getAmount());
		if (data.isDescriptionNull())
		{
			stm.setNull(pos++, java.sql.Types.VARCHAR);
		}
		else
		{
			stm.setString(pos++, data.getDescription());
		}

		if (data.isUnitsNull())
		{
			stm.setNull(pos++, java.sql.Types.DOUBLE);
		}
		else
		{
			stm.setDouble(pos++, data.getUnits());
		}

		if (data.isPriceNull())
		{
			stm.setNull(pos++, java.sql.Types.DOUBLE);
		}
		else
		{
			stm.setDouble(pos++, data.getPrice());
		}

		if (data.isUnitBalanceNull())
		{
			stm.setNull(pos++, java.sql.Types.DOUBLE);
		}
		else
		{
			stm.setDouble(pos++, data.getUnitBalance());
		}

		if (data.isRtaCodeNull())
		{
			stm.setNull(pos++, java.sql.Types.VARCHAR);
		}
		else
		{
			stm.setString(pos++, data.getRtaCode());
		}

		if (data.isSchemeNameNull())
		{
			stm.setNull(pos++, java.sql.Types.VARCHAR);
		}
		else
		{
			stm.setString(pos++, data.getSchemeName());
		}

		if (data.isFolioNumberNull())
		{
			stm.setNull(pos++, java.sql.Types.VARCHAR);
		}
		else
		{
			stm.setString(pos++, data.getFolioNumber());
		}

		if (data.isRegistrarNull())
		{
			stm.setNull(pos++, java.sql.Types.VARCHAR);
		}
		else
		{
			stm.setString(pos++, data.getRegistrar());
		}

		if (data.isSourceNull())
		{
			stm.setNull(pos++, java.sql.Types.VARCHAR);
		}
		else
		{
			stm.setString(pos++, data.getSource());
		}
	}

	public String getInsertFields()
	{
		return "id,date,scheme_code,amount,description,units,price,unitBalance,rta_code,schemeName,folioNumber,registrar,source";
	}

	public String getInsertQuestionMarks()
	{
		return "?,?,?,?,?,?,?,?,?,?,?,?,?";
	}

	public String getOptimisticLockingWhereSql()
	{
		return "";
	}

	public MithraObject createObject(MithraDataObject newData)
	{
		Transaction newObject = new Transaction();
		newObject.zSetFromTransactionData((TransactionData) newData);
		return newObject;
	}

	public String getPkColumnList(String databaseAlias)
	{
		if (databaseAlias.equals(SqlQuery.DEFAULT_DATABASE_ALIAS))
		{
			return "t0.id";
		}

		StringBuffer result = new StringBuffer((databaseAlias.length()+15)*13);
		result.append(databaseAlias);
		result.append(".");
		result.append("id");
		return result.toString();
	}
}
