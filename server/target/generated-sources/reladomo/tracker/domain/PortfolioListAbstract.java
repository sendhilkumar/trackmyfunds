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
public class PortfolioListAbstract extends DelegatingList<Portfolio> implements MithraTransactionalList<Portfolio>
{
	public PortfolioListAbstract()
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this);
	}

	public PortfolioListAbstract(int initialSize)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, initialSize);
	}

	public PortfolioListAbstract(Collection c)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, c);
	}

	public PortfolioListAbstract(com.gs.fw.finder.Operation operation)
	{
		super(operation);
		this.setDelegated(AbstractTransactionalOperationBasedList.DEFAULT);
	}

	public Portfolio[] elements()
	{
		Portfolio[] result = new Portfolio[size()];
		this.getDelegated().toArray(this, result);
		return result;
	}

	public PortfolioList intersection(PortfolioList another)
	{
		return (PortfolioList)super.intersection(another);
	}

	public Portfolio getPortfolioAt(int index)
	{
		return (Portfolio)this.get(index);
	}

	/**
	* Relationship Expression:<pre>
	PortfolioHolding.portfolioId = this.id</pre>
	* @return holdings
	*/
	public PortfolioHoldingList getHoldings()
	{
		return (PortfolioHoldingList) this.getDelegated().resolveRelationship(this, PortfolioFinder.holdings());
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return PortfolioFinder.getMithraObjectPortal();
	}

	public PortfolioList getNonPersistentCopy()
	{
		PortfolioList result = new PortfolioList();
		zCopyNonPersistentInto(result);
		return result;
	}

	public PortfolioList asAdhoc()
	{
		return (PortfolioList) super.asAdhoc();
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
	public MutableList<Portfolio> asGscList()
	{
		return ListAdapter.adapt(this);
	}

	public PortfolioList merge(MithraTransactionalList<Portfolio> incoming, TopLevelMergeOptions<Portfolio> mergeOptions)
	{
		return (PortfolioList) super.merge(incoming, mergeOptions);
	}

	public PortfolioList getDetachedCopy()
	{
		PortfolioList result = new PortfolioList();
		zDetachInto(result);
		return result;
	}

	public void zMakeDetached(Operation op, Object previousDetachedList)
	{
		super.zMakeDetached(op, previousDetachedList);
	}

	public void setId(int newValue)
	{
		zSetInteger(PortfolioFinder.id(), newValue);
	}

	public void setName(String newValue)
	{
		zSetString(PortfolioFinder.name(), newValue);
	}
}
