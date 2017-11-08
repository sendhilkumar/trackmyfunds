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
public class NetAssetValueData
implements MithraDataObject
{
	private byte isNullBits0 = 0;
	private Timestamp date;
	private int loadId;
	private double netAssetValue;
	private double repurchasePrice;
	private double salePrice;
	private int schemeCode;
	public boolean isDateNull()
	{
		return this.getDate() == null;
	}

	public boolean isSchemeCodeNull()
	{
		return false;
	}

	public boolean isNetAssetValueNull()
	{
		return (isNullBits0 & 1) != 0 ;
	}

	public boolean isRepurchasePriceNull()
	{
		return (isNullBits0 & 1 << 1) != 0 ;
	}

	public boolean isSalePriceNull()
	{
		return (isNullBits0 & 1 << 2) != 0 ;
	}

	public boolean isLoadIdNull()
	{
		return (isNullBits0 & 1 << 3) != 0 ;
	}

	public void zSerializeFullData(ObjectOutput out) throws IOException
	{
		zWriteNullBits(out);
		MithraTimestamp.writeTimezoneInsensitiveTimestamp(out, this.date);
		out.writeInt(this.loadId);
		out.writeDouble(this.netAssetValue);
		out.writeDouble(this.repurchasePrice);
		out.writeDouble(this.salePrice);
		out.writeInt(this.schemeCode);
	}

	private void zWriteNullBits(ObjectOutput out) throws IOException
	{
		out.writeByte(this.isNullBits0);
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
		this.date = TimestampPool.getInstance().getOrAddToCache(value, NetAssetValueFinder.isFullCache(), NetAssetValueFinder.isOffHeap());
	}

	public void setDateNull()
	{
		this.setDate(null);
	}

	public int getLoadId()
	{
		return this.loadId;
	}

	public void setLoadId(int value)
	{
		this.loadId = value;
		isNullBits0 = (byte)((int)isNullBits0 & ~( 1 << 3));
	}

	public double getNetAssetValue()
	{
		return this.netAssetValue;
	}

	public void setNetAssetValue(double value)
	{
		this.netAssetValue = value;
		isNullBits0 = (byte)((int)isNullBits0 & ~( 1));
	}

	public double getRepurchasePrice()
	{
		return this.repurchasePrice;
	}

	public void setRepurchasePrice(double value)
	{
		this.repurchasePrice = value;
		isNullBits0 = (byte)((int)isNullBits0 & ~( 1 << 1));
	}

	public double getSalePrice()
	{
		return this.salePrice;
	}

	public void setSalePrice(double value)
	{
		this.salePrice = value;
		isNullBits0 = (byte)((int)isNullBits0 & ~( 1 << 2));
	}

	public int getSchemeCode()
	{
		return this.schemeCode;
	}

	public void setSchemeCode(int value)
	{
		this.schemeCode = value;
	}

	public void setSchemeCodeNull()
	{
		throw new RuntimeException("should never be called");
	}

	public void setNetAssetValueNull()
	{
		isNullBits0 = (byte)((int)isNullBits0 | 1);
	}

	public void setRepurchasePriceNull()
	{
		isNullBits0 = (byte)((int)isNullBits0 | 1 << 1);
	}

	public void setSalePriceNull()
	{
		isNullBits0 = (byte)((int)isNullBits0 | 1 << 2);
	}

	public void setLoadIdNull()
	{
		isNullBits0 = (byte)((int)isNullBits0 | 1 << 3);
	}

	public byte zGetIsNullBits0()
	{
		return this.isNullBits0;
	}

	public void zSetIsNullBits0(byte newValue)
	{
		this.isNullBits0 = newValue;
	}

	protected void copyInto(NetAssetValueData copy, boolean withRelationships)
	{
		copy.isNullBits0 = this.isNullBits0;
		copy.date = this.date;
		copy.loadId = this.loadId;
		copy.netAssetValue = this.netAssetValue;
		copy.repurchasePrice = this.repurchasePrice;
		copy.salePrice = this.salePrice;
		copy.schemeCode = this.schemeCode;
		if (withRelationships)
		{
		}
	}

	public void zDeserializeFullData(ObjectInput in) throws IOException, ClassNotFoundException
	{
		this.isNullBits0 = in.readByte();
		this.date = TimestampPool.getInstance().getOrAddToCache(MithraTimestamp.readTimezoneInsensitiveTimestamp(in), NetAssetValueFinder.isFullCache(), NetAssetValueFinder.isOffHeap());
		this.loadId = in.readInt();
		this.netAssetValue = in.readDouble();
		this.repurchasePrice = in.readDouble();
		this.salePrice = in.readDouble();
		this.schemeCode = in.readInt();
	}

	public boolean hasSamePrimaryKeyIgnoringAsOfAttributes(MithraDataObject other)
	{
		if (this == other) return true;
		final NetAssetValueData otherData = (NetAssetValueData) other;
		if (!isDateNull() ? !getDate().equals(otherData.getDate()) : !otherData.isDateNull())
		{
			return false;
		}

		if (getSchemeCode() != otherData.getSchemeCode())
		{
			return false;
		}

		return true;
	}

	public void zSerializePrimaryKey(ObjectOutput out) throws IOException
	{
		MithraTimestamp.writeTimezoneInsensitiveTimestamp(out, this.date);
		out.writeInt(this.schemeCode);
	}

	public void zDeserializePrimaryKey(ObjectInput in) throws IOException, ClassNotFoundException
	{
		this.date = TimestampPool.getInstance().getOrAddToCache(MithraTimestamp.readTimezoneInsensitiveTimestamp(in), NetAssetValueFinder.isFullCache(), NetAssetValueFinder.isOffHeap());
		this.schemeCode = in.readInt();
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
		final NetAssetValueData netAssetValueData = (NetAssetValueData) newData;
		this.setLoadId(netAssetValueData.getLoadId());
		if (netAssetValueData.isLoadIdNull()) this.setLoadIdNull();
		this.setNetAssetValue(netAssetValueData.getNetAssetValue());
		if (netAssetValueData.isNetAssetValueNull()) this.setNetAssetValueNull();
		this.setRepurchasePrice(netAssetValueData.getRepurchasePrice());
		if (netAssetValueData.isRepurchasePriceNull()) this.setRepurchasePriceNull();
		this.setSalePrice(netAssetValueData.getSalePrice());
		if (netAssetValueData.isSalePriceNull()) this.setSalePriceNull();
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
		final NetAssetValueData other = (NetAssetValueData) newData;
		if ( isLoadIdNull() != other.isLoadIdNull() || getLoadId() != other.getLoadId())
		{
			return true;
		}

		if ( isNetAssetValueNull() != other.isNetAssetValueNull() || Math.abs(getNetAssetValue() - other.getNetAssetValue()) > toleranceForFloatingPointFields)
		{
			return true;
		}

		if ( isRepurchasePriceNull() != other.isRepurchasePriceNull() || Math.abs(getRepurchasePrice() - other.getRepurchasePrice()) > toleranceForFloatingPointFields)
		{
			return true;
		}

		if ( isSalePriceNull() != other.isSalePriceNull() || Math.abs(getSalePrice() - other.getSalePrice()) > toleranceForFloatingPointFields)
		{
			return true;
		}

		return false;
	}

	public MithraDataObject copy()
	{
		NetAssetValueData copy = new NetAssetValueData();
		this.copyInto(copy, true);
		return copy;
	}

	public MithraDataObject copy(boolean withRelationships)
	{
		NetAssetValueData copy = new NetAssetValueData();
		this.copyInto(copy, withRelationships);
		return copy;
	}

	public String zGetPrintablePrimaryKey()
	{
		String result = "";
		result += "date: "+(isDateNull() ? "null" : PrintablePreparedStatement.timestampFormat.print(getDate().getTime()))+ " / ";
		result += "schemeCode: "+(""+getSchemeCode())+ " / ";
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
		return "tracker.domain.NetAssetValueData";
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
		return NetAssetValueFinder.getMithraObjectPortal();
	}

	public MithraObjectPortal zGetMithraObjectPortal()
	{
		return NetAssetValueFinder.getMithraObjectPortal();
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
		return "tracker.domain.NetAssetValueData";
	}
}
