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
public class NAVLoadListAbstract extends DelegatingList<NAVLoad> implements MithraTransactionalList<NAVLoad>
{
	public NAVLoadListAbstract()
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this);
	}

	public NAVLoadListAbstract(int initialSize)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, initialSize);
	}

	public NAVLoadListAbstract(Collection c)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, c);
	}

	public NAVLoadListAbstract(com.gs.fw.finder.Operation operation)
	{
		super(operation);
		this.setDelegated(AbstractTransactionalOperationBasedList.DEFAULT);
	}

	public NAVLoad[] elements()
	{
		NAVLoad[] result = new NAVLoad[size()];
		this.getDelegated().toArray(this, result);
		return result;
	}

	public NAVLoadList intersection(NAVLoadList another)
	{
		return (NAVLoadList)super.intersection(another);
	}

	public NAVLoad getNAVLoadAt(int index)
	{
		return (NAVLoad)this.get(index);
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return NAVLoadFinder.getMithraObjectPortal();
	}

	public NAVLoadList getNonPersistentCopy()
	{
		NAVLoadList result = new NAVLoadList();
		zCopyNonPersistentInto(result);
		return result;
	}

	public NAVLoadList asAdhoc()
	{
		return (NAVLoadList) super.asAdhoc();
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
	public MutableList<NAVLoad> asGscList()
	{
		return ListAdapter.adapt(this);
	}

	public NAVLoadList merge(MithraTransactionalList<NAVLoad> incoming, TopLevelMergeOptions<NAVLoad> mergeOptions)
	{
		return (NAVLoadList) super.merge(incoming, mergeOptions);
	}

	public NAVLoadList getDetachedCopy()
	{
		NAVLoadList result = new NAVLoadList();
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

	public void setId(int newValue)
	{
		zSetInteger(NAVLoadFinder.id(), newValue);
	}

	public void setLatestNavDate(Timestamp newValue)
	{
		zSetTimestamp(NAVLoadFinder.latestNavDate(), newValue);
	}

	public void setLoadTime(Timestamp newValue)
	{
		zSetTimestamp(NAVLoadFinder.loadTime(), newValue);
	}

	public void setMaxOccuringDate(Timestamp newValue)
	{
		zSetTimestamp(NAVLoadFinder.maxOccuringDate(), newValue);
	}
}
