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
public abstract class TransactionAbstract extends com.gs.fw.common.mithra.superclassimpl.MithraTransactionalObjectImpl
{
	private static byte MEMORY_STATE = PersistenceState.IN_MEMORY;
	private static byte PERSISTED_STATE = PersistenceState.PERSISTED;
	private static final Logger logger = LoggerFactory.getLogger(Transaction.class.getName());
	private static final RelationshipHashStrategy forscheme = new SchemeRhs();
	private static final class SchemeRhs implements RelationshipHashStrategy
	{
		public boolean equalsForRelationship(Object _srcObject, Object _srcData, Object _targetData, Timestamp _asOfDate0, Timestamp _asOfDate1)
		{
			TransactionData _castedSrcData = (TransactionData) _srcData;
			SchemeData _castedTargetData = (SchemeData) _targetData;
			if (_castedSrcData.getSchemeCode() == _castedTargetData.getCode())
			{
				return true;
			}

			return false;
		}

		public int computeHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			TransactionData _castedSrcData = (TransactionData) _srcData;
			return HashUtil.hash(_castedSrcData.getSchemeCode());
		}

		public int computeOffHeapHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			return computeHashCodeFromRelated(_srcObject, _srcData);
		}
	}

	public TransactionAbstract()
	{
		this.persistenceState = MEMORY_STATE;
	}

	public Transaction getDetachedCopy() throws MithraBusinessException
	{
		return (Transaction) super.getDetachedCopy();
	}

	public Transaction getNonPersistentCopy() throws MithraBusinessException
	{
		Transaction result = (Transaction) super.getNonPersistentCopy();
		result.persistenceState = MEMORY_STATE;
		return result;
	}

	public void insert() throws MithraBusinessException
	{
		TransactionalBehavior behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		this.checkAndGeneratePrimaryKeys();
		behavior.insert(this);
	}

	public Transaction copyDetachedValuesToOriginalOrInsertIfNew()
	{
		return (Transaction) this.zCopyDetachedValuesToOriginalOrInsertIfNew();
	}

	public Transaction zFindOriginal()
	{
		TransactionData data = (TransactionData) this.currentData;
		Operation op;
		op = TransactionFinder.id().eq(data.getId());
		return TransactionFinder.findOne(op);
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
		return new TransactionData();
	}

	protected void zSetFromTransactionData( TransactionData data )
	{
		super.zSetData(data);
		this.persistenceState = PERSISTED_STATE;
	}

	public void setFromTransactionData( TransactionData data )
	{
		super.zSetData(data);
	}

	public void zWriteDataClassName(ObjectOutput out) throws IOException
	{
	}

	public boolean isAmountNull()
	{
		return ((TransactionData) this.zSynchronizedGetData()).isAmountNull();
	}

	public double getAmount()
	{
		TransactionData data = (TransactionData) this.zSynchronizedGetData();
		return data.getAmount();
	}

	public void setAmount(double newValue)
	{
		zSetDouble(TransactionFinder.amount(), newValue, false, false ,false);
	}

	public boolean isDateNull()
	{
		return ((TransactionData) this.zSynchronizedGetData()).isDateNull();
	}

	public Timestamp getDate()
	{
		TransactionData data = (TransactionData) this.zSynchronizedGetData();
		return data.getDate();
	}

	public void setDate(Timestamp newValue)
	{
		zSetTimestamp(TransactionFinder.date(), newValue, false, false );
	}

	public boolean isDescriptionNull()
	{
		return ((TransactionData) this.zSynchronizedGetData()).isDescriptionNull();
	}

	public String getDescription()
	{
		TransactionData data = (TransactionData) this.zSynchronizedGetData();
		return data.getDescription();
	}

	public void setDescription(String newValue)
	{
		if (newValue != null) newValue = newValue.trim();
		if (newValue != null && newValue.length() > 500)
		throw new MithraBusinessException("Attribute 'description' cannot exceed maximum length of 500: " + newValue);
		zSetString(TransactionFinder.description(), newValue, false, false );
	}

	public boolean isFolioNumberNull()
	{
		return ((TransactionData) this.zSynchronizedGetData()).isFolioNumberNull();
	}

	public String getFolioNumber()
	{
		TransactionData data = (TransactionData) this.zSynchronizedGetData();
		return data.getFolioNumber();
	}

	public void setFolioNumber(String newValue)
	{
		if (newValue != null) newValue = newValue.trim();
		if (newValue != null && newValue.length() > 100)
		newValue = newValue.substring(0, 100 ).trim();
		zSetString(TransactionFinder.folioNumber(), newValue, false, false );
	}

	public boolean isIdNull()
	{
		return ((TransactionData) this.zSynchronizedGetData()).isIdNull();
	}

	public int getId()
	{
		TransactionData data = (TransactionData) this.zSynchronizedGetData();
		return data.getId();
	}

	public void setId(int newValue)
	{
		zSetInteger(TransactionFinder.id(), newValue, true, false ,false);
	}

	public boolean isPriceNull()
	{
		return ((TransactionData) this.zSynchronizedGetData()).isPriceNull();
	}

	public double getPrice()
	{
		TransactionData data = (TransactionData) this.zSynchronizedGetData();
		if (data.isPriceNull()) MithraNullPrimitiveException.throwNew("price", data);
		return data.getPrice();
	}

	public void setPrice(double newValue)
	{
		zSetDouble(TransactionFinder.price(), newValue, false, false ,true);
	}

	public boolean isRegistrarNull()
	{
		return ((TransactionData) this.zSynchronizedGetData()).isRegistrarNull();
	}

	public String getRegistrar()
	{
		TransactionData data = (TransactionData) this.zSynchronizedGetData();
		return data.getRegistrar();
	}

	public void setRegistrar(String newValue)
	{
		if (newValue != null) newValue = newValue.trim();
		if (newValue != null && newValue.length() > 100)
		newValue = newValue.substring(0, 100 ).trim();
		zSetString(TransactionFinder.registrar(), newValue, false, false );
	}

	public boolean isRtaCodeNull()
	{
		return ((TransactionData) this.zSynchronizedGetData()).isRtaCodeNull();
	}

	public String getRtaCode()
	{
		TransactionData data = (TransactionData) this.zSynchronizedGetData();
		return data.getRtaCode();
	}

	public void setRtaCode(String newValue)
	{
		if (newValue != null) newValue = newValue.trim();
		if (newValue != null && newValue.length() > 50)
		newValue = newValue.substring(0, 50 ).trim();
		zSetString(TransactionFinder.rtaCode(), newValue, false, false );
	}

	public boolean isSchemeCodeNull()
	{
		return ((TransactionData) this.zSynchronizedGetData()).isSchemeCodeNull();
	}

	public int getSchemeCode()
	{
		TransactionData data = (TransactionData) this.zSynchronizedGetData();
		return data.getSchemeCode();
	}

	public void setSchemeCode(int newValue)
	{
		zSetInteger(TransactionFinder.schemeCode(), newValue, false, false ,false);
	}

	public boolean isSchemeNameNull()
	{
		return ((TransactionData) this.zSynchronizedGetData()).isSchemeNameNull();
	}

	public String getSchemeName()
	{
		TransactionData data = (TransactionData) this.zSynchronizedGetData();
		return data.getSchemeName();
	}

	public void setSchemeName(String newValue)
	{
		if (newValue != null) newValue = newValue.trim();
		if (newValue != null && newValue.length() > 500)
		newValue = newValue.substring(0, 500 ).trim();
		zSetString(TransactionFinder.schemeName(), newValue, false, false );
	}

	public boolean isSourceNull()
	{
		return ((TransactionData) this.zSynchronizedGetData()).isSourceNull();
	}

	public String getSource()
	{
		TransactionData data = (TransactionData) this.zSynchronizedGetData();
		return data.getSource();
	}

	public void setSource(String newValue)
	{
		if (newValue != null) newValue = newValue.trim();
		if (newValue != null && newValue.length() > 100)
		newValue = newValue.substring(0, 100 ).trim();
		zSetString(TransactionFinder.source(), newValue, false, false );
	}

	public boolean isUnitBalanceNull()
	{
		return ((TransactionData) this.zSynchronizedGetData()).isUnitBalanceNull();
	}

	public double getUnitBalance()
	{
		TransactionData data = (TransactionData) this.zSynchronizedGetData();
		if (data.isUnitBalanceNull()) MithraNullPrimitiveException.throwNew("unitBalance", data);
		return data.getUnitBalance();
	}

	public void setUnitBalance(double newValue)
	{
		zSetDouble(TransactionFinder.unitBalance(), newValue, false, false ,true);
	}

	public boolean isUnitsNull()
	{
		return ((TransactionData) this.zSynchronizedGetData()).isUnitsNull();
	}

	public double getUnits()
	{
		TransactionData data = (TransactionData) this.zSynchronizedGetData();
		if (data.isUnitsNull()) MithraNullPrimitiveException.throwNew("units", data);
		return data.getUnits();
	}

	public void setUnits(double newValue)
	{
		zSetDouble(TransactionFinder.units(), newValue, false, false ,true);
	}

	protected void issuePrimitiveNullSetters(TransactionalBehavior behavior, MithraDataObject data)
	{
		zNullify(behavior, data, TransactionFinder.units(), false);
		zNullify(behavior, data, TransactionFinder.price(), false);
		zNullify(behavior, data, TransactionFinder.unitBalance(), false);
	}

	public void setUnitsNull()
	{
		zNullify(TransactionFinder.units(), false);
	}

	public void setPriceNull()
	{
		zNullify(TransactionFinder.price(), false);
	}

	public void setUnitBalanceNull()
	{
		zNullify(TransactionFinder.unitBalance(), false);
	}

	public void zPersistDetachedRelationships(MithraDataObject _data)
	{
		TransactionData _newData = (TransactionData) _data;
	}

	public void zSetTxDetachedDeleted()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		if (_behavior.isDetached() && _behavior.isDeleted()) return;
		TransactionData _newData = (TransactionData) _behavior.getCurrentDataForRead(this);
		this.zSetTxPersistenceState(PersistenceState.DETACHED_DELETED);
	}

	public void zSetNonTxDetachedDeleted()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		TransactionData _newData = (TransactionData) _behavior.getCurrentDataForRead(this);
		this.zSetNonTxPersistenceState(PersistenceState.DETACHED_DELETED);
	}

	/**
	* Relationship Expression:<pre>
	Scheme.code = this.schemeCode</pre>
	* @see Scheme#getTransactions() reverse relationship Scheme.getTransactions()
	* @return The scheme
	*/
	public Scheme getScheme()
	{
		Scheme _result = null;
		Operation _op = null;
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForReadWithWaitIfNecessary();
		TransactionData _data = (TransactionData) _behavior.getCurrentDataForRead(this);
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
		TransactionData _data = (TransactionData) _behavior.getCurrentDataForWrite(this);
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
	public Transaction zCascadeCopyThenInsert() throws MithraBusinessException
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		this.checkAndGeneratePrimaryKeys();
		Transaction original = (Transaction) _behavior.copyThenInsert(this);
		return original;
	}

	protected void cascadeDeleteImpl() throws MithraBusinessException
	{
		this.delete();
	}

	public Cache zGetCache()
	{
		return TransactionFinder.getMithraObjectPortal().getCache();
	}

	public MithraObjectPortal zGetPortal()
	{
		return TransactionFinder.getMithraObjectPortal();
	}

	public Transaction getOriginalPersistentObject()
	{
		return this.zFindOriginal();
	}

	protected boolean issueUpdatesForNonPrimaryKeys(TransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		changed |= zUpdateDouble(behavior, data, newData, TransactionFinder.amount(), false);
		changed |= zUpdateTimestamp(behavior, data, newData, TransactionFinder.date(), false);
		changed |= zUpdateString(behavior, data, newData, TransactionFinder.description(), false);
		changed |= zUpdateString(behavior, data, newData, TransactionFinder.folioNumber(), false);
		changed |= zUpdateDouble(behavior, data, newData, TransactionFinder.price(), false);
		changed |= zUpdateString(behavior, data, newData, TransactionFinder.registrar(), false);
		changed |= zUpdateString(behavior, data, newData, TransactionFinder.rtaCode(), false);
		changed |= zUpdateInteger(behavior, data, newData, TransactionFinder.schemeCode(), false);
		changed |= zUpdateString(behavior, data, newData, TransactionFinder.schemeName(), false);
		changed |= zUpdateString(behavior, data, newData, TransactionFinder.source(), false);
		changed |= zUpdateDouble(behavior, data, newData, TransactionFinder.unitBalance(), false);
		changed |= zUpdateDouble(behavior, data, newData, TransactionFinder.units(), false);
		return changed;
	}

	protected boolean issueUpdatesForPrimaryKeys(TransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		changed |= zUpdateInteger(behavior, data, newData, TransactionFinder.id(), false);
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
		TransactionData data = (TransactionData) behavior.getCurrentDataForRead(this);
		return data.zGetIsIdSet();
	}

	protected int generateId()
	throws MithraBusinessException
	{
		MaxFromTablePrimaryKeyGenerator primaryKeyGenerator =
		MithraPrimaryKeyGenerator.getInstance().getMaxFromTablePrimaryKeyGenerator(TransactionFinder.id(),null);
		return (int)primaryKeyGenerator.getNextId();
	}

	private void checkAndGeneratePrimaryKeys()
	{
		TransactionalBehavior behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		TransactionData data = (TransactionData) behavior.getCurrentDataForWrite(this);
		if (!data.zGetIsIdSet())
		{
			data.setId(generateId());
		}
	}

	public Object readResolve() throws ObjectStreamException
	{
		TransactionAbstract result = (TransactionAbstract) super.readResolve();
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
