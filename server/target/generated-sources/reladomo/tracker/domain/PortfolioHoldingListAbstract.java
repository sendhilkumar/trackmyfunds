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
public class PortfolioHoldingListAbstract extends DelegatingList<PortfolioHolding> implements MithraTransactionalList<PortfolioHolding>
{
	public PortfolioHoldingListAbstract()
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this);
	}

	public PortfolioHoldingListAbstract(int initialSize)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, initialSize);
	}

	public PortfolioHoldingListAbstract(Collection c)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, c);
	}

	public PortfolioHoldingListAbstract(com.gs.fw.finder.Operation operation)
	{
		super(operation);
		this.setDelegated(AbstractTransactionalOperationBasedList.DEFAULT);
	}

	public PortfolioHolding[] elements()
	{
		PortfolioHolding[] result = new PortfolioHolding[size()];
		this.getDelegated().toArray(this, result);
		return result;
	}

	public PortfolioHoldingList intersection(PortfolioHoldingList another)
	{
		return (PortfolioHoldingList)super.intersection(another);
	}

	public PortfolioHolding getPortfolioHoldingAt(int index)
	{
		return (PortfolioHolding)this.get(index);
	}

	/**
	* Relationship Expression:<pre>
	Scheme.code = this.schemeCode</pre>
	* @return The scheme
	*/
	public SchemeList getSchemes()
	{
		return (SchemeList) this.getDelegated().resolveRelationship(this, PortfolioHoldingFinder.scheme());
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return PortfolioHoldingFinder.getMithraObjectPortal();
	}

	public PortfolioHoldingList getNonPersistentCopy()
	{
		PortfolioHoldingList result = new PortfolioHoldingList();
		zCopyNonPersistentInto(result);
		return result;
	}

	public PortfolioHoldingList asAdhoc()
	{
		return (PortfolioHoldingList) super.asAdhoc();
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
	public MutableList<PortfolioHolding> asGscList()
	{
		return ListAdapter.adapt(this);
	}

	public PortfolioHoldingList merge(MithraTransactionalList<PortfolioHolding> incoming, TopLevelMergeOptions<PortfolioHolding> mergeOptions)
	{
		return (PortfolioHoldingList) super.merge(incoming, mergeOptions);
	}

	public PortfolioHoldingList getDetachedCopy()
	{
		PortfolioHoldingList result = new PortfolioHoldingList();
		zDetachInto(result);
		return result;
	}

	public void zMakeDetached(Operation op, Object previousDetachedList)
	{
		super.zMakeDetached(op, previousDetachedList);
	}

	public void setPortfolioId(int newValue)
	{
		zSetInteger(PortfolioHoldingFinder.portfolioId(), newValue);
	}

	public void setSchemeCode(int newValue)
	{
		zSetInteger(PortfolioHoldingFinder.schemeCode(), newValue);
	}
}
