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
public class LatestNAVDateData
implements MithraDataObject
{
	private Timestamp date;
	public boolean isDateNull()
	{
		return this.getDate() == null;
	}

	public void zSerializeFullData(ObjectOutput out) throws IOException
	{
		zWriteNullBits(out);
		MithraTimestamp.writeTimezoneInsensitiveTimestamp(out, this.date);
	}

	private void zWriteNullBits(ObjectOutput out) throws IOException
	{
	}

	public Timestamp getDate()
	{
		return this.date;
	}

	public long zGetDateAsLong()
	{
		if (date == null) return TimestampPool.OFF_HEAP_NULL;
		return date.getTime();
	}

	public void setDate(Timestamp value)
	{
		this.date = TimestampPool.getInstance().getOrAddToCache(value, LatestNAVDateFinder.isFullCache(), LatestNAVDateFinder.isOffHeap());
	}

	public void setDateNull()
	{
		this.setDate(null);
	}

	protected void copyInto(LatestNAVDateData copy, boolean withRelationships)
	{
		copy.date = this.date;
		if (withRelationships)
		{
		}
	}

	public void zDeserializeFullData(ObjectInput in) throws IOException, ClassNotFoundException
	{
		this.date = TimestampPool.getInstance().getOrAddToCache(MithraTimestamp.readTimezoneInsensitiveTimestamp(in), LatestNAVDateFinder.isFullCache(), LatestNAVDateFinder.isOffHeap());
	}

	public boolean hasSamePrimaryKeyIgnoringAsOfAttributes(MithraDataObject other)
	{
		if (this == other) return true;
		final LatestNAVDateData otherData = (LatestNAVDateData) other;
		if (!isDateNull() ? !getDate().equals(otherData.getDate()) : !otherData.isDateNull())
		{
			return false;
		}

		return true;
	}

	public void zSerializePrimaryKey(ObjectOutput out) throws IOException
	{
		MithraTimestamp.writeTimezoneInsensitiveTimestamp(out, this.date);
	}

	public void zDeserializePrimaryKey(ObjectInput in) throws IOException, ClassNotFoundException
	{
		this.date = TimestampPool.getInstance().getOrAddToCache(MithraTimestamp.readTimezoneInsensitiveTimestamp(in), LatestNAVDateFinder.isFullCache(), LatestNAVDateFinder.isOffHeap());
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
		final LatestNAVDateData latestNAVDateData = (LatestNAVDateData) newData;
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
		final LatestNAVDateData other = (LatestNAVDateData) newData;
		return false;
	}

	public MithraDataObject copy()
	{
		LatestNAVDateData copy = new LatestNAVDateData();
		this.copyInto(copy, true);
		return copy;
	}

	public MithraDataObject copy(boolean withRelationships)
	{
		LatestNAVDateData copy = new LatestNAVDateData();
		this.copyInto(copy, withRelationships);
		return copy;
	}

	public String zGetPrintablePrimaryKey()
	{
		String result = "";
		result += "date: "+(isDateNull() ? "null" : PrintablePreparedStatement.timestampFormat.print(getDate().getTime()))+ " / ";
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
		return "tracker.domain.LatestNAVDateData";
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
		return LatestNAVDateFinder.getMithraObjectPortal();
	}

	public MithraObjectPortal zGetMithraObjectPortal()
	{
		return LatestNAVDateFinder.getMithraObjectPortal();
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
		return "tracker.domain.LatestNAVDateData";
	}
}
