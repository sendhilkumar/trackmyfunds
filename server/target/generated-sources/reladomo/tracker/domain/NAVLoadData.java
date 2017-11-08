package tracker.domain;
import java.util.*;
import java.sql.Timestamp;
import java.sql.Date;
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
import com.gs.fw.common.mithra.finder.PrintablePreparedStatement;
import com.gs.fw.common.mithra.finder.RelatedFinder;
import com.gs.fw.common.mithra.cache.offheap.MithraOffHeapDataObject;
import com.gs.fw.common.mithra.cache.offheap.OffHeapDataStorage;
/**
* This file was automatically generated using Mithra 16.6.1. Please do not modify it.
* Add custom logic to its subclass instead.
*/
public class NAVLoadData
implements MithraDataObject
{
	private int id;
	private Timestamp latestNavDate;
	private Timestamp loadTime;
	private Timestamp maxOccuringDate;
	public boolean isIdNull()
	{
		return false;
	}

	public boolean isLatestNavDateNull()
	{
		return this.getLatestNavDate() == null;
	}

	public boolean isLoadTimeNull()
	{
		return this.getLoadTime() == null;
	}

	public boolean isMaxOccuringDateNull()
	{
		return this.getMaxOccuringDate() == null;
	}

	public void zSerializeFullData(ObjectOutput out) throws IOException
	{
		zWriteNullBits(out);
		out.writeInt(this.id);
		out.writeBoolean(_isIdSet);
		MithraTimestamp.writeTimezoneInsensitiveTimestamp(out, this.latestNavDate);
		MithraTimestamp.writeTimezoneInsensitiveTimestamp(out, this.loadTime);
		MithraTimestamp.writeTimezoneInsensitiveTimestamp(out, this.maxOccuringDate);
	}

	private void zWriteNullBits(ObjectOutput out) throws IOException
	{
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int value)
	{
		this.id = value;
		_isIdSet = true;
	}

	public void setIdNull()
	{
		throw new RuntimeException("should never be called");
	}

	public Timestamp getLatestNavDate()
	{
		return this.latestNavDate;
	}

	public long zGetLatestNavDateAsLong()
	{
		if (latestNavDate == null) return TimestampPool.OFF_HEAP_NULL;
		return latestNavDate.getTime();
	}

	public void setLatestNavDate(Timestamp value)
	{
		this.latestNavDate = TimestampPool.getInstance().getOrAddToCache(value, NAVLoadFinder.isFullCache(), NAVLoadFinder.isOffHeap());
	}

	public void setLatestNavDateNull()
	{
		this.setLatestNavDate(null);
	}

	public Timestamp getLoadTime()
	{
		return this.loadTime;
	}

	public long zGetLoadTimeAsLong()
	{
		if (loadTime == null) return TimestampPool.OFF_HEAP_NULL;
		return loadTime.getTime();
	}

	public void setLoadTime(Timestamp value)
	{
		this.loadTime = TimestampPool.getInstance().getOrAddToCache(value, NAVLoadFinder.isFullCache(), NAVLoadFinder.isOffHeap());
	}

	public void setLoadTimeNull()
	{
		this.setLoadTime(null);
	}

	public Timestamp getMaxOccuringDate()
	{
		return this.maxOccuringDate;
	}

	public long zGetMaxOccuringDateAsLong()
	{
		if (maxOccuringDate == null) return TimestampPool.OFF_HEAP_NULL;
		return maxOccuringDate.getTime();
	}

	public void setMaxOccuringDate(Timestamp value)
	{
		this.maxOccuringDate = TimestampPool.getInstance().getOrAddToCache(value, NAVLoadFinder.isFullCache(), NAVLoadFinder.isOffHeap());
	}

	public void setMaxOccuringDateNull()
	{
		this.setMaxOccuringDate(null);
	}

	public boolean _isIdSet;
	public boolean zGetIsIdSet()
	{
		return _isIdSet;
	}

	protected void copyInto(NAVLoadData copy, boolean withRelationships)
	{
		copy.id = this.id;
		copy.latestNavDate = this.latestNavDate;
		copy.loadTime = this.loadTime;
		copy.maxOccuringDate = this.maxOccuringDate;
		copy._isIdSet = this._isIdSet;
		if (withRelationships)
		{
		}
	}

	public void zDeserializeFullData(ObjectInput in) throws IOException, ClassNotFoundException
	{
		this.id = in.readInt();
		_isIdSet = in.readBoolean();
		this.latestNavDate = TimestampPool.getInstance().getOrAddToCache(MithraTimestamp.readTimezoneInsensitiveTimestamp(in), NAVLoadFinder.isFullCache(), NAVLoadFinder.isOffHeap());
		this.loadTime = TimestampPool.getInstance().getOrAddToCache(MithraTimestamp.readTimezoneInsensitiveTimestamp(in), NAVLoadFinder.isFullCache(), NAVLoadFinder.isOffHeap());
		this.maxOccuringDate = TimestampPool.getInstance().getOrAddToCache(MithraTimestamp.readTimezoneInsensitiveTimestamp(in), NAVLoadFinder.isFullCache(), NAVLoadFinder.isOffHeap());
	}

	public boolean hasSamePrimaryKeyIgnoringAsOfAttributes(MithraDataObject other)
	{
		if (this == other) return true;
		final NAVLoadData otherData = (NAVLoadData) other;
		if (getId() != otherData.getId())
		{
			return false;
		}

		return true;
	}

	public void zSerializePrimaryKey(ObjectOutput out) throws IOException
	{
		out.writeInt(this.id);
	}

	public void zDeserializePrimaryKey(ObjectInput in) throws IOException, ClassNotFoundException
	{
		this.id = in.readInt();
	}

	public void clearRelationships()
	{
		clearAllDirectRefs();
	}

	public void clearAllDirectRefs()
	{
	}

	public void zSerializeRelationships(ObjectOutputStream out) throws IOException
	{
	}

	public void zDeserializeRelationships(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
	}

	public MithraOffHeapDataObject zCopyOffHeap()
	{
		throw new RuntimeException("off heap no supported");
	}

	public void copyNonPkAttributes(MithraDataObject newData)
	{
		final NAVLoadData nAVLoadData = (NAVLoadData) newData;
		this.setLatestNavDate(nAVLoadData.getLatestNavDate());
		this.setLoadTime(nAVLoadData.getLoadTime());
		this.setMaxOccuringDate(nAVLoadData.getMaxOccuringDate());
	}

	public byte zGetDataVersion() 
	{
		return (byte)0; 
	}

	public void zSetDataVersion(byte version) 
	{
	}
	// only used by dated objects
	public void zIncrementDataVersion() 
	{
	}

	public boolean zNonPrimaryKeyAttributesChanged(MithraDataObject newData)
	{
		return this.zNonPrimaryKeyAttributesChanged(newData, 0.0);
	}

	public boolean zNonPrimaryKeyAttributesChanged(MithraDataObject newData, double toleranceForFloatingPointFields)
	{
		final NAVLoadData other = (NAVLoadData) newData;
		if (!isLatestNavDateNull() ? !getLatestNavDate().equals(other.getLatestNavDate()) : !other.isLatestNavDateNull())
		{
			return true;
		}

		if (!isLoadTimeNull() ? !getLoadTime().equals(other.getLoadTime()) : !other.isLoadTimeNull())
		{
			return true;
		}

		if (!isMaxOccuringDateNull() ? !getMaxOccuringDate().equals(other.getMaxOccuringDate()) : !other.isMaxOccuringDateNull())
		{
			return true;
		}

		return false;
	}

	public MithraDataObject copy()
	{
		NAVLoadData copy = new NAVLoadData();
		this.copyInto(copy, true);
		return copy;
	}

	public MithraDataObject copy(boolean withRelationships)
	{
		NAVLoadData copy = new NAVLoadData();
		this.copyInto(copy, withRelationships);
		return copy;
	}

	public String zGetPrintablePrimaryKey()
	{
		String result = "";
		result += "id: "+(""+getId())+ " / ";
		return result;
	}

	public boolean zAsOfAttributesFromEquals(MithraDataObject other)
	{
		return true;
	}

	public boolean zAsOfAttributesChanged(MithraDataObject other)
	{
		return false;
	}

	public void zWriteDataClassName(ObjectOutput out) throws IOException
	{
	}

	public String zReadDataClassName(ObjectInput in) throws IOException, ClassNotFoundException
	{
		return "tracker.domain.NAVLoadData";
	}

	public boolean changed(MithraDataObject newData)
	{
		if(zNonPrimaryKeyAttributesChanged(newData))
		{
			return true;
		}
		else
		{
			return zAsOfAttributesChanged(newData);
		}
	}

	public boolean zHasSameNullPrimaryKeyAttributes(MithraDataObject newData)
	{
		return true;
	}

	public MithraObjectPortal zGetMithraObjectPortal(int hierarchyDepth)
	{
		return NAVLoadFinder.getMithraObjectPortal();
	}

	public MithraObjectPortal zGetMithraObjectPortal()
	{
		return NAVLoadFinder.getMithraObjectPortal();
	}

	public Number zGetIdentityValue()
	{
		return null;
	}

	public boolean zHasIdentity()
	{
		return false;
	}

	public void zSetIdentity(Number identityValue)
	{
	}

	public String zGetSerializationClassName()
	{
		return "tracker.domain.NAVLoadData";
	}
}
