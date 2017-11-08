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
public class NetAssetValueListAbstract extends DelegatingList<NetAssetValue> implements MithraTransactionalList<NetAssetValue>
{
	public NetAssetValueListAbstract()
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this);
	}

	public NetAssetValueListAbstract(int initialSize)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, initialSize);
	}

	public NetAssetValueListAbstract(Collection c)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, c);
	}

	public NetAssetValueListAbstract(com.gs.fw.finder.Operation operation)
	{
		super(operation);
		this.setDelegated(AbstractTransactionalOperationBasedList.DEFAULT);
	}

	public NetAssetValue[] elements()
	{
		NetAssetValue[] result = new NetAssetValue[size()];
		this.getDelegated().toArray(this, result);
		return result;
	}

	public NetAssetValueList intersection(NetAssetValueList another)
	{
		return (NetAssetValueList)super.intersection(another);
	}

	public NetAssetValue getNetAssetValueAt(int index)
	{
		return (NetAssetValue)this.get(index);
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return NetAssetValueFinder.getMithraObjectPortal();
	}

	public NetAssetValueList getNonPersistentCopy()
	{
		NetAssetValueList result = new NetAssetValueList();
		zCopyNonPersistentInto(result);
		return result;
	}

	public NetAssetValueList asAdhoc()
	{
		return (NetAssetValueList) super.asAdhoc();
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
	public MutableList<NetAssetValue> asGscList()
	{
		return ListAdapter.adapt(this);
	}

	public NetAssetValueList merge(MithraTransactionalList<NetAssetValue> incoming, TopLevelMergeOptions<NetAssetValue> mergeOptions)
	{
		return (NetAssetValueList) super.merge(incoming, mergeOptions);
	}

	public NetAssetValueList getDetachedCopy()
	{
		NetAssetValueList result = new NetAssetValueList();
		zDetachInto(result);
		return result;
	}

	public void zMakeDetached(Operation op, Object previousDetachedList)
	{
		super.zMakeDetached(op, previousDetachedList);
	}

	public void setDate(Timestamp newValue)
	{
		zSetTimestamp(NetAssetValueFinder.date(), newValue);
	}

	public void setLoadId(int newValue)
	{
		zSetInteger(NetAssetValueFinder.loadId(), newValue);
	}

	public void setNetAssetValue(double newValue)
	{
		zSetDouble(NetAssetValueFinder.netAssetValue(), newValue);
	}

	public void setRepurchasePrice(double newValue)
	{
		zSetDouble(NetAssetValueFinder.repurchasePrice(), newValue);
	}

	public void setSalePrice(double newValue)
	{
		zSetDouble(NetAssetValueFinder.salePrice(), newValue);
	}

	public void setSchemeCode(int newValue)
	{
		zSetInteger(NetAssetValueFinder.schemeCode(), newValue);
	}

	public void setNetAssetValueNull()
	{
		zSetAttributeNull(NetAssetValueFinder.netAssetValue());
	}

	public void setRepurchasePriceNull()
	{
		zSetAttributeNull(NetAssetValueFinder.repurchasePrice());
	}

	public void setSalePriceNull()
	{
		zSetAttributeNull(NetAssetValueFinder.salePrice());
	}

	public void setLoadIdNull()
	{
		zSetAttributeNull(NetAssetValueFinder.loadId());
	}
}
