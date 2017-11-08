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
import com.gs.fw.common.mithra.*;
import com.gs.fw.common.mithra.behavior.*;
import com.gs.fw.common.mithra.cache.Cache;
import com.gs.fw.common.mithra.extractor.*;
import com.gs.fw.common.mithra.finder.*;
import com.gs.fw.common.mithra.list.*;
import com.gs.fw.common.mithra.behavior.state.PersistenceState;
import com.gs.fw.common.mithra.attribute.update.*;
import com.gs.fw.common.mithra.transaction.MithraObjectPersister;
import java.util.Arrays;
import java.util.HashSet;
/**
* This file was automatically generated using Mithra 16.6.1. Please do not modify it.
* Add custom logic to its subclass instead.
*/
// Generated from templates/transactional/Abstract.jsp
public abstract class NetAssetValueAbstract extends com.gs.fw.common.mithra.superclassimpl.MithraTransactionalObjectImpl
{
	private static byte MEMORY_STATE = PersistenceState.IN_MEMORY;
	private static byte PERSISTED_STATE = PersistenceState.PERSISTED;
	private static final Logger logger = LoggerFactory.getLogger(NetAssetValue.class.getName());
	public NetAssetValueAbstract()
	{
		this.persistenceState = MEMORY_STATE;
	}

	public NetAssetValue getDetachedCopy() throws MithraBusinessException
	{
		return (NetAssetValue) super.getDetachedCopy();
	}

	public NetAssetValue getNonPersistentCopy() throws MithraBusinessException
	{
		NetAssetValue result = (NetAssetValue) super.getNonPersistentCopy();
		result.persistenceState = MEMORY_STATE;
		return result;
	}

	public NetAssetValue copyDetachedValuesToOriginalOrInsertIfNew()
	{
		return (NetAssetValue) this.zCopyDetachedValuesToOriginalOrInsertIfNew();
	}

	public NetAssetValue zFindOriginal()
	{
		NetAssetValueData data = (NetAssetValueData) this.currentData;
		Operation op;
		op = NetAssetValueFinder.schemeCode().eq(data.getSchemeCode());
		op = op.and(NetAssetValueFinder.date().eq(data.getDate()));
		return NetAssetValueFinder.findOne(op);
	}

	public boolean isModifiedSinceDetachmentByDependentRelationships()
	{
		if(this.isModifiedSinceDetachment()) return true;
		return false;
	}

	private Logger getLogger()
	{
		return logger;
	}

	public MithraDataObject zAllocateData()
	{
		return new NetAssetValueData();
	}

	protected void zSetFromNetAssetValueData( NetAssetValueData data )
	{
		super.zSetData(data);
		this.persistenceState = PERSISTED_STATE;
	}

	public void setFromNetAssetValueData( NetAssetValueData data )
	{
		super.zSetData(data);
	}

	public void zWriteDataClassName(ObjectOutput out) throws IOException
	{
	}

	public boolean isDateNull()
	{
		return ((NetAssetValueData) this.zSynchronizedGetData()).isDateNull();
	}

	public Timestamp getDate()
	{
		NetAssetValueData data = (NetAssetValueData) this.zSynchronizedGetData();
		return data.getDate();
	}

	public void setDate(Timestamp newValue)
	{
		zSetTimestamp(NetAssetValueFinder.date(), newValue, true, false );
	}

	public boolean isLoadIdNull()
	{
		return ((NetAssetValueData) this.zSynchronizedGetData()).isLoadIdNull();
	}

	public int getLoadId()
	{
		NetAssetValueData data = (NetAssetValueData) this.zSynchronizedGetData();
		if (data.isLoadIdNull()) MithraNullPrimitiveException.throwNew("loadId", data);
		return data.getLoadId();
	}

	public void setLoadId(int newValue)
	{
		zSetInteger(NetAssetValueFinder.loadId(), newValue, false, false ,true);
	}

	public boolean isNetAssetValueNull()
	{
		return ((NetAssetValueData) this.zSynchronizedGetData()).isNetAssetValueNull();
	}

	public double getNetAssetValue()
	{
		NetAssetValueData data = (NetAssetValueData) this.zSynchronizedGetData();
		if (data.isNetAssetValueNull()) MithraNullPrimitiveException.throwNew("netAssetValue", data);
		return data.getNetAssetValue();
	}

	public void setNetAssetValue(double newValue)
	{
		zSetDouble(NetAssetValueFinder.netAssetValue(), newValue, false, false ,true);
	}

	public boolean isRepurchasePriceNull()
	{
		return ((NetAssetValueData) this.zSynchronizedGetData()).isRepurchasePriceNull();
	}

	public double getRepurchasePrice()
	{
		NetAssetValueData data = (NetAssetValueData) this.zSynchronizedGetData();
		if (data.isRepurchasePriceNull()) MithraNullPrimitiveException.throwNew("repurchasePrice", data);
		return data.getRepurchasePrice();
	}

	public void setRepurchasePrice(double newValue)
	{
		zSetDouble(NetAssetValueFinder.repurchasePrice(), newValue, false, false ,true);
	}

	public boolean isSalePriceNull()
	{
		return ((NetAssetValueData) this.zSynchronizedGetData()).isSalePriceNull();
	}

	public double getSalePrice()
	{
		NetAssetValueData data = (NetAssetValueData) this.zSynchronizedGetData();
		if (data.isSalePriceNull()) MithraNullPrimitiveException.throwNew("salePrice", data);
		return data.getSalePrice();
	}

	public void setSalePrice(double newValue)
	{
		zSetDouble(NetAssetValueFinder.salePrice(), newValue, false, false ,true);
	}

	public boolean isSchemeCodeNull()
	{
		return ((NetAssetValueData) this.zSynchronizedGetData()).isSchemeCodeNull();
	}

	public int getSchemeCode()
	{
		NetAssetValueData data = (NetAssetValueData) this.zSynchronizedGetData();
		return data.getSchemeCode();
	}

	public void setSchemeCode(int newValue)
	{
		zSetInteger(NetAssetValueFinder.schemeCode(), newValue, true, false ,false);
	}

	protected void issuePrimitiveNullSetters(TransactionalBehavior behavior, MithraDataObject data)
	{
		zNullify(behavior, data, NetAssetValueFinder.netAssetValue(), false);
		zNullify(behavior, data, NetAssetValueFinder.repurchasePrice(), false);
		zNullify(behavior, data, NetAssetValueFinder.salePrice(), false);
		zNullify(behavior, data, NetAssetValueFinder.loadId(), false);
	}

	public void setNetAssetValueNull()
	{
		zNullify(NetAssetValueFinder.netAssetValue(), false);
	}

	public void setRepurchasePriceNull()
	{
		zNullify(NetAssetValueFinder.repurchasePrice(), false);
	}

	public void setSalePriceNull()
	{
		zNullify(NetAssetValueFinder.salePrice(), false);
	}

	public void setLoadIdNull()
	{
		zNullify(NetAssetValueFinder.loadId(), false);
	}

	public void zPersistDetachedRelationships(MithraDataObject _data)
	{
		NetAssetValueData _newData = (NetAssetValueData) _data;
	}

	public void zSetTxDetachedDeleted()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		if (_behavior.isDetached() && _behavior.isDeleted()) return;
		NetAssetValueData _newData = (NetAssetValueData) _behavior.getCurrentDataForRead(this);
		this.zSetTxPersistenceState(PersistenceState.DETACHED_DELETED);
	}

	public void zSetNonTxDetachedDeleted()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		NetAssetValueData _newData = (NetAssetValueData) _behavior.getCurrentDataForRead(this);
		this.zSetNonTxPersistenceState(PersistenceState.DETACHED_DELETED);
	}

	protected void cascadeInsertImpl() throws MithraBusinessException
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		_behavior.insert(this);
	}

	@Override
	public Map< RelatedFinder, StatisticCounter > zAddNavigatedRelationshipsStats(RelatedFinder finder, Map< RelatedFinder, StatisticCounter > navigationStats)
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		_behavior.addNavigatedRelationshipsStats(this, finder, navigationStats);
		return navigationStats;
	}

	@Override
	public Map< RelatedFinder, StatisticCounter > zAddNavigatedRelationshipsStatsForUpdate(RelatedFinder parentFinderGeneric, Map< RelatedFinder, StatisticCounter > navigationStats)
	{
		return navigationStats;
	}

	@Override
	public Map< RelatedFinder, StatisticCounter > zAddNavigatedRelationshipsStatsForDelete(RelatedFinder parentFinder, Map< RelatedFinder, StatisticCounter > navigationStats)
	{
		return navigationStats;
	}

	@Override
	public NetAssetValue zCascadeCopyThenInsert() throws MithraBusinessException
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		NetAssetValue original = (NetAssetValue) _behavior.copyThenInsert(this);
		return original;
	}

	protected void cascadeDeleteImpl() throws MithraBusinessException
	{
		this.delete();
	}

	public Cache zGetCache()
	{
		return NetAssetValueFinder.getMithraObjectPortal().getCache();
	}

	public MithraObjectPortal zGetPortal()
	{
		return NetAssetValueFinder.getMithraObjectPortal();
	}

	public NetAssetValue getOriginalPersistentObject()
	{
		return this.zFindOriginal();
	}

	protected boolean issueUpdatesForNonPrimaryKeys(TransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		changed |= zUpdateInteger(behavior, data, newData, NetAssetValueFinder.loadId(), false);
		changed |= zUpdateDouble(behavior, data, newData, NetAssetValueFinder.netAssetValue(), false);
		changed |= zUpdateDouble(behavior, data, newData, NetAssetValueFinder.repurchasePrice(), false);
		changed |= zUpdateDouble(behavior, data, newData, NetAssetValueFinder.salePrice(), false);
		return changed;
	}

	protected boolean issueUpdatesForPrimaryKeys(TransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		changed |= zUpdateTimestamp(behavior, data, newData, NetAssetValueFinder.date(), false);
		changed |= zUpdateInteger(behavior, data, newData, NetAssetValueFinder.schemeCode(), false);
		return changed;
	}

	public Object readResolve() throws ObjectStreamException
	{
		NetAssetValueAbstract result = (NetAssetValueAbstract) super.readResolve();
		if (result.persistenceState == PersistenceState.PERSISTED)
		{
			result.persistenceState = PERSISTED_STATE;
		}
		else if (result.persistenceState == PersistenceState.IN_MEMORY)
		{
			result.persistenceState = MEMORY_STATE;
		}

		return result;
	}

	protected static void zConfigNonTx()
	{
		MEMORY_STATE = PersistenceState.IN_MEMORY_NON_TRANSACTIONAL;
		PERSISTED_STATE = PersistenceState.PERSISTED_NON_TRANSACTIONAL;
	}

	protected static void zConfigFullTx()
	{
		MEMORY_STATE = PersistenceState.IN_MEMORY;
		PERSISTED_STATE = PersistenceState.PERSISTED;
	}
}
