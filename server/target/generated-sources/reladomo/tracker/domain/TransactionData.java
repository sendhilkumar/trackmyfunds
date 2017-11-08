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
public class TransactionData
implements MithraDataObject
{
	private Object[] _relationships;
	private byte isNullBits0 = 0;
	private double amount;
	private Timestamp date;
	private String description;
	private String folioNumber;
	private int id;
	private double price;
	private String registrar;
	private String rtaCode;
	private int schemeCode;
	private String schemeName;
	private String source;
	private double unitBalance;
	private double units;
	public boolean isAmountNull()
	{
		return false;
	}

	public boolean isDateNull()
	{
		return this.getDate() == null;
	}

	public boolean isDescriptionNull()
	{
		return this.getDescription() == null;
	}

	public boolean isFolioNumberNull()
	{
		return this.getFolioNumber() == null;
	}

	public boolean isIdNull()
	{
		return false;
	}

	public boolean isRegistrarNull()
	{
		return this.getRegistrar() == null;
	}

	public boolean isRtaCodeNull()
	{
		return this.getRtaCode() == null;
	}

	public boolean isSchemeCodeNull()
	{
		return false;
	}

	public boolean isSchemeNameNull()
	{
		return this.getSchemeName() == null;
	}

	public boolean isSourceNull()
	{
		return this.getSource() == null;
	}

	public boolean isUnitsNull()
	{
		return (isNullBits0 & 1) != 0 ;
	}

	public boolean isPriceNull()
	{
		return (isNullBits0 & 1 << 1) != 0 ;
	}

	public boolean isUnitBalanceNull()
	{
		return (isNullBits0 & 1 << 2) != 0 ;
	}

	public void zSerializeFullData(ObjectOutput out) throws IOException
	{
		zWriteNullBits(out);
		out.writeDouble(this.amount);
		MithraTimestamp.writeTimezoneInsensitiveTimestamp(out, this.date);
		out.writeObject(this.description);
		out.writeObject(this.folioNumber);
		out.writeInt(this.id);
		out.writeBoolean(_isIdSet);
		out.writeDouble(this.price);
		out.writeObject(this.registrar);
		out.writeObject(this.rtaCode);
		out.writeInt(this.schemeCode);
		out.writeObject(this.schemeName);
		out.writeObject(this.source);
		out.writeDouble(this.unitBalance);
		out.writeDouble(this.units);
	}

	private void zWriteNullBits(ObjectOutput out) throws IOException
	{
		out.writeByte(this.isNullBits0);
	}

	public double getAmount()
	{
		return this.amount;
	}

	public void setAmount(double value)
	{
		this.amount = value;
	}

	public void setAmountNull()
	{
		throw new RuntimeException("should never be called");
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
		this.date = TimestampPool.getInstance().getOrAddToCache(value, TransactionFinder.isFullCache(), TransactionFinder.isOffHeap());
	}

	public void setDateNull()
	{
		this.setDate(null);
	}

	public String getDescription()
	{
		return this.description;
	}

	public int zGetDescriptionAsInt()
	{
		return StringPool.getInstance().getOffHeapAddressWithoutAdding(description);
	}

	public void setDescription(String value)
	{
		this.description = StringPool.getInstance().getOrAddToCache(value, TransactionFinder.isFullCache());
	}

	public void setDescriptionNull()
	{
		this.setDescription(null);
	}

	public String getFolioNumber()
	{
		return this.folioNumber;
	}

	public int zGetFolioNumberAsInt()
	{
		return StringPool.getInstance().getOffHeapAddressWithoutAdding(folioNumber);
	}

	public void setFolioNumber(String value)
	{
		this.folioNumber = StringPool.getInstance().getOrAddToCache(value, TransactionFinder.isFullCache());
	}

	public void setFolioNumberNull()
	{
		this.setFolioNumber(null);
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

	public double getPrice()
	{
		return this.price;
	}

	public void setPrice(double value)
	{
		this.price = value;
		isNullBits0 = (byte)((int)isNullBits0 & ~( 1 << 1));
	}

	public String getRegistrar()
	{
		return this.registrar;
	}

	public int zGetRegistrarAsInt()
	{
		return StringPool.getInstance().getOffHeapAddressWithoutAdding(registrar);
	}

	public void setRegistrar(String value)
	{
		this.registrar = StringPool.getInstance().getOrAddToCache(value, TransactionFinder.isFullCache());
	}

	public void setRegistrarNull()
	{
		this.setRegistrar(null);
	}

	public String getRtaCode()
	{
		return this.rtaCode;
	}

	public int zGetRtaCodeAsInt()
	{
		return StringPool.getInstance().getOffHeapAddressWithoutAdding(rtaCode);
	}

	public void setRtaCode(String value)
	{
		this.rtaCode = StringPool.getInstance().getOrAddToCache(value, TransactionFinder.isFullCache());
	}

	public void setRtaCodeNull()
	{
		this.setRtaCode(null);
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

	public String getSchemeName()
	{
		return this.schemeName;
	}

	public int zGetSchemeNameAsInt()
	{
		return StringPool.getInstance().getOffHeapAddressWithoutAdding(schemeName);
	}

	public void setSchemeName(String value)
	{
		this.schemeName = StringPool.getInstance().getOrAddToCache(value, TransactionFinder.isFullCache());
	}

	public void setSchemeNameNull()
	{
		this.setSchemeName(null);
	}

	public String getSource()
	{
		return this.source;
	}

	public int zGetSourceAsInt()
	{
		return StringPool.getInstance().getOffHeapAddressWithoutAdding(source);
	}

	public void setSource(String value)
	{
		this.source = StringPool.getInstance().getOrAddToCache(value, TransactionFinder.isFullCache());
	}

	public void setSourceNull()
	{
		this.setSource(null);
	}

	public double getUnitBalance()
	{
		return this.unitBalance;
	}

	public void setUnitBalance(double value)
	{
		this.unitBalance = value;
		isNullBits0 = (byte)((int)isNullBits0 & ~( 1 << 2));
	}

	public double getUnits()
	{
		return this.units;
	}

	public void setUnits(double value)
	{
		this.units = value;
		isNullBits0 = (byte)((int)isNullBits0 & ~( 1));
	}

	public void setUnitsNull()
	{
		isNullBits0 = (byte)((int)isNullBits0 | 1);
	}

	public void setPriceNull()
	{
		isNullBits0 = (byte)((int)isNullBits0 | 1 << 1);
	}

	public void setUnitBalanceNull()
	{
		isNullBits0 = (byte)((int)isNullBits0 | 1 << 2);
	}

	public byte zGetIsNullBits0()
	{
		return this.isNullBits0;
	}

	public void zSetIsNullBits0(byte newValue)
	{
		this.isNullBits0 = newValue;
	}

	public boolean _isIdSet;
	public boolean zGetIsIdSet()
	{
		return _isIdSet;
	}

	protected void copyInto(TransactionData copy, boolean withRelationships)
	{
		copy.isNullBits0 = this.isNullBits0;
		copy.amount = this.amount;
		copy.date = this.date;
		copy.description = this.description;
		copy.folioNumber = this.folioNumber;
		copy.id = this.id;
		copy.price = this.price;
		copy.registrar = this.registrar;
		copy.rtaCode = this.rtaCode;
		copy.schemeCode = this.schemeCode;
		copy.schemeName = this.schemeName;
		copy.source = this.source;
		copy.unitBalance = this.unitBalance;
		copy.units = this.units;
		copy._isIdSet = this._isIdSet;
		if (withRelationships)
		{
			if (_relationships != null)
			{
				copy._relationships = new Object[1];
				System.arraycopy(_relationships, 0, copy._relationships, 0, _relationships.length);
			}
		}
	}

	public void zDeserializeFullData(ObjectInput in) throws IOException, ClassNotFoundException
	{
		this.isNullBits0 = in.readByte();
		this.amount = in.readDouble();
		this.date = TimestampPool.getInstance().getOrAddToCache(MithraTimestamp.readTimezoneInsensitiveTimestamp(in), TransactionFinder.isFullCache(), TransactionFinder.isOffHeap());
		this.description = StringPool.getInstance().getOrAddToCache((String)in.readObject(), TransactionFinder.isFullCache());
		this.folioNumber = StringPool.getInstance().getOrAddToCache((String)in.readObject(), TransactionFinder.isFullCache());
		this.id = in.readInt();
		_isIdSet = in.readBoolean();
		this.price = in.readDouble();
		this.registrar = StringPool.getInstance().getOrAddToCache((String)in.readObject(), TransactionFinder.isFullCache());
		this.rtaCode = StringPool.getInstance().getOrAddToCache((String)in.readObject(), TransactionFinder.isFullCache());
		this.schemeCode = in.readInt();
		this.schemeName = StringPool.getInstance().getOrAddToCache((String)in.readObject(), TransactionFinder.isFullCache());
		this.source = StringPool.getInstance().getOrAddToCache((String)in.readObject(), TransactionFinder.isFullCache());
		this.unitBalance = in.readDouble();
		this.units = in.readDouble();
	}

	public boolean hasSamePrimaryKeyIgnoringAsOfAttributes(MithraDataObject other)
	{
		if (this == other) return true;
		final TransactionData otherData = (TransactionData) other;
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
		_relationships = null;
		clearAllDirectRefs();
	}

	public void clearAllDirectRefs()
	{
	}

	public Object getScheme()
	{
		if (_relationships != null)
		{
			return _relationships[0];
		}

		return null;
	}

	public void setScheme(Object related)
	{
		if (_relationships == null)
		{
			_relationships = new Object[1];
		}

		_relationships[0] = related;
	}

	public void zSerializeRelationships(ObjectOutputStream out) throws IOException
	{
		if (_relationships == null)
		{
			out.writeInt(0);
			return;
		}

		out.writeInt(_relationships.length);
		for(int i=0;i<_relationships.length;i++)
		{
			out.writeObject(_relationships[i]);
		}
	}

	public void zDeserializeRelationships(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		int total = in.readInt();
		if(total > 0)
		{
			_relationships = new Object[total];
			for(int i=0;i<total;i++)
			{
				_relationships[i] = in.readObject();
			}
		}
	}

	public MithraOffHeapDataObject zCopyOffHeap()
	{
		throw new RuntimeException("off heap no supported");
	}

	public void copyNonPkAttributes(MithraDataObject newData)
	{
		final TransactionData transactionData = (TransactionData) newData;
		this.setAmount(transactionData.getAmount());
		this.setDate(transactionData.getDate());
		this.setDescription(transactionData.getDescription());
		this.setFolioNumber(transactionData.getFolioNumber());
		this.setPrice(transactionData.getPrice());
		if (transactionData.isPriceNull()) this.setPriceNull();
		this.setRegistrar(transactionData.getRegistrar());
		this.setRtaCode(transactionData.getRtaCode());
		this.setSchemeCode(transactionData.getSchemeCode());
		this.setSchemeName(transactionData.getSchemeName());
		this.setSource(transactionData.getSource());
		this.setUnitBalance(transactionData.getUnitBalance());
		if (transactionData.isUnitBalanceNull()) this.setUnitBalanceNull();
		this.setUnits(transactionData.getUnits());
		if (transactionData.isUnitsNull()) this.setUnitsNull();
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
		final TransactionData other = (TransactionData) newData;
		if ( Math.abs(getAmount() - other.getAmount()) > toleranceForFloatingPointFields)
		{
			return true;
		}

		if (!isDateNull() ? !getDate().equals(other.getDate()) : !other.isDateNull())
		{
			return true;
		}

		if (!isDescriptionNull() ? !getDescription().equals(other.getDescription()) : !other.isDescriptionNull())
		{
			return true;
		}

		if (!isFolioNumberNull() ? !getFolioNumber().equals(other.getFolioNumber()) : !other.isFolioNumberNull())
		{
			return true;
		}

		if ( isPriceNull() != other.isPriceNull() || Math.abs(getPrice() - other.getPrice()) > toleranceForFloatingPointFields)
		{
			return true;
		}

		if (!isRegistrarNull() ? !getRegistrar().equals(other.getRegistrar()) : !other.isRegistrarNull())
		{
			return true;
		}

		if (!isRtaCodeNull() ? !getRtaCode().equals(other.getRtaCode()) : !other.isRtaCodeNull())
		{
			return true;
		}

		if ( getSchemeCode() != other.getSchemeCode())
		{
			return true;
		}

		if (!isSchemeNameNull() ? !getSchemeName().equals(other.getSchemeName()) : !other.isSchemeNameNull())
		{
			return true;
		}

		if (!isSourceNull() ? !getSource().equals(other.getSource()) : !other.isSourceNull())
		{
			return true;
		}

		if ( isUnitBalanceNull() != other.isUnitBalanceNull() || Math.abs(getUnitBalance() - other.getUnitBalance()) > toleranceForFloatingPointFields)
		{
			return true;
		}

		if ( isUnitsNull() != other.isUnitsNull() || Math.abs(getUnits() - other.getUnits()) > toleranceForFloatingPointFields)
		{
			return true;
		}

		return false;
	}

	public MithraDataObject copy()
	{
		TransactionData copy = new TransactionData();
		this.copyInto(copy, true);
		return copy;
	}

	public MithraDataObject copy(boolean withRelationships)
	{
		TransactionData copy = new TransactionData();
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
		return "tracker.domain.TransactionData";
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
		return TransactionFinder.getMithraObjectPortal();
	}

	public MithraObjectPortal zGetMithraObjectPortal()
	{
		return TransactionFinder.getMithraObjectPortal();
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
		return "tracker.domain.TransactionData";
	}
}
