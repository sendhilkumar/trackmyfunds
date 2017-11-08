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
public class SchemeListAbstract extends DelegatingList<Scheme> implements MithraTransactionalList<Scheme>
{
	public SchemeListAbstract()
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this);
	}

	public SchemeListAbstract(int initialSize)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, initialSize);
	}

	public SchemeListAbstract(Collection c)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, c);
	}

	public SchemeListAbstract(com.gs.fw.finder.Operation operation)
	{
		super(operation);
		this.setDelegated(AbstractTransactionalOperationBasedList.DEFAULT);
	}

	public Scheme[] elements()
	{
		Scheme[] result = new Scheme[size()];
		this.getDelegated().toArray(this, result);
		return result;
	}

	public SchemeList intersection(SchemeList another)
	{
		return (SchemeList)super.intersection(another);
	}

	public Scheme getSchemeAt(int index)
	{
		return (Scheme)this.get(index);
	}

	/**
	* Relationship Expression:<pre>
	this.code = Transaction.schemeCode</pre>
	* @see Transaction#getScheme() reverse relationship Transaction.getScheme()
	* @return transactions
	*/
	public TransactionList getTransactions()
	{
		return (TransactionList) this.getDelegated().resolveRelationship(this, SchemeFinder.transactions());
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return SchemeFinder.getMithraObjectPortal();
	}

	public SchemeList getNonPersistentCopy()
	{
		SchemeList result = new SchemeList();
		zCopyNonPersistentInto(result);
		return result;
	}

	public SchemeList asAdhoc()
	{
		return (SchemeList) super.asAdhoc();
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
	public MutableList<Scheme> asGscList()
	{
		return ListAdapter.adapt(this);
	}

	public SchemeList merge(MithraTransactionalList<Scheme> incoming, TopLevelMergeOptions<Scheme> mergeOptions)
	{
		return (SchemeList) super.merge(incoming, mergeOptions);
	}

	public SchemeList getDetachedCopy()
	{
		SchemeList result = new SchemeList();
		zDetachInto(result);
		return result;
	}

	public void zMakeDetached(Operation op, Object previousDetachedList)
	{
		super.zMakeDetached(op, previousDetachedList);
	}

	public void setAmc(String newValue)
	{
		zSetString(SchemeFinder.amc(), newValue);
	}

	public void setCode(int newValue)
	{
		zSetInteger(SchemeFinder.code(), newValue);
	}

	public void setName(String newValue)
	{
		zSetString(SchemeFinder.name(), newValue);
	}

	public void setRtaCode(String newValue)
	{
		zSetString(SchemeFinder.rtaCode(), newValue);
	}
}
