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
public abstract class PortfolioAbstract extends com.gs.fw.common.mithra.superclassimpl.MithraTransactionalObjectImpl
{
	private static byte MEMORY_STATE = PersistenceState.IN_MEMORY;
	private static byte PERSISTED_STATE = PersistenceState.PERSISTED;
	private static final Logger logger = LoggerFactory.getLogger(Portfolio.class.getName());
	public PortfolioAbstract()
	{
		this.persistenceState = MEMORY_STATE;
	}

	public Portfolio getDetachedCopy() throws MithraBusinessException
	{
		return (Portfolio) super.getDetachedCopy();
	}

	public Portfolio getNonPersistentCopy() throws MithraBusinessException
	{
		Portfolio result = (Portfolio) super.getNonPersistentCopy();
		result.persistenceState = MEMORY_STATE;
		return result;
	}

	public Portfolio copyDetachedValuesToOriginalOrInsertIfNew()
	{
		return (Portfolio) this.zCopyDetachedValuesToOriginalOrInsertIfNew();
	}

	public Portfolio zFindOriginal()
	{
		PortfolioData data = (PortfolioData) this.currentData;
		Operation op;
		op = PortfolioFinder.id().eq(data.getId());
		return PortfolioFinder.findOne(op);
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
		return new PortfolioData();
	}

	protected void zSetFromPortfolioData( PortfolioData data )
	{
		super.zSetData(data);
		this.persistenceState = PERSISTED_STATE;
	}

	public void setFromPortfolioData( PortfolioData data )
	{
		super.zSetData(data);
	}

	public void zWriteDataClassName(ObjectOutput out) throws IOException
	{
	}

	public boolean isIdNull()
	{
		return ((PortfolioData) this.zSynchronizedGetData()).isIdNull();
	}

	public int getId()
	{
		PortfolioData data = (PortfolioData) this.zSynchronizedGetData();
		return data.getId();
	}

	public void setId(int newValue)
	{
		zSetInteger(PortfolioFinder.id(), newValue, true, false ,false);
	}

	public boolean isNameNull()
	{
		return ((PortfolioData) this.zSynchronizedGetData()).isNameNull();
	}

	public String getName()
	{
		PortfolioData data = (PortfolioData) this.zSynchronizedGetData();
		return data.getName();
	}

	public void setName(String newValue)
	{
		if (newValue != null) newValue = newValue.trim();
		if (newValue != null && newValue.length() > 100)
		throw new MithraBusinessException("Attribute 'name' cannot exceed maximum length of 100: " + newValue);
		zSetString(PortfolioFinder.name(), newValue, false, false );
	}

	protected void issuePrimitiveNullSetters(TransactionalBehavior behavior, MithraDataObject data)
	{
	}

	public void zPersistDetachedRelationships(MithraDataObject _data)
	{
		PortfolioData _newData = (PortfolioData) _data;
	}

	public void zSetTxDetachedDeleted()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		if (_behavior.isDetached() && _behavior.isDeleted()) return;
		PortfolioData _newData = (PortfolioData) _behavior.getCurrentDataForRead(this);
		this.zSetTxPersistenceState(PersistenceState.DETACHED_DELETED);
	}

	public void zSetNonTxDetachedDeleted()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		PortfolioData _newData = (PortfolioData) _behavior.getCurrentDataForRead(this);
		this.zSetNonTxPersistenceState(PersistenceState.DETACHED_DELETED);
	}

	/**
	* Relationship Expression:<pre>
	PortfolioHolding.portfolioId = this.id</pre>
	* @return holdings
	*/
	public PortfolioHoldingList getHoldings()
	{
		PortfolioHoldingList _result = null;
		Operation _op = null;
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForReadWithWaitIfNecessary();
		PortfolioData _data = (PortfolioData) _behavior.getCurrentDataForRead(this);
		if (_behavior.isPersisted())
		{
			{
				{
					_op = PortfolioHoldingFinder.portfolioId().eq(_data.getId());
				}
			}
		}
		else if (_behavior.isDetached())
		{
			{
				{
					Operation detachedOp = PortfolioHoldingFinder.portfolioId().eq(_data.getId());
					_result = new PortfolioHoldingList(detachedOp);
					_result.zSetForRelationship();
				}
			}
		}
		else if (_behavior.isInMemory())
		{
			_result = (PortfolioHoldingList) _data.getHoldings();
			if (_result == null)
			{
				{
					_op = PortfolioHoldingFinder.portfolioId().eq(_data.getId());
				}
			}
		}

		if (_op != null)
		{
			_result = new PortfolioHoldingList(_op);
			_result.zSetForRelationship();
		}

		return _result;
	}

	public void setHoldings(PortfolioHoldingList holdings)
	{
		PortfolioHoldingList _holdings = (PortfolioHoldingList) holdings;
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		PortfolioData _data = (PortfolioData) _behavior.getCurrentDataForWrite(this);
		if (_behavior.isInMemory())
		{
			_data.setHoldings(_holdings);
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
	public Portfolio zCascadeCopyThenInsert() throws MithraBusinessException
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		Portfolio original = (Portfolio) _behavior.copyThenInsert(this);
		return original;
	}

	protected void cascadeDeleteImpl() throws MithraBusinessException
	{
		this.delete();
	}

	public Cache zGetCache()
	{
		return PortfolioFinder.getMithraObjectPortal().getCache();
	}

	public MithraObjectPortal zGetPortal()
	{
		return PortfolioFinder.getMithraObjectPortal();
	}

	public Portfolio getOriginalPersistentObject()
	{
		return this.zFindOriginal();
	}

	protected boolean issueUpdatesForNonPrimaryKeys(TransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		changed |= zUpdateString(behavior, data, newData, PortfolioFinder.name(), false);
		return changed;
	}

	protected boolean issueUpdatesForPrimaryKeys(TransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		changed |= zUpdateInteger(behavior, data, newData, PortfolioFinder.id(), false);
		return changed;
	}

	public Object readResolve() throws ObjectStreamException
	{
		PortfolioAbstract result = (PortfolioAbstract) super.readResolve();
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
