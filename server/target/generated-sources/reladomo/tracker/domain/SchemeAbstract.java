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
public abstract class SchemeAbstract extends com.gs.fw.common.mithra.superclassimpl.MithraTransactionalObjectImpl
{
	private static byte MEMORY_STATE = PersistenceState.IN_MEMORY;
	private static byte PERSISTED_STATE = PersistenceState.PERSISTED;
	private static final Logger logger = LoggerFactory.getLogger(Scheme.class.getName());
	public SchemeAbstract()
	{
		this.persistenceState = MEMORY_STATE;
	}

	public Scheme getDetachedCopy() throws MithraBusinessException
	{
		return (Scheme) super.getDetachedCopy();
	}

	public Scheme getNonPersistentCopy() throws MithraBusinessException
	{
		Scheme result = (Scheme) super.getNonPersistentCopy();
		result.persistenceState = MEMORY_STATE;
		return result;
	}

	public Scheme copyDetachedValuesToOriginalOrInsertIfNew()
	{
		return (Scheme) this.zCopyDetachedValuesToOriginalOrInsertIfNew();
	}

	public Scheme zFindOriginal()
	{
		SchemeData data = (SchemeData) this.currentData;
		Operation op;
		op = SchemeFinder.code().eq(data.getCode());
		return SchemeFinder.findOne(op);
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
		return new SchemeData();
	}

	protected void zSetFromSchemeData( SchemeData data )
	{
		super.zSetData(data);
		this.persistenceState = PERSISTED_STATE;
	}

	public void setFromSchemeData( SchemeData data )
	{
		super.zSetData(data);
	}

	public void zWriteDataClassName(ObjectOutput out) throws IOException
	{
	}

	public boolean isAmcNull()
	{
		return ((SchemeData) this.zSynchronizedGetData()).isAmcNull();
	}

	public String getAmc()
	{
		SchemeData data = (SchemeData) this.zSynchronizedGetData();
		return data.getAmc();
	}

	public void setAmc(String newValue)
	{
		if (newValue != null) newValue = newValue.trim();
		if (newValue != null && newValue.length() > 500)
		throw new MithraBusinessException("Attribute 'amc' cannot exceed maximum length of 500: " + newValue);
		zSetString(SchemeFinder.amc(), newValue, false, false );
	}

	public boolean isCodeNull()
	{
		return ((SchemeData) this.zSynchronizedGetData()).isCodeNull();
	}

	public int getCode()
	{
		SchemeData data = (SchemeData) this.zSynchronizedGetData();
		return data.getCode();
	}

	public void setCode(int newValue)
	{
		zSetInteger(SchemeFinder.code(), newValue, true, false ,false);
	}

	public boolean isNameNull()
	{
		return ((SchemeData) this.zSynchronizedGetData()).isNameNull();
	}

	public String getName()
	{
		SchemeData data = (SchemeData) this.zSynchronizedGetData();
		return data.getName();
	}

	public void setName(String newValue)
	{
		if (newValue != null) newValue = newValue.trim();
		if (newValue != null && newValue.length() > 500)
		throw new MithraBusinessException("Attribute 'name' cannot exceed maximum length of 500: " + newValue);
		zSetString(SchemeFinder.name(), newValue, false, false );
	}

	public boolean isRtaCodeNull()
	{
		return ((SchemeData) this.zSynchronizedGetData()).isRtaCodeNull();
	}

	public String getRtaCode()
	{
		SchemeData data = (SchemeData) this.zSynchronizedGetData();
		return data.getRtaCode();
	}

	public void setRtaCode(String newValue)
	{
		if (newValue != null) newValue = newValue.trim();
		if (newValue != null && newValue.length() > 50)
		throw new MithraBusinessException("Attribute 'rtaCode' cannot exceed maximum length of 50: " + newValue);
		zSetString(SchemeFinder.rtaCode(), newValue, false, false );
	}

	protected void issuePrimitiveNullSetters(TransactionalBehavior behavior, MithraDataObject data)
	{
	}

	public void zPersistDetachedRelationships(MithraDataObject _data)
	{
		SchemeData _newData = (SchemeData) _data;
	}

	public void zSetTxDetachedDeleted()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		if (_behavior.isDetached() && _behavior.isDeleted()) return;
		SchemeData _newData = (SchemeData) _behavior.getCurrentDataForRead(this);
		this.zSetTxPersistenceState(PersistenceState.DETACHED_DELETED);
	}

	public void zSetNonTxDetachedDeleted()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		SchemeData _newData = (SchemeData) _behavior.getCurrentDataForRead(this);
		this.zSetNonTxPersistenceState(PersistenceState.DETACHED_DELETED);
	}

	/**
	* Relationship Expression:<pre>
	this.code = Transaction.schemeCode</pre>
	* @see Transaction#getScheme() reverse relationship Transaction.getScheme()
	* @return transactions
	*/
	public TransactionList getTransactions()
	{
		TransactionList _result = null;
		Operation _op = null;
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForReadWithWaitIfNecessary();
		SchemeData _data = (SchemeData) _behavior.getCurrentDataForRead(this);
		if (_behavior.isPersisted())
		{
			{
				{
					_op = TransactionFinder.schemeCode().eq(_data.getCode());
				}
			}
		}
		else if (_behavior.isDetached())
		{
			{
				{
					Operation detachedOp = TransactionFinder.schemeCode().eq(_data.getCode());
					_result = new TransactionList(detachedOp);
					_result.zSetForRelationship();
				}
			}
		}
		else if (_behavior.isInMemory())
		{
			_result = (TransactionList) _data.getTransactions();
			if (_result == null)
			{
				{
					_op = TransactionFinder.schemeCode().eq(_data.getCode());
				}
			}
		}

		if (_op != null)
		{
			_result = new TransactionList(_op);
			_result.zSetForRelationship();
		}

		return _result;
	}

	public void setTransactions(TransactionList transactions)
	{
		TransactionList _transactions = (TransactionList) transactions;
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		SchemeData _data = (SchemeData) _behavior.getCurrentDataForWrite(this);
		if (_behavior.isInMemory())
		{
			_data.setTransactions(_transactions);
		}
		else if (_behavior.isPersisted())
		{
			_behavior.clearTempTransaction(this);
		}
		else throw new RuntimeException("not implemented");
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
	public Scheme zCascadeCopyThenInsert() throws MithraBusinessException
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		Scheme original = (Scheme) _behavior.copyThenInsert(this);
		return original;
	}

	protected void cascadeDeleteImpl() throws MithraBusinessException
	{
		this.delete();
	}

	public Cache zGetCache()
	{
		return SchemeFinder.getMithraObjectPortal().getCache();
	}

	public MithraObjectPortal zGetPortal()
	{
		return SchemeFinder.getMithraObjectPortal();
	}

	public Scheme getOriginalPersistentObject()
	{
		return this.zFindOriginal();
	}

	protected boolean issueUpdatesForNonPrimaryKeys(TransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		changed |= zUpdateString(behavior, data, newData, SchemeFinder.amc(), false);
		changed |= zUpdateString(behavior, data, newData, SchemeFinder.name(), false);
		changed |= zUpdateString(behavior, data, newData, SchemeFinder.rtaCode(), false);
		return changed;
	}

	protected boolean issueUpdatesForPrimaryKeys(TransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		changed |= zUpdateInteger(behavior, data, newData, SchemeFinder.code(), false);
		return changed;
	}

	public Object readResolve() throws ObjectStreamException
	{
		SchemeAbstract result = (SchemeAbstract) super.readResolve();
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
