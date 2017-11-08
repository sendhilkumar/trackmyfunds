package tracker.domain;
import java.util.*;
import com.gs.fw.common.mithra.MithraList;
import com.gs.fw.common.mithra.MithraManager;
import com.gs.fw.common.mithra.MithraManagerProvider;
import com.gs.fw.common.mithra.finder.*;
import com.gs.fw.common.mithra.list.*;
import com.gs.fw.common.mithra.list.merge.TopLevelMergeOptions;
import com.gs.fw.finder.OrderBy;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.list.mutable.ListAdapter;
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
/**
* This file was automatically generated using Mithra 16.6.1. Please do not modify it.
* Add custom logic to its subclass instead.
*/
// Generated from templates/transactional/ListAbstract.jsp
public class TransactionListAbstract extends DelegatingList<Transaction> implements MithraTransactionalList<Transaction>
{
	public TransactionListAbstract()
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this);
	}

	public TransactionListAbstract(int initialSize)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, initialSize);
	}

	public TransactionListAbstract(Collection c)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, c);
	}

	public TransactionListAbstract(com.gs.fw.finder.Operation operation)
	{
		super(operation);
		this.setDelegated(AbstractTransactionalOperationBasedList.DEFAULT);
	}

	public Transaction[] elements()
	{
		Transaction[] result = new Transaction[size()];
		this.getDelegated().toArray(this, result);
		return result;
	}

	public TransactionList intersection(TransactionList another)
	{
		return (TransactionList)super.intersection(another);
	}

	public Transaction getTransactionAt(int index)
	{
		return (Transaction)this.get(index);
	}

	/**
	* Relationship Expression:<pre>
	Scheme.code = this.schemeCode</pre>
	* @see Scheme#getTransactions() reverse relationship Scheme.getTransactions()
	* @return The scheme
	*/
	public SchemeList getSchemes()
	{
		return (SchemeList) this.getDelegated().resolveRelationship(this, TransactionFinder.scheme());
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return TransactionFinder.getMithraObjectPortal();
	}

	public TransactionList getNonPersistentCopy()
	{
		TransactionList result = new TransactionList();
		zCopyNonPersistentInto(result);
		return result;
	}

	public TransactionList asAdhoc()
	{
		return (TransactionList) super.asAdhoc();
	}

	public MithraTransactionalList getNonPersistentGenericCopy()
	{
		return this.getNonPersistentCopy();
	}

	/**
	* Return a view of this list that implements GS Collections MutableList API.
	* Since the returned list will be operation-based, it is effectively read-only,
	* so mutating methods will throw a RuntimeException.
	* (Implemented by a light-weight adapter, not a copy)
	*/
	public MutableList<Transaction> asGscList()
	{
		return ListAdapter.adapt(this);
	}

	public TransactionList merge(MithraTransactionalList<Transaction> incoming, TopLevelMergeOptions<Transaction> mergeOptions)
	{
		return (TransactionList) super.merge(incoming, mergeOptions);
	}

	public TransactionList getDetachedCopy()
	{
		TransactionList result = new TransactionList();
		zDetachInto(result);
		return result;
	}

	public void zMakeDetached(Operation op, Object previousDetachedList)
	{
		super.zMakeDetached(op, previousDetachedList);
	}

	protected void generateAndSetPrimaryKeys()
	{
	}

	public void setAmount(double newValue)
	{
		zSetDouble(TransactionFinder.amount(), newValue);
	}

	public void setDate(Timestamp newValue)
	{
		zSetTimestamp(TransactionFinder.date(), newValue);
	}

	public void setDescription(String newValue)
	{
		zSetString(TransactionFinder.description(), newValue);
	}

	public void setFolioNumber(String newValue)
	{
		zSetString(TransactionFinder.folioNumber(), newValue);
	}

	public void setId(int newValue)
	{
		zSetInteger(TransactionFinder.id(), newValue);
	}

	public void setPrice(double newValue)
	{
		zSetDouble(TransactionFinder.price(), newValue);
	}

	public void setRegistrar(String newValue)
	{
		zSetString(TransactionFinder.registrar(), newValue);
	}

	public void setRtaCode(String newValue)
	{
		zSetString(TransactionFinder.rtaCode(), newValue);
	}

	public void setSchemeCode(int newValue)
	{
		zSetInteger(TransactionFinder.schemeCode(), newValue);
	}

	public void setSchemeName(String newValue)
	{
		zSetString(TransactionFinder.schemeName(), newValue);
	}

	public void setSource(String newValue)
	{
		zSetString(TransactionFinder.source(), newValue);
	}

	public void setUnitBalance(double newValue)
	{
		zSetDouble(TransactionFinder.unitBalance(), newValue);
	}

	public void setUnits(double newValue)
	{
		zSetDouble(TransactionFinder.units(), newValue);
	}

	public void setUnitsNull()
	{
		zSetAttributeNull(TransactionFinder.units());
	}

	public void setPriceNull()
	{
		zSetAttributeNull(TransactionFinder.price());
	}

	public void setUnitBalanceNull()
	{
		zSetAttributeNull(TransactionFinder.unitBalance());
	}
}
