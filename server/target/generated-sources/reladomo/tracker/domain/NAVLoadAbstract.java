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
public abstract class NAVLoadAbstract extends com.gs.fw.common.mithra.superclassimpl.MithraTransactionalObjectImpl
{
	private static byte MEMORY_STATE = PersistenceState.IN_MEMORY;
	private static byte PERSISTED_STATE = PersistenceState.PERSISTED;
	private static final Logger logger = LoggerFactory.getLogger(NAVLoad.class.getName());
	public NAVLoadAbstract()
	{
		this.persistenceState = MEMORY_STATE;
	}

	public NAVLoad getDetachedCopy() throws MithraBusinessException
	{
		return (NAVLoad) super.getDetachedCopy();
	}

	public NAVLoad getNonPersistentCopy() throws MithraBusinessException
	{
		NAVLoad result = (NAVLoad) super.getNonPersistentCopy();
		result.persistenceState = MEMORY_STATE;
		return result;
	}

	public void insert() throws MithraBusinessException
	{
		TransactionalBehavior behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		this.checkAndGeneratePrimaryKeys();
		behavior.insert(this);
	}

	public NAVLoad copyDetachedValuesToOriginalOrInsertIfNew()
	{
		return (NAVLoad) this.zCopyDetachedValuesToOriginalOrInsertIfNew();
	}

	public NAVLoad zFindOriginal()
	{
		NAVLoadData data = (NAVLoadData) this.currentData;
		Operation op;
		op = NAVLoadFinder.id().eq(data.getId());
		return NAVLoadFinder.findOne(op);
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
		return new NAVLoadData();
	}

	protected void zSetFromNAVLoadData( NAVLoadData data )
	{
		super.zSetData(data);
		this.persistenceState = PERSISTED_STATE;
	}

	public void setFromNAVLoadData( NAVLoadData data )
	{
		super.zSetData(data);
	}

	public void zWriteDataClassName(ObjectOutput out) throws IOException
	{
	}

	public boolean isIdNull()
	{
		return ((NAVLoadData) this.zSynchronizedGetData()).isIdNull();
	}

	public int getId()
	{
		NAVLoadData data = (NAVLoadData) this.zSynchronizedGetData();
		return data.getId();
	}

	public void setId(int newValue)
	{
		zSetInteger(NAVLoadFinder.id(), newValue, true, false ,false);
	}

	public boolean isLatestNavDateNull()
	{
		return ((NAVLoadData) this.zSynchronizedGetData()).isLatestNavDateNull();
	}

	public Timestamp getLatestNavDate()
	{
		NAVLoadData data = (NAVLoadData) this.zSynchronizedGetData();
		return data.getLatestNavDate();
	}

	public void setLatestNavDate(Timestamp newValue)
	{
		zSetTimestamp(NAVLoadFinder.latestNavDate(), newValue, false, false );
	}

	public boolean isLoadTimeNull()
	{
		return ((NAVLoadData) this.zSynchronizedGetData()).isLoadTimeNull();
	}

	public Timestamp getLoadTime()
	{
		NAVLoadData data = (NAVLoadData) this.zSynchronizedGetData();
		return data.getLoadTime();
	}

	public void setLoadTime(Timestamp newValue)
	{
		zSetTimestamp(NAVLoadFinder.loadTime(), newValue, false, false );
	}

	public boolean isMaxOccuringDateNull()
	{
		return ((NAVLoadData) this.zSynchronizedGetData()).isMaxOccuringDateNull();
	}

	public Timestamp getMaxOccuringDate()
	{
		NAVLoadData data = (NAVLoadData) this.zSynchronizedGetData();
		return data.getMaxOccuringDate();
	}

	public void setMaxOccuringDate(Timestamp newValue)
	{
		zSetTimestamp(NAVLoadFinder.maxOccuringDate(), newValue, false, false );
	}

	protected void issuePrimitiveNullSetters(TransactionalBehavior behavior, MithraDataObject data)
	{
	}

	public void zPersistDetachedRelationships(MithraDataObject _data)
	{
		NAVLoadData _newData = (NAVLoadData) _data;
	}

	public void zSetTxDetachedDeleted()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		if (_behavior.isDetached() && _behavior.isDeleted()) return;
		NAVLoadData _newData = (NAVLoadData) _behavior.getCurrentDataForRead(this);
		this.zSetTxPersistenceState(PersistenceState.DETACHED_DELETED);
	}

	public void zSetNonTxDetachedDeleted()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		NAVLoadData _newData = (NAVLoadData) _behavior.getCurrentDataForRead(this);
		this.zSetNonTxPersistenceState(PersistenceState.DETACHED_DELETED);
	}

	protected void cascadeInsertImpl() throws MithraBusinessException
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		this.checkAndGeneratePrimaryKeys();
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
	public NAVLoad zCascadeCopyThenInsert() throws MithraBusinessException
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		this.checkAndGeneratePrimaryKeys();
		NAVLoad original = (NAVLoad) _behavior.copyThenInsert(this);
		return original;
	}

	protected void cascadeDeleteImpl() throws MithraBusinessException
	{
		this.delete();
	}

	public Cache zGetCache()
	{
		return NAVLoadFinder.getMithraObjectPortal().getCache();
	}

	public MithraObjectPortal zGetPortal()
	{
		return NAVLoadFinder.getMithraObjectPortal();
	}

	public NAVLoad getOriginalPersistentObject()
	{
		return this.zFindOriginal();
	}

	protected boolean issueUpdatesForNonPrimaryKeys(TransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		changed |= zUpdateTimestamp(behavior, data, newData, NAVLoadFinder.latestNavDate(), false);
		changed |= zUpdateTimestamp(behavior, data, newData, NAVLoadFinder.loadTime(), false);
		changed |= zUpdateTimestamp(behavior, data, newData, NAVLoadFinder.maxOccuringDate(), false);
		return changed;
	}

	protected boolean issueUpdatesForPrimaryKeys(TransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		changed |= zUpdateInteger(behavior, data, newData, NAVLoadFinder.id(), false);
		return changed;
	}

	public int generateAndSetId()
	{
		int nextValue =(int) this.generateId();
		this.setId(nextValue);
		return nextValue;
	}

	public boolean zGetIsIdSet()
	{
		TransactionalBehavior behavior = zGetTransactionalBehaviorForReadWithWaitIfNecessary();
		NAVLoadData data = (NAVLoadData) behavior.getCurrentDataForRead(this);
		return data.zGetIsIdSet();
	}

	protected int generateId()
	throws MithraBusinessException
	{
		MaxFromTablePrimaryKeyGenerator primaryKeyGenerator =
		MithraPrimaryKeyGenerator.getInstance().getMaxFromTablePrimaryKeyGenerator(NAVLoadFinder.id(),null);
		return (int)primaryKeyGenerator.getNextId();
	}

	private void checkAndGeneratePrimaryKeys()
	{
		TransactionalBehavior behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		NAVLoadData data = (NAVLoadData) behavior.getCurrentDataForWrite(this);
		if (!data.zGetIsIdSet())
		{
			data.setId(generateId());
		}
	}

	public Object readResolve() throws ObjectStreamException
	{
		NAVLoadAbstract result = (NAVLoadAbstract) super.readResolve();
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
