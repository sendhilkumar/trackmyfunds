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
public class LatestNAVDateListAbstract extends DelegatingList<LatestNAVDate> implements MithraTransactionalList<LatestNAVDate>
{
	public LatestNAVDateListAbstract()
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this);
	}

	public LatestNAVDateListAbstract(int initialSize)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, initialSize);
	}

	public LatestNAVDateListAbstract(Collection c)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, c);
	}

	public LatestNAVDateListAbstract(com.gs.fw.finder.Operation operation)
	{
		super(operation);
		this.setDelegated(AbstractTransactionalOperationBasedList.DEFAULT);
	}

	public LatestNAVDate[] elements()
	{
		LatestNAVDate[] result = new LatestNAVDate[size()];
		this.getDelegated().toArray(this, result);
		return result;
	}

	public LatestNAVDateList intersection(LatestNAVDateList another)
	{
		return (LatestNAVDateList)super.intersection(another);
	}

	public LatestNAVDate getLatestNAVDateAt(int index)
	{
		return (LatestNAVDate)this.get(index);
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return LatestNAVDateFinder.getMithraObjectPortal();
	}

	public LatestNAVDateList getNonPersistentCopy()
	{
		LatestNAVDateList result = new LatestNAVDateList();
		zCopyNonPersistentInto(result);
		return result;
	}

	public LatestNAVDateList asAdhoc()
	{
		return (LatestNAVDateList) super.asAdhoc();
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
	public MutableList<LatestNAVDate> asGscList()
	{
		return ListAdapter.adapt(this);
	}

	public LatestNAVDateList merge(MithraTransactionalList<LatestNAVDate> incoming, TopLevelMergeOptions<LatestNAVDate> mergeOptions)
	{
		return (LatestNAVDateList) super.merge(incoming, mergeOptions);
	}

	public LatestNAVDateList getDetachedCopy()
	{
		LatestNAVDateList result = new LatestNAVDateList();
		zDetachInto(result);
		return result;
	}

	public void zMakeDetached(Operation op, Object previousDetachedList)
	{
		super.zMakeDetached(op, previousDetachedList);
	}

	public void setDate(Timestamp newValue)
	{
		zSetTimestamp(LatestNAVDateFinder.date(), newValue);
	}
}
