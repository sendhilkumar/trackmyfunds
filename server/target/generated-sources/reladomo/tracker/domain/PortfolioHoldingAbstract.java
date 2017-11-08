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
public abstract class PortfolioHoldingAbstract extends com.gs.fw.common.mithra.superclassimpl.MithraTransactionalObjectImpl
{
	private static byte MEMORY_STATE = PersistenceState.IN_MEMORY;
	private static byte PERSISTED_STATE = PersistenceState.PERSISTED;
	private static final Logger logger = LoggerFactory.getLogger(PortfolioHolding.class.getName());
	private static final RelationshipHashStrategy forscheme = new SchemeRhs();
	private static final class SchemeRhs implements RelationshipHashStrategy
	{
		public boolean equalsForRelationship(Object _srcObject, Object _srcData, Object _targetData, Timestamp _asOfDate0, Timestamp _asOfDate1)
		{
			PortfolioHoldingData _castedSrcData = (PortfolioHoldingData) _srcData;
			SchemeData _castedTargetData = (SchemeData) _targetData;
			if (_castedSrcData.getSchemeCode() == _castedTargetData.getCode())
			{
				return true;
			}

			return false;
		}

		public int computeHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			PortfolioHoldingData _castedSrcData = (PortfolioHoldingData) _srcData;
			return HashUtil.hash(_castedSrcData.getSchemeCode());
		}

		public int computeOffHeapHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			return computeHashCodeFromRelated(_srcObject, _srcData);
		}
	}

	public PortfolioHoldingAbstract()
	{
		this.persistenceState = MEMORY_STATE;
	}

	public PortfolioHolding getDetachedCopy() throws MithraBusinessException
	{
		return (PortfolioHolding) super.getDetachedCopy();
	}

	public PortfolioHolding getNonPersistentCopy() throws MithraBusinessException
	{
		PortfolioHolding result = (PortfolioHolding) super.getNonPersistentCopy();
		result.persistenceState = MEMORY_STATE;
		return result;
	}

	public PortfolioHolding copyDetachedValuesToOriginalOrInsertIfNew()
	{
		return (PortfolioHolding) this.zCopyDetachedValuesToOriginalOrInsertIfNew();
	}

	public PortfolioHolding zFindOriginal()
	{
		PortfolioHoldingData data = (PortfolioHoldingData) this.currentData;
		Operation op;
		op = PortfolioHoldingFinder.portfolioId().eq(data.getPortfolioId());
		op = op.and(PortfolioHoldingFinder.schemeCode().eq(data.getSchemeCode()));
		return PortfolioHoldingFinder.findOne(op);
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
		return new PortfolioHoldingData();
	}

	protected void zSetFromPortfolioHoldingData( PortfolioHoldingData data )
	{
		super.zSetData(data);
		this.persistenceState = PERSISTED_STATE;
	}

	public void setFromPortfolioHoldingData( PortfolioHoldingData data )
	{
		super.zSetData(data);
	}

	public void zWriteDataClassName(ObjectOutput out) throws IOException
	{
	}

	public boolean isPortfolioIdNull()
	{
		return ((PortfolioHoldingData) this.zSynchronizedGetData()).isPortfolioIdNull();
	}

	public int getPortfolioId()
	{
		PortfolioHoldingData data = (PortfolioHoldingData) this.zSynchronizedGetData();
		return data.getPortfolioId();
	}

	public void setPortfolioId(int newValue)
	{
		zSetInteger(PortfolioHoldingFinder.portfolioId(), newValue, true, false ,false);
	}

	public boolean isSchemeCodeNull()
	{
		return ((PortfolioHoldingData) this.zSynchronizedGetData()).isSchemeCodeNull();
	}

	public int getSchemeCode()
	{
		PortfolioHoldingData data = (PortfolioHoldingData) this.zSynchronizedGetData();
		return data.getSchemeCode();
	}

	public void setSchemeCode(int newValue)
	{
		zSetInteger(PortfolioHoldingFinder.schemeCode(), newValue, true, false ,false);
	}

	protected void issuePrimitiveNullSetters(TransactionalBehavior behavior, MithraDataObject data)
	{
	}

	public void zPersistDetachedRelationships(MithraDataObject _data)
	{
		PortfolioHoldingData _newData = (PortfolioHoldingData) _data;
	}

	public void zSetTxDetachedDeleted()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		if (_behavior.isDetached() && _behavior.isDeleted()) return;
		PortfolioHoldingData _newData = (PortfolioHoldingData) _behavior.getCurrentDataForRead(this);
		this.zSetTxPersistenceState(PersistenceState.DETACHED_DELETED);
	}

	public void zSetNonTxDetachedDeleted()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		PortfolioHoldingData _newData = (PortfolioHoldingData) _behavior.getCurrentDataForRead(this);
		this.zSetNonTxPersistenceState(PersistenceState.DETACHED_DELETED);
	}

	/**
	* Relationship Expression:<pre>
	Scheme.code = this.schemeCode</pre>
	* @return The scheme
	*/
	public Scheme getScheme()
	{
		Scheme _result = null;
		Operation _op = null;
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForReadWithWaitIfNecessary();
		PortfolioHoldingData _data = (PortfolioHoldingData) _behavior.getCurrentDataForRead(this);
		MithraObjectPortal _portal = null;
		if (_behavior.isPersisted())
		{
			{
				_portal = SchemeFinder.getMithraObjectPortal();
				Object _related = _portal.getAsOneFromCache(this, _data, forscheme, null, null);
				if (!(_related instanceof NulledRelation)) _result = (Scheme) _related;
				if (_related == null)
				{
					_op = SchemeFinder.code().eq(_data.getSchemeCode());
				}
			}
		}
		else if (_behavior.isDetached())
		{
			{
				{
					Operation detachedOp = SchemeFinder.code().eq(_data.getSchemeCode());
					_result = SchemeFinder.zFindOneForRelationship(detachedOp);
				}
			}
		}
		else if (_behavior.isInMemory())
		{
			_result = (Scheme) _data.getScheme();
			if (_result == null)
			{
				{
					_op = SchemeFinder.code().eq(_data.getSchemeCode());
				}
			}
		}

		if (_op != null)
		{
			_result = SchemeFinder.zFindOneForRelationship(_op);
		}

		return _result;
	}

	public void setScheme(Scheme scheme)
	{
		Scheme _scheme = (Scheme) scheme;
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		PortfolioHoldingData _data = (PortfolioHoldingData) _behavior.getCurrentDataForWrite(this);
		if (_behavior.isInMemory())
		{
			_data.setScheme(_scheme);
			if (_scheme == null)
			{
				this.setSchemeCode(0);
			}
			else
			{
				this.setSchemeCode(_scheme.getCode());
			}
		}
		else if (_behavior.isPersisted())
		{
			_behavior.clearTempTransaction(this);
			if (_scheme == null)
			{
				this.setSchemeCode(0);
			}
			else
			{
				this.setSchemeCode(
					_scheme.getCode());
			}
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
	public PortfolioHolding zCascadeCopyThenInsert() throws MithraBusinessException
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		PortfolioHolding original = (PortfolioHolding) _behavior.copyThenInsert(this);
		return original;
	}

	protected void cascadeDeleteImpl() throws MithraBusinessException
	{
		this.delete();
	}

	public Cache zGetCache()
	{
		return PortfolioHoldingFinder.getMithraObjectPortal().getCache();
	}

	public MithraObjectPortal zGetPortal()
	{
		return PortfolioHoldingFinder.getMithraObjectPortal();
	}

	public PortfolioHolding getOriginalPersistentObject()
	{
		return this.zFindOriginal();
	}

	protected boolean issueUpdatesForNonPrimaryKeys(TransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		return changed;
	}

	protected boolean issueUpdatesForPrimaryKeys(TransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		changed |= zUpdateInteger(behavior, data, newData, PortfolioHoldingFinder.portfolioId(), false);
		changed |= zUpdateInteger(behavior, data, newData, PortfolioHoldingFinder.schemeCode(), false);
		return changed;
	}

	public Object readResolve() throws ObjectStreamException
	{
		PortfolioHoldingAbstract result = (PortfolioHoldingAbstract) super.readResolve();
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
